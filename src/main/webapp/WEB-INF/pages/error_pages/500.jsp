<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
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
            <spring:message code="ErrorPage.oops"/></h1>

        <h2>
            <spring:message code="InternalServerError.name"/></h2>

        <div class="error-details">
            <spring:message code="InternalServerError.message"/>
        </div>
        <div class="error-actions">
            <a href="/" class="btn btn-primary btn-lg"><span class="glyphicon glyphicon-home"></span>
                <spring:message code="ErrorPage.takeMeHome"/></a>
        </div>
    </div>
</div>
</body>
</html>
