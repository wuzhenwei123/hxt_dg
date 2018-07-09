$(function() {
	
});
/**
 * 
 * @param base_path 项目跟目录
 * @param picker 
 * @param callback 回调
 * @param accept 上传格式
 * @param proVal 上传路径(/upload 后面的目录)
 * @param filetype 上传类型（pic or file）
 * 
 */
function upload(base_path,picker,callback,accept,proVal,filetype){
	
	if(accept == ''){
		accept = {
	        title: 'Images',
	        extensions: 'gif,jpg,jpeg,bmp,png',
	        mimeTypes: 'image/*'
	    }
	}
	var uploader = WebUploader.create({
	    // 选完文件后，是否自动上传。
	    auto: true,
	    // swf文件路径
	    swf:base_path+'/plus/webuploader/Uploader.swf',
	    // 文件接收服务端。
	    server: base_path+'/upload/exec?proVal='+proVal+"&filetype="+filetype,
	    // 选择文件的按钮。可选。
	    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
	    pick: '#'+picker,
	    // 只允许选择图片文件。
	    accept: accept,
	    fileSizeLimit:5*1024*1024
	});
	
	uploader.on( 'fileQueued', function( file ) {
		if($("#"+picker+"Gif")){
			$("#"+picker+"Gif").show();
		}
		if($("#load")){
			$HXT.loading('图片处理中');
			$('.popBox').css('height','122px');
        	$('.popBox').css('width','122px');
        	$('.popBox').css('margin-left','-61px');
        	$('.popBox').css('margin-top','-61px');
//			$("#load").show();
		}
		if($("#loadingToast1")){
			$("#loadingToast1").show();
		}
	});
	
	uploader.on( 'uploadSuccess', function( file,response ) {
		
		callback(response);
	});

	uploader.on( 'uploadError', function( file ) {
		tipError(file.id+",上传失败.");
	});

	uploader.on( 'uploadComplete', function( file ) {
	});
}
function upload2(base_path,picker,callback,accept,proVal,filetype,aid){
	
	if(accept == ''){
		accept = {
				title: 'Images',
				extensions: 'gif,jpg,jpeg,bmp,png',
				mimeTypes: 'image/*'
		}
	}
	var uploader = WebUploader.create({
		// 选完文件后，是否自动上传。
		auto: true,
		// swf文件路径
		swf:base_path+'/plus/webuploader/Uploader.swf',
		// 文件接收服务端。
		server: base_path+'/upload/exec?proVal='+proVal+"&filetype="+filetype,
		// 选择文件的按钮。可选。
		// 内部根据当前运行是创建，可能是input元素，也可能是flash.
		pick: '#'+picker,
		// 只允许选择图片文件。
		accept: accept,
		fileSizeLimit:5*1024*1024
	});
	
	uploader.on( 'fileQueued', function( file ) {
		if($("#"+picker+"Gif")){
			$("#"+picker+"Gif").show();
		}
		if($("#load")){
			$HXT.loading('图片处理中');
			$('.popBox').css('height','122px');
			$('.popBox').css('width','122px');
			$('.popBox').css('margin-left','-61px');
			$('.popBox').css('margin-top','-61px');
//			$("#load").show();
		}
		if($("#loadingToast1")){
			$("#loadingToast1").show();
		}
	});
	
	uploader.on( 'uploadSuccess', function( file,response ) {
		
		callback(aid,response);
	});
	
	uploader.on( 'uploadError', function( file ) {
		tipError(file.id+",上传失败.");
	});
	
	uploader.on( 'uploadComplete', function( file ) {
	});
}
function tipError(msg){
	var n = notyfy({
		text: '<strong>'+msg+'</strong>',
		type: 'error',
		layout: 'topCenter',
		theme: 'boolight'
	});	
	setTimeout(function(){n.close();},1000);
}
function tipOk(msg){
	var n = notyfy({
		text: '<strong>'+msg+'</strong>',
		type: 'success',
		layout: 'topCenter',
		theme: 'boolight'
	});	
	setTimeout(function(){n.close();},1000);
}
function tipInfo(msg){
	var n = notyfy({
		text: '<strong>'+msg+'</strong>',
		type: 'information',
		layout: 'topCenter',
		theme: 'boolight'
	});	
}
function tip(msg){
	var n = notyfy({
		text: '<strong>'+msg+'</strong>',
		type: 'topCenter',
		layout: 'topCenter',
		theme: 'boolight'
	});	
}
/**
 * 弹框
 * @param url 页面路径(绝对路径)
 * @param data 参数
 */
