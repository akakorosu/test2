var time = '';
var cityId = '';
var mapJson = "";
var mapType = "";
var city_data=new Array();
var city_map=new Map();//key:城市ID  value:城市名称
var mapData;
var contentLabelName1_array=new Array();
var hot_data="";
var label_map=new Map();

$(function () {
	$.ajax({
		"data":{},
		"url" : $.cxt + "/sysopt/getSysDay",
		"type":"POST",
		 async: false,
		"dataType":"json",
		"success":function(data){
			var obj = JSON.parse(data);
			time=obj.data;
		}
	});

	//初始化下拉框（区域）
	$.ajax({
		"url" : $.cxt + "/sysorg/getCityOption",
		"type":"POST",
		 async: false,
		"dataType":"json",
		"success":function(data){
			var obj = JSON.parse(data);
			var list=obj.data;
			var html="";
			for(var i=0;i<list.length;i++){
				html+="<option value="+list[i].orgId+">"+list[i].name+"</option>"
			}
			$("#cityId").append(html);
			cityId = $("#cityId").val();
		}
	});

	//选择城市
	$("#cityId").chosen({
		disable_search : true,
	}).change(function(option, checked) {
		cityId=$("#cityId").val();
		loadData();
	});

	//选择时间
    $("#time").datepicker({
        autoclose:true,
        format:"yyyymmdd"
    }).on('changeDate', loadData);
    $("#time").val(time);

    loadData();

	//跳转页面 展示所有的热点数据
	$('#more_hot_data').click(function(){
		window.location.href=$.cxt+"/billBoard/showHotData?mapJson="+mapJson +"&cityId="+cityId+"&monthId="+time;
	});
	$('#more_label').click(function(){
		window.location.href=$.cxt+"/billBoard/showAllLabels?mapJson="+mapJson +"&cityId="+cityId+"&monthId="+time;
	});
})

function loadData() {
	time = $("#time").val();
	console.log("load")
	get_word ()
	//获取热搜数据
	// get_hot_search();

	//获取地图配置
	// get_map_conf()

	//获取json文件的城市信息
	// get_json_data();

	//获取每个城市的热点数据
	// get_city_hot_data()

	//地图
	// map_echarts(city_data, mapJson, mapType);

	//获取码表中的分类
	// get_category_label();

	// initSwiper();

	//获取分类数据
	// get_category_data();
}

function get_word (){
	console.log("word")
	$.ajax({
		"data":{"billBoard":""},
		"url" : $.cxt + "/billBoard/getwordcloud",
		"type":"POST",
		async: false,
		"dataType":"json",
		"success":function(data){
			var obj = JSON.parse(data);
			console.log(obj)
			return false;
			var map =obj.data;
			mapJson = map.jsonName;
			mapType=map.mapType;
		}
	});
}
function initSwiper(){
	var swiper1 = new Swiper('.swiper1', {
		//    设置slider容器能够同时显示的slides数量(carousel模式)。
		//    可以设置为number或者 'auto'则自动根据slides的宽度来设定数量。
		//    loop模式下如果设置为'auto'还需要设置另外一个参数loopedSlides。
		slidesPerView: 3.5,
		paginationClickable: true, //此参数设置为true时，点击分页器的指示点分页器会控制Swiper切换。
		spaceBetween: 0, //slide之间的距离（单位px）。
		freeMode: false, //默认为false，普通模式：slide滑动时只滑动一格，并自动贴合wrapper，设置为true则变为free模式，slide会根据惯性滑动且不会贴合。
		loop: false, //是否可循环
		onTab: function(swiper) {
			var n = swiper1.clickedIndex;
		}
	});
}
//获取热搜数据
function get_hot_search(){
	$.ajax({
		"data":{cityId: cityId, monthId: time},
		"url":$.cxt+"/billBoard/queryHotData",
		"type":'POST',
		async: false,
		"dataType": "json",
		"success": function (data) {
			var data = JSON.parse(data);
			data=data.data;
			if(data.length <= 0) {
				$("#hotspot").empty();
			}else {
				var obj="";
				var html="";
				var products="";
				var img="";
				for(var i=0;i<10;i++){
					obj=data[i];
					html+='<div class="spotList">'+
						'<span class="spotList-1"><label>'+(i+1)+'</label></span>'+
						'<span class="spotList-2">'+obj.keyWord+'</span>'+
						'<span class="spotList-3">';
					products=obj.prodName.split(',');
					for(var j in products){
//						var img = pinyin.getFullChars(products[j]);
//						img=img+'.png';
						img='search.png';
						html+='<img src="'+$.cxt+'/pages/images/new/'+img+'" />'
					}
					html+='</span> </div>';
				}
				$("#hotspot").html(html);
			}
		}
	})
}
//获取每个城市的热点数据
function get_city_hot_data(){
	$.ajax({
		"data":{cityId: cityId, monthId: time},
		"url":$.cxt+"/billBoard/queryMapData",
		"type":'POST',
		async: false,
		"dataType": "json",
		"success": function (data) {
			var data = JSON.parse(data);
			data=data.data;
			hot_data=data;

			mapData = new Map();
			for(var i in city_data){
				mapData.set(city_data[i].name,new Array());
			}
			var array;
			for(var j in hot_data){
				var area=hot_data[j].area;
				area=city_map.get(area);
				array=mapData.get(area)
				array[array.length]=hot_data[j].keyWord+"~"+hot_data[j].userTotal;
			}
		}
	});
}



