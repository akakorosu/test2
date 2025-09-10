
$(function() {
	initDataGrid();
	
	//查询按钮
	$("#searchUpList").click(function(){
		reloadJqGrid("search");
	});
	//新建按钮
	$("#createUpBtn").click(function(){
		showUpFormInsert();
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
		url : $.cxt + "/domainFuzzyRule/selectpagelist",
		datatype : "json",
		mtype : "POST",
		height : topjqGridHeight(),
		autowidth : true,
		colNames : [ '产品ID','产品名称','截取规则','获取结果分组序号','URL样例','操作人','操作时间','操作' ],
		colModel : [
			{name : 'prodid',align : 'center',index : 'prodid',editable : true,hidden : true},
			{name : 'prodName',align : 'center',index : 'prodName',editable : true,hidden : false},
			{name : 'parseRule',align : 'center',index : 'parseRule',editable : true},
			{name : 'getIndex',align : 'center',index : 'getIndex',editable : true},		
			{name : 'urlExample',align : 'center',index : 'urlExample',editable : true},
			{name : 'author',align : 'center',index : 'author',editable : true},
			{name : 'opTime',align : 'center',index : 'opTime',editable : true},
			/*{name : 'isValid',align : 'center',index : 'isValid',editable : true,formatter : parseStatus},
			{name : 'totalCount',align : 'center',index : 'totalCount',editable : true},*/			
			{name : 'act',align : 'center', formatter : renderOperation}
        ],
		viewrecords : true,
		rownumbers: true,
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		pager : '#grid-pager',
		loadComplete : topjqGridLoadComplete,
		caption : "规则列表"
	});	
}

//格式化最后一列
function renderOperation (cellvalue, options, cell) {
	var html = ''; 
	html += '<a onclick="showUpForm(\''+cell.id+'\')" href="#" title="修改">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	html += '<a onclick="deleteUp(\''+cell.id+'\')" href="#" title="删除">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;';
//	html += '<a onclick="viewUp(\''+cell.id+'\')" href="#" title="查看">查看</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	return html;
}

//重新加载表格
function reloadJqGrid (flag) {
	if(flag == undefined) {
		//新增和修改的时候清空查询条件
		topclear('gridSearch')
	}
	
	var prodName=$("#prod_name").val().trim();
	var parseRule=$("#parse_rule").val().trim();
	var author=$("#author").val();
	var timeRange=$("#opTime").val();
	var startTime="";
	var endTime="";
	if(timeRange){
		var str=timeRange.split("-");
		startTime=$.trim(str[0]);
		endTime=$.trim(str[1]);
		
	}else{
		startTime="";
		endTime="";
	}
	var page = $('#grid-table').getGridParam('page');
	$("#grid-table").jqGrid('setGridParam',{
        postData : {prodName:prodName,parseRule:parseRule,opTime:timeRange,author:author,startTime:startTime,endTime:endTime},
        page : page
    }).trigger("reloadGrid");
}


//弹出窗口(修改)
function showUpForm(id) {
	topwindow.showWindow({
		//窗口名称
		title : "编辑",
		//参数
		data : {id : id},
		//url
		url : $.cxt + "/domainFuzzyRule/showForm",
		bottons : [{
			title : "确认",
			fun : function() {
				var obj=topgetObjByObj($("#upForm .formField"));
				obj.parseRuleParam=$("#parseRule").val();
				obj.urlExample=$("#urlExample").val();
				if(topcheckoutForm("upForm")){
					$.ajax({
						url : $.cxt + "/domainFuzzyRule/insertOrUpdateVo", 
						data : obj,
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
//弹出窗口   新增
function showUpFormInsert(id) {
	topwindow.showWindow({
		//窗口名称
		title : "编辑",
		//参数
		data : {id : id},
		//url
		url : $.cxt + "/domainFuzzyRule/showForm",
		bottons : [{
			title : "确认",
			fun : function() {		
				if(topcheckoutForm("upForm")){
					$.ajax({
						url : $.cxt + "/domainFuzzyRule/insertOrUpdateVo", 
						data : topgetObjByObj($("#upForm .formField")),
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
function deleteUp(id) {
	topshowdialog("确认删除？", function(){
		$.ajax({
			url : $.cxt + "/domainFuzzyRule/deleteById",
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
function viewUp(id) {
	
	topwindow.showWindow({
		//窗口名称
		title : "查看",
		//参数
		data : {id : id},
		//url
		url : $.cxt + "/domainFuzzyRule/viewById",
	});	
}
function parseStatus (cellvalue, options, cell) {
	
	return cellvalue==1 ?'有效' :'无效';
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