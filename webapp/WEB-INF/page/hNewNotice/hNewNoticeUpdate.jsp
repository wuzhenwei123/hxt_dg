<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>编辑对公网站公告</h4>
</div>
<div class="modal-body">
    <form id="updateForm" class="well well-nice form-horizontal">
	    <input type="hidden" value="${hnewnotice.id}" id="mId" isNotNull="true" warnName="id" />
         <div class="control-group">
             <label class="control-label" for="mTitle">标题</label>
             <div class="controls">
			    <input type="text" placeholder="标题" value="${hnewnotice.title}" id="mTitle" isNotNull="true" warnName="title" />
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
             <label class="control-label" for="mState">状态</label>
             <div class="controls">
		    	 <select id="mState" isNotNull="true" warnName="状态"> 
                    <option <c:if test="${hnewnotice.state == 1}" >selected="selected"</c:if>  value="1">正常</option> 
                    <option <c:if test="${hnewnotice.state == 0}" >selected="selected"</c:if> value="0">禁用</option> 
            	</select> 
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="sortId">排序</label>
             <div class="controls">
			    <input type="text" placeholder="排序" id="sortId" value="${hnewnotice.sortId}"/>
                 <span class="help-inline text-red">公告列表按此值从大到小排列</span>
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
	    ue.setContent(unescape('${hnewnotice.content}'));
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
		var flag = validateForm('updateForm');
	    if (flag&&ue.hasContents()){ 
	        $.post("<c:url value='/hNewNotice/updateHNewNotice'/>",
	        	{
		    		id : id,
		    		title : title,
		    		content : content,
		    		sortId:sortId,
		    		createTime : createTime,
		    		createId : createId,
		    		state : state,
		    		source : source,
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