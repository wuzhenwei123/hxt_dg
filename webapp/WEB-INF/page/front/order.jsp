<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${_titleIndex}</title>
<meta name="keywords" content="恒信通,易付通,企业网上交电费,公司网上交电费" />
<meta name="description" content="北京恒信通电信服务有限公司，成立于2002年，是国家电网北京市电力公司指定的代收费机构，恒信通依托“易付通”公共事业缴费服务品牌，为企业、公司、物业、工厂等企事业单位提供便捷的、一站式的网上交电费服务。" />
<meta content="textml;charset=utf-8" http-equiv="content-type">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta content="webkit|ie-comp|ie-stand" name="renderer">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<c:url value='/boo/assets/js/lib/jquery.js'/>"></script>
<script type="text/javascript" src="<c:url value='/boo/assets/plugins/My97DatePicker/WdatePicker.js'/>"></script>
<%-- <%@ include file="/WEB-INF/page/common/js.jsp" %> --%>
<style type="text/css">
	.hz01 td{
		color:#F1511B;
	}
</style>
</head>

<body>
<%@ include file="/WEB-INF/page/common/headerIndex.jsp" %>

<table width="990" border="0" align="center" cellpadding="1" cellspacing="1" class="beijs">
  <tr>
    <td width="224" valign="top" bgcolor="#FFFFFF"><table width="100%" border="0" cellpadding="1" cellspacing="0" class="beijs">
      <tr>
        <td bgcolor="#FFFFFF"><table width="212" border="0" align="right" cellpadding="0" cellspacing="0">
          <tr>
            <td width="183" height="137" bgcolor="#FFFFFF" class="heizi16">您上次的登录时间：<br />
              <span class="dizhi"><fmt:formatDate value="${admin_user.lastLogin}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></span></td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td height="1" bgcolor="#999999"></td>
      </tr>
      <tr>
      <%@ include file="/WEB-INF/page/common/navIndex.jsp" %>
      </tr>
      <tr>
        <td height="1" bgcolor="#999999"></td>
      </tr>
      <%@ include file="/WEB-INF/page/common/navIndex2.jsp" %>
      
    </table>
    
    </td>
    
    <td align="left" valign="top" bgcolor="#FFFFFF">
    <form id="form1" name="form1" method="get" action="${ctx}/hPayOrder/getHPayOrderListIndex">
    <input type="hidden" name="pageNo" value="${pageNo}" id="pageNo"/>
    <input type="hidden" name="rowCount" value="10"/>
    <input type="hidden" name="t_count" id="t_count" value="${hpayorderListCount}"/>
    <table width="700" border="0" align="center" cellpadding="0" cellspacing="0">
<!--       <tr> -->
<!--         <td height="40" align="right">缴费单号：</td> -->
<!--         <td width="110"><input name="o_no" type="text" style="width:100px" class="bk_yz" id="o_no" value="${o_no}"/></td> -->
       
<!--       </tr> -->
      <tr>
        <td height="40" align="right" nowrap="nowrap">抄表电客户编号：</td>
        <td><input name="electric_number" style="width:200px;width:100px" type="text" class="bk_yz" id="electric_number" value="${electric_number}"/></td>
        <td align="right" nowrap="nowrap">起始时间：</td>
        <td class="hz01"><input name="pay_start_time" type="text" value="${pay_start_time}" style="width:100px" class="bk_yz" id="pay_start_time" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/></td>
        <td align="right" nowrap="nowrap">结束时间：</td>
        <td align="left" class="hz01"><input name="pay_end_time" value="${pay_end_time}" type="text" style="width:100px" class="bk_yz" id="pay_end_time" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/></td>
	</tr>
      <tr>
      	 <td align="right">子单位名称：</td>
        <td width="210" class="hz01">
        	<select name="o_sub_id" id="o_sub_id" class="bk_yz" style="width:200px">
        		<option value="">全部子单位</option>
        		<c:forEach items="${listSubC}" var="subc">
        			<option value="${subc.s_id}" <c:if test="${o_sub_id==subc.s_id}">selected</c:if>>${subc.sub_name}</option>
        		</c:forEach>
        	</select>
        </td>
        <td align="right">支付状态：</td>
        <td width="110" class="hz01">
        	<select id="pay_status" style="width:100px" class="bk_yz" name="pay_status">
            	<option value="">请选择</option>
            	<option value="1" <c:if test="${pay_status=='1'}">selected='selected'</c:if>>成功</option>
            	<option value="0" <c:if test="${pay_status=='0'}">selected='selected'</c:if>>未支付</option>
            	<option value="2" <c:if test="${pay_status=='2'}">selected='selected'</c:if>>失败</option>
            </select>
        </td>
        <td height="40" align="right" width="60px;">发票状态：</td>
        <td>
        	<select id="tick_off_status" style="width:100px" class="bk_yz" name="tick_off_status">
            	<option value="">请选择</option>
            	<option value="1" <c:if test="${tick_off_status=='1'}">selected='selected'</c:if>>已邮寄</option>
            	<option value="0" <c:if test="${tick_off_status=='0'}">selected='selected'</c:if>>未邮寄</option>
            </select>
        </td> 
