<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>编辑客户单位信息</h4>
</div>
<div class="modal-body">
<input type="hidden" id="currPage1" value="1"><!-- 当前页码 -->
<input type="hidden" id="returnNum1" value="10"><!-- 分页返回 -->
    <form id="updateForm" class="well well-nice form-horizontal">
			    <input type="hidden" placeholder="id" value="${hcompany.id}" id="mId" isNotNull="true" warnName="id" />
         <div class="control-group">
             <label class="control-label" for="mName">客户单位名称</label>
             <div class="controls">
			    <input type="text" placeholder="客户单位名称" value="${hcompany.name}" id="mName"  warnName="客户单位名称" />
<!--                  <span class="help-inline text-red">*</span> -->
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mContact_name">业务联系人</label>
             <div class="controls">
			    <input type="text" placeholder="业务联系人" value="${hcompany.contact_name}" id="mContact_name" warnName="业务联系人" />
<!--                  <span class="help-inline text-red">*</span> -->
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mContact_phone">业务联系人手机</label>
             <div class="controls">
			    <input type="text" placeholder="业务联系人手机" value="${hcompany.contact_phone}" id="mContact_phone" isNotNull="true" warnName="业务联系人手机" />
			    <input type="hidden" id="old_phone" value="${hcompany.contact_phone}">
                 <span class="help-inline text-red">*</span>
                  <input type="button" id='codeBtn' value="发送手机验证码" onclick="sendCode(this)">
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="vercode">手机验证码</label>
             <div class="controls">
			    <input type="text" placeholder="手机验证码 " id="vercode" warnName="验证码 " />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mContact_mail">业务联系人邮箱</label>
             <div class="controls">
			    <input type="text" placeholder="业务联系人邮箱" value="${hcompany.contact_mail}" id="mContact_mail" warnName="业务联系人邮箱" />
             </div>
         </div>
        <%--  <div class="control-group">
             <label class="control-label" for="mFp_name">发票收件人姓名</label>
             <div class="controls">
			    <input type="text" placeholder="发票收件人姓名" value="${hcompany.fp_name}" id="mFp_name" isNotNull="true" warnName="发票收件人姓名" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mFp_phone">发票收件人手机</label>
             <div class="controls">
			    <input type="text" placeholder="发票收件人手机" value="${hcompany.fp_phone}" id="mFp_phone" warnName="发票收件人手机" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mFp_telephone">发票收件人电话</label>
             <div class="controls">
			    <input type="text" placeholder="发票收件人电话" value="${hcompany.fp_telephone}" id="mFp_telephone" warnName="发票收件人电话" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mFp_address">发票收件人地址</label>
             <div class="controls">
			    <input type="text" placeholder="发票收件人地址" value="${hcompany.fp_address}" id="mFp_address" isNotNull="true" warnName="发票收件人地址" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div> --%>
         <%-- <div class="control-group">
             <label class="control-label" for="mFax">传真总机</label>
             <div class="controls">
			    <input type="text" placeholder="传真总机" value="${hcompany.fax}" id="mFax" isNotNull="true" warnName="传真总机" readonly="readonly"/>
                 <span class="help-inline text-red">*</span>
             </div>
         </div> --%>
 <%--         <div class="control-group">
             <label class="control-label" for="mFax_ext">传真分机</label>
             <div class="controls">
			    <input type="text" placeholder="传真分机" value="${hcompany.fax_ext}" id="mFax_ext" warnName="传真分机" />
             </div>
         </div> --%>
   <%--       <div class="control-group">
             <label class="control-label" for="mYwyId">业务员</label>
             <div class="controls">
                <input type="text" placeholder="业务员" id="ywyname" isNotNull="true" warnName="业务员" readonly="readonly" onclick="selOne()" value="${hcompany.ywyNick}"/>
                 <input type="hidden" placeholder="业务员" id="mYwyId" isNotNull="true" warnName="业务员" value="${hcompany.ywyId}"/>
			    <input type="text" placeholder="业务员" value="${hcompany.ywyId}" id="mYwyId" isNotNull="true" warnName="业务员" /> 
			    <a href="#stack1" id="xxx" style="display: none;" class="btn btn-primary btn-large btn-block" data-toggle="modal"> </a>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>--%>
         <!-- <div id="stack1" class="modal hide fade" tabindex="-1" data-focus-on="input:first">
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
         <%-- <div class="control-group">
             <label class="control-label" for="mStatus">status</label>
             <div class="controls">
			    <input type="text" placeholder="status" value="${hcompany.status}" id="mStatus" isNotNull="true" warnName="status" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mCreate_time">create_time</label>
             <div class="controls">
		     	<input type="text" placeholder="create_time" value="<fmt:formatDate value="${hcompany.create_time}" type="both"/>" id="mCreate_time" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="create_time" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mVerify_reason">verify_reason</label>
             <div class="controls">
			    <input type="text" placeholder="verify_reason" value="${hcompany.verify_reason}" id="mVerify_reason" isNotNull="true" warnName="verify_reason" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mVerify_user_id">verify_user_id</label>
             <div class="controls">
			    <input type="text" placeholder="verify_user_id" value="${hcompany.verify_user_id}" id="mVerify_user_id" isNotNull="true" warnName="verify_user_id" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mCom_bank_code">com_bank_code</label>
             <div class="controls">
			    <input type="text" placeholder="com_bank_code" value="${hcompany.com_bank_code}" id="mCom_bank_code" isNotNull="true" warnName="com_bank_code" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mVerify_status">verify_status</label>
             <div class="controls">
			    <input type="text" placeholder="verify_status" value="${hcompany.verify_status}" id="mVerify_status" isNotNull="true" warnName="verify_status" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mCom_license_no">com_license_no</label>
             <div class="controls">
			    <input type="text" placeholder="com_license_no" value="${hcompany.com_license_no}" id="mCom_license_no" isNotNull="true" warnName="com_license_no" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mCom_license_img">com_license_img</label>
             <div class="controls">
			    <input type="text" placeholder="com_license_img" value="${hcompany.com_license_img}" id="mCom_license_img" isNotNull="true" warnName="com_license_img" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mCom_tax_no">com_tax_no</label>
             <div class="controls">
			    <input type="text" placeholder="com_tax_no" value="${hcompany.com_tax_no}" id="mCom_tax_no" isNotNull="true" warnName="com_tax_no" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mCom_tax_img">com_tax_img</label>
             <div class="controls">
			    <input type="text" placeholder="com_tax_img" value="${hcompany.com_tax_img}" id="mCom_tax_img" isNotNull="true" warnName="com_tax_img" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mCom_dept_code">com_dept_code</label>
             <div class="controls">
			    <input type="text" placeholder="com_dept_code" value="${hcompany.com_dept_code}" id="mCom_dept_code" isNotNull="true" warnName="com_dept_code" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mCom_dept_img">com_dept_img</label>
             <div class="controls">
			    <input type="text" placeholder="com_dept_img" value="${hcompany.com_dept_img}" id="mCom_dept_img" isNotNull="true" warnName="com_dept_img" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mCom_duty_no">com_duty_no</label>
             <div class="controls">
			    <input type="text" placeholder="com_duty_no" value="${hcompany.com_duty_no}" id="mCom_duty_no" isNotNull="true" warnName="com_duty_no" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mCom_bank_name">com_bank_name</label>
             <div class="controls">
			    <input type="text" placeholder="com_bank_name" value="${hcompany.com_bank_name}" id="mCom_bank_name" isNotNull="true" warnName="com_bank_name" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mCom_account_name">com_account_name</label>
             <div class="controls">
			    <input type="text" placeholder="com_account_name" value="${hcompany.com_account_name}" id="mCom_account_name" isNotNull="true" warnName="com_account_name" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mCom_account_no">com_account_no</label>
             <div class="controls">
			    <input type="text" placeholder="com_account_no" value="${hcompany.com_account_no}" id="mCom_account_no" isNotNull="true" warnName="com_account_no" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mRemark1">remark1</label>
             <div class="controls">
			    <input type="text" placeholder="remark1" value="${hcompany.remark1}" id="mRemark1" isNotNull="true" warnName="remark1" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mRemark2">remark2</label>
             <div class="controls">
			    <input type="text" placeholder="remark2" value="${hcompany.remark2}" id="mRemark2" isNotNull="true" warnName="remark2" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mRemark3">remark3</label>
             <div class="controls">
			    <input type="text" placeholder="remark3" value="${hcompany.remark3}" id="mRemark3" isNotNull="true" warnName="remark3" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mRemark4">remark4</label>
             <div class="controls">
			    <input type="text" placeholder="remark4" value="${hcompany.remark4}" id="mRemark4" isNotNull="true" warnName="remark4" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mRemark5">remark5</label>
             <div class="controls">
			    <input type="text" placeholder="remark5" value="${hcompany.remark5}" id="mRemark5" isNotNull="true" warnName="remark5" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mRemark6">remark6</label>
             <div class="controls">
			    <input type="text" placeholder="remark6" value="${hcompany.remark6}" id="mRemark6" isNotNull="true" warnName="remark6" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div> --%>
          <div class="control-group">
             <label class="control-label" for="aCom_business_doc_type">营业证件类型</label>
             <div class="controls">
				<select id="aCom_business_doc_type" warnName="营业证件类型">
					<option value=""  <c:if test="${empty hcompany.com_business_doc_type}">selected="selected"</c:if>>请选择</option>
					<option value="1"  <c:if test="${ hcompany.com_business_doc_type==1}">selected="selected"</c:if>>五证合一</option>
					<option value="2"  <c:if test="${ hcompany.com_business_doc_type==2}">selected="selected"</c:if>>非五证合一</option>
				</select>
             </div>
         </div>
     	<div class="control-group">
             <label class="control-label" for="aCredit_code">企业统一社会信用代码</label>
             <div class="controls">
			    <input type="text" placeholder="企业统一社会信用代码" id="aCredit_code"  warnName="企业统一社会信用代码" value="${hcompany.credit_code}"/>
                 
             </div>
         </div>
     	<div class="control-group">
             <label class="control-label" for="aCom_license_no">营业执照号码</label>
             <div class="controls">
			    <input type="text" placeholder="营业执照号码" id="aCom_license_no"  warnName="营业执照号码" value="${hcompany.com_license_no}"/>
                 
             </div>
         </div>
         		 <div class="control-group">
             <label class="control-label" for="aProvince_code">省份</label>
             <div class="controls">
				<select id="aProvince_code" gov-value="${hcompany.province_code }"><option value="">请选择</option></select>
                 
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aCity_code">城市</label>
             <div class="controls">
			    <select id="aCity_code" gov-value="${hcompany.city_code }"><option value="">请选择</option></select>
                 
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aArea_code">地区</label>
             <div class="controls">
			     <select id="aArea_code" gov-value="${hcompany.area_code }"><option value="">请选择</option></select>
                 
             </div>
         </div>
     	<div class="control-group">
             <label class="control-label" for="aCom_address">详细营业地址</label>
             <div class="controls">
			    <input type="text" placeholder="详细营业地址" id="aCom_address"  warnName="详细营业地址" value="${hcompany.com_address }"/>
                 
             </div>
         </div>
     	<div class="control-group">
             <label class="control-label" for="aCom_zip_code">营业地址邮编</label>
             <div class="controls">
			    <input type="text" placeholder="营业地址邮编" id="aCom_zip_code"  warnName="营业地址邮编"  value="${hcompany.com_zip_code }"/>
                 
             </div>
         </div>
