/*非空判断*/

function isEmpty(s) {
    return !Boolean(s.replace(/^\s*|\s*$/g, "").length);
}
// 电话验证
function f_phone(obj) {
    var regMobile = /^1[3|4|5|6|7|8|9][0-9]{1}[0-9]{8}$/;
    if (regMobile.test(obj)) {
        return true;
    } else {
        return false;
    }
}
//新增企业子公司
function addNewSubCompany(){
    $("#sub_id").val("");//子单位id
    $("#invoice_title").val(""); //发票台头名称
    $("#sub_name").val(""); //子单位名称：
    $("#consignee").val(""); // 发票收件人姓名
    $("#consignee_phone").val(""); //发票收件人手机
    $("#consignee_tel1").val(""); // 收件人固定电话
    $("#consignee_tel2").val(""); // 收件人固定电话分机号
    $("#consignee_address").val("");  // 发票邮寄地址
    $("#lightB").show();
}
//添加企业子公司（发票）
function addSubCompany(cId) {
    //var c_id;//公司ID
    //var sub_name;子公司名称
    //var invoice_title;//发票抬头
    //var consignee;//收件人
    //var consignee_phone;//收件人电话
    //var consignee_tel;//手机人座机
    //var consignee_address;//收件人地址

    var companyName = document.getElementById("companyName").value;  // 公司名称
    var invoice_title = document.getElementById("invoice_title").value; //发票台头名称
    var sub_name = document.getElementById("sub_name").value; //子单位名称：
    var s_id = document.getElementById("sub_id").value; //子单位id：
    var consignee = document.getElementById("consignee").value; // 发票收件人姓名
    var consignee_phone = document.getElementById("consignee_phone").value; //发票收件人手机
    var consignee_tel1 = document.getElementById("consignee_tel1").value; // 收件人固定电话
    var consignee_tel2 = document.getElementById("consignee_tel2").value; // 收件人固定电话分机号
    var consignee_address = document.getElementById("consignee_address").value;  // 发票邮寄地址

    if (isEmpty(invoice_title)) {
        alert("发票抬头不能为空！");
        return false;
    }
    if (isEmpty(consignee)) {
        alert("发票收件人不能为空！");
        return false;
    }
    if (isEmpty(consignee_phone)) {
        alert("发票收件人手机号码不能为空！");
        return false;
    } else {
        if (!f_phone(consignee_phone)) {
            alert("发票收件人手机号码格式不正确！");
            return false;
        }
    }
    if (isEmpty(consignee_address)) {
        alert("发票收件人地址不能为空！");
        return false;
    }


    var saveData = {
        'companyName': companyName ? companyName : '',
        'invoice_title': invoice_title ? invoice_title : '',
        'sub_name': sub_name ? sub_name : '',
        's_id': s_id ? s_id : '',
        'consignee': consignee ? consignee : '',
        'consignee_phone': consignee_phone ? consignee_phone : '',
        'consignee_tel1': consignee_tel1 ? consignee_tel1 : '',
        'consignee_tel2': consignee_tel2 ? consignee_tel2 : '',
        'consignee_address': consignee_address ? consignee_address : '',
        'c_id': cId ? cId : ""
        //'s_id' :''

    }


    var str = '';
    str += '<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0" class="bk_xf tableTop">';
    str += '<tr>';
    str += '<td width="80%" height="70" class="heizi12">';

    str += '<p><strong>子单位名称：</strong>：' + saveData.sub_name + '</p>';
    str += '<p><strong>发票台头</strong>：' + saveData.invoice_title + '</p>';
    str += '<p><strong>发票收件人：</strong>' + saveData.consignee;
    str += '<strong>手机：</strong>' + saveData.consignee_phone + '</p>';
    str += '<p><strong>收件人地址：</strong>' + saveData.consignee_address+'</p>';
    str += '</td>';
    str += '<td width="20%" align="center"><a href="#" onclick="getSubCompany(' + saveData.s_id + ');" class="lj_bc_14">变更此资料</a></td>';

    str += '</tr></table>';

    str += '<table width="96%" cellspacing="0" cellpadding="0" border="0" align="center" class="dianbiao">';
    str += '<tr>';
    str += '<td height="35" align="center" class="heizi12"><strong>序号</strong></td>';
    str += '<td align="center" class="heizi12"><strong>业务类型</strong></td>';
    str += ' <td align="center" class="heizi12"><strong>缴费号</strong></td>';
    str += '<td align="center" class="heizi12"><strong>用电信息</strong></td>';
    str += '<td align="center" class="heizi12"><strong>缴费状态</strong></td>';
    //str += '<td width="14%" align="center"><a class="lj_bc_14 lj_bc_14_list" href="javascript:void(0);">新增</a></td>';
	str += '<td width="14%" align="center">操作></td>';
    str += '</tr>';
    str += '</table>';

    saveHSubCompany(saveData, str);

}
$(function () {


    $("td .lj_bc_14_list").live("click", function () {
        var str = '';
        var obj = $(this).parent().parent().parent();

        var num = obj.find("tr").length;

        str += '<tr align="center">';
        str += ' <td width="10%" height="30" class="heizi12 ammeter_num">' + num + '</td>';
        str += '<td width="10%" class="heizi12 ammeter_type" value＝"A">抄表电</td>';
        str += '<td width="10%" class="heizi12" value=""><span class="hz14">';
        str += '<input type="text" maxlength="70" class="srk03 ammeter_no" name="username8"> </input>';
        str += '</span></td>';
        str += '<td width="10%" class="heizi12 ammeter_name">&nbsp;</td>';
        str += '<td width="10%" class="heizi12 pay_status"></td>';
        str += '<td align="center">';
        str += '<a href="javascript:void(0);" id="sotp_star_btn">保存</a>';
        str += '</td>';
        str += ' </tr>';


        obj.append(str);
        str = '';
    });
    getData();

    // 保存 、暂停 、 恢复
    $("#sotp_star_btn").live("click", function () {

        //按钮设置
        showLoading();
        var text = $(this).html();

        switch (text) {

            case "保存":
                //保存的数据处理;
                checkAmmeter(this);
                break;

            case "暂停":
                //暂停的数据处理;
                updateAmmeter(this, "0");
                break;
            case "恢复":
                //恢复的数据处理;
                updateAmmeter(this, "1");
                break;
        }
		


    });

    $("#closebt").click(function () {
        document.getElementById("lightB").style.display = "none";
    });
    $("#closebt,#guanbiA").click(function () {
        document.getElementById("lightAmmeter").style.display = "none";
    });

});

