<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.Date"%>
<%@ include file="/WEB-INF/page/common/config.jsp"%>
<!DOCTYPE html>
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
	<link rel="stylesheet" type="text/css" href="${ctx}/plus/jPaginate/css/style.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/public/css/nav.css">
<style>
.codeTitle {
	margin-left:6px;
}
.public_area{
	margin-left:12px;
}
.pItem1 {
/* 		    border: 1px solid #dddddd; */
		    height: 32px;
		    border-radius: 4px;
		    overflow: hidden;
		    vertical-align: middle;
/* 		    width: 300px; */
		    position: relative;
		}
		.pItem {
		    border: 1px solid #dddddd;
		    height: 32px;
		    border-radius: 4px;
		    overflow: hidden;
		    vertical-align: middle;
		    width: 135px! important;
		    position: relative;
		    margin-right: 5px;
		}
		.public_area1 {
		    background: transparent;
		    width: 335px;
		    padding: 5px;
		    font-family: "Microsoft YaHei","微软雅黑";
		    border: 1px solid #ddd;
		    height: 30px;
		    -webkit-appearance: none;
		    margin-right: 20px;
		    border-radius: 4px;
		}
		
		.cearchBtn, .jyPjBtn1 {
	    text-align: center;
	    border: 1px solid #dddddd;
/* 	    color: #01a796; */
	    width: 215px! important;
	    height: 30px;
	    line-height: 27px;
	    overflow: hidden;
	    font-size: 16px;
	    background-color: #01a796;
    	color: #fff;
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
	<input type="hidden" id="projectPath" value="${ctx}" />
	<input type="hidden" id="lastLoginDate" value="<fmt:formatDate value="${admin_user.lastLogin}" pattern="yyyy-MM-dd "></fmt:formatDate>">
	<input id="cId" type="hidden" value="${admin_user.companyId}" />
	<input type="hidden" id="nowDate" value="<fmt:formatDate value="<%=new Date()%>" pattern="yyyy年MM月dd日"/>">
	<%@ include file="/WEB-INF/page/public/common/top.jsp"%>
	<!---->
	<div class="box main">
		<%@ include file="/WEB-INF/page/public/common/left.jsp"%>
		<div class="rightBox jyListBox">
			<div class="commBody jySearchBox">
				<form id="form1" name="form1" method="get" action="${ctx}/hPayOrder/getHPayOrderListIndex">
				    <input type="hidden" name="pageNo" value="${pageNo}" id="pageNo"/>
				    <input type="hidden" name="rowCount" value="10"/>
				    <input type="hidden" name="t_count" id="t_count" value="${hpayorderListCount}"/>
					<div class="lineP sItem">
						<div class="lines pItem" style="margin-right:20px;">
							<input type="text" placeholder="缴费号" class="pInput" name="electric_number" value="${electric_number}">
						</div>
						<div class="lines pItem1">
						<select id="is_fp" name="is_fp" class="public_area" style="margin-left:0px!important;margin-right:20px;border-radius: 4px;">
							<option value="">发票状态</option>
							<option value="">全部</option>
							<option value="1" <c:if test="${is_fp==1}">selected='selected'</c:if>>已邮寄</option>
       						<option value="0" <c:if test="${is_fp==0}">selected='selected'</c:if>>未邮寄</option>
						</select>
						</div>
						<div class="lines pItem1">
						<select id="pay_status" name="pay_status" class="public_area" style="margin-left:0px!important;margin-right:20px;border-radius: 4px;">
							<option value="">支付状态</option>
							<option value="">全部</option>
			            	<option value="1" <c:if test="${pay_status=='1'}">selected='selected'</c:if>>成功</option>
			            	<option value="0" <c:if test="${pay_status=='0'}">selected='selected'</c:if>>未支付</option>
			            	<option value="2" <c:if test="${pay_status=='2'}">selected='selected'</c:if>>失败</option>
						</select>
						</div>
						<div class="lines pItem1">
						<select id="o_sub_id" name="o_sub_id" class="public_area1">
							<option value="">分组名称</option>
							<option value="">全部分组</option>
				       		<c:forEach items="${listSubC}" var="subc">
				       			<option value="${subc.s_id}" <c:if test="${o_sub_id==subc.s_id}">selected</c:if>>${subc.sub_name}</option>
				       		</c:forEach>
						</select>
						</div>
					</div>
					<div class="lineP searchItem  sItem">
						<div class="lines pItem" style="width: 150px;margin-right:20px;">
							<input placeholder="支付开始时间" name="pay_start_time" type="text" value="${pay_start_time}" class="pInput" id="pay_start_time" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
						</div>
						<div class="lines pItem" style="width: 150px;margin-right:20px;">
							<input placeholder="支付结束时间" name="pay_end_time" value="${pay_end_time}" type="text" class="pInput" id="pay_end_time" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
						</div>
<!-- 					</div> -->
<!-- 					<div class="lineP searchItem  sItem"> -->
						<div class="lines pItem code" style="margin-right:20px;">
							<input class="pInput" placeholder="输入右侧验证码" type="text" id="vercode"/>
						</div>
						<img src="${ctx }/manageAdminUser/pcrimg" id="verfyImg" onclick="getImg()"  style="width:100px;height:36px;margin-bottom: -20px;cursor: pointer;"/> <a class="lines cearchBtn" style="background-color: #01a796;color: #fff;border-radius: 4px;" href="javascript:find();">查询</a>
					</div>
				</form>
			</div>
			<c:if test="${empty list}">
				<div class="msgBox" style="background-color: #ffffff;">
		        	<p class="msOne">暂无订单信息</p>
					<p class="msTwo"></p>
		        </div>
			</c:if>
			<c:if test="${not empty list}">
			<c:forEach items="${list}" var="order">
				<div class="commBody jyItem">
	        	<div class="titleBox blodjy">
	            	<span class="lines">订单号：<font>${order.o_no}</font></span>
	                <span class="lines">银行流水号：<font>${order.query_id}</font></span>
	                <span class="lines">交易总金额：<font><c:if test="${order.pay_status=='1'}">${order.amountStr}</c:if><c:if test="${order.pay_status!='1'}">0.00</c:if>元</font></span>
					<span class="lines">付款时间：<font><fmt:formatDate value="${order.pay_time}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></font></span>
	                <span class="lines">支付状态：<font>${order.pay_status=='1'?'已支付':'未支付' }</font></span>
	                <span class="lines">支付类型：<font>${order.pay_type=='2'?'手机支付':'银联B2B' }</font></span>
	                <c:if test="${order.evaluateFlag==0 }"><a class="jyPjBtn" data-id="2989389218301839108" href="javascript:evaluate('${order.o_no}');" id='pj${order.o_no}' style="background-color: #01a796;color: #fff;border-radius: 4px;">评价</a></c:if>
	            </div>
	            <c:forEach items="${order.listSubOrder}" var="subOrder" varStatus="status">
	            	
		            <div class="titleBox small on" <c:if test="${status.index>0 }">style="border-top:1px solid #dddddd;"</c:if>>
		                <span class="lines">分组名称：<font>${subOrder.sub_name}</font></span>
		                <span class="lines">发票收件人：<font>${subOrder.consignee}</font></span>
		                <div>
		                    <span class="lines">收件人手机号：<font>${subOrder.consignee_phone}</font></span>
		                    <span class="lines">收件人地址： <font>${subOrder.consignee_address}</font></span>
		                </div>
		            </div>
		            <div class="jlTableBox">
		            	<table class="jlTable" cellpadding="0" cellspacing="0" border="0" width="100%">
		                	<tr>
		                    	<th>序号</th>
		                        <th>业务类型</th>
		                        <th>缴费号</th>
		                        <th width="120">支付状态</th>
		                        <th>销账状态</th>
		                        <th>客户名称</th>
		                        <th>邮寄状态</th>
		                        <th>欠费金额</th>
		                        <th>交易金额</th>
		                    </tr>
		                    <c:forEach items="${subOrder.listSubOrder}" var="subOrder1" varStatus="status">
			                    <tr>
			                    	<td align="center">${status.index+1}</td>
			                        <td align="center">抄表电</td>
			                        <td align="center">${subOrder1.electric}</td>
			                        <td align="center">
			                        	<b>
											<c:if test="${order.pay_status=='1'}">成功</c:if>
					                    	<c:if test="${order.pay_status=='2'}">失败<font>(付款金额小于交费金额)</font></c:if>
					                    	<c:if test="${order.pay_status=='3'}">失败<font>(付款金额大于交费金额)</font></c:if>
					                    	<c:if test="${order.pay_status=='0'}">未支付</c:if>
					                    	<c:if test="${order.pay_status=='4'}">失败<c:if test="${not empty order.respMsg}">(${order.respMsg})</c:if></c:if>
					                    	<c:if test="${empty order.pay_status}">未支付</c:if>
										</b>
									</td>
									<td align="center">
			                        	<b>
											<c:if test="${subOrder1.tick_off_status==1}">成功</c:if>
					                    	<c:if test="${subOrder1.tick_off_status==0||empty order.tick_off_status}">未销账</c:if>
										</b>
									</td>
			                        <td align="left">${subOrder1.ammeter_name}</td>
			                        <td align="center"><c:if test="${subOrder1.isFP==1}">已邮寄<br>${subOrder1.express_name}<br>快递单号：${subOrder1.express_no}</c:if><c:if test="${subOrder1.isFP!=1}">未邮寄</c:if></td>
			                        <td align="center">${subOrder1.totalFeeStr}</td>
			                        <td align="center">
			                        	<c:if test="${order.pay_status=='1'}">${subOrder1.totalFeeStr}</c:if>
				                    	<c:if test="${order.pay_status!='1'}">0.00</c:if>
				                    </td>
			                    </tr>
		                    </c:forEach>
		                </table>
		                <div class="allJe">交费金额小计：<font><c:if test="${order.pay_status=='1'}">${subOrder.totalFeeStr}</c:if><c:if test="${order.pay_status!='1'}">0.00</c:if></font></div>
		            </div>
	            </c:forEach>
	        </div>
	        </c:forEach>
	        </c:if>
		</div>
	</div>
	<div class="lineP pagesBox">
	<div id="demo3" style="left: 44%" ></div>
	</div>
	<%@ include file="/WEB-INF/page/public/common/footer.jsp"%>
	<script src="${ctx}/public/js/jquery-1.8.2.min.js"></script>
	<script src="${ctx}/public/xcConfirm/js/xcConfirm.js"></script>
	<script src="${ctx}/public/js/public/electricPay.js"></script>
	<script src="${ctx}/js/area_public.js"></script>
	<script src="${ctx}/public/custom/public/js/custominput.js"></script>
	<script type="text/javascript" src="<c:url value='/boo/assets/plugins/My97DatePicker/WdatePicker.js'/>"></script>
	<script src="${ctx}/js/layer.js"></script>
	<script src="${ctx}/public/js/myPlaceholder.js"></script>
	<script src="${ctx }/plus/jPaginate/jquery.paginate.js"></script>
	<script type="text/javascript">
	$(function(){
		var returnNum = 10 ;
		var totalRecords = parseInt('${hpayorderListCount}');
		var yu = totalRecords%returnNum;
		var zs = parseInt(totalRecords/returnNum);
		if(yu > 0){
			zs +=1;
		}
		if(zs == 0)
			zs = 1;
		$("#demo3").paginate({
			count 		: zs,
			start 		: ${pageNo},
			display     : 10,
			border					: true,
			border_color			: '#dddddd',
			text_color  			: '#01a796',
			background_color    	: '#FFFFFF',	
			border_hover_color		: '#68BA64',
			text_hover_color  		: 'black',
			background_hover_color	: '#CAE6C6', 
			rotate      : false,
			images		: false,
			mouse		: 'press',
			onChange     			: function(page){
				$("#pageNo").val(page);
				$("#form1").submit();
			}
		});
	})
	function find(){
		var vercode = $("#vercode").val();
		if(vercode==""){
			$HXT.showInfo("请输入验证码！");
		}else{
			$.getJSON("<c:url value='/public/payFee/checkVercode'/>",{vercode:$("#vercode").val()},function(data){
				if(data.code==1){
					$("#form1").submit();
				}else{
					$HXT.showInfo("验证码不正确！");
					getImg();
				}
			})
		}
	}
	//评价
	function evaluate(no) {
		var title = '订单评价 <span class="ddbh">订单号：' + no + '</span>';
		var html = '<div class="wItemBox">';
		html += '<div class="lineP searchItem  sItem pjBox">';
		html += '<label class="lines codeTitle">服务满意度：</label>';
		html += '<div id="starBox" class="lines starBox" style="width:277px;">';
		html += '<i></i>';
		html += '<i></i>';
		html += '<i></i>';
		html += '<i></i>';
		html += '<i></i>';
		html += ' </div>';
		html += '</div>';
		html += '<div class="lineP searchItem  sItem pjBox">';
		html += '<label class="lines codeTitle" style="vertical-align:top; margin-top:5px;">评价：</label>';
		html += '<div style="width:306px! important;" class="lines pItem mapSt" style="vertical-align:top;">';
		html += '<textarea placeholder="输入内容在200文字以内。" id="evaluate_content"></textarea>';
		html += '</div>';
		html += '</div>';

		html += '</div>';
		var option = {
			title : title,
			btn : parseInt("0011", 2),
			isOkBtn : true,
			onOk : function() {
				var star = 0;
				$("#starBox i").each(function(index,obj){
					if($(obj).hasClass("on")){
						star++;
					}
				})
				if(star==0){
					layer.msg("请选择满意度！", {time:2000});
					return;
				}
				if($("#evaluate_content").val()==""){
					layer.msg("请填写评价", {time:2000});
					return;
				}
			    $.post("<c:url value='/hEvaluate/addHEvaluate'/>",
		        	{
			    		orderNo : no,
			    		star : star,
			    		content : $("#evaluate_content").val(),
						 _t:Math.random()},
		        	function(data){
			        	var result = eval('('+data+')'); 
			            if (result.code == '1') {
			              	layer.msg("评价成功!", {time:2000});
// 			              	$("#pj"+no).hide();
			              	window.location.reload();
			             } else {
			            	 layer.msg(result.message, {time:2000});
// 			            	 alert(result.message);
			             }
		       		});
			},
// 			class : [ 'okAddressBox', 'on' ]
		}

		var classArr = ['okAddressBox','on'];
		window.wxc.xcConfirm(html, "custom", option);
		setXcConfirmClass(classArr);
		var isOkBtn = true;
		if(isOkBtn){
			$('.'+classArr[0] + " .popBox .cancel").remove();
		}
		$("."+classArr[0] +" .popBox .sgBtn.ok").html('确定').addClass('notClose');
		function setXcConfirmClass(className){
			//$(".xcConfirm").addClass(className);
			
			if( className && className.length > 0){
				for(var i = 0; i <className.length ; i ++){
					$(".xcConfirm").addClass(className[i]);
				}
				
			}
		}
		
		$(document).on("click", "#starBox i", function() {
			var arr = [];
			arr = $("#starBox i");
			var index = $(this).index();
			$("#starBox i").removeClass("on");
			for (var i = 0; i <= index; i++) {
				if (i == 0 && $(arr[i]).hasClass("s_n")) {
					$(arr[i]).removeClass("on");
				} else {
					$(arr[i]).addClass("on");
				}

			}

		});
	}
	function getImg(){
		var url = '${ctx }/manageAdminUser/pcrimg?_t='+Math.random();
		$("#verfyImg").attr("src",url);
	}
	</script>
</body>
</html>