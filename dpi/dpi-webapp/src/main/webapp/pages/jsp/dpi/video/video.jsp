<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/pages/jsp/dpi/tabUlLi9.js"></script> --%>
<script type="text/javascript"   src="../layui/layui.all.js"></script>
<script type="text/javascript"   src="../layui/layui.js"></script>
<script type="text/javascript"   src="../layui/jquery-3.1.1.min.js"></script>
<link rel="stylesheet" href="../layui/css/layui.css" />

<style>
input{
	margin-left:10px;
    outline-style: none ;
    border: 1px solid #ccc;
    border-radius: 3px;
    padding: 13px 14px;
    height: 31px;
    width: 150px;
    font-size: 14px;
    font-weight: 700;
    font-family: "Microsoft soft";
}
select{
    height: 33px;
    outline-style: none ;
	border: 1px solid #ccc;
    border-radius: 3px;
	width: 73%;
}
input:focus{
    border-color: #66afe9;
    outline: 0;
    -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,233,.6);
    box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,233,.6)
}
</style>
<div class="main">
	<blockquote class="layui-elem-quote">
		<table style="width:100%; font-size: 14px;">
			<tr width='100%'>
				<td style="font-weight:bold;">网站名称</td>
				<td style="padding-left: 30px;">
						<select id="webName"  style="" > </select>
				</td>
				<td style="font-weight:bold; text-align: right;">视频名称</td>
				<td >
					<input id="videoName" style="text-align: center; color:#000000">
				</td>
				<td style="font-weight:bold; text-align: right;">视频分类</td>
				<td >
                    <select id="videoCategory"  style="" > </select>
<%--					<input id="videoCategory"  style="text-align: center; color:#000000">--%>
				</td>
				<td style="font-weight:bold; text-align: right;">标签</td>
				<td >
                    <select id="videoLabel"  style="" > </select>
				</td>
				<td style="font-weight: bold; text-align: right;">演员</td>
				<td>
					<input id="actor"  style="text-align: center; color:#000000">
				</td>
				<td style="padding-right:2px; text-align: right;">
					<a href="javascript:;" class="layui-btn layui-btn-small" id="query" >
						<i class="layui-icon">&#xe615;</i> 查询
					</a>
				</td>
				<td style="padding-right:2px; text-align: right; width: 7%;">
					<a href="javascript:;" class="layui-btn layui-btn-small" id="lead" >
						<i class="layui-icon">&#xe63c;</i> 导出
					</a>
				</td>
				<td style=" text-align: right;">
					<a class="layui-icon layui-icon-down" id="more" style="font-size: 13px; color: #006400;">高级</a>
				</td>
			</tr>
			<tr id="search-more" class="layui-hide" style="height: 38px;">
				<td style="font-weight:bold;  ">区域</td>
				<td>
					<input id="area"  style="text-align: center; color:#000000">
				</td>
				<td style="font-weight: bold;  text-align: right;">导演</td>
				<td>
					<input id="director"  style="text-align: center; color:#000000">
				</td>
			</tr>
			<tr id="search-more2" class="layui-hide" style="height: 38px;display:none">
				<td style="font-weight:bold; " >语言</td>
				<td>
					<input id="language"  style="text-align: center; color:#000000;">
				</td>
			</tr>
		</table>
	</blockquote>
	 <table class="layui-hide" id="tablelist" lay-filter="tablelist"></table>
	 <div id="page" style="text-align: right; padding-right: 10px;margin-bottom:50px"></div>
	<script type="text/javascript">

		var net_id = $('#webName option:selected').val();
		var net_name = $("#webName").find("option:selected").text();
		if(net_id == ""){
			net_name = "";
		}
		var video_name = $('#videoName').val();
		var video_type= $('#videoCategory option:selected').val();
		var director = $('#director').val();
		var actor= $('#actor').val();
		var spot=$('#area').val();
		var language=$('#language').val();
		var lablename="";
		$("#li0").html($("#li0").html() + net_name);
		$("#li1").html($("#li1").html() + video_name);
		$("#li2").html($("#li2").html() + video_type);
		$("#li3").html($("#li3").html() + spot);
		$("#li4").html($("#li4").html() + actor);
		$("#li5").html($("#li5").html() + director);
		$("#li6").html($("#li6").html() + language);
		var checklableNameUnique=function (val){
			console.log(this);
			$.ajax({
				type: "POST",
				url :  "${pageContext.request.contextPath}/video/getExportLogNum",
				data :  {"lablename":val},
				async:false,
				dataType : "json",
				success : function(data) {
					if(data.count==0){
						unique=true
					}else {
						unique=false;
					}
				}
			});
		};
	</script>
	<div id="content" style="display: none">
		<ul class="layui-ul">
			<li>标签名：<input onblur="checklableNameUnique(this.value,this);lablename=this.value"></li>
		</ul>
	</div>
</div>

