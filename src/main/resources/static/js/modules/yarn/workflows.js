$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'generator/workflows/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '', name: 'wfid', index: 'wfId', width: 80 }, 			
			{ label: '', name: 'parentid', index: 'parentId', width: 80 }, 			
			{ label: '', name: 'appname', index: 'appName', width: 80 }, 			
			{ label: '', name: 'createdtime', index: 'createdTime', width: 80 }, 			
			{ label: '', name: 'starttime', index: 'startTime', width: 80 }, 			
			{ label: '', name: 'endtime', index: 'endTime', width: 80 }, 			
			{ label: '', name: 'user', index: 'user', width: 80 }, 			
			{ label: '', name: 'status', index: 'status', width: 80 }, 			
			{ label: '', name: 'externalid', index: 'externalId', width: 80 }, 			
			{ label: '', name: 'externalchildids', index: 'externalChildIDs', width: 80 }, 			
			{ label: '', name: 'doneflag', index: 'doneFlag', width: 80 }			
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
		workflows: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.workflows = {};
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
			var url = vm.workflows.id == null ? "generator/workflows/save" : "generator/workflows/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.workflows),
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
				    url: baseURL + "generator/workflows/delete",
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
			$.get(baseURL + "generator/workflows/info/"+id, function(r){
                vm.workflows = r.workflows;
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