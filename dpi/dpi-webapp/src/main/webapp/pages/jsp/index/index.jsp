<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.bonc.com.cn/common/tag/cxt" prefix="cxt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<%-- 	<cxt:commonLinks /> --%>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=device-width,initial-scale=1.0">
	<title>知识库管理平台</title>
	<link rel="stylesheet" href="css/bootstrap.css" />
	<link rel="stylesheet" href="css/index.css" />
	<link rel="stylesheet" href="css/font-awesome.css" />
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/echarts.js"></script>
	<script src="js/echarts-gl.min.js"></script>
	<script src="js/jquery.slimscroll.js"></script>
	<script src="index.js"></script>
	
	<style type="text/css">
	
	table,table tr th, table tr td { border:1px solid #0094ff; }
	</style>
</head>
<body>
		<div class="mainContent">
			<!--头部-->
			<div class="header clearfix">
				<div class="headerLogo">
				</div>
				<div class="headerTitle">
					<div class="headerTitleL"></div>
					<div class="headerTitleC">
						知识库管理平台
					</div>
					<div class="headerTitlerR"></div>
				</div>
			</div>
			<!--中心-->
			<div class="mainCenter clearfix">
				<div class="mainCenter1">
					<div class="total">
						
						<div class="total2">
						</div>
						
						<div class="topT">
							域名库管理
						</div>
						<div id="mainCentersData1">
							<span class="mainCentersData1L">
							</span>
							<span class="mainCentersData1C">
							</span>
							<span class="mainCentersData1R">
							</span>
						</div>
						<div id="mainCenters1">
							<!--进度条仪表盘1-->
						</div>
						<div id="basis">
							<span>新增占比</span>
							<span class="basis"></span>
						</div>
					</div>
					<div class="total">
						
						
						
						<div class="topT">
							产品库管理
						</div>
						<div id="mainCentersData2">
							<span class="mainCentersData1L">
							</span>
							<span class="mainCentersData1C">
							</span>
							<span class="mainCentersData1R">
							</span>
						</div>
						<div id="mainCenters2">
							<!--进度条仪表盘2-->
						</div>
						<div id="basis1">
							<span>新增占比</span>
							<span class="basis"></span>
						</div>
					</div>
					<div class="totals1">
						
						
						
						<div class="topT">
							域名库统计
						</div>
						<div id="totals1" style="width:100%;height:158px;top:27px;">
							
						</div>
					</div>
				</div>
				<div class="mainCenter2">
					<div class="total">
						
						
						
						<div class="centerMain">
							<div class="centerMain1">
								<div class="centerTopBg centerTopBgC centerTopBgC1">
									营运收入
								</div>
								<div class="mainCenterLight mainCenterLightH">
								</div>
								<div class="mainCenterRange1" >
									<div class="mainCenterRangeD1">
										<span>
											70,317
										</span>
										<span>/</span>
										<span>
											509,400
										</span>
									</div>
									<div id="mainCenterRange1"></div>
									<div class="range1 basis1"></div>
								</div>
							</div>
							<div class="centerMain2">
								<div class="centerTopBg centerTopBgC centerTopBgC2">
									营运支出
								</div>
								<div class="mainCenterLight mainCenterLightH">
								</div>
								<div class="mainCenterRange2">
									<div class="mainCenterRangeD2">
										<span>
											70,317
										</span>
										<span>/</span>
										<span>
											509,400
										</span>
									</div>
									<div id="mainCenterRange2"></div>
									<div class="range2 basis1"></div>
								</div>
							</div>
							<div class="centerMain3">
								<div class="centerTopBg">
									净利润
								</div>
								<div class="mainCenterLight"></div>
								<div class="mainCenterRange3">
									<div class="mainCenterRangeD3">
										<span>
											70,317
										</span>
										<span>/</span>
										<span>
											509,400
										</span>
									</div>
									<div id="mainCenterRange3"></div>
									<div class="range3 basis1"></div>
								</div>
							</div>
						</div>
						<div class="centerBorder">
							<div class="colorsBorder"></div>
						</div>
						<div class="rangeTable">
							<div class="tableScrollLeft1">
								<div class="tableScroll1">
									<table class="table table-striped"></table>
								</div>
							</div>
							<div class="tableScrollLeft2">
								<div class="tableScroll2">
									<table class="table table-striped"></table>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="mainCenter3">
					<div class="total" style="float:left;">
						
						
						
						<div class="topT">
							当日数据操作统计
						</div>
						<div class="income">
							<div class="incomeL">
								<div>本月操作总量</div>
								<div class="income1"></div>
							</div>
							<div class="incomeR">
								<div>当日操作总量</div>
								<div class="income2"></div>
							</div>
						</div>
						<div id="totals2" style="width:100%;height:170px;top:27px;">
							
						</div>
					</div>
					
					
					<div class="total1">
						
						
						
						<div class="topT">
							当前待入库数据
						</div>
						
						<div id="totals5" style="width:100%;height:170px;top:56px;padding:2 5 2 5;">
							<table style="width:100%;margin-top:40px;color:white;">
							  <tr>
							    <th>数据类型</th>
							    <th>数据数量</th>
							    <th>操作</td>
							  </tr>
							  <tr>
							    <td>域名信息</td>
							    <td>120</td>
							    <td>查看</td>
							  </tr>
							  <tr>
							    <td>产品信息</td>
							    <td>100</td>
							    <td>查看</td>
							  </tr>
							</table>
						</div>
					</div>
					
					<div style="clear:both;"></div>
					
					<div class="totals1">
						
						
						
						<div class="topT">
							入库统计
						</div>
						<div class="income">
							<div class="incomeL">
								<div>当月入库总量</div>
								<div class="income3"></div>
							</div>
							<div class="incomeR">
								<div>当日入库总量</div>
								<div class="income4"></div>
							</div>
						</div>
						<div id="totals3" style="width:100%;height:210px;top:27px;">
							
						</div>
					</div>
					
				</div>
				
			</div>
			<!--底部-->
			<div class="mainFooter">
				<div class="total">
				</div>
				<div class="footerMain">
					<div class="footerMain1" >
						<div class="footerTopBg">
							标签库
						</div>
						<div id="footerMain1" style="width:100%;height:90px">
							
						</div>
					</div>
					<div class="footerMain2">
						<div class="footerTopBg">
							信息库
						</div>
						<div id="footerMain2" style="width:100%;height:90px">
							
						</div>
					</div>
					<div class="footerMain3">
						<div class="footerTopBg">
							属性库
						</div>
						<div id="footerMain3" style="width:100%;height:90px">
							
						</div>
					</div>
					<div class="footerMain4">
						<div class="footerTopBg">
							动画库
						</div>
						<div id="footerMain4" style="width:100%;height:90px">
							
						</div>
					</div>
					<div class="footerMain5">
						<div class="footerTopBg">
							漫画库
						</div>
						<div id="footerMain5" style="width:100%;height:90px">
							
						</div>
					</div>
					<div class="footerMain6">
						<div class="footerTopBg">
							剧集库
						</div>
						<div id="footerMain6" style="width:100%;height:90px">
							
						</div>
					</div>
					<div class="footerMain7">
						<div class="footerTopBg">
							游戏库
						</div>
						<div id="footerMain7" style="width:100%;height:90px">
							
						</div>
					</div>
					
				</div>
			</div>
			
			<div class="mainFooter">
				<div class="total">
				</div>
				<div class="footerMain">
					<div class="footerMain1" >
						<div class="footerTopBg">
							电商库
						</div>
						<div id="footerMain8" style="width:100%;height:90px">
							
						</div>
					</div>
					<div class="footerMain2">
						<div class="footerTopBg">
							电影库
						</div>
						<div id="footerMain9" style="width:100%;height:90px">
							
						</div>
					</div>
					<div class="footerMain3">
						<div class="footerTopBg">
							音乐库
						</div>
						<div id="footerMain10" style="width:100%;height:90px">
							
						</div>
					</div>
					<div class="footerMain4">
						<div class="footerTopBg">
							新闻库
						</div>
						<div id="footerMain11" style="width:100%;height:90px">
							
						</div>
					</div>
					<div class="footerMain5">
						<div class="footerTopBg">
							小说库
						</div>
						<div id="footerMain12" style="width:100%;height:90px">
							
						</div>
					</div>
					<div class="footerMain4">
						<div class="footerTopBg">
							综艺库
						</div>
						<div id="footerMain13" style="width:100%;height:90px">
							
						</div>
					</div>
					<div class="footerMain5">
						<div class="footerTopBg">
							小视频库
						</div>
						<div id="footerMain14" style="width:100%;height:90px">
							
						</div>
					</div>
					
				</div>
			</div>
			
			<div class="mainFooter">
				<div class="total">
	
				</div>
				<div class="footerMain">
					<div class="footerMain1" >
						<div class="footerTopBg">
							近义词库
						</div>
						<div id="footerMain15" style="width:100%;height:90px">
							
						</div>
					</div>
					<div class="footerMain2">
						<div class="footerTopBg">
							敏感词库
						</div>
						<div id="footerMain16" style="width:100%;height:90px">
							
						</div>
					</div>
					<div class="footerMain3">
						<div class="footerTopBg">
							停用词词库
						</div>
						<div id="footerMain17" style="width:100%;height:90px">
							
						</div>
					</div>
					<div class="footerMain4">
						<div class="footerTopBg">
							分词词库
						</div>
						<div id="footerMain18" style="width:100%;height:90px">
							
						</div>
					</div>
					<div class="footerMain5">
						<div class="footerTopBg">
							关键词规则表
						</div>
						<div id="footerMain19" style="width:100%;height:90px">
							
						</div>
					</div>
					<div class="footerMain4">
						<div class="footerTopBg">
							关键词规则分组
						</div>
						<div id="footerMain20" style="width:100%;height:90px">
							
						</div>
					</div>
					<div class="footerMain5">
						<div class="footerTopBg">
							域名识别规则
						</div>
						<div id="footerMain21" style="width:100%;height:90px">
							
						</div>
					</div>
					
				</div>
			</div>
		</div>
		
		<!-- 模态框（Modal） -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"  data-backdrop="static">
		    <div class="modal-dialog">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                <h4 class="modal-title" id="myModalLabel">定制指标</h4>
		            </div>
		            <div class="modal-body"></div>
		            <div class="modal-footer">
		                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		                <button type="button" class="btn btn-primary">确定</button>
		            </div>
		        </div><!-- /.modal-content -->
		    </div><!-- /.modal -->
		</div>
	</body>
</html>