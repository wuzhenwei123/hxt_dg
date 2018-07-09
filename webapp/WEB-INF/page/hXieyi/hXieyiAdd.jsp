<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>添加协议</h4>
</div>
<div class="modal-body">
    <form id="addForm" class="well well-nice form-horizontal">
         <div class="control-group">
             <label class="control-label" for="aContent">内容</label>
             <div class="controls">
             	<script id="editor" type="text/plain" style="width:100%;height:300px;"></script>
			    <input type="hidden" placeholder="内容" id="aContent" isNotNull="true" warnName="内容" />
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
	var ue ;
	$(document).ready(function(){
		if(ue){
			ue.destroy();
		}
		ue = UE.getEditor('editor');
	});
	//执行添加
	function add(){
		$("#aContent").val(ue.getContent());
		var id = getVal("aId");
		var content = getVal("aContent");
		var createTime = getVal("aCreateTime");
		var createId = getVal("aCreateId");
		var flag = validateForm('addForm');
	    if (flag){ 
	        $.post("<c:url value='/hXieyi/addHXieyi'/>",
	        	{
		    		id : id,
		    		content : content,
		    		createTime : createTime,
		    		createId : createId,
				 _t:Math.random()},
	        	function(data){
		        	var result = eval('('+data+')'); 
		            if (result.code == '1') {
		              	var pageNo = $("#currPage").val();           
		              	searchData(pageNo);
		              	tipOk("保存成功!");
		              	$("#addBtn").hide();
		             } else {
		            	 tipError(result.message);
		             }
	              	$modal.modal('hide');
	        });
	    }
	}
</script>