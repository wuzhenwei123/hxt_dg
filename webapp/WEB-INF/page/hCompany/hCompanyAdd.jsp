<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>添加客户单位</h4>
</div>
<input type="hidden" id="currPage1" value="1"><!-- 当前页码 -->
<input type="hidden" id="returnNum1" value="10"><!-- 分页返回 -->
<div class="modal-body">
    <form id="addForm" class="well well-nice form-horizontal">
         <div class="control-group">
             <label class="control-label" for="aName">客户单位名称</label>
             <div class="controls">
			    <input type="text" placeholder="客户单位名称" id="aName" isNotNull="true" warnName="客户单位名称" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aContact_name">业务联系人</label>
             <div class="controls">
			    <input type="text" placeholder="业务联系人" id="aContact_name" isNotNull="true" warnName="业务联系人" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aContact_phone">业务联系人手机</label>
             <div class="controls">
			    <input type="text" placeholder="业务联系人手机" id="aContact_phone" isNotNull="true" warnName="业务联系人手机" />
                 <span class="help-inline text-red">*</span>
                 <input id='codeBtn' type="button" value="发送手机验证码" onclick="sendCode(this)">
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="vercode">手机验证码</label>
             <div class="controls">
			    <input type="text" placeholder="手机验证码 " id="vercode" isNotNull="true" warnName="验证码 " />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aContact_mail">业务联系人邮箱</label>
             <div class="controls">
			    <input type="text" placeholder="业务联系人邮箱" id="aContact_mail" warnName="业务联系人邮箱" />
             </div>
         </div>
        <!--  <div class="control-group">
             <label class="control-label" for="aFp_name">发票收件人姓名</label>
             <div class="controls">
			    <input type="text" placeholder="发票收件人姓名" id="aFp_name" isNotNull="true" warnName="发票收件人姓名" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aFp_phone">发票收件人手机</label>
             <div class="controls">
			    <input type="text" placeholder="发票收件人手机" id="aFp_phone" warnName="发票收件人手机" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aFp_telephone">发票收件人电话</label>
             <div class="controls">
			    <input type="text" placeholder="发票收件人电话" id="aFp_telephone" warnName="发票收件人电话" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aFp_address">发票收件人地址</label>
             <div class="controls">
			    <input type="text" placeholder="发票收件人地址" id="aFp_address" isNotNull="true" warnName="发票收件人地址" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div> -->
