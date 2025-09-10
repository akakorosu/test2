<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cxt" uri="http://www.bonc.com.cn/common/tag/cxt"%>
<link rel="stylesheet" href="productInfo/css/bootstrap.css" />
<!-- 表单div -->
<div  id="prodIdList">
	<table style="width: 750px; overflow-x:hidden; " class="table table-striped table-hover table-bordered">
		<input id = "type" name="type" value="${type}" type="hidden"/>
		<p style="color: green">温馨提示：规则，URL，位置（字符串中出现的位置）都需要填写，其中位置是数字</p>
		<p id="p1" style="color: red;font-size: 16px;"></p>
        <tr>
       		<td>
		    	<label >&nbsp;规则：</label>
		    	<input id ="rule" name = "rule" value = "${rule}" style="width:650px;height:30px;">
	    	</td>
   		</tr>
    	<tr>
    		<td>
		     	<label>&nbsp;URL：</label>
		    	<input id ="url" name = "url" value = "" style="width:650px;height:30px;">
    		</td>
    	</tr>
    	<tr>
    		<td>
		     	<label>&nbsp;位置：</label>
		    	<input id ="numLocation" name = "numLocation" value = "" class="formField" style="width:650px;height:30px;">
    		</td>
    	</tr>    	
    	<tr>
    		<td>
		     	<label>&nbsp;结果：</label>
		    	<input id ="value" name = "value" value = "" readonly="readonly"  style="width:650px;height:30px;">
    		</td>
    	</tr>    	
	     <tr align="center">
	     	<td>
	     		<button class="btn btn-success" id="checkParseRuleIos" onclick="testRule()">测&nbsp;&nbsp;&nbsp;&nbsp;试</button>
	     		<button class="btn btn-success" id="checkParseRuleIos" onclick="isUsable('1')">可&nbsp;&nbsp;&nbsp;&nbsp;用</button>
		   	 	<button class="btn btn-danger" id="checkParseRuleIos" onclick="isUsable('0')">不可用</button>
			</td>
	     </tr>    	
     </table>

 </div>

<script>
	//选择产品
	function testRule(){
		
		var rule = $("#rule").val();
		var url = $("#url").val();
		var num = $("#numLocation").val();//截取位置
		
		if(rule==""){
			$("#p1").html("请填写规则rule");
			clearP1();
			return;
		}
		if(url==""){
			$("#p1").html("请填写规则url");
			clearP1();
			return;
		}
// 		if(num==""){
// 			$("#p1").html("请填写规则位置");
// 			clearP1();
// 			return;
// 		}
// 		if(!isRealNum(num)){
// 			$("#p1").html("规则位置请填写数字");
// 			clearP1();
// 			return;
// 		}
		$.ajax({
			url : $.cxt + "/keywordSearchRule/checkRule",
			data:{
				rule:encodeURIComponent(rule),
				url:encodeURIComponent(url),
				num:num
			},
			type:'get',
			dataType:'text',
			success : function(data) {
				$("#value").val(data);
			}
		});
	}
	//1.5秒后清空提示语
	function clearP1(){
		setTimeout(function(){  
			$("#p1").html("");
		},1500);
	}
	
	//验证是否是数字
	function isRealNum(val){
	    // isNaN()函数 把空串 空格 以及NUll 按照0来处理 所以先去除
	    if(val === "" || val ==null){
	        return false;
	    }
	    if(!isNaN(val)){
	        return true;
	    }else{
	        return false;
	    }
	}
	
	//赋值是否可用
	function isUsable(value){
		var type = $("#type").val();
		titleName="";
		if(value==1){
			titleName="确定可用？";
		}else if(value==0){
			titleName="确定不可用？";
		}
		topshowdialog(titleName, function(){
			$("#isUsable").val(value);
			if(value==0){
				$("#isUsableName").val("不可用");
				$("#isUsableName").css("color","red");
			}else if(value==1){
				$("#isUsableName").val("可用");
				$("#isUsableName").css("color","green");
				
				//回显到Andriod或者Ios
				if(type==1){
					$("#parseRuleAndriod").val($("#rule").val());
					$("#getIndexAndriod").val($("#numLocation").val());
					$("#urlExample").val($("#url").val());
				}else if(type==2){
					$("#parseRuleIos").val($("#rule").val());
					$("#getIndexIos").val($("#numLocation").val());
				}
				topwindow.removeWindow();
			}
		});
		
	}
</script>