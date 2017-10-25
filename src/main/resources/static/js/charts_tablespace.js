$(document).ready(function(){
	 //初始化网页导航模块
	 // Header = new Header();
	 // Header.init();
	  var normalNum=0,expNum=0;
	  //获取数量
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
	                var myChart = ec.init(document.getElementById('MyChart2'));
	                
	                myChart.showLoading({
	                    text: '正在努力的读取数据中...',    //loading话术
	                });
	                
	                $.ajax({
	             		 type:"post",
	             		 url:"/chartsController/queryTableSpaceNum",//查询总条数
	             		 dataType:"json",
	             		 data:{},
	             		 async : false,
	             		 success : function(data) {
	             			 if (data) {
	             				 totalnum = data.totalnum;
	             				 normalNum = data.normalNum;
	             				 expNum = data.expNum;
	             				 unknow = data.unknow;
	             				 
	             				seriesData=[{value:normalNum, name:'正常',
		        		                	itemStyle:{
		            		                	normal:{
		            		                		 color: '#64bdec'
		            		                	}
		            		                }},
		            		                {value:expNum, name:'异常',
		        		                	itemStyle:{
		            		                	normal:{
		            		                		 color: '#efb43e'
		            		                	}
		            		                }}]
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
	                			backgroundColor:'#F5F5F5',
	                			title : {
	                				text: '表空间监控情况',
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
	                		        data:['正常','异常'],
	                		        formatter:function(data){
	                		        	var arry;
	                		        	if (data == '正常') {
											arry = data + normalNum + "个";
										}else if (data == '异常') {
											arry = data + expNum + "个";
										}
	                		        	
	                		    		return arry;
	                		    	}
	                		    },
	                		    /*toolbox: {
	                		        show : true,
	                		        feature : {
	                		            mark : {show: true},
	                		            dataView : {show: true, readOnly: false},
	                		            magicType : {
	                		                show: true, 
	                		                type: ['pie', 'funnel'],
	                		                option: {
	                		                    funnel: {
	                		                        x: '25%',
	                		                        width: '50%',
	                		                        funnelAlign: 'center',
	                		                        max: 1548
	                		                    }
	                		                }
	                		            },
	                		            restore : {show: true},
	                		            saveAsImage : {show: true}
	                		        }
	                		    },*/
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
	                		            data:[
	                		                /*{value:totalnum, name:'总量'},*/
	                		                {value:normalNum, name:'正常',
	                		                	itemStyle:{
		                		                	normal:{
		                		                		 color: '#64bdec'
		                		                	}
		                		                }},
	                		                {value:expNum, name:'异常',
	                		                	itemStyle:{
		                		                	normal:{
		                		                		 color: '#efb43e'
		                		                	}
		                		                }}
	                		            ]
	                		        }
	                		    ]
	                		};

	                myChart.setOption(option);
	                
	                //legend点击选中事件
	                var ecConfig = require('echarts/config');
	                myChart.on(ecConfig.EVENT.LEGEND_SELECTED, function (param){
	                	var leg=param.target;
	                	if (leg == '正常') {
	                		chartStatus = 'success';
						}else if (leg == '异常') {
							chartStatus = 'exception';
						}
	                	location.href="baseMonitor_TABLESPACE?chartStatus="+chartStatus;
	                });  
	                
	                
	            }
	    );
});