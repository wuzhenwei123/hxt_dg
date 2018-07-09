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
    <title>变更技术人员</title>
     <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/customForm.css">
     <link rel="stylesheet" type="text/css" href="${ctx}/public/xcConfirm/css/xcConfirm.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/style.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/comm.css">
    <link href="${ctx}/css/weui.css" rel="stylesheet" type="text/css" />
    <script src="${ctx}/public/custom/public/js/jquery.1.7.2.min.js"></script>
	<script src="${ctx}/public/xcConfirm/js/xcConfirm.js"></script>
	<script src="${ctx}/js/area_public.js"></script>
</head>
<body class="body">
<div class="box">
    <div class="topBox"><a class=" offen_back" href="javascript:history.go(-1);"></a>变更技术人员</div>
    
    <div class="bsBox marginTop16">
    	
    
   
	
    <div class="bsBox ItemLi marginTop16">
    	<ul class="clearAfter ItemuL">
			<li class="fl">原支持人员</li>
            <li class="fr">${user.nickName}</li>        
        </ul>
        <ul class="clearAfter ItemuL selBox">
			<li class="fl">变更支持人员</li>
            <li class="fr">
            	<div class="input">
                	<div class="diy_select select1">
                <input type="hidden" class="diy_select_input" name="" value="">
                <div class="diy_select_txt">请选择技术人员</div>
                <div class="diy_select_btn"></div>
                <ul class="diy_select_list" style="display: none;">
                	<c:forEach items="${listUser}" var="list">
                    	<li class="">${list.realName}<input type="hidden" value="${list.adminId}"></li>
                	</c:forEach>
                   
                </ul>
            </div>
                </div>
            </li>        
        </ul>
        <ul class="ItemuL">
			<li class="fl">客户名称</li>
            <li class="fr">${company.name}</li>        
        </ul>
        <ul class="clearAfter ItemuL">
			<li class="fl">联系人姓名</li>
            <li class="fr">${company.contact_name}</li>        
        </ul>
        <ul class="clearAfter ItemuL">
			<li class="fl">联系人手机</li>
            <li class="fr">${company.contact_phone}</li>        
        </ul>
        <span class="clear"></span>
        
        
        <div class="itemsContentBox lisBtnBox center">
            <a id="OkBtnBox" href="javascript:void(0);" class="btn btn_primary searBtnBox">变更支持人员</a>
        </div>
    </div>
    
</div>
<div class="weui_dialog_confirm" style="display: none;">
    <div class="weui_mask"></div>
    <div class="weui_dialog">
        <div class="weui_dialog_hd">
          <strong class="weui_dialog_title">确定变更该用户的支持人员吗？</strong>
        </div>
        <div class="weui_dialog_ft">
            <a href="javascript:;" class="weui_btn_dialog default" onclick="closeConfirm()">取消</a>
            <a href="javascript:;" class="weui_btn_dialog primary" onclick="okCall()">确定</a>
        </div>
    </div>
</div>
<script src="${ctx}/public/custom/public/js/select.js"></script>
<script>
	$(function(){
		$("#OkBtnBox").click(function(){
			var diy_select_input = $(".diy_select_input").val();
			if(diy_select_input!=""){
				$(".weui_dialog_confirm").show();
			}else{
				$HXT.wxAlert("请选择技术人员!");
			}
		});
	})
	
	var flag = true;
	function okCall(){
		var diy_select_input = $(".diy_select_input").val();
		var companyId = '${company.id}';
		var companyId = '${company.id}';
		var oldAdminId = '${company.servicerId}';
		var b=/<input type=\"hidden\" value=\"([^\"]*?)\">/gi
		var s=diy_select_input.match(b)
		var adminId = RegExp.$1;
		if(flag){
			flag = false;
			$.post("${ctx}/hCompany/changePeople",{"adminId":adminId,"companyId":companyId,"oldAdminId":oldAdminId,"_t":Math.random()},function(data){
				flag = true;
				var result = eval('('+data+')');
	            if (result.code == '1') {
	            	$HXT.wxAlert("更新成功!");
	            	window.location.href = '${ctx}/weixin/toMyCustomer?openId=${admin_user.openId}';
	            }else{
	            	$HXT.wxAlert("更新失败!");
	            }
			});
		}
	}
	
	function closeConfirm(){
		$(".weui_dialog_confirm").hide();
	}
</script>
</body>
</html>
