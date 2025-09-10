
$(function() {
	initDataGrid();
	
	//查询按钮
	$("#searchDrList").click(function(){
		reloadJqGrid("search");
		
	});
	//新建按钮
	$("#createDrBtn").click(function(){
		showDrForm();
	});
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
		url : $.cxt + "/domainRule/selectpagelist",
		datatype : "json",
		mtype : "POST",
		height : topjqGridHeight(),
		autowidth : true,
		colNames : [ '域名编码','产品Id','产品名称','操作人','操作时间','操作' ],
		colModel : [ 
			{name : 'domainCode',align : 'center',index : 'domainCode',editable : true,hidden : false},
			{name : 'prodId',align : 'center',index : 'prodId',editable : true},
			{name : 'prodName',align : 'center',index : 'prodName',editable : true},
			{name : 'author',align : 'center',index : 'author',editable : true},
			{name : 'opTime',align : 'center',index : 'opTime',editable : true},
//			{name : 'isValid',align : 'center',index : 'isValid',editable : true,formatter : isValid},
			{name : 'act',align : 'center', formatter : renderOperation}
        ],
		viewrecords : true,
		rownumbers: true,
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		pager : '#grid-pager',
		loadComplete : topjqGridLoadComplete,
		caption : "域名列表"
	});	
}

//格式化最后一列
function renderOperation (cellvalue, options, cell) {
	var html = ''; 
	html += '<a onclick="showDrForm(\''+cell.domainCode+'\')" href="#" title="修改">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	html += '<a onclick="deleteDr(\''+cell.domainCode+'\')" href="#" title="删除">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;';
//	html += '<a onclick="viewDr(\''+cell.domainCode+'\')" href="#" title="查看">查看</a>&nbsp;&nbsp;&nbsp;&nbsp;';
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
function showDrForm(domainCode) {
	topwindow.showWindow({
		//窗口名称
		title : "编辑",
		//参数
		data : {domainCode : domainCode},
		//url
		url : $.cxt + "/domainRule/showForm",
		bottons : [{
			title : "确认",
			fun : function() {
				//校验，参数为表单父级id
				if(topcheckoutForm("drForm")){
					$.ajax({
						url : $.cxt + "/domainRule/insertOrUpdateVo", 
						data : topgetObjByObj($("#drForm .formField")),
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
function deleteDr(domainCode) {
	topshowdialog("确认删除？", function(){
		$.ajax({
			url : $.cxt + "/domainRule/deleteVoById",
			data : {domainCode : domainCode},
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
function viewDr(domainCode) {
	topwindow.showWindow({
		//窗口名称
		title : "查看",
		//参数
		data : {domainCode : domainCode},
		//url
		url : $.cxt + "/domainRule/viewVoById",
	});	
}
