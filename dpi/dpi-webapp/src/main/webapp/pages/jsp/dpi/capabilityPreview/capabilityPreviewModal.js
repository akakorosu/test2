
$(function(){
	
   
    $("#goBack").click(function(){
    	topwindow.removeWindow();
     });
  
    $("#result11").show();
    
	initLabel();
	//alert(bbb);
	
})


function bondClick(){
	$(".slide-nav-left li").click(function(){
    $(this).siblings().removeClass("active");
    $(this).addClass("active");
    var aaaa= $(this).html();
    //var m=aaaa.split("|");
    if(labelFlag=='AN00'){
    	printRigth(aaaa,shiPin);
    }
    if(labelFlag=='BT00'){
    	printRigth(aaaa,dianShang);
    }
    if(labelFlag=='AP00'){
    	printRigth(aaaa,xiaoShuo);
    }
    
      })
	
}
/*function uniq(array){
    var temp = [];
    var index = [];
    var l = array.length;
    for(var i = 0; i < l; i++) {
        for(var j = i + 1; j < l; j++){
            if (array[i] === array[j]){
                i++;
                j = i;
            }
        }
        temp.push(array[i]);
        index.push(i);
    }
    //console.log(index);
    return temp;
}*/
function uniq(array){
    array.sort();
    var temp=[array[0]];
    for(var i = 1; i < array.length; i++){
        if( array[i] !== temp[temp.length-1]&&array[i]!=null&&array[i]!=undefined){
            temp.push(array[i]);
        }
    }
    return temp;
}

function initLabel(){
	var leftBar= $("#leftBar");
	
	if(labelFlag=='AN00'){ //视频
		$("#leftBar").empty();
		var str='';
		var aa=[];
		for(var i=0;i<shiPin.length;i++){
			aa.push(shiPin[i].contentLabelName2);
		}
		//debugger
		var bb=uniq(aa);
		//console.log(bb);
		
		for(var i=0;i<bb.length;i++){
			/*if((i+1)%2==0&&i!=0){
				str+='|'+bb[i];
				var li=$("<li></li>");
				li.html(str);
				leftBar.append(li);
				str='';
				
			}else{
				str+=bb[i];
				
			}*/
			str=bb[i];
			var li=$("<li></li>");
			li.html(str);
			leftBar.append(li);
			str='';
			
		}
		leftBar.find("li").first().addClass("active");
		
		var one=bb[0];
		//alert(one);
		//var two=bb[1];
		printRigth(one,shiPin);
		bondClick();
		
		
		//$("#rigthBar")
	}
    if(labelFlag=='BT00'){
    	$("#leftBar").empty();
    	var str='';
		var aa1=[];
		for(var i=0;i<dianShang.length;i++){
			aa1.push(dianShang[i].contentLabelName2);
		}
		
		var bb1=uniq(aa1);
		//console.log(bb1);
		
		for(var i=0;i<bb1.length;i++){
			/*if((i+1)%2==0&&i!=0){
				str+='|'+bb1[i];
				var li=$("<li></li>");
				li.html(str);
				leftBar.append(li);
				str='';
				
			}else{
				str+=bb1[i];
				
			}*/
			str=bb1[i];
			var li=$("<li></li>");
			li.html(str);
			leftBar.append(li);
			str='';
			
		}//电商
		leftBar.find("li").first().addClass("active");
		var one=bb1[0];
		var two=bb1[1];
		printRigth(one,dianShang);
		bondClick();
		
	}
    if(labelFlag=='AP00'){ 
    	$("#leftBar").empty();
    	var str='';
		var aa2=[];
		for(var i=0;i<xiaoShuo.length;i++){
			aa2.push(xiaoShuo[i].contentLabelName2);
		}
		
		var bb2=uniq(aa2);
		//console.log(bb2);
		
		for(var i=0;i<bb2.length;i++){
			/*if((i+1)%2==0&&i!=0){
				str+='|'+bb2[i];
				var li=$("<li></li>");
				li.html(str);
				leftBar.append(li);
				str='';
				
			}else{
				str+=bb2[i];
				
			}*/
			str=bb2[i];
			var li=$("<li></li>");
			li.html(str);
			leftBar.append(li);
			str='';
			
		}//小说
		leftBar.find("li").first().addClass("active");
		var one=bb2[0];
		var two=bb2[1];
		printRigth(one,xiaoShuo);
		bondClick();
	
    }
	
	
}

