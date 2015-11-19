package ru.anton.orlov.miracleguide.controllers.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author:      oav <br>
 * Date:        19.11.15, 12:13 <br>
 * Company:     SofIT labs <br>
 * Revision:    $Id$ <br>
 * Description: <br>
 */
@RestController
public class ManageController {

    private static final Logger log = LoggerFactory.getLogger(ManageController.class);

    @RequestMapping(value = "/api/manage/area", method = RequestMethod.POST)
    public ResponseEntity areaPost(@RequestBody String url) {
        log.debug("ManageController area called");
        log.debug("URL[" + url + "]");

//        List<Area> allAreas = areaService.getAllAreas();

        return new ResponseEntity(HttpStatus.OK);
    }

}
