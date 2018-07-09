<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
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
    <link rel="stylesheet" type="text/css" href="${ctx }/public/custom/public/css/customForm.css">
	<link rel="stylesheet" type="text/css" href="${ctx }/public/css/style.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/public/css/nav.css">
	<style type="text/css">
	.cContentBox {
	    padding-left: 145px! important;
	    padding-top: 10px;
	}
	
	.codeTitle {
	    width: 85px;
	    font-size: 14px;
	    color: #000000;
	    vertical-align: middle;
	    margin-right: 80px! important;
	}
	.pBtnBox {
	    padding-top: 10px;
	    width: 300px! important;
	    margin-left: 180px;
	}
	</style>
</head>
<body>
<%@ include file="/WEB-INF/page/public/common/top.jsp" %>

<!---->
<div class="box main">
	<%@ include file="/WEB-INF/page/public/common/left.jsp" %>
    <div class="rightBox">
    	<div class="commBody minHeight">
        	<div class="pTitle">财务复核人员管理</div>
            <p class="yMsg"></p>
            <div class="cContentBox qyCentBox">
                
                
                <div class="lineP pItems">
                	<font class="lines starFont">*</font>
                	<label class="lines codeTitle">复核人员姓名：</label>
                    <div class="lines pItem">
                    	<input type="text" placeholder="复核人员姓名" class="pInput" id="userName" value="${vo.userName }">
                    </div>
                </div>
                <div class="lineP pItems">
                	<font class="lines starFont">*</font>
                	<label class="lines codeTitle">复核人员性别：</label>
                    <dl class="lines pItem selPIten">
                        <dd class="lines" style="width:100%;">
                            <div class='diy_select select1'>
                                <input type='hidden' id="sex" name='' class='diy_select_input' value="<c:if test="${vo.sex == 1 }">男</c:if><c:if test="${vo.sex == 0 }">女</c:if>"/>
                                <div class='diy_select_txt'>
                                		<c:if test="${empty vo.sex }">请选择复核人员性别</c:if>
	                                	<c:if test="${not empty vo.sex }">
	                                	<c:if test="${vo.sex == 1 }">男</c:if>
	                                	<c:if test="${vo.sex == 0 }">女</c:if>
	                                	</c:if>
                                </div>
                                <div class='diy_select_btn'></div>
                                <ul class='diy_select_list'>
                                    <li>男</li>
                                    <li>女</li>
                                </ul>
                            </div>
                        </dd>
                    </dl>
                </div>
                <div class="lineP pItems">
                	<font class="lines starFont">*</font>
                	<label class="lines codeTitle">复核人员手机号：</label>
                    <div class="lines pItem">
                    	<input type="text" placeholder="复核人员手机号" class="pInput" id="mobil" value="${vo.mobil }">
                    </div>
                </div>
                
                <div class="lineP pItems">
                	<label class="lines codeTitle">复核人员称呼：</label>
                	<font class="lines starFont un"></font>
                    <div class="lines pItem">
                    	<input type="text" placeholder="复核人员称呼（如帐会计、李会计）" class="pInput" id="userLabel" value="${vo.userLabel }">
                    </div>
                     <span class="lines pIInfo">接收短信时对复核人员的称呼</span>
                </div>
                <div class="lineP pItems">
                	<font class="lines starFont">*</font>
                	<label class="lines codeTitle">是否发送复核短信提醒：</label>
                    <dl class="lines pItem selPIten">
                        <dd class="lines" style="width:100%;">
                            <div class='diy_select select1'>
                                <input type='hidden' id="msgSwitch"  name='' class='diy_select_input' value="<c:if test="${vo.msgSwitch == 1 }">是</c:if><c:if test="${vo.msgSwitch == 0 }">否</c:if>"/>
                                <div class='diy_select_txt'>
                                	<c:if test="${empty vo.msgSwitch }">请选择是否接受复核短信通知</c:if>
                                	<c:if test="${not empty vo.msgSwitch }">
                                	<c:if test="${vo.msgSwitch == 1 }">是</c:if>
                                	<c:if test="${vo.msgSwitch == 0 }">否</c:if>
                                	</c:if>
                                </div>
                                <div class='diy_select_btn'></div>
                                <ul class='diy_select_list'>
                                    <li>是</li>
                                    <li>否</li>
                                  
                                </ul>
                            </div>
                        </dd>
                    </dl>
                </div>
                
                
                <div class="pItems xyItems">
                    <div class="pBtnBox">
                    	<a href="javascript:void(0)" onclick="sub()" class="iBtn">提交</a>
                    </div>
                </div>
                
            </div>
        	
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/page/public/common/footer.jsp" %>
<input id="mId" value="${vo.id }" type="text">
<script src="${ctx}/public/js/jquery-1.8.2.min.js"></script>
<script src="${ctx}/public/custom/public/js/select.js"></script>
<script src="${ctx}/public/xcConfirm/js/xcConfirm.js"></script>
<script src="${ctx}/js/area_public.js"></script>
<script>
	function sub(){
		var userName = $.trim($("#userName").val());
		var sex = $.trim($("#sex").val());
		var mobil = $.trim($("#mobil").val());
		var userLabel = $.trim($("#userLabel").val());
		var msgSwitch = $.trim($("#msgSwitch").val());
		if(userName == ''){
			$HXT.showInfo("请输入复核人员姓名");
			return;
		}
		if(sex == ''){
			$HXT.showInfo("请选择复核人员性别");
			return;
		}
		if(mobil == ''){
			$HXT.showInfo("请输入复核人员手机号");
			return;
		}
		if(msgSwitch == ''){
			$HXT.showInfo("请选择是否接受复核短信通知 ");
			return;
		}
		var mid = getVal("mId");
		if(mid == ''){
			var isDefault = 1;
			var state = 1;
			var userLabel = getVal("userLabel");
			var userName = getVal("userName");
			var sexStr = getVal("sex");
			var sex = '';
			if(sexStr == '男'){
				sex = 1;
			}else{
				sex = 0;
			}
			var mobil = getVal("mobil");
			var msgSwitchStr = getVal("msgSwitch");
			var msgSwitch = '';
			if(msgSwitchStr == '是'){
				msgSwitch = '1';
			}else{
				msgSwitch = '0';
			}
			var flag = true;
		    if (flag){ 
		        $.post("<c:url value='/hReviewUser/addHReviewUser'/>",
		        	{
			    		isDefault : isDefault,
			    		state : state,
			    		userLabel : userLabel,
			    		userName : userName,
			    		companyId : '${companyId}',
			    		sex : sex,
			    		mobil : mobil,
			    		msgSwitch : msgSwitch,
					 _t:Math.random()},
		        	function(data){
			        	var result = eval('('+data+')'); 
			            if (result.code == '1') {
			            	$HXT.showInfo("提交成功");
			             } else {
			            	$HXT.showInfo(result.message);
			             }
		        });
		    }
		}else{
			var id = getVal("mId");
			var userLabel = getVal("userLabel");
			var userName = getVal("userName");
			var sexStr = getVal("sex");
			var sex = '';
			if(sexStr == '男'){
				sex = 1;
			}else{
				sex = 0;
			}
			var mobil = getVal("mobil");
			var msgSwitchStr = getVal("msgSwitch");
			var msgSwitch = '';
			if(msgSwitchStr == '是'){
				msgSwitch = '1';
			}else{
				msgSwitch = '0';
			}
			var flag = true;
		    if (flag){ 
		        $.post("<c:url value='/hReviewUser/updateHReviewUser'/>",
		        	{
			    		id : id,
			    		userLabel : userLabel,
			    		userName : userName,
			    		sex : sex,
			    		mobil : mobil,
			    		msgSwitch : msgSwitch,
					 _t:Math.random()},
		        	function(data){
			        	var result = eval('('+data+')'); 
			            if (result.code == '1') {
			            	$HXT.showInfo("提交成功");
			             } else {
			            	 $HXT.showInfo(result.message);
			             }
		        });
		    }
		}
	}
	function getVal(id){
		return $("#"+id).val();
	}
</script>
</body>
</html>
