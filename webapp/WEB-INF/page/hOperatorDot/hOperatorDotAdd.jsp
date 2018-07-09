<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>添加营业网点</h4>
</div>
<div class="modal-body">
    <form id="addForm" class="well well-nice form-horizontal">
         <div class="control-group">
             <label class="control-label" for="aOperator_id">所属运营商</label>
             <div class="controls">
             	<select id="aOperator_id" isNotNull="true" warnName="所属运营商">
             		<option>--请选择--</option>
             		<c:forEach items="${op_list }" var="op" varStatus="vs">
             		<option value="${op.id }">${op.name }</option>
             		</c:forEach>
             	</select>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aOperator_level">营业厅等级</label>
             <div class="controls">
			    <input type="text" placeholder="营业厅等级" id="aOperator_level" isNotNull="true" warnName="operator_level" />
                 <span class="help-inline text-red">*</span>
                 <span class="help-inline">A/B/C/D/其它待定义</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aWindow_name">营业厅营业窗口名称</label>
             <div class="controls">
			    <input type="text" placeholder="营业厅营业窗口名称" id="aWindow_name" isNotNull="true" warnName="营业厅营业窗口名称" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aName">名称</label>
             <div class="controls">
			    <input type="text" placeholder="名称" id="aName" isNotNull="true" warnName="名称" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aProvince_code">省</label>
             <div class="controls">
             	<select id="aProvince_code" isNotNull="true" warnName="省"></select>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aCity_code">市</label>
             <div class="controls">
            	 <select id="aCity_code" isNotNull="true" warnName="市"></select>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aArea_code">区</label>
             <div class="controls">
             	<select id="aArea_code" isNotNull="true" warnName="区"></select>
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
             <label class="control-label" for="aFull_invoice">是否可打印全区发票</label>
             <div class="controls">
             	<select id="aFull_invoice" isNotNull="true" warnName="是否可打印全区发票" >
             		<option value="1">是</option>
             		<option value="0">否</option>
             	</select>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aAdd_invoice">是否可打印归属地缴费号发票</label>
             <div class="controls">
             	<select id="aAdd_invoice" isNotNull="true" warnName="是否可打印归属地缴费号发票" >
             		<option value="1">是</option>
             		<option value="0">否</option>
             	</select>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aMobile">联系手机号</label>
             <div class="controls">
			    <input type="text" placeholder="联系手机号" id="aMobile" isNotNull="true" warnName="联系手机号" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aPhone_post">联系固话区号</label>
             <div class="controls">
			    <input type="text" placeholder="联系固话区号" id="aPhone_post" isNotNull="true" warnName="联系固话区号" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aPhone">联系固话号码</label>
             <div class="controls">
			    <input type="text" placeholder="联系固话号码" id="aPhone" isNotNull="true" warnName="联系固话号码" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aWork_start">工作日营业开始时间</label>
             <div class="controls">
		     	<input type="text" placeholder="工作日营业开始时间" id="aWork_start" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" isNotNull="true" warnName="工作日营业开始时间" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aWork_end">工作日营业结束时间</label>
             <div class="controls">
		     	<input type="text" placeholder="工作日营业结束时间" id="aWork_end" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" isNotNull="true" warnName="工作日营业结束时间" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aAddress">营业详细地址</label>
             <div class="controls">
			    <input type="text" placeholder="营业详细地址" id="aAddress" isNotNull="true" warnName="营业详细地址" />
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
		var operator_name = getVal("aOperator_name");
		var operator_id = getVal("aOperator_id");
		var operator_level = getVal("aOperator_level");
		var window_name = getVal("aWindow_name");
		var name = getVal("aName");
		var province_name = getVal("aProvince_name");
		var province_code = getVal("aProvince_code");
		var city_code = getVal("aCity_code");
		var city_name = getVal("aCity_name");
		var area_code = getVal("aArea_code");
		var area_name = getVal("aArea_name");
		var state = getVal("aState");
		var createTime = getVal("aCreateTime");
		var full_invoice = getVal("aFull_invoice");
		var add_invoice = getVal("aAdd_invoice");
		var adminId = getVal("aAdminId");
		var lastTime = getVal("aLastTime");
		var lastAdminId = getVal("aLastAdminId");
		var mobile = getVal("aMobile");
		var phone = getVal("aPhone");
		var phone_post = getVal("aPhone_post");
		var work_start = getVal("aWork_start");
		var work_end = getVal("aWork_end");
		var address = getVal("aAddress");
		var flag = validateForm('addForm');
	    if (flag){ 
	        $.post("<c:url value='/hOperatorDot/addHOperatorDot'/>",
	        	{
		    		id : id,
		    		operator_name : operator_name,
		    		operator_id : operator_id,
		    		operator_level : operator_level,
		    		window_name : window_name,
		    		name : name,
		    		province_name : province_name,
		    		province_code : province_code,
		    		city_code : city_code,
		    		city_name : city_name,
		    		area_code : area_code,
		    		area_name : area_name,
		    		state : state,
		    		createTime : createTime,
		    		full_invoice : full_invoice,
		    		add_invoice : add_invoice,
		    		adminId : adminId,
		    		lastTime : lastTime,
		    		lastAdminId : lastAdminId,
		    		mobile : mobile,
		    		phone : phone,
		    		phone_post : phone_post,
		    		work_start : work_start,
		    		work_end : work_end,
		    		address : address,
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