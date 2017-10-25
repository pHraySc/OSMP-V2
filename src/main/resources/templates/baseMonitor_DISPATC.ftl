<#assign base=request.contextPath />
<!DOCTYPE html>   
<html lang="en">  
 <head>
    <metacharset="utf-8">
    <metahttp-equiv="X-UA-Compatible"content="IE=edge">
    <metaname="viewport"content="width=device-width, initial-scale=1">
    <title>经分运维一体化平台-基础监控</title>

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
	             <div class="base-header-item" onclick="location.href='baseMonitor'">
	                 <img src="../images/icon_mon.png">
	                 <span>接口加载</span>
	             </div>
	             <div class="base-header-item base-header-item-active">
	             	<img src="../images/icon_c.png">
	             	<span>调度运行</span>
	             </div>
	             <!--<div class="base-header-item">
	                <img src="../images/icon_a.png">
	                <span>主机性能</span>
	             </div>-->
	              <div class="base-header-item" onclick="location.href='baseMonitor_TABLESPACE'">
	                <img src="../images/icon_a.png">
	                <span>表空间监控</span>
	             </div>
	              <div class="base-header-item"  onclick="location.href='baseMonitor_FILE'">
	                <img src="../images/icon_a.png">
	                <span>文件系统监控</span>
	             </div>
	             <div style="clear:both;"></div>
             </div>
             <div class="col-md-3"></div>
          </div>
          <!--页面内容头部 end-->
          
          <!--页面内容操作栏 start-->
          <div class="base-opt">
             <div class="col-md-6">
	             <div class="btn-group base-opt-btn" data-toggle="buttons" id="baseoptbtn">
	             
				 </div>
             </div>
             <div class="col-md-6">
	             <div class="base-opt-qry">
	                <select class="base-opt-qry-sel" data-type="1" id="select_button1">
						<option value="all">全部</option>
					    <option value="D">日调度</option>
					    <option value="M">月调度</option>
				    </select>
	                <select class="base-opt-qry-sel"  data-type="2" id="select_button2">
						<option value="all">全部调度</option>
					    <option value="base">全省彩信调度</option>
					    <option value="4g">4G彩信调度</option>
					    <option value="wide">宽带彩信</option>
				</select>
	                <input class="base-opt-qry-txt" type="text" placeholder="搜索任务名称" id="dispatcName"><img src="../images/icon_search.png"/></input>
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
                    <span>调度列表(<span id="totalnum">0</span>)</span>
                </div>
             </div>
             <div class="col-md-9">
                 <div class="base-label-right">
                    <ul>
                      <li><img src="../images/icon_h.png"/><span>运行成功</span>(<span id="success" class="spanclass">0</span>条)</li>
                      <li><img src="../images/icon_i.png"/><span>运行失败</span>(<span id="fail" class="spanclass">0</span>条)</li>
                      <li><img src="../images/icon_k.png"/><span>运行中</span>(<span id="process" class="spanclass">0</span>条)</li>
                      <li><img src="../images/icon_l.png"/><span>未开始</span>(<span id="notstart" class="spanclass">0</span>条)</li>
                      <li><img src="../images/icon_g.png"/><span>延迟</span>(<span id="timeout" class="spanclass">0</span>条)</li>
                    </ul>
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
	                     <th width="5%">状态</th>
	                     <th width="15%">任务名称</th>
	                     <th width="15%">节点名称</th>
	                     <th width="15%">开始时间</th>
	                     <th width="15%">实际完成时间</th>
	                     <th width="10%">规定完成时间</th>
	                     <th width="10%">更新时间</th>
	                     <th class="last-th"  width="5%">详情</th>
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
                	接口加载详情
            	<button type="button" class="close" id="ods-close" style="margin:10px">
                <img src="./static/images/close.png" alt="">
                </button>
            </div>
            <div class="dska">
                <div class="load">
                  <div class="load-img status"></div>
                  <div class="load-name taskId">m030001</div>
                  <div class="load-state result"></div>
                </div>
                <div class="cause">原因：<span class="odsWarningReason detail"></span></div>
                <div class="conduct-ods"> <div class="conduct-ods1">任务ID</div><div class="conduct-ods2 taskId"></div></div>
                <div class="conduct-ods"> <div class="conduct-ods1">节点名称</div><div class="conduct-ods2 nodeName"></div></div>
                <div class="conduct-ods"> <div class="conduct-ods1">实际完成时间</div><div class="conduct-ods2 beginDate"></div></div>
                <div class="conduct-ods"> <div class="conduct-ods1">要求完成时间</div><div class="conduct-ods2 actualDate"></div></div>
                <div class="conduct-ods"> <div class="conduct-ods1">规定完成时间</div><div class="conduct-ods2 demainDate"></div></div>
            </div>
        </div>
    </div>
   
   
</div>

<script type="text/javascript" src="../js/public/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="../js/public/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/public/query.js"></script>
<script type="text/javascript" src="../js/public/paging.js"></script>
<script type="text/javascript" src="../js/baseMonitor_DISPATC.js"></script>
<script type="text/javascript" src="../js/header.js"></script>
</body>
</html>  