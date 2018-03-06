$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'generator/yarnapplication/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 250, key: true },
			{ label: '任务所属用户', name: 'user', index: 'user', width: 100 },
			{ label: '应用任务', name: 'name', index: 'name', width: 300 },
			{ label: '队列名称', name: 'queue', index: 'queue', width: 100 },
			{ label: '状态', name: 'state', index: 'state', width: 100 },
			{ label: '最终状态', name: 'finalstatus', index: 'finalStatus', width: 100 },
			{ label: '进度', name: 'progress', index: 'progress', width: 40 },
			// { label: '诊断信息', name: 'diagnostics', index: 'diagnostics', width: 80 },
			{ label: '应用类型', name: 'applicationtype', index: 'applicationType', width: 120 },
			{ label: '', name: 'starttime', index: 'startTime', width: 150 },
			{ label: '', name: 'finishedtime', index: 'finishedTime', width: 150 },
			{ label: '占用时间', name: 'elapsedtime', index: 'elapsedTime', width: 80 }, 			
			{ label: '分配内存', name: 'allocatedmb', index: 'allocatedMB', width: 80 }, 			
			{ label: '', name: 'allocatedvcores', index: 'allocatedVcores', width: 80 }, 			
			{ label: '', name: 'runningcontainers', index: 'runningContainers', width: 80 }, 			
			{ label: '', name: 'memoryseconds', index: 'memorySeconds', width: 80 }, 			
			{ label: '', name: 'vcoreseconds', index: 'vcoreSeconds', width: 80 }, 			
			{ label: '', name: 'preemptedresourcemb', index: 'preemptedResourceMB', width: 80 }, 			
			{ label: '', name: 'preemptedresourcevcores', index: 'preemptedResourceVCores', width: 80 }, 			
			{ label: '', name: 'numnonamcontainerpreempted', index: 'numNonAMContainerPreempted', width: 80 }, 			
			{ label: '', name: 'numamcontainerpreempted', index: 'numAMContainerPreempted', width: 80 }			
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25,
		autowidth:false,
        width:window.screen.availWidth-20,
        shrinkToFit:false,
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
        	// $("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
            $("#jqGrid").setGridWidth($(window).width()*0.99);
            $("#cb_jqGrid").addClass("checkbox");
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		yarnApplication: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.yarnApplication = {};
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
			var url = vm.yarnApplication.id == null ? "generator/yarnapplication/save" : "generator/yarnapplication/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.yarnApplication),
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
				    url: baseURL + "generator/yarnapplication/delete",
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
			$.get(baseURL + "generator/yarnapplication/info/"+id, function(r){
                vm.yarnApplication = r.yarnApplication;
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