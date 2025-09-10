<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String contextPath = request.getContextPath();
%>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" type="text/css" href="./addwords-static/css/table.css"/>
    <script type="text/javascript" src="./addwords-static/js/vue.js"></script>
    <script type="text/javascript" src="./addwords-static/js/element.js"></script>
    <title>表格</title>
</head>

<body>
<div id="body">
    <section1 class="addDiv">
        <div class="title flex flexhc flex-space-between">
            <span class="section-title">文本添加</span>
            <span class="btnAdd pointer" @click='handleAdd'><img src="./addwords-static/images/add.png"><span> 添加词库</span></span>
        </div>
        <el-input class="add-body" type="textarea" v-model="textarea" resize="none" :rows="3" placeholder="请在此处输入文本，分次以“,”隔开">
        </el-input>
    </section1>
    <section class="tableDiv">
        <div class="title flex flexvc flex-space-between">
            <span class="section-title">添加记录</span>
            <span @click='handleDel' class="pointer"><img :src="'./addwords-static/images/btnDel'+(multipleSelection.length===0?'':'-active')+'.png'"
                                                          alt=""></span>
        </div>
        <template>
            <el-table ref="multipleTable" :data="tableData.slice((currentPage-1)*pageSize,currentPage*pageSize)"
                      tooltip-effect="dark" header-row-class-name='table-header'
                      style="width: 100%" @selection-change="handleSelectionChange">
                <el-table-column label="全选" type="selection" align="center"></el-table-column>
                <el-table-column label="添加时间" prop="date" align="center"></el-table-column>
                <el-table-column prop="word" label="添加词语" align="center"></el-table-column>
                <el-table-column prop="user" label="添加人" align="center" show-overflow-tooltip></el-table-column>
                <el-table-column prop="state" label="状态" width='150'>
                    <template slot-scope="scope">
                        <div>
                            <span :class="scope.row.state=='1'?'spot spot-success':scope.row.state=='-1'?'spot spot-error':'spot spot-update'">{{scope.row.state==='1'?'添加成功':scope.row.state==='-1'?'添加失败':'更新中'}}</span>
                        </div>
                    </template>
                </el-table-column>
            </el-table>
        </template>
        <template>
            <div class="block">
                <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage"
                               :page-sizes="[6, 12, 18, 24]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper"
                               :total="tableTotalLength">
                </el-pagination>
            </div>
        </template>
    </section>
</div>
</body>
<script>

    var vm = new Vue({
        el: '#body',
        data: {
            textarea: '',
            tableData: [],
            multipleSelection: [],
            currentPage: '',//分页当前页
            pageSize: '',
            tableTotalLength:'', //总数
            num:''
        },
        methods: {
            handleAdd:function() {
                if(this.textarea.length>0){
                    var txt = this.textarea;
                    var url = "<%=contextPath%>/yuqing/dingzhi/add";
                    $.ajax({
                        url: url,
                        type: "post",
                        data: {"txt": txt},
                        success: function (data) {
                            if(data=="suc"){
                                alert("添加成功，系统正在更新词库，请随时关注添加状态");
                                var url = "<%=contextPath%>/yuqing/dingzhi/tianci";
                                $.ajax({
                                    url: url,
                                    type: "post",
                                    success: function (data) {
                                        $("#tianci_customization").html(data);
                                    },
                                    error: function () {
                                        $(".div-loading").css("display", "none");
                                        alert("请求失败");
                                    }
                                });
                            }else if(data=="def"){
                                alert("添加失败！请稍后重试");
                            }
                        },
                        error: function () {
                            alert("添加失败！请联系管理员");
                        }
                    });
                }
            },

            handleSelectionChange: function(size) {
                if(size.length>0){
                    num=JSON.stringify(size);
                }
                return num;
            },
            handleDel:function
                () {
                console.log(num);
                if (num.length > 0) {
                    this.$confirm('是否确认删除选中记录?', '', {
                        cancelButtonText: '取消',
                        confirmButtonText: '确定',
                        iconClass: 'icon-del',
                        cancelButtonClass: 'el-button el-button--text btn-cancel',
                        confirmButtonClass: 'el-button el-button--text btn-sure'
                    }).then(() => {
                        var code="1";
                        var list=num.split("},");
                        for(var i=0;i<list.length;i++){
                            var txt=list[i];
                            console.log(list[i]);
                            var url = "<%=contextPath%>/yuqing/dingzhi/delLogin";
                            $.ajax({
                                url: url,
                                type: "post",
                                data: {"txt": txt},
                                success: function (data) {
                                    if(data=="suc"){
                                        code="1";
                                    }else if(data=="def"){
                                        code="0";
                                    }
                                },
                                error: function () {
                                    code="0";
                                }
                            });
                        }
                        if(code=="1"){
                            alert("删除成功！请稍后查看");
                            var url = "<%=contextPath%>/yuqing/dingzhi/tianci";
                            $.ajax({
                                url: url,
                                type: "post",
                                success: function (data) {
                                    $("#tianci_customization").html(data);
                                },
                                error: function () {
                                    $(".div-loading").css("display", "none");
                                    alert("请求失败");
                                }
                            });
                        }else {
                            alert("删除失败！请联系管理员");
                        }
                }).catch(() => {
                        alert("取消删除");
                });
                }
            },
            handleSizeChange: function (size) {
                this.pageSize  = size;

            },
            handleCurrentChange: function(currentPage){
                this.currentPage = currentPage;
            }
        },

        created:function(){
            var self =this;
            var url = "<%=contextPath%>/yuqing/dingzhi/loginList";
            $.ajax({
                url: url,
                type: "post",
                success: function (data) {
                    self.pageSize=6;
                    self.currentPage=1;
                    self.tableData=data;
                    self.tableTotalLength=data.length;
                    self.multipleSelection=6;
                },
                error: function () {
                    alert("系统繁忙！请联系管理员");
                }
            });

        }
    })


</script>

</html>
