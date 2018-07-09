<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta content="textml;charset=utf-8" http-equiv="content-type">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<meta content="webkit|ie-comp|ie-stand" name="renderer">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${ctx }/public/custom/public/css/customForm.css">
<link rel="stylesheet" type="text/css" href="${ctx }/public/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/public/css/nav.css">
<style type="text/css">
.starFont1 {
    width: 8px;
    height: 8px;
    vertical-align: middle;
    /* background: url(../images/star.png) center center no-repeat scroll; */
    margin-right: 5px;
}
    .pContentBox {
	    padding-left: 280px! important;
	    padding-top: 10px;
	}
	
	.codeTitle {
	    width: 85px;
	    font-size: 14px;
	    color: #000000;
	    vertical-align: middle;
	    margin-right: 30px! important;
	}
	.pBtnBox {
	    padding-top: 10px;
	    width: 300px! important;
	    margin-left: 130px;
	}
	
	.in-radio, .in-checkbox {
	    position: relative;
	    overflow: hidden;
	    display: inline-block;
	    margin-left: 130px;
	}
/* 	.popBox { */
/* 	    height: 480px! important; */
/* 	} */
	#xxx {
	    height: 54px! important;
	}
</style>
<title>${_title}</title>
	<meta name="keywords" content="${_keywords}" />
	<meta name="description" content="${_description}" />
</head>
<body>
<%@ include file="/WEB-INF/page/public/common/top.jsp" %>

<div class="box minBox">
	<div class="jfMsg jbReg">恒信通，您贴心的电费管家</div>
	    <div class="pContentBox">
	    	
	    <!-- 	<div class="lineP pItems">
	        	<font class="lines starFont">*</font>
	        	<label class="lines codeTitle">企业名称：</label>
	        	<div class="lines pItem">
	            	<input class="pInput" placeholder="企业名称" type="text" id="aCompanyName"/>
	            </div>
	        </div> -->
<!-- 	    	<div class="lineP pItems"> -->
<!-- 	        	<font class="lines starFont">*</font> -->
<!-- 	        	<label class="lines codeTitle">企业联系人姓名：</label> -->
<!-- 	        	<div class="lines pItem"> -->
<!-- 	            	<input class="pInput" placeholder="企业联系人姓名" type="text" id="aContactName"/> -->
<!-- 	            </div> -->
<!-- 	        </div> -->
	    	<div class="lineP pItems">
	    		<font class="lines starFont">*</font>
	    		<label class="lines codeTitle">抄表电缴费号：</label>
	        	<div class="lines pItem">
	            	<input class="pInput" type="text" id="aAmmeterNo"/>
	            </div>
	            <a id="jfhaoInfo" class="lines pIInfo" href="javascript:void(0);">如何查找缴费号</a>
	        </div>
	        <div class="lineP pItems">
	        	<font class="lines starFont">*</font>
	        	<label class="lines codeTitle">联系人手机号码：</label>
	        	<div class="lines pItem">
	            	<input class="pInput" type="text" id="aContact_phone"/>
	            </div>
	            <span class="lines pIInfo pColor" id="phone_tip">请输入联系人的11位手机号码</span>
	        </div>
	        <div class="lineP pItems">
	        	<font class="lines starFont">*</font>
	        	<label class="lines codeTitle">验证码：</label>
	        	<div class="lines pItem code">
	            	<input class="pInput" type="text" id="imgcode" />
	            </div>
	            <a class="lines pItem codeBtn imgCode" href="javascript:getImg();">
	            	<img id="vcode" alt="" src="${ctx }/manageAdminUser/pcrimg" style="width: 120px;height: 36px;">
	            </a>
	        </div>
	        <div class="lineP pItems">
	        	<font class="lines starFont">*</font>
	        	<label class="lines codeTitle">短信验证码：</label>
	        	<div class="lines pItem code">
	            	<input class="pInput" type="text" id="vercode"/>
	            </div>
	            <a class="lines pItem codeBtn" onclick="sendCode()" href="javascript:void(0)" id="codeBtn" style="background-color: #01a796;color:#fff">获取验证码</a>
	            <span class="lines pIInfo pColor">请输入您接收到的短信验证码</span>
	        </div>
	        <div class="lineP pItems">
	        	<font class="lines starFont1"></font>
	        	<label class="lines codeTitle">推荐人手机号：</label>
	        	<div class="lines pItem">
	            	<input class="pInput" type="text" id="recPhone" value="${recPhone}" <c:if test="${not empty recPhone}">readonly="readonly"</c:if>/>
	            </div>
	        </div>
	        <div class="pItems xyItems">
	     		
	            <div>
	                <div class="in-checkbox">
	                    <input type="checkbox" id="checkbox_a" name="checkbox_a" checked="checked"/>
	                    <label for="checkbox_a" id="ck">
	                        <span>我同意并遵守<a href="${ctx }/public/xieyi" target="_blank">《恒信通企业版网站服务协议》</a></span>
	                    </label>
	                </div>
	            <span class="clear"></span>
	            </div>
	            <div class="pBtnBox">
	            	<a class="iBtn" href="javascript:void(0)" onclick="add()">注册</a>
	            </div>
	            
	        </div>
	        
	    </div>
