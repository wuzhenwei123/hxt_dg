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
<link href="<c:url value='/plus/webuploader/webuploader.css'/>" rel="stylesheet">
<script type="text/javascript">
function test()
{
    var divV = document.getElementById("Layer1");
    if(divV.style.visibility =="hidden")
    {
        divV.style.visibility ="visible";
    }
    else
    {
        divV.style.visibility = "hidden";
    }
}
</script>
</head>

<body>
<%@ include file="/WEB-INF/page/common/headerIndex.jsp" %>
<!--
<table width="990" border="0" align="center" cellpadding="1" cellspacing="1" class="beijs">
  <tr>
    <td width="224" bgcolor="#FFFFFF">
      <table width="212" border="0" align="right" cellpadding="0" cellspacing="0">
        <tr>
          <td width="183" height="70" class="heizi16">您上次的登录时间：<br />
          <span class="dizhi"><fmt:formatDate value="${admin_user.lastLogin}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></span></td>
        </tr>
    </table></td>
    <td rowspan="5" bgcolor="#FFFFFF">
    <table width="400px;" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="80" align="right">营业执照号：</td>
    <td>
    <input name="com_license_no" style="width:210px" type="text" class="bk_yz" id="com_license_no" value="${com.com_license_no }" <c:if test="${not empty com.com_license_no }">disabled="disabled"</c:if>/></td>
    <td class="rq" style="color:red!important;">*</td>
  </tr>
  <tr>
    <td height="80" align="right">营业执照照片：</td>
    <td>
    <img src="${ctx }/${com.com_license_img}" alt="" id="showimg1" width="200px" heigth="200px" onerror="javascript:this.src='';this.width='0px';this.height='0px'"/>
    <input name="com_license_img" type="hidden" style="width:100px" class="bk_yz" id="com_license_img" value="${com.com_license_img }" <c:if test="${not empty com.com_license_img }">disabled="disabled"</c:if>/>
    <div id="filePicker1">选择图片</div>
    </td>
    <td class="rq" style="color:red!important;"></td>
  </tr>
   <tr>
    <td height="80" align="right">税务登记证号：</td>
    <td width="240"><input name="com_tax_no" type="text" style="width:210px" class="bk_yz" id="com_tax_no" value="${com.com_tax_no }" <c:if test="${not empty com.com_tax_no }">disabled="disabled"</c:if>/></td>
    <td class="rq" style="color:red!important;">*</td>
  </tr>
   <tr>
    <td height="80" align="right">税务登记证照片：</td>
    <td width="240">
    <img src="${ctx }/${com.com_tax_img}" alt="" id="showimg2" width="200px" heigth="200px" onerror="javascript:this.src='';this.width='0px';this.height='0px'"/>
    <input name="com_tax_img" type="hidden" style="width:210px" class="bk_yz" id="com_tax_img" value="${com.com_tax_img }" <c:if test="${not empty com.com_tax_img }">disabled="disabled"</c:if>/>
    <div id="filePicker2">选择图片</div>
    </td>
    <td class="rq" style="color:red!important;"></td>
  </tr>
   <tr>
    <td height="80" align="right">组织机构代码：</td>
    <td width="240"><input name="com_dept_code" type="text" style="width:210px" class="bk_yz" id="com_dept_code" value="${com.com_dept_code }" <c:if test="${not empty com.com_dept_code }">disabled="disabled"</c:if>/></td>
    <td class="rq" style="color:red!important;">*</td>
  </tr>
   <tr>
    <td height="80" align="right">组织机构照片：</td>
    <td width="240">
    <img src="${ctx }/${com.com_dept_img}" alt="" id="showimg3" width="200px" heigth="200px" onerror="javascript:this.src='';this.width='0px';this.height='0px'"/>
    <input name="com_dept_img" type="hidden" style="width:210px" class="bk_yz" id="com_dept_img" value="${com.com_dept_img }" <c:if test="${not empty com.com_dept_img }">disabled="disabled"</c:if>/>
    <div id="filePicker3">选择图片</div>
    </td>
    <td class="rq" style="color:red!important;">*</td>
  </tr>
   <tr>
    <td height="80" align="right">税号：</td>
    <td width="240"><input name="com_duty_no" type="text" style="width:210px" class="bk_yz" id="com_duty_no" value="${com.com_duty_no }" <c:if test="${not empty com.com_duty_no }">disabled="disabled"</c:if>/></td>
    <td class="rq" style="color:red!important;">*</td>
  </tr>
   <tr>
    <td height="80" align="right">开户行名称：</td>
    <td width="240"><input name="com_bank_name" type="text" style="width:210px" class="bk_yz" id="com_bank_name" value="${com.com_bank_name }" <c:if test="${not empty com.com_bank_name }">disabled="disabled"</c:if>/></td>
    <td class="rq" style="color:red!important;">*</td>
  </tr>
   <tr>
    <td height="80" align="right">开户行账户名称：</td>
    <td width="240"><input name="com_account_name" type="text" style="width:210px" class="bk_yz" id="com_account_name" value="${com.com_account_name }" <c:if test="${not empty com.com_account_name }">disabled="disabled"</c:if>/></td>
    <td class="rq" style="color:red!important;">*</td>
  </tr>
   <tr>
    <td height="80" align="right">开户账号：</td>
    <td width="240"><input name="com_account_no" type="text" style="width:210px" class="bk_yz" id="com_account_no" value="${com.com_account_no }" <c:if test="${not empty com.com_account_no }">disabled="disabled"</c:if>/></td>
    <td class="rq" style="color:red!important;">*</td>
  </tr>
   <tr>
    <td height="80" align="right">开户行号：</td>
    <td width="240"><input name="remark1" type="text" style="width:210px" class="bk_yz" id="remark1" value="${com.remark1 }" <c:if test="${not empty com.remark1 }">disabled="disabled"</c:if>/></td>
    <td class="rq" style="color:red!important;">*</td>
  </tr>
  <tr>
    <td height="80" align="right">业务联系人手机：</td>
    <td><input name="aContact_phone" style="width:210px" type="text" class="bk_yz" id="aContact_phone" /></td>
    <td class="rq" style="color:red!important;">*</td>
  </tr>
  <tr>
    <td height="80" align="right">图片验证码：</td>
    <td><input name="imgcode" type="text" style="width:100px" class="bk_yz" id="imgcode" />
    	<img src="${ctx }/manageAdminUser/pcrimg" style="width:58px;" onclick="javascript:this.src='${ctx }/manageAdminUser/pcrimg'"></img>
    </td>
    <td class="rq" style="color:red!important;"><a href="javascript:void(0)" onclick="sendCode()" style="width:80px" class="lj_bc_14" id="codeBtn">发送验证码</a></td>
  </tr>
   <tr>
    <td height="80" align="right">手机验证码： </td>
    <td width="240"><input name="opass" type="text" style="width:210px" class="bk_yz" id="mcode" /></td>
    <td class="rq" style="color:red!important;">*</td>
  </tr>
  <tr>
    <td height="50" colspan="3" align="center"><a href="javascript:void(0)" onclick="mo()" style="width:200px" class="lj_bc_14">提交</a></td>
  </tr>
