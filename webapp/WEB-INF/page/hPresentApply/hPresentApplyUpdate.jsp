<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>编辑HPresentApply</h4>
</div>
<div class="modal-body">
    <form id="updateForm" class="well well-nice form-horizontal">
         <div class="control-group">
             <label class="control-label" for="mId">id</label>
             <div class="controls">
			    <input type="text" placeholder="id" value="${hpresentapply.id}" id="mId" isNotNull="true" warnName="id" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mOpenId">openId</label>
             <div class="controls">
			    <input type="text" placeholder="openId" value="${hpresentapply.openId}" id="mOpenId" isNotNull="true" warnName="openId" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mTotalFee">totalFee</label>
             <div class="controls">
			    <input type="text" placeholder="totalFee" value="${hpresentapply.totalFee}" id="mTotalFee" isNotNull="true" warnName="totalFee" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mCreateTime">createTime</label>
             <div class="controls">
		     	<input type="text" placeholder="createTime" value="<fmt:formatDate value="${hpresentapply.createTime}" type="both"/>" id="mCreateTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="createTime" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mStatus">status</label>
             <div class="controls">
			    <input type="text" placeholder="status" value="${hpresentapply.status}" id="mStatus" isNotNull="true" warnName="status" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mOneAgentOpenId">oneAgentOpenId</label>
             <div class="controls">
			    <input type="text" placeholder="oneAgentOpenId" value="${hpresentapply.oneAgentOpenId}" id="mOneAgentOpenId" isNotNull="true" warnName="oneAgentOpenId" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mOneAgentName">oneAgentName</label>
             <div class="controls">
			    <input type="text" placeholder="oneAgentName" value="${hpresentapply.oneAgentName}" id="mOneAgentName" isNotNull="true" warnName="oneAgentName" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mTwoAgentOpenId">twoAgentOpenId</label>
             <div class="controls">
			    <input type="text" placeholder="twoAgentOpenId" value="${hpresentapply.twoAgentOpenId}" id="mTwoAgentOpenId" isNotNull="true" warnName="twoAgentOpenId" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mTwoAgentName">twoAgentName</label>
             <div class="controls">
			    <input type="text" placeholder="twoAgentName" value="${hpresentapply.twoAgentName}" id="mTwoAgentName" isNotNull="true" warnName="twoAgentName" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mAccountDetailId">accountDetailId</label>
             <div class="controls">
			    <input type="text" placeholder="accountDetailId" value="${hpresentapply.accountDetailId}" id="mAccountDetailId" isNotNull="true" warnName="accountDetailId" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mNickName">nickName</label>
             <div class="controls">
			    <input type="text" placeholder="nickName" value="${hpresentapply.nickName}" id="mNickName" isNotNull="true" warnName="nickName" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mBatchCode">batchCode</label>
             <div class="controls">
			    <input type="text" placeholder="batchCode" value="${hpresentapply.batchCode}" id="mBatchCode" isNotNull="true" warnName="batchCode" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mRemark1">remark1</label>
             <div class="controls">
			    <input type="text" placeholder="remark1" value="${hpresentapply.remark1}" id="mRemark1" isNotNull="true" warnName="remark1" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mRemark2">remark2</label>
             <div class="controls">
			    <input type="text" placeholder="remark2" value="${hpresentapply.remark2}" id="mRemark2" isNotNull="true" warnName="remark2" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mRemark3">remark3</label>
             <div class="controls">
			    <input type="text" placeholder="remark3" value="${hpresentapply.remark3}" id="mRemark3" isNotNull="true" warnName="remark3" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mServicerId">servicerId</label>
             <div class="controls">
			    <input type="text" placeholder="servicerId" value="${hpresentapply.servicerId}" id="mServicerId" isNotNull="true" warnName="servicerId" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mServicerName">servicerName</label>
             <div class="controls">
			    <input type="text" placeholder="servicerName" value="${hpresentapply.servicerName}" id="mServicerName" isNotNull="true" warnName="servicerName" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mTaxRate">taxRate</label>
             <div class="controls">
			    <input type="text" placeholder="taxRate" value="${hpresentapply.taxRate}" id="mTaxRate" isNotNull="true" warnName="taxRate" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mTaxFee">taxFee</label>
             <div class="controls">
			    <input type="text" placeholder="taxFee" value="${hpresentapply.taxFee}" id="mTaxFee" isNotNull="true" warnName="taxFee" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mAllFee">allFee</label>
             <div class="controls">
			    <input type="text" placeholder="allFee" value="${hpresentapply.allFee}" id="mAllFee" isNotNull="true" warnName="allFee" />
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
		var openId = getVal("mOpenId");
		var totalFee = getVal("mTotalFee");
		var createTime = getVal("mCreateTime");
		var status = getVal("mStatus");
		var oneAgentOpenId = getVal("mOneAgentOpenId");
		var oneAgentName = getVal("mOneAgentName");
		var twoAgentOpenId = getVal("mTwoAgentOpenId");
		var twoAgentName = getVal("mTwoAgentName");
		var accountDetailId = getVal("mAccountDetailId");
		var nickName = getVal("mNickName");
		var batchCode = getVal("mBatchCode");
		var remark1 = getVal("mRemark1");
		var remark2 = getVal("mRemark2");
		var remark3 = getVal("mRemark3");
		var servicerId = getVal("mServicerId");
		var servicerName = getVal("mServicerName");
		var taxRate = getVal("mTaxRate");
		var taxFee = getVal("mTaxFee");
		var allFee = getVal("mAllFee");
		var flag = validateForm('updateForm');
	    if (flag){ 
	        $.post("<c:url value='/hPresentApply/updateHPresentApply'/>",
	        	{
		    		id : id,
		    		openId : openId,
		    		totalFee : totalFee,
		    		createTime : createTime,
		    		status : status,
		    		oneAgentOpenId : oneAgentOpenId,
		    		oneAgentName : oneAgentName,
		    		twoAgentOpenId : twoAgentOpenId,
		    		twoAgentName : twoAgentName,
		    		accountDetailId : accountDetailId,
		    		nickName : nickName,
		    		batchCode : batchCode,
		    		remark1 : remark1,
		    		remark2 : remark2,
		    		remark3 : remark3,
		    		servicerId : servicerId,
		    		servicerName : servicerName,
		    		taxRate : taxRate,
		    		taxFee : taxFee,
		    		allFee : allFee,
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