//获取发票信息
function getSubCompany(sId) {
	
	showLoading();
    var url = $("#projectPath").val() + '/ammetermanager/getHSubCompanyBySId?sId=' + sId;
    $.ajax({
        type: "GET",
        url: url,
        dataType: "json",
        success: function (data) {
			hideLoading();
            if(data.status=='success'){
				
                var datas= JSON.parse(data.data);
                $("#sub_id").val(datas.s_id);//子单位id
                //$("#companyName").val(datas.companyName);  // 公司名称
                $("#invoice_title").val(datas.invoice_title); //发票台头名称
                $("#sub_name").val(datas.sub_name); //子单位名称：
                $("#consignee").val(datas.consignee); // 发票收件人姓名
                $("#consignee_phone").val(datas.consignee_phone); //发票收件人手机
                $("#consignee_tel1").val(datas.consignee_tel1); // 收件人固定电话
                $("#consignee_tel2").val(datas.consignee_tel2); // 收件人固定电话分机号
                $("#consignee_address").val(datas.consignee_address);  // 发票邮寄地址
                $("#lightB").show();
            }else{
                alert(data.msg);
            }
			
        }
    });

}

//获取发票电表信息
function getData() {
	showLoading();
    var url = $("#projectPath").val() + '/ammetermanager/getInvoceAmmeterInfoByCId?cId=' + $("#cId").val();
    $.ajax({
        type: "GET",
        url: url,
        dataType: "json",
        success: function (data) {
			hideLoading();
            dataCh(data);
        }
    });
}
//保存子公司信息（发票信息）
function saveHSubCompany(data, str) {

//	$("#s_id").val(121212);
    //var c_id;//公司ID
    //var sub_name;子公司名称
    //var invoice_title;//发票抬头
    //var consignee;//收件人
    //var consignee_phone;//收件人电话
    //var consignee_tel1;//收件人座机
    //var consignee_tel2;//收件人分机号
    //var consignee_address;//收件人地址
    var url = $("#projectPath").val() + '/ammetermanager/saveHSubCompany';
    $.ajax({
        type: "POST",
        url: url,
        data: data,
        dataType: "json",
        success: function (data) {
            if (data.status == 'success') {
                //页面回填
                var id = document.getElementById("id").value;
                if (!(id.length > 0)) {
                    $("#listBox").append(str);
                }

                $("#lightB").hide();
                document.getElementById('lightB').style.display = "none";

                $("#s_id").val(data.s_id);
                alert(data.msg);
                window.location.reload();
                return true;
            } else {
                alert(data.msg);
                return false;
            }
        }
    });
}
//保存电表信息
function saveAmmeter(datas, ammetertNo, ammeterName,xuhao) {

    var url = $("#projectPath").val() + '/ammetermanager/saveHAmmeterInfo';
    var s_id = $(datas).parent().find("td input.s_id").val();
    var c_id = $(datas).parent().find("td input.c_id").val();
	//console.info();
    //return;
    $.ajax({
        type: "POST",
        url: url,
        data: {
            ammeter_no: ammetertNo,
            ammeter_type: "A",
            last_pay_day: "7",
            pay_status: "1",
            c_id: c_id,
            s_id: s_id,
            ammeter_name: ammeterName
        },
        dataType: "json",
        success: function (data) {
            if (data.status == 'success') {
                $(datas).find("td span input").readonly = true;
                $(datas).find("td.ammeter_name").html(ammeterName);
                $(datas).find("td.pay_status").html("正常");
                $(datas).find("td span input").attr("readonly", "readonly");
                $(datas).find("td span input").attr("class", "srk03 myInput ammeter_no");
                $(datas).find("td a.sotp_star_btn").html("暂停");
				
                //alert(data.msg);

				var str = getTr(xuhao);
				$(datas).parent().append(str);
                //showDetail(ammetertNo);
                var ammetercount = $("#ammetercount").html();
                $("#ammetercount").html(Number(ammetercount)+1);
                //showDetail(datas,ammetertNo,xuhao,1);
                //$(obj).html='暂停';
                window.location.href=window.location.href;
            } else {
                alert(data.msg);
            }
        }
    });
}

