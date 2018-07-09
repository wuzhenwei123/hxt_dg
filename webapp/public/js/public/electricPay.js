$(document).ready(function(){
	getData();
});
function getData() {
    var url = $("#projectPath").val() + '/ammetermanager/getInvoceAmmeterInfoByCId?cId=' + $("#cId").val();
    $.ajax({
        type: "GET",
        url: url,
        dataType: "json",
        success: function (data) {
        	
        	$.get($("#projectPath").val()+"/hBank/getHBankBaseList",function(data1){
        		$("#loadingToast").hide();
        		var result = eval("("+data1+")");
        		if (result.code == 1) {
        			dataCh(data,data1);
        		}
        	});
        }
    });
}
var $fee;
function titleStr(cid,cname,count,fee,nopayammetercount,data1){
	var str = "";
	str += '<div class="commBody jySearchBox bankB">';
	str += '	<div class="backRem">注：点击您所对应的企业网银的银行图标，可以下载《企业网银操作手册》帮助您完成网银缴费。</div>';
	str += '   <ul class=" lineP backUlBox">';
	var result = eval("("+data1+")");
	for (var k=0; k<result.items.length; k++){
		var name = encodeURIComponent(result.items[k].name);
		if(result.items[k].state==1){
			str += '       <li class="lines bankItem"><a href="'+$("#projectPath_admin").val()+'/index/toDownload?link='+result.items[k].docUrl+'&name='+name+'" style="background-image:url('+$("#projectPath").val()+result.items[k].bigImg+');"><i></i></a></li>';
		}else{
			str += '       <li class="lines bankItem"><a href="javascript:void(0);" style="background-image:url('+$("#projectPath").val()+result.items[k].bigImg+');"><i></i></a></li>';
		}
	}
    str += '   </ul>';
	str += '</div>';
	str += '<div class="commBody jyItem">';
	str += '	<div class="titleBox">';
	str += '		<div class="fl jyListItemsLeft jymoney">';                
	str += '			<div><span>'+cname+'：</span></div>';
	str += '			<div><span>截止到 '+$("#nowDate").val()+'，您单位登记的电表共有 '+count+' 个，其中'+nopayammetercount+' 个电表欠费共计：<font>'+fee+'</font> 元。</span></div>';
	str += '			<div><span>请您与申请付款的欠费总金额核对，并在今天21：00前完成付款，超过此时间缴费有可能会产生滞纳金。</span></div>';
	str += '			<div><span>详细的欠费信息如下：</span></div>';
	str += '   	 	</div>';
	str += '		<div class="fr jyListItemsRight jyItemBtn">';
	str += '			<a href="javascript:beforePay(1,null);" class="jyBtn wy">全部用网银交费</a>';
	str += '			<a href="javascript:exportExcle();" class="jyBtn on">导出报表</a>';
	str += '   		</div>';
	str += '		<span class="clear"></span>';
	str += '	</div>';
	str += '</div>';
	return str;
}
//发票信息处理
var dataArr;
function dataCh(myData,data1) {
    // 发票信息 数据处理
    var str = '';
    var obj = eval(myData.data);
    dataArr = eval(myData);
    var ammetercount=0;//电表数
    var nopayammetercount = 0;//欠费电表数
	var flagStr = '';
    for (var i = 0; i < obj.length; i++) {
    	str += '<div class="commBody jyItem">';//外层开始
    	//title
    	str += '<div class="titleBox">';
    	str += '<input type="hidden" value="'+obj[i].s_id+'" class="s_id"/>';
    	str += '<input type="hidden" value="'+obj[i].c_id+'" class="c_id"/>';
    	str += '	<div class="fl jyListItemsLeft">';
    	str += '		<div><span>分组名称：<font> '+obj[i].sub_name+'</font></span></div>';
    	str += '		<div><span>发票收件人：<font>'+ obj[i].consignee +'</font></span><span>收件人手机：<font>'+obj[i].consignee_phone+'</font></span><span>邮政编码：<font>'+obj[i].zip_code+'</font></span></div>';
    	str += '		<div><span>发票收件地址：<font>'+obj[i].consignee_address+'</font></span></div>';
    	str += '	</div>';
    	str += '	<span class="clear"></span>';
    	str += '</div>';
    	//缴费号列表
    	var ammeters = eval(obj[i]).ammeters;//电表信息
        var ammeterNo = ammeters.ammeter_no;//电表号
    	str += '<div class="jyTableBox">';
    	str += '<table class="jyTable" cellpadding="0" cellspacing="0" border="0" width="100%">';
    	str += '<tr><th>序号</th><th>业务类型</th><th>缴费号</th><th>客户名称</th><th>应交金额(元)</th><th>缴费状态</th><th>操作</th></tr>';
    	var ammeter_type = "";
        var pay_statu = '';
        var bt_status = "";
		var num1 = 0;
        for (var j = 0; j < ammeters.length; j++) {
        	ammetercount++;
        	num1=j+1;
        	if (ammeters[j].ammeter_type == 'A') {
                ammeter_type = '抄表电';
            } else if (ammeters[j].ammeter_type == 'B') {
                ammeter_type = '智能表电';
            }
            if (ammeters[j].pay_status == '0') {
                pay_statu = '暂停';
                bt_status = '恢复';

            } else if (ammeters[j].pay_status == '1') {
                pay_statu = '正常';
                bt_status = '暂停';
            }
        	str += '<tr>';
        	str += '<td>'+eval(j + 1)+'</td>';
        	str += '<td>'+ammeter_type+'</td>'; 
        	str += '<td id="ammeterNum_td_'+obj[i].s_id+'">'+eval(ammeters[j]).ammeter_no+'</td>'; 
        	str += '<td>'+eval(ammeters[j]).ammeter_name+' </td>'; 
        	str += '<td>'+truncFee((ammeters[j]).totalFee)+'</td>';
        	if(truncFee((ammeters[j]).totalFee)!='0.00'){nopayammetercount++;}
        	str += '<td>'+pay_statu+'</td>';
        	str += '<td class="yjBtnTdBox"><a href="javascript:bind2(\''+obj[i].s_id+'\',\''+eval(ammeters[j]).ammeter_no+'\');">详情</a></td>';
        	str += '</tr>'
        }
        str += '</table>';
        str += '</div>';
        //小计
        str += '<div class="lineP linePjyAllBox">'
        str += '	<span class="lines">应交金额小计：<font id="sub_total_fee_'+obj[i].s_id+'">'+truncFee(eval(obj[i]).totalFee)+'</font> 元</span>';
        str += '	<a class="lines jyBtn" href="javascript:beforePay(2,'+obj[i].s_id+');">网银直接缴费</a>';
        str += '</div>'
        str += '</div>';//外层结束
    }
    str = titleStr(obj[0].c_id,obj[0].c_name,ammetercount,truncFee(myData.all_total_fee),nopayammetercount,data1)+str;
    $fee = truncFee(myData.all_total_fee);
    $(".main_box").append(str);
}
//电费处理
function truncFee(num){
	if(isNaN(num)){
		return '0.00';
	}else{
		return changeTwoDecimal_f(num/100);
	}
}
//补0
function changeTwoDecimal_f(x) {
	var f_x = parseFloat(x);
	if (isNaN(f_x)) {
		return 0;
	}
	var f_x = Math.round(x * 100) / 100;
	var s_x = f_x.toString();
	var pos_decimal = s_x.indexOf('.');
	if (pos_decimal < 0) {
		pos_decimal = s_x.length;
		s_x += '.';
	}
	while (s_x.length <= pos_decimal + 2) {
		s_x += '0';
	}
	return s_x;
}
function isEmpty(s) {
    return !Boolean(s.replace(/^\s*|\s*$/g, "").length);
}
//详情弹窗
function bind2(id,ammetertNo){
//	var ammetertNo =$("#ammeterNum_td_"+id).html();
//    if (isEmpty(ammetertNo)) {
//        $HXT.showInfo("缴费号不能为空！");
//        return false;
//    }
//    var reg = /^[\d.\-_]+$/;
//	if(!reg.test(ammetertNo)){
//		$HXT.showInfo("请您输入正确的缴费号");
//		return false;
//	}
    var url = $("#projectPath").val() + '/hCommon/getAmmeterInfo?electricNum=' + ammetertNo;
    $.ajax({
        type: "GET",
        url: url,
        dataType: "json",
        success: function (data) {
            if (data.status == 'success') {
                if (eval(data.data).resultCode == '00') {//电表存在
                	showDetail2(data.data,ammetertNo,id);
                } else {
                	$HXT.showInfo("该电表不存在，请检查重新输入！");
                }
            } else {
            	$HXT.showInfo(data.msg);
            }


        }
    });
}
function showDetail2(data,ammetertNo,sid){
	var html = '<div class="wInfoBox_p">';
	html += '<ul class="wInfoBox">';
	html += '<li class="clearFix"><label class="fl">缴费号：</label><span class="fr">'+ammetertNo+'</span></li>';
	html += '<li class="clearFix"><label class="fl">客户名称：</label><span class="fr">'+data.resultInfo.accountName+'</span></li>';
	html += '<li class="clearFix"><label class="fl">用电地址：</label><span class="fr">'+data.resultInfo.address+'</span></li>';
	html += '<li class="clearFix"><label class="fl">账单金额：</label><span class="fr">'+data.resultInfo.accountFee+'</span></li>';
	html += '<li class="clearFix"><label class="fl">滞纳金：</label><span class="fr">'+data.resultInfo.lateFee+'</span></li>';
	html += '<li class="clearFix"><label class="fl">应缴金额：</label><span class="fr">'+data.totalFee+'元</span></li>';
	html += '</ul>';
	html += '</div>';
	var option = {
			title : "电表信息",
			btn : parseInt("0011", 2),
			isOkBtn : true,
			onOk : function() {
			},
//			class : [ 'okAddressBox', 'on' ]
	}
	
	var classArr = ['okAddressBox','on'];
	window.wxc.xcConfirm(html, "custom", option);
	setXcConfirmClass(classArr);
	var isOkBtn = true;
	if(isOkBtn){
		$('.'+classArr[0] + " .popBox .cancel").remove();
	}
}
//获取复核人员信息
function beforePay(payType,subId){
	var url = $("#projectPath").val() + '/hReviewUser/getCompanReviewer';
	$.getJSON(url ,{cid:$("#cId").val()},function(data){
		if(data.code==1){
			pay(data.items,payType,subId);
		}else{
			$HXT.showInfo('系统异常！');
		}
	})
}
function pay(data,payType,subId){
	var title = '重要提示：在完成制单后，请您及时提醒复核员在当天登录网银复核订单，<font>超过当天，可能因为复核超时</font>造成缴费失败！';
	var html = '<div class="wItemBox">';
	html += '<div class="lineP searchItem reviewRadioDiv">';
	html += '<div class="in-radio" style="margin-right:20px;">';
	if(data&&data.msgSwitch=="1"){
		html += '<div class="custom-radio"><input type="radio" name="reviewRadio" id="RadioGroup1_1" class="checkedFocus" value="1" checked="checked"><label for="RadioGroup1_1" class="checked">';
	}else{
		html += '<div class="custom-radio"><input type="radio" name="reviewRadio" id="RadioGroup1_1" class="checkedFocus" value="1"><label for="RadioGroup1_1">';
	}
//	html += '<div class="custom-radio"><input type="radio" name="reviewRadio" id="RadioGroup1_1" class="checkedFocus" value="1" checked="checked"><label for="RadioGroup1_1" class="checked">';
	html += '<span>请短信提醒复核员在当天复核</span>';
	html += '</label></div>';
	html += '</div>';
	html += '<div class="in-radio">';
	if(data&&data.msgSwitch=="1"){
		html += '<div class="custom-radio"><input type="radio" name="reviewRadio" id="RadioGroup1_2" class="checkedFocus" value="0"><label for="RadioGroup1_2" class="">';
	}else{
		html += '<div class="custom-radio"><input type="radio" name="reviewRadio" id="RadioGroup1_2" class="checkedFocus" value="0" checked="checked"><label for="RadioGroup1_2" class="checked">';
	}
//	html += '<div class="custom-radio"><input type="radio" name="reviewRadio" id="RadioGroup1_2" class="checkedFocus" value="0"><label for="RadioGroup1_2" class="">';
	html += '<span>不需要短信提醒</span>';
	html += '</label></div>';
	html += '</div>';
	html += '</div>';
	html += '<div class="lineP searchItem  sItem">';
	html += '<label class="lines codeTitle" style="padding-left:5px;">复核员姓名：</label>';
	html += '<div class="lines pItem code" style="width:80px; margin-right:20px;">';
	html += '<input type="text" placeholder="" class="pInput" id="reviewerName"';
	if(data){
		html += 'value="'+data.userName+'" readonly="readonly"';
	}
	html +=	'>';
	html += '</div>';
	html += '<label class="lines codeTitle">复核员性别：</label>';
	html += '<select id="reviewerSex" class="public_area" style="width:50px;margin-right:15px;vertical-align: middle;"';
	if(data){
		html+= ' onfocus="this.defaultIndex=this.selectedIndex;" onchange="this.selectedIndex=this.defaultIndex;" ';
	}
	html += '><option value="1" ';
	if(data){
		if(data.sex==1)
			html += 'selected="selected"';
	}
	html += '>男</option><option value="0"';
	if(data){
		if(data.sex==0)
			html += 'selected="selected"';
	}
	html +='>女</option></select>';
	html += '<label class="lines codeTitle">复核员手机号：</label>';
	html += '<div class="lines pItem code" style="width:112px; margin-right:20px;">';
	html += '<input type="text" placeholder="" class="pInput" id="reviewerPhone" '
	if(data){
		html += 'value="'+data.mobil+'" readonly="readonly"';
	}	
	html +='>';
	html += '</div>';
	if(!data){
		html += '<label class="lines codeTitle" style="color:#01a796;" onclick="saveReviewer('+payType+','+subId+');" id="saveReviewLabel">保存</label>';
	}
	html += '</div>';
	/*html += '<div class="lineP searchItem  sItem payTypeDiv">';
	html += '<div class="in-checkbox">';
	html += '<div class="custom-checkbox"><input type="checkbox" name="bankCheck" id="checkbox_a"><label for="checkbox_a" class="checked">';
	html += '<span>银联企业网银对公在线支付(B2B)</span>';
	html += '</label></div>';
	html += '</div>';
	html += '<span class="cRem">支持21家银行的企业网银</span>';
	html += ' </div>';
	html += '<div class="lineP searchItem  sItem payTypeDiv">';
	html += '<div class="in-checkbox">';
	html += '<div class="custom-checkbox"><input type="checkbox" name="bankCheck" id="checkbox_c"><label for="checkbox_c" class="checked">';
	html += '<span>民生企业网银E支付（B2B）</span>';
	html += '</label></div>';
	html += '</div>';
	html += '<span class="cRem">支持北京农村商业银行的企业网银</span>';
	html += '</div>';*/
	html += '</div>';

	var option = {
		title : title,
		btn : parseInt("0011", 2),
		isOkBtn : true,
		onCancel : function() { // 下一步回调函数
			var tmpFlag = generatePayOrder(payType,subId);
			if(!tmpFlag){
				beforePay(payType,subId);
			}
		},
//		class : [ 'okAddressBox', 'okColseBox' ]
	}

	var classArr = ['okAddressBox','okColseBox','payBox'];
	window.wxc.xcConfirm(html, "custom", option);
	setXcConfirmClass(classArr);
	$('input').customInput();
//	newObject();
	$('.payBox .popBox').css('height','338px');
	$("."+classArr[1] +" .popBox .sgBtn.ok").html('取消');
	$("."+classArr[1] +" .popBox .sgBtn.cancel").html('下一步').addClass('notClose');
	$('input[name=bankCheck]').click(function(){
		var _this = $(this);
		$('input[name=bankCheck]').each(function(index,obj){
			if($(obj)!=_this){
				$(obj)[0].checked = false;
			}
		});
	});
}
function generatePayOrder(payType,subId){
    if($fee=='0.00'){
    	$('.payBox').hide();
        $HXT.showInfo("您单位登记的所有电表缴费及时，没有欠费，感谢使用。");
        return true;
    }
    if(subId){
    	if($("#sub_total_fee_"+subId).html()=='0.00'||$("#sub_total_fee_"+subId).html()=='0'||$("#sub_total_fee_"+subId).html()=='0.0'){
    		$('.payBox').hide();
    		$HXT.showInfo("该分组登记的所有电表缴费及时，没有欠费，感谢使用。");
    		return true;
    	}
    }
    var reviewerName = $("#reviewerName").val();
    var reviewerSex= $("#reviewerSex").val();
    var reviewerPhone = $("#reviewerPhone").val();
    var payType1 = 1;
    var payType2 = 0;
    var reviewRadio ;
	$(".reviewRadioDiv").find('label').each(function(index,obj){
		if($(obj)[0].className.indexOf('checked')!=-1){
			reviewRadio = $("#"+$(obj).attr('for')).val()
		}
	});
    /*$(".payTypeDiv").each(function(index,obj){
    	if($(obj).find('label')[0].className.indexOf('checked')!=-1){
    		if(index==0){
    			payType1 = 1;
    		}else{
    			payType1 = 0;
    		}
    		if(index==1){
    			payType2 = 2;
    		}else{
    			payType2 = 0;
    		}
    		return false;
    	}
    })*/
    var url = $("#projectPath").val() + '/pay/checkPayTime';
    var cId = $("#cId").val();
    var ready = checkPayOrderParam();
    if(ready){
    	$.ajax({
    		type: "GET",
    		url: url,
    		dataType: "json",
    		success: function (data) {
    			if (data.status == 'success') {
    				var url = $("#projectPath").val() + '/pay/toMiddle';
    				
//    				url = url + "?reviewRadio="+reviewRadio+"&reviewerName="+reviewerName+"&reviewerSex="+reviewerSex;
//    				url = url + "&reviewerPhone="+reviewerPhone+"&payType1="+payType1+"&payType2="+payType2;
//    				url = url + "&cId="+cId;
    				
    				
    				var formStr = '<form method="get" action="' + url + '">';
    				formStr += '<input type="hidden" value="'+reviewRadio+'" name="reviewRadio"/>';
//    				formStr += '<input type="hidden" value="'+reviewerName+'" name="reviewerName"/>';
//    				formStr += '<input type="hidden" value="'+reviewerSex+'" name="reviewerSex"/>';
//    				formStr += '<input type="hidden" value="'+reviewerPhone+'" name="reviewerPhone"/>';
    				formStr += '<input type="hidden" value="'+payType1+'" name="payType1"/>';
    				formStr += '<input type="hidden" value="'+payType2+'" name="payType2"/>';
    				formStr += '<input type="hidden" value="'+cId+'" name="cId"/>';
    				if(payType==2){
//    					url = url + "&payType=2&subId="+subId;
    					formStr += '<input type="hidden" value="2" name="payType"/>';
    					formStr += '<input type="hidden" value="'+subId+'" name="subId"/>';
    				}else{
//    					url = url + "&payType=1";
    					formStr += '<input type="hidden" value="1" name="payType"/>';
    				}
    				formStr += '</form>';
//    				var $a = "<a id='alink' href='"+url+"' style='visibility: hidden;' target='_blank'><span id='spanId'>下一步</span></a>";
    				var $tempForm = $(formStr);
    				$("body").append($tempForm);  
    				$tempForm.submit();  
    				$tempForm.remove();  
//    				$("body").append($a);
//    				$("#spanId").click();
//    				$("#alink").remove();
    			} else {
    				$HXT.showInfo(data.msg);
    			}
    		}
    	});
    }else{
    	return false;
    }
    return true;
}
function checkPayOrderParam(){
	var res = false;
	var payType1 = 1;
    var payType2 = 0;
    /*$(".payTypeDiv").each(function(index,obj){
    	if($(obj).find('label')[0].className.indexOf('checked')!=-1){
    		if(index==0){
    			payType1 = 1;
    		}else{
    			payType1 = 0;
    		}
    		if(index==1){
    			payType2 = 2;
    		}else{
    			payType2 = 0;
    		}
    		return false;
    	}
    })*/
    if(payType1!=1&&payType2!=2){
    	$HXT.showInfo('请选择支付方式！');
    	return res;
    }
    return true;
}
function exportExcle(){
    var url =  $("#projectPath").val() + '/excle/exportExcle';
    $.ajax({
        type: "POST",
        url: url,
        data: dataArr,
        dataType: "json",
        success: function (data) {
            if (data.status == 'success') {
            	var currDate = new Date();
                var d = new Date();
                var YMDHMS = d.getFullYear()+""+(d.getMonth()+1)+d.getDate()+d.getHours()+d.getMinutes()+d.getSeconds();
            	var name = encodeURIComponent(YMDHMS + "电表缴费号欠费信息");
            	window.location.href = $("#projectPath").val() + "/index/toDownload?link="+ data.url + "&name=" + name;
//                window.location.href = $("#projectPath").val() + data.url;
            } else {
            	$HXT.showInfo(data.msg);
            }
        }
    });
}

