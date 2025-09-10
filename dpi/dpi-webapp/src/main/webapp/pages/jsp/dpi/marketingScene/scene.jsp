<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.bonc.com.cn/common/tag/cxt" prefix="cxt"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <cxt:commonLinks />
<%--    <script type="text/javascript"   src="../layui/layui.all.js"></script>--%>
    <script type="text/javascript"   src="../layui/layui.js"></script>
    <script type="text/javascript"   src="../layui/jquery-3.1.1.min.js"></script>
    <link rel="stylesheet" href="../layui/css/layui.css" />
    <script type="text/javascript"   src="./scencetabs.js"></script>
    <script type="text/javascript"   src="./scene.js"></script>
    <title>实时营销场景</title>
</head>
<body style="margin-right: 100px" id="appparent">

    <div id="app">
    <blockquote class="layui-elem-field" style="margin-top: 30px">
        <table style="width:100%; font-size: 14px;margin: 15px">
            <tr width='100%'>
                <td style="font-weight:bold;">配置类别</td>
                <td style="padding-left: 10px;">
                    <select id="typename" style="width:150px" >
                        <option value="">---请选择---</option>
                        <option value="1">app域名配置</option>
                        <option value="2">视频内容配置</option>
                        <option value="3">关键词配置</option>
                    </select>
                </td>
                <td style="font-weight:bold; text-align: right;">场景名称</td>
                <td >
                    <input id="scenetext"  style="" />
                </td>
                <td style="font-weight:bold; text-align: right;">app应用名称</td>
                <td >
                    <input id="appname"  style=" color:#000000">
                </td>
            </tr>
            <tr id="search-more" class="" style="height: 38px;">
                <td style="font-weight:bold;  ">视频名称</td>
                <td>
                    <input id="videoname"  style=" color:#000000">
                </td>
                <td style="font-weight: bold;  text-align: right;">关键词名称</td>
                <td>
                    <input id="keysstrs"  style=" color:#000000">
                </td>
                <td style="font-weight:bold; text-align: right; ">场景生效时间</td>
                <td>
                    <input id="startdate" autocomplete="off" class="layui-input-sm" style=" color:#000000">
                </td>
            </tr>
            <tr id="search-more2" class="" style="height: 38px;">
                <td style="font-weight:bold; " >操作人</td>
                <td style="padding-left: 10px;">
                    <%--todo:从数据库表里面搂--%>
                    <select id="operator"  style="text-align: left; color:#000000;width:150px">
                        <option value="">---请选择---</option>
                        <option>秦培玉</option>
                        <option>丛林</option>
                    </select>
                </td>
                <td style="font-weight:bold; text-align: right" >业务状态</td>
                <td style="padding-left: 10px;">
                    <select id="statecode"  style="text-align: right; color:#000000;width:150px">
                        <option value="">所有</option>
                        <option value="1">待发布</option>
                        <option value="2">已发布</option>
                        <option value="3">已暂停</option>
                        <option value="4">执行中</option>
                        <option value="5">已失效</option>
                    </select>
                </td>
                <td style="font-weight:bold; text-align: right" >场景失效时间</td>
                <td >
                    <input id="enddate" autocomplete="off" class="layui-input-sm" style="text-align: left; color:#000000;"/>
                </td>

            </tr>
        </table>
    </blockquote>
    <div id="tablediv">
        <div style="display: flex;justify-content: flex-end">
            <a href="javascript:;" class="layui-btn layui-btn-sm layui-btn-normal" id="query" >
                <i class="layui-icon">&#xe615;</i> 查询
            </a>

            <a href="javascript:;" class="layui-btn layui-btn-sm layui-btn-warm" id="setup" >
                <i class="layui-icon">&#xe624;</i> 新增
            </a>

            <script>
                if(authority!="1"){
                    let setup=document.querySelector("#setup");
                    setup.style.display="none"
                }
            </script>
        </div>

        <table class="layui-hide table" id="tablelist" lay-filter="tablelist" style="float: right"></table>
        <script type="text/html" id="barDemo">

            <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
            <%--权限1为所有权限--%>
            {{# if(authority=="3"){ }}
            {{# }else if(authority=="1"){ }}
            {{# if(d.state_code =="1"){ }}
            <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="release">发布<i class="layui-icon">&#xe652;</i></a>
            <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit">编辑</a>
            {{# }if(d.state_code =="2") { }}
            <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="stop">暂停<i class="layui-icon">&#xe651;</i></a>
            {{# }if(d.state_code =="3") { }}
            <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="release">继续<i class="layui-icon">&#xe652;</i></a>
            <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit">编辑</a>
            {{# }if(d.state_code =="4") { }}
            <a  class="layui-btn layui-btn-normal layui-btn-xs" lay-event="stop">停止<i class="layui-icon">&#xe651;</i></a>
            {{# }if(d.state_code =="5") { }}
            <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="release">发布<i class="layui-icon">&#xe652;</i></a>
            <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit">编辑</a>

            {{# } }}
            {{# } }}
        </script>
        <div id="page" style="text-align: right; padding-right: 10px;margin-bottom:50px;"></div>
    </div>
    <div id="setupdiv" style="margin-top: 20px">

        <div style="display: flex;">
<%--            <a href="javascript:;" class="layui-btn layui-btn-sm layui-btn-normal" id="query2" style="position: relative">--%>
<%--                <i class="layui-icon">&#xe615;</i> 查询--%>
<%--            </a>--%>
            <div style="margin-right: auto;">
                <div  style="background-color: #00b3ee;width: 150px;height:25px;text-align: center;cursor: pointer;float: left;margin: 2px" id="displayappsetdiv" >
                     <font color="#ffffff" style="width: 170px;font-weight: bold;font-size:17px" >APP域名配置</font>
                </div>
                <div  style="background-color: #4c718a;width: 150px;height:25px;text-align: center;cursor: pointer;float: left;margin: 2px" id="displayvideosetdiv" >
                    <font color="#ffffff" style="width: 170px;font-weight: bold;font-size:17px" >视频内容配置</font>
                </div>
                <div  style="background-color: #4c718a;width: 150px;height:25px;text-align: center;cursor: pointer;float: left;margin: 2px" id="displaykeywordsetdiv" >
                    <font color="#ffffff" style="width: 170px;font-weight: bold;font-size:17px" >关键词配置</font>
                </div>
            </div>
            <div style="display: flex;justify-content: flex-end">
                <a href="javascript:;" class="layui-btn layui-btn-sm layui-btn-normal" id="query2" >
                    <i class="layui-icon">&#xe65c;</i> 返回
                </a>
<%--                <a href="javascript:;" class="layui-btn layui-btn-sm layui-btn-warm" id="" >--%>
<%--                    <i class="layui-icon">&#xe624;</i> 新增--%>
<%--                </a>--%>
            </div>
        </div>
        <hr class="layui-bg-black">
        <div id="appsetdiv" title="app域名配置" style="margin-top: 20px">
            <form class="layui-form" action="" lay-filter="example">
                <input name="state_name" value="待发布" type="hidden">
                <input name="state_code" value="1" type="hidden">
                <input name="type_code" value="1" type="hidden">
                <input name="type_name" value="app域名配置" type="hidden">
                <div>
                    <div class="layui-form-item" style="">
                        <label style="width: 150px" class="layui-form-label">营销场景名称：</label>
                        <input name="scene_text" required style=" width: 200px;color:#000000" lay-verify="required" autocomplete="off" class="layui-input-xs">
                    </div>
                    <div class="layui-form-item">
                        <label style="width: 150px" class="layui-form-label">APP应用名称：</label>
                        <div class="layui-input-inline" style="margin-right: 10px">
                            <input name="app_name" onkeydown="openFlag()" onkeyup="closeFlag(queryprod2,this)"  required style=" width: 200px;color:#000000" lay-verify="required" autocomplete="off" class="layui-input-xs app_name">
                        </div >

                        <script>
                            var flag = 0;
                            var t;
                            function openFlag (){
                                clearTimeout(t);
                                flag = 0;
                            }
                            function closeFlag(callback, para) {
                                t = setTimeout(function(){flag = 1; callback(para);}, 300);
                            }
                        </script>
                        <div class="layui-inline"  id="operatebtn">
<%--                            <div class="layui-inline" >--%>
<%--                                <button  style="margin-left: 30px" id="queryprodbtn" onclick="" type="button" class="layui-btn layui-btn-warm layui-btn-sm">--%>
<%--                                    <i class="layui-icon">&#xe615;</i>--%>
<%--                                </button>--%>
<%--                            </div>--%>
                            <div class="layui-inline" onclick="addinput(this,0)">
                                <button  style="margin-left: 30px;width: 36px" id="addappbtn" type="button" class="layui-btn layui-btn-warm layui-btn-sm">
                                    <i class="layui-icon">&#xe654;</i>
                                </button>
                            </div>
<%--                            <div class="layui-inline" onclick="removeinput(this,0)">--%>
<%--                                <button style="width: 36px;display: block"  type="button" class="layui-btn layui-btn-danger layui-btn-sm">--%>
<%--                                    <i class="layui-icon">&#xe67e;</i>--%>
<%--                                </button>--%>
<%--                            </div>--%>
                        </div>
                        <div class="layui-form-item lab" style="display:block;margin-left: 30px">

                        </div>
                    </div>
                </div>
                <hr class="layui-bg-black">
                <div class="layui-form-item" style="margin-top: 20px">
                    <label style="width: 150px" class="layui-form-label">触发规则：</label>
                    <div class="layui-input-block">
                        <input type="radio" name="rule_type" value="1" title="累加值" lay-filter="beshared" checked >
                        <input type="radio" name="rule_type" value="2" title="实时触发" lay-filter="beshared">
                    </div>
                </div>
                <div class="layui-form-item rule">
                    <label style="width: 150px" class="layui-form-label">流量累加阈值：</label>
                    <input name="flow_max"  style="width: 80px;color:#000000"  autocomplete="off" class="layui-input-xs layui-input-inline">
                    <div class="layui-word-aux">M</div>
                </div>
                <div class="layui-form-item">
                    <label style="width: 150px" class="layui-form-label">触发频次：</label>
                    <input name="go_times"  style="width: 100px;color:#000000"  autocomplete="off" class="layui-input-sm layui-input-inline">
                    <div class="layui-form-mid layui-word-aux">次</div>
                    <label style="width: 150px" class="layui-form-label">发送周期：</label>
                    <input name="flow_time_circle"  style="width: 100px;color:#000000"  autocomplete="off" class="layui-input-xs layui-input-inline">
                    <div class="layui-form-mid layui-word-aux">天</div>
                </div>
                <div class="layui-form-item ">
                    <label style="width: 150px" class="layui-form-label">场景上线：</label>
                    <input type="date" name="start_date" required style="width: 175px;color:#000000" lay-verify="required" autocomplete="off" class="layui-input-xs datepicker layui-input-inline">
                    <div class="layui-form-mid">
                        <i class="layui-icon">&#xe637;</i>
                    </div>
                    <label style="width: 123px" class="layui-form-label">场景下线：</label>
                    <input type="date" name="end_date" required style="width: 175px;color:#000000" lay-verify="required" autocomplete="off" class="layui-input-xs datepicker layui-input-inline">
                    <div class="layui-form-mid">
                        <i class="layui-icon">&#xe637;</i>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn layui-btn-sm layui-btn-normal" lay-submit lay-filter="formDemo">立即提交</button>
                        <button type="reset" class="layui-btn layui-btn-sm layui-btn-primary" onclick="resetinput(0)">重置</button>
                    </div>
                </div>
            </form>
        </div>
        <div id="videosetdiv" title="视频内容配置" style="display: none">
            <form class="layui-form" action="" lay-filter="example">
                <input name="state_name" value="待发布" type="hidden">
                <input name="state_code" value="1" type="hidden">
                <input name="type_code" value="2" type="hidden">
                <input name="type_name" value="视频内容配置" type="hidden">
                <div>
                    <div class="layui-form-item" style="">
                        <label style="width: 150px" class="layui-form-label">营销场景名称：</label>
                        <input name="scene_text" required style="width: 200px;color:#000000" lay-verify="required" autocomplete="off" class="layui-input-xs">
                    </div>

                    <div class="layui-form-item">
                        <label style="width: 150px" class="layui-form-label">APP应用名称：</label>
                        <div class="layui-input-inline" style="margin-right: 10px">
                            <input name="app_name" required onkeydown="openFlag()" onkeyup="closeFlag(queryprod2,this)" style=" width: 200px;color:#000000" lay-verify="required" autocomplete="off" class="layui-input-xs app_name">
                        </div >
                        <div class="layui-inline"  >
                            <div class="layui-inline add" onclick="addinput(this,1)">
                                <button  style="margin-left: 30px"  type="button" class="layui-btn layui-btn-warm layui-btn-sm">
                                    <i class="layui-icon">&nbsp;&#xe654;</i>
                                </button>
                            </div>
<%--                            <div class="layui-inline" onclick="removeinput(this,1)">--%>
<%--                                <button style="display: block"  type="button" class="layui-btn layui-btn-danger layui-btn-sm">--%>
<%--                                    <i class="layui-icon">&nbsp;&#xe67e;</i>--%>
<%--                                </button>--%>
<%--                            </div>--%>
                        </div>
                        <div class="layui-form-item lab" style="display:block;margin-left: 30px"></div>
                        <div class="layui-form-item content">
                            <label style="width: 150px" class="layui-form-label">视频内容ID：</label>
                            <div class="layui-input-inline" style="margin-right: 10px">
                                <input name="video_code" required style=" width: 200px;color:#000000" lay-verify="required" autocomplete="off" class="layui-input-xs video_code">
                            </div >
                            <div class="layui-form-mid layui-word-aux" style="margin-left: 25px">多个视频ID用“|”分割</div>
                        </div>
                    </div>


                </div>
                <hr class="layui-bg-black">
                <div class="layui-form-item" style="margin-top: 20px">
                    <label style="width: 150px" class="layui-form-label">触发规则：</label>
                    <div class="layui-input-block">
                        <input type="radio" name="rule_type" value="2" title="实时触发" checked>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label style="width: 150px" class="layui-form-label">场景上线：</label>
                    <input type="date" name="start_date" required style="width: 175px;color:#000000" lay-verify="required" autocomplete="off" class="layui-input-xs datepicker layui-input-inline">
                    <div class="layui-form-mid">
                        <i class="layui-icon">&#xe637;</i>
                    </div>
                    <label style="width: 123px" class="layui-form-label">场景下线：</label>
                    <input type="date" name="end_date" required style="width: 175px;color:#000000" lay-verify="required" autocomplete="off" class="layui-input-xs datepicker layui-input-inline">
                    <div class="layui-form-mid">
                        <i class="layui-icon">&#xe637;</i>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn layui-btn-sm layui-btn-normal" lay-submit lay-filter="formDemo">立即提交</button>
                        <button type="reset" class="layui-btn layui-btn-sm layui-btn-primary" onclick="resetinput(1)">重置</button>
                    </div>
                </div>
            </form>
        </div>
        <div id="keywordsetdiv" title="关键词配置" style="display: none" >
            <form class="layui-form" action="" lay-filter="example">
                <input name="state_name" value="待发布" type="hidden">
                <input name="state_code" value="1" type="hidden">
                <input name="type_code" value="3" type="hidden">
                <input name="type_name" value="关键词配置" type="hidden">
                <div>
                    <div class="layui-form-item" style="">
                        <label style="width: 150px" class="layui-form-label">营销场景名称：</label>
                        <input name="scene_text" required style=" width: 200px;color:#000000" lay-verify="required" autocomplete="off" class="layui-input-xs">
                    </div>
                    <div class="layui-form-item">
                        <label style="width: 150px" class="layui-form-label">关键词：</label>
                        <div class="layui-input-inline" style="margin-right: 10px">
                            <input name="keys_strs" required style=" width: 200px;color:#000000" lay-verify="required" autocomplete="off" class="layui-input-xs">
                        </div >
                        <div class="layui-form-mid layui-word-aux" style="margin-left: 25px">多个关键词用“|”分割</div>
                    </div>
                </div>
                <hr class="layui-bg-black">
                <div class="layui-form-item" style="margin-top: 20px">
                    <label style="width: 150px" class="layui-form-label">触发规则：</label>
                    <div class="layui-input-block">
                        <input type="radio" name="rule_type" value="2" title="实时触发" checked>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label style="width: 150px" class="layui-form-label">场景上线：</label>
                    <input type="date" name="start_date"  style="width: 175px;color:#000000" lay-verify="" autocomplete="off" class="layui-input-xs datepicker layui-input-inline">
                    <div class="layui-form-mid">
                        <i class="layui-icon">&#xe637;</i>
                    </div>
                    <label style="width: 123px" class="layui-form-label">场景下线：</label>
                    <input type="date" name="end_date" style="width: 175px;color:#000000" lay-verify="" autocomplete="off" class="layui-input-xs datepicker layui-input-inline">
                    <div class="layui-form-mid">
                        <i class="layui-icon">&#xe637;</i>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn layui-btn-sm layui-btn-normal" lay-submit lay-filter="formDemo">立即提交</button>
                        <button type="reset" class="layui-btn layui-btn-sm layui-btn-primary">重置</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    </div>
</body>
</html>
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
<%--src/main/webapp/pages/jsp/dpi/marketingScene/scene.jsp--%>
