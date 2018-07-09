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
    <title>我的账户</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/style.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/comm.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/public/xcConfirm/css/xcConfirm.css">
    <link href="${ctx}/css/weui.css" rel="stylesheet" type="text/css" />
    <script src="${ctx}/js/wx/zepto.min.js" ></script>
    <script src="${ctx}/js/area_public.js"></script>
    <style type="text/css">
    	.offen_back1 {
		    color: #ffffff;
		    border-style: solid;
		    content: " ";
		    height: 13px;
		    right: 20px;
		    position: absolute;
		}
    </style>
</head>
<body class="body">
<div class="box">
    <div class="topBox"><a class=" offen_back" href="javascript:history.go(-1);"></a>我的账户<a class="offen_back1" href="${ctx }/weixin/toAccountDetail">账户明细</a></div>
    
    <div class="bsBox marginTop16">
    	<div class="accTopBox">
            <div class="accTitleBox">账户余额</div>
            <div class="accMoneyNum">${totalFee }元</div>
        </div>
        <div class="accNavItems">
        	<div class="fl accNavItem">
            	<i class="accNacIcon ic_1"></i>
                <p class="accNavMoney"><fmt:formatNumber value="${arrival}" pattern="0.00"></fmt:formatNumber>元</p>
                <p class="accNavTxt">已到账金额</p>
            </div>
            <div class="fl accNavItem un">
            	<i class="accNacIcon ic_2"></i>
            	<p class="accNavMoney"><fmt:formatNumber value="${applyFee}" pattern="0.00"></fmt:formatNumber>元</p>
                <p class="accNavTxt">审核中金额</p>
            </div>
            <span class="clear"></span>
        </div>
        <div class="accNavItem on" style="display:none!important">
            <i class="accNacIcon ic_3"></i>
            <p class="accNavMoney">2000.00元</p>
            <p class="accNavTxt">审核中金额</p>
        </div>
    </div>
	    <div class="ch_ui_box marginTop16 accItems">
	        <div class="ui_cell ui_cells">
	            <div class="ui_center_left ">提现金额：</div>
	            <div class="ui_center ui_cell_flex">
<!-- 	                <font class="accJiaojian">大于等于100元</font> -->
<!-- 	                <input id="applyFee" type="text"/> -->
	                <input id="applyFee" placeholder="大于等于100元" class="offen_text offen_ui_text" type="text"/>
	            </div>
	        </div>
	        
	        <c:if test="${admin_user.roleId==twoRoleId}">
<!-- 	        	<div class="ui_cell ui_cells"> -->
<!-- 		            <div class="ui_center_left ">代交税款：</div> -->
<!-- 		            <div class="ui_center ui_cell_flex"> -->
<!-- 		                <span class="money" id="taxFeeSpan">00.00元</span> -->
<!-- 		            </div> -->
<!-- 		        </div> -->
<!-- 		         <div class="ui_cell ui_cells"> -->
<!-- 		            <div class="ui_center_left ">到账金额：</div> -->
<!-- 		            <div class="ui_center ui_cell_flex"> -->
<!-- 		                <span class="money" id="arrivalFeeSpan">00.00元</span> -->
<!-- 		            </div> -->
<!-- 		        </div> -->
	        </c:if>
	   </div>
   <div class="accRemBox marginTop16">
	        <p>每月1-5号可以申请提现，每月只允许一笔提现</p>
	        <p>提现经审核后月底前会打到您的银行卡里</p>
	        <p>根据国家相关法规，打款金额是扣除税费后金额</p>
   </div>
	   <div class="itemsContentBox on">
	       <a id="OkBtnBox" class="btn btn_primary" href="javascript:apply();">提现</a>
	   </div>
    
    
   
</div>
<script src="${ctx }/js/wx/jquery.1.7.2.min.js"></script>
<script src="${ctx }/public/xcConfirm/js/xcConfirm.js"></script>
<script>
// 	$(function(){
		
// 		$("#applyFee").change(function(){
// 			var fee = $(this).val();
			
// 		});
// 	})
	
	function loadData(fee){
		var taxRate = '${taxRate}';
		var roleId = '${admin_user.roleId}';
		var twoRoleId = '${twoRoleId}';
		if(roleId==twoRoleId){
			if(!isNaN(fee)){
				var taxFee = (eval(fee)*eval(taxRate)).toFixed(2); 
// 				$("#taxFee").val(taxFee);
// 				$("#taxFeeSpan").html(taxFee);
// 				$("#arrivalFee").val((eval(fee)).toFixed(2));
				$("#arrivalFeeSpan").html((eval(fee)).toFixed(2));
			}else{
// 				$("#taxFee").val("0.00");
// 				$("#taxFeeSpan").html("0.00");
// 				$("#arrivalFee").val("0.00");
				$("#arrivalFeeSpan").html("0.00");
			}
		}else{
// 			$("#taxFee").val("0.00");
// 			$("#taxFeeSpan").html("0.00");
// 			$("#arrivalFee").val("0.00");
			$("#arrivalFeeSpan").html("0.00");
		}
	}
	var flag = true;
	function apply(){
		var url = '${ctx}/weixin/wxApply';
		var applyFee = $("#applyFee").val();
		var totalFee = '${totalFee}';
		var reg = /^\d+(?:.\d{1,2})?$/;
		if(!reg.test(applyFee)){
			$HXT.wxAlert("您输入的提现金额有误，请重新输入！");
			return false;
		}else if(parseFloat(applyFee)<100){
			$HXT.wxAlert("提现金额最小100元，请重新输入！");
			return false;
		}else if(parseFloat(applyFee)>parseFloat(totalFee)){
			$HXT.wxAlert("提现金额不能大于账户余额，请您核对。");
			return false;
		}else{
			flag = true;
		}
		if(flag){
			flag = false;
			$.get(url,{totalFee:$("#applyFee").val(),_r:Math.random()},function(data){
	            if (data.status == 'success') {
	            	flag = true;
	       			var title = '';
	       			var html = '<div class="bgTitle">您的提现金额正在审核中，提现经审核后月底前会打到您的银行卡里，请注意查收！</div>';
	       			var option = {
	       						title: title,
	       						btn: parseInt("0011",2),
	       						isOkBtn : true,
	       						onOk: function(){
	       							location.reload();
	       						},
	       						class : ['bgPeo','acc']
	       					}
	       			window.wxc.xcConfirm(html, "custom", option);
	       			if(option.isOkBtn){
	       				$('.'+option.class[0] + " .popBox .cancel").remove();
	       			}
	            }else{
	            	flag = true;
	            	$HXT.wxAlert(JSON.parse(data).msg);
	            	location.reload();
	            }
			});
		}
	}
</script>

</body>
</html>
