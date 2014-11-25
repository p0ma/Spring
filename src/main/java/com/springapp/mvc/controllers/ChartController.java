package com.springapp.mvc.controllers;

import com.springapp.mvc.media.ChartData;
import entities.auth.User;
import entities.drilling.model.ParametersModel;
import entities.drilling.model.parameters.actual.parameters.pump.PumpPoint;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ChartController {

    @Autowired
    private ParametersModelService parametersModelService;

    @RequestMapping(value = "/getChartData", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    ChartData getChartData(@AuthenticationPrincipal User user) throws ParametersModelNotFoundException,
            UserNotFoundException {
        ParametersModel parametersModel = parametersModelService.findByUser(user);
        parametersModel.initParameters();
        ArrayList<PumpPoint> pointsList = parametersModel.getPoints();
        PumpPoint[] arrayArray = pointsList.toArray(new PumpPoint[pointsList.size()]);
        return new ChartData(arrayArray);

    }

    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(@AuthenticationPrincipal User user) throws
            ParametersModelNotFoundException, UserNotFoundException {
        ParametersModel parametersModel = parametersModelService.findByUser(user);
        parametersModel.initParameters();
        return "chart";
    }
}