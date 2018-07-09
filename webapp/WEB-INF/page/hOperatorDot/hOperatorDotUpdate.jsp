<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>编辑HOperatorDot</h4>
</div>
<div class="modal-body">
    <form id="updateForm" class="well well-nice form-horizontal">
	    <input type="hidden" placeholder="id" value="${hoperatordot.id}" id="mId" isNotNull="true" warnName="id" />
         <div class="control-group">
             <label class="control-label" for="mOperator_id">所属运营商</label>
             <div class="controls">
			    <input type="hidden" value="${hoperatordot.operator_name}" id="mOperator_name" />
			    <select id="mOperator_id" isNotNull="true" warnName="所属运营商">
             		<c:forEach items="${op_list }" var="op" varStatus="vs">
             		<option value="${op.id }" <c:if test="${hoperatordot.operator_id == op.id}">selected="selected"</c:if>>${op.name }</option>
             		</c:forEach>
             	</select>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mOperator_level">营业厅等级</label>
             <div class="controls">
			    <input type="text" placeholder="营业厅等级" value="${hoperatordot.operator_level}" id="mOperator_level" isNotNull="true" warnName="营业厅等级" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mWindow_name">营业厅营业窗口名称</label>
             <div class="controls">
			    <input type="text" placeholder="营业厅营业窗口名称" value="${hoperatordot.window_name}" id="mWindow_name" isNotNull="true" warnName="营业厅营业窗口名称" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mName">名称</label>
             <div class="controls">
			    <input type="text" placeholder="名称" value="${hoperatordot.name}" id="mName" isNotNull="true" warnName="名称" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mProvince_code">省</label>
             <div class="controls">
			    <input type="text" placeholder="省" readonly="readonly" value="${hoperatordot.province_name}" id="mProvince_name" />
			    <select id="mProvince_code" style="display: none;" gov-value="${hoperatordot.province_code}">
			    </select>
                 <span class="help-inline text-red">*</span>
			    <input type="button" class="btn mod" value="修改" onclick="modify()">
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mCity_name">市</label>
             <div class="controls">
			    <input type="text" placeholder="city_name" readonly="readonly" value="${hoperatordot.city_name}" id="mCity_name"/>
			    <select id="mCity_code" style="display: none;" gov-value="${hoperatordot.city_code}"></select>
                 <span class="help-inline text-red">*</span>
                 <input type="button" class="btn mod" value="修改" onclick="modify()">
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mArea_code">区</label>
             <div class="controls">
			    <input type="text" placeholder="area_name" readonly="readonly" value="${hoperatordot.area_name}" id="mArea_name" />
			    <select id="mArea_code" style="display: none;" gov-value="${hoperatordot.area_code}"></select>
                 <span class="help-inline text-red">*</span>
                 <input type="button" class="btn mod" value="修改" onclick="modify()">
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mState">状态</label>
             <div class="controls">
		    	 <select id="mState" isNotNull="true" warnName="状态"> 
                    <option <c:if test="${hoperatordot.state == 1}" >selected="selected"</c:if>  value="1">正常</option> 
                    <option <c:if test="${hoperatordot.state == 0}" >selected="selected"</c:if> value="0">暂停</option> 
            	</select> 
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mFull_invoice">是否可打印全区发票</label>
             <div class="controls">
             	<select id="mFull_invoice" isNotNull="true" warnName="是否可打印全区发票" >
             		<option value="1" <c:if test="${hoperatordot.full_invoice == 1}" >selected="selected"</c:if>>是</option>
             		<option value="0" <c:if test="${hoperatordot.full_invoice == 0}" >selected="selected"</c:if>>否</option>
             	</select>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mAdd_invoice">是否可打印归属地缴费号发票</label>
             <div class="controls">
             	<select id="mAdd_invoice" isNotNull="true" warnName="是否可打印归属地缴费号发票" >
             		<option value="1" <c:if test="${hoperatordot.add_invoice == 1}" >selected="selected"</c:if>>是</option>
             		<option value="0" <c:if test="${hoperatordot.add_invoice == 0}" >selected="selected"</c:if>>否</option>
             	</select>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mMobile">联系手机号</label>
             <div class="controls">
			    <input type="text" placeholder="联系手机号" value="${hoperatordot.mobile}" id="mMobile" isNotNull="true" warnName="联系手机号" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mPhone_post">联系固话区号</label>
             <div class="controls">
			    <input type="text" placeholder="联系固话区号" value="${hoperatordot.phone_post}" id="mPhone_post" isNotNull="true" warnName="联系固话区号" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mPhone">联系固话号码</label>
             <div class="controls">
			    <input type="text" placeholder="联系固话号码" value="${hoperatordot.phone}" id="mPhone" isNotNull="true" warnName="联系固话号码" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mWork_start">工作日营业开始时间</label>
             <div class="controls">
		     	<input type="text" placeholder="工作日营业开始时间" value="<fmt:formatDate value="${hoperatordot.work_start}" type="both"/>" id="mWork_start" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" isNotNull="true" warnName="工作日营业开始时间" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mWork_end">工作日营业结束时间</label>
             <div class="controls">
		     	<input type="text" placeholder="工作日营业结束时间" value="<fmt:formatDate value="${hoperatordot.work_end}" type="both"/>" id="mWork_end" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" isNotNull="true" warnName="工作日营业结束时间" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mAddress">营业详细地址</label>
             <div class="controls">
			    <input type="text" placeholder="营业详细地址" value="${hoperatordot.address}" id="mAddress" isNotNull="true" warnName="营业详细地址" />
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
		
// 		$gov.init("mProvince_code","mCity_code","mArea_code");
		new $gov("mProvince_code","mCity_code","mArea_code").init();
	}
	// 执行更新
	function update(){
		var id = getVal("mId");
		var operator_name = getVal("mOperator_name");
		var operator_id = getVal("mOperator_id");
		var operator_level = getVal("mOperator_level");
		var window_name = getVal("mWindow_name");
		var name = getVal("mName");
		var province_name = getVal("mProvince_name");
		var province_code = getVal("mProvince_code");
		var city_code = getVal("mCity_code");
		var city_name = getVal("mCity_name");
		var area_code = getVal("mArea_code");
		var area_name = getVal("mArea_name");
		var state = getVal("mState");
		var createTime = getVal("mCreateTime");
		var full_invoice = getVal("mFull_invoice");
		var add_invoice = getVal("mAdd_invoice");
		var adminId = getVal("mAdminId");
		var lastTime = getVal("mLastTime");
		var lastAdminId = getVal("mLastAdminId");
		var mobile = getVal("mMobile");
		var phone = getVal("mPhone");
		var phone_post = getVal("mPhone_post");
		var work_start = getVal("mWork_start");
		var work_end = getVal("mWork_end");
		var address = getVal("mAddress");
		var flag = validateForm('updateForm');
	    if (flag){ 
	        $.post("<c:url value='/hOperatorDot/updateHOperatorDot'/>",
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
		              	tipOk("更新成功!");
		             } else {
		            	 tipError(result.message);
		             }
		            $modal.modal('hide');
	        });
	    }
	}
</script>