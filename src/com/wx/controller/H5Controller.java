package com.wx.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.management.IntrospectionException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.base.controller.BaseController;
import com.base.payment.service.PaymentClient;
import com.base.payment.service.PaymentClientImpl;
import com.base.utils.DemoBase;
import com.base.utils.FileUploadConstants;
import com.base.utils.MD5;
import com.base.utils.MapUtil;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.utils.ResponseUtil;
import com.base.utils.SendMsgUtil;
import com.base.utils.SessionName;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hxt.hAgent.model.HAgent;
import com.hxt.hAgent.service.HAgentService;
import com.hxt.hAgentTwo.model.HAgentTwo;
import com.hxt.hAgentTwo.service.HAgentTwoService;
import com.hxt.hAmmeterInfo.model.HAmmeterInfo;
import com.hxt.hAmmeterInfo.service.HAmmeterInfoService;
import com.hxt.hArea.service.HAreaService;
import com.hxt.hCommon.controller.HCommonController;
import com.hxt.hCommon.service.HCommonService;
import com.hxt.hCompany.model.HCompany;
import com.hxt.hCompany.service.HCompanyService;
import com.hxt.hEvaluate.model.HEvaluate;
import com.hxt.hEvaluate.service.HEvaluateService;
import com.hxt.hFp.model.HFp;
import com.hxt.hFp.service.HFpService;
import com.hxt.hKtPay.model.HKtPay;
import com.hxt.hKtPay.service.HKtPayService;
import com.hxt.hMessagePhone.service.HMessagePhoneService;
import com.hxt.hPay.model.HPay;
import com.hxt.hPay.service.HPayService;
import com.hxt.hPayOrder.model.HPayOrder;
import com.hxt.hPayOrder.service.HPayOrderService;
import com.hxt.hProfitRatio.model.HProfitRatio;
import com.hxt.hProfitRatio.service.HProfitRatioService;
import com.hxt.hProxyMessage.model.HProxyMessage;
import com.hxt.hProxyMessage.service.HProxyMessageService;
import com.hxt.hReviewUser.model.HReviewUser;
import com.hxt.hSubCompany.model.HSubCompany;
import com.hxt.hSubCompany.service.HSubCompanyService;
import com.hxt.hSubOrder.model.HSubOrder;
import com.hxt.hSubOrder.service.HSubOrderService;
import com.hxt.hUserAccount.service.HUserAccountService;
import com.hxt.wap.controller.AmmeterManagerController;
import com.hxt.wap.service.CallProcedureService;
import com.sys.manageAdminUser.model.ManageAdminUser;
import com.sys.manageAdminUser.service.ManageAdminUserService;
import com.wx.utils.ErrorMsg;

@Controller
@RequestMapping("/H5")
public class H5Controller extends BaseController {

	Logger log = Logger.getLogger(H5Controller.class);

	@Autowired
	private HPayService hpayService = null;
	@Autowired
	private HEvaluateService hevaluateService = null;
	@Autowired
	private ManageAdminUserService manageadminuserService = null;// 用户
	@Autowired
	private HProxyMessageService hproxymessageService = null;
	@Autowired
	private HAmmeterInfoService hAmmeterInfoService;// 电表
	@Autowired
	private HCommonService hCommonService;
	@Autowired
	private HSubCompanyService hSubCompanyService;// 电表发票
	@Autowired
	private HAgentTwoService hagenttwoService = null;
	@Autowired
	private HAgentService hagentService = null;
	@Autowired
	private HPayOrderService hPayOrderService;
	@Autowired
	private HSubOrderService hSubOrderService;
	@Autowired
	private HCompanyService hcompanyService = null;
	@Autowired
	private HUserAccountService huseraccountService = null;
	@Autowired
	private CallProcedureService callProcedureService;
	@Autowired
	private HFpService hfpService = null;
	@Autowired
	private HKtPayService hktpayService = null;
	@Autowired
	private HProfitRatioService hprofitratioService = null;
	@Autowired
	private HAreaService hareaService = null;
	@Autowired
	private HMessagePhoneService hmessagephoneService = null;

	@RequestMapping("/whatPay")
	public String weixin(HttpServletResponse response,
			HttpServletRequest request, Model model) {
		HKtPay hKtPay = new HKtPay();
		HKtPay hKtPay1 = hktpayService.getHKtPay(hKtPay);
		model.addAttribute("hPay", hKtPay1);
		return "/wx/h5/whatPay";
	}
	
