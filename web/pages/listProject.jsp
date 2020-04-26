<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@include file="../include/header.jsp"%>
<%@include file="../include/navigator.jsp"%>

<script src="dist/js/list-project.js"></script>

<script>
    $(document).ready(function() {
	$('#dataTable-project').DataTable({
	    responsive : true,
	    "language" : {
		"info" : "共 _TOTAL_ 条目",
		"lengthMenu" : "展示 _MENU_ 个条目",
		"search" : "查询",
		"paginate" : {
		    "previous" : "上一页",
		    "next" : "下一页"
		}
	    }
	});
    });
</script>

<div id="page-wrapper">
	<div class="breadcrumbs" id="breadcrumbs">
		<ul class="breadcrumb">
			<li><i class="ace-icon fa fa-home home-icon"></i> <a
				href="admin_home_list">首页</a></li>
			<li class="active">项目管理</li>
		</ul>
		<!-- /.breadcrumb -->
	</div>
	<div id="addProjectDiv" class="panel panel-primary">
		<div id="projectPanelHead" class="panel-heading">新增项目</div>
		<div class="panel-body">
			<form action="" id="addProjectForm" name="admin_project_add"
				class="form-horizontal">
				<div class="form-group">
					<label for="name" class="col-sm-2 control-label">项目名</label>
					<div class="col-sm-10">
						<input id="id" name="id" type="hidden"> <input type="text"
							class="form-control" id="name" name="name" placeholder="请输入项目名称">
					</div>
				</div>
				<div class="form-group">
					<label for="sign" class="col-sm-2 control-label">签名</label>
					<div class="col-sm-10">
						<select id="sign" name="sign">
							<option value="0">不签名</option>
							<option value="1">签名：网关</option>
							<option value="2">签名：平台</option>
							<option value="3">签名：APP</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="encrypt" class="col-sm-2 control-label">加密</label>
					<div class="col-sm-10">
						<select id="encrypt" name="encrypt">
							<option value="0">不加密</option>
							<option value="1">加密</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="describe" class="col-sm-2 control-label">描述</label>
					<div class="col-sm-10">
						<textarea class="form-control" rows="3" name="describe"></textarea>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="button" class="btn btn-primary"
							onclick="submitProject()">提交</button>
						<button type="button" class="btn btn-default" onclick="cancel()">取消</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					项目列表
					<button type="button" class="btn btn-primary btn-xs button-left"
						onclick="addProject()">新增项目</button>
				</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<c:if test="${fn:length(ps) <1}">
						<div align="center">没有数据</div>
					</c:if>
					<c:if test="${fn:length(ps) >0}">
						<table
							class="table table-striped table-bordered table-hover listTable"
							id="dataTable-project">
							<thead>
								<tr>
									<th>编号</th>
									<th>项目名称</th>
									<th>签名</th>
									<th>加密</th>
									<th>创建人</th>
									<th>创建日期</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>

								<c:forEach items="${ps}" var="p">
									<tr class="odd gradeX">
										<td>${p.id}</td>
										<td>${p.name}</td>
										<td><c:choose>
												<c:when test="${p.sign == 0}">不签名</c:when>
												<c:when test="${p.sign == 1}">签名：网关</c:when>
												<c:when test="${p.sign == 2}">签名：平台</c:when>
												<c:when test="${p.sign == 3}">签名：APP</c:when>
											</c:choose></td>
										<td><c:choose>
												<c:when test="${p.encrypt == 0}">不加密</c:when>
												<c:when test="${p.encrypt == 1}">加密</c:when>
											</c:choose></td>
										<td>${p.user.username}</td>
										<td>${p.createDate}</td>
										<td><a href="admin_projectDetails_list?pid=${p.id}">配置<span
												class="glyphicon glyphicon-cog"></span></a> <a
											onclick="doProjectEdit(${p.id});return false;">编辑<span
												class="glyphicon glyphicon-edit"></span></a> <a
											onclick="doProjectDel(${p.id});return false;">删除<span
												class="glyphicon glyphicon-trash"></span></a></td>
									</tr>
								</c:forEach>

							</tbody>
						</table>
					</c:if>


				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
		<!-- /.col-lg-12 -->
	</div>
</div>
<%@include file="../include/footer.jsp"%>