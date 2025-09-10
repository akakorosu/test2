<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.bonc.com.cn/common/tag/cxt" prefix="cxt"%>
<!DOCTYPE html>
<html>
<head>
    <cxt:commonLinks />
    <meta charset='utf-8'>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/bootstrap-multiselect.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/common.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/operateCenter.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/pages/css/industryPic.css" />


    <script type="text/javascript" src="<%=request.getContextPath()%>/pages/js/bootstrap-multiselect.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/pages/jsp/dpi/industryPic/userPic.js"></script>
</head>
<body style="background: #fff;">
<div class="page-content clearfix">
    <form action="" class="form-inline">
        <div class="form-group">
            <label>账期：</label>
            <input type="text" class="form-control datepicker-input" placeholder="不限" id="time1">
        </div>
        <div class="form-group">
            <label>标签：</label>
            <input  type="text" placeholder="全部" id="labelName2" name="labelName2" readOnly="readonly" onclick="sntest123()">
        </div>
    </form>
    <div class="clearfix">
        <div class="pull-left pic-container">
            <div class="pic-main">
                <div class="pic-person pic-man">
                    <p>姓名：张动动</p>
                    <p>年龄：24</p>
                    <p>地区：吉林市</p>
                </div>
                <div class="pic-point pic-point1">
                    <div class="pull-left">
                        <div class="title">
                            <img src="<%=request.getContextPath()%>/pages/img/pic-icon1.png" alt="">自然特征
                        </div>
                        <ul class="pic-box pic-box1">
                            <li>职业: XXXX</li>
                            <li>消费水平: XXXX</li>
                            <li>是否有房: XXXX</li>
                            <li>是否有车: XXXX</li>
                            <li>孩子年龄段: XXXX</li>
                            <li>家庭角色: XXXX</li>
                        </ul>
                    </div>
                    <img src="<%=request.getContextPath()%>/pages/img/pin-line1.png" alt="" class="pic-line1 pull-left">
                </div>
                <div class="pic-point pic-point2">
                    <img src="<%=request.getContextPath()%>/pages/img/pin-line2.png" alt="" class="pic-line2 pull-left">
                    <div class="pull-left">
                        <div class="title">
                            <img src="<%=request.getContextPath()%>/pages/img/pic-icon2.png" alt="">音乐偏好
                        </div>
                        <ul class="pic-box pic-box2">
                            <li>摇滚</li>
                            <li>流行</li>
                            <li>欧美</li>
                            <li>电台</li>
                            <li>经典</li>
                        </ul>
                    </div>
                </div>
                <div class="pic-point pic-point3">
                    <div class="pull-left">
                        <div class="title">
                            <img src="<%=request.getContextPath()%>/pages/img/pic-icon3.png" alt="">视频偏好
                        </div>
                        <ul class="pic-box pic-box3">
                            <li>电视剧</li>
                            <li>电影</li>
                            <li>综艺</li>
                            <li>喜剧</li>
                            <li>恐怖</li>
                        </ul>
                    </div>
                    <img src="<%=request.getContextPath()%>/pages/img/pin-line3.png" alt="" class="pic-line3 pull-left">
                </div>
                <div class="pic-point pic-point4">
                    <img src="<%=request.getContextPath()%>/pages/img/pin-line4.png" alt="" class="pic-line4 pull-left">
                    <div class="pull-left">
                        <div class="title">
                            <img src="<%=request.getContextPath()%>/pages/img/pic-icon4.png" alt="">小说偏好
                        </div>
                        <ul class="pic-box pic-box4">
                            <li>都市</li>
                            <li>言情</li>
                            <li>玄幻</li>
                            <li>修真</li>
                            <li>XXXXXX</li>
                        </ul>
                    </div>
                </div>
                <div class="pic-point pic-point5">
                    <div class="pull-left">
                        <div class="title">
                            <img src="<%=request.getContextPath()%>/pages/img/pic-icon5.png" alt="">APP偏好
                        </div>
                        <ul class="pic-box pic-box5">
                            <li>微信</li>
                            <li>淘宝</li>
                            <li>京东</li>
                            <li>今日头条</li>
                            <li>墨迹天气</li>
                        </ul>
                    </div>
                    <img src="<%=request.getContextPath()%>/pages/img/pin-line5.png" alt="" class="pic-line5 pull-left">
                </div>
                <div class="pic-point pic-point6">
                    <img src="<%=request.getContextPath()%>/pages/img/pin-line6.png" alt="" class="pic-line6 pull-left">
                    <div class="pull-left">
                        <div class="title">
                            <img src="<%=request.getContextPath()%>/pages/img/pic-icon6.png" alt="">行业偏好
                        </div>
                        <ul class="pic-box pic-box6">
                            <li>教育</li>
                            <li>母婴</li>
                            <li>旅游</li>
                            <li>影视</li>
                            <li>游戏</li>
                            <li>房产</li>
                            <li>汽车</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="pull-left slide">
            <h2 class="about-title">
                推荐营销产品
                <a class="pull-right" href="#">换一换 <img src="<%=request.getContextPath()%>/pages/img/pic-icon-refresh.png" alt=""></a>
            </h2>
            <ul class="about-list">
                <li class="clearfix">
                    <span class="pull-left num num-red">1</span>
                    <span class="text">推荐流量包一元100M</span>
                    <span class="new-box">新</span>
                </li>
                <li class="clearfix">
                    <span class="pull-left num num-orange">2</span>
                    <span class="text">推荐流量包一元100M</span>
                    <span class="new-box">新</span>
                </li>
                <li class="clearfix">
                    <span class="pull-left num num-yellow">3</span>
                    <span class="text">推荐流量包一元100M</span>
                    <span class="new-box">新</span>
                </li>
                <li class="clearfix">
                    <span class="pull-left num">4</span>
                    <span class="text">推荐流量包一元100M</span>
                </li>
                <li class="clearfix">
                    <span class="pull-left num">5</span>
                    <span class="text">推荐流量包一元100M</span>
                </li>
                <li class="clearfix">
                    <span class="pull-left num">6</span>
                    <span class="text">推荐流量包一元100M</span>
                </li>
                <li class="clearfix">
                    <span class="pull-left num">7</span>
                    <span class="text">推荐流量包一元100M</span>
                </li>
                <li class="clearfix">
                    <span class="pull-left num">8</span>
                    <span class="text">推荐流量包一元100M</span>
                </li>
                <li class="clearfix">
                    <span class="pull-left num">9</span>
                    <span class="text">推荐流量包一元100M</span>
                </li>
                <li class="clearfix">
                    <span class="pull-left num">10</span>
                    <span class="text">推荐流量包一元100M</span>
                </li>
            </ul>
            <p class="more">加载更多>></p>
            <h2 class="about-title had-title">
                已订购产品
                <a class="pull-right" href="#"><img src="<%=request.getContextPath()%>/pages/img/pic-icon-refresh.png" alt=""></a>
            </h2>
            <ul class="had-list">
                <li>
                    <p class="title">新69元流量王</p>
                    <p class="dec">生效时间 2018-12-04 00:00:00</p>
                    <p class="dec">长期有效</p>
                </li>
                <li>
                    <p class="title">新69元流量王</p>
                    <p class="dec">生效时间 2018-12-04 00:00:00</p>
                    <p class="dec">长期有效</p>
                </li>
                <li>
                    <p class="title">新69元流量王</p>
                    <p class="dec">生效时间 2018-12-04 00:00:00</p>
                    <p class="dec">长期有效</p>
                </li>
            </ul>
            <p class="more">加载更多>></p>
        </div>
    </div>
</div>
</body>
</html>
