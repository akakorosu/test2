<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.bonc.com.cn/common/tag/cxt" prefix="cxt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <cxt:commonLinks/>
    <title>服务能力预览</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/commonTwo.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/pages/jsp/dpi/capabilityPreview/bootstrap-table.css"/>
    <script src="<%=request.getContextPath()%>/pages/js/jquery-3.1.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/pages/jsp/dpi/capabilityPreview/capabilityPreview.js"></script>
    <script src="<%=request.getContextPath()%>/pages/jsp/dpi/capabilityPreview/bootstrap-table.js"></script>
    <script src="<%=request.getContextPath()%>/pages/jsp/dpi/capabilityPreview/Convert_Pinyin.js"></script>
    <!-- <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css"> -->

    <!-- <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/pages/jsp/dpi/capabilityPreview/bootstrap.min.css"/>
    <script src="<%=request.getContextPath()%>/pages/jsp/dpi/capabilityPreview/bootstrap.min.js"></script>
    <style>
        .tooltip-inner {
            max-width: 1000px;
            padding: 3px 8px;
            color: #fff;
            text-align: left;
            background-color: #000;
            border-radius: 4px;
        }

        .lr-mark1 label {
            font-weight: lighter;
        }
        .lr-mark1{
	      height:10px;
          }
        .list-title7 {
            background: url(../images/list-title-icon7.png) no-repeat 0 0;
            background-size: 30px;
        }
        .modal-dialog{
        z-index:99999;
        }
        

    </style>
</head>
<body style="background: #f4f3f4;">
<header>
    <img src="<%=request.getContextPath()%>/pages/images/new/u417.svg"/>数据图谱
</header>
<!-- 行业产品 -->
<div class="product" style="margin-top:10px;">
    <div class="product-left-title">行业产品库</div>
    <div class="product-overview clearfix">
        <h4 class="pull-left">
            <img src="../../../img/product-icon-title.png" style="width:24px;margin-right:4px;" alt="">
            行业标签<span id="countAllNum"></span>个，其中一级标签<span id="fristLabelNum"></span>个，二级标签<span
                id="secondLabelNum"></span>个
        </h4>
        <div class="product-search pull-right ">
           <!--  <span>行业标签</span> -->
            <span class="result">共检索到<span id="searchLabelNum"></span>个相关标签<button id="changePrev">上一个</button><button
            id="changeNext">下一个</button></span>
            <button class="product-btn" onclick="search();">查询标签</button>
            <button class="product-btn" onclick="searchProd();">查询产品</button>
            <input type="text" id="profession" placeholder="标签/产品"/>
            <!-- <div class="input-group">
            <input type="text" class="form-control" placeholder="Recipient's username" aria-label="Recipient's username" aria-describedby="basic-addon2">
            <div class="input-group-append">
               <button class="btn btn-outline-secondary" type="button">Button</button>
               <button class="btn btn-outline-secondary" type="button">Button</button>
                 </div>
            </div> -->
        </div>
    </div>
</div>
<div class="professionBox" id="professionBox">
    <%--<div class="column">
            <div class="col-title">生活类</div>
                <ul>
                    <li class="level"><img src="<%=request.getContextPath()%>/pages/images/new/u2185.png" />交通出行</li>
                    <li style="cursor:pointer;">客车</li>
                    <li>火车</li>
                    <li>飞机</li>
                    <li>出租车</li>
                    <li>公交地铁</li>
                    <li>共享单车</li>
                    <li>地图导航</li>
                </ul>
                <ul>
                    <li class="level"><img src="<%=request.getContextPath()%>/pages/images/new/u2185.png" />餐饮</li>
                    <li>外卖</li>
                    <li>美食</li>
                </ul>
                <ul>
                    <li class="level"><img src="<%=request.getContextPath()%>/pages/images/new/u2185.png" />住宿</li>
                    <li>期房</li>
                    <li>二手房</li>
                </ul>
                <ul>
                    <li class="level"><img src="<%=request.getContextPath()%>/pages/images/new/u2185.png" />家装</li>
                    <li>家居</li>
                    <li>家电</li>
                </ul>
            </div> --%>
    <%--  <li class="level"  id="test"><img src="<%=request.getContextPath()%>/pages/images/new/u2185.png" /><span  style="display: none">woaini</span>餐饮</li> --%>
