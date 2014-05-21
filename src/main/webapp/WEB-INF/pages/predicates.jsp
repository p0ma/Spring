<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Predicates</title>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
    <script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <script type="text/javascript">
        function doAjaxPost(name, val) {
            $.ajax({
                url: "/parameters/setparam.html",
                data: ({name: name, val: val}),
                success: function (result) {
                    alert('result:' + result)
                },
                error: function (e) {
                    alert('Error: ' + e);
                }
            });
        }
    </script>

</head>
<body>
<jsp:include page="navbar.jsp"></jsp:include>
<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">Pressure</h3>
    </div>
    <div class="panel-body">
        <table class="table-responsive">
            <table class="table">
                <tr>
                    </h4>
                    <th>Name</th>
                    <th>LogicalOperation</th>
                    <th>FiresOnTrue</th>
                    <th>FiresOnFalse</th>
                </tr>
                <c:if test="${not empty predicates}">
                    <c:forEach var="predicate" items="${predicates}">
                        <tr>
                            <td>${predicate.name}</td>
                            <td>${predicate.logicalOperation.getLogicalOperationName()}</td>
                            <td>${predicate.firesOnTrue.name}</td>
                            <td>${predicate.firesOnFalse.name}</td>
                        </tr>
                    </c:forEach>
                </c:if>
            </table>
        </table>
        <!-- /.col-lg-6 -->
    </div>
</div>


</body>
</html>