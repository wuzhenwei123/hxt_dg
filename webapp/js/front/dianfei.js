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
        str += '<td align="right">';
        str += '<a class="lj_bc_14 detail_star_btn" href="javascript:void(0);">详情</a>';
        str += '</td>';
        str += ' </tr>';


        obj.append(str);
        str = '';
    });
    getData();

    // 详情
    $(".detail_star_btn").live("click", function () {

        var obj = $(this).parent().parent();
        var ammeterNo =  $(obj).find("td.ammeter_no").html();
        var ammeterType =  $(obj).find("td.ammeter_type").html();

        var url = $("#projectPath").val()+'/hCommon/getAmmeterInfo?electricNum=' + ammeterNo;
        $.ajax({
            type: "GET",
            url: url,
            //     data: {username:$("#username").val(), content:$("#content").val()},
            dataType: "json",
            success: function (data) {
//              hideLoading();
                if (data.status == 'success') {
                    if (eval(data.data).resultCode == '00') {//电表存在
                        var resultData = eval(data.data);
                        var resultInfo = eval(resultData.resultInfo);
                        var name = eval(resultData.resultInfo).accountName;
                        var obj = $(this).parent().parent();
                        $("#ammeter_type").html(ammeterType);
                        $("#ammeter_no").html(ammeterNo);
                        $("#ammeter_name").html(resultInfo.accountName);
                        $("#accountFee").html(resultInfo.accountFee);//实际支付金额
                        $("#lateFee").html(resultInfo.lateFee);//滞纳金
                        $("#totalfee").html(resultData.totalFee);
//                  saveAmmeter(datas, ammetertNo, name);
                        //ammetertName.html(name);
                        $("#lightB").show();

                    } else {
                        alert("该电表不存在，请检查重新输入！");
                    }
                } else {
                    alert(data.msg);
                }


            }
        });


    });
    $("#closebt,#guanbi").live("click",function () {
        document.getElementById("lightB").style.display = "none";
    });


    $("#closebt").click(function () {
        document.getElementById("lightB").style.display = "none";
    });

});

//获取发票信息
function getSubCompany(sId) {

    var url = $("#projectPath").val() + '/ammetermanager/getHSubCompanyBySId?sId=' + sId;
    $.ajax({
        type: "GET",
        url: url,
        dataType: "json",
        success: function (data) {
            if (data.status == 'success') {
                var datas = JSON.parse(data.data);
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
            } else {
                alert(data.msg);
            }
        }
    });

}

//获取发票电表信息
function getData() {
	//showLoading();
    var url = $("#projectPath").val() + '/ammetermanager/getInvoceAmmeterPayInfoByCId?cId=' + $("#cId").val();
    $.ajax({
        type: "GET",
        url: url,
        dataType: "json",
        success: function (data) {
			
            dataCh(data,1);
        }
    });
}

