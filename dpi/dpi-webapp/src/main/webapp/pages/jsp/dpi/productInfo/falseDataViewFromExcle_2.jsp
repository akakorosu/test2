<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cxt" uri="http://www.bonc.com.cn/common/tag/cxt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<link rel="stylesheet" href="css/bootstrap.css" />
<!-- 表单div -->
<div id="falseDataList">
	<ul id="myTab" class="nav nav-tabs">
		<c:if test="${fn:length(invalidDataInExcle_repeatPrimaryKey_domainCode)>0}">
			<li class="active"><a href="#tab1" data-toggle="tab">${tab_name_invalidDataInExcle_repeatPrimaryKey}</a></li>
		</c:if>
		<c:if test="${fn:length(invalidDataInExcle_empty)>0}">
			<li><a href="#tab2" data-toggle="tab">${tab_name_invalidDataInExcle_empty}</a></li>
		</c:if>
		<c:if test="${fn:length(invalidDataInExcle_tooLong)>0}">
			<li><a href="#tab3" data-toggle="tab">${tab_name_invalidDataInExcle_tooLong}</a></li>
		</c:if>		
		<c:if test="${fn:length(invalidDataInExcle_notTrue_prodLabelCode)>0}">
			<li><a href="#tab4" data-toggle="tab">${tab_name_invalidDataInExcle_notTrue_prodLabelCode}</a></li>
		</c:if>
		<c:if test="${fn:length(list_false_cache_productInfo)>0}">
<%-- 			<li><a href="#tab5" data-toggle="tab">${tab_name_list_false_cache}</a></li> --%>
			<li><a href="#tab5" data-toggle="tab">文件中产品名与产品表重复</a></li>
		</c:if>
		<c:if test="${fn:length(list_false_cache_domainRule)>0}">
