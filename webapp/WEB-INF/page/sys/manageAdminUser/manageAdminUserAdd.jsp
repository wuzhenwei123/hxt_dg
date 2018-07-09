<%@page import="com.base.utils.FileUploadConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<%
request.setAttribute("ONE_AGENT_MANAGE_ROLEID", FileUploadConstants.ONE_AGENT_MANAGE_ROLEID);
request.setAttribute("TWO_AGENT_MANAGE_ROLEID", FileUploadConstants.TWO_AGENT_MANAGE_ROLEID);
request.setAttribute("PROXY_ROLEID", FileUploadConstants.PROXY_ROLEID);
request.setAttribute("COMPANY_ROLE_ID", FileUploadConstants.COMPANY_ROLE_ID);
%>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>添加用户</h4>
</div>
<div class="modal-body">
   <form id="addForm" class="well well-nice form-horizontal">
        <div class="control-group">
            <label class="control-label" for="aAdminName">登录名</label>
            <div class="controls">
                <input type="text" id="aAdminName" placeholder="登录名" isNotNull="true" warnName="登录名">
                <span class="help-inline text-red">*</span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="aNickName">昵称</label>
            <div class="controls">
                <input type="text" id="aNickName" placeholder="昵称" warnName="昵称">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="aPasswd">密码</label>
            <div class="controls">
                <input type="password" id="aPasswd" placeholder="密码" isNotNull="true" warnName="密码">
                <span class="help-inline text-red">*</span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="aRealName">真实姓名</label>
            <div class="controls">
                <input type="text" id="aRealName" placeholder="真实姓名" warnName="真实姓名">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="aMobile">手机</label>
            <div class="controls">
                <input type="text" id="aMobile" placeholder="手机" isNotNull="true" warnName="手机" datatype="phone">
                <span class="help-inline text-red">*</span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="aPhone">电话</label>
            <div class="controls">
                <input type="text" id="aPhone" placeholder="电话">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="mState">状态</label>
            <div class="controls">
                <select id="aState" isNotNull="true" warnName="状态"> 
                    <option value="1">正常</option> 
                    <option value="0">禁用</option> 
              	</select> 
                <span class="help-inline text-red">*</span>
            </div>
        </div>
        <div class="control-group"> 
            <label class="control-label" for="aRoleId">角色</label>
            <div class="controls">
                <select id="aRoleId" isNotNull="true" warnName="角色" onchange="showAgent(this.value)"> 
                       <option value="">--请选择--</option> 
		    		<c:forEach items="${roleList }" var="role">
		    		<c:if test="${role.roleId!=ONE_AGENT_MANAGE_ROLEID&&role.roleId!=TWO_AGENT_MANAGE_ROLEID&&PROXY_ROLEID!=role.roleId&&COMPANY_ROLE_ID!=role.roleId}">
		    		
                       <option value="${role.roleId }">${role.roleName }</option> 
		    		</c:if>
		    		</c:forEach>
               	</select> 
              	<span class="help-inline text-red">*</span>
            </div>
        </div>
        <div class="control-group" style="display: none;" id="oneDiv">
            <label class="control-label" for="aPhone">所属单位</label>
            <div class="controls">
                <input type="text" id="oneAgent" readonly="readonly" onclick="selOne()" data-toggle="modal" warnName="所属单位">
            	<span class="help-inline text-red">*</span>
            	<a type="button" class="btn btn-boo" onclick="cleanOn()">清除</a>
            	<a href="#stack1" id="xxx" style="display: none;" class="btn btn-primary btn-large btn-block" data-toggle="modal">Demo - Modal Stacled</a> 
            	<input type="hidden" id="oneAgentOpenId"/>
            </div>
        </div>
