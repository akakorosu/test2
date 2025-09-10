
$(function() {
	initDataGrid();
	initBind();

	$('#appForm').bootstrapValidator({
//		excluded : [':disabled'],
		fields : {
			    domainLevel : {validators : {
								notEmpty : {message : '请填写域名级别'},
								regexp : { regexp : /^.{1,15}$/,message : '域名级别不能超过15个字'}
							}
				},
				isDomainSon : {validators : {
								notEmpty : {message : '请选择是否有子域名'}
							}
				},
				isNoise : {validators : {
								notEmpty : {message : '请选择是否是噪音'}
							}
				},
				prodId : {validators : {
								notEmpty : {message : '请填写产品编码'}
							}
				},
				fatherDomain : {validators : {
								notEmpty : {message : '请填写父域名'}
							}
				},
				rootDomain : {validators : {
								notEmpty : {message : '请填写根域名'}
							}
				},
				isValid : {validators : {
								notEmpty : {message : '请选择是否有效'}
							}
				},
				isRedis : {validators : {
								notEmpty : {message : '请选择是否导入Redis'}
							}
				}
		},
		submitHandler : function(validator, form, submitButton) {
			
			if(!$('#appForm select[name=isDomainSon]').val()){
				messageAlert("请选择是否有子域名！");
				$("#submitBtn").removeAttr("disabled");
				return;
			}
			
			if(!$('#appForm select[name=isNoise]').val()){
				messageAlert("请选择是否是噪音！");
				$("#submitBtn").removeAttr("disabled");
				return;
			}
			
			if(!$('#appForm select[name=isValid]').val()){
				messageAlert("请选择是否有效！");
				$("#submitBtn").removeAttr("disabled");
				return;
			}
			
			if(!$('#appForm select[name=isRedis]').val()){
				messageAlert("请选择是否导入Redis！");
				$("#submitBtn").removeAttr("disabled");
				return;
			}
			
			
//			$.ajax({
//				url : $.cxt + '/appstore/create',
//				data : $('#appForm').serializeObject(),
//				type : "POST",
//				success : function(json) {
//					$('div').remove('.alert');
//					var id = $.parseJSON(json);
//					$("#grid-table").jqGrid().trigger('reloadGrid');
//					$('#modal-app').modal('hide');
//					$('#gridContainer').prepend(successLable(""));
//					refreshDropZone();
//				}
//			});
		}
	});
	$("#modal-app").on("hidden.bs.modal", function() {
		$("#appForm").data('bootstrapValidator').resetForm();
	});
	
});

/**
 * 初始化DataGrid
 */
