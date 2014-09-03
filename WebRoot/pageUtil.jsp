<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />



<style type="text/css">
span {
	PADDING-RIGHT: 0px;
	PADDING-LEFT: 0px;
	FONT-SIZE: 12px;
	PADDING-BOTTOM: 0px;
	MARGIN: 0px auto;
	COLOR: #666666;
	PADDING-TOP: 0px;
	TEXT-ALIGN: left
}
</style>
</head>
<body>
<div align="center"><span>每页显示<s:property
	value="pageUtil.pageSize" />条</span> <span>共<s:property
	value="pageUtil.recordCount" />条&nbsp;</span> <span>当前页<s:property
	value="pageUtil.currPage" />/共<s:property value="pageUtil.pageCount" />页</span> <span>
<s:if test="pageUtil.hasPrevious==true">
	<s:a action="%{pageAction}">
                          第一页
          <s:param name="pageUtil.index" value="0"></s:param>
		<s:param name="pageUtil.currPage" value="1"></s:param>
	</s:a>
</s:if><s:else>
                  第一页
      </s:else> </span> <span> <s:if test="pageUtil.hasPrevious==true">
	<s:a action="%{pageAction}" >
                        上一页
          <s:param name="pageUtil.index" value="pageUtil.previousIndex"></s:param>
		<s:param name="pageUtil.currPage" value="pageUtil.currPage-1"></s:param>
	</s:a>
</s:if><s:else>
                      上一页
      </s:else> </span> <span> <s:if test="pageUtil.hasNext==true">
	<s:a action="%{pageAction}">
                        下一页
          <s:param name="pageUtil.index" value="pageUtil.nextIndex"></s:param>
		<s:param name="pageUtil.currPage" value="pageUtil.currPage+1"></s:param>
	</s:a>
</s:if><s:else>
                      下一页
       </s:else> </span> <span> <s:if test="pageUtil.hasNext==true">
	<s:a action="%{pageAction}">
                               最后一页
            <s:param name="pageUtil.index"
			value="(pageUtil.pageCount-1)*pageUtil.pageSize"></s:param>
		<s:param name="pageUtil.currPage" value="pageUtil.pageCount"></s:param>
	</s:a>
</s:if><s:else>
                 最后一页
      </s:else> </span></div>
</body>