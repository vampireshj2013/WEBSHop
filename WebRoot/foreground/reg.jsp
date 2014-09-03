<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>注册页面</title>
      	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<script src="js/ui/jquery-1.8.0.min.js"></script>
    <script src="bootstrap/js/bootstrap.js"></script>
    
	<script type="text/javascript">
	function register(){
			if($('#username').val()==null||$('#username').val() == "")
			{
				alert("用户名不能为空！");
			}
			else if($('#password').val()== null||$('#password').val() == "")
			{
				alert("密码不能为空！");
			}
			else if($('#tel').val()== null||$('#tel').val() == "")
			{
				alert("联系方式不能为空！");
			}
			else if($('#postcode').val()== null||$('#postcode').val() == "")
			{
				alert("邮编不能为空！");
			}
			else if($('#addr').val()== null||$('#addr').val() == "")
			{
				alert("地址不能为空！");
			}
			else if($('#age').val()== null||$('#age').val() == "")
			{
				alert("年龄不能为空！");
			}
			else if($('#confirm').val()==null||$('#confirm').val() =="")
			{
				alert("请输入验证密码");
			}
			else if($('#confirm').val()!=$('#password').val())
			{
				alert("两次输入的密码不一致");
			}
			 else if(!(/^\d{11}$/).test($('#tel').val())&&$('#tel').val()!=null&&$('#tel').val() != "")
			{	
				alert("手机号不正确，请确认！");
			}
			else if(!(/^\d{6}$/).test($('#postcode').val())&&$('#postcode').val()!=null&&$('#postcode').val()!="")
			{	
				alert("邮编不正确，请确认！");
			}
			else if(!(/^\d{1,3}$/).test($('#age').val())&&$('#age').val()!=null&&$('#age').val()!="")
			{	
				alert("年龄不正确，请确认！");
			}
		else {
			$.ajax({
			   type: "POST",
			   url: "userAction_checkRegister.action",
			   data: "username="+$('#username').val(),
			   success:function(msg) {
				    	
				    		var obj = jQuery.parseJSON(msg);
				    		if(obj && obj.success) {
						    	document.getElementById("form1").submit();

					    	}
				    		else{
				    			
				    			alert("用户名已存在");
				    			
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
	#username,#password,#tel,#age,#postcode,#confirm{height:30px}
	</style>
  </head>
  
  <body style="background-color: #161616">
  
    
    
    
    <div class="cc" style="background-color: #FFFFFF;position:absolute;top:20px;left:400px;width:500px;height:600px">
        <form class="form-horizontal" style="align:center" action="userAction_registerAfter" method="post" id="form1">
   
    	<div style="text-align:center"><h2>注册</h2><br/></div>
      	 <div class="control-group">
		<label class="control-label" for="username">用户名</label>
			<div class="controls">
				<input type="text" id="username" name="username"   placeholder="用户名"/>
			</div>
		</div>
		 <div class="control-group">
		<label class="control-label" for="password">密码</label>
			<div class="controls">
				<input type="password" id="password" name="password"   placeholder="密码"/>
			</div>
		</div>
		 <div class="control-group">
		<label class="control-label" for="confirm">确认密码</label>
			<div class="controls">
				<input type="password" id="confirm" name="confirm"   placeholder="确认密码"/>
			</div>
		</div>
		 <div class="control-group">
		<label class="control-label" for="tel">联系方式</label>
			<div class="controls">
				<input type="text" id="tel" name="tel"   placeholder="联系方式"/>
			</div>
		</div>
		 <div class="control-group">
		<label class="control-label" for="postcode">邮编</label>
			<div class="controls">
				<input type="text" id="postcode" name="postcode"   placeholder="邮编"/>
			</div>
		</div>
		<div class="control-group">
		<label class="control-label" for="age">年龄</label>
			<div class="controls">
				<input type="text" id="age" name="age"   placeholder="年龄"/>
			</div>
		</div>
		<div class="control-group">
		<label class="control-label" for="addr">地址</label>
			<div class="controls">
		<textarea rows="3" name="addr" id="addr">地址</textarea>
			</div>
		</div>
		<div class="control-group">
		<label class="control-label" for="sex">性别</label>
			<div class="controls">
				<label class="radio">
				<input type="radio" name="sex" id="optionsRadios1" value="男" checked>男
				</label>
				<label class="radio">
				<input type="radio" name="sex" id="optionsRadios2" value="女">女
				</label>
			</div>
		</div>
	 	
    <div style="text-align:center">
    <button type="button" style="width:200px" class="btn btn-primary" onclick="register()">注册</button>
     <a  class="btn btn-primary" href="index.action">返回首页</a>
    </div>
    </form>
    </div>
    
    
    
    
  </body>
</html>
