<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<html>
	<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="#e8447e">
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="Expires" CONTENT="-1">           
    <meta http-equiv="Cache-Control" CONTENT="no-cache">           
    <meta http-equiv="Pragma" CONTENT="no-cache">
    <meta name="description" content="">
    <meta name="Keywords" content="">
    <title>账户明细</title>
    <link href="${ctx}/css/weui.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/style.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/comm.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/customForm.css">
    <link rel="stylesheet" href="${ctx}/css/wx/mobiscroll_date.css" />
    <script src="${ctx }/js/wx/jquery.1.7.2.min.js"></script>
   	<script src="${ctx}/js/wx/mobiscroll_date.js"></script>
	<script src="${ctx}/js/wx/mobiscroll.js"></script>
    <script src="${ctx}/js/area_public.js"></script>
</head>
<body class="body">
<div class="box">
    <div class="topBox"><a class=" offen_back" href="javascript:history.go(-1);"></a>账户明细</div>
    
    <div class="bsBox marginTop16">
    	<div class="searchBox">查询<a id="searchIcon" class="searchIcon" href="javascript:void(0);"></a></div>
        <div id="searchBoxItem" class="searchBoxItem" style="height:0;">
            <div class="itemsContentBox">
                
                <div class="">
                    <input class="input" type="text" placeholder="单位名称" id="companyName"/>
                </div>
                <div class="searItems">
                    <div class="fl searItem">
                        <input class="input" type="text" placeholder="起始日期" id="startTime"/>
                    </div>
                    <div class="fr searItem">
                        <input class="input" type="text" placeholder="结束日期" id="endTime"/>
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
                                        <li value="">全部</li>
                                        <li value="1">自己</li>
                                        <li value="2">代理</li>
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
                    <span class="clear"></span>
                </div>
            </div>
            <div class="itemsContentBox">
                <a href="javascript:page = 1;searchDate(1);" class="btn btn_primary searBtnBox">查询</a>
            </div>
        </div> 
    </div>
    <div class="ch_ui_box bsBox marginTop16">
        <div class="ui_cell ui_cells">
            <div class="ui_center ui_cell_flex jls">记录条数</div>
            <div class="ui_cell_ft"><font class="" id="allCount">${allCount }</font></div>
        </div>
    </div>
    <div class="bsBox ItemLi bot">
    	<ul class="clearAfter ItemuL">
			<li class="fl">交易总额</li>
            <li class="fr right"><font id="allDetailFee">${allDetailFee }</font></li>        
        </ul>
        <ul class="clearAfter ItemuL">
			<li class="fl">累计钱币</li>
            <li class="fr"><font id="allMoney">${allMoney }</font></li>        
        </ul>
    </div>
	<div id="zd-item"></div>   
</div>
<script src="${ctx }/public/custom/public/js/select.js"></script>
<script>
	var stop=true;
	var page = 1;
	$(function(){
		$(document).on("click",".searchBox",function(){
			var h = 0;
			if($(this).hasClass("on")){
				$(this).removeClass("on");
				
			} else {
				$(this).addClass("on");
				h =  245;
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
	
// 	$('.diy_select_list li').each(function(index, obj) {
// 		if ($(obj).hasClass('focus')) {
// 			console.log(obj)
// 		}
// 	})
	
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
	var itemTpl = '<div class="bsBox ItemLi zhmx marginTop16" onclick="window.location.href=\'${ctx}/weixin/accDetail/1/{o_id}\'">';
	itemTpl = itemTpl + '<ul class="clearAfter ItemuL"><li class="fl">{agentName}的交易</li><li class="fr"><font>{payTimeStr}</font></li></ul>';
	itemTpl = itemTpl + '<ul class="clearAfter ItemuL"><li class="fl">客户名称</li><li class="fr right"><font>{c_name}</font></li></ul>';
	itemTpl = itemTpl + '<ul class="clearAfter ItemuL zhmxBox"><li class="fl">缴费金额</li><li class="fr right"><font>{amount}</font><span class="money">+{rateMoney}</span></li></ul>';
	itemTpl = itemTpl + '<span class="clear"></span></div>';	
	var itemTpl1 = '<div class="bsBox ItemLi zhmx marginTop16" onclick="window.location.href=\'${ctx}/weixin/accDetail/2/{o_id}\'">';
// 	itemTpl1 = itemTpl1 + '<ul class="clearAfter ItemuL"><li class="fl">{agentName}的交易</li><li class="fr"><font>{payTimeStr}</font></li></ul>';
	itemTpl1 = itemTpl1 + '<ul class="clearAfter ItemuL"><li class="fl">提现时间</li><li class="fr"><font>{payTimeStr}</font></li></ul>';
	itemTpl1 = itemTpl1 + '<ul class="clearAfter ItemuL zhmxBox"><li class="fl">提现金额</li><li class="fr right"><span class="money" style="color:#01a796">-{amount}</span></li></ul>';
	itemTpl1 = itemTpl1 + '<span class="clear"></span></div>';	
	/**
	 * 载入内容
	 * @return {[type]} [description]
	 */
	function loadData(page) {
		var startTime = $("#startTime").val();
		var endTime = $("#endTime").val();
		var companyName =$("#companyName").val();
		var diy_select_input = $(".diy_select_input").val();
		if(diy_select_input=="自己"){
			diy_select_input = 1;
		}else if(diy_select_input=="代理"){
			diy_select_input = 2;
		}else{
			diy_select_input = '';
		}
		$.get("${ctx}/weixin/accountDetailList?rowCount=10&pageNo="+page+"&_t="+Math.random()+"&openId=${openId}&agentOpenId=${agentOpenId}&startTime="+startTime+"&endTime="+endTime+"&cname="+companyName+"&queryType="+diy_select_input+"&sortColumn=a.create_time desc",function(data,status){
			var json = JSON.parse(data);
			if(json.message=="ok"){
				$(".loading").remove();
				var testData = json.items
				if(page==1){
					if(testData&&testData.length>0){
						var tmp = testData[0];
						$("#allCount").html(tmp.allCount);
						$("#allDetailFee").html(((tmp.allDetailFee)/100).toFixed(2));
						$("#allMoney").html(tmp.allMoney);
					}else{
						$("#allCount").html(0);
						$("#allDetailFee").html('0.00');
						$("#allMoney").html(0.00);
					}
				}
				for(var i in testData) {
					if(testData[i].order_type==1){
						$("#zd-item").append(renderTpl(itemTpl, testData[i]));
					}else if(testData[i].order_type==2){
						$("#zd-item").append(renderTpl(itemTpl1, testData[i]));
					}
				}
				stop = true;
			}else if(json.message=="end"){
				$(".loading").remove();
			}
		});
	}
	
	
	function renderTpl(tpl, op) {
		return tpl.replace(/\{(\w+)\}/g, function(e1, e2) {
			if('amount'==e2){
				return op[e2] != null ? (eval(op[e2])/100) : "";
			}else{
				return op[e2] != null ? op[e2] : "";
			}
			
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
</script>
</body>
</html>
