var topwindow = {
	//整个弹出页面默认 z-index 100，慢慢往上加
	i : 100,
	removeWindow : function() {
		//说明已经没有弹出框，直接退出
		if(topwindow.i == 100) {
			return;
		}
		var iTemp = topwindow.i;
		var idTemp = topwindow.id;
		topwindow.i --;
		topwindow.id = "topwindow" + topwindow.i + "context";
		/*$("#topwindow" + iTemp + "context").remove();
		$("#topwindow" + iTemp).remove();*/
		/**/
		$("#topwindow" + iTemp + "context").removeClass('transitions').addClass('transitioned1');
		$("#topwindow" + iTemp).fadeOut(300,function(){
			$("#topwindow" + iTemp + "context").remove();
			$("#topwindow" + iTemp).remove();
		});
		/**/
		return iTemp;
	},
	/**
	 * 弹出框右上角按钮关闭方法
	 */
	closeBtn : function() {
		var iTemp = topwindow.removeWindow();
		if(topobj["window_closeBtnFun" + iTemp]) {
			topobj["window_closeBtnFun" + iTemp]();
		}
	},
	/**
	 * 弹出框关闭方法
	 * @param obj 关闭窗口的时候传过来的参数
	 */
	close : function(obj) {
		var iTemp = topwindow.removeWindow();
		if(topobj["window_closeFun" + iTemp] && obj) {
			topobj["window_closeFun" + iTemp](obj);
		}
	},
	/**
	 * 弹出弹出框
	 * @param obj.url 弹出窗后台url
	 * @param obj.data url所需要的参数
	 * @param obj.width 弹出框的宽度
	 * @param obj.height 弹出框的高度
	 * @param obj.title 弹出框的标题
	 * @param obj.closeFun 关闭弹出框的回调方法
	 * @param obj.closeBtnFun 点击弹出框右上角关闭按钮回调方法
	 * @param obj.fun 弹出框加载完成回调方法
	 * @param obj.check 弹出前是否校验当前页面的数据
	 */
	showWindow : function(obj) {
		if(obj.url == undefined) {
			return;
		}
		$.ajax({
			url : obj.url, 
			data : obj.data,
			type: "POST",
			success : function(data) {
				var windowHeight = $('#contentLoader', window.parent.document).height();
				var windowWidth = $(window).width();
				//alert("height:" + windowHeight + "///width:" + windowWidth);
				topwindow.i ++;
				topwindow.id = "topwindow" + topwindow.i + "context";
				var id = "topwindow" + topwindow.i;
				//默认宽度给1000
				if(obj.width == undefined) {
					obj.width = 900;
				}
				//如果给的宽度大于窗口的，则宽度为窗口宽度
				if(obj.width > windowWidth) {
					obj.width = windowWidth;
				}
				//默认高度给500
				if(obj.height == undefined) {
					obj.height = 500;
				}
				//如果给的高度大于窗口的，则高度为窗口高度
				if(obj.height > windowHeight) {
					obj.height = windowHeight - 100;
				}
				/*var titlee = "";
				if(obj.title == undefined) {
					titlee = "<img src=\"themes/themeDefault/img/x_logo.png\"></img>";
				} else {
					titlee = "<h4 class=\"modal-title\">"+obj.title+"</h4>"
				}*/
				var bodyHeight = obj.height - 93.85;
				var html = 
				"<div id=\""+id+"\" style=\"z-index: "+topwindow.i+"\" class=\"fullBackground\"></div>" +
				//"<div id=\""+id+"context\" class=\"modal-content\" style=\"margin-top:0px;position:absolute;width: "+obj.width+"px;height:"+obj.height+"px;z-index: "+topwindow.i+";\">" +
				/**/
				"<div id=\""+id+"context\" class=\"modal-content fade transitioned\" style=\"margin-top:0px;position:absolute;width: "+obj.width+"px;height:"+obj.height+"px;z-index: "+topwindow.i+";\">" +
				/**/
					"<div class=\"modal-header\">" + 
						"<button type=\"button\" class=\"close\">" + 
							"<i class=\"confirm-close fa fa-times\"></i>" + 
						"</button>" + 
						"<h4 class=\"modal-title\">" + 
							obj.title +
						"</h4>" +
					"</div>" +
					"<div class=\"modal-body\" style=\"height: "+bodyHeight+"px;overflow:auto;\">" +
					"</div>" +
					"<div class=\"modal-bottom\">" +
					"</div>" + 
				"</div>"
				$("body").append(html);
				/**/
				$("#"+id).fadeIn(300,function(){
					$("#"+id+"context").addClass('transitions')
					$("#"+id+"context").addClass('in');
				});
				/**/
				if(obj.bottons != undefined) {
					for(var i = 0; i < obj.bottons.length; i++) {
						var botton = obj.bottons[i];
						var bottonCss = "btn-primary";
						if(botton.css != undefined) {
							bottonCss = botton.css;
						}
						var btnHtml = "<button id=\"" + id + "contextBtn" + i + "\" class=\"btn "
						+ bottonCss + "\"" + (i!=(obj.bottons.length-1)?" style=\"margin-right:10px;\"":"") + ">"
						+ botton.title + "</button>";
						$("#" + id + "context .modal-bottom").append(btnHtml);
						$("#" + id + "contextBtn" + i).click(botton.fun);
					}	
				} 
				/*else {
					$("#" + id + "context .modal-bottom").remove();
					$("#" + id + "context .modal-body").css({"border-radius":"0px 0px 8px 8px"});
				}*/
				topsetWindowCenter($("#" + id + "context"));
				$("#" + id + "context .close").click(function(){
					//关闭按钮调用方法
					topwindow.closeBtn();
				});
				//手动关闭回调方法
				topobj["window_closeFun" + topwindow.i] = obj.closeFun;
				//关闭按钮关闭回调方法
				topobj["window_closeBtnFun" + topwindow.i] = obj.closeBtnFun;
				//添加弹出窗内容
				$("#" + id + "context .modal-body").html(data);
				//如果有fun调用自定义加载完成方法
				if(obj.fun) {
					obj.fun();
				}
			}
		});
	}
}

