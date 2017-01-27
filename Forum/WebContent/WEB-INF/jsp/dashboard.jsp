<%@include file="/WEB-INF/includes/checkUser.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 1/23/2017
  Time: 12:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Dashboard</title>

    <!-- 	base url -->

    <base href="${pageContext.request.contextPath}/dashboard/"/>

    <!--  End	base url -->

    <%--stylying script--%>
    <%@include file="/WEB-INF/includes/styling.jsp"%>
    <%--end stylying script--%>


</head>

<body>



<%--notification if login failed--%>

<%--<c:if test="${statusModel.mode=='questionUpdate'}">

    <div class="col-lg-3  login_alert">
        <div class="alert alert-danger">
                ${statusModel.message}
        </div>
    </div>

</c:if>--%>

<%--***--%>

<div id="wrapper">


    <div id="page-wrapper" class="gray-bg">

        <%@include file="/WEB-INF/includes/header.jsp"%>

        <div class="row wrapper border-bottom white-bg page-heading">
            <div class="col-sm-4">
                <h2>Forum Sample</h2>
                <ol class="breadcrumb">
                    <li>
                        <a href="view"><span class="label label-primary">Dash Board</span> </a>
                    </li>

                    <li>
                        <a href="../forum/view">Forum</a>
                    </li>

                    <li>
                        <a href="../newQuestion/view">New Question</a>
                    </li>

                </ol>
            </div>

            <c:if test="${statusModel.mode=='questionUpdate'}">

                <div class="col-lg-3  login_alert  alert alert-info alert-dismissable">
                    <button class="close" aria-hidden="true" data-dismiss="alert" type="button">×</button>
                    <strong>${statusModel.message}</strong>
                </div>

            </c:if>


        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="wrapper wrapper-content animated fadeInRight">

                    <div class="ibox-content m-b-sm border-bottom">
                        <div class="p-xs">
                            <div class="pull-left m-r-md">
                                <i class="fa fa-globe text-navy mid-icon"></i>
                            </div>
                            <h2><strong>Dashboard</strong></h2>
                            <span>Your Dashboard Details</span>
                        </div>
                    </div>

                    <div class="ibox-content forum-container">

                        <div class="forum-title">

                            <h3>Posted Topics</h3>
                        </div>


                        <c:forEach items="${questions}" var="data" >

                            <div class="forum-item active">
                                <div class="row">
                                    <div class="col-md-9">
                                        <a target="_blank"  href="../comment/question/${data.id}" class="forum-item-title">${data.topic}</a>
                                        <div class="forum-sub-title">${data.content}
                                        </div>
                                    </div>
                                    <div class="col-md-2 forum-info">
                                        <span class="views-number">
                                                ${data.view}
                                        </span>
                                        <div>
                                            <small>Views</small>
                                        </div>
                                    </div>
                                    <div class="col-md-1 forum-info">
                                        <span class="views-number">
                                                ${data.post}
                                        </span>
                                        <div>
                                            <small>Posts</small>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </c:forEach>


                    </div>
                </div>
            </div>
        </div>

        <%@include file="/WEB-INF/includes/footer.jsp"%>

    </div>
</div>

<%--bind script--%>
<%@include file="/WEB-INF/includes/script.jsp"%>
<%--bind script--%>


</body>


<!-- Mirrored from webapplayers.com/inspinia_admin-v2.4/forum_main.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 01 Feb 2016 10:41:44 GMT -->
</html>
