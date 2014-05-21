<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Predicates</title>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
    <script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <script type="text/javascript">
        function addPipeSection(length, outerDiameter, thickness) {
            $.ajax({
                url: "/well/add_pipe_section.html",
                data: ({length: length, outerDiameter: outerDiameter, thickness: thickness}),
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
        <h3 class="panel-title">Adding pipe section</h3>
    </div>
    <div class="panel-body">
        <div class="input-group">

            <form class="form-inline" role="form">
                <div class="form-group">
                    <label class="sr-only" for="inputLength">Length</label>
                    <input type="text" class="form-control" id="inputLength" placeholder="Enter length">
                </div>
                <div class="form-group">
                    <label class="sr-only" for="inputOuterDiameter">Outer diameter</label>
                    <input type="number" class="form-control" id="inputOuterDiameter"
                           placeholder="Enter inner diameter">
                </div>

                <div class="form-group">
                    <label class="sr-only" for="inputThickness">Thickness</label>
                    <input type="number" class="form-control" id="inputThickness" placeholder="Enter thickness">
                </div>

                <button type="button" class="btn btn-default"
                        onclick="addPipeSection(
                    document.getElementById('inputLength').value,
                    document.getElementById('inputOuterDiameter').value,
                    document.getElementById('inputThickness').value)">
                    Add pipe section
                </button>
            </form>

        </div>
    </div>
</div>


</body>
</html>