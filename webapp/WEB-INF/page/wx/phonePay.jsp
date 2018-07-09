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
    <title>电费详情</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/style.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/comm.css">
    <link href="${ctx}/css/weui.css" rel="stylesheet" type="text/css" />
    <script src="${ctx}/js/wx/zepto.min.js" ></script>
    <script src="${ctx}/js/area_public.js"></script>
</head>
<body class="body">
	<div class="box">
    	<div class="topBox"><a class=" offen_back" href="javascript:closeWin()"></a>电费详情</div>
    	<c:forEach items="${subList }" var="sub">
    		<c:set var="info" value="${sub.value }"></c:set>
	   		<c:set var="amList" value="${info.ammeters }"></c:set>
	   		<c:set var="baseInfo" value="${info.baseInfo }"></c:set>
	        <div class="yzBox regBox jyInfoBox">   
		   		<c:if test="${not empty baseInfo }">
		            <div class="bsBox ItemLi marginTop17">
		                <ul class="clearAfter ItemuL">
		                    <li class="fl">分组名称</li>
		                    <li class="fr">${baseInfo.name }</li>        
		                </ul>
		                <ul class="clearAfter ItemuL">
		                    <li class="fl">收件人姓名</li>
		                    <li class="fr">${baseInfo.contact_name }</li>        
		                </ul>
		                <ul class="clearAfter ItemuL">
		                    <li class="fl">收件人手机</li>
		                    <li class="fr">${baseInfo.phone }</li>        
		                </ul>
		                <ul class="clearAfter ItemuL">
		                    <li class="fl">用电地址</li>
		                    <li class="fr">${baseInfo.address }</li>        
		                </ul>
		             </div>
	             </c:if>
	             <c:if test="${not empty amList }">
		             <div class="bsBox ItemLi">
		                <div class="jyTableBox">
		                	<table cellpadding="0" cellspacing="0" border="0" width="100%">
		                    	<tr>
		                        	<th>缴费号</th>
		                            <th>客户名称</th>
		                            <th>欠费金额</th>
		                        </tr>                
		                        <c:forEach items="${amList }" var="amInfo">    
			                        <tr>
			                        	<td>${amInfo.num }</td>
			                            <td>${amInfo.name }</td>
			                            <td align="center"><font class="money">${amInfo.fee/100 }</font></td>
			                        </tr>
		                        </c:forEach>
		                    </table>
		                </div>
		            </div>
		    	</c:if>
	    	</div>
        </c:forEach>
        <div class="itemsContentBox on">
            <a href="javascript:sub();" class="btn btn_primary searBtnBox">企业网银手机支付</a>
        </div>        
        
    </div>
    <script>
    var flag = true;
    	function sub(){
    		if(flag){
    			flag = false;
	    		$HXT.wxConfirm("是否确定代付？",function(){
	    			$.post('${ctx}/hProxyMessage/proxyPay',{'proxyId':'${proxyId}','orderNum':'${orderNum}'},function(data){
	    				flag = true;			
	    			})
	    			$HXT.wxAlertCall('您已经提交代付申请,等待系统处理。',function(){
	    				closeWin();
	    			});
	    		},null)
    		}
    	}
 	
		if (typeof WeixinJSBridge == "undefined") {
			if (document.addEventListener) {
				document.addEventListener('WeixinJSBridgeReady',
						onBridgeReady, false);
			} else if (document.attachEvent) {
				document.attachEvent('WeixinJSBridgeReady',
						onBridgeReady);
				document.attachEvent('onWeixinJSBridgeReady',
						onBridgeReady);
			}
		} else {
			onBridgeReady();
		}

		function closeWin() {
			WeixinJSBridge.call('closeWindow');
		}
		function onBridgeReady() {}
	</script>
</body>
</html>
