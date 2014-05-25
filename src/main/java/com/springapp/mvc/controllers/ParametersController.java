package com.springapp.mvc.controllers;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import system.drilling.model.ParametersModel;
import system.drilling.model.parameters.IParameter;
import system.drilling.model.parameters.Parameter;
import system.drilling.model.parameters.actual.parameters.fluid.FluidCriticalVolume;
import system.drilling.model.parameters.actual.parameters.pressure.*;
import system.drilling.model.parameters.actual.parameters.well.ActualWellDepth;
import system.drilling.model.parameters.actual.parameters.well.OuterGirdVolume;
import system.drilling.model.well.MyValidationException;
import system.drilling.repositories.exceptions.ParametersModelNotFoundException;
import system.drilling.service.ParametersModelService;
import system.drilling.service.WellService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/parameters")
public class ParametersController {

    @Autowired
    private ParametersModelService parametersModelService;

    @Autowired
    private WellService wellService;

    @RequestMapping(value = "/setparam")
    @ResponseBody
    public String getTime(@RequestParam String name, @RequestParam String val) throws MyValidationException {
        ParametersModel parametersModel;
            parametersModel = parametersModelService.getParametersModel();
        Parameter parameter = parametersModel.getParameter(name);
        parametersModel.setParameterValue(name, Double.parseDouble(val));
        if (parametersModel.isChanged()) {
            try {
                parametersModelService.update(parametersModel);
                parametersModel.setChanged(false);
            } catch (ParametersModelNotFoundException e) {
                e.printStackTrace();
            }
        }
        return "Parameter '" + parameter.getParameterName() + "' has been set to '" + val + "'";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String exceptionHandler(Exception e) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        StackTraceElement[] stackTraceElements = e.getStackTrace();
        stringBuilder.append(e.getClass().getSimpleName() + e.getMessage() + "<br>");
        for (StackTraceElement stackTraceElement : stackTraceElements) {
            stringBuilder.append(stackTraceElement.toString() + "\n");
        }
        return stringBuilder.toString();
    }

    @ExceptionHandler(MyValidationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String myValidationExceptionHandler(MyValidationException e) throws IOException {
        return e.getMessage();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        ParametersModel parametersModel = parametersModelService.getParametersModel();
        parametersModel.initParameters();
        Map<String, Map<String, IParameter>> parameterMap2 = parametersModel.getParametersByGroups();
        model.addAttribute("parameterMap2", parameterMap2);
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