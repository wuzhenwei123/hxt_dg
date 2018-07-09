<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<html>
<head>
	<meta charset="utf-8">
	<title>${_title}</title>
	<meta name="keywords" content="${_keywords}" />
	<meta name="description" content="${_description}" />
	<meta content="textml;charset=utf-8" http-equiv="content-type">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<meta content="webkit|ie-comp|ie-stand" name="renderer">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/public/css/style.css">
	<link href="${ctx}/css/weui.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/public/css/nav.css">
	<script src="${ctx}/public/js/jquery-1.8.2.min.js"></script>
	<script src="${ctx}/public/js/jq.Slide.js"></script>
	<script src="${ctx }/public/xcConfirm/js/xcConfirm.js"></script>
	<script src="${ctx}/js/area_public.js"></script>
	<script>
		var _hmt = _hmt || [];
		(function() {
		  var hm = document.createElement("script");
		  hm.src = "//hm.baidu.com/hm.js?352f3103d96cbaa54b79ee7b6ef60477";
		  var s = document.getElementsByTagName("script")[0]; 
		  s.parentNode.insertBefore(hm, s);
		})();
	</script>
<style>
.iLeftBox a{
	cursor: default;
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/page/public/common/top.jsp" %>
	<div class="bannerBox">
	    <div id="banner" class="banner ov">
	        <div id="JQ-content-box" class="JQ-content-box">
	            <ul id="JQ-slide-content" class="JQ-slide-content">
	            	<c:forEach items="${hlbimgList}" var="lb">
	            		<li class="fl" style="background-color:#001466; background-image:url('${pic_admin}${lb.img_path}');"></li>
	            	</c:forEach>
	            </ul>
	            <ul class="lineP JQ-slide-nav">
	            	<c:forEach items="${hlbimgList}" var="lb" varStatus="status">
	            		<li class="lines <c:if test="${status.index==0}">on</c:if>"></li>
	            	</c:forEach>
	            </ul>
	        </div>
	        <c:if test="${empty admin_user }">
	        
	        
	        <div class="loBox">
	        	<form id="form1" name="form1" method="post" action="${ctx}/index/pagelogin">
	        	<input type="hidden" id="wxflag" value="0"/>
	            <ul class="iloginItemsBox">
	            	<li class="iloginItems">
	                	<div class="iloTitle"><a href="${ctx}/index/toRegister">注册企业版用户</a></div>
	                </li>
	            	<li class="iloginItems">
	                	<div class="iItems on">
	                    	<input class="loTxt iTxt iuser" type="text" value="" placeholder="请输入账号" id="textfield4" name="adminName" maxlength="60"/>
	                    </div>
	                </li>
	                <li class="iloginItems">
	                	<div class="iItems">
	                    	<input class="loTxt iTxt ipwd" type="password" value="" placeholder="请输入密码" id="textfield5" name="passwd" maxlength="32"/>
	                    </div>
	                </li>
	                <li class="iloginItems">
	                	<div class="iItems lines code_txt">
	                    	<input class="loTxt" type="text" value="" placeholder="请输入验证码" id="textfield6" name="verify" maxlength="4"/>
	                    </div>
	                    <div class="lines code_c" style="cursor: pointer;"><img src="${ctx }/manageAdminUser/pcrimg" id="verfyImg" onclick="getImg()" width="120px" height="36px"/></div>
	                    <p class="iNoPwd"><a href="${ctx}/index/goForget">忘记密码？</a></p>
	                </li>
	                
	                <li class="iloginItems">
	                	<a class="iBtn" href="javascript:login()">立即登录</a>
	                </li>
	            </ul>
	            </form>
	        </div>
	        </c:if>
	    </div>
	</div>
	<div class="inotices">
		<div class="box inoticesBox">
	    	<span class="iTiceTitle">公告：</span>
	        <ul class="iNoticeUl">
	            <c:forEach items="${hnewnoticeList}" var="notice">
	            	<li><a href="${ctx}/public/notice/${notice.id}"><h1 class="lines">${notice.title}</h1><span class="lines"><fmt:formatDate value="${notice.createTime}" type="date"/></span></a></li>
	            </c:forEach>
	        </ul>
	        <div style="float:right;color:#01a796!important;font-size: 14px;top:13px;position:inherit;">
	        	<a href="${ctx }/public/noticelist" style="color: #01a796;">更多>></a>
	        </div>
	    </div>
	</div>
	
	<div class="iMinBox">
	<div class="box iBox">
		<div class="fl iLeftBox">
	    	<a class="fl lineP iLeftItem" href="javascript:void(0)">
	        	<i class="iicon navIcon_1"></i>
	            <div class="lines iNavItem">
	            	<h2>支持企业网上缴费</h2>
	                <p>21家银行的企业网银直接缴费</p>
	            </div>
	        </a>
	        <a class="fl lineP iLeftItem" href="javascript:void(0)">
	        	<i class="iicon navIcon_2"></i>
	            <div class="lines iNavItem">
	            	<h2>电费缴费提醒</h2>
	                <p>不再担心忘记缴费</p>
	            </div>
	        </a>
	        <a class="fl lineP iLeftItem" href="javascript:void(0)">
	        	<i class="iicon navIcon_3"></i>
	            <div class="lines iNavItem">
	            	<h2>历史账单查询</h2>
	                <p>缴费明细随时可查</p>
	            </div>
	        </a>
	        <a class="fl lineP iLeftItem" href="javascript:void(0)">
	        	<i class="iicon navIcon_4"></i>
	            <div class="lines iNavItem">
	            	<h2>发票递送到户</h2>
	                <p>免去索取发票之苦</p>
	            </div>
	        </a>
	        <span class="clear"></span>
	    </div>
	    <div class="fr iRightBox">
	    	<div class="iRtitle">服务要闻<a class="moreNews" href="${ctx}/public/newslist">更多>></a></div>
	        <div class="iHotBox">
	        	<c:forEach items="${hnewnewsList1}" var="news1">
	        		<a href="${ctx}/public/news/${news1.id}" class="iHots">
		            	<p class="fl iImgBox"><img src="${pic_admin}${news1.imgUrl}" /></p>
		                <div class="iNewsRemBox">
		                	<h1>
		                		<c:if test="${fn:length(news1.title)>13}">
									${fn:substring(news1.title, 0, 13)}...
								</c:if>
								<c:if test="${fn:length(news1.title)<=13}">
									${news1.title}
								</c:if>
		                	</h1>
<!-- 		                    <p>${news1.content}</p> -->
		                    <p></p>
		                     <span style="top:48px!important;"><fmt:formatDate value="${news1.createTime}" type="date"/></span>
		                </div>
		               
		            </a>
	        	</c:forEach>
	        </div>
	    	<ul class="iItemsUl">
	    		<c:forEach items="${hnewnewsList}" var="news">
	    			<li>
		            	<a href="${ctx}/public/news/${news.id}">
			            	<c:if test="${fn:length(news.title)>13}">
								${fn:substring(news.title, 0, 13)}...
							</c:if>
							<c:if test="${fn:length(news.title)<=13}">
								${news.title}
							</c:if>
		            	</a>
		                <span><fmt:formatDate value="${news.createTime}" type="date"/></span>
		            </li>
	    		</c:forEach>
	        </ul>
	    </div>
	    <span class="clear"></span>
	</div>
	</div>
	<%@ include file="/WEB-INF/page/public/common/footer.jsp" %>	
	<script src="${ctx}/public/js/myPlaceholder.js"></script>
	<script>
		 $(function(){
	            setHeight();
	            $("#banner").Slide({
	                effect : "scroolX",
	                speed : "normal",
	                timer : 6000
	            });
				window.onresize = function(){
					setHeight();
				}
				var ua = navigator.userAgent.toLowerCase();
				if (ua.match(/MicroMessenger/i) == "micromessenger") {//微信
					$("#wxflag").val(1);
				}
// 				$('input').placeholder();
		 });
		 function setHeight(){
			 $("#JQ-slide-content li").width( $("#banner").width());
	            $("#JQ-slide-content li").height( $("#banner").height());
	            $("#JQ-content-box").height( $("#banner").height());
		}
		 function login(){
			 	var wxflag = $("#wxflag").val();
				var username = $("#textfield4").val();
				var password = $("#textfield5").val();
				var verify = $("#textfield6").val();
				if($.trim(username)==""){
					$HXT.showInfo("请输入用户名");
// 					$('input').placeholder();
// 					alert("请输入用户名");
					getImg();
				}else if($.trim(password)==""){
					$HXT.showInfo("请输入登录密码");
// 					$('input').placeholder();
// 					alert("请输入用密码");
					getImg();
				}else if($.trim(verify)==""){
					$HXT.showInfo("请输入验证码");
// 					$('input').placeholder();
// 					alert("请输入用验证码");
				}else{
					var url = '${ctx }/index/pagelogin?_t='+Math.random();
					$.post(url ,{
						adminName:username,
						passwd:password,
						openId:'${openId}',
						wxflag:wxflag,
						verify:$.trim(verify)
					},function(data){
						var result = JSON.parse(data);
						if(result.code == 1	){
							if(result.items=='1'){
								window.location.href="${ctx}/hProxyMessage/showProxy";
							}else{
								window.location.href="${ctx}/public/payFee/index";
							}
						}else{
							getImg();
							$HXT.showInfo(result.message);
						}
					})
				}
				
			}
			function getImg(){
				var url = '${ctx }/manageAdminUser/pcrimg?_t='+Math.random();
				$("#verfyImg").attr("src",url);
			}
			document.onkeydown=function(event){
	            var e = event || window.event || arguments.callee.caller.arguments[0];
	            if(e && e.keyCode==13){
	            	login();
	            }
	        };
	</script>

</body>
</html>
