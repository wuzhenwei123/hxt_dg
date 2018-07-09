<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<link href="<c:url value='/plus/webuploader/webuploader.css'/>" rel="stylesheet">
<script type="text/javascript" src="<c:url value='/plus/webuploader/webuploader.js'/>"></script>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>添加客户经理</h4>
</div>
<div class="modal-body">
    <form id="addForm" class="well well-nice form-horizontal">
    	<div class="control-group">
             <label class="control-label" for="aStyle">机构类型</label>
             <div class="controls">
			    <select id="aStyle" isNotNull="true" warnName="style" onchange="changerole()">
			    	<option value="1">公司</option>
			    	<option value="2">个人</option>
			    </select>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group" id="aUser_name1">
             <label class="control-label" for="aUser_name">账号</label>
             <div class="controls">
			    <input type="text" placeholder="账号" id="aUser_name" isNotNull="true" warnName="账号" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aName">名称</label>
             <div class="controls">
			    <input type="text" placeholder="名称" id="aName" isNotNull="true" warnName="名称" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group" id="licenceUrlDiv">
             <label class="control-label" for="aLicence_url">执照照片</label>
             <div class="controls">
			    <input type="text" placeholder="执照照片" id="aLicence_url" warnName="执照照片" readonly="readonly"/>
			     <!-- <div id="fileList" class="uploader-list"></div> -->
    			 <div id="filePicker1">选择图片</div>
             </div>
         </div>
         <div class="control-group" id="licenceCodeDiv">
             <label class="control-label" for="aLicence_code">执照编号</label>
             <div class="controls">
			    <input type="text" placeholder="执照编号" id="aLicence_code" isNotNull="true" warnName="执照编号" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group" id="taxcodeDiv">
             <label class="control-label" for="aTax_code">税务登记证号</label>
             <div class="controls">
			    <input type="text" placeholder="税务登记证号" id="aTax_code" isNotNull="true" warnName="税务登记证号" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group" id="taxDiv">
             <label class="control-label" for="aTax_url">税务登记证照片</label>
             <div class="controls">
			    <input type="text" placeholder="税务登记证照片" id="aTax_url" warnName="税务登记证照片" />
                  <div id="filePicker2">选择图片</div>
             </div>
         </div>
         <div class="control-group" id="legalDiv">
             <label class="control-label" for="aLegal_person">法人姓名</label>
             <div class="controls">
			    <input type="text" placeholder="法人姓名" id="aLegal_person" isNotNull="true" warnName="法人姓名" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
        <div class="control-group">
             <label class="control-label" for="aCard_style">证件类型</label>
             <div class="controls">
<!-- 			    <input type="text" placeholder="card_style" id="aCard_style" isNotNull="true" warnName="card_style" /> -->
			    <select id="aCard_style" isNotNull="true" warnName="card_style">
			    	<option value="1">身份证</option>
<!-- 			    	<option value="2">护照</option> -->
			    </select>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group" id="zjno">
             <label class="control-label" for="aCard_no">证件号码</label>
             <div class="controls">
			    <input type="text" placeholder="证件号码" id="aCard_no" isNotNull="true" warnName="证件号码" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group" style="display:none;" id="aSexDiv">
             <label class="control-label" for="aSex">性别</label>
             <div class="controls">
			    <select id="aSex" isNotNull="true" warnName="性别">
			    	<option value="1">男</option>
			    	<option value="0">女</option>
			    </select>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group" id="contactDiv">
             <label class="control-label" for="aContact_person">联系人姓名</label>
             <div class="controls">
			    <input type="text" placeholder="联系人姓名" id="aContact_person" isNotNull="true" warnName="联系人姓名" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group" id="mobilediv1">
             <label class="control-label" for="aMobile1">联系手机</label>
             <div class="controls">
			    <input type="text" placeholder="联系手机" id="aMobile1" isNotNull="true" warnName="联系手机" datatype="phone" onkeyup="this.value=this.value.replace(/\D/gi,'')"/>
                 <span class="help-inline text-red">*</span>
<!--                  <input type="button" value="发送手机验证码" onclick="si(this)"> -->
             </div>
         </div>
<!--          <div class="control-group"> -->
<!--              <label class="control-label" for="aMobile2">mobile2</label> -->
<!--              <div class="controls"> -->
<!-- 			    <input type="text" placeholder="mobile2" id="aMobile2" isNotNull="true" warnName="mobile2" /> -->
<!--                  <span class="help-inline text-red">*</span> -->
<!--              </div> -->
<!--          </div> -->
          <div class="control-group">
             <label class="control-label" for="aStatus">状态</label>
             <div class="controls">
			    <select id="aStatus" isNotNull="true" warnName="状态">
			    	<option value="1">正常</option>
			    	<option value="0">终止</option>
			    	<option value="2">暂停</option>
			    </select>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