<!--      	<div class="control-group"> -->
<!--              <label class="control-label" for="aZp_code">增值税发票纳税人识别号信息</label> -->
<!--              <div class="controls"> -->
<!-- 			    <input type="text" placeholder="增值税发票纳税人识别号信息" id="aZp_code"  warnName="增值税发票纳税人识别号信息" value="${hcompany.zp_code }"/> -->
                 
<!--              </div> -->
<!--          </div> -->
<!--          <div class="control-group"> -->
<!--              <label class="control-label" for="aZp_province_code">增票省份</label> -->
<!--              <div class="controls"> -->
<!-- 				<select id="aZp_province_code" gov-value="${hcompany.zp_province_code }"><option value="">请选择</option></select> -->
                 
<!--              </div> -->
<!--          </div> -->
<!--          <div class="control-group"> -->
<!--              <label class="control-label" for="aZp_city_code">增票城市</label> -->
<!--              <div class="controls"> -->
<!-- 			    <select id="aZp_city_code" gov-value="${hcompany.zp_city_code }"><option value="">请选择</option></select> -->
                 
<!--              </div> -->
<!--          </div> -->
<!--          <div class="control-group"> -->
<!--              <label class="control-label" for="aZp_area_code">增票地区</label> -->
<!--              <div class="controls"> -->
<!-- 			     <select id="aZp_area_code" gov-value="${hcompany.zp_area_code }"><option value="">请选择</option></select> -->
                 
