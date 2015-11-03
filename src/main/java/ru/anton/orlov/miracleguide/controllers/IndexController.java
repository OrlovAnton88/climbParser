package ru.anton.orlov.miracleguide.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Author:      oav <br>
 * Date:        03.11.15, 13:57 <br>
 * Company:     SofIT labs <br>
 * Revision:    $Id$ <br>
 * Description: <br>
 */
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<String> area() {

        return new ResponseEntity<String>(HttpStatus.OK);

    }

}
