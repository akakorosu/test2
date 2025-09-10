<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cxt" uri="http://www.bonc.com.cn/common/tag/cxt"%>
<!-- 编辑表单div -->
<div id="upForm">
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">分词：</label> 
		<div class="col-sm-4">
			<input value="${vo.segWord }" type="text" readonly="readonly" />
		</div>
	</div>
</div>

