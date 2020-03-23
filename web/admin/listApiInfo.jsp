<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>
<script>
    $(function() {
	$("#addForm").submit(function() {
	    if (!checkEmpt("name"))
		return false;
	});
    });

    function showLayer() {
	$('#layer').css({
	    "display" : "block"
	});
	$('#layerBg').css({
	    "display" : "block"
	});
	return false;
    }

    function hideLayer() {
	$('#layer').css({
	    "display" : "none"
	});
	$('#layerBg').css({
	    "display" : "none"
	});
	window.location.reload();
    }
    
    function showLayer2(id){
	$("#addForm").attr("action","admin_apiInfo_update?id="+id);
	$("#subTitle").html("编辑接口");
	$.ajax({
	    type:"post",
	    dataType:"json",
	    url:"${pageContext.request.contextPath}/admin_apiInfo_edit",
	    data:{
			"id":id
	    },
	    success:function(data){
			var api = data.api;
			$("#apiName").val(api.apiName);
			$("#method").val(api.method);
			$("#portNum").val(api.portNum);
			$("#path").val(api.path);
			$("#protocol").val(api.protocol);
			$("#serverName").val(api.serverName);
			$("#dataType").val(api.dataType);
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
 
</script>

<title>接口管理</title>

<div id="layerBg"></div>

<div class="workingArea">
   <ol class="breadcrumb">
      <li><a href="admin_apiProject_list">所有项目</a></li>
      <li><a href="admin_apiInfo_list?pid=${ap.id}">${ap.proName}</a></li>
      <li class="active">接口管理</li>
    </ol>

	<button type="button" class="btn btn-success" onclick="showLayer()">新增接口</button>
	<br> <br>
	<div id="layer" class="panel panel-warning addApiDiv">
		<div class="panel-heading" id="subTitle">新增接口</div>
		<div class="panel-body">
			<form method="post" id="addForm" action="admin_apiInfo_add">
				<table class="addTable">
					<tr>
						<td>接口名称</td>
						<td><input id="apiName" name="apiName" type="text"
							class="form-control"></td>
					</tr>
					<tr>
						<td>协议</td>
						<td><input id="protocol" name="protocol" type="text"
							class="form-control"></td>
					</tr>
					<tr>
						<td>服务</td>
						<td><input id="serverName" name="serverName" type="text"
							class="form-control"></td>
					</tr>
					<tr>
						<td>端口</td>
						<td><input id="portNum" name="portNum" type="text"
							class="form-control"></td>
					</tr>
					<tr>
						<td>方法</td>
						<td><input id="method" name="method" type="text"
							class="form-control"></td>
					</tr>
					<tr>
						<td>路径</td>
						<td><input id="path" name="path" type="text"
							class="form-control">
						</td>
					</tr>
					<tr>
						<td>数据类型</td>
						<td><input id="path" name="path" type="text"
							class="form-control">
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input id="pid" name="pid" type="hidden" value="${ap.id}">
							<button type="submit" class="btn btn-success">提 交</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="btn" onclick="hideLayer()">取消</button>
						</td>
					</tr>
					
				</table>
			</form>
		</div>
	</div>
	
	<div class="listDataTableDiv1">
		<table
			class="table table-striped table-bordered table-hover  table-condensed">
			<thead>
				<tr class="success">
					<th>ID</th>
					<th>接口名称</th>
					<th>协议</th>
					<th>服务</th>
					<th>端口</th>
					<th>方法</th>
					<th>路径</th>
					<th>数据类型</th>
					<th>属性管理</th>
					<!--                     <th>执行</th> -->
					<th>编辑</th>
					<th>删除</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ais}" var="api">

					<tr>
						<td>${api.id}</td>
						<td>${api.apiName}</td>
						<td>${api.protocol}</td>
						<td>${api.serverName}</td>
						<td>${api.portNum}</td>
						<td>${api.method}</td>
						<td>${api.path}</td>
						<td>${api.dataType}</td>
						<td><a href="admin_apiAttribute_list?apiId=${api.id}"><span class="glyphicon glyphicon-th-list"></span></a></td>
						<%--                     <td><a href="admin_product_list?cid=${api.id}"><span class="glyphicon glyphicon-shopping-cart"></span></a></td>                    --%>
						<td><a  onclick="showLayer2(${api.id})" ><span
								class="glyphicon glyphicon-edit"></span></a></td>
						<td><a deleteLink="true"
							href="admin_apiInfo_delete?id=${api.id}"><span
								class="   glyphicon glyphicon-trash"></span></a></td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<div class="pageDiv">
		<%@include file="../include/admin/adminPage.jsp"%>
	</div>
</div>