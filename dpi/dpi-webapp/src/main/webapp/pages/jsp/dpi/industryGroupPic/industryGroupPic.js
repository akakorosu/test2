var dateId = "";
var areaId = "";
var sex = "";
var age = "";
//消除ajax 异步同步 赋值 失败
var date1 = "";
//var flag = 1;

$(function() {	
	if(areaId=="" &&sex==""){
	    //页面数据初始化
		getMessage("0","","");	
		dateId = date1;
	}
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
			$("#area").append(html);
		}
	});
	//选择城市
	$("#area").chosen({
		disable_search : true,
	}).change(function(option, checked) {
		areaId = $('#area option:selected').val();
		if(areaId=='0'){
			areaId='';
		}
		getMessage(dateId,areaId,sex);
	});
	//时间初始化
	$("#time1").datepicker({
        autoclose:true,
        format:"yyyymmdd",
        viewDate:dateId,
        onClose : getTime
    }).on('changeDate', getTime);	
	
	//选择性别
	$("#sex").chosen({
		disable_search : true,
	}).change(function(option, checked) {
		sex = $('#sex option:selected').text();
		if(sex=='全部'){
			sex='';
		}
		if(sex=='男'){
			sex='1';
		}
		if(sex=='女'){
			sex='2';
		}
		getPartMessage(dateId,areaId,sex);
		
		//getPartMessage1(dateId,areaId,sex,age);
	});			
});


//获取后台信息
function getMessage(dateId,areaId,sex){
	/**
	 * 获取年龄分布   //七大年龄的柱状图
	 */
	$.ajax({
		"data":{"dateId" : dateId,"areaId":areaId,"sex":sex},
		"url" : $.cxt + "/industrygrouppic/selectAgeEcharts", 
		"type":"POST",
		"dataType":"json",
		"async" : false,
		"success":function(data){
			var data=JSON.parse(data);		
			if(data.code!="0"){
				return;
			}
			
			getDate(data.data.date);   //给时间赋值
			initLeftBar(data);
			
		}
	});
	/**
	 * 获取app 占比
	 */
	$.ajax({
		"data":{"dateId" : dateId,"areaId":areaId,"sex":sex},
		"url" : $.cxt + "/industrygrouppic/selectTopAppdate", 
		"type":"POST",
		"dataType":"json",
		"async" : false,
		"success":function(data){
			var data=JSON.parse(data);		
			if(data.code!="0"){
				return;
			}
			getHtml1(data);
		}
	});
	/**
	 * 获取五大行业占比
	 */
	$.ajax({
		"data":{"dateId" : dateId,"areaId":areaId,"sex":sex},
		"url" : $.cxt + "/industrygrouppic/selectProdTypePer", 
		"type":"POST",
		"dataType":"json",
		"async" : false,
		"success":function(data){
			var data=JSON.parse(data);		
			if(data.code!="0"){
				return;
			}
			$("#manpercent").html(data.data.manPercent+"%");
			$("#womanpercent").html(data.data.womanPercent+"%");
			
			getHtml(data);
			
		}
	});

}


//选择性别部分刷新
function getPartMessage(dateId,areaId,sex){
	/**
	 * 获取年龄分布
	 */
	$.ajax({
		"data":{"dateId" : dateId,"areaId":areaId,"sex":sex},
		"url" : $.cxt + "/industrygrouppic/selectAgeEcharts", 
		"type":"POST",
		"dataType":"json",
		"async" : false,
		"success":function(data){
			var data=JSON.parse(data);		
			if(data.code!="0"){
				return;
			}			
			getDate(data.data.date);
			initLeftBar(data);
			
		}
	});
}

