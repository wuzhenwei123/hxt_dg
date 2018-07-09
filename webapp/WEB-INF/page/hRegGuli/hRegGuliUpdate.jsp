<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>编辑注册鼓励金政策</h4>
</div>
<div class="modal-body">
    	<input type="hidden" placeholder="id" value="${hregguli.id}" id="mId" isNotNull="true" warnName="id" />
    <form id="updateForm" class="well well-nice form-horizontal">
         <div class="control-group">
             <label class="control-label" for="mName">简称</label>
             <div class="controls">
			    <input type="text" placeholder="name" value="${hregguli.name}" id="mName" isNotNull="true" warnName="简称" />
                 <span class="help-inline text-red">最多20个汉字*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mStartTime">有效期开始时间</label>
             <div class="controls">
		     	<input type="text" placeholder="startTime" value="<fmt:formatDate value="${hregguli.startTime}" type="both"/>" id="mStartTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="有效期开始时间" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mEndTime">有效期结束时间</label>
             <div class="controls">
		     	<input type="text" placeholder="endTime" value="<fmt:formatDate value="${hregguli.endTime}" type="both"/>" id="mEndTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="有效期结束时间" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mFee">鼓励金金额</label>
             <div class="controls">
			    <input type="text" placeholder="fee" value="<fmt:formatNumber value="${hregguli.fee}" pattern="0.00" />" id="mFee" isNotNull="true" warnName="鼓励金金额" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mIsDefault">是否为默认政策</label>
             <div class="controls">
             	<select id="mIsDefault" isNotNull="true" warnName="状态"> 
                    <option <c:if test="${hregguli.isDefault == 0}" >selected="selected"</c:if> value="0">否</option> 
                    <option <c:if test="${hregguli.isDefault == 1}" >selected="selected"</c:if>  value="1">是</option> 
            	</select> 
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mState">状态</label>
             <div class="controls">
		    	 <select id="mState" isNotNull="true" warnName="状态"> 
                    <option <c:if test="${hregguli.state == 1}" >selected="selected"</c:if>  value="1">正常</option> 
                    <option <c:if test="${hregguli.state == 0}" >selected="selected"</c:if> value="0">禁用</option> 
            	</select> 
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mInfo">类型说明</label>
             <div class="controls">
             	<textarea rows="4" cols="4" id="mInfo" warnName="info">${hregguli.info}</textarea>
                 <span class="help-inline text-red">最多100个汉字*</span>
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
		var startTime = getVal("mStartTime");
		var endTime = getVal("mEndTime");
		var fee = getVal("mFee");
		var isDefault = getVal("mIsDefault");
		var state = getVal("mState");
		var createTime = getVal("mCreateTime");
		var updateTime = getVal("mUpdateTime");
		var stopTime = getVal("mStopTime");
		var createId = getVal("mCreateId");
		var updateId = getVal("mUpdateId");
		var info = getVal("mInfo");
		var flag = validateForm('updateForm');
		var reg = /[@#\$%\^&\*]+/g;
		if(reg.test(name)){
			tipError("简称含有非法字符，请修改");
			flag = false;
		}
		if(name.length>20){
			tipError("简称最多输入20个汉字");
			flag = false;
		}
		var reg1 = /^\d+(\.\d{2})?$/;
		if(!reg1.test(fee)){
			tipError("鼓励金只能输入数字并且保留两位小数");
			flag = false;
		}
		if(info!=""){
			if(reg.test(info)){
				tipError("类型说明含有非法字符，请修改");
				flag = false;
			}
			if(info.length>100){
				tipError("类型说明最多输入100个汉字");
				flag = false;
			}
		}
	    if (flag){
	    	if(isDefault=="1"){
// 	    		$.post("<c:url value='/hRegGuli/checkDefault'/>",
// 			        	{
// 				    		id : id,
// 						 _t:Math.random()},
// 			        	function(data){
// 				        	var result = eval('('+data+')'); 
// 				            if (result.code == '1') {
				            	$.post("<c:url value='/hRegGuli/updateHRegGuli'/>",
				        	        	{
				        		    		id : id,
				        		    		name : name,
				        		    		startTime : startTime,
				        		    		endTime : endTime,
				        		    		fee : fee,
				        		    		isDefault : isDefault,
				        		    		state : state,
				        		    		createTime : createTime,
				        		    		updateTime : updateTime,
				        		    		stopTime : stopTime,
				        		    		createId : createId,
				        		    		updateId : updateId,
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
// 				             } else {
// 				            	 tipError("编辑失败，已经存在默认鼓励金政策");
// 				             }
// 			        });
	    	}else{
	    		$.post("<c:url value='/hRegGuli/updateHRegGuli'/>",
        	        	{
        		    		id : id,
        		    		name : name,
        		    		startTime : startTime,
        		    		endTime : endTime,
        		    		fee : fee,
        		    		isDefault : isDefault,
        		    		state : state,
        		    		createTime : createTime,
        		    		updateTime : updateTime,
        		    		stopTime : stopTime,
        		    		createId : createId,
        		    		updateId : updateId,
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
	}
</script>