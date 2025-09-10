<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cxt" uri="http://www.bonc.com.cn/common/tag/cxt"%>

<!-- 编辑表单div -->
<div id="prForm">
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">域名：</label> 
		<div class="col-sm-4">
			<input value="${vo.host}" type="text" readonly="readonly"/>
		</div>
		<label class="col-sm-2 labelForm">URL：</label>
		<div class="col-sm-4">
			<input value="${vo.url}" type="text" readonly="readonly" />
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">匹配类型：</label> 
		<div class="col-sm-4">
			<input value="${vo.matchType}" type="text" readonly="readonly"/>
		</div>
		<label class="col-sm-2 labelForm">定位系统：</label> 
		<div class="col-sm-4">
			<input value="${vo.system}" type="text" readonly="readonly"/>
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">经度截取规则：</label>
		<div class="col-sm-4">
			<input value="${vo.longitudeRule}"  title="${vo.longitudeRule}" type="text" readonly="readonly"/>
		</div>		
		<label class="col-sm-2 labelForm">经度截取序号：</label> 
		<div class="col-sm-4">
			<input value="${vo.getIndexLong}" type="text" readonly="readonly"/>
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">纬度截取规则：</label>
		<div class="col-sm-4">
			<input value="${vo.latitudeRule}" title="${vo.latitudeRule}" type="text" readonly="readonly"/>
		</div>	
		<label class="col-sm-2 labelForm">纬度截取序号：</label> 
		<div class="col-sm-4">
			<input value="${vo.getIndexLati}" type="text" readonly="readonly"/>
		</div>
	</div>	
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">系统截取规则：</label> 
		<div class="col-sm-4">
			<input value="${vo.systemRule}" title="${vo.systemRule}" type="text" readonly="readonly"/>
		</div>
		<label class="col-sm-2 labelForm">系统截取序号：</label>
		<div class="col-sm-4">
			<input value="${vo.getIndexSys}" type="text" readonly="readonly"/>
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">应用分组编码：</label> 
		<div class="col-sm-4">
			<input value="${vo.groupType}" type="text" readonly="readonly"/>
		</div>
		<label class="col-sm-2 labelForm">应用分组名称：</label>
		<div class="col-sm-4">
			<input value="${vo.groupName}" type="text" readonly="readonly"/>
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">是否有效：</label> 
		<div class="col-sm-4">
			<c:if test="${vo.isValid == 1}">
				<input value="有效" style="color: green" type="text" readonly="readonly" />
			</c:if>	
			<c:if test="${vo.isValid == 0}">
				<input value="无效" style="color: red" type="text" readonly="readonly" />
			</c:if>				
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">操作人：</label> 
		<div class="col-sm-4">
			<input value="${vo.author}" type="text" readonly="readonly" />
		</div>
		<label class="col-sm-2 labelForm">操作时间：</label>
		<div class="col-sm-4">
			<input value="${vo.opTime}" type="text" readonly="readonly" />
		</div>
	</div>
</div>