</table>
    </td>
  </tr>
</table>
<table width="990" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="554">&nbsp;</td>
  </tr>
</table>-->
<style type="text/css">
	.leftBox{
		width:224px;
		float:left;
	}
	.clear{
		display:block;
		font-size:0;
		line-height:0;
		clear:both;
	}
	.minfoBox{
		width:990px;
		margin:0 auto;
		overflow:hidden;
		padding:0;
	}
	.rightBox{
		margin-left:224px;
		padding-top:12px;
		padding-bottom:12px;
		overflow:hidden;
		
	}
	.leftNavBox{
		float:none;
		display:block;
	}
	.minfoBox{
		border-bottom:1px solid #808080;
		border-top:1px solid #808080;
		border-right:1px solid #808080;
		border-bottom:1px solid #808080;
	}
	.minfoBox,.rightBox{
		border-left:1px solid #808080;
	}
	.leftNav0a li.un{
		border-bottom:1px solid #808080;
	}
	.listTable tr td{
		padding-top:10px;
		padding-bottom:10px;
	}
	.leftNav0a{
		border-top:1px solid #808080;
	}
	.headBoxaa{
		border-bottom:1px solid #808080;
		padding:20px 12px;
	}
</style>
<div class="minfoBox">
<div class="leftBox">
    <div class="headBoxaa">您上次的登录时间：<br/>
    	<fmt:formatDate value="${admin_user.lastLogin}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></span>
    </div>
    <ul class="leftNavBox">
        <li class="nTitle">电费缴费</li>
        <li><a class="<c:if test="${nav=='dianbiao'}">hz01</c:if>" href="${ctx}/index/toDianbiao">管理电表和发票信息<span>>></span></a></li>
        <li class="un" ><a class="<c:if test="${nav=='dianfei'}">hz01</c:if>" href="${ctx}/index/toDianfei">电费查询与缴费<span>>></span></a></li>
    </ul>
    <ul class="leftNavBox leftNav0a">
        <li>
        <!--<a class="<c:if test="${nav=='dianbiao'}">hz01</c:if>" href="${ctx}/hSubCompany/goDianBiao">账单与发票<span>>></span></a>-->
        <a <c:if test="${nav=='fp'}">hz01</c:if> href="${ctx}/hPayOrder/getHPayOrderListIndex">账单与发票<span>>></span></a>
        </li>
        <li class="un">
        <!--<a class="<c:if test="${nav=='dianbiao'}">hz01</c:if>" href="${ctx}/hSubCompany/goDianBiao">企业信息管理<span>>></span></a>-->
        <a class="<c:if test="${nav=='minfo'}">hz01</c:if>" href="${ctx}/index/toMinfo">企业信息管理<span>>></span></a>
        </li>
    </ul>
    <ul class="leftNavBox">
        <li class="nTitle">管理联系人信息</li>
        <li><a href="${ctx}/index/toMcontact">变更联系人信息<span>>></span></a></li>
        <li class="un" ><a class="<c:if test="${nav=='mima'}">hz01</c:if>" href="${ctx}/index/toMpass">更改登录密码<span>>></span></a></li>
    </ul>
