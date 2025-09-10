var table="";


$(function() {
	 
	initDataGrid();
	
	$("#backto").click(function(){
		
         window.location.href=$.cxt + "/pages/jsp/dpi/operateCenter/operateCenter.jsp";
		
	});
	$("#status").chosen({
		disable_search : true
	}).change(function(option, checked){
		/*var flag=$("#status option:selected").val();
		if(flag=='2'){
			flag='';
		}*/
        
        reloadJqGrid1();
       
    });
	
});
//初始化DataGrid
function initDataGrid(){
	$('#grid-table').jqGrid({
		url : $.cxt + "/urlFuzzyRecognition/selectpagelist",
		datatype : "json",
		postData:{dateId:time},
		mtype : "POST",
		height : topjqGridHeight(),
		autowidth : true,
		colNames : [ 'id','序号','URL','人数','次数','流量(KB)','状态','操作'],
		colModel : [ 
			{name : 'id',align : 'center',index : 'id',editable : true,width:2,sortable : false,hidden:true},//
			{name : 'rowNum',align : 'center',index : 'rowNum',editable : true,width:2,sortable : false},
			{name : 'url',align : 'center',index : 'url',editable : true,hidden : false,width:8,sortable : false},
			{name : 'userTotal',align : 'center',index : 'userTotal',editable : true,width:3},
			{name : 'userCount',align : 'center',index : 'userCount',editable : true,width:3},
			{name : 'flow',align : 'center',index : 'flow',editable : true,width:3 ,formatter:parseFlow},
			{name : 'isCheck',align : 'center',index : 'isCheck',editable : true,width:2,formatter:parseStu,sortable : false},
			{name : 'act',align : 'center',index : 'act',editable : true ,formatter:renderOperation,width:2}
			
        ],
        sortname: 'userTotal', //默认排序以 userTotal 
        sortable:true,
        sortorder :"desc",
        viewrecords : true,
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		pager : '#grid-pager',
		loadComplete : topjqGridLoadComplete,
		caption : "待补充URL模糊规则列表"
	});	
}

