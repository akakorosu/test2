<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.bonc.com.cn/common/tag/cxt" prefix="cxt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%-- <cxt:commonLinks/> --%>
    <title>内容库标签</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/capabilityPreviewModal.css">
    <script
	src="<%=request.getContextPath()%>/pages/jsp/dpi/capabilityPreview/capabilityPreviewModal.js"></script>
	 <script>
            $(function(){
                $(".slide-nav-left li").click(function(){
                    $(this).siblings().removeClass("active");
                    $(this).addClass("active");
                    /* var aaaa= $(this).html().split("/");
                    if(labelFlag=='AN00'){
                    	
                		printRigth(aaaa[0],aaaa[1],shiPin);
                		
                	}
                    if(labelFlag=='BT00'){
                    	
                		printRigth(aaaa[0],aaaa[1],dianShang);
                		
                	}
                    if(labelFlag=='BT00'){
	
	                    printRigth(aaaa[0],aaaa[1],xiaoShuo);
	
                     } */
                    
                })
            })
        </script>
        <!-- <style>
	       	.product-search .result1{
				display:inline-block;
				color:#A7A9C0;
				font-size:14px;
				margin-left:15px;
				display:none;
			}
			.product-search .result1 button{
				width:73px;
				height:28px;
				line-height:28px;
				border:none;
				border-radius:4px;
				outline:none;
				margin-left:8px;
				font-size:14px;
				color:#fff;
				background:rgba(167, 169, 192, 1);
			}
        
        
        </style> -->
</head>
<body style="background: #f4f3f3;padding:10px">
    <!-- <div class="view-title">内容库标签
        <button class="btn btn-white pull-right" id="goBack">返回</button>
    </div> -->
    <div class="product-search pull-right">
            <span>行业标签</span>
            <span class="result" id="result11">共检索到<span id="searchLabelNum2"></span>个相关标签<button id="changePrev1" onclick="changePrev()">上一个</button><button
            id="changeNext1" onclick="changeNext()">下一个</button></span>
            <button class="product-btn" onclick="search1();">查询标签</button>
            <!-- <button class="product-btn" onclick="searchProd();">查询产品</button> -->
            <input type="text" id="profession1" placeholder="标签"/>
           
        </div>
    <div class="view-main-content clearfix">
        <ul class="slide-nav-left pull-left" id="leftBar">
            <!-- <li>家用家电</li>
            <li>男装/女装/童装/内衣</li> -->
        </ul>
       
        <div class="detail-content">
            <%--蓝色标签--%>
            <!-- <ul class="content-overview">
                <li class="pull-left">玩3C<span>></span></li>
                <li class="pull-left">影像Club<span>></span></li>
                <li class="pull-left">手机频道<span>></span></li>
                <li class="pull-left">网上营业厅<span>></span></li>
                <li class="pull-left">配件选购中心<span>></span></li>
            </ul> -->
            <%--标签列--%>
            <ul class="content-list" id="rigthBar">
               <!--  <li class="clearfix">
                    <b class="main pull-left">手机通讯</b><span class="right-icon pull-left">></span>
                    <div class="clearfix">
                        <span class="pull-left">/  手机</span><span class="pull-left">/  游戏手机</span><span class="pull-left">/  老人机</span><span class="pull-left">/  对讲机</span><span class="pull-left">/  手机</span><span class="pull-left">/  手机</span>
                    </div>
                </li>
                <li class="clearfix">
                    <b class="main pull-left">运营商</b><span class="right-icon pull-left">></span>
                    <div class="clearfix">
                        <span class="pull-left">/  手机</span><span class="pull-left">/  手机</span><span class="pull-left">/  手机</span><span class="pull-left">/  手机</span><span class="pull-left">/  手机</span><span class="pull-left">/  手机</span>
                    </div>
                </li>
                <li class="clearfix">
                    <b class="main pull-left">手机通讯</b><span class="right-icon pull-left">></span>
                    <div class="clearfix">
                        <span class="pull-left">/  手机</span><span class="pull-left">/  手机</span><span class="pull-left">/  手机</span><span class="pull-left">/  手机</span><span class="pull-left">/  手机</span><span class="pull-left">/  手机</span>
                    </div>
                </li>
                <li class="clearfix">
                    <b class="main pull-left">手机通讯</b><span class="right-icon pull-left">></span>
                    <div class="clearfix">
                        <span class="pull-left">/  手机</span><span class="pull-left">/  手机</span><span class="pull-left">/  手机</span><span class="pull-left">/  手机</span><span class="pull-left">/  手机</span><span class="pull-left">/  手机</span>
                    </div>
                </li>
                <li class="clearfix">
                    <b class="main pull-left">手机通讯</b><span class="right-icon pull-left">></span>
                    <div class="clearfix">
                        <span class="pull-left">/  手机</span><span class="pull-left">/  手机</span><span class="pull-left">/  手机</span><span class="pull-left">/  手机</span><span class="pull-left">/  手机</span><span class="pull-left">/  手机</span>
                    </div>
                </li> -->
            </ul>
        </div>
    </div>
</body>
</html>
