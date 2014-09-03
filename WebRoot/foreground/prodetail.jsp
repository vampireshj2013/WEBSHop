<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<script src="js/ui/jquery-1.8.0.min.js"></script>
    <script src="bootstrap/js/bootstrap.js"></script>
<title>商品详情</title>
<style type="text/css">
	body{background-color: #F0F0F0}
	#container1{margin-top:50px;width:1000px;font-family: "微软雅黑";}
	#container1 span{font-size: 20px}
	#container{
	background-color:#FFF;
	width:1000px;
	margin-top:10px;
	border:1px solid #CCCCCC;
	padding-bottom: 50px;
	margin-bottom: 50px;
	}
	#left{padding:5px}
	#right{width:640px}
	#right,#left{float:left}
	#right ul li{list-style-type: none;font-family: "微软雅黑";font-size:16px;line-height: 30px}
	#detail{
	width:350px;
	height:400px
	}
</style>
</head>
<body>
	
	<%@ include file="head.jsp" %>
	<div class="container" id="container1"><span><s:property value="product.type.secondLevelType.typenName" /></span>&nbsp;>&nbsp;<s:property value="product.type.typenName" /></div>
	<div class="container" id="container">
	<div id="left">
		<img src='<s:property value="'upImage/'+product.pictureFileName"/>' id="detail" /> 
	</div>
	<div id="right">
		
			<ul>
				<li>商品名称：<s:property value="product.productName"/></li>
				<li>商品价格：<s:property value="product.price"/></li>
				<li>简介：<s:property value="product.simplyIntro"/></li>
				<li>材质：<s:property value="product.materia"/></li>
				<li>库存量：<s:property value="product.remain"/></li>
				<li>上架时间:<s:property value="product.carrDate"/></li>
				<li>生产日期：<s:property value="product.manuDate"/></li>
				<li>尺寸：<s:property value="product.size"/></li>
				<li>包装：<s:property value="product.pack"/></li>
				<li>工艺：<s:property value="product.craft"/></li>
				<li>品牌：<s:property value="product.brand"/></li>
				<li>产地：<s:property value="product.proAddr"/></li>
				<li>加印LOGO：<s:property value="product.logo"/></li>
				<li>表面工艺：<s:property value="product.faceCraft"/></li>
			</ul>
			<button type="button" style="width:150px" class="btn btn-primary" onclick="islogin2()">购买</button>
			<button  style="width:150px" type="button"  class="btn btn-primary" onclick="islogin(this)">
			加入购物车</button>
	</div>
	</div>
	<div  align="center" > Copyright &copy; 2011-2012 XX互联网服务有限公司 </div>
	<!-- Modal -->
	<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		<h3 id="myModalLabel">登陆</h3>
		</div>
		<div class="modal-body">
			 <form action="userAction_loginAfter.action" method="post" id="form1">
      			用户名<br/>
      			<input type="text" id="username" name="username"   placeholder="用户名"/>
	   			<br/>
	 	 		密码<br/><input type="password" id="password" name="password" placeholder="密码" />	   
    			<br/>
    			<button type="button" style="width:100px" class="btn btn-primary" onclick="login()">登陆</button>
    			<br/>
    		</form>
		</div>
		<div class="modal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
		</div>
	</div>
	<script type="text/javascript">
		function islogin(obj)
		{
			$.ajax({
			   type: "POST",
			   url: "proAction_addToshopcart.action?productId=<s:property value='product.productId'/>",
			   success:function(msg) {
				    		var obj = jQuery.parseJSON(msg);
				    		if(obj && obj.success) {
						    	alert("成功加入购物车");

					    	}
				    		else{
				    			$('#myModal').modal('show');
				    		}
			}
			});
		}
		function islogin2()
		{
			$.ajax({
			   type: "POST",
			   url: "indentAction_isLogin.action",
			   success:function(msg) {
				    		var obj = jQuery.parseJSON(msg);
				    		if(obj && obj.success) {
								location.href="indentAction_detailaddr.action?productId=<s:property value='product.productId' />";
					    	}
				    		else{
				    			$('#myModal').modal('show');
				    		}
			}
			});
		}
		function login()
		{
			$.ajax({
			   type: "POST",
			   url: "userAction_checkForCart.action?username="+$('#username').val()+"&password="+$('#password').val(),
			   success:function(msg) {
				    	
				    		var obj = jQuery.parseJSON(msg);
				    		if(obj && obj.success) {
				    				alert("成功登陆");
				    				window.location.reload();
					    	}
				    		else{
				    			alert("用户名或者密码错误");
				    		}
			}
			});
		}
	</script>
</html>