</div>
    
<%@ include file="/WEB-INF/page/public/common/footer.jsp" %>

<script src="${ctx }/public/js/jquery-1.8.2.min.js"></script>
<script src="${ctx }/public/custom/public/js/custominput.js"></script>
<script src="${ctx}/public/xcConfirm/js/xcConfirm.js"></script>
<script src="${ctx}/js/area_public.js"></script>
<!-- <script src="${ctx}/public/js/myPlaceholder.js"></script> -->
<script>
	function tip(id){
		$("#"+id).css("color","red");
	}
	function un_tip(id){
		$("#"+id).css("color","#999999");
	}
	var flag = true;
	function add(){
		var contact_phone = $("#aContact_phone").val();
		var recPhone = $("#recPhone").val();
		var vercode = $("#vercode").val();
		var ammeterNo = $("#aAmmeterNo").val();
		var companyName = contact_phone;
		var contactName= contact_phone;
		if($("#ck").attr('class') == ''){
			$HXT.showInfo('请先阅读服务协议');
			return false;
		}
		if(!validatemobile(contact_phone)){
			$HXT.showInfo('联系人手机格式不正确');
			tip("phone_tip");
			return false;
		}else{
			un_tip("phone_tip");
		}
		if(!vercode){
			$HXT.showInfo('短信验证码不能为空');
			return false;
		}
		if(!companyName){
			$HXT.showInfo('请填写企业名称');
			return false;
		}
		if(!ammeterNo){
			$HXT.showInfo('请填写缴费号');
			return false;
		}
		if(!contactName){
			$HXT.showInfo('请填写企业联系人姓名');
			return false;
		}
		
		var reg = /^[\d.\-_]+$/;
		if(!reg.test(ammeterNo)){
			$HXT.showInfo("请您输入正确的缴费号");
			return false;
		}
		
		if(recPhone!=""){
			if(!validatemobile(recPhone)){
				$HXT.showInfo('推荐人手机格式不正确');
				return false;
			}
		}
		
	    var url = '${ctx}/hCommon/getAmmeterInfo?electricNum=' + ammeterNo;
	    $.ajax({
	        type: "GET",
	        url: url,
	        dataType: "json",
	        success: function (data) {
	            if (data.status == 'success') {
	                if (eval(data.data).resultCode == '00') {//电表存在
	                    showDetail(data.data,ammeterNo,flag,contact_phone,vercode,companyName,contactName);
	                } else {
	                    $HXT.showInfo("该电表不存在，请检查重新输入！");
	                }
	            } else {
	                $HXT.showInfo(data.msg);
	                getImg();
	            }


	        }
	    });
	}

	var dxflag = true;//防止短信重复发送
	function sendCode(){
		var phone = $.trim($("#aContact_phone").val());
		if(phone == ''){
			$HXT.showInfo("请填写联系人的手机号");
			$("#aContact_phone").focus();
		}else{
			if(!validatemobile(phone)){
				$HXT.showInfo('联系人手机格式不正确');
				$("#aContact_phone").focus();
				tip("phone_tip");
				return false;
			}else{
				un_tip("phone_tip");
			}
			if(!$("#imgcode").val()){
				$HXT.showInfo('请先输入图形验证码中的数字！');
				$("#imgcode").focus();
				return false;
			}
			if(dxflag){
				dxflag = false;
				$.post("<c:url value='/hCompany/vercode'/>",
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
		            	if(result.message == '验证码不正确'){
			            	getImg();
		            	}
		             }
		        });
			}
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
				getImg();
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
		var myreg = /^(0|86|17951)?(13[0-9]|15[012356789]|17[0-9]|18[0-9]|14[57])[0-9]{8}$/;
		if (!myreg.test(mobile)) {
			return false;
		}
		return true;
	}
	
	function showDetail(data,ammeterNo,flag,contact_phone,vercode,companyName,contactName){
		var html = '<div class="wInfoBox_p">';
		html += '<ul class="wInfoBox">';
		html += '<li class="clearFix"><label class="fl">缴费号：</label><span class="fr">'+ammeterNo+'</span></li>';
		html += '<li class="clearFix"><label class="fl">客户名称：</label><span class="fr">'+data.resultInfo.accountName+'</span></li>';
		html += '<li class="clearFix"><label class="fl">用电地址：</label><span class="fr">'+data.resultInfo.address+'</span></li>';
		html += '<li class="clearFix"><label class="fl">账单金额：</label><span class="fr">'+data.resultInfo.accountFee+'</span></li>';
		html += '<li class="clearFix"><label class="fl">滞纳金：</label><span class="fr">'+data.resultInfo.lateFee+'</span></li>';
		html += '<li class="clearFix"><label class="fl">应缴金额：</label><span class="fr">'+data.totalFee+'元</span></li>';
		html += '</ul>';
		html += '</div>';
		var option = {
			title : "您输入的电表缴费号欠费信息如下",
			btn : parseInt("0011", 2),
			isOkBtn : true,
			onOk : function() {
				if(flag){
					flag = false;
					$.post("<c:url value='/hCompany/addHCompany'/>",
				        	{
					    		contact_phone : contact_phone,
					    		vercode : vercode,
					    		ammeterNo:ammeterNo,
// 					    		name:companyName,
					    		contact_name:'',
					    		ammeterAddress:data.resultInfo.address,
					    		recPhone:$("#recPhone").val(),
					    		action : 'reg',
							 _t:Math.random()},
				        	function(data){
					        	var result = eval('('+data+')');
					            if (result.code == '1') {
// 					            	$HXT.showInfo(result.message);
// 					            	window.location.href='${ctx}/index'
// 									$('.' + option.class[0] + " .popBox .cancel").remove();
					            	showSuccess(contact_phone);
					             } else {
					            	 flag = true;
// 					            	 $('.' + option.class[0] + " .popBox .cancel").remove();
					            	 $HXT.showInfo(result.message);
					             }
				        });
				}
			},
// 			onClose : function() {
// 				window.location.href="${ctx}/index/toRegister?recPhone=${recPhone}";
// 			}
			
// 			class : [ 'okAddressBox', 'on' ]
		}
		
		var classArr = ['okAddressBox','on'];
		window.wxc.xcConfirm(html, "custom", option);
		setXcConfirmClass(classArr);
		var isOkBtn = true;
		if(isOkBtn){
			$('.'+classArr[0] + " .popBox .cancel").remove();
		}
		$("."+classArr[0] +" .popBox .sgBtn.ok").html('确定无误，继续注册');

// 		window.wxc.xcConfirm(html, "custom", option);
		

// 		if (option.isOkBtn) {
// 			$('.' + option.class[0] + " .popBox .cancel").remove();
// 		}
	}
	
	function showSuccess(contact_phone){
		var title = '恭喜您注册成功';
		
		var html = '<div class="reBoxMsg"><i class="lines"></i>恭喜您注册成功，您的登录账号为注册时使用的手机号码（'+contact_phone+'），登录密码为手机号码的后6位，请您登录后设置更加安全的密码。如果您还有其他缴费号，请立即登录继续绑定。</div>';
		
		
		var option = {
			title: title,
			btn: parseInt("0011",2),
			isOkBtn : true,
			
			onOk: function(){
				window.location.href='${ctx}/index/loginR?contact_phone='+contact_phone;
			},
// 			onCancel:function(){ // 下一步回调函数
			
// 			},
// 			class : ['okAddressBox','on','re']
		}
		
		var classArr = ['okAddressBox','on','re'];
		window.wxc.xcConfirm(html, "custom", option);
		setXcConfirmClass(classArr);
		
		$("."+classArr[1] +" .popBox .sgBtn.ok").html('立即登录');
		$(".ttBox").css('padding-left','220px')
		$(".ttBox").css('color','#1bb0a1')
		$('.'+classArr[1] + " .popBox .cancel").remove();
		
// 		window.wxc.xcConfirm(html, "custom", option);
		
// 		初始化 表单控件效果
// 		$('input').customInput();
// 		newObject();
		
// 		$("."+option.class[1] +" .popBox .sgBtn.ok").html('立即登录');
// 		$('.'+option.class[1] + " .popBox .cancel").remove();
	}
	
	function getImg(){
		var url = '${ctx }/manageAdminUser/pcrimg?_t='+Math.random();
// 		$(".imgCode").css("background-image","url("+url+")");
		$("#vcode").attr("src",url);
	}
	
