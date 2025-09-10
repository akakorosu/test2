<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cxt" uri="http://www.bonc.com.cn/common/tag/cxt"%>

<!-- <link rel="stylesheet" href="css/bootstrap.css" /> -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/ace/css/bootstrap.css">

<script src="<%=request.getContextPath()%>/pages/jsp/dpi/keywordSearchRule/keywordSearchRuleForm.js"></script>
<script>
	//选择产品
	function chooseProdId(prodid,prodName){
		$("#prodName").val(prodName);
		$("#prodid").val(prodid);
		//关闭窗口
		topwindow.removeWindow();
	}
</script>

<!-- 表单div -->
<div  id="prodIdList">
    <table style="width: 750px; overflow-x:hidden; " class="table table-striped table-hover table-bordered">
        <thead>
            <tr>
                <th>产品名称</th>
                <th>产品Id</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach items="${requestScope.list}" var="vo" varStatus="id">
<%--      				${id.index} --%>
             <tr>
                 <td>${vo.prodName}</td>
                 <td>${vo.prodId}</td>
                 <td><a name="sdf" href="#" onclick="chooseProdId('${vo.prodId}','${vo.prodName}')">选择</a></td>
             </tr>     				
		</c:forEach>
		</tbody>
     </table>
 </div>

