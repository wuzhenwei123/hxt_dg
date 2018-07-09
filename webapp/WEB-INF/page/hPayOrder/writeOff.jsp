<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>订单详情</h4>
</div>
<div class="modal-body">
	<table class="table table-striped">
        <thead>
            <tr>
                <th scope="col">序号</th>
                <th scope="col">订单号</th>
                <th scope="col">单位名称</th>
                <th scope="col">用电地址</th>
                <th scope="col">子单位名称</th>
                <th scope="col">电表号</th>
                <th scope="col">金额</th>
                <th scope="col">当前欠费</th>
                <th scope="col">支付状态</th>
                <th scope="col">销账状态</th>
                <th scope="col">操作</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="order" varStatus="index">
            <tr <c:if test="${order.fp_style==2}">style="color:red;"</c:if>>
            	<input type="hidden" id="consignee${order.o_sub_id}" value="${order.consignee}">
            	<input type="hidden" id="consignee_phone${order.o_sub_id}" value="${order.consignee_phone}">
            	<input type="hidden" id="consignee_address${order.o_sub_id}" value="${order.consignee_address}">
            	<input type="hidden" id="invoice_title${order.o_sub_id}" value="${order.invoice_title}">
            	<input type="hidden" id="electric${order.o_sub_id}" value="${order.electric}">
            	<input type="hidden" id="sub_id${order.o_sub_id}" value="${order.sub_id}">
            	<input type="hidden" id="money${order.o_sub_id}" value="${order.amount}">
                <td>${index.index+1 }</td>
                <td>${o_no}</td>
                <td>${order.c_name}</td>
                <td>${order.electric_address}</td>
                <td>${order.sub_name}</td>
                <td>${order.electric}</td>
                <td>${order.totalFeeStr}</td>
                <td>${order.now_totalFee}</td>
                <td><c:if test="${pay_status>0}">已支付</c:if><c:if test="${pay_status==0||empty pay_status}">未支付</c:if></td>
                <td id="iskp${order.o_sub_id}"><c:if test="${empty order.tick_off_status}">未销账</c:if><c:if test="${not empty order.tick_off_status&&order.tick_off_status==1}">已销账</c:if><c:if test="${not empty order.tick_off_status&&order.tick_off_status==0}">未销账</c:if></td>
                <td>
                <c:if test="${empty order.tick_off_status||order.tick_off_status==0}">
                <perm:tag permPath='/hPayOrder/writeOff' ><a class='btn btn-green btn-mini no-wrap' href='javascript:void(0);' onclick="writeOffs('${order.o_sub_id}','${order.o_id}');return false;" id="kpbt${order.o_sub_id}"><i class='aweso-icon-ok' />销账</a></perm:tag>
                </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<input type="hidden" id="o_sub_id" value="">
<input type="hidden" id="consignee{order.o_sub_id}" value="${order.consignee}">
<script type="text/javascript">
function showMessage(o_sub_id){
	$("#xxxxxindex").click();
	$("#aTitle").val($("#invoice_title"+o_sub_id).val());
	$("#aExpress_name").val("");
	$("#aExpress_no").val("");
	$("#o_sub_id").val(o_sub_id);
}

function writeOffs(o_sub_id,o_id){
	bootbox.confirm("<i class=' fontello-icon-emo-happy' />你确定要销账吗?","取消","确定", function(result) {
	if(result){
		$.post("<c:url value='/hPayOrder/writeOffone'/>",
       	{
			o_sub_id	:o_sub_id,
			o_id	:o_id,
			ranNum:Math.random()
		},
       	function(data){
        	var result = eval('('+data+')'); 
            if (result.code == '1') {
              	var pageNo = $("#currPage").val();   
              	$("#iskp"+o_sub_id).html("已销账");
            	$("#kpbt"+o_sub_id).hide();
              	tipOk("销账成功!");
             } else {
              	tipError(result.message);
             }
        });
	}else{
	}
});
}
</script>