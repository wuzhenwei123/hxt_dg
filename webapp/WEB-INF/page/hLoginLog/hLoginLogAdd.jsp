<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>添加HLoginLog</h4>
</div>
<div class="modal-body">
    <form id="addForm" class="well well-nice form-horizontal">
         <div class="control-group">
             <label class="control-label" for="aId">id</label>
             <div class="controls">
			    <input type="text" placeholder="id" id="aId" isNotNull="true" warnName="id" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aAdminName">adminName</label>
             <div class="controls">
			    <input type="text" placeholder="adminName" id="aAdminName" isNotNull="true" warnName="adminName" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aLoginTIme">loginTIme</label>
             <div class="controls">
		     	<input type="text" placeholder="loginTIme" id="aLoginTIme" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="loginTIme" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aLoginIp">loginIp</label>
             <div class="controls">
			    <input type="text" placeholder="loginIp" id="aLoginIp" isNotNull="true" warnName="loginIp" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aDeviceType">deviceType</label>
             <div class="controls">
			    <input type="text" placeholder="deviceType" id="aDeviceType" isNotNull="true" warnName="deviceType" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aRemark1">remark1</label>
             <div class="controls">
			    <input type="text" placeholder="remark1" id="aRemark1" isNotNull="true" warnName="remark1" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aRemark2">remark2</label>
             <div class="controls">
			    <input type="text" placeholder="remark2" id="aRemark2" isNotNull="true" warnName="remark2" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aRemark3">remark3</label>
             <div class="controls">
			    <input type="text" placeholder="remark3" id="aRemark3" isNotNull="true" warnName="remark3" />
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
		var id = getVal("aId");
		var adminName = getVal("aAdminName");
		var loginTIme = getVal("aLoginTIme");
		var loginIp = getVal("aLoginIp");
		var deviceType = getVal("aDeviceType");
		var remark1 = getVal("aRemark1");
		var remark2 = getVal("aRemark2");
		var remark3 = getVal("aRemark3");
		var flag = validateForm('addForm');
	    if (flag){ 
	        $.post("<c:url value='/hLoginLog/addHLoginLog'/>",
	        	{
		    		id : id,
		    		adminName : adminName,
		    		loginTIme : loginTIme,
		    		loginIp : loginIp,
		    		deviceType : deviceType,
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