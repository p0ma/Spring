<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../basic/bootstrap.jsp"/>
    <title><spring:message code='registration'/></title>
</head>
<body>
<jsp:include page="../basic/navbar.jsp"/>

<div class="container col-lg-4 col-lg-offset-4 col-md-5 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-7 col-xs-offset-2">
    <form:form commandName="userRegistrationDTO" class="form-horizontal"
               action='${pageContext.request.contextPath}/registration' method="POST">

        <fieldset>
            <div id=legend>
                <legend class=""><spring:message code="registration"/></legend>
            </div>
            <div class="control-group">
                <!-- Username -->
                <spring:message code='user.name' var="usernamePlaceHolder"/>
                <label class="control-label" for="name"><spring:message code="user.name"/></label>

                <div class="controls">
                    <form:input cssClass="form-control" type="text" id="name" path="name"
                                placeholder="${usernamePlaceHolder}"
                                required="true" autofocus="true"/>
                    <form:errors for="name" path="name" cssClass="text-danger has-error"/>
                </div>
            </div>

            <div class="control-group">
                <!-- E-mail -->
                <spring:message code='user.email' var="mailPlaceHolder"/>
                <label class="control-label" for="email"><spring:message code="user.email"/></label>

                <div class="controls">
                    <form:input cssClass="form-control" path="email" type="text" id="email"
                                name="email"
                                placeholder="${mailPlaceHolder}"/>
                    <form:errors path="email" cssClass="text-danger has-error"/>
                </div>
            </div>

            <div class="control-group">
                <!-- E-mail (Confirm)-->
                <spring:message code='user.emailConfirm' var="mailConfirmPlaceHolder"/>
                <label class="control-label" for="confirmEmail"><spring:message code="user.emailConfirm"/></label>

                <div class="controls">
                    <form:input cssClass="form-control" path="confirmEmail" type="text"
                                id="confirmEmail"
                                name="confirmEmail"
                                placeholder="${mailConfirmPlaceHolder}"/>
                    <form:errors path="confirmEmail" cssClass="text-danger has-error"/>
                </div>
            </div>

            <div class="control-group">
                <!-- Password-->
                <spring:message code='user.password' var="passwordPlaceHolder"/>
                <label class="control-label" for="password"><spring:message code="user.password"/></label>

                <div class="controls">
                    <form:input cssClass="form-control" path="password" type="password"
                                id="password"
                                name="password"
                                required="true" placeholder="${passwordPlaceHolder}"/>
                    <form:errors path="password" cssClass="text-danger has-error"/>
                </div>
            </div>

            <div class="control-group">
                <!-- Password -->
                <spring:message code='user.passwordConfirm' var="passwordConfirmPlaceHolder"/>
                <label class="control-label" for="confirmPassword"><spring:message code="user.passwordConfirm"/></label>

                <div class="controls">
                    <form:input cssClass="form-control" path="confirmPassword" type="password"
                                id="confirmPassword"
                                name="confirmPassword"
                                required="true" placeholder="${passwordConfirmPlaceHolder}"/>
                    <form:errors path="confirmPassword" cssClass="text-danger has-error"/>
                </div>
            </div>
            <div>
                <legend class=""></legend>
            </div>
            <button class="btn btn-lg btn-success btn-block" type="submit"><spring:message code="register"/></button>
        </fieldset>
    </form:form>
</div>
</body>
</html>
