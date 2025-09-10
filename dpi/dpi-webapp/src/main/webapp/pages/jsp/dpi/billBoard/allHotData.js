var city_map=new Map();
$(function () {

    get_json_data();

    show();
})
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
                city_map.set(dataArrays[i].properties.id,dataArrays[i].properties.name);
            }
        }
    });
}

function show(){
    $('#tab-film-1').html('');
    var html='<div class="today">实时热点排行榜<a class="back" href="javascript:history.back(-1)">返回</a></div>'
    html+='<div class="today-list">'+
        '<span class="todayList-1">排名</span>'+
        '<span class="todayList-2">关键词</span>'+
        '<span class="todayList-3">分类</span>'+
        '<span class="todayList-4">类型</span>'+
        '<span class="todayList-5">地区</span>'+
        '<span class="todayList-6">搜索指数</span>'+
        '</div>';
    var max=0;
    if(hotData.length>100){
        max=100;
    }else{
        max=hotData.length;
    }

    for(var i=0;i<max;i++){
        var label1="";
        if(hotData[i].contentLabelName1){
            label1=hotData[i].contentLabelName1;
        }
        var label2="";
        if(hotData[i].contentLabelName2){
            label2=hotData[i].contentLabelName2;
        }
        html+='<div class="today-list">'+
            '<span class="todayList-1"><label>'+(i+1)+'</label></span>'+
            '<span class="todayList-2">'+hotData[i].keyWord+'</span>'+
            '<span class="todayList-3">'+label1+'</span>'+
            '<span class="todayList-4">'+label2+'</span>'+
            '<span class="todayList-5">'+city_map.get(hotData[i].area)+'</span>'+
            '<span class="todayList-6">'+hotData[i].userTotal+'</span>'+
            '</div>';
    }
    $('#tab-film-1').append(html);
}
