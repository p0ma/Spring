<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <jsp:include page="../basic/bootstrap.jsp"/>
    <title><spring:message code="signingIn"/></title>
</head>
<body>
<jsp:include page="../basic/navbar.jsp"/>
<div class="container col-lg-4 col-lg-offset-4 col-md-5 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-7 col-xs-offset-2">
    <form:form class="form-signin" commandName="user" action="/login" method="post">
        <fieldset>
            <div id=legend>
                <legend class=""><spring:message code="signingIn"/></legend>
            </div>
            <div class="control-group">
                <!-- Username -->
                <spring:message code='user.name' var="usernamePlaceholder"/>
                <label class="control-label" for="name"><spring:message code="user.name"/></label>

                <div class="controls">
                    <form:input cssClass="form-control" type="text" path="name" placeholder="${usernamePlaceholder}"
                                required="true" autofocus="true"/>
                    <form:errors for="name" path="name" cssClass="text-danger has-error"/>
                </div>
            </div>
            <div class="control-group">
                <!-- Password-->
                <spring:message code='user.password' var="passwordPlaceholder"/>
                <label class="control-label" for="password"><spring:message code="user.password"/></label>

                <div class="controls">
                    <form:input cssClass="form-control" path="password" type="password" id="password"
                                name="password"
                                required="true" placeholder="${passwordPlaceholder}"/>
                    <form:errors path="password" cssClass="text-danger has-error"/>
                </div>
            </div>
            <div class="checkbox">
                <label>
                    <input type="checkbox" value="_spring_security_remember_me"><spring:message code="rememberMe"/>
                </label>
            </div>
            <button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="signIn"/></button>
        </fieldset>
    </form:form>
</div>
</body>
</html>
