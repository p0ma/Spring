package com.springapp.mvc.controllers;

import com.springapp.mvc.media.AjaxDTO;
import com.springapp.mvc.media.ParameterDTO;
import entities.auth.User;
import entities.drilling.model.ParametersModel;
import entities.drilling.model.parameters.CrossComputingException;
import entities.drilling.model.parameters.IParameter;
import entities.drilling.model.parameters.Parameter;
import entities.drilling.model.well.MyValidationException;
import entities.drilling.model.well.PipeSection;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
import service.PipeSectionService;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("/parameters")
public class ParametersController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private PipeSectionService pipeSectionService;

    @Autowired
    private ParametersModelService parametersModelService;

    @Autowired
    private ParameterService parameterService;

    @RequestMapping(value = "/setparam", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public AjaxDTO setParameter(@AuthenticationPrincipal User user, @RequestBody ParameterDTO parameterDTO,
                                Locale locale) {
        try {
            Parameter parameter = parameterService.setParameterValue(user, parameterDTO);
            return new AjaxDTO(messageSource.getMessage("parameter.has.been.successfully.set", null, locale),
                    parameter.getRoundedValue().toString(), false);
        } catch (MyValidationException e) {
            try {
                return new AjaxDTO(e.getMessage(), e.getParameter().getStringRoundedValue(), true);
            } catch (CrossComputingException e1) {
                e1.printStackTrace();
                return new AjaxDTO(messageSource.getMessage("cross.computing.error.message", null, locale),
                        parameterDTO.getVal(), true);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return new AjaxDTO(messageSource.getMessage("bad.number.format", null, locale), parameterDTO.getVal(),
                    true);
        } catch (ParameterNotFoundException e) {
            e.printStackTrace();
            return new AjaxDTO(messageSource.getMessage("parameter.not.found", null, locale), parameterDTO.getVal(),
                    true);
        } catch (CrossComputingException e) {
            e.printStackTrace();
            return new AjaxDTO(messageSource.getMessage("cross.computing.error.message", null, locale),
                    parameterDTO.getVal(), true);
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
        parametersModel.getWorkingDataSet().getWell().getLength();
        List<PipeSection> pipeSections = pipeSectionService.getPipeSections(user);
        parametersModel.initParameters();
        Map<String, Map<String, IParameter>> parameterMap =
                parametersModel.getParametersByGroups();
        model.addAttribute("parameterMap", parameterMap);
        return "parameters";
    }
}