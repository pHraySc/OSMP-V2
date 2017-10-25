	$(document).ready(function(){
		 //初始化网页导航模块
		  Header = new Header();
		  Header.init();
		  Header.show("confModel");
		 $(".base-table").css("height",(document.documentElement.clientHeight-150)+"px");
		 //页面顶部菜单模块样式控制
		 $(".base-header-item").click(function(){
		 	$(".base-header-item-active").removeClass("base-header-item-active");
		 	$(this).addClass("base-header-item-active");
		 });
		 $(".base-opt-btn-item").click(function(){
		 	$(".base-opt-btn-active").removeClass("base-opt-btn-active");
		 	$(this).addClass("base-opt-btn-active");
		 });
		 
		 /**详情模态框**/
 	     $(".faultDeatil_click").click(function(){
 			$(".foundaods").show();
 	     });
		 /**新增模态框开启**/
   	     $("#add").click(function(){
   	    	 	$(".foundaodadd input").val("");
   				$(".foundaodadd").show();
   	      });
   	      /**新增模态框关闭**/
		  $("#add_close").click(function () {
		      $(".foundaodadd").hide();
		  });
		  $(".guanq").click(function () {
		      $(".foundaodadd").hide();
		  });
		  $(".guanq1").click(function () {
		      $(".foundaodxg").hide();
		  });
		  $("#modify_close").click(function () {
			  $(".foundaodxg").hide();
		  });
		 
   	     /**详情模态框**/
   	     /**详情模态框的关闭**/
		 $("#closea").click(function () {
		     $(".founda").hide();
		 });
		 $("#ods-close").click(function () {
		     $(".foundaods").hide();
		 });

   	      //刷新按钮点击事件
   	      $("#refresh").click(function(){
   	         $(".base-opt-qry-sel").val("all");
   	         $(".base-opt-qry-txt").val("");
   	         refresh("",1,"");
   	      });
   	      
   	      //数量点击查询
   	      $(".base-label-right li").click(function(){
   	    	  var status = $(this).find(".spanclass").attr("id");
   	    	  refresh(dataSouceId,1,status);
   	      });
   	      
   	      //日月下拉框选择，改变要求时间type类型
	   	  $(".foundaodadd #isImport").on('change',function(){
	   	     var isDayVal = $(".foundaodadd #isImport").val();
	   	     if (isDayVal == 'M') {
	   	    	$(".foundaodadd #demainTime").attr("type","date");
			 }else if (isDayVal == 'D') {
				$(".foundaodadd #demainTime").attr("type","time");
			}
	   	  });
	   	  
	   	  $(".foundaodxg #isImport").on('change',function(){
	   	     var isDayVal = $(".foundaodxg #isImport").val();
	   	     if (isDayVal == 'M') {
	   	    	$(".foundaodxg #demainTime").attr("type","date");
			 }else if (isDayVal == 'D') {
				$(".foundaodxg #demainTime").attr("type","time");
			}
	   	  });
   	      
   	   //新增保存   
	   var isExit = false;
   	   $("#adds").click(function () {
   		   var isImport = $(".foundaodadd #isImport").val();
           var interNo = $(".foundaodadd #interNo").val();
           var dataSource = $(".foundaodadd #dataSource").val();
           var interType = $(".foundaodadd #interType").val();
           var demainTime = $(".foundaodadd #demainTime").val();
           var creator = $(".foundaodadd #creator").val();
           var interfaceId = $(".foundaodadd #interfaceId").val();
           if (interNo==null || interNo =='' || interNo ==undefined) {
        	   alert("接口不能为空！");
        	   return;
           }
           if (dataSource==null || dataSource =='' || dataSource ==undefined) {
        	   alert("数据源不能为空！");
        	   return;
           }else{
        	   if(isNaN(dataSource)) {
        		   alert("数据源必须是数字！");
            	   return;
			}
           }
           if (demainTime==null || demainTime =='' || demainTime ==undefined) {
        	   alert("要求时间不能为空！");
        	   return;
           }
           if (isImport==null || isImport =='' || isImport ==undefined) {
  				alert("日/月类型不能为空！");
  				return;
  		   }
           if (interType==null || interType =='' || interType ==undefined) {
  				alert("接口类型不能为空！");
  				return;
  		   }
           if (creator==null || creator =='' || creator ==undefined) {
  				alert("创建人不能为空！");
  				return;
  		   }
           //var dispatcId = $(".foundaodadd #dispatcId").val();
           //数据存在性校验
           checkExit(interNo,interfaceId,dataSource);
           if (!isExit) {
           $.ajax({
        		   type:"GET",
        		   url:"/configManagerController/saveInterFaceConfigInfo",
        		   dataType:"json",
        		   async : false,
        		   data:{"isImport":isImport,"interNo":interNo,"dataSource":dataSource,"interType":interType,"demainTime":demainTime,"creator":creator},
        		   success: function (data) {
        			   if (data==0) {
        				   alert("保存失败!");
        			   }else{
        				   $(".foundaodadd #interfaceId").val(data);
        				   console.log("interfaceId====="+$("#interfaceId").val());
        				   alert("保存成功");
        				   $(".foundaodadd").hide();
        			   }
        			   refresh(1);
        		   }
        	   });
           }
       });
   	   
   	   /**修改模态框 保存确定按键**/         	
       $("#modify").click(function () {
    	   var isImport = $(".foundaodxg #isImport").val();
           var interNo = $(".foundaodxg #interNo").val();
           var dataSource = $(".foundaodxg #dataSource").val();
           var interType = $(".foundaodxg #interType").val();
           var demainTime = $(".foundaodxg #demainTime").val();
           var creator = $(".foundaodxg #creator").val();
           var interfaceId = $(".foundaodxg #interfaceId").val();
           
           if (interNo==null || interNo =='' || interNo ==undefined) {
        	   alert("接口不能为空！");
        	   return;
           }
           if (dataSource==null || dataSource =='' || dataSource ==undefined) {
        	   alert("数据源不能为空！");
        	   return;
           }else{
        	   if(isNaN(dataSource)) {
        		   alert("数据源必须是数字！");
            	   return;
			}
           }
           if (demainTime==null || demainTime =='' || demainTime ==undefined) {
        	   alert("要求时间不能为空！");
        	   return;
           }
           if (isImport==null || isImport =='' || isImport ==undefined) {
  				alert("日/月类型不能为空！");
  				return;
  		   }
           if (interType==null || interType =='' || interType ==undefined) {
  				alert("接口类型不能为空！");
  				return;
  		   }
           if (creator==null || creator =='' || creator ==undefined) {
  				alert("创建人不能为空！");
  				return;
  		   }
         //页面有效性校验
         //数据存在性校验
         checkExit(interNo,interfaceId,dataSource);
         if (!isExit) {
	         $.ajax({
	         	  type:"GET",
	  	      	  url:"/configManagerController/updateInterFaceConfigInfo",
	  	      	  dataType:"json",
	  	          async : false,
	  	      	  data:{"isImport":isImport,"interNo":interNo,"dataSource":dataSource,"interType":interType,"demainTime":demainTime,"creator":creator,"interfaceId":interfaceId},
	               success: function (data) {
	             	if (!data) {
	 					alert("更新失败!");
	 				}else{
	 					alert("更新成功");
	 					$(".foundaodxg").hide();
	 				}
	             	refresh(1);
	             }
	         });
         }
      });
   	   
      //数据存在性校验 接口名称+数据源
   	   var checkExit = function(interNo,interfaceId,dataSource){
   		 $.ajax({
          	  type:"GET",
   	      	  url:"/configManagerController/checkInterFaceExit",
   	      	  dataType:"json",
   	          async : false,
   	      	  data:{"interNo":interNo,"dataSource":dataSource},
                success: function (data) {
              	if (data!=0) {
              		if (interfaceId == undefined || interfaceId == null || interfaceId == '') {
              			alert("相同的接口和数据源,请重新填写!");
              			isExit = true;
					}else if(interfaceId != undefined && interfaceId != null && interfaceId !=''){
						if (interfaceId != data) {
							alert("相同的接口和数据源,请重新填写!");
	              			isExit = true;
						}else{
							isExit = false;
						}
					}
  				}else{
  					isExit = false;
  				}
              }
          });
   	   }
   	      
   	      //查询数量
   	      var totalnum=0;//总数
   	      var count = 1;
   	      var queryOdsInterNum = function(interNo){
 	    	 $.ajax({
 	    		 type:"GET",
 	    		 url:"/configManagerController/queryInterFaceConfigList",//查询总条数
 	    		 dataType:"json",
 	    		 data:{"interNo":interNo},
 	    		 async : false,
 	    		 success : function(data) {
 	    			 if(typeof(data) != 'undefined' && data != null ){
 	    				totalnum = data.length;
 	    				if (data.length==0) {
							count=1;
						}else {
							count = data.length;
						}
 	    			 }else{
 	    				totalnum = 0;
 	    				count = 1;
 	    			 }
 	    		 }
 	    	 });
 	     }
   	    
   	      //查询框动态查询
   	      $(".base-opt-qry-txt").keyup(function(){
   	        refresh(1);
   	      });
   	      
   	      var deleteInfo = function(interfaceId){
   	    	if (confirm("确定删除配置吗?")) {
   	    	 $.ajax({
 	    		 type:"GET",
 	    		 url:"/configManagerController/deleteInterFaceInfo",//查询总条数
 	    		 dataType:"json",
 	    		 data:{"interfaceId":interfaceId},
 	    		 async : false,
 	    		 success : function(data) {
 	    			if (data) {
 	    				alert("删除成功");
 	    				refresh(1);
					}else{
						alert("删除失败");
					}
 	    		 }
 	    	 });
			}else{
				
			}
   	      }
   	      
   	     //分页查询
		 var refresh=function(page){
			 var interNo = $("#interNo").val();
			 queryOdsInterNum(interNo);
			 $.ajax({
	   	      	  type:"GET",
	   	      	  url:"/configManagerController/queryInterFaceConfigList",
	   	      	  dataType:"json",
	   	      	  data:{"page":page,"interNo":interNo},
	   	      	  success:function(data){
	   	      	    var $tr=$("<tr></tr>");
	   	      	    var $td=$("<td></td>");
	   	      	    //var totalnum=0,expNum=0,normalNum=0;
	   	      	    $("#tbody").empty();
	   	      	    if (totalnum==0) {
	   	      	    	var $trTmp=$tr.clone();
	   	      	    	$trTmp.append($td.clone().attr("colspan","10").text("暂无数据"));
	   	      	    	$("#tbody").append($trTmp);
					}
	   	      	    $.each(data,function(k,v){
	   	      	        var $trTmp=$tr.clone();
	   	      	        if(totalnum==0){
	   	      	            //totalnum=totalnum+1;
	   	      	            $trTmp.append($td.clone().attr("colspan","10").text("暂无数据"));
	   	      	        }else{
		   	      	        $trTmp.append($td.clone().text(v.interNo==undefined||v.interNo==""?"":v.interNo));
		   	      	        $trTmp.append($td.clone().text(v.dataSource==undefined||v.dataSource==""?"":v.dataSource));
		   	      	        $trTmp.append($td.clone().text(v.demainTime));
		   	      	        $trTmp.append($td.clone().text(v.isImport));
		   	      	        $trTmp.append($td.clone().text(v.interType));
		   	      	        $trTmp.append($td.clone().text(v.creator));
		   	      	        $trTmp.append($td.clone().text(v.createTime));
		   	      	       // $trTmp.append($td.clone().addClass("last-th").append("<img src='../images/icon_detials.png'>"));
	      	        		var $tdLast=$td.clone();
	      	        		$($tdLast).bind("click",function(){
	      	        			$(".foundaods").show();
	      	        			$(".interfaceId").html(v.id);
	      	        			$(".dataSource").html(v.dataSource);
	      	        			$(".interNo").html(v.interNo);
	      	        			$(".creator").html((v.creator));
	      	        			$(".createTime").html(v.createTime);
	      	        			$(".demainTime").html(v.demainTime);
	      	        			$(".isImport").html(v.isImport);
	      	        			$(".interType").html(v.interType);
	      	        			
	      	        		});
	      	        		$trTmp.append($tdLast.addClass("odsInterDeatil_click").append("<img src='../images/icon_detials.png'>"));
	      	        		
	      	        		var $tdModify=$td.clone();
	     	        		$($tdModify).bind("click",function(){
	     	        			$(".foundaodxg").show();
	     	        			$(".foundaodxg #interfaceId").val(v.id);
	     	        			$(".foundaodxg #interNo").val(v.interNo);
	     	        			$(".foundaodxg #dataSource").val(v.dataSource);
	     	        			if (v.isImport == 'M') {
	     	        				$(".foundaodxg #demainTime").attr("type","date");
								}else if (v.isImport == 'D') {
									$(".foundaodxg #demainTime").attr("type","time");
								}
	     	        			$(".foundaodxg #demainTime").val(v.demainTime);
	     	        			$(".foundaodxg #creator").val(v.creator);
	     	        			$(".foundaodxg #isImport").val(v.isImport);
	     	        			$(".foundaodxg #interType").val(v.interType);
	     	        		});
	     	        		$trTmp.append($tdModify.addClass("InterDeatil_click").append("<img src='../images/icon_revise1.png'>"));
	     	        		
	     	        		var $tdDelete=$td.clone();
	     	        		$($tdDelete).bind("click",function(){
	     	        			deleteInfo(v.id);
	     	        		});
	     	        		$trTmp.append($tdDelete.addClass("InterDeatil_click").append("<img src='../images/icon_clean01.png'>"));
	   	      	        }
	   	      	         $("#tbody").append($trTmp);
	   	      	    });
		   	      		 $("#totalnum").text(totalnum);
	   	      	    
	   	      	    //分页初始化
					 $("#pageTool").html("");
					 $("#pageTool").Paging({pagesize:8,count:count,current:page,callback:function(page,size,count){
			 		     refresh(page);
			   	      }});
			   	      
	   	      	  },
	   	      	});
		 };
		 
		 refresh(1);
	});