<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>登陆页面</title>
      	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<script src="js/ui/jquery-1.8.0.min.js"></script>
    <script src="bootstrap/js/bootstrap.js"></script>
    
	<script type="text/javascript">
	var request = null;
	function login()
	{		var username = document.getElementById("username").value;
			var password = document.getElementById("password").value;
			
			if(username==null||username == "")
			{
				alert("用户名不能为空！");
			}
			else if(password== null||password == "")
			{
				alert("密码不能为空！");
			}
		else {
			$.ajax({
			   type: "POST",
			   url: "userAction_check",
			   data: "username="+username+"&password="+password,
			   success:function(data) {
				    	
				    		var obj = jQuery.parseJSON(data);
				    		if(obj && obj.success) {
						    	document.getElementById("form1").submit();

					    	}
				    		else{
				    			
				    			alert("用户名或者密码错误");
				    			
				    		}
			}
		
		
	});
	}}
	function resFunc()
	{
		if(request.readyState==4)
		 if(request.status==200)
		 {
		 	var hasUser=request.responseXML.getElementsByTagName("hasuser");
		 	var noUser = request.responseXML.getElementsByTagName("nouser");
		 	//alert(str);
		 	if(typeof(hasUser)!=undefined&&hasUser.length>0)
		 	{
		 		document.getElementById("form1").action="userAction_loginAfter";
		 		document.getElementById("form1").method="post";
		 		document.getElementById("form1").submit();
		 	}
		 	if(typeof(noUser)!=undefined&&noUser.length>0)
		 	{	alert("用户名或者密码错误！");
		 		

		 	}
		 }
	}
	</script>
	
	<style type="text/css">
	#username,#password{height:30px;width:300px}
	</style>
  </head>
  
  <body style="background-color: #161616">
  
    
    
    
    <div class="cc" style="background-color: #FFFFFF;position:absolute;top:50px;left:400px;padding:50px 100px;width:330px;height:400px">
        <form action="index_loginAfter" method="post" id="form1">
    
    <h2>登陆</h2><br/>
      	用户名<br/>
      	<input type="text" id="username" name="username"   placeholder="用户名"/>
	   		<br/>
	 	 密码<br/><input type="password" id="password" name="password" placeholder="密码" />	   
    <br/>
    <button type="button" style="width:100px;margin-right:100px" class="btn btn-primary" onclick="login()">登陆</button>
    <a  class="btn btn-primary" href="index.action">返回首页</a>
    </form>
    </div>
    
    
    
    
  </body>
</html>