function show(url,data){
	$('body').modalmanager('loading');
	setTimeout(function () {
		$modal.load(url, data, function () {
			$modal.modal();
		});
	}, 500);
}
//获取iframe值
function getIframeVal(iframe,key){
	var obj = iframe.document.getElementById(key);
	if(obj && obj != null){
		return obj.value;
	}
	return null;
}
function getVal(key){
	var obj = document.getElementById(key);
	if(obj && obj != null){
		return obj.value;
	}
	return null;
}

// 排序
function initTh(){
	$(".sortTh").click(function(event){
		var column = this.getAttribute("column");
		var id = this.getAttribute("id");
		if(column!=''){
			thImg(id,column);
		}
	});
  
  	var sortColumn = $("#sortColumn").val();
	if(sortColumn!=''){
		var arrayObj = new Array();
		arrayObj = sortColumn.split(' ');
		var id = 'th_'+arrayObj[0];
		var upImg = "aweso-icon-sort-up";
		var downImg = "aweso-icon-sort-down";
		var img = "aweso-icon-sort";
		var txt = $("#"+id).html();
		var spanObj = $("#"+id).children("span");
		var spanClass = spanObj.attr("class");//用来排序的span图片
		setTimeout(function(){
			if(arrayObj[1] == 'desc'){//desc
				if(spanClass.indexOf(upImg)!=-1 || spanClass.indexOf(downImg)!=-1){//含有图片
					
				}else{//空
					spanObj.addClass(downImg);
				}
			}else if(arrayObj[1] == 'asc'){//asc
				if(spanClass.indexOf(upImg)!=-1 || spanClass.indexOf(downImg)!=-1){//含有图片
					
				}else{//空
					spanObj.addClass(upImg);
				}
			}
			
			spanObj.removeClass(img);
		},300);
	}
}
//排序
function thImg(id,column){
	var upImg = "aweso-icon-sort-up";
	var downImg = "aweso-icon-sort-down";
	var img = "aweso-icon-sort";
	
	var spanObj = $("#"+id).children("span");
	var spanClass = spanObj.attr("class");//用来排序的span图片
	if(spanClass.indexOf(upImg)!=-1){//上
		spanObj.removeClass(upImg);
		spanObj.addClass(downImg);
		$("#sortColumn").val(column+" desc");
	}else if(spanClass.indexOf(downImg)!=-1){
		spanObj.removeClass(downImg);
		spanObj.addClass(upImg);
		$("#sortColumn").val(column+" asc");
	}else{
		spanObj.removeClass(upImg);
		spanObj.addClass(downImg);
		$("#sortColumn").val(column+" desc");
	}
	spanObj.removeClass(img);
	searchData($("#currPage").val());
}
// 初始化表格
function initTr(){
	$("#exampleDTC").colResizable({
        liveDrag:true,
        gripInnerHtml:"<div class='grip'></div>", 
        draggingClass:"dragging", 
        minWidth:30
      }); 
	// 表格行，鼠标放上去变色
	$(".tr:odd").css("background", "#FFFCEA");
	$(".tr:odd").each(function() {
		$(this).hover(function() {
			$(this).css("background-color", "#FFE1FF");
		}, function() {
			$(this).css("background-color", "#FFFCEA");
		});
	});
	$(".tr:even").each(function() {
		$(this).hover(function() {
			$(this).css("background-color", "#FFE1FF");
		}, function() {
			$(this).css("background-color", "#fff");
		});
	});
}

// 全选
function checkAll(obj,dom){
	var attrStr = $("#"+obj).is(":checked");
	if(!attrStr){//不选
		$.uniform.update($("input[name='"+dom+"']").attr("checked", false));//取消选中复选框
	}else{//全选
		$.uniform.update($("input[name='"+dom+"']").attr("checked", true));//选中复选框
	}
}

//pageNo 当前页
//totalRecords 总条数
//returnNum	每页数
function genPageTag(pageNo,totalRecords,returnNum,divId){
	
//	alert("pageNo="+pageNo+",totalRecords="+totalRecords+",returnNum="+returnNum);
	var yu = totalRecords%returnNum;
	var zs = parseInt(totalRecords/returnNum);
	if(yu > 0){
		zs +=1;
	}
	if(zs == 0)
		zs = 1;
	pageBar(pageNo,zs,totalRecords,divId,returnNum); 
}