<!--          <div class="control-group"> -->
<!--              <label class="control-label" for="aCreate_openId">create_openId</label> -->
<!--              <div class="controls"> -->
<!-- 			    <input type="text" placeholder="create_openId" id="aCreate_openId" isNotNull="true" warnName="create_openId" /> -->
<!--                  <span class="help-inline text-red">*</span> -->
<!--              </div> -->
<!--          </div> -->
         <div class="control-group" style="display: none;">
             <label class="control-label" for="aCheck_status">审核状态</label>
             <div class="controls">
			    <select id="aCheck_status" isNotNull="true" warnName="审核状态">
					<option value="0" selected="selected">待审核</option>
					<option value="1">已审核</option>
			    </select>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aBank_name">开户行</label>
             <div class="controls">
			    <input type="text" placeholder="开户行" id="aBank_name" isNotNull="true" warnName="开户行" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aBank_number">开户行号</label>
             <div class="controls">
			    <input type="text" placeholder="开户行号" id="aBank_number" isNotNull="true" warnName="开户行号" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aBank_account">银行账户</label>
             <div class="controls">
			    <input type="text" placeholder="银行账户" id="aBank_account" isNotNull="true" warnName="银行账户" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
<!--          <div class="control-group"> -->
<!--              <label class="control-label" for="aContract_number">合同编号</label> -->
<!--              <div class="controls"> -->
<!-- 			    <input type="text" placeholder="合同编号" id="aContract_number" isNotNull="true" warnName="合同编号" /> -->
<!--                  <span class="help-inline text-red">*</span> -->
<!--              </div> -->
<!--          </div> -->
<!--          <div class="control-group"> -->
<!--              <label class="control-label" for="aContract_start_time">合同开始时间</label> -->
<!--              <div class="controls"> -->
<!-- 		     	<input type="text" placeholder="合同开始时间" id="aContract_start_time" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="合同开始时间" /> -->
<!--                  <span class="help-inline text-red">*</span> -->
<!--              </div> -->
<!--          </div> -->
<!--          <div class="control-group"> -->
<!--              <label class="control-label" for="aContract_end_time">合同截止时间</label> -->
<!--              <div class="controls"> -->
<!-- 		     	<input type="text" placeholder="合同截止时间" id="aContract_end_time" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="合同截止时间" /> -->
<!--                  <span class="help-inline text-red">*</span> -->
<!--              </div> -->
<!--          </div> -->
         <div class="control-group">
             <label class="control-label" for="aRemark1">备注</label>
             <div class="controls">
			    <input type="text" placeholder="备注" id="aRemark1" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
<!--          <div class="control-group"> -->
<!--              <label class="control-label" for="aRemark2">remark2</label> -->
<!--              <div class="controls"> -->
<!-- 			    <input type="text" placeholder="remark2" id="aRemark2" isNotNull="true" warnName="remark2" /> -->
<!--                  <span class="help-inline text-red">*</span> -->
<!--              </div> -->
<!--          </div> -->
<!--          <div class="control-group"> -->
<!--              <label class="control-label" for="aRemark3">remark3</label> -->
<!--              <div class="controls"> -->
<!-- 			    <input type="text" placeholder="remark3" id="aRemark3" isNotNull="true" warnName="remark3" /> -->
<!--                  <span class="help-inline text-red">*</span> -->
<!--              </div> -->
<!--          </div> -->
     </form>
 </div>
 <div class="modal-footer">
     <button type="button" data-dismiss="modal" class="btn btn-boo">取消</button>
     <button type="submit" class="btn btn-green" onclick="add()">保存</button>
 </div>
