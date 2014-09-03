<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>订单详情</title>
<style type="text/css">
	#container{margin-top:100px}
	
</style>
</head>
<body>
	
	<%@ include file="head.jsp" %>
	<div id="container" class="container">
	<table class="table table-hover ">
			    <thead>
				    <tr>
				    <th>ID</th>
				    <th>图片</th>
				    <th>商品名</th>
				    <th>单价</th>
				    <th>数量</th>
				    <th>小计</th>
				    <th>操作</th>
				    </tr>
			    </thead>
			    <tbody>
			    	<s:iterator value="indents" var="indent">
			    	
				    <tr>
				    <td><s:property value="#indent.indentId"/></td>
				    <td><img  data-src="holder.js/300x200" style="height:60px;width:80px" src='<s:property value="'upImage/'+#indent.product.pictureFileName"/>' /></td>
				    <td><s:property value="#indent.product.productName"/></td>
				    <td><s:property value="#indent.product.price"/></td>
				    <td>
				    <s:property value='#indent.count'/>
				    </td>
				    <td> <s:property value='#indent.totalPrice'/></td>
				    <td>
				    	<s:if test="#indent.message==1">未发货</s:if>
				    	<s:elseif test="#indent.message==2">
				    		<button class="btn  btn-primary" type="button" onclick="received(<s:property value='#indent.indentId'/>)">确认收货</button>
				    	</s:elseif>
				    	<s:if test="#indent.message==3">已确认收货</s:if>
				    </td>
				    </tr>
				    </s:iterator>
			    </tbody>
			    </table>
			    <s:url id="pageAction" includeContext="false" action="indentAction_foreIndentInit.action" namespace="/">
			    </s:url>
			    <s:include value="/pageUtil.jsp"></s:include>
			    </div>
	<div  align="center" > Copyright &copy; 2011-2012 XX互联网服务有限公司 </div>
	<script type="text/javascript">
	
	function received(i){
		$.post("indentAction_foreOnePlus.action",{"id":i},function(){window.location.reload();});
	}
	</script>
	</body>
</html>