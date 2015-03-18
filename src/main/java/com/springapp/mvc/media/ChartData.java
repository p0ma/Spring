package com.springapp.mvc.media;

import entities.drilling.chart.DPoint;

/**
 * Created with IntelliJ IDEA.
 * User: Damager1
 * Date: 14.11.14
 * Time: 5:09
 * To change this template use File | Settings | File Templates.
 */
public class ChartData {

    private DPoint[] chartData;

    public ChartData(DPoint[] chartData) {
        this.chartData = chartData;
    }

    public DPoint[] getChartData() {
        return chartData;
    }

    public void setChartData(DPoint[] chartData) {
        this.chartData = chartData;
    }

}
