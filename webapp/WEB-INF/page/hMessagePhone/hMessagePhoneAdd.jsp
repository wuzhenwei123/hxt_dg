<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>添加手机号</h4>
</div>
<div class="modal-body">
    <form id="addForm" class="well well-nice form-horizontal">
         <div class="control-group">
             <label class="control-label" for="aPhone">手机号</label>
             <div class="controls">
			    <input type="text" id="aPhone" isNotNull="true" warnName="手机号" />
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
		var phone = getVal("aPhone");
		if(!validatemobile(phone)){
			tipError("请输入正确的手机号");
			return false;
		}
		var remark1 = getVal("aRemark1");
		var remark2 = getVal("aRemark2");
		var remark3 = getVal("aRemark3");
		var flag = validateForm('addForm');
	    if (flag){ 
	        $.post("<c:url value='/hMessagePhone/addHMessagePhone'/>",
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
		              	tipOk("保存成功!");
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