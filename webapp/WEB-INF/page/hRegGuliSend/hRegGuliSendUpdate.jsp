<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>编辑HRegGuliSend</h4>
</div>
<div class="modal-body">
    <form id="updateForm" class="well well-nice form-horizontal">
         <div class="control-group">
             <label class="control-label" for="mId">id</label>
             <div class="controls">
			    <input type="text" placeholder="id" value="${hreggulisend.id}" id="mId" isNotNull="true" warnName="id" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mSendTime">sendTime</label>
             <div class="controls">
		     	<input type="text" placeholder="sendTime" value="<fmt:formatDate value="${hreggulisend.sendTime}" type="both"/>" id="mSendTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="sendTime" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mCreateTime">createTime</label>
             <div class="controls">
		     	<input type="text" placeholder="createTime" value="<fmt:formatDate value="${hreggulisend.createTime}" type="both"/>" id="mCreateTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="createTime" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mCompanyId">companyId</label>
             <div class="controls">
			    <input type="text" placeholder="companyId" value="${hreggulisend.companyId}" id="mCompanyId" isNotNull="true" warnName="companyId" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mCompanyName">companyName</label>
             <div class="controls">
			    <input type="text" placeholder="companyName" value="${hreggulisend.companyName}" id="mCompanyName" isNotNull="true" warnName="companyName" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mAmmeter">ammeter</label>
             <div class="controls">
			    <input type="text" placeholder="ammeter" value="${hreggulisend.ammeter}" id="mAmmeter" isNotNull="true" warnName="ammeter" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mAgentTwoId">agentTwoId</label>
             <div class="controls">
			    <input type="text" placeholder="agentTwoId" value="${hreggulisend.agentTwoId}" id="mAgentTwoId" isNotNull="true" warnName="agentTwoId" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mAgentTwoName">agentTwoName</label>
             <div class="controls">
			    <input type="text" placeholder="agentTwoName" value="${hreggulisend.agentTwoName}" id="mAgentTwoName" isNotNull="true" warnName="agentTwoName" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mAgentId">agentId</label>
             <div class="controls">
			    <input type="text" placeholder="agentId" value="${hreggulisend.agentId}" id="mAgentId" isNotNull="true" warnName="agentId" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mAgentName">agentName</label>
             <div class="controls">
			    <input type="text" placeholder="agentName" value="${hreggulisend.agentName}" id="mAgentName" isNotNull="true" warnName="agentName" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mGuliId">guliId</label>
             <div class="controls">
			    <input type="text" placeholder="guliId" value="${hreggulisend.guliId}" id="mGuliId" isNotNull="true" warnName="guliId" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mFee">fee</label>
             <div class="controls">
			    <input type="text" placeholder="fee" value="${hreggulisend.fee}" id="mFee" isNotNull="true" warnName="fee" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mState">state</label>
             <div class="controls">
		    	 <select id="mState" isNotNull="true" warnName="状态"> 
                    <option <c:if test="${hreggulisend.state == 1}" >selected="selected"</c:if>  value="1">正常</option> 
                    <option <c:if test="${hreggulisend.state == 0}" >selected="selected"</c:if> value="0">禁用</option> 
            	</select> 
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mInfo">info</label>
             <div class="controls">
			    <input type="text" placeholder="info" value="${hreggulisend.info}" id="mInfo" isNotNull="true" warnName="info" />
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
		var sendTime = getVal("mSendTime");
		var createTime = getVal("mCreateTime");
		var companyId = getVal("mCompanyId");
		var companyName = getVal("mCompanyName");
		var ammeter = getVal("mAmmeter");
		var agentTwoId = getVal("mAgentTwoId");
		var agentTwoName = getVal("mAgentTwoName");
		var agentId = getVal("mAgentId");
		var agentName = getVal("mAgentName");
		var guliId = getVal("mGuliId");
		var fee = getVal("mFee");
		var state = getVal("mState");
		var info = getVal("mInfo");
		var flag = validateForm('updateForm');
	    if (flag){ 
	        $.post("<c:url value='/hRegGuliSend/updateHRegGuliSend'/>",
	        	{
		    		id : id,
		    		sendTime : sendTime,
		    		createTime : createTime,
		    		companyId : companyId,
		    		companyName : companyName,
		    		ammeter : ammeter,
		    		agentTwoId : agentTwoId,
		    		agentTwoName : agentTwoName,
		    		agentId : agentId,
		    		agentName : agentName,
		    		guliId : guliId,
		    		fee : fee,
		    		state : state,
		    		info : info,
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