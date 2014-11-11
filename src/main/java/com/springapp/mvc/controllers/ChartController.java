package com.springapp.mvc.controllers;

import com.google.visualization.datasource.base.TypeMismatchException;
import com.google.visualization.datasource.datatable.ColumnDescription;
import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.datatable.value.ValueType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import system.drilling.model.DPoint;
import system.drilling.model.ParametersModel;
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

    @RequestMapping(value = "/getChartData", method = RequestMethod.GET)
    @ResponseBody
    public DataTable getChartData() {
        ParametersModel parametersModel = parametersModelService.getParametersModel();
        DataTable data = new DataTable();
        ArrayList cd = new ArrayList();
        cd.add(new ColumnDescription("name", ValueType.TEXT, "Animal name"));
        cd.add(new ColumnDescription("link", ValueType.TEXT, "Link to wikipedia"));
        cd.add(new ColumnDescription("population", ValueType.NUMBER, "Population size"));
        cd.add(new ColumnDescription("vegeterian", ValueType.BOOLEAN, "Vegetarian?"));

        data.addColumns(cd);

        // Fill the data table.
        try {
            data.addRowFromValues("Aye-aye", "http://en.wikipedia.org/wiki/Aye-aye", 100, true);
            data.addRowFromValues("Sloth", "http://en.wikipedia.org/wiki/Sloth", 300, true);
            data.addRowFromValues("Leopard", "http://en.wikipedia.org/wiki/Leopard", 50, false);
            data.addRowFromValues("Tiger", "http://en.wikipedia.org/wiki/Tiger", 80, false);
        } catch (TypeMismatchException e) {
            System.out.println("Invalid type!");
        }
        return data;

    }

    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        ParametersModel parametersModel = parametersModelService.getParametersModel();
        parametersModel.initParameters();
        ArrayList<DPoint> points = parametersModel.getPoints();
        model.addAttribute("points", points);
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