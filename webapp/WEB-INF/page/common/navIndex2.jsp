<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<tr>
    <td bgcolor="#FFFFFF"><table width="212" border="0" align="right" cellpadding="0" cellspacing="0">
                <tr <c:if test="${nav=='fp'}">class="hz01"</c:if> onclick="location.href='${ctx}/hPayOrder/getHPayOrderListIndex'" style="cursor: pointer;">
          <td width="183" height="36" style="font-size: 16px;">账单与发票</td>
          <td width="29">&gt;&gt;</td>
        </tr>
    </table></td>
  </tr>
  <tr>
    <td height="1" bgcolor="#999999"></td>
  </tr>
  <tr>
    <td bgcolor="#FFFFFF" <c:if test="${nav=='minfo'}">class="hz01"</c:if>><table width="212" border="0" align="right" cellpadding="0" cellspacing="0">
                <tr onclick="location.href='${ctx}/index/toMinfo'" style="cursor: pointer;">
          <td width="183" height="36" class="heizi16">企业信息管理</td>
          <td width="29">&gt;&gt;</td>
        </tr>
    </table></td>
  </tr>
  <tr>
    <td height="1" bgcolor="#999999"></td>
  </tr>
  <tr>
    <td bgcolor="#FFFFFF"><table width="200" border="0" align="right" cellpadding="0" cellspacing="0">
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
    </table></td>
  </tr>