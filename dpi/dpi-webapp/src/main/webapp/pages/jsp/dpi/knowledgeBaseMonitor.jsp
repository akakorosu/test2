<%--
  Created by IntelliJ IDEA.
  User: liuyang_web
  Date: 2018/9/12
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.bonc.com.cn/common/tag/cxt" prefix="cxt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bonc.system.dao.entity.SysUser"%>
<%@ page import="com.bonc.system.dao.entity.SysRoleUser"%>
<%@ page import="com.bonc.common.cst.CST"%>
<!DOCTYPE html>
<html>
<head>
    <cxt:commonLinks />
    <meta charset='utf-8'>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/bootstrap-multiselect.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/common.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/operateCenter.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/knowledgeBaseMonitor.css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/pages/js/bootstrap-multiselect.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/pages/jsp/dpi/knowledgeBaseMonitor.js"></script>
    <title>知识库监控</title>
</head>
<body>
<form action="" class="form-inline">
    <div class="form-group">
        <label>时间选择：</label>
        <input type="text" class="form-control datepicker-input" placeholder="不限">
    </div>
    <div class="form-group">
        <label>地域：</label>
        <select name=""  multiple class="chosen-multiple" data-placeholder="不限">
            <option value="1">北京</option>
            <option value="2">上海</option>
            <option value="3">广州</option>
            <option value="4">深圳</option>
            <option value="5">南京</option>
        </select>
    </div>
    <div class="form-group">
        <label>性别：</label>
        <select name=""  class="chosen-select" data-placeholder="不限">
            <option value="0"></option>
            <option value="1">男</option>
            <option value="2">女</option>
        </select>
    </div>
</form>
<div class="num-main row">
    <span class="icon-question">?</span>
    <div class="col-xs-6 clearfix">
        <div class="pull-left echarts-div">
            <%--环形图容器--%>
            <div class="echarts-container echarts-pie pull-right"></div>
        </div>
        <div class="num-dec pull-left">
            <p class="dec-main">应用记录识别率</p>
            <p class="dec-text">不含ip识别率<span class="num-blue">93%</span></p>
        </div>
    </div>
    <div class="col-xs-6 clearfix" style="border-left:1px solid #eee;">
        <div class="pull-left echarts-div">
            <%--环形图容器--%>
            <div class="echarts-container echarts-pie pull-right"></div>
        </div>
        <div class="num-dec pull-left">
            <p class="dec-main">应用流量识别率</p>
            <p class="dec-text">不含ip识别率<span class="num-blue">93%</span></p>
        </div>
    </div>
</div>
<div class="num-list row">
    <div class="col-xs-12">
        <h4 class="text-center">规则库</h4>
    </div>
    <%-- 柱图模块1 --%>
    <div class="col-xs-6 num-list-bar">
        <div class="list-title list-title3">域名</div>
        <div class="pull-left bar-list-num">
            <p>活跃</p>
            <p><span class="num-weight">1000,010</span></p>
            <br>
            <p>新增</p>
            <p><span class="num-weight num-weight-small">1000,010</span></p>
        </div>
        <div class="echarts-div">
            <%--柱形图容器--%>
            <div class="echarts-container echarts-bar"></div>
            <p class="text-center">标签域名分布</p>
        </div>
    </div>
    <%-- 柱图模块2 --%>
    <div class="col-xs-6 num-list-bar">
        <div class="list-title list-title3">产品</div>
        <div class="pull-left bar-list-num">
            <p>活跃</p>
            <p><span class="num-weight">1000,010</span></p>
            <br>
            <p>新增</p>
            <p><span class="num-weight num-weight-small">1000,010</span></p>
        </div>
        <div class="echarts-div">
            <%--柱形图容器--%>
            <div class="echarts-container echarts-bar"></div>
            <p class="text-center">标签产品分布</p>
        </div>
    </div>
    <%-- 柱图模块3 --%>
    <div class="col-xs-6 num-list-bar no-bottom-border">
        <div class="list-title list-title10">标签</div>
        <div class="pull-left bar-list-num">
            <p>活跃</p>
            <p><span class="num-weight">1000,010</span></p>
            <br>
            <p>新增</p>
            <p><span class="num-weight num-weight-small">1000,010</span></p>
        </div>
        <div class="echarts-div">
            <%--柱形图容器--%>
            <div class="echarts-container echarts-bar"></div>
            <p class="text-center">标签层级分布</p>
        </div>
    </div>
    <%-- 柱图模块4 --%>
    <div class="col-xs-6 num-list-bar no-bottom-border">
        <span class="i-icon-add">+</span>
        <div class="list-title list-title4">内容规则</div>
        <div class="pull-left bar-list-num">
            <p>活跃</p>
            <p><span class="num-weight">1000,010</span></p>
            <br>
            <p>新增</p>
            <p><span class="num-weight num-weight-small">1000,010</span></p>
        </div>
        <div class="echarts-div">
            <%--柱形图容器--%>
            <div class="echarts-container echarts-bar"></div>
            <p class="text-center">深度规则分布</p>
        </div>
    </div>