//	pageNo 当前页
//	totalPage 总页数
//	totalRecords 总条数
function pageBar(pageNo,totalPage,totalRecords,divId,returnNum){
	if(!pageNo){
		pageNo = 1;
	}
	var allPages = 0;//总页数
	var count = totalRecords;
	if( count != 0 ) {
		if( count % 10 == 0 ) {
			allPages = count/returnNum;
		} else {
			allPages = Math.floor(count/returnNum)+1;
		}
	}
	
	var html = "<ul>";
	html +="<li class='disabled'><a href='javascript:void(0)'>"+pageNo+"/"+allPages+"</a></li>";
	if(pageNo == 1){
		html +="<li class='disabled'><a href='javascript:void(0)'><i class='aweso-icon-fast-backward'></i></a></li>";
		html +="<li class='disabled'><a href='javascript:void(0)'><i class='aweso-icon-backward'></i></a></li>";
	}else{
		html +="<li><a onclick=\"searchData(1)\" href='#'><i class='aweso-icon-fast-backward'></i></a></li>";
		html +="<li><a onclick=\"previousPage()\" href='#'><i class='aweso-icon-backward'></i></a></li>";
	}
//	html +="<span class=\"pre\" onclick=\"previousPage()\" title=\"上一页\"></span>";
	if(pageNo  == 0) {pageNo++;}
	for(var i = 0; i < allPages; i ++) {
		var flowNumber = (i + 1);	// 当前正在循环的页数
		if(flowNumber == pageNo) {		// 如果是点击的页数，则将class设置为active，用以标识选中
			html += "<li class='active'><a herf=\"javascript:void(0)\"";
		}else{
			html += "<li><a herf=\"javascript:void(0)\"  onclick=\"goPage("+flowNumber+")\"";	// IE8没有this.text属性
			
		}
		if(allPages > 8) { // 只显示8页，两边的隐藏
			if(pageNo <= 5) {
				if(flowNumber > 8) {
					html += " style=\"display:none\"";
				}
			} else if(pageNo > (allPages - 5)) {
				if(flowNumber < (allPages - 8)) {
					html += " style=\"display:none\"";
				}
			} else {
				if((pageNo - flowNumber) > 4 || (flowNumber - pageNo) > 5) {
					html += " style=\"display:none\"";
				}
			}
		}
		html +=	">" + flowNumber + "</a></li>";
	}
	if(pageNo == allPages){
		html +="<li class='disabled'><a href='javascript:void(0)'><i class='aweso-icon-forward'></i></a></li>";
		html +="<li class='disabled'><a href='javascript:void(0)'><i class='aweso-icon-fast-forward'></i></a></li>";
	}else{
		if(allPages > 8) { 
			html += "<li><a herf=\"javascript:void(0)\">" + "..." + "</a></li>";
		}
		html +="<li><a onclick=\"nextPage("+allPages+")\" href='#'><i class='aweso-icon-forward'></i></a></li>";
		html +="<li><a onclick=\"searchData("+allPages+")\" href='#'><i class='aweso-icon-fast-forward'></i></a></li>";
	}
	
	html +="<li><a style='padding: 0px;'>";
	html +="<select style='width: 60px;' onchange='selectPage(this.value,"+pageNo+")'>";
	if(returnNum == 10){
		html +="    <option selected='selected' value=\"10\">10</option>";
	}else{
		html +="    <option value=\"10\">10</option>";
	}
	if(returnNum == 15){
		html +="    <option selected='selected' value=\"15\">15</option>";
	}else{
		html +="    <option value=\"15\">15</option>";
	}
	if(returnNum == 50){
		html +="    <option selected='selected' value=\"50\">50</option>";
	}else{
		html +="    <option value=\"50\">50</option>";
	}
	if(returnNum == 100){
		html +="    <option selected='selected' value=\"100\">100</option>";
	}else{
		html +="    <option value=\"100\">100</option>";
	}
	if(returnNum == 200){
		html +="    <option selected='selected' value=\"200\">200</option>";
	}else{
		html +="    <option value=\"200\">200</option>";
	}
	html +="</select></a>";
	html += "</li>";
	html += "</ul>";
	
	$("#"+divId).html(html);
	$("html").getNiceScroll().resize();
}
function selectPage(pageNumber,pageNo) {
	$("#returnNum").val(pageNumber);
	goPage(pageNo);
}
/** 前往指定页面 */
function goPage(pageNumber) {
	$("#currPage").val(pageNumber);
	searchData(pageNumber);
}
/** 上一页 */
function previousPage(){ 
	var pageNumber = parseInt($("#currPage").val());
	if(pageNumber == 1) {
		return;
	}
	$("#currPage").val(pageNumber-1);
	searchData(pageNumber - 1);
}


