<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.bonc.com.cn/common/tag/cxt" prefix="cxt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;multipart/form-data; charset=UTF-8">
<cxt:commonLinks />
<link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/main.css">
<script src="<%=request.getContextPath()%>/resource/ace/js/jquery-ui.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/ace/css/dropzone.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/popBox.css">
<script src="<%=request.getContextPath()%>/resource/bonc/js/select-img.js"></script>
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/domainRule.js"></script>
<script src="<%=request.getContextPath()%>/resource/ace/js/dropzone.js"></script>

<style type="text/css">
</style>
</head>
<body  style="background:white;overflow-y:scroll">
	<div class="page-content" style="padding-left:15px;">
		<div class="page-header" style="border:none;margin-bottom:0px;padding-bottom:10px;border-bottom: 1px dotted #e2e2e2;">
			<span class="col-sm-12">
				<form class="form-inline">
				<div class="input-group">
					<input type="text" class="form-control search-query" style="border:1px solid #ccc;width:181.5px;padding-bottom:0;"  placeholder="域名名称" id="appName" />
					<span class="input-group-btn">
						<button type="button" id="searchProcessList" class="btn btn-primary btn-sm" style="height:30px;background:#23ae68!important; border-radius:3px!important; margin-left:5px;background-color:#de5741 !important;">
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
					</span>
				</div>
			</form>
			</span>
			<div>
			<div style="clear:both;"></div>
		</div>
			<div style="clear:both;"></div>
		</div>
		<!-- /.page-header -->
		<div class="row" style="width: 102%;">
			<div class="col-xs-12" id="gridContainer" style="width: 102%;">
				<table id="grid-table" style="width: 105%;"></table>
				<div id="grid-pager"></div>
			</div>
		</div>
	</div>
	<!-- 确认模态框 -->
	<div class="modal fade" id="confirmModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<i class="confirm-close fa fa-times"></i>
					</button>
					<h4 class="modal-title">确定删除么？</h4>
				</div>
				<div class="modal-body clearfix" style="margin:0 auto;border-radius:0px 0px 8px 8px;">
				<div style="width:200px;margin:0 auto;">
					<button class=" btn  pull-left btn-primary pri" style="width:40%;"  id="confirmBtnModal">确定</button>&nbsp;&nbsp;
					<button class=" btn  pull-right btn-warning war" style="width:40%;"  id="cancelBtnModal">取消</button>
				</div>
				</div>
			</div>
		</div>
	</div>
	
		<div  class="modal fade" id="modal-app" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog create-user-dialog" style="width:590px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" id="closeButton">
							<i class="confirm-close fa fa-times"></i>
						</button>
						<h4 class="modal-title">
							<i class="ace-icon fa fa-pencil-square-o"></i>
							编辑域名信息
						</h4>
					</div>
					<div class="modal-body" style="border-radius:0px 0px 8px 8px;">
						<div class="signup-box widget-box no-border visible" id="signup-box">
							<div class="widget-body">
								<div class="widget-main">
									
									<form id="appForm">
									<div style="float:left;width:100%;">
										<input type="hidden" name="domainCode" id="domainCode">
										<label class="block clearfix form-group">
											<span class="block input-icon input-icon-right">
												<input type="text" placeholder="域名级别" class="form-control" name="domainLevel" id="domainLevel">
												<i class="ace-icon fa fa-info"></i>
											</span>
										</label>
										<label class="clearfix form-group app-only" style="width:100%;">
											<span class="block input-icon input-icon-right">
												<select class="chosen-select" id="isDomainSon" name="isDomainSon" data-placeholder="是否有子域名">
													<option value></option>
													<option value="0">否</option>
													<option value="1">是</option>
												</select>
											</span>
										</label>
										<label class="clearfix form-group app-only" style="width:100%;">
											<span class="block input-icon input-icon-right">
												<select class="chosen-select" id="isNoise" name="isNoise" data-placeholder="是否是噪音">
													<option value></option>
													<option value="0">否</option>
													<option value="1">是</option>
												</select>
											</span>
										</label>
										<label class="block clearfix form-group">
											<span class="block input-icon input-icon-right">
												<input type="text" placeholder="产品编码" class="form-control" name="prodId" id="prodId">
												<i class="ace-icon fa fa-info"></i>
											</span>
										</label>
										<label class="block clearfix form-group">
											<span class="block input-icon input-icon-right">
												<input type="text" placeholder="父域名" class="form-control" name="fatherDomain" id="fatherDomain">
												<i class="ace-icon fa fa-info"></i>
											</span>
										</label>
										<label class="block clearfix form-group">
											<span class="block input-icon input-icon-right">
												<input type="text" placeholder="根域名" class="form-control" name="rootDomain" id="rootDomain">
												<i class="ace-icon fa fa-info"></i>
											</span>
										</label>
										<label class="clearfix form-group app-only" style="width:100%;">
											<span class="block input-icon input-icon-right">
												<select class="chosen-select" id="isValid" name="isValid" data-placeholder="是否有效">
													<option value></option>
													<option value="0">否</option>
													<option value="1">是</option>
												</select>
											</span>
										</label>
										<label class="clearfix form-group app-only" style="width:100%;">
											<span class="block input-icon input-icon-right">
												<select class="chosen-select" id="isRedis" name="isRedis" data-placeholder="是否导入Redis">
													<option value></option>
													<option value="0">否</option>
													<option value="1">是</option>
												</select>
											</span>
										</label>
										
										
										
										<div class="space-12"></div>
										<div class="clearfix" style="width: 40%;margin: 0 auto;">
											<button class="width-45 pull-left btn btn-sm btn-default" type="reset" id="resetButton">
												<i class="ace-icon fa fa-refresh"></i>
												<span class="bigger-110">重置</span>
											</button>
											<button class="width-45 pull-right btn btn-sm btn-info" id="submitBtn" type="submit">
												<span class="bigger-110">保存</span>
												<i class="ace-icon fa fa-arrow-right icon-on-right"></i>
											</button>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
</body>
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/placeHolder.js"></script>
<script src="<%=request.getContextPath()%>/resource/ace/js/jquery-ui.js"></script>
<script>
	 $(".modal-content" ).draggable({cursor:"move"});
</script>
</html>
