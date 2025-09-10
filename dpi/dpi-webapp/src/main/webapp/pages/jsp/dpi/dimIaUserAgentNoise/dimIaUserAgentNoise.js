var time='';
$(function() {
	$('#grid-table').jqGrid({
		url : $.cxt + "/uanoise/noiseselectpagelist",		//获取数据的地址
		datatype : "json",			//从服务器端返回的数据类型 可选类型：xml，local，json，jsonnp，script，xmlstring，jsonstring，clientside
		mtype : "POST",			//ajax提交方式。POST或者GET，默认GET
		height : topjqGridHeight(),
		autowidth : true,
		colNames : [ 'ID', '噪音规则','操作人','操作时间',  '操作'],			//数组  列显示名称
		colModel : [ 
			  //常用到的属性：name 列显示的名称；index 传到服务器端用来排序用的列名称
			  {name : 'id',align : 'center', hidden : true},
		      {name : 'noiseKey',align : 'center'},
		      {name : 'author',align : 'center',index : 'author',editable : true},
			{name : 'opTime',align : 'center',index : 'opTime',editable : true},
		      {name : 'act',align : 'center', formatter : renderOperation}
		      ],
		      
		viewrecords : true,
		rownumbers: true,
		rowNum : 10,		//在grid上显示记录条数，这个参数是要被传递到后台
		rowList : [ 10, 20, 30 ],		//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
		pager : '#grid-pager',		
		caption : "噪音规则表",		//表格名称
		loadComplete : topjqGridLoadComplete
	});
	//查询按钮  jq方式
	$("#searchNoiseList").click(function(){
		reloadJqGrid("search");
	}); 
	//新建按钮   同上
	$("#createNoiseBtn").click(function(){
		showNoiseForm();
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
		showNoiseFormInsert('',uaKey);
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
	html += "<a  onclick=\"showNoiseForm('" + cell.id + "')\" href=\"#\">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;";
	html += "<a  onclick=\"deleteNoise('" + cell.id + "')\" href=\"#\">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;"
	return html;
}
//重新加载表格
var reloadJqGrid = function(flag) {
	if(flag == undefined) {
		//新增和修改的时候清空查询条件
		topclear('gridSearch')
	}
	var noiseKey=$('#noise_key').val().trim();
	var opTime=$('#opTime').val().trim();
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
        postData : {noiseKey:noiseKey,opTime:opTime,startTime:startTime,endTime:endTime,author:author},  
        page : page    
    }).trigger("reloadGrid"); 
}

//弹出窗口
var showNoiseForm = function(id,uaKey) {
	
	topwindow.showWindow({
		//窗口名称
		title : "噪音详细信息",
		//参数
		data : {id : id,uaKey:uaKey},
		//url
		url : $.cxt + "/uanoise/shownoiseform",		//action中的方法
		
		bottons : [{
			title : "确认",
			fun : function() {
				//校验，参数为表单父级id
				if(topcheckoutForm("showNoiseForm")){		//更改后T的id	
					$.ajax({
						url : $.cxt + "/uanoise/insertorupdatenoise", 
						data : topgetObjByObj($("#showNoiseForm .formField")),		//id.class
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
var showNoiseFormInsert = function(id,uaKey) {
	
	topwindow.showWindow({
		//窗口名称
		title : "噪音详细信息",
		//参数
		data : {id : id,uaKey:uaKey},
		//url
		url : $.cxt + "/uanoise/shownoiseform",		//action中的方法
		
		bottons : [{
			title : "确认",
			fun : function() {
				//校验，参数为表单父级id
				if(topcheckoutForm("showNoiseForm")){		//更改后T的id	
					
					var obj1=topgetObjByObj($("#showNoiseForm .formField"));
					obj1.uaKey=uaKey;
					obj1.ua=ua;
					obj1.dateId=dateId;
					$.ajax({
						url : $.cxt + "/uanoise/insertUaNoiseAndupdateUa", 
						data : obj1,		//id.class
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
//噪音规则删除
var deleteNoise = function(id) {
	topshowdialog("确认删除噪音规则？", function(){
		$.ajax({
			url : $.cxt + "/uanoise/deletenoisebyid",		//cxt 获取过的根目录地址
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

