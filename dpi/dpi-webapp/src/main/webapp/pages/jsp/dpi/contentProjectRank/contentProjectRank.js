var time='';
var cityId = '';
var prod_id='';
var prod_name='';
var label_name1='影视';
var mapJson='';
var mapType='';
$(function(){
	$.ajax({
		"data":{},
		"url" : $.cxt + "/sysopt/getSysMonth", 
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
	
	//初始化app排行
    initDataGrid_app();
	//初始化内容排行
	initDataGrid_content();
	getMessage();
    //点击标签
    $('#u li').click(function() {
    	prod_id='';
    	reloadJqGrid(this);
    }); 
    
	//选择城市
	$("#cityId").chosen({
		disable_search : true,
	}).change(function(option, checked) {
		cityId=$("#cityId").val();
		getTime(null);
	});
    
	//选择时间
    $("#time").datepicker({
        autoclose:true,
        startView: 1,
        maxViewMode: 1,
        minViewMode: 1,
        todayHighlight: true,
        format:"yyyymm",
        
    }).on('changeDate', getTime);
    $("#time").val(time);
});
//格式化时间，重新加载数据
function getTime(ev){
	time=$('#time').val()
	prod_id='';
	$("#u .active").removeClass("active");
	$("#video").addClass("active");
	label_name1 ='影视';
	//显示隐藏的第二个分类
	$('#hide').show();
	//单独加载应用排行
	$("#grid-table1").jqGrid('setGridParam',{
		 postData : {monthId:time,cityId:cityId,labelName1:label_name1,prodId:prod_id}
   }).trigger("reloadGrid");
	reloadJqGrid ("");
}
function getMessage(){
	//获取用户统计数据
	$.ajax({
		"data":{monthId:time, cityId:cityId, labelName1:label_name1,prodId:prod_id},
		"url" : $.cxt + "/contentProjectRank/getUserData", 
		"type":"POST",
		"dataType":"json",
		"success":function(data){
			var data=JSON.parse(data);
			data=data.data;
			if(data==undefined){
				$('#p1').html('用户：'+0+'户');
				$('#p2').html('占比：'+0);
				
				$('#p3').html('流量：'+0+'M');
				$('#p4').html('占比：'+0);
				
				$('#p5').html('次数：'+0+'次');
				$('#p6').html('占比：'+0);
				
				$('#p7').html('内容：'+0+'个');
			}else{
				var d1=check(data.userTotal);
				var d2=check(data.userTotalPercent);
				if(d1.length<5){
					$('#p1').html('用户：'+d1+'户 ');					
				}else if(d1.length>=5&&d1.length<9){
					var f=(parseFloat(d1)/10000).toFixed(2);
					$('#p1').html('用户：'+f+'万');	
				}else if(d1.length>=9){
					var f=(parseFloat(d1)/(10000*10000)).toFixed(2);
					$('#p1').html('用户：'+f+'亿');	
				}				
				$('#p2').html('占比：'+d2);
				
				var d3=check(data.totalFlow);
				var d4=check(data.totalFlowPercent);
				if(d3.length<4){
					$('#p3').html('流量：'+d3+'MB');					
				}else if(d3.length>=4&&d3.length<8){
					var f=(parseFloat(d3)/1024).toFixed(2);
					$('#p3').html('流量：'+f+'GB');	
				}else if(d3.length>=8){
					var f=(parseFloat(d3)/(1024*1024)).toFixed(2);
					$('#p3').html('流量：'+f+'TB');	
				}
				$('#p4').html('占比：'+d4);
				
				var d5=check(data.userCount);
				var d6=check(data.userCountPercent);
				if(d5.length<5){
					$('#p5').html('次数：'+d5+'次');					
				}else if(d5.length>=5&&d5.length<9){
					var f=(parseFloat(d5)/10000).toFixed(2);
					$('#p5').html('次数：'+f+'万');	
				}else if(d5.length>=9){
					var f=(parseFloat(d5)/(10000*10000)).toFixed(2);
					$('#p5').html('次数：'+f+'亿');	
				}	
				$('#p6').html('占比：'+d6);
				
				var d7=check(data.totalCentent);
				if(d7.length<5){
					$('#p7').html('内容：'+d7+'个');					
				}else if(d7.length>=5&&d7.length<9){
					var f=(parseFloat(d7)/10000).toFixed(2);
					$('#p7').html('内容：'+f+'万');	
				}else if(d7.length>=9){
					var f=(parseFloat(d7)/(10000*10000)).toFixed(2);
					$('#p7').html('内容：'+f+'亿');	
				}	
			}
		}
	});
	//获取内容柱状图数据
	$.ajax({
		"data":{monthId:time, cityId:cityId, labelName1:label_name1,prodId:prod_id},
		"url" : $.cxt + "/contentProjectRank/getContentCountList", 
		"type":"POST",
		"dataType":"json",
		"success":function(data){
			var data=JSON.parse(data);
			data=data.data;
			var cata_arr=new Array();
			var user_arr=new Array();
			var flow_arr=new Array();
						
			var total_1=0;
			var total_2=0;
			for(var i in data){
				if(label_name1=='影视'){
					//影视类  商品分成六类
					if(data[i].contentLabelName=='电视剧'||data[i].contentLabelName=='电影'||
					   data[i].contentLabelName=='短视频'||data[i].contentLabelName=='综艺'||
					   data[i].contentLabelName=='动漫'){
						cata_arr[cata_arr.length]=data[i].contentLabelName;
						user_arr[user_arr.length]=data[i].userTotal;
						flow_arr[flow_arr.length]=data[i].totalFlow;
					}else{
						total_1=total_1+parseFloat(data[i].userTotal);
						total_2=total_2+parseFloat(data[i].totalFlow);
					}					
				}else{
					if(data[i].contentLabelName){
					//其它类  商品显示前十
						if(cata_arr.length<10){
							cata_arr[cata_arr.length]=data[i].contentLabelName;
							user_arr[user_arr.length]=data[i].userTotal;
							flow_arr[flow_arr.length]=data[i].totalFlow;
						}
					}
				}
			}
			//影视类 商品添加其它
			if(label_name1=='影视'){
				cata_arr[cata_arr.length]='其它';
				user_arr[user_arr.length]=total_1;
				flow_arr[flow_arr.length]=total_2;
			}
			
			initRightBar2(cata_arr,user_arr,flow_arr);
		}
	});
	//获取分类用户柱状图数据
	$.ajax({
		"data":{monthId:time, cityId:cityId, labelName1:label_name1,prodId:prod_id},
		"url" : $.cxt + "/contentProjectRank/getCatagoryCountList", 
		"type":"POST",
		"dataType":"json",
		"success":function(data){
			var data=JSON.parse(data);
			data=data.data;
			var cata_arr=new Array();
			var user_arr=new Array();
			var flow_arr=new Array()
			for(var i in data){
				if(data[i].catagory){
					cata_arr[cata_arr.length]=data[i].catagory;
					user_arr[user_arr.length]=data[i].userTotal;
					flow_arr[flow_arr.length]=data[i].totalFlow;
				}
			}
			initRightBar3(cata_arr,user_arr,flow_arr);
		}
	});
	//不联动APP
	//获取折线图数据
	$.ajax({
		"data":{monthId:time, cityId:cityId, labelName1:label_name1},
		"url" : $.cxt + "/contentProjectRank/getLineChartList", 
		"type":"POST",
		"dataType":"json",
		"success":function(data){
			var data=JSON.parse(data);
			data=data.data;
			var hour=new Array();
			var flow_arr=new Array();
			var count_arr=new Array();
			for(i in data){
				flow_arr[flow_arr.length] = data[i].totalFlow;
				count_arr[count_arr.length] = data[i].userCount;
				hour[hour.length] = data[i].hourId;
			}
			if(label_name1=='影视'){
				initLine1(hour,flow_arr,'流量数');
			}else{
				initLine1(hour,count_arr,'次数');
			}
		}
	});
	//用户画像
	//获取APP排名
	$.ajax({
		"data":{labelName1:label_name1, cityId:cityId, monthId:time},
		"url" : $.cxt + "/contentProjectRank/selectapptop10", 
		"type":"POST",
		"dataType":"json",
		"success":function(data){
			$('#app').empty();
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
				$("#app").append(tr);
			}
			meigui(name_arr,user_arr);
		}
	});
	//获取性别年龄
	$.ajax({
		"data":{"labelName1":label_name1,"monthId":time, cityId:cityId, "prodId":prod_id},
		"url" : $.cxt + "/contentProjectRank/selectage", 
		"type":"POST",
		"dataType":"json",
		"success":function(data){
			var data=JSON.parse(data);		
			if(data.code!="0"){
				$("#jin").css('width','50%');
				$("#manPercent").html('0%');
				$("#womanPercent").html('0%');
				return;
			}
			if(data.data==undefined){
				$("#jin").css('width','50%');
				$("#manPercent").html('0%');
				$("#womanPercent").html('0%');
				var chartDom = document.getElementById('columnecharts');
				echarts.dispose(chartDom);
				return;
			}
			if(data.data.manPercent==undefined&&data.data.womanPercent==undefined){
				$("#jin").css('width','50%');
				$("#manPercent").html('0%');
				$("#womanPercent").html('0%');
			}
			$("#jin").css('width',data.data.manPercent);
			$("#manPercent").html(data.data.manPercent);
			$("#womanPercent").html(data.data.womanPercent);		
			getAgeDistribution(data);			
		}
	});	
	//获取黑龙江地区用户分布
	$.ajax({
		"data":{"monthId":time, cityId:cityId, "labelName1":label_name1,"prodId":prod_id},
		"url" : $.cxt + "/contentProjectRank/getMapDataList", 
		"type":"POST",
		"dataType":"json",
		"success":function(data){
			var data=JSON.parse(data);
			data=data.data;
			var user_total=new Array();
			var flow=new Array();
			$("#thead").empty();
			$("#tbody").empty();
			if(label_name1=='影视'){
				var tr='<tr>' +
						'<th>区域</th>'+ 
						'<th>用户数(户)</th>'+ 
						'<th>用户占比</th>'+ 
						'<th>流量(GB)</th>'+ 
						'<th>流量占比</th>'+ 
						'<th>人均流量(MB)</th>'+ 
					   '</tr>';
				$("#thead").append(tr);
			}else{
				var tr='<tr>' +
				'<th>区域</th>'+ 
				'<th>用户数(户)</th>'+ 
				'<th>用户占比</th>'+ 
				'<th>流量(GB)</th>'+ 
				'<th>流量占比</th>'+ 
				'<th>人均流量(MB)</th>'+ 
			   '</tr>';
				$("#thead").append(tr);
			}
			var chartDom = document.getElementById('map_echarts');
			echarts.dispose(chartDom);
			var myChart = echarts.init(chartDom);
			if(data.length>0){
				var maxUserTotal=[];
				for(i in data){				
					user_total[user_total.length]={name: data[i].areaName, value: data[i].userTotal};
					flow[flow.length]={name: data[i].areaName, value: data[i].totalFlow};
					maxUserTotal.push(data[i].userTotal);
					//表格数据
					if(label_name1=='影视'){
						var tr='<tr>' +
								'<td>'+data[i].areaName+'</td>'+ 
								'<td>'+data[i].userTotal+'</td>'+ 
								'<td>'+data[i].userPercent+'%</td>'+ 
								'<td>'+data[i].totalFlow+'</td>'+ 
								'<td>'+data[i].flowPercent+'%</td>'+ 
								'<td>'+data[i].perCapitaFlow+'</td>'+ 
								'</tr>'
						$("#tbody").append(tr);
					}else{
						var tr='<tr>' +
								'<td>'+data[i].areaName+'</td>'+ 
								'<td>'+data[i].userTotal+'</td>'+ 
								'<td>'+data[i].userPercent+'%</td>'+ 
								'<td>'+data[i].totalFlow+'</td>'+ 
								'<td>'+data[i].flowPercent+'%</td>'+ 
								'<td>'+data[i].perCapitaFlow+'</td>'+ 
								'</tr>'
						$("#tbody").append(tr);
					}
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
//检查数据是否存在
function check(data){
	if(data){
		return data+'';
	}else{
		return '0';
	}
}
//重新加载数据
function reloadJqGrid (obj) {
	if(obj){
		$("#u .active").removeClass("active");
		$(obj).addClass("active");
	}
	
	//隐藏小说的第二个分类
	if($("#u .active").text()=='小说'){
		$('#hide').hide();
	}else{
		$('#hide').show();
	}
	
	changeLabelName();
	//点击应用时不执行查询
  	if(obj){
		$("#grid-table1").jqGrid('setGridParam',{
			 postData : {monthId:time,cityId:cityId,labelName1:label_name1,prodId:prod_id}
	    }).trigger("reloadGrid");
	}
	$("#grid-table2").jqGrid('setGridParam',{
		 postData : {monthId:time,cityId:cityId,labelName1:label_name1,prodId:prod_id}
    }).trigger("reloadGrid");
	getMessage();
}
//更改标签名称
function changeLabelName(){
	if( $("#u .active").text()){
		label_name1=$("#u .active").text();
	}	
	var label_name1_2='';
	//直接点击应用改变名称
	if(label_name1=='影视'){
		label_name1_2='视频';
	}
	if(label_name1=='小说阅读'){
		label_name1_2='小说';
	}
	if(label_name1=='网上购物'){
		label_name1_2='电商商品';
	}
	//点击标签改变名称
	if(label_name1=='视频'){
		label_name1='影视';
		label_name1_2='视频';
	}
	if(label_name1=='小说'){
		label_name1='小说阅读';
		label_name1_2='小说';
	}
	if(label_name1=='电商商品'){
		label_name1='网上购物';
		label_name1_2='电商商品';
	}
	if(label_name1=='音乐'){
		label_name1='音乐';
		label_name1_2='音乐';
	}
	if(label_name1==''&&prod_id==''){
		$('#title_1').html('APP TOP10');
		$('#title_2').html('用户数统计');
		$('#title_3').html('内容排行榜');
		$('#title_4').html('各分类用户分布');
		$('#title_5').html('分类用户数分布图');
		$('#title_6').html('近12个月趋势分布');
		$('#title_7').html('用户画像');
		$('#title_8').html('性别年龄占比');
		$('#title_9').html('地域分析');
		$('#title_10').html('关联APP TOP10');
	}else if(prod_id==''){
		$('#title_1').html(label_name1_2+'类APP TOP10');
		$('#title_2').html(label_name1_2+'类用户数统计');
		$('#title_3').html(label_name1_2+'类内容排行榜');
		$('#title_4').html(label_name1_2+'各分类用户分布');
		$('#title_5').html(label_name1_2+'分类用户数分布图');
		if(label_name1=='影视'){
			$('#title_6').html(label_name1_2+'类近12个月趋势分布');
		}else{
			$('#title_6').html(label_name1_2+'类近12个月趋势分布');
		}
		$('#title_7').html(label_name1_2+'类用户画像');
		$('#title_8').html(label_name1_2+'类性别年龄占比');
		$('#title_9').html(label_name1_2+'类地域分析');
		$('#title_10').html(label_name1_2+'类关联APP TOP10');
	}else{
		$('#title_1').html(prod_name);
		$('#title_2').html(prod_name+'用户数统计');
		$('#title_3').html(prod_name+'内容排行榜');
		$('#title_4').html(prod_name+'各分类用户分布');
		$('#title_5').html(prod_name+'分类用户数分布图');
		if(label_name1=='影视'){
			$('#title_6').html(label_name1_2+'类近12个月趋势分布');
		}else{
			$('#title_6').html(label_name1_2+'类近12个月趋势分布');
		}
		$('#title_7').html(prod_name+'用户画像');
		$('#title_8').html(prod_name+'性别年龄占比');
		$('#title_9').html(prod_name+'地域分析');
		$('#title_10').html(prod_name+'关联APP TOP10');
	}
}
//内容柱状图
function initRightBar2(cata_arr,user_arr,flow_arr){
	var chartDom = document.getElementById('right2');
	echarts.dispose(chartDom);
	var myChart = echarts.init(chartDom);
    var option = {
        color : ['#54A4FB','#75D590'],
        tooltip : {
            trigger: 'axis',
            axisPointer : {
                type : 'shadow'
            },
            formatter: function(params) {	
                var html=params[0].name+'<br/>';                
                for(var i=0;i<params.length;i++){
                	if(params[i].seriesName=='流量数'){
//                		html+=params[i].seriesName+'：'+(params[i].value/1024).toFixed(0)+'G<br/>';
                		html+=params[i].seriesName+'：'+params[i].value+'GB<br/>';
                	}
                	if(params[i].seriesName=='用户数'){
                		html+=params[i].seriesName+'：'+params[i].value+'户<br/>';
                	}           	
                }
                return html;	        	
            }
        },
        legend: {
//	    	orient : 'vertical',
	        x : 'right',
	        data:['用户数', '流量数'],
	        selected: {
				'用户数': true,
				'流量数': false
			}
	    },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '8%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                data : cata_arr,
                axisTick: {
                    alignWithLabel: true
                },
                axisLabel: {  
                	   interval:0,  
                	   rotate:30               	   
                },
                axisLine:{
                    lineStyle:{
                        color:"#999",
                        opacity: 0
                    }
                },
            }
        ],
        yAxis :  [
            {
                type : 'value',
                axisLine:{
                    lineStyle:{
                        color:"#999",
                        opacity: 0
                    }
                },
                axisTick:{
                    lineStyle:{
                        color:"#999",
                        opacity: 0
                    }
                }
            }
        ],
        series : [
            {
                name:'用户数',
                type:'bar',
                barWidth: "20%",
                data:user_arr
            },{
                name:'流量数',
                type:'bar',
                barWidth: "20%",
                data:flow_arr
            }
        ]
    };
    myChart.setOption(option);
}
//分类用户柱状图
function initRightBar3(cata_arr,user_arr,flow_arr){
	var chartDom = document.getElementById('bar3');
	echarts.dispose(chartDom);
	var myChart = echarts.init(chartDom);
    var option = {
        color : ['#54A4FB','#75D590'],
        tooltip : {
            trigger: 'axis',
            axisPointer : {
                type : 'shadow'
            },
            formatter: function(params) {	
                var html=params[0].name+'<br/>';                
                for(var i=0;i<params.length;i++){
                	if(params[i].seriesName=='流量数'){
//                		html+=params[i].seriesName+'：'+(params[i].value/1024).toFixed(0)+'G<br/>';
                		html+=params[i].seriesName+'：'+params[i].value+'GB<br/>';
                	}
                	if(params[i].seriesName=='用户数'){
                		html+=params[i].seriesName+'：'+params[i].value+'户<br/>';
                	}           	
                }
                return html;	        	
            }
        },
        legend: {
//	    	orient : 'vertical',
	        x : 'right',
	        data:['用户数', '流量数'],
	        selected: {
				'用户数': true,
				'流量数': false
			}
	    },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '8%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                data : cata_arr,
                axisTick: {
                    alignWithLabel: true
                },
                axisLine:{
                    lineStyle:{
                        color:"#999",
                        opacity: 0
                    }
                },
                axisLabel: {  
             	   interval:0,  
             	   rotate:30
                }
            }
        ],
        yAxis :  [
            {
                type : 'value',
                axisLine:{
                    lineStyle:{
                        color:"#999",
                        opacity: 0
                    }
                },
                axisTick:{
                    lineStyle:{
                        color:"#999",
                        opacity: 0
                    }
                }
            }
        ],
        series : [
            {
                name:'用户数',
                type:'bar',
                barWidth: "20%",
                data:user_arr
            },{
                name:'流量数',
                type:'bar',
                barWidth: "20%",
                data:flow_arr
            }
        ]
    };
    myChart.setOption(option);
}

//折线图
function initLine1(hour,data,desc){
	var chartDom = document.getElementById('line1');
	echarts.dispose(chartDom);
	var myChart = echarts.init(chartDom);
    var option = {
        tooltip : {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
            },
	    	formatter: function(params) {
		        var html=params[0].name+'月<br/>';
		        if(desc=='流量数'){
		        	for(var i=0;i<params.length;i++){
			        	html+=params[i].seriesName+'：'+params[i].value+'GB<br/>';		        	
			        }
		        }else{
		        	for(var i=0;i<params.length;i++){
		        		html+=params[i].seriesName+'：'+params[i].value+'次<br/>';
		        	}
		        }
		        return html;
    	
	    	}
        },
        xAxis: {
            type: 'category',
            boundaryGap : false,
            axisTick: {
                alignWithLabel: true
            },
            axisLine:{
                lineStyle:{
                    color:"#999",
                    opacity: 0
                }
            },
            data:hour
        },
        yAxis: {
            type: 'value',
            axisLine:{
                lineStyle:{
                    color:"#999",
                    opacity: 0
                }
            },
            axisTick:{
                lineStyle:{
                    color:"#999",
                    opacity: 0
                }
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        series: [{
        	name:desc,
        	symbolSize:'none',
            data: data,
            type: 'line',
            lineStyle:{
                normal:{
                    color:"#1F93FF"
                }
            },
            itemStyle:{
                normal:{
                    color:"#1F93FF"
                }
            }
        }]
    };
    myChart.setOption(option);
}
//初始化app排行
function initDataGrid_app(){
	$('#grid-table1').jqGrid({
		url : $.cxt + "/contentProjectRank/getCategoricalAppList",
		postData:{monthId:time, cityId:cityId, labelName1:label_name1},
		datatype : "json",
		mtype : "POST",
		height : 290,
		autowidth : true,
		colNames : [ '','应用','用户量(户)','流量数(GB)','id' ,'一级标签名称'],
		colModel : [ 
			{name : 'id',align : 'center',index : 'id',width:40,sortable:false,editable : true,hidden : false, cellattr:addCellAttr },
			{name : 'prodName',align : 'left',index : 'prodName',sortable:false,editable : true,hidden : false},
			{name : 'userTotal',align : 'center',index : 'userTotal',sortable:false,editable : true},
			{name : 'totalFlow',align : 'center',index : 'totalFlow',sortable:false,editable : true},
			{name : 'prodId',align : 'center',index : 'prodId',sortable:false,editable : true,hidden : true},
			{name : 'labelName1',align : 'center',index : 'labelName1',sortable:false,editable : true,hidden : true},
        ],
//        rownumbers: true,
//        onSelectRow: function (rowId) {
//        	click_app(rowId);
//        },
		loadComplete : function() { //滚动条
			$("#grid-table1").closest(".ui-jqgrid-bdiv").css({
				'overflow-y' : 'auto',
				'overflow-x' : 'hidden'
			});
		},

	});
}
//初始化内容排行
function initDataGrid_content(){
	$('#grid-table2').jqGrid({
		url : $.cxt + "/contentProjectRank/getContentTopList",
		postData:{monthId:time, cityId:cityId, labelName1:label_name1},
		datatype : "json",
		mtype : "POST",
		height : 290,
		autowidth : true,	
		colNames : [ '','内容','分类','用户量(户)','流量数(MB)' ],
		colModel : [ 
			{name : 'id',align : 'center',index : 'id',width:40,sortable:false,editable : true,hidden : false,cellattr:addCellAttr},
			{name : 'name',align : 'left',index : 'name',sortable:false,editable : true,hidden : false},
			{name : 'contentLabelName',align : 'center',index : 'contentLabelName',sortable:false,editable : true,hidden : false},
			{name : 'userTotal',align : 'center',index : 'userTotal',sortable:false,editable : true},
			{name : 'totalFlow',align : 'center',index : 'totalFlow',sortable:false,editable : true},
        ],
//		rownumbers: true,
//		scrollOffset:10,
		loadComplete : function() { //滚动条
			$("#grid-table2").closest(".ui-jqgrid-bdiv").css({
				'overflow-y' : 'auto',
				'overflow-x' : 'hidden'
			});	
		},
	});
}

// 点击单个应用
// 由于后台模型不具备，暂时禁止点击
function click_app(rowId){
	prod_id = $("#grid-table1").jqGrid('getRowData',rowId).prodId;
	prod_name=$("#grid-table1").jqGrid('getRowData',rowId).prodName;
	label_name1=$("#grid-table1").jqGrid('getRowData',rowId).labelName1;
	var prod_id_arr=new Array('C2379', 'c1937', 'C2294', 'C2515','C83','C370','C946');
	for(i in prod_id_arr){
		if(prod_id_arr[i]==prod_id){			
			var all_id = $("#grid-table1").jqGrid('getDataIDs');
			for(id in all_id){
				if(rowId!=(parseInt(id)+1)){
					$("#grid-table1").setRowData(parseInt(id)+1,null,{display: 'none'});
				}
			}
			reloadJqGrid('')
		}
	}
}

//修改排名字体颜色
function addCellAttr(rowId){
	if(rowId==1){
		return "style='color:red;font-weight:bold;'";
	}
	if(rowId==2){
		return "style='color:#FFCC66;font-weight:bold;'";
	}
	if(rowId==3){
		return "style='color:green;font-weight:bold;'";
	}
	return "style='font-weight:bold';";
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
                center : ['50%', '50%'], //图表的中心位于容器的相对位置
                roseType : 'radius',        //面积模式
                itemStyle : {
                    normal: {

                        label: {                 //指示到模块的标签文字
                            show: true,
                            color:'black',
                            formatter: '{b}\n'
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
//性别年龄比例(柱状图)
function getAgeDistribution(data) {
	var chartDom = document.getElementById('columnecharts');
	echarts.dispose(chartDom);
	var columnecharts = echarts.init(chartDom);
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
 	        left: '3%',
 	        right: '4%',
 	        bottom: '0%',
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
 	            data:[data.data.age6, data.data.age5, data.data.age4, data.data.age3, data.data.age2, data.data.age1, data.data.age0]
 	        }
 	    ]
 	};
 columnecharts.setOption(option);
}
//地图
function map_echarts(user_total,maxvalue,minvalue){
	//获取地图配置
	$.ajax({
		"data":{"city":cityId},
		"url" : $.cxt + "/sysorg/getMapConf", 
		"type":"POST",
		 async: false,
		"dataType":"json",
		"success":function(data){
			var obj = JSON.parse(data);
			var map =obj.data;
			mapJson = '../map/' + map.jsonName;
			mapType=map.mapType;
		}
	});
	
	var chartDom = document.getElementById('map_echarts');
	echarts.dispose(chartDom);
	var myChart = echarts.init(chartDom);
	myChart.hideLoading();
	$.get(mapJson, function (geoJson) {
        myChart.hideLoading();
        echarts.registerMap(mapType, geoJson);
        myChart.setOption(option = {
            title: {
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