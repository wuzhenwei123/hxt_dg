var myPlaceholder = function(element) {
	//检测是否需要模拟placeholder
	var placeholder = '';
	if (element && !("placeholder" in document.createElement("input")) && (placeholder = element.getAttribute("placeholder"))) {
		//当前文本控件是否有id, 没有则创建
		var idLabel = element.id ;
		if (!idLabel) {
			idLabel = "placeholder_" + new Date().getTime();
			element.id = idLabel;
		}
		//创建label元素
		var eleLabel = document.createElement("label");
		eleLabel.htmlFor = idLabel;
		eleLabel.style.position = "absolute";
		//根据文本框实际尺寸修改这里的margin值
		eleLabel.style.color = "graytext";
		eleLabel.style.cursor = "text";
		$(eleLabel).addClass("eleLabel");
		//插入创建的label元素节点
		element.parentNode.insertBefore(eleLabel, element);
		//事件
		element.onfocus = function() {
			eleLabel.innerHTML = "";
		};
		element.onblur = function() {
			if (this.value === "") {
				eleLabel.innerHTML = placeholder;  
			}
		};
		//样式初始化
		if (element.value === "") {
			eleLabel.innerHTML = placeholder;   
		}
	}	
};
$(function(){
	var arrayInput = document.getElementsByTagName("input");
	for( var i = 0; i < arrayInput.length; i ++){
		myPlaceholder(arrayInput[i]);
	}
})

