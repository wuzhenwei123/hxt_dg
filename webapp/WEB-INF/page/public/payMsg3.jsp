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
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="${ctx}/css/weui.css" rel="stylesheet" type="text/css" />
    
    <link rel="stylesheet" type="text/css" href="${ctx}/public/custom/public/css/customForm.css">
<link rel="stylesheet" type="text/css" href="${ctx}/public/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/public/css/hxtPay.css">
<link rel="stylesheet" type="text/css" href="${ctx}/public/custom/public/css/customForm.css">
<link href="<c:url value='/plus/webuploader/webuploader.css'/>" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${ctx}/public/css/nav.css">
<style type="text/css">
.webuploader-pick{
 		padding: 0px;
 		width: 100%;
 		background:#f1521b;
 	}
 	.up_ht_btn1 {
    /* background-color: #f1521b; */
	    width: 150px;
	    height: 30px;
	    line-height: 30px;
	    text-align: center;
	    color: #ffffff;
	    font-size: 12px;
	    border-radius: 3px;
	    position: relative;
	    overflow: hidden;
	    cursor: pointer;
 	}
</style>
</head>
<body>
<%@ include file="/WEB-INF/page/public/common/top.jsp" %>

<!---->
<div class="box main">
	<%@ include file="/WEB-INF/page/public/common/left.jsp" %>
	<input type="hidden" id="projectPath" value="${ctx}">
    <div class="rightBox jyListBox">
    	<div class="commBody jySearchBox m_pay">
            
            <div class="m_pay_top_items">
                <div class="m_pay_top_item b2b">
                    <p>手机缴费信息管理</p>
                </div>
            </div>
            <div class="type_box_pay three"></div>
            
            <div class="pay_pay_box">
            	<div class="pay_img_box on">
                	<img src="${ctx}/${hProxyMessage.hetongUrl}" style=" display:none;" id="img1"/>
                    <c:if test="${empty hProxyMessage}">
                    	<div class="up_ht_txt_box" id="preUpload">
	                    	<p><span>格式要求：</span>上传加盖公章和账务章的原件照片或扫描件，支持.jpg.jpeg.bmp.gif.png格式照片，不超过1M。</p>
	                        <p class="up_b_box"><label class=" line_l up_ht_btn" id="file1">上传</label></p>
	                    </div>
                    </c:if>
                    <input id="com_license_img" value="${hProxyMessage.hetongUrl }" type="hidden">
                    <div class="img_loding"  style=" display:none;" id="load"></div>
                </div>
                <div class="lineP up_btn_box" <c:if test="${empty hProxyMessage}">style=" display:none;"</c:if> id="congchuan">
                	<label class=" line_l up_ht_btn" id="file2" style="width: 150px;">上传已盖章的协议扫描件</label><label class=" line_l up_ht_btn1" style="color: red;margin-left: 5px;">（文件大小不能超过5M）</label>
                </div>
                <div class="lineP up_ok_box">
                	<a class="line_l pay_btn" href="javascript:pre()">上一步</a>
                    <a class="line_l pay_btn" href="javascript:save()">提交</a>
                </div>
            </div>
            
        </div>  
    </div>
</div>




<%@ include file="/WEB-INF/page/public/common/footer.jsp" %>

<div class="preview maskbg" data-p="" onclick="closePreview()">
	<img src="${ctx}/images/loading.gif" />
</div>
<script src="${ctx}/public/js/jquery-1.8.2.min.js"></script>
<script src="${ctx}/public/xcConfirm/js/xcConfirm.js"></script>
<script type="text/javascript" src="<c:url value='/plus/webuploader/webuploader.js'/>"></script>  
<script type="text/javascript" src="<c:url value='/js/common.js'/>"></script> 
<script src="${ctx}/js/area_public.js"></script>
<script src="${ctx}/public/custom/public/js/custominput.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	upload("${ctx}","file1",upload_call_back1,'','','pic');
	upload("${ctx}","file2",upload_call_back2,'','','pic');
});
function upload_call_back1(response){
	if(response.code == '1'){
		var fileList = response.list;
		if(fileList.length == 1){
			var newFileName = fileList[0].newName;
			var oldName = fileList[0].oldName;
			$("#com_license_img").val(newFileName);
			$("#img1").attr('src',$("#projectPath").val()+newFileName);
			$("#file1 > .webuploader-pick").html(oldName);
			$("#img1").show();
			$("#congchuan").show();
			$("#load").hide();
			$(".loading").remove();
			$("#preUpload").hide();
		}
	}else{
		$HXT.showInfo("上传异常");
	}
}
function upload_call_back2(response){
	if(response.code == '1'){
		var fileList = response.list;
		if(fileList.length == 1){
			var newFileName = fileList[0].newName;
			var oldName = fileList[0].oldName;
			$("#com_license_img").val(newFileName);
			$("#img1").attr('src',$("#projectPath").val()+newFileName);
			$("#file1 > .webuploader-pick").html(oldName);
			$("#img1").show();
			$("#congchuan").show();
			$("#load").hide();
			$(".loading").remove();
			$("#preUpload").hide();
		}
	}else{
		$HXT.showInfo("上传异常");
	}
}
function preview(newFileName,obj){
	//获取图片的宽和高
	var image = new Image();
    image.src = obj.src;
    var naturalWidth = image.width;
    var naturalHight = image.height;
	$(".preview").find("img").attr('src',$("#projectPath").val()+newFileName);
	$(".preview").find("img").attr('width',naturalWidth);
	$(".preview").find("img").attr('height',naturalHight);
	$(".preview").show();
}

function closePreview(){
	$(".preview").hide();
}
function pre(){
	window.location.href = "${ctx}/hProxyMessage/toPayMsg2?proxyMessageId=${hProxyMessage.id}";
}
function save(){
	var com_license_img = $("#com_license_img").val();
	if(com_license_img==""){
		$HXT.showInfo("请上传合同");
	}else{
		$.post("<c:url value='/hProxyMessage/saveProxyMessageHT'/>",
	        	{
					id:${hProxyMessage.id},
					hetongUrl:com_license_img,
				 _t:Math.random()},
	        	function(data){
		        	var result = eval('('+data+')'); 
		            if (result.code == '1') {
		            	$HXT.showInfo("提交成功!");
		            	window.location.href = "${ctx}/hProxyMessage/toPayMsg4?proxyMessageId="+result.items;
		             } else {
		            	 $HXT.showInfo(result.message);
		             }
	        });
	}
}
</script>
</body>
</html>
