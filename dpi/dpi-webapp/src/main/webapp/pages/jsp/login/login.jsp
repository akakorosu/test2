<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.bonc.com.cn/common/tag/cxt" prefix="cxt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<cxt:commonLinks />
<link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/login/login.css" />
<script type="text/javascript" src="login.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resource/bonc/js/md5.js"></script>
<title>用户上网行为分析运营平台</title>
</head>
<body>
	<div class="left clearfix" >
		<div class="leftBg mainCon">
			<div class="login-left">
				<div style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;z-index:0;">
					<img src="../../images/login/loginBg1.png" style="width:100%;height:100%;">
				</div>
			</div>
		</div>
		<div class="mainCon rightBg">
			<div class="login-box">
				<div class="login-title text-center">
					<h1>
						<small>
							<img src="../../images/login/dl.png">
						</small>
					</h1>
				</div>
				<div class="login-content ">
					<div class="form">
						<div id="errorMsg" />
						<form id="loginForm" method="post" autocomplete="off">
							<div class="form-group">
								<div>
									<div class="input-group">
										<span class="input-group-addon">
											<img src="../../images/login/ad.png">
										</span>
										</span>
										<input type="text" id="username" name="username" style="border: none" class="form-control" placeholder="用户名">
									</div>
								</div>
							</div>
							<div class="form-group">
								<div>
									<div class="input-group">
										<span class="input-group-addon">
											<img src="../../images/login/pw.png">
										</span>
										<input type="password" id="password" style="border: none" name="password" class="form-control" placeholder="密码">
									</div>
								</div>
							</div>
							<div class="form-group form-actions">
								<div >
									<!-- <button class="btn btn-block btn-success" onclick="doLogin()">登录</button> -->
									<button class="widb" type="submit" class="btn btn-block btn-primary" id="submitBtn">登&nbsp;&nbsp;录</button>
									<button class="fabuSyncPositionEventId">测试 IOP-实时短信</button>
									<script >
										$('.fabuSyncPositionEventId').click(()=>{
											$.ajax({
												url : $.cxt + '/nologin/test/fabuSyncPositionEventId',
												type : "POST",
												async : false,
												success : function(data) {
													console.log(data)
												}
											});
										})
									</script>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="chooseSysRoleUserFullBackground" class="fullBackground" style="z-index: 1000;display: none;"></div>
	<div id="chooseSysRoleUser">
		<div class="mailtop"></div>
		<div class="align-center">
			请选择角色<i class="fa fa-check red"></i>
		</div>
	</div>
</body>
</html>