<!DOCTYPE html>
<%@ page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<html>
<head>
<meta charset="utf-8">
<title>${_title}</title>
	<meta name="keywords" content="${_keywords}" />
	<meta name="description" content="${_description}" />
	<meta content="textml;charset=utf-8" http-equiv="content-type">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<meta content="webkit|ie-comp|ie-stand" name="renderer">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="${ctx}/css/weui.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/public/css/hxtPay.css" rel="stylesheet" type="text/css" />
	<link href="<c:url value='/plus/webuploader/webuploader.css'/>" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="${ctx}/public/css/hxtPay.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/public/css/nav.css">
	<style type="text/css">
	.webuploader-pick{
	    		padding: 0px;
	    		width: 100%;
	    		color: #01a796;
	    		background:#fff;
	    	}
	   	._lPay{
	   		padding-top:10px!important;
	   		padding-bottom:10px!important;
	   	}
	   	.frist_td{
		border-right:1px solid #e9e9e9;
		padding:0;
	}
	.jyTableBox1 {
	    /* padding-left: 20px; */
	    padding-right: 20px;
	    /*padding-bottom: 20px;*/
	    overflow: hidden;
	}
	.loading .popBox{
			width:122px! important;
			height:122px! important;
			margin-left:-61px! important;
			margin-top:-61px! important;
			z-index:2147000001! important;
			border-radius: 5px! important;
		}
</style>
</head>
<body>
<input type="hidden" id="projectPath" value="${ctx}"/>
<input type="hidden" id="lastLoginDate" value="<fmt:formatDate value="${admin_user.lastLogin}" pattern="yyyy-MM-dd "></fmt:formatDate>">
<input type="hidden" id="nowDate" value='<fmt:formatDate value="<%=new Date() %>" pattern="yyyy年MM月dd日"/>'>
<input id="cId" type="hidden" value="${admin_user.companyId}" />
<%@ include file="/WEB-INF/page/public/common/top.jsp" %>
<!---->
<div class="box main">
	<%@ include file="/WEB-INF/page/public/common/left.jsp" %>
    <div class="rightBox right_pay_main">
	</div>
</div>
<div class="weui_dialog_confirm" style="display: none;">
    <div class="weui_mask"></div>
    <div class="weui_dialog">
        <div class="weui_dialog_hd">
          <strong class="weui_dialog_title">您确定需要删除吗？</strong>
        </div>
        <div class="weui_dialog_ft">
            <a href="javascript:;" class="weui_btn_dialog default" onclick="closeConfirm()">取消</a>
            <a href="javascript:;" class="weui_btn_dialog primary" onclick="suredel()">确定</a>
        </div>
    </div>
</div>
<div id="loadingToast" class="weui_loading_toast" style="display: none;">
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
        <p class="weui_toast_content">努力加载中</p>
    </div>
</div>
<div class="allCheckBox">
	<div class="allchBox">
    	<div class="pay_sz foot_check">
<!--             <div class="in-checkbox check_box"> -->
                <input type="checkbox" id="selectAllChecked" name="selectAllChecked" onclick="selectAll(this)"/>
                <label for="selectAllChecked">
                    <span>全选</span>
                </label>
<!--             </div> -->
        </div>
        
        <div class="lineP fr footer_all_c">
            <span class="line_l">已选择<font class="pay_yellow" id="ammeterCountAll">0</font>个缴费号</span>
            <a class="line_l pay_btn" href="javascript:setupAll();">开通手机缴费服务</a>	
        </div>
        
    </div>
</div>
<%@ include file="/WEB-INF/page/public/common/footer.jsp" %>
<script src="${ctx}/public/js/jquery-1.8.2.min.js"></script>
<script src="${ctx}/public/xcConfirm/js/xcConfirm.js"></script>
<script src="${ctx}/js/area_public.js"></script>
<script src="${ctx}/public/js/public/mAmmeter.js"></script>
<!-- <script src="${ctx}/public/custom/public/js/custominput.js"></script> -->
<!-- <script src="${ctx}/public/js/myPlaceholder.js"></script> -->
<!-- <script src="${ctx}/boo/assets/plugins/pl-system-info/notyfy/jquery.notyfy.js"></script> -->

