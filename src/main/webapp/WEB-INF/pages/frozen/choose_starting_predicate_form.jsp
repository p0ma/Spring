<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<jsp:include page="../jquery.jsp"/>
<html>
<head>
    <title>Well</title>
    <jsp:include page="../basic/bootstrap.jsp"/>
    <script type="text/javascript">
        function chooseStartingPredicate(id) {
            var result_panel = $("#result_panel");
            var result = $("#result");
            $.ajax({
                url: "${pageContext.request.contextPath}/predicates/choose_starting_predicate.html",
                method: "POST",
                data: ({id: id}),
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
<jsp:include page="../basic/navbar.jsp"></jsp:include>
<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">Choosing triggering predicate</h3>
    </div>
    <div class="panel-body">
        <div class="input-group">

            <form class="form-inline">
                <div class="input-group">
                    <div class="form-group">
                        <jsp:useBean id="predicates" scope="request" type="java.util.List"/>
                        <select id="predicateSelect" class="form-control">
                            <c:forEach items="${predicates}" var="predicate">
                                <option value="${predicate.id}">
                                        ${predicate.name}
                                </option>
                            </c:forEach>
                        </select>

                        <button type="button" class="btn btn-default"
                                onclick="chooseStartingPredicate(
                                document.getElementById('predicateSelect').selectedOptions[0].value)">
                            Chose triggering predicate
                        </button>

                        <div id="result_panel" class="panel panel-danger" hidden="true">
                            <div id="result" class="panel-heading" hidden="true"></div>
                        </div>
                    </div>
                </div>
            </form>

        </div>
    </div>
</div>


</body>
</html>