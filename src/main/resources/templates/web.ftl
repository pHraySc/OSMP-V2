<#assign base=request.contextPath />
<!DOCTYPE html>   
<html lang="en">  
 <head>
    <metacharset="utf-8">
    <metahttp-equiv="X-UA-Compatible"content="IE=edge">
    <metaname="viewport"content="width=device-width, initial-scale=1">
    <title>Spring Boot - hello</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
    <link href="../css/omp.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script type="text/javascript" src="../js/html5shiv.min.js"></script>
    <script type="text/javascript" src="../js/respond.min.js"></script>
    <![endif]--> 
	<script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="../js/bootstrap.min.js"></script>
    <style>
    	html{
    position: relative;
    min-height:100%;
}
body{
    /* Margin bottom by footer height */
    margin-bottom:60px;
}
.footer{
    position: absolute;
    bottom:0;
    width:100%;
    /* Set the fixed height of the footer here */
    height:60px;
    background-color:#f5f5f5;
}

.container{
    width: auto;
    max-width:680px;
    padding:015px;
}
.container.text-muted{
    margin:20px0;
}
    </style>
</head>

<body>

<!-- Begin page content -->
<divclass="container">
    <divclass="page-header">
        <h1>Sprint Boot: Hello</h1>
    </div>

    <div>
        Date: ${time?date}
        <br>
        Time: ${time?time}
        <br>
        Message: ${message}
        <br>
        Listsize:${list?size}
        <#list list as l>
        <table>
        	<tr>
        	<td>${l.id}</td>
        	<td>${l.province_id}</td> 
        	<td>${l.city_name}</td>
        	<td>${l.description}</td>
        	</tr>
        </table>
        </#list>
    </div>
    <div class=""container>
    	<div class="row">
    		<div class="col-md-6">1</div>
    		<div class="col-md-6">2</div>
    	</div>
    	
    	<div class="row">
    		<div class="col-md-4"><button class="test" data-opt="1">测试1</button></div>
    		<div class="col-md-4"><button class="test" data-opt="2">测试2</button></div>
    		<div class="col-md-4"><button class="test" data-opt="3">测试3</button></div>
    	</div>
    	<div class="row" >
    	<div class="col-md-12"><a id="test"></a></div>
    	</div>
    </div>
</div>
<div class="container">
	<div class="row">
		<div class="col-md-12">
		1,2,3,4
		</div>
	</div>
<div>
<footerclass="footer">
    <divclass="container">
        <pclass="text-muted">&copy;2016 fyunli</p>
    </div>
</footer>
<script type="text/javascript">
	$(document).ready(function(){
		$(".test").click(function(){
			if($(this).attr("data-opt")=="1"){
				getData(1);
			}
			if($(this).attr("data-opt")=="2"){
				getData(2);
			}
			if($(this).attr("data-opt")=="3"){
				getData(3);
			}
		});
		var getData=function(opt){
		$.ajax({
			type:"GET",
			url:"../api/city",
			dataType:"json",
			data:{"cityName":"3","opt":opt},
			success:function(data,textStatus){
				$("#test").text(data.cityName);
			},
			error:function(data,textStatus){
			
			}
		});
		}
		getData("2");
	});

</script>
</body>
</html>  