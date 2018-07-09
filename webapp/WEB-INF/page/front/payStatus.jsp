<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.Date"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>缴费查询</title>
</head>
<body>
<input type="hidden" id="projectPath" value="${ctx}"/>
<input type="hidden" id="lastLoginDate" value="<fmt:formatDate value="${admin_user.lastLogin}" pattern="yyyy-MM-dd "></fmt:formatDate>">
<input id="cId" type="hidden" value="${admin_user.companyId}" />
<input type="hidden" id="nowDate" value="<fmt:formatDate value="<%=new Date() %>" pattern="yyyy年MM月dd日"/>">
<%@ include file="/WEB-INF/page/public/common/top.jsp" %>
<div class="box minBox">
    <div class="pContentBox jfBox">
    	<div class="jfMsg"><i class="lines"></i>您的付款申请已提交，付款金额：${cfee/100 } 元</div>
        <p class="jfMsgRem">正在为您缴费,一般在10分钟内可将申请提交到销账机构</p>
        <div class="jfTableBox">
            <table class="jfTable" cellpadding="0" cellspacing="0" border="0" width="100%">
                <tr>
                    <td class="tit">订单编号</td>
                    <td>${cNum }</td>
                    <td class="tit">交易日期</td>
                    <td>${ctime }</td>
                </tr>
                <tr>
                    <td class="tit">单位联系人电话</td>
                    <td>${cname }</td>
                    <td class="tit">交易金额</td>
                    <td>${cfee/100 } 元</td>
                </tr>
            </table>
        </div>
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
