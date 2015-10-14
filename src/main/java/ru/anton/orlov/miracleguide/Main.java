package ru.anton.orlov.miracleguide;

import ru.anton.orlov.miracleguide.json.JsonUtils;
import ru.anton.orlov.miracleguide.parser.CragsParser;
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


        Area area = CragsParser.getArea(CragsParser.BASE_URL + CragsParser.STALKER);

        String json = JsonUtils.getJson(area);

        FileUtils.saveToFile(json,Conf.RESOURSES_PATH, "stalker.json");


    }

}
