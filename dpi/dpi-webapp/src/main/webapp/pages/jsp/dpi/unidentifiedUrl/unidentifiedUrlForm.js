var searchObj={};
$(function() {
	
	$("#searchPiList1").click(function(){
		if($.isEmptyObject(searchObj)){
			messageAlert("未查询到产品");
			return;
		}
		if(searchObj!=null &&searchObj!=undefined){
			
			$("#prodId").val(searchObj.labelCode);
			$("#prodName").val(searchObj.labelName);
			getProdName();
		}
		
	});
	
	//下拉选change事件
	$(".chosen-select").change(function(){
		 //var text=$(".chosen-select").find("option:selected").text();
		 //$("#prodId").val(text);
		var id=$(".chosen-select").find("option:selected").val();
		var text=$(".chosen-select").find("option:selected").text();
		$("#prodId").val(id);
		$("#prodName").val(text);
		 getProdName();
	});
});
//选择的标签名称  (后台不用动)
var sntest12=function(){
	topshowLabelTree.show(function(obj){
		
		label_name=obj.labelName;
		var lvl=obj.level;
		var labelCode=obj.labelCode;
		
        if(lvl=='1'){
			
			$("#labelName").val(label_name);
			$("#prodLabel").val(labelCode);
		}else{
			$("#prodLabel").val(labelCode);
			$("#labelName").val(label_name);
		}
		/*$("#labelName").val(labelName2);
		$("#prodLabel").val(labelCode2);*/
	})
}
var sntest13=function(){
	topshowLabelTree.show(function(obj){
		
		label_name=obj.labelName;
		var lvl=obj.level;
		var labelCode=obj.labelCode;
		
      
		$("#prodCatagoryName").val(label_name);
		$("#prodCatagory").val(labelCode);
		
		/*$("#labelName").val(labelName2);
		$("#prodLabel").val(labelCode2);*/
	})
}


function messageAlert(message){
	bootbox.dialog({
        message: "<span style=\"color:#000\">"+message+"</span>",
        title: "消息提示",
        buttons: {
            OK: {
                label: "确定",
                className: "btn-success",
            }
        }
    });
}

function getProdName(){
	var prodId=$('#prodId').val();
	if(prodId){
		$.ajax({
			url : $.cxt + "/unidentifiedUrl/getInfoByProdId", 
			data : {prodId:prodId},
			type: "POST",
			success : function(data) {
				
				var data=JSON.parse(data);
			
				data=data.data;
				
				if(data.prodLabelCode){
					$("#prodLabel").val(data.prodLabelCode);
				}else{
					$("#prodLabel").val("");
				}
				if(data.prodLabelName){
					$("#labelName").val(data.prodLabelName);
				}else{
					$("#labelName").val("");
				}
				if(data.prodCatagoryName){
					$("#prodCatagoryName").val(data.prodCatagoryName);
				}else{
					$("#prodCatagoryName").val("");
				}
				if(data.prodCatagory){
					$("#prodCatagory").val(data.prodCatagory);
				}else{
					$("#prodCatagory").val("");
				}
			}
		});
	}
	
};
function getProdId(){
	var prodName=$('#prodName').val()
	$.ajax({
		url : $.cxt + "/unidentifiedUrl/getInfoByProdName", 
		data : {prodName:prodName},
		type: "POST",
		success : function(data) {
			var data=JSON.parse(data);
			data=data.data;
			if(data!=undefined&&data.prodId){
				$("#prodId").val(data.prodId);
			}
		}
	});
};
$("#likeSearch11").keydown(function(){//查询输入框键盘查询事件
	setTimeout(function(){
		inputFastSearchFun1();
		
	},500)
});
var inputFastSearchFun1 = function() {
	
	var v = $("#likeSearch11").val();
	
	if(v == "") {//输入值为空则将下拉框清空，并隐藏
		$("#divFastSearch1").html("");
		$("#divFastSearch1").hide();
		return;
	}
	var data = {prodName : v};
	$.ajax({//模糊查询标签
		url : $.cxt + "/unidentifiedUrl/comLikeSearch", 
		dataType : "json",
		data : data,
		type : "post",
		loading : 0,//去掉加载遮盖层
		success : function(data) {
		
			var obj=JSON.parse(data);
			var list=obj.data;
			var html = "";//拼接下拉框html
			var nu=list.length;
			for(var i = 0; i < list.length; i ++) {
				
				var o = list[i];
				html += "<div>";
				html += getSetLabelCloseWindowHtml1(o, "1");
				
				html += "</div>";
			}
			$("#divFastSearch1").html(html);
			if(html == "") {//html为空则隐藏下拉框，否则显示
				$("#divFastSearch1").hide();	
			} else {
				$("#divFastSearch1").show();	
			}
		}
	});
	
}
var getSetLabelCloseWindowHtml1 = function(obj, level) {
	var labelCode = obj.prodId;
	var labelName = obj.prodName;
	
	var o = {
		labelCode : labelCode,
		labelName : labelName,
		level : level,
	}
	var os = JSON.stringify(o);
	var html = "<a onclick='setLabelCloseWindow1(" + os + ")' href='#' labelCode='" + labelCode + "'>" + labelName + "</a>"
	return html;
}

var setLabelCloseWindow1 = function(cs) {
	searchObj=cs;
	$("#likeSearch11").val(cs.labelName);
	$("#divFastSearch1").hide();
} 



