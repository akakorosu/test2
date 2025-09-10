var gender;
var district=null;
var nowPage=1;
var all;
$(function(){
	
	init();
	
	/*
	* 初始化插件
	* @param  object:翻页容器对象
	* @param  function:回调函数
	* */
	Pagination.init($(".ht-page"), pageChange);
	
	Pagination.Page($(".ht-page"), nowPage-1, all, 20);
})

function init(){
	//总数
	
	var pageSize=$(".inputBox").text();
	/*if(nowPage==null&&nowPage==undefined){
		nowPage=1;
	}*/
	
	
	$.ajax({
		url :$.cxt + "/capability/initDirector", 
		data : {"nowPage":nowPage,"pageSize":pageSize,"gender":gender,"district":district},		//id.class   +
		dataType:"json",
		type: "GET",
		async:false,  //true（异步）或 false（同步)
		success : function(data) {
			   
			   var obj = JSON.parse(data);
			   console.log(obj)
			   all=obj.msg;
			   var list =obj.data;
			   var str='';
			   for(var i=0;i<list.length;i++){
				 
				  str+='<li class="listLi" style="width:8.2%;" >' ;
				  //var name=lengthJudge(list[i].chineseName,9);
				  str+=' <div class="img" onclick="linkToDetail(\''+list[i].foreignName+'\')" align="center" style="margin:auto;height=121px;"><img src="'+$.cxt+'/pages/img/director/'+list[i].foreignName+'.jpg" alt=""  height="121" width="100"></div>' ;
				  str+=' <div class="titleName"  style="font-size:11px;">'+list[i].chineseName+'</div>' ;
				  //str+=' <div class="titleName"  style="font-size:11px;">'+name+'</div>' ;
				  str+=' </li>' ;
				  
				   
			   }
			   $("#dirList").empty();
			   $("#dirList").append(str);
			  
			 
		}
	});
	//行业标签
	
			
			   
              
		

}


function lengthJudge(str,n){
	
		var len = str.length;
		var strTemp = '';
		if(len > n){
		strTemp = str.substring(0,n);
	    str = str.substring(n,len);
		return strTemp+'<br />'+lengthJudge(str,n);
		}else{
		return str;
		}
		}

function selectSix(six){
	gender=six;
	nowPage=1;
	init();
	Pagination.Page($(".ht-page"), 0, all, 20);
	
	
}
function selectDis(dis){
	district=dis;
	nowPage=1;
	init();
	Pagination.Page($(".ht-page"), 0, all, 20);
	
	
}
//分页
/*
* 定义回掉函数
* @param  number:跳转页
* */
var hisi=0;
function pageChange(i) {
	if(hisi<i){
		nowPage=i+1; 
	}else{
		nowPage=i+1;
	}
   //nowPage=i+1;       //为1 时代表第二页
  // debugger
   /*if(nowPage==0){
	   nowPage++;
   }*/
   
   //alert(nowPage);
   init();
   Pagination.Page($(".ht-page"), nowPage-1, all, 20);
}

function linkToDetail(forName){
	
	window.location.href=$.cxt + "/pages/jsp/dpi/directorList/directorDetail.jsp?orgId="+forName;
	
	
}

/*
* 首次调用
* @param  object:翻页容器对象
* @param  number:当前页
* @param  number:总页数
* @param  number:每页数据条数
* */
