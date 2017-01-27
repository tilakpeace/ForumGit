<%@include file="/WEB-INF/includes/checkUser.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Forum</title>

    <!-- 	base url -->

    <base href="${pageContext.request.contextPath}/forum/"/>

    <!--  End	base url -->


    <%--stylying script--%>
    <%@include file="/WEB-INF/includes/styling.jsp"%>
    <%--end stylying script--%>


</head>

<body>

<script>


</script>

<div id="wrapper">


    <div id="page-wrapper" class="gray-bg">

        <%@include file="/WEB-INF/includes/header.jsp"%>

        <div class="row wrapper border-bottom white-bg page-heading">
            <div class="col-sm-4">
                <h2>Forum Sample</h2>
                <ol class="breadcrumb">
                    <li>
                        <a href="../dashboard/view">Dash Board</a>
                    </li>

                    <li>
                        <a href="view"><span class="label label-primary">Forum</span> </a>
                    </li>

                    <li>
                        <a href="../newQuestion/view">New Question</a>
                    </li>


                </ol>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="wrapper wrapper-content animated fadeInRight">

                    <div class="ibox-content m-b-sm border-bottom">
                        <div class="row">
                            <div class="col-md-6">
                                <h2>Choose Topics</h2>
                                <div id="custom-search-input">
                                    <div class="input-group col-md-12">
                                        <input type="text" class="form-control input-lg"  name="topic" id="topic" placeholder="Topic" required=""/>
                                            <span class="input-group-btn">
                                                <button class="btn btn-info btn-lg" id="topic_search" type="button">
                                                    <i class="glyphicon glyphicon-search"></i>
                                                </button>
                                            </span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="ibox-content forum-container">

                        <div class="forum-title">
                            <div class="pull-right forum-desc">
                                <samll>Total posts: 320,800</samll>
                            </div>
                            <h3>Posted Topics</h3>



                        </div>


                        <c:forEach items="${questions}" var="data" >

                            <div class="forum-item active">
                                <div class="row">
                                    <div class="col-md-9">
                                        <a  target="_blank"  href="../comment/question/${data.id}" class="forum-item-title">${data.topic}</a>
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


<script>

    $( "#topic_search" ).click(function() {

        var val = $('#topic').val();
       // alert(val.length);

        var url;
        if (val.length!=0){

            url="search/"+val;
        }

        else {

            url="view";
        }

        window.location.href = url;

    });

</script>

</body>


<!-- Mirrored from webapplayers.com/inspinia_admin-v2.4/forum_main.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 01 Feb 2016 10:41:44 GMT -->
</html>
