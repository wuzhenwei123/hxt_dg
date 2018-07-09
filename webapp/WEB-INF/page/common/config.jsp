<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/tag/taglib.tld" prefix="perm"%> 


<c:set var="ctx" value="<%=request.getContextPath() %>"></c:set>
<c:set var="_title" value="恒信通-易付通-企业网上交电费-公司网上交电费" />
<c:set var="_keywords" value="恒信通,易付通,企业网上交电费,公司网上交电费" />
<c:set var="_description" value="北京恒信通电信服务有限公司，成立于2002年，是国家电网北京市电力公司指定的代收费机构，恒信通依托“易付通”公共事业缴费服务品牌，为企业、公司、物业、工厂等企事业单位提供便捷的、一站式的网上交电费服务。" />
<c:set var="_titleIndex" value="恒信通-易付通-企业网上交电费-公司网上交电费" />
<c:set var="pic" value="/pic"></c:set>
<c:set var="pic_admin" value="//qiye.chinaepay.com/admin/"></c:set>
<c:set var="pic_front" value="//qiye.chinaepay.com"></c:set>

<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
%>
