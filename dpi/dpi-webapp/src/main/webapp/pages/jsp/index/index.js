	$(function(){
		//网优平台覆盖率
		paiCharts1();
		//营运收入
		rangeCharts1();
		//营运支出 
		rangeCharts2();
		//净利润
		rangeCharts3();
		//合作费占勘察设计收入
		lineCharts1();
		//成本费用
		lineCharts2();
		
		//业务收款
		barCharts1();
		//资本开支
		barCharts2();
		//资产零购
		barCharts3();
		//基建项目
		barCharts4();
		//转资额
		barCharts5();
		
		//业务收款
		barCharts6();
		//资本开支
		barCharts7();
		//资产零购
		barCharts8();
		//基建项目
		barCharts9();
		//转资额
		barCharts10();
		
		//业务收款
		barCharts11();
		//资本开支
		barCharts12();
		//资产零购
		barCharts13();
		//基建项目
		barCharts14();
		//转资额
		barCharts15();
		
		//业务收款
		barCharts16();
		//资本开支
		barCharts17();
		//资产零购
		barCharts18();
		//基建项目
		barCharts19();
		//转资额
		barCharts20();
		
		//业务收款
		barCharts21();
		
		
		
		//表格
		addTable();
		//总投资设计份额
		barTimes1();
		//网优平台覆盖率
		barTimes2();
		
		//设置上升下降指示箭头
		for(var i=0;i<$(".basis").length;i++){
			if($(".basis").eq(i).text().split("(")[1].split(".")[0] > 0){
				$(".basis").eq(i).css({"background":"url(img/up.png)","background-repeat":"no-repeat","background-position":"left center"})
			}else{
				$(".basis").eq(i).css({"background":"url(img/down.png)","background-repeat":"no-repeat","background-position":"left center"})
			}
		}
		
		for(var i=0;i<$(".basis1").length;i++){
			if($(".basis1").eq(i).text().split(".")[0] > 0){
				$(".basis1").eq(i).css({"background":"url(img/up.png)","background-repeat":"no-repeat","background-position":"left center"})
			}else{
				$(".basis1").eq(i).css({"background":"url(img/down.png)","background-repeat":"no-repeat","background-position":"left center"})
			}
		}
	});
	/*****业务收款*****/
	function paiCharts1(){
	$(".range1").text("2.8pp")
    var myChartL1 = echarts.init(document.getElementById('totals1'));
	var option = {
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    color:['#2db7f5','#da4c62'],
	    legend: {
	        orient: 'vertical',
	        x:140,
	        y:18,
	        textStyle: {
			    color:'#fff'
			},
	        itemGap:20,
	        itemWidth:10,
	        itemHeight:10,
	        data: ['已入库','未入库'],
	        formatter:function(name){
	        	var oa = option.series[0].data;
	        	var num = oa[0].value + oa[1].value;
	        	for(var i = 0; i < option.series[0].data.length; i++){
                    if(name==oa[i].name){
                    	return name + '     ' + oa[i].value + '     ' + (oa[i].value/num * 100).toFixed(2) + '%';
                    }
	        	}
	        }
	    },
	    series : [
	        {
	            name: '域名入库情况',
	            type: 'pie',
	            radius : '65%',
	           // radius: ['50%', '70%'],
	            center: ['25%', '50%'],
	            data:[
	                {value:200, name:'已入库'},
	                {value:15, name:'未入库'}
	            ],
	            label:{ 
                            normal:{show:false}
               },
	            itemStyle: {
	                emphasis: {
	                    //shadowBlur: 10,
	                    //shadowOffsetX: 0,
	                    //shadowColor: 'rgba(0, 0, 0, 0.5)'
	                }
	            },
	            itemStyle: {
	                normal: {
	                    label:{ 
                            //show: true, 
							//position:'inside',
                            // formatter: '{b} : {c} ({d}%)' 
                        }
	                },
                    labelLine :{show:false}
	            }
	        }
	    ]
	};
	// 使用刚指定的配置项和数据显示图表。
	myChartL1.setOption(option);
	}
	/*****************环形图**************/
	function rangeCharts1(){
	$(".range1").text("-3.0pp");
	var myChartRange1 = echarts.init(document.getElementById('mainCenterRange1'));
	var option = {
    tooltip: {
        trigger: 'item',
        formatter: "{a}{b} : {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        left: 'left',
        //data: ['占有率']
    },
    series: [{
        name: '占有率',
        center: [
            '40.0%',
            '55%'
        ],
        radius: [
            '70%',
            '73%'
        ],
        type: 'pie',
        labelLine: {
            normal: {
                show: false
            }
        },
        data: [{
            value:  158587,
            //name: '222',
            tooltip: {
                show: false
            },
            itemStyle: {
                normal: {
                    color: '#aaa'
                },
                emphasis: {
                    color: '#aaa'
                }
            },
            itemStyle: {
                normal: {
                    "color": "#145075",
                    "borderColor": new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: '#145075'
                    }, {
                        offset: 1,
                        color: '#145075'
                    }]),
                    "borderWidth": 5
                },
                emphasis: {
                    color: '#145075'
                }
            },
            hoverAnimation: false
        },
        {
            value: 14098,
           // name: '111',
            label: {
                normal: {
                    formatter: '{d} %',
                    position: 'center',
                    show: true,
                    textStyle: {
                        fontSize: '10',
                        //fontWeight: 'bold',
                        color: '#fff'
                    }
                }
            },
            "itemStyle": {
                "normal": {
                    "color": "#5886f0",
                    "borderColor": new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: '#5affc8'
                    }, {
                        offset: 1,
                        color: '#eaf32c'
                    }]),
                    "borderWidth": 5
                },
                "emphasis": {
                    "color": "#5886f0",
                    "borderColor": new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: '#4ce1af'
                    }, {
                        offset: 1,
                        color: '#c0c726'
                    }]),
                    "borderWidth": 2
                }
            }
        }]
    }]
};
myChartRange1.setOption(option);
}
	
	
function rangeCharts2(){
	$(".range2").text("2.8pp");
	var myChartRange2 = echarts.init(document.getElementById('mainCenterRange2'));
	var option = {
    tooltip: {
        trigger: 'item',
        formatter: "{a}{b}:{c}({d}%)"
    },
    legend: {
        orient: 'vertical',
        left: 'left',
        //data: ['占有率']
    },
    series: [{
        name: '占有率',
        center: [
            '40.0%',
            '55%'
        ],
        radius: [
            '70%',
            '73%'
        ],
        type: 'pie',
        labelLine: {
            normal: {
                show: false
            }
        },
        data: [{
            value:  158587,
            //name: '222',
            tooltip: {
                show: false
            },
            itemStyle: {
                normal: {
                    color: '#aaa'
                },
                emphasis: {
                    color: '#aaa'
                }
            },
            itemStyle: {
                normal: {
                    "color": "#145075",
                    "borderColor": new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: '#145075'
                    }, {
                        offset: 1,
                        color: '#145075'
                    }]),
                    "borderWidth": 5
                },
                emphasis: {
                    color: '#145075'
                }
            },
            hoverAnimation: false
        },
        {
            value: 14098,
           // name: '111',
            label: {
                normal: {
                    formatter: '{d} %',
                    position: 'center',
                    show: true,
                    textStyle: {
                        fontSize: '10',
                        //fontWeight: 'bold',
                        color: '#fff'
                    }
                }
            },
            "itemStyle": {
                "normal": {
                    "color": "#5886f0",
                    "borderColor": new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: '#5affc8'
                    }, {
                        offset: 1,
                        color: '#eaf32c'
                    }]),
                    "borderWidth": 5
                },
                "emphasis": {
                    "color": "#5886f0",
                    "borderColor": new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: '#4ce1af'
                    }, {
                        offset: 1,
                        color: '#c0c726'
                    }]),
                    "borderWidth": 2
                }
            }
        }]
    }]
};
myChartRange2.setOption(option);
}

function rangeCharts3(){
	$(".range3").text("4.3pp");
	var myChartRange3 = echarts.init(document.getElementById('mainCenterRange3'));
	var option = {
    tooltip: {
        trigger: 'item',
        formatter:"{a}{b}:{c}({d}%)"
    },
    legend: {
        orient: 'vertical',
        left: 'left',
        //data: ['占有率']
    },
    series: [{
        name: '占有率',
        center: [
            '40.0%',
            '55%'
        ],
        radius: [
            '70%',
            '73%'
        ],
        type: 'pie',
        labelLine: {
            normal: {
                show: false
            }
        },
        data: [{
            value:  158587,
            //name: '222',
            tooltip: {
                show: false
            },
            itemStyle: {
                normal: {
                    color: '#aaa'
                },
                emphasis: {
                    color: '#aaa'
                }
            },
            itemStyle: {
                normal: {
                    "color": "#145075",
                    "borderColor": new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: '#145075'
                    }, {
                        offset: 1,
                        color: '#145075'
                    }]),
                    "borderWidth": 5
                },
                emphasis: {
                    color: '#145075'
                }
            },
            hoverAnimation: false
        },
        {
            value: 14098,
           // name: '111',
            label: {
                normal: {
                    formatter: '{d} %',
                    position: 'center',
                    show: true,
                    textStyle: {
                        fontSize: '10',
                        //fontWeight: 'bold',
                        color: '#fff'
                    }
                }
            },
            "itemStyle": {
                "normal": {
                    "color": "#5886f0",
                    "borderColor": new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: '#5affc8'
                    }, {
                        offset: 1,
                        color: '#eaf32c'
                    }]),
                    "borderWidth": 5
                },
                "emphasis": {
                    "color": "#5886f0",
                    "borderColor": new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: '#4ce1af'
                    }, {
                        offset: 1,
                        color: '#c0c726'
                    }]),
                    "borderWidth": 2
                }
            }
        }]
    }]
};
myChartRange3.setOption(option);
}
	/****************折线图************/
