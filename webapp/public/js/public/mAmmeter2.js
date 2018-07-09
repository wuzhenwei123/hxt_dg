$(document).ready(function(){
//	$(".weui_loading_toast").show();
	$HXT.loading('努力加载中');
	getData();
});
//获取发票电表信息
function getData() {
    var url = $("#projectPath").val() + '/ammetermanager/getInvoceAmmeterInfoByCId?proxy=1&cId=' + $("#cId").val()+'&_t='+Math.random();
    $.ajax({
        type: "GET",
        url: url,
        dataType: "json",
        success: function (data) {
//        	$(".weui_loading_toast").hide();
        	$(".loading").remove();
            dataCh(data);
        }
    });
}
var $fee;
function titleStr(cid,cname,count,fee,yuFee,yuNo){
	var str = "";
	str += '<div class="commBody jySearchBox m_pay">';
	str += '	<div class="backRem m_pay_ok pay_ye">您已开通手机缴费业务，以下是您开通信息：</div>';
	str += '    <div class="m_pay_top_items">';
	str += '       <div class="m_pay_top_item on">';
	str += '       <p>您绑定了<font class="pay_yellow">'+count+'</font>个抄表电缴费号，应交费总金额为：<font class="pay_yellow">'+fee+'</font> 元</p>';
	if(yuNo!=""){
		str += '           <p>其中您的缴费号：'+yuNo+'目前的欠费是预收电费，共计：<font class="pay_yellow">'+yuFee+'</font>元</p>';
	}
	str += '      </div>';
	str += '	<div class="lineP m_pay_add_group">';
	str += '		<a href="javascript:showDetail4();" class="line_l pay_btn">增加电表分组</a>';
	str += '	    <span class="line_l">如果您需要管理多个不同项目的电费，请根据项目情况进行分组</span>';
	str += '	 </div>';
	str += '	 <div class="lineP m_pay_add_group">';
	str += '	 	<a href="javascript:exportExcle();" class="line_l pay_btn">导出报表</a>';
	str += '	    <span class="line_l">将已绑定的缴费号欠费信息导出Excel表</span>';
	str += '	</div>';
	str += '	</div>';
	str += '</div>';
	return str;
}
//发票信息处理
var dataArr;
function dataCh(myData) {
    // 发票信息 数据处理
	var yuFee = 0;//预收总金额
	var yuNo = "";//
    var str = '';
    var obj = eval(myData.data);
    dataArr = eval(myData);
    var ammetercount=0;//电表数
	var flagStr = '';
    for (var i = 0; i < obj.length; i++) {
    	var infoFlag = false;
    	str += '<div class="commBody jyItem">';//外层开始
    	//title
    	str += '<div class="titleBox">';
    	str += '<input type="hidden" value="'+obj[i].s_id+'" class="s_id"/>';
    	str += '<input type="hidden" value="'+obj[i].c_id+'" class="c_id"/>';
    	str += '	<div class="fl jyListItemsLeft">';
    	str += '		<div><span>分组名称：<font> '+obj[i].sub_name+'</font></span></div>';
    	str += '		<div><span>发票收件人：<font>'+ obj[i].consignee +'</font></span><span>收件人手机：<font>'+obj[i].consignee_phone+'</font></span><span>邮政编码：<font>'+obj[i].zip_code+'</font></span></div>';
    	str += '		<div><span>发票收件地址：<font>'+obj[i].province_name + obj[i].city_name + obj[i].area_name + obj[i].consignee_address+'</font></span></div>';
    	str += '	</div>';
    	str += '	<div class="fr jyListItemsRight jyItemBtn">';
    	str += '		<a class="jyBtn" href="javascript:showDetail3('+obj[i].s_id+');">变更此信息</a>';
    	str += '		<a class="jyBtn on" href="javascript:delSubcompany('+obj[i].s_id+')">删除此信息</a>';
    	str += '	</div>';
    	str += '	<span class="clear"></span>';
    	str += '</div>';
//    	if(!obj[i].sub_name||!obj[i].consignee||!obj[i].consignee_address||!obj[i].consignee_phone||!obj[i].zip_code){
//    		infoFlag = true;
//    	}
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
        	if(eval(truncFee((ammeters[j]).totalFee))>0&&eval(truncFee((ammeters[j]).totalFee))%100==0){
        		yuFee=eval(yuFee)+eval(truncFee((ammeters[j]).totalFee))
        		yuNo += eval(ammeters[j]).ammeter_no+'，';
        	}
        	str += '<td>'+pay_statu+'</td>';
        	if (ammeters[j].proxy_flag == '0') {
        		str += '<td class="yjBtnTdBox"><a href="javascript:updateAmmeter('+eval(ammeters[j]).a_id+',1)" id="operat_'+eval(ammeters[j]).a_id+'">设为手机缴费</a><a href="javascript:bind2(\''+obj[i].s_id+'\');">详情</a>';
        	}
        	if (ammeters[j].proxy_flag == '1') {
        		str += '<td class="yjBtnTdBox"><a href="javascript:updateAmmeter('+eval(ammeters[j]).a_id+',0)" id="operat_'+eval(ammeters[j]).a_id+'">取消手机缴费</a><a href="javascript:bind2(\''+obj[i].s_id+'\');">详情</a>';
        	}
        	if(!ammeters[j].bill_img){
        		str += '<div class="uploadDiv" aid='+eval(ammeters[j]).a_id+' style="cursor:hand" onclick="upAid='+eval(ammeters[j]).a_id+';" id="upShowBillImg_'+eval(ammeters[j]).a_id+'"><label class="upFileLabel"><div id="file'+eval(ammeters[j]).a_id+'">上传缴费单</div></label></div>';
        	}else{
        		str += '<a href="javascript:showBillImg('+eval(ammeters[j]).a_id+')" id="showBillImg_'+eval(ammeters[j]).a_id+'">查看缴费单</a>';
        	}
        	str += '</td></tr>'
        }
        str += '<tr><td>'+(++num1)+'</td><td>抄电表</td><td><div class="lines pItem" style="width:126px;"><input id="ammeterNum_bind_'+obj[i].s_id+'" type="text" placeholder="缴费号" class="pInput"></div></td><td></td><td></td><td></td><td class="yjBtnTdBox"><a href="javascript:bind(\''+obj[i].s_id+'\',\''+obj[i].c_id+'\');">设置为手机缴费</a></td></tr>';
        str += '</table>';
        str += '</div>';
        //小计
        str += '<div class="lineP linePjyAllBox _lPay">'
        str += '	<span class="lines">应交金额小计：<font id="sub_total_fee_'+obj[i].s_id+'" class="pay_yellow">'+truncFee(eval(obj[i]).totalFee)+'</font> 元</span>';
        str += '</div>'
        str += '</div>';//外层结束
        if(infoFlag){
        	showModify(obj[i].s_id);
        }
    }
    if(!obj.length){
    	showDetail5();
    }
    str = titleStr(obj[0]?obj[0].c_id:'',obj[0]?obj[0].c_name:'',ammetercount,truncFee(myData.all_total_fee),yuFee,yuNo)+str;
    $fee = truncFee(myData.all_total_fee);
    $(".rightBox").append(str);
    
    
    var arrayInput = document.getElementsByTagName("input");
	for( var i = 0; i < arrayInput.length; i ++){
		myPlaceholder(arrayInput[i]);
	}
	$('.uploadDiv').each(function(index,obj){
		upload2($("#projectPath").val(),"file"+$(obj).attr('aid'),upload_call_back1,'','','pic',$(obj).attr('aid'));
	})
}
//信息不完整弹出窗
function showModify(sid){

	var url = $("#projectPath").val() + '/ammetermanager/getHSubCompanyBySId?sId=' + sid;
	$.ajax({
		type : "GET",
		url : url,
		data : {_r:Math.random()},
		dataType : "json",
		success : function(data) {
			if (data.status == 'success') {
				var datas = JSON.parse(data.data);
				/*var html = '<div class="wItemBox" >';
				html += '<input type="hidden" value="'+datas.s_id+'" id="m_sid">';
				html += '<div class="lineP searchItem  sItem"><label class="lines codeTitle">单位名称：</label><div class="lines pItem code" style="width:400px;float:right;"><input readonly="readonly" value="'+datas.c_name+'" type="text" placeholder="单位名称" class="pInput"> </div></div>';
				html += '<div class="lineP searchItem  sItem"><label class="lines codeTitle">分组名称：</label><div class="lines pItem code" style="width:400px;float:right;"><input id="m_sub_name" value="'+datas.sub_name+'" type="text" placeholder="分组名称" class="pInput"> </div></div>';
				html += '<div class="lineP searchItem  sItem"><label class="lines codeTitle">发票台头名称：</label><div class="lines pItem code" style="width:400px;float:right;"><input id="m_invoice_title" value="'+datas.invoice_title+'" type="text" placeholder="发票台头名称" class="pInput"> </div></div>';
				html += '<div class="lineP searchItem  sItem"><label class="lines codeTitle">发票收件人姓名：</label><div class="lines pItem code" style="width:400px;float:right;"><input id="m_consignee" value="'+datas.consignee+'" type="text" placeholder="发票收件人姓名" class="pInput"> </div></div>';
				html += '<div class="lineP searchItem  sItem"><label class="lines codeTitle">发票收件人手机：</label><div class="lines pItem code" style="width:400px;float:right;"><input id="m_consignee_phone" value="'+datas.consignee_phone+'" type="text" placeholder="发票收件人手机" class="pInput"> </div></div>';
				html += '<div class="lineP searchItem  sItem"><label class="lines codeTitle">发票收件人地址：</label><div class="lines pItem code" style="width:400px;float:right;"><input id="m_consignee_address" value="'+datas.consignee_address+'" type="text" placeholder="发票收件人地址" class="pInput"> </div></div>';
				html += '<div class="lineP searchItem  sItem"><label class="lines codeTitle">邮政编码：</label><div class="lines pItem code" style="width:400px;float:right;"><input id="m_zip_code" value="'+(datas.zip_code?datas.zip_code:"")+'" type="text" placeholder="邮政编码" class="pInput"> </div></div>';
				html += '</div>';
				var option = {
						title : "信息变更",
						btn : parseInt("0011", 2),
						isOkBtn : true,
						onOk : function() {
							saveHSubCompany();
						},
						class : [ 'okAddressBox', 'on' ]
				}
				
				window.wxc.xcConfirm(html, "custom", option);
				
				if (option.isOkBtn) {
					$('.' + option.class[0] + " .popBox .cancel").remove();
				}*/
				var html = '<div class="wItemBox">';
				html += '<input type="hidden" value="'+datas.s_id+'" id="m_sid">';
				html += '<div class="lineP searchItem  sItem">';
				html += '<label class="lines codeTitle fz_title">单位名称：';
//				html += '<div class="fzRem">';
//				html += '<div class="fz_title_a">为何要设置分组？</div>';
//				html += '<div class="fz_rem">';
//						html += '<p>1、当您有多个缴费号，并且希望交费发票递送到不同地址时，您可以将同一收件地址的缴费号分成一个组；</p>';
//						html += '<p>2、当您有多个缴费号，并且希望用不同的网银账号交费时，您可以将用同一网银账号的缴费号分成一个组；</p>';
//						html += '<p>3、当您有多个项目或者子公司下有不同的缴费号、需要独立核算成本时，您可以将不同项目或子公司下的缴费号分成一个组。</p>';
//					html += '</div>';
//				html += '</div>';
					
					
				html += '</label>';
				html += '<div class="lines pItem code" style="width:277px;">';
				html += '<input type="text" class="pInput" id="m_sub_name">';
				html += ' </div>';
				html += '</div>';
				html += '<div class="lineP searchItem  sItem">';
				html += '<label class="lines codeTitle">收件地区：</label>';
				html += '<select id="m_province_code" class="public_area" gov-value="'+datas.province_code+'"></select>';
				html += '<select id="m_city_code" class="public_area" gov-value="'+datas.city_code+'"></select>';
				html += '<select id="m_area_code" class="public_area" gov-value="'+datas.area_code+'"></select>';
				html += '</div>';
				html += '<div class="lineP searchItem  sItem">';
				html += '<label class="lines codeTitle labelW">&nbsp;</label>';
				html += '<div class="lines pItem code" style="width:277px;">';
				if(datas.consignee_address){
					html += '<input type="text" placeholder="请输入详细街道、小区及门牌号地址信息" class="pInput" id="m_consignee_address" value="'+datas.consignee_address+'">';
				}else{
					html += '<input type="text" placeholder="请输入详细街道、小区及门牌号地址信息" class="pInput" id="m_consignee_address" value="">';
				}
				html += '</div>';
				html += '</div>';
				html += '<div class="lineP searchItem  sItem">';
				html += '<label class="lines codeTitle labelW">&nbsp;</label>';
				html += '<div class="lines pItem code" style="width:100px; margin-right:10px;">';
				if(datas.zip_code){
					html += '<input type="text" placeholder="邮政编码" class="pInput" id="m_zip_code"  value="'+datas.zip_code+'">';
				}else{
					html += '<input type="text" placeholder="邮政编码" class="pInput" id="m_zip_code"  value="">';
				}
				html += '</div>';
				html += '<div class="lines pItem code" style="width:110px; margin-right:10px;">';
				if(datas.consignee){
					html += '<input type="text" placeholder="收件人" id="m_consignee" class="pInput" value="'+datas.consignee+'">';
				}else{
					html += '<input type="text" placeholder="收件人" id="m_consignee" class="pInput" value="">';
				}
				html += '</div>';
				html += '<div class="lines pItem code" style="width:130px;">';
				if(datas.consignee_phone){
					html += '<input type="text" placeholder="收件人手机号" class="pInput" id="m_consignee_phone" value="'+datas.consignee_phone+'">';
				}else{
					html += '<input type="text" placeholder="收件人手机号" class="pInput" id="m_consignee_phone" value="">';
				}
				html += '</div>';
				html += '</div>';
				html += '</div>';
				var txt = "为了让您及时收到交费发票，请您登记一下发票收件地址信息";
				var option = {
					title : "为了让您及时收到交费发票，请您登记一下发票收件地址信息",
					btn : parseInt("0011", 2),
					isOkBtn : true,
					onOk : function() {
						if(!$("#m_sub_name").val()){
//							$HXT.showInfo('请填写分组名称！')
							layer.msg("请填写单位名称！", {time:2000});
							return;
						}
						if(!$("#m_province_code").val()){
//							$HXT.showInfo('请选择省份！')
							layer.msg("请选择省份！", {time:2000});
							return;
						}
						if(!$("#m_city_code").val()){
//							$HXT.showInfo('请选择城市！')
							layer.msg("请选择城市！", {time:2000});
							return;
						}
						if(!$("#m_area_code").val()){
							layer.msg("请选择地区！", {time:2000});
//							$HXT.showInfo('请选择地区！')
							return;
						}
						if(!$("#m_consignee_address").val()){
							layer.msg("请填写详细地址！", {time:2000});
//							$HXT.showInfo('请填写详细地址！')
							return;
						}
						if(!$("#m_zip_code").val()){
							layer.msg("请填写邮编！", {time:2000});
//							$HXT.showInfo('请填写邮编！')
							return;
						}
						var reg = /^\d{6}$/;
						if(!reg.test($("#m_zip_code").val())){
							layer.msg("邮编不正确", {time:2000});
//						 	$HXT.showInfo('邮编不正确');
						  	return;
						}
						if(!$("#m_consignee").val()){
							layer.msg("请填写收件人！", {time:2000});
//							$HXT.showInfo('请填写收件人！')
							return;
						}
						if(!$("#m_consignee_phone").val()){
							layer.msg("请填写收件人手机号！", {time:2000});
//							$HXT.showInfo('请填写收件人手机号！')
							return;
						}
						reg = /^1\d{10}$/;
					    if(!reg.test($("#m_consignee_phone").val())){
					    	layer.msg("收件人手机号码不正确", {time:2000});
//					    	$HXT.showInfo('收件人手机号码不正确');
					    	return;
					    }
						saveHSubCompany();
					},
//					class : [ 'okAddressBox' ]
				}
				var classArr = ['okAddressBox'];
				window.wxc.xcConfirm(html, "custom", option);
				setXcConfirmClass(classArr);
				var isOkBtn = true;
				if(option.isOkBtn){
					$('.'+classArr[0] + " .popBox .cancel").remove();
				}
				new $gov("m_province_code","m_city_code","m_area_code").init();
				$("."+classArr[0] +" .popBox .sgBtn.ok").html('保存').addClass('notClose');
				$(".tt").css("color","#01a796");
				
				$('.clsBtn').hide();
				
				
				var arrayInput = document.getElementsByTagName("input");
				for( var i = 0; i < arrayInput.length; i ++){
					myPlaceholder(arrayInput[i]);
				}
				
//				newObject();
			} else {
				$HXT.showInfo(data.msg);
			}
		}
	});
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
//绑定弹出
function bind(id,cid) {
    var ammetertNo =$("#ammeterNum_bind_"+id).val();
    if (isEmpty(ammetertNo)) {
        $HXT.showInfo("缴费号不能为空！");
        return;
    }
    var reg = /^[\d.\-_]+$/;
	if(!reg.test(ammetertNo)){
		$HXT.showInfo("请您输入正确的缴费号");
		return false;
	}
    var url = $("#projectPath").val() + '/hCommon/getAmmeterInfo?electricNum=' + ammetertNo;
    $.ajax({
        type: "GET",
        url: url,
        dataType: "json",
        success: function (data) {
            if (data.status == 'success') {
                if (eval(data.data).resultCode == '00') {//电表存在
                    showDetail(data.data,ammetertNo,cid,id);
                } else {
                    $HXT.showInfo("该电表不存在，请检查重新输入！");
                }
            } else {
                $HXT.showInfo(data.msg);
            }


        }
    });
}
//弹窗
function showDetail(data,ammetertNo,cid,sid){
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
		title : "请确认电表信息",
		btn : parseInt("0011", 2),
		isOkBtn : true,
		onOk : function() {
			bindAmmeter(data,ammetertNo,cid,sid);
		},
//		class : [ 'okAddressBox', 'on' ]
	}

	var classArr = ['okAddressBox'];
	window.wxc.xcConfirm(html, "custom", option);
	setXcConfirmClass(classArr);
	var isOkBtn = true;
	if(option.isOkBtn){
		$('.'+classArr[0] + " .popBox .cancel").remove();
	}
//	newObject();
	$("."+classArr[0] +" .popBox .sgBtn.ok").html('保存');
	$(".popBox").css("height","480px");
}
//详情弹窗
function bind2(id){
	var ammetertNo =$("#ammeterNum_td_"+id).html();
    if (isEmpty(ammetertNo)) {
        $HXT.showInfo("缴费号不能为空！");
        return false;
    }
    var reg = /^[\d.\-_]+$/;
	if(!reg.test(ammetertNo)){
		$HXT.showInfo("请您输入正确的缴费号");
		return false;
	}
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
	
	var classArr = ['okAddressBox'];
	window.wxc.xcConfirm(html, "custom", option);
	setXcConfirmClass(classArr);
	var isOkBtn = true;
	if(option.isOkBtn){
		$('.'+classArr[0] + " .popBox .cancel").remove();
	}
//	newObject();
	$("."+classArr[0] +" .popBox .sgBtn.ok").html('确定');
	$(".popBox").css("height","480px");
}
//变更弹窗
function showDetail3(sid){
	var url = $("#projectPath").val() + '/ammetermanager/getHSubCompanyBySId?sId=' + sid;
	$.ajax({
		type : "GET",
		url : url,
		data : {_r:Math.random()},
		dataType : "json",
		success : function(data) {
			if (data.status == 'success') {
				var datas = JSON.parse(data.data);
				/*var html = '<div class="wItemBox" >';
				html += '<input type="hidden" value="'+datas.s_id+'" id="m_sid">';
				html += '<div class="lineP searchItem  sItem"><label class="lines codeTitle">单位名称：</label><div class="lines pItem code" style="width:400px;float:right;"><input readonly="readonly" value="'+datas.c_name+'" type="text" placeholder="单位名称" class="pInput"> </div></div>';
				html += '<div class="lineP searchItem  sItem"><label class="lines codeTitle">分组名称：</label><div class="lines pItem code" style="width:400px;float:right;"><input id="m_sub_name" value="'+datas.sub_name+'" type="text" placeholder="分组名称" class="pInput"> </div></div>';
				html += '<div class="lineP searchItem  sItem"><label class="lines codeTitle">发票台头名称：</label><div class="lines pItem code" style="width:400px;float:right;"><input id="m_invoice_title" value="'+datas.invoice_title+'" type="text" placeholder="发票台头名称" class="pInput"> </div></div>';
				html += '<div class="lineP searchItem  sItem"><label class="lines codeTitle">发票收件人姓名：</label><div class="lines pItem code" style="width:400px;float:right;"><input id="m_consignee" value="'+datas.consignee+'" type="text" placeholder="发票收件人姓名" class="pInput"> </div></div>';
				html += '<div class="lineP searchItem  sItem"><label class="lines codeTitle">发票收件人手机：</label><div class="lines pItem code" style="width:400px;float:right;"><input id="m_consignee_phone" value="'+datas.consignee_phone+'" type="text" placeholder="发票收件人手机" class="pInput"> </div></div>';
				html += '<div class="lineP searchItem  sItem"><label class="lines codeTitle">发票收件人地址：</label><div class="lines pItem code" style="width:400px;float:right;"><input id="m_consignee_address" value="'+datas.consignee_address+'" type="text" placeholder="发票收件人地址" class="pInput"> </div></div>';
				html += '<div class="lineP searchItem  sItem"><label class="lines codeTitle">邮政编码：</label><div class="lines pItem code" style="width:400px;float:right;"><input id="m_zip_code" value="'+(datas.zip_code?datas.zip_code:"")+'" type="text" placeholder="邮政编码" class="pInput"> </div></div>';
				html += '</div>';
				var option = {
						title : "信息变更",
						btn : parseInt("0011", 2),
						isOkBtn : true,
						onOk : function() {
							saveHSubCompany();
						},
						class : [ 'okAddressBox', 'on' ]
				}
				
				window.wxc.xcConfirm(html, "custom", option);
				
				if (option.isOkBtn) {
					$('.' + option.class[0] + " .popBox .cancel").remove();
				}*/
				var html = '<div class="wItemBox">';
				html += '<input type="hidden" value="'+datas.s_id+'" id="m_sid">';
				html += '<div class="lineP searchItem  sItem">';
				html += '<label class="lines codeTitle fz_title">单位名称：';
//				html += '<div class="fzRem">';
//				html += '<div class="fz_title_a">为何要设置分组？</div>';
//				html += '<div class="fz_rem">';
//						html += '<p>1、当您有多个缴费号，并且希望交费发票递送到不同地址时，您可以将同一收件地址的缴费号分成一个组；</p>';
//						html += '<p>2、当您有多个缴费号，并且希望用不同的网银账号交费时，您可以将用同一网银账号的缴费号分成一个组；</p>';
//						html += '<p>3、当您有多个项目或者子公司下有不同的缴费号、需要独立核算成本时，您可以将不同项目或子公司下的缴费号分成一个组。</p>';
//					html += '</div>';
//				html += '</div>';
					
					
				html += '</label>';
				html += '<div class="lines pItem code" style="width:277px;">';
				html += '<input type="text" class="pInput" id="m_sub_name" value="'+datas.sub_name+'">';
				html += ' </div>';
				html += '</div>';
				html += '<div class="lineP searchItem  sItem">';
				html += '<label class="lines codeTitle">收件地区：</label>';
				html += '<select id="m_province_code" class="public_area" gov-value="'+datas.province_code+'"></select>';
				html += '<select id="m_city_code" class="public_area" gov-value="'+datas.city_code+'"></select>';
				html += '<select id="m_area_code" class="public_area" gov-value="'+datas.area_code+'"></select>';
				html += '</div>';
				html += '<div class="lineP searchItem  sItem">';
				html += '<label class="lines codeTitle labelW">&nbsp;</label>';
				html += '<div class="lines pItem code" style="width:277px;">';
				if(datas.consignee_address){
					html += '<input type="text" placeholder="请输入详细街道、小区及门牌号地址信息" class="pInput" id="m_consignee_address" value="'+datas.consignee_address+'">';
				}else{
					html += '<input type="text" placeholder="请输入详细街道、小区及门牌号地址信息" class="pInput" id="m_consignee_address" value="">';
				}
				html += '</div>';
				html += '</div>';
				html += '<div class="lineP searchItem  sItem">';
				html += '<label class="lines codeTitle labelW">&nbsp;</label>';
				html += '<div class="lines pItem code" style="width:100px; margin-right:10px;">';
				if(datas.zip_code){
					html += '<input type="text" placeholder="邮政编码" class="pInput" id="m_zip_code"  value="'+datas.zip_code+'">';
				}else{
					html += '<input type="text" placeholder="邮政编码" class="pInput" id="m_zip_code"  value="">';
				}
				html += '</div>';
				html += '<div class="lines pItem code" style="width:110px; margin-right:10px;">';
				if(datas.consignee){
					html += '<input type="text" placeholder="收件人" id="m_consignee" class="pInput" value="'+datas.consignee+'">';
				}else{
					html += '<input type="text" placeholder="收件人" id="m_consignee" class="pInput" value="">';
				}
				html += '</div>';
				html += '<div class="lines pItem code" style="width:130px;">';
				if(datas.consignee_phone){
					html += '<input type="text" placeholder="收件人手机号" class="pInput" id="m_consignee_phone" value="'+datas.consignee_phone+'">';
				}else{
					html += '<input type="text" placeholder="收件人手机号" class="pInput" id="m_consignee_phone" value="">';
				}
				html += '</div>';
				html += '</div>';
				html += '</div>';
				var txt = "为了让您及时收到交费发票，请您登记一下发票收件地址信息";
				var option = {
					title : "变更单位信息",
					btn : parseInt("0011", 2),
					isOkBtn : true,
					onOk : function() {
						if(!$("#m_sub_name").val()){
//							$HXT.showInfo('请填写分组名称！')
							layer.msg("请填写单位名称！", {time:2000});
							return;
						}
						if(!$("#m_province_code").val()){
							layer.msg("请选择省份！", {time:2000});
//							$HXT.showInfo('请选择省份！')
							return;
						}
						if(!$("#m_city_code").val()){
//							$HXT.showInfo('请选择城市！')
							layer.msg("请选择城市！", {time:2000});
							return;
						}
						if(!$("#m_area_code").val()){
							layer.msg("请选择地区！", {time:2000});
//							$HXT.showInfo('请选择地区！')
							return;
						}
						if(!$("#m_consignee_address").val()){
							layer.msg("请填写详细地址！", {time:2000});
//							$HXT.showInfo('请填写详细地址！')
							return;
						}
						if(!$("#m_zip_code").val()){
							layer.msg("请填写邮编！", {time:2000});
//							$HXT.showInfo('请填写邮编！')
							return;
						}
						var reg = /^\d{6}$/;
						if(!reg.test($("#m_zip_code").val())){
							layer.msg("邮编不正确", {time:2000});
//						 	$HXT.showInfo('邮编不正确');
						  	return;
						}
						if(!$("#m_consignee").val()){
							layer.msg("请填写收件人！", {time:2000});
//							$HXT.showInfo('请填写收件人！')
							return;
						}
						if(!$("#m_consignee_phone").val()){
							layer.msg("请填写收件人手机号！", {time:2000});
//							$HXT.showInfo('请填写收件人手机号！')
							return;
						}
						reg = /^1\d{10}$/;
					    if(!reg.test($("#m_consignee_phone").val())){
					    	layer.msg("收件人手机号码不正确", {time:2000});
//					    	$HXT.showInfo('收件人手机号码不正确');
					    	return;
					    }
						saveHSubCompany();
					},
//					class : [ 'okAddressBox' ]
				}

				
				var classArr = ['okAddressBox'];
				window.wxc.xcConfirm(html, "custom", option);
				setXcConfirmClass(classArr);
				var isOkBtn = true;
				if(option.isOkBtn){
					$('.'+classArr[0] + " .popBox .cancel").remove();
				}
				new $gov("m_province_code","m_city_code","m_area_code").init();
				$("."+classArr[0] +" .popBox .sgBtn.ok").html('保存').addClass('notClose');
				$(".tt").css("color","#01a796");
				
				var arrayInput = document.getElementsByTagName("input");
				for( var i = 0; i < arrayInput.length; i ++){
					myPlaceholder(arrayInput[i]);
				}
				
//				newObject();
			} else {
				$HXT.showInfo(data.msg);
			}
		}
	});
	
}
//新增弹窗
function showDetail4(){
	var html = '<div class="wItemBox">';
	html += '<div class="lineP searchItem  sItem">';
//	html += '<label class="lines codeTitle" onmouseover="showlabel()" onmouseout="shutlabel()">分组名称：</label>';
	html += '<label class="lines codeTitle fz_title">单位名称：';
//	html += '<div class="fzRem">';
//	html += '<div class="fz_title_a">为何要设置分组？</div>';
//	html += '<div class="fz_rem">';
//			html += '<p>1、当您有多个缴费号，并且希望交费发票递送到不同地址时，您可以将同一收件地址的缴费号分成一个组；</p>';
//			html += '<p>2、当您有多个缴费号，并且希望用不同的网银账号交费时，您可以将用同一网银账号的缴费号分成一个组；</p>';
//			html += '<p>3、当您有多个项目或者子公司下有不同的缴费号、需要独立核算成本时，您可以将不同项目或子公司下的缴费号分成一个组。</p>';
//		html += '</div>';
//	html += '</div>';
		
		
	html += '</label>';
	html += '<div class="lines pItem code" style="width:277px;">';
	html += '<input type="text" class="pInput" id="a_sub_name">';
	html += ' </div>';
	html += '</div>';
	html += '<div class="lineP searchItem  sItem">';
	html += '<label class="lines codeTitle">收件地区：</label>';
	html += '<select id="a_province_code" class="public_area"></select>';
	html += '<select id="a_city_code" class="public_area"></select>';
	html += '<select id="a_area_code" class="public_area"></select>';
	html += '</div>';
	html += '<div class="lineP searchItem  sItem">';
	html += '<label class="lines codeTitle labelW">&nbsp;</label>';
	html += '<div class="lines pItem code" style="width:277px;">';
	html += '<input type="text" placeholder="请输入详细街道、小区及门牌号地址信息" class="pInput" id="a_consignee_address">';
	html += '</div>';
	html += '</div>';
	html += '<div class="lineP searchItem  sItem">';
	html += '<label class="lines codeTitle labelW">&nbsp;</label>';
	html += '<div class="lines pItem code" style="width:100px; margin-right:10px;">';
	html += '<input type="text" placeholder="邮政编码" class="pInput" id="a_zip_code">';
	html += '</div>';
	html += '<div class="lines pItem code" style="width:110px; margin-right:10px;">';
	html += '<input type="text" placeholder="收件人" id="a_consignee" class="pInput">';
	html += '</div>';
	html += '<div class="lines pItem code" style="width:130px;">';
	html += '<input type="text" placeholder="收件人手机号" class="pInput" id="a_consignee_phone">';
	html += '</div>';
	html += '</div>';
	html += '</div>';
	var txt = "为了让您及时收到交费发票，请您登记一下发票收件地址信息";
	var option = {
		title : "为了让您及时收到交费发票，请您登记一下发票收件地址信息",
		btn : parseInt("0011", 2),
		isOkBtn : true,
		onOk : function() {
			if(!$("#a_sub_name").val()){
//				$HXT.showInfo('请填写分组名称！')
				layer.msg("请填写单位名称！", {time:2000});
				return;
			}
			if(!$("#a_province_code").val()){
				layer.msg("请选择省份！", {time:2000});
//				$HXT.showInfo('请选择省份！')
				return;
			}
			if(!$("#a_city_code").val()){
				layer.msg("请选择城市！", {time:2000});
//				$HXT.showInfo('请选择城市！')
				return;
			}
			if(!$("#a_area_code").val()){
				layer.msg("请选择地区！", {time:2000});
//				$HXT.showInfo('请选择地区！')
				return;
			}
			if(!$("#a_consignee_address").val()){
				layer.msg("请填写详细地址！", {time:2000});
//				$HXT.showInfo('请填写详细地址！')
				return;
			}
			if(!$("#a_zip_code").val()){
				layer.msg("请填写邮编！", {time:2000});
//				$HXT.showInfo('请填写邮编！')
				return;
			}
			var reg = /^\d{6}$/;
			if(!reg.test($("#a_zip_code").val())){
				layer.msg("邮编不正确", {time:2000});
//			 	$HXT.showInfo('邮编不正确');
			  	return;
			}
			if(!$("#a_consignee").val()){
				layer.msg("请填写收件人！", {time:2000});
//				$HXT.showInfo('请填写收件人！')
				return;
			}
			if(!$("#a_consignee_phone").val()){
				layer.msg("请填写收件人手机号！", {time:2000});
//				$HXT.showInfo('请填写收件人手机号！')
				return;
			}
			reg = /^1\d{10}$/;
		    if(!reg.test($("#a_consignee_phone").val())){
		    	layer.msg("收件人手机号码不正确", {time:2000});
//		    	$HXT.showInfo('收件人手机号码不正确');
		    	return;
		    }
		   
			var subData = {
					'province_code':$("#a_province_code").val(),
					'city_code':$("#a_city_code").val(),
					'area_code':$("#a_area_code").val(),
			        'sub_name': $("#a_sub_name").val(),
			        'consignee': $("#a_consignee").val(),
			        'consignee_phone': $("#a_consignee_phone").val(),
			        'consignee_address': $("#a_consignee_address").val(),
			        'zip_code' : $("#a_zip_code").val(),
			        '_r':Math.random()
			};
			var url = $("#projectPath").val() + '/ammetermanager/saveHSubCompany';
			$.ajax({
				type : "POST",
				url : url,
				data : subData,
				dataType : "json",
				success : function(data) {
					if (data.status == 'success') {
						layer.msg("添加成功!", {time:2000});
//						$HXT.showInfo('添加成功!')
						window.location.reload();
					} else {
						layer.msg(data.msg, {time:2000});
//						$HXT.showInfo(data.msg);
					}
				}
			});
		},
//		class : [ 'okAddressBox' ]
	}

	
	var classArr = ['okAddressBox'];
	window.wxc.xcConfirm(html, "custom", option);
	setXcConfirmClass(classArr);
	var isOkBtn = true;
	if(option.isOkBtn){
		$('.'+classArr[0] + " .popBox .cancel").remove();
	}
	new $gov("a_province_code","a_city_code","a_area_code").init();
	$("."+classArr[0] +" .popBox .sgBtn.ok").html('保存').addClass('notClose');
	$(".tt").css("color","#01a796");
	
	var arrayInput = document.getElementsByTagName("input");
	for( var i = 0; i < arrayInput.length; i ++){
		myPlaceholder(arrayInput[i]);
	}
	
}
//没有分组弹框
function showDetail5(){
	var html = '<div class="wItemBox">';
	html += '<div class="lineP searchItem  sItem">';
//	html += '<label class="lines codeTitle" onmouseover="showlabel()" onmouseout="shutlabel()">分组名称：</label>';
	html += '<label class="lines codeTitle fz_title">单位名称：';
//	html += '<div class="fzRem">';
//	html += '<div class="fz_title_a">为何要设置分组？</div>';
//	html += '<div class="fz_rem">';
//	html += '<p>1、当您有多个缴费号，并且希望交费发票递送到不同地址时，您可以将同一收件地址的缴费号分成一个组；</p>';
//	html += '<p>2、当您有多个缴费号，并且希望用不同的网银账号交费时，您可以将用同一网银账号的缴费号分成一个组；</p>';
//	html += '<p>3、当您有多个项目或者子公司下有不同的缴费号、需要独立核算成本时，您可以将不同项目或子公司下的缴费号分成一个组。</p>';
//	html += '</div>';
//	html += '</div>';
	
	
	html += '</label>';
	html += '<div class="lines pItem code" style="width:277px;">';
	html += '<input type="text" class="pInput" id="a_sub_name">';
	html += ' </div>';
	html += '</div>';
	html += '<div class="lineP searchItem  sItem">';
	html += '<label class="lines codeTitle">收件地区：</label>';
	html += '<select id="a_province_code" class="public_area"></select>';
	html += '<select id="a_city_code" class="public_area"></select>';
	html += '<select id="a_area_code" class="public_area"></select>';
	html += '</div>';
	html += '<div class="lineP searchItem  sItem">';
	html += '<label class="lines codeTitle labelW">&nbsp;</label>';
	html += '<div class="lines pItem code" style="width:277px;">';
	html += '<input type="text" placeholder="请输入详细街道、小区及门牌号地址信息" class="pInput" id="a_consignee_address">';
	html += '</div>';
	html += '</div>';
	html += '<div class="lineP searchItem  sItem">';
	html += '<label class="lines codeTitle labelW">&nbsp;</label>';
	html += '<div class="lines pItem code" style="width:100px; margin-right:10px;">';
	html += '<input type="text" placeholder="邮政编码" class="pInput" id="a_zip_code">';
	html += '</div>';
	html += '<div class="lines pItem code" style="width:110px; margin-right:10px;">';
	html += '<input type="text" placeholder="收件人" id="a_consignee" class="pInput">';
	html += '</div>';
	html += '<div class="lines pItem code" style="width:130px;">';
	html += '<input type="text" placeholder="收件人手机号" class="pInput" id="a_consignee_phone">';
	html += '</div>';
	html += '</div>';
	html += '</div>';
	var txt = "为了让您及时收到交费发票，请您登记一下发票收件地址信息";
	var option = {
			title : "为了让您及时收到交费发票，请您登记一下发票收件地址信息",
			btn : parseInt("0011", 2),
			isOkBtn : true,
			onOk : function() {
				if(!$("#a_sub_name").val()){
//				$HXT.showInfo('请填写分组名称！')
					layer.msg("请填写单位名称！", {time:2000});
					return;
				}
				if(!$("#a_province_code").val()){
					layer.msg("请选择省份！", {time:2000});
//				$HXT.showInfo('请选择省份！')
					return;
				}
				if(!$("#a_city_code").val()){
					layer.msg("请选择城市！", {time:2000});
//				$HXT.showInfo('请选择城市！')
					return;
				}
				if(!$("#a_area_code").val()){
					layer.msg("请选择地区！", {time:2000});
//				$HXT.showInfo('请选择地区！')
					return;
				}
				if(!$("#a_consignee_address").val()){
					layer.msg("请填写详细地址！", {time:2000});
//				$HXT.showInfo('请填写详细地址！')
					return;
				}
				if(!$("#a_zip_code").val()){
					layer.msg("请填写邮编！", {time:2000});
//				$HXT.showInfo('请填写邮编！')
					return;
				}
				var reg = /^\d{6}$/;
				if(!reg.test($("#a_zip_code").val())){
					layer.msg("邮编不正确", {time:2000});
//			 	$HXT.showInfo('邮编不正确');
					return;
				}
				if(!$("#a_consignee").val()){
					layer.msg("请填写收件人！", {time:2000});
//				$HXT.showInfo('请填写收件人！')
					return;
				}
				if(!$("#a_consignee_phone").val()){
					layer.msg("请填写收件人手机号！", {time:2000});
//				$HXT.showInfo('请填写收件人手机号！')
					return;
				}
				reg = /^1\d{10}$/;
				if(!reg.test($("#a_consignee_phone").val())){
					layer.msg("收件人手机号码不正确", {time:2000});
//		    	$HXT.showInfo('收件人手机号码不正确');
					return;
				}
				
				var subData = {
						'province_code':$("#a_province_code").val(),
						'city_code':$("#a_city_code").val(),
						'area_code':$("#a_area_code").val(),
						'sub_name': $("#a_sub_name").val(),
						'consignee': $("#a_consignee").val(),
						'consignee_phone': $("#a_consignee_phone").val(),
						'consignee_address': $("#a_consignee_address").val(),
						'zip_code' : $("#a_zip_code").val(),
						'_r':Math.random()
				};
				var url = $("#projectPath").val() + '/ammetermanager/saveHSubCompany';
				$.ajax({
					type : "POST",
					url : url,
					data : subData,
					dataType : "json",
					success : function(data) {
						if (data.status == 'success') {
							layer.msg("添加成功!", {time:2000});
//						$HXT.showInfo('添加成功!')
							window.location.reload();
						} else {
							layer.msg(data.msg, {time:2000});
//						$HXT.showInfo(data.msg);
						}
					}
				});
			},
//		class : [ 'okAddressBox' ]
	}
	
	
	var classArr = ['okAddressBox'];
	window.wxc.xcConfirm(html, "custom", option);
	setXcConfirmClass(classArr);
	var isOkBtn = true;
	if(option.isOkBtn){
		$('.'+classArr[0] + " .popBox .cancel").remove();
	}
	new $gov("a_province_code","a_city_code","a_area_code").init();
	$("."+classArr[0] +" .popBox .sgBtn.ok").html('保存').addClass('notClose');
	$(".tt").css("color","#01a796");
	$('.clsBtn').hide();
	
//	var arrayInput = document.getElementsByTagName("input");
//	for( var i = 0; i < arrayInput.length; i ++){
//		myPlaceholder(arrayInput[i]);
//	}
	
}
//保存更新
function saveHSubCompany() {
	var data = {
			province_code:$("#m_province_code").val(),
			city_code:$("#m_city_code").val(),
			area_code:$("#m_area_code").val(),
			s_id : $("#m_sid").val(),
			sub_name:$("#m_sub_name").val(),
			consignee:$("#m_consignee").val(),
			consignee_phone:$("#m_consignee_phone").val(),
			consignee_address:$("#m_consignee_address").val(),
			zip_code:$("#m_zip_code").val()
	}
    var url = $("#projectPath").val() + '/ammetermanager/saveHSubCompany';
    $.ajax({
        type: "POST",
        url: url,
        data: data,
        dataType: "json",
        success: function (data) {
            if (data.status == 'success') {
                //页面回填
                window.location.reload();
                return true;
            } else {
                $HXT.showInfo(data.msg);
                return false;
            }
        }
    });
}
//绑定
function bindAmmeter(data, ammetertNo,cid,sid) {
	var url = $("#projectPath").val() + '/H5/saveHAmmeterInfo';
	$.ajax({
		type : "POST",
		url : url,
		data : {
			ammeter_no : ammetertNo,
			ammeter_type : "A",
			last_pay_day : "7",
			pay_status : "1",
			proxy : 1,
			c_id : cid,
			s_id : sid,
			ammeter_name : data.resultInfo.accountNamem,
			ammeter_address : data.resultInfo.address
		},
		dataType : "json",
		success : function(data) {
			if (data.status == 'success') {
				$HXT.showInfo('绑定成功！');
				window.location.reload();
			} else {
				$HXT.showInfo(data.msg);
			}
		}
	});
}
function isEmpty(s) {
    return !Boolean(s.replace(/^\s*|\s*$/g, "").length);
}
//删除
function delAmmeter(ammeterNo){
	if(confirm('确认要删除该缴费号？')){
		var url = $("#projectPath").val() + '/ammetermanager/delAmmeter';
		$.ajax({
			type : "POST",
			url : url,
			data : {
				ammeter_no : ammeterNo,
				_r : Math.random()
			},
			dataType : "json",
			success : function(data) {
				if (data.status == 'success') {
					$HXT.showInfo('删除成功！');
					window.location.reload();
				} else {
					$HXT.showInfo(data.msg);
				}
			}
		});
	}
}
//更新电表信息
function updateAmmeter(id, status) {
	var str = status==1?'设为':'取消';
	if(confirm('您确定要'+str+'手机缴费吗?')){
		var _aObj = $("#operat_"+id);
		var url = $("#projectPath").val() + '/ammetermanager/updateProxy';
		$.ajax({
			type: "POST",
			url: url,
			data: {id: id, proxy_flag: status},
			dataType: "json",
			success: function (data) {
				if (data.code == '1') {
					$HXT.showInfo(data.message);
					window.location.reload();
				} else {
					$HXT.showInfo(data.message);
				}
			}
		});
	}
}
//删除分组
var delSid;
function delSubcompany(sid){
	
	if(confirm('如果删除缴费号分组信息，那么该分组下的所有缴费号都将被删除，是否确认删除？')){
		var url = $("#projectPath").val() + '/ammetermanager/delSubcompany';
		$.ajax({
			type: "POST",
			url: url,
			data: {sid: sid},
			dataType: "json",
			success: function (data) {
				if (data.status == 'success') {
//					$HXT.showInfo(data.msg);
					window.location.reload();
				} else {
					$HXT.showInfo(data.msg);
				}
			}
		});
	}
	
//	$(".weui_dialog_confirm").show();
//	delSid = sid;
}