</div>
<div class="rightBox">
<input type="hidden" id="id" name="id" value="${company.id}"/>
	<table width="700px;" class="listTable" border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td align="right" width="250px;">营业执照号：</td>
                    <td>
                    <input name="com_license_no" style="width:210px" type="text" class="bk_yz" id="com_license_no" value="${company.com_license_no }" <c:if test="${not empty company.com_license_no }">disabled="disabled"</c:if>/></td>
                    <td class="rq" style="color:red!important;">*</td>
                  </tr>
                  <tr>
                    <td align="right">营业执照照片：</td>
                    <td>
                    <img src="${ctx }/${company.com_license_img}" alt="" id="showimg1" width="200px" heigth="200px" onerror="this.onerror='';this.src='';this.width='0px';this.height='0px';return false;"/>
                    <input name="com_license_img" type="hidden" style="width:100px" class="bk_yz" id="com_license_img" value="${company.com_license_img }" <c:if test="${not empty company.com_license_img }">disabled="disabled"</c:if>/>
                    <div id="filePicker1">选择图片</div>
                    </td>
                    <td class="rq" style="color:red!important;"></td>
                  </tr>
                   <tr>
                    <td align="right">税务登记证号：</td>
                    <td width="240"><input name="com_tax_no" type="text" style="width:210px" class="bk_yz" id="com_tax_no" value="${company.com_tax_no }" <c:if test="${not empty company.com_tax_no }">disabled="disabled"</c:if>/></td>
                    <td class="rq" style="color:red!important;">*</td>
                  </tr>
                   <tr>
                    <td align="right">税务登记证照片：</td>
                    <td width="240">
                    <img src="${ctx }/${company.com_tax_img}" alt="" id="showimg2" width="200px" heigth="200px" onerror="this.onerror='';this.src='';this.width='0px';this.height='0px';return false;"/>
                    <input name="com_tax_img" type="hidden" style="width:210px" class="bk_yz" id="com_tax_img" value="${company.com_tax_img }" <c:if test="${not empty company.com_tax_img }">disabled="disabled"</c:if>/>
                    <div id="filePicker2">选择图片</div>
                    </td>
                    <td class="rq" style="color:red!important;"></td>
                  </tr>
                   <tr>
                    <td align="right">组织机构代码：</td>
                    <td width="240"><input name="com_dept_code" type="text" style="width:210px" class="bk_yz" id="com_dept_code" value="${company.com_dept_code }" <c:if test="${not empty company.com_dept_code }">disabled="disabled"</c:if>/></td>
                    <td class="rq" style="color:red!important;">*</td>
                  </tr>
                   <tr>
                    <td align="right">组织机构照片：</td>
                    <td width="240">
                    <img src="${ctx }/${company.com_dept_img}" alt="" id="showimg3" width="200px" heigth="200px" onerror="this.onerror='';this.src='';this.width='0px';this.height='0px';return false;"/>
                    <input name="com_dept_img" type="hidden" style="width:210px" class="bk_yz" id="com_dept_img" value="${company.com_dept_img }" <c:if test="${not empty company.com_dept_img }">disabled="disabled"</c:if>/>
                    <div id="filePicker3">选择图片</div>
                    </td>
                    <td class="rq" style="color:red!important;">*</td>
                  </tr>
                   <tr>
                    <td align="right">税号：</td>
                    <td width="240"><input name="com_duty_no" type="text" style="width:210px" class="bk_yz" id="com_duty_no" value="${company.com_duty_no }" <c:if test="${not empty company.com_duty_no }">disabled="disabled"</c:if>/></td>
                    <td class="rq" style="color:red!important;">*</td>
                  </tr>
                   <tr>
                    <td align="right">开户行名称：</td>
                    <td width="240"><input name="com_bank_name" type="text" style="width:210px" class="bk_yz" id="com_bank_name" value="${company.com_bank_name }" <c:if test="${not empty company.com_bank_name }">disabled="disabled"</c:if>/></td>
                    <td class="rq" style="color:red!important;">*</td>
                  </tr>
                   <tr>
                    <td align="right">开户行账户名称：</td>
                    <td width="240"><input name="com_account_name" type="text" style="width:210px" class="bk_yz" id="com_account_name" value="${company.com_account_name }" <c:if test="${not empty company.com_account_name }">disabled="disabled"</c:if>/></td>
                    <td class="rq" style="color:red!important;">*</td>
                  </tr>
                   <tr>
                    <td align="right">开户账号：</td>
                    <td width="240"><input name="com_account_no" type="text" style="width:210px" class="bk_yz" id="com_account_no" value="${company.com_account_no }" <c:if test="${not empty company.com_account_no }">disabled="disabled"</c:if>/></td>
                    <td class="rq" style="color:red!important;">*</td>
                  </tr>
                   <tr>
                    <td align="right">开户行号：</td>
                    <td width="240"><input name="remark1" type="text" style="width:210px" class="bk_yz" id="remark1" value="${company.remark1 }" <c:if test="${not empty company.remark1 }">disabled="disabled"</c:if>/></td>
                    <td class="rq" style="color:red!important;">*</td>
                  </tr>
