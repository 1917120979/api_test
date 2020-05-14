/**
 * 检查是否为空
 * @param id
 * @param name
 * @returns
 */
function checkEmpty(id, name) {
    var value = $("#" + id).val();
    if (value.length == 0) {
	alert(name + "不能为空");
	$("#" + id)[0].focus();
	return false;
    }
    return true;
}

/**
 * 检查是否是数字
 * @param id
 * @param name
 * @returns
 */
function checkNumber(id, name) {
    var value = $("#" + id).val();
    if (value.length == 0) {
	alert(name + "不能为空");
	$("#" + id)[0].focus();
	return false;
    }
    if (isNaN(value)) {
	alert(name + "必须是数字");
	$("#" + id)[0].focus();
	return false;
    }

    return true;
}

/**
 * 检查是否是整数
 * @param id
 * @param name
 * @returns
 */
function checkInt(id, name) {
    var value = $("#" + id).val();
    if (value.length == 0) {
	alert(name + "不能为空");
	$("#" + id)[0].focus();
	return false;
    }
    if (parseInt(value) != value) {
	alert(name + "必须是整数");
	$("#" + id)[0].focus();
	return false;
    }

    return true;
}

/**
 * 展示所有已打开的tab
 * @returns
 */
function showAllTab(){
    var jsonItems = JSON.parse(localStorage.getItem("jsonItems"));
    for(var i in jsonItems){
	var tabItem = jsonItems[i];
	closableTab.addTab(tabItem);
    }
}

/**
 * 初始化加载首页和已打开的tab页面
 * @returns
 */
$(function() {
    var item = {
	'id' : '0',
	'name' : '首页',
	'url' : 'admin_home_list',
	'closable' : false
    };
    closableTab.addTab(item);
    showAllTab();
});

/**
 * 打开项目管理tab
 * @returns
 */
function addProjectTab() {
    var item = {
	'id' : '2',
	'name' : '项目管理',
	'url' : 'admin_project_list',
	'closable' : true
    };
    closableTab.addTab(item);
}

/**
 * 打开配置项目tab
 * @param pid
 * @returns
 */
function configProject(pid) {
    var url = 'admin_projectInfo_list?pid=' + pid;
    var item = {
	'id' : '3',
	'name' : '项目配置',
	'url' : url,
	'closable' : true
    };
    closableTab.addTab(item);
}

/**
 * 打开接口管理tab
 * @param pid
 * @returns
 */
function apiManage(pid){
    var url = 'admin_api_list?pid=' + pid;
    var item = {
	'id' : '4',
	'name' : '接口管理',
	'url' : url,
	'closable' : true
    };
    closableTab.addTab(item);
}

/**
 * 打开接口配置tab
 * @param aid
 * @returns
 */
function configApi(aid){
    var url = 'admin_apiInfo_list?aid=' + aid;
    var item = {
	'id' : '5',
	'name' : '接口调试',
	'url' : url,
	'closable' : true
    };
    closableTab.addTab(item);   
}

/**
 * 打开新增、编辑弹窗
 * @returns
 */
function showAddDiv(id) {
    $('#'+id).css({
	"display" : "block"
    });
    $('#layerBg').css({
	"display" : "block"
    });
}

/**
 * 取消新增、编辑弹窗
 * @returns
 */
function closeAddDiv(id) {
    $('#'+id).css({
	"display" : "none"
    });
    $('#layerBg').css({
	"display" : "none"
    });
}


/**
 * 提交新增/编辑项目form
 * @returns
 */
function submitProject() {
    if (!checkEmpty("projectName", "项目名"))
	return false;
    if (!checkEmpty("sign", "签名"))
	return false;
    if (!checkEmpty("encrypt", "加密"))
	return false;
    var targetUrl = $("#addProjectForm").attr("name");
    $.ajax({
	type : "post",
	dataType : "json",
	url : targetUrl,
	data : $("#addProjectForm").serialize(),
	success : function(data) {
	    alert(data.msg);
	    window.location.reload(true);
	},
	error : function() {
	    alert("错误！！");
	}
    });
}

/**
 * 获取项目信息
 * @param id
 * @returns
 */
function editProject(id) {
    $("#addProjectForm").attr("name", "admin_project_update");
    $("#projectPanelHead").html("编辑项目");
    $.ajax({
	type : "post",
	dataType : "json",
	url : "admin_project_edit",
	data : {
	    "id" : id
	},
	success : function(data) {
	    var p = data.data;
	    $("#projectId").val(p.id);
	    $("#projectName").val(p.name);
	    $("#sign").val(p.sign);
	    $("#encrypt").val(p.encrypt);
	    $("#pComments").val(p.comments);
	    
	    showAddDiv('addProjectDiv');
	},
	error : function() {
	    alert("系统错误！");
	}
    });
}

/**
 * 删除项目
 * @param id
 * @returns
 */