	/**
	 * 测试页面
	 * 
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/test")
	public String test(HttpServletResponse response,
			HttpServletRequest request, Model model) {
		String openId = RequestHandler.getString(request, "openId");
		model.addAttribute("openId", openId);
		return "/wx/tixing";
	}

	/**
	 * 忘记密码
	 * 
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/toForgetPwd")
	public String toForgetPwd(HttpServletResponse response,
			HttpServletRequest request, Model model) {
		String openId = RequestHandler.getString(request, "openId");
		model.addAttribute("openId", openId);
		return "/wx/h5/forgetPwd";
	}

	/**
	 * 发送验证码
	 * 
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/sendCode")
	public String sendCode(HttpServletResponse response,HttpServletRequest request, Model model) {
		String phone = RequestHandler.getString(request, "phone");
		String content = RequestHandler.getString(request, "content");
		String vercode = RequestHandler.getString(request, "vercode");
		Object token = request.getSession().getAttribute(SessionName.TOKEN);// 验证码
		try {
			if (request.getSession().getAttribute("errCount") != null) {
				if (Integer.valueOf(request.getSession().getAttribute("errCount").toString()) >= 2) {
					if (StringUtils.isBlank(vercode)) {
						writeErrorMsg("请输入验证码", "", response);
						return null;
					}
					if (token == null|| !vercode.equalsIgnoreCase(token.toString())) {
						writeErrorMsg("验证码不正确", "", response);
						return null;
					}
				}
			}
			String randomCode = SendMsgUtil.getRandomCode(4);
			content = content + randomCode;
			int s = SendMsgUtil.sendMsg(phone, content);
			if (s > 0) {
				SendMsgUtil.TIMEMAP.put(phone, new Date());
				SendMsgUtil.CODEMAP.put(phone, randomCode);
				writeSuccessMsg("发送成功", null, response);
			} else {
				writeErrorMsg("发送失败", "", response);
			}
		} catch (Exception e) {
			writeErrorMsg("发送失败", "", response);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 发送验证码
	 * 
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/sendCode1")
	public String sendCode1(HttpServletResponse response,HttpServletRequest request, Model model) {
		String phone = RequestHandler.getString(request, "phone");
		String content = RequestHandler.getString(request, "content");
		String vercode = RequestHandler.getString(request, "vercode");
		Object token = request.getSession().getAttribute(SessionName.TOKEN);// 验证码
		try {
			if (StringUtils.isBlank(vercode)) {
				writeErrorMsg("请输入验证码", "", response);
				return null;
			}
			if (token == null || !vercode.equalsIgnoreCase(token.toString())) {
				writeErrorMsg("验证码不正确", "", response);
				return null;
			}
			String randomCode = SendMsgUtil.getRandomCode(4);
			content = content + randomCode;
			int s = SendMsgUtil.sendMsg(phone, content);
			if (s > 0) {
				SendMsgUtil.TIMEMAP.put(phone, new Date());
				SendMsgUtil.CODEMAP.put(phone, randomCode);
				writeSuccessMsg("发送成功", null, response);
			} else {
				writeErrorMsg("发送失败", "", response);
			}
		} catch (Exception e) {
			writeErrorMsg("发送失败", "", response);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 验证代付
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/checkVercode")
	public String checkVercode(HttpServletRequest request,HttpServletResponse response, Model model) {
		try {
			String phone = RequestHandler.getString(request, "phone");
			String pwd = RequestHandler.getString(request, "pwd");
			String imgcode = RequestHandler.getString(request, "imgcode");
			String openId = RequestHandler.getString(request, "openId");
			Object token = request.getSession().getAttribute(SessionName.TOKEN);// 验证码
//			Integer errCount = 0;
//			if (request.getSession().getAttribute("errCount") != null) {
//				errCount = Integer.valueOf(request.getSession()
//						.getAttribute("errCount").toString());
//			}
//			String sysCode = SendMsgUtil.CODEMAP.get(phone);
			
			if (StringUtils.isNotBlank(phone)&& StringUtils.isNotBlank(imgcode)&& imgcode.equals(token)) {
				
				//

				ManageAdminUser manageAdminUser = new ManageAdminUser();
				manageAdminUser.setAdminName(phone);
				manageAdminUser.setPasswd(new MD5().getMD5ofStr(pwd));
				manageAdminUser.setRoleId(Integer.valueOf(FileUploadConstants.PROXY_ROLEID));
				manageAdminUser = manageadminuserService.getManageAdminUser1(manageAdminUser);
				if (manageAdminUser != null) {
					if(manageAdminUser.getState() == 1){
						if(StringUtils.isNotBlank(manageAdminUser.getOpenId())&&!manageAdminUser.getOpenId().equals(openId)){
							writeErrorMsg("该账号已被别的微信账号绑定，无法登陆，详情请咨询010-96199",null,response);
							return null;
						}
						// 判断机构是否正常
						HProxyMessage hProxyMessage = new HProxyMessage();
						hProxyMessage.setUserId(manageAdminUser.getAdminId());
						hProxyMessage = hproxymessageService.getHProxyMessage(hProxyMessage);
						if (hProxyMessage.getState() == 1&& (hProxyMessage.getCheckState() == 1 || hProxyMessage.getCheckState() == 7|| hProxyMessage.getCheckState() == 8|| hProxyMessage.getCheckState() == 6)) {
							HCompany hCompany = new HCompany();
							hCompany.setId(hProxyMessage.getcId());
							hCompany = hcompanyService.getHCompany(hCompany);
							if(hCompany!=null&&hCompany.getStatus()!=null&&hCompany.getVerify_status()==1){
								//验证公司登陆账户
								ManageAdminUser adminUser2 = new ManageAdminUser();
								adminUser2.setAdminId(hCompany.getUser_id());
								adminUser2 = manageadminuserService.getManageAdminUser1(adminUser2);
								if(adminUser2!=null&&adminUser2.getState()==1){
									//判断openId是否已经绑定
									ManageAdminUser manageAdminUser111 = new ManageAdminUser();
									manageAdminUser111.setOpenId(openId);
									int count = manageadminuserService.getManageAdminUserListCount(manageAdminUser111);
									if(count>0){
										writeErrorMsg("该账号已被别的微信账号绑定，无法登陆，详情请咨询010-96199",null,response);
									}else{
										manageAdminUser.setOpenId(openId);
										manageadminuserService.updateManageAdminUser(manageAdminUser);
										ManageAdminUser manageAdminUser1 = new ManageAdminUser();
										manageAdminUser1.setAdminId(manageAdminUser.getAdminId());
										manageAdminUser1 = manageadminuserService.getManageAdminUser1(manageAdminUser1);

										request.getSession().setAttribute(SessionName.ADMIN_USER_NAME,manageAdminUser1.getAdminName());
										request.getSession().setAttribute(SessionName.ADMIN_USER_ID,manageAdminUser1.getAdminId());
										request.getSession().setAttribute(SessionName.ADMIN_USER, manageAdminUser1);
										writeSuccessMsg("success", null, response);
									}
								}else{
									writeErrorMsg("您的注册账户已被禁用，详情请咨询010-96199", null, response);
								}
							}else{
								writeErrorMsg("您的单位账户已被暂停，详情请咨询010-96199!",null,response);
							}
						} else {
							if(hProxyMessage.getCheckState() == 3||hProxyMessage.getCheckState() == 4||hProxyMessage.getCheckState() == 5){
								writeErrorMsg("您单位已经中止本服务，详情请咨询010-96199", null, response);
							}else{
								writeErrorMsg("您的手机缴费账户已被禁用，详情请咨询010-96199", null, response);
							}
						}
					}else{
						writeErrorMsg("您的注册账户已被禁用，详情请咨询010-96199", null, response);
					}
				} else {
					writeErrorMsg("您输入的账号或密码错误，请重新输入", null, response);
				}

			} else {
				writeErrorMsg("验证码不正确",null, response);
			}
		} catch (Exception e) {
			writeErrorMsg("登录不上，详情请咨询010-96199", null, response);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toIndex")
	public String toIndex(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return "/wx/h5/index";
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/unbind")
	public String unbind(HttpServletRequest request,HttpServletResponse response, Model model) {
		try {
			ManageAdminUser manageAdminUser = (ManageAdminUser) request.getSession().getAttribute(SessionName.ADMIN_USER);
			int ss = manageadminuserService.unBindWx(manageAdminUser);
			if (ss > 0) {
				writeSuccessMsg("success", null, response);
			} else {
				writeErrorMsg("error", null, response);
			}
		} catch (Exception e) {
			writeErrorMsg("error", null, response);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/forgetPwd")
	public String forgetPwd(HttpServletRequest request,HttpServletResponse response, Model model) {
		String phone = RequestHandler.getString(request, "phone");
		String vercode = RequestHandler.getString(request, "vercode");
		try {
			if (StringUtils.isNotBlank(phone)&& StringUtils.isNotBlank(vercode)) {
				String sysCode = SendMsgUtil.CODEMAP.get(phone);
				if (sysCode.equals(vercode)) {
					SendMsgUtil.CODEMAP.remove(phone);
					ManageAdminUser manageAdminUser = new ManageAdminUser();
					manageAdminUser.setAdminName(phone);
					manageAdminUser.setRoleId(Integer.valueOf(FileUploadConstants.PROXY_ROLEID));
					int count = manageadminuserService.getManageAdminUserListCount(manageAdminUser);
					if (count == 1) {
						manageAdminUser = manageadminuserService.getManageAdminUser(manageAdminUser);
						if (manageAdminUser.getState() == 1) {
							request.getSession().setAttribute(SessionName.ADMIN_USER_NAME,manageAdminUser.getAdminName());
							request.getSession().setAttribute(SessionName.ADMIN_USER_ID,manageAdminUser.getAdminId());
							request.getSession().setAttribute(SessionName.ADMIN_USER, manageAdminUser);
							writeSuccessMsg("success", null, response);
						} else {
							writeErrorMsg("您的账号已被禁用，详情请咨询010-96199。", null,response);
						}
					} else {
						writeErrorMsg("账号信息异常，请联系管理员", null, response);
					}
				} else {
					writeErrorMsg("手机验证码错误", null, response);
				}
			} else {
				writeErrorMsg("输入信息有误", null, response);
			}
		} catch (Exception e) {
			writeErrorMsg("系统异常", null, response);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toUpdatePwd")
	public String toUpdatePwd(HttpServletRequest request,HttpServletResponse response, Model model) {
		return "/wx/h5/updatePwd";
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/updatePwd")
	public String updatePwd(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String pwd = RequestHandler.getString(request, "pwd");
		try {
			ManageAdminUser manageAdminUser = (ManageAdminUser) request.getSession().getAttribute(SessionName.ADMIN_USER_NAME);
			ManageAdminUser manageAdminUser1 = new ManageAdminUser();
			manageAdminUser1.setAdminId(manageAdminUser.getAdminId());
			manageAdminUser1.setPasswd(new MD5().getMD5ofStr(pwd));
			int ss = manageadminuserService.updateManageAdminUser(manageAdminUser1);
			if (ss > 0) {
				request.getSession().removeAttribute(SessionName.ADMIN_USER_NAME);
				request.getSession().removeAttribute(SessionName.ADMIN_USER_ID);
				request.getSession().removeAttribute(SessionName.ADMIN_USER);
				writeSuccessMsg("success", null, response);
			} else {
				writeErrorMsg("重置失败", null, response);
			}
		} catch (Exception e) {
			writeErrorMsg("系统异常", null, response);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toPay")
	public String toPay(HttpServletRequest request,HttpServletResponse response, Model model) {
		try {
			ManageAdminUser manageAdminUser = (ManageAdminUser) request.getSession().getAttribute(SessionName.ADMIN_USER);
			List<Map> resultList = new ArrayList();
			String IP = this.getIpAddr(request);
			int feeTotal = 0;// 欠费总金额
			int yufeeTotal = 0;//
			String yu = null;
			int ammeterCount1 = 0;
			HSubCompany hSubCompanyQry = new HSubCompany();
			hSubCompanyQry.setC_id(manageAdminUser.getCompanyId());
			hSubCompanyQry.setSortColumn("create_time desc");
			List<HSubCompany> hSubCompanyList = hSubCompanyService.getHSubCompanyBaseList(hSubCompanyQry);
			if (hSubCompanyList != null && hSubCompanyList.size() > 0) {
				for (HSubCompany invoce : hSubCompanyList) {
					int ammeterCount = 0;
					int subOwedSum = 0;// 子单位缴费金额
					Map<String, Object> dataMap = new HashMap<String, Object>();// 数据
					List<HAmmeterInfo> hAmmeterInfos = new ArrayList<HAmmeterInfo>();// 电表信息
					Integer sId = invoce.getS_id();
					if (sId != null) {
						HAmmeterInfo hAmmeterInfoQry = new HAmmeterInfo();
						hAmmeterInfoQry.setS_id(sId);
						hAmmeterInfoQry.setDelete_state(1);
						hAmmeterInfoQry.setProxy_flag(1);
						hAmmeterInfos = hAmmeterInfoService.getHAmmeterInfoBaseList(hAmmeterInfoQry);
						for (HAmmeterInfo hAmmeterInfo : hAmmeterInfos) {
							if ("1".equals(hAmmeterInfo.getPay_status())) {
								String ammeterNo = hAmmeterInfo.getAmmeter_no();// 电表号
								JSONObject result = hCommonService.hXTServiceQuery(ammeterNo, IP);
								if ("00".equals(result.get("resultCode"))) {// 获取电表信息成功
									ammeterCount = ammeterCount + 1;
									ammeterCount1 = ammeterCount1 + 1;
									Integer fee = Integer.valueOf(result.get("totalFeeStr") == null ? "0": result.get("totalFeeStr").toString());
									System.out.println("-------fee-----------" + fee);
									feeTotal = feeTotal + fee;
									if (fee >= 0) {
										if (fee % 10000 == 0 && fee >0) {
											yufeeTotal = yufeeTotal + fee;
											if (yu == null) {
												yu = ammeterNo;
											} else {
												yu = yu + "," + ammeterNo;
											}
										}
										hAmmeterInfo.setTotalFee(fee);
										hAmmeterInfo.setNow_totalFee(super.getMoney(fee));
										if (fee > 0) {
											subOwedSum = subOwedSum + fee;// 子单位欠费
										}
									}
									if (StringUtils.isNotBlank(result.getJSONObject("resultInfo").getString("accountName"))) {
										hAmmeterInfo.setAmmeter_name(result.getJSONObject("resultInfo").getString("accountName"));
										hAmmeterInfoService.updateHAmmeterInfo(hAmmeterInfo);
									}
								} else {// 获取电表信息失败
									hAmmeterInfo.setTotalFee(0);
									hAmmeterInfo.setNow_totalFee(super.getMoney(0));
								}
							}
						}
						invoce.setAmmeterCount(ammeterCount);
						invoce.setTotalFeeStr(super.getMoney(subOwedSum));
						dataMap = MapUtil.convertBean(invoce);
						dataMap.put("ammeters", hAmmeterInfos);
						ammeterCount1 = hAmmeterInfos.size();
					}
					resultList.add(dataMap);
				}
			}
			model.addAttribute("all_total_fee", super.getMoney(feeTotal));
			model.addAttribute("resultList", resultList);
			model.addAttribute("yu", yu);
			model.addAttribute("ammeterCount1", ammeterCount1);
			model.addAttribute("yufeeTotal", super.getMoney(yufeeTotal));
		} catch (Exception e) {
			writeErrorMsg("error", null, response);
			e.printStackTrace();
		}
		return "/wx/h5/pay";
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toAddGroup")
	public String toAddGroup(HttpServletRequest request,HttpServletResponse response, Model model) {
		return "/wx/h5/addGroup";
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/jiaofei")
	public synchronized String jiaofei(HttpServletRequest request,HttpServletResponse response, Model model) {
		String pwd = RequestHandler.getString(request, "pwd");
		Integer cId =  RequestHandler.getInteger(request, "cId");
        Integer subId = RequestHandler.getInteger(request, "subId");
        String payType = RequestHandler.getString(request, "payType");
        String no = RequestHandler.getString(request, "no");
        
		ManageAdminUser manageAdminUser1 = (ManageAdminUser) request.getSession().getAttribute(SessionName.ADMIN_USER);
		try {
			if (StringUtils.isNotBlank(pwd)&& (new MD5().getMD5ofStr(pwd)).equals(manageAdminUser1.getPasswd())) {
				 HPayOrder hPayOrderQur = new HPayOrder();
				 hPayOrderQur.setO_no(no);
				 hPayOrderQur = hPayOrderService.getHPayOrder(hPayOrderQur);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//				
//				Map<String, Object> resultMap = new HashMap<String, Object>();//返回数据
//		        List<Map> resultList = new ArrayList();
//		        if(cId==null){
//		        	writeErrorMsg("获取公司信息失败", null, response);
//		        }else {
//		        	//获取信息
		        	HProxyMessage hProxyMessage = new HProxyMessage();
					hProxyMessage.setUserId(manageAdminUser1.getAdminId());
					hProxyMessage = hproxymessageService.getHProxyMessage(hProxyMessage);
//		        	//公司缴费
//		        	if(StringUtils.isNotBlank(payType)&&payType.equals("1")){
//		    		  HPayOrder hPayOrderQur = new HPayOrder();
//		              hPayOrderQur.setC_id(cId);
//		              hPayOrderQur.setPay_status("0");//未支付
//		              hPayOrderQur.setSub_id(-1);
//		              HPayOrder findHPayOrder = hPayOrderService.findTodayHPayOrder(hPayOrderQur);
//
//		              String IP = this.getIpAddr(request);
//
//		              HSubCompany hSubCompanyQry = new HSubCompany();
//		              hSubCompanyQry.setC_id(cId);
//		              List<HSubCompany> hSubCompanyList = new ArrayList<HSubCompany>();//字典表
//		              hSubCompanyQry.setSortColumn("create_time desc");
//		              hSubCompanyList = hSubCompanyService.getHSubCompanyBaseList(hSubCompanyQry);
//		              //校验订单支付金额是否与欠费金额相等
//		              boolean flag = true;
//		              if(findHPayOrder!=null){
//		              	Integer amount = findHPayOrder.getAmount();//订单金额
//		              	log.info("--------验证订单amount----------->"+amount);
//		              	flag = checkPayOrderSum(hSubCompanyList,IP,amount);
//		              }
//		              if(findHPayOrder!=null && flag){//当天存在支付订单，不重复下单 直接跳转支付页面
//		            	//发送末班消息
//		            	  HCompany hCompany1 = new HCompany();
//		                  hCompany1.setId(cId);
//		                  HCompany hCompany = hcompanyService.getHCompany(hCompany1);
//						  ManageAdminUser manageAdminUser = new ManageAdminUser();
//						  manageAdminUser.setOneAgentOpenId(hCompany.getOneAgentOpenId());
//						  manageAdminUser.setRoleId(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
//						  ManageAdminUser user = manageadminuserService.getManageAdminUser(manageAdminUser);
//						  HAgent agent = new HAgent();
//						  agent.setStatus(1);
//						  agent.setOpenId(hCompany.getOneAgentOpenId());
//						  agent = hagentService.getHAgent(agent);
//						  //获取对应的openId
//						  String toOPENID = null;
//						  if(user!=null&&user.getState()==1&&agent!=null&&agent.getStatus()==1&&agent.getCheck_status()==1){
//						     toOPENID = user.getOpenId();
//						     huseraccountService.sendJFTempltMsg(toOPENID, super.getMoney(findHPayOrder.getAmount()), findHPayOrder.getO_no(), hCompany.getContact_name(), hCompany.getContact_phone(), hCompany.getId(), hCompany.getName());
//						  }
//						  //获取服务人员
//		                  if(hCompany!=null&&hCompany.getServicerId()!=null){
//		                	  ManageAdminUser manageAdminUser3 = new ManageAdminUser();
//		    				  manageAdminUser3.setAdminId(hCompany.getServicerId());
//		    				  ManageAdminUser user3 = manageadminuserService.getManageAdminUser(manageAdminUser3);
//		    				  if(user3!=null&&user3.getState()==1){
//		    					  huseraccountService.sendJFTempltMsg(user3.getOpenId(), super.getMoney(findHPayOrder.getAmount()), findHPayOrder.getO_no(), hCompany.getContact_name(), hCompany.getContact_phone(), hCompany.getId(), hCompany.getName());
//		    				  }
//		                  }
					if (manageAdminUser1.getState()==1&&hProxyMessage.getState() == 1&& (hProxyMessage.getCheckState() == 1 || hProxyMessage.getCheckState() == 7|| hProxyMessage.getCheckState() == 8|| hProxyMessage.getCheckState() == 6)) {
						
					
		                  String oso = HCommonController.maporder.get(no);
	   					  if(StringUtils.isNotBlank(oso)){
	   						  HPayOrder hPayOrderQur1 = new HPayOrder();
		   						hPayOrderQur1.setO_no(no);
		   						hPayOrderQur1 = hPayOrderService.getHPayOrder(hPayOrderQur1);
		   						if("1".equals(hPayOrderQur1.getPay_status())){
		   							writeSuccessMsg("success", hPayOrderQur.getO_no()+"_"+hPayOrderQur1.getQuery_id()+"_"+hPayOrderQur1.getAmount()+"_成功", response);
		   						}else{
		   							writeErrorMsg("支付失败", hPayOrderQur.getO_no()+"_"+hPayOrderQur1.getQuery_id()+"_"+hPayOrderQur1.getAmount()+"_失败", response);
		   						}
	   					  }else{
	   						  String sss = null;
	   						  HCommonController.maporder.put(no, no);
	   						  System.out.println(hPayOrderQur.getPay_status());
	   						  if("0".equals(hPayOrderQur.getPay_status())||hPayOrderQur.getPay_status()==null){
	   							//调用手机代收接口
				                  Long fee = Long.valueOf(hPayOrderQur.getAmount());
				  				  PaymentClient client = new PaymentClientImpl();
				  				  String sqe = client.getSQE();
				  				  sss = sqe;
//				  				  String returnCode = client.daishou(hProxyMessage.getUserNumber(), FileUploadConstants.SF_BANK_NUMBER, FileUploadConstants.SF_BANK_ACCOUNT, hProxyMessage.getQsBank(), null, hProxyMessage.getPayBankNumber(), hProxyMessage.getProxyName(), "00100", hProxyMessage.getContractNumber(), "CNY", fee+"",sqe);// 交易序号n16);
				  				  String returnCode = client.daishou(hProxyMessage.getUserNumber(), FileUploadConstants.SF_BANK_NUMBER, FileUploadConstants.SF_BANK_ACCOUNT, hProxyMessage.getQsBank(), hProxyMessage.getBank_number(), hProxyMessage.getPayBankNumber(), hProxyMessage.getPayAccountName(), "00100", hProxyMessage.getContractNumber(), "CNY", fee+"",sqe);// 交易序号n16);
					  			  log.info("----------------no------------------------"+returnCode);
				  				  String returnCode111 = "";
				  				  String settleDate = "";
				  				  String returnCode1 = "";
								  if(StringUtils.isNotBlank(returnCode)&&returnCode.length()>3&&returnCode.indexOf("_")!=-1){
									  String[] returnCodes = returnCode.split("_");
									  returnCode1 = returnCodes[0];
									  returnCode111 = returnCode1.substring(returnCode1.length()-3, returnCode1.length());
									  settleDate = returnCodes[1];
								  }else{
									  returnCode1 = returnCode;
								  }
				  				  if("000".equals(returnCode111)){//成功，进入销账流程
									  hPayOrderQur.setPay_type("2");
				  					  hPayOrderService.updateHPayOrder(hPayOrderQur);
				  					  if(manageAdminUser1!=null&&StringUtils.isNotBlank(manageAdminUser1.getOpenId())){
				  						  huseraccountService.sendProxyTempltSuccessMsg(manageAdminUser1.getOpenId(), hProxyMessage.getSex(), hProxyMessage.getPayName(), no, fee);
				  					  }
				  					  //进入销账流程
				  					  Map<String, String> valideData = new HashMap<String, String>();
				  					  valideData.put("merOrderId", hPayOrderQur.getO_no());//商户订单号
				  					  valideData.put("merTxnAmt", fee+"");//交易金额
				  					  valideData.put("merTxnTime", sdf.format(new Date()));//交易时间
				  					  valideData.put("queryId", sqe);//银行交易流水号
				  					  valideData.put("settleDate", settleDate);//清算日期
				  					  valideData.put("respCode", "00");//支付成功
				  					  valideData.put("Source", "ACT1");
				  					  hCommonService.tickOffPayOrder(valideData, this.getIpAddr(request));
				  					  writeSuccessMsg("success", hPayOrderQur.getO_no()+"_"+sqe+"_"+fee+"_"+returnCode1, response);
				  				  }else{
				  					 HCommonController.maporder.remove(no);
				  					  if(manageAdminUser1!=null&&StringUtils.isNotBlank(manageAdminUser1.getOpenId())){
										  huseraccountService.sendProxyTempltFailMsg(manageAdminUser1.getOpenId(), hProxyMessage.getSex(), hProxyMessage.getPayName(), no, fee,returnCode1);
									  }
				  					  String msg = ErrorMsg.mapErrorMsg.get(returnCode1);
				  					hmessagephoneService.sendMessage(sss,hProxyMessage.getProxyName());
				  					  writeErrorMsg("支付失败", hPayOrderQur.getO_no()+"_"+sqe+"_"+fee+"_"+returnCode1+" "+msg, response);
				  				  }
	   						  }else{
	   							HPayOrder hPayOrderQur1 = new HPayOrder();
		   						hPayOrderQur1.setO_no(no);
		   						hPayOrderQur1 = hPayOrderService.getHPayOrder(hPayOrderQur1);
		   						if("1".equals(hPayOrderQur1.getPay_status())){
		   							writeSuccessMsg("success", hPayOrderQur.getO_no()+"_"+hPayOrderQur1.getQuery_id()+"_"+hPayOrderQur1.getAmount()+"_成功", response);
		   						}else{
		   							hmessagephoneService.sendMessage(sss,hProxyMessage.getProxyName());
		   							writeErrorMsg("支付失败", hPayOrderQur.getO_no()+"_"+hPayOrderQur1.getQuery_id()+"_"+hPayOrderQur1.getAmount()+"_失败", response);
		   						}
	   						  }
	   					  }
					}else{
						writeErrorMsg("支付失败", hPayOrderQur.getO_no()+"_ _ _您的账号被已被禁用", response);
					}
//		              }else{//重新生成支付订单
//		                  HCompany hCompany1 = new HCompany();
//		                  hCompany1.setId(cId);
//		                  HCompany hCompany = hcompanyService.getHCompany(hCompany1);
//
//
//		                  
//		                  boolean is_zz = false;//是否包含增值税发票
//		                  int ammeterSum = 0;//总电表数
//		                  int owedAmmeterSum = 0;//欠费电表数
//		                  StringBuffer sb = new StringBuffer();
//		                  int rowsTotal = 0;
//		                  int feeTotal = 0;
//		                  List<HSubOrder> subOrderList = new ArrayList<HSubOrder>();
//		                  try {
//		                      for (HSubCompany invoce : hSubCompanyList) {
//		                          Map<String, Object> dataMap = new HashMap<String, Object>();//数据
//		                          List<HAmmeterInfo> hAmmeterInfos = new ArrayList<HAmmeterInfo>();//电表信息
//		                          dataMap = MapUtil.convertBean(invoce);
//		                          Integer sId = invoce.getS_id();
//		                          if (sId != null) {
//		                              HAmmeterInfo hAmmeterInfoQry = new HAmmeterInfo();
//		                              hAmmeterInfoQry.setS_id(sId);
//		                              hAmmeterInfoQry.setPay_status("1");
//		                              hAmmeterInfoQry.setDelete_state(1);
//		                              hAmmeterInfoQry.setProxy_flag(1);
//		                              hAmmeterInfos = hAmmeterInfoService.getHAmmeterInfoBaseList(hAmmeterInfoQry);
//		                              for (HAmmeterInfo hAmmeterInfo : hAmmeterInfos) {
//		                                  ammeterSum++;
//		                                  String ammeterNo = hAmmeterInfo.getAmmeter_no();//电表号
//		                                  if(hAmmeterInfo.getFp_style()==null){
//		                                  	is_zz = true;
//		                                  }
//		                                  JSONObject result = hCommonService.hXTServiceQuery(ammeterNo, IP);
//		                                  if ("00".equals(result.get("resultCode"))) {//获取电表信息成功
//		                                  	Integer fee = Integer.valueOf(result.get("totalFeeStr") == null ? "0" : result.get("totalFeeStr").toString());
//		                                  	feeTotal = feeTotal + fee;
//		                                      if (fee >= 0) {
//		                                          hAmmeterInfo.setTotalFee(fee);
//		                                          if (fee > 0) {
//		                                              owedAmmeterSum++;//欠费表数
//		                                              sb.append(ammeterNo + ",");
//		                                              //插入子订单信息
//		                                              HSubOrder hSubOrder = new HSubOrder();
//		                                              hSubOrder.setC_id(cId);
//		                                              hSubOrder.setSub_id(sId);
////		                                                  hSubOrder.setO_id(oNo);
//		                                              hSubOrder.setElectric(ammeterNo);//电表编号
//		                                              hSubOrder.setAmount(fee);//欠费金额
//		                                              //获取分组信息
//		                                              HSubCompany subcom = new HSubCompany();
//		                                              subcom.setS_id(sId);
//		                                              subcom = hSubCompanyService.getHSubCompany(subcom);
//		                                              if(subcom!=null){
//		                                            	  hSubOrder.setSub_name(subcom.getSub_name());
//		                                            	  hSubOrder.setConsignee(subcom.getConsignee());
//		                                            	  String adress = "";
//		                                            	  if(StringUtils.isNotBlank(subcom.getProvince_name())){
//		                                            		  adress = adress + subcom.getProvince_name();
//		                                            	  }
//		                                            	  
//		                                            	  if(StringUtils.isNotBlank(subcom.getCity_name())){
//		                                            		  adress = adress + subcom.getCity_name();
//		                                            	  }
//		                                            	  
//		                                            	  if(StringUtils.isNotBlank(subcom.getArea_name())){
//		                                            		  adress = adress + subcom.getArea_name();
//		                                            	  }
//		                                            	  
//		                                            	  if(StringUtils.isNotBlank(subcom.getConsignee_address())){
//		                                            		  adress = adress + subcom.getConsignee_address();
//		                                            	  }
//		                                            	  hSubOrder.setConsignee_address(adress);
//		                                            	  hSubOrder.setConsignee_phone(subcom.getConsignee_phone());
//		                                              }
//		                                              subOrderList.add(hSubOrder);
//		                                          }
//		                                      }
//		                                  } else {//获取电表信息失败
//
//		                                  }
//		                              }
//		                              dataMap.put("ammeters", hAmmeterInfos);
//		                              if(hAmmeterInfos.size()>0){
//		                                  rowsTotal++;
//		                                  resultList.add(dataMap);
//		                              }
//		                          }
//		                      }
//
//		                      if (sb.length() > 0) {//存在欠费电表
//		                      	System.out.println("-----------money2-------------"+feeTotal);
//		                    	  String oNo = createOrder(IP,is_zz,feeTotal,cId,hCompany.getYwyId(),subOrderList,sb,null);//解决订单重复问题
//		                    	  //发送末班消息
//			                      if(hCompany!=null&&hCompany.getOneAgentOpenId()!=null){
//			                    	  ManageAdminUser manageAdminUser = new ManageAdminUser();
//				  					  manageAdminUser.setOneAgentOpenId(hCompany.getOneAgentOpenId());
//				  					  manageAdminUser.setRoleId(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
//				  					  ManageAdminUser user = manageadminuserService.getManageAdminUser(manageAdminUser);
//				  					  //获取一级机构
//				  					  HAgent hAgent = new HAgent();
//				  					  hAgent.setOpenId(hCompany.getOneAgentOpenId());
//				  					  hAgent = hagentService.getHAgent(hAgent);
//				  					  //获取对应的openId
//				  					  String toOPENID = null;
//				  					  if(user!=null&&user.getState()==1&&hAgent!=null&&hAgent.getStatus()==1&&hAgent.getCheck_status()==1){
//				  					     toOPENID = user.getOpenId();
//				  					     huseraccountService.sendJFTempltMsg(toOPENID, super.getMoney(feeTotal), oNo, hCompany.getContact_name(), hCompany.getContact_phone(), hCompany.getId(), hCompany.getName());
//				  					  }
//			                      }
//			  					  
//		                    	  
//			                      //获取服务人员
//			                      if(hCompany!=null&&hCompany.getServicerId()!=null){
//			                    	  ManageAdminUser manageAdminUser3 = new ManageAdminUser();
//			        				  manageAdminUser3.setAdminId(hCompany.getServicerId());
//			        				  ManageAdminUser user3 = manageadminuserService.getManageAdminUser(manageAdminUser3);
//			        				  if(user3!=null&&user3.getState()==1){
//			        					  huseraccountService.sendJFTempltMsg(user3.getOpenId(), super.getMoney(feeTotal), oNo, hCompany.getContact_name(), hCompany.getContact_phone(), hCompany.getId(), hCompany.getName());
//			        				  }
//			                      }
//			                      String oso = HCommonController.maporder.get(oNo);
//			   					  if(StringUtils.isNotBlank(oso)){
//			   							
//			   					  }else{
//			   						//调用手机代收接口
//					                  Long fee = Long.valueOf(feeTotal);
//					  				  PaymentClient client = new PaymentClientImpl();
//					  				  String sqe = client.getSQE();
////					  				  String returnCode = client.daishou(hProxyMessage.getUserNumber(), FileUploadConstants.SF_BANK_NUMBER, FileUploadConstants.SF_BANK_ACCOUNT, hProxyMessage.getQsBank(), null, hProxyMessage.getPayBankNumber(), hProxyMessage.getProxyName(), "00100", hProxyMessage.getContractNumber(), "CNY", fee+"",sqe);// 交易序号n16);
//					  				  String returnCode = client.daishou(hProxyMessage.getUserNumber(), FileUploadConstants.SF_BANK_NUMBER, FileUploadConstants.SF_BANK_ACCOUNT, hProxyMessage.getBank_number(), null, hProxyMessage.getPayBankNumber(), hProxyMessage.getProxyName(), "00100", hProxyMessage.getContractNumber(), "CNY", fee+"",sqe);// 交易序号n16);
//					  				  String returnCode111 = "";
//					  				  if(StringUtils.isNotBlank(returnCode)&&returnCode.length()>3){
//											returnCode111 = returnCode.substring(returnCode.length()-3, returnCode.length());
//									  }
//									  if("000".equals(returnCode111)){//成功，进入销账流程
//					  					  if(manageAdminUser1!=null&&StringUtils.isNotBlank(manageAdminUser1.getOpenId())){
//					  						  huseraccountService.sendProxyTempltSuccessMsg(manageAdminUser1.getOpenId(), hProxyMessage.getSex(), hProxyMessage.getPayName(), oNo, fee);
//					  					  }
//					  					  //进入销账流程
//					  					  Map<String, String> valideData = new HashMap<String, String>();
//					  					  valideData.put("merOrderId", oNo);//商户订单号
//					  					  valideData.put("merTxnAmt", fee+"");//交易金额
//					  					  valideData.put("merTxnTime", sdf.format(new Date()));//交易时间
//					  					  valideData.put("queryId", sqe);//银行交易流水号
//					  					  valideData.put("settleDate", sdf.format(new Date()).substring(4, 8));//清算日期
//					  					  valideData.put("respCode", "00");//支付成功
//					  					  hCommonService.tickOffPayOrder(valideData, this.getIpAddr(request));
//					  					  writeSuccessMsg("success", oNo+"_"+sqe+"_"+fee+"_"+returnCode, response);
//					  				  }else{
//					  					  if(manageAdminUser1!=null&&StringUtils.isNotBlank(manageAdminUser1.getOpenId())){
//											  huseraccountService.sendProxyTempltFailMsg(manageAdminUser1.getOpenId(), hProxyMessage.getSex(), hProxyMessage.getPayName(), oNo, fee,returnCode);
//										  }
//					  					  writeErrorMsg("支付失败", oNo+"_"+sqe+"_"+fee+"_"+returnCode, response);
//					  				  }
//			   					  }
//		                      }else{
//		                    	  writeErrorMsg("您单位登记的所有电表缴费及时，没有欠费，感谢使用。", null, response);
//		                      }
//		                  } catch (Exception e) {
//		                      e.printStackTrace();
//		                      writeErrorMsg("支付订单生成失败", null, response);
//		                  }
//		              }
//		        	}else if(StringUtils.isNotBlank(payType)&&payType.equals("2")&&subId!=null){//子单位缴费
//		        		HPayOrder hPayOrderQur = new HPayOrder();
//		                hPayOrderQur.setSub_id(subId);
//		                hPayOrderQur.setPay_status("0");//未支付
//		                HPayOrder findHPayOrder = hPayOrderService.findTodayHPayOrder(hPayOrderQur);
//		                String IP = this.getIpAddr(request);
//		                HAmmeterInfo ammeterParam = new HAmmeterInfo();
//		                ammeterParam.setS_id(subId);
//		                ammeterParam.setDelete_state(1);
//		                ammeterParam.setAmmeterinfo_type(1);
//		                ammeterParam.setPay_status("1");
//		                ammeterParam.setProxy_flag(1);
//		                List<HAmmeterInfo> ammeterList = hAmmeterInfoService.getHAmmeterInfoBaseList(ammeterParam);
//		                //校验订单支付金额是否与欠费金额相等
//		                boolean flag = true;
//		                if(findHPayOrder!=null){
//		                	Integer amount = findHPayOrder.getAmount();//订单金额
//		                	log.info("--------验证订单amount----------->"+amount);
//		                	flag = checkPayOrderSumSub(ammeterList,IP,amount);
//		                }
//		                if(findHPayOrder!=null && flag){//当天存在支付订单，不重复下单 直接跳转支付页面
//		                	//发送末班消息
//		              	  	HCompany hCompany1 = new HCompany();
//		                    hCompany1.setId(cId);
//		                    HCompany hCompany = hcompanyService.getHCompany(hCompany1);
//		                    
//		                  if(hCompany!=null&&hCompany.getOneAgentOpenId()!=null){
//		                	  ManageAdminUser manageAdminUser = new ManageAdminUser();
//		      				  manageAdminUser.setOneAgentOpenId(hCompany.getOneAgentOpenId());
//		      				  manageAdminUser.setRoleId(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
//		      				  ManageAdminUser user = manageadminuserService.getManageAdminUser(manageAdminUser);
//		      				  //获取一级机构
//		    				  HAgent hAgent = new HAgent();
//		    				  hAgent.setOpenId(hCompany.getOneAgentOpenId());
//		    				  hAgent = hagentService.getHAgent(hAgent);
//		      				  //获取对应的openId
//		      				  String toOPENID = null;
//		      				  if(user!=null&&user.getState()==1&&hAgent!=null&&hAgent.getCheck_status()==1&&hAgent.getStatus()==1){
//		      				     toOPENID = user.getOpenId();
//		      				     huseraccountService.sendJFTempltMsg(toOPENID, super.getMoney(findHPayOrder.getAmount()), findHPayOrder.getO_no(), hCompany.getContact_name(), hCompany.getContact_phone(), hCompany.getId(), hCompany.getName());
//		      				  }
//		                  }
//		                  //获取服务人员
//		                  if(hCompany!=null&&hCompany.getServicerId()!=null){
//		                	  ManageAdminUser manageAdminUser3 = new ManageAdminUser();
//		    				  manageAdminUser3.setAdminId(hCompany.getServicerId());
//		    				  ManageAdminUser user3 = manageadminuserService.getManageAdminUser(manageAdminUser3);
//		    				  if(user3!=null&&user3.getState()==1){
//		    					  huseraccountService.sendJFTempltMsg(user3.getOpenId(), super.getMoney(findHPayOrder.getAmount()), findHPayOrder.getO_no(), hCompany.getContact_name(), hCompany.getContact_phone(), hCompany.getId(), hCompany.getName());
//		    				  }
//		                  }
//		              	  
//		                 //判断是否已经回调
//						 String oso = HCommonController.maporder.get(findHPayOrder.getO_no());
//						 if(StringUtils.isNotBlank(oso)){
//							
//						 }else{
//							//调用手机代收接口
//			                  Long fee = Long.valueOf(findHPayOrder.getAmount());
//			  				  PaymentClient client = new PaymentClientImpl();
//			  				  String sqe = client.getSQE();
////			  				  String returnCode = client.daishou(hProxyMessage.getUserNumber(), FileUploadConstants.SF_BANK_NUMBER, FileUploadConstants.SF_BANK_ACCOUNT, hProxyMessage.getQsBank(), null, hProxyMessage.getPayBankNumber(), hProxyMessage.getProxyName(), "00100", hProxyMessage.getContractNumber(), "CNY", fee+"",sqe);// 交易序号n16);
//			  				  String returnCode = client.daishou(hProxyMessage.getUserNumber(), FileUploadConstants.SF_BANK_NUMBER, FileUploadConstants.SF_BANK_ACCOUNT, hProxyMessage.getBank_number(), null, hProxyMessage.getPayBankNumber(), hProxyMessage.getProxyName(), "00100", hProxyMessage.getContractNumber(), "CNY", fee+"",sqe);// 交易序号n16);
//			  				  String returnCode111 = "";
//			  				  if(StringUtils.isNotBlank(returnCode)&&returnCode.length()>3){
//									returnCode111 = returnCode.substring(returnCode.length()-3, returnCode.length());
//							  }
//							  if("000".equals(returnCode111)){//成功，进入销账流程
//			  					  findHPayOrder.setPay_type("2");
//			  					  hPayOrderService.updateHPayOrder(findHPayOrder);
//			  					  if(manageAdminUser1!=null&&StringUtils.isNotBlank(manageAdminUser1.getOpenId())){
//			  						  huseraccountService.sendProxyTempltSuccessMsg(manageAdminUser1.getOpenId(), hProxyMessage.getSex(), hProxyMessage.getPayName(), findHPayOrder.getO_no(), fee);
//			  					  }
//			  					  //进入销账流程
//			  					  Map<String, String> valideData = new HashMap<String, String>();
//			  					  valideData.put("merOrderId", findHPayOrder.getO_no());//商户订单号
//			  					  valideData.put("merTxnAmt", fee+"");//交易金额
//			  					  valideData.put("merTxnTime", sdf.format(new Date()));//交易时间
//			  					  valideData.put("queryId", sqe);//银行交易流水号
//			  					  valideData.put("settleDate", sdf.format(new Date()).substring(4, 8));//清算日期
//			  					  valideData.put("respCode", "00");//支付成功
//			  					  hCommonService.tickOffPayOrder(valideData, this.getIpAddr(request));
//			  					  writeSuccessMsg("success", findHPayOrder.getO_no()+"_"+sqe+"_"+fee+"_"+returnCode, response);
//			  				  }else{
//			  					  if(manageAdminUser1!=null&&StringUtils.isNotBlank(manageAdminUser1.getOpenId())){
//									  huseraccountService.sendProxyTempltFailMsg(manageAdminUser1.getOpenId(), hProxyMessage.getSex(), hProxyMessage.getPayName(), findHPayOrder.getO_no(), fee,returnCode);
//								  }
//			  					  writeErrorMsg("支付失败", findHPayOrder.getO_no()+"_"+sqe+"_"+fee+"_"+returnCode, response);
//			  				  }
//						 }
//		                  
//		                }else{//重新生成支付订单
//		                    HCompany hCompany1 = new HCompany();
//		                    hCompany1.setId(cId);
//		                    HCompany hCompany = hcompanyService.getHCompany(hCompany1);
//		                    boolean is_zz = false;//是否包含增值税发票
//		                    int ammeterSum = 0;//总电表数
//		                    int owedAmmeterSum = 0;//欠费电表数
//		                    StringBuffer sb = new StringBuffer();
//		                    int rowsTotal = 0;
//		                    int feeTotal = 0;
//		                    List<HSubOrder> subOrderList = new ArrayList<HSubOrder>();
//		                    try {
//		                        for (HAmmeterInfo hAmmeterInfo : ammeterList) {
//		                            ammeterSum++;
//		                            String ammeterNo = hAmmeterInfo.getAmmeter_no();//电表号
//		                            if(hAmmeterInfo.getFp_style()==null){
//		                            	is_zz = true;
//		                            }
//		                            JSONObject result = hCommonService.hXTServiceQuery(ammeterNo, IP);
//		                            if ("00".equals(result.get("resultCode"))) {//获取电表信息成功
//		                            	Integer fee = Integer.valueOf(result.get("totalFeeStr") == null ? "0" : result.get("totalFeeStr").toString());
//		                            	feeTotal = feeTotal + fee;
//		                                if (fee >= 0) {
//		                                    hAmmeterInfo.setTotalFee(fee);
//		                                    if (fee > 0) {
//		                                        owedAmmeterSum++;//欠费表数
//		                                        sb.append(ammeterNo + ",");
//		                                        //插入子订单信息
//		                                        HSubOrder hSubOrder = new HSubOrder();
//		                                        hSubOrder.setC_id(cId);
//		                                        hSubOrder.setSub_id(subId);
//		                                        hSubOrder.setElectric(ammeterNo);//电表编号
//		                                        hSubOrder.setAmount(fee);//欠费金额
//		                                        //获取分组信息
//		                                        HSubCompany subcom = new HSubCompany();
//		                                        subcom.setS_id(subId);
//		                                        subcom = hSubCompanyService.getHSubCompany(subcom);
//		                                        if(subcom!=null){
//		                                      	  hSubOrder.setSub_name(subcom.getSub_name());
//		                                      	  hSubOrder.setConsignee(subcom.getConsignee());
//		                                      	  String adress = "";
//			                                  	  if(StringUtils.isNotBlank(subcom.getProvince_name())){
//			                                  		  adress = adress + subcom.getProvince_name();
//			                                  	  }
//			                                  	  
//			                                  	  if(StringUtils.isNotBlank(subcom.getCity_name())){
//			                                  		  adress = adress + subcom.getCity_name();
//			                                  	  }
//			                                  	  
//			                                  	  if(StringUtils.isNotBlank(subcom.getArea_name())){
//			                                  		  adress = adress + subcom.getArea_name();
//			                                  	  }
//			                                  	  
//			                                  	  if(StringUtils.isNotBlank(subcom.getConsignee_address())){
//			                                  		  adress = adress + subcom.getConsignee_address();
//			                                  	  }
//			                                  	  hSubOrder.setConsignee_address(adress);
////			                                  	  hSubOrder.setConsignee_address(subcom.getProvince_name()+subcom.getCity_name()+subcom.getArea_name()+subcom.getConsignee_address());
//		                                      	  hSubOrder.setConsignee_phone(subcom.getConsignee_phone());
//		                                        }
//		                                        subOrderList.add(hSubOrder);
//		                                    }
//		                                }
//		                            } else {//获取电表信息失败
//
//		                            }
//		                        }
//		                        if (sb.length() > 0) {//存在欠费电表
//		                        	System.out.println("-----------money2-------------"+feeTotal);
//		                            //启动多线程查单
//		                      		String oNo = createOrder(IP,is_zz,feeTotal,cId,hCompany.getYwyId(),subOrderList,sb,subId);//解决订单重复问题
//		                      		 //发送末班消息
//		                      		if(hCompany!=null&&hCompany.getOneAgentOpenId()!=null){
//		  	                    	  ManageAdminUser manageAdminUser = new ManageAdminUser();
//			  	  					  manageAdminUser.setOneAgentOpenId(hCompany.getOneAgentOpenId());
//			  	  					  manageAdminUser.setRoleId(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
//			  	  					  ManageAdminUser user = manageadminuserService.getManageAdminUser(manageAdminUser);
//			  	  					 //获取一级机构
//			  	    				  HAgent hAgent = new HAgent();
//			  	    				  hAgent.setOpenId(hCompany.getOneAgentOpenId());
//			  	    				  hAgent = hagentService.getHAgent(hAgent);
//			  	  					  //获取对应的openId
//			  	  					  String toOPENID = null;
//			  	  					  if(user!=null&&user.getState()==1&&hAgent!=null&&hAgent.getStatus()==1&&hAgent.getCheck_status()==1){
//			  	  					     toOPENID = user.getOpenId();
//			  	  					     huseraccountService.sendJFTempltMsg(toOPENID, super.getMoney(feeTotal), oNo, hCompany.getContact_name(), hCompany.getContact_phone(), hCompany.getId(), hCompany.getName());
//			  	  					  }
//		                      		}
//		                      		//获取服务人员
//		                            if(hCompany!=null&&hCompany.getServicerId()!=null){
//		                          	  ManageAdminUser manageAdminUser3 = new ManageAdminUser();
//		              				  manageAdminUser3.setAdminId(hCompany.getServicerId());
//		              				  ManageAdminUser user3 = manageadminuserService.getManageAdminUser(manageAdminUser3);
//		              				  if(user3!=null&&user3.getState()==1){
//		              					  huseraccountService.sendJFTempltMsg(user3.getOpenId(), super.getMoney(feeTotal), oNo, hCompany.getContact_name(), hCompany.getContact_phone(), hCompany.getId(), hCompany.getName());
//		              				  }
//		                            }
//		                            String oso = HCommonController.maporder.get(oNo);
//			   						if(StringUtils.isNotBlank(oso)){
//			   							
//			   						}else{
//			   						//调用手机代收接口
//			  		                  	Long fee = Long.valueOf(feeTotal);
//			  		  				 	PaymentClient client = new PaymentClientImpl();
//			  		  				 	String sqe = client.getSQE();
////			  		  				 	String returnCode = client.daishou(hProxyMessage.getUserNumber(), FileUploadConstants.SF_BANK_NUMBER, FileUploadConstants.SF_BANK_ACCOUNT, hProxyMessage.getQsBank(), null, hProxyMessage.getPayBankNumber(), hProxyMessage.getProxyName(), "00100", hProxyMessage.getContractNumber(), "CNY", fee+"",sqe);// 交易序号n16);
//			  		  				 	String returnCode = client.daishou(hProxyMessage.getUserNumber(), FileUploadConstants.SF_BANK_NUMBER, FileUploadConstants.SF_BANK_ACCOUNT, hProxyMessage.getBank_number(), null, hProxyMessage.getPayBankNumber(), hProxyMessage.getProxyName(), "00100", hProxyMessage.getContractNumber(), "CNY", fee+"",sqe);// 交易序号n16);
//				  		  				 String returnCode111 = "";
//						  				  if(StringUtils.isNotBlank(returnCode)&&returnCode.length()>3){
//												returnCode111 = returnCode.substring(returnCode.length()-3, returnCode.length());
//										  }
//										  if("000".equals(returnCode111)){//成功，进入销账流程
//			  		  				 		if(manageAdminUser1!=null&&StringUtils.isNotBlank(manageAdminUser1.getOpenId())){
//			  		  				 			huseraccountService.sendProxyTempltSuccessMsg(manageAdminUser1.getOpenId(), hProxyMessage.getSex(), hProxyMessage.getPayName(), oNo, fee);
//			  		  				 		}
//			  		  				 		//进入销账流程
//			  		  				 		Map<String, String> valideData = new HashMap<String, String>();
//			  		  				 		valideData.put("merOrderId", oNo);//商户订单号
//			  		  				 		valideData.put("merTxnAmt", fee+"");//交易金额
//			  		  				 		valideData.put("merTxnTime", sdf.format(new Date()));//交易时间
//			  		  				 		valideData.put("queryId", sqe);//银行交易流水号
//			  		  				 		valideData.put("settleDate", sdf.format(new Date()).substring(4, 8));//清算日期
//			  		  				 		valideData.put("respCode", "00");//支付成功
//			  		  				 		hCommonService.tickOffPayOrder(valideData, this.getIpAddr(request));
//			  		  				 		writeSuccessMsg("success", oNo+"_"+sqe+"_"+fee+"_"+returnCode, response);
//			  		  				 	}else{
//			  		  				 		if(manageAdminUser1!=null&&StringUtils.isNotBlank(manageAdminUser1.getOpenId())){
//			  		  				 			huseraccountService.sendProxyTempltFailMsg(manageAdminUser1.getOpenId(), hProxyMessage.getSex(), hProxyMessage.getPayName(), oNo, fee,returnCode);
//			  		  				 		}
//			  		  				 		writeErrorMsg("支付失败", oNo+"_"+sqe+"_"+fee+"_"+returnCode, response);
//			  		  				 	}
//			   						}
//		                        }else{
//		                        	writeErrorMsg("您单位登记的所有电表缴费及时，没有欠费，感谢使用", null, response);
//		                        }
//		                    } catch (Exception e) {
//		                        e.printStackTrace();
//		                        writeErrorMsg("支付订单生成失败", null, response);
//		                    }
//		                }
//		        	}else{
//		        		writeErrorMsg("参数错误", null, response);
//		        	}
//		        }
			} else {
				writeErrorMsg("密码错误", null, response);
			}
		} catch (Exception e) {
			writeErrorMsg("系统异常", null, response);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toPayInfo")
	public String toPayInfo(HttpServletRequest request,HttpServletResponse response, Model model) {
		Integer cId = RequestHandler.getInteger(request, "cId");
		Integer subId = RequestHandler.getInteger(request, "subId");
		String payType = RequestHandler.getString(request, "payType");
		String totalFeeStr = RequestHandler.getString(request, "totalFeeStr");
//		ManageAdminUser manageAdminUser = (ManageAdminUser) request.getSession().getAttribute(SessionName.ADMIN_USER);
		ManageAdminUser manageAdminUser1 = (ManageAdminUser) request.getSession().getAttribute(SessionName.ADMIN_USER);
		String no = "";
		try {
			HProxyMessage hProxyMessage = new HProxyMessage();
			hProxyMessage.setUserId(manageAdminUser1.getAdminId());
			hProxyMessage = hproxymessageService.getHProxyMessage(hProxyMessage);
			model.addAttribute("hProxyMessage", hProxyMessage);
//			if (StringUtils.isNotBlank(pwd)&& (new MD5().getMD5ofStr(pwd)).equals(manageAdminUser1.getPasswd())) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				
				Map<String, Object> resultMap = new HashMap<String, Object>();//返回数据
		        List<Map> resultList = new ArrayList();
		        if(cId==null){
		        	writeErrorMsg("获取公司信息失败", null, response);
		        }else {
		        	//获取信息
//		        	HProxyMessage hProxyMessage = new HProxyMessage();
//					hProxyMessage.setUserId(manageAdminUser1.getAdminId());
//					hProxyMessage = hproxymessageService.getHProxyMessage(hProxyMessage);
		        	//公司缴费
		        	if(StringUtils.isNotBlank(payType)&&payType.equals("1")){
		    		  HPayOrder hPayOrderQur = new HPayOrder();
		              hPayOrderQur.setC_id(cId);
		              hPayOrderQur.setPay_status("0");//未支付
		              hPayOrderQur.setSub_id(-1);
		              HPayOrder findHPayOrder = hPayOrderService.findTodayHPayOrder(hPayOrderQur);

		              String IP = this.getIpAddr(request);

		              HSubCompany hSubCompanyQry = new HSubCompany();
		              hSubCompanyQry.setC_id(cId);
		              List<HSubCompany> hSubCompanyList = new ArrayList<HSubCompany>();//字典表
		              hSubCompanyQry.setSortColumn("create_time desc");
		              hSubCompanyList = hSubCompanyService.getHSubCompanyBaseList(hSubCompanyQry);
		              //校验订单支付金额是否与欠费金额相等
		              boolean flag = true;
		              if(findHPayOrder!=null){
		              	Integer amount = findHPayOrder.getAmount();//订单金额
		              	log.info("--------验证订单amount----------->"+amount);
		              	flag = checkPayOrderSum(hSubCompanyList,IP,amount);
		              }
		              if(findHPayOrder!=null && flag){//当天存在支付订单，不重复下单 直接跳转支付页面
		            	//发送末班消息
		            	  HCompany hCompany1 = new HCompany();
		                  hCompany1.setId(cId);
		                  HCompany hCompany = hcompanyService.getHCompany(hCompany1);
						  ManageAdminUser manageAdminUser = new ManageAdminUser();
						  manageAdminUser.setOneAgentOpenId(hCompany.getOneAgentOpenId());
						  manageAdminUser.setRoleId(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
						  ManageAdminUser user = manageadminuserService.getManageAdminUser(manageAdminUser);
						  HAgent agent = new HAgent();
						  agent.setStatus(1);
						  agent.setOpenId(hCompany.getOneAgentOpenId());
						  agent = hagentService.getHAgent(agent);
						  //获取对应的openId
						  String toOPENID = null;
						  if(user!=null&&user.getState()==1&&agent!=null&&agent.getStatus()==1&&agent.getCheck_status()==1){
						     toOPENID = user.getOpenId();
						     huseraccountService.sendJFTempltMsg(toOPENID, super.getMoney(findHPayOrder.getAmount()), findHPayOrder.getO_no(), hCompany.getContact_name(), hCompany.getContact_phone(), hCompany.getId(), hCompany.getName());
						  }
						  //获取服务人员
		                  if(hCompany!=null&&hCompany.getServicerId()!=null){
		                	  ManageAdminUser manageAdminUser3 = new ManageAdminUser();
		    				  manageAdminUser3.setAdminId(hCompany.getServicerId());
		    				  ManageAdminUser user3 = manageadminuserService.getManageAdminUser(manageAdminUser3);
		    				  if(user3!=null&&user3.getState()==1){
		    					  huseraccountService.sendJFTempltMsg(user3.getOpenId(), super.getMoney(findHPayOrder.getAmount()), findHPayOrder.getO_no(), hCompany.getContact_name(), hCompany.getContact_phone(), hCompany.getId(), hCompany.getName());
		    				  }
		                  }
		                  no = findHPayOrder.getO_no();
		              }else{//重新生成支付订单
		                  HCompany hCompany1 = new HCompany();
		                  hCompany1.setId(cId);
		                  HCompany hCompany = hcompanyService.getHCompany(hCompany1);


		                  
		                  boolean is_zz = false;//是否包含增值税发票
		                  int ammeterSum = 0;//总电表数
		                  int owedAmmeterSum = 0;//欠费电表数
		                  StringBuffer sb = new StringBuffer();
		                  int rowsTotal = 0;
		                  int feeTotal = 0;
		                  List<HSubOrder> subOrderList = new ArrayList<HSubOrder>();
		                  try {
		                      for (HSubCompany invoce : hSubCompanyList) {
		                          Map<String, Object> dataMap = new HashMap<String, Object>();//数据
		                          List<HAmmeterInfo> hAmmeterInfos = new ArrayList<HAmmeterInfo>();//电表信息
		                          dataMap = MapUtil.convertBean(invoce);
		                          Integer sId = invoce.getS_id();
		                          if (sId != null) {
		                              HAmmeterInfo hAmmeterInfoQry = new HAmmeterInfo();
		                              hAmmeterInfoQry.setS_id(sId);
		                              hAmmeterInfoQry.setPay_status("1");
		                              hAmmeterInfoQry.setDelete_state(1);
		                              hAmmeterInfoQry.setProxy_flag(1);
		                              hAmmeterInfos = hAmmeterInfoService.getHAmmeterInfoBaseList(hAmmeterInfoQry);
		                              for (HAmmeterInfo hAmmeterInfo : hAmmeterInfos) {
		                                  ammeterSum++;
		                                  String ammeterNo = hAmmeterInfo.getAmmeter_no();//电表号
		                                  if(hAmmeterInfo.getFp_style()==null){
		                                  	is_zz = true;
		                                  }
		                                  JSONObject result = hCommonService.hXTServiceQuery(ammeterNo, IP);
		                                  if ("00".equals(result.get("resultCode"))) {//获取电表信息成功
		                                  	Integer fee = Integer.valueOf(result.get("totalFeeStr") == null ? "0" : result.get("totalFeeStr").toString());
		                                  	feeTotal = feeTotal + fee;
		                                      if (fee >= 0) {
		                                          hAmmeterInfo.setTotalFee(fee);
		                                          if (fee > 0) {
		                                              owedAmmeterSum++;//欠费表数
		                                              sb.append(ammeterNo + ",");
		                                              //插入子订单信息
		                                              HSubOrder hSubOrder = new HSubOrder();
		                                              hSubOrder.setC_id(cId);
		                                              hSubOrder.setSub_id(sId);
//		                                                  hSubOrder.setO_id(oNo);
		                                              hSubOrder.setElectric(ammeterNo);//电表编号
		                                              hSubOrder.setAmount(fee);//欠费金额
		                                              //获取分组信息
		                                              HSubCompany subcom = new HSubCompany();
		                                              subcom.setS_id(sId);
		                                              subcom = hSubCompanyService.getHSubCompany(subcom);
		                                              if(subcom!=null){
		                                            	  hSubOrder.setSub_name(subcom.getSub_name());
		                                            	  hSubOrder.setConsignee(subcom.getConsignee());
		                                            	  String adress = "";
		                                            	  if(StringUtils.isNotBlank(subcom.getProvince_name())){
		                                            		  adress = adress + subcom.getProvince_name();
		                                            	  }
		                                            	  
		                                            	  if(StringUtils.isNotBlank(subcom.getCity_name())){
		                                            		  adress = adress + subcom.getCity_name();
		                                            	  }
		                                            	  
		                                            	  if(StringUtils.isNotBlank(subcom.getArea_name())){
		                                            		  adress = adress + subcom.getArea_name();
		                                            	  }
		                                            	  
		                                            	  if(StringUtils.isNotBlank(subcom.getConsignee_address())){
		                                            		  adress = adress + subcom.getConsignee_address();
		                                            	  }
		                                            	  hSubOrder.setConsignee_address(adress);
		                                            	  hSubOrder.setConsignee_phone(subcom.getConsignee_phone());
		                                              }
		                                              subOrderList.add(hSubOrder);
		                                          }
		                                      }
		                                  } else {//获取电表信息失败

		                                  }
		                              }
		                              dataMap.put("ammeters", hAmmeterInfos);
		                              if(hAmmeterInfos.size()>0){
		                                  rowsTotal++;
		                                  resultList.add(dataMap);
		                              }
		                          }
		                      }

		                      if (sb.length() > 0) {//存在欠费电表
		                      	System.out.println("-----------money2-------------"+feeTotal);
		                    	  String oNo = createOrder(IP,is_zz,feeTotal,cId,hCompany.getYwyId(),subOrderList,sb,null);//解决订单重复问题
		                    	  //发送末班消息
			                      if(hCompany!=null&&hCompany.getOneAgentOpenId()!=null){
			                    	  ManageAdminUser manageAdminUser = new ManageAdminUser();
				  					  manageAdminUser.setOneAgentOpenId(hCompany.getOneAgentOpenId());
				  					  manageAdminUser.setRoleId(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
				  					  ManageAdminUser user = manageadminuserService.getManageAdminUser(manageAdminUser);
				  					  //获取一级机构
				  					  HAgent hAgent = new HAgent();
				  					  hAgent.setOpenId(hCompany.getOneAgentOpenId());
				  					  hAgent = hagentService.getHAgent(hAgent);
				  					  //获取对应的openId
				  					  String toOPENID = null;
				  					  if(user!=null&&user.getState()==1&&hAgent!=null&&hAgent.getStatus()==1&&hAgent.getCheck_status()==1){
				  					     toOPENID = user.getOpenId();
				  					     huseraccountService.sendJFTempltMsg(toOPENID, super.getMoney(feeTotal), oNo, hCompany.getContact_name(), hCompany.getContact_phone(), hCompany.getId(), hCompany.getName());
				  					  }
			                      }
			  					  
		                    	  
			                      //获取服务人员
			                      if(hCompany!=null&&hCompany.getServicerId()!=null){
			                    	  ManageAdminUser manageAdminUser3 = new ManageAdminUser();
			        				  manageAdminUser3.setAdminId(hCompany.getServicerId());
			        				  ManageAdminUser user3 = manageadminuserService.getManageAdminUser(manageAdminUser3);
			        				  if(user3!=null&&user3.getState()==1){
			        					  huseraccountService.sendJFTempltMsg(user3.getOpenId(), super.getMoney(feeTotal), oNo, hCompany.getContact_name(), hCompany.getContact_phone(), hCompany.getId(), hCompany.getName());
			        				  }
			                      }
			                      no = oNo;
		                      }else{
		                    	  writeErrorMsg("您单位登记的所有电表缴费及时，没有欠费，感谢使用。", null, response);
		                      }
		                  } catch (Exception e) {
		                      e.printStackTrace();
		                      writeErrorMsg("支付订单生成失败", null, response);
		                  }
		              }
		        	}else if(StringUtils.isNotBlank(payType)&&payType.equals("2")&&subId!=null){//子单位缴费
		        		HPayOrder hPayOrderQur = new HPayOrder();
		                hPayOrderQur.setSub_id(subId);
		                hPayOrderQur.setPay_status("0");//未支付
		                HPayOrder findHPayOrder = hPayOrderService.findTodayHPayOrder(hPayOrderQur);
		                String IP = this.getIpAddr(request);
		                HAmmeterInfo ammeterParam = new HAmmeterInfo();
		                ammeterParam.setS_id(subId);
		                ammeterParam.setDelete_state(1);
		                ammeterParam.setAmmeterinfo_type(1);
		                ammeterParam.setPay_status("1");
		                ammeterParam.setProxy_flag(1);
		                List<HAmmeterInfo> ammeterList = hAmmeterInfoService.getHAmmeterInfoBaseList(ammeterParam);
		                //校验订单支付金额是否与欠费金额相等
		                boolean flag = true;
		                if(findHPayOrder!=null){
		                	Integer amount = findHPayOrder.getAmount();//订单金额
		                	log.info("--------验证订单amount----------->"+amount);
		                	flag = checkPayOrderSumSub(ammeterList,IP,amount);
		                }
		                if(findHPayOrder!=null && flag){//当天存在支付订单，不重复下单 直接跳转支付页面
		                	//发送末班消息
		              	  	HCompany hCompany1 = new HCompany();
		                    hCompany1.setId(cId);
		                    HCompany hCompany = hcompanyService.getHCompany(hCompany1);
		                    
		                  if(hCompany!=null&&hCompany.getOneAgentOpenId()!=null){
		                	  ManageAdminUser manageAdminUser = new ManageAdminUser();
		      				  manageAdminUser.setOneAgentOpenId(hCompany.getOneAgentOpenId());
		      				  manageAdminUser.setRoleId(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
		      				  ManageAdminUser user = manageadminuserService.getManageAdminUser(manageAdminUser);
		      				  //获取一级机构
		    				  HAgent hAgent = new HAgent();
		    				  hAgent.setOpenId(hCompany.getOneAgentOpenId());
		    				  hAgent = hagentService.getHAgent(hAgent);
		      				  //获取对应的openId
		      				  String toOPENID = null;
		      				  if(user!=null&&user.getState()==1&&hAgent!=null&&hAgent.getCheck_status()==1&&hAgent.getStatus()==1){
		      				     toOPENID = user.getOpenId();
		      				     huseraccountService.sendJFTempltMsg(toOPENID, super.getMoney(findHPayOrder.getAmount()), findHPayOrder.getO_no(), hCompany.getContact_name(), hCompany.getContact_phone(), hCompany.getId(), hCompany.getName());
		      				  }
		                  }
		                  //获取服务人员
		                  if(hCompany!=null&&hCompany.getServicerId()!=null){
		                	  ManageAdminUser manageAdminUser3 = new ManageAdminUser();
		    				  manageAdminUser3.setAdminId(hCompany.getServicerId());
		    				  ManageAdminUser user3 = manageadminuserService.getManageAdminUser(manageAdminUser3);
		    				  if(user3!=null&&user3.getState()==1){
		    					  huseraccountService.sendJFTempltMsg(user3.getOpenId(), super.getMoney(findHPayOrder.getAmount()), findHPayOrder.getO_no(), hCompany.getContact_name(), hCompany.getContact_phone(), hCompany.getId(), hCompany.getName());
		    				  }
		                  }
		              	  
		                  no = findHPayOrder.getO_no();
		                  
		                }else{//重新生成支付订单
		                    HCompany hCompany1 = new HCompany();
		                    hCompany1.setId(cId);
		                    HCompany hCompany = hcompanyService.getHCompany(hCompany1);
		                    boolean is_zz = false;//是否包含增值税发票
		                    int ammeterSum = 0;//总电表数
		                    int owedAmmeterSum = 0;//欠费电表数
		                    StringBuffer sb = new StringBuffer();
		                    int rowsTotal = 0;
		                    int feeTotal = 0;
		                    List<HSubOrder> subOrderList = new ArrayList<HSubOrder>();
		                    try {
		                        for (HAmmeterInfo hAmmeterInfo : ammeterList) {
		                            ammeterSum++;
		                            String ammeterNo = hAmmeterInfo.getAmmeter_no();//电表号
		                            if(hAmmeterInfo.getFp_style()==null){
		                            	is_zz = true;
		                            }
		                            JSONObject result = hCommonService.hXTServiceQuery(ammeterNo, IP);
		                            if ("00".equals(result.get("resultCode"))) {//获取电表信息成功
		                            	Integer fee = Integer.valueOf(result.get("totalFeeStr") == null ? "0" : result.get("totalFeeStr").toString());
		                            	feeTotal = feeTotal + fee;
		                                if (fee >= 0) {
		                                    hAmmeterInfo.setTotalFee(fee);
		                                    if (fee > 0) {
		                                        owedAmmeterSum++;//欠费表数
		                                        sb.append(ammeterNo + ",");
		                                        //插入子订单信息
		                                        HSubOrder hSubOrder = new HSubOrder();
		                                        hSubOrder.setC_id(cId);
		                                        hSubOrder.setSub_id(subId);
		                                        hSubOrder.setElectric(ammeterNo);//电表编号
		                                        hSubOrder.setAmount(fee);//欠费金额
		                                        //获取分组信息
		                                        HSubCompany subcom = new HSubCompany();
		                                        subcom.setS_id(subId);
		                                        subcom = hSubCompanyService.getHSubCompany(subcom);
		                                        if(subcom!=null){
		                                      	  hSubOrder.setSub_name(subcom.getSub_name());
		                                      	  hSubOrder.setConsignee(subcom.getConsignee());
		                                      	  String adress = "";
			                                  	  if(StringUtils.isNotBlank(subcom.getProvince_name())){
			                                  		  adress = adress + subcom.getProvince_name();
			                                  	  }
			                                  	  
			                                  	  if(StringUtils.isNotBlank(subcom.getCity_name())){
			                                  		  adress = adress + subcom.getCity_name();
			                                  	  }
			                                  	  
			                                  	  if(StringUtils.isNotBlank(subcom.getArea_name())){
			                                  		  adress = adress + subcom.getArea_name();
			                                  	  }
			                                  	  
			                                  	  if(StringUtils.isNotBlank(subcom.getConsignee_address())){
			                                  		  adress = adress + subcom.getConsignee_address();
			                                  	  }
			                                  	  hSubOrder.setConsignee_address(adress);
//			                                  	  hSubOrder.setConsignee_address(subcom.getProvince_name()+subcom.getCity_name()+subcom.getArea_name()+subcom.getConsignee_address());
		                                      	  hSubOrder.setConsignee_phone(subcom.getConsignee_phone());
		                                        }
		                                        subOrderList.add(hSubOrder);
		                                    }
		                                }
		                            } else {//获取电表信息失败

		                            }
		                        }
		                        if (sb.length() > 0) {//存在欠费电表
		                        	System.out.println("-----------money2-------------"+feeTotal);
		                            //启动多线程查单
		                      		String oNo = createOrder(IP,is_zz,feeTotal,cId,hCompany.getYwyId(),subOrderList,sb,subId);//解决订单重复问题
		                      		 //发送末班消息
		                      		if(hCompany!=null&&hCompany.getOneAgentOpenId()!=null){
		  	                    	  ManageAdminUser manageAdminUser = new ManageAdminUser();
			  	  					  manageAdminUser.setOneAgentOpenId(hCompany.getOneAgentOpenId());
			  	  					  manageAdminUser.setRoleId(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
			  	  					  ManageAdminUser user = manageadminuserService.getManageAdminUser(manageAdminUser);
			  	  					 //获取一级机构
			  	    				  HAgent hAgent = new HAgent();
			  	    				  hAgent.setOpenId(hCompany.getOneAgentOpenId());
			  	    				  hAgent = hagentService.getHAgent(hAgent);
			  	  					  //获取对应的openId
			  	  					  String toOPENID = null;
			  	  					  if(user!=null&&user.getState()==1&&hAgent!=null&&hAgent.getStatus()==1&&hAgent.getCheck_status()==1){
			  	  					     toOPENID = user.getOpenId();
			  	  					     huseraccountService.sendJFTempltMsg(toOPENID, super.getMoney(feeTotal), oNo, hCompany.getContact_name(), hCompany.getContact_phone(), hCompany.getId(), hCompany.getName());
			  	  					  }
		                      		}
		                      		//获取服务人员
		                            if(hCompany!=null&&hCompany.getServicerId()!=null){
		                          	  ManageAdminUser manageAdminUser3 = new ManageAdminUser();
		              				  manageAdminUser3.setAdminId(hCompany.getServicerId());
		              				  ManageAdminUser user3 = manageadminuserService.getManageAdminUser(manageAdminUser3);
		              				  if(user3!=null&&user3.getState()==1){
		              					  huseraccountService.sendJFTempltMsg(user3.getOpenId(), super.getMoney(feeTotal), oNo, hCompany.getContact_name(), hCompany.getContact_phone(), hCompany.getId(), hCompany.getName());
		              				  }
		                            }
		                            no = oNo;
		                        }else{
		                        	writeErrorMsg("您单位登记的所有电表缴费及时，没有欠费，感谢使用", null, response);
		                        }
		                    } catch (Exception e) {
		                        e.printStackTrace();
		                        writeErrorMsg("支付订单生成失败", null, response);
		                    }
		                }
		        	}else{
		        		writeErrorMsg("参数错误", null, response);
		        	}
		        }
//			} else {
//				writeErrorMsg("密码错误", null, response);
//			}
		} catch (Exception e) {
			writeErrorMsg("系统异常", null, response);
			e.printStackTrace();
		}
		
		
//		try {
//			
//			
//			s
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		model.addAttribute("cId", cId);
		model.addAttribute("no", no);
		model.addAttribute("subId", subId);
		model.addAttribute("payType", payType);
		model.addAttribute("totalFeeStr", totalFeeStr);
		return "/wx/h5/payInfo";
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toElectricInfo")
	public String toElectricInfo(HttpServletRequest request,HttpServletResponse response, Model model) {
		String electricNum = RequestHandler.getString(request, "electricNum");
		String accountName = RequestHandler.getString(request, "accountName");
		String address = RequestHandler.getString(request, "address");
		String accountFee = RequestHandler.getString(request, "accountFee");
		String lateFee = RequestHandler.getString(request, "lateFee");
		String totalFee = RequestHandler.getString(request, "totalFee");
		Integer cid = RequestHandler.getInteger(request, "cid");
		Integer sid = RequestHandler.getInteger(request, "sid");
		model.addAttribute("cid", cid);
		model.addAttribute("sid", sid);
		model.addAttribute("accountName", accountName);
		model.addAttribute("address", address);
		model.addAttribute("accountFee", accountFee);
		model.addAttribute("lateFee", lateFee);
		model.addAttribute("totalFee", totalFee);
		model.addAttribute("electricNum", electricNum);
		return "/wx/h5/electricInfo";
	}


	/**
	 * 判断欠费金额
	 * 
	 * @param hSubCompanyList
	 * @param IP
	 * @param amount
	 * @return
	 */
	public boolean checkPayOrderSumSub(List<HAmmeterInfo> ammeterList,String IP, Integer amount) {

		boolean flag = false;
		int totalFee = 0;
		for (HAmmeterInfo hAmmeterInfo : ammeterList) {
			String ammeterNo = hAmmeterInfo.getAmmeter_no();// 电表号
			JSONObject result = hCommonService.hXTServiceQuery(ammeterNo, IP);
			if ("00".equals(result.get("resultCode"))) {// 获取电表信息成功
				Integer fee = Integer.valueOf(result.get("totalFeeStr") == null ? "0": result.get("totalFeeStr").toString());// 欠费金额
				totalFee = totalFee + fee;// 欠费总额累加
			} else {// 获取电表信息失败

			}
		}
		log.info("--------子单位验证订单amount----------->" + amount);
		if (amount.equals(totalFee)) {// 欠费金额相等
			flag = true;
			log.info("==================>子单位订单欠费金额相等 amount=" + amount+ "   totalFee=" + totalFee);
		} else {
			log.info("==================>子单位订单欠费金额不相等 amount=" + amount+ "   totalFee=" + totalFee);
		}
		return flag;
	}

