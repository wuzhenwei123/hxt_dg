<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<html>
	<head>
    <meta charset="utf-8">
    <meta name="author" content="www.999care.com">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="#e8447e">
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="Expires" CONTENT="-1">           
    <meta http-equiv="Cache-Control" CONTENT="no-cache">           
    <meta http-equiv="Pragma" CONTENT="no-cache">
    <meta name="description" content="">
    <meta name="Keywords" content="">
    <title>我的二维码</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/customForm.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/style.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/comm.css">
    <link href="${ctx}/css/weui.css" rel="stylesheet" type="text/css" />
    <script src="${ctx}/public/custom/public/js/jquery.1.7.2.min.js"></script>
</head>
<body class="body">
<div class="box">
	<%@ include file="/WEB-INF/page/common/share1.jsp" %>
    <div class="topBox"><a class=" offen_back" href="javascript:history.go(-1);"></a>我的二维码</div>
    
    <div class="bsBox marginTop16">
    	<div class="codeLogoBox"></div>
        <div class="code_box">
        	<img src="${imgUrl}" width="300px;" height="300px;"/>
        </div>
        
        <div class="codeRemBox">
        	请您长按二维码，并选择“识别图中二维码”，点击关注即可，赶快行动吧！
        </div>
        
    </div>
    
    
    
</div>
</body>
</html>
