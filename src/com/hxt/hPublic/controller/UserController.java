package com.hxt.hPublic.controller;

import java.awt.Color;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.record.formula.functions.T;
import org.patchca.color.ColorFactory;
import org.patchca.filter.predefined.CurvesRippleFilterFactory;
import org.patchca.filter.predefined.DiffuseRippleFilterFactory;
import org.patchca.filter.predefined.DoubleRippleFilterFactory;
import org.patchca.filter.predefined.MarbleRippleFilterFactory;
import org.patchca.filter.predefined.WobbleRippleFilterFactory;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.utils.encoder.EncoderHelper;
import org.patchca.word.RandomWordFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.base.controller.BaseController;
import com.base.utils.Common;
import com.base.utils.DateFormatToString;
import com.base.utils.FileUploadConstants;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.utils.ResultRsp;
import com.base.utils.SendMsgUtil;
import com.base.utils.SessionName;
import com.hxt.hAmmeterInfo.model.HAmmeterInfo;
import com.hxt.hAmmeterInfo.service.HAmmeterInfoService;
import com.hxt.hCommon.service.HCommonService;
import com.hxt.hCompany.model.HCompany;
import com.hxt.hCompany.service.HCompanyService;
import com.hxt.hReviewUser.model.HReviewUser;
import com.hxt.hReviewUser.service.HReviewUserService;
import com.hxt.hXieyi.model.HXieyi;
import com.hxt.hXieyi.service.HXieyiService;
import com.sys.adminRole.model.AdminRole;
import com.sys.adminRole.service.AdminRoleService;
import com.sys.adminRoleColumn.service.AdminRoleColumnService;
import com.sys.adminSkins.model.AdminSkins;
import com.sys.adminSkins.service.AdminSkinsService;
import com.sys.adminUserRole.model.AdminUserRole;
import com.sys.adminUserRole.service.AdminUserRoleService;
import com.sys.manageAdminUser.controller.ManageAdminUserController;
import com.sys.manageAdminUser.model.ManageAdminUser;
import com.sys.manageAdminUser.service.ManageAdminUserService;
import com.sys.manageColumn.model.ManageColumn;
import com.sys.manageColumn.service.ManageColumnService;

/**
 * @author keeny
 * @time 2015年02月04日 11:09:21
 */
