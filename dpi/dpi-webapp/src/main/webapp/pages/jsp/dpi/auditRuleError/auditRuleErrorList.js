var table="";
$(function() {
	initDataGrid();
	initTableName();
	$("#backto").click(function(){
		
		window.location.href=$.cxt + "/pages/jsp/dpi/operateCenter/operateCenter.jsp";
		
	})
	/*$("#searchUpList").click(function(){
		
		reloadJqGrid();
		
	})*/
	
});
//初始化DataGrid
function initDataGrid(){
	$('#grid-table').jqGrid({
		url : $.cxt + "/auditRuleError/selectpagelist",
		datatype : "json",
		mtype : "POST",
		height : topjqGridHeight(),
		autowidth : true,
		colNames : [ '序号','产品ID','产品名称','稽核对象','稽核编码','稽核错误说明'],
		colModel : [ 
			{name : 'rowNums',align : 'center',index : 'rowNums',editable : true,width:5},
			{name : 'prodId',align : 'center',index : 'prodId',editable : true,hidden : false,width:5},
			{name : 'prodName',align : 'center',index : 'prodName',editable : true,width:10},
			{name : 'checkObject',align : 'center',index : 'checkObject',editable : true,width:10},
			{name : 'errorCode',align : 'center',index : 'errorCode',editable : true,width:10},
			{name : 'errorDesc',align : 'center',index : 'errorDesc',editable : true,width:20}
        ],
		viewrecords : true,
		//rownumbers: true,
		rowNum : 10,
		sortable:true,
		sortorder:'desc',
		rowList : [ 10, 20, 30 ],
		pager : '#grid-pager',
		loadComplete : topjqGridLoadComplete,
		caption : "规则库逻辑稽核错误列表"
	});	
}

//重新加载表格
function reloadJqGrid (table) {
	
	
	if(table=='全部'){
		table='';
	}
	$("#grid-table").jqGrid('setGridParam',{
        postData : {tableName:table},
        page : 1
    }).trigger("reloadGrid");
}
//表名初始化
function initTableName () {
	$.ajax({
		"data":{},
		"url":$.cxt + "/auditRuleError/getTableName",
		"type":"POST",
		"dataType":"json",
		"success":function(data){
			var obj=JSON.parse(data);
			if(obj.code!="0"){
				return;
			}
			var list=obj.data;
			for(var i=0;i<list.length;i++){
				//console.log(list[i].check_object);
				if(list[i].CHECK_OBJECT!=null&&list[i].CHECK_OBJECT!=undefined&&list[i].CHECK_OBJECT!=''&&list[i].CHECK_OBJECT!='null'){
					$("#tableName1").append($("<option></option>").html(list[i].CHECK_OBJECT));
				}
				if(list[i].check_object!=null&&list[i].check_object!=undefined&&list[i].check_object!=''&&list[i].check_object!='null'){
					$("#tableName1").append($("<option></option>").html(list[i].check_object));
				}
				
				
			}
			$("#tableName1").chosen({
				disable_search : true
			}).change(function(option, checked){
		    	table=$('.chosen-select option:selected').text();
		        reloadJqGrid(table);
		    });
			
			
		}
	});
}
function exportExcel(){
	var tableName=$("#tableName1 option:selected").text();
	//tableName=$.trim(tableName);
	if(tableName=='全部'){
		tableName='';
	}
	$.ajax({
		"data":{tableName:tableName},
		"url":$.cxt + "/auditRuleError/getExcelList",
		"type":"POST",
		"dataType":"json",
		"success":function(data){
			var obj=JSON.parse(data);
			if(obj.data>20000){
				messageAlert('Excel导出条数不能大于20000条');
				
				return false;
			}
			tableName=encodeURI(tableName);//进行两次转码
			tableName=encodeURI(tableName);
			window.location.href=$.cxt + "/auditRuleError/exportData?tableName="+tableName;
		}
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