<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.bonc.com.cn/common/tag/cxt" prefix="cxt"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <cxt:commonLinks />
    <title>热搜风云榜</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/pages/jsp/dpi/billBoard/swiper.min.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/billBoard.css" />
    <!-- <link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/operateCenter.css" /> -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/pages/js/echarts4.min.js"></script>
    <script src="<%=request.getContextPath()%>/pages/jsp/dpi/capabilityPreview/Convert_Pinyin.js"></script>
    <script src="<%=request.getContextPath()%>/pages/jsp/dpi/billBoard/swiper.min.js"></script>
    <script type="text/javascript"   src="../layui/layui.all.js"></script>
    <script type="text/javascript"   src="../layui/layui.js"></script>
    <script type="text/javascript" src = "./echarts-wordcloud.js"></script>
    <link rel="stylesheet" href="../layui/css/layui.css" />

    <style>
        .tab-content{
            position:relative;
        }
        .whole{
            position:absolute;
            right:10px;
            bottom:0;
        }
        #label_list{
            position:relative;
        }
        #more_label{
            position:absolute;
            right:5px;
            bottom:10px;
        }
        .my_group{
            margin-left: 10px;
            margin-top: 10px;
        }
        #grid-table2 tr.jqgrow td,#grid-table1 tr.jqgrow td {
            white-space: normal;
            border-bottom:1px solid #EBEBEB;
        }
        #grid-table2_contentLabelName div,#grid-table2_userTotal div,#grid-table2_totalFlow div,#grid-table1_userTotal div,#grid-table1_totalFlow div{
            text-align:center;
        }
    </style>
    <style>
        input{
            margin-left:10px;
            outline-style: none ;
            border: 1px solid #ccc;
            border-radius: 3px;
            padding: 13px 14px;
            height: 31px;
            width: 150px;
            font-size: 14px;
            font-weight: 700;
            font-family: "Microsoft soft";
        }
        select{
            height: 33px;
            outline-style: none ;
            border: 1px solid #ccc;
            border-radius: 3px;
            width: 73%;
        }
        input:focus{
            border-color: #66afe9;
            outline: 0;
            -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,233,.6);
            box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,233,.6)
        }
    </style>
</head>
<body>
<form action="" class="form-inline" style="display: none">
    <div class="form-group my_group">
        <label>账期：</label>
        <input type="text" autocomplete="off" class="form-control datepicker-input" placeholder="不限" id="time" style="width:150px;">
    </div>
    <div class="form-group my_group">
        <label>地区：</label>
        <select name=""  class="chosen-select"  id="cityId" style="width: 150px;"></select>
    </div>
</form>

<%--<!-- 地图 -->--%>
<div class="bill-top" >
    <div class="bill-top-left"  >
        <ul id="myTabspot" class="nav nav-tabs">
            <li class="active">
                <a href="#hotspot" data-toggle="tab"  style="color:#333366;font-weight:700;margin-left:5px;: none;border-bottom:none;">
<%--                    a--%>
                    搜索榜
                </a>
            </li>
            <li>
                <a href="#concern" data-toggle="tab"></a>
            </li>
        </ul>
        <div id="myTabspotContent" class="tab-content tab-content-top" >
            <div class="tab-pane fade in active" style="overflow: hidden">
                <div class="grid-container" style="height:350px;" >
                    <table id="hotsearch"></table>
                </div>
            </div>
            <div class="tab-pane fade" id="concern">
                第二个页面
            </div>
        </div>
        <div class="allList" id="more_hot_data" style="display: none">
            完整榜单&emsp;
        </div>
    </div>
    <div class="bill-top-middle" id="main">
        <ul class="nav nav-tabs" style="height:5%;">
            <li class="active">
                <a href="#hotspot" data-toggle="tab"  style="color:#333366;font-weight:700;margin-left:5px;: none;border-bottom:none;">
                    热门词云
                </a>

            </li>
        </ul>
        <div id="map" class="bill-top-middle-map" style="height:400px;">
        </div>
    </div>

    <div class="bill-top-right" id="label_list">
        <ul class="nav nav-tabs">
            <li class="active">
                <a href="#hotspot" data-toggle="tab"  style="color:#333366;font-weight:700;margin-left:5px;: none;border-bottom:none;">
                    热词排行   TOP10
                </a>

            </li>
        </ul>
        <div id="data" class="col-xs-12">