</div>
</div>
</div>
<!-- 内容库 -->
<div class="content" style="margin-top:10px; display: none;">
    <div class="product-left-title">内容库</div>
    <div class="content-detail">
        <div class="detail-list" id="shipin">
            <div class="list-left ">
                <%--<img src="<%=request.getContextPath()%>/pages/images/new/u2246.png"/>--%>
                <img src="../../../img/product-icon2.jpg" alt="">
                <span>视频</span>
            </div>
            <div class="list-right">
                <div class="lr-mark" onclick="showLabel('AN00','0')" style="cursor:pointer;"><!--  -->
                    总标签 <span id="shipinAll"></span>
                </div>
                <div class="lr-mark1" id="shipinlvl">
                    <!-- <label>一级标签 <span>30</span></label>
                    <label>二级标签 <span>30</span></label>
                    <label>三级标签 <span>30</span></label> -->
                </div>
                <div class="proname">产品名称</div>
                <div class="imgbox" id="shipinimgbox">
                    <%-- <img src="<%=request.getContextPath()%>/pages/images/new/14.png" />
                    <img src="<%=request.getContextPath()%>/pages/images/new/u923.png" />
                    <img src="<%=request.getContextPath()%>/pages/images/new/u941.png" /> --%>
                </div>
                <div class="property">属性</div>
                <div class="prodetail" id="shipinattr">
                    <!-- <a href="#">作者</a><a href="#">字数</a><a href="#">点击量</a><a href="#">评分</a> -->
                </div>
            </div>
        </div>
        <div class="detail-list" id="dianshang">
            <div class="list-left">
                <%--<img src="<%=request.getContextPath()%>/pages/images/new/u2209.png"/>--%>
                <img src="../../../img/product-icon3.jpg" alt="">
                <span>电商</span>
            </div>
            <div class="list-right">
                <div class="lr-mark" onclick="showLabel('BT00','0')" style="cursor:pointer;">
                    总标签 <span id="dianshangAll"></span>
                </div>
                <div class="lr-mark1" id="dianshanglvl">
                    <!-- <label>一级标签 <span>30</span></label>
                    <label>二级标签 <span>30</span></label>
                    <label>三级标签 <span>30</span></label>
                    <label>三级标签 <span>30</span></label>
                    <label>三级标签 <span>30</span></label> -->
                </div>
                <div class="proname">产品名称</div>
                <div class="imgbox" id="dianshangimgbox">
                    <%-- <img src="<%=request.getContextPath()%>/pages/images/new/u941.png" />
                    <img src="<%=request.getContextPath()%>/pages/images/new/u950.jpg" />
                    <img src="<%=request.getContextPath()%>/pages/images/new/u959.png" />
                    <img src="<%=request.getContextPath()%>/pages/images/new/u932.png" /> --%>
                </div>
                <div class="property">属性</div>
                <div class="prodetail" id="dianshangattr">
                    <!-- <a href="#">作者</a><a href="#">字数</a><a href="#">点击量</a><a href="#">评分</a> -->
                </div>
            </div>
        </div>
        <div class="detail-list" id="xiaoshuo">
            <div class="list-left">
                <%--<img src="<%=request.getContextPath()%>/pages/images/new/u2228.png"/>--%>
                <img src="../../../img/product-icon1.jpg" alt="">
                <span>小说</span>
            </div>
            <div class="list-right">
                <div class="lr-mark" onclick="showLabel('AP00','0')" style="cursor:pointer;">
                    总标签 <span id="xiaoshuoAll"></span>
                </div>
                <div class="lr-mark1" id="xiaoshuolvl">
                    <!-- <label>一级标签 <span>30</span></label>
                    <label>二级标签 <span>30</span></label>
                    <label>三级标签 <span>30</span></label> -->
                </div>
                <div class="proname">产品名称</div>
                <div class="imgbox" id="xiaoshuoimgbox">
                    <%-- <img src="<%=request.getContextPath()%>/pages/images/new/QQ.png" />
                    <img src="<%=request.getContextPath()%>/pages/images/new/u959.png" /> --%>
                </div>
                <div class="property">属性</div>
                <div class="prodetail" id="xiaoshuoattr">
                    <!-- <a href="#">作者</a><a href="#">字数</a><a href="#">点击量</a><a href="#">评分</a> -->
                </div>
            </div>
        </div>
        <div class="detail-list">
            <div class="list-left">
                <%--<img src="<%=request.getContextPath()%>/pages/images/new/u2259.png"/>--%>
                <img src="../../../img/product-icon4.jpg" alt="">
                <span>音乐</span>
            </div>
            <div class="list-right">
                <div class="lr-mark">
                    总标签 <span>0</span>
                </div>
                <div class="lr-mark1">
                    
                </div>
                <div class="proname">产品名称</div>
                <div class="imgbox">
                    <img src="<%=request.getContextPath()%>/pages/images/new/14.png"/>
                    <img src="<%=request.getContextPath()%>/pages/images/new/14.png"/>
                </div>
                <div class="property">属性</div>
                <div class="prodetail">
                    <a href="#">无</a>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 用户画像 -->
