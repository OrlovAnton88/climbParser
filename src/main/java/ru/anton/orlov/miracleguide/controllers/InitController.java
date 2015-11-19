package ru.anton.orlov.miracleguide.controllers;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.anton.orlov.miracleguide.Conf;
import ru.anton.orlov.miracleguide.controllers.api.AreaController;
import ru.anton.orlov.miracleguide.parser.model.Area;
import ru.anton.orlov.miracleguide.service.AreaService;
import ru.anton.orlov.miracleguide.utils.FileUtils;

import java.util.*;

/**
 * Created by antonorlov on 03/11/15.
 */
@Controller
public class InitController {

    @Autowired
    private AreaService areaService;

    private static final Logger log = LoggerFactory.getLogger(AreaController.class);

    @RequestMapping(value = "/availableAreas", method = RequestMethod.GET)
    public ModelAndView available() {

        Map<String, Boolean> map = new HashMap<>();

        String stalker = FileUtils.readFile(Conf.RESOURSES_PATH + "stalker.json");
        if (stalker != null && !stalker.isEmpty()) {
            Area area = new Gson().fromJson(stalker, Area.class);
            Optional<Area> areaDb = areaService.getArea(area.getName());
            map.put(area.getTranslitName(), areaDb.isPresent());
        }

        String monrepo = FileUtils.readFile(Conf.RESOURSES_PATH + "monrepo.json");
        if (monrepo != null && !monrepo.isEmpty()) {
            Area area = new Gson().fromJson(monrepo, Area.class);
            Optional<Area> areaDb = areaService.getArea(area.getName());
            map.put(area.getTranslitName(), areaDb.isPresent());

        }

        String  triangular = FileUtils.readFile(Conf.RESOURSES_PATH + "triangular_lake.json");
        if(triangular !=null && !triangular.isEmpty()){
//            areas.add("triangular_lake");
            Area area  = new Gson().fromJson(triangular, Area.class);
            Optional<Area> areaDb = areaService.getArea(area.getName());
            map.put(area.getTranslitName(),areaDb.isPresent());
        }

        ModelAndView mv = new ModelAndView("available_areas");
        mv.addObject("areas",map.entrySet());
        return mv;
    }

    @RequestMapping(value = "/add_to_db", method = RequestMethod.GET)
    public ModelAndView available(@RequestParam(value = "name", required = true) String name) {

        String area = FileUtils.readFile(Conf.RESOURSES_PATH + name +".json");

        Area areaObj = new Gson().fromJson(area, Area.class);
        areaService.saveArea(areaObj);

        return new ModelAndView("redirect:/availableAreas");
    }


}


