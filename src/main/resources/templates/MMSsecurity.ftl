<#assign base=request.contextPath />
<!DOCTYPE html>   
<html lang="en">  
 <head>
    <metacharset="utf-8">
    <metahttp-equiv="X-UA-Compatible"content="IE=edge">
    <metaname="viewport"content="width=device-width, initial-scale=1">
    <title>二经运维平台-彩信保障</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" type="text/css" href="../css/public/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../css/public/paging.css">
    <link rel="stylesheet" type="text/css" href="../css/header.css">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script type="text/javascript" src="../js/public/html5shiv.min.js"></script>
    <script type="text/javascript" src="../js/public/respond.min.js"></script>
    <![endif]--> 
    <style>
 .base-header{
     border:solid 1px #d7d7d7;
     height:43px;
     text-align: center;
     background-color: #F5F5F5;
 }
 .base-header-item{
     float:left;
     margin-right: 25px;
     height: 43px;
     padding-top: 9px;
     width: 90px;
  }
 .base-header-item:hover{
     color: #333333;
     border-bottom: solid 3px #e67e6f;
 }
 .base-header-item-active{
     color: #333333;
     border-bottom: solid 3px #e67e6f;
 }
 .base-header-item>span{
     color: #666666;
     font-size: 14px;
     margin-left: 5px;
 }
 .base-opt{
   height:56px;
   background-color: #FBFBFB;
   padding-top:11px;
 }
 .base-opt-btn{
   float:left;
   height: 33px;
   margin-left: 18px;
   margin-right: 10px;
 }
 .base-opt-btn-item{
    width:width: 90px;
    border-radius: 0px;
    border:solid 1px #d7d7d7;
    background-color:#F3F3F3;
 }
 .base-opt-btn-item:hover{
    color:white;
    background-color:lightgray;
    border:solid 1px #d7d7d7;
 }
 .base-opt-sel{
   width:90px;
   height:33px;
   padding-left: 15px;
   border:solid 1px #d7d7d7;
   background-color:#F3F3F3;
 }
 .base-opt-btn-active{
   background-color:#E67C6F;
   color:white;
 }
 .base-opt-qry{
   float:right;
   padding-right: 20px;
 }
 .base-opt-qry-txt{
  height:29px;
  width:214px;
  margin-right:10px;
  border:solid 1px #d7d7d7;
 }
 .base-opt-qry-btn{
  height:29px;
  width:66px;
  padding:1px;
  background-color:#F3F3F3;
  border:solid 1px #d7d7d7;
 }
 .base-opt-qry>img{
    position: absolute;
    margin-left: -30px;
    margin-top: 8px;
 }
 .base-opt-qry-btn>img{
    margin-right: 5px;
    margin-top: -3px;
 }
 .base-label{
 	height:44px;
 	padding-top: 10px;
 	padding-left: 26px;
 	background-color:#ffffff;
 }
 .base-label-right{
    float:right;
 }
 .base-label-right>ul{
     list-style: none;
 }
 .base-label-right>ul>li{
     float:left;
     margin-right: 10px;
 }
 .base-label-right>ul>li>span{
   padding-left: 6px;
 }
 .base-table{
  background-color:#ffffff;
 }
 .base-table-tab{
  width:100%;
 }
 .base-table-tab>thead>tr{
   height:46px;
   background-color:#ebeef9;
   font-size:14px;
   color:#666666;
 }
 .base-table-tab>thead>tr>th{
   text-align:center;
 }
 .base-table-tab>tbody>tr{
   height:46px;
   font-size:14px;
   color:#888888;
 }
  .base-table-tab>tbody>.trs{
 }
  .base-table-tab>tbody>.trd{
   background-color:#fbfcff;
 }
 .base-table-tab>tbody>tr>td{
   text-align:center;
 }
