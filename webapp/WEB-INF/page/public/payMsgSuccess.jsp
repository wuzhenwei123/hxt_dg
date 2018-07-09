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
    <link href="${ctx}/css/weui.css" rel="stylesheet" type="text/css" />
    
    <link rel="stylesheet" type="text/css" href="${ctx}/public/custom/public/css/customForm.css">
<link rel="stylesheet" type="text/css" href="${ctx}/public/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/public/css/hxtPay.css">
<link rel="stylesheet" type="text/css" href="${ctx}/public/custom/public/css/customForm.css">
<link rel="stylesheet" type="text/css" href="${ctx}/public/css/nav.css">
<style type="text/css">
.cContentBox {
    padding-left: 165px! important;
    padding-top: 10px;
}
.pItem1 {
    height: 32px;
    border-radius: 4px;
    overflow: hidden;
    vertical-align: middle;
    width: 300px;
    position: relative;
}
.pItem1 .pInput{
	border:0 none;
	height:30px;
	line-height:30px;
	display:block;
	width:100%;
	padding-left:10px;
	padding-right:10px;
	font-size:14px;
	color:#333333;
}
</style>
</head>
<body>
<%@ include file="/WEB-INF/page/public/common/top.jsp" %>

<!---->
<div class="box main">
	<%@ include file="/WEB-INF/page/public/common/left.jsp" %>
    <div class="rightBox jyListBox">
    	<div class="commBody jySearchBox m_pay pay_info_box">
            
            <div class="m_pay_top_items on">
                <div class="m_pay_top_item b2b">
                    <p>手机缴费信息管理</p>
                </div>
            </div>
            
            <input type="hidden" value="${hProxyMessage.id}" id="proxyId">
            
            <div class="cContentBox m_pay_info">
                <div class="lineP pItems">
                    <label class="lines pay_label_item on"><font class="red">*</font>单位名称：</label>
                    <div class="lines pItem1">
                    	<input type="text" placeholder="单位名称" class="pInput" id="proxyName" value="${hProxyMessage.proxyName}" readonly="readonly">
                    </div>
                </div>
                <div class="lineP pItems">
                    <label class="lines pay_label_item on"><font class="red">*</font>开户银行名称：</label>
                    <div class="lines pItem1">
                    	<input type="text" placeholder="开户银行名称" class="pInput" id="bank_name" value="${hProxyMessage.bank_name}" readonly="readonly">
                    </div>
                </div>
                <div class="lineP pItems">
                    <label class="lines pay_label_item on"><font class="red">*</font>开户行行号：</label>
                    <div class="lines pItem1">
                    	<input type="text" placeholder="开户行行号" class="pInput" id="bank_number" readonly="readonly" value="${hProxyMessage.bank_number}" readonly="readonly">
                    </div>
                </div>
                <div class="lineP pItems">
                    <label class="lines pay_label_item on"><font class="red">*</font>账户单位名称：</label>
                    <div class="lines pItem1">
                    	<input type="text" placeholder="开户账号名称" class="pInput" id="payAccountName" value="${hProxyMessage.payAccountName}" readonly="readonly">
                    </div>
<!--                     <p class="m_pay_rem">该名称为您单位支付的账号名称，应与单位名称一致</p> -->
                    
                </div>
                <div class="lineP pItems">
                    <label class="lines pay_label_item on"><font class="red">*</font>开户账号：</label>
                    <div class="lines pItem1">
                    	<input type="text" placeholder="开户账号" class="pInput" id="payBankNumber" value="${hProxyMessage.payBankNumber}" readonly="readonly">
                    </div>
<!--                     <p class="m_pay_rem">该账号为您手机支付业务的付款账号</p> -->
                    
                </div>
                <div class="lineP pItems">
                    <label class="lines pay_label_item on"><font class="red">*</font>账户负责人姓名：</label>
                    <div class="lines pItem1">
                    	<input type="text" placeholder="账户负责人姓名" class="pInput" id="payName" value="${hProxyMessage.payName}" readonly="readonly">
                    </div>
