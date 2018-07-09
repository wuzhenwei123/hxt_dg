<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>编辑HArea</h4>
</div>
<div class="modal-body">
    <form id="updateForm" class="well well-nice form-horizontal">
         <div class="control-group">
             <label class="control-label" for="mId">id</label>
             <div class="controls">
			    <input type="text" placeholder="id" value="${harea.id}" id="mId" isNotNull="true" warnName="id" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mAreaCode">areaCode</label>
             <div class="controls">
			    <input type="text" placeholder="areaCode" value="${harea.areaCode}" id="mAreaCode" isNotNull="true" warnName="areaCode" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mAreaName">areaName</label>
             <div class="controls">
			    <input type="text" placeholder="areaName" value="${harea.areaName}" id="mAreaName" isNotNull="true" warnName="areaName" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mCityCode">cityCode</label>
             <div class="controls">
			    <input type="text" placeholder="cityCode" value="${harea.cityCode}" id="mCityCode" isNotNull="true" warnName="cityCode" />
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
		var areaCode = getVal("mAreaCode");
		var areaName = getVal("mAreaName");
		var cityCode = getVal("mCityCode");
		var flag = validateForm('updateForm');
	    if (flag){ 
	        $.post("<c:url value='/hArea/updateHArea'/>",
	        	{
		    		id : id,
		    		areaCode : areaCode,
		    		areaName : areaName,
		    		cityCode : cityCode,
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