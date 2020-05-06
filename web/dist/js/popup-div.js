// 上面按钮点击事件
function popup(date) {
    /*
     * 创建div的样式，宽200px,高80px，下面的是css样式居中，
     * css样式居中具体了解链接：https://blog.csdn.net/A_Bear/article/details/80546181
     */
    // 创建一个div
    var layer = document.createElement("div");
    // 设置div的id值
    layer.id = "layer";
    var style = {
	background : "#f00",
	position : "absolute",
	zIndex : 10,
	width : "200px",
	height : "80px",
	left : "50%",
	top : "50%",
	marginLeft : "-100px",
	marginTop : "-40px"
    }
    for ( var i in style)
	layer.style[i] = style[i];
    // 当找不到id为layer的控件时
    if (document.getElementById("layer") == null) {
	// 在body中添加layer控件（layer在上面创建的）
	document.body.appendChild(layer);
	// 设置显示类容
	layer.innerHTML = date;
	// 将div中文本居中
	layer.style.textAlign = "center";
	layer.style.lineHeight = "80px"; // 作用是调节字体行高与div同高，使其保持水平居中
	// 设置3s后去掉弹出窗
	setTimeout("document.body.removeChild(layer)", 3000)
    }
    change();
}

// 下面是计时控件js

function change() {
    var today;
    today = new Date();
    timeString = today.toLocaleString();
    document.getElementById("date").innerHTML = timeString;
    setTimeout("change();", 100);
}
