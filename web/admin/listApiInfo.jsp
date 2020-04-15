<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>
<script>
var pid = ${p.id};

function showApiLayer(gid) {
	$('#addAPIDiv').css({
	    "display" : "block"
	});
	$('#layerBg').css({
	    "display" : "block"
	});
	$("#api_gid").val(gid);
	return false;
}

function showGroupLayer() {
	$('#addGroupDiv').css({
	    "display" : "block"
	});
	$('#layerBg').css({
	    "display" : "block"
	});
	return false;
}

function cancel() {
	$('#addAPIDiv').css({
	    "display" : "none"
	});
	$('#addGroupDiv').css({
	    "display" : "none"
	});
	$('#layerBg').css({
	    "display" : "none"
	});
	window.location.reload();
}

function doGroupEdit(id){
	$("#addGroupForm").attr("name","admin_group_update");
	$("#addGroupTitle").html("编辑分组");
	$.ajax({
	    type:"post",
	    dataType:"json",
	    url:"admin_group_edit",
	    data:{
			"id":id
	    },
	    success:function(data){
			var g = data.data;
			$("#gid").val(g.id);
			$("#group_pid").val(pid);
			$("#groupName").val(g.name);
			$('#addGroupDiv').css({
			    "display" : "block"
			});
			$('#layerBg').css({
			    "display" : "block"
			});	
	    },
	    error:function(data){
			alert("系统错误");
	    }
	});
	
	return false;
}   

function submitGroupForm(){
    if (!checkEmpty("groupName","分组名称"))
		return false;
    
	var targetUrl = $("#addGroupForm").attr("name");
	$.ajax({
			type:"post",
		    dataType:"json",
	    url:targetUrl,
	    data: $("#addGroupForm").serialize(),
	    success:function(data){
	        alert(data.msg);
	        window.location.href="admin_apiInfo_list?pid="+pid;
	    },
	    error:function(e){
	        alert("错误！！");
	    }
});	
}  

function doGroupDelete(id){
		var flag = confirm("是否确认删除？");
		if(flag){
		    $.ajax({
			    type:"post",
			    dataType:"json",
			    url:"admin_group_delete",
			    data:{
					"id":id
			    },
			    success:function(data){
					alert(data.msg);
					window.location.href="admin_apiInfo_list?pid="+pid;
			    },
			    error:function(data){
					alert("系统错误");
			    }
			});
		}
}
 
function doApiEdit(id){
	$("#addAPIForm").attr("name","admin_apiInfo_update");
	$("#subTitle").html("编辑接口");
	$.ajax({
	    type:"post",
	    dataType:"json",
	    url:"admin_apiInfo_edit",
	    data:{
			"id":id
	    },
	    success:function(data){
			var api = data.data;
			$("#aid").val(api.id);
			$("#api_gid").val(api.group.id);
			$("#apiName").val(api.apiName);
			$("#url").val(api.url);
			$("#method").val(api.method);
			$("#dataType").val(api.dataType);
			$('#addAPIDiv').css({
			    "display" : "block"
			});
			$('#layerBg').css({
			    "display" : "block"
			});	
	    },
	    error:function(data){
			alert("系统错误");
	    }
	});
	
	return false;
}   

function submitApiForm(){
    if (!checkEmpty("apiName","接口名称"))
		return false;
    if (!checkEmpty("url","请求地址"))
		return false;
    if (!checkEmpty("method","方法"))
		return false;
    if (!checkEmpty("dataType","数据类型"))
		return false;
    
	var targetUrl = $("#addAPIForm").attr("name");
	var formData = $("#addAPIForm").serialize();
	$.ajax({
		type:"post",
		dataType:"json",
		url:targetUrl,
		data:formData,
		success:function(data){
	    alert(data.msg);
	    window.location.href="admin_apiInfo_list?pid="+pid;
	},
	error:function(e){
	    alert("错误！！");
	}
	});	
}  

function doApiDelete(id){
		var flag = confirm("是否确认删除？");
		if(flag){
		    $.ajax({
			    type:"post",
			    dataType:"json",
			    url:"admin_apiInfo_delete",
			    data:{
					"id":id
			    },
			    success:function(data){
					alert(data.msg);
					window.location.href="admin_apiInfo_list?pid="+pid;
			    },
			    error:function(data){
					alert("系统错误");
			    }
			});
		}
} 
 
</script>

<title>接口管理</title>

<div id="layerBg"></div>

