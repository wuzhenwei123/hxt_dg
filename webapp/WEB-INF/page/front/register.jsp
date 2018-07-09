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
<script type="text/javascript" src="<c:url value='/boo/assets/js/lib/jquery.js'/>"></script>

</head>

<body>
<%@ include file="/WEB-INF/page/common/headerIndex.jsp" %>
<table width="990" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="554" height="30">&nbsp;</td>
  </tr>
</table>
<table width="660" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="30" align="center" class="sk01">1</td>
    <td width="284"><img src="${ctx}/images/cx.gif" width="284" height="29" /></td>
    <td align="center" class="sk02">2</td>
    <td width="284"><img src="${ctx}/images/cx.gif" width="284" height="29" /></td>
    <td align="center" class="sk02">3</td>
  </tr>
</table>
<table width="930" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="554" height="36" align="center" class="hz01" id='tip1'>注册账户信息</td>
    <td width="554" align="center" class="rq" id='tip2'>等待审核（3个工作日内）</td>
    <td width="554" align="center" class="rq" id='tip3'>审核成功，短信获取登录账户和密码</td>
  </tr>
</table>




<table width="990" border="0" align="center" cellpadding="1" cellspacing="1" class="beijs">
  <tr>
    <td bgcolor="#FFFFFF"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td width="554" height="30">&nbsp;</td>
      </tr>
    </table>
      <table width="700" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="50" align="right">企业名称：</td>
        <td width="240"><input name="aName" type="text" class="bk_yz" id="aName" style="width:360px" placeholder="请填写全称"/></td>
        <td class="hz01">&nbsp;</td>
      </tr>
      <tr>
        <td height="50" align="right" nowrap="nowrap">业务联系人姓名：</td>
        <td><input name="aContact_name" type="text" style="width:360px" class="bk_yz" id="aContact_name"/></td>
        <td class="hz01">&nbsp;</td>
      </tr>
      <tr>
        <td height="50" align="right">业务联系人手机：</td>
        <td><input name="aContact_phone" type="text" class="bk_yz" id="aContact_phone" style="width:360px" placeholder="请填写接收账号和密码短信的手机号"/></td>
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
        	<img src="${ctx }/manageAdminUser/pcrimg" width="120" height="47" onclick="javascript:this.src='${ctx }/manageAdminUser/pcrimg'" />
        </td>
      </tr>
      <tr>
        <td height="50" align="right">手机验证码：</td>
        <td><input name="vercode" type="text" class="bk_yz" id="vercode" style="width:360px" placeholder="请输入手机收到的验证码"/></td>
        <td class="hz01">&nbsp;</td>
      </tr>
      <tr>
        <td height="50" align="right" nowrap="nowrap">业务联系人EMAIL：</td>
        <td><input name="aContact_mail" type="text" style="width:360px" class="bk_yz" id="aContact_mail"/></td>
        <td class="hz01">&nbsp;</td>
      </tr>
      <tr>
        <td height="50" align="right">企业传真号码：</td>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="40">010-</td>
            <td><input name="aFax" type="text" style="width:150px" class="bk_yz" id="aFax" onkeyup="this.value=this.value.replace(/\D/gi,'')"/></td>
            <td width="20" align="center">-</td>
            <td><input name="aFax_ext" type="text" style="width:150px" class="bk_yz" id="aFax_ext" onkeyup="this.value=this.value.replace(/\D/gi,'')"/></td>
          </tr>
        </table></td>
        <td class="rq">&nbsp;</td>
      </tr>
      <tr>
        <td height="50" align="left">&nbsp;</td>
<!--         <td height="50" align="center"><a href="javascript:void(0)" onclick="up()" style="width:100px" id="linkbt" class="lj_bc_14">注册</a></td> -->
        <td height="50" align="center"><a href="javascript:void(0)" onclick="add()" style="width:100px" id="linkbt" class="lj_bc_14">注册</a></td>
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

<!-- 注册成功 -->
<div id="light" style='display:none;top:45%!important;left:30%!important;'>
<table width="560" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td align="right"><a href="javascript:void(0)" class="lj_hs_24" id="closebt">×</a></td>
  </tr>