var topmyDragging=function(validateHandler){ //参数为验证点击区域是否为可移动区域，如果是返回欲移动元素，负责返回null
    var draggingObj=null; //dragging Dialog
    var diffX=0;
    var diffY=0;
    
    function mouseHandler(e){
        switch(e.type){
            case 'mousedown':
                draggingObj=validateHandler(e);//验证是否为可点击移动区域
                if(draggingObj!=null){
                    diffX=e.clientX-draggingObj.offsetLeft;
                    diffY=e.clientY-draggingObj.offsetTop;
                }
                break;
            
            case 'mousemove':
                if(draggingObj){
                    draggingObj.style.left=(e.clientX-diffX)+'px';
                    draggingObj.style.top=(e.clientY-diffY)+'px';
                }
                break;
            
            case 'mouseup':
                draggingObj =null;
                diffX=0;
                diffY=0;
                break;
        }
    };
    
    return {
        enable:function(){
        	$(document).mousedown(mouseHandler);
        	$(document).mousemove(mouseHandler);
        	$(document).mouseup(mouseHandler);
            /*document.addEventListener('mousedown',mouseHandler);
            document.addEventListener('mousemove',mouseHandler);
            document.addEventListener('mouseup',mouseHandler);*/
        },
        disable:function(){
        	$(document).mousedown(mouseHandler);
        	$(document).mousemove(mouseHandler);
        	$(document).mouseup(mouseHandler);
            /*document.removeEventListener('mousedown',mouseHandler);
            document.removeEventListener('mousemove',mouseHandler);
            document.removeEventListener('mouseup',mouseHandler);*/
        }
    }
}

function getDraggingDialog(e){
    var target=e.target;
    while(target && target.className.indexOf('modal-header')==-1 && target.className.indexOf('modal-title')==-1){
        target=target.offsetParent;
    }
    if(target!=null){
        return target.offsetParent;
    }else{
        return null;
    }
}
    
topmyDragging(getDraggingDialog).enable();