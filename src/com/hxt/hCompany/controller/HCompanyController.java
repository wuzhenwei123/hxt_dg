package com.hxt.hCompany.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.base.controller.BaseController;
import com.base.utils.FileUploadConstants;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.utils.SendMsgUtil;
import com.base.utils.SessionName;
import com.hxt.hAgent.model.HAgent;
import com.hxt.hAgent.service.HAgentService;
import com.hxt.hAmmeterInfo.model.HAmmeterInfo;
import com.hxt.hAmmeterInfo.service.HAmmeterInfoService;
import com.hxt.hCommon.service.HCommonService;
import com.hxt.hCompany.model.HCompany;
import com.hxt.hCompany.service.HCompanyService;
import com.hxt.hPayOrder.model.HPayOrder;
import com.hxt.hPayOrder.service.HPayOrderService;
import com.hxt.hReviewUser.model.HReviewUser;
import com.hxt.hReviewUser.service.HReviewUserService;
import com.hxt.hSubCompany.model.HSubCompany;
import com.hxt.hSubCompany.service.HSubCompanyService;
import com.hxt.hUserAccount.service.HUserAccountService;
import com.sys.adminUserRole.model.AdminUserRole;
import com.sys.adminUserRole.service.AdminUserRoleService;
import com.sys.manageAdminUser.model.ManageAdminUser;
import com.sys.manageAdminUser.service.ManageAdminUserService;
/**
 * @author	yy
 * @time	2015年11月17日 23:59:31
 */
