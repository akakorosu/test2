<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
	.btn-block-toggle{
		display: block;
		cursor: pointer;
		width:120px;
		text-align: left;
		padding:0;
		padding-left:20px;
		height:40px;
		line-height:40px;
		color:#337ab7 !important;
	}
	.btn.btn-block{
		display: block;
		margin-top:0;
		border-radius: 0;
		text-align: left;
		padding:0;
		padding-left:20px;
		height:40px;
		line-height:40px;
		color:#337ab7 !important;
		border:0;
		border-bottom:1px solid #ddd;
		background:#F5F5F3 !important;
	}
	.btn.btn-block:hover{
		background:#fff !important;
	}
	.btn-block-box{
		display: none;
		position:absolute;
		top:50px;
		right:12px;
		z-index:2;
		width:120px;
		box-shadow:0px 0px 5px #aaa;
	}
	.btn-block-box>form,.btn-block-box>form>block,.btn-block-box>block{
		display: block;
		width:100%;
	}

</style>
<div class="clearfix pull-right" id="operationFlowDIv">
	<a class="btn-block-toggle"><img src="<%=request.getContextPath()%>/pages/images/piliang-copy.png" alt=""> 批量操作</a>
	<div class="clearfix btn-block-box">
		<button class="btn btn-block btn-warning" type="button" id="downloadBtn">excel模板下载</button>

		<!-- txt -->
		<button class="btn btn-block btn-warning" type="button" id="exportTxtBtn">导出txt</button>
		<form id="importExcelTxt"action="" method="post" enctype="multipart/form-data" style="position:relative;">
			<button class="btn btn-block btn-warning">导入txt</button>
			<input id="importBtnTxt" style="opacity:0;width:100%;height:40px;position:absolute;top:0px;right:0px;z-index:1;cursor:pointer;" type="file"/>
		</form>

		<!-- excel -->
		<button class="btn btn-block btn-warning" type="button" id="exportExcelBtn">导出excel</button>
		<form id="importExcelForm" action="" method="post" enctype="multipart/form-data" style="position:relative;">
			<button class="btn btn-block btn-warning">导入excel</button>
			<input id="importBtnExcel" style="opacity:0;width:100%;height:40px;position:absolute;top:0px;right:0px;z-index:1;cursor:pointer;" type="file"/>
		</form>

		<form id="downloadForm" action="<%=request.getContextPath()%>/operationflow/download?templateName=<%=request.getParameter("templateName")%>" method="post" style="float: right;">
		</form>

		<form id="exportForm" action="<%=request.getContextPath()%>/operationflow/export" method="post">
			<input type="hidden" id="beanName" name="beanName" value="<%=request.getParameter("beanName")%>">
			<input type="hidden" id="entityName" name="entityName" value="<%=request.getParameter("entityName")%>">
			<input type="hidden" id="templateName" name="templateName" value="<%=request.getParameter("templateName")%>">
			<input type="hidden" id="fieldNums" name="fieldNums" value="<%=request.getParameter("fieldNums")%>">
			<input type="hidden" id="textFieldNums" name="textFieldNums" value="<%=request.getParameter("textFieldNums")%>">
			<input type="hidden" id="exportParameter" name="exportParameter">
			<input type="hidden" id="exportType" name="exportType">
		</form>
	</div>
