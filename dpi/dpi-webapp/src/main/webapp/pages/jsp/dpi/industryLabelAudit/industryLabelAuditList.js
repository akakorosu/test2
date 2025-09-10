var queryLabelName="";
var auditStatus="";
var topNum="";
var selectArr=[];

$(function() {
	initDataGrid();
	initBatch();
    $("#backto").click(function(){
    	window.location.href=$.cxt + "/pages/jsp/dpi/operateCenter/operateCenter.jsp";
	})
	//查询按钮
	$("#searchPiList").click(function(){
		selectArr=[];
		reloadJqGridLabel();
	});
	//清空按钮
	$("#clearList").click(function(){
		topclear('gridSearch');
		queryLabelName='';
		$("#auditStatus option:first").prop("selected", 'selected').trigger("chosen:updated");
		$("#top option:first").prop("selected", 'selected').trigger("chosen:updated");
		reloadJqGridLabel()
	});
	$("#auditStatus").chosen({
		disable_search : true
	}).change(function(option, checked){
		auditStatusChange();
    });
	$("#top").chosen({
		disable_search : true
	}).change(function(option, checked){
		topChange();
    });
	/*$("#auditStatus").change(function(){
		auditStatusChange();
	});
	$("#top").change(function(){
		topChange();
	});*/
});
//初始化DataGrid
function initDataGrid(){
	$('#grid-table').jqGrid({
		url : $.cxt + "/industryLabelAudit/selectpagelist1",
		postData:{monthId:time},
		datatype : "json",
		mtype : "POST",
		height : topjqGridHeight(),
		autowidth : true,
		colNames : [ '序号','产品Id','产品名称','一级标签','二级标签','一级标签Code','二级标签Code','人数','次数','流量(M)','稽核状态','操作'],
		colModel : [ 
			{name : 'rowNums',align : 'center',index : 'rowNums',editable : true,sortable : false},
			{name : 'prodId',align : 'center',index : 'prodId',editable : true ,hidden:true,sortable : false},
			{name : 'prodName',align : 'center',index : 'prodName',editable : true,sortable : false},
			{name : 'labelName1',align : 'center',index : 'labelName1',editable : true,sortable : false},
			{name : 'labelName2',align : 'center',index : 'labelName2',editable : true,sortable : false},
			{name : 'labelId1',align : 'center',index : 'labelId1',editable : true,hidden:true,sortable : false},
			{name : 'labelId2',align : 'center',index : 'labelId2',editable : true,hidden:true,sortable : false},
			{name : 'userTotal',align : 'center',index : 'userTotal',editable : true,sortable : true},
			{name : 'userCount',align : 'center',index : 'userCount',editable : true,sortable : true},
			{name : 'flow',align : 'center',index : 'flow',editable : true,sortable : true},
			{name : 'isCheck',align : 'center',index : 'isCheck',editable : true, formatter :parseStatus,sortable : false},
			{name : 'act',align : 'center', formatter : formatterAct}
        ],
        sortname: 'userTotal',
        sortable:true,
        sortorder :"desc",
		viewrecords : true,
		//rownumbers: true,
		multiselect: true,
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		pager : '#grid-pager',
		loadComplete : topjqGridLoadComplete,
		caption : "行业标签稽核列表",
		gridComplete:function() {
		    var _this = this;
		    var rowData = $(_this).jqGrid('getRowData');
		    for(var i =0,n=rowData.length;i<n;i++){
		    	var obj = rowData[i];
		    	// var rowData = $("#idx_table").jqGrid('getRowData',rowId);
		    	if(selectArr[obj.prodId]){
		    		 $(_this).jqGrid('setSelection',i+1,false);
		    	}
		    }
		},
		// 这里和beforeSelectRow()俩个函数，一起组合让jqGrid表格单选，
		onSelectRow:function(rowid,state, e) {
			var obj = $(this).jqGrid('getRowData',rowid);
		    if(state){
		    	selectArr[obj.prodId]=obj;
		    }else{
		    	if(selectArr[obj.prodId]){
		    		delete  selectArr[obj.prodId];
		    	} 
		    }
		    //selectRow(rowid,state);
		},
		onSelectAll:function(aRowids,status) {
            var _this = this;
            var rowData = $(_this).jqGrid('getRowData');
            for(var i=0,n=rowData.length;i<n;i++){
            	var obj = rowData[i];
            	if(status){
            		selectArr[obj.prodId]=obj;
	            }else{
	            	if(selectArr[obj.prodId]){
	            		delete  selectArr[obj.prodId];
			    	} 
	            }    
            }
                   
        },
	});	
}

