	$(document).ready(function(){
		 //初始化网页导航模块
		  Header = new Header();
		  Header.init();
		  Header.show("baseMonitor");
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
		 
		 var dataSouceId = "";//初始值为0
		 //按钮组点击事件
   	     /*$(".base-opt-btn-item").click(function(){
   	      });*/
   	     //数据源点击事件
   	     $(".base-opt-btn").on("click",".base-opt-btn-item",function(){
			 dataSouceId=$("input",this).attr("data-id");
			 $(".base-opt-btn-active").removeClass("base-opt-btn-active");
			 $(this).addClass("base-opt-btn-active");
			 $("#interface").val("");
			 refresh(dataSouceId,1,"");
		 });
   	     
   	     var DetailInfo = function(id){
   	    	 $.ajax({
   	    		 type:"GET",
   	    		 url:"/tableSapceController/queryDetailInfo",//查询总条数
   	    		 dataType:"json",
   	    		 data:{"id":id},
   	    		 async : false,
   	    		 success : function(data) {
   	    			 totalnum = data.length;//获取总长度
   	    		 }
   	    	 });
   	       }
   	       
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
   	         $("#interface").val("");
   	         refresh(dataSouceId,1,"");
   	      });
   	      
   	    
   	      //查询框动态查询
   	      $(".base-opt-qry-txt").keyup(function(){
   	        refresh(dataSouceId,1,"");
   	      });
   	      
   	      //数量点击查询
   	      $(".base-label-right li").click(function(){
   	    	  var status = $(this).find(".spanclass").attr("id");
   	    	  refresh(dataSouceId,1,status);
   	      });
         var tableSpaceName = "";
   	     var totalnum=0;//全部条数
   	     var expNum=0;//异常条数
   	     var normalNum=0;//正常条数
   	     var count=0;//分页总条数
   	     //查询数量
   	     var queryOdsInterNum = function(tableSpaceName,dataSouceId,status){
   	    	 tableSpaceName = $("#interface").val();
   	    	 $.ajax({
   	    		 type:"GET",
   	    		 url:"/tableSapceController/queryOdsInterNum",//查询总条数
   	    		 dataType:"json",
   	    		 data:{"tableSpaceName":tableSpaceName,"dataSouceId":dataSouceId},
   	    		 async : false,
   	    		 success : function(data, textStatus) {
   	    			if(typeof(data) != 'undefined' && data != null ){
   	    				    expNum = data.expNum;
   	    				    normalNum = data.normalNum;
   	    				    totalnum = data.totalnum;
   	    				    if (status=='success') {
								count=data.normalNum;
							}else if (status=='exception') {
								count=data.expNum;
							}else{
								count=data.totalnum;
							}
   	    				 if (count == 0) {
								count=count+1;
							}
			 }else{
				    expNum=0;//异常条数
				    normalNum=0;//正常条数
				    totalnum=0;//全部条数
				    count = 1;
			 }
   	    		 }
   	    	 });
   	     }
   	     
   	     //详情页面
   	     var tableInfo = function(tableSpaceName,dataSouceId){
   	    	$.ajax({
   	    		  type:"GET",
	   	      	  url:"/tableSapceController/queryInfoList",//查询list
	   	      	  dataType:"json",
	   	      	  data:{"tableSpaceName":tableSpaceName,"dataSouceId":dataSouceId},
	   	      	  success:function(data){
	   	      	  var $tr=$("<tr style='height:22px;padding: 8px;'></tr>");
	   	      	    var $td=$("<td style='text-align: center;width:20%'></td>");
	   	      	    $("#tbody1").empty();
	   	      	    $.each(data,function(k,v){
	   	      	    	var $trTmp=$tr.clone();
	   	      	    	$trTmp.append($td.clone().append(Header.setOdsStatus(v.status,v.timeOut)));
	   	      	    	$trTmp.append($td.clone().text(v.nodeId));
	   	      	    	$trTmp.append($td.clone().text(v.totalSpace));
 	        		    $trTmp.append($td.clone().text(v.actualPercent));
 	        		    $trTmp.append($td.clone().text(v.remainSpace+"G"));
 	        		    $("#tbody1").append($trTmp);
	   	      	    });
	   	      	  }
   	    	});
   	     }
   	     
   	     //列表分页
		 var refresh=function(dataSouceId,page,status){
			 tableSpaceName = $("#interface").val();
			 queryOdsInterNum(tableSpaceName,dataSouceId,status);//查询总条数
			 $.ajax({
	   	      	  type:"GET",
	   	      	  url:"/tableSapceController/queryList",//查询list
	   	      	  dataType:"json",
	   	      	  data:{"page":page,"tableSpaceName":tableSpaceName,"dataSouceId":dataSouceId,"status":status},
	   	      	  success:function(data){
	   	      	    var $tr=$("<tr></tr>");
	   	      	    var $td=$("<td></td>");
	   	      	    $("#tbody").empty();
	   	      	    if(data.length==0){
	   	      	        var $trTmp=$tr.clone();
	      	            $trTmp.append($td.clone().attr("colspan","10").text("暂无数据"));
	      	        }else{
	      	        	
	      	        	$.each(data,function(k,v){
	      	        		var $trTmp=$tr.clone();
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
	      	        		$trTmp.append($td.clone().text(v.tableSpaceName==undefined||v.tableSpaceName==""?"":v.tableSpaceName));
	      	        		$trTmp.append($td.clone().text(v.actualPercent));
	      	        		$trTmp.append($td.clone().text(v.remainSpace+"G"));
	      	        		$trTmp.append($td.clone().text(v.threshold+"G"));
	      	        		$trTmp.append($td.clone().text(v.updateTime));
	      	        		var $tdLast=$td.clone();
	      	        		$($tdLast).bind("click",function(){
	      	        			tableInfo(v.tableSpaceName,dataSouceId);
	      	        			$(".foundaods").show();
	      	        			
	      	        			$(".interStatus").html(Header.setOdsStatus(v.status,v.timeOut));
	      	        			if(v.status==1){
	      	        				$(".result").html("异常");
	      	        			}else if(v.status==0){
	      	        				$(".result").html("正常");
	      	        			}
	      	        			$(".tableSpaceName").html(v.tableSpaceName);
	      	        		});
	      	        		$trTmp.append($tdLast.addClass("odsInterDeatil_click").append("<img src='../images/icon_detials.png'>"));
	      	        		//}
	      	        		$("#tbody").append($trTmp);
	      	        	});
	      	        }
		   	      		 $("#totalnum").text(totalnum);
		   	      		 $("#success").text(normalNum);
		   	      		 $("#exception").text(expNum);
	   	      	    
	   	      	    //分页初始化
					 $("#pageTool").html("");
					 $("#pageTool").Paging({pagesize:8,count:count,current:page,callback:function(page,size,count){
			 		     refresh(dataSouceId,page,status);
			   	      }});
			   	      
	   	      	  },
	   	      	});
		 };
		 
		 //查询数据源
		 var queryDataSource = function(moduleId) {
			 $.ajax({
				 type:"GET",
	   	      	  url:"/commonUtilController/queryTypeByModuleId",
	   	      	  dataType:"json",
	   	      	  data:{"moduleId":moduleId},
		   	      success:function(data){
		   	    	   var $baseButton = $("#baseoptbtn");
		   	    	   if (data != null && data.length !=0) {
		   	    		 $.each(data,function(k,v){
		   	    			 if (k==0) {
		   	    				 var $Label = $("<label class="+'"btn base-opt-btn-item base-opt-btn-active"'+"></label>");
		   	    				 var $Input = $("<input type="+'"radio"'+" data-id="+'"'+v.type_id+'"'+">"+v.type_name+"</input>");
		   	    				 $Label.append($Input);
		   	    				 $baseButton.append($Label);
		   	    				 dataSouceId=v.type_id;
							}else{
								 var $Label = $("<label class="+'"btn base-opt-btn-item"'+"></label>");
		   	    				 var $Input = $("<input type="+'"radio"'+" data-id="+'"'+v.type_id+'"'+">"+v.type_name+"</input>");
		   	    				 $Label.append($Input);
		   	    				 $baseButton.append($Label);
							}
		   	    		 });
					}else{
						alert("请配置数据库信息!");
					}
		   	    	   refresh(dataSouceId,1,chartStatus);//页面初始化加载列表数据
		   	       }
			 });
		 }
		 queryDataSource(2);//页面初始化加载数据源
		 
		 
		 
		 
	});
