$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'hdfs/monitor_item/list',
        datatype: "json",
        colModel: [
            { label: '日期', name: 'createDate', index: 'create_date', width: 20 },
            { label: '总文件个数', name: 'fileCount', index: 'file_count', width: 20 },
            { label: '总block数', name: 'blockCount', index: 'block_count', width: 20 },
            { label: '总磁盘空间(G)', name: 'totalDisk', index: 'total_disk', width: 30 },
            { label: '已用磁盘空间(G)', name: 'usedDisk', index: 'used_disk', width: 40 },
            { label: '日增磁盘(G)', name: 'increaseDisk', index: 'increase_disk', width: 20 },
            { label: 'namenode已耗内存(M)', name: 'namenodeUsedMomory', index: 'namenode_used_momory', width: 40 },
            { label: '创建时间', name: 'createTime', index: 'create_time', width: 30 }
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList : [10,30,50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth:true,
        multiselect: false,
        sortname: "create_date",
        sortorder: "desc",
        pager: "#jqGridPager",
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
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
        }
    });

});

var vm = new Vue({
    el:'#rrapp',
    data:{
        q:{
            createDate: null
        },
        showList: true,
        title: null,
        hdfsMonitorItem: {}
    },
    methods: {
        query: function () {
            vm.reload();
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'createDate': vm.q.createDate},
                page:page
            }).trigger("reloadGrid");
        }
    }
});