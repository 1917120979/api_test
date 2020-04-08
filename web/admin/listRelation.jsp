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
	$("#addForm").attr("action","admin_relation_update");
	$("#subTitle").html("编辑关系");
	$.ajax({
	    type:"post",
	    dataType:"json",
	    url:"${pageContext.request.contextPath}/admin_relation_edit",
	    data:{
			"id":id
	    },
	    success:function(data){
			var r = data.rel;
			$("#relName").val(r.relName);
			$("#relValue").val(r.relValue);
			$("#id").val(r.id);
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

<title>关系管理</title>

<div id="layerBg"></div>

<div class="workingArea">
	<h1 class="label label-info">关联管理</h1>
	<br> <br>
	<button type="button" class="btn btn-success" onclick="showLayer()">新增关系</button>
	<br> <br>
	<div id="layer" class="panel panel-warning addApiDiv">
		<div class="panel-heading" id="subTitle">新增关系</div>
		<div class="panel-body">
			<form method="post" id="addForm" action="admin_relation_add">
				<table class="addTable">
					<tr>
						<td>关系名称</td>
						<td><input id="relName" name="relName" type="text"
							class="form-control">
							<input id="id" name="id" type="hidden">
						</td>							
					</tr>
					<tr>
						<td>关系值</td>
						<td><input id="relValue" name="relValue" type="text"
							class="form-control">
						</td>							
					</tr>
					<tr>
						<td colspan="2" align="center">
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
					<th>关系名称</th>		
					<th>关系值</th>
					<th>删除</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${rs}" var="r">

					<tr>
						<td>${r.id}</td>
						<td>${r.relationName}</td>
						<td>${r.relationValue}</td>
						<td><a  onclick="showLayer2(${p.id})" ><span
								class="glyphicon glyphicon-edit"></span></a></td>
						<td><a deleteLink="true"
							href="admin_relation_delete?id=${r.id}"><span
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