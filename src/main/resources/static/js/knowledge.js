$(document).ready(function(){
		 //初始化网页导航模块
		  Header = new Header();
		  Header.init();
		  Header.show("knowledge");
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
   	    	    $("#msgDiv").empty();
   	    	    attIds=[];
   				$(".foundaodadd").show();
   				$("#uploadFileDiv").hide();
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
		    
	  var attIds=[];//附件id集合  
	  /**新增模态框 保存确定按键**/         	
   	  $("#adds").click(function () {
        var faultname = $(".foundaodadd #faultname").val();
        var typeName = $(".foundaodadd #typeName").val();
        var dealmethod = $(".foundaodadd #dealmethod").val();
        var faultcontent = $(".foundaodadd #faultcontent").val();
        var addusername = $(".foundaodadd #addusername").val();
        var faultId = $(".foundaodadd #faultId").val();
        //页面校验
        if (faultname==null || faultname =='' || faultname ==undefined) {
			alert("故障名称不能为空！");
			return;
		}
        if (typeName==null || typeName =='' || typeName ==undefined) {
			alert("故障分类不能为空！");
			return;
		}
        if (dealmethod==null || dealmethod =='' || dealmethod ==undefined) {
			alert("故障处理方式不能为空！");
			return;
		}
        if (faultcontent==null || faultcontent =='' || faultcontent ==undefined) {
			alert("故障内容不能为空！");
			return;
		}
        if (addusername==null || addusername =='' || addusername ==undefined) {
			alert("新增人不能为空！");
			return;
		}
        //var totalnum=0;
        var attIdString = attIds.join(",");
        //var attIdString = attIds.substring(0,attIds.length-1);
        $.ajax({
        	  type:"GET",
 	      	  url:"/knowledgeController/saveFaultInfo",
 	      	  dataType:"json",
 	      	  data:{"faultname":faultname,"typeName":typeName,"dealmethod":dealmethod,"faultcontent":faultcontent,"addusername":addusername,"attIds":attIdString,"faultId":faultId},
              success: function (data) {
            	if (data==null||data==0) {
					alert("保存失败!");
				}else{
					$(".foundaodadd #faultId").val(data);
					console.log("faultId====="+$("#faultId").val());
					$("#uploadFileDiv").show();
					//alert("保存成功");
					if (confirm("保存成功!是否上传附件?")) {
						
					}else {
						$(".foundaodadd").hide();
						refresh("",1);
					};
				}
            	/*if (attIds.length != 0) {
            		$(".foundaodadd").hide();
            		refresh("",1);
				}else{
					refresh("",1);
				}*/
            	
            }
        });
    });
   	  
   	  
		/**修改模态框 保存确定按键**/         	
   	  $("#modify").click(function () {
        var faultname = $(".foundaodxg #faultName").val();
        var typeName = $(".foundaodxg #typeName").val();
        var dealmethod = $(".foundaodxg #dealMethod").val();
        var faultcontent = $(".foundaodxg #faultContent").val();
        var addusername = $(".foundaodxg #addusername").val();
        var faultId = $(".foundaodxg #faultId").val();
        var attIdString = attIds.join(",");
        //页面校验
        if (faultname==null || faultname =='' || faultname ==undefined) {
			alert("故障名称不能为空！");
			return;
		}
        if (typeName==null || typeName =='' || typeName ==undefined) {
			alert("故障分类不能为空！");
			return;
		}
        if (dealmethod==null || dealmethod =='' || dealmethod ==undefined) {
			alert("故障处理方式不能为空！");
			return;
		}
        if (faultcontent==null || faultcontent =='' || faultcontent ==undefined) {
			alert("故障内容不能为空！");
			return;
		}
        if (addusername==null || addusername =='' || addusername ==undefined) {
			alert("新增人不能为空！");
			return;
		}
        
        $.ajax({
        	  type:"GET",
 	      	  url:"/knowledgeController/updateFaultInfo",
 	      	  dataType:"json",
 	      	  data:{"faultname":faultname,"typeName":typeName,"dealmethod":dealmethod,"addusername":addusername,"faultcontent":faultcontent,"faultId":faultId,"attIds":attIdString},
              success: function (data) {
            	if (!data) {
					alert("更新失败!");
				}else{
					alert("更新成功");
				}
            	$(".foundaodxg").hide();
            	refresh("",1);
            }
        });
    });
   	      
  	      
   	      
   	      /**详情模态框**/
   	      $(".odsInterDeatil_click").click(function(){
   				$(".foundaods").show();
   	      });
   	      /**详情模态框的关闭**/
		  $("#closea").click(function () {
		        $(".founda").hide();
		  });
		  $("#ods-close").click(function () {
		        $(".foundaods").hide();
		  });
		  /**修改模态框**/
	   	  $(".InterDeatil_click").click(function(){
	   		    $(".foundaodxg input").val("");
	   		    $("#msgDiv1").empty();
	    	    attIds=[];
	   	    	$(".foundaodxg").show();
	   	  });
	   	  /**修改模态框的关闭**/
		  $("#closeaxg").click(function () {
			    $(".foundaodxg").hide();
	      });
		  $("#ods-closexg").click(function () {
			    $(".foundaodxg").hide();
		  });
		    
		    
		    

   	      //刷新按钮点击事件
   	      $("#refresh").click(function(){
   	         $(".base-opt-qry-sel").val("all");
   	         $(".base-opt-qry-txt").val("");
   	         refresh("",1);
   	      });
   	      
   	    
   	      //查询框动态查询
   	      $(".base-opt-qry-txt").keyup(function(){
   	        refresh($(this).val(),1);
   	      });
   	      
	   	  //查询总数
   	      var queryKnowNum = function(tableSpaceName,dataSouceId,status){
	 	    	 $.ajax({
	 	    		 type:"GET",
	 	    		 url:"/knowledgeController/queryKnowNum",//查询总条数
	 	    		 dataType:"json",
	 	    		 data:{},
	 	    		 async : false,
	 	    		 success : function(data, textStatus) {
	 	    			 totalnum = data.length;//获取总长度
	 	    		 }
	 	    	 });
	 	     }
   	   
		 var refresh=function(qryStr,page){
			 var faultName = $("#faultName").val();
			 queryKnowNum(faultName);
			 $.ajax({
	   	      	  type:"GET",
	   	      	  url:"/knowledgeController/queryList",
	   	      	  dataType:"json",
	   	      	  data:{"page":page,"faultFlowName":qryStr},
	   	      	  success:function(data){
	   	      	    var $tr=$("<tr></tr>");
	   	      	    var $td=$("<td style='overflow:hidden;white-space:nowrap;text-overflow:ellipsis;'></td>");
	   	      	    $("#tbody").empty();
	   	      	    $.each(data,function(k,v){
	   	      	        //totalnum = totalnum + 1;
	   	      	        //count = count + 1;
	   	      	        var $trTmp=$tr.clone();
	   	      	        if(totalnum==0){
	   	      	           //$trTmp.append($td.clone().attr("colspan","10").text("暂无数据"));
	   	      	        }else{
		   	      	        $trTmp.append($td.clone().append(v.faultName));
		   	      	        $trTmp.append($td.clone().text(v.typeName));
						    $trTmp.append($td.clone().append(v.faultContent).attr("title",v.faultContent));
						    $trTmp.append($td.clone().append(v.dealMethod).attr("title",v.dealMethod));
						    $trTmp.append($td.clone().append(v.lastDealDate));
						    var $tdDetail=$td.clone();
	     	        		$($tdDetail).bind("click",function(){
	     	        			$(".foundaods").show();
	     	        			$("#acctach").empty();
	     	        			findAttachList(v.id);
	     	        			$(".faultName").html(v.faultName);
	     	        			$(".typeName").html(v.typeName);
	     	        			$(".faultContent").html(v.faultContent);
	     	        			$(".dealMethod").html(v.dealMethod);
	     	        			$(".dealDate").html(v.lastDealDate);
	     	        		});
	     	        		$trTmp.append($tdDetail.addClass("faultDeatil_click").append("<img src='../images/icon_detials.png'>"));
	     	        		
	     	        		var $tdModify=$td.clone();
	     	        		$($tdModify).bind("click",function(){
	     	        			$(".foundaodxg").show();
	     	        			$("#msgDiv1").empty();
	     	        			attIds=[];
	     	        			findAttList(v.id);
	     	        			$(".foundaodxg #faultId").val(v.id);
	     	        			$(".foundaodxg #faultName").val(v.faultName);
	     	        			$(".foundaodxg #typeName").val(v.typeName);
	     	        			$(".foundaodxg #faultContent").val(v.faultContent);
	     	        			$(".foundaodxg #dealMethod").val(v.dealMethod);
	     	        			$(".foundaodxg #addusername").val(v.dealPersons);
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
	   	      	     if (totalnum == 0) {
	   	      	        var $trTmp=$tr.clone();
	   	      	        totalnum=totalnum+1;
	      	            $trTmp.append($td.clone().attr("colspan","10").text("暂无数据"));
	      	            $("#tbody").append($trTmp);
	      	            $("#totalnum").text(totalnum-1);
					}else {
						$("#totalnum").text(totalnum);
					}
	   	      	    
	   	      	    //分页初始化
					 $("#pageTool").html("");
					 $("#pageTool").Paging({pagesize:8,count:totalnum,current:page,callback:function(page,size,count){
			 		     refresh("",page);
			   	      }});
			   	      
	   	      	  },
	   	      	  
	   	      	});
		 };
		 
		 //修改查询文件列表
		 var findAttList = function(faultId) {
			 $.ajax({
				  type:"GET",
	   	      	  url:"/knowledgeController/queryAttachList",
	   	      	  dataType:"json",
	   	      	  data:{"faultId":faultId},
		   	      success:function(data){
		   	    	 $.each(data,function(k,v){
		   	    		 attIds.push(v.id);
		   	    		 msg = "<div id="+'"attId'+v.id+'"'+"><br>" +v.attach_name +"<input class="+'"boxa"'+" type="+'"button"'+" onclick="+'"deleteAtt('+v.id+','+attIds+')"'+" value="+'"删除"'+"/>"+"</br></div> ";
		   	    		 $("#msgDiv1").append(msg);
		   	    	 });
		   	       }
			 });
		};
		
	    //详情查询文件列表
		var findAttachList = function(faultId) {
			 $.ajax({
				  type:"GET",
	   	      	  url:"/knowledgeController/queryAttachList",
	   	      	  dataType:"json",
	   	      	  data:{"faultId":faultId},
		   	      success:function(data){
		   	    	 $.each(data,function(k,v){
		   	    		 var $trHtml = "<tr><td style=\"text-align:left;\">"+v.attach_name+"<a href=\"javascript:void(0)\" onclick=\"loadfile('"+v.attach_path+"','"+v.attach_name+"')\" style=\"float:right;margin-left:50px;\">下载</ a></td></tr>";
		   	    		 var $acctachDiv = $("#acctach");
		   	    		 $acctachDiv.append($trHtml);
		   	    	 });
		   	    	 if (data.length >0) {
		   	    		 var length = data.length *32+"px";
		   	    		 $("#divClass").css("height",length);
		   	    		 $("#acctachDiv").css("height",length);
		   	    		 $("#acctach").css("height",length);
					}
		   	       }
			 });
		};
		
		//删除
		var deleteInfo = function(faultId){
   	    	if (confirm("确定删除配置吗?")) {
   	    	 $.ajax({
 	    		 type:"GET",
 	    		 url:"/knowledgeController/deleteFaultInfo",//查询总条数
 	    		 dataType:"json",
 	    		 data:{"faultId":faultId},
 	    		 async : false,
 	    		 success : function(data) {
 	    			if (data) {
 	    				alert("删除成功");
 	    				refresh("",1);
					}else{
						alert("删除失败");
					}
 	    		 }
 	    	 });
			}else{
				
			}
   	      }
		
		 //新增上传文件
		$('#uploadFile').fileupload({
			url:"/fileUploadContoller/upload",
			dataType: 'json',
			progressall: function (e, data) {
		        var progress = parseInt(data.loaded / data.total * 100, 10);
		        $('#progress .bar').css(
		            'width',
		            progress + '%'
		        );
		    },
			done: function(e, data) {
				var attId = data.result;
				var msg="";
				if (attId > 0) {
					alert("附件上传成功！")
					$('#progress .bar').css(
				            'width',
				            0 + '%'
				        );
					attIds.push(attId);
					addbindInfo();//绑定信息
					msg = "<div id="+'"attId'+attId+'"'+"><br>" +data.files[0].name +"<input class="+'"boxa"'+" type="+'"button"'+" onclick="+'"deleteAtt('+attId+','+attIds+')"'+" value="+'"删除"'+"/>"+"</br></div> ";
					
					$("#msgDiv").append(msg);
				}else{
					alert("附件上传失败！");
				}
			}
		});
		
		 //更新上传文件
		$('#uploadFile1').fileupload({
			url:"/fileUploadContoller/upload",
			dataType: 'json',
			progressall: function (e, data) {
		        var progress = parseInt(data.loaded / data.total * 100, 10);
		        $('#progress1 .bar').css(
		            'width',
		            progress + '%'
		        );
		    },
			done: function(e, data) {
				var attId = data.result;
				var msg="";
				if (attId > 0) {
					alert("附件上传成功！")
					$('#progress1 .bar').css(
				            'width',
				            0 + '%'
				        );
					attIds.push(attId);
					updatebindInfo();//绑定信息
					msg = "<div id="+'"attId'+attId+'"'+"><br>" +data.files[0].name +"<input type="+'"button"'+" onclick="+'"deleteAtt('+attId+','+attIds+')"'+" value="+'"删除"'+"/>"+"</br></div> ";
					$("#msgDiv1").append(msg);
				}else{
					alert("附件上传失败！");
				}
			}
		});
		
		
	//新增绑定附件	
	var addbindInfo = function(){
			var faultId = $(".foundaodadd #faultId").val();
			var attIdString = attIds.join(",");
			$.ajax({
				type:"GET",
				url:"/knowledgeController/bindInfo",
				dataType:"json",
				data:{"faultId":faultId,"attIds":attIdString},
				success:function(data){
				 
				}
			});
		}
	
	//更新绑定附件	
	var updatebindInfo = function(){
			var faultId = $(".foundaodxg #faultId").val();
			var attIdString = attIds.join(",");
			$.ajax({
				type:"GET",
				url:"/knowledgeController/bindInfo",
				dataType:"json",
				data:{"faultId":faultId,"attIds":attIdString},
				success:function(data){
				 
				}
			});
		}
		
		 
		 refresh("",1);
	});

	//删除附件
	var deleteAtt = function(attId,attIds){
		$.ajax({
			type:"GET",
			url:"/fileUploadContoller/deleteAtt",
			dataType:"json",
			data:{"attId":attId},
			success:function(data){
				if (data) {
					remove(attId,attIds);//删除数据id
					$("#attId"+attId).remove();
					alert("附件删除成功！");
				}else{
					alert("附件删除失败！");
				}
			 
			}
		});
	};
	
	//预览下载附件
	var loadfile = function(url,fileName){
		//url = url.replace("\\/g","/");
		//url = "E:/upload/20170801102042.jpg";
		console.log(url);
		var url = "/fileUploadContoller/filedownload?url="+url+"&fileName="+fileName;
		window.location.href = url;
	};
	
	//删除数组某元素
	function remove(val,attIds){
		var index = indexOf(val,attIds);
		if(index > -1){arr.splice(index,1);}
	}
	//查找元素索引
	function indexOf(val,attIds){
		for(var i = 0; i < attIds.length; i++){
			if(attIds[i] == val){return i;}
		}
		return -1;
	}
	
	
	/*var checkFault = function(){
		faultId = $("#faultId").val();
		if (faultId == 0) {
			alert("请先保存故障流程！");
			return;
		}
	};*/
