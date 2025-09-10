$(function() {
	//加载表格
	$('#grid-table').jqGrid({
		url : $.cxt + "/domain/selectpagelist",
		datatype : "json",
		mtype : "POST",
		height : topjqGridHeight(),
		autowidth : true,
		colNames : [ '域名编码', '产品ID','操作人','操作时间','是否有效','累计访问次数','操作'],
		colModel : [ 
		      {name : 'domainCode',align : 'center'}, 
		      {name : 'prodId',align : 'center',formatter :setProductInfo}, 
		      {name : 'author',align : 'center'},
		      {name : 'opTime',align : 'center'},
		      {name : 'isValid',align : 'center',formatter :formatFreeFlag},
		      {name: 'totalCount',align : 'center'},
		      {name : 'operation',align : 'center', formatter : renderOperation}],
		viewrecords : true,
		rownumbers: true,
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		pager : '#grid-pager',
		caption : "域名列表",
		loadComplete : topjqGridLoadComplete
	});
	//查询按钮
	$("#searchOrgList").click(function(){
		reloadJqGrid("search");
	});
	//新建按钮
	$("#createApplyBtn").click(function(){
		showDomainRuleForm();
	})
})
var formatFreeFlag  = function (cellvalue, options, cell) {
	var result = "";
	if(cellvalue == '0'){
		result = "<select onchange=\"setIsValid(this,'" + cell.domainCode+ "')\">" +
				"<option value='0' selected='selected'>否</option>" +
				"<option value='1'>是</option>" +
				"</select>"	;
	}else{
		result = "<select onchange=\"setIsValid(this,'" + cell.domainCode+ "')\">" +
		"<option value='0'>否</option>" +
		"<option value='1' selected='selected'>是</option>" +
		"</select>"	;
	}
	return result;
}

var setIsValid = function (obj,domainCode){
	$.ajax({
		url : $.cxt + "/domain/update", 
		data :{"domainCode":domainCode,"value":obj.value,"name":"isValid"},
		type: "POST",
		success : function(data) {
			reloadJqGrid();
			topwindow.removeWindow();
			showMessage("修改成功",3000);
			
		}
	});
}

var setProductInfo  = function (cellvalue, options, cell) {
	return "<input type='text' value='"+cellvalue+"'/>"
}

//格式化最后一列
var renderOperation = function(cellvalue, options, cell) {
	var html = "<button title='查看' class='btn btn-xs grid-btn grids-btn' ><i class='ace-icon bigger-130 fa fa-search-plus blue' ></i></button>";
	html += "<button title='删除' class='btn btn-xs grid-btn grids-btn' onclick=\"deleteDoamainRule('" + cell.domainCode+ "')\" ><i class='ace-icon bigger-130 fa fa-trash-o red' ></i></a></button>";
	return html;
}
//重新加载表格
var reloadJqGrid = function(flag) {
	if(flag == undefined) {
		//清空查询条件
		topclear('gridSearch')
	}
	$("#grid-table").jqGrid('setGridParam',{    
        postData : topgetObjByObj($("#gridSearch .searchField")),  
        page : 1    
    }).trigger("reloadGrid"); 
}

//弹出窗口
var showDomainRuleForm = function() {
	var obj = {};
	obj.id = "域名";
	topwindow.showWindow({
		//窗口名称
		title : "新增域名信息",
		data:obj,
		//url
		url : $.cxt + "/domain/showforinsertdomainruleform",
		width:590,
		bottons : [{
			title : "确认",
			fun : function() {
				if(topcheckoutForm("domainRuleForm")){
					var domainRule = topgetObjByObj($("#domainRuleForm .formField"));
					var n = document.getElementsByName("domainCode");
					var domainCode = ""
					for(var i=0;i<n.length;i++){
						domainCode += n[i].value +",";
					}
					domainRule.domainCode = domainCode
					$.ajax({
						url : $.cxt + "/domain/insert", 
						data :domainRule,
						type: "POST",
						success : function(data) {
							//重新加载表格
							reloadJqGrid();
							//关闭窗口
							topwindow.removeWindow();
							//操作提示
							showMessage("添加成功",3000);
							
						}
					});
				}
			}
		}]
	});
	}

//删除
var deleteDoamainRule = function(domainCode) {
	topshowdialog("确认删除？", function(){
		$.ajax({
			url : $.cxt + '/domain/delete', 
			data : {domainCode : domainCode},
			type: "POST",
			success : function(data) {
				//重新加载表格
				reloadJqGrid();
				//操作提示
				showMessage("删除成功",3000);
			}
		})
	})
}
var showMessage = function(msg,time) {
	if($("#alertSuccessDiv").length > 0) {
		$("#alertSuccessDiv").remove();
	}
	var html = "" + 
	"<div class=\"alert alert-success fade in\" style=\"position: absolute;z-index:1111\" id=\"alertSuccessDiv\">" + 
		"<strong>&nbsp&nbsp"+msg+"&nbsp&nbsp!</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + 
	"</div>";
	$("body").append(html);
	topsetWindowCenter($("#alertSuccessDiv"));
	setTimeout(function(){
		$("#alertSuccessDiv").remove();
	},time); 
}