function lineCharts1(){
	$(".income1").text("("+'27891'+")");
	$(".income2").text("("+'851'+")");
	var myCharts5 = echarts.init(document.getElementById('totals2'));
	var option = {
        tooltip : {
        trigger: 'axis',
        grid: {
			left: '3%',
			right: '4%',
			bottom: '0',
			containLabel: true
		},
        axisPointer: {
            type: 'line',
            label: {
               // backgroundColor: '#6a7985'
            },
            itemStyle: {
                        normal: {
                            color:'#f3c334'
                        }
                    },
		        }
		    },
          /*  title: {
                text: '示例'
            },*/
            legend: {
                //data:['bar1']
            },
            xAxis: {
                type: 'category',
                axisLine: { 
                    lineStyle: { color: '#586f89' } 
                },
                boundaryGap: false,
                data: ['新增','消亡','修改','人工识别','规则配置'],
                axisPointer:{
                    show:true,
                    type:'line'
                },
                axisLabel:{  
                    interval:0,  
//                    rotate:45,//倾斜度 -90 至 90 默认为0  
                    margin:2,  
                    textStyle:{  
//                        fontWeight:"bolder",  
                        color:"white"  
                    }  
                }
            },
            yAxis: {
                show:false,//屏蔽y轴
                type: 'value',
                axisLine: {
                    lineStyle: { color: '#666' }
                },
                boundaryGap: false,
                splitLine: { show: false }
            },
            series: [
                {
                    name: '操作数量',
                    type: 'line',
                    smooth:true,
                    symbol: 'none',
                    sampling: 'average',
                    itemStyle: {
                        normal: {
                            color:'#f3c334'
                        }
                    },
                    lineStyle:{
                        normal:{
                            width:1
                        }
                    },
                    areaStyle: {
                        normal: {
                           /* color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                offset: 0,
                                color: 'rgb(255, 70, 131)'
                            }, {
                                offset: 1,
                                color: 'rgb(255, 158, 68)'
                            }])*///渐变颜色
                           color:['#3b4b48']
                        }
                    },
                    data: [311, 112, 164, 231, 129]       
                },
               
            ]
        }	
        myCharts5.setOption(option);
     }
	
function lineCharts2(){
    $(".income3").text("("+'11487'+")");
	$(".income4").text("("+'345'+")");
    var myCharts6 = echarts.init(document.getElementById('totals3'));
//	var option = {
//            tooltip : {
//        trigger: 'axis',
//        axisPointer: {
//            type: 'var',
//            label: {
//               // backgroundColor: '#6a7985'
//            },
//            itemStyle: {
//                        normal: {
//                            color:'#f3c334'
//                        }
//                    },
//        		}
//   		 	},
//          	/*  title: {
//                text: '示例'
//            },*/
//            legend: {
//               // data:['bar1']
//            },
//            xAxis: {
//                type: 'category',
//                axisLine: { 
//                    lineStyle: { color: '#586f89' } 
//                },
//                boundaryGap: false,
//                data: ['标签库','信息库','属性库','动画库','漫画库','剧集库','游戏库','电商库','电影库','音乐库','新闻库','小说库','综艺库','小视频库','近义词库','敏感词库','停用词词库','分词词库','关键词规则表','关键词规则分组','域名模糊识别规则表'],
//                axisPointer:{
//                    show:true,
//                    type:'bar'
//                },
//                axisLabel:{  
//                    interval:0,  
//                    rotate:45,//倾斜度 -90 至 90 默认为0  
//                    margin:2,  
//                    textStyle:{  
////                        fontWeight:"bolder",  
//                        color:"white"  
//                    }  
//                }
//            },
//            yAxis: {
//                show:false,//屏蔽y轴
//                type: 'value',
//                axisLine: {
//                    lineStyle: { color: '#666' }
//                },
//                boundaryGap: false,
//                splitLine: { show: false }
//            },
//            series: [
//                {
//                    name: 'bar1',
//                    type: 'line',
//                    smooth:true,
//                    symbol: 'none',
//                    sampling: 'average',
//                    itemStyle: {
//                        normal: {
//                            color:'#2db7f5'
//                        }
//                    },
//                    lineStyle:{
//                        normal:{
//                            width:1
//                        }
//                    },
//                    areaStyle: {
//                        normal: {
//                           /* color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
//                                offset: 0,
//                                color: 'rgb(255, 70, 131)'
//                            }, {
//                                offset: 1,
//                                color: 'rgb(255, 158, 68)'
//                            }])*///渐变颜色
//                           color:['#11466f']
//                        }
//                    },
//                    data: [311785, 125450, 164356, 534530, 482363, 522344, 52340,341230,311785, 125450, 164356, 534530, 482363, 522344, 52340,341230,311785, 125450, 164356, 482363,311785]       
//                },
//               
//            ]
//        }	
    
    
    var option = {
			color: ['#3398DB'],
			tooltip: {
				trigger: 'axis',
				axisPointer: { // 坐标轴指示器，坐标轴触发有效
					type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
				}
			},
			grid: {
				left: '3%',
				right: '4%',
				bottom: '30',
				containLabel: true
			},
			xAxis: [{
				type: 'category',
				axisLine: {
					show: true,
					lineStyle: {
						
						color: '#cccccc'
					}
				},
				data: ['标签库','信息库','属性库','动画库','漫画库','剧集库','游戏库','电商库','电影库','音乐库','新闻库','小说库','综艺库','小视频库','近义词库','敏感词库','停用词词库','分词词库','关键词规则表','关键词规则分组','域名识别规则'],
				axisTick: {
					alignWithLabel: true
				},
				axisLabel: {
				  interval: 0,
	              rotate:45,//倾斜度 -90 至 90 默认为0  
	              margin:2,  
	              textStyle:{ 
	            	  fontSize:'10',
	                  color:"white"  
	              }  
				}
			}],
			yAxis: [{
				show: true,
				type: 'value',
				axisTick: {
					show: false
				},
				axisLine: {
					show: false,
					lineStyle: {
						color: 'rgba(255,255,255,0.8)'
					}
				},
				axisLabel: {
					show: false,
					margin: 5,
					textStyle: {
						fontSize: 12
					}
				},
				splitLine: {
					show: false,
					lineStyle: {
						color: '#57617B'
		
					}
				}
			}],
			series: [{
					name: '入库统计',
					type: 'bar',
					barWidth: '40%',
					data: [311785, 125450, 164356, 534530, 482363, 522344, 52340,341230,311785, 125450, 164356, 534530, 482363, 522344, 52340,341230,311785, 125450, 164356, 482363,311785]
				},
		
			],
			label: {
				normal: {
					show: false,
					position: 'top',
					formatter: '{c}'
				}
			},
			itemStyle: {
				normal: {
					color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
						offset: 0,
						color: 'rgba(1, 255, 214, 1)'
					}, {
						offset: 1,
						color: 'rgba(1, 255, 214, 0.1)'
					}]),
					shadowColor: 'rgba(0, 0, 0, 0.1)',
					shadowBlur: 10
				}
			}
		};
    
        myCharts6.setOption(option);
        
    }    
        
	/*****业务收款*****/
function barCharts1(){
	$(".up1").text("0.9pp")
    var myChart1 = echarts.init(document.getElementById('footerMain1'));
	var data=[85261];
	var xMax=447920;
	var option = {
	tooltip:{
		show:true,
		formatter:"{b} {c}"
	},
	grid:{
		left:20,
		top:50,
		bottom:'0',
		right:20
	},
    xAxis : [
        {
            max:xMax,
            type : 'value',
            axisTick: {show: false},
	        axisLine: {show:false},
	        axisLabel: {show:false},
	        splitLine: {show: false}
        }
    ],
    yAxis : [
        {
            type : 'category',
            data : ['业务收款'],
            nameTextStyle:{color:'#b7ce9e',fontSize:'18px'},
           	axisLabel: {show:false},
            axisTick: {show: false},
	        axisLine: {show: false}
        }
    ],
    graphic: [
        {
            type: 'text',
            z: -10,
            left: 20,
            top: 'middle',
            style: {
                fill: '#fff',
                text: '5,261/7,920',
                font: '14px Microsoft YaHei'
            }
        } ,
        {
            type: 'text',
            z: -10,
            right: 20,
            top: 'middle',
            style: {
                fill: '#fff',
                text: '(19%)',
                font: '14px Microsoft YaHei'
            }
        }
    ],
    series : [
        { 
            name:' ',
            type: 'bar',
            barWidth:10,
            silent:true,
            itemStyle: {
	            normal: {
	                color: '#18537b',
	                barBorderRadius: [10, 10, 10, 10]
	
	            }

        },
            barGap:'-100%',
            barCategoryGap:'50%',
            data: data.map(function(d){return xMax}),
        },
        {
            name:' ',
            type:'bar',
            barWidth:10,
            label: {normal: {show: false,position: 'right',formatter: '{c}%'}},
            data:[{
            	value:85261, 
            	itemStyle: {
                normal: {
                    barBorderRadius: [10, 10, 10, 10],
                    color: {
                        type: 'bar',
                        colorStops: [{
                            offset: 0,
                            color: '#00d1cc' // 100% 处的颜色
                            
                            //color:'#00facc'
                        }, {
                            offset: 1,
                            color: 'rgba(0,250,204,.3)' // 0% 处的颜色
                        }],
                        globalCoord: false, // 缺省为 false
                    }
                }
            }}]
        }
    ]
};
	// 使用刚指定的配置项和数据显示图表。
	myChart1.setOption(option);
}
 
 /*****业务收款*****/
