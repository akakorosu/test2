$(function() {
	// 偏好类型
	$("#tarType").change(function(){
		// 初始化
		tarInit();
		var type = $("#tarType").val();
		if(type == "2") {
			$("#searchTar").show();
		} else if(type == "4") {
			$("#searchTar").show();
		} else if(type == "5") {
			$("#phnrsxtype").show();
		} else if(type == "3") {
			$("#phnrbqlx").show();
		} else if(type == "1") {
			$("#searchTar").show();
		}
	});
	// 内容属性
	$("#phnrbqlx").change(function(){
		tarInit_1();
		if($("#phnrbqlx").val() != "") {
			$("#searchTar").show();
		}
	});
	// 内容属性
	$("#phnrsxtype").change(function(){
		tarInit_1();
		tarInit_2();
		var type = $("#phnrsxtype").val();
		$("#phnrsxtype_" + type).show();
	});
	// 内容属性各个类别
	$("[name^='phnrsxtype_']").change(function(){
		tarInit_1();
		if($(this).val() != "") {
			$("#searchTar").show();
		}
	})
	// 查询keydown
	$("#tarName").keydown(function(){
		setTimeout(function(){
			getSelectTarType();
		},1000)
	});
	// 查询click
	$("#tarName").click(function(){
		getSelectTarType();
	});
	// 往表格里添加按钮
	$("#tarAddBtn").click(function(){
		var tarType = $("#tarType").val();
		var tarTypeText = $("#tarType").find("option:selected").text();
		// 内容属性偏好类型
		var phnrsxtype = $("#phnrsxtype").val();
		var phnrsxtypeText = $("#phnrsxtype").find("option:selected").text();
		// 内容属性偏好要查询的字段
		var phnrsxtypec = $("#phnrsxtype_" + phnrsxtype).val();
		var phnrsxtypecText = $("#phnrsxtype_" + phnrsxtype).find("option:selected").text();
		// 内容标签偏好类型
		var phnrbqlx = $("#phnrbqlx").val();
		var phnrbqlxText = $("#phnrbqlx").find("option:selected").text();
		// 如果有内容属性偏好类型则进行拼接
		if(phnrsxtype != "") {
			tarType = tarType + "#" + phnrsxtype + "#" + phnrsxtypec;
			tarTypeText = tarTypeText + "#" + phnrsxtypeText + "#" + phnrsxtypecText;
		}
		// 如果有内容标签偏好类型则进行拼接
		if(phnrbqlx != "") {
			tarType = tarType + "#" + phnrbqlx
			tarTypeText = tarTypeText + "#" + phnrbqlxText
		}
		var tarName = $("#tarName").val();
		var tarCode = $("#tarCode").val();
		var tarQuota = $("#tarQuota").val();
		var tarQuotaText = $("#tarQuota").find("option:selected").text();
		var tarSign = $("#tarSign").val();
		var tarSignText = $("#tarSign").find("option:selected").text();
		var tarValue = $("#tarValue").val();
		if(tarCode == "") {
			topshowAlertErrorDiv("请选择一个名称！")
			return;
		}
		if(tarValue == "") {
			topshowAlertErrorDiv("数值不能为空！")
			return;
		}
		if(!topcheckIsNumValue(tarValue)) {
			topshowAlertErrorDiv("数值只能为数字！")
			return;
		}
		if(!topcheckIsNumValue(tarValue)) {
			topshowAlertErrorDiv("数值只能为数字！")
			return;
		}
		var o = {
			tarType : tarType,
			tarTypeText : tarTypeText,
			tarName : tarName,
			tarCode : tarCode,
			tarQuota : tarQuota,
			tarQuotaText : tarQuotaText,
			tarSign : tarSign,
			tarSignText : tarSignText,
			tarValue : tarValue
		}
		// 拼装tr
		getTableTrHtml(o);
	})
	// 点击其他元素将下拉框隐藏
	$("#dmDtarRuleInfoForm").click(function(e){
		if($(e.target).attr("id") != "searchTarInputDiv" && $(e.target).attr("id") != "tarName") {
			doSearchTarInputDivHide()
		}
	});
});
// 隐藏searchTarInputDiv
var doSearchTarInputDivHide = function() {
	$("#searchTarInputDiv").hide();
	$("#searchTarInputDiv").html("");
}
// 后台模糊查询
var getSelectTarType = function() {
	var value = $("#tarName").val();
	var type = $("#tarType").val();
	if(value == "") {
		doSearchTarInputDivHide()
		return;
	}
	var html = "";
	if(type == "2") {
		// 行业标签
		html = getSelectTarType2(value);
	} else if(type == "4") {
		// 内容动作
		html = getSelectTarType4(value);
	} else if(type == "5") {
		// 内容属性
		html = getSelectTarType5(value);
	} else if(type == "3") {
		// 内容标签
		html = getSelectTarType3(value);
	} else if(type == "1") {
		// 产品
		html = getSelectTarType1(value);
	}
	$("#searchTarInputDiv").html(html);
	if(html != "") {
		$("#searchTarInputDiv").show();
	}
}
// 拼接每个tr
var getTableTrHtml = function(obj) {
	var html = "<tr>";
	html += "<input type='hidden' name='tarName' value='" + obj.tarName + "'>";
	html += "<input type='hidden' name='tarCode' value='" + obj.tarCode + "'>";
	html += "<input type='hidden' name='tarType' value='" + obj.tarType + "'>";
	html += "<input type='hidden' name='tarSign' value='" + obj.tarSign + "'>";
	html += "<input type='hidden' name='tarValue' value='" + obj.tarValue + "'>";
	html += "<input type='hidden' name='tarQuota' value='" + obj.tarQuota + "'>";
	html += "<input type='hidden' name='tarTypeText' value='" + obj.tarTypeText + "'>";
	html += "<td>" + obj.tarTypeText + "</td>";
	html += "<td>" + obj.tarName + "</td>";
	html += "<td>" + obj.tarQuotaText + "</td>";
	html += "<td>" + obj.tarSignText + "</td>";
	html += "<td>" + obj.tarValue + "</td>";
	html += "<td>";
	html += "<button  class='btn btn-xs btn-primary' type='button' id='tarDeleteBtn' onclick='clicktarDeleteBtn(this)'><i class='glyphicon glyphicon-minus'></i></button>";
	// 如果tr不是第一个则加and or的下拉选
	if($("#simple-table tbody tr").length != 0) {
		html += "<select name='tarAndOr' style=\"margin-left:5px;\">";
		html += "<option value='and'>and</option>"
		if(obj.tarAndOr == "or") {
			html += "<option value='or' selected='selected'>or</option>"
		} else {
			html += "<option value='or'>or</option>"
		}
		html += "</select>";
	}
	html += "</td></tr>";
	$("#simple-table tbody").prepend(html);
}
// searchTarInputDiv上的a标签的点击事件
var setSearchTarInput = function(o) {
	$("#tarName").val(o.labelName);
	$("#tarCode").val(o.labelCode);
}
// 拼接searchTarInputDiv上的a标签
var getClickSearchTarInputDivHtml = function(o) {
	var os = JSON.stringify(o);
	var html = "<a onclick='setSearchTarInput(" + os + ")' href='#' labelCode='" + o.labelCode + "'>" + o.labelName + "</a>"
	return html;
}
var tarInit = function() {
	tarInit_1();
	$("#phnrsxtype").val("")
	$("#phnrsxtype").hide();
	$("#phnrbqlx").val("")
	$("#phnrbqlx").hide();
	tarInit_2();
}
var tarInit_1 = function() {
	$("#searchTar").hide();
	$("#tarName").val("")
	$("#tarCode").val("")
	$("#tarValue").val("")
	$("#tarSign").val("1")
	$("#tarQuota").val("1")
}
var tarInit_2 = function() {
	$("[id^='phnrsxtype_']").hide();
	$("[id^='phnrsxtype_']").val("")
}
// tr上的删除按钮
var clicktarDeleteBtn = function(o) {
	if($(o).siblings("select").length == 0) {
		// 如果删除按钮同级没有select，则需要删除上一个tr的select，要保证最后一个tr没有select
		$(o).parents("tr").prev().find("select").remove();
	}
	$(o).parents("tr").remove();
}