<%--                <h4 class="text-bold" id='title_1'> </h4>--%>
                <%-- 表格容器 --%>
                <div class="grid-container" style="height:350px;" >
                    <table id="grid-table1"></table>
                </div>
        </div>
        <div class="allList" id="more_label" style="display: none">
            完整榜单&emsp;
        </div>
    </div>
</div>

<script>

</script>

<blockquote class="layui-elem-quote" style="margin-top: 50px">
    <table style="width:100%; font-size: 14px;">
        <tr width='100%'>

            <td style="font-weight:bold;">一级分类</td>
            <td style="padding-left: 30px;">
                <select id="class1" onchange="showclass2()" style="width:160px" > </select>
            </td>
            <td style="font-weight:bold; text-align: right;">二级分类</td>
            <td >
                <select id="class2"  style="width:160px" ><option value="">---请选择---</option> </select>
            </td>
            <td style="font-weight:bold; text-align: right;">关键词</td>
            <td >
                <input id="keyword"  style="text-align: center; color:#000000">
            </td>
            <td style="font-weight:bold; text-align: right;">开始时间</td>
            <td >
                <input type="date" id="startdate"  style="width:160px;text-align: center; color:#000000">
            </td>
            <td style="font-weight:bold; text-align: right;">结束时间</td>
            <td >
                <input type="date" id="enddate"  style="width:160px;text-align: center; color:#000000">
            </td>

<%--            <td style="font-weight: bold; text-align: right;">演员</td>--%>
<%--            <td>--%>
<%--                <input id="actor"  style="text-align: center; color:#000000">--%>
<%--            </td>--%>
            <td style="padding-right:2px; text-align: right;">
                <a href="javascript:;" class="layui-btn layui-btn-small" id="query" >
                    <i class="layui-icon">&#xe615;</i> 查询
                </a>
            </td>
<%--            <td style="font-weight:bold; text-align: right;">标签名</td>--%>
<%--            <td >--%>
<%--                <input id="lablename"  style="width:80px;text-align: center; color:#000000">--%>
<%--            </td>--%>
            <td style="padding-right:2px; text-align: right; width: 7%;">
                <a href="javascript:;" class="layui-btn layui-btn-small" id="lead" >
                    <i class="layui-icon">&#xe63c;</i> 导出
                </a>
            </td>
<%--            <td style=" text-align: right;">--%>
<%--                <a class="layui-icon layui-icon-down" id="more" style="font-size: 13px; color: #006400;">高级</a>--%>
<%--            </td>--%>
        </tr>
        <tr id="search-more" class="layui-hide" style="height: 38px;">
            <td style="font-weight:bold;  ">区域</td>
            <td>
                <input id="area"  style="text-align: center; color:#000000">
            </td>
            <td style="font-weight: bold;  text-align: right;">导演</td>
            <td>
                <input id="director"  style="text-align: center; color:#000000">
            </td>
        </tr>
        <tr id="search-more2" class="layui-hide" style="height: 38px;display:none">
            <td style="font-weight:bold; " >语言</td>
            <td>
                <input id="language"  style="text-align: center; color:#000000;">
            </td>
        </tr>
    </table>
</blockquote>

