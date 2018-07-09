<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
 <!doctype html>
 <html lang="zh-CN">
 <head>
   	<meta charset="UTF-8">
   	<title>manageAdminUser</title>
 </head>
 <body>
 <div id="forms">
   <div class="box">
     <div class="box_border">
       <div class="box_center">
         <form action="" class="jqtransform">
          <table class="form_table pb15 pr110" width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
		        <th>头像</th>
			    <td>
			    	<img onclick="uploadHead()" src="/pic/head/${manageadminuser.headImg}" style="cursor: pointer;"  onerror="this.src='${ctx }/images/a.png'" id="headImg" alt="" width="60" height="60">
			    	<input id="aheadImg" type="hidden" value="${manageadminuser.headImg}" >
			    </td>
		        <th>用户ID</th>
			    <td><input type="text" id="adminId" name="adminId" class="input-text lh30 disabled" size="40" value="${manageadminuser.adminId}" isNotNull="true" warnName="用户ID"  readonly="readonly"/><font color='red'>*</font></td>
            </tr>
            <tr>
		        <th>用户名</th>
			    <td><input type="text" class="input-text lh30 disabled" size="40" value="${manageadminuser.adminName}" isNotNull="true" warnName="用户名" readonly="readonly" /><font color='red'>*</font></td>
			    <th>手机</th>
			    <td><input type="text" id="mobile" name="mobile" class="input-text lh30" size="40" value="${manageadminuser.mobile}" isNotNull="true" warnName="手机" /><font color='red'>*</font></td>
            </tr>
            <tr>
		        <th>昵称</th>
			    <td><input type="text" id="nickName" name="nickName" class="input-text lh30" size="40" value="${manageadminuser.nickName}" isNotNull="true" warnName="昵称" /><font color='red'>*</font></td>
		        <th>真实姓名</th>
			    <td><input type="text" id="realName" name="realName" class="input-text lh30" size="40" value="${manageadminuser.realName}" isNotNull="true" warnName="真实姓名" /></td>
            </tr>
            <tr>
		        <th>电话</th>
			    <td><input type="text" id="phone" name="phone" class="input-text lh30" size="40" value="${manageadminuser.phone}" /></td>
		        <th>状态</th>
			    <td>
			    	<select class="select" isNotNull="true" warnName="状态" disabled="disabled"> 
                        <option <c:if test="${manageadminuser.state == 1}" >selected="selected"</c:if>  value="1">正常</option> 
                        <option <c:if test="${manageadminuser.state == 0}" >selected="selected"</c:if> value="0">禁用</option> 
                	</select> 
			        <font color='red'>*</font>
			    </td>
            </tr>
            <tr>
		        <th>角色</th>
			    <td>
			    	<select class="select" isNotNull="true" warnName="角色" disabled="disabled"> 
			    	<c:forEach items="${roleList }" var="role">
                        <option <c:if test="${manageadminuser.roleId == role.roleId}" >selected="selected"</c:if>  value="${role.roleId }">${role.roleName }</option> 
			    	</c:forEach>
                	</select> 
			        <font color='red'>*</font>
			    </td>
				<th>密码</th>
			    <td><input type="password" id="passwd" name="passwd" class="input-text lh30" size="40" value="${manageadminuser.passwd}" isNotNull="true" warnName="密码" /><font color='red'>*</font></td>
            </tr>
            <tr>
		        <th>登陆日期</th>
		     	<td><input type="text" class="input-text lh30 disabled" readonly="readonly" size="40" value="<fmt:formatDate value="${manageadminuser.lastLogin}" type="both"/>" /></td>
