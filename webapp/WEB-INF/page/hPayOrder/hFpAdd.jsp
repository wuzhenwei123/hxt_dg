<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>添加发票快递信息</h4>
</div>
<div class="modal-body">
    <form id="addForm" class="well well-nice form-horizontal">
    <input type="hidden" id="pid" value="${pid }">
         <div class="control-group">
             <label class="control-label" for="aOrderNumber">订单号</label>
             <div class="controls">
             	${o_no}
			    <input type="hidden" id="aOrderNumber" isNotNull="true" warnName="订单号" value="${o_no}"/>
                <span class="help-inline text-red">*</span>
             </div>
         </div>
<!--          <div class="control-group"> -->
<!--              <label class="control-label" for="aMoney">发票金额</label> -->
<!--              <div class="controls"> -->
<!-- 			    <input type="text" placeholder="发票金额" id="aMoney" isNotNull="true" warnName="发票金额" /> -->
<!--                  <span class="help-inline text-red">*</span> -->
<!--              </div> -->
<!--          </div> -->
         <div class="control-group">
             <label class="control-label" for="aTitle">发票抬头</label>
             <div class="controls">
			    <input type="text" placeholder="发票抬头" id="aTitle" isNotNull="true" warnName="发票抬头" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <!-- <div class="control-group">
             <label class="control-label" for="aUserName">收件人姓名</label>
             <div class="controls">
			    <input type="text" placeholder="userName" id="aUserName" isNotNull="true" warnName="收件人姓名" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aPhone">收件人电话</label>
             <div class="controls">
			    <input type="text" placeholder="phone" id="aPhone" isNotNull="true" warnName="收件人电话" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aAddress">收件人地址</label>
             <div class="controls">
			    <input type="text" placeholder="address" id="aAddress" isNotNull="true" warnName="收件人地址" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aCreateTime">createTime</label>
             <div class="controls">
		     	<input type="text" placeholder="createTime" id="aCreateTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="createTime" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div> -->
         <div class="control-group">
             <label class="control-label" for="aExpress_no">快递单号</label>
             <div class="controls">
			    <input type="text" placeholder="快递单号" id="aExpress_no" isNotNull="true" warnName="快递单号" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <!-- <div class="control-group">
             <label class="control-label" for="aExpress_status">express_status</label>
             <div class="controls">
			    <input type="text" placeholder="express_status" id="aExpress_status" isNotNull="true" warnName="express_status" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div> -->
         <div class="control-group">
             <label class="control-label" for="aExpress_name">快递名称</label>
             <div class="controls">
			    <input type="text" placeholder="快递名称" id="aExpress_name" isNotNull="true" warnName="快递名称" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
        <!--  <div class="control-group">
             <label class="control-label" for="aMailType">邮寄方式</label>
             <div class="controls">
			    <input type="text" placeholder="mailType" id="aMailType" isNotNull="true" warnName="邮寄方式" />
			    <select id="aMailType" isNotNull="true" warnName="邮寄方式">
               		<option value="1">快递</option>
               		<option value="2">挂号信</option>
               		<option value="3">平信</option>
			    </select>
                 <span class="help-inline text-red">*</span>
             </div>
         </div> -->
         <input type='hidden' id="aMailType" value='1'>
        <!--  <div class="control-group">
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
         </div> -->
     </form>
 </div>
 <div class="modal-footer">
     <button type="button" data-dismiss="modal" class="btn btn-boo">取消</button>
     <button type="submit" class="btn btn-green" onclick="add()">保存</button>
 </div>
<script type="text/javascript">
	//执行添加
	function add(){
// 		var id = getVal("aId");
		var orderNumber = getVal("aOrderNumber");
// 		var money = getVal("aMoney");
		var title = getVal("aTitle");
// 		var userName = getVal("aUserName");
// 		var phone = getVal("aPhone");
// 		var address = getVal("aAddress");
// 		var createTime = getVal("aCreateTime");
		var express_no = getVal("aExpress_no");
// 		var express_status = getVal("aExpress_status");
		var express_name = getVal("aExpress_name");
		var mailType = getVal("aMailType");
// 		var remark1 = getVal("aRemark1");
// 		var remark2 = getVal("aRemark2");
// 		var remark3 = getVal("aRemark3");
		var flag = validateForm('addForm');
	    if (flag){ 
	        $.post("<c:url value='/hFp/addHFp'/>",
	        	{
// 		    		id : id,
		    		orderNumber : orderNumber,
// 		    		money : money,
		    		title : title,
// 		    		userName : userName,
// 		    		phone : phone,
// 		    		address : address,
// 		    		createTime : createTime,
		    		express_no : express_no,
		    		express_status : 2,
		    		express_name : express_name,
		    		mailType : mailType,
// 		    		remark1 : remark1,
// 		    		remark2 : remark2,
		    		remark3 : $("#pid").val(),
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