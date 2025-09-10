<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.bonc.com.cn/common/tag/cxt" prefix="cxt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bonc.system.dao.entity.SysUser"%>
<%@ page import="com.bonc.system.dao.entity.SysRoleUser"%>
<%@ page import="com.bonc.common.cst.CST"%>
<!DOCTYPE html>
<html>
<head>
<cxt:commonLinks />
<meta charset='utf-8'>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/pages/css/bootstrap-multiselect.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/pages/css/common.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/pages/css/operateCenter.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/pages/css/industryPic.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/operateCenter.css" />
<script type="text/javascript">
 var label_name1 = "<%=request.getParameter("label_name1")%>";
</script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/pages/js/bootstrap-multiselect.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/pages/jsp/dpi/industryPic/industryPic.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/pages/js/echarts.js"></script>
<script src="<%=request.getContextPath()%>/pages/jsp/dpi/dpiCommon.js"></script>
</head>
<style>
     #tabel1 th,#tabel1 td{
         text-align: center;
         border-bottom:1px solid #ddd !important;
         color:#999;
     }
     #d1,#d2,#d3{
     	margin-left:10px;
     }
</style>
<body>

<div class="page-content clearfix">

<form action="" class="form-inline">
	<div class="form-group">
        	<label>账期：</label>
        	<input type="text" class="form-control datepicker-input" placeholder="不限" id="time1" style="width: 150px;">
    </div>
    <div class="form-group">
        <label>地区：</label>
        <select name=""  class="chosen-select"  id="city" style="width: 150px;"></select>
    </div> 
    <div class="form-group">
  			<label>标签：</label>
			<input  type="text" placeholder="全部" id="labelName2" name="labelName2" readOnly="readonly" onclick="sntest123()" style="width: 150px;">
	</div> 
</form>	
	<div class="row num-main">
		<h4 class="text-bold"  style='margin-left:20px;'>性别年龄占比</h4>
	<!-- 	<div class="col-xs-12" id="d1">性别年龄占比</div> -->
			<div class="col-xs-6">
				<%--人形图容器 --%>
				<div class="echarts-container echarts-container-barman"
					style="height: 300px;">
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

		<div class="col-xs-6">
			<%-- 柱图容器1 --%>
			<div class="echarts-container echarts-container-bar"style="height: 300px;" id="columnecharts">

			</div>
				
		</div>
	</div>
	
	
	<%-- <div class="row num-main">
		<h4 class="text-bold"  style='margin-left:20px;'>地域分析</h4>
        <div class="col-xs-6">
                地图容器
            <div class="echarts-container echarts-container-pie" style="height:420px;" id='map_echarts'>地图</div>
        </div>
          <div class="col-xs-6">
        <table class="table" id='tabel1'>
            <thead>
                <tr>
                    <th>区域</th>
                    <th>用户数(户)</th>
                    <th>用户占比</th>
                    <th>流量(G)</th>
                    <th>流量占比</th>
                    <th>人均流量(M)</th>
                </tr>
            </thead>
            <tbody id="table">
            </tbody>
        </table>
        </div>
  	</div> --%>
	
	
	<div class="row num-main">
		  <div class="col-xs-6">
            <h4 class="text-bold" id='title_10' style='margin-left:10px;'>关联APP TOP10</h4>
            <%-- 柱图容器2 --%>
            <div class="echarts-container echarts-container-pie" style="width: 100%;height: 400px;" id='mei_echarts'>表格</div>
        </div>
		<div class="col-xs-6">
			<table class="table" style="text-align: center;margin-top:40px;">
				<tbody id='tbody'>
					<tr>
                        <td width="50%" id="name0" style='text-align:center;'></td>
                        <td width="50%" id="percent0"></td>
                    </tr>
                    <tr>
                        <td id="name1"></td>
                        <td id="percent1"></td>
                    </tr>
                    <tr>
                        <td id="name2"></td>
                        <td id="percent2"></td>
                    </tr>
                    <tr>
                        <td id="name3"></td>
                        <td id="percent3"></td>
                    </tr>
                    <tr>
                        <td id="name4"></td>
                        <td id="percent4"></td>
                    </tr>
                    <tr>
                        <td id="name5"></td>
                        <td id="percent5"></td>
                    </tr>
                    <tr>
                        <td id="name6"></td>
                        <td id="percent6"></td>
                    </tr>
                    <tr>
                        <td id="name7"></td>
                        <td id="percent7"></td>
                    </tr>
                    <tr>
                        <td id="name8"></td>
                        <td id="percent8"></td>
                    </tr>
                    <tr>
                        <td id="name9"></td>
                        <td id="percent9"></td>
                    </tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>

