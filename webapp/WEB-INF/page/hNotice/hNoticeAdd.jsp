<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>发布公共</h4>
</div>
<div class="modal-body">
    <form id="addForm" class="well well-nice form-horizontal">
         <div class="control-group">
             <label class="control-label" for="aTitle">标题</label>
             <div class="controls">
			    <input type="text" placeholder="标题" id="aTitle" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aContent">内容</label>
             <div class="controls">
			   <div id="editor" style="width:100%;height:200px;"></div>
			    <input type="hidden" placeholder="内容" id="aContent" isNotNull="true" warnName="内容" />
                <span class="help-inline text-red">*</span>
             </div>
         </div>
<!--          <div class="control-group"> -->
<!--              <label class="control-label" for="aCreateTime">createTime</label> -->
<!--              <div class="controls"> -->
<!-- 		     	<input type="text" placeholder="createTime" id="aCreateTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="createTime" /> -->
<!--                  <span class="help-inline text-red">*</span> -->
<!--              </div> -->
<!--          </div> -->
<!--          <div class="control-group"> -->
<!--              <label class="control-label" for="aCreateId">createId</label> -->
<!--              <div class="controls"> -->
<!-- 			    <input type="text" placeholder="createId" id="aCreateId" isNotNull="true" warnName="createId" /> -->
<!--                  <span class="help-inline text-red">*</span> -->
<!--              </div> -->
<!--          </div> -->
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
		var title = getVal("aTitle");
		var createTime = getVal("aCreateTime");
		var createId = getVal("aCreateId");
		var state = getVal("aState");
		var flag = validateForm('addForm');
	    if (flag){ 
	        $.post("<c:url value='/hNotice/addHNotice'/>",
	        	{
		    		id : id,
		    		content : content,
		    		title : title,
		    		createTime : createTime,
		    		createId : createId,
		    		state : state,
				 _t:Math.random()},
	        	function(data){
		        	var result = eval('('+data+')'); 
		            if (result.code == '1') {
		              	var pageNo = $("#currPage").val();           
		              	searchData(pageNo);
		              	tipOk("发布成功!");
		             } else {
		            	 tipError(result.message);
		             }
	              	$modal.modal('hide');
	        });
	    }
	}
</script>