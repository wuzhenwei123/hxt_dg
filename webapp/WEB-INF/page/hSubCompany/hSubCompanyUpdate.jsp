<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>编辑发票收件信息</h4>
</div>
<input type="hidden" placeholder="s_id" value="${hsubcompany.s_id}" id="mS_id" isNotNull="true" warnName="s_id" />
<div class="modal-body">
    <form id="updateForm" class="well well-nice form-horizontal">
<%--          <div class="control-group">
             <label class="control-label" for="mC_id">所属公司单位</label>
             <div class="controls">
			    <input type="text" placeholder="所属公司单位" value="${hsubcompany.c_id}" id="mC_id" isNotNull="true" warnName="所属公司单位" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div> --%>
         <div class="control-group">
             <label class="control-label" for="companyName">企业</label>
             <div class="controls">
<!-- 			    <input type="text" placeholder="点击选择企业" id="companyName" isNotNull="true" data-toggle="modal" onclick="showCompany();" warnName="企业" readonly="readonly" value="${hsubcompany.c_name }"/> -->
<!-- 			    <a href="#stack5" id="sss5" style="display: none;" class="btn btn-primary btn-large btn-block" data-toggle="modal">Demo - Modal Stacled</a>  -->
			    ${hsubcompany.c_name}(${hsubcompany.contact_phone})
			    <input type="hidden" id="aCompanyId" value="${hsubcompany.c_id}"/>
<!--                  <span class="help-inline text-red">*</span> -->
<!--                  <a type="button" class="btn btn-boo" onclick="cleanOn()">清除</a> -->
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mSub_name">分组名称</label>
             <div class="controls">
			    <input type="text" placeholder="分组名称" value="${hsubcompany.sub_name}" id="mSub_name" warnName="分组名称" />
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mInvoice_title">发票抬头</label>
             <div class="controls">
			    <input type="text" placeholder="发票抬头" value="${hsubcompany.invoice_title}" id="mInvoice_title" warnName="发票抬头" />
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mConsignee">收件人</label>
             <div class="controls">
			    <input type="text" placeholder="收件人" value="${hsubcompany.consignee}" id="mConsignee" isNotNull="true" warnName="收件人" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mConsignee_phone">收件人手机</label>
             <div class="controls">
			    <input type="text" placeholder="收件人手机" value="${hsubcompany.consignee_phone}" id="mConsignee_phone" isNotNull="true" warnName="收件人手机" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mConsignee_tel1">收件人座机</label>
             <div class="controls">
			    <input type="text" placeholder="收件人座机" value="${hsubcompany.consignee_tel1}" id="mConsignee_tel1" warnName="收件人座机" />
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mConsignee_tel2">固定电话分机号</label>
             <div class="controls">
			    <input type="text" placeholder="固定电话分机号" value="${hsubcompany.consignee_tel2}" id="mConsignee_tel2" warnName="固定电话分机号" />
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mConsignee_address">收件人地址</label>
             <div class="controls">
			    <input type="text" placeholder="收件人地址" value="${hsubcompany.consignee_address}" id="mConsignee_address" isNotNull="true" warnName="收件人地址" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mConsignee_address">邮编</label>
             <div class="controls">
			    <input type="text" placeholder="邮编" value="${hsubcompany.zip_code}" id="mZip_code" isNotNull="true" warnName="邮编" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mProvince_code">省份</label>
             <div class="controls">
				<select id="mProvince_code" gov-value="${hsubcompany.province_code }"><option value="">请选择</option></select>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mCity_code">城市</label>
             <div class="controls">
			    <select id="mCity_code" gov-value="${hsubcompany.city_code }"><option value="">请选择</option></select>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mArea_code">地区</label>
             <div class="controls">
			     <select id="mArea_code" gov-value="${hsubcompany.area_code }"><option value="">请选择</option></select>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
     </form>
 </div>
 <div class="modal-footer">
     <button type="button" data-dismiss="modal" class="btn btn-boo">取消</button>
     <button type="submit" class="btn btn-green" onclick="update()">更新</button>
 </div>
   <%@ include file="/WEB-INF/page/hSubCompany/companyList.jsp" %>
<script type="text/javascript">
$(document).ready(function(){
	new $gov("mProvince_code","mCity_code","mArea_code").init();
})
	function showCompany(){
		$("#sss5").click();
		searchData5(1);
	}
	function cleanOn(){
		$("#companyName").val('');
		$("#aCompanyId").val('');
	}
	// 执行更新
	function update(){
		var s_id = getVal("mS_id");
		var c_id = getVal("aCompanyId");
		var sub_name = getVal("mSub_name");
		var invoice_title = getVal("mInvoice_title");
		var consignee = getVal("mConsignee");
		var consignee_phone = getVal("mConsignee_phone");
		var consignee_tel1 = getVal("mConsignee_tel1");
		var consignee_tel2 = getVal("mConsignee_tel2");
		var consignee_address = getVal("mConsignee_address");
		var create_time = getVal("mCreate_time");
		var update_time = getVal("mUpdate_time");
		var zip_code = getVal("mZip_code");
		var province_code = getVal("mProvince_code");
		var city_code = getVal("mCity_code");
		var area_code = getVal("mArea_code");
		var flag = validateForm('updateForm');
	    if (flag){ 
	        $.post("<c:url value='/hSubCompany/updateHSubCompany'/>",
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
		              	tipOk("更新成功!");
		             } else {
		            	 tipError(result.message);
		             }
		            $modal.modal('hide');
	        });
	    }
	}
</script>