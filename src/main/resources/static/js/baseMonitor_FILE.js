	$(document).ready(function(){
		 //初始化网页导航模块
		  Header = new Header();
		  Header.init();
		  Header.show("baseMonitor");
		  
		  var getParm = function GetRequest() {  
			   var url = location.search; //获取url中"?"符后的字串  
			   var theRequest = new Object(); 
			   var chartStatus;
			   if (url.indexOf("?") != -1) {  
			      var str = url.substr(1);  
			      strs = str.split("&");  
			      for(var i = 0; i < strs.length; i ++) {  
			         theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);  
			      }  
			   }  
			   return theRequest.chartStatus;  
		 }   
		  
		 var chartStatus = getParm();
		 
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
		 
		 var dataSouceId = "";
   	      //按钮组点击事件
   	      $(".base-opt-btn-item").click(function(){
   	      	var spaceId=$("input",this).attr("data-id");
   	      	if (spaceId=='134') {
	      		dataSouceId="1";
	        	refresh("1",1,"");
			}else if(spaceId=='fdb'){
				dataSouceId="2";
				refresh("2",1,"");
			}else if(spaceId=='vdb'){
				dataSouceId="3";
				refresh("3",1,"");
			}else if(spaceId=='ddb'){
				dataSouceId="";
				refresh("",1,"");
			}
   	      });
   	      	/**详情模态框**/
   	      /* $(".odsInterDeatil_click").click(function(){
   				$(".foundaods").show();
   	      });*/
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
   	      
   	      //查询数量
   	      var expNum=0;//异常条数
   	      var normalNum=0;//正常条数
   	      var totalnum=0;//总数量
   	      var unknow=0;//未知数量
   	      var queryOdsInterNum = function(fileSystemName,dataSouceId,status){
   	    	 fileSystemName = $("#fileSystemName").val();
 	    	 $.ajax({
 	    		 type:"GET",
 	    		 url:"/fileSystemController/queryFileSystemNum",//查询总条数
 	    		 dataType:"json",
 	    		 data:{"fileSystemName":fileSystemName,"dataSouceId":dataSouceId},
 	    		 async : false,
 	    		 success : function(data, textStatus) {
 	    			 if(typeof(data) != 'undefined' && data != null ){
 	    							normalNum = data.normalNum;
									expNum = data.expNum;
									totalnum =  data.totalnum;
									unknow = data.unknow;
									if (status=='normalNum') {
										count=data.normalNum;
									}else if (status=='expNum') {
										count=data.expNum;
									}else if (status=='unknow') {
										count=data.unknow;
									}else{
										count=data.totalnum;
									}
									if (count == 0) {
										count=count+1;
									}
 	    			 }else{
 	    				normalNum = 0;
						expNum = 0;
						totalnum = 0;
						unknow = 0;
						count = 1;
 	    			 }
 	    		 }
 	    	 });
 	     }
   	    
   	      //查询框动态查询
   	      $(".base-opt-qry-txt").keyup(function(){
   	        refresh(dataSouceId,1,"");
   	      });
   	      
   	     //分页查询
		 var refresh=function(dataSouceId,page,status){
			 fileSystemName = $("#fileSystemName").val();
			 queryOdsInterNum(fileSystemName,dataSouceId,status);
			 $.ajax({
	   	      	  type:"GET",
	   	      	  url:"/fileSystemController/queryList",
	   	      	  dataType:"json",
	   	      	  data:{"page":page,"dataSouceId":dataSouceId,"fileSystemName":fileSystemName,"status":status},
	   	      	  success:function(data){
	   	      	    var $tr=$("<tr></tr>");
	   	      	    var $td=$("<td></td>");
	   	      	    //var totalnum=0,expNum=0,normalNum=0;
	   	      	    $("#tbody").empty();
	   	      	    $.each(data,function(k,v){
	   	      	        if(k==0){
	   	      	          //expNum=v.expNum;
	   	      	          //normalNum=v.normalNum;
	   	      	        }
	   	      	        var $trTmp=$tr.clone();
	   	      	        if(totalnum==0){
	   	      	            //totalnum=totalnum+1;
	   	      	            $trTmp.append($td.clone().attr("colspan","10").text("暂无数据"));
	   	      	        }else{
		   	      	        $trTmp.append($td.clone().append(Header.setOdsStatus(v.status,v.timeOut)));
		   	      	        /**预警原因**/
		   	      	        var msg="";
						     if(v.status == "1" || v.status=="2"){
						     }else if(v.status == "-2"){
						     	if(v.errorMsg==""||v.errorMsg=="文件未到"){
						     	}else{
						     		msg=$("<div class= 'warning-reason' style='background-color:#fb7f54;' title='"+v.errorMsg+"'>"+ v.errorMsg +"</div>");
						     	}
						     }else{
						    	if(v.errorMsg==""||v.errorMsg=="文件未到"){
						     	}else{
						     		msg=$("<div class= 'warning-reason' style='background-color:#ffb430;' title='"+v.errorMsg+"'>"+ v.errorMsg +"</div>");
						     	}
						     }
						    //$trTmp.append($td.clone().append(msg));
		   	      	        $trTmp.append($td.clone().text(v.ip==undefined||v.ip==""?"":v.ip));
		   	      	        $trTmp.append($td.clone().text(v.filePath==undefined||v.filePath==""?"":v.filePath));
		   	      	        $trTmp.append($td.clone().text(v.status==98?"-":v.remainPercent+"%"));
		   	      	        $trTmp.append($td.clone().text(v.status==98?"-":v.remainSpace));
		   	      	        $trTmp.append($td.clone().text(v.threshold+"%"));
		   	      	        $trTmp.append($td.clone().text(v.updateTime));
		   	      	       // $trTmp.append($td.clone().addClass("last-th").append("<img src='../images/icon_detials.png'>"));
	      	        		var $tdLast=$td.clone();
	      	        		$($tdLast).bind("click",function(){
	      	        			$(".foundaods").show();
	      	        			$(".fileStatus").html(Header.setOdsStatus(v.status,v.timeOut));
	      	        			if(v.status==0){
	      	        				$(".result").html("正常");
	      	        			}else if(v.status==1){
	      	        				$(".result").html("警告");
	      	        			}else if(v.status==98){
	      	        				$(".result").html("未知");
	      	        			}
	      	        			$(".fileIP").html(v.ip);
	      	        			$(".filePath").html(v.filePath);
	      	        			$(".remainPercent").html(v.status==98?"-":v.remainPercent+"%");
	      	        			$(".remainSpace").html((v.status==98?"-":v.remainSpace));
	      	        			$(".threshold").html(v.threshold+"%");
	      	        		});
	      	        		$trTmp.append($tdLast.addClass("odsInterDeatil_click").append("<img src='../images/icon_detials.png'>"));
	   	      	        }
	   	      	         $("#tbody").append($trTmp);
	   	      	    });
		   	      	 //if (status == null || chartStatus != null ) {
		   	      		 $("#totalnum").text(totalnum);
		   	      		 $("#expNum").text(expNum);
		   	      		 $("#normalNum").text(normalNum);
		   	      		 $("#unknow").text(unknow);
		   	      	// }
	   	      	    
	   	      	    //分页初始化
					 $("#pageTool").html("");
					 $("#pageTool").Paging({pagesize:8,count:count,current:page,callback:function(page,size,count){
			 		     refresh(dataSouceId,page,status);
			   	      }});
			   	      
	   	      	  },
	   	      	});
		 };
		 
		 refresh("",1,chartStatus);
	});