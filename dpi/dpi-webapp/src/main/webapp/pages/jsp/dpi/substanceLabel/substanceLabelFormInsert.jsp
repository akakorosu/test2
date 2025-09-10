<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cxt" uri="http://www.bonc.com.cn/common/tag/cxt"%>
<!-- 编辑表单div -->
<style>
#divFastSearch>div{
	padding-left:12px;
    line-height: 30px;
    border-bottom: 1px dashed #ccc;
}
</style>
<div id="piForm" style="position:relative;">
	<%-- <input type="hidden" class="formField" name="id" value="${vo.id}">  --%>
	<input value="${vo.contentLabelCode }" type="hidden" placeholder="映射标签编码"   readonly class="formField " id="contentLabelCodeOriginal" name="contentLabelCodeOriginal" >
	<div class="clearfix formGroup">
	<p style="color: red">提示：更改映射标签编码</p>
	</div>
	<div class="clearfix formGroup" style="margin:0 0 20px 40px;position:absolute;width:570px;z-index:1;"> 
	    <label class="col-sm-2 labelForm">搜索标签</label> <!-- 新加的 -->
	    <input class="width200" maxlength="40" type="text" id="likeSearch" name="record.doctor" style="width:380px" >
	  <!--   <input placeholder="快速搜索" id="inputFastSearch"> -->
	  
	    <!-- <button class="btn btn-primary" type="button"><i class="fa fa-search inputFastSearchIcon"></i>查询</button>-->
	    <button class="btn btn-primary" type="button" id="searchPiListInsert">确定</button>
	     <label class="col-sm-2 labelForm"></label> <!-- 新加的 -->
		<div id="divFastSearch" style="width:380px;max-height:240px;overflow:auto;background:#fff;">
		</div>
		<!-- <input type="text" placeholder="产品名称" class="searchField input-md" id="name" name="name" style="width:100px;"> -->
		<!-- <select name="" id="selectName" class="chosen-select" data-placeholder="产品ID" style="width:100px;">
		</select>  -->
		
		<!-- <span style="color: #000;">产品名称：</span> <select
							style="width: 400px !important;" multiple=""
							class="chosen-select" id="form-field-select-4"
							data-placeholder="请选择指标名称">
							<option value="AL">北京</option>
							<option value="AK">天津</option>
						</select> -->
	</div>
	<div class="clearfix formGroup" style="margin-top:66px;">
		<label class="col-sm-2 labelForm">映射标签编码：</label> 
		<div class="col-sm-4">
			<input value type="text" placeholder="映射标签编码"  readonly  class="formField " id="contentLabelCodeInsert" name="contentLabelCodeInsert" >
		</div>	
		<label class="col-sm-2 labelForm">表类型：</label> 
		<div class="col-sm-4">
			<!-- <input value type="text" placeholder="表类型" class="formField " id="contentLabelName1" name="contentLabelName1" /> -->
			<select id="tableTypeInsert" class="searchField input-md " style="width:180px;">
					<option value="G0004">视频</option>
					<option value="G0009">小说</option>
					<option value="G0014">电商</option>
					<option value="G0012">音乐</option>
					
				</select>
		</div>
	</div>
	<div class="clearfix formGroup">
	    <label class="col-sm-2 labelForm">产品编码：</label> 
		<div class="col-sm-4">
			<input value type="text" placeholder="产品编码" class="formField " id="prodIdInsert" name="prodId" readonly/>
		</div>
		<label class="col-sm-2 labelForm">产品名称：</label> 
		<!-- <div class="col-sm-4">
			<input value type="text" placeholder="产品名称"  class="formField " id="prodName" name="prodName" readonly>
		</div> -->
		<div class="col-sm-4">
		<select id="prodNameInsert" class="searchField input-md " style="width:180px;">
					
					
		</select>
		</div>
	</div>
	<div class="clearfix formGroup">
	    <label class="col-sm-2 labelForm">识别单一标签：</label> 
		<div class="col-sm-4">
			<input value type="text" placeholder="识别单一标签" class="formField " id="catFullLabelNameInsert" name="catFullLabelNameInsert" />
		</div>
		<label class="col-sm-2 labelForm">内容分类：</label> 
		<div class="col-sm-4">
			<!-- <input value type="text" placeholder="识别内容类别" class="formField " id="catContentType" name="contentLabelName1Insert" /> -->
		<select id="catContentType" class="searchField input-md " style="width:180px;">
					
					
		</select>
		</div>
		
	</div>
	
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/substanceLabel/substanceLabelFormInsert.js"></script>
