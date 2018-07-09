<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>编辑代理商</h4>
</div>
<div class="modal-body">
    <form id="updateForm" class="well well-nice form-horizontal">
	     <input type="hidden" placeholder="id" value="${hoperator.id}" id="mId" isNotNull="true" warnName="id" />
         <div class="control-group">
             <label class="control-label" for="mFull_name">全称</label>
             <div class="controls">
			    <input type="text" placeholder="全称" value="${hoperator.full_name}" id="mFull_name" isNotNull="true" warnName="全称" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mName">简称</label>
             <div class="controls">
			    <input type="text" placeholder="简称" value="${hoperator.name}" id="mName" isNotNull="true" warnName="简称" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mProvince_code">省</label>
             <div class="controls">
			    <input type="text" placeholder="省" readonly="readonly" value="${hoperator.province_name}" id="mProvince_name" />
			    <select id="mProvince_code" style="display: none;" gov-value="${hoperator.province_code}">
			    </select>
                 <span class="help-inline text-red">*</span>
			    <input type="button" class="btn mod" value="修改" onclick="modify()">
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mCity_name">市</label>
             <div class="controls">
			    <input type="text" placeholder="city_name" readonly="readonly" value="${hoperator.city_name}" id="mCity_name"/>
			    <select id="mCity_code" style="display: none;" gov-value="${hoperator.city_code}"></select>
                 <span class="help-inline text-red">*</span>
                 <input type="button" class="btn mod" value="修改" onclick="modify()">
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mArea_code">区</label>
             <div class="controls">
			    <input type="text" placeholder="area_name" readonly="readonly" value="${hoperator.area_name}" id="mArea_name" />
			    <select id="mArea_code" style="display: none;" gov-value="${hoperator.area_code}"></select>
                 <span class="help-inline text-red">*</span>
                 <input type="button" class="btn mod" value="修改" onclick="modify()">
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mState">状态</label>
             <div class="controls">
		    	 <select id="mState" isNotNull="true" warnName="状态"> 
                    <option <c:if test="${hoperator.state == 1}" >selected="selected"</c:if>  value="1">正常</option> 
                    <option <c:if test="${hoperator.state == 0}" >selected="selected"</c:if> value="0">暂停</option> 
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
	function modify(){
		$(".mod").hide();
		
		$("#mProvince_code").show();
		$("#mCity_code").show();
		$("#mArea_code").show();
		//isNotNull="true" warnName="简称" 
		$("#mProvince_code").attr("isNotNull","true")
		$("#mProvince_code").attr("warnName","省")
		
		$("#mCity_code").attr("isNotNull","true")
		$("#mCity_code").attr("warnName","市")
		
		$("#mArea_code").attr("isNotNull","true")
		$("#mArea_code").attr("warnName","区")
		
		$("#mProvince_name").hide();
		$("#mCity_name").hide();
		$("#mArea_name").hide();
		
		new $gov("mProvince_code","mCity_code","mArea_code").init();
	}
	// 执行更新
	function update(){
		var id = getVal("mId");
		var full_name = getVal("mFull_name");
		var name = getVal("mName");
		var province_name = getVal("mProvince_name");
		var province_code = getVal("mProvince_code");
		var city_code = getVal("mCity_code");
		var city_name = getVal("mCity_name");
		var area_code = getVal("mArea_code");
		var area_name = getVal("mArea_name");
		var state = getVal("mState");
		var createTime = getVal("mCreateTime");
		var adminId = getVal("mAdminId");
		var lastTime = getVal("mLastTime");
		var lastAdminId = getVal("mLastAdminId");
		var flag = validateForm('updateForm');
	    if (flag){ 
	        $.post("<c:url value='/hOperator/updateHOperator'/>",
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
		              	tipOk("更新成功!");
		             } else {
		            	 tipError(result.message);
		             }
		            $modal.modal('hide');
	        });
	    }
	}
</script>