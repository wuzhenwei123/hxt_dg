<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
 <!DOCTYPE html>
<!--[if lt IE 7 ]> <html class="ie6"> <![endif]-->
<!--[if IE 7 ]>    <html class="ie7"> <![endif]-->
<!--[if IE 8 ]>    <html class="ie8"> <![endif]-->
<!--[if IE 9 ]>    <html class="ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html>
<!--<![endif]-->

<head>
<meta charset="utf-8">
<title>后台管理系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="description" content="">
<meta name="author" content="">

<!-- Custom styles -->
<style type="text/css">
.signin-content {
  max-width: 360px;
  margin: 0 auto 20px;
}
</style>

<!-- Le styles -->
<%@ include file="/WEB-INF/page/common/css.jsp" %>
</head>

<body class="signin signin-vertical">
<div class="page-container">
    <div id="header-container">
        <div id="header">
            <div class="navbar-inverse navbar-fixed-top">
                <div class="navbar-inner">
                    <div class="container-fluid"> </div>
                </div>
            </div>
            <!-- // navbar -->
            
            <div class="header-drawer" style="height:3px"> </div>
            <!-- // breadcrumbs --> 
        </div>
        <!-- // drawer --> 
    </div>
    <!-- // header-container -->
    
    <div id="main-container">
        <div id="main-content" class="main-content container">
            <div class="signin-content">
                <h1 class="welcome text-center" style="line-height: 0.6;">
                	<span>欢迎"${admin_user.adminName }"登陆</span>
                <br />
<!--                     Boo<small>admin panel</small> -->
                </h1>
                <div class="well well-nice form-dark">
                    <div class="tab-content overflow">
                        <div class="tab-pane fade in active" id="login">
                            <h3 class="no-margin-top"><i class="fontello-icon-user-4"></i> 首次登陆请修改密码</h3>
                            <form class="form-tied margin-00" id="updateForm" name="login_form">
                                <fieldset>
                                    <legend class="two"><span>Legend</span></legend>
                                    <ul>
                                        <li>
                                            <input id="username" name="adminName" tabindex="1" value="${admin_user.adminName }" readonly="readonly" class="input-block-level" type="text"  placeholder="账号/邮箱">
                                        </li>
                                        <li>
                                            <input name="passwd" id="passwd1" tabindex="2" class="input-block-level" type="password" placeholder="新密码" isNotNull="true" warnName="新密码">
                                            <span class="help-inline text-red">*</span>
                                        </li>
                                        <li>
                                            <input name="passwd" id="passwd2" tabindex="2" class="input-block-level" type="password" placeholder="确认密码" isNotNull="true" warnName="确认密码">
                                            <span class="help-inline text-red">*</span>
                                        </li>
                                    </ul>
                                    <button type="button" onclick="update()" class="btn btn-envato btn-block btn-large">修改</button>
                                    <hr class="margin-xm">
                                    <label class="pull-left text-red">
<!--                                     	<input id="remember" class="checkbox" type="checkbox"> -->
<!--                                                                                                                                             记住密码  -->
										<c:if test="${param.error == '-1'}">用户名、密码、验证码不能为空!</c:if>
									</label>
<!--                                     <a href="#forgot" class="link pull-right" data-toggle="tab">忘记密码?</a> -->
                                </fieldset>
                            </form>
                            <!-- // form --> 
                            
                        </div>
                        <!-- // Tab Login -->
                        
                    </div>
                </div>
                <!-- // Well-Nice --> 
                <div class="web-description">
                    <h5>Copyright &copy; 2012 aixuetang.com</h5>
                    <p>
                    	Backend and Frontend interface for web &amp; mobile development. <br />
                        All rights reserved.
                    </p>
                </div>
            </div>
            <!-- // sign-content --> 
            
        </div>
        <!-- // main-content --> 
        
    </div>
    <!-- // main-container  --> 
    
</div>
<!-- // page-container --> 

<!-- Le javascript --> 
<%@ include file="/WEB-INF/page/common/js.jsp" %>

<script>
	$(document).ready(function() {
		$("#username").focus();
	});
	function getImg(){
		var url = '${ctx }/manageAdminUser/pcrimg?_t='+Math.random();
		$("#verfyImg").attr("src",url);
	}
	function update(){
		var adminId = "${admin_user.adminId}";
		var passwd1 = $("#passwd1").val();
		var passwd2 = $("#passwd2").val();
		var flag = validateForm('updateForm');
		
		
		var isFirst = 1;
	    if (flag){ 
			if(passwd1 != passwd2){
				tipError("两次密码不一致!");
				return ;
			}
			
	        $.post("<c:url value='/manageAdminUser/updateManageAdminUser'/>",
	        	{
	    		adminId : adminId,
	    		passwd : passwd1,
	    		isFirst : isFirst,
	    		lastLogin : new Date(),
				 _t:Math.random()},
	        	function(data){
		        	var result = eval('('+data+')'); 
		            if (result.code == '1') {
		              	window.location.href= "${ctx}/manageAdminUser/main";
		             } else {
		             }
		            
	        });
	    }
	}
</script>
</body>
</html>