<!--                   <tr > -->
<!--                     <td align="right">业务联系人手机：</td> -->
<!--                     <td><input name="aContact_phone" style="width:210px" type="text" class="bk_yz" id="aContact_phone" /></td> -->
<!--                     <td class="rq" style="color:red!important;">*</td> -->
<!--                   </tr> -->
<!--                   <tr> -->
<!--                     <td align="right">图片验证码：</td> -->
<!--                     <td valign="middle"><input name="imgcode" style="vertical-align:middle" type="text" style="width:100px" class="bk_yz" id="imgcode" /> -->
<!--                         <img style="vertical-align:middle" src="${ctx }/manageAdminUser/pcrimg" width="120" height="47" onclick="javascript:this.src='${ctx }/manageAdminUser/pcrimg'"></img> -->
<!--                     </td> -->
<!--                     <td class="rq" style="color:red!important;"><a href="javascript:void(0)" onclick="sendCode()" style="width:80px" class="lj_bc_14" id="codeBtn">发送验证码</a></td> -->
<!--                   </tr> -->
<!--                    <tr> -->
<!--                     <td align="right">手机验证码： </td> -->
<!--                     <td width="240"><input name="opass" type="text" style="width:210px" class="bk_yz" id="mcode" /></td> -->
<!--                     <td class="rq" style="color:red!important;">*</td> -->
<!--                   </tr> -->
                  <tr>
                    <td height="50" colspan="3" align="center"><a href="javascript:void(0)" onclick="mo()" style="width:200px" class="lj_bc_14">提交</a></td>
                  </tr>
                </table>
