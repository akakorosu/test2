var title="";
var selectArr=[];

$(function() {
	if(typeId=="1"){
		title="抓取标题";
		var colNames=[ '序号','域名','title','产品ID','产品名称','稽核状态','标签名称','人数','次数','流量','平均流量(M)','操作'];
		var colModel=[ 
			{name : 'rowNums',align : 'center',index : 'rowNums',editable : true,width:1,sortable:false},
			{name : 'host',align : 'center',index : 'host',editable : true,hidden : false,width:3,sortable:false},
			{name : 'title',align : 'center',index : 'title',editable : true,width:5,sortable:false},
			{name : 'prodId',align : 'center',index : 'prodId',editable : true,width:3,sortable:false},
			{name : 'prodName',align : 'center',index : 'prodName',editable : true,width:3,sortable:false},
			{name : 'isCheck',align : 'center',index : 'isCheck',editable : true,width:1,hidden : true,sortable:false},
			{name : 'prodLabelName',align : 'center',index : 'prodLabelName',editable : true,width:3,sortable:false},
			{name : 'userTotal',align : 'center',index : 'userTotal',editable : true ,width:2},
			{name : 'userCount',align : 'center',index : 'userCount',editable : true ,width:2},
			{name : 'flow',align : 'center',index : 'flow',editable : true ,width:2},
			{name : 'flowCount',align : 'center',index : 'flowCount',editable : true ,width:2,formatter : zeroCheck},
			{name : 'act',align : 'center', formatter : renderOperation,width:2}
        ];
		initDataGrid(colNames,colModel);
	}else if(typeId=="2"){
		title="域名备案";
		var colNames=[ '序号','域名'/*,'title'*/,'产品ID','产品名称','稽核状态','标签名称','人数','次数','流量','平均流量(M)','操作'];
		var colModel=[ 
			{name : 'rowNums',align : 'center',index : 'rowNums',editable : true,width:1,sortable:false},
			{name : 'host',align : 'center',index : 'host',editable : true,hidden : false,width:3,sortable:false},
			/*{name : 'title',align : 'center',index : 'title',editable : true,width:5},*/
			{name : 'prodId',align : 'center',index : 'prodId',editable : true,width:3,sortable:false},
			{name : 'prodName',align : 'center',index : 'prodName',editable : true,width:3,sortable:false},
			{name : 'isCheck',align : 'center',index : 'isCheck',editable : true,width:1,hidden : true,sortable:false},
			{name : 'prodLabelName',align : 'center',index : 'prodLabelName',editable : true,width:3,sortable:false},
			{name : 'userTotal',align : 'center',index : 'userTotal',editable : true ,width:2},
			{name : 'userCount',align : 'center',index : 'userCount',editable : true ,width:2},
			{name : 'flow',align : 'center',index : 'flow',editable : true ,width:2},
			{name : 'flowCount',align : 'center',index : 'flowCount',editable : true ,width:2,formatter : zeroCheck},
			{name : 'act',align : 'center', formatter : renderOperation,width:2}
        ];
		initDataGrid(colNames,colModel);
	}else{
		
		$("#batch").hide();
		title="未自动识别域名";
		
		var colNames=[ '序号','域名'/*,'title'*/,'产品ID','产品名称','稽核状态','标签名称','人数','次数','流量','平均流量(M)','操作'];
		var colModel=[ 
			{name : 'rowNums',align : 'center',index : 'rowNums',editable : true,width:1,sortable:false},
			{name : 'host',align : 'center',index : 'host',editable : true,hidden : false,width:3,sortable:false},
			/*{name : 'title',align : 'center',index : 'title',editable : true,width:5},*/
			{name : 'prodId',align : 'center',index : 'prodId',editable : true,width:3,sortable:false},
			{name : 'prodName',align : 'center',index : 'prodName',editable : true,width:3,sortable:false},
			{name : 'isCheck',align : 'center',index : 'isCheck',editable : true,width:1,hidden : true,sortable:false},
			{name : 'prodLabelName',align : 'center',index : 'prodLabelName',editable : true,width:3,sortable:false},
			{name : 'userTotal',align : 'center',index : 'userTotal',editable : true ,width:2},
			{name : 'userCount',align : 'center',index : 'userCount',editable : true ,width:2},
			{name : 'flow',align : 'center',index : 'flow',editable : true ,width:2},
			{name : 'flowCount',align : 'center',index : 'flowCount',editable : true ,width:2,formatter : zeroCheck},
			{name : 'act',align : 'center', formatter : renderOperation,width:2}
        ];
		initDataGrid(colNames,colModel);
	}
	//initDataGrid();
	initBatch();

	$("#backto").click(function(){
			
		window.location.href=$.cxt + "/pages/jsp/dpi/operateCenter/operateCenter.jsp";
			
		})
		$("#status").chosen({
			disable_search : true
		}).change(function(option, checked){
			/*var flag=$("#status option:selected").val();
			if(flag=='2'){
				flag='';
			}*/
	        
			searchAgainOption();
	       
	    });
});
//初始化DataGrid
function initDataGrid(colNames,colModel){
	$('#grid-table').jqGrid({
		url : $.cxt + "/unidentifiedUrl/selectpagelist",
		postData:{monthId:time,typeId:typeId},
		datatype : "json",
		mtype : "POST",
		height : topjqGridHeight(),
		autowidth : true,
		colNames : colNames,
		colModel : colModel,
		sortname: 'userTotal',
		sortable:true,
		sortorder :"desc", 
        multiselect: true,
		viewrecords : true,
		rowNum : 10,
		rowList : [ 10,20, 30 ],
		pager : '#grid-pager',
		loadComplete : topjqGridLoadComplete,
		caption : title,
		gridComplete:function() {
		    var _this = this;
		    var rowData = $(_this).jqGrid('getRowData');
		    for(var i =0,n=rowData.length;i<n;i++){
		    	var obj = rowData[i];
		    	// var rowData = $("#idx_table").jqGrid('getRowData',rowId);
		    	if(selectArr[obj.host]){
		    		 $(_this).jqGrid('setSelection',i+1,false);
		    	}
		    }
		},
		// 这里和beforeSelectRow()俩个函数，一起组合让jqGrid表格单选，
		onSelectRow:function(rowid,state, e) {
			var obj = $(this).jqGrid('getRowData',rowid);
		    if(state){
		    	selectArr[obj.host]=obj;
		    }else{
		    	if(selectArr[obj.host]){
		    		delete  selectArr[obj.host];
		    	} 
		    }
		    //selectRow(rowid,state);
		},
		onSelectAll:function(aRowids,status) {
            var _this = this;
            var rowData = $(_this).jqGrid('getRowData');
            for(var i=0,n=rowData.length;i<n;i++){
            	var obj = rowData[i];
            	if(status){
            		selectArr[obj.host]=obj;
	            }else{
	            	if(selectArr[obj.host]){
	            		delete  selectArr[obj.host];
			    	} 
	            }    
            }
                   
        },
	});		
}

