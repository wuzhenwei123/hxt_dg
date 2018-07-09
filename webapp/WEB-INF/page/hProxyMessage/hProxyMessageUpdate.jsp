<%@page import="com.base.utils.FileUploadConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<style type="text/css">
.maskbg{background:rgba(0,0,0,.8);display:none;height:100%;left:0;position:fixed;top:0;width:100%;z-index:19999;overflow: auto;}
.loading{text-align:center;margin:1em auto;width:100%;}
.loading-box img{left:50%;margin-left:-30px;position:absolute;top:50%; margin-top: -30px;}
.preview{vertical-align:middle;text-align:center;}
</style>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>编辑</h4>
</div>
<div class="modal-body">
    <form id="updateForm" class="well well-nice form-horizontal">
         <input type="hidden" placeholder="id" value="${hproxymessage.id}" id="mId"  warnName="id" />
         <div class="control-group">
             <label class="control-label" for="mContractNumber">合同编号</label>
             <div class="controls">
			    <input type="text" readonly="readonly" value="${hproxymessage.contractNumber}"  readonly="readonly" style="width: 350px;"/>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mUserNumber">用户编号</label>
             <div class="controls">
			    <input type="text" value="${hproxymessage.userNumber}" readonly="readonly"/>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mRemindStartDate">信息提醒日期</label>
             <div class="controls">
			    <input type="text" value="${hproxymessage.remindStartDate}" style="width: 85px;" id="mRemindStartDate" />
                ~
                <input type="text" style="width: 85px;" value="${hproxymessage.remindEndDate}" id="mRemindEndDate" />
                <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mProxyName">合同状态</label>
             <div class="controls">
             		<c:if test="${hproxymessage.checkState==0}">开通审核中</c:if>
             		<c:if test="${hproxymessage.checkState==1}">开通审核通过</c:if>
             		<c:if test="${hproxymessage.checkState==2}">开通审核驳回</c:if>
             		<c:if test="${hproxymessage.checkState==3}">终止审核中</c:if>
             		<c:if test="${hproxymessage.checkState==4}">终止审核通过</c:if>
             		<c:if test="${hproxymessage.checkState==5}">终止审核驳回</c:if>
             		<c:if test="${hproxymessage.checkState==6}">变更审核中</c:if>
             		<c:if test="${hproxymessage.checkState==7}">变更审核通过</c:if>
             		<c:if test="${hproxymessage.checkState==8}">变更审核驳回</c:if>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mProxyName">单位全称</label>
             <div class="controls">
			    <input type="text" value="${hproxymessage.proxyName}" readonly="readonly"/>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mPayName">账户负责人姓名</label>
             <div class="controls">
			    <input type="text" value="${hproxymessage.payName}" readonly="readonly"/>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mPayPhoneNumber">账户负责人手机号</label>
             <div class="controls">
			    <input type="text" value="${hproxymessage.payPhoneNumber}" onkeyup="this.value=this.value.replace(/\D/gi,'')" readonly="readonly"/>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group" id="zjno">
             <label class="control-label" for="mPayCard">账户负责人证件类型</label>
             <div class="controls">
             	<input type="text" value="身份证" readonly="readonly"/>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group" id="zjno">
             <label class="control-label" for="mPayCard">账户负责人证件号码</label>
             <div class="controls">
			    <input type="text" value="${hproxymessage.payCard}" readonly="readonly"/>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mProxyAddress">账户负责人地址</label>
             <div class="controls">
			    <input type="text" value="${hproxymessage.proxyAddress}"  readonly="readonly"/>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mProxyCode">账户负责人邮编</label>
             <div class="controls">
			    <input type="text" value="${hproxymessage.proxyCode}"  readonly="readonly"/>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mPayMail">账户负责人邮箱</label>
             <div class="controls">
			    <input type="text" value="${hproxymessage.payMail}" id="mPayMail" readonly="readonly"/>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mBank_number">开户银行名称</label>
             <div class="controls">
			    <input type="text" value="${hproxymessage.bank_name}" style="width: 300px;" readonly="readonly"/>