function delProject(id) {
    var flag = confirm("是否确认删除？");
    if (flag) {
	$.ajax({
	    type : "post",
	    dataType : "json",
	    url : "admin_project_delete",
	    data : {
		"id" : id
	    },
	    success : function(data) {
		alert(data.msg);
		window.location.reload(true);
	    },
	    error : function() {
		alert("系统错误！");
	    }
	});
    }
}

/**
 * 提交新增、编辑变量form
 * @returns
 */
function submitVariable() {
    if (!checkEmpty("varName", "Name"))
	return false;
    if (!checkEmpty("varValue", "Value"))
	return false;
    var targetUrl = $("#addVariableForm").attr("name");
    $.ajax({
	type : "post",
	dataType : "json",
	url : targetUrl,
	data : $("#addVariableForm").serialize(),
	success : function(data) {
	    alert(data.msg);
	    window.location.reload(true);
	},
	error : function() {
	    alert("错误！！");
	}
    });
}

/**
 * 编辑变量
 * @param id
 * @returns
 */
function editVariable(id) {
    $("#addVariableForm").attr("name", "admin_variable_update");
    $("#varPanelHead").html("编辑变量");
    $.ajax({
	type : "post",
	dataType : "json",
	url : "admin_variable_edit",
	data : {
	    "id" : id
	},
	success : function(data) {
	    var v = data.data;
	    $("#vid").val(v.id);
	    $("#varName").val(v.name);
	    $("#varValue").val(v.value);
	    $("#type").val(v.type);
	    $("#description").val(v.description);
	    
	    showAddDiv('addVariableDiv');
	},
	error : function() {
	    alert("系统错误");
	}
    });
}

/**
 * 删除变量
 * @param id
 * @returns
 */
function delVariable(id) {
    var flag = confirm("是否确认删除？");
    if (flag) {
	$.ajax({
	    type : "post",
	    dataType : "json",
	    url : "admin_variable_delete",
	    data : {
		"id" : id
	    },
	    success : function(data) {
		alert(data.msg);
		window.location.reload(true);
	    },
	    error : function() {
		alert("系统错误");
	    }
	});
    }
}

/**
 * 提交新增、编辑接口
 * @returns
 */
function submitApi() {
    if (!checkEmpty("apiName", "名称"))
	return false;
    if (!checkEmpty("protocol", "协议"))
	return false;
    if (!checkEmpty("url", "服务地址"))
	return false;
    if (!checkEmpty("method", "方法"))
	return false;
    if (!checkEmpty("dataType", "数据类型"))
	return false;
    
    var targetUrl = $("#addApiForm").attr("name");
    $.ajax({
	type : "post",
	dataType : "json",
	url : targetUrl,
	data : $("#addApiForm").serialize(),
	success : function(data) {
	    alert(data.msg);
	    window.location.reload(true);
	},
	error : function() {
	    alert("错误！！");
	}
    });
}

/**
 * 编辑接口
 * @param id
 * @returns
 */
function editApi(id) {
    $("#addApiForm").attr("name", "admin_api_update");
    $("#apiPanelHead").html("编辑接口");
    $.ajax({
	type : "post",
	dataType : "json",
	url : "admin_api_edit",
	data : {
	    "id" : id
	},
	success : function(data) {
	    var api = data.data;
	    $("#apiId").val(api.id);
	    $("#apiName").val(api.name);
	    $("#protocol").val(api.protocol);
	    $("#url").val(api.url);
	    $("#method").val(api.method);
	    $("#dataType").val(api.dataType);
	    $("#filesUpload").val(api.filesUpload);
	    $("#comments").val(api.comments);
	    
	    showAddDiv('addApiDiv');
	},
	error : function() {
	    alert("系统错误");
	}
    });
}

/**
 * 删除接口
 * @param id
 * @returns
 */
function delApi(id) {
    var flag = confirm("是否确认删除？");
    if (flag) {
	$.ajax({
	    type : "post",
	    dataType : "json",
	    url : "admin_api_delete",
	    data : {
		"id" : id
	    },
	    success : function(data) {
		alert(data.msg);
		window.location.reload(true);
	    },
	    error : function() {
		alert("系统错误");
	    }
	});
    }
}

/**
 * 提交新增、编辑变量form
 * @returns
 */
function submitAttribute() {
    if (!checkEmpty("attrName", "Name"))
	return false;
    if (!checkEmpty("attrValue", "Value"))
	return false;
    var targetUrl = $("#addAttributeForm").attr("name");
    $.ajax({
	type : "post",
	dataType : "json",
	url : targetUrl,
	data : $("#addAttributeForm").serialize(),
	success : function(data) {
	    alert(data.msg);
	    window.location.reload(true);
	},
	error : function() {
	    alert("错误！！");
	}
    });
}

/**
 * 编辑变量
 * @param id
 * @returns
 */
function editAttribute(id) {
    $("#addAttributeForm").attr("name", "admin_attribute_update");
    $("#attrPanelHead").html("编辑属性");
    $.ajax({
	type : "post",
	dataType : "json",
	url : "admin_attribute_edit",
	data : {
	    "id" : id
	},
	success : function(data) {
	    var v = data.data;
	    $("#attrid").val(v.id);
	    $("#attrName").val(v.name);
	    $("#attrValue").val(v.value);
	    $("#attrType").val(v.type);
	    $("#comments").val(v.comments);
	    
	    showAddDiv('addAttributeDiv');
	},
	error : function() {
	    alert("系统错误");
	}
    });
}