@Controller
@RequestMapping("/public")
public class UserController extends BaseController {
	// private static Logger logger =
	// Logger.getLogger(ManageAdminUserController.class); //Logger
	@Autowired
	private ManageAdminUserService manageadminuserService = null;// 用户
	@Autowired
	private HCompanyService hcompanyService = null;
	@Autowired
	private HReviewUserService hreviewuserService = null;
	@Autowired
	private HXieyiService hxieyiService = null;
	@Autowired
	private HAmmeterInfoService hammeterinfoService = null;
	private static Random random = new Random();
	/***************** 页面中转 *********************/
	@RequestMapping(value = "/xieyi")
	public String xieyi(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		List<HXieyi> xieyiList = hxieyiService.getHXieyiBaseList(new HXieyi());
		if(xieyiList.size() != 0 ){
			model.addAttribute("xieyi", xieyiList.get(0));
		}
		
		return "/public/xieyi";
	}
	/**
	 * 复核人员管理
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/pcwgl")
	public String pcwgl(HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("left_nav", "pcwgl");//左导航
		
		ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
		HCompany tmp = new HCompany();
		tmp.setUser_id(adminUser.getAdminId());
		List<HCompany> tmplist = hcompanyService.getHCompanyBaseList(tmp);
		if(tmplist!=null && tmplist.size() != 0){
			HCompany company = tmplist.get(0);
			 HReviewUser hReviewUser = new HReviewUser();
			 hReviewUser.setCompanyId(company.getId());
			 hReviewUser.setIsDefault(1);
			 List<HReviewUser> reviewuserList = hreviewuserService.getHReviewUserBaseList(hReviewUser);
			 if(reviewuserList!=null && reviewuserList.size() >0 ){
				 model.addAttribute("vo", reviewuserList.get(0));
			 }
			 model.addAttribute("companyId", company.getId());
		}
		
		return "/public/pcwgl";
	}
	/**
	 * 修改密码
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/upPwd", method = RequestMethod.GET)
	public String upPwd(HttpServletRequest request, HttpServletResponse response, Model model) {
		Object userObject = request.getSession().getAttribute(SessionName.ADMIN_USER);
		if(userObject == null){
			//去首页
		}
		model.addAttribute("left_nav", "upPwd");//左导航
		return "/public/upPwd";
	}
	/**
	 * 联系人信息管理
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/cPeople", method = RequestMethod.GET)
	public String cPeople(HttpServletRequest request, HttpServletResponse response, Model model) {
		ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
		HCompany tmp = new HCompany();
		tmp.setUser_id(adminUser.getAdminId());
		List<HCompany> tmplist = hcompanyService.getHCompanyBaseList(tmp);
		if(tmplist!=null && tmplist.size() != 0){
			model.addAttribute("contact_name", tmplist.get(0).getContact_name());
			model.addAttribute("contact_phone", tmplist.get(0).getContact_phone());
		}
		
		model.addAttribute("left_nav", "cPeople");//左导航
		return "/public/cPeople";
	}
	/**
	 * 企业信息管理-基本信息
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/qyInfo", method = RequestMethod.GET)
	public String qyInfo(HttpServletRequest request, HttpServletResponse response, Model model) {
		ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
		HCompany tmp = new HCompany();
		tmp.setUser_id(adminUser.getAdminId());
		List<HCompany> tmplist = hcompanyService.getHCompanyBaseList(tmp);
		if(tmplist!=null && tmplist.size() != 0){
			model.addAttribute("company", tmplist.get(0));
		}
		
		model.addAttribute("left_nav", "qyInfo1");//左导航
		return "/public/qyInfo";
	}
	/**
	 * 企业信息管理-财务
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/caiwu", method = RequestMethod.GET)
	public String caiwu(HttpServletRequest request, HttpServletResponse response, Model model) {
		ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
		HCompany tmp = new HCompany();
		tmp.setUser_id(adminUser.getAdminId());
		List<HCompany> tmplist = hcompanyService.getHCompanyBaseList(tmp);
		if(tmplist!=null && tmplist.size() != 0){
			model.addAttribute("company", tmplist.get(0));
		}
		
		model.addAttribute("left_nav", "qyInfo1");//左导航
		return "/public/caiwu";
	}
	/**
	 * 变更联系人信息验证码
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/cPeople_img")
	public void cPeople_img(HttpServletRequest request, HttpServletResponse response) throws IOException {
		switch (random.nextInt(5)) {
		case 0:
			ManageAdminUserController.cs.setFilterFactory(new CurvesRippleFilterFactory(ManageAdminUserController.cs.getColorFactory()));
			break;
		case 1:
			ManageAdminUserController.cs.setFilterFactory(new MarbleRippleFilterFactory());
			break;
		case 2:
			ManageAdminUserController.cs.setFilterFactory(new DoubleRippleFilterFactory());
			break;
		case 3:
			ManageAdminUserController.cs.setFilterFactory(new WobbleRippleFilterFactory());
			break;
		case 4:
			ManageAdminUserController.cs.setFilterFactory(new DiffuseRippleFilterFactory());
			break;
		}
		HttpSession session = request.getSession(false);
		if (session == null) {
			session = request.getSession();
		}
		setResponseHeaders(response);
		String token = EncoderHelper.getChallangeAndWriteImage(ManageAdminUserController.cs, "png", response.getOutputStream());
		session.setAttribute("user_cPeople", token);
	}
	@RequestMapping(value = "/hCompany/modify1", method = RequestMethod.POST)
	public String modify1(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HCompany hcompany = new HCompany();
			ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			if(adminUser!=null){
				String name = RequestHandler.getString(request, "name");
				hcompany.setName(name);
				
				hcompany.setUser_id(adminUser.getAdminId());
				
//				String contact_name = RequestHandler.getString(request, "contact_name");
//				hcompany.setContact_name(contact_name);
//				
				String contact_phone = RequestHandler.getString(request, "contact_phone");
				hcompany.setContact_phone(contact_phone);
				hcompany.setContact_name(contact_phone);
				String contact_mail = RequestHandler.getString(request, "contact_mail");
				hcompany.setContact_mail(contact_mail);
				
				String fp_name = RequestHandler.getString(request, "fp_name");
				hcompany.setFp_name(fp_name);
				
				String fp_phone = RequestHandler.getString(request, "fp_phone");
				hcompany.setFp_phone(fp_phone);
				
				String fp_telephone = RequestHandler.getString(request, "fp_telephone");
				hcompany.setFp_telephone(fp_telephone);
				
				String fp_address = RequestHandler.getString(request, "fp_address");
				hcompany.setFp_address(fp_address);
				
				String fax = RequestHandler.getString(request, "fax");
				hcompany.setFax(fax);
				
				String fax_ext = RequestHandler.getString(request, "fax_ext");
				hcompany.setFax_ext(fax_ext);
				
				Integer ywyId = RequestHandler.getInteger(request, "ywyId");
				hcompany.setYwyId(ywyId);
				
				Integer status = RequestHandler.getInteger(request, "status");
				hcompany.setStatus(status);
				
				Date create_time = RequestHandler.getDate(request, "create_time");
				hcompany.setCreate_time(create_time);
				
				String verify_reason = RequestHandler.getString(request, "verify_reason");
				hcompany.setVerify_reason(verify_reason);
				
				Integer verify_user_id = RequestHandler.getInteger(request, "verify_user_id");
				hcompany.setVerify_user_id(verify_user_id);
				
				String com_bank_code = RequestHandler.getString(request, "com_bank_code");
				hcompany.setCom_bank_code(com_bank_code);
				
				Integer verify_status = RequestHandler.getInteger(request, "verify_status");
				hcompany.setVerify_status(verify_status);
				
				String com_license_no = RequestHandler.getString(request, "com_license_no");
				hcompany.setCom_license_no(com_license_no);
				
				String com_license_img = RequestHandler.getString(request, "com_license_img");
				hcompany.setCom_license_img(com_license_img);
				
				String com_tax_no = RequestHandler.getString(request, "com_tax_no");
				hcompany.setCom_tax_no(com_tax_no);
				
				String com_tax_img = RequestHandler.getString(request, "com_tax_img");
				hcompany.setCom_tax_img(com_tax_img);
				
				String com_dept_code = RequestHandler.getString(request, "com_dept_code");
				hcompany.setCom_dept_code(com_dept_code);
				
				String com_dept_img = RequestHandler.getString(request, "com_dept_img");
				hcompany.setCom_dept_img(com_dept_img);
				
				String com_duty_no = RequestHandler.getString(request, "com_duty_no");
				hcompany.setCom_duty_no(com_duty_no);
				
				String com_bank_name = RequestHandler.getString(request, "com_bank_name");
				hcompany.setCom_bank_name(com_bank_name);
				
				String com_account_name = RequestHandler.getString(request, "com_account_name");
				hcompany.setCom_account_name(com_account_name);
				
				String com_account_no = RequestHandler.getString(request, "com_account_no");
				hcompany.setCom_account_no(com_account_no);
				
				String remark1 = RequestHandler.getString(request, "remark1");
				hcompany.setRemark1(remark1);
				
				String remark2 = RequestHandler.getString(request, "remark2");
				hcompany.setRemark2(remark2);
				
				String remark3 = RequestHandler.getString(request, "remark3");
				hcompany.setRemark3(remark3);
				
				String remark4 = RequestHandler.getString(request, "remark4");
				hcompany.setRemark4(remark4);
				
				String remark5 = RequestHandler.getString(request, "remark5");
				hcompany.setRemark5(remark5);
				
				String remark6 = RequestHandler.getString(request, "remark6");
				hcompany.setRemark6(remark6);
				
				String validate_code = RequestHandler.getString(request, "validate_code");//图片验证码
				String user_cPeople_code =  (String) request.getSession().getAttribute(SessionName.TOKEN);//图片验证码
				
				String phone_code = RequestHandler.getString(request, "phone_code");//手机验证码
				String sysPhoneCode =  SendMsgUtil.CODEMAP.get(contact_phone);//手机验证码
				
				Integer com_business_doc_type = RequestHandler.getInteger(request, "com_business_doc_type");
				hcompany.setCom_business_doc_type(com_business_doc_type);
				
				if(StringUtils.isBlank(validate_code) || !validate_code.equals(user_cPeople_code)){//图片验证码错误
					writeErrorMsg("右侧验证码错误", null, response);
					return null;
				}
				if(StringUtils.isBlank(phone_code) || !phone_code.equals(sysPhoneCode)){//手机验证码错误
					writeErrorMsg("手机验证码错误", null, response);
					return null;
				}
				
				HCompany tmp = new HCompany();
				tmp.setUser_id(adminUser.getAdminId());
				List<HCompany> tmplist = hcompanyService.getHCompanyBaseList(tmp);
				if(tmplist!=null&&tmplist.size()>0){
					SendMsgUtil.CODEMAP.remove(contact_phone);
					SendMsgUtil.TIMEMAP.remove(contact_phone);
					
					tmp = tmplist.get(0);
					hcompany.setId(tmp.getId());
					hcompanyService.updateHCompany(hcompany);
					
					ManageAdminUser manageAdminUser = new ManageAdminUser();
					manageAdminUser.setAdminId(adminUser.getAdminId());
					manageAdminUser.setMobile(contact_phone);
					manageAdminUser.setAdminName(contact_phone);
					manageAdminUser.setPasswd(MD5.getMD5ofStr(contact_phone.substring(contact_phone.length()-6, contact_phone.length())));
					manageadminuserService.updateManageAdminUser(manageAdminUser);
					
					writeSuccessMsg("修改成功", null, response);
				}
			}else{
				writeErrorMsg("请先登录", null, response);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/hCompany/modify2", method = RequestMethod.POST)
	public String modify2(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HCompany hcompany = new HCompany();
			ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			if(adminUser!=null){
				String name = RequestHandler.getString(request, "name");
				hcompany.setName(name);
				
				hcompany.setUser_id(adminUser.getAdminId());
				
				String contact_name = RequestHandler.getString(request, "contact_name");
				hcompany.setContact_name(contact_name);
				
				String contact_phone = RequestHandler.getString(request, "contact_phone");
				hcompany.setContact_phone(contact_phone);
				
				String contact_mail = RequestHandler.getString(request, "contact_mail");
				hcompany.setContact_mail(contact_mail);
				
				String fp_name = RequestHandler.getString(request, "fp_name");
				hcompany.setFp_name(fp_name);
				
				String fp_phone = RequestHandler.getString(request, "fp_phone");
				hcompany.setFp_phone(fp_phone);
				
				String fp_telephone = RequestHandler.getString(request, "fp_telephone");
				hcompany.setFp_telephone(fp_telephone);
				
				String fp_address = RequestHandler.getString(request, "fp_address");
				hcompany.setFp_address(fp_address);
				
				String fax = RequestHandler.getString(request, "fax");
				hcompany.setFax(fax);
				
				String fax_ext = RequestHandler.getString(request, "fax_ext");
				hcompany.setFax_ext(fax_ext);
				
				Integer ywyId = RequestHandler.getInteger(request, "ywyId");
				hcompany.setYwyId(ywyId);
				
				Integer status = RequestHandler.getInteger(request, "status");
				hcompany.setStatus(status);
				
				Date create_time = RequestHandler.getDate(request, "create_time");
				hcompany.setCreate_time(create_time);
				
				String verify_reason = RequestHandler.getString(request, "verify_reason");
				hcompany.setVerify_reason(verify_reason);
				
				Integer verify_user_id = RequestHandler.getInteger(request, "verify_user_id");
				hcompany.setVerify_user_id(verify_user_id);
				
				String com_bank_code = RequestHandler.getString(request, "com_bank_code");
				hcompany.setCom_bank_code(com_bank_code);
				
				Integer verify_status = RequestHandler.getInteger(request, "verify_status");
				hcompany.setVerify_status(verify_status);
				
				String com_license_no = RequestHandler.getString(request, "com_license_no");
				hcompany.setCom_license_no(com_license_no);
				
				String com_license_img = RequestHandler.getString(request, "com_license_img");
				hcompany.setCom_license_img(com_license_img);
				
				String com_tax_no = RequestHandler.getString(request, "com_tax_no");
				hcompany.setCom_tax_no(com_tax_no);
				
				String com_tax_img = RequestHandler.getString(request, "com_tax_img");
				hcompany.setCom_tax_img(com_tax_img);
				
				String com_dept_code = RequestHandler.getString(request, "com_dept_code");
				hcompany.setCom_dept_code(com_dept_code);
				
				String com_dept_img = RequestHandler.getString(request, "com_dept_img");
				hcompany.setCom_dept_img(com_dept_img);
				
				String com_duty_no = RequestHandler.getString(request, "com_duty_no");
				hcompany.setCom_duty_no(com_duty_no);
				
				String com_bank_name = RequestHandler.getString(request, "com_bank_name");
				hcompany.setCom_bank_name(com_bank_name);
				
				String com_account_name = RequestHandler.getString(request, "com_account_name");
				hcompany.setCom_account_name(com_account_name);
				
				String com_account_no = RequestHandler.getString(request, "com_account_no");
				hcompany.setCom_account_no(com_account_no);
				
				String remark1 = RequestHandler.getString(request, "remark1");
				hcompany.setRemark1(remark1);
				
				String remark2 = RequestHandler.getString(request, "remark2");
				hcompany.setRemark2(remark2);
				
				String remark3 = RequestHandler.getString(request, "remark3");
				hcompany.setRemark3(remark3);
				
				String remark4 = RequestHandler.getString(request, "remark4");
				hcompany.setRemark4(remark4);
				
				String remark5 = RequestHandler.getString(request, "remark5");
				hcompany.setRemark5(remark5);
				
				String remark6 = RequestHandler.getString(request, "remark6");
				hcompany.setRemark6(remark6);
				
				String validate_code = RequestHandler.getString(request, "validate_code");//图片验证码
				String user_cPeople_code =  (String) request.getSession().getAttribute(SessionName.TOKEN);//图片验证码
				
				String phone_code = RequestHandler.getString(request, "phone_code");//手机验证码
				String sysPhoneCode =  SendMsgUtil.CODEMAP.get(contact_phone);//手机验证码
				
				Integer com_business_doc_type = RequestHandler.getInteger(request, "com_business_doc_type");
				hcompany.setCom_business_doc_type(com_business_doc_type);
				
				if(StringUtils.isBlank(validate_code) || !validate_code.equals(user_cPeople_code)){//图片验证码错误
					writeErrorMsg("右侧验证码错误", null, response);
					return null;
				}
				if(StringUtils.isBlank(phone_code) || !phone_code.equals(sysPhoneCode)){//手机验证码错误
					writeErrorMsg("手机验证码错误", null, response);
					return null;
				}
				
				HCompany tmp = new HCompany();
				tmp.setUser_id(adminUser.getAdminId());
				List<HCompany> tmplist = hcompanyService.getHCompanyBaseList(tmp);
				if(tmplist!=null&&tmplist.size()>0){
					SendMsgUtil.CODEMAP.remove(contact_phone);
					SendMsgUtil.TIMEMAP.remove(contact_phone);
					
					tmp = tmplist.get(0);
					hcompany.setId(tmp.getId());
					hcompanyService.updateHCompany(hcompany);
					
					ManageAdminUser manageAdminUser = new ManageAdminUser();
					manageAdminUser.setAdminId(adminUser.getAdminId());
					manageAdminUser.setMobile(contact_phone);
					manageAdminUser.setAdminName(contact_phone);
					manageAdminUser.setRealName(contact_name);
					manageAdminUser.setPasswd(MD5.getMD5ofStr(contact_phone.substring(contact_phone.length()-6, contact_phone.length())));
					manageadminuserService.updateManageAdminUser(manageAdminUser);
					
					writeSuccessMsg("修改成功", null, response);
				}
			}else{
				writeErrorMsg("请先登录", null, response);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	/** 发验证码 **/
	@RequestMapping(value = "/ePeople/vercode", method = RequestMethod.POST)
	public String vercode(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception
	{
		String phone =  RequestHandler.getString(request, "phone");
		if(StringUtils.isNotBlank(phone)){
			HCompany tmp = new HCompany();
			tmp.setContact_phone(phone);
			List<HCompany> list = hcompanyService.getHCompanyBaseList(tmp);
			boolean b = true;
			if(list!=null&&list.size()>0){
				ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
				ManageAdminUser user = new ManageAdminUser();
				user.setAdminName(phone);
				user.setAdminId(adminUser.getAdminId());
				int count = manageadminuserService.checkAdminName(user);
				if(count>0){
					b = false;
					writeErrorMsg("手机号码已存在！", "", response);
				}else{
					tmp = new HCompany();
					tmp.setUser_id(adminUser.getAdminId());
					List<HCompany> tmplist = hcompanyService.getHCompanyBaseList(tmp);
					if(tmplist!=null && tmplist.size() != 0){
						if(tmplist.get(0).getContact_phone()!=null && tmplist.get(0).getContact_phone().equals(phone)){
							//可更新 
						}else{
							b = false;
							writeErrorMsg("手机号码已存在！", "", response);
						}
					}else{
						//可更新
					}
				}
			}else{
				//可更新 
			}
			if(b){
				String randomCode = SendMsgUtil.getRandomCode(4);
				String content = "变更联系人信息，手机验证码为：" + randomCode;
//				System.out.println(content+"=="+phone);
				int s = SendMsgUtil.sendMsg(phone,content);
//				int s = 1;
				if(s > 0){
					SendMsgUtil.TIMEMAP.put(phone, new Date());
					SendMsgUtil.CODEMAP.put(phone, randomCode);
					writeSuccessMsg("发送成功", null, response);
				}else{
					writeErrorMsg("发送失败", "", response);
				}
			}
		}else{
			writeErrorMsg("发送失败", "", response);
		}
		
		return null;
	}
	private void setResponseHeaders(HttpServletResponse response) {
		response.setContentType("image/png");
		response.setHeader("Cache-Control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		long time = System.currentTimeMillis();
		response.setDateHeader("Last-Modified", time);
		response.setDateHeader("Date", time);
		response.setDateHeader("Expires", time);
	}
	@RequestMapping(value = "/save_upPwd", method = RequestMethod.POST)
	public void save_upPwd(HttpServletRequest request, HttpServletResponse response, Model model
			,String oldpwd,String re_newpwd,String newpwd) {
		Object userObject = request.getSession().getAttribute(SessionName.ADMIN_USER);
		if(userObject == null){
			//去首页
		}
		if(StringUtils.isBlank(oldpwd) || StringUtils.isBlank(newpwd) || StringUtils.isBlank(re_newpwd)){
			writeSuccessMsg("-1", null, response);//密码不能为空
			return ;
		}
		if(!newpwd.equals(re_newpwd)){
			writeSuccessMsg("-2", null, response);//两次密码不一致
			return ;
		}
		
		ManageAdminUser adminUser = (ManageAdminUser)userObject;
		ManageAdminUser manageAdminUser = new ManageAdminUser();
		manageAdminUser.setAdminId(adminUser.getAdminId());
		ManageAdminUser manageAdminUser1 = manageadminuserService.getManageAdminUser(manageAdminUser);
		if(!manageAdminUser1.getPasswd().equals(MD5.getMD5ofStr(oldpwd))){
			writeSuccessMsg("-3", null, response);//原密码错误
			return ;
		}
		
		manageAdminUser1.setPasswd(MD5.getMD5ofStr(newpwd));
		manageAdminUser1.setPwdModifyTime(new Date());
		try {
			manageadminuserService.updateManageAdminUser(manageAdminUser1);
			ManageAdminUser u = new ManageAdminUser();
			u.setAdminId(adminUser.getAdminId());
			ManageAdminUser manageAdminUser2 = manageadminuserService.getManageAdminUser(u);
			request.getSession().setAttribute(SessionName.ADMIN_USER, manageAdminUser2);
			writeSuccessMsg("1", null, response);
			return ;
		} catch (Exception e) {
			e.printStackTrace();
			writeErrorMsg("修改密码失败", null, response);
		}
	}
	@RequestMapping(value = "/upBillImg", method = RequestMethod.POST)
	public String upBillImg(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HAmmeterInfo hammeterinfo = new HAmmeterInfo();
			
			Integer a_id = RequestHandler.getInteger(request, "aid");
			hammeterinfo.setA_id(a_id);
			String bill_img = RequestHandler.getString(request, "bill_img");
			hammeterinfo.setBill_img(bill_img);
			hammeterinfoService.updateHAmmeterInfo(hammeterinfo);
			writeSuccessMsg("上传成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/showBillImg", method = RequestMethod.POST)
	public String showBillImg(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HAmmeterInfo hammeterinfo = new HAmmeterInfo();
			
			Integer a_id = RequestHandler.getInteger(request, "aid");
			hammeterinfo.setA_id(a_id);
			hammeterinfo = hammeterinfoService.getHAmmeterInfo(hammeterinfo);
			writeSuccessMsg("成功", hammeterinfo, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/mAmmeter", method = RequestMethod.GET)
	public String mAmmeter(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		model.addAttribute("left_nav", "mAmmeter");//左导航
		return "/public/mAmmeter";
	}
}
