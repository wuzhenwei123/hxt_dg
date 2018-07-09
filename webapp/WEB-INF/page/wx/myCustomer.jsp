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
    <title>我的客户</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/customForm.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/style.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/comm.css">
    <link rel="stylesheet" href="${ctx}/css/wx/mobiscroll_date.css" />
    <link href="${ctx}/css/weui.css" rel="stylesheet" type="text/css" />
    <script src="${ctx}/public/custom/public/js/jquery.1.7.2.min.js"></script>
	<script src="${ctx}/js/wx/mobiscroll_date.js"></script>
	<script src="${ctx}/js/wx/mobiscroll.js"></script>
</head>
<body class="body">
<div class="box">
    <div class="topBox"><a class=" offen_back" href="javascript:history.go(-1);"></a>我的客户</div>
    
    <div class="bsBox marginTop16">
    	<div class="searchBox" id="searchBox">查询<a id="searchIcon" class="searchIcon" href="javascript:void(0);"></a></div>
        <div id="searchBoxItem" class="searchBoxItem" style="height:0;">
            <div class="itemsContentBox">
                
                <div class="">
                    <input class="input" type="text" placeholder="单位名称" id="companyName"/>
                </div>
                <div class="searItems">
                    <div class="fl searItem">
                        <input class="input" type="text" placeholder="联系人姓名" id="contactName"/>
                    </div>
                    <div class="fr searItem">
                        <input class="input" type="text" placeholder="联系人手机" id="contactPhone"/>
                    </div>
                </div>
                
                <div class="searItems">
                    <div class="fl searItem">
                        <input class="input" type="text" id="startTime" placeholder="起始日期" />
                    </div>
                    <div class="fr searItem">
                        <input class="input" type="text" id="endTime" placeholder="结束日期" />
                    </div>
                </div>
                <div class="searItems sel">
                    <div class="fl searItem">
                     
                    <c:if test="${admin_user.roleId==148}">    
                    <dl class="input" style="overflow:visible;">
                            <dd class="line">
                                <div class='diy_select select1'>
                                    <input type='hidden' name='' class='diy_select_input'/>
                                    <div class='diy_select_txt'>全部</div>
                                    <div class='diy_select_btn'></div>
                                    <ul class='diy_select_list'>
                                        <li>全部</li>
                                        <li>自己</li>
                                        <li>代理</li>
                                        
                                    </ul>
                                </div>
                            </dd>
                        </dl>
                        </c:if>
                        <c:if test="${admin_user.roleId!=148}">  
                        <dl class="input" style="overflow:visible;">
                            <dd class="line">
                                <div class='diy_select select1'>
                                    <input type='hidden' name='' class='diy_select_input'/>
                                    <div class='diy_select_txt'>自己</div>
                                    <div class='diy_select_btn'></div>
                                    <ul class='diy_select_list'>
                                        
                                    </ul>
                                </div>
                            </dd>
                        </dl>
                        </c:if>
                    </div>
                    <div class="fr searItem">
                        <input class="input" type="text" placeholder="缴费号" id="paymentNo"/>
                    </div>
                    <span class="clear"></span>
                </div>
                
                <div class="searItems">
                    <div class="fl searItem">
                    	<c:if test="${admin_user.roleId==150}">
                    		<input class="input" type="text" placeholder="客户经理姓名" id="oneAgentName"/>
                    	</c:if>
                    	<c:if test="${admin_user.roleId==148}">
                    		<input class="input" type="text" placeholder="支持人员姓名" id="servicerName"/>
                    	</c:if>
                    </div>
                </div> 
            </div>
            <div class="itemsContentBox">
                <a href="javascript:searchDate(1)" class="btn btn_primary searBtnBox">查询</a>
            </div>
        </div> 
    </div>
    
    
    <div class="ch_ui_box bsBox marginTop16">
        <div class="ui_cell ui_cells">
            <div class="ui_center ui_cell_flex jls">记录条数</div>
            <div class="ui_cell_ft"><font class="" id="fansCount"></font></div>
        </div>
    </div>
	
	<div id="zd-item">
		
	</div>    
    
    
