<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>编辑缴费单</h4>
</div>
<input type="hidden" placeholder="mA_id" value="${hammeterinfo.a_id}" id="mA_id" isNotNull="true" warnName="a_id" />
<div class="modal-body">
	<c:if test="${empty message}">
    <form id="updateForm" class="well well-nice form-horizontal">
    
    	
    
         <%-- <div class="control-group">
             <label class="control-label" for="mA_id">a_id</label>
             <div class="controls">
			    <input type="text" placeholder="a_id" value="${hammeterinfo.a_id}" id="mA_id" isNotNull="true" warnName="a_id" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div> --%>
         <div class="control-group" style="display: none;">
             <label class="control-label" for="companyName">企业</label>
             <div class="controls">
			    <input type="text" placeholder="点击选择企业" id="companyName" data-toggle="modal" onclick="showCompany();" warnName="企业" readonly="readonly" value="${hammeterinfo.c_name }"/>
			    <a href="#stack2" id="sss" style="display: none;" class="btn btn-primary btn-large btn-block" data-toggle="modal">Demo - Modal Stacled</a> 
			    <input type="hidden" id="aC_id" value="${hammeterinfo.c_id}" isNotNull="true"/>
                 <span class="help-inline text-red">*</span>
                 <a type="button" class="btn btn-boo" onclick="cleanOn2()">清除</a>
             </div>
         </div>
         <%-- <div class="control-group">
             <label class="control-label" for="mC_id">c_id</label>
             <div class="controls">
			    <input type="text" placeholder="c_id" value="${hammeterinfo.c_id}" id="mC_id" isNotNull="true" warnName="c_id" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div> --%>
   <%--       <div class="control-group">
             <label class="control-label" for="mS_id">s_id</label>
             <div class="controls">
			    <input type="text" placeholder="s_id" value="${hammeterinfo.s_id}" id="mS_id" isNotNull="true" warnName="s_id" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div> --%>
         <div class="control-group" style="display: none;">
             <label class="control-label" for="aS_id">分组</label>
             <div class="controls">
			    <input type="text" id="subCompanyName" placeholder="点击选择分组"  data-toggle="modal" onclick="showSubCompany();" warnName="企业" readonly="readonly" value="${hammeterinfo.sub_name }"/>
			    <a href="#stack3" id="ddd" style="display: none;" class="btn btn-primary btn-large btn-block" data-toggle="modal">Demo - Modal Stacled</a> 
			    <input type="hidden" id="aS_id" value="${hammeterinfo.s_id}" isNotNull="true"/>
                 <span class="help-inline text-red">*</span>
                 <a type="button" class="btn btn-boo" onclick="cleanOn3()">清除</a>
             </div>
         </div>
  <%--        <div class="control-group">
             <label class="control-label" for="mAmmeter_no">缴费单号</label>
             <div class="controls">
			    <input type="text" placeholder="ammeter_no" value="${hammeterinfo.ammeter_no}" id="mAmmeter_no" isNotNull="true" warnName="ammeter_no" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div> --%>
          <div class="control-group">
             <label class="control-label" for="aAmmeter_no">缴费单号</label>
             <div class="controls">
			    <input type="text" placeholder="缴费单号" id="aAmmeter_no" value="${hammeterinfo.ammeter_no}" isNotNull="true" warnName="缴费单号" readonly="readonly"/>
                 <span class="help-inline text-red">*</span>
<!--                  <a type="button" class="btn btn-boo" onclick="getAmmeterInfo()">校验</a> -->
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mAmmeter_name">客户名称</label>
             <div class="controls">
			    <input type="text" placeholder="ammeter_name" value="${hammeterinfo.ammeter_name}" id="mAmmeter_name" isNotNull="true" warnName="客户名称" readonly="readonly"/>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
<%--          <div class="control-group">
             <label class="control-label" for="mAmmeter_type">电表类型</label>
             <div class="controls">
			    <input type="text" placeholder="ammeter_type" value="${hammeterinfo.ammeter_type}" id="mAmmeter_type" isNotNull="true" warnName="电表类型" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div> --%>
        <div class="control-group">
             <label class="control-label" for="mAmmeter_type">电表类型</label>
             <div class="controls">
			    <select id="mAmmeter_type">
			    	<option value="A">抄表电</option>
<!-- 			    	<option value="B">智能表</option> -->
			    </select>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
        <%--  <div class="control-group">
             <label class="control-label" for="mLast_pay_day">last_pay_day</label>
             <div class="controls">
			    <input type="text" placeholder="last_pay_day" value="${hammeterinfo.last_pay_day}" id="mLast_pay_day" isNotNull="true" warnName="last_pay_day" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div> --%>
