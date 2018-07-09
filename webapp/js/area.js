var pathName=window.document.location.pathname;
var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
//var projectName = '';
var $gov = function(province,city,area){
	var _this = this;
	this.proObj = $("#"+province);
	this.cityObj = $("#"+city);
	this.areaObj = $("#"+area);
	this.init = function(){
		var proCodeDefault = $(this.proObj).attr('gov-value');
		$.getJSON(projectName+"/hProvince/getHProvinceJson",{},function(data){
			var result = data;
	        if (result.code == 1) {
	            var provinces = "<option value=''>请选择</option>";
	            if(result.items.length>0){
	            	for(var pr in result.items){
	            		provinces += '<option value="'+result.items[pr].provinceCode+'" '+(proCodeDefault==result.items[pr].provinceCode?'selected="selected"':'')+'>'+result.items[pr].provinceName+'</option>'
	            		proCodeDefault==result.items[pr].provinceCode?_this.proObj.val(result.items[pr].provinceCode):void(0);
	            	}
	            }
	        }
	        _this.proObj.html(provinces)
	        _this.proChange();
		});
		_this.proObj.change(function(){_this.proChange()});
		_this.cityObj.change(function(){_this.cityChange()});
	};
	this.proChange = function(){
		_this.areaObj.html("<option value=''>请选择</option>");
		_this.cityObj.html("<option value=''>请选择</option>");
		var cityCodeDefault = $(_this.cityObj).attr('gov-value');
		$.getJSON(projectName+"/hCity/getHCityJson",{provinceCode:_this.proObj.val()},function(data){
			var result = data;
            if (result.code == 1) {
                if(result.items.length>0){
                    if(result.items.length>0){
                    	for(var pr in result.items){
                    		var op = document.createElement('option');
                    		op.value=result.items[pr].cityCode;
                    		op.innerHTML= result.items[pr].cityName;
                    		if(cityCodeDefault==result.items[pr].cityCode){
                    			op.selected = true;
                    			_this.cityObj.val(cityCodeDefault);
                    		}
                    		_this.cityObj.append(op)
                    	}
                    }
                }
            }
            _this.cityChange();
		});
	};
	this.cityChange = function(){
		_this.areaObj.html("<option value=''>请选择</option>");
		var areaCodeDefault = _this.areaObj.attr('gov-value');
		$.getJSON(projectName+"/hArea/getHAreaJson",{cityCode:_this.cityObj.val()},function(data){
			var result = data;
            if (result.code == 1) {
                if(result.items.length>0){
                	for(var pr in result.items){
                		var op = document.createElement('option');
                		op.value=result.items[pr].areaCode;
                		op.innerHTML= result.items[pr].areaName;
                		if(areaCodeDefault==result.items[pr].areaCode)
                			op.selected = true;
                		_this.areaObj.append(op)
                	}
                }
            }
		});
	}
}
$(document).ready(function(){
	if($("#fProvince_code")[0]&&$("#fCity_code")[0]&&$("#fArea_code")[0]){
		new $gov("fProvince_code","fCity_code","fArea_code").init();
	}
	if($("#aProvince_code")[0]&&$("#aCity_code")[0]&&$("#aArea_code")[0]){
		new $gov("aProvince_code","aCity_code","aArea_code").init();
	}
	if($("#mProvince_code")[0]&&$("#mCity_code")[0]&&$("#mArea_code")[0]){
		new $gov("mProvince_code","mCity_code","mArea_code").init();
	}
})
