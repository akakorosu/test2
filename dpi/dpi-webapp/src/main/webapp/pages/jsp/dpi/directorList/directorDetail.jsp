<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.bonc.com.cn/common/tag/cxt" prefix="cxt"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<cxt:commonLinks />
        <link href="css/jquery-ui.css" rel="stylesheet">
    <link href="css/ui.jqgrid.css" rel="stylesheet">
    <!--jquery-->
    <!--  <script src="https://cdn.bootcss.com/jquery/3.0.0-beta1/jquery.min.js"></script> -->
    <script src="<%=request.getContextPath()%>/pages/js/jquery-3.1.1.min.js"></script>
    <!--locale-->
    <script src="<%=request.getContextPath()%>/pages/jsp/dpi/directorList/js/grid.locale-cn.js"></script>
    <!--jqgrid的js-->
    <script src="<%=request.getContextPath()%>/pages/jsp/dpi/directorList/js/jquery.jqGrid.src.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/pages/jsp/dpi/directorList/directorDetail.js"></script>
    <meta charset="UTF-8">
    <title>导演详情页</title>
    <style>
        * {margin: 0; padding: 0; list-style: none;}
        ul,li{list-style: none;}
        .clearfix:after{content:"";height:0;line-height:0;display:block;visibility:hidden;clear:both}
　　　　 .clearfix{zoom:1;}
        .mainBox{
            background: #f9f9fa;
        }
        .infoDetail{
            background: #fff;
            border: 1px solid #e3e6ee;
            padding: 15px 0;
        }
        .infoDetail>div{
            float: left;
        }
        .imgDiv{
            padding: 5px 20px;
            width: 121px;
            height: 121px;
        }
        .textDiv{
            margin-left: 10px;
        }
        .textDiv .title{
            
            margin-bottom: 10px;
        }
        .chinaName{
            font-size: 20px;
            color: #333;
            font-weight: 700;
            margin-right: 15px;
        }
        .englishName{
            font-size: 14px;
            color: #8084A4;
        }
        .textDiv .content td{
            color: #999999;
            font-size: 13px;
            width: 300px;
            line-height: 22px;
        }
        .textDiv .content td .labelName{
            display: inline-block;
            font-weight: 700;
            width: 52px;
        }
        .textDiv .content td .labelName1{
            margin-right: 15px;
        }
        .jqGridDiv{
            background: #fff;
            border: 1px solid #e3e6ee;
            padding: 15px 20px;
            margin-top: 5px;
        }
        .ui-th-ltr, .ui-jqgrid .ui-jqgrid-htable th.ui-th-ltr {
            line-height: 21px;
            height: 36px;
            text-align: left;
            color: #333;
            font-size: 13px;
        }
        .ui-jqgrid tr.jqgrow {
            line-height: 32px;
            color: #8084A4;
            font-size: 13px;
        }
        .ui-th-ltr, .ui-jqgrid .ui-jqgrid-htable th.ui-th-ltr:last-child{
            text-align: center;
        }
        .ui-jqgrid .ui-jqgrid-hdiv { overflow-y: hidden; }
        #jqgh_jqGrid_year, #jqgh_jqGrid_workName, #jqgh_jqGrid_duty, #jqgh_jqGrid_type{
         height: 30px;
        }
        .jqGridDiv{
          height: 70%;
        }
        .ui-jqgrid .ui-jqgrid-pager {
		    height: 45px!important;
		}
    </style>
    <script>
    <% 
    String orgId = request.getParameter("orgId");
    
    %>
    
    
    
    </script>
    <script type="text/javascript">
	var orgId = "<%=orgId%>"; 
</script>
</head>
<body>
    <div class="mainBox">
        <div class="infoDetail clearfix">
            <!-- <div class="imgDiv"><img src="../images/u2746.jpg" alt=""></div> -->
            <div class="textDiv">
                <div class="title">
                    <span class="chinaName" id="chinaName"></span>
                    <!-- <span class="englishName">Stephen Chow</span> -->
                </div>
                <table class="content">
                    <tbody>
                        <tr>
                            <td><span class="labelName">别&nbsp;&nbsp;&nbsp;名</span><span id="anotherName"></span></td>
                            <td><span class="labelName">血&nbsp;&nbsp;&nbsp;型</span><span id="bloodType"></span></td>
                            <td><span class="labelName labelName1">生&nbsp;&nbsp;&nbsp;日</span><span id="birth"></span></td>
                        </tr>
                        <tr>
                            <td><span class="labelName">国&nbsp;&nbsp;&nbsp;籍</span><span id="nationality"></span></td>
                            <td><span class="labelName">身&nbsp;&nbsp;&nbsp;高</span><span id="height"></span></td>
                            <td><span class="labelName labelName1">性&nbsp;&nbsp;&nbsp;别</span><span id="gender"></span></td>
                        </tr>
                        <tr>
                            <td><span class="labelName">民&nbsp;&nbsp;&nbsp;族</span><span id="nation"></span></td>
                            <td><span class="labelName">体&nbsp;&nbsp;&nbsp;重</span><span id="weight"></span></td>
                            <td><span class="labelName labelName1">籍&nbsp;&nbsp;&nbsp;贯</span><span id="nativePlace"></span></td>
                        </tr>
                        <tr>
                            <td><span class="labelName">星&nbsp;&nbsp;&nbsp;座</span><span id="constellation"></span></td>
                            <td><span class="labelName">出生地</span><span id="brithPalce"></span></td>
                            <td><span class="labelName labelName1">职&nbsp;&nbsp;&nbsp;业</span><span id="job"></span></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="jqGridDiv" >
            <table id="jqGrid" ></table>
            <!-- <div id="jqGridPager"></div> -->
            <div id="grid-pager"></div>
        </div>
    </div>
    <script>
       /*  $(document).ready(function () {
            $("#jqGrid").jqGrid({
                url: 'http://trirand.com/blog/phpjqgrid/examples/jsonp/getjsonp.php?callback=?&qwery=longorders',
                mtype: "GET",
                datatype: "jsonp",
                colModel: [
                    { label: 'OrderID', name: 'OrderID',  width: 75 ,hidden:true},
                    { label: '上映时间', name: 'CustomerID', width: 100 },
                    { label: '名称', name: 'OrderDate', width: 150 },
                    { label: '主演', name: 'Freight', width: 150 },
                    { label:'类型', name: 'ShipName', width: 100,align :'center'}
                ],
                scrollOffset : 0,
				viewrecords: true,
                width: $(window).width()-40,
                height: "auto",
                // rowNum: 10,
                // pager: "#jqGridPager",
            });
        }); */
    </script>
</body>
</html>