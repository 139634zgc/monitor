var queueName;
var insertTime;
var winWeight = window.innerWidth - 100;
var winHight = window.innerHeight / 2 + 100;

//格式化日期
Date.prototype.Format = function (fmt) { //author: meizz   
    var o = {  
        "M+": this.getMonth() + 1, //月份   
        "d+": this.getDate(), //日   
        "H+": this.getHours(), //小时   
        "m+": this.getMinutes(), //分   
        "s+": this.getSeconds(), //秒   
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度   
        "S": this.getMilliseconds() //毫秒   
    };  
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));  
    for (var k in o)  
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));  
    return fmt;  
}

//数据请求
function findScheduleList()
{
	var url = "monitor/scheduler/list";
	$.ajax({
		type: "POST",
	    url: baseURL + url,
        //contentType: "application/json",
        data: {timeType: $("input[name='timeType']:checked").val()},
	    success: function(r){
	        
	    	if(r.code === 0){
	    		
	    		for(var key in r.page)
	    		{
	    			showChart(key, r.page[key]);
	    		}

			}else{
				alert(r.msg);
			}
		}
	});
}


//绘制图表函数
function showChart(myType, dataArr)
{
	//---------------------------------开始-----
	var index = 0;
	var legendDatas = new Array();
	var yName;
	var serialData = new Array();
	var title;
	var subTitle;
	for(var key in dataArr)
	{
		var legendData = new Object();
		legendData.name=key;
		legendData.icon='diamond';
		legendDatas[index] = legendData;
		if (null == yName)
		{
			yName = dataArr[key][1];
		} 
		
		var o = new Object();
		o.name=key;
		o.type='line';
		o.smooth=true;
		o.data=dataArr[key][0];
		
		var oNormal = new Object();
		var oAreaStyle = new Object();
		var oType = new Object();
		oType.type='default';
		oAreaStyle.areaStyle = oType;
		oNormal.normal=oAreaStyle;
		o.itemStyle=oNormal;
		serialData[index] = o;
		index++;
	}
	
	//设置图表标题
	if ("cpu" == myType)
	{
		title="队列CPU使用情况分布";
		subTitle="CPU监控    (核数)";
	}
	else
	{
		title="队列内存使用情况分布";
		subTitle="内存监控    (单位:兆)";
	}
	
	var myChart = document.getElementById(myType);

	//自适应宽高
	var myChartContainer = function () {
	    myChart.style.width = winWeight +'px';
	    myChart.style.height = winHight +'px';
		
//		myChart.style.width = window.innerWidth +'px';
//	    myChart.style.height = window.innerHeight +'px';
		
	    //$("#container").width(winWeight);
	    $("#splitLine").width(winWeight);
	};
	myChartContainer();
	
	//开始绘制内存图表
	var echart = echarts.init(myChart, 'customed');
	option = {
		title : {
			text : title,
			subtext : subTitle
		},
		tooltip : {
			trigger : 'axis'
		},
		legend : {
			type: 'scroll',
	        bottom: 1,
			data : legendDatas
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				magicType : {
					show : true,
					type : [ 'line', 'bar', 'stack', 'tiled' ]
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		calculable : true,
		xAxis : [ {
			type : 'category',
			boundaryGap : false,
			data : yName
		} ],
		yAxis : [ {
			type : 'value'
		} ],
		series : serialData
	};

	echart.setOption(option);
	//---------------------------------结束-----
	//浏览器大小改变时重置大小
	window.onresize = function () 
    {
       myChartContainer();
       echart.resize();
    };

    
	var ecConfig = echarts.config;
    function eConsole(param){
    	queueName=param.seriesName;
    	insertTime=param.name;
        $("#jqGrid").jqGrid('setGridParam',{ 
            postData:{
             'queue':queueName,
             'insertTime':insertTime,
             'showCount':$("#showCount").val(),
            }
        }).trigger("reloadGrid");
        
        $("#showList").show();
        hideMyChart();
    }
    
    //在这里做一个点击事件的监听，绑定的是eConsole方法
//    echart.on(ecConfig.EVENT.CLICK, eConsole);        
    echart.on('click', eConsole);
}

//隐藏图表
function hideMyChart()
{
	$("#cpu").hide();
    $("#mem").hide();
    $("#splitLine").hide();
    $("#timeType").hide();
    
    $("#evaluate").hide();
    $("#queueSelect").hide();
}

//显示图表
function showMyChart()
{
	$("#cpu").show();
    $("#mem").show();
    $("#splitLine").show();
    $("#timeType").show();
    
    $("#evaluate").show();
    $("#queueSelect").show();
}

function initTable()
{
	 $("#jqGrid").jqGrid({
	        url: baseURL + 'monitor/scheduler/tasklist',
	        datatype: "json",
	        colModel: [			
				{ label: 'id', name: 'id', index: 'id', width: 240, key: true },
				{ label: '所属用户', name: 'user', index: 'user', width: 130 }, 			
				{ label: '任务名称', name: 'name', index: 'name', width: 240 }, 			
				{ label: '队列名称', name: 'queue', index: 'queue', width: 130 }, 			
				//{ label: '状态', name: 'state', index: 'state', width: 80 }, 			
				{ label: '执行状态', name: 'finalstatus', index: 'finalStatus', width: 130 }, 			
				{ label: '任务类型', name: 'applicationtype', index: 'applicationType', width: 130 }, 			
				{ label: '开始时间', name: 'startedtime', index: 'startedTime', width: 180, formatter: function(value, options, row){
					return new Date(value).Format("yyyy-MM-dd HH:mm:ss");
				}}, 			
				{ label: '运行时间(s)', name: 'elapsedtime', index: 'elapsedTime', width: 130 }, 			
				{ label: '内存占用(mb)', name: 'allocatedmb', index: 'allocatedMB', width:130 }, 			
				{ label: 'CPU占用(vcore)', name: 'allocatedvcores', index: 'allocatedVcores', width: 130 }		
	        ],
	        altRows:true,
	        altclass:'background-color: #DDDDDC; background-image: none;',
			viewrecords: true,
	        height: 385,
//	        rowNum: 10,   //去掉分页
//			rowList : [10,30,50],
	        rownumbers: true, 
	        rownumWidth: 25, 
	        autowidth:false,
	        width:window.screen.availWidth-20,
	        shrinkToFit:false,   
//	        autoScroll: true, 
//	        multiselect: true,
//	        pager: "#jqGridPager",
	        jsonReader : {
	            root: "page.list",
	            page: "page.currPage",
	            total: "page.totalPage",
	            records: "page.totalCount"
	        },
	        prmNames : {
	            page:"page", 
	            rows:"limit", 
	            order: "order"
	        },
	        gridComplete:function(){
	        	//隐藏grid底部滚动条
	        	$("#jqGrid").setGridWidth($(window).width()*0.99);
//	        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "scroll" }); 
	        }
	    });
 }


//设置下拉列表值
function fillQueue() {
	var url = "monitor/scheduler/queuelist";
	$.ajax({
		type : "POST",
		url : baseURL + url,
		success : function(r) {

			if (r.code === 0) {

				for ( var key in r.page) {
					$("#queueList").append(
							"<option value='" + r.page[key] + "'>"
									+ r.page[key] + "</option>");
					
					//设置默认选中值
					if ("root.default" == r.page[key])
					{
						$("#queueList").val(r.page[key]);
					}
				}
				
			} else {
				alert(r.msg);
			}
		}
	});
}

// 数据请求
function queueMonitor()
{
	var url = "monitor/scheduler/queuemonitor";
	
	var selQueue=$("#queueList").val();
	if ("" == selQueue || selQueue == null)
	{
		selQueue = "root.default";
	}
	
	$.ajax({
		type: "POST",
	    url: baseURL + url,
        data: {timeType: $("input[name='timeType']:checked").val(), queueName: selQueue},
	    success: function(r){
	        
	    	if(r.code === 0){
	    		
	    		//展现cpu队列
	    		showQueueChart("cpu", r.cpuResVals, r.cpuSteadyCount, r.cpuMaxRes);
	    		
	    		//展现meme队列
	    		showQueueChart("mem", r.memResVals, r.memSteadyCount, r.memMaxRes);

			}else{
				alert(r.msg);
			}
		}
	});
}


//绘制图表函数
function showQueueChart(myType, dataArr, lineVal, maxRes)
{
	//---------------------------------开始-----
	var yName = dataArr[1];
	var serialData = dataArr[0];
	var title;
	var subTitle;
	var endLine = dataArr[1][dataArr[1].length-1];
	var startLine =  dataArr[1][0];
	var queueName=$("#queueList").val();

	
	//设置图表标题
	if ("cpu" == myType)
	{
		title="CPU队列评估";
		subTitle="[" + queueName + "] 队列评估   (核数)";
	}
	else
	{
		title="内存队列评估";
		subTitle="[" + queueName + "] 队列评估  (单位:兆)";
	}
	
	var myChart = document.getElementById(myType + "queue");

	//自适应宽高
	var myChartContainer = function () {
	    myChart.style.width = (winWeight / 2) +'px';
	    myChart.style.height = (winHight / 2 + 100) +'px';
	
	    $("#splitLine1").width(winWeight);
	};
	myChartContainer();
	
	//开始绘制内存图表
	var echart = echarts.init(myChart, 'customed');
	option = {
		title : {
			text : title,
			subtext : subTitle
		},
		tooltip : {
			trigger : 'axis'
		},
		calculable : true,
		xAxis : [ {
			type : 'category',
			boundaryGap : false,
			data : yName,
//			axisLabel :{  //显示x轴的所有值
//			    interval : 0   
//			},
		} ],
		yAxis : [ {
			type : 'value',
			max : Math.max(Math.round(lineVal / 0.7),maxRes)
		} ],
		grid: { // 控制图的大小，调整下面这些值就可以，  
            x:90//这个就是显示的长度距离 ,80为默认值
            //y:60, //这个表示左边图表距离div上边框的距离,60为默认值
            //x2:20,//这个表示右边图表距离div右边框的距离  ,80为默认值
            //y2:60  //这个表示右边图表距离div下边框的距离，60为默认值
        },
		series : [ {
			name : queueName,
			type : 'line',
			smooth : true,
			itemStyle : {
				normal : {
					areaStyle : {
						type : 'default'
					}
				}
			},
			data : serialData,
			markLine : {
				itemStyle : {
					normal : {
						lineStyle : {
							type : 'dotted',
							color : 'red'
						},
						label : {
							show : true,
							position : 'middle'
						}
					}
				},
				large : true,
				effect : {
					show : false,
					loop : true,
					period : 0,
					scaleSize : 2,
					color : null,
					shadowColor : null,
					shadowBlur : null
				},
				data : [ [ {
					value:lineVal, coord : [ startLine, lineVal ]
				}, {
					coord : [ endLine, lineVal ]
				}
				] ]
			}
		} ]
	};

	echart.setOption(option);
	// ---------------------------------结束-----
	// 浏览器大小改变时重置大小
	window.onresize = function () 
    {
       myChartContainer();
       echart.resize();
    };
}