<!--          <div class="control-group">
             <label class="control-label" for="aFax">传真总机</label>
             <div class="controls">
			    <input type="text" placeholder="传真总机" id="aFax" onkeyup="this.value=this.value.replace(/\D/gi,'')" maxsize="11" minsize="8"  warnName="传真总机" isNotNull="true" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aFax_ext">传真分机</label>
             <div class="controls">
			    <input type="text" placeholder="传真分机" id="aFax_ext" warnName="fax_ext" />
             </div>
         </div> -->
     <!--     <div class="control-group">
             <label class="control-label" for="aYwyId">业务员</label>
             <div class="controls">
			    <input type="text" placeholder="业务员" id="ywyname" isNotNull="true" warnName="业务员" readonly="readonly" onclick="selOne()" />
			    <input type="hidden" placeholder="业务员" id="aYwyId" isNotNull="true" warnName="业务员" />
			    <a href="#stack1" id="xxx" style="display: none;" class="btn btn-primary btn-large btn-block" data-toggle="modal"> </a>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div id="stack1" class="modal hide fade" tabindex="-1" data-focus-on="input:first">
		    <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
		        <h4>选择业务员</h4>
		    </div>
		    <div class="modal-body">
			    <input type="text" id="fOneAgentName" class="span2 margin5" placeholder="业务员名称">
			    <a href="javascript:searchData1('1');" class="btn btn-green">查询</a>
		    	<div class="widget-content" id="demo2">
			         <div class="widget-body">
			             <table id="exampleDTC1" class="table table-striped table-content table-condensed boo-table table-hover bg-blue-light">
				         </table>
				         <div class="widget-footer">
				             <div class="btn-toolbar pull-left">
				             </div>
				             <div class="pagination pagination-btn pull-right">
				             	<div id="kkpager1"></div>
				             </div>
				         </div>
			         </div>
				</div>
		    </div>
		    <div class="modal-footer"> <a type="button" id="closeOne" data-dismiss="modal" class="btn btn-boo">关闭</a></div>
		</div> -->
		 <div class="control-group">
             <label class="control-label" for="aCom_business_doc_type">营业证件类型</label>
             <div class="controls">
				<select id="aCom_business_doc_type" warnName="营业证件类型">
					<option value="">请选择</option>
					<option value="1">五证合一</option>
					<option value="2">非五证合一</option>
				</select>
             </div>
         </div>
     	<div class="control-group">
             <label class="control-label" for="aCredit_code">企业统一社会信用代码</label>
             <div class="controls">
			    <input type="text" placeholder="企业统一社会信用代码" id="aCredit_code"  warnName="企业统一社会信用代码" />
                 
             </div>
         </div>
     	<div class="control-group">
             <label class="control-label" for="aCom_license_no">营业执照号码</label>
             <div class="controls">
			    <input type="text" placeholder="营业执照号码" id="aCom_license_no"  warnName="营业执照号码" />
                 
             </div>
         </div>
		 <div class="control-group">
             <label class="control-label" for="aProvince_code">省份</label>
             <div class="controls">
				<select id="aProvince_code"><option value="">请选择</option></select>
                 
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aCity_code">城市</label>
             <div class="controls">
			    <select id="aCity_code"><option value="">请选择</option></select>
                 
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aArea_code">地区</label>
             <div class="controls">
			     <select id="aArea_code"><option value="">请选择</option></select>
                 
             </div>
         </div>
     	<div class="control-group">
             <label class="control-label" for="aCom_address">详细营业地址</label>
             <div class="controls">
			    <input type="text" placeholder="详细营业地址" id="aCom_address"  warnName="详细营业地址" />
                 
             </div>
         </div>
     	<div class="control-group">
             <label class="control-label" for="aCom_zip_code">营业地址邮编</label>
             <div class="controls">
			    <input type="text" placeholder="营业地址邮编" id="aCom_zip_code"  warnName="营业地址邮编" />
                 
             </div>
         </div>
<!--      	<div class="control-group"> -->
<!--              <label class="control-label" for="aZp_code">增值税发票纳税人识别号信息</label> -->
<!--              <div class="controls"> -->
<!-- 			    <input type="text" placeholder="增值税发票纳税人识别号信息" id="aZp_code"  warnName="增值税发票纳税人识别号信息" /> -->
                 
<!--              </div> -->
<!--          </div> -->
<!--          <div class="control-group"> -->
<!--              <label class="control-label" for="aZp_province_code">增票省份</label> -->
<!--              <div class="controls"> -->
<!-- 				<select id="aZp_province_code"><option value="">请选择</option></select> -->
                 
<!--              </div> -->
<!--          </div> -->
<!--          <div class="control-group"> -->
<!--              <label class="control-label" for="aZp_city_code">增票城市</label> -->
<!--              <div class="controls"> -->
<!-- 			    <select id="aZp_city_code"><option value="">请选择</option></select> -->
                 
<!--              </div> -->
<!--          </div> -->
<!--          <div class="control-group"> -->
<!--              <label class="control-label" for="aZp_area_code">增票地区</label> -->
<!--              <div class="controls"> -->
<!-- 			     <select id="aZp_area_code"><option value="">请选择</option></select> -->
                 
