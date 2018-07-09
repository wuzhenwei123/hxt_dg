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
.custom-checkbox{
	display: inline-block;
	margin-right: 5px;
}
.pay_info_bom_box span{
	    line-height: 15px! important;
}
</style>
</head>
<body>
<%@ include file="/WEB-INF/page/public/common/top.jsp" %>

<!---->
<div class="box main">
	<%@ include file="/WEB-INF/page/public/common/left.jsp" %>
    <div class="rightBox jyListBox right_pay_main">
    	<div class="commBody jySearchBox m_pay">
            
<!--             <div class="m_pay_top_items pay_in_tit"> -->
<!--                 <div class="m_pay_top_item"> -->
<!--                     <p>手机支付业务介绍</p> -->
<!--                 </div> -->
               
<!--             </div> -->
            <div class="pay_info_title_box">
<!--             	<div class="pay_in_tit pTitlePay">手机支付是什么？</div> -->
                <div class="pay_in_content_box">
                	${hPay.content }
                </div>
            </div>
        </div>
        <div class="pay_info_bom_box lineP">
        	<input type="checkbox" checked="checked" id="yidu" value="1" onclick="cc(this.value)"/>
        	<span class="lines">我已阅读并同意《手机缴费服务协议》</span>
        	<a class="lines pay_btn_info" href="${ctx}/hProxyMessage/toPayMsg1?proxyMessageId=${hProxyMessage.id}" id="kaitong">去开通</a>
        </div>
        
        
       

        
    </div>
</div>

<%@ include file="/WEB-INF/page/public/common/footer.jsp" %>


<script src="${ctx}/public/js/jquery-1.8.2.min.js"></script>
<script src="${ctx}/public/custom/public/js/custominput.js"></script>
<script type="text/javascript">
function cc(val){
	if(val==0){
		$("#yidu").val(1);
		$("#kaitong").attr("href","${ctx}/hProxyMessage/toPayMsg1");
		$("#kaitong").css("background-color","#f1511b");
	}else{
		$("#yidu").val(0);
		$("#kaitong").attr("href","javascript:void(0)");
		$("#kaitong").css("background-color","#676464");
	}
}
</script>
</body>
</html>
