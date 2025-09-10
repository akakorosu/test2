<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cxt" uri="http://www.bonc.com.cn/common/tag/cxt"%>
<!-- 编辑表单div -->
<script type="text/javascript">
    var dateId = "<%=request.getParameter("dateId")%>";
    var type = "<%=request.getParameter("type")%>";
</script>
<div>
    <!-- 列表div -->
    <div id="gridContainer">
        <table id="grid-table"></table>
        <div id="grid-pager"></div>

    </div>
</div>



<script src="<%=request.getContextPath()%>/pages/jsp/dpi/applicationRate/applicationRateView.js"></script>
