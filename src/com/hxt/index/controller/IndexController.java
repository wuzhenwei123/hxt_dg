package com.hxt.index.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.base.controller.BaseController;
import com.base.utils.Common;
import com.base.utils.DateFormatToString;
import com.base.utils.FileUploadConstants;
import com.base.utils.RequestHandler;
import com.base.utils.SendMsgUtil;
import com.base.utils.SessionName;
import com.hxt.hAgent.model.HAgent;
import com.hxt.hAgent.service.HAgentService;
import com.hxt.hAgentTwo.model.HAgentTwo;
import com.hxt.hAgentTwo.service.HAgentTwoService;
import com.hxt.hCompany.model.HCompany;
import com.hxt.hCompany.service.HCompanyService;
import com.hxt.hEwm.model.HEwm;
import com.hxt.hLbImg.model.HLbImg;
import com.hxt.hLbImg.service.HLbImgService;
import com.hxt.hLoginLog.model.HLoginLog;
import com.hxt.hLoginLog.service.HLoginLogService;
import com.hxt.hMessageLog.model.HMessageLog;
import com.hxt.hMessageLog.service.HMessageLogService;
import com.hxt.hNewNews.model.HNewNews;
import com.hxt.hNewNews.service.HNewNewsService;
import com.hxt.hNewNotice.model.HNewNotice;
import com.hxt.hNewNotice.service.HNewNoticeService;
import com.hxt.hProxyMessage.model.HProxyMessage;
import com.hxt.hProxyMessage.service.HProxyMessageService;
import com.sys.adminSkins.model.AdminSkins;
import com.sys.adminSkins.service.AdminSkinsService;
import com.sys.manageAdminUser.model.ManageAdminUser;
import com.sys.manageAdminUser.service.ManageAdminUserService;
import com.sys.manageColumn.model.ManageColumn;
import com.sys.manageColumn.service.ManageColumnService;
import com.wx.utils.FileUtils;

@Controller
@RequestMapping("/index")
public class IndexController extends BaseController{
	
	@Autowired
	private HLbImgService hlbimgService = null;
	@Autowired
	private HCompanyService hcompanyService = null;
	@Autowired
	private ManageAdminUserService manageadminuserService = null;// 用户
	@Autowired
	private ManageColumnService managecolumnService = null;// 菜单
	@Autowired
	private AdminSkinsService adminskinsService = null;// 风格
	@Autowired
	private ManageAdminUserService manageAdminUserService = null;
	@Autowired
	private HNewNewsService hnewnewsService = null;
	@Autowired
	private HNewNoticeService hnewnoticeService = null;
	@Autowired
	private HMessageLogService hMessageLogService = null;
	@Autowired
	private HAgentService hagentService = null;
	@Autowired
	private HAgentTwoService hagenttwoService = null;
	@Autowired
	private HProxyMessageService hproxymessageService = null;
	@Autowired
	private HLoginLogService hloginlogService = null;
	/**
	 * 进入首页
	 * 
	 * @author springLml 2015年1月28日 上午12:57:50
	 */
	@RequestMapping(value = "/first")
	public String index(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String error = request.getParameter("errormsg");
		String openId = request.getParameter("openId");
		HLbImg hlbimg = new HLbImg();
		hlbimg.setStatus(1);
		hlbimg.setSortColumn(" a.sort_id asc ");
		List<HLbImg> hlbimgList = hlbimgService.getHLbImgBaseList(hlbimg);
		
		HNewNews hNewNews = new HNewNews();
		hNewNews.setSortColumn(" a.sortId desc ,a.createTime desc ");
		hNewNews.setState(1);
		hNewNews.setRowStart(1);
		hNewNews.setRowCount(4);
		List<HNewNews> hnewnewsList = hnewnewsService.getNewsList(hNewNews);
		
		hNewNews.setRowStart(0);
		hNewNews.setRowCount(1);
		
		List<HNewNews> hnewnewsList1 = hnewnewsService.getNewsList(hNewNews);
		
		HNewNotice hnewnotice = new HNewNotice();
		hnewnotice.setState(1);
		hnewnotice.setRowStart(0);
		hnewnotice.setRowCount(1);
		hnewnotice.setSortColumn(" a.id desc ");
		
		List<HNewNotice> hnewnoticeList = hnewnoticeService.getNoticeList(hnewnotice);
		
		model.addAttribute("hnewnoticeList", hnewnoticeList);
		model.addAttribute("hnewnewsList1", hnewnewsList1);
		model.addAttribute("hnewnewsList", hnewnewsList);
		model.addAttribute("hnewnewsList", hnewnewsList);
		model.addAttribute("hlbimgList", hlbimgList);
		model.addAttribute("nav", "index");
		model.addAttribute("error", error);
		model.addAttribute("openId", openId);
		return "/public/index";
	}
	