<!--         <td height="40" align="right">销账状态：</td> -->
<!--         <td> -->
<!--         	<select id="tick_off_status" style="width:100px" class="bk_yz"> -->
<!--             	<option value="">请选择</option> -->
<!--             	<option value="1" <c:if test="${tick_off_status=='1'}">selected='selected'</c:if>>已销账</option> -->
<!--             	<option value="0" <c:if test="${tick_off_status=='0'}">selected='selected'</c:if>>未销账</option> -->
<!--             </select> -->
<!--         </td> -->
        <td align="left" class="hz01"><a href="javascript:void(0)" onClick="find()" style="width:100px" class="lj_bc_14">查询</a></td>
      </tr>
      </table>
      </form>
      <div class="borderNone"></div>
      
      <style type="text/css">
		.listOrderBox th{
			white-space:nowrap;
		}
		.listOrderBox td,.listOrderBox th{
			text-align:center;
			padding:5px;
		}
      	.liBox{
      	padding:15px 25px;
      	display:block;
      }
      .liBox li{
      	border-bottom:1px solid #808080;
		position:relative;
		padding-right:90px;
      }
	  
      .liBox li p span{
		  font-weight:bolder;
		  
      }
	  .liBox li p span,.liBox li p font{
		  display:inline-block;
		  *display:inline;
		  *zoom:1;
		 }
	   .liBox li p span,.liBox li p font{
		   color:#333333;
		   font-size:14px;
		   padding-left:5px;
		}
		.lj_bc_info{
			position:absolute;
			right:0px;
			top:50%;
			margin-top:-35px;
		}
		.orderTitleBox{
			border:1px solid #808080;
			margin-left:20px;
			margin-right:20px;
			overflow:hidden;
			padding-top:15px;
			padding-bottom:15px;
			position:relative;
		}
		.orderTitleBox{
			border-bottom:1px solid #808080;
		}
		.fl{
			float:left;
		}
		.fr{
			float:right;
		}
		.clear{
			clear:both;
			line-height:0;
			font-size:0;
			display:block;
			overflow:hidden;
		}
		.orderLlBox{
			padding-left:15px;
			padding-right:15px;
			line-height:25px;
		}
		.orderLlBox.un{
			border-bottom:1px solid #808080;
		}
		.myOrderTitle{
			background-repeat:repeat-x;
			margin:0;
			padding-top:5px;
			padding-bottom:5px;
			overflow:hidden;
		}
		.subline{
			background-repeat:repeat;
			margin-top: 10px;
			padding-top:5px;
			padding-bottom:5px;
			background:url(${ctx}/images/xx01.png) repeat-x;
		}
		.listOrderBox{
			padding-left:10px;
			padding-right:10px;
			overflow:hidden;
		}
		.listOrderBox{
			
			border:0 none;
		}
		.orderTitleBox span{
			font-size:14px;
		}
		td,th{
			color:#333333;
			font-size:12px;
		}
		.disable td{
			color:#cccccc;
		}
		.orderTitleBox{
			margin-bottom:20px;
			overflow:hidden;
		}
		.listOrderBox table{
			border-bottom:1px solid #808080;
		}
		.listOrderBox table:last-child{
			border-bottom:0 none;
		}
      </style>
      
      <div class="orderBox">
      	 <c:forEach items="${list}" var="order">
      	 	<div class="orderTitleBox">
      	 		<div class="orderLlBox">
	                <span class="fl"><strong>订单号：${order.o_no}</strong></span>
	                <span class="fl" style="padding-left:20px;"><strong>银行流水号：${order.query_id}</strong></span>
	                <span class="clear"></span>
	             </div>
	             <div class="orderLlBox un">
	                <span class="fl"><strong>付款时间：<fmt:formatDate value="${order.pay_time}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></strong></span>
	                <span class="fr"><strong>交费总金额：￥<c:if test="${order.pay_status=='1'}">${order.amountStr}</c:if><c:if test="${order.pay_status!='1'}">0.00</c:if></strong></span>
	                <span class="clear"></span>
	    		</div>
	    		<div class="listOrderBox">
	    			<c:forEach items="${order.listSubOrder}" var="subOrder">
	    				<div class="myOrderTitle">
		                    <span class="fl">子单位名称：${subOrder.sub_name}</span>
		                    <span class="fr">交费金额小计：￥<c:if test="${order.pay_status=='1'}">${subOrder.totalFeeStr}</c:if><c:if test="${order.pay_status!='1'}">0.00</c:if></span>
		                    <span class="clear"></span>
							<div class="subline"></div>
							<%--<hr style="height:1px;border:none;border-top:1px dashed grey;" />--%>
		                    <table cellpadding="0" cellspacing="0" border="0" width="100%">
			                    <tr>
				                	<th width="8%">序号</th>
				                    <th width="10%">业务类型</th>
				                    <th width="20">客户编号</th>
				                    <th width="10%">交费状态</th>
				                    <th width="10%">销账状态</th>
				                    <th width="10%">用电客户名称</th>
				                    <th width="10%">发票状态</th>
				                    <th width="10%">欠费金额</th>
				                    <th>交费金额</th>
				                </tr>
				                <c:forEach items="${subOrder.listSubOrder}" var="subOrder1" varStatus="status">
				                	<tr>
					                	<td align="center">${status.index+1}</td>
					                    <td align="left">抄表电</td>
					                    <td align="left">${subOrder1.electric}</td>
					                    <td align="left">
					                    	<c:if test="${order.pay_status=='1'}">成功</c:if>
					                    	<c:if test="${order.pay_status=='2'}">失败(付款金额小于交费金额)</c:if>
					                    	<c:if test="${order.pay_status=='3'}">失败(付款金额大于交费金额)</c:if>
					                    	<c:if test="${order.pay_status=='0'}">未支付</c:if>
					                    	<c:if test="${order.pay_status=='4'}">失败<c:if test="${not empty order.respMsg}">(${order.respMsg})</c:if></c:if>
					                    	<c:if test="${empty order.pay_status}">未支付</c:if>
					                    </td>
					                    <td align="left">
					                    	<c:if test="${not empty subOrder1.tick_off_status&&subOrder1.tick_off_status=='0'}">失败</c:if>
					                    	<c:if test="${order.pay_status=='1'}"><c:if test="${empty subOrder1.tick_off_status}">成功</c:if></c:if>
					                    </td>
					                    <td>${subOrder1.ammeter_name}</td>
				                    	<td align="center"><c:if test="${subOrder1.isFP==1}">已邮寄</c:if><c:if test="${subOrder1.isFP!=1}">未邮寄</c:if> </td>
					                    <td align="right">${subOrder1.totalFeeStr}</td>
					                    <td align="right">
					                    	<c:if test="${order.pay_status=='1'}">${subOrder1.totalFeeStr}</c:if>
					                    	<c:if test="${order.pay_status!='1'}">0.00</c:if>
					                    </td>
					                </tr>
				                </c:forEach>
		                    </table>
						</div>
	    			</c:forEach>
	    		</div>
      	 	</div>
      	 </c:forEach>
      </div>
      
      
