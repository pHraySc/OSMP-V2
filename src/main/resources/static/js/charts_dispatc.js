$(document).ready(function(){
	 //初始化网页导航模块
	 // Header = new Header();
	 // Header.init();
	 var notstart=0,process=0,success=0,fail=0,timeout=0,totalnum=0;
	 //series数据定义
	 var seriesData = [];
	  
	  require.config({
		   paths: {
		   echarts: '../js/echarts-2.2.7/build/dist'
		          }
		     });
	  
	  require(
			  [
		        'echarts',
		        'echarts/chart/pie'
		    ], 
	            function(ec){
	                var myChart = ec.init(document.getElementById('MyChart4'));
	                myChart.showLoading({
	                    text: '正在努力的读取数据中...',    //loading话术
	                });

	                //获取数量
	                $.ajax({
	                	type:"post",
	                	url:"/chartsController/queryDispatcNum",//查询总条数
	                	dataType:"json",
	                	data:{},
	                	async : false,
	                	success : function(data) {
	                		if (data != null) {
	                			totalnum = data.totalnum;
	                			process = data.process;
	                			success = data.success;
	                			fail = data.fail;
	                			timeout = data.timeout;
	                			notstart = data.notstart;
	                			//ajax请求数据不为空填充series数据
	                			seriesData=[ {value:success, name:'运行成功'},
	                		                {value:fail, name:'运行失败'},
	                		                {value:process, name:'运行中'},
	                		                {value:notstart, name:'未开始'},
	                		                {value:timeout, name:'超时'}]
	                			
	                		}
	                	}
	                });
	                
	                // ajax 回调
	                myChart.hideLoading();
	                
	                var dataStyle = {
	                	    normal: {
	                	        label: {show:false},
	                	        labelLine: {show:false}
	                	    }
	                	};
	                	var placeHolderStyle = {
	                	    normal : {
	                	        color: 'rgba(0,0,0,0)',
	                	        label: {show:false},
	                	        labelLine: {show:false}
	                	    },
	                	    emphasis : {
	                	    }
	                	};
	                	
	                    
	                	var option = {
	                			backgroundColor : '#F5F5F5',
	                			title : {
	                				text: '调度监控情况',
	                                x: 'center',
	                                y: '10',
	                                textStyle: {
	                                     fontSize: 14,
	                                     fontWeight: 'bolder',
	                                     color: '#4b4b4b'
	                                 }
	                			},
	                		    tooltip : {
	                		        trigger: 'item',
	                		        formatter: "{a} <br/>{b} : {c} ({d}%)"
	                		    },
	                		    legend: {
	                		    	orient: 'vertical',
	                                x: 'right',
	                                y: 10,
	                                itemWidth: 6,
	                                itemHeight: 12,
	                                selectedMode: true,
	                		        data:['运行成功','运行失败','运行中','未开始','超时'],
	                		        formatter:function(data){
	                		        	var arry;
	                		        	if (data == '运行中') {
											arry = data + process + "个";
										}else if (data == '未开始') {
											arry = data + notstart + "个";
										}else if (data == '运行成功') {
											arry = data + success + "个";
										}else if (data == '运行失败') {
											arry = data + fail + "个";
										}else if (data == '超时') {
											arry = data + timeout + "个";
										}
	                		        	
	                		    		return arry;
	                		    	}
	                		    },
	                		    calculable : false,
	                		    series : [
	                		        {
	                		            name:'百分比',
	                		            type:'pie',
	                		            radius : ['40%', '60%'],
	                		            itemStyle : {
	                		                normal : {
	                		                    label : {
	                		                    	 position: 'center',
	                                                 distance: 8,
	                                                 textStyle: {
	                                                     color: '#4b4b4b',
	                                                     fontSize: '14'
	                                                 },
	                                                 formatter: function(){
	                                                     return "总量" + "\n" + totalnum + "个"
	                                                 }
	                		                    },
	                		                    labelLine : {
	                		                        show : false,
	                		                        length: 16
	                		                    }
	                		                },
	                		                emphasis : {
	                		                    label : {
	                		                        show : true,
	                		                        position : 'outer',
	                		                        textStyle : {
	                		                            fontSize : '20',
	                		                            fontWeight : 'bold'
	                		                        }
	                		                    }
	                		                }
	                		            },
	                		            data:seriesData
	                		        }
	                		    ]
	                		};

	                myChart.setOption(option);
	                
	                //legend点击选中事件
	                var ecConfig = require('echarts/config');
	                myChart.on(ecConfig.EVENT.LEGEND_SELECTED, function (param){
	                	var leg=param.target;
	                	var chartStatus;
	                	if (leg == '运行成功') {
	                		chartStatus = 'success';
						}else if (leg == '运行失败') {
							chartStatus = 'fail';
						}else if (leg == '运行中'){
							chartStatus = 'process';
						}else if (leg == '未开始'){
							chartStatus = 'notstart';
						}else if (leg == '超时'){
							chartStatus = 'timeout';
						}
	                	
	                	location.href="baseMonitor_DISPATC.ftl?chartStatus="+chartStatus;
	                });  
	                
	            }
	    );
});