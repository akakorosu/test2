<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.bonc.com.cn/common/tag/cxt" prefix="cxt"%>

<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <cxt:commonLinks />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/jsp/dpi/directorList/css/pagination.css" />
    <!-- <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script> -->
    <script src="<%=request.getContextPath()%>/pages/js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/pages/jsp/dpi/directorList/js/pagination.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/pages/jsp/dpi/directorList/director.js"></script>
    <title>导演页</title>
    <style>
        *{margin: 0;padding: 0;font-family: '微软雅黑';}
        ul,li{list-style: none;}
        .clearfix:after{content:"";height:0;line-height:0;display:block;visibility:hidden;clear:both}
　　　　 .clearfix{zoom:1;}
        .ht-page {
            text-align: right;
            padding: 10px 30px;
        }
        /* .clearfix{
        width:100%
        } */
        .listLi{
        width:10%;
        height:200px;
        }
        .ht-page .previous.disable {
            background: none;
            border: 0;
            border-right: 1px solid #e9e9e9;
        }
        .ht-page .previous {
            background: url(../images/u1036.png) no-repeat center;
        }
        .ht-page .previous:hover {
            background: url(../images/u1036.png) no-repeat center;
        }
        .ht-page .next {
            background: url(../images/u1035.png) no-repeat center;
        }
        .ht-page .next.disable {
            background: none;
            border: 0;
            border-left: 1px solid #e9e9e9;
        }
        .ht-page .next:hover {
            background: url(../images/u1035.png) no-repeat center;

        }
        /* .ht-page .num {
            line-height: 22px;
        }
        .ht-page .dots {
            line-height: 20px;
        } */
        .ht-page .total {
            display: none;
            position: relative;
            top: -5px;
        }
        /* .ht-page .page_jump {
            position: relative;
            top: -5px;
        } */
        .ht-page .num.current {
            background: #217cd1;
            color: #fff;
        }
        .ht-page a {
            color: #666;
        }
        .ht-page .pagebtn {
            height: 32px;
            background: #fff;
            color: #666;
            border: 1px solid #e9e9e9;
        }
        .ht-page .pagebtn:hover {
            background: #fff;
        }
        .mainBox{
            background: #f9f9fa;
        }
        .selectBox{
            background: #fff;
            border: 1px solid #e3e6ee;
            padding: 15px 30px;
        }
        .selectBox .title{
            font-size: 16px;
            color: #333;
            line-height: 32px;
        }
        .selectBox .selectDiv{
            line-height: 26px;
            font-size: 14px;
            color: #8084A4;;
            font-weight: 400;
            margin: 5px 0;
        }
        .selectBox .selectDiv .label{
            float: left;
            font-weight: 700;
        }
        .selectBox .selectDiv ul{
            float: left;
        }
        .selectBox .selectDiv li{
            float: left;
            padding: 0 5px;
            margin-right: 5px;
            cursor: pointer;
        }
        .selectBox .selectDiv li.active{
            background: #4193ff;
            color: #fff;
        }
        .listBox{
            margin-top: 5px;
            background: #fff;
            border: 1px solid #e3e6ee;
            padding: 15px 10px;
            position: relative;
        }
        .listBox .listLi{
            float: left;
            width: 121px;
            margin: 0 10px 20px 10px;
        }
        .listBox .listLi .img{
            width: 121px;
            height: 121px;
        }
        .listBox .listLi .titleName{
            text-align: center;
            line-height: 40px;
            color: #8084A4;
            font-size: 14px;
        }
        .selectCon{
            position: absolute;
            bottom: 25px;
            left: 20px;
        }
        .selectCon>div,.selectCon>ul{
            float: left;
        }
        .selectCon .title{
            color: #333;
            font-size: 13px;
            line-height: 34px;
        }
        .selectCon .contBox{
            width: 104px;
            height: 32px;
            color: #333;
            font-size: 13px;
            border: 1px solid #d7d7d7;
            margin-left: 10px;
            position: relative;
            z-index: 100;
        }
        .selectCon .contBox div{
            float: left;
            height: 32px;
        }
        .selectCon .contBox .jiantou{
            width: 32px;
            border-left: 1px solid #d7d7d7;
            background: url(../images/u1017.png) no-repeat center;
            cursor: pointer;
        }
        .selectCon .contBox .inputBox{
            width: 51px;
            line-height: 32px;
            padding-left: 20px;
        }
        .selectCon .optionDiv{
            display: none;
            width: 71px;
            border: 1px solid #d7d7d7;
            border-top: 0;
            line-height: 28px;
            position: absolute;
            top: 33px;
            left: -1px;
            background: #fff;
        }
        .selectCon .optionDiv li{
            cursor: pointer;
            padding-left: 20px;
        }
        .selectCon .optionDiv li:hover{
            background: #adcffc;
        }
        
    </style>
