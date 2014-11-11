<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Example of Twitter Bootstrap 3 Tooltip</title>
    <jsp:include page="bootstrap.jsp"/>
    <script type="text/javascript">
        $(document).ready(function () {
            $(".tooltip-examples a").tooltip({
                placement: 'top'
            });
        });
    </script>
    <style type="text/css">
        .bs-example {
            margin: 100px 50px;
        }
    </style>
</head>
<body>
<div class="bs-example">
    <ul class="tooltip-examples list-inline">
        <li><a href="#" data-toggle="tooltip" data-original-title="Default tooltip">Tooltip</a></li>
        <li><a href="#" data-toggle="tooltip" data-original-title="Another tooltip">Another tooltip</a></li>
        <li><a href="#" data-toggle="tooltip"
               data-original-title="A much longer tooltip to demonstrate the max-width of the Bootstrp tooltip.">Large
            tooltip</a></li>
        <li><a href="#" data-toggle="tooltip" data-original-title="The last tip!">Last tooltip</a></li>
    </ul>
</div>
</body>
</html>                                		