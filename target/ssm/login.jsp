<%--
  Created by IntelliJ IDEA.
  User: Luke
  Date: 2020/4/11
  Time: 11:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Bootstrap Admin Theme</title>
    <base href="<%=basePath%>">
    <script src="js/jquery-3.4.1.js"></script>
    <script src="My97DatePicker/WdatePicker.js"></script>

    <!-- Bootstrap Core CSS -->
    <link href="<%=basePath%>vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="<%=basePath%>vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<%=basePath%>dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="<%=basePath%>vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>-->
    <!--    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>-->
    <!--    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>-->
    <!--[endif]-->

</head>

<body style="text-align: center">

<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="login-panel panel panel-default">
                <div class="panel-body">
                    <h3>
                        健康体检系统

                    </h3>
                </div>

                <div class="panel-heading">
                    <h3 class="panel-title">请登录</h3>
                </div>
                <div class="panel-body">
                    <form action="user/login" method="post">
                        <fieldset>
                            <div class="form-group">
                                <%--   <input class="form-control" placeholder="E-mail" name="email" type="email" autofocus>--%>
                                <input class="form-control" placeholder="用户名" id="userName" name="userName" value="${cookie.userName.value}" type="text" autofocus autocomplete="off"/>
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="密码" id="userPwd" name="userPwd"  value="${cookie.userPwd.value}" type="password"  />
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value="不记住密码" name="isMemory" id="isMemory"/>是否记住密码？
                                </label>
                            </div>
                            <!-- Change this to a button or input when using this as a form -->
                            <%--                            <a href="index.html" class="btn btn-lg btn-success btn-block">Login</a>--%>
                            <input type="submit" value="Login" class="btn btn-lg btn-success btn-block" />
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- jQuery -->
<script src="<%=basePath%>vendor/jquery/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="<%=basePath%>vendor/bootstrap/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="<%=basePath%>vendor/metisMenu/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="<%=basePath%>dist/js/sb-admin-2.js"></script>

</body>

</html>
