<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>添加</h4>
</div>
<div class="modal-body">
    <form id="addForm" class="well well-nice form-horizontal">
         <div class="control-group">
             <label class="control-label" for="aProxyName">联系人姓名</label>
             <div class="controls">
			    <input type="text" id="aProxyName" isNotNull="true" warnName="proxyName" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aSex">联系人性别</label>
             <div class="controls">
		    	<select id="aSex"> 
                    <option value="1">男</option> 
                    <option value="0">女</option> 
            	</select> 
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group" id="mobilediv1">
             <label class="control-label" for="aProxyPhone">联系人电话</label>
             <div class="controls">
			    <input type="text" id="aProxyPhone" isNotNull="true" warnName="proxyPhone" datatype="phone" onkeyup="this.value=this.value.replace(/\D/gi,'')"/>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aProxyAddress">联系人地址</label>
             <div class="controls">
			    <input type="text" id="aProxyAddress" isNotNull="true" warnName="proxyAddress" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aProxyCode">联系人邮编</label>
             <div class="controls">
			    <input type="text" id="aProxyCode" isNotNull="true" warnName="proxyCode" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aRemindStartDate">信息提醒日期</label>
             <div class="controls">
			    <input type="text" id="aRemindStartDate" style="width: 85px;" isNotNull="true" warnName="remindStartDate" onkeyup="this.value=this.value.replace(/\D/gi,'')"/>
                ~
                <input type="text" id="aRemindEndDate" isNotNull="true" style="width: 85px;" warnName="remindEndDate" onkeyup="this.value=this.value.replace(/\D/gi,'')"/>
                <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aState">状态</label>
             <div class="controls">
		    	<select id="aState"> 
                    <option value="1">正常</option> 
                    <option value="0">禁用</option> 
            	</select> 
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aBank_number">付款行行号</label>
             <div class="controls">
			    <input type="text" id="aBankName" value="201581000018" isNotNull="true" warnName="付款行行号" style="width: 300px;" readonly="readonly" onclick="selOneindex2()"/>
			    <a type="button" class="btn btn-boo" onclick="cleanOnIndex2()">清除</a>
	           	<a href="#stack2" id="xxxindex2" style="display: none;" class="btn btn-primary btn-large btn-block" data-toggle="modal">Demo - Modal Stacled</a> 
	           	<input type="hidden" id="aBank_number" value="201581000018" isNotNull="true" warnName="付款行行号"/>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aPayBankNumber">付款人账号</label>
             <div class="controls">
			    <input type="text" id="aPayBankNumber" isNotNull="true" warnName="付款人账号" value="6225260101653000" oninput="OnInput(event)" onpropertychange="OnPropChanged(event)"/>
			    <a type="button" class="btn btn-boo" onclick="getNumber()">获取用户编号和合同号</a>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aUserNumber">用户编号</label>
             <div class="controls">
			    <input type="text" id="aUserNumber" readonly="readonly" isNotNull="true" warnName="用户编号" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aContractNumber">合同编号</label>
             <div class="controls">
			    <input type="text" readonly="readonly" id="aContractNumber" isNotNull="true" warnName="contractNumber" style="width: 350px;"/>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aContractStartTime">合同开始日期</label>
             <div class="controls">
		     	<input type="text" id="aContractStartTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="contractStartTime" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aContractEndTime">合同结束日期</label>
             <div class="controls">
		     	<input type="text" id="aContractEndTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="contractEndTime" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aPayName">付款人姓名</label>
             <div class="controls">
			    <input type="text" id="aPayName" isNotNull="true" warnName="付款人姓名" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group" id="zjno">
             <label class="control-label" for="aPayCard">付款人身份证号</label>
             <div class="controls">
			    <input type="text" id="aPayCard" isNotNull="true" warnName="付款人身份证号" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aPayPhoneNumber">付款人手机号</label>
             <div class="controls">
			    <input type="text" id="aPayPhoneNumber" isNotNull="true" warnName="付款人手机号" datatype="phone" onkeyup="this.value=this.value.replace(/\D/gi,'')"/>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aPayMail">付款人邮箱</label>
             <div class="controls">
			    <input type="text" id="aPayMail" isNotNull="true" warnName="付款人邮箱" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aCId">隶属客户</label>
             <div class="controls">
			    <input type="text" id="aCName" readonly="readonly" onclick="selOneindex3()"/>
			    <a type="button" class="btn btn-boo" onclick="cleanOnIndex()">清除</a>
	           	<a href="#stack3" id="xxxindex3" style="display: none;" class="btn btn-primary btn-large btn-block" data-toggle="modal">Demo - Modal Stacled</a> 
	           	<input type="hidden" id="aCId" isNotNull="true" warnName="隶属客户"/>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aInfo">附言</label>
             <div class="controls">
			    <input type="text" id="aInfo" warnName="附言" />
             </div>
         </div>
     </form>
 </div>
 <div class="modal-footer">
     <button type="button" data-dismiss="modal" class="btn btn-boo">取消</button>
     <button type="submit" class="btn btn-green" onclick="add()">保存</button>
 </div>
 
 <div id="stack3" class="modal hide fade" tabindex="-1" data-focus-on="input:first">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
        <h4>选择客户</h4>
    </div>
    <div class="modal-body">
	    <input type="text" id="fOneAgentNameIndex" class="span2 margin5" placeholder="客户名称">
	    <a href="javascript:searchData3('1');" class="btn btn-green">查询</a>
    	<div class="widget-content" id="demo3">
	         <div class="widget-body">
	             <table id="exampleDTC3" class="table table-striped table-content table-condensed boo-table table-hover bg-blue-light">
		         </table>
		         <div class="widget-footer">
		             <div class="btn-toolbar pull-left">
		             </div>
		             <div class="pagination pagination-btn pull-right">
		             	<div id="kkpager3"></div>
		             </div>
		         </div>
	         </div>
		</div>
    </div>
    <div class="modal-footer"> <a type="button" id="closeOneIndex3" data-dismiss="modal" class="btn btn-boo">关闭</a></div>
    <input id="returnNum3" type="hidden" value="10" />
    <input id="currPage3" type="hidden" value="1"/>
    <input id="sortColumn3" type="hidden" value=""/>