<div class="workingArea">
	<div id="workNav">
		<ol>
	     	<li><a href="admin_project_list">所有项目</a></li>
			<li><a href="admin_projectVariable_list?pid=${p.id}">${p.name}</a></li>
			<li class="active">接口管理</li>
	    </ol>
	</div>
	<div id="addGroupDiv" class="panel panel-warning">
		<div class="panel-heading" id="addGroupTitle">新增分组</div>
		<div class="panel-body">
			<form id="addGroupForm" name="admin_group_add" action="">
				<table class="addTable">
					<tr>
						<td>分组名称</td>
						<td>
							<input id="gid" name="gid" type="hidden">
							<input id="group_pid" name="pid" type="hidden" value="${p.id }">
							<input id="groupName" name="groupName" type="text"
							class="form-control"></td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<button type="button" class="btn btn-success"
								onclick="submitGroupForm()">提 交</button>
							<button type="button" class="btn" onclick="cancel()">取 消</button>
						</td>
					</tr>

				</table>
			</form>
		</div>
	</div>
	<div id="addAPIDiv" class="panel panel-warning">
		<div class="panel-heading" id="subTitle">新增接口</div>
		<div class="panel-body">
			<form id="addAPIForm" name="admin_apiInfo_add" action="">
				<table class="addTable">
					<tr>
						<td>接口名称</td>
						<td>
							<input id="aid" name="aid" type="hidden">
							<input id="api_pid" name="pid" type="hidden" value="${p.id}">
							<input id="api_gid" name="gid" type="hidden" value="">
							<input id="apiName" name="apiName" type="text" class="form-control"></td>
					</tr>
					<tr>
						<td>服务地址</td>
						<td><input id="url" name="url" type="text"
							class="form-control"></td>
					</tr>
					<tr>
						<td>方法</td>
						<td><input id="method" name="method" type="text"
							class="form-control"></td>
					</tr>
					<tr>
						<td>数据类型</td>
						<td><select id="dataType" name="dataType">
								<option value="0">其他</option>
								<option value="1">json</option>
						</select></td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<button type="button" class="btn btn-success"
								onclick="submitApiForm()">提 交</button>
							<button type="button" class="btn" onclick="cancel()">取 消</button>
						</td>
					</tr>

				</table>
			</form>
		</div>
	</div>
	<div id="listTitle">
    	<span>接口列表</span>
    	<span><button type="button" class="btn btn-success" onclick="showGroupLayer()">新增分组</button></span>
    </div>	
	<c:if test="${fn:length(gs) <1}">
		<div align="center">没有分组数据</div>
	</c:if>
	<c:forEach items="${gs}" var="g">
		<div id="groupTitle">
			<span>
				分组：${g.name}&nbsp;&nbsp;&nbsp;&nbsp;
				<a onclick="doGroupEdit(${g.id});return false;" class="tda"><span class="glyphicon glyphicon-edit"></span></a>
				<a onclick="doGroupDelete(${g.id});return false;" class="tda"><span class="glyphicon glyphicon-trash"></span></a>
			</span> 				
			<span class="rightSpan">
				<button type="button" class="btn btn-success" onclick="showApiLayer(${g.id})">新增接口</button>
			</span>	
		</div>
		<div class="listDataTableDiv1">		
			<table
				class="table table-striped table-bordered table-hover  table-condensed">
				<thead>
					<tr class="success">
						<th>ID</th>
						<th>接口名称</th>
						<th>服务地址</th>
						<th>方法</th>
						<th>数据类型</th>
						<th>提取器</th>
						<th>断言</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${g.apiByGroup}" var="api">
						<tr>
							<td>${api.id}</td>
							<td>${api.apiName}</td>
							<td>${api.url}</td>
							<td>${api.method}</td>
							<td><c:choose>
									<c:when test="${api.dataType == 0}">普通</c:when>
									<c:when test="${api.dataType == 1}">json</c:when>
								</c:choose></td>
							<td><c:choose>
									<c:when test="${api.hasExtractor == 0}">无</c:when>
									<c:when test="${api.hasExtractor == 1}">有</c:when>
								</c:choose></td>
							<td><c:choose>
									<c:when test="${api.hasAssert == 0}">无</c:when>
									<c:when test="${api.hasAssert == 1}">有</c:when>
								</c:choose></td>
							<td>
								<a href="admin_apiAttribute_list?aid=${api.id }" class="tda"><span class="glyphicon glyphicon-cog"></span></a> 
								<a onclick="doApiEdit(${api.id});return false;" class="tda"><span class="glyphicon glyphicon-edit"></span></a> 
								<a onclick="doApiDelete(${api.id});return false;" class="tda"><span class="glyphicon glyphicon-trash"></span></a>
							</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</c:forEach>
</div>