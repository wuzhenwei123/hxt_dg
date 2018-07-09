<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.Date"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<html>
<head>
<meta charset="utf-8">
<meta content="textml;charset=utf-8" http-equiv="content-type">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<meta content="webkit|ie-comp|ie-stand" name="renderer">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${_title}-查询电费</title>
	<meta name="keywords" content="${_keywords}" />
	<meta name="description" content="${_description}" />
	<link rel="stylesheet" type="text/css" href="${ctx}/public/css/nav.css">
<style type="text/css">
	.codeBtn1 {
	    /* margin-left: 11px; */
	    font-size: 14px;
	    color: #01a796;
	    line-height: 38px;
	    overflow: hidden! important;
	    width: 120px! important;
	    height: 40px! important;
	    text-align: center;
	    cursor: pointer;
	}
	.loginBg{
		background-repeat:no-repeat\9; 
		background-image:none\9; 
		filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='${ctx}/public/images/loginBg.jpg', sizingMethod='scale')\9; 
	}
</style>
</head>
<body>
<input type="hidden" id="projectPath" value="${ctx}"/>
<input type="hidden" id="lastLoginDate" value="<fmt:formatDate value="${admin_user.lastLogin}" pattern="yyyy-MM-dd "></fmt:formatDate>">
<input id="cId" type="hidden" value="${admin_user.companyId}" />
<input type="hidden" id="nowDate" value="<fmt:formatDate value="<%=new Date() %>" pattern="yyyy年MM月dd日"/>">
<%@ include file="/WEB-INF/page/public/common/top.jsp" %>
<!---->
<div class="bannerBox loginBg" style="background-image:url(${ctx}/public/images/loginBg.jpg);">
    <div class="box loginBox">    
        <div class="loBox">
            <ul class="iloginItemsBox">
                <li class="iloginItems">
                    <div class="iItems" style="border:none;background-color:transparent;margin-top:0px;height: 10px;"></div>
                </li>
                <li class="iloginItems">
                    <div class="iItems on">
                        <input class="loTxt iTxt1 iuser" type="text" value="" placeholder="请输入用户缴费号" id="ammeterNo" style="padding-left:9px;"/>
                    </div>
                </li>
                <li class="iloginItems">
                    <div class="iItems">
                        <input class="loTxt iTxt1 ipwd" type="text" value="" placeholder="请输入手机号码" id="phone" style="padding-left:9px;"/>
                    </div>
                </li>
                <li class="iloginItems" id="imgcodeDiv">
                    <div class="iItems lines code_txt">
                        <input class="loTxt" type="text" value="" placeholder="验证码" id="imgcode"/>
                    </div>
                    <div class="lines code_c"><img src="${ctx }/manageAdminUser/pcrimg" onclick="getImg()" id="codeimg" style="height:40px;width:120px;"></div>
                </li>
                <li class="iloginItems">
                    <div class="iItems lines code_txt">
                        <input class="loTxt" type="text" value="" placeholder="手机验证码" id="vercode"/>
                    </div>
                    <div class="lines code_c"><a class="lines pItem codeBtn1" onclick="sendCode()" id="codeBtn" href="javascript:void(0)" style="background-color: #01a796;color:#fff">获取验证码</a></div>
<!--                     <p class="iNoPwd"><a href="javascript:void(0)"></a></p> -->
                    
                </li>
             <%--     <div class="items" style="display:<c:if test="${empty session.errCount||session.errCount<2 }">none</c:if>;" id="imgcodeDiv">
	              <ul class="searItems codeBox">
	                    <li class="fl searItem">
	                        <input class="input" type="text" placeholder="" />
	                    </li>
	                    <li class="fr searItem ">
	                    		<div class="imgCode"><img src="${ctx }/manageAdminUser/pcrimg" onclick="getImg()" id="codeimg" style="height:40px;width:140px;"></div>
	                    </li>
	              </ul>
	            </div> --%>
                <li class="iloginItems">
                    <a class="iBtn" href="javascript:showDetail();" style="margin-top:13px;">查询</a>
                </li>
            </ul>
        </div>
        
        <div class="loginRightBox">
            <div class="minBox">
            
