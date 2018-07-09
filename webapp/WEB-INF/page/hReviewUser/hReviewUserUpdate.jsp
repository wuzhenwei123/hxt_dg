<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>编辑客户复核人员</h4>
</div>
<input type="hidden" placeholder="id" value="${hreviewuser.id}" id="mId" isNotNull="true" warnName="id" />
<div class="modal-body">
    <form id="updateForm" class="well well-nice form-horizontal">
         <div class="control-group">
             <label class="control-label" for="mIsDefault">是否默认发短信对象</label>
             <div class="controls">
			    <select id="mIsDefault">
			    	<option value="1" <c:if test="${hreviewuser.isDefault==1 }">selected="selected"</c:if>>是</option>
			    	<option value="0" <c:if test="${hreviewuser.isDefault==0 }">selected="selected"</c:if>>否</option>
			    </select>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mState">状态</label>
             <div class="controls">
		    	 <select id="mState" isNotNull="true" warnName="状态"> 
                    <option <c:if test="${hreviewuser.state == 1}" >selected="selected"</c:if>  value="1">正常</option> 
                    <option <c:if test="${hreviewuser.state == 0}" >selected="selected"</c:if> value="0">禁用</option> 
            	</select> 
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mUserLabel">人员称谓</label>
             <div class="controls">
			    <input type="text" placeholder="人员称谓" value="${hreviewuser.userLabel}" id="mUserLabel" warnName="人员称谓" />
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mUserName">人员姓名</label>
             <div class="controls">
			    <input type="text" placeholder="人员姓名" value="${hreviewuser.userName}" id="mUserName" isNotNull="true" warnName="人员姓名" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mSex">性别</label>
             <div class="controls">
			     <select id="mSex" isNotNull="true" warnName="状态"> 
                    <option <c:if test="${hreviewuser.sex == 1}" >selected="selected"</c:if>  value="1">男</option> 
                    <option <c:if test="${hreviewuser.sex == 0}" >selected="selected"</c:if> value="0">女</option> 
            	</select> 
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mMobil">手机号</label>
             <div class="controls">
			    <input type="text" placeholder="手机号" value="${hreviewuser.mobil}" id="mMobil" isNotNull="true" warnName="手机号" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mMsgSwitch">默认短信开关</label>
             <div class="controls">
		       <select id="mSex" isNotNull="true" warnName="状态"> 
                    <option <c:if test="${hreviewuser.msgSwitch == 1}" >selected="selected"</c:if>  value="1">开</option> 
                    <option <c:if test="${hreviewuser.msgSwitch == 0}" >selected="selected"</c:if> value="0">关</option> 
            	</select> 
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mRemark">备注</label>
             <div class="controls">
			    <input type="text" placeholder="备注" value="${hreviewuser.remark}" id="mRemark" warnName="备注" />
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="companyName">所属公司</label>
             <div class="controls">
<!-- 			    <input type="text" placeholder="点击选择所属公司" id="companyName" isNotNull="true" data-toggle="modal" onclick="showCompany();" warnName="所属公司" readonly="readonly" value="${hreviewuser.companyName }"/> -->
			    <input type="text" placeholder="点击选择所属公司" id="companyName" isNotNull="true" data-toggle="modal" warnName="所属公司" readonly="readonly" value="${hreviewuser.companyName }"/>
			    <a href="#stack2" id="sss" style="display: none;" class="btn btn-primary btn-large btn-block" data-toggle="modal">Demo - Modal Stacled</a> 
			    <input type="hidden" id="aCompanyId" value="${hreviewuser.companyId}"/>
                 <span class="help-inline text-red">*</span>
                 <a type="button" class="btn btn-boo" onclick="cleanOn()" style="display: none;">清除</a>
             </div>
         </div>
     </form>
 </div>
 <div class="modal-footer">
     <button type="button" data-dismiss="modal" class="btn btn-boo">取消</button>
     <button type="submit" class="btn btn-green" onclick="update()">更新</button>
 </div>
  <%@ include file="/WEB-INF/page/hReviewUser/companyList.jsp" %>
<script type="text/javascript">
	function showCompany(){
		$("#sss").click();
		searchData2(1);
	}
	function cleanOn(){
		$("#companyName").val('');
		$("#aCompanyId").val('');
	}
	// 执行更新
	function update(){
		var id = getVal("mId");
		var isDefault = getVal("mIsDefault");
		var state = getVal("mState");
		var userLabel = getVal("mUserLabel");
		var userName = getVal("mUserName");
		var sex = getVal("mSex");
		var mobil = getVal("mMobil");
		var msgSwitch = getVal("mMsgSwitch");
		var createTime = getVal("mCreateTime");
		var createId = getVal("mCreateId");
		var updateTime = getVal("mUpdateTime");
		var updateId = getVal("mUpdateId");
		var remark = getVal("mRemark");
		var companyId = getVal("aCompanyId");
		var flag = validateForm('updateForm');
	    if (flag){ 
	        $.post("<c:url value='/hReviewUser/updateHReviewUser'/>",
	        	{
		    		id : id,
		    		isDefault : isDefault,
		    		state : state,
		    		userLabel : userLabel,
		    		userName : userName,
		    		sex : sex,
		    		mobil : mobil,
		    		msgSwitch : msgSwitch,
		    		createTime : createTime,
		    		createId : createId,
		    		updateTime : updateTime,
		    		updateId : updateId,
		    		remark : remark,
		    		companyId : companyId,
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