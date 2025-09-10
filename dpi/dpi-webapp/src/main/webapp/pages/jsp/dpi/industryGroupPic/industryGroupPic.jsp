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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/operateCenter.css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/pages/jsp/dpi/industryGroupPic/industryGroupPic.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/pages/js/echarts.js"></script>
    <script src="<%=request.getContextPath()%>/pages/jsp/dpi/dpiCommon.js"></script>
    <title>行业群体画像</title>
</head>
<body>
<div class="page-content clearfix group-pic">
    <form action="" class="form-inline">
        <div class="form-group">
        	<label>账期：</label>
        	<input type="text" class="form-control datepicker-input" placeholder="不限" id="time1" style="width: 150px;">
    	</div>     
        <div class="form-group">
            <label>地域：</label>
            <select name=""  class="chosen-select"  id="area" style="width: 150px;">
                <option value="0">全省</option>
                <!-- <option value="2301">哈尔滨</option>
                <option value="2302">齐齐哈尔</option>
                <option value="2303">牡丹江</option>
                <option value="2304">佳木斯</option>
                <option value="2305">双鸭山</option>
                <option value="2306">七台河</option>
                <option value="2307">鸡西</option>
                <option value="2308">鹤岗</option>
                <option value="2309">伊春</option>
                <option value="2310">黑河</option>
                <option value="2311">绥化</option>
                <option value="2312">大兴安岭</option>
                <option value="2313">大庆</option> -->
            </select>
        </div>
        <div class="form-group">
            <label>性别：</label>
            <select name=""  class="chosen-select"  id="sex" style="width: 150px;">
                <option value="0">全部</option>
                <option value="1">男</option>
                <option value="2">女</option>
            </select>
        </div>
    </form>
    <div class="row num-main">
        <div class="col-xs-6">
            <%-- 男容器 --%>
            <div class="man-container clearfix" style="height:280px;">
                <div class="col-xs-1"></div>
                <div class="col-xs-4">
                    <h4 class="text-center text-bold">行业数量占比</h4>
                    <table class="table">
                        <tr>
                            <td id = "1">无</td>
                            <td	id = "6">0%</td>
                        </tr>
                        <tr>
                            <td id = "2">无</td>
                            <td	id = "7">0%</td>
                        </tr>
                        <tr>
                            <td id = "3">无</td>
                            <td	id = "8">0%</td>
                        </tr>
                        <tr>
                            <td id = "4">无</td>
                            <td	id = "9">0%</td>
                        </tr>
                        <tr>
                            <td id = "5">无</td>
                            <td	id = "10">0%</td>
                        </tr>
                    </table>
                </div>
                <div class="col-xs-4">
                    <h4 class="text-center text-bold">APP使用占比</h4>
                    <table class="table">
                        <tr>
                            <td id = "11">无</td>
                            <td id = "16">0%</td>
                        </tr>
                        <tr>
                            <td id = "12">无</td>
                            <td id = "17">0%</td>
                        </tr>
                        <tr>
                            <td id = "13">无</td>
                            <td id = "18">0%</td>
                        </tr>
                        <tr>
                            <td id = "14">无</td>
                            <td id = "19">0%</td>
                        </tr>
                        <tr>
                            <td id = "15">无</td>
                            <td id = "20">0%</td>
                        </tr>
                    </table>
                </div>
                <div class="col-xs-3">
                    <img id="" class="img" src="../industryPic/images/u3501.png" />
                    <p id="manpercent"></p>
                </div>
            </div>
        </div>
        <div class="col-xs-6">
            <%-- 女容器 --%>
            <div class="woman-container" style="height:280px;">
                <div class="col-xs-3">
                    <img id="" class="img" src="../industryPic/images/u3499.png" /> 
                    <p id="womanpercent"></p>
                </div>
                <div class="col-xs-4">
                    <h4 class="text-center text-bold">行业数量占比</h4>
                    <table class="table">
                        <tr>
                            <td id = "21">无</td>
                            <td id = "26">0%</td>
                        </tr>
                        <tr>
                            <td id = "22">无</td>
                            <td id = "27">0%</td>
                        </tr>
                        <tr>
                            <td id = "23">无</td>
                            <td id = "28">0%</td>
                        </tr>
                        <tr>
                            <td id = "24">无</td>
                            <td id = "29">0%</td>
                        </tr>
                        <tr>
                            <td id = "25">无</td>
                            <td id = "30">0%</td>
                        </tr>
                    </table>
                </div>
                <div class="col-xs-4">
                    <h4 class="text-center text-bold">APP使用占比</h4>
                    <table class="table">
                        <tr>
                            <td id = "31">无</td>
                            <td id = "36">0%</td>
                        </tr>
                        <tr>
                            <td id = "32">无</td>
                            <td id = "37">0%</td>
                        </tr>
                        <tr>
                            <td id = "33">无</td>
                            <td id = "38">0%</td>
                        </tr>
                        <tr>
                            <td id = "34">无</td>
                            <td id = "39">0%</td>
                        </tr>
                        <tr>
                            <td id = "35">无</td>
                            <td id = "40">0%</td>
                        </tr>
                    </table>
                </div>
                <div class="col-xs-1"></div>
            </div>
        </div>
        <div class="col-xs-12 clearfix">
            <div class="man-progress" style="width:0%;" id = ""></div>
            <div class="woman-progress" style="width:0%;" id = ""></div>
        </div>
    </div>
    <div class="row num-main">
        <div class="col-xs-6">
            <%-- 柱图容器 --%>
            <div class="echarts-container echarts-container-pie" style="height:350px;" id='left1'></div>
        </div>
        <div class="col-xs-6">
            <%-- 表格容器 --%>
            <div class="echarts-container echarts-container-bar" style="height:350px;" id=''>
                <div class="table-box">
                    <div class="col-xs-12">
                        <h4 class="text-bold bold-black" id = "ageDistribution">年龄段</h4>
                    </div>
                    <div class="col-xs-6">
                        <h4 class="text-center text-bold">行业数量占比</h4>
                        <table class="table">
                            <tr>
                                <td id = "a0">无</td>
                                <td id = "agePer0">0%</td>
                            </tr>
                            <tr>
                                <td id = "a1">无</td>
                                <td id = "agePer1">0%</td>
                            </tr>
                            <tr>
                                <td id = "a2">无</td>
                                <td id = "agePer2">0%</td>
                            </tr>
                            <tr>
                                <td id = "a3">无</td>
                                <td id = "agePer3">0%</td>
                            </tr>
                            <tr>
                                <td id = "a4">无</td>
                                <td id = "agePer4">0%</td>
                            </tr>
                        </table>
                    </div>
                    <div class="col-xs-6">
                        <h4 class="text-center text-bold">APP使用占比</h4>
                        <table class="table">
                            <tr>
                                <td id = "app0">无</td>
                                <td id = "appPer0">0%</td>
                            </tr>
                            <tr>
                                <td id = "app1">无</td>
                                <td id = "appPer1">0%</td>
                            </tr>
                            <tr>
                                <td id = "app2">无</td>
                                <td id = "appPer2">0%</td>
                            </tr>
                            <tr>
                                <td id = "app3">无</td>
                                <td id = "appPer3">0%</td>
                            </tr>
                            <tr>
                                <td id = "app4">无</td>
                                <td id = "appPer4">0%</td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%-- <div class="row num-main">
        <div class="col-xs-12">
            <h4 class="text-center text-bold">终端分析</h4>
            终端分析柱图容器
            <div class="echarts-container echarts-container-pie" style="height:400px;" id='bottom1'></div>
        </div>
    </div> --%>
</body>
</html>