function initDataGrid(){
	var grid_data = 
        [ 
            {domainCode:"1",domainLevel:"1",isDomainSon:'1',isNoise:"1",prodId:"BH5896",
            	fatherDomain:"父域名",rootDomain:"根域名",author:"管理员",opTime:"2018.05.09",
            	isValid:"0",isRedis:"1",totalCount:"5"},
        	{domainCode:"2",domainLevel:"1",isDomainSon:'1',isNoise:"1",prodId:"BH5896",
            	fatherDomain:"父域名",rootDomain:"根域名",author:"管理员",opTime:"2018.05.09",
            	isValid:"0",isRedis:"1",totalCount:"5"},
        	{domainCode:"3",domainLevel:"1",isDomainSon:'1',isNoise:"1",prodId:"BH5896",
            	fatherDomain:"父域名",rootDomain:"根域名",author:"管理员",opTime:"2018.05.09",
            	isValid:"0",isRedis:"1",totalCount:"5"},
        	{domainCode:"4",domainLevel:"1",isDomainSon:'1',isNoise:"1",prodId:"BH5896",
            	fatherDomain:"父域名",rootDomain:"根域名",author:"管理员",opTime:"2018.05.09",
            	isValid:"0",isRedis:"1",totalCount:"5"},
        	{domainCode:"5",domainLevel:"1",isDomainSon:'1',isNoise:"1",prodId:"BH5896",
            	fatherDomain:"父域名",rootDomain:"根域名",author:"管理员",opTime:"2018.05.09",
            	isValid:"0",isRedis:"1",totalCount:"5"},
        	{domainCode:"6",domainLevel:"1",isDomainSon:'1',isNoise:"1",prodId:"BH5896",
            	fatherDomain:"父域名",rootDomain:"根域名",author:"管理员",opTime:"2018.05.09",
            	isValid:"0",isRedis:"1",totalCount:"5"},
        	{domainCode:"7",domainLevel:"1",isDomainSon:'1',isNoise:"1",prodId:"BH5896",
            	fatherDomain:"父域名",rootDomain:"根域名",author:"管理员",opTime:"2018.05.09",
            	isValid:"0",isRedis:"1",totalCount:"5"},
        	{domainCode:"8",domainLevel:"1",isDomainSon:'1',isNoise:"1",prodId:"BH5896",
            	fatherDomain:"父域名",rootDomain:"根域名",author:"管理员",opTime:"2018.05.09",
            	isValid:"0",isRedis:"1",totalCount:"5"},
        	{domainCode:"9",domainLevel:"1",isDomainSon:'1',isNoise:"1",prodId:"BH5896",
            	fatherDomain:"父域名",rootDomain:"根域名",author:"管理员",opTime:"2018.05.09",
            	isValid:"0",isRedis:"1",totalCount:"5"},
        	{domainCode:"10",domainLevel:"1",isDomainSon:'1',isNoise:"1",prodId:"BH5896",
            	fatherDomain:"父域名",rootDomain:"根域名",author:"管理员",opTime:"2018.05.09",
            	isValid:"0",isRedis:"1",totalCount:"5"},
        	{domainCode:"11",domainLevel:"1",isDomainSon:'1',isNoise:"1",prodId:"BH5896",
            	fatherDomain:"父域名",rootDomain:"根域名",author:"管理员",opTime:"2018.05.09",
            	isValid:"0",isRedis:"1",totalCount:"5"},
        	{domainCode:"12",domainLevel:"1",isDomainSon:'1',isNoise:"1",prodId:"BH5896",
            	fatherDomain:"父域名",rootDomain:"根域名",author:"管理员",opTime:"2018.05.09",
            	isValid:"0",isRedis:"1",totalCount:"5"},
        	{domainCode:"13",domainLevel:"1",isDomainSon:'1',isNoise:"1",prodId:"BH5896",
            	fatherDomain:"父域名",rootDomain:"根域名",author:"管理员",opTime:"2018.05.09",
            	isValid:"0",isRedis:"1",totalCount:"5"},
        	{domainCode:"14",domainLevel:"1",isDomainSon:'1',isNoise:"1",prodId:"BH5896",
            	fatherDomain:"父域名",rootDomain:"根域名",author:"管理员",opTime:"2018.05.09",
            	isValid:"0",isRedis:"1",totalCount:"5"},
        	{domainCode:"15",domainLevel:"1",isDomainSon:'1',isNoise:"1",prodId:"BH5896",
            	fatherDomain:"父域名",rootDomain:"根域名",author:"管理员",opTime:"2018.05.09",
            	isValid:"0",isRedis:"1",totalCount:"5"},
        	{domainCode:"16",domainLevel:"1",isDomainSon:'1',isNoise:"1",prodId:"BH5896",
            	fatherDomain:"父域名",rootDomain:"根域名",author:"管理员",opTime:"2018.05.09",
            	isValid:"0",isRedis:"1",totalCount:"5"}
        ];
	
	$('#grid-table').jqGrid(
			{
//				url : $.cxt + "/appstore/listAllApp",
				datatype : "local",
				data : grid_data,
//				datatype : "json",
//				mtype : "POST",
				height : 360,
				width:$("#gridContainer").width(),
				rownumbers:true,
				cellEdit:false,
				colNames : [ '域名编码','域名级别','是否有子域名','是否是噪音','产品ID','父域名','根域名','操作人','操作时间','是否有效','是否导入Redis','累计访问次数','操作' ],
				colModel : [ 
				             {name : 'domainCode',align : 'center',index : 'domainCode',editable : true,hidden : true},
				             {name : 'domainLevel',align : 'center',index : 'domainLevel',editable : true},
				             {name : 'isDomainSon',align : 'center',index : 'isDomainSon',editable : true,formatter : formatFreeFlag},
				             {name : 'isNoise',align : 'center',index : 'isNoise',editable : true},
				             {name : 'prodId',align : 'center',index : 'prodId',editable : true},
				             {name : 'fatherDomain',align : 'center',index : 'fatherDomain',editable : true},
				             {name : 'rootDomain',align : 'center',index : 'rootDomain',editable : true},
				             {name : 'author',align : 'center',index : 'author',editable : true},
				             {name : 'opTime',align : 'center',index : 'opTime',editable : true},
				             {name : 'isValid',align : 'center',index : 'isValid',editable : true,formatter : formatFreeFlag},
				             {name : 'isRedis',align : 'center',index : 'isRedis',editable : true,formatter : formatFreeFlag},
				             {name : 'totalCount',align : 'center',index : 'totalCount',editable : true},
				             {name : 'act',align : 'center',index : 'act',width : '300%',search : false,sortable : false,editable : false,formatter : renderOperation} 
				            ],
				viewrecords : true,
				rowNum : 10,
				rowList : [ 10, 20, 30 ],
				pager : '#grid-pager',
				altRows : true,
				loadComplete : function() {
					var table = this;
					setTimeout(function() {
						styleCheckbox(table);
						updatePagerIcons(table);
					}, 0);
				},
				caption : "域名信息"
			});
	//添加滚动条	
	$("#gview_grid-table > .ui-jqgrid-bdiv").slimScroll({
		height : "360px",
		alwaysVisible : false
	});
	$(".ui-jqgrid-sortable").css("text-align", "center");
	//JqGrid重新布局
	$(window).triggerHandler('resize.jqGrid');
	
	//JqGrid分页栏设置
	$('#grid-table').jqGrid('navGrid', '#grid-pager', {
		edit : false,
		editicon : 'ace-icon fa fa-pencil blue',
		add : false,
		addicon : 'ace-icon fa fa-plus-circle purple',
		del : false,
		delicon : 'ace-icon fa fa-trash-o red',
		search : false,
		searchicon : 'ace-icon fa fa-search orange',
		refresh : true,
		refreshicon : 'ace-icon fa fa-refresh green',
		view : false,
		viewicon : 'ace-icon fa fa-search-plus grey'
	})
}

