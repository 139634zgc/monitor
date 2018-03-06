$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'get/project/list',
        datatype: "json",
        colModel: [			
			{ label: '项目ID', name: 'id', index: "id", width: 45, key: true },
			{ label: '项目名称', name: 'projectName', index: "project_name", width: 75 },
			{ label: '备注', name: 'projectDesc', width: 100 },
            { label: '所属部门', name: 'department', width: 100 },
            { label: '任务数', name: 'jobCount', index: "jobCount", width: 80},
            { label: '创建时间', name: 'createTime', index: "create_time", width: 80}
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
            projectName:"projectName"
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
			vm.showList = false;
			vm.title = "新增";
			vm.project = {};
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
			var url = vm.project.id == null ? "get/project/save" : "get/project/update";
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
	    getProjectInfo:function(projectId){
            $.get(baseURL + "get/project/"+projectId, function(r){
                vm.project = r.project;
            });
		},
	    reload: function () {
	    	vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                postData:{'projectName': vm.q.projectName},
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