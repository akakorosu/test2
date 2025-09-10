<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cxt" uri="http://www.bonc.com.cn/common/tag/cxt"%>
<link rel="stylesheet" href="productInfo/css/bootstrap.css" />
<!-- 表单div -->
<div  id="prodIdList">
    <table style="width: 750px; overflow-x:hidden; " class="table table-striped table-hover table-bordered">
        <thead>
            <tr>
                <th>分组名称</th>
                <th>分组编码</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach items="${requestScope.list}" var="vo" varStatus="id">
<%--      				${id.index} --%>
             <tr>
                 <td>${vo.groupName}</td>
                 <td>${vo.groupType}</td>
                 <td><a name="sdf" href="#" onclick="chooseProdId('${vo.groupType}','${vo.groupName}')">选择</a></td>
             </tr>     				
		</c:forEach>
		</tbody>
     </table>
 </div>

<script>
	//选择产品
	function chooseProdId(groupType,groupName){
		$("#groupNameNew").val(groupName);
		$("#groupType").val(groupType);
		//关闭窗口
		topwindow.removeWindow();
	}
</script>