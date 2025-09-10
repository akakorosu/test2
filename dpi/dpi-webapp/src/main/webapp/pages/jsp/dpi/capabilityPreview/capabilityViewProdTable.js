var monthId = '';
var cityId = '';
var fbFlag = '-1';
var publisbAuth = '';//发布权限

$(function() {
	$.ajax({
		"data":{},
		"url" : $.cxt + "/sysopt/getSysMonth", 
		"type":"POST",
		 async: false,
		"dataType":"json",
		"success":function(data){
			var obj = JSON.parse(data);
			monthId=obj.data;
		}
	});
	
	//初始化下拉框（区域）
	$.ajax({
		"url" : $.cxt + "/sysorg/getCityOption", 
		"type":"POST",
		 async: false,
		"dataType":"json",
		"success":function(data){
			var obj = JSON.parse(data);
			var list=obj.data;
			var html="";
			for(var i=0;i<list.length;i++){
				html+="<option value="+list[i].orgId+">"+list[i].name+"</option>"
			}
			$("#cityId").append(html);
			cityId=$("#cityId").val();
		}
	});
	
	//获取发布权限
	$.ajax({
		"data":{},
		"url" : $.cxt + "/capability/publishAuth", 
		"type":"POST",
		 async: false,
		"dataType":"json",
		"success":function(data){
			var obj = JSON.parse(data);
			publisbAuth = obj.code;
		}
	});
	
	//选择城市
	$("#cityId").chosen({
		disable_search : true,
	}).change(function(option, checked) {
		cityId=$("#cityId").val();
	});
	
	//选择时间
    $("#queryTime").datepicker({
        autoclose:true,
        startView: 1,
        maxViewMode: 1,
        minViewMode: 1,
        todayHighlight: true,
        format:"yyyymm",
        
    }).on('changeDate', getQueryTime);
    $("#queryTime").val(monthId);
	
	
    //////////////////////////////////////////////////////////////////
	initDataGrid();
	
	//查询按钮
	$("#searchPiList").click(function(){
		obj = {};
		obj.queryLabelLvl = queryLabelLvl;
		obj.queryLabelName = queryLabelName;
		obj.queryProdName = queryProdName;
		
		obj.cityId = $("#cityId").val();
		obj.dateId = $("#queryTime").val();
		obj.fbFlag = $("#fbFlag").val();
		console.log("obj :"+ obj);
		var page = $('#grid-table').getGridParam('page');
		$("#grid-table").jqGrid('setGridParam',{
	        postData : obj,
	        page : page
	    }).trigger("reloadGrid");
//		reloadJqGrid("search");
	});
	
	$("#fbFlag").chosen({
		disable_search : true
	}).change(function(option, checked){
		fbFlag = $("#fbFlag").val();
    });
});

//时间
function getQueryTime(ev){
	var year=ev.date.getFullYear().toString();
	var month=(ev.date.getMonth()+1)>9?(ev.date.getMonth()+1).toString():("0"+(ev.date.getMonth()+1).toString());
	monthId=year+""+month;
	$("#queryTime").val(monthId);
}

function getParam(paramObj) {
	var paramObj = {};
	paramObj.queryLabelLvl = queryLabelLvl;
	paramObj.queryLabelName = queryLabelName;
	paramObj.queryProdName = queryProdName;
	
	paramObj.cityId = cityId;
	paramObj.dateId = monthId;
	paramObj.fbFlag = fbFlag;
	return paramObj;
}

//初始化DataGrid
function initDataGrid(){
	var paramObj = getParam();
	$('#grid-table').jqGrid({
		url : $.cxt + "/capability/selectProductFbDescInfoList",
		datatype : "json",
		mtype : "POST",
		height : topjqGridHeight(),
		autowidth : true,
		colNames : [ '产品ID','产品名称','标签名称','标签描述','用户数','流量(MB)','操作' ],
		colModel : [ 
			{name : 'prodId',align : 'center',index : 'prodId',editable : true,hidden : false,width:30},
			{name : 'prodName',align : 'center',index : 'prodName',editable : true,width:40},
			// {name : 'prodLabelCode',align : 'center',index : 'prodLabelCode',editable : true,width:30},
			{name : 'joinLabelName',align : 'center',index : 'joinLabelName',editable : true,width:20},
			{name : 'labelDesc',align : 'center',index : 'labelDesc',editable : true,width:30},
			{name : 'userTotal',align : 'center',index : 'userTotal',editable : true,width:20, sortable: true},
			{name : 'flow',align : 'center',index : 'flow',editable : true,width:20, sortable: true},
			{name : 'fbFlag',align : 'center', formatter : renderOperation,width:50}
        ],
		viewrecords : true,
		rownumbers: true,
		rowNum : 10,
		sortname: 'userTotal',
		sortorder: 'desc',
		rowList : [ 10, 20, 30 ],
		postData : paramObj,
		pager : '#grid-pager',
		loadComplete : topjqGridLoadComplete,
		caption : "产品列表"
	});	
}

//格式化最后一列
function renderOperation (cellvalue, options, cell) {
	var html = '';
//	html += '<a onclick="deletePi(\''+cell.prodId+'\')" href="#" title="删除">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	if(publisbAuth == '1'){
		if(cell.fbFlag == '0'){
			html += '<a onclick="publishPi(\''+cell.prodId+'\''+','+'\''+cell.fbFlag+'\')" href="#" title="发布">发布</a>';
		}else if(cell.fbFlag == '1'){
			html += '<a onclick="publishPi(\''+cell.prodId+'\''+','+'\''+cell.fbFlag+'\')" href="#" title="取消发布">取消发布</a>';
		}
	}else {
		if(cell.fbFlag == '0'){
			html += '<p>未发布</p>';
		}else if(cell.fbFlag == '1'){
			html += '<p>已发布</p>';
		}
	}
	console.log(html);
	return html;
}

//重新加载表格
function reloadJqGrid (flag) {
//	var obj=topgetObjByObj($("#gridSearch .searchField"));
	var obj = getParam();
	var page = $('#grid-table').getGridParam('page');
	$("#grid-table").jqGrid('setGridParam',{
        postData : obj,
        page : page
    }).trigger("reloadGrid");
}

// 返回
function backButton(){
	window.location.href = $.cxt + "/capability/toCapabilityPreview";
}

//删除
function deletePi(prodId) {
	topshowdialog("确认删除？", function(){
		$.ajax({
			url : $.cxt + "/productInfo/deleteVoById",
			data : {prodId : prodId},
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

//发布
function publishPi(prodId,fbFlag) {
	
	topshowdialog("确认修改发布状态？", function(){
		$.ajax({
			url : $.cxt + "/capability/publishById",
			data : {prodId : prodId, fbFlag : fbFlag},
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