<table class="layui-hide" id="tablelist" lay-filter="tablelist"></table>
<div id="page" style="text-align: right; padding-right: 10px;margin-bottom:50px"></div>
</body>
</html>
<script type="text/javascript">

    var end = new Date();
    var day = ("0" + end.getDate()).slice(-2);
    var month = ("0" + (end.getMonth() + 1)).slice(-2);
    var today = end.getFullYear()+"-"+(month)+"-"+(day) ;
    console.log(today)
    $('#enddate').val(today);

    var start = new Date();
    start.setDate(start.getDate()-1);
    month =  ("0" + (start.getMonth() + 1)).slice(-2);
    day = ("0" + start.getDate()).slice(-2);
    today = start.getFullYear()+"-"+(month)+"-"+(day) ;
    console.log(today,month.length)
    $('#startdate').val(today);
    get_word()
    initDataGrid_Word()
    initDataGrid_Search()
    function get_word (){
        console.log("word")
        $.ajax({
            "data":{"startdate":$("#startdate").val(),"enddate":$("#enddate").val()},
            "url" :"${pageContext.request.contextPath}/billBoard/getwordcloud",
            "type":"POST",
            async: false,
            "dataType":"json",
            "success":function(data){
                var obj = JSON.parse(data);
                console.log(obj)
                // return false;
                var myChart = echarts.init(document.getElementById('map'));

                // 指定图表的配置项和数据
                var option = {
                    title: {
                        text: '',//标题
                        x: 'center',
                        textStyle: {
                            fontSize: 23
                        }

                    },
                    backgroundColor: '#ffffff',
                    tooltip: {
                        show: true
                    },
                    series: [{
                        name: '热点分析',//数据提示窗标题
                        type: 'wordCloud',
                        drawOutOfBound:true,
                        sizeRange: [14, 80],//画布范围
                        rotationRange: [-45, 90],//数据翻转范围
                        shape: 'circle',
                        textPadding: 0,
                        autoSize: {
                            enable: true,
                            minSize: 30
                        },
                        textStyle: {
                            normal: {
                                color: function() {
                                    return 'rgb(' + [
                                        Math.round(Math.random() * 160),
                                        Math.round(Math.random() * 160),
                                        Math.round(Math.random() * 160)
                                    ].join(',') + ')';
                                }
                            },
                            emphasis: {
                                shadowBlur: 10,
                                shadowColor: '#333'
                            }
                        },
                        data: obj.data//name和value建议用小写，大写有时会出现兼容问题
                    }]
                };


                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
            }
        });
    }
    // 基于准备好的dom，初始化echarts实例

    function initDataGrid_Word(){
        console.log("dataword")
        $('#grid-table1').jqGrid({
            url :"${pageContext.request.contextPath}/billBoard/getWordList",
            postData:{"startdate":$("#startdate").val(),"enddate":$("#enddate").val()},
            datatype : "json",
            mtype : "POST",
            height : 420,
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
        });
    }

    function initDataGrid_Search(){
        $('#hotsearch').jqGrid({
            url :"${pageContext.request.contextPath}/billBoard/getSearchList",
            postData:{"startdate":$("#startdate").val(),"enddate":$("#enddate").val()},
            datatype : "json",
            mtype : "POST",
            height : 420,
            autowidth : true,
            colNames : [ '','一级分类','二级分类','搜索指数','',''],
            colModel : [
                {name : 'totalFlow',align : 'center',index : 'totalFlow',width:40,sortable:false,editable : true},
                {name : 'contentLabelName1',align : 'left',index : 'contentLabelName1',sortable:false,editable : true,hidden : false},
                {name : 'contentLabelName2',align : 'center',index : 'contentLabelName2',sortable:false,editable : true},
                {name : 'userTotal',align : 'center',index : 'userTotal ',sortable:false,editable : true},
                {name : 'prodId',align : 'center',index : 'prodId',sortable:false,editable : true,hidden : true},
                {name : 'ids',align : 'center',index : 'ids',width:80,sortable:false,editable : true,hidden : false, cellattr:addCellAttr },
            ],
        })
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
</script>

<script>
    // var time = '';
    // var cityId = '';
    // var mapJson = "";
    // var mapType = "";
    // var city_data=new Array();
    // var city_map=new Map();//key:城市ID  value:城市名称
    // var mapData;
    // var contentLabelName1_array=new Array();
    // var hot_data="";
    // var label_map=new Map();
    $(function () {
        //选择时间
        $("#time").datepicker({
            autoclose: true,
            format: "yyyymmdd"
        }).on('changeDate', loadData);
        $("#time").val(time);
        function loadData() {
            time = $("#time").val();
        }
    })
    function showclass2(){
        let class1=$("#class1 option:selected").val()
        $.ajax({
            type: "POST",
            url :  $.cxt + "/billBoard/getclass2",
            data: {"contentLabelName1":class1},
            async: false,
            dataType : "json",
            success : function(data) {
                var catahtml = "";
                catahtml += '<option value="">---请选择---</option>'
                for(var i = 0;i < data.categorylist.length;i++){
                    catahtml += '<option value="'+data.categorylist[i].contentLabelName2+'">'+data.categorylist[i].contentLabelName2+'</option>';
                }
                $("#class2").empty().html(catahtml);
            }
        });
    }
    var pageNum = 1 ; // 当前第几页
    var _totalNum = 0; // 查询结果总数
    var pageLimit = 20; // 每页显示数

    layui.use(['table','laypage','form'], function(){
        var $ = layui.jquery;
        var table = layui.table;
        var laypage = layui.laypage;
        var form = layui.form;
        setTimeout(() => {
            $("#query").click()
        }, 200);
        initParameters();
        function initParameters(){
            $.ajax({
                type: "POST",
                url :  $.cxt + "/billBoard/initCatagory",
                async: false,
                dataType : "json",
                success : function(data) {
                    var catahtml = "";
                    catahtml += '<option value="">---请选择---</option>'
                    for(var i = 0;i < data.categorylist.length;i++){
                        catahtml += '<option value="'+data.categorylist[i].contentLabelName1+'">'+data.categorylist[i].contentLabelName1+'</option>';
                    }
                    $("#class1").empty().html(catahtml);
                }
            });
        }

        // $("#more").on('click', function(){
        //     if($("#search-more").hasClass("layui-hide")){
        //         $("#more").removeClass("layui-icon layui-icon-down").addClass("layui-icon layui-icon-up");
        //         $("#search-more").removeClass("layui-hide");
        //         $("#search-more2").removeClass("layui-hide");
        //     }else {
        //         $("#more").removeClass("layui-icon layui-icon-up").addClass("layui-icon layui-icon-down");
        //         $("#search-more").addClass("layui-hide");
        //         $("#search-more2").addClass("layui-hide");
        //     }
        // })

        $("#query").on('click', function(){
            var contentLabelName1= $('#class1 option:selected').val();
            var contentLabelName2= $('#class2 option:selected').val();
            var keyword = $('#keyword').val();
            var startdate = $('#startdate').val();
            var enddate = $('#enddate').val();
            console.log(enddate,startdate)

            get_word()
            getTotalNum(contentLabelName1, contentLabelName2, keyword,enddate,startdate);
            showTableData(pageNum,contentLabelName1, contentLabelName2, keyword,enddate,startdate);
            paging(_totalNum);
            $("#grid-table1").trigger("reloadGrid");
            $("#hotsearch").trigger("reloadGrid");
            $("#grid-table1").jqGrid('clearGridData');// 清空数据
            $("#hotsearch").jqGrid('clearGridData');// 清空数据
            $("#grid-table1").jqGrid('setGridParam',{
                url :"${pageContext.request.contextPath}/billBoard/getWordList",
                postData:{"startdate":$("#startdate").val(),"enddate":$("#enddate").val()},
                datatype : "json",
                mtype : "POST",
                height : 420,
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
            }).trigger("reloadGrid");
            $("#hotsearch").jqGrid('setGridParam',{
                url :"${pageContext.request.contextPath}/billBoard/getSearchList",
                postData:{"startdate":$("#startdate").val(),"enddate":$("#enddate").val()},
                datatype : "json",
                mtype : "POST",
                height : 420,
                autowidth : true,
                colNames : [ '','一级分类','二级分类','搜索指数','',''],
                colModel : [
                    {name : 'totalFlow',align : 'center',index : 'totalFlow',width:40,sortable:false,editable : true},
                    {name : 'contentLabelName1',align : 'left',index : 'contentLabelName1',sortable:false,editable : true,hidden : false},
                    {name : 'contentLabelName2',align : 'center',index : 'contentLabelName2',sortable:false,editable : true},
                    {name : 'userTotal',align : 'center',index : 'userTotal ',sortable:false,editable : true},
                    {name : 'prodId',align : 'center',index : 'prodId',sortable:false,editable : true,hidden : true},
                    {name : 'ids',align : 'center',index : 'ids',width:80,sortable:false,editable : true,hidden : false, cellattr:addCellAttr },
                ],
            }).trigger("reloadGrid");
        })

        var getTotalNum = function(contentLabelName1, contentLabelName2, keyword,enddate,startdate){
            $.ajax({
                type: "POST",
                url :  $.cxt + "/billBoard/getHotWordTotalnum",
                "data": {"contentLabelName1": contentLabelName1, "contentLabelName2": contentLabelName2, "keyWord": keyword ,"startdate":startdate,"enddate":enddate},
                async: false,
                dataType : "json",
                success : function(data) {
                    console.log(data);
                    _totalNum = data.totalnum;
                }
            });
            // $.ajax({
            //     type: "POST",
            //     url : $.cxt + '/billBord/getHotWordTotalnum',
            //     // "data": {"contentLabelName1": contentLabelName1, "contentLabelName2": contentLabelName2, "keyword": keyword},
            //     async: false,
            //     dataType : "json",
            //     success : function(data) {
            //         console.log(data);
            //         _totalNum = data.totalnum;
            //     }
            // });
        }

        var showTableData = function(pageNum,contentLabelName1, contentLabelName2, keyword,enddate ,startdate ){

            table.render({
                id: 'datatable'
                ,elem: '#tablelist'
                ,url: $.cxt + "/billBoard/getHotWordList"
                ,where: {"pagenum":pageNum,"contentLabelName1": contentLabelName1, "contentLabelName2": contentLabelName2, "keyWord": keyword,"startdate":startdate,"enddate":enddate}//查询
                ,method: 'post'
                ,limit : 20
                ,request :{
                    pageName: 'pagenum' //页码的参数名称，默认：page
                }
                ,response: {
                    statusName: 'resultCode' //规定数据状态的字段名称，默认：code
                    ,statusCode: 1 //规定成功的状态码，默认：0
                    ,msgName: 'resultMsg' //规定状态信息的字段名称，默认：msg
                    ,countName: 'totalnum' //规定数据总数的字段名称，默认：count
                    ,dataName: 'boardList' //规定数据列表的字段名称，默认：data
                }
                ,page: false
                ,defaultToolbar: ['filter']
                ,cols: [[
                    /* {type:'checkbox'} */
                    {field:'id', title:'序号' }
                    ,{field:'contentLabelName1', title:'一级分类名称'}
                    ,{field:'contentLabelName2', title:'二级分类名称'}
                    ,{field:'keyWord', title:'关键词'}
                    ,{field:'userTotal', title:'搜索指数'}
                    // ,{field:'release_date', title:'时间'}
                    // ,{field:'director', title:'导演'}
                    // ,{field:'actor', title:'演员'}
                    // ,{field:'video_rating', title:'评分',sort:'true'}
                    // ,{field:'view_counts', title:'播放量（热度）',sort:'true'}
                    //,{field:'video_id', title: '视频ID'}
                    //,{field:'language', title:'语言'}
                ]]
                ,even: true
            });
            /* 			var checkStatus = table.checkStatus('video_id');
                        console.log(checkStatus.data) //获取选中行的数据
                        console.log(checkStatus.data.length) //获取选中行数量，可作为是否有选中行的条件
                        console.log(checkStatus.isAll ) //表格是否全选 */

        }

        //分页
        var paging = function(count){
            var contentLabelName1= $('#class1 option:selected').val();
            var contentLabelName2= $('#class2 option:selected').val();
            var keyword = $('#keyword').val();
            var startdate = $('#startdate').val();
            var enddate = $('#enddate').val();
            // console.log(pageLimit,count)
            //layui分页
            laypage.render({
                elem:'page',
                count:count,
                limit: pageLimit,
                curr:1,
                layout:['prev','page','next','count'],
                jump:function (obj,first) {
                    var presentpage = obj.curr;
                    if(!first){
                        showTableData(presentpage, contentLabelName1, contentLabelName2, keyword,enddate,startdate);
                    }
                }
            })
        };

        //导出
        $("#lead").on('click', function(){
            // console.log(layui.table.cache["datatable"])
            var contentLabelName1= $('#class1 option:selected').val();
            var contentLabelName2= $('#class2 option:selected').val();
            var keyword = $('#keyword').val();
            layer.open({
                type: 0,
                title: '请确认筛选条件',
                skin: 'layui-layer-molv',
                closeBtn: 1,
                anim: 2,
                offset: '100px',
                time: 0,//不自动关闭
                area: ['400px', '300px'],
                content: '<ul class="layui-ul"><li>一级分类：'+ contentLabelName1 +'</li><li">二级分类：'+ contentLabelName2 +'</li><li>关键字：'+ keyword +'</li></ul>',
                btn: ['确定', '取消'],
                yes: function(index){
                    layer.close(index);
                    $.ajax({
                        type: "POST",
                        url :  $.cxt + '/billBoard/exportJob',
                        data :  {"contentLabelName1":contentLabelName1,"contentLabelName2": contentLabelName2, "keyWord": keyword},
                        /* async: false,  */
                        dataType : "json",
                        success : function(data) {
                            var code  =  data.resultCode;
                            var titleMsg = "";
                            if(code == 1){
                                titleMsg = "导入结果";
                            }else {
                                titleMsg = '温馨提示';
                            }
                            layer.open({
                                offset: '100px',
                                title: titleMsg
                                ,content: data.resultMsg
                            });
                        }
                    });
                }
            })
        })
    })

</script>

