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
    <title>个人代理变更银行卡</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/customForm.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/style.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/comm.css">
    <link href="${ctx}/css/weui.css" rel="stylesheet" type="text/css" />
    <script src="${ctx}/public/custom/public/js/jquery.1.7.2.min.js"></script>
</head>
<body class="body">
<div class="box">
    <div class="topBox"><a class=" offen_back" href="#"></a>个人代理变更银行卡</div>
    
    
    <div class="ch_ui_box marginTop16 accItems tx">
        <div class="ui_cell ui_cells">
            <div class="ui_center_left ">开户银行</div>
            <div class="ui_center ui_cell_flex">
                <input id="card_1" class="offen_text offen_ui_text" type="text" value=""  placeholder="请选择银行" />
            </div>
        </div>
        <div class="ui_cell ui_cells">
            <div class="ui_center_left ">开户行行号</div>
            <div class="ui_center ui_cell_flex">
                <input id="card_1" class="offen_text offen_ui_text" type="text" value="" placeholder="请填写开户行" />
            </div>
        </div>
         <div class="ui_cell ui_cells">
            <div class="ui_center_left ">开户行支行</div>
            <div class="ui_center ui_cell_flex">
                <input id="card_1" class="offen_text offen_ui_text" type="text" value="" placeholder="请填写支行" />
            </div>
        </div>
        <div class="ui_cell ui_cells">
            <div class="ui_center_left ">银行卡号</div>
            <div class="ui_center ui_cell_flex">
                <input id="card_1" class="offen_text offen_ui_text" type="text" value="" placeholder="请选择银行卡号" />
            </div>
        </div>
        
   </div>
   <div class="itemsContentBox on">
    <a id="OkBtnBox" class="btn btn_primary" href="javascript:void(0);">保存</a>
</div>
</div>
 

<script>
	$(function(){
		
		$("#OkBtnBox").click(function(){
			
			var title = '';
			var html = '<div class="bgTitle">确定变更该用户的支持人员吗？</div>';
			var option = {
						title: title,
						btn: parseInt("0011",2),
						isOkBtn : true,
						onOk: function(){
// 							console.log("确认啦");
						},
						class : ['bgPeo']
					}
					
					window.wxc.xcConfirm(html, "custom", option);
					
					if(option.isOkBtn){
						$('.'+option.class[0] + " .popBox .cancel").remove();
					}
			
		});
		
		
	})
</script>
</body>
</html>
