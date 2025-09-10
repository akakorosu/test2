<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cxt" uri="http://www.bonc.com.cn/common/tag/cxt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<link rel="stylesheet" href="css/bootstrap.css" />
<!-- 表单div -->
<div id="falseDataList">
	<ul id="myTab" class="nav nav-tabs">
		<c:if test="${fn:length(invalidDataInExcle_repeatPrimaryKey)>0}">
			<li class="active"><a href="#tab1" data-toggle="tab">${tab_name_invalidDataInExcle_repeatPrimaryKey}</a></li>
		</c:if>
		<c:if test="${fn:length(invalidDataInExcle_empty)>0}">
			<li><a href="#tab2" data-toggle="tab">${tab_name_invalidDataInExcle_empty}</a></li>
		</c:if>
		<c:if test="${fn:length(invalidDataInExcle_tooLong)>0}">
			<li><a href="#tab3" data-toggle="tab">${tab_name_invalidDataInExcle_tooLong}</a></li>
		</c:if>		
		<c:if test="${fn:length(invalidDataInExcle_notTrue_prodId)>0}">
			<li><a href="#tab4" data-toggle="tab">${tab_name_invalidDataInExcle_notTrue_prodId}</a></li>
		</c:if>
		<c:if test="${fn:length(list_false_cache)>0}">
			<li><a href="#tab5" data-toggle="tab">${tab_name_list_false_cache}</a></li>
		</c:if>		
	</ul>	
	
	<div id="myTabContent" class="tab-content">
		<div class="tab-pane fade in active" id="tab1">
			<p id="p1" style="color: red;font-size: 20px;" align="center">${info_invalidDataInExcle_repeatPrimaryKey} : ${fn:length(invalidDataInExcle_repeatPrimaryKey)}条</p>
		    <table style="width: 850px; overflow-x:hidden; " class="table table-striped table-hover table-bordered">
		        <thead>
		            <tr><th title="excle行号" style="color: red;width: 90px;">excle行号</th><th title="主键">截取规则</th><th>产品ID</th></tr>
		        </thead>
		        <tbody>
		        	<c:forEach items="${requestScope.invalidDataInExcle_repeatPrimaryKey}" var="vo" varStatus="id"><tr><td>${vo.rowNum+1}</td><td>${vo.parseRule}</td><td>${vo.prodid}</td></tr></c:forEach>
				</tbody>
		    </table>
	   	</div>
	   	
		<div class="tab-pane fade" id="tab2">
			<p id="p2" style="color: red;font-size: 20px;" align="center">${info_invalidDataInExcle_empty} : ${fn:length(invalidDataInExcle_empty)}条</p>
		    <table style="width: 850px; overflow-x:hidden; " class="table table-striped table-hover table-bordered">
		        <thead>
		            <tr><th title="excle行号" style="color: red;width: 90px;">excle行号</th><th title="联合主键">截取规则</th><th>产品ID</th></tr>
		        </thead>
		        <tbody>
		        	<c:forEach items="${requestScope.invalidDataInExcle_empty}" var="vo" varStatus="id"><tr><td>${vo.rowNum+1}</td><td>${vo.parseRule}</td><td>${vo.prodid}</td></tr></c:forEach>
				</tbody>
		    </table>
		</div>
		
		<div class="tab-pane fade" id="tab3">
			<p id="p3" style="color: red;font-size: 20px;" align="center">${info_invalidDataInExcle_tooLong} : ${fn:length(invalidDataInExcle_tooLong)}条</p>
		    <table style="width: 850px; overflow-x:hidden; " class="table table-striped table-hover table-bordered">
		        <thead>
		            <tr><th title="excle行号" style="color: red;width: 90px;">excle行号</th><th title="联合主键">截取规则</th><th>产品ID</th></tr>
		        </thead>
		        <tbody>
		        	<c:forEach items="${requestScope.invalidDataInExcle_tooLong}" var="vo" varStatus="id"><tr><td>${vo.rowNum+1}</td><td>${vo.parseRule}</td><td>${vo.prodid}</td></tr></c:forEach>
				</tbody>
		    </table>
		</div>
		
		<div class="tab-pane fade" id="tab4">
			<p id="p4" style="color: red;font-size: 20px;" align="center">${info_invalidDataInExcle_notTrue_prodId} : ${fn:length(invalidDataInExcle_notTrue_prodId)}条</p>
		    <table style="width: 850px; overflow-x:hidden; " class="table table-striped table-hover table-bordered">
		        <thead>
		            <tr><th title="excle行号" style="color: red;width: 90px;">excle行号</th><th title="联合主键">截取规则</th><th>产品ID</th></tr>
		        </thead>
		        <tbody>
		        	<c:forEach items="${requestScope.invalidDataInExcle_notTrue_prodId}" var="vo" varStatus="id"><tr><td>${vo.rowNum+1}</td><td>${vo.parseRule}</td><td>${vo.prodid}</td></tr></c:forEach>
				</tbody>
		    </table>
		</div>		
		
		<div class="tab-pane fade" id="tab5">
			<p id="p5-0" style="color: red;font-size: 12px;">温馨提示：舍弃：excle中的重复数据不导入,更新：excle中的重复数据导入</p>
			<p id="p5-1" style="color: red;font-size: 20px;" align="center">${info_list_false_cache} : ${fn:length(list_false_cache)}条</p>
			<table style="width: 850px; overflow-x:hidden; " class="table table-striped table-hover table-bordered">
			     <tr align="center">
			     	<td>
			     		<button style="width: 300px;" class="btn btn-warning"  onclick="falseDataFromExcleUpdate('1','/domainFuzzyRule/falseDataFromExcleUpdate')">舍&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;弃</button>
			     		<button style="width: 300px;" class="btn btn-success"  onclick="falseDataFromExcleUpdate('2','/domainFuzzyRule/falseDataFromExcleUpdate')">更&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;新</button>
					</td>
			     </tr>	
		     </table>
		
		    <table style="width: 850px; overflow-x:hidden; " class="table table-striped table-hover table-bordered">
		        <thead>
		            <tr><th title="excle行号" style="color: red;width: 90px;">excle行号</th><th title="联合主键">截取规则</th><th>产品ID</th></tr>
		        </thead>
		        <tbody>
		        	<c:forEach items="${requestScope.list_false_cache}" var="vo" varStatus="id"><tr><td>${vo.rowNum+1}</td><td>${vo.parseRule}</td><td>${vo.prodid}</td></tr></c:forEach>
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