package ru.anton.orlov.miracleguide.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Author:      oav <br>
 * Date:        19.10.15, 19:09 <br>
 * Company:     SofIT labs <br>
 * Revision:    $Id$ <br>
 * Description: <br>
 */
@Controller
public class HelloController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ModelAndView hello() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("hello");

        String str = "Hello World!";
        mav.addObject("message", str);

        return mav;
    }

}