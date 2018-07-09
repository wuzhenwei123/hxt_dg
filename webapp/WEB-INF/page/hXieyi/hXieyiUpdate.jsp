<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>编辑协议</h4>
</div>
<div class="modal-body">
   <input type="hidden" placeholder="id" value="${hxieyi.id}" id="mId" isNotNull="true" warnName="id" />
    <form id="updateForm" class="well well-nice form-horizontal">
         <div class="control-group">
             <label class="control-label" for="mContent">内容</label>
             <div class="controls">
             	<div id="editor" style="width:100%;height:300px;"></div>
			    <input type="hidden" placeholder="content" value="${hxieyi.content}" id="mContent" isNotNull="true" warnName="内容" />
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
	var ue ;
	$(document).ready(function(){
		if(ue){
			ue.destroy();
		}
		ue = UE.getEditor('editor');
		ue.ready(function() {
		    ue.setContent('${hxieyi.content}');
		});
	});
	// 执行更新
	function update(){
		var id = getVal("mId");
		if(ue.hasContents()){
			$("#mContent").val("aaaa");
		}else{
			$("#mContent").val("");
		}
		var content = ue.getContent();
		var flag = validateForm('updateForm');
	    if (flag){ 
	        $.post("<c:url value='/hXieyi/updateHXieyi'/>",
	        	{
		    		id : id,
		    		content : content,
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
</script>