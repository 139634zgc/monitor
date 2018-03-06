var winWeight = window.innerWidth - 100;
var winHight = window.innerHeight / 2 + 100;

$(function() {
    fetchSmallFileStatisList();
    var list = fetchMonitorItemList();
    initFileBlockChart(list);

    //点选值变动，立即触发更新数据
    $("#dateRange").click(function()
    {
        fetchSmallFileStatisList();
    });
});

function fetchMonitorItemList() {
    var url = "hdfs/monitor_item/list";
    var pageLimit = 7;
    var ret =  new Array();
    $.ajax({
        type: "POST",
        url: baseURL + url,
        async: false,
        data: {limit: pageLimit, page:1, sidx:"create_date", order:"desc"},
        success: function(r){
            if(r.code === 0){
                ret = r.page.list;
            }
        }
    });
    return ret;
}

function fetchSmallFileStatisList() {
    var url = "hdfs/small_file/list_daily_statis";
    var pageLimit;
    var dateRange = $("input[name='dateRange']:checked").val();
    if (dateRange == "2week") {
        pageLimit = 14;
    } else if (dateRange == "week") {
        pageLimit = 7;
    } else if (dateRange == "3month") {
        pageLimit = 90;
    } else {
        pageLimit = 30;
    }

    $.ajax({
        type: "POST",
        url: baseURL + url,
        data: {limit: pageLimit, page:1, sidx:"create_date", order:"desc"},
        success: function(r){
            if(r.code === 0){
                initSmallFileStatisChart(r.page.list);
            }else{
                var emptyList = new Array();
                initSmallFileStatisChart(emptyList);
            }
        }
    });
}

function initFileBlockChart(list) {
    var xAxisData = new Array();
    var seriesFileData = new Array();
    var seriesBlockData = new Array();
    var j = 0;
    for(var i = list.length - 1; i >= 0; i--) {
        j++;
        if(j > 7) {
            break;
        }

        xAxisData[list.length - i -1] = list[i].createDate;
        seriesFileData[list.length - i -1] = parseInt((list[i].fileCount)/10000);
        seriesBlockData[list.length - i -1] = parseInt((list[i].blockCount)/10000);

    }

    var fileBlockChartElement = document.getElementById('fileBlockChart');
    //自适应宽高
    var myChartContainer = function () {
        fileBlockChartElement.style.width = winWeight +'px';
        fileBlockChartElement.style.height = winHight +'px';
    };

    myChartContainer();

    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(fileBlockChartElement, "customed");

    var labelOption = {
        normal: {
            show: false,
            position: 'inside',
            distance: 15,
            align: 'left',
            verticalAlign: 'middle',
            rotate: 90,
            fontSize: 16,
            rich: {
                name: {
                    textBorderColor: '#fff'
                }
            }
        }
    };

    option = {
        title: {
            text: "文件数和Block数(单位万)"
        },
        color: ['#8E8E38','#EE7621'],
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        legend: {
            data: ['文件数', 'Block数'],
            y: 'bottom'
        },
        calculable: true,
        xAxis: [
            {
                //name: "日期",
                type: 'category',
                axisTick: {show: false},
                data: xAxisData
            }
        ],
        yAxis: [
            {
                //name: "单位: 万",
                type: 'value'
            }
        ],
        series: [
            {
                name: '文件数',
                type: 'bar',
                barGap: 0,
                label: labelOption,
                data: seriesFileData,
                barWidth: 40,
                itemStyle: {
                    normal: {
                        color: "#FFA1A8"
                    }
                },
            },
            {
                name: 'Block数',
                type: 'bar',
                label: labelOption,
                data: seriesBlockData,
                barWidth: 40,
                itemStyle: {
                    normal: {
                        color: "#83E284"
                    }
                },
            }
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    window.onresize = function ()
    {
        myChartContainer();
        myChart.resize();
    };
}


function initSmallFileStatisChart(list) {
    var xAxisData = new Array();
    var seriesAData = new Array();
    var seriesBData = new Array();
    for(var i = list.length - 1; i >= 0; i--) {
        xAxisData[list.length - i -1] = list[i].createDate;
        seriesAData[list.length - i -1] = list[i].smallFileCount;
        seriesBData[list.length - i -1] = list[i].smallDirCount;
    }

    var smallFileChartElement = document.getElementById('smallFileChart')
    //自适应宽高
    var myChartContainer = function () {
        smallFileChartElement.style.width = winWeight +'px';
        smallFileChartElement.style.height = winHight +'px';
    };
    myChartContainer();

    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(smallFileChartElement, "customed");

    var title = "小文件文件数和小文件目录数";
    var seriesAName = "小文件文件数";
    var seriesBName = "小文件目录数";


    // 指定图表的配置项和数据
    option = {
        title: {
            text: title
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:[seriesAName,seriesBName],
            bottom: 10,
            left: 'center',
        },
        grid: {
            x:90,
        },
        toolbox: {
            feature: {
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: xAxisData
        },
        yAxis: [
            {
                name: seriesAName,
                type: 'value',

            },
            {
                name: seriesBName,
                type: 'value',

            }
        ],
        series: [
            {
                name:seriesAName,
                type:'line',
                data:seriesAData,
                itemStyle: {
                    normal: {
                        color: "#53B0EF"
                    }
                },
            },
            {
                name:seriesBName,
                type:'line',
                yAxisIndex:1,
                data:seriesBData,
                itemStyle: {
                    normal: {
                        color: "#83E284"
                    }
                },
            }
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);

    $(window).resize(function(){
        myChartContainer();
        myChart.resize();
    });

}
