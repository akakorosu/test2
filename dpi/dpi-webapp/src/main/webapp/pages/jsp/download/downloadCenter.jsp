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
<script src="<%=request.getContextPath()%>/pages/jsp/download/downloadCenter.js"></script>
<script src="<%=request.getContextPath()%>/resource/ace/js/dropzone.js"></script>

<style type="text/css">
</style>
</head>
<body  style="background:white;overflow-y:scroll">
	<div class="page-content" style="padding-left:15px;">
		<div class="page-header">
			<h1>
				下载中心维护
				<small>
					<i class="ace-icon fa fa-angle-double-right"></i>
					上传与下载文件
				</small>
				<button class="btn btn-primary btn-sm" id="import" type="button" style="margin-left:5px;height:30px; border-radius:3px;margin-top:2px;">
					<img src="../../img/sc.png" />
					上传
				</button>
			</h1>
		</div>

		<div class="row">
			<div class="col-xs-12">
				<div>
					<ul class="ace-thumbnails clearfix" id="downloadUl">

					</ul>
				</div>
			</div>
		</div>
	</div>
	<form id="form1" method="post">
	</form>
	<!-- 确认模态框 -->
	<div class="modal fade" id="confirmModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel3" aria-hidden="true">
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
	
	<div  class="modal fade" id="modal-ftp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true">
			<div class="modal-dialog create-user-dialog" style="width:590px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<i class="confirm-close fa fa-times"></i>
						</button>
						<h4 class="modal-title">
							<i class="ace-icon fa fa-pencil-square-o"></i>
							文件上传服务器
						</h4>
					</div>
					<div class="modal-body" style="border-radius:0px 0px 8px 8px;">
						<div class="signup-box widget-box no-border visible">
							<div class="widget-body">
								<div class="widget-main">
									
									<form id="ftpForm">
									<div style="float:left;width:100%;">
										<input type="hidden" name="ftpUrl" id="ftpUrl">
										<label class="block clearfix form-group">
											<span class="block input-icon input-icon-right">
												<input type="text" placeholder="服务器地址" class="form-control" name="ftpAddress" id="ftpAddress">
												<i class="ace-icon fa fa-info"></i>
											</span>
										</label>
										<label class="block clearfix form-group">
											<span class="block input-icon input-icon-right">
												<input type="text" placeholder="服务器端口" class="form-control" name="ftpPort" id="ftpPort">
												<i class="ace-icon fa fa-info"></i>
											</span>
										</label>
										<label class="block clearfix form-group">
											<span class="block input-icon input-icon-right">
												<input type="text" placeholder="服务器用户" class="form-control" name="ftpUser" id="ftpUser">
												<i class="ace-icon fa fa-info"></i>
											</span>
										</label>
										<label class="block clearfix form-group">
											<span class="block input-icon input-icon-right">
												<input type="text" placeholder="服务器密码" class="form-control" name="ftpPassword" id="ftpPassword">
												<i class="ace-icon fa fa-info"></i>
											</span>
										</label>
										<label class="block clearfix form-group">
											<span class="block input-icon input-icon-right">
												<input type="text" placeholder="服务器路径" class="form-control" name="ftpPath" id="ftpPath">
												<i class="ace-icon fa fa-info"></i>
											</span>
										</label>
										
										
										
										<div class="space-12"></div>
										<div class="clearfix" style="width: 40%;margin: 0 auto;">
											<button class="width-45 pull-left btn btn-sm btn-default" type="reset" id="ftpResetButton">
												<i class="ace-icon fa fa-refresh"></i>
												<span class="bigger-110">重置</span>
											</button>
											<button class="width-45 pull-right btn btn-sm btn-info" id="ftpSubmitBtn" type="submit">
												<span class="bigger-110">确定</span>
												<i class="ace-icon fa fa-arrow-right icon-on-right"></i>
											</button>
										</div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div  class="modal fade" id="modal-database" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true">
			<div class="modal-dialog create-user-dialog" style="width:590px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<i class="confirm-close fa fa-times"></i>
						</button>
						<h4 class="modal-title">
							<i class="ace-icon fa fa-pencil-square-o"></i>
							文件上传服务器
						</h4>
					</div>
					<div class="modal-body" style="border-radius:0px 0px 8px 8px;">
						<div class="signup-box widget-box no-border visible">
							<div class="widget-body">
								<div class="widget-main">
									
									<form id="databaseForm">
									<div style="float:left;width:100%;">
										<input type="hidden" name="databaseUrl" id="databaseUrl">
										<label class="block clearfix form-group">
											<span class="block input-icon input-icon-right">
												<input type="text" placeholder="数据库地址" class="form-control" name="databaseAddress" id="databaseAddress">
												<i class="ace-icon fa fa-info"></i>
											</span>
										</label>
										<label class="block clearfix form-group">
											<span class="block input-icon input-icon-right">
												<input type="text" placeholder="数据库端口" class="form-control" name="databasePort" id="databasePort">
												<i class="ace-icon fa fa-info"></i>
											</span>
										</label>
										<label class="block clearfix form-group">
											<span class="block input-icon input-icon-right">
												<input type="text" placeholder="数据库用户" class="form-control" name="databaseUser" id="databaseUser">
												<i class="ace-icon fa fa-info"></i>
											</span>
										</label>
										<label class="block clearfix form-group">
											<span class="block input-icon input-icon-right">
												<input type="text" placeholder="数据库密码" class="form-control" name="databasePassword" id="databasePassword">
												<i class="ace-icon fa fa-info"></i>
											</span>
										</label>
										<label class="block clearfix form-group">
											<span class="block input-icon input-icon-right">
												<input type="text" placeholder="数据库表名" class="form-control" name="databaseTable" id="databaseTable">
												<i class="ace-icon fa fa-info"></i>
											</span>
										</label>
										
										
										
										<div class="space-12"></div>
										<div class="clearfix" style="width: 40%;margin: 0 auto;">
											<button class="width-45 pull-left btn btn-sm btn-default" type="reset" id="databaseResetButton">
												<i class="ace-icon fa fa-refresh"></i>
												<span class="bigger-110">重置</span>
											</button>
											<button class="width-45 pull-right btn btn-sm btn-info" id="databaseSubmitBtn" type="submit">
												<span class="bigger-110">确定</span>
												<i class="ace-icon fa fa-arrow-right icon-on-right"></i>
											</button>
										</div>
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