function setXcConfirmClass(className){
	//$(".xcConfirm").addClass(className);
	
	if( className && className.length > 0){
		for(var i = 0; i <className.length ; i ++){
			$(".xcConfirm").addClass(className[i]);
		}
		
	}
}
function saveReviewer(payType,subId){
	var reviewerName = $("#reviewerName").val();
    var reviewerSex= $("#reviewerSex").val();
    var reviewerPhone = $("#reviewerPhone").val();
    if(!reviewerName){
    	layer.msg("请填写复核人员姓名！", {time:2000});
        return true;
    }
    if(!reviewerSex){
    	layer.msg("请选择复核人员性别！", {time:2000});
    	return true;
    }
    if(!reviewerPhone){
    	layer.msg("请填写复核人员手机号！", {time:2000});
    	return true;
    }
    var dataArr = {
    		reviewerName:reviewerName,
    		reviewerSex:reviewerSex,
    		reviewerPhone:reviewerPhone
    }
    var url =  $("#projectPath").val() + '/pay/saveReviewer';
    $.ajax({
        type: "POST",
        url: url,
        data: dataArr,
        dataType: "json",
        success: function (data) {
            if (data.code == '1') {
            	$("#saveReviewLabel").hide();
            	$("#reviewerName").attr("readonly","readonly");
            	$("#reviewerSex").attr("readonly","readonly");
            	$("#reviewerSex").attr("onfocus","this.defaultIndex=this.selectedIndex;");
            	$("#reviewerSex").attr("onchange","this.selectedIndex=this.defaultIndex;");
            	$("#reviewerPhone").attr("readonly","readonly");
                layer.msg("保存成功！", {time:2000});
            } else {
            	layer.msg(data.message, {time:2000});
            }
        }
    });
}