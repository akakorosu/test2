var dateId = "";
var lable = "";
var num = "";
//消除赋值失败
var date1 = "";
var orgId = '';
var mapJson='';
var mapType='';
$(function() {	
	//从后台获取时间
	$.ajax({
		"url" : $.cxt + "/contentProjectRank/getTime", 
		'data':{"code":1004},
		"type":"POST",
		"async": false,
		"dataType":"json",
		"success":function(data){
			var obj=JSON.parse(data);
			dateId=obj.msg;
			$('#time1').val(dateId);			
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
			$("#city").append(html);
		}
	});
	// 选择区域
	$("#city").chosen({
		disable_search : true,
	}).change(function(option, checked) {
		orgId = $("#city").val();
		getTime();
	});
	
	//获取地图配置
	$.ajax({
		"data":{},
		"url" : $.cxt + "/industryOverview/getMapConf", 
		"type":"POST",
		 async: false,
		"dataType":"json",
		"success":function(data){
			var obj = JSON.parse(data);
			var map =obj.data;
			mapJson=map.jsonName;
			mapType=map.mapType;
		}
	});
	//时间初始化
	$("#time1").datepicker({
        autoclose:true,
        format:"yyyymmdd",
        viewDate:dateId,
        onClose : getTime
    }).on('changeDate', getTime);			
	getApptop("","0",dateId);		
	//getUserDistributionMap("","0",dateId);
	getAgePercent("","0",dateId);	
});
var sntest123 = function(){
	topshowLabelTree.show(function(obj){
		// level labelCode labelName		
		num = obj.level;
		lable = obj.labelName;
		$("#labelName2").val(lable);
		getApptop(lable,num,dateId);
		getAgePercent(lable,num,dateId);
		//getUserDistributionMap(lable,num,dateId);
	})
}

//获取时间
function getTime(ev){
	dateId=$('#time1').val();
	getApptop(lable,num,dateId);
	getAgePercent(lable,num,dateId);
	//getUserDistributionMap(lable,num,dateId);

}
//获取APP排名
function getApptop(label,num,dateId){
	$.ajax({
		"data":{label : label,"num":num,"dateId":dateId},
		"url" : $.cxt + "/industrypic/selectapptop10", 
		"type":"POST",
		"dataType":"json",
		"async" : false,
		"success":function(data){
			$('#tbody').empty();
			var data=JSON.parse(data);		
			if(data.code!="0"){
				return;
			}
			data=data.data;
			var name_arr=new Array();
			var user_arr=new Array();
			for(var i in data){
				name_arr[name_arr.length]= data[i].prodName;
				user_arr[user_arr.length]={value:data[i].userTotalPercent,name:data[i].prodName};
				var tr='<tr>' +
				'<td>'+data[i].prodName+'</td>'+ 
				'<td>'+data[i].userTotalPercent+'%</td>'+ 
				'</tr>';
				$("#tbody").append(tr);
			}
			meigui(name_arr,user_arr);
		}
	});	
	
}
//APP排名玫瑰图
function meigui(name_arr,user_arr){
	// 获取到这个DOM节点，然后初始化 
	var chartDom = document.getElementById('mei_echarts');
	echarts.dispose(chartDom);
	var echartsJoinApp = echarts.init(chartDom);      
    //屏幕自适应
	window.addEventListener("resize",function(){
		echartsJoinApp.resize();
	});
    var option={
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c}% "//({d}%)
        },
        //图例的位置与名称
        legend: {
            x : 'center',
            y : 'bottom',
//            data:name_arr
        },
       
        calculable : true,
        graphic:{            //echarts饼图中间放字
            type:'text',
            left:'center',
            top:'center',
            z:2,
		//  zlevel:10,
            style:{
                text:'',
                textAlign:'center',
                fill:'#000',
                font:' 22px 微软雅黑 '
            },
        },
        series : [
            {
                name:'APP排行',
                type:'pie',
                radius : [40, 100],
                center : ['50%', '55%'], //图表的中心位于容器的相对位置
                roseType : 'radius',        //面积模式
                itemStyle : {
                    normal: {

                        label: {                 //指示到模块的标签文字
                            show: true,
                            color:'black',
                            formatter: '{b}\n'
//                            formatter: '{b}\n{c}%'
                        		},
                        labelLine: {             //指示到模块的标签线
                            show: true,

                        	}
                    	},
                },
                color: ['#FF6666','#FFCC66','#0285CF','#33CC99','#FF9900',' #c3b4df','#59ccf7','#ADFF2F','#BF3EFF','#393939'], //颜色将根据饼图的区域个数循环
                data:user_arr

            }
        ]
    };
    echartsJoinApp.setOption(option);
}

