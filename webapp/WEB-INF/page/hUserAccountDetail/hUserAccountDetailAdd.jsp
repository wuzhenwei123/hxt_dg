<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>添加HUserAccountDetail</h4>
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
             <label class="control-label" for="aUserAccountId">userAccountId</label>
             <div class="controls">
			    <input type="text" placeholder="userAccountId" id="aUserAccountId" isNotNull="true" warnName="userAccountId" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aType">type</label>
             <div class="controls">
			    <input type="text" placeholder="type" id="aType" isNotNull="true" warnName="type" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aTotalFee">totalFee</label>
             <div class="controls">
			    <input type="text" placeholder="totalFee" id="aTotalFee" isNotNull="true" warnName="totalFee" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aRealFee">realFee</label>
             <div class="controls">
			    <input type="text" placeholder="realFee" id="aRealFee" isNotNull="true" warnName="realFee" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aCreateTime">createTime</label>
             <div class="controls">
		     	<input type="text" placeholder="createTime" id="aCreateTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="createTime" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aOperaterId">operaterId</label>
             <div class="controls">
			    <input type="text" placeholder="operaterId" id="aOperaterId" isNotNull="true" warnName="operaterId" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aRate">rate</label>
             <div class="controls">
			    <input type="text" placeholder="rate" id="aRate" isNotNull="true" warnName="rate" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aOrderId">orderId</label>
             <div class="controls">
			    <input type="text" placeholder="orderId" id="aOrderId" isNotNull="true" warnName="orderId" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aAmmeterNum">ammeterNum</label>
             <div class="controls">
			    <input type="text" placeholder="ammeterNum" id="aAmmeterNum" isNotNull="true" warnName="ammeterNum" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aOrderDetailId">orderDetailId</label>
             <div class="controls">
			    <input type="text" placeholder="orderDetailId" id="aOrderDetailId" isNotNull="true" warnName="orderDetailId" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aOrderDetailMoney">orderDetailMoney</label>
             <div class="controls">
			    <input type="text" placeholder="orderDetailMoney" id="aOrderDetailMoney" isNotNull="true" warnName="orderDetailMoney" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aTaxRate">taxRate</label>
             <div class="controls">
			    <input type="text" placeholder="taxRate" id="aTaxRate" isNotNull="true" warnName="taxRate" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aTaxMoney">taxMoney</label>
             <div class="controls">
			    <input type="text" placeholder="taxMoney" id="aTaxMoney" isNotNull="true" warnName="taxMoney" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aRemark1">remark1</label>
             <div class="controls">
			    <input type="text" placeholder="remark1" id="aRemark1" isNotNull="true" warnName="remark1" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aRemark2">remark2</label>
             <div class="controls">
			    <input type="text" placeholder="remark2" id="aRemark2" isNotNull="true" warnName="remark2" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aRemark3">remark3</label>
             <div class="controls">
			    <input type="text" placeholder="remark3" id="aRemark3" isNotNull="true" warnName="remark3" />
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
		var userAccountId = getVal("aUserAccountId");
		var type = getVal("aType");
		var totalFee = getVal("aTotalFee");
		var realFee = getVal("aRealFee");
		var createTime = getVal("aCreateTime");
		var operaterId = getVal("aOperaterId");
		var rate = getVal("aRate");
		var orderId = getVal("aOrderId");
		var ammeterNum = getVal("aAmmeterNum");
		var orderDetailId = getVal("aOrderDetailId");
		var orderDetailMoney = getVal("aOrderDetailMoney");
		var taxRate = getVal("aTaxRate");
		var taxMoney = getVal("aTaxMoney");
		var remark1 = getVal("aRemark1");
		var remark2 = getVal("aRemark2");
		var remark3 = getVal("aRemark3");
		var flag = validateForm('addForm');
	    if (flag){ 
	        $.post("<c:url value='/hUserAccountDetail/addHUserAccountDetail'/>",
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
		              	tipOk("保存成功!");
		             } else {
		            	 tipError(result.message);
		             }
	              	$modal.modal('hide');
	        });
	    }
	}
</script>