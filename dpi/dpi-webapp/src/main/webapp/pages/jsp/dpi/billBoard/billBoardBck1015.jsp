<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.bonc.com.cn/common/tag/cxt" prefix="cxt"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<cxt:commonLinks />
	<title>热搜风云榜</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/pages/jsp/dpi/billBoard/swiper.min.css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/billBoard.css" />
	<!-- <link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/operateCenter.css" /> -->
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/js/echarts4.min.js"></script>
	<script src="<%=request.getContextPath()%>/pages/jsp/dpi/capabilityPreview/Convert_Pinyin.js"></script>
	<script src="<%=request.getContextPath()%>/pages/jsp/dpi/billBoard/swiper.min.js"></script>
	<style>
	.tab-content{
		position:relative;
	}
	.whole{
		position:absolute;
		right:10px;
		bottom:0;
	}
	#label_list{
		position:relative;
	}
	#more_label{
		position:absolute;
		right:5px;
		bottom:10px;
	}
	.my_group{
		margin-left: 10px;
		margin-top: 10px;
	}

</style>
</head>
<body>
<form action="" class="form-inline">
    <div class="form-group my_group">
        <label>账期：</label>
        <input type="text" class="form-control datepicker-input" placeholder="不限" id="time" style="width:150px;">
    </div>
    <div class="form-group my_group">
        <label>地区：</label>
        <select name=""  class="chosen-select"  id="cityId" style="width: 150px;"></select>
    </div>
</form>

<!-- 地图 -->
<div class="bill-top" >
	<div class="bill-top-left"  >
		<ul id="myTabspot" class="nav nav-tabs">
			<li class="active">
				<a href="#hotspot" data-toggle="tab"  style="color:#333366;font-weight:700;margin-left:5px;: none;border-bottom:none;">
					实时热点
				</a>
			</li>
			<li>
				<a href="#concern" data-toggle="tab"></a>
			</li>
		</ul>
		<div id="myTabspotContent" class="tab-content tab-content-top" >
			<div class="tab-pane fade in active" id="hotspot" style="overflow: hidden">
			</div>
			<div class="tab-pane fade" id="concern">
				第二个页面
			</div>
		</div>
		<div class="allList" id="more_hot_data">
			完整榜单&emsp;
		</div>
	</div>
	<div class="bill-top-middle" id="main">
		<ul class="nav nav-tabs" style="height:5%;">
			<li class="active">
				<a href="#hotspot" data-toggle="tab"  style="color:#333366;font-weight:700;margin-left:5px;: none;border-bottom:none;">
					热搜风向标
				</a>

			</li>
		</ul>
		<div id="map" class="bill-top-middle-map"></div>
	</div>
	<div class="bill-top-right" id="label_list">
		<ul class="nav nav-tabs">
			<li class="active">
				<a href="#hotspot" data-toggle="tab"  style="color:#333366;font-weight:700;margin-left:5px;: none;border-bottom:none;">
					热搜分类
				</a>

			</li>
		</ul>
		<div id="data"></div>
		<div class="allList" id="more_label">
			完整榜单&emsp;
		</div>
	</div>
</div>
<!-- 模块 -->
<div class="bill-bottom clearfix" id="bill">
	<div class="bill-list">
		<div class="list-title">
			<img src="<%=request.getContextPath()%>/pages/images/new/19.png" />生活 <span>更多</span>
		</div>
		<div class="swiper1 swiper-container clearfix">
			<ul id="myTab9" class="nav nav-tabs swiper-wrapper clearfix">
				<li class="active swiper-slide">
				<a href="#mt9-1" data-toggle="tab">
					旅游城市
				</a>
			</li>
			<li class="swiper-slide">
				<a href="#mt9-2" data-toggle="tab">博物馆</a>
			</li>
			<li class="swiper-slide">
				<a href="#mt9-3" data-toggle="tab">宠物</a>
			</li>
			<li class="swiper-slide">
				<a href="#mt9-4" data-toggle="tab">小吃</a>
			</li>
			</ul>
		</div>
		<div id="myTab9Content" class="tab-content">
			<div class="tab-pane fade in active" id="mt9-1">
			</div>
			<div class="tab-pane fade" id="mt9-2">
				第二个页面
			</div>
			<div class="tab-pane fade" id="mt9-3">
				第三个页面
			</div>
			<div class="tab-pane fade" id="mt9-4">
				第四个页面
			</div>
		</div>
	</div>
</div>
</body>
</html>
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/billBoard/billBoard.js"></script>