	/**
	 * 判断欠费金额
	 * 
	 * @param hSubCompanyList
	 * @param IP
	 * @param amount
	 * @return
	 */
	public boolean checkPayOrderSum(List<HSubCompany> hSubCompanyList,String IP, Integer amount) {

		boolean flag = false;
		int totalFee = 0;

		for (HSubCompany invoce : hSubCompanyList) {
			Map<String, Object> dataMap = new HashMap<String, Object>();// 数据
			List<HAmmeterInfo> hAmmeterInfos = new ArrayList<HAmmeterInfo>();// 电表信息
			try {
				dataMap = MapUtil.convertBean(invoce);
			} catch (IntrospectionException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			Integer sId = invoce.getS_id();
			if (sId != null) {
				HAmmeterInfo hAmmeterInfoQry = new HAmmeterInfo();
				hAmmeterInfoQry.setS_id(sId);
				hAmmeterInfoQry.setPay_status("1");
				hAmmeterInfoQry.setDelete_state(1);
				hAmmeterInfoQry.setProxy_flag(1);
				hAmmeterInfos = hAmmeterInfoService.getHAmmeterInfoBaseList(hAmmeterInfoQry);
				for (HAmmeterInfo hAmmeterInfo : hAmmeterInfos) {
					String ammeterNo = hAmmeterInfo.getAmmeter_no();// 电表号

					JSONObject result = hCommonService.hXTServiceQuery(ammeterNo, IP);
					if ("00".equals(result.get("resultCode"))) {// 获取电表信息成功
						Integer fee = Integer.valueOf(result.get("totalFeeStr") == null ? "0": result.get("totalFeeStr").toString());// 欠费金额
						totalFee = totalFee + fee;// 欠费总额累加
					} else {// 获取电表信息失败

					}
				}
			}
		}
		log.info("--------验证订单amount----------->" + amount);
		if (amount.equals(totalFee)) {// 欠费金额相等
			flag = true;
			log.info("==================>订单欠费金额相等 amount=" + amount + "   totalFee=" + totalFee);
		} else {
			log.info("==================>订单欠费金额不相等 amount=" + amount + "   totalFee=" + totalFee);
		}
		return flag;
	}

	/**
	 * 获取时间
	 * 
	 * @return
	 */
	public synchronized String getNowTime() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}

