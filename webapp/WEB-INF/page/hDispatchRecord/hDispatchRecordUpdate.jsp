<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>编辑HDispatchRecord</h4>
</div>
<div class="modal-body">
    <form id="updateForm" class="well well-nice form-horizontal">
         <div class="control-group">
             <label class="control-label" for="mId">id</label>
             <div class="controls">
			    <input type="text" placeholder="id" value="${hdispatchrecord.id}" id="mId" isNotNull="true" warnName="id" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mAmmeter_no">ammeter_no</label>
             <div class="controls">
			    <input type="text" placeholder="ammeter_no" value="${hdispatchrecord.ammeter_no}" id="mAmmeter_no" isNotNull="true" warnName="ammeter_no" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mTotalFee">totalFee</label>
             <div class="controls">
			    <input type="text" placeholder="totalFee" value="${hdispatchrecord.totalFee}" id="mTotalFee" isNotNull="true" warnName="totalFee" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mAccountName">accountName</label>
             <div class="controls">
			    <input type="text" placeholder="accountName" value="${hdispatchrecord.accountName}" id="mAccountName" isNotNull="true" warnName="accountName" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mAddress">address</label>
             <div class="controls">
			    <input type="text" placeholder="address" value="${hdispatchrecord.address}" id="mAddress" isNotNull="true" warnName="address" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mStatus">status</label>
             <div class="controls">
			    <input type="text" placeholder="status" value="${hdispatchrecord.status}" id="mStatus" isNotNull="true" warnName="status" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mCreateTime">createTime</label>
             <div class="controls">
		     	<input type="text" placeholder="createTime" value="<fmt:formatDate value="${hdispatchrecord.createTime}" type="both"/>" id="mCreateTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="createTime" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mUpdateTime">updateTime</label>
             <div class="controls">
		     	<input type="text" placeholder="updateTime" value="<fmt:formatDate value="${hdispatchrecord.updateTime}" type="both"/>" id="mUpdateTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="updateTime" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mRemark1">remark1</label>
             <div class="controls">
			    <input type="text" placeholder="remark1" value="${hdispatchrecord.remark1}" id="mRemark1" isNotNull="true" warnName="remark1" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mRemark2">remark2</label>
             <div class="controls">
			    <input type="text" placeholder="remark2" value="${hdispatchrecord.remark2}" id="mRemark2" isNotNull="true" warnName="remark2" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mRemark3">remark3</label>
             <div class="controls">
			    <input type="text" placeholder="remark3" value="${hdispatchrecord.remark3}" id="mRemark3" isNotNull="true" warnName="remark3" />
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
		var ammeter_no = getVal("mAmmeter_no");
		var totalFee = getVal("mTotalFee");
		var accountName = getVal("mAccountName");
		var address = getVal("mAddress");
		var status = getVal("mStatus");
		var createTime = getVal("mCreateTime");
		var updateTime = getVal("mUpdateTime");
		var remark1 = getVal("mRemark1");
		var remark2 = getVal("mRemark2");
		var remark3 = getVal("mRemark3");
		var flag = validateForm('updateForm');
	    if (flag){ 
	        $.post("<c:url value='/hDispatchRecord/updateHDispatchRecord'/>",
	        	{
		    		id : id,
		    		ammeter_no : ammeter_no,
		    		totalFee : totalFee,
		    		accountName : accountName,
		    		address : address,
		    		status : status,
		    		createTime : createTime,
		    		updateTime : updateTime,
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