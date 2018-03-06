$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'get/job/info',
        datatype: "json",
        colModel: [
			{ label: '任务ID', name: 'jobId', index: "jobId", width: 45, key: true },
			{ label: '任务名称', name: 'jobName', index: "jobName", width: 75 },
			{ label: '创建人', name: 'authName', width: 100 },
            { label: '开始时间', name: 'startTime', width: 100 },
            { label: '执行耗时', name: 'execTime', index: "execTime", width: 80},
            { label: '执行状态', name: 'status', index: "execTime", width: 80},
            { label: '三日平均耗时', name: 'avgTime', index: "avgTime", width: 80}
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
            order: "order",
            projectId:"projectId"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
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
            projectName: null
		},
		showList: true,
		title:null,
        project:{}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
            var projectId = getSelectedRow();
            if(projectId == null){
                return ;
            }
			vm.showList = false;
			vm.title = "新增";
            vm.getProjectInfo(projectId);

            },
		update: function () {
			var projectId = getSelectedRow();
			if(projectId == null){
				return ;
			}

			vm.showList = false;
            vm.title = "修改";
            vm.getProjectInfo(projectId);
		},
		del: function (event) {
			var projectIds = getSelectedRows();
			if(projectIds == null){
				return ;
			}

			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "get/project/delete",
                    contentType: "application/json",
				    data: JSON.stringify(projectIds),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								vm.reload();
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		saveOrUpdate: function () {
            if(vm.validator()){
                return ;
            }
            var url = "get/job/save";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.project),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
	    getProjectInfo:function(jobId){
            $.get(baseURL + "get/job/"+jobId, function(r){
                vm.project = r.project;
            });
		},
        projectList:function () {

        },
	    reload: function () {
	    	vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                postData:{'projectId': vm.q.projectId},
                page:page
            }).trigger("reloadGrid");
		},
        validator: function () {
            if(isBlank(vm.project.projectName)){
                alert("角色名不能为空");
                return true;
            }
        }
	}
});