<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>${_title}</title>
	<meta name="keywords" content="${_keywords}" />
	<meta name="description" content="${_description}" />
	<meta content="textml;charset=utf-8" http-equiv="content-type">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<meta content="webkit|ie-comp|ie-stand" name="renderer">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="${ctx}/css/weui.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/public/css/nav.css">
    <style type="text/css">
    .cContentBox {
	    padding-left: 200px! important;
	    padding-top: 10px;
	}
	
	.codeTitle {
	    width: 85px;
	    font-size: 14px;
	    color: #000000;
	    vertical-align: middle;
	    margin-right: 10px;
	}
	.pBtnBox {
	    padding-top: 10px;
	    width: 300px! important;
	    margin-left: 110px;
	}
	</style>
</head>
<body>
<%@ include file="/WEB-INF/page/public/common/top.jsp" %>

<!---->
<div class="box main">
	<%@ include file="/WEB-INF/page/public/common/left.jsp" %>
    <div class="rightBox">
    	<div class="commBody minHeight">
        	<div class="pTitle">修改密码</div>
        	
            	<p class="yMsg"><c:if test="${empty admin_user.pwdModifyTime}">提示：您的默认密码还未更改，为保证您的账号安全，请更改密码！</c:if></p>
        	
            <div class="cContentBox">
                <div class="lineP pItems">
                    <font class="lines starFont">*</font>
                	<label class="lines codeTitle">原密码：</label>
                    <div class="lines pItem">
                    	<input type="password" id="oldpwd" class="pInput">
                    </div>
                </div>
                <div class="lineP pItems">
                    <font class="lines starFont">*</font>
                	<label class="lines codeTitle">新密码：</label>
                    <div class="lines pItem">
                    	<input type="password" id="newpwd" class="pInput">
                    </div>
                </div>
                <div class="lineP pItems">
                    <font class="lines starFont">*</font>
                	<label class="lines codeTitle">确认新密码：</label>
                    <div class="lines pItem">
                    	<input type="password" id="re_newpwd" class="pInput">
                    </div>
                </div>
                
                <div class="pItems xyItems">
                    <div class="pBtnBox">
                    	<a href="javascript:void(0);" onclick="sub();" class="iBtn">确定</a>
                    </div>
                </div>
                
            </div>
        	
        </div>
    </div>
</div>

	<%@ include file="/WEB-INF/page/public/common/footer.jsp" %>

<script src="${ctx}/public/js/jquery-1.8.2.min.js"></script>
<script src="${ctx}/public/xcConfirm/js/xcConfirm.js"></script>
<script src="${ctx}/js/area_public.js"></script>
<script src="${ctx}/public/js/myPlaceholder.js"></script>
<script>
	$(function(){
// 		$('input').placeholder();
	})
	function sub(){
		var oldpwd = $("#oldpwd").val();
		var newpwd = $("#newpwd").val();
		var re_newpwd = $("#re_newpwd").val();
		if(oldpwd == ''){
			$HXT.showInfo("请输入原密码");
			return;
		}
		if(newpwd == ''){
			$HXT.showInfo("请输入新密码");
			return;
		}
		if(re_newpwd == ''){
			$HXT.showInfo("请输入确认新密码");
			return;
		}
		if(newpwd != re_newpwd){
			$HXT.showInfo("两次密码输入不一致");
			return;
		}
		
		$.post("<c:url value='/public/save_upPwd'/>",
       	{
			oldpwd :oldpwd,
			re_newpwd :re_newpwd,
			newpwd :newpwd,
			ranNum:Math.random()
		},
       	function(data){
        	var result = eval('('+data+')'); 
            if (result.code == '1') {
            	if(result.message == '1'){
            		$HXT.showInfo("修改成功");
            		setInterval(function(){ window.location.href = "${ctx}/manageAdminUser/cloginout" },1500);
            	}
            	if(result.message == '-1'){
            		$HXT.showInfo("密码不能为空");
            	}
            	if(result.message == '-2'){
            		$HXT.showInfo("两个新密码不一致");
            	}
            	if(result.message == '-3'){
            		$HXT.showInfo("原密码错误");
            	}
             } else {
            	 $HXT.showInfo("修改失败");
             }
        });
	}
</script>
</body>
</html>
