<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="40" bgcolor="#f1511b">
	    <table width="990" border="0" align="center" cellpadding="0" cellspacing="0">
	      <tr>
	        <td align="right">
	        	<c:if test="${empty admin_user}">
	        		<c:if test="${empty nav}">
		        		<span class="baizi01"><a href="${ctx }/index/first" class="lj_bs">登录</a></span>　
			        	<span class="baizi14">|</span>　
	        		</c:if>
		        	<span class="baizi01"><a href="${ctx }/index/toRegister" class="lj_bs">注册</a></span>
	        	</c:if>　
	        	<c:if test="${not empty admin_user}">
	        		<span class="baizi01"><a href="${ctx }/index/toDianbiao" class="lj_bs">${admin_user.companyName}</a></span>　
	        		<span class="baizi14">|</span>　
	        		<span class="baizi01"><a href="${ctx }/manageAdminUser/cloginout" class="lj_bs">退出登录</a></span>　
	        	</c:if>
	        	<span class="baizi14">|</span>　
	        	<span class="baizi01"><a href="${ctx }/hHelp/toHelp" class="lj_bs">帮助中心</a></span>　
	        	<span class="baizi14">|</span>　<span class="baizi14">客服电话：（010）96199</span></td>
	      </tr>
	    </table>
    </td>
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
    <td width="200" align="right"><table width="100%" border="0" align="right" cellpadding="0" cellspacing="0">
      <tr>
        <td width="134" align="center"><div><a href="${ctx}/index/first" class="lj_hs">首页</a></td>
<!--           <td width="110" align="center"> -->
<!--               <ul id="nav"> -->
<!--                   <li><a>业务办理指南</a> -->
<!--                       <ul> -->
<!--                           <li><a href="#" class="lj_hs_14">原柜台交电费企业</a></li> -->
<!--                           <li><a href="#" class="lj_hs_14">物业代收电费企业</a></li> -->
<!--                           <li><a href="#" class="lj_hs_14">原电费托收企业</a></li> -->
<!--                       </ul> -->
<!--                   </li> -->
<!--               </ul> -->
<!--           </td> -->
      </tr>
    </table></td>
  </tr>
</table>