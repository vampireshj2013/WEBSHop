<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/include.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
<title>后台管理</title>
<style type="text/css">
*{margin:0;padding:0
}
.tabs-panels{border:0}
ul.tabs{border:0}
</style>
<script type="text/javascript">
	function usermg(){
		if($('#tt').tabs('exists','用户管理'))
		{
			$('#tt').tabs('select', '用户管理');
		}
		else
		{
			$('#tt').tabs('add',{
			    title:'用户管理',
			    content:'<iframe scrolling="auto" frameborder="0" src="/web/userAction_usermgInit.action" style="width:100%;height:100%;overflow-x:hidden;overflow:auto;"></iframe>',
			    closable:true
			});
		}
	}
	function promg(){
		if($('#tt').tabs('exists','商品管理'))
		{
			$('#tt').tabs('select', '商品管理');
		}
		else
			{
			$('#tt').tabs('add',{
		    title:'商品管理',
		    content:'<iframe scrolling="auto" frameborder="0" src="/web/proAction_promgInit.action" style="width:100%;height:100%;overflow-x:hidden;overflow:auto;"></iframe>',
		    selected:true,
		    closable:true
			});
			}
	}
	function indmg(){
		if($('#tt').tabs('exists','订单管理'))
		{
			$('#tt').tabs('select', '订单管理');
		}
		else
			{
				$('#tt').tabs('add',{
				    title:'订单管理',
				    content:'<iframe scrolling="auto" frameborder="0" src="/web/indentAction_indentInit.action" style="width:100%;height:100%;overflow-x:hidden;overflow:auto;"></iframe>',
				    closable:true
				});
		}
	}
</script>
</head>
<body class="easyui-layout" style="width:980px;margin:0 auto;">

	<div id="win" class="easyui-window" data-options="closed:true,resizable:false"></div>
	
		<div data-options="region:'north'" style="overflow:auto;height:50px;border:0;">
			<div style="width:100%;height:100%;background-image: url('/web/img/back_header.jpg')">
			
			</div>
		</div>
		
		<div data-options="region:'center'" style="width:980px;overflow:hidden">
				<div style="background-color:#C6D3E1">
					<a href="#" class="easyui-linkbutton" data-options="plain:true"  onclick="javascript:$('#mm').menu('show',{left:0,top:77})">菜单</a>
				</div>
				<div id="mm" class="easyui-menu" style="width:120px;">
				      <div><a href="#" class="easyui-linkbutton" data-options="plain:true" onclick="usermg()">用户管理</a></div>
			      	  <div><a href="#" class="easyui-linkbutton" data-options="plain:true" onclick="promg()">商品管理</a></div>
			      	  <div><a href="#" class="easyui-linkbutton" data-options="plain:true" onclick="indmg()">订单管理</a></div>
				</div>
				<div id="tt" class="easyui-tabs" data-options="plain:true" style="height:535px;border:0">
				   
				</div>
		</div>

</body>
</html>