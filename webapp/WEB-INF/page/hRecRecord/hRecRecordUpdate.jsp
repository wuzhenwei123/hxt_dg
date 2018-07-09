<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>编辑HRecRecord</h4>
</div>
<div class="modal-body">
    <form id="updateForm" class="well well-nice form-horizontal">
         <div class="control-group">
             <label class="control-label" for="mId">id</label>
             <div class="controls">
			    <input type="text" placeholder="id" value="${hrecrecord.id}" id="mId" isNotNull="true" warnName="id" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mAmmeter_no">ammeter_no</label>
             <div class="controls">
			    <input type="text" placeholder="ammeter_no" value="${hrecrecord.ammeter_no}" id="mAmmeter_no" isNotNull="true" warnName="ammeter_no" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mAmmeter_name">ammeter_name</label>
             <div class="controls">
			    <input type="text" placeholder="ammeter_name" value="${hrecrecord.ammeter_name}" id="mAmmeter_name" isNotNull="true" warnName="ammeter_name" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mAmmeter_type">ammeter_type</label>
             <div class="controls">
			    <input type="text" placeholder="ammeter_type" value="${hrecrecord.ammeter_type}" id="mAmmeter_type" isNotNull="true" warnName="ammeter_type" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mCreate_time">create_time</label>
             <div class="controls">
		     	<input type="text" placeholder="create_time" value="<fmt:formatDate value="${hrecrecord.create_time}" type="both"/>" id="mCreate_time" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="create_time" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mAmmeter_address">ammeter_address</label>
             <div class="controls">
			    <input type="text" placeholder="ammeter_address" value="${hrecrecord.ammeter_address}" id="mAmmeter_address" isNotNull="true" warnName="ammeter_address" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mAmmeterinfo_type">ammeterinfo_type</label>
             <div class="controls">
			    <input type="text" placeholder="ammeterinfo_type" value="${hrecrecord.ammeterinfo_type}" id="mAmmeterinfo_type" isNotNull="true" warnName="ammeterinfo_type" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mRec_phone">rec_phone</label>
             <div class="controls">
			    <input type="text" placeholder="rec_phone" value="${hrecrecord.rec_phone}" id="mRec_phone" isNotNull="true" warnName="rec_phone" />
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
		var ammeter_no = getVal("mAmmeter_no");
		var ammeter_name = getVal("mAmmeter_name");
		var ammeter_type = getVal("mAmmeter_type");
		var create_time = getVal("mCreate_time");
		var ammeter_address = getVal("mAmmeter_address");
		var ammeterinfo_type = getVal("mAmmeterinfo_type");
		var rec_phone = getVal("mRec_phone");
		var flag = validateForm('updateForm');
	    if (flag){ 
	        $.post("<c:url value='/hRecRecord/updateHRecRecord'/>",
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
		              	tipOk("更新成功!");
		             } else {
		            	 tipError(result.message);
		             }
		            $modal.modal('hide');
	        });
	    }
	}
</script>