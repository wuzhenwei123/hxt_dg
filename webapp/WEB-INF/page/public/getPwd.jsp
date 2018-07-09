<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<html>
<head>
<meta charset="utf-8">
<title>${_title}</title>
	<meta name="keywords" content="${_keywords}" />
	<meta name="description" content="${_description}" />
	<meta content="textml;charset=utf-8" http-equiv="content-type">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<meta content="webkit|ie-comp|ie-stand" name="renderer">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${ctx }/public/custom/public/css/customForm.css">
<link rel="stylesheet" type="text/css" href="${ctx }/public/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/public/css/nav.css">
<style type="text/css">
	
	.codeTitle {
	    width: 85px;
	    font-size: 14px;
	    color: #000000;
	    vertical-align: middle;
	    margin-right: 20px! important;
	}
	.pBtnBox {
	    padding-top: 10px;
	    width: 300px! important;
	    margin-left: 120px;
	}
	
	.pContentBox {
	    padding-left: 300px! important;
	    overflow: hidden;
	    padding-top: 10px;
	}
	
	.codeBtn {
	    margin-left: 11px;
	    font-size: 14px;
	    color: #01a796;
	    line-height: 30px;
	    overflow: hidden;
	    width: 124px;
	    text-align: center;
	    cursor: pointer;
	    background-color: #01a796;
	    color: #fff! important;
	}
    </style>
</head>
<body>
<%@ include file="/WEB-INF/page/public/common/top.jsp" %>

<div class="box minBox">
	
    <div class="pTitle">找回密码</div>
    <div class="pContentBox">
    	<div class="lineP pItems">
        	<font class="lines starFont">*</font>
    		<label class="lines codeTitle">注册手机号：</label>
        	<div class="lines pItem">
            	<input class="pInput" placeholder="请输入注册手机号" type="text" id="aContact_phone"/>
            </div>
        </div>
       <div class="lineP pItems">
        	<font class="lines starFont">*</font>
        	<label class="lines codeTitle">验证码：</label>
        	<div class="lines pItem code">
            	<input class="pInput" placeholder="输入右侧验证码" type="text" id="imgcode"/>
            </div>
            <a class="lines pItem codeBtn imgCode" href="javascript:getImg();">
            	<img id="vcode" alt="" src="${ctx }/manageAdminUser/pcrimg" style="width: 120px;height: 36px;">
            </a>
        </div>
        <div class="lineP pItems">
        	<font class="lines starFont">*</font>
        	<label class="lines codeTitle">短信验证码：</label>
        	<div class="lines pItem code">
            	<input class="pInput" placeholder="请输入短信验证码" type="text" id="vercode"/>
            </div>
            <a class="lines pItem codeBtn" onclick="sendCode()" href="javascript:void(0)" id="codeBtn">获取验证码</a>
            
        </div>
        
        <div class="lineP pItems">
        	<font class="lines starFont">*</font>
        	<label class="lines codeTitle">新密码：</label>
        	<div class="lines pItem">
            	<input class="pInput" placeholder="请输入新密码" type="password" id="pwd"/>
            </div>
        </div>
        <div class="lineP pItems">
        	<font class="lines starFont">*</font>
        	<label class="lines codeTitle">确认新密码：</label>
        	<div class="lines pItem">
            	<input class="pInput" placeholder="确认新密码" type="password" id="newPwd"/>
            </div>
        </div>
        
        <div class="pItems xyItems">
            <div class="pBtnBox">
            	<a class="iBtn" href="javascript:void(0)" onclick="upd()">提交</a>
            </div>
        </div>
        
    </div>
    
    
    
    