<script src="${ctx}/js/layer.js"></script>
<!-- <script type="text/javascript" src="<c:url value='/plus/webuploader/webuploader.js'/>"></script> -->
<script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>
<script>
	$(function(){
// 		$("#jfHcm").click(function(){
// 				});
				
// 				$(document).on("click",".backUlBox > li",function(){
// 					$(".backUlBox > li").removeClass('on');
// 					$(this).addClass("on");
					
// 				});
	})
	function selectAll(obj){
// 		$('body').find('input[name=ammeterCheck]').each(function(index,obj){
// 			if($("#selectAllChecked")[0].checked){
// 				obj.checked = true;
// 				$(obj).next().addClass('checked');
// 			}else{
// 				obj.checked = false;
// 				$(obj).next().removeClass('checked');
// 			}
// 		})
// 		$('body').find('input[name=subCheck]').each(function(index,obj){
// 			if($("#selectAllChecked")[0].checked){
// 				obj.checked = true;
// 				$(obj).next().addClass('checked');
// 			}else{
// 				obj.checked = false;
// 				$(obj).next().removeClass('checked');
// 			}
// 		})
		if($(obj).attr("checked")=="checked"){
			$("input[name^='ammeterCheck']").each(function(){
				$(this).attr("checked","checked");
			});
			$("input[name='subCheck']").each(function(){
				$(this).attr("checked","checked");
			});
		}else{
			$("input[name^='ammeterCheck']").each(function(){
				$(this).removeAttr("checked");
			});
			$("input[name='subCheck']").each(function(){
				$(this).removeAttr("checked");
			});
		}
		
		updateAmmeterCount()
	}
	function updateAmmeterCount(){
		var allCount = 0;
// 		$('.subBox').each(function(index,obj){
// 			var count = 0;
// 			$(obj).find('input[name=ammeterCheck]:checked').each(function(index,obj){
// 				count++;
// 			})
// 			$("#ammeterCount_"+$(obj).attr('subId')).html(count);
// 			allCount+=count;
// 		});
		$("input[name^='ammeterCheck']").each(function(){
			if($(this).attr("checked")=="checked"){
				allCount++;
			}
		});
		$("#ammeterCountAll").html(allCount);
	}
	function showbank(){
		
		$.get("${ctx}/hBank/getHBankBaseList?_t="+Math.random(),function(data){
			var result = eval("("+data+")");
			var html = '<ul class=" lineP backUlBox">';
            if (result.code == 1) {
            	for (var k=0; k<result.items.length; k++){
            		if(result.items[k].state==1){
            			
            			var name = encodeURIComponent(result.items[k].name);
            			html += '<li class="lines bankItem"><a href="${ctx}/index/toDownload?link='+result.items[k].docUrl+'&name='+name+'" style="background-image:url(${ctx}/'+result.items[k].bigImg+');"><i></i></a></li>';
            		}else{
            			html += '<li class="lines bankItem"><a href="javascript:void(0);" style="background-image:url(${ctx}/'+result.items[k].bigImg+');"><i></i></a></li>';
            		}
            	}
            }
            html += '</ul>';
        	
        	var txt=  "注：点击您所对应的企业网银的银行图标，可以下载《企业网银操作手册》帮助您完成网银缴费。";
    		var option = {
    			title: "注：点击您所对应的企业网银的银行图标，可以下载《企业网银操作手册》帮助您完成网银缴费。",
    			btn: parseInt("0011",2),
    			onOk: function(){
//     				console.log("确认啦");
    			}
    		}
    		window.wxc.xcConfirm(html, "custom", option);
    		$(".popBox").css("height","500px");
    		$(".tt").css("color","#fa771f");
		});
		
		$(document).on("click",".backUlBox > li",function(){
			$(".backUlBox > li").removeClass('on');
			$(this).addClass("on");
			
		});
	}
</script>
</body>
</html>
