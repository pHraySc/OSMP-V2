<#assign base=request.contextPath />
<!DOCTYPE html>   
<html lang="en">  
 <head>
    <metacharset="utf-8">
    <metahttp-equiv="X-UA-Compatible"content="IE=edge">
    <metaname="viewport"content="width=device-width, initial-scale=1">
    <title>经分运维一体化平台-文件系统监控</title>

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
	             <div class="base-header-item" onclick="location.href='baseMonitor_DISPATC'">
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
	             <div class="base-header-item base-header-item-active">
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
	             <!--<div class="btn-group base-opt-btn" data-toggle="buttons">
					<label class="btn base-opt-btn-item base-opt-btn-active">
						<input type="radio" data-id="ddb">全部
					</label>
				    <label class="btn base-opt-btn-item">
						<input type="radio"  data-id="134">134仓库
					</label>
					<label class="btn base-opt-btn-item">
						<input type="radio"  data-id="fdb">一经库
					</label>
					<label class="btn base-opt-btn-item">
						<input type="radio"  data-id="vdb">VGOP库
					</label>
				</div>-->
				<!--<select class="base-opt-sel">
					<option value="all">全部</option>
				    <option value="1">明细1</option>
				    <option value="2">明细2</option>
				    <option value="3">明细3</option>
				</select>-->
             </div>
             <div class="col-md-6">
	             <div class="base-opt-qry">
	               <!-- <select class="base-opt-qry-sel" data-type="1">
						<option value="all">全部</option>
					    <option value="day">日接口</option>
					    <option value="mon">月接口</option>
				    </select>
	                <select class="base-opt-qry-sel"  data-type="2">
					<option value="all">全部接口</option>
				    <option value="imp">重要接口</option>
				</select> -->
	                <input class="base-opt-qry-txt" type="text" placeholder="搜索文件系统IP地址" id="fileSystemName"><img src="../images/icon_search.png"/></input>
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
                    <span><span id="navBtn"></span>文件系统监控列表(<span id="totalnum">0</span>)</span>
                </div>
             </div>
             <div class="col-md-9">
                 <div class="base-label-right">
                    <ul>
                      <li><img src="../images/icon_h.png"/><span>正常</span>(<span id="normalNum" class="spanclass">0</span>条)</li>
                      <li><img src="../images/icon_i.png"/><span>警告</span>(<span id="expNum" class="spanclass">0</span>条)</li>
                      <li><img src="../images/icon_l.png"/><span>未知</span>(<span id="unknow" class="spanclass">0</span>条)</li>
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
	                     <th>文件系统IP</th>
	                     <th>路径</th>
	                     <th>已用百分比</th>
	                     <th>剩余大小</th>
	                     <th>阀值</th>
	                     <th>更新时间</th>
	                     <th class="last-th">详情</th>
                     </tr>
                  </thead>
                  <tbody id="tbody">
	              <tr>
		              <td></td>
	                  <td></td>
	                  <td></td>
	                  <td></td>
	                  <td></td>
	                  <td></td>
	                  <td class='odsInterDeatil_click' data-status='' title='' value=''><img  src='../images/icon_detials.png'/></td>
                  </tr>
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
                	文件系统监控详情
            	<button type="button" class="close" id="ods-close" style="margin:10px">
                <img src="../images/close.png" alt="">
                </button>
            </div>
            <div class="dska">
                <div class="load">
                  <div class="load-img fileStatus"></div>
                  <div class="load-name fileIP"></div>
                  <div class="load-state result"></div>
                </div>
                <div class="conduct-ods"> <div class="conduct-ods1">文件系统IP</div><div class="conduct-ods2 fileIP"></div></div>
                <div class="conduct-ods"> <div class="conduct-ods1">路径</div><div class="conduct-ods2 filePath"></div></div>
                <div class="conduct-ods"> <div class="conduct-ods1">已用百分比</div><div class="conduct-ods2 remainPercent"></div></div>
                <div class="conduct-ods"> <div class="conduct-ods1">剩余大小</div><div class="conduct-ods2 remainSpace"></div></div>
                <div class="conduct-ods"> <div class="conduct-ods1">阀值</div><div class="conduct-ods2 threshold"></div></div>
        	</div>
         </div>
    </div>
</div>

<script type="text/javascript" src="../js/public/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="../js/public/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/public/query.js"></script>
<script type="text/javascript" src="../js/public/paging.js"></script>
<script type="text/javascript" src="../js/header.js"></script>
<script type="text/javascript" src="../js/baseMonitor_FILE.js"></script>
</body>
</html>  