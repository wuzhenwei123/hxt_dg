<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>添加HReviewUser</h4>
</div>
<div class="modal-body">
    <form id="addForm" class="well well-nice form-horizontal">
        <!--  <div class="control-group">
             <label class="control-label" for="aId">id</label>
             <div class="controls">
			    <input type="text" placeholder="id" id="aId" isNotNull="true" warnName="id" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div> -->
         <div class="control-group">
             <label class="control-label" for="aIsDefault">是否默认发短信对象</label>
             <div class="controls">
			    <select id="aIsDefault">
			    	<option value="0">否</option>
			    	<option value="1">是</option>
			    </select>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aState">状态</label>
             <div class="controls">
		    	<select id="aState"> 
                    <option value="1">正常</option> 
                    <option value="0">禁用</option> 
            	</select> 
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aUserLabel">人员称谓</label>
             <div class="controls">
			    <input type="text" placeholder="人员称谓" id="aUserLabel" isNotNull="true" warnName="人员称谓" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aUserName">人员姓名</label>
             <div class="controls">
			    <input type="text" placeholder="人员姓名" id="aUserName" isNotNull="true" warnName="人员姓名" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aSex">性别</label>
             <div class="controls">
			    <select id="aSex">
			    	<option value="1">男</option>
			    	<option value="0">女</option>
			    </select>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aMobil">手机号</label>
             <div class="controls">
			    <input type="text" placeholder="手机号" id="aMobil" isNotNull="true" warnName="手机号" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aMsgSwitch">默认短信开关</label>
             <div class="controls">
			    <select id="aMsgSwitch">
			    	<option value="0">关</option>
			    	<option value="1">开</option>
			    </select>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aRemark">备注</label>
             <div class="controls">
			    <input type="text" placeholder="备注" id="aRemark" isNotNull="true" warnName="remark" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <!-- <div class="control-group">
             <label class="control-label" for="aCompanyId">所属公司</label>
             <div class="controls">
			    <input type="text" placeholder="所属公司" id="aCompanyId" isNotNull="true" warnName="companyId" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div> -->
         <div class="control-group">
             <label class="control-label" for="aCompanyId">所属公司</label>
             <div class="controls">
			    <input type="text" placeholder="点击选择公司" id="companyName" isNotNull="true" data-toggle="modal" onclick="showCompany();" warnName="所属公司" readonly="readonly"/>
			    <a href="#stack2" id="sss" style="display: none;" class="btn btn-primary btn-large btn-block" data-toggle="modal">Demo - Modal Stacled</a> 
			    <input type="hidden" id="aCompanyId" value=""/>
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
 <%@ include file="/WEB-INF/page/hReviewUser/companyList.jsp" %>
<script type="text/javascript">
function cleanOn(){
	$("#companyName").val('');
	$("#aCompanyId").val('');
}
	//执行添加
	function add(){
		var id = getVal("aId");
		var isDefault = getVal("aIsDefault");
		var state = getVal("aState");
		var userLabel = getVal("aUserLabel");
		var userName = getVal("aUserName");
		var sex = getVal("aSex");
		var mobil = getVal("aMobil");
		var msgSwitch = getVal("aMsgSwitch");
		var createTime = getVal("aCreateTime");
		var createId = getVal("aCreateId");
		var updateTime = getVal("aUpdateTime");
		var updateId = getVal("aUpdateId");
		var remark = getVal("aRemark");
		var companyId = getVal("aCompanyId");
		var flag = validateForm('addForm');
	    if (flag){ 
	        $.post("<c:url value='/hReviewUser/addHReviewUser'/>",
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
		              	tipOk("保存成功!");
		             } else {
		            	 tipError(result.message);
		             }
	              	$modal.modal('hide');
	        });
	    }
	}
	function showCompany(){
		$("#sss").click();
		searchData2(1);
	}
</script>