</div>
<div class="num-list row">
    <div class="col-xs-12">
        <h4 class="text-center">内容库</h4>
    </div>
    <div class="col-xs-3 num-list-pie num-list-table">
        <div class="list-title list-title5">
            小说库
            <span class="i-icon-dec">?</span>
        </div>
        <p class="clearfix">
            <span class="pull-left" style="padding-top:12px;">识别率</span>
            <span class="pull-right num-weight">20.98%</span>
        </p>
        <div class="progress">
            <div class="progress-bar" role="progressbar" aria-valuemin="0" aria-valuemax="100" style="width: 20.98%;">
            </div>
        </div>
        <table>
            <tr>
                <td>
                    <p class="text-center bold">活跃产品</p>
                    <p class="text-center">1,002,458</p>
                </td>
                <td>
                    <p class="text-center bold">活跃标签</p>
                    <p class="text-center">10,024</p>
                </td>
                <td>
                    <p class="text-center bold">活跃内容</p>
                    <p class="text-center">1,002</p>
                </td>
            </tr>
            <tr>
                <td class="no-bottom-border">
                    <p class="text-center bold">新增内容</p>
                    <p class="text-center">2,458</p>
                </td>
                <td class="no-bottom-border">
                    <p class="text-center bold">使用率</p>
                    <p class="text-center">10.02%</p>
                </td>
                <td class="no-bottom-border">
                    <p class="text-center bold">错误率</p>
                    <p class="text-center">1.002%</p>
                </td>
            </tr>
        </table>
    </div>
    <div class="col-xs-3 num-list-pie num-list-table">
        <div class="list-title list-title5">
            小说库
            <span class="i-icon-dec">?</span>
        </div>
        <p class="clearfix">
            <span class="pull-left" style="padding-top:12px;">识别率</span>
            <span class="pull-right num-weight">20.98%</span>
        </p>
        <div class="progress">
            <div class="progress-bar" role="progressbar" aria-valuemin="0" aria-valuemax="100" style="width: 20.98%;">
            </div>
        </div>
        <table>
            <tr>
                <td>
                    <p class="text-center bold">活跃产品</p>
                    <p class="text-center">1,002,458</p>
                </td>
                <td>
                    <p class="text-center bold">活跃标签</p>
                    <p class="text-center">10,024</p>
                </td>
                <td>
                    <p class="text-center bold">活跃内容</p>
                    <p class="text-center">1,002</p>
                </td>
            </tr>
            <tr>
                <td class="no-bottom-border">
                    <p class="text-center bold">新增内容</p>
                    <p class="text-center">2,458</p>
                </td>
                <td class="no-bottom-border">
                    <p class="text-center bold">使用率</p>
                    <p class="text-center">10.02%</p>
                </td>
                <td class="no-bottom-border">
                    <p class="text-center bold">错误率</p>
                    <p class="text-center">1.002%</p>
                </td>
            </tr>
        </table>
    </div>
    <div class="col-xs-3 num-list-pie num-list-table">
        <div class="list-title list-title5">
            小说库
            <span class="i-icon-dec">?</span>
        </div>
        <p class="clearfix">
            <span class="pull-left" style="padding-top:12px;">识别率</span>
            <span class="pull-right num-weight">20.98%</span>
        </p>
        <div class="progress">
            <div class="progress-bar" role="progressbar" aria-valuemin="0" aria-valuemax="100" style="width: 20.98%;">
            </div>
        </div>
        <table>
            <tr>
                <td>
                    <p class="text-center bold">活跃产品</p>
                    <p class="text-center">1,002,458</p>
                </td>
                <td>
                    <p class="text-center bold">活跃标签</p>
                    <p class="text-center">10,024</p>
                </td>
                <td>
                    <p class="text-center bold">活跃内容</p>
                    <p class="text-center">1,002</p>
                </td>
            </tr>
            <tr>
                <td class="no-bottom-border">
                    <p class="text-center bold">新增内容</p>
                    <p class="text-center">2,458</p>
                </td>
                <td class="no-bottom-border">
                    <p class="text-center bold">使用率</p>
                    <p class="text-center">10.02%</p>
                </td>
                <td class="no-bottom-border">
                    <p class="text-center bold">错误率</p>
                    <p class="text-center">1.002%</p>
                </td>
            </tr>
        </table>
    </div>
    <div class="col-xs-3 num-list-pie num-list-table">
        <div class="list-title list-title5">
            小说库
            <span class="i-icon-dec">?</span>
        </div>
        <p class="clearfix">
            <span class="pull-left" style="padding-top:12px;">识别率</span>
            <span class="pull-right num-weight">20.98%</span>
        </p>
        <div class="progress">
            <div class="progress-bar" role="progressbar" aria-valuemin="0" aria-valuemax="100" style="width: 20.98%;">
            </div>
        </div>
        <table>
            <tr>
                <td>
                    <p class="text-center bold">活跃产品</p>
                    <p class="text-center">1,002,458</p>
                </td>
                <td>
                    <p class="text-center bold">活跃标签</p>
                    <p class="text-center">10,024</p>
                </td>
                <td>
                    <p class="text-center bold">活跃内容</p>
                    <p class="text-center">1,002</p>
                </td>
            </tr>
            <tr>
                <td class="no-bottom-border">
                    <p class="text-center bold">新增内容</p>
                    <p class="text-center">2,458</p>
                </td>
                <td class="no-bottom-border">
                    <p class="text-center bold">使用率</p>
                    <p class="text-center">10.02%</p>
                </td>
                <td class="no-bottom-border">
                    <p class="text-center bold">错误率</p>
                    <p class="text-center">1.002%</p>
                </td>
            </tr>
        </table>
    </div>
