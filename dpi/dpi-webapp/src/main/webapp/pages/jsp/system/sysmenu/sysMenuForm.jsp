<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 编辑表单div -->
<div id="sysMenuForm">
	<input type="hidden" class="formField" name="menuId" value="${vo.menuId }">
	<input type="hidden" class="formField" name="menuLevel" value="${vo.menuLevel }">
	<input type="hidden" class="formField" name="state" value="${vo.state }">
	<input type="hidden" class="formField" name="memo" value="${vo.memo }">
	<input type="hidden" class="formField" name="treeCode" value="${vo.treeCode }">
	<!-- 父机构信息 -->
	<div class="clearfix formGroup">
		<input type="hidden" class="formField" name="parentId" value="${vo.parent.menuId }">
		<label class="col-sm-2 labelForm">父菜单名称：</label> 
		<div class="col-sm-4">
			<label class="labelForm">${vo.parent.menuName }</label>
		</div>
		<label class="col-sm-2 labelForm">父菜单级别：</label> 
		<div class="col-sm-4">
			<label class="labelForm">${vo.parent.menuLevel }</label>
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">菜单名称：</label> 
		<div class="col-sm-4">
			<input value="${vo.menuName }" type="text" placeholder="菜单名称" class="formField required" id="menuName" name="menuName">
		</div>
		<label class="col-sm-2 labelForm">排序：</label>
		<div class="col-sm-4">
			<input value="${vo.displayOrder }" type="text" placeholder="排序" class="formField required isNum" id="displayOrder" name="displayOrder">
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">菜单url：</label> 
		<div class="col-sm-10">
			<input value="${vo.menuUrl }" type="text" placeholder="菜单url" class="input-xxlarge formField" id="menuUrl" name="menuUrl">
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">图标url：</label> 
		<div class="col-sm-10">
			<input value="${vo.iconPath }" type="text" placeholder="图标url" class="input-xxlarge formField" id="iconPath" name="iconPath">
		</div>
	</div>
</div>
<script src="<%=request.getContextPath()%>/pages/jsp/system/sysmenu/sysMenuForm.js"></script>