//--------------------产品查询start--------------------
var getSelectTarType1 = function(v) {
	var html = "";
	var data = {}
	data.prodName = v
	$.ajax({//模糊查询标签
		url : $.cxt + "/productInfo/selectlist",
		dataType : "json",
		data : data,
		type : "post",
		loading : 0,//去掉加载遮盖层
		async:false,
		success : function(data) {
			if(data.length != 0) {
				for(var i = 0; i < data.length; i ++) {
					var o = data[i];
					html += "<div>";
					html += getClickSearchTarInputDivHtml({labelCode:o.prodId,labelName:o.prodName});
					html += "</div>";
				}
			} else {
				html += "没有查询到相关信息！";
			}
		}
	});
	return html;
}
//--------------------产品查询end--------------------

//--------------------内容标签查询start--------------------
var getSelectTarType3 = function(v) {
	var html = "";
	var data = {}
	var phnrbqlx = $("#phnrbqlx").val()
	data.contentLabelName = v
	data.contentLabelCode1 = phnrbqlx
	$.ajax({//模糊查询标签
		url : $.cxt + "/content/selectlist",
		dataType : "json",
		data : data,
		type : "post",
		loading : 0,//去掉加载遮盖层
		async:false,
		success : function(data) {
			if(data.length != 0) {
				for(var i = 0; i < data.length; i ++) {
					var o = data[i];
					html += "<div>";
					if(o.contentLabelCode1 != null) {
						html += getClickSearchTarInputDivHtml({labelCode:o.contentLabelCode1,labelName:o.contentLabelName1});
						html += "&nbsp;&nbsp;&nbsp;&nbsp;";
					}
					if(o.contentLabelCode2 != null) {
						html += getClickSearchTarInputDivHtml({labelCode:o.contentLabelCode2,labelName:o.contentLabelName2});
						html += "&nbsp;&nbsp;&nbsp;&nbsp;";
					}
					if(o.contentLabelCode3 != null) {
						html += getClickSearchTarInputDivHtml({labelCode:o.contentLabelCode3,labelName:o.contentLabelName3});
						html += "&nbsp;&nbsp;&nbsp;&nbsp;";
					}
					if(o.contentLabelCode4 != null) {
						html += getClickSearchTarInputDivHtml({labelCode:o.contentLabelCode4,labelName:o.contentLabelName4});
						html += "&nbsp;&nbsp;&nbsp;&nbsp;";
					}
					if(o.contentLabelCode5 != null) {
						html += getClickSearchTarInputDivHtml({labelCode:o.contentLabelCode5,labelName:o.contentLabelName5});
						html += "&nbsp;&nbsp;&nbsp;&nbsp;";
					}
					if(o.contentLabelCode6 != null) {
						html += getClickSearchTarInputDivHtml({labelCode:o.contentLabelCode6,labelName:o.contentLabelName6});
						html += "&nbsp;&nbsp;&nbsp;&nbsp;";
					}
					html += "</div>";
				}
			} else {
				html += "没有查询到相关信息！";
			}
		}
	});
	return html;
}
//--------------------内容标签查询end--------------------

