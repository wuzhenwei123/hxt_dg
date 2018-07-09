package com.hxt.hPublic.controller;

import java.util.List;

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
import com.hxt.hAmmeterInfo.model.HAmmeterInfo;
import com.hxt.hAmmeterInfo.service.HAmmeterInfoService;
import com.hxt.hProxyMessage.model.HProxyMessage;
import com.hxt.hProxyMessage.service.HProxyMessageService;
import com.hxt.hReviewUser.service.HReviewUserService;
import com.sys.manageAdminUser.model.ManageAdminUser;
/**
 * @author	zhangyiyang
 * @time	2016年08月02日 22:11:34
 */
@Controller
@RequestMapping("/public/withhold")
public class WithholdController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HProvinceController.class); //Logger
	@Autowired
	private HReviewUserService reviewuserService = null;
	@Autowired
	private HAmmeterInfoService hammeterInfoService = null;
	@Autowired
	private HProxyMessageService hproxymessageService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
		model.addAttribute("left_nav", "withhold");
		ManageAdminUser user = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
		HProxyMessage hProxyMessage1 = new HProxyMessage();
		hProxyMessage1.setCId(user.getCompanyId());
		hProxyMessage1.setState(1);
		hProxyMessage1 = hproxymessageService.getHProxyMessage(hProxyMessage1);

		if(hProxyMessage1!=null&&hProxyMessage1.getCheckState()==null){
			return "/public/mAmmeter1";//未开通代扣
		}else if(hProxyMessage1!=null&&(hProxyMessage1.getCheckState()==1||hProxyMessage1.getCheckState()==3||hProxyMessage1.getCheckState()==5||hProxyMessage1.getCheckState()==6||hProxyMessage1.getCheckState()==7||hProxyMessage1.getCheckState()==8)){
			//查询是否有手机缴费号
//			HAmmeterInfo ammeterInfo = new HAmmeterInfo();
//			ammeterInfo.setC_id(user.getCompanyId());
//			ammeterInfo.setProxy_flag(1);
//			ammeterInfo.setPay_status("1");
//			ammeterInfo.setDelete_state(1);
//			int count  = hammeterInfoService.getHAmmeterInfoListCount(ammeterInfo);
//			if(count>0){
//				return "redirect:/public/withhold/submit";
//			}else{
				return "/public/mAmmeter";
//			}
		}else{
			return "/public/mAmmeter1";//未开通代扣
		}
	}
	/**
	 * 设置代扣完成页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/submit", method = RequestMethod.GET)
	public String submit(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
		model.addAttribute("left_nav", "withhold");
		return "/public/mAmmeter2";
	}
	@RequestMapping(value = "/checkVercode", method = RequestMethod.POST)
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
	@RequestMapping(value = "/setupSub", method = RequestMethod.POST)
	public String setupSub(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		String ids = RequestHandler.getString(request, "ids");
		String type = RequestHandler.getString(request, "type");
		if(StringUtils.isNotBlank(ids)){
			if(ids.length()>1){
				ids = ids.substring(0, ids.length()-1);
				String[] idsArray = ids.split(",");
				for(int i = 0 ;i<idsArray.length;i++){
					HAmmeterInfo info = new HAmmeterInfo();
					info.setA_id(Integer.valueOf(idsArray[i]));
					if("1".equals(type)){
						info.setProxy_flag(1);
					}else{
						info.setProxy_flag(0);
					}
					try {
						hammeterInfoService.updateHAmmeterInfo(info);
					} catch (Exception e) {
						e.printStackTrace();
						writeErrorMsg("设置失败", null, response);
					}
				}
			}
		}
		writeSuccessMsg("设置成功", null, response);
		return null;
	}
	@RequestMapping(value = "/setupAll", method = RequestMethod.POST)
	public String setupAll(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception
	{
		String ids = RequestHandler.getString(request, "ids");
		String type = RequestHandler.getString(request, "type");
		Integer cId = RequestHandler.getInteger(request, "cId");
		List<HAmmeterInfo> list = null;
		if(cId!=null){
			HAmmeterInfo info = new HAmmeterInfo();
			info.setC_id(cId);
			list = hammeterInfoService.getHAmmeterInfoBaseList(info);
		}
		if(StringUtils.isNotBlank(ids)&&list!=null&&list.size()>0){
			if(ids.length()>1){
				ids = ids.substring(0, ids.length()-1);
				String[] idsArray = ids.split(",");
				for(HAmmeterInfo infoa:list){
					boolean b = false;
					for(int i = 0 ;i<idsArray.length;i++){
						if(Integer.valueOf(idsArray[i]).equals(infoa.getA_id())){
							b = true;
							break;
						}
					}
					if(b){
						infoa.setProxy_flag(1);
					}else{
						infoa.setProxy_flag(0);
					}
					try {
						hammeterInfoService.updateHAmmeterInfo(infoa);
					} catch (Exception e) {
						e.printStackTrace();
						writeErrorMsg("设置失败", null, response);
					}
				}
//				for(int i = 0 ;i<idsArray.length;i++){
//					HAmmeterInfo info = new HAmmeterInfo();
//					info.setA_id(Integer.valueOf(idsArray[i]));
//					if("1".equals(type)){
//						info.setProxy_flag(1);
//					}else{
//						info.setProxy_flag(0);
//					}
//					try {
//						hammeterInfoService.updateHAmmeterInfo(info);
//					} catch (Exception e) {
//						e.printStackTrace();
//						writeErrorMsg("设置失败", null, response);
//					}
//				}
			}
		}else{
//			if(cId!=null){
//				HAmmeterInfo info = new HAmmeterInfo();
//				info.setC_id(cId);
//				List<HAmmeterInfo> list = hammeterInfoService.getHAmmeterInfoBaseList(info);
				if(list!=null&&list.size()>0){
					for(HAmmeterInfo infoa:list){
						infoa.setProxy_flag(0);
						hammeterInfoService.updateHAmmeterInfo(infoa);
					}
				}
//			}
		}
		writeSuccessMsg("设置成功", null, response);
		return null;
	}
}
