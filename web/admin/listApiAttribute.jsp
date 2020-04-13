<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>
<script>
	var aid = ${api.id};
	var type = ${type};
	$("#pageselect").val(type);
	   
    function showAttrAddDiv() {
	$('#layer').css({
	    "display" : "block"
	});
	$('#layerBg').css({
	    "display" : "block"
	});
	return false;
    }
    
    function showExtrAddDiv() {
	$('#layer1').css({
	    "display" : "block"
	});
	$('#layerBg').css({
	    "display" : "block"
	});
	return false;
    }
    
    function showAssertAddDiv() {
	$('#layer2').css({
	    "display" : "block"
	});
	$('#layerBg').css({
	    "display" : "block"
	});
	return false;
    }

    function cancel() {
	$('#layer').css({
	    "display" : "none"
	});
	$('#layerBg').css({
	    "display" : "none"
	});
	window.location.reload();
    }
    
    function doAttrEdit(id){
	$("#addForm").attr("name","admin_apiAttribute_update");
	$("#subTitle").html("编辑属性");
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
	    },
	    error:function(data){
			alert("系统错误");
	    }
	});
	$('#layer').css({
	    "display" : "block"
	});
	$('#layerBg').css({
	    "display" : "block"
	});	
	return false;
    }   
    
    function doExtrEdit(id){
	$("#addForm1").attr("name","admin_extractor_update");
	$("#subTitle1").html("编辑提取器");
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
	    },
	    error:function(data){
			alert("系统错误");
	    }
	});
	$('#layer1').css({
	    "display" : "block"
	});
	$('#layerBg').css({
	    "display" : "block"
	});	
	return false;
    } 
    
    function doAssertEdit(id){
	$("#addForm2").attr("name","admin_assert_update");
	$("#subTitle2").html("编辑断言");
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
	    },
	    error:function(data){
			alert("系统错误");
	    }
	});
	$('#layer2').css({
	    "display" : "block"
	});
	$('#layerBg').css({
	    "display" : "block"
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
		var targetUrl = $("#addForm").attr("name");
	    $.ajax({
			type:"post",
		    dataType:"json",
	        url:targetUrl,
	        data: $("#addForm").serialize(),
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
		var targetUrl = $("#addForm1").attr("name");
	    $.ajax({
			type:"post",
		    dataType:"json",
	        url:targetUrl,
	        data: $("#addForm1").serialize(),
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
		var targetUrl = $("#addForm2").attr("name");
	    $.ajax({
			type:"post",
		    dataType:"json",
	        url:targetUrl,
	        data: $("#addForm2").serialize(),
	        success:function(data){
	            alert(data.msg);
	            window.location.href="admin_apiAttribute_list?aid="+aid;
	        },
	        error:function(e){
	            alert("错误！！");
	        }
	    });	
	} 
    function s_click(obj){
		var num = $("#pageselect").val();
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
</script>

<title>属性管理</title>

<div id="layerBg"></div>

<div class="workingArea">
    <ol class="breadcrumb">
		<li><a href="admin_project_list">所有项目</a></li>
		<li><a href="admin_projectVariable_list?pid=${api.project.id}">当前项目：${api.project.name}</a></li>
		<li><a href="admin_apiInfo_list?pid=${p.id}">当前接口：${api.apiName }</a></li>
		<li class="active">接口配置</li>
	</ol>
    <div id="listTitle">
		<span>
			接口属性列表 &nbsp;&nbsp;&nbsp;&nbsp;查询:
			<select id="pageselect" onchange="s_click(this)" > 
	   			<OPTION value="-1">全部</OPTION> 
				<OPTION value="0">请求头</OPTION> 
				<OPTION value="1">参数</OPTION> 
			</select>
			<button type="button" class="btn btn-success" onclick="showAttrAddDiv()">新增属性</button> 	
		</span>				
	</div>
    <div id="layer" class="panel panel-warning addApiDiv">
		<div class="panel-heading" id="subTitle">新增属性</div>
		<div class="panel-body">
			<form id="addForm" name= "admin_apiAttribute_add" action="">
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
    
    <div class="listDataTableDiv1 panel-warning">
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
							<a onclick="doAttrEdit(${attr.id});return false;" class="tda"><span class="glyphicon glyphicon-edit"></span></a>
							<a onclick="doAttrDelete(${attr.id});return false;" class="tda"><span class="glyphicon glyphicon-trash"></span></a>
						</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    
    <div id="listTitle">
		<span>
			提取器列表 		
			<button type="button" class="btn btn-success" onclick="showExtrAddDiv()">新增提取器</button> 	
		</span>				
	</div>
	 <div id="layer1" class="panel panel-warning addApiDiv">
		<div class="panel-heading" id="subTitle1">新增提取器</div>
		<div class="panel-body">
			<form id="addForm1" name= "admin_extractor_add" action="">
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
	<div class="listDataTableDiv1 panel-warning">
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
           		 <c:if test="${fn:length(extrs) <1}">
						<tr>
							<td colspan="5" align="center">没有数据</td>
						</tr>
				</c:if>
                <c:forEach items="${extrs}" var="e">
 
                    <tr>
                    	<td>${e.id}</td>
                        <td>${e.name}</td>
                        <td>${e.expression}</td>                  
                        <td>
							<a onclick="doExtrEdit(${e.id});return false;" class="tda"><span class="glyphicon glyphicon-edit"></span></a>
							<a onclick="doExtrDelete(${e.id});return false;" class="tda"><span class="glyphicon glyphicon-trash"></span></a>
						</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    
    <div id="listTitle">
		<span>
			断言列表 		
			<button type="button" class="btn btn-success" onclick="showAssertAddDiv()">新增断言</button> 	
		</span>				
	</div>
	 <div id="layer2" class="panel panel-warning addApiDiv">
		<div class="panel-heading" id="subTitle2">新增断言</div>
		<div class="panel-body">
			<form id="addForm2" name= "admin_assert_add" action="">
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
	<div class="listDataTableDiv1 panel-warning">
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
           		 <c:if test="${fn:length(asserts) <1}">
						<tr>
							<td colspan="5" align="center">没有数据</td>
						</tr>
				</c:if>
                <c:forEach items="${asserts}" var="ass">
 
                    <tr>
                    	<td>${ass.id}</td>
                        <td>${ass.assertExpress}</td>
                        <td>${ass.assertExpect}</td>                  
                        <td>
							<a onclick="doAssertEdit(${ass.id});return false;" class="tda"><span class="glyphicon glyphicon-edit"></span></a>
							<a onclick="doAssertDelete(${ass.id});return false;" class="tda"><span class="glyphicon glyphicon-trash"></span></a>
						</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    
    <div id="listTitle">
		<span>
			接口调试		
			<button type="button" class="btn btn-success" onclick="apiDebug()">调试</button> 	
		</span>				
	</div>
	<div class="listDataTableDiv1 panel-warning">
        <table
            class="table table-striped table-bordered table-hover  table-condensed">
            <thead>
                <tr class="success">
                	<th>编号</th>
                    <th>接口请求</th>
                    <th>接口返回</th>
                    <th>提取器</th>
                    <th>断言</th>
                </tr>
            </thead>
            <tbody>
           		 <c:if test="${fn:length(rs) <1}">
						<tr>
							<td colspan="5" align="center">没有数据</td>
						</tr>
				</c:if>
            </tbody>
        </table>
    </div>
</div>