<!--                     <p class="m_pay_rem">该负责人为有权通过上述账号支付的财务负责人</p> -->
                    
                </div>
                <div class="lineP pItems">
                    <label class="lines pay_label_item on"><font class="red">*</font>账户负责人证件类型：</label>
                    <dl class="lines pItem1 selPIten" style="width:307px;" readonly="readonly">
                        <dd class="lines" style="width:100%;">
                            <div class="diy_select select1">
                                <input type="hidden" name="payCardStyle" class="diy_select_input" id="payCardStyle" value="${hProxyMessage.payCardStyle}">
                                <div class="diy_select_txt">
                                	<c:if test="${empty hProxyMessage}">账户负责人证件类型</c:if>
                                	<c:if test="${not empty hProxyMessage&&hProxyMessage.payCardStyle==1}">身份证</c:if>
                                	<c:if test="${not empty hProxyMessage&&hProxyMessage.payCardStyle==2}">护照</c:if>
                                	<c:if test="${not empty hProxyMessage&&hProxyMessage.payCardStyle==3}">军官证</c:if>
                                </div>
<!--                                 <div class="diy_select_btn"></div> -->
<!--                                 <ul class="diy_select_list" style="display: none;" readonly="readonly"> -->
<!--                                     <li>身份证</li> -->
<!--                                     <li>护照</li> -->
<!--                                   	<li>军官证</li> -->
<!--                                 </ul> -->
                            </div>
                        </dd>
                    </dl>
                </div>
                <div class="lineP pItems">
                    <label class="lines pay_label_item on"><font class="red">*</font>账户负责人证件号码：</label>
                    <div class="lines pItem1">
                    	<input type="text" placeholder="账户负责人证件号码" class="pInput" id="payCard" value="${hProxyMessage.payCard}" readonly="readonly">
                    </div>
                </div>
                <div class="lineP pItems">
                    <label class="lines pay_label_item on"><font class="red">*</font>账户负责人手机号码：</label>
                    <div class="lines pItem1">
                    	<input type="text" placeholder="账户负责人手机号码" class="pInput" id="payPhoneNumber" value="${hProxyMessage.payPhoneNumber}" readonly="readonly">
                    </div>
<!--                     <p class="m_pay_rem">该手机号作为登录账号和接收业务通知信息使用</p> -->
                </div>
                
                <div class="lineP pItems">
                    <label class="lines pay_label_item on"><font class="lines">&nbsp;</font>账户负责人电子邮箱：</label>
                    <div class="lines pItem1">
                    	<input type="text" class="pInput" id="payMail" value="${hProxyMessage.payMail}" readonly="readonly">
                    </div>
                </div>
                <div class="lineP pItems">
                    <label class="lines pay_label_item on"><font class="lines">&nbsp;</font>账户负责人地址：</label>
                    <div class="lines pItem1">
                    	<input type="text" class="pInput" id="proxyAddress" value="${hProxyMessage.proxyAddress}" readonly="readonly">
                    </div>
                </div>
                <div class="lineP pItems">
                    <label class="lines pay_label_item on"><font class="lines">&nbsp;</font>账户负责人地址邮编：</label>
                    <div class="lines pItem1">
                    	<input type="text" class="pInput" id="proxyCode" value="${hProxyMessage.proxyCode}" readonly="readonly">
                    </div>
                </div>
      
                
               
                
                
            </div>
            
         
            
        </div>
        <div class="lineP up_ok_box">
                
               	<a class="line_l pay_btn" href="${ctx}/hProxyMessage/updateMsg?id=${hProxyMessage.id}">更改资料</a>
                <a class="line_l pay_btn" href="${ctx}/hProxyMessage/toPayOver1">中止合同</a>
           </div>
    </div>
</div>




<%@ include file="/WEB-INF/page/public/common/footer.jsp" %>


