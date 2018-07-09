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
    <title>电费信息</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/h5/style.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/h5/comm.css">
    <link href="${ctx}/css/weui.css" rel="stylesheet" type="text/css" />
    <link href="<c:url value='/plus/webuploader/webuploader.css'/>" rel="stylesheet">
    <script src="${ctx}/public/custom/public/js/jquery.1.7.2.min.js"></script>
    <script src="${ctx}/js/jquery.form2.96.js" ></script>
    <script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <script src="${ctx}/js/area_public.js"></script>
</head>
<body class="">
	<div class="box">
    	<div class="topBox"><a class=" offen_back" href="javascript:history.go(-1);"></a>电费信息</div>
        <div class="body body_black"></div>
        <div class="qrBox">
        	<div class="qrItemBox">
            	<label class="">缴费号：</label>
                <span class="fr">${ammeter_no}</span>
                <font class="clear"></font>
            </div>
            <div class="qrItemBox">
            	<label class="fl">客户名称：</label>
                <span class="fl">${hAmmeterInfoQry.ammeter_name}</span>
            </div>
            <div class="qrItemBox">
            	<label class="">用电地址：</label>
                <span>${hAmmeterInfoQry.electric_address}</span>
            </div>
            
            <div class="qrItemBox">
            	<label class="">账单金额：</label>
                <span class="fr"><font>${accountFee}</font></span>
                <font class="clear"></font>
            </div>
            
            <div class="qrItemBox">
            	<label class="">滞纳金：</label>
                <span class="fr"><font>${lateFee}</font></span>
                <font class="clear"></font>
            </div>
            
            <div class="qrItemBox">
            	<label class="">应交金额：</label>
                <span class="fr"><font>${hAmmeterInfoQry.now_totalFee}元</font></span>
                <font class="clear"></font>
            </div>
            
            
            
            
            
            
        </div>
        <div class="itemsContentBox on qrjfBtnBox">
        		<c:if test="${empty hAmmeterInfoQry.bill_img}">
    				<a id="file1" class="btn btn_primary" href="javascript:uploadP();">上传缴费单</a>
        		</c:if>
        		<c:if test="${not empty hAmmeterInfoQry.bill_img}">
    				<a id="OkBtnBox" class="btn btn_primary" href="javascript:showdan();">查看缴费单</a>
        		</c:if>
    			<c:if test="${hAmmeterInfoQry.proxy_flag==1}">
    				<a id="noBtnBox" class="btn btn_primary" href="javascript:showSZ();">取消手机缴费</a>
    			</c:if>
                <a id="OkBtnBox" class="btn btn_primary" href="javascript:javascript:history.go(-1);">返回</a>
    		</div>
     
        
        
    </div>
    <form action="${ctx}/H5/uploadPic" id="inputForm" method="post" enctype="multipart/form-data">
	    <div class="weui_cells weui_cells_form" style="display: none;">
				        <div class="weui_cell">
				            <div class="weui_cell_bd weui_cell_primary">
				                <div class="weui_uploader">
				                    <div class="weui_uploader_hd weui_cell">
				                        <div class="weui_cell_bd weui_cell_primary">图片上传</div>
<!-- 				                        <div class="weui_cell_ft">0/2</div> -->
				                    </div>
				                    <div class="weui_uploader_bd">
				                        <ul class="weui_uploader_files">
<!-- 				                            <li class="weui_uploader_file" style="background-image:url(http://shp.qpic.cn/weixinsrc_pic/pScBR7sbqjOBJomcuvVJ6iacVrbMJaoJZkFUIq4nzQZUIqzTKziam7ibg/)"></li> -->
				                        </ul>
				                        <div class="weui_uploader_input_wrp">
				                            <input class="weui_uploader_input" name="file" type="file" accept="image/jpg,image/jpeg,image/png,image/gif" />
				                        </div>
				                    </div>
				                </div>
				            </div>
				        </div>
				    </div>
   </form>
    <div id="loadingToast1" class="weui_loading_toast" style="display: none;">
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
	        <p class="weui_toast_content">保存中...</p>
	    </div>
	</div>
	<div class="weui_dialog_confirm" style="display: none;">
	    <div class="weui_mask"></div>
	    <div class="weui_dialog">
	        <div class="weui_dialog_hd">
	          <strong class="weui_dialog_title">您确定取消手机缴费？</strong>
	        </div>
	        <div class="weui_dialog_ft">
	            <a href="javascript:;" class="weui_btn_dialog default" onclick="closeConfirm()">取消</a>
	            <a href="javascript:;" class="weui_btn_dialog primary" onclick="cancelP()">确定</a>
	        </div>
	    </div>
	</div>
<script>
var isImageUploadComplete = true;  
var maxsize = 1024 * 1024;  
var canvas = document.createElement("canvas");  
var ctx = canvas.getContext('2d');  
//	    瓦片canvas  
var tCanvas = document.createElement("canvas");  
var tctx = tCanvas.getContext("2d");  
var imgData;  
var imgType;

function uploadP(){
	$(".weui_uploader_input").click();
}
$(document).ready(function(){
// 	upload("${ctx}","file1",upload_call_back1,'','','pic');
});

function showSZ(){
	$(".weui_dialog_confirm").show();
}

function closeConfirm(){
	$(".weui_dialog_confirm").hide();
}

