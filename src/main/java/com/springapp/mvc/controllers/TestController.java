package com.springapp.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/test1")
public class TestController {

    //private static final String MODEL_CONFIG_PATH = "classpath*:system/drilling/parametersModel/config.xml";

    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        return "test1";
    }
}