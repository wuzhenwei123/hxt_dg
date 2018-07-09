<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>编辑HPayOrder</h4>
</div>
<div class="modal-body">
    <form id="updateForm" class="well well-nice form-horizontal">
         <div class="control-group">
             <label class="control-label" for="mO_id">o_id</label>
             <div class="controls">
			    <input type="text" placeholder="o_id" value="${hpayorder.o_id}" id="mO_id" isNotNull="true" warnName="o_id" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mO_no">o_no</label>
             <div class="controls">
			    <input type="text" placeholder="o_no" value="${hpayorder.o_no}" id="mO_no" isNotNull="true" warnName="o_no" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mQuery_id">query_id</label>
             <div class="controls">
			    <input type="text" placeholder="query_id" value="${hpayorder.query_id}" id="mQuery_id" isNotNull="true" warnName="query_id" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mPay_ip">pay_ip</label>
             <div class="controls">
			    <input type="text" placeholder="pay_ip" value="${hpayorder.pay_ip}" id="mPay_ip" isNotNull="true" warnName="pay_ip" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mC_id">c_id</label>
             <div class="controls">
			    <input type="text" placeholder="c_id" value="${hpayorder.c_id}" id="mC_id" isNotNull="true" warnName="c_id" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mO_sub_id">o_sub_id</label>
             <div class="controls">
			    <input type="text" placeholder="o_sub_id" value="${hpayorder.o_sub_id}" id="mO_sub_id" isNotNull="true" warnName="o_sub_id" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mAmount">amount</label>
             <div class="controls">
			    <input type="text" placeholder="amount" value="${hpayorder.amount}" id="mAmount" isNotNull="true" warnName="amount" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mActual_payment">actual_payment</label>
             <div class="controls">
			    <input type="text" placeholder="actual_payment" value="${hpayorder.actual_payment}" id="mActual_payment" isNotNull="true" warnName="actual_payment" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mAccount_payment">account_payment</label>
             <div class="controls">
			    <input type="text" placeholder="account_payment" value="${hpayorder.account_payment}" id="mAccount_payment" isNotNull="true" warnName="account_payment" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mPay_type">pay_type</label>
             <div class="controls">
			    <input type="text" placeholder="pay_type" value="${hpayorder.pay_type}" id="mPay_type" isNotNull="true" warnName="pay_type" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mPay_account">pay_account</label>
             <div class="controls">
			    <input type="text" placeholder="pay_account" value="${hpayorder.pay_account}" id="mPay_account" isNotNull="true" warnName="pay_account" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mOperator_id">operator_id</label>
             <div class="controls">
			    <input type="text" placeholder="operator_id" value="${hpayorder.operator_id}" id="mOperator_id" isNotNull="true" warnName="operator_id" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mPayee">payee</label>
             <div class="controls">
			    <input type="text" placeholder="payee" value="${hpayorder.payee}" id="mPayee" isNotNull="true" warnName="payee" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mCreate_time">create_time</label>
             <div class="controls">
		     	<input type="text" placeholder="create_time" value="<fmt:formatDate value="${hpayorder.create_time}" type="both"/>" id="mCreate_time" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="create_time" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mPay_time">pay_time</label>
             <div class="controls">
		     	<input type="text" placeholder="pay_time" value="<fmt:formatDate value="${hpayorder.pay_time}" type="both"/>" id="mPay_time" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="pay_time" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mPay_status">pay_status</label>
             <div class="controls">
			    <input type="text" placeholder="pay_status" value="${hpayorder.pay_status}" id="mPay_status" isNotNull="true" warnName="pay_status" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mTick_off_status">tick_off_status</label>
             <div class="controls">
			    <input type="text" placeholder="tick_off_status" value="${hpayorder.tick_off_status}" id="mTick_off_status" isNotNull="true" warnName="tick_off_status" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mTick_off_time">tick_off_time</label>
             <div class="controls">
		     	<input type="text" placeholder="tick_off_time" value="<fmt:formatDate value="${hpayorder.tick_off_time}" type="both"/>" id="mTick_off_time" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="tick_off_time" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mBack_fee_status">back_fee_status</label>
             <div class="controls">
			    <input type="text" placeholder="back_fee_status" value="${hpayorder.back_fee_status}" id="mBack_fee_status" isNotNull="true" warnName="back_fee_status" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mBack_time">back_time</label>
             <div class="controls">
		     	<input type="text" placeholder="back_time" value="<fmt:formatDate value="${hpayorder.back_time}" type="both"/>" id="mBack_time" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="back_time" />
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
		var o_id = getVal("mO_id");
		var o_no = getVal("mO_no");
		var query_id = getVal("mQuery_id");
		var pay_ip = getVal("mPay_ip");
		var c_id = getVal("mC_id");
		var o_sub_id = getVal("mO_sub_id");
		var amount = getVal("mAmount");
		var actual_payment = getVal("mActual_payment");
		var account_payment = getVal("mAccount_payment");
		var pay_type = getVal("mPay_type");
		var pay_account = getVal("mPay_account");
		var operator_id = getVal("mOperator_id");
		var payee = getVal("mPayee");
		var create_time = getVal("mCreate_time");
		var pay_time = getVal("mPay_time");
		var pay_status = getVal("mPay_status");
		var tick_off_status = getVal("mTick_off_status");
		var tick_off_time = getVal("mTick_off_time");
		var back_fee_status = getVal("mBack_fee_status");
		var back_time = getVal("mBack_time");
		var flag = validateForm('updateForm');
	    if (flag){ 
	        $.post("<c:url value='/hPayOrder/updateHPayOrder'/>",
	        	{
		    		o_id : o_id,
		    		o_no : o_no,
		    		query_id : query_id,
		    		pay_ip : pay_ip,
		    		c_id : c_id,
		    		o_sub_id : o_sub_id,
		    		amount : amount,
		    		actual_payment : actual_payment,
		    		account_payment : account_payment,
		    		pay_type : pay_type,
		    		pay_account : pay_account,
		    		operator_id : operator_id,
		    		payee : payee,
		    		create_time : create_time,
		    		pay_time : pay_time,
		    		pay_status : pay_status,
		    		tick_off_status : tick_off_status,
		    		tick_off_time : tick_off_time,
		    		back_fee_status : back_fee_status,
		    		back_time : back_time,
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