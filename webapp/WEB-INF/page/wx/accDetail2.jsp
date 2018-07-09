<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<html>
	<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="#e8447e">
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="Expires" CONTENT="-1">           
    <meta http-equiv="Cache-Control" CONTENT="no-cache">           
    <meta http-equiv="Pragma" CONTENT="no-cache">
    <meta name="description" content="">
    <meta name="Keywords" content="">
    <title>我的账户</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/style.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/comm.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/public/xcConfirm/css/xcConfirm.css">
    <link href="${ctx}/css/weui.css" rel="stylesheet" type="text/css" />
    <script src="${ctx}/js/wx/zepto.min.js" ></script>
    <script src="${ctx}/js/area_public.js"></script>
</head>
<body class="body">
	<div class="box">
    	<div class="topBox"><a class=" offen_back" href="javascript:history.go(-1);"></a>交易详情</div>
        
        <div class="yzBox regBox jyInfoBox">
            <div class="bsBox ItemLi marginTop16">
            	<ul class="clearAfter ItemuL">
                    <li class="fl">鼓励金(元)</li>
                    <li class="fr">+<fmt:formatNumber value="${hRegGuliSend.fee }" pattern="0.00"></fmt:formatNumber></li>        
                </ul>
                <ul class="clearAfter ItemuL">
                    <li class="fl">鼓励金来源</li>
                    <li class="fr">客户成功注册</li>        
                </ul>
                <ul class="clearAfter ItemuL">
                    <li class="fl">注册时间</li>
                    <li class="fr"><fmt:formatDate value="${hRegGuliSend.c_create_time }" pattern="yyyy-MM-dd HH:mm"></fmt:formatDate></li>        
                </ul>
                <ul class="clearAfter ItemuL">
                    <li class="fl">注册账号</li>
                    <li class="fr">${hRegGuliSend.contact_phone }</li>        
                </ul>
                <ul class="clearAfter ItemuL">
                	<li class="fl">注册缴费号</li>
                    <li class="fr">${hRegGuliSend.ammeter }</li>        
                </ul>
            </div>
        </div>
    </div>
<body>
</body>
</html>
