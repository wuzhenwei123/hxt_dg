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
    <title>手机缴费</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/h5/style.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/h5/comm.css">
    <link href="${ctx}/css/weui.css" rel="stylesheet" type="text/css" />
    <script src="${ctx}/public/custom/public/js/jquery.1.7.2.min.js"></script>
    <script src="${ctx}/js/area_public.js"></script>
</head>
<body class="body">

<div class="box">
    <div class="topBox"><a class=" offen_back" href="javascript:goback();"></a>手机缴费</div>
    
    <div id="mob_jf_info_box_id" class="mob_jf_info_box">
    	<div class="mob_jf">您已开通手机缴费业务，以下是您开通信息：</div>
    	<div class="mob_jy_big_title">您绑定了<font>${ammeterCount1 }</font>个缴费号，缴费金额共计：<font>${all_total_fee}</font>元</div>
        <c:if test="${not empty yu}"><p class="mob_jy_rem">其中您的缴费号：${yu}，目前的欠费是预收电费，共计：${yufeeTotal}元</p></c:if>
		
        <div class="itemsContentBox m_jf_btn">
            <a id="" class="btn btn_primary fl " href="javascript:add();">新增电表分组</a>
            <a id="" class="btn btn_primary fr " href="javascript:generatePayOrder('${all_total_fee}','','${admin_user.companyId}','1');">全部使用手机缴费</a>
            <span class="clear"></span>
    	</div>
    </div>
    
    <div id="m_content_boxmin">
    <c:forEach items="${resultList}" var="list">
    	<div class="order_list_box marginTop16">
	        <div class="ui_cell ui_cells on">
	            <div class="ui_center_left fl_title"><span class="">分组名称：</span></div>
	            <div class="ui_center ui_cell_flex le">
	                	${list.sub_name}
	            </div>
	        </div>
	        
	        <div class="ui_cell ui_cells">
	            <div class="ui_center_left ">
	                <i class="order_icon"></i>
	            </div>
	            <div class="ui_center ui_cell_flex le">
	                <div class="or_tit_box"><span class="tit">收件人姓名:</span><span>${list.consignee}</span><span class="fr">${list.consignee_phone}</span><span class="clear"></span></div>
	                <div class="or_tit_box on"><span class="tit">发票收件地址:</span><span>${list.province_name}${list.city_name}${list.area_name}${list.consignee_address}</span></div>
	            </div>
	        </div>
	        
	        <div class="itemsContentBox m_jf_btn m_jf_btn_box">
	            <a id="" class="btn btn_primary fl delGroup" href="javascript:showSZ(${list.s_id});">删除分组</a>
	            <a id="" class="btn btn_primary fr " href="javascript:updateGroup(${list.s_id});">变更收件信息</a>
	            <span class="clear"></span>                                            
	    	</div>
	        <table class="m_tab_list" cellpadding="0" cellspacing="0" border="0" width="100%">
	        	<tr>
	            	<th>缴费号</th>
	                <th width="35%">客户名称</th>
	                <th>欠费金额</th>
	            </tr>
	            <c:forEach items="${list.ammeters}" var="ammeter">
	            	<tr onclick="electricShow('${ammeter.ammeter_no}','${list.s_id}')">
		            	<td align="center">${ammeter.ammeter_no}</td>
		                <td align="left">${ammeter.ammeter_name}</td>
		                <td align="center"><font>${ammeter.now_totalFee}</font></td>
		            </tr>
	            </c:forEach>
	        </table>
	        
	        <div class="itemsContentBox m_jf_btn m_jf_btn_box">
	            <input type="text" class="fl m_index_input" id="ammeterNum_bind_${list.s_id}"/>
	            <a id="" class="btn btn_primary fr " href="javascript:bind('${list.s_id}','${list.c_id}');">设为手机缴费	</a>
	            <span class="clear"></span>                                            
	    	</div>
	        
	        <div class="ch_ui_box gr jf_btn_m">
	        	<div class="ui_cell ui_cells">
	                
	                <div class="ui_center ui_cell_flex">
	                    
	                    
	                    <p>应交金额小计：<font>${list.totalFeeStr}</font>元</p>
	                    <p>共<font>${list.ammeterCount}</font>个缴费号</p>
	                    
	                </div>
	                <a class="ui_center_left jf_btn_m sj_zf_btn"  data-info='{"num":263586.20}' href="javascript:generatePayOrder('${list.totalFeeStr}','${list.s_id}','${list.c_id}','2')">手机缴费</a>
	        	</div>
	        </div>
	        
	        
	        
	        
	        
	    </div>
    </c:forEach>
   </div> 
</div>
<div class="weui_dialog_confirm" style="display: none;">
    <div class="weui_mask"></div>
    <div class="weui_dialog">
        <div class="weui_dialog_hd">
          <strong class="weui_dialog_title">您确要删除吗？</strong>
        </div>
        <div class="weui_dialog_ft">
            <a href="javascript:;" class="weui_btn_dialog default" onclick="closeConfirm()">取消</a>
            <a href="javascript:;" class="weui_btn_dialog primary" onclick="delSubcompany()">确定</a>
        </div>
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
        <p class="weui_toast_content">数据加载中...</p>
    </div>
</div>
<script>
// $(function(){
// 	setfixed();
// 	/*注册鼠标滚动事件 */
// 	window.onscroll = function(){
// 		setfixed();
// 	}
// })

