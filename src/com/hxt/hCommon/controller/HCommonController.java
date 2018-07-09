package com.hxt.hCommon.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.base.utils.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hxt.hPayOrder.model.HPayOrder;
import com.hxt.hPayOrder.service.HPayOrderService;
import com.hxt.hPayurlCheck.model.HPayurlCheck;
import com.hxt.hPayurlCheck.service.HPayurlCheckService;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sun.security.krb5.internal.CredentialsUtil;

import com.base.controller.BaseController;
import com.hxt.hAmmeterQueryLog.model.HAmmeterQueryLog;
import com.hxt.hAmmeterQueryLog.service.HAmmeterQueryLogService;
import com.hxt.hCommon.service.HCommonService;
import com.hxt.hCompany.model.HCompany;
import com.hxt.hCompany.service.HCompanyService;
import com.hxt.task.TaskJob;
import com.sys.manageAdminUser.model.ManageAdminUser;
import com.unionpay.acp.sdk.CertUtil;
import com.unionpay.acp.sdk.SDKConfig;
import com.unionpay.acp.sdk.SDKConstants;
import com.unionpay.acp.sdk.SDKUtil;

import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/hCommon")
public class HCommonController extends BaseController{
	
	Logger log = Logger.getLogger(HCommonController.class);
	
	@Autowired
	private HCommonService hCommonService;
	@Autowired
	private HCompanyService hcompanyService;
	
	@Autowired
	private HPayOrderService hpayorderService = null;
	@Autowired
	private TaskJob job = null;

	@Autowired
	private HPayurlCheckService hpayurlcheckService;
	@Autowired
	private HAmmeterQueryLogService hAmmeterQueryLogService;
	public static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();    // gson解析对象。
	
	public static Map<String,String> maporder = new HashMap<String,String>();

	
	@RequestMapping(value = "/test2", method = RequestMethod.GET)
	public String test1(HttpServletRequest request, HttpServletResponse response, Model model){
		job.job2();
		return null;
	}