<!--                 <a type="button" class="btn btn-boo" onclick="cleanOnIndex2()">清除</a> -->
<!-- 	           	<a href="#stack2" id="xxxindex2" style="display: none;" class="btn btn-primary btn-large btn-block" data-toggle="modal">Demo - Modal Stacled</a>  -->
<!-- 	           	<input type="hidden" id="mBank_number" value="${hproxymessage.bank_name}"  warnName="开户银行名称"/> -->
                <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mBank_number">开户银行行号</label>
             <div class="controls">
			    <input type="text" value="${hproxymessage.bank_number}" style="width: 300px;" readonly="readonly"/>
<!--                 <a type="button" class="btn btn-boo" onclick="cleanOnIndex2()">清除</a> -->
<!-- 	           	<a href="#stack2" id="xxxindex2" style="display: none;" class="btn btn-primary btn-large btn-block" data-toggle="modal">Demo - Modal Stacled</a>  -->
<!-- 	           	<input type="hidden" id="mBank_number" value="${hproxymessage.bank_number}"  warnName="开户银行行号"/> -->
                <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mPayBankNumber">账户名称</label>
             <div class="controls">
			    <input type="text" value="${hproxymessage.payAccountName}"  readonly="readonly"/>
<!--                 <a type="button" class="btn btn-boo" onclick="getNumber()">获取用户编号和合同号</a> -->
                <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mPayBankNumber">开户账号</label>
             <div class="controls">
			    <input type="text" value="${hproxymessage.payBankNumber}"  readonly="readonly"/>
<!--                 <a type="button" class="btn btn-boo" onclick="getNumber()">获取用户编号和合同号</a> -->
                <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mPayBankNumber">清算行行号</label>
             <div class="controls">
			    <input type="text" value="${hproxymessage.qsBank}" readonly="readonly"/>
<!--                 <a type="button" class="btn btn-boo" onclick="getNumber()">获取用户编号和合同号</a> -->
                <span class="help-inline text-red">*</span>
             </div>
         </div>
         
         
         
<!--          <div class="control-group"> -->
<!--              <label class="control-label" for="mSex">联系人性别</label> -->
<!--              <div class="controls"> -->
<!-- 		    	 <select id="mSex"  warnName="联系人性别">  -->
<!--                     <option <c:if test="${hproxymessage.sex == 1}" >selected="selected"</c:if>  value="1">男</option>  -->
<!--                     <option <c:if test="${hproxymessage.sex == 0}" >selected="selected"</c:if> value="0">女</option>  -->
<!--             	</select>  -->
<!--                  <span class="help-inline text-red">*</span> -->
<!--              </div> -->
<!--          </div> -->
<!--          <div class="control-group" id="mobilediv1"> -->
<!--              <label class="control-label" for="mProxyPhone">联系人电话</label> -->
<!--              <div class="controls"> -->
<!-- 			    <input type="text" value="${hproxymessage.proxyPhone}" id="mProxyPhone"  warnName="proxyPhone" datatype="phone" onkeyup="this.value=this.value.replace(/\D/gi,'')"/> -->
<!--                  <span class="help-inline text-red">*</span> -->
<!--              </div> -->
<!--          </div> -->
<!--          <div class="control-group"> -->
<!--              <label class="control-label" for="mState">状态</label> -->
<!--              <div class="controls"> -->
<!-- 		    	 <select id="mState"  warnName="状态">  -->
<!--                     <option <c:if test="${hproxymessage.state == 1}" >selected="selected"</c:if>  value="1">正常</option>  -->
<!--                     <option <c:if test="${hproxymessage.state == 0}" >selected="selected"</c:if> value="0">禁用</option>  -->
<!--             	</select>  -->
<!--                  <span class="help-inline text-red">*</span> -->
<!--              </div> -->
<!--          </div> -->
<!--          <div class="control-group"> -->
<!--              <label class="control-label" for="mContractStartTime">合同开始日期</label> -->
<!--              <div class="controls"> -->
<!-- 		     	<input type="text" placeholder="contractStartTime" value="<fmt:formatDate value="${hproxymessage.contractStartTime}" type="both"/>" id="mContractStartTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"  warnName="contractStartTime" /> -->
<!--                  <span class="help-inline text-red">*</span> -->
<!--              </div> -->
<!--          </div> -->
<!--          <div class="control-group"> -->
<!--              <label class="control-label" for="mContractEndTime">合同结束日期</label> -->
<!--              <div class="controls"> -->
<!-- 		     	<input type="text" placeholder="contractEndTime" value="<fmt:formatDate value="${hproxymessage.contractEndTime}" type="both"/>" id="mContractEndTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"  warnName="contractEndTime" /> -->
<!--                  <span class="help-inline text-red">*</span> -->
<!--              </div> -->
<!--          </div> -->
         <div class="control-group">
             <label class="control-label" for="mCId">注册人手机号</label>
             <div class="controls">
			    <input type="text" value="${hCompany.contact_phone}" readonly="readonly"  />
                <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mCId">注册联系人姓名</label>
             <div class="controls">
	           	<input type="hidden" value="${hCompany.contact_name}" readonly="readonly"/>
                <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mCId">注册人单位名称</label>
             <div class="controls">
			    <input type="text" value="${hCompany.name}" readonly="readonly"  />
