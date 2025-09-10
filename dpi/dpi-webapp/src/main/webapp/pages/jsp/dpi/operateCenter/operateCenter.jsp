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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/knowledgeBaseMonitor.css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/pages/js/echarts.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/pages/js/bootstrap-multiselect.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/pages/jsp/dpi/operateCenter/operateCenter.js"></script>
    <title>运维中心</title>
    <style>
    .tooltip-inner{
      max-width:1000px;
      padding:3px 8px;
      color:#fff;
      text-align:left;
      background-color: #000;
      border-radius: 4px;
    
    }
    
    </style>
</head>
<body>
	<form action="" class="form-inline">
    <div class="form-group">
        <label>统计账期:</label><label id='time'></label>
    </div>
    <a href="javascript:void(0);" onclick="logInfo()" class="pull-right" style="line-height:30px;"><span class="glyphicon glyphicon-open" ></span>导出新增产品</a>
	</form>
    <div class="num-main row">
        <div class="col-xs-4 clearfix" >
            <h4 class="num-weight pull-left" id="top_audit" style="cursor: pointer"></h4>
            <div class="num-dec pull-left">
                <p class="dec-main" id="audit_rule_error">稽核错误</p>
                
                <span class="i-icon-dec"  data-toggle="tooltip" data-placement="bottom"  title="稽核错误：对知识库数据进行逻辑、完整性稽核；">?</span>
            </div>
        </div>
        <div class="col-xs-4 clearfix" style="border-left:1px solid #eee;">
            <h4 class="num-weight pull-left" id="top_ua" style="cursor: pointer"></h4>
            <div class="num-dec pull-left">
                <p class="dec-main" id="unidentified_ua">待补充UA识别</p>
                <span class="i-icon-dec"  data-toggle="tooltip" data-placement="bottom" title="待补充UA识别：提取未识别USER_AGENT关键字并去燥，根据此结果可补充UA库、UA噪音库，提高UA产品识别率">?</span>
            </div>
        </div>
        <div class="col-xs-4 clearfix" style="border-left:1px solid #eee;">
            <h4 class="num-weight pull-left" id="URLFuzzyRecognitionNum" style="cursor: pointer"></h4>
            <div class="num-dec pull-left">
                <p class="dec-main" id="URLFuzzyRecognition">URL模糊识别</p>
                <span class="i-icon-dec"  data-toggle="tooltip" data-placement="bottom" title="域名无法精确识别产品时，通过分析URL提取配置模糊规则进行产品识别">?</span>
            </div>
        </div>
    </div>
    <div class="num-list row">
        <div class="col-xs-12">
            <h4 class="text-center" id="unidentified_url">未识别域名-智能识别</h4>
        </div>
        <%-- 饼图模块 --%>
        <div class="col-xs-3 num-list-pie" style="padding-top:138px;">
            <p class="no-margin-bottom" style="font-size:20px;text-align:center;">域名数：<span class="num-weight" id="record">111,111</span></p>
            <span class="i-icon-dec"  data-toggle="tooltip" data-placement="bottom" title="域名数=未识别的总域名条数<br/>流量=未识别的流量总数">?</span>
            <!-- <p class="clearfix" >
                <span class="pull-left" style="padding-top:12px;" >占比</span>
                <span class="pull-right num-weight" id="record_proportion">80%</span>
            </p> -->
            <!-- <div class="progress">
                <div class="progress-bar" role="progressbar" aria-valuemin="0" aria-valuemax="100" style="width: 80%;" id="record_proportion_bar">
                </div>
            </div > -->
            <p class="no-margin-bottom" style="margin-top:60px;font-size:20px;text-align:center;">流量：&nbsp;&nbsp;&nbsp;<span class="num-weight" id="flow">111,111</span></p>
            <!-- <p class="clearfix">
                <span class="pull-left" style="padding-top:12px;">占比</span>
                <span class="pull-right num-weight" id="flow_proportion">30%</span>
            </p> -->
            <!-- <div class="progress">
                <div class="progress-bar" role="progressbar" aria-valuemin="0" aria-valuemax="100" style="width: 30%;" id="flow_proportion_bar">
                </div>
            </div> -->
            <%-- echarts容器-饼图 --%>
            <div class="echarts-container echarts-container-pie" id="echart1"></div>
        </div>
        <%-- 环形图模块_1 --%>
        <div class="col-xs-3 num-list-ring">
            <div class="list-title list-title1" id="title">抓取标题</div>
            <span class="i-icon-dec"  data-toggle="tooltip" data-placement="bottom" title="抓取标题条数=未识别域名通过爬虫爬取到标题的域名数抓取标题<br/>占比=抓取标题条数/总未识别域名数<br/>自动匹配产品=爬取到产品并在产品库中存在的域名数<br/>自动匹配产品占比=自动匹配产品/抓取标题条数需<br/>新增产品=爬取到标题在产品库中没有对应的产品的域名数<br/>需新增产品占比=需新增产品/抓取标题条数">?</span>
            <%-- echarts容器-环形图1 --%>
            <div class="echarts-container echarts-container-ring" id="echart2"></div>
            <p class="num-weight text-center" id="echart2_total" style="cursor: pointer"></p>
            <table>
                <tr>
                    <td><p>自动匹配产品</p><p class="num-weight" id="echart2_auto"></p></td>
                    <td><p>占比</p><p class="num-weight" id="echart2_autoPar"></p></td>
                </tr>
                <tr>
                    <td><p>需新增新品</p><p class="num-weight" id="echart2_need"></p></td>
                    <td><p>占比</p><p class="num-weight" id="echart2_needPar"></p></td>
                </tr>
            </table>
        </div>
        <%-- 环形图模块_2 --%>
        <div class="col-xs-3 num-list-ring">
            <div class="list-title list-title2" id="host">域名备案</div>
            <span class="i-icon-dec"  data-toggle="tooltip" data-placement="bottom" title="域名备案条数=未识别域名通过爬虫未爬取到标题，通过查询备案能够查询到的域名条数<br/>域名备案占比=未识别域名通过爬虫未爬取到标题，通过查询备案能够查询到的域名条数/总未识别域名数<br/>自动匹配产品=未识别域名通过爬虫未爬取到标题，通过查询备案能够查询到并匹配到产品的域名条数<br/>自动匹配产品占比=自动匹配产品/域名备案条数<br/>需新增产品=未识别域名通过爬虫未爬取到标题，通过查询备案能够查询到的产品在产品库中没有对应的产品的域名数<br/>需新增产品占比=需新增产品/域名备案条数">?</span>
            <%-- echarts容器-环形图2 --%>
            <div class="echarts-container echarts-container-ring" id="echart3"></div>
            <p class="num-weight text-center" id="echart3_total" style="cursor: pointer"></p>
            <table>
                <tr>
                    <td><p>自动匹配产品</p><p class="num-weight" id="echart3_auto"></p></td>
                    <td><p>占比</p><p class="num-weight" id="echart3_autoPar"></p></td>
                </tr>
                <tr>
                    <td><p>需新增新品</p><p class="num-weight" id="echart3_need"></p></td>
                    <td><p>占比</p><p class="num-weight" id="echart3_needPar"></p></td>
                </tr>
            </table>
        </div>
        <%-- 环形图模块_3 --%>
        <div class="col-xs-3 num-list-ring">
            <div class="list-title list-title2" id="unidentified">未自动识别域名</div>
            <span class="i-icon-dec"  data-toggle="tooltip" data-placement="bottom" title="未自动识别域名=没有自动爬取到域名对应产品信息，需要人工按排名处理">?</span>
            <%-- echarts容器-环形图1 --%>
            <div class="echarts-container echarts-container-ring" id="echart4"></div>
            <p class="num-weight text-center" id="echart4_total" style="cursor: pointer"></p>
        </div>
    </div>
    <div class="num-main row">
        <div class="col-xs-4 clearfix">
            <h4 class="num-weight pull-left" id="inNum" style="cursor: pointer"></h4>
            <div class="num-dec pull-left">
                <p class="dec-main" id="industry_label_audit">行业标签稽核</p>
                <span class="i-icon-dec"  data-toggle="tooltip" data-placement="bottom" title="行业标签稽核：根据行业解析结果，按行业稽核产品Top100，发现异常数据可直接更改产品对应标签信息">?</span>
            </div>
        </div>
        <div class="col-xs-4 clearfix" style="border-left:1px solid #eee;">
            <h4 class="num-weight pull-left" id="bottom_level" style="cursor: pointer"></h4>
            <div class="num-dec pull-left">
                <p class="dec-main" id="top_level" >一级域名识别</p>
                <span class="i-icon-dec"  data-toggle="tooltip" data-placement="bottom" title="一级域名识别：展示通过一级域名识别的产品对应的原始域名，可按优先级分析原始域名，如具有业务含义且为独立产品，需把原始域名对应产品信息维护到域名库，最终到达提高识别精度、准确率的目的。">?</span>
            </div>
        </div>
        <div class="col-xs-4 clearfix" style="border-left:1px solid #eee;">
            <h4 class="num-weight pull-left"  style="cursor: pointer" id="substanceLabelNum"></h4>
            <div class="num-dec pull-left">
                <p class="dec-main"  >内容标签稽核</p>
                <span class="i-icon-dec"  data-toggle="tooltip" data-placement="bottom" title="内容标签稽核：展示实际爬取到的内容标签系统处理后的映射结果，根据分析映射结果可细化内容标签映射表、补充内容标签体系，同时提高标签映射质量">?</span>
            </div>
        </div>
    </div>
</body>
</html>
