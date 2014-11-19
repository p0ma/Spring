package com.springapp.mvc.controllers;

import com.springapp.mvc.media.ChartData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import system.auth.User;
import system.drilling.model.ParametersModel;
import system.drilling.model.parameters.actual.parameters.pump.PumpPoint;
import system.drilling.model.well.MyValidationException;
import system.drilling.repositories.exceptions.ParametersModelNotFoundException;
import system.drilling.service.ParametersModelService;

import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequestMapping("/chart")
public class ChartController {

    @Autowired
    private ParametersModelService parametersModelService;

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

    @RequestMapping(value = "/getChartData", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    ChartData getChartData(@AuthenticationPrincipal User user) {
        ParametersModel parametersModel = user.getWorkingDataSet().getParametersModel();
        parametersModel.initParameters();
        ArrayList<PumpPoint> pointsList = parametersModel.getPoints();
        PumpPoint[] arrayArray = pointsList.toArray(new PumpPoint[pointsList.size()]);
        return new ChartData(arrayArray);

    }

    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(@AuthenticationPrincipal User user, ModelMap model) {
        ParametersModel parametersModel = user.getWorkingDataSet().getParametersModel();
        parametersModel.initParameters();
        if (parametersModel.isChanged()) {
            try {
                parametersModelService.update(parametersModel);
                parametersModel.setChanged(false);
            } catch (ParametersModelNotFoundException e) {
                e.printStackTrace();
            }
        }
        return "chart";
    }
}