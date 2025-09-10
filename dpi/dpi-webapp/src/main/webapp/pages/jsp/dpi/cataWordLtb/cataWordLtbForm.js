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