//选择性别部分刷新
function getPartMessage1(dateId,areaId,sex,age){
	/**
	 * 获取年龄分布
	 */
	$.ajax({
		"data":{"dateId" : dateId,"areaId":areaId,"sex":sex,"age":age},
		"url" : $.cxt + "/industrygrouppic/ageEchartsClickLeft", 
		"type":"POST",
		"dataType":"json",
		"async" : false,
		"success":function(data){
			var data=JSON.parse(data);		
			if(data.code!="0"){
				return;
			}
			getLeftAgeDistributionHtml(data);
		}
	});
	$.ajax({
		"data":{"dateId" : dateId,"areaId":areaId,"sex":sex,"age":age},
		"url" : $.cxt + "/industrygrouppic/ageEchartsClickRight", 
		"type":"POST",
		"dataType":"json",
		"async" : false,
		"success":function(data){
			var data=JSON.parse(data);		
			if(data.code!="0"){
				return;
			}
			getRightAgeDistributionHtml(data);
		}
	});
}
//消除ajax 赋值失败
function getDate(dateId){
	date1 = dateId;
	$("#time1").val(date1);
}
//时间
function getTime(ev){
	var year=ev.date.getFullYear().toString();
	var month=(ev.date.getMonth()+1)>9?(ev.date.getMonth()+1).toString():("0"+(ev.date.getMonth()+1).toString());
	var day=ev.date.getDate()>9?ev.date.getDate().toString():("0"+ev.date.getDate().toString());
	dateId=year+""+month+""+day;
	getMessage(dateId,areaId,sex);
}

//性别分布以及行业、app信息
function getHtml(data){	
	
	$("#1").html(data.data.data1);
	$("#2").html(data.data.data2);
	$("#3").html(data.data.data3);
	$("#4").html(data.data.data4);
	$("#5").html(data.data.data5);
	$("#6").html(data.data.data6);
	$("#7").html(data.data.data7);
	$("#8").html(data.data.data8);
	$("#9").html(data.data.data9);
	$("#10").html(data.data.data10);
	$("#21").html(data.data.data21);
	$("#22").html(data.data.data22);
	$("#23").html(data.data.data23);
	$("#24").html(data.data.data24);
	$("#25").html(data.data.data25);
	$("#26").html(data.data.data26);
	$("#27").html(data.data.data27);
	$("#28").html(data.data.data28);
	$("#29").html(data.data.data29);
	$("#30").html(data.data.data30);

}
function getHtml1(data){
	$("#11").html(data.data.data11);
	$("#12").html(data.data.data12);
	$("#13").html(data.data.data13);
	$("#14").html(data.data.data14);
	$("#15").html(data.data.data15);
	$("#16").html(data.data.data16);
	$("#17").html(data.data.data17);
	$("#18").html(data.data.data18);
	$("#19").html(data.data.data19);
	$("#20").html(data.data.data20);
	
	$("#31").html(data.data.data31);
	$("#32").html(data.data.data32);
	$("#33").html(data.data.data33);
	$("#34").html(data.data.data34);
	$("#35").html(data.data.data35);
	$("#36").html(data.data.data36);
	$("#37").html(data.data.data37);
	$("#38").html(data.data.data38);
	$("#39").html(data.data.data39);
	$("#40").html(data.data.data40);

}

