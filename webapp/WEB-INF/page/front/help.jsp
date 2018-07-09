<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${_titleIndex}</title>
<meta name="keywords" content="恒信通,易付通,企业网上交电费,公司网上交电费" />
<meta name="description" content="北京恒信通电信服务有限公司，成立于2002年，是国家电网北京市电力公司指定的代收费机构，恒信通依托“易付通”公共事业缴费服务品牌，为企业、公司、物业、工厂等企事业单位提供便捷的、一站式的网上交电费服务。" />
<meta content="textml;charset=utf-8" http-equiv="content-type">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta content="webkit|ie-comp|ie-stand" name="renderer">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${ctx}/css/news.css" rel="stylesheet" type="text/css" />
<style type="text/css">
    .gs01 {
	    color: #186C5C;
	    font-size: 19px;
	    font-weight: bold;
	}
</style>
</head>

<body>
<%@ include file="/WEB-INF/page/common/headerIndex.jsp" %>
<table width="990" border="0" align="center" cellpadding="0" cellspacing="0" class="bk_xf">
  <tr>
    <td width="554">&nbsp;</td>
  </tr>
</table>

<table width="900" border="0" align="center" cellpadding="1" cellspacing="1">
  <tr>
    <td width="224" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td height="50" align="center" bgcolor="#FFFFFF" class="bt01">帮助中心</td>
  </tr>
  <tr>
    <td bgcolor="#FFFFFF" class="nw">
    ${hhelp.content}
    </td></tr></table><table width="990" border="0" align="center" cellpadding="1" cellspacing="1"><tr>
  </tr>
</table>
<table width="990" border="0" align="center" cellpadding="1" cellspacing="1">
  <tr> </tr>
</table>
<table width="990" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="554">&nbsp;</td>
  </tr>
</table>
<%@ include file="/WEB-INF/page/common/footerIndex.jsp" %>
</body>
</html>
