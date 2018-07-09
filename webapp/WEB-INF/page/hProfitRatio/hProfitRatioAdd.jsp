<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>添加分润比例</h4>
</div>
<div class="modal-body">
    <form id="addForm" class="well well-nice form-horizontal">
         <div class="control-group">
             <label class="control-label" for="aName">名称</label>
             <div class="controls">
			    <input type="text" placeholder="名称" id="aName" isNotNull="true" warnName="名称" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
<!--          <div class="control-group"> -->
<!--              <label class="control-label" for="aManager_ratio">客户经理分润比例</label> -->
<!--              <div class="controls"> -->
<!-- 			    <input type="text" placeholder="客户经理分润比例" id="aManager_ratio" isNotNull="true" warnName="客户经理分润比例" /> -->
<!--                  <span class="help-inline text-red">*</span> -->
<!--                  <span class="help-inline">0~1之间小数，如：0.5，代表50%</span> -->
                 
<!--              </div> -->
<!--          </div> -->
         <div class="control-group">
             <label class="control-label" for="aOnt_agent_ratio">客户经理分润比例</label>
             <div class="controls">
			    <input type="text" placeholder="一级代理分润比例" id="aOnt_agent_ratio" isNotNull="true" warnName="客户经理分润比例" />
                 <span class="help-inline text-red">*</span>
                 <span class="help-inline">0~1之间小数，如：0.5，代表50%</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aTwo_agent_ratio">代理分润比例</label>
             <div class="controls">
			    <input type="text" placeholder="二级代理分润比例" id="aTwo_agent_ratio" isNotNull="true" warnName="代理分润比例" />
                 <span class="help-inline text-red">*</span>
                 <span class="help-inline">0~1之间小数，如：0.5，代表50%</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aPersonal_ratio">服务人员分润比例</label>
             <div class="controls">
			    <input type="text" placeholder="服务人员分润比例" id="aPersonal_ratio" isNotNull="true" warnName="服务人员分润比例" />
                 <span class="help-inline text-red">*</span>
                 <span class="help-inline">0~1之间小数，如：0.5，代表50%</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aIs_default">是否默认</label>
             <div class="controls">
             	<select id="aIs_default"> 
                    <option value="1">默认</option> 
                    <option value="0">非默认</option> 
            	</select> 
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aState">状态</label>
             <div class="controls">
		    	<select id="aState"> 
                    <option value="1">正常</option> 
                    <option value="0">禁用</option> 
            	</select> 
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
// 		var manager_ratio = getVal("aManager_ratio");
		var ont_agent_ratio = getVal("aOnt_agent_ratio");
		var two_agent_ratio = getVal("aTwo_agent_ratio");
		var personal_ratio = getVal("aPersonal_ratio");
		var is_default = getVal("aIs_default");
		var state = getVal("aState");
		var flag = validateForm('addForm');
	    if (flag){ 
	        $.post("<c:url value='/hProfitRatio/addHProfitRatio'/>",
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
		              	tipOk("保存成功!");
		             } else {
		            	 tipError(result.message);
		             }
	              	$modal.modal('hide');
	        });
	    }
	}
</script>