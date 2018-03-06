$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'monitor/scheduler/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '最大可以运行的app数目', name: 'maxapps', index: 'maxApps', width: 80 }, 			
			{ label: '最小内存', name: 'minresMem', index: 'minRes_mem', width: 80 }, 			
			{ label: '最小cpu核数', name: 'minresCpu', index: 'minRes_cpu', width: 80 }, 			
			{ label: '最大内存', name: 'maxresMem', index: 'maxRes_mem', width: 80 }, 			
			{ label: '最大cpu核数', name: 'maxresCpu', index: 'maxRes_cpu', width: 80 }, 			
			{ label: '已经被使用的内存', name: 'usedresMem', index: 'usedRes_mem', width: 80 }, 			
			{ label: '已经被使用的cpu核数', name: 'usedresCpu', index: 'usedRes_cpu', width: 80 }, 			
			{ label: '固定占用的内存', name: 'steadyfairresMem', index: 'steadyFairRes_mem', width: 80 }, 			
			{ label: '固定占用的cpu核数', name: 'steadyfairresCpu', index: 'steadyFairRes_cpu', width: 80 }, 			
			{ label: '共享内存', name: 'fairresMem', index: 'fairRes_mem', width: 80 }, 			
			{ label: '共享cpu核数', name: 'fairresCpu', index: 'fairRes_cpu', width: 80 }, 			
			{ label: '集群总内存量', name: 'clusterresMem', index: 'clusterRes_mem', width: 80 }, 			
			{ label: '集群总cpu核数', name: 'clusterresCpu', index: 'clusterRes_cpu', width: 80 }, 			
			{ label: '正在等待分配的容器数', name: 'pendingcountainers', index: 'pendingCountainers', width: 80 }, 			
			{ label: '已经分配的容器数', name: 'allocatedcontainers', index: 'allocatedContainers', width: 80 }, 			
			{ label: '固定占用的容器数', name: 'reservedcontainers', index: 'reservedContainers', width: 80 }, 			
			{ label: '队列名称', name: 'queuename', index: 'queueName', width: 80 }, 			
			{ label: '调度策略', name: 'schedulingpolicy', index: 'schedulingPolicy', width: 80 }, 			
			{ label: '数据插入时间', name: 'inserttime', index: 'insertTime', width: 80 }			
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
		scheduler: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.scheduler = {};
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
			var url = vm.scheduler.id == null ? "monitor/scheduler/save" : "monitor/scheduler/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.scheduler),
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
				    url: baseURL + "monitor/scheduler/delete",
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
			$.get(baseURL + "monitor/scheduler/info/"+id, function(r){
                vm.scheduler = r.scheduler;
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