// JavaScript Document
$(function(){
	var val = $("#searchBankInput").val();
		if(val.length  < 1){
			$("#searchBankInput").removeClass("on");
			$("#searchBankInput").parent().find("i").html("搜索");
			$("#searchBankInput").parent().find("i").removeClass("on");
		} else  {
			$("#searchBankInput").addClass("on");
			$("#searchBankInput").parent().find("i").html("");
			$("#searchBankInput").parent().find("i").addClass("on");
		}
		$(document).on("click","#sel_bank_search_box",function(){
			
			$(this).find("i").addClass("on");
			$(this).find("input").addClass("on");
			$("#searchBankInput").focus();
		});
		
		$(document).on("click","#searchBankInput",function(){
			var txt = $(this).val();
			if(txt.length  < 1){
				$(this).removeClass("on");
				$(this).parent().find("i").html("搜索");
				$(this).parent().find("i").removeClass("on");
			} else  {
				$(this).addClass("on");
				$(this).parent().find("i").html("");
				$(this).parent().find("i").addClass("on");
			}
		});
		$(document).on("keyup","#searchBankInput",function(){
			var val = $(this).val();
			if(val.length  < 1){
				$(this).blur();
				$(this).removeClass("on");
				$(this).parent().find("i").html("搜索");
				$(this).parent().find("i").removeClass("on");
			} else  {
				$(this).addClass("on");
				$(this).parent().find("i").html("");
				$(this).parent().find("i").addClass("on");
			}
		});
		
		$(document).on("click","#cl_sel_bank",function(){
			$(".bankTxtBox").stop().animate({"bottom":"-80%"},300,function(){
				$(".bankTxtBox").hide();
				$(".selBoxYn").hide();
			});
			var html = $(".ui_line_label.ui_checked .ui_checkbox:checked").parent().parent().find(".ui_cell_flex").html();
			var html1 = $(".ui_line_label.ui_checked .ui_checkbox:checked").val();
			$("#show_sel_bank_box .yhName").html(html);
			$("#bankId").val(html1);
		});
		$(document).on("click","#show_sel_bank_box",function(){
				$(".bankTxtBox").show();
				$(".selBoxYn").show();
				$(".bankTxtBox").stop().animate({"bottom":"0"},300);
				loadData("");
		})
})