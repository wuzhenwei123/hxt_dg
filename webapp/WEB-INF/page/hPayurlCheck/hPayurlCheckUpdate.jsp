<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>编辑HPayurlCheck</h4>
</div>
<div class="modal-body">
    <form id="updateForm" class="well well-nice form-horizontal">
         <div class="control-group">
             <label class="control-label" for="mId">id</label>
             <div class="controls">
			    <input type="text" placeholder="id" value="${hpayurlcheck.id}" id="mId" isNotNull="true" warnName="id" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mC_id">c_id</label>
             <div class="controls">
			    <input type="text" placeholder="c_id" value="${hpayurlcheck.c_id}" id="mC_id" isNotNull="true" warnName="c_id" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mCheck_no">check_no</label>
             <div class="controls">
			    <input type="text" placeholder="check_no" value="${hpayurlcheck.check_no}" id="mCheck_no" isNotNull="true" warnName="check_no" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mCheck_url">check_url</label>
             <div class="controls">
			    <input type="text" placeholder="check_url" value="${hpayurlcheck.check_url}" id="mCheck_url" isNotNull="true" warnName="check_url" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mPay_url">pay_url</label>
             <div class="controls">
			    <input type="text" placeholder="pay_url" value="${hpayurlcheck.pay_url}" id="mPay_url" isNotNull="true" warnName="pay_url" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mStatus">status</label>
             <div class="controls">
			    <input type="text" placeholder="status" value="${hpayurlcheck.status}" id="mStatus" isNotNull="true" warnName="status" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mCreate_date">create_date</label>
             <div class="controls">
			    <input type="text" placeholder="create_date" value="${hpayurlcheck.create_date}" id="mCreate_date" isNotNull="true" warnName="create_date" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mCreate_time">create_time</label>
             <div class="controls">
		     	<input type="text" placeholder="create_time" value="<fmt:formatDate value="${hpayurlcheck.create_time}" type="both"/>" id="mCreate_time" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="create_time" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mCheck_time">check_time</label>
             <div class="controls">
		     	<input type="text" placeholder="check_time" value="<fmt:formatDate value="${hpayurlcheck.check_time}" type="both"/>" id="mCheck_time" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="check_time" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mOpen_ip">open_ip</label>
             <div class="controls">
			    <input type="text" placeholder="open_ip" value="${hpayurlcheck.open_ip}" id="mOpen_ip" isNotNull="true" warnName="open_ip" />
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
		var c_id = getVal("mC_id");
		var check_no = getVal("mCheck_no");
		var check_url = getVal("mCheck_url");
		var pay_url = getVal("mPay_url");
		var status = getVal("mStatus");
		var create_date = getVal("mCreate_date");
		var create_time = getVal("mCreate_time");
		var check_time = getVal("mCheck_time");
		var open_ip = getVal("mOpen_ip");
		var flag = validateForm('updateForm');
	    if (flag){ 
	        $.post("<c:url value='/hPayurlCheck/updateHPayurlCheck'/>",
	        	{
		    		id : id,
		    		c_id : c_id,
		    		check_no : check_no,
		    		check_url : check_url,
		    		pay_url : pay_url,
		    		status : status,
		    		create_date : create_date,
		    		create_time : create_time,
		    		check_time : check_time,
		    		open_ip : open_ip,
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