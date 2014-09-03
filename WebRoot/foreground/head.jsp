<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>首页</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<script src="js/ui/jquery-1.8.0.min.js"></script>
    <script src="bootstrap/js/bootstrap.js"></script>
    <style type="text/css">
    body{
    font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
    font-size: 13px;}
    .navbar-search .search-btn {
    background-attachment: scroll;
    background-color: #535353;
    background-position: center center;
    background-repeat: repeat;
    border: 0 solid #000000;
    border-radius: 5px;
    font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
    font-size: 13px;
    font-weight: normal;
    line-height: 1;
    margin-bottom: 0;
    padding: 5px 7px;
    text-align: center;
	}
	
    </style>
    <script type="text/javascript">
    function search()
    {	var a = document.getElementById("key");
    	if(a.value!="")
    	{
    		document.getElementById("searchForm").submit();
    	}
    }
    
    </script>
</head>
<body>
<div class="navbar  navbar-inverse navbar-fixed-top"  >
		
			<div class="navbar-inner">
				
					
				<div class="container" style="width:1000px">
								<a class="brand" href="index.action">www.gyp.com</a>
								<div class="nav-collapse pull-right">
			  <ul class="nav">
			             		 <li>
			               		 <form class="navbar-search pull-left" id="searchForm" action="proAction_search.action" >
			                   <input type="text" name ="key"  id="key" value="<s:property value='key'/>" placeholder="Search" class="search-query">
			                   <input type="hidden" value="1" name="price">
			                   <input type="hidden" value='<s:property value="typeId"/>' name="type">
			                   <button type="button" class="search-btn" onclick="search()"><i class="icon-search icon-white"> &nbsp;</i></button>
			                 </form>
				                </li>
				                
				                
				                <!-- 用户已经登陆 -->
				<s:if test="#session.currentUser!=null">
				<li ><p class="navbar-text">&nbsp;&nbsp;欢迎您,<b><s:property  value="#session.currentUser.username"/></b></p></li>
				<li><a onclick="exit(this)" href="#">退出</a></li>
				<li class="">
				   <s:a id="shopcart" href="cartAction_cartInit.action"><i class="icon-shopping-cart icon-white"></i> 购物车</s:a>
				</li>
				<li>
				<s:a action="indentAction_foreIndentInit.action" >我的订单</s:a>
				</li>
				<li class="">
				                <s:a href="modifyUser_modifyInit.action">个人中心</s:a>
				 </li>	
				</s:if>
				<!-- 用户未登录 -->
				<s:else>
					
					<li class="">
				                <a href="userAction_login.action">登陆</a>
				    </li>
				    <li class=""><!--active-->
				                <a href="userAction_register.action">注册</a>
				    </li>
					
					
				</s:else>
				
			</ul>
		        </div>
				</div>
		    </div>
    </div>
    	
    	
   
</body>
	<script type="text/javascript">
	function exit(obj){
		$.ajax({
			   type: "POST",
			   url: "index_exit.action",
			   success:function(msg) {
				    	
				    		var obj = jQuery.parseJSON(msg);
				    		if(obj && obj.success) {
				    				alert("成功退出");
				    				window.location.reload();
					    	}
				    		
			}
			});
	}
	</script>
</html>