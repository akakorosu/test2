var searchObj={};
$(function() {
	//根据产品名称查询产品ID
	$("#searchPiList1").click(function(){
		if($.isEmptyObject(searchObj)){
			messageAlert("未查询到产品");
			return;
		}
		if(searchObj!=null &&searchObj!=undefined &&searchObj!=''){
			
			$("#prodId").val(searchObj.labelCode);
			$("#prodName").val(searchObj.labelName);
		}
		
		//getProdIdList();
	});
	//initProdId();
	$(".chosen-select").change(function(){
		
		var prodid=$(".chosen-select").find("option:selected").val();
		if(prodid){
			var text=$(".chosen-select").find("option:selected").text();
			$("#prodId").val(prodid);
			$("#prodName").val(text);
		}
		
		
	});
	
});

function initProdId(){
	var options='';
	//alert($("#prodId").val());
	var id=$("#prodId").val();
	$.ajax({
		url : $.cxt + "/unidentifiedUrl/getAllProdId", 
		data : {},
		type: "POST",
		success : function(data) {
			var data=JSON.parse(data);
			list=data.data;
			
			$("#selectName").empty();
			options+="<option value=''>--请选择产品--</option>";
			var top=list.length>=50 ? top=50: top=list.length;
			for(var i=0;i<top;i++){
				if(id==list[i].PROD_ID){
					options+="<option selected='selected' value='"+list[i].PROD_ID+"'>"+list[i].PROD_NAME+"</option>";
				}else{
					options+="<option value='"+list[i].PROD_ID+"'>"+list[i].PROD_NAME+"</option>";
				}
				
			}
			//options='<option>产品ID</option>'+options;
			$("#selectName").append(options);
			

		
		}
	});
}
function getProdIdList(){
	var prodName=$("#name").val();
	prodName=$.trim(prodName);
	if(prodName){
	$.ajax({
		url : $.cxt + "/unidentifiedUrl/getProId", 
		data : {prodName:prodName},
		type: "POST",
		success : function(data) {
			var data=JSON.parse(data);
			list=data.data;
			var options="";
			
			$("#selectName").empty();
			options+="<option value=''>--请选择产品--</option>";
			for(var i=0;i<list.length;i++){                		                			                	
				options+="<option value='"+list[i].PROD_ID+"'>"+list[i].PROD_NAME+"</option>";
			}
			//options='<option>产品ID</option>'+options;
			$("#selectName").append(options);

		
		}
	});
	}
}
$("#likeSearch").keydown(function(){//查询输入框键盘查询事件
	setTimeout(function(){
		
		inputFastSearchFun();
		
	},10)
});
var inputFastSearchFun = function() {
	
	var v = $("#likeSearch").val();
	
	if(v == "") {//输入值为空则将下拉框清空，并隐藏
		$("#divFastSearch").html("");
		$("#divFastSearch").hide();
		return false;
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
				html += getSetLabelCloseWindowHtml(o, "1");
				
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
var getSetLabelCloseWindowHtml = function(obj, level) {
	var labelCode = obj.prodId;
	var labelName = obj.prodName;
	
	var o = {
		labelCode : labelCode,
		labelName : labelName,
		level : level,
	}
	var os = JSON.stringify(o);
	var html = "<a onclick='setLabelCloseWindow(" + os + ")' href='#' labelCode='" + labelCode + "'>" + labelName + "</a>"
	return html;
}

var setLabelCloseWindow = function(cs) {
	searchObj=cs;
	$("#likeSearch").val(cs.labelName);
	$("#divFastSearch").hide();
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



 



