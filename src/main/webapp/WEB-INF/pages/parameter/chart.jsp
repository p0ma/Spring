<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>

    <jsp:include page="../basic/jquery.jsp"/>
    <jsp:include page="../basic/bootstrap.jsp"/>
    <script type="text/javascript" src="http://code.highcharts.com/highcharts.js"></script>
    <script type="text/javascript" src="http://code.highcharts.com/highcharts-more.js"></script>
    <script type="text/javascript" src="http://code.highcharts.com/modules/exporting.js"></script>
    <script type="text/javascript" src="http://code.highcharts.com/adapters/mootools-adapter.js"></script>
    <script type="text/javascript" src="http://code.highcharts.com/adapters/prototype-adapter.js"></script>
    <script type="text/javascript" src="http://www.technicalkeeda.com/js/javascripts/plugin/json2.js"></script>


    <script type="text/javascript">
        $(document).ready(function () {
            var message;
            $.ajax({
                method: 'GET',
                url: "/chart/getChartData",
                headers: {
                    Accept: "application/json; charset=utf-8",
                    "Content-Type": "application/json; charset=utf-8"
                },
                success: function (response) {
                    message = response;
                    var json = response;
                    //var obj = JSON.parse(json.chartData);

                    var chartData = response.chartData.chartData;
                    var chartLocalization = response.chartLocalization;
                    var len = chartData.length;
                    var editedChartData = [];
                    for (var i = 0; i < chartData.length; i++) {
                        editedChartData[i] = [chartData[i].turn, chartData[i].pressure];
                    }

                    drawChart(editedChartData, chartLocalization);
                },
                error: function (response) {
                    message = response.responseText;
                }
            })
        })
        function drawChart(chartData, chartLocalization) {
            $('#container').highcharts({
                chart: {
                    type: 'area'
                },
                title: {
                    text: $('#chartHeader').text()
                },
                subtitle: {
                    text: ''
                },
                xAxis: {
                    allowDecimals: true,
                    labels: {
                        formatter: function () {
                            return this.value + ' ' + $('#turnsUnit').text(); // clean, unformatted number for year
                        }
                    }
                },
                yAxis: {
                    title: {
                        text: $('#pressure').text()
                    },
                    labels: {
                        formatter: function () {
                            return this.value + ' ' + $('#pressureUnit').text();
                        }
                    }
                },
                tooltip: {
                    headerFormat: '',
                    pointFormat: $('#pointFormat').text()
                },
                plotOptions: {
                    area: {
                        pointStart: 0,
                        marker: {
                            enabled: false,
                            symbol: 'circle',
                            radius: 2,
                            states: {
                                hover: {
                                    enabled: true
                                }
                            }
                        }
                    }
                },
                lang: chartLocalization,
                credits: {
                    enabled: false
                },
                series: [
                    {
                        name: $('#turns').text(),
                        data: chartData
                    }
                ]
            });
        }
        ;


    </script>
</head>

<body>
<jsp:include page="../basic/navbar.jsp"/>
<div class="container">
    <div id="json1">

    </div>

    <div id="container">

    </div>

    <div id="pressure" hidden="hidden"><spring:message code="chart.pressure"/></div>
    <div id="pressureUnit" hidden="hidden"><spring:message code="chart.pressure.unit"/></div>
    <div id="turns" hidden="hidden"><spring:message code="chart.turns"/></div>
    <div id="turnsUnit" hidden="hidden"><spring:message code="chart.turns.unit"/></div>
    <div id="pointFormat" hidden="hidden"><spring:message code="chart.pointFormat"/></div>
    <div id="chartHeader" hidden="hidden"><spring:message code="chart.header"/></div>

</div>

</body>
</html>
