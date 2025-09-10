
$(function() {
	initDataGrid();
	
	//查询按钮
	$("#searchPiList").click(function(){
		reloadJqGrid("search");
	});
	//新建按钮
	$("#createPiBtn").click(function(){
		showQueryForm();
	});
	$("#tongbubiao").click(function(){
		tongbubiao();
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
	$("#exportProdType").chosen({
		disable_search : true
	}).change(function(option, checked){
		
		
    });
});


//初始化DataGrid
function initDataGrid(){
	$('#grid-table').jqGrid({
		url : $.cxt + "/productInfo/selectpagelist",
		datatype : "json",
		mtype : "POST",
		height : topjqGridHeight(),
		autowidth : true,
		colNames : [ '产品ID','产品名称','一级标签','二级标签','操作人','操作时间','操作' ],
		colModel : [ 
			{name : 'prodId',align : 'center',index : 'prodId',editable : true,hidden : false,width:40},
			{name : 'prodName',align : 'center',index : 'prodName',editable : true,width:40},
			{name : 'labelName1',align : 'center',index : 'labelName1',editable : true,width:40},
			{name : 'labelName2',align : 'center',index : 'labelName2',editable : true,width:40},
			{name : 'author',align : 'center',index : 'author',editable : true,width:20},
			{name : 'opTime',align : 'center',index : 'opTime',editable : true,width:20},
			{name : 'act',align : 'center', formatter : renderOperation,width:80}
        ],
		viewrecords : true,
		rownumbers: true,
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		pager : '#grid-pager',
		loadComplete : topjqGridLoadComplete,
		caption : "产品列表"
	});	
}
var sntestprod=function(){
	topshowLabelTree.show(function(obj){
	
		var lvl=obj.level;
		var labelName=obj.labelName;
		var labelCode=obj.labelCode;
		
		
		if(lvl=='1'){
			
			$("#labelNameOverView").val(labelName);
			$("#labelCodeHidden").val(labelCode);
		}else{
			//$("#labelId1").val(labelCode1);
			$("#labelNameOverView").val(labelName);
			$("#labelCodeHidden").val(labelCode);
		}
		
	})
}
//格式化最后一列
function renderOperation (cellvalue, options, cell) {
	var html = ''; 
	html += '<a onclick="showQueryForm(\''+cell.prodId+'\')" href="#" title="修改">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	html += '<a onclick="deletePi(\''+cell.prodId+'\')" href="#" title="删除">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	html += '<a onclick="viewPi(\''+cell.prodId+'\')" href="#" title="规则列表">规则列表</a>&nbsp;&nbsp;&nbsp;&nbsp;'; 
	return html;
}

//重新加载表格
function reloadJqGrid (flag) {
	if(flag == undefined) {
		//新增和修改的时候清空查询条件
		topclear('gridSearch');
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
	var prodName =$("#prodName").val();
	
	if(prodName.indexOf("~") != -1){
		var batchFlag=$("#exportProdType").val();
		obj.batchFlag=batchFlag;
	}else{
		var batchFlag=$("#exportProdType").val();
		obj.batchFlag=batchFlag;
	}
	var page = $('#grid-table').getGridParam('page');
	$("#grid-table").jqGrid('setGridParam',{
        postData : obj,
        page : page
    }).trigger("reloadGrid");
}

//清空查询条件
function clearGridSearch(){
	//$("#gridSearch input").val("");//清空所有搜索条件
	$("#prodId").val("");
	$("#prodName").val("");
	$("#labelNameOverView").val("");
	$("#author").val("");
	$("#opTime").val("");
	$("#labelCodeHidden").val("");
}

//弹出窗口(新增或修改)
function showQueryForm(prodId) {
	topwindow.showWindow({
		//窗口名称
		title : "编辑",
		//参数
		data : {prodId : prodId},
		//url
		url : $.cxt + "/productInfo/showForm",
		bottons : [{
			title : "确认",
			fun : function() {
				//校验，参数为表单父级id
				if(topcheckoutForm("piForm")){
				    console.log(topgetObjByObj($("#piForm .formField")));
					$.ajax({
						url : $.cxt + "/productInfo/insertOrUpdateVo", 
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
function deletePi(prodId) {
	topshowdialog("确认删除？", function(){
		$.ajax({
			url : $.cxt + "/productInfo/deleteVoById",
			data : {prodId : prodId},
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
function viewPi(prodId) {
	topwindow.showWindow({
		//窗口名称
		title : "规则列表",
		//参数
		data : {prodId : prodId},
		//url
		url : $.cxt + "/productInfo/viewVoById",
		bottons : [{
			title : "确认",
			fun : function() {
				var prodIdNew=$.trim($("#prodIdnew").val());
				
				//校验，参数为表单父级id
				if(prodIdNew){
				    var obj={};
				    obj.prodIdOld=prodId;
				    obj.prodId=prodIdNew;
					$.ajax({
						url : $.cxt + "/productInfo/updateAll", 
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
function tongbubiao(){
	$.ajax({
		url : $.cxt + "/productInfo/tongBuBiao", 
		//data : topgetObjByObj($("#piForm .formField")),
		type: "POST",
		success : function(data) {
			//重新加载表格
//			reloadJqGrid("search");
//			//关闭窗口
//			topwindow.removeWindow();
//			//操作提示
			topshowAlertSuccessDiv();
		}
	});
}