//校验支付url
function checkPayUrl(){
    var checkNo = $("#checkNo").val();
    if(typeof(checkNo) == "undefined" || checkNo==""){
        alert("请输入验证码！");
        return;
    }
    var url = $("#projectPath").val() + '/pay/checkPayurl?param=' + $("#param").val()+'&checkNo='+checkNo;
    $.ajax({
        type: "GET",
        url: url,
        dataType: "json",
        success: function (data) {
            if (data.status == 'success') {
                var payurl = data.data;
                window.location = payurl;
            } else {
                alert(data.msg);
            }
        }
    });

}