//年龄分布柱状图    并根据柱状图第一个有数据的柱状图更新右边APP占比    行业占比     年龄段
function initLeftBar(data){
    var option = {
        color: ['#5EB2FF'],
        tooltip : {
            trigger: 'axis',
            axisPointer : {
                type : 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
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
        yAxis : [
            {
                type : 'category',
                data : ['65岁以上', '55-64', '45-54', '35-44', '25-34', '18-24', '18岁以下'],
                axisTick: {
                    alignWithLabel: true
                },
                axisLine:{
                    lineStyle:{
                        color:"#999",
                        opacity: 0
                    }
                },
            }
        ],
        series : [
            {
                name:'访问次数',
                type:'bar',
                barWidth: 20,
               
            	data:[data.data.age6, data.data.age5, data.data.age4, data.data.age3, data.data.age2, data.data.age1, data.data.age0]
            }
        ]
    };
    var myChart = echarts.init(document.getElementById('left1'));
    //自适应
    window.addEventListener("resize",function(){
 	   columnecharts.resize();    // 报错  以后查一下
	});
    
    myChart.setOption(option);
    //获取第一档 年龄数据不为空的分布
    //age0 到age6 其实是数据值
    var flag=1;
    if(data.data.age0>0&&flag==1){
    	getAgeGroupInformation(dateId,areaId,sex,"18岁以下");
    	flag = 0;
    }
    if(data.data.age1>0&&flag==1){
    	getAgeGroupInformation(dateId,areaId,sex,"18-24");
    	flag = 0;
    }
    if(data.data.age2>0&&flag==1){
    	getAgeGroupInformation(dateId,areaId,sex,"25-34");
    	flag = 0;
    }
    if(data.data.age3>0&&flag==1){
    	getAgeGroupInformation(dateId,areaId,sex,"35-44");
    	flag = 0;
    }
    if(data.data.age4>0&&flag==1){
    	getAgeGroupInformation(dateId,areaId,sex,"45-54");
    	flag = 0;
    }
    if(data.data.age5>0&&flag==1){
    	getAgeGroupInformation(dateId,areaId,sex,"55-64");
    	flag = 0;
    }
    if(data.data.age6>0&&flag==1){
    	getAgeGroupInformation(dateId,areaId,sex,"65岁以上");
    	flag = 0;
    }
    myChart.on('click', function (params) {
    	getAgeGroupInformation(dateId,areaId,sex,params.name);
    });
}

//终端分布图
function initBottomBar(){
    var option = {
        color: ['#5EB2FF'],
        tooltip : {
            trigger: 'axis',
            axisPointer : {
                type : 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                data : ['vivo x1', 'oppo R17', '华为mate20', 'iphoneXs', 'iphoneXs Plus', '坚果pro2', '一加6T'],
                axisTick: {
                    alignWithLabel: true
                },
                axisLine:{
                    lineStyle:{
                        color:"#999",
                        opacity: 0
                    }
                },
            }
        ],
        yAxis : [
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
                name:'直接访问',
                type:'bar',
                barWidth: "30%",
                data:[10, 52, 200, 334, 390, 330, 220]
            }
        ]
    };
    var myChart = echarts.init(document.getElementById('bottom1'));
    //自适应
    window.addEventListener("resize",function(){
 	   columnecharts.resize();
	});
    myChart.setOption(option);
}

//柱状图点击事件
function getAgeGroupInformation(dateId,areaId,sex,age){
	$.ajax({
		"data":{"dateId" : dateId,"areaId":areaId,"sex":sex,"age":age},
		"url" : $.cxt + "/industrygrouppic/ageEchartsClickLeft", 
		"type":"POST",
		"dataType":"json",
		"async" : false,
		"success":function(data){
			var data=JSON.parse(data);		
			if(data.code!="0"){
				return;
			}
			getLeftAgeDistributionHtml(data);
		}
	});
	$.ajax({
		"data":{"dateId" : dateId,"areaId":areaId,"sex":sex,"age":age},
		"url" : $.cxt + "/industrygrouppic/ageEchartsClickRight", 
		"type":"POST",
		"dataType":"json",
		"async" : false,
		"success":function(data){
			var data=JSON.parse(data);		
			if(data.code!="0"){
				return;
			}
			getRightAgeDistributionHtml(data);
		}
	});
}

//柱状图点击事件后续
function getLeftAgeDistributionHtml(data){
	$("#a0").html(data.data.a0);
	$("#a1").html(data.data.a1);
	$("#a2").html(data.data.a2);
	$("#a3").html(data.data.a3);
	$("#a4").html(data.data.a4);
	$("#agePer0").html(data.data.agePer0);
	$("#agePer1").html(data.data.agePer1);
	$("#agePer2").html(data.data.agePer2);
	$("#agePer3").html(data.data.agePer3);
	$("#agePer4").html(data.data.agePer4);
}
//柱状图点击事件后续
function getRightAgeDistributionHtml(data){
	$("#app0").html(data.data.app0);
	$("#app1").html(data.data.app1);
	$("#app2").html(data.data.app2);
	$("#app3").html(data.data.app3);
	$("#app4").html(data.data.app4);
	$("#appPer0").html(data.data.appPer0);
	$("#appPer1").html(data.data.appPer1);
	$("#appPer2").html(data.data.appPer2);
	$("#appPer3").html(data.data.appPer3);
	$("#appPer4").html(data.data.appPer4);
	$("#ageDistribution").html(data.data.ageDistribution);
}









