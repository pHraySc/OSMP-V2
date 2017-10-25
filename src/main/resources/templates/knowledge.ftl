<#assign base=request.contextPath />
<!DOCTYPE html>   
<html lang="en">  
 <head>
    <metacharset="utf-8">
    <metahttp-equiv="X-UA-Compatible"content="IE=edge">
    <metaname="viewport"content="width=device-width, initial-scale=1">
    <title>经分运维一体化平台-知识库</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" type="text/css" href="../css/public/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../css/public/paging.css">
    <link rel="stylesheet" type="text/css" href="../css/header.css">
    <link rel="stylesheet" type="text/css" href="../css/knowledge.css">
    <script type="text/javascript" src="../js/public/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="../js/public/bootstrap.min.js"></script>
	<script type="text/javascript" src="../js/public/query.js"></script>
	<script type="text/javascript" src="../js/public/paging.js"></script>
	<script type="text/javascript" src="../js/header.js"></script>
	<script type="text/javascript" src="../js/knowledge.js"></script>
	<script type="text/javascript" src="./js/public/jquery.ui.widget.js"></script>
    <script type="text/javascript" src="./js/public/jquery.fileupload.js"></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script type="text/javascript" src="../js/public/html5shiv.min.js"></script>
    <script type="text/javascript" src="../js/public/respond.min.js"></script>
    <![endif]--> 
    
