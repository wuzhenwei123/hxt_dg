<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>添加HAmmeterQueryLog</h4>
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
             <label class="control-label" for="aCreateTime">createTime</label>
             <div class="controls">
		     	<input type="text" placeholder="createTime" id="aCreateTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="createTime" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aAmmeterNo">ammeterNo</label>
             <div class="controls">
			    <input type="text" placeholder="ammeterNo" id="aAmmeterNo" isNotNull="true" warnName="ammeterNo" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aPhone">phone</label>
             <div class="controls">
			    <input type="text" placeholder="phone" id="aPhone" isNotNull="true" warnName="phone" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aIp">ip</label>
             <div class="controls">
			    <input type="text" placeholder="ip" id="aIp" isNotNull="true" warnName="ip" />
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
             <label class="control-label" for="aAmmeter_name">ammeter_name</label>
             <div class="controls">
			    <input type="text" placeholder="ammeter_name" id="aAmmeter_name" isNotNull="true" warnName="ammeter_name" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aFee">fee</label>
             <div class="controls">
			    <input type="text" placeholder="fee" id="aFee" isNotNull="true" warnName="fee" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aZnFee">znFee</label>
             <div class="controls">
			    <input type="text" placeholder="znFee" id="aZnFee" isNotNull="true" warnName="znFee" />
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
		var createTime = getVal("aCreateTime");
		var ammeterNo = getVal("aAmmeterNo");
		var phone = getVal("aPhone");
		var ip = getVal("aIp");
		var ammeter_address = getVal("aAmmeter_address");
		var ammeter_name = getVal("aAmmeter_name");
		var fee = getVal("aFee");
		var znFee = getVal("aZnFee");
		var totalFee = getVal("aTotalFee");
		var remark1 = getVal("aRemark1");
		var remark2 = getVal("aRemark2");
		var remark3 = getVal("aRemark3");
		var flag = validateForm('addForm');
	    if (flag){ 
	        $.post("<c:url value='/hAmmeterQueryLog/addHAmmeterQueryLog'/>",
	        	{
		    		id : id,
		    		createTime : createTime,
		    		ammeterNo : ammeterNo,
		    		phone : phone,
		    		ip : ip,
		    		ammeter_address : ammeter_address,
		    		ammeter_name : ammeter_name,
		    		fee : fee,
		    		znFee : znFee,
		    		totalFee : totalFee,
		    		remark1 : remark1,
		    		remark2 : remark2,
		    		remark3 : remark3,
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