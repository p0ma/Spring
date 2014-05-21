package com.springapp.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import system.drilling.model.ParametersModel;
import system.drilling.model.parameters.actual.parameters.pressure.*;
import system.drilling.model.parameters.actual.parameters.well.ActualWellDepth;
import system.drilling.repositories.exceptions.ParametersModelNotFoundException;
import system.drilling.service.ParametersModelService;
import system.drilling.service.WellService;

@Controller
@RequestMapping("/parameters")
public class ParametersController {

    @Autowired
    private ParametersModelService parametersModelService;

    @Autowired
    private WellService wellService;

    @RequestMapping(value = "/setparam")
    public @ResponseBody String getTime(@RequestParam String name, @RequestParam String val) {
        ParametersModel parametersModel = parametersModelService.getParametersModel();
        parametersModel.setParameterValue(name, Double.parseDouble(val));
        if (parametersModel.isChanged()) {
            try {
                parametersModelService.update(parametersModel);
                parametersModel.setChanged(false);
            } catch (ParametersModelNotFoundException e) {
                e.printStackTrace();
            }
        }
        return "name = " + name + ", val = " + val;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        ParametersModel parametersModel = parametersModelService.getParametersModel();
        model.addAttribute(DrillPipeInnerPressure.class.getSimpleName(), parametersModel.getParameterValue(DrillPipeInnerPressure.class).toString());
        model.addAttribute(DrillPipeOuterPressure.class.getSimpleName(), parametersModel.getParameterValue(DrillPipeOuterPressure.class).toString());
        model.addAttribute(MudPumpingPressure.class.getSimpleName(), parametersModel.getParameterValue(MudPumpingPressure.class).toString());
        model.addAttribute(MudPumpingPressureLoss.class.getSimpleName(), parametersModel.getParameterValue(MudPumpingPressureLoss.class).toString());
        model.addAttribute(ActualWellDepth.class.getSimpleName(), parametersModel.getParameterValue(ActualWellDepth.class).toString());
        if (parametersModel.isChanged()) {
            try {
                parametersModelService.update(parametersModel);
                parametersModel.setChanged(false);
            } catch (ParametersModelNotFoundException e) {
                e.printStackTrace();
            }
        }
        return "parameters";
    }
}