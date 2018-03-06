var winWeight = window.innerWidth - 100;
var winHight = window.innerHeight / 2 + 100;

$(function() {
    var list = fetchMonitorItemList();
    initDiskPerChart(list);
    initDepartmentDiskPerChart();
    initDailyDiskChart(list);
    initNamenodeMemoryChart(list);

    //点选值变动，立即触发更新数据
    $("#dateRange").click(function()
    {
        var list = fetchMonitorItemList();
        initDailyDiskChart(list);

    });
});

function fetchMonitorItemList() {
    var url = "hdfs/monitor_item/list";
    var pageLimit;
    var dateRange = $("input[name='dateRange']:checked").val();
    if (dateRange == "week") {
        pageLimit = 7;
    } else if (dateRange == "2week") {
        pageLimit = 14;
    } else if (dateRange == "3month") {
        pageLimit = 90;
    } else {
        pageLimit = 30;
    }
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

function initDepartmentDiskPerChart() {
    var url = "hdfs/department/list";
    var list =  new Array();
    $.ajax({
        type: "POST",
        url: baseURL + url,
        async: false,
        data: {limit: 100, page:1},
        success: function(r){
            if(r.code === 0){
                list = r.page.list;
            }
        }
    });
    if(list.length == 0) {
        return;
    }

    var legenddata = new Array();
    for(var i = 0; i < list.length; i++) {
        legenddata[i] = list[i].name;
    }

    var seriesdata = new Array();
    for(var i = 0; i < list.length; i++) {
        var item = new Object();
        item.value = parseInt((list[i].disk)/(1024*1024*1024));
        item.name = list[i].name;
        seriesdata[i] = item;
    }

    var DiskPerChartElement = document.getElementById('departmentPerChartDiv');

    //自适应宽高
    var myChartContainer = function () {
        DiskPerChartElement.style.width = winWeight +'px';
        DiskPerChartElement.style.height = winHight +'px';
    };

    myChartContainer();

    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(DiskPerChartElement, "customed");

    option = {
        title : {
            text: '部门磁盘占用统计(单位G)',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: legenddata
        },
        series : [
            {
                name: '部门磁盘占用',
                type: 'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:seriesdata,
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };

    myChart.setOption(option);
    window.onresize = function ()
    {
        myChartContainer();
        myChart.resize();
    };
}

function initDiskPerChart(list) {
    var length = list.length;
    if(length == 0) {
        return;
    }
    var usedDiskG = 0;
    var increaseDayCount = 0;
    for(var i = 0; i < list.length; i++) {
        if(list[i].increaseDisk < 0) {
            continue;
        }
        increaseDayCount++;
        usedDiskG += list[i].increaseDisk;
    }
    var avgDisk = usedDiskG / increaseDayCount;
    var avgDayCount = Math.round((list[0].totalDisk - list[0].usedDisk) / avgDisk);


    var totalDisk = ((list[0].totalDisk)/1024).toFixed(2);
    var usedDisk = ((list[0].usedDisk)/1024).toFixed(2);
    var freeDisk = (totalDisk - usedDisk).toFixed(2);

    var DiskPerChartElement = document.getElementById('diskPerChartDiv');

    //自适应宽高
    var myChartContainer = function () {
        DiskPerChartElement.style.width = winWeight +'px';
        DiskPerChartElement.style.height = winHight +'px';
    };

    myChartContainer();

    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(DiskPerChartElement, "customed");

    option = {
        title: {
            text: '磁盘使用情况(单位T)',
            left: 'center',

        },
        tooltip : {
            trigger: 'item',
            formatter: "{b} : {c} ({d}%)<br/>" + "剩余磁盘预计可用" + avgDayCount + "天"
        },
        legend: {
            left: 'left',
            orient: 'vertical',
            data: ['剩余磁盘','已使用磁盘']
        },
        color:['#6AC78C', '#FFA1A8'],

        series : [
            {
                type: 'pie',
                radius : '65%',
                center: ['50%', '50%'],
                selectedMode: 'single',
                data:[
                    {
                        value:freeDisk,
                        name: '剩余磁盘',
                    },
                    {
                        value:usedDisk,
                        name: '已使用磁盘',
                    }
                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };

    myChart.setOption(option);
    window.onresize = function ()
    {
        myChartContainer();
        myChart.resize();
    };


}

function initDailyDiskChart(list) {
    var xAxisData = new Array();
    var seriesCData = new Array();
    for(var i = list.length - 1; i >= 0; i--) {

        xAxisData[list.length - i -1] = list[i].createDate;
        seriesCData[list.length - i -1] = list[i].increaseDisk;
    }

    var diskChartElement = document.getElementById('diskChart');
    //自适应宽高
    var myChartContainer = function () {
        diskChartElement.style.width = winWeight +'px';
        diskChartElement.style.height = winHight +'px';
    };

    myChartContainer();

    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(diskChartElement, "customed");

    var title = "日增磁盘(单位G)";
    var seriesCName = "日增磁盘";


    // 指定图表的配置项和数据
    option = {
        title: {
            text: title
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:["日增磁盘"],
            y: 'bottom'
        },
        grid: {
            x:90,
        },
        toolbox: {
        },
        xAxis: {
            //name: "日期",
            type: 'category',
            boundaryGap: false,
            data: xAxisData
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name:seriesCName,
                type:'line',
                data:seriesCData,
                itemStyle: {
                    normal: {
                        color: "#FFA1A8"
                    }
                },
            }
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    window.onresize = myChart.resize;
}


function initNamenodeMemoryChart(list) {
    var xAxisData = new Array();
    var seriesAData = new Array();
    var seriesBData = new Array();
    var seriesCData = new Array();
    if(list.length == 0) {
        return;
    }
    var totalMemmory = list[0].namenodeTotalMomory;
    var criticalMemory = parseInt(totalMemmory * 0.9);

    for(var i = list.length - 1; i >= 0; i--) {
        xAxisData[list.length - i -1] = list[i].createDate;
        seriesAData[list.length - i -1] = list[i].namenodeUsedMomory;
        seriesBData[list.length - i -1] = list[i].namenodeTotalMomory;
        seriesCData[list.length - i -1] = criticalMemory;
    }

    var namenodeMemoryChartElement = document.getElementById('namenodeMemoryChart');
    //自适应宽高
    var myChartContainer = function () {
        namenodeMemoryChartElement.style.width = winWeight +'px';
        namenodeMemoryChartElement.style.height = winHight +'px';
    };

    myChartContainer();

    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(namenodeMemoryChartElement, "customed");

    var title = "Namenode内存(单位M)";
    var seriesAName = "已使用内存";
    var seriesBName = "总内存";
    var seriesCName = "阈值内存";


    // 指定图表的配置项和数据
    option = {
        title: {
            text: title
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:[seriesBName,seriesCName,seriesAName],
            y: 'bottom'
        },
        grid: {
            x:90,
        },
        xAxis: {
            //name: "日期",
            type: 'category',
            boundaryGap: false,
            data: xAxisData
        },
        yAxis: {
            //name: "单位: M",
            type: 'value'
        },
        series: [
            {
                name:seriesAName,
                type:'line',
                data:seriesAData,
                itemStyle: {
                    normal: {
                        color: "#E17CDD"
                    }
                },
            },
            {
                name:seriesBName,
                type:'line',
                data:seriesBData,
                itemStyle: {
                    normal: {
                        color: "#53B0EF"
                    }
                },
            },
            {
                name:seriesCName,
                type:'line',
                data:seriesCData,
                itemStyle: {
                    normal: {
                        color: "#FFA1A8"
                    }
                },
            }
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);

}