</div>
<div id="stack2" class="modal hide fade" tabindex="-1" data-focus-on="input:first" style="widows: 600px;">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
        <h4>选择付款行</h4>
    </div>
    <div class="modal-body">
	    <input type="text" id="fOneAgentNameIndex2" class="span2 margin5" placeholder="付款行名称">
	    <a href="javascript:searchData2('1');" class="btn btn-green">查询</a>
    	<div class="widget-content" id="demo4">
	         <div class="widget-body">
	             <table id="exampleDTC2" class="table table-striped table-content table-condensed boo-table table-hover bg-blue-light">
		         </table>
		         <div class="widget-footer">
		             <div class="btn-toolbar pull-left">
		             </div>
		             <div class="pagination pagination-btn pull-right">
		             	<div id="kkpager2"></div>
		             </div>
		         </div>
	         </div>
		</div>
    </div>
    <div class="modal-footer"> <a type="button" id="closeOneIndex2" data-dismiss="modal" class="btn btn-boo">关闭</a></div>
    <input id="returnNum2" type="hidden" value="10" />
    <input id="currPage2" type="hidden" value="1"/>
</div>
<script type="text/javascript">
	var flagsss = true;
	//执行添加
	function add(){
		var id = getVal("aId");
		var proxyName = getVal("aProxyName");
		var proxyPhone = getVal("aProxyPhone");
		var createYime = getVal("aCreateYime");
		var contractNumber = getVal("aContractNumber");
		var contractStartTime = getVal("aContractStartTime");
		var contractEndTime = getVal("aContractEndTime");
		var remindStartDate = getVal("aRemindStartDate");
		var remindEndDate = getVal("aRemindEndDate");
		var bank_number = getVal("aBank_number");
		var info = getVal("aInfo");
		var remark1 = getVal("aRemark1");
		var remark2 = getVal("aRemark2");
		var remark3 = getVal("aRemark3");
		var cId = getVal("aCId");
		var userId = getVal("aUserId");
		var state = getVal("aState");
		var userNumber = getVal("aUserNumber");
		var proxyAddress = getVal("aProxyAddress");
		var proxyCode = getVal("aProxyCode");
		var payBankNumber = getVal("aPayBankNumber");
		var payName = getVal("aPayName");
		var payCardStyle = getVal("aPayCardStyle");
		var payCard = getVal("aPayCard");
		var payPhoneNumber = getVal("aPayPhoneNumber");
		var payMail = getVal("aPayMail");
		var sex = getVal("aSex");
		var flag = validateForm('addForm');
	    if (flag&&flagsss){ 
	    	flagsss = false;
	    	if(!IdentityCodeValid(payCard)){
				$("#aPayCard").next().html("<i class='fontello-icon-cancel-circle'>身份证号错误，请重新填写</i>");
				$("#zjno").removeClass("success");
				$("#zjno").addClass("error");
				return;
			}
			if(!checkMobile(proxyPhone,'')){
				$("#aProxyPhone").next().html("<i class='fontello-icon-cancel-circle'>联系人电话已存在，请重新填写</i>");
				$("#mobilediv1").removeClass("success");
				$("#mobilediv1").addClass("error");
				return;
			}
			$(".loading-box").show();
	        $.post("<c:url value='/hProxyMessage/addHProxyMessage'/>",
	        	{
		    		id : id,
		    		proxyName : proxyName,
		    		proxyPhone : proxyPhone,
		    		createYime : createYime,
		    		contractNumber : contractNumber,
		    		contractStartTime : contractStartTime,
		    		contractEndTime : contractEndTime,
		    		remindStartDate : remindStartDate,
		    		remindEndDate : remindEndDate,
		    		bank_number : bank_number,
		    		info : info,
		    		remark1 : remark1,
		    		remark2 : remark2,
		    		remark3 : remark3,
		    		cId : cId,
		    		sex : sex,
		    		userId : userId,
		    		state : state,
		    		userNumber : userNumber,
		    		proxyAddress : proxyAddress,
		    		proxyCode : proxyCode,
		    		payBankNumber : payBankNumber,
		    		payName : payName,
		    		payCardStyle : 1,
		    		payCard : payCard,
		    		payPhoneNumber : payPhoneNumber,
		    		payMail : payMail,
				 _t:Math.random()},
	        	function(data){
					 flagsss = true;
					 $(".loading-box").hide();
		        	var result = eval('('+data+')'); 
		            if (result.code == '1') {
		              	var pageNo = $("#currPage").val();           
		              	searchData(pageNo);
		              	tipOk("保存成功!");
		             } else {
		            	 tipError(result.message);
		             }
	              	$modal.modal('hide');
	        });
	    }
	}
	function selOneindex3(){
		$("#xxxindex3").click();
		searchData3(1);
	}
	function cleanOnIndex(){
		$("#aCName").val("");
		$("#aCId").val("");
	}
	function searchData3(pageNo){
		var returnNum = $("#returnNum3").val();
		var name = $("#fOneAgentNameIndex").val();
			var sortColumn = $("#sortColumn3").val();
		    $.getJSON("<c:url value='/hCompany/getHCompanyList2'/>",
	        {
	        	sortColumn:sortColumn,
	        	name : name,
	    		status : 1,
	    		verify_status : 1,
	    		pageNo: pageNo,
	    		rowCount: returnNum, 
				_t: Math.random()
	        },function(data){
	        var result = data;
	        if (result.code == 1) {
	            setTableStr3(result, pageNo, returnNum);
	        } else {
	        	tipError("系统异常!");
	        } 
	    });
	}
	function genTableHeader3(){
		var str = "<thead><tr>" ;
	    	str+= "<th scope=\"col\" class=\"check-col\"></th>";
	    	str+= "<th onselectstart='return false' scope=\"col\">序号</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_name\" column='name' onselectstart='return false' scope=\"col\">客户名称</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_style\" column='style' onselectstart='return false' scope=\"col\">联系人手机</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_status\" column='status' onselectstart='return false' scope=\"col\">状态</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_status\" column='status' onselectstart='return false' scope=\"col\">审核状态</th>";
			str+="</tr></thead>";
		return str;
	}
	function setTableStr3(result, pageNo, returnNum){
	 	var tableStr = "<table id='dataTable' class=\"table table-striped table-content table-condensed boo-table table-hover bg-blue-light\">";
	    tableStr += genTableHeader3();
	    var number = (pageNo - 1) * returnNum;
	    tableStr += "<tfoot></tfoot>";
	    tableStr += "<tbody>";
// 	    var oneAgentId = '';
	    var cId = $("#aCId").val();
	    for (var k=0; k<result.items.length; k++){      
	        tableStr += "<tr id='DataRow"+k+"'>";
	        if(cId!=""){
	        	if(cId==result.items[k].id){
	        		tableStr += "<td ><input type=\"radio\" checked id='"+result.items[k].id+"' class=\"checkbox check-row\" value=\"0\" name=\"checkName\" onclick='selonagentindex3(\""+result.items[k].id+"\",\""+result.items[k].name+"\")'/></td>";
	        	}else{
	        		tableStr += "<td ><input type=\"radio\" id='"+result.items[k].id+"' class=\"checkbox check-row\" value=\"0\" name=\"checkName\" onclick='selonagentindex3(\""+result.items[k].id+"\",\""+result.items[k].name+"\")'/></td>";
	        	}
	        }else{
	        	tableStr += "<td ><input type=\"radio\" id='"+result.items[k].id+"' class=\"checkbox check-row\" value=\"0\" name=\"checkName\" onclick='selonagentindex3(\""+result.items[k].id+"\",\""+result.items[k].name+"\")'/></td>";
	        }
	        tableStr += "<td >" + (number + k + 1) + "</td>";
		        var status = result.items[k].status;
		    	if(status==1){status = '正常'}else if(status==0){status = '终止'}else if(status==2){status = '暂停'}
		    	var verify_status = result.items[k].verify_status;
		    	if(verify_status ==0){verify_status='待审核'}else if (verify_status==1){verify_status='审核通过'}else if (verify_status==2){verify_status='审核不通过'}
		        tableStr += "<td>" + result.items[k].name + "</td>";
		        tableStr += "<td>" + result.items[k].contact_phone + "</td>";
		        tableStr += "<td>" + status + "</td>";
		        tableStr += "<td>" + verify_status + "</td>";
	        tableStr += "</tr>";            
	    }
	    tableStr += "</tbody></table>";
	    $("#exampleDTC3").html(tableStr);
	    $("#currPage3").val(pageNo);	
// 	    $("#demo4").find("radio").uniform();//初始化复选框
	    genPageTag3(pageNo,result.totalResults,returnNum,'kkpager3');
	}
	function selonagentindex3(id,name,openId){
		$("#aCName").val(name);
		$("#aCId").val(id);
		$("#closeOneIndex3").click();
	}
	
	function selOneindex2(){
		$("#xxxindex2").click();
		searchData2(1);
	}
	function cleanOnIndex2(){
		$("#aBankName").val("");
		$("#aBank_number").val("");
		$("#aUserNumber").val("");
    	$("#aContractNumber").val("");
    	$("#aPayBankNumber").val("");
    	
	}
	function searchData2(pageNo){
		var returnNum = $("#returnNum2").val();
		var name = $("#fOneAgentNameIndex2").val();
			var sortColumn = $("#sortColumn2").val();
		    $.getJSON("<c:url value='/hBankInfo/getHBankInfoList'/>",
	        {
	        	sortColumn:sortColumn,
	        	name : name,
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
	    	str+= "<th scope=\"col\" class=\"check-col\"></th>";
	    	str+= "<th onselectstart='return false' scope=\"col\">序号</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_name\" column='name' onselectstart='return false' scope=\"col\">开户行行号</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_style\" column='style' onselectstart='return false' scope=\"col\">开户行名称</th>";
			str+="</tr></thead>";
		return str;
	}
	function setTableStr2(result, pageNo, returnNum){
	 	var tableStr = "<table id='dataTable' class=\"table table-striped table-content table-condensed boo-table table-hover bg-blue-light\">";
	    tableStr += genTableHeader2();
	    var number = (pageNo - 1) * returnNum;
	    tableStr += "<tfoot></tfoot>";
	    tableStr += "<tbody>";
	    var bank_number = $("#aBank_number").val();
	    for (var k=0; k<result.items.length; k++){      
	        tableStr += "<tr id='DataRow"+k+"'>";
	        if(bank_number!=""){
	        	if(bank_number==result.items[k].bankNum){
	        		tableStr += "<td ><input type=\"radio\" checked id='"+result.items[k].bankNum+"' class=\"checkbox check-row\" value=\"0\" name=\"checkName\" onclick='selonagentindex2(\""+result.items[k].bankNum+"\",\""+result.items[k].name+"\")'/></td>";
	        	}else{
	        		tableStr += "<td ><input type=\"radio\" id='"+result.items[k].bankNum+"' class=\"checkbox check-row\" value=\"0\" name=\"checkName\" onclick='selonagentindex2(\""+result.items[k].bankNum+"\",\""+result.items[k].name+"\")'/></td>";
	        	}
	        }else{
	        	tableStr += "<td ><input type=\"radio\" id='"+result.items[k].bankNum+"' class=\"checkbox check-row\" value=\"0\" name=\"checkName\" onclick='selonagentindex2(\""+result.items[k].bankNum+"\",\""+result.items[k].name+"\")'/></td>";
	        }
	        tableStr += "<td >" + (number + k + 1) + "</td>";
		        tableStr += "<td>" + result.items[k].bankNum + "</td>";
		        tableStr += "<td>" + result.items[k].name + "</td>";
	        tableStr += "</tr>";            
	    }
	    tableStr += "</tbody></table>";
	    $("#exampleDTC2").html(tableStr);
	    $("#currPage2").val(pageNo);	
// 	    $("#demo4").find("radio").uniform();//初始化复选框
	    genPageTag2(pageNo,result.totalResults,returnNum,'kkpager2');
	}
	function selonagentindex2(id,name){
		$("#aBankName").val(name+"("+id+")");
		$("#aBank_number").val(id);
		$("#aUserNumber").val("");
    	$("#aContractNumber").val("");
		$("#aPayBankNumber").val("");
		$("#closeOneIndex2").click();
	}
	
	function getNumber(){
		var bank_number = $("#aBank_number").val();
		if(bank_number==""){
			tipError("请选择付款行行号");
			return false;
		}
		
		var payBankNumber = $("#aPayBankNumber").val();
		if(payBankNumber==""){
			tipError("请选择付款人账号");
			return false;
		}
		//获取用户编号和合同号
		$.get("${ctx}/hProxySerial/getNumber?bank_number="+bank_number+"&payBankNumber="+payBankNumber+"&_t="+Math.random(),function(data){
			var result = eval("("+data+")");
			console.log(data);
	        if (result.code == "1") {
	        	var obj = result.items;
	        	$("#aUserNumber").val(obj.userNumber);
	        	$("#aContractNumber").val(obj.contractNumber);
	        }else{
	        	tipError("获取失败");
	        }
		});
		
	}
	
	function OnInput (event) {
		if($.trim(event.target.value)!=""){
// 			event.target.value;
			$("#aUserNumber").val("");
        	$("#aContractNumber").val("");
		}else{
			$("#aUserNumber").val("");
        	$("#aContractNumber").val("");
      	}
	}
	function OnPropChanged (event) {
	    if (event.propertyName.toLowerCase () == "value") {
	      	if($.trim(event.srcElement.value)!=""){
// 	      		event.srcElement.value
	      		$("#aUserNumber").val("");
	        	$("#aContractNumber").val("");
	      	}else{
	      		$("#aUserNumber").val("");
	        	$("#aContractNumber").val("");
	      	}
	    }
	}
	
	function IdentityCodeValid(code) { 
        var city={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江 ",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北 ",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏 ",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外 "};
        var tip = "";
        var pass= true;
        
        if (!code || !/^[1-9][0-9]{5}(19[0-9]{2}|200[0-9]|2010)(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])[0-9]{3}[0-9xX]$/i.test(code)) {
            tip = "身份证号格式错误";
            pass = false;
        }else if(!city[code.substr(0,2)]){
            tip = "地址编码错误";
            pass = false;
        }else{
            //18位身份证需要验证最后一位校验位
            if(code.length == 18){
                code = code.split('');
                //∑(ai×Wi)(mod 11)
                //加权因子
                var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
                //校验位
                var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];
                var sum = 0;
                var ai = 0;
                var wi = 0;
                for (var i = 0; i < 17; i++)
                {
                    ai = code[i];
                    wi = factor[i];
                    sum += ai * wi;
                }
                var last = parity[sum % 11];
                if(parity[sum % 11] != code[17]){
                    tip = "校验位错误";
                    pass =false;
                }
            }
        }
        return pass;
    }
	function checkMobile(mobile,id){
		$.ajaxSetup({ 
		    async : false 
		}); 
		var flag = false;
		$.post("${ctx}/hProxyMessage/checkProxyPhone",{mobile:mobile,id:id},function(data){
			 var result = eval('('+data+')');
	            if (result.code == '1') {
	            	flag= true;
	            }
		});
		return flag;
	}
</script>