<!--                  <a type="button" class="btn btn-boo" onclick="cleanOnIndex3()">清除</a> -->
<!-- 	           	<a href="#stack3" id="xxxindex3" style="display: none;" class="btn btn-primary btn-large btn-block" data-toggle="modal">Demo - Modal Stacled</a>  -->
                <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mCId">申请协议图片</label>
             <div class="controls">
			    <c:if test="${empty hproxymessage.hetongUrl}">
             		暂无合同
             	</c:if>
             	<c:if test="${not empty hproxymessage.hetongUrl}">
			    	<img alt="" src="${pic_front}${hproxymessage.hetongUrl}" style="width:300px;height:100px;" onclick="preview('${hproxymessage.hetongUrl}',this)">
             	</c:if>
                <span class="help-inline text-red"><input type="button" value="下载" onclick="downLoad()"></span>
             </div>
         </div>
<!--          <div class="control-group"> -->
<!--              <label class="control-label" for="mInfo">附言</label> -->
<!--              <div class="controls"> -->
<!-- 			    <input type="text" value="${hproxymessage.info}" id="mInfo" warnName="附言" /> -->
<!--              </div> -->
<!--          </div> -->
         
     </form>
 </div>
 <div class="modal-footer">
     <button type="button" data-dismiss="modal" class="btn btn-boo">取消</button>
     <button type="submit" class="btn btn-green" onclick="update()">更新</button>
 </div>
 <div id="stack3" class="modal hide fade" tabindex="-1" data-focus-on="input:first">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
        <h4>选择客户</h4>
    </div>
    <div class="modal-body">
	    <input type="text" id="fOneAgentNameIndex" class="span2 margin5" placeholder="客户名称">
	    <a href="javascript:searchData3('1');" class="btn btn-green">查询</a>
    	<div class="widget-content" id="demo3">
	         <div class="widget-body">
	             <table id="exampleDTC3" class="table table-striped table-content table-condensed boo-table table-hover bg-blue-light">
		         </table>
		         <div class="widget-footer">
		             <div class="btn-toolbar pull-left">
		             </div>
		             <div class="pagination pagination-btn pull-right">
		             	<div id="kkpager3"></div>
		             </div>
		         </div>
	         </div>
		</div>
    </div>
    <div class="modal-footer"> <a type="button" id="closeOneIndex3" data-dismiss="modal" class="btn btn-boo">关闭</a></div>
    <input id="returnNum3" type="hidden" value="10" />
    <input id="currPage3" type="hidden" value="1"/>
    <input id="sortColumn3" type="hidden" value=""/>
</div>
<div id="stack2" class="modal hide fade" tabindex="-1" data-focus-on="input:first" style="widows: 600px;">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
        <h4>选择付款行</h4>
    </div>
    <div class="modal-body">
	    <input type="text" id="fOneAgentNameIndex2" class="span2 margin5" placeholder="付款行名称">
	    <a href="javascript:searchData2('1');" class="btn btn-green">查询</a>
    	<div class="widget-content" id="demo4">
	         <div class="widget-body">
	             <table id="exampleDTC2" class="table table-striped table-content table-condensed boo-table table-hover bg-blue-light">
		         </table>
		         <div class="widget-footer">
		             <div class="btn-toolbar pull-left">
		             </div>
		             <div class="pagination pagination-btn pull-right">
		             	<div id="kkpager2"></div>
		             </div>
		         </div>
	         </div>
		</div>
    </div>
    <div class="modal-footer"> <a type="button" id="closeOneIndex2" data-dismiss="modal" class="btn btn-boo">关闭</a></div>
    <input id="returnNum2" type="hidden" value="10" />
    <input id="currPage2" type="hidden" value="1"/>
