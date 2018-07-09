<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>编辑HPersonType</h4>
</div>
<div class="modal-body">
    <form id="updateForm" class="well well-nice form-horizontal">
	    <input type="hidden" placeholder="id" value="${hpersontype.id}" id="mId" isNotNull="true" warnName="id" />
         <div class="control-group">
             <label class="control-label" for="mName">名称</label>
             <div class="controls">
			    <input type="text" placeholder="名称" value="${hpersontype.name}" id="mName" isNotNull="true" warnName="名称" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mState">状态</label>
             <div class="controls">
		    	 <select id="mState" isNotNull="true" warnName="状态"> 
                    <option <c:if test="${hpersontype.state == 1}" >selected="selected"</c:if>  value="1">正常</option> 
                    <option <c:if test="${hpersontype.state == 0}" >selected="selected"</c:if> value="0">暂停</option> 
            	</select> 
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mRemark">备注</label>
             <div class="controls">
			    <input type="text" placeholder="备注" value="${hpersontype.remark}" id="mRemark"/>
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
		var name = getVal("mName");
		var state = getVal("mState");
		var createTime = getVal("mCreateTime");
		var adminId = getVal("mAdminId");
		var lastTime = getVal("mLastTime");
		var lastAdminId = getVal("mLastAdminId");
		var remark = getVal("mRemark");
		var flag = validateForm('updateForm');
	    if (flag){ 
	        $.post("<c:url value='/hPersonType/updateHPersonType'/>",
	        	{
		    		id : id,
		    		name : name,
		    		state : state,
		    		createTime : createTime,
		    		adminId : adminId,
		    		lastTime : lastTime,
		    		lastAdminId : lastAdminId,
		    		remark : remark,
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