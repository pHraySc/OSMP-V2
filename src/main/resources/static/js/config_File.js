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
   		   var fileId = $(".foundaodadd #fileConfigId").val();
           var fileIp = $(".foundaodadd #fileIp").val();
           var filePath = $(".foundaodadd #filePath").val();
           var threshold = $(".foundaodadd #threshold").val();
           var creator = $(".foundaodadd #creator").val();
           
           //页面有效性校验
           var val = /([0-9]{1,3}\.{1}){3}[0-9]{1,3}/;//正则表达式   
           var vald = val.exec(fileIp);
           if (vald == null) {
               alert('注意IP有效性');
               return false;
           }
           if (vald != '') {
               if (vald[0] != fileIp) {
                   alert('注意IP有效性');
                   return false;
               }
           }
           if (vald==''||vald==null||vald==undefined) {
          	 	alert("IP不能为空！");
  		   }
           if (filePath==null || filePath =='' || filePath ==undefined) {
   				alert("路径不能为空！");
   				return;
   		   }
           var pattern = /^(((\d|[1-9]\d)(\.\d{1,2})?)|100|100.0|100.00)$/;
      	   if (threshold != null || threshold != '' || threshold != undefined) {
  			if (!pattern.test(threshold)) {
  				alert("请输入符合要求的数字!");
  				return;
  			}
  		   }else {
  			   	alert("阈值不能为空！");
  			    return;
  		   }
           if (creator==null || creator =='' || creator ==undefined) {
          	 	alert("创建人不能为空!");
          	 	return;
           }
           //数据存在性校验
           checkExit(fileIp,filePath,fileId);
           if (!isExit) {
           $.ajax({
        		   type:"GET",
        		   url:"/configManagerController/saveFileConfigInfo",
        		   dataType:"json",
        		   async : false,
        		   data:{"fileIp":fileIp,"filePath":filePath,"threshold":threshold,"creator":creator},
        		   success: function (data) {
        			   if (data==0) {
        				   alert("保存失败!");
        			   }else{
        				   $(".foundaodadd #fileConfigId").val(data);
        				   console.log("fileConfigId====="+$("#fileConfigId").val());
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
         var fileIp = $(".foundaodxg #fileIp").val();//IP地址
         var filePath = $(".foundaodxg #filePath").val();//路径
         var threshold = $(".foundaodxg #threshold").val();//阈值
         var creator = $(".foundaodxg #creator").val();//创建人
         var fileId = $(".foundaodxg #fileConfigId").val();//id
         var val = /([0-9]{1,3}\.{1}){3}[0-9]{1,3}/;//正则表达式   
         //页面有效性校验
         var vald = val.exec(fileIp);
         if (vald == null) {
             alert('注意IP有效性');
             return false;
         }
         if (vald != '') {
             if (vald[0] != fileIp) {
                 alert('注意IP有效性');
                 return false;
             }
         }
         if (vald==''||vald==null||vald==undefined) {
        	 alert("IP不能为空！");
		 }
         if (filePath==null || filePath =='' || filePath ==undefined) {
 			alert("路径不能为空！");
 			return;
 		 }
         var pattern = /^(((\d|[1-9]\d)(\.\d{1,2})?)|100|100.0|100.00)$/;
    	 if (threshold != null || threshold != '' || threshold != undefined) {
			if (!pattern.test(threshold)) {
				alert("请输入符合要求的数字!");
				return;
			}
		 }else {
			 alert("阈值不能为空！");
			 return;
		 }
         if (creator==null || creator =='' || creator ==undefined) {
        	 alert("创建人不能为空!")
        	 return;
         }
         //数据存在性校验
         checkExit(fileIp,filePath,fileId);
         if (!isExit) {
	         $.ajax({
	         	  type:"GET",
	  	      	  url:"/configManagerController/updateFileConfigInfo",
	  	      	  dataType:"json",
	  	      	  data:{"fileIp":fileIp,"filePath":filePath,"threshold":threshold,"creator":creator,"fileId":fileId},
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
   	   var checkExit = function(fileIp,filePath,fileId){
   		 $.ajax({
          	  type:"GET",
   	      	  url:"/configManagerController/checkFileExit",
   	      	  dataType:"json",
   	          async : false,
   	      	  data:{"fileIp":fileIp,"filePath":filePath},
                success: function (data) {
              	if (data!=0) {
              		if (fileId == undefined || fileId == null || fileId == '') {
              			alert("相同的IP和路径,请重新填写!");
              			isExit = true;
					}else if(fileId != undefined && fileId != null && fileId !=''){
						if (fileId != data) {
							alert("相同的IP和路径,请重新填写!");
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
   	      var queryOdsInterNum = function(fileIp){
 	    	 $.ajax({
 	    		 type:"GET",
 	    		 url:"/configManagerController/queryfileconfigList",//查询总条数
 	    		 dataType:"json",
 	    		 data:{"fileIp":fileIp},
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
   	      
   	      //删除
   	      var deleteInfo = function(fileId){
   	    	if (confirm("确定删除配置吗?")) {
   	    	 $.ajax({
 	    		 type:"GET",
 	    		 url:"/configManagerController/deleteFileInfo",//查询总条数
 	    		 dataType:"json",
 	    		 data:{"fileId":fileId},
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
			 var fileIp = $("#fileIp").val();
			 queryOdsInterNum(fileIp);
			 $.ajax({
	   	      	  type:"GET",
	   	      	  url:"/configManagerController/queryfileconfigList",
	   	      	  dataType:"json",
	   	      	  data:{"page":page,"fileIp":fileIp},
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
		   	      	        $trTmp.append($td.clone().text(v.fileIp==undefined||v.fileIp==""?"":v.fileIp));
		   	      	        $trTmp.append($td.clone().text(v.filePath==undefined||v.filePath==""?"":v.filePath));
		   	      	        $trTmp.append($td.clone().text(v.threshold+"G"));
		   	      	        $trTmp.append($td.clone().text(v.creator));
		   	      	        $trTmp.append($td.clone().text(v.createTime));
		   	      	       // $trTmp.append($td.clone().addClass("last-th").append("<img src='../images/icon_detials.png'>"));
	      	        		var $tdLast=$td.clone();
	      	        		$($tdLast).bind("click",function(){
	      	        			$(".foundaods").show();
	      	        			$(".fileIp").html(v.fileIp);
	      	        			$(".filePath").html(v.filePath);
	      	        			$(".threshold").html(v.threshold+"G");
	      	        			$(".creator").html((v.creator));
	      	        			$(".createTime").html(v.createTime);
	      	        		});
	      	        		$trTmp.append($tdLast.addClass("odsInterDeatil_click").append("<img src='../images/icon_detials.png'>"));
	      	        		
	      	        		var $tdModify=$td.clone();
	     	        		$($tdModify).bind("click",function(){
	     	        			$(".foundaodxg").show();
	     	        			$(".foundaodxg #fileConfigId").val(v.id);
	     	        			$(".foundaodxg #fileIp").val(v.fileIp);
	     	        			$(".foundaodxg #filePath").val(v.filePath);
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