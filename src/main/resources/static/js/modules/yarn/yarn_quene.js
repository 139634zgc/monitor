//数据请求
$(function findScheduleList()
    {
        var url = "generator/yarnapplication/quene";
        $.ajax({
            type: "POST",
            url: baseURL + url,
            contentType: "application/json",
            success: function(r){

                if(r.code === 0){
                    var queue=r.page.queue;
                    var data=r.page.data;

                    showChart(queue,data);
                }else{
                    alert(r.msg);
                }
            }
        });
    }
)



//绘制图表函数
function showChart(queue,data)
{
	//开始绘制内存图表
	var echart = echarts.init(document.getElementById("yarn_quene"),'customed');
	var type =["阻塞","运行中"];
	// var data ={"pending":[1,2,3,4,5],"running":[1,2,3,4,5]};
	// var quene=["dw","psc","da","default","higth"];
    option = {
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        legend: {
            data: type
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis:  {
            type: 'category',
            data: queue
        },
        yAxis: {
            type: 'value'

        },
        series: [
            {
                name: type[0],
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'insideRight'
                    }
                },
                data: data.pending
            },
            {
                name: type[1],
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'insideRight'
                    }
                },
                data: data.running
            }
        ]
    };

	echart.setOption(option);
	//---------------------------------结束-----
}