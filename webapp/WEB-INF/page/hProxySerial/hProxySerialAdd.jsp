<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>添加HProxySerial</h4>
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
             <label class="control-label" for="aPayBankNumber">payBankNumber</label>
             <div class="controls">
			    <input type="text" placeholder="payBankNumber" id="aPayBankNumber" isNotNull="true" warnName="payBankNumber" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aUserNumber">userNumber</label>
             <div class="controls">
			    <input type="text" placeholder="userNumber" id="aUserNumber" isNotNull="true" warnName="userNumber" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aContractNumber">contractNumber</label>
             <div class="controls">
			    <input type="text" placeholder="contractNumber" id="aContractNumber" isNotNull="true" warnName="contractNumber" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aBank_number">bank_number</label>
             <div class="controls">
			    <input type="text" placeholder="bank_number" id="aBank_number" isNotNull="true" warnName="bank_number" />
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
		var payBankNumber = getVal("aPayBankNumber");
		var userNumber = getVal("aUserNumber");
		var contractNumber = getVal("aContractNumber");
		var bank_number = getVal("aBank_number");
		var flag = validateForm('addForm');
	    if (flag){ 
	        $.post("<c:url value='/hProxySerial/addHProxySerial'/>",
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
		              	tipOk("保存成功!");
		             } else {
		            	 tipError(result.message);
		             }
	              	$modal.modal('hide');
	        });
	    }
	}
</script>