$(function () {
    var html='';
    for(var i in contentLabelName1List){
        html+='<div class="label_list">';
        html+='<p onclick="choose_cate1(this)">'+contentLabelName1List[i]+'</p>';
        html+='<div ></div>';
        var labels=contentLabelName_map[contentLabelName1List[i]];
        for(var i in labels){
            if(labels[i]){
                html+='<a class="lab" onclick="choose_cate2(this)">'+labels[i]+'</a>'
            }
        }
        html+='</div>';
    }
    $('#main').html('');
    $('#main').html(html);
})
function choose_cate1(obj){
    var contentLabelName1=obj.innerText;
    window.location.href=$.cxt+"/billBoard/showData?contentLabelName1="+  encodeURI(encodeURI(contentLabelName1))+"&contentLabelName2="+encodeURI(encodeURI(""))+"&mapJson="+mapJson +"&cityId="+cityId+"&monthId="+time;
}

function choose_cate2(obj){
    var contentLabelName1=obj.parentNode.firstChild.innerText;
    var contentLabelName2=obj.innerText;
    window.location.href=$.cxt+"/billBoard/showData?contentLabelName1="+encodeURI(encodeURI(contentLabelName1))+"&contentLabelName2="+encodeURI(encodeURI(contentLabelName2))+"&mapJson="+mapJson +"&cityId="+cityId+"&monthId="+time;
}