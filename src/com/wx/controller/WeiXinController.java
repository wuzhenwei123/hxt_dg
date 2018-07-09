package com.wx.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.base.controller.BaseController;
import com.base.utils.FileUploadConstants;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseUtil;
import com.base.utils.SendMsgUtil;
import com.base.utils.SessionName;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hxt.hAgent.model.HAgent;
import com.hxt.hAgent.service.HAgentService;
import com.hxt.hAgentTwo.model.HAgentTwo;
import com.hxt.hAgentTwo.service.HAgentTwoService;
import com.hxt.hAmmeterInfo.model.HAmmeterInfo;
import com.hxt.hAmmeterInfo.service.HAmmeterInfoService;
import com.hxt.hArea.service.HAreaService;
import com.hxt.hBank.model.HBank;
import com.hxt.hBank.service.HBankService;
import com.hxt.hCity.service.HCityService;
import com.hxt.hCommon.service.HCommonService;
import com.hxt.hCompany.model.HCompany;
import com.hxt.hCompany.service.HCompanyService;
import com.hxt.hEwm.model.HEwm;
import com.hxt.hEwm.service.HEwmService;
import com.hxt.hPayOrder.model.HPayOrder;
import com.hxt.hPayOrder.service.HPayOrderService;
import com.hxt.hPresentApply.model.HPresentApply;
import com.hxt.hPresentApply.service.HPresentApplyService;
import com.hxt.hProvince.service.HProvinceService;
import com.hxt.hReferee.model.HReferee;
import com.hxt.hReferee.service.HRefereeService;
import com.hxt.hRegGuliSend.model.HRegGuliSend;
import com.hxt.hRegGuliSend.service.HRegGuliSendService;
import com.hxt.hSubCompany.model.HSubCompany;
import com.hxt.hSubCompany.service.HSubCompanyService;
import com.hxt.hSubOrder.model.HSubOrder;
import com.hxt.hSubOrder.service.HSubOrderService;
import com.hxt.hUserAccount.model.HUserAccount;
import com.hxt.hUserAccount.service.HUserAccountService;
import com.hxt.hUserAccountDetail.model.HUserAccountDetail;
import com.hxt.hUserAccountDetail.service.HUserAccountDetailService;
import com.hxt.hVerificate.model.HVerificate;
import com.hxt.hVerificate.service.HVerificateService;
import com.hxt.wap.service.CallProcedureService;
import com.sys.manageAdminUser.model.ManageAdminUser;
import com.sys.manageAdminUser.service.ManageAdminUserService;
import com.wx.service.WeiXinService;
import com.wx.utils.Constants;
import com.wx.utils.WxMenuUtils;
import com.wx.x0001.WeiXin;
import com.wx.x0001.vo.recv.WxRecvEventMsg;
import com.wx.x0001.vo.recv.WxRecvMsg;
import com.wx.x0001.vo.recv.WxRecvTextMsg;
import com.wx.x0001.vo.send.WxSendMsg;
import com.wx.x0001.vo.send.WxSendNewsMsg;
import com.wx.x0001.vo.send.WxSendNewsMsgItem;
import com.wx.x0001.vo.send.WxSendTextMsg;

@Controller
@RequestMapping("/weixin")
public class WeiXinController extends BaseController{
	
	Logger log = Logger.getLogger(WeiXinController.class);
	
	@Autowired
	private WeiXinService weiXinService;
	@Autowired
	private HProvinceService hprovinceService = null;
	@Autowired
	private HEwmService hewmService = null;
	@Autowired
	private HCityService hcityService = null;
	@Autowired
	private HRefereeService hrefereeService = null;
	@Autowired
	private HVerificateService hverificateService = null;
	@Autowired
	private HAgentService hagentService = null;
	@Autowired
	private HAgentTwoService hagenttwoService = null;
	@Autowired
	private ManageAdminUserService manageadminuserService = null;// 用户
	@Autowired
	private HUserAccountService huseraccountService = null;
	@Autowired
	private HUserAccountDetailService huseraccountdetailService = null;
	@Autowired
	private HAreaService hareaService = null;
	@Autowired
	private HCompanyService hCompanyService = null;
	@Autowired
	private HCommonService hCommonService = null;
	@Autowired
	private HAmmeterInfoService hAmmeterInfoService = null;
	@Autowired
	private HCompanyService hcompanyService = null;
	@Autowired
	private HPresentApplyService hpresentApplyservice = null;
	@Autowired
	private HUserAccountDetailService huserAccountDetailService = null;
	@Autowired
	private HBankService hbankService = null;
	@Autowired
	private HSubOrderService hsuborderService = null;
	@Autowired
	private HPayOrderService hpayorderService = null;
	@Autowired
	private HSubCompanyService hsubCompanyService = null;
	@Autowired
	private HEwmService hEwmService = null;// 用户
	@Autowired
	private CallProcedureService callProcedureService = null;
	@Autowired
	private HPresentApplyService hpresentapplyService = null;
	@Autowired
	private HRegGuliSendService hreggulisendService = null;
	
	public static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();    // gson解析对象。
	
