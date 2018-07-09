<%@page import="com.base.utils.FileUploadConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setAttribute("PROXY_ROLEID", FileUploadConstants.PROXY_ROLEID);
%>
<div class="leftBox">
   	<div class="leftTopBox">
       	<div class="leftTopTime" style="padding-top: 5px;">
       		<div style="border-bottom:1px solid #e4e4e4;padding-left: 40px;">
       			<p>您上次的登陆时间：</p>
       			<p ><fmt:formatDate value="${admin_user.lastLogin}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></p>
       		</div>
       		<div style="padding-left: 40px;">
				<p>您的网银指导人员：
					<c:if test="${fn:length(admin_user.servicerName)>3}">
						${fn:substring(admin_user.servicerName, 0, 3)}
					</c:if>
					<c:if test="${fn:length(admin_user.servicerName)<=3}">
						${admin_user.servicerName}
					</c:if>
				</p>
				<p>手机：${admin_user.servicerPhone }</p>
				<p>服务热线：010-96199</p>
       		</div>
       </div>
       </div>
       
       <ul class="leftNavBox">
       		<c:if test="${admin_user.roleId!=PROXY_ROLEID}">
			<li>
				<a class="nav firstNav <c:if test="${left_nav == 'pcwgl'||left_nav == 'cPeople'||left_nav == 'upPwd'||left_nav == 'payFee'}">on</c:if>" href="javascript:void(0);">企业网银缴费管理</a>
			    <ul class="leftNavs" <c:if test="${left_nav != 'pcwgl'&&left_nav != 'cPeople'&&left_nav != 'upPwd'&&left_nav != 'payFee'}">style="display:none;"</c:if>>
		           <li class="nav"><a <c:if test="${left_nav == 'payFee' }">class="on"</c:if> href="${ctx }/public/payFee/index"><i class="leftNavIcon LN1"></i>电费查询与交费</a></li>
		           <li class="nav"><a <c:if test="${left_nav == 'pcwgl' }">class="on"</c:if> href="${ctx }/public/pcwgl"><i class="leftNavIcon LN2"></i>财务复核人员管理</a></li>
		           <li class="nav"><a <c:if test="${left_nav == 'cPeople' }">class="on"</c:if> href="${ctx }/public/cPeople"><i class="leftNavIcon LN5"></i>联系人信息管理</a></li>
		           <li class="nav"><a <c:if test="${left_nav == 'upPwd' }">class="on"</c:if> href="${ctx }/public/upPwd"><i class="leftNavIcon LN6"></i>更改登录密码</a></li>
		       </ul>
			       
			</li>
			   
