var time='';//hyh 新增
$(function() {
	$('#grid-table').jqGrid({				
		url : $.cxt + "/uarule/ruleselectpagelist",		//获取数据的地址
		datatype : "json",			//从服务器端返回的数据类型 可选类型：xml，local，json，jsonnp，script，xmlstring，jsonstring，clientside
		mtype : "POST",			//ajax提交方式。POST或者GET，默认GET
		height : topjqGridHeight(),
		autowidth : true,
		colNames : ['ID', '产品ID','产品名称', '识别规则', '正则分组编号', '举例', '备注', '操作人', '操作时间','操作'],			//数组  列显示名称,    
		colModel : [ 
			  {name : 'id',align : 'center',hidden : true}, 
		      {name : 'prodId',align : 'center',hidden : true}, 
		      {name : 'prodName',align : 'center'},
		      {name : 'uaKeyRule',align : 'center'},
		      {name : 'getIndex',align : 'center'},
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
		caption : "UA规则列表",		//表格名称
		loadComplete : topjqGridLoadComplete
	});
	//查询按钮  jq方式
	$("#searchRuleList").click(function(){
		reloadJqGrid("search");
	});  
	//新建按钮   同上
	$("#createRuleButton").click(function(){
		showRuleForm();
	})
	//hyh 新增
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
	//hyh 新增
	$("#backtoye").click(function(){
		window.location.href=$.cxt + "/pages/jsp/dpi/unidentifiedUa/unidentifiedUaList.jsp?time="+time;
	})
	//hyh 新增
	/*if(uaKey!=null&&uaKey!=undefined&&uaKey!='null'&&uaKey!=''){
		showRuleFormInsert(uaKey);
		$("#backtoye").show();
	}else{
		$("#backtoye").hide();
	}*/
	if(dateId!=null&&dateId!=undefined&&dateId!='null'&&dateId!=''){
		showRuleFormInsert(uaKey);
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
	html += "<a  onclick=\"showRuleForm('" + cell.id + "')\" href=\"#\">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;";
	html += "<a  onclick=\"deleteRule('" + cell.id + "')\" href=\"#\">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;"
	return html;
}
//重新加载表格
var reloadJqGrid = function(flag) {
	if(flag == undefined) {
		//新增和修改的时候清空查询条件
		topclear('gridSearch')
	}
	var prodName=$("#prod_name").val().trim();
	var uaKeyRule=$("#ua_key_rule").val().trim();
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
        postData : {prodName:prodName,uaKeyRule:uaKeyRule,opTime:opTime,startTime:startTime,endTime:endTime,author:author},  
        page : page    
    }).trigger("reloadGrid"); 
}

//弹出窗口
var showRuleForm = function(id) {
	topwindow.showWindow({
		//窗口名称
		title : "UA规则详细信息",
		//参数
		data : {id : id},
		//url
		url : $.cxt + "/uarule/showruleform",		//action中的方法
		
		bottons : [{
			title : "确认",
			fun : function() {
				//校验，参数为表单父级id
				if(topcheckoutForm("showRuleForm")){		//更改后TestSysUserForm的id		
					$.ajax({
						url : $.cxt + "/uarule/insertorupdaterule", 
						data : topgetObjByObj($("#showRuleForm .formField")),		//id.class
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
var showRuleFormInsert = function(id) {
	
	topwindow.showWindow({
		//窗口名称
		title : "UA规则详细信息",
		//参数
		data : {id : id,ua:ua},   //id其实是uaKey
		//url
		url : $.cxt + "/uarule/showRuleFormInsert",		//action中的方法
		
		bottons : [{
			title : "确认",
			fun : function() {
				//校验，参数为表单父级id
				if(topcheckoutForm("showRuleForm")){		//更改后TestSysUserForm的id	
					
					var obj=topgetObjByObj($("#showRuleForm .formField"));
					obj.uaKey=uaKey;
					obj.ua=ua;
					obj.dateId=dateId;
					$.ajax({
						url : $.cxt + "/uarule/insertRuleAndUpdateUa", 
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
//删除规则
var deleteRule = function(id) {
	topshowdialog("确认删除规则？", function(){
		$.ajax({
			url : $.cxt + "/uarule/deleteRuleById",		//cxt 获取过的根目录地址
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