function barCharts2(){
	$(".up2").text("-1.77pp")
    var myChart2 = echarts.init(document.getElementById('footerMain2'));
	var data=[1930];
	var xMax=8638;
	var option = {
	tooltip:{
		show:true,
		formatter:"{b} {c}"
	},
	grid:{
		left:20,
		top:50,
		bottom:'0',
		right:20
	},
    xAxis : [
        {
            max:xMax,
            type : 'value',
            axisTick: {show: false},
	        axisLine: {show:false},
	        axisLabel: {show:false},
	        splitLine: {show: false}
        }
    ],
    yAxis : [
        {
            type : 'category',
            data : ['业务收款'],
            nameTextStyle:{color:'#b7ce9e',fontSize:'18px'},
           	axisLabel: {show:false},
            axisTick: {show: false},
	        axisLine: {show: false}
        }
    ],
    graphic: [
        {
            type: 'text',
            z: -10,
            left: 20,
            top: 'middle',
            style: {
                fill: '#fff',
                text: '193／8638',
                font: '14px Microsoft YaHei'
            }
        },
         {
            type: 'text',
            z: -10,
            right: 20,
            top: 'middle',
            style: {
                fill: '#fff',
                text: '(5.2%)',
                font: '14px Microsoft YaHei'
            }
        }
    ],
    series : [
        { 
            name:' ',
            type: 'bar',
            barWidth:10,
            silent:true,
            itemStyle: {
	            normal: {
	                color: '#18537b',
	                barBorderRadius: [10, 10, 10, 10]
	
	            }

        },
            barGap:'-100%',
            barCategoryGap:'50%',
            data: data.map(function(d){return xMax}),
        },
        {
            name:' ',
            type:'bar',
            barWidth:10,
            label: {normal: {show: false,position: 'right',formatter: '{c}%'}},
            data:[{
            	value:193, 
            	itemStyle: {
                normal: {
                    barBorderRadius: [10, 10, 10, 10],
                    color: {
                        type: 'bar',
                        colorStops: [{
                            offset: 0,
                            color: '#00d1cc' // 100% 处的颜色
                            
                            //color:'#00facc'
                        }, {
                            offset: 1,
                            color: 'rgba(0,250,204,.3)' // 0% 处的颜色
                        }],
                        globalCoord: false, // 缺省为 false
                    }
                }
            }}]
        }
    ]
};
// 使用刚指定的配置项和数据显示图表。
myChart2.setOption(option);
}

function barCharts3(){
 /*****业务收款*****/
	$(".up3").text("-1.77p")
    var myChart3 = echarts.init(document.getElementById('footerMain3'));
	var data=[193];
	var xMax=8638;
	var option = {
	tooltip:{
		show:true,
		formatter:"{b} {c}"
	},
	grid:{
		left:20,
		top:50,
		bottom:'0',
		right:20
	},
    xAxis : [
        {
            max:xMax,
            type : 'value',
            axisTick: {show: false},
	        axisLine: {show:false},
	        axisLabel: {show:false},
	        splitLine: {show: false}
        }
    ],
    yAxis : [
        {
            type : 'category',
            data : ['业务收款'],
            nameTextStyle:{color:'#b7ce9e',fontSize:'18px'},
           	axisLabel: {show:false},
            axisTick: {show: false},
	        axisLine: {show: false}
        }
    ],
    graphic: [
        {
            type: 'text',
            z: -10,
            left: 20,
            top: 'middle',
            style: {
                fill: '#fff',
                text: '193／8638',
                font: '14px Microsoft YaHei',
                position:'left'
            }
        } ,
        {
            type: 'text',
            z: -10,
            right: 20,
            top: 'middle',
            style: {
                fill: '#fff',
                text: '(2.2%)',
                font: '14px Microsoft YaHei',
                position:'left'
            }
        } 
    ],
    series : [
        { 
            name:' ',
            type: 'bar',
            barWidth:10,
            silent:true,
            itemStyle: {
	            normal: {
	                color: '#18537b',
	                barBorderRadius: [10, 10, 10, 10]
	
	            }

        },
            barGap:'-100%',
            barCategoryGap:'50%',
            data: data.map(function(d){return xMax}),
        },
        {
            name:' ',
            type:'bar',
            barWidth:10,
            label: {normal: {show: false,position: 'right',formatter: '{c}%'}},
            data:[{
            	value:193, 
            	itemStyle: {
                normal: {
                    barBorderRadius: [10, 10, 10, 10],
                    color: {
                        type: 'bar',
                        colorStops: [{
                            offset: 0,
                            color: '#00d1cc' // 100% 处的颜色
                            
                            //color:'#00facc'
                        }, {
                            offset: 1,
                            color: 'rgba(0,250,204,.3)' // 0% 处的颜色
                        }],
                        globalCoord: false, // 缺省为 false
                    }
                }
            }}]
        }
    ]
};
// 使用刚指定的配置项和数据显示图表。
myChart3.setOption(option);
}

function barCharts4(){
	$(".up4").text("-1.77p")
    var myChart4 = echarts.init(document.getElementById('footerMain4'));
	var data=[54];
	var xMax=6138;
	var option = {
	tooltip:{
		show:true,
		formatter:"{b} {c}"
	},
	grid:{
		left:20,
		top:50,
		bottom:'0',
		right:20
	},
    xAxis : [
        {
            max:xMax,
            type : 'value',
            axisTick: {show: false},
	        axisLine: {show:false},
	        axisLabel: {show:false},
	        splitLine: {show: false}
        }
    ],
    yAxis : [
        {
            type : 'category',
            data : ['业务收款'],
            nameTextStyle:{color:'#b7ce9e',fontSize:'18px'},
           	axisLabel: {show:false},
            axisTick: {show: false},
	        axisLine: {show: false}
        }
    ],
    graphic: [
        {
            type: 'text',
            z: -10,
            left: 20,
            top: 'middle',
            style: {
                fill: '#fff',
                text: '54/6,138',
                font: '14px Microsoft YaHei',
                position:'left'
            }
        },
        {
            type: 'text',
            z: -10,
            right: 20,
            top: 'middle',
            style: {
                fill: '#fff',
                text: '(19%)',
                font: '14px Microsoft YaHei',
                position:'left'
            }
        },
    ],
    series : [
        { 
            name:' ',
            type: 'bar',
            barWidth:10,
            silent:true,
            itemStyle: {
	            normal: {
	                color: '#18537b',
	                barBorderRadius: [10, 10, 10, 10]
	
	            }

        },
            barGap:'-100%',
            barCategoryGap:'50%',
            data: data.map(function(d){return xMax}),
        },
        {
            name:' ',
            type:'bar',
            barWidth:10,
            label: {normal: {show: false,position: 'right',formatter: '{c}%'}},
            data:[{
            	value:54, 
            	itemStyle: {
                normal: {
                    barBorderRadius: [10, 10, 10, 10],
                    color: {
                        type: 'bar',
                        colorStops: [{
                            offset: 0,
                            color: '#00d1cc' // 100% 处的颜色
                            
                            //color:'#00facc'
                        }, {
                            offset: 1,
                            color: 'rgba(0,250,204,.3)' // 0% 处的颜色
                        }],
                        globalCoord: false, // 缺省为 false
                    }
                }
            }}]
        }
    ]
};
// 使用刚指定的配置项和数据显示图表。
myChart4.setOption(option);
}


function barCharts5(){
    var myChart5 = echarts.init(document.getElementById('footerMain5'));
	var data=[3000];
	var xMax=3664;
	var option = {
	tooltip:{
		show:true,
		formatter:"{b} {c}"
	},
	grid:{
		left:20,
		top:50,
		bottom:'0',
		right:20
	},
    xAxis : [
        {
            max:xMax,
            type : 'value',
            axisTick: {show: false},
	        axisLine: {show:false},
	        axisLabel: {show:false},
	        splitLine: {show: false}
        }
    ],
    yAxis : [
        {
            type : 'category',
            data : ['业务收款'],
            nameTextStyle:{color:'#b7ce9e',fontSize:'18px'},
           	axisLabel: {show:false},
            axisTick: {show: false},
	        axisLine: {show: false}
        }
    ],
    graphic: [
        {
            type: 'text',
            z: -10,
            left: 20,
            top: 'middle',
            style: {
                fill: '#fff',
                text: '664/3,664',
                font: '14px Microsoft YaHei'
            }
        },
        {
            type: 'text',
            z: -10,
            right: 20,
            top: 'middle',
            style: {
                fill: '#fff',
                text: '(32%)',
                font: '14px Microsoft YaHei',
                position:'left'
            }
        }
    ],
    series : [
        { 
            name:' ',
            type: 'bar',
            barWidth:10,
            silent:true,
            itemStyle: {
	            normal: {
	                color: '#18537b',
	                barBorderRadius: [10, 10, 10, 10]
	
	            }

        },
            barGap:'-100%',
            barCategoryGap:'50%',
            data: data.map(function(d){return xMax}),
        },
        {
            name:' ',
            type:'bar',
            barWidth:10,
            label: {normal: {show: false,position: 'right',formatter: '{c}%'}},
            data:[{
            	value:1000, 
            	itemStyle: {
                normal: {
                    barBorderRadius: [10, 10, 10, 10],
                    color: {
                        type: 'bar',
                        colorStops: [{
                            offset: 0,
                            color: '#00d1cc' // 100% 处的颜色
                            
                            //color:'#00facc'
                        }, {
                            offset: 1,
                            color: 'rgba(0,250,204,.3)' // 0% 处的颜色
                        }],
                        globalCoord: false, // 缺省为 false
                    }
                }
            }}]
        }
    ]
};
// 使用刚指定的配置项和数据显示图表。
myChart5.setOption(option);
}

function barCharts6(){
    var myChart6 = echarts.init(document.getElementById('footerMain6'));
	var data=[3000];
	var xMax=3664;
	var option = {
	tooltip:{
		show:true,
		formatter:"{b} {c}"
	},
	grid:{
		left:20,
		top:50,
		bottom:'0',
		right:20
	},
    xAxis : [
        {
            max:xMax,
            type : 'value',
            axisTick: {show: false},
	        axisLine: {show:false},
	        axisLabel: {show:false},
	        splitLine: {show: false}
        }
    ],
    yAxis : [
        {
            type : 'category',
            data : ['业务收款'],
            nameTextStyle:{color:'#b7ce9e',fontSize:'18px'},
           	axisLabel: {show:false},
            axisTick: {show: false},
	        axisLine: {show: false}
        }
    ],
    graphic: [
        {
            type: 'text',
            z: -10,
            left: 20,
            top: 'middle',
            style: {
                fill: '#fff',
                text: '456/3,664',
                font: '14px Microsoft YaHei'
            }
        },
        {
            type: 'text',
            z: -10,
            right: 20,
            top: 'middle',
            style: {
                fill: '#fff',
                text: '(21%)',
                font: '14px Microsoft YaHei',
                position:'left'
            }
        }
    ],
    series : [
        { 
            name:' ',
            type: 'bar',
            barWidth:10,
            silent:true,
            itemStyle: {
	            normal: {
	                color: '#18537b',
	                barBorderRadius: [10, 10, 10, 10]
	
	            }

        },
            barGap:'-100%',
            barCategoryGap:'50%',
            data: data.map(function(d){return xMax}),
        },
        {
            name:' ',
            type:'bar',
            barWidth:10,
            label: {normal: {show: false,position: 'right',formatter: '{c}%'}},
            data:[{
            	value:1000, 
            	itemStyle: {
                normal: {
                    barBorderRadius: [10, 10, 10, 10],
                    color: {
                        type: 'bar',
                        colorStops: [{
                            offset: 0,
                            color: '#00d1cc' // 100% 处的颜色
                            
                            //color:'#00facc'
                        }, {
                            offset: 1,
                            color: 'rgba(0,250,204,.3)' // 0% 处的颜色
                        }],
                        globalCoord: false, // 缺省为 false
                    }
                }
            }}]
        }
    ]
};
// 使用刚指定的配置项和数据显示图表。
myChart6.setOption(option);
}

