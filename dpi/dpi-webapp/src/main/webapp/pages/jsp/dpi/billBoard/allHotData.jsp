<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.bonc.com.cn/common/tag/cxt" prefix="cxt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <cxt:commonLinks/>
    <title>全部榜单</title>
    <script type="text/javascript">
        var hotData = '<%=request.getAttribute("hotData")%>';
        hotData = JSON.parse(hotData);
        var mapJson = '<%=request.getAttribute("mapJson")%>';
        // cityMap = JSON.parse(cityMap);
    </script>
    <script src="<%=request.getContextPath()%>/pages/js/jquery-3.1.1.min.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/bootstrap-table.min.css"/>
    <script src="<%=request.getContextPath()%>/pages/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/pages/js/bootstrap-table.min.js"></script>
    <script src="<%=request.getContextPath()%>/pages/js/bootstrap-table-zh-CN.min.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/pages/jsp/dpi/billBoard/allHotData.css"/>
    <style>
        .today{
            position:relative;
        }
        .back{
            position:absolute;
            right:50px;
            bottom:0;
        }
    </style>
</head>
<body>
    <div class="tab-pane fade in active" id="tab-film-1">
        <div class="today">
            今日热点排行榜
        </div>
        <div class="today-list">
            <span class="todayList-1">排名</span>
            <span class="todayList-2">关键词</span>
            <span class="todayList-3">相关链接</span>
            <span class="todayList-4">搜索指数</span>
        </div>
        <div class="today-list">
            <span class="todayList-1"><label>1</label></span>
            <span class="todayList-2">复仇者联盟4</span>
            <span class="todayList-3"><a href="#">新闻</a><a href="#">简介</a><a href="#">啥玩意</a></span>
            <span class="todayList-4">66666<img src="<%=request.getContextPath()%>/pages/images/new/u3581.png"/></span>
        </div>
        <div class="today-list">
            <span class="todayList-1"><label>2</label></span>
            <span class="todayList-2">复仇者联盟4</span>
            <span class="todayList-3"><a href="#">新闻</a><a href="#">简介</a><a href="#">啥玩意</a></span>
            <span class="todayList-4">66666<img src="<%=request.getContextPath()%>/pages/images/new/u3581.png"/></span>
        </div>
        <div class="today-list">
            <span class="todayList-1"><label>3</label></span>
            <span class="todayList-2">复仇者联盟4</span>
            <span class="todayList-3"><a href="#">新闻</a><a href="#">简介</a><a href="#">啥玩意</a></span>
            <span class="todayList-4">66666<img src="<%=request.getContextPath()%>/pages/images/new/u3581.png"/></span>
        </div>
        <div class="today-list">
            <span class="todayList-1"><label>4</label></span>
            <span class="todayList-2">复仇者联盟4</span>
            <span class="todayList-3"><a href="#">新闻</a><a href="#">简介</a><a href="#">啥玩意</a></span>
            <span class="todayList-4">66666<img src="<%=request.getContextPath()%>/pages/images/new/u3581.png"/></span>
        </div>
    </div>
</body>

<script src="<%=request.getContextPath()%>/pages/jsp/dpi/billBoard/allHotData.js"></script>
</html>