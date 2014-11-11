<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="jquery.jsp"/>
<html>
<head>
    <title>Chart</title>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    <jsp:include page="bootstrap.jsp"/>
    <html>
    <head>


        <script type="text/javascript" src="https://www.google.com/jsapi"></script>
        <script type="text/javascript">
            var chartData; // globar variable for hold chart data
            google.load("visualization", "1", { packages: ["corechart"] });

            // Here We will fill chartData

            $(document).ready(function () {

                $.ajax({
                    url: "${pageContext.request.contextPath}/chart/getChartData",
                    data: "",
                    dataType: "json",
                    type: "GET",
                    contentType: "application/json; chartset=utf-8",
                    success: function (data) {
                        chartData = data;
                    },
                    error: function () {
                        alert("Error loading data! Please try again.");
                    }
                }).done(function () {
                            // after complete loading data
                            google.setOnLoadCallback(drawChart);
                            drawChart();
                        });
            });

            google.load("visualization", "1", {packages: ["corechart"]});
            google.setOnLoadCallback(drawChart);
            function drawChart() {
                var data = google.visualization.DataTable(chartData);

                var options = {
                    title: 'Company Performance'
                };

                var chart = new google.visualization.LineChart(document.getElementById('chart_div'));

                chart.draw(data, options);
            }
        </script>
    </head>
    <body>
    <jsp:include page="navbar.jsp"/>
    <div id="chart_div" style="width: 900px; height: 500px;"></div>
    </body>
    </html>
