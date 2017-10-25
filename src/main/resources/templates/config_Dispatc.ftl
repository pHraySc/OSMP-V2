<#assign base=request.contextPath />
<!DOCTYPE html>   
<html lang="en">  
 <head>
    <metacharset="utf-8">
    <metahttp-equiv="X-UA-Compatible"content="IE=edge">
    <metaname="viewport"content="width=device-width, initial-scale=1">
    <title>经分运维一体化平台-配置管理</title>

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
	<script type="text/javascript" src="../js/config_Dispatc.js"></script>
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
             <div class="col-md-3"></div>
             <div class="col-md-7">
          		 <div class="base-header-item" onclick="location.href='config_File'">
	                 <img src="../images/icon_mon.png">
	                 <span>文件监控配置</span>
	             </div>
	             <div class="base-header-item" onclick="location.href='config_InterFace'">
	             	<img src="../images/icon_c.png">
	             	<span>接口监控配置</span>
	             </div>
	              <div class="base-header-item" onclick="location.href='config_TableSpace'">
	                <img src="../images/icon_a.png">
	                <span>表空间监控配置</span>
	             </div>
	              <div class="base-header-item base-header-item-active">
	                <img src="../images/icon_a.png">
	                <span>调度监控配置</span>
	             </div>
	             <div style="clear:both;"></div>
             </div>
             <div class="col-md-3"></div>
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
	                <input class="base-opt-qry-txt" type="text" placeholder="搜索调度NodeId" id="nodeId"><img src="../images/icon_search.png"/></input>
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
                    <span><span id="navBtn"></span>调度监控配置列表(<span id="totalnum">0</span>)</span>
                </div>
             </div>
             <div class="col-md-9">
                 <div class="base-label-right">
				<button type="button" class="btn btn-primary" data-toggle="button" id="add">
				<img src="../images/icon_new.png" alt="">新增配置</button>
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
	                     <th width="10%">节点Id</th>
	                     <th width="8%">调度数据源</th>
	                     <th width="15%">要求时间</th>
	                     <th width="12%">日/月调度</th>
	                     <th width="15%">调度类型</th>
	                     <th width="10%">创建人</th>
	                     <th width="15%">创建时间</th>
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
                	调度监控配置详情
            	<button type="button" class="close" id="ods-close" style="margin:10px">
                <img src="../images/close.png" alt="">
                </button>
            </div>
            <div class="dska">
                <div class="conduct-ods"> <div class="conduct-ods1">节点Id</div><div class="conduct-ods2 nodeId"></div></div>
                <div class="conduct-ods"> <div class="conduct-ods1">调度数据源</div><div class="conduct-ods2 dataSource"></div></div>
                <div class="conduct-ods"> <div class="conduct-ods1">要求时间</div><div class="conduct-ods2 demainTime"></div></div>
                <div class="conduct-ods"> <div class="conduct-ods1">日/月调度</div><div class="conduct-ods2 isDay"></div></div>
                <div class="conduct-ods"> <div class="conduct-ods1">调度类型</div><div class="conduct-ods2 dispatcType"></div></div>
                <div class="conduct-ods"> <div class="conduct-ods1">创建人</div><div class="conduct-ods2 creator"></div></div>
                <div class="conduct-ods"> <div class="conduct-ods1">创建时间</div><div class="conduct-ods2 createTime"></div></div>
        	</div>
         </div>
    </div>
    <!--新增-->
      <div class="foundaodadd pa">
        <div class="add_content">
			
            <div class="foundadd_top">
	             <div class="jishd" style="background-color:#fff; margin-left: 20px; "></div>
	                	新建调度配置
	            	<button type="button" class="close" id="add_close" style="margin:10px">
	                <img src="../images/close.png" alt="">
	                </button>
	             </div>
		            <div class="dskaadd" style="height: 210px;">
                    		<div class="found_mc">
                        		<div class="form-group form-group-lg">
                           			 <div class="col-sm-5" style="width: 248px;">
		                                <label class="col-sm-6 control-label" style="padding-left:0;" for="nodeId"><span class="bixuan">*</span>节点Id</label>
		                                <input type="hidden" id="dispatcId"/>
		                                <input  type="text" maxlength="16" class="form-control required validateName validateJobName" id="nodeId" style="height:30px;width: 222px; padding:0 5px;" placeholder="请输入...">
		                                <label class="error_name error" style=" display: block;"></label>
                            		 </div>
                            		  <div class="col-sm-5" style="width: 248px;float: right;">
		                                <label class="col-sm-6 control-label" style="padding-left:0;width: 200px;" for="filePath"><span class="bixuan">*</span>调度数据源</label>
		                                <input type="text" maxlength="16" class="form-control required validateName validateJobName" id="dataSource" style="height:30px;width: 222px; padding:0 5px;" placeholder="请输入...">
		                                <label class="error_name error" style=" display: block;"></label>
                            		 </div>
                                </div>
                            </div>
                            <div class="found_mc">
                        		<div class="form-group form-group-lg">
                           			  <div class="col-sm-5" style="width: 248px;">
		                                <label class="col-sm-6 control-label" style="padding-left:0;width: 200px;" for="threshold"><span class="bixuan">*</span>日/月调度</label>
		                                <select id="isDay" style="height:30px;width: 222px; padding:0 5px;" onchange="changeType()">
		                                	<option value="D">日</option>
		                                	<option value="M">月</option>
		                                </select>
		                                <label class="error_name error" style=" display: block;"></label>
                            		 </div>
                            		 <div class="col-sm-5" style="width: 248px;float: right;">
		                                <label class="col-sm-6 control-label" style="padding-left:0;" for="demainTime"><span class="bixuan">*</span>要求时间</label>
		                                <input  type="time" maxlength="16" class="form-control required validateName validateJobName" id="demainTime" style="height:30px;width: 222px; padding:0 5px;" placeholder="请输入...">
		                                <label class="error_name error" style=" display: block;"></label>
                            		 </div>
                                </div>
                            </div>
                             <div class="found_mc">
                        		<div class="form-group form-group-lg">
                            		  <div class="col-sm-5" style="width: 248px;float: right;">
		                                <label class="col-sm-6 control-label" style="padding-left:0;" for="creator"><span class="bixuan">*</span>创建人</label>
		                                <input  type="text" maxlength="16" class="form-control required validateName validateJobName" id="creator" style="height:30px;width: 222px; padding:0 5px;" placeholder="请输入...">
		                                <label class="error_name error" style=" display: block;"></label>
                            		 </div>
                            		  <div class="col-sm-5" style="width: 248px;">
		                                <label class="col-sm-6 control-label" style="padding-left:0;width: 200px;" for="threshold"><span class="bixuan">*</span>调度类型</label>
		                                <select id="dispatcType" style="height:30px;width: 222px; padding:0 5px;" >
		                                	<option value="base">全省彩信调度</option>
		                                	<option value="4g">4G彩信调度</option>
		                                	<option value="wide">宽带彩信</option>
		                                </select>
		                                <label class="error_name error" style=" display: block;"></label>
                            		 </div>
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
	                	修改调度配置
	            	<button type="button" class="close" id="modify_close" style="margin:10px">
	                <img src="../images/close.png" alt="">
	                </button>
	             </div>
	            	 <div class="dskaadd" style="height: 210px;">
                    		<div class="found_mc">
                        		<div class="form-group form-group-lg">
                           			 <div class="col-sm-5" style="width: 248px;">
		                                <label class="col-sm-6 control-label" style="padding-left:0;" for="nodeId"><span class="bixuan">*</span>节点Id</label>
		                                <input type="hidden" id="dispatcId"/>
		                                <input  type="text" maxlength="16" class="form-control required validateName validateJobName" id="nodeId" style="height:30px;width: 222px; padding:0 5px;" placeholder="请输入...">
		                                <label class="error_name error" style=" display: block;"></label>
                            		 </div>
                            		  <div class="col-sm-5" style="width: 248px;float: right;">
		                                <label class="col-sm-6 control-label" style="padding-left:0;width: 200px;" for="filePath"><span class="bixuan">*</span>调度数据源</label>
		                                <input type="text" maxlength="16" class="form-control required validateName validateJobName" id="dataSource" style="height:30px;width: 222px; padding:0 5px;" placeholder="请输入...">
		                                <label class="error_name error" style=" display: block;"></label>
                            		 </div>
                                </div>
                            </div>
                            <div class="found_mc">
                        		<div class="form-group form-group-lg">
                           			  <div class="col-sm-5" style="width: 248px;">
		                                <label class="col-sm-6 control-label" style="padding-left:0;width: 200px;" for="threshold"><span class="bixuan">*</span>日/月调度</label>
		                                <select id="isDay" style="height:30px;width: 222px; padding:0 5px;" onchange="changeType()">
		                                	<option value="D">日</option>
		                                	<option value="M">月</option>
		                                </select>
		                                <label class="error_name error" style=" display: block;"></label>
                            		 </div>
                            		 <div class="col-sm-5" style="width: 248px;float: right;">
		                                <label class="col-sm-6 control-label" style="padding-left:0;" for="demainTime"><span class="bixuan">*</span>要求时间</label>
		                                <input  type="time" maxlength="16" class="form-control required validateName validateJobName" id="demainTime" style="height:30px;width: 222px; padding:0 5px;" placeholder="请输入...">
		                                <label class="error_name error" style=" display: block;"></label>
                            		 </div>
                                </div>
                            </div>
                             <div class="found_mc">
                        		<div class="form-group form-group-lg">
                            		  <div class="col-sm-5" style="width: 248px;float: right;">
		                                <label class="col-sm-6 control-label" style="padding-left:0;" for="creator"><span class="bixuan">*</span>创建人</label>
		                                <input  type="text" maxlength="16" class="form-control required validateName validateJobName" id="creator" style="height:30px;width: 222px; padding:0 5px;" placeholder="请输入...">
		                                <label class="error_name error" style=" display: block;"></label>
                            		 </div>
                            		  <div class="col-sm-5" style="width: 248px;">
		                                <label class="col-sm-6 control-label" style="padding-left:0;width: 200px;" for="threshold"><span class="bixuan">*</span>调度类型</label>
		                                <select id="dispatcType" style="height:30px;width: 222px; padding:0 5px;" >
		                                	<option value="base">全省彩信调度</option>
		                                	<option value="4g">4G彩信调度</option>
		                                	<option value="wide">宽带彩信</option>
		                                </select>
		                                <label class="error_name error" style=" display: block;"></label>
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