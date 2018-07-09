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
    <title>提现信息管理</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/style.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/comm.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/public/xcConfirm/css/xcConfirm.css">
    <link href="${ctx}/css/weui.css" rel="stylesheet" type="text/css" />
    <script src="${ctx}/public/custom/public/js/jquery.1.7.2.min.js"></script>
    <script src="${ctx}/js/area_public.js"></script>
</head>
<body class="body">
<div class="box">
    <div class="topBox"><a class=" offen_back" href="javascript:history.go(-1);"></a>提现信息管理</div>
    <div class="ch_ui_box marginTop16 accItems tx">
        <div class="ui_cell ui_cells">
            <div class="ui_center_left ">真实姓名</div>
            <div class="ui_center ui_cell_flex">
                <font class="accJiaojian">${adminUser1.realName}</font>
            </div>
        </div>
        <div class="ui_cell ui_cells">
            <div class="ui_center_left ">身份证号</div>
            <div class="ui_center ui_cell_flex">
                <font class="accJiaojian">${hAgentTwo.card_no}</span>
            </div>
        </div>
    </div>
    <div class="accRemBox marginTop16">
        <p>请绑定持卡人本人的银行卡，仅支持储蓄卡</p>
   </div>
        
        
        
    <div class="ch_ui_box marginTop16 accItems tx">    
         <div class="ui_cell ui_cells">
            <div class="ui_center ui_cell_flex bank">
				<div class="accNavItem on">
                    <i class="accNacIcon ic_3"></i>
                    <p class="accNavTxt tit">${hAgentTwo.bank_name}</p>
                    <p class="accNavTxt on">尾号${bank_number}</p>
                </div>
            </div>
            <div class="ui_cell_link" onclick="selBank()">请选择银行卡</div>
        </div>
        
        
   </div>
   <div class="itemsContentBox on">
<!--     <a id="OkBtnBox" class="btn btn_primary" href="javascript:void(0);">保存</a> -->
</div>
</div>

<script>
	function selBank(){
		var style = '${hAgentTwo.style}';
		if(style=="1"){//公司
			window.location.href = "${ctx}/weixin/toPostalInfoSecondPc?id=${hAgentTwo.id}";
		}else{
			window.location.href = "${ctx}/weixin/toPostalInfoSecond?id=${hAgentTwo.id}";
		}
	}
</script>
</body>
</html>
