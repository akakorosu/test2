$(function() {
	$('#grid-table').jqGrid({
		url : $.cxt + "/dmdtarrulebase/selectpagelist",
		datatype : "json",
		mtype : "POST",
		height : topjqGridHeight(),
		autowidth : true,
		colNames : [ 'ID', '偏好标签名称','偏好标签类别','偏好标签分类','偏好规则', '操作' ],
		colModel : [ 
		      {name : 'id',align : 'center',hidden : true}, 
		      {name : 'tarBaseName',align : 'center', width:15},
		      {name : 'tarBaseClass',align : 'center', width:15}, 
		      {name : 'tarBaseTypeName',align : 'center', width:15},
		      {name : 'children',align : 'left',width:40, formatter : childrenOperation},
		      {name : 'act',align : 'center', width:15, formatter : renderOperation}],
		viewrecords : true,
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		pager : '#grid-pager',
		caption : "偏好规则表",
		loadComplete : topjqGridLoadComplete
	});
	//新建按钮
	$("#createBtn").click(function(){
		showForm();
	})
	//查询按钮
	$("#searchList").click(function(){
		reloadJqGrid("search");
	});
	// 查询keydown
	$("#tarName").keydown(function(){
		// 每次键盘事件清空
		$("#tarCode").val("");
		setTimeout(function(){
			getSelectTarType();
		},1000)
	});
	// 查询click
	$("#tarName").click(function(){
		getSelectTarType();
	});
})
var childrenOperation = function(cellvalue, options, cell) {
	var children = cell.children;
	var html = "";
	for(var i = 0; i < children.length; i ++) {
		var o = children[i];
		html += "<div style='margin:10px 0px 10px 0px'>"
			+ o.tarTypeText + " --- "
			+ o.tarName + " --- "
			+ o.tarQuotaText + " "
			+ o.tarSignText + " "
			+ o.tarValue
			+ "</div>";
	}
	return html;
}
//重新加载表格
var reloadJqGrid = function(flag) {
	if(flag == undefined) {
		//新增和修改的时候清空查询条件
		topclear('gridSearch')
	}
	$("#grid-table").jqGrid('setGridParam',{    
        postData : topgetObjByObj($("#gridSearch .searchField")),  
        page : 1    
    }).trigger("reloadGrid");
}
//格式化最后一列
var renderOperation = function(cellvalue, options, cell) {
	var html = ""; 
	html += "<a onclick=\"showForm('" + cell.id + "')\" href=\"#\">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;";
	html += "<a onclick=\"doDelete('" + cell.id + "')\" href=\"#\">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;";
	html += "<a onclick=\"showFormInfo('" + cell.id + "')\" href=\"#\">规则编辑</a>"
	return html;
}
var doDelete = function(id) {
	topshowdialog("确认删除？", function(){
		$.ajax({
			url : $.cxt + "/dmdtarrulebase/deletebyid", 
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
//弹出窗口
var showForm = function(id) {
	topwindow.showWindow({
		//窗口名称
		title : "偏好标签编辑",
		//参数
		data : {id : id},
		//url
		url : $.cxt + "/dmdtarrulebase/showform",
		bottons : [{
			title : "确认",
			fun : function() {
				if(topcheckoutForm("dmDtarRuleBaseForm")){
					$.ajax({
						url : $.cxt + "/dmdtarrulebase/insertorupdate", 
						data : topgetObjByObj($("#dmDtarRuleBaseForm .formField")),//json转为字符串
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
var showFormInfo = function(id) {
	var data =  {tarBaseId : id};
	topwindow.showWindow({
		height : 1000,
		width : 1000,
		//窗口名称
		title : "规则添加",
		//参数
		data : data,
		//url
		url : $.cxt + "/dmdtarruleinfo/showform",
		bottons : [{
			title : "确认",
			fun : function() {
				// 没有添加规则提示
				if($("[name=tarName]").length == 0) {
					topshowAlertErrorDiv("请添加至少一个偏好规则！")
					return;
				}
				// 拼装添加的规则
				var children = [];
				var tarNames = $("[name=tarName]");
				var tarCodes = $("[name=tarCode]");
				var tarTypes = $("[name=tarType]");
				var tarSigns = $("[name=tarSign]");
				var tarValues = $("[name=tarValue]");
				var tarQuotas = $("[name=tarQuota]");
				var tarTypeTexts = $("[name=tarTypeText]");
				var tarAndOrs = $("[name=tarAndOr]");
				for(var i = 0; i < tarNames.length; i ++) {
					var temp = {
						tarName : $(tarNames[i]).val(),
						tarCode : $(tarCodes[i]).val(),
						tarType : $(tarTypes[i]).val(),
						tarSign : $(tarSigns[i]).val(),
						tarValue : $(tarValues[i]).val(),
						tarQuota : $(tarQuotas[i]).val(),
						tarTypeText : $(tarTypeTexts[i]).val(),
						tarAndOr : $(tarAndOrs[i]).val()
					}
					children.push(temp);
				}
				data.listStr = JSON.stringify(children);
				$.ajax({
					url : $.cxt + "/dmdtarruleinfo/insert", 
					data : data,//json转为字符串
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
		}]
	});
}