	/**
	 * 电表号查询是否存在
	 * @return CS:调用链接超时/00:存在/其他:不存在
	 */
	@RequestMapping(value = "/checkElectric", method = RequestMethod.GET)
	public String checkElectric(HttpServletRequest request, HttpServletResponse response, Model model){
		try{
			String electricNum = RequestHandler.getString(request, "electricNum");//电表号
			String IP = this.getIpAddr(request);
			String result = hCommonService.checkElectric(electricNum, IP);
			writeSuccessMsg("ok", result, response);
		}catch(Exception e){
			writeErrorMsg("fail", null, response);
			e.printStackTrace();
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
			String IP = this.getIpAddr(request);
			System.out.println("-----IP-------------"+IP);
			System.out.println("-----electricNum-------------"+electricNum);
			JSONObject result = hCommonService.hXTServiceQuery(electricNum, IP);
			if("00".equals(result.get("resultCode"))){
				resultMap.put("status", "success");
				resultMap.put("msg", "获取电表信息成功!");
				resultMap.put("data", result);
			}else{
				resultMap.put("status", "fail");
				resultMap.put("msg", "不存在该电表信息!");
			}
		}catch (Exception e){
			resultMap.put("status", "fail");
			resultMap.put("msg", "获取电表信息失败!");
		}
		ResponseUtil.responseText(response, gson.toJson(resultMap));
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
	 *	非登录专用
	 */
	@RequestMapping(value = "/queryAmmeterInfo", method = RequestMethod.GET)
	public String queryAmmeterInfo(HttpServletRequest request, HttpServletResponse response, Model model){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();//返回数据
		Integer errCount = 0;
		if(request.getSession().getAttribute("errCount")!=null){
			errCount = Integer.valueOf(request.getSession().getAttribute("errCount").toString());
		}
			
		try{
			String electricNum = RequestHandler.getString(request, "ammeterNo");//电表号
			String phone = RequestHandler.getString(request, "phone");//电表号
			String vercode = RequestHandler.getString(request, "vercode");//手机验证码
			String sysCode = SendMsgUtil.CODEMAP.get(phone);// 手机验证码
			if (sysCode == null ||StringUtils.isBlank(vercode)|| !vercode.equalsIgnoreCase(sysCode)) {
				request.getSession().setAttribute("errCount", ++errCount);
				writeErrorMsg("验证码不正确", "{\"errCount\":\""+errCount+"\"}", response);
				return null;
			}
			request.getSession().setAttribute("errCount", null);//清空错误次数
			if(StringUtils.isNotBlank(electricNum)&&StringUtils.isNotBlank(phone)&&StringUtils.isNotBlank(vercode)){
				Pattern p = Pattern.compile("^1\\d{10}$");
				Matcher m = p.matcher(phone);
				if(!m.matches()){
					resultMap.put("status", "fail");
					resultMap.put("msg", "手机号不正确!");
				}else{
					SendMsgUtil.CODEMAP.remove(phone);
					String IP = this.getIpAddr(request);
					System.out.println("-----IP-------------"+IP);
					System.out.println("-----electricNum-------------"+electricNum);
					JSONObject result = hCommonService.hXTServiceQuery(electricNum, IP);
					if("00".equals(result.get("resultCode"))){
						HAmmeterQueryLog hAmmeterQueryLog = new HAmmeterQueryLog();
						hAmmeterQueryLog.setCreateTime(new Date());
						hAmmeterQueryLog.setAmmeterNo(electricNum);
						hAmmeterQueryLog.setIp(IP);
						hAmmeterQueryLog.setPhone(phone);
						hAmmeterQueryLog.setAmmeter_address(result.getJSONObject("resultInfo").getString("address"));
						hAmmeterQueryLog.setAmmeter_name(result.getJSONObject("resultInfo").getString("accountName"));
						hAmmeterQueryLog.setFee(result.getJSONObject("resultInfo").getString("accountFee"));
						hAmmeterQueryLog.setZnFee(result.getJSONObject("resultInfo").getString("lateFee"));
						hAmmeterQueryLog.setTotalFee(result.getString("totalFee"));
						hAmmeterQueryLogService.insertHAmmeterQueryLog(hAmmeterQueryLog);
						resultMap.put("status", "success");
						resultMap.put("msg", "获取电表信息成功!");
						resultMap.put("data", result);
					}else{
						resultMap.put("status", "fail");
						resultMap.put("msg", result.get("resultInfo"));
					}
				}
			}else{
				resultMap.put("status", "fail");
				resultMap.put("msg", "参数错误!");
			}

		}catch (Exception e){
			resultMap.put("status", "fail");
			resultMap.put("msg", "获取电表信息失败!");
		}
		ResponseUtil.responseText(response, gson.toJson(resultMap));
		return null;
	}

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
	 * 商户前台通知
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/frontRcvResponse")
	public String frontRcvResponse(HttpServletRequest request, HttpServletResponse response, Model model){
		try{
			String encoding = request.getParameter(SDKConstants.param_encoding);
			Map<String, String> respParam = hCommonService.getAllRequestParam(request);
			Map<String, String> valideData = null;
			if (null != respParam && !respParam.isEmpty()) {
				Iterator<Entry<String, String>> it = respParam.entrySet().iterator();
				valideData = new HashMap<String, String>(respParam.size());
				while (it.hasNext()) {
					Entry<String, String> e = it.next();
					String key = (String) e.getKey();
					String value = (String) e.getValue();
					value = new String(value.getBytes("ISO-8859-1"), encoding);
					valideData.put(key, value);
				}
			}
//			if (!hCommonService.validate(valideData, encoding)) {//验证回调失败
			if (!SDKUtil.validate(valideData, encoding)) {//验证回调失败
				System.out.println("22222222222222222222222222");
			} else {//验证回调成功
				//跳转成功页面
				String respCode = valideData.get("respCode");
				if("00".equals(respCode)){
					String merOrderId = valideData.get("merOrderId");//商户订单号
					String merTxnAmt = valideData.get("merTxnAmt");//交易金额
					String merTxnTime = valideData.get("merTxnTime");//交易时间
					String queryId = valideData.get("queryId");//银行交易流水号
					String settleDate = valideData.get("settleDate");//清算日期
					//获取订单信息
					HPayOrder tmp = new HPayOrder();
					tmp.setO_no(merOrderId);
					tmp = hpayorderService.getHPayOrder(tmp);
					if(tmp!=null){
						//获取单位信息
						HCompany tmpCom = new HCompany();
						tmpCom.setId(tmp.getC_id());
						tmpCom = hcompanyService.getHCompany(tmpCom);
						if(tmpCom!=null){
							model.addAttribute("cname", tmpCom.getContact_phone());
						}
						SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
						model.addAttribute("ctime", sf.format(tmp.getPay_time()));
						model.addAttribute("cfee",merTxnAmt);
						model.addAttribute("cNum", merOrderId);
					}
					return "/front/payStatus";
				}else{
					return "/front/payStatus1";
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return  null;
	}

	
	/**
	 * 商户后台通知
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/backRcvResponse")
	public String backRcvResponse(HttpServletRequest request, HttpServletResponse response, Model model){
		try{
			String encoding = request.getParameter(SDKConstants.param_encoding);
			Map<String, String> reqParam = hCommonService.getAllRequestParam(request);
			String IP = this.getIpAddr(request);
			Map<String, String> valideData = null;
			if (null != reqParam && !reqParam.isEmpty()) {
				Iterator<Entry<String, String>> it = reqParam.entrySet().iterator();
				valideData = new HashMap<String, String>(reqParam.size());
				while (it.hasNext()) {
					Entry<String, String> e = it.next();
					String key = (String) e.getKey();
					String value = (String) e.getValue();
					value = new String(value.getBytes("ISO-8859-1"), encoding);
					valideData.put(key, value);
				}
			}
//			if (!hCommonService.validate(valideData, encoding)) {//验证回调失败
			if (!SDKUtil.validate(valideData, encoding)) {//验证回调失败
				System.out.println("======================");
			} else {//验证回调成功
				//销账
				log.info("----------成功收到银行返回--------------"+valideData.get("merOrderId"));
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter pw = response.getWriter();
				pw.print("respCode=00");
				pw.flush();
				pw.close();
				HPayOrder hPayOrder = new HPayOrder();
				hPayOrder.setO_no(valideData.get("merOrderId"));
				HPayOrder order = hpayorderService.getHPayOrder(hPayOrder);
				if(!"0".equals(order.getPay_status())){
					
				}else{
					//判断是否已经回调
					String oso = maporder.get(valideData.get("merOrderId"));
					if(StringUtils.isNotBlank(oso)){
						
					}else{
						//放入map
						maporder.put(valideData.get("merOrderId"), valideData.get("merOrderId"));
						valideData.put("Source", "ACT");
						hCommonService.tickOffPayOrder(valideData, IP);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(HttpServletRequest request, HttpServletResponse response, Model model){
		try{
			Map<String,String> map = new HashMap<String,String>();
			map.put("version", "1.0.0");
			map.put("encoding", "UTF-8");
			map.put("signMethod", "01");
			map.put("bizType", "000202");
			map.put("frontUrl", FileUploadConstants.FRONT_URL);
			map.put("backUrl", FileUploadConstants.BACK_URL);
			map.put("merId", "000110060120006");
			map.put("merOrderId", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
			map.put("merTxnTime", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
			map.put("merTxnAmt", "100");
			map.put("currencyCode", "156");
			Map<String, String> hiddens = DemoBase.signData(map);
			String html = DemoBase.createHtml(FileUploadConstants.GATE_WAY, hiddens);
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter pw = response.getWriter();
			pw.print(html);
			pw.flush();
			pw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/payStatus", method = RequestMethod.GET)
	public String toElectricPay(HttpServletRequest request, HttpServletResponse response, Model model){
		model.addAttribute("nav", "dianfei");
		return "/front/payStatus";
	}
	
}
