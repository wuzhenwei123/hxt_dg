<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>编辑HDispatchRecordC</h4>
</div>
<div class="modal-body">
    <form id="updateForm" class="well well-nice form-horizontal">
         <div class="control-group">
             <label class="control-label" for="mId">id</label>
             <div class="controls">
			    <input type="text" placeholder="id" value="${hdispatchrecordc.id}" id="mId" isNotNull="true" warnName="id" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mContact_phone">contact_phone</label>
             <div class="controls">
			    <input type="text" placeholder="contact_phone" value="${hdispatchrecordc.contact_phone}" id="mContact_phone" isNotNull="true" warnName="contact_phone" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mAmmeter_no">ammeter_no</label>
             <div class="controls">
			    <input type="text" placeholder="ammeter_no" value="${hdispatchrecordc.ammeter_no}" id="mAmmeter_no" isNotNull="true" warnName="ammeter_no" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mTotalFee">totalFee</label>
             <div class="controls">
			    <input type="text" placeholder="totalFee" value="${hdispatchrecordc.totalFee}" id="mTotalFee" isNotNull="true" warnName="totalFee" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mStatus">status</label>
             <div class="controls">
			    <input type="text" placeholder="status" value="${hdispatchrecordc.status}" id="mStatus" isNotNull="true" warnName="status" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mCreateTime">createTime</label>
             <div class="controls">
		     	<input type="text" placeholder="createTime" value="<fmt:formatDate value="${hdispatchrecordc.createTime}" type="both"/>" id="mCreateTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="createTime" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mUpdateTime">updateTime</label>
             <div class="controls">
		     	<input type="text" placeholder="updateTime" value="<fmt:formatDate value="${hdispatchrecordc.updateTime}" type="both"/>" id="mUpdateTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="updateTime" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mContent">content</label>
             <div class="controls">
			    <input type="text" placeholder="content" value="${hdispatchrecordc.content}" id="mContent" isNotNull="true" warnName="content" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mRemark1">remark1</label>
             <div class="controls">
			    <input type="text" placeholder="remark1" value="${hdispatchrecordc.remark1}" id="mRemark1" isNotNull="true" warnName="remark1" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mRemark2">remark2</label>
             <div class="controls">
			    <input type="text" placeholder="remark2" value="${hdispatchrecordc.remark2}" id="mRemark2" isNotNull="true" warnName="remark2" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mRemark3">remark3</label>
             <div class="controls">
			    <input type="text" placeholder="remark3" value="${hdispatchrecordc.remark3}" id="mRemark3" isNotNull="true" warnName="remark3" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mC_id">c_id</label>
             <div class="controls">
			    <input type="text" placeholder="c_id" value="${hdispatchrecordc.c_id}" id="mC_id" isNotNull="true" warnName="c_id" />
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
		var contact_phone = getVal("mContact_phone");
		var ammeter_no = getVal("mAmmeter_no");
		var totalFee = getVal("mTotalFee");
		var status = getVal("mStatus");
		var createTime = getVal("mCreateTime");
		var updateTime = getVal("mUpdateTime");
		var content = getVal("mContent");
		var remark1 = getVal("mRemark1");
		var remark2 = getVal("mRemark2");
		var remark3 = getVal("mRemark3");
		var c_id = getVal("mC_id");
		var flag = validateForm('updateForm');
	    if (flag){ 
	        $.post("<c:url value='/hDispatchRecordC/updateHDispatchRecordC'/>",
	        	{
		    		id : id,
		    		contact_phone : contact_phone,
		    		ammeter_no : ammeter_no,
		    		totalFee : totalFee,
		    		status : status,
		    		createTime : createTime,
		    		updateTime : updateTime,
		    		content : content,
		    		remark1 : remark1,
		    		remark2 : remark2,
		    		remark3 : remark3,
		    		c_id : c_id,
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