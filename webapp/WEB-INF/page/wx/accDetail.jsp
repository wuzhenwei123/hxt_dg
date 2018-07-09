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
            	<ul class="clearAfter ItemuL om">
                    <li class="fl">申请金额（元）</li>
                    <li class="fr">${param1.totalFee }</li>        
                </ul>
                <ul class="clearAfter ItemuL om">
                    <li class="fl">申请日期</li>
                    <li class="fr"><fmt:formatDate value="${param1.tick_off_time}" pattern="yyyy-MM-dd"></fmt:formatDate></li>        
                </ul>
                <ul class="clearAfter ItemuL">
                    <li class="fl">代缴税金额（元）</li>
                    <li class="fr">${param1.taxRate }</li>        
                </ul>
                <ul class="clearAfter ItemuL">
                    <li class="fl">应到账金额（元）</li>
                    <li class="fr">${param1.realFee }</li>        
                </ul>
                <ul class="clearAfter ItemuL">
                    <li class="fl">申请状态</li>
                    <li class="fr">已发放</li>        
                </ul>
                <ul class="clearAfter ItemuL">
                    <li class="fl">发放时间</li>
                    <li class="fr"><fmt:formatDate value="${param1.createTime}" pattern="yyyy-MM-dd"></fmt:formatDate></li>        
                </ul>
            </div>
    </div>
<body>
</body>
</html>