<!-- 		        <TH>登陆IP</TH> -->
<%-- 			    <TD><INPUT TYPE="TEXT" ID="MLOGINIP" CLASS="INPUT-TEXT LH30" SIZE="40" VALUE="${MANAGEADMINUSER.LOGINIP}"  READONLY="READONLY"/></TD> --%>
		        <th>修改密码日期</th>
		     	<td><input type="text"  class="input-text lh30 disabled" readonly="readonly" size="40" value="<fmt:formatDate value="${manageadminuser.pwdModifyTime}" type="both"/>" /></td>
            </tr>
            <tr>
		        <th>创建日期</th>
		     	<td><input type="text" class="input-text lh30 disabled" readonly="readonly" size="40" value="<fmt:formatDate value="${manageadminuser.createTime}" type="both"/>" /></td>
		        <th>创建人</th>
			    <td><input type="text" class="input-text lh30 disabled" size="40" readonly="readonly" value="${manageadminuser.createrName}" /></td>
            </tr>
            <tr>
			    <td colspan="4" align="center"><input type="button" name="button" class="btn btn82 btn_search" onclick="update();" value="保存"></td>
            </tr>
          </table>
          </form>
       </div>
     </div>
   </div>
</div>
<script type="text/javascript">
	function uploadHead(){
		
		top.art.dialog.open('<c:url value="/plus/uploadify/send.jsp"/>',
		{
			id:123,
			fixed:true,
			esc:true,
			title:'头像上传',
			width: '370px',
			height:'150px',
			okVal : "保存",
			ok:function(){
				var iframe = this.iframe.contentWindow;
		    	if (!iframe.document.body) {
		        	return false;
		        };
		        
		        var newPath = getIframeVal(iframe,"newPath");
				var oldPath = getIframeVal(iframe,"oldPath");
				
				upload(newPath,oldPath);
				return true;
			}
		});
	}
	function upload(newPath,oldPath){
		$("#headImg").attr("src","/pic/head/"+newPath);
		$("#aheadImg").val(newPath);
	}
	function update(){
		var headImg = $("#aheadImg").val();
		var adminId = $("#adminId").val();
		var adminName = $("#adminName").val();
		var nickName = $("#nickName").val();
		var passwd = $("#passwd").val();
		var realName = $("#realName").val();
		var mobile = $("#mobile").val();
		var phone = $("#phone").val();
		var lastLogin = '';
		var loginIP = '';
		var pwdModifyTime = '';
		var state = '';
		var createTime = '';
		var createrId = '';
		var roleId = '';
		var flag = true ;
	    if (flag){ 
	        $.post("<c:url value='/manageAdminUser/saveManageAdminUser'/>",
	        	{
	        	headImg:headImg,
	        	roleId:roleId,
	    		adminId : adminId,
// 	    		adminName : adminName,
	    		nickName : encodeURI(nickName,'UTF-8'),
	    		passwd : passwd,
	    		realName : encodeURI(realName,'UTF-8'),
	    		mobile : mobile,
	    		phone : phone,
// 	    		lastLogin : lastLogin,
// 	    		loginIP : loginIP,
// 	    		pwdModifyTime : pwdModifyTime,
// 	    		state : state,
// 	    		createTime : createTime,
// 	    		createrId : createrId,
				 _t:Math.random()},
	        	function(data){
		        	var result = eval('('+data+')'); 
		            if (result.code == '1') {
		            	if(result.message == 'ok'){
			              	top.art.dialog.tips("保存成功");
			              	
			              	parent.$("#mainHeadImg").attr("src","/pic/head/"+headImg);
		            	}else{
		            		var dialog = top.art.dialog({
		            		    title: '重新登录',
		            		    content: '您的密码已修改，请重新登录!',
		            		    icon: 'succeed',
		            		    close: function(){
		            		    	parent.location = '${ctx }/manageAdminUser/loginout';
		            		    },
		            		    ok: function(){
		            		    	parent.location = '${ctx }/manageAdminUser/loginout';
		            		        return false;
		            		    }
		            		});
		            		
		            	}
		             } else {
		            	 top.art.dialog.alert(result.message);
		             }
	        });
	    }
	}
</script>
 </body>
 </html>
