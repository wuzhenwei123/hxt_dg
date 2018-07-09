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
    <title>如何找到抄表电缴费号</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/style.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/comm.css">
    <link href="${ctx}/css/weui.css" rel="stylesheet" type="text/css" />
    <script src="${ctx}/js/wx/zepto.min.js" ></script>
</head>
<body class="body">
	<div class="box">
    	<div class="topBox"><a class=" offen_back" href="javascript:history.go(-1);"></a>如何找到抄表电缴费号</div>
        
        <div class="yzBox regBox">
        	
            <p class="qRem">1、您可以在以往的抄表电缴费通知单上找到。</p>
            <div class="qImgBox"><img src="${ctx}/images/wx/asdas1.jpg" /></div>
            <p class="qRem">2、您可以在以往的抄表单缴费发票上找到。</p>
            <p class="qRem">3、您可以拨打国家电网24小时客服热线 95598 ，提供用电地 址信息，咨询人工客服查询。</p>
            
        </div>
        
        
    </div>
</body>
<script>
$('.offen_back').click(function(){
	window.history.back();
});
</script>
</html>
