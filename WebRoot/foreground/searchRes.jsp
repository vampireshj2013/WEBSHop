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
    <style type="text/css">
    .thumbnails .thumbnail .pict:HOVER img{opacity:.5;filter:alpha(opacity=50)}
    .thumbnails .thumbnail > p{height:40px;font-family:'微软雅黑',arial,sans-serif;}}
    </style>
</head>
<body>
	
		<ul class="thumbnails">
			<s:if test="ps.size()<15">
					<s:iterator value="#session['ps']" var="product">
    			    
    					<li class="span3">
						    <div class="thumbnail">
						    <a href="proAction_proDetail.action?productId=<s:property  value='#product.productId' />" class="pict" target="_blank">
						    <img  data-src="holder.js/300x200" style="height:200px;width:300px" src='<s:property value="'upImage/'+#product.pictureFileName"/>'   alt=""/>
						    
						    </a>
						    <p><s:property value="#product.simplyIntro"/></p>
						    </div>
						</li>
				   
    		</s:iterator>
			
			</s:if>
			<s:elseif test="ps.size==0" >
				<div style="padding-left:20px">抱歉没有为您找到相关产品</div>
			</s:elseif>
			<s:else>
				<s:iterator value="ps" var="product" begin="0" end="3">
    			  
    					<li class="span3">
						    <div class="thumbnail">
						    <s:a href="proAction_proDetail.action" cssClass="pict" target="_blank"><img  data-src="holder.js/300x200" style="height:200px;width:300px" src='<s:property value="'upImage/'+#product.pictureFileName"/>'   alt="">
						    <s:param name="productId" value="#product.productId" />
						    </s:a>
						    <p><s:property value="#product.simplyIntro"/></p>
						    </div>
						</li>
				   
    		</s:iterator>
			</s:else>
    		
       </ul>
       
      
   
        
</body>
</html>