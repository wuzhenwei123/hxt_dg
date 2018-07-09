<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>编辑HProxySerial</h4>
</div>
<div class="modal-body">
    <form id="updateForm" class="well well-nice form-horizontal">
         <div class="control-group">
             <label class="control-label" for="mId">id</label>
             <div class="controls">
			    <input type="text" placeholder="id" value="${hproxyserial.id}" id="mId" isNotNull="true" warnName="id" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mPayBankNumber">payBankNumber</label>
             <div class="controls">
			    <input type="text" placeholder="payBankNumber" value="${hproxyserial.payBankNumber}" id="mPayBankNumber" isNotNull="true" warnName="payBankNumber" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mUserNumber">userNumber</label>
             <div class="controls">
			    <input type="text" placeholder="userNumber" value="${hproxyserial.userNumber}" id="mUserNumber" isNotNull="true" warnName="userNumber" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mContractNumber">contractNumber</label>
             <div class="controls">
			    <input type="text" placeholder="contractNumber" value="${hproxyserial.contractNumber}" id="mContractNumber" isNotNull="true" warnName="contractNumber" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mBank_number">bank_number</label>
             <div class="controls">
			    <input type="text" placeholder="bank_number" value="${hproxyserial.bank_number}" id="mBank_number" isNotNull="true" warnName="bank_number" />
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
		var payBankNumber = getVal("mPayBankNumber");
		var userNumber = getVal("mUserNumber");
		var contractNumber = getVal("mContractNumber");
		var bank_number = getVal("mBank_number");
		var flag = validateForm('updateForm');
	    if (flag){ 
	        $.post("<c:url value='/hProxySerial/updateHProxySerial'/>",
	        	{
		    		id : id,
		    		payBankNumber : payBankNumber,
		    		userNumber : userNumber,
		    		contractNumber : contractNumber,
		    		bank_number : bank_number,
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