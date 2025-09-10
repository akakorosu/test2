<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cxt" uri="http://www.bonc.com.cn/common/tag/cxt"%>
<style>
#simple-table tbody td {height: 51px;vertical-align: middle;}
</style>
<!-- 编辑表单div -->
<div id="dmDtarRuleInfoForm">
	<div class="clearfix" style="margin-bottom: 10px;">
		<cxt:CodeSelectTlb id="tarType" name="tarTypeTemp" codeType="phtype"  style="float:left;margin-right:5px;"></cxt:CodeSelectTlb>
		<cxt:CodeSelectTlb id="phnrsxtype" name="phnrsxtypeTemp" codeType="phnrsxtype"  style="display: none;float:left;margin-right:5px;"></cxt:CodeSelectTlb>
		<cxt:CodeSelectTlb id="phnrsxtype_1" name="phnrsxtype_1Temp" codeType="phnrsxtype_1"  style="display: none;float:left;margin-right:5px;"></cxt:CodeSelectTlb>
		<cxt:CodeSelectTlb id="phnrsxtype_2" name="phnrsxtype_2Temp" codeType="phnrsxtype_2"  style="display: none;float:left;margin-right:5px;"></cxt:CodeSelectTlb>
		<cxt:CodeSelectTlb id="phnrsxtype_3" name="phnrsxtype_3Temp" codeType="phnrsxtype_3"  style="display: none;float:left;margin-right:5px;"></cxt:CodeSelectTlb>
		<cxt:CodeSelectTlb id="phnrbqlx" name="phnrbqlxTemp" codeType="phnrbqlx"  style="display: none;float:left;margin-right:5px;"></cxt:CodeSelectTlb>
		<span id="searchTar" style="display: none;">
			<div style="float:left;margin-right:5px;">
				<input type="text" placeholder="搜索" id="tarName" style="width: 150px;">
				<input type="hidden" id="tarCode">
				<div id="searchTarInputDiv" style="display: none;position: absolute;border:1px solid #ccc;background-color: white;max-width: 400px;max-height: 400px;overflow: auto;padding: 10px;"></div>
			</div>
			<cxt:CodeSelectTlb id="tarQuota" name="tarQuotaTemp" codeType="phzhibiao"  defaultyn="n"></cxt:CodeSelectTlb>
			<cxt:CodeSelectTlb id="tarSign" name="tarSignTemp" codeType="phfuhao"  defaultyn="n" style="width:80px"></cxt:CodeSelectTlb>
			<input type="text" placeholder="数值" id="tarValue" name="tarValueTemp" style="width: 100px;">
			<button class="btn btn-xs btn-primary" type="button" id="tarAddBtn">
				<i class="glyphicon glyphicon-plus"></i>
			</button>
		</span>
	</div>
	<div>
		<table id="simple-table" class="table table-bordered table-hover center">
			<thead>
				<tr>
					<th width="25%" class="center">类型</th>
					<th width="15%" class="center">名称</th>
					<th width="15%" class="center">指标</th>
					<th width="10%" class="center">符号</th>
					<th width="10%" class="center">值</th>
					<th width="25%" class="center"></th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
</div>
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/dmDtarRuleInfo/dmDtarRuleInfoForm.js"></script>
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/dpiCommon.js"></script>
<script type="text/javascript">
$(function(){
	// 修改获取children字符串
	var s = '${list}';
	// 转json
	var array = eval('(' + s + ')');
	// 循环往表格追加
	for(var i = 0; i < array.length; i ++) {
		getTableTrHtml(array[i]);
	}
})
</script>