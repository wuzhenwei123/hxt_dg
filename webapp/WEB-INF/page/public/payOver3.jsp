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
.up_ht_txt_box {
    overflow: hidden;
    padding-top: 100px! important;
}
.pay_img_box {
    height: 250px! important;
}
.up_ht_btn {
    width: 100px! important;
}
</style>
</head>
<body>
<%@ include file="/WEB-INF/page/public/common/top.jsp" %>

<!---->
<div class="box main">
	<%@ include file="/WEB-INF/page/public/common/left.jsp" %>
    <div class="rightBox jyListBox">
    	<div class="commBody jySearchBox m_pay">
            
            <div class="m_pay_top_items">
                <div class="m_pay_top_item b2b">
                    <p>手机缴费信息管理</p>
                </div>
            </div>
            <div class="type_box_pay type_box_pay_ht three"></div>
            <c:if test="${not empty hProxyMessage&&hProxyMessage.checkState==5}">
            	<div class="lineP pay_download_box on">
	                <span class="lines">您终止手机缴费服务的申请未通过，具体原因为：</span>
	            </div>
            </c:if>
            <div class="pay_pay_box">
            	<div class="pay_img_box on">
                    <div class="up_ht_txt_box">
                    	<c:if test="${not empty hProxyMessage&&hProxyMessage.checkState==3}">
                    		<p class="on">提交审核中~~审核在三个工作日内完成，审核通过后将会以短信的形式发送到您登记的服务负责人手机号上。</p>
                    	</c:if>
                    	<c:if test="${not empty hProxyMessage&&hProxyMessage.checkState==5}">
                    		<p class="on">${hProxyMessage.msg}</p>
                    	</c:if>
                    	<c:if test="${not empty hProxyMessage&&hProxyMessage.checkState==4}">
                    		<p class="on">您提交的中止手机缴费服务的申请已通过审核，感谢您的使用。</p>
                    	</c:if>
                    </div>
                </div>
                
                <div class="lineP up_btn_box on">
                	<c:if test="${not empty hProxyMessage&&hProxyMessage.checkState==4}">
                		<a class=" line_l up_ht_btn" href="javascript:update1()">重新开通手机缴费业务</a>
                	</c:if>
                	<c:if test="${not empty hProxyMessage&&hProxyMessage.checkState==5}">
                		<a class=" line_l up_ht_btn" href="javascript:update()">继续申请终止</a>
                	</c:if>
                </div>
             	
            </div>
            
        </div>  
    </div>
</div>




<%@ include file="/WEB-INF/page/public/common/footer.jsp" %>


<script src="${ctx}/public/js/jquery-1.8.2.min.js"></script>
<script src="${ctx}/public/custom/public/js/custominput.js"></script>
<script type="text/javascript">
function update(){
	window.location.href = "${ctx}/hProxyMessage/toPayMsgSuccess?proxyMessageId=${hProxyMessage.id}";
}
function update1(){
	window.location.href = "${ctx}/hProxyMessage/toWhatPay?proxyMessageId=${hProxyMessage.id}";
}
</script>
</body>
</html>
