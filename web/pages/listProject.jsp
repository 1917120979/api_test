<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>



<script>
$(document).ready(function() {
    $('#dataTable-project').DataTable({
	bPaginate : true,
	bInfo : false,
	retrieve: true,
	"language" : {
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

<!-- 新增项目div -->
<div id="addProjectDiv" class="panel panel-primary addDiv addDiv-size-2">
	<div id="projectPanelHead" class="panel-heading">新增项目</div>
	<div class="panel-body">
		<form action="" id="addProjectForm" name="admin_project_add"
			class="form-horizontal">
			<div class="form-group">
				<label for="name" class="col-sm-2 control-label">名称</label>
				<div class="col-sm-10">
					<input id="projectId" name="id" type="hidden"> <input type="text"
						class="form-control" id="projectName" name="name" placeholder="请输入项目名称">
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
				<label for="pComments" class="col-sm-2 control-label">备注</label>
				<div class="col-sm-10">
					<textarea class="form-control" rows="3" name="comments"
						id="pComments"></textarea>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="button" class="btn btn-primary"
						onclick="submitProject()">提交</button>
					<button type="button" class="btn btn-default" onclick="closeAddDiv('addProjectDiv')">取消</button>
				</div>
			</div>
		</form>
	</div>
</div>
<!-- page-header -->
<div>
	<div class="col-lg-12">
		<div class="page-header-p">
			项目列表
		</div>
	</div>
</div>
<!-- 项目列表 -->
<div class="col-lg-12">
	<div class="panel panel-default">
		<div class="panel-heading">
			<button type="button" class="btn btn-primary btn-sm"
				onclick="showAddDiv('addProjectDiv')">新增项目</button>
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
								<td>${p.name}</td>
								<td><c:choose>
										<c:when test="${p.sign == 0}">不签名</c:when>
										<c:when test="${p.sign == 1}">网关</c:when>
										<c:when test="${p.sign == 2}">平台</c:when>
										<c:when test="${p.sign == 3}">APP</c:when>
									</c:choose></td>
								<td><c:choose>
										<c:when test="${p.encrypt == 0}">不加密</c:when>
										<c:when test="${p.encrypt == 1}">加密</c:when>
									</c:choose></td>
								<td>${p.user.username}</td>
								<td>${p.createDate}</td>
								<td><a href="#" onclick="configProject(${p.id});return false;">配置<span
										class="glyphicon glyphicon-cog"></span></a> <a
									onclick="editProject(${p.id});return false;">编辑<span
										class="glyphicon glyphicon-edit"></span></a> <a
									onclick="delProject(${p.id});return false;">删除<span
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

