<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
    String contextPath = request.getContextPath();
%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../yuqing-static/css/bootstrap-datetimepicker.css"/>
    <script type="text/javascript" src="../yuqing-static/js/bootstrap-datetimepicker.js"></script>
    <script type="text/javascript" src="../yuqing-static/js/bootstrap-datetimepicker.zh-CN.js"></script>
</head>
<style>
    html, body {
        height: 100%;
    }

    html, body, #downloaddiv {
        background: white;
    }

    b > span {
        border-bottom: 2px solid red;
    }
</style>
<body>
<!--专题订制列表-->
<div class="topicDetail_content" style="">
    <div id="downloaddiv" style="width:100%; padding:20px;">
        <div class="topicDetail_content_title">
            <i style='background: url(../yuqing-static/img/2-12.png) no-repeat center center; background-size: 90%;'></i>
            <span class="back" style="cursor: pointer;">周报订制&nbsp;&gt;&nbsp;</span>
            <span style="color:#46CCA6; cursor:pointer">${title }舆情周报</span>
            <c:if test="${exportAuth eq '1'}">
                <button class="btn btn-info" style="border-radius: 6px; font-weight:bold;" onclick="exportbaogao('1')">导出至OA</button>
            </c:if>
            <button class="btn btn-info" style="border-radius: 6px; font-weight:bold;" onclick="exportbaogao('0')">导出报告
            </button>
        </div>
        <h1 style="margin-top: 20px">${title }舆情周报</h1>
        <div class="part1">
            <h2>一、舆情总览</h2>
            <h4>
                &nbsp;<b>本周舆情主要集中在平面媒体以及新媒体方面。</b>&nbsp;&nbsp;其中:
            </h4>
            <h4>
                1. 北京移动相关舆情&nbsp;<b> ${maxHotArticle[0].total}</b>&nbsp;条,其中 : <c:forEach items="${sourcetypeSum}" var="sourcetype">
                ${sourcetype.X}&nbsp;<b><span>${sourcetype.Y}</span></b>&nbsp;篇、
            </c:forEach>
            </h4>
            <h4>
                2. 最热一条舆情的舆情标题 : &nbsp;<b> ${maxHotArticle[0].title}</b>
            </h4>
            <h4>
                3. 共发现北京移动相关负面舆情&nbsp;<b><span>${fumianTotal}</span></b>&nbsp;条
            </h4>
            <h4>
                4. 友商舆情 : 北京联通相关舆情信息&nbsp;<b><span>${yidongTotal}</span></b>&nbsp;条，北京电信相关舆情信息&nbsp;<b><span>${dianxinTotal}</span></b>&nbsp;条。
            </h4>
        </div>
        <div class="part2">
            <h2>二、舆情特点</h2>
            <h3>1. 北京移动当前最热一条舆情内容</h3>
            <div class="articalList">
                <span>
                    <h4>
                        <i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;最热1条舆情如下：${maxHotArticle[0].title }</i>
                    </h4>
                    <h5>${maxHotArticle[0].articleContent}</h5>
                    <ul>
                        <li>评论量：<span class="llpl">${maxHotArticle[0].commentCnt } </span></li>
                        <li>发布时间：<span class="time">${maxHotArticle[0].publishTime }</span></li>
                        <li>文章来源：<span class='source' style="margin-right:30px;">${maxHotArticle[0].sourceType }</span></li>
                    </ul>
                    <p>
                        网址链接：<a href="${maxHotArticle[0].url }" target="_blank">${maxHotArticle[0].url }</a>
                    </p>
                </span>
            </div>
            <h3>2. 北京移动新媒体宣传方面</h3>
            <h4><b>微博 :</b></h4>
            <h4>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;系统监测到 ${title} 之间，&nbsp;<b>北京移动</b>&nbsp;相关微博共&nbsp;<b><span>${weiboTotal}</span></b>&nbsp;篇，评论量&nbsp;<b><span>${pinglunTotal}</span></b>&nbsp;次，转发量&nbsp;<b><span>${zhuanfaTotal}</span></b>&nbsp;次，点赞量&nbsp;<b><span>${dianzanTotal}</span></b>&nbsp;次
            </h4>
            <div class="articalList">
                <span>
                    <h4>
                        <i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;热度最高的一条微博如下：${maxHotWeiboArticle[0].title }</i>
                    </h4>
                    <h5> ${maxHotWeiboArticle[0].articleContent} </h5>
                    <ul>
                        <li>评论量：<span class="llpl">${maxHotWeiboArticle[0].commentCnt } </span></li>
                        <li>发布时间：<span class="time">${maxHotWeiboArticle[0].publishTime }</span></li>
                        <li>文章来源：<span class='source' style="margin-right:30px;">${maxHotWeiboArticle[0].sourceType }</span></li>
                    </ul>
                    <p>
                        网址链接：<a href="${maxHotWeiboArticle[0].url }" target="_blank">${maxHotWeiboArticle[0].url }</a>
                    </p>
                </span>
            </div>
            <h4><b>微信公众号 :</b></h4>
            <h4>
                <c:forEach items="${weixinSum}" var="weixin">
                    &nbsp;&nbsp;&nbsp;&nbsp;微信公众号 ${weixin.X} 发布北京移动相关文章&nbsp;<b><span>${weixin.Y}</span></b>&nbsp;篇。<br>
                </c:forEach>
            </h4>
            <h4>
                &nbsp;&nbsp;&nbsp;&nbsp;微信文章&nbsp;TOP&nbsp;<b><span>10</span></b>&nbsp;:
            </h4>
            <div id="main-weixin" style="width:100%;">
                <table class="table table-striped table-sort0">
                    <thead style="text-align: center;">
                    <tr>
                        <th class="col-md-1" >序号</th>
                        <th class="col-md-5" data-type="title">标题</th>
                        <th class="col-md-1" data-type="string">来源</th>
                        <th class="col-md-1" data-type="string">公众号</th>
                        <th class="col-md-2" data-type="date">发表时间</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${maxHotWeixinArticle }" var="article" varStatus="status">
                        <tr>
                            <td>${status.count }</td>
                            <td style="cursor: pointer;"><a href="javascript:void(0)" url="${article.url }" sourcetype="${article.sourceType }" articleid="${article.id }">${article.title }</a></td>
                            <c:if test="${article.sourceType eq '客户端'}">
                                <td class="client"><i></i><span>${article.sourceType }</span></td>
                            </c:if>
                            <c:if test="${article.sourceType eq '微信'}">
                                <td class="wechat"><i></i><span>${article.sourceType }</span></td>
                            </c:if>
                            <c:if test="${article.sourceType eq '搜索引擎'}">
                                <td class="search"><i></i><span>${article.sourceType }</span></td>
                            </c:if>
                            <c:if test="${article.sourceType eq '网页'}">
                                <td class="media"><i></i><span>${article.sourceType }</span></td>
                            </c:if>
                            <c:if test="${article.sourceType eq '微博'}">
                                <td class="mblog"><i></i><span>${article.sourceType }</span></td>
                            </c:if>
                            <c:if test="${article.sourceType eq '论坛'}">
                                <td class="forum"><i></i><span>${article.sourceType }</span></td>
                            </c:if>
                            <c:if test="${article.sourceType eq '报刊'}">
                                <td class="paper"><i></i><span>${article.sourceType }</span></td>
                            </c:if>
                            <td>${article.dataSource }</td>
                            <td>${article.publishTime }</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <h4><b>客户端 :</b></h4>
            <h4>
                <c:forEach items="${kehuduanSum}" var="kehuduan">
                    <c:if test="${kehuduan.X != ''}">
                        &nbsp;&nbsp;&nbsp;&nbsp;${kehuduan.X}&nbsp;发布北京移动相关文章&nbsp;<b><span>${kehuduan.Y}</span></b>&nbsp;篇。<br>
                    </c:if>
                </c:forEach>
            </h4>
            <h4>
                &nbsp;&nbsp;&nbsp;&nbsp;客户端文章&nbsp;TOP&nbsp;<b><span>10</span></b>&nbsp;:
            </h4>
            <div id="main10" style="width:100%;">
                <table class="table table-striped table-sort1">
                    <thead style="text-align: center;">
                    <tr>
                        <th class="col-md-1" >序号</th>
                        <th class="col-md-5" data-type="title">标题</th>
                        <th class="col-md-1" data-type="string">来源</th>
                        <th class="col-md-1" data-type="string">网站</th>
                        <th class="col-md-2" data-type="date">发表时间</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${kehuduanList }" var="article" varStatus="status">
                        <tr>
                            <td>${status.count }</td>
                            <td style="cursor: pointer;"><a href="javascript:void(0)" url="${article.url }" sourcetype="${article.sourceType }" articleid="${article.id }">${article.title }</a></td>
                            <c:if test="${article.sourceType eq '客户端'}">
                                <td class="client"><i></i><span>${article.sourceType }</span></td>
                            </c:if>
                            <c:if test="${article.sourceType eq '微信'}">
                                <td class="wechat"><i></i><span>${article.sourceType }</span></td>
                            </c:if>
                            <c:if test="${article.sourceType eq '搜索引擎'}">
                                <td class="search"><i></i><span>${article.sourceType }</span></td>
                            </c:if>
                            <c:if test="${article.sourceType eq '网页'}">
                                <td class="media"><i></i><span>${article.sourceType }</span></td>
                            </c:if>
                            <c:if test="${article.sourceType eq '微博'}">
                                <td class="mblog"><i></i><span>${article.sourceType }</span></td>
                            </c:if>
                            <c:if test="${article.sourceType eq '论坛'}">
                                <td class="forum"><i></i><span>${article.sourceType }</span></td>
                            </c:if>
                            <c:if test="${article.sourceType eq '报刊'}">
                                <td class="paper"><i></i><span>${article.sourceType }</span></td>
                            </c:if>
                            <td>${article.dataSource }</td>
                            <td>${article.publishTime }</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="part3">
            <h2>三、舆情案例</h2>
            <h3>1. ${title }之间，北京移动相关 <b>负面舆情</b> 展示：</h3>
            <div id="main-fan" style="width:100%;">
                <table class="table table-striped table-sort2">
                    <thead style="text-align: center;">
                    <tr>
                        <th class="col-md-1" >序号</th>
                        <th class="col-md-5" data-type="title">标题</th>
                        <th class="col-md-1" data-type="string">来源</th>
                        <th class="col-md-1" data-type="string">网站</th>
                        <th class="col-md-2" data-type="date">发表时间</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${fumianList }" var="article1"
                               varStatus="status">
                        <tr>
                            <td>${status.count }</td>
                            <td style="cursor: pointer;"><a href="javascript:void(0)" url="${article1.url }" sourcetype="${article1.sourceType }" articleid="${article1.id }">${article1.title }</a></td>
                            <c:if test="${article1.sourceType eq '客户端'}">
                                <td class="client"><i></i><span>${article1.sourceType }</span></td>
                            </c:if>
                            <c:if test="${article1.sourceType eq '微信'}">
                                <td class="wechat"><i></i><span>${article1.sourceType }</span></td>
                            </c:if>
                            <c:if test="${article1.sourceType eq '搜索引擎'}">
                                <td class="search"><i></i><span>${article1.sourceType }</span></td>
                            </c:if>
                            <c:if test="${article1.sourceType eq '网页'}">
                                <td class="media"><i></i><span>${article1.sourceType }</span></td>
                            </c:if>
                            <c:if test="${article1.sourceType eq '微博'}">
                                <td class="mblog"><i></i><span>${article1.sourceType }</span></td>
                            </c:if>
                            <c:if test="${article1.sourceType eq '论坛'}">
                                <td class="forum"><i></i><span>${article1.sourceType }</span></td>
                            </c:if>
                            <c:if test="${article1.sourceType eq '报刊'}">
                                <td class="paper"><i></i><span>${article1.sourceType }</span></td>
                            </c:if>
                            <td>${article1.dataSource }</td>
                            <td>${article1.publishTime }</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <br>
        <div class="part4">
            <h2>四、友商舆情特点</h2>
            <h3>1. ${title }之间，<b>北京移动</b>相关舆情展示：</h3>
            <div id="main-yidong" style="width:100%;">
                <table class="table table-striped table-sort3">
                    <thead style="text-align: center;">
                    <tr>
                        <th class="col-md-1" >序号</th>
                        <th class="col-md-5" data-type="title">标题</th>
                        <th class="col-md-1" data-type="string">来源</th>
                        <th class="col-md-1" data-type="string">网站</th>
                        <th class="col-md-2" data-type="date">发表时间</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${yidongList }" var="article1" varStatus="status">
                        <tr>
                            <td>${status.count }</td>
                            <td style="cursor: pointer;"><a href="javascript:void(0)" url="${article1.url }" sourcetype="${article1.sourceType }" articleid="${article1.id }">${article1.title }</a></td>
                            <c:if test="${article1.sourceType eq '客户端'}">
                                <td class="client"><i></i><span>${article1.sourceType }</span></td>
                            </c:if>
                            <c:if test="${article1.sourceType eq '微信'}">
                                <td class="wechat"><i></i><span>${article1.sourceType }</span></td>
                            </c:if>
                            <c:if test="${article1.sourceType eq '搜索引擎'}">
                                <td class="search"><i></i><span>${article1.sourceType }</span></td>
                            </c:if>
                            <c:if test="${article1.sourceType eq '网页'}">
                                <td class="media"><i></i><span>${article1.sourceType }</span></td>
                            </c:if>
                            <c:if test="${article1.sourceType eq '微博'}">
                                <td class="mblog"><i></i><span>${article1.sourceType }</span></td>
                            </c:if>
                            <c:if test="${article1.sourceType eq '论坛'}">
                                <td class="forum"><i></i><span>${article1.sourceType }</span></td>
                            </c:if>
                            <c:if test="${article1.sourceType eq '报刊'}">
                                <td class="paper"><i></i><span>${article1.sourceType }</span></td>
                            </c:if>
                            <td>${article1.dataSource }</td>
                            <td>${article1.publishTime }</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <h3>2. ${title }之间，<b>北京电信</b>相关舆情展示：</h3>
            <div id="main-dianxin" style="width:100%;">
                <table class="table table-striped table-sort4">
                    <thead style="text-align: center;">
                    <tr>
                        <th class="col-md-1" >序号</th>
                        <th class="col-md-5" data-type="title">标题</th>
                        <th class="col-md-1" data-type="string">来源</th>
                        <th class="col-md-1" data-type="string">网站</th>
                        <th class="col-md-2" data-type="date">发表时间</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${dianxinList }" var="article1"
                               varStatus="status">
                        <tr>
                            <td>${status.count }</td>
                            <td style="cursor: pointer;"><a href="javascript:void(0)" url="${article1.url }" sourcetype="${article1.sourceType }" articleid="${article1.id }">${article1.title }</a></td>
                            <c:if test="${article1.sourceType eq '客户端'}">
                                <td class="client"><i></i><span>${article1.sourceType }</span></td>
                            </c:if>
                            <c:if test="${article1.sourceType eq '微信'}">
                                <td class="wechat"><i></i><span>${article1.sourceType }</span></td>
                            </c:if>
                            <c:if test="${article1.sourceType eq '搜索引擎'}">
                                <td class="search"><i></i><span>${article1.sourceType }</span></td>
                            </c:if>
                            <c:if test="${article1.sourceType eq '网页'}">
                                <td class="media"><i></i><span>${article1.sourceType }</span></td>
                            </c:if>
                            <c:if test="${article1.sourceType eq '微博'}">
                                <td class="mblog"><i></i><span>${article1.sourceType }</span></td>
                            </c:if>
                            <c:if test="${article1.sourceType eq '论坛'}">
                                <td class="forum"><i></i><span>${article1.sourceType }</span></td>
                            </c:if>
                            <c:if test="${article1.sourceType eq '报刊'}">
                                <td class="paper"><i></i><span>${article1.sourceType }</span></td>
                            </c:if>
                            <td>${article1.dataSource }</td>
                            <td>${article1.publishTime }</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<div class="modal" id="detailModal">
    <!--半透明遮罩层-->
    <div class="modal-content" style="overflow: auto;left:15%;margin-left: 5%!important; margin-top: 5%!important;width: 70%;height: 500px;">
        <div id="all_content">
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    var tableList = [".table-sort0",".table-sort1",".table-sort2",".table-sort3",".table-sort4"];

    function exportbaogao(exportflag) {
        $(".topicDetail_content_title").css("display", "none");
        let name = '${title}';
        let downloaddiv = $('#downloaddiv');
        let width = downloaddiv.outerWidth();
        let height = downloaddiv.height();
        let c = document.createElement("canvas");
        c.width = width * 2;
        c.height = height * 2;
        c.getContext("2d").scale(2,2);
        let rect = downloaddiv.get(0).getBoundingClientRect();//获取元素相对于视察的偏移量
        c.getContext("2d").translate(-rect.left-15,-rect.top);
        let opts = {
            scale: 2,
            dpi: window.devicePixelRatio*2,
            logging: false,
            canvas: c,
            width: width,
            height: height,
            onrendered:function (canvas) {
                //返回图片dataURL，参数：图片格式和清晰度(0-1)
                let pageData = canvas.toDataURL('image/jpeg', 1.0);
                //方向默认竖直，尺寸ponits，格式a4[595.28,841.89]
                let pdf = new jsPDF('', 'pt', [595.28, 3500]);
                //addImage后两个参数控制添加图片的尺寸，此处将页面高度按照a4纸宽高比列进行压缩
                pdf.addImage(pageData, 'JPEG', 0, 0, 595.28, 595.28 / canvas.width * canvas.height);
                if (exportflag === "0"){
                    pdf.save(name + '舆情周报.pdf');
                } else {
                    //取得日报的日期
                    let baogaoTime = '${baogaoTime}';
                    if (confirm("是否将PDF上传到OA？")) {
                        let pdfContent = pdf.output("datauristring");
                        $.ajax({
                            url: "<%=contextPath%>/yuqing/dingzhi/pdfToOA",
                            type: "post",
                            data: {
                                pdfname: '北京移动监测数据周报' + baogaoTime,
                                pdfContent: pdfContent
                            },
                            success: function (data) {
                                alert(data)
                            }
                        });
                    }
                }
            }
        };
        html2canvas(downloaddiv,opts);
        $(".topicDetail_content_title").css("display", "block");
    }

    $(function () {
        //列表文章点击事件
        $("tbody tr td a").on("click",function () {
            var sourceType = $(this).attr("sourcetype");
            if (sourceType === '微博' || sourceType === '微信' || sourceType === '客户端') {
                var id = $(this).attr("articleid");
                $.ajax({
                    url: "<%=contextPath%>/yuqing/dingzhi/gotoSourceNet",
                    type: "post",
                    data: {
                        "articleId": id
                    },
                    success: function (data) {
                        $("#all_content").html(data);
                        $("#detailModal").fadeIn(300);
                    },
                    error: function () {
                        alert("请求失败");
                    }
                });
            }else {
                var url = $(this).attr("url");
                window.open(url);
            }
        });
    });
</script>
<script type="text/javascript" src="../yuqing-static/js/dingzhi-tablesort.js"></script>
</html>