<!--         <div class="control-group" style="display: none;" id="twoDiv"> -->
<!--             <label class="control-label" for="aPhone">所属二级代理机构</label> -->
<!--             <div class="controls"> -->
<!--             	<c:if test="${roleType==1||roleType==2}"> -->
<!--             		<input type="text" id="twoAgent" readonly="readonly" onclick="selTwo()" data-toggle="modal" warnName="所属二级代理机构"> -->
<!-- 	            	<span class="help-inline text-red">*</span> -->
<!-- 	            	<a type="button" class="btn btn-boo" onclick="cleanTwo()">清除</a> -->
<!-- 	            	<input type="hidden" id="twoAgentOpenId"/> -->
<!-- 	            	<a href="#stack2" id="sss" style="display: none;" class="btn btn-primary btn-large btn-block" data-toggle="modal">Demo - Modal Stacled</a>  -->
<!--             	</c:if> -->
<!--                 <c:if test="${roleType==3}"> -->
<!--                 	<input type="text" id="twoAgent" readonly="readonly" data-toggle="modal" value="${hAgentTwo.name}"> -->
<!--                 	<input type="hidden" id="twoAgentOpenId" value="${twoAgentOpenId}"/> -->
<!--                 </c:if> -->
<!--             </div> -->
<!--         </div> -->
    </form>
</div>
<div class="modal-footer">
    <button type="button" data-dismiss="modal" class="btn btn-boo">取消</button>
    <button type="submit" class="btn btn-green" onclick="add()">保存</button>
</div>
<div id="stack1" class="modal hide fade" tabindex="-1" data-focus-on="input:first">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
        <h4>所属单位</h4>
    </div>
    <div class="modal-body">
	    <input type="text" id="fOneAgentName" class="span2 margin5" placeholder="单位名称">
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
</div>
<div id="stack2" class="modal hide fade" tabindex="-1" data-focus-on="input:first">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
        <h4>选择二级代理机构</h4>
    </div>
    <div class="modal-body">
	    <input type="text" id="fTwoAgentName" class="span2 margin5" placeholder="代理名称">
	    <a href="javascript:searchData2('1');" class="btn btn-green">查询</a>
    	<div class="widget-content" id="demo3">
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
    <div class="modal-footer"> <a type="button" id="closeTwo" data-dismiss="modal" class="btn btn-boo">关闭</a></div>
</div>
<input type="hidden" id="currPage1" value="1"><!-- 当前页码 -->
<input type="hidden" id="returnNum1" value="10"><!-- 分页返回 -->
<input type="hidden" id="currPage2" value="1"><!-- 当前页码 -->
<input type="hidden" id="returnNum2" value="10"><!-- 分页返回 -->
<script type="text/javascript">
//执行添加
var flag1 = true;
function add(){
	var adminId = getVal("aAdminId");
	var adminName = getVal("aAdminName");
	var nickName = getVal("aNickName");
	var passwd = getVal("aPasswd");
	var realName = getVal("aRealName");
	var mobile = getVal("aMobile");
	var phone = getVal("aPhone");
	var lastLogin = getVal("aLastLogin");
	var loginIP = getVal("aLoginIP");
	var pwdModifyTime = getVal("aPwdModifyTime");
	var state = getVal("aState");
	var createTime = getVal("aCreateTime");
	var createrId = getVal("aCreaterId");
	var roleId = getVal("aRoleId");
	var oneAgentOpenId = getVal("oneAgentOpenId");
	var twoAgentOpenId = getVal("twoAgentOpenId");
	var flag = validateForm('addForm');
    if (flag&&flag1){
    	flag1 = false;
    	$.post("${ctx}/manageAdminUser/checkAdminName",{adminName:adminName},function(data){
	   		 var result = eval('('+data+')');
	             if (result.code == '1') {
	            	 $.post("<c:url value='/manageAdminUser/addManageAdminUser'/>",
	            		       	{
	            		        	roleId:roleId,
	            		    		adminId : adminId,
	            		    		adminName : adminName,
	            		    		oneAgentOpenId : oneAgentOpenId,
	            		    		twoAgentOpenId : twoAgentOpenId,
	            		    		nickName : nickName,
	            		    		passwd : passwd,
	            		    		realName : realName,
	            		    		mobile : mobile,
	            		    		phone : phone,
	            		    		lastLogin : lastLogin,
	            		    		loginIP : loginIP,
	            		    		pwdModifyTime : pwdModifyTime,
	            		    		state : state,
	            		    		createTime : createTime,
	            		    		createrId : createrId,
	            					 _t:Math.random()},
	            		        	function(data){
	            						flag1 = true;
	            			        	var result = eval('('+data+')'); 
	            			            if (result.code == '1') {
	            			              	var pageNo = $("#currPage").val();           
	            			              	searchData(pageNo);
	            			              	tipOk('保存成功');
	            			             } else {
	            			            	 tipError(result.message);
	            			             }
	            			            $modal.modal('hide');
	            		        });
	             	
	             }else{
	            	 flag1 = true;
	            	 tipError("登录名已经存在");
	             }
	   	});
    }
}
function selOne(){
	$("#xxx").click();
	searchData1(1);
}
function selTwo(){
	var oneAgentOpenId = $("#oneAgentOpenId").val();
	if(oneAgentOpenId!=""){
		$("#sss").click();
		searchData2(1,oneAgentOpenId);
	}else{
		tipError("请选择一级代理!");
	}
	
}

