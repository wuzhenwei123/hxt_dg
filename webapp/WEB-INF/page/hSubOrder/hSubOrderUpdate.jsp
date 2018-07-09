<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>编辑HSubOrder</h4>
</div>
<div class="modal-body">
    <form id="updateForm" class="well well-nice form-horizontal">
         <div class="control-group">
             <label class="control-label" for="mO_sub_id">o_sub_id</label>
             <div class="controls">
			    <input type="text" placeholder="o_sub_id" value="${hsuborder.o_sub_id}" id="mO_sub_id" isNotNull="true" warnName="o_sub_id" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mO_id">o_id</label>
             <div class="controls">
			    <input type="text" placeholder="o_id" value="${hsuborder.o_id}" id="mO_id" isNotNull="true" warnName="o_id" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mC_id">c_id</label>
             <div class="controls">
			    <input type="text" placeholder="c_id" value="${hsuborder.c_id}" id="mC_id" isNotNull="true" warnName="c_id" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mSub_id">sub_id</label>
             <div class="controls">
			    <input type="text" placeholder="sub_id" value="${hsuborder.sub_id}" id="mSub_id" isNotNull="true" warnName="sub_id" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mAmount">amount</label>
             <div class="controls">
			    <input type="text" placeholder="amount" value="${hsuborder.amount}" id="mAmount" isNotNull="true" warnName="amount" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mElectric">electric</label>
             <div class="controls">
			    <input type="text" placeholder="electric" value="${hsuborder.electric}" id="mElectric" isNotNull="true" warnName="electric" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mPay_status">pay_status</label>
             <div class="controls">
			    <input type="text" placeholder="pay_status" value="${hsuborder.pay_status}" id="mPay_status" isNotNull="true" warnName="pay_status" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mPay_time">pay_time</label>
             <div class="controls">
		     	<input type="text" placeholder="pay_time" value="<fmt:formatDate value="${hsuborder.pay_time}" type="both"/>" id="mPay_time" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="pay_time" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mTick_off_status">tick_off_status</label>
             <div class="controls">
			    <input type="text" placeholder="tick_off_status" value="${hsuborder.tick_off_status}" id="mTick_off_status" isNotNull="true" warnName="tick_off_status" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mTick_off_time">tick_off_time</label>
             <div class="controls">
		     	<input type="text" placeholder="tick_off_time" value="<fmt:formatDate value="${hsuborder.tick_off_time}" type="both"/>" id="mTick_off_time" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="tick_off_time" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mCreate_time">create_time</label>
             <div class="controls">
		     	<input type="text" placeholder="create_time" value="<fmt:formatDate value="${hsuborder.create_time}" type="both"/>" id="mCreate_time" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="create_time" />
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
		var o_sub_id = getVal("mO_sub_id");
		var o_id = getVal("mO_id");
		var c_id = getVal("mC_id");
		var sub_id = getVal("mSub_id");
		var amount = getVal("mAmount");
		var electric = getVal("mElectric");
		var pay_status = getVal("mPay_status");
		var pay_time = getVal("mPay_time");
		var tick_off_status = getVal("mTick_off_status");
		var tick_off_time = getVal("mTick_off_time");
		var create_time = getVal("mCreate_time");
		var flag = validateForm('updateForm');
	    if (flag){ 
	        $.post("<c:url value='/hSubOrder/updateHSubOrder'/>",
	        	{
		    		o_sub_id : o_sub_id,
		    		o_id : o_id,
		    		c_id : c_id,
		    		sub_id : sub_id,
		    		amount : amount,
		    		electric : electric,
		    		pay_status : pay_status,
		    		pay_time : pay_time,
		    		tick_off_status : tick_off_status,
		    		tick_off_time : tick_off_time,
		    		create_time : create_time,
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