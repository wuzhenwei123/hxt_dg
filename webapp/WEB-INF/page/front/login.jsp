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
<script type="text/javascript" src="<c:url value='/boo/assets/js/lib/jquery.js'/>"></script>

</head>

<body>
<%@ include file="/WEB-INF/page/common/headerIndex.jsp" %>
<table width="100%" border="0" align="center" cellpadding="1" cellspacing="1">
  <tr></tr>
  <tr>
    <td valign="top" bgcolor="#FFFFCB"><table width="990" border="0" align="center" cellpadding="1" cellspacing="1">
      <tr></tr>
      <tr>
        <td height="400" style="background-image:url(${ctx }/images/dl.jpg)" bgcolor="#ffffcb">
        <form id="form1" name="form1" method="post" action="${ctx}/index/pagelogin">
        <table width="281" style="margin-left:500px" border="0" cellpadding="0" cellspacing="0" class="bk01">
        <tr>
        	
            <td height="44"><table width="240" border="0" align="center" cellpadding="0" cellspacing="0" style="margin:auto;">
                <tr>
                    <td height="44" align="left" class="hz01">注册企业版用户</td>
                </tr>
                <tr>
                    <td><img src="${ctx }/images/xx.png" width="240" height="1" /></td>
                </tr>
                <tr>
                    <td height="60"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="bk02">
                        <tr>
                            <td width="40" height="40"><img src="${ctx }/images/u65.png" width="40" height="40" /></td>
                            <td>
                                <input name="adminName" type="text" class="wbk" id="textfield4" placeholder="登录账号" />
                            </td>
                        </tr>
                    </table></td>
                </tr>
                <tr>
                    <td height="60">
	                    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="bk02">
	                        <tr>
	                            <td width="40" height="40"><img src="${ctx }/images/u68.png" width="40" height="40" /></td>
	                            <td>
	                                <input name="passwd" type="password" class="wbk" id="textfield5" placeholder="登录密码" />
	                            </td>
	                        </tr>
	                    </table>
                    </td>
                </tr>
                <tr>
                    <td align="right"><a href="#" class="lj_hs_14">忘记登录密码？</a></td>
                </tr>
                <tr>
                    <td>
	                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	                        <tr>
	                            <td height="60"><input name="verify" type="text" class="bk_yz" id="textfield6" placeholder="验证码" /></td>
	                            <td><img src="${ctx }/manageAdminUser/pcrimg" width="120" height="47" id="verfyImg"/></td>
	                        </tr>
	                    </table>
                    </td>
                </tr>
                <tr>
                    <td><a href="javascript:login()" target="_parent" class="lj_dl_18">登录企业版</a></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                </tr>
            </table>
            </td>
            
        </tr>
    </table>
    </form>
    </td>
      </tr>
    </table></td>
  </tr>
</table>
<%@ include file="/WEB-INF/page/common/footerIndex.jsp" %>
<script type="text/javascript">
	function login(){
		var username = $("#textfield4").val();
		var password = $("#textfield5").val();
		var verify = $("#textfield6").val();
		if($.trim(username)==""){
			alert("请输入用户名");
		}else if($.trim(password)==""){
			alert("请输入用密码");
		}else if($.trim(verify)==""){
			alert("请输入用验证码");
		}else{
			$("#form1").submit();
		}
		
	}
	function getImg(){
		var url = '${ctx }/manageAdminUser/pcrimg?_t='+Math.random();
		$("#verfyImg").attr("src",url);
	}
</script>
</body>
</html>
