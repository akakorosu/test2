var flag=true;
var time='';
$(function() {
	var today=new Date();
	var yesterday_milliseconds=today.getTime()-1000*60*60*24;
    var yesterday=new Date();      
    yesterday.setTime(yesterday_milliseconds);      
    var strYear=yesterday.getFullYear();   
    var strDay=yesterday.getDate();   
    var strMonth=yesterday.getMonth()+1;   
    if(strMonth<10)   
     {   
            strMonth="0"+strMonth;   
        }   
    time=strYear+""+strMonth+""+strDay;  
	initDataGrid();
	$("#time1").datepicker({
        autoclose:true,
        format:"yyyymmdd"
    }).on('changeDate', getTime1);
    $("#backto1").click(function(){
		 window.history.go(-1);
	})
	//查询按钮
	$("#searchPiList").click(function(){
		selectArr=[];
		
	});
	//清空按钮
	$("#clearList").click(function(){
		topclear('gridSearch');
		queryLabelName='';
		$("#auditStatus option:first").prop("selected", 'selected');
		$("#top option:first").prop("selected", 'selected');
	});
	
});
//初始化DataGrid
function initDataGrid(){
	$('#grid-table').jqGrid({
		//url : $.cxt + "/industryLabelAudit/selectpagelist",
		postData:{dateId:time},
		datatype : "json",
		mtype : "POST",
		height : topjqGridHeight(),
		autowidth : true,
		colNames : [ '序号','产品Id','产品名称','一级标签','二级标签','一级标签Code','二级标签Code','人数','次数','流量','稽核状态','操作'],
		colModel : [ 
			{name : 'rowNums',align : 'center',index : 'rowNums',editable : true},
			{name : 'prodId',align : 'center',index : 'prodId',editable : true ,hidden:true},
			{name : 'prodName',align : 'center',index : 'prodName',editable : true},
			{name : 'labelName1',align : 'center',index : 'labelName1',editable : true},
			{name : 'labelName2',align : 'center',index : 'labelName2',editable : true},
			{name : 'labelId1',align : 'center',index : 'labelId1',editable : true,hidden:true},
			{name : 'labelId2',align : 'center',index : 'labelId2',editable : true,hidden:true},
			{name : 'userTotal',align : 'center',index : 'userTotal',editable : true},
			{name : 'userCount',align : 'center',index : 'userCount',editable : true},
			{name : 'flow',align : 'center',index : 'flow',editable : true},
			{name : 'isCheck',align : 'center',index : 'isCheck',editable : true, formatter :parseStatus},
			{name : 'act',align : 'center', formatter : formatterAct}
        ],
		viewrecords : true,
		//rownumbers: true,
		multiselect: true,
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		pager : '#grid-pager',
		loadComplete : topjqGridLoadComplete,
		caption : "行业标签稽核列表",
		
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

function getTime1(ev){
	var year=ev.date.getFullYear().toString();
	var month=(ev.date.getMonth()+1)>9?(ev.date.getMonth()+1).toString():("0"+(ev.date.getMonth()+1).toString());
	var day=ev.date.getDate()>9?ev.date.getDate().toString():("0"+ev.date.getDate().toString());
	monthId=year+""+month+""+day;
	
}