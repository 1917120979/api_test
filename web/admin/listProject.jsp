<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>
<script>
    function showAddProjectDiv() {
		$('#addProjectDiv').css({
		    "display" : "block"
		});
		$('#layerBg').css({
		    "display" : "block"
		});
		return false;
    }

    function cancel() {
		$('#addProjectDiv').css({
		    "display" : "none"
		});
		$('#layerBg').css({
		    "display" : "none"
		});
		window.location.reload();
    }
    
    function doProjectEdit(id){
		$("#addProjectForm").attr("name","admin_project_update");
		$("#subTitle").html("编辑项目");
		$.ajax({
		    type:"post",
		    dataType:"json",
		    url:"${pageContext.request.contextPath}/admin_project_edit",
		    data:{
				"id":id
		    },
		    success:function(data){
				var p = data.data;
				$("#id").val(p.id);
				$("#name").val(p.name);			
				$("#sign").val(p.sign);
				$("#encrypt").val(p.encrypt);
				$('#addProjectDiv').css({
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
    }
    
    function doProjectDel(id){
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
    
    function submitProjectForm(){
		if (!checkEmpty("name","项目 名称"))
			return false;
		if (!checkEmpty("sign","是否签名"))
				return false;
		if (!checkEmpty("encrypt","是否加密"))
			return false;
		var targetUrl = $("#addProjectForm").attr("name");
	    $.ajax({
			type:"post",
		    dataType:"json",
	        url:targetUrl,
	        data: $("#addProjectForm").serialize(),
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
	<div id="workNav">
		<ol>
	      <li class="active">所有项目</li>
	    </ol>
	</div>
    <div id="listTitle">
    	<span>项目列表</span>
    	<button type="button" class="btn btn-success" onclick="showAddProjectDiv()">新增项目</button>
    </div>	
	<div id="addProjectDiv" class="panel panel-warning">
		<div class="panel-heading" id="subTitle">新增项目</div>
		<div class="panel-body">
			<form id="addProjectForm" name="admin_project_add" action="">
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
							<select id="sign" name="sign">
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
							<select id="encrypt" name="encrypt">
								<option value="0">不加密</option>
								<option value="1">加密</option>								
							</select>							
						</td>
							
					</tr>
					<tr>
						<td colspan="2" align="center">
							<button type="button" class="btn btn-success" onclick="submitProjectForm()">提 交</button>
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
					<th>项目名称</th>
					<th>是否签名</th>
					<th>是否加密</th>
					<th>操作</th>		
				</tr>
			</thead>
			<tbody>		
				<c:if test="${fn:length(ps) <1}">
						<tr>
							<td colspan="5" align="center">没有数据</td>
						</tr>
				</c:if>
				<c:forEach items="${ps}" var="p">

					<tr>
						<td>${p.id}</td>
						<td>${p.name}</td>
						<td>
							<c:choose>
								<c:when test="${p.sign == 0}">不签名</c:when>
								<c:when test="${p.sign == 1}">签名：网关</c:when>
								<c:when test="${p.sign == 2}">签名：平台</c:when>
								<c:when test="${p.sign == 3}">签名：APP</c:when>
							</c:choose>
						</td>
						<td>
							<c:choose>
								<c:when test="${p.encrypt == 0}">不加密</c:when>
								<c:when test="${p.encrypt == 1}">加密</c:when>
							</c:choose>
						</td>
						<td>
							<a href="admin_projectVariable_list?pid=${p.id}">配置<span class="glyphicon glyphicon-cog"></span></a>
							<a onclick="doProjectEdit(${p.id});return false;">编辑<span class="glyphicon glyphicon-edit"></span></a>
							<a onclick="doProjectDel(${p.id});return false;">删除<span class="glyphicon glyphicon-trash"></span></a>
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