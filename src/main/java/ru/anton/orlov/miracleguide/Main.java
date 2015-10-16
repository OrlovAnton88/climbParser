package ru.anton.orlov.miracleguide;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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

        String elements[] = { "A", "B", "C", "D", "E" };
        Set set = new HashSet<>(Arrays.asList(elements));

//        Set set2 = ((Set) ((HashSet) set).clone());
        Set set2 = new HashSet<>(set);
        set.remove("A");
        System.out.println(set);
        System.out.println(set2);


    }

}
