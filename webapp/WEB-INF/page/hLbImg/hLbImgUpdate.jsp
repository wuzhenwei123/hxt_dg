<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
<link href="<c:url value='/plus/webuploader/webuploader.css'/>" rel="stylesheet">
<script type="text/javascript" src="<c:url value='/plus/webuploader/webuploader.js'/>"></script>  
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>编辑HLbImg</h4>
</div>
<div class="modal-body">
    <form id="updateForm" class="well well-nice form-horizontal">
         <input type="hidden" placeholder="id" value="${hlbimg.id}" id="mId" isNotNull="true" warnName="id" />
         <div class="control-group">
             <label class="control-label" for="mImg_name">图片名称</label>
             <div class="controls">
			    <input type="text" value="${hlbimg.img_name}" id="mImg_name" isNotNull="true" warnName="img_name" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mImg_path">选择图片</label>
             <div class="controls">
                <img src="${ctx}${hlbimg.img_path}" style="width: 300px;height: 150px;" id="showimg"/>
             	<div id="filePicker1">选择图片</div>
			    <input type="hidden" id="mImg_path" isNotNull="true" warnName="图片" value="${hlbimg.img_path}"/>
                <span class="help-inline text-red">建议图片尺寸1920*400</span>
             </div>
         </div>
        
         <div class="control-group">
             <label class="control-label" for="mStatus">状态</label>
             <div class="controls">
			     <select id="mStatus" isNotNull="true" warnName="状态">
			    	<option value="1" <c:if test="${hlbimg.status==1}">selected="selected"</c:if>>正常</option>
			    	<option value="0" <c:if test="${hlbimg.status==0}">selected="selected"</c:if>>冻结</option>
			    </select>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         
     </form>
 </div>
 <div class="modal-footer">
     <button type="button" data-dismiss="modal" class="btn btn-boo">取消</button>
     <button type="submit" class="btn btn-green" onclick="update()">更新</button>
 </div>
<script type="text/javascript">
	// 执行更新
	function update(){
		var id = getVal("mId");
		var img_name = getVal("mImg_name");
		var img_path = getVal("mImg_path");
		var link_address = getVal("mLink_address");
		var add_user_id = getVal("mAdd_user_id");
		var add_time = getVal("mAdd_time");
		var status = getVal("mStatus");
		var sort_id = getVal("mSort_id");
		var flag = validateForm('updateForm');
	    if (flag){ 
	        $.post("<c:url value='/hLbImg/updateHLbImg'/>",
	        	{
		    		id : id,
		    		img_name : img_name,
		    		img_path : img_path,
		    		link_address : link_address,
		    		add_user_id : add_user_id,
		    		add_time : add_time,
		    		status : status,
		    		sort_id : sort_id,
				 _t:Math.random()},
	        	function(data){
		        	var result = eval('('+data+')'); 
		            if (result.code == '1') {
		              	var pageNo = $("#currPage").val();           
		              	searchData(pageNo);
		              	tipOk("更新成功!");
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
				$("#mImg_path").val(servername);
			});
			uploader.on( 'uploadError', function( file ) {
			    alert("上传失败!");
			});
		}, 200);
	});
</script>