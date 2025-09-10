<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.bonc.com.cn/common/tag/cxt" prefix="cxt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <cxt:commonLinks/>
    <title>全部榜单</title>
    <script type="text/javascript">
        var contentLabelName1List = '<%=request.getAttribute("contentLabelName1List")%>';
        contentLabelName1List = JSON.parse(contentLabelName1List);
        var contentLabelName_map = '<%=request.getAttribute("contentLabelNameMap")%>';
        contentLabelName_map=JSON.parse(contentLabelName_map);
        var mapJson = '<%=request.getAttribute("mapJson")%>';
        var cityId = '<%=request.getAttribute("cityId")%>';
        var time = '<%=request.getAttribute("monthId")%>';
    </script>
    <script src="<%=request.getContextPath()%>/pages/js/jquery-3.1.1.min.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/bootstrap-table.min.css"/>
    <script src="<%=request.getContextPath()%>/pages/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/pages/js/bootstrap-table.min.js"></script>
    <script src="<%=request.getContextPath()%>/pages/js/bootstrap-table-zh-CN.min.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/pages/jsp/dpi/billBoard/allHotData.css"/>
    <style>
        .today{
            position:relative;
        }
        .back{
            position:absolute;
            right:50px;
            bottom:0;
        }
        .label_list{
            /*border-top:1px solid #D4D4D9;*/
            /*border-bottom:1px solid #D4D4D9;*/
            /*margin-bottom:5px;*/


        }
        p{
            color:#F23D7C;
            font-size:1em;
            /*margin-block-start: 1em;*/
            /*margin-block-end: 1em;*/
            /*margin-inline-start: 0px;*/
            /*margin-inline-end: 0px;*/
            font-weight: bold;
            margin-left:0.5%;
            margin-bottom:0;
        }
        .lab{
            display: inline-block;
            /*width:70px;*/
            /*margin-left:5%;*/
            /*margin-right:5%;*/
            margin:0 0.5% 0 0.5%;
        }

    </style>
</head>
<body>
    <div class="tab-pane fade in active" id="tab-film-1">
        <div class="today">
            全部分类<a class="back" href="javascript:history.back(-1)">返回</a>
        </div>
        <div id="main">

        </div>
    </div>
</body>

<script src="<%=request.getContextPath()%>/pages/jsp/dpi/billBoard/labelsView.js"></script>
</html>