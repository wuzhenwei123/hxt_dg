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
    <title>提现信息管理</title>
   <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/style.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/comm.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/public/xcConfirm/css/xcConfirm.css">
    <link href="${ctx}/css/weui.css" rel="stylesheet" type="text/css" />
    <script src="${ctx}/public/custom/public/js/jquery.1.7.2.min.js"></script>
    <script src="${ctx}/js/area_public.js"></script>
</head>
<body class="body">
<div class="box">
    <div class="topBox"><a class=" offen_back" href="javascript:history.go(-1);"></a>提现信息管理</div>
    
    
   
    
    <div class="bsBox marginTop16">
    	
    
   
	
    <div class="bsBox ItemLi userDetail txBox marginTop16">
        
        
        <ul class="ItemuL">
			<li class="fl">公司名称</li>
            <li class="fr">${hAgentTwo.name}</li>        
        </ul>
        <ul class="clearAfter ItemuL">
			<li class="fl">联系人姓名</li>
            <li class="fr">${hAgentTwo.contact_person}</li>        
        </ul>
        <ul class="clearAfter ItemuL">
			<li class="fl">联系人电话</li>
            <li class="fr">${hAgentTwo.mobile1}</li>        
        </ul>
        <ul class="clearAfter ItemuL">
			<li class="fl">开户银行</li>
            <li class="fr">${hAgentTwo.bank_name}</li>        
        </ul>
        <ul class="clearAfter ItemuL">
			<li class="fl">开户行行号</li>            
            <li class="fr">${hAgentTwo.bank_number}</li>        
        </ul>
        <ul class="clearAfter ItemuL">
			<li class="fl">开户行支行</li>            
            <li class="fr">${hAgentTwo.subBankName}</li>        
        </ul>
        <ul class="clearAfter ItemuL on">
			<li class="fl">银行账号</li>            
            <li class="fr">${hAgentTwo.bank_account}</li>        
        </ul>           
        <span class="clear"></span>
    </div>
</div>
<div class="tixBtnBox">
<a class="bjtxbTn" href="javascript:selBank();">编辑</a>
</div>
<script type="text/javascript">
function selBank(){
	var style = '${hAgentTwo.style}';
	if(style=="1"){//公司
		window.location.href = "${ctx}/weixin/toPostalInfo?openId=${openId}";
	}else{
		window.location.href = "${ctx}/weixin/toPostalInfoSecond?id=${hAgentTwo.id}";
	}
}
</script>
</body>
</html>
