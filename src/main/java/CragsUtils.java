import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by antonorlov on 13/10/15.
 */
public class CragsUtils {


    public static Route getRoute(final String link) {
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
        Route route = new Route(name, desc, imageLink);

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

//        save image
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

        return route;
    }
}