//更新电表信息
function updateAmmeter(obj, status) {
    //$(obj).html("恢复");
    var datas = $(obj).parent().parent();
    var aId = datas.find("td span input.a_id").val();
    var url = $("#projectPath").val() + '/ammetermanager/updateHAmmeterInfoStatus';
    $.ajax({
        type: "POST",
        url: url,
        data: {a_id: aId, pay_status: status},
        dataType: "json",
        success: function (data) {
			hideLoading();
            if (data.status == 'success') {
                alert(data.msg);
                if (status == 1) {
                    $(obj).html("暂停");
                } else {
                    $(obj).html("恢复");
                }
                window.location.reload();
            } else {
                alert(data.msg);
            }
        }
    });
}

//校验电表是否存在
function checkAmmeter(obj) {
    var datas = $(obj).parent().parent();
    var ammetertNo = datas.find("td span input").val();
    var xuhao = datas.find("td span.ammeter_num").html();
    if (isEmpty(ammetertNo)) {
        alert("缴费号不能为空！");
        hideLoading();
        return false;
    }
    var reg = /^[\d.\-_]+$/;
	if(!reg.test(ammetertNo)){
		alert("请您输入正确的缴费号");
		hideLoading();
		return false;
	}
    var ammetertName = datas.find("td.ammeter_name");
    var url = $("#projectPath").val() + '/hCommon/getAmmeterInfo?electricNum=' + ammetertNo;
    $.ajax({
        type: "GET",
        url: url,
        //     data: {username:$("#username").val(), content:$("#content").val()},
        dataType: "json",
        success: function (data) {
			hideLoading();
            if (data.status == 'success') {
                if (eval(data.data).resultCode == '00') {//电表存在
                    var name = eval(eval(data.data).resultInfo).accountName;
                    //saveAmmeter(datas, ammetertNo, name,xuhao);
                    showDetail(datas,ammetertNo,xuhao);
                    //ammetertName.html(name);
					
                } else {
                    alert("该电表不存在，请检查重新输入！");
                }
            } else {
                alert(data.msg);
            }
            // var dataArr = data;
            //	 console.info(dataArr);
            //	 for(var i =0 ; i < dataArr.length; i ++ ){

            // console.info(dataArr[i]);
            //	}


        }
    });
}
// 发票信息处理
function dataCh(myData) {
//	console.info(data);
//    console.info(myData);

    //var data = eval(myData);
    // 发票信息 数据处理
    var str = '';


    var obj = eval(myData.data);
    //console.info(obj[0]);
    //console.info(obj[1]);
    //return false;
	
    var ammetercount=0;//电表数
	var flagStr = '';
    for (var i = 0; i < obj.length; i++) {
        str += '<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0" class="bk_xf tableTop">';
        str += '<tr>';
        str += '<td width="80%" height="70" class="heizi12">';
        str += '<strong class="s_id" style="display:none;">' + obj[i].s_id + '</strong>';
        str += '<strong class="c_id" style="display:none;">' + obj[i].c_id + '</strong>';
        str += '<strong>子单位名称：</strong>' + obj[i].sub_name;
        str += '<strong>　发票台头</strong>：' + obj[i].invoice_title + '</br>';
        str += '<strong>发票收件人：</strong>' + obj[i].consignee + '&nbsp;&nbsp;';
        str += '<strong>　手机：</strong>' + obj[i].consignee_phone;
        str += '<strong>　收件人地址：</strong>' + obj[i].consignee_address;
        str += '</td>';
        str += '<td width="20%" align="center"><a href="#" onclick="getSubCompany('+obj[i].s_id+');" class="lj_bc_14">变更此资料</a></td>';
        str += '</tr>';
        str += '</table>';
        var ammeters = eval(obj[i]).ammeters;//电表信息
        var ammeterNo = ammeters.ammeter_no;//电表号


        /// 电表信息
        str += '<table width="96%" cellspacing="0" cellpadding="0" border="0" align="center" class="dianbiao">';
        str += '<tr>';
        str += '<td height="35" align="center" class="heizi12"><strong>序号</strong></td>';
        str += '<td align="center" class="heizi12"><strong>业务类型</strong></td>';
        str += ' <td align="center" class="heizi12"><strong>缴费号</strong></td>';
        str += '<td align="center" class="heizi12"><strong>用电客户名称</strong></td>';
        str += '<td align="center" class="heizi12"><strong>缴费状态</strong></td>';
        str += '<td width="14%" align="center">';
        str += '<input class="s_id" type="hidden" value="' + obj[i].s_id + '"/>';
        str += '<input class="c_id" type="hidden" value="' + obj[i].c_id + '"/>';
        //str += '<a class="lj_bc_14 lj_bc_14_list" href="javascript:void(0);">新增</a>';
	   str += '操作';
        str += '</td>';
        str += '</tr>';

        var ammeter_type = "";
        var pay_statu = '';
        var bt_status = "";
		var num1 = 0;
        for (var j = 0; j < ammeters.length; j++) {
            ammetercount++;
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

            str += '<tr align="center">';
            str += ' <td width="10%" height="30" class="heizi12"><span class="hz14 ammeter_num">'+eval(j + 1)+'</span></td>'
            str += '<td width="10%" class="heizi12">' + ammeter_type + '</td>';
            str += '<td  align="center"  width="10%" class="heizi12"><span class="hz14">';
            str += '<input class="a_id" type="hidden" value="' + ammeters[j].a_id + '"/>';
            str += '<input type="text" maxlength="70" value="' + eval(ammeters[j]).ammeter_no + '" class="srk03 myInput ammeter_no" readonly="readonly" name="username[]">';
            str += '</span></td>';
            str += '<td  align="center" width="10%" class="heizi12">' + eval(ammeters[j]).ammeter_name + '</td>';
            str += '<td width="10%" class="heizi12">' + pay_statu + '</td>';
            str += '<td align="center">';
            str += '<a href="javascript:void(0);" id="sotp_star_btn">' + bt_status + '</a>';
            str += '</td>';
            str += ' </tr>';
            //5
			num1 ++ ;
        }
		flagStr = getTr(num1);
		str += flagStr;
        str += '</table>';
		str += '<div class="borderNone"></div>';
		
		flagStr = '';
		num1 = 0;

    }


    $("#ammetercount").html(ammetercount);
    $("#listBox").append(str);
	
	

}
function getTr(num){
    num = Number(num);
	//var that = obj1;
	 var str = '';
      //  var obj = $(that).parent().parent().parent();

  //      var num = obj.find("tr").length;
		var num1 = eval(num+1);
		if( eval(num1) <= 1 ){
			num1 = 1
		}
		
			
        str += '<tr align="center">';
        str += ' <td width="10%" height="30" class="heizi12"><span class="hz14 ammeter_num">'+num1+'</span></td>'
        str += '<td width="10%" class="heizi12 ammeter_type" value＝"A">抄表电</td>';
        str += '<td width="10%" class="heizi12" value=""><span class="hz14">';
        str += '<input type="text" maxlength="70" class="srk03 ammeter_no" name="username8"> </input>';
        str += '</span></td>';
        str += '<td width="10%" class="heizi12 ammeter_name">&nbsp;</td>';
        str += '<td width="10%" class="heizi12 pay_status"></td>';
        str += '<td align="center">';
        str += '<a href="javascript:void(0);" id="sotp_star_btn">保存</a>';
        str += '</td>';
        str += ' </tr>';
		return str;


   //     obj.append(str);
      //  str = '';
}