</table>
<table width="560" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td valign="top"><table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr></tr>
      <tr>
        <td height="100"><p class="heizi16">　　您的注册信息已成功提交，我们将在3个工作日内完成审核，请您保持手机通畅，以便接收登录账号和密码信息。</p></td>
        </tr>
      <tr>
        <td height="40" align="center"><a href="${ctx}/index/first" style="width:100px" class="lj_dl_14">确　定</a></td>
      </tr>
    </table></td>
  </tr>
</table>
</div>



<%-- 

<table width="990" border="0" align="center" cellpadding="0" cellspacing="0" class="bk_xf">
  <tr>
    <td align="center" valign="bottom" class="heizi12">&nbsp;</td>
  </tr>
</table>
<table width="650" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="80" align="right">单位名称：</td>
    <td width="240"><input name="aName" type="text" style="width:230px" class="bk_yz" id="aName" /></td>
    <td class="rq" style="color:red!important;">*</td>
  </tr>
  <tr>
    <td height="80" align="right" nowrap="nowrap">业务联系人：</td>
    <td><input name="aContact_name" type="text" style="width:230px" class="bk_yz" id="aContact_name" /></td>
    <td class="rq" style="color:red!important;">*</td>
  </tr>
  <tr>
    <td height="80" align="right">业务联系人手机：</td>
    <td><input name="aContact_phone" style="width:230px" type="text" class="bk_yz" id="aContact_phone" /></td>
    <td class="rq" style="color:red!important;">*</td>
  </tr>
  <tr>
  	<td height="50" align="right">图片验证码：</td>
       <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
         <tr>
           <td width="110"><input name="imgcode" type="text" style="width:100px" class="bk_yz" id="imgcode" /></td>
           <td><img src="${ctx }/manageAdminUser/pcrimg" width="120" height="47" onclick="javascript:this.src='${ctx }/manageAdminUser/pcrimg'" /></td>
           </tr>
         </table>
        </td>
       <td class="hz01"><table width="100%" border="0" cellspacing="0" cellpadding="0">
           <tr>
             <td>*</td>
             <td><a href="javascript:void(0)" onclick="sendCode()" style="width:100px" class="lj_bc_14" id="codeBtn">发送验证码</a></td>
           </tr>
         </table></td>
  
  </tr>
  <tr>
    <td height="80" align="right">手机验证码：</td>
    <td><input name="vercode" type="text" style="width:100px" class="bk_yz" id="vercode" />
    </td>
    <td class="rq" style="color:red!important;">*</td>
  </tr>
  <tr>
    <td height="80" align="right">业务联系人邮箱：</td>
    <td><input name="aContact_mail" type="text" style="width:230px" class="bk_yz" id="aContact_mail" /></td>
    <td>&nbsp;</td>
  </tr>
  
   --%>
  <!-- 
   <tr>
    <td height="80" align="right">发票收件人姓名：</td>
    <td><input name="aFp_name" type="text" style="width:230px" class="bk_yz" id="aFp_name" /></td>
    <td class="rq" style="color:red!important;">*</td>
  </tr>
   <tr>
    <td height="80" align="right">发票收件人手机：</td>
    <td><input name="aFp_phone" type="text" style="width:230px" class="bk_yz" id="aFp_phone" /></td>
    <td class="rq">发票收件人电话和手机必填其中一项</td>
  </tr>
   <tr>
    <td height="80" align="right">发票收件人电话：</td>
    <td><input name="aFp_telephone" type="text" style="width:230px" class="bk_yz" id="aFp_telephone" /></td>
    <td class="rq">发票收件人电话和手机必填其中一项</td>
  </tr>
   <tr>
    <td height="80" align="right">发票收件人地址：</td>
    <td><input name="aFp_address" type="text" style="width:230px" class="bk_yz" id="aFp_address" /></td>
    <td class="rq" style="color:red!important;">*</td>
  </tr>
   -->
  
  
<!--    <tr>
    <td height="80" align="right">传真总机：</td>
    <td><input name="aFax" type="text" style="width:230px" class="bk_yz" id="aFax" /></td>
    <td class="rq" style="color:red!important;">*</td>
  </tr> -->
  <!-- 
   <tr>
    <td height="80" align="right">传真分机：</td>
    <td><input name="aFax_ext" type="text" style="width:230px" class="bk_yz" id="aFax_ext" /></td>
    <td class="rq">&nbsp;</td>
  </tr> -->
