<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cxt" uri="http://www.bonc.com.cn/common/tag/cxt"%>
<!-- 编辑表单div -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/commonTwo.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/jsp/dpi/capabilityPreview/echarts.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/jsp/dpi/capabilityPreview/capabilityViewLabel.js"></script>


<style type="text/css">
      .modal-body{
         padding:0 10px;
         position:relative;
         background:#fff;
      }
      .modal-bottom{
         background:#fff;
      }
      #piForm{
          margin-top:50px;
          overflow-y:scroll;
      } 
      .product-search{
          position:fixed;
          top:38px;
          left:0;
          padding:0 3%;
          z-index:99;
          background:#fff;
          height:40px;
          padding-top:12px;
      }
      #piForm span{
          display:inline-block;
          width:auto;
          padding:0 15px;
          border-radius:14px;
          height:28px;
          line-height:28px;
          background:#f1f1f1;
          color:#8084A4;
          font-size:13px;
          margin-right:10px;
          margin-bottom:10px;
      }
      #piForm span.active{
          background:rgba(16, 142, 233, 1);
          color:#fff;
      }
      .tree-container {
             border: 1px solid grey;
              position: relative;
        }
        .left-hover-menu {
            position: absolute;
            border: 1px solid grey;
            left: -99999px;
            top: -999999px;
        }
        .right-click-menu {
              position: absolute;
              border: 1px solid blue;
              left: -99999px;
              top: -999999px;
         }
</style>
<!-- <div class="product-search">
		<span>行业标签</span>
		<span class="result resultTwo">共检索到<span id="prodLabelTwoNum"></span>个相关产品<button id="prevBtn">上一个</button><button id="nextBtn">下一个</button></span>
		<button class="product-btn" onclick="searchTwo();">查询</button>
		<input type="text" id="detail" placeholder="产品名称" />
	</div> -->
	<!--  <button class="product-btn" onclick="update();">变变</button> -->
<div id="piForm11" style="overflow: hidden;">	
     
     <div class="tree-container">
      <div id="main11" style="width: 1200px;height:1000px;"></div>
  </div>


<%-- <div class="professionBox11" id="professionBox11" style="overflow: hidden;">
		<div class="column">
			<div class="col-title">生活类</div>
			<ul>
				<li class="level"><img src="<%=request.getContextPath()%>/pages/images/new/u2185.png" />交通出行</li>
				<li style="cursor:pointer;">客车</li>
				<li>火车</li>
				<li>飞机</li>
				<li>出租车</li>
				<li>公交地铁</li>
				<li>共享单车</li>
				<li>地图导航</li>
			</ul>
			<ul>
				<li class="level"><img src="<%=request.getContextPath()%>/pages/images/new/u2185.png" />餐饮</li>
				<li>外卖</li>
				<li>美食</li>
			</ul>
			<ul>
				<li class="level"><img src="<%=request.getContextPath()%>/pages/images/new/u2185.png" />住宿</li>
				<li>期房</li>
				<li>二手房</li>
			</ul>
			<ul>
				<li class="level"><img src="<%=request.getContextPath()%>/pages/images/new/u2185.png" />家装</li>
				<li>家居</li>
				<li>家电</li>
			</ul>
		</div>
   
	</div>
 --%>
   
	
</div>





