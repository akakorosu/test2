<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
    String contextPath = request.getContextPath();
%>
<html>
<head>
    <link rel="stylesheet" type="text/css"
          href="../yuqing-static/css/bootstrap-datetimepicker.css"/>
    <script type="text/javascript"
            src="../yuqing-static/js/bootstrap-datetimepicker.js"></script>
    <script type="text/javascript"
            src="../yuqing-static/js/bootstrap-datetimepicker.zh-CN.js"></script>
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
<div class="topicDetail_content" style="width:100%;">
    <div id="downloaddiv" style="width:100%; padding:20px;">
        <div class="topicDetail_content_title">
            <i style='background: url(../yuqing-static/img/2-12.png) no-repeat center center; background-size: 90%;'></i>
            <span class="back" style="cursor: pointer;">专题订制列表&nbsp;&gt;&nbsp;</span>
            <span style="color:#46CCA6; cursor:pointer">${monitorTime }</span>
            <c:if test="${exportAuth eq '1'}">
                <button class="btn btn-info"
                        style="border-radius: 6px; font-weight:bold;" onclick="exportbaogao('1')">导出至OA
                </button>
            </c:if>
            <button class="btn btn-info"
                    style="border-radius: 6px; font-weight:bold;" onclick="exportbaogao('0')">导出报告
            </button>
            <button id="download" class="btn btn-info"
                    style="border-radius: 6px; font-weight:bold;">保存模板
            </button>
        </div>
        <h1 style="margin-top: 20px;">${monitorTime }</h1>
        <div class="part1">
            <h2>一、舆情总览</h2>
            <h4>
                &nbsp;<b>${monitorTime }</b>&nbsp;&nbsp;，监测到&nbsp;<b id="subjectname-fan">${subjectname }</b>&nbsp;主题相关的舆情信息共&nbsp;<b><span>${mediamap.total }</span></b>&nbsp;条,其中:
            </h4>
            <h4>
                1.从社交媒体信息结构维度分析，&nbsp;<b> ${mediamap.X }</b>&nbsp;舆情信息占比最大，总计&nbsp;<b><span>${mediamap.Y }</span></b>&nbsp;条，整体占比&nbsp;<b><span>${mediaPrecent }</span></b>&nbsp;。
            </h4>
            <h4>
                2.从信息情感积极性维度分析，&nbsp;<b>${emotmap.X }面</b>&nbsp;舆情信息占比最多，总计&nbsp;<b><span>${emotmap.Y }</span></b>&nbsp;条，整体占比&nbsp;<b><span>${emotPrecent }</span></b>&nbsp;。
            </h4>
        </div>
        <div class="part2">
            <h2>二、舆情监测</h2>
            <h3>1.热门文章</h3>
            <h4>
                系统监测到与&nbsp;<b>${subjectname }</b>&nbsp;主题相关的最新舆情共有&nbsp;<b><span>${mediamap.total }</span></b>&nbsp;条，其中热度排行&nbsp;<b>TOP<span
                    id="article_count">${fn:length(hotArticleList)}</span></b>&nbsp;如下。
            </h4>
            <div id="main4" style="width:100%;">
                <table class="table table-striped table-sort0">
                    <thead style="text-align: center;">
                    <tr>
                        <th class="col-md-1">序号</th>
                        <th class="col-md-5" data-type="title">标题</th>
                        <th class="col-md-1" data-type="string">来源</th>
                        <th class="col-md-1" data-type="string">网站</th>
                        <th class="col-md-2" data-type="date">发表时间</th>
                    </tr>
                    </thead>
                    <tbody class="article_fan">
                    <c:forEach items="${hotArticleList }" var="article"
                               varStatus="status">
                        <tr>
                            <td class="xuhao_article">${status.count }</td>
                            <td style="cursor: pointer;"><a href="javascript:void(0)" url="${article.url }"
                                                            sourcetype="${article.sourceType }"
                                                            articleid="${article.id }">${article.title }</a></td>
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
                            <c:if test="${article.dataSource eq '百度贴吧'}">
                                <td class="paper"><i></i><span>百度贴吧</span></td>
                            </c:if>
                            <td>${article.dataSource }</td>
                            <td>${article.publishTime }</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="articalList weiwang_fan">
                <c:forEach items="${weiboList }" var="weibo" varStatus="status">
                    <c:if test="${status.count < 6 }">
                        <span>
                        <h4>
                            微博文章<span class="xuhao_weibo">${status.count }</span>：<i>${weibo.title }</i>
                        </h4>
                        <h5>   ${fn:substring(weibo.articleContent,0,500)}... </h5>
                        <ul>
                            <li>浏览评论量：<span class="llpl">${weibo.commentCnt } </span></li>
                            <li>发布时间：<span class="time">${weibo.publishTime }</span></li>
                            <li>文章来源：<span class='source' style="margin-right:30px;">${weibo.sourceType }</span></li>
                        </ul>
                        <p>
                            网址链接：<a href="${weibo.url }" target="_blank">${weibo.url }</a>
                        </p>
                        </span>
                    </c:if>
                </c:forEach>
            </div>
            <div class="articalList weiwang_fan" id="wangye_fan">
                <c:forEach items="${webpageList }" var="webpage"
                           varStatus="status">
                    <c:if test="${status.count < 6 }">
                        <span>
                        <h4>
                            网页文章<span class="xuhao_wangye">${status.count }</span>：<i>${webpage.title }</i>
                        </h4>
                        <h5>${fn:substring(webpage.articleContent,0,500)}... </h5>
                        <ul>
                            <li>发布时间：<span class="time">${webpage.publishTime }</span></li>
                            <li>文章来源：<span class='source' style="margin-right:30px;">${webpage.sourceType }</span></li>
                        </ul>
                        <p>
                            网址链接：<a href="${webpage.url }" target="_blank">${webpage.url }</a>
                        </p>
                        </span>
                    </c:if>
                </c:forEach>
            </div>

            <h3>2.热门词云</h3>
            <h4>
                系统监测到与&nbsp;<b>${subjectname }</b>&nbsp;主题相关的热门词TOP<span id="word_count"><c:choose><c:when
                    test="${fn:length(hotWordList) >= 50}">50</c:when><c:otherwise>${fn:length(hotWordList)}</c:otherwise></c:choose></span>如下:
            </h4>
            <div class="hotk_wordcloud_content word_fan">
                <ul>
                    <c:forEach items="${hotWordList }" var="hot" varStatus="status">
                        <c:if test="${status.count < 51 }">
                            <li>${hot.name }<span>${hot.value }</span></li>
                        </c:if>
                    </c:forEach>
                </ul>
            </div>
        </div>

        <div class="part3">
            <h2>三、舆情分析</h2>
            <h3>1.走势分析</h3>
            <h4>
                系统监测到<b>${monitorTime }</b>与&nbsp;<b>${subjectname }</b>&nbsp;主题相关的相关舆情共有&nbsp;<b><span>${mediamap.total}</span></b>&nbsp;条，舆论声量波动趋势如下:
            </h4>
            <div id="main5" style="width:1000px; height:360px; margin-top:30px;">
            </div>
            <h4>
                图中峰值点&nbsp;${maxday}&nbsp;文章列表&nbsp;TOP&nbsp;<b><span>${fn:length(trendList)}</span></b>&nbsp;
                :
            </h4>
            <div id="main-fan" style="width:100%;">
                <table class="table table-striped table-sort1">
                    <thead style="text-align: center;">
                    <tr>
                        <th class="col-md-1">序号</th>
                        <th class="col-md-5" data-type="title">标题</th>
                        <th class="col-md-1" data-type="string">来源</th>
                        <th class="col-md-1" data-type="string">网站</th>
                        <th class="col-md-2" data-type="date">发表时间</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${trendList }" var="article1"
                               varStatus="status">
                        <tr>
                            <td>${status.count }</td>
                            <td style="cursor: pointer;"><a href="javascript:void(0)" url="${article1.url }"
                                                            sourcetype="${article1.sourceType }"
                                                            articleid="${article1.id }">${article1.title }</a>
                            </td>
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
                            <c:if test="${article.dataSource eq '百度贴吧'}">
                                <td class="paper"><i></i><span>百度贴吧</span></td>
                            </c:if>
                            <td>${article1.dataSource }</td>
                            <td>${article1.publishTime }</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <h3>2.传播分析</h3>
            <h4>
                系统监测到<b>${monitorTime }</b>与&nbsp;<b>${subjectname }</b>&nbsp;主题相关的主要信息来源为&nbsp;<b>(${resourceType})</b>&nbsp;，各媒体传播此相关舆情占比如下:
            </h4>
            <div id="main6" style="width:1000px; height:360px; margin-top:30px;">
            </div>
            <h3>3.情感分析</h3>
            <h4>
                系统监测到<b>${monitorTime }</b>与&nbsp;<b>${subjectname }</b>&nbsp;主题相关的舆情在情感积极性方面主要以&nbsp;<b>${emotmap.X }</b>&nbsp;面为主，具体分布情况如下图:
            </h4>
            <div id="main7" style="width:1000px; height:360px;"></div>
            <h4>
                系统监测到<b>${monitorTime }</b>与&nbsp;<b>${subjectname }</b>&nbsp;主题相关的负面舆情共有&nbsp;<b><span>${fuTotal }</span></b>&nbsp;条，其中&nbsp;<b>TOP<span
                    id="negative_count">${fn:length(negativeList)}</span></b>&nbsp;如下。
            </h4>
            <div id="main10" style="width:100%;">
                <table class="table table-striped table-sort2">
                    <thead style="text-align: center;">
                    <tr>
                        <th class="col-md-1">序号</th>
                        <th class="col-md-5" data-type="title">标题</th>
                        <th class="col-md-1" data-type="string">来源</th>
                        <th class="col-md-1" data-type="string">网站</th>
                        <th class="col-md-2" data-type="date">发表时间</th>
                    </tr>
                    </thead>
                    <tbody class="article_fan" id="negative_article">
                    <c:forEach items="${negativeList }" var="article"
                               varStatus="status">
                        <tr>
                            <td class="xuhao_negative">${status.count }</td>
                            <td style="cursor: pointer;"><a href="javascript:void(0)" url="${article.url }"
                                                            sourcetype="${article.sourceType }"
                                                            articleid="${article.id }">${article.title }</a></td>
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
                            <c:if test="${article.dataSource eq '百度贴吧'}">
                                <td class="paper"><i></i><span>百度贴吧</span></td>
                            </c:if>
                            <td>${article.dataSource }</td>
                            <td>${article.publishTime }</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="part4">
            <h2>四、电信行业舆情</h2>
            <h3>1.热门文章</h3>
            <h4>
                系统监测到与&nbsp;<b>电信行业</b>&nbsp;主题相关的最新舆情共有&nbsp;<b><span>${dxTotal}</span></b>&nbsp;条，其中热度排行&nbsp;<b>TOP<span
                    id="dx_count">${fn:length(dxList)}</span></b>&nbsp;如下。
            </h4>
            <div id="main11" style="width:100%;">
                <table class="table table-striped table-sort3">
                    <thead style="text-align: center;">
                    <tr>
                        <th class="col-md-1">序号</th>
                        <th class="col-md-5" data-type="title">标题</th>
                        <th class="col-md-1" data-type="string">来源</th>
                        <th class="col-md-1" data-type="string">网站</th>
                        <th class="col-md-2" data-type="date">发表时间</th>
                    </tr>
                    </thead>
                    <tbody class="article_fan" id="dx_article">
                    <c:forEach items="${dxList }" var="article"
                               varStatus="status">
                        <tr>
                            <td class="xuhao_dx">${status.count }</td>
                            <td style="cursor: pointer;"><a href="javascript:void(0)" url="${article.url }"
                                                            type="dianxin" sourcetype="${article.sourceType }"
                                                            articleid="${article.articleId }">${article.title }</a></td>
                            <td class="${article.sourceCode}"><i></i><span>${article.sourceType}</span></td>
                            <td>${article.dataSource }</td>
                            <td>${article.publishTime }</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <br><br>
        </div>
    </div>
