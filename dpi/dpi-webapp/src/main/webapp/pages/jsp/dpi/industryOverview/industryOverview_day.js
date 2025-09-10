var monthId='';
var label_name="";
var city="";
var label_name1='';
var label_name2='';
var orderFlag='1';
var mapJson='';
var mapType='';
$(function(){
	 //设置默认时间
	/*var myDate = new Date();
	var month=myDate.getMonth()+1;
	var day=myDate.getDate()-1;
    month=month>9?month:("0"+month);
    day=day>9?day:("0"+day);
    monthId=myDate.getFullYear()+""+month+""+day;*/
	/*var today=new Date();
	var yesterday_milliseconds=today.getTime()-1000*60*60*24;
    var yesterday=new Date();      
    yesterday.setTime(yesterday_milliseconds);      
    var strYear=yesterday.getFullYear();   
    var strDay=yesterday.getDate();   
    var strMonth=yesterday.getMonth()+1;   
    if(strMonth<10)   
     {   
            strMonth="0"+strMonth;   
        }  
    if(strDay<10)   
    {   
    	strDay="0"+strDay;   
       } 
    monthId=strYear+""+strMonth+""+strDay; */ 
	
	$.ajax({
		"data":{},
		"url" : $.cxt + "/industryOverview/getOverViewTime", 
		"type":"POST",
		 async: false,
		"dataType":"json",
		"success":function(data){
			var obj = JSON.parse(data);
			monthId=obj.data;
			getMessage();
		}
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
	
	
	//初始化下拉框
	$.ajax({
		"data":{},
		"url" : $.cxt + "/industryOverview/getCityOption", 
		"type":"POST",
		 async: false,
		"dataType":"json",
		"success":function(data){
			var obj = JSON.parse(data);
			var list=obj.data;
			var html="";
			for(var i=0;i<list.length;i++){
				html+="<option value="+list[i].areaId+">"+list[i].areaName+"</option>"
				
			}
			$("#city").append(html);
		}
	});
	//选择城市
	$("#city").chosen({
		disable_search : true,
	}).change(function(option, checked) {
		//var cityText = $('#city option:selected').text();
		city=$("#city").val();
		if(city=='1'){
			city='';
		}
		getMessage();
	});
	//选择时间
    $("#time1").datepicker({
        autoclose:true,
        format:"yyyymmdd",
        
    }).on('changeDate', getTime1);
    $("#time1").val(monthId);
    //选择折线时间
    $("#time2").datepicker({
        autoclose:true,
        format:"yyyymmdd"
    }).on('changeDate', getTime2);
    $("#time2").val(monthId);
    /*切换最下面折线图按钮active状态*/
    $(".btn-group .btn").click(function(){
    	if($(this).hasClass("active")){
    		$(this).removeClass("active");
    		getlineChartData();
    	}else{
    		$(".btn.active").removeClass("active");
            $(this).addClass("active");
            getUserOrFlowtData($(this).html());
    	}
//    	$(".btn.active").removeClass("active");
//        $(this).addClass("active");
//        getUserOrFlowtData($(this).html());
    })
    $("#moreApps").click(function(){
    	showPiForm();
    })
})
var labelFlag='';//选择标签的级数 二级为1  一级为0
//选择的标签名称
var sntest2=function(){
	topshowLabelTree.show(function(obj){
	    label_name=obj.labelName;
		var lvl=obj.level;
		var label_code= obj.labelCode;
		
		$("#labelCodeHidden").val(label_code);
		$("#labelCodeHidden_form_param").val(label_code);//弹出form表单中用,参数用
		
		if(lvl=='1'){
			$("#labelNameOverView").val(label_name);
			//getMessage();
			label_name2='';
			label_name1=label_name;
			getMessage();
			
		}else{
			$("#labelNameOverView").val(label_name);
			//getMessage();
			label_name1='';
			label_name2=label_name;
			getMessage();
		}
		
	})
}


/*选择的标签名称
 * 通过form表单中查询标签
 */
var getLableCode_byForm=function(){
	topshowLabelTree.show(function(obj){
	    label_name=obj.labelName;
		var lvl=obj.level;
		var label_code= obj.labelCode;
		
		$("#labelCodeHidden_form").val(label_code);//弹出form表单中用
		
		if(lvl=='1'){
			$("#labelNameOverView_form").val(label_name);//弹出form表单中用
			label_name2='';
			label_name1=label_name;
			getMessage();
			
		}else{
			$("#labelNameOverView_form").val(label_name);//弹出form表单中用
			label_name1='';
			label_name2=label_name;
			getMessage();
		}
	})
}


//时间
function getTime1(ev){
	var year=ev.date.getFullYear().toString();
	var month=(ev.date.getMonth()+1)>9?(ev.date.getMonth()+1).toString():("0"+(ev.date.getMonth()+1).toString());
	var day=ev.date.getDate()>9?ev.date.getDate().toString():("0"+ev.date.getDate().toString());
	monthId=year+""+month+""+day;
	$("#time1").val(monthId);
	$("#time2").val(monthId);
	getMessage();
	
	
}
function getTime2(ev){
	var year=ev.date.getFullYear().toString();
	var month=(ev.date.getMonth()+1)>9?(ev.date.getMonth()+1).toString():("0"+(ev.date.getMonth()+1).toString());
	var day=ev.date.getDate()>9?ev.date.getDate().toString():("0"+ev.date.getDate().toString());
	monthId=year+""+month+""+day;
	//$("#time1").val(monthId);
	$("#time2").val(monthId);
	//getlineChartData();
	getUserOrFlowtData("次数");
}
//从后台获取数据
function getMessage(){
	
	//获取饼图和柱图的数据
	$.ajax({
		"data":{"monthId":monthId,city:city,orderFlag:orderFlag},
		"url" : $.cxt + "/industryOverview/getCategoricalData", 
		"type":"POST",
		"dataType":"json",
		"success":function(data){
	
			var data=JSON.parse(data);
			data=data.data;
			var legend_arr= new Array();
			var user_total_arr= new Array();
			var flow_arr= new Array();
			var user_total= new Array();
			var flow= new Array();
			var Max=new Array();
			var obj={};//暂时更改
			for(i in data){
				if(data[i].prodTypeName=='休闲娱乐'){  //暂时更改
					
					obj={value:data[i].allUserTotal,name:data[i].prodTypeName};   //暂时更改
				}
				legend_arr[legend_arr.length] = data[i].prodTypeName;
				//饼图
				user_total_arr[user_total_arr.length]={value:data[i].allUserTotal,name:data[i].prodTypeName};
				Max.push(data[i].allUserTotal);
				flow_arr[flow_arr.length]={value:data[i].allFlow,name:data[i].prodTypeName};
				//横向柱状图
				user_total[user_total.length] = data[i].allUserTotal;
				flow[flow.length] = data[i].allFlow;
			}
			bing_echarts(legend_arr,user_total_arr,flow_arr);
			//zhu_echarts(legend_arr,user_total,flow);
		    
			var Maxuser=Math.max.apply(null,Max);
			var index=0;
			for(var i=0;i<user_total_arr.length;i++){
				if(Maxuser==user_total_arr[i].value){
					index=i;
					break;
				}
			}
			/*var obj=user_total_arr[index];   //以后可能恢复
			getInitMaxValue(obj);*/
			obj.orderFlag='1';
			getInitMaxValue(obj);  //暂时更改
			
			

		}
	});
	//获取APPtop10数据  //用户占比  

	var inputLabel=$("#labelNameOverView").val();
	if(!inputLabel){
		//不选择标签时
		$.ajax({
			"data":{"monthId":monthId,"cityId":city},
			"url" : $.cxt + "/industryOverview/getTopList", 
			"type":"POST",
			"dataType":"json",
			"success":function(data){
				for(var i=1;i<=10;i++){
					var chartDom = document.getElementById('top'+i);
					echarts.dispose(chartDom);
				}
				var data=JSON.parse(data);
				data=data.data;
				if(data.length>0){
					for(i in data){				
						var j=parseInt(i)+1;
						var prodName=strlength(data[i].prodName);
						top_echarts('top'+j,data[i].userTotal,data[i].percentUser,prodName);
					}
					
					
					/*var input= $("#labelNameOverView").val();
					if(input=="" || input=='全部'){
						$("#top_name").html("所有APP TOP10");			
					}else{
						$("#top_name").html(input+"类APP TOP10");		
					}*/
				}
				if(label_name=="" || label_name=='全部'){
					$("#top_name").html("所有APP TOP10");			
				}else{
					$("#top_name").html(label_name+"类APP TOP10");		
				}
			}
		});
	}else{
		//选择标签时
		var labelCode=$("#labelCodeHidden").val();
		$.ajax({
			"data":{"monthId":monthId,"labelCode":labelCode,"cityId":city},
			"url" : $.cxt + "/industryOverview/selectTopListChoose", 
			"type":"POST",
			"dataType":"json",
			"success":function(data){
				for(var i=1;i<=10;i++){
					var chartDom = document.getElementById('top'+i);
					echarts.dispose(chartDom);
				}
				var data=JSON.parse(data);
				data=data.data;
				if(data.length>0){
					for(i in data){				
						var j=parseInt(i)+1;
						var prodName=strlength(data[i].prodName);
						
						top_echarts('top'+j,data[i].userTotal,data[i].percentUser,prodName)
					}
					
					
					/*var input= $("#labelNameOverView").val();
					if(input=="" || input=='全部'){
						$("#top_name").html("所有APP TOP10");			
					}else{
						$("#top_name").html(input+"类APP TOP10");		
					}*/
				}
				if(label_name=="" || label_name=='全部'){
					$("#top_name").html("所有APP TOP10");			
				}else{
					$("#top_name").html(label_name+"类APP TOP10");		
				}
			}
		});
	}
	
	//获取地图数据 
	var mapLabel=$("#labelNameOverView").val();
	var mapLabelFlag='0';
	if(!mapLabel){
		mapLabelFlag='0'
	}else{
		mapLabelFlag='1'
	}
	$.ajax({
		"data":{"dateId":monthId,"city":city,"labelName1":label_name1,"labelName2":label_name2,mapLabelFlag:mapLabelFlag},
		"url" : $.cxt + "/industryOverview/getMapDataList", 
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
					user_total[user_total.length]={name: data[i].city, value: data[i].userTotal};
					flow[flow.length]={name: data[i].city, value: data[i].totalFlow};
					maxUserTotal.push(data[i].userTotal);
					//表格数据
					var tr='<tr>' +
							'<td>'+data[i].city+'</td>'+ 
							'<td>'+data[i].userTotal+'</td>'+ 
							'<td>'+data[i].userTotalPer+'%'+'</td>'+ 
							'<td>'+data[i].totalFlow+'</td>'+ 
							'<td>'+data[i].totalFlowPer+'%'+'</td>'+ 
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
			if(label_name=="" || label_name=='全部'){
				$("#map_name").html("所有类地域分析")			
			}else{
				$("#map_name").html(label_name+"类地域分析")		
			}
		}
	});
	getUserOrFlowtData("次数");
}
//获取折线图数据（折线图数据现在不能根据cityid变化而变化）
function getlineChartData(){
	$.ajax({
		"data":{"monthId":monthId,"labelName1":label_name1,"labelName2":label_name2,"city":city},
		"url" : $.cxt + "/industryOverview/getLineChartList", 
		"type":"POST",
		"dataType":"json",
		"success":function(data){
			var data=JSON.parse(data);
			data=data.data;
			var hour=new Array();
			var all_user_total=new Array();
			var all_flow=new Array();
			for(i in data){
				hour[hour.length] = data[i].hourId
				all_user_total[all_user_total.length] = data[i].allUserCount;
				all_flow[all_flow.length] = (data[i].allFlow/1024).toFixed(2);
			}
			zhe_echarts(hour,all_user_total,all_flow)
			if(label_name=="" || label_name=='全部'){
				$("#zhe_name").html("所有类时段分布")			
			}else{
				$("#zhe_name").html(label_name+"类时段分布")		
			}
		}
	});
}
//获取折线的用户或流量数据
function getUserOrFlowtData(type){
	
	var inputLabel=$("#labelNameOverView").val();
	if(inputLabel=="" || inputLabel=='全部'){
		$("#zhe_name").html("所有类时段分布");			
	}else{
		$("#zhe_name").html(inputLabel+"类时段分布");		
	}
	$.ajax({
		"data":{"monthId":monthId,"labelName1":label_name1,"labelName2":label_name2,"city":city},
		"url" : $.cxt + "/industryOverview/getLineChartList", 
		"type":"POST",
		"dataType":"json",
		"success":function(data){
			var data=JSON.parse(data);
			data=data.data;
			var hour=new Array();
			var all_user_total=new Array();
			var all_flow=new Array();
			for(i in data){
				if(type=="次数"){
					hour[hour.length] = data[i].hourId
					all_user_total[all_user_total.length] = data[i].allUserCount;
				}else{
					hour[hour.length] = data[i].hourId
					all_flow[all_flow.length] = (data[i].allFlow/1024).toFixed(2);
				}			
			}
			zhe_echarts(hour,all_user_total,all_flow);
			
		}
	});
}
//饼图
function bing_echarts(legend_arr,user_total_arr,flow_arr) {
	var chartDom = document.getElementById('user_echarts1');
	echarts.dispose(chartDom);
	var myChart = echarts.init(chartDom);
    var option = {
    		//color:[ '#f9d237','#36f3d3','a96fff','fad472','#E066FF'], 
    	    tooltip : {
    	    	trigger: 'item',
    	    	
    	        formatter: "{a} <br/>{b} : {c} ({d}%)",
    	    	
    	    },
    	    legend: {
//    	        orient : 'vertical',
    	        x : 'right',
    	        data:legend_arr
    	      
    	    },
    	    toolbox: {
    	        show : true,
    	    },
    	    calculable : false,
    	    //color:[ '#f9d237','#36f3d3','limegreen','skyblue','#396fff','#FFC0CB'], 
    	    //color:[ '#F38A7C','#F7AB85','green','blue','#F8A1A1'], 
    	    color:[ '#ff6666','#ffcc66','#0285cf','#33cc99','#ff9900','#FFC0CB'], 
    	    series : [
    	        {
    	            name:'流量占比',//a
    	            type:'pie',
    	            selectedMode: 'single',
    	            radius : [0, 90],
    	            x: '20%',
    	            width: '40%',
    	            funnelAlign: 'right',
    	            max: 1548,
    	            itemStyle : {
    	                normal : {
    	                    label : {
    	                    	 show : false,
    	                        position : 'inner'
    	                    },
    	                    labelLine : {
    	                        show : false
    	                    }
    	                }
    	            },
    	            data:flow_arr  //{b(name):c(value)}  d占比
    	        },
    	        {
    	            name:'用户占比',
    	            type:'pie',
    	            radius : [120, 150],
    	            x: '60%',
    	            width: '35%',
    	            funnelAlign: 'left',
    	            max: 1048,    	            
    	            data:user_total_arr,
    	           
    	        }
    	    ]
    	};                    
    myChart.setOption(option);
    myChart.on("click", linkBar);
};

//横向柱状图
function zhu_echarts(legend_arr,user_total,flow,orderFlag) {
	var showFlag={};
	if(orderFlag=='1'){
		showFlag={'用户数': true,'流量数': false};
	}
	if(orderFlag=='2'){
		showFlag={'用户数': false,'流量数': true};
	}
	var chartDom = document.getElementById('user_echarts2');
	echarts.dispose(chartDom);
	var myChart = echarts.init(chartDom);
    var option = {
    	    tooltip : {
    	        trigger: 'axis',
    	        formatter: function(params) {
    	        	//console.log(params);
    	        	
                    var html=params[0].name+'<br/>';
                    
                    for(var i=0;i<params.length;i++){
                    	if(params[i].seriesName=='流量数'){
                    		html+=params[i].seriesName+':'+(params[i].value/1024).toFixed(2)+'G<br/>';
                    	}
                    	if(params[i].seriesName=='用户数'){
                    		html+=params[i].seriesName+':'+params[i].value+'户<br/>';
                    	}
                    	
                    	
                    }
                    return html;
    	        	
                }
    	    },
    	    legend: {
//    	    	orient : 'vertical',
    	        x : 'right',
    	        data:['用户数', '流量数'],
    	        selected: showFlag
    	    },
    	    toolbox: {
    	        show : true,
    	    },
    	    calculable : true,
    	    color:[ '#5eb6f9','#34c36d'],
    	    xAxis : [
    	        {
    	        	show: false,
    	            type : 'value',
    	        }
    	    ],
    	    yAxis : [
    	        {
    	            type : 'category',
    	            axisLine: {
    	                show: false
    	              }, // y轴坐标轴线隐藏,注意不是y轴隐藏,我们还要显示文字的
    	            axisTick: [{
    	                show: false
    	            }],// y轴坐标轴刻度隐藏
    	            data : legend_arr
    	        }
    	    ],
    	    series : [
    	        {
    	            name:'用户数',
    	            type:'bar',
    	            itemStyle : { normal: {label : {show: true, position: 'right',textStyle:{color:'green'}, 	            
    	            }}},
    	            barWidth : 16,
    	            data:user_total
    	        },
    	        {
    	            name:'流量数',
    	            type:'bar',
    	            itemStyle : { normal: {label : {show: true, position: 'right',textStyle:{color:'green'},
    	            	formatter: function(params) {
   	    	        	// console.log(params);
   	    	        	 return (params.value/1024).toFixed(2)+'G';
   	    	        	}   	            
    	            }}},
    	            barWidth : 16,
    	            data:flow 
    	        }
    	    ]
    }
    myChart.setOption(option);
};
//top环形图
var labelTop = {
	    normal : {
	    	color:'#5CACEE',
	        label : {
	            show : true,
	            position : 'center',
	            formatter : '{b}',
	            textStyle: {
	                baseline : 'bottom'
	            }
	        },
	        labelLine : {
	            show : false
	        }
	    }
	};
var labelFromatter = {
		normal : {
			label : {
				show : false,
				formatter : function(params) {
					
					return Math.round((100 - params.percent)*100)/100 + '%'
				},
				textStyle : {
					baseline : 'middle',
					fontSize:14,
					color:'#7F7F7F',
					fontWeight: 'bold',
					align : 'center' 
				}
			}
		},
	}
var labelBottom = {
		normal : {
			color : '#CAE1FF',
			label : {
				show : true,
				position : 'center',
			},
			labelLine : {
				show : false
			}
		},
		emphasis : {
			color : '#CAE1FF'
		}
	};                   
function top_echarts(id,userTotal,percentUser,prodName){
	var myRangeChart = echarts.init(document.getElementById(id));
	option = {
			 title: {
			        x: 'center',
			        y: '60',
			        itemGap: 50,
			        textStyle : {
			            color : '#666',
			            fontSize : 8,
			            fontWeight : 'normal'
			        },
			        subtextStyle : {
			            color : '#de4751',
			            fontSize : 8
			        }
			    },    
			series : [ {
				type : 'pie',
				center: ['50%', '50%'],
				radius : [ '60', '70' ],
				x : '0%',
				itemStyle : labelFromatter,
				data : [ {
					name : 'other',
					value :userTotal/percentUser-userTotal, //一会调试userTotal/percentUser-userTotal
					//value :percentUser,
					itemStyle : labelBottom
				}, {
					name : prodName,
					value :userTotal,
					itemStyle : labelTop
				} ]
			} ]
		};
	myRangeChart.setOption(option);
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
                	zoom: 1.2,
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
//折线图
function zhe_echarts(hour,all_user_total,all_flow) {
	
    var chartDom = document.getElementById('zhe_echarts');
	echarts.dispose(chartDom);
	var myChart = echarts.init(chartDom);
    var option = {
    	    tooltip : {
    	        trigger: 'axis',
    	        formatter: function(params) {
                    var html=params[0].name+'时<br/>';
                    for(var i=0;i<params.length;i++){
                    	if(params[i].seriesName=='次数'){
                    		html+=params[i].seriesName+':'+params[i].value+'次<br/>';
                    	}
                    	if(params[i].seriesName=='流量数'){
                    		html+=params[i].seriesName+':'+params[i].value+'G<br/>';
                    	}
                    	
                    	
                    }
                    return html;
    	        	
                }
    	        
    	        
    	    },
    	    legend: {
    	        x : 'center',
    	        icon: "line",
    	        itemWidth: 30,
    	        data:['次数', '流量数']
    	    },
    	    toolbox: {
    	        show : true
    	    },
    	    calculable : true,
    	    xAxis : [
    	        {
    	            type : 'category',
    	            boundaryGap : false,
    	            axisLine: {
    	                show: false
    	              }, 
    	            axisTick: [{
    	                show: false
    	            }],
    	            data : hour
    	        }
    	    ],
    	    yAxis : [
    	        {
    	            type : 'value',
    	            axisLine: {
    	                show: false
    	              }, 
    	            axisTick: [{
    	                show: false
    	            }]
    	           /* axisLabel : {  //新增的Y轴显示
    	                show:true,
    	                interval: 'auto',    // {number}
    	               
    	               
    	                formatter: '{value} ',    // Template formatter!
    	               
    	                
    	            },*/
    	        }
    	    ],
    	    series : [
    	        {
    	            name:'次数',
    	            type:'line',
    	            symbolSize:'none',   //拐点圆的大小
                    color:['blue'],  //折线条的颜色
    	            data:all_user_total,
    	            //itemStyle : { normal: {label : {show: true}}} // 显示拐点数据
    	        },
    	        {
    	            name:'流量数',
    	            type:'line',
    	            symbolSize:'none',   //拐点圆的大小
                    color:['red'],  //折线条的颜色
    	            data:all_flow,
    	            //itemStyle : { normal: {label : {show: true}}}
    	        }
    	    ]
    	};
    myChart.setOption(option);
};
//弹出窗口(更多app排名)
function showPiForm() {
	
	topwindow.showWindow({
		//窗口名称
		title : "查看app排名",
		//参数
		data : {"monthId":monthId,"city":city,"labelName1":label_name1,"labelName2":label_name2},
		//url
		url : $.cxt + "/industryOverview/showForm",
		bottons : [{
			title : "确认",
			fun : function() {
				topwindow.removeWindow();
				}
		}]
	});
}

function linkBar(param){
	
	if(param.seriesName=='用户占比'){
		
		var obj={};
		obj.name=param.name;
		obj.orderFlag='1';
		getInitMaxValue(obj);
	}else{
		
		var obj={};
		obj.name=param.name;
		obj.orderFlag='2';
		getInitMaxValue(obj);
	}
}
function getInitMaxValue(obj){
	//获取默认最大的数据
	var orderFlag =obj.orderFlag;
	if(obj==null ||obj==undefined){
		return false;
	}
	var prodTypeName=obj.name;
	var orderFlag=obj.orderFlag;
	$.ajax({
		"data":{"monthId":monthId,"prodTypeName":prodTypeName,"areaId":city,orderFlag:orderFlag},
		"url" : $.cxt + "/industryOverview/getInitMaxValue", 
		"type":"POST",
		"dataType":"json",
		"success":function(data){
		
			var data=JSON.parse(data);
			data=data.data;
			var legend_arr=[];
			var user_total=[];
			var flow=[];
			var top6=data.length>7 ? top6=6:top6=data.length;
			for(var i=0;i<top6;i++){
				legend_arr[i]=data[i].labelName1;
				user_total[i]=data[i].allUserTotal;
				flow[i]=data[i].allFlow;
			}
			zhu_echarts(legend_arr,user_total,flow,orderFlag);
			
		}
	});
}
function strlength(str){
    var len = 0;
    for (var i=0; i<str.length; i++) { 
     var c = str.charCodeAt(i); 
    //单字节加1 
     if ((c >= 0x0001 && c <= 0x007e) || (0xff60<=c && c<=0xff9f)) { 
       len++; 
     } 
     else { 
      len+=2; 
     }
     if (len>14){ //默认7个汉字
    	 str=str.substring(0,i);
    	 str+='..'
    	 break;
     }
    }
    
    return str;
}
