var winWeight = window.innerWidth - 100;
var winHight = window.innerHeight / 2 + 100;

$(function () {
          	 var url = "oozieServer/oozieCollect/list";
           $.ajax({
               type: "POST",
               url: baseURL + url,
               contentType: "application/json",
               success: function(r){
                   if(r.code === 0){
                       $("#jobcount").html(r.page.jobcount);
                       $("#succeededcount").html(r.page.succeededcount);
                       $("#errorcount").html(r.page.errorcount);
                       $("#readycount").html(r.page.readycount);
                       $("#runingcount").html(r.page.runingcount);
                       $("#waitingcount").html(r.page.waitingcount);
                       initLineChart(r.page.charkey,r.page.chardata,'mem',"每小时启动任务量");
                       initLineChart(r.page.charFinishkey,r.page.charFinishdata,'finishCharDiv',"每小时完成任务量");
                       initLineChart(r.page.charExecutionTimekey,r.page.charExecutionTimedata,'executionTimeCharDiv',"任务执行时长");
                   }else{
                       alert(r.msg);
                   }
               }
           });

});


function initLineChart(keys,datas,divid,title) {
    var xAxisData = new Array();
    var seriesCData = new Array();


    var ks = keys.split(",")
    var ds = datas.split(",")

    for(var i = 0; i < ks.length; i++) {

        xAxisData[i] = ks[i];
        seriesCData[i] = ds[i];
    }

    var diskChartElement = document.getElementById(divid);
    //自适应宽高
    var myChartContainer = function () {
        diskChartElement.style.width = winWeight +'px';
        diskChartElement.style.height = winHight +'px';
    };

    myChartContainer();

    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(diskChartElement, "customed");

    var seriesCName = "数量";
 // 指定图表的配置项和数据
    option = {
        title: {
             text: title,
             x: "center"
        },
        tooltip: {
             trigger: "item",
            formatter: "任务数:{c}"
        },
        legend: {
            data:[]
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        toolbox: {
        },
        xAxis: {
            name: "时间",
            type: 'category',
            boundaryGap: false,
            data: xAxisData
        },
        yAxis: {
            name: "数量",
            type: 'value'
        },
        series: [
            {
                name:seriesCName,
                type:'line',
                data:seriesCData
            }
        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    window.onresize = myChart.resize;
}




var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
            username: '',
            startTime: ''
		},
		showList: true,
		title:null

	},
	methods: {
	query: function () {
         this.findScheduleList();
     },
    redirect: function(rurl,status){
        sessionStorage.setItem("status",status);
    	top.location.hash = rurl;
    },
    getTime(date){
          this.q.startTime = date;
    },

     findScheduleList:function(){
           var self = this;
          var url =  "oozieServer/oozieCollect/list?username="+self.q.username+"&startTime="+self.q.startTime;
          $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                success: function(r){
                    if(r.code === 0){
                         $("#jobcount").html(r.page.jobcount);
                         $("#succeededcount").html(r.page.succeededcount);
                         $("#errorcount").html(r.page.errorcount);
                         $("#readycount").html(r.page.readycount);
                         $("#runingcount").html(r.page.runingcount);
                         $("#waitingcount").html(r.page.waitingcount);
                         initLineChart(r.page.charkey,r.page.chardata,'mem',"每小时启动任务量");
                         initLineChart(r.page.charFinishkey,r.page.charFinishdata,'finishCharDiv',"每小时完成任务量");
                          initLineChart(r.page.charExecutionTimekey,r.page.charExecutionTimedata,'executionTimeCharDiv',"任务执行时长");
                    }else{
                        alert(r.msg);
                    }
                }
          });
      }

}
});