</div>
<span class="clear"></span>
</div>
<div style="height:16px; overflow:hidden;"></div>






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
		$.post("<c:url value='/hCompany/mcode'/>",
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
// 	var mcode = $("#mcode").val();
	var com_license_no = $("#com_license_no").val();
	var com_license_img = $("#com_license_img").val();
	var com_tax_no = $("#com_tax_no").val();
	var com_tax_img = $("#com_tax_img").val();
	var com_dept_code = $("#com_dept_code").val();
	var com_dept_img = $("#com_dept_img").val();
	var com_duty_no = $("#com_duty_no").val();
	var com_bank_name = $("#com_bank_name").val();
	var com_account_name = $("#com_account_name").val();
	var com_account_no = $("#com_account_no").val();
	var remark1 = $("#remark1").val();
	var id = $("#id").val();
// 	if(!phone){ 
// 		alert('请填写手机号码');
// 		return false;
// 	}
// 	if(!mcode){
// 		alert('请填写手机验证码');
// 		return false;
// 	}
	var url = "<c:url value='/hCompany/modify'/>";
	$.post(url,{
		id:id,
		com_license_no:com_license_no,
		com_license_img:com_license_img,
		com_tax_no:com_tax_no,
		com_tax_img:com_tax_img,
		com_dept_code:com_dept_code,
		com_dept_img:com_dept_img,
		com_duty_no:com_duty_no,
		com_bank_name:com_bank_name,
		com_account_name:com_account_name,
		com_account_no:com_account_no,
		remark1:remark1
// 		,
// 		phone:phone,vercode:mcode
		},function(data){
		var result = eval('('+data+')'); 
        if (result.code == '1') {
          	alert('修改成功');
         } else {
        	 alert(result.message);
         }
	});
}

$(function(){
	var uploader;
	setTimeout(function() {
		uploader = WebUploader.create({
		    auto: true,
		    swf: '${ctx}/plus/webuploader/Uploader.swf',
		    server: '${ctx}/hLbImg/uploadPic',
		   	pick: {
		    	id:'#filePicker1',
		    	multiple : false//是否开起同时选择多个文件能力
		    },
		    compress:false,
		    resize: false,
		    fileSizeLimit:2048000,
		    accept: {
		        title: 'Images',
		        extensions: 'gif,jpg,jpeg,bmp,png',
		        mimeTypes: 'image/*'
		    },
		    formData:{ // 参数
		    }
		});
		uploader.on( 'uploadSuccess', function( file,response ) {
			uploader.reset();
			var servername = response.serverfileName;
			$("#showimg1").attr("src","${ctx}"+servername);
			$("#showimg1").attr('width','200px')
			$("#showimg1").attr('height','200px')
			$("#com_license_img").val(servername);
		});
		uploader.on( 'uploadError', function( file ) {
		    alert("上传失败!");
		});
	}, 200);
	var uploader1;
	setTimeout(function() {
		uploader1 = WebUploader.create({
		    auto: true,
		    swf: '${ctx}/plus/webuploader/Uploader.swf',
		    server: '${ctx}/hLbImg/uploadPic',
		   	pick: {
		    	id:'#filePicker2',
		    	multiple : false//是否开起同时选择多个文件能力
		    },
		    compress:false,
		    resize: false,
		    accept: {
		        title: 'Images',
		        extensions: 'gif,jpg,jpeg,bmp,png',
		        mimeTypes: 'image/*'
		    },
		    formData:{ // 参数
		    }
		});
		uploader1.on( 'uploadSuccess', function( file,response ) {
			uploader1.reset();
			var servername = response.serverfileName;
			$("#showimg2").attr("src","${ctx}"+servername);
			$("#showimg2").attr('width','200px')
			$("#showimg2").attr('height','200px')
			$("#com_tax_img").val(servername);
		});
		uploader1.on( 'uploadError', function( file ) {
		    alert("上传失败!");
		});
	}, 200);
	var uploader2;
	setTimeout(function() {
		uploader2 = WebUploader.create({
		    auto: true,
		    swf: '${ctx}/plus/webuploader/Uploader.swf',
		    server: '${ctx}/hLbImg/uploadPic',
		   	pick: {
		    	id:'#filePicker3',
		    	multiple : false//是否开起同时选择多个文件能力
		    },
		    compress:false,
		    resize: false,
		    accept: {
		        title: 'Images',
		        extensions: 'gif,jpg,jpeg,bmp,png',
		        mimeTypes: 'image/*'
		    },
		    formData:{ // 参数
		    }
		});
		uploader2.on( 'uploadSuccess', function( file,response ) {
			uploader2.reset();
			var servername = response.serverfileName;
			$("#showimg3").attr("src","${ctx}"+servername);
			$("#showimg3").attr('width','200px')
			$("#showimg3").attr('height','200px')
			$("#com_dept_img").val(servername);
		});
		uploader2.on( 'uploadError', function( file ) {
		    alert("上传失败!");
		});
	}, 200);
});
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
		}
	}, 1000);
}
</script>
<script type="text/javascript" src="<c:url value='/plus/webuploader/webuploader.js'/>"></script>
</body>
</html>