<div class="user" id="userPic" style="display: none;">
    <div class="product-left-title">用户画像</div>
    <div class="sex">
        性别：&nbsp;&nbsp;<span>男/女</span>
    </div>
    <div class="profession" id="work">
        <span class="protitle">职业：</span>
        <!-- <div class="professionList">
            <label>生活|服务业</label>&emsp;/&emsp;餐饮&emsp;/&emsp;家政保洁&emsp;/&emsp;安保&emsp;/&emsp;美容&emsp;/&emsp;美发&emsp;/&emsp;旅游&emsp;/&emsp;娱乐&emsp;/&emsp;休闲&emsp;/&emsp;保健按摩&emsp;/&emsp;运动健身
        </div>
        <div class="professionList">
            <label>人力|行政|管理</label>&emsp;/&emsp;人事/行政/后勤&emsp;/&emsp;司机&emsp;/&emsp;高级管理
        </div>
        <div class="professionList">
            <label>销售|客服|采购|淘宝</label>&emsp;/&emsp;销售&emsp;/&emsp;客服&emsp;/&emsp;贸易采购&emsp;/&emsp;超市/百货/零售&emsp;/&emsp;淘宝职位&emsp;/&emsp;房产中介
        </div>
        <div class="professionList">
            <label>酒店</label>&emsp;/&emsp;酒店
        </div>
        <div class="professionList">
            <label>市场|媒介|广告|设计</label>&emsp;/&emsp;市场/媒介/公关&emsp;/&emsp;广告/会展/咨询&emsp;/&emsp;美术/设计/创意
        </div> -->
    </div>
    <div class="otherOpt" id="child">
        <!-- <label>孩子年龄段：</label>&emsp;0-1岁&emsp;/&emsp;2-3岁&emsp;/&emsp;4-6岁&emsp;/&emsp;7-12岁&emsp;/&emsp;13-16岁&emsp;/&emsp;小学生&emsp;/&emsp;初中生&emsp;/&emsp;高中生&emsp;/&emsp;大学生 -->
    </div>

    <div class="otherOpt">
        <label>是否有车：</label>&emsp;<span data-toggle="tooltip" title="违章查询类app访问行为用户--车险类app访问行为用户--网购汽车用品行为用户">有车</span>&emsp;/&emsp;<span
            data-toggle="tooltip" title="通过搜索关键词判断--访问汽车类网站--访问购车类app">有买车意向</span>
        <!-- &emsp;/&emsp;<span data-toggle="tooltip" title="aaaaaaaaaa">租车</span> -->
    </div>
    <!-- <button class="product-btn" onclick="update();">变变</button>  -->
</div>
</body>
</html>