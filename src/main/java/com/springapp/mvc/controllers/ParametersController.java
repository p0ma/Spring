package com.springapp.mvc.controllers;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import system.drilling.model.ParametersModel;
import system.drilling.model.parameters.IParameter;
import system.drilling.model.parameters.Parameter;
import system.drilling.model.well.MyValidationException;
import system.drilling.repositories.exceptions.ParametersModelNotFoundException;
import system.drilling.service.ParametersModelService;

import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/parameters")
public class ParametersController {

    @Autowired
    private ParametersModelService parametersModelService;

    @RequestMapping(value = "/setparam", method = RequestMethod.POST)
    @ResponseBody
    public String setParameter(@RequestParam String name, @RequestParam String val) throws MyValidationException {
        ParametersModel parametersModel;
        parametersModel = parametersModelService.getParametersModel();
        Parameter parameter = parametersModel.getParameter(name);
        parametersModel.setParameterValue(name, Double.parseDouble(val.replace(',', '.')));
        if (parametersModel.isChanged()) {
            try {
                parametersModelService.update(parametersModel);
                parametersModel.setChanged(false);
            } catch (ParametersModelNotFoundException e) {
                e.printStackTrace();
            }
        }
        return "Parameter has been successfully set";
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String numberFormatExceptionHandler(NumberFormatException e) throws IOException {
        return "Invalid number format";
    }

    @ExceptionHandler(TypeMismatchException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String typeMismatchExceptionHandler(TypeMismatchException e) throws NumberFormatException {
        boolean isNumberFormatException = e.getCause() instanceof NumberFormatException;
        if (isNumberFormatException) {
            throw (NumberFormatException) e.getCause();
        }
        return "Invalid type";
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
    public String showParameters(ModelMap model) {
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