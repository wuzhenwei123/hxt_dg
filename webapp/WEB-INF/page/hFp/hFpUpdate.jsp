<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>编辑发票信息</h4>
</div>
<div class="modal-body">
    <form id="updateForm" class="well well-nice form-horizontal">
	    <input type="hidden" placeholder="id" value="${hfp.id}" id="mId" />
         <div class="control-group">
             <label class="control-label" for="mOrderNumber">订单号</label>
             <div class="controls">
			    <input type="text" placeholder="多个订单使用英文状态下逗号分隔" value="${hfp.orderNumber}" id="mOrderNumber" isNotNull="true" warnName="订单号" readonly="readonly"/>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mMoney">发票金额</label>
             <div class="controls">
			    <input type="text" placeholder="发票金额" value="${hfp.totalFeeStr}" id="mMoney" isNotNull="true" warnName="发票金额" disabled="disabled" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mTitle">发票抬头</label>
             <div class="controls">
			    <input type="text" placeholder="发票抬头" value="${hfp.title}" id="mTitle" warnName="发票抬头" />
             </div>
         </div>
         <%-- <div class="control-group">
             <label class="control-label" for="mUserName">userName</label>
             <div class="controls">
			    <input type="text" placeholder="userName" value="${hfp.userName}" id="mUserName" isNotNull="true" warnName="userName" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mPhone">phone</label>
             <div class="controls">
			    <input type="text" placeholder="phone" value="${hfp.phone}" id="mPhone" isNotNull="true" warnName="phone" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mAddress">address</label>
             <div class="controls">
			    <input type="text" placeholder="address" value="${hfp.address}" id="mAddress" isNotNull="true" warnName="address" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mCreateTime">createTime</label>
             <div class="controls">
		     	<input type="text" placeholder="createTime" value="<fmt:formatDate value="${hfp.createTime}" type="both"/>" id="mCreateTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="createTime" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div> --%>
         <div class="control-group">
             <label class="control-label" for="mExpress_no">快递单号</label>
             <div class="controls">
			    <input type="text" placeholder="快递单号" value="${hfp.express_no}" id="mExpress_no" isNotNull="true" warnName="快递单号" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
<!--          <div class="control-group"> -->
<!--              <label class="control-label" for="mExpress_status">express_status</label> -->
<!--              <div class="controls"> -->
<%-- 			    <input type="text" placeholder="express_status" value="${hfp.express_status}" id="mExpress_status" isNotNull="true" warnName="express_status" /> --%>
<!--                  <span class="help-inline text-red">*</span> -->
<!--              </div> -->
<!--          </div> -->
         <div class="control-group">
             <label class="control-label" for="mExpress_name">快递名称</label>
             <div class="controls">
			    <input type="text" placeholder="快递名称" value="${hfp.express_name}" id="mExpress_name" isNotNull="true" warnName="快递名称" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
        <%--  <div class="control-group">
             <label class="control-label" for="mMailType">邮寄方式</label>
             <div class="controls">
             <select id="mMailType">
          		<option value="1" <c:if test="${hfp.mailType==1 }">selected="selected"</c:if>>快递</option>
          		<option value="2" <c:if test="${hfp.mailType==2 }">selected="selected"</c:if>>挂号信</option>
          		<option value="3" <c:if test="${hfp.mailType==3 }">selected="selected"</c:if>>平信</option>
             </select>
                 <span class="help-inline text-red">*</span>
             </div>
         </div> --%>
         <input type='hidden' id="mMailType" value='1'>
        <%--  <div class="control-group">
             <label class="control-label" for="mRemark1">remark1</label>
             <div class="controls">
			    <input type="text" placeholder="remark1" value="${hfp.remark1}" id="mRemark1" isNotNull="true" warnName="remark1" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mRemark2">remark2</label>
             <div class="controls">
			    <input type="text" placeholder="remark2" value="${hfp.remark2}" id="mRemark2" isNotNull="true" warnName="remark2" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mRemark3">remark3</label>
             <div class="controls">
			    <input type="text" placeholder="remark3" value="${hfp.remark3}" id="mRemark3" isNotNull="true" warnName="remark3" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div> --%>
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
		var orderNumber = getVal("mOrderNumber");
		var money = getVal("mMoney");
		var title = getVal("mTitle");
// 		var userName = getVal("mUserName");
// 		var phone = getVal("mPhone");
// 		var address = getVal("mAddress");
// 		var createTime = getVal("mCreateTime");
		var express_no = getVal("mExpress_no");
// 		var express_status = getVal("mExpress_status");
		var express_name = getVal("mExpress_name");
		var mailType = getVal("mMailType");
// 		var remark1 = getVal("mRemark1");
// 		var remark2 = getVal("mRemark2");
// 		var remark3 = getVal("mRemark3");
		var flag = validateForm('updateForm');
	    if (flag){ 
	        $.post("<c:url value='/hFp/updateHFp'/>",
	        	{
		    		id : id,
		    		orderNumber : orderNumber,
// 		    		money : money,
		    		title : title,
// 		    		userName : userName,
// 		    		phone : phone,
// 		    		address : address,
// 		    		createTime : createTime,
		    		express_no : express_no,
// 		    		express_status : express_status,
		    		express_name : express_name,
		    		mailType : mailType,
// 		    		remark1 : remark1,
// 		    		remark2 : remark2,
// 		    		remark3 : remark3,
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