</head>

<body>
    <div class="mainBox">
        <div class="selectBox">
            <div class="title">导演</div>
            <div class="selectDiv clearfix">
                <div class="label">性别：</div>
                <ul class="clearfix">
                    <li class="active" onclick="selectSix('')">全部</li>
                    <li onclick="selectSix('1')">男</li>
                    <li onclick="selectSix('0')">女</li>
                </ul>
            </div>
            <div class="selectDiv clearfix">
                <div class="label">地区：</div>
                <ul class="clearfix">
                    <li class="active" onclick="selectDis('')">全部</li>
                    <li onclick="selectDis('1')">内地</li>
                    <li onclick="selectDis('2')">香港</li>
                    <li onclick="selectDis('3')">台湾</li>
                    <li onclick="selectDis('4')">日本</li>
                    <li onclick="selectDis('5')">韩国</li>
                    <li onclick="selectDis('6')">欧美</li>
                </ul>
            </div>
        </div>
        <div class="listBox">
            <ul class="clearfix" id="dirList">
            
                <!-- <li class="listLi">
                    <div class="img"><img src="../images/u2746.jpg" alt=""></div>
                    <div class="titleName" >雷杰</div>
                </li>
                <li class="listLi">
                    <div class="img"><img src="../images/u2746.jpg" alt=""></div>
                    <div class="titleName">雷杰</div>
                </li>
                <li class="listLi">
                    <div class="img"><img src="../images/u2746.jpg" alt=""></div>
                    <div class="titleName">雷杰</div>
                </li>
                <li class="listLi">
                    <div class="img"><img src="../images/u2746.jpg" alt=""></div>
                    <div class="titleName">雷杰</div>
                </li>
                <li class="listLi">
                    <div class="img"><img src="../images/u2746.jpg" alt=""></div>
                    <div class="titleName">雷杰</div>
                </li>
                <li class="listLi">
                    <div class="img"><img src="../images/u2746.jpg" alt=""></div>
                    <div class="titleName" >雷杰</div>
                </li>
                <li class="listLi">
                    <div class="img"><img src="../images/u2746.jpg" alt=""></div>
                    <div class="titleName">雷杰</div>
                </li>
                <li class="listLi">
                    <div class="img"><img src="../images/u2746.jpg" alt=""></div>
                    <div class="titleName">雷杰</div>
                </li>
                <li class="listLi">
                    <div class="img"><img src="../images/u2746.jpg" alt=""></div>
                    <div class="titleName">雷杰</div>
                </li>
                <li class="listLi">
                    <div class="img"><img src="../images/u2746.jpg" alt=""></div>
                    <div class="titleName">雷杰</div>
                </li>
                <li class="listLi">
                    <div class="img"><img src="../images/u2746.jpg" alt=""></div>
                    <div class="titleName" >雷杰</div>
                </li>
                <li class="listLi">
                    <div class="img"><img src="../images/u2746.jpg" alt=""></div>
                    <div class="titleName">雷杰</div>
                </li>
                <li class="listLi">
                    <div class="img"><img src="../images/u2746.jpg" alt=""></div>
                    <div class="titleName">雷杰</div>
                </li>
                <li class="listLi">
                    <div class="img"><img src="../images/u2746.jpg" alt=""></div>
                    <div class="titleName">雷杰</div>
                </li>
                <li class="listLi">
                    <div class="img"><img src="../images/u2746.jpg" alt=""></div>
                    <div class="titleName">雷杰</div>
                </li> -->
                
            </ul>
            <div class="selectCon clearfix">
                <div class="title">每页显示:</div>
                <div class="contBox clearfix">
                    <div class="inputBox">20</div>
                    <div class="jiantou"></div>
                    <ul class="optionDiv">
                       <!--  <li>40</li>
                        <li>30</li> -->
                        <li>20</li>
                    </ul>
                </div>
                
            </div>
            <div class="ht-page"></div>
        </div>
    </div>
</body>
<script>
    // $(function() { 
        //头部选择
        $(".selectDiv ul li").on("click",function(){
            $(this).addClass('active').siblings().removeClass('active');
        })
        $(".jiantou").click(function(){
            $(".optionDiv").toggle();
        });
        $(".optionDiv li").on("click",function(){
            $(".optionDiv").hide();
            $(".inputBox").text($(this).text());
        })
    // });
    
   
 
</script>
</html>