<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>添加注册鼓励金政策</h4>
</div>
<div class="modal-body">
    <form id="addForm" class="well well-nice form-horizontal">
         <div class="control-group">
             <label class="control-label" for="aName">简称</label>
             <div class="controls">
			    <input type="text" id="aName" isNotNull="true" warnName="简称" />
                 <span class="help-inline text-red">最多20个汉字*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aStartTime">有效期开始时间</label>
             <div class="controls">
		     	<input type="text" id="aStartTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="有效期开始时间" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aEndTime">有效期结束时间</label>
             <div class="controls">
		     	<input type="text" id="aEndTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="有效期结束时间" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aFee">鼓励金金额</label>
             <div class="controls">
			    <input type="text"  id="aFee" isNotNull="true" warnName="鼓励金金额" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aIsDefault">是否为默认政策</label>
             <div class="controls">
                 <select id="aIsDefault"> 
                    <option value="0">否</option> 
                    <option value="1">是</option> 
            	</select> 
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
         <div class="control-group">
             <label class="control-label" for="aInfo">类型说明</label>
             <div class="controls">
             	<textarea rows="4" cols="4" id="aInfo" warnName="info"></textarea>
                 <span class="help-inline text-red">最多100个汉字*</span>
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
		var startTime = getVal("aStartTime");
		var endTime = getVal("aEndTime");
		var fee = getVal("aFee");
		var isDefault = getVal("aIsDefault");
		var state = getVal("aState");
		var createTime = getVal("aCreateTime");
		var updateTime = getVal("aUpdateTime");
		var stopTime = getVal("aStopTime");
		var createId = getVal("aCreateId");
		var updateId = getVal("aUpdateId");
		var info = getVal("aInfo");
		var flag = validateForm('addForm');
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
// 						 _t:Math.random()},
// 			        	function(data){
// 				        	var result = eval('('+data+')'); 
// 				            if (result.code == '1') {
		    	
		        $.post("<c:url value='/hRegGuli/addHRegGuli'/>",
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
			              	tipOk("保存成功!");
			             } else {
			            	 tipError(result.message);
			             }
		              	$modal.modal('hide');
		        });
// 				            } else {
// 				            	 tipError("添加失败，已经存在默认鼓励金政策");
// 				             }
// 			        });
	    	}else{
	    		$.post("<c:url value='/hRegGuli/addHRegGuli'/>",
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
				              	tipOk("保存成功!");
				             } else {
				            	 tipError(result.message);
				             }
			              	$modal.modal('hide');
			        });
	    	}
	    	
	    }
	}
</script>