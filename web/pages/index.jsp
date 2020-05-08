<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>API接口测试平台</title>	
	<link href ="favicon.ico" rel="shortcut icon">  
	<!-- Bootstrap Core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- MetisMenu CSS -->
    <!-- <link href="vendor/metisMenu/metisMenu.min.css" rel="stylesheet"> -->
    <!-- DataTables CSS -->
    <link href="vendor/datatables-plugins/dataTables.bootstrap.css" rel="stylesheet">
    <!-- DataTables Responsive CSS -->
    <!-- <link href="vendor/datatables-responsive/dataTables.responsive.css" rel="stylesheet"> -->
    <!-- Custom CSS -->
    <link href="dist/css/sb-admin-2.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="dist/css/font.css" rel="stylesheet">  
    <!-- Custom Fonts -->
    <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <!-- jQuery -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
    <!-- Metis Menu Plugin JavaScript -->
    <script src="vendor/metisMenu/metisMenu.min.js"></script>
    <!-- DataTables JavaScript -->
    <script src="vendor/datatables/js/jquery.dataTables.min.js"></script>
    <script src="vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
    <!-- <script src="vendor/datatables-responsive/dataTables.responsive.js"></script> -->
    <!-- Custom Theme JavaScript -->
    <script src="dist/js/sb-admin-2.js"></script>
    <!-- Custom JavaScript -->
    <script src="dist/js/close-tab-div.js"></script>
    <script src="dist/js/index-min.js"></script>
</head>
<body>
<!-- 遮罩层 -->
<div id="layerBg"></div>
<!-- Navigation -->
<div id="wrapper">
	<nav class="navbar  navbar-inverse navbar-static-top" role="navigation"
		style="margin-bottom: 0">
		<div class="navbar-header">
			<a class="navbar-brand" href="index.html">接口测试平台</a>
		</div>
		<ul class="nav navbar-top-links navbar-right">
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
					<i class="fa fa-caret-down"></i>
			</a>
				<ul class="dropdown-menu dropdown-user">
					<li><a href="#"><i class="fa fa-user fa-fw"></i> 设置</a></li>
					<li class="divider"></li>
					<li><a href="admin_user_exit" ><i class="fa fa-sign-out fa-fw"></i>
							退出</a></li>
				</ul></li>
		</ul>
		<div class="navbar-default sidebar" role="navigation">
			<div class="sidebar-nav navbar-collapse">
				<ul class="nav" id="side-menu">
					<li class="sidebar-search">
						<div class="input-group custom-search-form">
							<input type="text" class="form-control" placeholder="Search...">
							<span class="input-group-btn">
								<button class="btn btn-default" type="button">
									<i class="fa fa-search"></i>
								</button>
							</span>
						</div>
					</li>
					<li><a href="#" onclick="addProjectTab();return false;"><i class="glyphicon glyphicon-book"></i>
							项目管理</a></li>
					<li><a href="#"><i class="glyphicon glyphicon-user"></i>
							用户管理</a></li>
					<li><a href="#"><i class="glyphicon glyphicon-cog"></i>
							系统设置</a></li>
				</ul>
			</div>
		</div>
	</nav>
</div>
<!-- 工作区域 -->
<div id="nav-bar">
	<ul class="nav nav-tabs nav-ul" id="nav-tabs" role="tablist"></ul>
	<div class="tab-content" id="tab-content"></div>
</div>

</body>
</html>