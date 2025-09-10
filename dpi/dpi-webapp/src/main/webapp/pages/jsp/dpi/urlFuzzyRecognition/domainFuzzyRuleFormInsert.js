

function  checkDomainRule(){
	var domainCode=$.trim($("#parseRule").val());
	
	if(domainCode){
		$.ajax({
			url : $.cxt + "/urlFuzzyRecognition/checkDomainRule",
			dataType : "json",
		 	type : "POST",
		 	data : {domainCode:domainCode},
		 	success : function(obj){
		 		var data=JSON.parse(obj);
		 		var list=data.data;
		 		if(list.length>0){
		 			$("#prodid").val(list[0].PROD_ID);
		 			$("#prodName").val(list[0].PROD_NAME);
		 			$("#labelCode").val(list[0].PROD_LABEL_CODE);
		 			$("#labelName2").val(list[0].PROD_LABEL_CODE);
		 		}else{
		 			$("#prodid").val('');
		 			$("#prodName").val('');
		 			$("#labelCode").val('');
		 			$("#labelName2").val('');
		 			messageAlert("没有匹配上的产品");
					return false;
		 		}
		 		
		 	}
		 });
	}
}
//弹出产品id的列表
function getProdIdList1(prodId,prodName,type){
   
	var prodId = document.getElementById("prodid").value;
	//var prodId = $("#prodid").val();
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
		url : $.cxt + "/groupClass/urlFuzzyRecognition",
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
var sntest3=function(){
	topshowLabelTree.show(function(obj){
	   
		var lvl=obj.level;
		var label_code= obj.labelCode;
		var label_name= obj.labelName;
		$("#labelCode").val(label_code);
		$("#labelName2").val(label_name);
		
	})
}
var sntest4=function(){
	topshowLabelTree.show(function(obj){
	   
		var lvl=obj.level;
		var label_code= obj.labelCode;
		var label_name= obj.labelName;
		$("#prodCatagoryCode").val(label_code);
		$("#prodCatagoryName").val(label_name);
		
	})
}