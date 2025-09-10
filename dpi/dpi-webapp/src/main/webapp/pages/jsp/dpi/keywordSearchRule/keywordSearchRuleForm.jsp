<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cxt" uri="http://www.bonc.com.cn/common/tag/cxt"%>

<!-- 编辑表单div -->
<div id="ksrForm">
	<input type="hidden" class="formField" id="id" name="id" value="${vo.id}">
	<input type="hidden" class="formField" name="author" value="${vo.author}">
	<input type="hidden" class="formField" name="opTime" value="${vo.opTime}">
	<input type="hidden" class="formField" name="isValid" value="${vo.isValid}">
	<div class="clearfix formGroup">
<!-- 	<p style="color: red">提示：域名、产品ID、安卓截取规则、苹果截取规则这四个值组合的联合主键不能重复；安卓截取规则、苹果截取规则这两个值不可以同时为空</p> -->
	<p style="color: red">提示：域名、产品ID、安卓截取规则这三个值组合的联合主键不能重复</p>
	</div>
	
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">域名：</label> 
		<div class="col-sm-4">
			<input value="${vo.host}" type="text" placeholder="域名" onlyUrl="/keywordSearchRule/checkRepeat,id,hostParam,prodid,parseRuleAndriod|联合主键重复" class="formField" id="host" name="hostParam" />
		</div>
		<label class="col-sm-2 labelForm">匹配类型：</label> 
		<div class="col-sm-4">		
			<cxt:CodeSelectTlb id="matchType" name="matchType" codeType="matchType" value="${vo.matchType}" defaultyn="n" cssClass="formField"></cxt:CodeSelectTlb>
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">产品ID：</label>
		<div class="col-sm-4">
			<input value="${vo.prodid }" type="text" <c:if test="${!empty vo.id}">readonly="readonly"</c:if> placeholder="产品Id"  onlyUrl="/keywordSearchRule/checkRepeat,id,hostParam,prodid,parseRuleAndriod|联合主键重复" class="formField required" id="prodid" name="prodid" >
			<c:if test="${empty vo.id}">
				<button class="btn btn-warning" id="checkParseRuleAndriod" onclick="getProdIdList('','','prodId')">查询</button>
			</c:if>
		</div>
		<label class="col-sm-2 labelForm">产品名称：</label> 
		<div class="col-sm-4">
			<input value="${vo.prodName }" type="text" <c:if test="${!empty vo.id}">readonly="readonly"</c:if> placeholder="产品名称"  class="formField required" id="prodNameNew" name="prodName" >
			<c:if test="${empty vo.id}">
				<button class="btn btn-warning" id="checkParseRuleAndriod" onclick="getProdIdList('','','prodName')">查询</button>
			</c:if>
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">动作目录名称：</label>
		<div class="col-sm-4">
			<input value="${vo.channelName }" type="text" placeholder="动作目录名称" class="formField required " id="channelName" name="channelName">
		</div>		
		<label class="col-sm-2 labelForm">规则匹配类型：</label> 
		<div class="col-sm-4">
			<cxt:CodeSelectTlb name="ruleType" codeType="ruleType" value="${vo.ruleType }" defaultyn="n" cssClass="formField"></cxt:CodeSelectTlb>
<%-- 			<input value="${vo.ruleType }" type="text" placeholder="规则匹配类型" class="formField required " id="ruleType" name="ruleType"> --%>
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">安卓截取规则：</label>
		<div class="col-sm-4">
			<input value="${vo.parseRuleAndriod }" type="text" placeholder="安卓截取规则" onlyUrl="/keywordSearchRule/checkRepeat,id,hostParam,prodid,parseRuleAndriod|联合主键重复"  class="formField required" id="parseRuleAndriod" name="parseRuleAndriod">
			<button class="btn btn-success" id="checkParseRuleAndriod" onclick="checkRule('1')">校验</button>
		</div>	
		<label class="col-sm-2 labelForm">安卓截取序号：</label> 
		<div class="col-sm-4">
			<input value="${vo.getIndexAndriod }" type="text" placeholder="安卓截取序号" class="formField" id="getIndexAndriod" name="getIndexAndriod">
		</div>
	</div>	
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">苹果截取规则：</label> 
		<div class="col-sm-4">
			<input value="${vo.parseRuleIos }" type="text" placeholder="苹果截取规则"  class="formField " id="parseRuleIos" name="parseRuleIos">
			<button class="btn btn-success" id="checkParseRuleIos" onclick="checkRule('2')">校验</button>
		</div>
		<label class="col-sm-2 labelForm">苹果截取序号：</label>
		<div class="col-sm-4">
			<input value="${vo.getIndexIos }" type="text" placeholder="苹果截取序号" class="formField" id="getIndexIos" name="getIndexIos">
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">应用分组编码：</label> 
		<div class="col-sm-4">
			<input value="${vo.groupType }" type="text"  placeholder="应用分组编码" onlyUrl="/keywordSearchRule/checkGroupType,groupType|应用分组编码不存在" class="formField required "  id="groupType" name="groupType">
			<button class="btn btn-warning" id="checkParseRuleAndriod" onclick="getGroupTypeList('','','groupType')">查询</button>
		</div>
		<label class="col-sm-2 labelForm">应用分组名称：</label>
		<div class="col-sm-4">
			<input value="${vo.groupName }" type="text" placeholder="应用分组名称" onlyUrl="/keywordSearchRule/checkGroupName,groupNameParam|应用分组名称不存在"  class="formField required "  id="groupNameNew" name="groupNameParam">
			<button class="btn btn-warning"  onclick="getGroupTypeList('','','groupName')">查询</button>
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">URL样例：</label> 
		<div class="col-sm-4">
			<input value="${vo.urlExample}" type="text"  placeholder="URL样例"  class="formField"  id="urlExample" name="urlExample">
		</div>
		<c:choose>
			<c:when test="${empty vo.id}">
			</c:when>
			<c:otherwise>
				<label class="col-sm-2 labelForm">NUM：</label>
				<div class="col-sm-4">
					<input value="${vo.num}" type="text" maxlength="10" placeholder="NUM"  class="formField required "  id="num" name="num" readonly>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">是否有效：</label>
		<div class="col-sm-4">
	    	<c:choose>
		    	<c:when test="${'1' eq vo.isValid}">
					<input type="radio" value="1" checked class="formField required" name="isValid"/><span>有效</span>
					<input type="radio" value="0" class="formField required" name="isValid"/><span>无效</span>
		    	</c:when>
		    	<c:when test="${'0' eq vo.isValid}">
					<input type="radio" value="1" class="formField required" name="isValid"/><span>有效</span>
					<input type="radio" value="0" checked class="formField required" name="isValid"/><span>无效</span>
		    	</c:when>
		    	<c:otherwise>
					<input type="radio" value="1" checked class="formField required" name="isValid"/><span>有效</span>
					<input type="radio" value="0" class="formField required" name="isValid"/><span>无效</span>
		    	</c:otherwise>
	    	</c:choose>		
    	</div>
		
	</div>
</div>

<script src="<%=request.getContextPath()%>/pages/jsp/dpi/productInfo/productInfoForm.js"></script>
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/keywordSearchRule/keywordSearchRuleForm.js"></script>
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/keywordGroup/keywordGroupForm.js"></script>
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/dpiCommon.js"></script>