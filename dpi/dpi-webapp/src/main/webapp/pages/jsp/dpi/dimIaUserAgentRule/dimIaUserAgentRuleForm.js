//弹出产品id的列表
function getProdidList(prodIdParam,prodName,type){
	var prodIdParam = $("#showRuleForm #prodIdParam").val();
	var prodName=$("#prodName").val();
	if(type=="prodId"){
		prodName="";
	}
	if(type=="prodName"){
		prodIdParam="";
	}
	topwindow.showWindow({
		//窗口名称
		title : "产品名称列表",
		//参数
		data : {
			prodId : prodIdParam,
			prodName : prodName
		},
		//url
		url : $.cxt + "/uakey/getIdNameList",//getIdNameList  urlFuzzyRecognition
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