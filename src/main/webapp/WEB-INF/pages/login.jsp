<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <jsp:include page="bootstrap.jsp"/>
    <title>Parameters</title>
</head>
<body>
<jsp:include page="navbar.jsp"/>
<div class="container col-lg-4 col-lg-offset-4 col-md-5 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-7 col-xs-offset-2">
    <form class="form-signin" role="form" name="f" action="${pageContext.request.contextPath}/user/signin"
          method="post">
        <fieldset>
            <div id=legend>
                <legend class="">Signing in</legend>
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
                <!-- Password-->
                <label class="control-label" for="password">Password</label>

                <div class="controls">
                    <form:input cssClass="form-control" path="user.password" type="password" id="password"
                                name="password"
                                required="true" placeholder="Password"/>
                    <form:errors path="user.password" cssClass="text-danger has-error"/>
                </div>
            </div>

            <div class="checkbox">
                <label>
                    <input type="checkbox" value="remember-me"> Remember me
                </label>
            </div>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>

        </fieldset>
    </form>

</div>
</body>

</html>
