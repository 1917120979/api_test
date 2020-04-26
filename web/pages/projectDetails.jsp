<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@include file="../include/header.jsp"%>
<%@include file="../include/navigator.jsp"%>

<script>
    $(document).ready(function() {
		$('table.listTable').DataTable({
		    responsive : true
		});
    });
</script>

<div id="page-wrapper">
	<div class="breadcrumbs" id="breadcrumbs">
		<ul class="breadcrumb">
			<li><i class="ace-icon fa fa-home home-icon"></i> <a
				href="admin_home_list">首页</a></li>
			<li><a href="admin_project_list">项目列表</a></li>
			<li class="active">当前项目</li>
		</ul>
	</div>

	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">项目配置</div>
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
									<div class="form-group">
										<label>项目名</label><input type="text" class="form-control"
											id="name" value="${p.name }" disabled>
									</div>
									<div class="form-group">
										<label>描述</label>
										<textarea class="form-control" rows="3" disabled></textarea>
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
										class="btn btn-primary btn-xs button-left"
										onclick="addProject()">新增变量</button>
								</h4>
							</div>
							<div id="collapseTwo" class="panel-collapse collapse">
								<div class="panel-body">
									<!-- Nav tabs -->
									<ul class="nav nav-tabs">
										<li class="active"><a href="#userVar" data-toggle="tab">用户变量</a>
										</li>
										<li><a href="#profile" data-toggle="tab">公共header</a></li>
										<li><a href="#messages" data-toggle="tab">公共param</a></li>
										<li><a href="#settings" data-toggle="tab">接口关联</a></li>
									</ul>

									<!-- Tab panes -->
									<div class="tab-content">
										<div class="tab-pane fade in active" id="userVar">
											<h5></h5>
											<div>
												<table
													class="table table-striped table-bordered table-hover listTable"
													id="dataTables-userVar">
													<thead>
														<tr>
															<th>编号</th>
															
														</tr>
													</thead>
													<tbody>
														<c:if test="${fn:length(ps) <1}">
															<tr>
																<td  align="center">没有数据</td>
															</tr>
														</c:if>
														
													</tbody>
												</table>
											</div>
										</div>
										<div class="tab-pane fade" id="profile">
											<h5>
												<button type="button" class="btn btn-primary btn-xs"
													onclick="addProject()">新增header</button>
											</h5>
											<div>
												<table
													class="table table-striped table-bordered table-hover listTable"
													id="dataTables-example">
													<thead>
														<tr>
															<th>编号</th>
															
														</tr>
													</thead>
													<tbody>
														<c:if test="${fn:length(ps) <1}">
															<tr>
																<td colspan="7" align="center">没有数据</td>
															</tr>
														</c:if>
													</tbody>
												</table>
											</div>
										</div>
										<div class="tab-pane fade" id="messages">
											<h5>
												<button type="button" class="btn btn-primary btn-xs"
													onclick="addProject()">新增param</button>
											</h5>
											<div>
												<table
													class="table table-striped table-bordered table-hover listTable"
													id="dataTables-example">
													<thead>
														<tr>
															<th>编号</th>
														
														</tr>
													</thead>
													<tbody>
														<c:if test="${fn:length(ps) <1}">
															<tr>
																<td colspan="7" align="center">没有数据</td>
															</tr>
														</c:if>
													</tbody>
												</table>
											</div>
										</div>
										<div class="tab-pane fade" id="settings">
											<h5></h5>
											<div>
												<table
													class="table table-striped table-bordered table-hover listTable"
													id="dataTables-example">
													<thead>
														<tr>
															<th>编号</th>
														
														</tr>
													</thead>
													<tbody>
														<c:if test="${fn:length(ps) <1}">
															<tr>
																<td colspan="7" align="center">没有数据</td>
															</tr>
														</c:if>
													</tbody>
												</table>
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
							<div id="collapseThree" class="panel-collapse collapse">
								<div class="panel-body">接口列表按钮、接口执行，查看结果</div>
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

	<div class="row">
		<div class="col-lg-12">

			<!-- /.panel -->
		</div>
		<!-- /.col-lg-6 -->
		<!-- /.row -->
	</div>
</div>
<%@include file="../include/footer.jsp"%>