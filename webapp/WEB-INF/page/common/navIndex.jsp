<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<td class="navTd" valign="top" bgcolor="#FFFFFF">
  <!-- <table width="200" border="0" align="right" cellpadding="0" cellspacing="0">
     <tr>
       <td width="183" height="36" class="heizi16">电费缴费111</td>
       <td width="29">&nbsp;</td>
     </tr>
     <tr>
       <td colspan="2" height="1"><img src="${ctx}/images/xx01.png" width="212" height="1" /></td>
       </tr>
     <tr <c:if test="${nav=='dianbiao'}">class="hz01"</c:if> onclick="location.href='${ctx}/index/toDianbiao'" style="cursor: pointer;">
       <td height="36">管理电表和发票信息11 </td>
       <td>&gt;&gt;</td>
     </tr>
     <tr>
       <td colspan="2" height="1" style="padding:0"><img src="${ctx}/images/xx01.png" width="212" height="1" /></td>
       </tr>
     <tr <c:if test="${nav=='dianfei'}">class="hz01"</c:if> onclick="location.href='${ctx}/index/toDianfei'" style="cursor: pointer;">
       <td height="36">电费查询与缴费11</td>
       <td>&gt;&gt;</td>
     </tr>
   </table>-->
   
<ul class="leftNavBox">
   <li class="nTitle">电费缴费</li>
   <li><a class="<c:if test="${nav=='dianbiao'}">hz01</c:if>" href="${ctx}/index/toDianbiao">管理电表和发票信息<span>>></span></a></li>
   <li class="un" ><a class="<c:if test="${nav=='dianfei'}">hz01</c:if>" href="${ctx}/index/toDianfei">电费查询与缴费<span>>></span></a></li>
</ul>
   
   
  </td>