<!--       <ul class="liBox"> -->
<!--        <c:forEach items="${list}" var="order"> -->
<!--       	<li> -->
<!--         	<p><span>缴费单号：</span><font>${order.o_no}</font></p> -->
<!--             <p><span>银行流水号：</span><font>${order.query_id}</font></p> -->
<!--             <p><span>交费金额：</span><font><fmt:formatNumber value="${order.amount}" pattern="0.00"></fmt:formatNumber></font></p> -->
<!--             <p><span>子单位名称：</span><font>${order.o_sub_name}</font></p> -->
<!--             <p><span>支付状态：</span><font><c:if test="${order.pay_status=='1'}">已支付</c:if> -->
<!--             <c:if test="${order.pay_status=='2'}">已支付(短款)</c:if> -->
<!--             <c:if test="${order.pay_status=='3'}">已支付(长款)</c:if> -->
<!-- 	        	<c:if test="${empty order.pay_status||order.pay_status=='0'}">未支付</c:if></font></p> -->
<!--             <p><span>销账状态：</span><font><c:if test="${order.tick_off_status=='1'}">已销账</c:if> -->
<!-- 	        	<c:if test="${order.tick_off_status!='1'}">未销账</c:if></font></p> -->
<!--             <p><span>支付时间：</span><font><fmt:formatDate value="${order.pay_time}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></font></p> -->
<!--             <p><span>交费单类型：</span><font>抄表电</font></p> -->
<!--             <p><span>涉及电表号：</span><font>${order.electric_number}</font></p> -->
<!--             <a href="javascript:void(0)" onclick="showFP('${order.o_id}')" style="width:70px" class="lj_bc_14 lj_bc_info">发票信息</a> -->
<!--         </li> -->
<!--         </c:forEach> -->
<!--       </ul> -->
  
  <!--      
      <table width="700" align="center" cellpadding="1" cellspacing="1" class="beijs">
      <tr>
        <td width="10%" align="center" bgcolor="#D6D6D6">缴费单号</td>
        <td width="20%" align="center" bgcolor="#D6D6D6">银行流水号</td>
        <td width="10%" align="center" bgcolor="#D6D6D6">交费金额</td>
        <td width="30%" align="center" bgcolor="#D6D6D6">子单位名称</td>
        <td width="5%" align="center" nowrap="nowrap" bgcolor="#D6D6D6">支付状态</td>
        <td width="5%" align="center" nowrap="nowrap" bgcolor="#D6D6D6">销账状态</td>
        <td width="15%" align="center" bgcolor="#D6D6D6">支付时间</td>
        <td width="10%" align="center" nowrap="nowrap" bgcolor="#D6D6D6">交费单类型</td>
        <td align="center" bgcolor="#D6D6D6">操作</td>
      </tr>
      <c:forEach items="${list}" var="order">
      	 <tr>
      		 <td height="40" align="center" nowrap="nowrap" bgcolor="#FFFFFF">${order.o_no}</td>
	        <td align="center" bgcolor="#FFFFFF">${order.query_id}</td>
	        <td align="center" bgcolor="#FFFFFF">${order.amount}</td>
	        <td align="center" bgcolor="#FFFFFF">${order.o_sub_name}</td>
	        <td align="center" bgcolor="#FFFFFF">
	        	<c:if test="${order.pay_type=='1'}">已支付</c:if>
	        	<c:if test="${order.pay_type!='1'}">未支付</c:if>
	        </td>
	         <td align="center" bgcolor="#FFFFFF">
	        	<c:if test="${order.tick_off_status=='1'}">已销账</c:if>
	        	<c:if test="${order.tick_off_status!='1'}">未销账</c:if>
	        </td>
	        <td align="center" bgcolor="#FFFFFF"><fmt:formatDate value="${order.pay_time}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
	        <td align="center" bgcolor="#FFFFFF">抄表电</td>
	        <td align="center" bgcolor="#FFFFFF" class="hz01"><a href="javascript:void(0)" onclick="showFP('${order.o_id}')" style="width:70px" class="lj_bc_14">发票信息</a></td>
    	</tr>
    </c:forEach>
    </table>-->
      <table width="700" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td>&nbsp;</td>
        </tr>
      </table>
      <c:if test="${hpayorderListCount>10}">
      <table width="480" border="0" align="center" cellpadding="0" cellspacing="1" class="beijs">
      	 <tr id="pg">
      <script type="text/javascript">
		var pageNumber = parseInt($("#pageNo").val(), 10);
		var count = $("#t_count").val();
		var allPages = 0;
		if( count != 0 ) {
			if( count % 10 == 0 ) {
				allPages = count/10;
			} else {
				allPages = Math.floor(count/10)+1;
			}
		}
		var html = "<td align='center' bgcolor='#e7e7e7' class='heizi12'>"+pageNumber+"/"+allPages+"</td>";
		html +="<td width='33' height='29' align='center' bgcolor='#FFFFFF' class='heizi12'><img src='${ctx }/images/zj01.jpg' width='42' height='28' onclick=\"goPage(1)\"></td>";
		html +="<td width='33' height='29' align='center' bgcolor='#FFFFFF' class='heizi12'><img src='${ctx }/images/zk01.jpg' width='42' height='28' onclick=\"previousPage()\"></td>";
		var pageNo = pageNumber;
		if(pageNo  == 0) {pageNo++;}
		for(var i = 0; i < allPages; i ++) {
			var flowNumber = (i + 1);	// 当前正在循环的页数
			html += "<td width='33' height='29' align='center' bgcolor='#FFFFFF' class='heizi12'><a href='#' class='lj_kuai_12'";	// IE8没有this.text属性
			html += "<a herf=\"javascript:void(0)\" style=\"cursor: pointer;\"  onclick=\"goPage("+flowNumber+")\"";	// IE8没有this.text属性
			if(flowNumber == pageNo) {		// 如果是点击的页数，则将class设置为current，用以标识选中
				html += " id=\"page_select\" class=\"current\"";
			}
			if(allPages > 10) { // 只显示10页，两边的隐藏
				if(pageNo <= 5) {
					if(flowNumber > 10) {
						html += " style=\"display:none\"";
					}
				} else if(pageNo > (allPages - 5)) {
					if(flowNumber <= (allPages - 10)) {
						html += " style=\"display:none\"";
					}
				} else {
					if((pageNo - flowNumber) > 4 || (flowNumber - pageNo) > 5) {
						html += " style=\"display:none\"";
					}
				}
			}
			html +=	">" + flowNumber + "</a></td>";
		}
		html += "<td width='33' height='29' align='center' bgcolor='#FFFFFF' class='heizi12'><img src='${ctx }/images/yk01.jpg' width='41' height='28' onclick=\"nextPage()\"></td>";
		html += "<td width='33' height='29' align='center' bgcolor='#FFFFFF' class='heizi12'><img src='${ctx }/images/yj01.jpg' width='41' height='28' onclick=\"goPage("+allPages+")\"></td>";
		$("#pg").html(html);
	</script>
      
        </tr>
    </table>
    </c:if>
      <table width="700" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td>&nbsp;</td>
        </tr>
    </table></td>
  </tr>
</table>
<table width="990" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="554">&nbsp;</td>
  </tr>
</table>
<%@ include file="/WEB-INF/page/common/footerIndex.jsp" %>
<script type="text/javascript">
	function find(){
		$("#form1").submit();
	}
	/** 上一页 */
	function previousPage(){
		if(pageNumber == 1) {
			return;
		}
		$("#pageNo").val(pageNumber - 1);
		search(pageNumber - 1);
	}


	/** 下一页 */
	function nextPage(){
		if(pageNumber == allPages) {
			return;
		}
		$("#pageNo").val(pageNumber+1);
		search(pageNumber + 1);
	}
	/** 前往指定页面 */
	function goPage(pageNumber) {
		$("#pageNo").val(pageNumber);
		search(pageNumber);
	}
	
	function search(pageNumber){
		$("#form1").submit();
	}
</script>
</body>
</html>
