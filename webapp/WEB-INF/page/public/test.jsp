<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<html>
	<head>
    <meta charset="utf-8">
    <link href="${ctx}/css/weui.css" rel="stylesheet" type="text/css" />
    
    <script src="${ctx}/public/js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript">
    	function showSZ(){
    		$(".weui_dialog_confirm").show();
    	}
    	
    	function closeConfirm(){
    		$(".weui_dialog_confirm").hide();
    	}
    	
    	function showTS(){
    		$(".weui_dialog_alert").show();
    	}
    	function closeAlert(){
    		$(".weui_dialog_alert").hide();
    	}
    	
    	function showJZ(){
    		$(".weui_loading_toast").show();
    	}
    </script>
</head>
<body class="body">
<div class="box">
	<input type="button" value="选择提示框" onclick="showSZ()">
	<input type="button" value="提示框" onclick="showTS()">
	<input type="button" value="加载层" onclick="showJZ()">
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
    		<strong class="weui_dialog_title" id="msg">由于您已成功绑定<br/>系统自动转向解绑页面</strong>
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
        <p class="weui_toast_content">努力加载中</p>
    </div>
</div>
</body>
</html>
