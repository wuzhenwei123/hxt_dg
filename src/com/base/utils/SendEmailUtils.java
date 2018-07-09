package com.base.utils;

import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;



public class SendEmailUtils {
	/** 邮件随机数 */
	public static Random random = new Random();
	//邮箱服务器
	public static String MAILHOST="";
	public static String MAILEMAIL="";
	public static String MAILUSER="";
	public static String MAILPASSWORD="";
	public static String MAILADDRESS="";
	
	private static Properties prop = null;
	static{
		String path = "/com/conf/mail.properties";
		
		InputStream in = SendEmailUtils.class.getResourceAsStream(path);
		if ( in != null ){
			prop = new Properties();
			try{
				prop.load(in);
				MAILHOST = (String) prop.get("mail.smtp.host");
				MAILEMAIL = (String) prop.get("mail.smtp.email");
				MAILUSER=(String)prop.get("mail.smtp.user");
				MAILPASSWORD=(String)prop.get("mail.smtp.password");
			} catch (Exception e){
				throw new RuntimeException(e);
			}
		}
	}
	public static String getPropValue(String key){
		String path = "/com/conf/mail.properties";
		String val = null;
		InputStream in = SendEmailUtils.class.getResourceAsStream(path);
		if ( in != null ){
			prop = new Properties();
			try{
				prop.load(in);
				val = prop.getProperty(key);
			} catch (Exception e){
				e.printStackTrace();
				System.out.println("=====================get property file error!");
			}
		}
		return val;
	}
	
	
	private static Logger logger = Logger.getLogger(SendEmailUtils.class);
	//发送邮件相关
	/**
	 * 生成密码（6位随机数字）
	 * @return String
	 * @author HuKaiXuan 2014-5-17 上午11:16:18
	 */
	public static String generatePassword(){
		String number = "";
		for(int i = 0; i < 6; i ++) {
			number += random.nextInt(10);
 		}
		return number;
	}
	
