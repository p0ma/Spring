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
        function deleteQuestion(url) {
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
        function addQuestion(url) {
            var result_panel = $("#result_panel");
            var result = $("#result");
            var message = $("#message").val();
            var name = $("#name").val();
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
            $.ajax({
                url: url,
                method: "POST",
                data: ({name: name, message: message, firesOnTrue: firesOnTrue, firesOnFalse: firesOnFalse}),
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
        <h3 class="panel-title">${pretext} question</h3>
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
                    <label for="message">Question text</label>
                    <input type="text" class="form-control" id="message" placeholder="Enter question text"
                           value="${predicate.conclusion.message}">
                </div>
            </div>
        </form>

        <form class="form-inline" role="form">
            <div class="input-group">
                <div class="form-group">
                    <label for="firesOnTrue">Fires on true</label>
                    <select id="firesOnTrue" class="form-control">
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
                                onclick="addQuestion('${pageContext.request.contextPath}/predicates/add_question.html')">
                            Add question
                        </button>
                    </c:if>
                    <c:if test="${not empty predicate}">
                        <button type="button" class="btn btn-default"
                                onclick="addQuestion('${pageContext.request.contextPath}/predicates/edit_question/${predicate.id}.html')">
                            Edit question
                        </button>
                        <button type="button" class="btn btn-default"
                                onclick="deleteQuestion('${pageContext.request.contextPath}/predicates/delete_question/${predicate.id}.html')">
                            Delete question
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