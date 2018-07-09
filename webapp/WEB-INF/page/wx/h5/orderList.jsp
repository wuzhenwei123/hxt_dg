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
    <title>交易记录</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/public/custom/public/css/customForm.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/h5/style.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/h5/comm.css">
    <link href="${ctx}/css/weui.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="${ctx}/css/wx/mobiscroll_date.css" />
    <script src="${ctx}/public/custom/public/js/jquery.1.7.2.min.js"></script>
    <script src="${ctx}/js/area_public.js"></script>
    <style type="text/css">
    .loading{
    	text-align:center;
    }
    .select2 .diy_select_txt {
	    padding-right: 20px;
	    height: 30px;
	}
	.select2 .diy_select_list {
	    width: 100%;
	}
    </style>
</head>
<body class="body">
	<div class="box">
    	<div class="topBox"><a class=" offen_back" href="javascript:history.go(-1);"></a>交易记录</div>
        
        
        
        
        <div class="yzBox regBox jyInfoBox ">
        	<div class="bsBox jysearchBox marginTop16">
            	<div class="searchTitle">查询</div>
            	<div id="searchItemBox" class="searchItemBox" style="display:none;">
                    <div class="searItemsa twoItem">
                    	<input type="text" class="fl searchInput" value="" placeholder="缴费号" id="electric_number"/>
                        <div class='diy_select select2  searchInput' style="width: 45%;float: right;height:35px;">
                            <input type='hidden' name='' class='diy_select_input' id="is_fp"/>
                            <div class='diy_select_txt'>发票状态</div>
                            <div class='diy_select_btn'></div>
                            <ul class='diy_select_list'>
                            	<li>发票状态</li>
                            	<li>已邮寄</li>
                            	<li>未邮寄</li>
                            </ul>
                        </div>
                        <span class="clear"></span>
                    </div>
                    <div class="searItemsa">
                    	<div class='diy_select select2  searchInput' style="height:35px;">
                            <input type='hidden' name='' class='diy_select_input' id="o_sub_id"/>
                            <div class='diy_select_txt'>分组名称</div>
                            <div class='diy_select_btn'></div>
                            <ul class='diy_select_list' id="subul">
                            	<li value="">分组名称</li>
                            	<c:forEach items="${listSubC}" var="sub">
                            		<li value="${sub.s_id}">${sub.sub_name}</li>
                            	</c:forEach>
                            </ul>
                        </div>
                    </div>
                    <div class="searItemsa twoItem">
                    	<input type="text" class="fl searchInput" placeholder="支付开始时间" id="pay_start_time"/>
                        <input type="text" class="fr searchInput" placeholder="支付结束时间" id="pay_end_time"/>
                        <span class="clear"></span>
                    </div>
                    <div class="searItemsa">
                        <div class='diy_select select2  searchInput' style="height:35px;">
                            <input type='hidden' name='' class='diy_select_input' id="pay_status"/>
                            <div class='diy_select_txt'>支付状态</div>
                            <div class='diy_select_btn'></div>
                            <ul class='diy_select_list'>
                            	<li>支付状态</li>
                                <li>已支付</li>
                                <li>未支付</li>
                            </ul>
                        </div>
                    </div>
                    <div class="itemsContentBox on">
                    	<a id="OkBtnBox" class="btn btn_primary" href="javascript:searchDate(1);">查询</a>
                    </div>
                </div>
            </div>
            <div class="ch_ui_box accItems gr payInfoBox marginTop16 jyjl" style="display: none;">
                <div class="ui_cell ui_cells">
                    <div class="ui_center_left ">记录条数：</div>
                    <div class="ui_center ui_cell_flex">
                    	<font class="accJiaojian" id="fansCount">${hpayorderListCount}</font>
                    </div>
                </div>
                
                <div class="ui_cell ui_cells">
                    <div class="ui_center_left ">交易总额：</div>
                    <div class="ui_center ui_cell_flex">
                    	<font class="accJiaojian" id="countsum">${countsum}</font>
                    </div>
                </div>
            </div>
            
            <div id="zd-item">
		
			</div>
            
        </div>
        
        
    </div>
    <script src="${ctx}/js/wx/mobiscroll_date.js"></script>
	<script src="${ctx}/js/wx/mobiscroll.js"></script>
    <script src="${ctx}/public/custom/public/js/select.js"></script>
    <script>
    	var stop=true;
		var page = 1;
    	$(function(){
    		stop=false;
			$(".searchTitle").click(function(){
				if($(this).hasClass("on")){
					$(this).removeClass("on");
					$(".searchItemBox").hide();
				} else {
					$(this).addClass("on");
					$(".searchItemBox").show();
				}
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
	        
	        $("#pay_start_time").mobiscroll($.extend(opt['date'], opt['default']));
	        $("#pay_end_time").mobiscroll($.extend(opt['date'], opt['default']));
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
		
    	var itemTpl = '<div class="bsBox ItemLi marginTop16  jyjl" onclick="showInfo(\'{o_no}\')">';
    	itemTpl = itemTpl + '<ul class="clearAfter ItemuL"><li class="fl">消费总金额:</li><li class="fr"><font class="black">{totalFee}</font></li></ul>';
    	itemTpl = itemTpl + '<ul class="clearAfter ItemuL"><li class="fl">付款时间:</li><li class="fr">{time}</li></ul>';
    	itemTpl = itemTpl + '<ul class="clearAfter ItemuL"><li class="fl">支付状态:</li><li class="fr">{state}</li></ul>';
    	itemTpl = itemTpl + '<ul class="clearAfter ItemuL"><li class="fl">支付类型:</li><li class="fr">手机缴费</li></ul>';
    	itemTpl = itemTpl + '<ul class="clearAfter ItemuL"><li class="fl">订单号:</li><li class="fr">{o_no}</li></ul>';
    	itemTpl = itemTpl + '<ul class="clearAfter ItemuL"><li class="fl">银行流水号:</li><li class="fr">{query_id}</li></ul></div>';
    	var itemTpl2 = '<div class="bsBox ItemLi marginTop16  jyjl" onclick="showInfo(\'{o_no}\')">';
    	itemTpl2 = itemTpl2 + '<ul class="clearAfter ItemuL"><li class="fl">消费总金额:</li><li class="fr"><font class="black">{totalFee}</font></li></ul>';
    	itemTpl2 = itemTpl2 + '<ul class="clearAfter ItemuL"><li class="fl">付款时间:</li><li class="fr">{time}</li></ul>';
    	itemTpl2 = itemTpl2 + '<ul class="clearAfter ItemuL"><li class="fl">支付状态:</li><li class="fr">{state}</li></ul>';
    	itemTpl2 = itemTpl2 + '<ul class="clearAfter ItemuL"><li class="fl">支付类型:</li><li class="fr">银联B2B</li></ul>';
    	itemTpl2 = itemTpl2 + '<ul class="clearAfter ItemuL"><li class="fl">订单号:</li><li class="fr">{o_no}</li></ul>';
    	itemTpl2 = itemTpl2 + '<ul class="clearAfter ItemuL"><li class="fl">银行流水号:</li><li class="fr">{query_id}</li></ul></div>';
    	
    	
    	/**
    	 * 载入内容
    	 * @return {[type]} [description]
    	 */
    	function loadData(page) {
    		var electric_number = $("#electric_number").val();
    		var pay_start_time = $("#pay_start_time").val();
    		var pay_end_time = $("#pay_end_time").val();
    		var is_fp = $("#is_fp").val();
    		if("已邮寄"==is_fp){
    			is_fp = "1";
    		}else if("未邮寄"==is_fp){
    			is_fp = "0";
    		}else{
    			is_fp = "";
    		}
    		var pay_status = $("#pay_status").val();
    		if("已支付"==pay_status){
    			pay_status = "1";
    		}else if("未支付"==pay_status){
    			pay_status = "0";
    		}else{
    			pay_status = "";
    		}
    		var o_sub_id = $("#o_sub_id").val();
    		if(o_sub_id!=""){
    			$("#subul").find("li").each(function(){
       				if(o_sub_id==$(this).html()){
       					o_sub_id = $(this).val();
       				}
        		});
    		}
    		$.get("${ctx}/H5/orderListByJson?pageNo="+page+"&_t="+Math.random()+"&electric_number="+electric_number+"&pay_start_time="+pay_start_time+"&pay_end_time="+pay_end_time+"&is_fp="+is_fp+"&pay_status="+pay_status+"&o_sub_id="+o_sub_id,function(data,status){
    			var json = eval("("+data+")");
    			if(json.message=="ok"){
    				$("#fansCount").html(json.totalResults);
    				$("#countsum").html(json.countsum);
    				$(".loading").remove();
    				var testData = json.items
    				for(var i in testData) {
    					if(testData[i].pay_type=="2"){
    						$("#zd-item").append(renderTpl(itemTpl, testData[i]));
    					}else{
    						$("#zd-item").append(renderTpl(itemTpl2, testData[i]));
    					}
    				}
    				stop = true;
    			}else if(json.message=="end"){
    				$("#fansCount").html(json.totalResults);
    				$("#countsum").html(json.countsum);
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
    	
    	function showInfo(o_no){
    		window.location.href = "${ctx}/H5/showOrder?o_no="+o_no; 
    	}
    </script>
<body>
</body>
</html>
