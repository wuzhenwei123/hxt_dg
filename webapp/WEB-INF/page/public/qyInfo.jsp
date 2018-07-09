<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<html>
<head>
<meta charset="utf-8">
<title>${_title}</title>
	<meta name="keywords" content="${_keywords}" />
	<meta name="description" content="${_description}" />
	<meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<meta content="webkit|ie-comp|ie-stand" name="renderer">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="${ctx}/css/weui.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/public/custom/public/css/customForm.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/public/css/style.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/public/css/nav.css">
    <link rel="stylesheet" href="<c:url value='/boo/assets/plugins/My97DatePicker/skin/WdatePicker.css'/>">
    
    <link href="<c:url value='/plus/webuploader/webuploader.css'/>" rel="stylesheet">
    <style type="text/css">
    	.webuploader-pick{
    		padding: 0px;
    		width: 100%;
    		color: #01a796;
    		background:#fff;
    	}
    	.pItem1 {
/* 		    border: 1px solid #dddddd; */
		    height: 32px;
		    border-radius: 4px;
		    overflow: hidden;
		    vertical-align: middle;
		    width: 300px;
		    position: relative;
		}
		.cContentBox {
		    padding-left: 155px! important;
		    padding-top: 10px;
		}
		
		.codeTitle {
		    width: 85px;
		    font-size: 14px;
		    color: #000000;
		    vertical-align: middle;
		    margin-right: 90px! important;
		}
		.pBtnBox {
		    padding-top: 10px;
		    width: 300px! important;
		    margin-left: 175px;
		}
		.public_area{
			width: 96px! important;
		}
    </style>
</head>
<body>
<%@ include file="/WEB-INF/page/public/common/top.jsp" %>

<!---->
<div class="box main">
	<%@ include file="/WEB-INF/page/public/common/left.jsp" %>
	<input type="hidden" id="projectPath" value="${ctx}">
    <div class="rightBox">
    	<div class="commBody minHeight">
        	<div class="pTitle qyInfoBox"><a class="lines on" href="${ctx }/public/qyInfo">企业基本信息<span></span></a><a class="lines" href="${ctx }/public/caiwu">增票基本信息<span></span></a></div>
            <p class="yMsg"></p>
            <div class="cContentBox" id="content1">
                <div class="lineP pItems">
                	<label class="lines codeTitle">单位名称：</label>
                    <div class="lines pItem">
                    	<input type="text" class="pInput" id="name" value="${company.name }">
                    </div>
                </div>
                <div class="lineP pItems">
                	<label class="lines codeTitle">地址：</label>
                    <div class="lines pItem1">
                    	<select id="m_province_code" class="public_area" gov-value="${company.province_code }"></select>
								<select id="m_city_code" class="public_area" gov-value="${company.city_code }"></select>
								<select id="m_area_code" class="public_area" gov-value="${company.area_code }"></select>
                    </div>
                </div>
<!--                 <div class="lineP pItems"> -->
<!--                     <dl class="pItem1 selPIten"> -->
<!--                         <dd class="lines" style="width:100%;"> -->
<!--                         	<label class="lines codeTitle">地址：</label> -->
<!--                             <div class='diy_select select1'> -->
                            	
