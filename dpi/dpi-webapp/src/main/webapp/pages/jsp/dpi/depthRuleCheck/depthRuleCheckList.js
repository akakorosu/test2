
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
		url : $.cxt + "/depthRuleCheck/selectpagelist",
		datatype : "json",
		mtype : "POST",
		height : topjqGridHeight(),
		autowidth : true,
		colNames : [ 'ID','产品名称','规则类型','动作目录名称','URL','解析规则','截取标号','测试结果','分组类型','分组名称','解析结果','解码结果','操作' ],
		colModel : [
			{name : 'id',align : 'center',index : 'id',hidden : true,editable : true},
			{name : 'prodName',align : 'center',index : 'prodName',editable : true},
			{name : 'ruleType',align : 'center',index : 'ruleType',editable : true},
			{name : 'channelName',align : 'center',index : 'channelName',editable : true,hidden : false},
			{name : 'url',align : 'center',index : 'url',editable : true},
			{name : 'parseRule',align : 'center',index : 'parseRule',editable : true},
			{name : 'getIndex',align : 'center',index : 'getIndex',editable : true},
			{name : 'testResult',align : 'center',index : 'testResult',editable : true,hidden : false},
			{name : 'groupType',align : 'center',index : 'groupType',editable : true},
			{name : 'groupName',align : 'center',index : 'groupName',editable : true},
			{name : 'parseResult',align : 'center',index : 'parseResultDec',editable : true},
			{name : 'parseResultDec',align : 'center',index : 'parseResultDec',editable : true},
			{name : 'act',align : 'center', formatter : renderOperation}
        ],
		viewrecords : true,
		rownumbers: true,
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		pager : '#grid-pager',
		loadComplete : topjqGridLoadComplete,
		caption : "深度规则校验列表"
	});	
}

//格式化最后一列
function renderOperation (cellvalue, options, cell) {
	var html = ''; 
	html += '<a onclick="audit(\''+cell.id+'\')" href="#" title="稽核">稽核</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	html += '<a onclick="remove(\''+cell.id+'\')" href="#" title="删除">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	return html;
}
function audit(id){
	topshowdialog("确认稽核？", function(){
		$.ajax({
			url : $.cxt + "/depthRuleCheck/audit",
			data : {id:id},
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
function remove(id){
	topshowdialog("确认删除？", function(){
		$.ajax({
			url : $.cxt + "/depthRuleCheck/delete",
			data : {id:id},
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
