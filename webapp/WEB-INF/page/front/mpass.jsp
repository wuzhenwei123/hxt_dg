<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script>
function turnoff(obj){
	document.getElementById(obj).style.display="none";
}
</script>
<title>${_titleIndex}</title>
<meta name="keywords" content="恒信通,易付通,企业网上交电费,公司网上交电费" />
<meta name="description" content="北京恒信通电信服务有限公司，成立于2002年，是国家电网北京市电力公司指定的代收费机构，恒信通依托“易付通”公共事业缴费服务品牌，为企业、公司、物业、工厂等企事业单位提供便捷的、一站式的网上交电费服务。" />
<meta content="textml;charset=utf-8" http-equiv="content-type">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta content="webkit|ie-comp|ie-stand" name="renderer">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />

<script type="text/javascript"> 
window.onload=function(){ 
   var linkbt=document.getElementById("linkbt"); 
   var light=document.getElementById('light'); 
   var fade=document.getElementById('fade'); 
   var closebt=document.getElementById("closebt"); 
   linkbt.onclick=function(){ 
     light.style.display='block'; 
     fade.style.display='block'; 
   } 
   closebt.onclick=function(){ 
     light.style.display='none'; 
     fade.style.display='none'; 
   } 
} 
</script> 
</head>

<body>
<%@ include file="/WEB-INF/page/common/headerIndex.jsp" %>

<table width="990" border="0" align="center" cellpadding="1" cellspacing="1" class="beijs">
  <tr>
    <td width="224" bgcolor="#FFFFFF">
      <table width="212" border="0" align="right" cellpadding="0" cellspacing="0">
        <tr>
          <td width="183" height="70" class="heizi16">您上次的登录时间：<br />
          <span class="dizhi"><fmt:formatDate value="${admin_user.lastLogin}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></span></td>
        </tr>
    </table></td>
    <td rowspan="5" align="center" bgcolor="#FFFFFF"><table width="500px;" border="0" align="center" cellpadding="0" cellspacing="0">
      <%-- <tr>
        <td height="60" align="right">业务联系人手机：</td>
        <td><input name="aContact_phone" style="width:230px" type="text" class="bk_yz" id="aContact_phone" />
          <span class="hz01">*</span></td>
        </tr>
      <tr>
        <td height="60" align="right">图片验证码：</td>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="110"><input name="imgcode" type="text" style="width:100px" class="bk_yz" id="imgcode" /></td>
            <td><img src="${ctx }/manageAdminUser/pcrimg" width="120" height="47" onclick="javascript:this.src='${ctx }/manageAdminUser/pcrimg'" /></td>
          </tr>
        </table>
          </img></td>
      </tr> --%>
      <tr>
	    <td height="60" align="right">旧密码： </td>
	    <td ><input name="opass" type="password" style="width:210px" class="bk_yz" id="opass" />
	    <span class="hz01">*</span></td>
	  </tr>
	   <tr>
	    <td height="60" align="right">新密码：</td>
	    <td><input name="npass" type="password" style="width:210px" class="bk_yz" id="npass" />
	    <span class="hz01">*</span></td>
	  </tr>
	   <tr>
	    <td height="60" align="right">重复新密码：</td>
	    <td><input name="npass1" type="password" style="width:210px" class="bk_yz" id="npass1" />
	    <span class="hz01">*</span></td>
	  </tr>
        <tr>
        <td height="50" colspan="2" align="center"><a href="javascript:void(0)" onclick="mo()" style="width:180px" class="lj_bc_14">修改密码</a></td>
      </tr>
    </table></td>
  </tr>
  <tr>
   <%@ include file="/WEB-INF/page/common/navIndex.jsp" %>
  </tr>
  <%@ include file="/WEB-INF/page/common/navIndex1.jsp" %>
  </tr>
</table>
<table width="990" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="554">&nbsp;</td>
  </tr>
</table>
<%@ include file="/WEB-INF/page/common/footerIndex.jsp" %>
<%@ include file="/WEB-INF/page/common/js.jsp" %>
<script>
function sendCode(){
	var phone = $.trim($("#aContact_phone").val());
	if(phone == ''){
		alert("请填写您的手机号");
	}else{
		if(!validatemobile(phone)){
			alert('业务联系人手机格式不正确');
			return false;
		}
		if(!$("#imgcode").val()){
			alert('请填写验证码！');
			return false;
		}
// 		$.post("<c:url value='/hCompany/mcode'/>",
//     	{
// 			vercode:$("#imgcode").val(),
// 			phone :phone,
// 			ranNum:Math.random()
// 		},
//     	function(data){
//         	var result = eval('('+data+')'); 
//             if (result.code == '1') {
//             	alert("发送成功，请查收新密码");
//             	var url = "<c:url value='/hCompany/mp'/>";
//             	$.post(url,{phone:phone,vercode:mcode},function(data){
//             		var result = eval('('+data+')'); 
//                     if (result.code == '1') {
//                       	alert('修改成功');
//                      } else {
//                     	 alert(result.message);
//                      }
//             	});
//              } else {
//             	countdown = 0;
//             	alert(result.message);
//              }
//         });
		
		var url = "<c:url value='/hCompany/mp'/>";
    	$.post(url,{phone:phone,vercode:mcode},function(data){
    		var result = eval('('+data+')'); 
            if (result.code == '1') {
            	alert("发送成功，请查收新密码");
//               	alert('修改成功');
             } else {
//             	 alert(result.message);
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
	var myreg = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
	if (!myreg.test(mobile)) {
		return false;
	}
	return true;
}

function mo(){
// 	var phone = $("#aContact_phone").val();
// 	var mcode = $("#imgcode").val();
	var opass = $("#opass").val();
	var npass = $("#npass").val();
	var npass1 = $("#npass1").val();
// 	if(!phone){ 
// 		alert('请填写手机号码');
// 		return false;
// 	}
// 	if(!mcode){
// 		alert('请填写验证码');
// 		return false;
// 	}
	 if(!opass){
		alert('请填写旧密码');
		return false;
	}
	if(!npass){
		alert('请填写新密码');
		return false;
	}
	if(!npass1){
		alert('请重复一遍新密码');
		return false;
	}
	if(npass!=npass1){
		alert('两次密码不一致，请检查');
		return false;
	}
	var url = "<c:url value='/hCompany/mp'/>";
	$.post(url,{opass:opass,npass:npass},function(data){
		var result = eval('('+data+')'); 
        if (result.code == '1') {
          	alert('修改成功');
         } else {
        	 alert(result.message);
         }
	});
}
</script>
</body>
</html>