//获取地图配置
function get_map_conf (){
	$.ajax({
		"data":{"city":cityId},
		"url" : $.cxt + "/sysorg/getMapConf",
		"type":"POST",
		 async: false,
		"dataType":"json",
		"success":function(data){
			var obj = JSON.parse(data);
			var map =obj.data;
			mapJson = map.jsonName;
			mapType=map.mapType;
		}
	});
}

//获取json文件的城市信息
function get_json_data(){
	$.ajax({
		type: "get",//请求方式
		url: $.cxt+"/pages/jsp/dpi/map/"+mapJson,//地址，就是json文件的请求路径
		dataType: "json",//数据类型可以为 text xml json  script  jsonp
		async: false,
		success: function (data) {//返回的参数就是 action里面所有的有get和set方法的参数
			var dataArrays = data.features;
			for (var i = 0; i < dataArrays.length; i++) {
				city_data[city_data.length] = new city_obj(dataArrays[i].properties.name, i)
				city_map.set(dataArrays[i].properties.id,dataArrays[i].properties.name);
			}
		}
	});
}

//保存json文件中地图数据
function city_obj(name, value, select) {
	this.name = name;
	this.value = value;
}


//地图
function map_echarts(city_data, mapJson, mapType) {
	return false
	mapJson = '../map/' + mapJson;
	var chartDom = document.getElementById('map');
	echarts.dispose(chartDom);
	var myChart = echarts.init(chartDom);
	myChart.hideLoading();
	$.get(mapJson, function (geoJson) {
		myChart.hideLoading();
		echarts.registerMap(mapType, geoJson);
		myChart.setOption(option = {
			type: 'map',
			grid: {
				left: '3%',
				right: '4%',
				bottom: '3%',
				containLabel: true
			},
			label: {
				show: true
			},
			zoom: 1.2,

			tooltip: {
				position: ['50%', '15%'],
				backgroundColor: "rgba(242,242,242,0.8)",
				borderColor: "#91c7ff",
				borderWidth: 1,
				padding: 10,
				textStyle: {
					color: "#333333",
					fontSize: 13,
					lineHeight: 30
				},
				formatter: function (params, ticket, callback) {
					var array=mapData.get(params.data.name)
					var html="<span class='echarSpan' style='color:black;font-size:16px;font-weight:bold'>" + params.data.name + "</span>" + "<br />" ;
					if(array.length>10){
						for(var i=0;i<10;i++){
							var arr=array[i].split("~");
							html+="<span class='echarSpan'>"+arr[0]+"</span>&emsp;<span class='echarNum'>"+arr[1]+"</span>" + "<br />";
						}
					}else{
						for(var i=0;i<array.length;i++){
							var arr=array[i].split("~");
							html+="<span class='echarSpan'>"+arr[0]+"</span>&emsp;<span class='echarNum'>"+arr[1]+"</span>" + "<br />";
						}
					}
					return html;
				}
			},
			toolbox: {
				show: false,
				orient: 'vertical',
				left: 'right',
				top: 'center',
				feature: {
					dataView: {readOnly: false},
					restore: {},
					saveAsImage: {}
				}
			},
			series: [
				{
					zoom: 1.2,
					name: '地域分布',
					type: 'map',
					mapType: mapType,
					label: {
						show: true
					},
					itemStyle: {
						normal: {
							areaColor: "#91c7ff",
							borderWidth: 2,
							borderColor: '#fff'
						},
						emphasis: { //选中样式
							areaColor: "#41a2ff"
						}
					},
					data: city_data
				}
			]
		});
	});
	var index = 0;

	// 以下代码取消轮询
	/*
	setInterval(function () {
		myChart.dispatchAction({
			type: 'showTip',
			seriesIndex: 0,
			dataIndex: index
		});
		remove_area(index - 1, myChart)
		select_area(index, myChart)
		index++;
		if (index > city_data.length) {
			index = 0;
		}
	}, 3000);
	*/
};

