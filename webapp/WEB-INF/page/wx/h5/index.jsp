<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<html>
	<head>
    <meta charset="utf-8">
    <meta name="author" content="www.999care.com">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="#e8447e">
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="Expires" CONTENT="-1">           
    <meta http-equiv="Cache-Control" CONTENT="no-cache">           
    <meta http-equiv="Pragma" CONTENT="no-cache">
    <meta name="description" content="">
    <meta name="Keywords" content="">
    <title>手机缴费主页</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/h5/style.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/h5/comm.css">
    <link href="${ctx}/css/weui.css" rel="stylesheet" type="text/css" />
    <script src="${ctx}/public/custom/public/js/jquery.1.7.2.min.js"></script>
</head>
<body class="body">
    <div class="box">
    	
        <div class="topBox"><a class=" offen_back" href="javascript:closeWin()"></a>手机缴费主页</div>
        <div class="ch_ui_box marginTop16 m_index_box">
            <a class="ui_cell ui_cells" href="javascript:toPay()">
                <div class="ui_cell_icon_box" style="background-image:url(${ctx}/images/wx/h5/jf_icon_1.png);"></div>
                <div class="ui_center ui_cell_flex">手机缴费</div>
                <div class="ui_cell_link"></div>
            </a>
        </div>
        <div class="ch_ui_box marginTop16 m_index_box">
            <a class="ui_cell ui_cells" href="${ctx}/H5/toOrderList">
                <div class="ui_cell_icon_box" style="background-image:url(${ctx}/images/wx/h5/jy_jl_2.png);"></div>
                <div class="ui_center ui_cell_flex">历史交易记录</div>
                <div class="ui_cell_link"></div>
            </a>
        </div>
        <div class="ch_ui_box marginTop16 m_index_box">
            <a class="ui_cell ui_cells" href="javascript:showSZ()">
                <div class="ui_cell_icon_box" style="background-image:url(${ctx}/images/wx/h5/jb_icon_3.png);"></div>
                <div class="ui_center ui_cell_flex">解绑</div>
                <div class="ui_cell_link"></div>
            </a>
        </div>
    
        
       
    </div>
    
    <div class="weui_dialog_confirm" style="display: none;">
	    <div class="weui_mask"></div>
	    <div class="weui_dialog">
	        <div class="weui_dialog_hd">
	          <strong class="weui_dialog_title">您确定需要解绑吗？</strong>
	        </div>
	        <div class="weui_dialog_ft">
	            <a href="javascript:;" class="weui_btn_dialog default" onclick="closeConfirm()">取消</a>
	            <a href="javascript:;" class="weui_btn_dialog primary" onclick="unbind()">确定</a>
	        </div>
	    </div>
	</div>
	<div class="weui_dialog_alert" style="display: none;">
	    <div class="weui_mask"></div>
	    <div class="weui_dialog">
	    	<div class="weui_dialog_hd">
	    		<strong class="weui_dialog_title" id="msg"></strong>
	      	</div>
	        <div class="weui_dialog_ft">
	            <a href="javascript:closeAlert();" class="weui_btn_dialog primary">确定</a>
	        </div>
	    </div>
	</div>
	<div id="loadingToast" class="weui_loading_toast" style="display: none;">
	    <div class="weui_mask_transparent"></div>
	    <div class="weui_toast">
	        <div class="weui_loading">
	            <div class="weui_loading_leaf weui_loading_leaf_0"></div>
	            <div class="weui_loading_leaf weui_loading_leaf_1"></div>
	            <div class="weui_loading_leaf weui_loading_leaf_2"></div>
	            <div class="weui_loading_leaf weui_loading_leaf_3"></div>
	            <div class="weui_loading_leaf weui_loading_leaf_4"></div>
	            <div class="weui_loading_leaf weui_loading_leaf_5"></div>
	            <div class="weui_loading_leaf weui_loading_leaf_6"></div>
	            <div class="weui_loading_leaf weui_loading_leaf_7"></div>
	            <div class="weui_loading_leaf weui_loading_leaf_8"></div>
	            <div class="weui_loading_leaf weui_loading_leaf_9"></div>
	            <div class="weui_loading_leaf weui_loading_leaf_10"></div>
	            <div class="weui_loading_leaf weui_loading_leaf_11"></div>
	        </div>
	        <p class="weui_toast_content">数据加载中...</p>
	    </div>
	</div>
	<script type="text/javascript">
	if (typeof WeixinJSBridge == "undefined"){
	   	   if( document.addEventListener ){
	   	       document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
	   	   }else if (document.attachEvent){
	   	       document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
	   	       document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
	   	   }
	   	}else{
	   		   onBridgeReady();
	   	}

	   	function closeWin(){
	   		WeixinJSBridge.call('closeWindow');
	   	}

	   	function onBridgeReady(){
	   	}
	    
	    function unbind(){
	    	$.post("${ctx}/H5/unbind", {
	    		_t : Math.random()
	    	}, function(data) {
	    		var result = eval("(" + data + ")");
	    		if (result.code == 1) {
	    			if (result.message == "success") {
	    				$(".weui_dialog_alert").show();
	    				$("#msg").html("解绑成功！");
	    				flag = true;
	    			}
	    		}else{
	    			if (result.message == "error") {
	    				$(".weui_dialog_alert").show();
	    				$("#msg").html("解绑失败！");
	    			} 
	    		}
	    	});
	    }
	    
	    function closeAlert(){
	    	closeWin();
	    	$(".weui_dialog_alert").hide();
	    }
	    function showSZ(){
			$(".weui_dialog_confirm").show();
		}
		
		function closeConfirm(){
			$(".weui_dialog_confirm").hide();
		}
		
		function toPay(){
			$("#loadingToast").show();
			window.location.href="${ctx}/H5/toPay";
		}
	</script>

</body>
</html>
