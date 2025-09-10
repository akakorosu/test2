
$(function() {
	initDataGrid();
	initBind();
	
	$('#ftpForm').bootstrapValidator({
		fields : {
				ftpAddress : {validators : {
								notEmpty : {message : '请填写服务器地址'}
							}
				},
				ftpPort : {validators : {
								notEmpty : {message : '请填写服务器端口'}
							}
				},
				ftpUser : {validators : {
								notEmpty : {message : '请填写服务器用户'}
							}
				},
				ftpPassword : {validators : {
								notEmpty : {message : '请填写服务器密码'}
							}
				},
				ftpPath : {validators : {
								notEmpty : {message : '请填写服务器路径'}
							}
				}
		},
		submitHandler : function(validator, form, submitButton) {
//			$.ajax({
//				url : $.cxt + '/appstore/create',
//				data : $('#appForm').serializeObject(),
//				type : "POST",
//				success : function(json) {
//					$('div').remove('.alert');
//					var id = $.parseJSON(json);
//					$("#grid-table").jqGrid().trigger('reloadGrid');
//					$('#modal-ftp').modal('hide');
//					$('#gridContainer').prepend(successLable(""));
//				}
//			});
		}
	});
	
	$("#modal-ftp").on("hidden.bs.modal", function() {
		$("#ftpForm").data('bootstrapValidator').resetForm();
	});

	
	$('#databaseForm').bootstrapValidator({
		fields : {
			databaseAddress : {validators : {
								notEmpty : {message : '请填写数据库地址'}
							}
				},
				databasePort : {validators : {
								notEmpty : {message : '请填写数据库端口'}
							}
				},
				databaseUser : {validators : {
								notEmpty : {message : '请填写数据库用户'}
							}
				},
				databasePassword : {validators : {
								notEmpty : {message : '请填写数据库密码'}
							}
				},
				databaseTable : {validators : {
								notEmpty : {message : '请填写数据库表名'}
							}
				}
		},
		submitHandler : function(validator, form, submitButton) {
//			$.ajax({
//				url : $.cxt + '/appstore/create',
//				data : $('#appForm').serializeObject(),
//				type : "POST",
//				success : function(json) {
//					$('div').remove('.alert');
//					var id = $.parseJSON(json);
//					$("#grid-table").jqGrid().trigger('reloadGrid');
//					$('#modal-database').modal('hide');
//					$('#gridContainer').prepend(successLable(""));
//				}
//			});
		}
	});
	
	$("#modal-database").on("hidden.bs.modal", function() {
		$("#databaseForm").data('bootstrapValidator').resetForm();
	});

	
});

/**
 * 初始化DataGrid
 */
function initDataGrid(){
	for(var i = 1;i <= 8;i++){
		var url = 'localhost:8082/dpi/pages/jsp/frame/topMenuFrame.jsp';
		var li = $('<li></li>')
		 .append($('<a href="../../images/gallery/image-1.jpg" title="文件简况"></a>')
				 .append('<img width="150" height="150" alt="150x150" src="../../images/gallery/thumb-'+i+'.jpg" />'))
		 .append($('<div class="tags"></div>').append('<span class="label-holder"><span class="label label-info">域名信息模板</span></span>')
                 .append('<span class="label-holder"><span class="label label-danger">管理员</span></span>')
                 .append('<span class="label-holder"><span class="label label-success">2018.05.08</span></span>'))
         .append($('<div class="tools tools-bottom"></div>').append($('<a href="#" title="传输"></a>').append($('<i class="ace-icon fa fa-link"></i>').attr("onclick","doTransport('"+url+"')")))
        		 .append($('<a href="#" title="存库"></a>').append($('<i class="ace-icon fa fa-paperclip"></i>').attr("onclick","doDatabase('"+url+"')")))
        		 .append($('<a href="#" title="下载"></a>').append($('<i class="ace-icon fa fa-download"></i>').attr("onclick","doDownload('"+url+"')")))
        		 .append($('<a href="#" title="删除"></a>').append($('<i class="ace-icon fa fa-times red"></i>').attr("onclick","doDelete('"+url+"')"))));
		$('#downloadUl').append(li);
	}
}

function doTransport(url) {
	createFtpModel(url);
}

function doDatabase(url) {
	createDatabaseModel(url);
}

function doDownload(url) {
	var path = "D:\\software\\apache-tomcat-7.0.67\\wtpwebapps\\dpi-webapp\\WEB-INF\\upload\\规则库相关表_180426.xlsx";
	var name = "规则库相关表_180426.xlsx";
	var fromObj=$("#form1").attr("action",$.cxt+"/download/download");
	fromObj.empty();
	fromObj
	.append($("<input></input>").attr("type","hidden").attr("name","path").val(path))
	.append($("<input></input>").attr("type","hidden").attr("name","name").val(name))
	fromObj.submit();
}

function doDelete(url) {
	
}

