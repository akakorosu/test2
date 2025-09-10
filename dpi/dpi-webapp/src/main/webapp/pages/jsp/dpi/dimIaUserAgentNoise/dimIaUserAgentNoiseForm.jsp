<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cxt" uri="http://www.bonc.com.cn/common/tag/cxt"%>
<!-- 编辑表单div -->
<div id="showNoiseForm">
	<div class="clearfix formGroup">
		<p style="color: red">提示：噪音规则重复校验</p>
	</div>
	<div class="clearfix formGroup">
		<div class="col-sm-4">
			<input value="${vo.id }" type="hidden" placeholder="噪音规则" class="formField required" id="id" name="id" />
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">噪音规则：</label> 
		<div class="col-sm-4">
			<input value="${vo.noiseKey }" type="text" placeholder="噪音规则" onlyUrl="/uanoise/check,id,noiseKey" class="formField required" id="noiseKey" name="noiseKey" />
		</div>
	
	</div>

</div>
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/dpiCommon.js"></script>