<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.bonc.com.cn/common/tag/cxt" prefix="cxt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<cxt:commonLinks />
<title>明星列表</title>
<script src="<%=request.getContextPath()%>/pages/js/jquery-3.1.1.min.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/pages/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/pages/css/bootstrap-table.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/pages/jsp/dpi/actorList/style.css" />
<script src="<%=request.getContextPath()%>/pages/js/bootstrap.min.js"></script>
<script
	src="<%=request.getContextPath()%>/pages/js/bootstrap-table.min.js"></script>
<script
	src="<%=request.getContextPath()%>/pages/js/bootstrap-table-zh-CN.min.js"></script>
<script
	src="<%=request.getContextPath()%>/pages/jsp/dpi/actorList/actorList.js"></script>

<link rel="stylesheet" href="actorList.css" />
</head>
<body>
	<div class="optiontop">
		<div class="option-title">导演</div>
		<div class="option-sex">
			性别：<label class="active">全部</label><label onclick="alert(1)">男</label><label>女</label>
		</div>
		<div class="option-area">
			地区：<label class="active">全部</label><label>内地</label><label>香港</label>
			<label>台湾</label><label>日本</label><label>韩国</label>
		</div>
	</div>
	<%-- <div class="res clearfix">
			<div class="resList">
				<img src="<%=request.getContextPath()%>/pages/images/new/li.jpg" />
				<p>李晨</p>
			</div>
			
		</div> --%>
		
	<div class="piclist">
		<ul class="listul">

		</ul>
		<div class="fydiv">
			<ul class="fenye">

			</ul>
		</div>
	</div>

</body>
</html>
<script>
	$(".option-sex label").click(function() {
		$(".option-sex label").removeClass("active");
		$(this).addClass("active");
	});
	$(".option-area label").click(function() {
		$(".option-area label").removeClass("active");
		$(this).addClass("active");
	});
</script>