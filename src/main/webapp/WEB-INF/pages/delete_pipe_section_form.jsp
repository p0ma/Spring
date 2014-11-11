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
        function deletePipeSection(id) {
            var result_panel = $("#result_panel");
            var result = $("#result");
            $.ajax({
                url: "${pageContext.request.contextPath}/well/delete_pipe_section.html",
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
<jsp:include page="navbar.jsp"></jsp:include>
<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">Deleting pipe section</h3>
    </div>
    <div class="panel-body">
        <div class="input-group">

            <form class="form-inline" role="form">
                <select id="pipeSelect" class="form-control">
                    <c:forEach items="${pipeSections}" var="predicate">
                        <option value="${predicate.id}">
                                ${predicate.length} ${predicate.pipeType.outerDiameter} ${predicate.pipeType.thickness}
                        </option>
                    </c:forEach>
                </select>

                <button type="button" class="btn btn-default"
                        onclick="deletePipeSection(
                    document.getElementById('pipeSelect').selectedOptions[0].value)">
                    Delete pipe section
                </button>

                <div id="result_panel" class="panel panel-danger" hidden="true">
                    <div id="result" class="panel-heading" hidden="true"></div>
                </div>
            </form>

        </div>
    </div>
</div>


</body>
</html>