// function upload_call_back1(response){
// 	if(response.code == '1'){
// 		var fileList = response.list;
// 		if(fileList.length == 1){
// 			var newFileName = fileList[0].newName;
// 			var url = '${ctx}/H5/uploadDan';
// 			$.ajax({
// 				type: "POST",
// 				url: url,
// 				data: {a_id: '${hAmmeterInfoQry.a_id}',newFileName:newFileName},
// 				dataType: "json",
// 				success: function (data) {
// 					$("#loadingToast1").hide();
// 					if (data.status == 'success') {
// 						window.location.href = "${ctx}/H5/showdan?a_id=${hAmmeterInfoQry.a_id}";
// 					} else {
// 						$HXT.wxAlert(data.msg);
// 					}
// 				}
// 			});
// 		}
// 	}else{
// 		$("#loadingToast1").hide();
// 		$HXT.wxAlert("上传异常");
// 	}
// }
function cancelP(){
	var url = '${ctx}/H5/cancelProxy';
	$("#loadingToast1").show();
	$.ajax({
		type: "POST",
		url: url,
		data: {sid: '${subId}',ammeter_no:'${ammeter_no}'},
		dataType: "json",
		success: function (data) {
			$(".weui_dialog_confirm").hide();
			if (data.code == '1') {
				window.location.href = "${ctx}/H5/toPay";
			} else {
				$HXT.wxAlert(data.msg);
			}
		}
	});
}

function showdan(){
	window.location.href = "${ctx}/H5/showdan?a_id=${hAmmeterInfoQry.a_id}";
}

var r = 0;

$(function(){
	$(".weui_uploader_input").change(function() {
		$("#loadingToast1").show();
		isImageUploadComplete = false;
		var reader = new FileReader();
		var files = Array.prototype.slice.call(this.files);
		file = files[0];
		//获取图片大小  
		var size = file.size/1024>1024?((10 * file.size/1024/1024))/10+"MB":(file.size/1024)+"KB";
		reader.onload = function() {
			var result = this.result;
			r = r + 1;
			var img = new Image();
			img.src = result;
			//如果图片大小小于100kb，则直接上传  
			if (result.length <= maxsize) {
				img = null;
				imgData = result;
				imgType = file.type;
				isImageUploadComplete = true;
				ajaxFileUpload(r);
				return;
			}
			//图片加载完毕之后进行压缩，然后上传  
			if (img.complete) {
				callback();
			} else {
				img.onload = callback;
			}
			function callback() {
				imgData = compress(img);
				imgType = file.type;
				isImageUploadComplete = true;
				img = null;
				ajaxFileUpload(r);
			}
		};
		reader.readAsDataURL(file);
	});
})

function compress(img) {
	var initSize = img.src.length;
	var width = img.width;
	var height = img.height;
	//如果图片大于四百万像素，计算压缩比并将大小压至400万以下  
	var ratio;
	if ((ratio = width * height / 4000000) > 1) {
		ratio = Math.sqrt(ratio);
		width /= ratio;
		height /= ratio;
	} else {
		ratio = 1;
	}
	canvas.width = width;
	canvas.height = height;
	//            铺底色  
	ctx.fillStyle = "#fff";
	ctx.fillRect(0, 0, canvas.width, canvas.height);
	//如果图片像素大于100万则使用瓦片绘制  
	var count;
	if ((count = width * height / 1000000) > 1) {
		count = (Math.sqrt(count) + 1); //计算要分成多少块瓦片  
		//                计算每块瓦片的宽和高  
		var nw = (width / count);
		var nh = (height / count);
		tCanvas.width = nw;
		tCanvas.height = nh;
		for (var i = 0; i < count; i++) {
			for (var j = 0; j < count; j++) {
				tctx.drawImage(img, i * nw * ratio, j * nh * ratio, nw * ratio, nh * ratio, 0,0, nw, nh);
				ctx.drawImage(tCanvas, i * nw, j * nh, nw,nh);
			}
		}
	} else {
		ctx.drawImage(img, 0, 0, width, height);
	}
	//进行最小压缩  
	var ndata = canvas.toDataURL('image/jpeg', 0.1);
	tCanvas.width = tCanvas.height = canvas.width = canvas.height = 0;
	return ndata;
}
// 获取blob对象的兼容性写法  
function getBlob(buffer, format) {
	var Builder = window.WebKitBlobBuilder|| window.MozBlobBuilder;
	if (Builder) {
		var builder = new Builder;
		builder.append(buffer);
		return builder.getBlob(format);
	} else {
		return new window.Blob([buffer], {
			type : format
		});
	}
}

function ajaxFileUpload(r) {
	var formData = new FormData();
	if (isImageUploadComplete) {
		if (imgData) {
			var text = window.atob(imgData.split(",")[1]);
			var buffer = new Uint8Array(text.length);
			var pecent = 0, loop = null;
			for (var i = 0; i < text.length; i++) {
				buffer[i] = text.charCodeAt(i);
			}
			var blob = getBlob(buffer, imgType);
			formData.append('feedbackImg', blob);
		}
	  $.ajax({  
			url : "${ctx}/H5/uploadPic",  
			type : 'POST',  
			data : formData,  
			processData : false,  
			contentType : false,  
			dataType : 'json',  
			success : function(json) { 
				$("#loadingToast1").hide();
//					data = data.replace(/<[^>]+>/g,"");
//	            	var json = eval("("+data+")");
//             	var hidden = "<input type='hidden' id='hid"+r+"' name='imgUrl' value='"+json.serverfileName+"'>";
//             	$(".weui_uploader_files").append(hidden);
            	var url = '${ctx}/H5/uploadDan';
            	$.ajax({
    				type: "POST",
    				url: url,
    				data: {a_id: '${hAmmeterInfoQry.a_id}',newFileName:json.serverfileName},
    				dataType: "json",
    				success: function (data) {
    					if (data.code == '1') {
    						window.location.href = "${ctx}/H5/showdan?a_id=${hAmmeterInfoQry.a_id}";
    					} else {
    						$("#loadingToast1").hide();
    						$HXT.wxAlert(data.msg);
    					}
    				}
    			});
			}
		});  
	}
	return false;
}
</script>
<body>
</body>
</html>
