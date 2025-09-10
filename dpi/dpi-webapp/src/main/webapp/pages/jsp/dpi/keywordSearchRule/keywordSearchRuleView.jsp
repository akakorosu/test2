<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cxt" uri="http://www.bonc.com.cn/common/tag/cxt"%>

<!-- 编辑表单div -->
<div id="ksrForm">
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">域名：</label> 
		<div class="col-sm-4">
			<input value="${vo.host}" type="text" readonly="readonly"/>
		</div>
		<label class="col-sm-2 labelForm">匹配类型：</label> 
		<div class="col-sm-4">
			<input value="${vo.matchType }" type="text" readonly="readonly"/>
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">产品ID：</label> 
		<div class="col-sm-4">
			<input value="${vo.prodid }" type="text" readonly="readonly"/>
		</div>
		<label class="col-sm-2 labelForm">产品名称：</label> 
		<div class="col-sm-4">
			<input value="${vo.prodName }" type="text" readonly="readonly"/>
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">动作目录名称：</label>
		<div class="col-sm-4">
			<input value="${vo.channelName }" type="text" readonly="readonly"/>
		</div>		
		<label class="col-sm-2 labelForm">规则匹配类型：</label> 
		<div class="col-sm-4">
			<input value="${vo.ruleType }" type="text" readonly="readonly"/>
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">安卓截取规则：</label>
		<div class="col-sm-4">
			<input value="${vo.parseRuleAndriod }" title="${vo.parseRuleAndriod }" type="text" readonly="readonly"/>
		</div>	
		<label class="col-sm-2 labelForm">安卓截取序号：</label> 
		<div class="col-sm-4">
			<input value="${vo.getIndexAndriod }" type="text" readonly="readonly"/>
		</div>
	</div>	
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">苹果截取规则：</label> 
		<div class="col-sm-4">
			<input value="${vo.parseRuleIos }" title="${vo.parseRuleIos }" type="text" readonly="readonly"/>
		</div>
		<label class="col-sm-2 labelForm">苹果截取序号：</label>
		<div class="col-sm-4">
			<input value="${vo.getIndexIos }" type="text" readonly="readonly"/>
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">应用分组编码：</label> 
		<div class="col-sm-4">
			<input value="${vo.groupType }" type="text" readonly="readonly"/>
		</div>
		<label class="col-sm-2 labelForm">应用分组名称：</label>
		<div class="col-sm-4">
			<input value="${vo.groupName }" type="text" readonly="readonly"/>
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">是否有效：</label> 
		<div class="col-sm-4">
			<c:if test="${vo.isValid ==1}">
				<input value="有效" style="color: green" type="text" readonly="readonly" />
			</c:if>	
			<c:if test="${vo.isValid ==0}">
				<input value="无效" style="color: red" type="text" readonly="readonly" />
			</c:if>				
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">操作人：</label> 
		<div class="col-sm-4">
			<input value="${vo.author }" type="text" readonly="readonly" />
		</div>
		<label class="col-sm-2 labelForm">操作时间：</label>
		<div class="col-sm-4">
			<input value="${vo.opTime }" type="text" readonly="readonly" />
		</div>
	</div>
</div>

