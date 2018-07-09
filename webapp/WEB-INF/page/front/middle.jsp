<%@ page import="com.base.utils.RequestHandler" %>
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
<link rel="stylesheet" type="text/css" href="${ctx}/public/custom/public/css/customForm.css">
<link rel="stylesheet" type="text/css" href="${ctx}/public/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/public/css/hxtPay.css">
<script src="${ctx}/public/js/jquery-1.8.2.min.js"></script>
<script src="${ctx}/public/xcConfirm/js/xcConfirm.js"></script>
<script src="${ctx}/js/area_public.js"></script>
	<style type="text/css">
	.xcConfirm .xc_layer {
	    position: fixed;
	    top: 0;
	    left: 0;
	    width: 100%;
	    height: 100%;
	    background-color: #666666;
	    opacity: 0.5;
	    filter: alpha(opacity=50);
	    z-index: -1;
	}
	.loading .popBox{
			width:122px! important;
			height:122px! important;
			margin-left:45%;
			margin-top:15%;
			z-index:2147000001! important;
			border-radius: 5px! important;
		}
	</style>
</head>

<body>
<div class="box main">
<input type="hidden" id="projectPath" value="${ctx}"/>
</div>
<script type="text/javascript">
$HXT.loading('努力加载中');
var url = '${ctx}/pay/generatePayOrder?cId=${sessionScope.cId}&payType1=${sessionScope.payType1}&payType2=${sessionScope.payType2}&payType=${sessionScope.payType}&subId=${sessionScope.subId}&reviewRadio=${sessionScope.reviewRadio}';
location.href = url;
</script>
</body>
</html>