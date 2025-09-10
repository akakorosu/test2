<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String contextPath = request.getContextPath();
%>
<html lang="en">
<body>
<!--专题订制部分-->
<div class="topic_content content" style="padding:5px;">
    <div class="topic_title" style="display:none">
        <i></i><span>专题订制</span>
    </div>
    <!--导入页面的遮罩层-->
    <div class="modal" id="zhuantiModal"><!--半透明遮罩层-->
        <div class="modal-content"
        ><!--背景边框-->
            <div class="modal_title">
                <span>订制专题设置</span>
            </div>
            <div class="ta_date">
                <b>监测时间</b>
                <i></i>
                <span class="date_title" id="date3"></span>
            </div>
            <div class="ta_date">
                <b>专题名称</b>
                <i class="topicImg"></i>
<%--                 <c:choose>
                    <c:when test="${writeWordAuth eq '1'}">
                        <input type="text" value="北京移动" disabled/>
                    </c:when>
                    <c:otherwise>
                    </c:otherwise>
                </c:choose> --%>
                <input type="text" placeholder="***,***,***"/>
                <p style="font-weight: bold;color: #EBBB27;"><strong>(提示：关键信息请以","隔开，如：中国，移动，积分)</strong></p>
            </div>
            <button class="btn btn-danger cancelBtn">取消</button>
            <button id="query_button" class="btn btn-info">确定</button>
        </div>
    </div>

    <div class="modal" id="zhoubaoModal"><!--半透明遮罩层-->
        <div class="modal-content"><!--背景边框-->
            <div class="modal_title">
                <span>订制周报设置</span>
            </div>
            <div class="ta_date" style=" border: 0;">
                <b>监测周期</b>
                <i></i>
                <select class="form-control" style=" height: 36px;" id="zhoubaoDate">
                    <c:forEach items="${dayList }" var="day">
                        <option value='${day}'>${day}</option>
                    </c:forEach>
                </select>
            </div>
            <button class="btn btn-danger cancelBtn">取消</button>
            <button class="btn btn-info" id="query_button2">确定</button>
        </div>
    </div>
    <div class="content">
        <div class="topic_add">
            <button class="btn btn-info" id="zhoubaoButton"><i></i><span>周报订制</span></button>
            <button class="btn btn-info" id="zhuantiButton"><i></i><span>专题订制</span></button>
        </div>
        <div id="main3" style="width:100%; margin-top:30px;">
            <div id="table_custom">
            </div>
        </div>
        <div class="pagination-holder">
            <div id="light-pagination2" class="pagination"></div>
        </div>
    </div>
</div>
<div class="div-loading">
    拼命加载中,请稍后.....
</div>
</body>
<script type="text/javascript">
    changePage(1);
    <%--生成定制报告--%>
    $("#query_button").click(function () {
        $(".modal").css("display", "none");
        var monitoringtime = $("#date3").text();
        var subjectname = $("div.ta_date input").val();
        if (subjectname.trim() === '') {
            alert("请输入模板名称");
            return;
        }
        if (subjectname.indexOf(",") !== -1) {
            if (subjectname.match(/,/g).length > 4) {
                alert("最多只能输入5个关键词!");
                return;
            }
        }
        if (subjectname.indexOf("，") !== -1) {
            if (subjectname.match(/，/g).length > 4) {
                alert("最多只能输入5个关键词!");
                return;
            }
        }
        var url = "<%=contextPath%>/yuqing/dingzhi/baogao";
        $.ajax({
            url: url,
            type: "post",
            data: {"monitortime": monitoringtime, "subjectname": subjectname},
            beforeSend: function () {
                $(".topic_content").addClass("loading");
                $(".div-loading").css("display", "block");
            },
            success: function (data) {
                if (data.indexOf("词库没有") !== -1) {
                    $(".div-loading").css("display", "none");
                    $(".topic_content").removeClass("loading");
                    alert(data);
                } else {
                    $("div.source_content,div.topic_content").hide();
                    $("div#subject_customization").fadeIn(1000);
                    $(".div-loading").css("display", "none");
                    $("#subject_customization").html(data);
                }
            },
            error: function () {
                alert("请求失败");
                $(".div-loading").css("display", "none");
            }
        });
    });

    /*生成周报*/
    $("#query_button2").click(function () {
        var date = $("#zhoubaoDate").val();
        var url = "<%=contextPath%>/yuqing/dingzhi/zhoubao";
        $.ajax({
            url: url,
            type: "post",
            data: {"date": date},
            beforeSend: function () {
                $(".topic_content").addClass("loading");
                $(".div-loading").css("display", "block");
            },
            success: function (data) {
                $("div.source_content,div.topic_content").hide();
                $("div#subject_customization").fadeIn(1000);
                $(".div-loading").css("display", "none");
                $("#subject_customization").html(data);
            },
            error: function () {
                alert("请求失败");
                $(".div-loading").css("display", "none");
            }
        });
    });

    var mainWidth = $("div#content_right div.topicDetail_content_title").width();
    $("#main5,#main6,#main7").css("width", mainWidth);
    $("#main8").css("width", mainWidth * 0.59);
    $("#main9").css("width", mainWidth * 0.4);

    $("div.topicDetail_content_title span.back").click(function () {
        $("div.topicDetail_content").hide();
        $("div.topic_content").fadeIn(1000);
    });
    /*content_right的文章正负面切换*/
    $("div#content_right div.source_content>div.content div.content_title>div.option_cho ul li").click(function () {
        $(this).addClass("current");
        $(this).siblings("li").removeClass("current");
    });
    /*模态框的控制*/
    $('#zhuantiButton').click(function () {
        $('#zhuantiModal').fadeIn(300);
    });
    $('#zhoubaoButton').click(function () {
        $('#zhoubaoModal').fadeIn(300);
    });
    $('.cancelBtn').click(function () {
        $('.modal').fadeOut(300);
    });

    function changePage(currentPage) {
        if (currentPage == null) {
            currentPage = $("#light-pagination2").pagination('getCurrentPage');
        }
        var url = "<%=contextPath%>/yuqing/dingzhi/tablelist";
        $.ajax({
            url: url,
            type: "post",
            data: {
                "currentPage": currentPage
            },
            success: function (data) {
                $("#table_custom").html(data);
            },
            error: function () {
                alert("请求失败");
            }
        });
    }

    /*多选新闻列表*/
    $('.multiselect').multiselect({
        setMaxOptionNum: 5,
        selectedHtmlValue: '请选择'
    });
    /*日期插件的控制*/
    var dateRange3 = new pickerDateRange('date3', {
        isTodayValid: true, //今天是否可选
        startDate: ${today},
        endDate: ${today},
        startToday: false, //开始时间为今天
        stopToday: true, //结束时间为今天
        needCompare: false,
        defaultText: ' -- ',
        autoSubmit: true,
        inputTrigger: 'input_trigger1',
        theme: 'ta',
        minDate: '06/08/2017'
    });
</script>
</html>