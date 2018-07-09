<%@page import="com.base.utils.FileUploadConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setAttribute("PROXY_ROLEID", FileUploadConstants.PROXY_ROLEID);
%>
<link rel="stylesheet" type="text/css" href="${ctx}/public/custom/public/css/customForm.css">
<link rel="stylesheet" type="text/css" href="${ctx}/public/xcConfirm/css/xcConfirm.css">
<link rel="stylesheet" type="text/css" href="${ctx}/public/css/style.css?Rand=Math.random()">
<link rel="stylesheet" type="text/css" href="${ctx}/css/weui.css">
<style>
	.sdfa{
		height:70px! important;
		margin-top: 10px;
	}
</style>
<div class="top">
	<div class="box lineP top_link_box">
	<c:if test="${empty admin_user }">
    	<a class="lines" href="javascript:window.location.href='${ctx}/index/first';">登录</a>
        <a class="lines" href="javascript:window.location.href='${ctx}/index/toRegister';">注册</a>
    </c:if>
    <c:if test="${not empty admin_user }">
    	<c:if test="${admin_user.roleId!=PROXY_ROLEID}">
    		<a class="lines" href="javascript:window.location.href='${ctx}/public/payFee/index';" style="cursor:pointer;color: red;">${admin_user.mobile}${admin_user.companyName}的账户</a>
    	</c:if>
    	<c:if test="${admin_user.roleId==PROXY_ROLEID}">
    		<a class="lines" href="javascript:window.location.href='${ctx}/hProxyMessage/showProxy';" style="cursor:pointer;color: red;">${admin_user.adminName}${admin_user.realName}的账户</a>
    	</c:if>
    	<a class="lines" href="javascript:window.location.href='${ctx}/manageAdminUser/cloginout';">退出</a>
    </c:if>
        <span class="lines">丨</span>
        <a class="lines" href="javascript:window.location.href='${ctx}/hHelp/toHelp';">帮助中心</a>
        <span class="lines">丨</span>
        <a class="lines" href="javascript:window.location.href='${ctx}/hAbout/toAboutUs';">关于恒信通</a>
        <span class="lines">丨</span>
        <span class="lines">客服电话：（010）96199</span>
    </div>
</div>
<div class="iMinBox">
    <div class="box logoBox">
        <a class="fl" href="${ctx}/index/first">
            <img src="${ctx}/public/images/logo.jpg" width="325px;" height="70px;" class="sdfa"/>
        </a>
        <div id="code_box" class="code_box fr">
<!--             <img src="${ctx}/public/images/geren.png"/> -->
            <img src="${ctx}/public/images/qiye.png"/>
        </div>
        <div id="code_box" class="code_box fr" style="margin-right: 139px;">
            <img src="${ctx}/public/images/geren.png"/>
<!--             <img src="${ctx}/public/images/qiye.png"/> -->
        </div>
        <span class="clear"></span>
<!--         <div id="bigCode" class="bigCode" style="background-image:url(${ctx}/public/images/code_img_b.png);" style="width: 180px;height: 180px;"></div> -->
    </div>
</div>
<div class="topNavBox">
	<div class="box lineP topNav">
    	<a class="lines <c:if test="${nav == 'index' }">on</c:if>" href="${ctx}/index/first">首页</a>
        <a class="lines <c:if test="${nav == 'chaxun' }">on</c:if>" href="${ctx}/public/payFee/toQuery">查询电费</a>
        <a class="lines <c:if test="${nav == 'news' }">on</c:if>" href="${ctx }/public/newslist?sortColumn= a.id desc ">服务要闻</a>
        <a class="lines <c:if test="${nav == 'notice' }">on</c:if>" href="${ctx }/public/noticelist?sortColumn=a.sortId desc ">网站公告</a>
        <a class="lines" href="http://www.chinaepay-usa.com" target="_blank">CHINAEPAY USA</a>
<!--         <a class="lines <c:if test="${nav == 'fuwu' }">on</c:if>" href="${ctx }/public/dot/map">服务网点</a> -->
    </div>
</div>
<input type="hidden" id="admin_company_id" value="${admin_user.companyId}">
