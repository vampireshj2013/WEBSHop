<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>易迅购物</title>
	<style type="text/css">
	*{margin:0;padding:0;}
	
	#item_3{border:1px solid blue;overflow:hidden;background-color:#1D7AD9;width:850px;margin:30px 0;}
	#item_1{position:relative; padding:5px 20px;}
	#item_1 ul li {list-style:none;float:left;margin-left:10px;}
	#item_1 ul{ float:left;overflow:hidden;}
	#item_2{background-color:#FFF;padding:20px 0;}
	#center{width:850px;margin-top:50px;margin:0 auto;}
	
	#second_left{float:left;width:108px;font-weight:bold;color:#1d7ad9}
	.clear{clear:both;}
	#left{line-height:106px;height:106px;color:#fff;font-weight:bold;}
	#item_2,#left{float:left;}
	#center a{text-decoration:none;}
	#center a:hover{text-decoration:underline;}
	#index_con{width:1050px;margin:0 auto;border:1px solid red;overflow:hidden;}
	#index_left,#index_right{float:left;}
	#index_right{width:190px;margin:0 4px;border:1px solid black;}
	.most_scans_head{font-size:18px;font-family:"微软雅黑",Arial;padding:5px 0;text-align:center;background-color:#1369C0;}
	</style>
  </head>
  
  <body>
    <s:include value="header.jsp"></s:include>
   
     
    <div id="index_con">
		    <div id="index_left">
						    <img src="img/bigpro.jpg"/>
						    <div id="center">
										
										<s:iterator value="thirds"   var="third">
											<div id="item_3">
												
												
												<div id="left"><s:property value="#third.typenName" /></div>
												<div id="item_2">
													<s:iterator value="#third.getSecondLevelTypes()" var="second" >
														<div id="item_1">
														<div id="second_left"><s:property value="#second.getTypenName()"/></div>
														<ul id="second_right">
														<s:iterator value="#second.getFirstLevelTypes()" id="first">
														
															<li>
																<s:a action="typeAction_setType">
																	<s:property value="#first.getTypenName()"/>
																	<s:param name="firstId" value="#first.firstId"/>
																	
																</s:a>
															</li>
															
														</s:iterator>
															
														</ul>
														<div class="clear"></div>	
														</div>
													</s:iterator>
												</div>
												
											</div>
											
										</s:iterator>
										
										
								</div>
			</div>
		    <div id="index_right">
				    
				    <div class="most_scans">
				    		<div class="most_scans_head">人气商品</div>
				    		<s:iterator value="proFromScans" id="pro">
				    		
				    		<s:a action="proAction_detail">
				    		<s:property value="pro.productName"/>
				    		<s:param name="productId" value="pro.productId"></s:param>
				    		</s:a>
				    		</s:iterator>
				    </div>
				    <div class="most-deal">
				    		<div class="most_scans_head">热销商品</div>
				    		<s:iterator value="proFromIndent" id="pro">
				    		
				    		<s:a action="proAction_detail">
				    		<s:property value="pro.productName"/>
				    		<s:param name="productId" value="pro.productId"></s:param>
				    		</s:a>
				    		</s:iterator>
				    </div>
		    </div>
    </div>
    <s:include value="foot.jsp"></s:include>
  </body>
</html>
