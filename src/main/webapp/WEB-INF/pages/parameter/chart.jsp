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
            var url = "/chart/" + $('#method').text();
            $.ajax({
                method: 'GET',
                url: url,
                headers: {
                    Accept: "application/json; charset=utf-8",
                    "Content-Type": "application/json; charset=utf-8"
                },
                success: function (response) {
                    message = response;
                    var json = response;
                    //var obj = JSON.parse(json.chartData);

                    var header = response.header;
                    var pointFormat = response.pointFormat;
                    var chartData = response.chartData.chartData;
                    var chartLocalization = response.chartLocalization;
                    var len = chartData.length;
                    var editedChartData = [];
                    for (var i = 0; i < chartData.length; i++) {
                        editedChartData[i] = [chartData[i].x, chartData[i].y];
                    }

                    drawChart(editedChartData, chartLocalization, header, pointFormat);
                },
                error: function (response) {
                    message = response.responseText;
                }
            })
        });
        function drawChart(chartData, chartLocalization, header, pointFormat) {
            $('#container').highcharts({
                chart: {
                    type: 'area'
                },
                title: {
                    text: header
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
                        text: $('#y').text()
                    },
                    labels: {
                        formatter: function () {
                            return this.value + ' ' + $('#pressureUnit').text();
                        }
                    }
                },
                tooltip: {
                    headerFormat: '',
                    pointFormat: pointFormat
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
    </script>
</head>

<body>
<jsp:include page="../basic/navbar.jsp"/>
<div class="container">
    <div id="json1">

    </div>

    <div id="container">

    </div>

    <div id="y" hidden="hidden"><spring:message code="chart.y"/></div>
    <div id="pressureUnit" hidden="hidden"><spring:message code="chart.y.unit"/></div>
    <div id="turns" hidden="hidden"><spring:message code="chart.x"/></div>
    <div id="turnsUnit" hidden="hidden"><spring:message code="chart.x.unit"/></div>
    <div id="method" hidden="hidden">${method}</div>

</div>
</body>
</html>
