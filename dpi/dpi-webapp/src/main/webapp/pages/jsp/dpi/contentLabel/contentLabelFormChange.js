$(function() {
	var contentLabelLevel=$('#le').val();
	$('#contentLabelLevel').val(contentLabelLevel);
	$('#contentLabelLevel').attr('disabled',"disabled");
	$('#contentLabelLevel').css('background-color',"#f5f5f5");
	if(contentLabelLevel){
		$('#contentLabelName1').removeAttr('placeholder');
		$('#contentLabelName2').removeAttr('placeholder');
		$('#contentLabelName3').removeAttr('placeholder');
		$('#contentLabelName4').removeAttr('placeholder');
		$('#contentLabelName5').removeAttr('placeholder');
		$('#contentLabelName6').removeAttr('placeholder');
	}
	if(contentLabelLevel==1){
		$('#contentLabelName1').attr('class',"formField required");
		$('#contentLabelName1').removeAttr("readonly");
		$('#con').val($('#contentLabelName1').val());
	}
	if(contentLabelLevel==2){
		$('#contentLabelName1').attr('class',"formField required");
		$('#contentLabelName2').attr('class',"formField required");
		$('#contentLabelName2').removeAttr("readonly");
		$('#con').val($('#contentLabelName2').val());
	}
	if(contentLabelLevel==3){
		$('#contentLabelName1').attr('class',"formField required");
		$('#contentLabelName2').attr('class',"formField required");
		$('#contentLabelName3').attr('class',"formField required");
		$('#contentLabelName3').removeAttr("readonly");
		$('#con').val($('#contentLabelName3').val());
	}
	if(contentLabelLevel==4){
		$('#contentLabelName1').attr('class',"formField required");
		$('#contentLabelName2').attr('class',"formField required");
		$('#contentLabelName3').attr('class',"formField required");
		$('#contentLabelName4').attr('class',"formField required");
		$('#contentLabelName4').removeAttr("readonly");
		$('#con').val($('#contentLabelName4').val());
	}
	if(contentLabelLevel==5){
		$('#contentLabelName1').attr('class',"formField required");
		$('#contentLabelName2').attr('class',"formField required");
		$('#contentLabelName3').attr('class',"formField required");
		$('#contentLabelName4').attr('class',"formField required");
		$('#contentLabelName5').attr('class',"formField required");
		$('#contentLabelName5').removeAttr("readonly");
		$('#con').val($('#contentLabelName5').val());
	}
	if(contentLabelLevel==6){
		$('#contentLabelName1').attr('class',"formField required");
		$('#contentLabelName2').attr('class',"formField required");
		$('#contentLabelName3').attr('class',"formField required");
		$('#contentLabelName4').attr('class',"formField required");
		$('#contentLabelName5').attr('class',"formField required");
		$('#contentLabelName6').attr('class',"formField required");
		$('#contentLabelName6').removeAttr("readonly");
		$('#con').val($('#contentLabelName6').val());
	}
})