function barCharts7(){
    var myChart7 = echarts.init(document.getElementById('footerMain7'));
	var data=[3000];
	var xMax=3664;
	var option = {
	tooltip:{
		show:true,
		formatter:"{b} {c}"
	},
	grid:{
		left:20,
		top:50,
		bottom:'0',
		right:20
	},
    xAxis : [
        {
            max:xMax,
            type : 'value',
            axisTick: {show: false},
	        axisLine: {show:false},
	        axisLabel: {show:false},
	        splitLine: {show: false}
        }
    ],
    yAxis : [
        {
            type : 'category',
            data : ['业务收款'],
            nameTextStyle:{color:'#b7ce9e',fontSize:'18px'},
           	axisLabel: {show:false},
            axisTick: {show: false},
	        axisLine: {show: false}
        }
    ],
    graphic: [
        {
            type: 'text',
            z: -10,
            left: 20,
            top: 'middle',
            style: {
                fill: '#fff',
                text: '321/3,664',
                font: '14px Microsoft YaHei'
            }
        },
        {
            type: 'text',
            z: -10,
            right: 20,
            top: 'middle',
            style: {
                fill: '#fff',
                text: '(42%)',
                font: '14px Microsoft YaHei',
                position:'left'
            }
        }
    ],
    series : [
        { 
            name:' ',
            type: 'bar',
            barWidth:10,
            silent:true,
            itemStyle: {
	            normal: {
	                color: '#18537b',
	                barBorderRadius: [10, 10, 10, 10]
	
	            }

        },
            barGap:'-100%',
            barCategoryGap:'50%',
            data: data.map(function(d){return xMax}),
        },
        {
            name:' ',
            type:'bar',
            barWidth:10,
            label: {normal: {show: false,position: 'right',formatter: '{c}%'}},
            data:[{
            	value:1000, 
            	itemStyle: {
                normal: {
                    barBorderRadius: [10, 10, 10, 10],
                    color: {
                        type: 'bar',
                        colorStops: [{
                            offset: 0,
                            color: '#00d1cc' // 100% 处的颜色
                            
                            //color:'#00facc'
                        }, {
                            offset: 1,
                            color: 'rgba(0,250,204,.3)' // 0% 处的颜色
                        }],
                        globalCoord: false, // 缺省为 false
                    }
                }
            }}]
        }
    ]
};
// 使用刚指定的配置项和数据显示图表。
myChart7.setOption(option);
}

function barCharts8(){
    var myChart8 = echarts.init(document.getElementById('footerMain8'));
	var data=[3000];
	var xMax=3664;
	var option = {
	tooltip:{
		show:true,
		formatter:"{b} {c}"
	},
	grid:{
		left:20,
		top:50,
		bottom:'0',
		right:20
	},
    xAxis : [
        {
            max:xMax,
            type : 'value',
            axisTick: {show: false},
	        axisLine: {show:false},
	        axisLabel: {show:false},
	        splitLine: {show: false}
        }
    ],
    yAxis : [
        {
            type : 'category',
            data : ['业务收款'],
            nameTextStyle:{color:'#b7ce9e',fontSize:'18px'},
           	axisLabel: {show:false},
            axisTick: {show: false},
	        axisLine: {show: false}
        }
    ],
    graphic: [
        {
            type: 'text',
            z: -10,
            left: 20,
            top: 'middle',
            style: {
                fill: '#fff',
                text: '852/3,664',
                font: '14px Microsoft YaHei'
            }
        },
        {
            type: 'text',
            z: -10,
            right: 20,
            top: 'middle',
            style: {
                fill: '#fff',
                text: '(45%)',
                font: '14px Microsoft YaHei',
                position:'left'
            }
        }
    ],
    series : [
        { 
            name:' ',
            type: 'bar',
            barWidth:10,
            silent:true,
            itemStyle: {
	            normal: {
	                color: '#18537b',
	                barBorderRadius: [10, 10, 10, 10]
	
	            }

        },
            barGap:'-100%',
            barCategoryGap:'50%',
            data: data.map(function(d){return xMax}),
        },
        {
            name:' ',
            type:'bar',
            barWidth:10,
            label: {normal: {show: false,position: 'right',formatter: '{c}%'}},
            data:[{
            	value:1000, 
            	itemStyle: {
                normal: {
                    barBorderRadius: [10, 10, 10, 10],
                    color: {
                        type: 'bar',
                        colorStops: [{
                            offset: 0,
                            color: '#00d1cc' // 100% 处的颜色
                            
                            //color:'#00facc'
                        }, {
                            offset: 1,
                            color: 'rgba(0,250,204,.3)' // 0% 处的颜色
                        }],
                        globalCoord: false, // 缺省为 false
                    }
                }
            }}]
        }
    ]
};
// 使用刚指定的配置项和数据显示图表。
myChart8.setOption(option);
}

function barCharts9(){
    var myChart9 = echarts.init(document.getElementById('footerMain9'));
	var data=[3000];
	var xMax=3664;
	var option = {
	tooltip:{
		show:true,
		formatter:"{b} {c}"
	},
	grid:{
		left:20,
		top:50,
		bottom:'0',
		right:20
	},
    xAxis : [
        {
            max:xMax,
            type : 'value',
            axisTick: {show: false},
	        axisLine: {show:false},
	        axisLabel: {show:false},
	        splitLine: {show: false}
        }
    ],
    yAxis : [
        {
            type : 'category',
            data : ['业务收款'],
            nameTextStyle:{color:'#b7ce9e',fontSize:'18px'},
           	axisLabel: {show:false},
            axisTick: {show: false},
	        axisLine: {show: false}
        }
    ],
    graphic: [
        {
            type: 'text',
            z: -10,
            left: 20,
            top: 'middle',
            style: {
                fill: '#fff',
                text: '978/3,664',
                font: '14px Microsoft YaHei'
            }
        },
        {
            type: 'text',
            z: -10,
            right: 20,
            top: 'middle',
            style: {
                fill: '#fff',
                text: '(85%)',
                font: '14px Microsoft YaHei',
                position:'left'
            }
        }
    ],
    series : [
        { 
            name:' ',
            type: 'bar',
            barWidth:10,
            silent:true,
            itemStyle: {
	            normal: {
	                color: '#18537b',
	                barBorderRadius: [10, 10, 10, 10]
	
	            }

        },
            barGap:'-100%',
            barCategoryGap:'50%',
            data: data.map(function(d){return xMax}),
        },
        {
            name:' ',
            type:'bar',
            barWidth:10,
            label: {normal: {show: false,position: 'right',formatter: '{c}%'}},
            data:[{
            	value:1000, 
            	itemStyle: {
                normal: {
                    barBorderRadius: [10, 10, 10, 10],
                    color: {
                        type: 'bar',
                        colorStops: [{
                            offset: 0,
                            color: '#00d1cc' // 100% 处的颜色
                            
                            //color:'#00facc'
                        }, {
                            offset: 1,
                            color: 'rgba(0,250,204,.3)' // 0% 处的颜色
                        }],
                        globalCoord: false, // 缺省为 false
                    }
                }
            }}]
        }
    ]
};
// 使用刚指定的配置项和数据显示图表。
myChart9.setOption(option);
}

function barCharts10(){
    var myChart10 = echarts.init(document.getElementById('footerMain10'));
	var data=[3000];
	var xMax=3664;
	var option = {
	tooltip:{
		show:true,
		formatter:"{b} {c}"
	},
	grid:{
		left:20,
		top:50,
		bottom:'0',
		right:20
	},
    xAxis : [
        {
            max:xMax,
            type : 'value',
            axisTick: {show: false},
	        axisLine: {show:false},
	        axisLabel: {show:false},
	        splitLine: {show: false}
        }
    ],
    yAxis : [
        {
            type : 'category',
            data : ['业务收款'],
            nameTextStyle:{color:'#b7ce9e',fontSize:'18px'},
           	axisLabel: {show:false},
            axisTick: {show: false},
	        axisLine: {show: false}
        }
    ],
    graphic: [
        {
            type: 'text',
            z: -10,
            left: 20,
            top: 'middle',
            style: {
                fill: '#fff',
                text: '221/3,664',
                font: '14px Microsoft YaHei'
            }
        },
        {
            type: 'text',
            z: -10,
            right: 20,
            top: 'middle',
            style: {
                fill: '#fff',
                text: '(65%)',
                font: '14px Microsoft YaHei',
                position:'left'
            }
        }
    ],
    series : [
        { 
            name:' ',
            type: 'bar',
            barWidth:10,
            silent:true,
            itemStyle: {
	            normal: {
	                color: '#18537b',
	                barBorderRadius: [10, 10, 10, 10]
	
	            }

        },
            barGap:'-100%',
            barCategoryGap:'50%',
            data: data.map(function(d){return xMax}),
        },
        {
            name:' ',
            type:'bar',
            barWidth:10,
            label: {normal: {show: false,position: 'right',formatter: '{c}%'}},
            data:[{
            	value:1000, 
            	itemStyle: {
                normal: {
                    barBorderRadius: [10, 10, 10, 10],
                    color: {
                        type: 'bar',
                        colorStops: [{
                            offset: 0,
                            color: '#00d1cc' // 100% 处的颜色
                            
                            //color:'#00facc'
                        }, {
                            offset: 1,
                            color: 'rgba(0,250,204,.3)' // 0% 处的颜色
                        }],
                        globalCoord: false, // 缺省为 false
                    }
                }
            }}]
        }
    ]
};
// 使用刚指定的配置项和数据显示图表。
myChart10.setOption(option);
}

