<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<meta HTTP-EQUIV="Expires" CONTENT="0"> 
<title>购物车</title>
<style type="text/css">
	#con{padding-top:50px;height:600px}
</style>
</head>
<body>
	
	<%@ include file="head.jsp" %>
		<div class="container" id="con">
	   	<div class="navbar navbar-inverse ">
              <div class="navbar-inner">
                <a href="#" class="brand" >工艺品商城</a>
                <div class="nav-collapse collapse jumbotron subhead">
                  <ul class="nav" >
                    <li class="active"><a href="index.action">首页</a></li>
                    <s:iterator value="seconds" var="second">
                    <li class="dropdown">
                      <a data-toggle="dropdown" class="dropdown-toggle" href="#"><s:property value="#second.typenName"/><b class="caret"></b></a>
                      <ul class="dropdown-menu">
                      	<s:iterator value="#second.firstLevelTypes" var="first">
                        <li>
                        <s:a action="proAction_search.action">
							<s:property value="#first.typenName"/>
							<s:param name="typeId" value="#first.firstId"/>
						</s:a>
						</li>
                        </s:iterator>
                      </ul>
                    </li>
                    </s:iterator>
                    
                  </ul>
                </div><!--/.nav-collapse -->
              </div><!-- /.navbar-inner -->
            </div>
           
           
           
           <!-- 遍历购物车 -->
           
               <table class="table table-hover ">
			    <thead>
				    <tr>
				    <th>
				    <label class="checkbox">
					<input style="margin-top:10px" onclick="checkedAll()" type="checkbox" value="">
					</label>
					</th>
				    <th>图片</th>
				    <th>商品名</th>
				    <th>单价</th>
				    <th>数量</th>
				    <th>小计</th>
				    <th>操作</th>
				    </tr>
			    </thead>
			    <tbody>
			    	<s:iterator value="carts" var="cart">
			    	
				    <tr>
				    <td><label class="checkbox">
					<input type="checkbox" name="check" value="<s:property value='#cart.shopCartId' />" onclick="checkout(this)">
					</label>
					</td>
				    <td><img  data-src="holder.js/300x200" style="height:60px;width:80px" src='<s:property value="'upImage/'+#cart.product.pictureFileName"/>' /></td>
				    <td><s:property value="#cart.product.productName"/></td>
				    <td><s:property value="#cart.product.price"/></td>
				    <td>
				    <a class="minus"  href="###" onclick="minus(this,<s:property value='#cart.shopCartId' />)"><span style="font-size: 16px">-</span></a>
				    <input autocomplete="off"  type="text" style="width:20px;margin:5px;height:10px" value="<s:property value='#cart.count' />">
				    <a class="add" href="###" onclick="plus(this,<s:property value='#cart.shopCartId' />)"><span style="font-size: 16px">+</span></a>
				    </td>
				    <td><s:property value="#cart.totalPrice"/></td>
				    <td><a href="###" onclick="deleteCart(<s:property value='#cart.shopCartId' />)">删除</a></td>
				    </tr>
				    </s:iterator>
			    </tbody>
			    </table>
			    <s:url id="pageAction" includeContext="false" action="cartAction_cartInit.action" namespace="/">
			    </s:url>
			    <s:include value="/pageUtil.jsp"></s:include>
           	<s:property value="totalPrice"/><button class="btn btn-large btn-primary" type="button" onclick="buy()">结算</button>
           	<div style="float:right;width:200px;margin-top:10px;height:50px;font-size:20px;color:red">总价：<span id="total"></span>元</div>
         </div>
         <script type="text/javascript">
         setTotals();
         function plus(a,b){
	         $.post("cartAction_plus.action",{ "cartId": b},function(data){setTotals();});
	         $(a).prev().val(parseInt($(a).prev().val()) + 1);
	         total($(a),$(a).prev().val());
	         
		 }
		 function minus(a,b) {
		 $.post("cartAction_min.action",{ "cartId": b},
		 function(data){
		     if(data.success)
		     {	 $(a).next().val(parseInt($(a).next().val()) - 1);
		     	total($(a),$(a).next().val());
		     	setTotals();
		     }
		     else{
		    	alert("数量不能小于0");
		     }
		   }, "json");
         
         
		 }
		 function total(obj,count)
		 {	var a =parseFloat(obj.parent().prev().html());
		 	obj.parent().next().html(a*count);
		 }
		 function deleteCart(obj){
		 	$.post("cartAction_delete.action", { "cartId": obj},
		   function(data){
		     location.reload();
		   }, "json");
		 }
		 var checked = false;
		 function checkedAll()
		 {	var cc = document.getElementsByName("check");
		 	if(checked == false)
		 	{
		 		
		 		for(var i =0;i<cc.length;i++)
		 		{
		 			cc[i].checked = true;
		 		}
		 		checked=true;
		 	}
		 	else {
		 		for(var i =0;i<cc.length;i++)
		 		{
		 			cc[i].checked = false;
		 		}
		 		checked=false;
		 	}
		 	setTotals();
		 }
		 function checkout(obj){
		 setTotals();
		 }
		 
		 function buy()
		 {	
		 	var cc = document.getElementsByName("check");
		 	var ss = "";
		 	for(var i=0; i < cc.length; i++) {
		 					if(cc[i].checked == true)
							ss=ss+cc[i].value+",";
				}		
			 if(""==ss){alert("请至少选择一件物品");return;}
			var param="ss="+ss;
		 	location.href="cartAction_detailAddr?ss="+ss;
		 	
		 }
		 function setTotals()
		 {
		 	var cc = document.getElementsByName("check");
		 	var ss = "";
		 	for(var i=0; i < cc.length; i++) {
		 					if(cc[i].checked == true)
							ss=ss+cc[i].value+",";
						}
			var param="ss="+ss;
			$.ajax({
			   type: "POST",
			   url: "cartAction_getTotals.action",
			   data:param,
			   dataType: 'text',
			   success:function(msg) {
				    		var obj = jQuery.parseJSON(msg);
				    		
				    		$("#total").html(obj.totals);
				    		
			}
			});
		 }
         </script>
		 <div  align="center" > Copyright &copy; 2011-2012 XX互联网服务有限公司 </div>
</html>