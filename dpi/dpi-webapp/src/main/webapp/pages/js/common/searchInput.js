var topsearchInput = {
	init : function(obj) {
		// 输入框id
		this.inputId = obj.inputId;
		// 拼装divid
		this.divId = obj.inputId + "Div";
		// 后台url
		this.url = obj.url;
		// 显示哪个字段
		this.showName = obj.showName;
		// 要查询哪个字段
		this.likeName = obj.likeName;
		// a标签的点击事件，参数为每一行数据
		this.aClick = obj.aClick;
		// 查询显示的div
		var divHtml = "<div id=\"" + this.divId + "\" style=\"display: none;position: absolute;border:1px solid #ccc;background-color: white;max-width: 400px;max-height: 400px;overflow: auto;padding: 10px;z-index: 10\"></div>";
		// 追加到输入框之后
		$("#" + this.inputId).after(divHtml);
		// 输入框的键盘事件
		$("#" + this.inputId).keydown(function(){
			setTimeout(function(){
				topsearchInput.getSelectList();
			},1000)
		});
		// 输入框的点击事件
		$("#" + this.inputId).click(function(){
			topsearchInput.getSelectList();
		});
		// 点击页面其他地方，清空显示div
		$(document).click(function(e){
			// 如果点击的不是div，也不是input则将div清空并隐藏
			if($(e.target).attr("id") != topsearchInput.divId && $(e.target).attr("id") != topsearchInput.inputId) {
				$("#"  + topsearchInput.divId).hide();
				$("#"  + topsearchInput.divId).html("");
			}
		});
	},
	getSelectList : function() {
		// div
		var $div = $("#" + this.divId);
		// 每次查询清空一下div
		$div.html("");
		// 获取input值
		var v = $("#" + this.inputId).val();
		// 值为空则隐藏div
		if(v == "") {
			$div.hide();
			return;
		}
		// 查询数据对象
		var data = {};
		data[this.likeName] = v;
		var html = "";
		$.ajax({//模糊查询标签
			url : this.url,
			dataType : "json",
			data : data,
			type : "post",
			loading : 0,//去掉加载遮盖层
			async:false,
			success : function(data) {
				// 查询到data
				if(data.length != 0) {
					for(var i = 0; i < data.length; i ++) {
						// 拼装div下的div的id
						var id = topsearchInput.divId + "Div" + i;
						var o = data[i];
						// 往div中追加元素
						$div.append("<div id='" + id + "'><a href='#'>" + o[topsearchInput.showName] + "</a></div>");
						// div下的a标签的点击事件，参数o为每一行对象
						$("#" + id + " a").click(o, function(e){
							// 执行点击事件，将每一行的对象作为参数传入
							topsearchInput.aClick(e.data)
						});
					}
				// 没有查询到data
				} else {
					$div.append("没有查询到相关信息！");
				}
			}
		});
		$div.show();
	}
}