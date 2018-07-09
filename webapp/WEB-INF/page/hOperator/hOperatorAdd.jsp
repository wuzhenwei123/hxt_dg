<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>添加运营商</h4>
</div>
<div class="modal-body">
    <form id="addForm" class="well well-nice form-horizontal">
         <div class="control-group">
             <label class="control-label" for="aFull_name">全称</label>
             <div class="controls">
			    <input type="text" placeholder="全称" id="aFull_name" isNotNull="true" warnName="全称" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aName">简称</label>
             <div class="controls">
			    <input type="text" placeholder="简称" id="aName" isNotNull="true" warnName="简称" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aProvince_code">省</label>
             <div class="controls">
             	<select id="aProvince_code"></select>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aCity_code">市</label>
             <div class="controls">
             	<select id="aCity_code"></select>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aArea_code">区</label>
             <div class="controls">
             	<select id="aArea_code"></select>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aState">状态</label>
             <div class="controls">
		    	<select id="aState"> 
                    <option value="1">正常</option> 
                    <option value="0">暂停</option> 
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
	$(document).ready(function(){
// 		$gov.init("aProvince_code","aCity_code","aArea_code");
		new $gov("aProvince_code","aCity_code","aArea_code").init();
	});
	//执行添加
	function add(){
		var id = getVal("aId");
		var full_name = getVal("aFull_name");
		var name = getVal("aName");
		var province_name = getVal("aProvince_name");
		var province_code = getVal("aProvince_code");
		var city_code = getVal("aCity_code");
		var city_name = getVal("aCity_name");
		var area_code = getVal("aArea_code");
		var area_name = getVal("aArea_name");
		var state = getVal("aState");
		var createTime = getVal("aCreateTime");
		var adminId = getVal("aAdminId");
		var lastTime = getVal("aLastTime");
		var lastAdminId = getVal("aLastAdminId");
		var flag = validateForm('addForm');
	    if (flag){ 
	        $.post("<c:url value='/hOperator/addHOperator'/>",
	        	{
		    		id : id,
		    		full_name : full_name,
		    		name : name,
		    		province_name : province_name,
		    		province_code : province_code,
		    		city_code : city_code,
		    		city_name : city_name,
		    		area_code : area_code,
		    		area_name : area_name,
		    		state : state,
		    		createTime : createTime,
		    		adminId : adminId,
		    		lastTime : lastTime,
		    		lastAdminId : lastAdminId,
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