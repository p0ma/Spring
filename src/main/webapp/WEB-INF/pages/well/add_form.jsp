<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="pipeSection.add"/></title>
    <jsp:include page="../basic/bootstrap.jsp"/>
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
                url: "/well/add.json",
                data: JSON.stringify(json),
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Accept", "application/json");
                    xhr.setRequestHeader("Content-Type", "application/json");
                },
                success: function (response) {
                    var message = response.message;
                    var hasError = response.hasError;
                    if (hasError == true) {
                        result.parent().addClass("panel panel-success");
                        result.parent().removeClass("panel panel-danger");
                        result.html(message);
                        result.parent().show();
                        result.show();
                    } else {
                        result.parent().addClass("panel panel-danger");
                        result.parent().removeClass("panel panel-success");
                        result.html(message);
                        result.parent().show();
                        result.show();
                    }
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
<jsp:include page="../basic/navbar.jsp"/>
<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title"><spring:message code="pipeSection.add"/></h3>
        </div>
        <div class="panel-body">
            <div class="input-group">
                <form class="form-inline" role="form">
                    <div class="form-group">
                        <spring:message code="pipeSection.length" var="pipeSectionLength"/>
                        <label class="sr-only" for="inputLength"><spring:message code="pipeSection.length"/></label>
                        <input type="text" class="form-control" id="inputLength"
                               placeholder="${pipeSectionLength}">
                        <form:errors path="length"/>
                    </div>
                    <div class="form-group">
                        <spring:message code="pipeSection.outerDiameter" var="pipeSectionOuterDiameter"/>
                        <label class="sr-only" for="inputOuterDiameter">
                            <spring:message code="pipeSection.outerDiameter"/></label>
                        <input type="text" class="form-control" id="inputOuterDiameter"
                               placeholder="${pipeSectionOuterDiameter}">
                        <form:errors path="outerDiameter"/>
                    </div>
                    <div class="form-group">
                        <spring:message code="pipeSection.thickness" var="pipeSectionThickness"/>
                        <label class="sr-only" for="inputThickness">
                            <spring:message code="pipeSection.thickness"/></label>
                        <input type="text" class="form-control" id="inputThickness"
                               placeholder="${pipeSectionThickness}">
                        <form:errors path="thickness"/>
                    </div>
                    <button type="button" class="btn btn-default"
                            onclick="addPipeSection()">
                        <spring:message code="pipeSection.add"/>
                    </button>
                </form>
                <div id="result_panel" class="panel panel-danger" hidden="hidden">
                    <div id="result" class="panel-heading" hidden="hidden"></div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>