<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>接口测试平台-用户注册</title>

    <!-- Bootstrap Core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- MetisMenu CSS -->
    <link href="vendor/metisMenu/metisMenu.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="dist/css/sb-admin-2.css" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <!-- jQuery -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
    <!-- Metis Menu Plugin JavaScript -->
    <script src="vendor/metisMenu/metisMenu.min.js"></script>
    <!-- Custom Theme JavaScript -->
    <script src="dist/js/sb-admin-2.js"></script>
    <script src="dist/js/header-check.js"></script>
    <script>
    	function register(){
	    	if (!checkEmpty("username", "用户名"))
	    		return false;
	        if (!checkEmpty("password", "密码"))
	    		return false;
	        var pwd = $("#password").val();
	        var pwd2 = $("#password2").val();
	        if(pwd != pwd2){
	            alert("两次密码不一致！");
	            return false;
	        }else if(pwd.length < 6){
	            alert("密码长度少于6位！");
	            return false;
	        }
	        $.ajax({
		    	type : "post",
		    	dataType : "json",
		    	url : "admin_user_register",
		    	data : $("#registerForm").serialize(),
		    	success : function(data) {
		    	    if(data.code == 0){
		    			alert(data.msg);
		    			window.location.href = "admin_user_loginPage";
		    			return;
		    	    }
		    	    if(data.code == 403){
		    			alert(data.msg);
		    			return;
		    	    }
		    		alert(data.msg);    		
		    	},
		    	error : function() {
		    	    alert("错误！！");
		    	}
	        });
	        return false; 
    	}
    </script>
</head>

<body>
    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">欢迎注册</h3>
                    </div>
                    <div class="panel-body">
                        <form id="registerForm" action="">
                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control" placeholder="用户名" id="username" name="username" type="text" autofocus>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="密码" id="password" name="password" type="password" autocomplete="off">
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="确认密码" id="password2" name="password" type="password" autocomplete="off">
                                </div>
                                <a href="#" class="btn btn-lg btn-success btn-block" onclick="register();return false;">注册</a>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>

</html>