<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>报表总结</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="./theme/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
  	<div class="container">
  		<div style="padding:50px">
  			<form action="ExcelFilter"  enctype="multipart/form-data" method="post">
  				<div class="form-group">
    				<label for="exampleInputFile">File input</label>
    				<input type="file" name="file"/>   				
  				</div>  									
				<button type="submit" class="btn btn-primary">提交</button> ${result}				    				 
     		</form> 
     	</div>		   	
  	</div>   
    <script src="./theme/js/jquery-1.10.2.min.js"></script>
	<script src="./theme/js/bootstrap.min.js"></script>  
  </body>
</html>
