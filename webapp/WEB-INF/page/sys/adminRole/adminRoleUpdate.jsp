<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>编辑用户</h4>
</div>
<div class="modal-body">
   <form id="updateForm" class="well well-nice form-horizontal">
        <div class="control-group">
            <label class="control-label" for="mRoleId">角色ID</label>
            <div class="controls">
                <input type="text" readonly="readonly" id="mRoleId" value="${adminrole.roleId}" placeholder="角色ID" isNotNull="true" warnName="角色ID">
                <span class="help-inline text-red">*</span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="mRoleName">角色名称</label>
            <div class="controls">
                <input type="text" id="mRoleName" value="${adminrole.roleName}" placeholder="角色名称" isNotNull="true" warnName="角色名称">
                <span class="help-inline text-red">*</span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="mState">状态</label>
            <div class="controls">
                <select id="mState" isNotNull="true" warnName="状态"> 
                	<option <c:if test="${adminrole.state == 1}" >selected="selected"</c:if>  value="1">正常</option> 
                   <option <c:if test="${adminrole.state == 0}" >selected="selected"</c:if> value="0">禁用</option> 
              	 </select> 
                <span class="help-inline text-red">*</span>
            </div>
        </div>
        <div class="control-group"> 
            <label class="control-label" for="mRoleId">默认角色</label>
            <div class="controls">
                <select id="mDefaule" isNotNull="true" warnName="角色类型"> 
                     <option <c:if test="${adminrole.defaule == 1}" >selected="selected"</c:if> value="1">默认角色</option> 
                     <option <c:if test="${adminrole.defaule == 0}" >selected="selected"</c:if> value="0">非默认角色</option> 
               	</select> 
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="mCreateTime">创建日期</label>
            <div class="controls">
                <input type="text" id="mCreateTime" placeholder="创建日期" readonly="readonly" value="<fmt:formatDate value="${adminrole.createTime}" type="both"/>" />
            </div>
        </div>
    </form>
</div>
<div class="modal-footer">
    <button type="button" data-dismiss="modal" class="btn btn-boo">取消</button>
    <button type="submit" class="btn btn-green" onclick="update()">更新</button>
</div>
<script type="text/javascript">
	//执行更新
	function update(){
		var roleId = getVal("mRoleId");
		var roleName = getVal("mRoleName");
		var createTime = getVal("mCreateTime");
		var state = getVal("mState");
		var defaule = getVal("mDefaule");
		var roleType = getVal("mRoleType");
		var flag = validateForm('updateForm');
	    if (flag){ 
	        $.post("<c:url value='/adminRole/updateAdminRole'/>",
	        	{
	    		roleId : roleId,
	    		roleName : roleName,
	    		createTime : createTime,
	    		state : state,
	    		defaule : defaule,
	    		roleType : roleType,
				 _t:Math.random()},
	        	function(data){
		        	var result = eval('('+data+')'); 
		            if (result.code == '1') {
		              	var pageNo = $("#currPage").val();           
		              	searchData(pageNo);
		              	tipOk("更新成功");
		             } else {
		            	 tipError(result.message);
		             }
		            $modal.modal('hide');
	        });
	    }
	}
</script>
