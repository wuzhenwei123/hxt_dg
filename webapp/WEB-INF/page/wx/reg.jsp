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
    <title>注册</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/style.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/comm.css">
    <link href="${ctx}/css/weui.css" rel="stylesheet" type="text/css" />
    <script src="${ctx}/js/wx/zepto.min.js" ></script>
    <script src="${ctx}/js/area_public.js"></script>
</head>
<body class="body">
	<div class="box">
    	<div class="topBox"><a class=" offen_back" href="#"></a>注册</div>
        <div class="yzBox regBox">
            <div class="items">
            	<input class="input" type="text" placeholder="请输入缴费号" id="aAmmeterNo"/>
                <a class="ques" href="${ctx }/weixin/toQuestion">如何查找缴费号码?</a>
            </div>
        	<div class="items">
            	<input class="input" type="text" placeholder="联系人手机号" id="aContact_phone"/>
            </div>
            <div class="items">
            	<ul class="searItems codeBox">
                    <li class="fl searItem">
                        <input class="input" id="vercode" type="text" placeholder="请输入短信验证码" value=""/>
                    </li>
                    <li class="fr searItem">
                        <a class="input btn btn_primary codeBtn" href="javascript:sendCode();" id="codeBtn">获取短信验证码</a>
                    </li>
              </ul>
              </div>
	            <div class="items" style="display:<c:if test="${empty session.errCount||session.errCount<2 }">none</c:if>;" id="imgcodeDiv">
	              <ul class="searItems codeBox">
	                    <li class="fl searItem">
	                        <input class="input" type="text" placeholder="请输入图形验证码" id="imgcode"/>
	                    </li>
	                    <li class="fr searItem ">
	                    		<div class="imgCode"><img src="${ctx }/manageAdminUser/pcrimg" onclick="getImg()" id="codeimg" style="height:40px;width:140px;"></div>
	                    </li>
	              </ul>
	            </div>
           	<div class="items">
            	<input class="input" type="text" placeholder="推荐人手机号(可不填)" id="recPhone"/>
            </div>
            <div class="items">
            	<a href="javascript:add();" class="btn btn_primary">注册</a>
            </div>
        </div>
    </div>
    <input type="hidden" id="errCount" value="${session.errCount }" readonly="readonly"/>
<!--     <c:if test="${errOpenId ==1}"> -->
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
//     	$HXT.wxAlertCall('该微信号已注册过，请不要重复注册！',function(){closeWin();});
    	</script>
<!--     </c:if> -->
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
	function add(){
		var contact_phone = $("#aContact_phone").val();
		var vercode = $("#vercode").val();
		var ammeterNo = $("#aAmmeterNo").val();
		if(!ammeterNo){
			$HXT.wxAlert('请填写缴费号');
			return false;
		}
		var reg = /^[\d.\-_]+$/;
		if(!reg.test(ammeterNo)){
			$HXT.wxAlert("请您输入正确的缴费号");
			return false;
		}
		if(!contact_phone){
			$HXT.wxAlert('请填写联系人手机号');
			return false;
		}
		if(!validatemobile(contact_phone)){
			$HXT.wxAlert('联系人手机格式不正确');
			return false;
		}
		if(!vercode){
			$HXT.wxAlert('请填写短信验证码');
			return false;
		}
		//验证码
		var url = '${ctx}/weixin/checkVercode';
		$.post(url,{
			phone:contact_phone,
			vercode:vercode,
			_r:Math.random()
		},function(data){
			var result = data;
			if(JSON.parse(result).code==1){
				//验证通过
				checkElectric(ammeterNo,contact_phone);
			}else{
				var itemjson = JSON.parse(result).items;
				var errCount = JSON.parse(itemjson).errCount;
				if(errCount>=2){
					if($("#errCount").val()){
						$("#errCount").val(errCount)
					}else{
						$("#errCount").val(1);
					}
					$HXT.wxAlertCall("验证码不正确",function(){$('#imgcodeDiv').show();})
				}else{
					$HXT.wxAlert("验证码不正确");
				}
			}
		});
	}
	function checkElectric(ammeterNo,phone){
		var url = '${ctx}/weixin/getAmmeterInfo?electricNum=' + ammeterNo;
	    $.ajax({
	        type: "GET",
	        url: url,
	        dataType: "json",
	        success: function (data) {
	            if (data.status == 'success') {
	                if (eval(data.data).resultCode == '00') {//电表存在
	                    //电表详情页面
	                    window.location.href = '${ctx}/weixin/toAmmeter?openId='+'${openId}'+'&phone='+phone+'&ammeterNo='+ammeterNo+'&ammeterInfo='+JSON.stringify($(data.data))+"&recPhone="+$("#recPhone").val()+'&refereeOpenId=${refereeOpenId}&content_phone='+phone;
	                } else {
	                	$HXT.wxAlert(data.msg);
	                }
	            } else {
	            	$HXT.wxAlert(data.msg);
	            }


	        }
	    });
	}
	function validatemobile(mobile){ 
		if (mobile.length == 0) {
			return false;
		}
		if (mobile.length != 11) {
			return false;
		}
		var myreg = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
		if (!myreg.test(mobile)) {
			return false;
		}
		return true;
	}
	
	function sendCode(){
		var phone = $.trim($("#aContact_phone").val());
		if(phone == ''){
			$HXT.wxAlert("请填写联系人的手机号");
			$("#aContact_phone").focus();
		}else{
			if(!validatemobile(phone)){
				$HXT.wxAlert('联系人手机格式不正确');
				$("#aContact_phone").focus();
				return false;
			}
			if($("#errCount").val()>=2){
				if(!$("#imgcode").val()){
					$HXT.wxAlert('请填写右侧验证码！');
					$("#imgcode").focus();
					return false;
				}
			}
			$.post("<c:url value='/weixin/wxVercode'/>",
	       	{
				vercode:$("#imgcode").val(),
				phone :phone,
				ranNum:Math.random()
			},
	       	function(data){
	        	var result = eval('('+data+')'); 
	            if (result.code == '1') {
	            	$HXT.wxAlert("发送成功，请注意查收验证码");
	            	btnNum();
	             } else {
	            	countdown = 0;
	            	$HXT.wxAlert(result.message);
	             }
	        });
		}
	}
	function btnNum(){
		var num = 59;
		$("#codeBtn").attr('disabled',true);
		$("#codeBtn").html('还剩'+num-- +'秒');
		var intervalID = setInterval(function(){
			if(num>0){
				$("#codeBtn").attr('disabled',true);
				$("#codeBtn").attr('onclick','');
				$("#codeBtn").html('还剩'+num+'秒');
				num--;
			}else{
				$("#codeBtn").html('发送验证码');
				$("#codeBtn").attr('disabled',false);
				$("#codeBtn").attr('onclick','sendCode()');
				clearInterval(intervalID)
			}
		}, 1000);
	}
	function getImg(){
		var url = '${ctx }/manageAdminUser/pcrimg?_t='+Math.random();
		$("#codeimg").attr('src',url);
	}
	$('.offen_back').click(function(){
		closeWin();
	})
	</script>
</body>
</html>
