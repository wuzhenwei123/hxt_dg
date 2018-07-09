<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/page/common/config.jsp" %>
<div id="header-container">
   <div id="header">
       <div class="navbar navbar-inverse navbar-fixed-top">
           <div class="navbar-inner">
               <div class="container-fluid">
                   <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
                   <a class="brand" href="javascript:void(0);">
<%--                    	<img src="${ctx }/images/logo.png" width="47"> --%>
                   	${_title }
                   </a>
                   <div class="nav-collapse collapse">
<!--                        <ul class="nav user-menu visible-desktop"> -->
<!--                            <li><a class="btn-glyph fontello-icon-edit tip-bc" href="javascript:void(0);" title="消息"><span class="badge badge-important">8</span></a></li> -->
<!--                            <li><a class="btn-glyph fontello-icon-mail-1 tip-bc" href="javascript:void(0);" title="邮件"></a></li> -->
<!--                            <li><a class="btn-glyph fontello-icon-lifebuoy tip-bc" href="javascript:void(0);" title="支持"><span class="badge badge-important">4</span></a></li> -->
<!--                        </ul> -->
                       <!-- 头部导航 -->
                       <%-- 菜单换算开始 --%>
						<c:set value="${requestPath }" var="cPath"></c:set><!-- 当前子类路径 -->
						<c:set value="" var="pid"></c:set><!-- 当前子类ID -->
						<c:set value="" var="pName"></c:set><!-- 当前父类名称 -->
						<c:set value="" var="cName"></c:set><!-- 当前子类名称 -->
						<c:forEach items="${system_columnList }" var="column">
							<c:forEach items="${column.childs }" var="cColumn">
								<c:if test="${cColumn.columnUrl eq cPath }">
									<c:set value="${column.columnId }" var="pid"></c:set>
								</c:if>
							</c:forEach>
						</c:forEach>
						<%-- 菜单换算开始 --%>
                       <ul class="nav" style="display: none;">
                       		<c:if test="${pid == '' }">
                       		<li class="active"> <a href="${ctx }/manageAdminUser/main"><span class="fontello-icon-home f12"></span>首页</a> </li>
                       		</c:if>
                       		<c:if test="${pid != '' }">
                       		<li> <a href="${ctx }/manageAdminUser/main"><span class="fontello-icon-home f12"></span>首页</a> </li>
                       		</c:if>
                       	<c:forEach items="${system_columnList }" var="column">
                       		<c:set var="columnImg" value="${column.columnImg }"></c:set>
                       		<c:if test="${empty columnImg }">
                       		<c:set var="columnImg" value="fontello-icon-monitor"></c:set>
                       		</c:if>
                       		
                       		<c:if test="${column.columnId == pid }">
                       			<c:if test="${not empty column.childs }">
                       				<c:set value="${column.columnName }" var="pName"></c:set>
	                       			<li class="dropdown active"> 
		                           		<a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown">
		                           			<span class="${columnImg }"></span>${column.columnName }
		                           			<b class="caret"></b>
		                           		</a>
		                               <ul class="dropdown-menu">
		                               	<c:forEach items="${column.childs }" var="cColumn">
		                               		<c:if test="${cColumn.columnUrl == cPath }">
		                               		<c:set value="${cColumn.columnName }" var="cName"></c:set>
		                               		</c:if>
		                                   <li><a tabindex="-1" href="${ctx }${cColumn.columnUrl }">${cColumn.columnName }</a></li>
					    				</c:forEach>
		                               </ul>
		                           	</li>
                       			</c:if>
					    	</c:if>
					    	<c:if test="${column.columnId != pid }">
                       			<c:if test="${not empty column.childs }">
	                       			<li class="dropdown"> 
		                           		<a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown">
		                           			<span class="${columnImg }"></span>${column.columnName }
		                           			<b class="caret"></b>
		                           		</a>
		                               <ul class="dropdown-menu">
		                                   <c:forEach items="${column.childs }" var="cColumn">
			                                   <li><a tabindex="-1" href="${ctx }${cColumn.columnUrl }">${cColumn.columnName }</a></li>
						    				</c:forEach>
		                               </ul>
		                           	</li>
                       			</c:if>
					    	</c:if>
                       	</c:forEach>
                       </ul>
                       <!-- 头部导航 -->
                   </div>
               </div>
           </div>
       </div>
       <!-- // navbar -->
       <div class="header-drawer">
           <div class="mobile-nav text-center visible-phone"> <a href="javascript:void(0);" class="mobile-btn" data-toggle="collapse" data-target=".sidebar"><i class="aweso-icon-chevron-down"></i> 系统菜单</a> </div>
           <!-- // Resposive navigation -->
           <!-- 小导航 -->
           <div class="breadcrumbs-nav hidden-phone">
               <ul id="breadcrumbs" class="breadcrumb">
                   <li><a href="${ctx }/manageAdminUser/main"><i class="fontello-icon-home f12"></i> 首页</a> <span class="divider">/</span></li>
                   <c:if test="${pName != '' }">
	                   <li class="dropdown"><a href="javascript:void(0);">${pName}</a> <span class="divider">/</span>
	                       <ul class="dropdown-menu">
	                        <c:forEach items="${system_columnList }" var="column">
		                   		<c:if test="${column.columnId == pid && not empty column.childs }">
		                   			<c:forEach items="${column.childs }" var="cColumn">
		                           <li><a href="${ctx }${cColumn.columnUrl }">${cColumn.columnName }</a></li>
		                   			</c:forEach>
		                   		</c:if>
		                   </c:forEach>
	                       </ul>
	                   </li>
                   </c:if>
                   <li class="active">${cName } </li>
               </ul>
           </div>
           <!-- 小导航 -->
           <!-- // breadcrumbs --> 
       </div>
       <!-- // drawer --> 
    </div>
</div>