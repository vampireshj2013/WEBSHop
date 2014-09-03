<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>search</title>
<style type="text/css">
.foucs{background-color: red}
/* .navbar .nav > li:hover .dropdown-menu {
	display: block;
	} */
	
</style>
</head>
<body>
	
	<jsp:include page="head.jsp"></jsp:include>
	<input type="hidden" value='<s:property value="typeId"/>' id="type1" />
	<div class="container" style="width:1000px;position:absolute;top:80px;left:175px">
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
            	<s:if test="flag==0">
            	<div style="width:60px;height:20px;background-color: #E9E9E9">相关类别</div>
            	<div id="type" style="width:1000px;height:70px;border:1px solid #E9E9E9">
            		<s:iterator value="#session.relative" var="first">
            			<s:a action="proAction_searchRes" target="frame" onclick="setType(this)">
							<s:property value="#first.typenName"/>
							<s:param name="typeId"  value="#first.firstId"/>
							<s:param name="key"  value="#root[1].key"/>				
						</s:a>
            		</s:iterator>
            	</div>
            	
            	</s:if>
            	<s:else>
            	<div style="width:200px;height:20px;background-color: #E9E9E9">当前查询类别:<s:property value="firstType.typenName"/></div>
            	</s:else>
                <div class="btn-toolbar">
				    
				   <div class="btn-group">
				    <button class="btn" onclick="searchByRelative(this)" id="s1"><i class="icon-arrow-up"></i> 价格</button>
				   	<button class="btn" onclick="searchByRelative(this)" id="s2"><i class="icon-arrow-down"></i> 价格</button>
				    <button class="btn" onclick="searchByRelative(this)" id="s3"><i class="icon-arrow-up"></i> 人气</button>
				    <button class="btn" onclick="searchByRelative(this)" id="s4"><i class="icon-arrow-down"></i> 人气</button>
				    </div>
				 </div>
    <iframe name="frame"  id="frame" width=1000 height=880 frameborder=0 scrolling=no src="proAction_toSerchRes.action">
       		
     </iframe>
                   	
      <div  align="center" > Copyright &copy; 2011-2012 XX互联网服务有限公司 </div>
    
    
    </div>
        
</body>
<script type="text/javascript">
function setType(obj){
	href = obj.href;
	var start = href.indexOf("=")+1;
	var end = href.indexOf("&")-start;
	var value = href.substr(start,end);
	$("#type1").attr("value",value);
            		
}
	function searchByRelative(obj){
	if(obj.id =='s1')
	{	
		var url = "proAction_searchRes.action?key="+$('#key').val()+"&typeId="+$('#type1').val()+"&price=1";
		$("#frame").attr("src",url);
	}
	if(obj.id =='s2')
	{
		var url = "proAction_searchRes.action?key="+$('#key').val()+"&typeId="+$('#type1').val()+"&price=2";
		$("#frame").attr("src",url);
	}
	if(obj.id =='s3')
	{
		var url = "proAction_searchRes.action?key="+$('#key').val()+"&typeId="+$('#type1').val()+"&scans=1";
		$("#frame").attr("src",url);
	}
	if(obj.id =='s4')
	{
		var url = "proAction_searchRes.action?key="+$('#key').val()+"&typeId="+$('#type1').val()+"&scans=2";
		$("#frame").attr("src",url);
	}
	 
}
	
</script>
</html>