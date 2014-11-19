<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="bootstrap.jsp"/>
    <title>Registration</title>
</head>
<body>
<jsp:include page="navbar.jsp"/>

<div class="container col-lg-4 col-lg-offset-4 col-md-5 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-7 col-xs-offset-2">
    <form class="form-horizontal" action='${pageContext.request.contextPath}/user/register' method="POST">

        <fieldset>
            <div id=legend>
                <legend class="">Registration</legend>
            </div>
            <div class="control-group">
                <!-- Username -->
                <label class="control-label" for="name">Username</label>

                <div class="controls">
                    <form:errors for="name" path="user.name" cssClass="text-danger has-error"/>
                    <form:input cssClass="form-control" type="text" id="name" path="user.name" placeholder="Username"
                                required="true" autofocus="true"/>
                </div>
            </div>

            <div class="control-group">
                <!-- E-mail -->
                <label class="control-label" for="email">E-mail</label>

                <div class="controls">
                    <form:input cssClass="form-control" path="user.email" type="text" id="email" name="email"
                                placeholder="E-mail"/>
                    <form:errors path="user.email" cssClass="text-danger has-error"/>
                </div>
            </div>

            <div class="control-group">
                <!-- E-mail -->
                <label class="control-label" for="confirmEmail">E-mail (Confirm)</label>

                <div class="controls">
                    <form:input cssClass="form-control" path="user.confirmEmail" type="text" id="confirmEmail"
                                name="confirmEmail"
                                placeholder="E-mail confirmation"/>
                    <form:errors path="user.confirmEmail" cssClass="text-danger has-error"/>
                </div>
            </div>

            <div class="control-group">
                <!-- Password-->
                <label class="control-label" for="password">Password</label>

                <div class="controls">
                    <form:input cssClass="form-control" path="user.password" type="password" id="password"
                                name="password"
                                required="true" placeholder="Password"/>
                    <form:errors path="user.password" cssClass="text-danger has-error"/>
                </div>
            </div>

            <div class="control-group">
                <!-- Password -->
                <label class="control-label" for="confirmPassword">Password (Confirm)</label>

                <div class="controls">
                    <form:input cssClass="form-control" path="user.confirmPassword" type="confirmPassword"
                                id="confirmPassword"
                                name="confirmPassword"
                                required="true" placeholder="Password confirmation"/>
                    <form:errors path="user.confirmPassword" cssClass="text-danger has-error"/>
                </div>
            </div>
            <div>
                <legend class=""></legend>
            </div>
            <button class="btn btn-lg btn-success btn-block" type="submit">Register</button>
        </fieldset>
    </form>
</div>
</body>
</html>
