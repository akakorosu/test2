//弹出分组编码的列表
function getGroupTypeList(groupType,groupName,type){
	var groupType = $("#groupType").val();
	var groupName = $("#groupNameNew").val();
	if(type=="groupType"){
		groupName="";
	}
	if(type=="groupName"){
		groupType="";
	}
	topwindow.showWindow({
		//窗口名称
		title : "分组编码列表",
		//参数
		data : {
			groupType : groupType,
			groupName : groupName
		},
		//url
		url : $.cxt + "/keywordGroup/getGroupTypeList",
		bottons : [{
			title : "返回",
			fun : function() {
				//重新加载表格
				reloadJqGrid("search");
				//关闭窗口
				topwindow.removeWindow();
			}
		}]
	});
}