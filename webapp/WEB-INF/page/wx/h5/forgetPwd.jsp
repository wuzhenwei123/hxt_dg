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
    <title>忘记密码</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/h5/style.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/h5/comm.css">
    <link href="${ctx}/css/weui.css" rel="stylesheet" type="text/css" />
    <script src="${ctx}/public/custom/public/js/jquery.1.7.2.min.js"></script>
    <script src="${ctx}/js/area_public.js"></script>
    <style type="text/css">
    .code input {
	    width: 47% !important;
	    float: left;
	}
	.restPwdBox .searItemsa1 {
	    padding-top: 25px;
	    padding: 10px 20px;
	}
	input::-webkit-input-placeholder { 
		font-size: 14px! important;
	}
	.searchInput{
		font-size: 14px! important;
	}
    </style>
</head>
<body class="body">
	<div class="box">
    	<div class="topBox"><a class=" offen_back" href="javascript:history.go(-1);"></a>忘记密码</div>
        
        <div class="restPwdBox">
            <div class="searItemsa">
                <input type="text" class="searchInput" placeholder="请输入注册手机号码" id="aContact_phone"/>
            </div>
            
            <div class="searItemsa code">
                <input type="text" class="searchInput" placeholder="请输入图形验证码" id="imgcode"/>
                <div class="fr codes_box">
                	<div class="code_img_box"><img src="${ctx }/manageAdminUser/pcrimg" onclick="getImg()" id="codeimg" style="height:40px;width:125px;"></div>
                </div>
                <span class="clear"></span>
            </div>
            <div class="searItemsa1 code">
                <input type="text" class="searchInput" placeholder="请输入短信验证码" id="vercode"/>
                <div class="fr codes_box"><a id="codeBtn" class="btn btn_primary" href="javascript:void(0);" onclick="sendCode()">获取短信验证码</a></div>
                <span class="clear"></span>
            </div>
            
        </div>
        
        <div class="itemsContentBox on">
        	<a id="OkBtnBox" class="btn btn_primary" href="javascript:add();">完成</a>
            
            
        </div>
        
        
        
    </div>
    <script>
    function add(){
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
			if(!$("#imgcode").val()){
				$HXT.wxAlert('请填写右侧验证码！');
				$("#imgcode").focus();
				return false;
			}
			if(!$("#vercode").val()){
				$HXT.wxAlert('请填写手机验证码！');
				$("#vercode").focus();
				return false;
			}
			$.post("<c:url value='/H5/forgetPwd'/>",
	       	{
				vercode:$("#vercode").val(),
				imgcode:$("#imgcode").val(),
				phone :phone,
				ranNum:Math.random()
			},
	       	function(data){
	        	var result = eval('('+data+')'); 
	            if (result.code == '1') {
	            	window.location.href = "${ctx}/H5/toUpdatePwd";
	             } else {
	            	$HXT.wxAlert(result.message);
	             }
	        });
		}
    	
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
			if(!$("#imgcode").val()){
				$HXT.wxAlert('请填写右侧验证码！');
				$("#imgcode").focus();
				return false;
			}
			$.post("<c:url value='/H5/sendCode1'/>",
	       	{
				vercode:$("#imgcode").val(),
				phone :phone,
				content:'您的验证码是：',
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
    
    function getImg(){
		var url = '${ctx }/manageAdminUser/pcrimg?_t='+Math.random();
		$("#codeimg").attr('src',url);
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
    </script>
<body>
</body>
</html>