function printRigth(one,arr){
	//return;
	
	$("#rigthBar").empty();
	
	var rigth1=[];
	for(var i=0;i<arr.length;i++){
		if(arr[i].contentLabelName3!=null&&arr[i].contentLabelName3!=undefined){
			/*if(arr[i].contentLabelName2==one||arr[i].contentLabelName2==two){
				rigth1.push(arr[i].contentLabelName3);
				
			}*/
			if(arr[i].contentLabelName2==one){
				rigth1.push(arr[i].contentLabelName3);
				
			}
			
		}
		
	}
	
	var bb2=uniq(rigth1);
	
	
	if(rigth1.length!=0){   //右侧第一级
		for(var n=0;n<bb2.length;n++){
			var li=$("<li class=\"clearfix\"></li>");
			var str1='<b class="main pull-left">'+bb2[n]+'</b><span class="right-icon pull-left">></span>';
			li.append(str1);
			var rigth2=[];
			var rigth5=[];
			for(var i=0;i<arr.length;i++){
				if(arr[i].contentLabelName4!=null&&arr[i].contentLabelName4!=undefined){
					if(arr[i].contentLabelName3==bb2[n]){
						rigth2.push(arr[i].contentLabelName4);
						rigth5.push(arr[i]);
					}
					
				}
				
			}
		    //console.log(rigth2);
			var bb3=uniq(rigth2);
			
			//console.log(bb3);
			var div=$("<div class='clearfix' style='float:left;width:452px; min-height:40px;'></div>");
			if(rigth2.length!=0){
				
				var str8='';
				for(var m=0;m<bb3.length;m++){
					
					/*if(rigth5[m].contentLabelName5!=null&&rigth5[m].contentLabelName5!=undefined){
						//debugger
						str8+='<span class="pull-left" data-toggle="tooltip" title="'+rigth5[m].contentLabelName5+'">/  '+rigth2[m]+'</span>';
					}else{
						str8+='<span class="pull-left">/  '+rigth2[m]+'</span>';
					}*/
					var str10='';
					for(var q=0;q<rigth5.length;q++){
						if(rigth5[q].contentLabelName4==bb3[m]&&rigth5[q].contentLabelName5!=null){
							str10+=rigth5[q].contentLabelName5+'|>';
						}
						
					}
					if(str10!=''){
						str10=str10.substring(0,str10.length-2);
						str8+='<span class="pull-left" data-toggle="tooltip" title="'+str10+'">|  '+bb3[m]+'</span>';
					}else{
						str8+='<span class="pull-left" data-toggle="tooltip" title="无下级标签">|  '+bb3[m]+'</span>';
					}
					
					
				}
				div.append(str8);
				li.append(div);
				$("#rigthBar").prepend(li);
			}else{
				li.append(div);
				$("#rigthBar").append(li);
			}
			
			
			
		}
		
		
		
	}else{
		
		var li=$("<li class=\"clearfix\"></li>");
		var str1='<b class="main pull-left">无下级标签</b><span class="right-icon pull-left">></span>';
		li.append(str1);
		$("#rigthBar").append(li);
	}
	
	  $('[data-toggle="tooltip"]').tooltip({html : true });   
	
	
}

