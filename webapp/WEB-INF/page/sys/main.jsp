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
<title>${_title }</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="description" content="">
<meta name="author" content="">

<!-- Le styles -->
<%@ include file="/WEB-INF/page/common/css.jsp" %>

</head>

<body class="sidebar-left">
<div class="page-container">
	<!-- 头开始 -->
    <%@ include file="/WEB-INF/page/common/header.jsp" %>
   	<!--  头结束 -->
    <!-- // header-container -->
    
    <div id="main-container">
        <div id="main-sidebar" class="sidebar sidebar-inverse">
        	<!-- // 左边 -->
        	<%@ include file="/WEB-INF/page/common/left.jsp" %>
	        <!-- // 左边 -->
        </div>
        <!-- // sidebar -->
        <!--  右边开始 -->
        <div id="main-content" class="main-content container-fluid">
            <div id="page-content" class="page-content">
            	<section>
            		<div class="span3 well well-nice bg-gray-light" style="margin-top: 10% ;text-align: center;width: 90%;">
                         <h3 class="simple-header">欢迎使用恒信通管理平台</h3>
                         <p><img alt="" src="${ctx }/images/logo.png"></p>
                     </div>
            		
            	</section>
            </div>
            <!-- // page content --> 
            
        </div>
       	<!--  右边结束 -->
        <!-- // main-content --> 
        
    </div>
    <!-- // main-container  -->
    
    <footer id="footer-fix">
        <div id="footer-sidebar" class="footer-sidebar">
            <div class="navbar">
                <div class="btn-toolbar"> <a class="btn btn-glyph btn-link" href="javascript:void(0);"><i class="fontello-icon-up-open-1"></i></a> </div>
            </div>
        </div>
        <!-- // footer sidebar -->
        	<%@ include file="/WEB-INF/page/common/footer.jsp" %>
        <!-- // footer content --> 
        
    </footer>
    <!-- // footer-fix  --> 
    
</div>
<!-- // page-container  --> 

<!-- Le javascript --> 
<%@ include file="/WEB-INF/page/common/js.jsp" %>
<!-- Only This Demo Page --> 
<div id="temModal" class="modal hide fade" tabindex="-1" data-width="50%"></div>
<script type="text/javascript">
	var $modal = $('#temModal');
	function toAdd(){
		$('body').modalmanager('loading');
		setTimeout(function () {
			$modal.load('${ctx}/test.html', '', function () {
				$modal.modal();
			});
		}, 800);
	}
	function delAll(){
		bootbox.confirm("你确定要删除吗?","取消","确定", function(result) {
			if(result){
				var n = notyfy({
					text: '<strong>删除成功!</strong>',
					type: 'success',
					layout: 'topCenter',
					theme: 'boolight'
				});	
				console.log(n);
				n.close();
			}else{
				
			}
		});
	}
</script>
</body>
</html>
 