<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script>
$(document).ready(function() {
    $('#dataTables-headerAttr').DataTable({
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
    $('#dataTables-paramAttr').DataTable({
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
<div id="addAttributeDiv"
	class="panel panel-primary addDiv addDiv-size-2">
	<div id="attrPanelHead" class="panel-heading">新增属性</div>
	<div class="panel-body">
		<form action="" id="addAttributeForm" name="admin_attribute_add"
			class="form-horizontal">
			<div class="form-group">
				<label for="name" class="col-sm-2 control-label">Name</label>
				<div class="col-sm-10">
					<input id="attrid" name="id" type="hidden"> <input
						id="aaid" name="aid" type="hidden" value="${api.id }"> <input
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
				<label for="attrType" class="col-sm-2 control-label">类型</label>
				<div class="col-sm-10">
					<select id="attrType" name="type">
						<option value="2">header</option>
						<option value="3">params</option>
					</select>
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
				<div class="col-sm-offset-2 col-sm-10">
					<button type="button" class="btn btn-primary"
						onclick="submitAttribute()">提交</button>
					<button type="button" class="btn btn-default"
						onclick="closeAddDiv('addAttributeDiv')">取消</button>
				</div>
			</div>
		</form>
	</div>
</div>
<!--新增 编辑提取器div  -->
<div id="addExtractorDiv"
	class="panel panel-primary addDiv addDiv-size-2">
	<div id="extrPanelHead" class="panel-heading">新增提取器</div>
	<div class="panel-body">
		<form action="" id="addExtractorForm" name="admin_extractor_add"
			class="form-horizontal">
			<div class="form-group">
				<label for="extrName" class="col-sm-2 control-label">Name</label>
				<div class="col-sm-10">
					<input id="extrId" name="id" type="hidden"> <input
						id="eaid" name="aid" type="hidden" value="${api.id }"> <input
						type="text" class="form-control" id="extrName" name="extractorName"
						placeholder="请输入Name">
				</div>
			</div>
			<div class="form-group">
				<label for="extrVar" class="col-sm-2 control-label">Variable</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="extrVariable" name="variableName"
						placeholder="请输入变量名">
				</div>
			</div>			
			<div class="form-group">
				<label for="extrRegular" class="col-sm-2 control-label">Regular</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="extrRegular" name="regularExpression"
						placeholder="请输入正则表达式">
				</div>
			</div>	
			<div class="form-group">
				<label for="extrComments" class="col-sm-2 control-label">描述</label>
				<div class="col-sm-10">
					<textarea class="form-control" rows="2" name="comments"
						id="extrComments"></textarea>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="button" class="btn btn-primary"
						onclick="submitExtractor()">提交</button>
					<button type="button" class="btn btn-default"
						onclick="closeAddDiv('addExtractorDiv')">取消</button>
				</div>
			</div>
		</form>
	</div>
</div>
<!--新增 编辑断言div  -->
<div id="addAssertDiv"
	class="panel panel-primary addDiv addDiv-size-2">
	<div id="assPanelHead" class="panel-heading">新增断言</div>
	<div class="panel-body">
		<form action="" id="addAssertForm" name="admin_assert_add"
			class="form-horizontal">
			<div class="form-group">
				<label for="assName" class="col-sm-2 control-label">Name</label>
				<div class="col-sm-10">
					<input id="assId" name="id" type="hidden"> <input
						id="assaid" name="aid" type="hidden" value="${api.id }"> <input
						type="text" class="form-control" id="assName" name="assertName"
						placeholder="请输入Name">
				</div>
			</div>						
			<div class="form-group">
				<label for="assRegular" class="col-sm-2 control-label">Regular</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="assRegular" name="assertRegular"
						placeholder="请输入正则表达式">
				</div>
			</div>
			<div class="form-group">
				<label for="expect" class="col-sm-2 control-label">expect</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="expect" name="expectValue"
						placeholder="请输入期望值">
				</div>
			</div>	
			<div class="form-group">
				<label for="assComments" class="col-sm-2 control-label">描述</label>
				<div class="col-sm-10">
					<textarea class="form-control" rows="2" name="comments"
						id="assComments"></textarea>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="button" class="btn btn-primary"
						onclick="submitAssert()">提交</button>
					<button type="button" class="btn btn-default"
						onclick="closeAddDiv('addAssertDiv')">取消</button>
				</div>
			</div>
		</form>
	</div>
</div>
<!-- page-header -->
<div>
	<div class="col-lg-12">
		<div class="page-header-p">接口配置和调试</div>
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
						href="#collapseOne2">接口名：${api.name }</a>
				</h4>
			</div>
			<div id="collapseOne2" class="panel-collapse collapse in">
				<div class="panel-body">
					<div class="form-group div-height">
						<label class="col-sm-1 control-label" for="apiURL"> URL </label>
						<div class="col-sm-11">
							<input type="text" class="form-control" id="apiURL"
								value="${api.url }" disabled>
						</div>
					</div>
					<div class="form-group div-height">
						<label class="col-sm-1 control-label" for="apiComments">
							描述 </label>
						<div class="col-sm-11">
							<input type="text" class="form-control" id="apiComments"
								value="${api.comments }" disabled>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--变量列表  -->
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">
					<a data-toggle="collapse" data-parent="#accordion2"
						href="#collapseTwo2">属性列表</a>
					<button type="button" class="btn btn-primary btn-xs margin-left"
						onclick="showAddDiv('addAttributeDiv')">新增属性</button>
					<button type="button" class="btn btn-primary btn-xs margin-left"
						onclick="showAddDiv('addExtractorDiv')">新增提取器</button>
					<button type="button" class="btn btn-primary btn-xs margin-left"
						onclick="showAddDiv('addAssertDiv')">新增断言</button>
				</h4>
			</div>
			<div id="collapseTwo2" class="panel-collapse collapse in">
				<div class="panel-body">
					<!-- Nav tabs -->
					<ul class="nav nav-tabs">
						<li class="active"><a href="#headerAttr" data-toggle="tab">header</a></li>
						<li><a href="#paramAttr" data-toggle="tab">param</a></li>
						<li><a href="#apiExtractor" data-toggle="tab">extractor</a></li>
						<li><a href="#apiAssert" data-toggle="tab">assert</a></li>
					</ul>
					<!-- Tab panes -->
					<div class="tab-content">
						<div class="tab-pane fade in active" id="headerAttr">
							<h5></h5>
							<div>
								<c:if test="${fn:length(has) < 1}">
									<div align="center">没有数据</div>
								</c:if>
								<c:if test="${fn:length(has) > 0}">
									<table
										class="table table-striped table-bordered table-hover listTable"
										id="dataTables-headerAttr">
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
						<div class="tab-pane fade" id="paramAttr">
							<h5></h5>
							<div>
								<c:if test="${fn:length(pas) < 1}">
									<div align="center">没有数据</div>
								</c:if>
								<c:if test="${fn:length(pas) > 0}">
									<table
										class="table table-striped table-bordered table-hover listTable"
										id="dataTables-paramAttr">
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
						<div class="tab-pane fade" id="apiExtractor">
							<h5></h5>
							<div>
								<c:if test="${fn:length(eas) < 1}">
									<div align="center">没有数据</div>
								</c:if>
								<c:if test="${fn:length(eas) > 0}">
									<table
										class="table table-striped table-bordered table-hover listTable"
										id="dataTables-relation">
										<thead>
											<tr>
												<th>Name</th>
												<th>Variable</th>
												<th>正则</th>
												<th>描述</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${eas }" var="ea">
												<tr>
													<td>${ea.extractorName }</td>
													<td>${ea.variableName }</td>
													<td>${ea.regularExpression }</td>
													<td>${ea.comments }</td>
													<td><a onclick="editExtractor(${ea.id});return false;">编辑<span
															class="glyphicon glyphicon-edit"></span></a> <a
														onclick="delExtractor(${ea.id});return false;">删除<span
															class="glyphicon glyphicon-trash"></span></a></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</c:if>
							</div>
						</div>
						<div class="tab-pane fade" id="apiAssert">
							<h5></h5>
							<div>
								<c:if test="${fn:length(aas) < 1}">
									<div align="center">没有数据</div>
								</c:if>
								<c:if test="${fn:length(aas) > 0}">
									<table
										class="table table-striped table-bordered table-hover listTable"
										id="dataTables-relation">
										<thead>
											<tr>
												<th>Name</th>
												<th>正则</th>
												<th>期望</th>
												<th>描述</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${aas }" var="aa">
												<tr>
													<td>${aa.assertName }</td>
													<td>${aa.assertRegular }</td>
													<td>${aa.expectValue }</td>
													<td>${aa.comments }</td>
													<td><a onclick="editAssert(${aa.id});return false;">编辑<span
															class="glyphicon glyphicon-edit"></span></a> <a
														onclick="delAssert(${aa.id});return false;">删除<span
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
						href="#collapseThree2">调试</a>
					<button type="button" class="btn btn-primary btn-xs margin-left"
						onclick="debugApi(${api.id})">运 行</button>
				</h4>
			</div>
			<div id="collapseThree2" class="panel-collapse collapse in">
				<div class="panel-body">
					<!-- Nav tabs -->
					<ul class="nav nav-tabs">
						<li class="active"><a href="#debugRequest" data-toggle="tab">Request</a>
						</li>
						<li><a href="#debugResponse" data-toggle="tab">Response</a></li>
						<li><a href="#debugPost" data-toggle="tab">Extractor/Assert</a></li>
					</ul>

					<!-- Tab panes -->
					<div class="tab-content">
						<div class="tab-pane fade in active" id="debugRequest">
							<h4></h4>
							<div>
								<textarea class="form-control" rows="4"
									id="debugRequestText" disabled></textarea>
							</div>
						</div>
						<div class="tab-pane fade" id="debugResponse">
							<h4></h4>
							<div>
								<textarea class="form-control" rows="4"
									id="debugResponseText" disabled></textarea>
							</div>
						</div>
						<div class="tab-pane fade" id="debugPost">
							<h4></h4>
							<div>
								<textarea class="form-control" rows="4"
									id="debugPostText" disabled></textarea>
							</div>
						</div>
						
					</div>
				</div>
			</div>
		</div>
	</div>
</div>



