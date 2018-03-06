$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'hdfs/table/list',
        datatype: "json",
        colModel: [
            { label: '表名', name: 'tblName', index: 'tbl_name', width: 40 },
            { label: '表路径', name: 'tblLocation', index: 'tbl_location', width: 100 },
            { label: '表大小(M)', name: 'tblSize', index: 'tbl_size', width: 30 },
            { label: '所属库', name: 'dbName', index: 'db_name', width: 40 },
            { label: '表归属人', name: 'tblOwner', index: 'tbl_owner', width: 15 },
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
        sortname: "hdfs_tablestatis_id",
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

var vm1 = new Vue({
    el:'#rrapp',
    data:{
        q:{
            tblName: null,
        },
        showList: true,
        title: null,
    },
    methods: {
        query: function () {
            vm1.reload();
        },
        reload: function (event) {
            vm1.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                //postData:{'tblName': vm1.q.tblName},
                postData:{'tblName': $("#tableQuery").val()},
                page:1
            }).trigger("reloadGrid");
        }
    }
});