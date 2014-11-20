package com.springapp.mvc.controllers;

import entities.auth.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({"/", "/welcome"})
public class HelloController {

    //private static final String MODEL_CONFIG_PATH = "classpath*:system/drilling/parametersModel/config.xml";

    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(@AuthenticationPrincipal User user, ModelMap model) {
        if (user != null) {
            return "redirect:/parameters";
        } else {
            return "redirect:/user/login";
        }
    }
}