<!-- 			<li> -->
<!-- 				<a class="nav firstNav <c:if test="${left_nav == 'proxy'||left_nav == 'withhold'}">on</c:if>" href="javascript:void(0);">手机缴费管理</a> -->
<!-- 			    <ul class="leftNavs" <c:if test="${left_nav != 'proxy'&&left_nav != 'withhold'}">style="display:none;"</c:if>> -->
<!-- 		            <li class="nav"><a <c:if test="${left_nav == 'proxy' }">class="on"</c:if> href="${ctx }/hProxyMessage/showProxy"><i class="leftNavIcon LN10"></i>手机缴费服务管理</a></li> -->
<!-- 		            <li class="nav"><a <c:if test="${left_nav == 'withhold' }">class="on"</c:if> href="${ctx }/public/withhold/index"><i class="leftNavIcon LN11"></i>手机缴费号设置</a></li> -->
<!-- 		        </ul> -->
<!-- 			</li> -->
			    
		    <li>
		    	<a class="nav firstNav <c:if test="${left_nav == 'jyjl' }">on</c:if>" href="javascript:void(0);">历史交易记录管理</a>
		    	<ul class="leftNavs" <c:if test="${left_nav != 'jyjl' }">style="display:none;"</c:if>>
		            <li class="nav"><a <c:if test="${left_nav == 'jyjl' }">class="on"</c:if> href="${ctx }/hPayOrder/getHPayOrderListIndex"><i class="leftNavIcon LN10"></i>历史交易记录管理</a></li>
		        </ul>
		    </li>
		    <li>
		    	<a class="nav firstNav <c:if test="${left_nav == 'qyInfo1' }">on</c:if>" href="javascript:void(0);">企业信息管理</a>
		    	<ul class="leftNavs" <c:if test="${left_nav != 'qyInfo1' }">style="display:none;"</c:if>>
		            <li class="nav"><a <c:if test="${left_nav == 'qyInfo1' }">class="on"</c:if> href="${ctx }/public/qyInfo"><i class="leftNavIcon LN10"></i>企业信息管理</a></li>
		        </ul>
		    </li>
		    </c:if>
		    <c:if test="${admin_user.roleId==PROXY_ROLEID}">
		    <li>
		    	<a class="nav firstNav <c:if test="${left_nav == 'jyjl' }">on</c:if>" href="javascript:void(0);">历史交易记录管理</a>
		    	<ul class="leftNavs" <c:if test="${left_nav != 'jyjl' }">style="display:none;"</c:if>>
		            <li class="nav"><a <c:if test="${left_nav == 'jyjl' }">class="on"</c:if> href="${ctx }/hPayOrder/getHPayOrderListIndex"><i class="leftNavIcon LN10"></i>历史交易记录管理</a></li>
		        </ul>
		    </li>
		    <li>
		    	<a class="nav firstNav <c:if test="${left_nav == 'qyInfo1' }">on</c:if>" href="javascript:void(0);">企业信息管理</a>
		    	<ul class="leftNavs" <c:if test="${left_nav != 'qyInfo1' }">style="display:none;"</c:if>>
		            <li class="nav"><a <c:if test="${left_nav == 'qyInfo1' }">class="on"</c:if> href="${ctx }/public/qyInfo"><i class="leftNavIcon LN10"></i>企业信息管理</a></li>
		        </ul>
		    </li>
		    <li>
				<a class="nav firstNav <c:if test="${left_nav == 'proxy'||left_nav == 'withhold'||left_nav == 'upPwd'}">on</c:if>" href="javascript:void(0);">手机缴费管理</a>
			    <ul class="leftNavs" <c:if test="${left_nav != 'proxy'&&left_nav != 'withhold'&&left_nav != 'upPwd'}">style="display:none;"</c:if>>
		            <li class="nav"><a <c:if test="${left_nav == 'proxy' }">class="on"</c:if> href="${ctx }/hProxyMessage/showProxy"><i class="leftNavIcon LN10"></i>手机缴费服务管理</a></li>
		            <li class="nav"><a <c:if test="${left_nav == 'withhold' }">class="on"</c:if> href="${ctx }/public/withhold/index"><i class="leftNavIcon LN11"></i>手机缴费号设置</a></li>
		        	<li class="nav"><a <c:if test="${left_nav == 'upPwd' }">class="on"</c:if> href="${ctx }/public/upPwd"><i class="leftNavIcon LN6"></i>更改登录密码</a></li>
		        </ul>
			</li>
		    
		    </c:if>
		    
		</ul>
   </div>
   <script src="${ctx}/public/js/jquery-1.8.2.min.js"></script>
   <script>
	//  菜单效果
	$(function(){
		$(document).on("click",".firstNav",function(){
			var jqObj = $(this).parent().find("ul.leftNavs");
			$(this).parent().parent().find(".firstNav").each(function(){
				if(jqObj.parent().find("a").html()!=$(this).html()){
					$(this).parent().find("ul.leftNavs").slideUp();
					$(this).removeClass("on");
				}
			});
			if($(this).hasClass("on")){
				$(this).removeClass("on");
				jqObj.slideUp();
			} else {
				$(this).addClass("on");
				jqObj.slideDown();
			}
		});
	});
</script>