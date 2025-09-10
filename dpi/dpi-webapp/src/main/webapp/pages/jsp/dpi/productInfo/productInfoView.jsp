<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cxt" uri="http://www.bonc.com.cn/common/tag/cxt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- 编辑表单div -->
<script
	src="<%=request.getContextPath()%>/pages/jsp/dpi/productInfo/productInfoView.js"></script>
<div id="upForm">
	<c:forEach var="vo" items="${list}">

		<div class="clearfix formGroup">
			<label class="col-sm-1 labelForm">表名：</label>
			<div class="col-sm-3">
				<input value="${vo.tableName }" type="text" readonly="readonly" title="${vo.tableNameEn }"/>
			</div>
			<label class="col-sm-1 labelForm">域名：</label>
			<div class="col-sm-3">
				<input value="${vo.host }" type="text" readonly="readonly" />
			</div>
			<label class="col-sm-1 labelForm">规则：</label>
			<div class="col-sm-3">
				<input value="${vo.regRule }" type="text" readonly="readonly" />
			</div>
		</div>
	</c:forEach>
	<c:if test="${list!=null && fn:length(list) > 0}">
      <div class="clearfix formGroup">
			<label class="col-sm-1 labelForm">新id：</label>
			<div class="col-sm-4">
				<input value="" type="text"  id="prodIdnew" placeholder="更改上表的产品id"/>
			</div>
			
		
	</div>
    </c:if>
	
	<%-- <div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">产品ID：</label> 
		<div class="col-sm-4">
			<input value="${vo.prodId }" type="text" readonly="readonly" />
		</div>
		<label class="col-sm-2 labelForm">产品名称：</label> 
		<div class="col-sm-4">
			<input value="${vo.prodName }" type="text" readonly="readonly" />
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">标签ID：</label> 
		<div class="col-sm-4">
			<input value="${vo.prodLabelCode }" type="text" readonly="readonly" />
		</div>
		<label class="col-sm-2 labelForm">附属标签列表：</label>
		<div class="col-sm-4">
			<input value="${vo.addLabelList }" type="text" readonly="readonly" />
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">父产品ID：</label> 
		<div class="col-sm-4">
			<input value="${vo.fatherProdId }" type="text" readonly="readonly" />
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">产品归类ID：</label> 
		<div class="col-sm-4">
			<input value="${vo.prodTypeId }" type="text" readonly="readonly" />
		</div>		
		<label class="col-sm-2 labelForm">产品归类名称：</label> 
		<div class="col-sm-4">
			<input value="${vo.prodTypeName }" type="text" readonly="readonly" />
		</div>
	</div>	
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">产品类别：</label> 
		<div class="col-sm-4">
			<input value="${vo.prodCatagory }" type="text" readonly="readonly" />
		</div>
		<label class="col-sm-2 labelForm">运营商业务：</label>
		<div class="col-sm-4">
			<c:if test="${vo.isOperator ==0}">
				<input value="非运营商" type="text" readonly="readonly" />
			</c:if>	
			<c:if test="${vo.isOperator ==1}">
				<input value="移动自有" type="text" readonly="readonly" />
			</c:if>
			<c:if test="${vo.isOperator ==2}">
				<input value="联通自有" type="text" readonly="readonly" />
			</c:if>	
			<c:if test="${vo.isOperator ==3}">
				<input value="电信自有" type="text" readonly="readonly" />
			</c:if>			
		</div>		
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">是否主产品：</label> 
		<div class="col-sm-4">
			<c:if test="${vo.isMainProd ==1}">
				<input value="是" type="text" readonly="readonly" />
			</c:if>	
			<c:if test="${vo.isMainProd ==0}">
				<input value="否" type="text" readonly="readonly" />
			</c:if>			
		</div>
		<label class="col-sm-2 labelForm">是否APP：</label> 
		<div class="col-sm-4">
			<c:if test="${vo.isApp ==1}">
				<input value="是" type="text" readonly="readonly" />
			</c:if>	
			<c:if test="${vo.isApp ==0}">
				<input value="否" type="text" readonly="readonly" />
			</c:if>			
		</div>
	</div>
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">spid：</label> 
		<div class="col-sm-4">
			<input value="${vo. spId}" type="text" readonly="readonly">
		</div>
		<label class="col-sm-2 labelForm">spName：</label> 
		<div class="col-sm-4">
			<input value="${vo.spName }" type="text" readonly="readonly">
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
	<div class="clearfix formGroup">
		<label class="col-sm-2 labelForm">公司简称：</label> 
		<div class="col-sm-4">
			<input value="${vo. shortName}" type="text" readonly="readonly" >
		</div>	
		<label class="col-sm-2 labelForm">是否有效：</label> 
		<div class="col-sm-4">
			<c:if test="${vo.isValid ==1}">
				<input value="有效" type="text" readonly="readonly" />
			</c:if>	
			<c:if test="${vo.isValid ==0}">
				<input value="无效" type="text" readonly="readonly" />
			</c:if>
		</div>
	</div> --%>
</div>

