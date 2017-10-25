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
   	      
   	   //新增保存   
	   var isExit = false;
   	   $("#adds").click(function () {
   		   var tableSpaceId = $(".foundaodadd #tableConfigId").val();
           var tableSpaceName = $(".foundaodadd #tableSpaceName").val();
           var dataSource = $(".foundaodadd #dataSource").val();
           var threshold = $(".foundaodadd #threshold").val();
           var creator = $(".foundaodadd #creator").val();
           
           //页面有效性校验
           if (tableSpaceName==null || tableSpaceName =='' || tableSpaceName ==undefined) {
   				alert("表空间名称不能为空！");
   				return;
   		   }
           if (dataSource==null || dataSource =='' || dataSource ==undefined) {
  				alert("表空间数据源不能为空！");
  				return;
  		   }else {
  			  if(isNaN(dataSource)) {
      		   alert("数据源必须是数字！");
          	   return;
			}
  		   }
           var pattern = /^(((\d|[1-9]\d)(\.\d{1,2})?)|100|100.0|100.00)$/;
           if (threshold==null || threshold =='' || threshold ==undefined) {
        	 	alert("阈值不能为空!");
        	 	return;
           }else{
        	   if (!pattern.test(threshold)) {
     				alert("请输入符合要求的数字!");
     				return;
     			}
           }
           if (creator==null || creator =='' || creator ==undefined) {
        	   alert("创建人不能为空!");
        	   return;
           }
           //数据存在性校验
           checkExit(tableSpaceName,dataSource,tableSpaceId);
           if (!isExit) {
           $.ajax({
        		   type:"GET",
        		   url:"/configManagerController/saveTableSpaceConfigInfo",
        		   dataType:"json",
        		   async : false,
        		   data:{"tableSpaceName":tableSpaceName,"dataSource":dataSource,"threshold":threshold,"creator":creator},
        		   success: function (data) {
        			   if (data==0) {
        				   alert("保存失败!");
        			   }else{
        				   $(".foundaodadd #tableConfigId").val(data);
        				   console.log("tableConfigId====="+$("#tableConfigId").val());
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
         var tableSpaceId = $(".foundaodxg #tableConfigId").val();
         var tableSpaceName = $(".foundaodxg #tableSpaceName").val();
         var dataSource = $(".foundaodxg #dataSource").val();
         var threshold = $(".foundaodxg #threshold").val();
         var creator = $(".foundaodxg #creator").val();
         //页面有效性校验
       //页面有效性校验
         if (tableSpaceName==null || tableSpaceName =='' || tableSpaceName ==undefined) {
 				alert("表空间名称不能为空！");
 				return;
 		   }
         if (dataSource==null || dataSource =='' || dataSource ==undefined) {
				alert("表空间数据源不能为空！");
				return;
		   }
         if (threshold==null || threshold =='' || threshold ==undefined) {
     	 	alert("阈值不能为空!");
     	 	return;
         }
         if (creator==null || creator =='' || creator ==undefined) {
        	 alert("创建人不能为空!");
        	 return;
         }
         //数据存在性校验
         checkExit(tableSpaceName,dataSource,tableSpaceId);
         if (!isExit) {
	         $.ajax({
	         	  type:"GET",
	  	      	  url:"/configManagerController/updateTableSpaceConfigInfo",
	  	      	  dataType:"json",
	  	      	  data:{"tableSpaceName":tableSpaceName,"dataSource":dataSource,"threshold":threshold,"creator":creator,"tableSpaceId":tableSpaceId},
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
   	   
     //数据存在性校验 ip+path
   	   var checkExit = function(tableSpaceName,dataSource,tableSpaceId){
   		 $.ajax({
          	  type:"GET",
   	      	  url:"/configManagerController/checkTableSpaceExit",
   	      	  dataType:"json",
   	          async : false,
   	      	  data:{"tableSpaceName":tableSpaceName,"dataSource":dataSource},
                success: function (data) {
              	if (data!=0) {
              		if (tableSpaceId == undefined || tableSpaceId == null || tableSpaceId == '') {
              			alert("相同的表空间名和数据源,请重新填写!");
              			isExit = true;
					}else if(tableSpaceId != undefined && tableSpaceId != null && tableSpaceId !=''){
						if (tableSpaceId != data) {
							alert("相同的表空间名和数据源,请重新填写!");
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
   	      var queryOdsInterNum = function(tableSpaceName){
 	    	 $.ajax({
 	    		 type:"GET",
 	    		 url:"/configManagerController/queryTableSpaceConfigList",//查询总条数
 	    		 dataType:"json",
 	    		 data:{"tableSpaceName":tableSpaceName},
 	    		 async : false,
 	    		 success : function(data) {
 	    			 if(typeof(data) != 'undefined' && data != null ){
 	    				totalnum = data.length;
 	    			 }else{
 	    				totalnum = 0;
 	    			 }
 	    		 }
 	    	 });
 	     }
   	    
   	      //查询框动态查询
   	      $(".base-opt-qry-txt").keyup(function(){
   	        refresh(1);
   	      });
   	      
   	      var deleteInfo = function(tableSpaceId){
   	    	if (confirm("确定删除配置吗?")) {
   	    	 $.ajax({
 	    		 type:"GET",
 	    		 url:"/configManagerController/deleteTableSpaceInfo",//查询总条数
 	    		 dataType:"json",
 	    		 data:{"tableSpaceId":tableSpaceId},
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
			 var tableSpaceName = $("#tableSpaceName").val();
			 queryOdsInterNum(tableSpaceName);
			 $.ajax({
	   	      	  type:"GET",
	   	      	  url:"/configManagerController/queryTableSpaceConfigList",
	   	      	  dataType:"json",
	   	      	  data:{"page":page,"tableSpaceName":tableSpaceName},
	   	      	  success:function(data){
	   	      	    var $tr=$("<tr></tr>");
	   	      	    var $td=$("<td></td>");
	   	      	    //var totalnum=0,expNum=0,normalNum=0;
	   	      	    $("#tbody").empty();
	   	      	    $.each(data,function(k,v){
	   	      	        var $trTmp=$tr.clone();
	   	      	        if(totalnum==0){
	   	      	            //totalnum=totalnum+1;
	   	      	            $trTmp.append($td.clone().attr("colspan","10").text("暂无数据"));
	   	      	        }else{
		   	      	        $trTmp.append($td.clone().text(v.tableSpaceName==undefined||v.tableSpaceName==""?"":v.tableSpaceName));
		   	      	        $trTmp.append($td.clone().text(v.dataSource==undefined||v.dataSource==""?"":v.dataSource));
		   	      	        $trTmp.append($td.clone().text(v.threshold+"G"));
		   	      	        $trTmp.append($td.clone().text(v.creator));
		   	      	        $trTmp.append($td.clone().text(v.createTime));
		   	      	       // $trTmp.append($td.clone().addClass("last-th").append("<img src='../images/icon_detials.png'>"));
	      	        		var $tdLast=$td.clone();
	      	        		$($tdLast).bind("click",function(){
	      	        			$(".foundaods").show();
	      	        			$(".tableSpaceName").html(v.tableSpaceName);
	      	        			$(".dataSource").html(v.dataSource);
	      	        			$(".threshold").html(v.threshold+"G");
	      	        			$(".creator").html((v.creator));
	      	        			$(".createTime").html(v.createTime);
	      	        		});
	      	        		$trTmp.append($tdLast.addClass("odsInterDeatil_click").append("<img src='../images/icon_detials.png'>"));
	      	        		
	      	        		var $tdModify=$td.clone();
	     	        		$($tdModify).bind("click",function(){
	     	        			$(".foundaodxg").show();
	     	        			$(".foundaodxg #tableConfigId").val(v.id);
	     	        			$(".foundaodxg #tableSpaceName").val(v.tableSpaceName);
	     	        			$(".foundaodxg #dataSource").val(v.dataSource);
	     	        			$(".foundaodxg #threshold").val(v.threshold);
	     	        			$(".foundaodxg #creator").val(v.creator);
	     	        			$(".foundaodxg #createTime").val(v.createTime);
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
					 $("#pageTool").Paging({pagesize:8,count:totalnum,current:page,callback:function(page,size,count){
			 		     refresh(page);
			   	      }});
			   	      
	   	      	  },
	   	      	});
		 };
		 
		 refresh(1);
	});