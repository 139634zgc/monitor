$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'hdfs/small_file/list',
        datatype: "json",
        colModel: [
            { label: '目录名', name: 'directory', index: 'directory', width: 180 },
            { label: '文件个数', name: 'fileCount', index: 'file_count', width: 30 },
            { label: '平均大小(M)', name: 'avgSize', index: 'avg_size', width: 40 },
            { label: '总大小(M)', name: 'totalSize', index: 'total_size', width: 40 },
            { label: '所属用户', name: 'username', index: 'username', width: 30 },
            { label: '统计时间', name: 'createTime', index: 'create_time', width: 80 }
        ],
        sortname: 'avg_size',
        sortorder: 'desc',
        viewrecords: false,
        height: 385,
        rowNum: 11,
        rowList : [10,30,50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth:true,
        multiselect: false,
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
            directory: null
        },
        showList: true,
        title: null,
        hdfsSmallfile: {}
    },
    methods: {
        query: function () {
            vm.reload();
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'directory': vm.q.directory},
                page: 1
            }).trigger("reloadGrid");
        }
    }
});