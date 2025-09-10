var labelCode1='';
var labelName1='';
var labelCode2='';
var labelName2='';
$(function() {
	
	
});
//选择的标签名称
var sntest=function(){
	topshowLabelTree.show(function(obj){
		
		var lvl=obj.level;
		var labelName=obj.labelName;
		var labelCode=obj.labelCode;
		$("#labellvlHidden").val(lvl);
		if(lvl=='1'){
			
			$("#labelName1").val(labelName);
			$("#labelId1").val(labelCode);
		}else{
			$("#labelId1").val(labelCode);
			$("#labelName1").val(labelName);
		}
		
	})
}




