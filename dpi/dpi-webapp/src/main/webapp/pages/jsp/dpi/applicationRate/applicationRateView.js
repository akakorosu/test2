$(function() {
    initTable();
});

function initTable(){
    //初始化DataGrid
    if(type==1){
        $('#grid-table').jqGrid({
            url : $.cxt + "/applicationRate/selectPageList",
            postData:{type:type,dateId:dateId},
            datatype : "json",
            mtype : "POST",
            height : topjqGridHeight(),
            autowidth : true,
            colNames : [ '小时','应用记录数识别数','应用记录数总数','应用记录数识别率'],
            colModel : [
                {name : 'hourId',align : 'center',index : 'hourId',editable : true},
                {name : 'yyjlSblFz',align : 'center',index : 'yyjlSblFz',editable : true },
                {name : 'yyjlSblFm',align : 'center',index : 'yyjlSblFz',editable : true },
                {name : 'yyjlSblPer',align : 'center',index : 'yyjlSblPer',editable : true,formatter :add},
            ],
            viewrecords : true,
            //rownumbers: true,
            //multiselect: true,
            rowNum : 10,
            rowList : [ 10, 20, 30 ],
            pager : '#grid-pager',
            loadComplete : topjqGridLoadComplete,
        });

        }else{
            $('#grid-table').jqGrid({
                url: $.cxt + "/applicationRate/selectPageList",
                postData: {type: type, dateId: dateId},
                datatype: "json",
                mtype: "POST",
                height: topjqGridHeight(),
                autowidth: true,
                colNames: ['小时', '应用流量识别数', '应用流量总数', '应用流量识别率'],
                colModel: [
                    {name: 'hourId', align: 'center', index: 'hourId', editable: true},
                    {name: 'yyllSblFz', align: 'center', index: 'yyjlSblFz', editable: true},
                    {name: 'yyllSblFm', align: 'center', index: 'yyllSblFm', editable: true},
                    {name: 'yyllSblPer', align: 'center', index: 'yyllSblPer', editable: true,formatter :add}
                ],
                viewrecords: true,
                //rownumbers: true,
                //multiselect: true,
                rowNum: 10,
                rowList: [10, 20, 30],
                pager: '#grid-pager',
                loadComplete: topjqGridLoadComplete,
            });
    }
}
function add(cellvalue, options, cell){
    return cellvalue+'%';
}