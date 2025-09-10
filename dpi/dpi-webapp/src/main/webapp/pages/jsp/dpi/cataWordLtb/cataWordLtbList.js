
$(function() {
	initDataGrid();
	
	//查询按钮
	$("#searchCwlList").click(function(){
		reloadJqGrid("search");
	});
	//新建按钮
	$("#createCwlBtn").click(function(){
		showCwlForm();
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

var sntestprod=function(){
	topshowLabelTree.show(function(obj){
	
		var lvl=obj.level;
		var labelName=obj.labelName;
		var labelCode=obj.labelCode;
		
		
		if(lvl=='1'){
			
			$("#labelNameOverView").val(labelName);
			$("#labelCodeHidden1").val(labelCode);
		}else{
			//$("#labelId1").val(labelCode1);
			$("#labelNameOverView").val(labelName);
			$("#labelCodeHidden1").val(labelCode);
		}
		
	})
}
//初始化DataGrid
function initDataGrid(){
	$('#grid-table').jqGrid({
		url : $.cxt + "/cataWordLtb/selectpagelist",
		datatype : "json",
		mtype : "POST",
		height : topjqGridHeight(),
		autowidth : true,
		colNames : [ '分类标签','一类标签','二类标签','分类关键词','操作人','操作时间','操作' ],
		colModel : [ 
			{name : 'cataLabelName',align : 'center',index : 'cataLabelName',editable : true,hidden : false},
			{name : 'cataLabelName1',align : 'center',index : 'cataLabelName1',editable : true},
			{name : 'cataLabelName2',align : 'center',index : 'cataLabelName2',editable : true},
			{name : 'cataWord',align : 'center',index : 'cataWord',editable : true},
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
		caption : "分类词库列表"
	});	
}

//格式化最后一列
function renderOperation (cellvalue, options, cell) {
	var html = ''; 
	html += '<a onclick="showCwlForm(\''+cell.id+'\')" href="#" title="修改">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	html += '<a onclick="deleteCwl(\''+cell.id+'\')" href="#" title="删除">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;';
//	html += '<a onclick="viewCwl(\''+cell.id+'\')" href="#" title="查看">查看</a>&nbsp;&nbsp;&nbsp;&nbsp;';
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
	//$("#gridSearch input").val("");//清空所有搜索条件
	$("#labelNameOverView").val("");
	$("#cataWord").val("");
	$("#author").val("");
	$("#opTime").val("");
	$("#labelCodeHidden1").val("");
}


//弹出窗口(新增或修改)
function showCwlForm(id) {
	topwindow.showWindow({
		//窗口名称
		title : "编辑",
		//参数
		data : {id : id},
		//url
		url : $.cxt + "/cataWordLtb/showForm",
		bottons : [{
			title : "确认",
			fun : function() {
				//校验，参数为表单父级id
				if(topcheckoutForm("cwlForm")){
					$.ajax({
						url : $.cxt + "/cataWordLtb/insertOrUpdateVo", 
						data : topgetObjByObj($("#cwlForm .formField")),
						type: "POST",
						success : function(data) {
							//重新加载表格
							reloadJqGrid();
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
function deleteCwl(id) {
	topshowdialog("确认删除？", function(){
		$.ajax({
			url : $.cxt + "/cataWordLtb/deleteVoById",
			data : {id : id},
			type: "POST",
			success : function(data) {
				//重新加载表格
				reloadJqGrid();
				//操作提示
				topshowAlertSuccessDiv();
			}
		})
	})
}

//查看
function viewCwl(id) {
	topwindow.showWindow({
		//窗口名称
		title : "查看",
		//参数
		data : {id : id},
		//url
		url : $.cxt + "/cataWordLtb/viewVoById",
	});	
}
