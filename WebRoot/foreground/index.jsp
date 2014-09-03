<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>首页</title>
    
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
	.container{width:1000px}
	#myCarousel{margin-top:-20px}
	#myCarousel .carousel-caption{position:absolute;top:150px;left:185px;width:500px;background: rgba(14, 54, 54, 0);}
	.thumbnails .thumbnail .pict:HOVER img{opacity:.5;filter:alpha(opacity=50)}
	/* .navbar .nav > li:hover .dropdown-menu {
	display: block;
	} */
	
    </style>
</head>
<body>
<%@ include file="head.jsp" %>
    <div id="myCarousel" class="carousel slide">
    
	    <!-- Carousel items -->
	    <div class="carousel-inner">
		    <div class="active item">
		    	
		    	<div class="carousel-caption">
            	
	              
              <p class="lead">
	            </div>
		        <img alt="" src="img/4.png" style="width:100%;height:500px"/>
		    </div>
		    <div class="item">
			   <img alt="" src="img/8.png" style="width:100%;height:500px"/>
				
            	<div class="carousel-caption">
            	
	              
              <p class="lead">
	            </div>
			</div>
		    <div class="item">
		       <img alt="" src="img/9.png" style="width:100%;height:500px"/>
		   <div class="carousel-caption">
            	
	             
              <p class="lead">
	           </div>
		    </div>
	    </div>
    <!-- Carousel nav -->
	    <a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
	    <a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
    </div>
    <div class="container" style="width:1000px;position:absolute;top:80px;left:175px">
    	<div class="navbar navbar-inverse ">
              <div class="navbar-inner">
              
               
                <a href="#" class="brand" >工艺品商城</a>
                <div class="nav-collapse collapse jumbotron subhead">
                  <ul class="nav" >
                    <li class="active"><a href="#">首页</a></li>
                    <s:iterator value="seconds" var="second">
                    <li class="dropdown">
                      <a data-toggle="dropdown" class="dropdown-toggle" href="#"><s:property value="#second.typenName"/><b class="caret"></b></a>
                      <ul class="dropdown-menu">
                      	<s:iterator value="#second.firstLevelTypes" var="first">
                        <li>
                        <s:a action="proAction_search.action" target="target" >
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
    </div>
    		<div class="container">
    		<div>
    		<h5>最新上架</h5>
    		<ul class="thumbnails">
    		<s:iterator value="p_c" var="product" begin="0" end="3">
    			    
    					<li class="span3">
						    <div class="thumbnail">
						    <a href="proAction_proDetail?productId=<s:property value='#product.productId'/>" class="pict" target="_blank">
						    <img  data-src="holder.js/300x200" style="height:200px;width:300px" src='<s:property value="'upImage/'+#product.pictureFileName"/>'   alt="">
						    </a>
						    <p><s:property value="#product.simplyIntro"/></p>
						    </div>
						</li>
				   
    		</s:iterator>
    		 </ul>
    		</div>
    		<div>
    		<h5>最高点击</h5>
    		<ul class="thumbnails">
    		<s:iterator value="p_s" var="product" begin="0" end="3">
    			    
    					<li class="span3">
						    <div class="thumbnail">
						  <a href="proAction_proDetail?productId=<s:property value='#product.productId'/>" class="pict" target="_blank">
						    <img  data-src="holder.js/300x200" style="height:200px;width:300px" src='<s:property value="'upImage/'+#product.pictureFileName"/>'   alt="">
						    </a>
						    <p><s:property value="#product.simplyIntro"/></p>
						    </div>
						</li>
				   
    		</s:iterator>
    		 </ul>
    		</div>
    		<div>
    		<h5>最高成交</h5>
    		<ul class="thumbnails">
    		<s:iterator value="p_i" var="product" begin="0" end="3">
    			    
    					<li class="span3">
						    <div class="thumbnail">
						    <a href="proAction_proDetail?productId=<s:property value='#product.productId'/>" class="pict" target="_blank">
						    <img  data-src="holder.js/300x200" style="height:200px;width:300px" src='<s:property value="'upImage/'+#product.pictureFileName"/>'   alt="">
						    </a>
						    <p><s:property value="#product.simplyIntro"/></p>
						    </div>
						</li>
				   
    		</s:iterator>
    		 </ul>
    		</div>
    		</div>
    		
    		<div class="container" align="center"> Copyright &copy; 2011-2012 XX互联网服务有限公司 </div>
</body>
</html>