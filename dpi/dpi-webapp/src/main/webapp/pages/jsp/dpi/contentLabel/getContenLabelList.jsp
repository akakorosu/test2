<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cxt" uri="http://www.bonc.com.cn/common/tag/cxt"%>
<link rel="stylesheet" href="css/bootstrap.css" />
<!-- 表单div -->
<script>
	//选择产品
	
	function chooseLabel(contentLabelName,contentLabelLevel,contentLabelName1,contentLabelName2,contentLabelName3,contentLabelName4,contentLabelName5,contentLabelName6){
		
		$("#contentLabelName11").val(contentLabelName);
		$("#contentLabelLevel").val(contentLabelLevel);
		$("#contentLabelName1").val(contentLabelName1);
		$("#contentLabelName2").val(contentLabelName2);
		$("#contentLabelName3").val(contentLabelName3);
		$("#contentLabelName4").val(contentLabelName4);
		$("#contentLabelName5").val(contentLabelName5);
		$("#contentLabelName6").val(contentLabelName6);
		topwindow.removeWindow();
	}
	
	
</script>
<div  id="contentLabelList">
    <table style="width: 850px; overflow-x:hidden; " class="table table-striped table-hover table-bordered">
        <thead>
            <tr>
                <th>标签名称</th>
                <th>标签级别</th>
                <th>一级标签名称</th>
                <th>二级标签名称</th>
                <th>三级标签名称</th>
                <th>四级标签名称</th>
                <th>五级标签名称</th>
                <th>六级标签名称</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach items="${requestScope.list}" var="vo" varStatus="id">
             <tr>
                 <td>${vo.contentLabelName}</td>
                 <td>${vo.contentLabelLevel}</td>
                 <td>${vo.contentLabelName1}</td>
                 <td>${vo.contentLabelName2}</td>
                 <td>${vo.contentLabelName3}</td>
                 <td>${vo.contentLabelName4}</td>
                 <td>${vo.contentLabelName5}</td>
                 <td>${vo.contentLabelName6}</td>
                 <td><a name="sdf" href="#" onclick="chooseLabel('${vo.contentLabelName}','${vo.contentLabelLevel}','${vo.contentLabelName1}','${vo.contentLabelName2}','${vo.contentLabelName3}','${vo.contentLabelName4}','${vo.contentLabelName5}','${vo.contentLabelName6}')">选择</a></td>
             </tr>     				
		</c:forEach>
		</tbody>
     </table>
 </div>

