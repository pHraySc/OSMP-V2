$(document).ready(function(){
	
	//查询数量
	var expNum=0;//异常条数
	var normalNum=0;//正常条数
	var totalnum=0;//总数量
	var unknow=0;//未知数量
	//查询文件数量
	var queryFileNum = function(){
    	 $.ajax({
    		 type:"GET",
    		 url:"/fileSystemController/queryFileSystemNum",//查询总条数
    		 dataType:"json",
    		 data:{"fileSystemName":fileSystemName,"dataSouceId":dataSouceId,"status":status},
    		 async : false,
    		 success : function(data, textStatus) {
    			 if(typeof(data) != 'undefined' && data != null ){
    							normalNum = data.normalNum;
								expNum = data.expNum;
								totalnum =  data.totalnum;
								unknow = data.unknow;
    			 }else{
    				normalNum = 0;
					expNum = 0;
					totalnum = 0;
					unknow = 0;
    			 }
    		 }
    	 });
	}
});