</div>
<%@ include file="/WEB-INF/page/public/common/footer.jsp" %>
<script src="${ctx }/public/js/jquery-1.8.2.min.js"></script>
<script src="${ctx }/public/custom/public/js/custominput.js"></script>
<script src="${ctx}/public/xcConfirm/js/xcConfirm.js"></script>
<script src="${ctx}/js/area_public.js"></script>
<script src="${ctx}/public/js/myPlaceholder.js"></script>
<script type="text/javascript">
$(function(){ });
	var flag = true;
	function upd(){
		var phone = $.trim($("#aContact_phone").val());
		if(phone == ''){
			$HXT.showInfo("请填写联系人的手机号");
			getImg();
// 			$('input').placeholder();
			password();
			return;
		}else{
			if(!validatemobile(phone)){
				$HXT.showInfo('联系人手机格式不正确');
				getImg();
// 				$('input').placeholder();
				return;
			}
		}
		
		var vercode = $("#vercode").val();
		if(!vercode){
			$HXT.showInfo('请填写手机验证码');
			getImg();
// 			$('input').placeholder();
			return;
		}
		
		var pwd = $("#pwd").val();
		if(!pwd){
			$HXT.showInfo('新密码不能为空');
			getImg();
// 			$('input').placeholder();
			return;
		}
		
		var newPwd = $("#newPwd").val();
		if(!newPwd){
			$HXT.showInfo('确认密码不能为空');
			getImg();
// 			$('input').placeholder();
			return;
		}
		
		if(newPwd!=pwd){
			$HXT.showInfo('两次密码不一致');
			getImg();
// 			$('input').placeholder();
			return;
		}
		
		if(flag){
			flag = false;
			var url = "<c:url value='/index/forget'/>";
			$.post(url,{phone:phone,vercode:vercode,pwd:pwd},function(data){
				var result = eval('('+data+')'); 
		        if (result.code == '1') {
		          	$HXT.showInfo('修改成功');
		          	setTimeout(function() {
		          		window.location.href='${ctx}/index/first';
	                }, 1000);
		         } else {
		        	 flag = true;
		        	 $HXT.showInfo(result.message);
		         }
			});
		}
		
	}
	
	var dxflag = true;
	
	function sendCode(){
		var phone = $.trim($("#aContact_phone").val());
		if(phone == ''){
			$HXT.showInfo("请填写联系人的手机号");
			getImg();
// 			$("#aContact_phone").focus();
// 			$('input').placeholder();
		}else{
			if(!validatemobile(phone)){
				$HXT.showInfo('联系人手机格式不正确');
				getImg();
// 				$("#aContact_phone").focus();
// 				$('input').placeholder();
				return;
			}
			if(!$("#imgcode").val()){
				$HXT.showInfo('请填写右侧验证码！');
				getImg();
// 				$("#imgcode").focus();
// 				$('input').placeholder();
				return;
			}
			if(dxflag){
				dxflag = false;
				$.post("<c:url value='/index/sendCode'/>",
				       	{
							vercode:$("#imgcode").val(),
							phone :phone,
							ranNum:Math.random()
						},
				       	function(data){
							dxflag = true;
				        	var result = eval('('+data+')'); 
				            if (result.code == '1') {
				            	$HXT.showInfo("发送成功，请注意查收验证码");
//			 	            	$('input').placeholder();
				            	btnNum();
				             } else {
				            	countdown = 0;
				            	getImg();
				            	$HXT.showInfo(result.message);
//			 	            	$('input').placeholder();
				             }
				        });
			}
		}
	}
	function btnNum(){
		var num = 179;
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
// 		$(".imgCode").css("background-image","url("+url+")");
		$("#vcode").attr("src",url);
	}
	
	function password(){
		//对password框的特殊处理1.创建一个text框 2获取焦点和失去焦点的时候切换  
        var pwdField    = $("input[type=password]");  
        var pwdVal      = pwdField.attr('placeholder');  
        pwdField.after('<input id="pwdPlaceholder" type="text" value='+pwdVal+' autocomplete="off" />');  
        var pwdPlaceholder = $('#pwdPlaceholder');  
        pwdPlaceholder.show();  
        pwdField.hide();  
          
        pwdPlaceholder.focus(function(){  
            pwdPlaceholder.hide();  
            pwdField.show();  
            pwdField.focus();  
        });  
          
        pwdField.blur(function(){  
            if(pwdField.val() == '') {  
                pwdPlaceholder.show();  
                pwdField.hide();  
            }  
        });
	}
</script>

</body>
</html>
