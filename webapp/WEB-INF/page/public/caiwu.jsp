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
    <link rel="stylesheet" type="text/css" href="${ctx}/public/custom/public/css/customForm.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/public/css/style.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/public/css/nav.css">
    <style type="text/css">
    	.pItem1 {
/* 		    border: 1px solid #dddddd; */
		    height: 32px;
		    border-radius: 4px;
		    overflow: hidden;
		    vertical-align: middle;
		    width: 300px;
		    position: relative;
		}
		.cContentBox {
		    padding-left: 155px! important;
		    padding-top: 10px;
		}
		
		.codeTitle {
		    width: 85px;
		    font-size: 14px;
		    color: #000000;
		    vertical-align: middle;
		    margin-right: 90px! important;
		}
		.pBtnBox {
		    padding-top: 10px;
		    width: 300px! important;
		    margin-left: 177px;
		}
		.public_area{
			width: 96px! important;
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
        	<div class="pTitle qyInfoBox"><a class="lines" href="${ctx }/public/qyInfo">企业基本信息<span></span></a><a class="lines on" href="${ctx }/public/caiwu.html">增票基本信息<span></span></a></div>
            <p class="yMsg"></p>
            <div class="cContentBox" id="caiwu">
                <div class="lineP pItems">
                	<label class="lines codeTitle">增值税发票纳税人识别号：</label>
                    <div class="lines pItem">
                    	<input type="text" class="pInput" id="zp_code" value="${company.zp_code }">
                    </div>
                </div>
                
                <div class="lineP pItems">
                	<label class="lines codeTitle"> 地址：</label>
                    <div class="lines pItem1">
                    	<select id="m_province_code" class="public_area" gov-value="${company.zp_province_code }"></select>
						<select id="m_city_code" class="public_area" gov-value="${company.zp_city_code }"></select>
						<select id="m_area_code" class="public_area" gov-value="${company.zp_area_code }"></select>
                    </div>
                </div>
                
<!--                 <div class="lineP pItems"> -->
<!--                     <dl class="pItem1 selPIten"> -->
<!--                         <dd class="lines" style="width:100%;"> -->
<!--                             <div class='diy_select select1'> -->
<!--                             地址： -->
<!--                             	<select id="m_province_code" class="public_area" gov-value="${company.zp_province_code }"></select> -->
<!-- 								<select id="m_city_code" class="public_area" gov-value="${company.zp_city_code }"></select> -->
<!-- 								<select id="m_area_code" class="public_area" gov-value="${company.zp_area_code }"></select> -->
								
<!--                                 <input type='hidden' name='' class='diy_select_input'/> -->
<!--                                 <div class='diy_select_txt'>请选择营业地址，如北京市东城区</div> -->
<!--                                 <div class='diy_select_btn'></div> -->
<!--                                 <ul class='diy_select_list'> -->
<!--                                     <li>北京东城区</li> -->
<!--                                     <li>北京东城区</li> -->
<!--                                     <li>北京东城区</li> -->
<!--                                     <li>北京东城区</li> -->
                                  
<!--                                 </ul> -->
<!--                             </div> -->
<!--                         </dd> -->
<!--                     </dl> -->
<!--                 </div> -->
                <div class="lineP pItems">
                	<label class="lines codeTitle">增票开票信息地址：</label>
                    <div class="lines pItem">
                    	<input type="text" class="pInput" id="zp_address" value="${company.zp_address }">
                    </div>
                </div>
                
                <div class="lineP pItems">
                	<label class="lines codeTitle">增票开票信息电话区号：</label>
                    <div class="lines pItem">
                    	<input type="text" class="pInput" id="zp_area_number" value="${company.zp_area_number }" onkeyup="this.value=this.value.replace(/\D/gi,'')">
                    </div>
                </div>
                <div class="lineP pItems">
                	<label class="lines codeTitle">增票开票信息电话：</label>
                    <div class="lines pItem">
                    	<input type="text" class="pInput" id="zp_phone" value="${company.zp_phone }" onkeyup="this.value=this.value.replace(/\D/gi,'')">
                    </div>
                </div>
                <div class="lineP pItems">
                	<label class="lines codeTitle">增票开票信息开户行：</label>
                    <div class="lines pItem">
                    	<input type="text" class="pInput" id="zp_bank_code" value="${company.zp_bank_code }">
                    </div>
                </div>
                <div class="lineP pItems">
                	<label class="lines codeTitle">增票开票信息开户行账号：</label>
                    <div class="lines pItem">
                    	<input type="text" class="pInput" id="zp_bank_account" value="${company.zp_bank_account }" onkeyup="this.value=this.value.replace(/\D/gi,'')">
                    </div>
                </div>
                
                <div class="pItems xyItems">
                    <div class="pBtnBox">
                    	<a href="javascript:void(0);" onclick="update()" class="iBtn">提交</a>
                    	
                    </div>
                </div>
                
            </div>
        </div>
    </div>
</div>

	<%@ include file="/WEB-INF/page/public/common/footer.jsp" %>

<input id="mId" value="${company.id }" type="text" />

<script src="${ctx }/public/js/jquery-1.8.2.min.js"></script>
<script src="${ctx }/public/xcConfirm/js/xcConfirm.js"></script>
<script src="${ctx}/js/area_public.js"></script>
<!-- <script src="${ctx}/public/js/myPlaceholder.js"></script> -->
<script>
	$(document).ready(function(){
		new $gov("m_province_code","m_city_code","m_area_code").init();
// 		$('input').placeholder();
	});
	function getVal(id){
		return $("#"+id).val();
	}
	function update(){
		var id = getVal("mId");
		var user_id = "${admin_user_id}";
		var zp_code  = getVal("zp_code");
		var zp_province_code = getVal("m_province_code");
		var zp_city_code = getVal("m_city_code");
		var zp_area_code = getVal("m_area_code");
		
		var zp_address = getVal("zp_address");
		var zp_area_number = getVal("zp_area_number");
		var zp_phone = getVal("zp_phone");
		var zp_bank_code = getVal("zp_bank_code");
		var zp_bank_account = getVal("zp_bank_account");
		if(!zp_code){
			$HXT.showInfo('请填写增值税发票纳税人识别号');
			return;
		}
		if(!zp_province_code){
			$HXT.showInfo('请选择省份');
			return;
		}
		if(!zp_city_code){
			$HXT.showInfo('请选择城市');
			return;
		}
		if(!zp_area_code){
			$HXT.showInfo('请选择地区');
			return;
		}
		if(!zp_address){
			$HXT.showInfo('请填写增票开票信息地址');
			return;
		}
		
		var reg = /^0\d{2,3}$/;
		
		if(!zp_area_number){
			$HXT.showInfo('请填写增票开票信息电话区号');
			return;
		}else if(!reg.test(zp_area_number)){
			$HXT.showInfo('增票开票信息电话区号不正确');
			return;
		}
		
		
		if(!zp_phone){
			$HXT.showInfo('请填写增票开票信息电话');
			return;
		}else if(zp_phone.length!=11){
			$HXT.showInfo('增票开票信息电话不正确');
			return;
		}
		
		
		if(!zp_bank_code){
			$HXT.showInfo('请填写增票开票信息开户行');
			return;
		}
		
		var reg1 = /^[0-9]*$/;
		
		if(!zp_bank_account){
			$HXT.showInfo('请填写增票开票信息开户行账号');
			return;
		}else if(!reg1.test(zp_bank_account)){
			$HXT.showInfo('增票开票信息开户行账号不正确');
			return;
		}else if(zp_bank_account.length<5){
			$HXT.showInfo('增票开票信息开户行账号不正确');
			return;
		}
		var flag = true;
	    if (flag){
	    	if(id == ''){
		        $.post("<c:url value='/hCompany/addHCompany'/>",
		        	{
		        		user_id:user_id,
			    		zp_code:zp_code,
			    		zp_province_code:zp_province_code,
			    		zp_city_code:zp_city_code,
			    		zp_area_code:zp_area_code,
			    		zp_address : zp_address,
			    		zp_area_number : zp_area_number,
			    		zp_phone : zp_phone,
			    		zp_bank_code : zp_bank_code,
			    		zp_bank_account : zp_bank_account,
			    		servicerId:	'${company.servicerId}',
		        		servicerName:	'${company.servicerName}',
					 _t:Math.random()},
		        	function(data){
			        	var result = eval('('+data+')'); 
			            if (result.code == '1') {
			            	$HXT.showInfo("保存成功!");
			             } else {
			            	 $HXT.showInfo(result.message);
			             }
		        });
	    	}else{
		        $.post("<c:url value='/hCompany/updateHCompany'/>",
		        	{
			    		id : id,
			    		zp_code:zp_code,
			    		zp_province_code:zp_province_code,
			    		zp_city_code:zp_city_code,
			    		zp_area_code:zp_area_code,
			    		zp_address : zp_address,
			    		zp_area_number : zp_area_number,
			    		zp_phone : zp_phone,
			    		zp_bank_code : zp_bank_code,
			    		zp_bank_account : zp_bank_account,
			    		servicerId:	'${company.servicerId}',
		        		servicerName:	'${company.servicerName}',
					 _t:Math.random()},
		        	function(data){
			        	var result = eval('('+data+')'); 
			            if (result.code == '1') {
			            	$HXT.showInfo("更新成功!");
			             } else {
			            	 $HXT.showInfo(result.message);
			             }
		        });
	    	}
	    	
	    }
	}
</script>
</body>
</html>
