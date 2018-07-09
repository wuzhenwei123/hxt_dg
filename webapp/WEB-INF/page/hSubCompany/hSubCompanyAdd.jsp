<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>添加HSubCompany</h4>
</div>
<div class="modal-body">
    <form id="addForm" class="well well-nice form-horizontal">
<!--          <div class="control-group"> -->
<!--              <label class="control-label" for="aC_id">所属客户单位</label> -->
<!--              <div class="controls"> -->
<!-- 			    <input type="text" placeholder="所属客户单位" id="aC_id" isNotNull="true" warnName="所属客户单位" /> -->
<!--                  <span class="help-inline text-red">*</span> -->
<!--              </div> -->
<!--          </div> -->
          <div class="control-group">
             <label class="control-label" for="aCompanyId">所属公司</label>
             <div class="controls">
			    <input type="text" placeholder="点击选择公司" id="companyName" isNotNull="true" data-toggle="modal" onclick="showCompany();" warnName="所属公司" readonly="readonly"/>
			    <a href="#stack5" id="sss5" style="display: none;" class="btn btn-primary btn-large btn-block" data-toggle="modal">Demo - Modal Stacled</a> 
			    <input type="hidden" id="aCompanyId" value=""/>
                 <span class="help-inline text-red">*</span>
                 <a type="button" class="btn btn-boo" onclick="cleanOn()">清除</a>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aSub_name">分组名称</label>
             <div class="controls">
			    <input type="text" placeholder="分组名称" id="aSub_name" isNotNull="true" warnName="分组名称" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aInvoice_title">发票抬头</label>
             <div class="controls">
			    <input type="text" placeholder="发票抬头" id="aInvoice_title" isNotNull="true" warnName="发票抬头" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aConsignee">收件人</label>
             <div class="controls">
			    <input type="text" placeholder="收件人" id="aConsignee" isNotNull="true" warnName="收件人" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aConsignee_phone">收件人手机</label>
             <div class="controls">
			    <input type="text" placeholder="收件人手机" id="aConsignee_phone" isNotNull="true" warnName="收件人手机" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aConsignee_tel1">收件人座机</label>
             <div class="controls">
			    <input type="text" placeholder="收件人座机" id="aConsignee_tel1" isNotNull="true" warnName="收件人座机" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aConsignee_tel2">固定电话分机号</label>
             <div class="controls">
			    <input type="text" placeholder="固定电话分机号" id="aConsignee_tel2" isNotNull="true" warnName="固定电话分机号" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aConsignee_address">收件人地址</label>
             <div class="controls">
			    <input type="text" placeholder="收件人地址" id="aConsignee_address" isNotNull="true" warnName="收件人地址" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aConsignee_address">收件人邮编</label>
             <div class="controls">
			    <input type="text" placeholder="收件人邮编" id="aZip_code" isNotNull="true" warnName="收件人邮编" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aProvince_code">省份</label>
             <div class="controls">
				<select id="aProvince_code"><option value="">请选择</option></select>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aCity_code">城市</label>
             <div class="controls">
			    <select id="aCity_code"><option value="">请选择</option></select>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aArea_code">地区</label>
             <div class="controls">
			     <select id="aArea_code"><option value="">请选择</option></select>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
     </form>
 </div>
 <div class="modal-footer">
     <button type="button" data-dismiss="modal" class="btn btn-boo">取消</button>
     <button type="submit" class="btn btn-green" onclick="add()">保存</button>
 </div>
  <%@ include file="/WEB-INF/page/hSubCompany/companyList.jsp" %>
<script type="text/javascript">
$(document).ready(function(){
	new $gov("aProvince_code","aCity_code","aArea_code").init();
})
	function showCompany(){
		$("#sss5").click();
		searchData5(1);
	}
	function cleanOn(){
		$("#companyName").val('');
		$("#aCompanyId").val('');
	}
	//执行添加
	function add(){
		var s_id = getVal("aS_id");
		var c_id = getVal("aCompanyId");
		var sub_name = getVal("aSub_name");
		var invoice_title = getVal("aInvoice_title");
		var consignee = getVal("aConsignee");
		var consignee_phone = getVal("aConsignee_phone");
		var consignee_tel1 = getVal("aConsignee_tel1");
		var consignee_tel2 = getVal("aConsignee_tel2");
		var consignee_address = getVal("aConsignee_address");
		var create_time = getVal("aCreate_time");
		var update_time = getVal("aUpdate_time");
		var flag = validateForm('addForm');
		var zip_code = getVal("aZip_code");
		var province_code = getVal("aProvince_code");
		var city_code = getVal("aCity_code");
		var area_code = getVal("aArea_code");
	    if (flag){ 
	        $.post("<c:url value='/hSubCompany/addHSubCompany'/>",
	        	{
		    		s_id : s_id,
		    		c_id : c_id,
		    		sub_name : sub_name,
		    		invoice_title : invoice_title,
		    		consignee : consignee,
		    		consignee_phone : consignee_phone,
		    		consignee_tel1 : consignee_tel1,
		    		consignee_tel2 : consignee_tel2,
		    		consignee_address : consignee_address,
		    		zip_code : zip_code,
		    		province_code : province_code,
		    		city_code : city_code,
		    		area_code : area_code,
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