// show loding
function showLoading(){
	$("#bgColor").show();
	$("#loding").show();
}
function hideLoading(){
	$("#bgColor").hide();
	$("#loding").hide();
}

function showDetail(datas, ammetertNo,xuhao,scene){
    // 详情
        var url = $("#projectPath").val()+'/hCommon/getAmmeterInfo?electricNum=' + ammetertNo;
        $.ajax({
            type: "GET",
            url: url,
            //     data: {username:$("#username").val(), content:$("#content").val()},
            dataType: "json",
            success: function (data) {
//              hideLoading();
                if (data.status == 'success') {
                	$('#guanbiA').unbind("click");
                    if (eval(data.data).resultCode == '00') {//电表存在
                        var resultData = eval(data.data);
                        var resultInfo = eval(resultData.resultInfo);
                        var name = eval(resultData.resultInfo).accountName;
                        $("#ammeter_type").html("抄表电");
                        $("#ammeter_no").html(ammetertNo);
                        $("#ammeter_name").html(resultInfo.accountName);
                        $("#accountFee").html(resultInfo.accountFee);//实际支付金额
                        $("#lateFee").html(resultInfo.lateFee);//滞纳金
                        $("#totalfee").html(resultData.totalFee);
                  //saveAmmeter(datas, ammetertNo, name);
//                        saveAmmeter(datas, ammetertNo, name,xuhao);
                        //ammetertName.html(name);
                        $("#lightAmmeter").show();
                        $('#guanbiA').click(function () {
                        	saveAmmeter(datas, ammetertNo, name,xuhao);
                        	document.getElementById("lightAmmeter").style.display = "none";
                            if(scene==1){
                                window.location.href=window.location.href;
                            }
                        });
                        $('#guanbiB').click(function () {
                            document.getElementById("lightAmmeter").style.display = "none";
                        });
                    } else {
                        //alert("该电表不存在，请检查重新输入！");
                    }
                } else {
                    //alert(data.msg);
                }


            }
        });

}