//格式化最后一列
function formatterAct (cellvalue, options, cell) {
	var html = '';
	
	html += '<a onclick="showPiForm(\''+cell.prodId+'\')" href="#" title="修改">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	if(cell.isCheck==1){
		html += '<a id="audit"  href="#" title="已稽核" style="color:#696969;">已稽核</a>&nbsp;&nbsp;&nbsp;&nbsp;';
		
	}else{
		html += '<a id="audit" onclick="audit(\''+cell.prodId+'\')" href="#" title="稽核">稽核</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	}
	return html;
}
//重新加载表格
function reloadJqGridLabel() {
	selectArr=[]; //刷新后 清除选中状态的缓存
	var isCheck=$("#auditStatus option:selected").text();
	var page = $('#grid-table').getGridParam('page');
	if('稽核状态'==isCheck){
		isCheck='';
	}
	if('已稽核'==isCheck){
		isCheck='1';
	}
	if('未稽核'==isCheck){
		isCheck='0';
	}
	var topNum =$("#top option:selected").text();
	if('TOP'==topNum){
		topNum='';
	}
	$("#grid-table").jqGrid("clearGridData");
	$("#grid-table").jqGrid('setGridParam',{
        postData : {monthId:time,labelName:queryLabelName,isCheck:isCheck,prodRnk:topNum},
        page : page
    }).trigger("reloadGrid");
	
}

//选择的稽核状态
function auditStatusChange(){
	auditStatus=$("#auditStatus option:selected").text();
	
}
//选择的top数
function topChange(){
	topNum=$("#top option:selected").text();

}

//稽核
function audit(prodId) {
	$.ajax({
		url : $.cxt + "/industryLabelAudit/updateCheck", 
		data : {prodId:prodId},
		type: "POST",
		success : function(data) {
			//重新加载表格
			reloadJqGrid();
		}
	});
}
//弹出窗口(新增或修改)
function showPiForm(prodId) {

	topwindow.showWindow({
		//窗口名称
		title : "编辑",
		//参数
		data : {prodId : prodId,dateId:time},
		//url
		url : $.cxt + "/industryLabelAudit/showForm",
		bottons : [{
			title : "确认",
			fun : function() {
				
				var labellvl= $("#labellvlHidden").val();
				var formLabelId1= $("#labelId1").val();
				var formLabelName1= $("#labelName1").val();
				/*var formLabelId2= $("#labelId2Hidden").val();
				var formLabelName2= $("#labelName2Hidden").val();*/
				var prodId=$("#prodId").val();
				if(!formLabelId1){
					messageAlert("请选择标签");
					return false;
				}
			    //二级标签不为空
				if(labellvl=='2'){
					if(topcheckoutForm("upForm")){
						$.ajax({
							url : $.cxt + "/industryLabelAudit/updateLabel2", 
							data : {monthId:time,prodId:prodId,LabelCode2:formLabelId1,LabelName2:formLabelName1},
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
				}else{
					if(topcheckoutForm("upForm")){
						$.ajax({
							url : $.cxt + "/industryLabelAudit/updateLabel1", 
							data : {monthId:time,prodId:prodId,LabelCode1:formLabelId1,LabelName1:formLabelName1},
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
				
				
			}
		}]
	});
}
function parseStatus (cellvalue, options, cell) {
	
	return cellvalue==1 ?'已稽核' :'未稽核';
}
function initBatch () {
	$("#batch").click( function () {
		
		var isEmpty = $.isEmptyObject(selectArr);
		
		if(isEmpty){
			messageAlert("至少选择一条数据");
			return false;
		}
		var prodId='';
		for(var key in selectArr){
			var rowData = selectArr[key];
				prodId+=rowData.prodId+',';
		}
		
		prodId=prodId.substring(0,prodId.length-1);
		
		$.ajax({
			url : $.cxt + "/industryLabelAudit/batchCheck",
			type: "POST",
			data:{
				prodId:prodId,
				
			},
			async : false,
			success: function(json){
				var data  = JSON.parse(json)
				if(data.code == '0'){
					reloadJqGridLabel();
					//reloadJqGrid('search');
					messageAlert("更新成功");
				}
			}
		}) 
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
//选择的标签名称
var sntest1=function(){
	topshowLabelTree.show(function(obj){
	
		var lvl=obj.level;
		var labelName=obj.labelName;
		var labelCode=obj.labelCode;
		
		
		if(lvl=='1'){
			
			$("#labelName").val(labelName);
			queryLabelName=labelName;
		}else{
			//$("#labelId1").val(labelCode1);
			$("#labelName").val(labelName);
			queryLabelName=labelName;
		}
		
	})
}
//重新加载表格
var reloadJqGrid = function(flag) {
	if(flag == undefined) {
		//新增和修改的时候清空查询条件
		//topclear('gridSearch')
	}
	var page = $('#grid-table').getGridParam('page');
	$("#grid-table").jqGrid('setGridParam',{    
        postData : topgetObjByObj($("#gridSearch .searchField")),  
        page :page    
    }).trigger("reloadGrid"); 
}