$(function() {
	$('#label_name').hide();
	$('#label_name1').show();
	$('#label_name').removeAttr("name");
	$('#joinLabelName').val($('#labelName1').val())
	if($('#labelName2').val()){
		$('#labelName1').attr('readonly',"readonly");
		$('#labelName2').removeAttr("readonly");
	}else{
		$('#labelName2').attr('readonly',"readonly");
	}
})
