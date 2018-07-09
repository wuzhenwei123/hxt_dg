package com.wx.service;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.base.utils.DateFormatToString;
import com.base.utils.FileUploadConstants;
import com.base.utils.SessionName;
import com.base.utils.https.HttpUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.hxt.hAddress.dao.HAddressDAO;
import com.hxt.hAgent.dao.HAgentDAO;
import com.hxt.hAgent.model.HAgent;
import com.hxt.hAgentTwo.dao.HAgentTwoDAO;
import com.hxt.hAgentTwo.model.HAgentTwo;
import com.hxt.hCompany.model.HCompany;
import com.hxt.hCompany.service.HCompanyService;
import com.hxt.hEwm.dao.HEwmDAO;
import com.hxt.hEwm.model.HEwm;
import com.hxt.hLoginLog.model.HLoginLog;
import com.hxt.hLoginLog.service.HLoginLogService;
import com.hxt.hProxyMessage.model.HProxyMessage;
import com.hxt.hProxyMessage.service.HProxyMessageService;
import com.hxt.hUserAccount.service.HUserAccountService;
import com.sys.adminUserRole.dao.AdminUserRoleDAO;
import com.sys.adminUserRole.model.AdminUserRole;
import com.sys.manageAdminUser.dao.ManageAdminUserDAO;
import com.sys.manageAdminUser.model.ManageAdminUser;
import com.wx.utils.Constants;
import com.wx.utils.EncryptUtil;
import com.wx.utils.FileUtils;
import com.wx.utils.MD5;
import com.wx.utils.WxApiURL;
import com.wx.utils.https.HttpRequest;

@Component
@Transactional
public class WeiXinService {
	
	Logger log = Logger.getLogger(WeiXinService.class);
	
	@Resource(name = "manageAdminUserDao")
    private ManageAdminUserDAO manageAdminUserDAO;
	@Resource(name = "adminUserRoleDao")
    private AdminUserRoleDAO adminUserRoleDAO;
	@Resource(name = "hAddressDao")
    private HAddressDAO hAddressDAO;
	@Resource(name = "hEwmDao")
    private HEwmDAO hEwmDAO;
	@Resource(name = "hAgentDao")
    private HAgentDAO hAgentDAO;
	@Resource(name = "hAgentTwoDao")
    private HAgentTwoDAO hAgentTwoDAO;
	@Autowired
	private HUserAccountService huseraccountService = null;
	@Autowired
	private HLoginLogService hloginlogService = null;
	@Autowired
	private HProxyMessageService hproxymessageService = null;
	@Autowired
	private HCompanyService hcompanyService = null;
	
	// http客户端
//	public static DefaultHttpClient httpclient;

	public static Map<String, JSONObject> TOKEN_MAP = null;
	public static Map<String, JSONObject> TICKET_MAP = null;
	public static Integer LOGINNAME_COUNT = null;

//	static {
//		httpclient = new DefaultHttpClient();
//		httpclient = (DefaultHttpClient) HttpClientConnectionManager.getSSLInstance(httpclient); // 接受任何证书的浏览器客户端
//	}

	/**
	 * 获取accessToken
	 */
	public String getAccessToken(String appid, String secret) throws Exception {
		
		List<String> token = this.readToken();
		
		if (token!=null&&token.size()>0) {
			JSONObject tokenJson = JSONObject.parseObject(token.get(0));
			if (tokenJson != null) {
				int expires_in = (Integer) tokenJson.getInteger("expires_in");// token时间
				Long time = (new Date()).getTime() / 1000;
				if (time.intValue() < expires_in) {
					return tokenJson.getString("access_token");
				}
			}
		} 
//		HttpGet get = HttpClientConnectionManager.getGetMethod(WxApiURL.ACCESSTOKEN_URL + "&appid=" + appid + "&secret=" + secret);
//		HttpResponse response = httpclient.execute(get);
//		String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
		
//		String jsonStr = HttpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/token", "grant_type=client_credential&appid=" + appid + "&secret=" + secret);
		
		String jsonStr = HttpUtils.httpsRequest("https://api.weixin.qq.com/cgi-bin/token", "GET", "grant_type=client_credential&appid=" + appid + "&secret=" + secret);
		
		JSONObject object = JSON.parseObject(jsonStr);
		if (object.getInteger("expires_in") != null) {
			Long time = (new Date()).getTime() / 1000;
			int expires_in = time.intValue() + 7000;
			object.put("expires_in", expires_in);
			this.writeToken(object.toJSONString());
//			TOKEN_MAP.put(appid, object);
		}
		return object.getString("access_token");
	}
	/**
	 * 获取accessToken
	 */
	public String getAccessToken1(String appid, String secret) throws Exception {
		if (TOKEN_MAP == null) {
			TOKEN_MAP = new HashMap<String, JSONObject>();
		} else {
			JSONObject tokenJson = TOKEN_MAP.get(appid);
			if (tokenJson != null) {
				int expires_in = (Integer) tokenJson.getInteger("expires_in");// token时间
				Long time = (new Date()).getTime() / 1000;
				if (time.intValue() < expires_in) {
					return tokenJson.getString("access_token");
				}
			}
		}
//		HttpGet get = HttpClientConnectionManager.getGetMethod(WxApiURL.ACCESSTOKEN_URL + "&appid=" + appid + "&secret=" + secret);
//		HttpResponse response = httpclient.execute(get);
//		String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
		
//		String jsonStr = HttpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/token", "grant_type=client_credential&appid=" + appid + "&secret=" + secret);
		
		String jsonStr = HttpUtils.httpsRequest("https://api.weixin.qq.com/cgi-bin/token", "GET", "grant_type=client_credential&appid=" + appid + "&secret=" + secret);
		
		JSONObject object = JSON.parseObject(jsonStr);
		if (object.getInteger("expires_in") != null) {
			Long time = (new Date()).getTime() / 1000;
			int expires_in = time.intValue() + 7000;
			object.put("expires_in", expires_in);
			TOKEN_MAP.put(appid, object);
		}
		return object.getString("access_token");
	}

