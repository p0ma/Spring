package com.springapp.mvc.controllers;

import com.springapp.mvc.media.AjaxDTO;
import com.springapp.mvc.media.ParameterDTO;
import entities.auth.User;
import entities.drilling.model.ParametersModel;
import entities.drilling.model.parameters.CrossComputingException;
import entities.drilling.model.parameters.IParameter;
import entities.drilling.model.parameters.Parameter;
import entities.drilling.model.well.MyValidationException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import repositories.exceptions.ParameterNotFoundException;
import service.ParameterService;
import service.ParametersModelService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/parameters")
public class ParametersController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ParametersModelService parametersModelService;

    @Autowired
    private ParameterService parameterService;

    @RequestMapping(value = "/setparam", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public AjaxDTO setParameter(@AuthenticationPrincipal User user, @RequestBody ParameterDTO parameterDTO) {
        try {
            Parameter parameter = parameterService.setParameterValue(user, parameterDTO);
            return new AjaxDTO("Parameter has been successfully set", parameter.getRoundedValue().toString(), false);
        } catch (MyValidationException e) {
            Double lastValue;
            try {
                lastValue = e.getParameter().getRoundedValue();
            } catch (CrossComputingException e1) {
                lastValue = 0d;
            }
            return new AjaxDTO(e.getMessage(), lastValue.toString(), true);
        } catch (ParameterNotFoundException e) {
            e.printStackTrace();
            return new AjaxDTO("Parameter not found", parameterDTO.getVal(), true);
        } catch (CrossComputingException e) {
            e.printStackTrace();
            return new AjaxDTO("Cross computing exception: parameters schema is invalid", parameterDTO.getVal(), true);
        }
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
    @Transactional(readOnly = true)
    public String showParameters(ModelMap model, @AuthenticationPrincipal User user) {
        ParametersModel parametersModel = parametersModelService.findByUser(user);
        parametersModel.initParameters();
        Map<String, Map<String, IParameter>> parameterMap =
                parametersModel.getParametersByGroups();
        model.addAttribute("parameterMap", parameterMap);
        return "parameters";
    }
}