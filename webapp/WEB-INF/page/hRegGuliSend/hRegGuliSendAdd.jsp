<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>添加HRegGuliSend</h4>
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
             <label class="control-label" for="aSendTime">sendTime</label>
             <div class="controls">
		     	<input type="text" placeholder="sendTime" id="aSendTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="sendTime" />
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
             <label class="control-label" for="aCompanyId">companyId</label>
             <div class="controls">
			    <input type="text" placeholder="companyId" id="aCompanyId" isNotNull="true" warnName="companyId" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aCompanyName">companyName</label>
             <div class="controls">
			    <input type="text" placeholder="companyName" id="aCompanyName" isNotNull="true" warnName="companyName" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aAmmeter">ammeter</label>
             <div class="controls">
			    <input type="text" placeholder="ammeter" id="aAmmeter" isNotNull="true" warnName="ammeter" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aAgentTwoId">agentTwoId</label>
             <div class="controls">
			    <input type="text" placeholder="agentTwoId" id="aAgentTwoId" isNotNull="true" warnName="agentTwoId" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aAgentTwoName">agentTwoName</label>
             <div class="controls">
			    <input type="text" placeholder="agentTwoName" id="aAgentTwoName" isNotNull="true" warnName="agentTwoName" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aAgentId">agentId</label>
             <div class="controls">
			    <input type="text" placeholder="agentId" id="aAgentId" isNotNull="true" warnName="agentId" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aAgentName">agentName</label>
             <div class="controls">
			    <input type="text" placeholder="agentName" id="aAgentName" isNotNull="true" warnName="agentName" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aGuliId">guliId</label>
             <div class="controls">
			    <input type="text" placeholder="guliId" id="aGuliId" isNotNull="true" warnName="guliId" />
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
             <label class="control-label" for="aState">state</label>
             <div class="controls">
		    	<select id="aState"> 
                    <option value="1">正常</option> 
                    <option value="0">禁用</option> 
            	</select> 
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aInfo">info</label>
             <div class="controls">
			    <input type="text" placeholder="info" id="aInfo" isNotNull="true" warnName="info" />
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
		var sendTime = getVal("aSendTime");
		var createTime = getVal("aCreateTime");
		var companyId = getVal("aCompanyId");
		var companyName = getVal("aCompanyName");
		var ammeter = getVal("aAmmeter");
		var agentTwoId = getVal("aAgentTwoId");
		var agentTwoName = getVal("aAgentTwoName");
		var agentId = getVal("aAgentId");
		var agentName = getVal("aAgentName");
		var guliId = getVal("aGuliId");
		var fee = getVal("aFee");
		var state = getVal("aState");
		var info = getVal("aInfo");
		var flag = validateForm('addForm');
	    if (flag){ 
	        $.post("<c:url value='/hRegGuliSend/addHRegGuliSend'/>",
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
		              	tipOk("保存成功!");
		             } else {
		            	 tipError(result.message);
		             }
	              	$modal.modal('hide');
	        });
	    }
	}
</script>