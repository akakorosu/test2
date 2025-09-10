var time='';
$(function() {
	$('#time').val('');
	initDataGrid();
	
	//查询按钮
	$("#searchDrList").click(function(){
		if(!$('#time').val().trim()){
			time='';
		}
		reloadJqGrid("search");
	});
	$("#backto").click(function(){		
		window.location.href=$.cxt + "/pages/jsp/dpi/keywordSearchRule/keywordSearchRuleList.jsp";
		
	})
	$("#clearList").click(function(){
		time='';
		$('#time').val('');
		$('#id').val('');
		$('#url').val('');
	})
	 $("#time").datepicker({
	        autoclose:true,
	        format:"yyyymmdd"
	 }).on('changeDate', getTime); 
});

function getTime(ev){
	time=$('#time').val().trim();
}



//初始化DataGrid
function initDataGrid(){
	$('#grid-table').jqGrid({
		url : $.cxt + "/ruleCheck/selectpagelist",
		datatype : "json",
		mtype : "POST",
		height : topjqGridHeight(),
		autowidth : true,
		colNames : [ '规则ID','解析URL','解析结果','解析次数'],
		colModel : [ 
			{name : 'id',align : 'center',index : 'id',editable : true,hidden : false,sortable:true,width:30},
			{name : 'url',align : 'center',index : 'url',editable : true,sortable:true},
			{name : 'keyWord',align : 'center',index : 'keyWord',editable : true,sortable:false,width:60},
			{name : 'numTatal',align : 'center',index : 'numTatal',editable : true,sortable:false,width:30},
        ],
        sortable:true,
		viewrecords : true,
		rownumbers: true,
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		pager : '#grid-pager',
		loadComplete : topjqGridLoadComplete,
		caption : "规则校验",
	});	
}


//重新加载表格
function reloadJqGrid (flag) {
	if(flag == undefined) {
		//新增和修改的时候清空查询条件
		topclear('gridSearch')
	}
	var id=$('#id').val().trim();
	var url=$('#url').val().trim(); 
	$("#grid-table").jqGrid('setGridParam',{
        postData : {id:id,url:url,dateId:time},
        page : 1
    }).trigger("reloadGrid");
}
