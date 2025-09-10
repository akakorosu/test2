<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cxt" uri="http://www.bonc.com.cn/common/tag/cxt"%>
<style>
a {margin-bottom: 5px;color: #333333;}
#productLabelTree {padding: 0px 50px 0px 50px;font-size: 12px;}
#productLabelTree_div1 {margin-bottom: 10px;position:relative;}
#productLabelTree_div1 > input {width: 100%;border: 0px;height: 40px;padding: 10px;border-bottom:1px solid #ccc;background:#f9f9f9;margin-bottom:5px;padding-left:30px;}
#productLabelTree_div1 > div {position: absolute;top:39px;border:1px solid #ccc;background-color: white;width: 200px;max-height: 400px;overflow: auto;display:none; padding: 10px;}
#productLabelTree_div1 > div > div {margin-bottom: 5px;}
#productLabelTree_div1 > p > strong{margin-right:10px;color:#777;} 
#productLabelTree_div1 > p > span{margin-right:10px;color:#999;} 
#productLabelTree_div2 {float: left;}
#productLabelTree_div2 a {margin-right: 20px;display: block;float: left;}
#productLabelTree_div21 {float: left;}
#productLabelTree_div22 {float: left;}
#productLabelTree_div23 {float: left;}
#productLabelTree_div3 {float: right;width: 150px;padding-left:50px;border-left:1px solid #ccc;}
#productLabelTree_div3 a {display: block;}
#productLabelTree_div2 > div > div > div:first-child, 
	#productLabelTree_div3_all,
	#divFastSearch span:first-child {font-size: 14px;font-weight: bold;margin-bottom: 10px;}
.inputFastSearchIcon{position:absolute;top:12px;left:10px;color:#ccc;}
/* #divFastSearch > div >span {font-size: 14px !important;font-weight: normal !important; } */
</style>
<!-- 编辑表单div -->
<div id="productLabelTree">
	<div id="productLabelTree_div1">
		<input placeholder="快速搜索" id="inputFastSearch">
		<i class="fa fa-search inputFastSearchIcon"></i>
		<div id="divFastSearch">
		</div>
	</div>	
	<div id="productLabelTree_div2">
		<div id="productLabelTree_div21"></div>
		<div id="productLabelTree_div22"></div>
		<div id="productLabelTree_div23"></div>
	</div>
	<div id="productLabelTree_div3">
		<div id="productLabelTree_div3_all">
			<a href="#" onclick="showLablelByLabelCode()">全部</a>
		</div>
		<div id="productLabelTree_div3_head"></div>
	</div>
</div>
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/productLabel/productLabelTree.js"></script>