	/**
	 * 获取jsapi_ticket
	 * 
	 * @param accessToken
	 * @return
	 */
	public String getTicket(String appid, String secret) throws Exception {
		if (TICKET_MAP == null) {
			TICKET_MAP = new HashMap<String, JSONObject>();
		} else {
			JSONObject ticketJson = TICKET_MAP.get(appid);
			if (ticketJson != null) {
				int expires_in = (Integer) ticketJson.getInteger("expires_in");// token时间
				Long time = (new Date()).getTime() / 1000;
				if (time.intValue() < expires_in) {
					return ticketJson.getString("ticket");
				}
			}
		}
		String accessToken = this.getAccessToken(appid, secret);
		System.out.println("accessToken========================="+accessToken);
//		HttpGet get = HttpClientConnectionManager.getGetMethod(WxApiURL.TICKET_URL + "?access_token=" + accessToken + "&type=jsapi");
//		HttpResponse response = httpclient.execute(get);
//		String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
		
//		String jsonStr = HttpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/ticket/getticket", "access_token=" + accessToken + "&type=jsapi");
		
		String jsonStr = HttpUtils.httpsRequest("https://api.weixin.qq.com/cgi-bin/ticket/getticket", "GET", "access_token=" + accessToken + "&type=jsapi");
		
		
		System.out.println("jsonStr========================="+jsonStr);
		JSONObject object = JSON.parseObject(jsonStr);
		if (object.getInteger("expires_in") != null) {
			Long time = (new Date()).getTime() / 1000;
			int expires_in = time.intValue() + 7000;
			object.put("expires_in", expires_in);
			TICKET_MAP.put(appid, object);
		}
		return object.getString("ticket");
	}

	/**
	 * 获取请求js的凭据
	 * 
	 * @param appid
	 * @param secret
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getJsticket(String appid, String secret, String url) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		String noncestr = UUID.randomUUID().toString();
		String timestamp = Long.toString(System.currentTimeMillis() / 1000);
		String jsapi_ticket = this.getTicket(appid, secret);
		map.put("noncestr", noncestr);
		map.put("timestamp", timestamp);
		map.put("jsapi_ticket", jsapi_ticket);
		map.put("url", url);
		System.out.println("jsapi_ticket==============="+jsapi_ticket);
		String str = this.paramLinks(map);
		MessageDigest crypt = MessageDigest.getInstance("SHA-1");
		crypt.reset();
		crypt.update(str.getBytes("UTF-8"));
		String signature = byteToHex(crypt.digest());
		map.put("signature", signature);
		return map;
	}

	/**
	 * 获取aouth2.0网页认证返回码
	 */
	public String getAccessCode(String APPID, String SECRET, String code) throws Exception {
//		HttpGet get = HttpClientConnectionManager.getGetMethod(WxApiURL.GET_AOUTH_URL + "?appid=" + APPID + "&secret=" + SECRET + "&code=" + code + "&grant_type=authorization_code");
//		HttpResponse response = httpclient.execute(get);
//		String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
		
		String jsonStr = HttpUtils.httpsRequest(WxApiURL.GET_AOUTH_URL, "GET", "appid=" + APPID + "&secret=" + SECRET + "&code=" + code + "&grant_type=authorization_code");
		
//		String jsonStr = HttpRequest.sendGet(WxApiURL.GET_AOUTH_URL, "appid=" + APPID + "&secret=" + SECRET + "&code=" + code + "&grant_type=authorization_code");
		return jsonStr;
	}

	/**
	 * 获取用户基本信息
	 */

	public String getFromUserMess(String accessToken, String openID) throws Exception {
//		HttpGet get = HttpClientConnectionManager.getGetMethod(WxApiURL.GET_USERINFO_URL + "?access_token=" + accessToken + "&openid=" + openID + "&lang=zh_CN");
//		HttpResponse response = httpclient.execute(get);
//		String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
		// JSONObject json = JSON.parseObject(jsonStr);
//		String jsonStr = HttpRequest.sendGet(WxApiURL.GET_USERINFO_URL, "access_token=" + accessToken + "&openid=" + openID + "&lang=zh_CN");
		
		String jsonStr = HttpUtils.httpsRequest(WxApiURL.GET_USERINFO_URL, "GET", "access_token=" + accessToken + "&openid=" + openID + "&lang=zh_CN");
		
		return jsonStr;
	}

	/**
	 * 发送客服消息
	 */

	public void sendMessage(String params, String accessToken) throws Exception {
//		HttpPost httpost = HttpClientConnectionManager.getPostMethod(WxApiURL.SEND_MESSAGE + "?access_token=" + accessToken);
//		httpost.setEntity(new StringEntity(params, "UTF-8"));
//		HttpResponse response = httpclient.execute(httpost);
//		String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
		
		String jsonStr = HttpRequest.sendGet(WxApiURL.SEND_MESSAGE, "access_token=" + accessToken);
		System.out.println(JSON.parseObject(jsonStr).get("errmsg"));
	}

	/**
	 * 参数拼接
	 * */
	public String paramLinks(Map<String, String> params) {

		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);

