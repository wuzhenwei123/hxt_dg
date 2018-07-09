<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<link href="<c:url value='/plus/webuploader/webuploader.css'/>" rel="stylesheet">
<script type="text/javascript" src="<c:url value='/plus/webuploader/webuploader.js'/>"></script>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>添加</h4>
</div>
<div class="modal-body">
    <form id="addForm" class="well well-nice form-horizontal">
         <div class="control-group">
             <label class="control-label" for="aName">银行名称</label>
             <div class="controls">
			    <input type="text" id="aName" isNotNull="true" warnName="name" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aState">状态</label>
             <div class="controls">
		    	<select id="aState"> 
                    <option value="1">正常</option> 
                    <option value="0">禁用</option> 
            	</select> 
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aBigImg">大图标</label>
             <div class="controls">
			    <input type="text" id="aBigImg" isNotNull="true" warnName="大图标" readonly="readonly"/>
                <div id="filePicker1">选择图标</div>
                <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aSmallImg">小图标</label>
             <div class="controls">
			    <input type="text" id="aSmallImg" isNotNull="true" warnName="小图标" readonly="readonly"/>
                <div id="filePicker2">选择图标</div>
                <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aDocUrl">操作手册</label>
             <div class="controls">
			    <input type="text" id="aDocUrl" isNotNull="true" warnName="操作手册" readonly="readonly"/>
                <div id="filePicker3">选择文档</div>
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
$(function(){
	var uploader1;
	var uploader2;
	var uploader3;
	setTimeout(function() {
		console.log('初始化...')
		// 初始化Web Uploader
		uploader1 = WebUploader.create({
		
		    // 选完文件后，是否自动上传。
		    auto: true,
		
		    // swf文件路径
		    swf: '${ctx}/plus/webuploader/Uploader.swf',
		
		    // 文件接收服务端。
		    server: '${ctx}/hAgent/uploadPic',
		
		    // 选择文件的按钮。可选。
		    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
		    //pick: '#filePicker',
		   	pick: {
		    	id:'#filePicker1',
		    	multiple : false//是否开起同时选择多个文件能力
		    },
			
			// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
		    resize: false,
			
		    // 只允许选择图片文件。
		    accept: {
		        title: 'Images',
		        extensions: 'gif,jpg,jpeg,bmp,png',
		        mimeTypes: 'image/*'
		    },
		    
		    formData:{ // 参数
		    }
		});
	
		/* uploader.on('uploadError', function(file, reason){
          alert(reason);
        }); */
	
		// 文件上传成功，给item添加成功class, 用样式标记上传成功。
		uploader1.on( 'uploadSuccess', function( file,response ) {
			uploader1.reset();
			
			var servername = response.serverfileName;
			
			$("#aBigImg").val(servername);
		});
		
		// 文件上传失败，显示上传出错。
		uploader1.on( 'uploadError', function( file ) {
		    alert("上传失败!");
		});
		/////////////////////////////////////////////////////////////
		// 初始化Web Uploader
		uploader2 = WebUploader.create({
		
		    // 选完文件后，是否自动上传。
		    auto: true,
		
		    // swf文件路径
		    swf: '${ctx}/plus/webuploader/Uploader.swf',
		
		    // 文件接收服务端。
		    server: '${ctx}/hAgent/uploadPic',
		
		    // 选择文件的按钮。可选。
		    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
		    //pick: '#filePicker',
		   	pick: {
		    	id:'#filePicker2',
		    	multiple : false//是否开起同时选择多个文件能力
		    },
			
			// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
		    resize: false,
			
		    // 只允许选择图片文件。
		    accept: {
		        title: 'Images',
		        extensions: 'gif,jpg,jpeg,bmp,png',
		        mimeTypes: 'image/*'
		    },
		    
		    formData:{ // 参数
		    }
		});
	
		/* uploader2.on('uploadError', function(file, reason){
          alert(reason);
        }); */
	
		// 文件上传成功，给item添加成功class, 用样式标记上传成功。
		uploader2.on( 'uploadSuccess', function( file,response ) {
			uploader2.reset();
			
			var servername = response.serverfileName;
			
			$("#aSmallImg").val(servername);
		});
		
		// 文件上传失败，显示上传出错。
		uploader2.on( 'uploadError', function( file ) {
		    alert("上传失败!");
		});
		
		/////////////////////////////////////////////////////////////
		// 初始化Web Uploader
		uploader3 = WebUploader.create({
		
		    // 选完文件后，是否自动上传。
		    auto: true,
		
		    // swf文件路径
		    swf: '${ctx}/plus/webuploader/Uploader.swf',
		
		    // 文件接收服务端。
		    server: '${ctx}/hAgent/uploadPic',
		
		    // 选择文件的按钮。可选。
		    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
		    //pick: '#filePicker',
		   	pick: {
		    	id:'#filePicker3',
		    	multiple : false//是否开起同时选择多个文件能力
		    },
			
			// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
		    resize: false,
			
		    formData:{ // 参数
		    }
		});

		/* uploader2.on('uploadError', function(file, reason){
	      alert(reason);
	    }); */

		// 文件上传成功，给item添加成功class, 用样式标记上传成功。
		uploader3.on( 'uploadSuccess', function( file,response ) {
			uploader3.reset();
			
			var servername = response.serverfileName;
			
			$("#aDocUrl").val(servername);
		});
		
		// 文件上传失败，显示上传出错。
		uploader3.on( 'uploadError', function( file ) {
		    alert("上传失败!");
		});
	}, 200);
	
});
	//执行添加
	function add(){
		var id = getVal("aId");
		var name = getVal("aName");
		var state = getVal("aState");
		var bigImg = getVal("aBigImg");
		var smallImg = getVal("aSmallImg");
		var docUrl = getVal("aDocUrl");
		var remark1 = getVal("aRemark1");
		var remark2 = getVal("aRemark2");
		var remark3 = getVal("aRemark3");
		var flag = validateForm('addForm');
	    if (flag){ 
	        $.post("<c:url value='/hBank/addHBank'/>",
	        	{
		    		id : id,
		    		name : name,
		    		state : state,
		    		bigImg : bigImg,
		    		smallImg : smallImg,
		    		docUrl : docUrl,
		    		remark1 : remark1,
		    		remark2 : remark2,
		    		remark3 : remark3,
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
</script>