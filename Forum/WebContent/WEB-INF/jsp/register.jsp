
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<html>


<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Register</title>

    <!-- 	base url -->

    <base href="${pageContext.request.contextPath}/login/"/>

    <!--  End	base url -->

    <%--stylying script--%>
    <%@include file="/WEB-INF/includes/styling.jsp"%>
    <%--end stylying script--%>

</head>

<body class="gray-bg">

<div class="middle-box text-center loginscreen   animated fadeInDown">
    <div>

        <h3>Register to Forum</h3>
        <p>Create account to see it in action.</p>
        <form class="m-t" role="form" action="register" method="post">
            <div class="form-group">
                <input type="text" class="form-control" name="name" placeholder="Name" required="">
            </div>
            <div class="form-group">
                <input type="email" class="form-control" name="emailId" placeholder="Email" required="">
            </div>
            <div class="form-group">
                <input type="text" class="form-control" name="phoneNo" placeholder="Phone Number" required="">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" name="password" placeholder="Password" required="">
            </div>
            <div class="form-group">
                <div class="checkbox i-checks"><label> <input type="checkbox"><i></i> Agree the terms and policy </label></div>
            </div>
            <button type="submit" class="btn btn-primary block full-width m-b">Register</button>


        </form>
        <p class="m-t"> <small>Inspinia we app framework base on Bootstrap 3 &copy; 2014</small> </p>
    </div>
</div>

<%--bind script--%>
<%@include file="/WEB-INF/includes/script.jsp"%>
<%--bind script--%>


<script>
    $(document).ready(function(){
        $('.i-checks').iCheck({
            checkboxClass: 'icheckbox_square-green',
            radioClass: 'iradio_square-green',
        });
    });
</script>
</body>


<!-- Mirrored from webapplayers.com/inspinia_admin-v2.4/register.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 01 Feb 2016 10:37:35 GMT -->
</html>
