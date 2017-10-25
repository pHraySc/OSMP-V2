<#assign base=request.contextPath />
<!DOCTYPE html>   
<html lang="en">  
 <head>
    <metacharset="utf-8">
    <metahttp-equiv="X-UA-Compatible"content="IE=edge">
    <metaname="viewport"content="width=device-width, initial-scale=1">
    <title>Spring Boot - hello</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" type="text/css" href="../css/public/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../css/header.css">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script type="text/javascript" src="../js/public/html5shiv.min.js"></script>
    <script type="text/javascript" src="../js/public/respond.min.js"></script>
    <![endif]--> 
    <style>
 .base-header{
     height:43px;
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
          <div class="base-header"></div>
      </div>
      <div style="clear:both;"></div>
   </div>
</div>

<script type="text/javascript" src="../js/public/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="../js/public/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/header.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		 //初始化网页导航模块
		  Header = new Header();
		  Header.init();
		  Header.show("speMonitor");
	});
</script>
</body>
</html>  