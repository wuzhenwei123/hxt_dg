<%@ page import="com.base.utils.RequestHandler" %>
<%@ page import="com.base.utils.Base64" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<%String param = RequestHandler.getString(request, "param");
  param = Base64.getFromBase64(param);
  Gson gson = new Gson();
  Map paramMap = gson.fromJson(param,Map.class);
  String cId= paramMap.get("c_id").toString();
  String companyName= paramMap.get("companyName").toString();

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
<input id="cId" type="hidden" value="<%=cId%>" />
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
<table width="990" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="554" height="94"><table width="332" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="144" rowspan="2"><img src="${ctx}/images/logo01.jpg" width="140" height="71" /></td>
          <td width="184" height="40" class="gs01">专业电费服务</td>
        </tr>
<!--         <tr> -->
<!--           <td valign="top" class="gs02">指定代收费服务机构</td> -->
<!--         </tr> -->
    </table></td>
  </tr>
</table>
<table width="990" border="0" align="center" cellpadding="1" cellspacing="1" class="beijs">
  <tr>
    <td height="100" bgcolor="#FFFFFF"><table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="86%" rowspan="2" class="heizi12"><%=companyName%>：<br />
          截止到 <fmt:formatDate value="${admin_user.lastLogin}" pattern="yyyy年MM月dd日"></fmt:formatDate>，您登记的电表共有 <font id="ammeterSum">0</font> 个，其中 <font id="owedAmmeterSum">0</font> 个电表欠费共计：<span id="paysum" class="hz01">0.00</span> 元。<br />
          <span class="hz01">请您在完成制单后，及时提示复核人员登录网银复核订单，超过当日再复核订单可能造成交费失败!</span>。<br />
          具体的电表欠费信息如下：
            </td>
          <td height="40" align="right"><a href="#" onclick="showNext()" class="lj_bc_14" style="width: 100px;">立即付款缴费</a></td>
        </tr>
        <tr>
          <td width="14%" height="40" type="hidden" align="right"><a href="#" onclick="exportExcle()" class="lj_bc_14">导出报表</a></td>
        </tr>
      </table></td>
  </tr>
  <tr>
    <td valign="top" bgcolor="#FFFFFF" style="position:relative;">
        <div style="height:500px; overflow:auto;">
        <div  id="listBox"></div>
        <div class="classPage">
        <table id="beijs" width="280" border="0" align="center" cellpadding="0" cellspacing="1" class="beijs">
        </table>
        </div>
        <div id="loding" class="lodingIcon"><img src="${ctx}/images/loding.gif"><span>数据加载中...</span></div>
    <div id="bgColor" class="bgColor"></div>
        </div>
    </td>
  </tr>
</table>
<table width="990" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="554">&nbsp;</td>
  </tr>
</table>
<div id="lightB">
<table width="710" border="0" cellspacing="0" cellpadding="0">
  <tbody><tr>
    <td align="right"><a href="javascript:void(0)" class="lj_hs_24" id="closebt">×</a></td>
  </tr>
</tbody></table>
<table width="710" border="0" cellspacing="0" cellpadding="0">
  <tbody><tr>
    <td valign="top"><table width="710" border="0" align="center" cellpadding="0" cellspacing="0">
      <tbody><tr>
        <td width="126" height="40" align="right"><span class="hz14">业务类型：</span></td>
        <td width="240" id="ammeter_type" height="40"></td>
      </tr>
      <tr>
        <td height="40" align="right">客户编号：</td>
        <td height="20" id="ammeter_no"></td>
      </tr>
      <tr>
        <td height="40" align="right">用电客户名称：</td>
        <td id="ammeter_name"></td>
      </tr>
      <tr>
        <td height="40" align="right">总欠费金额：</td>
        <td id="totalfee"></td>
      </tr>
      <tr>
        <td height="40" align="right">电表欠费金额：</td>
        <td id="accountFee"></td>
      </tr>
      <tr>
        <td height="40" align="right">滞纳金：</td>
        <td id="lateFee"></td>
      </tr>
      <tr>
        <td height="40" colspan="2" align="center"><a href="javascript:void(0);" id="guanbi" class="lj_bc_14">关闭</a></td>
        </tr>
    </tbody></table></td>
  </tr>
</tbody></table>
</div>

<div id="lightC" >
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tbody><tr>
    <td align="right"><a href="javascript:void(0)" class="lj_hs_24" id="closebt1">×</a></td>
  </tr>
</tbody></table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tbody><tr>
    <td valign="top"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tbody>
      <tr>
        <td height="40" align="right">注意：请您在完成制单后，及时提示复核人员登录网银复核订单，超过当日再复核订单可能造成交费失败！</td>
      </tr>
      <tr>
        <td height="40" colspan="2" align="center"><a href="javascript:generatePayOrder();" id="guanbi" class="lj_bc_14">下一步</a></td>
        </tr>
    </tbody></table></td>
  </tr>
</tbody></table>
</div>
<%@ include file="/WEB-INF/page/common/footerIndex.jsp" %>
<script src="${ctx}/js/front/zhifu.js"></script>
<script type="text/javascript">
function showNext(){
	  $("#lightC").show();
}
</script>
</body>
</html>
