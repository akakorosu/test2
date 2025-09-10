$(function() {
	
	$('input[name=boceDate]').datepicker({
		'applyClass' : 'btn-sm btn-primary',
		'cancelClass' : 'btn-sm btn-default',
		locale: {
			applyLabel: '确定',
			cancelLabel: '取消',
		}
	})
	.prev().on(ace.click_event, function(){
		$(this).next().focus();
	});
	
});

//弹出产品id的列表
function getProdIdList(prodId,prodName,type){
	var prodId = $("#prodid").val();
	var prodName = $("#prodNameNew").val();
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
		url : $.cxt + "/productInfo/getProdIdList",
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

//产品id校验
function checkProdId(){
	var prodId = $("#prodid").val();
	var prodName = "";
	if(prodId!=""){
		$.ajax({
			url : $.cxt + "/productInfo/prodIdCheck",
			data:{
				prodId : prodId,
				prodName : prodName
			},
			type:'get',
			dataType:'text',
			success : function(data) {
				if(data=="true"){
					topshowAlertErrorDiv_dpi("产品ID已存在");
				}else{
					topshowAlertSuccessDiv_dpi("产品ID校验通过");
				}
			}
		});
	}else{
		topshowAlertErrorDiv_dpi("请输入产品ID");
	}
	

}
var getLableCode_byForm=function(){
	topshowLabelTree.show(function(obj){
	    label_name=obj.labelName;
		var lvl=obj.level;
		var label_code= obj.labelCode;
		
		$("#labelCodeHidden_form").val(label_code);//弹出form表单中用
		
		if(lvl=='1'){
			$("#labelNameOverView_form").val(label_name);//弹出form表单中用
			$("#labelCodeHidden_form").val(label_code);
			
			}else{
			$("#labelNameOverView_form").val(label_name);//弹出form表单中用
			$("#labelCodeHidden_form").val(label_code);
			}
	})
}
var getLableCodeCatagory=function(){
	topshowLabelTree.show(function(obj){
	    label_name=obj.labelName;
		var lvl=obj.level;
		var label_code= obj.labelCode;
		
		$("#prodCatagory").val(label_code);//弹出form表单中用
		
		if(lvl=='1'){
			$("#prodCatagory").val(label_name);//弹出form表单中用
			$("#CodeHiddenCatagory").val(label_code);
			
			}else{
			$("#prodCatagory").val(label_name);//弹出form表单中用
			$("#CodeHiddenCatagory").val(label_code);
			}
	})
}
