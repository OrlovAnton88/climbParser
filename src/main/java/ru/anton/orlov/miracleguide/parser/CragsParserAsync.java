package ru.anton.orlov.miracleguide.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.anton.orlov.miracleguide.parser.model.Area;
import ru.anton.orlov.miracleguide.parser.model.Topo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

/**
 * Author:      oav <br>
 * Date:        19.10.15, 11:21 <br>
 * Company:     SofIT labs <br>
 * Revision:    $Id$ <br>
 * Description: <br>
 */
public class CragsParserAsync {

//todo:
//    awaitTermination on the ExecutorService.

    public static final String BASE_URL = "https://27crags.com/";
    public static final String STALKER  = "crags/stalker/";
    public static final String MONREPO  = "crags/monrepo";
    public static final String KUB      = "crags/kub";

    private final ExecutorService pool = Executors.newFixedThreadPool(10);


    public Area getArea(final String areaLink) {
        Area area = new Area(areaLink);
        fillArea(area);
        return area;

    }

    private void fillArea(final Area area) {

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

            int taskCounter = 0;
            int threadNum = 5;
            List<FutureTask<Topo>> taskList = new ArrayList<FutureTask<Topo>>();

            ExecutorService executor = Executors.newFixedThreadPool(threadNum);

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
                //todo:

                FutureTask<Topo> task = new FutureTask<Topo>(new TopoParser(topo));
                taskList.add(task);
                executor.execute(task);
                System.out.println(taskCounter + " TOPO task go to execution");
                taskCounter++;

            }

            executor.shutdown();
            try {
                executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }

            for (FutureTask<Topo> topoFutureTask : taskList) {
                Topo topo = null;
                try {
                    topo = topoFutureTask.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                if (topo != null) {
                    toposSet.add(topo);
                }
            }
        }

        area.setTopos(toposSet);

    }

//    public Future<Topo> fillTopoAsync(final Topo topo) {
//        return pool.submit(new Callable<Topo>() {
//
//            @Override
//            public Topo call() throws Exception {
//                fillTopo(topo);
//                return topo;
//            }
//        });
//    }

//    private static void fillTopo(final Topo topo) {
//
//        Document doc;
//
//        Set<Route> routeSet = new HashSet<Route>();
//
//        try {
//            doc = Jsoup.connect(topo.getLink()).timeout(10000).get();
//            try {
//                Thread.sleep(500);                 //1000 milliseconds is one second.
//            } catch (InterruptedException ex) {
//                Thread.currentThread().interrupt();
//            }
//        } catch (IOException ex) {
//            System.out.println("sectorlink: " + ex.getMessage());
//            return;
//        }
//        final Element centerDiv = doc.select("div.centered.t14").first();
//        if (centerDiv != null) {
//            List<Route> routes = new ArrayList<Route>();
//            final Elements r = centerDiv.select("ol li");
//            for (Element element : r) {
//                final String routeLink = element.select("a").first().attr("href");
//                Route route = new Route(BASE_URL + routeLink);
//                fillRoute(route);
//                routeSet.add(route);
//            }
//        }
//
//        //        latitude: 60.750354, longitude: 28.794804
//        final String coordinatesStr = doc.select("#coordinates div.qr div.b8").first().html();
//
//        double latitude = 0.0;
//        double longitude = 0.0;
//        final String[] split = coordinatesStr.split(",");
//        for (String s : split) {
//            if (s.contains("latitude")) {
//                final int start = s.indexOf(":") + 1;
//                final String lat = s.substring(start, s.length()).replaceAll(" ", "");
//                try {
//                    latitude = Double.valueOf(lat);
//                } catch (NumberFormatException ex) {
//                    System.out.println("error convert " + lat + " to double");
//                }
//            }
//            if (s.contains("longitude")) {
//                final int start = s.indexOf(":") + 1;
//                final String lon = s.substring(start, s.length()).replaceAll(" ", "");
//                try {
//                    longitude = Double.valueOf(lon);
//                } catch (NumberFormatException ex) {
//                    System.out.println("error convert " + lon + " to double");
//                }
//            }
//        }
//
//        if (latitude != 0.0 && longitude != 0.0) {
//            Coordinates c = new Coordinates(latitude, longitude);
//            topo.setCoordinates(c);
//        }
//
//        topo.setRoutes(routeSet);
//        int i = topo.getLink().lastIndexOf('/');
//        String topoId = topo.getLink().substring(i, topo.getLink().length());
//        String json = JsonUtils.getJson(topo);
//        FileUtils.saveToFile(json, Conf.RESOURSES_PATH + "json/topo", topoId + ".json");

