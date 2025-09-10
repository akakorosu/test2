$(function() {
	$.ajax({
		url : $.cxt + "/content/selectContentLabelName1",
		data:{},
		datatype:"json",
		type: "POST",
		success : function(data) {
			var data=JSON.parse(data);
			var html='<option disabled="disabled">分类名称</option><option selected disabled style="display: none;">全部</option>';
			data=data.data;
			for(var i in data){
				html+='<option>'+data[i]+'</option>';
			}
			$("#contentLabelName11").empty();
			$("#contentLabelName11").append(html);
		}
	})
	
	initDataGrid();	
	//查询按钮
	$("#searchUpList").click(function(){
		reloadJqGrid("search");
	});
	//新建按钮
	$("#createUpBtn").click(function(){
		showUpForm();
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
});
//初始化DataGrid
function initDataGrid(){
	$('#grid-table').jqGrid({
		url : $.cxt + "/content/selectpagelist",
		datatype : "json",
		mtype : "POST",
		height : topjqGridHeight(),
		autowidth : true,
		colNames : [ '标签编码','一级标签','二级标签','三级标签','四️级标签','五级标签','六级标签','标签级别','映射标签','操作' ],
		colModel : [
			{name : 'contentLabelCode',align : 'center',index : 'contentLabelCode',editable : true,hidden : false},
			{name : 'contentLabelName1',align : 'center',index : 'contentLabelName1',editable : true},
			{name : 'contentLabelName2',align : 'center',index : 'contentLabelName2',editable : true},		
			{name : 'contentLabelName3',align : 'center',index : 'contentLabelName3',editable : true},
			{name : 'contentLabelName4',align : 'center',index : 'contentLabelName4',editable : true},
			{name : 'contentLabelName5',align : 'center',index : 'contentLabelName5',editable : true},
			{name : 'contentLabelName6',align : 'center',index : 'contentLabelName6',editable : true},
			{name : 'contentLabelLevel',align : 'center',index : 'contentLabelLevel',editable : true,hidden:true},
            {name : 'contentLabelNameRelevance',align : 'center',index : 'contentLabelNameRelevance',editable : true},
			{name : 'act',align : 'center', formatter : renderOperation}
        ],
		viewrecords : true,
		rownumbers: true,
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		pager : '#grid-pager',
		loadComplete : topjqGridLoadComplete,
		caption : "内容标签列表"
	});	
}

//格式化最后一列
function renderOperation (cellvalue, options, cell) {
	var html = ''; 
	html += '<a onclick="showUpForm(\''+cell.id+'\')" href="#" title="修改">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	html += '<a onclick="deleteUp(\''+cell.id+'\')" href="#" title="删除">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	html += '<a onclick="viewUp(\''+cell.id+'\')" href="#" title="查看"  >查看</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	return html;
}

//重新加载表格
function reloadJqGrid (flag) {
	if(flag == undefined) {
		//新增和修改的时候清空查询条件
		topclear('gridSearch')


	}
	var name1=$("#contentLabelName11 option:selected").text();
	if(name1=='全部'){
		name1='';
	}
	var label=$("#label").val().trim();
	var opTime=$("#opTime").val().trim();
	var startTime='';
	var endTime='';
	if(opTime){
		var str=opTime.split("-");
		startTime=$.trim(str[0]);
		endTime=$.trim(str[1]);
	}
	var author=$("#author").val().trim();
	$("#grid-table").jqGrid('setGridParam',{
        postData : {contentLabelName1:name1,label:label,startTime:startTime,endTime:endTime,author:author},
        page : 1
    }).trigger("reloadGrid");
}


//弹出窗口(新增或修改)
function showUpForm(id) {
	topwindow.showWindow({
		//窗口名称
		title : "编辑",
		//参数
		data : {id : id},
		//url
		url : $.cxt + "/content/showForm",
		bottons : [{
			title : "确认",
			fun : function() {
				var contentLabelName1='';
				var contentLabelName2='';
				var contentLabelName3='';
				var contentLabelName4='';
				var contentLabelName5='';
				var contentLabelName6='';
				var contentLabelLevel=$('#contentLabelLevel').val();
				if(contentLabelLevel=='0'){
					return;
				}
				if(contentLabelLevel==1){
					if(!$('#id')){
						$('#contentLabelName1').removeAttr("name");
					}
				}
				if(contentLabelLevel==2){
					$('#contentLabelName1_1').removeAttr("name");
				}
				if(contentLabelLevel==3){
					$('#contentLabelName1_1').removeAttr("name");
					$('#contentLabelName2_1').removeAttr("name");
				}
				if(contentLabelLevel==4){
					$('#contentLabelName1_1').removeAttr("name");
					$('#contentLabelName2_1').removeAttr("name");
					$('#contentLabelName3_1').removeAttr("name");
				}
				if(contentLabelLevel==5){
					$('#contentLabelName1_1').removeAttr("name");
					$('#contentLabelName2_1').removeAttr("name");
					$('#contentLabelName3_1').removeAttr("name");
					$('#contentLabelName4_1').removeAttr("name");
				}
				if(contentLabelLevel==6){
					$('#contentLabelName1_1').removeAttr("name");
					$('#contentLabelName2_1').removeAttr("name");
					$('#contentLabelName3_1').removeAttr("name");
					$('#contentLabelName4_1').removeAttr("name");
					$('#contentLabelName5_1').removeAttr("name");
				}
				if(topcheckoutForm("upForm")){
					$.ajax({
						url : $.cxt + "/content/insertOrUpdate", 
						data : topgetObjByObj($("#upForm .formField")),
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
function deleteUp(id) {
	var rowData = $("#grid-table").jqGrid('getRowData',id);
	var contentLabelLevel=rowData.contentLabelLevel;
	var contentLabelName1='';
	var contentLabelName2='';
	var contentLabelName3='';
	var contentLabelName4='';
	var contentLabelName5='';
	var contentLabelName6='';
	if(contentLabelLevel==1){
		contentLabelName1=rowData.contentLabelName1;
	}
	if(contentLabelLevel==2){
		contentLabelName2=rowData.contentLabelName2;
//		contentLabelName1=rowData.contentLabelName1;
	}
	if(contentLabelLevel==3){
		contentLabelName3=rowData.contentLabelName3;
//		contentLabelName2=rowData.contentLabelName2;
	}
	if(contentLabelLevel==4){
		contentLabelName4=rowData.contentLabelName4;
//		contentLabelName3=rowData.contentLabelName3;
	}
	if(contentLabelLevel==5){
		contentLabelName5=rowData.contentLabelName5;
//		contentLabelName4=rowData.contentLabelName4;
	}
	if(contentLabelLevel==6){
		contentLabelName6=rowData.contentLabelName6;
//		contentLabelName5=rowData.contentLabelName5;
	}	
	topshowdialog("确认删除？", function(){
		$.ajax({
			url : $.cxt + "/content/deleteById",
			data : {contentLabelName1:contentLabelName1,contentLabelName2:contentLabelName2,contentLabelName3:contentLabelName3,contentLabelName4:contentLabelName4,contentLabelName5:contentLabelName5,contentLabelName6:contentLabelName6},
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
function viewUp(id) {
	
	topwindow.showWindow({
		//窗口名称
		title : "查看",
		//参数
		data : {id : id},
		//url
		url : $.cxt + "/content/viewById",
	});	
}

