<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <jsp:include page="bootstrap.jsp"/>
    <title>Parameters</title>
    <script type="text/javascript">
        $(document).ready(function () {
            $("body").tooltip({ selector: '[data-toggle=tooltip]' });
        });
        function setParameterValue(name, val) {
            var result_panel = $("#result_panel");
            var result = $("#result");
            var input = $("#" + name);
            var json = {"name": name, "val": val};
            $.ajax({
                method: 'PUT',
                url: "/parameters/setparam.json",
                data: JSON.stringify(json),
                async: false,
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Accept", "application/json");
                    xhr.setRequestHeader("Content-Type", "application/json");
                },
                success: function (response) {
                    var message = response.message;
                    var hasError = response.hasError;
                    var lastVal = response.lastVal;
                    if (hasError == true) {
                        input.parent().addClass("input-group has-warning");
                        input.parent().removeClass("input-group has-success");
                        input.parent().removeClass("input-group has-error");
                        input.val(lastVal);
                    } else {
                        input.parent().addClass("input-group has-success");
                        input.parent().removeClass("input-group has-warning");
                        input.parent().removeClass("input-group has-error");
                    }
                    updateTooltip(input, message, 'bottom');
                },
                error: function (response) {
                    var json = JSON.parse(response.responseText);
                    var message = json.message;
                    input.parent().addClass("input-group has-error");
                    input.parent().removeClass("input-group has-success");
                    input.parent().removeClass("input-group has-warning");
                    updateTooltip(input, message, 'bottom');
                }
            });
        }
        function updateTooltip(element, tooltip, placement) {
            if (element.data('bs.tooltip') != null) {
                element.tooltip('hide');
                element.removeData('bs.tooltip');
            }
            element.tooltip({
                title: tooltip,
                placement: placement
            });
        }
    </script>
</head>
<body>


<jsp:include page="navbar.jsp"/>
<div class="container">
    <c:forEach items="${parameterMap}" var="group">
        <c:set var="group_name_trimmed" value="${fn:replace(group.key, ' ', '_')}" scope="page"/>

        <div class="panel panel-default" id="accordion">
            <div class="panel-heading">
                <h3 class="panel-title">
                    <a data-toggle="collapse" data-parent="#accordion" href="#${group_name_trimmed}">
                            ${group.key}
                    </a>
                </h3>
            </div>
            <c:set var="counter" value='0' scope="page"/>
            <c:set var="isRowed" value='true' scope="page"/>
            <c:set var="columnsNum" value='4' scope="page"/>
            <div id="${group_name_trimmed}" class="panel-collapse collapse in" role="tabpanel"
                 aria-labelledby="headingOne">
                <div class="panel-body">
                    <c:forEach items="${group.value}" var="parameter">
                        <c:if test="${counter lt columnsNum}">
                            <c:set var="counter" value="${counter + 1}"/>
                            <c:set var="isRowed" value='false'/>
                        </c:if>
                        <c:if test="${not (counter lt columnsNum)}">
                            <c:set var="counter" value='0'/>
                            <c:set var="isRowed" value='true'/>
                        </c:if>
                        <c:if test="${isRowed}">
                            <div class="row">
                        </c:if>
                        <div class="form-group col-md-3">
                            <label class="label label-default label-primary"
                                   for="${parameter.value.getClass().getSimpleName()}"
                                   data-toggle="tooltip" data-placement="top" title="${parameter.value.hint}">
                                    ${parameter.value.parameterName}, ${parameter.value.unit}
                            </label>
                            <input id="${parameter.value.getClass().getSimpleName()}" type="text" width="300"
                                   onchange="setParameterValue(
                                           '${parameter.value.getClass().getSimpleName()}',
                                           document.getElementById('${parameter.value.getClass().getSimpleName()}').value)"
                                   class="form-control"
                                   value="${parameter.value.stringRoundedValue}">
                        </div>

                        <c:if test="${isRowed}">
                            </div>
                        </c:if>


                    </c:forEach>
                </div>
            </div>
        </div>

    </c:forEach>
</div>
​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​

</body>
</html>