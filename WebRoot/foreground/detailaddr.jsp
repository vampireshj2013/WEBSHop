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
 	
 </style>
</head>
<body>
	
	<%@ include file="head.jsp" %>
	<div class="container" id="container">
				
				<div id="my">
				<ul class="nav nav-tabs" id="myTab">
				<li class="active" ><a href="#home" onclick="openL(1)">使用默认信息</a></li>
				<li><a href="#profile" onclick="openL(2)">修改详细信息</a></li>
				</ul>
				 
				<div class="tab-content">
				<div class="tab-pane active" id="home">
					地址：<s:property value="#session.currentUser.addr"/><br>
					邮编：<s:property value="#session.currentUser.postcode" /><br>
					电话：<s:property value="#session.currentUser.tel" /><br>
					<input type="button" style="margin-top:20px" class="btn btn-large btn-primary" value="购买" onclick="buy()"><br></div>
				<div class="tab-pane" id="profile">
					<form action="indentAction_buy.action" id="form1" method="post">
					<input type="hidden" name="ss" value="<s:property value='ss'/>" id="ss"/>
					地址：<input type="text" name="user.addr" id="addr"><br>
					邮编：<input type="text" name="user.postcode" id="postcode"><br>
					电话：<input type="text" name="user.tel" id="tel"><br>
					<input type="hidden" name="productId" value="<s:property value='productId'/>" id="id">
					<input type="button" class="btn btn-large btn-primary" value="购买" onclick="buy2()"><br>
					</form>
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
				
				function buy(){
						if($("#ss").val()=="")
						location.href="indentAction_buy.action?productId="+$("#id").val();
						else{
							var param = "ss="+$("#ss").val();
							$.ajax({
							   type: "POST",
							   url: "cartAction_buy.action",
							   data:param,
							   dataType: 'text',
							   success:function(msg) {
								    		var obj = jQuery.parseJSON(msg);
								    		if(obj.success==true)
								    		{
								    			alert("成功购买");
								    			location.href="cartAction_cartInit.action";
								    		}
								    		else if(obj.success=='overflow')
									    		{
									    			alert("购买数量大于库存，请重新选择数量！");
									    		}
							}
							});
						}
				}
				function buy2(){
					if($("#addr").val()==null||$("#addr").val()=="")
					{
						alert("地址不能为空");
						return;
					}
					else if($("#postcode").val()==null||$("#postcode").val()=="")
					{
						alert("邮编不能为空");
						return;
					}
					else if ($("#tel").val()==null||$("#tel").val()=="")
					{
						alert("联系方式不能为空");
						return;
					}
					if($("#ss").val()=="")
					$("#form1").submit();
					else
					{	
						$.ajax({
								   type: "POST",
								   url: "cartAction_buy.action",
								   data:$("#form1").serialize(),
								   dataType: 'text',
								   success:function(msg) {
									    		var obj = jQuery.parseJSON(msg);
									    		if(obj.success==true)
								    		{
								    			alert("成功购买");
								    			location.href="cartAction_cartInit.action";
								    		}
								    		else if(obj.success=='overflow')
									    		{
									    			alert("购买数量大于库存量，请重新选择！");
									    		}
								}
								});
					}
				}
				</script>
 				</div>
	</div>
	<div  align="center" > Copyright &copy; 2011-2012 XX互联网服务有限公司 </div>
</html>