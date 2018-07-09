<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>详情</h4>
</div>
<input type="hidden" id="currPage2" value="1"><!-- 当前页码 -->
<input type="hidden" id="returnNum2" value="10"><!-- 分页返回 -->
<input type="hidden" id="sortColumn2" value=" a.id desc "><!-- 排序字段 -->
<div class="modal-body">
        <div id="page-content" class="page-content">
        <section>
        	<div class="row-fluid">
        	<div class="span12 widget widget-simple bg-gray-light">
            <div class="widget-body">
               <div class="widget-row form-inline row-fluid">
                <label class="margin5">类型:</label>
               	<select id="fType">
               		<option value="">--请选择--</option>
               		<option value="1">分润</option>
               		<option value="2">提现</option>
               	</select>
               	<label class="margin5">订单号:</label>
                <input type="text" id="fOrderNo" class="span2 margin5" placeholder="订单号">
                <label class="margin5">缴费号:</label>
				<input type="text" id="fAmmeterNo" class="span2 margin5" placeholder="缴费号">
<!--                 <label class="margin5">涉及订单号:</label> -->
<!--                	<input type="text" id="fOrderNumber" class="span2 margin5"> -->
             <a href="javascript:searchData2('1');" class="btn btn-green">查询</a> 
             </div>
<!--              <div class="widget-row form-inline row-fluid"> -->
<!--                 <label class="margin5">交易类型:</label> -->
<!--                 <select id="fTradeCode"> -->
<!--                		<option value="">--请选择--</option> -->
<!--                		<option value="3100">抄表电</option> -->
<!--                		<option value="4200">智能电</option> -->
<!--                	</select> -->
<!--                 <label class="margin5">客户编号:</label> -->
<!--                	<input type="text" id="fCustomerNumber" class="span2 margin5"> -->
<!--                </div> -->
           </div>
           </div>
           </div>
           </section>
            <section>
                <div class="row-fluid">
                	<div class="span12 widget widget-simple bg-gray-light">
                         <div class="widget-content" id="demo1">
                             <div class="widget-body">
                                 <table id="exampleDTC2" class="table table-striped table-content table-condensed boo-table table-hover bg-blue-light">
                             </table>
                             <div class="widget-footer">
                                 <div class="btn-toolbar pull-left">
                                 </div>
                                 <div class="pagination pagination-btn pull-right">
                                 	<div id="kkpager2"></div>
                                 </div>
                                 <!-- // pagination -->
                             </div>
                             </div>
                         </div>
                     </div>
                </div>
            </section>
        </div>
</div>
<div class="modal-footer">
    <button type="button" data-dismiss="modal" class="btn btn-boo">关闭</button>
