$(function() {
	$('#label_1').hide();
	$('#label_2').hide();
	$('#label_3').hide();
	$('#label_4').hide();
	$('#label_5').hide();
	$('#contentLabelName1_1').attr('class',"formField required");
	$('#contentLabelName2_1').attr('class',"formField required");
	$('#contentLabelName3_1').attr('class',"formField required");
	$('#contentLabelName4_1').attr('class',"formField required");
	$('#contentLabelName5_1').attr('class',"formField required");
	$('#contentLabelName6').attr('class',"formField required");
})

var contentLabelLevel='';
$('#contentLabelLevel').change(function(){
	$('.formField-error').remove();
	$('input').css("border-color","#ccc");
	$('select').css("border-color","#ccc");
	contentLabelLevel=$('#contentLabelLevel').val();
	$('#contentLabelName1').empty();
	$('#contentLabelName2').empty();
	$('#contentLabelName3').empty();
	$('#contentLabelName4').empty();
	$('#contentLabelName5').empty();
	$('#contentLabelName6').val('');
	
	$('#contentLabelName1_1').val('');
	$('#contentLabelName2_1').val('');
	$('#contentLabelName3_1').val('');
	$('#contentLabelName4_1').val('');
	$('#contentLabelName5_1').val('');
	
	$('#contentLabelName1_1').attr('readonly',"readonly");
	$('#contentLabelName2_1').attr('readonly',"readonly");
	$('#contentLabelName3_1').attr('readonly',"readonly");
	$('#contentLabelName4_1').attr('readonly',"readonly");
	$('#contentLabelName5_1').attr('readonly',"readonly");
	$('#contentLabelName6').attr('readonly',"readonly");
	
	$('#contentLabelName1_1').removeAttr("class");
	$('#contentLabelName2_1').removeAttr("class");
	$('#contentLabelName3_1').removeAttr("class");
	$('#contentLabelName4_1').removeAttr("class");
	$('#contentLabelName5_1').removeAttr("class");
	$('#contentLabelName6').removeAttr("class");
	
	$('#label_1').hide();
	$('#label_2').hide();
	$('#label_3').hide();
	$('#label_4').hide();
	$('#label_5').hide();
	$('#label_1_1').show();
	$('#label_2_1').show();
	$('#label_3_1').show();
	$('#label_4_1').show();
	$('#label_5_1').show();
	
	if(!contentLabelLevel){
		return;
	}

	if(contentLabelLevel==1){
		$('#contentLabelName1_1').attr('class',"formField required");
		$('#contentLabelName1_1').removeAttr("readonly");
		$('#contentLabelName1_1').attr('class',"formField required");
	}
	if(contentLabelLevel==2){
		$('#label_1').show();
		$('#label_1_1').hide();		
		$('#contentLabelName1').attr('class',"formField required");
		$('#contentLabelName2_1').attr('class',"formField required");
		$('#contentLabelName2_1').removeAttr("readonly");
		$('#contentLabelName2').removeAttr("name");
		$('#contentLabelName2').val('');
		$('#contentLabelName1_1').attr('class',"formField required");
		$('#contentLabelName2_1').attr('class',"formField required");
		getLabel(1,'','','','','');
		
	}
	if(contentLabelLevel==3){
		$('#label_1').show();
		$('#label_1_1').hide();			
		$('#contentLabelName1').attr('class',"formField required");
		$('#contentLabelName3_1').attr('class',"formField required");
		$('#contentLabelName3_1').removeAttr("readonly");
		$('#contentLabelName2').val('');
		$('#contentLabelName3').removeAttr("name");
		$('#contentLabelName1_1').attr('class',"formField required");
		$('#contentLabelName2_1').attr('class',"formField required");
		$('#contentLabelName3_1').attr('class',"formField required");
		getLabel(1,'','','','','');	
	}
	if(contentLabelLevel==4){
		$('#label_1').show();
		$('#label_1_1').hide();			
		$('#contentLabelName1').attr('class',"formField required");
		$('#contentLabelName4_1').attr('class',"formField required");
		$('#contentLabelName4_1').removeAttr("readonly");
		$('#contentLabelName2').val('');
		$('#contentLabelName4').removeAttr("name");
		$('#contentLabelName1_1').attr('class',"formField required");
		$('#contentLabelName2_1').attr('class',"formField required");
		$('#contentLabelName3_1').attr('class',"formField required");
		$('#contentLabelName4_1').attr('class',"formField required");
		getLabel(1,'','','','','');	
	}
	if(contentLabelLevel==5){
		$('#label_1').show();
		$('#label_1_1').hide();			
		$('#contentLabelName1').attr('class',"formField required");
		$('#contentLabelName5_1').attr('class',"formField required");
		$('#contentLabelName5_1').removeAttr("readonly");
		$('#contentLabelName2').val('');
		$('#contentLabelName5').removeAttr("name");
		$('#contentLabelName1_1').attr('class',"formField required");
		$('#contentLabelName2_1').attr('class',"formField required");
		$('#contentLabelName3_1').attr('class',"formField required");
		$('#contentLabelName4_1').attr('class',"formField required");
		$('#contentLabelName5_1').attr('class',"formField required");
		getLabel(1,'','','','','');	
	}
	if(contentLabelLevel==6){
		$('#label_1').show();
		$('#label_1_1').hide();	
		$('#contentLabelName1').attr('class',"formField required");
		$('#contentLabelName6').attr('class',"formField required");
		$('#contentLabelName6').removeAttr("readonly");
		$('#contentLabelName2').val('');
		$('#contentLabelName1_1').attr('class',"formField required");
		$('#contentLabelName2_1').attr('class',"formField required");
		$('#contentLabelName3_1').attr('class',"formField required");
		$('#contentLabelName4_1').attr('class',"formField required");
		$('#contentLabelName5_1').attr('class',"formField required");
		$('#contentLabelName6').attr('class',"formField required");
		getLabel(1,'','','','','');	
	}
	
});
function getLabel(level,labelName1,labelName2,labelName3,labelName4,labelName5){
	$.ajax({
		"url" : $.cxt + "/content/selectLabelList", 
		'data':{contentLabelLevel:level,contentLabelName1:labelName1,contentLabelName2:labelName2,contentLabelName3:labelName3,contentLabelName4:labelName4,contentLabelName5:labelName5},
		"type":"POST",
		"dataType":"json",
		"success":function(data){
			var data=JSON.parse(data);
			var html='<option></option>';
			data=data.data;
//			if(JSON.stringify(data[0])=='{}'){
//				return;
//			}			
			if(level==1){
				if(data.length){
					if(data.length==1&&JSON.stringify(data[0]) == "{}"){
						$('#label_1_1').show();
						$('#label_1').hide();
					}else{
						for(var i in data){
							if(data[i].contentLabelName1){
								html+='<option key='+data[i].contentLabelCode1+'  value='+data[i].contentLabelName1+'>'+data[i].contentLabelName1+'</option>';
							}
						}
						$('#contentLabelName1').empty();
						$('#contentLabelName1').append(html);
					}
				}else{
					$('#label_1_1').show();
					$('#label_1').hide();
				}
			}
			if(level==2){
				if(data.length){
					if(data.length==1&&JSON.stringify(data[0]) == "{}"){
						$('#label_2_1').show();
						$('#label_2').hide();
					}else{
						for(var i in data){
							if(data[i].contentLabelName2){
								html+='<option key='+data[i].contentLabelCode2+' value='+data[i].contentLabelName2+'>'+data[i].contentLabelName2+'</option>';
							}
						}
						$('#contentLabelName2').empty();
						$('#contentLabelName2').append(html);
					}
				}else{
					$('#label_2_1').show();
					$('#label_2').hide();
				}
			}
			if(level==3){
				if(data.length){
					if(data.length==1&&JSON.stringify(data[0]) == "{}"){
						$('#label_3_1').show();
						$('#label_3').hide();
					}else{
						for(var i in data){
							if(data[i].contentLabelName3){
								html+='<option key='+data[i].contentLabelCode3+' value='+data[i].contentLabelName3+'>'+data[i].contentLabelName3+'</option>';
							}
						}
						$('#contentLabelName3').empty();
						$('#contentLabelName3').append(html);
					}
					
				}else{
					$('#label_3_1').show();
					$('#label_3').hide();
				}
			}
			if(level==4){
				if(data.length){
					if(data.length==1&&JSON.stringify(data[0]) == "{}"){
						$('#label_4_1').show();
						$('#label_4').hide();
					}else{
						for(var i in data){
							if(data[i].contentLabelName4){
								html+='<option key='+data[i].contentLabelCode4+' value='+data[i].contentLabelName4+'>'+data[i].contentLabelName4+'</option>';
							}
						}
						$('#contentLabelName4').empty();
						$('#contentLabelName4').append(html);
					}
				}else{
					$('#label_4_1').show();
					$('#label_4').hide();
				}
			}
			if(level==5){
				if(data.length){
					if(data.length==1&&JSON.stringify(data[0]) == "{}"){
						$('#label_5_1').show();
						$('#label_5').hide();
					}else{
						for(var i in data){
							if(data[i].contentLabelName5){
								html+='<option key='+data[i].contentLabelCode5+' value='+data[i].contentLabelName5+'>'+data[i].contentLabelName5+'</option>';
							}
						}
						$('#contentLabelName5').empty();
						$('#contentLabelName5').append(html);
					}
				}else{
					$('#label_5_1').show();
					$('#label_5').hide();
				}
			}
		}
	}); 
}
$('#contentLabelName1').change(function(){
	$('#contentLabelCode1').val($("#contentLabelName1 option:checked").attr("key"));
	$('.formField-error').remove();
	$('input').css("border-color","#ccc");
	$('select').css("border-color","#ccc");
	$('#label_2').hide();
	$('#label_3').hide();
	$('#label_4').hide();
	$('#label_5').hide();
	$('#label_2_1').show();
	$('#label_3_1').show();
	$('#label_4_1').show();
	$('#label_5_1').show();
	if(contentLabelLevel>2){
		$('#label_2').show();
		$('#contentLabelName2').attr('class',"formField required");
		$('#label_2_1').hide();
		getLabel(2,$('#contentLabelName1').find("option:selected").text(),'','','','');
	}
});
$('#contentLabelName2').change(function(){
	$('#contentLabelCode2').val($("#contentLabelName2 option:checked").attr("key"));
	$('.formField-error').remove();
	$('input').css("border-color","#ccc");
	$('select').css("border-color","#ccc");
	$('#label_3').hide();
	$('#label_4').hide();
	$('#label_5').hide();
	$('#label_3_1').show();
	$('#label_4_1').show();
	$('#label_5_1').show();
	if(contentLabelLevel>3){
		$('#label_3').show();
		$('#contentLabelName3').attr('class',"formField required");
		$('#label_3_1').hide();
		getLabel(3,$('#contentLabelName1').find("option:selected").text(),$('#contentLabelName2').find("option:selected").text(),'','','');
	}
});
$('#contentLabelName3').change(function(){
	$('#contentLabelCode3').val($("#contentLabelName3 option:checked").attr("key"));
	$('.formField-error').remove();
	$('input').css("border-color","#ccc");
	$('select').css("border-color","#ccc");
	$('#label_4').hide();
	$('#label_5').hide();
	$('#label_4_1').show();
	$('#label_5_1').show();
	if(contentLabelLevel>4){
		$('#label_4').show();
		$('#contentLabelName4').attr('class',"formField required");
		$('#label_4_1').hide();
		getLabel(4,$('#contentLabelName1').find("option:selected").text(),$('#contentLabelName2').find("option:selected").text(),$('#contentLabelName3').find("option:selected").text(),'','');
	}
});
$('#contentLabelName4').change(function(){
	$('#contentLabelCode4').val($("#contentLabelName4 option:checked").attr("key"));
	$('.formField-error').remove();
	$('input').css("border-color","#ccc");
	$('select').css("border-color","#ccc");
	$('#label_5').hide();
	$('#label_5_1').show();
	if(contentLabelLevel>5){
		$('#label_5').show();
		$('#contentLabelName5').attr('class',"formField required");
		$('#label_5_1').hide();
		getLabel(5,$('#contentLabelName1').find("option:selected").text(),$('#contentLabelName2').find("option:selected").text(),$('#contentLabelName3').find("option:selected").text(),$('#contentLabelName4').find("option:selected").text(),'');
	}else{
		
	}
});
$('#contentLabelName5').change(function(){
	$('#contentLabelCode5').val($("#contentLabelName5 option:checked").attr("key"));
});











//弹出产品id的列表
function getContentLabelList(){
	var contentLabelName = $("#contentLabelName1").val();
	topwindow.showWindow({
		//窗口名称
		title : "标签列表",
		//参数
		data : {
			contentLabelName : contentLabelName
		},
		//url
		url : $.cxt + "/content/selectByLabelCode",
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