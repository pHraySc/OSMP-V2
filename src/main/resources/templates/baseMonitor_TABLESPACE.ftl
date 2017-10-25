<#assign base=request.contextPath />
<!DOCTYPE html>   
<html lang="en">  
 <head>
    <metacharset="utf-8">
    <metahttp-equiv="X-UA-Compatible"content="IE=edge">
    <metaname="viewport"content="width=device-width, initial-scale=1">
    <title>经分运维一体化平台-表空间监控</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" type="text/css" href="../css/public/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../css/public/paging.css">
    <link rel="stylesheet" type="text/css" href="../css/header.css">
    <link rel="stylesheet" type="text/css" href="../css/baseMonitor.css">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script type="text/javascript" src="../js/public/html5shiv.min.js" charset="utf-8"></script>
    <script type="text/javascript" src="../js/public/respond.min.js" charset="utf-8"></script>
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
	             <div class="base-header-item"  onclick="location.href='baseMonitor'">
	                 <img src="../images/icon_mon.png">
	                 <span>接口加载</span>
	             </div>
	             <div class="base-header-item" onclick="location.href='baseMonitor_DISPATC'">
	             	<img src="../images/icon_c.png">
	             	<span>调度运行</span>
	             </div>
	             <!--<div class="base-header-item">
	                <img src="../images/icon_a.png">
	                <span>主机性能</span>
	             </div>-->
	             <div class="base-header-item base-header-item-active">
	                <img src="../images/icon_a.png">
	                <span>表空间监控</span>
	             </div>
	              <div class="base-header-item" onclick="location.href='baseMonitor_FILE'">
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
	                <input class="base-opt-qry-txt" type="text" placeholder="搜索表空间名称" id="interface"><img src="../images/icon_search.png"/></input>
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
                    <span><span id="navBtn"></span>表空间监控列表(<span id="totalnum">0</span>)</span>
                </div>
             </div>
             <div class="col-md-9">
                 <div class="base-label-right">
                    <ul>
                      <li><img src="../images/icon_h.png"/><span>正常</span>(<span id="success" class='spanclass'>0</span>条)</li>
                      <li><img src="../images/icon_i.png"/><span>异常</span>(<span id="exception" class='spanclass'>0</span>条)</li>
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
	                     <th>状态</th>
	                     <th>表空间名称</th>
	                     <th>实际百分比</th>
	                     <th>剩余大小</th>
	                     <th>阀值</th>
	                     <th>更新时间</th>
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
                	表空间监控详情
            	<button type="button" class="close" id="ods-close" style="margin:10px">
                <img src="../images/close.png" alt="">
                </button>
            </div>
            <div class="dska">
                <div class="load">
                  <div class="load-img interStatus"></div>
                  <div class="load-name tableSpaceName">m030001</div>
                  <div class="load-state result"></div>
                </div>
               <!--  <div class="conduct-ods"> <div class="conduct-ods1">表空间名称</div><div class="conduct-ods2 tableSpaceName"></div></div>
                <div class="conduct-ods"> <div class="conduct-ods1">实际百分比</div><div class="conduct-ods2 actualPercent"></div></div>
                <div class="conduct-ods"> <div class="conduct-ods1">剩余大小</div><div class="conduct-ods2 remainSpace"></div></div>
                <div class="conduct-ods"> <div class="conduct-ods1">阀值</div><div class="conduct-ods2 threshold"></div></div> -->
                <div>
	                <table class="base-table-tab">
	                  <thead>
	                     <tr>
		                     <th width="20%" style="text-align: center;">状态</th>
		                     <th width="20%" style="text-align: center;">节点名称</th>
		                     <th width="20%" style="text-align: center;">总量大小</th>
		                     <th width="20%" style="text-align: center;">实际百分比</th>
		                     <th width="20%" style="text-align: center;">剩余大小</th>
	                     </tr>
	                  </thead>
	                  </table>
	            </div>      
	            <div style="height: 300px;overflow: auto;">
	                 <table class="base-table-tab">
	                  <tbody id="tbody1">
	                  </tbody>
	             	</table>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="../js/public/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="../js/public/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/public/query.js"></script>
<script type="text/javascript" src="../js/public/paging.js"></script>
<script type="text/javascript" src="../js/header.js"></script>
<script type="text/javascript" src="../js/baseMonitor_TABLESPACE.js"></script>
</body>
</html>  