function barCharts11(){
    var myChart11 = echarts.init(document.getElementById('footerMain11'));
	var data=[3000];
	var xMax=3664;
	var option = {
	tooltip:{
		show:true,
		formatter:"{b} {c}"
	},
	grid:{
		left:20,
		top:50,
		bottom:'0',
		right:20
	},
    xAxis : [
        {
            max:xMax,
            type : 'value',
            axisTick: {show: false},
	        axisLine: {show:false},
	        axisLabel: {show:false},
	        splitLine: {show: false}
        }
    ],
    yAxis : [
        {
            type : 'category',
            data : ['业务收款'],
            nameTextStyle:{color:'#b7ce9e',fontSize:'18px'},
           	axisLabel: {show:false},
            axisTick: {show: false},
	        axisLine: {show: false}
        }
    ],
    graphic: [
        {
            type: 'text',
            z: -10,
            left: 20,
            top: 'middle',
            style: {
                fill: '#fff',
                text: '522/3,664',
                font: '14px Microsoft YaHei'
            }
        },
        {
            type: 'text',
            z: -10,
            right: 20,
            top: 'middle',
            style: {
                fill: '#fff',
                text: '(36%)',
                font: '14px Microsoft YaHei',
                position:'left'
            }
        }
    ],
    series : [
        { 
            name:' ',
            type: 'bar',
            barWidth:10,
            silent:true,
            itemStyle: {
	            normal: {
	                color: '#18537b',
	                barBorderRadius: [10, 10, 10, 10]
	
	            }

        },
            barGap:'-100%',
            barCategoryGap:'50%',
            data: data.map(function(d){return xMax}),
        },
        {
            name:' ',
            type:'bar',
            barWidth:10,
            label: {normal: {show: false,position: 'right',formatter: '{c}%'}},
            data:[{
            	value:1000, 
            	itemStyle: {
                normal: {
                    barBorderRadius: [10, 10, 10, 10],
                    color: {
                        type: 'bar',
                        colorStops: [{
                            offset: 0,
                            color: '#00d1cc' // 100% 处的颜色
                            
                            //color:'#00facc'
                        }, {
                            offset: 1,
                            color: 'rgba(0,250,204,.3)' // 0% 处的颜色
                        }],
                        globalCoord: false, // 缺省为 false
                    }
                }
            }}]
        }
    ]
};
// 使用刚指定的配置项和数据显示图表。
myChart11.setOption(option);
}

function barCharts12(){
    var myChart12 = echarts.init(document.getElementById('footerMain12'));
	var data=[3000];
	var xMax=3664;
	var option = {
	tooltip:{
		show:true,
		formatter:"{b} {c}"
	},
	grid:{
		left:20,
		top:50,
		bottom:'0',
		right:20
	},
    xAxis : [
        {
            max:xMax,
            type : 'value',
            axisTick: {show: false},
	        axisLine: {show:false},
	        axisLabel: {show:false},
	        splitLine: {show: false}
        }
    ],
    yAxis : [
        {
            type : 'category',
            data : ['业务收款'],
            nameTextStyle:{color:'#b7ce9e',fontSize:'18px'},
           	axisLabel: {show:false},
            axisTick: {show: false},
	        axisLine: {show: false}
        }
    ],
    graphic: [
        {
            type: 'text',
            z: -10,
            left: 20,
            top: 'middle',
            style: {
                fill: '#fff',
                text: '336/3,664',
                font: '14px Microsoft YaHei'
            }
        },
        {
            type: 'text',
            z: -10,
            right: 20,
            top: 'middle',
            style: {
                fill: '#fff',
                text: '(35%)',
                font: '14px Microsoft YaHei',
                position:'left'
            }
        }
    ],
    series : [
        { 
            name:' ',
            type: 'bar',
            barWidth:10,
            silent:true,
            itemStyle: {
	            normal: {
	                color: '#18537b',
	                barBorderRadius: [10, 10, 10, 10]
	
	            }

        },
            barGap:'-100%',
            barCategoryGap:'50%',
            data: data.map(function(d){return xMax}),
        },
        {
            name:' ',
            type:'bar',
            barWidth:10,
            label: {normal: {show: false,position: 'right',formatter: '{c}%'}},
            data:[{
            	value:1000, 
            	itemStyle: {
                normal: {
                    barBorderRadius: [10, 10, 10, 10],
                    color: {
                        type: 'bar',
                        colorStops: [{
                            offset: 0,
                            color: '#00d1cc' // 100% 处的颜色
                            
                            //color:'#00facc'
                        }, {
                            offset: 1,
                            color: 'rgba(0,250,204,.3)' // 0% 处的颜色
                        }],
                        globalCoord: false, // 缺省为 false
                    }
                }
            }}]
        }
    ]
};
// 使用刚指定的配置项和数据显示图表。
myChart12.setOption(option);
}

function barCharts13(){
    var myChart13 = echarts.init(document.getElementById('footerMain13'));
	var data=[3000];
	var xMax=3664;
	var option = {
	tooltip:{
		show:true,
		formatter:"{b} {c}"
	},
	grid:{
		left:20,
		top:50,
		bottom:'0',
		right:20
	},
    xAxis : [
        {
            max:xMax,
            type : 'value',
            axisTick: {show: false},
	        axisLine: {show:false},
	        axisLabel: {show:false},
	        splitLine: {show: false}
        }
    ],
    yAxis : [
        {
            type : 'category',
            data : ['业务收款'],
            nameTextStyle:{color:'#b7ce9e',fontSize:'18px'},
           	axisLabel: {show:false},
            axisTick: {show: false},
	        axisLine: {show: false}
        }
    ],
    graphic: [
        {
            type: 'text',
            z: -10,
            left: 20,
            top: 'middle',
            style: {
                fill: '#fff',
                text: '557/3,664',
                font: '14px Microsoft YaHei'
            }
        },
        {
            type: 'text',
            z: -10,
            right: 20,
            top: 'middle',
            style: {
                fill: '#fff',
                text: '(63%)',
                font: '14px Microsoft YaHei',
                position:'left'
            }
        }
    ],
    series : [
        { 
            name:' ',
            type: 'bar',
            barWidth:10,
            silent:true,
            itemStyle: {
	            normal: {
	                color: '#18537b',
	                barBorderRadius: [10, 10, 10, 10]
	
	            }

        },
            barGap:'-100%',
            barCategoryGap:'50%',
            data: data.map(function(d){return xMax}),
        },
        {
            name:' ',
            type:'bar',
            barWidth:10,
            label: {normal: {show: false,position: 'right',formatter: '{c}%'}},
            data:[{
            	value:1000, 
            	itemStyle: {
                normal: {
                    barBorderRadius: [10, 10, 10, 10],
                    color: {
                        type: 'bar',
                        colorStops: [{
                            offset: 0,
                            color: '#00d1cc' // 100% 处的颜色
                            
                            //color:'#00facc'
                        }, {
                            offset: 1,
                            color: 'rgba(0,250,204,.3)' // 0% 处的颜色
                        }],
                        globalCoord: false, // 缺省为 false
                    }
                }
            }}]
        }
    ]
};
// 使用刚指定的配置项和数据显示图表。
myChart13.setOption(option);
}

function barCharts14(){
    var myChart14 = echarts.init(document.getElementById('footerMain14'));
	var data=[3000];
	var xMax=3664;
	var option = {
	tooltip:{
		show:true,
		formatter:"{b} {c}"
	},
	grid:{
		left:20,
		top:50,
		bottom:'0',
		right:20
	},
    xAxis : [
        {
            max:xMax,
            type : 'value',
            axisTick: {show: false},
	        axisLine: {show:false},
	        axisLabel: {show:false},
	        splitLine: {show: false}
        }
    ],
    yAxis : [
        {
            type : 'category',
            data : ['业务收款'],
            nameTextStyle:{color:'#b7ce9e',fontSize:'18px'},
           	axisLabel: {show:false},
            axisTick: {show: false},
	        axisLine: {show: false}
        }
    ],
    graphic: [
        {
            type: 'text',
            z: -10,
            left: 20,
            top: 'middle',
            style: {
                fill: '#fff',
                text: '671/3,664',
                font: '14px Microsoft YaHei'
            }
        },
        {
            type: 'text',
            z: -10,
            right: 20,
            top: 'middle',
            style: {
                fill: '#fff',
                text: '(23%)',
                font: '14px Microsoft YaHei',
                position:'left'
            }
        }
    ],
    series : [
        { 
            name:' ',
            type: 'bar',
            barWidth:10,
            silent:true,
            itemStyle: {
	            normal: {
	                color: '#18537b',
	                barBorderRadius: [10, 10, 10, 10]
	
	            }

        },
            barGap:'-100%',
            barCategoryGap:'50%',
            data: data.map(function(d){return xMax}),
        },
        {
            name:' ',
            type:'bar',
            barWidth:10,
            label: {normal: {show: false,position: 'right',formatter: '{c}%'}},
            data:[{
            	value:1000, 
            	itemStyle: {
                normal: {
                    barBorderRadius: [10, 10, 10, 10],
                    color: {
                        type: 'bar',
                        colorStops: [{
                            offset: 0,
                            color: '#00d1cc' // 100% 处的颜色
                            
                            //color:'#00facc'
                        }, {
                            offset: 1,
                            color: 'rgba(0,250,204,.3)' // 0% 处的颜色
                        }],
                        globalCoord: false, // 缺省为 false
                    }
                }
            }}]
        }
    ]
};
// 使用刚指定的配置项和数据显示图表。
myChart14.setOption(option);
}

