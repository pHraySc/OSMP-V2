<#assign base=request.contextPath />
<html lang="en">
<head>
	<metacharset="utf-8">
    <metahttp-equiv="X-UA-Compatible"content="IE=edge">
    <metaname="viewport"content="width=device-width, initial-scale=1">
    <title>经分运维一体化平台-首页 </title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" type="text/css" href="./css/public/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="./css/public/paging.css">
    <link rel="stylesheet" type="text/css" href="./css/header.css">
    <link rel="stylesheet" type="text/css" href="./css/baseMonitor.css">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script type="text/javascript" src="./js/public/html5shiv.min.js"></script>
    <script type="text/javascript" src="./js/public/respond.min.js"></script>
    <![endif]--> 
</head>
<body>
<!-- Begin page content -->
 <div class="row nomargin">
   <div class="page-left">
         <#include "/header.ftl">
    </div>
       <div class="page-right">
       	<!--页面内容头部 start-->
          <div class="base-header">
          	<div class="base-header-item" style="width: 300px;">
	          <div class="col-md-7" >
	          	<span style="text-align: left">当前位置：首页</span>
	          </div>
          	</div>
         <!-- 页面内容头部 end -->
       </div>
       <!-- 页面图表开始 start -->
       <div class="base-table">
       <div class="col-md-3" id="MyChart1" style="height: 200px; width:25%; border: 1px solid #ccc; padding: 10px;">1</div>
       <div class="col-md-3" id="MyChart2" style="height: 200px; width:25%; border: 1px solid #ccc; padding: 10px;">2</div>
       <div class="col-md-3" id="MyChart3" style="height: 200px; width:25%; border: 1px solid #ccc; padding: 10px;">3</div>
       <div class="col-md-3" id="MyChart4" style="height: 200px; width:25%; border: 1px solid #ccc; padding: 10px;">4</div>
       </div>
       <!-- 页面图表结束 end -->
 	</div>
<script type="text/javascript" src="./js/public/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="./js/public/bootstrap.min.js"></script>
<script type="text/javascript" src="./js/public/query.js"></script>
<script type="text/javascript" src="./js/public/paging.js"></script>
<script type="text/javascript" src="./js/charts_file.js"></script>
<script type="text/javascript" src="./js/charts_tablespace.js"></script>
<script type="text/javascript" src="./js/charts_interface.js"></script>
<script type="text/javascript" src="./js/charts_dispatc.js"></script>
<script type="text/javascript" src="./js/header.js"></script>
<script type="text/javascript" src="./js/echarts-2.2.7/build/dist/echarts.js"></script>
</body>
</html>