//--------------------内容属性查询start--------------------
var getSelectTarType5 = function(v) {
	// 内容属性类型
	var phnrsxtype = $("#phnrsxtype").val();
	// 内容属性
	var key = $("#phnrsxtype_" + phnrsxtype).val();
	// 内容属性字典key（name或name,id）,用逗号分割
	var keys = key.split(",");
	// 默认name和id都给赋值为下标0
	var id = keys[0];
	var name = keys[0];
	// 字典key如果有逗号分割
	if(keys.length == 2) {
		// id赋值为下标1
		id = keys[1];
	} else {
		// 如果字典key没有逗号分割则直接将tarName值赋值给tarCode
		$("#tarCode").val(v);
		// 跳出，这种情况暂时不需要联想查询
		return;
	}
	var html = "";//拼接下拉框html
	var data = {};
	var url;
	// 给name赋值，用于后台查询
	data[name] = v;
	if(phnrsxtype == "1") {
		url = $.cxt + "/dmdevvideoinfo/selectlist";
	} else if(phnrsxtype == "2") {
		url = $.cxt + "/dmdevgoodsinfo/selectlist";
	} else if(phnrsxtype == "3") {
		url = $.cxt + "/dmdevnovelinfo/selectlist";
	}
	$.ajax({//模糊查询标签
		url : url,
		dataType : "json",
		data : data,
		type : "post",
		loading : 0,//去掉加载遮盖层
		async:false,
		success : function(data) {
			if(data.length != 0) {
				for(var i = 0; i < data.length; i ++) {
					var o = data[i];
					html += "<div>";
					html += getClickSearchTarInputDivHtml({labelCode:o[id],labelName:o[name]});
					html += "</div>";
				}
			} else {
				html += "没有查询到相关信息！";
			}
		}
	});
	return html;
}
//--------------------内容属性查询end--------------------

//--------------------内容动作查询start--------------------
var getSelectTarType4 = function(v) {
	var html = "";//拼接下拉框html
	var data = {channelName : v, noRuleType : "content"};
	$.ajax({//模糊查询标签
		url : $.cxt + "/keywordSearchRule/selectlistsimple",
		dataType : "json",
		data : data,
		type : "post",
		loading : 0,//去掉加载遮盖层
		async:false,
		success : function(data) {
			if(data.length != 0) {
				for(var i = 0; i < data.length; i ++) {
					var o = data[i];
					html += "<div>";
					html += getClickSearchTarInputDivHtml({labelName:o.channelName, labelCode:o.id});
					html += "</div>";
				}
			} else {
				html += "没有查询到相关信息！";
			}
		}
	});
	return html;
}
//--------------------内容动作查询end--------------------

//--------------------行业标签查询start--------------------
var getSelectTarType2 = function(v) {
	var html = "";//拼接下拉框html
	var data = {labelNameLike : v};
	$.ajax({//模糊查询标签
		url : $.cxt + "/productLabel/selectlist",
		dataType : "json",
		data : data,
		type : "post",
		loading : 0,//去掉加载遮盖层
		async:false,
		success : function(data) {
			if(data.length != 0) {
				for(var i = 0; i < data.length; i ++) {
					var o = data[i];
					html += "<div>";
					html += getSetLabelCloseWindowHtml(o, "1");
					html += "&nbsp;&nbsp;&nbsp;&nbsp;";
					html += getSetLabelCloseWindowHtml(o, "2");
					html += "</div>";
				}
			} else {
				html += "没有查询到相关信息！";
			}
		}
	});
	return html;
}
var getSetLabelCloseWindowHtml = function(obj, level) {
	var o = {};
	if(level == "1") {
		o = {
			labelCode : obj.labelCode1,
			labelName : obj.labelName1,
		}
	}else if(level == "2") {
		o = {
			labelCode : obj.labelCode2,
			labelName : obj.labelName2,
		}
	}
	return getClickSearchTarInputDivHtml(o);
}
//--------------------行业标签查询end--------------------
