
function addVariable() {
    $('#addVariableDiv').css({
	"display" : "block"
    });
    $('#layerBg').css({
	"display" : "block"
    });
    return false;
}

function submitVariable() {
    if (!checkEmpty("name", "名称"))
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

function cancel() {
    $('#addVariableDiv').css({
	"display" : "none"
    });
    $('#layerBg').css({
	"display" : "none"
    });
    window.location.reload();
}

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
	    $("#id").val(v.id);
	    $("#name").val(v.name);
	    $("#value").val(v.value);
	    $("#type").val(v.type);
	    $("#description").val(v.description);
	    
	    $('#addVariableDiv').css({
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

function apiManage(pid){
    window.location.href="admin_api_list?pid="+pid;
}

