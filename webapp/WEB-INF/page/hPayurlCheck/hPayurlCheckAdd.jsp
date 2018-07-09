<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>添加HPayurlCheck</h4>
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
             <label class="control-label" for="aC_id">c_id</label>
             <div class="controls">
			    <input type="text" placeholder="c_id" id="aC_id" isNotNull="true" warnName="c_id" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aCheck_no">check_no</label>
             <div class="controls">
			    <input type="text" placeholder="check_no" id="aCheck_no" isNotNull="true" warnName="check_no" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aCheck_url">check_url</label>
             <div class="controls">
			    <input type="text" placeholder="check_url" id="aCheck_url" isNotNull="true" warnName="check_url" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aPay_url">pay_url</label>
             <div class="controls">
			    <input type="text" placeholder="pay_url" id="aPay_url" isNotNull="true" warnName="pay_url" />
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
             <label class="control-label" for="aCreate_date">create_date</label>
             <div class="controls">
			    <input type="text" placeholder="create_date" id="aCreate_date" isNotNull="true" warnName="create_date" />
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
             <label class="control-label" for="aCheck_time">check_time</label>
             <div class="controls">
		     	<input type="text" placeholder="check_time" id="aCheck_time" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="check_time" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aOpen_ip">open_ip</label>
             <div class="controls">
			    <input type="text" placeholder="open_ip" id="aOpen_ip" isNotNull="true" warnName="open_ip" />
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
		var c_id = getVal("aC_id");
		var check_no = getVal("aCheck_no");
		var check_url = getVal("aCheck_url");
		var pay_url = getVal("aPay_url");
		var status = getVal("aStatus");
		var create_date = getVal("aCreate_date");
		var create_time = getVal("aCreate_time");
		var check_time = getVal("aCheck_time");
		var open_ip = getVal("aOpen_ip");
		var flag = validateForm('addForm');
	    if (flag){ 
	        $.post("<c:url value='/hPayurlCheck/addHPayurlCheck'/>",
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
		              	tipOk("保存成功!");
		             } else {
		            	 tipError(result.message);
		             }
	              	$modal.modal('hide');
	        });
	    }
	}
</script>