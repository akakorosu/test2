//弹出产品id的列表
function getProdIdList11(prodId,prodName,type){
	
	//var prodId = document.getElementById("prodId").value
	//console.log(topgetObjByObj($("#gridSearch .searchField")));
	var prodId = $("#prodId").val();
	var prodName = $("#prodName").val();
	if(type=="prodId"){
		prodName="";
	}
	if(type=="prodName"){
		prodId="";
	}
	topwindow.showWindow({
		//窗口名称
		title : "产品名称列表",
		//参数
		data : {
			prodId : prodId,
			prodName : prodName
		},
		//url
		url : $.cxt + "/groupClass/getProdIdList",
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