/** 下一页 */
function nextPage(allPages){
	var pageNumber = parseInt($("#currPage").val());
	if(pageNumber == allPages) {
		return;
	}
	$("#currPage").val(pageNumber+1);
	searchData(pageNumber + 1);
}


//格式化日期
function genDateTimeAll(data){
	if(data == null){
		return "";
	}else{
		var c = new Date(); 
		c.setTime(data.time);
		var mon = c.getMonth()+1;
		if(mon<10){
			mon = "0" + mon;
		}
		var dat = c.getDate();
		if(dat<10){
			dat = "0" + dat;
		}
		
		var h = c.getHours();
		if(parseInt(h,10)<10){
			h = "0" + h;
		}
		
		var m = c.getMinutes();
		if(parseInt(m,10)<10){
			m = "0" + m;
		}
		var s = c.getSeconds();
		if(parseInt(s,10)<10){
			s = "0" + s;
		}
		return c.getFullYear()+"-"+mon+"-"+dat+" "+h+":"+m+":"+s;
	}
}
//格式化日期
function genDate(data){
	if(data == null){
		return "";
	}else{
		var c = new Date(); 
		c.setTime(data.time);
		var mon = c.getMonth()+1;
		if(mon<10){
			mon = "0" + mon;
		}
		var dat = c.getDate();
		if(dat<10){
			dat = "0" + dat;
		}
		return c.getFullYear()+"-"+mon+"-"+dat;
	}
}


function validateForm(myform){
	 var myform = document.forms[myform];
	 var validateResult = true;
	 
	 if (myform){
		 var errorMsg = "";
		 var i;
		 var formElement;
		 for (i=0;i<myform.elements.length;i++){
			  //非自定义属性的元素不校验
			  formElement = myform.elements[i];
			  if (formElement.getAttribute("warnName") == "undefined" || formElement.getAttribute("warnName") == null){
			  	continue;
			  }
		  	// 校验当前元素
		  	validateResult = validateElementById(formElement.id);
		  	if (!validateResult){
		  		break;
		  	}
		 }
	 }
	 return validateResult;
}

//验证方法入口，传入需要验证对象的ID，以及存放error字符串的ID
function validateElementById(input){
	var inputObj = $("#"+input);
	var inputobj = document.getElementById (input);
	var warnStr = validateResultOfInput(inputobj);
	if (warnStr != ""){
		setRed(inputObj,warnStr);
		inputObj.blur(function(){
			warnStr = validateResultOfInput(inputobj);
			if(warnStr != ''){
				setRed(inputObj,warnStr);
			}else{
				setGreen(inputObj,warnStr);
				return true;
			}
	    });
		
		return false;
	}else{
		setGreen(inputObj,warnStr);
		return true;
	}
}
//置红字体
function setRed(obj,warnStr){
	obj.parent().parent().addClass("error");
	obj.next().html("<i class='fontello-icon-cancel-circle'> "+warnStr);
}
//置绿字体
function setGreen(obj,warnStr){
	obj.parent().parent().addClass("success");
	obj.next().removeClass("text-red");
	obj.next().html("<i class='fontello-icon-ok-circle'> "+warnStr);
}

