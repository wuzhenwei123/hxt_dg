<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>添加类型</h4>
</div>
<div class="modal-body">
    <form id="addForm" class="well well-nice form-horizontal">
         <div class="control-group">
             <label class="control-label" for="aName">名称</label>
             <div class="controls">
			    <input type="text" placeholder="名称" id="aName" isNotNull="true" warnName="名称" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aState">状态</label>
             <div class="controls">
		    	<select id="aState"> 
                    <option value="1">正常</option> 
                    <option value="0">暂停</option> 
            	</select> 
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aRemark">备注</label>
             <div class="controls">
			    <input type="text" placeholder="备注" id="aRemark" />
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
		var id = getVal("aId");
		var name = getVal("aName");
		var state = getVal("aState");
		var createTime = getVal("aCreateTime");
		var adminId = getVal("aAdminId");
		var lastTime = getVal("aLastTime");
		var lastAdminId = getVal("aLastAdminId");
		var remark = getVal("aRemark");
		var flag = validateForm('addForm');
	    if (flag){ 
	        $.post("<c:url value='/hPersonType/addHPersonType'/>",
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
		              	tipOk("保存成功!");
		             } else {
		            	 tipError(result.message);
		             }
	              	$modal.modal('hide');
	        });
	    }
	}
</script>