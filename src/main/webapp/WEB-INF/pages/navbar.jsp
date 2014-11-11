<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Decision Support System</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/parameters">Parameters</a></li>
                <li><a href="${pageContext.request.contextPath}/chart">Chart</a></li>
                <li><a href="${pageContext.request.contextPath}/inference">Inference</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Predicates<b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/predicates/add_predicate_form">Add predicate</a>
                        </li>
                        <li><a href="${pageContext.request.contextPath}/predicates/add_conclusion_form">Add
                            conclusion</a></li>
                        <li><a href="${pageContext.request.contextPath}/predicates/add_question_form">Add question</a>
                        </li>
                        <li><a href="${pageContext.request.contextPath}/predicates/choose_starting_predicate_form">
                            Choose triggering predicate</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="${pageContext.request.contextPath}/predicates">Predicates list</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Well<b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/well/add_pipe_section_form">Add pipe section</a>
                        </li>
                        <li><a href="${pageContext.request.contextPath}/well/delete_pipe_section_form">Delete pipe
                            section</a></li>
                        <li class="divider"></li>
                        <li><a href="${pageContext.request.contextPath}/well/pipe_sections">Pipe sections list</a></li>
                    </ul>
                </li>

            </ul>
        </div>
    </div>
</nav>