function createFtpModel(url) {
	
	$('#modal-ftp .modal-title').empty();
	$('#modal-ftp .modal-title').append($('<i></i>').addClass("ace-icon fa fa-users").css("margin-right","2px")).append("服务器信息");
	$('#modal-ftp').modal({
		backdrop : 'static',
		keyboard : false,
		show : true
	});	
	document.getElementById("ftpForm").reset();
	$('#ftpForm').data('bootstrapValidator').validate();
	
	$("#ftpUrl").val(url);
	$("#ftpAddress").val('');
	$('#ftpPort').val('');
	$('#ftpUser').val('');
	$('#ftpPassword').val('');
	$('#ftpPath').val('');
	
}

function createDatabaseModel(url) {
	
	$('#modal-database .modal-title').empty();
	$('#modal-database .modal-title').append($('<i></i>').addClass("ace-icon fa fa-users").css("margin-right","2px")).append("数据库信息");
	$('#modal-database').modal({
		backdrop : 'static',
		keyboard : false,
		show : true
	});	
	document.getElementById("databaseForm").reset();
	$('#databaseForm').data('bootstrapValidator').validate();
	
	$("#databaseUrl").val(url);
	$("#databaseAddress").val('');
	$('#databasePort').val('');
	$('#databaseUser').val('');
	$('#databasePassword').val('');
	$('#databaseTable').val('');
	
}


function doSearch() {
	$("#grid-table").jqGrid('setGridParam', {
		url : $.cxt + "/appstore/listAllApp",
		datatype : 'json',
		postData : {"json" : JSON.stringify(setParams())}
	}).trigger('reloadGrid');
}

function deleteApp(appId){
	$('#confirmModal').modal({
		backdrop : 'static',
		keyboard : false,
		show : true
	});
	$('#confirmBtnModal').off();
	$('#confirmBtnModal').on("click", function() {
		$.ajax({
			url : $.cxt + '/appstore/doDelete',
			type : "POST",
			data : {appId:appId},
			success : function(json) {
				$('div').remove('.alert');
				$('#confirmModal').modal('hide');
				var data = $.parseJSON(json);
				$("#grid-table").jqGrid().trigger('reloadGrid');
				$('#gridContainer').prepend(successLable(data.msg));
			}
		});
	});
	$('#cancelBtnModal').on("click", function() {
		$('#confirmModal').modal('hide');
	});
}


function successLable(d) {
	return $("<div></div>").addClass("alert alert-success").css("width","98%").append(
			$("<button></button>").attr("data-dismiss", "alert").addClass(
					"close").attr("type", "button").append(
					$("<i></i>").addClass("ace-icon fa fa-times"))).append(
			$("<strong></strong>").append("操作成功！")).append(d).append($("<br>"));
}

function errorLable(d) {
	return $("<div></div>").addClass("alert alert-danger").append(
			$("<button></button>").attr("data-dismiss", "alert").addClass(
					"close").attr("type", "button").append(
					$("<i></i>").addClass("ace-icon fa fa-times"))).append(
			$("<strong></strong>").append("操作失败！")).append(d).append($("<br>"));
}

function messageAlert(message){
	bootbox.dialog({
        message: "<span style=\"color:#000\">"+message+"</span>",
        title: "消息提示",
        buttons: {
            OK: {
                label: "确定",
                className: "btn-success",
            }
        }
    });
}

function initBind(){
	
	$('#searchProcessList').bind('click', function() {
		doSearch();
	});
	
	$('#createApplyBtn').bind("click", function() {
		createModel("0",null);
	});
	
	$('#ftpResetButton').bind('click',function(){
		$("#ftpAddress").val('');
		$('#ftpPort').val('');
		$('#ftpUser').val('');
		$('#ftpPassword').val('');
		$('#ftpPath').val('');
	});
	
	$('#databaseResetButton').bind('click',function(){
		$("#databaseAddress").val('');
		$('#databasePort').val('');
		$('#databaseUser').val('');
		$('#databasePassword').val('');
		$('#databaseTable').val('');
	});
	
	$("#import").on("click",function(){
		var fileObj=$("<input id='fileField'></input>").attr("type","file").attr("name","file").on("change",function(){
			
			var data = new FormData();
			data.append('file', fileObj[0].files[0]);
			var fileName=fileObj.val().split("\\")[fileObj.val().split("\\").length-1];
			data.append('name', fileName);
			var filePath = fileName;
            var fileExt = filePath.substring(filePath.lastIndexOf("."))
               .toLowerCase();
            if (!checkFileExt(fileExt)) {
               messageAlert("您上传的文件类型不符,请重新上传！");
            }else{
            	$.ajax({
    	    		url : $.cxt + "/download/upload",
    	    		data:data,
    	    		type : "POST",
    	    		contentType: false, 
    	    		processData: false, 
    	    		success : function(json) {
                        if(json.code!="0"){
                             bootbox.dialog({
                                message: json.msg,
                                title: "消息提示",
                                buttons: {
                                   OK: {
                                      label: "确定",
                                      className: "btn-success",
                                   }
                                }
                             });
                        }
                        else{
//                            $("#grid-table").jqGrid().trigger('reloadGrid');
                        }
    	    		}
    	    	});
            }
		});
		fileObj.trigger("click");
    });
}

function checkFileExt(ext) {
    if (!ext.match(/.xls|.xlsx|.doc|.docx/i)) {
        return false;
    }
    return true;
}