function closeConfirm(){
	$(".weui_dialog_confirm").hide();
}
function suredel(){
	$(".weui_dialog_confirm").hide();
	
}

//获取复核人员信息
function beforePay(payType,subId){
	var url = $("#projectPath").val() + '/hReviewUser/getCompanReviewer';
	$.getJSON(url ,{cid:$("#admin_company_id").val(),_r : Math.random()},function(data){
		if(data.code==1){
			if(data.items){
				pay(data.items,payType,subId);
			}else{
				pay(null,payType,subId);
			}
		}else{
			$HXT.showInfo('系统异常！');
		}
	})
}
function pay(data,payType,subId){
	var title = '重要提示:在完成制单后,请您及时提醒复核员在当天登录网银复核订单,<font>超过当天,可能因为复核超时而导致退款</font>(1-7个工作日退款到账),从而造成缴费失败!';
	var html = '<div class="wItemBox">';
	html += '<div class="lineP searchItem reviewRadioDiv">';
	html += '<div class="in-radio" style="margin-right:20px;">';
	html += '<div class="custom-radio"><input type="radio" name="reviewRadio" id="RadioGroup1_1" class="checkedFocus" value="1" checked="checked"><label for="RadioGroup1_1" class="checked">';
	html += '<span>请短信提醒复核员在当天复核</span>';
	html += '</label></div>';
	html += '</div>';
	html += '<div class="in-radio">';
	html += '<div class="custom-radio"><input type="radio" name="reviewRadio" id="RadioGroup1_2" class="checkedFocus" value="0"><label for="RadioGroup1_2" class="">';
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
	html += '</div>'
	if(!data){
		html += '<label class="lines codeTitle" style="color:#01a796;" onclick="saveReviewer('+payType+','+subId+');" id="saveReviewLabel">保存</label>';
	}
	html += '</div>';
/*	html += '<div class="lineP searchItem  sItem payTypeDiv">';
	html += '<div class="in-checkbox">';
	html += '<div class="custom-checkbox"><input type="checkbox" name="bankCheck" id="checkbox_a" checked="checked"><label for="checkbox_a" class="checked">';
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
			if($(obj).attr('id')!=_this.attr("id")){
				$(obj)[0].checked = false;
				$(obj).next().attr('class','');
			}
		});
	});
	$("#checkbox_a").next().attr('class','checked');//初始化默认checkbox
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
    var reviewRadio ;
	$(".reviewRadioDiv").find('label').each(function(index,obj){
		if($(obj)[0].className.indexOf('checked')!=-1){
			reviewRadio = $("#"+$(obj).attr('for')).val()
		}
	});
	if(reviewRadio==1){
		var reviewerName = $("#reviewerName").val();
		var reviewerSex= $("#reviewerSex").val();
		var reviewerPhone = $("#reviewerPhone").val();
		if(!reviewerName){
			layer.msg("请填写复核人员姓名！", {time:2000});
//			$HXT.showInfo("请填写复核人员姓名。");
			return true;
		}
		if(!reviewerSex){
			layer.msg("请选择复核人员性别！", {time:2000});
//			$HXT.showInfo("请选择复核人员性别。");
			return true;
		}
		if(!reviewerPhone){
			layer.msg("请填写复核人员手机号！", {time:2000});
//			$HXT.showInfo("请填写复核人员手机号。");
			return true;
		}
	}
    var payType1 = 1;
    var payType2 = 0;
   /* $(".payTypeDiv").each(function(index,obj){
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
    var ready = checkPayOrderParam();
    if(ready){
    	$.ajax({
    		type: "GET",
    		url: url,
    		dataType: "json",
    		success: function (data) {
    			if (data.status == 'success') {
    				var url = $("#projectPath").val() + '/pay/toMiddle';
    				var formStr = '<form method="get" action="' + url + '">';
    				formStr += '<input type="hidden" value="'+reviewRadio+'" name="reviewRadio"/>';
    				formStr += '<input type="hidden" value="'+reviewerName+'" name="reviewerName"/>';
    				formStr += '<input type="hidden" value="'+reviewerSex+'" name="reviewerSex"/>';
    				formStr += '<input type="hidden" value="'+reviewerPhone+'" name="reviewerPhone"/>';
    				formStr += '<input type="hidden" value="'+payType1+'" name="payType1"/>';
    				formStr += '<input type="hidden" value="'+payType2+'" name="payType2"/>';
    				if(payType==2){
    					formStr += '<input type="hidden" value="2" name="payType"/>';
    					formStr += '<input type="hidden" value="'+subId+'" name="subId"/>';
    				}else{
    					formStr += '<input type="hidden" value="1" name="payType"/>';
    				}
    				formStr += '</form>';
    				var $tempForm = $(formStr);
    				$("body").append($tempForm);  
    				$tempForm.submit();  
    				$tempForm.remove();  
    			} else {
    				layer.msg(data.msg, {time:2000});
//    				$HXT.showInfo(data.msg);
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
	var reviewRadio ;
	$(".reviewRadioDiv").find('label').each(function(index,obj){
		if($(obj)[0].className.indexOf('checked')!=-1){
			reviewRadio = $("#"+$(obj).attr('for')).val()
		}
	});
	if(reviewRadio==1){
		var reviewerName = $("#reviewerName").val();
		var reviewerSex= $("#reviewerSex").val();
		var reviewerPhone = $("#reviewerPhone").val();
	    if(!reviewerName){
	    	$HXT.showInfo('请完整填写复核人员信息！');
	    	return res;
	    }
	    if(!reviewerSex){
	    	$HXT.showInfo('请完整填写复核人员信息！');
	    	return res;
	    }
	    if(!reviewerPhone){
	    	$HXT.showInfo('请完整填写复核人员信息！');
	    	return res;
	    }
	    var reg = /^1\d{10}$/;
	    if(!reg.test(reviewerPhone)){
	    	$HXT.showInfo('复核人员手机号码不正确');
	    	return res;
	    }
	}
    var payType1 = 1;
    var payType2 = 0;
   /* $(".payTypeDiv").each(function(index,obj){
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
    if(!reviewRadio){
    	$HXT.showInfo('请选择是否给复核人员发送短信！');
    	return res;
    }
    if(payType1!=1&&payType2!=2){
    	$HXT.showInfo('请选择支付方式！');
    	return res;
    }
    return true;
}
function payInfo(companyId, companyName) {
	var sendData = {
		'companyName' : companyName ? companyName : '',
		'companyId' : companyId ? companyId : ''
	}
	var url = $("#projectPath").val() + '/pay/getPayurl';
	$.ajax({
			type : "POST",
			url : url,
			data : sendData,
			dataType : "json",
			success : function(data) {
				var resultData = eval(data);
				if (resultData.status == 'success') {
					var datas = JSON.parse(resultData.data);
					$("#checkUrl").html(datas.check_url);
					$("#checkNo").html(datas.check_no);
					var title = '转交财务交费';
					var html = '<div class="reBoxMsg">请您在请款流程完成后，将下述付款链接信息复制后邮件给财务付款人员付款：</br>';
					html += '财务部您好，截止到 '+$("#nowDate").val()+'，我单位的电费欠费共计：'+$fee+' 元请登录下述网址完成付款：</br>'
					html += '付款网址：'+datas.check_url+'</br>'
					html += '付款验证码：'+datas.check_no+'</br>'
					html += '验证码有效期截止到：'+$("#nowDate").val()+' 21:00:00</div>'
					var option = {
						title : title,
						btn : parseInt("0011", 2),
						isOkBtn : true,
						onCancel : function() { // 下一步回调函数

						},
//						class : [ 'okAddressBox', 'on', 're' ]
					}

					var classArr = ['okAddressBox','on','re'];
					window.wxc.xcConfirm(html, "custom", option);
					setXcConfirmClass(classArr);
					
					$("."+classArr[1] +" .popBox .sgBtn.ok").html('确定');
					$('.'+classArr[1] + " .popBox .cancel").remove();
					$('.popBox').css('width','625px');
				} else {
					$HXT.showInfo(resultData.msg);
				}
			}
		});
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
//                beforePay(payType,subId);
            } else {
            	layer.msg(data.message, {time:2000});
            }
        }
    });
}
var upAid;
var upflag = true;
/*function upShowBillImg(id){
	if($("#file"+id).html()=='上传缴费单'){
		if(upflag){
			upflag=false;
			upAid = id;
			upload($("#projectPath").val(),"file"+id,upload_call_back1,'','','pic');
			setTimeout(function(){
				var obj1 = $("#upShowBillImg_"+id);
				obj1.click();
				$("#upShowBillImg_"+id).find("#file"+id).find('label').each(function(index,obj){
					console.log(123)
					if(index==0){
						console.log('click...')
						$(obj).click();
					}
				})
			},500)
			upflag = true;
		}
	}
}*/
function showBillImg(id){
	var path = "";
	var url =  $("#projectPath").val() + '/public/showBillImg';
	$.ajax({
	        type: "POST",
	        url: url,
	        data: {
	        	aid : id,
	        	_r:Math.random()
	        },
	        dataType: "json",
	        success: function (data) {
	            if (data.code == '1') {
	            	path = data.items.bill_img;
	            	if(path){
	            		layer.open({
	            			title:'缴费单查看',
	            			type: 1,
	            			skin: 'layui-layer-demo', //样式类名
	            			closeBtn: 0, //不显示关闭按钮
	            			shift: 2,
	            			shadeClose: true, //开启遮罩关闭
	            			area : ['800px','647px'],
	            			content: '<img style="width:800px;height:600px;" src="'+$("#projectPath").val()+path+'"/>'
	            		});
	            	}else{
	            		layer.msg('未找到缴费单', {time:2000});
	            	}
	            } else {
	            	layer.msg("数据错误！", {time:2000});
	            }
	        }
	});
}
function upload_call_back1(id,response){
	upAid = id;
	if(response.code == '1'){
		var fileList = response.list;
		if(fileList.length == 1){
			var newFileName = fileList[0].newName;
			//保存图片
			var url =  $("#projectPath").val() + '/public/upBillImg';
			 $.ajax({
			        type: "POST",
			        url: url,
			        data: {
			        	aid : upAid,
			        	bill_img : newFileName,
			        	_r:Math.random()
			        },
			        dataType: "json",
			        success: function (data) {
			        	$(".loading").remove();
			            if (data.code == '1') {
			                layer.msg("保存成功！", {time:2000});
			            } else {
			            	layer.msg(data.message, {time:2000});
			            }
			        }
			    });
			$("#upShowBillImg_"+upAid).html('查看缴费单');
			$("#upShowBillImg_"+upAid).attr('onclick','showBillImg('+upAid+')');
			$("#upShowBillImg_"+upAid).attr('id','showBillImg_'+upAid);
			$('#showBillImg_'+upAid).css('color','#01a796');
		}
	}else{
		$HXT.showInfo("上传异常");
	}
}
$(document).on('mouseenter',".fz_title",function(){
	$(this).find(".fzRem").show();	
});
$(document).on("mouseout",".fz_title",function(){
	$(this).find(".fzRem").hide();	
});