//子页面不用iframe，用div展示
var closableTab = {
    jsonItems : {},
    // 添加tab
    addTab : function(tabItem) {

	var id = "tab_seed_" + tabItem.id;
	var container = "tab_container_" + tabItem.id;

	$("li[id^=tab_seed_]").removeClass("active");
	$("div[id^=tab_container_]").removeClass("active");

	if (!$('#' + id)[0]) {
	    var li_tab = '<li role="presentation" class="" id="'
		    + id
		    + '"><a href="#'
		    + container
		    + '"  role="tab" data-toggle="tab" style="position: relative;padding:5px 20px;">'
		    + tabItem.name;
	    if (tabItem.closable) {
		li_tab = li_tab
			+ '<i class="glyphicon glyphicon-remove small" tabclose="'
			+ id
			+ '" style="position: absolute;right:4px;top: 4px;"  onclick="closableTab.closeTab(this)"></i></a></li> ';
	    } else {
		li_tab = li_tab + '</a></li>';
	    }

	    var tabpanel = '<div role="tabpanel" class="tab-pane" id="'
		    + container + '" style="width: 100%;">' + '正在加载...'
		    + '</div>';

	    $('#nav-tabs').append(li_tab);
	    $('#tab-content').append(tabpanel);
	    $('#' + container).load(tabItem.url,
		    function(response, status) {
			if (status == 'error') {// status的值为success和error，如果error则显示一个错误页面
			    $(this).html(response);
			}
		    });
	} else {
	    $('#' + container).load(tabItem.url,
		    function(response, status) {
			if (status == 'error') {// status的值为success和error，如果error则显示一个错误页面
			    $(this).html(response);
			}
		    });
	}
	$("#" + id).addClass("active");
	$("#" + container).addClass("active");
	var strItems = localStorage.getItem("jsonItems");
	if (null != strItems) {
	    this.jsonItems = JSON.parse(strItems);
	}
	this.jsonItems[container] = tabItem;
	localStorage.setItem("jsonItems", JSON.stringify(this.jsonItems));
    },
    // 关闭tab
    closeTab : function(item) {
	var val = $(item).attr('tabclose');
	var containerId = "tab_container_" + val.substring(9);

	if ($('#' + containerId).hasClass('active')) {
	    $('#' + val).prev().addClass('active');
	    $('#' + containerId).prev().addClass('active');
	}
	$("#" + val).remove();
	$("#" + containerId).remove();
	
	var strItems = localStorage.getItem("jsonItems");
	this.jsonItems = JSON.parse(strItems);
	delete this.jsonItems[containerId];
	localStorage.setItem("jsonItems", JSON.stringify(this.jsonItems));
    }
}