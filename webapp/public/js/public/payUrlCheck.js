$(function(){
	var html = '<div class="wItemBox">';
	html += '<div class="lineP searchItem  sItem">';
	html += '<label class="lines codeTitle">验证码：</label>';
	html += '<div class="lines pItem code" style="width:277px;">';
	html += '<input type="text" placeholder="验证码" class="pInput" id="checkNo">';
	html += ' </div>';
	html += '</div>';
	html += '</div>';
	var txt = "安全验证";
	var option = {
		title : "安全验证",
		btn : parseInt("0011", 2),
		isOkBtn : true,
		onOk : function() {
			if(!$("#checkNo").val()){
				$HXT.showInfo('请输入验证码！')
				return false;
			}
			var url = $("#projectPath").val() + '/pay/checkPayurl?param=' + $("#param").val()+'&checkNo='+$("#checkNo").val();
		    $.ajax({
		        type: "GET",
		        url: url,
		        dataType: "json",
		        success: function (data) {
		            if (data.status == 'success') {
		                var payurl = data.data;
		               $("#loadingToast").show();
		                window.location = payurl;
		            } else {
		            	$HXT.showInfo(data.msg)
		            }
		        }
		    });
		},
//		class : [ 'okAddressBox' ]
	}

	
	var classArr = ['okAddressBox'];
	window.wxc.xcConfirm(html, "custom", option);
	setXcConfirmClass(classArr);
	var isOkBtn = true;
	if(option.isOkBtn){
		$('.'+classArr[0] + " .popBox .cancel").remove();
	}
	$("."+classArr[0] +" .popBox .sgBtn.ok").html('验证');
	
//	window.wxc.xcConfirm(html, "custom", option);
//	if (option.isOkBtn) {
//		$('.' + option.class + " .popBox .cancel").remove();
//		$(".clsBtn").hide();
//	}
//	$("." + option.class[0] + " .popBox .sgBtn.ok").html('验证');
})
function setXcConfirmClass(className){
	//$(".xcConfirm").addClass(className);
	
	if( className && className.length > 0){
		for(var i = 0; i <className.length ; i ++){
			$(".xcConfirm").addClass(className[i]);
		}
		
	}
}