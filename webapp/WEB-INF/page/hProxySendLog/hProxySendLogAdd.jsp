<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>添加HProxySendLog</h4>
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
             <label class="control-label" for="aStyle">style</label>
             <div class="controls">
			    <input type="text" placeholder="style" id="aStyle" isNotNull="true" warnName="style" />
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
             <label class="control-label" for="aUserNumber">userNumber</label>
             <div class="controls">
			    <input type="text" placeholder="userNumber" id="aUserNumber" isNotNull="true" warnName="userNumber" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aContent">content</label>
             <div class="controls">
			    <input type="text" placeholder="content" id="aContent" isNotNull="true" warnName="content" />
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
         <div class="control-group">
             <label class="control-label" for="aPayBankNumber">payBankNumber</label>
             <div class="controls">
			    <input type="text" placeholder="payBankNumber" id="aPayBankNumber" isNotNull="true" warnName="payBankNumber" />
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
         <div class="control-group">
             <label class="control-label" for="aSendStyle">sendStyle</label>
             <div class="controls">
			    <input type="text" placeholder="sendStyle" id="aSendStyle" isNotNull="true" warnName="sendStyle" />
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
		var style = getVal("aStyle");
		var contractNumber = getVal("aContractNumber");
		var userNumber = getVal("aUserNumber");
		var content = getVal("aContent");
		var bank_number = getVal("aBank_number");
		var payBankNumber = getVal("aPayBankNumber");
		var remark1 = getVal("aRemark1");
		var remark2 = getVal("aRemark2");
		var remark3 = getVal("aRemark3");
		var sendStyle = getVal("aSendStyle");
		var flag = validateForm('addForm');
	    if (flag){ 
	        $.post("<c:url value='/hProxySendLog/addHProxySendLog'/>",
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
		              	tipOk("保存成功!");
		             } else {
		            	 tipError(result.message);
		             }
	              	$modal.modal('hide');
	        });
	    }
	}
</script>