//重新加载表格
function reloadJqGrid (flag) {
	var data="";
	if(flag!="全部"){
		data=flag;
	}
	console.log(flag);
	$("#grid-table").jqGrid('setGridParam',{
        postData : {tableName:data},
        page : 1
    }).trigger("reloadGrid");
}
function reloadJqGrid1() {
	var page = $('#grid-table').getGridParam('page');
	var flag=$("#status option:selected").val();
	if(flag=='2'){
		flag='';
	}
	$("#grid-table").jqGrid('setGridParam',{
		postData:{dateId:time,isCheck:flag},
        //page : 1
		page : page    //当前页
    }).trigger("reloadGrid");
}
/*//表名初始化
function initTableName () {
	$.ajax({
		"data":{},
		"url":$.cxt + "/auditRuleError/getTableName",
		"type":"POST",
		"dataType":"json",
		"success":function(data){
			var obj=JSON.parse(data);
			if(obj.code!="0"){
				return;
			}
			var list=obj.data;
			for(var i=0;i<list.length;i++){
				console.log(list[i].check_object);
				$("#tableName1").append($("<option></option>").val(list[i].check_object).html(list[i].check_object));
				
			}
			$("#tableName1").chosen({
				disable_search : true
			}).change(function(option, checked){
		    	table=$('.chosen-select option:selected').text();
		        reloadJqGrid(table);
		    });
			
			
		}
	});
}*/
//格式化最后一列
function renderOperation (cellvalue, options, cell) {
	var html = '';
	
	
	if(cell.isCheck==1){
		html += '<a id="audit"  href="#" title="稽核" style="color:#696969;">已稽核</a>';
		
	}else{
		html += '<a onclick="showPiForm(\''+cell.url+'\',\''+cell.id+'\')" href="#" title="编辑">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;';
		html += '<a onclick="showPiFormYuMing(\''+cell.url+'\',\''+cell.id+'\')" href="#" title="域名">域名</a>';
	}
	return html;
}
//弹出窗口 新增
function showPiForm(url,id) {
	/*var htmlrow="<input type='hidden' class='formField' name='rowId1' value="+rowId1+">"
	$("#upForm").append(htmlrow);*/
	topwindow.showWindow({
		//窗口名称
		title : "编辑",
		//参数
		data : {url:url},
		//url
		url : $.cxt + "/urlFuzzyRecognition/showForm",
		bottons : [{
			title : "确认",
			fun : function() {
				
				//校验，参数为表单父级id
				var parseRule=$("#parseRule").val();
				//var getIndex=$("#getIndex").val();
				var prodId=$("#prodid").val();
				var prodName=$("#prodName").val();
				var labelCode=$("#labelCode").val();
				var urlExample=$("#urlExample").val();
				if(!parseRule ||!prodName ||!labelCode||!urlExample){
					messageAlert("除产品ID外,其他数据不能为空");
					return false;
				}
				if(!prodId){
					var obj=topgetObjByObj($("#upForm .formField"));
					obj.id=id;
					obj.dateId=time;
					console.log(obj);
					if(topcheckoutForm("upForm")){
						$.ajax({
							url : $.cxt + "/urlFuzzyRecognition/insertIntoExportTable", 
							data : obj,
							//data : {parseRule:parseRule},
							type: "POST",
							success : function(data) {
								//重新加载表格
								reloadJqGrid1();
								//关闭窗口
								topwindow.removeWindow();
								//操作提示
								topshowAlertSuccessDiv();
							}
						});
					}
				}else{
					
					
					if(!prodName){
						return;
					}
					$.ajax({
						url : $.cxt + "/domainFuzzyRule/checkProdIdAndProdName",
						dataType : "json",
					 	type : "POST",
					 	data : {prodId:prodId,prodName:prodName},
					 	success : function(obj){
					 		var data=JSON.parse(obj);
					 		if(data.data=='1'){
					 			var obj1=topgetObjByObj($("#upForm .formField"));
								obj1.id=id;
								obj1.dateId=time;
					 			if(topcheckoutForm("upForm")){
								$.ajax({
									url : $.cxt + "/domainFuzzyRule/insertAndUpdateVo", 
									data : obj1,
									//data : {parseRule:parseRule},
									type: "POST",
									success : function(data) {
										var obj=JSON.parse(data);
										if(obj.data!='0'){
											messageAlert("此数据已经存在");
										}else{
											//重新加载表格
											reloadJqGrid1();
											//关闭窗口
											topwindow.removeWindow();
											//操作提示
											topshowAlertSuccessDiv();
										}
										
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
				
				
			}
		}]
	});
}
//弹出窗口 域名
function showPiFormYuMing(url,id) {
	
	topwindow.showWindow({
		//窗口名称
		title : "编辑",
		//参数
		data : {url:url},
		//url
		url : $.cxt + "/urlFuzzyRecognition/showPiFormYuMing",
		bottons : [{
			title : "确认",
			fun : function() {
				
				//校验，参数为表单父级id
				/*域名逻辑
				 * 当产品id为空时 ，作为新增的产品 ，不为空时插到域名表里面
				 * 
				 * */
				var prodId=$("#prodId").val();
				var prodName=$("#prodName").val();
				var domainCode=$("#domainCode").val();
				var labelCode=$("#labelCode").val();
				if(!prodName ||!domainCode ){
					messageAlert("数据不能为空");
					return false;
				}
				if(!prodId){
					if(!labelCode){
						messageAlert("请选择标签");
						return false;
					}
					var obj={};
					obj.id=id;
					obj.prodName=prodName;
					obj.parseRule=domainCode;
					obj.labelCode=labelCode;
					if(topcheckoutForm("upForm")){
						$.ajax({
							url : $.cxt + "/urlFuzzyRecognition/insertIntoExportTable", 
							data : obj,
							//data : {parseRule:parseRule},
							type: "POST",
							success : function(data) {
								//重新加载表格
								reloadJqGrid1();
								//关闭窗口
								topwindow.removeWindow();
								//操作提示
								topshowAlertSuccessDiv();
							}
						});
					}
				}else{
					
					
					if(!prodName){
						return;
					}
					$.ajax({
						url : $.cxt + "/domainFuzzyRule/checkProdIdAndProdName",
						dataType : "json",
					 	type : "POST",
					 	data : {prodId:prodId,prodName:prodName},
					 	success : function(obj){
					 		var data=JSON.parse(obj);
					 		if(data.data=='1'){
					 			
					 			var obj1={};
								obj1.id=id;
								obj1.prodName=prodName;
								obj1.parseRule=domainCode;
								obj1.prodid=prodId;
					 			if(topcheckoutForm("upForm")){
								$.ajax({
									url : $.cxt + "/domainFuzzyRule/insertOrNotDomainRule", 
									data : obj1,
									//data : {parseRule:parseRule},
									type: "POST",
									success : function(data) {
										var obj=JSON.parse(data);
										if(obj.data!='0'){
											messageAlert("此域名已经存在");
										}else{
											//重新加载表格
											reloadJqGrid1();
											//关闭窗口
											topwindow.removeWindow();
											//操作提示
											topshowAlertSuccessDiv();
										}
										
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
				
				
			}
		}]
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
function parseStu (cellvalue, options, cell) {
	
	return cellvalue==1 ? '已稽核':'未稽核' ;
}
function parseFlow (cellvalue, options, cell) {
	
	return (cellvalue/1024).toFixed(2);
}