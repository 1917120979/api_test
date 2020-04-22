function checkEmpty(id, name) {
    var value = $("#" + id).val();
    if (value.length == 0) {
	alert(name + "不能为空");
	$("#" + id)[0].focus();
	return false;
    }
    return true;
}
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