</div>
<div style="display: none"><input id="htmlId" type="text" value="${htmlId}"/></div>
<div class="btn-group btn-group-sm" id="btn_fan1" style="z-index: 9;position: absolute;display: none;">
    <button type="button" class="btn btn-default" style="background:#39B3D7;" id="btn_add1">增加</button>
    <button type="button" class="btn btn-default delete_fan1" style="background:#39B3D7;">删除</button>
    <button type="button" class="btn btn-default btn_exit" style="background:#39B3D7;">关闭</button>
</div>
<div class="btn-group btn-group-sm" id="btn_fan3" style="z-index: 9;position: absolute;display: none;">
    <button type="button" class="btn btn-default" style="background:#39B3D7;" id="btn_add2">增加</button>
    <button type="button" class="btn btn-default delete_fan3" style="background:#39B3D7;">删除</button>
    <button type="button" class="btn btn-default btn_exit" style="background:#39B3D7;">关闭</button>
</div>
<div class="btn-group btn-group-sm" id="btn_fan2" style="z-index: 9;position: absolute;display: none;">
    <button type="button" class="btn btn-default delete_fan2" style="background:#39B3D7;">删除</button>
    <button type="button" class="btn btn-default btn_exit" style="background:#39B3D7;">关闭</button>
</div>
<div id="mymodal1"
     style="width:450px;top:20%;left: 40%;position: absolute;background-color: white;padding:30px;border-radius:5px;border:5px solid #32C08D;display: none">
    <form class="form-horizontal" role="form">
        <div class="form-group">
            <label class="col-sm-2 control-label">标题</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="title1">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">URL</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="url1" placeholder="以http(s)://开头">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">来源</label>
            <div class="col-sm-10">
                <select class="form-control" id="source1">
                    <option value="media">网页</option>
                    <option value="mblog">微博</option>
                    <option value="paper">报刊</option>
                    <option value="client">客户端</option>
                    <option value="wechat">微信</option>
                    <option value="forum">论坛</option>
                    <option value="search">搜索引擎</option>
                    <option value="search">百度贴吧</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">网站</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="net1">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">发表时间</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="date" readonly>
            </div>
        </div>
    </form>
    <div style="text-align: center">
        <button class="btn btn-danger" id="btn-quxiao1">取消</button>
        <button class="btn btn-info" id="btn-queding1">确定</button>
    </div>
