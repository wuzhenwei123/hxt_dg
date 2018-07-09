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
    <title>变更技术人员</title>
     <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/customForm.css">
     <link rel="stylesheet" type="text/css" href="${ctx}/public/xcConfirm/css/xcConfirm.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/style.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/comm.css">
    <link href="${ctx}/css/weui.css" rel="stylesheet" type="text/css" />
    <script src="${ctx}/public/custom/public/js/jquery.1.7.2.min.js"></script>
	<script src="${ctx}/public/xcConfirm/js/xcConfirm.js"></script>
	<script src="${ctx}/js/area_public.js"></script>
</head>
<body class="body">
<div class="box">
    <div class="topBox">变更技术人员详情</div>
    
    <div class="bsBox marginTop16">
    	
    
   
	
    <div class="bsBox ItemLi marginTop16">
    	<ul class="clearAfter ItemuL">
			<li class="fl">原支持人员</li>
            <li class="fr">${oldUser.nickName}</li>        
        </ul>
        <ul class="clearAfter ItemuL">
			<li class="fl">变更支持人员</li>
            <li class="fr">${newUser.nickName}</li>        
        </ul>
        <ul class="clearAfter ItemuL">
			<li class="fl">操作客户经理</li>
            <li class="fr">${company.oneAgentName}</li>        
        </ul>
        <ul class="ItemuL">
			<li class="fl">客户名称</li>
            <li class="fr">${company.name}</li>        
        </ul>
        <ul class="clearAfter ItemuL">
			<li class="fl">联系人姓名</li>
            <li class="fr">${company.contact_name}</li>        
        </ul>
        <ul class="clearAfter ItemuL">
			<li class="fl">联系人手机</li>
            <li class="fr">${company.contact_phone}</li>        
        </ul>
        <span class="clear"></span>
        
        
    </div>
    
</div>
</body>
</html>
