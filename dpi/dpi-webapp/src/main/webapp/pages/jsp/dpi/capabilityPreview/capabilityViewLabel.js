$(function(){
	

/*var chartDom = document.getElementById('main11');
echarts.dispose(chartDom);
var myChart = echarts.init(chartDom);
myChart.showLoading();


var data2 = {
    "name": "省核心交换机",
    "children": [{
        name: 'XX市核心',
        "children": [

            {
                "name": "BRAS001",
                "children": [{
                    "name": "下联口001",
                    itemStyle: {
                        normal: {
                            color: {
                                image: 'image://http://img.ph.126.net/wIjj_obvlVrnFJuHG5eAvA==/1509550300099644221.jpg', // 支持为 HTMLImageElement, HTMLCanvasElement，不支持路径字符串
                                repeat: 'no-repeat' // 是否平铺, 可以是 'repeat-x', 'repeat-y', 'no-repeat'
                            },
                            borderColor: '#000',
                            borderWidth: 1,
                            borderType: 'solid',
                        }
                    },
                    "children": [{
                            "name": "OLT上联口837",

                            "children": [{
                                "name": "OLT001",
                                "children": [{
                                    "name": "PON口001",

                                    "children": [{
                                        "name": "ONU001",
                                        "value": 2105,
                                        children: [{
                                            name: 'stb7376464764847'
                                        }]
                                    }, {
                                        "name": "ONU002",
                                        "value": 1316,
                                        children: [{
                                            name: 'stb7376464764847'
                                        }]
                                    }, {
                                        "name": "ONU003",
                                        "value": 3151,
                                        children: [{
                                            name: 'stb7376464764847'
                                        }]
                                    }]
                                }, {
                                    "name": "PON口002",

                                    "children": [{
                                        "name": "ONU001",
                                        "value": 2105,
                                        children: [{
                                            name: 'stb7376464764847'
                                        }]
                                    }, {
                                        "name": "ONU002",
                                        "value": 1316,
                                        children: [{
                                            name: 'stb7376464764847'
                                        }]
                                    }, {
                                        "name": "ONU003",
                                        "value": 3151,
                                        children: [{
                                            name: 'stb7376464764847'
                                        }]
                                    }]
                                }],
                            }]
                        },


                    ]
                }]
            }
        ]
    }, {
        "name": "省中心CDN"
    }, ]
};

myChart.hideLoading();


myChart.setOption(option = {
		          tooltip: {
		              show: false
		          },
		          series: [
		              {
		                  type: 'tree',
		                  data: [data2],
		                  top: '1%',
		                  left: '7%',
		                  bottom: '1%',
		                  right: '20%',
		                  symbolSize: 7,
		                  label: {
		                      normal: {
		                          position: 'left',
		                          verticalAlign: 'middle',
		                          align: 'right',
		                          fontSize: 15,
		                         formatter: function (param) {
		                              if (param.name.match('Tra')) {
		                                  return '{a|' + param.name + '}'
		                             } else {
		                                  return param.name;
		                              }
		                          },
		                         rich: {
		                             a: {
		                                  color: 'red',
		                                  lineHeight: 10
		                              }
		                          }
		                    },
		                      emphasis: {
		                          fontSize: 25
		                    }
		                  },
		                  leaves: {
		                      label: {
		                          normal: {
		                              position: 'right',
		                              verticalAlign: 'middle',
		                              align: 'left'
		                        },
		 
		                     }
		                 },
		                 expandAndCollapse: true,
		                 roam: true,  // 缩放
		               animationDuration: 550,
		                 animationDurationUpdate: 750
		             }
		         ]
		     });
myChart.setOption(option = {
    tooltip: {
        trigger: 'item',
        triggerOn: 'mousemove'
    },
    legend: {
        top: '2%',
        left: '3%',
        orient: 'vertical',
        data: [{
            name: 'tree1',
            icon: 'rectangle'
        }, {
            name: 'tree2',
            icon: 'rectangle'
        }],
        borderColor: '#c23531'
    },
    series: [

        {
            type: 'tree',
            name: 'tree2',
            data: [data2],

            top: '20%',
            //left: '5%',
            bottom: '22%',
            right: '18%',

            symbolSize: 20,
            initialTreeDepth: 10,
            label: {
                normal: {
                    position: 'bottom',
                    //verticalAlign: 'middle',
                    align: 'center'
                }
            },

             leaves: {
                 label: {
                     normal: {
                         position: 'right',
                         verticalAlign: 'middle',
                         align: 'left'
                     }
                 }
             },

            expandAndCollapse: true,

            animationDuration: 550,
            animationDurationUpdate: 750
        }
    ]
});
$.get($.cxt +'/capability/getLabelTree', function (data) {
    myChart.hideLoading();
    console.log(data);
    echarts.util.each(data.children, function (datum, index) {
        index % 2 === 0 && (datum.collapsed = true);
    });

    myChart.setOption(option = {
        tooltip: {
            trigger: 'item',
            triggerOn: 'mousemove'
        },
        series: [
            {
                type: 'tree',

                data: [data],

                top: '1%',
                left: '7%',
                bottom: '1%',
                right: '20%',

                symbolSize: 7,

                label: {
                    normal: {
                        position: 'left',
                        verticalAlign: 'middle',
                        align: 'right',
                        fontSize: 9
                    }
                },

                leaves: {
                    label: {
                        normal: {
                            position: 'right',
                            verticalAlign: 'middle',
                            align: 'left'
                        }
                    }
                },

                expandAndCollapse: true,
                animationDuration: 550,
                animationDurationUpdate: 750
            }
        ]
    });
});*/
	
	
	
	
})
/*function searchTwo() {
	var val = $("#detail").val();
	var liList = $("#piForm span");
	var tagetArr=[];
	for(var i =0; i < liList.length; i++ ){
		if($(liList[i]).text().indexOf(val)>-1){
			tagetArr.push(liList[i]);
		}
	}
	if(tagetArr.length==0){
		alert("您查找的内容不存在！");
		return;
	}
	$("#piForm span").removeClass("active");
	$(tagetArr[0]).addClass("active");
	$("#piForm").parent().scrollTop(0);
	$("#piForm").parent().scrollTop($(tagetArr[0]).offset().top-325);
	$("#prodLabelTwoNum").text(tagetArr.length);
	$(".resultTwo").show();
	var index=0;
	changeSelectTwo(tagetArr,index);
}
function changeSelectTwo(str,index){
	$("#prevBtn").click(function(){
		index-=1;
		if(index<0){
			index=str.length-1;
		}
		$("#piForm span").removeClass("active");
		$(str[index]).addClass("active");
		$("#piForm").parent().scrollTop(0);
	    $("#piForm").parent().scrollTop($(str[index]).offset().top-325);
	});
	$("#nextBtn").click(function(){
		index+=1;
		if(index>str.length-1){
			index=0;
		}
		$("#piForm span").removeClass("active");
	    $(str[index]).addClass("active");
	    $("#piForm").parent().scrollTop(0);
	    $("#piForm").parent().scrollTop($(str[index]).offset().top-325);
	});
}*/
function update (){
	
	//总数
	$.ajax({
		url : $.cxt + "/capability/getAllAct", 
		//data : topgetObjByObj($("#showSensiForm .formField")),		//id.class
		dataType:"json",
		type: "POST",
		async:true,  //true（异步）或 false（同步)
		success : function(data) {
			    
			   var obj = JSON.parse(data);
			   var list =obj.data;
			   for(var i=0;i<list.length;i++){
				   var obj=list[i];
				   var ch=obj.chineseName;
				   var fn=obj.foreignName;
				   var fullName = pinyin.getFullChars(ch);
				   var obj1={};
				   obj1.chineseName=ch;
				   obj1.foreignName=fullName;
				  
				   $.ajax({
						url : $.cxt + "/capability/updateAct", 
						data : obj1,		//id.class
						dataType:"json",
						type: "POST",
						async:false,  //true（异步）或 false（同步)
						success : function(data) {
							
							
							
							
							
						}
					});
				   
				   
				   
				   
			   }
			   
              
		}
	});
}

