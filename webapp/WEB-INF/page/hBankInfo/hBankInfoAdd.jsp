<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>添加HBankInfo</h4>
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
             <label class="control-label" for="aName">name</label>
             <div class="controls">
			    <input type="text" placeholder="name" id="aName" isNotNull="true" warnName="name" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aCode">code</label>
             <div class="controls">
			    <input type="text" placeholder="code" id="aCode" isNotNull="true" warnName="code" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aBankNum">bankNum</label>
             <div class="controls">
			    <input type="text" placeholder="bankNum" id="aBankNum" isNotNull="true" warnName="bankNum" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aClearBankNum">clearBankNum</label>
             <div class="controls">
			    <input type="text" placeholder="clearBankNum" id="aClearBankNum" isNotNull="true" warnName="clearBankNum" />
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
		var name = getVal("aName");
		var code = getVal("aCode");
		var bankNum = getVal("aBankNum");
		var clearBankNum = getVal("aClearBankNum");
		var remark1 = getVal("aRemark1");
		var remark2 = getVal("aRemark2");
		var flag = validateForm('addForm');
	    if (flag){ 
	        $.post("<c:url value='/hBankInfo/addHBankInfo'/>",
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
		              	tipOk("保存成功!");
		             } else {
		            	 tipError(result.message);
		             }
	              	$modal.modal('hide');
	        });
	    }
	}
</script>