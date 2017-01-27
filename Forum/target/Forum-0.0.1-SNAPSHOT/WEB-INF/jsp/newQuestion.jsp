<%@include file="/WEB-INF/includes/checkUser.jsp"%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>New Question</title>

    <!-- 	base url -->

    <base href="${pageContext.request.contextPath}/newQuestion/"/>

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
                        <a href="../forum/view">Forum</a>
                    </li>

                    <li>
                        <a href="view"><span class="label label-primary">New Question</span> </a>
                    </li>

                </ol>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="wrapper wrapper-content animated fadeInRight">


                    <div class="col-lg-12">
                        <div class="ibox float-e-margins">
                            <div class="ibox-title">
                                <h5>All form elements
                                    <small>With custom checbox and radion elements.</small>
                                </h5>
                                <div class="ibox-tools">
                                    <a class="collapse-link">
                                        <i class="fa fa-chevron-up"></i>
                                    </a>
                                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                        <i class="fa fa-wrench"></i>
                                    </a>
                                    <ul class="dropdown-menu dropdown-user">
                                        <li><a href="#">Config option 1</a>
                                        </li>
                                        <li><a href="#">Config option 2</a>
                                        </li>
                                    </ul>
                                    <a class="close-link">
                                        <i class="fa fa-times"></i>
                                    </a>
                                </div>
                            </div>
                            <div class="ibox-content">
                                <form method="post" action="add"   class="form-horizontal">
                                    <div class="form-group"><label class="col-sm-2 control-label">Topic</label>

                                        <div class="col-sm-10"><input type="text" name="topic" class="form-control"></div>
                                    </div>

                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group"><label class="col-sm-2 control-label">Content</label>

                                        <div class="col-sm-10">

                                            <div id="summernote"><p></p></div>

                                        </div>
                                    </div>


                                    <div class="form-group">
                                        <div class="col-sm-4 col-sm-offset-2">
                                            <button id="button_test"  class="btn btn-primary" type="submit">Save</button>
                                            <input type="hidden" name="content" id="hidden_test" class="form-control">

                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>


                </div>
            </div>
        </div>

        <%@include file="/WEB-INF/includes/footer.jsp"%>

    </div>
</div>

<!-- Mainly scripts -->

<%--bind script--%>
<%@include file="/WEB-INF/includes/script.jsp"%>
<%--bind script--%>

<script>
   /* $(document).ready(function() {
        $('#summernote').summernote();
    });
*/
    $('#summernote').summernote({
        height: 300,                 // set editor height
        minHeight: null,             // set minimum height of editor
        maxHeight: null,             // set maximum height of editor
        focus: true                  // set focus to editable area after initializing summernote
    });


  /* $(document).ready(function() {
       $('#summernote').summernote({
           onKeyup: function(e) {
               var markupStr = $('#summernote').summernote('code');

               alert("testingldfkfjsdlfjs");
               $('#hidden_test').val(markupStr);
           },
           height: 300,
       });
   });*/



   $( "#button_test" ).click(function() {

       var markupStr = $('#summernote').summernote('code');

       $('#hidden_test').val(markupStr);
       var val = $('#hidden_test').val();

   });



</script>

</body>


<!-- Mirrored from webapplayers.com/inspinia_admin-v2.4/forum_main.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 01 Feb 2016 10:41:44 GMT -->
</html>
