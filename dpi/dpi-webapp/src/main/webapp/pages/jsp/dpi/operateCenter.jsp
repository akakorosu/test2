<%--
  Created by IntelliJ IDEA.
  User: liuyang_web
  Date: 2018/9/11
  Time: 14:23
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
    <script type="text/javascript" src="<%=request.getContextPath()%>/pages/js/bootstrap-multiselect.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/pages/jsp/dpi/operateCenter.js"></script>
    <title>运维中心</title>
</head>
<body>
    <form action="" class="form-inline">
        <div class="form-group">
            <label for="">时间选择：</label>
            <input type="text" class="form-control datepicker-input" placeholder="不限">
        </div>
        <div class="form-group">
            <label for="">地域：</label>
            <select name="" id="" multiple class="chosen-multiple" data-placeholder="不限">
                <option value="1">北京</option>
                <option value="2">上海</option>
                <option value="3">广州</option>
                <option value="4">深圳</option>
                <option value="5">南京</option>
            </select>
        </div>
        <div class="form-group">
            <label for="">性别：</label>
            <select name="" id="" class="chosen-select" data-placeholder="不限">
                <option value="0"></option>
                <option value="1">男</option>
                <option value="2">女</option>
            </select>
        </div>
    </form>
    <div class="num-main row">
        <div class="col-xs-6 clearfix">
            <h4 class="num-weight pull-left">200</h4>
            <div class="num-dec pull-left">
                <p class="dec-main">稽核错误</p>
                <p class="dec-text">解释说明解释说明解释说明</p>
            </div>
        </div>
        <div class="col-xs-6 clearfix" style="border-left:1px solid #eee;">
            <h4 class="num-weight pull-left">325</h4>
            <div class="num-dec pull-left">
                <p class="dec-main">待补充UA识别</p>
                <p class="dec-text">解释说明解释说明解释说明</p>
            </div>
        </div>
    </div>
    <div class="num-list row">
        <div class="col-xs-12">
            <h4 class="text-center">未识别域名-智能识别</h4>
        </div>
        <%-- 饼图模块 --%>
        <div class="col-xs-3 num-list-pie">
            <p class="no-margin-bottom">记录数：<span class="num-weight">111,111</span></p>
            <p class="clearfix">
                <span class="pull-left" style="padding-top:12px;">占比</span>
                <span class="pull-right num-weight">20%</span>
            </p>
            <div class="progress">
                <div class="progress-bar" role="progressbar" aria-valuemin="0" aria-valuemax="100" style="width: 20%;">
                </div>
            </div>
            <p class="no-margin-bottom">流量：&nbsp;&nbsp;&nbsp;<span class="num-weight">111,111</span></p>
            <p class="clearfix">
                <span class="pull-left" style="padding-top:12px;">占比</span>
                <span class="pull-right num-weight">30%</span>
            </p>
            <div class="progress">
                <div class="progress-bar" role="progressbar" aria-valuemin="0" aria-valuemax="100" style="width: 30%;">
                </div>
            </div>
            <%-- echarts容器-饼图 --%>
            <div class="echarts-container echarts-container-pie"></div>
        </div>
        <%-- 环形图模块_1 --%>
        <div class="col-xs-3 num-list-ring">
            <div class="list-title list-title1">抓取标题</div>
            <%-- echarts容器-环形图1 --%>
            <div class="echarts-container echarts-container-ring"></div>
            <p class="num-weight text-center">2,000,000</p>
            <table>
                <tr>
                    <td><p>自动匹配产品</p><p class="num-weight">200,000</p></td>
                    <td><p>占比</p><p class="num-weight">50%</p></td>
                </tr>
                <tr>
                    <td><p>需新增新品</p><p class="num-weight">200,000</p></td>
                    <td><p>占比</p><p class="num-weight">50%</p></td>
                </tr>
            </table>
        </div>
        <%-- 环形图模块_2 --%>
        <div class="col-xs-3 num-list-ring">
            <div class="list-title list-title2">域名备案</div>
            <%-- echarts容器-环形图2 --%>
            <div class="echarts-container echarts-container-ring"></div>
            <p class="num-weight text-center">2,222,000</p>
            <table>
                <tr>
                    <td><p>自动匹配产品</p><p class="num-weight">200,000</p></td>
                    <td><p>占比</p><p class="num-weight">50%</p></td>
                </tr>
                <tr>
                    <td><p>需新增新品</p><p class="num-weight">200,000</p></td>
                    <td><p>占比</p><p class="num-weight">50%</p></td>
                </tr>
            </table>
        </div>
        <%-- 环形图模块_3 --%>
        <div class="col-xs-3 num-list-ring">
            <div class="list-title list-title2">未自动识别域名</div>
            <%-- echarts容器-环形图1 --%>
            <div class="echarts-container echarts-container-ring"></div>
            <p class="num-weight text-center">2,000,000</p>
        </div>
    </div>
    <div class="num-main row">
        <div class="col-xs-6 clearfix">
            <h4 class="num-weight pull-left">200</h4>
            <div class="num-dec pull-left">
                <p class="dec-main">稽核错误</p>
                <p class="dec-text">解释说明解释说明解释说明</p>
            </div>
        </div>
        <div class="col-xs-6 clearfix" style="border-left:1px solid #eee;">
            <h4 class="num-weight pull-left">325</h4>
            <div class="num-dec pull-left">
                <p class="dec-main">待补充UA识别</p>
                <p class="dec-text">解释说明解释说明解释说明</p>
            </div>
        </div>
    </div>
</body>
</html>
