<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>Not found</title>
</head>
<body>
<div class="container">
    <div class="error-template">
        <h1>
            Oops!</h1>

        <h2>
            Access denied</h2>

        <div class="error-details">
            Access to the referred url has been denied!
        </div>
        <div class="error-actions">
            <a href="/" class="btn btn-primary btn-lg"><span class="glyphicon glyphicon-home"></span>
                Take Me Home </a>
        </div>
    </div>
</div>
</body>
</html>
