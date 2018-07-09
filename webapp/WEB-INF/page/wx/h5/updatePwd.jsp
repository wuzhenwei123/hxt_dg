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
    <title>密码重置</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/h5/style.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/h5/comm.css">
    <link href="${ctx}/css/weui.css" rel="stylesheet" type="text/css" />
    <script src="${ctx}/public/custom/public/js/jquery.1.7.2.min.js"></script>
    <script src="${ctx}/js/area_public.js"></script>
</head>
<body class="body">
	<div class="box">
    	<div class="topBox"><a class=" offen_back" href="javascript:history.go(-1);"></a>密码重置</div>
        
        <div class="restPwdBox">
            <div class="searItemsa">
                <input type="password" class="searchInput" placeholder="请输入密码" id="pwd"/>
            </div>
            
            <div class="searItemsa">
                <input type="password" class="searchInput" placeholder="请再次输入密码" id="pwd1"/>
            </div>
            
        </div>
        
        <div class="itemsContentBox on resPwd">
        	<a id="OkBtnBox" class="btn btn_primary" href="javascript:void(0);" onclick="save()">完成</a>
        </div>
        
        
        
    </div>
    
    <div class="weui_dialog_alert" style="display: none;">
	    <div class="weui_mask"></div>
	    <div class="weui_dialog">
	    	<div class="weui_dialog_hd">
	    		<strong class="weui_dialog_title" id="msg">密码重置成功!</strong>
	      	</div>
	        <div class="weui_dialog_ft">
	            <a href="javascript:closeWin();" class="weui_btn_dialog primary">确定</a>
	        </div>
	    </div>
	</div>
    <script>
    
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
	   	
    	function save(){
    		var pwd = $.trim($("#pwd").val());
    		var pwd1 = $.trim($("#pwd1").val());
    		if(!pwd){
    			$HXT.wxAlert('请输入密码');
    			return false;
    		}
    		if(!pwd1){
    			$HXT.wxAlert('请再次输入密码');
    			return false;
    		}
    		
    		if(pwd!=pwd1){
    			$HXT.wxAlert('两次输入密码不一致，请重新输入');
    			return false;
    		}
    		
    		$.post("<c:url value='/H5/updatePwd'/>",
	       	{
    			pwd:pwd,
				ranNum:Math.random()
			},
	       	function(data){
	        	var result = eval('('+data+')'); 
	            if (result.code == '1') {
	            	$(".weui_dialog_alert").show()
	             } else {
	            	$HXT.wxAlert(result.message);
	             }
	        });
    	}
    </script>
<body>
</body>
</html>
