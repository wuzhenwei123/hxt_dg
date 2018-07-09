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
    <title>个人代理提现信息管理</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/customForm.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/style.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/comm.css">
    <link href="${ctx}/css/weui.css" rel="stylesheet" type="text/css" />
    <script src="${ctx}/public/custom/public/js/jquery.1.7.2.min.js"></script>
    <script src="${ctx}/js/area_public.js"></script>
</head>
<body class="body">
<div class="box">
    <div class="topBox"><a class="offen_back" href="javascript:history.go(-1);"></a>个人代理提现信息管理</div>
    <div class="ch_ui_box marginTop16 accItems tx">
        <div class="ui_cell ui_cells">
            <div class="ui_center_left ">真实姓名</div>
            <div class="ui_center ui_cell_flex">
                <input id="realName" class="offen_text offen_ui_text" type="text" value="${admin_user.realName}"  placeholder="真实姓名" />
            </div>
        </div>
        <div class="ui_cell ui_cells">
            <div class="ui_center_left ">身份证号</div>
            <div class="ui_center ui_cell_flex">
                <input id="wocard_no" class="offen_text offen_ui_text" type="text" value="${hAgentTwo.card_no}"  placeholder="身份证号" />
            </div>
        </div>
    </div>

        
   
   <div class="itemsContentBox on">
    <a id="OkBtnBox" class="btn btn_primary" href="javascript:void(0);">下一步</a>
	</div>
</div>
<script>
	$(function(){
		$("#OkBtnBox").click(function(){
			var realName = $("#realName").val();
			if(realName==""){
				$HXT.showInfo('请输入真实姓名');
				return false;
			}
			
			var wocard_no = $("#wocard_no").val();
			if(wocard_no==""){
				$HXT.showInfo('请输入身份证号');
				return false;
			}else{
				if(!IdentityCodeValid(wocard_no)){
					$HXT.showInfo('身份证号错误，请重新填写');
					return false;
				}
			}
			$.get("${ctx}/weixin/toPostalInfoNext?realName="+realName+"&wocard_no="+wocard_no+"&id=${hAgentTwo.id}",function(data){
				var result = eval('('+data+')'); 
	            if (result.code == '1') {
	            	if(result.message == 'no'){
	            		$HXT.showInfo('身份证号已经存在');
	            	}else{
	            		var style = '${hAgentTwo.style}';
	            		if(style=="1"){//公司
	            			window.location.href = "${ctx}/weixin/toPostalInfoSecondPc?id=${hAgentTwo.id}";
	            		}else{
	            			window.location.href = "${ctx}/weixin/toPostalInfoSecond?id=${hAgentTwo.id}";
	            		}
	            	}
	            }else{
	            	$HXT.showInfo('系统异常');
	            }
			});
		});
	})
	
	function IdentityCodeValid(code) { 
        var city={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江 ",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北 ",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏 ",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外 "};
        var tip = "";
        var pass= true;
        
        if (!code || !/^[1-9][0-9]{5}(19[0-9]{2}|200[0-9]|2010)(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])[0-9]{3}[0-9xX]$/i.test(code)) {
            tip = "身份证号格式错误";
            pass = false;
        }else if(!city[code.substr(0,2)]){
            tip = "地址编码错误";
            pass = false;
        }else{
            //18位身份证需要验证最后一位校验位
            if(code.length == 18){
                code = code.split('');
                //∑(ai×Wi)(mod 11)
                //加权因子
                var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
                //校验位
                var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];
                var sum = 0;
                var ai = 0;
                var wi = 0;
                for (var i = 0; i < 17; i++)
                {
                    ai = code[i];
                    wi = factor[i];
                    sum += ai * wi;
                }
                var last = parity[sum % 11];
                if(parity[sum % 11] != code[17]){
                    tip = "校验位错误";
                    pass =false;
                }
            }
        }
        return pass;
    }
</script>
</body>
</html>