function formatFreeFlag(cellvalue) {
	return cellvalue == '0' ? '否' : '是';
}

function renderOperation(cellvalue, options, cell) {
	var div = $("<div><div>").append(
		$("<button></button>")
		.append($("<i></i>").addClass("ace-icon bigger-130 fa fa-search-plus blue"))
		.attr("onclick","selectApp('" + cell.domainCode + "')")
		.addClass("btn btn-xs grid-btn grids-btn")
		.attr("title","查看")	
	);
	div.append(
		$("<button></button>")
		.append($("<i></i>").addClass("ace-icon bigger-130 fa fa-trash-o red"))
		.attr("onclick","deleteApp('" + cell.domainCode + "')")
		.addClass("btn btn-xs grid-btn grids-btn")
		.attr("title","删除")	
	).append(
		$("<button></button>")
		.append($("<i></i>").addClass("ace-icon bigger-130 fa fa-pencil-square-o green"))
		.attr("onclick","editApp(" + JSON.stringify(cell) + ")")
		.addClass("btn btn-xs grid-btn grids-btn")
		.attr("title","编辑")	
	);
	$('[data-rel=tooltip]').tooltip();
	return div.html();
}

function styleCheckbox(table) {
	$('.ui-jqgrid-sortable').css("text-align", "center");
}

function updatePagerIcons(table) {
	var replacement = {
		'ui-icon-seek-first' : 'ace-icon fa fa-angle-double-left bigger-140',
		'ui-icon-seek-prev' : 'ace-icon fa fa-angle-left bigger-140',
		'ui-icon-seek-next' : 'ace-icon fa fa-angle-right bigger-140',
		'ui-icon-seek-end' : 'ace-icon fa fa-angle-double-right bigger-140'
	};
	$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon')
			.each(function() {
				var icon = $(this);
				var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
				if ($class in replacement)
					icon.attr('class', 'ui-icon ' + replacement[$class]);
			})
}

