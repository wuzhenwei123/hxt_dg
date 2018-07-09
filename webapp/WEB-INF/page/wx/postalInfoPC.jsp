<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<html>
	<head>
    <meta charset="utf-8">
    <meta name="author" content="www.999care.com">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="#e8447e">
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="Expires" CONTENT="-1">           
    <meta http-equiv="Cache-Control" CONTENT="no-cache">           
    <meta http-equiv="Pragma" CONTENT="no-cache">
    <meta name="description" content="">
    <meta name="Keywords" content="">
    <title>企业代理提现信息管理</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/customForm.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/style.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/comm.css">
    <link href="${ctx}/css/weui.css" rel="stylesheet" type="text/css" />
    <script src="${ctx}/public/custom/public/js/jquery.1.7.2.min.js"></script>
    <script src="${ctx}/public/js/selectBank.js"></script>
    <script src="${ctx}/js/area_public.js"></script>
    <style>
    	html,body,.box{
			height:100%;
		}
    </style>
</head>
<body class="body">
<div class="box">
    <div class="topBox"><a class=" offen_back" href="javascript:history.go(-1);"></a>企业代理提现信息管理</div>
    
    
    <div class="ch_ui_box marginTop16 accItems tx">
        <div class="ui_cell ui_cells">
            <div class="ui_center_left ">公司名称</div>
            <div class="ui_center ui_cell_flex">
                <input id="name" class="offen_text offen_ui_text" type="text" value="${hAgentTwo.name}"  placeholder="公司名称请选择公司名称" />
            </div>
        </div>
        <div class="ui_cell ui_cells">
            <div class="ui_center_left ">联系人姓名</div>
            <div class="ui_center ui_cell_flex">
                <input id="contact_person" class="offen_text offen_ui_text" type="text" value="${hAgentTwo.contact_person}" placeholder="请填写联系人姓名" />
            </div>
        </div>
         <div class="ui_cell ui_cells">
            <div class="ui_center_left ">联系人电话</div>
            <div class="ui_center ui_cell_flex">
                <input id="mobile1" class="offen_text offen_ui_text" type="text" value="${hAgentTwo.mobile1}" onkeyup="this.value=this.value.replace(/\D/gi,'')" placeholder="请填写联系电话" />
            </div>
        </div>
        <div class="ui_cell ui_cells" id="show_sel_bank_box">
            <div class="ui_center_left ">开户银行</div>
            <div class="ui_center ui_cell_flex">
                <span class="yhName"><c:if test="${empty hAgentTwo.bank_name}">请选择银行</c:if><c:if test="${not empty hAgentTwo.bank_name}">${hAgentTwo.bank_name}</c:if></span>
                <input type="hidden" id="bankId" value="${hAgentTwo.bankId}">
            </div>
            <div class="ui_cell_link on"></div>
        </div>
        <div class="ui_cell ui_cells">
            <div class="ui_center_left ">开户行行号</div>
            <div class="ui_center ui_cell_flex">
               <input id="bank_number" class="offen_text offen_ui_text" type="text" value="${hAgentTwo.bank_number}" placeholder="请填写开户行行号" />
            </div>
            <div class="ui_cell_link hh"></div>
        </div>
        <div class="ui_cell ui_cells">
            <div class="ui_center_left ">开户行支行</div>
            <div class="ui_center ui_cell_flex">
                <input id="subBankName" class="offen_text offen_ui_text" type="text" value="${hAgentTwo.subBankName}" placeholder="请填写开户行支行" />
            </div>
        </div>
        <div class="ui_cell ui_cells">
            <div class="ui_center_left ">银行账号</div>
            <div class="ui_center ui_cell_flex">
                <input id="bank_number" class="offen_text offen_ui_text" type="text" value="" placeholder="请铁写银行账号" onkeyup="this.value=this.value.replace(/\D/gi,'')"/>
            </div>
        </div>
   </div>
   <div class="itemsContentBox on">
    <a id="OkBtnBox" class="btn btn_primary" href="javascript:void(0);" onclick="save()">保存</a>
