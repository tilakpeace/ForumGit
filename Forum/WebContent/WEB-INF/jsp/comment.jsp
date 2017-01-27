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

    <title>Comment</title>

    <!-- 	base url -->

    <base href="${pageContext.request.contextPath}/comment/"/>

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
            <c:if test="${statusModel.mode=='commentUpdate'}">

                <div class="col-lg-3  login_alert  alert alert-info alert-dismissable">
                    <button class="close" aria-hidden="true" data-dismiss="alert" type="button">×</button>
                    <strong>${statusModel.message}</strong>
                </div>

            </c:if>


            <div class="col-sm-4">
                <h2>Question</h2>
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

                    <div class="ibox-content forum-post-container">
                        <div class="forum-post-info">
                            <h4><span class="text-navy"><i class="fa fa-globe"></i> Topic</span> - <span
                                    class="text-muted">${question.topic}</span></h4>
                        </div>
                        <div class="media">
                            <a class="forum-avatar" href="#">
                                <img src="../img/a1.jpg" class="img-circle" alt="image">
                                <div class="author-info">
                                    <strong>posted:</strong>${question.createdDate}<br/>
                                </div>
                            </a>
                            <div class="media-body">

                                ${question.content}
                                <br/><br/>
                                - ${question.profile.name}
                            </div>
                        </div>

                        <c:forEach items="${comment}" var="data">

                            <div class="media">
                                <a class="forum-avatar" href="#">
                                    <img src="../img/a2.jpg" class="img-circle" alt="image">
                                    <div class="author-info">
                                        <strong>posted:</strong>${data.createdDate}<br/>
                                    </div>
                                </a>
                                <div class="media-body">
                                    ${data.comment}
                                    <br/><br/>
                                    - ${data.profile.name}
                                </div>
                            </div>


                        </c:forEach>






                        <div class="media">

                            <form method="post" action="add" >

                                <div class="hr-line-dashed"></div>

                                <div class="form-group"><label class="col-sm-3 control-label">Write Comment</label>

                                    <div class="col-sm-12">

                                        <div id="summernote"><p></p></div>

                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-4 col-sm-offset-2">
                                       <%-- <button class="btn btn-white" type="submit">Cancel</button>--%>
                                        <button id="button_comment" class="btn btn-primary" type="submit">Post Your Comment
                                        </button>
                                       <%-- <button class="btn btn-primary" type="button">button test</button>--%>
                                        <input type="hidden" name="comment" id="hidden_test" >
                                        <input type="hidden" name="questionId" value="${question.id}" id="hidden_test" >


                                    </div>
                                </div>
                            </form>


                        </div>
                        <%-- <div class="form-group"><label class="col-sm-2 control-label">Content</label>

                             <div class="col-sm-10">

                                 <div id="summernote"><p>Hello Summernote</p></div>

                             </div>
                         </div>
 --%>
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

    $('#summernote').summernote({
        height: 300,                 // set editor height
        minHeight: null,             // set minimum height of editor
        maxHeight: null,             // set maximum height of editor
        focus: true,                  // set focus to editable area after initializing summernote
        required:true
    });

    /*$('#summernote').summernote();
    $('.note-codable').attr('required', true).attr('data-summernote', true)*/


    $( "#button_comment" ).click(function() {

        var markupStr = $('#summernote').summernote('code');

        $('#hidden_test').val(markupStr);
        var val = $('#hidden_test').val();

    });


</script>

</body>


<!-- Mirrored from webapplayers.com/inspinia_admin-v2.4/forum_main.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 01 Feb 2016 10:41:44 GMT -->
</html>