</style>
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
	                 <img src="../images/icon_b.png">
	                 <span>4G彩信</span>
	             </div>
	             <div class="base-header-item">
	             	<img src="../images/icon_c.png">
	             	<span>宽带彩信</span>
	             </div>
	             <div class="base-header-item">
	                <img src="../images/icon_a.png">
	                <span>全省彩信</span>
	             </div>
	             <div style="clear:both;"></div>
             </div>
             <div class="col-md-4"></div>
          </div>
          <!--页面内容头部 end-->
          
          <!--页面内容操作栏 start-->
          <div class="base-opt">
             <div class="col-md-6">
	             <div class="btn-group base-opt-btn" data-toggle="buttons">
					<label class="btn base-opt-btn-item base-opt-btn-active">
						<input type="radio"> ODS监控
					</label>
					<label class="btn base-opt-btn-item">
						<input type="radio"> 调度监控
					</label>
					<label class="btn base-opt-btn-item">
						<input type="radio"> 指标监控
					</label>
				</div>
				<select class="base-opt-sel">
					<option value="all">全部</option>
				    <option value="1">明细1</option>
				    <option value="2">明细2</option>
				    <option value="3">明细3</option>
				</select>
             </div>
             <div class="col-md-6">
	             <div class="base-opt-qry">
	                <input class="base-opt-qry-txt" type="text" placeholder="搜索接口名称"><img src="../images/icon_search.png"/></input>
	                <button class="base-opt-qry-btn"><img src="../images/icon_d.png"/>刷新</button>
	             </div>
             </div>
          </div>
          <!--页面内容操作栏 end-->
          
          <!--页面内容标示栏 start-->
          <div class="base-label">
             <div class="col-md-3">
                <div class="base-label-left">
                	<img src="../images/icon_line.png"/>
                    <span><span>ODS监控</span>结果列表(<span>0</span>)</span>
                </div>
             </div>
             <div class="col-md-9">
                 <div class="base-label-right">
                    <ul>
                      <li><img src="../images/icon_h.png"/><span>加载成功</span>(<span>0</span>条)</li>
                      <li><img src="../images/icon_i.png"/><span>加载失败</span>(<span>0</span>条)</li>
                      <li><img src="../images/icon_j.png"/><span>波动异常</span>(<span>0</span>条)</li>
                      <li><img src="../images/icon_k.png"/><span>进行中</span>(<span>0</span>条)</li>
                      <li><img src="../images/icon_l.png"/><span>未开始</span>(<span>0</span>条)</li>
                      <li><img src="../images/icon_g.png"/><span>延迟</span>(<span>0</span>条)</li>
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
	                     <th>接口名称</th>
	                     <th>表名</th>
	                     <th>开始时间</th>
	                     <th>实际完成时间</th>
	                     <th>规定完成时间</th>
	                     <th>条数</th>
	                     <th>与上月波动性</th>
	                     <th>详情</th>
                     </tr>
                  </thead>
                  <tbody>
                      <tr class="trs">
                           <td>1</td>                      
                           <td>2</td>
                           <td>3</td>
                           <td>4</td>
                           <td>5</td>
                           <td>6</td>
                           <td>7</td>
                           <td>8</td>
                           <td>9</td>
                      </tr>
                      <tr class="trd">
                           <td>1</td>                      
                           <td>2</td>
                           <td>3</td>
                           <td>4</td>
                           <td>5</td>
                           <td>6</td>
                           <td>7</td>
                           <td>8</td>
                           <td>9</td>
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
</div>

<script type="text/javascript" src="../js/public/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="../js/public/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/public/query.js"></script>
<script type="text/javascript" src="../js/public/paging.js"></script>
<script type="text/javascript" src="../js/header.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		 //初始化网页导航模块
		  Header = new Header();
		  Header.init();
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
		 //分页初始化
		 $('#pageTool').html('');
		 $('#pageTool').Paging({pagesize:10,count:20,callback:function(page,size,count){
 		     alert(page+","+size+","+count);
   	      }});
		 
	});
</script>
</body>
</html>  