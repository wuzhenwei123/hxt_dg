<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>编辑手机号</h4>
</div>
<div class="modal-body">
	<input type="hidden" placeholder="id" value="${hmessagephone.id}" id="mId" isNotNull="true" warnName="id" />
    <form id="updateForm" class="well well-nice form-horizontal">
         <div class="control-group">
             <label class="control-label" for="mPhone">手机号</label>
             <div class="controls">
			    <input type="text" value="${hmessagephone.phone}" id="mPhone" isNotNull="true" warnName="手机号" />
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
		var phone = getVal("mPhone");
		if(!validatemobile(phone)){
			tipError("请输入正确的手机号");
			return false;
		}
		var remark1 = getVal("mRemark1");
		var remark2 = getVal("mRemark2");
		var remark3 = getVal("mRemark3");
		var flag = validateForm('updateForm');
	    if (flag){ 
	        $.post("<c:url value='/hMessagePhone/updateHMessagePhone'/>",
	        	{
		    		id : id,
		    		phone : phone,
		    		remark1 : remark1,
		    		remark2 : remark2,
		    		remark3 : remark3,
				 _t:Math.random()},
	        	function(data){
		        	var result = eval('('+data+')'); 
		            if (result.code == '1') {
		              	var pageNo = $("#currPage").val();           
// 		              	searchData(pageNo);
		              	tipOk("更新成功!");
		              	window.location.href="${ctx}/hMessagePhone/index";
		             } else {
		            	 tipError(result.message);
		             }
		            $modal.modal('hide');
	        });
	    }
	}
	function validatemobile(mobile){ 
		if (mobile.length == 0) {
			return false;
		}
		if (mobile.length != 11) {
			return false;
		}
		var myreg = /^(0|86|17951)?(13[0-9]|15[012356789]|17[012356789]|18[0-9]|14[012356789])[0-9]{8}$/;
		if (!myreg.test(mobile)) {
			return false;
		}
		return true;
	}
</script>