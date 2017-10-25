$(document).ready(function(){
	 //初始化网页导航模块
	  Header = new Header();
	  Header.init();
	  //数据变量
	  var totalnum=0,normalNum=0,expNum=0,unknow=0;
	  //获得图表的options对象  
      //var optionsAjax = myChart.getOption();  
	  var legendArr;
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
	                var myChart = ec.init(document.getElementById('MyChart1'));
	                myChart.showLoading({
	                    text: '正在努力的读取数据中...',    //loading话术
	                });
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
	                	
	              	  //获取数量
	                    $.ajax({
	                		 type:"post",
	                		 url:"/chartsController/queryFileNum",//查询总条数
	                		 dataType:"json",
	                		 data:{},
	                		 async : false,
	                		 success : function(result) {
	                			 var series = result.series;
	                			 if (result != null) {
	                				 //legendArr = result.legend;
	                				 totalnum = series.totalnum;
	                				 normalNum = series.normalNum;
	                				 expNum = series.expNum;
	                				 unknow = series.unknow;
	                				 seriesData = [ {value:normalNum,
			                		                 name:'正常',
			                		                 itemStyle:{
				                		             normal:{
				                		                 color: '#64bdec'
				                		                	}
			                		                 }},
			                		                {value:expNum, 
			                		                 name:'异常',
			                		                 itemStyle:{
				                		             normal:{
				                		                color: '#efb43e'
				                		                	}
				                		             }},
			                		                {value:unknow, 
			                		                 name:'未知',
			                		                 itemStyle:{
				                		             normal:{
				                		                color: '#BFEFFF'
				                		                	}
				                		             }}]
	              				}
	                		 }
	                	 });
	                    
	                    // ajax 回调
	                    myChart.hideLoading();
	                    
	                	var option = {
	                			backgroundColor:'#F5F5F5',
	                			title : {
	                				text: '文件系统监控情况',
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
	                		        data:['正常','异常','未知'],
	                                //data : legendArr,
	                		        formatter:function(data){
	                		        	var arry;
	                		        	if (data == '正常') {
											arry = data + normalNum + "个";
										}else if (data == '异常') {
											arry = data + expNum + "个";
										}else if (data == '未知') {
											arry = data + unknow + "个";
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
	                                                     fontSize: '10'
	                                                 },
	                                                 formatter: function(){
	                                                     return "总量" + "\n" + totalnum + "个"
	                                                 }
	                		                    },
	                		                    labelLine : {
	                		                        show : false,
	                		                        length:30
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
	                	if (leg == '正常') {
	                		chartStatus = 'normalNum';
						}else if (leg == '异常') {
							chartStatus = 'expNum';
						}else if (leg == '未知'){
							chartStatus = 'unknow';
						}
	                	location.href="baseMonitor_FILE.ftl?chartStatus="+chartStatus;
	                });  
	                
	                
	            }
	    );
});