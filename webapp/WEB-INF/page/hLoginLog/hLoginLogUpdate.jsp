<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>编辑HLoginLog</h4>
</div>
<div class="modal-body">
    <form id="updateForm" class="well well-nice form-horizontal">
         <div class="control-group">
             <label class="control-label" for="mId">id</label>
             <div class="controls">
			    <input type="text" placeholder="id" value="${hloginlog.id}" id="mId" isNotNull="true" warnName="id" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mAdminName">adminName</label>
             <div class="controls">
			    <input type="text" placeholder="adminName" value="${hloginlog.adminName}" id="mAdminName" isNotNull="true" warnName="adminName" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mLoginTIme">loginTIme</label>
             <div class="controls">
		     	<input type="text" placeholder="loginTIme" value="<fmt:formatDate value="${hloginlog.loginTIme}" type="both"/>" id="mLoginTIme" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="loginTIme" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mLoginIp">loginIp</label>
             <div class="controls">
			    <input type="text" placeholder="loginIp" value="${hloginlog.loginIp}" id="mLoginIp" isNotNull="true" warnName="loginIp" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mDeviceType">deviceType</label>
             <div class="controls">
			    <input type="text" placeholder="deviceType" value="${hloginlog.deviceType}" id="mDeviceType" isNotNull="true" warnName="deviceType" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mRemark1">remark1</label>
             <div class="controls">
			    <input type="text" placeholder="remark1" value="${hloginlog.remark1}" id="mRemark1" isNotNull="true" warnName="remark1" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mRemark2">remark2</label>
             <div class="controls">
			    <input type="text" placeholder="remark2" value="${hloginlog.remark2}" id="mRemark2" isNotNull="true" warnName="remark2" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mRemark3">remark3</label>
             <div class="controls">
			    <input type="text" placeholder="remark3" value="${hloginlog.remark3}" id="mRemark3" isNotNull="true" warnName="remark3" />
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
		var adminName = getVal("mAdminName");
		var loginTIme = getVal("mLoginTIme");
		var loginIp = getVal("mLoginIp");
		var deviceType = getVal("mDeviceType");
		var remark1 = getVal("mRemark1");
		var remark2 = getVal("mRemark2");
		var remark3 = getVal("mRemark3");
		var flag = validateForm('updateForm');
	    if (flag){ 
	        $.post("<c:url value='/hLoginLog/updateHLoginLog'/>",
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
		              	tipOk("更新成功!");
		             } else {
		            	 tipError(result.message);
		             }
		            $modal.modal('hide');
	        });
	    }
	}
</script>