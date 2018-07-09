<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>编辑HProxyMessageLog</h4>
</div>
<div class="modal-body">
    <form id="updateForm" class="well well-nice form-horizontal">
         <div class="control-group">
             <label class="control-label" for="mId">id</label>
             <div class="controls">
			    <input type="text" placeholder="id" value="${hproxymessagelog.id}" id="mId" isNotNull="true" warnName="id" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mProxyName">proxyName</label>
             <div class="controls">
			    <input type="text" placeholder="proxyName" value="${hproxymessagelog.proxyName}" id="mProxyName" isNotNull="true" warnName="proxyName" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mProxyPhone">proxyPhone</label>
             <div class="controls">
			    <input type="text" placeholder="proxyPhone" value="${hproxymessagelog.proxyPhone}" id="mProxyPhone" isNotNull="true" warnName="proxyPhone" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mCreateYime">createYime</label>
             <div class="controls">
		     	<input type="text" placeholder="createYime" value="<fmt:formatDate value="${hproxymessagelog.createYime}" type="both"/>" id="mCreateYime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="createYime" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mContractNumber">contractNumber</label>
             <div class="controls">
			    <input type="text" placeholder="contractNumber" value="${hproxymessagelog.contractNumber}" id="mContractNumber" isNotNull="true" warnName="contractNumber" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mContractStartTime">contractStartTime</label>
             <div class="controls">
		     	<input type="text" placeholder="contractStartTime" value="<fmt:formatDate value="${hproxymessagelog.contractStartTime}" type="both"/>" id="mContractStartTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="contractStartTime" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mContractEndTime">contractEndTime</label>
             <div class="controls">
		     	<input type="text" placeholder="contractEndTime" value="<fmt:formatDate value="${hproxymessagelog.contractEndTime}" type="both"/>" id="mContractEndTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="contractEndTime" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mRemindStartDate">remindStartDate</label>
             <div class="controls">
			    <input type="text" placeholder="remindStartDate" value="${hproxymessagelog.remindStartDate}" id="mRemindStartDate" isNotNull="true" warnName="remindStartDate" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mRemindEndDate">remindEndDate</label>
             <div class="controls">
			    <input type="text" placeholder="remindEndDate" value="${hproxymessagelog.remindEndDate}" id="mRemindEndDate" isNotNull="true" warnName="remindEndDate" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mBank_number">bank_number</label>
             <div class="controls">
			    <input type="text" placeholder="bank_number" value="${hproxymessagelog.bank_number}" id="mBank_number" isNotNull="true" warnName="bank_number" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mInfo">info</label>
             <div class="controls">
			    <input type="text" placeholder="info" value="${hproxymessagelog.info}" id="mInfo" isNotNull="true" warnName="info" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mRemark1">remark1</label>
             <div class="controls">
			    <input type="text" placeholder="remark1" value="${hproxymessagelog.remark1}" id="mRemark1" isNotNull="true" warnName="remark1" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mRemark2">remark2</label>
             <div class="controls">
			    <input type="text" placeholder="remark2" value="${hproxymessagelog.remark2}" id="mRemark2" isNotNull="true" warnName="remark2" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mRemark3">remark3</label>
             <div class="controls">
			    <input type="text" placeholder="remark3" value="${hproxymessagelog.remark3}" id="mRemark3" isNotNull="true" warnName="remark3" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mCId">cId</label>
             <div class="controls">
			    <input type="text" placeholder="cId" value="${hproxymessagelog.cId}" id="mCId" isNotNull="true" warnName="cId" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mUserId">userId</label>
             <div class="controls">
			    <input type="text" placeholder="userId" value="${hproxymessagelog.userId}" id="mUserId" isNotNull="true" warnName="userId" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mState">state</label>
             <div class="controls">
		    	 <select id="mState" isNotNull="true" warnName="状态"> 
                    <option <c:if test="${hproxymessagelog.state == 1}" >selected="selected"</c:if>  value="1">正常</option> 
                    <option <c:if test="${hproxymessagelog.state == 0}" >selected="selected"</c:if> value="0">禁用</option> 
            	</select> 
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mUserNumber">userNumber</label>
             <div class="controls">
			    <input type="text" placeholder="userNumber" value="${hproxymessagelog.userNumber}" id="mUserNumber" isNotNull="true" warnName="userNumber" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mProxyAddress">proxyAddress</label>
             <div class="controls">
			    <input type="text" placeholder="proxyAddress" value="${hproxymessagelog.proxyAddress}" id="mProxyAddress" isNotNull="true" warnName="proxyAddress" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mProxyCode">proxyCode</label>
             <div class="controls">
			    <input type="text" placeholder="proxyCode" value="${hproxymessagelog.proxyCode}" id="mProxyCode" isNotNull="true" warnName="proxyCode" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mPayBankNumber">payBankNumber</label>
             <div class="controls">
			    <input type="text" placeholder="payBankNumber" value="${hproxymessagelog.payBankNumber}" id="mPayBankNumber" isNotNull="true" warnName="payBankNumber" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mPayName">payName</label>
             <div class="controls">
			    <input type="text" placeholder="payName" value="${hproxymessagelog.payName}" id="mPayName" isNotNull="true" warnName="payName" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mPayCardStyle">payCardStyle</label>
             <div class="controls">
			    <input type="text" placeholder="payCardStyle" value="${hproxymessagelog.payCardStyle}" id="mPayCardStyle" isNotNull="true" warnName="payCardStyle" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mPayCard">payCard</label>
             <div class="controls">
			    <input type="text" placeholder="payCard" value="${hproxymessagelog.payCard}" id="mPayCard" isNotNull="true" warnName="payCard" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mPayPhoneNumber">payPhoneNumber</label>
             <div class="controls">
			    <input type="text" placeholder="payPhoneNumber" value="${hproxymessagelog.payPhoneNumber}" id="mPayPhoneNumber" isNotNull="true" warnName="payPhoneNumber" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mPayMail">payMail</label>
             <div class="controls">
			    <input type="text" placeholder="payMail" value="${hproxymessagelog.payMail}" id="mPayMail" isNotNull="true" warnName="payMail" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mSex">sex</label>
             <div class="controls">
			    <input type="text" placeholder="sex" value="${hproxymessagelog.sex}" id="mSex" isNotNull="true" warnName="sex" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mCheckState">checkState</label>
             <div class="controls">
			    <input type="text" placeholder="checkState" value="${hproxymessagelog.checkState}" id="mCheckState" isNotNull="true" warnName="checkState" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mHetongUrl">hetongUrl</label>
             <div class="controls">
			    <input type="text" placeholder="hetongUrl" value="${hproxymessagelog.hetongUrl}" id="mHetongUrl" isNotNull="true" warnName="hetongUrl" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mChexiaoUrl">chexiaoUrl</label>
             <div class="controls">
			    <input type="text" placeholder="chexiaoUrl" value="${hproxymessagelog.chexiaoUrl}" id="mChexiaoUrl" isNotNull="true" warnName="chexiaoUrl" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mMsg">msg</label>
             <div class="controls">
			    <input type="text" placeholder="msg" value="${hproxymessagelog.msg}" id="mMsg" isNotNull="true" warnName="msg" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mBank_name">bank_name</label>
             <div class="controls">
			    <input type="text" placeholder="bank_name" value="${hproxymessagelog.bank_name}" id="mBank_name" isNotNull="true" warnName="bank_name" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mPayAccountName">payAccountName</label>
             <div class="controls">
			    <input type="text" placeholder="payAccountName" value="${hproxymessagelog.payAccountName}" id="mPayAccountName" isNotNull="true" warnName="payAccountName" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mBiangengUrl">biangengUrl</label>
             <div class="controls">
			    <input type="text" placeholder="biangengUrl" value="${hproxymessagelog.biangengUrl}" id="mBiangengUrl" isNotNull="true" warnName="biangengUrl" />
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
		var proxyName = getVal("mProxyName");
		var proxyPhone = getVal("mProxyPhone");
		var createYime = getVal("mCreateYime");
		var contractNumber = getVal("mContractNumber");
		var contractStartTime = getVal("mContractStartTime");
		var contractEndTime = getVal("mContractEndTime");
		var remindStartDate = getVal("mRemindStartDate");
		var remindEndDate = getVal("mRemindEndDate");
		var bank_number = getVal("mBank_number");
		var info = getVal("mInfo");
		var remark1 = getVal("mRemark1");
		var remark2 = getVal("mRemark2");
		var remark3 = getVal("mRemark3");
		var cId = getVal("mCId");
		var userId = getVal("mUserId");
		var state = getVal("mState");
		var userNumber = getVal("mUserNumber");
		var proxyAddress = getVal("mProxyAddress");
		var proxyCode = getVal("mProxyCode");
		var payBankNumber = getVal("mPayBankNumber");
		var payName = getVal("mPayName");
		var payCardStyle = getVal("mPayCardStyle");
		var payCard = getVal("mPayCard");
		var payPhoneNumber = getVal("mPayPhoneNumber");
		var payMail = getVal("mPayMail");
		var sex = getVal("mSex");
		var checkState = getVal("mCheckState");
		var hetongUrl = getVal("mHetongUrl");
		var chexiaoUrl = getVal("mChexiaoUrl");
		var msg = getVal("mMsg");
		var bank_name = getVal("mBank_name");
		var payAccountName = getVal("mPayAccountName");
		var biangengUrl = getVal("mBiangengUrl");
		var flag = validateForm('updateForm');
	    if (flag){ 
	        $.post("<c:url value='/hProxyMessageLog/updateHProxyMessageLog'/>",
	        	{
		    		id : id,
		    		proxyName : proxyName,
		    		proxyPhone : proxyPhone,
		    		createYime : createYime,
		    		contractNumber : contractNumber,
		    		contractStartTime : contractStartTime,
		    		contractEndTime : contractEndTime,
		    		remindStartDate : remindStartDate,
		    		remindEndDate : remindEndDate,
		    		bank_number : bank_number,
		    		info : info,
		    		remark1 : remark1,
		    		remark2 : remark2,
		    		remark3 : remark3,
		    		cId : cId,
		    		userId : userId,
		    		state : state,
		    		userNumber : userNumber,
		    		proxyAddress : proxyAddress,
		    		proxyCode : proxyCode,
		    		payBankNumber : payBankNumber,
		    		payName : payName,
		    		payCardStyle : payCardStyle,
		    		payCard : payCard,
		    		payPhoneNumber : payPhoneNumber,
		    		payMail : payMail,
		    		sex : sex,
		    		checkState : checkState,
		    		hetongUrl : hetongUrl,
		    		chexiaoUrl : chexiaoUrl,
		    		msg : msg,
		    		bank_name : bank_name,
		    		payAccountName : payAccountName,
		    		biangengUrl : biangengUrl,
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