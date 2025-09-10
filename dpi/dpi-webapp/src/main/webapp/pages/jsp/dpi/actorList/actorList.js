$(function(){
	
	 getPage(1);
	
	
	
})

//翻页
function getPage(pn){
  var dataCount=50;//总数据条数
  var pageSize=12;//每页显示条数
  var pageCount= Math.ceil(dataCount / pageSize);//总页数
  if(pn==0||pn>pageCount){
    return;
  }
  var ul=$(".listul");
  ul.empty();
  //console.log(pageCount+"..."+pn)
  paintPage(pageCount,pn);   //绘制页码
  var startPage = pageSize * (pn - 1);

  if (pageCount == 1) {     // 当只有一页时 
    for (var j = 0; j < dataCount; j++) {  
      var e="<li><a href=\"content.html\"><img src=\"images/lbt.jpg\"></a><p><span>跟着我一起摇摆"+j+"</span></p></li>";
      ul.append(e);    
    }
  }else {      // 当超过一页时 
    var e=""; 
    var endPage = pn<pageCount?pageSize * pn:dataCount;
    for (var j = startPage; j < endPage; j++) {  
      var e="<li><a href=\"content.html\"><img src=\"images/lbt.jpg\"></a><p><span>跟着我一起摇摆"+j+"</span></p></li>";
      ul.append(e);
    }
  }
}

//绘制页码
  function paintPage(number,currNum)  //number 总页数,currNum 当前页  
  {
    var pageUl=$(".fenye");
    pageUl.empty();
    var ulDetail="";

    if(number==1){
     ulDetail= "<li class=\"prev\"><a href=\"javascript:void(0)\">上一页</a></li>"+
     "<li class=\"numb choose\"><a href=\"javascript:getPage(1)\">1</a></li>"+
     "<li class=\"next\"><a href=\"javascript:void(0)\">下一页</a></li>";
   }else if(number==2){
     ulDetail= "<li class=\"prev\"><a href=\"javascript:getPage(1)\">上一页</a></li>"+
     "<li class=\"numb"+choosele(currNum,1)+"\"><a href=\"javascript:getPage(1)\">1</a></li>"+
     "<li class=\"numb"+choosele(currNum,2)+"\"><a href=\"javascript:getPage(2)\">2</a></li>"+
     "<li class=\"next\"><a href=\"javascript:getPage(2)\">下一页</a></li>";
   }else if(number==3){
     ulDetail= "<li class=\"prev\"><a href=\"javascript:getPage("+parseInt(currNum-1)+")\">上一页</a></li>"+
     "<li class=\"numb"+choosele(currNum,1)+"\"><a href=\"javascript:getPage(1)\">1</a></li>"+
     "<li class=\"numb"+choosele(currNum,2)+"\"><a href=\"javascript:getPage(2)\">2</a></li>"+
     "<li class=\"numb"+choosele(currNum,3)+"\"><a href=\"javascript:getPage(3)\">3</a></li>"+
     "<li class=\"next\"><a href=\"javascript:getPage("+parseInt(currNum+1)+")\">下一页</a></li>";
   }else if(number==currNum&&currNum>3){
     ulDetail= "<li class=\"prev\"><a href=\"javascript:getPage("+parseInt(currNum-1)+")\">上一页</a></li>"+
     "<li class=\"numb\"><a href=\"javascript:getPage("+parseInt(currNum-2)+")\">"+parseInt(currNum-2)+"</a></li>"+
     "<li class=\"numb\"><a href=\"javascript:getPage("+parseInt(currNum-1)+")\">"+parseInt(currNum-1)+"</a></li>"+
     "<li class=\"numb choose\"><a href=\"javascript:getPage("+currNum+")\">"+currNum+"</a></li>"+
     "<li class=\"next\"><a href=\"javascript:getPage("+currNum+")\">下一页</a></li>";
   }else if(currNum==1&&number>3){
     ulDetail= "<li class=\"prev\"><a href=\"javascript:void(0)\">上一页</a></li>"+
     "<li class=\"numb choose\"><a href=\"javascript:void(0)\">1</a></li>"+
     "<li class=\"numb\"><a href=\"javascript:getPage(2)\">2</a></li>"+
     "<li class=\"numb\"><a href=\"javascript:getPage(3)\">3</a></li>"+
     "<li class=\"next\"><a href=\"javascript:getPage(2)\">下一页</a></li>";
   }else{
     ulDetail= "<li class=\"prev\"><a href=\"javascript:getPage("+parseInt(currNum-1)+")\">上一页</a></li>"+
     "<li class=\"numb\"><a href=\"javascript:getPage("+parseInt(currNum-1)+")\">"+parseInt(currNum-1)+"</a></li>"+
     "<li class=\"numb choose\"><a href=\"javascript:getPage("+currNum+")\">"+currNum+"</a></li>"+
     "<li class=\"numb\"><a href=\"javascript:getPage("+parseInt(currNum+1)+")\">"+parseInt(currNum+1)+"</a></li>"+
     "<li class=\"next\"><a href=\"javascript:getPage("+parseInt(currNum+1)+")\">下一页</a></li>";
   }

   $(".fenye").append(ulDetail);

 }

 function choosele(num,cur){
  if(num==cur){
    return " choose";
  }else{
    return "";
  }
}

