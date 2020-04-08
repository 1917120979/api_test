<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>
<script>
    $(function() {
	$("#configName").submit(function() {
	    if (!checkEmpt("configName"))
		return false;
	});
    });
	
    $(function() {
	$("#configValue").submit(function() {
	    if (!checkEmpt("configValue"))
		return false;
	});
    });
    
    function showAddDiv() {
	$('#layer').css({
	    "display" : "block"
	});
	$('#layerBg').css({
	    "display" : "block"
	});
	return false;
    }

    function hide() {
	$('#layer').css({
	    "display" : "none"
	});
	$('#layerBg').css({
	    "display" : "none"
	});
	window.location.reload();
    }
    
    function showEditDiv(id){
	$("#addForm").attr("action","admin_config_update");
	$("#subTitle").html("编辑项目");
	$.ajax({
	    type:"post",
	    dataType:"json",
	    url:"${pageContext.request.contextPath}/admin_config_edit",
	    data:{
			"id":id
	    },
	    success:function(data){
			var c = data.config;
			$("#id").val(c.id);
			$("#pid").val(c.project.id);
			$("#configName").val(c.configName);
			$("#configValue").val(c.configValue);
	    },
	    error:function(data){
			alert("系统错误");
	    }
	});
	$('#layer').css({
	    "display" : "block"
	});
	$('#layerBg').css({
	    "display" : "block"
	});
	
	return false;
    }
 	
    function deleteAll(pid){
		window.location.href= "admin_variable_delete?pid="+pid;
    }
    
    function apiManagement(pid){
		window.location.href= "admin_apiInfo_list?pid="+pid;
    }
</script>

<title>项目管理</title>

<div id="layerBg"></div>

<div class="workingArea">
	<ol class="breadcrumb">
      <li><a href="admin_project_list">所有项目</a></li>
      <li><a href="admin_projectDetail_list?pid=${project.id}">${project.name}</a></li>
      <li class="active">项目管理</li>
    </ol>
	<div id="layer" class="panel panel-warning addProjDiv">
		<div class="panel-heading" id="subTitle">新增配置</div>
		<div class="panel-body">
			<form method="post" id="addForm" action="admin_config_add">
				<table class="addTable">
					<tr>
						<td>配置名称</td>
						<td>
							<input id="id" name="id" type="hidden">
							<input id="pid" name="pid" value = "${project.id }" type="hidden">
							<input id="configName" name="configName" type="text" class="form-control">							
						</td>
							
					</tr>
					<tr>
						<td>配置内容</td>
						<td>
							<input id="configValue" name="configValue" type="text" class="form-control">					
						</td>
							
					</tr>
					<tr>
						<td colspan="2" align="center">
							<button type="submit" class="btn btn-success">提 交</button>
							<button type="button" class="btn" onclick="hide()">取消</button>
						</td>
					</tr>
					
				</table>
			</form>
		</div>
	</div>
	<h1 class="label label-info">项目配置列表</h1> 
	<button type="button" class="btn btn-success" onclick="showAddDiv()">新增配置</button>
	<br>
	<br>
	<div class="listDataTableDiv1">
		<table
			class="table table-striped table-bordered table-hover  table-condensed">
			<thead>
				<tr class="success">
					<th>ID</th>
					<th>配置名称</th>
					<th>配置内容</th>
					<th>操作</th>		
				</tr>
			</thead>
			<tbody>
				<c:if test="${fn:length(confs) <1}">
						<tr>
							<td colspan="4" align="center">没有数据</td>
						</tr>
				</c:if>
				<c:forEach items="${confs}" var="c">
					<tr>
						<td>${c.id}</td>
						<td>${c.configName}</td>
						<td>${c.configValue}</td>
						<td>
							<a onclick="showEditDiv(${c.id})" class="tda"><span class="glyphicon glyphicon-edit"></span></a>
							<a deleteLink="true" href="admin_config_delete?id=${c.id}&pid=${c.project.id }" class="tda"><span class="glyphicon glyphicon-trash"></span></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<h1 class="label label-info">用户变量列表</h1>
	<button type="button" class="btn btn-success" onclick="deleteAll(${project.id})">清空</button>
	<br>
	<br>
	<div class="listDataTableDiv1">
		<table
			class="table table-striped table-bordered table-hover  table-condensed">
			<thead>
				<tr class="success">
					<th>ID</th>
					<th>变量名称</th>
					<th>变量内容</th>
					<th>所属接口</th>	
				</tr>
			</thead>
			<tbody>
				<c:if test="${fn:length(variables) <1}">
						<tr>
							<td colspan="4" align="center">没有数据</td>
						</tr>
				</c:if>
				<c:forEach items="${variables}" var="v">
					<tr>
						<td>${v.id}</td>
						<td>${v.varName}</td>
						<td>${v.varValue}</td>
						<td>${v.apiInfo.apiName}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div>
		<button type="button" class="btn btn-success" onclick="apiManagement(${project.id})">接口管理</button>
		<button type="button" class="btn btn-success" onclick="showAddDiv()">接口执行</button>
		<button type="button" class="btn btn-success" onclick="showAddDiv()">查看执行结果</button>
		<button type="button" class="btn btn-success" onclick="showAddDiv()">查看结果明细</button>
	</div>
	
</div>