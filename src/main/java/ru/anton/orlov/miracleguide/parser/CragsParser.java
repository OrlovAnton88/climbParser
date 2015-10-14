package ru.anton.orlov.miracleguide.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.anton.orlov.miracleguide.Conf;
import ru.anton.orlov.miracleguide.json.JsonUtils;
import ru.anton.orlov.miracleguide.parser.model.Area;
import ru.anton.orlov.miracleguide.parser.model.Route;
import ru.anton.orlov.miracleguide.parser.model.Topo;
import ru.anton.orlov.miracleguide.utils.FileUtils;
import ru.anton.orlov.miracleguide.utils.ImageUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by antonorlov on 13/10/15.
 */
public class CragsParser {

    public static final String BASE_URL = "https://27crags.com/";
    public static final String STALKER = "crags/stalker/";
    public static final String MONREPO = "crags/monrepo";
    public static final String KUB = "crags/kub";


    public static Area getArea(final String areaLink) {
        Area area = new Area(areaLink);
        fillArea(area);
        return area;

    }


    private static void fillArea(final Area area) {

        Document doc;

        Set<Topo> toposSet = new HashSet<Topo>();

        try {
            doc = Jsoup.connect(area.getLink()).timeout(10000).get();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return;
        }

        final String areaName = doc.select("div.headline h1").first().text();
        area.setName(areaName);

        final Elements tables = doc.select("div.centered table.data.list");

        System.out.println("Tables found[" + tables.size() + "]");

        for (Element table : tables) {
            final Elements rows = table.select("tr");
            for (Element row : rows) {
                Element link = row.select("td.stxt>a").first();
                if (link == null) {
                    continue;
                }
                final String relativeLink = link.attr("href");
                String name = link.html();
                final String topoLink = BASE_URL + relativeLink;
                Topo topo = new Topo(topoLink, name);
                fillTopo(topo);
                toposSet.add(topo);
            }
        }

        area.setTopos(toposSet);

    }

    private static void fillTopo(final Topo topo) {

        Document doc;

        Set<Route> routeSet = new HashSet<Route>();

        try {
            doc = Jsoup.connect(topo.getLink()).timeout(10000).get();
            try {
                Thread.sleep(500);                 //1000 milliseconds is one second.
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        } catch (IOException ex) {
            System.out.println("sectorlink: " + ex.getMessage());
            return;
        }
        final Element centerDiv = doc.select("div.centered.t14").first();
        if (centerDiv != null) {
            List<Route> routes = new ArrayList<Route>();
            final Elements r = centerDiv.select("ol li");
            for (Element element : r) {
                final String routeLink = element.select("a").first().attr("href");
                Route route = new Route(BASE_URL + routeLink);
                fillRoute(route);
                routeSet.add(route);
            }
        }

        topo.setRoutes(routeSet);
        int i = topo.getLink().lastIndexOf('/');
        String topoId = topo.getLink().substring(i, topo.getLink().length());
        String json = JsonUtils.getJson(topo);
        FileUtils.saveToFile(json, Conf.RESOURSES_PATH + "json/topo", topoId + ".json");

    }

    private static Route fillRoute(final Route route) {
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
            System.out.println("FAIL: " + ex.getMessage());
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
        if (coordsForImage.contains("strong_line")) {
            int beforeStart = coordsForImage.lastIndexOf("strong_line");
            int start = coordsForImage.indexOf("[[", beforeStart);
            int end = coordsForImage.indexOf("]]", beforeStart) + 2;
            String coords = coordsForImage.substring(start, end);
            route.setDirtyCoordinates(coords);
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
            route.setImageWidth(width);
            route.setImageHeight(height);
        }

        int i = route.getLink().lastIndexOf('/');
        String routeId = route.getLink().substring(i, route.getLink().length());
        String json = JsonUtils.getJson(route);
        FileUtils.saveToFile(json, Conf.RESOURSES_PATH + "json/route", routeId + ".json");
        return route;
    }
}