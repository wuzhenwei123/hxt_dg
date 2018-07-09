<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>编辑HUserAccountDetail</h4>
</div>
<div class="modal-body">
    <form id="updateForm" class="well well-nice form-horizontal">
         <div class="control-group">
             <label class="control-label" for="mId">id</label>
             <div class="controls">
			    <input type="text" placeholder="id" value="${huseraccountdetail.id}" id="mId" isNotNull="true" warnName="id" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mUserAccountId">userAccountId</label>
             <div class="controls">
			    <input type="text" placeholder="userAccountId" value="${huseraccountdetail.userAccountId}" id="mUserAccountId" isNotNull="true" warnName="userAccountId" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mType">type</label>
             <div class="controls">
			    <input type="text" placeholder="type" value="${huseraccountdetail.type}" id="mType" isNotNull="true" warnName="type" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mTotalFee">totalFee</label>
             <div class="controls">
			    <input type="text" placeholder="totalFee" value="${huseraccountdetail.totalFee}" id="mTotalFee" isNotNull="true" warnName="totalFee" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mRealFee">realFee</label>
             <div class="controls">
			    <input type="text" placeholder="realFee" value="${huseraccountdetail.realFee}" id="mRealFee" isNotNull="true" warnName="realFee" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mCreateTime">createTime</label>
             <div class="controls">
		     	<input type="text" placeholder="createTime" value="<fmt:formatDate value="${huseraccountdetail.createTime}" type="both"/>" id="mCreateTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="createTime" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mOperaterId">operaterId</label>
             <div class="controls">
			    <input type="text" placeholder="operaterId" value="${huseraccountdetail.operaterId}" id="mOperaterId" isNotNull="true" warnName="operaterId" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mRate">rate</label>
             <div class="controls">
			    <input type="text" placeholder="rate" value="${huseraccountdetail.rate}" id="mRate" isNotNull="true" warnName="rate" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mOrderId">orderId</label>
             <div class="controls">
			    <input type="text" placeholder="orderId" value="${huseraccountdetail.orderId}" id="mOrderId" isNotNull="true" warnName="orderId" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mAmmeterNum">ammeterNum</label>
             <div class="controls">
			    <input type="text" placeholder="ammeterNum" value="${huseraccountdetail.ammeterNum}" id="mAmmeterNum" isNotNull="true" warnName="ammeterNum" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mOrderDetailId">orderDetailId</label>
             <div class="controls">
			    <input type="text" placeholder="orderDetailId" value="${huseraccountdetail.orderDetailId}" id="mOrderDetailId" isNotNull="true" warnName="orderDetailId" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mOrderDetailMoney">orderDetailMoney</label>
             <div class="controls">
			    <input type="text" placeholder="orderDetailMoney" value="${huseraccountdetail.orderDetailMoney}" id="mOrderDetailMoney" isNotNull="true" warnName="orderDetailMoney" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mTaxRate">taxRate</label>
             <div class="controls">
			    <input type="text" placeholder="taxRate" value="${huseraccountdetail.taxRate}" id="mTaxRate" isNotNull="true" warnName="taxRate" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mTaxMoney">taxMoney</label>
             <div class="controls">
			    <input type="text" placeholder="taxMoney" value="${huseraccountdetail.taxMoney}" id="mTaxMoney" isNotNull="true" warnName="taxMoney" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mRemark1">remark1</label>
             <div class="controls">
			    <input type="text" placeholder="remark1" value="${huseraccountdetail.remark1}" id="mRemark1" isNotNull="true" warnName="remark1" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mRemark2">remark2</label>
             <div class="controls">
			    <input type="text" placeholder="remark2" value="${huseraccountdetail.remark2}" id="mRemark2" isNotNull="true" warnName="remark2" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mRemark3">remark3</label>
             <div class="controls">
			    <input type="text" placeholder="remark3" value="${huseraccountdetail.remark3}" id="mRemark3" isNotNull="true" warnName="remark3" />
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
		var userAccountId = getVal("mUserAccountId");
		var type = getVal("mType");
		var totalFee = getVal("mTotalFee");
		var realFee = getVal("mRealFee");
		var createTime = getVal("mCreateTime");
		var operaterId = getVal("mOperaterId");
		var rate = getVal("mRate");
		var orderId = getVal("mOrderId");
		var ammeterNum = getVal("mAmmeterNum");
		var orderDetailId = getVal("mOrderDetailId");
		var orderDetailMoney = getVal("mOrderDetailMoney");
		var taxRate = getVal("mTaxRate");
		var taxMoney = getVal("mTaxMoney");
		var remark1 = getVal("mRemark1");
		var remark2 = getVal("mRemark2");
		var remark3 = getVal("mRemark3");
		var flag = validateForm('updateForm');
	    if (flag){ 
	        $.post("<c:url value='/hUserAccountDetail/updateHUserAccountDetail'/>",
	        	{
		    		id : id,
		    		userAccountId : userAccountId,
		    		type : type,
		    		totalFee : totalFee,
		    		realFee : realFee,
		    		createTime : createTime,
		    		operaterId : operaterId,
		    		rate : rate,
		    		orderId : orderId,
		    		ammeterNum : ammeterNum,
		    		orderDetailId : orderDetailId,
		    		orderDetailMoney : orderDetailMoney,
		    		taxRate : taxRate,
		    		taxMoney : taxMoney,
		    		remark1 : remark1,
		    		remark2 : remark2,
		    		remark3 : remark3,
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