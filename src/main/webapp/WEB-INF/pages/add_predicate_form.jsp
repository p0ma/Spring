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
        function deletePredicate(url) {
            var result_panel = $("#result_panel");
            var result = $("#result");
            $.ajax({
                url: url,
                method: "POST",
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
        function addPredicate(url) {
            var result_panel = $("#result_panel");
            var result = $("#result");
            var expression1 = $("#expression1").val();
            var expression2 = $("#expression2").val();
            try {
                var firesOnTrue = $("#firesOnTrue")[0].selectedOptions[0].value;
            } catch (e) {
                firesOnTrue = null;

            }
            try {
                var firesOnFalse = $("#firesOnFalse")[0].selectedOptions[0].value;
            } catch (e) {
                firesOnFalse = null;
            }
            var name = $("#name").val();
            try {
                var left = $("#left")[0].selectedOptions[0].value;
            } catch (e) {
                left = null;
            }
            try {
                var right = $("#right")[0].selectedOptions[0].value;
            } catch (e) {
                right = null;
            }
            var logicalOperation = $("#logicalOperations")[0].selectedOptions[0].value;
            $.ajax({
                url: url,
                method: "POST",
                data: ({name: name, left: left, right: right, logicalOperation: logicalOperation, expression1: expression1,
                    expression2: expression2, firesOnTrue: firesOnTrue, firesOnFalse: firesOnFalse}),
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
<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">${pretext} predicate</h3>
    </div>
    <div class="panel-body">
        <form class="form-inline">
            <div class="input-group">
                <div class="form-group">
                    <label for="name">Name</label>
                    <input type="text" class="form-control" id="name" placeholder="Enter name"
                           value="${predicate.name}">
                </div>
            </div>
        </form>
        <form class="form-inline">
            <div class="input-group">
                <div class="form-group">
                    <label for="left">Left</label>
                    <select id="left" class="form-control">
                        <c:forEach items="${parameters}" var="parameter">
                            <option value="${parameter.key}">
                                    ${parameter.value.parameterName}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="logicalOperations">Logical Operator</label>
                    <select id="logicalOperations" class="form-control">
                        <jsp:useBean id="logicalOperations" scope="request" type="java.util.List"/>
                        <c:forEach items="${logicalOperations}" var="logicalOperation">
                            <option value="${logicalOperation.logicalOperationSignature}">
                                    ${logicalOperation.logicalOperationSignature}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="right">Right</label>
                    <select id="right" class="form-control">
                        <%--@elvariable id="parameters" type="java.util.List"--%>
                        <c:forEach items="${parameters}" var="parameter">
                            <option value="${parameter.key}">
                                    ${parameter.value.parameterName}
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </form>
        <form class="form-inline" role="form">
            <div class="input-group">
                <div class="form-group">
                    <label for="expression1">First expression</label>
                    <input type="text" class="form-control" id="expression1" placeholder="Enter first expression"
                           value=${predicate.logicalOperation.operand1.expression}>
                </div>

                <div class="form-group">
                    <label for="expression2">Second expression</label>
                    <input type="text" class="form-control" id="expression2" placeholder="Enter second expression"
                           value=${predicate.logicalOperation.operand2.expression}>
                </div>
            </div>
        </form>
        <form class="form-inline" role="form">
            <div class="input-group">
                <div class="form-group">
                    <label for="firesOnTrue">Fires on true</label>
                    <select id="firesOnTrue" class="form-control">
                        <jsp:useBean id="predicates" scope="request" type="java.util.List"/>
                        <option value="-1">
                            &ltEmpty&gt
                        </option>
                        <c:forEach items="${predicates}" var="predicate">
                            <option value="${predicate.id}">
                                    ${predicate.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="firesOnFalse">Fires on false</label>
                    <select id="firesOnFalse" class="form-control">
                        <option value="-1">
                            &ltEmpty&gt
                        </option>
                        <c:forEach items="${predicates}" var="predicate">
                            <option value="${predicate.id}">
                                    ${predicate.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </form>


        <form class="form-inline" role="form">

            <div class="input-group">
                <div class="form-group">
                    <c:if test="${empty predicate}">
                        <button type="button" class="btn btn-default"
                                onclick="addPredicate('${pageContext.request.contextPath}/predicates/add_predicate.html')">
                            Add predicate
                        </button>
                    </c:if>
                    <c:if test="${not empty predicate}">
                        <button type="button" class="btn btn-default"
                                onclick="addPredicate('${pageContext.request.contextPath}/predicates/edit_predicate/${predicate.id}.html')">
                            Edit predicate
                        </button>
                        <button type="button" class="btn btn-default"
                                onclick="deletePredicate('${pageContext.request.contextPath}/predicates/delete_predicate/${predicate.id}.html')">
                            Delete predicate
                        </button>
                    </c:if>
                </div>
            </div>
        </form>

        <div id="result_panel" class="panel panel-success" hidden>
            <div id="result" class="panel-heading" hidden>
                ${result}
            </div>
        </div>
    </div>
</div>


</body>
</html>