<script src="${ctx}/public/js/jquery-1.8.2.min.js"></script>
<script src="${ctx }/public/xcConfirm/js/xcConfirm.js"></script>
<!-- <script src="${ctx}/public/custom/public/js/select.js"></script> -->
<script src="${ctx}/js/area_public.js"></script>
<script src="${ctx}/public/js/myPlaceholder.js"></script>
<script type="text/javascript">
	function save(){
		var proxyId = $("#proxyId").val();
		var proxyName = $("#proxyName").val();
		if(!proxyName){
			$HXT.showInfo('请填写单位名称');
			return;
		}
		var bank_name = $("#bank_name").val();
		if(!bank_name){
			$HXT.showInfo('请填写开户银行名称');
			return;
		}
		var bank_number = $("#bank_number").val();
		if(!bank_number){
			$HXT.showInfo('请填写开户行行号');
			return;
		}
		var payAccountName = $("#payAccountName").val();
		if(!payAccountName){
			$HXT.showInfo('请填写账户单位名称');
			return;
		}
		var payBankNumber = $("#payBankNumber").val();
		if(!payBankNumber){
			$HXT.showInfo('请填写开户账号');
			return;
		}
		var payName = $("#payName").val();
		if(!payName){
			$HXT.showInfo('请填写账户负责人姓名');
			return;
		}
		var payCardStyle = $("#payCardStyle").val();
		if(!payCardStyle){
			$HXT.showInfo('请选择账户负责人证件类型');
			return;
		}else{
			if(payCardStyle=="身份证"){
				payCardStyle = "1";
			}else if(payCardStyle=="护照"){
				payCardStyle = "2";
			}else if(payCardStyle=="军官证"){
				payCardStyle = "3";
			}
		}
		var payCard = $("#payCard").val();
		if(!payCard){
			$HXT.showInfo('请填写账户负责人证件号码');
			return;
		}else{
			if(payCardStyle=="1"){
				if(!IdentityCodeValid(payCard)){
					$HXT.showInfo('请填写正确的账户负责人证件号码');
					return;
				}
			}
		}
		
		var payPhoneNumber = $("#payPhoneNumber").val();
		if(!payPhoneNumber){
			$HXT.showInfo('请填写账户负责人手机号码');
			return;
		}
		
		var payMail = $("#payMail").val();
		var proxyAddress = $("#proxyAddress").val();
		var proxyCode = $("#proxyCode").val();
		if(payMail!=""){
			var filter  = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
			if(!filter.test(payMail)){
				$HXT.showInfo('请输入正确的账户负责人电子邮箱');
				return;
			}
		}
		if(proxyCode!=""){
			var reg = /^\d{6}$/;
			if(!reg.test(proxyCode)){
				$HXT.showInfo('请输入正确的账户负责人地址邮编');
				return;
			}
		}
		$.post("<c:url value='/hProxyMessage/saveProxyMessage'/>",
	        	{
					proxyId:proxyId,
					proxyName:proxyName,
					bank_name:bank_name,
					bank_number:bank_number,
					payAccountName:payAccountName,
					payBankNumber:payBankNumber,
					payName : payName,
					payCardStyle : payCardStyle,
					payCard : payCard,
					payPhoneNumber : payPhoneNumber,
					payMail : payMail,
					proxyAddress : proxyAddress,
					proxyCode : proxyCode,
				 _t:Math.random()},
	        	function(data){
		        	var result = eval('('+data+')'); 
		            if (result.code == '1') {
		            	$HXT.showInfo("提交成功!");
		            	window.location.href = "${ctx}/hProxyMessage/toPayMsg2?cId=${admin_user.companyId}&proxyMessageId="+result.items;
		             } else {
		            	 $HXT.showInfo(result.message);
		             }
	        });
		
	}
	
	function IdentityCodeValid(code) { 
        var city={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江 ",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北 ",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏 ",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外 "};
        var tip = "";
        var pass= true;
        
        if (!code || !/^[1-9][0-9]{5}(19[0-9]{2}|200[0-9]|2010)(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])[0-9]{3}[0-9xX]$/i.test(code)) {
            tip = "身份证号格式错误";
            pass = false;
        }else if(!city[code.substr(0,2)]){
            tip = "地址编码错误";
            pass = false;
        }else{
            //18位身份证需要验证最后一位校验位
            if(code.length == 18){
                code = code.split('');
                //∑(ai×Wi)(mod 11)
                //加权因子
                var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
                //校验位
                var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];
                var sum = 0;
                var ai = 0;
                var wi = 0;
                for (var i = 0; i < 17; i++)
                {
                    ai = code[i];
                    wi = factor[i];
                    sum += ai * wi;
                }
                var last = parity[sum % 11];
                if(parity[sum % 11] != code[17]){
                    tip = "校验位错误";
                    pass =false;
                }
            }
        }
        return pass;
    }
	
	function next(){
		window.location.href = "${ctx}/hProxyMessage/toPayMsg2?cId=${admin_user.companyId}&proxyMessageId=${hProxyMessage.id}";
	}
</script>
</body>
</html>