<script type="text/javascript">
	$(function(){
		var uploader1;
		var uploader2;
		setTimeout(function() {
			console.log('初始化...')
			// 初始化Web Uploader
			uploader1 = WebUploader.create({
			
			    // 选完文件后，是否自动上传。
			    auto: true,
			
			    // swf文件路径
			    swf: '${ctx}/plus/webuploader/Uploader.swf',
			
			    // 文件接收服务端。
			    server: '${ctx}/hAgent/uploadPic',
			
			    // 选择文件的按钮。可选。
			    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
			    //pick: '#filePicker',
			   	pick: {
			    	id:'#filePicker1',
			    	multiple : false//是否开起同时选择多个文件能力
			    },
				
				// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
			    resize: false,
				
			    // 只允许选择图片文件。
			    accept: {
			        title: 'Images',
			        extensions: 'gif,jpg,jpeg,bmp,png',
			        mimeTypes: 'image/*'
			    },
			    
			    formData:{ // 参数
			    }
			});
		
			/* uploader.on('uploadError', function(file, reason){
	          alert(reason);
	        }); */
		
			// 文件上传成功，给item添加成功class, 用样式标记上传成功。
			uploader1.on( 'uploadSuccess', function( file,response ) {
				uploader1.reset();
				
				var servername = response.serverfileName;
				
				$("#aLicence_url").val(servername);
			});
			
			// 文件上传失败，显示上传出错。
			uploader1.on( 'uploadError', function( file ) {
			    alert("上传失败!");
			});
			/////////////////////////////////////////////////////////////
			// 初始化Web Uploader
			uploader2 = WebUploader.create({
			
			    // 选完文件后，是否自动上传。
			    auto: true,
			
			    // swf文件路径
			    swf: '${ctx}/plus/webuploader/Uploader.swf',
			
			    // 文件接收服务端。
			    server: '${ctx}/hAgent/uploadPic',
			
			    // 选择文件的按钮。可选。
			    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
			    //pick: '#filePicker',
			   	pick: {
			    	id:'#filePicker2',
			    	multiple : false//是否开起同时选择多个文件能力
			    },
				
				// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
			    resize: false,
				
			    // 只允许选择图片文件。
			    accept: {
			        title: 'Images',
			        extensions: 'gif,jpg,jpeg,bmp,png',
			        mimeTypes: 'image/*'
			    },
			    
			    formData:{ // 参数
			    }
			});
		
			/* uploader2.on('uploadError', function(file, reason){
	          alert(reason);
	        }); */
		
			// 文件上传成功，给item添加成功class, 用样式标记上传成功。
			uploader2.on( 'uploadSuccess', function( file,response ) {
				uploader2.reset();
				
				var servername = response.serverfileName;
				
				$("#aTax_url").val(servername);
			});
			
			// 文件上传失败，显示上传出错。
			uploader2.on( 'uploadError', function( file ) {
			    alert("上传失败!");
			});
		}, 200);
	});
	function changerole(){
		var type = $("#aStyle").val();
		if(type==1){ //公司
			$("#aSexDiv").hide();
	//			$("#messageNameDiv").hide();
			$("#aName").attr('warnName','公司名称');
			$("#aSex").attr("isNotNull","false");
			$("#licenceUrlDiv").show();
			$("#licenceCodeDiv").show();
			$("#taxDiv").show();
			$("#taxcodeDiv").show();
			$("#legalDiv").show();
			$("#contactDiv").show();
			$("#nameLabel").html('公司名称');
	//			$("#aMessageName").attr("isNotNull","false"); 
			$("#aLicence_code").attr("isNotNull","true"); 
			$("#aLegal_person").attr("isNotNull","true"); 
			$("#aContact_person").attr("isNotNull","true");
			$("#aTax_code").attr("isNotNull","true");
		}else{ //个人
			$("#aSexDiv").show();
	//			$("#messageNameDiv").show();
			$("#nameLabel").html('姓名');
			$("#aName").attr('warnName','姓名');
			$("#licenceUrlDiv").hide();
			$("#licenceCodeDiv").hide();
			$("#legalDiv").hide();
			$("#taxDiv").hide();
			$("#taxcodeDiv").hide();
			$("#contactDiv").hide();
	//			$("#aMessageName").attr("isNotNull","true");
			$("#aLicence_code").attr("isNotNull","false"); 
			$("#aLegal_person").attr("isNotNull","false"); 
			$("#aContact_person").attr("isNotNull","false");
			$("#aTax_code").attr("isNotNull","false");
		}
	}
	//执行添加
	function add(){
		var id = getVal("aId");
		var openId = getVal("aOpenId");
		var name = getVal("aName");
		var licence_url = getVal("aLicence_url");
		var licence_code = getVal("aLicence_code");
		var tax_url = getVal("aTax_url");
		var legal_person = getVal("aLegal_person");
		var card_style = getVal("aCard_style");
		var card_no = getVal("aCard_no");
		var sex = getVal("aSex");
		var mobile1 = getVal("aMobile1");
		var mobile2 = getVal("aMobile2");
		var style = getVal("aStyle");
		var status = getVal("aStatus");
		var create_time = getVal("aCreate_time");
		var create_openId = getVal("aCreate_openId");
		var check_status = getVal("aCheck_status");
		var contact_person = getVal("aContact_person");
		var tax_code = getVal("aTax_code");
		var bank_name = getVal("aBank_name");
		var bank_number = getVal("aBank_number");
		var bank_account = getVal("aBank_account");
		var contract_number = getVal("aContract_number");
		var contract_start_time = getVal("aContract_start_time");
		var contract_end_time = getVal("aContract_end_time");
		var user_name = getVal("aUser_name");
		var remark1 = getVal("aRemark1");
		var remark2 = getVal("aRemark2");
		var remark3 = getVal("aRemark3");
		var flag = validateForm('addForm');
	    if (flag){ 
	    	if(!IdentityCodeValid(card_no)){
				$("#aCard_no").next().html("<i class='fontello-icon-cancel-circle'>身份证号错误，请重新填写</i>");
				$("#zjno").removeClass("success");
				$("#zjno").addClass("error");
				return;
			}
			if(!checkUserName(user_name,'')){
				$("#aUser_name").next().html("<i class='fontello-icon-cancel-circle'> 账号已存在，请重新填写</i>");
				$("#aUser_name1").removeClass("success");
				$("#aUser_name1").addClass("error");
				return;
			}
// 			if(!checkMobile(mobile1,'')){
// 				$("#aMobile1").next().html("<i class='fontello-icon-cancel-circle'> 联系手机1已存在，请重新填写</i>");
// 				$("#mobilediv1").removeClass("success");
// 				$("#mobilediv1").addClass("error");
// 				return;
// 			}

			//校验手机号
			$.post("<c:url value='/hAgent/checkMobile'/>",
	        	{
		    		mobile : mobile1,
				 _t:Math.random()},
	        	function(data){
		        	var result = eval('('+data+')'); 
		            if (result.code == '1') {
		            	$.post("<c:url value='/hAgent/checkUserName'/>",
		          	        	{
		          	        		user_name:user_name,
		          				 _t:Math.random()},
		          	        	function(data){
		          		        	var result = eval('('+data+')'); 
		          		            if (result.code == '1') {
		          		            	$.post("<c:url value='/hAgent/addHAgent'/>",
		        		        	        	{
		        		        	        		user_name:user_name,
		        		        		    		id : id,
		        		        		    		openId : openId,
		        		        		    		name : name,
		        		        		    		licence_url : licence_url,
		        		        		    		licence_code : licence_code,
		        		        		    		tax_url : tax_url,
		        		        		    		legal_person : legal_person,
		        		        		    		card_style : card_style,
		        		        		    		card_no : card_no,
		        		        		    		sex : sex,
		        		        		    		mobile1 : mobile1,
		        		        		    		mobile2 : mobile2,
		        		        		    		style : style,
		        		        		    		status : status,
		        		        		    		create_time : create_time,
		        		        		    		create_openId : create_openId,
		        		        		    		check_status : check_status,
		        		        		    		contact_person : contact_person,
		        		        		    		tax_code : tax_code,
		        		        		    		bank_name : bank_name,
		        		        		    		bank_number : bank_number,
		        		        		    		bank_account : bank_account,
		        		        		    		contract_number : contract_number,
		        		        		    		contract_start_time : contract_start_time,
		        		        		    		contract_end_time : contract_end_time,
		        		        		    		remark1 : remark1,
		        		        		    		remark2 : remark2,
		        		        		    		remark3 : remark3,
		        		        				 _t:Math.random()},
		        		        	        	function(data){
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
		          		             } else {
		          		            	 tipError("账号已经存在，请修改");
		          		             }
		          	        });
		             } else {
		            	 tipError("联系手机被占用，请更改");
		             }
	        });
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
		$.post("${ctx}/hAgent/checkMobile",{mobile:mobile,id:id},function(data){
			 var result = eval('('+data+')');
	            if (result.code == '1') {
	            	flag= true;
	            }
		});
		return flag;
	}
	// 校验账号
	function checkUserName(user_name,id){
		$.ajaxSetup({ 
		    async : false 
		}); 
		var flag = false;
		$.post("${ctx}/hAgent/checkUserName",{user_name:user_name,id:id},function(data){
			 var result = eval('('+data+')');
	            if (result.code == '1') {
	            	flag= true;
	            }
		});
		return flag;
	}
</script>