<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cxt" uri="http://www.bonc.com.cn/common/tag/cxt"%>
<!-- 编辑表单div -->

<link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/commonTwo.css" />
<script
	src="<%=request.getContextPath()%>/pages/jsp/dpi/capabilityPreview/capabilityViewProd.js"></script>
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
          top:52px;
          right:20px;
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
</style>
<div class="product-search pull-right">
		<!-- <span>行业标签</span> -->
		<span class="result resultTwo">共检索到<span id="prodLabelTwoNum"></span>个相关产品<button id="prevBtn">上一个</button><button id="nextBtn">下一个</button></span>
		<button class="product-btn" onclick="searchTwo();">查询</button>
		<input type="text" id="detail" placeholder="产品名称" />
	</div>
<div id="piForm" style="overflow: hidden;">	



   <c:forEach var="vo" items="${list}">

		<%-- <div class="clearfix formGroup">
			<label class="col-sm-1 labelForm">表名：</label>
			<div class="col-sm-3">
				<input value="${vo.tableName }" type="text" readonly="readonly" title="${vo.tableNameEn }"/>
			</div>
			<label class="col-sm-1 labelForm">域名：</label>
			<div class="col-sm-3">
				<input value="${vo.host }" type="text" readonly="readonly" />
			</div>
			<label class="col-sm-1 labelForm">规则：</label>
			<div class="col-sm-3">
				<input value="${vo.regRule }" type="text" readonly="readonly" />
			</div>
		</div> --%>
		<span>${vo.prodName }</span>
	</c:forEach>
	<!-- <span>长春到哈尔滨</span> -->
	
</div>




