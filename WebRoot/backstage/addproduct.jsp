<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/include.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<title>新增商品</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<style type="text/css">
		td{height:35px}
		</style>
		<script type="text/javascript">
		$(document).ready(function() {
			$('#pro_type').combotree({
	            url:'backstage/type.json',
	            onSelect : function(node) {  
	                    //返回树对象  
	                    var tree = $(this).tree;  
	                    //选中的节点是否为叶子节点,如果不是叶子节点,清除选中  
	                    var isLeaf = tree('isLeaf', node.target);  
	                    if (!isLeaf) {  
	                        //清除选中  
	                        $('#pro_type').combotree('clear');  
	                    }  
	                }  
	        });
		});
		$.extend($.fn.validatebox.defaults.rules, {
			vdNumber: {
				validator: function(value, param) {
					var reg = /^\d+(\.\d+)?$/;
					var bol = value.match(reg);
					
					return bol;
				},
				message: '只能为数值型数据'
			},
 			vdInt:{
			 
			 validator: function(value, param) {
					if(value>0)
					{
						var reg = /^[0-9]*$/;
						var bol = value.match(reg);
						return bol;
					}
					return false;
				},
				message: '只能为大于零的整数'
				
		 },
		 vdLength:{
			 
			 validator: function(value, param) {
					if(value.length>45)
					return false;
					else return true;
				},
				message: '简介不能多于45个字'
				
		 },
		 vds:{
			 
			 validator :function(value, param) {
						var reg = /^\d+\*\d+\*\d+$/;
						var bol = value.match(reg);
						return bol;
				},
				message: '只能为n*n*n格式'
		 }
		});
		
		</script>
	</head>
	
	<body >
		<div style="padding:10px 0 10px 30px">
			<form id="ff" method="post" enctype="multipart/form-data" >
				<table style="margin-left:10px">
					<tr>
						<td>商品名称：</td>
						<td>
							<input id="pro_name" name="product.productName" class="easyui-validatebox" data-options="required:true" />
						</td>
						<td>&nbsp&nbsp商品价格：</td>
						<td>
							<input id="pro_price" name="product.price" class="easyui-validatebox" data-options="required:true,validType:['vdNumber']" />
						</td>
					</tr>
					
					<tr>
						<td>生产日期：</td>
						<td>
							<input id="pro_manuDate" name="product.manuDate" class="easyui-datebox" required="required"></input>
						</td>
						<td>&nbsp&nbsp商品总数：</td>
						<td>
							<input id="pro_totals" name="product.totals" class="easyui-validatebox" data-options="required:true,validType:['vdInt']" />
						</td>
					</tr>
					<tr>
						<td>商品品牌：</td>
						<td>
							<input id="pro_brand" name="product.brand" class="easyui-validatebox" />
						</td>
						<td>&nbsp&nbsp商品产地：</td>
						<td>
							<input id="pro_proAddr" name="product.proAddr" class="easyui-validatebox" />
						</td>
					</tr>
					<tr>
						<td>商品材质：</td>
						<td>
							<input id="pro_materia" name="product.materia" class="easyui-validatebox" />
						</td>
						<td>&nbsp&nbsp商品尺寸：</td>
						<td>
							<input id="pro_size" name="product.size" class="easyui-validatebox" data-options="validType:['vds']"/>
						</td>
					</tr>
					<tr>
						<td>商品包装：</td>
						<td>
							<input id="pro_pack" name="product.pack" class="easyui-validatebox" />
						</td>
						<td>&nbsp&nbsp商品工艺：</td>
						<td>
							<input id="pro_craft" name="product.craft" class="easyui-validatebox"  />
						</td>
					</tr>
					<tr>
						<td>加印LOGO：</td>
						<td>
							<input id="pro_logo" name="product.logo" class="easyui-combobox" data-options="data:[{name:'可以',value:'可以'},{name:'不可以',value:'不可以'}], value:'不可以', valueField:'value', textField:'name', panelHeight:'auto', editable:false, required:false" />
						</td>
						<td>&nbsp&nbsp表面工艺：</td>
						<td>
							<input id="pro_faceCraft" name="product.faceCraft" class="easyui-validatebox"  />
						</td>
					</tr>
					<tr>
						<td>上传图片：</td>
						<td>
							<input id="pro_picture" name="product.picture" type="file" data-options="required:true" />
						</td>
						
					</tr>
					<tr>
						<td>商品类型：</td>
						<td>
							<select id="pro_type" name="product.type.firstId"  style="width:200px;" data-options="required:true" />
						</td>
						
					</tr>
					<tr>
						<td>简介：</td>
						<td>
							<input id="pro_simplyIntro" name="product.simplyIntro" class="easyui-validatebox" data-options="validType:['vdLength']"/>
						</td>
						
					</tr>
					
				</table>
			</form>
			
		</div>
		<div style="text-align:center;padding:5px">
			<a href="javascript:void(0)" id="btnSubmit" class="easyui-linkbutton" iconCls="icon-ok" onclick="submitForm()">确定</a>
			<a href="javascript:void(0)" id="btnCancel" class="easyui-linkbutton" iconCls="icon-cancel" onclick="clearForm()">取消</a>
		</div>
		<script>
			
			function submitForm() {
				
				$('#ff').form('submit', {
				    url:'/web/proAction_addPro.action',  
				    onSubmit: function() {
				         var isValid = $(this).form('validate');
						 if(!isValid) {
						 	$('#btnSubmit').linkbutton('enable');
						 } else {
						 	$('#btnSubmit').linkbutton('disable');
						 }
						 return isValid;
				    },
				    success:function(data) {
				    	
				    		var obj = jQuery.parseJSON(data);
				    		if(obj && obj.success) {
						    	parent.$('#win').window('close');  
						        parent.$('#dg').datagrid('reload');
					    	}
				    		else{
				    			
				    			alert("新增错误");
				    			$('#btnSubmit').linkbutton('enable');
				    		}
				    	
				    }
				}); 
			}
			
			function clearForm() {
				parent.$('#win').window('close');
			}
		</script>
	</body>
</html>
