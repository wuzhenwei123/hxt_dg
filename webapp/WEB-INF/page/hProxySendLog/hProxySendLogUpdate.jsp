<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>编辑HProxySendLog</h4>
</div>
<div class="modal-body">
    <form id="updateForm" class="well well-nice form-horizontal">
         <div class="control-group">
             <label class="control-label" for="mId">id</label>
             <div class="controls">
			    <input type="text" placeholder="id" value="${hproxysendlog.id}" id="mId" isNotNull="true" warnName="id" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mStyle">style</label>
             <div class="controls">
			    <input type="text" placeholder="style" value="${hproxysendlog.style}" id="mStyle" isNotNull="true" warnName="style" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mContractNumber">contractNumber</label>
             <div class="controls">
			    <input type="text" placeholder="contractNumber" value="${hproxysendlog.contractNumber}" id="mContractNumber" isNotNull="true" warnName="contractNumber" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mUserNumber">userNumber</label>
             <div class="controls">
			    <input type="text" placeholder="userNumber" value="${hproxysendlog.userNumber}" id="mUserNumber" isNotNull="true" warnName="userNumber" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mContent">content</label>
             <div class="controls">
			    <input type="text" placeholder="content" value="${hproxysendlog.content}" id="mContent" isNotNull="true" warnName="content" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mBank_number">bank_number</label>
             <div class="controls">
			    <input type="text" placeholder="bank_number" value="${hproxysendlog.bank_number}" id="mBank_number" isNotNull="true" warnName="bank_number" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mPayBankNumber">payBankNumber</label>
             <div class="controls">
			    <input type="text" placeholder="payBankNumber" value="${hproxysendlog.payBankNumber}" id="mPayBankNumber" isNotNull="true" warnName="payBankNumber" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mRemark1">remark1</label>
             <div class="controls">
			    <input type="text" placeholder="remark1" value="${hproxysendlog.remark1}" id="mRemark1" isNotNull="true" warnName="remark1" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mRemark2">remark2</label>
             <div class="controls">
			    <input type="text" placeholder="remark2" value="${hproxysendlog.remark2}" id="mRemark2" isNotNull="true" warnName="remark2" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mRemark3">remark3</label>
             <div class="controls">
			    <input type="text" placeholder="remark3" value="${hproxysendlog.remark3}" id="mRemark3" isNotNull="true" warnName="remark3" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mSendStyle">sendStyle</label>
             <div class="controls">
			    <input type="text" placeholder="sendStyle" value="${hproxysendlog.sendStyle}" id="mSendStyle" isNotNull="true" warnName="sendStyle" />
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
		var style = getVal("mStyle");
		var contractNumber = getVal("mContractNumber");
		var userNumber = getVal("mUserNumber");
		var content = getVal("mContent");
		var bank_number = getVal("mBank_number");
		var payBankNumber = getVal("mPayBankNumber");
		var remark1 = getVal("mRemark1");
		var remark2 = getVal("mRemark2");
		var remark3 = getVal("mRemark3");
		var sendStyle = getVal("mSendStyle");
		var flag = validateForm('updateForm');
	    if (flag){ 
	        $.post("<c:url value='/hProxySendLog/updateHProxySendLog'/>",
	        	{
		    		id : id,
		    		style : style,
		    		contractNumber : contractNumber,
		    		userNumber : userNumber,
		    		content : content,
		    		bank_number : bank_number,
		    		payBankNumber : payBankNumber,
		    		remark1 : remark1,
		    		remark2 : remark2,
		    		remark3 : remark3,
		    		sendStyle : sendStyle,
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