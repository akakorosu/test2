$(function() {
	$('#lableType').val('1001');
	$('#label_name').change(function(){
		change1();
	});
	$('#labelName1').change(function(){
		if($('#labelName1').val().trim()){
			$('#labelName2').removeAttr("readonly");
		}else{
			$('#labelName2').val('')
			$('#labelName2').attr('readonly',"readonly");
		}
	});
//	$('#labelName2').change(function(){
//		change2();
//	});
	var label_name1=$('#labelName1').val();
	var label_name2=$('#labelName2').val();
	if(label_name1  && label_name2){
		$('#labelName1').removeAttr("onlyUrl");
		$('#labelName1').removeAttr("class");
		$('#labelName1').attr('class',"formField");
		
		$('#labelName2').removeAttr("onlyUrl");
		$('#labelName2').removeAttr("class");
		$('#labelName2').attr('onlyUrl',"/productLabel/check,id,labelName1,labelName2|联合校验重复!");
		$('#labelName2').attr('class',"formField required");
	}
});
function change1(){
	var labelName=$("#label_name").find("option:selected").text();
	if(labelName=='休闲娱乐'){
		$('#lableType').val('1001');
	}
	if (labelName == '生活类') {
		$('#lableType').val('1002');
	}
	if (labelName == '行业类') {
		$('#lableType').val('1003');
	}
	if (labelName == '工具类') {
		$('#lableType').val('1004');
	}
	if (labelName == '个性偏好') {
		$('#lableType').val('1005');
	}
	if (labelName == '其他') {
		$('#lableType').val('1009');
	}
};
function change2(){
	var labelName2=$('#labelName2').val();
	if(labelName2.trim()){
		$('#labelName2').removeAttr("class");
		$('#labelName2').removeAttr("onlyUrl");
		$('#labelName1').removeAttr("onlyUrl");
		$('#labelName1').removeAttr("class");
		$('#labelName1').attr('class',"formField");
		$('#labelName2').attr('class',"formField required");
		$('#labelName2').attr('onlyUrl',"/productLabel/check,id,labelName1,labelName2|联合校验重复!");
	}else{
		$('#labelName2').removeAttr("class");
		$('#labelName2').removeAttr("onlyUrl");
		$('#labelName2').attr('class',"formField");
	}
}

