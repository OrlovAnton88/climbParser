import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

/**
 * Author:      oav <br>
 * Date:        12.10.15, 18:57 <br>
 * Company:     SofIT labs <br>
 * Revision:    $Id$ <br>
 * Description: <br>
 */

public class Main {

    public static final String BASE_URL = "https://27crags.com/";
    public static final String STALKER = "crags/stalker/";
    public static final String MONREPO = "crags/monrepo";
    public static final String KUB = "crags/kub";

    public static void main(String[] args) {
        Document doc;

        Set<String> sectorlinkList = new HashSet<String>();
        List<String> links = Arrays.asList(
//                "https://27crags.com/crags/stalker",
                "https://27crags.com/crags/stalker/more_routes?prev_grade=6a");

        List<Sector> sectors = new ArrayList<Sector>();
        for (String link : links) {
            try {
                doc = Jsoup.connect(link).timeout(10000).get();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                return;
            }
            final Elements tables = doc.select("div.centered table.data.list");

            System.out.println("Tables found[" + tables.size() + "]");

            for (Element table : tables) {
                final Elements rows = table.select("tr");
                for (Element row : rows) {
                    final String sectorLink = row.select("td.stxt>a").attr("href");
                    sectorlinkList.add(BASE_URL + sectorLink);
                }
            }
        }

        List<String> routeLinksList = new ArrayList<String>();

        for (String sectorlink : sectorlinkList) {
            try {
                doc = Jsoup.connect(sectorlink).timeout(10000).get();
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
                    routeLinksList.add(BASE_URL + routeLink);
                }
            }
        }

        for (String routeLink : routeLinksList) {

        }
//        System.out.println(routeLinksList.size());
//        Route route = new Route()

    }

}