function search1(){
	var value=$("#profession1").val();
	if(value==null||value==''||value==undefined){
		return ;
	}
	if(labelFlag=='AN00'){  //shipin
		searchModelLabel(value,shiPin);
		
		
	}
	if(labelFlag=='BT00'){  // dianshang
		searchModelLabel(value,dianShang);
	}
	if(labelFlag=='AP00'){   //xiaoshuo
		searchModelLabel(value,xiaoShuo);
	}
	
	
}
var searchArr=[];
var searchArrNum=0;
function searchModelLabel(str,arr){
	searchArr=[];

	for(var i=0;i<arr.length;i++){
		
		if(arr[i].contentLabelName1!=null&&arr[i].contentLabelName1.indexOf(str)!=-1){
			 searchArr.push(arr[i]);
			 continue; 
			
		}
		if(arr[i].contentLabelName2!=null&&arr[i].contentLabelName2.indexOf(str)!=-1){
			 searchArr.push(arr[i]);
			 continue; 
			
		}
		if(arr[i].contentLabelName3!=null&&arr[i].contentLabelName3.indexOf(str)!=-1){
			 searchArr.push(arr[i]);
			 continue; 
			
		}
		if(arr[i].contentLabelName4!=null&&arr[i].contentLabelName4.indexOf(str)!=-1){
			 searchArr.push(arr[i]);
			 continue; 
			
		}
		if(arr[i].contentLabelName5!=null&&arr[i].contentLabelName5.indexOf(str)!=-1){
			 searchArr.push(arr[i]);
			 continue; 
			
		}
	}
	if(searchArr.length!=0){
		/*<ul class="content-overview">
        <li class="pull-left">玩3C<span>></span></li>
        <li class="pull-left">影像Club<span>></span></li>
        <li class="pull-left">手机频道<span>></span></li>
        <li class="pull-left">网上营业厅<span>></span></li>
        <li class="pull-left">配件选购中心<span>></span></li>
        </ul>*/
		var obj=searchArr[0];
		var ul= $('<ul class="content-overview"> </ul>');
		if(obj.contentLabelName1!=null&&obj.contentLabelName1!=''&&obj.contentLabelName1!=undefined){
			var str='<li class="pull-left">'+obj.contentLabelName1+'<span>></span></li>';
			ul.append(str);
		}
		if(obj.contentLabelName2!=null&&obj.contentLabelName2!=''&&obj.contentLabelName2!=undefined){
			var str='<li class="pull-left">'+obj.contentLabelName2+'<span>></span></li>';
			ul.append(str);
		}
		if(obj.contentLabelName3!=null&&obj.contentLabelName3!=''&&obj.contentLabelName3!=undefined){
			var str='<li class="pull-left">'+obj.contentLabelName3+'<span>></span></li>';
			ul.append(str);
		}
		if(obj.contentLabelName4!=null&&obj.contentLabelName4!=''&&obj.contentLabelName4!=undefined){
			var str='<li class="pull-left">'+obj.contentLabelName4+'<span>></span></li>';
			ul.append(str);
		}
		if(obj.contentLabelName5!=null&&obj.contentLabelName5!=''&&obj.contentLabelName5!=undefined){
			var str='<li class="pull-left">'+obj.contentLabelName5+'<span>></span></li>';
			ul.append(str);
		}
		$(".content-overview").remove();
		$(".detail-content").prepend(ul);
		
		$("#searchLabelNum2").html(searchArr.length);
		
		
		
	}else{
		messageAlert("不存在搜索的标签");
	}
	
}
function changeNext(){
	if(searchArrNum==searchArr.length-1){
		searchArrNum=searchArr.length-1;
	}else{
		searchArrNum++;
	}
	var obj=searchArr[searchArrNum];
	var ul= $('<ul class="content-overview"> </ul>');
	if(obj.contentLabelName1!=null&&obj.contentLabelName1!=''&&obj.contentLabelName1!=undefined){
		var str='<li class="pull-left">'+obj.contentLabelName1+'<span>></span></li>';
		ul.append(str);
	}
	if(obj.contentLabelName2!=null&&obj.contentLabelName2!=''&&obj.contentLabelName2!=undefined){
		var str='<li class="pull-left">'+obj.contentLabelName2+'<span>></span></li>';
		ul.append(str);
	}
	if(obj.contentLabelName3!=null&&obj.contentLabelName3!=''&&obj.contentLabelName3!=undefined){
		var str='<li class="pull-left">'+obj.contentLabelName3+'<span>></span></li>';
		ul.append(str);
	}
	if(obj.contentLabelName4!=null&&obj.contentLabelName4!=''&&obj.contentLabelName4!=undefined){
		var str='<li class="pull-left">'+obj.contentLabelName4+'<span>></span></li>';
		ul.append(str);
	}
	if(obj.contentLabelName5!=null&&obj.contentLabelName5!=''&&obj.contentLabelName5!=undefined){
		var str='<li class="pull-left">'+obj.contentLabelName5+'<span>></span></li>';
		ul.append(str);
	}
	$(".content-overview").remove();
	$(".detail-content").prepend(ul);
}
function changePrev(){
	if(searchArrNum==0){
		searchArrNum=0;
	}else{
		searchArrNum--;
	}
	var obj=searchArr[searchArrNum];
	var ul= $('<ul class="content-overview"> </ul>');
	if(obj.contentLabelName1!=null&&obj.contentLabelName1!=''&&obj.contentLabelName1!=undefined){
		var str='<li class="pull-left">'+obj.contentLabelName1+'<span>></span></li>';
		ul.append(str);
	}
	if(obj.contentLabelName2!=null&&obj.contentLabelName2!=''&&obj.contentLabelName2!=undefined){
		var str='<li class="pull-left">'+obj.contentLabelName2+'<span>></span></li>';
		ul.append(str);
	}
	if(obj.contentLabelName3!=null&&obj.contentLabelName3!=''&&obj.contentLabelName3!=undefined){
		var str='<li class="pull-left">'+obj.contentLabelName3+'<span>></span></li>';
		ul.append(str);
	}
	if(obj.contentLabelName4!=null&&obj.contentLabelName4!=''&&obj.contentLabelName4!=undefined){
		var str='<li class="pull-left">'+obj.contentLabelName4+'<span>></span></li>';
		ul.append(str);
	}
	if(obj.contentLabelName5!=null&&obj.contentLabelName5!=''&&obj.contentLabelName5!=undefined){
		var str='<li class="pull-left">'+obj.contentLabelName5+'<span>></span></li>';
		ul.append(str);
	}
	$(".content-overview").remove();
	$(".detail-content").prepend(ul);
}
function searchTwo11() {
	var val = $("#detail").val();
	var liList = $("#piForm span");
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
	$("#piForm span").removeClass("active");
	$(tagetArr[0]).addClass("active");
	$("#piForm").parent().scrollTop(0);
	$("#piForm").parent().scrollTop($(tagetArr[0]).offset().top-325);
	$("#prodLabelTwoNum").text(tagetArr.length);
	$(".resultTwo").show();
	var index=0;
	changeSelectTwo11(tagetArr,index);
}
function changeSelectTwo11(str,index){
	$("#prevBtn").click(function(){
		index-=1;
		if(index<0){
			index=str.length-1;
		}
		$("#piForm span").removeClass("active");
		$(str[index]).addClass("active");
		$("#piForm").parent().scrollTop(0);
	    $("#piForm").parent().scrollTop($(str[index]).offset().top-325);
	});
	$("#nextBtn").click(function(){
		index+=1;
		if(index>str.length-1){
			index=0;
		}
		$("#piForm span").removeClass("active");
	    $(str[index]).addClass("active");
	    $("#piForm").parent().scrollTop(0);
	    $("#piForm").parent().scrollTop($(str[index]).offset().top-325);
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

