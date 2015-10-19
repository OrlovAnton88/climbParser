package ru.anton.orlov.miracleguide.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ru.anton.orlov.miracleguide.Conf;
import ru.anton.orlov.miracleguide.json.JsonUtils;
import ru.anton.orlov.miracleguide.parser.model.Point;
import ru.anton.orlov.miracleguide.parser.model.Route;
import ru.anton.orlov.miracleguide.parser.model.VectorLine;
import ru.anton.orlov.miracleguide.utils.FileUtils;
import ru.anton.orlov.miracleguide.utils.ImageUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

/**
 * Author:      oav <br>
 * Date:        19.10.15, 15:02 <br>
 * Company:     SofIT labs <br>
 * Revision:    $Id$ <br>
 * Description: <br>
 */
public class RouteParser implements Callable<Route> {

    private Route route;

    public RouteParser(final Route route) {
        this.route = route;
    }

    @Override
    public Route call() throws Exception {
        return fillRoute(route);
    }

    private Route fillRoute(final Route route) {
        String link = route.getLink();
        Document doc;
        try {
            doc = Jsoup.connect(link).timeout(10000).get();
            try {
                Thread.sleep(500);                 //1000 milliseconds is one second.
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        } catch (IOException ex) {
            System.out.println("FAIL: " + ex.getMessage() +"; LINK[" + link + "]");
            return null;
        }

        final String name = doc.select("div.headline h1").first().text();
        final String desc = doc.select("div.headline h2").first().text();

        final String imageLink = doc.select("div.main div.pics img").first().attr("src");

        route.setName(name);
        route.setDesc(desc);
        route.setImageLink(imageLink);

        //Boulder, 6B+ at Крыша, Stalker in the area of Vyborg, Russian Federation'
        final String[] split = desc.split(",");
        final String levelAndSector = split[1];
        final String[] ls = levelAndSector.split("at");
        if (ls.length == 2) {
            route.setLevel(ls[0].trim());
            route.setSectorName(ls[1].trim());
        }

        //get route lines

        String coordsForImage = doc.select("script.js-data").html();
        String coords = null;
        if (coordsForImage.contains("strong_line")) {
            int beforeStart = coordsForImage.lastIndexOf("strong_line");
            int start = coordsForImage.indexOf("[[", beforeStart);
            int end = coordsForImage.indexOf("]]", beforeStart) + 2;
            coords = coordsForImage.substring(start, end);

        }

        //save image
        File image = null;
        try {
            image = ImageUtils.getImage(route.getImageLink());
        } catch (IOException e) {
            e.printStackTrace();
        }

        int width = 0;
        int height = 0;
        if (image != null) {
            try {
                BufferedImage img = ImageIO.read(image);
                width = img.getWidth();
                height = img.getHeight();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        if (coords != null && width != 0 && height != 0) {
            Optional<VectorLine> vector = getVector(coords, width, height);
            if (vector.isPresent()) {
                route.setLine(vector.get());
            }
        }

        int i = route.getLink().lastIndexOf('/');
        String routeId = route.getLink().substring(i, route.getLink().length());
        String json = JsonUtils.getJson(route);
        FileUtils.saveToFile(json, Conf.RESOURSES_PATH + "json/route", routeId + ".json");
        return route;
    }

    /**
     * Get VectorLine object from string
     *
     * @param points Example: [[340,265],[347,217]]
     * @return VectorLine object
     */
    private static Optional<VectorLine> getVector(final String points, int imageWidth, int imageHeight) {

        String[] xy = points.split("],\\[");
        List<Point> poins = new ArrayList<>();
        for (String s : xy) {

            String[] split = s.split(",");
            String xStr;
            String yStr;

            if (split.length == 2) {
                xStr = extractNums(split[0].toCharArray());
                yStr = extractNums(split[1].toCharArray());

            } else {
                System.out.println("wrong coordinates");
                return Optional.empty();
            }
            System.out.println("x[" + xStr + "],y[" + yStr + "]");
            Integer x;
            Integer y;

            try {
                x = Integer.valueOf(xStr);
                y = Integer.valueOf(yStr);
            } catch (NumberFormatException ex) {
                System.out.println("ERROR parsing coordinates");
                return Optional.empty();
            }

            poins.add(new Point(x, y));

        }

        return Optional.ofNullable(new VectorLine(imageWidth, imageHeight, poins));

    }

    private static String extractNums(char[] chars) {
        String result = "";
        for (char c : chars) {
            if (Character.isDigit(c)) {
                result += c;
            }
        }
        return result;
    }
}
