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
                <th scope="col">开票状态</th>
                <th scope="col">发票类型</th>
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
<!--                 <td id="iskp${order.o_sub_id}"><c:if test="${empty order.tick_off_status}">未销账</c:if><c:if test="${not empty order.tick_off_status&&order.tick_off_status==1}">已销账</c:if><c:if test="${not empty order.tick_off_status&&order.tick_off_status==0}">未销账</c:if></td> -->
                <td><c:if test="${empty order.tick_off_status}">未销账</c:if><c:if test="${not empty order.tick_off_status&&order.tick_off_status==1}">已销账</c:if><c:if test="${not empty order.tick_off_status&&order.tick_off_status==0}">未销账</c:if></td>
                <td id="iskp${order.o_sub_id}"><c:if test="${empty order.fp_order_id}">未开票</c:if><c:if test="${not empty order.fp_order_id}">已开票</c:if></td>
                <td><c:if test="${order.fp_style==1}">恒信通发票</c:if><c:if test="${order.fp_style==2}">电力增值税普票</c:if><c:if test="${order.fp_style==3}">电力增值税专票</c:if><c:if test="${order.fp_style==4}">电力额定发票</c:if></td>
                <td>
                <c:if test="${empty order.fp_order_id&&pay_status==1&&order.tick_off_status==1}">
                <perm:tag permPath='/hPayOrder/kaipiao' ><a class='btn btn-green btn-mini no-wrap' href='javascript:void(0);' onclick="showMessage('${order.o_sub_id}','${order.fp_style}');return false;" id="kpbt${order.o_sub_id}"><i class='aweso-icon-ok' />开票</a></perm:tag>
                </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<input type="hidden" id="o_sub_id" value="">
<input type="hidden" id="consignee{order.o_sub_id}" value="${order.consignee}">
<a href="#stack8" id="xxxxxindex" style="display: none;" class="btn btn-primary btn-large btn-block" data-toggle="modal">Demo - Modal Stacled</a>
<div id="stack8" class="modal hide fade" tabindex="-1" data-focus-on="input:first">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
        <h4>填写发票信息</h4>
    </div>
    <div class="modal-body">
    	<form id="addFormXXX" class="well well-nice form-horizontal">
	         <div class="widget-body">
	              <div class="control-group" style="display: none;">
		             <label class="control-label" for="aTitle">发票抬头</label>
		             <div class="controls">
					    <input type="text" placeholder="发票抬头" id="aTitle" warnName="发票抬头" />
		                 <span class="help-inline text-red">*</span>
		             </div>
		         </div>
		         <div class="control-group">
		             <label class="control-label" for="aExpress_name">快递名称</label>
		             <div class="controls">
					    <input type="text" placeholder="快递名称" id="aExpress_name" isNotNull="true" warnName="快递名称" />
		                 <span class="help-inline text-red">*</span>
		             </div>
		         </div>
		         <input type='hidden' id="aMailType" value='1'>
		         <div class="control-group">
		             <label class="control-label" for="aExpress_no">快递单号</label>
		             <div class="controls">
					    <input type="text" placeholder="快递单号" id="aExpress_no" isNotNull="true" warnName="快递单号" />
		                 <span class="help-inline text-red">*</span>
		             </div>
		         </div>
	         </div>
		</form>
    </div>
    <div class="modal-footer"> <a type="button" id="closeOneIndex1111" data-dismiss="modal" class="btn btn-boo" style="display: none;">关闭</a><button type="submit" id="saveBtn" class="btn btn-green" onclick="save()">保存</button></div>
</div>
<div class="modal-footer">
    <button type="button" data-dismiss="modal" class="btn btn-boo">关闭</button>
</div>
<script type="text/javascript">
function showMessage(o_sub_id,fp_style){
	if(fp_style==""){
		tipError("请设置发票类型");
	}else{
		$("#xxxxxindex").click();
		$("#aTitle").val($("#invoice_title"+o_sub_id).val());
		$("#aExpress_name").val("");
		$("#aExpress_no").val("");
		$("#o_sub_id").val(o_sub_id);
	}
}

function save(){
	var o_sub_id = $("#o_sub_id").val();
	var o_no = '${o_no}';
	var title = getVal("aTitle");
	var express_no = getVal("aExpress_no");
	var express_name = getVal("aExpress_name");
	var mailType = getVal("aMailType");
	var money = $("#money"+o_sub_id).val();
	var sub_id = $("#sub_id"+o_sub_id).val();

	var userName = $("#consignee"+o_sub_id).val();
	var phone = $("#consignee_phone"+o_sub_id).val();
	var address = $("#consignee_address"+o_sub_id).val();
	var electric = $("#electric"+o_sub_id).val();
	
	var flag = validateForm('addFormXXX');
    if (flag){ 
        $.post("<c:url value='/hFp/addHFp'/>",
        	{
	    		orderNumber : o_no,
	    		title : title,
	    		money : money,
	    		express_no : express_no,
	    		express_status : 2,
	    		express_name : express_name,
	    		userName : userName,
	    		phone : phone,
	    		address : address,
	    		mailType : mailType,
	    		remark3 : o_sub_id,
	    		remark2 : electric,
	    		sub_id : sub_id,
			 _t:Math.random()},
        	function(data){
	        	var result = eval('('+data+')'); 
	            if (result.code == '1') {
	            	$("#iskp"+o_sub_id).html("已开票");
	            	$("#kpbt"+o_sub_id).hide();
	              	tipOk("保存成功!");
	              	$("#closeOneIndex1111").click();
	             } else {
	            	 tipError(result.message);
	             }
        });
    }
	
}
</script>