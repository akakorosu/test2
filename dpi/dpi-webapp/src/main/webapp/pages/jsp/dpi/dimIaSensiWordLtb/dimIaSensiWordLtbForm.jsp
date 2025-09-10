<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cxt" uri="http://www.bonc.com.cn/common/tag/cxt"%>
<!-- 编辑表单div -->
<div id="showSensiForm">
	<div class="clearfix formGroup">
		<p style="color: red">提示：敏感词不能重复</p>
	</div>
	<div class="clearfix formGroup">
		<div class="col-sm-4">
			<input value="${vo.id }" type="hidden" placeholder="id" class="formField required" id="id" name="id" />
		</div>
	</div>
	<div class="clearfix formGroup">
	    <label class="col-sm-2 labelForm">敏感词：</label> 
		<div class="col-sm-4">
			<input value="${vo.sensiWord }" style='width:500px;' type="text" placeholder="敏感词" onlyUrl="/sensi/check,id,sensiWord" class="formField required" id="sensiWord" name="sensiWord" />
		</div>
	</div>
	
	
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm"></label>
		<div class="col-sm-4">
				<input value="${vo.author }" type="hidden" placeholder="作者" class="formField required" id="author" name="author" />
		</div>
		<label class="col-sm-2 labelForm"></label> 
		<div class="col-sm-4">
			<input value="${vo.opTime }" type="hidden" placeholder="时间" class="formField required" id="opTime" name="opTime" />
		</div>
	</div>

</div>
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/dpiCommon.js"></script>