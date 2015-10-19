package ru.anton.orlov.miracleguide.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.anton.orlov.miracleguide.Conf;
import ru.anton.orlov.miracleguide.json.JsonUtils;
import ru.anton.orlov.miracleguide.model.Coordinates;
import ru.anton.orlov.miracleguide.parser.model.Route;
import ru.anton.orlov.miracleguide.parser.model.Topo;
import ru.anton.orlov.miracleguide.utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

/**
 * Author:      oav <br>
 * Date:        19.10.15, 14:59 <br>
 * Company:     SofIT labs <br>
 * Revision:    $Id$ <br>
 * Description: <br>
 */
public class TopoParser implements Callable<Topo> {

    public static final String BASE_URL = "https://27crags.com/";

    private Topo topo;

    public TopoParser(final Topo topo) {
        this.topo = topo;
    }

    @Override
    public Topo call() throws Exception {
        return fillTopo(topo);
    }

    private Topo fillTopo(final Topo topo) {

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
            System.out.println("FAIL[" + ex.getMessage() + "],LINK[" + topo.getLink() + "]");
            return null;
        }
        final Element centerDiv = doc.select("div.centered.t14").first();
        if (centerDiv != null) {
            List<Route> routes = new ArrayList<Route>();
            final Elements r = centerDiv.select("ol li");

            int taskCounter = 0;
            int threadNum = 5;
            List<FutureTask<Route>> taskList = new ArrayList<FutureTask<Route>>();

            ExecutorService executor = Executors.newFixedThreadPool(threadNum);

            for (Element element : r) {
                final String routeLink = element.select("a").first().attr("href");
                Route route = new Route(BASE_URL + routeLink);

                FutureTask<Route> task = new FutureTask<Route>(new RouteParser(route));
                taskList.add(task);
                executor.execute(task);
                System.out.println(taskCounter + " route task go to execution");
                taskCounter++;
            }

            executor.shutdown();
            try {
                executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }

            for (FutureTask<Route> routeFutureTask : taskList) {
                Route route = null;
                try {
                    route = routeFutureTask.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                if (route != null) {
                    routeSet.add(route);
                }

            }

        }

        //        latitude: 60.750354, longitude: 28.794804
        final String coordinatesStr = doc.select("#coordinates div.qr div.b8").first().html();

        double latitude = 0.0;
        double longitude = 0.0;
        final String[] split = coordinatesStr.split(",");
        for (String s : split) {
            if (s.contains("latitude")) {
                final int start = s.indexOf(":") + 1;
                final String lat = s.substring(start, s.length()).replaceAll(" ", "");
                try {
                    latitude = Double.valueOf(lat);
                } catch (NumberFormatException ex) {
                    System.out.println("error convert " + lat + " to double");
                }
            }
            if (s.contains("longitude")) {
                final int start = s.indexOf(":") + 1;
                final String lon = s.substring(start, s.length()).replaceAll(" ", "");
                try {
                    longitude = Double.valueOf(lon);
                } catch (NumberFormatException ex) {
                    System.out.println("error convert " + lon + " to double");
                }
            }
        }

        if (latitude != 0.0 && longitude != 0.0) {
            Coordinates c = new Coordinates(latitude, longitude);
            topo.setCoordinates(c);
        }

        topo.setRoutes(routeSet);
        int i = topo.getLink().lastIndexOf('/');
        String topoId = topo.getLink().substring(i, topo.getLink().length());
        String json = JsonUtils.getJson(topo);
        FileUtils.saveToFile(json, Conf.RESOURSES_PATH + "json/topo", topoId + ".json");

        return topo;

    }
}
