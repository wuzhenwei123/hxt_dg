package com.hxt.hPublic.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.base.controller.BaseController;
import com.base.utils.RequestHandler;
import com.base.utils.SessionName;
import com.hxt.hReviewUser.service.HReviewUserService;
/**
 * @author	zhangyiyang
 * @time	2016年08月02日 22:11:34
 */
@Controller
@RequestMapping("/public/payFee")
public class PayfeeController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HProvinceController.class); //Logger
	@Autowired
	private HReviewUserService reviewuserService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		model.addAttribute("left_nav", "payFee");
		return "/public/jfQuery";
	}
	@RequestMapping(value = "/toElectricPay", method = RequestMethod.GET)
	public String toElectricPay(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/public/electricPay";
	}
	@RequestMapping(value = "/toHistory", method = RequestMethod.GET)
	public String toHistory(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/public/payHistory";
	}
	@RequestMapping(value = "/toQuery", method = RequestMethod.GET)
	public String toQuery(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		Integer errCount = 0;
		if(request.getSession().getAttribute("errCount")!=null){
			errCount = Integer.valueOf(request.getSession().getAttribute("errCount").toString());
		}
		model.addAttribute("errCount", errCount);
		model.addAttribute("nav", "chaxun");
		return "/public/query";
	}
	@RequestMapping(value = "/checkVercode", method = RequestMethod.GET)
	public String checkVercode(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		String vercode = RequestHandler.getString(request, "vercode");
		Object token = request.getSession().getAttribute(SessionName.TOKEN);// 验证码
		if (StringUtils.isBlank(vercode)||token == null || !vercode.equalsIgnoreCase(token.toString())) {
			writeErrorMsg("验证码不正确！", null, response);
		}else{
			writeSuccessMsg("success", null, response);
		}
		return null;
	}
}
