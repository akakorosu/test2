var firstActiveMenuId = "";
$(function(){
	//$("#sidebar").height($(window).height() - 45);
	$.ajax({//请求菜单树
		url : $.cxt + "/sysmenu/selectjoinsysrolemenutree", 
		dataType:'json',
		type: "POST",
		success : function(data) {
			
			var listData = data[0].children;
			var srccs=$.cxt+listData[0].menuUrl;
			
			for (x in listData) {
			    $("#flex-list").append('<li class="flex-item" data-src="'+listData[x].menuUrl+'"><div class="item-wrap"><div class="item-icon"></div><div class="item-desc">'+ listData[x].menuName +'</div></div></li>')
			}
			$("#contentLoader").attr("src",srccs)//给iframe赋值src跳转,初始界面
			
			$('.flex-item').bind('click',function(){
				//console.log($(this).attr("data-src"));
				var src=$.cxt +$(this).attr("data-src");
				$("#contentLoader").attr("src",src)//给iframe赋值src跳转
			})
		}
	});
	$('[data-toggle="tooltip"]').tooltip()//
	$(".menuControl").click(function(){
		if($(".sidebar").hasClass("menu-min")){
			doMenuSwitch();
		}else{
			doMenuSwitchMin();
		}
		var $table = $("#contentLoader").contents().find("#gridContainer #grid-table");
		if($table.length > 0) {
			var $pageContent = $("#contentLoader").contents().find(".page-content");
			var w = 24;
			if($pageContent.find("div:first-child").hasClass("left-full")) {
				w = 24 + $pageContent.find(".left-full").width() + 12;
			}
			var width = $pageContent.width() - w;
            $table.setGridWidth(width);
            $("#contentLoader").contents().find("#gridContainer").find("#gbox_grid-table").find("#gview_grid-table").width(width);
            $("#contentLoader").contents().find("#gridContainer").find("#gbox_grid-table").find("#grid-pager").width(width);
		}
	})
})
//标准菜单
var doMenuSwitch = function() {
	$(".sidebar").removeClass("menu-min");
	$(".sidebar").css({overflow:"auto"});
}
//收缩菜单
var doMenuSwitchMin = function() {
	$(".sidebar").addClass("menu-min");
	$(".sidebar").css({overflow:""});
}
//添加菜单
var getChildrenMenu = function(menu) {
	var html = "";
	if(menu.menuUrl != null && menu.menuUrl != "") {//有url给url赋值，没有则不赋值
		html += "<a menuLevel=\"" + menu.menuLevel + "\" menuId=\"" + menu.menuId + "\" href=\"#\" data-operations=\"" + menu.operations + "\" data-src=\"" + ($.cxt + menu.menuUrl) + "\" class=\"dropdown-toggle getSrc\">";
		if(firstActiveMenuId == "") {
			firstActiveMenuId = menu.menuId;//保存第一个url的菜单id
		}
	} else {
		html += "<a href=\"#\" class=\"dropdown-toggle\">";
	}
	var icons=menu.iconPath;
	if(icons == null){
		icons='fa-caret-right';//子菜单默认图标
	}
	html += "<i class=\"menu-icon fa "+icons+"\"></i>";//菜单前面的小图标
	html += "<span class=\"menu-text\">" + menu.menuName + "</span>";//菜单名称
	if(menu.children != null) {//如果有子菜单则在右侧添加向下的小箭头
		html += "<b class=\"arrow fa fa-angle-down\"></b>"
	}
	html += "</a>";
	html += "<b class=\"arrow\"></b>";//添加线
	if(menu.children != null) {//如果有子菜单递归添加子菜单
		html += getChildrenMenu1(menu);				
	}
	return html;
}
//递归添加子菜单
var getChildrenMenu1 = function(menu) {
	// style=\"height:  500px;overflow:  auto;\"
	var html = "<ul class=\"submenu\">";//添加子菜单的ul
	var children = menu.children;//获取子菜单
	for(var i = 0; i < children.length; i ++) {
		html += "<li class=\"\">";//子菜单li
		html += getChildrenMenu(children[i]);//添加子菜单
		html += "</li>";
	}
	html += "</ul>";
	return html;
}
//退出
var doLoginOut = function() {
	topshowdialog("确认退出？", function(){
		$.ajax({
			url : $.cxt + "/loginout", 
			type: "POST",
			success : function(data) {
				if(data == "1") {
					location.href = $.cxt + "/pages/jsp/login/login.jsp";
				}
			}
		});
	})
}
var doContentLoader = function($this) {
	//console.log("-++++++------"+String.valueOf($this));
	$("#contentLoader").attr("src",$this.attr("data-src"))//给iframe赋值src跳转
	var $active = $("#sidebar li.active");//查找有.active的菜单
	$active.removeClass("active");//移除active样式
	$this.parent().addClass("active");//给最新点击的li添加active样式
	//添加菜单导航条
	var $lis = $this.parents("li");//获取所有上级li元素
	var breadcrumbsHtml = "";//导航条的html
	for(var i = $lis.length - 1; i >= 0 ; i --) {//倒叙循环，这样就能让顶级菜单在第一个
		var $li = $($lis[i]);
		var text = $li.find(".menu-text").html();//获取菜单名称
		breadcrumbsHtml += "<li>" + text + "</li>";//拼装导航条html
	}
	$("#breadcrumbs ul").html(breadcrumbsHtml);//添加导航条的html
	$("#breadcrumbs ul li:first-child").append($("<i class='fa fa-home'></i>").css({"float":"left","margin":"1px 3px 0px","font-size":"16px"}));
}