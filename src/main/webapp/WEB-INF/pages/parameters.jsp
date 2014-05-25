<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="jquery.jsp"/>
<html>
<head>
    <title>Parameters</title>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    <jsp:include page="bootstrap.jsp"/>
    <script type="text/javascript">
        function setParameterValue(name, val) {
            var result_panel = $("#result_panel");
            var result = $("#result");
            $.ajax({
                url: "/parameters/setparam.html",
                data: ({name: name, val: val}),
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
                    result.parent().show();
                    result.html(response.responseText);
                    result.show();
                }
            });
        }
    </script>

</head>
<body>
<jsp:include page="navbar.jsp"></jsp:include>
<c:forEach items="${parameterMap2}" var="group">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">${group.key}</h3>
        </div>
        <div class="panel-body">
            <c:forEach items="${group.value}" var="parameter">
                <div class="col-lg-6">
                    <div class="input-group">
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button"
                                    onclick="setParameterValue(
                                            '${parameter.value.getClass().getSimpleName()}', document.getElementById('${parameter.value.getClass().getSimpleName()}').value)">
                                    ${parameter.value.parameterName}
                            </button>
                        </span>
                        <input id="${parameter.value.getClass().getSimpleName()}" type="text" class="form-control"
                               value="${parameter.value.value}">
                    </div>
                </div>

            </c:forEach>
        </div>
    </div>

</c:forEach>

<div id="result_panel" class="panel panel-danger" hidden="true">
    <div id="result" class="panel-heading" hidden="true"></div>
</div>

</body>
</html>