function setfixed(){
	var t = document.documentElement.scrollTop || document.body.scrollTop;
	var dis = 40;
	var obj = document.getElementById("mob_jf_info_box_id");
	var main = document.getElementById("m_content_boxmin");
	var mainTop = obj.offsetHeight + 50;
	if( t > dis){
		$(obj).addClass("m_jf_info_box");
		main.style.paddingTop = mainTop + "px";
		main.style.zIndex = 1;
	} else {
		$(obj).removeClass("m_jf_info_box");
		main.style.paddingTop = 0 + "px";
	
	}	
}
function add(){
	$("#loadingToast").show();
	window.location.href = "${ctx}/H5/toAddGroup";
}
var sid = "";
function delSubcompany(){
	$(".weui_dialog_confirm").hide();
	if(sid==""){
		$HXT.wxAlert("请选择您要删除的分组");
	}else{
		var url = '${ctx}/ammetermanager/delSubcompany';
		$("#loadingToast").show();
		$.ajax({
			type: "POST",
			url: url,
			data: {sid: sid},
			dataType: "json",
			success: function (data) {
				if (data.status == 'success') {
					window.location.reload();
				} else {
					$HXT.wxAlert(data.msg);
				}
			}
		});
	}
}

function showSZ(s_id){
	sid = s_id;
	$(".weui_dialog_confirm").show();
}

function closeConfirm(){
	sid = "";
	$(".weui_dialog_confirm").hide();
}

//绑定弹出
function bind(id,cid) {
    var ammetertNo =$("#ammeterNum_bind_"+id).val();
    if (isEmpty(ammetertNo)) {
        $HXT.wxAlert("缴费号不能为空！");
        return;
    }
    var reg = /^[\d.\-_]+$/;
	if(!reg.test(ammetertNo)){
		$HXT.wxAlert("请您输入正确的缴费号");
		return false;
	}
    var url = '${ctx}/hCommon/getAmmeterInfo?electricNum=' + ammetertNo;
    $.ajax({
        type: "GET",
        url: url,
        dataType: "json",
        success: function (data) {
            if (data.status == 'success') {
                if (eval(data.data).resultCode == '00') {//电表存在
               		var accountName=data.data.resultInfo.accountName;
               		var address=data.data.resultInfo.address;
               		var accountFee=data.data.resultInfo.accountFee;
               		var lateFee=data.data.resultInfo.lateFee;
                    window.location.href="${ctx}/H5/toElectricInfo?accountName="+accountName+"&cid="+cid+"&sid="+id+"&address="+address+"&accountFee="+accountFee+"&lateFee="+lateFee+"&totalFee="+data.data.totalFee+"&electricNum="+ammetertNo;
                } else {
                    $HXT.wxAlert("该电表不存在，请检查重新输入！");
                }
            } else {
                $HXT.wxAlert(data.msg);
            }
        }
    });
}
function isEmpty(s) {
    return !Boolean(s.replace(/^\s*|\s*$/g, "").length);
}
function generatePayOrder(totalFeeStr,subId,cId,payType){
	var tot = totalFeeStr.replace("\.","");
    if(totalFeeStr=='0.00'){
    	if(payType=="1"){
        	$HXT.wxAlert("您登记的所有电表都没有欠费，感谢使用");
    	}else{
       	 	$HXT.wxAlert("该分组登记的所有电表缴费，没有欠费，感谢使用。");
    	}
        return false;
    }else if(parseInt(tot,10)>5000000){
    	  $HXT.wxAlert("为了保障您的账户安全,本服务不支持5万以上金额的交易,请您用电脑登录qiye.chinaepay.com,在网站上使用企业网银缴费服务。");
          return false;
    }
    //查看用户状态
    var url1 = '${ctx}/H5/checkLoginState';
   	$.ajax({
   		type: "POST",
   		url: url1,
   		dataType: "json",
   		success: function (result) {
   			if (result.code == '1') {
   				var url = '${ctx}/pay/checkPayTime';
   	   		   	$.ajax({
   	   		   		type: "GET",
   	   		   		url: url,
   	   		   		dataType: "json",
   	   		   		success: function (data) {
   	   		   			if (data.status == 'success') {
   	   		   				var url = '${ctx}/H5/toPayInfo';
   	   		   				
   	   		   				$("#loadingToast").show();
   	   		   				var formStr = '<form method="get" action="' + url + '">';
   	   		   				formStr += '<input type="hidden" value="'+cId+'" name="cId"/>';
   	   		   				formStr += '<input type="hidden" value="'+subId+'" name="subId"/>';
   	   		   				formStr += '<input type="hidden" value="'+payType+'" name="payType"/>';
   	   		   				formStr += '<input type="hidden" value="'+totalFeeStr+'" name="totalFeeStr"/>';
   	   		   				formStr += '</form>';
   	   		   				var $tempForm = $(formStr);
   	   		   				$("body").append($tempForm);  
   	   		   				$tempForm.submit();  
   	   		   				$tempForm.remove();  
   	   		   			} else {
   	   		  				$HXT.wxAlert(data.msg);
   	   		   			}
   	   		   		}
   	   		   	});
   			}else{
   				$HXT.wxAlert(result.message);
   			}
   		}
   	})
    return true;
}

function updateGroup(subId){
	$("#loadingToast").show();
	window.location.href = "${ctx}/H5/toUpdateGroup?subId="+subId;
}

function electricShow(ammeter_no,subId){
	$("#loadingToast").show();
	window.location.href = "${ctx}/H5/electricShow?subId="+subId+"&ammeter_no="+ammeter_no;
}
function goback(){
	window.location.href = "${ctx}/H5/toIndex";
}
</script>
</body>
</html>
