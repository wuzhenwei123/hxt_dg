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
<script type="text/javascript" src="<c:url value='/boo/assets/js/lib/jquery.js'/>"></script>
</head>

<body>
<%@ include file="/WEB-INF/page/common/headerIndex.jsp" %>

<table width="990" border="0" align="center" cellpadding="1" cellspacing="1" class="beijs">
  <tr>
    <td width="224" valign="top" bgcolor="#FFFFFF"><table width="100%" border="0" cellpadding="1" cellspacing="0" class="beijs">
      <tr>
        <td bgcolor="#FFFFFF"><table width="212" border="0" align="right" cellpadding="0" cellspacing="0">
          <tr>
            <td width="183" height="70" bgcolor="#FFFFFF" class="heizi16">您上次的登录时间：<br />
              <span class="dizhi"><fmt:formatDate value="${admin_user.lastLogin}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></span></td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td height="1" bgcolor="#999999"></td>
      </tr>
      <tr>
        <%@ include file="/WEB-INF/page/common/navIndex.jsp" %>
      </tr>
      
      <tr>
        <td height="1" bgcolor="#999999"></td>
      </tr>
      <tr>
        <%@ include file="/WEB-INF/page/common/navIndex2.jsp" %>
      </tr>
      <tr>
        <td colspan="2"><img src="${ctx}/images/xx01.png" width="212" height="1" /></td>
      </tr>
    </table></td>
    <td align="center" bgcolor="#FFFFFF"><table width="550" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="50" align="right">单位名称：</td>
        <td width="240"><input name="aName" type="text" style="width:230px" class="bk_yz" id="aName" value="${company.name }"/></td>
        <td class="hz01">*</td>
      </tr>
      <tr>
        <td height="50" align="right" nowrap="nowrap">业务联系人：</td>
        <td><input name="aContact_name" type="text" style="width:230px" class="bk_yz" id="aContact_name" value="${company.contact_name }"/></td>
        <td class="hz01">*</td>
        </tr>
      <tr>
        <td height="50" align="right">业务联系人手机：</td>
        <td><input name="aContact_phone" style="width:230px" type="text" class="bk_yz" id="aContact_phone" value="${company.contact_phone }"/></td>
        <td class="hz01">*</td>
        </tr>
      <tr>
        <td height="50" align="right">图片验证码：</td>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="110"><input name="imgcode" type="text" style="width:100px" class="bk_yz" id="imgcode" /></td>
            <td><img src="${ctx }/manageAdminUser/pcrimg" width="120" height="47" id="verfyImg" onclick="javascript:this.src='${ctx }/manageAdminUser/pcrimg'" /></td>
            </tr>
          </table>
         </td>
        <td class="hz01"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td>*</td>
              <td><a href="javascript:void(0)" onclick="sendCode()" style="width:100px" class="lj_bc_14" id='codeBtn'>发送验证码</a></td>
            </tr>
          </table></td>
        </tr>
      <tr>
        <td height="50" align="right">手机验证码：</td>
        <td><input name="vercode" style="width:230px" type="text" class="bk_yz" id="vercode"/></td>
        <td class="hz01">*</td>
        </tr>
      <tr>
        <td height="50" align="right">业务联系人邮箱：</td>
        <td><input name="aContact_mail" type="text" style="width:230px" class="bk_yz" id="aContact_mail" value="${company.contact_mail }"/></td>
        <td class="hz01">*</td>
      </tr>
     <%--  <tr>
        <td height="50" align="right">发票收件人姓名：</td>
        <td><input name="aFp_name" type="text" style="width:230px" class="bk_yz" id="aFp_name" value="${company.fp_name }"/></td>
        <td class="hz01">*</td>
        </tr>
      <tr>
        <td height="50" align="right">发票收件人手机：</td>
        <td><input name="aFp_phone" type="text" style="width:230px" class="bk_yz" id="aFp_phone" value="${company.fp_phone }"/></td>
        <td class="rq">发票收件人电话和手机必填其中一项</td>
      </tr>
      <tr>
        <td height="50" align="right" nowrap="nowrap">发票收件人电话：</td>
        <td><input name="aFp_telephone" type="text" style="width:230px" class="bk_yz" id="aFp_telephone"/></td>
        <td class="rq">发票收件人电话和手机必填其中一项</td>
      </tr>
      <tr>
        <td height="50" align="right">发票收件人地址：</td>
        <td><input name="aFp_address" type="text" style="width:230px" class="bk_yz" id="aFp_address" value="${company.fp_telephone }"/></td>
        <td class="hz01">*</td>
        </tr> --%>
      <tr>
        <td height="50" align="right">传真总机：</td>
        <td><input name="aFax" type="text" style="width:230px" class="bk_yz" id="aFax" value="${company.fax }"/></td>
        <td class="hz01">*</td>
        </tr>
      <tr>
        <td height="50" align="right">传真分机：</td>
        <td><input name="aFax_ext" type="text" style="width:230px" class="bk_yz" id="aFax_ext" value="${company.fax_ext }"/></td>
        <td class="rq">&nbsp;</td>
      </tr>
      <tr>
        <td height="50" align="left">&nbsp;</td>
        <td height="50" align="center"><a href="javascript:void(0)" onclick="up()" style="width:100px" class="lj_bc_14">完成</a></td>
        <td height="50">&nbsp;</td>
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
			$.post("<c:url value='/hCompany/vercode1'/>",
        	{
				vercode:$("#imgcode").val(),
				phone :phone,
				id:'${company.id }',
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
	
	function up(){
		var name = getVal("aName");
		var contact_name = getVal("aContact_name");
		var contact_phone = getVal("aContact_phone");
		var contact_mail = getVal("aContact_mail");
// 		var fp_name = getVal("aFp_name");
// 		var fp_phone = getVal("aFp_phone");
// 		var fp_telephone = getVal("aFp_telephone");
// 		var fp_address = getVal("aFp_address");
		var fax = getVal("aFax");
		var fax_ext = getVal("aFax_ext");
		var vercode = getVal("vercode");
		if(!validatemobile(contact_phone)){
			alert('业务联系人手机格式不正确')
			return false;
		}
// 		if(contact_mail){
			if(!checkEmail(contact_mail)){
				alert('业务联系人邮箱格式不正确')
				return false;
			}
// 		}
// 		if(!fp_phone&&!fp_telephone){
// 			alert('发票收件人手机和电话必填一项')
// 			return false;
// 		}
// 		if(!validatemobile(fp_phone)){
// 			alert('发票收件人手机格式不正确')
// 			return false;
// 		}
		
		if(!name){
			alert('单位名称不能为空')
			return false;
		}
		if(!contact_name){
			alert('业务联系人不能为空')
			return false;
		}
		if(!contact_phone){
			alert('业务联系人手机不能为空')
			return false;
		}
// 		if(!fp_name){
// 			alert('发票收件人姓名不能为空')
// 			return false;
// 		}
// 		if(!fp_address){
// 			alert('发票收件人地址不能为空')
// 			return false;
// 		}
		if(!fax){
			alert('传真总机不能为空')
			return false;
		}
		if(!vercode){
			alert('手机验证码不能为空')
			return false;
		}
		if(fax){
			if(!checkFax(fax)){
				alert('传真总机号码格式不正确');
				return false;
			}
		}
		if(fax_ext){
			if(!checkFax_ext(fax_ext)){
				alert('传真分机号码格式不正确');
				return false;
			}
		}
	    $.post("<c:url value='/hCompany/modify'/>",{
	    			id:'${company.id }',
		    		name : name,
		    		contact_name : contact_name,
		    		contact_phone : contact_phone,
		    		contact_mail : contact_mail,
// 		    		fp_name : fp_name,
// 		    		fp_phone : fp_phone,
// 		    		fp_telephone : fp_telephone,
// 		    		fp_address : fp_address,
		    		fax : fax,
		    		fax_ext : fax_ext,
		    		vercode : vercode,
				 _t:Math.random()},
	        	function(data){
		        	var result = eval('('+data+')'); 
		            if (result.code == '1') {
		              	var pageNo = $("#currPage").val();           
		              	alert("修改成功");
		             } else {
		            	 alert(result.message);
		             }
	        });
	}
	
	function checkEmail(str){
		var reg = new RegExp('^[a-zA-Z0-9]+@[a-zA-Z0-9]+.[a-z][a-z.]{2,8}$');
	    if(reg.test(str)){
	    	return true;
	    }else{
			return false;
	    }
	}
	
	function getVal(id){
		return $("#"+id).val();
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
				clearInterval(intervalID)
				getImg();
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
	function getImg(){
		var url = '${ctx }/manageAdminUser/pcrimg?_t='+Math.random();
		$("#verfyImg").attr("src",url);
	}
</script>
</body>
</html>
