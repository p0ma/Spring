<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Parameters</title>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
    <script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <script type="text/javascript">
        function setParameterValue(name, val) {
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
        <div class="col-lg-6">
            <div class="input-group">
              <span class="input-group-btn">
                    <button class="btn btn-default" type="button"
                            onclick="setParameterValue(
                            'DrillPipeInnerPressure', document.getElementById('DrillPipeInnerPressure').value)">
                        Drill pipe inner pressure
                    </button>
              </span>
                <input id="DrillPipeInnerPressure" type="text" class="form-control" value="${DrillPipeInnerPressure}">
            </div>

            <div class="input-group">
              <span class="input-group-btn">
                    <button class="btn btn-default" type="button"
                            onclick="setParameterValue(
                            'DrillPipeOuterPressure', document.getElementById('DrillPipeOuterPressure').value)">
                        Drill pipe outer pressure
                    </button>
              </span>

                <div class="text-center">
                    <input id="DrillPipeOuterPressure" type="text" class="form-control"
                           value="${DrillPipeOuterPressure}">
                </div>
            </div>

            <div class="input-group">
              <span class="input-group-btn">
                    <button class="btn btn-default" type="button"
                            onclick="setParameterValue(
                            'MudPumpingPressure', document.getElementById('MudPumpingPressure').value)">
                        Mud pumping pressure
                    </button>
              </span>
                <input id="MudPumpingPressure" type="text" class="form-control" value="${MudPumpingPressure}">
            </div>

            <div class="input-group">
              <span class="input-group-btn">
                    <button class="btn btn-default" type="button"
                            onclick="setParameterValue(
                            'MudPumpingPressureLoss', document.getElementById('MudPumpingPressureLoss').value)">
                        Mud pumping pressure loss
                    </button>
              </span>
                <input id="MudPumpingPressureLoss" type="text" class="form-control" value="${MudPumpingPressureLoss}">
            </div>
        </div>
    </div>


    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">Well</h3>

            <div class="panel-body">
                <div class="col-lg-6">
                    <div class="input-group">
              <span class="input-group-btn">
                    <button class="btn btn-default" type="button"
                            onclick="setParameterValue(
                            'ActualWellDepth', document.getElementById('ActualWellDepth').value)">
                        Actual well depth
                    </button>
              </span>
                        <input id="ActualWellDepth" type="text" class="form-control" value="${ActualWellDepth}">
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>


</body>
</html>