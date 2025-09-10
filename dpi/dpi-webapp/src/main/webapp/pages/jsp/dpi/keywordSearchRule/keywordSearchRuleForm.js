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
	$("select[name='matchType']").change(function(){
		changeIpCheck();
	});
})

/**
 * 规则校验
 * @param value
 */
function checkRule(type){
	var rule = "";
	var titleName = "";
	var parseRuleAndriod = $("#parseRuleAndriod").val();
	var parseRuleIos = $("#parseRuleIos").val();
	if(type==1){
		rule = parseRuleAndriod;
		titleName = "Andriod规则校验";
	}else if(type==2){
		rule = parseRuleIos;
		titleName = "ios规则校验";
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
		url : $.cxt + "/keywordSearchRule/checkRuleForm",
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

function changeIpCheck(){
	var match_type = $("select[name='matchType']").val();
	console.log(match_type);
	if(match_type=="ip"){
		$("input[name='hostParam']").removeClass("required");
		$("input[name='hostParam']").next().hide();
	}else {
		$("input[name='hostParam']").addClass("required");
	}
}