function barCharts15(){
    var myChart15 = echarts.init(document.getElementById('footerMain15'));
	var data=[3000];
	var xMax=3664;
	var option = {
	tooltip:{
		show:true,
		formatter:"{b} {c}"
	},
	grid:{
		left:20,
		top:50,
		bottom:'0',
		right:20
	},
    xAxis : [
        {
            max:xMax,
            type : 'value',
            axisTick: {show: false},
	        axisLine: {show:false},
	        axisLabel: {show:false},
	        splitLine: {show: false}
        }
    ],
    yAxis : [
        {
            type : 'category',
            data : ['业务收款'],
            nameTextStyle:{color:'#b7ce9e',fontSize:'18px'},
           	axisLabel: {show:false},
            axisTick: {show: false},
	        axisLine: {show: false}
        }
    ],
    graphic: [
        {
            type: 'text',
            z: -10,
            left: 20,
            top: 'middle',
            style: {
                fill: '#fff',
                text: '456/3,664',
                font: '14px Microsoft YaHei'
            }
        },
        {
            type: 'text',
            z: -10,
            right: 20,
            top: 'middle',
            style: {
                fill: '#fff',
                text: '(56%)',
                font: '14px Microsoft YaHei',
                position:'left'
            }
        }
    ],
    series : [
        { 
            name:' ',
            type: 'bar',
            barWidth:10,
            silent:true,
            itemStyle: {
	            normal: {
	                color: '#18537b',
	                barBorderRadius: [10, 10, 10, 10]
	
	            }

        },
            barGap:'-100%',
            barCategoryGap:'50%',
            data: data.map(function(d){return xMax}),
        },
        {
            name:' ',
            type:'bar',
            barWidth:10,
            label: {normal: {show: false,position: 'right',formatter: '{c}%'}},
            data:[{
            	value:1000, 
            	itemStyle: {
                normal: {
                    barBorderRadius: [10, 10, 10, 10],
                    color: {
                        type: 'bar',
                        colorStops: [{
                            offset: 0,
                            color: '#00d1cc' // 100% 处的颜色
                            
                            //color:'#00facc'
                        }, {
                            offset: 1,
                            color: 'rgba(0,250,204,.3)' // 0% 处的颜色
                        }],
                        globalCoord: false, // 缺省为 false
                    }
                }
            }}]
        }
    ]
};
// 使用刚指定的配置项和数据显示图表。
myChart15.setOption(option);
}

function barCharts16(){
    var myChart16 = echarts.init(document.getElementById('footerMain16'));
	var data=[3000];
	var xMax=3664;
	var option = {
	tooltip:{
		show:true,
		formatter:"{b} {c}"
	},
	grid:{
		left:20,
		top:50,
		bottom:'0',
		right:20
	},
    xAxis : [
        {
            max:xMax,
            type : 'value',
            axisTick: {show: false},
	        axisLine: {show:false},
	        axisLabel: {show:false},
	        splitLine: {show: false}
        }
    ],
    yAxis : [
        {
            type : 'category',
            data : ['业务收款'],
            nameTextStyle:{color:'#b7ce9e',fontSize:'18px'},
           	axisLabel: {show:false},
            axisTick: {show: false},
	        axisLine: {show: false}
        }
    ],
    graphic: [
        {
            type: 'text',
            z: -10,
            left: 20,
            top: 'middle',
            style: {
                fill: '#fff',
                text: '423/3,664',
                font: '14px Microsoft YaHei'
            }
        },
        {
            type: 'text',
            z: -10,
            right: 20,
            top: 'middle',
            style: {
                fill: '#fff',
                text: '(35%)',
                font: '14px Microsoft YaHei',
                position:'left'
            }
        }
    ],
    series : [
        { 
            name:' ',
            type: 'bar',
            barWidth:10,
            silent:true,
            itemStyle: {
	            normal: {
	                color: '#18537b',
	                barBorderRadius: [10, 10, 10, 10]
	
	            }

        },
            barGap:'-100%',
            barCategoryGap:'50%',
            data: data.map(function(d){return xMax}),
        },
        {
            name:' ',
            type:'bar',
            barWidth:10,
            label: {normal: {show: false,position: 'right',formatter: '{c}%'}},
            data:[{
            	value:1000, 
            	itemStyle: {
                normal: {
                    barBorderRadius: [10, 10, 10, 10],
                    color: {
                        type: 'bar',
                        colorStops: [{
                            offset: 0,
                            color: '#00d1cc' // 100% 处的颜色
                            
                            //color:'#00facc'
                        }, {
                            offset: 1,
                            color: 'rgba(0,250,204,.3)' // 0% 处的颜色
                        }],
                        globalCoord: false, // 缺省为 false
                    }
                }
            }}]
        }
    ]
};
// 使用刚指定的配置项和数据显示图表。
myChart16.setOption(option);
}


function barCharts17(){
    var myChart17 = echarts.init(document.getElementById('footerMain17'));
	var data=[3000];
	var xMax=3664;
	var option = {
	tooltip:{
		show:true,
		formatter:"{b} {c}"
	},
	grid:{
		left:20,
		top:50,
		bottom:'0',
		right:20
	},
    xAxis : [
        {
            max:xMax,
            type : 'value',
            axisTick: {show: false},
	        axisLine: {show:false},
	        axisLabel: {show:false},
	        splitLine: {show: false}
        }
    ],
    yAxis : [
        {
            type : 'category',
            data : ['业务收款'],
            nameTextStyle:{color:'#b7ce9e',fontSize:'18px'},
           	axisLabel: {show:false},
            axisTick: {show: false},
	        axisLine: {show: false}
        }
    ],
    graphic: [
        {
            type: 'text',
            z: -10,
            left: 20,
            top: 'middle',
            style: {
                fill: '#fff',
                text: '112/3,664',
                font: '14px Microsoft YaHei'
            }
        },
        {
            type: 'text',
            z: -10,
            right: 20,
            top: 'middle',
            style: {
                fill: '#fff',
                text: '(56%)',
                font: '14px Microsoft YaHei',
                position:'left'
            }
        }
    ],
    series : [
        { 
            name:' ',
            type: 'bar',
            barWidth:10,
            silent:true,
            itemStyle: {
	            normal: {
	                color: '#18537b',
	                barBorderRadius: [10, 10, 10, 10]
	
	            }

        },
            barGap:'-100%',
            barCategoryGap:'50%',
            data: data.map(function(d){return xMax}),
        },
        {
            name:' ',
            type:'bar',
            barWidth:10,
            label: {normal: {show: false,position: 'right',formatter: '{c}%'}},
            data:[{
            	value:1000, 
            	itemStyle: {
                normal: {
                    barBorderRadius: [10, 10, 10, 10],
                    color: {
                        type: 'bar',
                        colorStops: [{
                            offset: 0,
                            color: '#00d1cc' // 100% 处的颜色
                            
                            //color:'#00facc'
                        }, {
                            offset: 1,
                            color: 'rgba(0,250,204,.3)' // 0% 处的颜色
                        }],
                        globalCoord: false, // 缺省为 false
                    }
                }
            }}]
        }
    ]
};
// 使用刚指定的配置项和数据显示图表。
myChart17.setOption(option);
}


function barCharts18(){
    var myChart18 = echarts.init(document.getElementById('footerMain18'));
	var data=[3000];
	var xMax=3664;
	var option = {
	tooltip:{
		show:true,
		formatter:"{b} {c}"
	},
	grid:{
		left:20,
		top:50,
		bottom:'0',
		right:20
	},
    xAxis : [
        {
            max:xMax,
            type : 'value',
            axisTick: {show: false},
	        axisLine: {show:false},
	        axisLabel: {show:false},
	        splitLine: {show: false}
        }
    ],
    yAxis : [
        {
            type : 'category',
            data : ['业务收款'],
            nameTextStyle:{color:'#b7ce9e',fontSize:'18px'},
           	axisLabel: {show:false},
            axisTick: {show: false},
	        axisLine: {show: false}
        }
    ],
    graphic: [
        {
            type: 'text',
            z: -10,
            left: 20,
            top: 'middle',
            style: {
                fill: '#fff',
                text: '745/3,664',
                font: '14px Microsoft YaHei'
            }
        },
        {
            type: 'text',
            z: -10,
            right: 20,
            top: 'middle',
            style: {
                fill: '#fff',
                text: '(32%)',
                font: '14px Microsoft YaHei',
                position:'left'
            }
        }
    ],
    series : [
        { 
            name:' ',
            type: 'bar',
            barWidth:10,
            silent:true,
            itemStyle: {
	            normal: {
	                color: '#18537b',
	                barBorderRadius: [10, 10, 10, 10]
	
	            }

        },
            barGap:'-100%',
            barCategoryGap:'50%',
            data: data.map(function(d){return xMax}),
        },
        {
            name:' ',
            type:'bar',
            barWidth:10,
            label: {normal: {show: false,position: 'right',formatter: '{c}%'}},
            data:[{
            	value:1000, 
            	itemStyle: {
                normal: {
                    barBorderRadius: [10, 10, 10, 10],
                    color: {
                        type: 'bar',
                        colorStops: [{
                            offset: 0,
                            color: '#00d1cc' // 100% 处的颜色
                            
                            //color:'#00facc'
                        }, {
                            offset: 1,
                            color: 'rgba(0,250,204,.3)' // 0% 处的颜色
                        }],
                        globalCoord: false, // 缺省为 false
                    }
                }
            }}]
        }
    ]
};
// 使用刚指定的配置项和数据显示图表。
myChart18.setOption(option);
}