<!--                     		    <select id="m_province_code" class="public_area" gov-value="${company.province_code }"></select> -->
<!-- 								<select id="m_city_code" class="public_area" gov-value="${company.city_code }"></select> -->
<!-- 								<select id="m_area_code" class="public_area" gov-value="${company.area_code }"></select> -->
<!--                             </div> -->
<!--                         </dd> -->
<!--                     </dl> -->
<!--                 </div> -->
                <div class="lineP pItems">
                	<label class="lines codeTitle">详细营业地址：</label>
                    <div class="lines pItem">
                    	<input type="text" class="pInput" id="com_address" value="${company.com_address }">
                    </div>
                </div>
                
                <div class="lineP pItems">
                	<label class="lines codeTitle">营业地址邮政编码：</label>
                    <div class="lines pItem">
                    	<input type="text" class="pInput" id="com_zip_code" value="${company.com_zip_code }">
                    </div>
                </div>
                
                
                <div class="pItems xyItems">
                    <div class="pBtnBox">
                    	<a href="javascript:void(0);" onclick="goNext()" class="iBtn">下一步</a>
                    </div>
                </div>
                
            </div>
        	
    	    <div class="cContentBox qyCentBox" id="content2" style="display: none;">
                
                <div class="lineP pItems">
                    <dl class="pItem1 selPIten">
                        <dd class="lines" style="width:100%;">
                            <div class='diy_select select1'>
                            	请选择营业证件类型：
                                <select id="com_business_doc_type" class="public_area" style="width: 298px;margin-left:31px;" onchange="selZ(this.value)">
                                	<option <c:if test="${company.com_business_doc_type ==1 }">selected="selected"</c:if> value="1">五证合一</option>
                                	<option <c:if test="${company.com_business_doc_type ==2 }">selected="selected"</c:if> value="2">非五证合一</option>
                                </select>