<%--          <div class="control-group">
             <label class="control-label" for="mPay_status">缴费状态</label>
             <div class="controls">
			    <input type="text" placeholder="pay_status" value="${hammeterinfo.pay_status}" id="mPay_status" isNotNull="true" warnName="pay_status" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div> --%>
          <div class="control-group">
             <label class="control-label" for="mPay_status">缴费状态</label>
             <div class="controls">
			    <select id="mPay_status">
			    	<option value="1" <c:if test="${hammeterinfo.pay_status==1}">selected</c:if>>正常</option>
			    	<option value="0" <c:if test="${hammeterinfo.pay_status==0}">selected</c:if>>暂停</option>
			    </select>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aPay_status">是否设为代付</label>
             <div class="controls">
<!-- 			    <input type="text" placeholder="pay_status" id="aPay_status" isNotNull="true" warnName="缴费状态" /> -->
			    <select id="mProxy_flag">
			    	<option value="0" <c:if test="${hammeterinfo.proxy_flag==0}">selected</c:if>>否</option>
			    	<option value="1" <c:if test="${hammeterinfo.proxy_flag==1}">selected</c:if>>是</option>
			    </select>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
<%--          <div class="control-group">
             <label class="control-label" for="mFp_style">fp_style</label>
             <div class="controls">
			    <input type="text" placeholder="fp_style" value="${hammeterinfo.fp_style}" id="mFp_style" isNotNull="true" warnName="fp_style" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div> --%>
         
		<div class="control-group">
             <label class="control-label" for="mFp_style">发票类型</label>
             <div class="controls">
			    <select id="mFp_style">
			    	<option value="" <c:if test="${empty hammeterinfo.fp_style}">selected</c:if>>未设置</option>
			    	<option value="1" <c:if test="${not empty hammeterinfo.fp_style&&hammeterinfo.fp_style==1}">selected</c:if>>恒信通发票</option>
			    	<option value="2" <c:if test="${not empty hammeterinfo.fp_style&&hammeterinfo.fp_style==2}">selected</c:if>>电力增值税普票</option>
			    	<option value="3" <c:if test="${not empty hammeterinfo.fp_style&&hammeterinfo.fp_style==3}">selected</c:if>>电力增值税专票</option>
			    	<option value="4" <c:if test="${not empty hammeterinfo.fp_style&&hammeterinfo.fp_style==4}">selected</c:if>>电力额定发票</option>
			    </select>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mAmmeter_address">客户详细地址</label>
             <div class="controls">
			    <input type="text" placeholder="ammeter_address" value="${hammeterinfo.ammeter_address}" id="mAmmeter_address" isNotNull="true" warnName="客户详细地址"/>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
  <%--        <div class="control-group">
             <label class="control-label" for="mProvince_code">province_code</label>
             <div class="controls">
			    <input type="text" placeholder="province_code" value="${hammeterinfo.province_code}" id="mProvince_code" isNotNull="true" warnName="province_code" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mCity_code">city_code</label>
             <div class="controls">
			    <input type="text" placeholder="city_code" value="${hammeterinfo.city_code}" id="mCity_code" isNotNull="true" warnName="city_code" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mArea_code">area_code</label>
             <div class="controls">
			    <input type="text" placeholder="area_code" value="${hammeterinfo.area_code}" id="mArea_code" isNotNull="true" warnName="area_code" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div> --%>
         <div class="control-group">
             <label class="control-label" for="mProvince_code">省份</label>
             <div class="controls">
				<select id="mProvince_code" gov-value="${hammeterinfo.province_code }"><option value="">请选择</option></select>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mCity_code">城市</label>
             <div class="controls">
			    <select id="mCity_code" gov-value="${hammeterinfo.city_code }"><option value="">请选择</option></select>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mArea_code">地区</label>
             <div class="controls">
			     <select id="mArea_code" gov-value="${hammeterinfo.area_code }"><option value="">请选择</option></select>
             </div>
         </div>
