package com.hxt.hAgentTwo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hxt.hAgent.model.HAgent;
import com.hxt.hAgent.service.HAgentService;
import com.hxt.hAgentTwo.model.HAgentTwo;
import com.hxt.hAgentTwo.service.HAgentTwoService;
import com.hxt.hBank.model.HBank;
import com.hxt.hBank.service.HBankService;
import com.hxt.hCompany.model.HCompany;
import com.hxt.hCompany.service.HCompanyService;
import com.hxt.hEwm.model.HEwm;
import com.hxt.hEwm.service.HEwmService;
import com.hxt.hPayGuli.model.HPayGuli;
import com.hxt.hPayGuli.service.HPayGuliService;
import com.hxt.hRegGuli.model.HRegGuli;
import com.hxt.hRegGuli.service.HRegGuliService;
import com.hxt.hUserAccount.model.HUserAccount;
import com.hxt.hUserAccount.service.HUserAccountService;
import com.hxt.hVerificate.model.HVerificate;
import com.hxt.hVerificate.service.HVerificateService;
import com.sys.manageAdminUser.model.ManageAdminUser;
import com.sys.manageAdminUser.service.ManageAdminUserService;
import com.wx.utils.FileUtils;
import com.base.utils.FileUploadConstants;
import com.base.utils.MD5;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.utils.SessionName;
import com.base.controller.BaseController;
/**
 * @author	zlb
 * @time	2016年08月08日 21:39:34
 */
