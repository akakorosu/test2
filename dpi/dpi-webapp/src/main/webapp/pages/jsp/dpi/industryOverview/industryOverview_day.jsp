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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/operateCenter.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/industryOverview.css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/pages/js/echarts.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/pages/js/bootstrap-multiselect.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/pages/jsp/dpi/industryOverview/industryOverview.js"></script>
    <title>行业总览</title>
 
</head>
<body>
<div class="page-content clearfix"> 
<input type="hidden" id="labelCodeHidden" class="formField" name="id" >
<form action="" class="form-inline">
    <div class="form-group">
        <label>账期：</label>
        <input type="text" class="form-control datepicker-input" placeholder="不限" id="time1" style="width:150px;">
    </div>
    <div class="form-group">
        <label>地域：</label>
        <select name=""  class="chosen-select"  id="city" style="width: 150px;">
            <option value="1">全部</option>
            <!-- <option value="1">哈尔滨</option>
            <option value="2">齐齐哈尔</option>
            <option value="3">牡丹江</option>
            <option value="4">佳木斯</option>
            <option value="5">双鸭山</option>
            <option value="6">七台河</option>
            <option value="7">鸡西</option>
            <option value="8">鹤岗</option>
            <option value="9">伊春</option>
            <option value="11">黑河</option>
            <option value="12">绥化</option>
            <option value="13">大兴安岭</option>
            <option value="14">大庆</option> -->
        </select>
    </div>
    <div class="form-group">
        <label>标签：</label>
        <!-- <select name=""  class="chosen-select"  id="categoricate">
            <option value="0" selected>全部</option>
            <option value="1">生活</option>
            <option value="2">行业</option>
            <option value="3">工具</option>
            <option value="4">娱乐</option>
            <option value="4">个性偏好</option>
        </select> -->
        <input type="text" placeholder="全部" class="searchField input-md form-control" readonly id="labelNameOverView" name="labelName" style="width:150px;cursor: pointer !important;" unselectable="on" onclick="sntest2()">
    </div>
</form>
<div class="row num-main">
    <div class="col-xs-6">
        <%-- 饼图容器 --%>
        <div class="echarts-container echarts-container-pie" style="height:420px;" id='user_echarts1'>饼图</div>
    </div>
    <div class="col-xs-6">
        <%-- 柱图容器 --%>
        <div class="echarts-container echarts-container-bar" style="height:420px;" id='user_echarts2'>柱图</div>
    </div>
</div>
<div class="row num-main">
    <div class="col-xs-12" id="top_name">行业APP TOP10</div>
    <%-- 增加间距用 --%>
    <div class="col-xs-12">&nbsp;</div>
    <%-- 每一行前空1，后空1，共12 --%>
    <div class="col-xs-offset-1 col-xs-2" id="top1"></div>
    <div class="col-xs-2"  id="top2"></div>
    <div class="col-xs-2"  id="top3"></div>
    <div class="col-xs-2"  id="top4"></div>
    <div class="col-xs-2"  id="top5"></div>
    <%-- 每一行前空1，后空1，共12 --%>
    <div class="col-xs-offset-1 col-xs-2"  id="top6"></div>
    <div class="col-xs-2"  id="top7"></div>
    <div class="col-xs-2"  id="top8"></div>
    <div class="col-xs-2"  id="top9"></div>
    <div class="col-xs-2"  id="top10"></div>
    <%-- 增加间距用 --%>
    <div class="col-xs-12">&nbsp;</div>
    <div class="col-xs-12 text-center"><a href="javascript:void(0);" id="moreApps">更多APP排名</a></div>
</div>
<div class="row num-main">
    <div class="col-xs-12" id="map_name">休闲娱乐类地域分析</div>
    <%-- 增加间距用 --%>
    <div class="col-xs-12">&nbsp;</div>
    
    <div class="col-xs-6">
        <%-- 地图容器 --%>
        <div class="echarts-container echarts-container-pie" style="height:420px;" id='map_echarts'>地图</div>
    </div>
    <div class="col-xs-6">
        <table class="table">
            <thead>
                <tr>
                    <th>区域</th>
                    <th>用户数(户)</th>
                    <th>用户占比</th>
                    <th>流量(G)</th>
                    <th>流量占比</th>
                    <th>人均流量(M)</th>
                </tr>
            </thead>
            <tbody id="table">
            </tbody>
        </table>
    </div>
</div>
<div class="row num-main">
    <div class="col-xs-12" id="zhe_name">休闲娱乐类时段分布</div>
    <%-- 增加间距用 --%>
    <div class="col-xs-12">&nbsp;</div>
    <div class="col-xs-12 clearfix">
        <div class="form-inline" style="border:0;padding:0;">
            <div class="pull-left form-group">
                <label style="margin-left:0;">账期：</label>
                <input type="text" class="form-control datepicker-input" placeholder="不限" id="time2">
            </div>
            <div class="pull-right btn-group">
                <button class="btn btn-primary active" >次数</button>
                <button class="btn btn-primary">流量数</button>
            </div>
        </div>
    </div>
    <div class="col-xs-12">
        <%-- 折线图容器 --%>
        <div class="echarts-container-line" style="height:300px;" id='zhe_echarts'>折线图</div>
    </div>
</div>
</div>
</body>
</html>

