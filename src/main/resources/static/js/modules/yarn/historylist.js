$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'generator/historylist/quene',
        datatype: "json",
        colModel: [			
			{ label: '时间', name: 'date', width: 30 },
			{ label: '用户名', name: 'queneName', width: 50 },
			{ label: '任务总数', name: 'taskNum', width: 70 },
			{ label: '失败数', name: 'failNum', width: 150 },
			{ label: '成功数', name: 'successNum', width: 80 }
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: false,
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


$(function () {
    $("#jqGridUser").jqGrid({
        url: baseURL + 'generator/historylist/user',
        datatype: "json",
        colModel: [
            { label: '时间', name: 'date', width: 30 },
            { label: '用户名', name: 'userName', width: 50 },
            { label: '任务总数', name: 'taskNum', width: 70 },
            { label: '失败数', name: 'failNum', width: 150 },
            { label: '成功数', name: 'successNum', width: 80 }
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
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
            $("#jqGridUser").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			key: null,
            startTime: null,
            endTime: null
		},
	},
	methods: {
		query: function () {
		},
		reload: function (event) {
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
				postData:{'key': vm.q.key},
                page:page
            }).trigger("reloadGrid");
		}
	}
});