<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>
<script>
    $(function() {
	$("#variableName").submit(function() {
	    if (!checkEmpt("variableName"))
		return false;
	});
	var type = ${type};
	$("#pageselect").val(type);
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
    
    function doEdit(id){
	$("#addForm").attr("name","admin_projectVariable_update");
	$("#subTitle").html("编辑项变量");
	$.ajax({
	    type:"post",
	    dataType:"json",
	    url:"admin_projectVariable_edit",
	    data:{
			"id":id
	    },
	    success:function(data){
			var pv = data.data;
			$("#id").val(pv.id);
			$("#pid").val(pv.project.id);
			$("#variableName").val(pv.variableName);
			$("#variableValue").val(pv.variableValue);
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
    
    function doDelete(id,pid){
		var flag = confirm("是否确认删除？");
		if(flag){
		    $.ajax({
			    type:"post",
			    dataType:"json",
			    url:"admin_projectVariable_delete",
			    data:{
					"id":id
			    },
			    success:function(data){
					alert(data.msg);
					window.location.href="admin_projectVariable_list?pid="+pid;
			    },
			    error:function(data){
					alert("系统错误");
			    }
			});
		}
    }
    
    function submitForm(pid){
		var targetUrl = $("#addForm").attr("name");
	    $.ajax({
			type:"post",
		    dataType:"json",
	        url:targetUrl,
	        data: $("#addForm").serialize(),
	        success:function(data){
	            alert(data.msg);
	            window.location.href="admin_projectVariable_list?pid="+pid;
	        },
	        error:function(e){
	            alert("错误！！");
	        }
	    });	
    }  
    function s_click(obj,pid){
		var num = $("#pageselect").val();
		if(num == 0){
		    window.location.href="admin_projectVariable_list?pid="+pid;
		}
		if(num == 1){
		    window.location.href="admin_projectVariable_list?pid="+pid+"&type=1";
		}
		if(num == 2){
		    window.location.href="admin_projectVariable_list?pid="+pid+"&type=2";
		}
		if(num == 3){
		    window.location.href="admin_projectVariable_list?pid="+pid+"&type=3";
		}
		    
    }
    function groupManagement(pid){
		window.location.href="admin_group_list?pid="+pid;
    }
    function apiManagement(pid){
	window.location.href="admin_group_list?pid="+pid;
	}
    function apiExecute(pid){
	window.location.href="admin_apiExecute_add?pid="+pid;
	}
    function viewResult(pid){
	window.location.href="admin_projectResult_list?pid="+pid;
	}
</script>

<title>项目管理</title>

<div id="layerBg"></div>

<div class="workingArea">
	<ol class="breadcrumb">
      <li><a href="admin_project_list">所有项目</a></li>
      <li><a href="admin_projectVariable_list?pid=${p.id}">${p.name}</a></li>
      <li class="active">项目详情</li>
    </ol>
    
    <div class="buttonDiv"> 
    	<button type="button" class="btn btn-success" onclick="groupManagement(${p.id})">分组管理</button>	
		<button type="button" class="btn btn-success" onclick="apiManagement(${p.id})">接口管理</button>	
		<button type="button" class="btn btn-success" onclick="apiExecute(${p.id}">接口执行</button>
		<button type="button" class="btn btn-success" onclick="viewResult(${p.id}">查看结果</button>
	</div>
	<br>
	<div class="select">
		<span>用户变量列表</span>
		<span>查询:</span>
   		<select id="pageselect" onchange="s_click(this,${p.id})" > 
   			<OPTION value="0">全部</OPTION> 
			<OPTION value="1">项目配置</OPTION> 
			<OPTION value="2">公共参数</OPTION> 
			<OPTION value="3">接口关联</OPTION> 
		</select> 
		<button type="button" class="btn btn-success" onclick="showAddDiv()">新增变量</button>
	</div>
    
    <br>
	<div id="layer" class="panel panel-warning addProjDiv">
		<div class="panel-heading" id="subTitle">新增属性</div>
		<div class="panel-body">
			<form id="addForm" name="admin_projectVariable_add" action="">
				<table class="addTable">
					<tr>
						<td>变量名称</td>
						<td>
							<input id="id" name="id" type="hidden">
							<input id="pid" name="pid" value = "${p.id }" type="hidden">
							<input id="variableName" name="variableName" type="text" class="form-control">							
						</td>
							
					</tr>
					<tr>
						<td>变量值</td>
						<td>
							<input id="variableValue" name="variableValue" type="text" class="form-control">					
						</td>
							
					</tr>
					<tr>
						<td>类型</td>
						<td>
							<select id="aid" name="aid">
								<option value="0">项目配置</option>
								<option value="-1" >公共参数</option>								
							</select>					
						</td>
							
					</tr>
					<tr>
						<td colspan="2" align="center">
							<button type="button" class="btn btn-success" onclick="submitForm(${p.id})">提 交</button>
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
					<th>变量名称</th>
					<th>变量内容</th>
					<th>类型</th>
					<th>操作</th>	
				</tr>
			</thead>
			<tbody>
				<c:if test="${fn:length(pvs) <1}">
						<tr>
							<td colspan="4" align="center">没有数据</td>
						</tr>
				</c:if>
				<c:forEach items="${pvs}" var="pv">
					<tr>
						<td>${pv.id}</td>
						<td>${pv.variableName}</td>
						<td>${pv.variableValue}</td>
						<td>
							<c:choose>
								<c:when test="${pv.apiInfo.id == 0}">项目配置</c:when>
								<c:when test="${pv.apiInfo.id == -1}">公共参数</c:when>
								<c:when test="${pv.apiInfo.id > 0}">接口关联：${pv.apiInfo.apiName}</c:when>
							</c:choose>
						</td>
						<td>
							<c:if test="${pv.apiInfo.id == 0 }">
								<a onclick="doEdit(${pv.id});return false;" class="tda"><span class="glyphicon glyphicon-edit"></span></a>
							</c:if>						
							<a onclick="doDelete(${pv.id},${p.id});return false;" class="tda"><span class="glyphicon glyphicon-trash"></span></a>
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