<%--
  Created by IntelliJ IDEA.
  User: liuyang_web
  Date: 2018/9/12
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.bonc.com.cn/common/tag/cxt" prefix="cxt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cxt" uri="http://www.bonc.com.cn/common/tag/cxt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ page import="java.util.*"%>
<%@ page import="com.bonc.system.dao.entity.SysUser"%>
<%@ page import="com.bonc.system.dao.entity.SysRoleUser"%>
<%@ page import="com.bonc.common.cst.CST"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset='utf-8'>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <cxt:commonLinks />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/bootstrap-multiselect.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/common.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/operateCenter.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/knowledgeBaseMonitor.css" />
    
    <script type="text/javascript" src="<%=request.getContextPath()%>/pages/js/bootstrap-multiselect.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/pages/jsp/dpi/knowledgeBaseMonitor.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/pages/jsp/dpi/dIdentifiedPandect/dIdentifiedPandectForm.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/pages/js/echarts4.min.js"></script>

    
    <title>知识库监控</title>
    
    <style>
		/*改变问号中文字文字提示的样式*/
	    .tooltip-inner{
	      max-width:1000px;
	      padding:3px 8px;
	      color:#fff;
	      text-align:left;
	      background-color: #000;
	      border-radius: 4px;
	    }
    </style>
    
    <script type="text/javascript">
    
		//将数字三位一个逗号划分
		function toThousands(num) {
		     var num = (num || 0).toString(); 
		     var result = "";
		     while (num.length > 3) {
		         result = "," + num.slice(-3) + result;
		         num = num.slice(0, num.length - 3);
		     }
		     if (num) {
		    	 result = num + result; 
	    	 }
		     return result;
		 }
		
		//初始化
		$(function(){
			
			//识别率：<90%   字体颜色变红色
			//使用率: <70%  字体颜色变红色
			//错误率: >10%  字体颜色变红色
			
			$('[data-toggle="tooltip"]').tooltip({html : true });//title的黑色文本框
			checkData();
		})
		
		//检查数据有效性
		function checkData(){
			//-----内容库
			//1小说
			$("#vo_G0009_activeProd").html(toThousands($("#vo_G0009_activeProd").html()));
			$("#vo_G0009_activeLabel").html(toThousands($("#vo_G0009_activeLabel").html()));
			$("#vo_G0009_activeContent").html(toThousands($("#vo_G0009_activeContent").html()));
			$("#vo_G0009_addContent").html(toThousands($("#vo_G0009_addContent").html()));	
			//2视频
			$("#vo_G0012_activeProd").html(toThousands($("#vo_G0012_activeProd").html()));
			$("#vo_G0012_activeLabel").html(toThousands($("#vo_G0012_activeLabel").html()));
			$("#vo_G0012_activeContent").html(toThousands($("#vo_G0012_activeContent").html()));
			$("#vo_G0012_addContent").html(toThousands($("#vo_G0012_addContent").html()));
			//3音乐
			$("#vo_G0004_activeProd").html(toThousands($("#vo_G0004_activeProd").html()));
			$("#vo_G0004_activeLabel").html(toThousands($("#vo_G0004_activeLabel").html()));
			$("#vo_G0004_activeContent").html(toThousands($("#vo_G0004_activeContent").html()));
			$("#vo_G0004_addContent").html(toThousands($("#vo_G0004_addContent").html()));
			//4电商商品
			$("#vo_G0014_activeProd").html(toThousands($("#vo_G0014_activeProd").html()));
			$("#vo_G0014_activeLabel").html(toThousands($("#vo_G0014_activeLabel").html()));
			$("#vo_G0014_activeContent").html(toThousands($("#vo_G0014_activeContent").html()));
			$("#vo_G0014_addContent").html(toThousands($("#vo_G0014_addContent").html()));
			
			//内容库---〉识别率 
			if($("#vo_G0009_recongnition").html()==""){
				$("#vo_G0009_recongnition").html("0.00%");
			}
			if($("#vo_G0012_recongnition").html()==""){
				$("#vo_G0012_recongnition").html("0.00%");
			}
			if($("#vo_G0004_recongnition").html()==""){
				$("#vo_G0004_recongnition").html("0.00%");
			}
			if($("#vo_G0014_recongnition").html()==""){
				$("#vo_G0014_recongnition").html("0.00%");
			}				
			
			//内容库---〉使用率和错误率
			if($("#vo_G0009_useCnt").html()==0){
				$("#vo_G0009_useCnt").html("0.00%");
			}
			if($("#vo_G0009_errorCnt").html()==""){
				$("#vo_G0009_errorCnt").html("0.00%");
			}
			if($("#vo_G0012_useCnt").html()==0){
				$("#vo_G0012_useCnt").html("0.00%");
			}
			if($("#vo_G0012_errorCnt").html()==0){
				$("#vo_G0012_errorCnt").html("0.00%");
			}			
			if($("#vo_G0004_useCnt").html()==0){
				$("#vo_G0004_useCnt").html("0.00%");
			}
			if($("#vo_G0004_errorCnt").html()==0){
				$("#vo_G0004_errorCnt").html("0.00%");
			}
			if($("#vo_G0014_useCnt").html()==0){
				$("#vo_G0014_useCnt").html("0.00%");
			}
			if($("#vo_G0014_errorCnt").html()==0){
				$("#vo_G0014_errorCnt").html("0.00%");
			}	
			
			//------规则库
			$("#activeSensitive").html(toThousands($("#activeSensitive").html()));
			$("#addSensitive").html(toThousands($("#addSensitive").html()));
			$("#activeWords").html(toThousands($("#activeWords").html()));
			$("#addWords").html(toThousands($("#addWords").html()));
			$("#activeWordsType").html(toThousands($("#activeWordsType").html()));
			$("#addWordsType").html(toThousands($("#addWordsType").html()));
			$("#stopWordLib_hy").html(toThousands($("#stopWordLib_hy").html()));
			$("#stopWordLib_xz").html(toThousands($("#stopWordLib_xz").html()));
		}
		
		
		
		//按时间查询
		function queryDate(){
// 			alert("queryDate");
			var date = $("#chooseDate").val();
			if(date!=""){
				date = date.replace("-","");
				date = date.replace("-","");
				loadEcharts(date);
			}
		}
		//清空查询条件
		function clearDate(){
// 			alert("clearDate");
			$("#chooseDate").val("");
		}
    </script>
    