// 发票信息处理
var dataArr;
function dataCh(myData,myType) {
	if(myType == 1){
		hideLoading();
	} else {
		showLoading();
	}
//	console.info(data);
//    console.info(myData);

    //var data = eval(myData);
    // 发票信息 数据处理
    var str = '';


//	alert(myData.data.length);
    //console.info(obj[0]);
    //console.info(obj[1]);
    //return false;
    //if(dataArr  == null){
    dataArr = eval(myData);
    //}
    //console.info(datAarr);
    var ammetercount = 0;//电表数
    var ammeterSum = dataArr.ammeterSum ? dataArr.ammeterSum : 0;
    var owedAmmeterSum = dataArr.owedAmmeterSum ? dataArr.owedAmmeterSum : 0;
    var paysum = dataArr.paysum ? dataArr.paysum : "0";
    var totalFee = parseInt(paysum,0);
    if(totalFee>0){
		if((paysum+"").length>2){
			var totalFeeStr="";
			var fen = totalFee%100;
			if(parseInt(fen,0)>0&&parseInt(fen,0)<10){
				totalFeeStr = Math.floor(totalFee/100) + ".0" + fen;
			}else if(parseInt(fen,0)>=10){
				totalFeeStr = Math.floor(totalFee/100) + "." + fen;
			}else{
				totalFeeStr = Math.floor(totalFee/100) + ".00";
			}
			$("#paysum").html(totalFeeStr);
			$("#paysum1").html(totalFeeStr);
		}else if((paysum+"").length==1){
			$("#paysum").html("0.0"+totalFee);
			$("#paysum1").html("0.0"+totalFee);
		}else{
			$("#paysum").html("0."+totalFee);
			$("#paysum1").html("0."+totalFee);
		}
	}else{
		$("#paysum").html("0.00");
		$("#paysum1").html("0.00");
	}
    $("#ammeterSum").html(ammeterSum);
    $("#owedAmmeterSum").html(owedAmmeterSum);
    var num = dataArr.rowsTotal;
    var obj = eval(dataArr.data);
    var i = $("#page").val()-1;//页面数据号


    //  for (var i = 0; i < obj.length; i++) {
    str += '<table width="96%" border="0" align="center" id="ammeterTable" cellpadding="0" cellspacing="0" class="bk_xf">';
    str += '<tr>';
    str += '<td width="86%" height="70" class="heizi12">';
    str += '<strong class="s_id" style="display:none;">' + obj[i].s_id + '</strong>';
    str += '<strong class="c_id" style="display:none;">' + obj[i].c_id + '</strong>';
    str += '<strong>子单位名称：</strong>' + obj[i].sub_name;
    str += '<strong>　发票台头</strong>：' + obj[i].invoice_title;
    str += '<strong>　发票收件人：</strong>' + obj[i].consignee + '<br />';
    str += '<strong>手机：</strong>' + obj[i].consignee_phone;
    str += '<strong>　收件人地址：</strong>' + obj[i].consignee_address;
    str += '</td>';
    //str += '<td width="14%" align="right"><a href="#" onclick="getSubCompany('+obj[i].s_id+');" class="lj_bc_14">变更此资料</a></td>';
    str += '</tr>';
    str += '</table>';
    var ammeters = eval(obj[i]).ammeters;//电表信息
    var ammeterNo = ammeters.ammeter_no;//电表号


    /// 电表信息
    str += '<table width="96%" cellspacing="0" cellpadding="0" border="0" align="center">';
    str += '<tr>';
    str += '<td valign="middle" height="35" align="center" class="heizi12"><strong>序号</strong></td>';
    str += '<td valign="middle" align="center" class="heizi12"><strong>业务类型</strong></td>';
    str += ' <td valign="middle" align="center" class="heizi12"><strong>缴费号</strong></td>';
    str += '<td valign="middle" align="center" class="heizi12"><strong>用电客户名称</strong></td>';
    str += '<td valign="middle" align="center" class="heizi12"><strong>欠费金额</strong></td>';
    str += '<td width="14%" align="right">';
    str += '<input class="s_id" type="hidden" value="' + obj[i].s_id + '"/>';
    str += '<input class="c_id" type="hidden" value="' + obj[i].c_id + '"/>';
    //str += '<a class="lj_bc_14 lj_bc_14_list" href="javascript:void(0);">新增</a>';
    str += '</td>';
    str += '</tr>';

    var ammeter_type = "";
    var pay_statu = '';
    var bt_status = "";
    for (var j = 0; j < ammeters.length; j++) {
        ammetercount++;
        if (ammeters[j].ammeter_type == 'A') {
            ammeter_type = '抄表电';
        } else if (ammeters[j].ammeter_type == 'B') {
            ammeter_type = '智能表电';
        }
        //if (ammeters[j].pay_status == '0') {
        //    pay_statu = '暂停';
        //    bt_status = '恢复';
        //
        //} else if (ammeters[j].pay_status == '1') {
        //    pay_statu = '正常';
        //    bt_status = '暂停';
        //}
        var totalFeeValue = eval(ammeters[j]).totalFee;
        if (typeof(totalFeeValue) == "undefined") {
            totalFeeValue = 0;
        }
        var totalFeeValue1;
        var totalFee = parseInt(totalFeeValue,0);
        if(totalFee>0){
    		if((paysum+"").length>2){
    			var totalFeeStr="";
    			var fen = totalFee%100;
    			if(parseInt(fen,0)>0&&parseInt(fen,0)<10){
    				totalFeeStr = Math.floor(totalFee/100) + ".0" + fen;
    			}else if(parseInt(fen,0)>=10){
    				totalFeeStr = Math.floor(totalFee/100) + "." + fen;
    			}else{
    				totalFeeStr = Math.floor(totalFee/100) + ".00";
    			}
    			totalFeeValue1 = totalFeeStr;
    		}else if((paysum+"").length==1){
    			totalFeeValue1 = "0.0"+totalFee;
    		}else{
    			totalFeeValue1 = "0."+totalFee;
    		}
    	}else{
    		totalFeeValue1 = "0.00";
    	}

        str += '<tr align="center">';
        str += ' <td width="10%" height="30" class="heizi12">' + eval(j + 1) + '</td>';
        str += '<td width="10%" class="heizi12 ammeter_type" ammeter_type="'+ammeter_type+'">' + ammeter_type + '</td>';
        //str += '<td width="10%" class="heizi12"><span class="hz14">';
        str += '<input class="a_id ammeters" type="hidden" a_id="'+ ammeters[j].a_id +'" value="' + ammeters[j].a_id + '"/>';
        //str += '</span></td>';
        str += '<td width="10%" class="heizi12 ammeter_no" ammeter_no="'+eval(ammeters[j]).ammeter_no+'">' + eval(ammeters[j]).ammeter_no + '</td>';
        str += '<td width="10%" class="heizi12 ammeter_name" ammeter_name="'+eval(ammeters[j]).ammeter_name+'">' + eval(ammeters[j]).ammeter_name + '</td>';
        str += '<td width="10%" class="heizi12 totalFee" totalFee="'+ totalFeeValue1 +'">' + totalFeeValue1 + '</td>';
        str += '<td align="right">';
        str += '<a class="lj_bc_14 detail_star_btn" href="javascript:void(0);">详情</a>';
        str += '</td>';
        str += ' </tr>';
        //5
    }
    str += '</table>';
    //  }
    $("#ammetercount").html(" 目前共有 " + ammetercount + " 个抄表电客户编号 ");
    $("#listBox").html(str);
    setClasssPage(num);
	if(myType != 1){
		hideLoading();
	}
}
function setClasssPage(num) {
    // 分页

    var page = $("#page").val();//当前页面
    var next = page - 1;
    if (next <= 0) {
        next = 1;
    }
    var up = eval(page + 1);
    if (up >= eval(num + 1)) {
        up = eval(num + 1);
    }

    var pageStr = "<tr>";

    pageStr += '<td align="center" bgcolor="#e7e7e7" class="heizi12">' + eval(page) + '/' + eval(num) + '</td>';
    pageStr += '<td width="33" height="29" align="center" onClick="firstPage()" class="heizi12"><img src="' + $("#projectPath").val() + '/images/zj01.jpg" width="42" height="28" /></td>';
    pageStr += '<td width="33" height="29" align="center" onClick="upPage()" class="heizi12"><img src="' + $("#projectPath").val() + '/images/zk01.jpg" width="42" height="28" /></td>';
    var classSelected = '';
    for (var j = 0; j < num; j++) {
        if (eval(j + 1) == eval(page)) {
            classSelected = 'selected';
        }
        pageStr += '<td width="33" height="29" class="' + classSelected + '" align="center" onClick="changePage(' + eval(j + 1) + ')"  class="heizi12"><a href="#" class="lj_kuai_12">' + eval(j + 1) + '</a></td>';

    }
    pageStr += '<td width="33" height="29" align="center" onClick="nextPage()" class="heizi12"><img src="' + $("#projectPath").val() + '/images/yk01.jpg" width="41" height="28" /></td>';
    pageStr += '<td width="33" height="29" align="center" onClick="endPage()" class="heizi12"><img src="' + $("#projectPath").val() + '/images/yj01.jpg" width="41" height="28" /></td>';
    pageStr += '</tr>';
    if(eval(num)>1){
        $("#beijs").html(pageStr);
    }


}
function changePage(pageNum){
    $("#page").val(pageNum);
    dataCh(dataArr);
    return false;
}
function firstPage() {
    $("#page").val(1);
    dataCh(dataArr,333);
    return false;
}
function upPage() {
    var pageNum = $("#page").val();//当前页面
    if (pageNum > 1) {
        pageNum --;
    }
    $("#page").val(pageNum);
    dataCh(dataArr,333);
    return false;
}
function nextPage() {
    var pageNum = $("#page").val();//当前页面
    if (pageNum < dataArr.rowsTotal) {
        pageNum++;
    }
    $("#page").val(pageNum);
    dataCh(dataArr,3333);
    return false;
}
function endPage(type) {
    pageNum = dataArr.rowsTotal;
    $("#page").val(pageNum);
    dataCh(dataArr,3333);
    return false;
}

