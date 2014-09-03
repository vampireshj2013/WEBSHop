<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>商品详情</title>
<style type="text/css">
	#container{
	margin-top:50px;
	
	height:500px;
	}
	#my{
	width:500px;
	height:300px;
	position:absolute;
	top:100px;
	left:450px;}
 	#ul1,#ul2{
 	list-style-type: none
 	}
 	#ul1 li,#ul2 li{text-align:right;width:300px}
 </style>
 	
</head>
<body>
	
	<%@ include file="head.jsp" %>
	
	<div class="container" id="container">
				
				<div id="my">
				<ul class="nav nav-tabs" id="myTab">
				<li class="active" ><a href="#home" onclick="openL(1)">基本信息</a></li>
				<li><a href="#profile" onclick="openL(2)">修改密码</a></li>
				</ul>
				 
				<div class="tab-content">
				<div class="tab-pane active" id="home">
				<form method="post" action="modifyUser_modify.action" id="form1">
				<ul id="ul1">
				<li><span>用户名：</span><input type="text" id="username" name="user.username" value="<s:property value='user.username'/>" /></li>
				<li><span>收货地址：</span><input type="text" id="addr" name="user.addr"  value="<s:property value='user.addr'/>" /></li>
				<li><span>电话：</span><input type="text"  id="tel" name="user.tel" value="<s:property value='user.tel'/>" /></li>
				<li><span>邮编：</span><input type="text" id="postcode" name="user.postcode" value="<s:property value='user.postcode'/>" /></li>
				<li><span>性别：</span>
				<select name="user.sex">
				<option value="男" id="option1">男</option>
				<option value="女" id="option2">女</option>
				</select>
				</li>
				<li><span>年龄：</span><input type="text" id="age" name="user.age" value="<s:property value='user.age'/>" />
				</li>
				<li><input type="button" class="btn btn-primary" value="修改" onclick="change()"></li>
				</ul>
				
				</form>
				</div>	
				<div class="tab-pane" id="profile">
					<ul id="ul2">
						<li>新密码：<input type="password" id="pass"  /></li>
						<li>确认密码：<input type="password" id="confirm"/></li>
						<li><input type="button" class="btn btn-primary" value="修改密码" onclick="changePass()"></li></li>
					</ul>
				</div>
				</div>
				<script>
				$('#myTab a:first').tab('show');
				function openL(i)
				{
					if(i==1)
						$('#myTab a:first').tab('show');
					else if(i==2)
						$('#myTab a:last').tab('show');
				}
				function change()
				{
					if($('#username').val()==null||$('#username').val() == "")
					{
						alert("用户名不能为空！");
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
					$.ajax({
							   type: "POST",
							   url: "modifyUser_modify",
							   data:$('#form1').serialize(),
							   dataType: 'text',
							   success:function(msg) {
								    		var obj = jQuery.parseJSON(msg);
								    		if(obj&&obj.success)
								    		{
								    			alert("修改成功");
								    			location.reload();
								    		}
								    		else {alert("修改失败");}
							}
							});
				}
				
				function changePass()
				{
					 if($('#pass').val()== null||$('#pass').val() == "")
					{
						alert("密码不能为空！");
					}
					else if($('#confirm').val()==null||$('#confirm').val() =="")
					{
						alert("请输入验证密码");
					}
					else if($('#confirm').val()!=$('#pass').val())
					{
						alert("两次输入的密码不一致");
					}
					var param ="user.password="+$('#pass').val();
					$.ajax({
							   type: "POST",
							   url: "modifyUser_modifyPass",
							   data:param,
							   dataType: 'text',
							   success:function(msg) {
								    		var obj = jQuery.parseJSON(msg);
								    		if(obj&&obj.success)
								    		{
								    			alert("修改成功");
								    			location.href="modifyUser_modifyInit.action#profile";
								    		}
								    		else {alert("修改失败");}
							}
							});
				}
				</script>
 				</div>
	</div>
	<div  align="center" > Copyright &copy; 2011-2012 XX互联网服务有限公司 </div>
	<script type="text/javascript">
 if("<s:property value='user.sex'/>"=="男")
 	{document.getElementById("option1").selected=true; }
 else
 	{
 	document.getElementById("option2").selected=true;
 	 }
 </script>
</html>