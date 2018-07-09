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
    <title>缴费结果</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/h5/style.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/h5/comm.css">
    <link href="${ctx}/css/weui.css" rel="stylesheet" type="text/css" />
    <script src="${ctx}/public/custom/public/js/jquery.1.7.2.min.js"></script>
    <script src="${ctx}/js/area_public.js"></script>
</head>
<body class="">

<div class="box">
    <div class="topBox">缴费结果</div>
   	<div class="body body_black"></div>
    <div class="jf_jg_box ok">
    	<div class="jf_jg_number">￥${fee}</div>
        <div class="jf_type_box"><i></i>支付成功</div>
    </div>
    
    <div class="ch_ui_box marginTop16 jfjg">
        <div class="ui_cell ui_cells">
            <div class="ui_center ui_cell_flex">支付状态</div>
            <div class="ui_cell_ft">已付款</div>
        </div>
        <div class="ui_cell ui_cells">
            <div class="ui_center ui_cell_flex">支付类型</div>
            <div class="ui_cell_ft">手机缴费</div>
        </div>
        <div class="ui_cell ui_cells">
            <div class="ui_center ui_cell_flex">付款时间</div>
            <div class="ui_cell_ft"><fmt:formatDate value="${time}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></div>
        </div>
        <div class="ui_cell ui_cells">
            <div class="ui_center ui_cell_flex">订单号</div>
            <div class="ui_cell_ft">${o_no}</div>
        </div>
        <div class="ui_cell ui_cells">
            <div class="ui_center ui_cell_flex">银行流水</div>
            <div class="ui_cell_ft">${query_id}</div>
        </div>
    </div>
    
    
    
    
    <div class="itemsContentBox on jfjg_btn_box">
    	<a id="OkBtnBox" class="btn btn_primary fl" href="${ctx}/H5/toIndex?openId=${admin_user.openId}">返回主页</a>
        <a id="OkBtnBox" class="btn btn_primary fr" href="javascript:showOrder('${o_no}');">查看详情</a>
    </div>
    
</div>
 

<script>
	function showOrder(o_no){
		window.location.href = "${ctx}/H5/showOrder?o_no="+o_no; 
	}
</script>
</body>
</html>
