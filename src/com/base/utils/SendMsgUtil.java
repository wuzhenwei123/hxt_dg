package com.base.utils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;









import org.apache.http.client.ClientProtocolException;

import com.hxt.hMessageLog.model.HMessageLog;
import com.hxt.hMessageLog.service.HMessageLogService;


public class SendMsgUtil {
	
	static HMessageLogService hMessageLogService = (HMessageLogService) ApplicationContextHolder.getBean("hMessageLogService");
	
	/** 安全校验Key **/
	private static final String KEY = "axt@)!$";
	private static EncryptUtil md5 = new EncryptUtil();
	/** 存放手机号-验证码缓存 **/
	public static Map<String, String> CODEMAP = new HashMap<String, String>();
	/** 存放手机号-时间缓存 **/
	public static Map<String, Date> TIMEMAP = new HashMap<String, Date>();
	public static HttpClientDemo clientDemo = new HttpClientDemo(); 
	private static String userName = "BJLK00343";
	private static String passwd = "849600@";
	
	public static void main(String[] args) throws ClientProtocolException, Exception {
		String randomCode = SendMsgUtil.getRandomCode(6);
		String content = "【恒信通】欢迎您注册家长会平台，注册码：" + randomCode+"【恒信通】";
		int s = SendMsgUtil.sendMsg("18810619832",content);
		System.out.println(s);
	}

	public static boolean isNull(Object param) {
		if (param == null || "".equals(param))
			return true;
		else
			return false;
	}
	/**
	 * 发短信
	 * @param mobile
	 * @param content
	 * @return
	 */
	public static int sendMsg(String mobile,String content) throws Exception{
		int re =0 ;
		try {
			String param = "CorpID="+userName+"&Pwd="+passwd+"&Mobile=" + mobile + "&Content=" + URLEncoder.encode(content, "GB2312");
			String serverURL = "http://yzm.mb345.com/ws/BatchSend2.aspx" + "?" + param;
			String s = clientDemo.post(serverURL, null, null, null);
			re = Integer.valueOf(s);
			if(re>0){
				HMessageLog hMessageLog = new HMessageLog();
				hMessageLog.setContent(content);
				hMessageLog.setCreateTime(new Date());
				hMessageLog.setPhone(mobile);
//				hMessageLog.setIp(this.getIpAddr(request));
				hMessageLogService.insertHMessageLog(hMessageLog);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return re;
	}
	/**
	 * 返回随机数
	 * 
	 * @param bit
	 *            位数
	 * @return
	 */
	public static String getRandomCode(int bit) {
		Random ran = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bit; i++) {
			int ranNum = ran.nextInt(10);
			sb.append(ranNum);
		}
		return sb.toString();
	}

	/**
	 * 安全校验
	 * 
	 * @param param
	 *            参数
	 * @param md5Str
	 *            md5字符串
	 * @return
	 */
	public static boolean verify(String param, String md5Str) {

		String str = KEY + param;
		String localMd5 = md5.md5(str);
		if (localMd5.equals(md5Str.toUpperCase())) {
			return true;
		} else {
			return false;
		}
	}
}
