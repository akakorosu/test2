$(function(){
    //从后台获取时间
    $.ajax({
        "url" : $.cxt + "/applicationRate/getTime",
        'data':{"code":1001},
        "type":"POST",
        "async": false,
        "dataType":"json",
        "success":function(data){
            var obj=JSON.parse(data);
            dateId=obj.msg;
            $('#dateId').val(dateId);
        }
    });

    initEcharts($('#dateId').val());

	$("#dateId").datepicker({
        autoclose:true,
        format:"yyyymmdd"
    }).on('changeDate', getTime);

    $("#details_1").click(function(){
        showPiForm("1");
    })
    $("#details_2").click(function(){
        showPiForm("2");
    })

    $('#exportData').click(function(){
        window.location.href=$.cxt + "/applicationRate/exportData?dateId="+$('#dateId').val();
    })
})
function getTime(ev){
	initEcharts($('#dateId').val())
}
function initEcharts(dateId){
	$.ajax({
        url:$.cxt + "/applicationRate/getApplicationData",
        data:{dateId:dateId},
        type:"post",
        dataType:"json",
        success:function(data){
            var data=JSON.parse(data);
            data=data.data;
            var hour_id=new Array();
            var yyjlSblFz=new Array();
            var yyjlSblFm=new Array();
            var yyllSblFz=new Array();
            var yyllSblFm=new Array();
            var all_yyjlSblFz=0;
            var all_yyjlSblFm=0;
            var all_yyllSblFz=0;
            var all_yyllSblFm=0;
            var yyjlSb=new Array();
            var yyllSb=new Array();
            for(var i in data){
                hour_id[hour_id.length]=data[i].hourId;
                yyjlSblFz[yyjlSblFz.length]=data[i].yyjlSblFz;
                yyjlSblFm[yyjlSblFm.length]=data[i].yyjlSblFm-data[i].yyjlSblFz;
                yyllSblFz[yyllSblFz.length]=data[i].yyllSblFz;
                yyllSblFm[yyllSblFm.length]=data[i].yyllSblFm-data[i].yyllSblFz;

                yyjlSb[yyjlSb.length]=(data[i].yyjlSblFz*100/data[i].yyjlSblFm).toFixed(2);
                yyllSb[yyllSb.length]=(data[i].yyllSblFz*100/data[i].yyllSblFm).toFixed(2);

                all_yyjlSblFz+=data[i].yyjlSblFz;
                all_yyjlSblFm+=data[i].yyjlSblFm;
                all_yyllSblFz+=data[i].yyllSblFz;
                all_yyllSblFm+=data[i].yyllSblFm;
            }

            all_yyjlSblFm>0?$('#jl_val').html((all_yyjlSblFz*100/all_yyjlSblFm).toFixed(2)+'%'):$('#jl_val').html("0%");
            all_yyllSblFm>0?$('#ll_val').html((all_yyllSblFz*100/all_yyllSblFm).toFixed(2)+'%'):$('#ll_val').html("0%");

            initLine(hour_id,yyjlSb,'应用记录数识别率','jl_zhe')
            initLine(hour_id,yyllSb,'应用流量识别率','ll_zhe')
        }
    })
}
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
function initHuan(fz,fm,desc,id){
    console.log(id);
    console.log(fz);
    console.log(fm);
    var chartDom = document.getElementById(id);
    echarts.dispose(chartDom);
    var myChart = echarts.init(chartDom);
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
            radius : [ '75', '90' ],
            x : '0%',
            itemStyle : labelFromatter,
            data : [ {
                name : 'other',
                value :fm,
                itemStyle : labelBottom
            }, {
                name : desc,
                value :fz,
                itemStyle : labelTop
            } ]
        } ]
    };
    myChart.setOption(option);
}

//折线图
function initLine(hour_id,data,desc,id){
    var chartDom = document.getElementById(id);
    echarts.dispose(chartDom);
    var myChart = echarts.init(chartDom);
    var option = {
        tooltip : {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
            },
            formatter: function(params) {
                var html=params[0].name+'时<br/>';
                html=html+desc+'：'+params[0].value+'%';
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
            data:hour_id
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

function showPiForm(type) {
    var dateId=$('#dateId').val();
    if(type==1){
        topwindow.showWindow({
            //窗口名称
            title : "应用记录数识别详情",
            //参数
            data : {"type":type,dateId:dateId},
            //url
            url : $.cxt + "/applicationRate/showForm",
            bottons : [{
                title : "确认",
                fun : function() {
                    topwindow.removeWindow();
                }
            }]
        });
    }else{
        topwindow.showWindow({
            //窗口名称
            title : "应用流量识别详情",
            //参数
            data : {"type":type,dateId:dateId},
            //url
            url : $.cxt + "/applicationRate/showForm",
            bottons : [{
                title : "确认",
                fun : function() {
                    topwindow.removeWindow();
                }
            }]
        });
    }

}
