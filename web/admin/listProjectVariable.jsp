<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>
<script>
	var pid = ${p.id};
	var type = ${type};
	
	$(function(){
	    $("#pageSelect").val(type);	    
	});
	
    function showAddVarDiv() {
		$('#addVarDiv').css({
		    "display" : "block"
		});
		$('#layerBg').css({
		    "display" : "block"
		});
		return false;
    }

    function cancel() {
		$('#addVarDiv').css({
		    "display" : "none"
		});
		$('#layerBg').css({
		    "display" : "none"
		});
	window.location.reload();
    }
    
    function doEdit(id){
		$("#addForm").attr("name","admin_projectVariable_update");
		$("#subTitle").html("编辑变量");
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
				$("#variableName").val(pv.variableName);
				$("#variableValue").val(pv.variableValue);
				$("#type").val(pv.type);
				$('#addVarDiv').css({
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
    
    function doDelete(id){
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
    
    function submitForm(){
		if (!checkEmpty("variableName","变量名称"))
			return false;
		if (!checkEmpty("variableValue","变量值"))
			return false;
		if (!checkEmpty("type","类型"))
			return false;
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
    
    function selectClick(obj){
		var num = $("#pageSelect").val();
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
    
    function groupManagement(){
		window.location.href="admin_group_list?pid="+pid;
    }
    function managementAPI(){
		window.location.href="admin_apiInfo_list?pid="+pid;
	}
    function executeAPI(){
		window.location.href="admin_apiExecute_add?pid="+pid;
	}
    function viewResult(){
		window.location.href="admin_projectResult_list?pid="+pid;
	}
</script>

<title>项目配置</title>

<div id="layerBg"></div>
<div class="workingArea">
	<div id="workNav">
		<ol>
	      <li><a href="admin_project_list">所有项目</a></li>
     	  <li class="active">${p.name}</li>
	    </ol>
	</div>
	<div id="listTitle">
		<span>
			项目变量列表 &nbsp;&nbsp;&nbsp;&nbsp;查询:
			<select id="pageSelect" onchange="selectClick(this)" > 
	   			<OPTION value="0">全部</OPTION> 
				<OPTION value="1">项目配置</OPTION> 
				<OPTION value="2">公共参数</OPTION> 
				<OPTION value="3">接口关联</OPTION> 
			</select>
			<button type="button" class="btn btn-success" onclick="showAddVarDiv()">新增变量</button> 	
		</span>				
		<span class="floatRight">
			<button type="button" class="btn btn-success" onclick="managementAPI()">接口管理</button>
			<button type="button" class="btn btn-success" onclick="executeAPI()">接口执行</button>
			<button type="button" class="btn btn-success" onclick="viewResult()">查看结果</button>
		</span>	
	</div>
	<div id="addVarDiv" class="panel panel-warning">
		<div class="panel-heading" id="subTitle">新增变量</div>
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
							<select id="type" name="type">
								<option value="1">项目配置</option>
								<option value="2">公共参数-请求头</option>								
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

	<div class="listDataTableDiv">
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
							<td colspan="5" align="center">没有数据</td>
						</tr>
				</c:if>
				<c:forEach items="${pvs}" var="pv">
					<tr>
						<td>${pv.id}</td>
						<td>${pv.variableName}</td>
						<td>${pv.variableValue}</td>
						<td>
							<c:choose>
								<c:when test="${pv.type == 1}">项目配置</c:when>
								<c:when test="${pv.type == 2}">公共参数-请求头</c:when>
								<c:when test="${pv.type == 3}">接口关联：${pv.apiInfo.apiName}</c:when>
							</c:choose>
						</td>
						<td>
							<c:if test="${pv.type == 1 }">
								<a onclick="doEdit(${pv.id});return false;">编辑<span class="glyphicon glyphicon-edit"></span></a>
							</c:if>						
							<a onclick="doDelete(${pv.id});return false;">删除<span class="glyphicon glyphicon-trash"></span></a>
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