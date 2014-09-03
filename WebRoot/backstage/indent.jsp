<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title></title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
</head>
 
<body>
<table id="dg"></table>
<div id="tb">
	<div>
		ID：<input id="id" class="easyui-validatebox" style="width:120px" data-options="required:false" />
		商品名称：<input id="proname" class="easyui-validatebox" style="width:120px" data-options="required:false" />
		用户名称：<input id="username" name="username" class="easyui-validatebox" data-options="required:false"/>
		<a href="#" id="btnQuery" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
		<a href="#" id="btnModify" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">发货</a>
		<a href="#" id="btnDelete" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>  
	</div>
	
</div>
<div id="win" class="easyui-window" data-options="closed:true,inline:true,resizable:false"></div>
<script type="text/javascript">

$('#dg').datagrid({
	    url:"/web/indentAction_findAllIndent.action",
		toolbar: '#tb',
		border: false,
	    fitColumns: true,
	    fit: true,
	    singleSelect: false,
		loadMsg:'加载中...',
		height:300,
		autoRowHeight: false,
		pagination:true,
		pagePosition:'bottom',
		pageList:[5,10,15,20],
		pageSize:10,
		rownumbers:true,
		onLoadError: function(data) {
			var content = '<iframe scrolling="auto" frameborder="0" src="/web/error.jsp" style="width:100%;height:100%;overflow-x:hidden;overflow:auto;"></iframe>';
		   	top.$('#winErr').window({
			    content: content
			});
			top.$('#winErr').window('center');
		    top.$('#winErr').window('open');
		},
		onLoadSuccess : function(data) {
			checkEditDelBtnStatus();
		},
		onClickRow : function(rowIndex, rowData) {
			checkEditDelBtnStatus();
		},
		onCheck: function(rows) {
			checkEditDelBtnStatus();
		},
		onUncheck: function(rowIndex,rowData) {
			checkEditDelBtnStatus();
		},
		onCheckAll: function(rows) {
			checkEditDelBtnStatus();
		},	
		onUncheckAll: function(rows) {
			checkEditDelBtnStatus();
		},
		columns:[[/*{"addr":"","indentId":1,"message":0,"postcode":0,"product":{"productName":"手机","productId":"2"},"tel":0,"user":{"username":"wr","userId":"1"}}*/
			    	{field:'ck',checkbox:true},
			        {field:'indentId',title:'ID',width:100,formatter: function(value,row,index) {
						if("" != value) {
			    			return "<span title='" + value + "'>" + value + "</span>";
			    		} else {
			    			return "--";
			    		}
					}},
			        {field:'addr',title:'地址',width:100,formatter: function(value,row,index) {
						if("" != value) {
			    			return "<span title='" + value + "'>" + value + "</span>";
			    		} else {
			    			return  "默认";
			    		}
					}},
					{field:'postcode',title:'邮编',width:100,formatter: function(value,row,index) {
						if("" != value) {
			    			return "<span title='" + value + "'>" + value + "</span>";
			    		} else {
			    			return "默认";
			    		}
					}},
					{field:'product',title:'商品名称',width:100,formatter: function(value,row,index) {
						if("" != value) {
			    			return "<span title='" + value.productName + "'>" + value.productName + "</span>"
			    			+"<input type='hidden' name='productId' value='"+value.productId+"'/>";
			    		} else {
			    			return "--";
			    		}
					}},
					{field:'user',title:'用户名称',width:100,formatter: function(value,row,index) {
						if("" != value) {
			    			return "<span title='" + value.username + "'>" + value.username + "</span>"
			    			+"<input type='hidden' name='userId' value='"+value.userId+"'/>";
			    		} else {
			    			return "--";
			    		}
					}},
			        {field:'tel',title:'电话',width:100,formatter: function(value,row,index) {
						if("" != value) {
			    			return "<span title='" + value + "'>" + value + "</span>";
			    		} else {
			    			return "默认";
			    		}
					}},
			        {field:'message',title:'订单状态',width:100,formatter: function(value,row,index) {
						if("" != value) {
							if(value == '1'){
								return "<span title='" + value + "' style='background-color:green'>" + "已付款" + "</span>";
								
							}
							else if(value == '0')
							{
								return "<span title='" + value + "'>" + "未付款" + "</span>";
							}
							else if(value =='2')
							{
								return "<span title='" + value + "'>" + "已发货" + "</span>";
							}
							else if(value == '3')
							{
								return "<span title='" + value + "'>" + "确认收货" + "</span>";
							}
			    		} else {
			    			return "--";
			    		}
					}}
					
			    ]]
	}); 
		
			/*queryParams:{
			 'product.manuDate': $('#pro_manuDate').datebox('getValue'),
			 'product.productName': $('#proname').val(),
	       	 'product.productId': $('#id').val()
       	 
		},*/
	
	// 查询
	$('#btnQuery').bind('click', function() {
        $('#dg').datagrid('load', {
        	 'indent.product.productName': $('#proname').val(),
        	 'indent.indentId': $('#id').val(),
        	 'indent.user.username': $('#username').val()
		});
       
    });
    //删除
	$('#btnDelete').bind('click', function() {
    	var opt = $('#btnDelete').linkbutton('options');
    	if(!opt.disabled) {
    		var rows = $('#dg').datagrid('getSelections');
    		
	    		$.messager.confirm('删除订单', '确定要删除该记录吗?', function(r) {
					if (r) {
						var ss = [];
						for(var i=0; i < rows.length; i++) {
							var row = rows[i];
							ss.push(row.indentId);
						}
						var params = {
							'ids':ss.join(',')
						};
						$.ajax({
							type: 'post',
							url: '/web/indentAction_deleteIndent.action',
							data: params,
							dataType: 'text',
							success:function(data, textStatus) {
								$('#dg').datagrid('reload');
							}
						});
					}
				});
	    	
		}
    });
	//发货
	$('#btnModify').bind('click', function() {
    	var opt = $('#btnModify').linkbutton('options');
    	if(!opt.disabled) {
    		var rows = $('#dg').datagrid('getSelections');
    		
	    		$.messager.confirm('发货', '确定要发货吗?', function(r) {
					if (r) {
						var ss = [];
						for(var i=0; i < rows.length; i++) {
							var row = rows[i];
							ss.push(row.indentId);
						}
						var params = {
							'ids':ss.join(',')
						}
						$.ajax({
							type: 'post',
							url: '/web/indentAction_onePlus.action',
							data: params,
							dataType: 'text',
							success:function(data, textStatus) {
								$('#dg').datagrid('reload');
							},
							error:function() {
								var content = '<iframe scrolling="auto" frameborder="0" src="/web/error.jsp" style="width:100%;height:100%;overflow-x:hidden;overflow:auto;"></iframe>';
							   	top.$('#winErr').window({
								    content: content
								});
								top.$('#winErr').window('center');
							    top.$('#winErr').window('open');
							}
						})
					}
				});
	    	
		}
    });
	
	function checkEditDelBtnStatus() {
		
		var rows = $('#dg').datagrid('getSelections');
		var bol = true;
		if(rows.length >= 1) {
			$('#btnDelete').linkbutton('enable');
			for(var i = 0;i<rows.length;i++)
				{
					if(rows[i].message!= 1)
						{
							bol = false;
							break;
						}
				}
			if(bol) $('#btnModify').linkbutton('enable');
			else if(!bol) $('#btnModify').linkbutton('disable');
		}  else {
			$('#btnModify').linkbutton('disable');
			$('#btnDelete').linkbutton('disable');
		}
	}


</script>
</body>

</html>