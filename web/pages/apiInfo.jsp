<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script>
$(function() {
    var sign = ${p.sign};
    if(sign == 0){	    
	    $("#pSign").val("不签名");
    }else if(sign == 1){
		$("#pSign").val("网关");
    }else{
		$("#pSign").val("其他");
    }
		
    if(encrypt == 0){
		$("#pEncrypt").val("不加密");
    }else{
		$("#pEncrypt").val("加密");
    }				
	return;
});
$(document).ready(function() {
    $('table.listTable').DataTable({
	bPaginate : true,
	bInfo : false,
	retrieve: true,
	searching: false,
	lengthChange: false,
	language: {
	    paginate : {
		previous : "上一页",
		next : "下一页"
	    }
	}
    });
});

</script>

<!--新增 编辑变量 div  -->
<div id="addAttributeDiv" class="panel panel-primary addDiv addDiv-size-2">
	<div id="attrPanelHead" class="panel-heading">新增属性</div>
	<div class="panel-body">
		<form action="" id="addAttributeForm" name="admin_variable_add"
			class="form-horizontal">
			<div class="form-group">
				<label for="name" class="col-sm-2 control-label">Name</label>
				<div class="col-sm-10">
					<input id="attrid" name="id" type="hidden"> <input id="pid"
						name="aaid" type="hidden" value="${api.id }"> <input
						type="text" class="form-control" id="attrName" name="name"
						placeholder="请输入Name">
				</div>
			</div>
			<div class="form-group">
				<label for="value" class="col-sm-2 control-label">Value</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="attrValue" name="value"
						placeholder="请输入Value">
				</div>
			</div>
			<div class="form-group">
				<label for="comments" class="col-sm-2 control-label">描述</label>
				<div class="col-sm-10">
					<textarea class="form-control" rows="2" name="comments"
						id="attrComments"></textarea>
				</div>
			</div>
			<div class="form-group">
				<label for="type" class="col-sm-2 control-label">类型</label>
				<div class="col-sm-10">
					<select id="type" name="type">
						<option value="2">header</option>
						<option value="3">param</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="button" class="btn btn-primary"
						onclick="submitAttribute()">提交</button>
					<button type="button" class="btn btn-default" onclick="closeAddDiv('addAttributeDiv')">取消</button>
				</div>
			</div>
		</form>
	</div>
</div>
<!-- page-header -->
<div>
	<div class="col-lg-12">
		<div class="page-header-p">
			<div class="page-header-p1">接口名：${api.name }</div>
			项目配置
		</div>
	</div>
</div>
<!--详情  -->
<div class="col-lg-12">
	<!--描述  -->
	<div class="panel-group" id="accordion2">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">
					<a data-toggle="collapse" data-parent="#accordion2"
						href="#collapseOne">描述</a>
				</h4>
			</div>
			<div id="collapseOne" class="panel-collapse collapse in">
				<div class="panel-body">
					<div class="form-group">
						<textarea class="form-control" rows="1" disabled>${p.comments }</textarea>
					</div>
				</div>
			</div>
		</div>
		<!--变量列表  -->
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">
					<a data-toggle="collapse" data-parent="#accordion2"
						href="#collapseTwo">属性列表</a>
					<button type="button" class="btn btn-primary btn-xs margin-left"
						onclick="showAddDiv('addAttributeDiv')">新增变量</button>
				</h4>
			</div>
			<div id="collapseTwo" class="panel-collapse collapse in">
				<div class="panel-body">
					<!-- Nav tabs -->
					<ul class="nav nav-tabs">
						<li class="active"><a href="#userVar" data-toggle="tab">用户变量</a>
						</li>
						<li><a href="#header" data-toggle="tab">header</a></li>
						<li><a href="#param" data-toggle="tab">param</a></li>
					</ul>

					<!-- Tab panes -->
					<div class="tab-content">
						<div class="tab-pane fade" id="header">
							<h5></h5>
							<div>
								<c:if test="${fn:length(has) < 1}">
									<div align="center">没有数据</div>
								</c:if>
								<c:if test="${fn:length(has) > 0}">
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
											<c:forEach items="${has }" var="ha">
												<tr>
													<td>${ha.name }</td>
													<td>${ha.value }</td>
													<td>${ha.comments }</td>
													<td><a onclick="editAttribute(${ha.id});return false;">编辑<span
															class="glyphicon glyphicon-edit"></span></a> <a
														onclick="delAttribute(${ha.id});return false;">删除<span
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
								<c:if test="${fn:length(pas) < 1}">
									<div align="center">没有数据</div>
								</c:if>
								<c:if test="${fn:length(pas) > 0}">
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
											<c:forEach items="${pas }" var="pa">
												<tr>
													<td>${pa.name }</td>
													<td>${pa.value }</td>
													<td>${pa.comments }</td>
													<td><a onclick="editAttribute(${pa.id});return false;">编辑<span
															class="glyphicon glyphicon-edit"></span></a> <a
														onclick="delAttribute(${pa.id});return false;">删除<span
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
								<c:if test="${fn:length(has) < 1}">
									<div align="center">没有数据</div>
								</c:if>
								<c:if test="${fn:length(has) > 0}">
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
											<c:forEach items="${has }" var="ha">
												<tr>
													<td>${ha.name }</td>
													<td>${ha.value }</td>
													<td>${ha.comments }</td>
													<td><a onclick="editAttribute(${ha.id});return false;">编辑<span
															class="glyphicon glyphicon-edit"></span></a> <a
														onclick="delAttribute(${ha.id});return false;">删除<span
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
		<!-- 其他功能 -->
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">
					<a data-toggle="collapse" data-parent="#accordion2"
						href="#collapseThree">功能</a>
				</h4>
			</div>
			<div id="collapseThree" class="panel-collapse collapse in">
				<div class="panel-body">
					<button type="button" class="btn btn-primary btn-xs margin-right"
						onclick="apiManage(${p.id})">接口管理</button>
					<span>管理接口的增删改查</span> <br> <br>
					<button type="button" class="btn btn-primary btn-xs margin-right"
						onclick="testcaseList(${p.id})">用例列表</button>
					<span>管理接口的增删改查</span> <br> <br>
					<button type="button" class="btn btn-primary btn-xs margin-right"
						onclick="apiExecute()">接口执行</button>
					<span>批量执行项目的所有接口</span> <br> <br>
					<button type="button" class="btn btn-primary btn-xs margin-right"
						onclick="viewResult()">查看结果</button>
					<span>查看接口执行结果</span> <br> <br>
				</div>
			</div>
		</div>
	</div>
</div>



