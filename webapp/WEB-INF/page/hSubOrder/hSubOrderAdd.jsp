<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>添加HSubOrder</h4>
</div>
<div class="modal-body">
    <form id="addForm" class="well well-nice form-horizontal">
         <div class="control-group">
             <label class="control-label" for="aO_sub_id">o_sub_id</label>
             <div class="controls">
			    <input type="text" placeholder="o_sub_id" id="aO_sub_id" isNotNull="true" warnName="o_sub_id" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aO_id">o_id</label>
             <div class="controls">
			    <input type="text" placeholder="o_id" id="aO_id" isNotNull="true" warnName="o_id" />
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
             <label class="control-label" for="aSub_id">sub_id</label>
             <div class="controls">
			    <input type="text" placeholder="sub_id" id="aSub_id" isNotNull="true" warnName="sub_id" />
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
             <label class="control-label" for="aElectric">electric</label>
             <div class="controls">
			    <input type="text" placeholder="electric" id="aElectric" isNotNull="true" warnName="electric" />
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
             <label class="control-label" for="aPay_time">pay_time</label>
             <div class="controls">
		     	<input type="text" placeholder="pay_time" id="aPay_time" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="pay_time" />
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
             <label class="control-label" for="aCreate_time">create_time</label>
             <div class="controls">
		     	<input type="text" placeholder="create_time" id="aCreate_time" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="create_time" />
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
		var o_sub_id = getVal("aO_sub_id");
		var o_id = getVal("aO_id");
		var c_id = getVal("aC_id");
		var sub_id = getVal("aSub_id");
		var amount = getVal("aAmount");
		var electric = getVal("aElectric");
		var pay_status = getVal("aPay_status");
		var pay_time = getVal("aPay_time");
		var tick_off_status = getVal("aTick_off_status");
		var tick_off_time = getVal("aTick_off_time");
		var create_time = getVal("aCreate_time");
		var flag = validateForm('addForm');
	    if (flag){ 
	        $.post("<c:url value='/hSubOrder/addHSubOrder'/>",
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
		              	tipOk("保存成功!");
		             } else {
		            	 tipError(result.message);
		             }
	              	$modal.modal('hide');
	        });
	    }
	}
</script>