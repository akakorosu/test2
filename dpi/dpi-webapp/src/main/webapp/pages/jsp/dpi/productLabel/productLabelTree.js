var $productLabelTree_div1 = $("#productLabelTree_div1");//查询div
var $productLabelTree_div2 = $("#productLabelTree_div2");//标签详细div
var $productLabelTree_div3 = $("#productLabelTree_div3");//1级标签查询div
var $productLabelTree_div21 = $("#productLabelTree_div21");//标签详细div1
var $productLabelTree_div22 = $("#productLabelTree_div22");//标签详细div2
var $productLabelTree_div23 = $("#productLabelTree_div23");//标签详细div3
var $productLabelTree_div3_head = $("#productLabelTree_div3_head");//1级标签查询详细div
var productLabelTreeData;//缓存标签树
$(function(){
	var width = $("#productLabelTree").parent().width();//整个页面宽度
	var height = $("#productLabelTree").parent().height();//整个页面高度
	var div2Width = width - $productLabelTree_div3.width() - 200;//标签详细div设置的宽度
	var div2ChildrenWidth = (div2Width / 3);//标签详细div每个分别的宽度
	$productLabelTree_div2.width(div2Width);
	$productLabelTree_div21.width(div2ChildrenWidth);
	$productLabelTree_div22.width(div2ChildrenWidth);
	$productLabelTree_div23.width(div2ChildrenWidth);
	$.ajax({//获取标签树
		url : $.cxt + "/productLabel/selectlabeltree",
		dataType : "json",
		success : function(data) {
			productLabelTreeData = data;//赋值缓存
			showLablelByLabelCode();//初始化页面
		}
	});
	$("#inputFastSearch").keydown(function(){//查询输入框键盘查询事件
		setTimeout(function(){
			inputFastSearchFun();
		},500)
	});
	$("#productLabelTree").click(function(e){//点击其他元素将下拉框隐藏
		if($(e.target).attr("id") != "divFastSearch") {
			$("#divFastSearch").hide();
		}
	});
	
	$("#productLabelTree").parent().css({'background-color': '#FFF'});
	$("#inputFastSearch").css({'background-color': '#FFF'});
})

var inputFastSearchFun = function() {
	var v = $("#inputFastSearch").val();
	if(v == "") {//输入值为空则将下拉框清空，并隐藏
		$("#divFastSearch").html("");
		$("#divFastSearch").hide();
		return;
	}
	var data = {labelNameLike : v};
	$.ajax({//模糊查询标签
		url : $.cxt + "/productLabel/selectlist",
		dataType : "json",
		data : data,
		type : "post",
		loading : 0,//去掉加载遮盖层
		success : function(data) {
			var html = "";//拼接下拉框html
			for(var i = 0; i < data.length; i ++) {
				var o = data[i];
				html += "<div>";
				html += "<span>" + getSetLabelCloseWindowHtml(o, "1") + "</span>&nbsp;&nbsp;&nbsp;&nbsp;";
				html += getSetLabelCloseWindowHtml(o, "2");
				html += "</div>";
			}
			$("#divFastSearch").html(html);
			if(html == "") {//html为空则隐藏下拉框，否则显示
				$("#divFastSearch").hide();	
			} else {
				$("#divFastSearch").show();	
			}
		}
	});
}

var getLabelHtml = function(obj) {
	var html = "";
	var children = obj.children;
	if(children.length == 0) {
		html += "<div>"
	} else {
		html += "<div style=\"margin-bottom: 30px;\">"
	}
	html += "<div class=\"clearfix\">" + getSetLabelCloseWindowHtml(obj, "1") + "</div>"
	html += "<div class=\"clearfix\">"
	for(var i = 0; i < children.length; i ++) {
		var c = children[i];
		html += getSetLabelCloseWindowHtml(c, "2");
	}
	html += "</div>"
	html += "</div>"
	return html;
}

var getLabelHtmlNull = function() {
	var html = "";
	html += "<div style=\"height:36px\">"
	html += "<div class=\"clearfix\"></div>"
	html += "<div class=\"clearfix\"></div>"
	html += "</div>"
	return html;
}

var getSetLabelCloseWindowHtml = function(obj, level) {
	var o = {};
	if(level == "1") {
		o = {
			labelCode : obj.labelCode1,
			labelName : obj.labelName1,
		}
	}else if(level == "2") {
		o = {
			labelCode : obj.labelCode2,
			labelName : obj.labelName2,
		}
	}
	o.level = level;
	var os = JSON.stringify(o);
	var html = "<a onclick='setLabelCloseWindow(" + os + ")' href='#' labelCode='" + o.labelCode + "'>" + o.labelName + "</a>"
	return html;
}

var setLabelCloseWindow = function(cs) {
	topwindow.close(cs);
}

/**
 * 根据高度动态获取高度最小的元素追加html
 */
var setLabelHtml = function(html) {
	var d21Height = $productLabelTree_div21.height();
	var d22Height = $productLabelTree_div22.height();
	var d23Height = $productLabelTree_div23.height();
	if(d21Height <= d22Height && d21Height <= d23Height) {
		$productLabelTree_div21.append(html);
	} else if(d22Height <= d21Height && d22Height <= d23Height) {
		$productLabelTree_div22.append(html);
	} else if(d23Height <= d21Height && d23Height <= d22Height) {
		$productLabelTree_div23.append(html);
	}
}

var getLabelHeadHtml = function(obj) {
	var html = "<a onclick=\"showLablelByLabelCode('" + obj.labelCode1 + "')\" href=\"#\" labelCode=" + obj.labelCode1 + ">" + obj.labelName1 + "</a>";
	return html;
}

var setLabelHeadHtml = function(html) {
	$productLabelTree_div3_head.append(html);
}

var showLablelByLabelCode = function(labelCode) {
	$("#productLabelTree").parent().scrollTop(0);//将滚动条置顶
	$("#inputFastSearch").val("");//查询input清空
	$productLabelTree_div2.children("div").html("");//标签详细页清空
	var bl = $productLabelTree_div3_head.html() == "";//判断是否右侧查询div已加载过
	var b = true;
	if(labelCode == undefined) {//初始化
		for(var i = 0; i < productLabelTreeData.length; i ++) {
			var obj = productLabelTreeData[i];
			// 第一次有二级标签进入
			if(b && obj.children.length > 0) {
				// 取余
				var z = i % 3;
				// 余1则需要添加2个空
				if(z == 1) {
					setLabelHtml(getLabelHtmlNull());
					setLabelHtml(getLabelHtmlNull());
				// 余2则需要添加1个空
				} else if(z == 2) {
					setLabelHtml(getLabelHtmlNull());
				}
				// 添加三个空区分开
				setLabelHtml(getLabelHtmlNull());
				setLabelHtml(getLabelHtmlNull());
				setLabelHtml(getLabelHtmlNull());
				// 不再进入
				b = false;
			}
			var html = getLabelHtml(obj);//获取详细html
			setLabelHtml(html);//div2追加html
			if(bl) {//右侧div没有加载过才进行加载
				var htmlHead = getLabelHeadHtml(obj);//获取右侧查询html
				setLabelHeadHtml(htmlHead);//右侧查询div的追加
			}
		}
	} else {
		for(var i = 0; i < productLabelTreeData.length; i ++) {
			var obj = productLabelTreeData[i];
			if(obj.labelCode1 == labelCode) {//按1级标签查询
				var html = getLabelHtml(obj);
				setLabelHtml(html);
				break;
			}
		}
	}
}