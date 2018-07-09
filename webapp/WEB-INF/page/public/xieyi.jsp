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
</head>
<body>
<%@ include file="/WEB-INF/page/public/common/top.jsp" %>


<div class="box minBox">
    
    <div class="newsContentBox">
        <div class="newsTitlle">
            <h1 style="font-size: 25px;">《恒信通企业版网站服务协议》</h1>
        </div>
        <div class="newsContent">
        ${xieyi.content }
    	</div>
    </div>
</div>

<%@ include file="/WEB-INF/page/public/common/footer.jsp" %>

</body>
</html>