</div>
<div class="num-list row">
    <div class="col-xs-12">
        <h4 class="text-center">分词库</h4>
    </div>
    <div class="col-xs-3 num-word-base num-list-pie">
        <div class="list-title list-title9">敏感词</div>
        <span class="i-icon-add">+</span>
        <div class="clearfix">
            <div class="col-xs-6">
                <p>活跃</p>
                <p><span class="num-weight">780,000</span></p>
            </div>
            <div class="col-xs-6">
                <p>新增</p>
                <p><span class="num-weight">780,000</span></p>
            </div>
        </div>
    </div>
    <div class="col-xs-3 num-word-base num-list-pie">
        <div class="list-title list-title1">分词库</div>
        <span class="i-icon-add">+</span>
        <div class="clearfix">
            <div class="col-xs-6">
                <p>活跃</p>
                <p><span class="num-weight">780,000</span></p>
            </div>
            <div class="col-xs-6">
                <p>新增</p>
                <p><span class="num-weight">780,000</span></p>
            </div>
        </div>
    </div>
    <div class="col-xs-3 num-word-base num-list-pie">
        <div class="list-title list-title2">分类词库</div>
        <span class="i-icon-add">+</span>
        <div class="clearfix">
            <div class="col-xs-6">
                <p>活跃</p>
                <p><span class="num-weight">780,000</span></p>
            </div>
            <div class="col-xs-6">
                <p>新增</p>
                <p><span class="num-weight">780,000</span></p>
            </div>
        </div>
    </div>
    <div class="col-xs-3 num-word-base num-list-pie">
       <div class="add-button text-center" title="点击上传">
           <img src="<%=request.getContextPath()%>/pages/images/know-add-button.png">
           <p class="text-center">点击新增区块内容</p>
       </div>
    </div>
</div>
</body>
</html>

