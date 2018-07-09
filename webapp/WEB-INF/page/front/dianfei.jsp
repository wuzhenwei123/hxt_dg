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
</head>

<body onload="getImg()">
<%@ include file="/WEB-INF/page/common/headerIndex.jsp" %>

<table width="990" border="0" align="center" cellpadding="1" cellspacing="1" class="beijs">
  <tr>
    <td width="224" bgcolor="#FFFFFF">
      <table width="212" border="0" align="right" cellpadding="0" cellspacing="0">
        <tr>
          <td width="183" height="70" class="heizi16">您上次的登录时间：<br />
          <span class="dizhi"><fmt:formatDate value="${admin_user.lastLogin}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></span></td>
        </tr>
    </table></td>
    <td rowspan="5" bgcolor="#FFFFFF"><div id="Layer1"><table width="318" border="0" align="center" cellpadding="0" cellspacing="1" class="beijs">
      <tr>
        <td height="180" valign="top" bgcolor="#FFFFFF"><table width="300" border="0" align="center" cellpadding="0" cellspacing="0" class="bk_xf">
          <tr>
            <td height="40" bgcolor="#FFFFFF">安全验证</td>
            <td width="20" bgcolor="#FFFFFF"><a href="#" class="lj_hs_24" onclick="test()">×</a></td>
          </tr>
        </table>
          <table width="240" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td height="80" align="center"><input name="textfield4" type="text" class="bk_yz" id="checkNo" placeholder="请输入验证码" value="" /></td>
              <td height="60" align="center"><img id="checkImg" src="" onclick="getImg()" width="120" height="47" /></td>
            </tr>
            <tr>
              <td height="40" colspan="2" align="center"><a href="#" onclick="toDianfei()" class="lj_bc_14">验证</a></td>
            </tr>
          </table></td>
      </tr>
    </table></div></td>
  </tr>
  <tr>
  	<%@ include file="/WEB-INF/page/common/navIndex.jsp" %>
  </tr>
  <%@ include file="/WEB-INF/page/common/navIndex1.jsp" %>
</table>
<table width="990" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="554">&nbsp;</td>
  </tr>
</table>
<%@ include file="/WEB-INF/page/common/footerIndex.jsp" %>
</body>
</html>
<script type="text/javascript" src="${ctx}/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript">

    function turnoff(obj){
        document.getElementById(obj).style.display="none";
    }
    function test()
    {
        var divV = document.getElementById("Layer1");
        if(divV.style.visibility =="hidden")
        {
            divV.style.visibility ="visible";
        }
        else
        {
            divV.style.visibility = "hidden";
        }
    }

    function getImg(){
        var url = '${ctx }/manageAdminUser/pcrimg?_t='+Math.random();
        $("#checkImg").attr("src",url);
    }
    function toDianfei(){
        var checkNo = $("#checkNo").val();
        if(typeof(checkNo) == "undefined" || checkNo==""){
            alert("请输入验证码！");
            return;
        }
        var url = '${ctx }/pay/checkVerifyImg?verify=' + checkNo;
        $.ajax({
            type: "GET",
            url: url,
            dataType: "json",
            success: function (data) {
                if (data.status == 'success') {
                    window.location = "${ctx}/index/toDianfei01";
                } else {
                    alert(data.msg);
                    getImg();
                    $("#checkNo").val("");
                }
            }
        });
    }
</script>
