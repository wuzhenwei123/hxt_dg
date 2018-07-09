<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta content="textml;charset=utf-8" http-equiv="content-type">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<meta content="webkit|ie-comp|ie-stand" name="renderer">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${ctx }/public/custom/public/css/customForm.css">
<link rel="stylesheet" type="text/css" href="${ctx }/public/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctx }/plus/jPaginate/css/style.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/public/css/nav.css">
<title>${_title}</title>
	<meta name="keywords" content="${_keywords}" />
	<meta name="description" content="${_description}" />
</head>
<body>
<%@ include file="/WEB-INF/page/public/common/top.jsp" %>

<div class="box minBox">
    <div class="pTitle" style="color: #01a796;font-size: 24px;text-align: center;">恒信通，您贴心的电费管家</div>
    <div class="maps">
    	<div class="fl mapTxt">
        	<div class="lineP sItem">
             	<label class="lines codeTitle">所属区域</label>
                <div id="magAddress" class="lines pItem vi" style="width:306px;">
                	<input type="hidden" class="diy_select_input" name="">
                	<span class="addRessSpan" id="addRessSpan">请选择地区地址，如北京市东城区</span>
                    <div class="addRessBox">
                    	<div class="ressTabBox">
                    		<a href="javascript:void(0);" class="aClose"></a>
                        	<div class="ressTabNav">
                            	<a class="lines resNav on" data-id="shengBox" href="javascript:void(0);" id="sheng"><span></span>省/直辖市/自治区</a>
                                <a class="lines resNav" data-id="shiBox" href="javascript:void(0);" id="shi"><span></span>市行政区</a>
                                <a class="lines resNav" data-id="xianBox" href="javascript:void(0);" id="xian"><span></span>县名称</a>
                            </div>
                            <div id="shengBox" class="addReBox" style="display:block;">
	                            <ul class="addRessPro" id="shengBox1">
	                            </ul>
                            </div>
                            <div  id="shiBox" class="addReBox" style="display:none;">
	                            <ul class="addRessPro" id="shiBox1" >
	                            </ul>
                            </div>
                            
                            <div  id="xianBox" class="addReBox" style="display:none;"> 
	                            <ul class="addRessPro" id="xianBox1">
	                            </ul>
                            </div>
                        </div>
                    </div>
                    
                </div>
             </div>
             <div class="lineP  sItem pItems">
             	<label class="lines codeTitle">&nbsp;</label>
                <div class="lines pItem mapSt" style="width:306px;">
                	<textarea placeholder="可输入关键字查找更方便，如街道、路名等" id="query_name">${param.name }</textarea>
                </div>
                
             </div>
             
             <div class="lineP pItems  sItem">
             	<label class="lines codeTitle">&nbsp;</label>
                 <a href="javascript:void(0);" onclick="query()" class="lines cearchBtn" style="background-color: #01a796;color:#fff">查询</a>
             </div>
        
        </div>
        <div class="fr mapBox">
        	<img src="${ctx }/public/images/maps.jpg" />
        </div>
    </div>
    
   <div class="mapItems">
		<c:if test="${not empty newsList }">
	   		<!-- 列表 -->
	       <c:forEach items="${newsList }" var="dot" varStatus="vs">
	        	<a class="fl mItem" href="#">
		        	<p class="mTitle">${vs.index+1}  ${dot.name }</p>
		            <p class="mAddress">${dot.area_name }</p>
		        </a>
	  		</c:forEach>
		
		</c:if>
		<c:if test="${empty newsList }">
	        <!-- 空  -->
	    	<div class="msgBox">
	        	<p class="msOne">哎呀，找不到该网点信息</p>
				<p class="msTwo">温馨提示：试一试别的关键词</p>
	        </div>
		</c:if>
    </div>
</div>
<div class="lineP pagesBox">
<c:if test="${not empty newsList }">
<div id="demo3" style="left: 44%" ></div>
</c:if>
</div>
<%@ include file="/WEB-INF/page/public/common/footer.jsp" %>

