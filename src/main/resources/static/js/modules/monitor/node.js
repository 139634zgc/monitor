$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'monitor/node/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '所在机架位置', name: 'rack', index: 'rack', width: 80 }, 			
			{ label: '状态', name: 'state', index: 'state', width: 80 }, 			
			{ label: '节点id', name: 'nodeid', index: 'nodeId', width: 80 }, 			
			{ label: '主机名', name: 'nodehostname', index: 'nodeHostName', width: 80 }, 			
			{ label: 'http地址', name: 'nodehttpaddress', index: 'nodeHttpAddress', width: 80 }, 			
			{ label: '健康报告', name: 'healthreport', index: 'healthReport', width: 80 }, 			
			{ label: '最后一次健康时间', name: 'lasthealthupdate', index: 'lastHealthUpdate', width: 80 }, 			
			{ label: '已使用内存', name: 'usedmemorymb', index: 'usedMemoryMB', width: 80 }, 			
			{ label: '剩余内存', name: 'availmemorymb', index: 'availMemoryMB', width: 80 }, 			
			{ label: '已使用cpu核数', name: 'usedvirtualcores', index: 'usedVirtualCores', width: 80 }, 			
			{ label: '剩余cpu核数', name: 'availablevirtualcores', index: 'availableVirtualCores', width: 80 }, 			
			{ label: '正在运行容器数目', name: 'numcontainers', index: 'numContainers', width: 80 }, 			
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
		node: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.node = {};
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
			var url = vm.node.id == null ? "monitor/node/save" : "monitor/node/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.node),
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
				    url: baseURL + "monitor/node/delete",
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
			$.get(baseURL + "monitor/node/info/"+id, function(r){
                vm.node = r.node;
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