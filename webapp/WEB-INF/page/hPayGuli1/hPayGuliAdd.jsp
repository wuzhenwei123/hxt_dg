<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>添加手机支付交易的鼓励金</h4>
</div>
<div class="modal-body">
    <form id="addForm" class="well well-nice form-horizontal">
         <div class="control-group" style="display: none;">
             <label class="control-label" for="aTrade_code">交易类型</label>
             <div class="controls">
			    <input type="text" placeholder="trade_code" value="3100" id="aTrade_code" isNotNull="true" warnName="trade_code" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aName">简称</label>
             <div class="controls">
			    <input type="text" id="aName" isNotNull="true" warnName="简称" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aStartTime">有效期开始时间</label>
             <div class="controls">
		     	<input type="text" id="aStartTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="startTime" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aEndTime">有效期结束时间</label>
             <div class="controls">
		     	<input type="text" id="aEndTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="endTime" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aType">类型</label>
             <div class="controls">
                 <select id="aType" onchange="showType(this.value)"> 
                    <option value="1">按每笔送固定金额</option> 
                    <option value="2">按实际缴费比例</option> 
            	</select> 
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aOpObject">鼓励金对象</label>
             <div class="controls">
                 <select id="aOpObject"> 
                    <option value="1">客户经理</option> 
                    <option value="2">代理</option> 
                    <option value="3">服务人员</option> 
            	</select> 
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group" id="feeDiv">
             <label class="control-label" for="aFee">鼓励金金额</label>
             <div class="controls">
			    <input type="text" id="aFee" isNotNull="true" warnName="鼓励金金额" />
                 <span class="help-inline text-red">请输入大于等于0的数字，且最多两位小数*</span>
             </div>
         </div>
         <div class="control-group" style="display: none;" id="rateDiv">
             <label class="control-label" for="aRate">实际缴费费率</label>
             <div class="controls">
			    <input type="text" id="aRate" warnName="实际缴费费率" />
                 <span class="help-inline text-red">请输入大于0的数字，且最多五位小数*</span>
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
             <label class="control-label" for="aIs_default">是否是默认</label>
             <div class="controls">
             	<select id="aIs_default"> 
                    <option value="0">否</option> 
                    <option value="1">是</option> 
            	</select>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aInfo">说明</label>
             <div class="controls">
             	<textarea rows="4" cols="4" id="aInfo" warnName="说明"></textarea>
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
		var trade_code = getVal("aTrade_code");
		var name = getVal("aName");
		var style = 2;
		var startTime = getVal("aStartTime");
		var endTime = getVal("aEndTime");
		var type = getVal("aType");
		var fee = getVal("aFee");
		var rate = getVal("aRate");
		var state = getVal("aState");
		var is_default = getVal("aIs_default");
		var createTime = getVal("aCreateTime");
		var createId = getVal("aCreateId");
		var updateTime = getVal("aUpdateTime");
		var updateId = getVal("aUpdateId");
		var stopTime = getVal("aStopTime");
		var info = getVal("aInfo");
		var opObject = getVal("aOpObject");
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
		if(type=="1"){
			var reg1 = /^\d+(?:.\d{1,2})?$/;
			if(!reg1.test(fee)){
				tipError("你输入的数值不正确");
				flag = false;
				$("#aFee").focus();
			}else if(fee<0){
				tipError("你输入的数值不正确");
				flag = false;
				$("#aFee").focus();
			}
		}else{
			var reg1 = /^\d+(?:.\d{1,5})?$/;
			if(!reg1.test(rate)){
				tipError("你输入的数值不正确");
				flag = false;
				$("#aRate").focus();
			}else if(rate<0){
				tipError("你输入的数值不正确");
				flag = false;
				$("#aRate").focus();
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
// 	    		    		style : 2,
// 	    		    		opObject : opObject,
// 	    				 _t:Math.random()},
// 	    	        	function(data){
// 	    		        	var result = eval('('+data+')'); 
// 	    		            if (result.code == '1') {
	    		            	$.post("<c:url value='/hPayGuli/addHPayGuli'/>",
	    		        	        	{
	    		        		    		id : id,
	    		        		    		trade_code : trade_code,
	    		        		    		name : name,
	    		        		    		style : style,
	    		        		    		startTime : startTime,
	    		        		    		endTime : endTime,
	    		        		    		type : type,
	    		        		    		opObject : opObject,
	    		        		    		fee : fee,
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
	    		        		              	tipOk("保存成功!");
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
	    		$.post("<c:url value='/hPayGuli/addHPayGuli'/>",
	    	        	{
	    		    		id : id,
	    		    		trade_code : trade_code,
	    		    		name : name,
	    		    		style : style,
	    		    		startTime : startTime,
	    		    		endTime : endTime,
	    		    		type : type,
	    		    		fee : fee,
	    		    		rate : rate,
	    		    		opObject : opObject,
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
	    		              	tipOk("保存成功!");
	    		             } else {
	    		            	 tipError(result.message);
	    		             }
	    	              	$modal.modal('hide');
	    	        });
	    	}
	    	
	    }
	}
	
	function showType(val){
		if(val=="1"){
			$("#feeDiv").show();
			$("#rateDiv").hide();
			$("#aRate").val("");
			$("#aRate").removeAttr("isNotNull");
			$("#aFee").attr("isNotNull","true");
		}else{
			$("#feeDiv").hide();
			$("#rateDiv").show();
			$("#aFee").val("");
			$("#aFee").removeAttr("isNotNull");
			$("#aRate").attr("isNotNull","true");
		}
	}
</script>