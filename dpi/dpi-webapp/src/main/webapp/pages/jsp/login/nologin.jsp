<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.bonc.com.cn/common/tag/cxt" prefix="cxt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<cxt:commonLinks />
<script type="text/javascript">
$(function() {
    var loginToken = getQueryString("paramDpiRd");
    var debugFlag = getQueryString("debugFlag");
    var ticket = getQueryString("ticket");

    var userid = getQueryString("userid");
    var data ={}
    if(loginToken!=null){
        data = {"loginToken":loginToken}
        $.ajax({
            url : $.cxt + '/nologin',
            type : "POST",
            async : false,
            data:data,
            success : function(data) {
                if(data != "-1" && data != "-2") {
                    data = eval('(' + data + ')');
                    var sysRoleUsers = data.sysRoleUserList;
                    if(sysRoleUsers.length == 1) {
                        location.href =$.cxt+'/pages/jsp/frame/frameNew.jsp';
                    }
                } else {
                    alert('您好，请登录后，访问系统！');
                }
            }
        });
    }else {
        data = {"debugFlag" : debugFlag,"ticket" : ticket,"userid" : userid};
        console.log(data)
        $.ajax({
            url : $.cxt + '/nologin1',
            type : "POST",
            async : false,
            data:data,
            success : function(data) {
                if(data != "-1" && data != "-2") {
                    data = eval('(' + data + ')');
                    var sysRoleUsers = data.sysRoleUserList;
                    if(sysRoleUsers.length == 1) {
                        location.href =$.cxt+'/pages/jsp/frame/frameNew.jsp';
                    }
                } else {
                    alert('您好，请登录后，访问系统！');
                }
            }
        });
    }

    // var


	/* $.ajax({
		url : $.cxt + '/nologin?userid=' + userid + "&ticket=" + ticket+ "&debugFlag=" + debugFlag,
		type : "POST",
		async : false,
		data:"json",
		success : function(data) {
			if(data != "-1" && data != "-2") {
				data = eval('(' + data + ')');
				var sysRoleUsers = data.sysRoleUserList;
				if(sysRoleUsers.length == 1) {
					location.href =$.cxt+'/pages/jsp/frame/frameNew.jsp';
				}
			} else {
				alert('您好，请登录后，访问系统！');
			}
		}
	}); */
});

function getQueryString(paramname) {
	var reg = new RegExp("(^|&)"+ paramname +"=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r!=null)return  unescape(r[2]); return null;
}
</script>
<title>用户上网行为分析运营平台</title>
</head>
<body>
</body>
</html>
