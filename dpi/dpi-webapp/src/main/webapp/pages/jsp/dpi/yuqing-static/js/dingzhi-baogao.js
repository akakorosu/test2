//该js实现了列表文章的增删,列表排序
$(function () {
    var currentClick;
    var count;
    var currentXuhao;
    var xuhao;
    //文章/负面文章/电信行业舆情
    $(".article_fan").on('dblclick', "tr td", function (e) {
        var pageX = e.pageX,
            pageY = e.pageY;
        currentClick = $(this).parent("tr");
        count = $("#article_count");
        xuhao = $(".xuhao_article");
        if ($(this).parents("tbody").attr("id") == "negative_article") {
            count = $("#negative_count");
            xuhao = $(".xuhao_negative");
        }
        if ($(this).parents("tbody").attr("id") == "dx_article") {
            count = $("#dx_count");
            xuhao = $(".xuhao_dx");
        }
        currentXuhao = parseInt($(this).prev("td").text());
        $("#btn_fan1").css({
            top: pageY,
            left: pageX
        });
        $("#btn_fan1").css("display", "inline-block");
    });
    //微博网页
    $(".weiwang_fan").on('dblclick', " span h4", function (e) {
        var pageX = e.pageX,
            pageY = e.pageY;
        currentClick = $(this).parent("span");
        currentXuhao = parseInt($(this).children("span").text());
        xuhao = $(".xuhao_weibo");
        if ($(this).parents("div").attr("id") == "wangye_fan") {
            xuhao = $(".xuhao_wangye");
        }
        $("#btn_fan3").css({
            top: pageY,
            left: pageX
        });
        $("#btn_fan3").css("display", "inline-block");
    });
    //热门词云
    $(".word_fan ul li").on('dblclick', function (e) {
        var pageX = e.pageX,
            pageY = e.pageY;
        currentClick = $(this);
        count = $("#word_count");
        $("#btn_fan2").css({
            top: pageY,
            left: pageX
        });
        $("#btn_fan2").css("display", "inline-block");
    });
    //退出按钮点击时
    $(".btn_exit").on("click", function () {
        $("#btn_fan1").css("display", "none");
        $("#btn_fan2").css("display", "none");
        $("#btn_fan3").css("display", "none");
    });
    //删除文章/负面文章/电信行业舆情时
    $(".delete_fan1").on("click", function () {
        currentClick.remove();
        xuhao.each(function () {
            if ($(this).text() > currentXuhao) {
                $(this).text($(this).text() - 1);
            }
        });
        $("#btn_fan1").css("display", "none");
        count.text(count.text() - 1);
    });
    //删除微博/网页文章时
    $(".delete_fan3").on("click", function () {
        currentClick.remove();
        xuhao.each(function () {
            if ($(this).text() > currentXuhao) {
                $(this).text($(this).text() - 1);
            }
        });
        $("#btn_fan3").css("display", "none");
    });
    //删除词云时
    $(".delete_fan2").on("click", function () {
        currentClick.remove();
        count.text(count.text() - 1);
        $("#btn_fan2").css("display", "none");
    });
    //增加按钮1
    $("#btn_add1").on("click", function () {
        $("#btn_fan1").css("display", "none");
        $("#mymodal1").css("display", "inline-block");
    });
    //日历插件
    $("#date").datetimepicker({
        format: 'yyyy-mm-dd hh:ii',
        language: 'zh-CN',
        autoclose: 1,
        minuteStep: 3
    });
    $("#date2").datetimepicker({
        format: 'yyyy-mm-dd hh:ii',
        language: 'zh-CN',
        autoclose: 1,
        minuteStep: 3
    });

    function clear1() {
        $("#mymodal1").css("display", "none");
        $("#title1").val("");
        $("#url1").val("");
        $("#source1").val("media");
        $("#date").val("");
        $("#net1").val("");
    }

    //取消按钮1
    $("#btn-quxiao1").on("click", function () {
        clear1();
    });
    //确定按钮1
    $("#btn-queding1").on("click", function () {
        var xuhao_class = "xuhao_article";
        if (currentClick.parents("tbody").attr("id") == "negative_article") {
            xuhao_class = "xuhao_negative";
        }
        if (currentClick.parents("tbody").attr("id") == "dx_article") {
            xuhao_class = "xuhao_dx";
        }
        currentClick.after("<tr><td class='" + xuhao_class + "'>" + (currentXuhao + 1) + "</td><td style='cursor: pointer;'><a href='" + $("#url1").val() + "' target='_blank'>" + $("#title1").val() + "</a></td><td class='" + $("#source1").val() + "'><i></i><span>" + $("#source1 option:selected").text() + "</span></td><td>"+$("#net1").val()+"</td><td>" + ($("#date").val() + ":00") + "</td></tr>");
        xuhao.each(function () {
            if ($(this).text() > currentXuhao) {
                $(this).text(parseInt($(this).text()) + 1);
            }
        });
        count.text(parseInt(count.text()) + 1);
        clear1();
    });
    //增加按钮2
    $("#btn_add2").on("click", function () {
        $("#btn_fan3").css("display", "none");
        $("#mymodal2").css("display", "inline-block");
    });
    //取消按钮2
    $("#btn-quxiao2").on("click", function () {
        clear2();
    });

    function clear2() {
        $("#mymodal2").css("display", "none");
        $("#title2").val("");
        $("#content2").val("");
        $("#url2").val("");
        $("#date2").val("");
        $("#pinglun2").val("");
        $("#net2").val("");
    }

    //确定按钮2
    $("#btn-queding2").on("click", function () {
        var xuhao_class = "xuhao_weibo";
        var wen = "微博文章";
        if (currentClick.parents("div").attr("id") == "wangye_fan") {
            xuhao_class = "xuhao_wangye";
            wen = "网页文章";
        }
        currentClick.after("<span><h4>" + wen + "<span class='" + xuhao_class + "'>" + (currentXuhao + 1) + "</span>：<i>" + $("#title2").val() + "</i></h4><h5>" + $("#content2").val() + "</h5><ul><li>浏览评论量：<span class='llpl'>" + $("#pinglun2").val() + "</span></li><li>发布时间：<span class='time'>" + ($("#date2").val() + ":00") + "</span></li><li>文章来源：<span class='source' style='margin-right:30px;'>" + $("#source2 option:selected").text() + "</span></li></ul><p>网址链接：<a href='" + $("#url2").val() + "' target='_blank'>" + $("#url2").val() + "</a></p></span>");
        xuhao.each(function () {
            if ($(this).text() > currentXuhao) {
                $(this).text(parseInt($(this).text()) + 1);
            }
        });
        clear2();
    });
});
