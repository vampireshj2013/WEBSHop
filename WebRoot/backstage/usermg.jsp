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
		性别:<input id="us" class="easyui-combobox"  data-options="data:[{name:'男',value:'男',selected:true},{name:'女',value:'女'}], valueField:'value', textField:'name', panelHeight:'auto', editable:true, required:false" />
		用户名：<input id="username" class="easyui-validatebox" style="width:120px" data-options="required:false" />
		<a href="#" id="btnQuery" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
		<a href="#" id="btnDelete" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a> 
	</div>
	
</div>
<div id="win" class="easyui-window" data-options="closed:true,resizable:false"></div>
<script type="text/javascript">
	$('#dg').datagrid({
	    url:"/web/userAction_usermg.action",
		toolbar: '#tb',
		border: false,
	    fitColumns: true,
	    fit: true,
	    singleSelect: false,
		loadMsg:'加载中...',
		pagination: true,
		rownumbers: true,
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
		columns:[[
			    	{field:'ck',checkbox:true},
			        {field:'userId',title:'ID',width:100,formatter: function(value,row,index) {
						if("" != value) {
			    			return "<span title='" + value + "'>" + value + "</span>";
			    		} else {
			    			return "--";
			    		}
					}},
			        {field:'username',title:'用户名',width:100,formatter: function(value,row,index) {
						if("" != value) {
			    			return "<span title='" + value + "'>" + value + "</span>";
			    		} else {
			    			return "--";
			    		}
					}},
					{field:'sex',title:'性别',width:100,formatter: function(value,row,index) {
						if("" != value) {
			    			return "<span title='" + value + "'>" + value + "</span>";
			    		} else {
			    			return "--";
			    		}
					}},
					{field:'age',title:'年龄',width:100,formatter: function(value,row,index) {
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
			    			return "--";
			    		}
					}},
			        {field:'tel',title:'电话',width:100,formatter: function(value,row,index) {
						if("" != value) {
			    			return "<span title='" + value + "'>" + value + "</span>";
			    		} else {
			    			return "--";
			    		}
					}},
			        {field:'postcode',title:'邮编',width:100,formatter: function(value,row,index) {
						if("" != value) {
			    			return "<span title='" + value + "'>" + value + "</span>";
			    		} else {
			    			return "--";
			    		}
					}}
					
					
			    ]]
	}); 
	// 查询
	$('#btnQuery').bind('click', function() {
        $('#dg').datagrid('load', {
        	 'user.username': $('#username').val(),
        	 'user.userId': $('#id').val(),
        	 'user.sex': $('#us').combobox('getValue')

		});
       
    });
	//删除
	$('#btnDelete').bind('click', function() {
    	var opt = $('#btnDelete').linkbutton('options');
    	if(!opt.disabled) {
    		var rows = $('#dg').datagrid('getSelections');
    		
	    		$.messager.confirm('删除上线计划信息', '确定要删除该记录吗?', function(r) {
					if (r) {
						var ss = [];
						for(var i=0; i < rows.length; i++) {
							var row = rows[i];
							ss.push(row.userId);
						}
						var params = {
							'ids':ss.join(',')
						}
						$.ajax({
							type: 'post',
							url: 'web/userAction_delUsers.action',
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
		if(rows.length == 0) {
			$('#btnDelete').linkbutton('disable');
		} else if(rows.length >= 1) {
			$('#btnDelete').linkbutton('enable');
		} else {
			$('#btnDelete').linkbutton('disable');
		}
	}


</script>
</body>

</html>