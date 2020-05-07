function addApi() {
    $('#addApiDiv').css({
	"display" : "block"
    });
    $('#layerBg').css({
	"display" : "block"
    });
    return false;
}

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
	    $("#apiName").val(api.name);
	    $("#protocol").val(api.protocol);
	    $("#url").val(api.url);
	    $("#method").val(api.method);
	    $("#dataType").val(api.dataType);
	    $("#filesUpload").val(api.filesUpload);
	    $("#comments").val(api.comments);
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

