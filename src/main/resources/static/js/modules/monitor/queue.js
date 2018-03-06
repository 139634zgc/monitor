$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'monitor/queue/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 105, key: true },
			{ label: '队列id', name: 'queueid', index: 'queueId', width: 105 }, 			
			{ label: '队列名称', name: 'queuename', index: 'queueName', width: 180 }, 			
			{ label: '父队列id', name: 'parentqueueid', index: 'parentQueueId', width: 105 }, 			
			{ label: '队列路径', name: 'queuepath', index: 'queuePath', width: 180 }, 
			{ label: '是否叶节点', name: 'leaf', index: 'leaf', width: 140 },
			{ label: '插入时间', name: 'inserttime', index: 'insertTime', width: 200 }, 			
			{ label: '更新时间', name: 'updatetime', index: 'updateTime', width: 200 }, 			
			{ label: '更新描述', name: 'desc', index: 'desc', width: 240 }			
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
        rownumbers: true, 
        rownumWidth: 25, 
//        autowidth:true,
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
        	$("#jqGrid").setGridWidth($(window).width()*0.99);
//        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        	$("#cb_jqGrid").addClass("checkbox");
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		queue: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.queue = {};
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
			var url = vm.queue.id == null ? "monitor/queue/save" : "monitor/queue/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.queue),
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
				    url: baseURL + "monitor/queue/delete",
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
			$.get(baseURL + "monitor/queue/info/"+id, function(r){
                vm.queue = r.queue;
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