package com.sys.manageAdminUser.controller;

import java.awt.Color;
import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
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
import com.base.utils.SessionName;
import com.hxt.hAgent.model.HAgent;
import com.hxt.hAgent.service.HAgentService;
import com.hxt.hAgentTwo.model.HAgentTwo;
import com.hxt.hAgentTwo.service.HAgentTwoService;
import com.hxt.hCommon.service.HCommonService;
import com.hxt.hCompany.model.HCompany;
import com.hxt.hCompany.service.HCompanyService;
import com.hxt.hLoginLog.model.HLoginLog;
import com.hxt.hLoginLog.service.HLoginLogService;
import com.hxt.hPayGuli.model.HPayGuli;
import com.hxt.hPayGuli.service.HPayGuliService;
import com.sys.adminRole.model.AdminRole;
import com.sys.adminRole.service.AdminRoleService;
import com.sys.adminRoleColumn.service.AdminRoleColumnService;
import com.sys.adminSkins.model.AdminSkins;
import com.sys.adminSkins.service.AdminSkinsService;
import com.sys.adminUserRole.model.AdminUserRole;
import com.sys.adminUserRole.service.AdminUserRoleService;
import com.sys.manageAdminUser.model.ManageAdminUser;
import com.sys.manageAdminUser.service.ManageAdminUserService;
import com.sys.manageColumn.model.ManageColumn;
import com.sys.manageColumn.service.ManageColumnService;

/**
 * @author keeny
 * @time 2015年02月04日 11:09:21
 */
@Controller
@RequestMapping("/manageAdminUser")
public class ManageAdminUserController extends BaseController {

	public static ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
	private static Random random = new Random();

	// private static Logger logger =
	// Logger.getLogger(ManageAdminUserController.class); //Logger
	@Autowired
	private AdminRoleService adminroleService = null;// 角色
	@Autowired
	private AdminUserRoleService adminuserroleService = null;// 用户角色关系
	@Autowired
	private ManageAdminUserService manageadminuserService = null;// 用户
	@Autowired
	private ManageColumnService managecolumnService = null;// 菜单
	@Autowired
	private AdminRoleColumnService adminrolecolumnService = null;// 角色菜单关系表
	@Autowired
	private AdminSkinsService adminskinsService = null;// 风格
	@Autowired
	private HCompanyService hcompanyService = null;
	@Autowired
	private HCommonService hCommonService = null;
	@Autowired
	private HLoginLogService hloginlogService = null;
	@Autowired
	private HAgentService hagentService = null;
	@Autowired
	private HAgentTwoService hagenttwoService = null;
	@Autowired
	private HPayGuliService hpayguliService = null;

	static {
		// cs.setColorFactory(new SingleColorFactory(new Color(25, 60, 170)));
		cs.setColorFactory(new ColorFactory() {
			@Override
			public Color getColor(int x) {
				int[] c = new int[3];
				int i = random.nextInt(c.length);
				for (int fi = 0; fi < c.length; fi++) {
					if (fi == i) {
						c[fi] = random.nextInt(71);
					} else {
						c[fi] = random.nextInt(256);
					}
				}
				return new Color(c[0], c[1], c[2]);
			}
		});
		RandomWordFactory wf = new RandomWordFactory();
		wf.setCharacters("1234567890");
		wf.setMaxLength(4);
		wf.setMinLength(4);
		cs.setWordFactory(wf);
	}