/**
 * 删除变量
 * @param id
 * @returns
 */
function delAttribute(id) {
    var flag = confirm("是否确认删除？");
    if (flag) {
	$.ajax({
	    type : "post",
	    dataType : "json",
	    url : "admin_attribute_delete",
	    data : {
		"id" : id
	    },
	    success : function(data) {
		alert(data.msg);
		window.location.reload(true);
	    },
	    error : function() {
		alert("系统错误");
	    }
	});
    }
}

/**
 * 提交新增、编辑变量form
 * @returns
 */
function submitExtractor() {
    if (!checkEmpty("extrName", "Name"))
	return false;
    if (!checkEmpty("extrVariable", "变量"))
	return false;
    if (!checkEmpty("extrRegular", "正则"))
	return false;
    var targetUrl = $("#addExtractorForm").attr("name");
    $.ajax({
	type : "post",
	dataType : "json",
	url : targetUrl,
	data : $("#addExtractorForm").serialize(),
	success : function(data) {
	    alert(data.msg);
	    window.location.reload(true);
	},
	error : function() {
	    alert("错误！！");
	}
    });
}

/**
 * 编辑变量
 * @param id
 * @returns
 */
function editExtractor(id) {
    $("#addExtractorForm").attr("name", "admin_extractor_update");
    $("#extrPanelHead").html("编辑属性");
    $.ajax({
	type : "post",
	dataType : "json",
	url : "admin_extractor_edit",
	data : {
	    "id" : id
	},
	success : function(data) {
	    var v = data.data;
	    $("#extrid").val(v.id);
	    $("#extrName").val(v.extractorName);
	    $("#extrVariable").val(v.variableName);
	    $("#extrRegular").val(v.regularExpression);
	    $("#extrComments").val(v.comments);
	    
	    showAddDiv('addExtractorDiv');
	},
	error : function() {
	    alert("系统错误");
	}
    });
}

/**
 * 删除变量
 * @param id
 * @returns
 */
function delExtractor(id) {
    var flag = confirm("是否确认删除？");
    if (flag) {
	$.ajax({
	    type : "post",
	    dataType : "json",
	    url : "admin_extractor_delete",
	    data : {
		"id" : id
	    },
	    success : function(data) {
		alert(data.msg);
		window.location.reload(true);
	    },
	    error : function() {
		alert("系统错误");
	    }
	});
    }
}

/**
 * 提交新增、编辑变量form
 * @returns
 */
function submitAssert() {
    if (!checkEmpty("assName", "Name"))
	return false;
    if (!checkEmpty("assRegular", "正则"))
	return false;
    if (!checkEmpty("expect", "期望"))
	return false;
    var targetUrl = $("#addAssertForm").attr("name");
    $.ajax({
	type : "post",
	dataType : "json",
	url : targetUrl,
	data : $("#addAssertForm").serialize(),
	success : function(data) {
	    alert(data.msg);
	    window.location.reload(true);
	},
	error : function() {
	    alert("错误！！");
	}
    });
}

/**
 * 编辑变量
 * @param id
 * @returns
 */
function editAssert(id) {
    $("#addAssertForm").attr("name", "admin_assert_update");
    $("#extrPanelHead").html("编辑属性");
    $.ajax({
	type : "post",
	dataType : "json",
	url : "admin_assert_edit",
	data : {
	    "id" : id
	},
	success : function(data) {
	    var v = data.data;
	    $("#assId").val(v.id);
	    $("#assName").val(v.assertName);
	    $("#assRegular").val(v.assertRegular);
	    $("#expect").val(v.expectValue);
	    $("#assComments").val(v.comments);
	    
	    showAddDiv('addAssertDiv');
	},
	error : function() {
	    alert("系统错误");
	}
    });
}

/**
 * 删除变量
 * @param id
 * @returns
 */
function delAssert(id) {
    var flag = confirm("是否确认删除？");
    if (flag) {
	$.ajax({
	    type : "post",
	    dataType : "json",
	    url : "admin_assert_delete",
	    data : {
		"id" : id
	    },
	    success : function(data) {
		alert(data.msg);
		window.location.reload(true);
	    },
	    error : function() {
		alert("系统错误");
	    }
	});
    }
}

function debugApi(aid){
	$.ajax({
	    type : "post",
	    dataType : "json",
	    url : "admin_debugResult_add",
	    data : {
		"aid" : aid
	    },
	    success : function(data) {
		    var result = data.data;
			alert(data.msg);
			$("#debugRequestText").val(result.debugRequest);
			$("#debugResponseText").val(result.debugResponse);
			$("#debugPostText").val(result.debugPost);
	    },
	    error : function() {
	    	alert("系统错误");
	    }
	});
}