</div>
<div class="preview maskbg" data-p="" onclick="closePreview()">
	<img src="images/icon.jpg" />
</div>
<div id="loadingToast" class="weui_loading_toast" style="display: none;">
    <div class="weui_mask_transparent"></div>
    <div class="weui_toast">
        <div class="weui_loading">
            <div class="weui_loading_leaf weui_loading_leaf_0"></div>
            <div class="weui_loading_leaf weui_loading_leaf_1"></div>
            <div class="weui_loading_leaf weui_loading_leaf_2"></div>
            <div class="weui_loading_leaf weui_loading_leaf_3"></div>
            <div class="weui_loading_leaf weui_loading_leaf_4"></div>
            <div class="weui_loading_leaf weui_loading_leaf_5"></div>
            <div class="weui_loading_leaf weui_loading_leaf_6"></div>
            <div class="weui_loading_leaf weui_loading_leaf_7"></div>
            <div class="weui_loading_leaf weui_loading_leaf_8"></div>
            <div class="weui_loading_leaf weui_loading_leaf_9"></div>
            <div class="weui_loading_leaf weui_loading_leaf_10"></div>
            <div class="weui_loading_leaf weui_loading_leaf_11"></div>
        </div>
        <p class="weui_toast_content">处理中，请稍后</p>
    </div>
