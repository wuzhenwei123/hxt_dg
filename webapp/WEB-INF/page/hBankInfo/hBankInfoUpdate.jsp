<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>编辑HBankInfo</h4>
</div>
<div class="modal-body">
    <form id="updateForm" class="well well-nice form-horizontal">
         <div class="control-group">
             <label class="control-label" for="mId">id</label>
             <div class="controls">
			    <input type="text" placeholder="id" value="${hbankinfo.id}" id="mId" isNotNull="true" warnName="id" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mName">name</label>
             <div class="controls">
			    <input type="text" placeholder="name" value="${hbankinfo.name}" id="mName" isNotNull="true" warnName="name" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mCode">code</label>
             <div class="controls">
			    <input type="text" placeholder="code" value="${hbankinfo.code}" id="mCode" isNotNull="true" warnName="code" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mBankNum">bankNum</label>
             <div class="controls">
			    <input type="text" placeholder="bankNum" value="${hbankinfo.bankNum}" id="mBankNum" isNotNull="true" warnName="bankNum" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mClearBankNum">clearBankNum</label>
             <div class="controls">
			    <input type="text" placeholder="clearBankNum" value="${hbankinfo.clearBankNum}" id="mClearBankNum" isNotNull="true" warnName="clearBankNum" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mRemark1">remark1</label>
             <div class="controls">
			    <input type="text" placeholder="remark1" value="${hbankinfo.remark1}" id="mRemark1" isNotNull="true" warnName="remark1" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mRemark2">remark2</label>
             <div class="controls">
			    <input type="text" placeholder="remark2" value="${hbankinfo.remark2}" id="mRemark2" isNotNull="true" warnName="remark2" />
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
		var name = getVal("mName");
		var code = getVal("mCode");
		var bankNum = getVal("mBankNum");
		var clearBankNum = getVal("mClearBankNum");
		var remark1 = getVal("mRemark1");
		var remark2 = getVal("mRemark2");
		var flag = validateForm('updateForm');
	    if (flag){ 
	        $.post("<c:url value='/hBankInfo/updateHBankInfo'/>",
	        	{
		    		id : id,
		    		name : name,
		    		code : code,
		    		bankNum : bankNum,
		    		clearBankNum : clearBankNum,
		    		remark1 : remark1,
		    		remark2 : remark2,
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