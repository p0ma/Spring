package com.springapp.mvc.controllers;

import com.springapp.mvc.media.AjaxWithLastValDTO;
import com.springapp.mvc.media.ParameterDTO;
import entities.auth.User;
import entities.drilling.model.ParametersModel;
import entities.drilling.model.parameters.CrossComputingException;
import entities.drilling.model.parameters.IParameter;
import entities.drilling.model.well.MyValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
import service.PipeSectionService;

import java.util.Locale;
import java.util.Map;

@Controller
@PreAuthorize("isAuthenticated()")
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
    public AjaxWithLastValDTO setParameter(@AuthenticationPrincipal User user, @RequestBody ParameterDTO parameterDTO,
                                           Locale locale) {
        try {
            parameterService.setParameterValue(user, parameterDTO);
            return new AjaxWithLastValDTO(messageSource.getMessage("parameter.has.been.successfully.set", null, locale),
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
            return new AjaxWithLastValDTO("Wrong number format", parameterDTO.getVal(), true);
        } catch (Exception e) {
            return new AjaxWithLastValDTO(e.getMessage(), parameterDTO.getVal(), true);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showParameters(ModelMap model, @AuthenticationPrincipal User user, Locale locale) {
        ParametersModel parametersModel = null;
        try {
            parametersModel = parametersModelService.findByUser(user);
            Map<String, Map<String, IParameter>> parameterMap =
                    parametersModel.getParametersByGroups();
            model.addAttribute("parameterMap", parameterMap);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        } catch (ParametersModelNotFoundException e) {
            e.printStackTrace();
        }
        return "parameters";
    }
}