
$(function() {
	initDataGrid();
	
	//查询按钮
	$("#searchKsrList").click(function(){
		reloadJqGrid("search");
	});
	//新建按钮
	$("#createKsrBtn").click(function(){
		showKsrForm();
	})
	$("#ruleCheck").click(function(){
		window.location.href=$.cxt+'/pages/jsp/dpi/ruleCheck/ruleCheck.jsp';
	})
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
	$("#exportProdType").chosen({
		disable_search : true
	}).change(function(option, checked){
		
		
    });
});


//初始化DataGrid
function initDataGrid(){
	$('#grid-table').jqGrid({
		url : $.cxt + "/keywordSearchRule/selectpagelist",
		datatype : "json",
		mtype : "POST",
		height : topjqGridHeight(),
		autowidth : true,
		colNames : [ 'NUM','域名','匹配类型','产品名称','动作目录名称','安卓截取规则','应用分组名称','操作人','操作时间','操作' ],
		colModel : [ 
            {name : 'num',align : 'center',index : 'num',editable : true,hidden : false,width:40},
			{name : 'host',align : 'center',index : 'host',editable : true,hidden : false},
			{name : 'matchType',align : 'center',index : 'matchType',editable : true,width:60},
//			{name : 'prodid',align : 'center',index : 'prodid',editable : true},
			{name : 'prodName',align : 'center',index : 'prodName',editable : true},
			{name : 'channelName',align : 'center',index : 'channelName',editable : true},
			{name : 'parseRuleAndriod',align : 'center',index : 'parseRuleAndriod',editable : true},
			{name : 'groupName',align : 'center',index : 'groupName',editable : true},
			{name : 'author',align : 'center',index : 'author',editable : true},
			{name : 'opTime',align : 'center',index : 'opTime',editable : true},
			{name : 'act',align : 'center', formatter : renderOperation}
        ],
		viewrecords : true,
		rownumbers: true,
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		pager : '#grid-pager',
		loadComplete : topjqGridLoadComplete,
		caption : "深度解析规则列表"
	});	
}

//格式化最后一列
function renderOperation (cellvalue, options, cell) {
	var html = ''; 
	html += '<a onclick="showKsrForm(\''+cell.id+'\')" href="#" title="修改">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	html += '<a onclick="deleteKsr(\''+cell.id+'\')" href="#" title="删除">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	html += '<a onclick="viewKsr(\''+cell.id+'\')" href="#" title="查看">查看</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	return html;
}

//重新加载表格
function reloadJqGrid (flag) {
	if(flag == undefined) {
		//新增和修改的时候清空查询条件
		topclear('gridSearch')
	}
	var obj={};
	
	obj.host=$("#host").val().trim();
	obj.num=$("#num").val().trim();
	obj.prodName=$("#prodName").val().trim();
	obj.channelName=$("#channelName").val().trim();
	obj.groupName=$("#groupName").val().trim();
	obj.author=$("#author").val().trim();
	
	var timeRange=$("#opTime").val().trim();
	if(timeRange){
		var str=timeRange.split("-");
		var startTime=$.trim(str[0]);
		var endTime=$.trim(str[1]);
		obj.startTime=startTime;
		obj.endTime=endTime;
	}else{
		obj.startTime="";
		obj.endTime="";
	}
	
	var batchFlag=$("#exportProdType").val();
	obj.batchFlag=batchFlag;
	
	
	$("#grid-table").jqGrid('setGridParam',{
        postData : obj,
        page : 1
    }).trigger("reloadGrid");
}


//弹出窗口(新增或修改)
function showKsrForm(id) {
	topwindow.showWindow({
		//窗口名称
		title : "编辑",
		//参数
		data : {id : id},
		//url
		url : $.cxt + "/keywordSearchRule/showForm",
		bottons : [{
			title : "确认",
			fun : function() {
				//校验，参数为表单父级id
				var obj=topgetObjByObj($("#ksrForm .formField"));
				obj.parseRuleAndriod=$("#parseRuleAndriod").val();
				
				if(topcheckoutForm("ksrForm")){
					$.ajax({
						url : $.cxt + "/keywordSearchRule/insertOrUpdateVo", 
						data : obj,
						type: "POST",
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

//删除
function deleteKsr(id) {
	topshowdialog("确认删除？", function(){
		$.ajax({
			url : $.cxt + "/keywordSearchRule/deleteVoById",
			data : {id : id},
			type: "POST",
			success : function(data) {
				//重新加载表格
				reloadJqGrid("search");
				//操作提示
				topshowAlertSuccessDiv();
			}
		})
	})
}

//查看
function viewKsr(id) {
	topwindow.showWindow({
		//窗口名称
		title : "查看",
		//参数
		data : {id : id},
		//url
		url : $.cxt + "/keywordSearchRule/viewVoById",
	});	
}