<script src="${ctx }/public/js/jquery-1.8.2.min.js"></script>
<script src="${ctx }/plus/jPaginate/jquery.paginate.js"></script>
<script src="${ctx}/public/js/myPlaceholder.js"></script>
<input id="province_code" value="${param.province_code }" type="hidden">
<input id="city_code" value="${param.city_code }" type="hidden">
<input id="area_code" value="${param.area_code }" type="hidden">
<script>
	//省
	function getPro(){
		$.getJSON("${ctx}/hProvince/getHProvinceJson",{id:1},function(data){
			var result = data;
	        if (result.code == 1) {
	            var provinces = "";
	            if(result.items.length>0){
	            	for(var pr in result.items){
	            		provinces += '<li class="lines" onclick="setVal(\'province_code\','+result.items[pr].provinceCode+',\''+result.items[pr].provinceName+'\')" >'+result.items[pr].provinceName+'</li>';
	            	}
	            }
	            
	            $("#shengBox1").html(provinces);
	            $("#shengBox1").show();
	        }
		});
	}
	//市
	function getCity(){
		var provinceCode = $("#province_code").val();
		$.getJSON("${ctx}/hCity/getHCityJson",{provinceCode:provinceCode},function(data){
			var result = data;
            if (result.code == 1) {
                if(result.items.length>0){
                	var city_html = "";
                   	for(var pr in result.items){
                   		city_html += '<li class="lines" onclick="setVal(\'city_code\','+result.items[pr].cityCode+',\''+result.items[pr].cityName+'\')" >'+result.items[pr].cityName+'</li>';
                   	}
                   	
                   	$("#shiBox1").html(city_html);
                   	$("#shiBox1").show();
                }
            }
		});
	}
	//县
	function getArea(){
		var city_code = $("#city_code").val();
		$.getJSON("${ctx}/hArea/getHAreaJson",{cityCode:city_code},function(data){
			var result = data;
            if (result.code == 1) {
                if(result.items.length>0){
                	var area_html = "";
                	for(var pr in result.items){
                		area_html += '<li class="lines" onclick="setVal(\'area_code\','+result.items[pr].areaCode+',\''+result.items[pr].areaName+'\')" >'+result.items[pr].areaName+'</li>';
                	}
                	$("#xianBox1").html(area_html);
                	$("#xianBox1").show();
                }
            }
		});
	}
	function setVal(id,val,name){
		$("#"+id).val(val);
		
		if('province_code' == id){
			$(".resNav").removeClass("on");
			$("#shi").addClass("on");
			
			$("#shengBox").hide();
			$("#shiBox").show();
			$("#xianBox").hide();
	        //获取市
	        
	        getCity();
			$("#addRessSpan").html(name);
		}
		if('city_code' == id){
			$(".resNav").removeClass("on");
			$("#xian").addClass("on");
			
			$("#shengBox").hide();
			$("#shiBox").hide();
			$("#xianBox").show();
			
			getArea();
			$("#addRessSpan").html($("#addRessSpan").html()+","+name);
		}
		if('area_code' == id){
			$("#addRessSpan").html($("#addRessSpan").html()+","+name);
			//去查询
			$(".addRessBox").hide();
		}
	}
	function query(){
		var name = $("#query_name").val();
		var province_code = $("#province_code").val();
		var city_code = $("#city_code").val();
		var area_code = $("#area_code").val();
		location.href = "${ctx}/public/dot/map?name="+name+"&province_code="+province_code+"&city_code="+city_code+"&area_code="+area_code;
	}
	$(function(){
		$("#demo3").paginate({
			count 		: ${newsCount},
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
				location.href = "${ctx}/public/dot/map?p="+page;
			}
		});
		
		
		$(document).on("click",".resNav",function(){
			$(".resNav").removeClass("on");
			$(this).addClass("on");
			var id = $(this).attr("data-id");
			
			// 获取市数据
			if(id == 'shiBox'){
				 getCity();
			} else if(id == 'xianBox'){ // 获取县数据
				getArea();
			}else if(id == 'shengBox'){
				getPro();
			}
			
			$(".addRessPro").hide();
			$("#"+id).show();
		});
		$(document).on("click",'.addRessPro > li',function(){
			var pId = $(this).parent().attr('id');
			$("#"+pId).find("li").removeClass("on");
			$(this).addClass("on");
			// 获取市数据
			if(pId == 'shiBox'){
				
			} else if(pId == 'xianBox'){ // 获取县数据
				
			}
		});
		$(document).on("click","#addRessSpan",function(){
			if($(this).parent().find(".addRessBox").hasClass("on")){
				$(this).parent().find(".addRessBox").removeClass("on");
			} else {
				$(this).parent().find(".addRessBox").addClass("on");
			}
			getPro();
			$(".addRessBox").show();
		});
		$(document).on("click",".aClose",function(){
			$(this).parent().parent().removeClass("on");
		});
// 		$('input').placeholder();
// 		$('textarea').placeholder();
	})
</script>
</body>
</html>