<!--              </div> -->
<!--          </div> -->
<!--      	<div class="control-group"> -->
<!--              <label class="control-label" for="aZp_address">增票开票信息地址详细信息</label> -->
<!--              <div class="controls"> -->
<!-- 			    <input type="text" placeholder="增票开票信息地址详细信息" id="aZp_address"  warnName="营业地址邮编" /> -->
                 
<!--              </div> -->
<!--          </div> -->
<!--      	<div class="control-group"> -->
<!--              <label class="control-label" for="aZp_area_number">增票开票信息电话区号</label> -->
<!--              <div class="controls"> -->
<!-- 			    <input type="text" placeholder="增票开票信息电话区号" id="aZp_area_number"  warnName="增票开票信息电话区号" /> -->
                 
<!--              </div> -->
<!--          </div> -->
<!--      	<div class="control-group"> -->
<!--              <label class="control-label" for="aZp_phone">增票开票信息电话</label> -->
<!--              <div class="controls"> -->
<!-- 			    <input type="text" placeholder="增票开票信息电话" id="aZp_phone"  warnName="营业地址邮编" /> -->
                 
<!--              </div> -->
<!--          </div> -->
<!--      	<div class="control-group"> -->
<!--              <label class="control-label" for="aZp_bank_code">增票开票信息开户行</label> -->
<!--              <div class="controls"> -->
<!-- 			    <input type="text" placeholder="增票开票信息开户行" id="aZp_bank_code"  warnName="增票开票信息开户行" /> -->
<!--              </div> -->
<!--          </div> -->
<!--      	<div class="control-group"> -->
<!--              <label class="control-label" for="aZp_bank_account">增票开票信息开户行账号</label> -->
<!--              <div class="controls"> -->
<!-- 			    <input type="text" placeholder="增票开票信息开户行账号" id="aZp_bank_account"  warnName="营业地址邮编" /> -->
<!--              </div> -->
<!--          </div> -->
 <!--     	<div class="control-group">
             <label class="control-label" for="aZp_verify_status">增票开票信息资料是否已审核</label>
             <div class="controls">
			    <input type="text" placeholder="增票开票信息资料是否已审核" id="aZp_verify_status" isNotNull="true" warnName="营业地址邮编" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
     	<div class="control-group">
             <label class="control-label" for="aVerify_status_time">证件资料最后一次审核时间</label>
             <div class="controls">
			    <input type="text" placeholder="证件资料最后一次审核时间" id="aVerify_status_time" isNotNull="true" warnName="营业地址邮编" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
     	<div class="control-group">
             <label class="control-label" for="aZp_verify_time">增票开票资料最后一次审核时间</label>
             <div class="controls">
			    <input type="text" placeholder="增票开票资料最后一次审核时间" id="aZp_verify_time" isNotNull="true" warnName="营业地址邮编" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
     	<div class="control-group">
             <label class="control-label" for="aZp_verify_creater_id">增票开票资料审核人 ID</label>
             <div class="controls">
			    <input type="text" placeholder="增票开票资料审核人 ID" id="aZp_verify_creater_id" isNotNull="true" warnName="营业地址邮编" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
     	<div class="control-group">
             <label class="control-label" for="zUpdate_time">最后一次变更时间</label>
             <div class="controls">
			    <input type="text" placeholder="最后一次变更时间" id="zUpdate_time" isNotNull="true" warnName="营业地址邮编" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
     	<div class="control-group">
             <label class="control-label" for="aUpdate_operator_id">最后一次变更人 ID</label>
             <div class="controls">
			    <input type="text" placeholder="最后一次变更人 ID" id="aUpdate_operator_id" isNotNull="true" warnName="营业地址邮编" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div> -->
          <div class="control-group">
            <label class="control-label" for="aAgentOneName">客户经理</label>
            <div class="controls">
		    <input type="text" placeholder="点击选择客户经理" id="aAgentOneName" isNotNull="true" data-toggle="modal" onclick="modelType=2;showAgentOne();" warnName="客户经理" readonly="readonly"/>
		    <a href="#stack2" id="sss" style="display: none;" class="btn btn-primary btn-large btn-block" data-toggle="modal">Demo - Modal Stacled</a> 
		    <input type="hidden" id="agentOneOpenId" value=""/>
		    <input type="hidden" id="agentOneName" value=""/>
                <span class="help-inline text-red">*</span>
                <a type="button" class="btn btn-boo" onclick="modelType=2;cleanOn2()">清除</a>
            </div>
         </div>
          <div class="control-group">
            <label class="control-label" for="aAgentTwoName">代理</label>
            <div class="controls">
		    <input type="text" placeholder="点击选择代理" id="aAgentTwoName" isNotNull="" data-toggle="modal" onclick="modelType=2;showAgentTwo();" warnName="代理" readonly="readonly"/>
		    <a href="#stack3" id="ddd" style="display: none;" class="btn btn-primary btn-large btn-block" data-toggle="modal">Demo - Modal Stacled</a> 
		    <input type="hidden" id="agentTwoOpenId" value=""/>
		    <input type="hidden" id="agentTwoName" value=""/>
