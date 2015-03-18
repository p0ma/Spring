<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <jsp:include page="../basic/bootstrap.jsp"/>
    <title><spring:message code="parameters"/></title>
    <script type="text/javascript">
        $(document).ready(function () {
            $("body").tooltip({selector: '[data-toggle=tooltip]', html: true});
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
                        input.parent().addClass("has-warning");
                        input.parent().removeClass("has-success");
                        input.parent().removeClass("has-error");
                        input.val(lastVal);
                    } else {
                        input.parent().addClass("has-success");
                        input.parent().removeClass("has-warning");
                        input.parent().removeClass("has-error");
                    }
                    updateTooltip(input, message, 'bottom');
                },
                error: function (response) {
                    var json = JSON.parse(response.responseText);
                    var message = json.message;
                    input.addClass("has-error");
                    input.parent().removeClass("has-success");
                    input.parent().removeClass("has-warning");
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
<jsp:include page="../basic/navbar.jsp"/>
<div class="container">
    <div role="tabpanel">
        <ul class="nav nav-tabs" role="tablist">
            <c:set var="active" scope="page" value="true"/>
            <c:forEach items="${parameterMap}" var="group">
                <c:set var="group_name_trimmed" value="${fn:replace(group.key, ' ', '_')}" scope="page"/>
                <c:if test="${not active}">
                    <li role="presentation">
                </c:if>
                <c:if test="${active}">
                    <li role="presentation" class="active">
                    <c:set var="active" value="${not active}"/>
                </c:if>
                <a href="#${group_name_trimmed}" aria-controls="${group_name_trimmed}" role="tab"
                   data-toggle="tab">${group.key}</a>
                </li>
            </c:forEach>
        </ul>


        <div class="tab-content">
            <c:set var="active" scope="page" value="true"/>
            <c:forEach items="${parameterMap}" var="group">
            <c:set var="group_name_trimmed" value="${fn:replace(group.key, ' ', '_')}" scope="page"/>
            <c:if test="${not active}">
            <div role="tabpanel" class="tab-pane" id="${group_name_trimmed}">
                </c:if>
                <c:if test="${active}">
                <div role="tabpanel" class="tab-pane active" id="${group_name_trimmed}">
                    <c:set var="active" value="${not active}"/>
                    </c:if>
                    <c:forEach items="${group.value}" var="parameter">
                        <div class="input-group">
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
                                   value="${parameter.value.roundedValue}">
                        </div>
                    </c:forEach>
                </div>
                </c:forEach>
        </div>
        </div>
    </div>
    ​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​
</body>
</html>