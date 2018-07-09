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
    <title>您输入的电表缴费号欠费信息如下</title>
    <link rel="stylesheet" type="text/css" href="${ctx }/css/wx/style.css">
    <link rel="stylesheet" type="text/css" href="${ctx }/css/wx/comm.css">
    <link href="${ctx}/css/weui.css" rel="stylesheet" type="text/css" />
    <script src="${ctx }/js/wx/zepto.min.js" ></script>
    <script src="${ctx}/js/area_public.js"></script>
</head>
<body class="body">
	<div class="box">
    	<div class="topBox"><a class=" offen_back" href="#"></a>您输入的电表缴费号欠费信息如下</div>
        <div class="yzBox regBox jyInfoBox qfInfoBox">   
            <div class="bsBox ItemLi qfInfo marginTop16">
                <ul class="clearAfter ItemuL">
                    <li class="fl">缴费号</li>
                    <li class="fr">${ammeterNo }</li>        
                </ul>
                <ul class="clearAfter ItemuL">
                    <li class="fl">客户名称</li>
                    <li class="fr">${accountName }</li>        
                </ul>
                <ul class="clearAfter ItemuL">
                    <li class="fl">用电地址</li>
                    <li class="fr">${address }</li>        
                </ul>
                <ul class="clearAfter ItemuL">
                    <li class="fl">账单金额</li>
                    <li class="fr"><font class="money">${accountFee }</font></li>        
                </ul>
                <ul class="clearAfter ItemuL">
                    <li class="fl">滞纳金</li>
                    <li class="fr"><font class="money">${lateFee }</font></li>        
                </ul>
                <ul class="clearAfter ItemuL">
                    <li class="fl">应交金额</li>
                    <li class="fr"><font class="money">${totalFee }</font></li>        
                </ul>                                              
             </div>
             
    	</div>
        
        
        <div class="qfBtnBox">
        	<div class="fl"><a href="javascript:window.history.back();" class="btn btn_primary">返回重输</a></div>
            <div class="fr"><a href="javascript:sub();" class="btn btn_primary">确认注册</a></div>
        </div>
    </div>
    <div class="weui_dialog_confirm" style="display: none;">
	    <div class="weui_mask"></div>
	    <div class="weui_dialog">
	        <div class="weui_dialog_hd">
	          <strong class="weui_dialog_title">恭喜您注册成功，您的登录账号为注册时使用的手机号码（${content_phone}），登录密码为手机号码的后6位，请您登录后设置更加安全的密码。如果您还有其他缴费号，请立即登录继续绑定。</strong>
	        </div>
	        <div class="weui_dialog_ft">
	            <a href="javascript:;" class="weui_btn_dialog default" onclick="closeConfirm()">返回</a>
	            <a href="javascript:;" class="weui_btn_dialog primary" onclick="login()">立即登录</a>
	        </div>
	    </div>
	</div>
    <c:if test="${not empty errMsg }">
	    <script>
	    	$(document).ready(function(){
	    		$HXT.wxAlert('${errMsg}');
	    	})
    	</script>
    </c:if>
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
    var flag = true;
    	function sub(){
    		if(flag){
	    		$.post("<c:url value='/hCompany/wxReg'/>",
			        	{
				    		contact_phone : '${phone}',
				    		ammeterNo:'${ammeterNo}',
				    		recPhone:'${recPhone}',
				    		refereeOpenId:'${refereeOpenId}',
				    		action : 'reg',
						 _t:Math.random()},
			        	function(data){
				        	var result = eval('('+data+')');
				            if (result.code == '1') {
				            	$(".weui_dialog_confirm").show();
// 								$HXT.wxAlertCall("注册成功！",function(){closeWin();});
				             } else {
				            	 flag = true;
				            	 $HXT.wxAlert(result.message);
				             }
			        });
    		}
    	}
    	$('.offen_back').click(function(){
    		window.history.back();
    	})
    	
    	function closeConfirm(){
    		closeWin();
//     		$(".weui_dialog_confirm").hide();
    	}
    	
    	function login(){
    		window.location.href='${ctx}/manageAdminUser/cloginout';
    	}
    </script>
</body>
</html>
