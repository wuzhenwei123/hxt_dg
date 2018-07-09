$(document).ready(function(){
//	$(".weui_loading_toast").show();
	$HXT.loading('努力加载中');
	getData();
});
//获取发票电表信息
function getData() {
    var url = $("#projectPath").val() + '/ammetermanager/getInvoceAmmeterInfoByCId?cId=' + $("#cId").val()+'&_t='+Math.random();
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
function titleStr(cid,cname,count,fee){
	var str = "";
	str += '<div class="commBody jyBox">';
	str += '<div class="pTitle">代扣缴费号设置</div>';
	str += '</div>';
	return str;
}
//发票信息处理
var dataArr;
function dataCh(myData) {
    // 发票信息 数据处理
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
        	str += '<td>'+pay_statu+'</td>';
        	if (ammeters[j].proxy_flag == '0') {
        		str += '<td class="yjBtnTdBox"><a href="javascript:updateAmmeter('+eval(ammeters[j]).a_id+',1)" id="operat_'+eval(ammeters[j]).a_id+'">代扣</a></td>';
        	}
        	if (ammeters[j].proxy_flag == '1') {
        		str += '<td class="yjBtnTdBox"><a href="javascript:updateAmmeter('+eval(ammeters[j]).a_id+',0)" id="operat_'+eval(ammeters[j]).a_id+'">取消代扣</a></td>';
        	}
        	str += '</tr>'
        }
        str += '</table>';
        str += '</div>';
        str += '</div>';//外层结束
//        if(infoFlag){
//        	showModify(obj[i].s_id);
//        }
    }
    if(!obj.length){
//    	showDetail4();
    }
    str = titleStr(obj[0]?obj[0].c_id:'',obj[0]?obj[0].c_name:'',ammetercount,truncFee(myData.all_total_fee))+str;
    $fee = truncFee(myData.all_total_fee);
    $(".rightBox").append(str);
    $('input').placeholder();
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
//更新电表信息
function updateAmmeter(id, status) {
	var str = status==1?'设置代扣':'取消代扣';
	if(confirm('您确定要给该缴费号'+str+'吗?')){
		var _aObj = $("#operat_"+id);
		var url = $("#projectPath").val() + '/ammetermanager/updateProxy';
		$.ajax({
			type: "POST",
			url: url,
			data: {id: id, proxy_flag: status},
			dataType: "json",
			success: function (data) {
				if (data.code == 1) {
					$HXT.showInfo("设置成功！");
					window.location.reload();
				} else {
					$HXT.showInfo(data.message);
				}
			}
		});
	}
}

$(document).on('mouseenter',".fz_title",function(){
	$(this).find(".fzRem").show();	
});
$(document).on("mouseout",".fz_title",function(){
	$(this).find(".fzRem").hide();	
});