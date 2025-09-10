$(function(){
	
    
	
	
	
	
})
function searchTwo() {
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
	changeSelectTwo(tagetArr,index);
}
function changeSelectTwo(str,index){
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

