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
</head>
<body class="body">
	<div class="box">
    	<div class="topBox"><a class=" offen_back" href="javascript:history.go(-1);"></a>交易详情</div>
        
        <div class="yzBox regBox jyInfoBox">
            <div class="bsBox ItemLi marginTop16">
                <ul class="clearAfter ItemuL">
                    <li class="fl">客户来源</li>
                    <li class="fr">${agentName}</li>        
                </ul>
                <ul class="clearAfter ItemuL">
                    <li class="fl">联系人姓名</li>
                    <li class="fr">${company.contact_name }</li>        
                </ul>
                <ul class="clearAfter ItemuL">
                    <li class="fl">联系人手机</li>
                    <li class="fr">${company.contact_phone }</li>        
                </ul>
                <ul class="clearAfter ItemuL">
                	<li class="fl">支持人员姓名</li>
                    <li class="fr">${company.servicerName }</li>        
                </ul>
            </div>
            <c:forEach items="${subCom }" var="sub">
	            <div class="bsBox ItemLi marginTop16">
	                <ul class="clearAfter ItemuL">
	                    <li class="fl">分组名称</li>
	                    <li class="fr">${sub.sub_name }</li>        
	                </ul>
	                <ul class="clearAfter ItemuL">
	                    <li class="fl">收件人姓名</li>
	                    <li class="fr">${sub.consignee }</li>        
	                </ul>
	                <ul class="clearAfter ItemuL">
	                    <li class="fl">收件人手机</li>
	                    <li class="fr">${sub.consignee_phone }</li>        
	                </ul>
	                <ul class="clearAfter ItemuL">
	                    <li class="fl">收件人地址</li>
	                    <li class="fr">${sub.province_name }${sub.city_name }${sub.area_name }${sub.consignee_address }</li>        
	                </ul>
	                <div class="jyTableBox">
	                	<table cellpadding="0" cellspacing="0" border="0" width="100%">
	                    	<tr>
	                        	<th>缴费号</th>
	                            <th>客户名称</th>
	                            <th>交费金额</th>
<!-- 	                            <th>提成</th> -->
	                        </tr>                    
		                	<c:forEach items="${sub.subOrderList}" var="detail">
		                        <tr style="text-align: center;">
		                        	<td>${detail.electric }</td>
		                            <td>${detail.ammeter_name }</td>
		                            <td align="center"><font class="money">${detail.amount/100 }</font></td>
<!-- 		                            <td align="center"><font class="money"> -->
<!-- 			                            <c:if test="${roleType==1 }"> -->
<!-- 			                            	${detail.oneFee/100 } -->
<!-- 			                            </c:if> -->
<!-- 			                            <c:if test="${roleType==2 }"> -->
<!-- 			                            	${detail.twoFee/100 } -->
<!-- 			                            </c:if> -->
<!-- 			                            <c:if test="${roleType==3 }"> -->
<!-- 			                            	${detail.personalFee/100 } -->
<!-- 			                            </c:if> -->
<!-- 		                            </font></td> -->
		                        </tr>
	                        </c:forEach>
	                    </table>
	                </div>
	                
	            </div>
            </c:forEach>
        </div>
    </div>
<body>
</body>
</html>
