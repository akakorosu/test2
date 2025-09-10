
$(function() {
	initDataGrid();
	
	//查询按钮
	$("#searchSwlList").click(function(){
		reloadJqGrid("search");
	});
	//新建按钮
	$("#createSwlBtn").click(function(){
		showSwlForm();
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
		url : $.cxt + "/stopWordLib/selectpagelist",
		datatype : "json",
		mtype : "POST",
		height : topjqGridHeight(),
		autowidth : true,
		colNames : [ '停用关键词','操作人','操作时间','操作' ],
		colModel : [ 
			{name : 'stopWord',align : 'center',index : 'stopWord',editable : true},
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
		caption : "停用词库列表"
	});	
}

//格式化最后一列
function renderOperation (cellvalue, options, cell) {
	var html = ''; 
	html += '<a onclick="showSwlForm(\''+cell.id+'\')" href="#" title="修改">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	html += '<a onclick="deleteSwl(\''+cell.id+'\')" href="#" title="删除">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;';
//	html += '<a onclick="viewSwl(\''+cell.id+'\')" href="#" title="查看">查看</a>&nbsp;&nbsp;&nbsp;&nbsp;';
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
	var stopWord=$("#stopWord").val().trim();
	var author=$("#author").val().trim();
	var opTime=$("#opTime").val().trim();
	var startTime='';
	var endTime='';
	if(opTime){
		var str=opTime.split("-");
		startTime=$.trim(str[0]);
		endTime=$.trim(str[1]);
	}
	$("#grid-table").jqGrid('setGridParam',{
        postData : {stopWord:stopWord,author:author,startTime:startTime,endTime:endTime},
        page : 1
    }).trigger("reloadGrid");
}


//弹出窗口(新增或修改)
function showSwlForm(id) {
	topwindow.showWindow({
		//窗口名称
		title : "编辑",
		//参数
		data : {id : id},
		//url
		url : $.cxt + "/stopWordLib/showForm",
		bottons : [{
			title : "确认",
			fun : function() {
				//校验，参数为表单父级id
				if(topcheckoutForm("swlForm")){
					$.ajax({
						url : $.cxt + "/stopWordLib/insertOrUpdateVo", 
						data : topgetObjByObj($("#swlForm .formField")),
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
function deleteSwl(id) {
	topshowdialog("确认删除？", function(){
		$.ajax({
			url : $.cxt + "/stopWordLib/deleteVoById",
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
function viewSwl(id) {
	topwindow.showWindow({
		//窗口名称
		title : "查看",
		//参数
		data : {id : id},
		//url
		url : $.cxt + "/stopWordLib/viewVoById",
	});	
}
