package com.springapp.mvc.media;

import entities.drilling.model.parameters.actual.parameters.pump.PumpPoint;

/**
 * Created with IntelliJ IDEA.
 * User: Damager1
 * Date: 14.11.14
 * Time: 5:09
 * To change this template use File | Settings | File Templates.
 */
public class ChartData {

    private PumpPoint[] chartData;

    public ChartData(PumpPoint[] chartData) {
        this.chartData = chartData;
    }

    public PumpPoint[] getChartData() {
        return chartData;
    }

    public void setChartData(PumpPoint[] chartData) {
        this.chartData = chartData;
    }

}