//    }

//    private static Route fillRoute(final Route route) {
//        String link = route.getLink();
//        Document doc;
//        try {
//            doc = Jsoup.connect(link).timeout(10000).get();
//            try {
//                Thread.sleep(500);                 //1000 milliseconds is one second.
//            } catch (InterruptedException ex) {
//                Thread.currentThread().interrupt();
//            }
//        } catch (IOException ex) {
//            System.out.println("FAIL: " + ex.getMessage());
//            return null;
//        }
//
//        final String name = doc.select("div.headline h1").first().text();
//        final String desc = doc.select("div.headline h2").first().text();
//
//        final String imageLink = doc.select("div.main div.pics img").first().attr("src");
//
//        route.setName(name);
//        route.setDesc(desc);
//        route.setImageLink(imageLink);
//
//        //Boulder, 6B+ at Крыша, Stalker in the area of Vyborg, Russian Federation'
//        final String[] split = desc.split(",");
//        final String levelAndSector = split[1];
//        final String[] ls = levelAndSector.split("at");
//        if (ls.length == 2) {
//            route.setLevel(ls[0].trim());
//            route.setSectorName(ls[1].trim());
//        }
//
//        //get route lines
//
//        String coordsForImage = doc.select("script.js-data").html();
//        String coords = null;
//        if (coordsForImage.contains("strong_line")) {
//            int beforeStart = coordsForImage.lastIndexOf("strong_line");
//            int start = coordsForImage.indexOf("[[", beforeStart);
//            int end = coordsForImage.indexOf("]]", beforeStart) + 2;
//            coords = coordsForImage.substring(start, end);
//
//        }
//
//        //save image
//        File image = null;
//        try {
//            image = ImageUtils.getImage(route.getImageLink());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        int width = 0;
//        int height = 0;
//        if (image != null) {
//            try {
//                BufferedImage img = ImageIO.read(image);
//                width = img.getWidth();
//                height = img.getHeight();
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
//
//        if (coords != null && width != 0 && height != 0) {
//            Optional<VectorLine> vector = getVector(coords, width, height);
//            if (vector.isPresent()) {
//                route.setLine(vector.get());
//            }
//        }
//
//        int i = route.getLink().lastIndexOf('/');
//        String routeId = route.getLink().substring(i, route.getLink().length());
//        String json = JsonUtils.getJson(route);
//        FileUtils.saveToFile(json, Conf.RESOURSES_PATH + "json/route", routeId + ".json");
//        return route;
//    }
//
//    /**
//     * Get VectorLine object from string
//     *
//     * @param points Example: [[340,265],[347,217]]
//     * @return VectorLine object
//     */
//    private static Optional<VectorLine> getVector(final String points, int imageWidth, int imageHeight) {
//
//        String[] xy = points.split("],\\[");
//        List<Point> poins = new ArrayList<>();
//        for (String s : xy) {
//
//            String[] split = s.split(",");
//            String xStr;
//            String yStr;
//
//            if (split.length == 2) {
//                xStr = extractNums(split[0].toCharArray());
//                yStr = extractNums(split[1].toCharArray());
//
//            } else {
//                System.out.println("wrong coordinates");
//                return Optional.empty();
//            }
//            System.out.println("x[" + xStr + "],y[" + yStr + "]");
//            Integer x;
//            Integer y;
//
//            try {
//                x = Integer.valueOf(xStr);
//                y = Integer.valueOf(yStr);
//            } catch (NumberFormatException ex) {
//                System.out.println("ERROR parsing coordinates");
//                return Optional.empty();
//            }
//
//            poins.add(new Point(x, y));
//
//        }
//
//        return Optional.ofNullable(new VectorLine(imageWidth, imageHeight, poins));
//
//    }
//
//    private static String extractNums(char[] chars) {
//        String result = "";
//        for (char c : chars) {
//            if (Character.isDigit(c)) {
//                result += c;
//            }
//        }
//        return result;
//    }

}
