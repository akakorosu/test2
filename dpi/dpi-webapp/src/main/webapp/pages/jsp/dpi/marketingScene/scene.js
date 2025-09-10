
layui.use(['table','laypage','form','laydate','layer','element'], function(){
    //初始化
    let laydate = layui.laydate;
    let $ = layui.jquery;
    let table = layui.table;
    let laypage = layui.laypage;
    let layer = layui.layer;
    var form = layui.form;
    let element = layui.element;


    //变量
    let pageNum = 1;
    let _totalNum = 0; // 查询结果总数
    let pageLimit = 15; // 每页显示数

    //获取div
    let tablediv=document.querySelector("#tablediv");//表格div
    let setupdiv=document.querySelector("#setupdiv");//新增div
    let displayappsetdiv=document.querySelector("#displayappsetdiv");//新增APP域名配置
    let displayvideosetdiv=document.querySelector("#displayvideosetdiv");//新增视频内容配置-----------------
    let displaykeywordsetdiv=document.querySelector("#displaykeywordsetdiv");//新增关键词配置
    let appsetdiv=document.querySelector("#appsetdiv");//新增APP域名配置
    let videosetdiv=document.querySelector("#videosetdiv");//新增视频内容配置
    let keywordsetdiv=document.querySelector("#keywordsetdiv");//新增关键词配置
    let add1 = document.querySelector("#add1");
    let app = document.querySelector("#app");
    let appparent = document.querySelector("#appparent");
    let app2 = document.createElement('div')
    // let addinputs={"addinputs1":[],"addinputs2":[],"addinputs3":[]}
    //执行一个laydate实例
    //https://www.layui.com/doc/modules/laydate.html
    laydate.render({
        elem: '#startdate' //指定元素
    });
    laydate.render({
        elem: '#enddate' //指定元素
    });
    laydate.render({
        elem: '#dateonline' //指定元素
    });
    laydate.render({
        elem: '#dateoffline' //指定元素
    });
    setTimeout(() => {
        $("#query").click()
    }, 200);

    //获取分页所需数据：总数
    function getTotalNum(querydata) {
        $.ajax({
            type: "POST",
            url :  $.cxt+'/marketingScene/getSceneTotalNum',
            data :  querydata,
            async: false,
            dataType : "json",
            success : function(data) {
                _totalNum = data.totalnum;
            }
        });
    }

    //渲染table
    function showTableData(pageNum, querydata) {
        // console.log("showTableData(pageNum, querydata)")
        let data = getMergeData(pageNum, querydata);

        table.render({
            id: 'datatable'
            ,elem: '#tablelist'
            ,url: $.cxt + "/marketingScene/getSceneList"
            ,where: data//查询
            ,method: 'post'
            ,limit : 15
            ,size: 'sm'
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
                {field:'id', title:'序号',width: 60 }
                ,{field:'scene_text', title:'场景名称',minWidth: 120}
                ,{field:'app_name', title:'app应用名称'}
                ,{field:'app_codes', title:'产品ID'}
                ,{field:'video_code', title:'视频内容ID'}
                ,{field:'keys_strs', title:'关键词'}
                ,{field:'state_name', title:'状态',width: 75}
                ,{field:'operator_name', title:'操作人',width: 75}
                ,{field:'start_date', title:'开始时间',width:120}
                ,{field:'end_date', title:'结束时间',width:120}
                ,{fixed: 'right', minWidth: 205, align:'left', toolbar: '#barDemo'}
            ]]
            ,even: true
        });
        /* 			var checkStatus = table.checkStatus('video_id');
                    console.log(checkStatus.data) //获取选中行的数据
                    console.log(checkStatus.data.length) //获取选中行数量，可作为是否有选中行的条件
                    console.log(checkStatus.isAll ) //表格是否全选 */
    }
    //渲染分页
    function paging(count,querydata){
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
                    showTableData(presentpage, querydata);
                }
            }
        })
    }

    $("#query").on('click', function(){
        tableproccess();

    })
    function tableproccess(){
        // console.log("显示表格操作")
        tableShow();
        let querydata={
            "type_code":$("#typename option:selected").val()
            ,"scene_text":$("#scenetext").val()
            ,"app_name":$("#appname").val()
            ,"video_name":$("#videoname").val()
            ,"keys_strs":$("#keysstrs").val()
            ,"start_date":$("#startdate").val()
            ,"operator_code":$("#operator option:selected").val()
            ,"state_code":$("#statecode option:selected").val()
            ,"end_date":$("#enddate").val()
        }
        getTotalNum(querydata);
        showTableData(pageNum, querydata);
        paging(_totalNum,querydata);
    }



    table.on('tool(tablelist)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data //获得当前行数据
            ,layEvent = obj.event; //获得 lay-event 对应的值




        if(layEvent === 'detail'){
            app2 = getDiv(data);
            app.parentElement.appendChild(app2);
            app.style.display="none"
            form.render();

        } else if(layEvent === 'release'){
            layer.confirm('确认发布？', function(index){
                // obj.del(); //删除对应行（tr）的DOM结构
                layer.close(index);
                $.ajax({
                    type: "POST",
                    url :  $.cxt+'/marketingScene/fabu',
                    data : data,
                    async: false,
                    dataType : "json",
                    success : function(data) {
                        layer.msg(data.result);
                    }
                });
                tableproccess();
                //todo:发布-页面完成后做
            });
        } else if(layEvent === 'edit'){
            layer.msg('编辑操作');
            resetinputnum();
            changepage();
            form.render();
            //todo:编辑操作-新增功能完成后做
        }else if(layEvent === 'stop'){
            layer.confirm('确定暂停？', function(index){
                // obj.del(); //删除对应行（tr）的DOM结构
                layer.close(index);
                $.ajax({
                    type: "POST",
                    url :  $.cxt+'/marketingScene/stop',
                    data : data,
                    async: false,
                    dataType : "json",
                    success : function(data) {
                        layer.msg(data.result);
                    }
                });
                tableproccess();
                //向服务端发送删除指令
                //todo:发布-页面完成后做
            });
        }
        function resetinputnum() {
            inuptnum[3] = 1;
            inuptnum[4] = 1;
            inuptnum[5] = 1;
        }
        function changepage() {
            app2 = getDiv(data, true);
            app.parentElement.appendChild(app2);
            app.style.display = "none"
        }
    });

    //监听提交
    form.on('submit(formDemo)', function(obj){
        // console.log(obj)
        let data = obj.field;
        let app_names=obj.form.querySelectorAll(".app_name")
        let video_codes=obj.form.querySelectorAll(".video_code")
        // console.log(checkchecked(obj.form))
        // return false
        if(checkchecked(obj.form)){
            let labs=obj.form.querySelectorAll('.lab');
            console.log(labs)
            layer.msg("请选择APP产品名称")
            return false
        }
        data.app_name = "";
        data.app_codes = "";
        app_names.forEach((item)=>{
            data.app_codes += `${item.value.split("(")[0]}|`;
            data.app_name += `${item.value}|`
        })
        if(video_codes.length!=0){
            data.video_code =""
            video_codes.forEach((item)=>{
                data.video_code += `${item.value}|`
            })
            data.video_code = data.video_code.substring(0,data.video_code.length-1)
        }
        data.app_name = data.app_name.substring(0,data.app_name.length-1)
        data.app_codes = data.app_codes.substring(0,data.app_codes.length-1)

        $.ajax({
            type: "POST",
            url :  $.cxt+'/marketingScene/save',
            data : data,
            async: false,
            dataType : "json",
            success : function(data) {
                layer.msg(data.result);
                // $("#query2").click()
                // $("#query").click()
            }
        });
        backtomain()
        tableproccess();
        return false

    });

    function checkchecked(form){
        let labs=form.querySelectorAll('.lab');
        return [...labs].some(item=>item.style.display==="block")
    }
