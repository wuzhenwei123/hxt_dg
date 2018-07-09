package com.hxt.hAgent.controller;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.base.controller.BaseController;
import com.base.utils.Common;
import com.base.utils.FileUploadConstants;
import com.base.utils.MD5;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.utils.SessionName;
import com.hxt.hAgent.model.HAgent;
import com.hxt.hAgent.service.HAgentService;
import com.hxt.hAgentTwo.model.HAgentTwo;
import com.hxt.hAgentTwo.service.HAgentTwoService;
import com.hxt.hCompany.model.HCompany;
import com.hxt.hCompany.service.HCompanyService;
import com.hxt.hEwm.model.HEwm;
import com.hxt.hEwm.service.HEwmService;
import com.hxt.hLoginLog.model.HLoginLog;
import com.hxt.hLoginLog.service.HLoginLogService;
import com.hxt.hPayGuli.model.HPayGuli;
import com.hxt.hPayGuli.service.HPayGuliService;
import com.hxt.hPresentApply.model.HPresentApply;
import com.hxt.hPresentApply.service.HPresentApplyService;
import com.hxt.hUserAccount.model.HUserAccount;
import com.hxt.hUserAccount.service.HUserAccountService;
import com.hxt.hVerificate.model.HVerificate;
import com.hxt.hVerificate.service.HVerificateService;
import com.sys.manageAdminUser.model.ManageAdminUser;
import com.sys.manageAdminUser.service.ManageAdminUserService;
import com.wx.service.WeiXinService;
import com.wx.utils.FileUtils;
/**
 * @author	zlb
 * @time	2016年08月07日 21:47:53
 */
@Controller
@RequestMapping("/hAgent")
public class HAgentController extends BaseController
{
	private static Logger logger = Logger.getLogger(HAgentController.class); //Logger
	//private static Logger logger = Logger.getLogger(HAgentController.class); //Logger
	
	@Autowired
	private HAgentService hagentService = null;
	@Autowired
	private WeiXinService weiXinService = null;
	@Autowired
	private ManageAdminUserService manageadminuserService = null;// 用户
	@Autowired
	private HAgentTwoService hagenttwoService = null;
	@Autowired
	private HEwmService hEwmService = null;
	@Autowired
	private HVerificateService hverificateService = null;
	@Autowired
	private HUserAccountService huseraccountService = null;
	
