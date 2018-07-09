<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>${_title}</title>
	<meta name="keywords" content="${_keywords}" />
	<meta name="description" content="${_description}" />
	<meta content="textml;charset=utf-8" http-equiv="content-type">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<meta content="webkit|ie-comp|ie-stand" name="renderer">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/public/css/nav.css">
</head>
<body>
<%@ include file="/WEB-INF/page/public/common/top.jsp" %>


<div class="box minBox">
    <div class="pTitle"><a href="${ctx }/public/noticelist?sortColumn= a.id desc ">公告</a><span>></span><span>${notice.title }</span></div>
    
    <div class="newsContentBox">
        <div class="newsTitlle">
            <h1>${notice.title }</h1>
            <p>时间：<fmt:formatDate value="${notice.createTime}" type="date"/></p>
        </div>
        <div class="newsContent">
    	</div>
    </div>
</div>


<%@ include file="/WEB-INF/page/public/common/footer.jsp" %>

<script src="/public/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript">
 var conetent = unescape('${notice.content }');
 $(".newsContent").append(conetent);
</script>
</body>
</html>
