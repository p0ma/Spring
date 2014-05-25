<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="jquery.jsp"/>
<html>
<head>
    <title>Parameters</title>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    <jsp:include page="bootstrap.jsp"/>
    <script type="text/javascript">
        function showDialog(message) {
            var result = $("#result");
            var result_panel = $("#result_panel");
            $("#dialog-confirm").dialog({
                resizable: true,
                modal: true,
                resize: "auto",

                buttons: {
                    "Yes": function () {
                        $(this).dialog("close");
                        $.ajax({
                            url: "/inference/conclusion.htm",
                            data: ({boolVal: true, message: message}),
                            method: "POST",
                            success: function (data) {
                                if (data != null) {
                                    console.log(data)
                                    var obj = $.parseJSON(data);
                                    if (obj != {}) {
                                        if (obj.boolVal == false) {
                                            showDialog(obj.message)
                                        } else {
                                            result.parent().addClass("panel panel-success");
                                            result.parent().removeClass("panel panel-danger");
                                            result.parent().show();
                                            result.html(obj.message);
                                            result.show();
                                        }
                                    } else {
                                        alert('No results found.');
                                    }
                                } else {
                                    alert('An error occurred, try again.');
                                }
                            },
                            error: function (response) {
                                alert(response.responseText);
                            }
                        });
                    },
                    "No": function () {
                        $(this).dialog("close");
                        $.ajax({
                            url: "/inference/conclusion.htm",
                            data: ({boolVal: false, message: message}),
                            method: "POST",
                            success: function (data) {
                                if (data != null) {
                                    console.log(data)
                                    var obj = $.parseJSON(data);
                                    if (obj != {}) {
                                        if (obj.boolVal == false) {
                                            showDialog(obj.message)
                                        } else {
                                            result.parent().addClass("panel panel-success");
                                            result.parent().removeClass("panel panel-danger");
                                            result.parent().show();
                                            result.html(obj.message);
                                            result.show();
                                        }
                                    } else {
                                        alert('No results found.');
                                    }
                                } else {
                                    alert('An error occurred, try again.');
                                }
                            },
                            error: function (data) {
                                if (data != null) {
                                    console.log(data)
                                    var obj = $.parseJSON(data);
                                    if (obj != {}) {
                                        if (obj.boolVal == false) {
                                            showDialog(obj.message)
                                        } else {
                                            result.parent().addClass("panel panel-success");
                                            result.parent().removeClass("panel panel-danger");
                                            result.parent().show();
                                            result.html(obj.message);
                                            result.show();
                                        }
                                    } else {
                                        alert('No results found.');
                                    }
                                } else {
                                    alert('An error occurred, try again.');
                                }
                            }
                        });
                    }
                }
            }
            ).text(message)
            ;
        }
        function getConclusion() {
            var result_panel = $("#result_panel");
            var result = $("#result");
            $.ajax({
                url: "/inference/conclusion.htm",
                method: "POST",
                data: ({valBool: true, message: ""}),
                success: function (data) {
                    if (data != null) {
                        console.log(data)
                        var obj = $.parseJSON(data);

                        if (obj != {}) {
                            if (obj.boolVal == true) {
                                result.parent().addClass("panel panel-success");
                                result.parent().removeClass("panel panel-danger");
                                result.parent().show();
                                result.html(obj.message);
                                result.show();
                            } else {
                                showDialog(obj.message)
                            }
                        } else {
                            alert('No results found.');
                        }
                    } else {
                        alert('An error occurred, try again.');
                    }
                },
                error: function (data) {
                    if (data != null) {
                        console.log(data)
                        var obj = $.parseJSON(data);

                        if (obj != {}) {
                            if (obj.boolVal == false) {
                                result.parent().addClass("panel panel-danger");
                                result.parent().removeClass("panel panel-success");
                                result.parent().show();
                                result.html(obj.message);
                                result.show();
                            }
                        } else {
                            alert('No results found.');
                        }
                    } else {
                        alert('An error occurred, try again.');
                    }
                }
            });
        }
    </script>

</head>
<body>
<jsp:include page="navbar.jsp"></jsp:include>


<div id="dialog-confirm" title="Answer the folowing question" hidden>
    <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>These items will be
        permanently deleted and cannot be recovered. Are you sure?</p>
</div>

<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">Inference model</h3>
    </div>
    <div class="panel-body">
        <button class="btn btn-default" type="button"
                onclick="getConclusion()">
            Make inference
        </button>

        <div id="result_panel" class="panel panel-danger" hidden>
            <div id="result" class="panel-heading" hidden></div>
        </div>
    </div>
</div>

</body>
</html>