<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.bonc.com.cn/common/tag/cxt" prefix="cxt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<cxt:commonLinks />

<script
	src="<%=request.getContextPath()%>/pages/jsp/dpi/operateCenter/operateLogInfo.js"></script>
<script
	src="<%=request.getContextPath()%>/pages/jsp/system/sysuser/sysUser.js"></script>
</head>
<style>
select {
	/*Chrome和Firefox里面的边框是不一样的，所以复写了一下*/
	border: solid 1px #D3D3D3;
	border-radius: 5px;
	/*很关键：将默认的select选择框样式清除*/
	appearance: none;
	-moz-appearance: none;
	-webkit-appearance: none;
	/*在选择框的最右侧中间显示小箭头图片*/
	background: url("http://ourjs.github.io/static/2015/arrow.png")
		no-repeat scroll rightright center transparent;
	/*为下拉小箭头留出一点位置，避免被文字覆盖*/
	padding-right: 14px;
}
/*清除ie的默认选择框样式清除，隐藏下拉箭头*/
select::-ms-expand {
	display: none;
}
</style>
<body>
	<div class="page-content clearfix">
		<div class="col-sm-12 grid-full">
			<!-- 查询div -->
			<div class="grid-search-div" id="gridSearch">
				<label>时间选择：</label> <input type="text"class="datepicker-input" placeholder="请选择时间" id="time1">
				<input type="text" placeholder="对象" class="searchField input-md" id="prodId" name="prodId">
				<button class="btn btn-primary" type="button" id="searchPiList">查询</button>
				<button class="btn btn-primary" type="button" id="clearList">清空</button>
				<button class="btn btn-primary" type="button" id="backto1">返回</button>
			</div>
			<!-- 列表div -->
			<div id="gridContainer">
				<table id="grid-table1"></table>
				<div id="grid-pager1"></div>
			</div>
		</div>
	</div>
</body>

</html>