//获取性别年龄比例
function getAgePercent(label,num,dateId){
	$.ajax({
		"data":{"label":label,"num":num,"dateId":dateId},
		"url" : $.cxt + "/industrypic/selectage", 
		"type":"POST",
		"dataType":"json",
		"async" : false,
		"success":function(data){
			var data=JSON.parse(data);		
			if(data.code!="0"){
				return;
			}
			data=data.data;
			if(data==undefined){
				$("#jin").css('width','50%');
				$("#manPercent").html('0%');
				$("#womanPercent").html('0%');
				var chartDom = document.getElementById('columnecharts');
				echarts.dispose(chartDom);
				return;
			}
			if(data.manPer==undefined&&data.womanPer==undefined){
				$("#jin").css('width','50%');
				$("#manPercent").html('0%');
				$("#womanPercent").html('0%');
			}
			$("#jin").css('width',data.manPer);
			$("#manPercent").html(data.manPer);
			$("#womanPercent").html(data.womanPer);		
			getAgeDistribution(data);
		}
	});	
	
}
function getAgeDistribution(data) {
	var chartDom = document.getElementById('columnecharts');
	echarts.dispose(chartDom);
	var columnecharts = echarts.init(chartDom);  
    //屏幕自适应
    window.addEventListener("resize",function(){
    	columnecharts.resize();
    });
    var option = {
 	    color: ['#5EB2FF'],
 	    tooltip : {
 	        trigger: 'axis',
 	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
 	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
 	        },
 	       formatter: "{b} : {c}% "
 	    },
 	    grid: {
 	        left: '0%',
 	        right: '5%',
 	        bottom: '0%',
 	        	top:"0%",
 	        containLabel: true
 	    },
 	    yAxis : [
 	        {
 	            type : 'category',
 	            data : ['65岁以上', '55-64岁', '45-54岁', '35-44岁', '25-34岁', '18-24岁', '18岁以下'],
 	            axisTick: {
 	                alignWithLabel: true,
 	                lineStyle: {
 	                	color:"#999"
 	                }
 	            },
 	            axisLine:{
 	            	lineStyle: {
 	                	color:"#999"
 	                }
 	            }
 	        }
 	    ],
 	    xAxis : [
 	        {
 	            type : 'value',
 	            axisLine:{
 	            	show: false
 	            },
 	            axisTick:{
 	            	show: false
 	            },
 	            axisLabel:{
 	            	color:"#999"
 	            }
 	        }
 	    ],
 	    series : [
 	        {
 	            name:'年龄段占比',
 	            type:'bar',
 	            barWidth: '40%',
 	            data:[data.age7, data.age6, data.age5, data.age4, data.age3, data.age2, data.age1]
 	        }
 	    ]
 	};
 columnecharts.setOption(option);
}
//获取黑龙江地区用户分布
function getUserDistributionMap(label,num,dateId){	
	$.ajax({
		"data":{"label":label,"num":num,"dateId":dateId},
		"url" : $.cxt + "/industrypic/getMapDataList", 
		"type":"POST",
		"dataType":"json",
		"success":function(data){
			var data=JSON.parse(data);
			data=data.data;
			var user_total=new Array();
			var flow=new Array();
			$("#table").empty();
			var chartDom = document.getElementById('map_echarts');
			echarts.dispose(chartDom);
			var myChart = echarts.init(chartDom);
		
			if(data.length>0){
				var maxUserTotal=[];
				for(i in data){				
					user_total[user_total.length]={name: data[i].areaId, value: data[i].userTotal};
					flow[flow.length]={name: data[i].areaId, value: data[i].totalFlow};
					maxUserTotal.push(data[i].userTotal);
					//表格数据
					var tr='<tr>' +
							'<td>'+data[i].areaId+'</td>'+ 
							'<td>'+data[i].userTotal+'</td>'+ 
							'<td>'+data[i].userTotalPer+'%</td>'+ 
							'<td>'+data[i].totalFlow+'</td>'+ 
							'<td>'+data[i].totalFlowPer+'%</td>'+ 
							'<td>'+data[i].perCapitaFlow+'</td>'+ 
						 '</tr>'
					$("#table").append(tr);
				}
				var maxvalue= Math.max.apply(null,maxUserTotal);
				var minvalue = Math.min.apply(null, maxUserTotal);
				if(maxvalue==minvalue){
					minvalue=0;
				}
				map_echarts(user_total,maxvalue,minvalue);
				
				
			}
		}
	});
}
//地图
function map_echarts(user_total,maxvalue,minvalue){
	var chartDom = document.getElementById('map_echarts');
	echarts.dispose(chartDom);
	var myChart = echarts.init(chartDom);
	myChart.hideLoading();
	$.get(mapJson, function (geoJson) {
        myChart.hideLoading();
        echarts.registerMap(mapType, geoJson);
        myChart.setOption(option = {
            title: {
                //text: '地域分布',
                //subtext: '黑龙江地区'
            },
            tooltip: {
                trigger: 'item',
                //formatter: '{b}<br/>{c} (人)'
                formatter: function(params) {
                    var html=params.name+'<br/>';
                    if(params.value===params.value){  //NAN!=NAN  NAN 是唯一一个与本身不相等的值
                    	html+=params.value+' (户)';
                    }else{
                    	html+='0 (户)';
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
            visualMap: {
                min: minvalue,
                max: maxvalue,
                text:['高','低'],
                realtime: false,
                left: 'right',
                calculable: false,
                inRange: {
                    color: ['lightskyblue','cyan', 'blue']
                }
            },
            series: [
                {
                	zoom:1.2,
                    name: '地域分布',
                    type: 'map',
                    mapType: mapType, 
                    itemStyle:{
                        normal:{label:{show:true}},
                        emphasis:{label:{show:true}}
                    },
                    data:user_total
                }
            ]
        });
    });
};
