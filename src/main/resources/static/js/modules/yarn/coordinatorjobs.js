$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'generator/coordinatorjobs/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '', name: 'coordjobid', index: 'coordJobId', width: 80 }, 			
			{ label: '', name: 'user', index: 'user', width: 80 }, 			
			{ label: '', name: 'coordjobname', index: 'coordJobName', width: 80 }, 			
			{ label: '', name: 'frequency', index: 'frequency', width: 80 }, 			
			{ label: '', name: 'timezone', index: 'timeZone', width: 80 }, 			
			{ label: '', name: 'matThrottling', index: 'mat_throttling', width: 80 }, 			
			{ label: '', name: 'total', index: 'total', width: 80 }, 			
			{ label: '', name: 'starttime', index: 'startTime', width: 80 }, 			
			{ label: '', name: 'endtime', index: 'endTime', width: 80 }, 			
			{ label: '', name: 'status', index: 'status', width: 80 }, 			
			{ label: '', name: 'executionpolicy', index: 'executionPolicy', width: 80 }, 			
			{ label: '', name: 'actions', index: 'actions', width: 80 }, 			
			{ label: '', name: 'group', index: 'group', width: 80 }, 			
			{ label: '', name: 'coordexternalid', index: 'coordExternalId', width: 80 }, 			
			{ label: '', name: 'lastaction', index: 'lastAction', width: 80 }, 			
			{ label: '', name: 'pausetime', index: 'pauseTime', width: 80 }, 			
			{ label: '', name: 'concurrency', index: 'concurrency', width: 80 }, 			
			{ label: '', name: 'timeout', index: 'timeOut', width: 80 }, 			
			{ label: '', name: 'dataset', index: 'dataset', width: 80 }			
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
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		coordinatorjobs: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.coordinatorjobs = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			var url = vm.coordinatorjobs.id == null ? "generator/coordinatorjobs/save" : "generator/coordinatorjobs/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.coordinatorjobs),
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
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "generator/coordinatorjobs/delete",
                    contentType: "application/json",
				    data: JSON.stringify(ids),
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
		getInfo: function(id){
			$.get(baseURL + "generator/coordinatorjobs/info/"+id, function(r){
                vm.coordinatorjobs = r.coordinatorjobs;
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