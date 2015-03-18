package com.springapp.mvc.controllers;

import com.springapp.mvc.media.ChartInfo;
import entities.auth.User;
import entities.drilling.chart.BorerMethodChartBuilder;
import entities.drilling.chart.ChartInfoBuilderImpl;
import entities.drilling.chart.WaitingAndWeightingChartBuilder;
import entities.drilling.model.ParametersModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import repositories.exceptions.ParametersModelNotFoundException;
import repositories.exceptions.UserNotFoundException;
import service.ParametersModelService;

@Controller
@RequestMapping("/chart")
@PreAuthorize("isAuthenticated()")
public class ChartController {
    @Autowired
    private ParametersModelService parametersModelService;

    @RequestMapping(value = "/waitingAndWeighting1", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    ChartInfo waitingAndWeighting(@AuthenticationPrincipal User user) throws ParametersModelNotFoundException,
            UserNotFoundException {
        ParametersModel parametersModel = parametersModelService.findByUser(user);
        parametersModel.initParameters();
        return new ChartInfoBuilderImpl().build(new WaitingAndWeightingChartBuilder(parametersModel).getChart());
    }

    @RequestMapping(value = "/borerMethod1", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    ChartInfo borerMethod(@AuthenticationPrincipal User user) throws ParametersModelNotFoundException,
            UserNotFoundException {
        ParametersModel parametersModel = parametersModelService.findByUser(user);
        parametersModel.initParameters();
        return new ChartInfoBuilderImpl().build(new BorerMethodChartBuilder(parametersModel).getChart());
    }

    @RequestMapping(value = "/{method}", method = RequestMethod.GET)
    public String printWelcome(@AuthenticationPrincipal User user, @PathVariable String method, Model model) throws
            ParametersModelNotFoundException, UserNotFoundException {
        ParametersModel parametersModel = parametersModelService.findByUser(user);
        parametersModel.initParameters();
        model.addAttribute("method", method + "1");
        return "parameter/chart";
    }
}