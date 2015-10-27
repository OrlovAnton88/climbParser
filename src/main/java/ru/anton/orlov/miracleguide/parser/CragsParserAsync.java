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

                FutureTask<Topo> task = new FutureTask<Topo>(new TopoParser(topo, area));
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

        if (!toposSet.isEmpty()) {
            final Topo next = toposSet.iterator().next();
            area.setImageLink(next.getImageLink());
        }
    }
}
