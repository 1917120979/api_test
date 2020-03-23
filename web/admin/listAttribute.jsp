<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>
<script>
function showLayer(x) {
	$('#layer').css({
	    "display" : "block",
		"height": "200px"
	});
	$('#layerBg').css({
	    "display" : "block"
	});
	if(x == 2){
	    $("#subTitle").html("新增参数");
	    $("#type").attr("value","1");
	}
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

function showLayer3(id, x){
    $('#layer').css({
	    "display" : "block",
		"height": "200px"
	});
	$('#layerBg').css({
	    "display" : "block"
	});
	if(x == 1){
	    $("#subTitle").html("编辑请求头");
	}
	if(x == 2){
	    $("#subTitle").html("编辑参数");
	}
	$("#addForm").attr("action","admin_apiAttribute_update?id="+id);
	$.ajax({
	    type:"post",
	    dataType:"json",
	    url:"${pageContext.request.contextPath}/admin_apiAttribute_edit",
	    data:{
			"id":id
	    },
	    success:function(data){
			var att = data.aattr;
			$("#propName").val(att.propName);
			$("#propValue").val(att.propValue);
			$("#type").val(att.type);
			$("#apiId").val(att.apiId);
	    },
	    error:function(data){
		alert("系统错误");
    }
	});
}

function execute(apiId){
    window.location.href="admin_apiResult_add?apiId="+apiId;
}

function deleteAll(apiId){
    window.location.href="admin_apiResult_delete?apiId="+apiId;
}
</script>
<title>属性管理</title>

<div id="layerBg"></div>

<div class="workingArea">
 
    <ol class="breadcrumb">
      <li><a href="admin_apiInfo_list">所有接口</a></li>
      <li><a href="admin_apiAttribute_list?apiId=${api.id}">${api.apiName}</a></li>
      <li class="active">属性管理</li>
    </ol>
    
    <button type="button" class="btn btn-success" onclick="showLayer(1)">新增请求头</button>
    <button type="button" class="btn btn-success" onclick="showLayer(2)">新增参数</button>
    <br><br>
    <div id="layer" class="panel panel-warning addApiDiv">
		<div class="panel-heading" id="subTitle">新增请求头</div>
		<div class="panel-body">
			<form method="post" id="addForm" action="admin_apiAttribute_add?apiId=${api.id}">
				<table class="addTable">
					<tr>
						<td>属性名</td>
						<td><input id="propName" name="propName" type="text"
							class="form-control"></td>
					</tr>
					<tr>
						<td>属性值</td>
						<td><input id="propValue" name="propValue" type="text"
							class="form-control">
							<input id="type" name="type" type="hidden" value="0">
							<input id="apiId" name="apiId" type="hidden">
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
    
    <div class="listDataTableDiv panel-warning">
    	<div class="panel-heading right-border">请求头</div>
        <table
            class="table table-striped table-bordered table-hover  table-condensed">
            <thead>
                <tr class="success">
                    <!-- <th>ID</th> -->
                    <th>请求头名</th>
                    <th>请求头值</th>
                    <th>编辑</th>
                    <th>删除</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${aatts0}" var="att">
 
                    <tr>
                        <%-- <td>${att.id}</td> --%>
                        <td>${att.propName}</td>
                        <td>${att.propValue}</td>
                        <td><a onclick="showLayer3(${att.id},1)"><span
                                class="glyphicon glyphicon-edit"></span></a></td>
                        <td><a deleteLink="true"
                            href="admin_apiAttribute_delete?id=${att.id}"><span
                                class="     glyphicon glyphicon-trash"></span></a></td>
 
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="listDataTableDiv panel-warning ">
    	<div class="panel-heading">参数</div>
        <table
            class="table table-striped table-bordered table-hover  table-condensed">
            <thead>
                <tr class="success">
                    <!-- <th>ID</th> -->
                    <th>参数名称</th>
                    <th>参数值</th>
                    <th>编辑</th>
                    <th>删除</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${aatts1}" var="att1">
 
                    <tr>
                        <%-- <td>${att1.id}</td> --%>
                        <td>${att1.propName}</td>
                        <td>${att1.propValue}</td>
                        <td><a onclick="showLayer3(${att1.id},2)"><span
                                class="glyphicon glyphicon-edit"></span></a></td>
                        <td><a deleteLink="true"
                            href="admin_apiAttribute_delete?id=${att1.id}"><span
                                class="     glyphicon glyphicon-trash"></span></a></td>
 
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <button type="button" class="btn btn-success" onclick="execute(${api.id})">执行接口</button>
    <button type="button" class="btn btn-success" onclick="deleteAll(${api.id})">删除全部记录</button>
    <%-- <a href="admin_apiResult_add?apiId=${api.id}"><span>执行</span></a> --%>
    <br><br>
    
    <div class="listDataTableDiv1 panel-warning ">
    	<div class="panel-heading">执行结果</div>
        <table
            class="table table-striped table-bordered table-hover  table-condensed">
            <thead>
                <tr class="success">
                    <!-- <th>ID</th> -->
                    <th>请求</th>
                    <th>响应</th>
                    <th>日期</th>
                    <th>删除</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${ars}" var="ar">
 
                    <tr>
                        <%-- <td>${att1.id}</td> --%>
                        <td>${ar.request}</td>
                        <td>${ar.response}</td>
                        <td>${ar.createDate}</td>
                        <td><a deleteLink="true"
                            href="admin_apiResult_delete?id=${ar.id}"><span
                                class="     glyphicon glyphicon-trash"></span></a></td>
 
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    
    <div class="pageDiv">
		<%@include file="../include/admin/adminPage.jsp"%>
	</div>
</div>