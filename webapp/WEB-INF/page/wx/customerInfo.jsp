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
    <title>缴费号详情</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/style.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/comm.css">
    <link href="${ctx}/css/weui.css" rel="stylesheet" type="text/css" />
    <script src="${ctx}/js/wx/zepto.min.js" ></script>
</head>
<body class="body">
	<div class="box">
    	<div class="topBox"><a class=" offen_back" href="javascript:history.go(-1);"></a>客户详情</div>
        
        <div class="yzBox regBox jyInfoBox">
            <div class="bsBox ItemLi marginTop16">
                <ul class="clearAfter ItemuL">
                    <li class="fl">客户姓名</li>
                    <li class="fr">${hCompany.name}</li>        
                </ul>
                <ul class="clearAfter ItemuL">
                    <li class="fl">联系人姓名</li>
                    <li class="fr">${hCompany.contact_name}</li>        
                </ul>
                <ul class="clearAfter ItemuL">
                    <li class="fl">联系人手机</li>
                    <li class="fr">${hCompany.contact_phone}</li>        
                </ul>
                <ul class="clearAfter ItemuL">
                    <li class="fl">职称人员姓名</li>
                    <li class="fr">${hCompany.name}</li>        
                </ul>
            </div>
            
            
			<div id="zd-item">
			
			</div>           
            
            
        </div>
        
        
    </div>
    <div id="loadingToast" class="weui_loading_toast">
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
	        <p class="weui_toast_content">数据加载中</p>
	    </div>
	</div>
    <script type="text/javascript">
    	$(function(){
    		$.get("${ctx}/hCompany/customerInfoJson?companyId=${hCompany.id}",function(data,status){
    			var json = eval("("+data+")");
    			if(json.message=="ok"){
    				$("#loadingToast").hide();
    				var testData = json.items
    				var str = '';
    				for(var i in testData) {
    					str += '<div class="bsBox ItemLi marginTop16">';
    					str += '                <ul class="clearAfter ItemuL">';
    					str += '                    <li class="fl">分组名称</li>';
    					str += '                    <li class="fr">'+testData[i].name+'</li>';        
    					str += '                </ul>';
    					str += '                <ul class="clearAfter ItemuL">';
    					str += '                    <li class="fl">收件人姓名</li>';
    					str += '                    <li class="fr">'+testData[i].consignee+'</li> ';       
    					str += '               </ul>';
    					str += '                <ul class="clearAfter ItemuL">';
    					str += '                    <li class="fl">收件人手机</li>';
    					str += '                    <li class="fr">'+testData[i].consignee_phone+'</li>';        
    					str += '                </ul>';
    					str += '               <ul class="clearAfter ItemuL">';
    					str += '                    <li class="fl">收件人地址</li>';
    					str += '                    <li class="fr">'+testData[i].consignee_address+'</li>';        
    					str += '                </ul>';
    					str += '                <div class="jyTableBox">';
    					str += '                	<table cellpadding="0" cellspacing="0" border="0" width="100%">';
    					str += '                    	<tr>';
    					str += '                        	<th>缴费号</th>';
    					str += '                            <th>客户名称</th>';
    					str += '                            <th>欠费金额</th>';
    					str += '                        </tr>      ';              
    					var subData = testData[i].items1;
    					for(var j in subData) {
    						str += '                         <tr>';
    						str += '                        	<td>'+subData[j].ammeterNo+'</td>';
    						str += '                            <td>'+subData[j].accountName+'</td>';
    						str += '                            <td align="center"><font class="money">'+subData[j].fee+'</font></td>';
    						str += '                        </tr>';
    					}
    					str += '                    </table>';
    					str += '                </div>';
    					str += '            </div>';
    					$("#zd-item").append(str);
    				}
    				stop = true;
    			}else if(json.message=="end"){
    				$(".loading").remove();
    			}
    		});
    	})
    </script>
</body>
</html>