function init(){
	//总数
	$.ajax({
		url : $.cxt + "/capability/getLabelNum", 
		//data : topgetObjByObj($("#showSensiForm .formField")),		//id.class
		dataType:"json",
		type: "POST",
		async:true,  //true（异步）或 false（同步)
		success : function(data) {
			   
			   var obj = JSON.parse(data);
			   if(obj.data.allNum!=null&&obj.data.allNum!=undefined){
				   $("#countAllNum").text(obj.data.allNum);
	               $("#secondLabelNum").text(obj.data.twoNum);
	               $("#fristLabelNum").text(obj.data.allNum-obj.data.twoNum);
			   }
			   if(obj.data.ALLNUM!=null&&obj.data.ALLNUM!=undefined){
				   $("#countAllNum").text(obj.data.ALLNUM);
	               $("#secondLabelNum").text(obj.data.TWONUM);
	               $("#fristLabelNum").text(obj.data.ALLNUM-obj.data.TWONUM);
			   }
              
		}
	});
	//行业标签
	$.ajax({
		url : $.cxt + "/capability/getLabelLvl", 
		//data : topgetObjByObj($("#showSensiForm .formField")),		//id.class
		dataType:"json",
		type: "POST",
		async:true,  //true（异步）或 false（同步)
		success : function(data) {
			
			 var qita;
			 var obj = JSON.parse(data);
			 var map=obj.data;
			 var div=$("#professionBox");
			 //console.log(obj)
			 for(var key in map){   //大类

				 var digdiv=$('<div class="column"></div>');
				 var bid6='<div class="col-title">'+key+'</div>';
				 digdiv.append(bid6);
				 var list=map[key];
				 for(var i=0;i<list.length;i++){   //一级分类
					 var ul=$('<ul></ul>');
					 var aa=list[i].labelName1;
					 var code1=list[i].labelCode1;
					 var search1=list[i].prodSearch1;                      // <span  style="display: none">woaini</span>
                     var li='<li class="level" style="cursor:pointer;"  onclick="labelShowProd(\''+1+'\',\''+code1+'\')"><img src="'+$.cxt+'/pages/images/new/u2185.png" /><span  style="display: none">'+search1+'</span>'+aa+'</li>';
                     ul.append(li);
                     var li3l=list[i].labelName2;
                     var code2=list[i].labelCode2;
                     var search2=list[i].prodSearch2;
                     if(li3l!=null&&li3l!=undefined&&li3l!=''){
                    	 var bb=li3l.split(";");
                    	 var mm=code2.split(";");
                    	 var nn=search2.split("~~~");
                    	 var str="";
                    	 for(var q=0;q<bb.length;q++){
                    		 str+='<li style="cursor:pointer;" onclick="labelShowProd(\''+2+'\',\''+mm[q]+'\')"><span  style="display: none">'+nn[q]+'</span>'+bb[q]+'</li>'
                    	 }
                    	 ul.append(str);
                    	 
                     }
					 
                     digdiv.append(ul); 
				 }
				 if("其他"!=key){
					 div.append(digdiv);
				 }else{
					 qita=digdiv;
				 }
				 
				}
			 div.append(qita);

		
			 
		}
	});
	//内容库
	$.ajax({
		url : $.cxt + "/capability/getContentKu", 
		//data : topgetObjByObj($("#showSensiForm .formField")),		//id.class
		dataType:"json",
		type: "POST",
		async:true,  //true（异步）或 false（同步)
		success : function(data) {
			var obj = JSON.parse(data);
			var labellist=obj.data.LabelNum;
			var attrshipin=obj.data.attrshipin;
			var attrxiaoshuo=obj.data.attrxiaoshuo;
			var attrdianshang=obj.data.attrdianshang;
			var dianshang=obj.data.dianshang;
			var xiaoshuo=obj.data.xiaoshuo;
			var shipin=obj.data.shipin;
			
			for(var item in shipin){
				//<img src="<%=request.getContextPath()%>/pages/images/new/QQ.png" />
				var prodName=shipin[item].prodName;
				var fullName = pinyin.getFullChars(prodName);
			
				var st1='<img src="'+$.cxt+'/pages/images/new/'+fullName.trim()+'.png" title="'+prodName+'" onerror="imgError(this);"/>';
				//var st1='<img src="<%=request.getContextPath()%>/pages/images/new/'+fullName.trim()+'.png" title="'+prodName+'"/>';
				
				
				$("#shipinimgbox").append(st1);
			}
			for(var item in dianshang){
				var prodName=dianshang[item].prodName;
				var fullName = pinyin.getFullChars(prodName);
				//fullName=fullName.toLowerCase();
				var st1='<img src="'+$.cxt+'/pages/images/new/'+fullName+'.png" title="'+prodName+'" onerror="imgError(this);"/>';
				$("#dianshangimgbox").append(st1);
			}
			for(var item in xiaoshuo){
				var prodName=xiaoshuo[item].prodName;
				var fullName = pinyin.getFullChars(prodName);
				//fullName=fullName.toLowerCase();
				var st1='<img src="'+$.cxt+'/pages/images/new/'+fullName+'.png" title="'+prodName+'" onerror="imgError(this);"/>';
				$("#xiaoshuoimgbox").append(st1);
			}
			for(var item in attrshipin){
				//<a href="#">作者</a>
                if('导演'==attrshipin[item].value){
                	var st1='<a onclick="linkToArt()" >'+attrshipin[item].value+'</a>';
				}else{
					var st1='<a href="#">'+attrshipin[item].value+'</a>';
				}
			
				
				$("#shipinattr").append(st1);
			}
			for(var item in attrxiaoshuo){
				//<a href="#">作者</a>
				var st1='<a href="#">'+attrxiaoshuo[item].value+'</a>';
				$("#xiaoshuoattr").append(st1);
			}
			for(var item in attrdianshang){
				//<a href="#">作者</a>
				var st1='<a href="#">'+attrdianshang[item].value+'</a>';
				$("#dianshangattr").append(st1);
			}
			for(var i=0;i<labellist.length;i++){
				var liobj=labellist[i];
				var bigtype=liobj.contentLabelCode1;
				if(bigtype=="视频"){
					var str="";
					$("#shipinAll").text(liobj.allNum-1);
					//<label>一级标签 <span>30</span></label>
					if(liobj.oneNum!='0'){
						str+='<label>一级标签 <span>'+liobj.oneNum+'</span></label>'
					}
					if(liobj.twoNum!='0'){
						str+='<label>二级标签 <span>'+liobj.twoNum+'</span></label>'
					}
					if(liobj.threeNum!='0'){
						str+='<label>三级标签 <span>'+liobj.threeNum+'</span></label>'
					}
					if(liobj.fourNum!='0'){
						str+='<label>四级标签 <span>'+liobj.fourNum+'</span></label>'
					}
					if(liobj.fiveNum!='0'){
						str+='<label>五级标签 <span>'+liobj.fiveNum+'</span></label>'
					}
					$("#shipinlvl").append(str);
					
					
				}
				if(bigtype=="购物"){
					var str="";
					$("#dianshangAll").text(liobj.allNum-1);
					//<label>一级标签 <span>30</span></label>
					if(liobj.oneNum!='0'){
						str+='<label>一级标签 <span>'+liobj.oneNum+'</span></label>'
					}
					if(liobj.twoNum!='0'){
						str+='<label>二级标签 <span>'+liobj.twoNum+'</span></label>'
					}
					if(liobj.threeNum!='0'){
						str+='<label>三级标签 <span>'+liobj.threeNum+'</span></label>'
					}
					if(liobj.fourNum!='0'){
						str+='<label>四级标签 <span>'+liobj.fourNum+'</span></label>'
					}
					if(liobj.fiveNum!='0'){
						str+='<label>五级标签 <span>'+liobj.fiveNum+'</span></label>'
					}
					$("#dianshanglvl").append(str);
					
					
				}
				if(bigtype=="小说"){
					var str="";
					$("#xiaoshuoAll").text(liobj.allNum-1);
					//<label>一级标签 <span>30</span></label>
					if(liobj.oneNum!='0'){
						str+='<label>一级标签 <span>'+liobj.oneNum+'</span></label>'
					}
					if(liobj.twoNum!='0'){
						str+='<label>二级标签 <span>'+liobj.twoNum+'</span></label>'
					}
					if(liobj.threeNum!='0'){
						str+='<label>三级标签 <span>'+liobj.threeNum+'</span></label>'
					}
					if(liobj.fourNum!='0'){
						str+='<label>四级标签 <span>'+liobj.fourNum+'</span></label>'
					}
					if(liobj.fiveNum!='0'){
						str+='<label>五级标签 <span>'+liobj.fiveNum+'</span></label>'
					}
					$("#xiaoshuolvl").append(str);
					
					
				}
				
			}
			
			   
              
		}
	});
	//模型描述
	$.ajax({
		url : $.cxt + "/capability/getModelInfo", 
		//data : topgetObjByObj($("#showSensiForm .formField")),		//id.class
		dataType:"json",
		type: "POST",
		async:true,  //true（异步）或 false（同步)
		success : function(data) {
			
			   var obj = JSON.parse(data);
			   var map=obj.data;
			   for(var key in map){
					var list=map[key];
				    for(var i=0;i<list.length;i++){
				    	var entity=list[i];
				    	var lvlm2=entity.lvl2;
				    	if(lvlm2!=null&&lvlm2!=undefined){
				    		
//				    		<div class="profession">
//				    		<span class="protitle">职业：</span>
//				    		<div class="professionList">
//				    			<label>生活|服务业</label>&emsp;/&emsp;餐饮&emsp;/&emsp;家政保洁&emsp;/&emsp;安保&emsp;/&emsp;美容&emsp;/&emsp;美发&emsp;/&emsp;旅游&emsp;/&emsp;娱乐&emsp;/&emsp;休闲&emsp;/&emsp;保健按摩&emsp;/&emsp;运动健身
//				    		</div>
//				    		<div class="professionList">
//				    			<label>人力|行政|管理</label>&emsp;/&emsp;人事/行政/后勤&emsp;/&emsp;司机&emsp;/&emsp;高级管理
//				    		</div>
//				    		<div class="professionList">
//				    			<label>销售|客服|采购|淘宝</label>&emsp;/&emsp;销售&emsp;/&emsp;客服&emsp;/&emsp;贸易采购&emsp;/&emsp;超市/百货/零售&emsp;/&emsp;淘宝职位&emsp;/&emsp;房产中介
//				    		</div>
//				    		<div class="professionList">
//				    			<label>酒店</label>&emsp;/&emsp;酒店
//				    		</div>
//				    		<div class="professionList">
//				    			<label>市场|媒介|广告|设计</label>&emsp;/&emsp;市场/媒介/公关&emsp;/&emsp;广告/会展/咨询&emsp;/&emsp;美术/设计/创意
//				    		</div>
//				    	</div>
				    	}
				    }
				   
				   
					
				}
			   
              
		}
	});
	
	
	
	
}

