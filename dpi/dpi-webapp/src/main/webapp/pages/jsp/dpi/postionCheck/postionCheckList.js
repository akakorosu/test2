
$(function() {
	initDataGrid();
	
	//查询按钮
	$("#searchCwlList").click(function(){
		reloadJqGrid("search");
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
		url : $.cxt + "/positionCheck/selectpagelist",
		datatype : "json",
		mtype : "POST",
		height : topjqGridHeight(),
		autowidth : true,
		colNames : [ '产品名称','url','ruleUrl','域名','匹配类型','经度规则','截取标号','经度测试结果','经度计算结果','纬度规则','截取标号','纬度测试结果','纬度计算结果','分组类型','坐标系','操作' ],
		colModel : [

			{name : 'prodName',align : 'center',index : 'prodName',editable : true},
			{name : 'url',align : 'center',index : 'url',editable : true},
			{name : 'ruleUrl',align : 'center',index : 'ruleUrl',editable : true},
			{name : 'host',align : 'center',index : 'host',editable : true,hidden : false},
			{name : 'matchType',align : 'center',index : 'matchType',editable : true},
			{name : 'longitudeRule',align : 'center',index : 'longitudeRule',editable : true},
			{name : 'getIndexLong',align : 'center',index : 'getIndexLong',editable : true},
			{name : 'tLongResult',align : 'center',index : 'tLongResult',editable : true,hidden : false},
			{name : 'longResult',align : 'center',index : 'longResult',editable : true,hidden : false},
			{name : 'latitudeRule',align : 'center',index : 'latitudeRule',editable : true},
			{name : 'getIndexLati',align : 'center',index : 'getIndexLati',editable : true},
			{name : 'tLatiResult',align : 'center',index : 'tLatiResult',editable : true,hidden : false},
			{name : 'latiResult',align : 'center',index : 'latiResult',editable : true,hidden : false},
			{name : 'groupType',align : 'center',index : 'groupType',editable : true},
			
			{name : 'system',align : 'center',index : 'system',editable : true},
			{name : 'act',align : 'center', formatter : renderOperation}
        ],
		viewrecords : true,
		rownumbers: true,
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		pager : '#grid-pager',
		loadComplete : topjqGridLoadComplete,
		caption : "位置规则校验列表"
	});	
}

//格式化最后一列
function renderOperation (cellvalue, options, cell) {
	var html = ''; 
	html += '<a onclick="audit(\''+cell.host+'\')" href="#" title="稽核">稽核</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	html += '<a onclick="deleteUrl(\''+cell.host+'\')" href="#" title="删除">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	return html;
}
function audit(host){
	topshowdialog("确认稽核？", function(){
		$.ajax({
			url : $.cxt + "/positionCheck/audit",
			data : {host:host},
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
function deleteUrl(host){
	topshowdialog("确认删除？", function(){
		$.ajax({
			url : $.cxt + "/positionCheck/deleteUrl",
			data : {host:host},
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

//重新加载表格
function reloadJqGrid (flag) {
	if(flag == undefined) {
		//新增和修改的时候清空查询条件
		topclear('gridSearch')
	}
	var obj=topgetObjByObj($("#gridSearch .searchField"));
	var timeRange=$("#opTime").val();
	var str=timeRange.split("-");
	var startTime=$.trim(str[0]);
	var endTime=$.trim(str[1]);
	obj.startTime=startTime;
	obj.endTime=endTime;
	$("#grid-table").jqGrid('setGridParam',{
        postData :obj,
        page : 1
    }).trigger("reloadGrid");
}

//清空查询条件
function clearGridSearch(){
	$("#gridSearch input").val("");//清空所有搜索条件
}

//重新加载表格
function reloadJqGrid (flag) {
	if(flag == undefined) {
		//新增和修改的时候清空查询条件
		topclear('gridSearch')
	}
	var obj=topgetObjByObj($("#gridSearch .searchField"));
	var timeRange=$("#opTime").val();
	var str=timeRange.split("-");
	obj.startTime=$.trim(str[0]);
	obj.endTime=$.trim(str[1]);
	$("#grid-table").jqGrid('setGridParam',{
		postData :obj,
		page : 1
	}).trigger("reloadGrid");
}
