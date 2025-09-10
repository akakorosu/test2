
$(function() {
	initDataGrid();
	
	//查询按钮
	$("#searchPrList").click(function(){
		reloadJqGrid("search");
	});
	//新建按钮
	$("#createPrBtn").click(function(){
		showPrForm();
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
		url : $.cxt + "/positionRule/selectpagelist",
		datatype : "json",
		mtype : "POST",
		height : topjqGridHeight(),
		autowidth : true,
		colNames : [ 'NUM','地址','域名','匹配类型','经度规则','维度规则','系统规则','定位系统','应用分组名称','操作人','操作时间','操作' ],
		colModel : [ 
            {name : 'num',align : 'center',index : 'num',editable : true,hidden : false,width:40},
            {name : 'url',align : 'center',index : 'url',editable : true,hidden : false},
			{name : 'host',align : 'center',index : 'host',editable : true,hidden : false},
			{name : 'matchType',align : 'center',index : 'matchType',editable : true,width:60},
			{name : 'longitudeRule',align : 'center',index : 'longitudeRule',editable : true},
			{name : 'latitudeRule',align : 'center',index : 'latitudeRule',editable : true},
			{name : 'systemRule',align : 'center',index : 'systemRule',editable : true},
			{name : 'system',align : 'center',index : 'system',editable : true},
			{name : 'groupName',align : 'center',index : 'groupName',editable : true},
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
		caption : "位置解析规则列表"
	});	
}

//格式化最后一列
function renderOperation (cellvalue, options, cell) {
	var html = ''; 
	html += '<a onclick="showPrForm(\''+cell.id+'\')" href="#" title="修改">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	html += '<a onclick="deletePr(\''+cell.id+'\')" href="#" title="删除">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	html += '<a onclick="viewPr(\''+cell.id+'\')" href="#" title="查看">查看</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	return html;
}

//重新加载表格
function reloadJqGrid (flag) {
	if(flag == undefined) {
		//新增和修改的时候清空查询条件
		topclear('gridSearch')
	}
	var obj={};
	obj.url=$("#url").val().trim();
	obj.host=$("#host").val().trim();
	obj.num=$("#num").val().trim();
	obj.groupName=$("#groupName").val().trim();
	obj.author=$("#author").val().trim();
	var timeRange=$("#opTime").val().trim();
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
	$("#grid-table").jqGrid('setGridParam',{
        postData : obj,
        page : 1
    }).trigger("reloadGrid");
}


//弹出窗口(新增或修改)
function showPrForm(id) {
	topwindow.showWindow({
		//窗口名称
		title : "编辑",
		//参数
		data : {id : id},
		//url
		url : $.cxt + "/positionRule/showForm",
		bottons : [{
			title : "确认",
			fun : function() {
				//校验，参数为表单父级id
				var obj=topgetObjByObj($("#prForm .formField"));
				
				if(topcheckoutForm("prForm")){
					$.ajax({
						url : $.cxt + "/positionRule/insertOrUpdateVo", 
						data : obj,
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
function deletePr(id) {
	topshowdialog("确认删除？", function(){
		$.ajax({
			url : $.cxt + "/positionRule/deleteVoById",
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
function viewPr(id) {
	topwindow.showWindow({
		//窗口名称
		title : "查看",
		//参数
		data : {id : id},
		//url
		url : $.cxt + "/positionRule/viewVoById",
	});	
}