//格式化最后一列
function renderOperation (cellvalue, options, cell) {
	var html = '';
	if('未自动识别域名'==title){
		if(cell.isCheck==1){
			html += '<a id="audit"  href="#" title="稽核" style="color:#696969;">已稽核</a>';
		}else{
			html += '<a onclick="showPiForm(\''+cell.host+'\')" href="#" title="修改">修改</a>';
		}
		
		return html;
	}
	
	if(cell.isCheck==1){
		//html += '<a onclick="showPiForm(\''+cell.host+'\')" href="#" title="修改">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;';
		html += '<a id="audit"  href="#" title="稽核" style="color:#696969;">已稽核</a>';
		
	}else{
		html += '<a onclick="showPiForm(\''+cell.host+'\')" href="#" title="修改">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;';
		html += '<a id="audit" onclick="audit(\''+cell.host+'\',\''+cell.prodId+'\',\''+cell.prodLabelName+'\')" href="#" title="稽核">稽核</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	}
	return html;
}
function zeroCheck (cellvalue, options, cell) {
	var v=cellvalue;
	if(v.substring(0,1)=='.'){
		cellvalue='0'+cellvalue;
	}
	return cellvalue;
}
//重新加载表格
function reloadJqGrid () {
	selectArr=[];
	
	var page = $('#grid-table').getGridParam('page');
	$("#grid-table").jqGrid('setGridParam',{
		postData : topgetObjByObj($("#gridSearch .searchField")),
        page : page
    }).trigger("reloadGrid");
}


//弹出窗口(新增或修改)
function showPiForm(host) {

	topwindow.showWindow({
		//窗口名称
		title : "编辑",
		//参数
		data : {host1 : host,time:time},
		//url
		url : $.cxt + "/unidentifiedUrl/showForm",
		bottons : [{
			title : "确认",
			fun : function() {
				//原来的一些初始数据
			
				//校验，参数为表单父级id
				
				var formProdId=$("#prodId").val();
				var formProdName=$("#prodName").val();
				var formProdLabel=$("#prodLabel").val();
				var formProdLabelName=$("#labelName").val();
				
				if(formProdName==null ||formProdName=='' ||formProdLabel==null ||formProdLabel=='' || formProdLabelName==null ||formProdLabelName==''){
					messageAlert("产品名称、标签id、标签名称都不能为空");
					return false;
				}
				if(formProdId!=null &&formProdName!=null&&formProdLabel!=null&& formProdLabelName!=null &&formProdId!='' &&formProdName!=''&&formProdLabel!=''&& formProdLabelName!=''){
					$.ajax({
						url : $.cxt + "/domainFuzzyRule/checkProdIdAndProdName",
						dataType : "json",
					 	type : "POST",
					 	data : {prodId:formProdId,prodName:formProdName},
					 	success : function(obj){
					 		var data=JSON.parse(obj);
					 		if(data.data=='1'){
					 			if(topcheckoutForm("upForm")){
								$.ajax({
									url : $.cxt + "/unidentifiedUrl/alterAndInsert", 
									data : topgetObjByObj($("#piForm .formField")),
									type: "POST",
									success : function(data) {
										//重新加载表格
										reloadJqGrid();
										//关闭窗口
										topwindow.removeWindow();
										//操作提示
										topshowAlertSuccessDiv();
									}
								});
							}
					 		}else{
					 			messageAlert("此产品不存在");
								return false;
					 		}
					 		
					 	}
					 });
					
				}
				if(formProdId==null || formProdId==''){
					
					console.log(topgetObjByObj($("#piForm .formField")));
					if(topcheckoutForm("upForm")){
						
						$.ajax({
							url : $.cxt + "/unidentifiedUrl/alterOnlyUpdate", 
							data : topgetObjByObj($("#piForm .formField")),
							type: "POST",
							success : function(data) {
								//重新加载表格
								reloadJqGrid();
								//关闭窗口
								topwindow.removeWindow();
								//操作提示
								topshowAlertSuccessDiv();
							}
						});
					}
				}
				/*if(topcheckoutForm("upForm")){
					$.ajax({
						url : $.cxt + "/unidentifiedUrl/insertOrUpdateVo", 
						data : topgetObjByObj($("#piForm .formField")),
						type: "POST",
						success : function(data) {
							//重新加载表格
							reloadJqGrid();
							//关闭窗口
							topwindow.removeWindow();
							//操作提示
							topshowAlertSuccessDiv();
						}
					});
				}*/
			}
		}]
	});
}

//稽核
function audit(host,prodid,prodLabelName) {
	//如果标签名称为空时，不让稽核

	if( prodLabelName==null||prodLabelName=='' || prodLabelName=='null'){
		messageAlert('标签名称为空,不能稽核');
		return;
	}
	if(prodid==null ||prodid==undefined||prodid=='' ||prodid=='null'){
		$.ajax({
		url : $.cxt + "/unidentifiedUrl/onlyUpdateCheck", 
		data:{host:host} ,
		type: "POST",
		success : function(data) {
			//重新加载表格
			reloadJqGrid();
			//操作提示
			topshowAlertSuccessDiv();
		}
	});
	}else{
		
		$.ajax({
		url : $.cxt + "/unidentifiedUrl/updateCheckAndInsert", 
		data:{host:host,prodId:prodid} ,
		type: "POST",
		success : function(data) {
			//重新加载表格
			reloadJqGrid();
			//操作提示
			topshowAlertSuccessDiv();
		}
	});
	}
	
}
function initBatch () {
	
	$("#batch").click( function () {
		var isEmpty = $.isEmptyObject(selectArr);
		
		if(isEmpty){
			messageAlert("至少选择一条数据");
			return false;
		}
		
		var emptyProdId='';
		var prodId='';
		var host='';
		
		
		for(var key in selectArr){
			
			var rowData = selectArr[key];
				if(rowData.prodLabelName==null || rowData.prodLabelName==undefined ||rowData.prodLabelName==''){
					messageAlert("标签名称为空的数据不能稽核");
					return false;
				}else{
					if(rowData.prodId==null || rowData.prodId==undefined || rowData.prodId==''){
						emptyProdId+=rowData.host+',,';
					}else{
						host+=rowData.host+',,';
						prodId+=rowData.prodId+',,';
					}
					
					
				}
				
		}
		emptyProdId=emptyProdId.substring(0,emptyProdId.length-2);
		prodId=prodId.substring(0,prodId.length-2);
		host=host.substring(0,host.length-2);
		$.ajax({
			url : $.cxt + "/unidentifiedUrl/batchCheckOrInsert",
			type: "POST",
			data:{
				emptyProdId:emptyProdId,
				prodId:prodId,
				host:host
			},
			async : false,
			success: function(json){
				var data  = JSON.parse(json)
				if(data.code == '0'){
					searchAgain();
					messageAlert("更新成功");
				}
			}
		}) 
		});
	
}
function parseStatus (cellvalue, options, cell) {
	
	return cellvalue==1 ?'已稽核' :'未稽核';
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
function searchAgain(){
	selectArr=[];
	var page = $('#grid-table').getGridParam('page');
	$("#grid-table").jqGrid("setGridParam",{
			url:$.cxt + "/unidentifiedUrl/selectpagelist",
			datatype:"json",
			postData : {monthId:time,typeId:typeId},
			 page : page
		}).trigger('reloadGrid');
}
function searchAgainOption(){
	selectArr=[];
	var flag=$("#status option:selected").val();
	if(flag=='2'){
		flag='';
	}
	var page = $('#grid-table').getGridParam('page');
	$("#grid-table").jqGrid("setGridParam",{
			url:$.cxt + "/unidentifiedUrl/selectpagelist",
			datatype:"json",
			postData : {monthId:time,typeId:typeId,isCheck:flag},
			 page : 1
		}).trigger('reloadGrid');
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