<script>
	var pageNum = 1 ; // 当前第几页
	var _totalNum = 0; // 查询结果总数
	var pageLimit = 20; // 每页显示数
	var lablename="";
	var unique=false;
	layui.use(['table','laypage','form'], function(){
		var $ = layui.jquery;
		var table = layui.table;
		var laypage = layui.laypage;
		var form = layui.form;

		setTimeout(() => {
			$("#query").click()
		}, 200);
		//工号
		// var user_id = $('#jfUserId').val();
	 	/* var user_id = "lul"//测试 */
	//console.log("------"+$.cxt )
		//dpi/sysorg/getCityOption
		//初始化下拉列表
		 function initParameters(){
			 $.ajax({
					type: "POST",
					url :   "${pageContext.request.contextPath}/video/initparam",
					async: false,
					dataType : "json",
					success : function(data) {
						//console.log(data);
						//console.log(data.weblist);
						var webhtml = "";
						webhtml += '<option value="">---请选择---</option>'
						for(var i = 0;i < data.weblist.length;i++){
							webhtml += '<option value="'+data.weblist[i].net_id+'">'+data.weblist[i].net_name+'</option>';
						}
						$("#webName").empty().html(webhtml);
                        var categoryhtml = "";
                        categoryhtml += '<option value="">---请选择---</option>'
                        for(var i = 0;i < data.categorylist.length;i++){
                            categoryhtml += '<option value="'+data.categorylist[i].video_type_name+'">'+data.categorylist[i].video_type_name+'</option>';
                        }
                        $("#videoCategory").empty().html(categoryhtml);
						var labelhtml = "";
						labelhtml += '<option value="">---请选择---</option>'
                        $("#videoLabel").empty().html(labelhtml);
					}
				});
		 }

		 initParameters();

		 $("#more").on('click', function(){
			if($("#search-more").hasClass("layui-hide")){
				$("#more").removeClass("layui-icon layui-icon-down").addClass("layui-icon layui-icon-up");
				$("#search-more").removeClass("layui-hide");
				$("#search-more2").removeClass("layui-hide");
			}else {
				$("#more").removeClass("layui-icon layui-icon-up").addClass("layui-icon layui-icon-down");
				$("#search-more").addClass("layui-hide");
				$("#search-more2").addClass("layui-hide");
			}
		})

		$("#query").on('click', function(){
			 var net_id= $('#webName option:selected').val();
			 var video_name = $('#videoName').val();
			 var video_type = $('#videoCategory option:selected').val();
			 var video_label = $('#videoLabel option:selected').val();
			 var director = $('#director').val();
			 var actor= $('#actor').val();
			 var spot=$('#area').val();
			 var language=$('#language').val();
			 getTotalNum(net_id, video_name, video_type, director, actor, spot, language, video_label);
			 showTableData(pageNum, net_id, video_name, video_type, director, actor, spot, language, video_label);
			 paging(_totalNum);
		})

		$("#videoCategory").on('click', function(){
			var videoCategory= $('#videoCategory option:selected').val();
			if(videoCategory!=null && videoCategory!=""){
				$.ajax({
					type: "POST",
					url :  '${pageContext.request.contextPath}/video/labellist',
					data :  {"videotypename": videoCategory},
					async: false,
					dataType : "json",
					success : function(data) {
						console.log(data);
						var labelhtml = "";
						labelhtml += '<option value="">---请选择---</option>'
						for(var i = 0;i < data.labellist.length;i++){
							labelhtml += '<option value="'+data.labellist[i].label_type+'">'+data.labellist[i].label_name+'</option>';
						}
						$("#videoLabel").empty().html(labelhtml);
					}
				});
			}else{
				var labelhtml = "";
				labelhtml += '<option value="">---请选择---</option>'
				$("#videoLabel").empty().html(labelhtml);
			}
		})

		var getTotalNum = function(net_id, video_name, video_type, director, actor, spot, language, video_label){
			 $.ajax({
				type: "POST",
				url :  '${pageContext.request.contextPath}/video/getVideoTotalnum',
				data :  {"net_id": net_id, "video_name": video_name, "video_type": video_type, "director": director, "actor": actor, "spot": spot, "language": language, "video_label": video_label},
 				async: false,
				dataType : "json",
				success : function(data) {
					console.log(data);
					_totalNum = data.totalnum;
				}
			});
		}

		var showTableData = function(pageNum, net_id, video_name, video_type, director, actor, spot, language, video_label){

			table.render({
					id: 'datatable'
				    ,elem: '#tablelist'
				    ,url: "${pageContext.request.contextPath}/video/getVideoList"
				    ,where: {"pagenum": pageNum, "net_id": net_id, "video_name": video_name, "video_type": video_type, "director": director, "actor": actor, "spot": spot, "language": language, "video_label": video_label}//查询
				    ,method: 'post'
				    	,limit : 20
				  	,request :{
				  		pageName: 'pagenum' //页码的参数名称，默认：page
				  	}
				    ,response: {
				        statusName: 'resultCode' //规定数据状态的字段名称，默认：code
				        ,statusCode: 1 //规定成功的状态码，默认：0
				        ,msgName: 'resultMsg' //规定状态信息的字段名称，默认：msg
				        ,countName: 'totalnum' //规定数据总数的字段名称，默认：count
				        ,dataName: 'videolist' //规定数据列表的字段名称，默认：data
				      }
				    ,page: false
				    ,defaultToolbar: ['filter']
				    ,cols: [[
 				        /* {type:'checkbox'} */
				      {field:'net_name', title:'网站名称' }
				      ,{field:'video_name', title:'视频名称'}
				      ,{field:'video_type', title:'视频分类'}
				      ,{field:'video_tag', title:'视频标签'}
				      ,{field:'spot', title:'区域'}
				      ,{field:'release_date', title:'时间'}
				      ,{field:'director', title:'导演'}
				      ,{field:'actor', title:'演员'}
				      ,{field:'video_rating', title:'评分',sort:'true'}
				      ,{field:'view_counts', title:'播放量（热度）',sort:'true'}
				      //,{field:'video_id', title: '视频ID'}
				      //,{field:'language', title:'语言'}
				    ]]
				    ,even: true
				  });
/* 			var checkStatus = table.checkStatus('video_id');
			console.log(checkStatus.data) //获取选中行的数据
			console.log(checkStatus.data.length) //获取选中行数量，可作为是否有选中行的条件
			console.log(checkStatus.isAll ) //表格是否全选 */

		  }

		  //分页
		  var paging = function(count){
			     var net_id= $('#webName option:selected').val();
				 var video_name = $('#videoName').val();
				 var video_type= $('#videoCategory option:selected').val();
				 var video_label= $('#videoLabel option:selected').val();
				 var director = $('#director').val();
				 var actor= $('#actor').val();
				 var spot=$('#area').val();
				 var language=$('#language').val();
			//layui分页
			     laypage.render({
			        elem:'page',
			        count:count,
			        limit: pageLimit,
			        curr:1,
			        layout:['prev','page','next','count'],
			        jump:function (obj,first) {
			                var presentpage = obj.curr;
			               if(!first){
			            	   showTableData(presentpage, net_id, video_name, video_type, director, actor, spot, language, video_label);
			               }
			         }
			    })
		  };

					  // var code  =  data.resultCode;
					  // var titleMsg = "";
					  // if(code == 1){
						//   titleMsg = "导入结果";
					  // }else {
						//   titleMsg = '温馨提示';
					  // }
					  // layer.open({
						//   offset: '100px',
						//   title: titleMsg
						//   ,content: data.resultMsg
					  // });

		  //导出
		  $("#lead").on('click', function(){
			  console.log(layui.table.cache["datatable"])
              var export_data = JSON.stringify(layui.table.cache["datatable"])
			  var net_id = $('#webName option:selected').val();
			  var net_name = $("#webName").find("option:selected").text();
			  if(net_id==""||net_id==null){
				  net_name="";
			  }
			  var video_name = $('#videoName').val();
			  var video_type= $('#videoCategory option:selected').val();
			  if(video_type==""||video_type==null){
				  video_type="";
			  }
			  var video_label= $('#videoLabel option:selected').val();
			  var video_label_name= $('#videoLabel option:selected').text();
			  if(video_label==""||video_label==null){
				  video_label_name="";
			  }
			  var director = $('#director').val();
    		  var actor= $('#actor').val();
			  var spot=$('#area').val();
			  var language=$('#language').val();

			  layer.open({
				  type: 0,
				  title: '请确认筛选条件',
				  skin: 'layui-layer-molv',
				  closeBtn: 1,
				  anim: 2,
				  offset: '100px',
				  time: 0,//不自动关闭
				  area: ['400px', '300px'],
				  content:'<li>网站名称：'+net_name+'</li><li>视频名称：'+video_name+'</li><li>视频分类：'+video_type+'</li><li>标签：'+video_label_name+'</li><li>区域：'+spot+'</li><li>演员：'+actor+'</li><li>导演：'+director+'</li><li>语言：'+language+'</li>',
				  btn: ['确定', '取消'],

				  yes: function(index){
					  layer.close(index);
					  $.ajax({
							type: "POST",
							url :  '${pageContext.request.contextPath}/video/exportJob',
							data :  {"lablename":video_label_name,"net_id": net_id, "video_name": video_name, "video_type": video_type, "video_label": video_label, "director": director, "actor": actor, "spot": spot, "language": language,"export_data":export_data, "video_label": video_label},
			 				/* async: false,  */
							dataType : "json",
							success : function(data) {
								var code  =  data.resultCode;
								var titleMsg = "";
								if(code == 1){
									titleMsg = "导入结果";
								}else {
									titleMsg = '温馨提示';
								}
								layer.open({
									offset: '100px',
									  title: titleMsg
									  ,content: data.resultMsg
									});
							}
						});
				  }
				})
		  })

	});
</script>
