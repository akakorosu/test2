<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String contextPath = request.getContextPath();
%>
<html>
<head>
</head>
<body>
<table class="table table-striped">
    <thead style="text-align: center;">
    <tr>
        <th>序号</th>
        <th>专题名称</th>
        <th>定制时间</th>
        <th>监测时间</th>
        <c:if test="${sessionScope.user.role != 'MEMBER'}">
            <th>定制人</th>
        </c:if>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${dingzhiList}" var="dingzhi">
        <tr>
            <td>${dingzhi.rn}</td>
            <td>${dingzhi.title }</td>
            <td>${dingzhi.insertTime }</td>
            <td>${dingzhi.monitorTime }</td>
            <c:if test="${sessionScope.user.role != 'MEMBER'}">
                <th>${dingzhi.userName}</th>
            </c:if>
            <td>
                <span onclick="checkin('HTMLCONTENT','${dingzhi.id }');"><i class="scan"></i><b
                        class="scan">查看1</b></span>
                <span>|</span>
                <span onclick="checkin('HTMLCONTENT2','${dingzhi.id }');"><i class="scan"></i><b
                        class="scan">查看2</b></span>
                <span>|</span>
                <span onclick="delete_('${dingzhi.id }');"><i class="delete"></i><b class="delete">删除</b></span>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
<script type="text/javascript">
    /*分页插件的样式控制*/
    $("#light-pagination2").pagination({
        items: '${page.totalPage}',
        cssStyle: 'light-theme',
        displayedPages: 7,
        currentPage: ${page.currentPage},
        onPageClick: changePage
    });

    function checkin(fieldName, id) {
        var url = "<%=contextPath%>/yuqing/dingzhi/getHtml";
        $.ajax({
            url: url,
            type: "post",
            data: {"fieldName": fieldName, "id": id},
            beforeSend: function () {
                $(".topic_content").addClass("loading");
                $(".div-loading").css("display", "block");
            },
            success: function (data) {
                $(".topic_content").hide();
                $("#customizationhome_table").hide();
                $("#subject_customization").css("display", "block");
                $(".div-loading").css("display", "none");
                $("#subject_customization").html(data);
            },
            error: function () {
                alert("请求失败");
                $(".div-loading").css("display", "none");
            }
        });
    }

    function delete_(id) {
        if (confirm("您确定要删除吗?")) {
            var url = "<%=contextPath%>/yuqing/dingzhi/delete";
            var currentPage = $("#light-pagination2").pagination('getCurrentPage');
            $.ajax({
                url: url,
                type: "post",
                data: {
                    "condition":1,
                    "currentPage": currentPage,
                    "id": id
                },
                success: function (data) {
                    $("#table_custom").html(data);
                    alert("删除成功!");
                },
                error: function () {
                    alert("请求失败");
                }
            });
        } else {
            return false;
        }
    }
</script>
</html>