/*
//移除地图选中区域
function remove_area(index, myChart) {
	myChart.dispatchAction({
		type: 'mapUnSelect',
		seriesIndex: 0,
		dataIndex: index
	});
}

//选中地图区域
function select_area(index, myChart) {
	myChart.dispatchAction({
		type: 'mapSelect',
		seriesIndex: 0,
		dataIndex: index
	});
}
*/

//获取码表中的分类
function get_category_label(){
	$.ajax({
		"data":{},
		"url":$.cxt+"/billBoard/selectLabel",
		"type":'POST',
		async: false,
		"dataType": "json",
		"success": function (data) {
			var data = JSON.parse(data);
			data=data.data;

			for(var i=0;i<data.length;i++){
				if(contentLabelName1_array.indexOf(data[i].contentLabelName1) == -1){
					contentLabelName1_array[contentLabelName1_array.length]=data[i].contentLabelName1;
				}
				label_map.set(data[i].contentLabelName1,new Array);
			}
			var array="";
			for(var i=0;i<data.length;i++){
				array=label_map.get(data[i].contentLabelName1);
				if(array){
					array[array.length]=data[i].contentLabelName2;
					label_map.set(data[i].contentLabelName1,array)

				}
			}

			var html_1="";
			var html_2="";
			for(var i=0;i<contentLabelName1_array.length;i++){
				var img = pinyin.getFullChars(contentLabelName1_array[i])+".png";
				html_1+=group_part_label(label_map.get(contentLabelName1_array[i]),img,contentLabelName1_array[i]);
				html_2+=group_all_label(label_map.get(contentLabelName1_array[i]),img,contentLabelName1_array[i],'myTab'+i);
			}
			$('#data').html("");
			$('#data').append(html_1);

			$('#bill').html("");
			$('#bill').append(html_2)

		}
	})
}

function group_part_label(array,img,label){
	var html='<div class="tvList">';
	html+='<span class="tvList-1"><img src="'+$.cxt+'/pages/images/new/'+img+'" /></span>';
	var res="&emsp;";
	var contentLabelName2_array=new Array();
	for(var i in array){
		if(array[i]){
			contentLabelName2_array[contentLabelName2_array.length]=array[i];
		}
	}
	for(var i=0;i<4;i++){
		if(contentLabelName2_array[i]){
			res+='<a onclick="choose_cate2(this)">'+contentLabelName2_array[i]+'</a>&emsp;';
		}
	}
	html+='<span class="tvList-2"><label>'+label+'</label>'+res+'</span>';
	html+='<span class="tvList-3" onclick="more_data_1(this)">更多</span></div>';
	return html;
}

function group_all_label(array,img,label,id){
	var html='<div class="bill-list">';
	html+='<div class="list-title">'+
		'<img src="'+$.cxt+'/pages/images/new/'+img+'" />'+label+' <span onclick="more_data_2(this)">更多</span>'+
		'</div>';
	html+='<div class="swiper1 swiper-container clearfix">';
	html+='<ul id="'+id+'" class="nav nav-tabs swiper-wrapper clearfix">';

	html+='<li class="active swiper-slide"  onclick="change_cate2(this)">';
	html+='<a href="#'+id+0+'" data-toggle="tab">全部</a></li>';
	var j=1;
	for(var i=0;i<array.length;i++){
		if(array[i]){
			html+='<li class="swiper-slide"  onclick="change_cate2(this)">';
			html+='<a href="#'+id+(i+1)+'" data-toggle="tab">'+array[i]+'</a></li>';
			j++;
		}
	}

	html+='</ul></div>';
	html+='<div id='+id+"_1"+' class="tab-content" ><div><a onclick="show_whole(this)" class="whole" style="color:#8084A4;">完整榜单</a></div>';
	for(k=0;k<j;k++){
		if(k==0){
			html+='<div class="tab-pane fade in active"  id='+id+k+' style="overflow: hidden"></div>';
		}else{
			html+='<div class="tab-pane fade" id='+id+k+' style="overflow: hidden"></div>';
		}


	}
	html+='</div></div>';
	return html;
}