<!--                                 <input type='hidden' name='' class='diy_select_input'/> -->
<!--                                 <div class='diy_select_txt'>请选择营业证件类型</div> -->
<!--                                 <div class='diy_select_btn'></div> -->
<!--                                 <ul class='diy_select_list'> -->
<!--                                     <li>北京东城区</li> -->
<!--                                     <li>北京东城区</li> -->
<!--                                 </ul> -->
                            </div>
                        </dd>
                    </dl>
                </div>
                <div class="lineP pItems" id="credit_codeDiv">
                	<label class="lines codeTitle">企业统一社会信用代码：</label>
                    <div class="lines pItem">
                    	<input type="text" class="pInput" id="credit_code" value="${company.credit_code }">
                    </div>
                </div>
                <div class="lineP pItems" id="license_noDiv" style="display: none;">
                	<label class="lines codeTitle">营业执照号码：</label>
                    <div class="lines pItem">
                    	<input type="text" class="pInput" id="com_license_no" value="${company.com_license_no }">
                    </div>
                </div>
                
                <div class="lineP pItems" id="license_noDivImg">
                	<label class="lines codeTitle">营业执照副本：</label>
                    <div class="lines pItem pUpBox">
                    	<label class="upFileLabel">
                    		<div id="file1">
                    		选择营业执照副本...
                    		</div>
                    	</label>
                    	<input id="com_license_img" value="${company.com_license_img }" type="hidden">
                    </div>
                    <label class="lines codeTitle" style="color: red;"><img src="${ctx}/images/loading.gif" id="file1Gif" style="display: none;">(上传图片最大2M)</label>
                </div>
                 <div class="lineP pItems" <c:if test="${empty company.com_license_img }">style="display:none;"</c:if> id="licenseImgP">
                 		<label class="lines codeTitle">营业执照副本预览：</label>
                    	<img src="${ctx }${company.com_license_img }" id="img1" style="width:300px;height:100px;" onclick="preview('${company.com_license_img }',this)">
                 </div>
                <div class="lineP pItems">
                	<label class="lines codeTitle">营业执照有效期开始日期：</label>
                    <div class="lines pItem">
                    	<input type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" class="pInput" id="com_license_start" value="<fmt:formatDate value="${ company.com_license_start}" pattern="yyyy-MM-dd"></fmt:formatDate>">
                    </div>
                </div>
                <div class="lineP pItems">
                	<label class="lines codeTitle">营业执照有效期结束日期：</label>
                    <div class="lines pItem">
                    	<input type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" class="pInput" id="com_license_end" value="<fmt:formatDate value="${ company.com_license_end}" pattern="yyyy-MM-dd"></fmt:formatDate>">
                    </div>
                </div>
                <div class="lineP pItems" id="taxDiv" style="display: none;">
                	<label class="lines codeTitle">税务登记证号码：</label>
                    <div class="lines pItem">
                    	<input type="text" class="pInput" id="com_tax_no" value="${company.com_tax_no }">
                    </div>
                </div>
                <div class="lineP pItems" id="taxImgDiv" style="display: none;">
                	<label class="lines codeTitle">税务登记证照片：</label>
                    <div class="lines pItem pUpBox">
                    	<label class="upFileLabel">
                    		<div id="file2">
                    		浏览税务登记证照片...
                    		</div>
                    	</label>
                    	<input id="com_tax_img" value="${company.com_tax_img }" type="hidden">
                    </div>
                    <label class="lines codeTitle" style="color: red;"><img src="${ctx}/images/loading.gif" id="file2Gif" style="display: none;">(上传图片最大2M)</label>
                </div>
                  <div class="lineP pItems" <c:if test="${empty company.com_tax_img }">style="display:none;"</c:if> id="taxImgDivP">
                 		<label class="lines codeTitle">税务登记证照片预览：</label>
                    	<img src="${ctx}${company.com_tax_img }" id="img2" style="width:300px;height:100px;" onclick="preview('${company.com_tax_img }',this)">
                 </div>
                <div class="lineP pItems" id="deptDiv" style="display: none;">
                	<label class="lines codeTitle">组织机构代码证号：</label>
                    <div class="lines pItem">
                    	<input type="text" class="pInput" id="com_dept_code" value="${company.com_dept_code }">
                    </div>
                </div>
                <div class="lineP pItems" id="deptImgDiv" style="display: none;">
                	<label class="lines codeTitle">组织机构代码证照片：</label>
                    <div class="lines pItem pZZ">
                    	<label class="upFileLabel">
                    		<div id="file3">
                    		浏览组织机构代码证照片...
                    		</div>
                    	</label>
                    	<input id="com_dept_img" value="${company.com_dept_img }" type="hidden">
                    </div>
                    <label class="lines codeTitle" style="color: red;"><img src="${ctx}/images/loading.gif" id="file3Gif" style="display: none;">(上传图片最大2M)</label>
                </div>
                 <div class="lineP pItems" <c:if test="${empty company.com_dept_img }">style="display:none;"</c:if> id="deptImgDivP">
                 		<label class="lines codeTitle">组织机构代码证照片预览：</label>
                    	<img src="${ctx}${company.com_dept_img }" id="img3" style="width:300px;height:100px;" onclick="preview('${company.com_dept_img }',this)">
                 </div>
                
                <div class="pItems xyItems" style="margin-bottom: 20px;">
                    <div class="pBtnBox">
                    	<a href="javascript:void(0);" onclick="sub();" class="iBtn">提交</a>
                    </div>
                </div>
                
            </div>
        </div>
    </div>
</div>
<div class="preview maskbg" data-p="" onclick="closePreview()">
	<img src="images/icon.jpg" />
</div>
<input id="mId" value="${company.id }" type="hidden" />
	<%@ include file="/WEB-INF/page/public/common/footer.jsp" %>

