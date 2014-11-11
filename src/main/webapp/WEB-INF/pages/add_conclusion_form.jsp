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
        function deleteConclusion(url) {
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
        function addConclusion(url) {
            var result_panel = $("#result_panel");
            var result = $("#result");
            var message = $("#message").val();
            var name = $("#name").val();
            $.ajax({
                url: url,
                method: "POST",
                data: ({name: name, message: message}),
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
        <h3 class="panel-title">${pretext} conclusion</h3>
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
            <div class="input-group">
                <div class="form-group">
                    <label for="message">Conclusion text</label>
                    <input type="text" class="form-control" id="message" placeholder="Enter conclusion text"
                           value="${predicate.conclusion.message}">
                </div>
            </div>
        </form>

        <form class="form-inline" role="form">

            <div class="input-group">
                <div class="form-group">
                    <c:if test="${empty predicate}">
                        <button type="button" class="btn btn-default"
                                onclick="addConclusion('${pageContext.request.contextPath}/predicates/add_conclusion.html')">
                            Add conclusion
                        </button>
                    </c:if>
                    <c:if test="${not empty predicate}">
                        <button type="button" class="btn btn-default"
                                onclick="addConclusion('${pageContext.request.contextPath}/predicates/edit_conclusion/${predicate.id}.html')">
                            Edit conclusion
                        </button>
                        <button type="button" class="btn btn-default"
                                onclick="deleteConclusion('${pageContext.request.contextPath}/predicates/delete_conclusion/${predicate.id}.html')">
                            Delete conclusion
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