function get_category_data(){
	$.ajax({
		"data":{cityId: cityId, monthId: time},
		"url":$.cxt+"/billBoard/selectData",
		"type":'POST',
		"dataType": "json",
		"success": function (data) {
			data = JSON.parse(data);

			var array="";
			for(var i in contentLabelName1_array){
				array=data[contentLabelName1_array[i]];
				var max=0;
				if (array.length > 10) {
					max = 10;
				} else {
					max = array.length;
				}
				var html='';
				for (var j = 0; j < max; j++) {
					html += '<div class="tab-list"> <div class="tab-list-show">'
					html += '<span class="tab-list-show-1"><label>' + (j + 1) + '</label></span>';
					html += '<span class="tab-list-show-2">' + array[j].keyWord + '</span>';
					html += '<span class="tab-list-show-3">' + array[j].userTotal + '</span></div></div>';
				}
				$("#myTab" + i + "0").append(html);
			}

		}
	})
}
//点击下面的分类标签时切换数据
function change_cate2(obj){
	var contentLabelName1=obj.parentNode.parentNode.previousSibling.innerText.split("\n")[0];
	var contentLabelName2 = obj.innerText;
	if(contentLabelName2=='全部'){
		contentLabelName2='';
	}
	var contentLabelName2_id=obj.firstChild.getAttribute("href");
	$(contentLabelName2_id).html("");
	$.ajax({
		"data": {contentLabelName1:contentLabelName1,contentLabelName2:contentLabelName2, cityId: cityId, monthId: time},
		"url": $.cxt + "/billBoard/selectContentLabelName2Data",
		"type": 'POST',
		"dataType": "json",
		"success": function (data) {
			data = JSON.parse(data);
			data=data.data;
			if(data.length>0){
				var max=0;
				if (data.length > 10) {
					max = 10;
				} else {
					max = data.length;
				}
				var html='';
				for(var j=0;j<max;j++) {
					data[j]
					html += '<div class="tab-list"> <div class="tab-list-show">'
					html += '<span class="tab-list-show-1"><label>' + (j + 1) + '</label></span>';
					html += '<span class="tab-list-show-2">' + data[j].keyWord + '</span>';
					html += '<span class="tab-list-show-3">' + data[j].userTotal + '</span></div></div>';
				}
				$(contentLabelName2_id).append(html);
			}
		}
	});
}
//点击热搜分类的更多时跳转页面
function more_data_1(obj){
	var contentLabelName1=obj.previousSibling.getElementsByTagName("label")[0].innerText;
	show_more_data(contentLabelName1);
}
//点击每个大分类的更多时跳转页面
function more_data_2(obj){
	var contentLabelName1=obj.parentNode.innerText.split("\n")[0];
	show_more_data(contentLabelName1.replace("更多","").trim())
}
function show_more_data(contentLabelName1){
	window.location.href=$.cxt+"/billBoard/showData?contentLabelName1="+  encodeURI(encodeURI(contentLabelName1))+"&contentLabelName2="+encodeURI(encodeURI(""))+"&mapJson="+mapJson +"&cityId="+cityId+"&monthId="+time;
}
//点击热搜分类的小分类时跳转页面
function choose_cate2(obj){
    var contentLabelName1=obj.parentNode.firstChild.innerText;
    var contentLabelName2=obj.innerText;
    window.location.href=$.cxt+"/billBoard/showData?contentLabelName1="+encodeURI(encodeURI(contentLabelName1))+"&contentLabelName2="+encodeURI(encodeURI(contentLabelName2))+"&mapJson="+mapJson +"&cityId="+cityId+"&monthId="+time;
}
//点击完整榜单时跳转页面
function show_whole(obj){
	var contentLabelName1=obj.parentNode.parentNode.parentNode.firstChild.innerText.replace("更多","").trim();
	var contentLabelName2=obj.parentNode.parentNode.previousSibling.firstChild.getElementsByClassName("active")[0].innerText;
	window.location.href=$.cxt+"/billBoard/showData?contentLabelName1="+encodeURI(encodeURI(contentLabelName1))+"&contentLabelName2="+encodeURI(encodeURI(contentLabelName2))+"&mapJson="+mapJson +"&cityId="+cityId+"&monthId="+time;
}
