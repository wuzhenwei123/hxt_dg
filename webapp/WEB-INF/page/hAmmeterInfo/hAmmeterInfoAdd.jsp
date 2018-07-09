<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>添加缴费号</h4>
</div>
<div class="modal-body">
    <form id="addForm" class="well well-nice form-horizontal">
   <!--       <div class="control-group">
             <label class="control-label" for="aC_id">企业ID</label>
             <div class="controls">
			    <input type="text" placeholder="c_id" id="aC_id" isNotNull="true" warnName="企业ID" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div> -->
         <div class="control-group">
             <label class="control-label" for="aC_id">企业</label>
             <div class="controls">
			    <input type="text" placeholder="点击选择企业" id="companyName" isNotNull="true" data-toggle="modal" onclick="showCompany();" warnName="企业" readonly="readonly"/>
			    <a href="#stack2" id="sss" style="display: none;" class="btn btn-primary btn-large btn-block" data-toggle="modal">Demo - Modal Stacled</a> 
			    <input type="hidden" id="aC_id" value=""/>
                 <span class="help-inline text-red">*</span>
                 <a type="button" class="btn btn-boo" onclick="cleanOn2()">清除</a>
             </div>
         </div>
<!--          <div class="control-group">
             <label class="control-label" for="aS_id">分组ID</label>
             <div class="controls">
			    <input type="text" placeholder="s_id" id="aS_id" isNotNull="true" warnName="分组ID" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div> -->
         <div class="control-group">
             <label class="control-label" for="aS_id">分组</label>
             <div class="controls">
			    <input type="text" id="subCompanyName" placeholder="点击选择分组" isNotNull="true" data-toggle="modal" onclick="showSubCompany();" warnName="企业" readonly="readonly"/>
			    <a href="#stack4" id="ddd" style="display: none;" class="btn btn-primary btn-large btn-block" data-toggle="modal">Demo - Modal Stacled</a> 
			    <input type="hidden" id="aS_id" value=""/>
                 <span class="help-inline text-red">*</span>
                 <a type="button" class="btn btn-boo" onclick="cleanOn3()">清除</a>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aAmmeter_no">缴费单号</label>
             <div class="controls">
			    <input type="text" placeholder="缴费单号" id="aAmmeter_no" isNotNull="true" warnName="缴费单号" />
                 <span class="help-inline text-red">*</span>
                 <a type="button" class="btn btn-boo" onclick="getAmmeterInfo()">校验</a>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aAmmeter_name">客户名称</label>
             <div class="controls">
			    <input type="text" placeholder="客户名称" id="aAmmeter_name" isNotNull="true" warnName="客户名称" readonly="readonly" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aAmmeter_type">电表类型</label>
             <div class="controls">
<!-- 			    <input type="text" placeholder="ammeter_type" id="aAmmeter_type" isNotNull="true" warnName="电表类型" /> -->
			    <select id="aAmmeter_type">
			    	<option value="A">抄表电</option>
<!-- 			    	<option value="B">智能表</option> -->
			    </select>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aPay_status">缴费状态</label>
             <div class="controls">
<!-- 			    <input type="text" placeholder="pay_status" id="aPay_status" isNotNull="true" warnName="缴费状态" /> -->
			    <select id="aPay_status">
			    	<option value="1">正常</option>
			    	<option value="0">暂停</option>
			    </select>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aPay_status">是否设为代付</label>
             <div class="controls">
<!-- 			    <input type="text" placeholder="pay_status" id="aPay_status" isNotNull="true" warnName="缴费状态" /> -->
			    <select id="aProxy_flag">
			    	<option value="0" selected="selected">否</option>
			    	<option value="1">是</option>
			    </select>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
<!--          <div class="control-group"> -->
<!--              <label class="control-label" for="aOperator_id">operator_id</label> -->
<!--              <div class="controls"> -->
<!-- 			    <input type="text" placeholder="operator_id" id="aOperator_id" isNotNull="true" warnName="operator_id" /> -->
<!--                  <span class="help-inline text-red">*</span> -->
<!--              </div> -->
<!--          </div> -->
         <div class="control-group">
             <label class="control-label" for="aFp_style">发票类型</label>
             <div class="controls">
<!-- 			    <input type="text" placeholder="fp_style" id="aFp_style" isNotNull="true" warnName="发票类型" /> -->
			    <select id="aFp_style">
			    	<option value="">未设置</option>
			    	<option value="1">恒信通发票</option>
			    	<option value="2">电力增值税普票</option>
			    	<option value="3">电力增值税专票</option>
			    	<option value="4">电力额定发票</option>
			    </select>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aAmmeter_address">客户详细地址</label>
             <div class="controls">
			    <input type="text" placeholder="客户详细地址" id="aAmmeter_address" isNotNull="true" warnName="客户详细地址"/>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aProvince_code">省份</label>
             <div class="controls">
