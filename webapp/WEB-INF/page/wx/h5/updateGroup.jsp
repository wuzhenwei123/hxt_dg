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
    <title>变更分组信息</title>
    <link rel="stylesheet" type="text/css" href="${ctx }/public/custom/public/css/customForm.css">
    <link href="${ctx }/js/wx/city/css/mobiscroll.scroller.css" rel="stylesheet" />
    <link href="${ctx }/js/wx/city/css/mobiscroll.scroller.android-ics.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/h5/style.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/h5/comm.css">
    <link href="${ctx}/css/weui.css" rel="stylesheet" type="text/css" />
    <script src="${ctx}/public/custom/public/js/jquery.1.7.2.min.js"></script>
	<script src="${ctx}/js/wx/city/js/mobiscroll.zepto.js" type="text/javascript"></script>
	<script src="${ctx}/js/wx/city/js/mobiscroll.core.js" type="text/javascript"></script>
	<script src="${ctx}/js/wx/city/js/mobiscroll.scroller.js" type="text/javascript"></script>
	<script src="${ctx}/js/wx/city/js/mobiscroll.area.js"></script>
	<script src="${ctx}/js/wx/city/js/mobiscroll.scroller.android-ics.js"></script>
	<script src="${ctx}/js/wx/city/js/i18n/mobiscroll.i18n.zh.js"></script>
	<script src="${ctx}/js/area_public.js"></script>
</head>
<body>
<!-- <input type="hidden" value="${m_sid}" id="m_sid"> -->
<!-- <input type="hidden" id="provincecode_id" value=""/> -->
<!-- <input type="hidden" id="citycode_id" value=""/> -->
<!-- <input type="hidden" id="districtcode_id" value=""/> -->
<div class="box">
    <div class="topBox"><a class=" offen_back" href="javascript:history.go(-1);"></a>变更分组信息</div>
    <div class="body body_black"></div>
    <div class="ch_ui_box accItems gr gr_ch">
        <div class="ui_cell ui_cells">
<!--             <div class="ui_center_left fl_title"><span class="">分组名称：</span></div> -->
            <div class="ui_center_left"><font class="redColor">*</font>单位名称：</div>
            <div class="ui_center ui_cell_flex">
            	<div class="textarea" id="m_sub_name">${hSubCompany.sub_name}</div>
                <font class="accJiaojian"></font>
            </div>
        </div>
        <div class="ui_cell ui_cells">
            <div class="ui_center_left "><font class="redColor">*</font>发票收件人：</div>
            <div class="ui_center ui_cell_flex">
            	<input id="m_consignee" class="offen_text offen_ui_text" type="text" value="${hSubCompany.consignee}">
            </div>
        </div>
        <div class="ui_cell ui_cells">
            <div class="ui_center_left "><font class="redColor">*</font>收件人手机号码：</div>
            <div class="ui_center ui_cell_flex">
            	<input id="m_consignee_phone" class="offen_text offen_ui_text" type="text" value="${hSubCompany.consignee_phone}">
            </div>
        </div>
        <div class="ui_cell ui_cells">
            <div class="ui_center_left "><font class="redColor">*</font>邮政编码：</div>
            <div class="ui_center ui_cell_flex">
            	<input id="m_zip_code" class="offen_text offen_ui_text" type="text" value="${hSubCompany.zip_code}">
            </div>
        </div>
        <div class="ui_cell ui_cells">
            <div class="ui_center_left "><font class="redColor">*</font>所在区域：</div>
            <div class="ui_center ui_cell_flex" id="areaDiv">
            	<input id="area" name="area" areaid="10060 10019 10019" class="offen_text offen_ui_text" type="text" value="" placeholder="请选择所在地区">
                
            </div>
        </div>
        
        <div class="ui_cell ui_cells">
            <div class="ui_center_left fl_title"><span><font class="redColor">*</font>详细地址：</span></div>
            <div class="ui_center ui_cell_flex">
            	<div class="textarea" id="m_consignee_address">${hSubCompany.consignee_address}</div>
                <font class="accJiaojian"></font>
            </div>
        </div>                                                   
    </div>
    <div class="itemsContentBox on">
    <a id="OkBtnBox" class="btn btn_primary" href="javascript:add();">保存</a>
    </div>
</div>
<div id="loadingToast" class="weui_loading_toast" style="display: none;">
    <div class="weui_mask_transparent"></div>
    <div class="weui_toast">
        <div class="weui_loading">
            <div class="weui_loading_leaf weui_loading_leaf_0"></div>
            <div class="weui_loading_leaf weui_loading_leaf_1"></div>
            <div class="weui_loading_leaf weui_loading_leaf_2"></div>
            <div class="weui_loading_leaf weui_loading_leaf_3"></div>
            <div class="weui_loading_leaf weui_loading_leaf_4"></div>
            <div class="weui_loading_leaf weui_loading_leaf_5"></div>
            <div class="weui_loading_leaf weui_loading_leaf_6"></div>
            <div class="weui_loading_leaf weui_loading_leaf_7"></div>
            <div class="weui_loading_leaf weui_loading_leaf_8"></div>
            <div class="weui_loading_leaf weui_loading_leaf_9"></div>
            <div class="weui_loading_leaf weui_loading_leaf_10"></div>
            <div class="weui_loading_leaf weui_loading_leaf_11"></div>
        </div>
        <p class="weui_toast_content">保存中...</p>
    </div>
