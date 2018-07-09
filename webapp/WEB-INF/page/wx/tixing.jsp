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
    <title>业务暂停通知</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/style.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/comm.css">
    <link href="${ctx}/css/weui.css" rel="stylesheet" type="text/css" />
    <script src="${ctx}/js/wx/zepto.min.js" ></script>
    <script type="text/javascript">
    if (typeof WeixinJSBridge == "undefined"){
   	   if( document.addEventListener ){
   	       document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
   	   }else if (document.attachEvent){
   	       document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
   	       document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
   	   }
   	}else{
   		   onBridgeReady();
   	}

   	function closeWin(){
   		WeixinJSBridge.call('closeWindow');
   	}

   	function onBridgeReady(){
   	}
   	
   	function closeAlert(){
		$(".weui_dialog_alert").hide();
	}
    </script>
</head>
<body class="body">
	<div class="box">
    	<div class="topBox">
        	<a class=" offen_back" href="javascript:closeWin()"></a>
          	  业务暂停通知
        </div>
        <div class="yzBox">
        	<p style="text-align: center;">
    公告
</p>
<p>
    <br/>
</p>
<p>
    根据中国人民银行办公厅内部发布的《关于加强小额支付系统集中代收付业务管理有关事项的通知》的支付限额以及相关规定，本公众号手机缴费业务暂停服务，具体回复时间另行公告。
</p>
<p>
    <br/>
</p>
<p>
    您可使用电脑登录http://qiye.chinaepay.com，使用企业支付B2B支付功能缴费，登录后，课查看页面左上角，联系您的网银指导人员咨询如何开通企业B2B支付功能。
</p>
<p>
    <br/>
</p>
<p>
    特此通知
</p>
<p>
    <br/>
</p>
<p>
    <br/>
</p>
<p style="white-space: normal; text-align: right;">
    北京恒信通电信服务有限公司
</p>
<p style="white-space: normal; text-align: right;">
    2017年7月
</p>
            
        </div>
        
    </div>
    
	<div class="weui_dialog_alert" style="display: none;">
	    <div class="weui_mask"></div>
	    <div class="weui_dialog">
	    	<div class="weui_dialog_hd">
	    		<strong class="weui_dialog_title" id="msg"></strong>
	      	</div>
	        <div class="weui_dialog_ft">
	            <a href="javascript:closeAlert();" class="weui_btn_dialog primary">确定</a>
	        </div>
	    </div>
	</div>
<body>
</body>
</html>
