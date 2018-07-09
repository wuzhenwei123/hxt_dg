<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<script language="javascript" type="text/javascript"> 
var error = '${error}';
if(error==""){
	window.parent.location.href="${ctx}/index/toDianbiao"; 
}else{
	window.parent.location.href="${ctx}/index/first?errormsg="+error; 
}
</script> 