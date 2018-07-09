<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>添加HRecRecord</h4>
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
             <label class="control-label" for="aAmmeter_no">ammeter_no</label>
             <div class="controls">
			    <input type="text" placeholder="ammeter_no" id="aAmmeter_no" isNotNull="true" warnName="ammeter_no" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aAmmeter_name">ammeter_name</label>
             <div class="controls">
			    <input type="text" placeholder="ammeter_name" id="aAmmeter_name" isNotNull="true" warnName="ammeter_name" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aAmmeter_type">ammeter_type</label>
             <div class="controls">
			    <input type="text" placeholder="ammeter_type" id="aAmmeter_type" isNotNull="true" warnName="ammeter_type" />
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
             <label class="control-label" for="aAmmeter_address">ammeter_address</label>
             <div class="controls">
			    <input type="text" placeholder="ammeter_address" id="aAmmeter_address" isNotNull="true" warnName="ammeter_address" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aAmmeterinfo_type">ammeterinfo_type</label>
             <div class="controls">
			    <input type="text" placeholder="ammeterinfo_type" id="aAmmeterinfo_type" isNotNull="true" warnName="ammeterinfo_type" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aRec_phone">rec_phone</label>
             <div class="controls">
			    <input type="text" placeholder="rec_phone" id="aRec_phone" isNotNull="true" warnName="rec_phone" />
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
		var ammeter_no = getVal("aAmmeter_no");
		var ammeter_name = getVal("aAmmeter_name");
		var ammeter_type = getVal("aAmmeter_type");
		var create_time = getVal("aCreate_time");
		var ammeter_address = getVal("aAmmeter_address");
		var ammeterinfo_type = getVal("aAmmeterinfo_type");
		var rec_phone = getVal("aRec_phone");
		var flag = validateForm('addForm');
	    if (flag){ 
	        $.post("<c:url value='/hRecRecord/addHRecRecord'/>",
	        	{
		    		id : id,
		    		ammeter_no : ammeter_no,
		    		ammeter_name : ammeter_name,
		    		ammeter_type : ammeter_type,
		    		create_time : create_time,
		    		ammeter_address : ammeter_address,
		    		ammeterinfo_type : ammeterinfo_type,
		    		rec_phone : rec_phone,
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