var monthId='';
$(function() {
	
	monthId=$("#time1").val();
	
	initTable();
});

function initTable(){
	//初始化DataGrid

	
	var city=$("#cityHidden").val();
	var labelName1=$("#labelName1Hidden").val();
	var labelName2=$("#labelName2Hidden").val();
		$('#grid-table').jqGrid({
			url : $.cxt + "/industryOverview/getMoreAppList",
			postData:{monthId:monthId,city:city,labelName1:labelName1,labelName2:labelName2},
			datatype : "json",
			mtype : "POST",
			height : '410px',
			autowidth : true,
			colNames : [ '序号','产品Id','产品名称'/*,'产品类别','一级标签','二级标签','一级标签Code','二级标签Code'*/,'人数','次数','流量(GB)'/*,'稽核状态'*//*,'操作'*/],
			colModel : [ 
				{name : 'rowNums',align : 'center',index : 'rowNums',editable : true},
				{name : 'prodId',align : 'center',index : 'prodId',editable : true ,hidden:true},
				{name : 'prodName',align : 'center',index : 'prodName',editable : true},
				/*{name : 'labelName',align : 'center',index : 'labelName',editable : true},*/
				/*{name : 'labelName1',align : 'center',index : 'labelName1',editable : true},
				{name : 'labelName2',align : 'center',index : 'labelName2',editable : true},
				{name : 'labelId1',align : 'center',index : 'labelId1',editable : true,hidden:true},
				{name : 'labelId2',align : 'center',index : 'labelId2',editable : true,hidden:true},*/
				{name : 'userTotal',align : 'center',index : 'userTotal',editable : true},
				{name : 'userCount',align : 'center',index : 'userCount',editable : true},
				{name : 'flow',align : 'center',index : 'flow',editable : true /*,formatter :parseStatus*/}
				/*{name : 'isCheck',align : 'center',index : 'isCheck',editable : true, formatter :parseStatus},*/
				/*{name : 'act',align : 'center', formatter : formatterAct}*/
	        ],
			viewrecords : true,
			//rownumbers: true,
			//multiselect: true,
			rowNum : 10,
			rowList : [ 10, 20, 30 ],
			pager : '#grid-pager',
			loadComplete : topjqGridLoadComplete,
			//caption : "app排名",
			
		});	
	
	
	
	
}

function parseStatus (cellvalue, options, cell) {
	
	return (cellvalue/1024).toFixed(2) ;
}


