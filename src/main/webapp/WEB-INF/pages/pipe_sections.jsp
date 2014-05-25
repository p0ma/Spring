<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<jsp:include page="jquery.jsp"/>
<head>
    <title>Predicates</title>

    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/jquery-2.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/tablednd/tablednd.js"></script>

    <jsp:include page="bootstrap.jsp"/>

    <script type="text/javascript">
        $(document).ready(function () {
            var rows;
            $("#table").tableDnD({
                onDrop: function (table, row) {
                    var result_panel = $("#result_panel");
                    var result = $("#result");
                    var rows1 = table.tBodies[0].rows;
                    var from = 0;
                    for (var i = 0; i < table.tBodies[0].rows.length; i++) {
                        if (rows[i] == row.id) {
                            from = rows1[i].id;
                        }
                    }
                    if (row.id === from) {

                    } else {
                        $.ajax({
                            url: "/well/reorder.html",
                            data: ({fromId: from, toId: row.id}),
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
                        })
                    }

                },
                onDragStart: function (table, row) {
                    rows = [];
                    var rows1 = table.tBodies[0].rows;
                    for (var i = 0; i < rows1.length; i++) {
                        rows[i] = rows1[i].id;
                    }
                }
            })
        });
    </script>


</head>
<body>
<jsp:include page="navbar.jsp"/>
<%--<ul id="draggablePanelList" class="list-unstyled">
    <li class="panel panel-info">
        <div class="panel-heading">You can drag this panel.</div>
        <div class="panel-body">Content ...</div>
    </li>
    <li class="panel panel-info">
        <div class="panel-heading">You can drag this panel too.</div>
        <div class="panel-body">Content ...</div>
    </li>
</ul>--%>
<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">Pipe sections</h3>
    </div>
    <div class="panel-body">
        <table id="table" class="table">
            <thead>
            <tr>
                <th>Id</th>
                <th>Order number</th>
                <th>Length</th>
                <th>Outer diameter</th>
                <th>Thickness</th>
            </tr>
            </thead>
            <tbody>

            <c:if test="${not empty pipeSections}">
                <c:forEach var="predicate" items="${pipeSections}">
                    <tr id="${predicate.id}">
                        <td>${predicate.id}</td>
                        <td>${predicate.orderNumber}</td>
                        <td>${predicate.length}</td>
                        <td>${predicate.pipeType.outerDiameter}</td>
                        <td>${predicate.pipeType.thickness}</td>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
        <div id="result_panel" class="panel panel-danger" hidden>
            <div id="result" class="panel-heading" hidden></div>
        </div>
    </div>
</div>
</body>
</html>