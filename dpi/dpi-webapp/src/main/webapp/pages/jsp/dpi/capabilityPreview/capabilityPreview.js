var searchFlag=2;//1 为产品    2 标签
var shiPin={};
var dianShang={};
var xiaoShuo={};
var labelFlag;
$(function(){
	$('[data-toggle="tooltip"]').tooltip({html : true });
	init();
	
	
	
	
})

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
					 var search1=list[i].prodSearch1;
					 // <span  style="display: none">woaini</span>
					 if(code1!=null&&code1!=''){
						 var li='<li class="level" style="cursor:pointer;" value="'+aa+'" onclick="labelShowProd(\''+1+'\',\''+code1+'\')"><img src="'+$.cxt+'/pages/images/new/u2185.png" /><span  style="display: none">'+search1+'</span>'+aa+'</li>';
	                     ul.append(li); 
					 }
                     
                     var li3l=list[i].labelName2;
                     var code2=list[i].labelCode2;
                     var search2=list[i].prodSearch2;
                     if(li3l!=null&&li3l!=undefined&&li3l!=''){
                    	 var bb=li3l.split(";");
                    	 var mm=code2.split(";");
                    	 var nn=search2.split("~~~");
                    	 var str="";
                    	 for(var q=0;q<bb.length;q++){
                    		 if(mm[q]!=null&&mm[q]!=''){
                    			 str+='<li style="cursor:pointer;" value="'+bb[q]+'" onclick="labelShowProd(\''+2+'\',\''+mm[q]+'\')"><span  style="display: none">'+nn[q]+'</span>'+bb[q]+'</li>'
                    		 }
                    		 
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
                	var st1='<a onclick="linkToArt()" style="cursor:pointer;">'+attrshipin[item].value+'</a>';
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
					/*if(liobj.oneNum!='0'){
						str+='<label style="cursor:pointer;" onclick="showLabel(\'shipinall\',\'1\')">一级 <span>'+liobj.oneNum+'</span></label>&nbsp; '
					}
					if(liobj.twoNum!='0'){
						str+='<label style="cursor:pointer;" onclick="showLabel(\'shipinall\',\'2\')">二级 <span>'+liobj.twoNum+'</span></label>&nbsp; '
					}
					if(liobj.threeNum!='0'){
						str+='<label style="cursor:pointer;" onclick="showLabel(\'shipinall\',\'3\')">三级 <span>'+liobj.threeNum+'</span></label>&nbsp; '
					}
					if(liobj.fourNum!='0'){
						str+='<label style="cursor:pointer;" onclick="showLabel(\'shipinall\',\'4\')">四级 <span>'+liobj.fourNum+'</span></label>&nbsp; '
					}
					if(liobj.fiveNum!='0'){
						str+='<label style="cursor:pointer;" onclick="showLabel(\'shipinall\',\'5\')">五级 <span>'+liobj.fiveNum+'</span></label>'
					}
					$("#shipinlvl").append(str);*/
					
					
				}
				if(bigtype=="购物"){
					var str="";
					$("#dianshangAll").text(liobj.allNum-1);
					//<label>一级标签 <span>30</span></label>
					/*if(liobj.oneNum!='0'){
						str+='<label style="cursor:pointer;" onclick="showLabel(\'dianshangall\',\'1\')">一级 <span>'+liobj.oneNum+'</span></label>&nbsp; '
					}
					if(liobj.twoNum!='0'){
						str+='<label style="cursor:pointer;" onclick="showLabel(\'dianshangall\',\'2\')">二级 <span>'+liobj.twoNum+'</span></label>&nbsp; '
					}
					if(liobj.threeNum!='0'){
						str+='<label style="cursor:pointer;" onclick="showLabel(\'dianshangall\',\'3\')">三级 <span>'+liobj.threeNum+'</span></label>&nbsp; '
					}
					if(liobj.fourNum!='0'){
						str+='<label style="cursor:pointer;" onclick="showLabel(\'dianshangall\',\'4\')">四级 <span>'+liobj.fourNum+'</span></label>&nbsp; '
					}
					if(liobj.fiveNum!='0'){
						str+='<label style="cursor:pointer;" onclick="showLabel(\'dianshangall\',\'5\')">五级 <span>'+liobj.fiveNum+'</span></label>'
					}
					$("#dianshanglvl").append(str);*/
					
					
				}
				if(bigtype=="小说"){
					var str="";
					$("#xiaoshuoAll").text(liobj.allNum-1);
					//<label>一级标签 <span>30</span></label>
					/*if(liobj.oneNum!='0'){
						str+='<label style="cursor:pointer;" onclick="showLabel(\'xiaoshuoall\',\'1\')">一级 <span>'+liobj.oneNum+'</span></label>&nbsp; '
					}
					if(liobj.twoNum!='0'){
						str+='<label style="cursor:pointer;" onclick="showLabel(\'xiaoshuoall\',\'2\')">二级 <span>'+liobj.twoNum+'</span></label>&nbsp; '
					}
					if(liobj.threeNum!='0'){
						str+='<label style="cursor:pointer;" onclick="showLabel(\'xiaoshuoall\',\'3\')">三级 <span>'+liobj.threeNum+'</span></label>&nbsp; '
					}
					if(liobj.fourNum!='0'){
						str+='<label style="cursor:pointer;" onclick="showLabel(\'xiaoshuoall\',\'4\')">四级 <span>'+liobj.fourNum+'</span></label>&nbsp; '
					}
					if(liobj.fiveNum!='0'){
						str+='<label style="cursor:pointer;" onclick="showLabel(\'xiaoshuoall\',\'5\')">五级 <span>'+liobj.fiveNum+'</span></label>'
					}
					$("#xiaoshuolvl").append(str);*/
					
					
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
			  //console.log(data)
			   var obj = JSON.parse(data);
			   var map=obj.data;
			   for(var key in map){
				   if(key=='child'){
					   var list=map[key];
					   str='<label>孩子年龄段：</label>&emsp;';
					   for(var i=0;i<list.length;i++){
						   var age=list[i].childSch;
						   var info=list[i].bak;
						   if(i!=list.length-1){
							   str+='<span data-toggle="tooltip" title="'+info+'">'+age+'</span>&emsp;/&emsp;';
						   }else{
							   str+='<span data-toggle="tooltip" title="'+info+'">'+age+'</span>';
						   }
						  
						   //<label>是否有车：</label>&emsp;<span data-toggle="tooltip" title="kkkkkk">有车</span>&emsp;/&emsp;<span data-toggle="tooltip" title="kkkkkk">有买车意向</span>&emsp;/&emsp;<span data-toggle="tooltip" title="aaaaaaaaaa">租车</span>
					   }
					   $("#child").append(str);
					   
				   }else{
						var list=map[key];
						str='<div class="professionList"><label>'+key+'</label>&emsp;';
					    for(var i=0;i<list.length;i++){
					    	var profName2=list[i].profName2;
							var info=list[i].bak;
					    	
					    	
					    	if(i!=list.length-1){
								   str+='<span data-toggle="tooltip" title="'+info+'">'+profName2+'</span>&emsp;/&emsp;';
							   }else{
								   str+='<span data-toggle="tooltip" title="'+info+'">'+profName2+'</span>';
							   }
					    }
					    str+='</div>';
					    $("#work").append(str);
				   }
				
				   
				   
					
				}
			   $('[data-toggle="tooltip"]').tooltip({html : true });   
              
		}
	});
	
	
	
	
}

