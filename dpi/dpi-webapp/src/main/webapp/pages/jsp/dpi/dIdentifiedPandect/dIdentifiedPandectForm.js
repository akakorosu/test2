$(function(){
    $(".chosen-select").chosen({
        disable_search : true
    });
    $(".chosen-multiple").multiselect();
    $(".datepicker-input").datepicker({
        autoclose:true,
        format:"yyyy-mm-dd"
    });
    //加载数据图谱的map
    $.ajax({
		url : $.cxt + "/capability/initMap", 
		//data : topgetObjByObj($("#showSensiForm .formField")),		//id.class
		dataType:"json",
		type: "POST",
		async:true,  //true（异步）或 false（同步)
		success : function(data) {}
	});
});

$(function(){
	loadEcharts("20181112");
});

function loadEcharts(date){
//	alert("loadEcharts--->"+date);
	//应用记录识别率,不含ip识别率
	var obj_echarts_numberUnipTotal = echarts.init(document.getElementById('echarts_numberUnipTotal'));
	var url_echarts_numberUnipTotal = "echarts_numberUnipTotal";
	initEcharts(obj_echarts_numberUnipTotal,url_echarts_numberUnipTotal,date);
	
	//应用流量识别率,不含ip识别率
	var obj_echarts_flowUnipTotal = echarts.init(document.getElementById('echarts_flowUnipTotal'));
	var url_echarts_flowUnipTotal = "echarts_flowUnipTotal";
	initEcharts(obj_echarts_flowUnipTotal,url_echarts_flowUnipTotal,date);
	
	//标签域名分布
	var obj_echarts_domain = echarts.init(document.getElementById('echarts_domain'));
	var url_echarts_domain = "echarts_domain";
	initEcharts(obj_echarts_domain,url_echarts_domain,date);
	
	//标签产品分布
	var obj_echarts_product = echarts.init(document.getElementById('echarts_product'));
	var url_echarts_product = "echarts_product";
	initEcharts(obj_echarts_product,url_echarts_product,date);
	
	//标签层级分布
	var obj_echarts_label = echarts.init(document.getElementById('echarts_label'));
	var url_echarts_label = "echarts_label";
	initEcharts(obj_echarts_label,url_echarts_label,date);
	
	//深度规则分布
	var obj_echarts_rule = echarts.init(document.getElementById('echarts_rule'));
	var url_echarts_rule = "echarts_rule";
	initEcharts(obj_echarts_rule,url_echarts_rule,date);
	
	//屏幕自适应
	window.addEventListener("resize",function(){
		obj_echarts_numberUnipTotal.resize();
		obj_echarts_flowUnipTotal.resize();
		obj_echarts_domain.resize();
		obj_echarts_product.resize();
		obj_echarts_label.resize();
		obj_echarts_rule.resize();
	});
}

//图表的初始化
function initEcharts(objEcharts,url,dateId){
//	alert("initEcharts--->"+dateParam);
	objEcharts.clear();
	objEcharts.showLoading({text: '正在努力的读取数据中...'});
    $.ajax({
        url:$.cxt + "/dIdentifiedPandect/"+url,
        data:{dateId:dateId},
        type:"post",
        success:function(data){
            var option = eval("("+data+")");
            objEcharts.setOption(option);
            objEcharts.hideLoading();
        }
    })
}