//切换div
    $("#setup").on('click', function(){
        setupShow();
    })
    $("#query2").on('click',()=>{
        tableShow();
    })
    $("#displayappsetdiv").on('click', ()=>{
        appsetShow();
    })
    $("#displayvideosetdiv").on('click', ()=>{
        videosetShow();
    })
    $("#displaykeywordsetdiv").on('click', ()=>{
        keywordsetShow();
    })

    function getMergeData(pageNum, querydata) {
        return {"pagenum": pageNum, ...querydata};
    }

    init();
    function init(){
        initDatePicker()
        initOperatorSelect()

        function initDatePicker() {
            let defaultDate = document.querySelectorAll('.datepicker');
            for (let i = 0; i<defaultDate.length; i++) {
                defaultDate[i].valueAsDate = new Date();
            }
        }
        //todo:初始化操作人列表
        function initOperatorSelect() {

        }
    }
    layui.$('#LAY-component-form-setval').on('click', function(){
        form.val('example', {
            "scene_text": "芒果TV“亲爱的自己“" // "name": "value"
            ,"video_name": "亲爱的自己"
            ,"app_name": "芒果TV"
            ,"rule_type": 2 //
            ,"flow_max": 500 //开关状态
            ,"go_times": 1
            ,"flow_time_circle": 7
        });
    });
    form.on('radio(beshared)', function (data) {
        if(data.value=="2"){
            hiderule()
        }else{
            showrule()
        }
    })

});


//查询产品
function queryprod2(node){
    let input=getSearchInput(node)//获取搜索
    let querystr = input.value;
    querystr = querystr.replace(/^\s+|\s+$/g,"");
    let labdiv= getLableDiv(node)
    $.ajax({
        type: "POST",
        url :  $.cxt+'/marketingScene/queryprod',
        data : {"querystr":querystr},
        async: false,
        dataType : "json",
        success : function(data) {
            labdiv.innerHTML=""
            data.res.forEach((item)=>{
                let ul=getUl(item);//生成ul
                ul.addEventListener("click",function (){
                    this.parentNode.parentNode.querySelector('input').value=this.dataset.val//赋值input
                    this.parentNode.style.display="none"//隐藏列表div
                })
                ul.innerHTML = `<li>${item.split("(")[0]}</li><li>(${item.split("(")[1]}</li>`;
                labdiv.appendChild(ul);
            })
            labdiv.style.display="block"//显示列表div
            function getUl(item) {
                let ul=document.createElement('ul');
                ul.style.float="left"
                ul.style.marginLeft="10px"
                ul.dataset.val=item;
                return ul;
            }
        }
    });
    function getSearchInput(node) {
        return node.parentNode.parentNode.querySelector("input");
    }
    function getLableDiv(node) {
        return node.parentNode.parentNode.querySelector(".lab");
    }


}
function hiderule(){
    console.log("hiderule")
    let rulediv = document.querySelectorAll(".rule");
    // console.log(rulediv)
    rulediv.forEach((item)=>{
        item.style.display="none"
    })
}
function showrule(){
    console.log("showrule")
    let rulediv = document.querySelectorAll(".rule");
    rulediv.forEach((item)=>{
        item.style.display="block"
    })
}
authority = 1;//权限
authorityinit();
function authorityinit(){
    $.ajax({
        type: "POST",
        url :  '/dpi/marketingScene/authority',
        data : {},
        async: false,
        dataType : "json",
        success : function(data) {
            // alert(data.result);
            authority=data.result

            // $("#query2").click()
            // $("#query").click()
        }
    });
}