<%-- 			<li><a href="#tab6" data-toggle="tab">${tab_name_list_false_cache}</a></li> --%>
<%-- 			只对域名表和IP表增加了更新和舍弃 --%>
			<li><a href="#tab6" data-toggle="tab">文件中域名与域名表重复</a></li>  
		</c:if>
		<c:if test="${fn:length(list_false_cache_ipPort)>0}">
			<li><a href="#tab7" data-toggle="tab">文件中ip与ip表重复</a></li>
		</c:if>				
	</ul>	
	
	<div id="myTabContent" class="tab-content">
		<div class="tab-pane fade in active" id="tab1">
			<p id="p1" style="color: red;font-size: 20px;" align="center">${info_invalidDataInExcle_repeatPrimaryKey} : ${fn:length(invalidDataInExcle_repeatPrimaryKey_domainCode)}条</p>
		    <table style="width: 850px; overflow-x:hidden; " class="table table-striped table-hover table-bordered">
		        <thead>
		            <tr><th title="excle行号" style="color: red;width: 90px;">excle行号</th><th title="主键">域名</th><th>产品名称</th><th>标签编码</th></tr>
		        </thead>
		        <tbody>
		        	<c:forEach items="${requestScope.invalidDataInExcle_repeatPrimaryKey_domainCode}" var="vo" varStatus="id"><tr><td>${vo.rowNum+1}</td><td style="color: red;">${vo.domainCode}</td><td>${vo.prodName}</td><td>${vo.prodLabelCode}</td></tr></c:forEach>
				</tbody>
		    </table>
	   	</div>
	   	
		<div class="tab-pane fade" id="tab2">
			<p id="p2" style="color: red;font-size: 20px;" align="center">${info_invalidDataInExcle_empty} : ${fn:length(invalidDataInExcle_empty)}条</p>
		    <table style="width: 850px; overflow-x:hidden; " class="table table-striped table-hover table-bordered">
		        <thead>
		            <tr><th title="excle行号" style="color: red;width: 90px;">excle行号</th><th title="主键">域名</th><th>产品名称</th><th>标签编码</th></tr>
		        </thead>
		        <tbody>
		        	<c:forEach items="${requestScope.invalidDataInExcle_empty}" var="vo" varStatus="id"><tr><td>${vo.rowNum+1}</td><td>${vo.domainCode}</td><td>${vo.prodName}</td><td>${vo.prodLabelCode}</td></tr></c:forEach>
				</tbody>
		    </table>
		</div>
		
		<div class="tab-pane fade" id="tab3">
			<p id="p3" style="color: red;font-size: 20px;" align="center">${info_invalidDataInExcle_tooLong} : ${fn:length(invalidDataInExcle_tooLong)}条</p>
		    <table style="width: 850px; overflow-x:hidden; " class="table table-striped table-hover table-bordered">
		        <thead>
		            <tr><th title="excle行号" style="color: red;width: 90px;">excle行号</th><th title="主键">域名</th><th>产品名称</th><th>标签编码</th></tr>
		        </thead>
		        <tbody>
		        	<c:forEach items="${requestScope.invalidDataInExcle_tooLong}" var="vo" varStatus="id"><tr><td>${vo.rowNum+1}</td><td>${vo.domainCode}</td><td>${vo.prodName}</td><td>${vo.prodLabelCode}</td></tr></c:forEach>
				</tbody>
		    </table>
		</div>
		
		<div class="tab-pane fade" id="tab4">
			<p id="p4" style="color: red;font-size: 20px;" align="center">${info_invalidDataInExcle_notTrue_prodLabelCode} : ${fn:length(invalidDataInExcle_notTrue_prodLabelCode)}条</p>
		    <table style="width: 850px; overflow-x:hidden; " class="table table-striped table-hover table-bordered">
		        <thead>
		            <tr><th title="excle行号" style="color: red;width: 90px;">excle行号</th><th title="主键">域名</th><th>产品名称</th><th>标签编码</th></tr>
		        </thead>
		        <tbody>
		        	<c:forEach items="${requestScope.invalidDataInExcle_notTrue_prodLabelCode}" var="vo" varStatus="id"><tr><td>${vo.rowNum+1}</td><td>${vo.domainCode}</td><td>${vo.prodName}</td><td>${vo.prodLabelCode}</td></tr></c:forEach>
				</tbody>
		    </table>
		</div>		
		
		<div class="tab-pane fade" id="tab5">
			<p id="p5-1" style="color: red;font-size: 20px;" align="center">${info_list_false_cache} : ${fn:length(list_false_cache_productInfo)}条</p>
		    <table style="width: 850px; overflow-x:hidden; " class="table table-striped table-hover table-bordered">
		        <thead>
		            <tr><th title="excle行号" style="color: red;width: 90px;">excle行号</th><th>产品id</th><th>产品名称</th></tr>
		        </thead>
		        <tbody>
		        	<c:forEach items="${requestScope.list_false_cache_productInfo}" var="vo" varStatus="id"><tr><td>${vo.rowNum+1}</td><td>${vo.prodId}</td><td>${vo.prodName}</td></tr></c:forEach>
				</tbody>
		    </table>
		</div>
		
		<div class="tab-pane fade" id="tab6">
			<p id="p6-1" style="color: red;font-size: 20px;" align="center">${info_list_false_cache} : ${fn:length(list_false_cache_domainRule)}条</p>
		    <table style="width: 850px; overflow-x:hidden; " class="table table-striped table-hover table-bordered">
			     <tr align="center">
			     	<td>
			     		<button style="width: 300px;" class="btn btn-warning"  onclick="falseDataFromExcleUpdate('1','/productInfo/falseDataFromExcleUpdateDomain')">舍&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;弃</button>
			     		<button style="width: 300px;" class="btn btn-success"  onclick="falseDataFromExcleUpdate('2','/productInfo/falseDataFromExcleUpdateDomain')">更&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;新</button>
					</td>
			     </tr>	
		     </table>
		    <table style="width: 850px; overflow-x:hidden; " class="table table-striped table-hover table-bordered">
		        <thead>
		            <tr><th title="excle行号" style="color: red;width: 90px;">excle行号</th><th>域名</th><th>产品id</th><th>产品名称</th></tr>
		        </thead>
		        <tbody>
		        	<c:forEach items="${requestScope.list_false_cache_domainRule}" var="vo" varStatus="id"><tr><td>${vo.rowNum+1}</td><td>${vo.domainCode}</td><td>${vo.prodId}</td><td>${vo.prodName}</td></tr></c:forEach>
				</tbody>
		    </table>
		</div>

		<div class="tab-pane fade" id="tab7">
			<p id="p6-1" style="color: red;font-size: 20px;" align="center">${info_list_false_cache} : ${fn:length(list_false_cache_ipPort)}条</p>
			<table style="width: 850px; overflow-x:hidden; " class="table table-striped table-hover table-bordered">
			     <tr align="center">
			     	<td>
			     		<button style="width: 300px;" class="btn btn-warning"  onclick="falseDataFromExcleUpdate('1','/productInfo/falseDataFromExcleUpdateIp')">舍&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;弃</button>
			     		<button style="width: 300px;" class="btn btn-success"  onclick="falseDataFromExcleUpdate('2','/productInfo/falseDataFromExcleUpdateIp')">更&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;新</button>
					</td>
			     </tr>	
		     </table>
		    <table style="width: 850px; overflow-x:hidden; " class="table table-striped table-hover table-bordered">
		        <thead>
		            <tr><th title="excle行号" style="color: red;width: 90px;">excle行号</th><th>ip端口</th><th>产品id</th><th>产品名称</th></tr>
		        </thead>
		        <tbody>
		        	<c:forEach items="${requestScope.list_false_cache_ipPort}" var="vo" varStatus="id"><tr><td>${vo.rowNum+1}</td><td>${vo.ipPort}</td><td>${vo.prodid}</td><td>${vo.prodName}</td></tr></c:forEach>
				</tbody>
		    </table>
		</div>
										
	</div>
 </div>

<script>

	$(function(){
		//默认点击第一个tab页
		var aFirst = $("#myTab a").first();
		aFirst.click();
	})

</script>