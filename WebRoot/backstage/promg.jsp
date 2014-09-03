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
		生产日期：<input id="pro_manuDate" name="product.manuDate" class="easyui-datebox" required="false"></input>
		<a href="#" id="btnQuery" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
		<a href="#" id="btnAdd" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>
		<a href="#" id="btnModify" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true,disabled:true">修改</a>
		<a href="#" id="btnDelete" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a> 
	</div>
	
</div>
<div id="win" class="easyui-window" data-options="closed:true,inline:true,resizable:false"></div>
<script type="text/javascript">

$('#dg').datagrid({
	    url:"/web/proAction_findAllPro.action",
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
		columns:[[
			    	{field:'ck',checkbox:true},
			        {field:'productId',title:'ID',width:100,formatter: function(value,row,index) {
						if("" != value) {
			    			return "<span title='" + value + "'>" + value + "</span>";
			    		} else {
			    			return "--";
			    		}
					}},
			        {field:'productName',title:'商品名',width:100,formatter: function(value,row,index) {
						if("" != value) {
			    			return "<span title='" + value + "'>" + value + "</span>";
			    		} else {
			    			return "--";
			    		}
					}},
					{field:'price',title:'价格',width:100,formatter: function(value,row,index) {
						if("" != value) {
			    			return "<span title='" + value + "'>" + value + "</span>";
			    		} else {
			    			return "--";
			    		}
					}},
					{field:'manuDate',title:'生产日期',width:100,formatter: function(value,row,index) {
						if("" != value) {
			    			return "<span title='" + value + "'>" + value + "</span>";
			    		} else {
			    			return "--";
			    		}
					}},
					{field:'carrDate',title:'上架日期',width:100,formatter: function(value,row,index) {
						if("" != value) {
			    			return "<span title='" + value + "'>" + value + "</span>";
			    		} else {
			    			return "--";
			    		}
					}},
			        {field:'brand',title:'品牌',width:100,formatter: function(value,row,index) {
						if("" != value) {
			    			return "<span title='" + value + "'>" + value + "</span>";
			    		} else {
			    			return "--";
			    		}
					}},
			        {field:'faceCraft',title:'表面工艺',width:100,formatter: function(value,row,index) {
						if("" != value) {
			    			return "<span title='" + value + "'>" + value + "</span>";
			    		} else {
			    			return "--";
			    		}
					}},
			        {field:'craft',title:'工艺',width:100,formatter: function(value,row,index) {
						if("" != value) {
			    			return "<span title='" + value + "'>" + value + "</span>";
			    		} else {
			    			return "--";
			    		}
					}},
					 {field:'totals',title:'总量',width:100,formatter: function(value,row,index) {
							/* if("" != value) {
				    			return "<span title='" + value + "'>" + value + "</span>";
				    		} else {
				    			return "--";
				    		} */
				    		return "<span title='" + value + "'>" + value + "</span>";
				    		
						}},
						{field:'remain',title:'剩余',width:100,formatter: function(value,row,index) {
							/* if("" != value) {
				    			return "<span title='" + value + "'>" + value + "</span>";
				    		} else {
				    			return "--";
				    		} */
				    		return "<span title='" + value + "'>" + value + "</span>";
				    		
						}},
				        {field:'size',title:'大小',width:100,formatter: function(value,row,index) {
							if("" != value) {
				    			return "<span title='" + value + "'>" + value + "</span>";
				    		} else {
				    			return "--";
				    		}
						}},
						{field:'proAddr',title:'生产地址',width:100,formatter: function(value,row,index) {
							if("" != value) {
				    			return "<span title='" + value + "'>" + value + "</span>";
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
	//增加
	$('#btnAdd').bind('click', function() {
    	var content = '<iframe scrolling="auto" frameborder="0" src="/web/proAction_add.action" style="scrolling:no;width:100%;height:100%;overflow:hidden"></iframe>';
    	$('#win').window({
    		title: '添加商品',
		    width: 700,
		    height: 450,
		    content: content,
		    minimizable: false,
		    maximizable: false,
		    closable: true,
		    modal:true,
		});
		$('#win').window('center');
        $('#win').window('open');
    });

	// 查询
	$('#btnQuery').bind('click', function() {
        $('#dg').datagrid('load', {
        	 'product.productName': $('#proname').val(),
        	 'product.productId': $('#id').val(),
        	 'product.manuDate': $('#pro_manuDate').datebox('getValue')
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
							ss.push(row.productId);
						}
						var params = {
							'ids':ss.join(',')
						}
						$.ajax({
							type: 'post',
							url: '/web/proAction_delPro.action',
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
	// 修改
    $('#btnModify').bind('click', function() {
    	
    	var opt = $('#btnModify').linkbutton('options');
    	if(!opt.disabled) {
	    	var rows = $('#dg').datagrid('getSelections');
	    	var suc = true;
    		var content = '<iframe scrolling="no" frameborder="0" src="/web/proAction_modifyInit.action?product.productId=' + rows[0].productId + '" style="scrolling:no;width:100%;height:100%;overflow:hidden;"></iframe>';
		    	$('#win').window({
		    		title: '修改商品',
				    width: 700,
			    	height: 450,
				    content: content,
				    minimizable: false,
				    maximizable: false,
				    closable: true,
				    modal:true
				});
				
				$('#win').window('refresh');
				$('#win').window('center');
		        $('#win').window('open');
    		
    	}
    });

	function checkEditDelBtnStatus() {
		var rows = $('#dg').datagrid('getSelections');
		if(rows.length == 0) {
			$('#btnModify').linkbutton('disable');
			$('#btnDelete').linkbutton('disable');
		} else if(rows.length == 1) {
			$('#btnModify').linkbutton('enable');
			$('#btnDelete').linkbutton('enable');
		} else if(rows.length > 1) {
			$('#btnModify').linkbutton('disable');
			$('#btnDelete').linkbutton('enable');
		} else {
			$('#btnModify').linkbutton('disable');
			$('#btnDelete').linkbutton('disable');
		}
	}


</script>
</body>

</html>