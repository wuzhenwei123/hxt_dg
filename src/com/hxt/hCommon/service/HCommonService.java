
package com.hxt.hCommon.service;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.Formatter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.management.IntrospectionException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.base.utils.DemoBase;
import com.base.utils.FileUploadConstants;
import com.base.utils.MapUtil;
import com.base.utils.Tinput;
import com.hxt.hAgent.dao.HAgentDAO;
import com.hxt.hAgent.model.HAgent;
import com.hxt.hAgentTwo.dao.HAgentTwoDAO;
import com.hxt.hAgentTwo.model.HAgentTwo;
import com.hxt.hAmmeterInfo.dao.HAmmeterInfoDAO;
import com.hxt.hAmmeterInfo.model.HAmmeterInfo;
import com.hxt.hAmmeterInfo.service.HAmmeterInfoService;
import com.hxt.hCompany.dao.HCompanyDAO;
import com.hxt.hCompany.model.HCompany;
import com.hxt.hDiaodan.model.HDiaodan;
import com.hxt.hDiaodan.service.HDiaodanService;
import com.hxt.hPayGuli.dao.HPayGuliDAO;
import com.hxt.hPayGuli.model.HPayGuli;
import com.hxt.hPayOrder.dao.HPayOrderDAO;
import com.hxt.hPayOrder.model.HPayOrder;
import com.hxt.hPayOrder.service.HPayOrderService;
import com.hxt.hProfitRatio.dao.HProfitRatioDAO;
import com.hxt.hProfitRatio.model.HProfitRatio;
import com.hxt.hSubCompany.dao.HSubCompanyDAO;
import com.hxt.hSubCompany.model.HSubCompany;
import com.hxt.hSubOrder.dao.HSubOrderDAO;
import com.hxt.hSubOrder.model.HSubOrder;
import com.hxt.hUserAccount.dao.HUserAccountDAO;
import com.hxt.hUserAccount.model.HUserAccount;
import com.hxt.hUserAccount.service.HUserAccountService;
import com.hxt.hUserAccountDetail.dao.HUserAccountDetailDAO;
import com.hxt.hUserAccountDetail.model.HUserAccountDetail;
import com.hxt.wap.controller.PayController;
import com.hxt.wap.service.CallProcedureService;
import com.sys.manageAdminUser.dao.ManageAdminUserDAO;
import com.sys.manageAdminUser.model.ManageAdminUser;
import com.unionpay.acp.sdk.SDKUtil;

@Service("hCommonService")
public class HCommonService {
	
	Logger log = Logger.getLogger(HCommonService.class);

