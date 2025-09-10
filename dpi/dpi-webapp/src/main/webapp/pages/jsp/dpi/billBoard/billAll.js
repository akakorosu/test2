var contentLabelName2_array=new Array();//小分类
var contentLabelName2_data_map=new Map(); //key：小分类   value：小分类的数据
var city_map=new Map();
$(function () {
    get_json_data()

    //拼接顶部的大分类
    group_top_label();
    //拼接左侧的小分类
    var array=contentLabelName_map[contentLabelName1];
    for(var i in array){
        if(array[i]){
            contentLabelName2_array[contentLabelName2_array.length]=array[i];
        }
    }
    if(!contentLabelName2){
        //点击更多跳转展示
        group_left_label("");
        //展示分类数据
        show_data(0);
    }else{
        //点击小分类或完整榜单时 跳转展示
        group_left_label(contentLabelName2);
        set_data();
        var html='';
        if(contentLabelName2=='全部'){
            html=more_data(contentLabelName1_data_list,contentLabelName1,contentLabelName2);
        }else{
            html=more_data(contentLabelName2_data_list,contentLabelName1,contentLabelName2);
        }
        $('#myTabContent-film').html('');
        $('#myTabContent-film').append(html);
    }
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

//拼接顶部标签
function group_top_label(){
    var html="";
    for(var i in contentLabelName1_list){
        if(contentLabelName1_list[i]){
            if(contentLabelName1==contentLabelName1_list[i]){
                html+='<li class="active" onclick="change_cate1(this)"><a href="#tab-0" data-toggle="tab">'+contentLabelName1_list[i]+'</a></li>'
            }else{
                html+='<li onclick="change_cate1(this)"><a href="#tab-1" data-toggle="tab">'+contentLabelName1_list[i]+'</a></li>'
            }
        }

    }
    html+='<li><a href="javascript:history.back(-1)">返回</a></li>'
    $('#myTab').html("");
    $('#myTab').append(html);
}
//拼接左侧标签
function group_left_label(contentLabelName2){
    var html="";
    if(!contentLabelName2){
        //点击更多标签时 contentLabelName2为空
        html='<li class="active"> <a href="#tab-film-0" data-toggle="tab" onclick="change_cate2(this)">'+contentLabelName1+'榜单</a></li>'
        html+='<li> <a href="#tab-film-'+(1)+'" data-toggle="tab" onclick="change_cate2(this)">全部</a></li>';
        for(var i=0;i<contentLabelName2_array.length;i++){
            html+='<li> <a href="#tab-film-'+(i+2)+'" data-toggle="tab" onclick="change_cate2(this)">'+contentLabelName2_array[i]+'</a></li>';
        }
    }else{
        //点击完整榜单时 contentLabelName2不为空
        html='<li> <a href="#tab-film-0" data-toggle="tab" onclick="change_cate2(this)">'+contentLabelName1+'榜单</a></li>'
        if(contentLabelName2=='全部'){
            html+='<li class="active"> <a href="#tab-film-'+(1)+'" data-toggle="tab" onclick="change_cate2(this)">全部</a></li>';
        }else{
            html+='<li> <a href="#tab-film-'+(1)+'" data-toggle="tab" onclick="change_cate2(this)">全部</a></li>';
        }
        for(var i=0;i<contentLabelName2_array.length;i++){
            if(contentLabelName2_array[i]==contentLabelName2){
                html+='<li class="active"> <a href="#tab-film-'+(i+2)+'" data-toggle="tab" onclick="change_cate2(this)">'+contentLabelName2_array[i]+'</a></li>';
            }else{
                html+='<li> <a href="#tab-film-'+(i+2)+'" data-toggle="tab" onclick="change_cate2(this)">'+contentLabelName2_array[i]+'</a></li>';
            }

        }
    }
    $('#myTab-film').html("");
    $('#myTab-film').append(html);
}
//显示全部数据
function show_data(n){
    set_data();
    $('#myTabContent-film').html('');
    $('#myTabContent-film').append('<div class="tab-pane fade in active" id="tab-film-'+n+'">');
    $('#tab-film-'+n).append(join_html(contentLabelName1_data_list,1,contentLabelName1,0));
    for(var i=0;i<contentLabelName2_array.length;i++){
        var html=join_html(contentLabelName2_data_map.get(contentLabelName2_array[i]),1,contentLabelName2_array[i],(i+1));
        $('#tab-film-'+n).append(html);
    }
}
//保存数据
function set_data(){
    for(var i=0;i<contentLabelName2_array.length;i++){
        contentLabelName2_data_map.set(contentLabelName2_array[i],new Array());
    }
    var array="";
    for(var i=0;i<contentLabelName1_data_list.length;i++){
        array=contentLabelName2_data_map.get(contentLabelName1_data_list[i].contentLabelName2);
        if(array){
            array[array.length]=contentLabelName1_data_list[i];
            contentLabelName2_data_map.set(contentLabelName1_data_list[i].contentLabelName2,array);
        }
    }
}
//拼接html
function join_html(data,num,contentLabelName2,n){
    var max=0;
    if(data.length>10){
        max=10;
    }else{
        max=data.length;
    }
    var html='<div class="bill-list">'

    if(n==0){
        html+='<div class="list-title">全部</div>';
    }else{
        html+='<div class="list-title">'+contentLabelName2+'</div>';
    }
    html+='<div class="clearfix" style="background:#f7f7f7;">' +
            '<span class="tab-list-show-1">排名</span>'+
            '<span class="tab-list-show-2">关键词</span>'+
            '<span class="tab-list-show-3">搜索指数</span>'+
          '</div>'
    for(var i=0;i<max;i++){
        html+='<div class="tab-list "><div class="tab-list-show">'+
                '<span class="tab-list-show-1"><label>'+(num+i)+'</label></span>'+
                '<span class="tab-list-show-2">'+data[i].keyWord+'</span>'+
                '<span class="tab-list-show-3">'+data[i].userTotal+'</span>'+
              '</div></div>';
    }
    html+='</div>';
    return html;
}
//更改顶部大类展示数据
function change_cate1(obj){
    contentLabelName1=obj.innerText;
    $.ajax({
        "data": {contentLabelName1:contentLabelName1, cityId: cityId, monthId: time},
        "url": $.cxt + "/billBoard/selectContentLabelName1Data",
        "type": 'POST',
        "dataType": "json",
        "success": function (data) {
            var data = JSON.parse(data);
            contentLabelName1_data_list = data.data;
            var array=contentLabelName_map[contentLabelName1]
            contentLabelName2_array.length=0;
            for(var i in array){
                if(array[i]){
                    contentLabelName2_array[contentLabelName2_array.length]=array[i];
                }
            }
            group_left_label();
            show_data(contentLabelName1_list.indexOf(contentLabelName1));
        }
    });
}

//更改左侧小类展示数据
function change_cate2(obj){
    var contentLabelName2=obj.innerText;
    var index=obj.getAttribute("href").split("-")[2];
    var contentLabelName1=$('#myTab li.active').text()
    $('#myTabContent-film').html('');
    var html='';
    if(index==0){
        show_data(contentLabelName1_list.indexOf(contentLabelName1));
    }else if(index==1){
        html=more_data(contentLabelName1_data_list,contentLabelName1,"")
    }else{
        html=more_data(contentLabelName2_data_map.get(contentLabelName2),contentLabelName1,contentLabelName2);
    }
    $('#myTabContent-film').append(html);
}
function more_data(data,contentLabelName1,contentLabelName2){
    var title="";
    if(contentLabelName1=='旅游'||contentLabelName1=='汽车'){
        if(contentLabelName2==""){
            title=contentLabelName1;
        }else{
            title=contentLabelName2;
        }

    }else{
        title=contentLabelName2+contentLabelName1;
    }
    var html='<div class="tab-pane fade in active">';
        html+='<div class="today">今日'+title+'排行榜</div>'
        html+='<div class="today-list">'+
                '<span class="todayList-1">排名</span>'+
                '<span class="todayList-2">关键词</span>'+
                '<span class="todayList-3">类型</span>'+
                '<span class="todayList-4">地区</span>'+
                '<span class="todayList-5">搜索指数</span>'+
               '</div>';

    for(var i=0;i<data.length;i++){
        var c2="";
        if(data[i].contentLabelName2){
            c2=data[i].contentLabelName2;
        }
        html+='<div class="today-list">'+
                '<span class="todayList-1"><label>'+(i+1)+'</label></span>'+
                '<span class="todayList-2">'+data[i].keyWord+'</span>'+
                '<span class="todayList-3">'+c2+'</span>'+
                '<span class="todayList-4">'+city_map.get(data[i].area)+'</span>'+
                '<span class="todayList-5">'+data[i].userTotal+'</span>'+
            '</div>';
    }
    return html+'</div>';
}