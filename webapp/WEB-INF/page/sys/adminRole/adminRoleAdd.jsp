<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>添加用户</h4>
</div>
<div class="modal-body">
   <form id="addForm" class="well well-nice form-horizontal">
        <div class="control-group">
            <label class="control-label" for="aRoleName">角色名称</label>
            <div class="controls">
                <input type="text" id="aRoleName" placeholder="角色名称" isNotNull="true" warnName="角色名称">
                <span class="help-inline text-red">*</span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="mState">状态</label>
            <div class="controls">
                <select id="aState" isNotNull="true" warnName="状态"> 
                    <option value="1">正常</option> 
                    <option value="0">禁用</option> 
              	</select> 
                <span class="help-inline text-red">*</span>
            </div>
        </div>
        <div class="control-group"> 
            <label class="control-label" for="aRoleId">默认角色</label>
            <div class="controls">
                <select id="aDefaule" isNotNull="true" warnName="默认角色"> 
                    <option value="1">默认</option> 
                    <option value="0">非默认</option> 
               	</select> 
              	<span class="help-inline text-red">*</span>
            </div>
        </div>
        <div class="control-group"> 
            <label class="control-label" for="aRoleId">角色类型</label>
            <div class="controls">
                <select id="aRoleType" isNotNull="true" warnName="角色类型"> 
               		<option value="0">普通管理</option> 
                    <option value="1">超级管理</option> 
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
	//执行添加
	function add(){
		var roleId = getVal("aRoleId");
		var roleName = getVal("aRoleName");
		var createTime = getVal("aCreateTime");
		var state = getVal("aState");
		var defaule = getVal("aDefaule");
		var roleType = getVal("aRoleType");
		var flag = validateForm('addForm');
	    if (flag){ 
	        $.post("<c:url value='/adminRole/addAdminRole'/>",
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
		            	tipOk('保存成功');
		             } else {
		            	 tipError(result.message);
		             }
		            $modal.modal('hide');
	        });
	    }
	}
</script>