</head>
<body>
	<form action="" class="form-inline">
		<c:if test="${empty vo_dip.dateId}">
			<c:set var="value1"  value="0"/>
			<c:set var="value2"  value="0"/>			
		</c:if>
		<c:if test="${!empty vo_dip.dateId}">
			<c:set var="value1"  value="${vo_dip.yyjlSblFz/vo_dip.yyjlSblNoipFm}"/>
			<c:set var="value2"  value="${vo_dip.yyllSblFz/vo_dip.yyllSblNoipFm}"/>
		</c:if>
	    <div class="form-group">
	    	<label>统计账期：${dateId_fromDataBase}</label>
<!-- 			<label>统计账期：</label> -->
<!-- 			<input id="chooseDate" type="text" class="form-control datepicker-input"  placeholder="选择日期"> -->
<!-- 			<button type="button" class="btn btn-primary" onclick="queryDate()">查询</button> -->
<!-- 			<button type="button" class="btn btn-warning" onclick="clearDate()">清空</button> -->
	    </div>
	</form>
	
	<div class="num-main row">
	    <div class="col-xs-6 clearfix">
	    	<span class="i-icon-dec" data-toggle="tooltip" data-placement="bottom" title="应用记录识别率=已识别记录数/总记录数<br>不含IP识别率=已识别记录数/（总记录数-未识别IP的记录数）<br>注：域名为IP存在几种情况，共享DNS、缓存服务器、云服务器、服务商专属服务器，这些都无法直观识别出详细产品信息">?</span>
	        <div class="pull-left echarts-div" style="text-align: center;" id="echarts_numberUnipTotal">
	            <%--环形图容器--%>
	            <div class="echarts-container echarts-pie pull-right"></div>
	        </div>
	       	<div class="num-dec pull-left" style="text-align: center;">
	            <p class="dec-main">应用记录数识别率</p>
	            <p class="dec-text">不含ip识别率<span class="num-blue" <c:if test="${value1 lt 0.90}">style="color: red;font-weight: bold;"</c:if> ><fmt:formatNumber type="percent" value="${value1}" maxFractionDigits="2"/></span></p>
	        </div>	        
	    </div>
	    <div class="col-xs-6 clearfix" style="border-left:1px solid #eee;">
	    	<span class="i-icon-dec" data-toggle="tooltip" data-placement="bottom" title="应用流量识别率=已识别总流量/总流量<br>不含IP识别率=已识别总流量/（总流量-域名为IP的流量）<br>注：域名为IP存在几种情况，共享DNS、缓存服务器、云服务器、服务商专属服务器，这些都无法直观识别出详细产品信息">?</span>
	        <div class="pull-left echarts-div" style="text-align: center;" id="echarts_flowUnipTotal">
	            <%--环形图容器--%>
	            <div class="echarts-container echarts-pie pull-right"></div>
	        </div>	       
	        <div class="num-dec pull-left" style="text-align: center;">
	            <p class="dec-main">应用流量识别率</p>
	            <p class="dec-text">不含ip识别率<span class="num-blue" <c:if test="${value2 lt 0.90}">style="color: red;font-weight: bold;"</c:if> ><fmt:formatNumber type="percent" value="${value2}"  maxFractionDigits="2"/></span></p>
	        </div>       
	    </div>
	</div>
	
	
	<div class="num-list row">
	    <div class="col-xs-12">
	        <h4 class="text-center">规则库</h4>
	    </div>
	    <%-- 柱图模块1 --%>
	    <div class="col-xs-6 num-list-bar">
	    	<span class="i-icon-dec" data-toggle="tooltip" data-placement="bottom" title="域名：截取url中host，用来识别上网产品信息<br>活跃：截止当前有使用行为的域名数量<br>新增：当月新增的域名数量">?</span>
	        <div class="list-title list-title1">域名</div>
	        <div class="pull-left bar-list-num">
	            <p>活跃</p><p><span class="num-weight">${ym_hy}</span></p>
	            <br>
	            <p>新增</p><p><span class="num-weight num-weight-small">${ym_xz}</span></p>
	        </div>
	        <div class="echarts-div" >
	            <%--柱形图容器--%>
	            <div class="echarts-container echarts-bar" id="echarts_domain"></div>
	            <p class="text-center">标签域名分布</p>
	        </div>
	    </div>
	    <%-- 柱图模块2 --%>
	    <div class="col-xs-6 num-list-bar">
	    	<span class="i-icon-dec" data-toggle="tooltip" data-placement="bottom" title="产品：可独立安装访问的互联网产品，包括APP和网页，并对产品进行分类打标签<br>活跃：截止当前有使用行为的产品数量<br>新增：当月新增的产品数量">?</span>
	        <div class="list-title list-title3">产品</div>
	        <div class="pull-left bar-list-num">
	            <p>活跃</p><p><span class="num-weight">${cp_hy}</span></p>
	            <br>
	            <p>新增</p><p><span class="num-weight num-weight-small">${cp_xz}</span></p>
	        </div>
	        <div class="echarts-div" >
	            <%--柱形图容器--%>
	            <div class="echarts-container echarts-bar" id="echarts_product"></div>
	            <p class="text-center">标签产品分布</p>
	        </div>
	    </div>
	    <%-- 柱图模块3 --%>
	    <div class="col-xs-6 num-list-bar no-bottom-border">
	    	<span class="i-icon-dec" data-toggle="tooltip" data-placement="bottom" title="标签：按行业建设标签体系，对互联网产品进行分类<br>活跃：截止当前有使用行为的标签数量<br>新增：当月新增的标签数量">?</span>
	        <div class="list-title list-title10">行业标签</div>
	        <div class="pull-left bar-list-num">
	            <p>活跃</p><p><span class="num-weight">${hybq_hy}</span></p>
	            <br>
	            <p>新增</p><p><span class="num-weight num-weight-small">${hybq_xz}</span></p>
	        </div>
	        <div class="echarts-div">
	            <%--柱形图容器--%>
	            <div class="echarts-container echarts-bar" id="echarts_label"></div>
	            <p class="text-center">标签层级分布</p>
	        </div>
	    </div>
	    <%-- 柱图模块4 --%>
	    <div class="col-xs-6 num-list-bar no-bottom-border">
	<!--         <span class="i-icon-add">+</span> -->
			<span class="i-icon-dec" data-toggle="tooltip" data-placement="bottom" title="内容规则：通过分析url，配置对应规则，深度挖掘url业务含义，如目录、动作、内容<br>活跃：截止当前各应用有效的规则总条数<br>新增：当月新增的规则条数">?</span>
	        <div class="list-title list-title4">内容规则</div>
	        <div class="pull-left bar-list-num">
	            <p>活跃</p><p><span class="num-weight">${nr_hy}</span></p>
	            <br>
	            <p>新增</p><p><span class="num-weight num-weight-small">${nr_xz}</span></p>
	        </div>
	        <div class="echarts-div">
	            <%--柱形图容器--%>
	            <div class="echarts-container echarts-bar" id="echarts_rule"></div>
	            <p class="text-center">内容规则分布</p>
	        </div>
	    </div>
	</div>
	
	
	
	<div class="num-list row">
	    <div class="col-xs-12">
	        <h4 class="text-center">内容库</h4>
	    </div>
	    <div class="col-xs-3 num-list-pie num-list-table">
	        <div class="list-title list-title5">小说库
	        	<span class="i-icon-dec" data-toggle="tooltip" data-placement="bottom" title="小说库：识别率=当日已爬取小说数/当日总提取小说数<br>活跃产品=已拨测产品并配置内容提取规则的产品数<br>活跃标签=当日识别小说特有标签数量<br>活跃内容=当日识别小说总数<br>新增内容=当日识别新增小说数量<br>使用率=活跃内容/小说总数<br>错误率=当日爬取小说错误数/当日总提取小说数">?</span>
	        </div>
	        <p class="clearfix"><span class="pull-left" style="padding-top:12px;">识别率</span><span id="vo_G0009_recongnition"  <c:if test="${vo_G0009.recongnition lt 0.90 || empty vo_G0009.recongnition}">style="color: red"</c:if>  class="pull-right num-weight"><fmt:formatNumber type="percent" value="${vo_G0009.recongnition}" maxFractionDigits="2"/></span></p>
	        <div class="progress"><div class="progress-bar" role="progressbar" aria-valuemin="0" aria-valuemax="100" style="width: <fmt:formatNumber type="percent" value="${vo_G0009.recongnition}" maxFractionDigits="2"/>;"></div></div>
	        <table>
	            <tr>
	                <td><p class="text-center bold">活跃产品</p><p id="vo_G0009_activeProd" class="text-center">${vo_G0009.activeProd }</p></td>
	                <td><p class="text-center bold">活跃标签</p><p id="vo_G0009_activeLabel" class="text-center">${vo_G0009.activeLabel }</p></td>
	                <td><p class="text-center bold">活跃内容</p><p id="vo_G0009_activeContent" class="text-center">${vo_G0009.activeContent }</p></td>
	            </tr>
	            <tr>
	                <td class="no-bottom-border"><p class="text-center bold">新增内容</p><p id="vo_G0009_addContent" class="text-center">${vo_G0009.addContent }</p></td>
	                <td class="no-bottom-border"><p class="text-center bold">使用率</p><p id="vo_G0009_useCnt" <c:if test="${vo_G0009.useCnt lt 0.70 || empty vo_G0009.useCnt}">style="color: red;font-weight: bold;"</c:if> class="text-center"><fmt:formatNumber type="percent" value="${vo_G0009.useCnt}" maxFractionDigits="2"/></p></td>
	                <td class="no-bottom-border"><p class="text-center bold">错误率</p><p id="vo_G0009_errorCnt" <c:if test="${vo_G0009.errorCnt gt 0.10}">style="color: red;font-weight: bold;"</c:if> class="text-center"><fmt:formatNumber type="percent" value="${vo_G0009.errorCnt}" maxFractionDigits="2"/></p></td>
	            </tr>
	        </table>
	    </div>
	    
	    <div class="col-xs-3 num-list-pie num-list-table">
	    		<div class="list-title list-title7">音乐库
	        <span class="i-icon-dec" data-toggle="tooltip" data-placement="bottom" title="音乐库：识别率=当日已爬取音乐数/当日总提取音乐数<br>活跃产品=已拨测产品并配置内容提取规则的产品数<br>活跃标签=当日识别音乐特有标签数量<br>活跃内容=当日识别音乐总数<br>新增内容=当日识别新增音乐数量<br>使用率=活跃内容/活跃内容<br>错误率=当日爬取音乐错误数/当日总提取音乐数">?</span>
	        </div>
	        <p class="clearfix"><span class="pull-left" style="padding-top:12px;">识别率</span><span id="vo_G0012_recongnition"  <c:if test="${vo_G0012.recongnition lt 0.90  || empty vo_G0012.recongnition}">style="color: red"</c:if> class="pull-right num-weight"><fmt:formatNumber type="percent" value="${vo_G0012.recongnition}" maxFractionDigits="2"/></span></p>
	        <div class="progress"><div class="progress-bar" role="progressbar" aria-valuemin="0" aria-valuemax="100" style="width: <fmt:formatNumber type="percent" value="${vo_G0012.recongnition}" maxFractionDigits="2"/>;"></div></div>
	        <table>
	            <tr>
	                <td><p class="text-center bold">活跃产品</p><p id="vo_G0012_activeProd" class="text-center">${vo_G0012.activeProd }</p></td>
	                <td><p class="text-center bold">活跃标签</p><p id="vo_G0012_activeLabel" class="text-center">${vo_G0012.activeLabel }</p></td>
	                <td><p class="text-center bold">活跃内容</p><p id="vo_G0012_activeContent" class="text-center">${vo_G0012.activeContent }</p></td>
	            </tr>
	            <tr>
	                <td class="no-bottom-border"><p class="text-center bold">新增内容</p><p id="vo_G0012_addContent" class="text-center">${vo_G0012.addContent }</p></td>
	                <td class="no-bottom-border"><p class="text-center bold">使用率</p><p id="vo_G0012_useCnt" <c:if test="${vo_G0012.useCnt lt 0.70 || empty vo_G0012.useCnt}">style="color: red;font-weight: bold;"</c:if> class="text-center"><fmt:formatNumber type="percent" value="${vo_G0012.useCnt}" maxFractionDigits="2"/></p></td>
	                <td class="no-bottom-border"><p class="text-center bold">错误率</p><p id="vo_G0012_errorCnt" <c:if test="${vo_G0012.errorCnt gt 0.10}">style="color: red;font-weight: bold;"</c:if> class="text-center"><fmt:formatNumber type="percent" value="${vo_G0012.errorCnt}" maxFractionDigits="2"/></p></td>
	            </tr>
	        </table>
		</div>
		
	    <div class="col-xs-3 num-list-pie num-list-table">
	        	<div class="list-title list-title6">视频库
	        	<span class="i-icon-dec" data-toggle="tooltip" data-placement="bottom" title="视频库：识别率=当日已爬取视频数/当日总提取视频数<br>活跃产品=已拨测产品并配置内容提取规则的产品数<br>活跃标签=当日识别视频特有标签数量<br>活跃内容=当日识别视频总数<br>新增内容=当日识别新增视频数量<br>使用率=活跃内容/视频总数<br>错误率=当日爬取视频错误数/当日总提取视频数">?</span>
	        </div>
	        <p class="clearfix"><span class="pull-left" style="padding-top:12px;">识别率</span><span id="vo_G0004_recongnition"  <c:if test="${vo_G0004.recongnition lt 0.90 || empty vo_G0004.recongnition}">style="color: red"</c:if> class="pull-right num-weight"><fmt:formatNumber type="percent" value="${vo_G0004.recongnition}" maxFractionDigits="2"/></span></p>
	        <div class="progress"><div class="progress-bar" role="progressbar" aria-valuemin="0" aria-valuemax="100" style="width: <fmt:formatNumber type="percent" value="${vo_G0004.recongnition}" maxFractionDigits="2"/>;"></div></div>
	        <table>
	            <tr>
	                <td><p class="text-center bold">活跃产品</p><p id="vo_G0004_activeProd" class="text-center">${vo_G0004.activeProd }</p></td>
	                <td><p class="text-center bold">活跃标签</p><p id="vo_G0004_activeLabel" class="text-center">${vo_G0004.activeLabel }</p></td>
	                <td><p class="text-center bold">活跃内容</p><p id="vo_G0004_activeContent" class="text-center">${vo_G0004.activeContent }</p></td>
	            </tr>
	            <tr>
	                <td class="no-bottom-border"><p class="text-center bold">新增内容</p><p id="vo_G0004_addContent" class="text-center">${vo_G0004.addContent }</p></td>
	                <td class="no-bottom-border"><p class="text-center bold">使用率</p><p id="vo_G0004_useCnt" <c:if test="${vo_G0004.useCnt lt 0.70 || empty vo_G0004.useCnt}">style="color: red;font-weight: bold;"</c:if> class="text-center"><fmt:formatNumber type="percent" value="${vo_G0004.useCnt}" maxFractionDigits="2"/></p></td>
	                <td class="no-bottom-border"><p class="text-center bold">错误率</p><p id="vo_G0004_errorCnt" <c:if test="${vo_G0004.errorCnt gt 0.10}">style="color: red;font-weight: bold;"</c:if> class="text-center"><fmt:formatNumber type="percent" value="${vo_G0004.errorCnt}" maxFractionDigits="2"/></p></td>
	            </tr>
	        </table>
	    </div>
	    
	    <div class="col-xs-3 num-list-pie num-list-table">
	        <div class="list-title list-title11">电商商品库
	        	<span class="i-icon-dec" data-toggle="tooltip" data-placement="bottom" title="电商库：识别率=当日已爬取电商商品数/当日总提取电商商品数<br>活跃产品=已拨测产品并配置内容提取规则的产品数<br>活跃标签=当日识别电商商品特有标签数量<br>活跃内容=当日识别电商商品总数<br>新增内容=当日识别新增电商商品数量<br>使用率=活跃内容/电商商品总数<br>错误率=当日爬取电商商品错误数/当日总提取电商商品数">?</span>
	        </div>
	        <p class="clearfix"><span class="pull-left" style="padding-top:12px;">识别率</span><span id="vo_G0014_recongnition"  <c:if test="${vo_G0014.recongnition lt 0.90 || empty vo_G0014.recongnition}">style="color: red"</c:if> class="pull-right num-weight"><fmt:formatNumber type="percent" value="${vo_G0014.recongnition}" maxFractionDigits="2"/></span></p>
	        <div class="progress"><div class="progress-bar" role="progressbar" aria-valuemin="0" aria-valuemax="100" style="width: <fmt:formatNumber type="percent" value="${vo_G0014.recongnition}" maxFractionDigits="2"/>;"></div></div>
	        <table>
	            <tr>
	                <td><p class="text-center bold">活跃产品</p><p id="vo_G0014_activeProd" class="text-center">${vo_G0014.activeProd }</p></td>
	                <td><p class="text-center bold">活跃标签</p><p id="vo_G0014_activeLabel" class="text-center">${vo_G0014.activeLabel }</p></td>
	                <td><p class="text-center bold">活跃内容</p><p id="vo_G0014_activeContent" class="text-center">${vo_G0014.activeContent }</p></td>
	            </tr>
	            <tr>
	                <td class="no-bottom-border"><p class="text-center bold">新增内容</p><p id="vo_G0014_addContent" class="text-center">${vo_G0014.addContent }</p></td>
	                <td class="no-bottom-border"><p class="text-center bold">使用率</p><p id="vo_G0014_useCnt" <c:if test="${vo_G0014.useCnt lt 0.70 || empty vo_G0014.useCnt}">style="color: red;font-weight: bold;"</c:if> class="text-center"><fmt:formatNumber type="percent" value="${vo_G0014.useCnt}" maxFractionDigits="2"/></p></td>
	                <td class="no-bottom-border"><p class="text-center bold">错误率</p><p id="vo_G0014_errorCnt" <c:if test="${vo_G0014.errorCnt gt 0.10}">style="color: red;font-weight: bold;"</c:if>  class="text-center"><fmt:formatNumber type="percent" value="${vo_G0014.errorCnt}" maxFractionDigits="2"/></p></td>
	            </tr>
	        </table>
	    </div>
	</div>
	
	
	
	<div class="num-list row">
	    <div class="col-xs-12"><h4 class="text-center">分词库</h4></div>
	    <div class="col-xs-3 num-word-base num-list-pie">
	        <div class="list-title list-title9">敏感词</div>
	<!--         <span class="i-icon-add">+</span> -->
			<span class="i-icon-dec" data-toggle="tooltip" data-placement="bottom" title="黄赌相关敏感词">?</span>
	        <div class="clearfix">
	            <div class="col-xs-6">
	                <p>活跃</p><p><span id="activeSensitive" class="num-weight-fck">${vo_dli.activeSensitive }</span></p>
	            </div>
	            <div class="col-xs-6">
	                <p>新增</p><p><span id="addSensitive" class="num-weight-fck">${vo_dli.addSensitive }</span></p>
	            </div>
	        </div>
	    </div>
	    <div class="col-xs-3 num-word-base num-list-pie">
	        <div class="list-title list-title1">分词库</div>
	        <span class="i-icon-dec" data-toggle="tooltip" data-placement="bottom" title="文本分析使用，常用分词记录">?</span>
	        <div class="clearfix">
	            <div class="col-xs-6">
	                <p>活跃</p><p><span id="activeWords" class="num-weight-fck">${vo_dli.activeWords }</span></p>
	            </div>
	            <div class="col-xs-6">
	                <p>新增</p><p><span id="addWords" class="num-weight-fck">${vo_dli.addWords }</span></p>
	            </div>
	        </div>
	    </div>
	    <div class="col-xs-3 num-word-base num-list-pie">
	        <div class="list-title list-title2">分类词库</div>
	        <span class="i-icon-dec" data-toggle="tooltip" data-placement="bottom" title="分词与标签体系映射关系">?</span>
	        <div class="clearfix">
	            <div class="col-xs-6">
	                <p>活跃</p><p><span id="activeWordsType" class="num-weight-fck">${vo_dli.activeWordsType}</span></p>
	            </div>
	            <div class="col-xs-6">
	                <p>新增</p><p><span id="addWordsType" class="num-weight-fck">${vo_dli.addWordsType}</span></p>
	            </div>
	        </div>
	    </div>
	    
	    <div class="col-xs-3 num-word-base num-list-pie">
	        <div class="list-title list-title12">停用词</div>
	        <span class="i-icon-dec" data-toggle="tooltip" data-placement="bottom" title="文本分析使用">?</span>
	        <div class="clearfix">
	            <div class="col-xs-6">
	                <p>活跃</p><p><span id="stopWordLib_hy" class="num-weight-fck">${vo_dli.activeStop}</span></p>
	            </div>
	            <div class="col-xs-6">
	                <p>新增</p><p><span id="stopWordLib_xz" class="num-weight-fck">${vo_dli.addStop}</span></p>
	            </div>
	        </div>
	    </div>	    
	    
<!-- 		<div class="col-xs-3 num-word-base num-list-pie"> -->
<!-- 			<div class="add-button text-center" title="点击上传"> -->
<%-- 			    <img src="<%=request.getContextPath()%>/pages/images/know-add-button.png"> --%>
<!-- 			    <p class="text-center">点击新增区块内容</p> -->
<!-- 			</div> -->
<!-- 		</div> -->
	</div>
</body>
</html>

