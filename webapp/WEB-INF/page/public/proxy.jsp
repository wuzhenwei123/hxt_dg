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
    <link rel="stylesheet" type="text/css" href="${ctx}/public/css/nav.css">
    <style type="text/css">
    .cContentBox {
	    padding-left: 290px! important;
	    padding-top: 10px;
	}
	
	.codeTitle {
	    width: 85px;
	    font-size: 14px;
	    color: #000000;
	    vertical-align: middle;
	    margin-right: 50px! important;
	}
	.pBtnBox {
	    padding-top: 10px;
	    width: 300px! important;
	    margin-left: 148px;
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
        	<div class="pTitle">代扣信息</div>
            <p class="yMsg"></p>
            <c:if test="${empty hProxyMessage}">
	            	<div class="msgBox" style="background-color: #ffffff;">
			        	<p class="msOne">暂无代扣信息</p>
						<p class="msTwo"></p>
			        </div>
	            </c:if>
            <div class="cContentBox">
            	
            	<c:if test="${not empty hProxyMessage}">
            		<div class="lineP pItems">
	                	<label class="lines codeTitle">联系人姓名：${hProxyMessage.proxyName}</label>
	                </div>
            		
            		<div class="lineP pItems">
	                	<label class="lines codeTitle">联系人电话：${hProxyMessage.proxyPhone}</label>
	                </div>
	                
	                <div class="lineP pItems">
	                	<label class="lines codeTitle">联系人地址：${hProxyMessage.proxyAddress}</label>
	                </div>
	                
	                <div class="lineP pItems">
	                	<label class="lines codeTitle">联系人邮编：${hProxyMessage.proxyCode}</label>
	                </div>
	                
	                
	                <div class="lineP pItems">
	                	<label class="lines codeTitle">信息提醒日期：${hProxyMessage.remindStartDate}号~${hProxyMessage.remindEndDate}号</label>
	                </div>
	                
	                <div class="lineP pItems">
	                	<label class="lines codeTitle">状态：<c:if test="${hProxyMessage.state == 1}" >正常</c:if>
	                    	<c:if test="${hProxyMessage.state == 0}" >暂停</c:if></label>
	                </div>
	                
	                
	                <div class="lineP pItems">
	                	<label class="lines codeTitle">付款行行号：${hProxyMessage.bank_number}</label>
	                </div>
	                
	                <div class="lineP pItems">
	                	<label class="lines codeTitle">付款人账号：${hProxyMessage.payBankNumber}</label>
	                </div>
	                
	                <div class="lineP pItems">
	                	<label class="lines codeTitle">用户编号：${hProxyMessage.userNumber}</label>
	                </div>
	                
	                <div class="lineP pItems">
	                	<label class="lines codeTitle">合同编号：${hProxyMessage.contractNumber}</label>
	                </div>
	                
	                <div class="lineP pItems">
	                	<label class="lines codeTitle">合同开始日期：<fmt:formatDate value="${hProxyMessage.contractStartTime}" pattern="yyyy-MM-dd"></fmt:formatDate></label>
	                </div>
	                
	                <div class="lineP pItems">
	                	<label class="lines codeTitle">合同结束日期：<fmt:formatDate value="${hProxyMessage.contractEndTime}" pattern="yyyy-MM-dd"></fmt:formatDate></label>
	                </div>
	                
	                <div class="lineP pItems">
	                	<label class="lines codeTitle">付款人姓名：${hProxyMessage.payName}</label>
	                </div>
	                
	                <div class="lineP pItems">
	                	<label class="lines codeTitle">付款人身份证号：${hProxyMessage.payCard}</label>
	                </div>
	                
	                <div class="lineP pItems">
	                	<label class="lines codeTitle">付款人手机号：${hProxyMessage.payPhoneNumber}</label>
	                </div>
	                
	                <div class="lineP pItems">
	                	<label class="lines codeTitle">付款人邮箱：${hProxyMessage.payMail}</label>
	                </div>
	                
	                <div class="lineP pItems">
	                	<label class="lines codeTitle">附言：${hProxyMessage.info}</label>
	                </div>
	                <div class="pItems xyItems">
	                </div>
            		
            	</c:if>
            </div>
        	
        </div>
    </div>
</div>

	<%@ include file="/WEB-INF/page/public/common/footer.jsp" %>

<script src="${ctx}/public/js/jquery-1.8.2.min.js"></script>
<script src="${ctx}/public/xcConfirm/js/xcConfirm.js"></script>
<script src="${ctx}/js/area_public.js"></script>
</body>
</html>