<!--   <tr> -->
<!--     <td height="50" colspan="3" align="center"><a href="javascript:void(0)" onclick="add()" style="width:200px" class="lj_bc_14">立即注册</a></td> -->
<!--     <td height="50" align="center"><a href="javascript:void(0)" onclick="add()" style="width:100px" id="linkbt" class="lj_bc_14">注册</a></td> -->
<!--   </tr> -->
</table>
<table width="990" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="554">&nbsp;</td>
  </tr>
</table>
<%@ include file="/WEB-INF/page/common/footerIndex.jsp" %>
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
		$.post("<c:url value='/hCompany/vercode'/>",
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
		var num = 179;
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
				clearInterval(intervalID)
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
	function add(){
		var name = getVal("aName");
		var contact_name = getVal("aContact_name");
		var contact_phone = getVal("aContact_phone");
		var contact_mail = getVal("aContact_mail");
		var fp_name = getVal("aFp_name");
		var fp_phone = getVal("aFp_phone");
		var fp_telephone = getVal("aFp_telephone");
		var fp_address = getVal("aFp_address");
		var fax = getVal("aFax");
		var fax_ext = getVal("aFax_ext");
		var vercode = getVal("vercode");
// 		var status = getVal("aStatus");
// 		var create_time = getVal("aCreate_time");
		/* var verify_reason = getVal("aVerify_reason");
		var verify_user_id = getVal("aVerify_user_id");
		var com_bank_code = getVal("aCom_bank_code");
		var verify_status = getVal("aVerify_status");
		var com_license_no = getVal("aCom_license_no");
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
		if(!validatemobile(contact_phone)){
			alert('业务联系人手机格式不正确');
			return false;
		}
		if(contact_mail){
			if(!checkEmail(contact_mail)){
				alert('业务联系人邮箱格式不正确');
				return false;
			}
		}
		/* if(!fp_phone&&!fp_telephone){
			alert('发票收件人手机和电话必填一项')
			return false;
		} */
// 		if(!validatemobile(fp_phone)){
// 			alert('发票收件人手机格式不正确')
// 			return false;
// 		}
		
		if(!name){
			alert('单位名称不能为空');
			return false;
		}
		if(!contact_name){
			alert('业务联系人不能为空');
			return false;
		}
		if(!contact_phone){
			alert('业务联系人手机不能为空')
			return false;
		}
		if(!contact_mail){
			alert('业务联系人EMAIL不能为空');
			return false;
		}
	/* 	if(!fp_name){
			alert('发票收件人姓名不能为空')
			return false;
		}
		if(!fp_address){
			alert('发票收件人地址不能为空')
			return false;
		} */
		if(!fax){
			alert('传真不能为空');
			return false;
		}else{
			var len = fax.length;
			if(len==8||len==11){
				
			}else{
				alert('传真号只允许填写8位数字字符或11位手机号');
				return false;
			}
		}
		if(!vercode){
			alert('手机验证码不能为空');
			return false;
		}
		if(flag){
			flag = false;
			$.post("<c:url value='/hCompany/addHCompany'/>",
		        	{
			    		name : name,
			    		contact_name : contact_name,
			    		contact_phone : contact_phone,
			    		contact_mail : contact_mail,
//	 		    		fp_name : fp_name,
//	 		    		fp_phone : fp_phone,
//	 		    		fp_telephone : fp_telephone,
//	 		    		fp_address : fp_address,
			    		fax : fax,
			    		fax_ext : fax_ext,
			    		vercode : vercode,
//	 		    		status : status,
//	 		    		create_time : create_time,
			    		/* verify_reason : verify_reason,
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
					 _t:Math.random()},
		        	function(data){
			        	var result = eval('('+data+')'); 
			            if (result.code == '1') {
			              	var pageNo = $("#currPage").val();
			              	$("#light").show();
			             } else {
			            	 flag = true;
			            	 alert(result.message);
			             }
		        });
		}
	}
	
	function checkEmail(str){
// 		var reg = new RegExp('^[a-zA-Z0-9]+@[a-zA-Z0-9]+.[a-z][a-z.]{2,8}$');
		var reg = new RegExp('^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$');
	    if(reg.test(str)){
	    	return true;
	    }else{
			return false;
	    }
	}
	
	function getVal(id){
		return $("#"+id).val();
	}
	$(function(){
		$("#closebt").click(function(){
			$("#light").hide();
		})
	})
</script>
</body>
</html>