<script src="${ctx }/public/js/jquery-1.8.2.min.js"></script>
<script src="${ctx}/public/xcConfirm/js/xcConfirm.js"></script>
<script src="${ctx}/js/area_public.js"></script>
<%-- <script src="${ctx}/public/custom/public/js/select.js"></script> --%>
<script type="text/javascript" src="<c:url value='/plus/webuploader/webuploader.js'/>"></script>  
<!-- 日期插件 -->
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/My97DatePicker/WdatePicker.js'/>"></script> 
<script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>  
<script>
	$(document).ready(function(){
// 		$('input').placeholder();
		selZ(${company.com_business_doc_type});
		new $gov("m_province_code","m_city_code","m_area_code").init();
		upload("${ctx}","file1",upload_call_back1,'','','pic');
		upload("${ctx}","file2",upload_call_back2,'','','pic');
		upload("${ctx}","file3",upload_call_back3,'','','pic');
// 		if($("#com_license_start").val()!=""){
// 			$("#com_license_start").val($("#com_license_start").val());
// 		}
// 		if($("#com_license_end").val()!=""){
// 			$("#com_license_end").val($("#com_license_end").val());
// 		}
	});
	function upload_call_back1(response){
		if(response.code == '1'){
			var fileList = response.list;
			if(fileList.length == 1){
				var newFileName = fileList[0].newName;
				var oldName = fileList[0].oldName;
				$("#com_license_img").val(newFileName);
				$("#img1").attr('src',$("#projectPath").val()+newFileName);
				$("#file1 > .webuploader-pick").html(oldName);
				$("#licenseImgP").show();
				$("#img1").attr("onclick","preview(\""+newFileName+"\",this)");
				$("#file1Gif").hide();
			}
		}else{
			$HXT.showInfo("上传异常");
		}
	}
	function upload_call_back2(response){
		if(response.code == '1'){
			var fileList = response.list;
			if(fileList.length == 1){
				var newFileName = fileList[0].newName;
				var oldName = fileList[0].oldName;
				$("#com_tax_img").val(newFileName);
				$("#img2").attr('src',$("#projectPath").val()+newFileName);
				$("#file2 > .webuploader-pick").html(oldName);
				$("#taxImgDivP").show();
				$("#img2").attr("onclick","preview(\""+newFileName+"\",this)");
				$("#file2Gif").hide();
			}
		}else{
			$HXT.showInfo("上传异常");
		}
	}
	function upload_call_back3(response){
		if(response.code == '1'){
			var fileList = response.list;
			if(fileList.length == 1){
				var newFileName = fileList[0].newName;
				var oldName = fileList[0].oldName;
				$("#com_dept_img").val(newFileName);
				$("#img3").attr('src',$("#projectPath").val()+newFileName);
				$("#file3 > .webuploader-pick").html(oldName);
				$("#deptImgDivP").show();
				$("#img3").attr("onclick","preview(\""+newFileName+"\",this)");
				$("#file3Gif").hide();
			}
		}else{
			$HXT.showInfo("上传异常");
		}
	}
	function sub(){
		var id = getVal("mId");
		var user_id = "${admin_user_id}";
		
		var name = $("#name").val();
		var m_province_code = $("#m_province_code").val();
		var m_city_code = $("#m_city_code").val();
		var m_area_code = $("#m_area_code").val();
		var com_address = $("#com_address").val();
		var com_zip_code = $("#com_zip_code").val();
		var com_business_doc_type = $("#com_business_doc_type").val();
		var credit_code = $("#credit_code").val();
		var com_license_no = $("#com_license_no").val();
		var com_license_img = $("#com_license_img").val();
		var com_license_start = $("#com_license_start").val();
		var com_license_end = $("#com_license_end").val();
		var com_tax_no = $("#com_tax_no").val();
		var com_tax_img = $("#com_tax_img").val();
		var com_dept_code = $("#com_dept_code").val();
		var com_dept_img = $("#com_dept_img").val();
		if(!name){
			$HXT.showInfo('请填写单位名称');
			return;
		}
		if(!m_province_code){
			$HXT.showInfo('请选择省份');
			return;
		}
		if(!m_city_code){
			$HXT.showInfo('请选择城市');
			return;
		}
		if(!m_area_code){
			$HXT.showInfo('请选择地区');
			return;
		}
		if(!com_address){
			$HXT.showInfo('请填写详细营业地址');
			return;
		}
		if(!com_zip_code){
			$HXT.showInfo('请填写营业地址邮政编码');
			return;
		}else if(com_zip_code.length!=6){
			$HXT.showInfo('营业地址邮政编码格式不正确');
			return;
		}
		if(!com_business_doc_type){
			$HXT.showInfo('请选择证件类型');
			return;
		}
		if(!com_license_img){
			$HXT.showInfo('请上传营业执照副本');
			return;
		}
		if(!com_license_start){
			$HXT.showInfo('请填写营业执照有效期开始日期');
			return;
		}
		if(!com_license_end){
			$HXT.showInfo('请填写营业执照有效期结束日期');
			return;
		}
		var com_business_doc_type = $("#com_business_doc_type").val();
		if(com_business_doc_type==2){
			if(!com_license_no){
				$HXT.showInfo('请填写营业执照号码');
				return;
			}
			if(!com_tax_no){
				$HXT.showInfo('请填写税务登记证号码');
				return;
			}
			if(!com_tax_img){
				$HXT.showInfo('请上传税务登记证照片');
				return;
			}
			if(!com_dept_code){
				$HXT.showInfo('请填写组织机构代码证号');
				return;
			}
			if(!com_dept_img){
				$HXT.showInfo('请上传组织机构代码证照片');
				return;
			}
		}else{
			if(!credit_code){
				$HXT.showInfo('请填写企业统一社会信用代码');
				return;
			}
		}
		if(id == ''){
	        $.post("<c:url value='/hCompany/addHCompany'/>",
	        	{
	        		user_id:user_id,
	        		name:	name,
	        		province_code:	m_province_code,
	        		city_code:	m_city_code,
	        		area_code:	m_area_code,
	        		com_address:	com_address,
	        		com_zip_code:	com_zip_code,
	        		com_business_doc_type:	com_business_doc_type,
	        		credit_code:	credit_code,
	        		com_license_no:	com_license_no,
	        		com_license_img:	com_license_img,
	        		com_license_start:	com_license_start,
	        		com_license_end:	com_license_end,
	        		com_tax_no:	com_tax_no,
	        		com_tax_img:	com_tax_img,
	        		com_dept_code:	com_dept_code,
	        		com_dept_img:	com_dept_img,
	        		agentOneOpenId:	'${company.oneAgentOpenId}',
	        		agentOneName:	'${company.oneAgentName}',
	        		agentTwoOpenId:	'${company.twoAgentOpenID}',
	        		agentTwoName:	'${company.twoAgentName}',
	        		servicerId:	'${company.servicerId}',
	        		servicerName:	'${company.servicerName}',
				 _t:Math.random()},
	        	function(data){
		        	var result = eval('('+data+')'); 
		            if (result.code == '1') {
		            	$HXT.showInfo("保存成功!");
		             } else {
		            	 $HXT.showInfo(result.message);
		             }
	        });
    	}else{
	        $.post("<c:url value='/hCompany/updateHCompany'/>",
	        	{
		    		id : id,
		    		name:	name,
	        		province_code:	m_province_code,
	        		city_code:	m_city_code,
	        		area_code:	m_area_code,
	        		com_address:	com_address,
	        		com_zip_code:	com_zip_code,
	        		com_business_doc_type:	com_business_doc_type,
	        		credit_code:	credit_code,
	        		com_license_no:	com_license_no,
	        		com_license_img:	com_license_img,
	        		com_license_start:	com_license_start,
	        		com_license_end:	com_license_end,
	        		com_tax_no:	com_tax_no,
	        		com_tax_img:	com_tax_img,
	        		com_dept_code:	com_dept_code,
	        		com_dept_img:	com_dept_img,
	        		agentOneOpenId:	'${company.oneAgentOpenId}',
	        		agentOneName:	'${company.oneAgentName}',
	        		agentTwoOpenId:	'${company.twoAgentOpenID}',
	        		agentTwoName:	'${company.twoAgentName}',
	        		servicerId:	'${company.servicerId}',
	        		servicerName:	'${company.servicerName}',
				 _t:Math.random()},
	        	function(data){
		        	var result = eval('('+data+')'); 
		            if (result.code == '1') {
		            	$HXT.showInfo("更新成功!");
		             } else {
		            	 $HXT.showInfo(result.message);
		             }
	        });
    	}
    	
	}
	
	function goNext(){
		var id = getVal("mId");
		var name = $("#name").val();
		var m_province_code = $("#m_province_code").val();
		var m_city_code = $("#m_city_code").val();
		var m_area_code = $("#m_area_code").val();
		var com_address = $("#com_address").val();
		var com_zip_code = $("#com_zip_code").val();
		if(!name){
			$HXT.showInfo('请填写单位名称');
			return;
		}
		if(!m_province_code){
			$HXT.showInfo('请选择省份');
			return;
		}
		if(!m_city_code){
			$HXT.showInfo('请选择城市');
			return;
		}
		if(!m_area_code){
			$HXT.showInfo('请选择地区');
			return;
		}
		if(!com_address){
			$HXT.showInfo('请填写详细营业地址');
			return;
		}
		
		var reg = /^\d{6}$/;
		if(!com_zip_code){
			$HXT.showInfo('请填写营业地址邮政编码');
			return;
		}else if(!reg.test(com_zip_code)){
			$HXT.showInfo('营业地址邮政编码格式不正确');
			return;
		}
		$.post("<c:url value='/hCompany/updateHCompany'/>",
	        	{
		    		id : id,
		    		name:	name,
	        		province_code:	m_province_code,
	        		city_code:	m_city_code,
	        		area_code:	m_area_code,
	        		com_address:	com_address,
	        		com_zip_code:	com_zip_code,
	        		agentOneOpenId:	'${company.oneAgentOpenId}',
	        		agentOneName:	'${company.oneAgentName}',
	        		agentTwoOpenId:	'${company.twoAgentOpenID}',
	        		agentTwoName:	'${company.twoAgentName}',
	        		servicerId:	'${company.servicerId}',
	        		servicerName:	'${company.servicerName}',
				 _t:Math.random()},
	        	function(data){
		        	var result = eval('('+data+')'); 
		            if (result.code == '1') {
// 		            	$HXT.showInfo("更新成功!");
		            	$("#content1").hide();
		        		$("#content2").show();
		             } else {
// 		            	 $HXT.showInfo(result.message);
		             }
	        });
	}
	function sysalert(msg){
		$("#msg").html(msg);
		$(".weui_dialog_alert").show();
	}
	function closeAlert(){
		$(".weui_dialog_alert").hide();
	}
	
	function selZ(val){
		if(val==2){
			$("#taxDiv").show();
			$("#taxImgDiv").show();
			$("#deptDiv").show();
			$("#deptImgDiv").show();
			$("#credit_codeDiv").show();
			
			var com_tax_img = '${company.com_tax_img }';
			if(com_tax_img!=""){
				$("#taxImgDivP").show();
			}
			
			var com_dept_img = '${company.com_dept_img }';
			if(com_dept_img!=""){
				$("#deptImgDivP").show();
			}
			
			$("#license_noDiv").show();
			$("#credit_codeDiv").hide();
		}else{
			$("#taxDiv").hide();
			$("#taxImgDiv").hide();
			$("#deptDiv").hide();
			$("#deptImgDiv").hide();
			$("#credit_codeDiv").show();
			$("#taxImgDivP").hide();
			$("#deptImgDivP").hide();
			$("#license_noDiv").hide();
		}
	}
	
	function closePreview(){
		$(".preview").hide();
	}
	
	function preview(newFileName,obj){
		//获取图片的宽和高
		var image = new Image();
	    image.src = obj.src;
	    var naturalWidth = image.width;
	    var naturalHight = image.height;
		$(".preview").find("img").attr('src',$("#projectPath").val()+newFileName);
		$(".preview").find("img").attr('width',naturalWidth);
		$(".preview").find("img").attr('height',naturalHight);
		$(".preview").show();
	}
</script>
</body>
</html>
