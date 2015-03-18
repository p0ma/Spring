package com.springapp.mvc.controllers;

import com.springapp.mvc.media.AjaxWithLastValDTO;
import com.springapp.mvc.media.ParameterDTO;
import entities.auth.User;
import entities.drilling.model.ParametersModel;
import entities.drilling.parameters.CrossComputingException;
import entities.drilling.parameters.Parameter;
import entities.drilling.well.MyValidationException;
import localization.LocalizationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import repositories.exceptions.ParametersModelNotFoundException;
import repositories.exceptions.UserNotFoundException;
import service.ParameterService;
import service.ParametersModelService;

import java.util.Map;

@Controller
@RequestMapping("/parameters")
@PreAuthorize("isAuthenticated()")
public class ParametersController {
    @Autowired
    private ParametersModelService parametersModelService;

    @Autowired
    private ParameterService parameterService;

    @RequestMapping(value = "/setparam", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public AjaxWithLastValDTO setParameter(@AuthenticationPrincipal User user, @RequestBody ParameterDTO parameterDTO) {
        try {
            parameterService.setParameterValue(user, parameterDTO);
            return new AjaxWithLastValDTO(LocalizationUtils.getMessage("parameter.has.been.successfully.set"),
                    parameterDTO.getVal(), false);
        } catch (MyValidationException e) {
            try {
                return new AjaxWithLastValDTO(e.getMessage(), e.getParameter().getStringRoundedValue(), true);
            } catch (CrossComputingException e1) {
                e1.printStackTrace();
                return new AjaxWithLastValDTO(e1.getMessage(),
                        parameterDTO.getVal(), true);
            }
        } catch (NumberFormatException e) {
            return new AjaxWithLastValDTO(LocalizationUtils.getMessage("WrongNumberFormat.message"),
                    parameterDTO.getVal(), true);
        } catch (Exception e) {
            return new AjaxWithLastValDTO(e.getMessage(), parameterDTO.getVal(), true);
        }
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String showAllParameters(ModelMap model, @AuthenticationPrincipal User user) {
        ParametersModel parametersModel = null;
        try {
            parametersModel = parametersModelService.findByUser(user);
            Map<String, Map<String, Parameter>> parameterMap =
                    parametersModel.getParametersByGroups();
            model.addAttribute("parameterMap", parameterMap);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        } catch (ParametersModelNotFoundException e) {
            e.printStackTrace();
        }
        return "parameter/parameters";
    }

    @RequestMapping(value = "/input", method = RequestMethod.GET)
    public String showInputParameters(ModelMap model, @AuthenticationPrincipal User user) {
        ParametersModel parametersModel = null;
        try {
            parametersModel = parametersModelService.findByUser(user);
            Map<String, Map<String, Parameter>> parameterMap =
                    parametersModel.getInputParametersByGroups();
            model.addAttribute("parameterMap", parameterMap);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        } catch (ParametersModelNotFoundException e) {
            e.printStackTrace();
        }
        return "parameter/parameters";
    }
}