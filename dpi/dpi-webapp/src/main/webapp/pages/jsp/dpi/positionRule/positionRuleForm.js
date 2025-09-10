$(function (){
	var id = $("#id").val();
	//如果是修改,初始化是否可用
	if(id!=""){
		var isUsable = $("#isUsable").val();
		if(isUsable==0){
			$("#isUsableName").val("不可用");
			$("#isUsableName").css("color","red");
		}else if(isUsable==1){
			$("#isUsableName").val("可用");
			$("#isUsableName").css("color","green");
		}
	}else{
		$("#isUsable").val(0);
		$("#isUsableName").val("");
	}
})

/**
 * 规则校验
 * @param value
 */
function checkRule(type){
	var rule = "";
	var titleName = "";
	var longitudeRule = $("#longitudeRule").val();
	var latitudeRule = $("#latitudeRule").val();
	var systemRule = $("#systemRule").val();
	if(type==1){
		rule = longitudeRule;
		titleName = "经度规则校验";
	}else if(type==2){
		rule = latitudeRule;
		titleName = "纬度规则校验";
	}else if(type==3){
		rule = systemRule;
		titleName = "系统规则校验";
	}
	
	topwindow.showWindow({
		//窗口名称
		title : titleName,
		//参数
		data : {
			rule : rule,
			type : type
		},
		//url
		url : $.cxt + "/positionRule/checkRuleForm",
		bottons : [{
			title : "返回",
			fun : function() {
				//重新加载表格
				reloadJqGrid("search");
				//关闭窗口
				topwindow.removeWindow();
			}
		}]
	});
}