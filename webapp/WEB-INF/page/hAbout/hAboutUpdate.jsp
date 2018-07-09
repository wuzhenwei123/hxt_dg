<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>编辑内容</h4>
</div>
<div class="modal-body">
	 <input type="hidden" readonly="readonly" placeholder="id" value="${habout.id}" id="mId" isNotNull="true" warnName="id" />
    <form id="updateForm" class="well well-nice form-horizontal">
<!--                   <div class="control-group"> -->
<!--              <label class="control-label" for="mId">ID</label> -->
<!--              <div class="controls"> -->
<%-- 			    <input type="text" readonly="readonly" placeholder="id" value="${habout.id}" id="mId" isNotNull="true" warnName="id" /> --%>
<!--                  <span class="help-inline text-red">*</span> -->
<!--              </div> -->
<!--          </div> -->
         <div class="control-group">
             <label class="control-label" for="mContent">内容</label>
             <div class="controls">
             	<script id="editor" type="text/plain" style="width:100%;height:300px;"></script>
			    <input type="hidden" placeholder="内容" value="" id="mContent" isNotNull="true" warnName="内容" />
                <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mState">状态</label>
             <div class="controls">
		    	 <select id="mState" isNotNull="true" warnName="状态"> 
                    <option <c:if test="${habout.state == 1}" >selected="selected"</c:if>  value="1">正常</option> 
                    <option <c:if test="${habout.state == 0}" >selected="selected"</c:if> value="0">禁用</option> 
            	</select> 
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
<!--          <div class="control-group"> -->
<!--              <label class="control-label" for="mCreateTime">创建日期</label> -->
<!--              <div class="controls"> -->
<%-- 		     	<input type="text" placeholder="createTime" value="<fmt:formatDate value="${habout.createTime}" type="both"/>" id="mCreateTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="createTime" /> --%>
<!--                  <span class="help-inline text-red">*</span> -->
<!--              </div> -->
<!--          </div> -->
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
		    ue.setContent('${habout.content}');
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
		var state = getVal("mState");
		var createTime = getVal("mCreateTime");
		var flag = validateForm('updateForm');
	    if (flag){ 
	        $.post("<c:url value='/hAbout/updateHAbout'/>",
	        	{
		    		id : id,
		    		content : content,
		    		state : state,
		    		createTime : createTime,
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