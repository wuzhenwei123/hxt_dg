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
    <title>付款详情</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/h5/style.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/h5/comm.css">
    <link href="${ctx}/css/weui.css" rel="stylesheet" type="text/css" />
    <script src="${ctx}/public/custom/public/js/jquery.1.7.2.min.js"></script>
    <script src="${ctx}/js/area_public.js"></script>
    <style type="text/css">
    .weui_toast {
	    top: 30%! important;
	}
    .weui_loading {
	    top: 26%! important;
	}
    </style>
</head>
<body class="">

<div class="box">
    <div class="topBox"><a class=" offen_back" href="javascript:history.go(-1);"></a>付款详情</div>
    <div class="body body_black"></div>
    <div class="ch_ui_box accItems gr payInfoBox">
        <div class="ui_cell ui_cells">
            <div class="ui_center_left ">开户行名称：</div>
            <div class="ui_center ui_cell_flex">
                <font class="accJiaojian">${hProxyMessage.bank_name}</font>
            </div>
        </div>
        <div class="ui_cell ui_cells">
            <div class="ui_center_left ">开户行账号名称：</div>
            <div class="ui_center ui_cell_flex">
                <font class="accJiaojian">${hProxyMessage.payAccountName}</font>
            </div>
        </div>
        <div class="ui_cell ui_cells">
            <div class="ui_center_left ">付款账号：</div>
            <div class="ui_center ui_cell_flex">
                <font class="accJiaojian">${hProxyMessage.payBankNumber}</font>
            </div>
        </div>
        <div class="ui_cell ui_cells">
            <div class="ui_center_left ">应交付合计：</div>
            <div class="ui_center ui_cell_flex">
                <font class="accJiaojian black">￥${totalFeeStr}</font>
            </div>
        </div>
     </div>
    
    <div class="pay_input_box"><input type="password" placeholder="请输入您的密码" id="pwd"/></div>
    
    <div class="itemsContentBox on">
    	<a id="OkBtnBox" class="btn btn_primary btnRed" href="javascript:jiaofei();">缴费</a>
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
        <p class="weui_toast_content">请稍后，正在为您缴费...</p>
    </div>
</div>
<script>
	function jiaofei(){
		var url = '${ctx}/H5/jiaofei';
		var subId = '${subId}';
		var pwd = $("#pwd").val();
		var cId = '${cId}';
		var payType = '${payType}';
		var no = '${no}';
		$("#loadingToast").show();
		var url1 = '${ctx}/H5/checkLoginState';
	   	$.ajax({
	   		type: "POST",
	   		url: url1,
	   		dataType: "json",
	   		success: function (data) {
	   			if (data.code == '1') {
	   				$.ajax({
	   			   		type: "POST",
	   			   		url: url,
	   			   		data: {subId:subId,payType:payType,cId:cId,pwd:pwd,no:no},
	   			   		dataType: "json",
	   			   		success: function (result) {
	   			   			$("#loadingToast").hide();
//	   		 	   			alert(data);
//	   		 	   			alert(data);
//	   		 	   			var result = JSON.parse(data);
	   						if(result.code==1){
	   							var str = result.items;
	   				   			var o_no = str.split("_")[0];
	   				   			var query_id = str.split("_")[1];
	   				   			var fee = str.split("_")[2];
	   							window.location.href = "${ctx}/H5/toPayOk?o_no="+o_no+"&query_id="+query_id+"&fee="+fee;
	   			   			} else {
//	   		 	  				$HXT.wxAlert(result.msg);
	   							if(result.items!=null){
	   								var str = result.items;
	   								var o_no = str.split("_")[0];
	   					   			var query_id = str.split("_")[1];
	   					   			var fee = str.split("_")[2];
	   					   			var msg = str.split("_")[3];
	   				   				window.location.href = "${ctx}/H5/toPayFail?o_no="+o_no+"&query_id="+query_id+"&fee="+fee+"&msg="+msg;
	   							}else{
	   								$HXT.wxAlert(result.message);
	   							}
	   			  				
	   			   			}
	   			   		}
	   			   	});
	   			}else{
	   				$("#loadingToast").hide();
	   				$HXT.wxAlert(data.message);
	   			}
	   		}
	   	});
	}
</script>
</body>
</html>
