
$(function() {
	initDataGrid();
    $("#backto").click(function(){
		
    	window.location.href=$.cxt + "/pages/jsp/dpi/operateCenter/operateCenter.jsp";
		
	})
	$("#status").chosen({
		disable_search : true
	}).change(function(option, checked){
		/*var flag=$("#status option:selected").val();
		if(flag=='2'){
			flag='';
		}*/
        
        reloadJqGrid1();
       
    });
});
//初始化DataGrid
function initDataGrid(){
	
	$('#grid-table').jqGrid({
		url : $.cxt + "/unidentifiedUa/selectpagelist",
		postData:{monthId:time},
		datatype : "json",
		mtype : "POST",
		height : topjqGridHeight(),
		autowidth : true,
		colNames : [ '原始UA','截取UA','人数','次数','流量','操作'],
		colModel : [ 
			{name : 'ua',align : 'center',index : 'ua',hidden : false,sortable : false,width:5},
			{name : 'uaKey',align : 'center',index : 'uaKey',sortable : false,width:5},
			{name : 'userTotal',align : 'center',index : 'userTotal',editable : true,sortable : true,width:2},
			{name : 'userCount',align : 'center',index : 'userCount',editable : true,sortable : true,width:2},
			{name : 'flow',align : 'center',index : 'flow',editable : true,sortable : true,formatter : parseFlow,width:2},
			{name : 'isCheck',align : 'center', sortable : true,formatter : renderOperation,width:3}
        ],
        sortname: 'userTotal',
        sortable:true,
        sortorder :"desc",
		viewrecords : true,
		rownumbers: true,
		rowNum : 10,
		//sortable:true,
		rowList : [ 10, 20, 30 ],
		pager : '#grid-pager',
		loadComplete : topjqGridLoadComplete,
		caption : "待补充UA识别列表"
	});	
}

//格式化最后一列
function renderOperation (cellvalue, options, cell) {
	
	var html = ''; 
	if(cellvalue=='0'){
		html += '<a onclick="showUaKey(\''+cell.uaKey+'\',\''+cell.ua+'\',\''+time+'\')" href="#" title="UA关键字">UA关键字</a>&nbsp;&nbsp;&nbsp;&nbsp;';
		html += '<a onclick="showUa(\''+cell.uaKey+'\',\''+cell.ua+'\',\''+time+'\')" href="#" title="UA噪音">UA噪音</a>&nbsp;&nbsp;&nbsp;&nbsp;';
		html += '<a onclick="showRule(\''+cell.uaKey+'\',\''+cell.ua+'\',\''+time+'\')" href="#" title="UA规则">UA规则</a>&nbsp;&nbsp;&nbsp;&nbsp;'
	}else{
		html += '<a id="audit"  href="#" title="稽核" style="color:#696969;">已稽核</a>';
	}
	
	return html;
}
function showUaKey(uaKey,ua,time){
	window.location.href=$.cxt + "/unidentifiedUa/showUaKey?uaKey="+uaKey+"&ua="+ua+"&dateId="+time;
}
function showUa(uaKey,ua,time){
	window.location.href=$.cxt + "/unidentifiedUa/showUa?uaKey="+uaKey+"&ua="+ua+"&dateId="+time;
}
function showRule(uaKey,ua,time){
	window.location.href=$.cxt + "/unidentifiedUa/showRule?uaKey="+uaKey+"&ua="+ua+"&dateId="+time;
}
function parseFlow (cellvalue, options, cell) {
	
	return Number(cellvalue).toFixed(0);
}
function reloadJqGrid1() {
	//var page = $('#grid-table').getGridParam('page');
	var flag=$("#status option:selected").val();
	if(flag=='2'){
		flag='';
	}
	$("#grid-table").jqGrid('setGridParam',{
		postData:{monthId:time,isCheck:flag},
        page : 1
        //page : page    //当前页
    }).trigger("reloadGrid");
}