<!--              </div> -->
<!--          </div> -->
<!--      	<div class="control-group"> -->
<!--              <label class="control-label" for="aZp_address">增票开票信息地址详细信息</label> -->
<!--              <div class="controls"> -->
<!-- 			    <input type="text" placeholder="增票开票信息地址详细信息" id="aZp_address"  warnName="营业地址邮编" value="${hcompany.zp_address }"/> -->
                 
<!--              </div> -->
<!--          </div> -->
<!--      	<div class="control-group"> -->
<!--              <label class="control-label" for="aZp_area_number">增票开票信息电话区号</label> -->
<!--              <div class="controls"> -->
<!-- 			    <input type="text" placeholder="增票开票信息电话区号" id="aZp_area_number"  warnName="增票开票信息电话区号"  value="${hcompany.zp_area_number }"/> -->
                 
<!--              </div> -->
<!--          </div> -->
<!--      	<div class="control-group"> -->
<!--              <label class="control-label" for="aZp_phone">增票开票信息电话</label> -->
<!--              <div class="controls"> -->
<!-- 			    <input type="text" placeholder="增票开票信息电话" id="aZp_phone"  warnName="营业地址邮编"  value="${hcompany.zp_phone }"/> -->
                 
<!--              </div> -->
<!--          </div> -->
<!--      	<div class="control-group"> -->
<!--              <label class="control-label" for="aZp_bank_code">增票开票信息开户行</label> -->
<!--              <div class="controls"> -->
<!-- 			    <input type="text" placeholder="增票开票信息开户行" id="aZp_bank_code"  warnName="增票开票信息开户行" value="${hcompany.zp_bank_code }" /> -->
                 
