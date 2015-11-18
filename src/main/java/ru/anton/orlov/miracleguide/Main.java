package ru.anton.orlov.miracleguide;

import ru.anton.orlov.miracleguide.json.JsonUtils;
import ru.anton.orlov.miracleguide.parser.CragsParser;
import ru.anton.orlov.miracleguide.parser.CragsParserAsync;
import ru.anton.orlov.miracleguide.parser.model.Area;
import ru.anton.orlov.miracleguide.utils.FileUtils;

/**
 * Author:      oav <br>
 * Date:        12.10.15, 18:57 <br>
 * Company:     SofIT labs <br>
 * Revision:    $Id$ <br>
 * Description: <br>
 */

public class Main {

    public static void main(String[] args) {

//        Area area = CragsParser.getArea(CragsParser.BASE_URL + CragsParser.STALKER);
//
//        String json = JsonUtils.getJson(area);
//
//        FileUtils.saveToFile(json,Conf.RESOURSES_PATH, "stalker.json");

//        String elements[] = { "A", "B", "C", "D", "E" };
//        Set set = new HashSet<>(Arrays.asList(elements));
//
////        Set set2 = ((Set) ((HashSet) set).clone());
//        Set set2 = new HashSet<>(set);
//        set.remove("A");
//        System.out.println(set);
//        System.out.println(set2);

//        final long start = System.currentTimeMillis();
        CragsParserAsync async = new CragsParserAsync();
        final Area area = async.getArea(CragsParser.BASE_URL + CragsParser.STALKER);
//
        String json = JsonUtils.getJson(area);

        FileUtils.saveToFile(json, Conf.RESOURSES_PATH, "stalker.json");
//
//        final long end = System.currentTimeMillis();
//
//        System.out.println("Done in " + (end - start) + " millis");


//        Done in 46121 millis

//        final long start = System.currentTimeMillis();
//
//        CragsParser.getArea(CragsParser.BASE_URL + CragsParser.STALKER);
//
//        final long end = System.currentTimeMillis();
//
//        System.out.println("Done in " + (end - start) + " millis");

//        Topo topo = new Topo("https://27crags.com//crags/stalker/topos/17348","Шило");
//        FutureTask<Topo> task = new FutureTask<Topo>(new TopoParser(topo));
//
//        ExecutorService executor = Executors.newFixedThreadPool(1);
//        executor.execute(task);
//
//        executor.shutdown();
//        try {
//            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
//        } catch (InterruptedException e) {
//            System.out.println(e.getMessage());
//        }
//
//        try {
//            System.out.println(task.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

    }

}
