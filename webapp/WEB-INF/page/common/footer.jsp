<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <div id="footer-content" class="footer-content">
    <div class="navbar navbar-inverse">
        <div class="navbar-inner">
            <ul class="nav pull-left">
                <li class="divider-vertical hidden-phone"></li>
                <li><a id="btnToggleSidebar" class="btn-glyph fontello-icon-resize-full-2 tip hidden-phone" href="javascript:void(0);" title="收起做导航"></a></li>
                <li class="divider-vertical hidden-phone"></li>
<!--                 <li><a id="btnChangeSidebar" class="btn-glyph fontello-icon-login tip hidden-phone" href="javascript:void(0);" title="导航切换"></a></li> -->
<!--                 <li class="divider-vertical"></li> -->
<!--                 <li><a id="btnChangeSidebarColor" class="btn-glyph fontello-icon-palette tip" href="javascript:void(0);" title="导航变色"></a></li> -->
                <li class="divider-vertical"></li>
                <li><a id="btnRefresh" class="btn-glyph fontello-icon-cw tip" href="javascript:void(0);" title="刷新页面"></a></li>
                <li class="divider-vertical"></li>
                <li><a class="fontello-icon-home-3 tip" href="${ctx }/manageAdminUser/main" title="首页"></a></li>
                <li class="divider-vertical"></li>
            </ul>
            <ul class="nav pull-right">
<!--                 <li class="divider-vertical"></li> -->
<!--                 <li><a class="btn-glyph fontello-icon-help-2 tip" href="javascript:void(0);" title="帮助中心"></a></li> -->
<!--                 <li class="divider-vertical"></li> -->
<%--                 <li><a id="btnSystemSet" class="btn-glyph fontello-icon-cog-4 tip" href="javascript:show('<c:url value="/adminSkins/toAdd"/>','');" title="系统设置"></a></li> --%>
                <li class="divider-vertical"></li>
                <li><a id="btnLogout" class="btn-glyph fontello-icon-logout-1 tip" href="${ctx }/manageAdminUser/loginout" title="系统退出"></a></li>
                <li class="divider-vertical"></li>
                <li><a id="btnScrollup" class="scrollup btn-glyph fontello-icon-up-open-1" href="javascript:void(0);"><span class="hidden-phone">返回顶部</span></a></li>
            </ul>
        </div>
    </div>
</div>
