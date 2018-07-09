<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<tr>
    <td class="navTd" valign="top" bgcolor="#FFFFFF">
    
<ul class="leftNavBox leftNav0a">
   <li>
   <!--<a class="<c:if test="${nav=='dianbiao'}">hz01</c:if>" href="${ctx}/hSubCompany/goDianBiao">账单与发票<span>>></span></a>-->
   	<a class="<c:if test="${nav=='fp'}">hz01</c:if>" href="${ctx}/hPayOrder/getHPayOrderListIndex">账单与发票<span>>></span></a>
   </li>
   <li class="un">
   <!--<a class="<c:if test="${nav=='dianbiao'}">hz01</c:if>" href="${ctx}/hSubCompany/goDianBiao">企业信息管理<span>>></span></a>-->
   <a class="<c:if test="${nav=='minfo'}">hz01</c:if>" href="${ctx}/index/toMinfo">企业信息管理<span>>></span></a>
   </li>
</ul>



    <!--
    
    <table width="212" border="0" align="right" cellpadding="0" cellspacing="0">
                <tr onclick="location.href='${ctx}/hSubCompany/goDianBiao'" style="cursor: pointer;">
          <td width="183" height="36" class="heizi16">账单与发票</td>
          <td width="29">&gt;&gt;</td>
        </tr>
    </table></td>
  </tr>
  <tr>
    <td bgcolor="#FFFFFF"><table width="212" border="0" align="right" cellpadding="0" cellspacing="0">
                <tr onclick="location.href='${ctx}/index/toMinfo'" style="cursor: pointer;">
          <td width="183" height="36" class="heizi16">企业信息管理</td>
          <td width="29">&gt;&gt;</td>
        </tr>
    </table>
   -->
   </td>
  </tr>
  <tr>
    <td class="navTd" bgcolor="#FFFFFF">
    
<ul class="leftNavBox">
   <li class="nTitle">管理联系人信息</li>
   <li><a href="${ctx}/index/toMcontact">变更联系人信息<span>>></span></a></li>
   <li class="un" ><a class="<c:if test="${nav=='mima'}">hz01</c:if>" href="${ctx}/index/toMpass">更改登录密码<span>>></span></a></li>
   
</ul>
    
  <!--  
    <table width="200" border="0" align="right" cellpadding="0" cellspacing="0">
      <tr onclick="location.href='${ctx}/index/toMcontact'" style="cursor: pointer;">
        <td width="183" height="36" class="heizi16">管理联系人信息</td>
        <td width="29">&nbsp;</td>
        </tr>
      <tr>
        <td colspan="2"><img src="${ctx }/images/xx01.png" width="212" height="1" /></td>
        </tr>
      <tr onclick="location.href='${ctx}/index/toMcontact'" style="cursor: pointer;" <c:if test="${nav=='mcontact'}">class="hz01"</c:if>>
        <td height="36">变更联系人信息</td>
        <td>&gt;&gt;</td>
        </tr>
      <tr>
        <td colspan="2"><img src="${ctx }/images/xx01.png" width="212" height="1" /></td>
        </tr>
      <tr onclick="location.href='${ctx}/index/toMpass'" style="cursor: pointer;" <c:if test="${nav=='mima'}">class="hz01"</c:if>>
        <td height="36">更改登录密码</td>
        <td>&gt;&gt;</td>
        </tr>
    </table>
    	-->
    </td>
  </tr>