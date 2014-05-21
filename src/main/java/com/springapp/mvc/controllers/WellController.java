package com.springapp.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import system.drilling.model.well.PipeSection;
import system.drilling.model.well.PipeType;
import system.drilling.service.ParametersModelService;
import system.drilling.service.PipeSectionService;
import system.drilling.service.WellService;

@Controller
@RequestMapping("/well")
public class WellController {

    @Autowired
    private ParametersModelService parametersModelService;

    @Autowired
    private WellService wellService;

    @Autowired
    private PipeSectionService pipeSectionService;

    @RequestMapping(value = "/add_pipe_section")
    public @ResponseBody String getTime(@RequestParam String length, @RequestParam String outerDiameter,
                                        @RequestParam String thickness) {
        PipeSection pipeSection = new PipeSection();
        pipeSection.setLength(Double.parseDouble(length));
        PipeType pipeType = new PipeType();
        pipeType.setOuterDiameter(Double.parseDouble(outerDiameter));
        pipeType.setThickness(Double.parseDouble(thickness));
        pipeSection.setPipeType(pipeType);
        pipeSectionService.create(pipeSection);
        return "pipe has been added";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {

        return "well";
    }
}