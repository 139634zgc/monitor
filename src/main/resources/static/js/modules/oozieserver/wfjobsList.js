$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'oozieServer/wfjobsCollect/list',
        datatype: "json",
        colModel: [
			  { label: '任务名', name: 'appName', index: 'app_name', width: 20 },
              { label: '启动时间', name: 'startTime', index: 'app_name', width: 20 },
              { label: '结束时间', name: 'endTime', index: 'app_name', width: 20 },
              { label: '时长(分钟)', name: 'times', index: 'app_name', width: 20 },
              { label: '执行状态', name: 'status', index: 'app_name', width: 20 },
              { label: '拥有者', name: 'userName', index: 'user_name', width: 20 }
        ],
        postData:{'status': sessionStorage.getItem("status")},
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
        label: {normal: {
             show: true
        }},
        prmNames : {
            page:"page",
            rows:"limit"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
        }
    });
    $("#status").val(sessionStorage.getItem("status"));
   sessionStorage.clear();
});

var setting = {
	data: {
		simpleData: {
			enable: true,
			idKey: "menuId",
			pIdKey: "parentId",
			rootPId: -1
		},
		key: {
			url:"nourl"
		}
	},
	check:{
		enable:true,
		nocheckInherit:true
	}
};
var ztree;

var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			username: '',
			status: '',
			times: '',
			startTime: '',
            endTime: ''
		},
		showList: true,
		title:null,
		role:{}
	},

	methods: {
		query: function () {
			vm.reload();
		},
	    reload: function () {
	    	vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                postData:{'username': vm.q.username,'startTime': vm.q.startTime,'endTime': vm.q.endTime,'status': vm.q.status,'times': vm.q.times},
                page:page
            }).trigger("reloadGrid");
		}
	}
});