function barCharts19(){
    var myChart19 = echarts.init(document.getElementById('footerMain19'));
	var data=[3000];
	var xMax=3664;
	var option = {
	tooltip:{
		show:true,
		formatter:"{b} {c}"
	},
	grid:{
		left:20,
		top:50,
		bottom:'0',
		right:20
	},
    xAxis : [
        {
            max:xMax,
            type : 'value',
            axisTick: {show: false},
	        axisLine: {show:false},
	        axisLabel: {show:false},
	        splitLine: {show: false}
        }
    ],
    yAxis : [
        {
            type : 'category',
            data : ['业务收款'],
            nameTextStyle:{color:'#b7ce9e',fontSize:'18px'},
           	axisLabel: {show:false},
            axisTick: {show: false},
	        axisLine: {show: false}
        }
    ],
    graphic: [
        {
            type: 'text',
            z: -10,
            left: 20,
            top: 'middle',
            style: {
                fill: '#fff',
                text: '326/3,664',
                font: '14px Microsoft YaHei'
            }
        },
        {
            type: 'text',
            z: -10,
            right: 20,
            top: 'middle',
            style: {
                fill: '#fff',
                text: '(45%)',
                font: '14px Microsoft YaHei',
                position:'left'
            }
        }
    ],
    series : [
        { 
            name:' ',
            type: 'bar',
            barWidth:10,
            silent:true,
            itemStyle: {
	            normal: {
	                color: '#18537b',
	                barBorderRadius: [10, 10, 10, 10]
	
	            }

        },
            barGap:'-100%',
            barCategoryGap:'50%',
            data: data.map(function(d){return xMax}),
        },
        {
            name:' ',
            type:'bar',
            barWidth:10,
            label: {normal: {show: false,position: 'right',formatter: '{c}%'}},
            data:[{
            	value:1000, 
            	itemStyle: {
                normal: {
                    barBorderRadius: [10, 10, 10, 10],
                    color: {
                        type: 'bar',
                        colorStops: [{
                            offset: 0,
                            color: '#00d1cc' // 100% 处的颜色
                            
                            //color:'#00facc'
                        }, {
                            offset: 1,
                            color: 'rgba(0,250,204,.3)' // 0% 处的颜色
                        }],
                        globalCoord: false, // 缺省为 false
                    }
                }
            }}]
        }
    ]
};
// 使用刚指定的配置项和数据显示图表。
myChart19.setOption(option);
}

function barCharts20(){
    var myChart20 = echarts.init(document.getElementById('footerMain20'));
	var data=[3000];
	var xMax=3664;
	var option = {
	tooltip:{
		show:true,
		formatter:"{b} {c}"
	},
	grid:{
		left:20,
		top:50,
		bottom:'0',
		right:20
	},
    xAxis : [
        {
            max:xMax,
            type : 'value',
            axisTick: {show: false},
	        axisLine: {show:false},
	        axisLabel: {show:false},
	        splitLine: {show: false}
        }
    ],
    yAxis : [
        {
            type : 'category',
            data : ['业务收款'],
            nameTextStyle:{color:'#b7ce9e',fontSize:'18px'},
           	axisLabel: {show:false},
            axisTick: {show: false},
	        axisLine: {show: false}
        }
    ],
    graphic: [
        {
            type: 'text',
            z: -10,
            left: 20,
            top: 'middle',
            style: {
                fill: '#fff',
                text: '364/3,664',
                font: '14px Microsoft YaHei'
            }
        },
        {
            type: 'text',
            z: -10,
            right: 20,
            top: 'middle',
            style: {
                fill: '#fff',
                text: '(42%)',
                font: '14px Microsoft YaHei',
                position:'left'
            }
        }
    ],
    series : [
        { 
            name:' ',
            type: 'bar',
            barWidth:10,
            silent:true,
            itemStyle: {
	            normal: {
	                color: '#18537b',
	                barBorderRadius: [10, 10, 10, 10]
	
	            }

        },
            barGap:'-100%',
            barCategoryGap:'50%',
            data: data.map(function(d){return xMax}),
        },
        {
            name:' ',
            type:'bar',
            barWidth:10,
            label: {normal: {show: false,position: 'right',formatter: '{c}%'}},
            data:[{
            	value:1000, 
            	itemStyle: {
                normal: {
                    barBorderRadius: [10, 10, 10, 10],
                    color: {
                        type: 'bar',
                        colorStops: [{
                            offset: 0,
                            color: '#00d1cc' // 100% 处的颜色
                            
                            //color:'#00facc'
                        }, {
                            offset: 1,
                            color: 'rgba(0,250,204,.3)' // 0% 处的颜色
                        }],
                        globalCoord: false, // 缺省为 false
                    }
                }
            }}]
        }
    ]
};
// 使用刚指定的配置项和数据显示图表。
myChart20.setOption(option);
}

function barCharts21(){
    var myChart21 = echarts.init(document.getElementById('footerMain21'));
	var data=[3000];
	var xMax=3664;
	var option = {
	tooltip:{
		show:true,
		formatter:"{b} {c}"
	},
	grid:{
		left:20,
		top:50,
		bottom:'0',
		right:20
	},
    xAxis : [
        {
            max:xMax,
            type : 'value',
            axisTick: {show: false},
	        axisLine: {show:false},
	        axisLabel: {show:false},
	        splitLine: {show: false}
        }
    ],
    yAxis : [
        {
            type : 'category',
            data : ['业务收款'],
            nameTextStyle:{color:'#b7ce9e',fontSize:'18px'},
           	axisLabel: {show:false},
            axisTick: {show: false},
	        axisLine: {show: false}
        }
    ],
    graphic: [
        {
            type: 'text',
            z: -10,
            left: 20,
            top: 'middle',
            style: {
                fill: '#fff',
                text: '756/3,664',
                font: '14px Microsoft YaHei'
            }
        },
        {
            type: 'text',
            z: -10,
            right: 20,
            top: 'middle',
            style: {
                fill: '#fff',
                text: '(25%)',
                font: '14px Microsoft YaHei',
                position:'left'
            }
        }
    ],
    series : [
        { 
            name:' ',
            type: 'bar',
            barWidth:10,
            silent:true,
            itemStyle: {
	            normal: {
	                color: '#18537b',
	                barBorderRadius: [10, 10, 10, 10]
	
	            }

        },
            barGap:'-100%',
            barCategoryGap:'50%',
            data: data.map(function(d){return xMax}),
        },
        {
            name:' ',
            type:'bar',
            barWidth:10,
            label: {normal: {show: false,position: 'right',formatter: '{c}%'}},
            data:[{
            	value:1000, 
            	itemStyle: {
                normal: {
                    barBorderRadius: [10, 10, 10, 10],
                    color: {
                        type: 'bar',
                        colorStops: [{
                            offset: 0,
                            color: '#00d1cc' // 100% 处的颜色
                            
                            //color:'#00facc'
                        }, {
                            offset: 1,
                            color: 'rgba(0,250,204,.3)' // 0% 处的颜色
                        }],
                        globalCoord: false, // 缺省为 false
                    }
                }
            }}]
        }
    ]
};
// 使用刚指定的配置项和数据显示图表。
myChart21.setOption(option);
}

//table
function addTable(){
	var dateJson={
		"default":
			[
			    {"num":"勘察设计收入","num1":413221 ,"num2":61670 ,"num3":"14.90%"},
				{"num":"咨询收入","num1":81343 ,"num2":6305 ,"num3":"7.80%"},
				{"num":"其他收入","num1":14836 ,"num2":2342 ,"num3":"15.80%"}
			]
		
	}
	
	var dateJson1={
		"default1":
			[
			    {"num":"勘察设计收入","num1":413221 ,"num2":61670 ,"num3":"14.90%","num4":"3.9pp"},
				{"num":"咨询收入","num1":81343 ,"num2":6305 ,"num3":"7.80%","num4":"5.0PP"},
				{"num":"其他收入","num1":14836 ,"num2":2342 ,"num3":"15.80%","num4":"2.9PP"}
			]
		
	}
	//
	$(".colorsBorder").css({"width":$(".mainCenterRange1").width()-10});
		//设置滚动条
		$(".tableScroll1").slimScroll({
			height: '200px',
			width:"100%",
			alwaysVisible: true,
		});
		$(".rangeTable ").find(".tableScroll1 > table").append($("<thead></thead>")
			.append($("<tr></tr>")
				.append($("<th>营运收入</th>"))
				.append($("<th>年预计完成</th>"))
				.append($("<th>4月已完成</th>"))
				.append($("<th>完成比例</th>"))
			)
		)
		for(var i=0;i<dateJson.default.length;i++){
			var tR=$("<tr></tr>").append($("<td>"+dateJson.default[i].num+"</td>"))
								.append($("<td>"+dateJson.default[i].num1+"</td>"))
								.append($("<td>"+dateJson.default[i].num2+"</td>"))
								.append($("<td>"+dateJson.default[i].num3+"</td>"))
			$(".rangeTable ").find(".tableScroll1 > table").append(tR)
		}
		
		$(".tableScroll2").slimScroll({
			height: '200px',
			width:"100%",
			alwaysVisible: true,
		});
		$(".rangeTable ").find(".tableScroll2 > table").append($("<thead></thead>")
			.append($("<tr></tr>")
				.append($("<th>营运支出</th>"))
				.append($("<th>年预计完成</th>"))
				.append($("<th>4月已完成</th>"))
				.append($("<th>完成比例</th>"))
				.append($("<th>完成比例同比增长</th>"))
			)
		)
		for(var i=0;i<dateJson1.default1.length;i++){
			var tR=$("<tr></tr>").append($("<td>"+dateJson1.default1[i].num+"</td>"))
								.append($("<td>"+dateJson1.default1[i].num1+"</td>"))
								.append($("<td>"+dateJson1.default1[i].num2+"</td>"))
								.append($("<td>"+dateJson1.default1[i].num3+"</td>"))
								.append($("<td>"+dateJson1.default1[i].num4+"</td>"))
			$(".rangeTable ").find(".tableScroll2 > table").append(tR);
		}
	
	//设置滚动条
	
	$(".centerTopBgC").each(function(index,element){
		$(this).click(function(){
			if(index == 0){
				$(".centerTopBgC").css({"color":"#aeaeae"});
				$(".centerTopBgC").eq(index).css({"color":"white"});
				$(".tableScrollLeft1").css({"display":"block"})
				$(".tableScrollLeft1").animate({left:"0px"});
				$(".tableScrollLeft2").css({"display":"block"});
				$(".tableScrollLeft2").animate({left:"100%"});
				$(".colorsBorder").animate({left:($(".mainCenterRange1").width())*index});
			}
			if(index == 1){
				$(".centerTopBgC").css({"color":"#aeaeae"});
				$(".centerTopBgC").eq(index).css({"color":"white"});
				$(".tableScrollLeft2").css({"display":"block"});
				$(".tableScrollLeft1").animate({left:"-100%"});
				$(".tableScrollLeft1").css({"display":"block"});
				$(".tableScrollLeft2").animate({left:"0px"});
				$(".colorsBorder").animate({left:($(".mainCenterRange1").width())*index});
			}
		});
	});
}



