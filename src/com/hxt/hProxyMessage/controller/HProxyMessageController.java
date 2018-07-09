package com.hxt.hProxyMessage.controller;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hxt.hAgent.model.HAgent;
import com.hxt.hAgentTwo.model.HAgentTwo;
import com.hxt.hAmmeterInfo.model.HAmmeterInfo;
import com.hxt.hAmmeterInfo.service.HAmmeterInfoService;
import com.hxt.hBankInfo.model.HBankInfo;
import com.hxt.hBankInfo.service.HBankInfoService;
import com.hxt.hCommon.service.HCommonService;
import com.hxt.hCompany.model.HCompany;
import com.hxt.hCompany.service.HCompanyService;
import com.hxt.hMessageLog.model.HMessageLog;
import com.hxt.hMessageLog.service.HMessageLogService;
import com.hxt.hPay.model.HPay;
import com.hxt.hPay.service.HPayService;
import com.hxt.hPayOrder.model.HPayOrder;
import com.hxt.hPayOrder.service.HPayOrderService;
import com.hxt.hProxyMessage.model.HProxyMessage;
import com.hxt.hProxyMessage.model.Location;
import com.hxt.hProxyMessage.service.HProxyMessageService;
import com.hxt.hProxyMessageLog.model.HProxyMessageLog;
import com.hxt.hProxyMessageLog.service.HProxyMessageLogService;
import com.hxt.hProxySerial.model.HProxySerial;
import com.hxt.hProxySerial.service.HProxySerialService;
import com.hxt.hUserAccount.model.HUserAccount;
import com.hxt.hUserAccount.service.HUserAccountService;
import com.hxt.hVerificate.model.HVerificate;
import com.hxt.openingBank.model.OpeningBank;
import com.hxt.openingBank.service.OpeningBankService;
import com.sys.manageAdminUser.model.ManageAdminUser;
import com.sys.manageAdminUser.service.ManageAdminUserService;
import com.base.payment.service.PaymentClient;
import com.base.payment.service.PaymentClientImpl;
import com.base.utils.CommUtil;
import com.base.utils.Common;
import com.base.utils.FileUploadConstants;
import com.base.utils.MD5;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.utils.SendMsgUtil;
import com.base.utils.SessionName;
import com.base.controller.BaseController;
/**
 * @author	wuzhenwei
 * @time	2016年09月08日 18:43:55
 */
@Controller
@RequestMapping("/hProxyMessage")
public class HProxyMessageController extends BaseController
{
	
	private static Logger logger = Logger.getLogger(HProxyMessageController.class); //Logger
	
	@Autowired
	private HProxyMessageService hproxymessageService = null;
	@Autowired
	private ManageAdminUserService manageadminuserService = null;// 用户
	@Autowired
	private HCompanyService hcompanyService = null;
	@Autowired
	private OpeningBankService openingbankService = null;
	@Autowired
	private HPayOrderService hpayorderService = null;
	@Autowired
	private HCommonService hCommonService;
	@Autowired
	private HUserAccountService huseraccountService = null;
	@Autowired
	private HPayService hpayService = null;
	@Autowired
	private HProxySerialService hproxyserialService = null;
	@Autowired
	private HProxyMessageLogService hproxymessagelogService = null;
	@Autowired
	private HBankInfoService hbankinfoService = null;
	@Autowired
	private HMessageLogService hmessagelogService = null;
	@Autowired
	private HAmmeterInfoService hammeterinfoService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hProxyMessage/hProxyMessageIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hProxyMessage/hProxyMessageAdd";
	}
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HProxyMessage hproxymessage1 = new HProxyMessage();
		hproxymessage1.setId(id);
		HProxyMessage hproxymessage = hproxymessageService.getHProxyMessage(hproxymessage1);
		model.addAttribute("hproxymessage", hproxymessage);
		HCompany hCompany = new HCompany();
		hCompany.setId(hproxymessage.getCId());
		hCompany = hcompanyService.getHCompany(hCompany);
