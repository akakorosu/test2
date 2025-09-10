$(function() {
	$.ajax({
		url : $.cxt + "/domain/searchfromproductinfo",
		dataType:"json",
		async : true,
		type : "POST",
		success : function(data) {
			var prodNames = [];
			for(var i in data){
				prodNames.push(data[i].prodName);
			}
			$('#prodId').autocomplete({
				source:prodNames
			});	
		}
	});
	
	$.ajax({
		url : $.cxt + "/domain/searchfromsuffix",
		dataType:"json",
		async : true,
		type : "POST",
		success : function(data) {
			var domainCodes = [];
			for(var i in data){
				domainCodes.push(data[i].domainCode);
			}
			$('#prodName').autocomplete({
				source:domainCodes
			});	
		}
	});
	
	$('#quxiao').click(function(){
		document.getElementById("prodctInfoForm").style.display="none";
	});
	$('#queren').click(function(){
		addProductInfo();
	});
})

function addProductInfo(){
	var prodName = $('#pName').val();
	/*$.ajax({
		url : $.cxt + "/product/insert",
		data : {
			prodName : prodName
		},
		async : true,
		type : "POST",
		success : function(data) {
		}
	});
	*/
	 	if($("#alertSuccessDiv").length > 0) {
			$("#alertSuccessDiv").remove();
		}
		var html = "" + 
		"<div class=\"alert alert-success fade in\" style=\"position: absolute;z-index:1111\" id=\"alertSuccessDiv\">" + 
			"<strong>&nbsp&nbsp添加成功&nbsp&nbsp!</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + 
		"</div>";
		$("body").append(html);
		topsetWindowCenter($("#alertSuccessDiv"));
		setTimeout(function(){
			$("#alertSuccessDiv").remove();
		},3000); 
		
	 $('#prodId').val(prodName);
	 document.getElementById("prodctInfoForm").style.display="none";
}

function test(){
	document.getElementById("prodctInfoForm").style.display="";
}

var flag = true;
var showMessage = function(obj) {
	var value = obj.value;
	var reg = /^(?=^.{3,255}$)(http(s)?:\/\/)?(www\.)?[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+(:\d+)*(\/\w+\.\w+)*$/;
	if (!reg.test(value)) {
		flag = false;
		$('#DOMAINCODE').html("域名格式不正确");
	} else{
		flag = true;
		$('#DOMAINCODE').html("");

	}
}

var checkReplaceName = function(obj) {
	var value = obj.value;
	var domainCode =document.getElementById("dc").value;
	if (flag) {
		$.ajax({
			url : $.cxt + "/domain/checkdomaincode",
			data : {
				domainCode : domainCode+value
			},
			async : true,
			type : "POST",
			success : function(data) {
				if (data == "yes") {
					$('#DOMAINCODE').html("以被使用，请重新输入");
				} else {
					var s = data.split(",");
					var html = "";
					for(var i=0;i<s.length;i++){
						if(s[i].length != 0){
							html += "<input type='checkbox' name='domainCode' value='"+s[i]+"'/>"+s[i] +"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
						}
					}
					$('#ckeckboxs').html(html);
				}
			}
		});
	} else {
		$('#DOMAINCODE').html("域名格式不正确");
	}
}

