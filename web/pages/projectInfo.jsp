<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@include file="../include/header.jsp"%>
<%@include file="../include/navigator.jsp"%>

<script src="dist/js/project-info.js"></script>

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
			<li class="active">${p.name }</li>
		</ul>
	</div>

	<!--新增 编辑变量 div  -->
	<div id="addVariableDiv" class="panel panel-primary addDiv addDiv-size-2">
		<div id="varPanelHead" class="panel-heading">新增变量</div>
		<div class="panel-body">
			<form action="" id="addVariableForm" name="admin_variable_add"
				class="form-horizontal">
				<div class="form-group">
					<label for="name" class="col-sm-2 control-label">Name</label>
					<div class="col-sm-10">
						<input id="id" name="id" type="hidden"> <input id="pid"
							name="pid" type="hidden" value="${p.id }"> <input
							type="text" class="form-control" id="name" name="name"
							placeholder="请输入Name">
					</div>
				</div>
				<div class="form-group">
					<label for="value" class="col-sm-2 control-label">Value</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="value" name="value"
							placeholder="请输入Value">
					</div>
				</div>
				<div class="form-group">
					<label for="description" class="col-sm-2 control-label">描述</label>
					<div class="col-sm-10">
						<textarea class="form-control" rows="2" name="description"
							id="description"></textarea>
					</div>
				</div>
				<div class="form-group">
					<label for="type" class="col-sm-2 control-label">类型</label>
					<div class="col-sm-10">
						<select id="type" name="type">
							<option value="1">用户变量</option>
							<option value="2">公共header</option>
							<option value="3">公共param</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="button" class="btn btn-primary"
							onclick="submitVariable()">提交</button>
						<button type="button" class="btn btn-default" onclick="cancel()">取消</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">项目信息</div>
				<!-- .panel-heading -->
				<div class="panel-body">
					<div class="panel-group" id="accordion">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#collapseOne">基本信息</a>
								</h4>
							</div>
							<div id="collapseOne" class="panel-collapse collapse in">
								<div class="panel-body">
									<div class="div-bottom">
										名字：<input type="text" class="div-input size-10" id="name"
											value="${p.name }" disabled>
									</div>
									<div class="div-bottom">
										签名：
										<c:choose>
											<c:when test="${p.sign == 0}">
												<input type="text" class="div-input size-2" id="sign"
													value="不签名" disabled>
											</c:when>
											<c:when test="${p.sign == 1}">
												<input type="text" class="div-input size-2" id="sign"
													value="网关" disabled>
											</c:when>
											<c:when test="${p.sign == 2}">
												<input type="text" class="div-input size-2" id="sign"
													value="平台" disabled>
											</c:when>
											<c:when test="${p.sign == 3}">
												<input type="text" class="div-input size-2" id="sign"
													value="APP" disabled>
											</c:when>
										</c:choose>
										加密：
										<c:choose>
											<c:when test="${p.encrypt == 0}">
												<input type="text" class="div-input size-2" id="encrypt"
													value="不加密" disabled>
											</c:when>
											<c:when test="${p.encrypt == 1}">
												<input type="text" class="div-input size-2" id="encrypt"
													value="加密" disabled>
											</c:when>
										</c:choose>
									</div>
									<div class="div-bottom">
										描述：
										<textarea class="div-textarea size-10" rows="2" disabled>${p.description }</textarea>
									</div>
								</div>
							</div>
						</div>

						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#collapseTwo">变量管理</a>
									<button type="button"
										class="btn btn-primary btn-xs margin-left"
										onclick="addVariable()">新增变量</button>
								</h4>
							</div>
							<div id="collapseTwo" class="panel-collapse collapse in">
								<div class="panel-body">
									<!-- Nav tabs -->
									<ul class="nav nav-tabs">
										<li class="active"><a href="#userVar" data-toggle="tab">用户变量</a>
										</li>
										<li><a href="#header" data-toggle="tab">公共header</a></li>
										<li><a href="#param" data-toggle="tab">公共param</a></li>
										<li><a href="#relation" data-toggle="tab">接口关联</a></li>
									</ul>

									<!-- Tab panes -->
									<div class="tab-content">
										<div class="tab-pane fade in active" id="userVar">
											<h5></h5>
											<div>
												<c:if test="${fn:length(uvs) < 1}">
													<div align="center">没有数据</div>
												</c:if>
												<c:if test="${fn:length(uvs) > 0}">
													<table
														class="table table-striped table-bordered table-hover listTable"
														id="dataTables-userVar">
														<thead>
															<tr>
																<th>Name</th>
																<th>Value</th>
																<th>描述</th>
																<th>操作</th>
															</tr>
														</thead>
														<tbody>
															<c:forEach items="${uvs }" var="uv">
																<tr>
																	<td>${uv.name }</td>
																	<td>${uv.value }</td>
																	<td>${uv.description }</td>
																	<td><a
																		onclick="editVariable(${uv.id});return false;">编辑<span
																			class="glyphicon glyphicon-edit"></span></a> <a
																		onclick="delVariable(${uv.id});return false;">删除<span
																			class="glyphicon glyphicon-trash"></span></a></td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
												</c:if>
											</div>
										</div>
										<div class="tab-pane fade" id="header">
											<h5></h5>
											<div>
												<c:if test="${fn:length(hvs) < 1}">
													<div align="center">没有数据</div>
												</c:if>
												<c:if test="${fn:length(hvs) > 0}">
													<table
														class="table table-striped table-bordered table-hover listTable"
														id="dataTables-header">
														<thead>
															<tr>
																<th>Name</th>
																<th>Value</th>
																<th>描述</th>
																<th>操作</th>
															</tr>
														</thead>
														<tbody>
															<c:forEach items="${hvs }" var="hv">
																<tr>
																	<td>${hv.name }</td>
																	<td>${hv.value }</td>
																	<td>${hv.description }</td>
																	<td><a
																		onclick="editVariable(${hv.id});return false;">编辑<span
																			class="glyphicon glyphicon-edit"></span></a> <a
																		onclick="delVariable(${hv.id});return false;">删除<span
																			class="glyphicon glyphicon-trash"></span></a></td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
												</c:if>
											</div>
										</div>
										<div class="tab-pane fade" id="param">
											<h5></h5>
											<div>
												<c:if test="${fn:length(pvs) < 1}">
													<div align="center">没有数据</div>
												</c:if>
												<c:if test="${fn:length(pvs) > 0}">
													<table
														class="table table-striped table-bordered table-hover listTable"
														id="dataTables-param">
														<thead>
															<tr>
																<th>Name</th>
																<th>Value</th>
																<th>描述</th>
																<th>操作</th>
															</tr>
														</thead>
														<tbody>
															<c:forEach items="${pvs }" var="pv">
																<tr>
																	<td>${pv.name }</td>
																	<td>${pv.value }</td>
																	<td>${pv.description }</td>
																	<td><a
																		onclick="editVariable(${pv.id});return false;">编辑<span
																			class="glyphicon glyphicon-edit"></span></a> <a
																		onclick="delVariable(${pv.id});return false;">删除<span
																			class="glyphicon glyphicon-trash"></span></a></td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
												</c:if>
											</div>
										</div>
										<div class="tab-pane fade" id="relation">
											<h5></h5>
											<div>
												<c:if test="${fn:length(avs) < 1}">
													<div align="center">没有数据</div>
												</c:if>
												<c:if test="${fn:length(avs) > 0}">
													<table
														class="table table-striped table-bordered table-hover listTable"
														id="dataTables-relation">
														<thead>
															<tr>
																<th>Name</th>
																<th>Value</th>
																<th>描述</th>
																<th>操作</th>
															</tr>
														</thead>
														<tbody>
															<c:forEach items="${avs }" var="av">
																<tr>
																	<td>${av.name }</td>
																	<td>${av.value }</td>
																	<td>${av.description }</td>
																	<td><a
																		onclick="editVariable(${av.id});return false;">编辑<span
																			class="glyphicon glyphicon-edit"></span></a> <a
																		onclick="delVariable(${av.id});return false;">删除<span
																			class="glyphicon glyphicon-trash"></span></a></td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
												</c:if>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#collapseThree">接口配置</a>
								</h4>
							</div>
							<div id="collapseThree" class="panel-collapse collapse in">
								<div class="panel-body">
									<button type="button"
										class="btn btn-primary btn-xs margin-right"
										onclick="apiManage(${p.id})">接口管理</button>
									<span>管理接口的增删改查</span> <br>
									<br>
									<button type="button"
										class="btn btn-primary btn-xs margin-right"
										onclick="apiExecute()">接口执行</button>
									<span>批量执行项目的所有接口</span> <br>
									<br>
									<button type="button"
										class="btn btn-primary btn-xs margin-right"
										onclick="viewResult()">查看结果</button>
									<span>查看接口执行结果</span> <br>
									<br>
								</div>
							</div>
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
</div>
<%@include file="../include/footer.jsp"%>