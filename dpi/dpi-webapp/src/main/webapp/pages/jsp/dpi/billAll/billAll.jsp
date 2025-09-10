<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.bonc.com.cn/common/tag/cxt" prefix="cxt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<cxt:commonLinks />
<title>全部榜单</title>
<script src="<%=request.getContextPath()%>/pages/js/jquery-3.1.1.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/bootstrap-table.min.css" />
<script src="<%=request.getContextPath()%>/pages/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/pages/js/bootstrap-table.min.js"></script>
<script src="<%=request.getContextPath()%>/pages/js/bootstrap-table-zh-CN.min.js"></script>
<link rel="stylesheet" href="billAll.css" />
</head>
<body>
   <ul id="myTab" class="nav nav-tabs">
			<li class="active">
				<a href="#tab-1" data-toggle="tab">电影</a>
			</li>
			<li>
				<a href="#tab-2" data-toggle="tab">电视剧</a>
			</li>
			<li>
				<a href="#tab-3" data-toggle="tab">综艺</a>
			</li>
			<li>
				<a href="#tab-4" data-toggle="tab">动漫</a>
			</li>
			<li>
				<a href="#tab-5" data-toggle="tab">少儿</a>
			</li>
			<li>
				<a href="#tab-6" data-toggle="tab">纪录片</a>
			</li>
		</ul>
		<div id="myTabContent" class="tab-content" style="margin-top:10px;">
			<div class="tab-pane fade in active nav-column" id="tab-1">
				<ul id="myTab-film" class="nav nav-tabs">
					<li >
						<a href="#tab-film-1" data-toggle="tab">电影榜单首页</a>
					</li>
					<li>
						<a href="#tab-film-2" data-toggle="tab">爱情电影</a>
					</li>
					<li>
						<a href="#tab-film-3" data-toggle="tab">喜剧电影</a>
					</li>
					<li>
						<a href="#tab-film-4" data-toggle="tab">惊悚电影</a>
					</li>
					<li>
						<a href="#tab-film-5" data-toggle="tab">科幻电影</a>
					</li>
					<li class="active">
						<a href="#tab-film-6" data-toggle="tab">剧情电影</a>
					</li>
				</ul>
				<div id="myTabContent-film" class="tab-content tab-content-column">
					<div class="tab-pane fade " id="tab-film-1">
						<div class="bill-list">
							<div class="list-title">
								全部电影 <span>更多</span>
							</div>
							<div class="clearfix" style="background:#f7f7f7;">
								<span class="tab-list-show-1">排名</span>
								<span class="tab-list-show-2">关键词</span>
								<span class="tab-list-show-3">搜索指数</span>
								<span class="tab-list-show-4"></span>
							</div>
							<div class="tab-list active">
								<div class="tab-list-show">
									<span class="tab-list-show-1"><label>1</label></span>
									<span class="tab-list-show-2">复仇者联盟3：复仇者联盟3复仇者联盟3</span>
									<span class="tab-list-show-3">666663</span>
									<span class="tab-list-show-4"><img src="<%=request.getContextPath()%>/pages/images/new/u3581.png" /></span>
								</div>
								<div class="tab-list-hide">
									<div class="tab-list-hide-left"><img src="<%=request.getContextPath()%>/pages/images/new/u3607.jpg" /></div>
									<div class="tab-list-hide-right">
										《末日之战》改编自迈克斯·布鲁克斯的同名科幻小说，是由马克·福斯特执导，布拉德·皮特、米瑞·伊诺丝主演的一部惊悚科幻片.影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的
									</div>
								</div>
							</div>
							<div class="tab-list">
								<div class="tab-list-show">
									<span class="tab-list-show-1"><label>2</label></span>
									<span class="tab-list-show-2">复仇者联盟3：复仇者联盟3复仇者联盟3</span>
									<span class="tab-list-show-3">666663</span>
									<span class="tab-list-show-4"><img src="<%=request.getContextPath()%>/pages/images/new/u3581.png" /></span>
								</div>
								<div class="tab-list-hide">
									<div class="tab-list-hide-left"><img src="<%=request.getContextPath()%>/pages/images/new/u3607.jpg" /></div>
									<div class="tab-list-hide-right">
										《末日之战》改编自迈克斯·布鲁克斯的同名科幻小说，是由马克·福斯特执导，布拉德·皮特、米瑞·伊诺丝主演的一部惊悚科幻片.影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的
									</div>
								</div>
							</div>
							<div class="tab-list">
								<div class="tab-list-show">
									<span class="tab-list-show-1"><label>3</label></span>
									<span class="tab-list-show-2">复仇者联盟3：复仇者联盟3复仇者联盟3</span>
									<span class="tab-list-show-3">666663</span>
									<span class="tab-list-show-4"><img src="<%=request.getContextPath()%>/pages/images/new/u3581.png" /></span>
								</div>
								<div class="tab-list-hide">
									<div class="tab-list-hide-left"><img src="<%=request.getContextPath()%>/pages/images/new/u3607.jpg" /></div>
									<div class="tab-list-hide-right">
										《末日之战》改编自迈克斯·布鲁克斯的同名科幻小说，是由马克·福斯特执导，布拉德·皮特、米瑞·伊诺丝主演的一部惊悚科幻片.影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的
									</div>
								</div>
							</div>
							<div class="tab-list">
								<div class="tab-list-show">
									<span class="tab-list-show-1"><label>4</label></span>
									<span class="tab-list-show-2">复仇者联盟3：复仇者联盟3复仇者联盟3</span>
									<span class="tab-list-show-3">666663</span>
									<span class="tab-list-show-4"><img src="<%=request.getContextPath()%>/pages/images/new/u3581.png" /></span>
								</div>
								<div class="tab-list-hide">
									<div class="tab-list-hide-left"><img src="<%=request.getContextPath()%>/pages/images/new/u3607.jpg" /></div>
									<div class="tab-list-hide-right">
										《末日之战》改编自迈克斯·布鲁克斯的同名科幻小说，是由马克·福斯特执导，布拉德·皮特、米瑞·伊诺丝主演的一部惊悚科幻片.影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的
									</div>
								</div>
							</div>
							<div class="tab-list">
								<div class="tab-list-show">
									<span class="tab-list-show-1"><label>5</label></span>
									<span class="tab-list-show-2">复仇者联盟3：复仇者联盟3复仇者联盟3</span>
									<span class="tab-list-show-3">666663</span>
									<span class="tab-list-show-4"><img src="<%=request.getContextPath()%>/pages/images/new/u3581.png" /></span>
								</div>
								<div class="tab-list-hide">
									<div class="tab-list-hide-left"><img src="<%=request.getContextPath()%>/pages/images/new/u3607.jpg" /></div>
									<div class="tab-list-hide-right">
										《末日之战》改编自迈克斯·布鲁克斯的同名科幻小说，是由马克·福斯特执导，布拉德·皮特、米瑞·伊诺丝主演的一部惊悚科幻片.影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的
									</div>
								</div>
							</div>
							<div class="tab-list">
								<div class="tab-list-show">
									<span class="tab-list-show-1"><label>6</label></span>
									<span class="tab-list-show-2">复仇者联盟3：复仇者联盟3复仇者联盟3</span>
									<span class="tab-list-show-3">666663</span>
									<span class="tab-list-show-4"><img src="<%=request.getContextPath()%>/pages/images/new/u3581.png" /></span>
								</div>
								<div class="tab-list-hide">
									<div class="tab-list-hide-left"><img src="<%=request.getContextPath()%>/pages/images/new/u3607.jpg" /></div>
									<div class="tab-list-hide-right">
										《末日之战》改编自迈克斯·布鲁克斯的同名科幻小说，是由马克·福斯特执导，布拉德·皮特、米瑞·伊诺丝主演的一部惊悚科幻片.影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的
									</div>
								</div>
							</div>
							<div class="tab-list">
								<div class="tab-list-show">
									<span class="tab-list-show-1"><label>7</label></span>
									<span class="tab-list-show-2">复仇者联盟3：复仇者联盟3复仇者联盟3</span>
									<span class="tab-list-show-3">666663</span>
									<span class="tab-list-show-4"><img src="<%=request.getContextPath()%>/pages/images/new/u3581.png" /></span>
								</div>
								<div class="tab-list-hide">
									<div class="tab-list-hide-left"><img src="<%=request.getContextPath()%>/pages/images/new/u3607.jpg" /></div>
									<div class="tab-list-hide-right">
										《末日之战》改编自迈克斯·布鲁克斯的同名科幻小说，是由马克·福斯特执导，布拉德·皮特、米瑞·伊诺丝主演的一部惊悚科幻片.影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的
									</div>
								</div>
							</div>
							<div class="tab-list">
								<div class="tab-list-show">
									<span class="tab-list-show-1"><label>8</label></span>
									<span class="tab-list-show-2">复仇者联盟3：复仇者联盟3复仇者联盟3</span>
									<span class="tab-list-show-3">666663</span>
									<span class="tab-list-show-4"><img src="<%=request.getContextPath()%>/pages/images/new/u3581.png" /></span>
								</div>
								<div class="tab-list-hide">
									<div class="tab-list-hide-left"><img src="<%=request.getContextPath()%>/pages/images/new/u3607.jpg" /></div>
									<div class="tab-list-hide-right">
										《末日之战》改编自迈克斯·布鲁克斯的同名科幻小说，是由马克·福斯特执导，布拉德·皮特、米瑞·伊诺丝主演的一部惊悚科幻片.影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的
									</div>
								</div>
							</div>
							<div class="tab-list">
								<div class="tab-list-show">
									<span class="tab-list-show-1"><label>9</label></span>
									<span class="tab-list-show-2">复仇者联盟3：复仇者联盟3复仇者联盟3</span>
									<span class="tab-list-show-3">666663</span>
									<span class="tab-list-show-4"><img src="<%=request.getContextPath()%>/pages/images/new/u3581.png" /></span>
								</div>
								<div class="tab-list-hide">
									<div class="tab-list-hide-left"><img src="<%=request.getContextPath()%>/pages/images/new/u3607.jpg" /></div>
									<div class="tab-list-hide-right">
										《末日之战》改编自迈克斯·布鲁克斯的同名科幻小说，是由马克·福斯特执导，布拉德·皮特、米瑞·伊诺丝主演的一部惊悚科幻片.影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的
									</div>
								</div>
							</div>
							<div class="tab-list">
								<div class="tab-list-show">
									<span class="tab-list-show-1"><label>10</label></span>
									<span class="tab-list-show-2">复仇者联盟3：复仇者联盟3复仇者联盟3</span>
									<span class="tab-list-show-3">666663</span>
									<span class="tab-list-show-4"><img src="<%=request.getContextPath()%>/pages/images/new/u3581.png" /></span>
								</div>
								<div class="tab-list-hide">
									<div class="tab-list-hide-left"><img src="<%=request.getContextPath()%>/pages/images/new/u3607.jpg" /></div>
									<div class="tab-list-hide-right">
										《末日之战》改编自迈克斯·布鲁克斯的同名科幻小说，是由马克·福斯特执导，布拉德·皮特、米瑞·伊诺丝主演的一部惊悚科幻片.影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的
									</div>
								</div>
							</div>
						</div>
                        <div class="bill-list">
							<div class="list-title">
								全部电影 <span>更多</span>
							</div>
							<div class="clearfix" style="background:#f7f7f7;">
								<span class="tab-list-show-1">排名</span>
								<span class="tab-list-show-2">关键词</span>
								<span class="tab-list-show-3">搜索指数</span>
								<span class="tab-list-show-4"></span>
							</div>
							<div class="tab-list active">
								<div class="tab-list-show">
									<span class="tab-list-show-1"><label>1</label></span>
									<span class="tab-list-show-2">复仇者联盟3：复仇者联盟3复仇者联盟3</span>
									<span class="tab-list-show-3">666663</span>
									<span class="tab-list-show-4"><img src="<%=request.getContextPath()%>/pages/images/new/u3581.png" /></span>
								</div>
								<div class="tab-list-hide">
									<div class="tab-list-hide-left"><img src="<%=request.getContextPath()%>/pages/images/new/u3607.jpg" /></div>
									<div class="tab-list-hide-right">
										《末日之战》改编自迈克斯·布鲁克斯的同名科幻小说，是由马克·福斯特执导，布拉德·皮特、米瑞·伊诺丝主演的一部惊悚科幻片.影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的
									</div>
								</div>
							</div>
							<div class="tab-list">
								<div class="tab-list-show">
									<span class="tab-list-show-1"><label>2</label></span>
									<span class="tab-list-show-2">复仇者联盟3：复仇者联盟3复仇者联盟3</span>
									<span class="tab-list-show-3">666663</span>
									<span class="tab-list-show-4"><img src="<%=request.getContextPath()%>/pages/images/new/u3581.png" /></span>
								</div>
								<div class="tab-list-hide">
									<div class="tab-list-hide-left"><img src="<%=request.getContextPath()%>/pages/images/new/u3607.jpg" /></div>
									<div class="tab-list-hide-right">
										《末日之战》改编自迈克斯·布鲁克斯的同名科幻小说，是由马克·福斯特执导，布拉德·皮特、米瑞·伊诺丝主演的一部惊悚科幻片.影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的
									</div>
								</div>
							</div>
							<div class="tab-list">
								<div class="tab-list-show">
									<span class="tab-list-show-1"><label>3</label></span>
									<span class="tab-list-show-2">复仇者联盟3：复仇者联盟3复仇者联盟3</span>
									<span class="tab-list-show-3">666663</span>
									<span class="tab-list-show-4"><img src="<%=request.getContextPath()%>/pages/images/new/u3581.png" /></span>
								</div>
								<div class="tab-list-hide">
									<div class="tab-list-hide-left"><img src="<%=request.getContextPath()%>/pages/images/new/u3607.jpg" /></div>
									<div class="tab-list-hide-right">
										《末日之战》改编自迈克斯·布鲁克斯的同名科幻小说，是由马克·福斯特执导，布拉德·皮特、米瑞·伊诺丝主演的一部惊悚科幻片.影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的
									</div>
								</div>
							</div>
							<div class="tab-list">
								<div class="tab-list-show">
									<span class="tab-list-show-1"><label>4</label></span>
									<span class="tab-list-show-2">复仇者联盟3：复仇者联盟3复仇者联盟3</span>
									<span class="tab-list-show-3">666663</span>
									<span class="tab-list-show-4"><img src="<%=request.getContextPath()%>/pages/images/new/u3581.png" /></span>
								</div>
								<div class="tab-list-hide">
									<div class="tab-list-hide-left"><img src="<%=request.getContextPath()%>/pages/images/new/u3607.jpg" /></div>
									<div class="tab-list-hide-right">
										《末日之战》改编自迈克斯·布鲁克斯的同名科幻小说，是由马克·福斯特执导，布拉德·皮特、米瑞·伊诺丝主演的一部惊悚科幻片.影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的
									</div>
								</div>
							</div>
							<div class="tab-list">
								<div class="tab-list-show">
									<span class="tab-list-show-1"><label>5</label></span>
									<span class="tab-list-show-2">复仇者联盟3：复仇者联盟3复仇者联盟3</span>
									<span class="tab-list-show-3">666663</span>
									<span class="tab-list-show-4"><img src="<%=request.getContextPath()%>/pages/images/new/u3581.png" /></span>
								</div>
								<div class="tab-list-hide">
									<div class="tab-list-hide-left"><img src="<%=request.getContextPath()%>/pages/images/new/u3607.jpg" /></div>
									<div class="tab-list-hide-right">
										《末日之战》改编自迈克斯·布鲁克斯的同名科幻小说，是由马克·福斯特执导，布拉德·皮特、米瑞·伊诺丝主演的一部惊悚科幻片.影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的
									</div>
								</div>
							</div>
							<div class="tab-list">
								<div class="tab-list-show">
									<span class="tab-list-show-1"><label>6</label></span>
									<span class="tab-list-show-2">复仇者联盟3：复仇者联盟3复仇者联盟3</span>
									<span class="tab-list-show-3">666663</span>
									<span class="tab-list-show-4"><img src="<%=request.getContextPath()%>/pages/images/new/u3581.png" /></span>
								</div>
								<div class="tab-list-hide">
									<div class="tab-list-hide-left"><img src="<%=request.getContextPath()%>/pages/images/new/u3607.jpg" /></div>
									<div class="tab-list-hide-right">
										《末日之战》改编自迈克斯·布鲁克斯的同名科幻小说，是由马克·福斯特执导，布拉德·皮特、米瑞·伊诺丝主演的一部惊悚科幻片.影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的
									</div>
								</div>
							</div>
							<div class="tab-list">
								<div class="tab-list-show">
									<span class="tab-list-show-1"><label>7</label></span>
									<span class="tab-list-show-2">复仇者联盟3：复仇者联盟3复仇者联盟3</span>
									<span class="tab-list-show-3">666663</span>
									<span class="tab-list-show-4"><img src="<%=request.getContextPath()%>/pages/images/new/u3581.png" /></span>
								</div>
								<div class="tab-list-hide">
									<div class="tab-list-hide-left"><img src="<%=request.getContextPath()%>/pages/images/new/u3607.jpg" /></div>
									<div class="tab-list-hide-right">
										《末日之战》改编自迈克斯·布鲁克斯的同名科幻小说，是由马克·福斯特执导，布拉德·皮特、米瑞·伊诺丝主演的一部惊悚科幻片.影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的
									</div>
								</div>
							</div>
							<div class="tab-list">
								<div class="tab-list-show">
									<span class="tab-list-show-1"><label>8</label></span>
									<span class="tab-list-show-2">复仇者联盟3：复仇者联盟3复仇者联盟3</span>
									<span class="tab-list-show-3">666663</span>
									<span class="tab-list-show-4"><img src="<%=request.getContextPath()%>/pages/images/new/u3581.png" /></span>
								</div>
								<div class="tab-list-hide">
									<div class="tab-list-hide-left"><img src="<%=request.getContextPath()%>/pages/images/new/u3607.jpg" /></div>
									<div class="tab-list-hide-right">
										《末日之战》改编自迈克斯·布鲁克斯的同名科幻小说，是由马克·福斯特执导，布拉德·皮特、米瑞·伊诺丝主演的一部惊悚科幻片.影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的
									</div>
								</div>
							</div>
							<div class="tab-list">
								<div class="tab-list-show">
									<span class="tab-list-show-1"><label>9</label></span>
									<span class="tab-list-show-2">复仇者联盟3：复仇者联盟3复仇者联盟3</span>
									<span class="tab-list-show-3">666663</span>
									<span class="tab-list-show-4"><img src="<%=request.getContextPath()%>/pages/images/new/u3581.png" /></span>
								</div>
								<div class="tab-list-hide">
									<div class="tab-list-hide-left"><img src="<%=request.getContextPath()%>/pages/images/new/u3607.jpg" /></div>
									<div class="tab-list-hide-right">
										《末日之战》改编自迈克斯·布鲁克斯的同名科幻小说，是由马克·福斯特执导，布拉德·皮特、米瑞·伊诺丝主演的一部惊悚科幻片.影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的
									</div>
								</div>
							</div>
							<div class="tab-list">
								<div class="tab-list-show">
									<span class="tab-list-show-1"><label>10</label></span>
									<span class="tab-list-show-2">复仇者联盟3：复仇者联盟3复仇者联盟3</span>
									<span class="tab-list-show-3">666663</span>
									<span class="tab-list-show-4"><img src="<%=request.getContextPath()%>/pages/images/new/u3581.png" /></span>
								</div>
								<div class="tab-list-hide">
									<div class="tab-list-hide-left"><img src="<%=request.getContextPath()%>/pages/images/new/u3607.jpg" /></div>
									<div class="tab-list-hide-right">
										《末日之战》改编自迈克斯·布鲁克斯的同名科幻小说，是由马克·福斯特执导，布拉德·皮特、米瑞·伊诺丝主演的一部惊悚科幻片.影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的
									</div>
								</div>
							</div>
						</div>
					    <div class="bill-list">
							<div class="list-title">
								全部电影 <span>更多</span>
							</div>
							<div class="clearfix" style="background:#f7f7f7;">
								<span class="tab-list-show-1">排名</span>
								<span class="tab-list-show-2">关键词</span>
								<span class="tab-list-show-3">搜索指数</span>
								<span class="tab-list-show-4"></span>
							</div>
							<div class="tab-list active">
								<div class="tab-list-show">
									<span class="tab-list-show-1"><label>1</label></span>
									<span class="tab-list-show-2">复仇者联盟3：复仇者联盟3复仇者联盟3</span>
									<span class="tab-list-show-3">666663</span>
									<span class="tab-list-show-4"><img src="<%=request.getContextPath()%>/pages/images/new/u3581.png" /></span>
								</div>
								<div class="tab-list-hide">
									<div class="tab-list-hide-left"><img src="<%=request.getContextPath()%>/pages/images/new/u3607.jpg" /></div>
									<div class="tab-list-hide-right">
										《末日之战》改编自迈克斯·布鲁克斯的同名科幻小说，是由马克·福斯特执导，布拉德·皮特、米瑞·伊诺丝主演的一部惊悚科幻片.影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的
									</div>
								</div>
							</div>
							<div class="tab-list">
								<div class="tab-list-show">
									<span class="tab-list-show-1"><label>2</label></span>
									<span class="tab-list-show-2">复仇者联盟3：复仇者联盟3复仇者联盟3</span>
									<span class="tab-list-show-3">666663</span>
									<span class="tab-list-show-4"><img src="<%=request.getContextPath()%>/pages/images/new/u3581.png" /></span>
								</div>
								<div class="tab-list-hide">
									<div class="tab-list-hide-left"><img src="<%=request.getContextPath()%>/pages/images/new/u3607.jpg" /></div>
									<div class="tab-list-hide-right">
										《末日之战》改编自迈克斯·布鲁克斯的同名科幻小说，是由马克·福斯特执导，布拉德·皮特、米瑞·伊诺丝主演的一部惊悚科幻片.影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的
									</div>
								</div>
							</div>
							<div class="tab-list">
								<div class="tab-list-show">
									<span class="tab-list-show-1"><label>3</label></span>
									<span class="tab-list-show-2">复仇者联盟3：复仇者联盟3复仇者联盟3</span>
									<span class="tab-list-show-3">666663</span>
									<span class="tab-list-show-4"><img src="<%=request.getContextPath()%>/pages/images/new/u3581.png" /></span>
								</div>
								<div class="tab-list-hide">
									<div class="tab-list-hide-left"><img src="<%=request.getContextPath()%>/pages/images/new/u3607.jpg" /></div>
									<div class="tab-list-hide-right">
										《末日之战》改编自迈克斯·布鲁克斯的同名科幻小说，是由马克·福斯特执导，布拉德·皮特、米瑞·伊诺丝主演的一部惊悚科幻片.影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的
									</div>
								</div>
							</div>
							<div class="tab-list">
								<div class="tab-list-show">
									<span class="tab-list-show-1"><label>4</label></span>
									<span class="tab-list-show-2">复仇者联盟3：复仇者联盟3复仇者联盟3</span>
									<span class="tab-list-show-3">666663</span>
									<span class="tab-list-show-4"><img src="<%=request.getContextPath()%>/pages/images/new/u3581.png" /></span>
								</div>
								<div class="tab-list-hide">
									<div class="tab-list-hide-left"><img src="<%=request.getContextPath()%>/pages/images/new/u3607.jpg" /></div>
									<div class="tab-list-hide-right">
										《末日之战》改编自迈克斯·布鲁克斯的同名科幻小说，是由马克·福斯特执导，布拉德·皮特、米瑞·伊诺丝主演的一部惊悚科幻片.影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的
									</div>
								</div>
							</div>
							<div class="tab-list">
								<div class="tab-list-show">
									<span class="tab-list-show-1"><label>5</label></span>
									<span class="tab-list-show-2">复仇者联盟3：复仇者联盟3复仇者联盟3</span>
									<span class="tab-list-show-3">666663</span>
									<span class="tab-list-show-4"><img src="<%=request.getContextPath()%>/pages/images/new/u3581.png" /></span>
								</div>
								<div class="tab-list-hide">
									<div class="tab-list-hide-left"><img src="<%=request.getContextPath()%>/pages/images/new/u3607.jpg" /></div>
									<div class="tab-list-hide-right">
										《末日之战》改编自迈克斯·布鲁克斯的同名科幻小说，是由马克·福斯特执导，布拉德·皮特、米瑞·伊诺丝主演的一部惊悚科幻片.影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的
									</div>
								</div>
							</div>
							<div class="tab-list">
								<div class="tab-list-show">
									<span class="tab-list-show-1"><label>6</label></span>
									<span class="tab-list-show-2">复仇者联盟3：复仇者联盟3复仇者联盟3</span>
									<span class="tab-list-show-3">666663</span>
									<span class="tab-list-show-4"><img src="<%=request.getContextPath()%>/pages/images/new/u3581.png" /></span>
								</div>
								<div class="tab-list-hide">
									<div class="tab-list-hide-left"><img src="<%=request.getContextPath()%>/pages/images/new/u3607.jpg" /></div>
									<div class="tab-list-hide-right">
										《末日之战》改编自迈克斯·布鲁克斯的同名科幻小说，是由马克·福斯特执导，布拉德·皮特、米瑞·伊诺丝主演的一部惊悚科幻片.影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的
									</div>
								</div>
							</div>
							<div class="tab-list">
								<div class="tab-list-show">
									<span class="tab-list-show-1"><label>7</label></span>
									<span class="tab-list-show-2">复仇者联盟3：复仇者联盟3复仇者联盟3</span>
									<span class="tab-list-show-3">666663</span>
									<span class="tab-list-show-4"><img src="<%=request.getContextPath()%>/pages/images/new/u3581.png" /></span>
								</div>
								<div class="tab-list-hide">
									<div class="tab-list-hide-left"><img src="<%=request.getContextPath()%>/pages/images/new/u3607.jpg" /></div>
									<div class="tab-list-hide-right">
										《末日之战》改编自迈克斯·布鲁克斯的同名科幻小说，是由马克·福斯特执导，布拉德·皮特、米瑞·伊诺丝主演的一部惊悚科幻片.影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的
									</div>
								</div>
							</div>
							<div class="tab-list">
								<div class="tab-list-show">
									<span class="tab-list-show-1"><label>8</label></span>
									<span class="tab-list-show-2">复仇者联盟3：复仇者联盟3复仇者联盟3</span>
									<span class="tab-list-show-3">666663</span>
									<span class="tab-list-show-4"><img src="<%=request.getContextPath()%>/pages/images/new/u3581.png" /></span>
								</div>
								<div class="tab-list-hide">
									<div class="tab-list-hide-left"><img src="<%=request.getContextPath()%>/pages/images/new/u3607.jpg" /></div>
									<div class="tab-list-hide-right">
										《末日之战》改编自迈克斯·布鲁克斯的同名科幻小说，是由马克·福斯特执导，布拉德·皮特、米瑞·伊诺丝主演的一部惊悚科幻片.影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的
									</div>
								</div>
							</div>
							<div class="tab-list">
								<div class="tab-list-show">
									<span class="tab-list-show-1"><label>9</label></span>
									<span class="tab-list-show-2">复仇者联盟3：复仇者联盟3复仇者联盟3</span>
									<span class="tab-list-show-3">666663</span>
									<span class="tab-list-show-4"><img src="<%=request.getContextPath()%>/pages/images/new/u3581.png" /></span>
								</div>
								<div class="tab-list-hide">
									<div class="tab-list-hide-left"><img src="<%=request.getContextPath()%>/pages/images/new/u3607.jpg" /></div>
									<div class="tab-list-hide-right">
										《末日之战》改编自迈克斯·布鲁克斯的同名科幻小说，是由马克·福斯特执导，布拉德·皮特、米瑞·伊诺丝主演的一部惊悚科幻片.影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的
									</div>
								</div>
							</div>
							<div class="tab-list">
								<div class="tab-list-show">
									<span class="tab-list-show-1"><label>10</label></span>
									<span class="tab-list-show-2">复仇者联盟3：复仇者联盟3复仇者联盟3</span>
									<span class="tab-list-show-3">666663</span>
									<span class="tab-list-show-4"><img src="<%=request.getContextPath()%>/pages/images/new/u3581.png" /></span>
								</div>
								<div class="tab-list-hide">
									<div class="tab-list-hide-left"><img src="<%=request.getContextPath()%>/pages/images/new/u3607.jpg" /></div>
									<div class="tab-list-hide-right">
										《末日之战》改编自迈克斯·布鲁克斯的同名科幻小说，是由马克·福斯特执导，布拉德·皮特、米瑞·伊诺丝主演的一部惊悚科幻片.影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的
									</div>
								</div>
							</div>
						</div>
					    <div class="bill-list">
							<div class="list-title">
								全部电影 <span>更多</span>
							</div>
							<div class="clearfix" style="background:#f7f7f7;">
								<span class="tab-list-show-1">排名</span>
								<span class="tab-list-show-2">关键词</span>
								<span class="tab-list-show-3">搜索指数</span>
								<span class="tab-list-show-4"></span>
							</div>
							<div class="tab-list active">
								<div class="tab-list-show">
									<span class="tab-list-show-1"><label>1</label></span>
									<span class="tab-list-show-2">复仇者联盟3：复仇者联盟3复仇者联盟3</span>
									<span class="tab-list-show-3">666663</span>
									<span class="tab-list-show-4"><img src="<%=request.getContextPath()%>/pages/images/new/u3581.png" /></span>
								</div>
								<div class="tab-list-hide">
									<div class="tab-list-hide-left"><img src="<%=request.getContextPath()%>/pages/images/new/u3607.jpg" /></div>
									<div class="tab-list-hide-right">
										《末日之战》改编自迈克斯·布鲁克斯的同名科幻小说，是由马克·福斯特执导，布拉德·皮特、米瑞·伊诺丝主演的一部惊悚科幻片.影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的
									</div>
								</div>
							</div>
							<div class="tab-list">
								<div class="tab-list-show">
									<span class="tab-list-show-1"><label>2</label></span>
									<span class="tab-list-show-2">复仇者联盟3：复仇者联盟3复仇者联盟3</span>
									<span class="tab-list-show-3">666663</span>
									<span class="tab-list-show-4"><img src="<%=request.getContextPath()%>/pages/images/new/u3581.png" /></span>
								</div>
								<div class="tab-list-hide">
									<div class="tab-list-hide-left"><img src="<%=request.getContextPath()%>/pages/images/new/u3607.jpg" /></div>
									<div class="tab-list-hide-right">
										《末日之战》改编自迈克斯·布鲁克斯的同名科幻小说，是由马克·福斯特执导，布拉德·皮特、米瑞·伊诺丝主演的一部惊悚科幻片.影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的
									</div>
								</div>
							</div>
							<div class="tab-list">
								<div class="tab-list-show">
									<span class="tab-list-show-1"><label>3</label></span>
									<span class="tab-list-show-2">复仇者联盟3：复仇者联盟3复仇者联盟3</span>
									<span class="tab-list-show-3">666663</span>
									<span class="tab-list-show-4"><img src="<%=request.getContextPath()%>/pages/images/new/u3581.png" /></span>
								</div>
								<div class="tab-list-hide">
									<div class="tab-list-hide-left"><img src="<%=request.getContextPath()%>/pages/images/new/u3607.jpg" /></div>
									<div class="tab-list-hide-right">
										《末日之战》改编自迈克斯·布鲁克斯的同名科幻小说，是由马克·福斯特执导，布拉德·皮特、米瑞·伊诺丝主演的一部惊悚科幻片.影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的
									</div>
								</div>
							</div>
							<div class="tab-list">
								<div class="tab-list-show">
									<span class="tab-list-show-1"><label>4</label></span>
									<span class="tab-list-show-2">复仇者联盟3：复仇者联盟3复仇者联盟3</span>
									<span class="tab-list-show-3">666663</span>
									<span class="tab-list-show-4"><img src="<%=request.getContextPath()%>/pages/images/new/u3581.png" /></span>
								</div>
								<div class="tab-list-hide">
									<div class="tab-list-hide-left"><img src="<%=request.getContextPath()%>/pages/images/new/u3607.jpg" /></div>
									<div class="tab-list-hide-right">
										《末日之战》改编自迈克斯·布鲁克斯的同名科幻小说，是由马克·福斯特执导，布拉德·皮特、米瑞·伊诺丝主演的一部惊悚科幻片.影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的
									</div>
								</div>
							</div>
							<div class="tab-list">
								<div class="tab-list-show">
									<span class="tab-list-show-1"><label>5</label></span>
									<span class="tab-list-show-2">复仇者联盟3：复仇者联盟3复仇者联盟3</span>
									<span class="tab-list-show-3">666663</span>
									<span class="tab-list-show-4"><img src="<%=request.getContextPath()%>/pages/images/new/u3581.png" /></span>
								</div>
								<div class="tab-list-hide">
									<div class="tab-list-hide-left"><img src="<%=request.getContextPath()%>/pages/images/new/u3607.jpg" /></div>
									<div class="tab-list-hide-right">
										《末日之战》改编自迈克斯·布鲁克斯的同名科幻小说，是由马克·福斯特执导，布拉德·皮特、米瑞·伊诺丝主演的一部惊悚科幻片.影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的
									</div>
								</div>
							</div>
							<div class="tab-list">
								<div class="tab-list-show">
									<span class="tab-list-show-1"><label>6</label></span>
									<span class="tab-list-show-2">复仇者联盟3：复仇者联盟3复仇者联盟3</span>
									<span class="tab-list-show-3">666663</span>
									<span class="tab-list-show-4"><img src="<%=request.getContextPath()%>/pages/images/new/u3581.png" /></span>
								</div>
								<div class="tab-list-hide">
									<div class="tab-list-hide-left"><img src="<%=request.getContextPath()%>/pages/images/new/u3607.jpg" /></div>
									<div class="tab-list-hide-right">
										《末日之战》改编自迈克斯·布鲁克斯的同名科幻小说，是由马克·福斯特执导，布拉德·皮特、米瑞·伊诺丝主演的一部惊悚科幻片.影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的
									</div>
								</div>
							</div>
							<div class="tab-list">
								<div class="tab-list-show">
									<span class="tab-list-show-1"><label>7</label></span>
									<span class="tab-list-show-2">复仇者联盟3：复仇者联盟3复仇者联盟3</span>
									<span class="tab-list-show-3">666663</span>
									<span class="tab-list-show-4"><img src="<%=request.getContextPath()%>/pages/images/new/u3581.png" /></span>
								</div>
								<div class="tab-list-hide">
									<div class="tab-list-hide-left"><img src="<%=request.getContextPath()%>/pages/images/new/u3607.jpg" /></div>
									<div class="tab-list-hide-right">
										《末日之战》改编自迈克斯·布鲁克斯的同名科幻小说，是由马克·福斯特执导，布拉德·皮特、米瑞·伊诺丝主演的一部惊悚科幻片.影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的
									</div>
								</div>
							</div>
							<div class="tab-list">
								<div class="tab-list-show">
									<span class="tab-list-show-1"><label>8</label></span>
									<span class="tab-list-show-2">复仇者联盟3：复仇者联盟3复仇者联盟3</span>
									<span class="tab-list-show-3">666663</span>
									<span class="tab-list-show-4"><img src="<%=request.getContextPath()%>/pages/images/new/u3581.png" /></span>
								</div>
								<div class="tab-list-hide">
									<div class="tab-list-hide-left"><img src="<%=request.getContextPath()%>/pages/images/new/u3607.jpg" /></div>
									<div class="tab-list-hide-right">
										《末日之战》改编自迈克斯·布鲁克斯的同名科幻小说，是由马克·福斯特执导，布拉德·皮特、米瑞·伊诺丝主演的一部惊悚科幻片.影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的
									</div>
								</div>
							</div>
							<div class="tab-list">
								<div class="tab-list-show">
									<span class="tab-list-show-1"><label>9</label></span>
									<span class="tab-list-show-2">复仇者联盟3：复仇者联盟3复仇者联盟3</span>
									<span class="tab-list-show-3">666663</span>
									<span class="tab-list-show-4"><img src="<%=request.getContextPath()%>/pages/images/new/u3581.png" /></span>
								</div>
								<div class="tab-list-hide">
									<div class="tab-list-hide-left"><img src="<%=request.getContextPath()%>/pages/images/new/u3607.jpg" /></div>
									<div class="tab-list-hide-right">
										《末日之战》改编自迈克斯·布鲁克斯的同名科幻小说，是由马克·福斯特执导，布拉德·皮特、米瑞·伊诺丝主演的一部惊悚科幻片.影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的
									</div>
								</div>
							</div>
							<div class="tab-list">
								<div class="tab-list-show">
									<span class="tab-list-show-1"><label>10</label></span>
									<span class="tab-list-show-2">复仇者联盟3：复仇者联盟3复仇者联盟3</span>
									<span class="tab-list-show-3">666663</span>
									<span class="tab-list-show-4"><img src="<%=request.getContextPath()%>/pages/images/new/u3581.png" /></span>
								</div>
								<div class="tab-list-hide">
									<div class="tab-list-hide-left"><img src="<%=request.getContextPath()%>/pages/images/new/u3607.jpg" /></div>
									<div class="tab-list-hide-right">
										《末日之战》改编自迈克斯·布鲁克斯的同名科幻小说，是由马克·福斯特执导，布拉德·皮特、米瑞·伊诺丝主演的一部惊悚科幻片.影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的影片讲述了联合国战后调查人员对一场毁灭世界各国的丧尸战争的
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="tab-pane fade" id="tab-film-2">
						爱情电影
					</div>
					<div class="tab-pane fade" id="tab-film-3">
						喜剧电影
					</div>
					<div class="tab-pane fade" id="tab-film-4">
						惊悚电影
					</div>
					<div class="tab-pane fade" id="tab-film-5">
						科幻电影
					</div>
					<div class="tab-pane fade in active" id="tab-film-6">
						<div class="today">
							今日剧情电影排行榜
						</div>
						<div class="today-list">
							<span class="todayList-1">排名</span>
							<span class="todayList-2">关键词</span>
							<span class="todayList-3">相关链接</span>
							<span class="todayList-4">搜索指数</span>
						</div>
						<div class="today-list">
							<span class="todayList-1"><label>1</label></span>
							<span class="todayList-2">复仇者联盟4</span>
							<span class="todayList-3"><a href="#">新闻</a><a href="#">简介</a><a href="#">啥玩意</a></span>
							<span class="todayList-4">66666<img src="<%=request.getContextPath()%>/pages/images/new/u3581.png" /></span>
						</div>
						<div class="today-list">
							<span class="todayList-1"><label>2</label></span>
							<span class="todayList-2">复仇者联盟4</span>
							<span class="todayList-3"><a href="#">新闻</a><a href="#">简介</a><a href="#">啥玩意</a></span>
							<span class="todayList-4">66666<img src="<%=request.getContextPath()%>/pages/images/new/u3581.png" /></span>
						</div>
						<div class="today-list">
							<span class="todayList-1"><label>3</label></span>
							<span class="todayList-2">复仇者联盟4</span>
							<span class="todayList-3"><a href="#">新闻</a><a href="#">简介</a><a href="#">啥玩意</a></span>
							<span class="todayList-4">66666<img src="<%=request.getContextPath()%>/pages/images/new/u3581.png" /></span>
						</div>
						<div class="today-list">
							<span class="todayList-1"><label>4</label></span>
							<span class="todayList-2">复仇者联盟4</span>
							<span class="todayList-3"><a href="#">新闻</a><a href="#">简介</a><a href="#">啥玩意</a></span>
							<span class="todayList-4">66666<img src="<%=request.getContextPath()%>/pages/images/new/u3581.png" /></span>
						</div>
					</div>
				</div>
			</div>
			<div class="tab-pane fade" id="tab-2">
				电视剧
			</div>
			<div class="tab-pane fade" id="tab-3">
				综艺
			</div>
			<div class="tab-pane fade" id="tab-4">
				动漫
			</div>
			<div class="tab-pane fade" id="tab-5">
				少儿
			</div>
			<div class="tab-pane fade" id="tab-6">
				纪录片
			</div>
		</div>
</body>
</html>