//检测指定文本框输入是否合法,并返回验证结果
function validateResultOfInput(input){
	 var value = input.value;
	 var warnName = input.getAttribute("warnName");
	 if (warnName){
	 	warnName = "'" + warnName + "' ";
	 }else{
	 	warnName = "";
	 }
	 
	 var errorMsg = "";
	 //非空校验
	 if(input.getAttribute("isNotNull")||input.getAttribute("isnotnull")){
	  if ((input.getAttribute("isNotNull")=="true"|| input.getAttribute("isnotnull")=="true")&& _isBlank(value)){
	   errorMsg = warnName + "不能为空!";
	   return  errorMsg;
	  }
	 }
	 //长度校验
	 if(input.getAttribute("maxsize")){
	  if(_getByteLength(value)>parseInt(input.getAttribute("maxsize"))){
	   errorMsg = warnName + "最大长度" + input.getAttribute("maxsize") + "个字节，一个汉字占2个字节。";
	   return  errorMsg;
	  }
	 }
	 if(input.getAttribute("minsize")){
	  if(_getByteLength(value)<parseInt(input.getAttribute("minsize"))){
	   errorMsg = warnName + "最小长度" + input.getAttribute("minsize") + "个字节，一个汉字占2个字节。";
	   return  errorMsg;
	  }
	 }
	 //类型校验
	 if(input.getAttribute("datatype")){
	  switch(input.getAttribute("datatype")){
	   case "number":
	    if (_isNumber(value)==false){//不是数字
	     errorMsg = warnName + "值必须为数字!";
	     return  errorMsg;
	    }else{//是数字，校验范围
	     var maxValue = 1*(input.getAttribute("maxValue"));
	     var theValue = 1*value;
	     if(theValue > maxValue){
	      errorMsg = warnName + "值必须 < " + maxValue + "!";
	      return  errorMsg;
	     }
	     var minValue = 1*(input.getAttribute("minValue"));
	     if(theValue < minValue){
	      errorMsg = warnName + "值必须 > " + minValue + "!";
	      return  errorMsg;
	     }
	    }
	    break;
	   case "text"://其它类型可以自己扩展
	    if (false){
	     errorMsg = warnName + " 输入不合法!";
	     return  errorMsg;
	    }
	    break;
	   case "unZh"://用户名类型,不含汉字
	    if(_isUname(value)){
	     errorMsg = warnName + " 不能含有汉字 ！";
	     return errorMsg;
	    }
	    break;
	   case "phone":
		if(_isPhone(value)){
		 errorMsg = warnName + " 不正确！";
		 return errorMsg;
		}
		break;
	   case "double"://double类型
	    if (!_isDouble(value)){
	     errorMsg = warnName + " 值必须为数字,或者带有两位小数的数字!";
	     return  errorMsg;
	    }
	    break;
	   case "loginname"://double类型
		   if (!_isLoginName(value)){
			   errorMsg = warnName + " 数字或字母!";
			   return  errorMsg;
		   }
		   break;
	   default  : break; 
	  }
	 }
	 return errorMsg;
}
function _isUname(value){
	if((escape(value).indexOf("%u"))<0){
		return false;
	}else{
		return true;
	}
}
function _isPhone(value){
	var pattern = /^0{0,1}(17[0-9]|13[0-9]|15[3-9]|15[0-2]|18[0-9])[0-9]{8}$/;
	if(pattern.test(value)){
		return false;
	}else{
		return true;
	}
}
//获取字节长度(注：对于Unicode字符而言，一个英文字母、一个汉字都是一个Unicode字符)
//但对于字节而言 汉字是2个字节，英文字母是一个字节。数据库中存储的长度往往以字节计
//对于UTF-8编码 汉字是3个字节，根据情况把len+=2 变为 len+=3
function _getByteLength(str){
 var i;
 var len;
 len = 0;
 for(i=0;i<str.length;i++){
  if(str.charCodeAt(i)>255){
   len+=2;
  }else{
   len++;
  }
 }
 return len;
}
//检测字符串是否为空
function _isBlank(value){
 var str = value;
 return str.length == 0;
}

//检测字符串是否全为数字
function _isNumber(value){
    if(isNaN(value)==0)
        return true;
 else
  return false;
}

//将true，false 转化为对应的字符串

function genBoolToStr(state,trueStr,falseStr){
    return (state?trueStr:falseStr);
}

function checkValid(Obj,msgObj,maxLength){
    var obj = eval(Obj);
    if(obj.value.length < maxLength){
        var str = "您还可以输入" + (maxLength-obj.value.length).toString() + "字";
        $("#"+msgObj).html(str);
    }else{
        obj.value = obj.value.substring(0,maxLength);
        $("#"+msgObj).html("您输入的总字数已达到最大限制！");
    }
}

function textlimit(Obj, maxLength){
    var obj = eval(Obj);
    if (obj.value.length >= maxLength) 
        obj.value = obj.value.substring(0,maxLength-1);        
}
function _isDouble(value){
	var reg = /^\d{1,5}(?:\.\d{1,2})?$/;
	return reg.test(value);
}
function _isLoginName(value){
	var reg = /^[0-9a-zA-Z]+$/;
	return reg.test(value);
}