</div>
<script src="${ctx}/public/custom/public/js/select.js"></script>
<script>
	var stop=true;
	var page = 1;
	$(function(){
		stop=false;
		$(document).on("click","#searchBox",function(){
			var h = 0;
			if($(this).hasClass("on")){
				$(this).removeClass("on");
				
			} else {
				$(this).addClass("on");
				h =  348;
			}
			$("#searchBoxItem").animate({"height": h + "px"},300);
		});
		
		var currYear = (new Date()).getFullYear();
        var opt = {};
        opt.date = {
            preset: 'date'
        };
        opt.datetime = {
            preset: 'datetime'
        };
        opt.time = {
            preset: 'time'
        };
        opt.default = {
            theme: 'android-ics light', //皮肤样式
            dateFormat: 'yyyy-mm-dd',
            showNow: true,
            nowText: "今天",
            setText: '确定', //确认按钮名称
            cancelText: '取消',//取消按钮名籍我
            dateOrder: 'yymmdd', //面板中日期排列格式
            dayText: '日', monthText: '月', yearText: '年', //面板中年月日文字
            startYear: currYear - 50, //开始年份
            endYear: currYear + 10 //结束年份
        };
        
        $("#startTime").mobiscroll($.extend(opt['date'], opt['default']));
        $("#endTime").mobiscroll($.extend(opt['date'], opt['default']));
        loading();
		loadData(page);
		
		
	})
	
	$(window).on("scroll", function() {
		totalheight = parseFloat($(window).height()) + parseFloat($(window).scrollTop());
	    if($(document).height() <= totalheight){
	        if(stop){
	            stop=false;
	            loading();
	            page = parseInt(page)+1;
	            loadData(page);
	        }
	    }
		
		var st = $(document).scrollTop();
		if(st > 52) {
			$(".search-box-new").addClass("fixed");
		} else {
			$(".search-box-new").removeClass("fixed");
		}
	});
	
	var itemTpl = '<div class="bsBox ItemLi marginTop16"><div onclick="showInfo({companyId})">';
	itemTpl = itemTpl + '<ul class="clearAfter ItemuL"><li class="fl">单位名称</li><li class="fr">{companyNme}</li></ul>';
	itemTpl = itemTpl + '<ul class="clearAfter ItemuL"><li class="fl">联系人姓名</li><li class="fr">{contactName}</li></ul>';
	itemTpl = itemTpl + '<ul class="clearAfter ItemuL"><li class="fl">联系人手机</li><li class="fr">{contactPhone}</li></ul>';
	if(${admin_user.roleId}!=148){
		itemTpl = itemTpl + '<ul class="clearAfter ItemuL"><li class="fl">客户经理</li><li class="fr">{oneAngetName}</li></ul>';
	}else{
		itemTpl = itemTpl + '<ul class="clearAfter ItemuL"><li class="fl">支持人员</li><li class="fr">{servicerName}</li></ul>';
	}
	itemTpl = itemTpl + '</div><div class="itemsContentBox lisBtnBox">';
	if(${admin_user.roleId}==148){
		itemTpl = itemTpl + '<a href="${ctx}/weixin/toChangePeople?servicerId={servicerId}&companyId={companyId}" class="fr btn btn_primary listBtn">变更支持人员</a>';
	}
	itemTpl = itemTpl + '</div></div>';
	
	
	
	/**
	 * 载入内容
	 * @return {[type]} [description]
	 */
	function loadData(page) {
		var startTime = $("#startTime").val();
		var endTime = $("#endTime").val();
		var companyName =$("#companyName").val();
		var contactPhone =$("#contactPhone").val();
		var oneAgentName =$("#oneAgentName").val();
		if(!oneAgentName){
			oneAgentName = "";
		}
		var paymentNo =$("#paymentNo").val();
		var servicerName =$("#servicerName").val();
		if(!servicerName){
			servicerName = "";
		}
		var diy_select_input = $(".diy_select_input").val();
		if(diy_select_input=="自己"){
			diy_select_input = 1;
		}else if(diy_select_input=="代理"){
			diy_select_input = 2;
		}
		$.get("${ctx}/hCompany/getMyCustomer?pageNo="+page+"&_t="+Math.random()+"&openId=${openId}&agentOpenId=${agentOpenId}&startTime="+startTime+"&endTime="+endTime+"&companyName="+companyName+"&contactPhone="+contactPhone+"&paymentNo="+paymentNo+"&oneAgentName="+oneAgentName+"&servicerName="+servicerName+"&style="+diy_select_input,function(data,status){
			var json = eval("("+data+")");
			if(json.message=="ok"){
				$("#fansCount").html(json.totalResults);
				$(".loading").remove();
				var testData = json.items
				for(var i in testData) {
					$("#zd-item").append(renderTpl(itemTpl, testData[i]));
				}
				stop = true;
			}else if(json.message=="end"){
				$(".loading").remove();
			}
		});
	}
	
	
	function renderTpl(tpl, op) {
		return tpl.replace(/\{(\w+)\}/g, function(e1, e2) {
			return op[e2] != null ? op[e2] : "";
		});
	}
	
	function searchDate(pages){
		$("#zd-item").empty();
		loading();
		loadData(pages);
	}
	
	function loading() {
	    $("#zd-item").append('<div class="loading"><img src="${ctx}/images/wx/loading.gif" alt=""></div>');
	}
	
	function showInfo(companyId){
		window.location.href = '${ctx}/hCompany/toCustomerInfo?companyId='+companyId;
	}
</script>
</body>
</html>