function labelShowProd(flag,str){  //flag 为标签层级的标志,str 为标签名称
	if(searchFlag==2){
		window.location.href=$.cxt+"/capability/showTable?labelLvl="+encodeURI(encodeURI(flag))+"&labelName="+encodeURI(encodeURI(str));
	}
	if(searchFlag==1){
	    var prodName=$("#profession").val();
	    if(prodName==''||prodName==null){
	    	return;
	    }
	    window.location.href=$.cxt+"/capability/showTable?labelLvl="+encodeURI(encodeURI(flag))+"&labelName="+encodeURI(encodeURI(str)) + "&prodName=" + encodeURI(encodeURI(prodName));
	}
}

function labelShowProd_old(flag,str){  //flag 为标签层级的标志,str 为标签名称
	   if(searchFlag==2){
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
	   if(searchFlag==1){
		   var prodName=$("#profession").val();
		   if(prodName==''||prodName==null){
			   return;
		   }
		   topwindow.showWindow({
				//窗口名称
				title : "产品列表",
				//参数
				data : {"prodName":prodName,"labelLvl":flag,"labelName":str},
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
	   
}
function showLabel(bigType,lvl){  //flag 为标签层级的标志,str 为标签名称
           
	labelFlag=bigType;
	//总数
	if(shiPin!=null){
		
		$.ajax({
			url : $.cxt + "/capability/getInitLabel", 
			data : {"bigType":bigType},
			dataType:"json",
			type: "POST",
			async:false,  //true（异步）或 false（同步)
			success : function(data) {
				   
				   var obj = JSON.parse(data);
				   
				   shiPin=obj.data.shipin;
				   dianShang=obj.data.dianshang;
				   xiaoShuo=obj.data.xiaoshuo;
				   
	              
			}
		});
		
	}
	
	
	
	
	       
		   topwindow.showWindow({
				//窗口名称
				title : "产品列表",
				//参数
				data : {"labelLvl":lvl,"bigType":bigType},
				//url
				url : $.cxt + "/capability/showLabel",
				bottons : [{
					title : "确认",
					fun : function() {
						topwindow.removeWindow();
						}
				}]
			}); 
	   
	 
}
/*function search() {
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
}*/
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
function search() {
	searchFlag=2;
	var val = $("#profession").val();
	if(val==''||val==null){
		$(".result").hide();
		return;
	}
	var liList = $("#professionBox li");
	
	
	var tagetArr=[];
	for(var i =0; i < liList.length; i++ ){
		var aaaa= $(liList[i]).find("span").text();
		
		var bbb= $(liList[i]).text();
		bbb=bbb.replace(aaaa,"");//$(liList[i]).text()
		if(bbb.indexOf(val)>-1){
			tagetArr.push(liList[i]);
		}
	}
	if(tagetArr.length==0){
		//alert("您查找的内容不存在！");
		messageAlert("您查找的内容不存在！");
		$(".result").hide();
		return;
	}
	$("#professionBox li").removeClass("active");
	$(tagetArr[0]).addClass("active");
	$("#professionBox").scrollTop(0);
	$("#professionBox").scrollTop($(tagetArr[0]).offset().top-200);
	$("#searchLabelNum").text(tagetArr.length);
	$(".result").show();
	var index=0;
	changeSelect(tagetArr,index);
}
function searchProd() {
	searchFlag=1;
	var val = $("#profession").val();
	if(val==''||val==null){
		$(".result").hide();
		searchFlag=2;
		return;
	}
	var liList = $("#professionBox li");
	//var liList1 = $("#professionBox li span");
	var tagetArr=[];
	for(var i =0; i < liList.length; i++ ){
		if($(liList[i]).find("span").text().indexOf(val)>-1){
			tagetArr.push(liList[i]);
		}
	}
	if(tagetArr.length==0){
		//alert("您查找的内容不存在！");
		messageAlert("您查找的内容不存在！");
		$(".result").hide();
		return;
	}
	$("#professionBox li").removeClass("active");
	$(tagetArr[0]).addClass("active");
	$("#professionBox").scrollTop(0);
	$("#professionBox").scrollTop($(tagetArr[0]).offset().top-200);
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
	    $("#professionBox").scrollTop($(str[index]).offset().top-200);
	});
	$("#changeNext").click(function(){
		index+=1;
		if(index>str.length-1){
			index=0;
		}
		$("#professionBox li").removeClass("active");
	    $(str[index]).addClass("active");
	    $("#professionBox").scrollTop(0);
	    $("#professionBox").scrollTop($(str[index]).offset().top-200);
	});
}
function imgError(image){ 
	// $(image).hide(); 
	 $(image).attr("src", $.cxt+"/pages/images/new/14.png"); 
	} 


function linkToArt(){
	
	window.location.href=$.cxt + "/pages/jsp/dpi/directorList/director.jsp";
	
	
}
function update (){
	
	//总数
	$.ajax({
		url : $.cxt + "/capability/getAllAct", 
		//data : topgetObjByObj($("#showSensiForm .formField")),		//id.class
		dataType:"json",
		type: "POST",
		async:true,  //true（异步）或 false（同步)
		success : function(data) {
			    
			   var obj = JSON.parse(data);
			   var list =obj.data;
			   for(var i=0;i<list.length;i++){
				   var obj=list[i];
				   var ch=obj.chineseName;
				   var fn=obj.foreignName;
				   var fullName = pinyin.getFullChars(ch);
				   var obj1={};
				   obj1.chineseName=ch;
				   obj1.foreignName=fullName;
				  
				   $.ajax({
						url : $.cxt + "/capability/updateAct", 
						data : obj1,		//id.class
						dataType:"json",
						type: "POST",
						async:false,  //true（异步）或 false（同步)
						success : function(data) {
							
							
							
							
							
						}
					});
				   
				   
				   
				   
			   }
			   
              
		}
	});
}