function labelShowProd(flag,str){  //flag 为标签层级的标志,str 为标签名称
	   
	   topwindow.showWindow({
			//窗口名称
			title : "产品列表",
			//参数
			data : {"labelLvl":flag,"labelName":str},
			//url
			url : $.cxt + "/capability/showForm",
			bottons : [{
				title : "确认",
				fun : function() {
					topwindow.removeWindow();
					}
			}]
		});
}

function search() {
	var val = $("#profession").val();
	var liList = $("#professionBox li");
	var tagetArr=[];
	for(var i =0; i < liList.length; i++ ){
		if($(liList[i]).text().indexOf(val)>-1){
			tagetArr.push(liList[i]);
		}
	}
	if(tagetArr.length==0){
		alert("您查找的内容不存在！");
		return;
	}
	$("#professionBox li").removeClass("active");
	$(tagetArr[0]).addClass("active");
	$("#professionBox").scrollTop(0);
	$("#professionBox").scrollTop($(tagetArr[0]).offset().top-125);
	$("#searchLabelNum").text(tagetArr.length);
	$(".result").show();
	var index=0;
	changeSelect(tagetArr,index);
}
function changeSelect(str,index){
	$("#changePrev").click(function(){
		index-=1;
		if(index<0){
			index=str.length-1;
		}
		$("#professionBox li").removeClass("active");
		$(str[index]).addClass("active");
		$("#professionBox").scrollTop(0);
	    $("#professionBox").scrollTop($(str[index]).offset().top-125);
	});
	$("#changeNext").click(function(){
		index+=1;
		if(index>str.length-1){
			index=0;
		}
		$("#professionBox li").removeClass("active");
	    $(str[index]).addClass("active");
	    $("#professionBox").scrollTop(0);
	    $("#professionBox").scrollTop($(str[index]).offset().top-125);
	});
}
function imgError(image){ 
	// $(image).hide(); 
	 $(image).attr("src", $.cxt+"/pages/images/new/14.png"); 
	} 


function linkToArt(){
	
	window.location.href=$.cxt + "/pages/jsp/dpi/actorList/actorList.jsp";
	
	
}
