<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/page/common/config.jsp" %>
<!-- 个人信息 -->
<div class="sidebar-item">
    <div class="media profile">
        <div class="media-thumb media-left thumb-bordereb"> 
        	<a class="img-shadow" href="javascript:void(0);">
        		<img class="thumb" src="${ctx }/images/logo.png">
        	</a> 
        </div>
        <div class="media-body">
            <h5 class="media-heading">${admin_user.nickName } <small>${admin_user.roleName }</small></h5>
            <c:if test="${empty admin_user.lastLogin }">
            <p class="data">最后访问: -/-/-/</p>
            </c:if>
            <c:if test="${not empty admin_user.lastLogin }">
            <p class="data">最后访问: <fmt:formatDate value="${admin_user.lastLogin}" type="both"/></p>
            </c:if>
        </div>
    </div>
</div>
<!-- 个人信息 -->
<!-- // sidebar item - profile -->
<!-- 左边开始 -->

<%-- 菜单换算开始 --%>
<c:set value="${requestPath }" var="cPath"></c:set>
<c:set value="" var="pid"></c:set>
<c:forEach items="${system_columnList }" var="column">
	<c:forEach items="${column.childs }" var="cColumn">
		<c:if test="${cColumn.columnUrl eq cPath }">
			<c:set value="${column.columnId }" var="pid"></c:set>
		</c:if>
	</c:forEach>
</c:forEach>
<%-- 菜单换算开始 --%>
<ul id="mainSideMenu" class="nav nav-list nav-side">
	<c:if test="${pid == '' }">
        <li class="accordion-group">
             <div class="accordion-heading active"> <a href="#accDash" data-parent="#mainSideMenu" data-toggle="collapse" class="accordion-toggle"> <span class="item-icon fontello-icon-home"></span> <i class="chevron fontello-icon-right-open-3"></i> 首页 </a> </div>
             <ul class="accordion-content nav nav-list collapse in" id="accDash">
                 <li class="active"> <a href="${ctx }/manageAdminUser/main"> <i class="fontello-icon-right-dir"></i>首页</a> </li>
             </ul>
         </li>
	</c:if>
	<c:if test="${pid != '' }">
        <li class="accordion-group">
             <div class="accordion-heading"> <a href="#accDash" data-parent="#mainSideMenu" data-toggle="collapse" class="accordion-toggle"> <span class="item-icon fontello-icon-home"></span> <i class="chevron fontello-icon-right-open-3"></i> 首页 </a> </div>
             <ul class="accordion-content nav nav-list collapse" id="accDash">
                 <li> <a href="${ctx }/manageAdminUser/main"> <i class="fontello-icon-right-dir"></i>首页 </a> </li>
             </ul>
         </li>
	</c:if>
	<c:forEach items="${system_columnList }" var="column">
		<c:set var="columnImg" value="${column.columnImg }"></c:set>
   		<c:if test="${empty columnImg }">
   		<c:set var="columnImg" value="fontello-icon-monitor"></c:set>
   		</c:if>
	
    <li class="accordion-group">
    	<c:if test="${column.columnId == pid }">
        <div class="accordion-heading active"> 
    	</c:if>
    	<c:if test="${column.columnId != pid }">
        <div class="accordion-heading"> 
    	</c:if>
        	<a href="#${column.columnId }" data-parent="#mainSideMenu" data-toggle="collapse" class="accordion-toggle"> 
        		<span class="item-icon ${columnImg }"></span> 
        		<i class="chevron fontello-icon-right-open-3"></i>
        		${column.columnName }
        	</a> 
        </div>
        <c:if test="${column.columnId == pid }">
        <ul class="accordion-content nav nav-list collapse in" id="${column.columnId }">
    	</c:if>
    	<c:if test="${column.columnId != pid }">
        <ul class="accordion-content nav nav-list collapse" id="${column.columnId }">
    	</c:if>
    		<c:forEach items="${column.childs }" var="cColumn">
				<c:if test="${cColumn.columnUrl == cPath }">
            <li class="active"> <a href="${ctx }${cColumn.columnUrl }"> <i class="fontello-icon-right-dir"></i>${cColumn.columnName } </a> </li>
				</c:if>
				<c:if test="${cColumn.columnUrl != cPath }">
            <li > <a href="${ctx }${cColumn.columnUrl }"> <i class="fontello-icon-right-dir"></i>${cColumn.columnName } </a> </li>
				</c:if>
			</c:forEach>
        </ul>
    </li>
	</c:forEach>
</ul>
 <!-- 左边结束 -->
<!-- // sidebar menu -->

<div class="sidebar-item"></div>
<!-- // sidebar item --> 