</div>
<script type="text/javascript">
	var $modal = $('#temModal');
	
	$(document).ready(function(){
    	searchData2(1);
    });
	function searchData2(pageNo){
		var returnNum = $("#returnNum2").val();
			var sortColumn = $("#sortColumn2").val();
		    var userAccountId = '${id}';
		    var type = $("#fType").val();
		    var tradeCode = $("#fTradeCode").val();
		    var customerNumber = $("#fCustomerNumber").val();
		    var orderNumber = $("#fOrderNumber").val();
		    var orderNo = $("#fOrderNo").val();
		    var ammeterNo = $("#fAmmeterNo").val();
		    $.getJSON("<c:url value='/hUserAccountDetail/getHUserAccountDetailList'/>",
	        {
	        	sortColumn:sortColumn,
	    		userAccountId : userAccountId,
	    		type : type,
	    		tradeCode : tradeCode,
	    		customerNumber : customerNumber,
	    		orderNumber : orderNumber,
	    		orderNo : orderNo,
	    		ammeterNo : ammeterNo,
	    		pageNo: pageNo,
	    		rowCount: returnNum, 
				_t: Math.random()
	        },function(data){
            var result = data;
            if (result.code == 1) {
                setTableStr2(result, pageNo, returnNum);
            } else {
            	tipError("系统异常!");
            } 
	    });
	}
	function genTableHeader2(){
		var str = "<thead><tr>" ;
	    	str+= "<th onselectstart='return false' scope=\"col\">序号</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_type\" column='type' onselectstart='return false' scope=\"col\">类型</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_totalFee\" column='totalFee' onselectstart='return false' scope=\"col\">金额(元)</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_createTime\" width='135' column='createTime' onselectstart='return false' scope=\"col\">明细创建时间</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_orderFee\" column='orderFee' onselectstart='return false' scope=\"col\">来源订单金额(元)</th>";
	    	
	    	str+= "<th class=\"sortTh\" id=\"th_orderFee\"  onselectstart='return false' scope=\"col\">订单号</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_orderFee\"  onselectstart='return false' scope=\"col\">订单类型</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_orderFee\"  onselectstart='return false' scope=\"col\">电表号</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_orderFee\"  onselectstart='return false' scope=\"col\">分润比例</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_orderFee\"  onselectstart='return false' scope=\"col\">单位名称</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_orderFee\"  onselectstart='return false' scope=\"col\">单位联系人手机号</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_orderFee\"  onselectstart='return false' scope=\"col\">联系人姓名</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_orderFee\"  onselectstart='return false' scope=\"col\">支付时间</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_orderFee\"  onselectstart='return false' scope=\"col\">订单生成时间</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_orderFee\"  onselectstart='return false' scope=\"col\">销账时间</th>";
			str+="</tr></thead>";
		return str;
	}
	function setTableStr2(result, pageNo, returnNum){
	 	var tableStr = "<table id='dataTable' class=\"table table-striped table-content table-condensed boo-table table-hover bg-blue-light\">";
	    tableStr += genTableHeader2();
	    var number = (pageNo - 1) * returnNum;
        tableStr += "<tfoot></tfoot>";
        tableStr += "<tbody>";
	    for (var k=0; k<result.items.length; k++){      
	        tableStr += "<tr id='DataRow"+k+"'>";
	        tableStr += "<td >" + (number + k + 1) + "</td>";
		        if(result.items[k].type=="1"){
		        	tableStr += "<td>分润</td>";
		        }else if(result.items[k].type=="2"){
		        	tableStr += "<td>提现</td>";
		        }else{
		        	tableStr += "<td></td>";
		        }
		        tableStr += "<td>" + result.items[k].totalFee + "</td>";
		        tableStr += "<td>" + genDateTimeAll(result.items[k].createTime) + "</td>";
		        tableStr += "<td>" + result.items[k].orderDetailMoney + "</td>";
		        
		        tableStr += "<td>" + result.items[k].o_id + "</td>";
		        if(result.items[k].pay_type=="1"){
		        	tableStr += "<td>银联B2B</td>";
		        }else  if(result.items[k].pay_type=="2"){
		        	tableStr += "<td>手机支付</td>";
		        }else{
		        	tableStr += "<td></td>";
		        }
		        tableStr += "<td>" + result.items[k].ammeterNum + "</td>";
		        tableStr += "<td>" + result.items[k].rate.toFixed(4) + "</td>";
		        tableStr += "<td>" + result.items[k].c_name + "</td>";
		        tableStr += "<td>" + result.items[k].contact_phone + "</td>";
		        tableStr += "<td>" + result.items[k].contact_name + "</td>";
		        tableStr += "<td>" + result.items[k].orderTime + "</td>";
		        tableStr += "<td>" + genDateTimeAll(result.items[k].orderCreateTime) + "</td>";
		        tableStr += "<td>" + genDateTimeAll(result.items[k].tick_off_time) + "</td>";
	        tableStr += "</tr>";            
	    }
	    tableStr += "</tbody>";
	    $("#exampleDTC2").html(tableStr);
	    $("#currPage2").val(pageNo);	
	    genPageTag2(pageNo,result.totalResults,returnNum,'kkpager2');
	}
	// 更新
	function toUpdate(id) {
		show('<c:url value="/hUserAccountDetail/toUpdate/'+id+'"/>','');
	}
	
	function toTime(val){
		var result = "";
		if(val!=""){
			var  year = val.substring(0,4);
			var  mounth = val.substring(4,6);
			var  day = val.substring(6,8);
			var hour = val.substring(8,10);
			var miniturs = val.substring(10,12);
			var s = val.substring(12,14);
			result = year+"-"+mounth+"-"+day+" "+hour+":"+miniturs+":"+s;
		}
		return result;
	}
	
	
	// 添加
	function toAdd(){
		show('<c:url value="/hUserAccountDetail/toAdd"/>','');
	}
	// 删除所选
	function delAll(){
		var ids = '';
		$("[name='checkName']").each(function(){
		    	var ck = $(this).attr("checked");
		    	if(ck == 'checked'){
		    		ids+=$(this).attr("id")+",";
		    	}else{
		    	}
			})
		if(ids!=''){
			bootbox.confirm("<i class=' fontello-icon-emo-happy' />你确定要删除吗?","取消","确定", function(result) {
				if(result){
					$.post("<c:url value='/hUserAccountDetail/removeAllHUserAccountDetail'/>",
		        	{
						ids :ids,
						ranNum:Math.random()
					},
		        	function(data){
			        	var result = eval('('+data+')'); 
			            if (result.code == '1') {
			              	var pageNo = $("#currPage").val();           
			              	searchData(pageNo);
			              	tipOk("删除成功!");
			             } else {
			            	 tipError("删除失败!");
			             }
			        });
				}else{
					//取消
				}
			}); 
			
		}else{
			tip("请选择待删除条目!");
		}
	}
		// 单条删除
	function del(id){
	   if (id != ""){
		   bootbox.confirm("<i class=' fontello-icon-emo-happy' />你确定要删除吗?","取消","确定", function(result) {
			if(result){
				$.post("<c:url value='/hUserAccountDetail/removeHUserAccountDetail'/>",
	        	{
					id	:id,
					ranNum:Math.random()
				},
	        	function(data){
		        	var result = eval('('+data+')'); 
		            if (result.code == '1') {
		              	var pageNo = $("#currPage").val();           
		              	searchData(pageNo);
		              	tipOk("删除成功!");
		             } else {
		              	tipError("删除失败!");
		             }
		        });
			}else{
				//取消
			}
		});
   	   }
    }
</script>