	/***************** 页面中转 *********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
		AdminRole adminRole = new AdminRole();
		adminRole.setState(1);
		List<AdminRole> roleList = adminroleService.getAdminRoleBaseList(adminRole);
		model.addAttribute("roleList", roleList);
		return "/sys/manageAdminUser/manageAdminUserIndex";
	}
	@RequestMapping(value = "/indexPP", method = RequestMethod.GET)
	public String indexPP(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
		AdminRole adminRole = new AdminRole();
		adminRole.setState(1);
		List<AdminRole> roleList = adminroleService.getAdminRoleBaseList(adminRole);
		model.addAttribute("roleList", roleList);
		return "/sys/manageAdminUserPP/manageAdminUserIndex";
	}

	@RequestMapping(value = "/toLogin", method = RequestMethod.GET)
	public String toLogin(HttpServletRequest request, HttpServletResponse response, Model model) {
		Object userObj = request.getSession().getAttribute(SessionName.ADMIN_USER);
		if (userObj != null) {
			loginInfo(request);
			return "/sys/main";

		}
		return "/sys/login";
	}

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String welcome(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "/sys/welcome";
	}

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String toMain(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "/sys/main";
	}

	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model) {

		AdminRole adminRole = new AdminRole();
		adminRole.setState(1);
		List<AdminRole> roleList = adminroleService.getAdminRoleBaseList(adminRole);
		model.addAttribute("roleList", roleList);
		
		ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
		//查询登陆用户角色
		model.addAttribute("company_role_id", FileUploadConstants.COMPANY_ROLE_ID);
		return "/sys/manageAdminUser/manageAdminUserAdd";
	}

	@RequestMapping(value = "/loginout", method = RequestMethod.GET)
	public String loginout(HttpServletRequest request, HttpServletResponse response, Model model) {

		request.getSession().removeAttribute(SessionName.ADMIN_USER_NAME);
		request.getSession().removeAttribute(SessionName.ADMIN_USER_ID);
		request.getSession().removeAttribute(SessionName.ADMIN_USER);

		return "redirect:/manageAdminUser/toLogin";
	}
	
	/**
	 * 前端登出
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/cloginout", method = RequestMethod.GET)
	public String cloginout(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		request.getSession().removeAttribute(SessionName.ADMIN_USER_NAME);
		request.getSession().removeAttribute(SessionName.ADMIN_USER_ID);
		request.getSession().removeAttribute(SessionName.ADMIN_USER);
		
		return "redirect:/index/first";
	}
	/** 第一次登陆修改密码**/
	@RequestMapping(value = "/toUpdatePasswd", method = RequestMethod.GET)
	public String updatePasswd(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "/sys/updatePasswd";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String toURL = "/manageAdminUser/toLogin";

		ManageAdminUser manageadminuser = new ManageAdminUser();
		String adminName = RequestHandler.getString(request, "adminName");
		manageadminuser.setAdminName(adminName);
		manageadminuser.setState(1);
		String passwd = RequestHandler.getString(request, "passwd");

		String verify = RequestHandler.getString(request, "verify");

		if (StringUtils.isEmpty(adminName) || StringUtils.isEmpty(passwd) || StringUtils.isEmpty(verify)) {
			model.addAttribute("error", "-1");
			return "redirect:"+toURL;
		}

		Object token = request.getSession().getAttribute(SessionName.TOKEN);// 验证码
		if (token == null || !verify.equalsIgnoreCase(token.toString())) {
			model.addAttribute("error", "-2");
			return "redirect:"+toURL;
		}

		int count = manageadminuserService.getManageAdminUserListCount1(manageadminuser);
		if (count == 0) {
			// 没有用户
			model.addAttribute("error", "-3");
		} else if (count == 1) {
			ManageAdminUser adminUser = manageadminuserService.getManageAdminUser1(manageadminuser);
			System.out.println(MD5.getMD5ofStr(passwd));
			if (adminUser.getPasswd().equals(MD5.getMD5ofStr(passwd))) {
				boolean b = true;
				//判断当前用户状态
				Integer state = adminUser.getState();
				if(state!=1){
					b = false;
					model.addAttribute("error", "-5");
				}else{
					//判断所属机构对应的状态
					if(adminUser.getRoleId().equals(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID))){
						HAgent agentOne = new HAgent();
						agentOne.setOpenId(adminUser.getOneAgentOpenId());
						agentOne = hagentService.getHAgent(agentOne);
						if(agentOne!=null){
							if(agentOne.getCheck_status()==1&&agentOne.getStatus()==1){
								
							}else{
								b = false;
								model.addAttribute("error", "-6");
							}
						}
					}else if(adminUser.getRoleId().equals(Integer.valueOf(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID))){
						HAgentTwo agentTwo = new HAgentTwo();
						agentTwo.setOpenId(adminUser.getTwoAgentOpenId());
						agentTwo = hagenttwoService.getHAgentTwo(agentTwo);
						if(agentTwo!=null){
							if(agentTwo.getCheck_status()==1&&agentTwo.getStatus()==1){
								
							}else{
								b = false;
								model.addAttribute("error", "-6");
							}	
						}
					}
					if(b){
						
						HLoginLog hLoginLog = new HLoginLog();
						hLoginLog.setAdminName(adminUser.getAdminName());
						hLoginLog.setLoginIp(super.getIpAddr(request));
						hLoginLog.setLoginTIme(new Date());
						hLoginLog.setDeviceType(0);
						hloginlogService.insertHLoginLog(hLoginLog);
						
						request.getSession().setAttribute(SessionName.ADMIN_USER_NAME, adminUser.getAdminName());
						request.getSession().setAttribute(SessionName.ADMIN_USER_ID, adminUser.getAdminId());
						request.getSession().setAttribute(SessionName.ADMIN_USER, adminUser);
						if (adminUser.getLastLogin() != null && !"".equals(adminUser.getLastLogin())) {
							request.getSession().setAttribute(SessionName.ADMIN_USER_LAST_LOGIN, DateFormatToString.getStringByDate(adminUser.getLastLogin()));
						}
						toURL = "/manageAdminUser/main";
						adminUser.setLoginIP(Common.getLocalIp());
						// 正常
						adminUser.setLastLogin(new Date());
						manageadminuserService.updateManageAdminUser(adminUser);
						loginInfo(request);
					}
				}
			} else {
				// 密码错误
				model.addAttribute("error", "-4");
			}
		} else {
			// 多个用户
			model.addAttribute("error", "-5");
		}
		return "redirect:"+toURL;
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
		
//		if(adminSkins!=null){
//			if(adminSkins.getNavgSet() == null){//1左导航2右导航 默认左导航
//				addSession(request, SessionName.SYSTEM_SKINS_NAGSET, "sidebar-left");
//			}else{//1左导航2右导航
//				if(adminSkins.getNavgSet() == 1){
//					addSession(request, SessionName.SYSTEM_SKINS_NAGSET, "sidebar-left");
//				}else{
//					addSession(request, SessionName.SYSTEM_SKINS_NAGSET, "sidebar-right");
//				}
//			}
//			
//			if(adminSkins.getNavgType() == null){//默认左
//				addSession(request, "navg_body", "");
//				addSession(request, "navg_left", "");//body上样式
//				addSession(request, "navg_top", "style_none");//head上样式 1 打开，0 禁用
//			}else{
//				if(adminSkins.getNavgType() == 1){//1上左导航2上导航3左导航
//					addSession(request, "navg_body", "");
//					addSession(request, "navg_left", "");//body上样式
//					addSession(request, "navg_top", "1");//head上样式 1 打开，0 禁用
//				}if(adminSkins.getNavgType() == 2){//1上左导航2上导航3左导航
//					addSession(request, "navg_body", "sidebar-hidden");
//					addSession(request, "navg_left", "style_none");//body上样式
//					addSession(request, "navg_top", "1");//head上样式 1 打开，0 禁用
//				}else{//1上左导航2上导航3左导航
//					addSession(request, "navg_body", "");
//					addSession(request, "navg_left", "");//body上样式
//					addSession(request, "navg_top", "style_none");//head上样式 1 打开，0 禁用
//				}
//			}
//		}else{
//			addSession(request, SessionName.SYSTEM_SKINS_NAGSET, "sidebar-left");
//		}
//		addSession(request, SessionName.SYSTEM_SKINS, adminSkins);
	}

	@RequestMapping(value = "/toSkins/{adminId}", method = RequestMethod.GET)
	public String toSkins(@PathVariable Integer adminId, HttpServletRequest request, HttpServletResponse response, Model model) {

		AdminSkins adminSkins = new AdminSkins();
		adminSkins.setAdminId(adminId);
		AdminSkins adminSkins1 = adminskinsService.getAdminSkins(adminSkins);
		if (adminSkins1 == null) {
			adminSkins1 = new AdminSkins();
		}
		adminSkins1.setAdminId(adminId);

		model.addAttribute("adminSkins", adminSkins1);

		return "/sys/adminSkins/adminSkinsUpdate";
	}

	@RequestMapping(value = "/toUpdate/{adminId}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer adminId, HttpServletRequest request, HttpServletResponse response, Model model) {

		ManageAdminUser manageadminuser1 = new ManageAdminUser();
		manageadminuser1.setAdminId(adminId);
		ManageAdminUser manageadminuser = manageadminuserService.getManageAdminUser(manageadminuser1);
		model.addAttribute("manageadminuser", manageadminuser);

		AdminRole adminRole = new AdminRole();
		adminRole.setState(1);
		List<AdminRole> roleList = adminroleService.getAdminRoleBaseList(adminRole);// 角色列表
		model.addAttribute("roleList", roleList);
		ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
		model.addAttribute("company_role_id", FileUploadConstants.COMPANY_ROLE_ID);
		
		HCompany hCompany = new HCompany();
		hCompany.setUser_id(adminId);
		HCompany company = hcompanyService.getHCompany(hCompany);
		model.addAttribute("company", company);
		return "/sys/manageAdminUser/manageAdminUserUpdate";
	}

	/**
	 * @time : 2015年2月9日 下午1:51:49
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @Description: 个人中心
	 */
	@RequestMapping(value = "/toUserCenter", method = RequestMethod.GET)
	public String toUserCenter(HttpServletRequest request, HttpServletResponse response, Model model) {
		Object id = request.getSession().getAttribute(SessionName.ADMIN_USER_ID);
		if (id != null) {
			Integer adminId = Integer.valueOf(id.toString());
			ManageAdminUser manageadminuser1 = new ManageAdminUser();
			manageadminuser1.setAdminId(adminId);
			ManageAdminUser manageadminuser = manageadminuserService.getManageAdminUser(manageadminuser1);
			model.addAttribute("manageadminuser", manageadminuser);

			AdminRole adminRole = new AdminRole();
			adminRole.setState(1);
			List<AdminRole> roleList = adminroleService.getAdminRoleBaseList(adminRole);// 角色列表
			model.addAttribute("roleList", roleList);
		} else {
			return "/login";
		}

		return "/sys/manageAdminUser/userCenter";
	}

	/************* Public Methods *************/

	@RequestMapping(value = "/getManageAdminUserList", method = RequestMethod.GET)
	public String getManageAdminUserList(HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			ManageAdminUser manageadminuser = new ManageAdminUser();

			Integer adminId = RequestHandler.getInteger(request, "adminId");
			manageadminuser.setAdminId(adminId);

			String adminName = RequestHandler.getString(request, "adminName");
			manageadminuser.setAdminName(adminName);

			String nickName = RequestHandler.getString(request, "nickName");
			manageadminuser.setNickName(nickName);

			String passwd = RequestHandler.getString(request, "passwd");
			manageadminuser.setPasswd(passwd);

			String realName = RequestHandler.getString(request, "realName");
			manageadminuser.setRealName(realName);

			String mobile = RequestHandler.getString(request, "mobile");
			manageadminuser.setMobile(mobile);

			String phone = RequestHandler.getString(request, "phone");
			manageadminuser.setPhone(phone);

			Date lastLogin = RequestHandler.getDate(request, "lastLogin");
			manageadminuser.setLastLogin(lastLogin);

			String loginIP = RequestHandler.getString(request, "loginIP");
			manageadminuser.setLoginIP(loginIP);

			Date pwdModifyTime = RequestHandler.getDate(request, "pwdModifyTime");
			manageadminuser.setPwdModifyTime(pwdModifyTime);

			Integer state = RequestHandler.getInteger(request, "state");
			manageadminuser.setState(state);

			Date createTime = RequestHandler.getDate(request, "createTime");
			manageadminuser.setCreateTime(createTime);

			Integer createrId = RequestHandler.getInteger(request, "createrId");
			manageadminuser.setCreaterId(createrId);
			
			Integer roleId = RequestHandler.getInteger(request, "roleId");
			manageadminuser.setRoleId(roleId);
			
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			String startTime = RequestHandler.getString(request, "startTime");
			if(StringUtils.isNotBlank(startTime)){
				manageadminuser.setStartTime(sf.parse(startTime));
			}
			
			String endTime = RequestHandler.getString(request, "endTime");
			if(StringUtils.isNotBlank(endTime)){
				manageadminuser.setEndTime(sf.parse(endTime));
			}
			
			Integer companyId = RequestHandler.getInteger(request, "companyId");
			manageadminuser.setCompanyId(companyId);

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");

			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			manageadminuser.setRowStart(from);
			manageadminuser.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			manageadminuser.setSortColumn(" a.createTime desc ");
			
			ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			
			//查询登陆用户角色
			if(adminUser.getRoleType()==1){//超管
				String oneAgentOpenId = RequestHandler.getString(request, "oneAgentOpenId");
				manageadminuser.setOneAgentOpenId(oneAgentOpenId);
				
				String twoAgentOpenId = RequestHandler.getString(request, "twoAgentOpenId");
				manageadminuser.setTwoAgentOpenId(twoAgentOpenId);
				model.addAttribute("roleType", "1");
			}else{
				if(adminUser.getRoleId().equals(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID))){
					
					manageadminuser.setOneAgentOpenId(adminUser.getOneAgentOpenId());
					
					String twoAgentOpenId = RequestHandler.getString(request, "twoAgentOpenId");
					manageadminuser.setTwoAgentOpenId(twoAgentOpenId);
					model.addAttribute("roleType", "2");
					model.addAttribute("oneAgentOpenId", adminUser.getOneAgentOpenId());
				}else if(adminUser.getRoleId().equals(Integer.valueOf(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID))){
					model.addAttribute("roleType", "3");
					manageadminuser.setOneAgentOpenId(adminUser.getOneAgentOpenId());
					manageadminuser.setTwoAgentOpenId(adminUser.getTwoAgentOpenId());
					
				}else if(adminUser.getRoleId().equals(Integer.valueOf(FileUploadConstants.SERVICER_MANAGE_ROLEID))){
					model.addAttribute("roleType", "4");
					manageadminuser.setServicerId(adminUser.getAdminId());
					
				}
			}
			
			
			int manageadminuserListCount = manageadminuserService.getManageAdminUserListCount(manageadminuser);
			ResponseList<ManageAdminUser> manageadminuserList = null;
			if (manageadminuserListCount > 0) {
				manageadminuserList = manageadminuserService.getManageAdminUserList(manageadminuser);
			} else {
				manageadminuserList = new ResponseList<ManageAdminUser>();
			}
			// 设置数据总数
			manageadminuserList.setTotalResults(manageadminuserListCount);

			writeSuccessMsg("ok", manageadminuserList, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/getManageAdminUserList1", method = RequestMethod.GET)
	public String getManageAdminUserList1(HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			ManageAdminUser manageadminuser = new ManageAdminUser();
			
			Integer adminId = RequestHandler.getInteger(request, "adminId");
			manageadminuser.setAdminId(adminId);
			
			String adminName = RequestHandler.getString(request, "adminName");
			manageadminuser.setAdminName(adminName);
			
			String nickName = RequestHandler.getString(request, "nickName");
			manageadminuser.setNickName(nickName);
			
			String passwd = RequestHandler.getString(request, "passwd");
			manageadminuser.setPasswd(passwd);
			
			String realName = RequestHandler.getString(request, "realName");
			manageadminuser.setRealName(realName);
			
			String mobile = RequestHandler.getString(request, "mobile");
			manageadminuser.setMobile(mobile);
			
			String phone = RequestHandler.getString(request, "phone");
			manageadminuser.setPhone(phone);
			
			Date lastLogin = RequestHandler.getDate(request, "lastLogin");
			manageadminuser.setLastLogin(lastLogin);
			
			String loginIP = RequestHandler.getString(request, "loginIP");
			manageadminuser.setLoginIP(loginIP);
			
			Date pwdModifyTime = RequestHandler.getDate(request, "pwdModifyTime");
			manageadminuser.setPwdModifyTime(pwdModifyTime);
			
			Integer state = RequestHandler.getInteger(request, "state");
			manageadminuser.setState(state);
			
			Date createTime = RequestHandler.getDate(request, "createTime");
			manageadminuser.setCreateTime(createTime);
			
			Integer createrId = RequestHandler.getInteger(request, "createrId");
			manageadminuser.setCreaterId(createrId);
			
			Integer roleId = RequestHandler.getInteger(request, "roleId");
			manageadminuser.setRoleId(roleId);
			
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			String startTime = RequestHandler.getString(request, "startTime");
			if(StringUtils.isNotBlank(startTime)){
				manageadminuser.setStartTime(sf.parse(startTime));
			}
			
			String endTime = RequestHandler.getString(request, "endTime");
			if(StringUtils.isNotBlank(endTime)){
				manageadminuser.setEndTime(sf.parse(endTime));
			}
			
			Integer companyId = RequestHandler.getInteger(request, "companyId");
			manageadminuser.setCompanyId(companyId);
			
			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			manageadminuser.setRowStart(from);
			manageadminuser.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			manageadminuser.setSortColumn(" a.createTime desc ");
			
			ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			
			//查询登陆用户角色
//			if(adminUser.getRoleType()==1){//超管
//				String oneAgentOpenId = RequestHandler.getString(request, "oneAgentOpenId");
//				manageadminuser.setOneAgentOpenId(oneAgentOpenId);
//				
//				String twoAgentOpenId = RequestHandler.getString(request, "twoAgentOpenId");
//				manageadminuser.setTwoAgentOpenId(twoAgentOpenId);
//				model.addAttribute("roleType", "1");
//			}else{
//				if(adminUser.getRoleId().equals(Integer.valueOf(FileUploadConstants.ONE_AGENT_SALE_ROLEID))||
//						adminUser.getRoleId().equals(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID))){
//					
//					manageadminuser.setOneAgentOpenId(adminUser.getOneAgentOpenId());
//					
//					String twoAgentOpenId = RequestHandler.getString(request, "twoAgentOpenId");
//					manageadminuser.setTwoAgentOpenId(twoAgentOpenId);
//					model.addAttribute("roleType", "2");
//					model.addAttribute("oneAgentOpenId", adminUser.getOneAgentOpenId());
//				}else if(adminUser.getRoleId().equals(Integer.valueOf(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID))){
//					model.addAttribute("roleType", "3");
//					manageadminuser.setOneAgentOpenId(adminUser.getOneAgentOpenId());
//					manageadminuser.setTwoAgentOpenId(adminUser.getTwoAgentOpenId());
//					
//				}
//			}
			
			
			int manageadminuserListCount = manageadminuserService.getManageAdminUserListCount1(manageadminuser);
			ResponseList<ManageAdminUser> manageadminuserList = null;
			if (manageadminuserListCount > 0) {
				manageadminuserList = manageadminuserService.getManageAdminUserList1(manageadminuser);
			} else {
				manageadminuserList = new ResponseList<ManageAdminUser>();
			}
			// 设置数据总数
			manageadminuserList.setTotalResults(manageadminuserListCount);
			
			writeSuccessMsg("ok", manageadminuserList, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/getManageAdminUserBaseList", method = RequestMethod.GET)
	public String getManageAdminUserBaseList(HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			ManageAdminUser manageadminuser = new ManageAdminUser();

			Integer adminId = RequestHandler.getInteger(request, "adminId");
			manageadminuser.setAdminId(adminId);

			String adminName = RequestHandler.getString(request, "adminName");
			manageadminuser.setAdminName(adminName);

			String nickName = RequestHandler.getString(request, "nickName");
			manageadminuser.setNickName(nickName);

			String passwd = RequestHandler.getString(request, "passwd");
			manageadminuser.setPasswd(passwd);

			String realName = RequestHandler.getString(request, "realName");
			manageadminuser.setRealName(realName);

			String mobile = RequestHandler.getString(request, "mobile");
			manageadminuser.setMobile(mobile);

			String phone = RequestHandler.getString(request, "phone");
			manageadminuser.setPhone(phone);

			Date lastLogin = RequestHandler.getDate(request, "lastLogin");
			manageadminuser.setLastLogin(lastLogin);

			String loginIP = RequestHandler.getString(request, "loginIP");
			manageadminuser.setLoginIP(loginIP);

			Date pwdModifyTime = RequestHandler.getDate(request, "pwdModifyTime");
			manageadminuser.setPwdModifyTime(pwdModifyTime);

			Integer state = RequestHandler.getInteger(request, "state");
			manageadminuser.setState(state);

			Date createTime = RequestHandler.getDate(request, "createTime");
			manageadminuser.setCreateTime(createTime);

			Integer createrId = RequestHandler.getInteger(request, "createrId");
			manageadminuser.setCreaterId(createrId);

			List<ManageAdminUser> manageadminuserList = manageadminuserService.getManageAdminUserBaseList(manageadminuser);

			writeSuccessMsg("ok", manageadminuserList, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/addManageAdminUser", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) {
		try {

			ManageAdminUser manageadminuser = new ManageAdminUser();

			String adminName = RequestHandler.getString(request, "adminName");
			manageadminuser.setAdminName(adminName);
			String nickName = RequestHandler.getString(request, "nickName");
			manageadminuser.setNickName(nickName);
			String passwd = RequestHandler.getString(request, "passwd");
			manageadminuser.setPasswd(MD5.getMD5ofStr(passwd));
			String realName = RequestHandler.getString(request, "realName");
			manageadminuser.setRealName(realName);
			String mobile = RequestHandler.getString(request, "mobile");
			manageadminuser.setMobile(mobile);
			String phone = RequestHandler.getString(request, "phone");
			manageadminuser.setPhone(phone);
			String headImg = RequestHandler.getString(request, "headImg");
			manageadminuser.setHeadImg(headImg);
			Date lastLogin = RequestHandler.getDate(request, "lastLogin");
			manageadminuser.setLastLogin(lastLogin);
			String loginIP = RequestHandler.getString(request, "loginIP");
			manageadminuser.setLoginIP(loginIP);
			String twoAgentOpenId = RequestHandler.getString(request, "twoAgentOpenId");
			manageadminuser.setTwoAgentOpenId(twoAgentOpenId);
			String oneAgentOpenId = RequestHandler.getString(request, "oneAgentOpenId");
//			manageadminuser.setOneAgentOpenId(oneAgentOpenId);
			Date pwdModifyTime = RequestHandler.getDate(request, "pwdModifyTime");
			manageadminuser.setPwdModifyTime(pwdModifyTime);
			Integer state = RequestHandler.getInteger(request, "state");
			manageadminuser.setState(state);
			// Date createTime = RequestHandler.getDate(request, "createTime");
			manageadminuser.setCreateTime(new Date());
			// Integer createrId = RequestHandler.getInteger(request,
			// "createrId");
			manageadminuser.setCreaterId(Integer.valueOf(request.getSession().getAttribute(SessionName.ADMIN_USER_ID).toString()));

			Integer roleId = RequestHandler.getInteger(request, "roleId");

			ManageAdminUser manageadminuser1 = new ManageAdminUser();
			manageadminuser1.setAdminName(adminName);
			int count = manageadminuserService.getManageAdminUserListCount(manageadminuser1);// 用户名验证
			if (count != 0) {
				writeErrorMsg("用户名不能重复", null, response);
				return null;
			}
			if(roleId!=null&&roleId==Integer.valueOf(FileUploadConstants.SERVICER_MANAGE_ROLEID)){
				
				//获取默认银联支付鼓励金
				HPayGuli hPayGuli = new HPayGuli();
				hPayGuli.setIs_default(1);
				hPayGuli.setState(1);
				hPayGuli.setOpObject(3);
				List<HPayGuli> listpaygl = hpayguliService.getHPayGuliBaseList(hPayGuli);
				if(listpaygl!=null&&listpaygl.size()>0){
					for(HPayGuli paygl:listpaygl){
						if(paygl.getStyle()==1){//银联
							manageadminuser.setGl_yl_id(paygl.getId());
							manageadminuser.setGl_yl_name(paygl.getName());
						}else if(paygl.getStyle()==2){
							manageadminuser.setGl_sj_id(paygl.getId());
							manageadminuser.setGl_sj_name(paygl.getName());
						}
					}
				}
			}
			Integer adminId = manageadminuserService.insertManageAdminUser(manageadminuser);

			if (roleId != null) {
				AdminUserRole adminUserRole = new AdminUserRole();
				adminUserRole.setAdminId(adminId);
				AdminUserRole adminUserRole2 = adminuserroleService.getAdminUserRole(adminUserRole);
				adminUserRole.setRoleId(roleId);
				if (adminUserRole2 == null) {
					adminuserroleService.insertAdminUserRole(adminUserRole);
				} else {
					adminUserRole2.setRoleId(roleId);
					adminuserroleService.updateAdminUserRole(adminUserRole2);
				}
			}

			if(adminId>0&&StringUtils.isNotBlank(oneAgentOpenId)){
				HCompany hCompany = new HCompany();
				hCompany.setId(Integer.valueOf(oneAgentOpenId));
				HCompany company = hcompanyService.getHCompany(hCompany);
				company.setUser_id(adminId);
				hcompanyService.updateHCompany(company);
			}
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e) {
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}

	/**
	 * 
	 * @time : 2015年2月9日 下午2:18:27
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @Description: 用于保存个人用户页面信息
	 */
	@RequestMapping(value = "/saveManageAdminUser", method = RequestMethod.POST)
	public String saveManageAdminUser(HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			ManageAdminUser manageadminuser = new ManageAdminUser();

			Integer adminId = RequestHandler.getInteger(request, "adminId");
			manageadminuser.setAdminId(adminId);

			String adminName = RequestHandler.getString(request, "adminName");
			manageadminuser.setAdminName(adminName);

			String nickName = RequestHandler.getString(request, "nickName");
			if (nickName != null) {
				nickName = URLDecoder.decode(nickName, "UTF-8");
				manageadminuser.setNickName(nickName);
			}

			String realName = RequestHandler.getString(request, "realName");

			if (realName != null) {
				realName = URLDecoder.decode(realName, "UTF-8");
				manageadminuser.setRealName(realName);
			}

			String mobile = RequestHandler.getString(request, "mobile");
			manageadminuser.setMobile(mobile);

			String phone = RequestHandler.getString(request, "phone");
			manageadminuser.setPhone(phone);

			Date lastLogin = RequestHandler.getDate(request, "lastLogin");
			manageadminuser.setLastLogin(lastLogin);

			String loginIP = RequestHandler.getString(request, "loginIP");
			manageadminuser.setLoginIP(loginIP);

			String twoAgentOpenId = RequestHandler.getString(request, "twoAgentOpenId");
			manageadminuser.setTwoAgentOpenId(twoAgentOpenId);
			String oneAgentOpenId = RequestHandler.getString(request, "oneAgentOpenId");
			manageadminuser.setOneAgentOpenId(oneAgentOpenId);
			
			Date pwdModifyTime = RequestHandler.getDate(request, "pwdModifyTime");
			manageadminuser.setPwdModifyTime(pwdModifyTime);

			Integer state = RequestHandler.getInteger(request, "state");
			manageadminuser.setState(state);

			Date createTime = RequestHandler.getDate(request, "createTime");
			manageadminuser.setCreateTime(createTime);

			Integer createrId = RequestHandler.getInteger(request, "createrId");
			manageadminuser.setCreaterId(createrId);

			String headImg = RequestHandler.getString(request, "headImg");
			manageadminuser.setHeadImg(headImg);

			// Integer roleId = RequestHandler.getInteger(request, "roleId");

			ManageAdminUser manageadminuser1 = new ManageAdminUser();
			manageadminuser1.setAdminName(adminName);
			int count = manageadminuserService.getManageAdminUserListCount(manageadminuser1);// 用户名验证
			if (count != 0) {
				writeErrorMsg("用户名不能重复", null, response);
				return null;
			}

			manageadminuserService.updateManageAdminUser(manageadminuser);

			manageadminuser = new ManageAdminUser();
			manageadminuser.setAdminId(adminId);

			ManageAdminUser adminUser = manageadminuserService.getManageAdminUser(manageadminuser);
			String passwd = RequestHandler.getString(request, "passwd");
			if (!adminUser.getPasswd().equals(passwd)) {// 密码已修改
				manageadminuser.setPasswd(MD5.getMD5ofStr(passwd));
				manageadminuser.setPwdModifyTime(new Date());
				manageadminuserService.updateManageAdminUser(manageadminuser);
				writeSuccessMsg("login", null, response);
			} else {
				writeSuccessMsg("ok", null, response);
			}

			request.getSession().setAttribute(SessionName.ADMIN_USER, adminUser);

		} catch (Exception e) {
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}

	@RequestMapping(value = "/updateManageAdminUser", method = RequestMethod.POST)
	public String updateManageAdminUser(HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			ManageAdminUser manageadminuser = new ManageAdminUser();

			Integer adminId = RequestHandler.getInteger(request, "adminId");
			manageadminuser.setAdminId(adminId);

			String adminName = RequestHandler.getString(request, "adminName");
			manageadminuser.setAdminName(adminName);

			String nickName = RequestHandler.getString(request, "nickName");
			manageadminuser.setNickName(nickName);

			String realName = RequestHandler.getString(request, "realName");
			manageadminuser.setRealName(realName);

			String mobile = RequestHandler.getString(request, "mobile");
			manageadminuser.setMobile(mobile);

			String phone = RequestHandler.getString(request, "phone");
			manageadminuser.setPhone(phone);

			Date lastLogin = RequestHandler.getDate(request, "lastLogin");
			manageadminuser.setLastLogin(lastLogin);

			String loginIP = RequestHandler.getString(request, "loginIP");
			manageadminuser.setLoginIP(loginIP);

			Date pwdModifyTime = RequestHandler.getDate(request, "pwdModifyTime");
			manageadminuser.setPwdModifyTime(pwdModifyTime);

			Integer state = RequestHandler.getInteger(request, "state");
			manageadminuser.setState(state);
			
			Integer isFirst = RequestHandler.getInteger(request, "isFirst");
			manageadminuser.setIsFirst(isFirst);

			Date createTime = RequestHandler.getDate(request, "createTime");
			manageadminuser.setCreateTime(createTime);

			Integer createrId = RequestHandler.getInteger(request, "createrId");
			manageadminuser.setCreaterId(createrId);

			String headImg = RequestHandler.getString(request, "headImg");
			manageadminuser.setHeadImg(headImg);

			String twoAgentOpenId = RequestHandler.getString(request, "twoAgentOpenId");
			manageadminuser.setTwoAgentOpenId(twoAgentOpenId);
			String companyId = RequestHandler.getString(request, "companyId");
//			manageadminuser.setOneAgentOpenId(oneAgentOpenId);
			
			Integer roleId = RequestHandler.getInteger(request, "roleId");

			if (roleId != null) {
				AdminUserRole adminUserRole = new AdminUserRole();
				adminUserRole.setAdminId(adminId);
				AdminUserRole adminUserRole2 = adminuserroleService.getAdminUserRole(adminUserRole);
				adminUserRole.setRoleId(roleId);
				if (adminUserRole2 == null) {
					adminuserroleService.insertAdminUserRole(adminUserRole);
				} else {
					adminUserRole2.setRoleId(roleId);
					adminuserroleService.updateAdminUserRole(adminUserRole2);
				}
				
				if(roleId.equals(Integer.valueOf(FileUploadConstants.COMPANY_ROLE_ID))){
					HCompany hCompany = new HCompany();
					hCompany.setId(Integer.valueOf(companyId));
					hCompany = hcompanyService.getHCompany(hCompany);
					if(hCompany!=null){
						hCompany.setContact_phone(mobile);
						hCompany.setContact_name(realName);
						hcompanyService.updateHCompany(hCompany);
					}
				}else if(roleId.equals(Integer.valueOf(FileUploadConstants.SERVICER_MANAGE_ROLEID))){
					HCompany hCompany = new HCompany();
					hCompany.setServicerId(adminId);
					hCompany.setServicerName(realName);
					hcompanyService.updateServicerName(hCompany);
				}
			}
			manageadminuserService.updateManageAdminUser(manageadminuser);

			manageadminuser = new ManageAdminUser();
			manageadminuser.setAdminId(adminId);

			ManageAdminUser adminUser = manageadminuserService.getManageAdminUser(manageadminuser);
			String passwd = RequestHandler.getString(request, "passwd");
			if (!adminUser.getPasswd().equals(passwd)) {// 密码已修改
				manageadminuser.setPasswd(MD5.getMD5ofStr(passwd));
				manageadminuser.setPwdModifyTime(new Date());
				manageadminuserService.updateManageAdminUser(manageadminuser);
			}

			Object sessionUserId = request.getSession().getAttribute(SessionName.ADMIN_USER_ID);
			if (adminId.equals(sessionUserId)) {
				request.getSession().setAttribute(SessionName.ADMIN_USER, adminUser);
			}
			
//			if(adminId>0&&StringUtils.isNotBlank(oneAgentOpenId)){
//				//原单位删除
//				HCompany hCompany1 = new HCompany();
//				hCompany1.setUser_id(adminId);
//				HCompany company1 = hcompanyService.getHCompany(hCompany1);
//				if(company1!=null&&company1.getId()>0){
//					company1.setUser_id(null);
//					hcompanyService.updateHCompany1(company1);
//				}
//				//新单位添加
//				HCompany hCompany = new HCompany();
//				hCompany.setId(Integer.valueOf(oneAgentOpenId));
//				HCompany company = hcompanyService.getHCompany(hCompany);
//				company.setUser_id(adminId);
//				hcompanyService.updateHCompany(company);
//			}

			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e) {
			e.printStackTrace();
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}

	@RequestMapping(value = "/removeManageAdminUser", method = RequestMethod.POST)
	public String removeManageAdminUser(HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			ManageAdminUser manageadminuser = new ManageAdminUser();
			Integer adminId = RequestHandler.getInteger(request, "adminId");
			manageadminuser.setAdminId(adminId);

			manageadminuserService.removeManageAdminUser(manageadminuser);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e) {
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}

	@RequestMapping(value = "/removeAllManageAdminUser", method = RequestMethod.POST)
	public String removeAllManageAdminUser(HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			String adminIds = RequestHandler.getString(request, "adminIds");
			if (adminIds != null) {
				String[] adminIdStr = adminIds.split(",");
				if (adminIdStr != null && adminIdStr.length != 0) {
					for (String adminId : adminIdStr) {
						ManageAdminUser manageAdminUser = new ManageAdminUser();
						manageAdminUser.setAdminId(Integer.valueOf(adminId));
						manageadminuserService.removeManageAdminUser(manageAdminUser);
					}
				}
			}
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e) {
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}

	@RequestMapping("/pcrimg")
	public void crimg(HttpServletRequest request, HttpServletResponse response) throws IOException {
		switch (random.nextInt(5)) {
		case 0:
			cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));
			break;
		case 1:
			cs.setFilterFactory(new MarbleRippleFilterFactory());
			break;
		case 2:
			cs.setFilterFactory(new DoubleRippleFilterFactory());
			break;
		case 3:
			cs.setFilterFactory(new WobbleRippleFilterFactory());
			break;
		case 4:
			cs.setFilterFactory(new DiffuseRippleFilterFactory());
			break;
		}
		HttpSession session = request.getSession(false);
		if (session == null) {
			session = request.getSession();
		}
		setResponseHeaders(response);
		String token = EncoderHelper.getChallangeAndWriteImage(cs, "png", response.getOutputStream());
		session.setAttribute(SessionName.TOKEN, token);
	}
	protected void setResponseHeaders(HttpServletResponse response) {
		response.setContentType("image/png");
		response.setHeader("Cache-Control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		long time = System.currentTimeMillis();
		response.setDateHeader("Last-Modified", time);
		response.setDateHeader("Date", time);
		response.setDateHeader("Expires", time);
	}
	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
	public String exportExcel(HttpServletRequest request, HttpServletResponse response, Model model) {
		ResultRsp rsp = new ResultRsp();
		try {
			ManageAdminUser manageadminuser = new ManageAdminUser();

			Integer adminId = RequestHandler.getInteger(request, "adminId");
			manageadminuser.setAdminId(adminId);

			String adminName = RequestHandler.getString(request, "adminName");
			manageadminuser.setAdminName(adminName);

			String nickName = RequestHandler.getString(request, "nickName");
			manageadminuser.setNickName(nickName);

			String passwd = RequestHandler.getString(request, "passwd");
			manageadminuser.setPasswd(passwd);

			String realName = RequestHandler.getString(request, "realName");
			manageadminuser.setRealName(realName);

			String mobile = RequestHandler.getString(request, "mobile");
			manageadminuser.setMobile(mobile);

			String phone = RequestHandler.getString(request, "phone");
			manageadminuser.setPhone(phone);

			Date lastLogin = RequestHandler.getDate(request, "lastLogin");
			manageadminuser.setLastLogin(lastLogin);

			String loginIP = RequestHandler.getString(request, "loginIP");
			manageadminuser.setLoginIP(loginIP);
			
			
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			String startTime = RequestHandler.getString(request, "startTime");
			if(StringUtils.isNotBlank(startTime)){
				manageadminuser.setStartTime(sf.parse(startTime));
			}
			
			String endTime = RequestHandler.getString(request, "endTime");
			if(StringUtils.isNotBlank(endTime)){
				manageadminuser.setEndTime(sf.parse(endTime));
			}

			Date pwdModifyTime = RequestHandler.getDate(request, "pwdModifyTime");
			manageadminuser.setPwdModifyTime(pwdModifyTime);

			Integer state = RequestHandler.getInteger(request, "state");
			manageadminuser.setState(state);

			Date createTime = RequestHandler.getDate(request, "createTime");
			manageadminuser.setCreateTime(createTime);

			Integer createrId = RequestHandler.getInteger(request, "createrId");
			manageadminuser.setCreaterId(createrId);
			
			Integer roleId = RequestHandler.getInteger(request, "roleId");
			manageadminuser.setRoleId(roleId);
			
			Integer companyId = RequestHandler.getInteger(request, "companyId");
			manageadminuser.setCompanyId(companyId);

			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			manageadminuser.setSortColumn(" a.createTime desc ");
			
			ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			
			int manageadminuserListCount = manageadminuserService.getManageAdminUserListCount(manageadminuser);
			if (manageadminuserListCount > 0) {
				List manageadminuserList = manageadminuserService.getManageAdminUserBaseList(manageadminuser);
				LinkedList list = new LinkedList();
				list.addAll(manageadminuserList);
				LinkedList fields = new LinkedList();
				fields.add("adminName");
				fields.add("nickName");
				fields.add("realName");
				fields.add("mobile");
				fields.add("phone");
				fields.add("roleName");
				fields.add("companyName");
				fields.add("lastLogin");
				fields.add("state");
				fields.add("createTime");
				LinkedList titles = new LinkedList();
				titles.add("用户名");
				titles.add("昵称");
				titles.add("真实姓名");
				titles.add("手机");
				titles.add("电话");
				titles.add("角色名称");
				titles.add("公司名称");
				titles.add("最后登录日期");
				titles.add("状态");
				titles.add("创建日期");
				String path = hCommonService.excleExport(list, fields, titles, ManageAdminUser.class, "用户管理",request);
				writeSuccessMsg("成功", path, response);
			} else {
				writeErrorMsg("无可导出内容", null, response);
			}
		} catch (Exception e) {
			writeErrorMsg("系统错误", null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/checkAdminName", method = RequestMethod.POST)
	public String checkAdminName(HttpServletRequest request, HttpServletResponse response, Model model) {
		String adminName = RequestHandler.getString(request, "adminName");
		Integer adminId = RequestHandler.getInteger(request, "adminId");
		try{
			ManageAdminUser manageadminuser = new ManageAdminUser();
			manageadminuser.setAdminId(adminId);
			manageadminuser.setAdminName(adminName);
			int count = manageadminuserService.checkAdminName(manageadminuser);
			if(count>0){
				writeErrorMsg("error", null, response);
			}else{
				writeSuccessMsg("ok", null, response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/getManageAdminUserPP", method = RequestMethod.GET)
	public String getManageAdminUserPP(HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			ManageAdminUser manageadminuser = new ManageAdminUser();

			Integer adminId = RequestHandler.getInteger(request, "adminId");
			manageadminuser.setAdminId(adminId);

			String adminName = RequestHandler.getString(request, "adminName");
			manageadminuser.setAdminName(adminName);

			String nickName = RequestHandler.getString(request, "nickName");
			manageadminuser.setNickName(nickName);

			String passwd = RequestHandler.getString(request, "passwd");
			manageadminuser.setPasswd(passwd);

			String realName = RequestHandler.getString(request, "realName");
			manageadminuser.setRealName(realName);

			String mobile = RequestHandler.getString(request, "mobile");
			manageadminuser.setMobile(mobile);

			String phone = RequestHandler.getString(request, "phone");
			manageadminuser.setPhone(phone);

			Date lastLogin = RequestHandler.getDate(request, "lastLogin");
			manageadminuser.setLastLogin(lastLogin);

			String loginIP = RequestHandler.getString(request, "loginIP");
			manageadminuser.setLoginIP(loginIP);

			Date pwdModifyTime = RequestHandler.getDate(request, "pwdModifyTime");
			manageadminuser.setPwdModifyTime(pwdModifyTime);

			Integer state = RequestHandler.getInteger(request, "state");
			manageadminuser.setState(state);

			Date createTime = RequestHandler.getDate(request, "createTime");
			manageadminuser.setCreateTime(createTime);

			Integer createrId = RequestHandler.getInteger(request, "createrId");
			manageadminuser.setCreaterId(createrId);
			
			Integer roleId = RequestHandler.getInteger(request, "roleId");
			manageadminuser.setRoleId(roleId);
			
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			String startTime = RequestHandler.getString(request, "startTime");
			if(StringUtils.isNotBlank(startTime)){
				manageadminuser.setStartTime(sf.parse(startTime));
			}
			
			String endTime = RequestHandler.getString(request, "endTime");
			if(StringUtils.isNotBlank(endTime)){
				manageadminuser.setEndTime(sf.parse(endTime));
			}
			
			Integer companyId = RequestHandler.getInteger(request, "companyId");
			manageadminuser.setCompanyId(companyId);

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");

			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			manageadminuser.setRowStart(from);
			manageadminuser.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			manageadminuser.setSortColumn(" a.createTime desc ");
			
			ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			
			//查询登陆用户角色
			if(adminUser.getRoleType()==1){//超管
				String oneAgentOpenId = RequestHandler.getString(request, "oneAgentOpenId");
				manageadminuser.setOneAgentOpenId(oneAgentOpenId);
				
				String twoAgentOpenId = RequestHandler.getString(request, "twoAgentOpenId");
				manageadminuser.setTwoAgentOpenId(twoAgentOpenId);
				model.addAttribute("roleType", "1");
			}else{
				if(adminUser.getRoleId().equals(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID))){
					
					manageadminuser.setOneAgentOpenId(adminUser.getOneAgentOpenId());
					
					String twoAgentOpenId = RequestHandler.getString(request, "twoAgentOpenId");
					manageadminuser.setTwoAgentOpenId(twoAgentOpenId);
					model.addAttribute("roleType", "2");
					model.addAttribute("oneAgentOpenId", adminUser.getOneAgentOpenId());
				}else if(adminUser.getRoleId().equals(Integer.valueOf(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID))){
					model.addAttribute("roleType", "3");
					manageadminuser.setOneAgentOpenId(adminUser.getOneAgentOpenId());
					manageadminuser.setTwoAgentOpenId(adminUser.getTwoAgentOpenId());
					
				}else if(adminUser.getRoleId().equals(Integer.valueOf(FileUploadConstants.SERVICER_MANAGE_ROLEID))){
					model.addAttribute("roleType", "4");
					manageadminuser.setServicerId(adminUser.getAdminId());
					
				}
			}
			
			manageadminuser.setRoleId(Integer.valueOf(FileUploadConstants.SERVICER_MANAGE_ROLEID));
			int manageadminuserListCount = manageadminuserService.getManageAdminUserPPCount(manageadminuser);
			ResponseList<ManageAdminUser> manageadminuserList = null;
			if (manageadminuserListCount > 0) {
				manageadminuserList = manageadminuserService.getManageAdminUserPP(manageadminuser);
			} else {
				manageadminuserList = new ResponseList<ManageAdminUser>();
			}
			// 设置数据总数
			manageadminuserList.setTotalResults(manageadminuserListCount);

			writeSuccessMsg("ok", manageadminuserList, response);
		} catch (Exception e) {
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
			ManageAdminUser manageAdminUser = new ManageAdminUser();
			manageAdminUser.setAdminId(id);
			manageAdminUser.setGl_sj_id(glsjid);
			manageAdminUser.setGl_sj_name(glsjname);
			int count = manageadminuserService.updateManageAdminUser(manageAdminUser);
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
			ManageAdminUser manageAdminUser = new ManageAdminUser();
			manageAdminUser.setAdminId(id);
			manageAdminUser.setGl_yl_id(glylid);
			manageAdminUser.setGl_yl_name(glylname);
			int count = manageadminuserService.updateManageAdminUser(manageAdminUser);
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