@Controller
@RequestMapping("/hAgentTwo")
public class HAgentTwoController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HAgentTwoController.class); //Logger
	
	@Autowired
	private HAgentTwoService hagenttwoService = null;
	@Autowired
	private ManageAdminUserService manageadminuserService = null;// 用户
	@Autowired
	private HEwmService hEwmService = null;// 用户
	@Autowired
	private HBankService hbankService = null;
	@Autowired
	private HAgentService hagentService = null;
	@Autowired
	private HCompanyService hcompanyService = null;
	@Autowired
	private HRegGuliService hregguliService = null;
	@Autowired
	private HPayGuliService hpayguliService = null;
	@Autowired
	private HUserAccountService huseraccountService = null;
	@Autowired
	private HVerificateService hverificateService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
		//查询登陆用户角色
		if(adminUser.getRoleType()==1){//超管
			model.addAttribute("roleFlag", 0);
		}else{
			if(adminUser.getRoleId().equals(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID))){
				HAgent hAgent = new HAgent();
				hAgent.setOpenId(adminUser.getOneAgentOpenId());
				hAgent = hagentService.getHAgent(hAgent);
				model.addAttribute("hAgent", hAgent);
				model.addAttribute("roleFlag", 1);
			}else{
				model.addAttribute("roleFlag", 0);
			}
		}
		return "/hAgentTwo/hAgentTwoIndex";
	}
	
	@RequestMapping(value = "/indexPP", method = RequestMethod.GET)
	public String indexPP(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
		//查询登陆用户角色
		if(adminUser.getRoleType()==1){//超管
			model.addAttribute("roleFlag", 0);
		}else{
			if(adminUser.getRoleId().equals(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID))){
				HAgent hAgent = new HAgent();
				hAgent.setOpenId(adminUser.getOneAgentOpenId());
				hAgent = hagentService.getHAgent(hAgent);
				model.addAttribute("hAgent", hAgent);
				model.addAttribute("roleFlag", 1);
			}else{
				model.addAttribute("roleFlag", 0);
			}
		}
		return "/hAgentTwoPP/hAgentTwoIndex";
	}
	
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
		//查询登陆用户角色
		if(adminUser.getRoleType()==1){//超管
			model.addAttribute("roleFlag", 0);
		}else{
			if(adminUser.getRoleId().equals(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID))){
				HAgent hAgent = new HAgent();
				hAgent.setOpenId(adminUser.getOneAgentOpenId());
				hAgent = hagentService.getHAgent(hAgent);
				model.addAttribute("hAgent", hAgent);
				model.addAttribute("roleFlag", 1);
			}else{
				model.addAttribute("roleFlag", 0);
			}
		}
		return "/hAgentTwo/hAgentTwoAdd";
	}
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
		//查询登陆用户角色
		if(adminUser.getRoleType()==1){//超管
			model.addAttribute("roleFlag", 0);
		}else{
			if(adminUser.getRoleId().equals(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID))){
				model.addAttribute("roleFlag", 1);
			}else{
				model.addAttribute("roleFlag", 0);
			}
		}
		HAgentTwo hagenttwo1 = new HAgentTwo();
		hagenttwo1.setId(id);
		HAgentTwo hagenttwo = hagenttwoService.getHAgentTwo(hagenttwo1);
		model.addAttribute("hagenttwo", hagenttwo);
		
		return "/hAgentTwo/hAgentTwoUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHAgentTwoList", method = RequestMethod.GET)
	public String getHAgentTwoList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HAgentTwo hagenttwo = new HAgentTwo();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hagenttwo.setId(id);
			
			String openId = RequestHandler.getString(request, "openId");
			hagenttwo.setOpenId(openId);
			
			String name = RequestHandler.getString(request, "name");
			hagenttwo.setName(name);
			
			String licence_url = RequestHandler.getString(request, "licence_url");
			hagenttwo.setLicence_url(licence_url);
			
			String licence_code = RequestHandler.getString(request, "licence_code");
			hagenttwo.setLicence_code(licence_code);
			
			String tax_url = RequestHandler.getString(request, "tax_url");
			hagenttwo.setTax_url(tax_url);
			
			String legal_person = RequestHandler.getString(request, "legal_person");
			hagenttwo.setLegal_person(legal_person);
			
			Integer card_style = RequestHandler.getInteger(request, "card_style");
			hagenttwo.setCard_style(card_style);
			
			String card_no = RequestHandler.getString(request, "card_no");
			hagenttwo.setCard_no(card_no);
			
			Integer sex = RequestHandler.getInteger(request, "sex");
			hagenttwo.setSex(sex);
			
			String mobile1 = RequestHandler.getString(request, "mobile1");
			hagenttwo.setMobile1(mobile1);
			
			String mobile2 = RequestHandler.getString(request, "mobile2");
			hagenttwo.setMobile2(mobile2);
			
			Integer style = RequestHandler.getInteger(request, "style");
			hagenttwo.setStyle(style);
			
			Integer status = RequestHandler.getInteger(request, "status");
			hagenttwo.setStatus(status);
			
			Date create_time = RequestHandler.getDate(request, "create_time");
			hagenttwo.setCreate_time(create_time);
			
			String create_openId = RequestHandler.getString(request, "create_openId");
			hagenttwo.setCreate_openId(create_openId);
			
			Integer check_status = RequestHandler.getInteger(request, "check_status");
			hagenttwo.setCheck_status(check_status);
			
			String contact_person = RequestHandler.getString(request, "contact_person");
			hagenttwo.setContact_person(contact_person);
			
			String tax_code = RequestHandler.getString(request, "tax_code");
			hagenttwo.setTax_code(tax_code);
			
			String bank_name = RequestHandler.getString(request, "bank_name");
			hagenttwo.setBank_name(bank_name);
			
			String bank_number = RequestHandler.getString(request, "bank_number");
			hagenttwo.setBank_number(bank_number);
			
			String bank_account = RequestHandler.getString(request, "bank_account");
			hagenttwo.setBank_account(bank_account);
			
			String contract_number = RequestHandler.getString(request, "contract_number");
			hagenttwo.setContract_number(contract_number);
			
			Date contract_start_time = RequestHandler.getDate(request, "contract_start_time");
			hagenttwo.setContract_start_time(contract_start_time);
			
			Date contract_end_time = RequestHandler.getDate(request, "contract_end_time");
			hagenttwo.setContract_end_time(contract_end_time);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hagenttwo.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hagenttwo.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hagenttwo.setRemark3(remark3);
			
			Integer agent_id = RequestHandler.getInteger(request, "agent_id");
			hagenttwo.setAgent_id(agent_id);
			
			String agent_name = RequestHandler.getString(request, "agent_name");
			hagenttwo.setAgent_name(agent_name);
			
			String user_name = RequestHandler.getString(request, "user_name");
			hagenttwo.setUser_name(user_name);
			
			ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			//查询登陆用户角色
			if(adminUser.getRoleType()==1){//超管
				String oneAgentOpenId = RequestHandler.getString(request, "oneAgentOpenId");
				hagenttwo.setCreate_openId(oneAgentOpenId);
				
//				String twoAgentOpenId = RequestHandler.getString(request, "twoAgentOpenId");
//				manageadminuser.setTwoAgentOpenId(twoAgentOpenId);
				model.addAttribute("roleType", "1");
			}else{
				if(adminUser.getRoleId().equals(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID))){
					
					hagenttwo.setCreate_openId(adminUser.getOneAgentOpenId());
					
//					String twoAgentOpenId = RequestHandler.getString(request, "twoAgentOpenId");
//					manageadminuser.setTwoAgentOpenId(twoAgentOpenId);
//					model.addAttribute("roleType", "2");
//					model.addAttribute("oneAgentOpenId", adminUser.getOneAgentOpenId());
				}else if(adminUser.getRoleId().equals(Integer.valueOf(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID))){
//					model.addAttribute("roleType", "3");
//					manageadminuser.setOneAgentOpenId(adminUser.getOneAgentOpenId());
//					manageadminuser.setTwoAgentOpenId(adminUser.getTwoAgentOpenId());
					hagenttwo.setOpenId(adminUser.getTwoAgentOpenId());
				}
			}
			
			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hagenttwo.setRowStart(from);
			hagenttwo.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hagenttwo.setSortColumn(sortColumn);
			
			int hagenttwoListCount = hagenttwoService.getHAgentTwoListCount(hagenttwo);
			ResponseList<HAgentTwo> hagenttwoList = null;
			if ( hagenttwoListCount > 0 )
			{
				hagenttwoList = hagenttwoService.getHAgentTwoList(hagenttwo);
			} else
			{
				hagenttwoList = new ResponseList<HAgentTwo>();
			}
			// 设置数据总数
			hagenttwoList.setTotalResults(hagenttwoListCount);
			
			writeSuccessMsg("ok", hagenttwoList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getHAgentTwoPP", method = RequestMethod.GET)
	public String getHAgentTwoPP(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			HAgentTwo hagenttwo = new HAgentTwo();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hagenttwo.setId(id);
			
			String openId = RequestHandler.getString(request, "openId");
			hagenttwo.setOpenId(openId);
			
			String name = RequestHandler.getString(request, "name");
			hagenttwo.setName(name);
			
			String licence_url = RequestHandler.getString(request, "licence_url");
			hagenttwo.setLicence_url(licence_url);
			
			String licence_code = RequestHandler.getString(request, "licence_code");
			hagenttwo.setLicence_code(licence_code);
			
			String tax_url = RequestHandler.getString(request, "tax_url");
			hagenttwo.setTax_url(tax_url);
			
			String legal_person = RequestHandler.getString(request, "legal_person");
			hagenttwo.setLegal_person(legal_person);
			
			Integer card_style = RequestHandler.getInteger(request, "card_style");
			hagenttwo.setCard_style(card_style);
			
			String card_no = RequestHandler.getString(request, "card_no");
			hagenttwo.setCard_no(card_no);
			
			Integer sex = RequestHandler.getInteger(request, "sex");
			hagenttwo.setSex(sex);
			
			String mobile1 = RequestHandler.getString(request, "mobile1");
			hagenttwo.setMobile1(mobile1);
			
			String mobile2 = RequestHandler.getString(request, "mobile2");
			hagenttwo.setMobile2(mobile2);
			
			Integer style = RequestHandler.getInteger(request, "style");
			hagenttwo.setStyle(style);
			
			Integer status = RequestHandler.getInteger(request, "status");
			hagenttwo.setStatus(status);
			
			Date create_time = RequestHandler.getDate(request, "create_time");
			hagenttwo.setCreate_time(create_time);
			
			String create_openId = RequestHandler.getString(request, "create_openId");
			hagenttwo.setCreate_openId(create_openId);
			
			Integer check_status = RequestHandler.getInteger(request, "check_status");
			hagenttwo.setCheck_status(check_status);
			
			String contact_person = RequestHandler.getString(request, "contact_person");
			hagenttwo.setContact_person(contact_person);
			
			String tax_code = RequestHandler.getString(request, "tax_code");
			hagenttwo.setTax_code(tax_code);
			
			String bank_name = RequestHandler.getString(request, "bank_name");
			hagenttwo.setBank_name(bank_name);
			
			String bank_number = RequestHandler.getString(request, "bank_number");
			hagenttwo.setBank_number(bank_number);
			
			String bank_account = RequestHandler.getString(request, "bank_account");
			hagenttwo.setBank_account(bank_account);
			
			String contract_number = RequestHandler.getString(request, "contract_number");
			hagenttwo.setContract_number(contract_number);
			
			Date contract_start_time = RequestHandler.getDate(request, "contract_start_time");
			hagenttwo.setContract_start_time(contract_start_time);
			
			Date contract_end_time = RequestHandler.getDate(request, "contract_end_time");
			hagenttwo.setContract_end_time(contract_end_time);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hagenttwo.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hagenttwo.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hagenttwo.setRemark3(remark3);
			
			Integer agent_id = RequestHandler.getInteger(request, "agent_id");
			hagenttwo.setAgent_id(agent_id);
			
			String agent_name = RequestHandler.getString(request, "agent_name");
			hagenttwo.setAgent_name(agent_name);
			
			String user_name = RequestHandler.getString(request, "user_name");
			hagenttwo.setUser_name(user_name);
			
			Double reg_gl_fee = RequestHandler.getDouble(request, "reg_gl_fee");
			hagenttwo.setReg_gl_fee(reg_gl_fee);
			
			String rStartTime = RequestHandler.getString(request, "rStartTime");
			if(StringUtils.isNotBlank(rStartTime)){
				rStartTime = rStartTime + " 00:00:00";
				hagenttwo.setrStartTime(sf.parse(rStartTime));
			}
			
			String rEndTime = RequestHandler.getString(request, "rEndTime");
			if(StringUtils.isNotBlank(rEndTime)){
				rEndTime = rEndTime + " 23:59:59";
				hagenttwo.setrEndTime(sf.parse(rEndTime));
			}
			
			ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			//查询登陆用户角色
			if(adminUser.getRoleType()==1){//超管
				String oneAgentOpenId = RequestHandler.getString(request, "oneAgentOpenId");
				hagenttwo.setCreate_openId(oneAgentOpenId);
				
//				String twoAgentOpenId = RequestHandler.getString(request, "twoAgentOpenId");
//				manageadminuser.setTwoAgentOpenId(twoAgentOpenId);
				model.addAttribute("roleType", "1");
			}else{
				if(adminUser.getRoleId().equals(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID))){
					
					hagenttwo.setCreate_openId(adminUser.getOneAgentOpenId());
					
//					String twoAgentOpenId = RequestHandler.getString(request, "twoAgentOpenId");
//					manageadminuser.setTwoAgentOpenId(twoAgentOpenId);
//					model.addAttribute("roleType", "2");
//					model.addAttribute("oneAgentOpenId", adminUser.getOneAgentOpenId());
				}else if(adminUser.getRoleId().equals(Integer.valueOf(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID))){
//					model.addAttribute("roleType", "3");
//					manageadminuser.setOneAgentOpenId(adminUser.getOneAgentOpenId());
//					manageadminuser.setTwoAgentOpenId(adminUser.getTwoAgentOpenId());
					hagenttwo.setOpenId(adminUser.getTwoAgentOpenId());
				}
			}
			
			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hagenttwo.setRowStart(from);
			hagenttwo.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hagenttwo.setSortColumn(sortColumn);
			
			int hagenttwoListCount = hagenttwoService.getHAgentTwoPPCount(hagenttwo);
			ResponseList<HAgentTwo> hagenttwoList = null;
			if ( hagenttwoListCount > 0 )
			{
				hagenttwoList = hagenttwoService.getHAgentTwoPP(hagenttwo);
			} else
			{
				hagenttwoList = new ResponseList<HAgentTwo>();
			}
			// 设置数据总数
			hagenttwoList.setTotalResults(hagenttwoListCount);
			
			writeSuccessMsg("ok", hagenttwoList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getHAgentTwoBaseList", method = RequestMethod.GET)
	public String getHAgentTwoBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HAgentTwo hagenttwo = new HAgentTwo();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hagenttwo.setId(id);
			
			String openId = RequestHandler.getString(request, "openId");
			hagenttwo.setOpenId(openId);
			
			String name = RequestHandler.getString(request, "name");
			hagenttwo.setName(name);
			
			String licence_url = RequestHandler.getString(request, "licence_url");
			hagenttwo.setLicence_url(licence_url);
			
			String licence_code = RequestHandler.getString(request, "licence_code");
			hagenttwo.setLicence_code(licence_code);
			
			String tax_url = RequestHandler.getString(request, "tax_url");
			hagenttwo.setTax_url(tax_url);
			
			String legal_person = RequestHandler.getString(request, "legal_person");
			hagenttwo.setLegal_person(legal_person);
			
			Integer card_style = RequestHandler.getInteger(request, "card_style");
			hagenttwo.setCard_style(card_style);
			
			String card_no = RequestHandler.getString(request, "card_no");
			hagenttwo.setCard_no(card_no);
			
			Integer sex = RequestHandler.getInteger(request, "sex");
			hagenttwo.setSex(sex);
			
			String mobile1 = RequestHandler.getString(request, "mobile1");
			hagenttwo.setMobile1(mobile1);
			
			String mobile2 = RequestHandler.getString(request, "mobile2");
			hagenttwo.setMobile2(mobile2);
			
			Integer style = RequestHandler.getInteger(request, "style");
			hagenttwo.setStyle(style);
			
			Integer status = RequestHandler.getInteger(request, "status");
			hagenttwo.setStatus(status);
			
			Date create_time = RequestHandler.getDate(request, "create_time");
			hagenttwo.setCreate_time(create_time);
			
			String create_openId = RequestHandler.getString(request, "create_openId");
			hagenttwo.setCreate_openId(create_openId);
			
			Integer check_status = RequestHandler.getInteger(request, "check_status");
			hagenttwo.setCheck_status(check_status);
			
			String contact_person = RequestHandler.getString(request, "contact_person");
			hagenttwo.setContact_person(contact_person);
			
			String tax_code = RequestHandler.getString(request, "tax_code");
			hagenttwo.setTax_code(tax_code);
			
			String bank_name = RequestHandler.getString(request, "bank_name");
			hagenttwo.setBank_name(bank_name);
			
			String bank_number = RequestHandler.getString(request, "bank_number");
			hagenttwo.setBank_number(bank_number);
			
			String bank_account = RequestHandler.getString(request, "bank_account");
			hagenttwo.setBank_account(bank_account);
			
			String contract_number = RequestHandler.getString(request, "contract_number");
			hagenttwo.setContract_number(contract_number);
			
			Date contract_start_time = RequestHandler.getDate(request, "contract_start_time");
			hagenttwo.setContract_start_time(contract_start_time);
			
			Date contract_end_time = RequestHandler.getDate(request, "contract_end_time");
			hagenttwo.setContract_end_time(contract_end_time);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hagenttwo.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hagenttwo.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hagenttwo.setRemark3(remark3);
			
			Integer agent_id = RequestHandler.getInteger(request, "agent_id");
			hagenttwo.setAgent_id(agent_id);
			
			String agent_name = RequestHandler.getString(request, "agent_name");
			hagenttwo.setAgent_name(agent_name);
			String user_name = RequestHandler.getString(request, "user_name");
			hagenttwo.setUser_name(user_name);
			List<HAgentTwo> hagenttwoList = hagenttwoService.getHAgentTwoBaseList(hagenttwo);
		
			writeSuccessMsg("ok", hagenttwoList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHAgentTwo", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HAgentTwo hagenttwo = new HAgentTwo();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hagenttwo.setId(id);
			String uuid = UUID.randomUUID().toString();
			uuid = uuid.replaceAll("-","");
			hagenttwo.setOpenId(uuid);
			String name = RequestHandler.getString(request, "name");
			hagenttwo.setName(name);
			String licence_url = RequestHandler.getString(request, "licence_url");
			hagenttwo.setLicence_url(licence_url);
			String licence_code = RequestHandler.getString(request, "licence_code");
			hagenttwo.setLicence_code(licence_code);
			String tax_url = RequestHandler.getString(request, "tax_url");
			hagenttwo.setTax_url(tax_url);
			String legal_person = RequestHandler.getString(request, "legal_person");
			hagenttwo.setLegal_person(legal_person);
			Integer card_style = RequestHandler.getInteger(request, "card_style");
			hagenttwo.setCard_style(card_style);
			String card_no = RequestHandler.getString(request, "card_no");
			hagenttwo.setCard_no(card_no);
			Integer sex = RequestHandler.getInteger(request, "sex");
			hagenttwo.setSex(sex);
			String mobile1 = RequestHandler.getString(request, "mobile1");
			hagenttwo.setMobile1(mobile1);
			String mobile2 = RequestHandler.getString(request, "mobile2");
			hagenttwo.setMobile2(mobile2);
			Integer style = RequestHandler.getInteger(request, "style");
			hagenttwo.setStyle(style);
			Integer status = RequestHandler.getInteger(request, "status");
			hagenttwo.setStatus(status);
			hagenttwo.setCreate_time(new Date());
			String create_openId = RequestHandler.getString(request, "create_openId");
			hagenttwo.setCreate_openId(create_openId);
			Integer check_status = RequestHandler.getInteger(request, "check_status");
			hagenttwo.setCheck_status(check_status);
			String contact_person = RequestHandler.getString(request, "contact_person");
			hagenttwo.setContact_person(contact_person);
			String tax_code = RequestHandler.getString(request, "tax_code");
			hagenttwo.setTax_code(tax_code);
			String bank_name = RequestHandler.getString(request, "bank_name");
			hagenttwo.setBank_name(bank_name);
			String bank_number = RequestHandler.getString(request, "bank_number");
			hagenttwo.setBank_number(bank_number);
			String bank_account = RequestHandler.getString(request, "bank_account");
			hagenttwo.setBank_account(bank_account);
			String contract_number = RequestHandler.getString(request, "contract_number");
			hagenttwo.setContract_number(contract_number);
			Date contract_start_time = RequestHandler.getDate(request, "contract_start_time");
			hagenttwo.setContract_start_time(contract_start_time);
			Date contract_end_time = RequestHandler.getDate(request, "contract_end_time");
			hagenttwo.setContract_end_time(contract_end_time);
			String remark1 = RequestHandler.getString(request, "remark1");
			hagenttwo.setRemark1(remark1);
			String remark2 = RequestHandler.getString(request, "remark2");
			hagenttwo.setRemark2(remark2);
			String remark3 = RequestHandler.getString(request, "remark3");
			hagenttwo.setRemark3(remark3);
			Integer agent_id = RequestHandler.getInteger(request, "agent_id");
			hagenttwo.setAgent_id(agent_id);
			String agent_name = RequestHandler.getString(request, "agent_name");
			hagenttwo.setAgent_name(agent_name);
			hagenttwo.setCreate_openId(create_openId);
			String user_name = RequestHandler.getString(request, "user_name");
			hagenttwo.setUser_name(user_name);
//			if(hagenttwoService.checkCard(hagenttwo)){
			//获取默认鼓励金
			HRegGuli hRegGuli = new HRegGuli();
			hRegGuli.setIsDefault(1);
			hRegGuli.setState(1);
			hRegGuli = hregguliService.getHRegGuli(hRegGuli);
			if(hRegGuli!=null){
				hagenttwo.setReg_gl_id(hRegGuli.getId());
			}
			//获取默认银联支付鼓励金
			HPayGuli hPayGuli = new HPayGuli();
			hPayGuli.setIs_default(1);
			hPayGuli.setState(1);
			hPayGuli.setOpObject(2);
			List<HPayGuli> listpaygl = hpayguliService.getHPayGuliBaseList(hPayGuli);
			if(listpaygl!=null&&listpaygl.size()>0){
				for(HPayGuli paygl:listpaygl){
					if(paygl.getStyle()==1){//银联
						hagenttwo.setGl_yl_id(paygl.getId());
						hagenttwo.setGl_yl_name(paygl.getName());
					}else if(paygl.getStyle()==2){
						hagenttwo.setGl_sj_id(paygl.getId());
						hagenttwo.setGl_sj_name(paygl.getName());
					}
				}
			}
			
				hagenttwoService.insertHAgentTwo(hagenttwo);
				manageadminuserService.saveAgentManage2(mobile1, user_name,user_name, hagenttwo.getCreate_openId(), hagenttwo.getOpenId(), Integer.valueOf(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID),Integer.valueOf(getSession(request, SessionName.ADMIN_USER_ID).toString()),agent_name,name);
				writeSuccessMsg("添加成功", null, response);
//			}else{
//				writeErrorMsg("身份证号重复", null, response);
//			}
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHAgentTwo", method = RequestMethod.POST)
	public String updateHAgentTwo(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HAgentTwo hagenttwo = new HAgentTwo();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hagenttwo.setId(id);
			
			String openId = RequestHandler.getString(request, "openId");
			hagenttwo.setOpenId(openId);
			
			String name = RequestHandler.getString(request, "name");
			hagenttwo.setName(name);
			
			String licence_url = RequestHandler.getString(request, "licence_url");
			hagenttwo.setLicence_url(licence_url);
			
			String licence_code = RequestHandler.getString(request, "licence_code");
			hagenttwo.setLicence_code(licence_code);
			
			String tax_url = RequestHandler.getString(request, "tax_url");
			hagenttwo.setTax_url(tax_url);
			
			String legal_person = RequestHandler.getString(request, "legal_person");
			hagenttwo.setLegal_person(legal_person);
			
			Integer card_style = RequestHandler.getInteger(request, "card_style");
			hagenttwo.setCard_style(card_style);
			
			String card_no = RequestHandler.getString(request, "card_no");
			hagenttwo.setCard_no(card_no);
			
			Integer sex = RequestHandler.getInteger(request, "sex");
			hagenttwo.setSex(sex);
			
			String mobile1 = RequestHandler.getString(request, "mobile1");
			hagenttwo.setMobile1(mobile1);
			
			String mobile2 = RequestHandler.getString(request, "mobile2");
			hagenttwo.setMobile2(mobile2);
			
			Integer style = RequestHandler.getInteger(request, "style");
			hagenttwo.setStyle(style);
			
			Integer status = RequestHandler.getInteger(request, "status");
			hagenttwo.setStatus(status);
			
			Date create_time = RequestHandler.getDate(request, "create_time");
			hagenttwo.setCreate_time(create_time);
			
			String create_openId = RequestHandler.getString(request, "create_openId");
			hagenttwo.setCreate_openId(create_openId);
			
			Integer check_status = RequestHandler.getInteger(request, "check_status");
			hagenttwo.setCheck_status(check_status);
			
			String contact_person = RequestHandler.getString(request, "contact_person");
			hagenttwo.setContact_person(contact_person);
			
			String tax_code = RequestHandler.getString(request, "tax_code");
			hagenttwo.setTax_code(tax_code);
			
			String bank_name = RequestHandler.getString(request, "bank_name");
			hagenttwo.setBank_name(bank_name);
			
			String bank_number = RequestHandler.getString(request, "bank_number");
			hagenttwo.setBank_number(bank_number);
			
			String bank_account = RequestHandler.getString(request, "bank_account");
			hagenttwo.setBank_account(bank_account);
			
			String contract_number = RequestHandler.getString(request, "contract_number");
			hagenttwo.setContract_number(contract_number);
			
			Date contract_start_time = RequestHandler.getDate(request, "contract_start_time");
			hagenttwo.setContract_start_time(contract_start_time);
			
			Date contract_end_time = RequestHandler.getDate(request, "contract_end_time");
			hagenttwo.setContract_end_time(contract_end_time);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hagenttwo.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hagenttwo.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hagenttwo.setRemark3(remark3);
			
			Integer agent_id = RequestHandler.getInteger(request, "agent_id");
			hagenttwo.setAgent_id(agent_id);
			
			String agent_name = RequestHandler.getString(request, "agent_name");
			hagenttwo.setAgent_name(agent_name);
			String user_name = RequestHandler.getString(request, "user_name");
			hagenttwo.setUser_name(user_name);
//			if(hagenttwoService.checkCard(hagenttwo)){
				
				HAgentTwo hAgentTwo = new HAgentTwo();
				hAgentTwo.setId(id);
				HAgentTwo hAgentTwo1 = hagenttwoService.getHAgentTwo(hAgentTwo);//原来代理
				
				int ss = hagenttwoService.updateHAgentTwo(hagenttwo);
				
				ManageAdminUser manageadminuser = new ManageAdminUser();
				manageadminuser.setRoleId(Integer.valueOf(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID));
				manageadminuser.setTwoAgentOpenId(hAgentTwo1.getOpenId());
				manageadminuser = manageadminuserService.getManageAdminUser(manageadminuser);
				if(manageadminuser!=null){
					manageadminuser.setOneAgentOpenId(hagenttwo.getCreate_openId());
					manageadminuser.setOneAgentName(agent_name);
					manageadminuser.setTwoAgentName(name);
					manageadminuser.setPhone(hagenttwo.getMobile1());
					if(hAgentTwo1.getMobile1()!=null){
						if(!hAgentTwo1.getMobile1().equals(mobile1)){//如果原来代理手机号发生变化，要修改密码
							String passwd = mobile1.substring(mobile1.length()-6, mobile1.length());
			    			manageadminuser.setPasswd(new MD5().getMD5ofStr(passwd));//如果修改了手机，将要修改密码
						}
					}
					
					manageadminuserService.updateManageAdminUser(manageadminuser);
				}
				if(ss>0){
					HCompany hCompany = new HCompany();
					hCompany.setTwoAgentOpenID(hAgentTwo1.getOpenId());
					hCompany.setTwoAgentName(name);
					hcompanyService.updateTwoAgentName(hCompany);
					//修改账户
					HUserAccount agentAccount = new HUserAccount();
					agentAccount.setTwoAgentOpenId(hAgentTwo1.getOpenId());
					agentAccount.setRole_id(Integer.valueOf(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID));
					agentAccount = huseraccountService.getHUserAccount(agentAccount);
					if(agentAccount!=null){
						agentAccount.setOneAgentOpenId(hagenttwo.getCreate_openId());
						agentAccount.setOneAgentName(hagenttwo.getAgent_name());
						huseraccountService.updateHUserAccount(agentAccount);
					}
				}
				
				
				writeSuccessMsg("修改成功", null, response);
//			}else{
//				writeErrorMsg("身份证号重复", null, response);
//			}
			
		} catch (Exception e)
		{
			e.printStackTrace();
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHAgentTwo", method = RequestMethod.POST)
	public String removeHAgentTwo(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HAgentTwo hagenttwo = new HAgentTwo();
			Integer id = RequestHandler.getInteger(request, "id");
			hagenttwo.setId(id);
			hagenttwo = hagenttwoService.getHAgentTwo(hagenttwo);
			hagenttwoService.removeHAgentTwo(hagenttwo);
			if(hagenttwo!=null){
				if(StringUtils.isNotBlank(hagenttwo.getMobile1())){
					ManageAdminUser user = new ManageAdminUser();
					user.setAdminName(hagenttwo.getMobile1());
					user.setRoleId(Integer.valueOf(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID));
					user = manageadminuserService.getManageAdminUser(user);
					if(user!=null){
						manageadminuserService.removeManageAdminUser(user);
					}
				}
				//删除绑定
				HVerificate hVerificate2 = new HVerificate();
				hVerificate2.setAgentOpenId(hagenttwo.getOpenId());
				hverificateService.removeHVerificate(hVerificate2);
			}
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHAgentTwo", method = RequestMethod.POST)
	public String removeAllHAgentTwo(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						HAgentTwo hAgentTwo = new HAgentTwo();
						hAgentTwo.setId(Integer.valueOf(id));
						hagenttwoService.removeHAgentTwo(hAgentTwo);
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
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @description 审核
	 * @return
	 */
	@RequestMapping(value = "/review", method = RequestMethod.POST)
	public String review(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try{
			HAgentTwo hagent = new HAgentTwo();
			Integer id = RequestHandler.getInteger(request, "id");
			hagent.setId(id);
			hagent = hagenttwoService.getHAgentTwo(hagent);
			if(hagent!=null){
				hagent.setCheck_status(1);
				hagenttwoService.updateHAgentTwo(hagent);
				//判断二维码是否存在
				HEwm hEwm = new HEwm();
				hEwm.setOpenId(hagent.getOpenId());
				hEwm.setStyle(2);
				HEwm ewm = hEwmService.getHEwm(hEwm);
				if(ewm!=null&&ewm.getId()>0){
					
				}else{
					Map map  = hEwmService.getEWMYj(FileUploadConstants.APPID, FileUploadConstants.APPSECRET, hagent.getOpenId());
					hEwmService.saveEwmYj(map.get("imgurl").toString(), map.get("ticket").toString(), hagent.getOpenId());
				}
			}
			writeSuccessMsg("成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/qrcode", method = RequestMethod.POST)
	public String qrcode(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try{
			HAgentTwo hagent = new HAgentTwo();
			Integer id = RequestHandler.getInteger(request, "id");
			hagent.setId(id);
			hagent = hagenttwoService.getHAgentTwo(hagent);
			Map map = new HashMap();
			if(hagent!=null){
				HEwm e  = hEwmService.getHEwmByOpenId(hagent.getOpenId());
				String url = e.getImgUrl();
				String contextPath = request.getSession().getServletContext().getRealPath("/");
				FileUtils.downPic(contextPath+File.separator+"download"+File.separator + hagent.getName()+hagent.getMobile1()+".jpg", url);
				map.put("url", url);
			}
			writeSuccessMsg("ok",map , response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @description 图片下载
	 * @return
	 */
	@RequestMapping(value = "/getpic", method = RequestMethod.GET)
	public String getpic(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try{
			HAgentTwo hagent = new HAgentTwo();
			Integer id = RequestHandler.getInteger(request, "id");
			hagent.setId(id);
			hagent = hagenttwoService.getHAgentTwo(hagent);
			String type = RequestHandler.getString(request, "type");
			if(hagent!=null&&StringUtils.isNotBlank(type)){
				Integer size = 0;
				String _size = "";
				switch(Integer.valueOf(type)){
				case 1 : 
					size = 302;
					_size = "8x8";
					break;
				case 2 : 
					size = 453;
					_size = "12x12";
					break;
				case 3 :
					size = 567;
					_size = "15x15";
					break;
				case 4:
					size = 1134;
					_size = "30x30";
					break;
				case 5:
					_size = "50x50";
					size = 1890;
					break;
				default:
					_size = "8x8";
					size = 302;
					break;
				}
				String contextPath = request.getSession().getServletContext().getRealPath("/");
				File in = new File(contextPath+File.separator+"download"+File.separator + hagent.getName()+hagent.getMobile1()+".jpg");
				if(!in.exists()){
					//二维码地址
					HEwm ewm = new HEwm();
					ewm.setOpenId(hagent.getOpenId());
					ewm.setStyle(2);
					HEwm ewm1 = hEwmService.getHEwm(ewm);
					FileUtils.downPic(contextPath+File.separator+"download"+File.separator + hagent.getName()+hagent.getMobile1()+".jpg", ewm1.getImgUrl());
					in = new File(contextPath+File.separator+"download"+File.separator + hagent.getName()+hagent.getMobile1()+".jpg");
				}
				File out = new File(contextPath+File.separator+"download"+File.separator + hagent.getName()+hagent.getMobile1()+"-"+_size+".jpg");
				FileUtils.resize(in, out, size, 1f);
				String fileName = hagent.getName()+hagent.getMobile1()+"-"+_size+".jpg";
				fileName = new String(fileName.getBytes("gb2312"), "ISO8859-1"); 
				response.setContentType("application/x-download");
				response.setHeader("Content-Disposition", "attachment;fileName="+fileName); 
				FileInputStream inputStream = new FileInputStream(out);  
				ServletOutputStream os; 
	            os = response.getOutputStream();
	            int b = 0;  
	            byte[] buffer = new byte[512];  
	            while (b != -1){  
	                b = inputStream.read(buffer);  
	                os.write(buffer,0,b);  
	            }  
	            inputStream.close();  
	            os.close();  
	            os.flush();  
			}
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/checkAgentTwoCard", method = RequestMethod.GET)
	public String checkAgentTwoCard(HttpServletRequest request, HttpServletResponse response, Model model){
		Integer id = RequestHandler.getInteger(request, "id");
		String card_no = RequestHandler.getString(request, "card_no");
		try{
			HAgentTwo hagent = new HAgentTwo();
			hagent.setId(id);
			hagent.setCard_no(card_no);
			boolean b = hagenttwoService.checkCard(hagent);
			if(b){
				writeSuccessMsg("ok",null , response);
			}else{
				writeSuccessMsg("no",null , response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/updateBankInfo", method = RequestMethod.POST)
	public String updateBankInfo(HttpServletRequest request, HttpServletResponse response, Model model){
		Integer id = RequestHandler.getInteger(request, "id");		
		Integer bankId = RequestHandler.getInteger(request, "bankId");		
		String subBankName = RequestHandler.getString(request, "subBankName");
		String bank_account = RequestHandler.getString(request, "bank_account");
		String bank_number = RequestHandler.getString(request, "bank_number");
		String contact_person = RequestHandler.getString(request, "contact_person");
		String name = RequestHandler.getString(request, "name");
		String mobile1 = RequestHandler.getString(request, "mobile1");
		Integer flag = RequestHandler.getInteger(request, "flag");
		try{
			
			HBank hBank = new HBank();
			hBank.setId(bankId);
			hBank = hbankService.getHBank(hBank);
			
			HAgentTwo hagent = new HAgentTwo();
			hagent.setId(id);
			hagent.setBankId(bankId);
			hagent.setBank_account(bank_account);
			hagent.setSubBankName(subBankName);
			hagent.setBank_name(hBank.getName());
			hagent.setBank_number(bank_number);
			hagent.setContact_person(contact_person);
			hagent.setName(name);
			hagent.setMobile1(mobile1);
			
			if(flag==1){
				if(StringUtils.isNotBlank(mobile1)){
					//查询手机是否存在
					int count = hagenttwoService.checkMobile1(hagent);
					if(count==0){
						//查询是否一级存在
						HAgent hAgent1 = new HAgent();
						hAgent1.setMobile1(mobile1);
						int count1 = hagentService.checkMobile1(hAgent1);
						if(count1==0){
							hagenttwoService.updateHAgentTwo(hagent);
							//修改对用用户手机号
							ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
							ManageAdminUser adminUser1 = new ManageAdminUser();
							adminUser1.setAdminName(mobile1);
							adminUser1.setPhone(mobile1);
							adminUser1.setAdminId(adminUser.getAdminId());
							manageadminuserService.updateManageAdminUser(adminUser1);
							writeSuccessMsg("ok",null , response);
						}else{
							writeSuccessMsg("mobileexit",null , response);
						}
					}else{
						writeSuccessMsg("mobileexit",null , response);
					}
				}else{
					writeSuccessMsg("mobilenotexit",null , response);
				}
			}else{
				hagenttwoService.updateHAgentTwo(hagent);
				writeSuccessMsg("ok",null , response);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/checkUserName", method = RequestMethod.POST)
	public void checkUserName(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try{
			String user_name = RequestHandler.getString(request, "user_name");
			Integer id = RequestHandler.getInteger(request, "id");
			
			if(hagenttwoService.checkUserName(id, user_name)){
				if(hagentService.checkUserName(null, user_name)){
					writeSuccessMsg("ok", null, response);
				}else{
					writeErrorMsg("error", null, response);
				}
			}else{
				writeErrorMsg("error", null, response);
			}
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
	}
	
	@RequestMapping(value = "/checkMobile", method = RequestMethod.POST)
	public String checkMobile(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try{
			String mobile = RequestHandler.getString(request, "mobile");
			Integer id = RequestHandler.getInteger(request, "id");
			HAgentTwo two = new HAgentTwo();
			two.setId(id);
			two.setMobile1(mobile);
			two.setMobile2(mobile);
			int counttwo = hagenttwoService.checkMobile1(two);
			if(counttwo>0){
				writeErrorMsg("error", null, response);
			}else{
				HAgent h = new HAgent();
				h.setMobile1(mobile);
				h.setMobile2(mobile);
				int count = hagentService.checkMobile1(h);
				if(count>0){
					writeErrorMsg("error", null, response);
				}else{
					writeSuccessMsg("ok", null, response);
				}
			}
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @description 
	 * @return
	 */
	@RequestMapping(value = "/setGlSJ", method = RequestMethod.POST)
	public String setGlSJ(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		Integer id = RequestHandler.getInteger(request, "id");
		Integer glsjid = RequestHandler.getInteger(request, "glsjid");
		String glsjname = RequestHandler.getString(request, "glsjname");
		try{
			HAgentTwo hagent = new HAgentTwo();
			hagent.setId(id);
			hagent.setGl_sj_id(glsjid);
			hagent.setGl_sj_name(glsjname);
			int count = hagenttwoService.updateHAgentTwo(hagent);
			if(count>0){
				writeSuccessMsg("成功", null, response);
			}else{
				writeErrorMsg("操作失败", null, response);
			}
		} catch (Exception e)
		{
			writeErrorMsg("操作失败", null, response);
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @description 
	 * @return
	 */
	@RequestMapping(value = "/setGlYL", method = RequestMethod.POST)
	public String setGlYL(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		Integer id = RequestHandler.getInteger(request, "id");
		Integer glylid = RequestHandler.getInteger(request, "glylid");
		String glylname = RequestHandler.getString(request, "glylname");
		try{
			HAgentTwo hagent = new HAgentTwo();
			hagent.setId(id);
			hagent.setGl_yl_id(glylid);
			hagent.setGl_yl_name(glylname);
			int count = hagenttwoService.updateHAgentTwo(hagent);
			if(count>0){
				writeSuccessMsg("成功", null, response);
			}else{
				writeErrorMsg("操作失败", null, response);
			}
		} catch (Exception e)
		{
			writeErrorMsg("操作失败", null, response);
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @description 
	 * @return
	 */
	@RequestMapping(value = "/setRegGl", method = RequestMethod.POST)
	public String setRegGl(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		Integer id = RequestHandler.getInteger(request, "id");
		Integer glylid = RequestHandler.getInteger(request, "glylid");
		String glylname = RequestHandler.getString(request, "glylname");
		try{
			HAgentTwo hagent = new HAgentTwo();
			hagent.setId(id);
			hagent.setReg_gl_id(glylid);
			hagent.setReg_gl_name(glylname);
			int count = hagenttwoService.updateHAgentTwo(hagent);
			if(count>0){
				writeSuccessMsg("成功", null, response);
			}else{
				writeErrorMsg("操作失败", null, response);
			}
		} catch (Exception e)
		{
			writeErrorMsg("操作失败", null, response);
			e.printStackTrace();
		}
		return null;
	}
}
