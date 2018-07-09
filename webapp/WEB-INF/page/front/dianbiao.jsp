<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<% String url = request.getScheme()+"://"+ request.getServerName()+request.getRequestURI()+"?"+request.getQueryString(); %>
<!DOCTYPE html>
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
<script type="text/javascript"> 
/*
window.onload=function(){ 
   var linkbt=document.getElementById("linkbt"); 
   var light=document.getElementById('lightB'); 
 //  var fade=document.getElementById('fade'); 
   var closebt=document.getElementById("closebt"); 
   linkbt.onclick=function(){ 
     light.style.display='block'; 
  //   fade.style.display='block'; 
   } 
   closebt.onclick=function(){ 
     light.style.display='none'; 
  //   fade.style.display='none'; 
   } 
} */
</script>
<style>
	.ammeter_no{
		text-align:center;
		color:#666666;
	}
</style>
</head>

<body>
<%@ include file="/WEB-INF/page/common/headerIndex.jsp" %>
<input type="hidden" id="projectPath" value="${ctx}"/>
<table width="990" border="0" align="center" cellpadding="1" cellspacing="1" class="beijs">
  <tr>
    <td width="224" bgcolor="#FFFFFF">
      <table width="212" border="0" align="right" cellpadding="0" cellspacing="0">
        <tr>
          <td width="183" height="70" class="heizi16">您上次的登录时间：<br />
          <span class="dizhi"><fmt:formatDate value="${admin_user.lastLogin}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></span></td>
        </tr>
    </table></td>
    <td bgcolor="#FFFFFF"><table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="25%"><a href="javascript:void(0)" class="lj_dl_14" onclick="addNewSubCompany()"; id="linkbt">新增抄表电及发票信息</a></td>
          <td width="75%" id=""> 目前共有 <span id="ammetercount">0</span>个抄表电客户编号</td>
        </tr>
    </table></td>
  </tr>
  <tr>
  <td valign="top" bgcolor="#ffffff">
  <table width="100%" border="0" align="right" cellpadding="0" cellspacing="0">
  	<tr>
    <%@ include file="/WEB-INF/page/common/navIndex.jsp" %>
    </tr>
     <%@ include file="/WEB-INF/page/common/navIndex1.jsp" %>
  </table>
  </td>
    <td valign="top" style="overflow:hidden; position:relative;" bgcolor="#FFFFFF">
    
    <div class="listMyBox" style="overflow:auto;">
    	<div  id="listBox" style="position:relative; z-index:1;"></div>
    </div>
    <div id="loding" class="lodingIcon"><img src="${ctx}/images/loding.gif"><span>数据加载中...</span></div>
	   <div id="bgColor" class="bgColor"></div>
     <!--   <table width="96%" border="0" align="center" cellpadding="0" cellspacing="0" class="bk_xf">
       <tr>
            <td width="86%" height="70" class="heizi12"><strong>子单位名称：</strong>无　<strong>发票台头</strong>：北京乐易付通科技有限公司第一分公司　<strong>发票收件人：</strong>王小霞<br />
            <strong>手机：</strong>15011290909　<strong>收件人地址：</strong>北京市东城区夕照寺街2号电信工程局3层</td>
            <td width="14%" align="right"><a href="#" class="lj_bc_14">变更此资料</a></td>
        </tr>
        </table>
        <table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
            <td height="35" align="center" valign="bottom" class="heizi12"><strong>序号</strong></td>
            <td align="center" valign="bottom" class="heizi12"><strong>业务类型</strong></td>
            <td align="center" valign="bottom" class="heizi12"><strong>客户编号</strong></td>
            <td align="center" valign="bottom" class="heizi12"><strong>用电信息</strong></td>
            <td align="center" valign="bottom" class="heizi12"><strong>缴费状态</strong></td>
            <td width="14%" rowspan="2" align="right"><a href="javascript:void(0);" class="lj_bc_14">新增</a></td>
        </tr>
        <tr align="center">
            <td width="10%" height="30" class="heizi12">1</td>
            <td width="10%" class="heizi12">抄表电</td>
            <td width="10%" class="heizi12"><span class="hz14">
            <input name="username8" type="text" class="srk03" id="username8"  maxlength="70"  />
            </span></td>
            <td width="10%" class="heizi12">&nbsp;</td>
            <td width="10%" class="heizi12">正常</td>
        </tr>
        </table>-->
    
    </td>
  </tr>
 