//		OpeningBank openingBank = new OpeningBank();
//		openingBank.setNBKCODE(hproxymessage.getBank_number());
//		openingBank = openingbankService.getOpeningBank(openingBank);
		HBankInfo hBankInfo = new HBankInfo();
		hBankInfo.setBankNum(hproxymessage.getBank_number());
		hBankInfo = hbankinfoService.getHBankInfo(hBankInfo);
		
		model.addAttribute("hCompany", hCompany);
		model.addAttribute("openingBank", hBankInfo);
		
		return "/hProxyMessage/hProxyMessageUpdate";
	}
	@RequestMapping(value = "/toShenhe/{id}", method = RequestMethod.GET)
	public String toShenhe(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HProxyMessage hproxymessage1 = new HProxyMessage();
		hproxymessage1.setId(id);
		HProxyMessage hproxymessage = hproxymessageService.getHProxyMessage(hproxymessage1);
		model.addAttribute("hproxymessage", hproxymessage);
		HCompany hCompany = new HCompany();
		hCompany.setId(hproxymessage.getCId());
		hCompany = hcompanyService.getHCompany(hCompany);
//		OpeningBank openingBank = new OpeningBank();
//		openingBank.setNBKCODE(hproxymessage.getBank_number());
//		openingBank = openingbankService.getOpeningBank(openingBank);
		
		HBankInfo hBankInfo = new HBankInfo();
		hBankInfo.setBankNum(hproxymessage.getBank_number());
		hBankInfo = hbankinfoService.getHBankInfo(hBankInfo);
		
		model.addAttribute("hCompany", hCompany);
		model.addAttribute("openingBank", hBankInfo);
		
		return "/hProxyMessage/shenhe";
	}
	@RequestMapping(value = "/tozzShenhe/{id}", method = RequestMethod.GET)
	public String tozzShenhe(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HProxyMessage hproxymessage1 = new HProxyMessage();
		hproxymessage1.setId(id);
		HProxyMessage hproxymessage = hproxymessageService.getHProxyMessage(hproxymessage1);
		model.addAttribute("hproxymessage", hproxymessage);
		HCompany hCompany = new HCompany();
		hCompany.setId(hproxymessage.getCId());
		hCompany = hcompanyService.getHCompany(hCompany);
//		OpeningBank openingBank = new OpeningBank();
//		openingBank.setNBKCODE(hproxymessage.getBank_number());
//		openingBank = openingbankService.getOpeningBank(openingBank);.
		HBankInfo hBankInfo = new HBankInfo();
		hBankInfo.setBankNum(hproxymessage.getBank_number());
		hBankInfo = hbankinfoService.getHBankInfo(hBankInfo);
		
		model.addAttribute("hCompany", hCompany);
		model.addAttribute("openingBank", hBankInfo);
		
		return "/hProxyMessage/zzshenhe";
	}
	@RequestMapping(value = "/tobgShenhe/{id}", method = RequestMethod.GET)
	public String tobgShenhe(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HProxyMessage hproxymessage1 = new HProxyMessage();
		hproxymessage1.setId(id);
		HProxyMessage hproxymessage = hproxymessageService.getHProxyMessage(hproxymessage1);
		model.addAttribute("hproxymessage", hproxymessage);
		HCompany hCompany = new HCompany();
		hCompany.setId(hproxymessage.getCId());
		hCompany = hcompanyService.getHCompany(hCompany);
//		OpeningBank openingBank = new OpeningBank();
//		openingBank.setNBKCODE(hproxymessage.getBank_number());
//		openingBank = openingbankService.getOpeningBank(openingBank);
		
		HBankInfo hBankInfo = new HBankInfo();
		hBankInfo.setBankNum(hproxymessage.getBank_number());
		hBankInfo = hbankinfoService.getHBankInfo(hBankInfo);
		
		model.addAttribute("hCompany", hCompany);
		model.addAttribute("openingBank", hBankInfo);
		
		return "/hProxyMessage/bgshenhe";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHProxyMessageList", method = RequestMethod.GET)
	public String getHProxyMessageList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HProxyMessage hproxymessage = new HProxyMessage();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hproxymessage.setId(id);
			
			String proxyName = RequestHandler.getString(request, "proxyName");
			hproxymessage.setProxyName(proxyName);
			
			String proxyPhone = RequestHandler.getString(request, "proxyPhone");
			hproxymessage.setProxyPhone(proxyPhone);
			
			Date createYime = RequestHandler.getDate(request, "createYime");
			hproxymessage.setCreateYime(createYime);
			
			String contractNumber = RequestHandler.getString(request, "contractNumber");
			hproxymessage.setContractNumber(contractNumber);
			
			Date contractStartTime = RequestHandler.getDate(request, "contractStartTime");
			hproxymessage.setContractStartTime(contractStartTime);
			
			Date contractEndTime = RequestHandler.getDate(request, "contractEndTime");
			hproxymessage.setContractEndTime(contractEndTime);
			
			Integer remindStartDate = RequestHandler.getInteger(request, "remindStartDate");
			hproxymessage.setRemindStartDate(remindStartDate);
			
			Integer remindEndDate = RequestHandler.getInteger(request, "remindEndDate");
			hproxymessage.setRemindEndDate(remindEndDate);
			
			String bank_number = RequestHandler.getString(request, "bank_number");
			hproxymessage.setBank_number(bank_number);
			
			String info = RequestHandler.getString(request, "info");
			hproxymessage.setInfo(info);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hproxymessage.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hproxymessage.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hproxymessage.setRemark3(remark3);
			
			Integer cId = RequestHandler.getInteger(request, "cId");
			hproxymessage.setCId(cId);
			
			Integer userId = RequestHandler.getInteger(request, "userId");
			hproxymessage.setUserId(userId);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hproxymessage.setState(state);
			
			Integer checkState = RequestHandler.getInteger(request, "checkState");
			hproxymessage.setCheckState(checkState);
			
			String userNumber = RequestHandler.getString(request, "userNumber");
			hproxymessage.setUserNumber(userNumber);
			
			String proxyAddress = RequestHandler.getString(request, "proxyAddress");
			hproxymessage.setProxyAddress(proxyAddress);
			
			String proxyCode = RequestHandler.getString(request, "proxyCode");
			hproxymessage.setProxyCode(proxyCode);
			
			String payBankNumber = RequestHandler.getString(request, "payBankNumber");
			hproxymessage.setPayBankNumber(payBankNumber);
			
			String payName = RequestHandler.getString(request, "payName");
			hproxymessage.setPayName(payName);
			
			Integer payCardStyle = RequestHandler.getInteger(request, "payCardStyle");
			hproxymessage.setPayCardStyle(payCardStyle);
			
			String payCard = RequestHandler.getString(request, "payCard");
			hproxymessage.setPayCard(payCard);
			
			String payPhoneNumber = RequestHandler.getString(request, "payPhoneNumber");
			hproxymessage.setPayPhoneNumber(payPhoneNumber);
			
			String payMail = RequestHandler.getString(request, "payMail");
			hproxymessage.setPayMail(payMail);
			

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hproxymessage.setRowStart(from);
			hproxymessage.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hproxymessage.setSortColumn(sortColumn);
			
			int hproxymessageListCount = hproxymessageService.getHProxyMessageListCount(hproxymessage);
			ResponseList<HProxyMessage> hproxymessageList = null;
			if ( hproxymessageListCount > 0 )
			{
				hproxymessageList = hproxymessageService.getHProxyMessageList(hproxymessage);
			} else
			{
				hproxymessageList = new ResponseList<HProxyMessage>();
			}
			// 设置数据总数
			hproxymessageList.setTotalResults(hproxymessageListCount);
			
			writeSuccessMsg("ok", hproxymessageList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getHProxyMessageBaseList", method = RequestMethod.GET)
	public String getHProxyMessageBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HProxyMessage hproxymessage = new HProxyMessage();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hproxymessage.setId(id);
			
			String proxyName = RequestHandler.getString(request, "proxyName");
			hproxymessage.setProxyName(proxyName);
			
			String proxyPhone = RequestHandler.getString(request, "proxyPhone");
			hproxymessage.setProxyPhone(proxyPhone);
			
			Date createYime = RequestHandler.getDate(request, "createYime");
			hproxymessage.setCreateYime(createYime);
			
			String contractNumber = RequestHandler.getString(request, "contractNumber");
			hproxymessage.setContractNumber(contractNumber);
			
			Date contractStartTime = RequestHandler.getDate(request, "contractStartTime");
			hproxymessage.setContractStartTime(contractStartTime);
			
			Date contractEndTime = RequestHandler.getDate(request, "contractEndTime");
			hproxymessage.setContractEndTime(contractEndTime);
			
			Integer remindStartDate = RequestHandler.getInteger(request, "remindStartDate");
			hproxymessage.setRemindStartDate(remindStartDate);
			
			Integer remindEndDate = RequestHandler.getInteger(request, "remindEndDate");
			hproxymessage.setRemindEndDate(remindEndDate);
			
			String bank_number = RequestHandler.getString(request, "bank_number");
			hproxymessage.setBank_number(bank_number);
			
			String info = RequestHandler.getString(request, "info");
			hproxymessage.setInfo(info);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hproxymessage.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hproxymessage.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hproxymessage.setRemark3(remark3);
			
			Integer cId = RequestHandler.getInteger(request, "cId");
			hproxymessage.setCId(cId);
			
			Integer userId = RequestHandler.getInteger(request, "userId");
			hproxymessage.setUserId(userId);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hproxymessage.setState(state);
			
			String userNumber = RequestHandler.getString(request, "userNumber");
			hproxymessage.setUserNumber(userNumber);
			
			String proxyAddress = RequestHandler.getString(request, "proxyAddress");
			hproxymessage.setProxyAddress(proxyAddress);
			
			String proxyCode = RequestHandler.getString(request, "proxyCode");
			hproxymessage.setProxyCode(proxyCode);
			
			String payBankNumber = RequestHandler.getString(request, "payBankNumber");
			hproxymessage.setPayBankNumber(payBankNumber);
			
			String payName = RequestHandler.getString(request, "payName");
			hproxymessage.setPayName(payName);
			
			Integer payCardStyle = RequestHandler.getInteger(request, "payCardStyle");
			hproxymessage.setPayCardStyle(payCardStyle);
			
			String payCard = RequestHandler.getString(request, "payCard");
			hproxymessage.setPayCard(payCard);
			
			String payPhoneNumber = RequestHandler.getString(request, "payPhoneNumber");
			hproxymessage.setPayPhoneNumber(payPhoneNumber);
			
			String payMail = RequestHandler.getString(request, "payMail");
			hproxymessage.setPayMail(payMail);
			
			List<HProxyMessage> hproxymessageList = hproxymessageService.getHProxyMessageBaseList(hproxymessage);
		
			writeSuccessMsg("ok", hproxymessageList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHProxyMessage", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HProxyMessage hproxymessage = new HProxyMessage();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hproxymessage.setId(id);
			String proxyName = RequestHandler.getString(request, "proxyName");
			hproxymessage.setProxyName(proxyName);
			String proxyPhone = RequestHandler.getString(request, "proxyPhone");
			hproxymessage.setProxyPhone(proxyPhone);
			Date createYime = RequestHandler.getDate(request, "createYime");
			hproxymessage.setCreateYime(new Date());
			String contractNumber = RequestHandler.getString(request, "contractNumber");
			hproxymessage.setContractNumber(contractNumber);
			Date contractStartTime = RequestHandler.getDate(request, "contractStartTime");
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(contractStartTime!=null){
				String start = sf.format(contractStartTime) + " 00:00:00";
				hproxymessage.setContractStartTime(sf1.parse(start));
			}
			Date contractEndTime = RequestHandler.getDate(request, "contractEndTime");
			if(contractEndTime!=null){
				String end = sf.format(contractEndTime) + " 23:59:59";
				hproxymessage.setContractEndTime(sf1.parse(end));
			}
			Integer remindStartDate = RequestHandler.getInteger(request, "remindStartDate");
			hproxymessage.setRemindStartDate(remindStartDate);
			Integer remindEndDate = RequestHandler.getInteger(request, "remindEndDate");
			hproxymessage.setRemindEndDate(remindEndDate);
			String bank_number = RequestHandler.getString(request, "bank_number");
			hproxymessage.setBank_number(bank_number);
			String info = RequestHandler.getString(request, "info");
			hproxymessage.setInfo(info);
			String remark1 = RequestHandler.getString(request, "remark1");
			hproxymessage.setRemark1(remark1);
			String remark2 = RequestHandler.getString(request, "remark2");
			hproxymessage.setRemark2(remark2);
			String remark3 = RequestHandler.getString(request, "remark3");
			hproxymessage.setRemark3(remark3);
			Integer cId = RequestHandler.getInteger(request, "cId");
			hproxymessage.setCId(cId);
			Integer userId = RequestHandler.getInteger(request, "userId");
			hproxymessage.setUserId(userId);
			Integer state = RequestHandler.getInteger(request, "state");
			hproxymessage.setState(state);
			Integer sex = RequestHandler.getInteger(request, "sex");
			hproxymessage.setSex(sex);
			String userNumber = RequestHandler.getString(request, "userNumber");
			hproxymessage.setUserNumber(userNumber);
			String proxyAddress = RequestHandler.getString(request, "proxyAddress");
			hproxymessage.setProxyAddress(proxyAddress);
			String proxyCode = RequestHandler.getString(request, "proxyCode");
			hproxymessage.setProxyCode(proxyCode);
			String payBankNumber = RequestHandler.getString(request, "payBankNumber");
			hproxymessage.setPayBankNumber(payBankNumber);
			String payName = RequestHandler.getString(request, "payName");
			hproxymessage.setPayName(payName);
			Integer payCardStyle = RequestHandler.getInteger(request, "payCardStyle");
			hproxymessage.setPayCardStyle(payCardStyle);
			String payCard = RequestHandler.getString(request, "payCard");
			hproxymessage.setPayCard(payCard);
			String payPhoneNumber = RequestHandler.getString(request, "payPhoneNumber");
			hproxymessage.setPayPhoneNumber(payPhoneNumber);
			String payMail = RequestHandler.getString(request, "payMail");
			hproxymessage.setPayMail(payMail);
			
			PaymentClient client = new PaymentClientImpl();
			String returnCode = client.add(hproxymessage.getContractNumber(), hproxymessage.getUserNumber(), hproxymessage.getPayName(), hproxymessage.getPayPhoneNumber(), "00100", hproxymessage.getBank_number(), hproxymessage.getPayBankNumber(), hproxymessage.getPayAccountName());
			if("CP1I1000".equals(returnCode)){
				int ids= hproxymessageService.insertHProxyMessage(hproxymessage);
				if(ids>0){
//					int uid = manageadminuserService.saveAgentManage1(proxyPhone, proxyName, null, null, Integer.valueOf(FileUploadConstants.PROXY_ROLEID),Integer.valueOf(getSession(request, SessionName.ADMIN_USER_ID).toString()),null,null,cId,payCard);
//					hproxymessage.setId(ids);
//					hproxymessage.setUserId(uid);
//					hproxymessageService.updateHProxyMessage(hproxymessage);
					
//					HProxyMessage hproxymessage1 = new HProxyMessage();
//					hproxymessage1.setId(ids);
//					hproxymessage1 = hproxymessageService.getHProxyMessage(hproxymessage1);
//					hproxymessage1.
				}
				
				writeSuccessMsg("添加成功", null, response);
			}else if("CS".equals(returnCode)){
				writeErrorMsg("发送数据超时", null, response);
			}else{
				writeErrorMsg("系统错误", null, response);
			}
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	
	@RequestMapping(value = "/updateHProxyMessage", method = RequestMethod.POST)
	public String updateHProxyMessage(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HProxyMessage hproxymessage = new HProxyMessage();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hproxymessage.setId(id);
			
			String proxyName = RequestHandler.getString(request, "proxyName");
			hproxymessage.setProxyName(proxyName);
			
			String proxyPhone = RequestHandler.getString(request, "proxyPhone");
			hproxymessage.setProxyPhone(proxyPhone);
			
			Date createYime = RequestHandler.getDate(request, "createYime");
			hproxymessage.setCreateYime(createYime);
			
			Integer sex = RequestHandler.getInteger(request, "sex");
			hproxymessage.setSex(sex);
			
			String contractNumber = RequestHandler.getString(request, "contractNumber");
			hproxymessage.setContractNumber(contractNumber);
			
			Date contractStartTime = RequestHandler.getDate(request, "contractStartTime");
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(contractStartTime!=null){
				String start = sf.format(contractStartTime) + " 00:00:00";
				hproxymessage.setContractStartTime(sf1.parse(start));
			}
			Date contractEndTime = RequestHandler.getDate(request, "contractEndTime");
			if(contractEndTime!=null){
				String end = sf.format(contractEndTime) + " 23:59:59";
				hproxymessage.setContractEndTime(sf1.parse(end));
			}
			
			Integer remindStartDate = RequestHandler.getInteger(request, "remindStartDate");
			hproxymessage.setRemindStartDate(remindStartDate);
			
			Integer remindEndDate = RequestHandler.getInteger(request, "remindEndDate");
			hproxymessage.setRemindEndDate(remindEndDate);
			
			String bank_number = RequestHandler.getString(request, "bank_number");
			hproxymessage.setBank_number(bank_number);
			
			String info = RequestHandler.getString(request, "info");
			hproxymessage.setInfo(info);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hproxymessage.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hproxymessage.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hproxymessage.setRemark3(remark3);
			
			Integer cId = RequestHandler.getInteger(request, "cId");
			hproxymessage.setCId(cId);
			
			Integer userId = RequestHandler.getInteger(request, "userId");
			hproxymessage.setUserId(userId);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hproxymessage.setState(state);
			
			String userNumber = RequestHandler.getString(request, "userNumber");
			hproxymessage.setUserNumber(userNumber);
			
			String proxyAddress = RequestHandler.getString(request, "proxyAddress");
			hproxymessage.setProxyAddress(proxyAddress);
			
			String proxyCode = RequestHandler.getString(request, "proxyCode");
			hproxymessage.setProxyCode(proxyCode);
			
			String payBankNumber = RequestHandler.getString(request, "payBankNumber");
			hproxymessage.setPayBankNumber(payBankNumber);
			
			String payName = RequestHandler.getString(request, "payName");
			hproxymessage.setPayName(payName);
			
			Integer payCardStyle = RequestHandler.getInteger(request, "payCardStyle");
			hproxymessage.setPayCardStyle(payCardStyle);
			
			String payCard = RequestHandler.getString(request, "payCard");
			hproxymessage.setPayCard(payCard);
			
			String payPhoneNumber = RequestHandler.getString(request, "payPhoneNumber");
			hproxymessage.setPayPhoneNumber(payPhoneNumber);
			
			String payMail = RequestHandler.getString(request, "payMail");
			hproxymessage.setPayMail(payMail);

			HProxyMessage hproxymessage1 = new HProxyMessage();
			hproxymessage1.setId(id);
			hproxymessage1 = hproxymessageService.getHProxyMessage(hproxymessage1);
			
//			PaymentClient client = new PaymentClientImpl();
//			String returnCode = client.update(hproxymessage1.getContractNumber(), hproxymessage1.getUserNumber(), hproxymessage.getPayName(), "00100", fkNo, fkAccount, hproxymessage.getPayName());
//			if("CP1I1000".equals(returnCode)){
				int ids = hproxymessageService.updateHProxyMessage(hproxymessage);
//				if(ids>0){
//					
//					ManageAdminUser manageadminuser = new ManageAdminUser();
//					manageadminuser.setAdminId(hproxymessage1.getUserId());
//					manageadminuser = manageadminuserService.getManageAdminUser(manageadminuser);
//					if(manageadminuser!=null){
//						manageadminuser.setAdminName(proxyPhone);
//						String passwd = proxyPhone.substring(proxyPhone.length()-6, proxyPhone.length());
//						manageadminuser.setPasswd(new MD5().getMD5ofStr(passwd));
//						manageadminuser.setNickName(proxyName);
//						manageadminuser.setRealName(proxyName);
//						manageadminuser.setCompanyId(cId);
//						manageadminuserService.updateManageAdminUser(manageadminuser);
//					}
//				}
				
				
				writeSuccessMsg("修改成功", null, response);
//			}else{
//				writeErrorMsg("修改失败", null, response);
//			}
			

			
		} catch (Exception e)
		{
			e.printStackTrace();
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHProxyMessage", method = RequestMethod.POST)
	public String removeHProxyMessage(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HProxyMessage hproxymessage = new HProxyMessage();
			Integer id = RequestHandler.getInteger(request, "id");
			hproxymessage.setId(id);
			hproxymessage = hproxymessageService.getHProxyMessage(hproxymessage);

			//删除序号
			HProxySerial hProxySerial = new HProxySerial();
			hProxySerial.setUserNumber(hproxymessage.getUserNumber());
			List<HProxySerial> list = hproxyserialService.getHProxySerialBaseList(hProxySerial);
			if(list!=null&&list.size()>0){
				for(HProxySerial ps:list){
					hproxyserialService.removeHProxySerial(ps);
				}
			}
			
//			PaymentClient client = new PaymentClientImpl();
//			String returnCode = client.revoke(hproxymessage.getContractNumber(), hproxymessage.getUserNumber(), hproxymessage.getPayName(), "00100", hproxymessage.getBank_number(), hproxymessage.getPayBankNumber(), hproxymessage.getPayName());
//			if("CP1I1000".equals(returnCode)){
//				
			if(hproxymessage.getUserId()!=null){
				ManageAdminUser manageadminuser = new ManageAdminUser();
				manageadminuser.setAdminId(hproxymessage.getUserId());
				manageadminuserService.removeManageAdminUser(manageadminuser);
			}
			hproxymessageService.removeHProxyMessage(hproxymessage);
				
			writeSuccessMsg("删除成功", null, response);
//			}else{
//				writeErrorMsg("删除失败", null, response);
//			}
			
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHProxyMessage", method = RequestMethod.POST)
	public String removeAllHProxyMessage(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						HProxyMessage hProxyMessage = new HProxyMessage();
						hProxyMessage.setId(Integer.valueOf(id));
						
						hProxyMessage = hproxymessageService.getHProxyMessage(hProxyMessage);
						
						//删除序号
						HProxySerial hProxySerial = new HProxySerial();
						hProxySerial.setUserNumber(hProxyMessage.getUserNumber());
						List<HProxySerial> list = hproxyserialService.getHProxySerialBaseList(hProxySerial);
						if(list!=null&&list.size()>0){
							for(HProxySerial ps:list){
								hproxyserialService.removeHProxySerial(ps);
							}
						}
						
//						PaymentClient client = new PaymentClientImpl();
//						String returnCode = client.revoke(hProxyMessage.getContractNumber(), hProxyMessage.getUserNumber(), hProxyMessage.getPayName(), "00100", hProxyMessage.getBank_number(), hProxyMessage.getPayBankNumber(), hProxyMessage.getPayName());
//						if("CP1I1000".equals(returnCode)){
						if(hProxyMessage.getUserId()!=null){
							ManageAdminUser manageadminuser = new ManageAdminUser();
							manageadminuser.setAdminId(hProxyMessage.getUserId());
							manageadminuserService.removeManageAdminUser(manageadminuser);
						}
//							
						hproxymessageService.removeHProxyMessage(hProxyMessage);
//						}
						
						
					}
				}
			}
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/checkProxyPhone", method = RequestMethod.POST)
	public String checkMobile(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try{
			String mobile = RequestHandler.getString(request, "mobile");
			Integer id = RequestHandler.getInteger(request, "id");
			HProxyMessage hProxyMessage = new HProxyMessage();
			hProxyMessage.setId(id);
			hProxyMessage.setProxyPhone(mobile);
			int count = hproxymessageService.checkProxyPhone(hProxyMessage);
			if(count>0){
				writeErrorMsg("error", null, response);
			}else{
				ManageAdminUser manageadminuser = new ManageAdminUser();
				if(id!=null){
					HProxyMessage hProxyMessage1 = new HProxyMessage();
					hProxyMessage1.setId(id);
					hProxyMessage1 = hproxymessageService.getHProxyMessage(hProxyMessage1);
					manageadminuser.setAdminId(hProxyMessage1.getUserId());
				}
				manageadminuser.setAdminName(mobile);
				int count1 = manageadminuserService.checkAdminName(manageadminuser);
				if(count1>0){
					writeErrorMsg("error", null, response);
				}else{
					writeSuccessMsg("ok", null, response);
				}
			}
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	
	@RequestMapping(value = "/verify", method = RequestMethod.POST)
	public String verify(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String phone = RequestHandler.getString(request, "phone");
			String pwd = RequestHandler.getString(request, "pwd");
			String openId = RequestHandler.getString(request, "openId");
			if(StringUtils.isNotBlank(phone) && StringUtils.isNotBlank(pwd) && StringUtils.isNotBlank(openId)){
				
			}else{
				writeSuccessMsg("-4", null, response);
				return null;
			}
			//判断微信是否绑定
			ManageAdminUser manageAdminUser1 = new ManageAdminUser();
			manageAdminUser1.setOpenId(openId);
			int count = manageadminuserService.getManageAdminUserListCount(manageAdminUser1);
			if(count==0){
				ManageAdminUser manageAdminUser = new ManageAdminUser();
				manageAdminUser.setAdminName(phone);
				manageAdminUser.setPasswd(MD5.getMD5ofStr(pwd));
				manageAdminUser.setState(1);
				manageAdminUser.setRoleId(Integer.valueOf(FileUploadConstants.PROXY_ROLEID));
				manageAdminUser = manageadminuserService.getAdminUserByLogin(manageAdminUser);
				if(manageAdminUser!=null){
					if(manageAdminUser.getState()==1){
						//成功
						manageAdminUser.setLoginIP(Common.getLocalIp());
						manageAdminUser.setLastLogin(new Date());
						manageAdminUser.setOpenId(openId);
						manageAdminUser.setState(1);
						manageadminuserService.updateManageAdminUser(manageAdminUser);
						writeSuccessMsg("1", null, response);
					}else{
						// 用户状态错误
						writeSuccessMsg("-2", null, response);
					}
				}else{
					// 没有用户
					writeSuccessMsg("-1", null, response);
				}
			}else{
				// 已经绑定
				writeSuccessMsg("-5", null, response);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/showProxy", method = RequestMethod.GET)
	public String showProxy(HttpServletRequest request, HttpServletResponse response, Model model){
		String url = "/public/payMsg1";
		try{
			ManageAdminUser user = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			HProxyMessage hProxyMessage1 = new HProxyMessage();
			hProxyMessage1.setCId(user.getCompanyId());
			hProxyMessage1.setState(1);
			hProxyMessage1 = hproxymessageService.getHProxyMessage(hProxyMessage1);
			if(hProxyMessage1!=null){
				if(hProxyMessage1.getCheckState()==null){
					if(StringUtils.isNotBlank(hProxyMessage1.getHetongUrl())){
						url = "redirect:/hProxyMessage/toPayMsg2?cId="+user.getCompanyId()+"&proxyMessageId="+hProxyMessage1.getId();
					}else{
						url = "/public/payMsg1";
					}
				}else if(hProxyMessage1.getCheckState()==1||hProxyMessage1.getCheckState()==7){
					url = "/public/payMsgSuccess";
				}else if(hProxyMessage1.getCheckState()==4){
					url = "/public/payOver3";
				}else if(hProxyMessage1.getCheckState()==2||hProxyMessage1.getCheckState()==0){
					url = "/public/payMsg4";
				}else if(hProxyMessage1.getCheckState()==5||hProxyMessage1.getCheckState()==3){
					url = "/public/payOver3";
				}else if(hProxyMessage1.getCheckState()==8){
					url = "/public/payMsg4";
				}else if(hProxyMessage1.getCheckState()==6){
					url = "/public/payMsg4";
				} 
				model.addAttribute("hProxyMessage", hProxyMessage1);
			}else{
				HPay hPay = new HPay();
				hPay = hpayService.getHPay(hPay);
				model.addAttribute("hPay", hPay);
				url = "/public/whatPay";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		model.addAttribute("left_nav", "proxy");
		return url;
	}
	
	/**
	 * 调用代扣接口
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/proxyPay", method = RequestMethod.POST)
	public String proxyPay(HttpServletRequest request, HttpServletResponse response, Model model){
		Integer proxyId = RequestHandler.getInteger(request, "proxyId");
		String orderNum = RequestHandler.getString(request, "orderNum");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		try{
			//查询代扣信息
			HProxyMessage hProxyMessage = new HProxyMessage();
			hProxyMessage.setId(proxyId);
			hProxyMessage = hproxymessageService.getHProxyMessage(hProxyMessage);
			if(hProxyMessage!=null&&hProxyMessage.getId()>0){
				//
				ManageAdminUser user = new ManageAdminUser();
				user.setAdminId(hProxyMessage.getUserId());
				user = manageadminuserService.getManageAdminUser(user);
				//查询订单信息
				HPayOrder hPayOrder = new HPayOrder();
				hPayOrder.setO_no(orderNum);
				hPayOrder = hpayorderService.getHPayOrder(hPayOrder);
				if(hPayOrder!=null&&hPayOrder.getO_id()>0){
					//订单金额
					Long fee = Long.valueOf(hPayOrder.getAmount());
					PaymentClient client = new PaymentClientImpl();
					String sqe = client.getSQE();
					String returnCode = client.daishou(hProxyMessage.getUserNumber(), FileUploadConstants.SF_BANK_NUMBER, FileUploadConstants.SF_BANK_ACCOUNT, hProxyMessage.getBank_number(), null, hProxyMessage.getPayBankNumber(), hProxyMessage.getPayName(), "00100", hProxyMessage.getContractNumber(), "CNY", fee+"",sqe);// 交易序号n16);
					if("CT1I1000".equals(returnCode)){//成功，进入销账流程
						if(user!=null&&StringUtils.isNotBlank(user.getOpenId())){
							huseraccountService.sendProxyTempltSuccessMsg(user.getOpenId(), hProxyMessage.getSex(), hProxyMessage.getPayName(), hPayOrder.getO_no(), fee);
						}
						Map<String, String> valideData = new HashMap<String, String>();
						valideData.put("merOrderId", hPayOrder.getO_no());//商户订单号
						valideData.put("merTxnAmt", fee+"");//交易金额
						valideData.put("merTxnTime", sdf.format(new Date()));//交易时间
						valideData.put("queryId", sqe);//银行交易流水号
						valideData.put("settleDate", sdf.format(new Date()).substring(4, 8));//清算日期
						valideData.put("respCode", "00");//支付成功
						hCommonService.tickOffPayOrder(valideData, "127.0.0.1");
					}else if("CS".equals(returnCode)){//超时，发冲正
						if(user!=null&&StringUtils.isNotBlank(user.getOpenId())){
							huseraccountService.sendProxyTempltFailMsg(user.getOpenId(), hProxyMessage.getSex(), hProxyMessage.getPayName(), hPayOrder.getO_no(), fee,"超时");
						}
						String dataSource = "100301" + autoFill(FileUploadConstants.ORGANIZATION_CODE, 12) + sdf.format(new Date()).substring(0, 8) + sqe;
						String returnCode1 = client.correct(hProxyMessage.getBank_number(), null, dataSource);
						logger.info("---------冲正---------->"+hPayOrder.getO_no()+"--------------"+returnCode1);
					}else{//其他发冲正
						if(user!=null&&StringUtils.isNotBlank(user.getOpenId())){
							huseraccountService.sendProxyTempltFailMsg(user.getOpenId(), hProxyMessage.getSex(), hProxyMessage.getPayName(), hPayOrder.getO_no(), fee,returnCode);
						}
						String dataSource = "100301" + autoFill(FileUploadConstants.ORGANIZATION_CODE, 12) + sdf.format(new Date()).substring(0, 8) + sqe;
						String returnCode1 = client.correct(hProxyMessage.getBank_number(), null, dataSource);
						logger.info("---------冲正---------->"+hPayOrder.getO_no()+"--------------"+returnCode1);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 自动补充空格
	 * 
	 * @param s
	 * @param num
	 * @return
	 */
	private String autoFill(String s, int num) {
		if(s == null)
			s = "";
		int i = length(s);
		if (i < num) {
			return s + getSpace(num - i);
		} else {
			return s;
		}
	}
	
	/**
	 * 获取空格
	 * 
	 * @param count
	 *            数量
	 * @return
	 */
	private String getSpace(int count) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < count; i++) {
			sb.append(" ");
		}
		return sb.toString();
	}
	
	public static boolean isLetter(char c) {
		int k = 0x80;
		return c / k == 0 ? true : false;
	}
	
	/**
	 * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1
	 * 
	 * @param String
	 *            s 需要得到长度的字符串
	 * @return int 得到的字符串长度
	 */
	public static int length(String s) {
		if (s == null)
			return 0;
		char[] c = s.toCharArray();
		int len = 0;
		for (int i = 0; i < c.length; i++) {
			len++;
			if (!isLetter(c[i])) {
				len++;
			}
		}
		return len;
	}
	
	@RequestMapping(value = "/shenhe", method = RequestMethod.POST)
	public String shenhe(HttpServletRequest request, HttpServletResponse response, Model model){
		
		try{
			
			Integer id = RequestHandler.getInteger(request, "id");
			Integer checkState = RequestHandler.getInteger(request, "checkState");
			String msg = RequestHandler.getString(request, "msg");
			HProxyMessage hproxymessage = new HProxyMessage();
			hproxymessage.setId(id);
			hproxymessage = hproxymessageService.getHProxyMessage(hproxymessage);
			
			if(hproxymessage!=null&&checkState==1){
				//查询是否存在登录账号
				if(hproxymessage.getUserId()!=null){
					ManageAdminUser manageadminuser = new ManageAdminUser();
					manageadminuser.setAdminId(hproxymessage.getUserId());
					manageadminuser = manageadminuserService.getManageAdminUser1(manageadminuser);
					if(manageadminuser!=null){
						ManageAdminUser manageadminuser1 = new ManageAdminUser();
						manageadminuser1.setAdminId(manageadminuser.getAdminId());
			    		manageadminuser1.setAdminName(hproxymessage.getPayPhoneNumber()+"8");
			    		int count = manageadminuserService.checkAdminName(manageadminuser1);// 用户名验证
			    		if(count==0){
			    			PaymentClient client = new PaymentClientImpl();
							String returnCode = client.add(hproxymessage.getContractNumber(), hproxymessage.getUserNumber(), hproxymessage.getProxyName(), hproxymessage.getPayPhoneNumber(), "00100", hproxymessage.getBank_number(), hproxymessage.getPayBankNumber(), hproxymessage.getPayAccountName());
							String returnCode111 = "";
							if(StringUtils.isNotBlank(returnCode)&&returnCode.length()>3){
								returnCode111 = returnCode.substring(returnCode.length()-3, returnCode.length());
							}
							if("000".equals(returnCode111)){
								manageadminuser.setAdminName(hproxymessage.getPayPhoneNumber()+"8");
				    			String passwd = hproxymessage.getPayCard().substring( hproxymessage.getPayCard().length()-6,  hproxymessage.getPayCard().length());
				    			manageadminuser.setPasswd(new MD5().getMD5ofStr(passwd));
				    			manageadminuserService.updateManageAdminUser(manageadminuser);
				    			hproxymessage.setCheckState(checkState);
								hproxymessage.setMsg(msg);
								hproxymessageService.updateHProxyMessage(hproxymessage);
								String content = hproxymessage.getPayPhoneNumber()+"8" + "，您单位在企业缴费平台上申请的手机缴费服务已通过审核，登录账号为："+hproxymessage.getPayPhoneNumber()+"8"+"，密码为您身份证号的后六位，关注“恒信通企业服务”微信公众号后，即可通过手机为您单位缴费啦，详情可咨询010-96199.";
				    			int s = SendMsgUtil.sendMsg(hproxymessage.getPayPhoneNumber(),content);
//				    			if(s>0){
//				    				HMessageLog hMessageLog = new HMessageLog();
//				    				hMessageLog.setContent(content);
//				    				hMessageLog.setCreateTime(new Date());
//				    				hMessageLog.setPhone(hproxymessage.getPayPhoneNumber());
//				    				hMessageLog.setIp(this.getIpAddr(request));
//				    				hmessagelogService.insertHMessageLog(hMessageLog);
//				    			}
								writeSuccessMsg("审核成功", null, response);
							}else if("CS".equals(returnCode)){
								writeErrorMsg("发送数据超时", null, response);
							}else{
								writeErrorMsg("审核失败，错误代码"+returnCode, null, response);
							}
			    		}else{
			    			writeErrorMsg("客户手机号在系统中已注册，审核失败", null, response);
			    		}
					}else{
						
						
							PaymentClient client = new PaymentClientImpl();
							String returnCode = client.add(hproxymessage.getContractNumber(), hproxymessage.getUserNumber(), hproxymessage.getProxyName(), hproxymessage.getPayPhoneNumber(), "00100", hproxymessage.getBank_number(), hproxymessage.getPayBankNumber(), hproxymessage.getPayAccountName());
							String returnCode111 = "";
							if(StringUtils.isNotBlank(returnCode)&&returnCode.length()>3){
								returnCode111 = returnCode.substring(returnCode.length()-3, returnCode.length());
							}
							if("000".equals(returnCode111)){
								hproxymessage.setCheckState(checkState);
								hproxymessage.setMsg(msg);
								int ids= hproxymessageService.updateHProxyMessage(hproxymessage);
								if(ids>0){
									int uid = manageadminuserService.saveAgentManage1(hproxymessage.getPayPhoneNumber(), hproxymessage.getProxyName(), null, null, Integer.valueOf(FileUploadConstants.PROXY_ROLEID),Integer.valueOf(getSession(request, SessionName.ADMIN_USER_ID).toString()),null,null,hproxymessage.getcId(),hproxymessage.getPayCard());
									if(uid>0){
										HProxyMessage hproxymessage1 = new HProxyMessage();
										hproxymessage1.setId(id);
										hproxymessage1.setUserId(uid);
										hproxymessageService.updateHProxyMessage(hproxymessage1);
										writeSuccessMsg("审核成功", null, response);
									}else{
										writeErrorMsg("客户手机号在系统中已注册，审核失败", null, response);
									}
									
								}else{
									writeErrorMsg("审核失败", null, response);
								}
							}else if("CS".equals(returnCode)){
								writeErrorMsg("发送数据超时", null, response);
							}else{
								writeErrorMsg("审核失败，错误代码"+returnCode, null, response);
							}
						
					}
				}else{
					
					
					PaymentClient client = new PaymentClientImpl();
					String returnCode = client.add(hproxymessage.getContractNumber(), hproxymessage.getUserNumber(), hproxymessage.getProxyName(), hproxymessage.getPayPhoneNumber(), "00100", hproxymessage.getBank_number(), hproxymessage.getPayBankNumber(), hproxymessage.getPayAccountName());
					String returnCode111 = "";
					if(StringUtils.isNotBlank(returnCode)&&returnCode.length()>3){
						returnCode111 = returnCode.substring(returnCode.length()-3, returnCode.length());
					}
					if("000".equals(returnCode111)){
						hproxymessage.setCheckState(checkState);
						hproxymessage.setMsg(msg);
						int ids= hproxymessageService.updateHProxyMessage(hproxymessage);
						if(ids>0){
							int uid = manageadminuserService.saveAgentManage1(hproxymessage.getPayPhoneNumber(), hproxymessage.getProxyName(), null, null, Integer.valueOf(FileUploadConstants.PROXY_ROLEID),Integer.valueOf(getSession(request, SessionName.ADMIN_USER_ID).toString()),null,null,hproxymessage.getcId(),hproxymessage.getPayCard());
							if(uid>0){
								HProxyMessage hproxymessage1 = new HProxyMessage();
								hproxymessage1.setId(id);
								hproxymessage1.setUserId(uid);
								hproxymessageService.updateHProxyMessage(hproxymessage1);
//								String content = hproxymessage.getPayPhoneNumber()+"8" + "，您单位在企业缴费平台上申请的手机缴费服务已通过审核，登录账号为："+hproxymessage.getPayPhoneNumber()+"8"+"，密码为您身份证号的后六位，关注“恒信通企业服务”微信公众号后，即可通过手机为您单位缴费啦，详情可咨询010-96199.";
//				    			int s = SendMsgUtil.sendMsg(hproxymessage.getPayPhoneNumber(),content);
								writeSuccessMsg("审核成功", null, response);
							}else{
								writeErrorMsg("客户手机号在系统中已注册，审核失败", null, response);
							}
							
						}else{
							writeErrorMsg("审核失败", null, response);
						}
					}else if("CS".equals(returnCode)){
						writeErrorMsg("发送数据超时", null, response);
					}else{
						writeErrorMsg("审核失败，错误代码"+returnCode, null, response);
					}
					
				}
				
			}else if(hproxymessage!=null&&(checkState==2||checkState==5||checkState==8)){
				HProxyMessage hproxymessage2 = new HProxyMessage();
				hproxymessage2.setId(id);
				hproxymessage2.setCheckState(checkState);
				hproxymessage2.setMsg(msg);
				int ids= hproxymessageService.updateHProxyMessage(hproxymessage2);
				if(ids>0){
					if(checkState==2){
						HProxyMessage hproxymessage1 = new HProxyMessage();
						hproxymessage1.setId(id);
						hproxymessage1.setHetongUrl("1");
						hproxymessageService.updateHProxyMessageBH(hproxymessage1);
						String content = hproxymessage.getProxyName() + "，您在企业缴费平台的手机缴费服务开通申请没有被通过，原因是："+msg+"，您可登录http://qiye.chinaepay.com修改申请资料重新申请开通，感谢您的支持。";
						SendMsgUtil.sendMsg(hproxymessage.getPayPhoneNumber(),content);
					}else if(checkState==5){
						HProxyMessage hproxymessage1 = new HProxyMessage();
						hproxymessage1.setId(id);
						hproxymessage1.setChexiaoUrl("1");
						hproxymessageService.updateHProxyMessageBH(hproxymessage1);
						String content = hproxymessage.getProxyName() + "，您在企业缴费平台的手机缴费服务中止申请没有被通过，原因为："+msg+"，详情可拨打010-96199咨询，感谢您的支持。";
						SendMsgUtil.sendMsg(hproxymessage.getPayPhoneNumber(),content);
					}else if(checkState==8){
						HProxyMessage hproxymessage1 = new HProxyMessage();
						hproxymessage1.setId(id);
						hproxymessage1.setBiangengUrl("1");
						hproxymessageService.updateHProxyMessageBH(hproxymessage1);
						String content = hproxymessage.getProxyName() + "，您在企业缴费平台的手机缴费服务更改资料申请没有被通过，原因为："+msg+",感谢您的支持。";
						SendMsgUtil.sendMsg(hproxymessage.getPayPhoneNumber(),content);
					}
					writeSuccessMsg("操作成功", null, response);
				}
			}else if(hproxymessage!=null&&checkState==4){//撤销合同
				PaymentClient client = new PaymentClientImpl();
				String returnCode = client.revoke(hproxymessage.getContractNumber(), hproxymessage.getUserNumber(), hproxymessage.getProxyName(), "00100", hproxymessage.getBank_number(), hproxymessage.getPayBankNumber(), hproxymessage.getPayAccountName());
				String returnCode111 = "";
				if(StringUtils.isNotBlank(returnCode)&&returnCode.length()>3){
					returnCode111 = returnCode.substring(returnCode.length()-3, returnCode.length());
				}
				if("000".equals(returnCode111)){
					hproxymessage.setCheckState(checkState);
					int ids= hproxymessageService.updateHProxyMessage(hproxymessage);
					if(ids>0&&hproxymessage.getUserId()!=null){
						ManageAdminUser manageadminuser = new ManageAdminUser();
						manageadminuser.setAdminId(hproxymessage.getUserId());
						manageadminuserService.removeManageAdminUser(manageadminuser);
						hproxymessageService.updateHProxyMessageZZ(hproxymessage);
						//操作缴费号
						HAmmeterInfo ammeterInfo = new HAmmeterInfo();
						ammeterInfo.setC_id(hproxymessage.getcId());
						List<HAmmeterInfo> listHAmmeterInfo = hammeterinfoService.getHAmmeterInfoBaseList(ammeterInfo);
						if(listHAmmeterInfo!=null&&listHAmmeterInfo.size()>0){
							for(HAmmeterInfo am:listHAmmeterInfo){
								am.setProxy_flag(0);
								hammeterinfoService.updateHAmmeterInfo(am);
							}
						}
						//删除用户编号
						HProxySerial hProxySerial = new HProxySerial();
			    		hProxySerial.setUserNumber(hproxymessage.getUserNumber());
			    		hProxySerial = hproxyserialService.getHProxySerial(hProxySerial);
			    		if(hProxySerial!=null){
			    			hproxyserialService.removeHProxySerial(hProxySerial);
			    		}
						String content = hproxymessage.getProxyName() + "，您在企业缴费平台的手机缴费服务中止申请已通过审核。如您想继续使用，可登录http://qiye.chinaepay.com重新开通本服务，感谢您的支持。";
						int s = SendMsgUtil.sendMsg(hproxymessage.getPayPhoneNumber(),content);
						writeSuccessMsg("操作成功", null, response);
					}else{
						writeErrorMsg("系统错误", null, response);
					}
//						manageadminuserService.removeManageAdminUser(manageadminuser);
					//hproxymessageService.removeHProxyMessage(hproxymessage);
				}else{
					writeErrorMsg("终止失败，错误代码："+returnCode, null, response);
				}
			}else if(hproxymessage!=null&&checkState==7){//变更审核
				ManageAdminUser manageadminuser = new ManageAdminUser();
				manageadminuser.setAdminId(hproxymessage.getUserId());
				manageadminuser = manageadminuserService.getManageAdminUser1(manageadminuser);
				if(manageadminuser!=null){
					ManageAdminUser manageadminuser1 = new ManageAdminUser();
					manageadminuser1.setAdminId(manageadminuser.getAdminId());
		    		manageadminuser1.setAdminName(hproxymessage.getPayPhoneNumber()+"8");
		    		int count = manageadminuserService.checkAdminName(manageadminuser1);// 用户名验证
		    		if(count==0){
		    			PaymentClient client = new PaymentClientImpl();
						String returnCode = client.update(hproxymessage.getContractNumber(), hproxymessage.getUserNumber(), hproxymessage.getProxyName(), hproxymessage.getPayPhoneNumber(), "00100", hproxymessage.getBank_number(), hproxymessage.getPayBankNumber(), hproxymessage.getPayAccountName());
						String returnCode111 = "";
						if(StringUtils.isNotBlank(returnCode)&&returnCode.length()>3){
							returnCode111 = returnCode.substring(returnCode.length()-3, returnCode.length());
						}
						if("000".equals(returnCode111)){
		    				manageadminuser.setAdminName(hproxymessage.getPayPhoneNumber()+"8");
    		    			String passwd = hproxymessage.getPayCard().substring( hproxymessage.getPayCard().length()-6,  hproxymessage.getPayCard().length());
    		    			manageadminuser.setPasswd(new MD5().getMD5ofStr(passwd));
    		    			manageadminuserService.updateManageAdminUser(manageadminuser);
    		    			hproxymessage.setCheckState(checkState);
    						hproxymessage.setMsg(msg);
    						int ids= hproxymessageService.updateHProxyMessage(hproxymessage);
    						if(ids>0){
    							String content = hproxymessage.getProxyName() + "，您在企业缴费平台的手机缴费服务资料更改申请已通过审核，感谢您的支持。";
    							SendMsgUtil.sendMsg(hproxymessage.getPayPhoneNumber(),content);
    							writeSuccessMsg("操作成功", null, response);
    						}else{
    							writeErrorMsg("系统错误", null, response);
    						}
		    			}else{
		    				writeErrorMsg("审核失败，错误代码"+returnCode, null, response);
		    			}
		    		}else{
		    			writeErrorMsg("客户手机号在系统中已注册，审核失败", null, response);
		    		}
				}else{
    				int uid = manageadminuserService.saveAgentManage1(hproxymessage.getPayPhoneNumber(), hproxymessage.getProxyName(), null, null, Integer.valueOf(FileUploadConstants.PROXY_ROLEID),Integer.valueOf(getSession(request, SessionName.ADMIN_USER_ID).toString()),null,null,hproxymessage.getcId(),hproxymessage.getPayCard());
					if(uid>0){
						PaymentClient client = new PaymentClientImpl();
						String returnCode = client.update(hproxymessage.getContractNumber(), hproxymessage.getUserNumber(), hproxymessage.getProxyName(), hproxymessage.getPayPhoneNumber(), "00100", hproxymessage.getBank_number(), hproxymessage.getPayBankNumber(), hproxymessage.getPayAccountName());
						String returnCode111 = "";
						if(StringUtils.isNotBlank(returnCode)&&returnCode.length()>3){
							returnCode111 = returnCode.substring(returnCode.length()-3, returnCode.length());
						}
						if("000".equals(returnCode111)){
		    				hproxymessage.setCheckState(checkState);
    						hproxymessage.setMsg(msg);
    						int ids= hproxymessageService.updateHProxyMessage(hproxymessage);
    						if(ids>0){
    							writeSuccessMsg("操作成功", null, response);
    						}else{
    							writeErrorMsg("系统错误", null, response);
    						}
		    			}else{
    		    			writeErrorMsg("审核失败，错误代码"+returnCode, null, response);
    		    		}
					}else{
						writeErrorMsg("客户手机号在系统中已注册，审核失败", null, response);
					}
		    			
    			}
			}else{
				writeErrorMsg("系统错误", null, response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/toPayMsg1", method = RequestMethod.GET)
	public String toPayMsg1(HttpServletRequest request, HttpServletResponse response, Model model){
		Integer proxyMessageId = RequestHandler.getInteger(request, "proxyMessageId");
		if(proxyMessageId!=null){
			HProxyMessage hProxyMessage = new HProxyMessage();
			hProxyMessage.setId(proxyMessageId);
			hProxyMessage = hproxymessageService.getHProxyMessage(hProxyMessage);
			model.addAttribute("hProxyMessage", hProxyMessage);
		}
		model.addAttribute("left_nav", "proxy");
		return "/public/payMsg1";
	}
	
	@RequestMapping(value = "/saveProxyMessage", method = RequestMethod.POST)
	public String saveProxyMessage(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HProxyMessage hproxymessage = new HProxyMessage();
			
			Integer id = RequestHandler.getInteger(request, "proxyId");
			hproxymessage.setId(id);
			String proxyName = RequestHandler.getString(request, "proxyName");
			hproxymessage.setProxyName(proxyName);
			String proxyPhone = RequestHandler.getString(request, "proxyPhone");
			hproxymessage.setProxyPhone(proxyPhone);
			Date createYime = RequestHandler.getDate(request, "createYime");
			hproxymessage.setCreateYime(new Date());
			String contractNumber = RequestHandler.getString(request, "contractNumber");
			hproxymessage.setContractNumber(contractNumber);
			Date contractStartTime = RequestHandler.getDate(request, "contractStartTime");
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(contractStartTime!=null){
				String start = sf.format(contractStartTime) + " 00:00:00";
				hproxymessage.setContractStartTime(sf1.parse(start));
			}
			Date contractEndTime = RequestHandler.getDate(request, "contractEndTime");
			if(contractEndTime!=null){
				String end = sf.format(contractEndTime) + " 23:59:59";
				hproxymessage.setContractEndTime(sf1.parse(end));
			}
			Integer remindStartDate = RequestHandler.getInteger(request, "remindStartDate");
			hproxymessage.setRemindStartDate(remindStartDate);
			Integer remindEndDate = RequestHandler.getInteger(request, "remindEndDate");
			hproxymessage.setRemindEndDate(remindEndDate);
			String bank_number = RequestHandler.getString(request, "bank_number");
			hproxymessage.setBank_number(bank_number);
			String info = RequestHandler.getString(request, "info");
			hproxymessage.setInfo(info);
			String remark1 = RequestHandler.getString(request, "remark1");
			hproxymessage.setRemark1(remark1);
			String remark2 = RequestHandler.getString(request, "remark2");
			hproxymessage.setRemark2(remark2);
			String remark3 = RequestHandler.getString(request, "remark3");
			hproxymessage.setRemark3(remark3);
			Integer cId = RequestHandler.getInteger(request, "cId");
			hproxymessage.setCId(cId);
			Integer userId = RequestHandler.getInteger(request, "userId");
			hproxymessage.setUserId(userId);
			Integer state = RequestHandler.getInteger(request, "state");
			hproxymessage.setState(state);
			Integer sex = RequestHandler.getInteger(request, "sex");
			hproxymessage.setSex(sex);
			String userNumber = RequestHandler.getString(request, "userNumber");
			hproxymessage.setUserNumber(userNumber);
			String proxyAddress = RequestHandler.getString(request, "proxyAddress");
			hproxymessage.setProxyAddress(proxyAddress);
			String proxyCode = RequestHandler.getString(request, "proxyCode");
			hproxymessage.setProxyCode(proxyCode);
			String payBankNumber = RequestHandler.getString(request, "payBankNumber");
			hproxymessage.setPayBankNumber(payBankNumber);
			String payName = RequestHandler.getString(request, "payName");
			hproxymessage.setPayName(payName);
			Integer payCardStyle = RequestHandler.getInteger(request, "payCardStyle");
			hproxymessage.setPayCardStyle(payCardStyle);
			String payCard = RequestHandler.getString(request, "payCard");
			hproxymessage.setPayCard(payCard);
			String payPhoneNumber = RequestHandler.getString(request, "payPhoneNumber");
			hproxymessage.setPayPhoneNumber(payPhoneNumber);
			String payMail = RequestHandler.getString(request, "payMail");
			hproxymessage.setPayMail(payMail);
			
			String payAccountName = RequestHandler.getString(request, "payAccountName");
			hproxymessage.setPayAccountName(payAccountName);
			String bank_name = RequestHandler.getString(request, "bank_name");
			hproxymessage.setBank_name(bank_name);
			
			ManageAdminUser manageadminuser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			hproxymessage.setcId(manageadminuser.getCompanyId());
			
			Integer checkState = RequestHandler.getInteger(request, "checkState");
			hproxymessage.setCheckState(checkState);
			
			hproxymessage.setRemindStartDate(1);
			
			String qsBank = RequestHandler.getString(request, "qsBank");
			hproxymessage.setQsBank(qsBank);
			
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			Calendar ca = Calendar.getInstance();    
	        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));  
	        String last = format.format(ca.getTime());
			hproxymessage.setRemindEndDate(Integer.valueOf(last.substring(last.length()-2, last.length())));
			
			if(id!=null){
				HProxyMessage hProxyMessage1 = new HProxyMessage();
				hProxyMessage1.setId(id);
				hProxyMessage1 = hproxymessageService.getHProxyMessage(hProxyMessage1);
				request.getSession().setAttribute("hProxyMessageBG", hProxyMessage1);
				if(hProxyMessage1!=null){
					HProxySerial hProxySerial = hproxyserialService.updateNumber(bank_number, payBankNumber,hProxyMessage1.getUserNumber());
					if(hProxySerial!=null&&hProxySerial.getId()>0){
						hproxymessage.setContractNumber(hProxySerial.getContractNumber());
						hproxymessage.setUserNumber(hProxySerial.getUserNumber());
						int ids = hproxymessageService.updateHProxyMessage(hproxymessage);
						if(ids>0){
							writeSuccessMsg("提交成功", id, response);
						}else{
							writeErrorMsg("提交失败", null, response);
						}
					}else{
						writeErrorMsg("提交失败", null, response);
					}
				}else{
					writeErrorMsg("提交失败", null, response);
				}
			}else{
				HProxySerial hProxySerial = hproxyserialService.insertNumber(bank_number, payBankNumber);
				if(hProxySerial!=null&&hProxySerial.getId()>0){
					hproxymessage.setContractNumber(hProxySerial.getContractNumber());
					hproxymessage.setUserNumber(hProxySerial.getUserNumber());
					int ids= hproxymessageService.insertHProxyMessage(hproxymessage);
					if(ids>0){
						writeSuccessMsg("提交成功", ids, response);
					}else{
						writeErrorMsg("提交失败", null, response);
					}
				}else{
					writeErrorMsg("提交失败", null, response);
				}
			}
//			PaymentClient client = new PaymentClientImpl();
//			String returnCode = client.add(hproxymessage.getContractNumber(), hproxymessage.getUserNumber(), hproxymessage.getPayName(), hproxymessage.getPayPhoneNumber(), "00100", hproxymessage.getBank_number(), hproxymessage.getPayBankNumber(), hproxymessage.getPayName());
//			if("CP1I1000".equals(returnCode)){
				
//				if(ids>0){
//					int uid = manageadminuserService.saveAgentManage1(proxyPhone, proxyName, null, null, Integer.valueOf(FileUploadConstants.PROXY_ROLEID),Integer.valueOf(getSession(request, SessionName.ADMIN_USER_ID).toString()),null,null,cId);
//					hproxymessage.setId(ids);
//					hproxymessage.setUserId(uid);
//					hproxymessageService.updateHProxyMessage(hproxymessage);
//					
//				}
				
//				writeSuccessMsg("添加成功", null, response);
//			}else if("CS".equals(returnCode)){
//				writeErrorMsg("发送数据超时", null, response);
//			}else{
//				writeErrorMsg("系统错误", null, response);
//			}
		} catch (Exception e)
		{
			e.printStackTrace();
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	
	@RequestMapping(value = "/saveProxyMessageHT", method = RequestMethod.POST)
	public String saveProxyMessageHT(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HProxyMessage hproxymessage = new HProxyMessage();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hproxymessage.setId(id);
			hproxymessage = hproxymessageService.getHProxyMessage(hproxymessage);
			if(hproxymessage!=null&&hproxymessage.getId()>0){
				String hetongUrl = RequestHandler.getString(request, "hetongUrl");
				hproxymessage.setHetongUrl(hetongUrl);
				hproxymessage.setCheckState(0);
				int ids = hproxymessageService.updateHProxyMessage(hproxymessage);
				if(ids>0){
					//计入变更记录
					JSONObject json = JSONObject.fromObject(hproxymessage);
					HProxyMessageLog pmLog = (HProxyMessageLog)JSONObject.toBean(json, HProxyMessageLog.class);
					hproxymessagelogService.insertHProxyMessageLog(pmLog);
					writeSuccessMsg("提交成功", id, response);
				}else{
					writeErrorMsg("提交失败", null, response);
				}
			}else{
				writeErrorMsg("提交失败", null, response);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/saveProxyMessageHTUpdate", method = RequestMethod.POST)
	public String saveProxyMessageHTUpdate(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HProxyMessage hproxymessage = new HProxyMessage();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hproxymessage.setId(id);
			hproxymessage = hproxymessageService.getHProxyMessage(hproxymessage);
			if(hproxymessage!=null&&hproxymessage.getId()>0){
				String biangengUrl = RequestHandler.getString(request, "biangengUrl");
				hproxymessage.setBiangengUrl(biangengUrl);
				hproxymessage.setCheckState(6);
				int ids = hproxymessageService.updateHProxyMessage(hproxymessage);
				if(ids>0){
					//计入变更记录
					JSONObject json = JSONObject.fromObject(hproxymessage);
					HProxyMessageLog pmLog = (HProxyMessageLog)JSONObject.toBean(json, HProxyMessageLog.class);
					hproxymessagelogService.insertHProxyMessageLog(pmLog);
					writeSuccessMsg("提交成功", id, response);
				}else{
					writeErrorMsg("提交失败", null, response);
				}
			}else{
				writeErrorMsg("提交失败", null, response);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/saveProxyMessageHTZZ", method = RequestMethod.POST)
	public String saveProxyMessageHTZZ(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HProxyMessage hproxymessage = new HProxyMessage();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hproxymessage.setId(id);
			hproxymessage = hproxymessageService.getHProxyMessage(hproxymessage);
			if(hproxymessage!=null&&hproxymessage.getId()>0){
				String chexiaoUrl = RequestHandler.getString(request, "chexiaoUrl");
				hproxymessage.setChexiaoUrl(chexiaoUrl);
				hproxymessage.setCheckState(3);
				int ids = hproxymessageService.updateHProxyMessage(hproxymessage);
				if(ids>0){
					//计入变更记录
					JSONObject json = JSONObject.fromObject(hproxymessage);
					HProxyMessageLog pmLog = (HProxyMessageLog)JSONObject.toBean(json, HProxyMessageLog.class);
					hproxymessagelogService.insertHProxyMessageLog(pmLog);
					writeSuccessMsg("提交成功", id, response);
				}else{
					writeErrorMsg("提交失败", null, response);
				}
			}else{
				writeErrorMsg("提交失败", null, response);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/toPayMsg2", method = RequestMethod.GET)
	public String toPayMsg2(HttpServletRequest request, HttpServletResponse response, Model model){
		Integer proxyMessageId = RequestHandler.getInteger(request, "proxyMessageId");
		HProxyMessage hProxyMessage = new HProxyMessage();
		hProxyMessage.setId(proxyMessageId);
		hProxyMessage = hproxymessageService.getHProxyMessage(hProxyMessage);
		model.addAttribute("hProxyMessage", hProxyMessage);
		model.addAttribute("left_nav", "proxy");
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		try{
			//获取模板路径
			String basePath = request.getSession().getServletContext().getRealPath("/");
			String auth_temple = basePath + "/upload/create.jpg";
			File file = new File(basePath + "/upload/xieyi/"+sf.format(new Date())+"/");
			if(!file.exists()){
				file.mkdirs();
			}
			Random rand = new Random();
			int i = rand.nextInt(); //int范围类的随机数
			i = rand.nextInt(100); //生成0-100以内的随机数
			List<Location> listLocation = hproxymessageService.getLocation(hProxyMessage);
			String outPath = "/upload/xieyi/"+sf.format(new Date())+"/"+hProxyMessage.getContractNumber()+"_"+i+".jpg";
			CommUtil.authImage(auth_temple, basePath + outPath, listLocation, "#000000");
			model.addAttribute("outPath", outPath);
			model.addAttribute("random", Math.random());
			model.addAttribute("name", hProxyMessage.getPayName()+"_手机缴费服务开通协议申请公函");
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/public/payMsg2";
	}
	@RequestMapping(value = "/toPayUpdateMsg2", method = RequestMethod.GET)
	public String toPayUpdateMsg2(HttpServletRequest request, HttpServletResponse response, Model model){
		Integer proxyMessageId = RequestHandler.getInteger(request, "proxyMessageId");
		HProxyMessage hProxyMessage = new HProxyMessage();
		hProxyMessage.setId(proxyMessageId);
		hProxyMessage = hproxymessageService.getHProxyMessage(hProxyMessage);
		model.addAttribute("hProxyMessage", hProxyMessage);
		model.addAttribute("left_nav", "proxy");
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		try{
			//获取变更前信息
			HProxyMessage hProxyMessageBG = (HProxyMessage)request.getSession().getAttribute("hProxyMessageBG");
			
			//获取模板路径
			String basePath = request.getSession().getServletContext().getRealPath("/");
			String auth_temple = basePath + "/upload/biangeng.jpg";
			File file = new File(basePath + "/upload/xieyi/"+sf.format(new Date())+"/");
			if(!file.exists()){
				file.mkdirs();
			}
			Random rand = new Random();
			int i = rand.nextInt(); //int范围类的随机数
			i = rand.nextInt(100); //生成0-100以内的随机数
			List<Location> listLocation = hproxymessageService.getLocation2(hProxyMessage,hProxyMessageBG);
			String outPath = "/upload/xieyi/"+sf.format(new Date())+"/"+hProxyMessage.getContractNumber()+"_"+i+".jpg";
			CommUtil.authImage(auth_temple, basePath + outPath, listLocation, "#000000");
			model.addAttribute("outPath", outPath);
			model.addAttribute("random", Math.random());
			model.addAttribute("name", hProxyMessage.getPayName()+"_手机缴费服务变更协议申请公函");
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/public/payUpdateMsg2";
	}
	
	@RequestMapping(value = "/toPayMsg3", method = RequestMethod.GET)
	public String toPayMsg3(HttpServletRequest request, HttpServletResponse response, Model model){
		Integer proxyMessageId = RequestHandler.getInteger(request, "proxyMessageId");
		HProxyMessage hProxyMessage = new HProxyMessage();
		hProxyMessage.setId(proxyMessageId);
		hProxyMessage = hproxymessageService.getHProxyMessage(hProxyMessage);
		model.addAttribute("hProxyMessage", hProxyMessage);
		model.addAttribute("left_nav", "proxy");
		return "/public/payMsg3";
	}
	
	@RequestMapping(value = "/toPayUpdateMsg3", method = RequestMethod.GET)
	public String toPayUpdateMsg3(HttpServletRequest request, HttpServletResponse response, Model model){
		Integer proxyMessageId = RequestHandler.getInteger(request, "proxyMessageId");
		HProxyMessage hProxyMessage = new HProxyMessage();
		hProxyMessage.setId(proxyMessageId);
		hProxyMessage = hproxymessageService.getHProxyMessage(hProxyMessage);
		model.addAttribute("hProxyMessage", hProxyMessage);
		model.addAttribute("left_nav", "proxy");
		return "/public/payUpdateMsg3";
	}
	
	@RequestMapping(value = "/toPayMsg4", method = RequestMethod.GET)
	public String toPayMsg4(HttpServletRequest request, HttpServletResponse response, Model model){
		Integer proxyMessageId = RequestHandler.getInteger(request, "proxyMessageId");
		HProxyMessage hProxyMessage = new HProxyMessage();
		hProxyMessage.setId(proxyMessageId);
		hProxyMessage = hproxymessageService.getHProxyMessage(hProxyMessage);
		model.addAttribute("hProxyMessage", hProxyMessage);
		model.addAttribute("left_nav", "proxy");
		return "/public/payMsg4";
	}
	
	@RequestMapping(value = "/updateMsg", method = RequestMethod.GET)
	public String updateMsg(HttpServletRequest request, HttpServletResponse response, Model model){
		String url = "/public/payUpdateMsg1";
		try{
			Integer id = RequestHandler.getInteger(request, "id");
			HProxyMessage hProxyMessage1 = new HProxyMessage();
			hProxyMessage1.setId(id);
			hProxyMessage1 = hproxymessageService.getHProxyMessage(hProxyMessage1);
			model.addAttribute("hProxyMessage", hProxyMessage1);
		}catch(Exception e){
			e.printStackTrace();
		}
		model.addAttribute("left_nav", "proxy");
		return url;
	}
	
	@RequestMapping(value = "/toPayOver1", method = RequestMethod.GET)
	public String toPayMsgOver1(HttpServletRequest request, HttpServletResponse response, Model model){
		String url = "/public/payOver1";
		try{
			ManageAdminUser user = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			HProxyMessage hProxyMessage1 = new HProxyMessage();
			hProxyMessage1.setCId(user.getCompanyId());
			hProxyMessage1.setState(1);
			hProxyMessage1 = hproxymessageService.getHProxyMessage(hProxyMessage1);
			model.addAttribute("hProxyMessage", hProxyMessage1);
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
			try{
				//获取模板路径
				String basePath = request.getSession().getServletContext().getRealPath("/");
				String auth_temple = basePath + "/upload/zhongzhi.jpg";
				File file = new File(basePath + "/upload/xieyi/"+sf.format(new Date())+"/");
				if(!file.exists()){
					file.mkdirs();
				}
				Random rand = new Random();
				int i = rand.nextInt(); //int范围类的随机数
				i = rand.nextInt(100); //生成0-100以内的随机数
				List<Location> listLocation = hproxymessageService.getLocation1(hProxyMessage1);
				String outPath = "/upload/xieyi/"+sf.format(new Date())+"/"+hProxyMessage1.getContractNumber()+"_"+i+".jpg";
				CommUtil.authImage(auth_temple, basePath + outPath, listLocation, "#000000");
				model.addAttribute("outPath", outPath);
				model.addAttribute("random", Math.random());
				model.addAttribute("name", hProxyMessage1.getPayName()+"_手机缴费服务中止协议申请公函");
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		model.addAttribute("left_nav", "proxy");
		return url;
	}
	
	@RequestMapping(value = "/toPayOver2", method = RequestMethod.GET)
	public String toPayOver2(HttpServletRequest request, HttpServletResponse response, Model model){
		String url = "/public/payOver2";
		try{
			Integer proxyMessageId = RequestHandler.getInteger(request, "proxyMessageId");
			HProxyMessage hProxyMessage = new HProxyMessage();
			hProxyMessage.setId(proxyMessageId);
			hProxyMessage = hproxymessageService.getHProxyMessage(hProxyMessage);
			model.addAttribute("hProxyMessage", hProxyMessage);
			model.addAttribute("left_nav", "proxy");
		}catch(Exception e){
			e.printStackTrace();
		}
		model.addAttribute("left_nav", "proxy");
		return url;
	}
	
	@RequestMapping(value = "/toPayOver3", method = RequestMethod.GET)
	public String toPayOver3(HttpServletRequest request, HttpServletResponse response, Model model){
		String url = "/public/payOver3";
		try{
			Integer proxyMessageId = RequestHandler.getInteger(request, "proxyMessageId");
			HProxyMessage hProxyMessage = new HProxyMessage();
			hProxyMessage.setId(proxyMessageId);
			hProxyMessage = hproxymessageService.getHProxyMessage(hProxyMessage);
			model.addAttribute("hProxyMessage", hProxyMessage);
			model.addAttribute("left_nav", "proxy");
		}catch(Exception e){
			e.printStackTrace();
		}
		model.addAttribute("left_nav", "proxy");
		return url;
	}
	
	@RequestMapping(value = "/toPayMsgSuccess", method = RequestMethod.GET)
	public String toPayMsgSuccess(HttpServletRequest request, HttpServletResponse response, Model model){
		String url = "/public/payMsgSuccess";
		try{
			Integer proxyMessageId = RequestHandler.getInteger(request, "proxyMessageId");
			HProxyMessage hProxyMessage = new HProxyMessage();
			hProxyMessage.setId(proxyMessageId);
			hProxyMessage = hproxymessageService.getHProxyMessage(hProxyMessage);
			model.addAttribute("hProxyMessage", hProxyMessage);
			model.addAttribute("left_nav", "proxy");
		}catch(Exception e){
			e.printStackTrace();
		}
		model.addAttribute("left_nav", "proxy");
		return url;
	}
	
	@RequestMapping(value = "/checkPhone", method = RequestMethod.POST)
	public String checkPhone(HttpServletRequest request, HttpServletResponse response, Model model){
		try{
			Integer proxyId = RequestHandler.getInteger(request, "proxyId");
			String payPhoneNumber = RequestHandler.getString(request, "payPhoneNumber");
			
			HProxyMessage hProxyMessage111 = new HProxyMessage();
			hProxyMessage111.setId(proxyId);
			hProxyMessage111.setPayPhoneNumber(payPhoneNumber);
			int count11 = hproxymessageService.checkProxyPhone(hProxyMessage111);
			if(count11==0){
				if(proxyId!=null){
					HProxyMessage hProxyMessage = new HProxyMessage();
					hProxyMessage.setId(proxyId);
					hProxyMessage = hproxymessageService.getHProxyMessage(hProxyMessage);
					if(hProxyMessage!=null&&hProxyMessage.getUserId()!=null){
						ManageAdminUser manageAdminUser = new ManageAdminUser();
						manageAdminUser.setAdminName(payPhoneNumber+"8");
						manageAdminUser.setAdminId(hProxyMessage.getUserId());
						int count = manageadminuserService.checkAdminName(manageAdminUser);
						if(count>0){
							writeErrorMsg("exit", null, response);
						}else{
							writeSuccessMsg("ok", null, response);
						}
					}else{
						HProxyMessage hProxyMessage1 = new HProxyMessage();
						hProxyMessage1.setProxyPhone(payPhoneNumber);
						int count = hproxymessageService.getHProxyMessageListCount(hProxyMessage1);
						if(count>0){
							writeErrorMsg("exit", null, response);
						}else{
							writeSuccessMsg("ok", null, response);
						}
					}
				}else{
					ManageAdminUser manageAdminUser = new ManageAdminUser();
					manageAdminUser.setAdminName(payPhoneNumber+"8");
					int count = manageadminuserService.getManageAdminUserListCount(manageAdminUser);
					if(count>0){
						writeErrorMsg("exit", null, response);
					}else{
						writeSuccessMsg("ok", null, response);
					}
				}
			}else{
				writeErrorMsg("exit", null, response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/toWhatPay", method = RequestMethod.GET)
	public String toWhatPay(HttpServletRequest request, HttpServletResponse response, Model model){
		try{
			ManageAdminUser user = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			HProxyMessage hProxyMessage1 = new HProxyMessage();
			hProxyMessage1.setCId(user.getCompanyId());
			hProxyMessage1.setState(1);
			hProxyMessage1 = hproxymessageService.getHProxyMessage(hProxyMessage1);
			if(hProxyMessage1!=null){
				HPay hPay = new HPay();
				hPay = hpayService.getHPay(hPay);
				model.addAttribute("hPay", hPay);
				model.addAttribute("hProxyMessage", hProxyMessage1);
			}else{
				HPay hPay = new HPay();
				hPay = hpayService.getHPay(hPay);
				model.addAttribute("hPay", hPay);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		model.addAttribute("left_nav", "proxy");
		return "/public/whatPay";
	}
}