	/**
	 * 生成订单2016-8-21 01:53:02
	 * 
	 * @param IP
	 * @param is_zz
	 * @param paysum
	 * @param cId
	 * @param ywyId
	 * @param subOrderList
	 * @param sb
	 * @param subId
	 * @return
	 * @throws Exception
	 */
	public synchronized String createOrder(String IP, boolean is_zz,
			Integer paysum, Integer cId, Integer ywyId,
			List<HSubOrder> subOrderList, StringBuffer sb, Integer subId) {
		String oNo = "";
		try {
			// 排序
			Map map = new HashMap();
			map.put("snNamePre", "PO");// 编号前缀
			map.put("snName", "PaySN");// 编号名称，记录用
			map.put("num", "4");// 编号长度
			map.put("sn", "@sn");// 编号
			oNo = callProcedureService.callGenerateSnNo(map);// 订单编号

			HPayOrder hPayOrder = new HPayOrder();// 电表支付订单
			String ammeterNums = sb.toString().substring(0, sb.length() - 1);
			hPayOrder.setO_no(oNo);
			hPayOrder.setPay_status("0");
			hPayOrder.setPay_ip(IP);
			if (is_zz) {
				hPayOrder.setIs_zz(1);
			} else {
				hPayOrder.setIs_zz(0);
			}
			System.out.println("-----------paysum-------------" + paysum);
			hPayOrder.setAmount(paysum);
			hPayOrder.setC_id(cId);
			// 单位信息
			HCompany comp = new HCompany();
			comp.setId(cId);
			comp = hcompanyService.getHCompany(comp);
			if (comp != null) {
				hPayOrder.setC_name(comp.getName());
			}
			hPayOrder.setElectric_number(ammeterNums);
			hPayOrder.setYw_id(ywyId == null ? null : ywyId);
			hPayOrder.setCreate_time(new Date());
			hPayOrder.setSub_id(subId);
			hPayOrder.setPay_type("2");
			HPayOrder hPayOrderQur = new HPayOrder();
			if (subId == null) {
				hPayOrderQur.setPay_status("0");
				hPayOrderQur.setC_id(cId);
				hPayOrderQur.setSub_id(-1);
			} else {
				hPayOrderQur.setC_id(cId);
				hPayOrderQur.setPay_status("0");// 未支付
				hPayOrderQur.setSub_id(subId);
			}
			// 插入代理数据
			HCompany company = new HCompany();
			company.setId(cId);
			company = hcompanyService.getHCompany(company);
			if (company != null) {
				if (company.getOneAgentOpenId() != null) {
					ManageAdminUser manageAdminUser = new ManageAdminUser();
					manageAdminUser.setOneAgentOpenId(company.getOneAgentOpenId());
					manageAdminUser.setRoleId(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
					ManageAdminUser user = manageadminuserService.getManageAdminUser(manageAdminUser);
					// 获取一级机构
					HAgent hAgent = new HAgent();
					hAgent.setOpenId(company.getOneAgentOpenId());
					hAgent = hagentService.getHAgent(hAgent);
					if (hAgent != null && hAgent.getStatus() == 1&& hAgent.getCheck_status() == 1 && user != null&& user.getState() == 1) {
						hPayOrder.setOneAgentName(company.getOneAgentName());
						hPayOrder.setOneAgentOpenId(company.getOneAgentOpenId());
					}
				}
				if (company.getTwoAgentOpenID() != null) {
					ManageAdminUser manageAdminUser2 = new ManageAdminUser();
					manageAdminUser2.setTwoAgentOpenId(company.getTwoAgentOpenID());
					manageAdminUser2.setRoleId(Integer.valueOf(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID));
					ManageAdminUser user2 = manageadminuserService.getManageAdminUser(manageAdminUser2);
					// 获取二级机构
					HAgentTwo hAgentTwo = new HAgentTwo();
					hAgentTwo.setOpenId(company.getTwoAgentOpenID());
					hAgentTwo = hagenttwoService.getHAgentTwo(hAgentTwo);
					if (hAgentTwo != null && hAgentTwo.getStatus() == 1&& hAgentTwo.getCheck_status() == 1&& user2 != null && user2.getState() == 1) {
						hPayOrder.setTwoAgentName(company.getTwoAgentName());
						hPayOrder.setTwoAgentOpenID(company.getTwoAgentOpenID());
					}
				}
				if (company != null && company.getServicerId() != null) {
					ManageAdminUser manageAdminUser3 = new ManageAdminUser();
					manageAdminUser3.setAdminId(company.getServicerId());
					ManageAdminUser user3 = manageadminuserService.getManageAdminUser(manageAdminUser3);
					if (user3 != null && user3.getState() == 1) {
						hPayOrder.setServicerId(company.getServicerId());
						hPayOrder.setServicerName(company.getServicerName());
					}
				}
			}
			HPayOrder findHPayOrder = hPayOrderService.findTodayHPayOrder(hPayOrderQur);
			if (findHPayOrder == null) {// 不存在订单
				hPayOrderService.insertHPayOrder(hPayOrder);// 插入订单数据
				for (HSubOrder hSubOrder : subOrderList) {
					hSubOrder.setO_id(oNo);
					hSubOrderService.insertHSubOrder(hSubOrder);// 插入子订单数据
				}
			} else {
				log.info(oNo+ "-----------findHPayOrder.getAmount()---------------->"+ findHPayOrder.getAmount());
				log.info(oNo + "--------paysum-------------->" + paysum);
				log.info(oNo + "--------paysum111-------------->"+ findHPayOrder.getAmount().equals(paysum));
				if (!findHPayOrder.getAmount().equals(paysum)) {// 金额不相等
					hPayOrderService.insertHPayOrder(hPayOrder);// 插入订单数据
					for (HSubOrder hSubOrder : subOrderList) {
						hSubOrder.setO_id(hPayOrder.getO_no());
						hSubOrderService.insertHSubOrder(hSubOrder);// 插入子订单数据
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return oNo;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toPayOk")
	public String toPayOk(HttpServletRequest request,HttpServletResponse response, Model model) {
		String o_no = RequestHandler.getString(request, "o_no");
		String query_id = RequestHandler.getString(request, "query_id");
		String pay_type = RequestHandler.getString(request, "pay_type");
		Integer fee = RequestHandler.getInteger(request, "fee");
		
		model.addAttribute("o_no", o_no);
		model.addAttribute("query_id", query_id);
		model.addAttribute("pay_type", pay_type);
		model.addAttribute("time", new Date());
		model.addAttribute("fee", super.getMoney(fee));
		return "/wx/h5/payOk";
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toPayFail")
	public String toPayFail(HttpServletRequest request,HttpServletResponse response, Model model) {
		String o_no = RequestHandler.getString(request, "o_no");
		String query_id = RequestHandler.getString(request, "query_id");
		String pay_type = RequestHandler.getString(request, "pay_type");
		String msg = RequestHandler.getString(request, "msg");
		Integer fee = RequestHandler.getInteger(request, "fee");
		
		model.addAttribute("o_no", o_no);
		model.addAttribute("msg", msg);
		model.addAttribute("pay_type", pay_type);
		model.addAttribute("query_id", query_id);
		model.addAttribute("time", new Date());
		model.addAttribute("fee", super.getMoney(fee));
		return "/wx/h5/payFail";
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/showOrder")
	public String showOrder(HttpServletRequest request,HttpServletResponse response, Model model) {
		String o_no = RequestHandler.getString(request, "o_no");
		String query_id = RequestHandler.getString(request, "query_id");
		List<HSubOrder> listSub = new ArrayList<HSubOrder>();
		HPayOrder order = new HPayOrder();
		try{
			order.setO_no(o_no);
			order = hPayOrderService.getHPayOrder(order);
			
			HSubOrder hSubOrder = new HSubOrder();
			hSubOrder.setO_id(o_no);
			List<HSubOrder> listSubOrder = hSubOrderService.getHSubOrderBaseList(hSubOrder);
			if(listSubOrder!=null&&listSubOrder.size()>0){
				Set<Integer> set = new HashSet<Integer>();
				for(HSubOrder subOrder:listSubOrder){
					set.add(subOrder.getSub_id());
				}
				Iterator<Integer> iterator = set.iterator();
				while (iterator.hasNext()) {
					String sub_name = null;
					HSubOrder sub = new HSubOrder();
					List<HSubOrder> listSub1 = new ArrayList<HSubOrder>();
					Integer totalFee = 0;
					Integer str = iterator.next(); 
					if(StringUtils.isNotBlank(query_id)){
						for(HSubOrder subOrder:listSubOrder){
							sub.setConsignee(subOrder.getConsignee());
	//								sub.setConsignee_address(subOrder.getConsignee_address());
							sub.setConsignee_phone(subOrder.getConsignee_phone());
							//查询待省市区的地址
							sub.setConsignee_address(subOrder.getConsignee_address());
							if(query_id.equals(subOrder.getElectric())){
								if(str.equals(subOrder.getSub_id())){
									sub_name = subOrder.getSub_name();
									listSub1.add(subOrder);
									
									
									String totalFeeStr2 = null;
									if(subOrder.getAmount()>0){
										if(String.valueOf(subOrder.getAmount()).length()>2){
											int fen = totalFee%100;
											if(fen>0&&fen<10){
												totalFeeStr2 = subOrder.getAmount()/100 + ".0" + fen;
											}else if(fen>=10){
												totalFeeStr2 = subOrder.getAmount()/100 + "." + fen;
											}else{
												totalFeeStr2 = subOrder.getAmount()/100 + ".00";
											}
											subOrder.setTotalFeeStr(totalFeeStr2);
										}else if(String.valueOf(subOrder.getAmount()).length()==1){
											subOrder.setTotalFeeStr("0.0"+totalFee);
										}else{
											subOrder.setTotalFeeStr("0."+totalFee);
										}
									}else{
										subOrder.setTotalFeeStr("0.00");
									}
									
									HFp hFp = new HFp();
									hFp.setOrderNumber(o_no);
									hFp.setRemark2(subOrder.getElectric());
									hFp = hfpService.getHFp(hFp);
									
									if(hFp!=null&&hFp.getId()>0){
										if(hFp.getExpress_status()==1){
											subOrder.setIsFP(1);
											subOrder.setExpress_name(hFp.getExpress_name());
											subOrder.setExpress_no(hFp.getExpress_no());
										}else{
											subOrder.setIsFP(0);
										}
									}else{
										subOrder.setIsFP(0);
									}
									
									
									totalFee = totalFee + subOrder.getAmount();
									sub.setListSubOrder(listSub1);
									sub.setTotalFee(totalFee);
									String totalFeeStr = null;
									if(totalFee>0){
										if(String.valueOf(totalFee).length()>2){
											int fen = totalFee%100;
											if(fen>0&&fen<10){
												totalFeeStr = totalFee/100 + ".0" + fen;
											}else if(fen>=10){
												totalFeeStr = totalFee/100 + "." + fen;
											}else{
												totalFeeStr = totalFee/100 + ".00";
											}
											sub.setTotalFeeStr(totalFeeStr);
										}else if(String.valueOf(totalFee).length()==1){
											sub.setTotalFeeStr("0.0"+totalFee);
										}else{
											sub.setTotalFeeStr("0."+totalFee);
										}
									}else{
										sub.setTotalFeeStr("0.00");
									}
									sub.setSub_name(sub_name);
									sub.setTick_off_status(subOrder.getTick_off_status());
									listSub.add(sub);
									break;
								}
							}
						}
					}else{
						for(HSubOrder subOrder:listSubOrder){
							if(str.equals(subOrder.getSub_id())){
								//查询待省市区的地址
								sub.setConsignee_address(subOrder.getConsignee_address());
								sub.setConsignee(subOrder.getConsignee());
								sub.setConsignee_phone(subOrder.getConsignee_phone());
								sub_name = subOrder.getSub_name();
								listSub1.add(subOrder);
								totalFee = totalFee + subOrder.getAmount();
							}
							String totalFeeStr2 = null;
							if(subOrder.getAmount()>0){
								if(String.valueOf(subOrder.getAmount()).length()>2){
									int fen = subOrder.getAmount()%100;
									if(fen>0&&fen<10){
										totalFeeStr2 = subOrder.getAmount()/100 + ".0" + fen;
									}else if(fen>=10){
										totalFeeStr2 = subOrder.getAmount()/100 + "." + fen;
									}else{
										totalFeeStr2 = subOrder.getAmount()/100 + ".00";
									}
									subOrder.setTotalFeeStr(totalFeeStr2);
								}else if(String.valueOf(subOrder.getAmount()).length()==1){
									subOrder.setTotalFeeStr("0.0"+subOrder.getAmount());
								}else{
									subOrder.setTotalFeeStr("0."+subOrder.getAmount());
								}
							}else{
								subOrder.setTotalFeeStr("0.00");
							}
							HFp hFp = new HFp();
							hFp.setOrderNumber(o_no);
							hFp.setRemark2(subOrder.getElectric());
							hFp = hfpService.getHFp(hFp);
							
							if(hFp!=null&&hFp.getId()>0){
								if(hFp.getExpress_status()==1){
									subOrder.setIsFP(1);
									subOrder.setExpress_name(hFp.getExpress_name());
									subOrder.setExpress_no(hFp.getExpress_no());
								}else{
									subOrder.setIsFP(0);
								}
							}else{
								subOrder.setIsFP(0);
							}
						}
						
						sub.setListSubOrder(listSub1);
						sub.setTotalFee(totalFee);
						String totalFeeStr = null;
						if(totalFee>0){
							if(String.valueOf(totalFee).length()>2){
								int fen = totalFee%100;
								if(fen>0&&fen<10){
									totalFeeStr = totalFee/100 + ".0" + fen;
								}else if(fen>=10){
									totalFeeStr = totalFee/100 + "." + fen;
								}else{
									totalFeeStr = totalFee/100 + ".00";
								}
								sub.setTotalFeeStr(totalFeeStr);
							}else if(String.valueOf(totalFee).length()==1){
								sub.setTotalFeeStr("0.0"+totalFee);
							}else{
								sub.setTotalFeeStr("0."+totalFee);
							}
						}else{
							sub.setTotalFeeStr("0.00");
						}
						sub.setSub_name(sub_name);
						listSub.add(sub);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		model.addAttribute("o_no", o_no);
		model.addAttribute("listSub", listSub);
		model.addAttribute("order", order);
		return "/wx/h5/orderInfo";
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/orderListByJson")
	public String orderListByJson(HttpServletRequest request,HttpServletResponse response, Model model) {
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		try
		{	
			ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			
			HPayOrder hpayorder = new HPayOrder();
			
			Integer o_id = RequestHandler.getInteger(request, "o_id");
			hpayorder.setO_id(o_id);
			
			String o_no = RequestHandler.getString(request, "o_no");
			hpayorder.setO_no(o_no);
			
			String query_id = RequestHandler.getString(request, "query_id");
			hpayorder.setQuery_id(query_id);
			
			String pay_ip = RequestHandler.getString(request, "pay_ip");
			hpayorder.setPay_ip(pay_ip);
			
			Integer c_id = RequestHandler.getInteger(request, "c_id");
			hpayorder.setC_id(c_id);
			
			Integer o_sub_id = RequestHandler.getInteger(request, "o_sub_id");
			hpayorder.setO_sub_id(o_sub_id);
			
			Integer amount = RequestHandler.getInteger(request, "amount");
			hpayorder.setAmount(amount);
			
			Integer actual_payment = RequestHandler.getInteger(request, "actual_payment");
			hpayorder.setActual_payment(actual_payment);
			
			Integer account_payment = RequestHandler.getInteger(request, "account_payment");
			hpayorder.setAccount_payment(account_payment);
			
			String pay_type = RequestHandler.getString(request, "pay_type");
			hpayorder.setPay_type(pay_type);
			
			String yw_name = RequestHandler.getString(request, "yw_name");
			hpayorder.setYw_name(yw_name);
			
			String pay_account = RequestHandler.getString(request, "pay_account");
			hpayorder.setPay_account(pay_account);
			
			Integer operator_id = RequestHandler.getInteger(request, "operator_id");
			hpayorder.setOperator_id(operator_id);
			
			Integer is_zz = RequestHandler.getInteger(request, "is_zz");
			hpayorder.setIs_zz(is_zz);
			
			Integer is_fp = RequestHandler.getInteger(request, "is_fp");
			hpayorder.setIs_fp(is_fp);
			
			String payee = RequestHandler.getString(request, "payee");
			hpayorder.setPayee(payee);
			
			Date create_time = RequestHandler.getDate(request, "create_time");
			hpayorder.setCreate_time(create_time);
			
			Date pay_time = RequestHandler.getDate(request, "pay_time");
			hpayorder.setPay_time(pay_time);
			
			String pay_status = RequestHandler.getString(request, "pay_status");
			hpayorder.setPay_status(pay_status);
			
			String o_sub_name = RequestHandler.getString(request, "o_sub_name");
			hpayorder.setO_sub_name(o_sub_name);
			
			String electric_number = RequestHandler.getString(request, "electric_number");
			hpayorder.setElectric_number(electric_number);
			
			String tick_off_status = RequestHandler.getString(request, "tick_off_status");
			hpayorder.setTick_off_status(tick_off_status);
			
			Date tick_off_time = RequestHandler.getDate(request, "tick_off_time");
			hpayorder.setTick_off_time(tick_off_time);
			
			String back_fee_status = RequestHandler.getString(request, "back_fee_status");
			hpayorder.setBack_fee_status(back_fee_status);
			
			Date back_time = RequestHandler.getDate(request, "back_time");
			hpayorder.setBack_time(back_time);
			
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String pay_start_time_str = RequestHandler.getString(request, "pay_start_time");
			if(StringUtils.isNotBlank(pay_start_time_str)){
				pay_start_time_str = pay_start_time_str + " 00:00:00";
				hpayorder.setPay_start_time(sf.parse(pay_start_time_str));
			}
			String pay_end_time_str = RequestHandler.getString(request, "pay_end_time");
			if(StringUtils.isNotBlank(pay_end_time_str)){
				pay_end_time_str = pay_end_time_str + " 23:59:59";
				hpayorder.setPay_end_time(sf.parse(pay_end_time_str));
			}
			String back_start_time_str = RequestHandler.getString(request, "back_start_time");
			if(StringUtils.isNotBlank(back_start_time_str)){
				back_start_time_str = back_start_time_str + " 00:00:00";
				hpayorder.setBack_start_time(sf.parse(back_start_time_str));
			}
			String back_end_time_str = RequestHandler.getString(request, "back_end_time");
			if(StringUtils.isNotBlank(back_end_time_str)){
				back_end_time_str = back_end_time_str + " 23:59:59";
				hpayorder.setBack_end_time(sf.parse(back_end_time_str));
			}
			String tick_start_time_str = RequestHandler.getString(request, "tick_start_time");
			if(StringUtils.isNotBlank(tick_start_time_str)){
				tick_start_time_str = tick_start_time_str + " 00:00:00";
				hpayorder.setTick_start_time(sf.parse(tick_start_time_str));
			}
			String tick_end_time_str = RequestHandler.getString(request, "tick_end_time");
			if(StringUtils.isNotBlank(tick_end_time_str)){
				tick_end_time_str = tick_end_time_str + " 23:59:59";
				hpayorder.setTick_end_time(sf.parse(tick_end_time_str));
			}
	
			hpayorder.setC_id(adminUser.getCompanyId());
			
			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hpayorder.setRowStart(from);
			hpayorder.setRowCount(rowCount);
			// 分页结束
			// 排序
			hpayorder.setSortColumn(" a.create_time desc ");
			
			Integer totalResults = hPayOrderService.getHPayOrderListCount(hpayorder);
			Long countsum = hPayOrderService.getHPayOrderListSum(hpayorder);
			
			List<HPayOrder> list = hPayOrderService.getHPayOrderListPage(hpayorder);
			
			if(list!=null&&list.size()>0){
				for(HPayOrder c:list){
					JSONObject jsons = new JSONObject();
					if(c.getAmount()!=null){
						jsons.put("totalFee", super.getMoney(c.getAmount()));
					}else{
						jsons.put("totalFee", "0.00");
					}
					if(c.getPay_time()!=null){
						jsons.put("time", sf.format(c.getPay_time()));
					}else{
						jsons.put("time", "");
					}
					if(StringUtils.isNotBlank(c.getPay_status())){
						if("1".equals(c.getPay_status())){
							jsons.put("state", "已支付");
						}else{
							jsons.put("state", "未支付");
						}
					}else{
						jsons.put("state", "未支付");
					}
					jsons.put("o_no", c.getO_no());
					if(StringUtils.isNotBlank(c.getQuery_id())){
						jsons.put("query_id", c.getQuery_id());
					}else{
						jsons.put("query_id", "");
					}
					jsons.put("pay_type", c.getPay_type());
					array.add(jsons);
				}
				json.put("message", "ok");
				if(totalResults==null){
					json.put("totalResults", 0);
				}else{
					json.put("totalResults", totalResults);
				}
				json.put("countsum", super.getMoneyL(countsum));
				json.put("items", array);
			}else{
				if(totalResults==null){
					json.put("totalResults", 0);
				}else{
					json.put("totalResults", totalResults);
				}
				json.put("countsum", super.getMoneyL(countsum));
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
	 * @return
	 */
	@RequestMapping(value = "/toOrderList")
	public String toOrderList(HttpServletRequest request,HttpServletResponse response, Model model) {
		
		try
		{	
			ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			
			HPayOrder hpayorder = new HPayOrder();
			
			Integer o_id = RequestHandler.getInteger(request, "o_id");
			hpayorder.setO_id(o_id);
			
			String o_no = RequestHandler.getString(request, "o_no");
			hpayorder.setO_no(o_no);
			
			String query_id = RequestHandler.getString(request, "query_id");
			hpayorder.setQuery_id(query_id);
			
			String pay_ip = RequestHandler.getString(request, "pay_ip");
			hpayorder.setPay_ip(pay_ip);
			
			Integer c_id = RequestHandler.getInteger(request, "c_id");
			hpayorder.setC_id(c_id);
			
			Integer o_sub_id = RequestHandler.getInteger(request, "o_sub_id");
			hpayorder.setO_sub_id(o_sub_id);
			
			Integer amount = RequestHandler.getInteger(request, "amount");
			hpayorder.setAmount(amount);
			
			Integer actual_payment = RequestHandler.getInteger(request, "actual_payment");
			hpayorder.setActual_payment(actual_payment);
			
			Integer account_payment = RequestHandler.getInteger(request, "account_payment");
			hpayorder.setAccount_payment(account_payment);
			
			String pay_type = RequestHandler.getString(request, "pay_type");
			hpayorder.setPay_type(pay_type);
			
			String yw_name = RequestHandler.getString(request, "yw_name");
			hpayorder.setYw_name(yw_name);
			
			String pay_account = RequestHandler.getString(request, "pay_account");
			hpayorder.setPay_account(pay_account);
			
			Integer operator_id = RequestHandler.getInteger(request, "operator_id");
			hpayorder.setOperator_id(operator_id);
			
			Integer is_zz = RequestHandler.getInteger(request, "is_zz");
			hpayorder.setIs_zz(is_zz);
			
			Integer is_fp = RequestHandler.getInteger(request, "is_fp");
			hpayorder.setIs_fp(is_fp);
			
			String payee = RequestHandler.getString(request, "payee");
			hpayorder.setPayee(payee);
			
			Date create_time = RequestHandler.getDate(request, "create_time");
			hpayorder.setCreate_time(create_time);
			
			Date pay_time = RequestHandler.getDate(request, "pay_time");
			hpayorder.setPay_time(pay_time);
			
			String pay_status = RequestHandler.getString(request, "pay_status");
			hpayorder.setPay_status(pay_status);
			
			String o_sub_name = RequestHandler.getString(request, "o_sub_name");
			hpayorder.setO_sub_name(o_sub_name);
			
			String electric_number = RequestHandler.getString(request, "electric_number");
			hpayorder.setElectric_number(electric_number);
			
			String tick_off_status = RequestHandler.getString(request, "tick_off_status");
			hpayorder.setTick_off_status(tick_off_status);
			
			Date tick_off_time = RequestHandler.getDate(request, "tick_off_time");
			hpayorder.setTick_off_time(tick_off_time);
			
			String back_fee_status = RequestHandler.getString(request, "back_fee_status");
			hpayorder.setBack_fee_status(back_fee_status);
			
			Date back_time = RequestHandler.getDate(request, "back_time");
			hpayorder.setBack_time(back_time);
			
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String pay_start_time_str = RequestHandler.getString(request, "pay_start_time");
			if(StringUtils.isNotBlank(pay_start_time_str)){
				pay_start_time_str = pay_start_time_str + " 00:00:00";
				hpayorder.setPay_start_time(sf.parse(pay_start_time_str));
			}
			String pay_end_time_str = RequestHandler.getString(request, "pay_end_time");
			if(StringUtils.isNotBlank(pay_end_time_str)){
				pay_end_time_str = pay_end_time_str + " 23:59:59";
				hpayorder.setPay_end_time(sf.parse(pay_end_time_str));
			}
			String back_start_time_str = RequestHandler.getString(request, "back_start_time");
			if(StringUtils.isNotBlank(back_start_time_str)){
				back_start_time_str = back_start_time_str + " 00:00:00";
				hpayorder.setBack_start_time(sf.parse(back_start_time_str));
			}
			String back_end_time_str = RequestHandler.getString(request, "back_end_time");
			if(StringUtils.isNotBlank(back_end_time_str)){
				back_end_time_str = back_end_time_str + " 23:59:59";
				hpayorder.setBack_end_time(sf.parse(back_end_time_str));
			}
			String tick_start_time_str = RequestHandler.getString(request, "tick_start_time");
			if(StringUtils.isNotBlank(tick_start_time_str)){
				tick_start_time_str = tick_start_time_str + " 00:00:00";
				hpayorder.setTick_start_time(sf.parse(tick_start_time_str));
			}
			String tick_end_time_str = RequestHandler.getString(request, "tick_end_time");
			if(StringUtils.isNotBlank(tick_end_time_str)){
				tick_end_time_str = tick_end_time_str + " 23:59:59";
				hpayorder.setTick_end_time(sf.parse(tick_end_time_str));
			}
	
			hpayorder.setC_id(adminUser.getCompanyId());
			
			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hpayorder.setRowStart(from);
			hpayorder.setRowCount(rowCount);
			// 分页结束
			// 排序
			hpayorder.setSortColumn(" a.create_time desc ");
			
			
			//获取子单位
			
			HSubCompany hSubCompany = new HSubCompany();
			hSubCompany.setC_id(adminUser.getCompanyId());
			List<HSubCompany> listSubC = hSubCompanyService.getHSubCompanyBaseList(hSubCompany);
			
			int hpayorderListCount = hPayOrderService.getHPayOrderListCount(hpayorder);
			Long countsum = hPayOrderService.getHPayOrderListSum(hpayorder);
//			ResponseList<HPayOrder> hpayorderList = null;
//			if ( hpayorderListCount > 0 )
//			{
//				hpayorderList = hPayOrderService.getHPayOrderList(hpayorder);
//				Iterator<Object> it = hpayorderList.iterator();
//				List<HPayOrder> list = new ArrayList<HPayOrder>();
//				while (it.hasNext()) {
//					HPayOrder order = (HPayOrder)it.next();
//					order.setAmountStr(super.getMoney(order.getAmount()));
//					list.add(order);
//				}
//				model.addAttribute("list", list);
//			} else
//			{
//				hpayorderList = new ResponseList<HPayOrder>();
//			}
			// 设置数据总数
			model.addAttribute("hpayorderListCount", hpayorderListCount);
			model.addAttribute("pageNo", pageNo);
			model.addAttribute("countsum", super.getMoneyL(countsum));
			model.addAttribute("o_no", o_no);
			model.addAttribute("is_fp", is_fp);
			model.addAttribute("o_sub_name", o_sub_name);
			if(StringUtils.isNotBlank(pay_end_time_str)){
				model.addAttribute("pay_end_time", pay_end_time_str.substring(0, 10));
			}
			
			if(StringUtils.isNotBlank(pay_start_time_str)){
				model.addAttribute("pay_start_time", pay_start_time_str.substring(0, 10));
			}
			model.addAttribute("pay_status", pay_status);
			model.addAttribute("listSubC", listSubC);
			model.addAttribute("o_sub_id", o_sub_id);
			model.addAttribute("electric_number", electric_number);
			model.addAttribute("tick_off_status", tick_off_status);
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return "/wx/h5/orderList";
	}
	
	/**
	 * 保存子单位信息
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/saveHSubCompany", method = RequestMethod.POST)
	public String saveHSubCompany(HttpServletRequest request, HttpServletResponse response, Model model) {
		Gson gson1 = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create(); 
		String province_code = RequestHandler.getString(request, "province_code");
		String city_code = RequestHandler.getString(request, "city_code");
		String area_code = RequestHandler.getString(request, "area_code");
		String sub_name = RequestHandler.getString(request, "sub_name");
		String consignee = RequestHandler.getString(request, "consignee");
		String consignee_phone = RequestHandler.getString(request, "consignee_phone");
		String consignee_address = RequestHandler.getString(request, "consignee_address");
		String zip_code = RequestHandler.getString(request, "zip_code");
		Integer s_id = RequestHandler.getInteger(request, "s_id");
//		Map dataMap = request.getParameterMap();
		HSubCompany hSubCompany = new HSubCompany();
		ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
//		MapUtil.transMap2Bean2(dataMap,hSubCompany);
		hSubCompany.setC_id(adminUser.getCompanyId());
		Map<String, Object> resultMap = new HashMap<String, Object>();//返回数据
		try {
			hSubCompany.setProvince_code(province_code);
			hSubCompany.setCity_code(city_code);
			hSubCompany.setArea_code(area_code);
			hSubCompany.setSub_name(sub_name);
			hSubCompany.setConsignee(consignee);
			hSubCompany.setConsignee_address(consignee_address);
			hSubCompany.setConsignee_phone(consignee_phone);
			hSubCompany.setZip_code(zip_code);
			hSubCompany.setS_id(s_id);
			int id = 0;
			if(hSubCompany.getS_id()!=null && hSubCompany.getS_id()>0){//存在更新
				hSubCompany.setUpdate_time(new Date());
				hSubCompanyService.updateHSubCompany(hSubCompany);
			}else{
				id= hSubCompanyService.insertHSubCompany(hSubCompany);
			}
//			if(id>0){
//				hSubCompany.setS_id(id);
//			}
			resultMap.put("status", "success");
			resultMap.put("msg", "保存子单位信息成功!");
			resultMap.put("data", gson1.toJson(hSubCompany));
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", "fail");
			resultMap.put("msg", "保存子单位信息失败!");
		}
		ResponseUtil.responseText(response, gson1.toJson(resultMap));
		return null;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toUpdateGroup")
	public String toUpdateGroup(HttpServletRequest request,HttpServletResponse response, Model model) {
		try{
			Integer subId = RequestHandler.getInteger(request, "subId");
			HSubCompany hSubCompany = new HSubCompany();
			hSubCompany.setS_id(subId);
			hSubCompany = hSubCompanyService.getHSubCompany(hSubCompany);
			model.addAttribute("hSubCompany", hSubCompany);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/wx/h5/updateGroup";
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/electricShow")
	public String electricShow(HttpServletRequest request,HttpServletResponse response, Model model) {
		try{
			Integer subId = RequestHandler.getInteger(request, "subId");
			String ammeter_no = RequestHandler.getString(request, "ammeter_no");
			HAmmeterInfo hAmmeterInfoQry = new HAmmeterInfo();
			hAmmeterInfoQry.setS_id(subId);
			hAmmeterInfoQry.setDelete_state(1);
			hAmmeterInfoQry.setProxy_flag(1);
			hAmmeterInfoQry.setAmmeter_no(ammeter_no);
			hAmmeterInfoQry = hAmmeterInfoService.getHAmmeterInfo(hAmmeterInfoQry);
			JSONObject result = hCommonService.hXTServiceQuery(ammeter_no, this.getIpAddr(request));
			if ("00".equals(result.get("resultCode"))) {// 获取电表信息成功
				Integer fee = Integer.valueOf(result.get("totalFeeStr") == null ? "0": result.get("totalFeeStr").toString());
				hAmmeterInfoQry.setNow_totalFee(super.getMoney(fee));
				hAmmeterInfoQry.setAmmeter_name(result.getJSONObject("resultInfo").getString("accountName"));
				hAmmeterInfoQry.setElectric_address(result.getJSONObject("resultInfo").getString("address"));
				model.addAttribute("accountFee", result.getJSONObject("resultInfo").getString("accountFee"));
				model.addAttribute("lateFee", result.getJSONObject("resultInfo").getString("lateFee"));
			}
//			HSubCompany hSubCompany = new HSubCompany();
//			hSubCompany.setS_id(subId);
//			hSubCompany = hSubCompanyService.getHSubCompany(hSubCompany);
			model.addAttribute("subId", subId);
			model.addAttribute("hAmmeterInfoQry", hAmmeterInfoQry);
			model.addAttribute("ammeter_no", ammeter_no);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/wx/h5/electricShow";
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/cancelProxy")
	public String cancelProxy(HttpServletRequest request,HttpServletResponse response, Model model) {
		try{
			Integer subId = RequestHandler.getInteger(request, "subId");
			String ammeter_no = RequestHandler.getString(request, "ammeter_no");
			HAmmeterInfo hAmmeterInfoQry = new HAmmeterInfo();
			hAmmeterInfoQry.setS_id(subId);
			hAmmeterInfoQry.setDelete_state(1);
			hAmmeterInfoQry.setProxy_flag(1);
			hAmmeterInfoQry.setAmmeter_no(ammeter_no);
			hAmmeterInfoQry = hAmmeterInfoService.getHAmmeterInfo(hAmmeterInfoQry);
			if(hAmmeterInfoQry!=null){
				hAmmeterInfoQry.setProxy_flag(0);
				hAmmeterInfoService.updateHAmmeterInfo(hAmmeterInfoQry);
				writeSuccessMsg("取消成功", null, response);
			}else{
				writeErrorMsg("取消失败", null, response);
			}
		}catch(Exception e){
			writeErrorMsg("系统异常", null, response);
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/uploadDan")
	public String uploadDan(HttpServletRequest request,HttpServletResponse response, Model model) {
		try{
			Integer a_id = RequestHandler.getInteger(request, "a_id");
			String newFileName = RequestHandler.getString(request, "newFileName");
			HAmmeterInfo hAmmeterInfoQry = new HAmmeterInfo();
			hAmmeterInfoQry.setA_id(a_id);
			hAmmeterInfoQry = hAmmeterInfoService.getHAmmeterInfo(hAmmeterInfoQry);
			if(hAmmeterInfoQry!=null){
				hAmmeterInfoQry.setBill_img(newFileName);
				hAmmeterInfoService.updateHAmmeterInfo(hAmmeterInfoQry);
				writeSuccessMsg("成功", null, response);
			}else{
				writeErrorMsg("失败", null, response);
			}
		}catch(Exception e){
			writeErrorMsg("系统异常", null, response);
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/showdan")
	public String showdan(HttpServletRequest request,HttpServletResponse response, Model model) {
		try{
			Integer a_id = RequestHandler.getInteger(request, "a_id");
			HAmmeterInfo hAmmeterInfoQry = new HAmmeterInfo();
			hAmmeterInfoQry.setA_id(a_id);
			hAmmeterInfoQry = hAmmeterInfoService.getHAmmeterInfo(hAmmeterInfoQry);
			model.addAttribute("hAmmeterInfoQry", hAmmeterInfoQry);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/wx/h5/showdan";
	}
	
	@RequestMapping(value = "/uploadPic")
	public String uploadPic(HttpServletRequest req, HttpServletResponse response, Model model){
		JSONObject json = new JSONObject();

		try {


			if(req instanceof MultipartHttpServletRequest){

				MultipartHttpServletRequest request = (MultipartHttpServletRequest)req;
//				MultipartFile imgFile = request.getFile("file");
				MultipartFile imgFile = request.getFile("feedbackImg");

				String uuid = UUID.randomUUID().toString().replace("-", "");	// 惟一ID

				Long times = System.currentTimeMillis();

				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

				String datedir = sdf.format(new Date(times));

				//等于配置的路径  加 当前日期格式化
				String realPath = "/" + "upload" + "/" + datedir;
				String contextPath = request.getSession().getServletContext().getRealPath("/");

				File file = new File(contextPath+realPath);
				if(!file.exists()){
					file.mkdirs();
				}

				String filetype = null;

				String contentType = imgFile.getContentType();

				if("image/png".equals(contentType)){
					filetype = ".png";
				}else if("image/jpeg".equals(contentType)){
					filetype = ".jpg";
				}else if("image/jpg".equals(contentType)){
					filetype = ".jpg";
				}else if("image/gif".equals(contentType)){
					filetype = ".gif";
				}

				//生成新的文件名称
				String newfilename = uuid + filetype;
				this.uploadFile(contextPath+realPath, imgFile, newfilename);
				json.put("serverfileName", realPath +"/" + newfilename);

			}
			response.getWriter().write(json.toString());
		} catch (Exception e) {
			System.out.println("上传图片出现异常：");
			e.printStackTrace();
		}
		 return null;
	}
	
	/**
	 * 上传文件
	 * @param destinationDir
	 * @param file
	 * @param filename
	 * @return
	 * @throws Exception
	 */
	public boolean uploadFile(String path, MultipartFile file, String filename) throws Exception {
         try {   
        	FileOutputStream outputStream = new FileOutputStream( path + "/"+ filename);
        	InputStream in = file.getInputStream();
    	    int byteCount = 0;
    	    byte[] bytes = new byte[1024];
    	    while ((byteCount = in.read(bytes)) != -1){
    	         outputStream.write(bytes, 0, byteCount);
    	    }
    	    outputStream.close();   
    	    in.close();
         } catch (IOException e) {   
             return false;   
         }   
        return true;
    }
	
	/**
	 * 保存电表信息
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/saveHAmmeterInfo", method = RequestMethod.POST)
	public String saveHAmmeterInfo(HttpServletRequest request, HttpServletResponse response, Model model) {
		ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
		Integer proxy = RequestHandler.getInteger(request, "proxy");
		Map dataMap = request.getParameterMap();
		HAmmeterInfo hAmmeterInfo = new HAmmeterInfo();
		MapUtil.transMap2Bean2(dataMap,hAmmeterInfo);
//		HAmmeterInfo hAmmeterInfo = gson.fromJson(gson.toJson(dataMap), HAmmeterInfo.class);
		Map<String, Object> resultMap = new HashMap<String, Object>();//返回数据
		try {
			//查询电表信息是否已经存在
			HAmmeterInfo hAmmeterInfo1 = new HAmmeterInfo();
			hAmmeterInfo1.setAmmeter_no(hAmmeterInfo.getAmmeter_no());
			hAmmeterInfo1.setDelete_state(1);
			int count1 = hAmmeterInfoService.getHAmmeterInfoListCount(hAmmeterInfo1);
//			hAmmeterInfo1.setC_id(hAmmeterInfo.getC_id());
//			int count = hAmmeterInfoService.getHAmmeterInfoListCount(hAmmeterInfo1);//不能存在未删除相同电表号
//			if(count>0){
//				resultMap.put("status", "fail");
//				resultMap.put("msg", "该缴费号您已绑定，无法再次绑定。");
//			}else 
			if(count1>0){
				if(count1==1){
					HAmmeterInfo hAmmeterInfo11 = hAmmeterInfoService.getHAmmeterInfo(hAmmeterInfo1);
					if(hAmmeterInfo11!=null&&hAmmeterInfo11.getC_id().equals(hAmmeterInfo.getC_id())){
						if(hAmmeterInfo11.getProxy_flag()==1){
							resultMap.put("status", "fail");
							resultMap.put("msg", "该缴费号已设为手机缴费，无需重复设置");
						}else{
							hAmmeterInfo11.setProxy_flag(1);
							hAmmeterInfoService.updateHAmmeterInfo(hAmmeterInfo11);
							resultMap.put("status", "success");
							resultMap.put("msg", "保存电表信息成功!");
							hAmmeterInfo.setProxy_flag(1);
							resultMap.put("data", AmmeterManagerController.gson.toJson(hAmmeterInfo));
						}
					}else{
						resultMap.put("status", "fail");
						resultMap.put("msg", "该缴费号已被其他账号绑定，无法再次绑定。");
					}
				}else{
					resultMap.put("status", "fail");
					resultMap.put("msg", "该缴费号已被其他账号绑定，无法再次绑定。");
				}
			}else{
				//获取默认的分润比例
				HProfitRatio hProfitRatio = new HProfitRatio();
				hProfitRatio.setIs_default(1);
				hProfitRatio = hprofitratioService.getHProfitRatio(hProfitRatio);
				if(hProfitRatio!=null){
					hAmmeterInfo.setOperator_id(adminUser.getAdminId());
					hAmmeterInfo.setProfit_id(hProfitRatio.getId());
					hAmmeterInfo = hareaService.setAreaCodeToAmmeterInfo(hAmmeterInfo);
					if(proxy!=null){
						hAmmeterInfo.setProxy_flag(proxy);
					}
					int id= hAmmeterInfoService.insertHAmmeterInfo(hAmmeterInfo);
					if(id>0){
						//获取对应的openId
						HCompany hCompany = new HCompany();
						hCompany.setId(hAmmeterInfo.getC_id());
						hCompany = hcompanyService.getHCompany(hCompany);
						if(hCompany!=null){
							ManageAdminUser manageAdminUser = new ManageAdminUser();
							manageAdminUser.setOneAgentOpenId(hCompany.getOneAgentOpenId());
							manageAdminUser.setRoleId(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
							ManageAdminUser user = manageadminuserService.getManageAdminUser(manageAdminUser);
							String toOPENID = null;
							if(user!=null){
								toOPENID = user.getOpenId();
							}
							huseraccountService.sendAOTempltMsg(toOPENID, "bind", hCompany.getContact_name(), hCompany.getContact_phone(), hAmmeterInfo.getAmmeter_no(), hAmmeterInfo.getC_id(), hCompany.getName());
						}
					}
					hAmmeterInfo.setA_id(id);
					resultMap.put("status", "success");
					resultMap.put("msg", "保存电表信息成功!");
					resultMap.put("data", AmmeterManagerController.gson.toJson(hAmmeterInfo));
				}else{
					resultMap.put("status", "fail");
					resultMap.put("msg", "请联系系统管理员，设置默认的分润比例。");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", "fail");
			resultMap.put("msg", "保存电表信息失败!");
		}
		ResponseUtil.responseText(response, AmmeterManagerController.gson.toJson(resultMap));
		return null;
	}
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/checkLoginState", method = RequestMethod.POST)
	public String checkLoginState(HttpServletRequest request, HttpServletResponse response, Model model) {
		ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
		try{
			ManageAdminUser adminUser1 = new ManageAdminUser();
			adminUser1.setAdminId(adminUser.getAdminId());
			adminUser1 = manageadminuserService.getManageAdminUser1(adminUser1);
			if(adminUser1!=null&&adminUser1.getState()==1){
				HCompany hCompany = new HCompany();
				hCompany.setId(adminUser.getCompanyId());
				hCompany = hcompanyService.getHCompany(hCompany);
				if(hCompany!=null&&hCompany.getStatus()==1&&hCompany.getVerify_status()==1){
					//验证公司登陆账户
					ManageAdminUser adminUser2 = new ManageAdminUser();
					adminUser2.setAdminId(hCompany.getUser_id());
					adminUser2 = manageadminuserService.getManageAdminUser1(adminUser2);
					if(adminUser2!=null&&adminUser2.getState()==1){
						HProxyMessage hProxyMessage = new HProxyMessage();
						hProxyMessage.setUserId(adminUser.getAdminId());
						hProxyMessage = hproxymessageService.getHProxyMessage(hProxyMessage);
						System.out.println("----------------------------------hProxyMessage.getCheckState()----------"+hProxyMessage.getCheckState());
						if(hProxyMessage!=null&&hProxyMessage.getState() == 1&&(hProxyMessage.getCheckState()== 1||hProxyMessage.getCheckState() == 7||hProxyMessage.getCheckState() == 8||hProxyMessage.getCheckState() == 6)){
							writeSuccessMsg("成功", null, response);
						}else{
							System.out.println("----------------------------------hProxyMessage.getCheckState()----------"+hProxyMessage.getCheckState());
							if(hProxyMessage.getCheckState() == 3||hProxyMessage.getCheckState() == 4||hProxyMessage.getCheckState() == 5){
								writeErrorMsg("您单位已经中止本服务，详情请咨询010-96199", null, response);
							}else{
								writeErrorMsg("您的手机缴费账户已被禁用，详情请咨询010-96199", null, response);
							}
						}
					}else{
						writeErrorMsg("您的注册账户已被禁用，详情请咨询010-96199", null, response);
					}
				}else{
					writeErrorMsg("您的单位信息已被禁用，详情请咨询010-96199", null, response);
				}
			}else{
				writeErrorMsg("您的注册账户已被禁用，详情请咨询010-96199", null, response);
			}
		}catch(Exception e){
			writeErrorMsg("系统异常", null, response);
			e.printStackTrace();
		}
		return null;
	}
	
}
