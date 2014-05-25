<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="jquery.jsp"/>
<html>
<head>
    <title>Predicates</title>
    <jsp:include page="bootstrap.jsp"/>


    <script type="text/javascript">
        $(document).ready(function () {
            $('table tr').click(function () {
                window.location = $(this).attr('href');
                return false;
            })
        });
    </script>

    <style>
        table tr {
            cursor: pointer;
        }
    </style>

</head>
<body>
<jsp:include page="navbar.jsp"></jsp:include>
<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">Predicates</h3>
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
                <jsp:useBean id="predicates" scope="request"
                             type="java.util.List<system.decision.support.logic.Predicate>"/>
                <c:if test="${not empty predicates}">
                    <c:forEach var="predicate" items="${predicates}">
                        <tr href="${pageContext.request.contextPath}/predicates/edit_predicate_form/${predicate.id}">
                            <td>
                                    ${predicate.name}
                            </td>
                            <td>${predicate.logicalOperation.getLogicalOperationName()}</td>
                            <td>
                                    ${predicate.firesOnTrue.name}
                            </td>
                            <td>
                                    ${predicate.firesOnFalse.name}
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
            </table>
        </table>
    </div>
</div>
<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">Questions</h3>
    </div>
    <div class="panel-body">
        <table class="table-responsive">
            <table class="table">
                <tr>
                    </h4>
                    <th>Name</th>
                    <th>Question</th>
                    <th>FiresOnTrue</th>
                    <th>FiresOnFalse</th>
                </tr>
                <jsp:useBean id="questions" scope="request"
                             type="java.util.List<system.decision.support.logic.Predicate>"/>
                <c:if test="${not empty questions}">
                    <c:forEach var="question" items="${questions}">
                        <tr href="${pageContext.request.contextPath}/predicates/edit_question_form/${question.id}">
                            <td>
                                    ${question.name}
                            </td>
                            <td>
                                    ${question.conclusion.message}
                            </td>
                            <td>
                                    ${question.firesOnTrue.name}
                            </td>
                            <td>
                                    ${question.firesOnFalse.name}
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
            </table>
        </table>
    </div>
</div>

<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">Conclusions</h3>
    </div>
    <div class="panel-body">
        <table class="table-responsive">
            <table class="table">
                <tr>
                    </h4>
                    <th>Name</th>
                    <th>Message</th>
                </tr>
                <jsp:useBean id="conclusions" scope="request"
                             type="java.util.List<system.decision.support.logic.Predicate>"/>
                <c:if test="${not empty conclusions}">
                    <c:forEach var="conclusion" items="${conclusions}">
                        <tr href="${pageContext.request.contextPath}/predicates/edit_conclusion_form/${conclusion.id}">
                            <td>
                                    ${conclusion.name}
                            </td>
                            <td>
                                    ${conclusion.conclusion.message}
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
            </table>
        </table>
    </div>
</div>


</body>
</html>