		String prestr = "";

		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);

			if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
				prestr = prestr + key + "=" + value;
			} else {
				prestr = prestr + key + "=" + value + "&";
			}
		}

		return prestr;
	}

	private String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	
	/**
	 * 绑定微信
	 * @param carSn
	 * @param userId
	 */
	public int bindWeiXin(String appId,String appSecret,String openId,HttpServletRequest request) {
		int flag = 0;
		try{
			//判断是否已经绑定
			ManageAdminUser manageAdminUser = new ManageAdminUser();
			manageAdminUser.setOpenId(openId);
			manageAdminUser.setRoleId(Integer.valueOf(FileUploadConstants.COMPANY_ROLE_ID));
			int count = manageAdminUserDAO.getManageAdminUserListCount(manageAdminUser);
			if(count==0){//创建账户
				String accessToken = this.getAccessToken(appId,appSecret);
				String jsonInfo = this.getFromUserMess(accessToken, openId);
				JSONObject json = JSONObject.parseObject(jsonInfo);
				ManageAdminUser manageAdminUser1 = new ManageAdminUser();
				manageAdminUser1.setOpenId(openId);
//				String nick = EmojiFilter.filterEmoji(json.getString("nickname"));
//				String nick = new String(json.getString("nickname").getBytes(),"utf-8");
				String nick = getChinese(json.getString("nickname"));
				manageAdminUser1.setNickName(nick);
				manageAdminUser1.setRealName(nick);
				manageAdminUser1.setState(1);
				manageAdminUser1.setHeadImg(json.getString("headimgurl"));
				manageAdminUser1.setCreateTime(new Date());
				manageAdminUser1.setPasswd(new MD5().getMD5ofStr("111111"));
				//微信注册获取登陆名
				String loginName = this.getWxLoginNum();
				manageAdminUser1.setAdminName(loginName);
				String agentCode = loginName.replace("wx", "DL");
				manageAdminUser1.setAgentCode(agentCode);
				Integer adminId = manageAdminUserDAO.insertManageAdminUser(manageAdminUser1);
				if (adminId != null) {
					AdminUserRole adminUserRole = new AdminUserRole();
					adminUserRole.setAdminId(adminId);
					AdminUserRole adminUserRole2 = adminUserRoleDAO.getAdminUserRole(adminUserRole);
					adminUserRole.setRoleId(Integer.valueOf(FileUploadConstants.COMPANY_ROLE_ID));
					if (adminUserRole2 == null) {
						adminUserRoleDAO.insertAdminUserRole(adminUserRole);
					} else {
						adminUserRole2.setRoleId(Integer.valueOf(FileUploadConstants.COMPANY_ROLE_ID));
						adminUserRoleDAO.updateAdminUserRole(adminUserRole2);
					}
				}
				
				
				
				ManageAdminUser adminUser = manageAdminUserDAO.getAdminUserByID(adminId);
				
				
				HLoginLog hLoginLog = new HLoginLog();
				hLoginLog.setAdminName(adminUser.getAdminName());
				hLoginLog.setLoginIp(this.getIpAddr(request));
				hLoginLog.setLoginTIme(new Date());
				hLoginLog.setDeviceType(1);
				hloginlogService.insertHLoginLog(hLoginLog);
				
				request.getSession().setAttribute(SessionName.ADMIN_USER_NAME, adminUser.getAdminName());
				request.getSession().setAttribute(SessionName.ADMIN_USER_ID, adminUser.getAdminId());
				request.getSession().setAttribute(SessionName.ADMIN_USER, adminUser);
				if (adminUser.getLastLogin() != null && !"".equals(adminUser.getLastLogin())) {
					request.getSession().setAttribute(SessionName.ADMIN_USER_LAST_LOGIN, DateFormatToString.getStringByDate(adminUser.getLastLogin()));
				}
			}else{
				ManageAdminUser user = manageAdminUserDAO.getManageAdminUser(manageAdminUser);
				if(user.getState()==-1){
					String accessToken = this.getAccessToken(appId,appSecret);
					String jsonInfo = this.getFromUserMess(accessToken, openId);
					JSONObject json = JSONObject.parseObject(jsonInfo);
					user.setState(1);
					String nick = getChinese(json.getString("nickname"));
//					String nick = EmojiFilter.filterEmoji(json.getString("nickname"));
//					String nick = new String(json.getString("nickname").getBytes(),"utf-8");
					user.setNickName(nick);
					user.setRealName(nick);
					manageAdminUserDAO.updateManageAdminUser(user);
				}
				manageAdminUser.setOpenId(openId);
				manageAdminUser.setState(1);
				ManageAdminUser adminUser = manageAdminUserDAO.getManageAdminUser(manageAdminUser);
				
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
			}
			flag = 1;
		}catch(Exception e){
			flag = 0;
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 绑定微信
	 * @param carSn
	 * @param userId
	 */
	public int bindWeiXin11(String appId,String appSecret,String openId,HttpServletRequest request) {
		int flag = 0;
		try{
			//判断是否已经绑定
			ManageAdminUser manageAdminUser = new ManageAdminUser();
			manageAdminUser.setOpenId(openId);
			manageAdminUser.setRoleId(Integer.valueOf(FileUploadConstants.COMPANY_ROLE_ID));
			int count = manageAdminUserDAO.getManageAdminUserListCount(manageAdminUser);
			if(count==0){//创建账户
				String accessToken = this.getAccessToken(appId,appSecret);
				String jsonInfo = this.getFromUserMess(accessToken, openId);
				JSONObject json = JSONObject.parseObject(jsonInfo);
				ManageAdminUser manageAdminUser1 = new ManageAdminUser();
				manageAdminUser1.setOpenId(openId);
//				String nick = EmojiFilter.filterEmoji(json.getString("nickname"));
//				String nick = new String(json.getString("nickname").getBytes(),"utf-8");
				String nick = getChinese(json.getString("nickname"));
				manageAdminUser1.setNickName(nick);
				manageAdminUser1.setRealName(nick);
				manageAdminUser1.setState(1);
				manageAdminUser1.setHeadImg(json.getString("headimgurl"));
				manageAdminUser1.setCreateTime(new Date());
				manageAdminUser1.setPasswd(new MD5().getMD5ofStr("111111"));
				//微信注册获取登陆名
				String loginName = this.getWxLoginNum();
				manageAdminUser1.setAdminName(loginName);
				String agentCode = loginName.replace("wx", "DL");
				manageAdminUser1.setAgentCode(agentCode);
				Integer adminId = manageAdminUserDAO.insertManageAdminUser(manageAdminUser1);
				if (adminId != null) {
					AdminUserRole adminUserRole = new AdminUserRole();
					adminUserRole.setAdminId(adminId);
					AdminUserRole adminUserRole2 = adminUserRoleDAO.getAdminUserRole(adminUserRole);
					adminUserRole.setRoleId(Integer.valueOf(FileUploadConstants.COMPANY_ROLE_ID));
					if (adminUserRole2 == null) {
						adminUserRoleDAO.insertAdminUserRole(adminUserRole);
					} else {
						adminUserRole2.setRoleId(Integer.valueOf(FileUploadConstants.COMPANY_ROLE_ID));
						adminUserRoleDAO.updateAdminUserRole(adminUserRole2);
					}
				}
				ManageAdminUser adminUser = manageAdminUserDAO.getAdminUserByID(adminId);
				
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
			}else{
				ManageAdminUser user = manageAdminUserDAO.getManageAdminUser(manageAdminUser);
				if(user.getState()==-1){
					String accessToken = this.getAccessToken(appId,appSecret);
					String jsonInfo = this.getFromUserMess(accessToken, openId);
					JSONObject json = JSONObject.parseObject(jsonInfo);
					user.setState(1);
					String nick = getChinese(json.getString("nickname"));
//					String nick = EmojiFilter.filterEmoji(json.getString("nickname"));
//					String nick = new String(json.getString("nickname").getBytes(),"utf-8");
					user.setNickName(nick);
					user.setRealName(nick);
					manageAdminUserDAO.updateManageAdminUser(user);
				}
				manageAdminUser.setOpenId(openId);
				manageAdminUser.setState(1);
				ManageAdminUser adminUser = manageAdminUserDAO.getManageAdminUser(manageAdminUser);
				request.getSession().setAttribute(SessionName.ADMIN_USER_NAME, adminUser.getAdminName());
				request.getSession().setAttribute(SessionName.ADMIN_USER_ID, adminUser.getAdminId());
				request.getSession().setAttribute(SessionName.ADMIN_USER, adminUser);
				if (adminUser.getLastLogin() != null && !"".equals(adminUser.getLastLogin())) {
					request.getSession().setAttribute(SessionName.ADMIN_USER_LAST_LOGIN, DateFormatToString.getStringByDate(adminUser.getLastLogin()));
				}
			}
			flag = 1;
		}catch(Exception e){
			flag = 0;
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 判断微信是否绑定
	 */
	public boolean isBind(String openId,HttpServletRequest request,String roleId) {
		boolean b = false;
		try{
			ManageAdminUser manageAdminUser = new ManageAdminUser();
			manageAdminUser.setOpenId(openId);
			manageAdminUser.setState(1);
			manageAdminUser.setRoleId(Integer.valueOf(roleId));
			ManageAdminUser user = manageAdminUserDAO.getAdminUserByLogin(manageAdminUser);
			System.out.println("========isband============");
			if(user!=null&&user.getAdminId()>0){
				System.out.println("========isband============"+user.getAdminId());
				user.setLastLogin(new Date());
				manageAdminUserDAO.updateManageAdminUser(user);
				user = manageAdminUserDAO.getManageAdminUser1(manageAdminUser);
				request.getSession().removeAttribute(SessionName.ADMIN_USER_NAME);
				request.getSession().removeAttribute(SessionName.ADMIN_USER_ID);
				request.getSession().removeAttribute(SessionName.ADMIN_USER);
				
				HLoginLog hLoginLog = new HLoginLog();
				hLoginLog.setAdminName(user.getAdminName());
				hLoginLog.setLoginIp(this.getIpAddr(request));
				hLoginLog.setLoginTIme(new Date());
				hLoginLog.setDeviceType(0);
				hloginlogService.insertHLoginLog(hLoginLog);
				
				request.getSession().setAttribute(SessionName.ADMIN_USER_NAME, user.getAdminName());
				request.getSession().setAttribute(SessionName.ADMIN_USER_ID, user.getAdminId());
				request.getSession().setAttribute(SessionName.ADMIN_USER, user);
				b = true;
			}else{
				b = false;
				System.out.println("========isband============"+b);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return b;
	}
	
	/**
     * 获取UUID
     * @return
     */
    public String getUUID(){
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        // 去掉"-"符号
        String temp = str.replace("-","");
        return temp;
    }
    
    /**
     * 获取微信注册登陆名
     * @return
     */
    public synchronized String getWxLoginNum(){
    	String loginName = null;
    	try{
    		if(LOGINNAME_COUNT==null){
    			//获取当前最大的序号
    			ManageAdminUser use = new ManageAdminUser();
    			use.setRoleId(Integer.valueOf(FileUploadConstants.COMPANY_ROLE_ID));
    			ManageAdminUser user = manageAdminUserDAO.getWxLoginNum(use);
    			if(user!=null&&StringUtils.isNotBlank(user.getAdminName())){
    				String loginNameStr = user.getAdminName();
    				loginNameStr = loginNameStr.substring(3, loginNameStr.length());
    				Integer num = Integer.valueOf(loginNameStr);
    				String zero = null;
        			for(int i=0;i<(8-(num+"").length());i++){
        				if(zero==null){
        					zero = "0";
        				}else{
        					zero = zero + "0";
        				}
        			}
        			LOGINNAME_COUNT = num + 1;
        			loginName = "wx1" + zero + LOGINNAME_COUNT;
    			}else{
    				LOGINNAME_COUNT = 1;
        			loginName = "wx1" + "0000000" + LOGINNAME_COUNT;
    			}
    		}else{
    			String loginNameStr = LOGINNAME_COUNT+"";
    			String zero = null;
    			for(int i=0;i<(8-loginNameStr.length());i++){
    				if(zero==null){
    					zero = "0";
    				}else{
    					zero = zero + "0";
    				}
    			}
    			LOGINNAME_COUNT = LOGINNAME_COUNT + 1;
    			loginName = "wx1" + zero + LOGINNAME_COUNT;
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return loginName;
    }
    
    /**
     * 获取访问者IP
     * 
     * 在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。
     * 
     * 本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)，
     * 如果还不存在则调用Request .getRemoteAddr()。
     * 
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
	 * 取消关注，解除绑定
	 * @param openId
	 * @return
	 */
	public int unBindWx(String openId){
		try{
			ManageAdminUser manageAdminUser = new ManageAdminUser();
			manageAdminUser.setOpenId(openId);
			manageAdminUser.setRoleId(Integer.valueOf(FileUploadConstants.COMPANY_ROLE_ID));
			ManageAdminUser user = manageAdminUserDAO.getManageAdminUser(manageAdminUser);
			user.setCancelTime(new Date());
			manageAdminUserDAO.unBindWx(user);
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	
	/** 生成关注二维码(临时)
	 * @param appid
	 * @param secret
	 */
	public Map<String,String> getEWM(String appid, String secret,String scene_id){
		String imgurl = null;
		Map<String,String> map = new HashMap<String,String>();
		try{
			String accessToken = getAccessToken(appid, secret);
			String json = "{\"expire_seconds\": 259200,\"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\":\""+scene_id+"\"}}}";
			
			URL url = new URL("https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+accessToken);
			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
			byte[] buf = json.getBytes();

			httpConn.setRequestProperty("Content-Length", String.valueOf(buf.length));
			httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
			httpConn.setRequestMethod("POST");
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			OutputStream out = httpConn.getOutputStream();
			out.write(buf);
			out.close();

			byte[] datas = readInputStream(httpConn.getInputStream());
			String jsonStr = new String(datas,"utf-8");
			
			JSONObject object = JSON.parseObject(jsonStr);
			String ticket = object.getString("ticket");
			if(StringUtils.isNotBlank(ticket)){
				imgurl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+URLEncoder.encode(ticket);
				map.put("ticket", ticket);
				map.put("imgurl", imgurl);
				System.out.println("================================="+imgurl);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 从输入流中读取数据
	 * 
	 * @param inStream
	 * @return
	 * @throws Exception
	 */
	public byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		byte[] data = outStream.toByteArray();// 网页的二进制数据
		outStream.close();
		inStream.close();
		return data;
	}
	
	/**
	 * 发送模板信息
	 * @param appid
	 * @param secret
	 * @return
	 */
	public String sendTempltMsg(String appid, String secret,String content){
		try{
			String accessToken = getAccessToken(appid, secret);
			String toOPENID = "ogJyvuC4eTh_jSkW-JrFgpGZeKBY";
			String template_id = "Y1tUY-szw7FGbzOBeiSarO2YDjheyjechMWZor_PHp4";
			String json = "{\"touser\": \""+toOPENID+"\","
					+ "\"template_id\": \""+template_id+"\","
					+ "\"url\": \"http://weixin.qq.com/download\","
					+ "\"topcolor\": \"#FF0000\","
					+ "\"data\": {\"first\": {\"value\": \"您好，欢迎使用！\",\"color\": \"#173177\"},"
					+ "\"product\": {\"value\": \"推荐抵用券\",\"color\": \"#173177\"},"
					+ "\"price\": {\"value\": \"2\",\"color\": \"#173177\"},"
					+ "\"time\": {\"value\": \"2015-09-01\",\"color\": \"#173177\"},"
					+ "\"remark\": {\"value\": \"感谢\",\"color\": \"#173177\"}}}";
			
			
			String jsonStr = HttpUtils.httpsRequest("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken, "POST", content);
			
//			URL url = new URL("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken);
//			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
//			byte[] buf = content.getBytes();
//
//			httpConn.setRequestProperty("Content-Length", String.valueOf(buf.length));
//			httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
//			httpConn.setRequestMethod("POST");
//			httpConn.setDoOutput(true);
//			httpConn.setDoInput(true);
//			OutputStream out = httpConn.getOutputStream();
//			out.write(buf);
//			out.close();
//
//			byte[] datas = readInputStream(httpConn.getInputStream());
//			String jsonStr = new String(datas,"utf-8");
			System.out.println(jsonStr);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 生成海报
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public String generateOutput(String appid, String secret,Map<String,String> map,String basepath) throws Exception {
		String media_id = null;
		try{
			
			String sceneId = map.get("sceneId");
			String url = FileUploadConstants.URL_PATH + "/weixin/toshare1?scene_id="+sceneId;
			
			String uuid = UUID.randomUUID().toString().replace("-", "");	// 惟一ID
			String saveFile = sceneId + ".jpg";		// 图片名称
			Long times = System.currentTimeMillis();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); 
			String datedir = sdf.format(new Date(times));
			String realPath = FileUploadConstants.UPLOAD_FILE_ROOT + File.separator + datedir;
			if(FileUploadConstants.mkdirs(realPath)){
				String filePath = FileUploadConstants.parseToPath(basepath+ File.separator + "upload" + File.separator + "ewm"  + File.separator + saveFile);
				FileUtils.downPic(filePath, map.get("imgUrl"));
				File file = new File(filePath);
				if(file.exists()){
					//生成海报
					String haibaoname = uuid + ".jpg";
					String haibaoPath = FileUploadConstants.parseToPath(realPath +File.separator+haibaoname);
					FileUtils.capt(basepath+File.separator + "capt" +File.separator+"CutyCapt.exe", url, haibaoPath, 360, 6000);
					File fileH = new File(haibaoPath);
					if(fileH.exists()){
						String accessToken = getAccessToken(appid, secret);
						String sendUrl = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token="+accessToken+"&type=image";
						String jsonStr = this.send(sendUrl, haibaoPath);
						JSONObject object = JSON.parseObject(jsonStr);
						media_id = object.getString("media_id");
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return media_id;
    }  
	
	/**
	 * 生成二位吗
	 * @return
	 */
	public Map<String,String> createEWM(String openId){
		Map<String,String> map = new HashMap<String,String>();
		try{
			ManageAdminUser manageAdminUser = new ManageAdminUser();
			manageAdminUser.setOpenId(openId);
			manageAdminUser.setRoleId(Integer.valueOf(FileUploadConstants.COMPANY_ROLE_ID));
			ManageAdminUser adminUser = manageAdminUserDAO.getManageAdminUser(manageAdminUser);
			String sceneId = "";
			for(int i=0;i<32;i++){
				sceneId += (new Random().nextInt(9)+1);
			}
			Map<String,String> maperm = this.getEWM(FileUploadConstants.APPID, FileUploadConstants.APPSECRET, sceneId);
			//保存数据库
			HEwm hEwm = new HEwm();
			hEwm.setOpenId(openId);
			hEwm.setImgUrl(maperm.get("imgurl"));
			hEwm.setCreateTime(new Date());
			hEwm.setScene_id(maperm.get("ticket"));
			hEwm.setStyle(1);
			int flag = hEwmDAO.insertHEwm(hEwm);
			if(flag>0){
				map.put("id", flag+"");
				map.put("imgUrl", maperm.get("imgurl"));
				map.put("sceneId",  maperm.get("ticket"));
				map.put("nickname",  adminUser.getNickName()!=null?adminUser.getNickName():"");
			}
			System.out.println("11111111111111111============"+maperm.get("imgurl"));
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	
	
	/**
     * 文件上传到微信服务器
     * @param fileType 文件类型
     * @param filePath 文件路径
     * @return JSONObject
     * @throws Exception
     */
    public String send(String sendUrl, String filePath) throws Exception {  
        String result = null;  
        File file = new File(filePath);  
        if (!file.exists() || !file.isFile()) {  
            throw new IOException("文件不存在");  
        }  
        /** 
        * 第一部分 
        */  
        URL urlObj = new URL(sendUrl);  
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();  
        con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式  
        con.setDoInput(true);  
        con.setDoOutput(true);  
        con.setUseCaches(false); // post方式不能使用缓存  
        // 设置请求头信息  
        con.setRequestProperty("Connection", "Keep-Alive");  
        con.setRequestProperty("Charset", "UTF-8");  
        // 设置边界  
        String BOUNDARY = "----------" + System.currentTimeMillis();  
        con.setRequestProperty("Content-Type", "multipart/form-data; boundary="+ BOUNDARY);  
        // 请求正文信息  
        // 第一部分：  
        StringBuilder sb = new StringBuilder();  
        sb.append("--"); // 必须多两道线  
        sb.append(BOUNDARY);  
        sb.append("\r\n");  
        sb.append("Content-Disposition: form-data;name=\"file\";filename=\""+ file.getName() + "\"\r\n");  
        sb.append("Content-Type:application/octet-stream\r\n\r\n");  
        byte[] head = sb.toString().getBytes("utf-8");  
        // 获得输出流  
        OutputStream out = new DataOutputStream(con.getOutputStream());  
        // 输出表头  
        out.write(head);  
        // 文件正文部分  
        // 把文件已流文件的方式 推入到url中  
        DataInputStream in = new DataInputStream(new FileInputStream(file));  
        int bytes = 0;  
        byte[] bufferOut = new byte[1024];  
        while ((bytes = in.read(bufferOut)) != -1) {  
        out.write(bufferOut, 0, bytes);  
        }  
        in.close();  
        // 结尾部分  
        byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线  
        out.write(foot);  
        out.flush();  
        out.close();  
        StringBuffer buffer = new StringBuffer();  
        BufferedReader reader = null;  
        try {  
        // 定义BufferedReader输入流来读取URL的响应  
        reader = new BufferedReader(new InputStreamReader(con.getInputStream()));  
        String line = null;  
        while ((line = reader.readLine()) != null) {  
        //System.out.println(line);  
        buffer.append(line);  
        }  
        if(result==null){  
        result = buffer.toString();  
        }  
        } catch (IOException e) {  
        System.out.println("发送POST请求出现异常！" + e);  
        e.printStackTrace();  
        throw new IOException("数据读取异常");  
        } finally {  
        if(reader!=null){  
        reader.close();  
        }  
        }
        System.out.println("===========result============"+result);
        return result;  
    }
   
    /**
     * 发送客服消息
     */
    
    public void postMess(String appid, String secret, String toOPENID,String media_id) throws Exception{
    	String json = "{\"touser\": \""+toOPENID+"\","
				+ "\"msgtype\": \"image\","
				+ "\"image\": {\"media_id\":\""+media_id+"\"}}";
    	try{
    		String accessToken = getAccessToken(appid, secret);
    		URL url = new URL("https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+accessToken);
    		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
    		byte[] buf = json.getBytes();

    		httpConn.setRequestProperty("Content-Length", String.valueOf(buf.length));
    		httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
    		httpConn.setRequestMethod("POST");
    		httpConn.setDoOutput(true);
    		httpConn.setDoInput(true);
    		OutputStream out = httpConn.getOutputStream();
    		out.write(buf);
    		out.close();
    		byte[] datas = readInputStream(httpConn.getInputStream());
			String jsonStr = new String(datas,"utf-8");
			System.out.println(jsonStr);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    /**
     * 生成关注二维码(永久)
     * @param appid   公众号ＩＤ
     * @param secret  公众号密码
     * @param scene_str  场景编码
     * @return imgurl  二维码地址
     */
	public Map<String,String> getEWMYj(String appid, String secret,String scene_str){
		Map<String,String> map = new HashMap<String,String>();
		try{
			String accessToken = getAccessToken(appid, secret);
			String json = "{\"action_name\": \"QR_LIMIT_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\":\""+scene_str+"\"}}}";
			
			URL url = new URL("https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+accessToken);
			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
			byte[] buf = json.getBytes();

			httpConn.setRequestProperty("Content-Length", String.valueOf(buf.length));
			httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
			httpConn.setRequestMethod("POST");
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			OutputStream out = httpConn.getOutputStream();
			out.write(buf);
			out.close();

			byte[] datas = readInputStream(httpConn.getInputStream());
			String jsonStr = new String(datas,"utf-8");
			
			JSONObject object = JSON.parseObject(jsonStr);
			String ticket = object.getString("ticket");
			if(StringUtils.isNotBlank(ticket)){
				String imgurl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+URLEncoder.encode(ticket);
				map.put("imgurl", imgurl);
				map.put("ticket", ticket);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	
	
	/**
	 * 绑定微信
	 * @param carSn
	 * @param userId
	 */
	public int bindWeiXin1(String appId,String appSecret,String openId,HttpServletRequest request) {
		int flag = 0;
		try{
			//判断是否已经绑定
			ManageAdminUser manageAdminUser = new ManageAdminUser();
			manageAdminUser.setOpenId(openId);
			manageAdminUser.setRoleId(Integer.valueOf(FileUploadConstants.COMPANY_ROLE_ID));
			int count = manageAdminUserDAO.getManageAdminUserListCount(manageAdminUser);
			if(count==0){//创建账户
				String accessToken = this.getAccessToken(appId,appSecret);
				String jsonInfo = this.getFromUserMess(accessToken, openId);
				JSONObject json = JSONObject.parseObject(jsonInfo);
				ManageAdminUser manageAdminUser1 = new ManageAdminUser();
				manageAdminUser1.setOpenId(openId);
				
//				String nick = EmojiFilter.filterEmoji(json.getString("nickname"));
//				String nick = new String(json.getString("nickname").getBytes(),"utf-8");
				String nick = xxx(json.getString("nickname"));
				manageAdminUser1.setNickName(nick);
				manageAdminUser1.setRealName(nick);
				manageAdminUser1.setState(1);
				manageAdminUser1.setHeadImg(json.getString("headimgurl"));
				manageAdminUser1.setCreateTime(new Date());
				manageAdminUser1.setPasswd(new MD5().getMD5ofStr("111111"));
				//微信注册获取登陆名
				String loginName = this.getWxLoginNum();
				manageAdminUser1.setAdminName(loginName);
				String agentCode = loginName.replace("wx", "DL");
				manageAdminUser1.setAgentCode(agentCode);
				Integer adminId = manageAdminUserDAO.insertManageAdminUser(manageAdminUser1);
				if (adminId != null) {
					AdminUserRole adminUserRole = new AdminUserRole();
					adminUserRole.setAdminId(adminId);
					AdminUserRole adminUserRole2 = adminUserRoleDAO.getAdminUserRole(adminUserRole);
					adminUserRole.setRoleId(Integer.valueOf(FileUploadConstants.COMPANY_ROLE_ID));
					if (adminUserRole2 == null) {
						adminUserRoleDAO.insertAdminUserRole(adminUserRole);
					} else {
						adminUserRole2.setRoleId(Integer.valueOf(FileUploadConstants.COMPANY_ROLE_ID));
						adminUserRoleDAO.updateAdminUserRole(adminUserRole2);
					}
				}
			}else{
				ManageAdminUser user = manageAdminUserDAO.getManageAdminUser(manageAdminUser);
				if(user.getState()==-1){
					String accessToken = this.getAccessToken(appId,appSecret);
					String jsonInfo = this.getFromUserMess(accessToken, openId);
					JSONObject json = JSONObject.parseObject(jsonInfo);
					user.setState(1);
//					String nick = json.getString("nickname");
//					String nick = new String(json.getString("nickname").getBytes(),"utf-8");
					String nick = getChinese(json.getString("nickname"));
					user.setNickName(nick);
					user.setRealName(nick);
					manageAdminUserDAO.updateManageAdminUser(user);
				}
			}
			flag = 1;
		}catch(Exception e){
			flag = 0;
			e.printStackTrace();
		}
		return flag;
	}
	
	public String xxx(String source){
		int len = source.length();
		StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < len; i++) {
	      char codePoint = source.charAt(i);
	      if(isChinese(codePoint)){
	    	  sb.append(codePoint);
	      }
	    }
	    return sb.toString();
	}
	
	public String getChinese(String str) {
		if (str == null || "".equals(str.trim())) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		char[] aa = str.toCharArray();
		for (int i = 0; i < aa.length; i++) {
			char c = aa[i];
			if (Pattern.matches("^[a-zA-Z0-9\u4E00-\u9FA5]+$", String.valueOf(c))) {
				sb.append(c);
			}
		}
		return sb.toString();
	}
	
	
	public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        System.out.println("================"+ub);  
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.BASIC_LATIN) {
            return true;
        }
        return false;
    }
	
	public static void main(String args[]){
		String str = "大家1blueis123你h好h";
//		System.out.println(getChinese(str));
	}
	
	
	public String getBaiDuLocationXY(String x, String y) {
//        String url = "http://api.map.baidu.com/geoconv/v1/";
//        String jsonStr = HttpRequest.sendGet(url, "coords="+x+","+y+"&from=1&to=5&ak=kqbMjjFSG8p3vvAcUNNB8558y5hyRXZD");
//        JSONObject object = JSON.parseObject(jsonStr);
//        if(0==object.getInteger("status")){
		
		String adcode = null;
		try{
			String url1 = "http://api.map.baidu.com/geocoder/v2/";
        	String jsonStr = HttpRequest.sendGet(url1, "ak=kqbMjjFSG8p3vvAcUNNB8558y5hyRXZD&callback=renderReverse&location="+x+","+y+"&output=json&pois=0");
        	if(StringUtils.isNotBlank(jsonStr)){
        		jsonStr = jsonStr.replaceAll("renderReverse&&renderReverse", "").replaceAll("\\(", "").replaceAll("\\)", "");
        	}
        	JSONObject object = JSON.parseObject(jsonStr);
            String jsonStr1 = object.getString("result");
            JSONObject object1 = JSON.parseObject(jsonStr1);
            String jsonStr2 = object1.getString("addressComponent");
            JSONObject object2 = JSON.parseObject(jsonStr2);
            adcode = object2.getString("adcode");
		}catch(Exception e){
			e.printStackTrace();
		}
        	
		return adcode;
    }
	
	public int isPayCheck(String openId,HttpServletRequest request){
		//判断是否已经绑定
		ManageAdminUser manageAdminUser = new ManageAdminUser();
		manageAdminUser.setOpenId(openId);
		manageAdminUser.setRoleId(Integer.valueOf(FileUploadConstants.PROXY_ROLEID));
		int count = manageAdminUserDAO.getManageAdminUserListCount(manageAdminUser);
		if(count>0){
			if(count==1){
				manageAdminUser = manageAdminUserDAO.getManageAdminUser1(manageAdminUser);
				if(manageAdminUser.getState()==1){
					// 判断机构是否正常
					HProxyMessage hProxyMessage = new HProxyMessage();
					hProxyMessage.setUserId(manageAdminUser.getAdminId());
					hProxyMessage = hproxymessageService.getHProxyMessage(hProxyMessage);
					if (hProxyMessage!=null&&hProxyMessage.getState() == 1&& (hProxyMessage.getCheckState() == 1 || hProxyMessage.getCheckState() == 7|| hProxyMessage.getCheckState() == 8|| hProxyMessage.getCheckState() == 6)) {
						HCompany hCompany = new HCompany();
						hCompany.setId(hProxyMessage.getcId());
						hCompany = hcompanyService.getHCompany(hCompany);
						if(hCompany!=null&&hCompany.getStatus()==1&&hCompany.getVerify_status()==1){
							//验证公司登陆账户
							ManageAdminUser adminUser2 = new ManageAdminUser();
							adminUser2.setAdminId(hCompany.getUser_id());
							adminUser2 = manageAdminUserDAO.getManageAdminUser1(adminUser2);
							if(adminUser2!=null&&adminUser2.getState()==1){
								request.getSession().setAttribute(SessionName.ADMIN_USER_NAME, manageAdminUser.getAdminName());
								request.getSession().setAttribute(SessionName.ADMIN_USER_ID, manageAdminUser.getAdminId());
								request.getSession().setAttribute(SessionName.ADMIN_USER, manageAdminUser);
								return 1;
							}else{
								return -2;
							}
						}else{
							return -2;
						}
					}else{
						return -2;
					}
				}else{
					return -2;
				}
			}else{
				return -2;
			}
		}else{
			return -1;
		}
	}
	
	public List<String> readToken() throws Exception {
		List<String> list = new ArrayList<String>();
		try {
			String encoding = "utf-8"; // 字符编码(可解决中文乱码问题 )
			File file = new File(FileUploadConstants.TOKEN_FILE);
			if (file.isFile() && file.exists()) {
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTXT = null;
				while ((lineTXT = bufferedReader.readLine()) != null) {
					list.add(lineTXT.toString().trim());
				}
				bufferedReader.close();
				read.close();
			} else {
				System.out.println("找不到指定的文件！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	

	public void writeToken(String str) {
		System.out.println("--------开始--------");
		File file = new File(FileUploadConstants.TOKEN_FILE);
		try {
			BufferedWriter output = new BufferedWriter(new FileWriter(file));
			output.write(str);
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("--------结束 --------");
	}
}
