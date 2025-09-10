<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cxt" uri="http://www.bonc.com.cn/common/tag/cxt"%>

<!-- 编辑表单div -->
<div id="prForm">
	<input type="hidden" class="formField" id="id" name="id" value="${vo.id}">
	<input type="hidden" class="formField" name="author" value="${vo.author}">
	<input type="hidden" class="formField" name="opTime" value="${vo.opTime}">
	<input type="hidden" class="formField" name="isValid" value="${vo.isValid}">
	<div class="clearfix formGroup">
	<p style="color: red">提示：域名、经度截取规则、纬度截取规则这三个值组合的联合主键不能重复</p>
	</div>
	
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">域名：</label> 
		<div class="col-sm-4">
			<input value="${vo.host}" type="text"  placeholder="域名" onlyUrl="/positionRule/checkRepeat,id,hostParam,longitudeRule,latitudeRule|联合主键重复" class="formField required" id="host" name="hostParam" />
		</div>
		<label class="col-sm-2 labelForm">URL：</label>
		<div class="col-sm-4">
			<input value="${vo.url}" type="text" placeholder="URL"  class="formField required" id="url" name="url" />
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">匹配类型：</label> 
		<div class="col-sm-4">
			<c:if test="${empty vo.id}">
				<cxt:CodeSelectTlb name="matchType" codeType="matchType" value="${vo.matchType}" defaultyn="n" cssClass="formField"></cxt:CodeSelectTlb>
			</c:if>
			<c:if test="${!empty vo.id}">
				<input value="${vo.matchType}" type="text" readonly="readonly" placeholder="匹配类型"  class="formField required" id="matchType" name="matchType" />
			</c:if>
		</div>		
		<label class="col-sm-2 labelForm">定位系统：</label> 
		<div class="col-sm-4">
			<cxt:CodeSelectTlb name="positionSys" codeType="positionSys" value="${vo.system}" defaultyn="n" cssClass="formField"></cxt:CodeSelectTlb>
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">经度截取规则：</label>
		<div class="col-sm-4">
			<input value="${vo.longitudeRule}" type="text" placeholder="经度截取规则" onlyUrl="/positionRule/checkRepeat,id,hostParam,longitudeRule,latitudeRule|联合主键重复"  class="formField required" id="longitudeRule" name="longitudeRule">
			<button class="btn btn-success" id="checkLongitudeRule" onclick="checkRule('1')">校验</button>
		</div>	
		<label class="col-sm-2 labelForm">经度截取序号：</label> 
		<div class="col-sm-4">
			<input value="${vo.getIndexLong}" type="text" placeholder="经度截取序号" class="formField isNum" id="getIndexLong" name="getIndexLong">
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">纬度截取规则：</label>
		<div class="col-sm-4">
			<input value="${vo.latitudeRule}" type="text" placeholder="纬度截取规则" onlyUrl="/positionRule/checkRepeat,id,hostParam,longitudeRule,latitudeRule|联合主键重复"  class="formField required" id="latitudeRule" name="latitudeRule">
			<button class="btn btn-success" id="checkLatitudeRule" onclick="checkRule('2')">校验</button>
		</div>	
		<label class="col-sm-2 labelForm">纬度截取序号：</label> 
		<div class="col-sm-4">
			<input value="${vo.getIndexLati}" type="text" placeholder="纬度截取序号" class="formField isNum" id="getIndexLati" name="getIndexLati">
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">系统截取规则：</label>
		<div class="col-sm-4">
			<input value="${vo.systemRule}" type="text" placeholder="系统截取规则" class="formField" id="systemRule" name="systemRule">
			<button class="btn btn-success" id="checkSystemRule" onclick="checkRule('3')">校验</button>
		</div>	
		<label class="col-sm-2 labelForm">系统截取序号：</label> 
		<div class="col-sm-4">
			<input value="${vo.getIndexSys}" type="text" placeholder="系统截取序号" class="formField isNum" id="getIndexSys" name="getIndexSys">
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">应用分组编码：</label> 
		<div class="col-sm-4">
			<input value="${vo.groupType}" type="text"  placeholder="应用分组编码" onlyUrl="/positionRule/checkGroupType,groupType|应用分组编码不存在" class="formField required "  id="groupType" name="groupType">
			<button class="btn btn-warning" id="checkParseRuleAndriod" onclick="getGroupTypeList('','','groupType')">查询</button>
		</div>
		<label class="col-sm-2 labelForm">应用分组名称：</label>
		<div class="col-sm-4">
			<input value="${vo.groupName}" type="text" placeholder="应用分组名称" onlyUrl="/positionRule/checkGroupName,groupNameParam|应用分组名称不存在"  class="formField required "  id="groupNameNew" name="groupNameParam">
			<button class="btn btn-warning"  onclick="getGroupTypeList('','','groupName')">查询</button>
		</div>
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
</div>

<script src="<%=request.getContextPath()%>/pages/jsp/dpi/positionRule/positionRuleForm.js"></script>
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/keywordGroup/keywordGroupForm.js"></script>
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/dpiCommon.js"></script>