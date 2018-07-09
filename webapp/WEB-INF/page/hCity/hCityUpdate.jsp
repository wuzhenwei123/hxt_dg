<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>编辑HCity</h4>
</div>
<div class="modal-body">
    <form id="updateForm" class="well well-nice form-horizontal">
         <div class="control-group">
             <label class="control-label" for="mId">id</label>
             <div class="controls">
			    <input type="text" placeholder="id" value="${hcity.id}" id="mId" isNotNull="true" warnName="id" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mCityCode">cityCode</label>
             <div class="controls">
			    <input type="text" placeholder="cityCode" value="${hcity.cityCode}" id="mCityCode" isNotNull="true" warnName="cityCode" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mCityName">cityName</label>
             <div class="controls">
			    <input type="text" placeholder="cityName" value="${hcity.cityName}" id="mCityName" isNotNull="true" warnName="cityName" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mProvinceCode">provinceCode</label>
             <div class="controls">
			    <input type="text" placeholder="provinceCode" value="${hcity.provinceCode}" id="mProvinceCode" isNotNull="true" warnName="provinceCode" />
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
		var cityCode = getVal("mCityCode");
		var cityName = getVal("mCityName");
		var provinceCode = getVal("mProvinceCode");
		var flag = validateForm('updateForm');
	    if (flag){ 
	        $.post("<c:url value='/hCity/updateHCity'/>",
	        	{
		    		id : id,
		    		cityCode : cityCode,
		    		cityName : cityName,
		    		provinceCode : provinceCode,
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