<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<html>
<head>
<meta charset="utf-8">
<title>${_title}</title>
	<meta name="keywords" content="${_keywords}" />
	<meta name="description" content="${_description}" />
	<meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<meta content="webkit|ie-comp|ie-stand" name="renderer">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${ctx}/plus/jPaginate/css/style.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/public/css/nav.css">
</head>
<body>
<%@ include file="/WEB-INF/page/public/common/top.jsp" %>

<div class="box minBox">
    <div class="pTitle">公告</div>
    <div class="newsListBox">
    	<ul class="iItemsUl">
    		<c:forEach items="${newsList }" var="news">
        	<li>
            	<a href="${ctx }/public/notice/${news.id}">${news.title }</a>
                <span><fmt:formatDate value="${news.createTime}" type="date"/></span>
            </li>
    		</c:forEach>
        </ul>
    </div>
</div>
<div class="lineP pagesBox">
<div id="demo3" style="left: 44%" ></div>
<!-- 	<a class="lines un" href="#">上一页</a> -->
<!--     <a class="lines page un" href="#">1</a> -->
<!--     <a class="lines page" href="#">2</a> -->
<!--     <a class="lines page" href="#">3</a> -->
<!--     <a class="lines page" href="#">4</a> -->
<!--     <a class="lines page" href="#">5</a> -->
<!--     <a class="lines page" href="#">6</a> -->
<!--     <a class="lines on" href="#">下一页</a> -->
</div>
<%@ include file="/WEB-INF/page/public/common/footer.jsp" %>

<script src="${ctx }/public/js/jquery-1.8.2.min.js"></script>
<script src="${ctx }/plus/jPaginate/jquery.paginate.js"></script>

<script>
	$(function(){
		var returnNum = 10 ;
		var totalRecords = parseInt('${newsCount}');
		var yu = totalRecords%returnNum;
		var zs = parseInt(totalRecords/returnNum);
		if(yu > 0){
			zs +=1;
		}
		if(zs == 0)
			zs = 1;
		$("#demo3").paginate({
			count 		: zs,
			start 		: ${p},
			display     : 10,
			border					: true,
			border_color			: '#dddddd',
			text_color  			: '#01a796',
			background_color    	: '#FFFFFF',	
			border_hover_color		: '#68BA64',
			text_hover_color  		: 'black',
			background_hover_color	: '#CAE6C6', 
			rotate      : false,
			images		: false,
			mouse		: 'press',
			onChange     			: function(page){
				location.href = "${ctx}/public/noticelist?p="+page;
			}
		});
	})
</script>
</body>
</html>
