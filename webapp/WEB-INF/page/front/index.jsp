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
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?352f3103d96cbaa54b79ee7b6ef60477";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script>
</head>

<body>
<%@ include file="/WEB-INF/page/common/headerIndex.jsp" %>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="400" align="center" bgcolor="#ffffcc">
       <iframe id="gg2" name="gg" src="${ctx}/hLbImg/getAllHLbImg" width="100%" height="400px" scrolling="no" frameborder="0" allowtransparency="true"></iframe></td>
  </tr>
</table>
<table width="990" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="254"><table width="200" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="150" align="center" bgcolor="#99cc99" class="baizi18">支持企业网上缴费</td>
      </tr>
      <tr>
        <td height="50" align="center" bgcolor="#74BA74"><p class="baizi12">21家银行的企业网银直接缴费</p></td>
      </tr>
    </table></td>
    <td align="center"><table width="200" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="150" align="center" bgcolor="#cc9900" class="baizi18"><p>电费缴费提醒</p></td>
      </tr>
      <tr>
        <td height="50" align="center" bgcolor="#A87E00"><p class="baizi12">不再担心忘记缴费</p></td>
      </tr>
    </table></td>
    <td align="center"><table width="200" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="150" align="center" bgcolor="#ff6600" class="baizi18"><p>历史账单查询</p></td>
      </tr>
      <tr>
        <td height="50" align="center" bgcolor="#C64F00"><p class="baizi12">缴费明细随时可查</p></td>
      </tr>
    </table></td>
    <td align="center"><table width="200" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="150" align="center" bgcolor="#f1511b" class="baizi18"><p>发票免费快递</p></td>
      </tr>
      <tr>
        <td height="50" align="center" bgcolor="#C2390C"><p class="baizi12">免去索取发票之苦</p></td>
      </tr>
    </table></td>
  </tr>
</table>
<%@ include file="/WEB-INF/page/common/footerIndex.jsp" %>
<script language="javascript" type="text/javascript"> 
var error = '${error}';
if(error!=""){
	if(error=="-1"){
		alert("用户名、密码、验证码不能为空!");
	}else if(error=="-2"){
		alert("验证码错误!");
	}else if(error=="-3"){
		alert("用户不存在!");
	}else if(error=="-4"){
		alert("用户名或密码错误!");
	}else if(error=="-5"){
		alert("用户异常，请联系系统管理员！");
	}else if(error=="-6"){
		alert("公司异常，请联系系统管理员！");
	}
}
</script>
</body>
</html>
