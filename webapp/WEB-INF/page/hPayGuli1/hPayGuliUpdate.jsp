<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>编辑手机支付交易的鼓励金</h4>
</div>
<div class="modal-body">
	<input type="hidden" placeholder="id" value="${hpayguli.id}" id="mId" isNotNull="true" warnName="id" />
    <form id="updateForm" class="well well-nice form-horizontal">
         <div class="control-group" style="display: none;">
             <label class="control-label" for="mTrade_code">交易类型</label>
             <div class="controls">
			    <input type="text" value="${hpayguli.trade_code}" id="mTrade_code" isNotNull="true" warnName="trade_code" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mName">简称</label>
             <div class="controls">
			    <input type="text" value="${hpayguli.name}" id="mName" isNotNull="true" warnName="简称" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mStartTime">有效期开始时间</label>
             <div class="controls">
		     	<input type="text" value="<fmt:formatDate value="${hpayguli.startTime}" type="both"/>" id="mStartTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="有效期开始时间" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mEndTime">有效期结束时间</label>
             <div class="controls">
		     	<input type="text" value="<fmt:formatDate value="${hpayguli.endTime}" type="both"/>" id="mEndTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="有效期结束时间" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mType">类型</label>
             <div class="controls">
                 <select id="mType" onchange="showType1(this.value)"> 
                    <option value="1" <c:if test="${hpayguli.type==1}">selected</c:if>>按每笔送固定金额</option> 
                    <option value="2" <c:if test="${hpayguli.type==2}">selected</c:if>>按实际缴费比例</option> 
            	</select> 
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mOpObject">鼓励金对象</label>
             <div class="controls">
                 <select id="mOpObject"> 
                    <option value="1" <c:if test="${hpayguli.opObject == 1}" >selected="selected"</c:if>>客户经理</option> 
                    <option value="2" <c:if test="${hpayguli.opObject == 2}" >selected="selected"</c:if>>代理</option> 
                    <option value="3" <c:if test="${hpayguli.opObject == 3}" >selected="selected"</c:if>>服务人员</option> 
            	</select> 
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group" id="feeDiv1">
             <label class="control-label" for="mFee">鼓励金金额</label>
             <div class="controls">
			    <input type="text" value="<fmt:formatNumber value="${hpayguli.fee}" pattern="0.00" />" id="mFee" isNotNull="true" warnName="鼓励金金额" />
                 <span class="help-inline text-red">请输入大于等于0的数字，且最多两位小数*</span>
             </div>
         </div>
         <div class="control-group" id="rateDiv1">
             <label class="control-label" for="mRate">实际缴费费率</label>
             <div class="controls">
			    <input type="text" value="<fmt:formatNumber value="${hpayguli.rate}" pattern="0.00000" />" id="mRate" warnName="实际缴费费率" />
                 <span class="help-inline text-red">请输入数字，且最多五位小数*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mState">状态</label>
             <div class="controls">
		    	 <select id="mState" isNotNull="true" warnName="状态"> 
                    <option <c:if test="${hpayguli.state == 1}" >selected="selected"</c:if>  value="1">正常</option> 
                    <option <c:if test="${hpayguli.state == 0}" >selected="selected"</c:if> value="0">禁用</option> 
            	</select> 
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mIs_default">是否是默认</label>
             <div class="controls">
             	<select id="mIs_default" isNotNull="true" warnName="是否是默认"> 
                    <option <c:if test="${hpayguli.is_default == 1}" >selected="selected"</c:if>  value="1">是</option> 
                    <option <c:if test="${hpayguli.is_default == 0}" >selected="selected"</c:if> value="0">否</option> 
            	</select> 
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mInfo">说明</label>
             <div class="controls">
             <textarea rows="4" cols="4" id="mInfo" warnName="说明">${hpayguli.info}</textarea>
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
	$(document).ready(function(){
		var type = getVal("mType");
		showType1(type);
	});
	// 执行更新
	function update(){
		var id = getVal("mId");
		var trade_code = getVal("mTrade_code");
		var name = getVal("mName");
		var style = 2;
		var startTime = getVal("mStartTime");
		var endTime = getVal("mEndTime");
		var type = getVal("mType");
		var fee = getVal("mFee");
		var rate = getVal("mRate");
		var state = getVal("mState");
		var is_default = getVal("mIs_default");
		var createTime = getVal("mCreateTime");
		var createId = getVal("mCreateId");
		var updateTime = getVal("mUpdateTime");
		var updateId = getVal("mUpdateId");
		var stopTime = getVal("mStopTime");
		var info = getVal("mInfo");
		var opObject = getVal("mOpObject");
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
		if(type=="1"){
			var reg1 = /^\d+(?:.\d{1,2})?$/;
			if(!reg1.test(fee)){
				tipError("你输入的数值不正确");
				flag = false;
				$("#mFee").focus();
			}else if(fee<0){
				tipError("你输入的数值不正确");
				flag = false;
				$("#mFee").focus();
			}
		}else{
			var reg1 = /^\d+(?:.\d{1,5})?$/;
			if(!reg1.test(rate)){
				tipError("你输入的数值不正确");
				flag = false;
				$("#mRate").focus();
			}else if(rate<0){
				tipError("你输入的数值不正确");
				flag = false;
				$("#mRate").focus();
			}
		}
		
		if(info!=""){
			if(reg.test(info)){
				tipError("类型说明含有非法字符，请修改");
				flag = false;
			}
			if(info.length>100){
				tipError("说明最多输入100个汉字");
				flag = false;
			}
		}
	    if (flag){ 
	    	
	    	if(is_default=="1"){
// 	    		$.post("<c:url value='/hPayGuli/checkDefault'/>",
// 	    	        	{
// 	    		    		id : id,
// 	    		    		style : 2,
// 	    		    		opObject : opObject,
// 	    				 _t:Math.random()},
// 	    	        	function(data){
// 	    		        	var result = eval('('+data+')'); 
// 	    		            if (result.code == '1') {
	    		            	$.post("<c:url value='/hPayGuli/updateHPayGuli'/>",
	    		        	        	{
	    		        		    		id : id,
	    		        		    		trade_code : trade_code,
	    		        		    		name : name,
	    		        		    		style : style,
	    		        		    		startTime : startTime,
	    		        		    		endTime : endTime,
	    		        		    		type : type,
	    		        		    		fee : fee,
	    		        		    		opObject : opObject,
	    		        		    		rate : rate,
	    		        		    		state : state,
	    		        		    		is_default : is_default,
	    		        		    		createTime : createTime,
	    		        		    		createId : createId,
	    		        		    		updateTime : updateTime,
	    		        		    		updateId : updateId,
	    		        		    		stopTime : stopTime,
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
// 	    		             } else {
// 	    		            	 tipError("添加失败，已存在鼓励金");
// 	    		             }
// 	    	        });
	    	}else{
	    		$.post("<c:url value='/hPayGuli/updateHPayGuli'/>",
	    	        	{
	    		    		id : id,
	    		    		trade_code : trade_code,
	    		    		name : name,
	    		    		style : style,
	    		    		startTime : startTime,
	    		    		endTime : endTime,
	    		    		type : type,
	    		    		fee : fee,
	    		    		opObject : opObject,
	    		    		rate : rate,
	    		    		state : state,
	    		    		is_default : is_default,
	    		    		createTime : createTime,
	    		    		createId : createId,
	    		    		updateTime : updateTime,
	    		    		updateId : updateId,
	    		    		stopTime : stopTime,
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
	
	function showType1(val){
		if(val=="1"){
			$("#feeDiv1").show();
			$("#rateDiv1").hide();
			$("#mRate").val("");
			$("#mRate").removeAttr("isNotNull");
			$("#mFee").attr("isNotNull","true");
		}else{
			$("#feeDiv1").hide();
			$("#rateDiv1").show();
			$("#mFee").val("");
			$("#mFee").removeAttr("isNotNull");
			$("#mRate").attr("isNotNull","true");
		}
	}
</script>