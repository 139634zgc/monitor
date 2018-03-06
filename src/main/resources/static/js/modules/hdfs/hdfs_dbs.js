$(function () {
    $("#jqGrid1").jqGrid({
        url: baseURL + 'hdfs/db/list',
        datatype: "json",
        colModel: [
            { label: '库名', name: 'dbName', index: 'db_name', width: 40 },
            { label: '库路径', name: 'dbLocation', index: 'db_location', width: 100 },
            { label: '库大小(G)', name: 'dbSize', index: 'db_size', width: 30 },
            { label: '库归属人', name: 'dbOwner', index: 'db_owner', width: 15 },
            { label: '统计时间', name: 'createTime', index: 'create_time', width: 35 }
        ],
        viewrecords: true,
        height: 385,
        rowNum: 11,
        rowList : [10,30,50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth:true,
        multiselect: false,
        sortname: "hdfs_dbstatis_id",
        sortorder: "desc",
        pager: "#jqGridPager1",
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
            $("#jqGrid1").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
        }
    });

});

var vm = new Vue({
    el:'#rrapp1',
    data:{
        q:{
            dbName: null,
        },
        showList: true,
        title: null,
    },
    methods: {
        query: function () {
            vm.reload();
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid1").jqGrid('getGridParam','page');
            $("#jqGrid1").jqGrid('setGridParam',{
                postData:{'dbName': vm.q.dbName},
                page:page
            }).trigger("reloadGrid");
        }
    }
});