</div>
<div id="mymodal2"
     style="width:550px;top:20%;left: 30%;position: absolute;background-color: white;padding:30px;border-radius:5px;border:5px solid #32C08D;display: none">
    <form class="form-horizontal" role="form">
        <div class="form-group">
            <label class="col-sm-2 control-label">标题</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="title2">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">内容</label>
            <div class="col-sm-10">
                <textarea rows="5" cols="60" id="content2"> </textarea>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">URL</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="url2" placeholder="以http(s)://开头">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">来源</label>
            <div class="col-sm-10">
                <select class="form-control" id="source2">
                    <option>网页</option>
                    <option>微博</option>
                    <option>报刊</option>
                    <option>客户端</option>
                    <option>微信</option>
                    <option>论坛</option>
                    <option>搜索引擎</option>
                    <option>百度贴吧</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">发表时间</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="date2" readonly>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">评论量</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="pinglun2">
            </div>
        </div>
    </form>
    <div style="text-align: center">
        <button class="btn btn-danger" id="btn-quxiao2">取消</button>
        <button class="btn btn-info" id="btn-queding2">确定</button>
    </div>
</div>

<div class="modal" id="detailModal">
    <!--半透明遮罩层-->
    <div class="modal-content"
         style="overflow: auto;left:15%;margin-left: 5%!important; margin-top: 5%!important;width: 70%;height: 500px;">
        <div id="all_content">
        </div>
    </div>
