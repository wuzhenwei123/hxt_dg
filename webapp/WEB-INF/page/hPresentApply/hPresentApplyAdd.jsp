<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>添加HPresentApply</h4>
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
             <label class="control-label" for="aOpenId">openId</label>
             <div class="controls">
			    <input type="text" placeholder="openId" id="aOpenId" isNotNull="true" warnName="openId" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aTotalFee">totalFee</label>
             <div class="controls">
			    <input type="text" placeholder="totalFee" id="aTotalFee" isNotNull="true" warnName="totalFee" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aCreateTime">createTime</label>
             <div class="controls">
		     	<input type="text" placeholder="createTime" id="aCreateTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="createTime" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aStatus">status</label>
             <div class="controls">
			    <input type="text" placeholder="status" id="aStatus" isNotNull="true" warnName="status" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aOneAgentOpenId">oneAgentOpenId</label>
             <div class="controls">
			    <input type="text" placeholder="oneAgentOpenId" id="aOneAgentOpenId" isNotNull="true" warnName="oneAgentOpenId" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aOneAgentName">oneAgentName</label>
             <div class="controls">
			    <input type="text" placeholder="oneAgentName" id="aOneAgentName" isNotNull="true" warnName="oneAgentName" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aTwoAgentOpenId">twoAgentOpenId</label>
             <div class="controls">
			    <input type="text" placeholder="twoAgentOpenId" id="aTwoAgentOpenId" isNotNull="true" warnName="twoAgentOpenId" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aTwoAgentName">twoAgentName</label>
             <div class="controls">
			    <input type="text" placeholder="twoAgentName" id="aTwoAgentName" isNotNull="true" warnName="twoAgentName" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aAccountDetailId">accountDetailId</label>
             <div class="controls">
			    <input type="text" placeholder="accountDetailId" id="aAccountDetailId" isNotNull="true" warnName="accountDetailId" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aNickName">nickName</label>
             <div class="controls">
			    <input type="text" placeholder="nickName" id="aNickName" isNotNull="true" warnName="nickName" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aBatchCode">batchCode</label>
             <div class="controls">
			    <input type="text" placeholder="batchCode" id="aBatchCode" isNotNull="true" warnName="batchCode" />
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
             <label class="control-label" for="aServicerId">servicerId</label>
             <div class="controls">
			    <input type="text" placeholder="servicerId" id="aServicerId" isNotNull="true" warnName="servicerId" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aServicerName">servicerName</label>
             <div class="controls">
			    <input type="text" placeholder="servicerName" id="aServicerName" isNotNull="true" warnName="servicerName" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aTaxRate">taxRate</label>
             <div class="controls">
			    <input type="text" placeholder="taxRate" id="aTaxRate" isNotNull="true" warnName="taxRate" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aTaxFee">taxFee</label>
             <div class="controls">
			    <input type="text" placeholder="taxFee" id="aTaxFee" isNotNull="true" warnName="taxFee" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aAllFee">allFee</label>
             <div class="controls">
			    <input type="text" placeholder="allFee" id="aAllFee" isNotNull="true" warnName="allFee" />
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
		var openId = getVal("aOpenId");
		var totalFee = getVal("aTotalFee");
		var createTime = getVal("aCreateTime");
		var status = getVal("aStatus");
		var oneAgentOpenId = getVal("aOneAgentOpenId");
		var oneAgentName = getVal("aOneAgentName");
		var twoAgentOpenId = getVal("aTwoAgentOpenId");
		var twoAgentName = getVal("aTwoAgentName");
		var accountDetailId = getVal("aAccountDetailId");
		var nickName = getVal("aNickName");
		var batchCode = getVal("aBatchCode");
		var remark1 = getVal("aRemark1");
		var remark2 = getVal("aRemark2");
		var remark3 = getVal("aRemark3");
		var servicerId = getVal("aServicerId");
		var servicerName = getVal("aServicerName");
		var taxRate = getVal("aTaxRate");
		var taxFee = getVal("aTaxFee");
		var allFee = getVal("aAllFee");
		var flag = validateForm('addForm');
	    if (flag){ 
	        $.post("<c:url value='/hPresentApply/addHPresentApply'/>",
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
		              	tipOk("保存成功!");
		             } else {
		            	 tipError(result.message);
		             }
	              	$modal.modal('hide');
	        });
	    }
	}
</script>