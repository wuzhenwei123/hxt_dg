<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>添加HCity</h4>
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
             <label class="control-label" for="aCityCode">cityCode</label>
             <div class="controls">
			    <input type="text" placeholder="cityCode" id="aCityCode" isNotNull="true" warnName="cityCode" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aCityName">cityName</label>
             <div class="controls">
			    <input type="text" placeholder="cityName" id="aCityName" isNotNull="true" warnName="cityName" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aProvinceCode">provinceCode</label>
             <div class="controls">
			    <input type="text" placeholder="provinceCode" id="aProvinceCode" isNotNull="true" warnName="provinceCode" />
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
		var cityCode = getVal("aCityCode");
		var cityName = getVal("aCityName");
		var provinceCode = getVal("aProvinceCode");
		var flag = validateForm('addForm');
	    if (flag){ 
	        $.post("<c:url value='/hCity/addHCity'/>",
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
		              	tipOk("保存成功!");
		             } else {
		            	 tipError(result.message);
		             }
	              	$modal.modal('hide');
	        });
	    }
	}
</script>