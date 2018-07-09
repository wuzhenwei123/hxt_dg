<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
<link href="<c:url value='/plus/webuploader/webuploader.css'/>" rel="stylesheet">
<script type="text/javascript" src="<c:url value='/plus/webuploader/webuploader.js'/>"></script>  
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>编辑HNewNews</h4>
</div>
<div class="modal-body">
    <form id="updateForm" class="well well-nice form-horizontal">
	    <input type="hidden" value="${hnewnews.id}" id="mId" isNotNull="true" warnName="id" />
         <div class="control-group">
             <label class="control-label" for="mTitle">标题</label>
             <div class="controls">
			    <input type="text" placeholder="标题" value="${hnewnews.title}" id="mTitle" isNotNull="true" warnName="title" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mContent">内容</label>
             <div class="controls">
             	<script id="editor" type="text/plain" style="width:100%;height:300px;"></script>
			    <input type="hidden" placeholder="content" value="" id="mContent" isNotNull="true" warnName="内容" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
           <div class="control-group">
             <label class="control-label" for="mImg_path">封面图片</label>
             <div class="controls">
                <img src="${ctx}${hnewnews.imgUrl}" style="width: 300px;height: 150px;" id="showimg"/>
             	<div id="filePicker1">选择图片</div>
			    <input type="hidden" id="mImg_path" isNotNull="true" warnName="图片" value="${hnewnews.imgUrl}"/>
            </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mState">state</label>
             <div class="controls">
		    	 <select id="mState" isNotNull="true" warnName="状态"> 
                    <option <c:if test="${hnewnews.state == 1}" >selected="selected"</c:if>  value="1">正常</option> 
                    <option <c:if test="${hnewnews.state == 0}" >selected="selected"</c:if> value="0">禁用</option> 
            	</select> 
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="sortId">排序</label>
             <div class="controls">
			    <input type="text" placeholder="排序" id="sortId" value="${hnewnews.sortId}"/>
                <span class="help-inline">新闻列表按此值从大到小排列</span>
             </div>
         </div>
     </form>
 </div>
 <div class="modal-footer">
     <button type="button" data-dismiss="modal" class="btn btn-boo">取消</button>
     <button type="submit" class="btn btn-green" onclick="update()">更新</button>
 </div>
<script type="text/javascript">
var ue ;
$(document).ready(function(){
	if(ue){
		ue.destroy();
	}
	ue = UE.getEditor('editor');
	ue.ready(function() {
	    ue.setContent(unescape('${hnewnews.content}'));
	});
});
	// 执行更新
	function update(){
		if(ue.hasContents()){
			$("#mContent").val(123);
		}else{
			$("#mContent").val("");
		}
		var id = getVal("mId");
		var title = getVal("mTitle");
		var content = escape(ue.getContent());
		var createTime = getVal("mCreateTime");
		var createId = getVal("mCreateId");
		var state = getVal("mState");
		var source = getVal("mSource");
		var sortId = getVal("sortId");
		var imgUrl = getVal("mImg_path");
		var flag = validateForm('updateForm');
	    if (flag&&ue.hasContents()){ 
	        $.post("<c:url value='/hNewNews/updateHNewNews'/>",
	        	{
		    		id : id,
		    		sortId:sortId,
		    		title : title,
		    		content : content,
		    		createTime : createTime,
		    		createId : createId,
		    		state : state,
		    		source : source,
		    		imgUrl : imgUrl,
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