</head>
<body>
<!-- Begin page content -->
<divclass="container">
   <div class="row nomargin">
      <div class="page-left">
         <#include "/header.ftl">
      </div>
      <div class="page-right">
          <!--页面内容头部 start-->
          <div class="base-header">
             <div class="col-md-4"></div>
             <div class="col-md-4">
	             <div class="base-header-item base-header-item-active">
	                 <img src="../images/icon_i2.png">
	                 <span>故障处理流程</span>
	             </div>
	             <!--<div class="base-header-item">
	             	<img src="../images/icon_h2.png">
	             	<span>月报流程</span>
	             </div>-->
	             <div style="clear:both;"></div>
             </div>
             <div class="col-md-4"></div>
          </div>
          <!--页面内容头部 end-->
          
          <!--页面内容操作栏 start-->
          <div class="base-opt">
             <div class="col-md-6">
	             <div class="btn-group base-opt-btn" data-toggle="buttons">
				</div>
             </div>
             <div class="col-md-6">
	             <div class="base-opt-qry">
	                <input class="base-opt-qry-txt" type="text" placeholder="搜索故障处理流程" id="faultName"><img src="../images/icon_search.png"/></input>
	                <button class="base-opt-qry-btn" id="refresh"><img src="../images/icon_d.png"/>刷新</button>
	             </div>
             </div>
          </div>
          <!--页面内容操作栏 end-->
          
          <!--页面内容标示栏 start-->
          <div class="base-label">
             <div class="col-md-3">
                <div class="base-label-left">
                	<img src="../images/icon_line.png"/>
                    <span><span id="navBtn"></span>故障处理流程列表(<span id="totalnum">0</span>)</span>
                </div>
             </div>
             <div class="col-md-9">
                 <div class="base-label-right">
				<button type="button" class="btn btn-primary" data-toggle="button" id="add">
				<img src="../images/icon_new.png" alt=""> 新增流程</button>
                 </div>
             </div>
          </div>
          <!--页面内容标示栏 end-->
          
          <!--页面内容表格 start-->
          <div class="base-table">
             <div>
             <table class="base-table-tab" style="table-layout:fixed">
                  <thead>
                     <tr>
	                     <th width="10%">故障名称</th>
	                     <th width="10%">类别</th>
	                     <th width="25%">故障内容</th>
	                     <th width="25%">处理方式</th>
	                     <th width="15%">更新时间</th>
	                     <th class="last-th" style="width:5%">详情</th>
	                     <th class="last-th" style="width:5%">修改</th>
	                     <th class="last-th" style="width:5%">删除</th>
                     </tr>
                  </thead>
                  <tbody id="tbody">
                  </tbody>
             </table>
             </div>
             <div id="pageTool" class="anniuBox" style="text-align: right;">
          </div>
          <!--页面内容表格 end-->
      </div>
      <div style="clear:both;"></div>
   </div>
   
    <!--详情-->
      <div class="foundaods pa">
        <div class="ods_content">
			
            <div class="found_top">
             <div class="jishd" style="background-color:#fff; margin-left: 20px; "></div>
                	故障处理流程详情
            	<button type="button" class="close" id="ods-close" style="margin:10px">
                <img src="../images/close.png" alt="">
                </button>
            </div>
            <div class="dska">
                <div class="conduct-ods"> <div class="conduct-ods1">故障名称</div><div class="conduct-ods2 faultName"></div></div>
                <div class="conduct-ods"> <div class="conduct-ods1">最后修改时间</div><div class="conduct-ods2 dealDate"></div></div>
                <div class="conduct-ods"> <div class="conduct-ods1">故障分类</div><div class="conduct-ods2 typeName"></div></div>
                <div class="conduct-ods"> <div class="conduct-ods1">故障内容</div><textarea rows="2" class="conduct-ods2 faultContent"></textarea></div>
                <div class="conduct-ods"> <div class="conduct-ods1">处理方式</div><textarea rows="2" class="conduct-ods2 dealMethod"></textarea></div>
                <div class="conduct-ods" id="divClass"> <div class="conduct-ods1" id="acctachDiv" >附件</div><div class="conduct-ods2" id='acctach'></div></div>
        	</div>
         </div>
    </div>
    <!--新增-->
      <div class="foundaodadd pa">
        <div class="add_content">
			
            <div class="foundadd_top">
	             <div class="jishd" style="background-color:#fff; margin-left: 20px; "></div>
	                	新建故障处理流程
	            	<button type="button" class="close" id="add_close" style="margin:10px">
	                <img src="../images/close.png" alt="">
	                </button>
	             </div>
		            <div class="dskaadd">
                    		<div class="found_mc">
                        		<div class="form-group form-group-lg">
                           			 <div class="col-sm-5" style="width: 248px;">
		                                <label class="col-sm-6 control-label" style="padding-left:0;" for="addusername"><span class="bixuan">*</span>故障名称</label>
		                                <input type="hidden" id="faultId"/>
		                                <input  type="text" maxlength="16" class="form-control required validateName validateJobName" id="faultname" style="height:30px;width: 222px; padding:0 5px;" placeholder="请输入...">
		                                <label class="error_name error" style=" display: block;"></label>
                            		 </div>
                            		  <div class="col-sm-5" style="width: 248px;float: right;">
		                                <label class="col-sm-6 control-label" style="padding-left:0;" for="addusername"><span class="bixuan">*</span>故障分类</label>
		                                <input  type="text" maxlength="16" class="form-control required validateName validateJobName" id="typeName" style="height:30px;width: 222px; padding:0 5px;" placeholder="请输入...">
		                                <label class="error_name error" style=" display: block;"></label>
                            		 </div>
                                </div>
                            </div>
                            <div class="found_mc">
                        		<div class="form-group form-group-lg">
                           			 <div class="col-sm-5" style="width:573px;">
		                                <label class="col-sm-6 control-label" style="padding-left:0;" for="addusername"><span class="bixuan">*</span>故障处理方式</label>
		                                <textarea rows="3"  type="text" maxlength="1000" class="form-control required validateName validateJobName" id="dealmethod" style="height:100px;width:476px ; padding:0 5px;" placeholder="请输入..."></textarea>
		                                <label class="error_name error" style=" display: block;"></label>
                            		 </div>
                                </div>
                            </div>
                             <div class="found_mc">
                        		<div class="form-group form-group-lg">
                           			 <div class="col-sm-5" style="width:573px;">
		                                <label class="col-sm-6 control-label" style="padding-left:0;" for="addusername"><span class="bixuan">*</span>故障内容 </label>
		                                <textarea rows="3"  type="text" maxlength="1000" class="form-control required validateName validateJobName" id="faultcontent" style="height:100px;width:476px ; padding:0 5px;" placeholder="请输入..."></textarea>
		                                <label class="error_name error" style=" display: block;"></label>
                            		 </div>
                                </div>
                           	 </div>
                           	 <div class="found_mc">
                        		<div class="form-group form-group-lg">
                           			 <div class="col-sm-5" style="width:573px;">
		                                <label class="col-sm-6 control-label" style="padding-left:0;" for="addusername"><span class="bixuan">*</span>新增人 </label>
		                                <input  type="text" maxlength="1000" class="form-control required validateName validateJobName" id="addusername" style="height:30px;width:476px ; padding:0 5px;" placeholder="请输入...">
		                                <label class="error_name error" style=" display: block;"></label>
                            		 </div>
                                </div>
                           	 </div>
                           	 <div class="found_mc">
                        		<div class="form-group form-group-lg">
                        		     <div class="col-sm-5" style="width:573px;">
                        		     <div id="progress">
                            		  	<div class="bar" style="width: 0%;"></div>
                            		 </div>
                            		 </div>
                           			 <div class="col-sm-5" style="width:573px;" id="uploadFileDiv">
		                                <label class="col-sm-12 control-label" style="padding-left:0;" for="addusername"><span class="bixuan">*</span>附件上传</label>
		                                <button  id="adda" type="button" class="btn btn-primary" data-toggle="button">选择附件</button>
		                                <input  type="file" name="file" maxlength="1000" class="btn btn-primary required validateName validateJobName" id="uploadFile" style="height: 0px;width: 0px;padding: 0px 0px;position: absolute;top: 26px;left: 16px">
                            		 </div>
                            		 <div id="msgDiv" class="msgDiv" ></div>
                                </div>
                           	 </div>
		        	 </div>
	        	   <div class="found_bottom">
	                    <button type="button" class="btn btn-default guanq asd">取消</button>
	                    <button type="button" id="adds" class="btn btn-info asd" style="background:#51d887;">保存</button>
	                </div>
        	 </div>
    </div>
     <!--修改-->
      <div class="foundaodxg pa">
        <div class="add_content">
			
            <div class="foundadd_top">
	             <div class="jishd" style="background-color:#fff; margin-left: 20px; "></div>
	                	新建故障处理流程
	            	<button type="button" class="close" id="modify_close" style="margin:10px">
	                <img src="../images/close.png" alt="">
	                </button>
	             </div>
		            <div class="dskaadd">
                    		<div class="found_mc">
                        		<div class="form-group form-group-lg">
                           			 <div class="col-sm-5" style="width: 248px;">
		                                <label class="col-sm-6 control-label" style="padding-left:0;" for="addusername"><span class="bixuan">*</span>故障名称</label>
		                                <input type="hidden" id="faultId"/>
		                                <input  type="text" maxlength="16" class="form-control required validateName validateJobName" id="faultName" style="height:30px;width: 222px; padding:0 5px;" placeholder="请输入...">
		                                <label class="error_name error" style=" display: block;"></label>
                            		 </div>
                            		  <div class="col-sm-5" style="width: 248px;float: right;">
		                                <label class="col-sm-6 control-label" style="padding-left:0;" for="addusername"><span class="bixuan">*</span>故障分类</label>
		                                <input  type="text" maxlength="16" class="form-control required validateName validateJobName" id="typeName" style="height:30px;width: 222px; padding:0 5px;" placeholder="请输入...">
		                                <label class="error_name error" style=" display: block;"></label>
                            		 </div>
                                </div>
                            </div>
                            <div class="found_mc">
                        		<div class="form-group form-group-lg">
                           			 <div class="col-sm-5" style="width:573px;">
		                                <label class="col-sm-6 control-label" style="padding-left:0;" for="addusername"><span class="bixuan">*</span>故障处理方式</label>
		                                <textarea rows="3"  type="text" maxlength="1000" class="form-control required validateName validateJobName" id="dealMethod" style="height:100px;width:476px ; padding:0 5px;" placeholder="请输入..."></textarea>
		                                <label class="error_name error" style=" display: block;"></label>
                            		 </div>
                                </div>
                            </div>
                             <div class="found_mc">
                        		<div class="form-group form-group-lg">
                           			 <div class="col-sm-5" style="width:573px;">
		                                <label class="col-sm-6 control-label" style="padding-left:0;" for="addusername"><span class="bixuan">*</span>故障内容 </label>
		                                <textarea rows="3"  type="text" maxlength="1000" class="form-control required validateName validateJobName" id="faultContent" style="height:100px;width:476px ; padding:0 5px;" placeholder="请输入..."></textarea>
		                                <label class="error_name error" style=" display: block;"></label>
                            		 </div>
                                </div>
                           	 </div>
                           	 <div class="found_mc">
                        		<div class="form-group form-group-lg">
                           			 <div class="col-sm-5" style="width:573px;">
		                                <label class="col-sm-6 control-label" style="padding-left:0;" for="addusername"><span class="bixuan">*</span>新增人 </label>
		                                <input  type="text" maxlength="1000" class="form-control required validateName validateJobName" id="addusername" style="height:30px;width:476px ; padding:0 5px;" placeholder="请输入...">
		                                <label class="error_name error" style=" display: block;"></label>
                            		 </div>
                                </div>
                           	 </div>
                           	 <div class="found_mc">
                        		<div class="form-group form-group-lg">
                        		     <div class="col-sm-5" style="width:573px;">
                        		     <div id="progress1">
                            		  	<div class="bar" style="width: 0%;"></div>
                            		 </div>
                            		 </div>
                           			 <div class="col-sm-5" style="width:573px;">
		                                <label class="col-sm-12 control-label" style="padding-left:0;" for="addusername"><span class="bixuan">*</span>附件上传</label>
		                                <div style="height:55px;">	
		                                <button  id="adda" type="button" class="btn btn-primary" data-toggle="button">选择附件</button>
		                                <input  type="file" name="file" maxlength="1000" class="validateName validateJobName" id="uploadFile1" style="height: 0px;width: 0px;padding: 0px 0px;position: absolute;top: 26px;left: 16px">
		                                </div>
		                                <div id="msgDiv1"></div>
                            		 </div>
                                </div>
                           	 </div>
		        	 </div>
	        	   <div class="found_bottom">
	                    <button type="button" class="btn btn-default guanq1 asd">取消</button>
	                    <button type="button" id="modify" class="btn btn-info asd" style="background:#51d887;">保存</button>
	                </div>
        	 </div>
    </div>
   
   
</div>
</body>
</html>  