</div>
<script type="text/javascript">
var flag = false;//按钮组打开关闭开关
$(function(){
    toggleBtnList();
	var isShow = '<%=request.getParameter("isShow")%>';
	if(isShow != "null") {
		var isShows = isShow.split(",");
		if(isShows.length < 1 || isShows[0] == 0) {
			$("#downloadBtn").hide();
		}
		if(isShows.length < 2 || isShows[1] == 0) {
			$("#importExcelForm").hide();
		}
		if(isShows.length < 3 || isShows[2] == 0) {
			$("#exportExcelBtn").hide();
		}
		if(isShows.length < 4 || isShows[3] == 0) {
			$("#importExcelTxt").hide();
		}
		if(isShows.length < 5 || isShows[4] == 0) {
			$("#exportTxtBtn").hide();
		}
	}
	$("#importBtnExcel").change(function(){
		importEvent("1");
        $(".btn-block-box").hide();
        flag=!flag;
	});
	$("#importBtnTxt").change(function(){
		importEvent("2");
        $(".btn-block-box").hide();
        flag=!flag;
	});
	$("#exportExcelBtn").click(function(){
		var exportParameter = JSON.stringify(topgetObjByObj($("#gridSearch .searchField")));
		console.log(exportParameter);
		$("#exportParameter").val(exportParameter);
		$("#exportType").val(1);
		checkExportFileDataNum();//如果是excle需要验证导出数量
// 		$("#exportForm").submit();
        $(".btn-block-box").hide();
        flag=!flag;
	})
	$("#exportTxtBtn").click(function(){
		var exportParameter = JSON.stringify(topgetObjByObj($("#gridSearch .searchField")));
		$("#exportParameter").val(exportParameter);
		$("#exportType").val(2);
		$("#exportForm").submit();
        $(".btn-block-box").hide();
        flag=!flag;
	})
	$("#downloadBtn").click(function(){
		$("#downloadForm").submit();
        $(".btn-block-box").hide();
        flag=!flag;
	})
})

var importEvent = function(type) {
	var formData = new FormData();
	formData.append("type", type);  
	if(type == "1") {
		formData.append("file", document.getElementById("importBtnExcel").files[0]); 
	} else if(type == "2") {
		formData.append("file", document.getElementById("importBtnTxt").files[0]); 
	}
	formData.append("entityName", '<%=request.getParameter("entityName")%>');
	formData.append("beanName", '<%=request.getParameter("beanName")%>');
	formData.append("fieldNums", '<%=request.getParameter("fieldNums")%>');
	$.ajax({
	    url: $.cxt + "/operationflow/import",
	    type: "POST",
	    dataType : "json",
	    data: formData,
	    /**
	    *必须false才会自动加上正确的Content-Type
	    */
	    contentType: false,
	    /**
	    * 必须false才会避开jQuery对 formdata 的默认处理
	    * XMLHttpRequest会对 formdata 进行正确的处理
	    */
	    processData: false,
	    success: function (data) {
	    	$("#importBtn").val("");
	    	var fun = '<%=request.getParameter("impCallbackFun")%>(' + JSON.stringify(data) + ')';
	    	if(fun != "null") {
	    		eval(fun);
	    		}
			},
	    error: function () {
	        alert("上传失败！");
	    }
	});
	cleanFileValue(type);
}

/**
 * 清空文件的值，解决在谷歌浏览器下如果不刷新页面第二次导入无效问题
 */
function cleanFileValue(type){
// 	$("#importBtnExcel").attr("title",Math.random());
	if(type == "1"){
		$("#importBtnExcel").val("");
	}else if(type == "2") {
		$("#importBtnTxt").val("");
	}
}


/**
 * 检查导出文件的数量
 * @param type
 * @param actionUrl
 */
function checkExportFileDataNum(){
	
	var  urlHead = "<%=request.getContextPath()%>";
	var  beanName = $("#beanName").val();
	var  entityName = $("#entityName").val();
	var  templateName = $("#templateName").val();
	var  fieldNums = $("#fieldNums").val();
	var  exportParameter = $("#exportParameter").val();
	var  exportType = $("#exportType").val();
	$.ajax({
		url : urlHead + "/operationflow/checkExportFileDataNum",
		data:{
			beanName:beanName,
			entityName:entityName,
			templateName:templateName,
			fieldNums:fieldNums,
			exportParameter:exportParameter,
			exportType:exportType,
		},
		type:'get',
		dataType:'text',
		success : function(data) {
			if(data=="1"){
				$("#exportForm").submit();
			}else{
				topshowAlertErrorDiv_dpi("excle导出失败,数据量过大,请小于1万条数据");
			}
		}
	});
}
function toggleBtnList(){
    $(".btn-block-toggle").on('click',function(){
        if(flag){
			$(".btn-block-box").hide();
        }else{
            $(".btn-block-box").show();
		}
		flag=!flag;
	})


}
</script>