	/**
	 * 进入电费查询与缴费
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toDianfei", method = RequestMethod.GET)
	public String toDianfei(HttpServletRequest request, HttpServletResponse response, Model model){
		model.addAttribute("nav", "dianfei");
		return "/front/dianfei";
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(HttpServletRequest request, HttpServletResponse response, Model model){
		model.addAttribute("nav", "dianfei");
		return "/public/test";
	}
	
	/**
	 * 进入电费查询与缴费
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toDianfei01", method = RequestMethod.GET)
	public String toDianfei01(HttpServletRequest request, HttpServletResponse response, Model model){
		model.addAttribute("nav", "dianfei");
		return "/front/dianfei01";
	}
	/**
	 * 进入电表
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toDianbiao", method = RequestMethod.GET)
	public String toDianbiao(HttpServletRequest request, HttpServletResponse response, Model model){
		model.addAttribute("nav", "dianbiao");
		return "/front/dianbiao";
	}
	/**
	 * 进入电表
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toDianbiaoNew", method = RequestMethod.GET)
	public String toDianbiaoNew(HttpServletRequest request, HttpServletResponse response, Model model){
		model.addAttribute("nav", "dianbiao");
		return "redirect:/public/payFee/index";
	}

	/**
	 * 进入登录页
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toLogin", method = RequestMethod.GET)
	public String toLogin(HttpServletRequest request, HttpServletResponse response, Model model){
		return "/front/login";
	}
	
	/**
	 * 进入注册页
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toRegister", method = RequestMethod.GET)
	public String toRegister(HttpServletRequest request, HttpServletResponse response, Model model){
		String recPhone = request.getParameter("recPhone");
		model.addAttribute("recPhone", recPhone);
		return "/public/reg";
	}
	
	/**
	 * 进入支付登录页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toCustomer", method = RequestMethod.GET)
	public String toCustomer(HttpServletRequest request, HttpServletResponse response, Model model){
		return "/front/customerIndex";
	}
	
	/**
	 * 进入支付登录页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toZhiFu", method = RequestMethod.GET)
	public String toZhiFu(HttpServletRequest request, HttpServletResponse response, Model model){
		return "/front/zhiFu";
	}
	/**
	 * 进入修改密码页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toMpass", method = RequestMethod.GET)
	public String toMpass(HttpServletRequest request, HttpServletResponse response, Model model){
		model.addAttribute("nav", "mima");
		return "/front/mpass";
	}
	/**
	 * 进入修改信息页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toMinfo", method = RequestMethod.GET)
	public String toMinfo(HttpServletRequest request, HttpServletResponse response, Model model){
		ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
		if(adminUser!=null){
			HCompany com = new HCompany();
			com.setUser_id(adminUser.getAdminId());
			List<HCompany> list = hcompanyService.getHCompanyBaseList(com);
			if(list!=null&&list.size()>0){
				model.addAttribute("company", list.get(0));
			}
		}
		model.addAttribute("nav", "minfo");
		return "/front/minfo";
	}
	
	/**
	 * 用户登录
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/pagelogin", method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		ManageAdminUser manageadminuser = new ManageAdminUser();
		String adminName = RequestHandler.getString(request, "adminName");
		manageadminuser.setAdminName(adminName);
		String passwd = RequestHandler.getString(request, "passwd");
		String openId = RequestHandler.getString(request, "openId");
		Integer wxflag = RequestHandler.getInteger(request, "wxflag");
		if(!StringUtils.isNotBlank(openId)){
			openId = (String)request.getSession().getAttribute("session_openId");
		}

		String verify = RequestHandler.getString(request, "verify");

		if (StringUtils.isEmpty(adminName) || StringUtils.isEmpty(passwd) || StringUtils.isEmpty(verify)) {
			writeErrorMsg("用户名、密码、验证码不能为空", null, response);
			return null;
		}

		Object token = request.getSession().getAttribute(SessionName.TOKEN);// 验证码
		if (token == null || !verify.equalsIgnoreCase(token.toString())) {
			writeErrorMsg("验证码不正确", null, response);
			return null;
		}
		int proxy_flag = 0;
		int count = manageadminuserService.getManageAdminUserListCount(manageadminuser);
		if (count == 0) {
			// 没有用户
			writeErrorMsg("用户不存在", null, response);
		} else if (count == 1) {
			ManageAdminUser adminUser = manageadminuserService.getManageAdminUser(manageadminuser);
			if(adminUser.getState()==1){
				if (adminUser.getPasswd().equals(MD5.getMD5ofStr(passwd))) {
					boolean b = true;
					//判断公司状态
					HCompany hCompany = new HCompany();
					HProxyMessage hProxyMessage = null;
					if(adminUser.getRoleId().equals(Integer.valueOf(FileUploadConstants.PROXY_ROLEID))){
						hProxyMessage = new HProxyMessage();
						hProxyMessage.setUserId(adminUser.getAdminId());
						hProxyMessage = hproxymessageService.getHProxyMessage(hProxyMessage);
						hCompany.setId(hProxyMessage.getCId());
						proxy_flag = 1;
						if (adminUser.getState()==1&&hProxyMessage.getState() == 1&& (hProxyMessage.getCheckState() == 1 || hProxyMessage.getCheckState() == 7|| hProxyMessage.getCheckState() == 8|| hProxyMessage.getCheckState() == 6)) {
							
						}else{
							writeErrorMsg("您的账号已被禁用，详情请咨询010-96199!", null, response);
							return null;
						}
					}else{
						hCompany.setUser_id(adminUser.getAdminId());
					}
					hCompany = hcompanyService.getHCompany(hCompany);
					if(hCompany!=null&&hCompany.getStatus()!=null&&hCompany.getStatus()==1&&hCompany.getVerify_status()!=null&&hCompany.getVerify_status()==1){
						//验证公司登陆账户
						ManageAdminUser adminUser2 = new ManageAdminUser();
						adminUser2.setAdminId(hCompany.getUser_id());
						adminUser2 = manageadminuserService.getManageAdminUser1(adminUser2);
						if(adminUser2!=null&&adminUser2.getState()==1){
							if(adminUser.getRoleId().equals(Integer.valueOf(FileUploadConstants.PROXY_ROLEID))){
								adminUser.setOneAgentOpenId(hCompany.getOneAgentOpenId());
								adminUser.setOneAgentName(hCompany.getOneAgentName());
								adminUser.setTwoAgentName(hCompany.getTwoAgentName());
								adminUser.setTwoAgentOpenId(hCompany.getTwoAgentOpenID());
								adminUser.setServicerId(hCompany.getServicerId());
								adminUser.setServicerName(hCompany.getServicerName());
							}
						}else{
							writeErrorMsg("您的账号已被禁用，详情请咨询010-96199!", null, response);
							return null;
						}
					}else{
//						if(hCompany!=null&&hCompany.getStatus()!=null&&hCompany.getStatus()!=1){
							writeErrorMsg("您的单位账户已被暂停，详情请咨询010-96199!", null, response);
							return null;
//						}
//						b = false;
					}
					if(b){
						//判断前台登陆人角色
//						if(adminUser.getRoleId().equals(Integer.valueOf(FileUploadConstants.COMPANY_ROLE_ID))){
							//获取代理信息
							if(StringUtils.isNotBlank(adminUser.getOneAgentOpenId())){
								HAgent agentOne = new HAgent();
								agentOne.setOpenId(adminUser.getOneAgentOpenId());
								agentOne = hagentService.getHAgent(agentOne);
								if(agentOne!=null){
									adminUser.setOneAgentPhone(agentOne.getMobile1());
									adminUser.setOneAgentName(agentOne.getName());
								}
							}
							if(StringUtils.isNotBlank(adminUser.getTwoAgentOpenId())){
								HAgentTwo agentTwo = new HAgentTwo();
								agentTwo.setOpenId(adminUser.getTwoAgentOpenId());
								agentTwo = hagenttwoService.getHAgentTwo(agentTwo);
								if(agentTwo!=null){
									adminUser.setTwoAgentPhone(agentTwo.getMobile1());
									adminUser.setTwoAgentName(agentTwo.getName());
								}
							}
							if(adminUser.getServicerId()!=null){
								ManageAdminUser servicer = new ManageAdminUser();
								servicer.setAdminId(adminUser.getServicerId());
								servicer = manageAdminUserService.getManageAdminUser(servicer);
								if(servicer!=null){
									adminUser.setServicerPhone(servicer.getMobile());
									adminUser.setServicerName(servicer.getRealName());
								}
							}
							if(hProxyMessage!=null){
								adminUser.setCompanyId(hProxyMessage.getCId());
							}
							
							
							HLoginLog hLoginLog = new HLoginLog();
							hLoginLog.setAdminName(adminUser.getAdminName());
							hLoginLog.setLoginIp(this.getIpAddr(request));
							hLoginLog.setLoginTIme(new Date());
							hLoginLog.setDeviceType(wxflag);
							hloginlogService.insertHLoginLog(hLoginLog);
							
							
							request.getSession().setAttribute(SessionName.ADMIN_USER_NAME, adminUser.getAdminName());
							request.getSession().setAttribute(SessionName.ADMIN_USER_ID, adminUser.getAdminId());
							request.getSession().setAttribute(SessionName.ADMIN_USER, adminUser);
							if (adminUser.getLastLogin() != null && !"".equals(adminUser.getLastLogin())) {
								request.getSession().setAttribute(SessionName.ADMIN_USER_LAST_LOGIN, DateFormatToString.getStringByDate(adminUser.getLastLogin()));
							}
							adminUser.setLoginIP(Common.getIp(request));
							// 正常
							adminUser.setLastLogin(new Date());
//							if(FileUploadConstants.PROXY_ROLEID.equals(adminUser.getRoleId()+"")){
//								adminUser.setOpenId(openId);
//							}
							manageadminuserService.updateManageAdminUser(adminUser);
							loginInfo(request);
							writeSuccessMsg("登录成功", proxy_flag, response);
//						}else{
//							writeErrorMsg("请使用客户登录账户登录", null, response);
//						}
						
//						return "redirect:/public/payFee/index";
					}else{
						writeErrorMsg("公司异常，请联系系统管理员!", null, response);
					}
					
				} else {
					// 密码错误
					writeErrorMsg("用户名或密码错误", null, response);
				}
			}else{
				writeErrorMsg("您的账号已被禁用，详情请咨询010-96199!", null, response);
			}
		} else {
			// 多个用户
			writeErrorMsg("用户异常，请联系系统管理员", null, response);
		}
		return null;
	}
	
	/**
	 * 注册登录信息
	 * 
	 * @time : 2015年3月6日 下午1:42:17
	 * @param request
	 */
	private void loginInfo(HttpServletRequest request) {

		// --------------------------------根据权限输出菜单
		ManageAdminUser adminUser = (ManageAdminUser) getSession(request, SessionName.ADMIN_USER);
		ManageColumn manageColumn = new ManageColumn();
		manageColumn.setState(1);
		manageColumn.setRoleId(adminUser.getRoleId());
		manageColumn.setParentColumnID(-1);// 一级菜单
		manageColumn.setSortColumn(" columnOrder ASC ");
		List<ManageColumn> parentList = managecolumnService.getManageColumnListByRole(manageColumn);
		for (ManageColumn manageColumn2 : parentList) {
			manageColumn = new ManageColumn();
			manageColumn.setParentColumnID(manageColumn2.getColumnId());
			manageColumn.setState(1);
			manageColumn.setRoleId(adminUser.getRoleId());
			manageColumn.setSortColumn(" columnOrder ASC ");
			List<ManageColumn> childs = managecolumnService.getManageColumnListByRole(manageColumn);// 二级菜单
			manageColumn2.setChilds(childs);
		}
		addSession(request, SessionName.SYSTEM_COLUMN_LIST, parentList);

		AdminSkins adminSkins = new AdminSkins();
		adminSkins.setAdminId(adminUser.getAdminId());
		adminSkins = adminskinsService.getAdminSkins(adminSkins);// 获取风格
	}
	/**
	 * 进入修改联系人信息页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toMcontact", method = RequestMethod.GET)
	public String toMcontact(HttpServletRequest request, HttpServletResponse response, Model model){
		ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
		if(adminUser!=null){
			HCompany com = new HCompany();
			com.setUser_id(adminUser.getAdminId());
			List<HCompany> list = hcompanyService.getHCompanyBaseList(com);
			if(list!=null&&list.size()>0){
				model.addAttribute("company", list.get(0));
			}
		}
		model.addAttribute("nav", "mcontact");
		return "/front/mcontact";
	}
	
	/**
	 * 进入发票页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/goFaPiao", method = RequestMethod.GET)
	public String goFaPiao(HttpServletRequest request, HttpServletResponse response, Model model){
		model.addAttribute("nav", "fp");
		return "/front/order";
	}
	
	/**
	 * 进入忘记密码页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/goForget", method = RequestMethod.GET)
	public String goForget(HttpServletRequest request, HttpServletResponse response, Model model){
		return "/public/getPwd";
	}
	
	@RequestMapping(value = "/forget", method = RequestMethod.POST)
	public String mp(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try{
			String phone = RequestHandler.getString(request, "phone");
			String vercode = RequestHandler.getString(request, "vercode");
			String password = RequestHandler.getString(request, "pwd");
			
			String sysCode = SendMsgUtil.CODEMAP.get(phone);// 手机验证码
			if (sysCode == null || !vercode.equalsIgnoreCase(sysCode)) {
				writeErrorMsg("手机验证码不正确", "", response);
				return null;
			}
			if(StringUtils.isNotBlank(phone)){
				HCompany hCompany = new HCompany();
				hCompany.setContact_phone(phone);
				int count  = hcompanyService.getHCompanyListCount(hCompany);
				if(count>1){
					writeErrorMsg("该手机号注册了多个企业，请联系010-96199更改密码", "", response);
					return null;
				}else if(count==1){
					HCompany company = hcompanyService.getHCompany(hCompany);
					if(company!=null&&company.getId()>0){
						Integer uid = company.getUser_id();
						if(uid!=null){
							ManageAdminUser user = new ManageAdminUser();
							user.setAdminId(uid);
							List<ManageAdminUser> ulst = manageAdminUserService.getManageAdminUserBaseList(user);
							if(ulst!=null&&ulst.size()>0){
								user = ulst.get(0);
//								StringBuilder str=new StringBuilder();//定义变长字符串
//								Random random=new Random();
//								for(int i=0;i<8;i++){
//									str.append(random.nextInt(10));
//								}
//								String password = str.toString();
								user.setPasswd(MD5.getMD5ofStr(password));
								manageAdminUserService.updateManageAdminUser(user);
//								String content = "您的新密码为：" + password;
//								int s = SendMsgUtil.sendMsg(phone,content);
								request.getSession().removeAttribute(SessionName.TOKEN);
								writeSuccessMsg("修改成功", null, response);
							}else{
								writeErrorMsg("用户不存在", null, response);
							}
						}else{
							writeErrorMsg("用户不存在", null, response);
						}
					}else{
						writeErrorMsg("手机号码不存在", "", response);
						return null;
					}
				}else{
					writeErrorMsg("手机号码不存在", "", response);
					return null;
				}
			}else{
				writeErrorMsg("请输入手机号", "", response);
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/** 发验证码 **/
	@RequestMapping(value = "/sendCode", method = RequestMethod.POST)
	public String sendCode(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception
	{
		String phone =  RequestHandler.getString(request, "phone");
		String vercode =  RequestHandler.getString(request, "vercode");
		Object token = request.getSession().getAttribute(SessionName.TOKEN);// 验证码
		if(StringUtils.isNotBlank(phone)){
			if (token == null || !vercode.equalsIgnoreCase(token.toString())) {
				writeErrorMsg("验证码错误", "", response);
			}else{
				HCompany tmp = new HCompany();
				tmp.setContact_phone(phone);
				List list = hcompanyService.getHCompanyBaseList(tmp);
				if(list!=null&&list.size()>0){
					String randomCode = SendMsgUtil.getRandomCode(4);
					String content = "您正在更改网站登录密码，手机验证码是：" + randomCode + "，请不要告诉他人。";
					int s = SendMsgUtil.sendMsg(phone,content);
					if(s > 0){
						SendMsgUtil.TIMEMAP.put(phone, new Date());
						SendMsgUtil.CODEMAP.put(phone, randomCode);
						writeSuccessMsg("发送成功", null, response);
					}else{
						writeErrorMsg("发送失败", "", response);
					}
				}else{
					writeErrorMsg("手机号码不存在！", "", response);
				}
			}
		}else{
			writeErrorMsg("发送失败", "", response);
		}
		return null;
	}
	/** 发验证码 **/
	@RequestMapping(value = "/sendVercode", method = RequestMethod.POST)
	public String sendVercode(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try{
			String phone =  RequestHandler.getString(request, "phone");
			String vercode =  RequestHandler.getString(request, "vercode");
			Object token = request.getSession().getAttribute(SessionName.TOKEN);// 验证码
			if(request.getSession().getAttribute("errCount")!=null){
				if(Integer.valueOf(request.getSession().getAttribute("errCount").toString())>=2){
					if(StringUtils.isBlank(vercode)){
						writeErrorMsg("验证码不正确", "", response);
						return null;
					}
					if (token == null || !vercode.equalsIgnoreCase(token.toString())) {
						writeErrorMsg("验证码不正确", "", response);
						return null;
					}
				}
			}
			String randomCode = SendMsgUtil.getRandomCode(4);
			String content = "您正在查询电费信息，手机验证码是：" + randomCode + "，请不要告诉他人。";
			int s = SendMsgUtil.sendMsg(phone,content);
			if(s > 0){
//				String IP = this.getIpAddr(request);
//				HMessageLog hMessageLog = new HMessageLog();
//				hMessageLog.setContent(content);
//				hMessageLog.setCreateTime(new Date());
//				hMessageLog.setIp(IP);
//				hMessageLog.setPhone(phone);
//				hMessageLogService.insertHMessageLog(hMessageLog);
				SendMsgUtil.TIMEMAP.put(phone, new Date());
				SendMsgUtil.CODEMAP.put(phone, randomCode);
				writeSuccessMsg("发送成功", null, response);
			}else{
				writeErrorMsg("发送失败", "", response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
//	String mailHost = SendEmailUtils.getPropValue("mail.smtp.host");
//	String mailEmail = SendEmailUtils.getPropValue("mail.smtp.email");
//	String mailUser = SendEmailUtils.getPropValue("mail.smtp.user");
//	String mailPassword = SendEmailUtils.getPropValue("mail.smtp.password");//com.base.utils.
//	new SendEmailUtils().send(mobile, mailEmail, "爱学堂—找回密码", emailContent, mailHost, mailUser, mailPassword, true);
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
	
	
	/**
	 * 用户登录
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/loginR", method = RequestMethod.GET)
	public String loginR(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String toURL = "/front/tj";

		ManageAdminUser manageadminuser = new ManageAdminUser();
		String adminName = RequestHandler.getString(request, "contact_phone");
		manageadminuser.setAdminName(adminName);
		manageadminuser.setState(1);
		String passwd = adminName.substring(adminName.length()-6, adminName.length());

//		String verify = RequestHandler.getString(request, "verify");

//		if (StringUtils.isEmpty(adminName)) {
//			model.addAttribute("error", "-1");
//			return toURL;
//		}

//		Object token = request.getSession().getAttribute(SessionName.TOKEN);// 验证码
//		if (token == null || !verify.equalsIgnoreCase(token.toString())) {
//			model.addAttribute("error", "-2");
//			return toURL;
//		}

		int count = manageadminuserService.getManageAdminUserListCount(manageadminuser);
		if (count == 0) {
			// 没有用户
			model.addAttribute("error", "-3");
		} else if (count == 1) {
			ManageAdminUser adminUser = manageadminuserService.getManageAdminUser(manageadminuser);
			System.out.println(MD5.getMD5ofStr(passwd));
			if (adminUser.getPasswd().equals(MD5.getMD5ofStr(passwd))) {
				boolean b = true;
				//判断公司状态
				HCompany hCompany = new HCompany();
				hCompany.setUser_id(adminUser.getAdminId());
				hCompany = hcompanyService.getHCompany(hCompany);
				if(hCompany!=null&&hCompany.getStatus()!=null&&hCompany.getStatus()==1&&hCompany.getVerify_status()!=null&&hCompany.getVerify_status()==1){
					
				}else{
					b = false;
				}
				
				if(b){
					//获取代理信息
					if(StringUtils.isNotBlank(adminUser.getOneAgentOpenId())){
						HAgent agentOne = new HAgent();
						agentOne.setOpenId(adminUser.getOneAgentOpenId());
						agentOne = hagentService.getHAgent(agentOne);
						if(agentOne!=null){
							adminUser.setOneAgentPhone(agentOne.getMobile1());
							adminUser.setOneAgentName(agentOne.getName());
						}
					}
					if(StringUtils.isNotBlank(adminUser.getTwoAgentOpenId())){
						HAgentTwo agentTwo = new HAgentTwo();
						agentTwo.setOpenId(adminUser.getTwoAgentOpenId());
						agentTwo = hagenttwoService.getHAgentTwo(agentTwo);
						if(agentTwo!=null){
							adminUser.setTwoAgentPhone(agentTwo.getMobile1());
							adminUser.setTwoAgentName(agentTwo.getName());
						}
					}
					if(adminUser.getServicerId()!=null){
						ManageAdminUser servicer = new ManageAdminUser();
						servicer.setAdminId(adminUser.getServicerId());
						servicer = manageAdminUserService.getManageAdminUser(servicer);
						if(servicer!=null){
							adminUser.setServicerPhone(servicer.getMobile());
							adminUser.setServicerName(servicer.getRealName());
						}
					}
					
					HLoginLog hLoginLog = new HLoginLog();
					hLoginLog.setAdminName(adminUser.getAdminName());
					hLoginLog.setLoginIp(this.getIpAddr(request));
					hLoginLog.setLoginTIme(new Date());
					hLoginLog.setDeviceType(0);
					hloginlogService.insertHLoginLog(hLoginLog);
					
					request.getSession().setAttribute(SessionName.ADMIN_USER_NAME, adminUser.getAdminName());
					request.getSession().setAttribute(SessionName.ADMIN_USER_ID, adminUser.getAdminId());
					request.getSession().setAttribute(SessionName.ADMIN_USER, adminUser);
					if (adminUser.getLastLogin() != null && !"".equals(adminUser.getLastLogin())) {
						request.getSession().setAttribute(SessionName.ADMIN_USER_LAST_LOGIN, DateFormatToString.getStringByDate(adminUser.getLastLogin()));
					}
//					toURL = "/index/toDianbiao";
					adminUser.setLoginIP(Common.getLocalIp());
					// 正常
					adminUser.setLastLogin(new Date());
					manageadminuserService.updateManageAdminUser(adminUser);
					loginInfo(request);
					return "redirect:/public/payFee/index";
				}else{
					model.addAttribute("error", "-6");
				}
				
			} else {
				// 密码错误
				model.addAttribute("error", "-4");
			}
		} else {
			// 多个用户
			model.addAttribute("error", "-5");
		}
		return toURL;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @description 图片下载
	 * @return
	 */
	@RequestMapping(value = "/toDownload", method = RequestMethod.GET)
	public String toDownload(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		String link = RequestHandler.getString(request, "link");
		String name = RequestHandler.getString(request, "name");
		String contextPath = request.getSession().getServletContext().getRealPath("/");
		try{
			String type = link.substring(link.lastIndexOf("."), link.length());
				model.addAttribute("name",name+type);
				model.addAttribute("link", contextPath + link);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return "/public/excelup";
	}
}
