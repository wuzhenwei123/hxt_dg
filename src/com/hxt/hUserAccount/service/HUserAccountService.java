package com.hxt.hUserAccount.service;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hxt.hCommon.service.HCommonService;
import com.hxt.hUserAccount.dao.HUserAccountDAO;
import com.hxt.hUserAccount.model.HUserAccount;
import com.wx.service.WeiXinService;
import com.alibaba.fastjson.JSONObject;
import com.base.utils.FileUploadConstants;
import com.base.utils.ResponseList;
import com.base.utils.https.HttpUtils;

/**
 * @author	zlb
 * @time	2016年08月16日 10:58:52
 */
 @Service("hUserAccountService")
public class HUserAccountService {
	 Logger log = Logger.getLogger(HUserAccountService.class);

	@Resource(name = "hUserAccountDao")
    private HUserAccountDAO hUserAccountDAO;
	@Autowired
	private WeiXinService weiXinService;
	@Autowired
	private HCommonService hCommonService = null;
    
    public ResponseList<HUserAccount> getHUserAccountList(HUserAccount hUserAccount) {
        return hUserAccountDAO.getHUserAccountList(hUserAccount);
    }
    
    public List<HUserAccount> getHUserAccountBaseList(HUserAccount hUserAccount) {
        return hUserAccountDAO.getHUserAccountBaseList(hUserAccount);
    }
    
    public int getHUserAccountListCount(HUserAccount hUserAccount) {
        return hUserAccountDAO.getHUserAccountListCount(hUserAccount);
    }

    public HUserAccount getHUserAccount(HUserAccount hUserAccount) { 
        return hUserAccountDAO.getHUserAccount(hUserAccount);
    }

    public int insertHUserAccount(HUserAccount hUserAccount) throws Exception {
        return hUserAccountDAO.insertHUserAccount(hUserAccount);
    }

    public int updateHUserAccount(HUserAccount hUserAccount) throws Exception {
        return hUserAccountDAO.updateHUserAccount(hUserAccount);
    }
    
    public int unBindUserAccount(HUserAccount hUserAccount) throws Exception {
    	return hUserAccountDAO.unBindUserAccount(hUserAccount);
    }
    
    public int removeHUserAccount(HUserAccount hUserAccount) throws Exception {
        return hUserAccountDAO.removeHUserAccount(hUserAccount);
    }
    
    
	
