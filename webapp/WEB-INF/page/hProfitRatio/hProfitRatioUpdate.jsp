<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>编辑HProfitRatio</h4>
</div>
<div class="modal-body">
    <form id="updateForm" class="well well-nice form-horizontal">
	    <input type="hidden" placeholder="id" value="${hprofitratio.id}" id="mId" isNotNull="true" warnName="id" />
         <div class="control-group">
             <label class="control-label" for="mName">名称</label>
             <div class="controls">
			    <input type="text" placeholder="名称" value="${hprofitratio.name}" id="mName" isNotNull="true" warnName="名称" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
<!--          <div class="control-group"> -->
<!--              <label class="control-label" for="mManager_ratio">客户经理分润比例</label> -->
<!--              <div class="controls"> -->
<%-- 			    <input type="text" placeholder="客户经理分润比例" value="${hprofitratio.manager_ratio}" id="mManager_ratio" isNotNull="true" warnName="客户经理分润比例" /> --%>
<!--                  <span class="help-inline text-red">*</span> -->
<!--                  <span class="help-inline">0~1之间小数，如：0.5，代表50%</span> -->
<!--              </div> -->
<!--          </div> -->
         <div class="control-group">
             <label class="control-label" for="mOnt_agent_ratio">客户经理分润比例</label>
             <div class="controls">
			    <input type="text" placeholder="一级代理分润比例" value="${hprofitratio.ont_agent_ratio}" id="mOnt_agent_ratio" isNotNull="true" warnName="客户经理分润比例" />
                 <span class="help-inline text-red">*</span>
                 <span class="help-inline">0~1之间小数，如：0.5，代表50%</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mTwo_agent_ratio">代理分润比例</label>
             <div class="controls">
			    <input type="text" placeholder="二级代理分润比例" value="${hprofitratio.two_agent_ratio}" id="mTwo_agent_ratio" isNotNull="true" warnName="代理分润比例" />
                 <span class="help-inline text-red">*</span>
                 <span class="help-inline">0~1之间小数，如：0.5，代表50%</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mPersonal_ratio">服务人员分润比例</label>
             <div class="controls">
			    <input type="text" placeholder="服务人员分润比例" value="${hprofitratio.personal_ratio}" id="mPersonal_ratio" isNotNull="true" warnName="服务人员分润比例" />
                 <span class="help-inline text-red">*</span>
                 <span class="help-inline">0~1之间小数，如：0.5，代表50%</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mIs_default">是否默认</label>
             <div class="controls">
             	<select id="mIs_default"> 
                    <option <c:if test="${hprofitratio.is_default == 1}" >selected="selected"</c:if>  value="1">默认</option> 
                    <option <c:if test="${hprofitratio.is_default == 0}" >selected="selected"</c:if> value="0">非默认</option> 
            	</select> 
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mState">状态</label>
             <div class="controls">
		    	 <select id="mState" isNotNull="true" warnName="状态"> 
                    <option <c:if test="${hprofitratio.state == 1}" >selected="selected"</c:if>  value="1">正常</option> 
                    <option <c:if test="${hprofitratio.state == 0}" >selected="selected"</c:if> value="0">禁用</option> 
            	</select> 
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
// 		var manager_ratio = getVal("mManager_ratio");
		var ont_agent_ratio = getVal("mOnt_agent_ratio");
		var two_agent_ratio = getVal("mTwo_agent_ratio");
		var personal_ratio = getVal("mPersonal_ratio");
		var is_default = getVal("mIs_default");
		var state = getVal("mState");
		var flag = validateForm('updateForm');
	    if (flag){ 
	        $.post("<c:url value='/hProfitRatio/updateHProfitRatio'/>",
	        	{
		    		id : id,
		    		name : name,
// 		    		manager_ratio : manager_ratio,
		    		ont_agent_ratio : ont_agent_ratio,
		    		two_agent_ratio : two_agent_ratio,
		    		personal_ratio : personal_ratio,
		    		is_default : is_default,
		    		state : state,
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