</div>
<script type="text/javascript">
	// 执行更新
	function update(){
		var id = getVal("mId");
		var proxyName = getVal("mProxyName");
		var proxyPhone = getVal("mProxyPhone");
		var createYime = getVal("mCreateYime");
		var contractNumber = getVal("mContractNumber");
		var contractStartTime = getVal("mContractStartTime");
		var contractEndTime = getVal("mContractEndTime");
		var remindStartDate = getVal("mRemindStartDate");
		var remindEndDate = getVal("mRemindEndDate");
		var bank_number = getVal("mBank_number");
		var info = getVal("mInfo");
		var remark1 = getVal("mRemark1");
		var remark2 = getVal("mRemark2");
		var remark3 = getVal("mRemark3");
		var cId = getVal("mCId");
		var userId = getVal("mUserId");
		var state = getVal("mState");
		var userNumber = getVal("mUserNumber");
		var proxyAddress = getVal("mProxyAddress");
		var proxyCode = getVal("mProxyCode");
		var payBankNumber = getVal("mPayBankNumber");
		var payName = getVal("mPayName");
		var payCardStyle = getVal("mPayCardStyle");
		var payCard = getVal("mPayCard");
		var payPhoneNumber = getVal("mPayPhoneNumber");
		var payMail = getVal("mPayMail");
		var sex = getVal("mSex");
		var flag = validateForm('updateForm');
	    if (flag){ 
// 	    	if(!IdentityCodeValid(payCard)){
// 				$("#mPayCard").next().html("<i class='fontello-icon-cancel-circle'>身份证号错误，请重新填写</i>");
// 				$("#zjno").removeClass("success");
// 				$("#zjno").addClass("error");
// 				return;
// 			}
// 	    	if(!checkMobile(proxyPhone,id)){
// 				$("#mProxyPhone").next().html("<i class='fontello-icon-cancel-circle'> 联系人电话已存在，请重新填写</i>");
// 				$("#mobilediv1").removeClass("success");
// 				$("#mobilediv1").addClass("error");
// 				return;
// 			}
	    	$("#loadingToast").show();
	        $.post("<c:url value='/hProxyMessage/updateHProxyMessage'/>",
	        	{
		    		id : id,
		    		proxyName : proxyName,
		    		proxyPhone : proxyPhone,
		    		createYime : createYime,
		    		contractNumber : contractNumber,
		    		contractStartTime : contractStartTime,
		    		contractEndTime : contractEndTime,
		    		remindStartDate : remindStartDate,
		    		remindEndDate : remindEndDate,
		    		bank_number : bank_number,
		    		info : info,
		    		sex : sex,
		    		remark1 : remark1,
		    		remark2 : remark2,
		    		remark3 : remark3,
		    		cId : cId,
		    		userId : userId,
		    		state : state,
		    		userNumber : userNumber,
		    		proxyAddress : proxyAddress,
		    		proxyCode : proxyCode,
		    		payBankNumber : payBankNumber,
		    		payName : payName,
		    		payCardStyle : 1,
		    		payCard : payCard,
		    		payPhoneNumber : payPhoneNumber,
		    		payMail : payMail,
				 _t:Math.random()},
	        	function(data){
					 $("#loadingToast").hide();
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
	
	function selOneindex3(){
		$("#xxxindex3").click();
		searchData3(1);
	}
	function cleanOnIndex3(){
		$("#mCName").val("");
		$("#mCId").val("");
	}
	function searchData3(pageNo){
		var returnNum = $("#returnNum3").val();
		var name = $("#fOneAgentNameIndex").val();
			var sortColumn = $("#sortColumn3").val();
		    $.getJSON("<c:url value='/hCompany/getHCompanyList2'/>",
	        {
	        	sortColumn:sortColumn,
	        	name : name,
	    		status : 1,
	    		verify_status : 1,
	    		pageNo: pageNo,
	    		notid: ${hproxymessage.CId},
	    		rowCount: returnNum, 
				_t: Math.random()
	        },function(data){
	        var result = data;
	        if (result.code == 1) {
	            setTableStr3(result, pageNo, returnNum);
	        } else {
	        	tipError("系统异常!");
	        } 
	    });
	}
	function genTableHeader3(){
		var str = "<thead><tr>" ;
	    	str+= "<th scope=\"col\" class=\"check-col\"></th>";
	    	str+= "<th onselectstart='return false' scope=\"col\">序号</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_name\" column='name' onselectstart='return false' scope=\"col\">客户名称</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_style\" column='style' onselectstart='return false' scope=\"col\">联系人手机</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_status\" column='status' onselectstart='return false' scope=\"col\">状态</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_status\" column='status' onselectstart='return false' scope=\"col\">审核状态</th>";
			str+="</tr></thead>";
		return str;
	}
	function setTableStr3(result, pageNo, returnNum){
	 	var tableStr = "<table id='dataTable' class=\"table table-striped table-content table-condensed boo-table table-hover bg-blue-light\">";
	    tableStr += genTableHeader3();
	    var number = (pageNo - 1) * returnNum;
	    tableStr += "<tfoot></tfoot>";
	    tableStr += "<tbody>";
// 	    var oneAgentId = '';
	    var cId = $("#mCId").val();
	    for (var k=0; k<result.items.length; k++){      
	        tableStr += "<tr id='DataRow"+k+"'>";
	        if(cId!=""){
	        	if(cId==result.items[k].id){
	        		tableStr += "<td ><input type=\"radio\" checked id='"+result.items[k].id+"' class=\"checkbox check-row\" value=\"0\" name=\"checkName\" onclick='selonagentindex(\""+result.items[k].id+"\",\""+result.items[k].name+"\")'/></td>";
	        	}else{
	        		tableStr += "<td ><input type=\"radio\" id='"+result.items[k].id+"' class=\"checkbox check-row\" value=\"0\" name=\"checkName\" onclick='selonagentindex(\""+result.items[k].id+"\",\""+result.items[k].name+"\")'/></td>";
	        	}
	        }else{
	        	tableStr += "<td ><input type=\"radio\" id='"+result.items[k].id+"' class=\"checkbox check-row\" value=\"0\" name=\"checkName\" onclick='selonagentindex(\""+result.items[k].id+"\",\""+result.items[k].name+"\")'/></td>";
	        }
	        tableStr += "<td >" + (number + k + 1) + "</td>";
		        var status = result.items[k].status;
		    	if(status==1){status = '正常'}else if(status==0){status = '终止'}else if(status==2){status = '暂停'}
		    	var verify_status = result.items[k].verify_status;
		    	if(verify_status ==0){verify_status='待审核'}else if (verify_status==1){verify_status='审核通过'}else if (verify_status==2){verify_status='审核不通过'}
		        tableStr += "<td>" + result.items[k].name + "</td>";
		        tableStr += "<td>" + result.items[k].contact_phone + "</td>";
		        tableStr += "<td>" + status + "</td>";
		        tableStr += "<td>" + verify_status + "</td>";
	        tableStr += "</tr>";            
	    }
	    tableStr += "</tbody></table>";
	    $("#exampleDTC3").html(tableStr);
	    $("#currPage3").val(pageNo);	
// 	    $("#demo4").find("radio").uniform();//初始化复选框
	    genPageTag3(pageNo,result.totalResults,returnNum,'kkpager3');
	}
	function selonagentindex(id,name,openId){
		$("#mCName").val(name);
		$("#mCId").val(id);
		$("#closeOneIndex3").click();
	}
	
	function selOneindex2(){
		$("#xxxindex2").click();
		searchData2(1);
	}
	function cleanOnIndex2(){
		$("#mBankName").val("");
		$("#mBank_number").val("");
		$("#mUserNumber").val("");
    	$("#mContractNumber").val("");
    	$("#mPayBankNumber").val("");
    	
	}
	function searchData2(pageNo){
		var returnNum = $("#returnNum2").val();
		var name = $("#fOneAgentNameIndex2").val();
			var sortColumn = $("#sortColumn2").val();
		    $.getJSON("<c:url value='/hBankInfo/getHBankInfoList'/>",
	        {
	        	sortColumn:sortColumn,
	        	name : name,
	    		pageNo: pageNo,
	    		rowCount: returnNum, 
				_t: Math.random()
	        },function(data){
	        var result = data;
	        if (result.code == 1) {
	            setTableStr2(result, pageNo, returnNum);
	        } else {
	        	tipError("系统异常!");
	        } 
	    });
	}
	function genTableHeader2(){
		var str = "<thead><tr>" ;
	    	str+= "<th scope=\"col\" class=\"check-col\"></th>";
	    	str+= "<th onselectstart='return false' scope=\"col\">序号</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_name\" column='name' onselectstart='return false' scope=\"col\">开户行行号</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_style\" column='style' onselectstart='return false' scope=\"col\">开户行名称</th>";
			str+="</tr></thead>";
		return str;
	}
	function setTableStr2(result, pageNo, returnNum){
	 	var tableStr = "<table id='dataTable' class=\"table table-striped table-content table-condensed boo-table table-hover bg-blue-light\">";
	    tableStr += genTableHeader2();
	    var number = (pageNo - 1) * returnNum;
	    tableStr += "<tfoot></tfoot>";
	    tableStr += "<tbody>";
	    var bank_number = $("#mBank_number").val();
	    for (var k=0; k<result.items.length; k++){      
	        tableStr += "<tr id='DataRow"+k+"'>";
	        if(bank_number!=""){
	        	if(bank_number==result.items[k].bankNum){
	        		tableStr += "<td ><input type=\"radio\" checked id='"+result.items[k].bankNum+"' class=\"checkbox check-row\" value=\"0\" name=\"checkName\" onclick='selonagentindex1(\""+result.items[k].bankNum+"\",\""+result.items[k].name+"\")'/></td>";
	        	}else{
	        		tableStr += "<td ><input type=\"radio\" id='"+result.items[k].bankNum+"' class=\"checkbox check-row\" value=\"0\" name=\"checkName\" onclick='selonagentindex1(\""+result.items[k].bankNum+"\",\""+result.items[k].name+"\")'/></td>";
	        	}
	        }else{
	        	tableStr += "<td ><input type=\"radio\" id='"+result.items[k].bankNum+"' class=\"checkbox check-row\" value=\"0\" name=\"checkName\" onclick='selonagentindex1(\""+result.items[k].bankNum+"\",\""+result.items[k].name+"\")'/></td>";
	        }
	        tableStr += "<td >" + (number + k + 1) + "</td>";
		        tableStr += "<td>" + result.items[k].bankNum + "</td>";
		        tableStr += "<td>" + result.items[k].name + "</td>";
	        tableStr += "</tr>";            
	    }
	    tableStr += "</tbody></table>";
	    $("#exampleDTC2").html(tableStr);
	    $("#currPage2").val(pageNo);	
// 	    $("#demo4").find("radio").uniform();//初始化复选框
	    genPageTag2(pageNo,result.totalResults,returnNum,'kkpager2');
	}
	function selonagentindex1(id,name){
		$("#mBankName").val(name+"("+id+")");
		$("#mBank_number").val(id);
		$("#mUserNumber").val("");
    	$("#mContractNumber").val("");
		$("#mPayBankNumber").val("");
		$("#closeOneIndex2").click();
	}
	
	function getNumber(){
		var bank_number = $("#mBank_number").val();
		if(bank_number==""){
			tipError("请选择付款行行号");
			return false;
		}
		
		var payBankNumber = $("#mPayBankNumber").val();
		if(payBankNumber==""){
			tipError("请选择付款人账号");
			return false;
		}
		//获取用户编号和合同号
		$.get("${ctx}/hProxySerial/getNumber?bank_number="+bank_number+"&payBankNumber="+payBankNumber+"&_t="+Math.random(),function(data){
			var result = eval("("+data+")");
			console.log(data);
	        if (result.code == "1") {
	        	var obj = result.items;
	        	$("#mUserNumber").val(obj.userNumber);
	        	$("#mContractNumber").val(obj.contractNumber);
	        }else{
	        	tipError("获取失败");
	        }
		});
		
	}
	
	function OnInput (event) {
		if($.trim(event.target.value)!=""){
// 			event.target.value;
			$("#mUserNumber").val("");
        	$("#mContractNumber").val("");
		}else{
			$("#mUserNumber").val("");
        	$("#mContractNumber").val("");
      	}
	}
	function OnPropChanged (event) {
	    if (event.propertyName.toLowerCase () == "value") {
	      	if($.trim(event.srcElement.value)!=""){
// 	      		event.srcElement.value
	      		$("#mUserNumber").val("");
	        	$("#mContractNumber").val("");
	      	}else{
	      		$("#mUserNumber").val("");
	        	$("#mContractNumber").val("");
	      	}
	    }
	}
	
	function IdentityCodeValid(code) { 
        var city={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江 ",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北 ",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏 ",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外 "};
        var tip = "";
        var pass= true;
        
        if (!code || !/^[1-9][0-9]{5}(19[0-9]{2}|200[0-9]|2010)(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])[0-9]{3}[0-9xX]$/i.test(code)) {
            tip = "身份证号格式错误";
            pass = false;
        }else if(!city[code.substr(0,2)]){
            tip = "地址编码错误";
            pass = false;
        }else{
            //18位身份证需要验证最后一位校验位
            if(code.length == 18){
                code = code.split('');
                //∑(ai×Wi)(mod 11)
                //加权因子
                var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
                //校验位
                var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];
                var sum = 0;
                var ai = 0;
                var wi = 0;
                for (var i = 0; i < 17; i++)
                {
                    ai = code[i];
                    wi = factor[i];
                    sum += ai * wi;
                }
                var last = parity[sum % 11];
                if(parity[sum % 11] != code[17]){
                    tip = "校验位错误";
                    pass =false;
                }
            }
        }
        return pass;
    }
	function checkMobile(mobile,id){
		$.ajaxSetup({ 
		    async : false 
		}); 
		var flag = false;
		$.post("${ctx}/hProxyMessage/checkProxyPhone",{mobile:mobile,id:id},function(data){
			 var result = eval('('+data+')');
	            if (result.code == '1') {
	            	flag= true;
	            }
		});
		return flag;
	}
	
	function preview(newFileName,obj){
		//获取图片的宽和高
		var image = new Image();
	    image.src = obj.src;
	    var naturalWidth = image.width;
	    var naturalHight = image.height;
		$(".preview").find("img").attr('src',"${pic_front}"+newFileName);
		$(".preview").find("img").attr('width',naturalWidth);
		$(".preview").find("img").attr('height',naturalHight);
		$(".preview").show();
	}
	
	function closePreview(){
		$(".preview").hide();
	}
	
	function downLoad(){
		window.location.href = "${pic_front}/index/toDownload?link=${hproxymessage.hetongUrl}&name=申请协议";
	}
</script>