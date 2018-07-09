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
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<meta content="webkit|ie-comp|ie-stand" name="renderer">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="${ctx}/css/weui.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}/public/custom/public/css/customForm.css">
<link rel="stylesheet" type="text/css" href="${ctx}/public/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/public/css/hxtPay.css">
<link rel="stylesheet" type="text/css" href="${ctx}/public/custom/public/css/customForm.css">
<link rel="stylesheet" type="text/css" href="${ctx}/public/css/nav.css">
</head>
<body>
<%@ include file="/WEB-INF/page/public/common/top.jsp" %>

<!---->
<div class="box main">
	<%@ include file="/WEB-INF/page/public/common/left.jsp" %>
    <div class="rightBox jyListBox">
    	<div class="commBody jySearchBox m_pay pay_info_box">
            
            <div class="m_pay_top_items">
                <div class="m_pay_top_item b2b">
                    <p>手机支付信息管理</p>
                </div>
            </div>
            <div class="type_box_pay two"></div>
            
            <div class="lineP pay_download_box">
                <span class="lines">请您核对协议内容确认无误后，请下载该协议并盖上公章、财务章（两章缺一不可）并点击下一步上传。</span>
                <a class="lines aDowloadBtn" href="javascript:download();">下载</a>
           </div>
           
            <div class="pay_info_title_box pay_ht_info">
            	<img alt="" src="${ctx}${outPath}?_t=${random}">
            </div>
            
            
            <div class="pay_pay_box">
            	
                <div class="lineP up_ok_box">
                	<a class="line_l pay_btn" href="${ctx}/hProxyMessage/showProxy">上一步</a>
                    <a class="line_l pay_btn" href="javascript:next()">下一步</a>
                </div>
            </div>
            
            
         
            
        </div>
       
    </div>
</div>




<%@ include file="/WEB-INF/page/public/common/footer.jsp" %>


<script src="${ctx}/public/js/jquery-1.8.2.min.js"></script>
<script src="${ctx }/public/xcConfirm/js/xcConfirm.js"></script>
<script src="${ctx}/public/custom/public/js/select.js"></script>
<script src="${ctx}/js/area_public.js"></script>
<script type="text/javascript">
function next(){
	window.location.href = "${ctx}/hProxyMessage/toPayMsg3?proxyMessageId=${hProxyMessage.id}";
}
function download(){
	window.location.href = "${ctx}/index/toDownload?link=${outPath}&name=${name}";
}
</script>
</body>
</html>