<!-- 			    <input type="text" placeholder="province_code" id="aProvince_code" isNotNull="true" warnName="province_code" /> -->
				<select id="aProvince_code"><option value="">请选择</option></select>
<!--                  <span class="help-inline text-red">*</span> -->
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aCity_code">城市</label>
             <div class="controls">
<!-- 			    <input type="text" placeholder="city_code" id="aCity_code" isNotNull="true" warnName="city_code" /> -->
			    <select id="aCity_code"><option value="">请选择</option></select>
<!--                  <span class="help-inline text-red">*</span> -->
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aArea_code">地区</label>
             <div class="controls">
<!-- 			    <input type="text" placeholder="area_code" id="aArea_code" isNotNull="true" warnName="area_code" /> -->
			     <select id="aArea_code"><option value="">请选择</option></select>
<!--                  <span class="help-inline text-red">*</span> -->
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aProfit_id">分润比例</label>
             <div class="controls">
			    <input type="text" id="profitName" isNotNull="true" data-toggle="modal" onclick="showProfit();" warnName="分润比例" readonly="readonly"/>
			    <a href="#stack1" id="xxx" style="display: none;" class="btn btn-primary btn-large btn-block" data-toggle="modal">Demo - Modal Stacled</a> 
			    <input type="hidden" id="aProfit_id" value=""/>
                 <span class="help-inline text-red">*</span>
                 <a type="button" class="btn btn-boo" onclick="cleanOn()">清除</a>
             </div>
         </div>
     </form>
 </div>
 <div class="modal-footer">
     <button type="button" data-dismiss="modal" class="btn btn-boo">取消</button>
     <button type="submit" class="btn btn-green" onclick="add()">保存</button>
 </div>
 <%@ include file="/WEB-INF/page/hAmmeterInfo/profitList.jsp" %>
 <%@ include file="/WEB-INF/page/hAmmeterInfo/companyList.jsp" %>
 <%@ include file="/WEB-INF/page/hAmmeterInfo/subCompanyList.jsp" %>
<script type="text/javascript">
$(document).ready(function(){
	new $gov("aProvince_code","aCity_code","aArea_code").init();
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
			searchData4(1);
		}else{
			tipError('请先选择企业')
		}
	}
	//执行添加
	function add(){
		var a_id = getVal("aA_id");
		var c_id = getVal("aC_id");
		var s_id = getVal("aS_id");
		var ammeter_no = getVal("aAmmeter_no");
		var ammeter_name = getVal("aAmmeter_name");
		var ammeter_type = getVal("aAmmeter_type");
		var last_pay_day = getVal("aLast_pay_day");
		var pay_status = getVal("aPay_status");
		var operator_id = getVal("aOperator_id");
		var create_time = getVal("aCreate_time");
		var update_time = getVal("aUpdate_time");
		var fp_style = getVal("aFp_style");
		var ammeter_address = getVal("aAmmeter_address");
		var province_code = getVal("aProvince_code");
		var city_code = getVal("aCity_code");
		var area_code = getVal("aArea_code");
		var proxy_flag = getVal("aProxy_flag");
		var province_name = $("#aProvince_code").find("option:selected").text();
		var city_name = $("#aCity_code").find("option:selected").text();
		var area_name = $("#aArea_code").find("option:selected").text();
		var profit_id = getVal("aProfit_id");
		var flag = validateForm('addForm');
	    if (flag){ 
	        $.post("<c:url value='/hAmmeterInfo/addHAmmeterInfo'/>",
	        	{
		    		a_id : a_id,
		    		c_id : c_id,
		    		s_id : s_id,
		    		ammeter_no : ammeter_no,
		    		ammeter_name : ammeter_name,
		    		ammeter_type : ammeter_type,
		    		last_pay_day : last_pay_day,
		    		pay_status : pay_status,
		    		operator_id : operator_id,
		    		create_time : create_time,
		    		update_time : update_time,
		    		fp_style : fp_style,
		    		proxy_flag : proxy_flag,
		    		ammeter_address : ammeter_address,
		    		province_code : province_code,
		    		city_code : city_code,
		    		area_code : area_code,
		    		province_name : province_name,
		    		city_name : city_name,
		    		area_name : area_name,
		    		profit_id : profit_id,
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
	
	function getAmmeterInfo() {
		$.post("<c:url value='/hAmmeterInfo/getAmmeterInfo'/>", {
			ammeterNum : getVal("aAmmeter_no")
		}, function(data) {
			var result = eval('(' + data + ')');
			if(result.code=='1'){
				var json = result.items;
				if(json.resultCode=='00'){
					$("#aAmmeter_name").val(json.resultInfo.accountName);
					$("#aAmmeter_address").val(json.resultInfo.address);
				}else{
					tipError('查询失败');
				}
			}else{
				tipError(result.message);
			}
		})
	}
</script>