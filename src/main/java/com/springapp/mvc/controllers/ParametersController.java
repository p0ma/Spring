package com.springapp.mvc.controllers;

import com.springapp.mvc.media.AjaxDTO;
import com.springapp.mvc.media.ParameterDTO;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import system.auth.User;
import system.drilling.model.ParametersModel;
import system.drilling.model.parameters.CrossComputingException;
import system.drilling.model.parameters.IParameter;
import system.drilling.model.parameters.Parameter;
import system.drilling.model.well.MyValidationException;
import system.drilling.repositories.exceptions.ParameterNotFoundException;
import system.drilling.repositories.exceptions.ParametersModelNotFoundException;
import system.drilling.service.ParameterService;
import system.drilling.service.ParametersModelService;

import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/parameters")
public class ParametersController {

    @Autowired
    private ParametersModelService parametersModelService;

    @Autowired
    private ParameterService parameterService;

    @RequestMapping(value = "/setparam", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public AjaxDTO setParameter(@AuthenticationPrincipal User user, @RequestBody ParameterDTO parameterDTO) {
        ParametersModel parametersModel;
        parametersModel = user.getWorkingDataSet().getParametersModel();
        parametersModel.initParameters();
        Parameter parameter = parametersModel.getParameter(parameterDTO.getName());
        Double value = Double.parseDouble(parameterDTO.getVal().replace(',', '.'));
        try {
            parameter.setParameterValue(value);
            parameterService.update(parameter);
            //parametersModel.setParameterValue(parameterDTO.getName(), value);
        } catch (MyValidationException e) {
            Double lastValue;
            try {
                lastValue = parameter.getRoundedValue();
            } catch (CrossComputingException e1) {
                lastValue = 0d;
            }
            return new AjaxDTO(e.getMessage(), lastValue.toString(), true);
        } catch (ParameterNotFoundException e) {
            e.printStackTrace();
        }
        return new AjaxDTO("Parameter has been successfully set", value.toString(), false);
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public AjaxDTO numberFormatExceptionHandler(NumberFormatException e) throws IOException {
        return new AjaxDTO("Wrong number format", "0,00", true);
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

    @RequestMapping(method = RequestMethod.GET)
    public String showParameters(ModelMap model, @AuthenticationPrincipal User user) {
        ParametersModel parametersModel = user.getWorkingDataSet().getParametersModel();
        parametersModel.initParameters();
        Map<String, Map<String, IParameter>> parameterMap2 = parametersModel.getParametersByGroups();
        model.addAttribute("parameterMap2", parameterMap2);
        if (parametersModel.isChanged()) {
            try {
                parametersModel.prepareForSaving();
                parametersModelService.update(parametersModel);
                parametersModel.setChanged(false);
            } catch (ParametersModelNotFoundException e) {
                e.printStackTrace();
            }
        }
        return "parameters";
    }
}