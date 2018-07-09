<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${_titleIndex}</title>
<meta name="keywords" content="恒信通,易付通,企业网上交电费,公司网上交电费" />
<meta name="description" content="北京恒信通电信服务有限公司，成立于2002年，是国家电网北京市电力公司指定的代收费机构，恒信通依托“易付通”公共事业缴费服务品牌，为企业、公司、物业、工厂等企事业单位提供便捷的、一站式的网上交电费服务。" />
<meta content="textml;charset=utf-8" http-equiv="content-type">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta content="webkit|ie-comp|ie-stand" name="renderer">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/css_r.css" rel="stylesheet" type="text/css" />
</head>

<body>
<%@ include file="/WEB-INF/page/common/headerIndex.jsp" %>
<table width="990" border="0" align="center" cellpadding="0" cellspacing="0" class="bk_xf">
  <tr>
    <td width="554">&nbsp;</td>
  </tr>
</table>

<!-- <table width="900" border="0" align="center" cellpadding="1" cellspacing="1"> -->
<!--   <tr> -->
<!--     <td width="224" bgcolor="#FFFFFF">&nbsp;</td> -->
<!--   </tr> -->
<!--   <tr> -->
<!--     <td height="50" align="center" bgcolor="#FFFFFF" class="bt01">忘记密码</td> -->
<!--   </tr> -->
<!--   <tr> -->
<!--    <td rowspan="5" align="center" bgcolor="#FFFFFF"><table width="500px;" border="0" align="center" cellpadding="0" cellspacing="0"> -->
<!--       <tr> -->
<!--         <td height="60" align="right">业务联系人手机：</td> -->
<!--         <td><input name="aContact_phone" style="width:230px" type="text" class="bk_yz" id="aContact_phone" /> -->
<!--           <span class="hz01">*</span></td> -->
<!--         </tr> -->
<!--       <tr> -->
<!--         <td height="60" align="right">图片验证码：</td> -->
<!--         <td><table width="100%" border="0" cellspacing="0" cellpadding="0"> -->
<!--           <tr> -->
<!--             <td width="110"><input name="imgcode" type="text" style="width:100px" class="bk_yz" id="imgcode" /></td> -->
<!--             <td><img src="${ctx }/manageAdminUser/pcrimg" width="120" height="47" onclick="javascript:this.src='${ctx }/manageAdminUser/pcrimg'" /></td> -->
<!--           </tr> -->
<!--         </table> -->
<!--           </img></td> -->
<!--       </tr> -->
<!--         <tr> -->
<!--         <td height="50" colspan="2" align="center"><a href="javascript:void(0)" onclick="mo()" style="width:180px" class="lj_bc_14">修改密码</a></td> -->
<!--       </tr> -->
<!--     </table></td> -->
<!--    </tr></table><table width="990" border="0" align="center" cellpadding="1" cellspacing="1"><tr> -->
<!--   </tr> -->
<!-- </table> -->
<!-- <table width="990" border="0" align="center" cellpadding="1" cellspacing="1"> -->
<!--   <tr> </tr> -->
<!-- </table> -->

<table width="990" border="0" align="center" cellpadding="1" cellspacing="1" class="beijs">
  <tr>
    <td bgcolor="#FFFFFF"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="50" align="center" bgcolor="#FFFFFF" style="color: #F1511B;font-size: 18px;font-weight: bold;">忘记密码</td>
      </tr>
    </table>
      <table width="700" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="50" align="right">业务联系人手机：</td>
        <td><input name="aContact_phone" type="text" class="bk_yz" id="aContact_phone" style="width:360px" placeholder="请填写业务联系人手机号"/></td>
        <td class="hz01"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td>&nbsp;</td>
<!--             <td align="center"><a href="javascript:void(0)" onclick="up()" style="width:120px" class="lj_bc_14">发送短信验证码</a></td> -->
            <td align="center"><a href="javascript:void(0)" onclick="sendCode()" style="width:120px" class="lj_bc_14" id="codeBtn">发送短信验证码</a></td>
          </tr>
        </table></td>
      </tr>
       <tr>
        <td height="50" align="right">图片验证码：</td>
        <td></input>
          <input name="aFp_name2" type="text" class="bk_yz" id="imgcode" style="width:360px" placeholder="请输入右侧的验证码，如看不清可点击图片重新生成"/></td>
        <td align="center" class="hz01">
        	<img src="${ctx }/manageAdminUser/pcrimg" width="120" height="47" id="verfyImg" onclick="javascript:this.src='${ctx }/manageAdminUser/pcrimg'" />
        </td>
      </tr>
      <tr>
        <td height="50" align="right">手机验证码：</td>
        <td><input name="vercode" type="text" class="bk_yz" id="vercode" style="width:360px" placeholder="请输入手机收到的验证码"/></td>
        <td class="hz01">&nbsp;</td>
      </tr>
      <tr>
        <td height="50" align="left">&nbsp;</td>
<!--         <td height="50" align="center"><a href="javascript:void(0)" onclick="up()" style="width:100px" id="linkbt" class="lj_bc_14">注册</a></td> -->
        <td height="50" align="center"><a href="javascript:void(0)" onclick="mo()" style="width:100px" id="linkbt" class="lj_bc_14">下一步</a></td>
        <td height="50">&nbsp;</td>
      </tr>
    </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="554" height="30">&nbsp;</td>
        </tr>
    </table></td>
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
		$.post("<c:url value='/index/sendCode'/>",
       	{
			vercode:$("#imgcode").val(),
			phone :phone,
			ranNum:Math.random()
		},
       	function(data){
        	var result = eval('('+data+')'); 
            if (result.code == '1') {
            	alert("发送成功，请注意查收验证码");
            	btnNum();
             } else {
            	countdown = 0;
            	alert(result.message);
             }
        });
	}
}

function btnNum(){
	var num = 59;
	$("#codeBtn").attr('disabled',true);
	$("#codeBtn").html('还剩'+num-- +'秒');
	var intervalID = setInterval(function(){
		if(num>0){
			$("#codeBtn").attr('disabled',true);
			$("#codeBtn").attr('onclick','');
			$("#codeBtn").html('还剩'+num+'秒');
			num--;
		}else{
			$("#codeBtn").html('发送验证码');
			$("#codeBtn").attr('disabled',false);
			$("#codeBtn").attr('onclick','sendCode()');
			clearInterval(intervalID);
			getImg();
		}
	}, 1000);
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
var flag = true;
function mo(){
	var phone = $("#aContact_phone").val();
	var vercode = $("#vercode").val();
// 	var opass = $("#opass").val();
// 	var npass = $("#npass").val();
// 	var npass1 = $("#npass").val();
	if(!phone){ 
		alert('请填写手机号码');
		return false;
	}
	if(!vercode){
		alert('请填写手机验证码');
		return false;
	}
	/* if(!opass){
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
	} */
	if(flag){
		flag = false;
		var url = "<c:url value='/index/forget'/>";
		$.post(url,{phone:phone,vercode:vercode},function(data){
			var result = eval('('+data+')'); 
	        if (result.code == '1') {
	          	alert('发送成功，请查收新密码');
	         } else {
	        	 flag = true;
	        	 alert(result.message);
	         }
		});
	}
}
function getImg(){
	var url = '${ctx }/manageAdminUser/pcrimg?_t='+Math.random();
	$("#verfyImg").attr("src",url);
}
</script>
</body>
</html>