<%--          <div class="control-group">
             <label class="control-label" for="mProfit_id">profit_id</label>
             <div class="controls">
			    <input type="text" placeholder="profit_id" value="${hammeterinfo.profit_id}" id="mProfit_id" isNotNull="true" warnName="profit_id" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div> --%>
          <div class="control-group">
             <label class="control-label" for="aProfit_id">分润比例</label>
             <div class="controls">
			    <input type="text" id="profitName" isNotNull="true" data-toggle="modal" onclick="showProfit();" warnName="分润比例" readonly="readonly" value="${hammeterinfo.profit_name}"/>
			    <a href="#stack1" id="xxx" style="display: none;" class="btn btn-primary btn-large btn-block" data-toggle="modal">Demo - Modal Stacled</a> 
			    <input type="hidden" id="aProfit_id" value="${hammeterinfo.profit_id}"/>
                 <span class="help-inline text-red">*</span>
                 <a type="button" class="btn btn-boo" onclick="cleanOn()">清除</a>
             </div>
         </div>
     </form>
     </c:if>
     <c:if test="${not empty message}">
     	<div class="control-group">
             <div class="controls">
             	缴费号不存在
             </div>
         </div>
     </c:if>
 </div>
 <div class="modal-footer">
     <button type="button" data-dismiss="modal" class="btn btn-boo">取消</button>
     <c:if test="${empty message}">
     <button type="submit" class="btn btn-green" onclick="update()">更新</button>
     </c:if>
 </div>
<%@ include file="/WEB-INF/page/hAmmeterInfo/profitList.jsp" %>
<%@ include file="/WEB-INF/page/hAmmeterInfo/companyList.jsp" %>
<%@ include file="/WEB-INF/page/hAmmeterInfo/subCompanyList.jsp" %>
<script type="text/javascript">
$(document).ready(function(){
	new $gov("mProvince_code","mCity_code","mArea_code").init();
})
	function cleanOn(){
		$("#profitName").val('');
		$("#aProfit_id").val('');
	}
	function cleanOn2(){
		$("#companyName").val('');
		$("#aC_id").val('');
	}
	function cleanOn3(){
		$("#subCompanyName").val('');
		$("#aS_id").val('');
	}
	function showProfit(){
		$("#xxx").click();
		searchData1(1);
	}
	function showCompany(){
		$("#sss").click();
		searchData2(1);
	}
	function showSubCompany(){
		if($("#aC_id").val()){
			$("#ddd").click();
			searchData3(1);
		}else{
			tipError('请先选择企业')
		}
	}
	// 执行更新
	function update(){
		var a_id = getVal("mA_id");
		var c_id = getVal("aC_id");
		var s_id = getVal("aS_id");
		var ammeter_no = getVal("aAmmeter_no");
		var ammeter_name = getVal("mAmmeter_name");
		var ammeter_type = getVal("mAmmeter_type");
		var last_pay_day = getVal("mLast_pay_day");
		var pay_status = getVal("mPay_status");
		var operator_id = getVal("mOperator_id");
		var create_time = getVal("mCreate_time");
		var update_time = getVal("mUpdate_time");
		var proxy_flag = getVal("mProxy_flag");
		var fp_style = getVal("mFp_style");
		var ammeter_address = getVal("mAmmeter_address");
		var province_code = getVal("mProvince_code");
		var city_code = getVal("mCity_code");
		var area_code = getVal("mArea_code");
		var province_name = $("#mProvince_code").find("option:selected").text();
		var city_name = $("#mCity_code").find("option:selected").text();
		var area_name = $("#mArea_code").find("option:selected").text();
		var profit_id = getVal("aProfit_id");
		var is_payed = getVal("mIs_payed");
// 		var delete_state = getVal("mDelete_state");
		var flag = validateForm('updateForm');
	    if (flag){ 
	        $.post("<c:url value='/hAmmeterInfo/updateHAmmeterInfo'/>",
	        	{
		    		a_id : a_id,
		    		c_id : c_id,
		    		s_id : s_id,
		    		ammeter_no : ammeter_no,
		    		ammeter_name : ammeter_name,
		    		proxy_flag : proxy_flag,
		    		ammeter_type : ammeter_type,
		    		last_pay_day : last_pay_day,
		    		pay_status : pay_status,
		    		operator_id : operator_id,
		    		create_time : create_time,
		    		update_time : update_time,
		    		fp_style : fp_style,
		    		ammeter_address : ammeter_address,
		    		province_code : province_code,
		    		city_code : city_code,
		    		area_code : area_code,
		    		province_name : province_name,
		    		city_name : city_name,
		    		area_name : area_name,
		    		profit_id : profit_id,
		    		is_payed : is_payed,
// 		    		delete_state : delete_state,
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
	function getAmmeterInfo() {
		$.post("<c:url value='/hAmmeterInfo/getAmmeterInfo'/>", {
			ammeterNum : getVal("aAmmeter_no")
		}, function(data) {
			var result = eval('(' + data + ')');
			if(result.code=='1'){
				var json = result.items;
				if(json.resultCode=='00'){
					$("#mAmmeter_name").val(json.resultInfo.accountName);
					$("#mAmmeter_address").val(json.resultInfo.address);
				}else{
					tipError('查询失败');
				}
			}else{
				tipError(result.message);
			}
		})
	}
</script>