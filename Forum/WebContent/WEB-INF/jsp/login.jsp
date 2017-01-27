
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>


<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Login</title>

    <!-- 	base url -->

    <base href="${pageContext.request.contextPath}/login/"/>

    <!--  End	base url -->

    <%--stylying script--%>
    <%@include file="/WEB-INF/includes/styling.jsp"%>
    <%--end stylying script--%>


</head>


<body class="gray-bg">


<%--notification if login failed--%>

<c:if test="${statusModel.mode=='invalidMode'}">

    <div class="col-lg-3  login_alert">
        <div class="alert alert-danger">
                ${statusModel.message}
        </div>
    </div>


</c:if>

<c:if test="${statusModel.mode=='registerMode'}">

    <div class="col-lg-3  login_alert  alert alert-info alert-dismissable">
        <button class="close" aria-hidden="true" data-dismiss="alert" type="button">×</button>
        <strong>${statusModel.message}</strong>
    </div>

</c:if>


<%--***--%>


<div class="middle-box text-center loginscreen animated fadeInDown">
    <div>

        <h3>Welcome to Forum</h3>
        <p>This is sample testing of forum.</p>
        <p>Login in. To see it in action.</p>
        <form class="m-t" role="form" action="access" method="post">
            <div class="form-group">
                <input type="email" class="form-control" placeholder="Email Id" name="emailId" required="">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" placeholder="Password" name="password" required="">
            </div>
            <button type="submit" class="btn btn-primary block full-width m-b">Login</button>

            <a href="#"><small>Forgot password?</small></a>
            <p class="text-muted text-center"><small>Do not have an account?</small></p>
            <a class="btn btn-sm btn-white btn-block" href="register.html">Create an account</a>
        </form>
        <p class="m-t"> <small>Inspinia we app framework base on Bootstrap 3 &copy; 2014</small> </p>
    </div>
</div>


<%--bind script--%>
<%@include file="/WEB-INF/includes/script.jsp"%>
<%--bind script--%>

</body>


</html>
