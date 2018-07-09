<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="uploadify.css" rel="stylesheet" type="text/css" /> 
<script src="${ctx }/js/jquery.min.js" type="text/javascript"></script>
<script src="swfobject.js" type="text/javascript"></script>
<script type="text/javascript" src="jquery.uploadify.js"></script>
<style type="text/css">
	.clear { /* generic container (i.e. div) for floating buttons */
		overflow: hidden;
		width: 100%;
	}
	a.button {
		background: transparent url('bg_button_a.gif') no-repeat scroll top right;
		color: #444;
		display: block;
		float: left;
		font: normal 12px arial, sans-serif;
		height: 24px;
		margin-right: 6px;
		padding-right: 18px; /* sliding doors padding */
		text-decoration: none;
	}
	a.button span
	{
		background: transparent url('bg_button_span.gif') no-repeat;
		display: block;
		line-height: 14px;
		padding: 5px 0 5px 18px;
	}
	a.button:active {
		background-position: bottom right;
		color: #000;
		outline: none; /* hide dotted outline in Firefox */
	}
	a.button:active span {
		background-position: bottom left;
		padding: 6px 0 4px 18px; /* push text down 1px */
	} 
</style>
</head>
<body>
<div style="width: 350px; height: 100px;overflow: auto;">
	<div style="margin: 0px;">
		<input TYPE="file" id="uploadModify" NAME="file"  multiple="true"/>
	</div>
	<div style="margin-left:145px;margin-top: -40px;">
		<a class="button" href="javascript:void(0);" onclick="uploadifyUpload()"><span>上传</span></a> 
		<a class="button" href="javascript:void(0);" onclick="jQuery('#uploadModify').uploadify('cancel', '*')"><span>取消</span></a> 
	</div>
	<div id="fileQueue" style="margin-top: 50px;"></div>
</div>
<input id="newPath" value="" type="hidden">
<input id="oldPath" value="" type="hidden">
<script type="text/javascript">
$(document).ready(function(){
    $("#uploadModify").uploadify({
        /*注意前面需要书写path的代码*/ 
        'swf'       	  : "uploadify.swf", 
        //'uploader'       : "${tikuPath}questions!importAnalyticQuestion.action",
        'cancelImg'      : "uploadify-cancel.png", 
        'queueID'        : 'fileQueue', //和存放队列的DIV的id一致 
        'fileObjName'   : 'file', //和以下input的name属性一致 
        'auto'           : false, //是否自动开始 
        'multi'          : false, //是否支持多文件上传 
        'buttonText'     : '浏览', //按钮上的文字 
        'simUploadLimit' : 1, //一次同步上传的文件数目 
        'fileSizeLimit'      : 5097152, //设置单个文件大小限制 
        'queueSizeLimit' : 1, //队列中同时存在的文件个数限制 
        'fileTypeDesc'       : '支持格式:Images', //如果配置了以下的'fileExt'属性，那么这个属性是必须的 
        'fileTypeExts'        : '*.jpg;*.png;*.gif',//允许的格式   
		 'method'   : 'post',//提交方式  
		 'removeTimeout':1,//上传完成后队列多长时间后消失 
     	onSelect: function(file){

       },
       'onUploadStart' : function(file) {
       
       },
       
       //返回一个错误，选择文件的时候触发
        'onSelectError':function(file, errorCode, errorMsg){
            switch(errorCode) {
                case -100:
                    alert("上传的文件数量已经超出系统限制的"+$('#uploadModify').uploadify('settings','queueSizeLimit')+"个文件！");
                    break;
                case -110:
                    alert("文件 ["+file.name+"] 大小超出系统限制的"+$('#uploadModify').uploadify('settings','fileSizeLimit')+"大小！");
                    break;
                case -120:
                    alert("文件 ["+file.name+"] 大小异常！");
                    break;
                case -130:
                    alert("文件 ["+file.name+"] 类型不正确！");
                    break;
            }
        },
       
       //检测FLASH失败调用
        'onFallback':function(){
            alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");
        },
       
       
        onUploadSuccess: function(file, data, response) { 
			var json = eval("(" + data + ")");
			if(json!=''){
				if(json.length!=0){
// 					alert(json[0].newName+","+json[0].oldName);
					parent.top.art.dialog.tips("上传成功，请保存！");
					$("#newPath").val(json[0].newName);
					$("#oldPath").val(json[0].oldName);
// 					if("${param.callback}" != ''){
// 						parent.${param.callback}(json[0].newName,json[0].oldName);
// 					}
				}
			}
		}, 
		onError: function(event, queueID, fileObj) { 
// 			alert(response.returnMessage);
        }, 
        onCancel: function(event, queueID, fileObj){ 
//        		alert(response.returnMessage);
      	} 
	}); 

});
//上传
function uploadifyUpload(){
	 var url = "upload.jsp?proVal=head_path&type=pic";

	 $('#uploadModify').uploadify('settings','uploader',url); 
 	 $('#uploadModify').uploadify('upload','*'); 
} 
</script>
</body>
</html>