<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cxt" uri="http://www.bonc.com.cn/common/tag/cxt"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/jquery-ui-1.10.0.custom.min.css">
<script src="<%=request.getContextPath()%>/pages/js/jquery-ui-1.10.0.custom.min.js"></script>
<div id="domainRuleForm">
	<div class="clearfix formGroup">
		<label class="col-sm-3 labelForm">域名编码：</label>
		<div class="col-sm-9"  style="display:inline;">
				<input type="text" placeholder="域名编码" class="formField required"
					id="dc" name="domainCode" oninput="showMessage(this)" onporpertychange="showMessage(this)" />
				<input type="text" placeholder="域名后缀"  class="formField required"
				 class="formField required" id="prodName" name="prodName"  onblur="checkReplaceName(this)"/>
				 <div class="ui-widget" ></div>
			<div style="color: red;" id="DOMAINCODE"></div>
			<div id="ckeckboxs"></div>
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-3 labelForm">选择产品：</label>
		<div class="col-sm-9" style="height: 10%;overflow: hidden ">
			<div class="ui-widget" ></div>
				<input type="text" placeholder="输入产品名称"  class="formField required"
				 class="formField required" id="prodId" name="prodId"/>
				<button class="btn btn-primary btn-sm" onclick="test()" type="button" id="createApplyBtn" style="margin-left:5px;height:30px; border-radius:3px;background-color:#10d228 !important;">
							<i class="fa fa-plus" aria-hidden="true"></i>
							新建
				</button>
		</div>
	</div>
	<div id="prodctInfoForm"  style="display: none;border-style:solid; border-width:1px; border-color:#000;" class="clearfix formGroup">
		<div class="clearfix formGroup">	
			<label class="col-sm-3 labelForm">输入产品名称</label>
			<div class="col-sm-9">
				<input type="text" placeholder="产品名称" class="formField"
					id="pName" name="pName"/>
			</div>
		</div>
		<div class="clearfix formGroup">	
			<label class="col-sm-3 labelForm">产品</label>
			<div class="col-sm-9">
				<input type="text" placeholder="产品" class="formField"
					id="" name="" value=""/>
			</div>
		</div>
		<div class="clearfix formGroup">	
			<label class="col-sm-3 labelForm"></label>
			<div class="col-sm-9">
				<button id="queren" class="btn btn-primary btn-sm">保存</button>&nbsp;&nbsp;&nbsp;&nbsp;
				<button id="quxiao" class="btn btn-primary btn-sm">取消</button>
			</div>
		</div>
	</div>
</div>
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/ruleForm.js"></script>
