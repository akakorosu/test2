$(function() {
	$('#grid-table').jqGrid({
		url : $.cxt + "/ipport/selectpagelist",		//获取数据的地址
		datatype : "json",			//从服务器端返回的数据类型 可选类型：xml，local，json，jsonnp，script，xmlstring，jsonstring，clientside
		mtype : "POST",			//ajax提交方式。POST或者GET，默认GET
		height : topjqGridHeight(), 
		autowidth : true,
		colNames : [ 'ID','IP端口', 'IP', '端口','产品ID','产品名称', '操作人', '操作时间', '操作'],			//数组  列显示名称, '操作'
		colModel : [ 
			  {name : 'id',align : 'center', hidden : true},
		      {name : 'ipPort',align : 'center'},			//常用到的属性：name 列显示的名称；index 传到服务器端用来排序用的列名称
		      {name : 'ip',align : 'center', hidden : true}, 
		      {name : 'port',align : 'center', hidden : true}, 
		      {name : 'prodid',align : 'center',hidden : true},
		      {name : 'prodName',align : 'center'},
		      
		      {name : 'author',align : 'center'}, 
		      {name : 'opTime',align : 'center'}, 
		      {name : 'act',align : 'center', formatter : renderOperation}
		      ],
			
		viewrecords : true,
		rownumbers: true,
		rowNum : 10,		//在grid上显示记录条数，这个参数是要被传递到后台
		rowList : [ 10, 20, 30 ],		//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
		pager : '#grid-pager',		
		caption : "Ip端口列表",		//表格名称
		loadComplete : topjqGridLoadComplete
	});
	//查询按钮  jq方式
	$("#searchList").click(function(){
		reloadJqGrid("search");
	}); 
	//新建按钮   同上
	$("#createBtn").click(function(){
		showForm();
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
})
//格式化最后一列
var renderOperation = function(cellvalue, options, cell) {
	var html = ""; 
	html += "<a  onclick=\"showForm('" + cell.id + "')\" href=\"#\">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;";
	html += "<a  onclick=\"deleteIpPort('" + cell.id + "')\" href=\"#\">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;"
	return html;
}
//重新加载表格
var reloadJqGrid = function(flag) {
	if(flag == undefined) {
		//新增和修改的时候清空查询条件
		topclear('gridSearch')
	}
	var ipPort=$("#ip_Port").val().trim();
	var prodName=$("#prod_Name").val().trim();
	var opTime=$("#opTime").val().trim();
	var author=$("#author").val().trim();
	var startTime='';
	var endTime='';
	if(opTime){
		var str=opTime.split("-");
		startTime=$.trim(str[0]);
		endTime=$.trim(str[1]);
	}else{
		startTime='';
		endTime='';
	}
	var page = $('#grid-table').getGridParam('page');
	$("#grid-table").jqGrid('setGridParam',{    
        postData :{prodName:prodName,ipPort:ipPort,opTime:opTime,author:author,startTime:startTime,endTime:endTime},  
        page : page    
    }).trigger("reloadGrid"); 
}

//弹出窗口
var showForm = function(id) {
	topwindow.showWindow({
		//窗口名称
		title : "IP端口详细信息",
		//参数
		data : {id : id},
		//url
		url : $.cxt + "/ipport/showform",		//action中的方法
		
		bottons : [{
			title : "确认",
			fun : function() {
				//校验，参数为表单父级id
				if(topcheckoutForm("showIpPortForm")){	
					$.ajax({
						url : $.cxt + "/ipport/insertorupdate", 
						data : topgetObjByObj($("#showIpPortForm .formField")),		//id.class
						type: "POST",
						async:false,
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

//删除关键字
var deleteIpPort = function(id) {
	topshowdialog("确认删除IP端口？", function(){
		$.ajax({
			url : $.cxt + "/ipport/delete",		//cxt 获取过的根目录地址
			data : {id : id},
			type: "POST",
			success : function(data) {		//返回html		
				//重新加载表格
				reloadJqGrid("search");
				//操作提示
				topshowAlertSuccessDiv();
			}
		})
	})
}

