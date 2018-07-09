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
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/js/jquery-1.8.2.min.js"></script>
<script src="${ctx}/js/front/dianfei.js"></script>
<script src="${ctx}/js/front/exclefile.js"></script>
<style>
#lightC
{ 	margin:0 auto !important; 
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
        <td width="80%" rowspan="3" class="heizi12 ">
        请您在交费前提前插上企业网银的U盾，为保证企业网银兼容性，请您使用 Internet Explorer 8.0 以上版本交费（不要使用360等浏览器）。<br/>
        截止到 <fmt:formatDate value="${admin_user.lastLogin}" pattern="yyyy年MM月dd日"></fmt:formatDate>，您登记的电表共有 <font id="ammeterSum">0</font> 个，其中 <font id="owedAmmeterSum">0</font> 个电表欠费共计：<span id="paysum" class="hz01">0.00</span> 元。
          具体的电表欠费信息如下：</td>
        <td width="20%" height="40" align="right"><a href="javascript:void(0)" onclick="showNext();" class="lj_bc_14" id="linkbt1" style="width: 100%">公司网银直接付款</a></td>
      </tr>
      <tr>
      	<td width="20%" height="40" align="right"><a href="javascript:void(0)" onclick="payInfo('${admin_user.companyId}','${admin_user.companyName}');" class="lj_bc_14" id="linkbt" style="width: 100%">转交财务人员付款</a></td>
      </tr>
      <tr>
      	<td width="20%" height="40" type="hidden" align="right"><a href="#" onclick="exportExcle()" class="lj_bc_14" style="width: 100%">导出报表</a></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <%@ include file="/WEB-INF/page/common/navIndex.jsp" %>
    <td rowspan="4" valign="top" bgcolor="#FFFFFF" style="position:relative;">
      <div style="height:300px; overflow:auto;">
        <div  id="listBox"></div>
        
        <div id="loding" class="lodingIcon" style="margin-top:-77px;"><img src="${ctx}/images/loding.gif"><span>数据加载中...</span></div>
        <div id="bgColor" class="bgColor" style="top:-121px; height:440px;"></div>
      </div>
      <div class="classPage">
        <table id="beijs" width="280" border="0" align="center" cellpadding="0" cellspacing="1" class="beijs">
        </table>
        </div>
      <%--<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0" class="bk_xf">--%>
      <%--<tr>--%>
        <%--<td width="86%" height="70" class="heizi12"><strong>子单位名称：</strong>无<strong>　发票台头：</strong>北京乐易付通科技有限公司第一分公司　<strong>发票收件人：</strong>王小霞　<strong>手机：</strong>15011290909  <br />--%>
          <%--<strong>收件人地址：</strong>北京市东城区夕照寺街2号电信工程局3层<br />--%>
          <%--共计 3 个电表客户编号，其中 2 个 欠费小计：31515.02 </td>--%>
      <%--</tr>--%>
    <%--</table>--%>
      <%--<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">--%>
        <%--<tr>--%>
          <%--<td height="35" align="center" valign="bottom" class="heizi12"><strong>序号</strong></td>--%>
          <%--<td align="center" valign="bottom" class="heizi12"><strong>业务类型</strong></td>--%>
          <%--<td align="center" valign="bottom" class="heizi12"><strong>电表客户编号</strong></td>--%>
          <%--<td align="center" valign="bottom" class="heizi12"><strong>用电客户名称</strong></td>--%>
          <%--<td align="center" valign="bottom" class="heizi12"><strong>欠费金额(元)</strong></td>--%>
          <%--<td width="80" align="center">&nbsp;</td>--%>
        <%--</tr>--%>
        <%--<tr align="center">--%>
          <%--<td height="30" class="heizi12">1</td>--%>
          <%--<td class="heizi12">抄表电</td>--%>
          <%--<td class="heizi12">345667777888</td>--%>
          <%--<td class="heizi12">北京乐易付通科有限公司有限公司...</td>--%>
          <%--<td class="heizi12">32312.01</td>--%>
          <%--<td width="80" align="center"><a href="#" class="lj_ls_14">详情</a></td>--%>
        <%--</tr>--%>
        <%--<tr align="center">--%>
          <%--<td height="30" class="heizi12">2</td>--%>
          <%--<td class="heizi12">抄表电</td>--%>
          <%--<td class="heizi12">345667777888</td>--%>
          <%--<td class="heizi12">北京乐易付通科有限公司有限公司...</td>--%>
          <%--<td class="heizi12">32312.01</td>--%>
          <%--<td align="center"><a href="#" class="lj_ls_14">详情</a></td>--%>
        <%--</tr>--%>
        <%--<tr align="center">--%>
          <%--<td height="30" class="heizi12">3</td>--%>
          <%--<td class="heizi12">抄表电</td>--%>
          <%--<td class="heizi12">345667777888</td>--%>
          <%--<td class="heizi12">北京乐易付通科有限公司有限公司...</td>--%>
          <%--<td class="heizi12">32312.01</td>--%>
          <%--<td align="center"><a href="#" class="lj_ls_14">详情</a></td>--%>
        <%--</tr>--%>
        <%--<tr align="center">--%>
          <%--<td height="30" class="heizi12">&nbsp;</td>--%>
          <%--<td class="heizi12">&nbsp;</td>--%>
          <%--<td class="heizi12">&nbsp;</td>--%>
          <%--<td class="heizi12">&nbsp;</td>--%>
          <%--<td class="heizi12">&nbsp;</td>--%>
          <%--<td align="center">&nbsp;</td>--%>
        <%--</tr>--%>
    <%--</table>--%>
      <table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td align="center" valign="bottom" class="heizi12">&nbsp;</td>
        </tr>
    </table>
     <!-- <table id="beijs" width="280" border="0" align="center" cellpadding="0" cellspacing="1" class="beijs">-->
        <%--<tr>--%>
          <%--<td align="center" bgcolor="#e7e7e7" class="heizi12">2/2</td>--%>
          <%--<td width="33" height="29" align="center" bgcolor="#FFFFFF" class="heizi12"><img src="${ctx }/images/zj01.jpg" width="42" height="28" /></td>--%>
          <%--<td width="33" height="29" align="center" bgcolor="#FFFFFF" class="heizi12"><img src="${ctx }/images/zk01.jpg" width="42" height="28" /></td>--%>
          <%--<td width="33" height="29" align="center" bgcolor="#FFFFFF" class="heizi12"><a href="#" class="lj_kuai_12">1</a></td>--%>
          <%--<td width="33" height="29" align="center" bgcolor="#FFFFFF" class="heizi12"><a href="#" class="lj_kuai_12">2</a></td>--%>
          <%--<td width="33" height="29" align="center" bgcolor="#FFFFFF" class="heizi12"><img src="${ctx }/images/yk01.jpg" width="41" height="28" /></td>--%>
          <%--<td width="33" height="29" align="center" bgcolor="#FFFFFF" class="heizi12"><img src="${ctx }/images/yj01.jpg" width="41" height="28" /></td>--%>
        <%--</tr>--%>
   <!-- </table> -->
    </td>
  </tr>
  <input type="hidden" id="page" value="1" />
  <%@ include file="/WEB-INF/page/common/navIndex1.jsp" %>
</table>
<table width="990" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="554">&nbsp;</td>
  </tr>
</table>
<%@ include file="/WEB-INF/page/common/footerIndex.jsp" %>

<div id="light" style="width: 660px !important;">
<table width="660" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td align="right"><a href="javascript:void(0)" class="lj_hs_24" onclick="closeBT()" id="closebt">×</a></td>
  </tr>
</table>
<table width="660" border="0" cellspacing="0" style="overflow: hidden" cellpadding="0">
  <tr>
    <td valign="top"><table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td width="126" height="30">请您在请款流程完成后，将下述付款链接信息复制后邮件给财务付款人员付款：</td>
        </tr>
      <tr>
        <td height="40"><p>财务部您好，截止到  <fmt:formatDate value="${admin_user.lastLogin}" pattern="yyyy年MM月dd日"></fmt:formatDate>，我单位的电费欠费共计：<span id="paysum1" class="hz01">0.00</span>  元<br />
          请登录下述网址完成付款：</p>
          <p>付款网址：<span id="checkUrl"></span><br />
          付款验证码：<span id="checkNo"></span><br />
          验证码有效期截止到：<fmt:formatDate value="${admin_user.lastLogin}" pattern="yyyy-MM-dd "></fmt:formatDate>  21:00:00</p></td>
        </tr>
      <tr>
        <td height="40" align="center"><a href="javascript:void(0)" onclick="showResult()" class="lj_dl_14">关闭</a></td>
      </tr>
    </table></td>
  </tr>
</table>
</div>

<div id="lightSuccess" style="display: none;">
<table width="560" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="150"><table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td width="126" height="40"><p class="hz01">恭喜！付款链接信息已复制成功，请将复制的信息粘贴到邮件中或以合适的方式提交给财务部付款。</p></td>
      </tr>
      <tr>
        <td height="60" align="center"><a href="javascript:void(0)" onclick="sure()" class="lj_bc_14">确定</a></td>
      </tr>
    </table></td>
  </tr>
</table>
</div>

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
        <td height="40" align="right">缴费号：</td>
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
        <td height="40" colspan="2" align="center"><a href="javascript:generatePayOrder('${admin_user.companyId}');" id="guanbi" class="lj_bc_14">下一步</a></td>
        </tr>
    </tbody></table></td>
  </tr>
</tbody></table>
</div>

<input id="cId" type="hidden" value="${admin_user.companyId}" />
</body>
</html>
<script type="text/javascript">
  window.onload=function(){
    var linkbt=document.getElementById("linkbt");
    var light=document.getElementById('light');
//   var fade=document.getElementById('fade');
    var closebt=document.getElementById("closebt");
    var closebt1=document.getElementById("closebt1");
    <%--linkbt.onclick=function(){--%>
      <%--var companyId = "${admin_user.companyId}";--%>
      <%--var companyName = "${admin_user.companyName}";--%>
      <%--var sendData = {--%>
        <%--'companyName': companyName ? companyName : '',--%>
        <%--'companyId': companyId ? companyId : '',--%>
      <%--}--%>
      <%--var url = $("#projectPath").val()+'/pay/getPayurl';--%>
      <%--$.ajax({--%>
        <%--type: "POST",--%>
        <%--url: url,--%>
        <%--data:sendData,--%>
        <%--dataType: "json",--%>
        <%--success: function (data) {--%>
          <%--var resultData = eval(data);--%>
          <%--if (resultData.status == 'success') {--%>
            <%--var datas = JSON.parse(resultData.data);--%>
            <%--$("#checkUrl").html(datas.check_url);--%>
            <%--$("#checkNo").html(datas.check_no);--%>
            <%--light.style.display='block';--%>
          <%--} else {--%>
            <%--alert(resultData.msg);--%>
          <%--}--%>
        <%--}--%>
      <%--});--%>
<%--//     fade.style.display='block';--%>
    <%--}--%>
    closebt.onclick=function(){
      light.style.display='none';
//     fade.style.display='none';
    }
    closebt1.onclick=function(){
    	lightC.style.display='none';
//     fade.style.display='none';
    }
  }

  function MM_preloadImages() { //v3.0
    var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
      var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
        if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
  }

  function showResult(){
//     $("#lightSuccess").show();
    $("#light").hide();
  }

  function sure(){
    $("#lightSuccess").hide();
    tozhifu();
  }
  
  function showNext(){
	  $("#lightC").show();
  }
  
</script>
