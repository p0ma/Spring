<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<jsp:include page="jquery.jsp"/>
<html>
<head>
    <title>Well</title>
    <jsp:include page="bootstrap.jsp"/>
    <script type="text/javascript">
        function addPipeSection() {
            var result_panel = $("#result_panel");
            var result = $("#result");
            var length = $("#inputLength").val();
            var outerDiameter = $("#inputOuterDiameter").val();
            var thickness = $("#inputThickness").val();
            var json = {
                "length": length, "outerDiameter": outerDiameter,
                "thickness": thickness
            };
            $.ajax({
                method: 'POST',
                url: "/well/add_pipe_section.json",
                data: JSON.stringify(json),
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Accept", "application/json");
                    xhr.setRequestHeader("Content-Type", "application/json");
                },
                success: function (response) {
                    result.parent().addClass("panel panel-success");
                    result.parent().removeClass("panel panel-danger");
                    result.parent().show();
                    result.html(response);
                    result.show();
                },
                error: function (response) {
                    result.parent().addClass("panel panel-danger");
                    result.parent().removeClass("panel panel-success");
                    result.html(response.responseText);
                    result.parent().show();
                    result.show();
                }
            });
        }
    </script>
</head>
<body>
<jsp:include page="navbar.jsp"></jsp:include>
<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">Adding pipe section</h3>
        </div>
        <div class="panel-body">
            <div class="input-group">
                <form class="form-inline" role="form">
                    <div class="form-group">
                        <label class="sr-only" for="inputLength">Length</label>
                        <input type="text" class="form-control" id="inputLength" placeholder="Enter length">
                        <form:errors path="length"/>
                    </div>
                    <div class="form-group">
                        <label class="sr-only" for="inputOuterDiameter">Outer diameter</label>
                        <input type="text" class="form-control" id="inputOuterDiameter"
                               placeholder="Enter outer diameter">
                        <form:errors path="outerDiameter"/>
                    </div>
                    <div class="form-group">
                        <label class="sr-only" for="inputThickness">Thickness</label>
                        <input type="text" class="form-control" id="inputThickness" placeholder="Enter thickness">
                        <form:errors path="thickness"/>
                    </div>
                    <button type="button" class="btn btn-default"
                            onclick="addPipeSection()">
                        Add pipe section
                    </button>
                </form>
                <div id="result_panel" class="panel panel-danger" hidden="true">
                    <div id="result" class="panel-heading" hidden="true"></div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>