package com.springapp.mvc.media;

import localization.LocalizationUtils;

/**
 * Created by Damager1 on 30.11.2014.
 */
public class ChartLocalization {
    String loading;
    String[] months;
    String[] weekdays;
    String[] shortMonth;
    String exportButtonTitle;
    String printButtonTitle;
    String rangeSelectorFrom;
    String rangeSelectorTo;
    String rangeSelectorZoom;
    String printChart;
    String downloadPNG;
    String downloadJPEG;
    String downloadPDF;
    String downloadSVG;
    String resetZoom;
    String resetZoomTitle;
    String thousandsSep;
    String decimalPoint;

    public ChartLocalization() {
        loading = LocalizationUtils.getMessage("chart.loading");
        months = LocalizationUtils.getMonths();
        weekdays = LocalizationUtils.getWeekdays();
        shortMonth = LocalizationUtils.getShortMonths();
        exportButtonTitle = LocalizationUtils.getMessage("chart.exportButtonTitle");
        printButtonTitle = LocalizationUtils.getMessage("chart.printButtonTitle");
        rangeSelectorFrom = LocalizationUtils.getMessage("chart.rangeSelectorFrom");
        rangeSelectorTo = LocalizationUtils.getMessage("chart.rangeSelectorTo");
        rangeSelectorZoom = LocalizationUtils.getMessage("chart.rangeSelectorZoom");
        printChart = LocalizationUtils.getMessage("chart.printChart");
        downloadPNG = LocalizationUtils.getMessage("chart.downloadPNG");
        downloadJPEG = LocalizationUtils.getMessage("chart.downloadJPEG");
        downloadPDF = LocalizationUtils.getMessage("chart.downloadPDF");
        downloadSVG = LocalizationUtils.getMessage("chart.downloadSVG");
        resetZoom = LocalizationUtils.getMessage("chart.resetZoom");
        resetZoomTitle = LocalizationUtils.getMessage("chart.resetZoomTitle");
        thousandsSep = LocalizationUtils.getMessage("chart.thousandsSep");
        decimalPoint = LocalizationUtils.getMessage("chart.decimalPoint");
    }

    public ChartLocalization(String loading, String[] months, String[] weekdays, String[] shortMonth, String exportButtonTitle, String printButtonTitle, String rangeSelectorFrom, String rangeSelectorTo, String rangeSelectorZoom, String printChart, String downloadPNG, String downloadJPEG, String downloadPDF, String downloadSVG, String resetZoom, String resetZoomTitle, String thousandsSep, String decimalPoint) {
        this.loading = loading;
        this.months = months;
        this.weekdays = weekdays;
        this.shortMonth = shortMonth;
        this.exportButtonTitle = exportButtonTitle;
        this.printButtonTitle = printButtonTitle;
        this.rangeSelectorFrom = rangeSelectorFrom;
        this.rangeSelectorTo = rangeSelectorTo;
        this.rangeSelectorZoom = rangeSelectorZoom;
        this.printChart = printChart;
        this.downloadPNG = downloadPNG;
        this.downloadJPEG = downloadJPEG;
        this.downloadPDF = downloadPDF;
        this.downloadSVG = downloadSVG;
        this.resetZoom = resetZoom;
        this.resetZoomTitle = resetZoomTitle;
        this.thousandsSep = thousandsSep;
        this.decimalPoint = decimalPoint;
    }

    public String getLoading() {
        return loading;
    }

    public void setLoading(String loading) {
        this.loading = loading;
    }

    public String[] getMonths() {
        return months;
    }

    public void setMonths(String[] months) {
        this.months = months;
    }

    public String[] getWeekdays() {
        return weekdays;
    }

    public void setWeekdays(String[] weekdays) {
        this.weekdays = weekdays;
    }

    public String[] getShortMonth() {
        return shortMonth;
    }

    public void setShortMonth(String[] shortMonth) {
        this.shortMonth = shortMonth;
    }

    public String getExportButtonTitle() {
        return exportButtonTitle;
    }

    public void setExportButtonTitle(String exportButtonTitle) {
        this.exportButtonTitle = exportButtonTitle;
    }

    public String getPrintButtonTitle() {
        return printButtonTitle;
    }

    public void setPrintButtonTitle(String printButtonTitle) {
        this.printButtonTitle = printButtonTitle;
    }

    public String getRangeSelectorFrom() {
        return rangeSelectorFrom;
    }

    public void setRangeSelectorFrom(String rangeSelectorFrom) {
        this.rangeSelectorFrom = rangeSelectorFrom;
    }

    public String getRangeSelectorTo() {
        return rangeSelectorTo;
    }

    public void setRangeSelectorTo(String rangeSelectorTo) {
        this.rangeSelectorTo = rangeSelectorTo;
    }

    public String getRangeSelectorZoom() {
        return rangeSelectorZoom;
    }

    public void setRangeSelectorZoom(String rangeSelectorZoom) {
        this.rangeSelectorZoom = rangeSelectorZoom;
    }

    public String getPrintChart() {
        return printChart;
    }

    public void setPrintChart(String printChart) {
        this.printChart = printChart;
    }

    public String getDownloadPNG() {
        return downloadPNG;
    }

    public void setDownloadPNG(String downloadPNG) {
        this.downloadPNG = downloadPNG;
    }

    public String getDownloadJPEG() {
        return downloadJPEG;
    }

    public void setDownloadJPEG(String downloadJPEG) {
        this.downloadJPEG = downloadJPEG;
    }

    public String getDownloadPDF() {
        return downloadPDF;
    }

    public void setDownloadPDF(String downloadPDF) {
        this.downloadPDF = downloadPDF;
    }

    public String getDownloadSVG() {
        return downloadSVG;
    }

    public void setDownloadSVG(String downloadSVG) {
        this.downloadSVG = downloadSVG;
    }

    public String getResetZoom() {
        return resetZoom;
    }

    public void setResetZoom(String resetZoom) {
        this.resetZoom = resetZoom;
    }

    public String getResetZoomTitle() {
        return resetZoomTitle;
    }

    public void setResetZoomTitle(String resetZoomTitle) {
        this.resetZoomTitle = resetZoomTitle;
    }

    public String getThousandsSep() {
        return thousandsSep;
    }

    public void setThousandsSep(String thousandsSep) {
        this.thousandsSep = thousandsSep;
    }

    public String getDecimalPoint() {
        return decimalPoint;
    }

    public void setDecimalPoint(String decimalPoint) {
        this.decimalPoint = decimalPoint;
    }
}
