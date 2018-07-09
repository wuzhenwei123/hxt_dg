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
          <td align="right"><span class="baizi14">客服电话：（010）96199</span></td>
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
		width:480px;
		height:320px;
		left:50%;
		top:40%;
		margin-left:-240px;
		margin-top:-160px;
		/*border:1px solid #808080;*/
		/*padding:15px;*/
		text-align: center;
	}
	.salf{
		border-bottom:1px solid #808080;
		
		padding-bottom:10px;
		overflow:hidden;
	}
</style>
<div class="playBox">
	<img src="${ctx}/images/success.png" style="width: 200px;height:200px;"/>
	<div style="margin: 20px; font-size:24px">支付成功！</div>
	<%--<span id="totalSecond">5</span--%>
</div>

<div class="footBox_1">
<%@ include file="/WEB-INF/page/common/footerIndex.jsp" %>
</div>
</body>
</html>
<%--<script language="javascript" type="text/javascript">--%>
	<%--var second = document.getElementById('totalSecond').textContent;--%>

	<%--if (navigator.appName.indexOf("Explorer") > -1)--%>
	<%--{--%>
		<%--second = document.getElementById('totalSecond').innerText;--%>
	<%--} else--%>
	<%--{--%>
		<%--second = document.getElementById('totalSecond').textContent;--%>
	<%--}--%>

	<%--setInterval("redirect()", 1000);--%>
	<%--function redirect()--%>
	<%--{--%>
		<%--if (second < 0)--%>
		<%--{--%>
			<%--window.close();--%>
		<%--} else--%>
		<%--{--%>
			<%--if (navigator.appName.indexOf("Explorer") > -1)--%>
			<%--{--%>
				<%--document.getElementById('totalSecond').innerText = second--;--%>
			<%--} else--%>
			<%--{--%>
				<%--document.getElementById('totalSecond').textContent = second--;--%>
			<%--}--%>
		<%--}--%>
	<%--}--%>
<%--</script>--%>