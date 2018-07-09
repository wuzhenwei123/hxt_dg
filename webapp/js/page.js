//pageNo 当前页
//totalRecords 总条数
//returnNum	每页数
function genPageTag1(pageNo,totalRecords,returnNum,divId){
	
//	alert("pageNo="+pageNo+",totalRecords="+totalRecords+",returnNum="+returnNum);
	var yu = totalRecords%returnNum;
	var zs = parseInt(totalRecords/returnNum);
	if(yu > 0){
		zs +=1;
	}
	if(zs == 0)
		zs = 1;
	pageBar1(pageNo,zs,totalRecords,divId,returnNum); 
}


//	pageNo 当前页
//	totalPage 总页数
//	totalRecords 总条数
function pageBar1(pageNo,totalPage,totalRecords,divId,returnNum){
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
		html +="<li><a onclick=\"searchData1(1)\" href='#'><i class='aweso-icon-fast-backward'></i></a></li>";
		html +="<li><a onclick=\"previousPage1()\" href='#'><i class='aweso-icon-backward'></i></a></li>";
	}
//	html +="<span class=\"pre\" onclick=\"previousPage()\" title=\"上一页\"></span>";
	if(pageNo  == 0) {pageNo++;}
	for(var i = 0; i < allPages; i ++) {
		var flowNumber = (i + 1);	// 当前正在循环的页数
		if(flowNumber == pageNo) {		// 如果是点击的页数，则将class设置为active，用以标识选中
			html += "<li class='active'><a herf=\"javascript:void(0)\"";
		}else{
			html += "<li><a herf=\"javascript:void(0)\"  onclick=\"goPage1("+flowNumber+")\"";	// IE8没有this.text属性
			
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
		html +="<li><a onclick=\"nextPage1("+allPages+")\" href='#'><i class='aweso-icon-forward'></i></a></li>";
		html +="<li><a onclick=\"searchData1("+allPages+")\" href='#'><i class='aweso-icon-fast-forward'></i></a></li>";
	}
	
	html +="<li><a style='padding: 0px;'>";
	html +="<select style='width: 60px;margin-bottom' onchange='selectPage1(this.value,"+pageNo+")'>";
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
function selectPage1(pageNumber,pageNo) {
	$("#returnNum1").val(pageNumber);
	goPage(pageNo);
}
/** 前往指定页面 */
function goPage1(pageNumber) {
	$("#currPage1").val(pageNumber);
	searchData1(pageNumber);
}
/** 上一页 */
function previousPage1(){ 
	var pageNumber = parseInt($("#currPage1").val());
	if(pageNumber == 1) {
		return;
	}
	$("#currPage1").val(pageNumber-1);
	searchData1(pageNumber - 1);
}


/** 下一页 */
function nextPage1(allPages){
	var pageNumber = parseInt($("#currPage1").val());
	if(pageNumber == allPages) {
		return;
	}
	$("#currPage1").val(pageNumber+1);
	searchData1(pageNumber + 1);
}



//pageNo 当前页
//totalRecords 总条数
//returnNum	每页数
function genPageTag2(pageNo,totalRecords,returnNum,divId){
	
//	alert("pageNo="+pageNo+",totalRecords="+totalRecords+",returnNum="+returnNum);
	var yu = totalRecords%returnNum;
	var zs = parseInt(totalRecords/returnNum);
	if(yu > 0){
		zs +=1;
	}
	if(zs == 0)
		zs = 1;
	pageBar2(pageNo,zs,totalRecords,divId,returnNum); 
}


//	pageNo 当前页
//	totalPage 总页数
//	totalRecords 总条数
function pageBar2(pageNo,totalPage,totalRecords,divId,returnNum){
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
		html +="<li><a onclick=\"searchData2(1)\" href='#'><i class='aweso-icon-fast-backward'></i></a></li>";
		html +="<li><a onclick=\"previousPage2()\" href='#'><i class='aweso-icon-backward'></i></a></li>";
	}
//	html +="<span class=\"pre\" onclick=\"previousPage()\" title=\"上一页\"></span>";
	if(pageNo  == 0) {pageNo++;}
	for(var i = 0; i < allPages; i ++) {
		var flowNumber = (i + 1);	// 当前正在循环的页数
		if(flowNumber == pageNo) {		// 如果是点击的页数，则将class设置为active，用以标识选中
			html += "<li class='active'><a herf=\"javascript:void(0)\"";
		}else{
			html += "<li><a herf=\"javascript:void(0)\"  onclick=\"goPage2("+flowNumber+")\"";	// IE8没有this.text属性
			
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
		html +="<li><a onclick=\"nextPage2("+allPages+")\" href='#'><i class='aweso-icon-forward'></i></a></li>";
		html +="<li><a onclick=\"searchData2("+allPages+")\" href='#'><i class='aweso-icon-fast-forward'></i></a></li>";
	}
	
	html +="<li><a style='padding: 0px;'>";
	html +="<select style='width: 60px;' onchange='selectPage2(this.value,"+pageNo+")'>";
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
function selectPage2(pageNumber,pageNo) {
	$("#returnNum2").val(pageNumber);
	goPage2(pageNo);
}
/** 前往指定页面 */
function goPage2(pageNumber) {
	$("#currPage2").val(pageNumber);
	searchData2(pageNumber);
}
/** 上一页 */
function previousPage2(){ 
	var pageNumber = parseInt($("#currPage2").val());
	if(pageNumber == 1) {
		return;
	}
	$("#currPage2").val(pageNumber-1);
	searchData2(pageNumber - 1);
}


/** 下一页 */
function nextPage2(allPages){
	var pageNumber = parseInt($("#currPage2").val());
	if(pageNumber == allPages) {
		return;
	}
	$("#currPage2").val(pageNumber+1);
	searchData2(pageNumber + 1);
}


//pageNo 当前页
//totalRecords 总条数
//returnNum	每页数
function genPageTag3(pageNo,totalRecords,returnNum,divId){
	
//	alert("pageNo="+pageNo+",totalRecords="+totalRecords+",returnNum="+returnNum);
	var yu = totalRecords%returnNum;
	var zs = parseInt(totalRecords/returnNum);
	if(yu > 0){
		zs +=1;
	}
	if(zs == 0)
		zs = 1;
	pageBar3(pageNo,zs,totalRecords,divId,returnNum); 
}


//	pageNo 当前页
//	totalPage 总页数
//	totalRecords 总条数
function pageBar3(pageNo,totalPage,totalRecords,divId,returnNum){
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
		html +="<li><a onclick=\"searchData3(1)\" href='#'><i class='aweso-icon-fast-backward'></i></a></li>";
		html +="<li><a onclick=\"previousPage3()\" href='#'><i class='aweso-icon-backward'></i></a></li>";
	}
//	html +="<span class=\"pre\" onclick=\"previousPage()\" title=\"上一页\"></span>";
	if(pageNo  == 0) {pageNo++;}
	for(var i = 0; i < allPages; i ++) {
		var flowNumber = (i + 1);	// 当前正在循环的页数
		if(flowNumber == pageNo) {		// 如果是点击的页数，则将class设置为active，用以标识选中
			html += "<li class='active'><a herf=\"javascript:void(0)\"";
		}else{
			html += "<li><a herf=\"javascript:void(0)\"  onclick=\"goPage3("+flowNumber+")\"";	// IE8没有this.text属性
			
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
		html +="<li><a onclick=\"nextPage3("+allPages+")\" href='#'><i class='aweso-icon-forward'></i></a></li>";
		html +="<li><a onclick=\"searchData3("+allPages+")\" href='#'><i class='aweso-icon-fast-forward'></i></a></li>";
	}
	
	html +="<li><a style='padding: 0px;'>";
	html +="<select style='width: 60px;' onchange='selectPage3(this.value,"+pageNo+")'>";
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
function selectPage3(pageNumber,pageNo) {
	$("#returnNum3").val(pageNumber);
	goPage3(pageNo);
}
/** 前往指定页面 */
function goPage3(pageNumber) {
	$("#currPage3").val(pageNumber);
	searchData3(pageNumber);
}
/** 上一页 */
function previousPage3(){ 
	var pageNumber = parseInt($("#currPage3").val());
	if(pageNumber == 1) {
		return;
	}
	$("#currPage3").val(pageNumber-1);
	searchData3(pageNumber - 1);
}


/** 下一页 */
function nextPage3(allPages){
	var pageNumber = parseInt($("#currPage3").val());
	if(pageNumber == allPages) {
		return;
	}
	$("#currPage3").val(pageNumber+1);
	searchData3(pageNumber + 1);
}

//pageNo 当前页
//totalRecords 总条数
//returnNum	每页数
function genPageTag4(pageNo,totalRecords,returnNum,divId){
	
//	alert("pageNo="+pageNo+",totalRecords="+totalRecords+",returnNum="+returnNum);
	var yu = totalRecords%returnNum;
	var zs = parseInt(totalRecords/returnNum);
	if(yu > 0){
		zs +=1;
	}
	if(zs == 0)
		zs = 1;
	pageBar4(pageNo,zs,totalRecords,divId,returnNum); 
}


//	pageNo 当前页
//	totalPage 总页数
//	totalRecords 总条数
function pageBar4(pageNo,totalPage,totalRecords,divId,returnNum){
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
		html +="<li><a onclick=\"searchData4(1)\" href='#'><i class='aweso-icon-fast-backward'></i></a></li>";
		html +="<li><a onclick=\"previousPage4()\" href='#'><i class='aweso-icon-backward'></i></a></li>";
	}
//	html +="<span class=\"pre\" onclick=\"previousPage()\" title=\"上一页\"></span>";
	if(pageNo  == 0) {pageNo++;}
	for(var i = 0; i < allPages; i ++) {
		var flowNumber = (i + 1);	// 当前正在循环的页数
		if(flowNumber == pageNo) {		// 如果是点击的页数，则将class设置为active，用以标识选中
			html += "<li class='active'><a herf=\"javascript:void(0)\"";
		}else{
			html += "<li><a herf=\"javascript:void(0)\"  onclick=\"goPage4("+flowNumber+")\"";	// IE8没有this.text属性
			
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
		html +="<li><a onclick=\"nextPage4("+allPages+")\" href='#'><i class='aweso-icon-forward'></i></a></li>";
		html +="<li><a onclick=\"searchData4("+allPages+")\" href='#'><i class='aweso-icon-fast-forward'></i></a></li>";
	}
	
	html +="<li><a style='padding: 0px;'>";
	html +="<select style='width: 60px;' onchange='selectPage4(this.value,"+pageNo+")'>";
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
function selectPage4(pageNumber,pageNo) {
	$("#returnNum4").val(pageNumber);
	goPage4(pageNo);
}
/** 前往指定页面 */
function goPage4(pageNumber) {
	$("#currPage4").val(pageNumber);
	searchData4(pageNumber);
}
/** 上一页 */
function previousPage4(){ 
	var pageNumber = parseInt($("#currPage4").val());
	if(pageNumber == 1) {
		return;
	}
	$("#currPage4").val(pageNumber-1);
	searchData4(pageNumber - 1);
}


/** 下一页 */
function nextPage4(allPages){
	var pageNumber = parseInt($("#currPage4").val());
	if(pageNumber == allPages) {
		return;
	}
	$("#currPage4").val(pageNumber+1);
	searchData4(pageNumber + 1);
}

//pageNo 当前页
//totalRecords 总条数
//returnNum	每页数
function genPageTag5(pageNo,totalRecords,returnNum,divId){
	
//	alert("pageNo="+pageNo+",totalRecords="+totalRecords+",returnNum="+returnNum);
	var yu = totalRecords%returnNum;
	var zs = parseInt(totalRecords/returnNum);
	if(yu > 0){
		zs +=1;
	}
	if(zs == 0)
		zs = 1;
	pageBar5(pageNo,zs,totalRecords,divId,returnNum); 
}


//	pageNo 当前页
//	totalPage 总页数
//	totalRecords 总条数
function pageBar5(pageNo,totalPage,totalRecords,divId,returnNum){
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
		html +="<li><a onclick=\"searchData5(1)\" href='#'><i class='aweso-icon-fast-backward'></i></a></li>";
		html +="<li><a onclick=\"previousPage5()\" href='#'><i class='aweso-icon-backward'></i></a></li>";
	}
//	html +="<span class=\"pre\" onclick=\"previousPage()\" title=\"上一页\"></span>";
	if(pageNo  == 0) {pageNo++;}
	for(var i = 0; i < allPages; i ++) {
		var flowNumber = (i + 1);	// 当前正在循环的页数
		if(flowNumber == pageNo) {		// 如果是点击的页数，则将class设置为active，用以标识选中
			html += "<li class='active'><a herf=\"javascript:void(0)\"";
		}else{
			html += "<li><a herf=\"javascript:void(0)\"  onclick=\"goPage5("+flowNumber+")\"";	// IE8没有this.text属性
			
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
		html +="<li><a onclick=\"nextPage5("+allPages+")\" href='#'><i class='aweso-icon-forward'></i></a></li>";
		html +="<li><a onclick=\"searchData5("+allPages+")\" href='#'><i class='aweso-icon-fast-forward'></i></a></li>";
	}
	
	html +="<li><a style='padding: 0px;'>";
	html +="<select style='width: 60px;' onchange='selectPage5(this.value,"+pageNo+")'>";
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
function selectPage5(pageNumber,pageNo) {
	$("#returnNum5").val(pageNumber);
	goPage5(pageNo);
}
/** 前往指定页面 */
function goPage5(pageNumber) {
	$("#currPage5").val(pageNumber);
	searchData5(pageNumber);
}
/** 上一页 */
function previousPage5(){ 
	var pageNumber = parseInt($("#currPage5").val());
	if(pageNumber == 1) {
		return;
	}
	$("#currPage5").val(pageNumber-1);
	searchData5(pageNumber - 1);
}


/** 下一页 */
function nextPage5(allPages){
	var pageNumber = parseInt($("#currPage5").val());
	if(pageNumber == allPages) {
		return;
	}
	$("#currPage5").val(pageNumber+1);
	searchData5(pageNumber + 1);
}