<!--              </div> -->
<!--          </div> -->
<!--      	<div class="control-group"> -->
<!--              <label class="control-label" for="aZp_bank_account">增票开票信息开户行账号</label> -->
<!--              <div class="controls"> -->
<!-- 			    <input type="text" placeholder="增票开票信息开户行账号" id="aZp_bank_account"  warnName="营业地址邮编" value="${hcompany.zp_bank_account }" /> -->
                 
<!--              </div> -->
<!--          </div> -->
         <div class="control-group">
            <label class="control-label" for="mAgentOneName">客户经理</label>
            <div class="controls">
		    <input type="text" placeholder="点击选择客户经理" id="mAgentOneName" isNotNull="true" data-toggle="modal" onclick="modelType=3;showAgentOne();" warnName="客户经理" readonly="readonly" value="${hcompany.oneAgentName }"/>
		    <a href="#stack2" id="sss" style="display: none;" class="btn btn-primary btn-large btn-block" data-toggle="modal">Demo - Modal Stacled</a> 
		    <input type="hidden" id="mAgentOneOpenId" value="${hcompany.oneAgentOpenId }"/>
<!-- 		    <input type="hidden" id="mAgentOneName"  value="${hcompany.oneAgentName }"/> -->
                <span class="help-inline text-red">*</span>
                <a type="button" class="btn btn-boo" onclick="cleanOn5()">清除</a>
            </div>
         </div>
          <div class="control-group">
            <label class="control-label" for="aAgentTwoName">代理</label>
            <div class="controls">
		    <input type="text" placeholder="点击选择代理" id="mAgentTwoName" data-toggle="modal" onclick="modelType=3;showAgentTwo();" warnName="代理" readonly="readonly" value="${hcompany.twoAgentName }"/>
		    <a href="#stack3" id="ddd" style="display: none;" class="btn btn-primary btn-large btn-block" data-toggle="modal">Demo - Modal Stacled</a> 
		    <input type="hidden" id="mAgentTwoOpenId" value="${hcompany.twoAgentOpenID }"/>
