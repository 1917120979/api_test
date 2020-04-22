function addProject() {
    $('#addProjectDiv').css({
	"display" : "block"
    });
    $('#layerBg').css({
	"display" : "block"
    });
    return false;
}

function submitProject() {
    if (!checkEmpty("name", "项目名"))
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
	    window.location.href = "admin_project_list";
	},
	error : function() {
	    alert("错误！！");
	}
    });
}

function cancel() {
    $('#addProjectDiv').css({
	"display" : "none"
    });
    $('#layerBg').css({
	"display" : "none"
    });
    window.location.reload();
}

function doProjectEdit(id) {
    $("#addProjectForm").attr("name", "admin_project_update");
    $("#subTitle").html("编辑项目");
    $.ajax({
	type : "post",
	dataType : "json",
	url : "admin_project_edit",
	data : {
	    "id" : id
	},
	success : function(data) {
	    var p = data.data;
	    $("#id").val(p.id);
	    $("#name").val(p.name);
	    $("#sign").val(p.sign);
	    $("#encrypt").val(p.encrypt);
	    $('#addProjectDiv').css({
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

function doProjectDel(id) {
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
		window.location.href = "admin_project_list";
	    },
	    error : function() {
		alert("系统错误");
	    }
	});
    }
}

