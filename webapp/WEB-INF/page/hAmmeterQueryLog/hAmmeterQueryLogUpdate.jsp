<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>编辑HAmmeterQueryLog</h4>
</div>
<div class="modal-body">
    <form id="updateForm" class="well well-nice form-horizontal">
         <div class="control-group">
             <label class="control-label" for="mId">id</label>
             <div class="controls">
			    <input type="text" placeholder="id" value="${hammeterquerylog.id}" id="mId" isNotNull="true" warnName="id" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mCreateTime">createTime</label>
             <div class="controls">
		     	<input type="text" placeholder="createTime" value="<fmt:formatDate value="${hammeterquerylog.createTime}" type="both"/>" id="mCreateTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="createTime" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mAmmeterNo">ammeterNo</label>
             <div class="controls">
			    <input type="text" placeholder="ammeterNo" value="${hammeterquerylog.ammeterNo}" id="mAmmeterNo" isNotNull="true" warnName="ammeterNo" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mPhone">phone</label>
             <div class="controls">
			    <input type="text" placeholder="phone" value="${hammeterquerylog.phone}" id="mPhone" isNotNull="true" warnName="phone" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mIp">ip</label>
             <div class="controls">
			    <input type="text" placeholder="ip" value="${hammeterquerylog.ip}" id="mIp" isNotNull="true" warnName="ip" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mAmmeter_address">ammeter_address</label>
             <div class="controls">
			    <input type="text" placeholder="ammeter_address" value="${hammeterquerylog.ammeter_address}" id="mAmmeter_address" isNotNull="true" warnName="ammeter_address" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mAmmeter_name">ammeter_name</label>
             <div class="controls">
			    <input type="text" placeholder="ammeter_name" value="${hammeterquerylog.ammeter_name}" id="mAmmeter_name" isNotNull="true" warnName="ammeter_name" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mFee">fee</label>
             <div class="controls">
			    <input type="text" placeholder="fee" value="${hammeterquerylog.fee}" id="mFee" isNotNull="true" warnName="fee" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mZnFee">znFee</label>
             <div class="controls">
			    <input type="text" placeholder="znFee" value="${hammeterquerylog.znFee}" id="mZnFee" isNotNull="true" warnName="znFee" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mTotalFee">totalFee</label>
             <div class="controls">
			    <input type="text" placeholder="totalFee" value="${hammeterquerylog.totalFee}" id="mTotalFee" isNotNull="true" warnName="totalFee" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mRemark1">remark1</label>
             <div class="controls">
			    <input type="text" placeholder="remark1" value="${hammeterquerylog.remark1}" id="mRemark1" isNotNull="true" warnName="remark1" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mRemark2">remark2</label>
             <div class="controls">
			    <input type="text" placeholder="remark2" value="${hammeterquerylog.remark2}" id="mRemark2" isNotNull="true" warnName="remark2" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mRemark3">remark3</label>
             <div class="controls">
			    <input type="text" placeholder="remark3" value="${hammeterquerylog.remark3}" id="mRemark3" isNotNull="true" warnName="remark3" />
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
		var createTime = getVal("mCreateTime");
		var ammeterNo = getVal("mAmmeterNo");
		var phone = getVal("mPhone");
		var ip = getVal("mIp");
		var ammeter_address = getVal("mAmmeter_address");
		var ammeter_name = getVal("mAmmeter_name");
		var fee = getVal("mFee");
		var znFee = getVal("mZnFee");
		var totalFee = getVal("mTotalFee");
		var remark1 = getVal("mRemark1");
		var remark2 = getVal("mRemark2");
		var remark3 = getVal("mRemark3");
		var flag = validateForm('updateForm');
	    if (flag){ 
	        $.post("<c:url value='/hAmmeterQueryLog/updateHAmmeterQueryLog'/>",
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
		              	tipOk("更新成功!");
		             } else {
		            	 tipError(result.message);
		             }
		            $modal.modal('hide');
	        });
	    }
	}
</script>