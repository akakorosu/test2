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
    <script type="text/javascript" src="<%=request.getContextPath()%>/pages/jsp/dpi/applicationRate/applicationRate.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/pages/js/echarts4.min.js"></script>

    <title>应用识别率</title>
</head>
<body>
<%--<form action="" class="form-inline">--%>
    <div class="form-inline">
        <label>统计账期：</label>
        <input type="text" class="form-control datepicker-input" placeholder="不限" id="dateId" style="width:150px;">
        <a href="javascript:void(0);"  id="exportData" class="pull-right" style="line-height:30px;"><span class="glyphicon glyphicon-open" ></span>导出应用识别率</a>
    </div>
<%--</form>--%>

<div class="num-main row">
	    <div class="col-xs-6 clearfix">

	    	<%--<span class="i-icon-dec" data-toggle="tooltip" data-placement="bottom" title="应用记录识别率=已识别记录数/总记录数<br>不含IP识别率=已识别记录数/（总记录数-未识别IP的记录数）<br>注：域名为IP存在几种情况，共享DNS、缓存服务器、云服务器、服务商专属服务器，这些都无法直观识别出详细产品信息">?</span>--%>
            <%--<div class="pull-left echarts-div" style="text-align: center;" id="ll_huan">--%>
                <%--&lt;%&ndash;环形图容器&ndash;%&gt;--%>
                <%--<div class="echarts-container echarts-container-pie" id="id_jl" ></div>--%>
            <%--</div>--%>
            <div class="num-dec pull-left" style="text-align: center;">
	            <p class="dec-main">应用记录数识别率：<span id="jl_val"></span></p>
	            <p class="dec-text">（不含ip识别率）</p>
	        </div>
	    </div>
	    <div class="col-xs-6 clearfix" style="border-left:1px solid #eee;">
	    	<%--<span class="i-icon-dec" data-toggle="tooltip" data-placement="bottom" title="应用流量识别率=已识别总流量/总流量<br>不含IP识别率=已识别总流量/（总流量-域名为IP的流量）<br>注：域名为IP存在几种情况，共享DNS、缓存服务器、云服务器、服务商专属服务器，这些都无法直观识别出详细产品信息">?</span>--%>
	        <%--<div class="pull-left echarts-div" style="text-align: center;" id="ll_huan">--%>
	            <%--&lt;%&ndash;环形图容器&ndash;%&gt;--%>
	            <%--<div class="echarts-container echarts-container-pie" id="id_ll" ></div>--%>
	        <%--</div>--%>
	        <div class="num-dec pull-left" style="text-align: center;">
	            <p class="dec-main">应用流量识别率：<span id="ll_val"></span></p>
                <p class="dec-text">（不含ip识别率）</p>
	        </div>
	    </div>
	</div>
</div>
<div class="row num-main">
    <div class="col-xs-12" style='font-weight:700;font-size:14px;margin-left:10px;'>应用记录数识别率</div>
    <div class="col-xs-12">
        <%-- 折线图容器 --%>
        <div class="echarts-container-line" style="height:300px;" id='jl_zhe'>折线图</div>
    </div>
    <div class="col-xs-12">&nbsp;</div>
    <div class="col-xs-12 text-center"><a href="javascript:void(0);" id="details_1">详&nbsp;&nbsp;情</a></div>
</div>
<div class="row num-main">
    <div class="col-xs-12" style='font-weight:700;font-size:14px;margin-left:10px;'> 应用流量识别率</div>
    <div class="col-xs-12">
        <%-- 折线图容器 --%>
        <div class="echarts-container-line" style="height:300px;" id='ll_zhe'>折线图</div>
    </div>
    <div class="col-xs-12">&nbsp;</div>
     <div class="col-xs-12 text-center"><a href="javascript:void(0);" id="details_2">详&nbsp;&nbsp;情</a></div>
</div>
</body>
</html>