function tozhifu() {
    //window.location.href=$("#projectPath").val()+"/ammetermanager/toZhifu";
    $("#lightSuccess").hide();
    //var url = $("#projectPath").val() + '/ammetermanager/toZhifu';
    //$.ajax({
    //    type: "GET",
    //    url: url,
    //    dataType: "json",
    //    success: function (data) {
    //        //if (data.status == 'success') {
    //        //
    //        //} else {
    //        //    alert(data.msg);
    //        //}
    //    }
    //});
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

function payInfo(companyId,companyName){
//    linkbt.onclick=function(){
        var sendData = {
            'companyName': companyName ? companyName : '',
            'companyId': companyId ? companyId : ''
        }
        var url = $("#projectPath").val()+'/pay/getPayurl';
        $.ajax({
            type: "POST",
            url: url,
            data:sendData,
            dataType: "json",
            success: function (data) {
                var resultData = eval(data);
                if (resultData.status == 'success') {
                    var datas = JSON.parse(resultData.data);
                    $("#checkUrl").html(datas.check_url);
                    $("#checkNo").html(datas.check_no);
                    light.style.display='block';
                } else {
                    alert(resultData.msg);
                }
            }
        });
//     fade.style.display='block';
//    }
}
function closeBT(){
    $("#checkUrl").style.display='none';
}

function exportExcle(){

//	window.location.href = $("#projectPath").val() + '/excle/exportExcle';
    var url =  $("#projectPath").val() + '/excle/exportExcle';
    $.ajax({
        type: "POST",
        url: url,
        data: dataArr,
        dataType: "json",
        success: function (data) {
            if (data.status == 'success') {
                window.location.href = $("#projectPath").val() + data.url;
            } else {
                alert(data.msg);
            }
        }
    });
}

function generatePayOrder(cId){
	$('#lightC').hide();
    var paysum = $("#paysum").text();
    if(paysum=="0.00"||paysum=="0"){
        alert("您单位登记的所有电表缴费及时，没有欠费，感谢使用。");
        return;
    }
    var datas = {
        'cId': cId ? cId : "",
    }
    var url = $("#projectPath").val() + '/pay/checkPayTime';
    $.ajax({
        type: "GET",
        url: url,
        dataType: "json",
        success: function (data) {
            if (data.status == 'success') {
                var url = $("#projectPath").val() + '/pay/toMiddle';
//                var ifram = document.createElement("iframe");
//                location.href = url;
//            	window.open($("#projectPath").val() + '/pay/generatePayOrder?isDirect=1&cId='+cId);
            	
            	var $tempForm = $('<form method="get" target="_blank" action="' + url + '"><input type="hidden" name="cId" value="'+cId+'"></form>');  
                $("body").append($tempForm);  
                $tempForm.submit();  
                $tempForm.remove();  
            	
            } else {
                alert(data.msg);
            }
        }
    });

}