<!-- 		    <input type="hidden" id="mAgentTwoName" value="${hcompany.twoAgentName }"/> -->
                
                <a type="button" class="btn btn-boo" onclick="cleanOn6()">清除</a>
            </div>
         </div>
          <div class="control-group">
            <label class="control-label" for="aServicerName">服务人员</label>
            <div class="controls">
		    <input type="text" placeholder="点击选择服务人员" id="mServicerName" data-toggle="modal" onclick="modelType=3;showServicer();" warnName="服务人员" readonly="readonly"  value="${hcompany.servicerName }"/>
		    <a href="#stack4" id="ccc" style="display: none;" class="btn btn-primary btn-large btn-block" data-toggle="modal">Demo - Modal Stacled</a> 
		    <input type="hidden" id="mServicerId"  value="${hcompany.servicerId }"/>
<!-- 		    <input type="hidden" id="servicerName"  value="${hcompany.servicerName }"/> -->
                
                <a type="button" class="btn btn-boo" onclick="cleanOn7()">清除</a>
            </div>
         </div>
     </form>
 </div>
 <div class="modal-footer">
     <button type="button" data-dismiss="modal" class="btn btn-boo">取消</button>
     <button type="submit" class="btn btn-green" onclick="update()">更新</button>
 </div>
<script type="text/javascript">
$(document).ready(function(){
	new $gov("aProvince_code","aCity_code","aArea_code").init();
	new $gov("aZp_province_code","aZp_city_code","aZp_area_code").init();
})

function cleanOn5(){
	$("#mAgentOneName").val('');
	$("#mAgentOneOpenId").val('');
	$("#agentOneName").val('');
}
function cleanOn6(){
	$("#mAgentTwoName").val('');
	$("#mAgentTwoOpenId").val('');
	$("#agentTwoName").val('');
}
function cleanOn7(){
	$("#mServicerName").val('');
	$("#mServicerId").val('');
	$("#servicerName").val('');
}

