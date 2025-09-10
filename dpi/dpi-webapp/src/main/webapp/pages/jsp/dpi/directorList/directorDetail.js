
$(function(){
	console.log(orgId);
	init();
	initDataGrid1();
	
})

function initDataGrid1(){
	$('#jqGrid').jqGrid({
		url : $.cxt + "/capability/selectpagelist",
		postData : {"foreignName":orgId},
		datatype : "json",
		mtype : "POST",
		autowidth : true,
		height : topjqGridHeight(),
		colNames : [ '年份','作品','职务','作品类型' ],
		colModel : [ 
			{name : 'year',align : 'center',index : 'year',editable : true,hidden : false,width:40},
			{name : 'workName',align : 'center',index : 'workName',editable : true,width:40},
			{name : 'duty',align : 'center',index : 'duty',editable : true,width:40},
			{name : 'type',align : 'center',index : 'type',editable : true,width:30}
			
        ],
		viewrecords : true,
		rownumbers: true,
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		pager : '#grid-pager',
		loadComplete : topjqGridLoadComplete,
		//caption : "导演列表"
	});	
}
function init(){
	
	
	
	$.ajax({
		url :$.cxt + "/capability/getDireDetail", 
		data : {"foreignName":orgId},		//id.class   +
		dataType:"json",
		type: "GET",
		async:false,  //true（异步）或 false（同步)
		success : function(data) {
			   
			   var obj = JSON.parse(data);
			   console.log(obj);
			   var entity=obj.data;
			   
			   $("#anotherName").html(judgeNull(entity.anotherName));
			   $("#birth").html(judgeNull(entity.birth));
			   $("#bloodType").html(judgeNull(entity.bloodType));
			   $("#brithPalce").html(judgeNull(entity.brithPalce));
			   $("#chineseName").html(judgeNull(entity.chineseName));
			   
			   $("#constellation").html(judgeNull(entity.constellation));
			   $("#height").html(judgeNull(entity.height));
			   $("#job").html(judgeNull(entity.job));
			   $("#nation").html(judgeNull(entity.nation));
			   $("#nationality").html(judgeNull(entity.nationality));
			   $("#nativePlace").html(judgeNull(entity.nativePlace));
			   $("#weight").html(judgeNull(entity.weight));
			   $("#chinaName").html(judgeNull(entity.chineseName));
			   
			   
			   var str='<div class="imgDiv" ><img   height="121" width="100" src="'+$.cxt+'/pages/img/director/'+entity.foreignName+'.jpg" alt=""></div>';
			   
			   $(".infoDetail").prepend(str);
			  
			  
			 
		}
	});
	//行业标签
	
			
			   
              
		

}
function judgeNull(str){
	if(str=='null'||str==null||str==undefined){
		return '--';
	}else{
		return  str;
		
	}
	
}

