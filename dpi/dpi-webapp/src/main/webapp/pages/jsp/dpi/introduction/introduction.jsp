<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.bonc.com.cn/common/tag/cxt" prefix="cxt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<cxt:commonLinks />
<title>人物详情</title>
<script src="<%=request.getContextPath()%>/pages/js/jquery-3.1.1.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/bootstrap-table.min.css" />
<script src="<%=request.getContextPath()%>/pages/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/pages/js/bootstrap-table.min.js"></script>
<script src="<%=request.getContextPath()%>/pages/js/bootstrap-table-zh-CN.min.js"></script>
<link rel="stylesheet" href="introduction.css" />
<script src="introduction.js"></script>
</head>
<body>
   <div class="person">
			<img src="<%=request.getContextPath()%>/pages/images/new/li.jpg" />
			<div class="personName">周星驰&emsp;<span>Stephen Chow</span></div>
			<div class="col">
				<div class="col-xs-4">
					<p>别&emsp;&emsp;名&emsp;星爷、星仔、阿星</p>
					<p>国&emsp;&emsp;籍&emsp;中国</p>
					<p>民&emsp;&emsp;族&emsp;汉族</p>
					<p>星&emsp;&emsp;座&emsp;巨蟹</p>
				</div>
				<div class="col-xs-4">
					<p>血&emsp;&emsp;型&emsp;O型</p>
					<p>身&emsp;&emsp;高&emsp;174cm</p>
					<p>体&emsp;&emsp;重&emsp;70kg</p>
					<p>出&nbsp;&nbsp;生&nbsp;&nbsp;地&emsp;香港</p>
				</div>
				<div class="col-xs-4">
					<p>出生日期&emsp;1962年6月22日</p>
					<p>生&emsp;&emsp;肖&emsp;虎</p>
					<p>籍&emsp;&emsp;贯&emsp;浙江宁波</p>
					<p>职&emsp;&emsp;业&emsp;演员、导演</p>
				</div>
			</div>
		</div>
		<div class="catalog">
			<div class="tableBox">
				<table id="tb_departments"></table>
			</div>
		</div>
</body>
</html>