function showAgentOne(){
	$("#sss").click();
	searchData2(1);
}
function showAgentTwo(){
	var agentOneOpenId = $("#mAgentOneOpenId").val();
	if(agentOneOpenId==""){
		tipError("请先选择客户经理");
	}else{
		$("#ddd").click();
		searchData6(1);
	}
}
function showServicer(){
	$("#ccc").click();
	searchData4(1);
}
	// 执行更新
	function update(){
		var id = getVal("mId");
		var name = getVal("mName");
		var user_id = getVal("mUser_id");
		var contact_name = getVal("mContact_name");
		var contact_phone = getVal("mContact_phone");
		var contact_mail = getVal("mContact_mail");
// 		var fp_name = getVal("mFp_name");
// 		var fp_phone = getVal("mFp_phone");
// 		var fp_telephone = getVal("mFp_telephone");
// 		var fp_address = getVal("mFp_address");
// 		var fax = getVal("mFax");
// 		var fax_ext = getVal("mFax_ext");
// 		var ywyId = getVal("mYwyId");
		/* var status = getVal("mStatus");
		var create_time = getVal("mCreate_time");
		var verify_reason = getVal("mVerify_reason");
		var verify_user_id = getVal("mVerify_user_id");
		var com_bank_code = getVal("mCom_bank_code");
		var verify_status = getVal("mVerify_status");
		var com_license_no = getVal("mCom_license_no");
		var com_license_img = getVal("mCom_license_img");
		var com_tax_no = getVal("mCom_tax_no");
		var com_tax_img = getVal("mCom_tax_img");
		var com_dept_code = getVal("mCom_dept_code");
		var com_dept_img = getVal("mCom_dept_img");
		var com_duty_no = getVal("mCom_duty_no");
		var com_bank_name = getVal("mCom_bank_name");
		var com_account_name = getVal("mCom_account_name");
		var com_account_no = getVal("mCom_account_no");
		var remark1 = getVal("mRemark1");
		var remark2 = getVal("mRemark2");
		var remark3 = getVal("mRemark3");
		var remark4 = getVal("mRemark4");
		var remark5 = getVal("mRemark5");
		var remark6 = getVal("mRemark6"); */
		var vercode = getVal("vercode");
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
		if(!validatemobile(contact_phone)){
			$("#mContact_phone").next().html("<i class='fontello-icon-cancel-circle'> 业务联系人手机格式不正确</i>");
			$("#mContact_phone").parent().parent().removeClass("success");
			$("#mContact_phone").parent().parent().addClass("error");
			return false;
		}else{
			$("#mContact_phone").next().html("<i class='fontello-icon-ok-circle'></i>");
			$("#mContact_phone").parent().parent().removeClass("error");
			$("#mContact_phone").parent().parent().addClass("success");
		}
		if(!vercode&&($("#old_phone").val()!=contact_phone)){
			$("#vercode").next().html("<i class='fontello-icon-cancel-circle'>手机验证码不能为空</i>");
			$("#vercode").parent().parent().removeClass("success");
			$("#vercode").parent().parent().addClass("error");
			return false;
		}else{
			$("#vercode").next().html("<i class='fontello-icon-ok-circle'></i>");
			$("#vercode").parent().parent().removeClass("error");
			$("#vercode").parent().parent().addClass("success");
		}
		if(contact_mail){
			if(!checkEmail(contact_mail)){
				$("#mContact_mail").next().html("<i class='fontello-icon-cancel-circle'> 业务联系人邮箱格式不正确</i>");
				$("#mContact_mail").parent().parent().removeClass("success");
				$("#mContact_mail").parent().parent().addClass("error");
				return false;
			}else{
				$("#mContact_mail").next().html("<i class='fontello-icon-ok-circle'></i>");
				$("#mContact_mail").parent().parent().removeClass("error");
				$("#mContact_mail").parent().parent().addClass("success");
			}
		}
// 		if(!checkFax($("#mFax").val())){
// 			$("#mFax").next().html("<i class='fontello-icon-cancel-circle'> 传真总机格式不正确</i>");
// 			$("#mFax").parent().parent().removeClass("success");
// 			$("#mFax").parent().parent().addClass("error");
// 			return false;
// 		}else{
// 			$("#mFax").next().html("<i class='fontello-icon-ok-circle'></i>");
// 			$("#mFax").parent().parent().removeClass("error");
// 			$("#mFax").parent().parent().addClass("success");
// 		}
// 		if(fax_ext){
// 			if(!checkFax_ext($("#mFax_ext").val())){
// 				$("#mFax_ext").next().html("<i class='fontello-icon-cancel-circle'> 传真分机格式不正确</i>");
// 				$("#mFax_ext").parent().parent().removeClass("success");
// 				$("#mFax_ext").parent().parent().addClass("error");
// 				return false;
// 			}else{
// 				$("#mFax_ext").next().html("<i class='fontello-icon-ok-circle'></i>");
// 				$("#mFax_ext").parent().parent().removeClass("error");
// 				$("#mFax_ext").parent().parent().addClass("success");
// 			}
// 		}
// 		if(!fp_phone&&!fp_telephone){
// 			$("#mFp_phone").next().html("<i class='fontello-icon-cancel-circle'> 发票收件人手机和电话必填一项</i>");
// 			$("#mFp_phone").parent().parent().removeClass("success");
// 			$("#mFp_phone").parent().parent().addClass("error");
// 			$("#mFp_telephone").next().html("<i class='fontello-icon-cancel-circle'> 发票收件人手机和电话必填一项</i>");
// 			$("#mFp_telephone").parent().parent().addClass("error");
// 			$("#mFp_telephone").parent().parent().removeClass("success");
// 			return false;
// 		}else{
// 			$("#mFp_phone").next().html("<i class='fontello-icon-cancel-circle'></i>");
// 			$("#mFp_phone").parent().parent().addClass("success");
// 			$("#mFp_phone").parent().parent().removeClass("error");
// 			$("#mFp_telephone").next().html("<i class='fontello-icon-cancel-circle'></i>");
// 			$("#mFp_telephone").parent().parent().removeClass("error");
// 			$("#mFp_telephone").parent().parent().addClass("success");
// 		}
// 		if(!validatemobile(fp_phone)){
// 			$("#mFp_phone").next().html("<i class='fontello-icon-cancel-circle'> 发票收件人手机格式不正确</i>");
// 			$("#mFp_phone").parent().parent().removeClass("success");
// 			$("#mFp_phone").parent().parent().addClass("error");
// 			return false;
// 		}else{
// 			$("#mFp_phone").next().html("<i class='fontello-icon-ok-circle'></i>");
// 			$("#mFp_phone").parent().parent().removeClass("error");
// 			$("#mFp_phone").parent().parent().addClass("success");
// 		}
// 		if(!ywyId){
// 			$("#ywyname").next().next().next().html("<i class='fontello-icon-cancel-circle'>业务员不能为空</i>");
// 			$("#ywyname").parent().parent().removeClass("success");
// 			$("#ywyname").parent().parent().addClass("error");
// 			return false;
// 		}else{
// 			$("#ywyname").next().next().next().html("<i class='fontello-icon-ok-circle'></i>");
// 			$("#ywyname").parent().parent().removeClass("error");
// 			$("#ywyname").parent().parent().addClass("success");
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
		var flag = validateForm('updateForm');
	    if (flag){ 
	        $.post("<c:url value='/hCompany/updateHCompany'/>",
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
// 		    		ywyId : ywyId,
		    		vercode : vercode,
		    		/* status : status,
		    		create_time : create_time,
		    		verify_reason : verify_reason,
		    		verify_user_id : verify_user_id,
		    		com_bank_code : com_bank_code,
		    		verify_status : verify_status,
		    		com_license_no : com_license_no,
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
		    		com_business_doc_type :com_business_doc_type,
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
		    		agentOneOpenId : $("#mAgentOneOpenId").val(),
		    		agentOneName : $("#mAgentOneName").val(),
		    		agentTwoOpenId : $("#mAgentTwoOpenId").val(),
		    		agentTwoName : $("#mAgentTwoName").val(),
		    		servicerId : $("#mServicerId").val(),
		    		servicerName : $("#mServicerName").val(),
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
		$("#mYwyId").val(openId);
		$("#closeOne").click();
	}
	function sendCode(){
		var phone = $.trim($("#mContact_phone").val());
		if(phone == ''){
			tipError("请填写您的手机号");
		}else{
			if(!validatemobile(phone)){
				setRed($("#mContact_phone"),'业务联系人手机格式不正确');
				return false;
			}else{
				setGreen($("#mContact_phone"),"");
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
	            	tipError("发送失败，请重试!");
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
		var myreg = /^(0|86|17951)?(13[0-9]|15[012356789]|17[0-9]|18[0-9]|14[57])[0-9]{8}$/;
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
	function btnNum(){
		var num = 59;
		$("#codeBtn").attr('disabled',true);
		$("#codeBtn").val('还剩'+num-- +'秒');
		var intervalID = setInterval(function(){
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