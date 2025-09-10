var label_name1='';
var label_name2='';
$(function() {
	initDataGrid();
	
	//查询按钮
	$("#searchPiList").click(function(){
		reloadJqGrid("search");
	});
	//新建按钮
	$("#createPiBtn").click(function(){
		showPiForm();
	})
	$("#clearRuleSearch").click(function(){
		label_name1='';
		label_name2=''
		$('#label_Name').val('');
		$('#label_code').val('');
		$('#label').val('');
		$('#author').val('');
		$('#opTime').val('');
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
var select_label = function(){
	topshowLabelTree.show(function(obj){
		label_name1='';
		label_name2='';
		if(obj.level==1){
			label_name1=obj.labelName;
			$('#label').removeAttr("name");
			$('#label').attr('name','labelName1');  
		}else if(obj.level==2){
			label_name2=obj.labelName;
			$('#label').removeAttr("name");
			$('#label').attr('name','labelName2');
		}
		$("#label").val(obj.labelName);
	})
}

//初始化DataGrid
function initDataGrid(){
	$('#grid-table').jqGrid({
		url : $.cxt + "/productLabel/selectpagelist",
		datatype : "json",
		mtype : "POST",
		height : topjqGridHeight(),
		autowidth : true,
		colNames : [ '标签编码','分类ID','分类名称','一级标签ID','一级标签名称','二级标签ID','二级标签名称','操作人','操作时间','操作' ],
		colModel : [ 
			{name : 'labelCode',align : 'center',index : 'labelCode',editable : true,hidden : false},
			{name : 'lableType',align : 'center',index : 'lableType',editable : true,hidden : false},
			{name : 'labelName',align : 'center',index : 'labelName',editable : true},
			{name : 'labelCode1',align : 'center',index : 'labelCode1',editable : true},
			{name : 'labelName1',align : 'center',index : 'labelName1',editable : true},		
			{name : 'labelCode2',align : 'center',index : 'labelCode2',editable : true,hidden : false},
			{name : 'labelName2',align : 'center',index : 'labelName2',editable : true},
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
		caption : "标签列表"
	});	
}

//格式化最后一列
function renderOperation (cellvalue, options, cell) {
	var html = ''; 
	html += '<a onclick="showPiForm(\''+cell.id+'\')" href="#" title="修改">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	html += '<a onclick="deletePi(\''+cell.id+'\')" href="#" title="删除">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	html += '<a onclick="viewPi(\''+cell.id+'\')" href="#" title="查看">查看</a>&nbsp;&nbsp;&nbsp;&nbsp;'; 
	return html;
}

//重新加载表格
function reloadJqGrid (flag) {
	var label_name=$('#label_Name').val();
	if(label_name=='全部'){
		label_name='';
	}
	var label_code=$('#label_code').val().trim();
	if(flag == undefined) {
		//新增和修改的时候清空查询条件
		topclear('gridSearch')
	}
	var author=$("#author").val();
	var timeRange=$("#opTime").val();
	var startTime='';
	var endTime='';
	if(timeRange){
		var str=timeRange.split("-");
		startTime=$.trim(str[0]);
		endTime=$.trim(str[1]);
	}else{
		startTime='';
		endTime='';
	}
	
	var page = $('#grid-table').getGridParam('page');
	$("#grid-table").jqGrid('setGridParam',{
        postData : {labelName:label_name,labelName1:label_name1,labelName2:label_name2,labelCode:label_code,author:author,startTime:startTime,endTime:endTime,opTime:timeRange},
        page : page
    }).trigger("reloadGrid");
}


//弹出窗口(新增或修改)
function showPiForm(id) {
	topwindow.showWindow({
		//窗口名称
		title : "编辑",
		//参数
		data : {id : id},
		//url
		url : $.cxt + "/productLabel/showForm",
		bottons : [{
			title : "确认",
			fun : function() {
				//校验，参数为表单父级id
				if(topcheckoutForm("piForm")){
					$.ajax({
						url : $.cxt + "/productLabel/insertOrUpdateVo", 
						data : topgetObjByObj($("#piForm .formField")),
						type: "POST",
						success : function(data) {
							label_name1='';
							label_name2=''
							$('#label_Name').val('');
							$('#label_code').val('');
							$('#label').val('');
							$('#author').val('');
							$('#opTime').val('');
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
function deletePi(id) {
	var rowData = $("#grid-table").jqGrid('getRowData',id);
	var labelCode1=rowData.labelCode1;
	var labelCode2=rowData.labelCode2;
	topshowdialog("确认删除？", function(){
		$.ajax({
			url : $.cxt + "/productLabel/deleteVoById",
			data : {labelCode1 : labelCode1,labelCode2:labelCode2,id:id},
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
function viewPi(id) {
	topwindow.showWindow({
		//窗口名称
		title : "查看",
		//参数
		data : {id : id},
		//url
		url : $.cxt + "/productLabel/viewVoById",
	});	
}
