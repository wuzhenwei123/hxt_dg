<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>添加HPayOrder</h4>
</div>
<div class="modal-body">
    <form id="addForm" class="well well-nice form-horizontal">
         <div class="control-group">
             <label class="control-label" for="aO_id">o_id</label>
             <div class="controls">
			    <input type="text" placeholder="o_id" id="aO_id" isNotNull="true" warnName="o_id" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aO_no">o_no</label>
             <div class="controls">
			    <input type="text" placeholder="o_no" id="aO_no" isNotNull="true" warnName="o_no" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aQuery_id">query_id</label>
             <div class="controls">
			    <input type="text" placeholder="query_id" id="aQuery_id" isNotNull="true" warnName="query_id" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aPay_ip">pay_ip</label>
             <div class="controls">
			    <input type="text" placeholder="pay_ip" id="aPay_ip" isNotNull="true" warnName="pay_ip" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aC_id">c_id</label>
             <div class="controls">
			    <input type="text" placeholder="c_id" id="aC_id" isNotNull="true" warnName="c_id" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aO_sub_id">o_sub_id</label>
             <div class="controls">
			    <input type="text" placeholder="o_sub_id" id="aO_sub_id" isNotNull="true" warnName="o_sub_id" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aAmount">amount</label>
             <div class="controls">
			    <input type="text" placeholder="amount" id="aAmount" isNotNull="true" warnName="amount" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aActual_payment">actual_payment</label>
             <div class="controls">
			    <input type="text" placeholder="actual_payment" id="aActual_payment" isNotNull="true" warnName="actual_payment" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aAccount_payment">account_payment</label>
             <div class="controls">
			    <input type="text" placeholder="account_payment" id="aAccount_payment" isNotNull="true" warnName="account_payment" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aPay_type">pay_type</label>
             <div class="controls">
			    <input type="text" placeholder="pay_type" id="aPay_type" isNotNull="true" warnName="pay_type" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aPay_account">pay_account</label>
             <div class="controls">
			    <input type="text" placeholder="pay_account" id="aPay_account" isNotNull="true" warnName="pay_account" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aOperator_id">operator_id</label>
             <div class="controls">
			    <input type="text" placeholder="operator_id" id="aOperator_id" isNotNull="true" warnName="operator_id" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aPayee">payee</label>
             <div class="controls">
			    <input type="text" placeholder="payee" id="aPayee" isNotNull="true" warnName="payee" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aCreate_time">create_time</label>
             <div class="controls">
		     	<input type="text" placeholder="create_time" id="aCreate_time" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="create_time" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aPay_time">pay_time</label>
             <div class="controls">
		     	<input type="text" placeholder="pay_time" id="aPay_time" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="pay_time" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aPay_status">pay_status</label>
             <div class="controls">
			    <input type="text" placeholder="pay_status" id="aPay_status" isNotNull="true" warnName="pay_status" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aTick_off_status">tick_off_status</label>
             <div class="controls">
			    <input type="text" placeholder="tick_off_status" id="aTick_off_status" isNotNull="true" warnName="tick_off_status" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aTick_off_time">tick_off_time</label>
             <div class="controls">
		     	<input type="text" placeholder="tick_off_time" id="aTick_off_time" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="tick_off_time" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aBack_fee_status">back_fee_status</label>
             <div class="controls">
			    <input type="text" placeholder="back_fee_status" id="aBack_fee_status" isNotNull="true" warnName="back_fee_status" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aBack_time">back_time</label>
             <div class="controls">
		     	<input type="text" placeholder="back_time" id="aBack_time" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="back_time" />
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
		var o_id = getVal("aO_id");
		var o_no = getVal("aO_no");
		var query_id = getVal("aQuery_id");
		var pay_ip = getVal("aPay_ip");
		var c_id = getVal("aC_id");
		var o_sub_id = getVal("aO_sub_id");
		var amount = getVal("aAmount");
		var actual_payment = getVal("aActual_payment");
		var account_payment = getVal("aAccount_payment");
		var pay_type = getVal("aPay_type");
		var pay_account = getVal("aPay_account");
		var operator_id = getVal("aOperator_id");
		var payee = getVal("aPayee");
		var create_time = getVal("aCreate_time");
		var pay_time = getVal("aPay_time");
		var pay_status = getVal("aPay_status");
		var tick_off_status = getVal("aTick_off_status");
		var tick_off_time = getVal("aTick_off_time");
		var back_fee_status = getVal("aBack_fee_status");
		var back_time = getVal("aBack_time");
		var flag = validateForm('addForm');
	    if (flag){ 
	        $.post("<c:url value='/hPayOrder/addHPayOrder'/>",
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
		              	tipOk("保存成功!");
		             } else {
		            	 tipError(result.message);
		             }
	              	$modal.modal('hide');
	        });
	    }
	}
</script>