
$(function() {
	initDataGrid();
	$("#time1").datepicker({
        autoclose:true,
        format:"yyyymmdd"
    });
	$("#clear").click(function(){
		$("#time1").val('');
		$("#auditStatus option:first").prop("selected", 'selected').trigger("chosen:updated");  
		doSearch();
	});
	$("#search").click(function(){ 
		doSearch();
	});
    $("#backto").click(function(){
		
    	window.location.href=$.cxt + "/pages/jsp/dpi/operateCenter/operateCenter.jsp";
		
	})
	$("#auditStatus").chosen({
		disable_search : true
	}).change(function(option, checked){
    	
    });
});
//初始化DataGrid
function initDataGrid(){
	$('#grid-table').jqGrid({
		url : $.cxt + "/topLevelDomain/selectpagelist",
		postData:{monthId:time},
		datatype : "json",
		mtype : "POST",
		height : topjqGridHeight(),
		autowidth : true,
		colNames : [ '序号','域名','产品ID','产品名称','流量(M)','次数','用户数','平均流量(M)','状态','操作'],
		colModel : [ 
			{name : 'rowNums',align : 'center',index : 'rowNums',editable : true,sortable : false},
			{name : 'host',align : 'center',index : 'host',editable : true,hidden : false,sortable : false},
			{name : 'prodId',align : 'center',index : 'prodId',editable : true,hidden : false,sortable : false},
			{name : 'prodName',align : 'center',index : 'prodName',editable : true,sortable : false},
			{name : 'flow',align : 'center',index : 'flow',editable : true},
			{name : 'userCount',align : 'center',index : 'userCount',editable : true},
			{name : 'userTotal',align : 'center',index : 'userTotal',editable : true},
			{name : 'flowCount',align : 'center',index : 'flowCount',editable : true},
			{name : 'isCheck',align : 'center',index : 'isCheck',editable : true,formatter : parseStu,sortable : false},
			{name : 'act',align : 'center', formatter : renderOperation}
        ],
        sortname: 'userTotal',
        sortable:true,
        sortorder :"desc", 
		viewrecords : true,
		//rownumbers: true,
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		pager : '#grid-pager',
		loadComplete : topjqGridLoadComplete,
		caption : "一级域名识别列表"
	});	
}

//格式化最后一列
function renderOperation (cellvalue, options, cell) {
	var html = '';
	if(!cell.prodId || cell.isCheck=='2'){
		html += '<a  href="#" title="修改" style="color:#696969;">修改</a>';
		html += '<a  href="#" title="无需处理" style="color:#696969;">无需处理</a>';
	}else{
		html += '<a id="audit" onclick="showPiForm(\''+cell.host+'\')" href="#" title="修改">修改</a>&nbsp;&nbsp;';
		html += '<a id="audit" onclick="topLvlDomainCheck2(\''+cell.host+'\')" href="#" title="无需处理">无需处理</a>';
	}
//	var html = ''; 
//	html += '<a onclick="showPiForm(\''+cell.id+'\')" href="#" title="修改">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	return html;
}
function parseStu (cellvalue, options, cell) {
	var html='';
	if(cell.isCheck=='1'){
		html += '<a  href="#" title="已处理" style="color:#696969;">已处理</a>';
	}
	if(cell.isCheck=='2'){
		html += '<a  href="#" title="无需处理" style="color:#696969;">无需处理</a>';
	}
	if(cell.isCheck=='0'){
		html += '<a  href="#" title="未处理" style="color:#696969;">未处理</a>';
	}
    return html;
}
//重新加载表格
function reloadJqGrid () {
	var page = $('#grid-table').getGridParam('page');
	$("#grid-table").jqGrid('setGridParam',{
        page : page
    }).trigger("reloadGrid");
}
//弹出窗口(新增或修改)
function showPiForm(host) {
 
	topwindow.showWindow({
		//窗口名称
		title : "编辑",
		//参数
		data : {host : host,monthId:time},
		//url
		url : $.cxt + "/topLevelDomain/showForm",
		bottons : [{
			title : "确认",
			fun : function() {
				//校验，参数为表单父级id
				
				
				if(topcheckoutForm("upForm")){
					
					if($("#prodId").val()){
					$.ajax({
						url : $.cxt + "/topLevelDomain/updateTopDomain", 
						data : topgetObjByObj($("#piForm .formField")),
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
					}else{
						messageAlert("产品ID不能为空");
					}
				}
			}
		}]
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
function topLvlDomainCheck2(host) {
	if(!host){
		messageAlert("域名不能为空");
		return ;
	}
	$.ajax({
		url : $.cxt + "/topLevelDomain/topLvlDomainCheck2", 
		data : {host:host},
		type: "POST",
		success : function(data) {
			//重新加载表格
			reloadJqGrid();
			//关闭窗口
			//topwindow.removeWindow();
			//操作提示
			topshowAlertSuccessDiv();
		}
	});
}
function doSearch() {
	
	var time1=$("#time1").val();
	var stus=$("#auditStatus").val(); 
	if(time1==''){
		time1=time;//没选择时间  默认昨天的时间
	}
	if(stus=='3'){
		stus='';
	}
	$("#grid-table").jqGrid('setGridParam',{
		url:$.cxt+"/topLevelDomain/selectpagelist",
		datatype:"json",
		postData :{monthId:time1,isCheck:stus},
        page : 1
    }).trigger("reloadGrid");
}