$(function(){
		
		// 您的缴费信息号为
		$("#jfhaoInfo").click(function(){
			
			
			var title = '如何查找您的抄表电费缴费号信息?';
			
			var html = '<div class="wInfoBox_p">';
					html += '<p>1、您可以在以往的抄表电交费通知单上找到。</p>';
					html += '<div class="re_RH_IMGbOX"><img src="${ctx}/public/images/zy.jpg" /></div>';
					html += '<p>2、您可以在以往的抄表电交费发票上找到。</p>';
					html += '<p id="xxx">3、您可以拨打国家电网24小时客服热线 95598 ，提供用电地址信息，咨询人工客服查询。</p>';
				html += '</div>';
			
			
			var option = {
						title: title,
						btn: parseInt("0011",2),
						isOkBtn : true,
						onOk: function(){
						},
					//	class : ['okAddressBox','on']
					}
					var classArr = ['okAddressBox','on','reJiaofei'];
					window.wxc.xcConfirm(html, "custom", option);
					setXcConfirmClass(classArr);
					
					
					
					
					if(option.isOkBtn){
						$('.'+classArr[0] + " .popBox .cancel").remove();
					}
					$("#xxx").css("height","54px");
					$(".popBox").css("height","480px");
			
		});
		
// 		$('input').placeholder();
		
	})
	function setXcConfirmClass(className){
		//$(".xcConfirm").addClass(className);
		
		if( className && className.length > 0){
			for(var i = 0; i <className.length ; i ++){
				$(".xcConfirm").addClass(className[i]);
			}
			
		}
	}
	
</script>
</body>
</html>
