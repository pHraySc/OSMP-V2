<#assign base=request.contextPath />
<!DOCTYPE html>   
<html lang="en">  
 <head>
    <metacharset="utf-8">
    <metahttp-equiv="X-UA-Compatible"content="IE=edge">
    <metaname="viewport"content="width=device-width, initial-scale=1">
    <title>经分运维一体化平台-文件监控配置</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" type="text/css" href="../css/public/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../css/public/paging.css">
    <link rel="stylesheet" type="text/css" href="../css/header.css">
    <link rel="stylesheet" type="text/css" href="../css/baseMonitor.css">
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
             	 <div class="base-header-item base-header-item-active">
	                 <img src="../images/icon_mon.png">
	                 <span>文件监控配置</span>
	             </div>
	             <div class="base-header-item" onclick="location.href='baseMonitor_DISPATC'">
	             	<img src="../images/icon_c.png">
	             	<span>接口监控配置</span>
	             </div>
	             <div class="base-header-item" onclick="location.href='baseMonitor_TABLESPACE'">
	                <img src="../images/icon_a.png">
	                <span>表空间监控配置</span>
	             </div>
	             <div class="base-header-item" onclick="location.href='config_File'">
	                <img src="../images/icon_a.png">
	                <span>调度配置</span>
	             </div>
	             <div style="clear:both;"></div>
             </div>
             <div class="col-md-3"></div>
          </div>
          <!--页面内容头部 end-->
          
          <!--页面内容操作栏 start-->
          <div class="base-opt">
             <div class="col-md-6">
             </div>
             <div class="col-md-6">
	             <div class="base-opt-qry">
	                <input class="base-opt-qry-txt" type="text" placeholder="搜索文件系统IP地址" id="fileIp"><img src="../images/icon_search.png"/></input>
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
                    <span><span id="navBtn"></span>文件系统配置列表(<span id="totalnum">0</span>)</span>
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
             <table class="base-table-tab">
                  <thead>
                     <tr>
	                     <th>文件系统IP</th>
	                     <th>路径</th>
	                     <th>阈值</th>
	                     <th>创建人</th>
	                     <th>创建时间</th>
	                     <th class="last-th">详情</th>
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
                	文件系统配置详情
            	<button type="button" class="close" id="ods-close" style="margin:10px">
                <img src="../images/close.png" alt="">
                </button>
            </div>
            <div class="dska">
                <!-- <div class="load">
                  <div class="load-img fileStatus"></div>
                  <div class="load-name fileIP"></div>
                  <div class="load-state result"></div>
                </div> -->
                <div class="conduct-ods"> <div class="conduct-ods1">文件系统IP</div><div class="conduct-ods2 fileIp"></div></div>
                <div class="conduct-ods"> <div class="conduct-ods1">文件系统路径</div><div class="conduct-ods2 filePath"></div></div>
                <div class="conduct-ods"> <div class="conduct-ods1">阈值</div><div class="conduct-ods2 threshold"></div></div>
                <div class="conduct-ods"> <div class="conduct-ods1">创建人</div><div class="conduct-ods2 creator"></div></div>
                <div class="conduct-ods"> <div class="conduct-ods1">创建时间</div><div class="conduct-ods2 createTime"></div></div>
        	</div>
         </div>
    </div>
    
     <!--新增-->
    <div class="foundaodadd pa" id="newAddInfo">
        <div class="add_content">
			
            <div class="foundadd_top">
	             <div class="jishd" style="background-color:#fff; margin-left: 20px; "></div>
	                	新建文件系统配置
	            	<button type="button" class="close" id="add_close" style="margin:10px">
	                <img src="../images/close.png" alt="">
	                </button>
	             </div>
		            <div class="dskaadd">
                    		<div class="found_mc">
                        		<div class="form-group form-group-lg">
                           			 <div class="col-sm-5" style="width: 248px;">
		                                <label class="col-sm-6 control-label" style="padding-left:0;" for="fileIp"><span class="bixuan">*</span>文件系统IP</label>
		                                <input type="hidden" id="fileConfigId"/>
		                                <input  type="text" maxlength="16" class="form-control required validateName validateJobName" id="fileIp" style="height:30px;width: 222px; padding:0 5px;" placeholder="请输入...">
		                                <label class="error_name error" style=" display: block;"></label>
                            		 </div>
                            		  <div class="col-sm-5" style="width: 248px;float: right;">
		                                <label class="col-sm-6 control-label" style="padding-left:0;" for="filePath"><span class="bixuan">*</span>文件系统路径</label>
		                                <input  type="text" maxlength="16" class="form-control required validateName validateJobName" id="filePath" style="height:30px;width: 222px; padding:0 5px;" placeholder="请输入...">
		                                <label class="error_name error" style=" display: block;"></label>
                            		 </div>
                                </div>
                            </div>
                            <div class="found_mc">
                        		<div class="form-group form-group-lg">
                           			 <div class="col-sm-5" style="width:573px;">
		                                <label class="col-sm-6 control-label" style="padding-left:0;" for="threshold"><span class="bixuan">*</span>阈值</label>
		                                <input type="text" maxlength="1000" class="form-control required validateName validateJobName" id="threshold" style="height:100px;width:476px ; padding:0 5px;" placeholder="请输入..."></input>
		                                <label class="error_name error" style=" display: block;"></label>
                            		 </div>
                                </div>
                            </div>
                             <div class="found_mc">
                        		<div class="form-group form-group-lg">
                           			 <div class="col-sm-5" style="width:573px;">
		                                <label class="col-sm-6 control-label" style="padding-left:0;" for="creator"><span class="bixuan">*</span>创建人 </label>
		                                <input type="text" maxlength="1000" class="form-control required validateName validateJobName" id="creator" style="height:100px;width:476px ; padding:0 5px;" placeholder="请输入..."></input>
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
</div>

<script type="text/javascript" src="../js/public/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="../js/public/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/public/query.js"></script>
<script type="text/javascript" src="../js/public/paging.js"></script>
<script type="text/javascript" src="../js/header.js"></script>
<script type="text/javascript" src="../js/config_File.js"></script>
</body>
</html>  