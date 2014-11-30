<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<div class="container">
    <nav class="navbar navbar-default" role="navigation">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/"><spring:message code="logo.text1"/></a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <sec:authorize access="isAuthenticated()">
                    <li>
                        <a href="${pageContext.request.contextPath}/parameters"><spring:message code="parameters"/></a>
                    </li>
                    <li><a href="${pageContext.request.contextPath}/chart"><spring:message code="chart"/></a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <spring:message code="well"/><b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="${pageContext.request.contextPath}/well/add_pipe_section_form">
                                <spring:message code="pipeSection.add"/></a>
                            </li>
                            <li><a href="${pageContext.request.contextPath}/well/delete_pipe_section_form">
                                <spring:message code="pipeSection.delete"/>
                            </a></li>
                            <li class="divider"></li>
                            <li><a href="${pageContext.request.contextPath}/well/pipe_sections">
                                <spring:message code="pipeSection.delete"/></a>
                            </li>
                        </ul>
                    </li>
                </sec:authorize>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <sec:authorize access="not isAuthenticated()">
                    <li><a href="${pageContext.request.contextPath}/login">
                        <spring:message code="login"/></a></li>
                    <li><a href="${pageContext.request.contextPath}/registration">
                        <spring:message code="registration"/></a></li>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <li>
                        <a href="${pageContext.request.contextPath}/logout"><spring:message code="logout"/></a>
                    </li>
                </sec:authorize>
            </ul>

            <ul class="nav navbar-nav navbar-link">
                <div id="flags">

                    <a href="?locale=en">
                        <span class="flag-icon flag-icon-gb"></span>
                    </a>
                    <a href="?locale=ua">
                        <span class="flag-icon flag-icon-ua"></span>
                    </a>
                </div>

            </ul>
        </div>
    </nav>

</div>
