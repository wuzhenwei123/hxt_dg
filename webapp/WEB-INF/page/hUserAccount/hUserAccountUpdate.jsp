<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>编辑</h4>
</div>
<div class="modal-body">
    <form id="updateForm" class="well well-nice form-horizontal">
	    <input type="hidden" placeholder="id" value="${huseraccount.id}" id="mId" isNotNull="true" warnName="id" />
         <div class="control-group">
             <label class="control-label" for="mTotalFee">账户余额</label>
             <div class="controls">
			    <input type="text" placeholder="totalFee" value="${huseraccount.totalFee}" id="mTotalFee" isNotNull="true" warnName="totalFee" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mStatus">状态</label>
             <div class="controls">
             	<select id="mStatus">
			    	<option value="1" <c:if test="${huseraccount.status==1}">selected='selected'</c:if>>正常</option>
			    	<option value="0" <c:if test="${huseraccount.status==0}">selected='selected'</c:if>>冻结</option>
			    </select>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mPhone">电话</label>
             <div class="controls">
			    <input type="text" placeholder="phone" value="${huseraccount.phone}" id="mPhone" isNotNull="true" warnName="phone" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mMobile">手机</label>
             <div class="controls">
			    <input type="text" placeholder="mobile" value="${huseraccount.mobile}" id="mMobile" isNotNull="true" warnName="mobile" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mCompany_name">单位名称</label>
             <div class="controls">
			    <input type="text" placeholder="company_name" value="${huseraccount.company_name}" id="mCompany_name" isNotNull="true" warnName="company_name" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
<!--          <div class="control-group"> -->
<!--              <label class="control-label" for="mRole_id">role_id</label> -->
<!--              <div class="controls"> -->
<%-- 			    <input type="text" placeholder="role_id" value="${huseraccount.role_id}" id="mRole_id" isNotNull="true" warnName="role_id" /> --%>
<!--                  <span class="help-inline text-red">*</span> -->
<!--              </div> -->
<!--          </div> -->
<!--          <div class="control-group"> -->
<!--              <label class="control-label" for="mFp_state">fp_state</label> -->
<!--              <div class="controls"> -->
<%-- 			    <input type="text" placeholder="fp_state" value="${huseraccount.fp_state}" id="mFp_state" isNotNull="true" warnName="fp_state" /> --%>
<!--                  <span class="help-inline text-red">*</span> -->
<!--              </div> -->
<!--          </div> -->
         <div class="control-group">
             <label class="control-label" for="mRemark1">备注1</label>
             <div class="controls">
			    <input type="text" placeholder="remark1" value="${huseraccount.remark1}" id="mRemark1"/>
             </div>
         </div>
<!--          <div class="control-group"> -->
<!--              <label class="control-label" for="mRemark2">remark2</label> -->
<!--              <div class="controls"> -->
<%-- 			    <input type="text" placeholder="remark2" value="${huseraccount.remark2}" id="mRemark2" isNotNull="true" warnName="remark2" /> --%>
<!--                  <span class="help-inline text-red">*</span> -->
<!--              </div> -->
<!--          </div> -->
<!--          <div class="control-group"> -->
<!--              <label class="control-label" for="mRemark3">remark3</label> -->
<!--              <div class="controls"> -->
<%-- 			    <input type="text" placeholder="remark3" value="${huseraccount.remark3}" id="mRemark3" isNotNull="true" warnName="remark3" /> --%>
<!--                  <span class="help-inline text-red">*</span> -->
<!--              </div> -->
<!--          </div> -->
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
		var openId = getVal("mOpenId");
		var totalFee = getVal("mTotalFee");
		var createTime = getVal("mCreateTime");
		var status = getVal("mStatus");
		var oneAgentOpenId = getVal("mOneAgentOpenId");
		var twoAgentOpenId = getVal("mTwoAgentOpenId");
		var oneAgentName = getVal("mOneAgentName");
		var twoAgentName = getVal("mTwoAgentName");
		var nickName = getVal("mNickName");
		var phone = getVal("mPhone");
		var mobile = getVal("mMobile");
		var company_name = getVal("mCompany_name");
		var role_id = getVal("mRole_id");
		var fp_state = getVal("mFp_state");
		var remark1 = getVal("mRemark1");
		var remark2 = getVal("mRemark2");
		var remark3 = getVal("mRemark3");
		var flag = validateForm('updateForm');
	    if (flag){ 
	        $.post("<c:url value='/hUserAccount/updateHUserAccount'/>",
	        	{
		    		id : id,
		    		openId : openId,
		    		totalFee : totalFee,
		    		createTime : createTime,
		    		status : status,
		    		oneAgentOpenId : oneAgentOpenId,
		    		twoAgentOpenId : twoAgentOpenId,
		    		oneAgentName : oneAgentName,
		    		twoAgentName : twoAgentName,
		    		nickName : nickName,
		    		phone : phone,
		    		mobile : mobile,
		    		company_name : company_name,
		    		role_id : role_id,
		    		fp_state : fp_state,
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