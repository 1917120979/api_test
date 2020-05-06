$(function() {
    var item = {
	'id' : '1',
	'name' : '首页',
	'url' : 'admin_home_list',
	'closable' : false
    };
    closableTab.addTab(item);
    showAllTab();
});

function addProjectTab() {
    var item = {
	'id' : '2',
	'name' : '项目管理',
	'url' : 'admin_project_list',
	'closable' : true
    };
    closableTab.addTab(item);
}

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

function showAllTab(){
    var jsonItems = JSON.parse(localStorage.getItem("jsonItems"));
    for(var i in jsonItems){
	var tabItem = jsonItems[i];
	closableTab.addTab(tabItem);
    }
}
/*document.onkeydown = function(e) {
    var ev = window.event || e;
    var code = ev.keyCode || ev.which;
    if (code == 116) {
	ev.keyCode ? ev.keyCode = 0 : ev.which = 0;
	cancelBubble = true;
	return false;
    }
} //禁止f5刷新
document.οncοntextmenu = function() {
    return false
};//禁止右键刷新*/