function searchData1(pageNo){
	var returnNum = $("#returnNum1").val();
		var sortColumn = $("#sortColumn1").val();
	    var name = $("#fOneAgentName").val();
	    $.getJSON("<c:url value='/hCompany/getHCompanyList'/>",
        {
        	sortColumn:sortColumn,
    		name : name,
    		status : 1,
    		verify_status : 1,
    		pageNo: pageNo,
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
    	str+= "<th class=\"sortTh\" id=\"th_name\" column='name' onselectstart='return false' scope=\"col\">代理名称</th>";
    	str+= "<th class=\"sortTh\" id=\"th_style\" column='style' onselectstart='return false' scope=\"col\">类型</th>";
    	str+= "<th class=\"sortTh\" id=\"th_status\" column='status' onselectstart='return false' scope=\"col\">状态</th>";
    	str+= "<th class=\"sortTh\" id=\"th_check_status\" column='check_status' onselectstart='return false' scope=\"col\">审核状态</th>";
		str+="</tr></thead>";
	return str;
}
function setTableStr1(result, pageNo, returnNum){
 	var tableStr = "<table id='dataTable' class=\"table table-striped table-content table-condensed boo-table table-hover bg-blue-light\">";
    tableStr += genTableHeader1();
    var number = (pageNo - 1) * returnNum;
    tableStr += "<tfoot></tfoot>";
    tableStr += "<tbody>";
    var oneAgentOpenId = $("#oneAgentOpenId").val();
    for (var k=0; k<result.items.length; k++){      
        tableStr += "<tr id='DataRow"+k+"'>";
        if(oneAgentOpenId!=""){
        	if(oneAgentOpenId==result.items[k].id){
        		tableStr += "<td ><input type=\"radio\" checked id='"+result.items[k].id+"' class=\"checkbox check-row\" value=\"0\" name=\"checkName\" onclick='selonagent(\""+result.items[k].id+"\",\""+result.items[k].name+"\")'/></td>";
        	}else{
        		tableStr += "<td ><input type=\"radio\" id='"+result.items[k].id+"' class=\"checkbox check-row\" value=\"0\" name=\"checkName\" onclick='selonagent(\""+result.items[k].id+"\",\""+result.items[k].name+"\")'/></td>";
        	}
        }else{
        	tableStr += "<td ><input type=\"radio\" id='"+result.items[k].id+"' class=\"checkbox check-row\" value=\"0\" name=\"checkName\" onclick='selonagent(\""+result.items[k].id+"\",\""+result.items[k].name+"\")'/></td>";
        }
        tableStr += "<td >" + (number + k + 1) + "</td>";
	        var ss = result.items[k].style;
	    	if(ss==1){ss='公司'}else if (ss==2){ss='个人'}
	    	var st = result.items[k].status;
	    	if(st==1){st = '正常'}else if(st==0){st = '终止'}else if(st==2){st = '暂停'}
	    	var cst = result.items[k].check_status;
	    	if(cst ==0){cst='待审核'}else if (cst==1){cst='审核通过'}
	        tableStr += "<td>" + result.items[k].name + "</td>";
	        tableStr += "<td>" + ss + "</td>";
	        tableStr += "<td>" + st + "</td>";
	        tableStr += "<td>" + cst + "</td>";
        tableStr += "</tr>";            
    }
    tableStr += "</tbody>";
    $("#exampleDTC1").html(tableStr);
    $("#currPage1").val(pageNo);	
    $("#demo2").find("radio").uniform();//初始化复选框
    genPageTag1(pageNo,result.totalResults,returnNum,'kkpager1');
}
function selonagent(openId,name){
	$("#oneAgent").val(name);
	$("#oneAgentOpenId").val(openId);
	$("#closeOne").click();
}
function cleanOn(){
	$("#oneAgent").val("");
	$("#oneAgentOpenId").val("");
	$("#twoAgent").val("");
	$("#twoAgentOpenId").val("");
}
function cleanTwo(){
	$("#twoAgent").val("");
	$("#twoAgentOpenId").val("");
}
function seltwoagent(openId,name){
	$("#twoAgent").val(name);
	$("#twoAgentOpenId").val(openId);
	$("#closeTwo").click();
}
function searchData2(pageNo,oneAgentOpenId){
	var returnNum = $("#returnNum2").val();
		var sortColumn = $("#sortColumn2").val();
	    var name = $("#fTwoAgentName").val();
	    $.getJSON("<c:url value='/hAgentTwo/getHAgentTwoList'/>",
        {
        	sortColumn:sortColumn,
    		name : name,
    		oneAgentOpenIdIndex : oneAgentOpenId,
    		status : 1,
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
    	str+= "<th class=\"sortTh\" id=\"th_name\" column='name' onselectstart='return false' scope=\"col\">代理名称</th>";
    	str+= "<th class=\"sortTh\" id=\"th_style\" column='style' onselectstart='return false' scope=\"col\">类型</th>";
    	str+= "<th class=\"sortTh\" id=\"th_status\" column='status' onselectstart='return false' scope=\"col\">状态</th>";
    	str+= "<th class=\"sortTh\" id=\"th_check_status\" column='check_status' onselectstart='return false' scope=\"col\">审核状态</th>";
		str+="</tr></thead>";
	return str;
}
function setTableStr2(result, pageNo, returnNum){
 	var tableStr = "<table id='dataTable' class=\"table table-striped table-content table-condensed boo-table table-hover bg-blue-light\">";
    tableStr += genTableHeader2();
    var number = (pageNo - 1) * returnNum;
    tableStr += "<tfoot></tfoot>";
    tableStr += "<tbody>";
    var twoAgentOpenId = $("#twoAgentOpenId").val();
    for (var k=0; k<result.items.length; k++){      
        tableStr += "<tr id='DataRow"+k+"'>";
        if(twoAgentOpenId!=""){
        	if(twoAgentOpenId==result.items[k].openId){
        		tableStr += "<td ><input type=\"radio\" checked id='"+result.items[k].id+"' class=\"checkbox check-row\" value=\"0\" name=\"checkName\" onclick='seltwoagent(\""+result.items[k].openId+"\",\""+result.items[k].name+"\")'/></td>";
        	}else{
        		tableStr += "<td ><input type=\"radio\" id='"+result.items[k].id+"' class=\"checkbox check-row\" value=\"0\" name=\"checkName\" onclick='seltwoagent(\""+result.items[k].openId+"\",\""+result.items[k].name+"\")'/></td>";
        	}
        }else{
        	tableStr += "<td ><input type=\"radio\" id='"+result.items[k].id+"' class=\"checkbox check-row\" value=\"0\" name=\"checkName\" onclick='seltwoagent(\""+result.items[k].openId+"\",\""+result.items[k].name+"\")'/></td>";
        }
        tableStr += "<td >" + (number + k + 1) + "</td>";
	        var ss = result.items[k].style;
	    	if(ss==1){ss='公司'}else if (ss==2){ss='个人'}
	    	var st = result.items[k].status;
	    	if(st==1){st = '正常'}else if(st==0){st = '终止'}else if(st==2){st = '暂停'}
	    	var cst = result.items[k].check_status;
	    	if(cst ==0){cst='待审核'}else if (cst==1){cst='审核通过'}
	        tableStr += "<td>" + result.items[k].name + "</td>";
	        tableStr += "<td>" + ss + "</td>";
	        tableStr += "<td>" + st + "</td>";
	        tableStr += "<td>" + cst + "</td>";
        tableStr += "</tr>";            
    }
    tableStr += "</tbody>";
    $("#exampleDTC2").html(tableStr);
    $("#currPage2").val(pageNo);	
    $("#demo3").find("radio").uniform();//初始化复选框
    genPageTag2(pageNo,result.totalResults,returnNum,'kkpager2');
}
function showAgent(val){
	var company_role_id = "${company_role_id}";
	if(val==company_role_id){
		$("#oneDiv").show();
		$("#oneAgent").attr("isNotNull","true");
	}else{
		$("#oneDiv").hide();
		$("#oneAgent").removeAttr("isNotNull");
		$("#oneAgentOpenId").val("");
		$("#oneAgent").val("");
	}
}
</script>
