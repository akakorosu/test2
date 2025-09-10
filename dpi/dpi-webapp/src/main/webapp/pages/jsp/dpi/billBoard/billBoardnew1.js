

initData()
function initData(){
    //初始化搜索榜
    initDataGrid_Search()
    //初始化热词top10
    initDataGrid_Word()
}

function initDataGrid_Search(){
    $('#hotsearch').jqGrid({
        url :"/pages/jsp/dpi/billBoard/getWordList",
        postData:{},
        datatype : "json",
        mtype : "POST",
        height : 350,
        autowidth : true,
        colNames : [ '','关键词','搜索指数','排名','id','一级标签名称'],
        colModel : [
            {name : 'totalFlow',align : 'center',index : 'totalFlow',width:40,sortable:false,editable : true},
            {name : 'keyWord',align : 'left',index : 'keyWord',sortable:false,editable : true,hidden : false},
            {name : 'userTotal',align : 'center',index : 'userTotal',sortable:false,editable : true},
            {name : 'id',align : 'center',index : 'id',width:80,sortable:false,editable : true,hidden : false, cellattr:addCellAttr },
            {name : 'prodId',align : 'center',index : 'prodId',sortable:false,editable : true,hidden : true},
            {name : 'labelName1',align : 'center',index : 'labelName1',sortable:false,editable : true,hidden : true},
        ],
    })
}

function initDataGrid_Word(){
    alert(111)
    $('#grid-table1').jqGrid({
        url :"/pages/jsp/dpi/billBoard/getWordList",
        postData:{},
        datatype : "json",
        mtype : "POST",
        height : 350,
        autowidth : true,
        colNames : [ '','关键词','搜索指数','排名','id','一级标签名称'],
        colModel : [
            {name : 'totalFlow',align : 'center',index : 'totalFlow',width:40,sortable:false,editable : true},
            {name : 'keyWord',align : 'left',index : 'keyWord',sortable:false,editable : true,hidden : false},
            {name : 'userTotal',align : 'center',index : 'userTotal',sortable:false,editable : true},
            {name : 'id',align : 'center',index : 'id',width:80,sortable:false,editable : true,hidden : false, cellattr:addCellAttr },
            {name : 'prodId',align : 'center',index : 'prodId',sortable:false,editable : true,hidden : true},
            {name : 'labelName1',align : 'center',index : 'labelName1',sortable:false,editable : true,hidden : true},
        ],
//        rownumbers: true,
//        onSelectRow: function (rowId) {
//        	click_app(rowId);
//        },
//             loadComplete : function() { //滚动条
//                 $("#grid-table1").closest(".ui-jqgrid-bdiv").css({
//                     'overflow-y' : 'auto',
//                     'overflow-x' : 'hidden'
//                 });
//             },

    });
}
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
