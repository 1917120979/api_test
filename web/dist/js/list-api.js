function addGroup() {
    $('#addGroupDiv').css({
	"display" : "block"
    });
    $('#layerBg').css({
	"display" : "block"
    });
    return false;
}

function submitGroup() {
    if (!checkEmpty("name", "名称"))
	return false;  
    var targetUrl = $("#addGroupForm").attr("name");
    $.ajax({
	type : "post",
	dataType : "json",
	url : targetUrl,
	data : $("#addGroupForm").serialize(),
	success : function(data) {
	    alert(data.msg);
	    window.location.reload(true);
	},
	error : function() {
	    alert("错误！！");
	}
    });
}

function editGroup(id) {
    $("#addGroupForm").attr("name", "admin_group_update");
    $("#groupPanelHead").html("编辑分组");
    $.ajax({
	type : "post",
	dataType : "json",
	url : "admin_group_edit",
	data : {
	    "id" : id
	},
	success : function(data) {
	    var g = data.data;
	    $("#gid").val(g.id);
	    $("#name").val(g.name);
	    $('#addGroupDiv').css({
		"display" : "block"
	    });
	    $('#layerBg').css({
		"display" : "block"
	    });
	},
	error : function() {
	    alert("系统错误");
	}
    });
}

function delGroup(id) {
    var flag = confirm("是否确认删除？");
    if (flag) {
	$.ajax({
	    type : "post",
	    dataType : "json",
	    url : "admin_group_delete",
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

function addApi(gid) {
    $('#addApiDiv').css({
	"display" : "block"
    });
    $('#layerBg').css({
	"display" : "block"
    });
    $("#agid").val(gid);
    return false;
}

function submitApi() {
    if (!checkEmpty("apiName", "名称"))
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

function cancel() {
    $('#addApiDiv').css({
	"display" : "none"
    });
    $('#layerBg').css({
	"display" : "none"
    });
    window.location.reload();
}

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
	    $("#agid").val(api.group.id);
	    $("#apiName").val(api.name);
	    $("#url").val(api.url);
	    $("#method").val(api.method);
	    $("#dataType").val(api.dataType);
	    
	    $('#addApiDiv').css({
		"display" : "block"
	    });
	    $('#layerBg').css({
		"display" : "block"
	    });
	},
	error : function() {
	    alert("系统错误");
	}
    });
}

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

