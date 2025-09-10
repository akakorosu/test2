<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.bonc.com.cn/common/tag/cxt" prefix="cxt" %>
<!DOCTYPE html>
<html>
<head>
    <cxt:commonLinks/>
    <meta charset='utf-8'>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/common.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/operateCenter.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/industryPic.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/pages/jsp/dpi/contentProjectRank/contentProjectRank.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/pages/js/echarts.js"></script>
    <script src="<%=request.getContextPath()%>/pages/jsp/dpi/dpiCommon.js"></script>
    <title>内容专题排名</title>
</head>
<style>
    #grid-table2 tr.jqgrow td,#grid-table1 tr.jqgrow td {
       white-space: normal;
       border-bottom:1px solid #EBEBEB;
     }
     #grid-table2_contentLabelName div,#grid-table2_userTotal div,#grid-table2_totalFlow div,#grid-table1_userTotal div,#grid-table1_totalFlow div{
         text-align:center;
     }
     .ui-jqgrid tr.jqgrow td {
         font-size:13px; 
     }
     #tabel1 th,#tabel1 td{
         text-align: center;
         border-bottom:1px solid #ddd !important;
         color:#999;
     }
</style>
<body>

    <div class="row num-main" style='padding-top:10px;'>
             <div class="form-group" style='margin:0 0 10px 20px;'> 
                    <label>账期：</label>
                    <input type="text" class="form-control datepicker-input" id='time' placeholder="不限" style='width:150px;display:inline-block;'>
             		<label style="margin-left: 10px">地区：</label>
			        <select name=""  class="chosen-select"  id="cityId" style="width: 150px;"></select>
             </div>
            <ul class="tab num-main col-xs-12" id="u" >
            <li class="active" id='video'>视频</li>
            <li class="">小说</li>
            <!-- <li class="">电商商品</li> -->
            <!-- <li class="">音乐</li> -->
        </ul>
        <div class="col-xs-6">
            <h4 class="text-bold" id='title_2'>视频类用户数统计</h4>
            <%-- 柱图容器1 --%>
            <div class="echarts-container" style="height:350px;" id='right1'>
                <div class="user-num-box pull-left">
                    <img class="pull-left" src="../contentProjectRank/images/icon1.jpg" />
                    <div class="pull-left">
                        <h4 class="text-bold">用户数</h4>
                        <p id='p1'>用户：234（户）</p>
                        <p id='p2'>占比：22%</p>
                    </div>
                </div>
                <div class="user-num-box pull-left">
                    <img class="pull-left" src="../contentProjectRank/images/icon2.jpg" />
                    <div class="pull-left">
                        <h4 class="text-bold">流量数</h4>
                        <p id='p3'>流量：234（GB）</p>
                        <p id='p4'>占比：22%</p>
                    </div>
                </div>
                <div class="user-num-box pull-left">
                    <img class="pull-left" src="../contentProjectRank/images/icon3.jpg" />
                    <div class="pull-left">
                        <h4 class="text-bold">次数</h4>
                        <p id='p5'>次数：234（次）</p>
                        <p id='p6'>占比：22%</p>
                    </div>
                </div>
                <div class="user-num-box pull-left">
                    <img class="pull-left" src="../contentProjectRank/images/icon4.jpg" />
                    <div class="pull-left">
                        <h4 class="text-bold">内容数</h4>
                        <p id='p7'>内容：234（X）</p>
                        <p> </p>
                    </div>
                </div>
            </div>
        </div>
         <div class="col-xs-6">
            <h4 class="text-bold" id='title_1'>视频类APP TOP10</h4>
            <%-- 表格容器 --%>
            <div class="grid-container" style="height:350px;" >
                    <table id="grid-table1"></table>
            </div>
        </div>
    </div>
    <div class="row num-main">
        <div class="col-xs-6">
            <h4 class="text-bold" id='title_3'>视频类内容排行榜</h4>
            <%-- 表格容器 --%>
            <div class="grid-container" style="height:350px;" id=''>
                <table id="grid-table2"></table>
            </div>
        </div>
        <div class="col-xs-6">
            <h4 class="text-bold" id='title_4'>视频类各分类用户分布</h4>
            <%-- 柱图容器2 --%>
            <div class="echarts-container echarts-container-pie" style="height:350px;" id='right2'>表格</div>
        </div>
    </div>
    <div class="row num-main" id='hide'>
        <div class="col-xs-12">
            <h4 class="text-bold" id='title_5'>视频类分类用户数分布图</h4>
            <%-- 柱图容器3 --%>
            <div class="echarts-container echarts-container-line" style="height:350px;" id='bar3'></div>
        </div>
    </div>
    <div class="row num-main">
        <div class="col-xs-12">
            <h4 class="text-bold" id='title_6'>视频类24小时时段流量分布</h4>
            <%-- 线图容器 --%>
            <div class="echarts-container echarts-container-line" style="height:350px;" id='line1'></div>
        </div>
    </div>
	 <%-- 性别年龄占比 --%> 
	<div class="row num-main">
	 	<div class="col-xs-12">
            <h4 class="text-bold" id='title_8'>视频类性别年龄占比</h4>
        </div> 
        <div>
            <div class="col-xs-6">
                <%--人形图容器 --%>
                <div class="echarts-container echarts-container-barman"
                    style="height: 350px;">
                    <div class='clearfix'>
                        <div class='pull-left'>
                            <img id="u3499_img" class="img" src="images/u3501.png" />
                        </div>
                        <div class='pull-left progress'>
                            <div class="progress-bar" role="progressbar" aria-valuenow="60"
                                aria-valuemin="0" aria-valuemax="100" style="width: 33.34%;" id='jin'>
                            </div>
                            <span id="manPercent">0%</span> <span id="womanPercent">0%</span>
                        </div>
                        <div class='pull-left'>
                            <img id="u3499_img" class="img" src="images/u3499.png" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
 
        <div class="col-xs-6">
            <%-- 柱图容器1 --%>
            <div class="echarts-container echarts-container-bar"style="height: 350px;" id="columnecharts">
 
            </div>
                
        </div>
    </div>
	
	
	<div class="row num-main">
		<div class="col-xs-12">
			<h4 class="text-bold" id='title_9'>视频类地域分析</h4>
		</div>
        <div class="col-xs-6">
                <%-- 地图容器 --%>
            <div class="echarts-container echarts-container-pie" style="height:420px;" id='map_echarts'>地图</div>
        </div>
          <div class="col-xs-6">
        <table class="table" id='tabel1'>
            <thead id='thead'>
                <tr>
                    <th>区域</th>
                    <th>用户数(户)</th>
                    <th>用户占比</th>
                    <th>流量(G)</th>
                    <th>流量占比</th>
                    <th>人均流量(M)</th>
                </tr>
            </thead>
            <tbody id="tbody">
            </tbody>
        </table>
        </div>
  	</div>
	
	
	<%-- 关联APP 气泡图 --%>
	<div class="row num-main">
		  <div class="col-xs-6">
            <h4 class="text-bold" id='title_10'>视频类关联APP TOP10</h4>
            <%-- 柱图容器2 --%>
            <div class="echarts-container echarts-container-pie" style="width: 100%;height: 380px;" id='mei_echarts'>表格</div>
        </div>
		<div class="col-xs-6">
			<table class="table" style="text-align: center;margin-top:40px;">
				<tbody id='app'>
					
				</tbody>
			</table>
		</div>
	</div>

</body>
</html>