	@RequestMapping("/access")
	public String weixin(HttpServletResponse response,
			HttpServletRequest request) {
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");

		String code = request.getParameter("code");
		String state = request.getParameter("state");
		String appid = request.getParameter("appid");
		// 验证接口配置消息的时候会调用
		if (null != timestamp && null != nonce && null != echostr
				&& null != signature) {
			if (WeiXin.access(Constants.TOKEN, signature, timestamp, nonce)) {
				try {
					PrintWriter writer = response.getWriter();
					writer.print(echostr);
					writer.flush();
					writer.close();
					return null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("--------------->"+appid);
		if (StringUtils.isNotBlank(code)) {
			try {
				String access_token = WxMenuUtils.getAccessCode(FileUploadConstants.APPID, FileUploadConstants.APPSECRET, code);
				ObjectMapper mapper = new ObjectMapper();
				JsonNode rootNode = mapper.readTree(access_token);
				String openId = rootNode.path("openid").asText();
				System.out.println("------------openId--------------"+openId);
				request.setAttribute("openId", openId);
				request.getSession().setAttribute("session_openId", openId);
				if("twoAgentBind".equals(state)){//二级代理绑定页面
					String create_openId = request.getParameter("create_openId");
					String maketwo = request.getParameter("maketwo");
					JsonNode jj = WxMenuUtils.getFromUserMessBySQ(rootNode.path("access_token").asText(), openId);
					String nickName = jj.path("nickname").asText();
					return  "redirect:/hAgent/toIndex?openId="+openId+"&create_openId="+create_openId+"&maketwo="+maketwo+"&nickName="+URLEncoder.encode(nickName,"UTF-8");
				}
//				if (weiXinService.isBind(openId,request,FileUploadConstants.COMPANY_ROLE_ID)) {// 如果绑定了
					if("login".equals(state)){//登录
						return  "redirect:/index/first?openId="+ openId ;
					}else if("register".equals(state)){//注册
						System.out.println("---------------register----------");
						return  "redirect:/weixin/toReg?openId="+ openId;
					}else if("pay".equals(state)){//手机支付
//						return  "redirect:/weixin/payCheck?openId="+ openId;
						return  "/wx/tixing";
					}
//				}else {// 如果没有绑定
//					return  "redirect:/weixin/reg?openId="+ openId;
					//
//					weiXinService.bindWeiXin(FileUploadConstants.APPID, FileUploadConstants.APPSECRET, openId,request);
//				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (null != timestamp && null != nonce && null != signature) {
			if (WeiXin.access(Constants.TOKEN, signature, timestamp, nonce)) {// 验证消息的真实性
				try {
					WxRecvMsg msg = WeiXin.recv(request.getInputStream());
					WxSendMsg sendMsg = WeiXin.builderSendByRecv(msg);
					String openId = msg.getFromUser();
					if (msg instanceof WxRecvEventMsg) {
						WxRecvEventMsg m = (WxRecvEventMsg) msg;
						String event = m.getEvent();
						boolean xx = true;
						if ("subscribe".equals(event)) {
//							boolean agentStatus = true;
//							if(agentStatus){
//								//绑定
//								int bindflag = weiXinService.bindWeiXin(FileUploadConstants.APPID, FileUploadConstants.APPSECRET, openId,request);
//								if(bindflag>0){
									if(StringUtils.isNotBlank(m.getTicket())){
										String ticket = m.getTicket();
										//将推荐人和被推荐人绑定。
										hrefereeService.bindTuijian1(ticket,openId);
									}
//								}
//							}else{
								String content = "您好，欢迎您关注“恒信通企业服务”公众号，请您点击屏幕下方的”注册“按钮进行注册，即可每月接收您的电费通知，也可以交电费。";
								sendMsg = new WxSendTextMsg(sendMsg, content);
//							}
						}else if("unsubscribe".equals(event)){//取消关注
							//解除绑定
							weiXinService.unBindWx(openId);
						}else if("CLICK".equals(event)){
							if("TUI_JIAN".equals(m.getEventKey())){
								String content = "";
								sendMsg = new WxSendTextMsg(sendMsg, content);
							}else if("pay".equals(m.getEventKey())){
								String content = "即将开通此服务，敬请期待。";
								sendMsg = new WxSendTextMsg(sendMsg, content);
							}
						}else if("SCAN".equals(event)){
							String ticket = m.getTicket();
							//判断改粉丝是否存在
//							int bindflag = weiXinService.bindWeiXin1(FileUploadConstants.APPID, FileUploadConstants.APPSECRET, openId,request);
//							if(bindflag>0){
								//将推荐人和被推荐人绑定。
								hrefereeService.bindTuijian1(ticket,openId);
//							}
								String content = "您好，欢迎您关注“恒信通企业服务”公众号，请您点击屏幕下方的”注册“按钮进行注册，即可每月接收您的电费通知，也可以交电费。";
								sendMsg = new WxSendTextMsg(sendMsg, content);
						}else if("LOCATION".equals(event)){
							String str = weiXinService.getBaiDuLocationXY(m.getLatitude(),m.getLongitude());
							System.out.println("------------str---------"+str);
						}
						WeiXin.send(sendMsg, response.getOutputStream());
					}else if(msg instanceof WxRecvTextMsg){
						WxRecvTextMsg m = (WxRecvTextMsg) msg;
						String content =  m.getContent();
						String basepath = request.getSession().getServletContext().getRealPath("/");
						if(content.contains("合作")){//机构验证
							System.out.println("------------合作---------"+openId);
							//判断是否验证
							int b = hverificateService.isVerficate(openId);
							System.out.println("------------合作---------"+b);
							if(b==1){//验证成功
								sendMsg = new WxSendNewsMsg(sendMsg).addItem(
										"验证成功",
										"感谢您关注恒信通微信公众服务号",
										FileUploadConstants.TW_PATH + "/images/subscribe.png",
										FileUploadConstants.TW_PATH + "/weixin/toIndex?openId=" + openId);
								WeiXin.send(sendMsg, response.getOutputStream());
							}else if(b==-1){//未验证
								sendMsg = new WxSendNewsMsg(sendMsg).addItem(
										"验证",
										"感谢您关注恒信通微信公众服务号",
										FileUploadConstants.TW_PATH + "/images/subscribe.png",
										FileUploadConstants.TW_PATH + "/hAgent/toIndex?openId=" + openId);
								WeiXin.send(sendMsg, response.getOutputStream());
							}else{
								if(b==2){
									sendMsg = new WxSendTextMsg(sendMsg, "您的帐号已被暂停，如有疑问，请拨打96199咨询。");
								}else if(b==0){
									sendMsg = new WxSendTextMsg(sendMsg, "您的帐号已被终止，如有疑问，请拨打96199咨询。");
								}
								WeiXin.send(sendMsg, response.getOutputStream());
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	/**
	 * 行业动态
	 * @param sendMsg
	 * @return
	 */
	public WxSendMsg getListNews(WxSendMsg sendMsg,String openId){
		try{
				List<WxSendNewsMsgItem> items = new LinkedList<WxSendNewsMsgItem>();
				
				String pageUrl1 = FileUploadConstants.URL_PATH + "/weixin/midoole?openId="+openId+"&styleType=1";
				String imgUrl1 = FileUploadConstants.URL_PATH + "/images/news/2016.jpg";
				WxSendNewsMsgItem item1 = new WxSendNewsMsgItem("携手国网电力八周年，服务用户过亿次","携手国网电力八周年，服务用户过亿次",imgUrl1,"");
				items.add(item1);
				
				String pageUrl2 = FileUploadConstants.URL_PATH + "/weixin/midoole?shopCode=3202&openId="+openId+"&styleType=2";
				String imgUrl2 = FileUploadConstants.URL_PATH + "/images/news/zhineng.png";
				WxSendNewsMsgItem item2 = new WxSendNewsMsgItem("智能电充值","智能电充值",imgUrl2,pageUrl2);
				items.add(item2);
				
				String pageUrl3 = FileUploadConstants.URL_PATH + "/weixin/midoole?shopCode=3102&openId="+openId+"&styleType=3";
				String imgUrl3 = FileUploadConstants.URL_PATH + "/images/news/chaobiao.png";
				WxSendNewsMsgItem item3 = new WxSendNewsMsgItem("抄表电充值","抄表电充值",imgUrl3,pageUrl3);
				items.add(item3);
				
				String pageUrl4 = FileUploadConstants.URL_PATH + "/weixin/midoole?openId="+openId+"&styleType=4";
				String imgUrl4 = FileUploadConstants.URL_PATH + "/images/news/zhangdan.png";
				WxSendNewsMsgItem item4 = new WxSendNewsMsgItem("账单与发票","账单与发票",imgUrl4,pageUrl4);
				items.add(item4);
					
				String pageUrl5 = FileUploadConstants.URL_PATH + "/weixin/midoole?openId="+openId+"&styleType=5";
				String imgUrl5 = FileUploadConstants.URL_PATH + "/images/news/diyongquan.png";
				WxSendNewsMsgItem item5 = new WxSendNewsMsgItem("我的抵用券","我的抵用券",imgUrl5,pageUrl5);
				items.add(item5);
					
				sendMsg = new WxSendNewsMsg(sendMsg).setItemsR(items);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return sendMsg;
	}
	
	/**
	 * 转向充值页面
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping("/midoole")
	public String midoole(HttpServletResponse response,HttpServletRequest request,Model model){
		
		String openId = request.getParameter("openId");
		String styleType = request.getParameter("styleType");
		try{
			if (weiXinService.isBind(openId,request,FileUploadConstants.COMPANY_ROLE_ID)) {// 如果绑定了
			}else{
				weiXinService.bindWeiXin(FileUploadConstants.APPID, FileUploadConstants.APPSECRET, openId,request);
			}
			if("1".equals(styleType)){
				return  "redirect:/hAccount/goCuXiao?openId="+openId;
			}else if("2".equals(styleType)){
				return  "redirect:/weixin/goIntelligentAddress?shopCode=3202" ;
			}else if("3".equals(styleType)){
				return  "redirect:/weixin/goIntelligentAddress?shopCode=3102" ;
			}else if("4".equals(styleType)){
				return  "redirect:/hUserAddress/toWxIndex";
			}else if("5".equals(styleType)){
				return  "redirect:/weixin/voucher?openId="+openId;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 分享后的页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toshare")
	public String toshare(HttpServletRequest request, HttpServletResponse response, Model model){
		super.getJsticket(request);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			String scene_id = request.getParameter("scene_id");
//			String name = request.getParameter("name");
			HEwm hEwm = new HEwm();
			hEwm.setScene_id(scene_id);
			hEwm.setStyle(1);
			HEwm hEwm1 = hewmService.getHEwm(hEwm);
			Calendar cl = Calendar.getInstance();
			cl.setTime(hEwm1.getCreateTime());
			cl.add(Calendar.DATE,3);
			String endtime = sf.format(cl.getTime());
			//获取昵称
			String nickName = null;
			ManageAdminUser adminUser = new ManageAdminUser();
			adminUser.setOpenId(hEwm1.getOpenId());
			adminUser.setRoleId(Integer.valueOf(FileUploadConstants.COMPANY_ROLE_ID));
			ManageAdminUser user = manageadminuserService.getManageAdminUser(adminUser);
			if(user!=null){
				nickName = user.getNickName();
			}
			if(StringUtils.isNotBlank(nickName)){
				model.addAttribute("name", java.net.URLDecoder.decode(nickName,"utf-8"));
			}else{
				model.addAttribute("name", "");
			}
			model.addAttribute("hEwm", hEwm1);
			model.addAttribute("endtime", endtime);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/wx/share";
	}
	
	/**
	 * 分享后的页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toshare1")
	public String toshare1(HttpServletRequest request, HttpServletResponse response, Model model){
		super.getJsticket(request);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			String scene_id = request.getParameter("scene_id");
//			String name = request.getParameter("name");
			HEwm hEwm = new HEwm();
			hEwm.setScene_id(scene_id);
			hEwm.setStyle(1);
			HEwm hEwm1 = hewmService.getHEwm(hEwm);
			Calendar cl = Calendar.getInstance();
			cl.setTime(hEwm1.getCreateTime());
			cl.add(Calendar.DATE,3);
			String endtime = sf.format(cl.getTime());
			model.addAttribute("hEwm", hEwm1);
			//获取昵称
			String nickName = null;
			ManageAdminUser adminUser = new ManageAdminUser();
			adminUser.setOpenId(hEwm1.getOpenId());
			adminUser.setRoleId(Integer.valueOf(FileUploadConstants.COMPANY_ROLE_ID));
			ManageAdminUser user = manageadminuserService.getManageAdminUser(adminUser);
			if(user!=null){
				nickName = user.getNickName();
			}
			if(StringUtils.isNotBlank(nickName)){
				model.addAttribute("name", java.net.URLDecoder.decode(nickName,"utf-8"));
			}else{
				model.addAttribute("name", "");
			}
			model.addAttribute("endtime", endtime);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/wx/share1";
	}
	
	/**
	 * 分享后的页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toshare2")
	public String toshare2(HttpServletRequest request, HttpServletResponse response, Model model){
		super.getJsticket(request);
		try{
			String scene_id = request.getParameter("scene_id");
			HEwm hEwm = new HEwm();
			hEwm.setScene_id(scene_id);
			hEwm.setStyle(2);
			HEwm hEwm1 = hewmService.getHEwm(hEwm);
			model.addAttribute("hEwm", hEwm1);
			//获取机构名称 
			HAgentTwo hAgentTwo1 = new HAgentTwo();
			hAgentTwo1.setOpenId(hEwm1.getOpenId());
			HAgentTwo hAgentTwo = hagenttwoService.getHAgentTwo(hAgentTwo1);
			String nickName = null;
			if(hAgentTwo!=null&&hAgentTwo.getId()>0){
				nickName = hAgentTwo.getName();
			}else{
				HAgent hAgent1 = new HAgent();
				hAgent1.setOpenId(hEwm1.getOpenId());
				HAgent hAgent = hagentService.getHAgent(hAgent1);
				if(hAgent!=null&&hAgent.getId()>0){
					nickName = hAgent.getName();
				}
			}
			if(StringUtils.isNotBlank(nickName)){
				model.addAttribute("name", java.net.URLDecoder.decode(nickName,"utf-8"));
			}else{
				model.addAttribute("name", "");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/wx/share2";
	}
	
	/**
	 * 生成分享的二维码
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/createEWM")
	public String createEWM(HttpServletRequest request, HttpServletResponse response, Model model){
		try{
			JSONObject json = new JSONObject();
			ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			//判断用户验证的在机构
			HVerificate hVerificate = new HVerificate();
			hVerificate.setOpenId(adminUser.getOpenId());
			hVerificate.setState(1);
			HVerificate ver = hverificateService.getHVerificate(hVerificate);
			//判断二维码是否存在
			HEwm hEwm = new HEwm();
			hEwm.setOpenId(ver.getAgentOpenId());
			hEwm.setStyle(2);
			HEwm ewm = hewmService.getHEwm(hEwm);
			if(ewm!=null&&ewm.getId()>0){
				json.put("ticket", ewm.getScene_id());
			}
			response.setContentType("text/html;charset=utf-8");
	        response.setHeader("Cache-Control","no-cache");
	        response.getWriter().write(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
//	/**
//	 * 生成分享的二维码
//	 * @param request
//	 * @param response
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping(value = "/createEWM")
//	public String createEWM(HttpServletRequest request, HttpServletResponse response, Model model){
//		try{
//			JSONObject json = new JSONObject();
//			ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
//			String sceneId = "";
//			for(int i=0;i<32;i++){
//				sceneId += (new Random().nextInt(9)+1);
//			}
//			Map<String,String> maperm = weiXinService.getEWM(FileUploadConstants.APPID, FileUploadConstants.APPSECRET, sceneId);
//			//保存数据库
//			HEwm hEwm = new HEwm();
////			hEwm.setUserId(adminUser.getAdminId());
//			hEwm.setImgUrl(maperm.get("imgurl"));
//			hEwm.setCreateTime(new Date());
//			hEwm.setScene_id(maperm.get("ticket"));
//			hEwm.setOpenId(adminUser.getOpenId());
//			hEwm.setStyle(1);
//			hewmService.insertHEwm(hEwm);
//			json.put("ticket", maperm.get("ticket"));
//			response.setContentType("text/html;charset=utf-8");
//			response.setHeader("Cache-Control","no-cache");
//			response.getWriter().write(json.toString());
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		return null;
//	}
	
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
	 * 机构绑定页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toAgentBind")
	public String toAgentBind(HttpServletRequest request, HttpServletResponse response, Model model){
		super.getJsticket(request);
		String openId = request.getParameter("openId"); 
		model.addAttribute("openId", openId);
		return "/wx/agent/bind";
	}
	
	/**
	 * 返回监控列表
	 * showShare
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/toOrder")
	public String toWatchVideo(HttpServletRequest request, HttpServletResponse response, Model model){
		return "/wx/agent/orderIndex";
	}
	
	@RequestMapping("/toIndex")
	public String toIndex(HttpServletRequest request, HttpServletResponse response, Model model){
		super.getJsticket(request);
		String openId = request.getParameter("openId");
		String agentOpenId = null;
		try{
//			Object obj = (Object)request.getSession().getAttribute(SessionName.ADMIN_USER);
//			if(obj!=null){
//				ManageAdminUser adminUser = (ManageAdminUser)obj;
//				openId = adminUser.getOpenId();
//			}
			//判断是否验证
			int b = hverificateService.isVerficate(openId);
			if(b==1){
				//获取余额
				boolean bc = true;
				String roleId = null;
				HVerificate hVerificate = new HVerificate();
				hVerificate.setOpenId(openId);
				hVerificate.setState(1);
				HVerificate ver = hverificateService.getHVerificate(hVerificate);
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
//					//查询账户余额
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
				}else{
					return "/wx/405";
				}
			}else if(b==-1){
				return "redirect:/hAgent/toIndex?openId="+openId;
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
		model.addAttribute("openId", openId);
		model.addAttribute("agentOpenId", agentOpenId);
		return "/wx/myInfo";
	}
	
	/**
	 * 生成带参数的绑定连接
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/getBindUrl", method = RequestMethod.GET)
	public String getBindUrl(HttpServletRequest request, HttpServletResponse response, Model model) throws UnsupportedEncodingException{
		super.getJsticket(request);
		try{
			String agentOpenId = request.getParameter("agentOpenId");
			String openId = request.getParameter("openId");
			String param = URLEncoder.encode(FileUploadConstants.URL_PATH+"/weixin/access?appid="+FileUploadConstants.APPID+"&create_openId="+agentOpenId+"&maketwo=maketwo", "utf-8");
			String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+FileUploadConstants.APPID+"&redirect_uri="+param+"&response_type=code&scope=snsapi_userinfo&state=twoAgentBind#wechat_redirect";
			model.addAttribute("url", url);
			model.addAttribute("agentOpenId", agentOpenId);
			model.addAttribute("openId", openId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/wx/agent/bindUrl";
	}
	
	/**
	 *	我的二维码
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/toMyQRcode", method = RequestMethod.GET)
	public String howMakeMoney(HttpServletRequest request, HttpServletResponse response, Model model){
		super.getJsticket(request);
		try{
			String openId = request.getParameter("openId");
			String agentOpenId = request.getParameter("agentOpenId");
			String flag = request.getParameter("flag");
			String imgUrl = null;
			//获取机构信息
			if(!StringUtils.isNotBlank(agentOpenId)){
				ManageAdminUser manageAdminUser = new ManageAdminUser();
				manageAdminUser.setOpenId(openId);
				manageAdminUser.setState(1);
				manageAdminUser.setRoleId(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
				manageAdminUser = manageadminuserService.getAdminUserByLogin(manageAdminUser);
				if(manageAdminUser!=null&&manageAdminUser.getAdminId()>0){
					agentOpenId = manageAdminUser.getOneAgentOpenId();
					model.addAttribute("level", 1);
					model.addAttribute("nickName", manageAdminUser.getOneAgentName());
				}else{
					ManageAdminUser manageAdminUser2 = new ManageAdminUser();
					manageAdminUser2.setOpenId(openId);
					manageAdminUser2.setState(1);
					manageAdminUser2.setRoleId(Integer.valueOf(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID));
					manageAdminUser2 = manageadminuserService.getAdminUserByLogin(manageAdminUser2);
					if(manageAdminUser2!=null&&manageAdminUser2.getAdminId()>0){
						model.addAttribute("nickName", manageAdminUser2.getTwoAgentName());
						agentOpenId = manageAdminUser2.getTwoAgentOpenId();
						model.addAttribute("level", 2);
					}
				} 
			}
			//获取机构二维码
			HEwm ewm = new HEwm();
			ewm.setOpenId(agentOpenId);
			ewm.setStyle(2);
			HEwm ewm1 = hewmService.getHEwm(ewm);
			if(ewm1!=null&&ewm1.getId()>0){
				imgUrl = ewm1.getImgUrl();
			}else{
				Map<String,String> map = weiXinService.getEWMYj(FileUploadConstants.APPID, FileUploadConstants.APPSECRET, UUID.randomUUID().toString().replace("-", ""));
				imgUrl = map.get("imgurl");
				//插入数据库
				hEwmService.saveEwmYj(map.get("imgurl").toString(), map.get("ticket").toString(), agentOpenId);
			}
			model.addAttribute("openId", openId);
			model.addAttribute("agentOpenId", agentOpenId);
			model.addAttribute("imgUrl", imgUrl);
			model.addAttribute("flag", flag);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/wx/myQRcode";
	}
	
	@RequestMapping(value = "/toMyInfo")
	public String toMyInfo(HttpServletRequest request, HttpServletResponse response, Model model){
		String openId = RequestHandler.getString(request, "openId");
		String agentOpenId = null;
		//获取余额
		String roleId = null;
		HVerificate hVerificate = new HVerificate();
		hVerificate.setOpenId(openId);
		hVerificate.setState(1);
		HVerificate ver = hverificateService.getHVerificate(hVerificate);
		if(ver.getLevel()==1){
			agentOpenId = ver.getAgentOpenId();
			roleId = FileUploadConstants.ONE_AGENT_MANAGE_ROLEID;
		}else if(ver.getLevel()==2){
			agentOpenId = ver.getAgentOpenId();
			roleId = FileUploadConstants.TWO_AGENT_MANAGE_ROLEID;
		}else if(ver.getLevel()==3){
			agentOpenId = ver.getAgentOpenId();
			roleId = FileUploadConstants.SERVICER_MANAGE_ROLEID;
		}
//		//查询账户余额
		HUserAccount hUserAccount = new HUserAccount();
		hUserAccount.setOpenId(openId);
		hUserAccount = huseraccountService.getHUserAccount(hUserAccount);
		if(hUserAccount!=null){
			model.addAttribute("myMoney", hUserAccount.getTotalFee());
		}
		weiXinService.isBind(openId,request,roleId);
		model.addAttribute("roleId", roleId);
		model.addAttribute("agentOpenId", agentOpenId);
		model.addAttribute("openId", openId);
		return "/wx/myInfo";
	}
	
	/**
	 * 解绑
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/unbind")
	public String unbind(HttpServletRequest request, HttpServletResponse response, Model model){
		ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
		try{
//			adminUser.setCancelTime(new Date());
//			manageadminuserService.unBindWx(adminUser);
			
			HVerificate hVerificate = new HVerificate();
			hVerificate.setOpenId(adminUser.getOpenId());
			hVerificate.setState(1);
			HVerificate ver = hverificateService.getHVerificate(hVerificate);
			if(ver!=null){
				hverificateService.removeHVerificate(ver);
			}
			//解绑账户
			HUserAccount hUserAccount = new HUserAccount();
			hUserAccount.setOpenId(adminUser.getOpenId());
			List<HUserAccount> list = huseraccountService.getHUserAccountBaseList(hUserAccount);
			if(list!=null&&list.size()>0){
				for(HUserAccount account:list){
					huseraccountService.unBindUserAccount(account);
				}
			}
			//清除用户表openId
			manageadminuserService.unBindWx(adminUser);
			//解绑提现申请
			HPresentApply hPresentApply = new HPresentApply();
			hPresentApply.setOpenId(adminUser.getOpenId());
			hpresentapplyService.unBindPresentApply(hPresentApply);
			writeSuccessMsg("success", null, response);
		}catch(Exception e){
			writeSuccessMsg("error", null, response);
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 微信注册验证码验证
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/checkVercode")
	public String checkVercode(HttpServletRequest request, HttpServletResponse response, Model model){
		try{
			String phone = RequestHandler.getString(request, "phone");
			String vercode = RequestHandler.getString(request, "vercode");
			Integer errCount = 0;
			if(request.getSession().getAttribute("errCount")!=null){
				errCount = Integer.valueOf(request.getSession().getAttribute("errCount").toString());
			}
			String sysCode = SendMsgUtil.CODEMAP.get(phone);
			if(StringUtils.isNotBlank(phone)&&StringUtils.isNotBlank(vercode)&&vercode.equals(sysCode)){
				writeSuccessMsg("success", null, response);
			}else{
				request.getSession().setAttribute("errCount", ++errCount);
				writeSuccessMsg("验证码不正确", "{\"errCount\":\""+errCount+"\"}", response);
			}
		}catch(Exception e){
			writeSuccessMsg("error", null, response);
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public static void main(String args[]){
		/*String param = null;
		try {
			param = URLEncoder.encode(FileUploadConstants.URL_PATH+"/weixin/access?appid="+FileUploadConstants.APPID+"&oneAgentOpenId=8944576bc20b41ff9892f5a67e8f0bb7&twoAgentOpenId=", "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+FileUploadConstants.APPID+"&redirect_uri="+param+"&response_type=code&scope=snsapi_userinfo&state=h5Recharge#wechat_redirect";
		System.out.println(url);*/
	}
	
	@RequestMapping(value = "/toQuestion")
	public String toQuestion(HttpServletRequest request, HttpServletResponse response, Model model){
		return "/wx/question";
	}
	
	@RequestMapping(value = "/toReg")
	public String toReg(HttpServletRequest request, HttpServletResponse response, Model model){
		String openId = RequestHandler.getString(request, "openId");
		//验证openId是否被注册
//		ManageAdminUser adminUser = new ManageAdminUser();
//		adminUser.setOpenId(openId);
//		adminUser.setState(1);
//		List<ManageAdminUser> list= manageadminuserService.getManageAdminUserBaseList(adminUser);
//		if(list!=null&&list.size()>0){
//			model.addAttribute("errOpenId", 1);
//		}
		//检查是否有推荐人
		HReferee hReferee = new HReferee();
		hReferee.setBeituijianId(openId);
		hReferee = hrefereeService.getHReferee(hReferee);
		if(hReferee!=null&&hReferee.getId()>0){
			model.addAttribute("refereeOpenId", hReferee.getTjrId());
		}
		model.addAttribute("openId", openId);
		return "/wx/reg";
	}
	@RequestMapping(value = "/toAmmeter")
	public String toAmmeter(HttpServletRequest request, HttpServletResponse response, Model model){
		String openId = RequestHandler.getString(request, "openId");
		String content_phone = RequestHandler.getString(request, "content_phone");
		String refereeOpenId = RequestHandler.getString(request, "refereeOpenId");
		if(request.getHeader("referer").indexOf("weixin/toReg")!=-1){
			String phone = RequestHandler.getString(request, "phone");
			String ammeterNo = RequestHandler.getString(request, "ammeterNo");
			String ammeterInfo = RequestHandler.getString(request, "ammeterInfo");
			if(StringUtils.isNotBlank(openId)&&StringUtils.isNotBlank(phone)&&StringUtils.isNotBlank(ammeterNo)){
				model.addAttribute("phone", phone);
				model.addAttribute("refereeOpenId", refereeOpenId);
				model.addAttribute("ammeterNo", ammeterNo);
				org.json.JSONArray jsonArray = new org.json.JSONArray(ammeterInfo);
				org.json.JSONObject json = jsonArray.getJSONObject(0);
				model.addAttribute("accountName",json.getJSONObject("resultInfo").getString("accountName"));
				model.addAttribute("address",json.getJSONObject("resultInfo").getString("address"));
				model.addAttribute("accountFee",json.getJSONObject("resultInfo").getString("accountFee"));
				model.addAttribute("lateFee",json.getJSONObject("resultInfo").getString("lateFee").replaceAll("。", ""));
				model.addAttribute("totalFee",json.getString("totalFee"));
			}else{
				model.addAttribute("errMsg", "参数不正确");
			}
		}else{
			model.addAttribute("errMsg", "非法请求");
		}
		model.addAttribute("recPhone",RequestHandler.getString(request, "recPhone"));
		model.addAttribute("openId", openId);
		model.addAttribute("content_phone", content_phone);
		return "/wx/ammeter";
	}
	
	/**
	 * 我的客户
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toMyCustomer")
	public String toMyCustomer(HttpServletRequest request, HttpServletResponse response, Model model){
		String openId = RequestHandler.getString(request, "openId");
		model.addAttribute("openId", openId);
		return "/wx/myCustomer";
	}
	/** 发验证码 **/
	@RequestMapping(value = "/wxVercode", method = RequestMethod.POST)
	public String wxVercode(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception
	{
		String phone =  RequestHandler.getString(request, "phone");
		String vercode = RequestHandler.getString(request, "vercode");
		Object token = request.getSession().getAttribute(SessionName.TOKEN);// 验证码
		if(StringUtils.isNotBlank(phone)){
			HCompany tmp = new HCompany();
			tmp.setContact_phone(phone);
			List list = hCompanyService.getHCompanyBaseList(tmp);
			if(list!=null&&list.size()>0){
				writeErrorMsg("该手机号已注册！", "", response);
			}else{
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
	/**
	 * 调单操作，查询电表是否有欠费
	 * key:resultCode       查询是否成功  00:成功/CS:超时/其他失败
	 * key:totalFee         欠费总金额（单位：元），包含滞纳金，有两位小数
	 * key:paymentInfo   中心流水号（销账的时候要用到）
	 * key:resultInfo       其他信息
	 * {
	 *	    "totalFee": "5585.77",
	 *		"resultCode": "00",
	 *	    "resultInfo": {
	 *	        "accountName": "北京市公安局房山分局",
	 *	        "address": "北京市房山区城关街道办事处北关村小田庄",
	 *	        "accountFee": "2436.01元",
	 *	        "accountTime": "201406",
	 *	        "lateFee": "3149.76元"
	 *	    },
	 *	    "paymentInfo": "32151118115542022175"
	 *	}
	 *
	 */
	@RequestMapping(value = "/getAmmeterInfo", method = RequestMethod.GET)
	public String getAmmeterInfo(HttpServletRequest request, HttpServletResponse response, Model model){
		Map<String, Object> resultMap = new HashMap<String, Object>();//返回数据

		try{
			String electricNum = RequestHandler.getString(request, "electricNum");//电表号
			HAmmeterInfo am = new HAmmeterInfo();
			am.setAmmeter_no(electricNum);
			am.setDelete_state(1);
			List list = hAmmeterInfoService.getHAmmeterInfoBaseList(am);
			if(list!=null&&list.size()>0){
				resultMap.put("status", "fail");
				resultMap.put("msg", "该缴费号已被其他单位绑定!");
			}else{
				String IP = this.getIpAddr(request);
				System.out.println("-----IP-------------"+IP);
				System.out.println("-----electricNum-------------"+electricNum);
				com.alibaba.fastjson.JSONObject result = hCommonService.hXTServiceQuery(electricNum, IP);
				if("00".equals(result.get("resultCode"))){
					resultMap.put("status", "success");
					resultMap.put("msg", "获取电表信息成功!");
					resultMap.put("data", result);
				}else{
					resultMap.put("status", "fail");
					resultMap.put("msg", "不存在该电表信息!");
				}
			}
		}catch (Exception e){
			resultMap.put("status", "fail");
			resultMap.put("msg", "获取电表信息失败!");
		}
		ResponseUtil.responseText(response, gson.toJson(resultMap));
		return null;
	}
	
	@RequestMapping(value = "/toChangePeople")
	public String toChangePeople(HttpServletRequest request, HttpServletResponse response, Model model){
		Integer servicerId = RequestHandler.getInteger(request, "servicerId");//支持人员ID
		Integer companyId = RequestHandler.getInteger(request, "companyId");//公司ID
		//查询其他的支持人员
		ManageAdminUser manageAdminUser = new ManageAdminUser();
		manageAdminUser.setRoleId(Integer.valueOf(FileUploadConstants.SERVICER_MANAGE_ROLEID));
		manageAdminUser.setOtherServicerId(servicerId);
		List<ManageAdminUser> listUser = manageadminuserService.getOtherManageUserList(manageAdminUser);
		ManageAdminUser manageAdminUser1 = new ManageAdminUser();
		manageAdminUser1.setAdminId(servicerId);
		ManageAdminUser user = manageadminuserService.getAdminUserByLogin(manageAdminUser1);
		
		HCompany hCompany = new HCompany();
		hCompany.setId(companyId);
		hCompany = hcompanyService.getHCompany(hCompany);
		
		model.addAttribute("listUser", listUser);
		model.addAttribute("user", user);
		model.addAttribute("company", hCompany);
		return "/wx/changePeople";
	}
	@RequestMapping(value = "/toAccount")
	public String toAccount(HttpServletRequest request, HttpServletResponse response, Model model){
		ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
		try{
			BigDecimal totalFee = new BigDecimal("0.00");
			BigDecimal arrival = new BigDecimal("0.00");
			BigDecimal applyFee = new BigDecimal("0.00");
			if(adminUser!=null){
				//获取账户
				HUserAccount acc = new HUserAccount();
				acc.setOpenId(adminUser.getOpenId());
				if(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID.equals(adminUser.getRoleId().toString())){//一级代理 
					acc.setOneAgentOpenId(adminUser.getOneAgentOpenId());
				}else if(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID.equals(adminUser.getRoleId().toString())){//二级代理
					acc.setTwoAgentOpenId(adminUser.getTwoAgentOpenId());
				}else if(FileUploadConstants.SERVICER_MANAGE_ROLEID.equals(adminUser.getRoleId().toString())){//服务人员
					acc.setServicerId(adminUser.getAdminId());
				}
				acc.setRole_id(adminUser.getRoleId());
				acc.setStatus(1);
				acc = huseraccountService.getHUserAccount(acc);
				if(acc!=null){
					totalFee = acc.getTotalFee();//总余额
					//已到账金额
					//获取明细
					HUserAccountDetail detail = new HUserAccountDetail();
					detail.setUserAccountId(acc.getId());
					detail.setType(2);
					List<HUserAccountDetail> detailList = huseraccountdetailService.getHUserAccountDetailBaseList(detail);
					if(detailList!=null&&detailList.size()>0){
						for(HUserAccountDetail de : detailList){
							arrival = arrival.add(de.getTotalFee());
						}
					}
					//审核中金额
					HPresentApply pres = new HPresentApply();
					pres.setOpenId(adminUser.getOpenId());
					pres.setStatus(0);
					List<HPresentApply> presList = hpresentApplyservice.getHPresentApplyBaseList(pres);
					if(presList!=null&&presList.size()>0){
						for(HPresentApply pre : presList){
							applyFee = applyFee.add(pre.getAllFee());
						}
					}
				}else{//创建账户
					acc = new HUserAccount();
					acc.setOpenId(adminUser.getOpenId());
					acc.setTotalFee(new BigDecimal("0.00"));
					acc.setCreateTime(new Date());
					acc.setStatus(1);
					if(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID.equals(adminUser.getRoleId().toString())){//一级代理 
						HAgent agent = new HAgent();
						agent.setOpenId(adminUser.getOpenId());
						agent = hagentService.getHAgent(agent);
						if(agent!=null){
							acc.setOneAgentOpenId(agent.getOpenId());
							acc.setOneAgentName(agent.getName());
							acc.setNickName(agent.getName());
							acc.setPhone(agent.getMobile1());
							acc.setMobile(agent.getMobile2());
							acc.setRole_id(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
						}
					}else if(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID.equals(adminUser.getRoleId().toString())){
						HAgentTwo agentTwo = new HAgentTwo();
						agentTwo.setOpenId(adminUser.getTwoAgentOpenId());
						agentTwo = hagenttwoService.getHAgentTwo(agentTwo);
						if(agentTwo!=null){
							acc.setOneAgentOpenId(agentTwo.getCreate_openId());
							acc.setOneAgentName(adminUser.getOneAgentName());
							acc.setTwoAgentOpenId(agentTwo.getOpenId());
							acc.setTwoAgentName(agentTwo.getName());
							acc.setNickName(agentTwo.getName());
							acc.setPhone(agentTwo.getMobile1());
							acc.setMobile(agentTwo.getMobile2());
							acc.setRole_id(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
						}
					}else if(FileUploadConstants.SERVICER_MANAGE_ROLEID.equals(adminUser.getRoleId().toString())){
						acc = new HUserAccount();
						acc.setServicerId(adminUser.getAdminId());
						acc.setOpenId(adminUser.getOpenId());
						acc.setTotalFee(new BigDecimal("0.00"));
						acc.setCreateTime(new Date());
						acc.setStatus(1);
						acc.setServicerName(adminUser.getRealName());
						acc.setNickName(adminUser.getNickName());
						acc.setPhone(adminUser.getPhone());
						acc.setMobile(adminUser.getMobile());
						acc.setRole_id(Integer.valueOf(FileUploadConstants.SERVICER_MANAGE_ROLEID));
					}
					int accId = huseraccountService.insertHUserAccount(acc);
				}
			}
			model.addAttribute("totalFee", totalFee);
			model.addAttribute("arrival", arrival);
			model.addAttribute("applyFee", applyFee);
			//日期判断
			Calendar ca = Calendar.getInstance();
			int day = ca.get(Calendar.DAY_OF_MONTH);
			if(day>=1&&day<=5){
				//本月是否有申请判断
				HPresentApply hPresentApply = new HPresentApply();
				hPresentApply.setOpenId(adminUser.getOpenId());
				hPresentApply.setFlag(1);
//				hPresentApply.setStatus(0);
				int count = hpresentApplyservice.getHPresentApplyListCount(hPresentApply);
				if(count>0){
					model.addAttribute("dayFlag", 0);
				}else{
					HPresentApply hPresentApply1 = new HPresentApply();
					hPresentApply1.setOpenId(adminUser.getOpenId());
					hPresentApply1.setStatus(0);
					int count1 = hpresentApplyservice.getHPresentApplyListCount(hPresentApply1);
					if(count1>0){
						model.addAttribute("dayFlag", 0);
					}else{
						model.addAttribute("dayFlag", 1);
					}
				}
			}else{
				model.addAttribute("dayFlag", 0);
			}
			model.addAttribute("taxRate", FileUploadConstants.TAX_RATE);
		}catch(Exception e){
			e.printStackTrace();
		}
		model.addAttribute("twoRoleId", FileUploadConstants.TWO_AGENT_MANAGE_ROLEID);
		return "/wx/account";
	}
	
	@RequestMapping(value = "/wxApply", method = RequestMethod.GET)
	public String wxApply(HttpServletRequest request, HttpServletResponse response, Model model){
		Map<String, Object> resultMap = new HashMap<String, Object>();//返回数据
		try{
			ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			//时间判断 
			Calendar ca = Calendar.getInstance();
			if(ca.get(Calendar.DAY_OF_MONTH)>=1&&ca.get(Calendar.DAY_OF_MONTH)<=5){
				//判断是否有申请,每月一次
				HPresentApply applyOnly = new HPresentApply();
				applyOnly.setOpenId(adminUser.getOpenId());
				SimpleDateFormat sf = new SimpleDateFormat("yyyyMM");
				applyOnly.setBatchCode(sf.format(new Date()));
				int count = hpresentApplyservice.getHPresentApplyListCount(applyOnly);
				if(count==0){
					//查询是否有未处理的提现申请
					HPresentApply applyNo = new HPresentApply();
					applyNo.setOpenId(adminUser.getOpenId());
					applyNo.setStatus(0);
					int countNo = hpresentApplyservice.getHPresentApplyListCount(applyNo);
					if(countNo==0){
						if(adminUser==null){
							resultMap.put("status", "fail");
							resultMap.put("msg", "请先登录!");
						}else{
							BigDecimal totalFee = RequestHandler.getBigDecimal(request, "totalFee");
							if(totalFee!=null&&new BigDecimal("0.00").compareTo(totalFee)!=1){
								if(new BigDecimal("100").compareTo(totalFee)==1){
//								if(new BigDecimal("1").compareTo(totalFee)==1){
//								if(false){
									resultMap.put("status", "fail");
									resultMap.put("msg", "提现申请需大于100元!");
								}else{
									//获取账户
									HUserAccount acc = new HUserAccount();
									acc.setOpenId(adminUser.getOpenId());
									if(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID.equals(adminUser.getRoleId().toString())){//一级代理 
										acc.setOneAgentOpenId(adminUser.getOneAgentOpenId());
									}else if(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID.equals(adminUser.getRoleId().toString())){//二级代理
										acc.setTwoAgentOpenId(adminUser.getTwoAgentOpenId());
									}else if(FileUploadConstants.SERVICER_MANAGE_ROLEID.equals(adminUser.getRoleId().toString())){//服务人员
										acc.setServicerId(adminUser.getAdminId());
									}	
									acc.setRole_id(adminUser.getRoleId());
									acc.setStatus(1);
									acc = huseraccountService.getHUserAccount(acc);
									if(acc!=null){
										System.out.println(acc.getTotalFee().compareTo(totalFee));
										if(acc.getTotalFee()!=null&&acc.getTotalFee().doubleValue()<totalFee.doubleValue()){
											resultMap.put("status", "fail");
											resultMap.put("msg", "账户余额不足!");
										}else{
											//税
//											BigDecimal taxFee = totalFee.multiply(new BigDecimal(FileUploadConstants.TAX_RATE));
											//实际金额
//											BigDecimal realFee = totalFee.subtract(taxFee);
											HPresentApply apply = new HPresentApply();
											apply.setOpenId(adminUser.getOpenId());
//											if(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID.equals((adminUser.getRoleId()+""))){
//												apply.setTaxRate(FileUploadConstants.TAX_RATE);
//												apply.setTotalFee(totalFee);
////												apply.setTaxFee(taxFee);
//												apply.setAllFee(totalFee);
//											}else{
												apply.setTotalFee(totalFee);
												apply.setTaxFee(new BigDecimal(0.0));
												apply.setAllFee(totalFee);
//											}
											apply.setCreateTime(new Date());
											apply.setStatus(0);
											if(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID.equals(adminUser.getRoleId().toString())){//一级代理 
												apply.setOneAgentOpenId(adminUser.getOneAgentOpenId());
												apply.setOneAgentName(adminUser.getRealName());
											}else if(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID.equals(adminUser.getRoleId().toString())){//二级代理
												apply.setTwoAgentOpenId(adminUser.getTwoAgentOpenId());
												apply.setTwoAgentName(adminUser.getTwoAgentName());
											}else if(FileUploadConstants.SERVICER_MANAGE_ROLEID.equals(adminUser.getRoleId().toString())){//服务人员
												apply.setServicerId(adminUser.getAdminId());
												apply.setServicerName(adminUser.getRealName());
											}
											apply.setNickName(adminUser.getNickName());
											apply.setBatchCode(sf.format(new Date()));
											int applyId = hpresentApplyservice.insertHPresentApply(apply);
											//插入提现订单
//											Map map = new HashMap();
//											map.put("snNamePre", "WD");//编号前缀
//											map.put("snName", "PaySN");//编号名称，记录用
//											map.put("num", "4");//编号长度
//											map.put("sn", "@sn");//编号
//											String oNo = callProcedureService.callGenerateSnNo(map);//订单编号
//											HPayOrder order = new HPayOrder();
//											order.setOneAgentName(adminUser.getOneAgentName());
//											order.setOneAgentOpenId(adminUser.getOneAgentOpenId());
//											order.setTwoAgentName(adminUser.getTwoAgentName());
//											order.setTwoAgentOpenID(adminUser.getTwoAgentOpenId());
//											order.setServicerId(adminUser.getServicerId());
//											order.setServicerName(adminUser.getServicerName());
//											order.setO_no(oNo);
//											order.setAmount(totalFee.multiply(new BigDecimal("100")).intValue());
//											order.setOrder_type(2);
//											order.setC_id(-1);//提现订单
//											order.setApply_id(applyId);
//											hpayorderService.insertHPayOrder(order);
											resultMap.put("status", "success");
											resultMap.put("msg", "申请成功!");
										}
									}else{
										resultMap.put("status", "fail");
										resultMap.put("msg", "账户不存在!");
									}
									
								}
							}else{
								resultMap.put("status", "fail");
								resultMap.put("msg", "您有未处理的提现申请，请联系业务员处理!");
							}
						}
					}else{
						resultMap.put("status", "fail");
						resultMap.put("msg", "每月只允许申请一次提现!");
					}
				}else{
					resultMap.put("status", "fail");
					resultMap.put("msg", "每月只允许申请一次提现!");
				}
			}else{
				resultMap.put("status", "fail");
				resultMap.put("msg", "请在每月1-5日申请提现！");
			}
		}catch (Exception e){
			e.printStackTrace();
			resultMap.put("status", "fail");
			resultMap.put("msg", "获取电表信息失败!");
		}
		ResponseUtil.responseText(response, gson.toJson(resultMap));
		return null;
	}
	@RequestMapping(value = "/toAccountDetail")
	public String toAccountDetail(HttpServletRequest request, HttpServletResponse response, Model model){
		Map<String, Object> resultMap = new HashMap<String, Object>();//返回数据
		ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
		if(adminUser==null){
			resultMap.put("status", "fail");
			resultMap.put("msg", "请先登录!");
		}else{
			HUserAccountDetail param = new HUserAccountDetail();
			if(adminUser.getRoleId().toString().equals(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID)){
				param.setRoleType(1);
				param.setOneAgentOpenId(adminUser.getOneAgentOpenId());
			}else if(adminUser.getRoleId().toString().equals(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID)){
				param.setRoleType(2);
				param.setTwoAgentOpenID(adminUser.getTwoAgentOpenId());
			}else if(adminUser.getRoleId().toString().equals(FileUploadConstants.SERVICER_MANAGE_ROLEID)){
				param.setRoleType(3);
				param.setServicerId(adminUser.getAdminId());
			}
			Integer queryType = RequestHandler.getInteger(request, "queryType");
			param.setQueryType(queryType);
			Date start = RequestHandler.getDate(request, "startTime");
			param.setStartTime(start);
			Date end = RequestHandler.getDate(request, "endTime");
			param.setEndTime(end);
			String cname = RequestHandler.getString(request, "cname");
			param.setC_name(cname);
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			//获取账户
			HUserAccount account = new HUserAccount();
			account.setOpenId(adminUser.getOpenId());
			if(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID.equals(adminUser.getRoleId().toString())){//一级代理 
				account.setOneAgentOpenId(adminUser.getOneAgentOpenId());
			}else if(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID.equals(adminUser.getRoleId().toString())){//二级代理
				account.setTwoAgentOpenId(adminUser.getTwoAgentOpenId());
			}else if(FileUploadConstants.SERVICER_MANAGE_ROLEID.equals(adminUser.getRoleId().toString())){//服务人员
				account.setServicerId(adminUser.getAdminId());
			}	
			account.setRole_id(adminUser.getRoleId());
			account = huseraccountService.getHUserAccount(account);
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			param.setRowStart(from);
			param.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			param.setSortColumn(sortColumn);
			param.setUserAccountId(account.getId());
			
			int allCount = huserAccountDetailService.getHUserAccountDetailListCount(param);
			/*ResponseList<HUserAccountDetail> detailList = null;
			if ( allCount > 0 )
			{
				detailList = huserAccountDetailService.getHUserAccountDetailList(param);
			} else
			{
				detailList = new ResponseList<HUserAccountDetail>();
			}
			// 设置数据总数
			detailList.setTotalResults(allCount);*/
			param = huserAccountDetailService.getAllDetailFee(param);
			
			DecimalFormat df = new DecimalFormat("0.00");
			model.addAttribute("allMoney", account.getTotalFee()==null?"0.00":df.format(account.getTotalFee()));
			if(param!=null&&param.getAllOrderFee()!=null){
//				BigDecimal subFee22222 = new BigDecimal(param.getAllOrderFee());
				System.out.println("--------------param.getAllOrderFee-------------------"+param.getAllOrderFee());
				model.addAttribute("allDetailFee",(param.getAllOrderFee().divide(new BigDecimal(100))).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			}else{
				model.addAttribute("allDetailFee","0.00");
			}
			model.addAttribute("allCount",allCount);
		}
		return "/wx/accountDetail";
	}
	@RequestMapping(value = "/accountDetailListbak")
	public String accountDetailListbak(HttpServletRequest request, HttpServletResponse response, Model model){
		Map<String, Object> resultMap = new HashMap<String, Object>();//返回数据
		try{
			ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			if(adminUser==null){
				resultMap.put("status", "fail");
				resultMap.put("msg", "请先登录!");
			}else{
				HPayOrder param = new HPayOrder();
				if(adminUser.getRoleId().toString().equals(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID)){
					param.setRoleType(1);
					param.setRole_id(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
					param.setOneAgentOpenId(adminUser.getOneAgentOpenId());
				}else if(adminUser.getRoleId().toString().equals(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID)){
					param.setRoleType(2);
					param.setRole_id(Integer.valueOf(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID));
					param.setTwoAgentOpenID(adminUser.getTwoAgentOpenId());
				}else if(adminUser.getRoleId().toString().equals(FileUploadConstants.SERVICER_MANAGE_ROLEID)){
					param.setRoleType(3);
					param.setRole_id(Integer.valueOf(FileUploadConstants.SERVICER_MANAGE_ROLEID));
					param.setServicerId(adminUser.getAdminId());
				}
				Integer queryType = RequestHandler.getInteger(request, "queryType");
				param.setQueryType(queryType);
				String startTime = RequestHandler.getString(request, "startTime");
				String endTime = RequestHandler.getString(request, "endTime");
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if(StringUtils.isNotBlank(startTime)){
					param.setStartTime(sf.parse(startTime + " 00:00:00"));
				}
				if(StringUtils.isNotBlank(endTime)){
					param.setEndTime(sf.parse(endTime + " 23:59:59"));
				}
				String cname = RequestHandler.getString(request, "cname");
				param.setC_name(cname);
				Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
				Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
				
				int from = RequestHandler.getFromByPage(pageNo, rowCount);
				param.setRowStart(from);
				param.setRowCount(rowCount);
				// 分页结束
				// 排序
				String sortColumn = RequestHandler.getString(request, "sortColumn");
				param.setSortColumn(" o_id desc ");
				param.setPay_status("1");
				param.setTick_off_status("1");
				int allCount = hpayorderService.getHPayOrderListCount_ForWX(param);
				List<HPayOrder> detailList = null;
				if ( allCount > 0 )
				{
					detailList = hpayorderService.getHPayOrderList_ForWX(param);
					//处理代理名称
					for(int i = 0;i<detailList.size();i++){
						//处理日期
//						if(StringUtils.isNotBlank(detailList.get(i).getPay_time())){
//							detailList.get(i).setOrderTime(detailList.get(i).getPay_time().split(" ")[0]);
//						}
						detailList.get(i).setAgentName("我");
						if(queryType!=null){
							if(queryType==1){//自己
								detailList.get(i).setAgentName("我");
							}else if(queryType==2){//代理
								if(adminUser.getRoleId().toString().equals(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID)){
									detailList.get(i).setAgentName(detailList.get(i).getTwoAgentName()+"代理");
								}else if(adminUser.getRoleId().toString().equals(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID)){
									detailList.get(i).setAgentName("我");
								}else if(adminUser.getRoleId().toString().equals(FileUploadConstants.SERVICER_MANAGE_ROLEID)){
									detailList.get(i).setAgentName("我");
								}
							}
						}else{//全部
							if(adminUser.getRoleId().toString().equals(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID)){
								if(StringUtils.isNotBlank(detailList.get(i).getTwoAgentName())){
									detailList.get(i).setAgentName(detailList.get(i).getTwoAgentName()+"代理");
								}else{
									detailList.get(i).setAgentName("我");
								}
							}else if(adminUser.getRoleId().toString().equals(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID)){
								detailList.get(i).setAgentName("我");
							}else if(adminUser.getRoleId().toString().equals(FileUploadConstants.SERVICER_MANAGE_ROLEID)){
								detailList.get(i).setAgentName("我");
							}
						}
					}
					HPayOrder param1 = hpayorderService.getAllDetailFee(param);
					//获取账户
					HUserAccount account = new HUserAccount();
					account.setOpenId(adminUser.getOpenId());
					if(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID.equals(adminUser.getRoleId().toString())){//一级代理 
						account.setOneAgentOpenId(adminUser.getOneAgentOpenId());
					}else if(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID.equals(adminUser.getRoleId().toString())){//二级代理
						account.setTwoAgentOpenId(adminUser.getTwoAgentOpenId());
					}else if(FileUploadConstants.SERVICER_MANAGE_ROLEID.equals(adminUser.getRoleId().toString())){//服务人员
						account.setServicerId(adminUser.getAdminId());
					}	
					account.setRole_id(adminUser.getRoleId());
					account = huseraccountService.getHUserAccount(account);
					DecimalFormat df = new DecimalFormat("0.00");
					if(detailList!=null&&detailList.size()>0){
						detailList.get(0).setAllDetailFee(param1.getAllOrderFee());
						param1 = hpayorderService.getAllRateMoney(param);
						detailList.get(0).setAllMoney(param1.getAllRateFee()==null?"0.00":param1.getAllRateFee().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
						detailList.get(0).setAllCount(allCount);
						for(int i = 0;i<detailList.size();i++){
							if(adminUser.getRoleId().toString().equals(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID)){
								detailList.get(i).setRateMoney(detailList.get(i).getOneRate()!=null?detailList.get(i).getOneRate().setScale(2, BigDecimal.ROUND_HALF_UP).toString():"0");
							}else if(adminUser.getRoleId().toString().equals(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID)){
								detailList.get(i).setRateMoney(detailList.get(i).getOneRate()!=null?detailList.get(i).getTwoRate().setScale(2, BigDecimal.ROUND_HALF_UP).toString():"0");
							}else if(adminUser.getRoleId().toString().equals(FileUploadConstants.SERVICER_MANAGE_ROLEID)){
								detailList.get(i).setRateMoney(detailList.get(i).getOneRate()!=null?detailList.get(i).getServerRate().setScale(2, BigDecimal.ROUND_HALF_UP).toString():"0");
							}
							detailList.get(i).setPayTimeStr(detailList.get(i).getPay_time()!=null?sf.format(detailList.get(i).getPay_time()):"");
						}
					}
				} else
				{
					detailList = new ArrayList<HPayOrder>();
				}
				writeSuccessMsg("ok", detailList, response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/accountDetailList")
	public String accountDetailList(HttpServletRequest request, HttpServletResponse response, Model model){
		Map<String, Object> resultMap = new HashMap<String, Object>();//返回数据
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		try{
			ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			if(adminUser==null){
				resultMap.put("status", "fail");
				resultMap.put("msg", "请先登录!");
			}else{
				HUserAccountDetail param = new HUserAccountDetail();
				
				String startTime = RequestHandler.getString(request, "startTime");
				String endTime = RequestHandler.getString(request, "endTime");
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				if(StringUtils.isNotBlank(startTime)){
					param.setStartTime1(sf.parse(startTime + " 00:00:00"));
				}
				
				if(adminUser.getRoleId().toString().equals(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID)){
					param.setRoleType(1);
					param.setRole_id(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
					param.setOneAgentOpenId(adminUser.getOneAgentOpenId());
				}else if(adminUser.getRoleId().toString().equals(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID)){
					param.setRoleType(2);
					param.setRole_id(Integer.valueOf(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID));
					param.setTwoAgentOpenID(adminUser.getTwoAgentOpenId());
				}else if(adminUser.getRoleId().toString().equals(FileUploadConstants.SERVICER_MANAGE_ROLEID)){
					param.setRoleType(3);
					param.setRole_id(Integer.valueOf(FileUploadConstants.SERVICER_MANAGE_ROLEID));
					param.setServicerId(adminUser.getAdminId());
				}
				
				if(StringUtils.isNotBlank(endTime)){
					param.setEndTime1(sf.parse(endTime + " 23:59:59"));
				}
				Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
				Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
				
				
				//获取账户
				HUserAccount account = new HUserAccount();
				account.setOpenId(adminUser.getOpenId());
				if(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID.equals(adminUser.getRoleId().toString())){//一级代理 
					account.setOneAgentOpenId(adminUser.getOneAgentOpenId());
				}else if(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID.equals(adminUser.getRoleId().toString())){//二级代理
					account.setTwoAgentOpenId(adminUser.getTwoAgentOpenId());
				}else if(FileUploadConstants.SERVICER_MANAGE_ROLEID.equals(adminUser.getRoleId().toString())){//服务人员
					account.setServicerId(adminUser.getAdminId());
				}	
				account.setRole_id(adminUser.getRoleId());
				account = huseraccountService.getHUserAccount(account);
				
				
				int from = RequestHandler.getFromByPage(pageNo, rowCount);
				param.setRowStart(from);
				param.setRowCount(rowCount);
				param.setUserAccountId(account.getId());
				// 分页结束
				// 排序
				param.setSortColumn(" a.id desc ");
				
				int allCount = huseraccountdetailService.getHUserAccountDetailListCount(param);
				HUserAccountDetail de1 = huseraccountdetailService.getAllRateMoney(param);
				HUserAccountDetail de2 = huseraccountdetailService.getAllDetailFee1(param);
//				if(allCount>0){
					List<HUserAccountDetail> detailList = huseraccountdetailService.getHUserAccountDetailListWx(param);
					if(detailList!=null&&detailList.size()>0){
						for(HUserAccountDetail acc:detailList){
							JSONObject jsons = new JSONObject();
							jsons.put("id", acc.getId());
							jsons.put("o_id", acc.getOrderId());
							jsons.put("allCount", allCount);
							if(de2.getAllOrderFee()!=null){
//								BigDecimal subFee22222 = new BigDecimal(de2.getAllOrderFee());
								jsons.put("allDetailFee", de2.getAllOrderFee().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
							}else{
								jsons.put("allDetailFee", "0.00");
							}
							if(de1.getAllRateFee()!=null){
								jsons.put("allMoney", de1.getAllRateFee().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
							}else{
								jsons.put("allMoney", "0.00");
							}
							jsons.put("type", acc.getType());
							jsons.put("pay_style", acc.getPay_style());
							jsons.put("payTimeStr", sf1.format(acc.getCreateTime()));
							if(acc.getTotalFee()!=null){
								jsons.put("rateMoney", acc.getTotalFee().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
							}else{
								jsons.put("rateMoney", "0.00");
							}
							array.add(jsons);
						}
						json.put("message", "ok");
						json.put("items", array);
					}else{
						json.put("message", "end");
					}
			}
			response.setContentType("text/html;charset=utf-8");
	        response.setHeader("Cache-Control","no-cache");
	        response.getWriter().write(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/accDetail/3/{id}", method = RequestMethod.GET)
	public String accDetail3(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		HRegGuliSend hRegGuliSend = new HRegGuliSend();
		hRegGuliSend.setId(id);
		hRegGuliSend = hreggulisendService.getHRegGuliSend(hRegGuliSend);
		if(hRegGuliSend!=null){
			String contact_phone = hRegGuliSend.getContact_phone();
			String ammeter = hRegGuliSend.getAmmeter();
			if(StringUtils.isNotBlank(contact_phone)){
				int len = contact_phone.length();
				String x = "";
				if(len>5){
					for(int i=0;i<len-5;i++){
						x = x + "*";
					}
					String str = contact_phone.substring(0, 1)+x+contact_phone.substring(len-4, len);
					hRegGuliSend.setContact_phone(str);
				}else{
					x = "***";
				}
			}
			if(StringUtils.isNotBlank(ammeter)){
				int len = ammeter.length();
				String x = "";
				if(len>4){
					for(int i=0;i<len-4;i++){
						x = x + "*";
					}
					String str = ammeter.substring(0, 1)+x+ammeter.substring(len-3, len);
					hRegGuliSend.setAmmeter(str);
				}else{
					x = "***";
				}
			}
		}
		model.addAttribute("hRegGuliSend", hRegGuliSend);
		return "/wx/accDetail2";
	}
	@RequestMapping(value = "/accDetail/2/{id}", method = RequestMethod.GET)
	public String accDetail2(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		Integer detailId = -1;
//		HPayOrder order = new HPayOrder();
//		order.setO_id(id);
//		order = hpayorderService.getHPayOrder(order);
//		if(order!=null){
//			HPresentApply apply = new HPresentApply();
//			apply.setId(order.getApply_id());
//			apply = hpresentApplyservice.getHPresentApply(apply);
//			if(apply != null){
//				detailId = apply.getAccountDetailId();
//			}
//		}
		HUserAccountDetail param = new HUserAccountDetail();
		param.setId(id);
		param = huseraccountdetailService.getHUserAccountDetail(param);
		if(param!=null){
			//查询申请时间
			HPresentApply hPresentApply = new HPresentApply();
			hPresentApply.setId(Integer.valueOf(param.getRemark1()));
			hPresentApply = hpresentapplyService.getHPresentApply(hPresentApply);
			if(hPresentApply!=null){
				param.setTick_off_time(hPresentApply.getCreateTime());
			}
			param.setTotalFee(param.getTotalFee().setScale(2, BigDecimal.ROUND_HALF_UP));
			param.setRealFee(param.getRealFee().setScale(2, BigDecimal.ROUND_HALF_UP));
			param.setTaxRate((param.getTotalFee().subtract(param.getRealFee())).setScale(2, BigDecimal.ROUND_HALF_UP));
		}
		model.addAttribute("param1", param);
		return "/wx/accDetail";
	}
	@RequestMapping(value = "/accDetail/1/{id}", method = RequestMethod.GET)
	public String accDetail1(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
		HPayOrder param = new HPayOrder();
		param.setO_id(id);
		param = hpayorderService.getHPayOrder(param);
		List<HSubCompany> hsubList = new ArrayList<HSubCompany>();
		if(param!=null){
			//总钱币
			if(adminUser.getRoleId().toString().equals(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID)){
				model.addAttribute("allMony", param.getOneRate()!=null?param.getOneRate().setScale(2, BigDecimal.ROUND_HALF_UP).toString():"0");
			}else if(adminUser.getRoleId().toString().equals(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID)){
				model.addAttribute("allMony", param.getOneRate()!=null?param.getTwoRate().setScale(2, BigDecimal.ROUND_HALF_UP).toString():"0");
			}else if(adminUser.getRoleId().toString().equals(FileUploadConstants.SERVICER_MANAGE_ROLEID)){
				model.addAttribute("allMony", param.getOneRate()!=null?param.getServerRate().setScale(2, BigDecimal.ROUND_HALF_UP).toString():"0");
			}
			//获取订单
			if(param!=null){
				model.addAttribute("order", param);
				//获取客户信息
				if(param.getC_id()!=null){
					HCompany company = new HCompany();
					company.setId(param.getC_id());
					company = hcompanyService.getHCompany(company);
					if(company!=null){
						model.addAttribute("company", company);
						String agentName = "我";
						if(adminUser.getRoleId().toString().equals(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID)){
							model.addAttribute("roleType", 1);
							if(StringUtils.isNotBlank(company.getTwoAgentName())){
								agentName = company.getTwoAgentName();
							}
						}else if(adminUser.getRoleId().toString().equals(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID)){
							model.addAttribute("roleType", 2);
						}else if(adminUser.getRoleId().toString().equals(FileUploadConstants.SERVICER_MANAGE_ROLEID)){
							model.addAttribute("roleType", 3);
							if(StringUtils.isNotBlank(company.getTwoAgentName())){
								agentName = company.getTwoAgentName();
							}else if(StringUtils.isNotBlank(company.getOneAgentName())){
								agentName = company.getOneAgentName();
							}
						}
						model.addAttribute("agentName", agentName);
					}
				}
				
				//获取订单详情列表
				HSubOrder subparam = new HSubOrder();
				subparam.setO_id(param.getO_no());
				List<HSubOrder> subOrderList = hsuborderService.getHSubOrderBaseList(subparam);
				Set<String> ammeterIds = new HashSet<String>();
				Set<Integer> subComIds = new HashSet<Integer>();
				if(subOrderList!=null&&subOrderList.size()>0){
					for(HSubOrder tmp : subOrderList){
						ammeterIds.add(tmp.getElectric());
						subComIds.add(tmp.getSub_id());
					}
				}
				Integer[] ids = {};
				ids = subComIds.toArray(ids);
				HSubCompany subcParam = new HSubCompany();
				subcParam.setIds(ids);
				hsubList = hsubCompanyService.getHSubCompanyBaseList(subcParam);
				if(hsubList!=null&&hsubList.size()>0){
					//获取分组下子订单
					for(HSubCompany tmp : hsubList){
						subparam.setSub_id(tmp.getS_id());
						tmp.setSubOrderList(hsuborderService.getHSubOrderBaseList(subparam));
					}
				}
			}
		}
		model.addAttribute("subCom", hsubList);
		return "/wx/accDetail1";
	}

	/**
	 * 转向个人提现信息完善页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toPostalInfo")
	public String toPostalInfo(HttpServletRequest request, HttpServletResponse response, Model model){
		String url = "/wx/postalInfo";
		ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
		//判断代理的类型 个人还是机构
		HAgentTwo hAgentTwo = new HAgentTwo();
		hAgentTwo.setOpenId(adminUser.getTwoAgentOpenId());
		hAgentTwo = hagenttwoService.getHAgentTwo(hAgentTwo);
		if(hAgentTwo.getStyle()==1){//公司
			url = "/wx/postalInfoPC";
		}
		model.addAttribute("openId", adminUser.getOpenId());
		model.addAttribute("hAgentTwo", hAgentTwo);
		return url;
	}
	
	/**
	 * 转向个人提现信息完善页面下一步
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toPostalInfoNext")
	public String toPostalInfoNext(HttpServletRequest request, HttpServletResponse response, Model model){
		String realName = RequestHandler.getString(request, "realName");
		String wocard_no = RequestHandler.getString(request, "wocard_no");
		Integer id = RequestHandler.getInteger(request, "id");
//		ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
		try{
			//验证身份证是否存在
			HAgentTwo hAgentTwo = new HAgentTwo();
			hAgentTwo.setCard_no(wocard_no);
			hAgentTwo.setId(id);
			boolean b = hagenttwoService.checkCard(hAgentTwo);
			if(b){
				hAgentTwo.setName(realName);
				hagenttwoService.updateHAgentTwo(hAgentTwo);
//				ManageAdminUser adminUser1 = new ManageAdminUser();
//				adminUser1.setAdminId(adminUser.getAdminId());
//				adminUser1 = manageadminuserService.getAdminUserByLogin(adminUser1);
//				if(adminUser1!=null){
//					adminUser1.setRealName(realName);
//					manageadminuserService.updateManageAdminUser(adminUser1);
//				}
				writeSuccessMsg("ok",null , response);
			}else{
				writeSuccessMsg("no",null , response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 转向个人提现信息完善页面下一步
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toPostalInfoSecond")
	public String toPostalInfoSecond(HttpServletRequest request, HttpServletResponse response, Model model){
		Integer id = RequestHandler.getInteger(request, "id");
//		ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
//		try{
//			//验证身份证是否存在
			HAgentTwo hAgentTwo = new HAgentTwo();
			hAgentTwo.setId(id);
			hAgentTwo = hagenttwoService.getHAgentTwo(hAgentTwo);
//			if(hAgentTwo!=null){
//				hagenttwoService.updateHAgentTwo(hAgentTwo);
//				ManageAdminUser adminUser1 = new ManageAdminUser();
//				adminUser1.setAdminId(adminUser.getAdminId());
//				adminUser1 = manageadminuserService.getAdminUserByLogin(adminUser1);
//				if(adminUser1!=null){
//					adminUser1.setRealName(realName);
//					manageadminuserService.unBindWx(adminUser1);
//				}
//				writeSuccessMsg("ok",null , response);
//			}else{
//				writeSuccessMsg("no",null , response);
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}
		model.addAttribute("id", id);
		model.addAttribute("hAgentTwo", hAgentTwo);
		return "/wx/postalInfoSecond";
	}
	/**
	 * 转向个人提现信息完善页面下一步
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toPostalInfo3rd")
	public String toPostalInfo3rd(HttpServletRequest request, HttpServletResponse response, Model model){
		Integer id = RequestHandler.getInteger(request, "id");
//		ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
//		try{
//			//验证身份证是否存在
		HAgentTwo hAgentTwo = new HAgentTwo();
		hAgentTwo.setId(id);
		hAgentTwo = hagenttwoService.getHAgentTwo(hAgentTwo);
		
		ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
		
		HBank hBank = new HBank();
		hBank.setId(hAgentTwo.getBankId());
		hBank = hbankService.getHBank(hBank);
//			if(hAgentTwo!=null){
//				hagenttwoService.updateHAgentTwo(hAgentTwo);
				ManageAdminUser adminUser1 = new ManageAdminUser();
				adminUser1.setAdminId(adminUser.getAdminId());
				adminUser1 = manageadminuserService.getAdminUserByLogin(adminUser1);
//				if(adminUser1!=null){
//					adminUser1.setRealName(realName);
//					manageadminuserService.unBindWx(adminUser1);
//				}
//				writeSuccessMsg("ok",null , response);
//			}else{
//				writeSuccessMsg("no",null , response);
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}
	    String bank_number = hAgentTwo.getBank_number();
	    if(bank_number.length()>5){
	    	bank_number = bank_number.substring(bank_number.length()-4, bank_number.length());
	    }
		model.addAttribute("bank_number", bank_number);
		model.addAttribute("id", id);
		model.addAttribute("hAgentTwo", hAgentTwo);
		model.addAttribute("hBank", hBank);
		model.addAttribute("adminUser1", adminUser1);
		return "/wx/postalInfo3rd";
	}
	
	@RequestMapping(value = "/toPostalInfo3rdPC")
	public String toPostalInfo3rdPC(HttpServletRequest request, HttpServletResponse response, Model model){
		Integer id = RequestHandler.getInteger(request, "id");
//		ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
//		try{
//			//验证身份证是否存在
		HAgentTwo hAgentTwo = new HAgentTwo();
		hAgentTwo.setId(id);
		hAgentTwo = hagenttwoService.getHAgentTwo(hAgentTwo);
		
		ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
		
		HBank hBank = new HBank();
		hBank.setId(hAgentTwo.getBankId());
		hBank = hbankService.getHBank(hBank);
//			if(hAgentTwo!=null){
//				hagenttwoService.updateHAgentTwo(hAgentTwo);
		ManageAdminUser adminUser1 = new ManageAdminUser();
		adminUser1.setAdminId(adminUser.getAdminId());
		adminUser1 = manageadminuserService.getAdminUserByLogin(adminUser1);
//				if(adminUser1!=null){
//					adminUser1.setRealName(realName);
//					manageadminuserService.unBindWx(adminUser1);
//				}
//				writeSuccessMsg("ok",null , response);
//			}else{
//				writeSuccessMsg("no",null , response);
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}
		model.addAttribute("id", id);
		model.addAttribute("hAgentTwo", hAgentTwo);
		model.addAttribute("hBank", hBank);
		model.addAttribute("adminUser1", adminUser1);
		return "/wx/postalInfo3rdPC";
	}
	
	@RequestMapping(value = "/payCheck")
	public String payCheck(HttpServletRequest request, HttpServletResponse response, Model model){
		String openId = RequestHandler.getString(request, "openId");
		String url = "/wx/h5/bind";
		//判断是否绑定
		if(weiXinService.isPayCheck(openId,request)==1){
			url = "/wx/h5/index";
		}else if(weiXinService.isPayCheck(openId,request)==-2){
//			url = "/public/405";
		}else{
			url = "/wx/h5/bind";
		}
		model.addAttribute("openId", openId);
		return url;
	}
	@RequestMapping(value = "/payDetail")
	public String payDetail(HttpServletRequest request, HttpServletResponse response, Model model){
		String orderNum = RequestHandler.getString(request, "orderNum");
		String proxyId = RequestHandler.getString(request, "proxyId");
		if(StringUtils.isNotBlank(orderNum)&&StringUtils.isNotBlank(proxyId)){
			Map<Integer,JSONObject> remap = new HashMap<Integer, JSONObject>();
			HSubOrder sub = new HSubOrder();
			sub.setO_id(orderNum);
			List<HSubOrder> list = hsuborderService.getHSubOrderBaseList(sub);
			if(list!=null&&list.size()>0){
				for(HSubOrder or : list){
					JSONObject subInfo = new JSONObject();//分组信息json
					if(remap.get(or.getSub_id())!=null){
						subInfo = remap.get(or.getSub_id());
					}
					//分组信息
					if(subInfo.getJSONObject("baseInfo")==null){
						//获取分组信息
						HSubCompany subcom = new HSubCompany();
						subcom.setS_id(or.getSub_id());
						subcom = hsubCompanyService.getHSubCompany(subcom);
						if(subcom!=null){
							JSONObject baseInfo = new JSONObject();
							baseInfo.put("name", subcom.getSub_name());
							baseInfo.put("contact_name", subcom.getConsignee());
							baseInfo.put("phone", subcom.getConsignee_phone());
							baseInfo.put("address", subcom.getConsignee_address());
							subInfo.put("baseInfo", baseInfo);
						}
					}
					JSONArray amList = new JSONArray();
					if(subInfo.getJSONArray("ammeters")!=null){
						amList = subInfo.getJSONArray("ammeters");//电表信息list	
					}
					JSONObject ammeter = new JSONObject();
					ammeter.put("num", or.getElectric());
					ammeter.put("fee", or.getAmount());
					//获取电表名称
					HAmmeterInfo info = new HAmmeterInfo();
					info.setAmmeter_no(or.getElectric());
					info.setDelete_state(1);
					info.setPay_status("1");
					info.setC_id(or.getC_id());
					info.setS_id(or.getSub_id());
					info = hAmmeterInfoService.getHAmmeterInfo(info);
					if(info==null){
						continue;
					}else{
						ammeter.put("name", info.getAmmeter_name());
					}
					amList.add(ammeter);
					subInfo.put("ammeters",amList);
					remap.put(or.getSub_id(), subInfo);
				}
			}
			model.addAttribute("subList", remap);
			model.addAttribute("orderNum", orderNum);
		}
		model.addAttribute("proxyId", proxyId);
		return "/wx/phonePay";
	}
	
}