<!--             <div class="pTitle"><img src="${ctx}/public/images/jgzy.jpg" /></div> -->
            <div class="pTitle" style="color: #01a796;cursor: pointer;text-align:center;" onclick="shotts()">如何查找您的抄表电缴费号信息？</div>
                <div class="pContentBox">
                	<div class="zyBoxRem" style="color: #01a796;">
                		<p>1、您可以在以往的抄表电交费通知单上找到。</p>
                	</div>
                	<div class="zyBox"><img src="${ctx}/public/images/zy.jpg" /></div>
                    <div class="zyBoxRem" style="color: #01a796;">
                    	<p>2、您可以在以往的抄表电交费发票上找到。</p>
                        <p>3、您可以拨打国家电网24小时客服热线 95598 ，提供用电地址信息，咨询人工客服查询。</p>
                    </div>
                </div>
        	</div>
         </div>   
        
        
    </div>
</div>   
<input type="hidden" id="errCount" value="${errCount }" readonly="readonly"/>
<%@ include file="/WEB-INF/page/public/common/footer.jsp" %>
<script src="${ctx}/public/js/jquery-1.8.2.min.js"></script>
<script src="${ctx}/public/xcConfirm/js/xcConfirm.js"></script>
<script src="${ctx}/js/area_public.js"></script>
<script src="${ctx}/public/custom/public/js/custominput.js"></script>
<script src="${ctx}/public/js/myPlaceholder.js"></script>
<script>
$(function(){ });
	function showDetail() {
		if(!$("#ammeterNo").val()){
	    	$HXT.showInfo('请填写缴费号!');
	    	return;
	    }else{
	    	var reg = /^[\d.\-_]+$/;
    		if(!reg.test($("#ammeterNo").val())){
    			$HXT.showInfo("请您输入正确的缴费号!");
    			return false;
    		}
	    }
		var reg = /^1\d{10}$/;
	    if(!reg.test($("#phone").val())){
	    	$HXT.showInfo('手机号码不正确!');
	    	return;
	    }
	    if(!$("#vercode").val()){
	    	$HXT.showInfo('请填写手机验证码!');
	    	return;
	    }
	    var url = "<c:url value='/hCommon/queryAmmeterInfo'/>";
		$.getJSON(url,{
					ammeterNo : $("#ammeterNo").val(),
					phone : $("#phone").val(),
					vercode : $("#vercode").val(),
					_r : Math.random()
				},
				function(data) {
					var adminId = '${admin_user.adminId}';
					if(data.status == 'success'){
						var data = data.data;
						if(data.totalFee>0&&data.totalFee%100==0){
							$HXT.showInfo("您的电表缴费号还未完成抄表，暂时没有欠费。");
						}else{
							var title = '您的缴费号信息如下:';
							var html = '<div class="wInfoBox_p">';
							html += '<ul class="wInfoBox">';
							html += '<li class="clearFix"><label class="fl">缴费号：</label><span class="fr">'+$("#ammeterNo").val()+'</span></li>';
							html += '<li class="clearFix"><label class="fl">客户名称：</label><span class="fr">'+data.resultInfo.accountName+'</span></li>';
							html += '<li class="clearFix"><label class="fl">用电地址：</label><span class="fr">'+data.resultInfo.address+'</span></li>';
							html += '<li class="clearFix"><label class="fl">账单金额：</label><span class="fr">'+data.resultInfo.accountFee+'</span></li>';
							if(data.resultInfo.lateFee!=""){
								var lateFee = data.resultInfo.lateFee;
								lateFee = lateFee.replace("。","");
								html += '<li class="clearFix"><label class="fl">滞纳金：</label><span class="fr">'+lateFee+'</span></li>';
							}
							html += '<li class="clearFix"><label class="fl">应缴金额：</label><span class="fr">'+data.totalFee+'元</span></li>';
							html += '</ul>';
							html += '</div>';
		
							var option = {
								title : title,
								btn : parseInt("0011", 2),
								isOkBtn : true,
								onOk : function() {
									
									if(adminId==""){
										window.location.href="${ctx}/index/toRegister";
									}else{
										window.location.href="${ctx }/public/payFee/index";
									}
								},
								onClose : function() {
									$("#ammeterNo").val("");						
									$("#phone").val("");						
									$("#vercode").val("");
									$("#imgcode").val("");
									$("#codeBtn").html('发送验证码');
									$("#codeBtn").attr('disabled',false);
									$("#codeBtn").attr('onclick','sendCode()');
									clearInterval(intervalID);
									var arrayInput = document.getElementsByTagName("input");
									for( var i = 0; i < arrayInput.length; i ++){
										myPlaceholder(arrayInput[i]);
									}
									getImg();
								}
//	 							class : [ 'okAddressBox', 'on' ]
							}
		
							var classArr = ['okAddressBox','on'];
							window.wxc.xcConfirm(html, "custom", option);
							setXcConfirmClass(classArr);
							var isOkBtn = true;
							if(isOkBtn){
								$('.'+classArr[0] + " .popBox .cancel").remove();
							}
							
							if(adminId==""){
								$("."+classArr[1] +" .popBox .sgBtn.ok").html('前往注册');
							}else{
								$("."+classArr[1] +" .popBox .sgBtn.ok").html('前往缴费');
							}
						}
						
						
// 						$('#imgcodeDiv').hide();
					}else{
						if(data.msg){
							$HXT.showInfo(data.msg);
						}else{
// 							var itemjson = data.items;
// 			 				var errCount = JSON.parse(itemjson).errCount;
// 			 				if(errCount>=2){
// 			 					if($("#errCount").val()){
// 			 						$("#errCount").val(errCount)
// 			 					}else{
// 			 						$("#errCount").val(1);
// 			 					}
			 					$HXT.showInfo("验证码不正确");
// 		 						$('#imgcodeDiv').show();
// 			 				}else{
// 								$HXT.showInfo(data.message)
// 			 				}
						}
					}
				});
					
				
	}
	function getImg(){
		var url = '${ctx }/manageAdminUser/pcrimg?_t='+Math.random();
		$("#codeimg").attr("src",url);
	}
	
	var dxflag = true;
	
	function sendCode(){
		var phone = $.trim($("#phone").val());
		if(phone == ''){
			$HXT.showInfo("请填写您的手机号");
		}else{
			if(!validatemobile(phone)){
				$HXT.showInfo('业务联系人手机格式不正确');
				return false;
			}
			
			if(dxflag){
				dxflag = false;
				//验证发送次数
			    var url1 = "<c:url value='/hMessageLog/getMessageLogCount'/>";
			    $.getJSON(url1,{
					phone : $("#phone").val(),
					_r : Math.random()
				},function(data) {
					dxflag = true;
					if(data.code=='1'){
						if(data.message=='ok'){
//	 						if($("#errCount").val()>=2){
								if(!$("#imgcode").val()){
									$HXT.showInfo('请填写图片验证码！');
									$("#imgcode").focus();
									return false;
								}
//	 						}
							$.post("<c:url value='/index/sendVercode'/>",
					       	{
								phone :phone,
								vercode : $("#imgcode").val(),
								ranNum:Math.random()
							},
					       	function(data){
					        	var result = eval('('+data+')'); 
					            if (result.code == '1') {
					            	$HXT.showInfo("发送成功，请注意查收验证码");
					            	btnNum();
					             } else {
					 				$HXT.showInfo("手机验证码不正确");
					             }
					        });
						}else{
							$HXT.showInfo("您的手机号一小时内在本网站接收短信已超过5条，由于短信相关法规关于短信数量的限制，本网站无法再给您发送短信，请您过一小时后再进行此项操作。");
							$(".popBox").css("height", "245px");
						}
					}
				});
			}
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
	var intervalID;
	function btnNum(){
		var num = 59;
		$("#codeBtn").attr('disabled',true);
		$("#codeBtn").html('还剩'+num-- +'秒');
		intervalID = setInterval(function(){
			if(num>0){
				$("#codeBtn").attr('disabled',true);
				$("#codeBtn").attr('onclick','');
				$("#codeBtn").html('还剩'+num+'秒');
				num--;
			}else{
				$("#codeBtn").html('发送验证码');
				$("#codeBtn").attr('disabled',false);
				$("#codeBtn").attr('onclick','sendCode()');
				clearInterval(intervalID);
				getImg();
			}
		}, 1000);
	}
	
	function setXcConfirmClass(className){
		//$(".xcConfirm").addClass(className);
		
		if( className && className.length > 0){
			for(var i = 0; i <className.length ; i ++){
				$(".xcConfirm").addClass(className[i]);
			}
			
		}
	}
	
	function shotts(){
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
	}
</script>
</body>
</html>