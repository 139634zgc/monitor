var winWeight = window.innerWidth - 100;
var winHight = window.innerHeight / 2 + 100;

$(function() {
    var dbDiskTopList = fetchDbDiskTop();
    initDbDiskTopListChart(dbDiskTopList);

    var tableDiskTopList = fetchTableDiskTop();
    initTableDiskTopListChart(tableDiskTopList);

    var tableIncreaseTopList = fetchTableDailyIncreaseTop();
    initTableIncreaseDiskTopListChart(tableIncreaseTopList);

    var dbIncreaseTopList = fetchDbDailyIncreaseTop();
    initDbIncreaseDiskTopListChart(dbIncreaseTopList);

    //点选值变动，立即触发更新数据
    $("#increaseTopDateRange").click(function()
    {
        var tableIncreaseTopList = fetchTableDailyIncreaseTop();
        initTableIncreaseDiskTopListChart(tableIncreaseTopList);

    });

    //点选值变动，立即触发更新数据
    $("#increaseTopDateRange1").click(function()
    {
        var dbIncreaseTopList = fetchDbDailyIncreaseTop();
        initDbIncreaseDiskTopListChart(dbIncreaseTopList);

    });

});

function fetchDbDiskTop() {
    var url = "hdfs/db/list_top";
    var ret =  new Array();
    $.ajax({
        type: "POST",
        url: baseURL + url,
        async: false,
        data: {},
        success: function(r){
            if(r.code === 0){
                ret = r.page.list;
            }
        }
    });
    return ret;
}

function fetchTableDiskTop() {
    var url = "hdfs/table/list_top";
    var ret =  new Array();
    $.ajax({
        type: "POST",
        url: baseURL + url,
        async: false,
        data: {},
        success: function(r){
            if(r.code === 0){
                ret = r.page.list;
            }
        }
    });
    return ret;
}


function fetchTableDailyIncreaseTop() {
    var dateRange = $("input[name='increaseTopDateRange']:checked").val();
    var url = "hdfs/table/list_increase_top";
    var ret =  new Array();
    $.ajax({
        type: "POST",
        url: baseURL + url,
        async: false,
        data: {daterange: dateRange},
        success: function(r){
            if(r.code === 0){
                ret = r.page.list;
            }
        }
    });
    return ret;
}

function fetchDbDailyIncreaseTop() {
    var dateRange = $("input[name='increaseTopDateRange1']:checked").val();
    var url = "hdfs/db/list_increase_top";
    var ret =  new Array();
    $.ajax({
        type: "POST",
        url: baseURL + url,
        async: false,
        data: {daterange: dateRange},
        success: function(r){
            if(r.code === 0){
                ret = r.page.list;
            }
        }
    });
    return ret;
}

