<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
<link href="<c:url value='/plus/webuploader/webuploader.css'/>" rel="stylesheet">
<script type="text/javascript" src="<c:url value='/plus/webuploader/webuploader.js'/>"></script>  
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>添加轮播图</h4>
</div>
<div class="modal-body">
    <form id="addForm" class="well well-nice form-horizontal">
         <div class="control-group">
             <label class="control-label" for="aImg_name">图片名称</label>
             <div class="controls">
			    <input type="text" id="aImg_name" isNotNull="true" warnName="图片名称" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aImg_path">选择图片</label>
             <div class="controls">
             	<img src="" style="width: 300px;height: 150px;" id="showimg"/>
             	<div id="filePicker1">选择图片</div>
			    <input type="hidden" placeholder="img_path" id="aImg_path" isNotNull="true" warnName="图片" />
                <span class="help-inline text-red">建议图片尺寸1920*400</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aStatus">状态</label>
             <div class="controls">
			    <select id="aStatus" isNotNull="true" warnName="status">
			    	<option value="1" selected="selected">正常</option>
			    	<option value="0">冻结</option>
			    </select>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
     </form>
 </div>
 <div class="modal-footer">
     <button type="button" data-dismiss="modal" class="btn btn-boo">取消</button>
     <button type="submit" class="btn btn-green" onclick="add()">保存</button>
 </div>
<script type="text/javascript">
	//执行添加
	function add(){
		var img_name = getVal("aImg_name");
		var img_path = getVal("aImg_path");
		var status = getVal("aStatus");
		var flag = validateForm('addForm');
	    if (flag){ 
	        $.post("<c:url value='/hLbImg/addHLbImg'/>",
	        	{
		    		img_name : img_name,
		    		img_path : img_path,
		    		status : status,
				 _t:Math.random()},
	        	function(data){
		        	var result = eval('('+data+')'); 
		            if (result.code == '1') {
		              	var pageNo = $("#currPage").val();           
		              	searchData(pageNo);
		              	tipOk("保存成功!");
		             } else {
		            	 tipError(result.message);
		             }
	              	$modal.modal('hide');
	        });
	    }
	}
	$(function(){
		var uploader;
		setTimeout(function() {
			console.log('初始化...')
			uploader = WebUploader.create({
			    auto: true,
			    swf: '${ctx}/plus/webuploader/Uploader.swf',
			    server: '${ctx}/hLbImg/uploadPic',
			   	pick: {
			    	id:'#filePicker1',
			    	multiple : false//是否开起同时选择多个文件能力
			    },
			    compress:false,
			    resize: false,
			    accept: {
			        title: 'Images',
			        extensions: 'gif,jpg,jpeg,bmp,png',
			        mimeTypes: 'image/*'
			    },
			    formData:{ // 参数
			    }
			});
			uploader.on( 'uploadSuccess', function( file,response ) {
				uploader.reset();
				var servername = response.serverfileName;
				$("#showimg").attr("src","${ctx}"+servername);
				$("#aImg_path").val(servername);
			});
			uploader.on( 'uploadError', function( file ) {
			    alert("上传失败!");
			});
		}, 200);
	});
</script>