


var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
            wfId: ''
		},
		showList: true,
		title:null

	},
	methods: {
		query: function () {
            this.findScheduleList();
		},

         findScheduleList:function()
        {
            var self = this;
    var url = "generator/yarnapplication/dependent?wfId="+self.q.wfId;
    $.ajax({
        type: "GET",
        url: baseURL + url,
        contentType: "application/json",
        success: function(r){

            if(r.code === 0){
                var data=r.page;
                self.showChart(data);
            }else{
                alert(r.msg);
            }
        }
    });
},
        showChart:function (data)
{
    //开始绘制内存图表
    var echart = echarts.init(document.getElementById("task_dependent"));
    echart.showLoading();
    var arr = new Array();
    var arr1 = new Array();
    var rigthArr = ["60%","18%"];

    for(var i = 0;i<data.length;i++){
        var obj = new Object();
        obj.name = data[i].name;
        obj.icon = "rectangle";
        arr[i] = obj;
        var res = {
            type: 'tree',

            name: '',

            data: '',

            top:  Math.random()*100+'%',
            left: '60%',
            bottom: '22%',
            right: rigthArr[i%2],

            symbolSize: 7,

            label: {
                normal: {
                    position: 'left',
                    verticalAlign: 'middle',
                    align: 'right'
                }
            },

            leaves: {
                label: {
                    normal: {
                        position: 'right',
                        verticalAlign: 'middle',
                        align: 'left'
                    }
                }
            },

            expandAndCollapse: true,

            animationDuration: 550,
            animationDurationUpdate: 750

        }

        res.data = [data[i]];
        res.name = data[i].name;
        arr1[i] = res;
    }


    option = {
        tooltip: {
            trigger: 'item',
            triggerOn: 'mousemove'
        },
        legend: {
            top: '2%',
            left: '3%',
            orient: 'vertical',


            data: arr,
            borderColor: '#c23531'
        },
        series:arr1
    };
    echart.hideLoading();
    echart.setOption(option,true)

    //---------------------------------结束-----
}

// validator: function () {
        //     if(isBlank(vm.user.username)){
        //         alert("用户名不能为空");
        //         return true;
        //     }
        //
        //     if(vm.user.userId == null && isBlank(vm.user.password)){
        //         alert("密码不能为空");
        //         return true;
        //     }
        //
        //     if(isBlank(vm.user.email)){
        //         alert("邮箱不能为空");
        //         return true;
        //     }
        //
        //     if(!validator.isEmail(vm.user.email)){
        //         alert("邮箱格式不正确");
        //         return true;
			// }
        // }
	}
});