function initDbDiskTopListChart(list) {
    var xAxisData = new Array();
    var seriesDbDiskData = new Array();
    for(var i = 0; i < list.length; i++) {
        xAxisData[i] = list[i].dbName;
        seriesDbDiskData[i] =list[i].dbSize;
    }

    var dbDiskTopChartElement = document.getElementById('dbDiskTop');
    //自适应宽高
    var myChartContainer = function () {
        dbDiskTopChartElement.style.width = winWeight +'px';
        dbDiskTopChartElement.style.height = winHight +'px';
    };

    myChartContainer();

    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(dbDiskTopChartElement, "customed");

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
            text: "库占用磁盘TOP(单位G)"
        },
        color: ['#83E284'],
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        legend: {
            data: ['库磁盘']
        },
        calculable: true,
        grid: {
            left: '10%',
            bottom:'35%'
        },
        xAxis: [
            {
                //name: "库名",
                type: 'category',
                axisTick: {show: false},
                data: xAxisData,
                axisLabel:{
                    interval: 0,
                    rotate: -30
                }
            }
        ],
        yAxis: [
            {
                //name: "单位: G",
                type: 'value'
            }
        ],
        series: [
            {
                name: '库磁盘',
                type: 'bar',
                barGap: 0,
                label: labelOption,
                data: seriesDbDiskData,
                barWidth: 40,
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

    myChart.on('click', eConsole);

    window.onresize = function ()
    {
        myChartContainer();
        myChart.resize();
    };
}

function initTableDiskTopListChart(list) {
    var xAxisData = new Array();
    var seriesDbDiskData = new Array();
    for(var i = 0; i < list.length; i++) {
        xAxisData[i] = list[i].tblName;
        seriesDbDiskData[i] =list[i].tblSize;
    }

    var dbDiskTopChartElement = document.getElementById('tableDiskTop');
    //自适应宽高
    var myChartContainer = function () {
        dbDiskTopChartElement.style.width = winWeight +'px';
        dbDiskTopChartElement.style.height = winHight +'px';
    };

    myChartContainer();

    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(dbDiskTopChartElement, "customed");

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
            text: "表占用磁盘TOP(单位G)"
        },
        color: ['#83E284'],
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        legend: {
            data: ['表磁盘']
        },
        calculable: true,
        grid: {
            left: '10%',
            bottom:'35%'
        },
        xAxis: [
            {
                //name: "库名",
                type: 'category',
                axisTick: {show: false},
                data: xAxisData,
                axisLabel:{
                    interval: 0,
                    rotate: -30
                }
            }
        ],
        yAxis: [
            {
                //name: "单位: G",
                type: 'value'
            }
        ],
        series: [
            {
                name: '表磁盘',
                type: 'bar',
                barGap: 0,
                label: labelOption,
                data: seriesDbDiskData,
                barWidth: 40,
                itemStyle: {
                    normal: {
                        color: "#53B0EF"
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

function eConsole(param) {
    console.log(param.name);

    if (typeof param.seriesIndex != 'undefined') {
        $("#tableQuery").val(param.name);
        vm1.query();
        $("html,body").animate({scrollTop:$("#tableQuery").offset().top}, 800);
    }
}

function initTableIncreaseDiskTopListChart(list) {
    var xAxisData = new Array();
    var seriesTableDiskData = new Array();
    for(var i = 0; i < list.length; i++) {
        xAxisData[i] = list[i].tblName;
        seriesTableDiskData[i] =list[i].increaseDisk;
    }

    var tableIncreaseTopChartElement = document.getElementById('tableIncreaseTop');
    //自适应宽高
    var myChartContainer = function () {
        tableIncreaseTopChartElement.style.width = winWeight +'px';
        tableIncreaseTopChartElement.style.height = winHight +'px';
    };

    myChartContainer();

    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(tableIncreaseTopChartElement, "customed");

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
            text: "表日增磁盘TOP(单位G)"
        },
        color: ['#2FB1F5'],
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        legend: {
            data: ['表日增磁盘']
        },
        calculable: true,
        grid: {
            left: '10%',
            bottom:'35%'
        },
        xAxis: [
            {
                //name: "表名",
                type: 'category',
                axisTick: {show: false},
                data: xAxisData,
                axisLabel:{
                    interval: 0,
                    rotate: -30
                }
            }
        ],
        yAxis: [
            {
                //name: "单位: G",
                type: 'value'
            }
        ],
        series: [
            {
                name: '表日增磁盘',
                type: 'bar',
                barGap: 0,
                label: labelOption,
                data: seriesTableDiskData,
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

function initDbIncreaseDiskTopListChart(list) {
    var xAxisData = new Array();
    var seriesTableDiskData = new Array();
    for(var i = 0; i < list.length; i++) {
        xAxisData[i] = list[i].dbName;
        seriesTableDiskData[i] =list[i].increaseDisk;
    }

    var tableIncreaseTopChartElement = document.getElementById('dbIncreaseTop');
    //自适应宽高
    var myChartContainer = function () {
        tableIncreaseTopChartElement.style.width = winWeight +'px';
        tableIncreaseTopChartElement.style.height = winHight +'px';
    };

    myChartContainer();

    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(tableIncreaseTopChartElement, "customed");

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
            text: "库日增磁盘TOP(单位G)"
        },
        color: ['#2FB1F5'],
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        legend: {
            data: ['库日增磁盘']
        },
        calculable: true,
        grid: {
            left: '10%',
            bottom:'35%'
        },
        xAxis: [
            {
                //name: "表名",
                type: 'category',
                axisTick: {show: false},
                data: xAxisData,
                axisLabel:{
                    interval: 0,
                    rotate: -30
                }
            }
        ],
        yAxis: [
            {
                //name: "单位: G",
                type: 'value'
            }
        ],
        series: [
            {
                name: '库日增磁盘',
                type: 'bar',
                barGap: 0,
                label: labelOption,
                data: seriesTableDiskData,
                barWidth: 40,
                itemStyle: {
                    normal: {
                        color: "#E17CDD"
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