</table>
<table width="990" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="554">&nbsp;</td>
  </tr>
</table>
<%@ include file="/WEB-INF/page/common/footerIndex.jsp" %>
<div id="lightB">
<table width="710" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td align="right"><a href="javascript:void(0)" class="lj_hs_24" id="closebt">×</a></td>
  </tr>
</table>
<table width="710" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td valign="top"><table width="710" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td width="126" height="40" align="right"><span class="hz14">单位名称：</span></td>
        <td width="240" id="companyName" height="40">${admin_user.companyName}</td>
      </tr>
      <tr>
        <td height="40" align="right">子单位名称：</td>
        <td height="20"><input name="sub_id" id="sub_id" type="hidden" /><input name="sub_name" type="text" class="srk01" id="sub_name"   placeholder="（没有可不填）" maxlength="70"  />        </td>
      </tr>
      <tr>
        <td height="40" align="right"><p><span class="hz01">*</span>发票台头名称：</p></td>
        <td><input name="invoice_title" type="text" class="srk01" id="invoice_title"   maxlength="70"  />        </td>
      </tr>
      <tr>
        <td height="40" align="right"><span class="hz01">*</span>发票收件人姓名：</td>
        <td><input name="consignee" type="text" class="srk02" id="consignee" maxlength="70"  /></td>
      </tr>
      <tr>
        <td height="40" align="right"><p><span class="hz01">*</span>发票收件人手机：</p></td>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="168"><input name="consignee_phone" type="text" class="srk02" id="consignee_phone"   /></td>
            <td width="157">收件人固定电话：010-</td>
            <td width="112"><span class="hz14">
              <input name="consignee_tel1" type="text" class="srk03" id="consignee_tel1" maxlength="70"  />
            </span></td>
            <td width="10" align="center">-</td>
            <td width="137"><span class="hz14">
              <input name="consignee_tel2" type="text" class="srk03" id="consignee_tel2"  maxlength="70"  />
            </span></td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td height="40" align="right"><span class="hz01">*</span>发票收件人地址：</td>
        <td><input name="consignee_address" type="text" class="srk01" id="consignee_address"  maxlength="70"  />        </td>
      </tr>
      <tr>
        <td height="40" colspan="2" align="center"><a href="#" onclick="addSubCompany('${admin_user.companyId}')" class="lj_bc_14">保 存</a></td>
        </tr>
    </table></td>
  </tr>
</table>
</div>
<div id="lightAmmeter" style="overflow: hidden;position: fixed;">
    <table width="710" border="0" cellspacing="0" cellpadding="0">
        <tbody><tr>
            <td align="right"><a href="javascript:void(0)" class="lj_hs_24" id="closebt">x</a></td>
        </tr>
        </tbody></table>
    <div style="text-align: center;font-size:22px;font-weight:bold;margin-top: 10px"> 请确认电表信息。</div>
    <table width="710" border="0" cellspacing="0" cellpadding="0" style="overflow: hidden">
        <tbody><tr>
            <td valign="top"><table width="710" border="0" align="center" cellpadding="0" cellspacing="0">
                <tbody>
                <tr>
                    <td width="50%" height="40" align="right"><span class="hz14">业务类型：</span></td>
                    <td id="ammeter_type" height="40"></td>
                </tr>
                <tr>
                    <td height="40" align="right">缴费号：</td>
                    <td id="ammeter_no"></td>
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
                    <td height="40" align="right" valign="middle"><a href="javascript:void(0);" id="guanbiA"  class="lj_bc_14">确定</a></td>
                    <td valign="middle" ><a style="margin-left: 10px" href="javascript:void(0);"  id="guanbiB" class="lj_bc_14">取消</a></td>
                </tr>
                <%--<tr>--%>
                    <%--<td height="40" colspan="2" style="text-align: center"><a href="javascript:void(0);" id="guanbiA"  class="lj_bc_14">确定</a><a href="javascript:void(0);"  id="guanbiB" class="lj_bc_14">取消</a></td>--%>
                <%--</tr>--%>
                </tbody></table></td>
        </tr>
        </tbody></table>
</div>
<input id="id" type="hidden" value="" />
<input id="sId" type="hidden" value="" />
<input id="cId" type="hidden" value="${admin_user.companyId}" />
<script src="${ctx}/js/jquery-1.8.2.min.js"></script>
<script src="${ctx}/js/front/dianbiao.js"></script>
</body>
</html>