function doSearch() {
	$("#grid-table").jqGrid('setGridParam', {
		url : $.cxt + "/appstore/listAllApp",
		datatype : 'json',
		postData : {"json" : JSON.stringify(setParams())}
	}).trigger('reloadGrid');
}

function editApp(cell){
	$('#modal-app .modal-title').empty();
	$('#modal-app .modal-title').append($('<i></i>').addClass("ace-icon fa fa-users").css("margin-right","2px")).append("编辑域名信息");
	$('#modal-app').modal({
		backdrop : 'static',
		keyboard : false,
		show : true
	});	
	document.getElementById("appForm").reset();
	
	$("#domainLevel").val(cell.domainLevel);
	$('#prodId').val(cell.prodId);
	$('#fatherDomain').val(cell.fatherDomain);
	$('#rootDomain').val(cell.rootDomain);
	$("#isDomainSon").val(cell.isDomainSon);
	$("#isNoise").val(cell.isNoise);
	$("#isDomainSon").val(cell.isDomainSon);
	$("#isNoise").val(cell.isNoise);
	
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

function createModel(applyId) {
	
	$('#modal-app .modal-title').empty();
	$('#modal-app .modal-title').append($('<i></i>').addClass("ace-icon fa fa-users").css("margin-right","2px")).append("新建域名信息");
	$('#modal-app').modal({
		backdrop : 'static',
		keyboard : false,
		show : true
	});	
	$("#domainLevel").val('');
	$('#prodId').val('');
	$('#fatherDomain').val('');
	$('#rootDomain').val('');
	
	$("#isDomainSon").val("");
	$("#isDomainSon").trigger('chosen:updated');
	$("#isNoise").val('');
	$("#isNoise").trigger('chosen:updated');
	$("#isValid").val('');
	$("#isValid").trigger('chosen:updated');
	$("#isRedis").val('');
	$("#isRedis").trigger('chosen:updated');
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

$(document).ready(function(){
	$("#modal-app").find(".modal-body").slimScroll({
		height : "450px",
		alwaysVisible : false
	});
});


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
	
	$(".visualSelect").visualSelect({width: "100%"});
	
	$(".chosen-select").chosen({width: "100%",disable_search_threshold:7});
	
	$('#searchProcessList').bind('click', function() {
		doSearch();
	});
	
	$('#createApplyBtn').bind("click", function() {
		createModel("0",null);
	});
	
	$('#resetButton').bind('click',function(){
		
		$("#domainLevel").val('');
		$('#prodId').val('');
		$('#fatherDomain').val('');
		$('#rootDomain').val('');
		
		$("#isDomainSon").val('');
		$("#isDomainSon").trigger('chosen:updated');
		$("#isNoise").val('');
		$("#isNoise").trigger('chosen:updated');
		$("#isValid").val('');
		$("#isValid").trigger('chosen:updated');
		$("#isRedis").val('');
		$("#isRedis").trigger('chosen:updated');
	});
	
	$("#import").on("click",function(){
		var fileObj=$("<input id='fileField'></input>").attr("type","file").attr("name","file").on("change",function(){
			
//			var form = $('<form id="importForm" style="display:none;" action="'+$.cxt+'/domain/import" enctype="multipart/form-data" method="post"></form>')
//			.append(fileObj);
//			$(document.body).append(form);
//			var filePath = document.getElementById("fileField").value;
//            var fileExt = filePath.substring(filePath.lastIndexOf("."))
//                .toLowerCase();
//            if (!checkFileExt(fileExt)) {
//                messageAlert("您上传的文件类型不符,请重新上传！");
//                $("importForm").remove();
//            }else{
//            	form.submit();
//    			$("importForm").remove();
//            }
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
    	    		url : $.cxt + "/domain/import",
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
                            $("#grid-table").jqGrid().trigger('reloadGrid');
                        }
    	    		}
    	    	});
            }
		});
		fileObj.trigger("click");
    });
}

function checkFileExt(ext) {
    if (!ext.match(/.xls|.xlsx/i)) {
        return false;
    }
    return true;
}

