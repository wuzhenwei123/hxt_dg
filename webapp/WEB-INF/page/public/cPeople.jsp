<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<!DOCTYPE html>
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
    <link href="${ctx}/css/weui.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/public/css/nav.css">
    <style type="text/css">
    .cContentBox {
	    padding-left: 182px! important;
	    padding-top: 10px;
	}
	
	.codeTitle {
	    width: 85px;
	    font-size: 14px;
	    color: #000000;
	    vertical-align: middle;
	    margin-right: 50px! important;
	}
	.pBtnBox {
	    padding-top: 10px;
	    width: 300px! important;
	    margin-left: 148px;
	}
    </style>
</head>
<body>
<%@ include file="/WEB-INF/page/public/common/top.jsp" %>

<!---->
<div class="box main">
	<%@ include file="/WEB-INF/page/public/common/left.jsp" %>
    <div class="rightBox">
    	<div class="commBody minHeight">
        	<div class="pTitle">变更联系人信息</div>
            <p class="yMsg"></p>
            <div class="cContentBox">
                <div class="lineP pItems">
                    <font class="lines starFont">*</font>
                	<label class="lines codeTitle">业务联系人姓名：</label>
                    <div class="lines pItem">
                    	<input type="text" class="pInput" id="aContact_name" value="${contact_name }">
                    </div>
                </div>
                <div class="lineP pItems">
                    <font class="lines starFont">*</font>
                	<label class="lines codeTitle">业务联系人手机号：</label>
                    <div class="lines pItem">
                    	<input type="text" id="aContact_phone" class="pInput" value="${contact_phone }">
                    </div>
                </div>
                <div class="lineP pItems">
                    <font class="lines starFont">*</font>
                	<label class="lines codeTitle">验证码：</label>
                    <div class="lines pItem code">
                        <input class="pInput" type="text" id="validate_code" />
                    </div>
                    <a class="lines pItem codeBtn imgCode" href="javascript:getImg()">
                    	<img id="vcode" alt="" src="${ctx }/manageAdminUser/pcrimg" style="width: 120px;height: 36px;">
                    </a>
                </div>
                <div class="lineP pItems">
                    <font class="lines starFont">*</font>
                	<label class="lines codeTitle">手机验证码：</label>
                    <div class="lines pItem code">
                        <input class="pInput" type="text" id="phone_code"/>
                    </div>
                    <a class="lines pItem codeBtn" style="background-color: #01a796;color: #fff;" onclick="sendCode()" id="codeBtn" href="javascript:void(0)">获取验证码</a>
                    
                </div>
                <div class="pItems xyItems">
                    <div class="pBtnBox">
                    	<a href="javascript:void(0)" onclick="sub()" class="iBtn">确定</a>
                    </div>
                </div>
                
            </div>
        	
        </div>
    </div>
</div>

	<%@ include file="/WEB-INF/page/public/common/footer.jsp" %>

<script src="${ctx}/public/js/jquery-1.8.2.min.js"></script>
<script src="${ctx}/public/xcConfirm/js/xcConfirm.js"></script>
<script src="${ctx}/js/area_public.js"></script>
<script src="${ctx}/public/js/myPlaceholder.js"></script>
<script>
var dxflag = true;
function sendCode(){
	var phone = $.trim($("#aContact_phone").val());
	if(phone == ''){
		$HXT.showInfo("请填写您的手机号");
	}else{
		if(!validatemobile(phone)){
			$HXT.showInfo("业务联系人手机格式不正确");
			return false;
		}
		if(dxflag){
			dxflag = false;
			$.post("<c:url value='/public/ePeople/vercode'/>",
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
	            	btnNum();
	             } else {
	            	countdown = 0;
	            	$HXT.showInfo(result.message);
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
	function sub(){
		var contact_name = $.trim($("#aContact_name").val());
		var contact_phone = $.trim($("#aContact_phone").val());
		var validate_code = $.trim($("#validate_code").val());
		var phone_code = $.trim($("#phone_code").val());
		if(contact_name == ''){
			$HXT.showInfo("请输入业务联系人名称");
			return false;
		}
		
		if(contact_phone == ''){
			$HXT.showInfo("请输入业务联系人手机");
			return false;
		}
		if(phone_code == ''){
			$HXT.showInfo("请输入手机验证码");
			return false;
		}
		if(validate_code == ''){
			
			$HXT.showInfo("请输入右侧验证码");
			return false;
		}
		if(!validatemobile(contact_phone)){
			$HXT.showInfo("业务联系人手机格式不正确");
			return false;
		}
		$.post("${ctx}/public/hCompany/modify2",
       	{
    		contact_name : contact_name,
    		contact_phone : contact_phone,
    		validate_code:validate_code,
    		phone_code:phone_code,
			ranNum:Math.random()
		},
       	function(data){
        	var result = eval('('+data+')'); 
            if (result.code == '1') {
            	var oldcontact_phone= '${contact_phone }';
            	if(contact_phone==oldcontact_phone){
            		shownewtext("更改成功");
            	}else{
            		shownewtext("更改成功，您的新登录账号为"+contact_phone+"，登录密码为手机号后6位，请您登录后设置成更加安全的密码。");
            	}
            	setInterval(function(){ window.location.href = "${ctx}/manageAdminUser/cloginout" },1500);
             } else {
            	 $HXT.showInfo(result.message);
             }
        });
	}
	function sysalert(msg){
		$("#msg").html(msg);
		$(".weui_dialog_alert").show();
	}
	function closeAlert(){
		$(".weui_dialog_alert").hide();
	}
	
	function getImg(){
		var url = '${ctx }/manageAdminUser/pcrimg?_t='+Math.random();
// 		$(".imgCode").css("background-image","url("+url+")");
		$("#vcode").attr("src",url);
	}
	
	function shownewtext(content){
		var title = '&nbsp;';
		var html = '<div class="bgTitle">'+content+'</div>';
		var option = {
			title: title,
			btn: parseInt("0011",2),
			isOkBtn : true,
			onOk : function() {
				window.location.href = "${ctx}/manageAdminUser/cloginout";
			}
		}
		var classArr = ['okAddressBox','on','re','bgPeo'];
		window.wxc.xcConfirm(html, "custom", option);
		setXcConfirmClass(classArr);
		$("."+classArr[1] +" .popBox .sgBtn.ok").html('确定');
		$('.'+classArr[1] + " .popBox .cancel").remove();
	}
</script>
</body>
</html>
