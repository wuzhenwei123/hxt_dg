<%@ page import="com.base.utils.RequestHandler" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<%
	String param = RequestHandler.getString(request, "param");
%>
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
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<c:url value='/boo/assets/js/lib/jquery.js'/>"></script>

</head>

<body>
<input type="hidden" id="projectPath" value="${ctx}"/>
<input type="hidden" id="param" value="<%=param%>"/>
<input type="hidden" id="page" value="1" />
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="40" bgcolor="#f1511b"><table width="990" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td align="right"><span class="baizi01"><a href="＃" class="lj_bs">帮助中心</a></span>　<span class="baizi14">|</span>　<span class="baizi14">客服电话：（010）96199</span></td>
        </tr>
      </table></td>
  </tr>
</table>
<style type="text/css">
	.footBox_1{
		position:absolute;
		width:100%;
		left:0;
		bottom:0;
	}
	p{
		margin:0;
	}
	.playBox{
		position:absolute;
		width:230px;
		height:136px;
		left:50%;
		top:50%;
		margin-left:-115px;
		margin-top:-208px;
		border:1px solid #808080;
		padding:15px;
	}
	.salf{
		border-bottom:1px solid #808080;
		
		padding-bottom:10px;
		overflow:hidden;
	}
</style>
<div class="playBox">
	<p class="salf">安全验证</p>
    <table border="0" align="center" cellpadding="0" cellspacing="0">
           <tr>
              <td height="80" align="center"><input name="textfield4" type="text" class="bk_yz" id="checkNo" placeholder="请输入验证码" value=""></td>
            </tr>
            <tr>
              <td colspan="2" align="left" class="btnBox"><a href="#" onclick="checkPayUrl()" class="lj_bc_14">验证</a></td>
            </tr>
	</table>
</div>

<div class="footBox_1">
<%@ include file="/WEB-INF/page/common/footerIndex.jsp" %>
</div>
<script src="${ctx}/js/front/payurlcheck.js"></script>
</body>
</html>
