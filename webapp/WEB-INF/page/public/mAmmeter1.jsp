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
.pay_info_bom_box1 {
    height: 79px;
    /* background: url(../images/pay/p_b.png) 0 0 no-repeat; */
    text-align: right;
}

.pay_info_bom_box1 span,.pay_btn_info{
	font-size:14px;
	margin-top:18px;
	overflow:hidden;
}

.pay_info_bom_box1 span{
	margin-right:107px;
	margin-top:10px;
	line-height:41px;
}
.commBody{
	height: 500px;
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
<!--                 <div class="pay_in_content_box"> -->
<!--                 	${hPay.content } -->
<!--                 </div> -->
                
                <div class="pay_info_bom_box1 lineP">
		        	<span class="lines">您还未开通手机缴费服务</span>
		        	<a class="lines pay_btn_info" href="${ctx }/hProxyMessage/showProxy" id="kaitong">去开通</a>
		        </div>
            </div>
        </div>
        
        
       

        
    </div>
</div>

<%@ include file="/WEB-INF/page/public/common/footer.jsp" %>


<script src="${ctx}/public/js/jquery-1.8.2.min.js"></script>
<script src="${ctx}/public/custom/public/js/custominput.js"></script>
</body>
</html>
