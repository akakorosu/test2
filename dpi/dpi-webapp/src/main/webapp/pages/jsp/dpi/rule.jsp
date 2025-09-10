<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.bonc.com.cn/common/tag/cxt" prefix="cxt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<cxt:commonLinks />
</head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/common.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/frame.css" />
<body  style="background:white;overflow-y:scroll">
	<div class="page-content" style="padding-left:15px;">
		<div class="page-header" style="border:none;margin-bottom:0px;padding-bottom:10px;border-bottom: 1px dotted #e2e2e2;">
		<div class="col-sm-12 grid-full">
			<!-- 查询div -->
			<div class="grid-search-div" id="gridSearch">
				<input type="text" placeholder="域名编码" class="searchField input-md" id="domainCode" name="domainCode">
				<button class="btn btn-primary btn-sm" type="button" id="searchOrgList" style="height:30px;background:#23ae68!important; border-radius:3px!important; margin-left:5px;background-color:#de5741 !important;">
					<i class="ace-icon fa fa-search bigger-110"></i>
				</button>
				<button class="btn btn-primary btn-sm" type="button" id="createApplyBtn" style="margin-left:5px;height:30px; border-radius:3px;background-color:#10d228 !important;">
							<i class="fa fa-plus" aria-hidden="true"></i>
							新建
						</button>
						<button class="btn btn-primary btn-sm" id="import" type="button" style="margin-left:5px;height:30px; border-radius:3px;margin-top:2px;background-color:#108ad2 !important;">
							<i class="ace-icon bigger-130 fa fa-upload white" aria-hidden="true"></i>
							导入
						</button>
						<button class="btn btn-primary btn-sm" id="export" type="button" style="margin-left:5px;height:30px; border-radius:3px;margin-top:2px;background-color:#d15b47 !important;">
							<i class="ace-icon bigger-130 fa fa-download white" aria-hidden="true"></i>
							导出
						</button>
						<button class="btn btn-primary btn-sm" id="upload" type="button" style="margin-left:5px;height:30px; border-radius:3px;margin-top:2px;background-color:#11bbbb !important;">
							<i class="ace-icon bigger-130 fa fa-server white" aria-hidden="true"></i>
							上传
						</button>
						<button class="btn btn-primary btn-sm" id="merge" type="button" style="margin-left:5px;height:30px; border-radius:3px;margin-top:2px;background-color:#7f1dd8 !important;">
							<i class="ace-icon bigger-130 fa fa-adjust white" aria-hidden="true"></i>
							清洗合并
						</button>
						<button class="btn btn-primary btn-sm" id="store" type="button" style="margin-left:5px;height:30px; border-radius:3px;margin-top:2px;background-color:#7396ab !important;">
							<i class="ace-icon bigger-130 fa fa-database white" aria-hidden="true"></i>
							入库
						</button>
			</div>
			<!-- 列表div -->
			<div class="row" style="width: 102%;">
				<div class="col-xs-12" id="gridContainer" style="width: 102%;">
					<table id="grid-table"  style="width: 105%;"></table>
					<div id="grid-pager"></div>
				</div>
			</div>
		</div>
	</div>
	</div>							
</body>
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/rule.js"></script>
</html>