<!--                 <span class="help-inline text-red">*</span> -->
                <a type="button" class="btn btn-boo" onclick="modelType=2;cleanOn3()">清除</a>
            </div>
         </div>
          <div class="control-group">
            <label class="control-label" for="aServicerName">服务人员</label>
            <div class="controls">
		    <input type="text" placeholder="点击选择服务人员" id="aServicerName" isNotNull="" data-toggle="modal" onclick="modelType=2;showServicer();" warnName="服务人员" readonly="readonly"/>
		    <a href="#stack4" id="ccc" style="display: none;" class="btn btn-primary btn-large btn-block" data-toggle="modal">Demo - Modal Stacled</a> 
		    <input type="hidden" id="servicerId" value=""/>
		    <input type="hidden" id="servicerName" value=""/>
<!--                 <span class="help-inline text-red">*</span> -->
                <a type="button" class="btn btn-boo" onclick="modelType=2;cleanOn4()">清除</a>
            </div>
         </div>
     </form>
 </div>
 <div class="modal-footer">
     <button type="button" data-dismiss="modal" class="btn btn-boo">取消</button>
     <button type="submit" class="btn btn-green" onclick="add()">保存</button>
 </div>
<%--  <%@ include file="/WEB-INF/page/hCompany/agentOne.jsp" %> --%>
<%--  <%@ include file="/WEB-INF/page/hCompany/agentTwo.jsp" %> --%>
<%--  <%@ include file="/WEB-INF/page/hCompany/servicer.jsp" %> --%>
<script type="text/javascript">
$(document).ready(function(){
	new $gov("aZp_province_code","aZp_city_code","aZp_area_code").init();
	new $gov("aProvince_code","aCity_code","aArea_code").init();
})
function showAgentOne(){
	$("#sss").click();
	searchData2(1);
}
function showAgentTwo(){
	var agentOneOpenId = $("#agentOneOpenId").val();
	if(agentOneOpenId==""){
		tipError("请先选择客户经理");
	}else{
		$("#ddd").click();
		searchData5(1);
	}
	
}
function showServicer(){
	$("#ccc").click();
	searchData4(1);
}
function cleanOn2(){
	$("#aAgentOneName").val('');
	$("#agentOneOpenId").val('');
	$("#agentOneName").val('');
}
function cleanOn3(){
	$("#aAgentTwoName").val('');
	$("#agentTwoOpenId").val('');
	$("#agentTwoName").val('');
}
function cleanOn4(){
	$("#aServicerName").val('');
	$("#servicerId").val('');
	$("#servicerName").val('');
}
	//执行添加
	function add(){
		var id = getVal("aId");
		var name = getVal("aName");
		var user_id = getVal("aYwyId");
		var contact_name = getVal("aContact_name");
		var contact_phone = getVal("aContact_phone");
		var contact_mail = getVal("aContact_mail");
// 		var fp_name = getVal("aFp_name");
// 		var fp_phone = getVal("aFp_phone");
// 		var fp_telephone = getVal("aFp_telephone");
// 		var fp_address = getVal("aFp_address");
// 		var fax = getVal("aFax");
// 		var fax_ext = getVal("aFax_ext");
		var ywyId = getVal("aYwyId");
		var vercode = getVal("vercode");
// 		var status = getVal("aStatus");
// 		var create_time = getVal("aCreate_time");
		/* var verify_reason = getVal("aVerify_reason");
		var verify_user_id = getVal("aVerify_user_id");
		var com_bank_code = getVal("aCom_bank_code");
		var verify_status = getVal("aVerify_status");
		var com_license_img = getVal("aCom_license_img");
		var com_tax_no = getVal("aCom_tax_no");
		var com_tax_img = getVal("aCom_tax_img");
		var com_dept_code = getVal("aCom_dept_code");
		var com_dept_img = getVal("aCom_dept_img");
		var com_duty_no = getVal("aCom_duty_no");
		var com_bank_name = getVal("aCom_bank_name");
		var com_account_name = getVal("aCom_account_name");
		var com_account_no = getVal("aCom_account_no");
		var remark1 = getVal("aRemark1");
		var remark2 = getVal("aRemark2");
		var remark3 = getVal("aRemark3");
		var remark4 = getVal("aRemark4");
		var remark5 = getVal("aRemark5");
		var remark6 = getVal("aRemark6"); */

		//新增
		var com_business_doc_type = getVal("aCom_business_doc_type");
		var credit_code = getVal("aCredit_code");
		var com_license_no = getVal("aCom_license_no");
		var province_code = getVal("aProvince_code");
		var city_code = getVal("aCity_code");
		var area_code = getVal("aArea_code");
		var com_address = getVal("aCom_address");
		var com_zip_code = getVal("aCom_zip_code");
		var zp_code  = getVal("aZp_code");
		var zp_province_code = getVal("aZp_province_code");
		var zp_city_code = getVal("aZp_city_code");
		var zp_area_code = getVal("aZp_area_code");
		
		var zp_address = getVal("aZp_address");
		var zp_area_number = getVal("aZp_area_number");
		var zp_phone = getVal("aZp_phone");
		var zp_bank_code = getVal("aZp_bank_code");
		var zp_bank_account = getVal("aZp_bank_account");
// 		var zp_verify_status =getVal("aZp_verify_status");
// 		var verify_status_time = getVal("aZverify_status_time");
// 		var zp_verify_time = getVal("aZp_verify_time");
// 		var zp_verify_creater_id = getVal("aZp_verify_creater_id");
// 		var update_time = getVal("aUpdate_time");
// 		var update_operator_id = getVal("aUpdate_operator_id");
		if(!validatemobile(contact_phone)){
			$("#aContact_phone").next().html("<i class='fontello-icon-cancel-circle'> 业务联系人手机格式不正确</i>");
			$("#aContact_phone").parent().parent().removeClass("success");
			$("#aContact_phone").parent().parent().addClass("error");
			return false;
		}else{
			$("#aContact_phone").next().html("<i class='fontello-icon-ok-circle'></i>");
			$("#aContact_phone").parent().parent().removeClass("error");
			$("#aContact_phone").parent().parent().addClass("success");
		}
		if(contact_mail){
			if(!checkEmail(contact_mail)){
				$("#aContact_mail").next().html("<i class='fontello-icon-cancel-circle'> 业务联系人邮箱格式不正确</i>");
				$("#aContact_mail").parent().parent().removeClass("success");
				$("#aContact_mail").parent().parent().addClass("error");
				return false;
			}else{
				$("#aContact_mail").next().html("<i class='fontello-icon-ok-circle'></i>");
				$("#aContact_mail").parent().parent().removeClass("error");
				$("#aContact_mail").parent().parent().addClass("success");
			}
		}
// 		if(!checkFax($("#aFax").val())){
// 			$("#aFax").next().html("<i class='fontello-icon-cancel-circle'> 传真总机格式不正确</i>");
// 			$("#aFax").parent().parent().removeClass("success");
// 			$("#aFax").parent().parent().addClass("error");
// 			return false;
// 		}else{
// 			$("#aFax").next().html("<i class='fontello-icon-ok-circle'></i>");
// 			$("#aFax").parent().parent().removeClass("error");
// 			$("#aFax").parent().parent().addClass("success");
// 		}
// 		if(fax_ext){
// 			if(!checkFax_ext($("#aFax_ext").val())){
// 				$("#aFax_ext").next().html("<i class='fontello-icon-cancel-circle'> 传真分机格式不正确</i>");
// 				$("#aFax_ext").parent().parent().removeClass("success");
// 				$("#aFax_ext").parent().parent().addClass("error");
// 				return false;
// 			}else{
// 				$("#aFax_ext").next().html("<i class='fontello-icon-ok-circle'></i>");
// 				$("#aFax_ext").parent().parent().removeClass("error");
// 				$("#aFax_ext").parent().parent().addClass("success");
// 			}
// 		}
// 		if(!fp_phone&&!fp_telephone){
// 			$("#aFp_phone").next().html("<i class='fontello-icon-cancel-circle'> 发票收件人手机和电话必填一项</i>");
// 			$("#aFp_phone").parent().parent().removeClass("success");
// 			$("#aFp_phone").parent().parent().addClass("error");
// 			$("#aFp_telephone").next().html("<i class='fontello-icon-cancel-circle'> 发票收件人手机和电话必填一项</i>");
// 			$("#aFp_telephone").parent().parent().addClass("error");
// 			$("#aFp_telephone").parent().parent().removeClass("success");
// 			return false;
// 		}else{
// 			$("#aFp_phone").next().html("<i class='fontello-icon-cancel-circle'></i>");
// 			$("#aFp_phone").parent().parent().addClass("success");
// 			$("#aFp_phone").parent().parent().removeClass("error");
// 			$("#aFp_telephone").next().html("<i class='fontello-icon-cancel-circle'></i>");
// 			$("#aFp_telephone").parent().parent().removeClass("error");
// 			$("#aFp_telephone").parent().parent().addClass("success");
// 		}
// 		if(!validatemobile(fp_phone)){
// 			$("#aFp_phone").next().html("<i class='fontello-icon-cancel-circle'> 发票收件人手机格式不正确</i>");
// 			$("#aFp_phone").parent().parent().removeClass("success");
// 			$("#aFp_phone").parent().parent().addClass("error");
// 			return false;
// 		}else{
// 			$("#aFp_phone").next().html("<i class='fontello-icon-ok-circle'></i>");
// 			$("#aFp_phone").parent().parent().removeClass("error");
// 			$("#aFp_phone").parent().parent().addClass("success");
// 		}
		if(com_business_doc_type ==1){
			$("#aCredit_code").attr("isNotNull","true");
		}else{
			$("#aCredit_code").attr("isNotNull","");
		}
		if(com_business_doc_type ==2){
			$("#aCom_license_no").attr("isNotNull","true");
		}else{
			$("#aCom_license_no").attr("isNotNull","");
		}
		var flag = validateForm('addForm');
	    if (flag){ 
	        $.post("<c:url value='/hCompany/addHCompany'/>",
	        	{
		    		id : id,
		    		name : name,
		    		user_id : user_id,
		    		contact_name : contact_name,
		    		contact_phone : contact_phone,
		    		contact_mail : contact_mail,
// 		    		fp_name : fp_name,
// 		    		fp_phone : fp_phone,
// 		    		fp_telephone : fp_telephone,
// 		    		fp_address : fp_address,
// 		    		fax : fax,
// 		    		fax_ext : fax_ext,
		    		ywyId : ywyId,
		    		vercode : vercode,
// 		    		status : status,
// 		    		create_time : create_time,
		    		/* verify_reason : verify_reason,
		    		verify_user_id : verify_user_id,
		    		com_bank_code : com_bank_code,
		    		verify_status : verify_status,
		    		com_license_img : com_license_img,
		    		com_tax_no : com_tax_no,
		    		com_tax_img : com_tax_img,
		    		com_dept_code : com_dept_code,
		    		com_dept_img : com_dept_img,
		    		com_duty_no : com_duty_no,
		    		com_bank_name : com_bank_name,
		    		com_account_name : com_account_name,
		    		com_account_no : com_account_no,
		    		remark1 : remark1,
		    		remark2 : remark2,
		    		remark3 : remark3,
		    		remark4 : remark4,
		    		remark5 : remark5,
		    		remark6 : remark6, */
		    		//新增
		    		credit_code : credit_code,
		    		com_license_no : com_license_no,
		    		province_code : province_code,
		    		city_code : city_code,
		    		area_code : area_code,
		    		com_address : com_address,
		    		com_zip_code:com_zip_code,
		    		zp_code:zp_code,
		    		zp_province_code:zp_province_code,
		    		zp_city_code:zp_city_code,
		    		zp_area_code:zp_area_code,
		    		
		    		zp_address : zp_address,
		    		zp_area_number : zp_area_number,
		    		zp_phone : zp_phone,
		    		zp_bank_code : zp_bank_code,
		    		zp_bank_account : zp_bank_account,
		    		com_business_doc_type :com_business_doc_type,
		    		agentOneOpenId : $("#agentOneOpenId").val(),
		    		agentOneName : $("#agentOneName").val(),
		    		agentTwoOpenId : $("#agentTwoOpenId").val(),
		    		agentTwoName : $("#agentTwoName").val(),
		    		servicerId : $("#servicerId").val(),
		    		servicerName : $("#servicerName").val(),
		    		/* zp_verify_status : zp_verify_status,
		    		verify_status_time : verify_status_time,
		    		zp_verify_time : zp_verify_time,
		    		zp_verify_creater_id : zp_verify_creater_id,
		    		update_time : update_time,
		    		update_operator_id : update_operator_id, */
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
	function sendCode(){
		var phone = $.trim($("#aContact_phone").val());
		if(phone == ''){
			tipError("请填写您的手机号");
		}else{
			if(!validatemobile(phone)){
				setRed($("#aContact_phone"),'业务联系人手机格式不正确');
				return false;
			}else{
				setGreen($("#aContact_phone"),"");
			}
			btnNum();
			$.post("<c:url value='/hCompany/sendCode'/>",
        	{
				phone :phone,
				ranNum:Math.random()
			},
        	function(data){
	        	var result = eval('('+data+')'); 
	            if (result.code == '1') {
	            	tipOk("发送成功，请注意查收验证码");
	             } else {
	            	countdown = 0;
	            	tipError(result.message);
	            	setRed($("#aContact_phone"),'');
	            	$("#codeBtn").val('发送验证码');
					$("#codeBtn").attr('disabled',false);
					$("#codeBtn").attr('onclick','sendCode()');
					clearInterval(intervalID);
	             }
	        });
		}
	}
	
	function validatemobile(mobile){ 
		if (mobile.length == 0) {
			return false;
		}
		if (mobile.length != 11) {
			return false;
		}
		var myreg = /^(0|86|17951)?(13[0-9]|15[012356789]|17[012356789]|18[0-9]|14[012356789])[0-9]{8}$/;
		if (!myreg.test(mobile)) {
			return false;
		}
		return true;
	}
	function checkEmail(str){
		var reg = new RegExp('^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+');
	    if(reg.test(str)){
	    	return true;
	    }else{
			return false;
	    }
	}
	
	
	function selOne(){
		$("#xxx").click();
		searchData1(1);
	}
	function searchData1(pageNo){
		var returnNum = $("#returnNum1").val();
			var sortColumn = $("#sortColumn1").val();
		    var name = $("#fOneAgentName").val().trim();
		    $.getJSON("<c:url value='/manageAdminUser/getManageAdminUserList1'/>",
	        {
		    	realName : $("#fOneAgentName").val(),
	    		status : 1,
	    		pageNo: pageNo,
	    		roleId:143,
	    		rowCount: returnNum, 
				_t: Math.random()
	        },function(data){
	        var result = data;
	        if (result.code == 1) {
	            setTableStr1(result, pageNo, returnNum);
	        } else {
	        	tipError("系统异常!");
	        } 
	    });
	}
	function genTableHeader1(){
		var str = "<thead><tr>" ;
	    	str+= "<th scope=\"col\" class=\"check-col\"></th>";
	    	str+= "<th onselectstart='return false' scope=\"col\">序号</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_name\" column='name' onselectstart='return false' scope=\"col\">业务员名称</th>";
			str+="</tr></thead>";
		return str;
	}
	function setTableStr1(result, pageNo, returnNum){
	 	var tableStr = "<table id='dataTable' class=\"table table-striped table-content table-condensed boo-table table-hover bg-blue-light\">";
	    tableStr += genTableHeader1();
	    var number = (pageNo - 1) * returnNum;
	    tableStr += "<tfoot></tfoot>";
	    tableStr += "<tbody>";
	    var oneAgentOpenId = $("#aCreate_openId").val();
	    for (var k=0; k<result.items.length; k++){      
	        tableStr += "<tr id='DataRow"+k+"'>";
        	tableStr += "<td ><input type=\"radio\" id='"+result.items[k].adminId+"' class=\"checkbox check-row\" value=\"0\" name=\"checkName\" onclick='selonagent(\""+result.items[k].adminId+"\",\""+result.items[k].realName+"\")'/></td>";
        	tableStr += "<td >" + (parseInt(number) + k + 1) + "</td>";
	        tableStr += "<td >" + result.items[k].realName + "</td>";
	        tableStr += "</tr>";            
	    }
	    tableStr += "</tbody>";
	    $("#exampleDTC1").html(tableStr);
	    $("#currPage1").val(pageNo);	
	    $("#demo2").find("radio").uniform();//初始化复选框
	    genPageTag1(pageNo,result.totalResults,returnNum,'kkpager1');
	}
	function selonagent(openId,name){
		$("#ywyname").val(name);
		$("#aYwyId").val(openId);
		$("#closeOne").click();
	}
	var intervalID;
	function btnNum(){
		var num = 59;
		$("#codeBtn").attr('disabled',true);
		$("#codeBtn").val('还剩'+num-- +'秒');
		intervalID = setInterval(function(){
			if(num>0){
				$("#codeBtn").attr('disabled',true);
				$("#codeBtn").attr('onclick','');
				$("#codeBtn").val('还剩'+num+'秒');
				num--;
			}else{
				$("#codeBtn").val('发送验证码');
				$("#codeBtn").attr('disabled','');
				$("#codeBtn").attr('onclick','sendCode()');
				clearInterval(intervalID)
			}
		}, 1000);
	}
	
	function checkFax(o) {
		var pattern = /\d{7,8}/;
		if (o != "") {
			if(o.length!=7&&o.length!=8){
				return false;				
			}else{
				if (!pattern.exec(o)) {
					return false;
				}else{
					return true;
				}
			}
		}
		return true;
	}
	function checkFax_ext(o) {
		var pattern = /\d{3}/;
		if (o != "") {
			if(o.length!=3){
				return false;				
			}else{
				if (!pattern.exec(o)) {
					return false;
				}else{
					return true;
				}
			}
		}
		return true;
	}
</script>