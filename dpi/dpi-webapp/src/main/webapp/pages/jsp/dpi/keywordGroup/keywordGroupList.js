
$(function() {
	initDataGrid();
	
	//查询按钮
	$("#searchKgList").click(function(){
		reloadJqGrid("search");
	});
	//新建按钮
	$("#createKgBtn").click(function(){
		showKgForm();
	})
	$('input[name=opTime]').daterangepicker({
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


//初始化DataGrid
function initDataGrid(){
	$('#grid-table').jqGrid({
		url : $.cxt + "/keywordGroup/selectpagelist",
		datatype : "json",
		mtype : "POST",
		height : topjqGridHeight(),
		autowidth : true,
		colNames : [ '分组编码','分组名称','分组级别','作者','操作时间','操作' ],
		colModel : [ 
			{name : 'groupType',align : 'center',index : 'groupType',editable : true,hidden : false},
			{name : 'groupName',align : 'center',index : 'groupName',editable : true},
			{name : 'groupLevel',align : 'center',index : 'groupLevel',editable : true},
			{name : 'author',align : 'center',index : 'author',editable : true},
			{name : 'opTime',align : 'center',index : 'opTime',editable : true},
			{name : 'act',align : 'center', formatter : renderOperation}
        ],
		viewrecords : true,
		rownumbers: true,
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		pager : '#grid-pager',
		loadComplete : topjqGridLoadComplete,
		caption : "深度解析规则分组列表"
	});	
}

//格式化最后一列
function renderOperation (cellvalue, options, cell) {
	var html = ''; 
	html += '<a onclick="showKgForm(\''+cell.id+'\')" href="#" title="修改">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	html += '<a onclick="deleteKg(\''+cell.id+'\')" href="#" title="删除">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;';
//	html += '<a onclick="viewKg(\''+cell.id+'\')" href="#" title="查看">查看</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	return html;
}

//是否有效
function isValid(cellvalue, options, cell){
	if(cell.isValid ==1){
		return "有效";
	}
	return "无效";
}

//重新加载表格
function reloadJqGrid (flag) {
	if(flag == undefined) {
		//新增和修改的时候清空查询条件
		topclear('gridSearch')
	}
	var obj=topgetObjByObj($("#gridSearch .searchField"));
	var timeRange=$("#opTime").val();
	if(timeRange){
		var str=timeRange.split("-");
		var startTime=$.trim(str[0]);
		var endTime=$.trim(str[1]);
		obj.startTime=startTime;
		obj.endTime=endTime;
	}else{
		obj.startTime="";
		obj.endTime="";
	}
	var page = $('#grid-table').getGridParam('page');
	$("#grid-table").jqGrid('setGridParam',{
        postData : obj,
        page : page
    }).trigger("reloadGrid");
}


//弹出窗口(新增或修改)
function showKgForm(id) {
	topwindow.showWindow({
		//窗口名称
		title : "编辑",
		//参数
		data : {id : id},
		//url
		url : $.cxt + "/keywordGroup/showForm",
		bottons : [{
			title : "确认",
			fun : function() {
				//校验，参数为表单父级id
				if(topcheckoutForm("kgForm")){
					$.ajax({
						url : $.cxt + "/keywordGroup/insertOrUpdateVo", 
						data : topgetObjByObj($("#kgForm .formField")),
						type: "POST",
						success : function(data) {
							//重新加载表格
							reloadJqGrid("search");
							//关闭窗口
							topwindow.removeWindow();
							//操作提示
							topshowAlertSuccessDiv();
						}
					});
				}
			}
		}]
	});
}

//删除
function deleteKg(id) {
	topshowdialog("确认删除？", function(){
		$.ajax({
			url : $.cxt + "/keywordGroup/deleteVoById",
			data : {id : id},
			type: "POST",
			success : function(data) {
				//重新加载表格
				reloadJqGrid("search");
				//操作提示
				topshowAlertSuccessDiv();
			}
		})
	})
}

//查看
function viewKg(id) {
	topwindow.showWindow({
		//窗口名称
		title : "查看",
		//参数
		data : {id : id},
		//url
		url : $.cxt + "/keywordGroup/viewVoById",
	});	
}