</div>
<div style="display: none;"><span id="ifsave">0</span></div>
</body>
<script type="text/javascript" src="../yuqing-static/js/dingzhi-baogao.js"></script>
<script type="text/javascript">
    //表格内容排序用到
    var tableList = [".table-sort0", ".table-sort1", ".table-sort2", ".table-sort3"];

    function exportbaogao(export2OA) {
        $(".topicDetail_content_title").css("display", "none");
        let name = $("#subjectname-fan").text();
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
            canvas: c,
            logging: false,
            width: width,
            height: height,
            onrendered:function (canvas) {
                //返回图片dataURL，参数：图片格式和清晰度(0-1)
                let pageData = canvas.toDataURL('image/jpeg', 1.0);
                //方向默认竖直，尺寸ponits，格式a4[595.28,841.89]
                let pdf = new jsPDF('', 'pt', [595.28, 4500]);
                //addImage后两个参数控制添加图片的尺寸，此处将页面高度按照a4纸宽高比列进行压缩
                pdf.addImage(pageData, 'JPEG', 0, 0, 595.28, 595.28 / canvas.width * canvas.height);
                if (export2OA === "0") {
                    pdf.save(name + '-定制报告.pdf');
                }
                if (export2OA === "1") {
                    //取得日报的日期
                    let baogaoTime = '${baogaoTime}';
                    if (confirm("是否将PDF导出到OA？")) {
                        let pdfContent = pdf.output("datauristring");
                        $.ajax({
                            url: "<%=contextPath%>/yuqing/dingzhi/pdfToOA",
                            type: "post",
                            data: {
                                pdfname: '北京移动监测数据日报' + baogaoTime,
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

    var htmlContent = "HTMLCONTENT";
    var downPdf = document.getElementById("download");
    downPdf.onclick = function () {
        htmlContent = "HTMLCONTENT2";
        insert();
        $("#download").attr("disabled", "disabled");
    };

    $(function () {
        //进页面时保存一份模板一
        let saveSign = $("#ifsave");
        if ("0" === saveSign.text()) {
            saveSign.text("1");
            insert();
        }

        //列表文章点击事件
        $("tbody tr td a").on("click", function () {
            let sourceType = $(this).attr("sourcetype");
            let type = $(this).attr("type");
            if (sourceType === '微博' || sourceType === '微信' || sourceType === '客户端') {
                var id = $(this).attr("articleid");
                $.ajax({
                    url: "<%=contextPath%>/yuqing/dingzhi/gotoSourceNet",
                    type: "post",
                    data: {
                        "type": type,
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
            } else {
                let url = $(this).attr("url");
                window.open(url);
            }
        });
    });

    function insert() {
        let htmlId = $("#htmlId").val();
        let monitoringtime = '${monitoringTime}';
        let subjectname = '${subjectname}';
        let url = "<%=contextPath%>/yuqing/dingzhi/insertRecord";
        let pdf = $("#subject_customization")[0].innerHTML;
        $.ajax({
            url: url,
            type: "post",
            data: {
                "id": htmlId,
                "fieldName": htmlContent,
                "monitorTime": monitoringtime,
                "title": subjectname,
                "htmlcontent": pdf
            },
            success: function (data) {
                if (data !== "") {
                    alert(data);
                }
            },
            error: function () {
                alert("请求失败");
            }
        });
    }

    var myChart5 = echarts.init(document.getElementById('main5'));
    option5 = {
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['xxx主题']
        },
        toolbox: {
            show: true,
            feature: {
                dataZoom: {
                    yAxisIndex: 'none'
                },
                dataView: {readOnly: false},
                magicType: {type: ['line', 'bar']},
                restore: {},
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: ${yqTrend_X}
        },
        yAxis: {
            type: 'value',
            axisLabel: {
                formatter: '{value}'
            }
        },
        series: [
            {
                name: '${subjectname}主题',
                type: 'line',
                data: ${yqTrend_Y},
                markPoint: {
                    data: [
                        {type: 'max', name: '最大值'},
                        {type: 'min', name: '最小值'}
                    ]
                }
            }
        ]
    };
    myChart5.setOption(option5);

    var myChart6 = echarts.init(document.getElementById("main6"));
    option6 = {
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            x: 'center',
            data: ['传播分析']
        },
        radar: [
            {
                indicator: ${diffuseTrend_value_max},
                center: ['50%', '50%'],
                radius: 90
            },

        ],
        series: [
            {
                type: 'radar',
                tooltip: {
                    trigger: 'item'
                },
                itemStyle: {normal: {areaStyle: {type: 'default'}}},
                data: [
                    {
                        value:${diffuseTrend_value},
                        name: '传播分析'
                    }
                ]
            }


        ]
    };

    myChart6.setOption(option6);

    var myChart7 = echarts.init(document.getElementById("main7"));
    option7 = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            x: 'center',
            y: '80%',
            data:${emotLengend}
        },
        toolbox: {
            show: true,
            feature: {
                mark: {show: true},
                dataView: {show: true, readOnly: false},
                magicType: {
                    show: true,
                    type: ['pie', 'funnel']
                },
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        calculable: true,
        series: {
            name: '情感',
            type: 'pie',
            radius: [20, 90],
            center: ['50%', '45%'],
            roseType: 'area',
            data:${emotValue}
        }
    };
    myChart7.setOption(option7);
</script>
<%--列表排序js--%>
<script type="text/javascript" src="../yuqing-static/js/dingzhi-tablesort.js"></script>
</html>