	@Autowired
	private HLoginLogService hloginlogService = null;
	@Autowired
	private HCompanyService hcompanyService = null;
	@Autowired
	private HPresentApplyService hpresentapplyService = null;
	@Autowired
	private HPayGuliService hpayguliService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hAgent/hAgentIndex";
	}
	/*****************页面中转*********************/
	@RequestMapping(value = "/indexPP", method = RequestMethod.GET)
	public String indexPP(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hAgentPP/hAgentIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hAgent/hAgentAdd";
	}
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HAgent hagent1 = new HAgent();
		hagent1.setId(id);
		HAgent hagent = hagentService.getHAgent(hagent1);
		model.addAttribute("hagent", hagent);
		ManageAdminUser manageAdminUser = new ManageAdminUser();
		manageAdminUser.setOneAgentOpenId(hagent.getOpenId());
		manageAdminUser.setRoleId(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
		manageAdminUser = manageadminuserService.getManageAdminUser(manageAdminUser);
		model.addAttribute("manageAdminUser", manageAdminUser);
		
		return "/hAgent/hAgentUpdate";
	}

	/************* Public Methods *************/
	@RequestMapping(value = "/checkUserName", method = RequestMethod.POST)
	public void checkUserName(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try{
			String user_name = RequestHandler.getString(request, "user_name");
			Integer id = RequestHandler.getInteger(request, "id");
			if(hagentService.checkUserName(id, user_name)){
				if(hagenttwoService.checkUserName(null, user_name)){
					//检查是否和注册单位手机一致
					HCompany hCompany = new HCompany();
					hCompany.setContact_phone(user_name);
					int count = hcompanyService.getHCompanyListCount(hCompany);
					if(count==0){
						writeSuccessMsg("ok", null, response);
					}else{
						writeErrorMsg("error", null, response);
					}
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
			HAgent h = new HAgent();
			h.setId(id);
			h.setMobile1(mobile);
			h.setMobile2(mobile);
			int count = hagentService.checkMobile1(h);
			if(count>0){
				writeErrorMsg("error", null, response);
			}else{
				HAgentTwo two = new HAgentTwo();
				two.setMobile1(mobile);
				two.setMobile2(mobile);
				int counttwo = hagenttwoService.checkMobile1(two);
				if(counttwo>0){
					writeErrorMsg("error", null, response);
				}else{
					writeSuccessMsg("ok", null, response);
				}
//					writeSuccessMsg("ok", null, response);
			}
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/getHAgentList", method = RequestMethod.GET)
	public String getHAgentList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HAgent hagent = new HAgent();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hagent.setId(id);
			
			String openId = RequestHandler.getString(request, "openId");
			hagent.setOpenId(openId);
			
			String name = RequestHandler.getString(request, "name");
			hagent.setName(name);
			
			String licence_url = RequestHandler.getString(request, "licence_url");
			hagent.setLicence_url(licence_url);
			
			String licence_code = RequestHandler.getString(request, "licence_code");
			hagent.setLicence_code(licence_code);
			
			String tax_url = RequestHandler.getString(request, "tax_url");
			hagent.setTax_url(tax_url);
			
			String legal_person = RequestHandler.getString(request, "legal_person");
			hagent.setLegal_person(legal_person);
			
			Integer card_style = RequestHandler.getInteger(request, "card_style");
			hagent.setCard_style(card_style);
			
			String card_no = RequestHandler.getString(request, "card_no");
			hagent.setCard_no(card_no);
			
			Integer sex = RequestHandler.getInteger(request, "sex");
			hagent.setSex(sex);
			
			String mobile1 = RequestHandler.getString(request, "mobile1");
			hagent.setMobile1(mobile1);
			
			String mobile2 = RequestHandler.getString(request, "mobile2");
			hagent.setMobile2(mobile2);
			
			Integer style = RequestHandler.getInteger(request, "style");
			hagent.setStyle(style);
			
			Integer status = RequestHandler.getInteger(request, "status");
			hagent.setStatus(status);
			
			Date create_time = RequestHandler.getDate(request, "create_time");
			hagent.setCreate_time(create_time);
			
			String create_openId = RequestHandler.getString(request, "create_openId");
			hagent.setCreate_openId(create_openId);
			
			Integer check_status = RequestHandler.getInteger(request, "check_status");
			hagent.setCheck_status(check_status);
			
			String contact_person = RequestHandler.getString(request, "contact_person");
			hagent.setContact_person(contact_person);
			
			String tax_code = RequestHandler.getString(request, "tax_code");
			hagent.setTax_code(tax_code);
			
			String bank_name = RequestHandler.getString(request, "bank_name");
			hagent.setBank_name(bank_name);
			
			String bank_number = RequestHandler.getString(request, "bank_number");
			hagent.setBank_number(bank_number);
			
			String bank_account = RequestHandler.getString(request, "bank_account");
			hagent.setBank_account(bank_account);
			
			String contract_number = RequestHandler.getString(request, "contract_number");
			hagent.setContract_number(contract_number);
			
			Date contract_start_time = RequestHandler.getDate(request, "contract_start_time");
			hagent.setContract_start_time(contract_start_time);
			
			Date contract_end_time = RequestHandler.getDate(request, "contract_end_time");
			hagent.setContract_end_time(contract_end_time);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hagent.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hagent.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hagent.setRemark3(remark3);
			String user_name = RequestHandler.getString(request, "user_name");
			hagent.setUser_name(user_name);
			

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hagent.setRowStart(from);
			hagent.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hagent.setSortColumn(sortColumn);
			
			int hagentListCount = hagentService.getHAgentListCount(hagent);
			ResponseList<HAgent> hagentList = null;
			if ( hagentListCount > 0 )
			{
				hagentList = hagentService.getHAgentList(hagent);
			} else
			{
				hagentList = new ResponseList<HAgent>();
			}
			// 设置数据总数
			hagentList.setTotalResults(hagentListCount);
			
			writeSuccessMsg("ok", hagentList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/getAgentPP", method = RequestMethod.GET)
	public String getAgentPP(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HAgent hagent = new HAgent();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hagent.setId(id);
			
			String openId = RequestHandler.getString(request, "openId");
			hagent.setOpenId(openId);
			
			String name = RequestHandler.getString(request, "name");
			hagent.setName(name);
			
			String licence_url = RequestHandler.getString(request, "licence_url");
			hagent.setLicence_url(licence_url);
			
			String licence_code = RequestHandler.getString(request, "licence_code");
			hagent.setLicence_code(licence_code);
			
			String tax_url = RequestHandler.getString(request, "tax_url");
			hagent.setTax_url(tax_url);
			
			String legal_person = RequestHandler.getString(request, "legal_person");
			hagent.setLegal_person(legal_person);
			
			Integer card_style = RequestHandler.getInteger(request, "card_style");
			hagent.setCard_style(card_style);
			
			String card_no = RequestHandler.getString(request, "card_no");
			hagent.setCard_no(card_no);
			
			Integer sex = RequestHandler.getInteger(request, "sex");
			hagent.setSex(sex);
			
			String mobile1 = RequestHandler.getString(request, "mobile1");
			hagent.setMobile1(mobile1);
			
			String mobile2 = RequestHandler.getString(request, "mobile2");
			hagent.setMobile2(mobile2);
			
			Integer style = RequestHandler.getInteger(request, "style");
			hagent.setStyle(style);
			
			Integer status = RequestHandler.getInteger(request, "status");
			hagent.setStatus(status);
			
			Date create_time = RequestHandler.getDate(request, "create_time");
			hagent.setCreate_time(create_time);
			
			String create_openId = RequestHandler.getString(request, "create_openId");
			hagent.setCreate_openId(create_openId);
			
			Integer check_status = RequestHandler.getInteger(request, "check_status");
			hagent.setCheck_status(check_status);
			
			String contact_person = RequestHandler.getString(request, "contact_person");
			hagent.setContact_person(contact_person);
			
			String tax_code = RequestHandler.getString(request, "tax_code");
			hagent.setTax_code(tax_code);
			
			String bank_name = RequestHandler.getString(request, "bank_name");
			hagent.setBank_name(bank_name);
			
			String bank_number = RequestHandler.getString(request, "bank_number");
			hagent.setBank_number(bank_number);
			
			String bank_account = RequestHandler.getString(request, "bank_account");
			hagent.setBank_account(bank_account);
			
			String contract_number = RequestHandler.getString(request, "contract_number");
			hagent.setContract_number(contract_number);
			
			Date contract_start_time = RequestHandler.getDate(request, "contract_start_time");
			hagent.setContract_start_time(contract_start_time);
			
			Date contract_end_time = RequestHandler.getDate(request, "contract_end_time");
			hagent.setContract_end_time(contract_end_time);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hagent.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hagent.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hagent.setRemark3(remark3);
			String user_name = RequestHandler.getString(request, "user_name");
			hagent.setUser_name(user_name);
			
			
			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hagent.setRowStart(from);
			hagent.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hagent.setSortColumn(sortColumn);
			
			int hagentListCount = hagentService.getAgentPPCount(hagent);
			ResponseList<HAgent> hagentList = null;
			if ( hagentListCount > 0 )
			{
				hagentList = hagentService.getAgentPP(hagent);
			} else
			{
				hagentList = new ResponseList<HAgent>();
			}
			// 设置数据总数
			hagentList.setTotalResults(hagentListCount);
			
			writeSuccessMsg("ok", hagentList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getHAgentBaseList", method = RequestMethod.GET)
	public String getHAgentBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HAgent hagent = new HAgent();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hagent.setId(id);
			
			String openId = RequestHandler.getString(request, "openId");
			hagent.setOpenId(openId);
			
			String name = RequestHandler.getString(request, "name");
			hagent.setName(name);
			
			String licence_url = RequestHandler.getString(request, "licence_url");
			hagent.setLicence_url(licence_url);
			
			String licence_code = RequestHandler.getString(request, "licence_code");
			hagent.setLicence_code(licence_code);
			
			String tax_url = RequestHandler.getString(request, "tax_url");
			hagent.setTax_url(tax_url);
			
			String legal_person = RequestHandler.getString(request, "legal_person");
			hagent.setLegal_person(legal_person);
			
			Integer card_style = RequestHandler.getInteger(request, "card_style");
			hagent.setCard_style(card_style);
			
			String card_no = RequestHandler.getString(request, "card_no");
			hagent.setCard_no(card_no);
			
			Integer sex = RequestHandler.getInteger(request, "sex");
			hagent.setSex(sex);
			
			String mobile1 = RequestHandler.getString(request, "mobile1");
			hagent.setMobile1(mobile1);
			
			String mobile2 = RequestHandler.getString(request, "mobile2");
			hagent.setMobile2(mobile2);
			
			Integer style = RequestHandler.getInteger(request, "style");
			hagent.setStyle(style);
			
			Integer status = RequestHandler.getInteger(request, "status");
			hagent.setStatus(status);
			
			Date create_time = RequestHandler.getDate(request, "create_time");
			hagent.setCreate_time(create_time);
			
			String create_openId = RequestHandler.getString(request, "create_openId");
			hagent.setCreate_openId(create_openId);
			
			Integer check_status = RequestHandler.getInteger(request, "check_status");
			hagent.setCheck_status(check_status);
			
			String contact_person = RequestHandler.getString(request, "contact_person");
			hagent.setContact_person(contact_person);
			
			String tax_code = RequestHandler.getString(request, "tax_code");
			hagent.setTax_code(tax_code);
			
			String bank_name = RequestHandler.getString(request, "bank_name");
			hagent.setBank_name(bank_name);
			
			String bank_number = RequestHandler.getString(request, "bank_number");
			hagent.setBank_number(bank_number);
			
			String bank_account = RequestHandler.getString(request, "bank_account");
			hagent.setBank_account(bank_account);
			
			String contract_number = RequestHandler.getString(request, "contract_number");
			hagent.setContract_number(contract_number);
			
			Date contract_start_time = RequestHandler.getDate(request, "contract_start_time");
			hagent.setContract_start_time(contract_start_time);
			
			Date contract_end_time = RequestHandler.getDate(request, "contract_end_time");
			hagent.setContract_end_time(contract_end_time);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hagent.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hagent.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hagent.setRemark3(remark3);
			String user_name = RequestHandler.getString(request, "user_name");
			hagent.setUser_name(user_name);
			List<HAgent> hagentList = hagentService.getHAgentBaseList(hagent);
		
			writeSuccessMsg("ok", hagentList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHAgent", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			HAgent hagent = new HAgent();
			String uuid = UUID.randomUUID().toString();
			uuid = uuid.replaceAll("-","");
			hagent.setOpenId(uuid);
			String name = RequestHandler.getString(request, "name");
			hagent.setName(name);
			String licence_url = RequestHandler.getString(request, "licence_url");
			hagent.setLicence_url(licence_url);
			String licence_code = RequestHandler.getString(request, "licence_code");
			hagent.setLicence_code(licence_code);
			String tax_url = RequestHandler.getString(request, "tax_url");
			hagent.setTax_url(tax_url);
			String legal_person = RequestHandler.getString(request, "legal_person");
			hagent.setLegal_person(legal_person);
			Integer card_style = RequestHandler.getInteger(request, "card_style");
			hagent.setCard_style(card_style);
			String card_no = RequestHandler.getString(request, "card_no");
			hagent.setCard_no(card_no);
			Integer sex = RequestHandler.getInteger(request, "sex");
			hagent.setSex(sex);
			String mobile1 = RequestHandler.getString(request, "mobile1");
			hagent.setMobile1(mobile1);
			String mobile2 = RequestHandler.getString(request, "mobile2");
			hagent.setMobile2(mobile2);
			Integer style = RequestHandler.getInteger(request, "style");
			hagent.setStyle(style);
			Integer status = RequestHandler.getInteger(request, "status");
			hagent.setStatus(status);
			hagent.setCreate_time(new Date());
			String create_openId = RequestHandler.getString(request, "create_openId");
			hagent.setCreate_openId(create_openId);
			Integer check_status = RequestHandler.getInteger(request, "check_status");
			hagent.setCheck_status(check_status);
			String contact_person = RequestHandler.getString(request, "contact_person");
			hagent.setContact_person(contact_person);
			String tax_code = RequestHandler.getString(request, "tax_code");
			hagent.setTax_code(tax_code);
			String bank_name = RequestHandler.getString(request, "bank_name");
			hagent.setBank_name(bank_name);
			String bank_number = RequestHandler.getString(request, "bank_number");
			hagent.setBank_number(bank_number);
			String bank_account = RequestHandler.getString(request, "bank_account");
			hagent.setBank_account(bank_account);
			String contract_number = RequestHandler.getString(request, "contract_number");
			hagent.setContract_number(contract_number);
			Date contract_start_time = RequestHandler.getDate(request, "contract_start_time");
			hagent.setContract_start_time(contract_start_time);
			Date contract_end_time = RequestHandler.getDate(request, "contract_end_time");
			hagent.setContract_end_time(contract_end_time);
			String remark1 = RequestHandler.getString(request, "remark1");
			hagent.setRemark1(remark1);
			String remark2 = RequestHandler.getString(request, "remark2");
			hagent.setRemark2(remark2);
			String remark3 = RequestHandler.getString(request, "remark3");
			hagent.setRemark3(remark3);
			String user_name = RequestHandler.getString(request, "user_name");
			hagent.setUser_name(user_name);
			
			//获取默认银联支付鼓励金
			HPayGuli hPayGuli = new HPayGuli();
			hPayGuli.setIs_default(1);
			hPayGuli.setOpObject(1);
			hPayGuli.setState(1);
			List<HPayGuli> listpaygl = hpayguliService.getHPayGuliBaseList(hPayGuli);
			if(listpaygl!=null&&listpaygl.size()>0){
				for(HPayGuli paygl:listpaygl){
					if(paygl.getStyle()==1){//银联
						hagent.setGl_yl_id(paygl.getId());
						hagent.setGl_yl_name(paygl.getName());
					}else if(paygl.getStyle()==2){
						hagent.setGl_sj_id(paygl.getId());
						hagent.setGl_sj_name(paygl.getName());
					}
				}
			}
			
			hagentService.insertHAgent(hagent);
			
			manageadminuserService.saveAgentManage2(mobile1,user_name, user_name, hagent.getOpenId(), null, Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID),Integer.valueOf(getSession(request, SessionName.ADMIN_USER_ID).toString()),name,null);
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			e.printStackTrace();
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	//图片上传
	@RequestMapping(value = "/uploadPic")
	public String uploadPic(HttpServletRequest req, HttpServletResponse response, Model model){
		JSONObject json = new JSONObject();
		try {
			if(req instanceof MultipartHttpServletRequest){
				
				MultipartHttpServletRequest request = (MultipartHttpServletRequest)req;
				MultipartFile imgFile = request.getFile("file");
				
				String type = request.getParameter("type");
				if(null == type || "".equals(type)){
					type = "jpg";
				}
				if(type.toLowerCase().contains("jpg")||type.toLowerCase().contains("jpeg")){
					type = "jpg";
				}
				if(type.toLowerCase().contains("gif")){
					type = "gif";
				}
				if(type.toLowerCase().contains("bmp")){
					type = "bmp";
				}
				if(type.toLowerCase().contains("png")){
					type = "png";
				}
				
				if(type.toLowerCase().contains("application/msword")){
					type = "doc";
				}
				
				if(type.toLowerCase().contains("application/pdf")){
					type = "pdf";
				}
				if(type.toLowerCase().contains("application/vnd.ms-powerpoint")){
					type = "ppt";
				}
				
				if(type.toLowerCase().contains("application/vnd.openxmlformats-officedocument.wordprocessingml.document")){
					type = "docx";
				}
				if(type.toLowerCase().contains("application/vnd.openxmlformats-officedocument.presentationml.presentation")){
					type = "pptx";
				}
				
				String uuid = UUID.randomUUID().toString().replace("-", "");	// 惟一ID
				
				String saveFile = uuid + "."+ type.toLowerCase();		// 图片名称
				Long times = System.currentTimeMillis();
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); 
			
				String datedir = sdf.format(new Date(times));
				
				//等于配置的路径  加 当前日期格式化
				String realPath = "/" + "upload" + "/" + datedir;
				String contextPath = request.getSession().getServletContext().getRealPath("/");
				if(FileUploadConstants.mkdirs(contextPath+realPath)){
					String filePath = FileUploadConstants.parseToPath(contextPath + "/" + realPath +"/" + saveFile);
					FileUploadConstants.saveFile(imgFile.getInputStream(), filePath);
					
					json.put("serverfileName", realPath +"/" + saveFile);
					
					//json.put("filename", imgFile.getOriginalFilename());
					
				}else{
					System.out.println("------路径不存在----------");
				}
				
			}
			response.getWriter().write(json.toString());
		} catch (Exception e) {
			System.out.println("上传图片出现异常：");
			e.printStackTrace();
		}
		 return null;
	}
	@RequestMapping(value = "/updateHAgent", method = RequestMethod.POST)
	public String updateHAgent(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HAgent hagent = new HAgent();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hagent.setId(id);
			
			String openId = RequestHandler.getString(request, "openId");
			hagent.setOpenId(openId);
			
			String name = RequestHandler.getString(request, "name");
			hagent.setName(name);
			
			String licence_url = RequestHandler.getString(request, "licence_url");
			hagent.setLicence_url(licence_url);
			
			String licence_code = RequestHandler.getString(request, "licence_code");
			hagent.setLicence_code(licence_code);
			
			String tax_url = RequestHandler.getString(request, "tax_url");
			hagent.setTax_url(tax_url);
			
			String legal_person = RequestHandler.getString(request, "legal_person");
			hagent.setLegal_person(legal_person);
			
			Integer card_style = RequestHandler.getInteger(request, "card_style");
			hagent.setCard_style(card_style);
			
			String card_no = RequestHandler.getString(request, "card_no");
			hagent.setCard_no(card_no);
			
			Integer sex = RequestHandler.getInteger(request, "sex");
			hagent.setSex(sex);
			
			String mobile1 = RequestHandler.getString(request, "mobile1");
			hagent.setMobile1(mobile1);
			
			String mobile2 = RequestHandler.getString(request, "mobile2");
			hagent.setMobile2(mobile2);
			
			Integer style = RequestHandler.getInteger(request, "style");
			hagent.setStyle(style);
			
			Integer status = RequestHandler.getInteger(request, "status");
			hagent.setStatus(status);
			
			Date create_time = RequestHandler.getDate(request, "create_time");
			hagent.setCreate_time(create_time);
			
			String create_openId = RequestHandler.getString(request, "create_openId");
			hagent.setCreate_openId(create_openId);
			
			Integer check_status = RequestHandler.getInteger(request, "check_status");
			hagent.setCheck_status(check_status);
			
			String contact_person = RequestHandler.getString(request, "contact_person");
			hagent.setContact_person(contact_person);
			
			String tax_code = RequestHandler.getString(request, "tax_code");
			hagent.setTax_code(tax_code);
			
			String bank_name = RequestHandler.getString(request, "bank_name");
			hagent.setBank_name(bank_name);
			
			String bank_number = RequestHandler.getString(request, "bank_number");
			hagent.setBank_number(bank_number);
			
			String bank_account = RequestHandler.getString(request, "bank_account");
			hagent.setBank_account(bank_account);
			
			String contract_number = RequestHandler.getString(request, "contract_number");
			hagent.setContract_number(contract_number);
			
			Date contract_start_time = RequestHandler.getDate(request, "contract_start_time");
			hagent.setContract_start_time(contract_start_time);
			
			Date contract_end_time = RequestHandler.getDate(request, "contract_end_time");
			hagent.setContract_end_time(contract_end_time);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hagent.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hagent.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hagent.setRemark3(remark3);
			String user_name = RequestHandler.getString(request, "user_name");
			hagent.setUser_name(user_name);

			HAgent hAgent = new HAgent();
			hAgent.setId(id);
			HAgent hAgent1 = hagentService.getHAgent(hAgent);//原来代理
			
			int ss = hagentService.updateHAgent(hagent);
			
			ManageAdminUser manageadminuser = new ManageAdminUser();
			manageadminuser.setRoleId(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
			manageadminuser.setOneAgentOpenId(hAgent1.getOpenId());
			manageadminuser = manageadminuserService.getManageAdminUser(manageadminuser);
			if(manageadminuser!=null){
				manageadminuser.setOneAgentOpenId(hagent.getOpenId());
				manageadminuser.setOneAgentName(name);
				manageadminuser.setPhone(hagent.getMobile1());
				if(hAgent1.getMobile1()!=null){
					if( !hAgent1.getMobile1().equals(mobile1)){//修改了手机
						String passwd = mobile1.substring(mobile1.length()-6, mobile1.length());
		    			manageadminuser.setPasswd(new MD5().getMD5ofStr(passwd));//如果修改了手机，将要修改密码
					}
				}
				
				manageadminuserService.updateManageAdminUser(manageadminuser);
				
			}
			
			if(ss>0){
				HCompany hCompany = new HCompany();
				hCompany.setOneAgentOpenId(hAgent1.getOpenId());
				hCompany.setOneAgentName(name);
				hcompanyService.updateOneAgentName(hCompany);
			}
			
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			e.printStackTrace();
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHAgent", method = RequestMethod.POST)
	public String removeHAgent(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HAgent hagent = new HAgent();
			Integer id = RequestHandler.getInteger(request, "id");
			hagent.setId(id);
			hagent = hagentService.getHAgent(hagent);
			hagentService.removeHAgent(hagent);
			if(hagent!=null){
				if(StringUtils.isNotBlank(hagent.getMobile1())){
					ManageAdminUser user = new ManageAdminUser();
					user.setAdminName(hagent.getMobile1());
					user = manageadminuserService.getManageAdminUser(user);
					if(user!=null){
						manageadminuserService.removeManageAdminUser(user);
					}
				}
				//删除绑定
				HVerificate hVerificate2 = new HVerificate();
				hVerificate2.setAgentOpenId(hagent.getOpenId());
				hverificateService.removeHVerificate(hVerificate2);
			}
			//删除用户
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHAgent", method = RequestMethod.POST)
	public String removeAllHAgent(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						HAgent hAgent = new HAgent();
						hAgent.setId(Integer.valueOf(id));
						hagentService.removeHAgent(hAgent);
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
			HAgent hagent = new HAgent();
			Integer id = RequestHandler.getInteger(request, "id");
			hagent.setId(id);
			hagent = hagentService.getHAgent(hagent);
			if(hagent!=null){
				hagent.setCheck_status(1);
				hagentService.updateHAgent(hagent);
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
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @description 冻结
	 * @return
	 */
	@RequestMapping(value = "/qrcode", method = RequestMethod.POST)
	public String qrcode(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try{
			HAgent hagent = new HAgent();
			Integer id = RequestHandler.getInteger(request, "id");
			hagent.setId(id);
			hagent = hagentService.getHAgent(hagent);
			Map map = new HashMap();
			if(hagent!=null){
				if(StringUtils.isBlank(hagent.getOpenId())){
					writeErrorMsg("该代理未绑定微信，不能生成二维码！", null, response);
				}else{
					HEwm e  = hEwmService.getHEwmByOpenId(hagent.getOpenId());
					String url = e.getImgUrl();
					map.put("url", url);
					String contextPath = request.getSession().getServletContext().getRealPath("/");
					FileUtils.downPic(contextPath+"/"+"download"+"/" + hagent.getName()+hagent.getMobile1()+".jpg", url);
	//				map.put("url", url);
				}
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
			HAgent hagent = new HAgent();
			Integer id = RequestHandler.getInteger(request, "id");
			hagent.setId(id);
			hagent = hagentService.getHAgent(hagent);
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
				File in = new File(contextPath+"/"+"download"+"/" + hagent.getName()+hagent.getMobile1()+".jpg");
				if(!in.exists()){
					//二维码地址
					HEwm ewm = new HEwm();
					ewm.setStyle(2);
					ewm.setOpenId(hagent.getOpenId());
					HEwm ewm1 = hEwmService.getHEwm(ewm);
					FileUtils.downPic(contextPath+"/"+"download"+"/" + hagent.getName()+hagent.getMobile1()+".jpg", ewm1.getImgUrl());
					in = new File(contextPath+"/"+"download"+"/" + hagent.getName()+hagent.getMobile1()+".jpg");
				}
				File out = new File(contextPath+"/"+"download"+"/" + hagent.getName()+hagent.getMobile1()+"-"+_size+".jpg");
				FileUtils.resize(in, out, size, 1f);
				/*response.setContentType("application/x-download")
				response.setHeader("Content-Disposition", "attachment;fileName="+hagent.getName()+hagent.getMobile1()+"-"+_size+".jpg"); 
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
	            os.flush();  */
				model.addAttribute("name",hagent.getName()+hagent.getMobile1()+"-"+_size+".jpg");
				model.addAttribute("link", contextPath+"/"+"download"+"/" + hagent.getName()+hagent.getMobile1()+"-"+_size+".jpg");
			}
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return "/hAgent/excelup";
	}
	
	
	/** 微信手机验证一级代理页面 **/
	@RequestMapping(value = "/toIndex")
	public String toIndex(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		super.getJsticket(request);
		String nickName =  RequestHandler.getString(request, "nickName");
		String openId =  RequestHandler.getString(request, "openId");
		String agentOpenId = null;
		model.addAttribute("nickName", nickName);
//		Object obj = (Object)request.getSession().getAttribute(SessionName.ADMIN_USER);
//		if(obj!=null){
//			logger.info("---------session判断---------->");
//			ManageAdminUser adminUser = (ManageAdminUser)obj;
//			openId = adminUser.getOpenId();
//		}
		logger.info("---------代理绑定---------->"+openId);
		try{
			//判断是否验证
			int b = hverificateService.isVerficate(openId);
			logger.info("---------b---------->"+b);
			if(b==1){
				
				//获取余额
				boolean bc = true;
				String roleId = null;
				HVerificate hVerificate = new HVerificate();
				hVerificate.setOpenId(openId);
				hVerificate.setState(1);
				HVerificate ver = hverificateService.getHVerificate(hVerificate);
				SimpleDateFormat sf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
				if(ver.getLevel()==1){
					agentOpenId = ver.getAgentOpenId();
					roleId = FileUploadConstants.ONE_AGENT_MANAGE_ROLEID;
					//获取所属的机构状态
					ManageAdminUser adminUser = new ManageAdminUser();
					adminUser.setOpenId(openId);
					adminUser.setRoleId(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
					adminUser = manageadminuserService.getManageAdminUser(adminUser);
					HAgent hAgent = new HAgent();
    				hAgent.setOpenId(ver.getAgentOpenId());
    				hAgent = hagentService.getHAgent(hAgent);
    				if(hAgent!=null&&hAgent.getStatus()==1&&hAgent.getCheck_status()==1&&adminUser!=null&&adminUser.getState()==1){
    					
    				}else{
    					bc = false;
    				}
				}else if(ver.getLevel()==2){
					agentOpenId = ver.getAgentOpenId();
					roleId = FileUploadConstants.TWO_AGENT_MANAGE_ROLEID;
					//获取所属的机构状态
					ManageAdminUser adminUser = new ManageAdminUser();
					adminUser.setOpenId(openId);
					adminUser.setRoleId(Integer.valueOf(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID));
					logger.info("---------openId-----------"+openId);
					logger.info("---------openId-----------"+FileUploadConstants.SERVICER_MANAGE_ROLEID);
					adminUser = manageadminuserService.getManageAdminUser(adminUser);
            	  	HAgentTwo hAgentTwo = new HAgentTwo();
            	  	hAgentTwo.setOpenId(ver.getAgentOpenId());
            	  	hAgentTwo = hagenttwoService.getHAgentTwo(hAgentTwo);
            	  	if(hAgentTwo!=null&&hAgentTwo.getStatus()==1&&hAgentTwo.getCheck_status()==1&&adminUser!=null&&adminUser.getState()==1){
            	  		model.addAttribute("bankFlag", hAgentTwo.getBank_number());
    				}else{
    					bc = false;
    				}
				}else if(ver.getLevel()==3){
					agentOpenId = ver.getAgentOpenId();
					roleId = FileUploadConstants.SERVICER_MANAGE_ROLEID;
					ManageAdminUser adminUser = new ManageAdminUser();
					adminUser.setOpenId(openId);
					adminUser.setRoleId(Integer.valueOf(FileUploadConstants.SERVICER_MANAGE_ROLEID));
					adminUser = manageadminuserService.getManageAdminUser(adminUser);
					if(adminUser!=null&&adminUser.getState()==1){
						
					}else{
						bc = false;
					}
				}
				if(bc){
					//查询账户余额
					HUserAccount hUserAccount = new HUserAccount();
					hUserAccount.setOpenId(openId);
					hUserAccount = huseraccountService.getHUserAccount(hUserAccount);
					if(hUserAccount!=null){
//						String TotalFeestr = String.valueOf(hUserAccount.getTotalFee());
//						if(TotalFeestr.length()>2){
//							int fen = hUserAccount.getTotalFee().intValue()%100;
//							if(fen>0&&fen<10){
//								TotalFeestr = hUserAccount.getTotalFee().intValue()/100 + ".0" + fen;
//							}else if(fen>=10){
//								TotalFeestr = hUserAccount.getTotalFee().intValue()/100 + "." + fen;
//							}else{
//								TotalFeestr = hUserAccount.getTotalFee().intValue()/100 + ".00";
//							}
//						}else if(TotalFeestr.length()==1){
//							TotalFeestr = "0.0"+TotalFeestr;
//						}else{
//							TotalFeestr = "0."+TotalFeestr;
//						}
						model.addAttribute("myMoney", hUserAccount.getTotalFee());
					}
					weiXinService.isBind(openId,request,roleId);
					model.addAttribute("roleId", roleId);
					model.addAttribute("openId", openId);
					model.addAttribute("agentOpenId", agentOpenId);
					return "/wx/myInfo";
				}else{
					return "/wx/405";
				}
			}else if(b==-1){
				model.addAttribute("openId", openId);
			}else{
				if(b==2){
					return "/wx/405";
				}else if(b==0){
					return "/wx/405";
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return "/wx/405";
		}
		return "/wx/bind";
	}
	
	
	@RequestMapping(value = "/verify", method = RequestMethod.POST)
	public String verify(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String phone = RequestHandler.getString(request, "phone");
			String pwd = RequestHandler.getString(request, "pwd");
			String openId = RequestHandler.getString(request, "openId");
			if(StringUtils.isNotBlank(phone) && StringUtils.isNotBlank(pwd) && StringUtils.isNotBlank(openId)){
				
			}else{
				writeSuccessMsg("-4", null, response);
				return null;
			}
			HPresentApply hPresentApply = new HPresentApply();
			ManageAdminUser manageAdminUser = new ManageAdminUser();
			manageAdminUser.setAdminName(phone);
			manageAdminUser.setPasswd(MD5.getMD5ofStr(pwd));
//			manageAdminUser.setState(1);
			manageAdminUser = manageadminuserService.getAdminUserByLogin(manageAdminUser);
			HUserAccount hUserAccount = new HUserAccount();
			HUserAccount hUserAccount1 = new HUserAccount();
			Integer daili = 1;
//			hUserAccount1.setCompany_name(manageAdminUser.getCompanyName());
			if(manageAdminUser!=null){
				//判断所在机构
				if(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID.equals((manageAdminUser.getRoleId()+""))){
					//判断机构是否已经被绑定
					HVerificate hVerificate = new HVerificate();
					hVerificate.setState(1);
					hVerificate.setAgentOpenId(manageAdminUser.getOneAgentOpenId());
					int count = hverificateService.getHVerificateListCount(hVerificate);
					if(count>0){
						writeSuccessMsg("-5", null, response);
						return null;
					}
				}else if(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID.equals((manageAdminUser.getRoleId()+""))){
					//判断机构是否已经被绑定
					HVerificate hVerificate = new HVerificate();
					hVerificate.setState(1);
					hVerificate.setAgentOpenId(manageAdminUser.getTwoAgentOpenId());
					int count = hverificateService.getHVerificateListCount(hVerificate);
					if(count>0){
						writeSuccessMsg("-5", null, response);
						return null;
					}
				}else if(FileUploadConstants.SERVICER_MANAGE_ROLEID.equals((manageAdminUser.getRoleId()+""))){
					//判断机构是否已经被绑定
					if(manageAdminUser.getOpenId()!=null){
						HVerificate hVerificate = new HVerificate();
						hVerificate.setState(1);
						hVerificate.setAgentOpenId(manageAdminUser.getOpenId());
						int count = hverificateService.getHVerificateListCount(hVerificate);
						if(count>0){
							writeSuccessMsg("-5", null, response);
							return null;
						}
					}
					
				}else{
					writeSuccessMsg("-5", null, response);
					return null;
				}
				
				if(manageAdminUser.getState()==1){
					hUserAccount1.setPhone(manageAdminUser.getMobile());
					if(manageAdminUser.getState()==1){
						//成功
						HVerificate hVerificate = new HVerificate();
						
						boolean b = true;//角色标识
						if(manageAdminUser.getRoleId().equals(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID))){
							hVerificate.setAgentOpenId(manageAdminUser.getOneAgentOpenId());
							hVerificate.setLevel(1);
							hUserAccount.setOneAgentOpenId(manageAdminUser.getOneAgentOpenId());
							hUserAccount.setRole_id(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
							hUserAccount1.setOneAgentOpenId(manageAdminUser.getOneAgentOpenId());
							hUserAccount1.setRole_id(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
							hUserAccount1.setOneAgentName(manageAdminUser.getAdminName());
							//获取所属的机构状态
							HAgent hAgent = new HAgent();
	 	    				hAgent.setOpenId(manageAdminUser.getOneAgentOpenId());
	 	    				hAgent = hagentService.getHAgent(hAgent);
	 	    				if(hAgent!=null&&hAgent.getStatus()==1&&hAgent.getCheck_status()==1){
	 	    					daili = hAgent.getStyle();
	 	    				}else{
	 	    					b = false;
	 	    					writeSuccessMsg("-6", null, response);
	 	    				}
	 	    				hPresentApply.setOneAgentOpenId(manageAdminUser.getOneAgentOpenId());
						}else if(manageAdminUser.getRoleId().equals(Integer.valueOf(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID))){
							hVerificate.setAgentOpenId(manageAdminUser.getTwoAgentOpenId());
							hVerificate.setLevel(2);
							hUserAccount.setOneAgentOpenId(manageAdminUser.getOneAgentOpenId());
							hUserAccount.setTwoAgentOpenId(manageAdminUser.getTwoAgentOpenId());
							hUserAccount.setRole_id(Integer.valueOf(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID));
							hUserAccount1.setOneAgentOpenId(manageAdminUser.getOneAgentOpenId());
							hUserAccount1.setTwoAgentOpenId(manageAdminUser.getTwoAgentOpenId());
							hUserAccount1.setRole_id(Integer.valueOf(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID));
							hUserAccount1.setOneAgentName(manageAdminUser.getOneAgentName());
							hUserAccount1.setTwoAgentName(manageAdminUser.getTwoAgentName());
							//获取所属的机构状态
	                	  	HAgentTwo hAgentTwo = new HAgentTwo();
	                	  	hAgentTwo.setOpenId(manageAdminUser.getTwoAgentOpenId());
	                	  	hAgentTwo = hagenttwoService.getHAgentTwo(hAgentTwo);
	                	  	if(hAgentTwo!=null&&hAgentTwo.getStatus()==1&&hAgentTwo.getCheck_status()==1){
	                	  		daili = hAgentTwo.getStyle();
	 	    				}else{
	 	    					b = false;
	 	    					writeSuccessMsg("-6", null, response);
	 	    				}
	                	  	hPresentApply.setTwoAgentOpenId(manageAdminUser.getTwoAgentOpenId());
						}else if(manageAdminUser.getRoleId().equals(Integer.valueOf(FileUploadConstants.SERVICER_MANAGE_ROLEID))){
							hVerificate.setAgentOpenId(openId);
							hVerificate.setLevel(3);
							hUserAccount.setServicerId(manageAdminUser.getAdminId());
							hUserAccount.setRole_id(Integer.valueOf(FileUploadConstants.SERVICER_MANAGE_ROLEID));
							hUserAccount1.setServicerId(manageAdminUser.getAdminId());
							hUserAccount1.setRole_id(Integer.valueOf(FileUploadConstants.SERVICER_MANAGE_ROLEID));
							hUserAccount1.setServicerName(manageAdminUser.getAdminName());
							//获取所属的机构状态
							hPresentApply.setServicerId(manageAdminUser.getAdminId());
						}else{
							b = false;
						}
						if(b){
							
//							HVerificate hVerificate1 = hverificateService.getHVerificate(hVerificate);
							
							HVerificate hVerificate2 = new HVerificate();
							hVerificate2.setOpenId(openId);
							int count = hverificateService.getHVerificateListCount(hVerificate2);
							if(count>0){
								writeSuccessMsg("-5", null, response);
								return null;
							}else{
								hVerificate.setOpenId(openId);
								hVerificate.setPhone(phone);
								hVerificate.setState(1);
								hVerificate.setCreateTime(new Date());
								hverificateService.insertHVerificate(hVerificate);
							}
//							if(hVerificate1!=null){
//								if(!openId.equals(hVerificate1.getOpenId())){
//									writeSuccessMsg("-5", null, response);
//									return null;
//								}
//							}
							
							//判断账户信息
							hUserAccount = huseraccountService.getHUserAccount(hUserAccount);
							if(hUserAccount!=null){
								hUserAccount.setCompany_name(manageAdminUser.getCompanyName());
								hUserAccount.setPhone(manageAdminUser.getPhone());
								hUserAccount.setRemark1(daili+"");
								hUserAccount.setMobile(manageAdminUser.getMobile());
								hUserAccount.setOpenId(openId);
								huseraccountService.updateHUserAccount(hUserAccount);
							}else{
								hUserAccount1.setCompany_name(manageAdminUser.getCompanyName());
								hUserAccount1.setPhone(manageAdminUser.getPhone());
								hUserAccount1.setMobile(manageAdminUser.getMobile());
								hUserAccount1.setRemark1(daili+"");
								hUserAccount1.setTotalFee(new BigDecimal(0));
								hUserAccount1.setOpenId(openId);
								huseraccountService.insertHUserAccount(hUserAccount1);
							}
							
							
							manageAdminUser.setLoginIP(Common.getLocalIp());
							manageAdminUser.setLastLogin(new Date());
							manageAdminUser.setOpenId(openId);
							manageAdminUser.setState(1);
							manageadminuserService.updateManageAdminUser(manageAdminUser);
							manageAdminUser = manageadminuserService.getManageAdminUser1(manageAdminUser);
							
							HLoginLog hLoginLog = new HLoginLog();
							hLoginLog.setAdminName(manageAdminUser.getAdminName());
							hLoginLog.setLoginIp(super.getIpAddr(request));
							hLoginLog.setLoginTIme(new Date());
							hLoginLog.setDeviceType(1);
							hloginlogService.insertHLoginLog(hLoginLog);
							
							//修改提现申请
							List<HPresentApply> listApplay = hpresentapplyService.getHPresentApplyBaseList(hPresentApply);
							if(listApplay!=null&&listApplay.size()>0){
								for(HPresentApply applay:listApplay){
									applay.setOpenId(openId);
									hpresentapplyService.updateHPresentApply(applay);
								}
							}
							
							request.getSession().setAttribute(SessionName.ADMIN_USER_NAME, manageAdminUser.getAdminName());
							request.getSession().setAttribute(SessionName.ADMIN_USER_ID, manageAdminUser.getAdminId());
							request.getSession().setAttribute(SessionName.ADMIN_USER, manageAdminUser);
							writeSuccessMsg("1", null, response);
						}else{
							// 该用户角色没有权限
							writeSuccessMsg("-3", null, response);
						}
					}else{
						// 用户状态错误
						writeSuccessMsg("-2", null, response);
					}
				}else{
					writeSuccessMsg("-6", null, response);
				}
			}else{
				// 没有用户
				writeSuccessMsg("-1", null, response);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
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
	@RequestMapping(value = "/isDefault", method = RequestMethod.POST)
	public String isDefault(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try{
			HAgent hagent = new HAgent();
			Integer id = RequestHandler.getInteger(request, "id");
			hagent.setId(id);
			hagent = hagentService.getHAgent(hagent);
			if(hagent!=null){
				hagent.setRemark3("1");
				hagentService.isDefault1();
				hagentService.isDefault2(hagent);
				writeSuccessMsg("成功", null, response);
			}else{
				writeSuccessMsg("操作失败", null, response);
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
	@RequestMapping(value = "/checkUseAgent", method = RequestMethod.POST)
	public String checkUseAgent(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		Integer id = RequestHandler.getInteger(request, "id");
		try{
			HAgent hagent = new HAgent();
			hagent.setId(id);
			int count = hagentService.checkUseAgent(hagent);
			if(count>0){
				writeSuccessMsg("成功", null, response);
			}else{
				writeErrorMsg("操作失败", null, response);
			}
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
			HAgent hagent = new HAgent();
			hagent.setId(id);
			hagent.setGl_sj_id(glsjid);
			hagent.setGl_sj_name(glsjname);
			int count = hagentService.updateHAgent(hagent);
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
			HAgent hagent = new HAgent();
			hagent.setId(id);
			hagent.setGl_yl_id(glylid);
			hagent.setGl_yl_name(glylname);
			int count = hagentService.updateHAgent(hagent);
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
