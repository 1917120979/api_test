<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script src="dist/js/list-api.js"></script>

<script>
    $(document).ready(function() {
		$('table.listTable').DataTable({
		    retrieve: true,
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

<!--新增 编辑分组div  -->
<%-- 	<div id="addGroupDiv" class="panel panel-primary addDiv addDiv-size-1">
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
	</div> --%>

<!--新增 编辑 接口 div  -->
<div id="addApiDiv" class="panel panel-primary addDiv addDiv-size-3">
	<div id="apiPanelHead" class="panel-heading">新增接口</div>
	<div class="panel-body">
		<form action="" id="addApiForm" name="admin_api_add"
			class="form-horizontal">
			<div class="form-group">
				<label for="name" class="col-sm-2 control-label">名称</label>
				<div class="col-sm-10">
					<input id="apiId" name="id" type="hidden"> <input id="aPid"
						name="pid" type="hidden" value="${p.id }"> <input
						type="text" class="form-control" id="apiName" name="name"
						placeholder="请输入Name">
				</div>
			</div>
			<div class="form-group">
				<label for="protocol" class="col-sm-2 control-label">协议</label>
				<div class="col-sm-10">
					<select id="protocol" name="protocol">
						<option value="1">http</option>
						<option value="2">https</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label for="url" class="col-sm-2 control-label">URL</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="url" name="url"
						placeholder="请输入url">
				</div>
			</div>
			<div class="form-group">
				<label for="method" class="col-sm-2 control-label">方法</label>
				<div class="col-sm-10">
					<select id="method" name="method">
						<option value="GET">GET</option>
						<option value="POST">POST</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label for="dataType" class="col-sm-2 control-label">类型</label>
				<div class="col-sm-10">
					<select id="dataType" name="dataType">
						<option value="1">JSON</option>
						<option value="0">其他</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label for="filesUpload" class="col-sm-2 control-label">上传</label>
				<div class="col-sm-10">
					<select id="dataType" name="filesUpload">
						<option value="0">不上传</option>
						<option value="1">上传</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="button" class="btn btn-primary" onclick="submitApi()">提交</button>
					<button type="button" class="btn btn-default" onclick="cancel()">取消</button>
				</div>
			</div>
		</form>
	</div>
</div>
<!-- page-header -->
<div>
	<div class="col-lg-12">
		<div class="page-header-p">接口列表</div>
	</div>
</div>
<!--分组和接口展示  -->
<div class="col-lg-12">
	<div class="panel panel-default">
		<div class="panel-heading">
			<button type="button" class="btn btn-primary btn-sm"
				onclick="addApi()">新增项目</button>
		</div>
		<!-- .panel-heading -->
		<div class="panel-body">
			<c:if test="${fn:length(apis) < 1 }">
				<div align="center">没有数据</div>
			</c:if>
			<c:if test="${fn:length(apis) > 0 }">
				<table
					class="table table-striped table-bordered table-hover listTable"
					id="dataTables-api">
					<thead>
						<tr>
							<th>Name</th>
							<th>协议</th>
							<th>URL</th>
							<th>METHOD</th>
							<th>数据类型</th>
							<th>上传文件</th>
							<th>备注</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${apis }" var="api">
							<tr>
								<td>${api.name }</td>
								<td><c:choose>
										<c:when test="${api.protocol == 1}">HTTP</c:when>
										<c:when test="${api.protocol == 2}">HTTPS</c:when>
									</c:choose></td>
								<td>${api.url }</td>
								<td>${api.method }</td>
								<td>${api.dataType }</td>
								<td><c:choose>
										<c:when test="${api.filesUpload == 0}">不上传</c:when>
										<c:when test="${api.filesUpload == 1}">上传</c:when>
									</c:choose></td>
								<td>${api.comments }</td>
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

