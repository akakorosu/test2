
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
		url : $.cxt + "/groupClass/selectpagelist",
		datatype : "json",
		mtype : "POST",
		height : topjqGridHeight(),
		autowidth : true,
		colNames : [ '集团大类','大类名称','集团小类','小类名称','产品ID','产品名称','操作人','操作时间','操作' ],
		colModel : [ 
			{name : 'classCode',align : 'center',index : 'classCode',editable : true,hidden : false},
			{name : 'className',align : 'center',index : 'className',editable : true},
			{name : 'subClassCode',align : 'center',index : 'subClassCode',editable : true},
			{name : 'subClassName',align : 'center',index : 'subClassName',editable : true},		
			{name : 'prodid',align : 'center',index : 'prodid',editable : true,hidden : true},
			{name : 'prodName',align : 'center',index : 'prodName',editable : true},
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
		caption : "集团大小类列表"
	});	
}

//格式化最后一列
function renderOperation (cellvalue, options, cell) {
	var html = ''; 
	html += '<a onclick="showPiForm(\''+cell.id+'\')" href="#" title="修改">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	html += '<a onclick="deletePi(\''+cell.id+'\')" href="#" title="删除">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;';
//	html += '<a onclick="viewPi(\''+cell.id+'\')" href="#" title="查看">查看</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	return html;
}

//重新加载表格
function reloadJqGrid (flag) {
	/*debugger
	console.log(topgetObjByObj($("#gridSearch .searchField")));*/
	if(flag == undefined) {
		//新增和修改的时候清空查询条件
		topclear('gridSearch')
	}
	var prodName=$("#prod_Name").val().trim();
	var opTime=$("#opTime").val().trim();
	var startTime='';
	var endTime='';
	if(opTime){
		var str=opTime.split("-");
		startTime=$.trim(str[0]);
		endTime=$.trim(str[1]);
	}
	var author=$("#author").val().trim();
	var page = $('#grid-table').getGridParam('page');
	$("#grid-table").jqGrid('setGridParam',{
        postData : {prodName:prodName,startTime:startTime,endTime:endTime,author:author},
        page :page
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
		url : $.cxt + "/groupClass/showForm",
		bottons : [{
			title : "确认",
			fun : function() {
				if(topcheckoutForm("piForm")){
					$.ajax({
						url : $.cxt + "/groupClass/insertOrUpdateVo", 
						data : topgetObjByObj($("#piForm .formField")),
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
function deletePi(id) {
	topshowdialog("确认删除？", function(){
		$.ajax({
			url : $.cxt + "/groupClass/deleteVoById",
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
function viewPi(id) {
	
	topwindow.showWindow({
		//窗口名称
		title : "查看",
		//参数
		data : {id : id},
		//url
		url : $.cxt + "/groupClass/viewVoById",
	});	
}
function messageAlert(message){
	bootbox.dialog({
        message: "<span style=\"color:#000\">"+message+"</span>",
        title: "消息提示",
        buttons: {
            OK: {
                label: "确定",
                className: "btn-success",
            }
        }
    });
}
function parseStatus (cellvalue, options, cell) {
	
	return cellvalue==1 ?'有效' :'无效';
}