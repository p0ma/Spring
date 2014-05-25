package com.springapp.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HelloController{

    //private static final String MODEL_CONFIG_PATH = "classpath*:system/drilling/model/config.xml";

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		return "redirect:parameters";
	}
}