</div>
</div>
<div class="selBoxYn">
	<div class="bankListBox">
    	<div class="blackBg"></div>
    	<div class="bankListBox">
        	
        	<div class="bankTxtBox">
            <div class="sel_banl_t_box">
                <div class="sel_bank_title"><span>选择银行</span><a id="cl_sel_bank" class="cl_sel_bank" href="javascript:void(0);"></a></div>
                <div id="sel_bank_search_box" class="sel_bank_search_box"><i>搜索</i><input id="searchBankInput" class="" type="text" oninput="loadData(this.value)"/></div>
             </div>
            	<ul class="bankUlBox">
                	
                    
                </ul>
            </div>
        </div>
    </div>
</div> 



<script>
function showInfo(){
	$HXT.showInfo('开户行号请咨询您的开户银行');
}

/**
 * 载入内容
 * @return {[type]} [description]
 */
function loadData(name) {
	$.get("${ctx}/hBank/getHBankList?name="+name+"&_t="+Math.random(),function(data,status){
		$(".bankUlBox").empty();
		var result = eval("("+data+")");
        if (result.code == 1) {
        	var tableStr = "";
        	for (var k=0; k<result.items.length; k++){
        		tableStr += '<li class="ch_ui_box bankItem">';
        		tableStr += '	<div class="ui_cell ui_cells">';
        		tableStr += '		<label class="ui_line_label ui_checked">';
        		tableStr += '			<div class="ui_cell_icon_box" style="background-image:url(${ctx}' + result.items[k].smallImg + ');"></div>';
        		tableStr += '			<div class="ui_center ui_cell_flex">' + result.items[k].name + '</div>';
        		tableStr += '			<div class="ui_center_left">';
        		tableStr += '				<input type="radio" checked="checked" name="aaa" class="ui_checkbox" value="' + result.items[k].id + '">';
        		tableStr += '				<i class="redio_icon"></i>';
        		tableStr += '			</div>';
        		tableStr += '		</label>';
        		tableStr += '	</div>';
        		tableStr += '</li>';
        	}
        	$(".bankUlBox").append(tableStr);
        }
	});
}
 
 function save(){
	 
	 var name = $("#name").val();
	 
	 if(name==""){
		 $HXT.showInfo('请输入公司名称');
		 return false;
	 }
	 
	 var contact_person = $("#contact_person").val();
	 
	 if(contact_person==""){
		 $HXT.showInfo('请输入联系人姓名');
		 return false;
	 }
	 
	 var mobile1 = $("#mobile1").val();
	 
	 if(mobile1==""){
		 $HXT.showInfo('请输入联系人电话');
		 return false;
	 }
	 
	 var bankId = $("#bankId").val();
	 if(bankId==""){
		 $HXT.showInfo('请选择开户银行');
		 return false;
	 }
	 
	 var bank_number = $("#bank_number").val();
	 if(bank_number==""){
		 $HXT.showInfo('请输入开户行号');
		 return false;
	 }
	 var subBankName = $("#subBankName").val();
	 if(subBankName==""){
		 $HXT.showInfo('请输入开户支行');
		 return false;
	 }
	 var bank_account = $("#bank_account").val();
	 if(bank_account==""){
		 $HXT.showInfo('请输入银行卡号');
		 return false;
	 }
	 
	 $.post("${ctx}/hAgentTwo/updateBankInfo",
			 {
		 		"bankId":bankId,
		 		"name":name,
		 		"mobile1":mobile1,
		 		"contact_person":contact_person,
		 		"subBankName":subBankName,
		 		"bank_account":bank_account,
		 		"bank_number":bank_number,
		 		"id":'${id}',
		 		"flag":1,
		 		ranNum:Math.random()
			 },
			 function(data){
			var result = eval('('+data+')'); 
            if (result.code == '1') {
        		var style = '${hAgentTwo.style}';
        		if(reslut.message=='mobilenotexit'){
        			$HXT.showInfo('请输入联系人电话');
        		}else if(reslut.message=='ok'){
        			if(style=="1"){//公司
            			$HXT.showInfo('添加成功');
            			window.location.href = "${ctx}/weixin/toPostalInfo3rdPC?id=${hAgentTwo.id}";
            		}else{
            			window.location.href = "${ctx}/weixin/toPostalInfo3rd?id=${hAgentTwo.id}";
            		}
        		}else if(reslut.message=='mobileexit'){
        			$HXT.showInfo('联系人电话已被占用');
        		}
            }else{
            	$HXT.showInfo('系统异常');
            }
		});
 }
</script>
</body>
</html>
