//默认视频表
var tableName='dim_ia_content_label_video';
var codeLength='';
var prodName='';
var tableFlag='0' //0为映射结果表  1为编码映射（带map的表）
var status='';
$(function() {
	
	initDataGrid();
	getProdIdAndName(tableName,codeLength);//初始化prodName
    $("#backto").click(function(){
		
    	window.location.href=$.cxt + "/pages/jsp/dpi/operateCenter/operateCenter.jsp";
		
	})
	//初始化内容分类
	initContent();
	
    $("#bigTable").chosen({
		disable_search : true
	}).change(function(option, checked){
		var flag=$("#bigTable option:selected").val();
		if(flag=='0'){
			$("#addNew").hide();
			tableFlag='0';
			tableName='dim_ia_content_label_video';
		}
        if(flag=='1'){
           $("#addNew").show();
    	   tableFlag='1';
    	   //更改默认表
    	   tableName='dim_ia_map_content_label_video';
        }
        
        getProdIdAndName(tableName,codeLength);
        reloadJqGrid();
       
    });
    $("#labelLvl").chosen({
		disable_search : true
	}).change(function(option, checked){
    	codeLength=$("#labelLvl option:selected").val();
        if(codeLength=='0'){
        	codeLength='';
        }
        getProdIdAndName(tableName,codeLength);
    });
    $("#prodName").chosen({
		disable_search : true
	}).change(function(option, checked){
    	prodName=$("#prodName option:selected").val();
    	if(prodName=='0'){
    		prodName='';
    	}
    	
    });
    $("#status").chosen({
		disable_search : true
	}).change(function(option, checked){
		status=$("#status option:selected").val();
    	if(status=='2'){
    		status='';
    	}
    	
    });
    $("#clearList").click(function(){
    	$("#addNew").hide(1);
    	tableName='dim_ia_content_label_video';
    	codeLength='';
    	prodName='';
    	$("#bigTable option:first").prop("selected", 'selected').trigger("chosen:updated");
		//$("#auditStatus option[value='G0004']").prop("selected", 'selected').trigger("chosen:updated");
    	initContent();
		$("#labelLvl option:first").prop("selected", 'selected').trigger("chosen:updated"); 
		$("#status option:first").prop("selected", 'selected').trigger("chosen:updated"); 
		getProdIdAndName(tableName,codeLength); //清空后恢复开始的状态
		//$("#contentProdName option:first").prop("selected", 'selected').trigger("chosen:updated"); 
		status='';
		
		//alert(1);
		reloadJqGridClear();
		
	});
    $("#searchPiList").click(function(){
    	
    	reloadJqGrid();
		});
    $("#addNew").click(function(){
    	showPiFormInsert();
    	
	});
});
//初始化DataGrid
function initDataGrid(){
	
	$('#grid-table').jqGrid({
		url : $.cxt + "/substanceLabel/selectpagelist",
		postData:{tableName:tableName},
		datatype : "json",
		mtype : "POST",
		height : topjqGridHeight(),
		autowidth : true,
		colNames : [ '内容编码','序号','产品名称','产品id','内容分类','识别单一标签','爬取标签','映射标签','标签层级','一级标签','二级标签','三级标签','四级标签','五级标签','六级标签','状态','操作'],
		colModel : [ 
            {name : 'contentLabelCode',align : 'center',index : 'contentLabelCode',editable : true,sortable : true,width:8 ,hidden : true,sortable : false},
            {name : 'rowNum',align : 'center',index : 'rowNum',hidden : false,sortable : false,width:2,sortable : false},
			{name : 'prodName',align : 'center',index : 'prodName',hidden : false,sortable : false,width:3,sortable : false},
			{name : 'prodId',align : 'center',index : 'prodId',hidden : true,sortable : false,width:3,sortable : false},
			{name : 'catContentType',align : 'center',index : 'catContentType',sortable : false,width:3,sortable : false},
			{name : 'catContentName',align : 'center',index : 'catContentName',sortable : false,width:3,sortable : false},
			{name : 'catFullLabelName',align : 'center',index : 'catFullLabelName',editable : true,sortable : true,width:2,sortable : false},
			{name : 'contentLabelName',align : 'center',index : 'contentLabelName',editable : true,sortable : true,width:2,sortable : false},
			{name : 'contentLabelLevel',align : 'center',index : 'contentLabelLevel',editable : true,sortable : true,width:2},
			{name : 'contentLabelName1',align : 'center',index : 'contentLabelName1',editable : true,sortable : true,width:2,sortable : false},
			{name : 'contentLabelName2',align : 'center',index : 'contentLabelName2',editable : true,sortable : true,width:2,sortable : false},
			{name : 'contentLabelName3',align : 'center',index : 'contentLabelName3',editable : true,sortable : true,width:2,sortable : false},
			{name : 'contentLabelName4',align : 'center',index : 'contentLabelName4',editable : true,sortable : true,width:2,sortable : false},
			{name : 'contentLabelName5',align : 'center',index : 'contentLabelName5',editable : true,sortable : true,width:2,sortable : false},
			{name : 'contentLabelName6',align : 'center',index : 'contentLabelName6',editable : true,sortable : true,width:2,sortable : false},
			{name : 'isCheck',align : 'center',index : 'isCheck',editable : true,sortable : true,width:2,formatter : parseStatus,sortable : false},
			{name : 'act',align : 'center', sortable : true,formatter : renderOperation,width:3,sortable : false}
        ],
        sortname: 'contentLabelLevel',
        sortable:true,
        sortorder :"desc", 
		viewrecords : true,
		//rownumbers: true,
		rowNum : 10,
		//sortable:true,
		rowList : [ 10, 20, 30 ],
		pager : '#grid-pager',
		loadComplete : topjqGridLoadComplete,
		caption : "内容标签稽核列表"
	});	
}
function parseStatus (cellvalue, options, cell) {
	
	return cellvalue==1 ?'已稽核' :'未稽核';
}
function initContent () {
	
	$.ajax({
		url : $.cxt + "/substanceLabel/getGroupType", 
		data : {},
		type: "POST",
		success : function(data) {
			var obj = JSON.parse(data);
			var list= obj.data;
			var html='';
			for(var i=0;i<list.length;i++){
				if(list[i].groupType=='G0004'){
					html+="<option value="+list[i].groupType+" selected>视频</option>";
				}
				if(list[i].groupType=='G0014'){
					html+="<option value="+list[i].groupType+" >电商</option>";
				}
				if(list[i].groupType=='G0009'){
					html+="<option value="+list[i].groupType+" >小说</option>";
				}
				if(list[i].groupType=='G0012'){
					html+="<option value="+list[i].groupType+" >音乐</option>";
				}
			}
			$("#contentStatus").empty().trigger("chosen:updated");
			$("#contentStatus").append(html);
			$("#contentStatus").chosen({
				disable_search : true
			}).change(function(option, checked){
		    	var optionVal=$("#contentStatus option:selected").text();
		    	if(optionVal=='视频'&&tableFlag=='0'){
                   tableName='dim_ia_content_label_video';
                }
		    	if(optionVal=='电商'&&tableFlag=='0'){
	                   tableName='dim_ia_content_label_goods';
	            }
		    	if(optionVal=='小说'&&tableFlag=='0'){
	                   tableName='dim_ia_content_label_novel';
	            }
		    	if(optionVal=='音乐'&&tableFlag=='0'){
	                   tableName='dim_ia_content_label_music';
			    }
		    	if(optionVal=='视频'&&tableFlag=='1'){
	                   tableName='dim_ia_map_content_label_video';
	                }
			    if(optionVal=='电商'&&tableFlag=='1'){
		               tableName='dim_ia_map_content_label_goods';
		            }
			    if(optionVal=='小说'&&tableFlag=='1'){
		               tableName='dim_ia_map_content_label_novel';
		            }
			    if(optionVal=='音乐'&&tableFlag=='1'){
		               tableName='dim_ia_map_content_label_music';
				    }
		    	getProdIdAndName(tableName,codeLength);
		  }).trigger("chosen:updated");
		}
	});
}
//有前面的两个条件查询产品
function getProdIdAndName (tableName,codeLength) {
	
	$.ajax({
		url : $.cxt + "/substanceLabel/getProdIdAndName", 
		data : {tableName:tableName,codeLength:codeLength},
		type: "POST",
		success : function(data) {
			
			var obj = JSON.parse(data);
			var list= obj.data;
			var html='<option value="0">产品名称</option>';
			for(var i=0;i<list.length;i++){
				
				html+="<option value="+list[i].prodId+" >"+list[i].prodName+"</option>";
				
			}
			$("#prodName").empty();
			$("#prodName").append(html).trigger("chosen:updated");
			/*$("#prodName").chosen({
					disable_search : true
				}).change(function(option, checked){
			    	prodName=$("#prodName option:selected").val();
			    	if(prodName=='0'){
			    		prodName='';
			    	}
			       
			    });*/
			
		}
	});
	
	
}
function reloadJqGrid() {
	
    var page = $('#grid-table').getGridParam('page');
	$("#grid-table").jqGrid("clearGridData");
	$("#grid-table").jqGrid('setGridParam',{
        postData : {tableName:tableName,codeLength:codeLength,prodName:prodName,isCheck:status},
        page :page
    }).trigger("reloadGrid");
	
}
//清空后专用
function reloadJqGridClear() {
	
    //var page = $('#grid-table').getGridParam('page');
	$("#grid-table").jqGrid("clearGridData");
	$("#grid-table").jqGrid('setGridParam',{
        postData : {tableName:tableName,codeLength:codeLength,prodName:prodName,isCheck:status},
        page :1
    }).trigger("reloadGrid");
	
}
//格式化最后一列
function renderOperation (cellvalue, options, cell) {
	
	var html = ''; 
	if(tableFlag=='0'){
		$("#addNew").hide();
		/*html += '<a onclick="showPiForm(\''+cell.contentLabelCode+'\',\''+cell.catContentName+'\',\''+cell.prodId+'\')" href="#" title="编辑">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;';*/
		html += '<a onclick="checkContent(\''+cell.contentLabelCode+'\',\''+cell.catContentName+'\',\''+cell.prodId+'\')" href="#" title="稽核">稽核</a>';

	}else{
		$("#addNew").show();
		html += '<a onclick="showPiForm(\''+cell.contentLabelCode+'\',\''+cell.catContentName+'\',\''+cell.prodId+'\')" href="#" title="编辑">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;';
		html += '<a onclick="checkContent(\''+cell.contentLabelCode+'\',\''+cell.catContentName+'\',\''+cell.prodId+'\')" href="#" title="稽核">稽核</a>&nbsp;&nbsp;&nbsp;&nbsp;';
		html += '<a onclick="deleteContent(\''+cell.contentLabelCode+'\',\''+cell.catContentName+'\',\''+cell.prodId+'\')" href="#" title="删除">删除</a>';
	}
	
	return html;
}
function deleteContent (contentLabelCode,catContentName,prodId){
	
	$.ajax({
		url : $.cxt + "/substanceLabel/delContent", 
		data : {tableName:tableName,contentLabelCode:contentLabelCode,catContentName:catContentName,prodId:prodId},
		type: "POST",
		success : function(data) {
			
			var obj = JSON.parse(data);
			reloadJqGrid();
			//操作提示
			topshowAlertSuccessDiv();
			
		}
	});
	
}
function showPiForm(contentLabelCode,catContentName,prodId) {
	 
	topwindow.showWindow({
		//窗口名称
		title : "编辑",
		//参数
		data : {tableName : tableName,contentLabelCode : contentLabelCode,catContentName:catContentName,prodId:prodId},
		//url
		url : $.cxt + "/substanceLabel/showForm",
		bottons : [{
			title : "确认",
			fun : function() {
				//校验，参数为表单父级id
				
				
				if(topcheckoutForm("upForm")){
					
					if($("#contentLabelCode").val()){
					var contentLabelCode1= $("#contentLabelCode").val();	
					var contentLabelCodeOriginal= $("#contentLabelCodeOriginal").val();
					var obj={};
					obj.tableName=tableName;
					obj.contentLabelCode=contentLabelCode1;
					obj.catContentName=catContentName;
					obj.prodId=prodId;
					obj.contentLabelCodeOriginal=contentLabelCodeOriginal;
					$.ajax({
						url : $.cxt + "/substanceLabel/updateContentLabelCode", 
						data :obj ,
						type: "POST",
						success : function(data) {
							var obj = JSON.parse(data);
							if(obj.data!=0){
								messageAlert("主键冲突，请更换映射编码");
							}else{
								//重新加载表格
								reloadJqGrid();
								//关闭窗口
								topwindow.removeWindow();
								//操作提示
								topshowAlertSuccessDiv();
							}
							
						}
					});
					}else{
						messageAlert("映射编码不能为空");
					}
				}
			}
		}]
	});
}
function showPiFormInsert() {
	 
	topwindow.showWindow({
		//窗口名称
		title : "编辑",
		//参数
		data : {},
		//url
		url : $.cxt + "/substanceLabel/showPiFormInsert",
		bottons : [{
			title : "确认",
			fun : function() {
				//校验，参数为表单父级id
				
				
				if(topcheckoutForm("upForm")){
					
					if($("#contentLabelCodeInsert").val()){
					var contentLabelCodeInsert= $("#contentLabelCodeInsert").val();	
					var tableTypeInsert= $("#tableTypeInsert").find("option:selected").text();
					var tableName1='';
					if(tableTypeInsert=='视频'){
						tableName1='dim_ia_map_content_label_video';
		                }
				    if(tableTypeInsert=='电商'){
				    	tableName1='dim_ia_map_content_label_goods';
			            }
				    if(tableTypeInsert=='小说'){
				    	tableName1='dim_ia_map_content_label_novel';
			            }
				    if(tableTypeInsert=='音乐'){
				    	tableName1='dim_ia_map_content_label_music';
					    }
					var prodIdInsert= $("#prodIdInsert").val();
					var prodNameInsert= $("#prodNameInsert").find("option:selected").text();
					var catFullLabelNameInsert= $("#catFullLabelNameInsert").val();
					var catContentType= $("#catContentType option:selected").text();
					var obj={};
					obj.tableName=tableName1;
					obj.contentLabelCode=contentLabelCodeInsert;
					
					obj.prodId=prodIdInsert;
					obj.prodName=prodNameInsert;
					obj.catContentType=catContentType;
					obj.catContentName=catFullLabelNameInsert;
					$.ajax({
						url : $.cxt + "/substanceLabel/insertContentLabelCode", 
						data :obj ,
						type: "POST",
						success : function(data) {
							var obj = JSON.parse(data);
							if(obj.data!=0){
								messageAlert("主键冲突，请更换映射编码");
							}else{
								//重新加载表格
								reloadJqGrid();
								//关闭窗口
								topwindow.removeWindow();
								//操作提示
								topshowAlertSuccessDiv();
							}
							
						}
					});
					}else{
						messageAlert("映射编码不能为空");
					}
				}
			}
		}]
	});
}
function checkContent (contentLabelCode,catContentName,prodId){
	
	$.ajax({
		url : $.cxt + "/substanceLabel/checkContent", 
		data : {tableName:tableName,contentLabelCode:contentLabelCode,catContentName:catContentName,prodId:prodId},
		type: "POST",
		success : function(data) {
			
			var obj = JSON.parse(data);
			reloadJqGrid();
			//操作提示
			topshowAlertSuccessDiv();
			
		}
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