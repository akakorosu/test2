<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String contextPath = request.getContextPath();
%>
<html>
<body>
    <div id="all_content">
        <div class="content hotart_detail_content">
            <div class="artical_detail">
                <div class="artical_detail_title">
                    文章详情<div style="float: right"></div>
                </div>
                <button class="btn btn-info" id="closedetail" style="position: static;">关闭</button>
                <div style="text-align: center;font-size: 18px">${article.title }</div>
                <div class="source_time">
                    来源：<span class='source' style="margin-right:30px;">${article.dataSource }</span>
                    发布时间：<span class="time">${article.publishTime }</span>
                </div>
                <div class="artical_detail_content">
                    ${article.articleContent }
                </div>
            </div>
        </div>
    </div>
</body>
<script type="application/javascript">
    $(function () {
        $("#closedetail").on("click",function () {
            $("#detailModal").fadeOut(300);
        });
    });
</script>
</html>
