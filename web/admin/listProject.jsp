<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>
<script>
    $(function() {
	$("#addForm").submit(function() {
	    if (!checkEmpt("name"))
			return false;
	    if (!checkEmpt("isSign"))
			return false;
	    if (!checkEmpt("isEncript"))
			return false;
	    return true;
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

    function cancel() {
	$('#layer').css({
	    "display" : "none"
	});
	$('#layerBg').css({
	    "display" : "none"
	});
	window.location.reload();
    }
    
    function doEdit(id){
	$("#addForm").attr("name","admin_project_update");
	$("#subTitle").html("编辑项目");
	$.ajax({
	    type:"post",
	    dataType:"json",
	    url:"${pageContext.request.contextPath}/admin_project_edit",
	    data:{
			"id":id
	    },
	    success:function(data){
			var p = data.p;
			$("#name").val(p.name);
			$("#id").val(p.id);
			$("#isSign").val(p.isSign);
			$("#isEncript").val(p.isEncript);
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
    
    function doDelete(id){
		var flag = confirm("是否确认删除？");
		if(flag){
		    $.ajax({
			    type:"post",
			    dataType:"json",
			    url:"admin_project_delete",
			    data:{
					"id":id
			    },
			    success:function(data){
					alert(data.msg);
					window.location.href="admin_project_list";
			    },
			    error:function(data){
					alert("系统错误");
			    }
			});
		}
    }
    
    function submitForm(){
		var targetUrl = $("#addForm").attr("name");
	    $.ajax({
			type:"post",
		    dataType:"json",
	        url:targetUrl,
	        data: $("#addForm").serialize(),
	        success:function(data){
	            alert(data.msg);
	            window.location.href="admin_project_list";
	        },
	        error:function(e){
	            alert("错误！！");
	        }
	    });	
    }
</script>

<title>项目管理</title>

<div id="layerBg"></div>

<div class="workingArea">
	<h1 class="label label-info">项目管理</h1>
	<br> <br>
	<button type="button" class="btn btn-success" onclick="showAddDiv()">新增项目</button>
	<br> <br>	
	<div id="layer" class="panel panel-warning addProjDiv">
		<div class="panel-heading" id="subTitle">新增项目</div>
		<div class="panel-body">
			<form method="post" id="addForm" name="admin_project_add" action="">
				<table class="addTable">
					<tr>
						<td>项目名称</td>
						<td>
							<input id="id" name="id" type="hidden">
							<input id="name" name="name" type="text" class="form-control">							
						</td>
							
					</tr>
					<tr>
						<td>签名</td>
						<td>
							<select id="isSign" name="isSign">
								<option value="0">不签名</option>
								<option value="1" >签名：网关</option>
								<option value="2" >签名：平台</option>
								<option value="3" >签名：APP</option>								
							</select>						
						</td>
							
					</tr>
					<tr>
						<td>加密</td>
						<td>
							<select id="isEncript" name="isEncript">
								<option value="0">不加密</option>
								<option value="1">加密</option>								
							</select>							
						</td>
							
					</tr>
					<tr>
						<td colspan="2" align="center">
							<button type="button" class="btn btn-success" onclick="submitForm()">提 交</button>
							<button type="button" class="btn" onclick="cancel()">取 消</button>
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
					<th>项目名称</th>
					<th>是否签名</th>
					<th>是否加密</th>
					<th>操作</th>		
				</tr>
			</thead>
			<tbody>		
				<c:if test="${fn:length(ps) <1}">
						<tr>
							<td colspan="4" align="center">没有数据</td>
						</tr>
				</c:if>
				<c:forEach items="${ps}" var="p">

					<tr>
						<td>${p.id}</td>
						<td>${p.name}</td>
						<td>
							<c:choose>
								<c:when test="${p.isSign == 0}">不签名</c:when>
								<c:when test="${p.isSign == 1}">签名：网关</c:when>
								<c:when test="${p.isSign == 2}">签名：平台</c:when>
								<c:when test="${p.isSign == 3}">签名：APP</c:when>
							</c:choose>
						</td>
						<td>
							<c:choose>
								<c:when test="${p.isEncript == 0}">不加密</c:when>
								<c:when test="${p.isEncript == 1}">加密</c:when>
							</c:choose>
						</td>
						<td>
							<a href="admin_projectDetail_list?pid=${p.id }" class="tda"><span class="glyphicon glyphicon-cog"></span></a>
							<a onclick="doEdit(${p.id});return false;" class="tda"><span class="glyphicon glyphicon-edit"></span></a>
							<a onclick="doDelete(${p.id});return false;" class="tda"><span class="glyphicon glyphicon-trash"></span></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<div class="pageDiv">
		<%@include file="../include/admin/adminPage.jsp"%>
	</div>
</div>