@Controller
@RequestMapping("/hCompany")
public class HCompanyController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HCompanyController.class); //Logger
	
	@Autowired
	private HCompanyService hcompanyService = null;
	@Autowired
	private ManageAdminUserService manageAdminUserService = null;
	@Autowired
	private AdminUserRoleService adminuserroleService = null;
	@Autowired
	private HCommonService hCommonService = null;
	@Autowired
	private HAmmeterInfoService hAmmeterInfoService = null;
	@Autowired
	private HSubCompanyService hSubCompanyService = null;
	@Autowired
	private HAgentService hagentservice = null;
	@Autowired
	private HAmmeterInfoService hammeterinfoService = null;
	@Autowired
	private HUserAccountService huseraccountService = null;
	@Autowired
	private HReviewUserService hreviewuserService = null;
	@Autowired
	private HPayOrderService hpayorderService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
		if(adminUser.getRoleId().equals(Integer.valueOf(FileUploadConstants.YWY_ROLE_ID))){
			model.addAttribute("ywyadmin", adminUser.getAdminId());
		}
		ManageAdminUser loginUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
		boolean agentFlag = false;
		if(loginUser.getRoleId().toString().equals(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID)){
			model.addAttribute("oneName", loginUser.getOneAgentName());
			model.addAttribute("oneId", loginUser.getOneAgentOpenId());
			agentFlag = true;
		}else if(loginUser.getRoleId().toString().equals(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID)){
			model.addAttribute("oneName", loginUser.getOneAgentName());
			model.addAttribute("oneId", loginUser.getOneAgentOpenId());
			model.addAttribute("twoName", loginUser.getTwoAgentName());
			model.addAttribute("twoId", loginUser.getTwoAgentOpenId());
			agentFlag = true;
		}else if(loginUser.getRoleId().toString().equals(FileUploadConstants.SERVICER_MANAGE_ROLEID)){
			model.addAttribute("servicerName", loginUser.getRealName());
			model.addAttribute("servicerId", loginUser.getAdminId());
			agentFlag = true;
		}
		model.addAttribute("agentFlag", agentFlag);
		model.addAttribute("verifyStatus", 1);
		model.addAttribute("twoAgentRoleId", FileUploadConstants.TWO_AGENT_MANAGE_ROLEID);
		return "/hCompany/hCompanyIndex";
	}
	@RequestMapping(value = "/index2", method = RequestMethod.GET)
	public String init2(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
		if(adminUser.getRoleId().equals(Integer.valueOf(FileUploadConstants.YWY_ROLE_ID))){
			model.addAttribute("ywyadmin", adminUser.getAdminId());
		}
		model.addAttribute("verifyStatus", 4);
		return "/hCompany/hCompanyIndex2";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hCompany/hCompanyAdd";
	}
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HCompany hcompany1 = new HCompany();
		hcompany1.setId(id);
		HCompany hcompany = hcompanyService.getHCompany(hcompany1);
		model.addAttribute("hcompany", hcompany);
		
		return "/hCompany/hCompanyUpdate";
	}
	
	@RequestMapping(value = "/showDetail/{id}", method = RequestMethod.GET)
	public String showDetail(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HCompany hcompany1 = new HCompany();
		hcompany1.setId(id);
		HCompany hcompany = hcompanyService.getHCompany(hcompany1);
		model.addAttribute("hcompany", hcompany);
		
		return "/hCompany/showDetail";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHCompanyList", method = RequestMethod.GET)
	public String getHCompanyList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HCompany hcompany = new HCompany();
			//角色条件处理
			ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			if(adminUser.getRoleId().toString().equals(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID)){
				hcompany.setOneAgentOpenId(adminUser.getOneAgentOpenId());
			}else if(adminUser.getRoleId().toString().equals(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID)){
				hcompany.setTwoAgentOpenID(adminUser.getTwoAgentOpenId());
			}else if(adminUser.getRoleId().toString().equals(FileUploadConstants.SERVICER_MANAGE_ROLEID)){
				hcompany.setServicerId(adminUser.getServicerId());
			}
			String agentOneOpenId = RequestHandler.getString(request, "agentOneOpenId");
			String agentTwoOpenId = RequestHandler.getString(request, "agentTwoOpenId");
			Integer servicerId = RequestHandler.getInteger(request, "servicerId");
			hcompany.setOneAgentOpenId(agentOneOpenId);
			hcompany.setTwoAgentOpenID(agentTwoOpenId);
			hcompany.setServicerId(servicerId);
			Integer id = RequestHandler.getInteger(request, "id");
			hcompany.setId(id);
			
			hcompany.setStartTime(RequestHandler.getDate(request, "startTime"));
			hcompany.setEndTime(RequestHandler.getDate(request, "endTime"));
			
			String name = RequestHandler.getString(request, "name");
			hcompany.setName(name);
			String rec_phone = RequestHandler.getString(request, "rec_phone");
			hcompany.setRec_phone(rec_phone);
			
			Integer user_id = RequestHandler.getInteger(request, "user_id");
			hcompany.setUser_id(user_id);
			
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
			
			String credit_code = RequestHandler.getString(request, "credit_code");
			hcompany.setCredit_code(credit_code);
			
			Date com_license_start = RequestHandler.getDate(request, "com_license_start");
			hcompany.setCom_license_start(com_license_start);
			
			Date com_license_end = RequestHandler.getDate(request, "com_license_end");
			hcompany.setCom_license_end(com_license_end);
			
			String province_code = RequestHandler.getString(request, "province_code");
			hcompany.setProvince_code(province_code);
			
			String city_code = RequestHandler.getString(request, "city_code");
			hcompany.setCity_code(city_code);
			
			String area_code = RequestHandler.getString(request, "area_code");
			hcompany.setArea_code(area_code);
			
			String com_address = RequestHandler.getString(request, "com_address");
			hcompany.setCom_address(com_address);
			
			String com_zip_code = RequestHandler.getString(request, "com_zip_code");
			hcompany.setCom_zip_code(com_zip_code);
			
			String zp_code = RequestHandler.getString(request, "zp_code");
			hcompany.setZp_code(zp_code);
			
			String zp_province_code = RequestHandler.getString(request, "zp_province_code");
			hcompany.setZp_province_code(zp_province_code);
			
			String zp_city_code = RequestHandler.getString(request, "zp_city_code");
			hcompany.setZp_city_code(zp_city_code);
			
			String zp_area_code = RequestHandler.getString(request, "zp_area_code");
			hcompany.setZp_area_code(zp_area_code);
			
			String zp_address = RequestHandler.getString(request, "zp_address");
			hcompany.setZp_address(zp_address);
			
			String zp_area_number = RequestHandler.getString(request, "zp_area_number");
			hcompany.setZp_area_number(zp_area_number);
			
			String zp_phone = RequestHandler.getString(request, "zp_phone");
			hcompany.setZp_phone(zp_phone);
			
			String zp_bank_code = RequestHandler.getString(request, "zp_bank_code");
			hcompany.setZp_bank_code(zp_bank_code);
			
			String zp_bank_account = RequestHandler.getString(request, "zp_bank_account");
			hcompany.setZp_bank_account(zp_bank_account);
			
			Integer zp_verify_status = RequestHandler.getInteger(request, "zp_verify_status");
			hcompany.setZp_verify_status(zp_verify_status);
			
			Date verify_status_time = RequestHandler.getDate(request, "verify_status_time");
			hcompany.setVerify_status_time(verify_status_time);
			
			Date zp_verify_time = RequestHandler.getDate(request, "zp_verify_time");
			hcompany.setZp_verify_time(zp_verify_time);
			
			Integer zp_verify_creater_id = RequestHandler.getInteger(request, "zp_verify_creater_id");
			hcompany.setZp_verify_creater_id(zp_verify_creater_id);
			
			Date update_time = RequestHandler.getDate(request, "update_time");
			hcompany.setUpdate_time(update_time);
			
			Integer update_operator_id = RequestHandler.getInteger(request, "update_operator_id");
			hcompany.setUpdate_operator_id(update_operator_id);
			Integer com_business_doc_type = RequestHandler.getInteger(request, "com_business_doc_type");
			hcompany.setCom_business_doc_type(com_business_doc_type);
			

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hcompany.setRowStart(from);
			hcompany.setRowCount(rowCount);
			
			Integer rateFlag = RequestHandler.getInteger(request, "rateFlag");
			hcompany.setRateFlag(rateFlag);
			
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hcompany.setSortColumn(sortColumn);
			
			int hcompanyListCount = hcompanyService.getHCompanyListCount(hcompany);
			ResponseList<HCompany> hcompanyList = null;
			if ( hcompanyListCount > 0 )
			{
				hcompanyList = hcompanyService.getHCompanyList(hcompany);
			} else
			{
				hcompanyList = new ResponseList<HCompany>();
			}
			// 设置数据总数
			hcompanyList.setTotalResults(hcompanyListCount);
			
			writeSuccessMsg("ok", hcompanyList, response);
		} catch (Exception e)
		{
			e.printStackTrace();
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/getHCompanyList2", method = RequestMethod.GET)
	public String getHCompanyList2(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HCompany hcompany = new HCompany();
			//角色条件处理
			ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			if(adminUser.getRoleId().toString().equals(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID)){
				hcompany.setOneAgentOpenId(adminUser.getOneAgentOpenId());
			}else if(adminUser.getRoleId().toString().equals(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID)){
				hcompany.setTwoAgentOpenID(adminUser.getTwoAgentOpenId());
			}else if(adminUser.getRoleId().toString().equals(FileUploadConstants.SERVICER_MANAGE_ROLEID)){
				hcompany.setServicerId(adminUser.getServicerId());
			}
			String agentOneOpenId = RequestHandler.getString(request, "agentOneOpenId");
			String agentTwoOpenId = RequestHandler.getString(request, "agentTwoOpenId");
			Integer servicerId = RequestHandler.getInteger(request, "servicerId");
			hcompany.setOneAgentOpenId(agentOneOpenId);
			hcompany.setTwoAgentOpenID(agentTwoOpenId);
			hcompany.setServicerId(servicerId);
			Integer id = RequestHandler.getInteger(request, "id");
			hcompany.setId(id);
			
			String name = RequestHandler.getString(request, "name");
			hcompany.setName(name);
			
			Integer user_id = RequestHandler.getInteger(request, "user_id");
			hcompany.setUser_id(user_id);
			
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
			
			String credit_code = RequestHandler.getString(request, "credit_code");
			hcompany.setCredit_code(credit_code);
			
			Date com_license_start = RequestHandler.getDate(request, "com_license_start");
			hcompany.setCom_license_start(com_license_start);
			
			Date com_license_end = RequestHandler.getDate(request, "com_license_end");
			hcompany.setCom_license_end(com_license_end);
			
			String province_code = RequestHandler.getString(request, "province_code");
			hcompany.setProvince_code(province_code);
			
			String city_code = RequestHandler.getString(request, "city_code");
			hcompany.setCity_code(city_code);
			
			String area_code = RequestHandler.getString(request, "area_code");
			hcompany.setArea_code(area_code);
			
			String com_address = RequestHandler.getString(request, "com_address");
			hcompany.setCom_address(com_address);
			
			String com_zip_code = RequestHandler.getString(request, "com_zip_code");
			hcompany.setCom_zip_code(com_zip_code);
			
			String zp_code = RequestHandler.getString(request, "zp_code");
			hcompany.setZp_code(zp_code);
			
			String zp_province_code = RequestHandler.getString(request, "zp_province_code");
			hcompany.setZp_province_code(zp_province_code);
			
			String zp_city_code = RequestHandler.getString(request, "zp_city_code");
			hcompany.setZp_city_code(zp_city_code);
			
			String zp_area_code = RequestHandler.getString(request, "zp_area_code");
			hcompany.setZp_area_code(zp_area_code);
			
			String zp_address = RequestHandler.getString(request, "zp_address");
			hcompany.setZp_address(zp_address);
			
			String zp_area_number = RequestHandler.getString(request, "zp_area_number");
			hcompany.setZp_area_number(zp_area_number);
			
			String zp_phone = RequestHandler.getString(request, "zp_phone");
			hcompany.setZp_phone(zp_phone);
			
			String zp_bank_code = RequestHandler.getString(request, "zp_bank_code");
			hcompany.setZp_bank_code(zp_bank_code);
			
			String zp_bank_account = RequestHandler.getString(request, "zp_bank_account");
			hcompany.setZp_bank_account(zp_bank_account);
			
			Integer zp_verify_status = RequestHandler.getInteger(request, "zp_verify_status");
			hcompany.setZp_verify_status(zp_verify_status);
			
			Date verify_status_time = RequestHandler.getDate(request, "verify_status_time");
			hcompany.setVerify_status_time(verify_status_time);
			
			Date zp_verify_time = RequestHandler.getDate(request, "zp_verify_time");
			hcompany.setZp_verify_time(zp_verify_time);
			
			Integer zp_verify_creater_id = RequestHandler.getInteger(request, "zp_verify_creater_id");
			hcompany.setZp_verify_creater_id(zp_verify_creater_id);
			
			Integer notid = RequestHandler.getInteger(request, "notid");
			hcompany.setNotid(notid);
			
			Date update_time = RequestHandler.getDate(request, "update_time");
			hcompany.setUpdate_time(update_time);
			
			Integer update_operator_id = RequestHandler.getInteger(request, "update_operator_id");
			hcompany.setUpdate_operator_id(update_operator_id);
			Integer com_business_doc_type = RequestHandler.getInteger(request, "com_business_doc_type");
			hcompany.setCom_business_doc_type(com_business_doc_type);
			
			
			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hcompany.setRowStart(from);
			hcompany.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hcompany.setSortColumn(sortColumn);
			
			int hcompanyListCount = hcompanyService.getHCompanyListCount2(hcompany);
			ResponseList<HCompany> hcompanyList = null;
			if ( hcompanyListCount > 0 )
			{
				hcompanyList = hcompanyService.getHCompanyList2(hcompany);
			} else
			{
				hcompanyList = new ResponseList<HCompany>();
			}
			// 设置数据总数
			hcompanyList.setTotalResults(hcompanyListCount);
			
			writeSuccessMsg("ok", hcompanyList, response);
		} catch (Exception e)
		{
			e.printStackTrace();
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getHCompanyBaseList", method = RequestMethod.GET)
	public String getHCompanyBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HCompany hcompany = new HCompany();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hcompany.setId(id);
			
			String name = RequestHandler.getString(request, "name");
			hcompany.setName(name);
			
			Integer user_id = RequestHandler.getInteger(request, "user_id");
			hcompany.setUser_id(user_id);
			
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
			
			String credit_code = RequestHandler.getString(request, "credit_code");
			hcompany.setCredit_code(credit_code);
			
			Date com_license_start = RequestHandler.getDate(request, "com_license_start");
			hcompany.setCom_license_start(com_license_start);
			
			Date com_license_end = RequestHandler.getDate(request, "com_license_end");
			hcompany.setCom_license_end(com_license_end);
			
			String province_code = RequestHandler.getString(request, "province_code");
			hcompany.setProvince_code(province_code);
			
			String city_code = RequestHandler.getString(request, "city_code");
			hcompany.setCity_code(city_code);
			
			String area_code = RequestHandler.getString(request, "area_code");
			hcompany.setArea_code(area_code);
			
			String com_address = RequestHandler.getString(request, "com_address");
			hcompany.setCom_address(com_address);
			
			String com_zip_code = RequestHandler.getString(request, "com_zip_code");
			hcompany.setCom_zip_code(com_zip_code);
			
			String zp_code = RequestHandler.getString(request, "zp_code");
			hcompany.setZp_code(zp_code);
			
			String zp_province_code = RequestHandler.getString(request, "zp_province_code");
			hcompany.setZp_province_code(zp_province_code);
			
			String zp_city_code = RequestHandler.getString(request, "zp_city_code");
			hcompany.setZp_city_code(zp_city_code);
			
			String zp_area_code = RequestHandler.getString(request, "zp_area_code");
			hcompany.setZp_area_code(zp_area_code);
			
			String zp_address = RequestHandler.getString(request, "zp_address");
			hcompany.setZp_address(zp_address);
			
			String zp_area_number = RequestHandler.getString(request, "zp_area_number");
			hcompany.setZp_area_number(zp_area_number);
			
			String zp_phone = RequestHandler.getString(request, "zp_phone");
			hcompany.setZp_phone(zp_phone);
			
			String zp_bank_code = RequestHandler.getString(request, "zp_bank_code");
			hcompany.setZp_bank_code(zp_bank_code);
			
			String zp_bank_account = RequestHandler.getString(request, "zp_bank_account");
			hcompany.setZp_bank_account(zp_bank_account);
			
			Integer zp_verify_status = RequestHandler.getInteger(request, "zp_verify_status");
			hcompany.setZp_verify_status(zp_verify_status);
			
			Date verify_status_time = RequestHandler.getDate(request, "verify_status_time");
			hcompany.setVerify_status_time(verify_status_time);
			
			Date zp_verify_time = RequestHandler.getDate(request, "zp_verify_time");
			hcompany.setZp_verify_time(zp_verify_time);
			
			Integer zp_verify_creater_id = RequestHandler.getInteger(request, "zp_verify_creater_id");
			hcompany.setZp_verify_creater_id(zp_verify_creater_id);
			
			Date update_time = RequestHandler.getDate(request, "update_time");
			hcompany.setUpdate_time(update_time);
			
			Integer update_operator_id = RequestHandler.getInteger(request, "update_operator_id");
			hcompany.setUpdate_operator_id(update_operator_id);
			
			Integer com_business_doc_type = RequestHandler.getInteger(request, "com_business_doc_type");
			hcompany.setCom_business_doc_type(com_business_doc_type);
			hcompany.setSortColumn(" a.create_time desc ");
			
			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hcompany.setRowStart(from);
			hcompany.setRowCount(rowCount);
			// 分页结束
			
			List<HCompany> hcompanyList = hcompanyService.getHCompanyList1(hcompany);
		
			writeSuccessMsg("ok", hcompanyList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHCompany", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HCompany hcompany = new HCompany();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hcompany.setId(id);
			String name = RequestHandler.getString(request, "name");
			hcompany.setName(name);
			Integer user_id = RequestHandler.getInteger(request, "user_id");
			hcompany.setUser_id(user_id);
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
			hcompany.setStatus(1);
			hcompany.setCreate_time(new Date());
			String verify_reason = RequestHandler.getString(request, "verify_reason");
			hcompany.setVerify_reason(verify_reason);
			Integer verify_user_id = RequestHandler.getInteger(request, "verify_user_id");
			hcompany.setVerify_user_id(verify_user_id);
			String com_bank_code = RequestHandler.getString(request, "com_bank_code");
			hcompany.setCom_bank_code(com_bank_code);
			hcompany.setVerify_status(0);
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
			String credit_code = RequestHandler.getString(request, "credit_code");
			hcompany.setCredit_code(credit_code);
			Date com_license_start = RequestHandler.getDate(request, "com_license_start");
			hcompany.setCom_license_start(com_license_start);
			Date com_license_end = RequestHandler.getDate(request, "com_license_end");
			hcompany.setCom_license_end(com_license_end);
			String province_code = RequestHandler.getString(request, "province_code");
			hcompany.setProvince_code(province_code);
			String city_code = RequestHandler.getString(request, "city_code");
			hcompany.setCity_code(city_code);
			String area_code = RequestHandler.getString(request, "area_code");
			hcompany.setArea_code(area_code);
			String com_address = RequestHandler.getString(request, "com_address");
			hcompany.setCom_address(com_address);
			String com_zip_code = RequestHandler.getString(request, "com_zip_code");
			hcompany.setCom_zip_code(com_zip_code);
			String zp_code = RequestHandler.getString(request, "zp_code");
			hcompany.setZp_code(zp_code);
			String zp_province_code = RequestHandler.getString(request, "zp_province_code");
			hcompany.setZp_province_code(zp_province_code);
			String zp_city_code = RequestHandler.getString(request, "zp_city_code");
			hcompany.setZp_city_code(zp_city_code);
			String zp_area_code = RequestHandler.getString(request, "zp_area_code");
			hcompany.setZp_area_code(zp_area_code);
			String zp_address = RequestHandler.getString(request, "zp_address");
			hcompany.setZp_address(zp_address);
			String zp_area_number = RequestHandler.getString(request, "zp_area_number");
			hcompany.setZp_area_number(zp_area_number);
			String zp_phone = RequestHandler.getString(request, "zp_phone");
			hcompany.setZp_phone(zp_phone);
			String zp_bank_code = RequestHandler.getString(request, "zp_bank_code");
			hcompany.setZp_bank_code(zp_bank_code);
			String zp_bank_account = RequestHandler.getString(request, "zp_bank_account");
			hcompany.setZp_bank_account(zp_bank_account);
			Integer zp_verify_status = RequestHandler.getInteger(request, "zp_verify_status");
			hcompany.setZp_verify_status(zp_verify_status);
			Date verify_status_time = RequestHandler.getDate(request, "verify_status_time");
			hcompany.setVerify_status_time(verify_status_time);
			Date zp_verify_time = RequestHandler.getDate(request, "zp_verify_time");
			hcompany.setZp_verify_time(zp_verify_time);
			Integer zp_verify_creater_id = RequestHandler.getInteger(request, "zp_verify_creater_id");
			hcompany.setZp_verify_creater_id(zp_verify_creater_id);
//			Date update_time = RequestHandler.getDate(request, "update_time");
//			hcompany.setUpdate_time(update_time);
//			Integer update_operator_id = RequestHandler.getInteger(request, "update_operator_id");
//			hcompany.setUpdate_operator_id(update_operator_id);
			Integer com_business_doc_type = RequestHandler.getInteger(request, "com_business_doc_type");
			hcompany.setCom_business_doc_type(com_business_doc_type);
			//传真校验
			if(fax != null){
				HCompany tmp = new HCompany();
				tmp.setFaxFlag(fax);
				List<HCompany> tmplist = hcompanyService.getHCompanyBaseList(tmp);
				if(tmplist!=null&&tmplist.size()>0){
					int asc = 97;
					tmp.setFaxFlag((char)asc+fax);
					tmplist = hcompanyService.getHCompanyBaseList(tmp);
					while(tmplist!=null&&tmplist.size()>0){
						asc++;
						tmp.setFaxFlag((char)asc+fax);
						tmplist = hcompanyService.getHCompanyBaseList(tmp);
					}
					hcompany.setFax((char)asc+fax);
				}
			}
			String vercode = RequestHandler.getString(request, "vercode");
			//手机
			if(hcompanyService.checkPhone(contact_phone)){
				//验证码 
				String sysCode = SendMsgUtil.CODEMAP.get(contact_phone);
				String action = RequestHandler.getString(request, "action");
				
					if(StringUtils.isBlank(action)||StringUtils.isBlank("ammeterNo")){//后台添加
						int result = hcompanyService.saveCompanyForManage(request, hcompanyService, hcompany, hAmmeterInfoService);
						if(result == 1){
							writeSuccessMsg("失败成功", null, response);
						}else{
							writeErrorMsg("添加失败", null, response);
						}
					}else if("reg".equals(action)){//前台注册
						if(StringUtils.isNotBlank(sysCode)&&sysCode.equals(vercode)){//手机验证码校验
							if(checkPhone(hcompany.getContact_phone())){
								int result = hcompanyService.saveCompanyForReg(request, hCommonService, hcompanyService, hAmmeterInfoService, hSubCompanyService, hagentservice, hcompany);
								switch (result){
								case 1:
									writeSuccessMsg("注册成功", null, response);
									break;
								case -1:
									writeErrorMsg("该缴费号已被其他单位绑定，请勿重复操作。", null, response);
									break;
								case -2:
									writeErrorMsg("注册失败:COMPANY_ERROR", null, response);
									break;
								case -3:
									writeErrorMsg("不存在该电表信息", null, response);
									break;
								case 0:
									writeErrorMsg("请填写缴费单号", null, response);
									break;
								case -4:
									writeErrorMsg("注册失败:SUB_ERROR", null, response);
									break;
								default:
									writeErrorMsg("系统异常", null, response);
								}
							}else{
								writeErrorMsg("手机已存在", null, response);
							}
						}else{
							writeErrorMsg("手机验证码不正确", null, response);
						}
					}
			}else{
				writeErrorMsg("该手机号已注册。", null, response);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/wxReg", method = RequestMethod.POST)
	public synchronized String wxReg(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HCompany hcompany = new HCompany();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hcompany.setId(id);
			String name = RequestHandler.getString(request, "name");
			hcompany.setName(name);
			Integer user_id = RequestHandler.getInteger(request, "user_id");
			hcompany.setUser_id(user_id);
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
			hcompany.setStatus(1);
			hcompany.setCreate_time(new Date());
			String verify_reason = RequestHandler.getString(request, "verify_reason");
			hcompany.setVerify_reason(verify_reason);
			Integer verify_user_id = RequestHandler.getInteger(request, "verify_user_id");
			hcompany.setVerify_user_id(verify_user_id);
			String com_bank_code = RequestHandler.getString(request, "com_bank_code");
			hcompany.setCom_bank_code(com_bank_code);
			hcompany.setVerify_status(0);
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
			String credit_code = RequestHandler.getString(request, "credit_code");
			hcompany.setCredit_code(credit_code);
			Date com_license_start = RequestHandler.getDate(request, "com_license_start");
			hcompany.setCom_license_start(com_license_start);
			Date com_license_end = RequestHandler.getDate(request, "com_license_end");
			hcompany.setCom_license_end(com_license_end);
			String province_code = RequestHandler.getString(request, "province_code");
			hcompany.setProvince_code(province_code);
			String city_code = RequestHandler.getString(request, "city_code");
			hcompany.setCity_code(city_code);
			String area_code = RequestHandler.getString(request, "area_code");
			hcompany.setArea_code(area_code);
			String com_address = RequestHandler.getString(request, "com_address");
			hcompany.setCom_address(com_address);
			String com_zip_code = RequestHandler.getString(request, "com_zip_code");
			hcompany.setCom_zip_code(com_zip_code);
			String zp_code = RequestHandler.getString(request, "zp_code");
			hcompany.setZp_code(zp_code);
			String zp_province_code = RequestHandler.getString(request, "zp_province_code");
			hcompany.setZp_province_code(zp_province_code);
			String zp_city_code = RequestHandler.getString(request, "zp_city_code");
			hcompany.setZp_city_code(zp_city_code);
			String zp_area_code = RequestHandler.getString(request, "zp_area_code");
			hcompany.setZp_area_code(zp_area_code);
			String zp_address = RequestHandler.getString(request, "zp_address");
			hcompany.setZp_address(zp_address);
			String zp_area_number = RequestHandler.getString(request, "zp_area_number");
			hcompany.setZp_area_number(zp_area_number);
			String zp_phone = RequestHandler.getString(request, "zp_phone");
			hcompany.setZp_phone(zp_phone);
			String zp_bank_code = RequestHandler.getString(request, "zp_bank_code");
			hcompany.setZp_bank_code(zp_bank_code);
			String zp_bank_account = RequestHandler.getString(request, "zp_bank_account");
			hcompany.setZp_bank_account(zp_bank_account);
			Integer zp_verify_status = RequestHandler.getInteger(request, "zp_verify_status");
			hcompany.setZp_verify_status(zp_verify_status);
			Date verify_status_time = RequestHandler.getDate(request, "verify_status_time");
			hcompany.setVerify_status_time(verify_status_time);
			Date zp_verify_time = RequestHandler.getDate(request, "zp_verify_time");
			hcompany.setZp_verify_time(zp_verify_time);
			Integer zp_verify_creater_id = RequestHandler.getInteger(request, "zp_verify_creater_id");
			hcompany.setZp_verify_creater_id(zp_verify_creater_id);
//			Date update_time = RequestHandler.getDate(request, "update_time");
//			hcompany.setUpdate_time(update_time);
//			Integer update_operator_id = RequestHandler.getInteger(request, "update_operator_id");
//			hcompany.setUpdate_operator_id(update_operator_id);
			Integer com_business_doc_type = RequestHandler.getInteger(request, "com_business_doc_type");
			hcompany.setCom_business_doc_type(com_business_doc_type);
			//传真校验
			if(fax != null){
				HCompany tmp = new HCompany();
				tmp.setFaxFlag(fax);
				List<HCompany> tmplist = hcompanyService.getHCompanyBaseList(tmp);
				if(tmplist!=null&&tmplist.size()>0){
					int asc = 97;
					tmp.setFaxFlag((char)asc+fax);
					tmplist = hcompanyService.getHCompanyBaseList(tmp);
					while(tmplist!=null&&tmplist.size()>0){
						asc++;
						tmp.setFaxFlag((char)asc+fax);
						tmplist = hcompanyService.getHCompanyBaseList(tmp);
					}
					hcompany.setFax((char)asc+fax);
				}
			}
			String vercode = RequestHandler.getString(request, "vercode");
			//手机
			if(hcompanyService.checkPhone(contact_phone)){
				//验证码 
				String sysCode = SendMsgUtil.CODEMAP.get(contact_phone);
				String action = RequestHandler.getString(request, "action");
				
				if(StringUtils.isBlank(action)||StringUtils.isBlank("ammeterNo")){//后台添加
					int result = hcompanyService.saveCompanyForManage(request, hcompanyService, hcompany, hAmmeterInfoService);
					if(result == 1){
						writeSuccessMsg("失败成功", null, response);
					}else{
						writeErrorMsg("添加失败", null, response);
					}
				}else if("reg".equals(action)){//前台注册
//					if(StringUtils.isNotBlank(sysCode)&&sysCode.equals(vercode)){//手机验证码校验
						if(checkPhone(hcompany.getContact_phone())){
							int result = hcompanyService.saveCompanyForReg(request, hCommonService, hcompanyService, hAmmeterInfoService, hSubCompanyService, hagentservice, hcompany);
							switch (result){
							case 1:
								writeSuccessMsg("注册成功", null, response);
								break;
							case -1:
								writeErrorMsg("该缴费号已被其他单位绑定，请勿重复操作。", null, response);
								break;
							case -2:
								writeErrorMsg("注册失败:COMPANY_ERROR", null, response);
								break;
							case -3:
								writeErrorMsg("不存在该电表信息", null, response);
								break;
							case 0:
								writeErrorMsg("请填写缴费单号", null, response);
								break;
							case -4:
								writeErrorMsg("注册失败:SUB_ERROR", null, response);
								break;
							default:
								writeErrorMsg("系统异常", null, response);
							}
						}else{
							writeErrorMsg("手机已存在", null, response);
						}
//					}else{
//						writeErrorMsg("手机验证码不正确", null, response);
//					}
				}
			}else{
				writeErrorMsg("手机号码已存在", null, response);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHCompany", method = RequestMethod.POST)
	public String updateHCompany(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HCompany hcompany = new HCompany();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hcompany.setId(id);
			
			String name = RequestHandler.getString(request, "name");
			hcompany.setName(name);
			
			Integer user_id = RequestHandler.getInteger(request, "user_id");
			hcompany.setUser_id(user_id);
			
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
			
			String vercode = RequestHandler.getString(request, "vercode");
			
			String credit_code = RequestHandler.getString(request, "credit_code");
			hcompany.setCredit_code(credit_code);
			
			Date com_license_start = RequestHandler.getDate(request, "com_license_start");
			hcompany.setCom_license_start(com_license_start);
			
			Date com_license_end = RequestHandler.getDate(request, "com_license_end");
			hcompany.setCom_license_end(com_license_end);
			
			String province_code = RequestHandler.getString(request, "province_code");
			hcompany.setProvince_code(province_code);
			
			String city_code = RequestHandler.getString(request, "city_code");
			hcompany.setCity_code(city_code);
			
			String area_code = RequestHandler.getString(request, "area_code");
			hcompany.setArea_code(area_code);
			
			String com_address = RequestHandler.getString(request, "com_address");
			hcompany.setCom_address(com_address);
			
			String com_zip_code = RequestHandler.getString(request, "com_zip_code");
			hcompany.setCom_zip_code(com_zip_code);
			
			String zp_code = RequestHandler.getString(request, "zp_code");
			hcompany.setZp_code(zp_code);
			
			String zp_province_code = RequestHandler.getString(request, "zp_province_code");
			hcompany.setZp_province_code(zp_province_code);
			
			String zp_city_code = RequestHandler.getString(request, "zp_city_code");
			hcompany.setZp_city_code(zp_city_code);
			
			String zp_area_code = RequestHandler.getString(request, "zp_area_code");
			hcompany.setZp_area_code(zp_area_code);
			
			String zp_address = RequestHandler.getString(request, "zp_address");
			hcompany.setZp_address(zp_address);
			
			String zp_area_number = RequestHandler.getString(request, "zp_area_number");
			hcompany.setZp_area_number(zp_area_number);
			
			String zp_phone = RequestHandler.getString(request, "zp_phone");
			hcompany.setZp_phone(zp_phone);
			
			String zp_bank_code = RequestHandler.getString(request, "zp_bank_code");
			hcompany.setZp_bank_code(zp_bank_code);
			
			String zp_bank_account = RequestHandler.getString(request, "zp_bank_account");
			hcompany.setZp_bank_account(zp_bank_account);
			
			Integer zp_verify_status = RequestHandler.getInteger(request, "zp_verify_status");
			hcompany.setZp_verify_status(zp_verify_status);
			
			Date verify_status_time = RequestHandler.getDate(request, "verify_status_time");
			hcompany.setVerify_status_time(verify_status_time);
			
			Date zp_verify_time = RequestHandler.getDate(request, "zp_verify_time");
			hcompany.setZp_verify_time(zp_verify_time);
			
			Integer zp_verify_creater_id = RequestHandler.getInteger(request, "zp_verify_creater_id");
			hcompany.setZp_verify_creater_id(zp_verify_creater_id);
			
			Date update_time = RequestHandler.getDate(request, "update_time");
			hcompany.setUpdate_time(update_time);
			
			Integer update_operator_id = RequestHandler.getInteger(request, "update_operator_id");
			hcompany.setUpdate_operator_id(update_operator_id);
			
			Integer com_business_doc_type = RequestHandler.getInteger(request, "com_business_doc_type");
			hcompany.setCom_business_doc_type(com_business_doc_type);
			//代理处理
			String agentOneOpenId = RequestHandler.getString(request, "agentOneOpenId");
			String agentOneName = RequestHandler.getString(request, "agentOneName");
			String agentTwoOpenId = RequestHandler.getString(request, "agentTwoOpenId");
			String agentTwoName = RequestHandler.getString(request, "agentTwoName");
			Integer servicerId = RequestHandler.getInteger(request, "servicerId");
			String servicerName = RequestHandler.getString(request, "servicerName");
			hcompany.setOneAgentOpenId(agentOneOpenId);
			hcompany.setTwoAgentOpenID(agentTwoOpenId);
			hcompany.setServicerId(servicerId);
			hcompany.setOneAgentName(agentOneName);
			hcompany.setTwoAgentName(agentTwoName);
			hcompany.setServicerName(servicerName);
			
			hcompanyService.updateHCompany2(hcompany);
			
			HCompany tmp = new HCompany();
			tmp.setId(id);
			List<HCompany> tmplist = hcompanyService.getHCompanyBaseList(tmp);
			if(tmplist!=null&&tmplist.size()>0){
				tmp = tmplist.get(0);
//				if(tmp.getContact_phone()!=null&&!tmp.getContact_phone().equals(contact_phone)){
					//验证码
//					String sysCode = SendMsgUtil.CODEMAP.get(contact_phone);
//					if(StringUtils.isNotBlank(vercode)&&StringUtils.isNotBlank(sysCode)&&vercode.equals(sysCode)){
						
						//处理用户 更新用户名、代理id
						ManageAdminUser user = new ManageAdminUser();
						user.setAdminId(tmp.getUser_id());
						user = manageAdminUserService.getManageAdminUser(user);
//						user.setAdminName(hcompany.getContact_phone());
						user.setOneAgentOpenId(agentOneOpenId);
						user.setTwoAgentOpenId(agentTwoOpenId);
						user.setServicerId(servicerId);
						user.setOneAgentName(agentOneName);
						user.setTwoAgentName(agentTwoName);
						user.setServicerName(servicerName);
						manageAdminUserService.updateManageAdminUser(user);
						writeSuccessMsg("修改成功", null, response);
//					}else{
//						writeErrorMsg("验证码不正确", null, response);
//					}
//				}
				
//				else{
//					hcompanyService.updateHCompany(hcompany);
//					writeSuccessMsg("修改成功", null, response);
//				}
			}
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HCompany hcompany = new HCompany();
			
			ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			if(adminUser!=null){
				Integer id = RequestHandler.getInteger(request, "id");
				hcompany.setId(id);
				
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
				
				String vercode = RequestHandler.getString(request, "vercode");
				
				Integer com_business_doc_type = RequestHandler.getInteger(request, "com_business_doc_type");
				hcompany.setCom_business_doc_type(com_business_doc_type);
				HCompany tmp = new HCompany();
				tmp.setUser_id(adminUser.getAdminId());
				List<HCompany> tmplist = hcompanyService.getHCompanyBaseList(tmp);
				if(tmplist!=null&&tmplist.size()>0){
					tmp = tmplist.get(0);
//					if(tmp.getContact_phone()!=null&&tmp.getContact_phone().equals(contact_phone)){
						//验证码
//						String sysCode = SendMsgUtil.CODEMAP.get(contact_phone);
//						if(StringUtils.isNotBlank(vercode)&&StringUtils.isNotBlank(sysCode)&&vercode.equals(sysCode)){
							hcompanyService.updateHCompany(hcompany);
							
							ManageAdminUser manageAdminUser = new ManageAdminUser();
							manageAdminUser.setAdminId(adminUser.getAdminId());
							manageAdminUser.setMobile(contact_phone);
							
							manageAdminUserService.updateManageAdminUser(manageAdminUser);
							
							writeSuccessMsg("修改成功", null, response);
							
							
							
//						}else{
//							writeErrorMsg("验证码不正确", null, response);
//						}
//					}else{
//						writeErrorMsg("联系人手机号码不正确", null, response);
//					}
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
	
	@RequestMapping(value = "/removeHCompany", method = RequestMethod.POST)
	public String removeHCompany(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HCompany hcompany = new HCompany();
			Integer id = RequestHandler.getInteger(request, "id");
			hcompany.setId(id);

			hcompany = hcompanyService.getHCompany(hcompany);
			//删除对应的账户
			ManageAdminUser user1 = new ManageAdminUser();
			user1.setAdminId(hcompany.getUser_id());
			manageAdminUserService.removeManageAdminUser(user1);
			//删除对应的复核人员
			HReviewUser hReviewUser = new HReviewUser();
			hReviewUser.setCompanyId(id);
			List<HReviewUser> list = hreviewuserService.getHReviewUserBaseList(hReviewUser);
			if(list!=null){
				for(HReviewUser ru:list){
					hreviewuserService.removeHReviewUser(ru);
				}
			}
			//修改客户的所有缴费号
			HAmmeterInfo hAmmeterInfo = new HAmmeterInfo();
			hAmmeterInfo.setC_id(id);
			List<HAmmeterInfo> listAmmeterInfo = hAmmeterInfoService.getHAmmeterInfoBaseList(hAmmeterInfo);
			if(listAmmeterInfo!=null&&listAmmeterInfo.size()>0){
				for(HAmmeterInfo af:listAmmeterInfo){
					af.setDelete_state(0);
					af.setPay_status("0");
					hAmmeterInfoService.updateHAmmeterInfo(af);
				}
			}
			//删除自单位
			HSubCompany hSubCompany = new HSubCompany();
			hSubCompany.setC_id(id);
			List<HSubCompany> listHSubCompany = hSubCompanyService.getHSubCompanyBaseList(hSubCompany);
			if(listHSubCompany!=null&&listHSubCompany.size()>0){
				for(HSubCompany sb:listHSubCompany){
					hSubCompanyService.removeHSubCompany(sb);
				}
			}
			
			hcompanyService.removeHCompany(hcompany);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHCompany", method = RequestMethod.POST)
	public String removeAllHCompany(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						HCompany hCompany = new HCompany();
						hCompany.setId(Integer.valueOf(id));
						
						hCompany = hcompanyService.getHCompany(hCompany);
						//删除对应的账户
						ManageAdminUser user1 = new ManageAdminUser();
						user1.setAdminId(hCompany.getUser_id());
						manageAdminUserService.removeManageAdminUser(user1);
						//删除对应的复核人员
						HReviewUser hReviewUser = new HReviewUser();
						hReviewUser.setCompanyId(Integer.valueOf(id));
						List<HReviewUser> list = hreviewuserService.getHReviewUserBaseList(hReviewUser);
						if(list!=null){
							for(HReviewUser ru:list){
								hreviewuserService.removeHReviewUser(ru);
							}
						}
						//修改客户的所有缴费号
						HAmmeterInfo hAmmeterInfo = new HAmmeterInfo();
						hAmmeterInfo.setC_id(Integer.valueOf(id));
						List<HAmmeterInfo> listAmmeterInfo = hAmmeterInfoService.getHAmmeterInfoBaseList(hAmmeterInfo);
						if(listAmmeterInfo!=null&&listAmmeterInfo.size()>0){
							for(HAmmeterInfo af:listAmmeterInfo){
								af.setDelete_state(0);
								af.setPay_status("0");
								hAmmeterInfoService.updateHAmmeterInfo(af);
							}
						}
						//删除自单位
						HSubCompany hSubCompany = new HSubCompany();
						hSubCompany.setC_id(Integer.valueOf(id));
						List<HSubCompany> listHSubCompany = hSubCompanyService.getHSubCompanyBaseList(hSubCompany);
						if(listHSubCompany!=null&&listHSubCompany.size()>0){
							for(HSubCompany sb:listHSubCompany){
								hSubCompanyService.removeHSubCompany(sb);
							}
						}
						
						hcompanyService.removeHCompany(hCompany);
					}
				}
			}
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	/** 发验证码 **/
	@RequestMapping(value = "/sendCode", method = RequestMethod.POST)
	public String sendCode(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception
	{
		String phone =  RequestHandler.getString(request, "phone");
		if(StringUtils.isNotBlank(phone)){
			HCompany tmp = new HCompany();
			tmp.setContact_phone(phone);
			List list = hcompanyService.getHCompanyBaseList(tmp);
			if(list!=null&&list.size()>0){
				writeErrorMsg("手机号码已存在！", "", response);
			}else{
				String randomCode = SendMsgUtil.getRandomCode(4);
				String content = "公司手机验证码：" + randomCode;
				int s = SendMsgUtil.sendMsg(phone,content);
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
	/** 审核窗口**/
	/** 发验证码 **/
	@RequestMapping(value = "/vercode", method = RequestMethod.POST)
	public String vercode(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception
	{
		String phone =  RequestHandler.getString(request, "phone");
		String vercode = RequestHandler.getString(request, "vercode");
		Object token = request.getSession().getAttribute(SessionName.TOKEN);// 验证码
		if(StringUtils.isNotBlank(phone)){
			HCompany tmp = new HCompany();
			tmp.setContact_phone(phone);
			List list = hcompanyService.getHCompanyBaseList(tmp);
			if(list!=null&&list.size()>0){
				writeErrorMsg("该手机号已经注册。", "", response);
			}else{
				if(StringUtils.isBlank(vercode)){
					writeErrorMsg("验证码不正确", "", response);
					return null;
				}
				if (token == null || !vercode.equalsIgnoreCase(token.toString())) {
					writeErrorMsg("验证码不正确", "", response);
					return null;
				}
				String randomCode = SendMsgUtil.getRandomCode(4);
				String content = "欢迎您的注册，手机验证码为：" + randomCode;
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
	/** 审核窗口**/
	/** 发验证码 **/
	@RequestMapping(value = "/vercode1", method = RequestMethod.POST)
	public String vercode1(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception
	{
		String phone =  RequestHandler.getString(request, "phone");
		String vercode = RequestHandler.getString(request, "vercode");
		Integer id = RequestHandler.getInteger(request, "id");
		Object token = request.getSession().getAttribute(SessionName.TOKEN);// 验证码
		if(StringUtils.isNotBlank(phone)){
			HCompany tmp = new HCompany();
			tmp.setContact_phone(phone);
			tmp.setNotid(id);
			List list = hcompanyService.getHCompanyBaseList(tmp);
			if(list!=null&&list.size()>0){
				writeErrorMsg("手机号码已存在,请您使用其它手机号", "", response);
			}else{
				if(StringUtils.isBlank(vercode)){
					writeErrorMsg("验证码不正确", "", response);
					return null;
				}
				if (token == null || !vercode.equalsIgnoreCase(token.toString())) {
					writeErrorMsg("验证码不正确", "", response);
					return null;
				}
				String randomCode = SendMsgUtil.getRandomCode(4);
				String content = "您正在变更联系人信息，手机号验证码为：" + randomCode;
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
	/** 发验证码 **/
	@RequestMapping(value = "/mcode", method = RequestMethod.POST)
	public String mcode(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception
	{
		String phone =  RequestHandler.getString(request, "phone");
		String vercode = RequestHandler.getString(request, "vercode");
		ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
		if(StringUtils.isNotBlank(phone)){
			HCompany tmp = new HCompany();
			tmp.setContact_phone(phone);
			List list = hcompanyService.getHCompanyBaseList(tmp);
			if(list!=null&&list.size()>0){
				writeErrorMsg("手机号码已存在！", "", response);
			}else{
				if(adminUser!=null){
					Object token = request.getSession().getAttribute(SessionName.TOKEN);// 验证码
					if(StringUtils.isBlank(vercode)){
						writeErrorMsg("验证码不正确", "", response);
						return null;
					}
					if (token == null || !vercode.equalsIgnoreCase(token.toString())) {
						writeErrorMsg("验证码不正确", "", response);
						return null;
					}
					String randomCode = SendMsgUtil.getRandomCode(4);
					String content = "手机验证码：" + randomCode;
					int s = SendMsgUtil.sendMsg(phone,content);
					if(s > 0){
						SendMsgUtil.TIMEMAP.put(phone, new Date());
						SendMsgUtil.CODEMAP.put(phone, randomCode);
						writeSuccessMsg("发送成功", null, response);
					}else{
						writeErrorMsg("发送失败", "", response);
					}
				}else{
					writeErrorMsg("请先登录", "", response);
				}
			}
		}else{
			writeErrorMsg("发送失败", "", response);
		}
		return null;
	}
	/** 审核窗口**/
	@RequestMapping(value = "/toInfo/{id}", method = RequestMethod.GET)
	public String toInfo(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{
		HCompany com = new HCompany();
		com.setId(Integer.valueOf(id));
		List<HCompany> list = hcompanyService.getHCompanyBaseList(com);
		model.addAttribute("comInfo", list.get(0));
		return "/hCompany/hCompanyInfo";
	}
	
	/** 审核**/
	@RequestMapping(value = "/verify", method = RequestMethod.POST)
	public String verify(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try{
			String id =  RequestHandler.getString(request, "id");
			String reason =  RequestHandler.getString(request, "reason");
			String type = RequestHandler.getString(request, "type");//1 通过 2 驳回
			String ywyId = RequestHandler.getString(request, "ywyId");
			ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			HCompany com = new HCompany();
			com.setId(Integer.valueOf(id));
			List<HCompany> list = hcompanyService.getHCompanyBaseList(com);
			if(list!=null&&list.size()>0){
				com = list.get(0);
				com.setVerify_status(Integer.valueOf(type));
				com.setVerify_user_id(adminUser.getAdminId());
				if(StringUtils.isNotBlank(reason)){
					com.setVerify_reason(reason);
				}
				if(StringUtils.isNotBlank(ywyId)){
					com.setYwyId(Integer.valueOf(ywyId));
				}
				if(com.getYwyId()!=null){
					hcompanyService.updateHCompany(com);
					boolean b = true;
					//判断是否已经生成了账号和密码
					String username = com.getFax();
					ManageAdminUser user1 = new ManageAdminUser();
					user1.setAdminName(username);
					int count = manageAdminUserService.getManageAdminUserListCount(user1);
					if(count>0){
						b = false;
					}
					
					//生成账号密码
					if(Integer.valueOf(type)==1&&b){
//						String username = com.getFax();
						StringBuilder str=new StringBuilder();//定义变长字符串
						Random random=new Random();
						for(int i=0;i<8;i++){
							str.append(random.nextInt(10));
						}
						String password = str.toString();
						
						ManageAdminUser user = new ManageAdminUser();
						user.setAdminName(username);
						user.setPasswd(MD5.getMD5ofStr(password));
						user.setNickName(com.getName());
						user.setRealName(com.getName());
						user.setMobile(com.getContact_phone());
						user.setCreateTime(new Date());
						user.setCreaterId(adminUser.getAdminId());
						user.setState(1);
						Integer userId = manageAdminUserService.insertManageAdminUser(user);
						AdminUserRole adminUserRole = new AdminUserRole();
						adminUserRole.setAdminId(userId);
						adminUserRole.setRoleId(Integer.valueOf(FileUploadConstants.COMPANY_ROLE_ID));
						adminuserroleService.insertAdminUserRole(adminUserRole);
						com.setUser_id(userId);
						hcompanyService.updateHCompany(com);
						String phone =  com.getContact_phone();
//						String content = "您在恒信通网站提交的注册信息已通过审核，登录账号为"+username+"，密码为"+password+"，登录http://qiye.chinaepay.com即可交电费啦！";
						String content = "贵单位的“电费短信通知服务”已开通。登录http://qiye.chinaepay.com既可以查询电费，也可以交纳电费，并享有送票上门服务。登录账号："+username+"密码："+password+"详情可致电96199咨询";
						SendMsgUtil.sendMsg(phone,content);
					}
					writeSuccessMsg("成功", null, response);
				}else{
					writeErrorMsg("审核失败，未填写业务员", null, response);
				}
			}else{
				writeErrorMsg("审核失败，请联系管理员", null, response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/** 暂停、恢复**/
	@RequestMapping(value = "/stop", method = RequestMethod.POST)
	public String stop(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try{
			String id =  RequestHandler.getString(request, "id");
			String type = RequestHandler.getString(request, "type");//1 恢复 2 暂停 
			HCompany com = new HCompany();
			com.setId(Integer.valueOf(id));
			List<HCompany> list = hcompanyService.getHCompanyBaseList(com);
			if(list!=null&&list.size()>0){
				com = list.get(0);
				com.setStatus(Integer.valueOf(type));
				hcompanyService.updateHCompany(com);
				writeSuccessMsg("操作成功", null, response);
			}else{
				writeErrorMsg("操作失败，请联系管理员", null, response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	/** 暂停、恢复**/
	@RequestMapping(value = "/israteFlag", method = RequestMethod.POST)
	public String israteFlag(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try{
			String id =  RequestHandler.getString(request, "id");
			String type = RequestHandler.getString(request, "type");//1 恢复 2 暂停 
			HCompany com = new HCompany();
			com.setId(Integer.valueOf(id));
			List<HCompany> list = hcompanyService.getHCompanyBaseList(com);
			if(list!=null&&list.size()>0){
				com = list.get(0);
				com.setRateFlag(Integer.valueOf(type));
				hcompanyService.updateHCompany(com);
				writeSuccessMsg("操作成功", null, response);
			}else{
				writeErrorMsg("操作失败，请联系管理员", null, response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	/** 修改密码**/
	@RequestMapping(value = "/toMp", method = RequestMethod.POST)
	public String toMp(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/front/mp";
	}
	@RequestMapping(value = "/mp", method = RequestMethod.POST)
	public String mp(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try{
			String opass = RequestHandler.getString(request, "opass");
			String npass = RequestHandler.getString(request, "npass");
//			String phone = RequestHandler.getString(request, "phone");
//			String vercode = RequestHandler.getString(request, "vercode");
			HCompany com = new HCompany();
			ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			if(adminUser!=null){
				//验证码
/*				String sysCode = SendMsgUtil.CODEMAP.get(phone);
				if(StringUtils.isNotBlank(vercode)&&StringUtils.isNotBlank(sysCode)&&vercode.equals(sysCode)){*/
//				Object token = request.getSession().getAttribute(SessionName.TOKEN);// 验证码
//				if(StringUtils.isBlank(vercode)){
//					writeErrorMsg("验证码不正确", "", response);
//					return null;
//				}
//				if (token == null || !vercode.equalsIgnoreCase(token.toString())) {
//					writeErrorMsg("验证码不正确", "", response);
//					return null;
//				}
					com.setUser_id(adminUser.getAdminId());
					List<HCompany> list = hcompanyService.getHCompanyBaseList(com);
					if(list!=null&&list.size()>0){
						com = list.get(0);
//						if(StringUtils.isNotBlank(phone)){
//							if(phone.equals(com.getContact_phone())){
								Integer uid = com.getUser_id();
								if(uid!=null){
									ManageAdminUser user = new ManageAdminUser();
									user.setAdminId(uid);
									List<ManageAdminUser> ulst = manageAdminUserService.getManageAdminUserBaseList(user);
									if(ulst!=null&&ulst.size()>0){
										user = ulst.get(0);
										if (user.getPasswd().equals(MD5.getMD5ofStr(opass))) {
//											StringBuilder str=new StringBuilder();//定义变长字符串
//											Random random=new Random();
//											for(int i=0;i<8;i++){
//												str.append(random.nextInt(10));
//											}
//											String password = str.toString();
											user.setPasswd(MD5.getMD5ofStr(npass));
											manageAdminUserService.updateManageAdminUser(user);
//											String content = "您的新密码为：" + password;
//											int s = SendMsgUtil.sendMsg(phone,content);
											writeSuccessMsg("修改成功", null, response);
										}else{
											writeErrorMsg("旧密码不正确", null, response);
										}
									}else{
										writeErrorMsg("用户不存在", null, response);
									}
								}else{
									writeErrorMsg("用户不存在", null, response);
								}
//							}else{
//								writeErrorMsg("手机号码不正确", null, response);
//							}
//						}else{
//							writeErrorMsg("手机号码不能为空", null, response);
//						}
					}else{
						writeErrorMsg("用户不存在", null, response);
					}
			/*	}else{
					writeErrorMsg("验证码不正确", null, response);
				}*/
			}else{
				writeErrorMsg("请登录后再进行操作", null, response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		
	}
	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
	public String exportExcel(HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			HCompany hcompany = new HCompany();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hcompany.setId(id);
			
			String name = RequestHandler.getString(request, "name");
			hcompany.setName(name);
			
			Integer user_id = RequestHandler.getInteger(request, "user_id");
			hcompany.setUser_id(user_id);
			
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
			Integer rateFlag = RequestHandler.getInteger(request, "rateFlag");
			hcompany.setVerify_user_id(verify_user_id);
			hcompany.setRateFlag(rateFlag);
			
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
			hcompany.setStartTime(RequestHandler.getDate(request, "startTime"));
			hcompany.setEndTime(RequestHandler.getDate(request, "endTime"));
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hcompany.setSortColumn(sortColumn);
			String exportType = RequestHandler.getString(request, "exportType");
			Integer com_business_doc_type = RequestHandler.getInteger(request, "com_business_doc_type");
			hcompany.setCom_business_doc_type(com_business_doc_type);
			int hcompanyListCount = hcompanyService.getHCompanyListCount(hcompany);
			if ( hcompanyListCount > 0 )
			{
				List manageadminuserList = hcompanyService.getHCompanyBaseList(hcompany);
				LinkedList list = new LinkedList();
				list.addAll(manageadminuserList);
				LinkedList fields = new LinkedList();
				fields.add("id");
				fields.add("name");
				fields.add("contact_name");
				fields.add("contact_phone");
				fields.add("contact_mail");
				fields.add("fax");
				fields.add("fax_ext");
				fields.add("verify_status");
				fields.add("status");
				fields.add("create_time");
				fields.add("oneAgentName");
				fields.add("twoAgentName");
				fields.add("servicerName");
				LinkedList titles = new LinkedList();
				titles.add("单位ID");
				titles.add("单位名称");
				titles.add("业务联系人");
				titles.add("业务联系人手机");
				titles.add("业务联系人邮箱");
				titles.add("传真总机");
				titles.add("传真分机");
				titles.add("审核状态");
				titles.add("状态");
				titles.add("创建时间");
				titles.add("客户经理");
				titles.add("代理");
				titles.add("服务人员");
				String path = "";
				if("1".equals(exportType)){
					path = hCommonService.excleExport(list, fields, titles, HCompany.class, "已审核单位管理",request);
				}else{
					path = hCommonService.excleExport(list, fields, titles, HCompany.class, "待审核单位管理",request);
				}
				writeSuccessMsg("成功", path, response);
			} else
			{
				writeErrorMsg("无可导出内容", null, response);
			}
		} catch (Exception e) {
			writeErrorMsg("系统错误", null, response);
		}
		return null;
	}
	/**
	 * 导出未缴费(当月没有缴费的单位导出)
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/exportUnPayHCompanyExcel", method = RequestMethod.GET)
	public String getUnPayHCompany(HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			HCompany hcompany = new HCompany();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hcompany.setId(id);
			
			String name = RequestHandler.getString(request, "name");
			hcompany.setName(name);
			
			Integer user_id = RequestHandler.getInteger(request, "user_id");
			hcompany.setUser_id(user_id);
			
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
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hcompany.setSortColumn(sortColumn);
			String exportType = RequestHandler.getString(request, "exportType");
			Integer com_business_doc_type = RequestHandler.getInteger(request, "com_business_doc_type");
			hcompany.setCom_business_doc_type(com_business_doc_type);
			
			Calendar cal = Calendar.getInstance();
		    int month = cal.get(Calendar.MONTH) + 1;
		    int year = cal.get(Calendar.YEAR);

		    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			sf.format(new Date());
			String stime = year + "-" + month + "-1";
			hcompany.setStartTimeOrder(sf.parse(stime + " 00:00:00"));
			hcompany.setEndTimeOrder(new Date());
			List manageadminuserList = null;
			if(exportType.equals("1")){
				hcompany.setPay_flage("1");
				hcompany.setPay_status("0");
				manageadminuserList = hcompanyService.getUnPayHCompany(hcompany);
			}
			if(exportType.equals("2")){
				hcompany.setPay_flage("2");
				manageadminuserList = hcompanyService.getUnPayHCompany1(hcompany);
			}
			
			if(manageadminuserList != null && manageadminuserList.size()!=0){
				LinkedList list = new LinkedList();
				list.addAll(manageadminuserList);
				LinkedList fields = new LinkedList();
				fields.add("id");
				fields.add("name");
				fields.add("contact_name");
				fields.add("contact_phone");
				fields.add("contact_mail");
				fields.add("fax");
				fields.add("fax_ext");
				fields.add("verify_status");
				fields.add("status");
				fields.add("create_time");
				fields.add("oneAgentName");
				fields.add("twoAgentName");
				fields.add("servicerName");
				LinkedList titles = new LinkedList();
				titles.add("单位ID");
				titles.add("单位名称");
				titles.add("业务联系人");
				titles.add("业务联系人手机");
				titles.add("业务联系人邮箱");
				titles.add("传真总机");
				titles.add("传真分机");
				titles.add("审核状态");
				titles.add("状态");
				titles.add("创建时间");
				titles.add("客户经理");
				titles.add("代理");
				titles.add("服务人员");
				String path = "";
				if(exportType.equals("2")){
					path = hCommonService.excleExport(list, fields, titles, HCompany.class, "本月没有制单的单位",request);
				}else{
					path = hCommonService.excleExport(list, fields, titles, HCompany.class, "本月制单没有缴费的单位",request);
				}
				writeSuccessMsg("成功", path, response);
			}else{
				writeErrorMsg("无可导出内容", null, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			writeErrorMsg("系统错误", null, response);
		}
		return null;
	}
	public boolean checkPhone(String phone){
		if(StringUtils.isNotBlank(phone)){
			ManageAdminUser user = new ManageAdminUser();
			user.setAdminName(phone);
			user.setState(1);
			List<ManageAdminUser> list = manageAdminUserService.getManageAdminUserBaseList(user);
			if(list!=null&&list.size()>0){
				return false;
			}else{
				return true;
			}
		}else{
			return false;
		}
	}
	@RequestMapping(value = "/getServicerList", method = RequestMethod.GET)
	public String getServicerList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			//服务人员roleId
			Integer roleId = Integer.valueOf(FileUploadConstants.SERVICER_MANAGE_ROLEID);
			ManageAdminUser param = new ManageAdminUser();
			param.setRoleId(roleId);
			param.setState(1);
			String realName = RequestHandler.getString(request, "realName");
			param.setRealName(realName);
			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			param.setRowStart(from);
			param.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			param.setSortColumn(sortColumn);
			
			int adminListCount = manageAdminUserService.getManageAdminUserListCount(param);
			ResponseList<ManageAdminUser> adminList = null;
			if ( adminListCount > 0 )
			{
				adminList = manageAdminUserService.getManageAdminUserList(param);
			} else
			{
				adminList = new ResponseList<ManageAdminUser>();
			}
			// 设置数据总数
			adminList.setTotalResults(adminListCount);
			
			writeSuccessMsg("ok", adminList, response);
		} catch (Exception e)
		{
			e.printStackTrace();
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	/**
	 * 获得绑定的客户
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getMyCustomer", method = RequestMethod.GET)
	public String getMyCustomer(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		String openId = RequestHandler.getString(request, "openId");
		String companyName = RequestHandler.getString(request, "companyName");
		String contactName = RequestHandler.getString(request, "contactName");
		String contactPhone = RequestHandler.getString(request, "contactPhone");
		String paymentNo = RequestHandler.getString(request, "paymentNo");
		String startTime = RequestHandler.getString(request, "startTime");
		String endTime = RequestHandler.getString(request, "endTime");
		String oneAgentName = RequestHandler.getString(request, "oneAgentName");
		String servicerName = RequestHandler.getString(request, "servicerName");
		Integer style = RequestHandler.getInteger(request, "style");
		try
		{	
			
			if(style==null){
				style = 0;
			}
			
			int totalResults = 0;
			
			HCompany hCompany = new HCompany();
			
			hCompany.setOneAgentName(oneAgentName);
			hCompany.setServicerName(servicerName);
			
//			hCompany.setPay_status("1");
			hCompany.setPaymentNo(paymentNo);
			
			ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(StringUtils.isNotBlank(startTime)){
				hCompany.setStartTime(sf.parse(startTime + " 00:00:00"));
			}
			if(StringUtils.isNotBlank(endTime)){
				hCompany.setEndTime(sf.parse(endTime + " 23:59:59"));
			}
			hCompany.setContact_name(contactName);
			hCompany.setContact_phone(contactPhone);
			hCompany.setName(companyName);
			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			
			hCompany.setRowStart(from);
			hCompany.setRowCount(rowCount);
			
			hCompany.setSortColumn(" a.id desc ");
			
			List<HCompany> list = null;
			
			
			if(adminUser.getRoleId().equals(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID))){
				hCompany.setOneAgentOpenId(adminUser.getOneAgentOpenId());
				hCompany.setSearchType(style);
				list = hcompanyService.getCompanyListByOneAgent(hCompany);
				totalResults = hcompanyService.getCompanyListByOneAgentCount(hCompany);
			}else if(adminUser.getRoleId().equals(Integer.valueOf(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID))){
				hCompany.setOneAgentOpenId(adminUser.getOneAgentOpenId());
				hCompany.setTwoAgentOpenID(adminUser.getTwoAgentOpenId());
				list = hcompanyService.getCompanyListByOther(hCompany);
				totalResults = hcompanyService.getCompanyListByOtherCount(hCompany);
			}else if(adminUser.getRoleId().equals(Integer.valueOf(FileUploadConstants.SERVICER_MANAGE_ROLEID))){
				hCompany.setServicerId(adminUser.getAdminId());
				list = hcompanyService.getCompanyListByOther(hCompany);
				totalResults = hcompanyService.getCompanyListByOtherCount(hCompany);
			}
			
			if(list!=null&&list.size()>0){
				for(HCompany c:list){
					JSONObject jsons = new JSONObject();
					if(StringUtils.isNotBlank(c.getName())){
						jsons.put("companyNme", c.getName());
					}else{
						jsons.put("companyNme", "");
					}
					if(StringUtils.isNotBlank(c.getContact_name())){
						
						jsons.put("contactName", c.getContact_name());
					}else{
						jsons.put("contactName", "");
					}
					jsons.put("contactPhone", c.getContact_phone());
					jsons.put("oneAngetName", c.getOneAgentName());
					jsons.put("servicerName", c.getServicerName());
					jsons.put("servicerId", c.getServicerId());
					jsons.put("companyId", c.getId());
					array.add(jsons);
				}
				json.put("message", "ok");
				json.put("totalResults", totalResults);
				json.put("items", array);
			}else{
				json.put("message", "end");
			}
			response.setContentType("text/html;charset=utf-8");
			response.setHeader("Cache-Control","no-cache");
			response.getWriter().write(json.toString());
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @description 审核
	 * @return
	 */
	@RequestMapping(value = "/toCustomerInfo", method = RequestMethod.GET)
	public String toCustomerInfo(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		Integer companyId = RequestHandler.getInteger(request, "companyId");
		try{
			HCompany hCompany = new HCompany();
			hCompany.setId(companyId);
			hCompany = hcompanyService.getHCompany(hCompany);
//			if(hCompany!=null&&hCompany.getId()>0){
//				HSubCompany hSubCompany = new HSubCompany();
//				hSubCompany.setC_id(companyId);
//				List<HSubCompany> list = hSubCompanyService.getHSubCompanyBaseList(hSubCompany);
//				if(list!=null&&list.size()>0){
//					for(HSubCompany subCompany:list){
//						//查询有效的电表号
//						HAmmeterInfo hAmmeterInfo = new HAmmeterInfo();
//						hAmmeterInfo.setC_id(companyId);
//						hAmmeterInfo.setS_id(subCompany.getS_id());
//						hAmmeterInfo.setPay_status("1");
//						hAmmeterInfo.setDelete_state(1);
//						List<HAmmeterInfo> listNo = hammeterinfoService.getHAmmeterInfoBaseList(hAmmeterInfo);
//						if(listNo!=null&&){
//							
//						}
//					}
//				}
//			}
			model.addAttribute("hCompany", hCompany);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/wx/customerInfo";
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @description 审核
	 * @return
	 */
	@RequestMapping(value = "/customerInfoJson", method = RequestMethod.GET)
	public String customerInfoJson(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		Integer companyId = RequestHandler.getInteger(request, "companyId");
		JSONObject jsonAll = new JSONObject();
		try{
			HSubCompany hSubCompany = new HSubCompany();
			hSubCompany.setC_id(companyId);
			List<HSubCompany> list = hSubCompanyService.getHSubCompanyBaseList(hSubCompany);
			if(list!=null&&list.size()>0){
				com.alibaba.fastjson.JSONArray array = new com.alibaba.fastjson.JSONArray();
				for(HSubCompany subCompany:list){
					//查询有效的电表号
					JSONObject json = new JSONObject();
					if(StringUtils.isNotBlank(subCompany.getSub_name())){
						json.put("name", subCompany.getSub_name());
					}else{
						json.put("name", "");
					}
					
					if(StringUtils.isNotBlank(subCompany.getConsignee())){
						json.put("consignee", subCompany.getConsignee());
					}else{
						json.put("consignee", "");
					}
					
					if(StringUtils.isNotBlank(subCompany.getConsignee_phone())){
						json.put("consignee_phone", subCompany.getConsignee_phone());
					}else{
						json.put("consignee_phone", "");
					}
					
					if(StringUtils.isNotBlank(subCompany.getConsignee_address())){
						json.put("consignee_address", subCompany.getConsignee_address());
					}else{
						json.put("consignee_address", "");
					}
					HAmmeterInfo hAmmeterInfo = new HAmmeterInfo();
					hAmmeterInfo.setC_id(companyId);
					hAmmeterInfo.setS_id(subCompany.getS_id());
					hAmmeterInfo.setPay_status("1");
					hAmmeterInfo.setDelete_state(1);
					List<HAmmeterInfo> listNo = hammeterinfoService.getHAmmeterInfoBaseList(hAmmeterInfo);
					if(listNo!=null&&listNo.size()>0){
						com.alibaba.fastjson.JSONArray array1 = new com.alibaba.fastjson.JSONArray();
						for(HAmmeterInfo ai:listNo){
							JSONObject jsons = new JSONObject();
							//调单
							JSONObject result = hCommonService.hXTServiceQuery(ai.getAmmeter_no(), this.getIpAddr(request));
							if("00".equals(result.get("resultCode"))){//获取电表信息成功
								Integer fee = Integer.valueOf(result.get("totalFeeStr") == null ? "0" : result.get("totalFeeStr").toString());
								String accountName = result.getJSONObject("resultInfo").getString("accountName");
								jsons.put("fee", super.getMoney(fee));
								jsons.put("accountName", accountName);
								jsons.put("ammeterNo", ai.getAmmeter_no());
								array1.add(jsons);
							}
						}
						json.put("items1", array1);
					}
					array.add(json);
				}
				jsonAll.put("message", "ok");
				jsonAll.put("items", array);
			}else{
				jsonAll.put("message", "end");
			}
			response.setContentType("text/html;charset=utf-8");
			response.setHeader("Cache-Control","no-cache");
			response.getWriter().write(jsonAll.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取登陆这的IP地址
	 * @param request
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		ip = request.getHeader("X-Forwarded-For");
		if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个IP值，第一个为真实IP。
			int index = ip.indexOf(',');
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		} else {
			return request.getRemoteAddr();
		}
	}
	
	@RequestMapping(value = "/changePeople", method = RequestMethod.POST)
	public String changePeople(HttpServletRequest request, HttpServletResponse response, Model model) {
		Integer companyId = RequestHandler.getInteger(request, "companyId");
		Integer newAdminId = RequestHandler.getInteger(request, "adminId");
		Integer oldAdminId = RequestHandler.getInteger(request, "oldAdminId");
		try{
			HCompany hCompany = new HCompany();
			hCompany.setId(companyId);
			hCompany = hcompanyService.getHCompany(hCompany);
			if(hCompany!=null&&hCompany.getId()>0){
				ManageAdminUser manageAdminUser = new ManageAdminUser();
				manageAdminUser.setAdminId(newAdminId);
				manageAdminUser = manageAdminUserService.getAdminUserByLogin(manageAdminUser);
				hCompany.setServicerId(newAdminId);
				hCompany.setServicerName(manageAdminUser.getNickName());
				hcompanyService.updateHCompany(hCompany);
				//获取、
				ManageAdminUser manageAdminUser1 = new ManageAdminUser();
				manageAdminUser1.setAdminId(hCompany.getUser_id());
				manageAdminUser1 = manageAdminUserService.getAdminUserByLogin(manageAdminUser1);
				if(manageAdminUser1!=null&&manageAdminUser1.getAdminId()>0){
					manageAdminUser1.setServicerId(newAdminId);
					manageAdminUser1.setServicerName(manageAdminUser.getNickName());
					manageAdminUserService.updateManageAdminUser(manageAdminUser1);
				}
				ManageAdminUser oldUser = new ManageAdminUser();
				oldUser.setAdminId(oldAdminId);
				oldUser = manageAdminUserService.getAdminUserByLogin(oldUser);
				
				ManageAdminUser newUser = new ManageAdminUser();
				newUser.setAdminId(newAdminId);
				newUser = manageAdminUserService.getAdminUserByLogin(newUser);
				//发送模板消息
				huseraccountService.sendCHTempltMsg(newUser.getOpenId(), oldUser.getNickName(), newUser.getNickName(), companyId, oldAdminId, newAdminId);
				huseraccountService.sendCHTempltMsg(oldUser.getOpenId(), oldUser.getNickName(), newUser.getNickName(), companyId, oldAdminId, newAdminId);
				
				writeSuccessMsg("成功", null, response);
			}
		}catch(Exception e){
			writeErrorMsg(e.getLocalizedMessage(), null, response);
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	@RequestMapping(value = "/showChangePeopel", method = RequestMethod.GET)
	public String showChangePeopel(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		Integer companyId = RequestHandler.getInteger(request, "companyId");
		Integer newAdminId = RequestHandler.getInteger(request, "newAdminId");
		Integer oldAdminId = RequestHandler.getInteger(request, "oldAdminId");
		try{
			
			HCompany hCompany = new HCompany();
			hCompany.setId(companyId);
			hCompany = hcompanyService.getHCompany(hCompany);
			
			ManageAdminUser oldUser = new ManageAdminUser();
			oldUser.setAdminId(oldAdminId);
			oldUser = manageAdminUserService.getAdminUserByLogin(oldUser);
			
			ManageAdminUser newUser = new ManageAdminUser();
			newUser.setAdminId(newAdminId);
			newUser = manageAdminUserService.getAdminUserByLogin(newUser);
			
			
			model.addAttribute("hCompany", hCompany);
			model.addAttribute("oldUser", oldUser);
			model.addAttribute("newUser", newUser);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/wx/showChangePeopel";
	}
	
	
	/**
	 * 本月缴费，之前未交费
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/exportPayHCompanyExcel", method = RequestMethod.GET)
	public String exportPayHCompanyExcel(HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			
			LinkedList<HCompany> list = new LinkedList();
			
			HPayOrder hPayOrder = new HPayOrder();
			List<HPayOrder> listO = hpayorderService.getHPayOrderCompany(hPayOrder);
			if(listO!=null&&listO.size()>0){
				for(HPayOrder o:listO){
					hPayOrder.setC_id(o.getC_id());
					int count = hpayorderService.getNoPayOrderCompanyCount(hPayOrder);
					if(count==0){
						HCompany hCompany = new HCompany();
						hCompany.setId(o.getC_id());
						hCompany = hcompanyService.getHCompany(hCompany);
						if(hCompany!=null){
							list.add(hCompany);
						}
					}
				}
			}
			
//			List manageadminuserList = hcompanyService.getUnPayHCompany(hcompany);
			
			if(list != null && list.size()!=0){
//				LinkedList list = new LinkedList();
//				list.addAll(manageadminuserList);
				LinkedList fields = new LinkedList();
				fields.add("name");
				fields.add("contact_name");
				fields.add("contact_phone");
				fields.add("contact_mail");
				fields.add("fax");
				fields.add("fax_ext");
				fields.add("ywyNick");
				fields.add("verify_status");
				fields.add("status");
				fields.add("create_time");
				LinkedList titles = new LinkedList();
				titles.add("单位名称");
				titles.add("业务联系人");
				titles.add("业务联系人手机");
				titles.add("业务联系人邮箱");
				titles.add("传真总机");
				titles.add("传真分机");
				titles.add("业务员");
				titles.add("审核状态");
				titles.add("状态");
				titles.add("创建时间");
				String path = "";
				
				path = hCommonService.excleExport(list, fields, titles, HCompany.class, "本月缴费，之前未交费",request);
				writeSuccessMsg("成功", path, response);
			}else{
				writeErrorMsg("无可导出内容", null, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			writeErrorMsg("系统错误", null, response);
		}
		return null;
	}
	
}
