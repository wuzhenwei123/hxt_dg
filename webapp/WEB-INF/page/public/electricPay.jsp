<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.Date"%>
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
	<link rel="stylesheet" type="text/css" href="${ctx}/public/css/nav.css">
<style type="text/css">
xcConfirm .popBox .ttBox .tt {
    font-size: 16px;
    display: block;
    float: left;
    position: relative;
    margin-left: -13px!important;
}
</style>
</head>
<body>
<input type="hidden" id="projectPath" value="${ctx}"/>
<input type="hidden" id="projectPath_admin" value="${pic_admin}"/>
<input type="hidden" id="lastLoginDate" value="<fmt:formatDate value="${admin_user.lastLogin}" pattern="yyyy-MM-dd "></fmt:formatDate>">
<input id="cId" type="hidden" value="${cId}" />
<input type="hidden" id="nowDate" value="<fmt:formatDate value="<%=new Date() %>" pattern="yyyy年MM月dd日"/>">
<%@ include file="/WEB-INF/page/public/common/top.jsp" %>
<!---->
<div class="box main main_box">
</div>
<div id="loadingToast" class="weui_loading_toast">
    <div class="weui_mask_transparent"></div>
    <div class="weui_toast">
        <div class="weui_loading">
            <div class="weui_loading_leaf weui_loading_leaf_0"></div>
            <div class="weui_loading_leaf weui_loading_leaf_1"></div>
            <div class="weui_loading_leaf weui_loading_leaf_2"></div>
            <div class="weui_loading_leaf weui_loading_leaf_3"></div>
            <div class="weui_loading_leaf weui_loading_leaf_4"></div>
            <div class="weui_loading_leaf weui_loading_leaf_5"></div>
            <div class="weui_loading_leaf weui_loading_leaf_6"></div>
            <div class="weui_loading_leaf weui_loading_leaf_7"></div>
            <div class="weui_loading_leaf weui_loading_leaf_8"></div>
            <div class="weui_loading_leaf weui_loading_leaf_9"></div>
            <div class="weui_loading_leaf weui_loading_leaf_10"></div>
            <div class="weui_loading_leaf weui_loading_leaf_11"></div>
        </div>
        <p class="weui_toast_content">数据加载中</p>
    </div>
</div>
<%@ include file="/WEB-INF/page/public/common/footer.jsp" %>
<script src="${ctx}/public/js/jquery-1.8.2.min.js"></script>
<script src="${ctx}/public/xcConfirm/js/xcConfirm.js"></script>
<script src="${ctx}/public/js/public/electricPay.js"></script>
<script src="${ctx}/js/area_public.js"></script>
<script src="${ctx}/public/custom/public/js/custominput.js"></script>
</body>
</html>
