<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>
<script>
	var aid = ${api.id};
	var type = ${type};
	$(function(){
	    $("#pageSelect").val(type);	
	    $("#dataType").val(${api.dataType});	
	});
	   
    function showAttrAddDiv() {
		$('#addAttributeDiv').css({
		    "display" : "block"
		});
		$('#layerBg').css({
		    "display" : "block"
		});
		return false;
    }
    
    function showExtrAddDiv() {
		$('#addExtractorDiv').css({
		    "display" : "block"
		});
		$('#layerBg').css({
		    "display" : "block"
		});
		return false;
    }
    
    function showAssertAddDiv() {
		$('#addAssertDiv').css({
		    "display" : "block"
		});
		$('#layerBg').css({
		    "display" : "block"
		});
		return false;
    }

    function cancel() {
		$('#addAttributeDiv').css({
		    "display" : "none"
		});
		$('#layerBg').css({
		    "display" : "none"
		});
		window.location.reload();
    }
    
    function doAttrEdit(id){
		$("#addAttributeForm").attr("name","admin_apiAttribute_update");
		$("#addAttributeTitle").html("编辑属性");
		$.ajax({
		    type:"post",
		    dataType:"json",
		    url:"admin_apiAttribute_edit",
		    data:{
				"id":id
		    },
		    success:function(data){
				var attr = data.data;
				$("#attrId").val(attr.id);
				$("#attributeName").val(attr.attributeName);
				$("#attributeValue").val(attr.attributeValue);
				$("#type").val(attr.type);
				$('#addAttributeDiv').css({
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
		return false;
    }   
    
    function doExtrEdit(id){
		$("#addExtractorForm").attr("name","admin_extractor_update");
		$("#addExtractorTitle").html("编辑提取器");
		$.ajax({
		    type:"post",
		    dataType:"json",
		    url:"admin_extractor_edit",
		    data:{
				"id":id
		    },
		    success:function(data){
				var extr = data.data;
				$("#extrId").val(extr.id);
				$("#variableName").val(extr.name);
				$("#expression").val(extr.expression);
				$('#addExtractorDiv').css({
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
		return false;
    } 
    
    function doAssertEdit(id){
		$("#addAssertForm").attr("name","admin_assert_update");
		$("#addAssertTitle").html("编辑断言");
		$.ajax({
		    type:"post",
		    dataType:"json",
		    url:"admin_assert_edit",
		    data:{
				"id":id
		    },
		    success:function(data){
				var ass = data.data;
				$("#assertId").val(ass.id);
				$("#assertExpress").val(ass.assertExpress);
				$("#assertExpect").val(ass.assertExpect);
				$('#addAssertDiv').css({
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
		
		return false;
    } 
    
    function doAttrDelete(id){
		var flag = confirm("是否确认删除？");
		if(flag){
		    $.ajax({
			    type:"post",
			    dataType:"json",
			    url:"admin_apiAttribute_delete",
			    data:{
					"id":id
			    },
			    success:function(data){
					alert(data.msg);
					window.location.href="admin_apiAttribute_list?aid="+aid;
			    },
			    error:function(data){
					alert("系统错误");
			    }
			});
		}
    }
    function doExtrDelete(id){
		var flag = confirm("是否确认删除？");
		if(flag){
		    $.ajax({
			    type:"post",
			    dataType:"json",
			    url:"admin_extractor_delete",
			    data:{
					"id":id
			    },
			    success:function(data){
					alert(data.msg);
					window.location.href="admin_apiAttribute_list?aid="+aid;
			    },
			    error:function(data){
					alert("系统错误");
			    }
			});
		}
	}
    
    function doAssertDelete(id){
		var flag = confirm("是否确认删除？");
		if(flag){
		    $.ajax({
			    type:"post",
			    dataType:"json",
			    url:"admin_assert_delete",
			    data:{
					"id":id
			    },
			    success:function(data){
					alert(data.msg);
					window.location.href="admin_apiAttribute_list?aid="+aid;
			    },
			    error:function(data){
					alert("系统错误");
			    }
			});
		}
	}
    
    function submitAttrForm(){
		if (!checkEmpty("attributeName","属性名称"))
			return false;
	    if (!checkEmpty("attributeValue","属性值"))
			return false;
	    if (!checkEmpty("type","属性类型"))
			return false;
		var targetUrl = $("#addAttributeForm").attr("name");
	    $.ajax({
			type:"post",
		    dataType:"json",
	        url:targetUrl,
	        data: $("#addAttributeForm").serialize(),
	        success:function(data){
	            alert(data.msg);
	            window.location.href="admin_apiAttribute_list?aid="+aid;
	        },
	        error:function(e){
	            alert("错误！！");
	        }
	    });	
    }
    function submitExtrForm(){
		if (!checkEmpty("variableName","变量名称"))
			return false;
	    if (!checkEmpty("expression","提取正则"))
			return false;
		var targetUrl = $("#addExtractorForm").attr("name");
	    $.ajax({
			type:"post",
		    dataType:"json",
	        url:targetUrl,
	        data: $("#addExtractorForm").serialize(),
	        success:function(data){
	            alert(data.msg);
	            window.location.href="admin_apiAttribute_list?aid="+aid;
	        },
	        error:function(e){
	            alert("错误！！");
	        }
	    });	
	}  
    function submitAssertForm(){
		if (!checkEmpty("assertExpress","断言正则"))
			return false;
	    if (!checkEmpty("assertExpect","期望结果"))
			return false;
		var targetUrl = $("#addAssertForm").attr("name");
	    $.ajax({
			type:"post",
		    dataType:"json",
	        url:targetUrl,
	        data: $("#addAssertForm").serialize(),
	        success:function(data){
	            alert(data.msg);
	            window.location.href="admin_apiAttribute_list?aid="+aid;
	        },
	        error:function(e){
	            alert("错误！！");
	        }
	    });	
	} 
    function selectClick(obj){
		var num = $("#pageSelect").val();
		if(num == -1){
		    window.location.href="admin_apiAttribute_list?aid="+aid;
		}
		if(num == 0){
		    window.location.href="admin_apiAttribute_list?aid="+aid+"&type=0";
		}
		if(num == 1){
		    window.location.href="admin_apiAttribute_list?aid="+aid+"&type=1";
		}   
    }
    
    function apiDebug(){
    	$.ajax({
    	    type:"post",
    	    dataType:"json",
    	    url:"admin_debugResult_add",
    	    data:{
    			"aid":aid
    	    },
    	    success:function(data){
    	    	alert(data.msg); 	    	 	    	
	            window.location.href="admin_apiAttribute_list?aid="+aid;
    	    },
    	    error:function(data){
    			alert("系统错误");
    	    }
    	});
    	
    }
    
    function deleteDebugResult(){
		var flag = confirm("是否确认删除？");
		if(flag){
		    $.ajax({
			    type:"post",
			    dataType:"json",
			    url:"admin_debugResult_delete",
			    data:{
					"aid":aid
			    },
			    success:function(data){
					alert(data.msg);
					window.location.href="admin_apiAttribute_list?aid="+aid;
			    },
			    error:function(data){
					alert("系统错误");
			    }
			});
		}
    }
    
    function submitApiForm(){
        if (!checkEmpty("apiName","接口名称"))
    		return false;
        if (!checkEmpty("url","请求地址"))
    		return false;
        if (!checkEmpty("method","方法"))
    		return false;
        if (!checkEmpty("dataType","数据类型"))
    		return false;
        
    	var targetUrl = $("#editAPIForm").attr("name");
    	var formData = $("#editAPIForm").serialize();
    	$.ajax({
    		type:"post",
    		dataType:"json",
    		url:targetUrl,
    		data:formData,
    		success:function(data){
    	    alert(data.msg);
    	    window.location.href="admin_apiAttribute_list?aid="+aid;
    	},
    	error:function(e){
    	    alert("错误！！");
    	}
    	});	
    }  
</script>

<title>接口配置和调试</title>

<div id="layerBg"></div>

<div class="workingArea">
	<div id="workNav">
		<ol>
		    <li><a href="admin_project_list">所有项目</a></li>
			<li><a href="admin_projectVariable_list?pid=${api.project.id}">${api.project.name}</a></li>
			<li><a href="admin_apiInfo_list?pid=${api.project.id}">${api.apiName }</a></li>
			<li class="active">接口配置</li>
	    </ol>
	</div>
	
	<div class="editAPIDiv">
			<form id="editAPIForm" name="admin_apiInfo_update" action="">
				<table class="editTable">
					<tr>
						<td rowspan="2" id="apiInfo">接口信息</td>
						<td>接口名称</td>
						<td>
							<input id="aid" name="aid" type="hidden" value="${api.id}">
							<input id="api_pid" name="pid" type="hidden" value="${api.project.id}">
							<input id="api_gid" name="gid" type="hidden" value="${api.group.id}">
							<input id="apiName" name="apiName" type="text" class="form-control" value="${api.apiName}">
						</td>
						<td>方法</td>
						<td><input id="method" name="method" type="text"
							class="form-control" value="${api.method}">
						</td>
						<td>数据类型</td>
						<td>
							<select id="dataType" name="dataType">
								<option value="0">其他</option>
								<option value="1">json</option>
							</select>
						</td>
						<td rowspan="2" align="center" id="editButton">
							<button type="button" class="btn btn-success"
								onclick="submitApiForm()">修改</button>
						</td>
					</tr>
					<tr>
						<td>服务地址</td>
						<td colspan="5" ><input id="url" name="url" type="text"
							class="form-control" value="${api.url}"></td>
					</tr>
				</table>
			</form>
		</div>
	
    <div id="listTitle">
		<span>
			接口属性列表 &nbsp;&nbsp;&nbsp;&nbsp;查询:
			<select id="pageSelect" onchange="selectClick(this)" > 
	   			<OPTION value="-1">全部</OPTION> 
				<OPTION value="0">请求头</OPTION> 
				<OPTION value="1">参数</OPTION> 
			</select>
			<button type="button" class="btn btn-success" onclick="showAttrAddDiv()">新增属性</button>
			<button type="button" class="btn btn-success" onclick="showExtrAddDiv()">新增提取器</button> 
			<button type="button" class="btn btn-success" onclick="showAssertAddDiv()">新增断言</button>	
		</span>	
		<span class="floatRight">		
			<button type="button" class="btn btn-success" onclick="apiDebug()">接口调试</button> 
			<button type="button" class="btn btn-success" onclick="deleteDebugResult()">清空调试结果</button>	
		</span>			
	</div>
    <div id="addAttributeDiv" class="panel panel-warning">
		<div class="panel-heading" id="addAttributeTitle">新增属性</div>
		<div class="panel-body">
			<form id="addAttributeForm" name= "admin_apiAttribute_add" action="">
				<table class="addTable">
					<tr>
						<td>属性名</td>
						<td>
							<input id="attrId" name="attrId" type="hidden">
							<input id="aid" name="aid" type="hidden" value="${api.id }">
							<input id="attributeName" name="attributeName" type="text"
							class="form-control"></td>
					</tr>
					<tr>
						<td>属性值</td>
						<td><input id="attributeValue" name="attributeValue" type="text"
							class="form-control">
							
						</td>
					</tr>
					<tr>
						<td>类型</td>
						<td>
							<select id="type" name="type">
								<option value="0">请求头</option>
								<option value="1" >参数</option>								
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<button type="button" class="btn btn-success" onclick="submitAttrForm()">提 交</button>
							<button type="button" class="btn" onclick="cancel()">取 消</button>
						</td>
					</tr>					
				</table>
			</form>
		</div>
	</div>
    <div id="addExtractorDiv" class="panel panel-warning ">
		<div class="panel-heading" id="addExtractorTitle">新增提取器</div>
		<div class="panel-body">
			<form id="addExtractorForm" name= "admin_extractor_add" action="">
				<table class="addTable">
					<tr>
						<td>变量名称</td>
						<td>
							<input id="extrId" name="extrId" type="hidden">
							<input id="extr_aid" name="extr_aid" type="hidden" value="${api.id }">
							<input id="variableName" name="variableName" type="text"
							class="form-control"></td>
					</tr>
					<tr>
						<td>提取正则</td>
						<td><input id="expression" name="expression" type="text"
							class="form-control">
							
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<button type="button" class="btn btn-success" onclick="submitExtrForm()">提 交</button>
							<button type="button" class="btn" onclick="cancel()">取 消</button>
						</td>
					</tr>					
				</table>
			</form>
		</div>
	</div>
	<div id="addAssertDiv" class="panel panel-warning">
		<div class="panel-heading" id="addAssertTitle">新增断言</div>
		<div class="panel-body">
			<form id="addAssertForm" name= "admin_assert_add" action="">
				<table class="addTable">
					<tr>
						<td>断言正则</td>
						<td>
							<input id="assertId" name="assertId" type="hidden">
							<input id="ass_aid" name="ass_aid" type="hidden" value="${api.id }">
							<input id="assertExpress" name="assertExpress" type="text"
							class="form-control"></td>
					</tr>
					<tr>
						<td>期望结果</td>
						<td><input id="assertExpect" name="assertExpect" type="text"
							class="form-control">
							
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<button type="button" class="btn btn-success" onclick="submitAssertForm()">提 交</button>
							<button type="button" class="btn" onclick="cancel()">取 消</button>
						</td>
					</tr>					
				</table>
			</form>
		</div>
	</div>
	
    <div class="listDataTableDiv panel-warning">
        <table
            class="table table-striped table-bordered table-hover  table-condensed">
            <thead>
                <tr class="success">
               		<th>ID</th>
                    <th>属性名称</th>
                    <th>属性值</th>
                    <th>类型</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
           		<c:if test="${fn:length(attrs) <1}">
						<tr>
							<td colspan="5" align="center">没有数据</td>
						</tr>
				</c:if>
                <c:forEach items="${attrs}" var="attr">
 
                    <tr>
                    	<td>${attr.id}</td>
                        <td>${attr.attributeName}</td>
                        <td>${attr.attributeValue}</td>
                        <td>
                        	<c:choose>
                        		<c:when test="${attr.type == 0 }">请求头</c:when>
                        		<c:when test="${attr.type == 1 }">参数</c:when>
                        	</c:choose>
                        </td>
                        
                        <td>
							<a onclick="doAttrEdit(${attr.id});return false;">编辑<span class="glyphicon glyphicon-edit"></span></a>
							<a onclick="doAttrDelete(${attr.id});return false;">删除<span class="glyphicon glyphicon-trash"></span></a>
						</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    
    <c:if test="${fn:length(extrs) >0}">
		<div>
			<div id="listTitle">
				<span>
					提取器列表 				 	
				</span>				
			</div>	 
			<div class="panel-warning">
		        <table
		            class="table table-striped table-bordered table-hover  table-condensed">
		            <thead>
		                <tr class="success">
		                	<th>编号</th>
		                    <th>变量名称</th>
		                    <th>提取正则</th>
		                    <th>操作</th>
		                </tr>
		            </thead>
		            <tbody>
		                <c:forEach items="${extrs}" var="e">
		 
		                    <tr>
		                    	<td>${e.id}</td>
		                        <td>${e.name}</td>
		                        <td>${e.expression}</td>                  
		                        <td>
									<a onclick="doExtrEdit(${e.id});return false;">编辑<span class="glyphicon glyphicon-edit"></span></a>
									<a onclick="doExtrDelete(${e.id});return false;">删除<span class="glyphicon glyphicon-trash"></span></a>
								</td>
		                    </tr>
		                </c:forEach>
		            </tbody>
		        </table>
		    </div>
		</div>
	</c:if>
    
    <c:if test="${fn:length(asserts) >0}">
		<div>
			<div id="listTitle">
				<span>
					断言列表 				 	
				</span>				
			</div>	 
			<div class="panel-warning">
		        <table
		            class="table table-striped table-bordered table-hover  table-condensed">
		            <thead>
		                <tr class="success">
		                	<th>编号</th>
		                    <th>断言正则</th>
		                    <th>期望结果</th>
		                    <th>操作</th>
		                </tr>
		            </thead>
		            <tbody>
		    
		                <c:forEach items="${asserts}" var="ass">
		 
		                    <tr>
		                    	<td>${ass.id}</td>
		                        <td>${ass.assertExpress}</td>
		                        <td>${ass.assertExpect}</td>                  
		                        <td>
									<a onclick="doAssertEdit(${ass.id});return false;">编辑<span class="glyphicon glyphicon-edit"></span></a>
									<a onclick="doAssertDelete(${ass.id});return false;">删除<span class="glyphicon glyphicon-trash"></span></a>
								</td>
		                    </tr>
		                </c:forEach>
		            </tbody>
		        </table>
		    </div>
		</div>
	</c:if>
    
    <c:if test="${fn:length(drs) >0}">
		<div>
			<div id="listTitle">
				<span>
					调试结果列表 				 	
				</span>				
			</div>
			<div id="debugResult" class="listDataTableDiv panel-warning">
		        <table class="table table-striped table-bordered table-hover table-condensed">
		            <thead>
		                <tr class="success">
		                	<th>时间</th>
		                    <th>接口请求</th>
		                    <th>接口返回</th>
		                    <th>提取器</th>
		                    <th>断言</th>
		                </tr>
		            </thead>
		            <tbody>
		
						<c:forEach items="${drs}" var="d">
		                    <tr>
		                    	<td>${d.date}</td>
		                        <td>${d.debugReq}</td>
		                        <td>${d.debugResp}</td>                  
		                        <td>${d.debugExtractor}</td>
		                        <td>${d.debugAssert}</td>
		                    </tr>
		               	</c:forEach>
		            </tbody>
		        </table>
		    </div>	
		</div>
	</c:if>
    
	
</div>
