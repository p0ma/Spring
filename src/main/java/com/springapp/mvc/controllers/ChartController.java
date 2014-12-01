package com.springapp.mvc.controllers;

import com.springapp.mvc.media.ChartData;
import com.springapp.mvc.media.ChartInfo;
import com.springapp.mvc.media.ChartLocalization;
import entities.auth.User;
import entities.drilling.model.ParametersModel;
import entities.drilling.model.parameters.actual.parameters.pump.PumpPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import repositories.exceptions.ParametersModelNotFoundException;
import repositories.exceptions.UserNotFoundException;
import service.ParametersModelService;

import java.util.ArrayList;

@Controller
@RequestMapping("/chart")
@PreAuthorize("isAuthenticated()")
public class ChartController {

    @Autowired
    private ParametersModelService parametersModelService;

    @RequestMapping(value = "/getChartData", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    ChartInfo getChartData(@AuthenticationPrincipal User user) throws ParametersModelNotFoundException,
            UserNotFoundException {
        ChartInfo chartInfo = new ChartInfo();
        ParametersModel parametersModel = parametersModelService.findByUser(user);
        parametersModel.initParameters();
        ArrayList<PumpPoint> pointsList = parametersModel.getPoints();
        PumpPoint[] arrayArray = pointsList.toArray(new PumpPoint[pointsList.size()]);
        chartInfo.chartData = new ChartData(arrayArray);
        chartInfo.chartLocalization = new ChartLocalization();
        return chartInfo;

    }

    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(@AuthenticationPrincipal User user) throws
            ParametersModelNotFoundException, UserNotFoundException {
        ParametersModel parametersModel = parametersModelService.findByUser(user);
        parametersModel.initParameters();
        return "parameter/chart";
    }
}