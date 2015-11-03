package ru.anton.orlov.miracleguide.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ru.anton.orlov.miracleguide.parser.model.Area;
import ru.anton.orlov.miracleguide.service.AreaService;

import java.util.List;

/**
 * Created by antonorlov on 19/10/15.
 */
@Controller
public class AreaController {

    private static final Logger log = LoggerFactory.getLogger(AreaController.class);

    @Autowired
    private AreaService areaService;

    @RequestMapping(value = "/area", method = RequestMethod.GET)
    public ModelAndView area() {
        log.debug("area called");
        ModelAndView mv = new ModelAndView("area");

        List<Area> allAreas = areaService.getAllAreas();
        mv.addObject("allAreas",allAreas);
        return mv;
    }


    @RequestMapping(value = "/areas_rest", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getAreas() {
        log.debug("area called");

        List<Area> allAreas = areaService.getAllAreas();

        return new ResponseEntity(allAreas,HttpStatus.OK);
    }

//    @ResponseBody
//    public String getJson(){
//
//    }
}