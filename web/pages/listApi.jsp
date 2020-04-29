<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@include file="../include/header.jsp"%>
<%@include file="../include/navigator.jsp"%>

<script src="dist/js/list-api.js"></script>

<script>
    $(document).ready(function() {
		$('table.listTable').DataTable({
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
			<li><a href="admin_project_list">项目列表</a></li>
			<li><a href="admin_projectInfo_list?pid=${p.id }">${p.name }</a></li>
			<li class="active">接口管理</li>
		</ul>
	</div>

	<!--新增 编辑分组div  -->
	<div id="addGroupDiv" class="panel panel-primary addDiv addDiv-size-1">
		<div id="groupPanelHead" class="panel-heading">新增分组</div>
		<div class="panel-body">
			<form action="" id="addGroupForm" name="admin_group_add"
				class="form-horizontal">
				<div class="form-group">
					<label for="name" class="col-sm-2 control-label">NAME</label>
					<div class="col-sm-10">
						<input id="gid" name="id" type="hidden"> <input id="gpid"
							name="pid" type="hidden" value="${p.id }"> <input
							type="text" class="form-control" id="name" name="name"
							placeholder="请输入Name">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="button" class="btn btn-primary"
							onclick="submitGroup()">提交</button>
						<button type="button" class="btn btn-default" onclick="cancel()">取消</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<!--新增 编辑 接口 div  -->
	<div id="addApiDiv" class="panel panel-primary addDiv addDiv-size-2">
		<div id="apiPanelHead" class="panel-heading">新增接口</div>
		<div class="panel-body">
			<form action="" id="addApiForm" name="admin_api_add"
				class="form-horizontal">
				<div class="form-group">
					<label for="name" class="col-sm-2 control-label">NAME</label>
					<div class="col-sm-10">
						<input id="apiId" name="id" type="hidden"> <input
							id="apid" name="pid" type="hidden" value="${p.id }"> <input
							id="agid" name="gid" type="hidden" value=""> <input
							type="text" class="form-control" id="apiName" name="name"
							placeholder="请输入Name">
					</div>
				</div>
				<div class="form-group">
					<label for="name" class="col-sm-2 control-label">URL</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="url" name="url"
							placeholder="请输入url">
					</div>
				</div>
				<div class="form-group">
					<label for="name" class="col-sm-2 control-label">METHOD</label>
					<div class="col-sm-10">
						<select id="method" name="method">
							<option value="GET">GET</option>
							<option value="POST">POST</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="name" class="col-sm-2 control-label">DATATYPE</label>
					<div class="col-sm-10">
						<select id="dataType" name="dataType">
							<option value="1">JSON</option>
							<option value="0">其他</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="button" class="btn btn-primary"
							onclick="submitApi()">提交</button>
						<button type="button" class="btn btn-default" onclick="cancel()">取消</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<!--分组和接口展示  -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					接口列表
					<button type="button" class="btn btn-primary btn-xs margin-left"
						onclick="addGroup()">新增分组</button>
				</div>
				<!-- .panel-heading -->
				<div class="panel-body">
					<div class="panel-group" id="accordion">
						<c:if test="${fn:length(gs) < 1}">
							<div align="center">没有数据</div>
						</c:if>
						<c:if test="${fn:length(gs) > 0}">
							<c:forEach items="${gs}" var="g">
								<div class="panel panel-default">
									<div class="panel-heading">
										<a data-toggle="collapse" data-parent="#accordion"
											href="#collapse${g.id }">分组：${g.name }</a> <a
											class="margin-left"
											onclick="editGroup(${g.id});return false;">编辑<span
											class="glyphicon glyphicon-edit"></span></a> <a
											class="margin-left" onclick="delGroup(${g.id});return false;">删除<span
											class="glyphicon glyphicon-trash"></span></a>
										<button type="button"
											class="btn btn-primary btn-xs float-right"
											onclick="addApi(${g.id})">新增接口</button>
									</div>
									<div id="collapse${g.id }" class="panel-collapse collapse in">
										<div class="panel-body">
											<c:if test="${fn:length(g.apiByGroup) < 1 }">
												<div align="center">没有数据</div>
											</c:if>
											<c:if test="${fn:length(g.apiByGroup) > 0 }">
												<table
													class="table table-striped table-bordered table-hover listTable"
													id="dataTables-header">
													<thead>
														<tr>
															<th>Name</th>
															<th>URL</th>
															<th>METHOD</th>
															<th>数据类型</th>
															<th>操作</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${g.apiByGroup }" var="api">
															<tr>
																<td>${api.name }</td>
																<td>${api.url }</td>
																<td>${api.method }</td>
																<td>${api.dataType }</td>
																<td><a href="admin_apiInfo_list?aid=${api.id}">配置<span
																		class="glyphicon glyphicon-cog"></span></a> <a
																	onclick="editApi(${api.id});return false;">编辑<span
																		class="glyphicon glyphicon-edit"></span></a> <a
																	onclick="delApi(${api.id});return false;">删除<span
																		class="glyphicon glyphicon-trash"></span></a></td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</c:if>
										</div>
									</div>
								</div>
							</c:forEach>
						</c:if>

					</div>
				</div>
			</div>
			<!-- .panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<%@include file="../include/footer.jsp"%>