</div>
<script type="text/javascript">
	$(function(){
		var u_province_code = '${hSubCompany.province_code}';
		var u_city_code = '${hSubCompany.city_code}';
		var u_area_code = '${hSubCompany.area_code}';
		var ar_name = "";
		var area_provincecode_id = "";
		for(var i =0 ;i<AddressInfo.Provinces.length;i++){
			if(AddressInfo.Provinces[i]._provincecode==u_province_code){
				area_provincecode_id = AddressInfo.Provinces[i]._proviceid;
				ar_name = AddressInfo.Provinces[i]._provicename;
				break;
			}
		}
		
		var area_citycode_id = "";
		if(area_provincecode_id!=""){
			for(var i =0 ;i<AddressInfo.Cities[area_provincecode_id].length;i++){
				if(AddressInfo.Cities[area_provincecode_id][i]._citycode==u_city_code){
					area_citycode_id = AddressInfo.Cities[area_provincecode_id][i]._cityid;
					ar_name = ar_name + " " +AddressInfo.Cities[area_provincecode_id][i]._cityname
					break;
				}
			}
		}
		
		var area_districtcode_id = "";
		if(area_citycode_id!=""){
			for(var i =0 ;i<AddressInfo.Districts[area_citycode_id].length;i++){
				if(AddressInfo.Districts[area_citycode_id][i]._districtcode==u_area_code){
					area_districtcode_id = AddressInfo.Districts[area_citycode_id][i]._districtid;
					ar_name = ar_name + " " +AddressInfo.Districts[area_citycode_id][i]._districtname
					break;
				}
			}
		}
		
		var html = "";
		html += '<input type="hidden" name="area_provincecode_id" value="'+area_provincecode_id+'"/>';
		html += '<input type="hidden" name="area_citycode_id" value="'+area_citycode_id+'"/>';
		html += '<input type="hidden" name="area_districtcode_id" value="'+area_districtcode_id+'"/>';
		$("#areaDiv").append(html);
		$("#area").attr("areaid",area_provincecode_id+" "+area_citycode_id+" "+area_districtcode_id);
		$("#area").val(ar_name);
		$('#area').scroller('destroy').scroller({ preset: 'area', theme: 'android-ics light', display: 'bottom',valueo:area_provincecode_id+" "+area_citycode_id+" "+area_districtcode_id });
	})
// 	var valo = $("#area").attr("areaid");
	
	function add(){
		if(!$("#m_sub_name").html()){
			$HXT.wxAlert('请填写单位名称！');
			return;
		}
		if(!$("#m_consignee").val()){
			$HXT.wxAlert('请填写收件人！');
			return;
		}
		if(!$("#m_consignee_phone").val()){
			$HXT.wxAlert('请填写收件人手机号！');
			return;
		}
		reg = /^1\d{10}$/;
	    if(!reg.test($("#m_consignee_phone").val())){
	    	$HXT.wxAlert('收件人手机号码不正确');
	    	return;
	    }
	    
	    if(!$("#m_zip_code").val()){
			$HXT.wxAlert('请填写邮编！');
			return;
		}
		var reg = /^\d{6}$/;
		if(!reg.test($("#m_zip_code").val())){
		 	$HXT.wxAlert('邮编不正确');
		  	return;
		}
		
		var area_provincecode_id = $("input[name='area_provincecode_id']").val();
		var area_citycode_id = $("input[name='area_citycode_id']").val();
		var area_districtcode_id = $("input[name='area_districtcode_id']").val();
// 		var area_provincecode_id = $("#provincecode_id']").val();
// 		var area_citycode_id = $("#citycode_id").val();
// 		var area_districtcode_id = $("#districtcode_id").val();

		if(!area_provincecode_id){
			$HXT.wxAlert('请选择所在区域！');
			return;
		}
		if(!area_citycode_id){
			$HXT.wxAlert('请选择所在区域！');
			return;
		}
		if(!area_districtcode_id){
			$HXT.wxAlert('请选择所在区域！');
			return;
		}
		var m_province_code = "";
		for(var i =0 ;i<AddressInfo.Provinces.length;i++){
			if(AddressInfo.Provinces[i]._proviceid==area_provincecode_id){
				m_province_code = AddressInfo.Provinces[i]._provincecode;
				break;
			}
		}
		var m_city_code = "";
		for(var i =0 ;i<AddressInfo.Cities[area_provincecode_id].length;i++){
			if(AddressInfo.Cities[area_provincecode_id][i]._cityid==area_citycode_id){
				m_city_code = AddressInfo.Cities[area_provincecode_id][i]._citycode;
				break;
			}
		}
		var m_area_code = "";
		for(var i =0 ;i<AddressInfo.Districts[area_citycode_id].length;i++){
			if(AddressInfo.Districts[area_citycode_id][i]._districtid==area_districtcode_id){
				m_area_code = AddressInfo.Districts[area_citycode_id][i]._districtcode;
				break;
			}
		}
	    
		if(!$("#m_consignee_address").html()){
			$HXT.wxAlert('请填写详细地址！');
			return;
		}
		var m_sid = '${hSubCompany.s_id}';
		var jsonParam = {
				province_code:m_province_code,
				city_code:m_city_code,
				area_code:m_area_code,
				s_id : m_sid,
				sub_name:$("#m_sub_name").html(),
				consignee:$("#m_consignee").val(),
				consignee_phone:$("#m_consignee_phone").val(),
				consignee_address:$("#m_consignee_address").html(),
				zip_code:$("#m_zip_code").val()
		}
	    var url = '${ctx}/H5/saveHSubCompany';
	    $("#loadingToast").show();
	    $.ajax({
	        type: "POST",
	        url: url,
	        data:jsonParam,
	        dataType: "json",
	        success: function (data) {
	            if (data.status == 'success') {
	            	window.location.href = "${ctx}/H5/toPay";
	            } else {
	            	$("#loadingToast").hide();
	                $HXT.wxAlert(data.msg);
	                return false;
	            }
	        }
	    });
	}
	
	
</script>

</body>
</html>
