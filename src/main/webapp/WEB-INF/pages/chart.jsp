<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>

    <jsp:include page="jquery.jsp"/>
    <jsp:include page="bootstrap.jsp"/>
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

                    var chartData = response.chartData;
                    var len = chartData.length;
                    var editedChartData = [];
                    for (var i = 0; i < chartData.length; i++) {
                        editedChartData[i] = [chartData[i].turn, chartData[i].pressure];
                    }

                    drawChart(editedChartData);
                },
                error: function (response) {
                    message = response.responseText;
                }
            })
        })
        function drawChart(chartData) {
            $('#container').highcharts({
                chart: {
                    type: 'area'
                },
                title: {
                    text: "Pressure loss"
                },
                subtitle: {
                    text: ''
                },
                xAxis: {
                    allowDecimals: true,
                    labels: {
                        formatter: function () {
                            return this.value + ' turns'; // clean, unformatted number for year
                        }
                    }
                },
                yAxis: {
                    title: {
                        text: 'Pressure'
                    },
                    labels: {
                        formatter: function () {
                            return this.value + ' bar';
                        }
                    }
                },
                tooltip: {
                    headerFormat: '',
                    pointFormat: 'After <b>{point.x} {series.name}</b> pressure will be <b>{point.y:,.2f} bar</b>'
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
                series: [
                    {
                        name: 'Turns',
                        data: chartData
                    }
                ]
            });
        }
        ;


    </script>
</head>

<body>
<jsp:include page="navbar.jsp"/>
<div class="container">
    <div id="json1">

    </div>

    <div id="container">

    </div>
</div>

</body>
</html>
