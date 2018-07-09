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
    <title>确认电费信息</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/h5/style.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/h5/comm.css">
    <link href="${ctx}/css/weui.css" rel="stylesheet" type="text/css" />
    <script src="${ctx}/public/custom/public/js/jquery.1.7.2.min.js"></script>
    <script src="${ctx}/js/area_public.js"></script>
</head>
<body class="">
	<div class="box">
    	<div class="topBox"><a class=" offen_back" href="javascript:history.go(-1);"></a>确认电费信息</div>
        <div class="body body_black"></div>
        <div class="qrBox">
        	<div class="qrItemBox">
            	<label class="">缴费号：</label>
                <span class="fr">${electricNum}</span>
                <font class="clear"></font>
            </div>
            <div class="qrItemBox">
            	<label class="fl">客户名称：</label>
                <span class="fl">${accountName}</span>
            </div>
            <div class="qrItemBox">
            	<label class="">用电地址：</label>
                <span>${address}</span>
            </div>
            
            <div class="qrItemBox">
            	<label class="">账单金额：</label>
                <span class="fr"><font>${accountFee}</font></span>
                <font class="clear"></font>
            </div>
            
            <div class="qrItemBox">
            	<label class="">滞纳金：</label>
                <span class="fr"><font>${lateFee}</font></span>
                <font class="clear"></font>
            </div>
            
            <div class="qrItemBox">
            	<label class="">应交金额：</label>
                <span class="fr"><font>${totalFee}</font></span>
                <font class="clear"></font>
            </div>
            
            
            
            
            
            
        </div>
        <div class="itemsContentBox on">
    			<a id="OkBtnBox" class="btn btn_primary" href="javascript:bindAmmeter();">确定</a>
    		</div>
     
        
        
    </div>
    <script>
  //绑定
    function bindAmmeter() {
    	var url = '${ctx}/H5/saveHAmmeterInfo';
    	$.ajax({
    		type : "POST",
    		url : url,
    		data : {
    			ammeter_no : '${electricNum}',
    			ammeter_type : "A",
    			last_pay_day : "7",
    			pay_status : "1",
    			c_id : '${cid}',
    			s_id : '${sid}',
    			ammeter_name : '${accountName}',
    			ammeter_address : '${address}',
    			proxy_flag : 1
    		},
    		dataType : "json",
    		success : function(data) {
    			if (data.status == 'success') {
    				$HXT.wxAlert('绑定成功！');
    				$("#loadingToast").show();
    				window.location.href="${ctx}/H5/toPay";
    			} else {
    				$HXT.wxAlert(data.msg);
    			}
    		}
    	});
    }
    </script>
<body>
</body>
</html>