/*横向进度条仪表盘
可以设置多个 target value
*/
function barTimes1(){
	

//configs
var myChart6 = echarts.init(document.getElementById('mainCenters1'));
/*设置仪表盘的data值*/
$("#mainCentersData1 > .mainCentersData1L").text("271");
$("#mainCentersData1 > .mainCentersData1C").text("("+'1.15'+"%)");
$("#mainCentersData1 > .mainCentersData1R").text("200000");
$("#basis > .basis").text("("+'1.15%'+")");
var configs = {
    min: 0,
    max: 2000,
    serise:271,
    targetValue: {
        color: '#5aaf2e',
        value: [1160,1360]
    }
}
//根据target value ，生成相应位置的 graphic
function _settingVerticalGraphic(configs) {
    var graphic = [];
    var left = myChart6.convertToPixel({
        yAxisIndex: 0
    }, 0);
    left = parseFloat(left, 10).toFixed(2);
    left -= 8;
    var targetValue1 = configs.targetValue.value[0];
    var targetValue2 = configs.targetValue.value[1];
    var min = configs.min || 0;
    var max = configs.max || 100;
    if (targetValue1 > max || targetValue1 < min) {
        return;
    }
    var top1 = _getyAxisValueTop(targetValue1);
    var top2 = _getyAxisValueTop(targetValue2);
    graphic.push({
        z: 10,
        type: 'line',
        left: top1,
        shape: {
            x1: 0,
            x2: 2
        },
        style: {
            stroke: '#00d1cc',
            lineWidth: 15
        },
        silent: true,
        top: left
    });
    graphic.push({
        z: 10,
        type: 'line',
        left: top2,
        shape: {
            x1: 0,
            x2: 2
        },
        style: {
            stroke: '#00d1cc',
            lineWidth: 15
        },
        silent: true,
        top: left
    });
    return {
        graphic: graphic
    };
}

function _getyAxisValueTop(value) {
    var top = myChart6.convertToPixel({
        xAxisIndex: 0
    }, value);
    top -= 10;
    return parseFloat(top, 10).toFixed(2) - 2;
}

//防止自动 setOption
var option1 = {
    "tooltip": {
        "axisPointer": {
            "type": ""
        }
    },
    "grid": {
        "left": '5%',
        "right": '5%',
       "bottom": '60%'
    },
    "xAxis": {
        "show": true,
        "offset": -5,
        "type": "value",
        "interval": 1,
        "axisTick": {
            "show": false
        },
        "axisLine": {
            "show": false
        },
        "axisLabel": {
            "show": true,
            "showMinLabel": true,
            "showMaxLabel": true,
            formatter: function(val) {
            	for(var i=0;i<configs.targetValue.value.length;i++){
            		if (val === configs.min ) {
                    	//return val
                	}
                	if (val === configs.max ) {
                    	//return val
                	}
                	if (val === configs.targetValue.value[i]) {
                		var b=configs.max
                		val=(Math.round(configs.targetValue.value[i] / b * 10000) / 100.00 + "%")
                    	return val;
                	}
            	}
               
            },
            textStyle: {
			    color:'#fff',
			    baseline:"top"
			},
        },
        "splitLine": {
            "show": false
        },
        "max": configs.max,
        "min": configs.min
    },
    "yAxis": {
        "show": false,
        "type": "category"
    },
    "series": [{
        "name": "",
        "type": "bar",
        "barWidth": 10,
        "silent": true,
        "animation": false,
        "itemStyle": {
            "normal": {
                "color": "#444"
            }
        },
        "barGap": "-100%",
        "data": [2000],
         itemStyle: {
	            normal: {
	                color: '#18537b',
	                barBorderRadius: [10, 10, 10, 10]
	
	            }

        }
    }, {
        "name": "设计院投资份额",
        "type": "bar",
        "barWidth": 10,
        "label": {
            "normal": {
                "show": true,
                "position": "insideTopRight",
                "offset": [30, 0],
                "textStyle": {
                    "color": "#63869e"
                }
            }
        },
		data:[{
            	value:271, 
            	itemStyle: {
                normal: {
                    barBorderRadius: [10, 10, 10, 10],
                    color: {
                        type: 'bar',
                        colorStops: [{
                            offset: 0,
                            color: '#00d1cc' // 100% 处的颜色
                        }, {
                            offset: 1,
                            color: 'rgba(0,250,204,.3)' // 0% 处的颜色
                        }],
                        globalCoord: false, // 缺省为 false
                    }
                }
            }}]
    }],
};

function handleColor(option, configs) {
    if (option.series[1].data[0].value >= configs.targetValue.value) {
        //option.series[1].data[0].itemStyle.normal.color = configs.targetValue.color;
        //option.series[1].label.normal.textStyle.color = configs.targetValue.color;
    }
    return option;
}
myChart6.setOption(handleColor(option1, configs));
setTimeout(function() {
    
    option1.series[1].data[0].value = 271;
    myChart6.setOption(handleColor(option1, configs));
}, 1000);
//标记线
myChart6.setOption(_settingVerticalGraphic(configs), false);
}




function barTimes2(){
	

var myChart7 = echarts.init(document.getElementById('mainCenters2'));
/*设置仪表盘的data值*/
$("#mainCentersData2 > .mainCentersData1L").text("271");
$("#mainCentersData2 > .mainCentersData1C").text("("+'1.15%'+")");
$("#mainCentersData2 > .mainCentersData1R").text("200000");
$("#basis1 > .basis").text("("+'1.15'+"%)");
var configs1 = {
    min: 0,
    max: 1000,
    targetValue: {
        color: '#5aaf2e',
        value: [980],
    }
}

//根据target value ，生成相应位置的 graphic
function _settingVerticalGraphic1(configs1) {
    var graphic = [];
    var left1 = myChart7.convertToPixel({
        yAxisIndex: 0
    }, 0);
  //  alert(left)
    left1 = parseFloat(left1, 10).toFixed(2);
    left1 -= 8;
    var targetValue = configs1.targetValue.value;
    var min = configs1.min || 0;
    var max = configs1.max || 100;
    if (targetValue > max || targetValue < min) {
        return;
    }
    var top = _getyAxisValueTop1(targetValue);
    graphic.push({
        z: 10,
        type: 'line',
        left: top,
        shape: {
            x1: 0,
            x2: 2
        },
        style: {
            stroke: '#00d1cc',
            lineWidth: 15
        },
        silent: true,
        top: left1
    });
    return {
        graphic: graphic
    };
}

function _getyAxisValueTop1(value) {
    var top = myChart7.convertToPixel({
        xAxisIndex: 0
    }, value);
    top -= 10;
    return parseFloat(top, 10).toFixed(2) - 2;
}

//防止自动 setOption
var option2 = {
    "tooltip": {
        "axisPointer": {
            "type": ""
        }
    },
    "grid": {
        "left": '5%',
        "right": '5%',
       "bottom": '60%'
    },
    "xAxis": {
        "show": true,
        "offset": -5,
        "type": "value",
        "interval": 1,
        "axisTick": {
            "show": false
        },
        "axisLine": {
            "show": false
        },
        "axisLabel": {
            "show": true,
            "showMinLabel": true,
            "showMaxLabel": true,
            formatter: function(val) {
                for(var i=0;i<configs1.targetValue.value.length;i++){
                	if (val === configs1.min || val === configs1.max ) {
                    	//return val
                	}
                	if (val === configs1.targetValue.value[i]) {
                		var b=configs1.max
                		val=(Math.round(configs1.targetValue.value[i] / b * 10000) / 100.00 + "%")
                    	return val;
                	}
            	}
            },
            textStyle: {
			    color:'#fff'
			}
        },
        "splitLine": {
            "show": false
        },
        "max": configs1.max,
        "min": configs1.min
    },
    "yAxis": {
        "show": false,
        "type": "category"
    },
    "series": [{
        "name": "",
        "type": "bar",
        "barWidth": 10,
        "silent": true,
        "animation": false,
        "itemStyle": {
            "normal": {
                "color": "#444"
            }
        },
        "barGap": "-100%",
        "data": [1000],
         itemStyle: {
	            normal: {
	                color: '#18537b',
	                barBorderRadius: [10, 10, 10, 10]
	
	            }

        }
    }, {
        "name": "设计院网优平台覆盖",
        "type": "bar",
        "barWidth": 10,
        "label": {
            "normal": {
                "show": true,
                "position": "insideTopRight",
                "offset": [30, 0],
                "textStyle": {
                    "color": "#63869e"
                }
            }
        },
		data:[{
            	value:85261, 
            	itemStyle: {
                normal: {
                    barBorderRadius: [10, 10, 10, 10],
                    color: {
                        type: 'bar',
                        colorStops: [{
                            offset: 0,
                            color: '#00d1cc' // 100% 处的颜色
                            
                            //color:'#00facc'
                        }, {
                            offset: 1,
                            color: 'rgba(0,250,204,.3)' // 0% 处的颜色
                        }],
                        globalCoord: false, // 缺省为 false
                    }
                }
            }}]
    }],
};

function handleColor1(option, configs1) {
    if (option.series[1].data[0].value >= configs1.targetValue.value) {
        //option.series[1].data[0].itemStyle.normal.color = configs.targetValue.color;
        //option.series[1].label.normal.textStyle.color = configs.targetValue.color;
    }
    return option;
}
myChart7.setOption(handleColor1(option2, configs1));
setTimeout(function() {
    
    option2.series[1].data[0].value = 271;//
    myChart7.setOption(handleColor1(option2, configs1));
}, 1000);
//标记线
myChart7.setOption(_settingVerticalGraphic1(configs1), false);
}


