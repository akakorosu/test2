var topshowLabelTree = {
	show : function(fun) {
		topwindow.showWindow({
			title : "标签信息",
			url : $.cxt + "/productLabel/showLabelTree",
			width : $(".page-content").width() - 50,
			height : $(".page-content").height() - 50,
			closeFun : function(obj) {
				fun(obj);
			}
		});
	}
}