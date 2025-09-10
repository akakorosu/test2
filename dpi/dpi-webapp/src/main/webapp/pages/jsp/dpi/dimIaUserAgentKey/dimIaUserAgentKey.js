var time='';
$(function() {
	
	$('#grid-table').jqGrid({
		url : $.cxt + "/uakey/keyselectpagelist",		//获取数据的地址
		datatype : "json",			//从服务器端返回的数据类型 可选类型：xml，local，json，jsonnp，script，xmlstring，jsonstring，clientside
		mtype : "POST",			//ajax提交方式。POST或者GET，默认GET
		height : topjqGridHeight(),
		autowidth : true,
		colNames : [ 'ID', 'UA关键字', '产品ID','产品名称', '举例', '备注', '操作人', '操作时间', '操作'],			//数组  列显示名称, '操作'
		colModel : [ 
			  {name : 'id',align : 'center', hidden : true},
		      {name : 'uaKey',align : 'center'},			//常用到的属性：name 列显示的名称；index 传到服务器端用来排序用的列名称
		      {name : 'prodId',align : 'center', hidden : true}, 
		      {name : 'prodName',align : 'center'},
		      {name : 'uaExample',align : 'center'},		      
		      {name : 'comments',align : 'center'}, 
		      {name : 'author',align : 'center'}, 
		      {name : 'opTime',align : 'center'}, 
		      {name : 'act',align : 'center', formatter : renderOperation}
		      ],
			
		viewrecords : true,
		rownumbers: true,
		rowNum : 10,		//在grid上显示记录条数，这个参数是要被传递到后台
		rowList : [ 10, 20, 30 ],		//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
		pager : '#grid-pager',		
		caption : "UA关键字列表",		//表格名称
		loadComplete : topjqGridLoadComplete
	});
	//查询按钮  jq方式
	$("#searchKeyList").click(function(){
		reloadJqGrid("search");
	}); 
	//新建按钮   同上
	$("#createKeyBtn").click(function(){
		showKeyForm();
	})
	$.ajax({
		"data":{},
		"url" : $.cxt + "/operationsCenter/getOperationsCenterTime", 
		"type":"POST",
		 async: false,
		"dataType":"json",
		"success":function(data){
			var obj = JSON.parse(data);
			time=obj.data;
			
		}
	});
	$("#backtoye").click(function(){
		window.location.href=$.cxt + "/pages/jsp/dpi/unidentifiedUa/unidentifiedUaList.jsp?time="+time;
	})
	
	if(dateId!=null&&dateId!=undefined&&dateId!='null'&&dateId!=''){
		
		showKeyForm1(uaKey);
		$("#backtoye").show();
	}else{
		$("#backtoye").hide();
	}
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
	html += "<a  onclick=\"showKeyForm('" + cell.id + "')\" href=\"#\">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;";
	html += "<a  onclick=\"deleteUaKey('" + cell.id + "')\" href=\"#\">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;"
	return html;
}
//重新加载表格
var reloadJqGrid = function(flag) {
	if(flag == undefined) {
		//新增和修改的时候清空查询条件
		topclear('gridSearch')
	}
	var prodName=$('#prod_name').val().trim();
	var uaKey=$('#uaKey1').val().trim();
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
        postData : {prodName:prodName,uaKey:uaKey,opTime:opTime,startTime:startTime,endTime:endTime,author:author},  
        page : page    
    }).trigger("reloadGrid"); 
}

//弹出窗口
var showKeyForm = function(id) {
	topwindow.showWindow({
		//窗口名称
		title : "关键词详细信息",
		//参数
		data : {id : id},
		//url
		url : $.cxt + "/uakey/showKeyForm",		//action中的方法
		
		bottons : [{
			title : "确认",
			fun : function() {
				//校验，参数为表单父级id
				
				if(topcheckoutForm("showKeyForm")){		//更改后TestSysUserForm的id		
					$.ajax({
						url : $.cxt + "/uakey/insertorupdatekey", 
						data : topgetObjByObj($("#showKeyForm .formField")),		//id.class
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
//弹出窗口
function showKeyForm1(uaKey) {
	topwindow.showWindow({
		//窗口名称
		title : "关键词详细信息",
		//参数
		data : {uaKey : uaKey},
		//url
		url : $.cxt + "/uakey/showKeyForm1",		//打开insert  页面
		
		bottons : [{
			title : "确认",
			fun : function() {
				//校验，参数为表单父级id
				var obj=topgetObjByObj($("#showKeyForm .formField"));
			    obj.ua=ua;
			    obj.dateId=time;
				if(topcheckoutForm("showKeyForm")){		//更改后TestSysUserForm的id		
					$.ajax({
						url : $.cxt + "/uakey/insertKeyAndUpdateUa", 
						data : obj,		//id.class
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
var deleteUaKey = function(id) {
	topshowdialog("确认删除关键字？", function(){
		$.ajax({
			url : $.cxt + "/uakey/deleteUaKey",		//cxt 获取过的根目录地址
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

