/**
 * excle中的错误数据展示
 * @param data
 */
function falseDataViewFromExcle(data){
	
	var actionUrl = data.actionUrl;
	var falseType = data.falseType;
	var maxDataNumForExcle = data.maxDataNumForExcle;
	if(falseType=="1"){
		topwindow.showWindow({
			//窗口名称
			title : "文件中的问题数据展示",
			data:{
			},
			//url
			url : $.cxt + actionUrl,
			bottons : [{
				title : "返回",
				fun : function() {
					//重新加载表格
					reloadJqGrid();
					//关闭窗口
					topwindow.removeWindow();
				}
			}]
		});
	}else if(falseType=="3"){
		//重新加载表格
		reloadJqGrid();
		topshowAlertErrorDiv_dpi("文件导入失败，数据条数超过"+maxDataNumForExcle+"条");
	}
	
	else{
		//重新加载表格
		reloadJqGrid();
		topshowAlertSuccessDiv_dpi("文件导入成功");
	}
}

/**
 * 问题数据插入库
 * @param type
 * @param actionUrl
 */
function falseDataFromExcleUpdate(type,actionUrl){
	
	
	$.ajax({
		url : $.cxt + actionUrl,
		data:{
			type:type
		},
		type:'get',
		dataType:'text',
		success : function(data) {
			if(data=="1"){
				topshowAlertSuccessDiv_dpi("文件导入成功");
			}else{
				topshowAlertErrorDiv_dpi("文件导入失败");
			}
		}
	});
	setTimeout("after2Second()","500");//500毫秒后执行
//	reloadJqGrid();
	//关闭窗口
	topwindow.removeWindow();
}
//2秒后执行
function after2Second(){
	reloadJqGrid();//刷新页面
}

$(function(){
	$('input').attr('autocomplete','off');
})