	/**
	 * 分销订单提成通知
	 * @param toOPENID
	 * @param money 订单金额
	 * @param orderNo 订单号
	 * @param fee 分润金额
	 * @param contact_name 单位联系人姓名
	 * @param contact_phone 单位联系人手机
	 * @param companyId 单位ID
	 * @param companyName 单位名称
	 * @return
	 */
	public String sendCZTempltMsg(String toOPENID,String money,String orderNo,String fee,String contact_name,String contact_phone,Integer companyId,String companyName){
		log.info("----------toOPENID-------->"+toOPENID);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
		try{
			
			if(StringUtils.isNotBlank(contact_name)){
				contact_phone = contact_phone + "(" + contact_name + ")";
			}
			
			if(!StringUtils.isNotBlank(companyName)){
				companyName = "暂未登记单位名称";
			}
			
			String accessToken = weiXinService.getAccessToken(FileUploadConstants.APPID, FileUploadConstants.APPSECRET);
			String content = "{\"touser\": \""+toOPENID+"\","
					+ "\"template_id\": \"YFH9NlUs1SYvdnyoLiyz7mP0EpnzFhha5Y5R4daUR0Q\","
//					+ "\"template_id\": \"R_zFACMkHhGfQkO2duRrnKPfvDgNXW3YQzU_g6z8BcU\","
					+ "\"topcolor\": \"#FF0000\","
					+ "\"data\": {\"first\": {\"value\": \"恭喜，您客户的订单已经完成缴费，提成已计入您的账户，详情如下：\",\"color\": \"#173177\"},"
					+ "\"keyword1\": {\"value\": \""+orderNo+"\",\"color\": \"#173177\"},"
					+ "\"keyword2\": {\"value\": \""+money+"元\",\"color\": \"#173177\"},"
					+ "\"keyword3\": {\"value\": \"抄表电缴费\",\"color\": \"#173177\"},"
					+ "\"keyword4\": {\"value\": \""+fee+"元\",\"color\": \"#173177\"},"
					+ "\"keyword5\": {\"value\": \""+sf.format(new Date())+"\",\"color\": \"#173177\"},"
					+ "\"remark\": {\"value\": \"该客户联系人手机为："+contact_phone+"，单位ID："+companyId+"，单位名称为："+companyName+"，请您知晓。\",\"color\": \"#173177\"}}}";
			
			String jsonStr = HttpUtils.httpsRequest("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken, "POST", content);
			
//			URL url = new URL("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken);
//			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
//			byte[] buf = content.getBytes("UTF-8");
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
//			byte[] datas = weiXinService.readInputStream(httpConn.getInputStream());
//			String jsonStr = new String(datas,"utf-8");
			log.info("-------sendCZTempltMsg--------->"+jsonStr);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 订单提交成功通知模板信息
	 * @param toOPENID
	 * @param money 订单金额
	 * @param orderNo 订单编号
	 * @param contact_name 单位联系人姓名
	 * @param contact_phone 单位联系人电话
	 * @param companyId 单位ID
	 * @param companyName 单位名称
	 * @return
	 */
	public String sendJFTempltMsg(String toOPENID,String money,String orderNo,String contact_name,String contact_phone,Integer companyId,String companyName){
		log.info("----------toOPENID-------->"+toOPENID);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try{
			if(StringUtils.isNotBlank(contact_name)){
				contact_phone = contact_phone + "(" + contact_name + ")";
			}
			
			if(!StringUtils.isNotBlank(companyName)){
				companyName = "暂未登记单位名称";
			}
			String accessToken = weiXinService.getAccessToken(FileUploadConstants.APPID, FileUploadConstants.APPSECRET);
			String content = "{\"touser\": \""+toOPENID+"\","
					+ "\"template_id\": \"zA50Ta0NS9uDduOywwMFFilh6BA-RTl30XD4dfwPaPw\","
//					+ "\"template_id\": \"SpK2HBhYq5j5spswTqyzJrMqzJTeRDhkIgcHiRMADzM\","
					+ "\"topcolor\": \"#FF0000\","
					+ "\"data\": {\"first\": {\"value\": \"您好，您的客户已经生成了订单，正在等待客户付款，该订单信息如下:\",\"color\": \"#173177\"},"
					+ "\"keyword1\": {\"value\": \"抄表电缴费\",\"color\": \"#173177\"},"
					+ "\"keyword2\": {\"value\": \""+money+"元\",\"color\": \"#173177\"},"
					+ "\"keyword3\": {\"value\": \""+sf.format(new Date())+"\",\"color\": \"#173177\"},"
					+ "\"keyword4\": {\"value\": \""+orderNo+"\",\"color\": \"#173177\"},"
					+ "\"remark\": {\"value\": \"该客户联系人手机为："+contact_phone+"，单位ID："+companyId+"，单位名称为："+companyName+"，请您知晓。\",\"color\": \"#173177\"}}}";
			
			
			String jsonStr = HttpUtils.httpsRequest("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken, "POST", content);
			
//			URL url = new URL("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken);
//			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
//			byte[] buf = content.getBytes("UTF-8");
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
//			byte[] datas = weiXinService.readInputStream(httpConn.getInputStream());
//			String jsonStr = new String(datas,"utf-8");
			log.info("-------发送消息--------->"+jsonStr);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 客户注册成功通知
	 * @param toOPENID 发送对象
	 * @param contact_name 单位联系人姓名
	 * @param contact_phone 单位联系人电话
	 * @param ammeterNo 绑定的电表号
	 * @param companyId 公司ID
	 * @return
	 */
	public String sendZCTempltMsg(String toOPENID,String contact_name,String contact_phone,String ammeterNo,Integer companyId){
		log.info("----------toOPENID-------->"+toOPENID);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try{
			if(StringUtils.isNotBlank(contact_name)){
				contact_phone = contact_phone + "(" + contact_name + ")";
			}
			String accountName = "";
			String feeStr = "0.00";
			//查询欠费信息
			JSONObject result = hCommonService.hXTServiceQuery(ammeterNo, "127.0.0.1");
			if("00".equals(result.get("resultCode"))){//获取电表信息成功
				Integer fee = Integer.valueOf(result.get("totalFeeStr") == null ? "0" : result.get("totalFeeStr").toString());
				accountName = result.getJSONObject("resultInfo").getString("accountName");
				feeStr = this.getMoney(fee);
			}
			String accessToken = weiXinService.getAccessToken(FileUploadConstants.APPID, FileUploadConstants.APPSECRET);
			String content = "{\"touser\": \""+toOPENID+"\","
					+ "\"template_id\": \"QOlRQf-KC3pDFg80Y7ePckw8bhq4UG0K_pZWVSvutoE\","
//					+ "\"template_id\": \"x4lLCi311JUKVQ94_Yt_DmAWrRl6sPCAJQXOyM3SY4A\","
//					+ "\"url\": \""+FileUploadConstants.URL_PATH+"/hCompany/showCompanyMsg?companyId="+companyId+"\","
					+ "\"topcolor\": \"#FF0000\","
					+ "\"data\": {\"first\": {\"value\": \"恭喜，您有一个客户已注册成功！\",\"color\": \"#173177\"},"
					+ "\"keyword1\": {\"value\": \""+contact_phone+"\",\"color\": \"#173177\"},"
					+ "\"keyword2\": {\"value\": \""+sf.format(new Date())+"\",\"color\": \"#173177\"},"
					+ "\"remark\": {\"value\": \"该客户登记的缴费号为："+ammeterNo+"，缴费号客户名称："+accountName+"，目前欠费为："+feeStr+"元，请您知晓。\",\"color\": \"#173177\"}}}";
			
			String jsonStr = HttpUtils.httpsRequest("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken, "POST", content);
			
//			URL url = new URL("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken);
//			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
//			byte[] buf = content.getBytes("UTF-8");
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
//			byte[] datas = weiXinService.readInputStream(httpConn.getInputStream());
//			String jsonStr = new String(datas,"utf-8");
			log.info("-------发送消息--------->"+jsonStr);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 发送变更支持人员模板信息
	 * @param toOPENID
	 * @param oldNickName 老支持人员昵称
	 * @param newNickName 新支持人员昵称
	 * @param companyId 公司ID
	 * @param oldAdminId 老支持人员ID
	 * @param newAdminId 新支持人员ID
	 * @return
	 */
	public String sendCHTempltMsg(String toOPENID,String oldNickName,String newNickName,Integer companyId,Integer oldAdminId,Integer newAdminId){
		log.info("----------toOPENID-------->"+toOPENID);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");
		try{
			String accessToken = weiXinService.getAccessToken(FileUploadConstants.APPID, FileUploadConstants.APPSECRET);
			String content = "{\"touser\": \""+toOPENID+"\","
					+ "\"template_id\": \"EhblbHb2buasbAzd7JDs-kCSm_2Q-DkNBYE6iYwVd4c\","
//					+ "\"template_id\": \"EhblbHb2buasbAzd7JDs-kCSm_2Q-DkNBYE6iYwVd4c\","
//					+ "\"url\": \""+FileUploadConstants.URL_PATH+"/hCompany/showChangePeopel?companyId="+companyId+"&oldAdminId="+oldAdminId+"&newAdminId="+newAdminId+"\","
					+ "\"topcolor\": \"#FF0000\","
					+ "\"data\": {\"first\": {\"value\": \"您好！您的粉丝已成功关注\",\"color\": \"#173177\"},"
					+ "\"keyword1\": {\"value\": \""+oldNickName+"\",\"color\": \"#173177\"},"
					+ "\"keyword2\": {\"value\": \""+sf.format(new Date())+"\",\"color\": \"#173177\"},"
					+ "\"remark\": {\"value\": \"你用电，我用心，继续分享您的购电二维码哦，祝您生活愉快！\",\"color\": \"#173177\"}}}";
			
			String jsonStr = HttpUtils.httpsRequest("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken, "POST", content);
			
//			URL url = new URL("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken);
//			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
//			byte[] buf = content.getBytes("UTF-8");
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
//			byte[] datas = weiXinService.readInputStream(httpConn.getInputStream());
//			String jsonStr = new String(datas,"utf-8");
			log.info("-------发送消息--------->"+jsonStr);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 绑定、删除、解绑电表号模板信息
	 * @param toOPENID 发送对象
	 * @param op 操作标识 bind：绑定  unbind：解绑   delete：删除
	 * @param contact_name  单位联系人姓名
	 * @param contact_phone 单位联系人电话
	 * @param ammeterNo 电表号
	 * @param companyId 单位ID
	 * @param companyName 单位名称
	 * @return
	 */
	public String sendAOTempltMsg(String toOPENID,String op,String contact_name,String contact_phone,String ammeterNo,Integer companyId,String companyName){
		log.info("----------toOPENID-------->"+toOPENID);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try{
			if(StringUtils.isNotBlank(contact_name)){
				contact_phone = contact_phone + "(" + contact_name + ")";
			}
			String st = "";
			if("bind".equals(op)){
				st = "绑定";
			}else if("unbind".equals(op)){
				st = "解绑";
			}else if("delete".equals(op)){
				st = "删除";
			}
			if(!StringUtils.isNotBlank(companyName)){
				companyName = "暂未登记单位名称";
			}
			String accessToken = weiXinService.getAccessToken(FileUploadConstants.APPID, FileUploadConstants.APPSECRET);
			String content = "{\"touser\": \""+toOPENID+"\","
					+ "\"template_id\": \"tSWfeOwkqj508XMV_xiBrBLUGSuTY4W01ZPCf6V-DVg\","
//					+ "\"template_id\": \"XFGD9yyLN-wCXgMTcwC-2LjWSh77FwrW7tOdOePUSrM\","
					+ "\"topcolor\": \"#FF0000\","
					+ "\"data\": {\"first\": {\"value\": \"您好，您有一个客户对号码为"+ammeterNo+"的缴费号做了如下操作\",\"color\": \"#173177\"},"
					+ "\"keyword1\": {\"value\": \""+contact_phone+"\",\"color\": \"#173177\"},"
					+ "\"keyword2\": {\"value\": \""+st+"\",\"color\": \"#173177\"},"
					+ "\"keyword3\": {\"value\": \""+sf.format(new Date())+"\",\"color\": \"#173177\"},"
					+ "\"remark\": {\"value\": \"该客户登记的单位ID为："+companyId+"，单位名称为："+companyName+"，请您知晓。\",\"color\": \"#173177\"}}}";
			
			String jsonStr = HttpUtils.httpsRequest("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken, "POST", content);
			
//			URL url = new URL("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken);
//			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
//			byte[] buf = content.getBytes("UTF-8");
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
//			byte[] datas = weiXinService.readInputStream(httpConn.getInputStream());
//			String jsonStr = new String(datas,"utf-8");
			log.info("-------发送消息--------->"+jsonStr);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 代扣信息
	 * @param toOPENID 发送对象
	 * @param proxy_name 联系人姓名
	 * @param proxy_phone 联系人电话
	 * @param orderNo 订单号
	 * @param companyName 单位名称
	 * @return
	 */
	public String sendProxyTempltMsg(String toOPENID,Integer sex,String proxy_name,Integer proxy_id,String orderNo,String companyName,Integer ammeterCount,Integer porxyCount,Integer money){
		log.info("----------toOPENID-------->"+toOPENID);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try{
			if(StringUtils.isNotBlank(proxy_name)){
//				if(sex==1){
//					proxy_name = proxy_name + "先生";
//				}else{
//					proxy_name = proxy_name + "女士";
//				}
//				proxy_phone = proxy_phone + "(" + proxy_name + ")";
			}
//			if(!StringUtils.isNotBlank(companyName)){
//				companyName = "暂未登记单位名称";
//			}
			
			String param = URLEncoder.encode(FileUploadConstants.URL_PATH+"/weixin/access?appid="+FileUploadConstants.APPID, "utf-8");
			String url1 = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+FileUploadConstants.APPID+"&redirect_uri="+param+"&response_type=code&scope=snsapi_base&state=pay#wechat_redirect";
			System.out.println(url1);
			
			String accessToken = weiXinService.getAccessToken(FileUploadConstants.APPID, FileUploadConstants.APPSECRET);
			String content = "{\"touser\": \""+toOPENID+"\","
					+ "\"template_id\": \"QWGZRY_429bbnLB2xC97I70Z_zvQg-plvbSDm6WgG5c\","
//					+ "\"template_id\": \"I0y7BXXqDLxyNAaChloWxRC6rzGvTkCCGjb5X4iuQic\","
//					+ "\"url\": \""+url1+"\","
					+ "\"topcolor\": \"#FF0000\","
					+ "\"data\": {\"first\": {\"value\": \""+proxy_name+"，您好，贵单位在恒信通缴费平台网站登记的缴费号共计"+ammeterCount+"个，其中"+porxyCount+"个已产生欠费，详情如下：\",\"color\": \"#173177\"},"
					+ "\"keyword1\": {\"value\": \"抄表电费\",\"color\": \"#173177\"},"
					+ "\"keyword2\": {\"value\": \"共计"+this.getMoney(money)+"元\",\"color\": \"#173177\"},"
					+ "\"remark\": {\"value\": \"请您使用电脑登录http://qiye.chinaepay.com缴费。\",\"color\": \"#173177\"}}}";
			
			
			
			String jsonStr = HttpUtils.httpsRequest("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken, "POST", content);
			
//			URL url = new URL("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken);
//			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
//			byte[] buf = content.getBytes("UTF-8");
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
//			byte[] datas = weiXinService.readInputStream(httpConn.getInputStream());
//			String jsonStr = new String(datas,"utf-8");
			log.info("-------发送代扣消息--------->" +toOPENID + "-------" +jsonStr);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 代扣成功提醒
	 * @param toOPENID 发送对象
	 * @param sex 性别
	 * @param proxy_name 联系人姓名
	 * @param orderNo 订单号
	 * @param money 订单金额
	 * @return
	 */
	public String sendProxyTempltSuccessMsg(String toOPENID,Integer sex,String proxy_name,String orderNo,Long money){
		log.info("----------toOPENID-------->"+toOPENID);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try{
			if(StringUtils.isNotBlank(proxy_name)){
//				if(sex==1){
//					proxy_name = proxy_name + "先生";
//				}else{
//					proxy_name = proxy_name + "女士";
//				}
//				proxy_phone = proxy_phone + "(" + proxy_name + ")";
			}
//			if(!StringUtils.isNotBlank(companyName)){
//				companyName = "暂未登记单位名称";
//			}
			String accessToken = weiXinService.getAccessToken(FileUploadConstants.APPID, FileUploadConstants.APPSECRET);
			String content = "{\"touser\": \""+toOPENID+"\","
					+ "\"template_id\": \"nmBYq2ps-WYITSTi7MqBcHsu1WzOKEbaeZ4aoncPc7I\","
//					+ "\"template_id\": \"CyEJDuCL7dwQLHbce-Tl0GXiPpFpPW7MEEPpAplCAMs\","
//					+ "\"url\": \""+FileUploadConstants.URL_PATH+"/hCompany/showChangePeopel?companyId="+proxy_name+"&oldAdminId="+proxy_name+"&newAdminId="+proxy_name+"\","
					+ "\"topcolor\": \"#FF0000\","
					+ "\"data\": {\"first\": {\"value\": \""+proxy_name+"您好，贵单位在恒信通缴费平台网站上“手机缴费”业务的缴费已成功，订单号："+orderNo+"，详情如下：\",\"color\": \"#173177\"},"
					+ "\"keyword1\": {\"value\": \""+sf.format(new Date())+"\",\"color\": \"#173177\"},"
					+ "\"keyword2\": {\"value\": \"共计"+this.getMoneyL(money)+"元\",\"color\": \"#173177\"}"
					+ "}}";
			
			
			String jsonStr = HttpUtils.httpsRequest("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken, "POST", content);
			
//			URL url = new URL("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken);
//			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
//			byte[] buf = content.getBytes("UTF-8");
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
//			byte[] datas = weiXinService.readInputStream(httpConn.getInputStream());
//			String jsonStr = new String(datas,"utf-8");
			log.info("-------发送代扣消息--------->" +toOPENID + "-------" +jsonStr);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 代扣失败提醒
	 * @param toOPENID 发送对象
	 * @param sex 性别
	 * @param proxy_name 联系人姓名
	 * @param orderNo 订单号
	 * @param money 订单金额
	 * @param remark 失败原因
	 * @return
	 */
	public String sendProxyTempltFailMsg(String toOPENID,Integer sex,String proxy_name,String orderNo,Long money,String remark){
		log.info("----------toOPENID-------->"+toOPENID);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try{
			if(StringUtils.isNotBlank(proxy_name)){
//				proxy_name = proxy_name + "您好";
//				if(sex==1){
//					proxy_name = proxy_name + "先生";
//				}else{
//					proxy_name = proxy_name + "女士";
//				}
//				proxy_phone = proxy_phone + "(" + proxy_name + ")";
			}
//			if(!StringUtils.isNotBlank(companyName)){
//				companyName = "暂未登记单位名称";
//			}
			String accessToken = weiXinService.getAccessToken(FileUploadConstants.APPID, FileUploadConstants.APPSECRET);
			String content = "{\"touser\": \""+toOPENID+"\","
					+ "\"template_id\": \"WbSBM5KX5vDzADCscWM_G93uzi0YX8537a2OTEpvZwo\","
//					+ "\"template_id\": \"-F4wS-Np4vkOWzdN7ONJx7o3dkVN35tTHh7lhReDBJM\","
//					+ "\"url\": \""+FileUploadConstants.URL_PATH+"/hCompany/showChangePeopel?companyId="+proxy_name+"&oldAdminId="+proxy_name+"&newAdminId="+proxy_name+"\","
					+ "\"topcolor\": \"#FF0000\","
					+ "\"data\": {\"first\": {\"value\": \""+proxy_name+"您好，很遗憾地通知您，贵单位在恒信通缴费平台网站上“手机缴费”业务的缴费失败了，订单号："+orderNo+"，详情如下：\",\"color\": \"#173177\"},"
					+ "\"keyword1\": {\"value\": \""+this.getMoneyL(money)+"\",\"color\": \"#173177\"},"
					+ "\"keyword2\": {\"value\": \"“手机缴费”业务\",\"color\": \"#173177\"},"
					+ "\"keyword3\": {\"value\": \""+sf.format(new Date())+"\",\"color\": \"#173177\"},"
					+ "\"remark\": {\"value\": \"失败原因："+remark+"\",\"color\": \"#173177\"}}}";
			
			
			String jsonStr = HttpUtils.httpsRequest("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken, "POST", content);
			
//			URL url = new URL("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken);
//			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
//			byte[] buf = content.getBytes("UTF-8");
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
//			byte[] datas = weiXinService.readInputStream(httpConn.getInputStream());
//			String jsonStr = new String(datas,"utf-8");
			log.info("-------发送代扣消息--------->" +toOPENID + "-------" +jsonStr);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 注册鼓励金
	 * @param toOPENID 发送对象
	 * @param sex 性别
	 * @param proxy_name 联系人姓名
	 * @param orderNo 订单号
	 * @param money 订单金额
	 * @param remark 失败原因
	 * @return
	 */
	public String sendRegGLTempltMsg(String toOPENID,String ammeterNo,Double money){
		log.info("----------toOPENID2-------->"+toOPENID);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try{
//			if(StringUtils.isNotBlank(proxy_name)){
//				proxy_name = proxy_name + "您好";
//				if(sex==1){
//					proxy_name = proxy_name + "先生";
//				}else{
//					proxy_name = proxy_name + "女士";
//				}
//				proxy_phone = proxy_phone + "(" + proxy_name + ")";
//			}
//			if(!StringUtils.isNotBlank(companyName)){
//				companyName = "暂未登记单位名称";
//			}
			
			BigDecimal bg1 = new BigDecimal(money);
	        double f1 = bg1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			
			String accessToken = weiXinService.getAccessToken(FileUploadConstants.APPID, FileUploadConstants.APPSECRET);
			String content = "{\"touser\": \""+toOPENID+"\","
					+ "\"template_id\": \"O0EUOnrUZgo-qxCOB6ne8fupir2Uj4FKw5G90ifdsFk\","
//					+ "\"template_id\": \"yvVGKjnt1mR47ZUOSYFe_tX9dAh0mCC_saWm0fVDgmI\","
//					+ "\"url\": \""+FileUploadConstants.URL_PATH+"/hCompany/showChangePeopel?companyId="+proxy_name+"&oldAdminId="+proxy_name+"&newAdminId="+proxy_name+"\","
					+ "\"topcolor\": \"#FF0000\","
					+ "\"data\": {\"first\": {\"value\": \"恭喜，您有一位客户注册成功了了，缴费号为："+ammeterNo+"，感谢您的辛苦工作，奉上红包一个，请您笑纳：\",\"color\": \"#173177\"},"
					+ "\"keyword1\": {\"value\": \""+f1+"元\",\"color\": \"#173177\"},"
					+ "\"keyword2\": {\"value\": \""+sf.format(new Date())+"\",\"color\": \"#173177\"},"
					+ "\"remark\": {\"value\": \"继续加油哦！\",\"color\": \"#173177\"}}}";
			
			String jsonStr = HttpUtils.httpsRequest("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken, "POST", content);
			
//			URL url = new URL("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken);
//			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
//			byte[] buf = content.getBytes("UTF-8");
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
//			byte[] datas = weiXinService.readInputStream(httpConn.getInputStream());
//			String jsonStr = new String(datas,"utf-8");
			log.info("-------发送代扣消息--------->" +toOPENID + "-------" +jsonStr);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 注册鼓励金
	 * @param toOPENID 发送对象
	 * @param sex 性别
	 * @param proxy_name 联系人姓名
	 * @param orderNo 订单号
	 * @param money 订单金额
	 * @param remark 失败原因
	 * @return
	 */
	public String sendRegGLTempltMsg1(String toOPENID,String ammeterNo,Double money){
		log.info("----------toOPENID-------->"+toOPENID);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try{
//			if(StringUtils.isNotBlank(proxy_name)){
//				proxy_name = proxy_name + "您好";
//				if(sex==1){
//					proxy_name = proxy_name + "先生";
//				}else{
//					proxy_name = proxy_name + "女士";
//				}
//				proxy_phone = proxy_phone + "(" + proxy_name + ")";
//			}
//			if(!StringUtils.isNotBlank(companyName)){
//				companyName = "暂未登记单位名称";
//			}
			
			
			String accessToken = weiXinService.getAccessToken(FileUploadConstants.APPID, FileUploadConstants.APPSECRET);
			String content = "{\"touser\": \""+toOPENID+"\","
					+ "\"template_id\": \"O0EUOnrUZgo-qxCOB6ne8fupir2Uj4FKw5G90ifdsFk\","
//					+ "\"template_id\": \"yvVGKjnt1mR47ZUOSYFe_tX9dAh0mCC_saWm0fVDgmI\","
//					+ "\"url\": \""+FileUploadConstants.URL_PATH+"/hCompany/showChangePeopel?companyId="+proxy_name+"&oldAdminId="+proxy_name+"&newAdminId="+proxy_name+"\","
					+ "\"topcolor\": \"#FF0000\","
					+ "\"data\": {\"first\": {\"value\": \"您有一位客户注册成功了了，缴费号为："+ammeterNo+"，不过可惜了，这个缴费号已经被注册过，鼓励金被别人拿走了：\",\"color\": \"#173177\"},"
					+ "\"keyword1\": {\"value\": \"0.00元\",\"color\": \"#173177\"},"
					+ "\"keyword2\": {\"value\": \""+sf.format(new Date())+"\",\"color\": \"#173177\"},"
					+ "\"remark\": {\"value\": \"要先下手为强啊，不然鼓励金都让别人挣了。\",\"color\": \"#173177\"}}}";
			
			String jsonStr = HttpUtils.httpsRequest("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken, "POST", content);
			
//			URL url = new URL("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken);
//			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
//			byte[] buf = content.getBytes("UTF-8");
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
//			byte[] datas = weiXinService.readInputStream(httpConn.getInputStream());
//			String jsonStr = new String(datas,"utf-8");
			log.info("-------发送代扣消息--------->" +toOPENID + "-------" +jsonStr);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String getMoney(Integer money){
		String totalFeeStr1 = null;
		if(money!=null&&money>0){
			if(String.valueOf(money).length()>2){
				int fen = money%100;
				if(fen>0&&fen<10){
					totalFeeStr1 = money/100 + ".0" + fen;
				}else if(fen>=10){
					totalFeeStr1 = money/100 + "." + fen;
				}else{
					totalFeeStr1 = money/100 + ".00";
				}
			}else if(String.valueOf(money).length()==1){
				totalFeeStr1 = "0.0"+money;
			}else{
				totalFeeStr1 = "0."+money;
			}
		}else{
			totalFeeStr1 = "0.00";
		}
		return totalFeeStr1;
	}
	
	public String getMoneyL(Long money){
		String totalFeeStr1 = null;
		if(money!=null&&money>0){
			if(String.valueOf(money).length()>2){
				Long fen = money%100;
				if(fen>0&&fen<10){
					totalFeeStr1 = money/100 + ".0" + fen;
				}else if(fen>=10){
					totalFeeStr1 = money/100 + "." + fen;
				}else{
					totalFeeStr1 = money/100 + ".00";
				}
			}else if(String.valueOf(money).length()==1){
				totalFeeStr1 = "0.0"+money;
			}else{
				totalFeeStr1 = "0."+money;
			}
		}else{
			totalFeeStr1 = "0.00";
		}
		return totalFeeStr1;
	}
}