	@Resource(name = "hPayOrderDao")
	private HPayOrderDAO hPayOrderDAO;
	@Resource(name = "hSubOrderDao")
    private HSubOrderDAO hSubOrderDAO;
	@Resource(name = "hCompanyDao")
	private HCompanyDAO hCompanyDao;
	@Resource(name = "hAgentDao")
	private HAgentDAO hAgentDao;
	@Resource(name = "hAgentTwoDao")
	private HAgentTwoDAO hAgentTwoDao;
	@Resource(name = "manageAdminUserDao")
	private ManageAdminUserDAO manageAdminUserDao;
	@Resource(name = "hAmmeterInfoDao")
	private HAmmeterInfoDAO hAmmeterInfoDao;
	@Resource(name = "hProfitRatioDao")
	private HProfitRatioDAO hProfitRatioDao;
	@Resource(name = "hUserAccountDao")
	private HUserAccountDAO hUserAccountDao;
	@Resource(name = "hUserAccountDetailDao")
	private HUserAccountDetailDAO hUserAccountDetailDao;
	@Resource(name = "hSubCompanyDao")
	private HSubCompanyDAO hSubCompanyDao;
	@Autowired
	private CallProcedureService callProcedureService;
	@Autowired
	private HDiaodanService hdiaodanService = null;
	@Autowired
	private HUserAccountService huseraccountService = null;
	@Resource(name = "hPayGuliDao")
    private HPayGuliDAO hPayGuliDAO;
	/**
	 * 电表号查询是否存在
	 * @param electricNum 电表号
	 * @param IP 当前客户ip地址
	 * @return CS:调用链接超时/00:存在/其他:不存在
	 */
	public String checkElectric(String electricNum,String IP){
		String result = null;
		try{
			if(StringUtils.isNotBlank(electricNum)){
				result = Tinput.getHXTServiceQuery("3100", electricNum, IP, "0");
				if("CS".equals(result)){
					result = "CS";
				}else{
					result = Tinput.getVal(result, "ResultCode");
				}
			}
		}catch(Exception e){
			result = "G0";
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 调单操作，查询电表是否有欠费
	 * @param electricNum 电表号
	 * @param IP 当前客户ip地址
	 * @return json 
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
	public JSONObject hXTServiceQuery(String electricNum,String IP){
		JSONObject json = new JSONObject();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			log.info("-----------IP------------"+IP);
			if(StringUtils.isNotBlank(electricNum)){
				String result = Tinput.getHXTServiceQuery("3100", electricNum, IP, "0");
				
				HDiaodan hDiaodan = new HDiaodan();
				hDiaodan.setElectricNum(electricNum);
				hDiaodan.setContent(result);
				hDiaodan.setCreateTime(new Date());
				hDiaodan.setRemark1("1");
				hdiaodanService.insertHDiaodan(hDiaodan);
				
				log.info("-----查询--"+electricNum+"---"+sf.format(new Date())+"------------->"+result);
				if("CS".equals(result)){//超时
					json.put("resultCode", "CS");
				}else{
					String resultCode = Tinput.getVal(result, "ResultCode");
					if("00".equals(resultCode)){//查询成功
						json.put("resultCode", "00");
						String totalFeeStr = Tinput.getVal(result, "TotalFee");
						Integer totalFee = Integer.valueOf(totalFeeStr);
						json.put("totalFeeStr", totalFee);
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
								json.put("totalFee", totalFeeStr);
							}else if(String.valueOf(totalFee).length()==1){
								json.put("totalFee", "0.0"+totalFee);
							}else{
								json.put("totalFee", "0."+totalFee);
							}
						}else{
							json.put("totalFee", "0.00");
						}
						String paymentInfo = Tinput.getVal(result, "PaymentInfo");
						if(StringUtils.isNotBlank(paymentInfo)){
							String[] paymentInfos = paymentInfo.split("\\u0024");
							json.put("paymentInfo", paymentInfos[0]);
						}
						String resultInfo = Tinput.getVal(result, "ResultInfo");
						if(StringUtils.isNotBlank(resultInfo)&&resultInfo.indexOf("；")!=-1){
							resultInfo = resultInfo.replaceAll("；", ";").replaceAll("：", ":");
							String[] resultInfos = resultInfo.split(";");
							JSONObject json1 = new JSONObject();
							json1.put("accountName", resultInfos[0].split(":")[1]);
							json1.put("address", resultInfos[1].split(":")[1]);
							json1.put("accountTime", resultInfos[2].split(":")[1]);
							json1.put("accountFee", resultInfos[3].split(":")[1]);
							json1.put("lateFee", resultInfos[4].split(":")[1]);
							json.put("resultInfo", json1);
						}
						
					}else{
						json.put("resultCode", resultCode);
						json.put("resultInfo", Tinput.getVal(result, "ResultInfo"));
					}
				}
			}
		}catch(Exception e){
			json.put("resultCode", "G0");
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * 销账
	 * @param electricNum 电表号
	 * @param IP 当前客户ip地址
	 * @param serialNo 交易流水号（网银返回）
	 * @param TotalFee 销账金额
	 * @param settlementDate 银行清算日（格式：MMdd）
	 * @param paymentInfo 中心流水号
	 * @return json
	 * key:resultCode       查询是否成功  00:成功/CS:超时/其他失败
	 */
	public JSONObject hXTServicePay(String electricNum,String IP,String serialNo,String TotalFee,String settlementDate,String paymentInfo,String Source){
		JSONObject json = new JSONObject();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			if(StringUtils.isNotBlank(electricNum)&&StringUtils.isNotBlank(paymentInfo)){
				paymentInfo = electricNum + "$" + paymentInfo;
				log.info("-----------TotalFee-------------"+TotalFee);
				log.info("-----销账前-----"+sf.format(new Date())+"------------->"+TotalFee);
				String result = Tinput.HXTServicePay("3100", paymentInfo, IP, TotalFee, serialNo, settlementDate,Source);
				log.info("-----销账-----"+electricNum+"-----"+sf.format(new Date())+"------------->"+result);
				HDiaodan hDiaodan = new HDiaodan();
				hDiaodan.setElectricNum(electricNum);
				hDiaodan.setStyle(2);
				hDiaodan.setCreateTime(new Date());
				hDiaodan.setContent(result);
				hDiaodan.setRemark1("2");
				hdiaodanService.insertHDiaodan(hDiaodan);
				if("CS".equals(result)){//超时
					json.put("resultCode", "CS");
				}else{
					String resultCode = Tinput.getVal(result, "ResultCode");
					json.put("resultCode", resultCode);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return json;
	}
	
	public void get(Map<String,String> map){
		try{
			map.put("version", "1.0.0");
			map.put("encoding", "UTF-8");
			map.put("signMethod", "01");
			map.put("bizType", "000202");
			map.put("frontUrl", "000110060120006");
			map.put("backUrl", "000110060120006");
			map.put("merId", "000110060120006");
			map.put("merOrderId", "a1b15555555555555555");
			map.put("merTxnTime", "20151125101010");
			map.put("merTxnAmt", "100");
			map.put("currencyCode", "156");
			DemoBase.signData(map);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public String getNoticUrl(){
		try{
			Map<String,String> map = new HashMap<String,String>();
			map.put("", "");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
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
	 * 获取请求参数中所有的信息
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, String> getAllRequestParam(final HttpServletRequest request) {
		Map<String, String> res = new HashMap<String, String>();
		Enumeration<?> temp = request.getParameterNames();
		if (null != temp) {
			while (temp.hasMoreElements()) {
				String en = (String) temp.nextElement();
				String value = request.getParameter(en);
				res.put(en, value);
				if (res.get(en) == null || "".equals(res.get(en))) {
					res.remove(en);
				}
			}
		}
		return res;
	}
	
	/**
	 * 验证回调签名
	 * @param resData
	 * @param encoding
	 * @return
	 */
	public boolean validate(Map<String, String> resData, String encoding) {
		String stringSign = (String) resData.get("signature");
		SDKUtil.sign(resData, encoding);
		try {
			System.out.println("------------------"+resData.get("signature"));
			System.out.println("--------stringSign----------"+stringSign);
		    return resData.get("signature").equals(stringSign);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 支付订单销账
	 * @param valideData
	 * @return
	 */
	@SuppressWarnings("static-access")
	public HPayOrder tickOffPayOrder(Map<String, String> valideData,String IP) throws Exception {

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int paysum = 0;//缴费金额
		String merOrderId = valideData.get("merOrderId");//商户订单号
		String merTxnAmt = valideData.get("merTxnAmt");//交易金额
		String merTxnTime = valideData.get("merTxnTime");//交易时间
		String queryId = valideData.get("queryId");//银行交易流水号
		String settleDate = valideData.get("settleDate");//清算日期
		String respCode = valideData.get("respCode");
		String respMsg = valideData.get("respMsg");
		String Source = valideData.get("Source");
		
		log.info("-------merOrderId--------"+merOrderId);
		log.info("-------merTxnAmt--------"+merTxnAmt);
		log.info("-------merTxnTime--------"+merTxnTime);
		log.info("-------queryId--------"+queryId);
		log.info("-------settleDate--------"+settleDate);
		
		new PayController().templeteMap.remove(merOrderId+"_1");
		new PayController().templeteMap.remove(merOrderId+"_3");

		List<Map> ammeterList = new ArrayList<Map>();//欠费电表list

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		HPayOrder hPayOrderQry = new HPayOrder();
		hPayOrderQry.setO_no(merOrderId);
		HPayOrder hPayOrder = hPayOrderDAO.getHPayOrder(hPayOrderQry);
		Date payTime = new Date();
		try {
			payTime = sdf.parse(merTxnTime);
		
		
		//获取公司信息、代理、服务人员
		HCompany company = new HCompany();
		company.setId(hPayOrder.getC_id());
		company = hCompanyDao.getHCompany(company);
//		ManageAdminUser companyAdmin = new ManageAdminUser();
//		companyAdmin.setCompanyId(hPayOrder.getC_id());
//		companyAdmin = manageAdminUserDao.getManageAdminUser(companyAdmin);
		HAgent agent = null;
		ManageAdminUser agentAdmin = null;
		log.info("------------company.getOneAgentOpenId()-----------"+company.getOneAgentOpenId());
		if(StringUtils.isNotBlank(company.getOneAgentOpenId())){
			agent = new HAgent();
			agent.setStatus(1);
			agent.setOpenId(company.getOneAgentOpenId());
			agent = hAgentDao.getHAgent(agent);
			if(agent!=null){
				agentAdmin = new ManageAdminUser();
				agentAdmin.setOneAgentOpenId(company.getOneAgentOpenId());
				agentAdmin.setRoleId(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
				agentAdmin = manageAdminUserDao.getManageAdminUser(agentAdmin);
				log.info("------------agentAdmin-----------"+agentAdmin.getOpenId());
			}
		}
		HAgentTwo agentTwo = null;
		ManageAdminUser agentTwoAdmin = null;
		log.info("------------company.getTwoAgentOpenID()-----------"+company.getTwoAgentOpenID());
		if(StringUtils.isNotBlank(company.getTwoAgentOpenID())){
			agentTwo = new HAgentTwo();
			agentTwo.setStatus(1);
			agentTwo.setOpenId(company.getTwoAgentOpenID());
			agentTwo = hAgentTwoDao.getHAgentTwo(agentTwo);
			if(agentTwo!=null){
				agentTwoAdmin = new ManageAdminUser();
				agentTwoAdmin.setTwoAgentOpenId(company.getTwoAgentOpenID());
				agentTwoAdmin.setRoleId(Integer.valueOf(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID));
				agentTwoAdmin = manageAdminUserDao.getManageAdminUser(agentTwoAdmin);
				log.info("------------agentTwoAdmin-----------"+agentTwoAdmin.getOpenId());
			}
		}
		ManageAdminUser servicer = null;
		if(company.getServicerId()!=null){
			servicer = new ManageAdminUser();
			servicer.setAdminId(company.getServicerId());
			servicer = manageAdminUserDao.getManageAdminUser(servicer);
		}
		if (hPayOrder!=null&&("0").equals(hPayOrder.getPay_status())){//存在未销账支付订单
			if("00".equals(respCode)){
				hPayOrder.setPay_status("1");//支付状态 成功支付
			}else{
				hPayOrder.setPay_status("4");//支付状态 成功失败
				hPayOrder.setRespMsg(respMsg);
			}
			hPayOrder.setPay_time(new Date());//支付时间
			hPayOrder.setQuery_id(queryId);//银行交易流水号
			String ammeters = hPayOrder.getElectric_number();//电表号
			String[] electrics = ammeters.split(",");
			for (String electric:electrics){//遍历电表

				JSONObject result = hXTServiceQuery(electric, IP);
				
				log.info("==========支付成功，销账开始，查询=============");
				log.info("----------"+sf.format(new Date())+"------------->"+result);
				
				if("00".equals(result.get("resultCode"))){//获取电表信息成功
					Map<String,String> ammeterMap = new HashMap<String, String>();//欠费电表信息
					Integer fee = Integer.valueOf(result.get("totalFeeStr") == null ? "0" : result.get("totalFeeStr").toString());
					String paymentInfo = result.get("paymentInfo") == null ? "" : result.get("paymentInfo").toString();//中心流水号（销账的时候要用到）
					ammeterMap.put("ammeter",electric);
					ammeterMap.put("totalFree",result.get("totalFeeStr").toString());
					ammeterMap.put("paymentInfo",paymentInfo);
					ammeterList.add(ammeterMap);
					paysum = paysum+fee;//欠费总额累加
				}else{//获取电表信息失败
					respMsg = (String)result.get("resultInfo");
				}

			}
			log.info("-----------paysum------------"+paysum);
			log.info("-----------***------------"+(paysum==Integer.parseInt(merTxnAmt)));
			log.info("-----------respCode------------"+respCode);
			if("00".equals(respCode)){
				if (paysum==Integer.parseInt(merTxnAmt)){//欠费金额与交易支付金额相等，支付订单验证有效，更新订单状态，调用销账接口
					System.out.println("1");
					String tickOff = "0";
					String finalTickOff = "";
					//销账
					for (Map<String,String> ammeterM:ammeterList){//遍历欠费电表
						String ammeter = ammeterM.get("ammeter");
						String totalFree = ammeterM.get("totalFree");
						String paymentInfo = ammeterM.get("paymentInfo");
						JSONObject jsonObject = hXTServicePay(ammeter, IP, queryId, totalFree, settleDate, paymentInfo,Source);
						log.info("-----销账结果-----"+sf.format(new Date())+"------------->"+jsonObject);
						if("00".equals(jsonObject.get("resultCode"))){//销账成功
							tickOff = "1";//销账成功
							//记录子订单
							HSubOrder hSubOrder = new HSubOrder();
							hSubOrder.setO_id(merOrderId);
							hSubOrder.setElectric(ammeter);
							HSubOrder hSubOrder1 = hSubOrderDAO.getHSubOrder(hSubOrder);
							if(hSubOrder1!=null){
								hSubOrder1.setTick_off_status("1");
								hSubOrder1.setTick_off_time(new Date());
								hSubOrderDAO.updateHSubOrder(hSubOrder1);
							}
						}else {//销账失败
							//记录子订单
							HSubOrder hSubOrder = new HSubOrder();
							hSubOrder.setO_id(merOrderId);
							hSubOrder.setElectric(ammeter);
							HSubOrder hSubOrder1 = hSubOrderDAO.getHSubOrder(hSubOrder);
							if(hSubOrder1!=null){
								hSubOrder1.setTick_off_status("0");
								hSubOrderDAO.updateHSubOrder(hSubOrder1);
							}
							finalTickOff = "0";
						}
					}
					if (!"".equals(finalTickOff)){
						tickOff = finalTickOff;
					}
					//进入分润
					if("1".equals(tickOff)){
						hPayOrder = fenrun(hPayOrder,company,electrics,merOrderId,agent,agentAdmin,agentTwo,agentTwoAdmin,servicer,hPayOrderQry,paysum,Source);
					}
					hPayOrder.setTick_off_status(tickOff);//销账失败
					hPayOrder.setTick_off_time(new Date());//销账时间


				}else if(paysum > Integer.parseInt(merTxnAmt)){//欠费金额大于交易支付金额，支付订单无效，更新订单状态
					System.out.println("2");
					hPayOrder.setPay_status("2");//支付状态 短款
					hPayOrder.setPay_time(new Date());//支付时间

				}else if (paysum < Integer.parseInt(merTxnAmt)){//欠费金额小于交易支付金额，支付订单无效，更新订单状态
					hPayOrder.setPay_status("3");//支付状态 长款
					hPayOrder.setPay_time(new Date());//支付时间
				}
			}else{
				hPayOrder.setPay_status("4");//支付状态 失败
				hPayOrder.setRespMsg(respMsg);
			}
			hPayOrderDAO.updateHPayOrder(hPayOrder);//更新订单
			
			
		}
		} catch (ParseException e) {
			log.info("---------error------------"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return hPayOrder;
	}

	public <T> String excleExport(LinkedList<T> list, LinkedList<String> fields, LinkedList<String> titles, Class c, String sheetName,HttpServletRequest request) {
		String path = "";
		if (list != null && list.size() > 0 && fields != null && titles != null && fields.size() == titles.size()) {
			SimpleDateFormat ssf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sssf = new SimpleDateFormat("yyyyMMddHHmmss");
			Date now = new Date();
			String filename = sssf.format(now) + sheetName + ".xls";
			String baseUrl = request.getSession().getServletContext().getRealPath("/");
			String OnputimagePath = baseUrl + "/upload/xls/" + filename;
			
			File file = new File(baseUrl + "/upload/xls/");
			if(!file.exists()){
				file.mkdirs();
			}
			
			path = "/upload/xls/" + filename;
			HSSFWorkbook hssfwork = new HSSFWorkbook();

			HSSFSheet sheet = hssfwork.createSheet(sheetName);
			HSSFCellStyle style = hssfwork.createCellStyle();
			style.setWrapText(true);
			HSSFFont font = hssfwork.createFont();
			HSSFFont headfont = hssfwork.createFont();
			style = hssfwork.createCellStyle();
			font = hssfwork.createFont();
			font.setFontHeightInPoints((short) 11);
			headfont.setFontHeightInPoints((short) 12);
			font.setFontName("宋体");
			headfont.setFontName("宋体");
			font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
			headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			// 生成头部
			// 设置表头
			style.setFont(font);
			HSSFRow row = sheet.createRow((short) 0);
			for (int i = 0; i < titles.size(); i++) {
				sheet.setColumnWidth((short) i, (short) 1500 * titles.get(i).length());
				HSSFCell newcell = row.createCell((short) i);
				newcell.setCellStyle(style); 
				newcell.setCellValue(titles.get(i));
			}
			try {
				for (int i = 0; i < list.size(); i++) {
					HSSFRow newrow = sheet.createRow((short) i + 1);
					Object obj = list.get(i);
					for(int j = 0;j<fields.size();j++){
						HSSFCell newcell = newrow.createCell((short) j);
						newcell.setCellStyle(style); 
						PropertyDescriptor pd = new PropertyDescriptor(fields.get(j), c);
			            //获得get方法
			            Method get = pd.getReadMethod();
			            Object getValue = get.invoke(obj, new Object[]{});
			            String field = fields.get(j).toString();
			            if("state".equals(fields.get(j).toString())||"state"==fields.get(j).toString()||"status".equals(fields.get(j).toString())||"status"==fields.get(j).toString()){
			            	if(getValue!=null){
			            		if("1".equals(getValue.toString())){
			            			newcell.setCellValue("正常");
			            		}else{
			            			newcell.setCellValue("禁用");
			            		}
			            	}else{
			            		newcell.setCellValue("");
			            	}
			            }else if(getValue!=null&&getValue.getClass().equals(Date.class)){
			            	newcell.setCellValue(ssf.format((Date)getValue));
			            }else if("verify_status".equals(fields.get(j).toString())){
			            	if(getValue!=null){
			            		if("1".equals(getValue.toString())){
			            			newcell.setCellValue("通过");
			            		}else if("2".equals(getValue.toString())){
			            			newcell.setCellValue("驳回");
			            		}else{
			            			newcell.setCellValue("待审核");
			            		}
			            	}else{
			            		newcell.setCellValue("");
			            	}
			            }else if("money".equals(fields.get(j).toString())){
			            	if(getValue!=null){
			            		int money = Integer.valueOf(getValue.toString());
			            		String totalFeeStr1 = null;
								if(getValue.toString().length()>2){
									int fen = money%100;
									if(fen>0&&fen<10){
										totalFeeStr1 = money/100 + ".0" + fen;
									}else if(fen>=10){
										totalFeeStr1 = money/100 + "." + fen;
									}else{
										totalFeeStr1 = money/100 + ".00";
									}
								}else if(getValue.toString().length()==1){
									totalFeeStr1 = "0.0"+money;
								}else{
									totalFeeStr1 = "0."+money;
								}
			            		newcell.setCellValue(totalFeeStr1);
			            	}else{
			            		newcell.setCellValue("0.00");
			            	}
			            }else if("express_status".equals(fields.get(j).toString())){
			            	if(getValue!=null){
			            		if("1".equals(getValue.toString())){
			            			newcell.setCellValue("已发送");
			            		}else{
			            			newcell.setCellValue("未发送");
			            		}
			            	}else{
			            		newcell.setCellValue("");
			            	}
			            }else if("mailType".equals(fields.get(j).toString())){
			            	if(getValue!=null){
			            		if("1".equals(getValue.toString())){
			            			newcell.setCellValue("快递");
			            		}else if("2".equals(getValue.toString())){
		            				newcell.setCellValue("挂号信");
			            		}else if("3".equals(getValue.toString())){
			            			newcell.setCellValue("平信");
			            		}else{
			            			newcell.setCellValue("");
			            		}
			            	}else{
			            		newcell.setCellValue("");
			            	}
			            }else if("fp_style".equals(fields.get(j).toString())){
			            	if(getValue!=null){
			            		if("1".equals(getValue.toString())){
			            			newcell.setCellValue("恒信通发票");
			            		}else if("2".equals(getValue.toString())){
			            			newcell.setCellValue("电力增值税普票");
			            		}else if("3".equals(getValue.toString())){
			            			newcell.setCellValue("电力增值税专票");
			            		}else if("4".equals(getValue.toString())){
			            			newcell.setCellValue("电力额定发票");
			            		}else{
			            			newcell.setCellValue("");
			            		}
			            	}else{
			            		newcell.setCellValue("");
			            	}
			            }else if("pay_type".equals(fields.get(j).toString())){
			            	if("1".equals(getValue.toString())){
			            		newcell.setCellValue("银联B2B");
		            		}else if("2".equals(getValue.toString())){
		            			newcell.setCellValue("手机支付");
		            		}
			            }else if("tick_off_status".equals(fields.get(j).toString())){
			            	if(getValue!=null){
			            		if("1".equals(getValue.toString())){
			            			newcell.setCellValue("已销账");
			            		}else{
			            			newcell.setCellValue("未销账");
			            		}
			            	}else{
			            		newcell.setCellValue("");
			            	}
			            }else if("back_fee_status".equals(fields.get(j).toString())){
			            	if(getValue!=null){
			            		if("1".equals(getValue.toString())){
			            			newcell.setCellValue("退费成功");
			            		}else{
			            			newcell.setCellValue("");
			            		}
			            	}else{
			            		newcell.setCellValue("");
			            	}
						}else if("pay_status".equals(fields.get(j).toString())){
							if(getValue!=null){
			            		if("1".equals(getValue.toString())){
			            			newcell.setCellValue("已支付");
			            		}else if("2".equals(getValue.toString())){
			            			newcell.setCellValue("已支付(短款)");
			            		}else if("3".equals(getValue.toString())){
			            			newcell.setCellValue("已支付(长款)");
			            		}else{
			            			newcell.setCellValue("未支付");
			            		}
			            	}else{
			            		newcell.setCellValue("");
			            	}
						}else if("fpExportTime".equals(fields.get(j).toString())){
							newcell.setCellValue(ssf.format(now));
						}
			            else{
			            	newcell.setCellValue(getValue==null?"":getValue.toString());
			            }
					}
				}
				File tmp = new File(OnputimagePath);
				FileOutputStream fOut = new FileOutputStream(OnputimagePath);
				hssfwork.write(fOut);
				fOut.flush();
				fOut.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return path;
	}
	public double udpateAccount(HUserAccount agentAccount,HSubOrder hSubOrder2,String ratio,HPayOrder hPayOrderQry,String electric,String merOrderId,Integer type,HCompany company) throws Exception{
		
		double feeTotal = 0.0;
		
		//分润比例
		BigDecimal profitRate = new BigDecimal(ratio);
		//订单支付金额(电表欠费金额 单位 分)
		BigDecimal totalFee = new BigDecimal(hSubOrder2.getAmount()).divide(new BigDecimal("100"));
//		//税率
//		BigDecimal taxRate = new BigDecimal(FileUploadConstants.TAX_RATE);
//		//税费
//		BigDecimal taxFee = taxRate.multiply(totalFee.multiply(profitRate));
		//分润金额
		BigDecimal subFee = totalFee.multiply(profitRate);//税前
		//账户明细
		HUserAccountDetail detail = new HUserAccountDetail();
		detail.setUserAccountId(agentAccount.getId());
		detail.setType(1);
		detail.setTotalFee(subFee);
		detail.setCreateTime(new Date());
		detail.setRate(Float.valueOf(ratio));
		detail.setOrderId(hPayOrderQry.getO_id());
		detail.setAmmeterNum(electric);
		//子订单
		HSubOrder hSubOrder = new HSubOrder();
		hSubOrder.setO_id(merOrderId);
		hSubOrder.setElectric(electric);
		HSubOrder hSubOrder1 = hSubOrderDAO.getHSubOrder(hSubOrder);
		detail.setOrderDetailId(hSubOrder1.getO_sub_id());
		detail.setOrderDetailMoney(totalFee);
		hUserAccountDetailDao.insertHUserAccountDetail(detail);
		//更新账户
		agentAccount.setTotalFee(agentAccount.getTotalFee().add(subFee));
	    
		feeTotal = subFee.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		
		hUserAccountDao.updateHUserAccount(agentAccount);
		//更新订单详情分润数据
		HAmmeterInfo ammeter = new HAmmeterInfo();
		ammeter.setAmmeter_no(electric);
		ammeter.setDelete_state(1);
		ammeter.setPay_status("1");
		ammeter.setC_id(company.getId());
		ammeter = hAmmeterInfoDao.getHAmmeterInfo(ammeter);
		if(ammeter!=null){
			ammeter.setIs_payed(1);
		}
		hAmmeterInfoDao.updateHAmmeterInfo(ammeter);
		HProfitRatio rate = new HProfitRatio();
		rate.setId(ammeter.getProfit_id());
		rate = hProfitRatioDao.getHProfitRatio(rate);
		switch (type){
		case 1:
			hSubOrder1.setOneFee(subFee.multiply(new BigDecimal("100")).longValue());
			hSubOrder1.setOneRate(profitRate);
			break;
		case 2:
			hSubOrder1.setTwoFee(subFee.multiply(new BigDecimal("100")).longValue());
			hSubOrder1.setTwoRate(profitRate);
			break;
		case 3:
			hSubOrder1.setPersonalFee(subFee.multiply(new BigDecimal("100")).longValue());
			hSubOrder1.setPersonalRate(profitRate);
			break;
		default:
			break;
		}
		hSubOrderDAO.updateHSubOrder(hSubOrder1);
		
		return feeTotal;
	}
	
	public double udpateAccount1(HUserAccount agentAccount,HSubOrder hSubOrder2,String ratio,HPayOrder hPayOrderQry,String electric,String merOrderId,Integer type,HCompany company) throws Exception{
		
		double feeTotal = 0.0;
		
		//分润比例
		BigDecimal profitRate = new BigDecimal(ratio);
		//订单支付金额(电表欠费金额 单位 分)
		BigDecimal totalFee = new BigDecimal(hSubOrder2.getAmount()).divide(new BigDecimal("100"));
//		//税率
//		BigDecimal taxRate = new BigDecimal(FileUploadConstants.TAX_RATE);
//		//税费
//		BigDecimal taxFee = taxRate.multiply(totalFee.multiply(profitRate));
		//分润金额
		BigDecimal subFee = totalFee.multiply(profitRate);//税前
		//账户明细
		HUserAccountDetail detail = new HUserAccountDetail();
		detail.setUserAccountId(agentAccount.getId());
		detail.setType(1);
		detail.setTotalFee(subFee);
		detail.setCreateTime(new Date());
		detail.setRate(Float.valueOf(ratio));
		detail.setOrderId(hPayOrderQry.getO_id());
		detail.setAmmeterNum(electric);
		//子订单
		HSubOrder hSubOrder = new HSubOrder();
		hSubOrder.setO_id(merOrderId);
		hSubOrder.setElectric(electric);
		detail.setStyle(2);
		HSubOrder hSubOrder1 = hSubOrderDAO.getHSubOrder(hSubOrder);
		detail.setOrderDetailId(hSubOrder1.getO_sub_id());
		detail.setOrderDetailMoney(totalFee);
		hUserAccountDetailDao.insertHUserAccountDetail(detail);
		//更新账户
		agentAccount.setTotalFee(agentAccount.getTotalFee().add(subFee));
		
		feeTotal = subFee.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		
		hUserAccountDao.updateHUserAccount(agentAccount);
		//更新订单详情分润数据
		HAmmeterInfo ammeter = new HAmmeterInfo();
		ammeter.setAmmeter_no(electric);
		ammeter.setDelete_state(1);
		ammeter.setPay_status("1");
		ammeter.setC_id(company.getId());
		ammeter = hAmmeterInfoDao.getHAmmeterInfo(ammeter);
		if(ammeter!=null){
			ammeter.setIs_payed(1);
		}
		hAmmeterInfoDao.updateHAmmeterInfo(ammeter);
		HProfitRatio rate = new HProfitRatio();
		rate.setId(ammeter.getProfit_id());
		rate = hProfitRatioDao.getHProfitRatio(rate);
		switch (type){
		case 1:
			hSubOrder1.setOneFee(subFee.multiply(new BigDecimal("100")).longValue());
			hSubOrder1.setOneRate(profitRate);
			break;
		case 2:
			hSubOrder1.setTwoFee(subFee.multiply(new BigDecimal("100")).longValue());
			hSubOrder1.setTwoRate(profitRate);
			break;
		case 3:
			hSubOrder1.setPersonalFee(subFee.multiply(new BigDecimal("100")).longValue());
			hSubOrder1.setPersonalRate(profitRate);
			break;
		default:
			break;
		}
		hSubOrderDAO.updateHSubOrder(hSubOrder1);
		
		return feeTotal;
	}
	//按笔
	public double udpateAccount2(HUserAccount agentAccount,Integer style,double totalFee,HPayOrder hPayOrderQry,String electric,String merOrderId,Integer pay_style) throws Exception{
		System.out.println("--------totalFee-------"+totalFee);
//		double feeTotal = 0.0;
		
		//分润比例
//		BigDecimal profitRate = new BigDecimal(ratio);
//		//订单支付金额(电表欠费金额 单位 分)
		BigDecimal totalFee111 = new BigDecimal(hPayOrderQry.getAmount()).divide(new BigDecimal("100"));
//		//税率
//		BigDecimal taxRate = new BigDecimal(FileUploadConstants.TAX_RATE);
//		//税费
//		BigDecimal taxFee = taxRate.multiply(totalFee.multiply(profitRate));
		//分润金额
//		BigDecimal subFee = totalFee.multiply(profitRate);//税前
		BigDecimal subFee = new BigDecimal(totalFee);
		//账户明细
		HUserAccountDetail detail = new HUserAccountDetail();
		detail.setUserAccountId(agentAccount.getId());
		detail.setType(1);
		detail.setTotalFee(subFee);
		detail.setCreateTime(new Date());
		detail.setRate(null);
		detail.setOrderId(hPayOrderQry.getO_id());
		detail.setAmmeterNum(electric);
		detail.setStyle(style);
		detail.setPay_style(Integer.valueOf(hPayOrderQry.getPay_type()));
		detail.setOrderDetailMoney(totalFee111);
		//子订单
//		HSubOrder hSubOrder = new HSubOrder();
//		hSubOrder.setO_id(merOrderId);
//		hSubOrder.setElectric(electric);
//		HSubOrder hSubOrder1 = hSubOrderDAO.getHSubOrder(hSubOrder);
//		detail.setOrderDetailId(hSubOrder1.getO_sub_id());
//		detail.setOrderDetailMoney(subFee);
		hUserAccountDetailDao.insertHUserAccountDetail(detail);
		//更新账户
		agentAccount.setTotalFee(agentAccount.getTotalFee().add(subFee));
		
		double feeTotal = subFee.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		
		hUserAccountDao.updateHUserAccount(agentAccount);
		
		return feeTotal;
	}
	
	public JSONObject createOrderByCompnay(Integer cId,String IP){
		List<Map> resultList = new ArrayList();
	    HPayOrder hPayOrderQur = new HPayOrder();
        hPayOrderQur.setC_id(cId);
        hPayOrderQur.setPay_status("0");//未支付
        hPayOrderQur.setSub_id(-1);
        HPayOrder findHPayOrder = hPayOrderDAO.findTodayHPayOrder(hPayOrderQur);


        HSubCompany hSubCompanyQry = new HSubCompany();
        hSubCompanyQry.setC_id(cId);
        List<HSubCompany> hSubCompanyList = new ArrayList<HSubCompany>();//字典表
        hSubCompanyQry.setSortColumn("create_time desc");
        hSubCompanyList = hSubCompanyDao.getHSubCompanyBaseList(hSubCompanyQry);
        //校验订单支付金额是否与欠费金额相等
        boolean flag = true;
        if(findHPayOrder!=null){
        	Integer amount = findHPayOrder.getAmount();//订单金额
        	log.info("--------验证订单amount----------->"+amount);
        	flag = checkPayOrderSum(hSubCompanyList,IP,amount);
        }
        if(findHPayOrder!=null && flag){//当天存在支付订单，不重复下单 直接跳转支付页面
        	String oNo = findHPayOrder.getO_no();
        	HPayOrder or = new HPayOrder();
			or.setO_no(oNo);
			or = hPayOrderDAO.getHPayOrder(or);
			int count1 = 0,count2 = 0,count3 = 0;
			if(or!=null){
				//获取单位登记有效电表数
				HAmmeterInfo am = new HAmmeterInfo();
				am.setC_id(or.getC_id());
				am.setPay_status("1");
				am.setDelete_state(1);
				count1 = hAmmeterInfoDao.getHAmmeterInfoListCount(am);//总有效电表数
				am.setProxy_flag(1);
				count2 = hAmmeterInfoDao.getHAmmeterInfoListCount(am);//设置代扣总电表数
				//获取子订单数
				HSubOrder su = new HSubOrder();
				su.setO_id(or.getO_no());
				count3 = hSubOrderDAO.getHSubOrderListCount(su);//实际缴费电表数
			}
        	JSONObject reJson = new JSONObject();
        	reJson.put("allFee", or.getAmount());//总金额 分
			reJson.put("allAmmeterCount", count1);//总有效电表数
			reJson.put("allProxyCount", count2);//设置代扣总电表数
			reJson.put("realProxyCount", count3);//实际缴费电表数
			reJson.put("orderNo", oNo);//订单号
           return reJson;
        }else{//重新生成支付订单
            HCompany hCompany1 = new HCompany();
            hCompany1.setId(cId);
            HCompany hCompany = hCompanyDao.getHCompany(hCompany1);
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
                        hAmmeterInfoQry.setProxy_flag(1);
                        hAmmeterInfos = hAmmeterInfoDao.getHAmmeterInfoBaseList(hAmmeterInfoQry);
                        for (HAmmeterInfo hAmmeterInfo : hAmmeterInfos) {
                            ammeterSum++;
                            String ammeterNo = hAmmeterInfo.getAmmeter_no();//电表号
                            if(hAmmeterInfo.getFp_style()==null){
                            	is_zz = true;
                            }
                            JSONObject result = this.hXTServiceQuery(ammeterNo, IP);
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
//                                            hSubOrder.setO_id(oNo);
                                        hSubOrder.setElectric(ammeterNo);//电表编号
                                        hSubOrder.setAmount(fee);//欠费金额
                                        
                                        HSubCompany hSubCompany = new HSubCompany();
                                        hSubCompany.setS_id(sId);
                                        hSubCompany = hSubCompanyDao.getHSubCompany(hSubCompany);
                                        if(hSubCompany!=null){
                                        	 hSubOrder.setConsignee(hSubCompany.getConsignee());
                                             hSubOrder.setConsignee_address(hSubCompany.getConsignee_address());
                                             hSubOrder.setConsignee_phone(hSubCompany.getConsignee_phone());
                                             hSubOrder.setSub_name(hSubCompany.getSub_name());
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
              		return createOrder(IP,is_zz,feeTotal,cId,hCompany.getYwyId(),subOrderList,sb,null);//解决订单重复问题
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
     * 判断欠费金额
     * @param hSubCompanyList
     * @param IP
     * @param amount
     * @return
     */
    public boolean checkPayOrderSum(List<HSubCompany> hSubCompanyList,String IP,Integer amount){

        boolean flag = false;
        int totalFee = 0;
        
        for (HSubCompany invoce : hSubCompanyList) {
            Map<String, Object> dataMap = new HashMap<String, Object>();//数据
            List<HAmmeterInfo> hAmmeterInfos = new ArrayList<HAmmeterInfo>();//电表信息
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
                hAmmeterInfos = hAmmeterInfoDao.getHAmmeterInfoBaseList(hAmmeterInfoQry);
                for (HAmmeterInfo hAmmeterInfo : hAmmeterInfos) {
                    String ammeterNo = hAmmeterInfo.getAmmeter_no();//电表号

                    JSONObject result = this.hXTServiceQuery(ammeterNo, IP);
                    if ("00".equals(result.get("resultCode"))) {//获取电表信息成功
                    	Integer fee = Integer.valueOf(result.get("totalFeeStr") == null ? "0" : result.get("totalFeeStr").toString());//欠费金额
                        totalFee = totalFee + fee;//欠费总额累加
                    } else {//获取电表信息失败

                    }
                }
            }
        }
        log.info("--------验证订单amount----------->"+amount);
        if(amount.equals(totalFee)){//欠费金额相等
            flag = true;
            log.info("==================>订单欠费金额相等 amount="+amount+"   totalFee="+totalFee);
        }else{
        	log.info("==================>订单欠费金额不相等 amount="+amount+"   totalFee="+totalFee);
        }
        return flag;
    }
//	public static void main(String[] args) {
//		LinkedList<ManageAdminUser> list = new LinkedList<ManageAdminUser>();
//		ManageAdminUser u = new ManageAdminUser();
//		u.setNickName("nick");
//		list.add(u);
//		LinkedList<String> fields = new LinkedList<String>();
//		fields.add("nickName");
//		LinkedList<String> titles = new LinkedList<String>();
//		titles.add("昵称");
//		excleExport(list, fields, titles, ManageAdminUser.class, "1");
//	}
    /**
     * 获取时间
     * @return
     */
    public synchronized String getNowTime(){
       return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }
    /**
     * 生成订单2016-8-21 01:53:02
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
	public synchronized JSONObject createOrder(String IP, boolean is_zz, Integer paysum,Integer cId,Integer ywyId,List<HSubOrder> subOrderList,StringBuffer sb, Integer subId) {
		String oNo = "";
		JSONObject reJson = new JSONObject();
		try{
			// 排序
			Map map = new HashMap();
			map.put("snNamePre", "PO");//编号前缀
			map.put("snName", "PaySN");//编号名称，记录用
			map.put("num", "4");//编号长度
			map.put("sn", "@sn");//编号
			oNo = callProcedureService.callGenerateSnNo(map);//订单编号
			
			HPayOrder hPayOrder = new HPayOrder();//电表支付订单
			String ammeterNums = sb.toString().substring(0, sb.length() - 1);
			hPayOrder.setO_no(oNo);
			hPayOrder.setPay_status("0");
			hPayOrder.setPay_ip(IP);
			if(is_zz){
				hPayOrder.setIs_zz(1);
			}else{
				hPayOrder.setIs_zz(0);
			}
			System.out.println("-----------paysum-------------"+paysum);
			hPayOrder.setAmount(paysum);
			hPayOrder.setC_id(cId);
			//单位信息
			HCompany comp = new HCompany();
			comp.setId(cId);
			comp = hCompanyDao.getHCompany(comp);
			if(comp!=null){
				hPayOrder.setC_name(comp.getName());
			}
			hPayOrder.setElectric_number(ammeterNums);
			hPayOrder.setYw_id(ywyId==null?null:ywyId);
			hPayOrder.setCreate_time(new Date());
			hPayOrder.setSub_id(subId);
			HPayOrder hPayOrderQur = new HPayOrder();
			if(subId==null){
				hPayOrderQur.setPay_status("0");
				hPayOrderQur.setC_id(cId);
				hPayOrderQur.setSub_id(subId);
			}else{
				hPayOrderQur.setC_id(cId);
				hPayOrderQur.setPay_status("0");//未支付
				hPayOrderQur.setSub_id(-1);
			}
			HPayOrder findHPayOrder = hPayOrderDAO.findTodayHPayOrder(hPayOrderQur);
			if(findHPayOrder==null){//不存在订单
				//插入代理数据
				HCompany company = new HCompany();
				company.setId(cId);
				company = hCompanyDao.getHCompany(company);
				if(company!=null){
//					hPayOrder.setOneAgentName(company.getOneAgentName());
//					hPayOrder.setOneAgentOpenId(company.getOneAgentOpenId());
//					hPayOrder.setTwoAgentName(company.getTwoAgentName());
//					hPayOrder.setTwoAgentOpenID(company.getTwoAgentOpenID());
//					hPayOrder.setServicerId(company.getServicerId());
//					hPayOrder.setServicerName(company.getServicerName());
					if(company.getOneAgentOpenId()!=null){
						  ManageAdminUser manageAdminUser = new ManageAdminUser();
	  					  manageAdminUser.setOneAgentOpenId(company.getOneAgentOpenId());
	  					  manageAdminUser.setRoleId(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
	  					  ManageAdminUser user = manageAdminUserDao.getManageAdminUser(manageAdminUser);
							//获取一级机构
						  HAgent hAgent = new HAgent();
						  hAgent.setOpenId(company.getOneAgentOpenId());
						  hAgent = hAgentDao.getHAgent(hAgent);
						  if(hAgent!=null&&hAgent.getStatus()==1&&hAgent.getCheck_status()==1&&user!=null&&user.getState()==1){
							  hPayOrder.setOneAgentName(company.getOneAgentName());
							  hPayOrder.setOneAgentOpenId(company.getOneAgentOpenId());
						  }
					}
					if(company.getTwoAgentOpenID()!=null){
					  ManageAdminUser manageAdminUser2 = new ManageAdminUser();
	               	  manageAdminUser2.setTwoAgentOpenId(company.getTwoAgentOpenID());
	               	  manageAdminUser2.setRoleId(Integer.valueOf(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID));
	   				  ManageAdminUser user2 = manageAdminUserDao.getManageAdminUser(manageAdminUser2);
						//获取二级机构
	  				  HAgentTwo hAgentTwo = new HAgentTwo();
	  				  hAgentTwo.setOpenId(company.getTwoAgentOpenID());
	  				  hAgentTwo = hAgentTwoDao.getHAgentTwo(hAgentTwo);
	  				  if(hAgentTwo!=null&&hAgentTwo.getStatus()==1&&hAgentTwo.getCheck_status()==1&&user2!=null&&user2.getState()==1){
	  					hPayOrder.setTwoAgentName(company.getTwoAgentName());
	  					hPayOrder.setTwoAgentOpenID(company.getTwoAgentOpenID());
	  				  }
					}
					if(company!=null&&company.getServicerId()!=null){
	              	  ManageAdminUser manageAdminUser3 = new ManageAdminUser();
	  				  manageAdminUser3.setAdminId(company.getServicerId());
	  				  ManageAdminUser user3 = manageAdminUserDao.getManageAdminUser(manageAdminUser3);
	  				  if(user3!=null&&user3.getState()==1){
	  					hPayOrder.setServicerId(company.getServicerId());
	  					hPayOrder.setServicerName(company.getServicerName());
	  				  }
	                }
				}
				hPayOrderDAO.insertHPayOrder(hPayOrder);//插入订单数据
				for (HSubOrder hSubOrder:subOrderList){
					hSubOrder.setO_id(oNo);
					hSubOrderDAO.insertHSubOrder(hSubOrder);//插入子订单数据
				}
			}else{
				log.info(oNo+"-----------findHPayOrder.getAmount()---------------->"+findHPayOrder.getAmount());
				log.info(oNo+"--------paysum-------------->"+paysum);
				log.info(oNo+"--------paysum111-------------->"+findHPayOrder.getAmount().equals(paysum));
				if(!findHPayOrder.getAmount().equals(paysum)){//金额不相等
					hPayOrderDAO.insertHPayOrder(hPayOrder);//插入订单数据
					for (HSubOrder hSubOrder:subOrderList){
						hSubOrder.setO_id(hPayOrder.getO_no());
						hSubOrderDAO.insertHSubOrder(hSubOrder);//插入子订单数据
					}
				}
			}
			//根据订单号获取返回数据
			if(findHPayOrder!=null&&findHPayOrder.getAmount().equals(paysum)){
				oNo = findHPayOrder.getO_no();
			}
			HPayOrder or = new HPayOrder();
			or.setO_no(oNo);
			or = hPayOrderDAO.getHPayOrder(or);
			int count1 = 0,count2 = 0,count3 = 0;
			if(or!=null){
				//获取单位登记有效电表数
				HAmmeterInfo am = new HAmmeterInfo();
				am.setC_id(or.getC_id());
				am.setPay_status("1");
				am.setDelete_state(1);
				count1 = hAmmeterInfoDao.getHAmmeterInfoListCount(am);//总有效电表数
				am.setProxy_flag(1);
				count2 = hAmmeterInfoDao.getHAmmeterInfoListCount(am);//设置代扣总电表数
				//获取子订单数
				HSubOrder su = new HSubOrder();
				su.setO_id(or.getO_no());
				count3 = hSubOrderDAO.getHSubOrderListCount(su);//实际缴费电表数
			}
			reJson.put("allFee", or.getAmount());//总金额 分
			reJson.put("allAmmeterCount", count1);//总有效电表数
			reJson.put("allProxyCount", count2);//设置代扣总电表数
			reJson.put("realProxyCount", count3);//实际缴费电表数
			reJson.put("orderNo", oNo);//订单号
			
		}catch (Exception e){
			e.printStackTrace();
		}
		return reJson;
	}
	
	
	/**
	 * 包装协议
	 * 
	 * @param param
	 * @return
	 */
	private String getXml(Map<String, Object> param) {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		sb.append("<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"");
		sb.append("	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">");
		sb.append("	<soap:Body>");
		sb.append("		<HXTServiceQuery xmlns=\"http://www.chinaepay.com/\">");

		if (param != null) {
			for (Map.Entry<String, Object> entry : param.entrySet()) {
				sb.append("			<" + entry.getKey() + ">" + entry.getValue() + "</" + entry.getKey() + ">");
			}
		}
		sb.append("		</HXTServiceQuery>");
		sb.append("	</soap:Body>");
		sb.append("</soap:Envelope>");
		return sb.toString();
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
	
	public HPayOrder fenrun(HPayOrder hPayOrder,HCompany company,String[] electrics,String merOrderId,HAgent agent,ManageAdminUser agentAdmin,HAgentTwo agentTwo,ManageAdminUser agentTwoAdmin,ManageAdminUser servicer,HPayOrder hPayOrderQry,int paysum,String Source){
		double feeTotal1 = 0.0;
		double feeTotal2 = 0.0;
		double feeTotal3 = 0.0;
		String toOpenId = null;
		String toTwoOpenId = null;
		int trans_count = hPayOrderDAO.getTransCountByCompany(company.getId());
		if(trans_count>0){
			hPayOrder.setTrans_count(trans_count+1);
		}else{
			hPayOrder.setTrans_count(1);
		}
		//判断客户费率标识
		Integer rateFlag = company.getRateFlag();
		HPayGuli hPayGuliOne = new HPayGuli();
		HPayGuli hPayGuliTwo = new HPayGuli();
		HPayGuli hPayGuliThree = new HPayGuli();
		String electric1 = null;
		for (String electric:electrics){//遍历电表
			if(electric1==null){
				electric1 = electric;
			}else{
				electric1 = electric1 + "," + electric;
			}
		}
		//查询鼓励金
		if(agentTwo!=null){
			if("ACT1".equals(Source)){
				hPayGuliTwo.setId(agentTwo.getGl_sj_id());
				log.info("------------agentTwo-----------"+agentTwo.getGl_sj_id());
				if(agentTwo.getGl_sj_id()!=null){
					hPayGuliTwo = hPayGuliDAO.getHPayGuli(hPayGuliTwo);
				}
			}else{
				hPayGuliTwo.setId(agentTwo.getGl_yl_id());
				log.info("------------agentTwo-----------"+agentTwo.getGl_yl_id());
				if(agentTwo.getGl_yl_id()!=null){
					hPayGuliTwo = hPayGuliDAO.getHPayGuli(hPayGuliTwo);
				}
			}
			
			
		}
		try{
			//是否过期
			if(hPayGuliTwo!=null&&hPayGuliTwo.getId()!=null&&hPayGuliTwo.getStartTime().before(new Date())&&hPayGuliTwo.getEndTime().after(new Date())){
				if(agentTwo!=null&&agentTwo.getStatus()==1&&agentTwo.getCheck_status()==1&&agentTwoAdmin!=null&&agentTwoAdmin.getState()==1){
					HUserAccount agentAccount = new HUserAccount();
					agentAccount.setTwoAgentOpenId(agentTwo.getOpenId());
					agentAccount.setRole_id(Integer.valueOf(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID));
					agentAccount = hUserAccountDao.getHUserAccount(agentAccount);
					if(agentAccount==null){
						//创建账户
						agentAccount = new HUserAccount();
						agentAccount.setOpenId(agentTwoAdmin.getOpenId());
						agentAccount.setTotalFee(new BigDecimal("0.00"));
						agentAccount.setCreateTime(new Date());
						agentAccount.setStatus(1);
						agentAccount.setOneAgentOpenId(agentTwo.getCreate_openId());
						agentAccount.setOneAgentName(agentTwoAdmin.getOneAgentName());
						agentAccount.setTwoAgentOpenId(agentTwo.getOpenId());
						agentAccount.setTwoAgentName(agentTwo.getName());
						agentAccount.setNickName(agentTwo.getName());
						agentAccount.setPhone(agentTwo.getMobile1());
						agentAccount.setMobile(agentTwo.getMobile2());
						agentAccount.setRole_id(Integer.valueOf(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID));
						hUserAccountDao.insertHUserAccount(agentAccount);
					}
					if(hPayGuliTwo.getType()==1){//按笔
//						if(hPayGuliTwo.getFee()>0){
							feeTotal2 = feeTotal2 + udpateAccount2(agentAccount, 1, hPayGuliTwo.getFee(), hPayOrder, electric1, merOrderId, null);
//						}
					}else{
//						if(hPayGuliTwo.getRate()>0){
							//分润比例
							BigDecimal profitRate = new BigDecimal(hPayGuliTwo.getRate());
							BigDecimal totalFee = new BigDecimal(hPayOrder.getAmount()).divide(new BigDecimal("100"));
							//分润金额
							BigDecimal subFee = totalFee.multiply(profitRate);//税前
							feeTotal2 = feeTotal2 + udpateAccount2(agentAccount, 2, subFee.doubleValue(), hPayOrder, electric1, merOrderId, null);
//						}
					}
					toTwoOpenId = agentTwoAdmin.getOpenId();
					log.info("------------toTwoOpenId----1-------"+toTwoOpenId);
				}
			}
			if(rateFlag==1){
				//查询鼓励金客户经理
				if(agent!=null){
					if("ACT1".equals(Source)){
						if(agent.getGl_sj_id()!=null){
							hPayGuliOne.setId(agent.getGl_sj_id());
							hPayGuliOne = hPayGuliDAO.getHPayGuli(hPayGuliOne);
						}
					}else{
						if(agent.getGl_yl_id()!=null){
							hPayGuliOne.setId(agent.getGl_yl_id());
							hPayGuliOne = hPayGuliDAO.getHPayGuli(hPayGuliOne);
						}
					}
					
				}
				
				//是否过期
				if(hPayGuliOne!=null&&hPayGuliOne.getId()!=null&&hPayGuliOne.getStartTime().before(new Date())&&hPayGuliOne.getEndTime().after(new Date())){
					if(agent!=null&&agent.getStatus()==1&&agent.getCheck_status()==1&&agentAdmin!=null&&agentAdmin.getState()==1){
						HUserAccount agentAccount = new HUserAccount();
						agentAccount.setOneAgentOpenId(agent.getOpenId());
						agentAccount.setRole_id(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
						agentAccount = hUserAccountDao.getHUserAccount(agentAccount);
						if(agentAccount==null){
							//创建账户
							agentAccount = new HUserAccount();
							agentAccount.setOpenId(agentAdmin.getOpenId());
							agentAccount.setTotalFee(new BigDecimal("0.00"));
							agentAccount.setCreateTime(new Date());
							agentAccount.setStatus(1);
							agentAccount.setOneAgentOpenId(agent.getOpenId());
							agentAccount.setOneAgentName(agent.getName());
							agentAccount.setNickName(agent.getName());
							agentAccount.setPhone(agent.getMobile1());
							agentAccount.setMobile(agent.getMobile2());
							agentAccount.setRole_id(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
							hUserAccountDao.insertHUserAccount(agentAccount);
						}
						toOpenId = agentAdmin.getOpenId();
						log.info("------------toOpenId----1-------"+toOpenId);
						if(hPayGuliOne.getType()==1){//按笔
//							if(hPayGuliOne.getFee()>0){
								feeTotal1 = feeTotal1 + udpateAccount2(agentAccount, 1, hPayGuliOne.getFee(), hPayOrder, electric1, merOrderId, null);
//							}
						}else{
//							if(hPayGuliOne.getRate()>0){
								//分润比例
								BigDecimal profitRate = new BigDecimal(hPayGuliOne.getRate());
								BigDecimal totalFee = new BigDecimal(hPayOrder.getAmount()).divide(new BigDecimal("100"));
								//分润金额
								BigDecimal subFee = totalFee.multiply(profitRate);//税前
								feeTotal1 = feeTotal1 + udpateAccount2(agentAccount, 2, subFee.doubleValue(), hPayOrder, electric1, merOrderId, null);
//							}
						}
					}
				}
				if(servicer!=null){
					//查询鼓励金支持人
					if("ACT1".equals(Source)){
						if(servicer.getGl_sj_id()!=null){
							hPayGuliThree.setId(servicer.getGl_sj_id());
							hPayGuliThree = hPayGuliDAO.getHPayGuli(hPayGuliThree);
						}
					}else{
						if(servicer.getGl_yl_id()!=null){
							hPayGuliThree.setId(servicer.getGl_yl_id());
							hPayGuliThree = hPayGuliDAO.getHPayGuli(hPayGuliThree);
						}
					}
					
				}
				
				//是否过期
				if(hPayGuliThree!=null&&hPayGuliThree.getId()!=null&&hPayGuliThree.getStartTime().before(new Date())&&hPayGuliThree.getEndTime().after(new Date())){
					if(servicer!=null&&servicer.getState()==1){
						HUserAccount agentAccount = new HUserAccount();
						agentAccount.setServicerId(servicer.getAdminId());
						agentAccount.setRole_id(Integer.valueOf(FileUploadConstants.SERVICER_MANAGE_ROLEID));
						agentAccount = hUserAccountDao.getHUserAccount(agentAccount);
						if(agentAccount==null){
							//创建账户
							agentAccount = new HUserAccount();
							agentAccount.setServicerId(servicer.getAdminId());
							agentAccount.setOpenId(servicer.getOpenId());
							agentAccount.setTotalFee(new BigDecimal("0.00"));
							agentAccount.setCreateTime(new Date());
							agentAccount.setStatus(1);
							agentAccount.setServicerName(servicer.getRealName());
							agentAccount.setNickName(servicer.getNickName());
							agentAccount.setPhone(servicer.getPhone());
							agentAccount.setMobile(servicer.getMobile());
							agentAccount.setRole_id(Integer.valueOf(FileUploadConstants.SERVICER_MANAGE_ROLEID));
							hUserAccountDao.insertHUserAccount(agentAccount);
						}
						if(hPayGuliThree.getType()==1){//按笔
//							if(hPayGuliThree.getFee()>0){
								feeTotal3 = feeTotal3 + udpateAccount2(agentAccount, 1, hPayGuliThree.getFee(), hPayOrder, electric1, merOrderId, null);
//							}
						}else{
//							if(hPayGuliThree.getRate()>0){
								//分润比例
								BigDecimal profitRate = new BigDecimal(hPayGuliThree.getRate());
								BigDecimal totalFee = new BigDecimal(hPayOrder.getAmount()).divide(new BigDecimal("100"));
								//分润金额
								BigDecimal subFee = totalFee.multiply(profitRate);//税前
								feeTotal3 = feeTotal3 + udpateAccount2(agentAccount, 2, subFee.doubleValue(), hPayOrder, electric1, merOrderId, null);
//							}
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		hPayOrder.setOneRate(new BigDecimal(feeTotal1));
		hPayOrder.setTwoRate(new BigDecimal(feeTotal2));
		hPayOrder.setServerRate(new BigDecimal(feeTotal3));
		BigDecimal bg1 = new BigDecimal(feeTotal1);
        double f1 = bg1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        BigDecimal bg2 = new BigDecimal(feeTotal2);
        double f2 = bg2.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        BigDecimal bg3 = new BigDecimal(feeTotal3);
        double f3 = bg3.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        if(agent!=null&&agent.getStatus()==1&&agent.getCheck_status()==1&&agentAdmin!=null&&agentAdmin.getState()==1){
        	huseraccountService.sendCZTempltMsg(toOpenId,this.getMoney(paysum), hPayOrderQry.getO_no(), f1+"", company.getContact_name(), company.getContact_phone(), company.getId(), company.getName());
        }
        if(agentTwo!=null&&agentTwo.getStatus()==1&&agentTwo.getCheck_status()==1&&agentTwoAdmin!=null&&agentTwoAdmin.getState()==1){
        	huseraccountService.sendCZTempltMsg(toTwoOpenId,this.getMoney(paysum), hPayOrderQry.getO_no(), f2+"", company.getContact_name(), company.getContact_phone(), company.getId(), company.getName());
        }
        if(servicer!=null&&servicer.getState()==1){
        	huseraccountService.sendCZTempltMsg(servicer.getOpenId(),this.getMoney(paysum), hPayOrderQry.getO_no(), f3+"", company.getContact_name(), company.getContact_phone(), company.getId(), company.getName());
        }
        return hPayOrder;
	}
}
