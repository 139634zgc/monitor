$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'hdfs/hdfsdepartmentdb/list',
        datatype: "json",
        colModel: [
            { label: 'hdfsDepartmentDbId', name: 'hdfsDepartmentDbId', index: 'hdfs_department_db_id', width: 50, key: true },
            { label: '库名', name: 'dbName', index: 'db_name', width: 80 },
            { label: '归属人', name: 'owner', index: 'owner', width: 80 },
            //{ label: '部门ID', name: 'departmentId', index: 'department_id', width: 80 },
            { label: '部门', name: 'departmentName', width: 80 }
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList : [10,30,50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth:true,
        multiselect: true,
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

    initdepartmentselect();
});

function initdepartmentselect() {
    var url = "hdfs/hdfsdepartment/list";
    var ret =  new Array();
    $.ajax({
        type: "POST",
        url: baseURL + url,
        async: false,
        data: {limit: 1000, page:1},
        success: function(r){
            if(r.code === 0){
                ret = r.page.list;
                for(var i in ret){
                    $("#departmentselect").append("<option value=" + ret[i].hdfsDepartmentId + ">" + ret[i].name + "</option>");
                }
            }
        }
    });
}

var vm = new Vue({
    el:'#rrapp',
    data:{
        showList: true,
        title: null,
        hdfsDepartmentDb: {}
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function(){
            vm.showList = false;
            vm.title = "新增";
            vm.hdfsDepartmentDb = {};
        },
        update: function (event) {
            var hdfsDepartmentDbId = getSelectedRow();
            if(hdfsDepartmentDbId == null){
                return ;
            }
            vm.showList = false;
            vm.title = "修改";

            vm.getInfo(hdfsDepartmentDbId)
        },
        saveOrUpdate: function (event) {
            var url = vm.hdfsDepartmentDb.hdfsDepartmentDbId == null ? "hdfs/hdfsdepartmentdb/save" : "hdfs/hdfsdepartmentdb/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.hdfsDepartmentDb),
                success: function(r){
                    if(r.code === 0){
                        alert('操作成功', function(index){
                            vm.reload();
                        });
                    }else{
                        alert(r.msg);
                    }
                }
            });
        },
        del: function (event) {
            var hdfsDepartmentDbIds = getSelectedRows();
            if(hdfsDepartmentDbIds == null){
                return ;
            }

            confirm('确定要删除选中的记录？', function(){
                $.ajax({
                    type: "POST",
                    url: baseURL + "hdfs/hdfsdepartmentdb/delete",
                    contentType: "application/json",
                    data: JSON.stringify(hdfsDepartmentDbIds),
                    success: function(r){
                        if(r.code == 0){
                            alert('操作成功', function(index){
                                $("#jqGrid").trigger("reloadGrid");
                            });
                        }else{
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        getInfo: function(hdfsDepartmentDbId){
            $.get(baseURL + "hdfs/hdfsdepartmentdb/info/"+hdfsDepartmentDbId, function(r){
                vm.hdfsDepartmentDb = r.hdfsDepartmentDb;
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                page:page
            }).trigger("reloadGrid");
        }
    }
});