<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (session.getAttribute("userName") != null) {
        String redirectURL = "index.jsp";
        response.sendRedirect(redirectURL);
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>xxx管理系统</title>

    <!-- Bootstrap core CSS -->
    <link href="../../dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="../../assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../../assets/css/signin.css" rel="stylesheet">


    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]>
    <script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="../../assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<div class="container">

    <form id="form-login" class="form-signin">
        <h2 class="form-signin-heading">请登陆</h2>
        <label for="inputEmail" class="sr-only">Email</label>
        <input type="email" name="email" id="inputEmail" class="form-control" placeholder="Email" required
               autofocus>
        <label for="inputPassword" class="sr-only">密码</label>
        <input type="password" name="password" id="inputPassword" class="form-control" placeholder="密码" required>

        <div class="checkbox">
            <label>
                <input type="checkbox" value="remember-me"> 记住我
            </label>
        </div>
        <button id="login-btn" class="btn btn-lg btn-primary btn-block" type="submit">登陆</button>
        <button id="show-register" class="btn btn-default center-block">
            <span class="glyphicon glyphicon-menu-down" aria-hidden="true"></span>
        </button>
    </form>

    <div class="row centered-form">
        <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">你现在可以注册!
                        <small>完全免费!</small>
                    </h3>
                </div>
                <div class="panel-body">
                    <form id="form-register" role="form">
                        <div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="text" name="name" id="name" class="form-control input-sm"
                                           placeholder="姓名">
                                </div>
                            </div>
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="text" name="idNumber" id="idNumber" class="form-control input-sm"
                                           placeholder="Id 号码">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <input type="email" name="email" id="email" class="form-control input-sm"
                                   placeholder="Email">
                        </div>

                        <div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                            <input type="radio" name="role" value="customer" aria-label="...">
                                    </span>
                                    <button class="btn btn-default disabled" type="button">Customer</button>
                                </div>
                                <div class="input-group">
                                    <span class="input-group-addon">
                                            <input type="radio" name="role" value="admin" aria-label="...">
                                    </span>
                                    <button class="btn btn-default disabled" type="button">Admin</button>
                                </div><!-- /input-group -->
                            </div><!-- /.col-lg-6 -->

                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="input-group">
                                      <span class="input-group-addon">
                                        <input type="radio" name="gender" value="男">
                                      </span>
                                    <button class="btn btn-default disabled" type="button">男</button>
                                </div>
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <input type="radio" name="gender" value="女">
                                      </span>
                                    <button class="btn btn-default disabled" type="button">女</button>
                                </div>
                                <!-- /input-group -->
                            </div><!-- /.col-lg-6 -->
                        </div><!-- /.row -->


                        <div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="text" name="age" id="age" class="form-control input-sm"
                                           placeholder="年龄">
                                </div>
                            </div>
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="password" name="password" id="password" class="form-control input-sm"
                                           placeholder="密码">
                                </div>
                            </div>
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="password" name="password_confirmation" id="password_confirmation"
                                           class="form-control input-sm" placeholder="密码确认">
                                </div>
                            </div>
                        </div>

                        <input type="submit" value="注册" class="btn btn-info btn-block">

                    </form>
                </div>
            </div>
        </div>
    </div>

</div> <!-- /container -->


<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../../assets/js/vendor/jquery.min.js"></script>
<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
<!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>-->
<%--<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>--%>
<script src="../../assets/js/index_login.js"></script>
</body>
</html>