	public static void main(String[] args) {
//		String propValue = new SendEmailUtils().getPropValue("mail.smtp.host");
//		System.out.println(propValue);
		Random random = new Random();
		String password = random.nextInt() + "";
//		StringBuffer content = new StringBuffer();
//		content.append("您好！用户 Mr.Men ***** 在radio的密码为：" + password + "<br/>(该邮件为系统邮件，请勿回复，登录后请及时修改您的密码。)");

		String mailHost = "smtp.126.com";// pu.getValue("mail.send.host");
		String mailEmail = "nihao5372@126.com";// pu.getValue("mail.send.email");
		String mailUser = "nihao5372";// pu.getValue("mail.send.user");
		String mailPassword = "w7788521";// pu.getValue("mail.send.password");
		
		StringBuffer sb = new StringBuffer();
//		sb.append("<table width='560' border='0' cellspacing='0' style='overflow: hidden' cellpadding='0'><tr>");
//		sb.append("<td valign='top'><table width='90%' border='0' align='center' cellpadding='0' cellspacing='0'>");
//		sb.append("<tr>");
//		sb.append("<td height='40'><p>财务部您好，截止到  <fmt:formatDate value='${admin_user.lastLogin}' pattern='yyyy年MM月dd日'></fmt:formatDate>，我单位的电费欠费共计：<span id='paysum1' class='hz01'>0.00</span>  元<br />");
//		sb.append("请登录下述网址完成付款（超过本日后付款可能产生新的滞纳金）：</p>");
//		sb.append("<p>付款网址：<span id='checkUrl'></span><br />");
//		sb.append("付款验证码：<span id='checkNo'></span><br />");
//		sb.append("验证码有效期截止到：<fmt:formatDate value='${admin_user.lastLogin}' pattern='yyyy-MM-dd '></fmt:formatDate>  21:00:00</p></td>");
//		sb.append("</tr></table></td></tr></table>");
		sb.append("测试");
		try {
			new SendEmailUtils().send("yichangjiaoyi@sohu.com", mailEmail, "测试", sb.toString(), mailHost, mailUser, mailPassword, true);
			System.out.println("Mail Send Success");
		} catch (AddressException e) {
			System.out.println("AddressException-------" + e.getMessage());
			e.printStackTrace();
		} catch (MessagingException e) {
			System.out.println("MessagingException-------" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/***************************************************************************
	 * 邮件发送，带用户名和密码验证，测试通过
	 * 
	 * @param to
	 * @param from
	 * @param title
	 * @param content
	 * @param smtpServer
	 * @param user
	 * @param password
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public void send(String to, String from, String title, String content,
			String smtpServer, String user, String password, boolean isHTML)
			throws AddressException, MessagingException {

		Properties props = new Properties();
		Authenticator auth = new MailAuthenticator(user, password);
		Session sendMailSession;
		Transport transport;
		props.put("mail.smtp.host", smtpServer);
		props.put("mail.smtp.auth", "true");
		sendMailSession = Session.getInstance(props, auth);
		Message newMessage = new MimeMessage(sendMailSession);
		newMessage.setFrom(new InternetAddress(from));
		newMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(
				to));
		newMessage.setSubject(title);
		newMessage.setSentDate(new Date());
		if (isHTML) {
			newMessage.setContent(content, "text/html;charset=UTF-8");
		} else {
			newMessage.setText(content);
		}
		transport = sendMailSession.getTransport("smtp");
		Transport.send(newMessage);
		transport.close();
		if (logger.isDebugEnabled()) {
			logger.debug("---------- Mail Send Success ----------");
		}
	}
	
	
}
class MailAuthenticator extends Authenticator {
	private String user;
	private String password;

	public MailAuthenticator() {

	}

	public MailAuthenticator(String user, String password) {
		this.user = user;
		this.password = password;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(user, password);
	}

	public String getMailServer(String email) {
		String mailUrl = "/";
		if (null != email) {
			mailUrl = email.toLowerCase();
			String email_array = email.substring(email.indexOf("@"));
			if ("163.com".equals(email_array)) {// 163邮箱
				mailUrl = "mail.163.com";
			} else if ("vip.163.com".equals(email_array)) {// 163vip邮箱
				mailUrl = "vip.163.com";
			} else if ("sina.com".equals(email_array)) {// 新浪邮箱
				mailUrl = "mail.sina.com.cn";
			} else if ("sina.cn".equals(email_array)) {// 新浪邮箱
				mailUrl = "mail.sina.com.cn";
			} else if ("vip.sina.com".equals(email_array)) {// 新浪vip邮箱
				mailUrl = "vip.sina.com.cn";
			} else if ("2008.sina.com".equals(email_array)) {// 新浪2008邮箱
				mailUrl = "mail.2008.sina.com.cn";
			} else if ("sohu.com".equals(email_array)) {// 搜狐邮箱
				mailUrl = "mail.sohu.com";
			} else if ("vip.sohu.com".equals(email_array)) {// 搜狐vip邮箱
				mailUrl = "vip.sohu.com";
			} else if ("tom.com".equals(email_array)) {// Tom邮箱
				mailUrl = "mail.tom.com";
			} else if ("vip.sina.com".equals(email_array)) {// Tom vip 邮箱
				mailUrl = "vip.tom.com";
			} else if ("sogou.com".equals(email_array)) {// 搜狗邮箱
				mailUrl = "mail.sogou.com";
			} else if ("126.com".equals(email_array)) {// 126邮箱
				mailUrl = "www.126.com";
			} else if ("vip.126.com".equals(email_array)) {// 126 vip 邮箱
				mailUrl = "vip.126.com";
			} else if ("139.com".equals(email_array)) {// 139邮箱
				mailUrl = "mail.10086.cn";
			} else if ("gmail.com".equals(email_array)) {// gmail邮箱
				mailUrl = "www.gmail.com";
			} else if ("hotmail.com".equals(email_array)) {// 139邮箱
				mailUrl = "login.live.com";
			} else if ("189.cn".equals(email_array)) {// 电信邮箱
				mailUrl = "webmail2.189.cn/webmail/";
			} else if ("qq.com".equals(email_array)) {// qq邮箱
				mailUrl = "mail.qq.com";
			} else if ("yahoo.com".equals(email_array)) {// 雅虎邮箱
				mailUrl = "mail.cn.yahoo.com";
			} else if ("yahoo.cn".equals(email_array)) {// 雅虎邮箱
				mailUrl = "mail.cn.yahoo.com";
			} else if ("yahoo.com.cn".equals(email_array)) {// 雅虎邮箱
				mailUrl = "mail.cn.yahoo.com";
			} else if ("21cn.com".equals(email_array)) {// 21cn邮箱
				mailUrl = "mail.21cn.com";
			} else if ("eyou.com".equals(email_array)) {// eyou邮箱
				mailUrl = "www.eyou.com";
			} else if ("188.com".equals(email_array)) {// 188邮箱
				mailUrl = "www.188.com";
			} else if ("yeah.net".equals(email_array)) {// yeah邮箱
				mailUrl = "www.yeah.net";
			} else if ("foxmail.com".equals(email_array)) {// foxmail邮箱
				mailUrl = "foxmail.com";
			} else if ("wo.com.cn".equals(email_array)) {// 联通手机邮箱
				mailUrl = "mail.wo.com.cn";
			} else if ("263.net".equals(email_array)) {// 263邮箱
				mailUrl = "www.263.net";
			} else if ("x263.net".equals(email_array)) {// 263邮箱
				mailUrl = "www.263.net";
			} else if ("263.net.cn".equals(email_array)) {// 263邮箱
				mailUrl = "www.263.net";
			} else {
				mailUrl = "mail." + (email.substring(email.indexOf("@") + 1));
			}
		}
		return mailUrl;
	}
	
}
