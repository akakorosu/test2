//时间

var time="";
var number="";
var differentindex = 999;
$(function(){
    //设置默认时间
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
   time=strYear+""+strMonth+""+strDay;*/  
	$.ajax({
		"data":{},
		"url" : $.cxt + "/operationsCenter/getOperationsCenterTime", 
		"type":"POST",
		 async: false,
		"dataType":"json",
		"success":function(data){
			var obj = JSON.parse(data);
			time=obj.data;
			
		}
	});
      
    
    	
   
    $('#time').text(time);
	//初始化
	getMessage();
	/*$("#top_level").click(function(){
		window.location.href=$.cxt + "/operationsCenter/showTopLevelDomain?time="+time;
	})*/
	$("#bottom_level").click(function(){
		window.location.href=$.cxt + "/operationsCenter/showTopLevelDomain?time="+time;
	})
	/*$("#URLFuzzyRecognition").click(function(){
		window.location.href=$.cxt + "/operationsCenter/urlFuzzyRecognition?time="+time;
	})*/
	$("#URLFuzzyRecognitionNum").click(function(){
		window.location.href=$.cxt + "/operationsCenter/urlFuzzyRecognition?time="+time;
	})
	/*$("#audit_rule_error").click(function(){
		window.location.href=$.cxt + "/operationsCenter/showAuditRuleError?time="+time;
	})*/
	$("#top_audit").click(function(){
		window.location.href=$.cxt + "/operationsCenter/showAuditRuleError?time="+time;
	})
	$("#substanceLabelNum").click(function(){
		window.location.href=$.cxt + "/operationsCenter/substanceLabelNum?time="+time;
	})
	/*$("#industry_label_audit").click(function(){
		window.location.href=$.cxt + "/operationsCenter/showIndustryLabelAudit?time="+time;
	})*/
	$("#inNum").click(function(){
		window.location.href=$.cxt + "/operationsCenter/showIndustryLabelAudit?time="+time;
	})
	/*$("#unidentified_ua").click(function(){
		window.location.href=$.cxt + "/operationsCenter/showUnidentifiedUa?time="+time;
	})*/
	$("#top_ua").click(function(){
		window.location.href=$.cxt + "/operationsCenter/showUnidentifiedUa?time="+time;
	})
	$("#echart2_total").click(function(){
		window.location.href=$.cxt + "/operationsCenter/showUnidentifiedUrl?time="+time+"&&typeId=1";
	})
	$("#echart3_total").click(function(){
		window.location.href=$.cxt + "/operationsCenter/showUnidentifiedUrl?time="+time+"&&typeId=2";
	})
	$("#echart4_total").click(function(){
		window.location.href=$.cxt + "/operationsCenter/showUnidentifiedUrl?time="+time+"&&typeId=0";
	})
	//$('[data-toggle="tooltip"]').tooltip();  
	$('[data-toggle="tooltip"]').tooltip({html : true });
})
//根据表单数据发起请求
function getMessage(){
	//获取稽核错误数量
	$.ajax({
		"data":{"time":time},
		"url":$.cxt + "/operationsCenter/getCount",
		"type":"POST",
		"dataType":"json",
		"success":function(data){
			var data=JSON.parse(data);
			if(data.code!="0"){
				return;
			}
			$("#top_audit").html(data.data.auditErrorCount);
			$("#top_ua").html(data.data.unidentifiedUaCount);
			$("#bottom_level").html(data.data.topLevelDomainCount);
		}
	});
	//获取未识别域名-智能识别信息
	$.ajax({
		"data":{"time":time},
		"url":$.cxt + "/operationsCenter/getRecordAndFlow",
		"type":"POST",
		 async:false,
		"dataType":"json",
		"success":function(data){
			var data=JSON.parse(data);
			if(data.code!="0"){
				return;
			}
			data=data.data;
			if(data.count=='' && data.count==null){
				$("#record").html(0+"条");
			}else{
				$("#record").html(data.count+"条");
			}
			
			number=data.count;
			//$("#record_proportion").html(data.totalPercent);
			if(data.flow!=null && data.flow!=''){
				var flowParse=(data.flow/1024/1024).toFixed(2);
				$("#flow").html(flowParse+"T");
			}else{
				$("#flow").html(0+"T");
			}
			
			//$("#flow_proportion").html(data.flowPercent);
//			//修改两个进度条
			//$("#record_proportion_bar").css("width",data.totalPercent);
			//$("#flow_proportion_bar").css("width",data.flowPercent);
			/*var not_record=parseInt(data.numberTotal)-parseInt(parseInt(data.count));
			var not_flow=parseInt(data.flowTotal)-parseInt(parseInt(data.flow));*/
		}
	});
	$.ajax({
		"data":{"time":time},
		"url":$.cxt + "/operationsCenter/getMatchAndAdd",
		"type":"POST",
		 
		"dataType":"json",
		"success":function(data){
			var data=JSON.parse(data);
			if(data.code!="0"){
				return;
			}
			var autoList=data.data.auto;
			var needList=data.data.need;
			var auto_count0=0;
			var auto_count1=0;
			var auto_count2=0;
			var need_count0=0;
			var need_count1=0;
			var need_count2=0;
			
			if(autoList.length>0){
				for(auto in autoList){
					auto=autoList[auto];
					if(auto.typeId=="0"){
						auto_count0=auto.count;
						
					}else if(auto.typeId=="1"){
						auto_count1=auto.count;
					}else{
						auto_count2=auto.count;
					}
				}
			}
			if(needList.length>0){
				for(need in needList){
					need=needList[need];
					if(need.typeId=="0"){
						need_count0=need.count;
					}else if(need.typeId=="1"){
						need_count1=need.count;
					}else{
						need_count2=need.count;
					}
				}
			}	
		    //环形图1		   
		    var sum1=parseInt(auto_count1)+parseInt(need_count1);
		    rangeCharts("echart2",sum1,number-sum1,"抓取标题");
		    $("#echart2_total").html(sum1+"条");	    
		    $("#echart2_auto").html(auto_count1);
		    $("#echart2_autoPar").html(sum1==0?("0.00%"):(auto_count1/sum1*100).toFixed(2)+"%");
		    $("#echart2_need").html(need_count1);
		    $("#echart2_needPar").html(sum1==0?("0.00%"):(need_count1/sum1*100).toFixed(2)+"%");
		    //环形图2
		    var sum2=parseInt(auto_count2)+parseInt(need_count2);
		    rangeCharts("echart3",sum2,number-sum2,"域名备案");
		    $("#echart3_total").html(sum2+"条");
		 
		    $("#echart3_auto").html(auto_count2);
		    $("#echart3_autoPar").html(sum1==0?("0.00%"):(auto_count2/sum2*100).toFixed(2)+"%");
		    $("#echart3_need").html(need_count2);
		    $("#echart3_needPar").html(sum1==0?("0.00%"):(need_count2/sum2*100).toFixed(2)+"%");
		    //环形图3
		    var sum0=parseInt(auto_count0)+parseInt(need_count0);
		    rangeCharts("echart4",sum0,number-sum0,"未自动识别域名");
		    $("#echart4_total").html(sum0+"条");
		}
	});
	//获取行业稽核数量
	$.ajax({
		"data":{"time":time},
		"url":$.cxt + "/operationsCenter/getInNum",
		"type":"POST",
		"dataType":"json",
		"success":function(data){
			var data=JSON.parse(data);
			if(data.code!="0"){
				return;
			}
			$("#inNum").empty();
			if(data.data.NUM==0){
				$("#inNum").html('0');
				}
			if(data.data.NUM!=null&&data.data.NUM!=undefined&&data.data.NUM!=''&&data.data.NUM!='null'){
				$("#inNum").html(data.data.NUM);
			}
			if(data.data.num==0){$("#inNum").html('0');}
			if(data.data.num!=null&&data.data.num!=undefined&&data.data.num!=''&&data.data.num!='null'){
				$("#inNum").html(data.data.num);
			}
			
		}
	});
	//获取内容标签稽核数量
	$.ajax({
		"data":{"time":time},
		"url":$.cxt + "/operationsCenter/substanceNum",
		"type":"POST",
		"dataType":"json",
		"success":function(data){
			var data=JSON.parse(data);
			if(data.code!="0"){
				return;
			}
		
			$("#substanceLabelNum").empty();
			$("#substanceLabelNum").html(data.data.substancenum);
			
			
		}
	});
	//获取URL模糊识别数量
	$.ajax({
		"data":{"date_id":time},
		"url":$.cxt + "/operationsCenter/getUrlNum",
		"type":"POST",
		"dataType":"json",
		"success":function(data){
			var data=JSON.parse(data);
			if(data.code!="0"){
				return;
			}
		
			$("#URLFuzzyRecognitionNum").empty();
			if(data.data.NUM==0){
				$("#URLFuzzyRecognitionNum").html('0');
				}
			if(data.data.NUM!=null&&data.data.NUM!=undefined&&data.data.NUM!=''&&data.data.NUM!='null'){
				$("#URLFuzzyRecognitionNum").html(data.data.NUM);
			}
			if(data.data.num==0){
				$("#URLFuzzyRecognitionNum").html('0');
				}
			if(data.data.num!=null&&data.data.num!=undefined&&data.data.num!=''&&data.data.num!='null'){
				$("#URLFuzzyRecognitionNum").html(data.data.num);
			}
			
		}
	});
}		
//var labelTop = {
//	normal : {
//		label : {
//			show : true,
//			position : 'center',
//			formatter : '{b}',
//			textStyle : {
//				baseline : 'bottom'
//			}
//		},
//		labelLine : {
//			show : false
//		}
//	}
//};
var labelFromatter = {
	normal : {
		label : {
			show : false,
			formatter : function(params) {
				//console.log(params);
				/*if(params.percent==0){
					return params.percent+ '%'
				}
				return Math.round((100 - params.percent)*100)/100 + '%'*/
				
				if(params.name=='other'){
					if(params.value=='0'){
						return '0.00%'
					}else{
						return (((number-params.value)*100)/number).toFixed(2) +'%';
					}
					
				}
			},
			textStyle : {
				baseline : 'middle',
				fontSize:16,
				color:'green',
				align : 'center' 
			}
		}
	},
}
var labelBottom = {
	normal : {
		color : '#ccc',
		label : {
			show : true,
			position : 'center'
		},
		labelLine : {
			show : false
		}
	},
	emphasis : {
		color :  '#ccc'  //'rgba(9,9,9,9)'
	}
};                   
// 环形图
function rangeCharts(id,num1,num2,det){
	var myRangeChart = echarts.init(document.getElementById(id));
	option = {
			 title: {
			        x: 'center',
			        y: '60',
			        itemGap: 50,
			        textStyle : {
			            color : '#666',
			            fontSize : 12,
			            fontWeight : 'normal'
			        },
			        subtextStyle : {
			            color : '#de4751',  //de4751
			            fontSize : 12
			        }
			    },
			color:[ '#3966ff','#fdad03'],     //原来的颜色 fdad03
			series : [ {
				type : 'pie',
				center: ['50%', '50%'],
				radius : [ '40', '50' ],
				x : '0%',
				itemStyle : labelFromatter,
				data : [ {
					name : 'other',
					value :num2,
					itemStyle : labelBottom
				}, {
					name : det,
					value :num1
//					itemStyle : labelTop
				} ]
			} ]
		};
	myRangeChart.setOption(option);
}

function logInfo(){
	window.location.href=$.cxt + "/operationsCenter/exportData"
	
}