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
    <title>订单详情</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/h5/style.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/h5/comm.css">
    <link href="${ctx}/css/weui.css" rel="stylesheet" type="text/css" />
    <script src="${ctx}/public/custom/public/js/jquery.1.7.2.min.js"></script>
    <script src="${ctx}/js/area_public.js"></script>
</head>
<body class="body">
	<div class="box">
    	<div class="topBox"><a class=" offen_back" href="javascript:history.go(-1);"></a>订单详情</div>
        
        <div class="yzBox regBox jyInfoBox order_box">
        	
        	<c:forEach items="${listSub}" var="subOrder" varStatus="status">
        		<div class="order_list_box">
	            	<div class="ui_cell ui_cells on">
	                    <div class="ui_center_left fl_title"><span class="">分组名称：</span></div>
	                    <div class="ui_center ui_cell_flex le">
	                        ${subOrder.sub_name}
	                    </div>
	                </div>
	                <div class="ui_cell ui_cells">
	                    <div class="ui_center_left ">交易金额：</div>
	                    <div class="ui_center ui_cell_flex">
	                       <font class="black">￥${subOrder.totalFeeStr}</font>
	                    </div>
	                </div>
	                <div class="ui_cell ui_cells">
	                    <div class="ui_center_left ">
	                        <i class="order_icon"></i>
	                    </div>
	                    <div class="ui_center ui_cell_flex le">
	                      	<div class="or_tit_box"><span class="tit">收件人姓名:</span><span>${subOrder.consignee}</span><span>${subOrder.consignee_phone}</span></div>
	                        <div class="or_tit_box on"><span class="tit">发票收件地址:</span><span>${subOrder.consignee_address}</span></div>
	                    </div>
	                </div>
	            </div>
	            <c:forEach items="${subOrder.listSubOrder}" var="subOrder1" varStatus="status">
	            	<div class="bsBox ItemLi <c:if test="${(status.index+1)%2==0}">odd</c:if>">
		                <ul class="clearAfter ItemuL">
		                    <li class="fl">客户名称：</li>
		                    <li class="fr">${subOrder1.ammeter_name}</li>        
		                </ul>
		                <ul class="clearAfter ItemuL">
		                    <li class="fl">缴费号：</li>
		                    <li class="fr">${subOrder1.electric}</li>        
		                </ul>
		                <ul class="clearAfter ItemuL">
		                    <li class="fl">交易金额：</li>
		                    <li class="fr"><font class="black">
		                    	${subOrder1.totalFeeStr}
		                    </font></li>        
		                </ul>
		                <ul class="clearAfter ItemuL">
		                    <li class="fl">欠费金额：</li>
		                    <li class="fr">${subOrder1.totalFeeStr}</li>        
		                </ul>
		                <ul class="clearAfter ItemuL">
		                    <li class="fl">支付状态</li>
		                    <li class="fr">
		                    	<c:if test="${order.pay_status=='1'}">成功</c:if>
		                    	<c:if test="${order.pay_status=='2'}">失败<font>(付款金额小于交费金额)</font></c:if>
		                    	<c:if test="${order.pay_status=='3'}">失败<font>(付款金额大于交费金额)</font></c:if>
		                    	<c:if test="${order.pay_status=='0'}">未支付</c:if>
		                    	<c:if test="${order.pay_status=='4'}">失败<c:if test="${not empty order.respMsg}">(${order.respMsg})</c:if></c:if>
		                    	<c:if test="${empty order.pay_status}">未支付</c:if>
		                    </li>        
		                </ul>
		                <ul class="clearAfter ItemuL">
		                    <li class="fl">销账状态</li>
		                    <li class="fr">
		                    	<c:if test="${subOrder1.tick_off_status==1}">成功</c:if>
					            <c:if test="${subOrder1.tick_off_status==0||empty subOrder1.tick_off_status}">未销账</c:if>
		                    </li>        
		                </ul>
		                <ul class="clearAfter ItemuL">
		                    <li class="fl">邮寄状态</li>
		                    <li class="fr"><p><c:if test="${subOrder1.isFP==1}">已邮寄</c:if><c:if test="${subOrder1.isFP!=1}">未邮寄</c:if></p><p class="on"><c:if test="${subOrder1.isFP==1}">${subOrder1.express_name}<br>快递单号：${subOrder1.express_no}</c:if></p></li>        
		                </ul>
		            </div>
	            </c:forEach>
        	</c:forEach>
        </div>
    </div>
<body>
</body>
</html>
