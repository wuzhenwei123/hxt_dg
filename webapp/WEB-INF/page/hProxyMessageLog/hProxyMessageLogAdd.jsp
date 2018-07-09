<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>添加HProxyMessageLog</h4>
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
             <label class="control-label" for="aProxyName">proxyName</label>
             <div class="controls">
			    <input type="text" placeholder="proxyName" id="aProxyName" isNotNull="true" warnName="proxyName" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aProxyPhone">proxyPhone</label>
             <div class="controls">
			    <input type="text" placeholder="proxyPhone" id="aProxyPhone" isNotNull="true" warnName="proxyPhone" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aCreateYime">createYime</label>
             <div class="controls">
		     	<input type="text" placeholder="createYime" id="aCreateYime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="createYime" />
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
             <label class="control-label" for="aContractStartTime">contractStartTime</label>
             <div class="controls">
		     	<input type="text" placeholder="contractStartTime" id="aContractStartTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="contractStartTime" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aContractEndTime">contractEndTime</label>
             <div class="controls">
		     	<input type="text" placeholder="contractEndTime" id="aContractEndTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="contractEndTime" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aRemindStartDate">remindStartDate</label>
             <div class="controls">
			    <input type="text" placeholder="remindStartDate" id="aRemindStartDate" isNotNull="true" warnName="remindStartDate" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aRemindEndDate">remindEndDate</label>
             <div class="controls">
			    <input type="text" placeholder="remindEndDate" id="aRemindEndDate" isNotNull="true" warnName="remindEndDate" />
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
             <label class="control-label" for="aInfo">info</label>
             <div class="controls">
			    <input type="text" placeholder="info" id="aInfo" isNotNull="true" warnName="info" />
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
             <label class="control-label" for="aCId">cId</label>
             <div class="controls">
			    <input type="text" placeholder="cId" id="aCId" isNotNull="true" warnName="cId" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aUserId">userId</label>
             <div class="controls">
			    <input type="text" placeholder="userId" id="aUserId" isNotNull="true" warnName="userId" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aState">state</label>
             <div class="controls">
		    	<select id="aState"> 
                    <option value="1">正常</option> 
                    <option value="0">禁用</option> 
            	</select> 
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
             <label class="control-label" for="aProxyAddress">proxyAddress</label>
             <div class="controls">
			    <input type="text" placeholder="proxyAddress" id="aProxyAddress" isNotNull="true" warnName="proxyAddress" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aProxyCode">proxyCode</label>
             <div class="controls">
			    <input type="text" placeholder="proxyCode" id="aProxyCode" isNotNull="true" warnName="proxyCode" />
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
             <label class="control-label" for="aPayName">payName</label>
             <div class="controls">
			    <input type="text" placeholder="payName" id="aPayName" isNotNull="true" warnName="payName" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aPayCardStyle">payCardStyle</label>
             <div class="controls">
			    <input type="text" placeholder="payCardStyle" id="aPayCardStyle" isNotNull="true" warnName="payCardStyle" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aPayCard">payCard</label>
             <div class="controls">
			    <input type="text" placeholder="payCard" id="aPayCard" isNotNull="true" warnName="payCard" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aPayPhoneNumber">payPhoneNumber</label>
             <div class="controls">
			    <input type="text" placeholder="payPhoneNumber" id="aPayPhoneNumber" isNotNull="true" warnName="payPhoneNumber" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aPayMail">payMail</label>
             <div class="controls">
			    <input type="text" placeholder="payMail" id="aPayMail" isNotNull="true" warnName="payMail" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aSex">sex</label>
             <div class="controls">
			    <input type="text" placeholder="sex" id="aSex" isNotNull="true" warnName="sex" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aCheckState">checkState</label>
             <div class="controls">
			    <input type="text" placeholder="checkState" id="aCheckState" isNotNull="true" warnName="checkState" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aHetongUrl">hetongUrl</label>
             <div class="controls">
			    <input type="text" placeholder="hetongUrl" id="aHetongUrl" isNotNull="true" warnName="hetongUrl" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aChexiaoUrl">chexiaoUrl</label>
             <div class="controls">
			    <input type="text" placeholder="chexiaoUrl" id="aChexiaoUrl" isNotNull="true" warnName="chexiaoUrl" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aMsg">msg</label>
             <div class="controls">
			    <input type="text" placeholder="msg" id="aMsg" isNotNull="true" warnName="msg" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aBank_name">bank_name</label>
             <div class="controls">
			    <input type="text" placeholder="bank_name" id="aBank_name" isNotNull="true" warnName="bank_name" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aPayAccountName">payAccountName</label>
             <div class="controls">
			    <input type="text" placeholder="payAccountName" id="aPayAccountName" isNotNull="true" warnName="payAccountName" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aBiangengUrl">biangengUrl</label>
             <div class="controls">
			    <input type="text" placeholder="biangengUrl" id="aBiangengUrl" isNotNull="true" warnName="biangengUrl" />
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
		var proxyName = getVal("aProxyName");
		var proxyPhone = getVal("aProxyPhone");
		var createYime = getVal("aCreateYime");
		var contractNumber = getVal("aContractNumber");
		var contractStartTime = getVal("aContractStartTime");
		var contractEndTime = getVal("aContractEndTime");
		var remindStartDate = getVal("aRemindStartDate");
		var remindEndDate = getVal("aRemindEndDate");
		var bank_number = getVal("aBank_number");
		var info = getVal("aInfo");
		var remark1 = getVal("aRemark1");
		var remark2 = getVal("aRemark2");
		var remark3 = getVal("aRemark3");
		var cId = getVal("aCId");
		var userId = getVal("aUserId");
		var state = getVal("aState");
		var userNumber = getVal("aUserNumber");
		var proxyAddress = getVal("aProxyAddress");
		var proxyCode = getVal("aProxyCode");
		var payBankNumber = getVal("aPayBankNumber");
		var payName = getVal("aPayName");
		var payCardStyle = getVal("aPayCardStyle");
		var payCard = getVal("aPayCard");
		var payPhoneNumber = getVal("aPayPhoneNumber");
		var payMail = getVal("aPayMail");
		var sex = getVal("aSex");
		var checkState = getVal("aCheckState");
		var hetongUrl = getVal("aHetongUrl");
		var chexiaoUrl = getVal("aChexiaoUrl");
		var msg = getVal("aMsg");
		var bank_name = getVal("aBank_name");
		var payAccountName = getVal("aPayAccountName");
		var biangengUrl = getVal("aBiangengUrl");
		var flag = validateForm('addForm');
	    if (flag){ 
	        $.post("<c:url value='/hProxyMessageLog/addHProxyMessageLog'/>",
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
		              	tipOk("保存成功!");
		             } else {
		            	 tipError(result.message);
		             }
	              	$modal.modal('hide');
	        });
	    }
	}
</script>