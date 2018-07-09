package com.hxt.hPayOrder.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

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
import com.hxt.hAgent.service.HAgentService;
import com.hxt.hAgentTwo.model.HAgentTwo;
import com.hxt.hAgentTwo.service.HAgentTwoService;
import com.hxt.hAmmeterInfo.model.HAmmeterInfo;
import com.hxt.hAmmeterInfo.service.HAmmeterInfoService;
import com.hxt.hCommon.service.HCommonService;
import com.hxt.hCompany.model.HCompany;
import com.hxt.hCompany.service.HCompanyService;
import com.hxt.hEvaluate.model.HEvaluate;
import com.hxt.hEvaluate.service.HEvaluateService;
import com.hxt.hFp.model.HFp;
import com.hxt.hFp.service.HFpService;
import com.hxt.hFpOrder.model.HFpOrder;
import com.hxt.hFpOrder.service.HFpOrderService;
import com.hxt.hPayOrder.model.HPayOrder;
import com.hxt.hPayOrder.service.HPayOrderService;
import com.hxt.hProfitRatio.model.HProfitRatio;
import com.hxt.hProfitRatio.service.HProfitRatioService;
import com.hxt.hProxySendLog.model.HProxySendLog;
import com.hxt.hProxySendLog.service.HProxySendLogService;
import com.hxt.hSubCompany.model.HSubCompany;
import com.hxt.hSubCompany.service.HSubCompanyService;
import com.hxt.hSubOrder.model.HSubOrder;
import com.hxt.hSubOrder.service.HSubOrderService;
import com.hxt.hUserAccount.model.HUserAccount;
import com.hxt.hUserAccount.service.HUserAccountService;
import com.sys.manageAdminUser.model.ManageAdminUser;
import com.sys.manageAdminUser.service.ManageAdminUserService;
import com.alibaba.fastjson.JSONObject;
import com.base.utils.FileUploadConstants;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.utils.SessionName;
import com.base.controller.BaseController;
/**
 * @author	wuzhenwei
 * @time	2015年11月24日 23:49:07
 */
@Controller
@RequestMapping("/hPayOrder")
public class HPayOrderController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HPayOrderController.class); //Logger
	Logger log = Logger.getLogger(HPayOrderController.class);
	@Autowired
	private HPayOrderService hpayorderService = null;
	@Autowired
	private HCommonService hCommonService;
	@Autowired
	private HSubOrderService hsuborderService = null;
	@Autowired
	private HSubCompanyService hsubcompanyService = null;
	@Autowired
	private HCompanyService hcompanyService = null;
	
	@Autowired
	private HFpService hfpService = null;
	@Autowired
	private HEvaluateService hevaluateService = null;
	@Autowired
	private HAmmeterInfoService hammeterinfoService = null;
	@Autowired
	private HProfitRatioService hprofitratioService = null;
	@Autowired
	private HAgentService hagentService = null;
	@Autowired
	private HAgentTwoService hagenttwoService = null;
	@Autowired
	private ManageAdminUserService manageadminuserService = null;// 用户
	@Autowired
	private HUserAccountService huseraccountService = null;
	@Autowired
	private HFpOrderService hfporderService = null;
	@Autowired
	private HProxySendLogService hproxysendlogService = null;
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		ManageAdminUser loginUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
		boolean agentFlag = false;
		if(loginUser.getRoleId().toString().equals(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID)){
			model.addAttribute("oneName", loginUser.getOneAgentName());
			model.addAttribute("oneId", loginUser.getOneAgentOpenId());
			agentFlag = true;
		}else if(loginUser.getRoleId().toString().equals(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID)){
			model.addAttribute("oneName", loginUser.getOneAgentName());
			model.addAttribute("oneId", loginUser.getOneAgentOpenId());
			model.addAttribute("twoName", loginUser.getTwoAgentName());
			model.addAttribute("twoId", loginUser.getTwoAgentOpenId());
			agentFlag = true;
		}else if(loginUser.getRoleId().toString().equals(FileUploadConstants.SERVICER_MANAGE_ROLEID)){
			model.addAttribute("servicerName", loginUser.getRealName());
			model.addAttribute("servicerId", loginUser.getAdminId());
			agentFlag = true;
		}
		model.addAttribute("agentFlag", agentFlag);
		model.addAttribute("twoAgentRoleId", FileUploadConstants.TWO_AGENT_MANAGE_ROLEID);
		return "/hPayOrder/hPayOrderIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hPayOrder/hPayOrderAdd";
	}
	@RequestMapping(value = "/toUpdate/{o_id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer o_id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HPayOrder hpayorder1 = new HPayOrder();
		hpayorder1.setO_id(o_id);
		HPayOrder hpayorder = hpayorderService.getHPayOrder(hpayorder1);
		model.addAttribute("hpayorder", hpayorder);
		
		return "/hPayOrder/hPayOrderUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHPayOrderList", method = RequestMethod.GET)
	public String getHPayOrderList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HPayOrder hpayorder = new HPayOrder();
			//角色条件处理
			ManageAdminUser loginUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			if(loginUser.getRoleId().toString().equals(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID)){
				hpayorder.setOneAgentOpenId(loginUser.getOneAgentOpenId());
			}else if(loginUser.getRoleId().toString().equals(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID)){
				hpayorder.setTwoAgentOpenID(loginUser.getTwoAgentOpenId());
			}else if(loginUser.getRoleId().toString().equals(FileUploadConstants.SERVICER_MANAGE_ROLEID)){
				hpayorder.setServicerId(loginUser.getServicerId());
			}
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
			
			Integer fr = RequestHandler.getInteger(request, "fr");
			hpayorder.setFr(fr);
			Integer yw_id = RequestHandler.getInteger(request, "yw_id");
			hpayorder.setYw_id(yw_id);
			
			Integer o_sub_id = RequestHandler.getInteger(request, "o_sub_id");
			hpayorder.setO_sub_id(o_sub_id);
			
			Integer amount = RequestHandler.getInteger(request, "amount");
			hpayorder.setAmount(amount);
			
			Integer is_fp = RequestHandler.getInteger(request, "is_fp");
			hpayorder.setIs_fp(is_fp);
			
			Integer actual_payment = RequestHandler.getInteger(request, "actual_payment");
			hpayorder.setActual_payment(actual_payment);
			
			Integer account_payment = RequestHandler.getInteger(request, "account_payment");
			hpayorder.setAccount_payment(account_payment);
			
			String pay_type = RequestHandler.getString(request, "pay_type");
			hpayorder.setPay_type(pay_type);
			
			String yw_name = RequestHandler.getString(request, "yw_name");
			hpayorder.setYw_name(yw_name);
			
			String c_name = RequestHandler.getString(request, "c_name");
			hpayorder.setC_name(c_name);
			
			String pay_account = RequestHandler.getString(request, "pay_account");
			hpayorder.setPay_account(pay_account);
			
			String electric_number = RequestHandler.getString(request, "electric_number");
			hpayorder.setElectric_number(electric_number);
			
			Integer operator_id = RequestHandler.getInteger(request, "operator_id");
			hpayorder.setOperator_id(operator_id);
			
			String payee = RequestHandler.getString(request, "payee");
			hpayorder.setPayee(payee);
			
			Date create_time = RequestHandler.getDate(request, "create_time");
			hpayorder.setCreate_time(create_time);
			
			Date pay_time = RequestHandler.getDate(request, "pay_time");
			hpayorder.setPay_time(pay_time);
			
			String pay_status = RequestHandler.getString(request, "pay_status");
			hpayorder.setPay_status(pay_status);
			
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
			
			String create_start_time_str = RequestHandler.getString(request, "create_start_time");
			if(StringUtils.isNotBlank(create_start_time_str)){
				create_start_time_str = create_start_time_str + " 00:00:00";
				hpayorder.setCreate_start_time(sf.parse(create_start_time_str));
			}
			String create_end_time_str = RequestHandler.getString(request, "create_end_time");
			if(StringUtils.isNotBlank(create_end_time_str)){
				create_end_time_str = create_end_time_str + " 23:59:59";
				hpayorder.setCreate_end_time(sf.parse(create_end_time_str));
			}
			
			Double startTotalFeeD = RequestHandler.getDouble(request, "startTotalFee");
			Double endTotalFeeD = RequestHandler.getDouble(request, "endTotalFee");
			if(startTotalFeeD!=null){
				BigDecimal sFee = new BigDecimal(startTotalFeeD*100);
		        double f11 = sFee.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		        BigDecimal bg111 = new BigDecimal(f11);
		        int f1 = bg111.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
		        hpayorder.setStartTotalFee(f1);
			}
			if(endTotalFeeD!=null){
				BigDecimal sFee = new BigDecimal(endTotalFeeD*100);
				double f11 = sFee.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				BigDecimal bg111 = new BigDecimal(f11);
				int f1 = bg111.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
				hpayorder.setEndTotalFee(f1);
			}

			String agentOneOpenId = RequestHandler.getString(request, "agentOneOpenId");
			String agentTwoOpenId = RequestHandler.getString(request, "agentTwoOpenId");
			Integer servicerId = RequestHandler.getInteger(request, "servicerId");
			hpayorder.setOneAgentOpenId(agentOneOpenId);
			hpayorder.setTwoAgentOpenID(agentTwoOpenId);
			hpayorder.setServicerId(servicerId);
			ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			if(adminUser.getRoleId().equals(Integer.valueOf(FileUploadConstants.YWY_ROLE_ID))){
				hpayorder.setYw_id(adminUser.getAdminId());
			}
			
			Integer is_zz = RequestHandler.getInteger(request, "is_zz");
			hpayorder.setIs_zz(is_zz);
			
			String contact_phone = RequestHandler.getString(request, "contact_phone");
			hpayorder.setContact_phone(contact_phone);
			
			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hpayorder.setRowStart(from);
			hpayorder.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hpayorder.setSortColumn(sortColumn);
			
			int hpayorderListCount = hpayorderService.getHPayOrderListCountNew(hpayorder);
			ResponseList<HPayOrder> hpayorderList = null;
			if ( hpayorderListCount > 0 )
			{
				
				hpayorderList = hpayorderService.getHPayOrderListNew(hpayorder);
				
				hpayorder.setPay_status("1");
				Long yi = hpayorderService.getHPayOrderTotal(hpayorder);
//				HPayOrder totalFeeOrder = hpayorderService.getPayTotalFee(hpayorder);
				
				hpayorder.setPay_status("0");
				Long wei = hpayorderService.getHPayOrderTotal(hpayorder);
//				HPayOrder totalFeeOrder1 = hpayorderService.getPayTotalFee(hpayorder);
				
				if(pay_status!=null){
					hpayorder.setPay_status(pay_status);
				}else{
					hpayorder.setPay_status(null);
				}
				
				Iterator<Object> it = hpayorderList.iterator();
				while(it.hasNext()){
					HPayOrder order = (HPayOrder)it.next();
					String totalFeeStr1 = null;
					if(order.getAmount()>0){
						if(String.valueOf(order.getAmount()).length()>2){
							int fen = order.getAmount()%100;
							if(fen>0&&fen<10){
								totalFeeStr1 = order.getAmount()/100 + ".0" + fen;
							}else if(fen>=10){
								totalFeeStr1 = order.getAmount()/100 + "." + fen;
							}else{
								totalFeeStr1 = order.getAmount()/100 + ".00";
							}
							order.setAmountStr(totalFeeStr1);
						}else if(String.valueOf(order.getAmount()).length()==1){
							order.setAmountStr("0.0"+order.getAmount());
						}else{
							order.setAmountStr("0."+order.getAmount());
						}
					}else{
						order.setAmountStr("0.00");
					}
					if(yi!=null){
						
						if(yi>0){
							if(String.valueOf(yi).length()>2){
								Long fen = yi%100;
								if(fen>0&&fen<10){
									totalFeeStr1 = yi/100 + ".0" + fen;
								}else if(fen>=10){
									totalFeeStr1 = yi/100 + "." + fen;
								}else{
									totalFeeStr1 = yi/100 + ".00";
								}
							}else if(String.valueOf(yi).length()==1){
								totalFeeStr1 = "0.0"+yi;
							}else{
								totalFeeStr1 = "0."+yi;
							}
						}else{
							totalFeeStr1 = "0.00";
						}
						
						
						order.setTotalFeeStr(totalFeeStr1);
					}else{
						order.setTotalFeeStr("0.00");
					}
					if(wei!=null){
						
						if(wei>0){
							if(String.valueOf(wei).length()>2){
								Long fen = wei%100;
								if(fen>0&&fen<10){
									totalFeeStr1 = wei/100 + ".0" + fen;
								}else if(fen>=10){
									totalFeeStr1 = wei/100 + "." + fen;
								}else{
									totalFeeStr1 = wei/100 + ".00";
								}
							}else if(String.valueOf(wei).length()==1){
								totalFeeStr1 = "0.0"+wei;
							}else{
								totalFeeStr1 = "0."+wei;
							}
						}else{
							totalFeeStr1 = "0.00";
						}
						
						
						order.setNotTotalFeeStr(totalFeeStr1);
					}else{
						order.setNotTotalFeeStr("0.00");
					}
				}
			} else
			{
				hpayorderList = new ResponseList<HPayOrder>();
			}
			
			
			// 设置数据总数
			hpayorderList.setTotalResults(hpayorderListCount);
			
			writeSuccessMsg("ok", hpayorderList, response);
		} catch (Exception e)
		{
			e.printStackTrace();
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	
	@RequestMapping(value = "/getHPayOrderListIndex", method = RequestMethod.GET)
	public String getHPayOrderListIndex(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			model.addAttribute("left_nav", "jyjl");
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
			List<HSubCompany> listSubC = hsubcompanyService.getHSubCompanyBaseList(hSubCompany);
			
			int hpayorderListCount = hpayorderService.getHPayOrderListCount(hpayorder);
			ResponseList<HPayOrder> hpayorderList = null;
			if ( hpayorderListCount > 0 )
			{
				hpayorderList = hpayorderService.getHPayOrderList(hpayorder);
				Iterator<Object> it = hpayorderList.iterator();
				List<HPayOrder> list = new ArrayList<HPayOrder>();
				while (it.hasNext()) {
					HPayOrder order = (HPayOrder)it.next();
					//查询子订单项目信息
					HSubOrder hSubOrder = new HSubOrder();
					hSubOrder.setO_id(order.getO_no());
					hSubOrder.setSub_id(o_sub_id);
					String totalFeeStr1 = null;
					if(order.getAmount()>0){
						if(String.valueOf(order.getAmount()).length()>2){
							int fen = order.getAmount()%100;
							if(fen>0&&fen<10){
								totalFeeStr1 = order.getAmount()/100 + ".0" + fen;
							}else if(fen>=10){
								totalFeeStr1 = order.getAmount()/100 + "." + fen;
							}else{
								totalFeeStr1 = order.getAmount()/100 + ".00";
							}
							order.setAmountStr(totalFeeStr1);
						}else if(String.valueOf(order.getAmount()).length()==1){
							order.setAmountStr("0.0"+order.getAmount());
						}else{
							order.setAmountStr("0."+order.getAmount());
						}
					}else{
						order.setAmountStr("0.00");
					}
					
					
					List<HSubOrder> listSubOrder = hsuborderService.getHSubOrderBaseList(hSubOrder);
					if(listSubOrder!=null&&listSubOrder.size()>0){
						Set<Integer> set = new HashSet<Integer>();
						for(HSubOrder subOrder:listSubOrder){
							set.add(subOrder.getSub_id());
						}
						Iterator<Integer> iterator = set.iterator();
						List<HSubOrder> listSub = new ArrayList<HSubOrder>();
						while (iterator.hasNext()) {
							String sub_name = null;
							HSubOrder sub = new HSubOrder();
							List<HSubOrder> listSub1 = new ArrayList<HSubOrder>();
							Integer totalFee = 0;
							Integer str = iterator.next(); 
							if(StringUtils.isNotBlank(query_id)){
								for(HSubOrder subOrder:listSubOrder){
									sub.setConsignee(subOrder.getConsignee());
//									sub.setConsignee_address(subOrder.getConsignee_address());
									sub.setConsignee_phone(subOrder.getConsignee_phone());
									//查询待省市区的地址
//									HSubCompany hSubCompanyddd = new HSubCompany();
//									hSubCompanyddd.setS_id(subOrder.getSub_id());
//									hSubCompanyddd = hsubcompanyService.getHSubCompany(hSubCompanyddd);
//									if(hSubCompanyddd!=null){
//										sub.setConsignee_address(hSubCompanyddd.getProvince_name()+hSubCompanyddd.getCity_name()+hSubCompanyddd.getArea_name()+subOrder.getConsignee_address());
//									}else{
										sub.setConsignee_address(subOrder.getConsignee_address());
//									}
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
											hFp.setOrderNumber(order.getO_no());
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
//										HSubCompany hSubCompanyddd = new HSubCompany();
//										hSubCompanyddd.setS_id(subOrder.getSub_id());
//										hSubCompanyddd = hsubcompanyService.getHSubCompany(hSubCompanyddd);
//										if(hSubCompanyddd!=null){
//											sub.setConsignee_address(hSubCompanyddd.getProvince_name()+hSubCompanyddd.getCity_name()+hSubCompanyddd.getArea_name()+subOrder.getConsignee_address());
//										}else{
											sub.setConsignee_address(subOrder.getConsignee_address());
//										}
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
									hFp.setOrderNumber(order.getO_no());
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
						order.setListSubOrder(listSub);
					}
					list.add(order);
				}
				if(list!=null&&list.size()>0){
					for(int i = 0;i< list.size();i++){
						HEvaluate hevaluate = new HEvaluate();
						HPayOrder or =  list.get(i);
						hevaluate.setOrderNo(or.getO_no());
						int evaluateCount = hevaluateService.getHEvaluateListCount(hevaluate);
						if(evaluateCount>0){
							or.setEvaluateFlag(1);
						}else{
							or.setEvaluateFlag(0);
						}
					}
				}
				model.addAttribute("list", list);
			} else
			{
				hpayorderList = new ResponseList<HPayOrder>();
			}
			// 设置数据总数
			model.addAttribute("hpayorderListCount", hpayorderListCount);
			model.addAttribute("pageNo", pageNo);
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
		model.addAttribute("nav", "fp");
//		return "/front/order";
		return "/public/payHistory";
	}
	
	
	@RequestMapping(value = "/getHPayOrderBaseList", method = RequestMethod.GET)
	public String getHPayOrderBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
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
			
			String pay_account = RequestHandler.getString(request, "pay_account");
			hpayorder.setPay_account(pay_account);
			
			Integer operator_id = RequestHandler.getInteger(request, "operator_id");
			hpayorder.setOperator_id(operator_id);
			
			String payee = RequestHandler.getString(request, "payee");
			hpayorder.setPayee(payee);
			
			Date create_time = RequestHandler.getDate(request, "create_time");
			hpayorder.setCreate_time(create_time);
			
			Date pay_time = RequestHandler.getDate(request, "pay_time");
			hpayorder.setPay_time(pay_time);
			
			String pay_status = RequestHandler.getString(request, "pay_status");
			hpayorder.setPay_status(pay_status);
			
			String tick_off_status = RequestHandler.getString(request, "tick_off_status");
			hpayorder.setTick_off_status(tick_off_status);
			
			Date tick_off_time = RequestHandler.getDate(request, "tick_off_time");
			hpayorder.setTick_off_time(tick_off_time);
			
			String back_fee_status = RequestHandler.getString(request, "back_fee_status");
			hpayorder.setBack_fee_status(back_fee_status);
			
			Date back_time = RequestHandler.getDate(request, "back_time");
			hpayorder.setBack_time(back_time);
			
			List<HPayOrder> hpayorderList = hpayorderService.getHPayOrderBaseList(hpayorder);
		
			writeSuccessMsg("ok", hpayorderList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHPayOrder", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
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
			String pay_account = RequestHandler.getString(request, "pay_account");
			hpayorder.setPay_account(pay_account);
			Integer operator_id = RequestHandler.getInteger(request, "operator_id");
			hpayorder.setOperator_id(operator_id);
			String payee = RequestHandler.getString(request, "payee");
			hpayorder.setPayee(payee);
			Date create_time = RequestHandler.getDate(request, "create_time");
			hpayorder.setCreate_time(create_time);
			Date pay_time = RequestHandler.getDate(request, "pay_time");
			hpayorder.setPay_time(pay_time);
			String pay_status = RequestHandler.getString(request, "pay_status");
			hpayorder.setPay_status(pay_status);
			String tick_off_status = RequestHandler.getString(request, "tick_off_status");
			hpayorder.setTick_off_status(tick_off_status);
			Date tick_off_time = RequestHandler.getDate(request, "tick_off_time");
			hpayorder.setTick_off_time(tick_off_time);
			String back_fee_status = RequestHandler.getString(request, "back_fee_status");
			hpayorder.setBack_fee_status(back_fee_status);
			Date back_time = RequestHandler.getDate(request, "back_time");
			hpayorder.setBack_time(back_time);
				
			hpayorderService.insertHPayOrder(hpayorder);
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHPayOrder", method = RequestMethod.POST)
	public String updateHPayOrder(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
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
			
			String pay_account = RequestHandler.getString(request, "pay_account");
			hpayorder.setPay_account(pay_account);
			
			Integer operator_id = RequestHandler.getInteger(request, "operator_id");
			hpayorder.setOperator_id(operator_id);
			
			String payee = RequestHandler.getString(request, "payee");
			hpayorder.setPayee(payee);
			
			Date create_time = RequestHandler.getDate(request, "create_time");
			hpayorder.setCreate_time(create_time);
			
			Date pay_time = RequestHandler.getDate(request, "pay_time");
			hpayorder.setPay_time(pay_time);
			
			String pay_status = RequestHandler.getString(request, "pay_status");
			hpayorder.setPay_status(pay_status);
			
			String tick_off_status = RequestHandler.getString(request, "tick_off_status");
			hpayorder.setTick_off_status(tick_off_status);
			
			Date tick_off_time = RequestHandler.getDate(request, "tick_off_time");
			hpayorder.setTick_off_time(tick_off_time);
			
			String back_fee_status = RequestHandler.getString(request, "back_fee_status");
			hpayorder.setBack_fee_status(back_fee_status);
			
			Date back_time = RequestHandler.getDate(request, "back_time");
			hpayorder.setBack_time(back_time);
			

			hpayorderService.updateHPayOrder(hpayorder);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHPayOrder", method = RequestMethod.POST)
	public String removeHPayOrder(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HPayOrder hpayorder = new HPayOrder();
			Integer o_id = RequestHandler.getInteger(request, "o_id");
			hpayorder.setO_id(o_id);

			hpayorderService.removeHPayOrder(hpayorder);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHPayOrder", method = RequestMethod.POST)
	public String removeAllHPayOrder(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String o_ids = RequestHandler.getString(request, "o_ids");
			if(o_ids != null){
				String[] o_idStr = o_ids.split(",");
				if(o_idStr != null && o_idStr.length != 0){
					for (String o_id : o_idStr) {
						HPayOrder hPayOrder = new HPayOrder();
						hPayOrder.setO_id(Integer.valueOf(o_id));
						hpayorderService.removeHPayOrder(hPayOrder);
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
	
	@RequestMapping(value = "/writeOff", method = RequestMethod.POST)
	public String writeOff(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String bh = null;
			String bh1 = null;
			int n = 0;
			Integer o_id = RequestHandler.getInteger(request, "o_id");
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMddHHmmss");
			if(o_id != null){
				HPayOrder hPayOrder = new HPayOrder();
				hPayOrder.setO_id(o_id);
				HPayOrder order = hpayorderService.getHPayOrder(hPayOrder);
				if(order!=null&&StringUtils.isNotBlank(order.getElectric_number())){
					String Electric_number_s = order.getElectric_number();
					if(Electric_number_s.endsWith(",")){
						Electric_number_s = Electric_number_s.substring(0, Electric_number_s.length()-1);
					}
					String[] electricStr = Electric_number_s.split(",");
					if(electricStr != null && electricStr.length != 0){
						for (String electric : electricStr) {
							JSONObject josn = hCommonService.hXTServiceQuery(electric, this.getIpAddr(request));
							if("00".equals(josn.getString("resultCode"))){//调单成功
								Integer TotalFee = Integer.valueOf(josn.getString("totalFeeStr"));
								log.info("------------手动销账金额------------->"+TotalFee);
								String serialNo = null;
								if(StringUtils.isNotBlank(order.getQuery_id())){
									serialNo = order.getQuery_id();
								}else{
									serialNo = "XZ" + sf1.format(new Date());
								}
								if(TotalFee>0){
									JSONObject josn1 = hCommonService.hXTServicePay(electric, this.getIpAddr(request), serialNo, josn.getString("totalFeeStr"), sf.format(new Date()).substring(4, 8), josn.getString("paymentInfo"),"ACT");
									if(!"00".equals(josn1.get("resultCode"))){
										n = n + 1;
										if(bh==null){
											bh = electric;
										}else{
											bh = bh + "," + electric;
										}
									}else{
										//记录子订单
										HSubOrder hSubOrder = new HSubOrder();
										hSubOrder.setO_id(order.getO_no());
										hSubOrder.setElectric(electric);
										HSubOrder hSubOrder1 = hsuborderService.getHSubOrder(hSubOrder);
										if(hSubOrder1!=null){
											hSubOrder1.setTick_off_status(null);
											hSubOrder1.setTick_off_time(new Date());
											hsuborderService.updateHSubOrder1(hSubOrder1);
										}
									}
								}
							}else{
								n = n + 1;
								if(bh1==null){
									bh1 = electric;
								}else{
									bh1 = bh + "," + electric;
								}
							}
						}
					}
				}else{
					writeErrorMsg("该笔订单销账异常", null, response);
				}
				if(n > 0){
					String ss = null;
					if(bh!=null){
						ss = "电表号：" + bh + "销账失败";
					}
					if(bh1!=null){
						if(ss==null){
							ss = "电表号：" + bh1 + "调单失败";
						}else{
							ss = ss + "，电表号：" + bh1 + "调单失败";
						}
					}
					writeErrorMsg(ss, null, response);
				}else{
					int trans_count = hpayorderService.getTransCountByCompany(order.getC_id());
					if(trans_count>0){
						hPayOrder.setTrans_count(trans_count+1);
					}else{
						hPayOrder.setTrans_count(1);
					}
					order.setTick_off_status("1");
					order.setPay_status("1");
					order.setTick_off_time(new Date());
					hpayorderService.updateHPayOrder(order);
					writeSuccessMsg("销账成功", null, response);
				}
			}else{
				writeErrorMsg("该笔订单销账异常", null, response);
			}
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/writeOffone", method = RequestMethod.POST)
	public String writeOffone(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String bh = null;
			String bh1 = null;
			int n = 0;
			Integer o_sub_id = RequestHandler.getInteger(request, "o_sub_id");
			String o_id = RequestHandler.getString(request, "o_id");//订单号
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMddHHmmss");
			SimpleDateFormat sf2 = new SimpleDateFormat("MMdd");
			if(o_sub_id != null){
				HSubOrder hSubOrder = new HSubOrder();
				hSubOrder.setO_sub_id(o_sub_id);
				hSubOrder = hsuborderService.getHSubOrder(hSubOrder);
				HPayOrder hPayOrder = new HPayOrder();
				hPayOrder.setO_no(o_id);
				HPayOrder order = hpayorderService.getHPayOrder(hPayOrder);
				if(hSubOrder!=null&&StringUtils.isNotBlank(hSubOrder.getElectric())){
					JSONObject josn = hCommonService.hXTServiceQuery(hSubOrder.getElectric(), this.getIpAddr(request));
					if("00".equals(josn.getString("resultCode"))){//调单成功
						Integer TotalFee = Integer.valueOf(josn.getString("totalFeeStr"));
						log.info("------------调单金额------------->"+TotalFee);
						log.info("------------记录金额------------->"+hSubOrder.getAmount());
						String serialNo = null;
						if(StringUtils.isNotBlank(order.getQuery_id())){
							serialNo = order.getQuery_id();
						}else{
							serialNo = "XZ" + sf1.format(new Date());
						}
						if(TotalFee>0&&TotalFee.equals(hSubOrder.getAmount())){
							String pay_type = "ACT";
							String settlementDate = o_id.substring(4, 8);
							if(order!=null&&"2".equals(order.getPay_type())){
								pay_type = "ACT1";
								HProxySendLog hProxySendLog1 = new HProxySendLog();
								hProxySendLog1.setSendStyle(2);
								hProxySendLog1.setStyle(6);
								hProxySendLog1.setRemark3(serialNo);
								hProxySendLog1 = hproxysendlogService.getHProxySendLog(hProxySendLog1);
								if(hProxySendLog1!=null&&StringUtils.isNotBlank(hProxySendLog1.getContent())&&hProxySendLog1.getContent().length()>2){
									int days = daysBetween(sf1.parse("20"+o_id.substring(2, 14)+"000000"), new Date());
									if(days>1){
										settlementDate = sf2.format(new Date());
									}else{
										settlementDate = hProxySendLog1.getContent().substring(46, 50);
									}
								}
							}else{
								int days = daysBetween(sf1.parse("20"+o_id.substring(2, 14)), new Date());
								if(days>1){
									settlementDate = sf2.format(new Date());
								}
							}
							
							JSONObject josn1 = hCommonService.hXTServicePay(hSubOrder.getElectric(), this.getIpAddr(request), serialNo, josn.getString("totalFeeStr"),settlementDate , josn.getString("paymentInfo"),pay_type);
							if(!"00".equals(josn1.get("resultCode"))){
								writeErrorMsg("该笔订单销账异常", null, response);
							}else{//销账成功
								//修改订单状态
								HSubOrder hSubOrder1 = new HSubOrder();
								hSubOrder1.setO_id(order.getO_no());
								hSubOrder1.setElectric(hSubOrder.getElectric());
								HSubOrder hSubOrder2 = hsuborderService.getHSubOrder(hSubOrder);
								if(hSubOrder1!=null){
									hSubOrder2.setTick_off_status("1");
									hSubOrder2.setTick_off_time(new Date());
									int ids = hsuborderService.updateHSubOrder1(hSubOrder2);
									if(ids>0){//判断订单下面所有的子单是否都已经销账
										boolean b = false;
										HSubOrder hSubOrder5 = new HSubOrder();
										hSubOrder5.setO_id(order.getO_no());
										List<HSubOrder> listS = hsuborderService.getHSubOrderBaseList(hSubOrder5);
										int count = 0;
										if(listS!=null&&listS.size()>0){
											for(HSubOrder sob:listS){
												String tick_off_status = sob.getTick_off_status();
												if(StringUtils.isNotBlank(tick_off_status)){
													if("1".equals(tick_off_status)){
														count = count + 1;
													}
												}
											}
											if(count==listS.size()){
												b = true;
											}
										}
										if(b){
											//获取公司信息、代理、服务人员
											HCompany company = new HCompany();
											company.setId(order.getC_id());
											company = hcompanyService.getHCompany(company);
											HAgent agent = null;
											ManageAdminUser agentAdmin = null;
											if(StringUtils.isNotBlank(company.getOneAgentOpenId())){
												agent = new HAgent();
												agent.setStatus(1);
												agent.setOpenId(company.getOneAgentOpenId());
												agent = hagentService.getHAgent(agent);
												if(agent!=null){
													agentAdmin = new ManageAdminUser();
													agentAdmin.setOneAgentOpenId(company.getOneAgentOpenId());
													agentAdmin.setRoleId(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
													agentAdmin = manageadminuserService.getManageAdminUser(agentAdmin);
												}
											}
											HAgentTwo agentTwo = null;
											ManageAdminUser agentTwoAdmin = null;
											if(StringUtils.isNotBlank(company.getTwoAgentOpenID())){
												agentTwo = new HAgentTwo();
												agentTwo.setStatus(1);
												agentTwo.setOpenId(company.getTwoAgentOpenID());
												agentTwo = hagenttwoService.getHAgentTwo(agentTwo);
												if(agentTwo!=null){
													agentTwoAdmin = new ManageAdminUser();
													agentTwoAdmin.setTwoAgentOpenId(company.getTwoAgentOpenID());
													agentTwoAdmin.setRoleId(Integer.valueOf(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID));
													agentTwoAdmin = manageadminuserService.getManageAdminUser(agentTwoAdmin);
												}
											}
											ManageAdminUser servicer = null;
											if(company.getServicerId()!=null){
												servicer = new ManageAdminUser();
												servicer.setAdminId(company.getServicerId());
												servicer = manageadminuserService.getManageAdminUser(servicer);
											}
											String Source = "ACT";
											if(order.getPay_type().equals("2")){
												Source = "ACT1";
											}
											String[] electrics = order.getElectric_number().split(",");
											hPayOrder = hCommonService.fenrun(order,company,electrics,order.getO_no(),agent,agentAdmin,agentTwo,agentTwoAdmin,servicer,order,order.getAmount(),Source);
											
											//修改订单销账状态
											order.setTick_off_status("1");
											order.setPay_status("1");
											order.setTick_off_time(new Date());
											order.setOneRate(hPayOrder.getOneRate());
											order.setTwoRate(hPayOrder.getTwoRate());
											order.setServerRate(hPayOrder.getServerRate());
											hpayorderService.updateHPayOrder(order);
										}
										writeSuccessMsg("销账成功", null, response);
									}
								}
							}
						}else{
							writeErrorMsg("该笔订单实时调单金额和存储记录不相等", null, response);
						}
					}
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/writeOffoneBak", method = RequestMethod.POST)
	public String writeOffoneBak(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String bh = null;
			String bh1 = null;
			int n = 0;
			Integer o_sub_id = RequestHandler.getInteger(request, "o_sub_id");
			String o_id = RequestHandler.getString(request, "o_id");//订单号
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMddHHmmss");
			if(o_sub_id != null){
				HSubOrder hSubOrder = new HSubOrder();
				hSubOrder.setO_sub_id(o_sub_id);
				hSubOrder = hsuborderService.getHSubOrder(hSubOrder);
				HPayOrder hPayOrder = new HPayOrder();
				hPayOrder.setO_no(o_id);
				HPayOrder order = hpayorderService.getHPayOrder(hPayOrder);
				if(hSubOrder!=null&&StringUtils.isNotBlank(hSubOrder.getElectric())){
					JSONObject josn = hCommonService.hXTServiceQuery(hSubOrder.getElectric(), this.getIpAddr(request));
					if("00".equals(josn.getString("resultCode"))){//调单成功
						Integer TotalFee = Integer.valueOf(josn.getString("totalFeeStr"));
						log.info("------------调单金额------------->"+TotalFee);
						log.info("------------记录金额------------->"+hSubOrder.getAmount());
						String serialNo = null;
						if(StringUtils.isNotBlank(order.getQuery_id())){
							serialNo = order.getQuery_id();
						}else{
							serialNo = "XZ" + sf1.format(new Date());
						}
						if(TotalFee>0&&TotalFee.equals(hSubOrder.getAmount())){
							String pay_type = "ACT";
							String settlementDate = o_id.substring(4, 8);
							if(order!=null&&"2".equals(order.getPay_type())){
								pay_type = "ACT1";
								HProxySendLog hProxySendLog1 = new HProxySendLog();
								hProxySendLog1.setSendStyle(2);
								hProxySendLog1.setStyle(6);
								hProxySendLog1.setRemark3(serialNo);
								hProxySendLog1 = hproxysendlogService.getHProxySendLog(hProxySendLog1);
								if(hProxySendLog1!=null&&StringUtils.isNotBlank(hProxySendLog1.getContent())&&hProxySendLog1.getContent().length()>2){
									settlementDate = hProxySendLog1.getContent().substring(46, 50);
								}
							}
							
							JSONObject josn1 = hCommonService.hXTServicePay(hSubOrder.getElectric(), this.getIpAddr(request), serialNo, josn.getString("totalFeeStr"),settlementDate , josn.getString("paymentInfo"),pay_type);
							if(!"00".equals(josn1.get("resultCode"))){
								writeErrorMsg("该笔订单销账异常", null, response);
							}else{//销账成功
								//修改订单状态
								HSubOrder hSubOrder1 = new HSubOrder();
								hSubOrder1.setO_id(order.getO_no());
								hSubOrder1.setElectric(hSubOrder.getElectric());
								HSubOrder hSubOrder2 = hsuborderService.getHSubOrder(hSubOrder);
								if(hSubOrder1!=null){
									hSubOrder2.setTick_off_status("1");
									hSubOrder2.setTick_off_time(new Date());
									int ids = hsuborderService.updateHSubOrder1(hSubOrder2);
									if(ids>0){//判断订单下面所有的子单是否都已经销账
										boolean b = false;
										HSubOrder hSubOrder5 = new HSubOrder();
										hSubOrder5.setO_id(order.getO_no());
										List<HSubOrder> listS = hsuborderService.getHSubOrderBaseList(hSubOrder5);
										int count = 0;
										if(listS!=null&&listS.size()>0){
											for(HSubOrder sob:listS){
												String tick_off_status = sob.getTick_off_status();
												if(StringUtils.isNotBlank(tick_off_status)){
													if("1".equals(tick_off_status)){
														count = count + 1;
													}
												}
											}
											if(count==listS.size()){
												b = true;
											}
										}
										if(b){
											double feeTotal1 = 0.0;
											double feeTotal2 = 0.0;
											double feeTotal3 = 0.0;
											String toOpenId = null;
											String toTwoOpenId = null;
											//获取公司信息、代理、服务人员
											HCompany company = new HCompany();
											company.setId(order.getC_id());
											company = hcompanyService.getHCompany(company);
											HAgent agent = null;
											ManageAdminUser agentAdmin = null;
											if(StringUtils.isNotBlank(company.getOneAgentOpenId())){
												agent = new HAgent();
												agent.setStatus(1);
												agent.setOpenId(company.getOneAgentOpenId());
												agent = hagentService.getHAgent(agent);
												if(agent!=null){
													agentAdmin = new ManageAdminUser();
													agentAdmin.setOneAgentOpenId(company.getOneAgentOpenId());
													agentAdmin.setRoleId(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
													agentAdmin = manageadminuserService.getManageAdminUser(agentAdmin);
												}
											}
											HAgentTwo agentTwo = null;
											ManageAdminUser agentTwoAdmin = null;
											if(StringUtils.isNotBlank(company.getTwoAgentOpenID())){
												agentTwo = new HAgentTwo();
												agentTwo.setStatus(1);
												agentTwo.setOpenId(company.getTwoAgentOpenID());
												agentTwo = hagenttwoService.getHAgentTwo(agentTwo);
												if(agentTwo!=null){
													agentTwoAdmin = new ManageAdminUser();
													agentTwoAdmin.setTwoAgentOpenId(company.getTwoAgentOpenID());
													agentTwoAdmin.setRoleId(Integer.valueOf(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID));
													agentTwoAdmin = manageadminuserService.getManageAdminUser(agentTwoAdmin);
												}
											}
											ManageAdminUser servicer = null;
											if(company.getServicerId()!=null){
												servicer = new ManageAdminUser();
												servicer.setAdminId(company.getServicerId());
												servicer = manageadminuserService.getManageAdminUser(servicer);
											}
											for(HSubOrder sob:listS){//遍历电表
												//代理账户操作
												//获取分润比例
												HAmmeterInfo amm = new HAmmeterInfo();
												amm.setAmmeter_no(sob.getElectric());
												amm.setDelete_state(1);
												amm.setPay_status("1");
												amm.setC_id(company.getId());
												amm = hammeterinfoService.getHAmmeterInfo(amm);
												HProfitRatio ratio = new HProfitRatio();
												ratio.setId(amm.getProfit_id());
												ratio = hprofitratioService.getHProfitRatio(ratio);
												//查询电表号的金额
												//记录子订单
												if(agent!=null&&agent.getStatus()==1&&agent.getCheck_status()==1){
													HUserAccount agentAccount = new HUserAccount();
													agentAccount.setOneAgentOpenId(agent.getOpenId());
													agentAccount.setRole_id(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
													agentAccount = huseraccountService.getHUserAccount(agentAccount);
													if(agentAccount!=null){
														//处理账户
														feeTotal1 = feeTotal1 + hCommonService.udpateAccount(agentAccount, sob, ratio.getOnt_agent_ratio(), order, sob.getElectric(), o_id,1,company);
														toOpenId = agentAccount.getOpenId();
													}else{
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
														huseraccountService.insertHUserAccount(agentAccount);
														toOpenId = agentAccount.getOpenId();
														feeTotal1 = feeTotal1 + hCommonService.udpateAccount(agentAccount, sob, ratio.getOnt_agent_ratio(), order, sob.getElectric(), o_id,1,company);
													}
												}
												if(agentTwo!=null&&agentTwo.getStatus()==1&&agentTwo.getCheck_status()==1){
													HUserAccount agentAccount = new HUserAccount();
													agentAccount.setTwoAgentOpenId(agentTwo.getOpenId());
													agentAccount.setRole_id(Integer.valueOf(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID));
													agentAccount = huseraccountService.getHUserAccount(agentAccount);
													if(agentAccount!=null){
														//处理账户
														feeTotal2 = feeTotal2 + hCommonService.udpateAccount(agentAccount, sob, ratio.getTwo_agent_ratio(), order, sob.getElectric(), o_id,2,company);
														toTwoOpenId = agentAccount.getOpenId();
													}else{
														//创建账户
														agentAccount = new HUserAccount();
														agentAccount.setOpenId(agentTwoAdmin.getOpenId());
														agentAccount.setTotalFee(new BigDecimal("0.00"));
														agentAccount.setCreateTime(new Date());
														agentAccount.setStatus(1);
														agentAccount.setTwoAgentOpenId(agentTwo.getOpenId());
														agentAccount.setTwoAgentName(agentTwo.getName());
														agentAccount.setNickName(agentTwo.getName());
														agentAccount.setPhone(agentTwo.getMobile1());
														agentAccount.setMobile(agentTwo.getMobile2());
														agentAccount.setRole_id(Integer.valueOf(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID));
														huseraccountService.insertHUserAccount(agentAccount);
														toTwoOpenId = agentAccount.getOpenId();
														feeTotal2 = feeTotal2 + hCommonService.udpateAccount(agentAccount, sob, ratio.getTwo_agent_ratio(), order, sob.getElectric(), o_id,2,company);
													}
												}
												if(servicer!=null&&servicer.getState()==1){
													HUserAccount agentAccount = new HUserAccount();
													agentAccount.setServicerId(servicer.getAdminId());
													agentAccount.setRole_id(Integer.valueOf(FileUploadConstants.SERVICER_MANAGE_ROLEID));
													agentAccount = huseraccountService.getHUserAccount(agentAccount);
													if(agentAccount!=null){
														//处理账户
														feeTotal3 = feeTotal3 + hCommonService.udpateAccount(agentAccount, sob, ratio.getPersonal_ratio(), order, sob.getElectric(), o_id,3,company);
													}else{
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
														huseraccountService.insertHUserAccount(agentAccount);
														feeTotal3 = feeTotal3 + hCommonService.udpateAccount(agentAccount, sob, ratio.getPersonal_ratio(), order, sob.getElectric(), o_id,3,company);
													}
												}
											}
											
											BigDecimal bg1 = new BigDecimal(feeTotal1);
											double f1 = bg1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
											BigDecimal bg2 = new BigDecimal(feeTotal2);
											double f2 = bg2.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
											BigDecimal bg3 = new BigDecimal(feeTotal3);
											double f3 = bg3.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
											if(agent!=null&&agent.getStatus()==1&&agent.getCheck_status()==1&&agentAdmin!=null&&agentAdmin.getState()==1){
												huseraccountService.sendCZTempltMsg(toOpenId,this.getMoney(order.getAmount()), order.getO_no(), f1+"", company.getContact_name(), company.getContact_phone(), company.getId(), company.getName());
											}
											if(agentTwo!=null&&agentTwo.getStatus()==1&&agentTwo.getCheck_status()==1&&agentTwoAdmin!=null&&agentTwoAdmin.getState()==1){
												huseraccountService.sendCZTempltMsg(toTwoOpenId,this.getMoney(order.getAmount()), order.getO_no(), f2+"", company.getContact_name(), company.getContact_phone(), company.getId(), company.getName());
											}
											if(servicer!=null&&servicer.getState()==1){
												huseraccountService.sendCZTempltMsg(servicer.getOpenId(),this.getMoney(order.getAmount()), order.getO_no(), f3+"", company.getContact_name(), company.getContact_phone(), company.getId(), company.getName());
											}
											//修改订单销账状态
											order.setTick_off_status("1");
											order.setPay_status("1");
											order.setTick_off_time(new Date());
											hPayOrder.setOneRate(new BigDecimal(feeTotal1));
											hPayOrder.setTwoRate(new BigDecimal(feeTotal2));
											hPayOrder.setServerRate(new BigDecimal(feeTotal3));
											hpayorderService.updateHPayOrder(order);
										}
										writeSuccessMsg("销账成功", null, response);
									}
								}
							}
						}else{
							writeErrorMsg("该笔订单实时调单金额和存储记录不相等", null, response);
						}
					}
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			writeErrorMsg(e.getLocalizedMessage(), null, response);
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
	
	@RequestMapping(value = "/addFP", method = RequestMethod.GET)
	public String addFP(HttpServletRequest request, HttpServletResponse response, Model model){
		String o_no = RequestHandler.getString(request, "o_no");
		
		model.addAttribute("o_no", o_no);
		return "/hPayOrder/hFpAdd";
	}
	
	@RequestMapping(value = "/showDetail", method = RequestMethod.GET)
	public String showDetail(HttpServletRequest request, HttpServletResponse response, Model model){
		String o_no = RequestHandler.getString(request, "o_no");
		String fp_order_id = RequestHandler.getString(request, "fp_order_id");
		Integer o_id = RequestHandler.getInteger(request, "o_id");
		Integer pay_status = RequestHandler.getInteger(request, "pay_status");
		try{
			HSubOrder hSubOrder = new HSubOrder();
			hSubOrder.setO_id(o_no);
			List<HSubOrder> list = hsuborderService.getHSubOrderBaseList(hSubOrder);
			if(list!=null&&list.size()>0){
				for(HSubOrder subOrder:list){
					JSONObject json = hCommonService.hXTServiceQuery(subOrder.getElectric(), this.getIpAddr(request));
					
					if("00".equals(json.getString("resultCode"))){
						subOrder.setElectric_address(json.getJSONObject("resultInfo").getString("address"));
						subOrder.setNow_totalFee(json.getString("totalFee"));
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
					}
				}
			}
			model.addAttribute("list", list);
		}catch(Exception e){
			e.printStackTrace();
		}
		model.addAttribute("o_no", o_no);
		model.addAttribute("pay_status", pay_status);
		model.addAttribute("fp_order_id", fp_order_id);
		return "/hPayOrder/showDetails";
	}
	
	@RequestMapping(value = "/toWriteOff", method = RequestMethod.GET)
	public String toWriteOff(HttpServletRequest request, HttpServletResponse response, Model model){
		String o_no = RequestHandler.getString(request, "o_no");
		String fp_order_id = RequestHandler.getString(request, "fp_order_id");
		Integer o_id = RequestHandler.getInteger(request, "o_id");
		Integer pay_status = RequestHandler.getInteger(request, "pay_status");
		try{
			HSubOrder hSubOrder = new HSubOrder();
			hSubOrder.setO_id(o_no);
			List<HSubOrder> list = hsuborderService.getHSubOrderBaseList(hSubOrder);
			if(list!=null&&list.size()>0){
				for(HSubOrder subOrder:list){
					JSONObject json = hCommonService.hXTServiceQuery(subOrder.getElectric(), this.getIpAddr(request));
					
					if("00".equals(json.getString("resultCode"))){
						subOrder.setElectric_address(json.getJSONObject("resultInfo").getString("address"));
						subOrder.setNow_totalFee(json.getString("totalFee"));
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
					}
				}
			}
			model.addAttribute("list", list);
		}catch(Exception e){
			e.printStackTrace();
		}
		model.addAttribute("o_no", o_no);
		model.addAttribute("pay_status", pay_status);
		model.addAttribute("fp_order_id", fp_order_id);
		return "/hPayOrder/writeOff";
	}
	
	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
	public String exportExcel(HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			HPayOrder hpayorder = new HPayOrder();
			
			//角色条件处理
			ManageAdminUser loginUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			if(loginUser.getRoleId().toString().equals(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID)){
				hpayorder.setOneAgentOpenId(loginUser.getOneAgentOpenId());
			}else if(loginUser.getRoleId().toString().equals(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID)){
				hpayorder.setTwoAgentOpenID(loginUser.getTwoAgentOpenId());
			}else if(loginUser.getRoleId().toString().equals(FileUploadConstants.SERVICER_MANAGE_ROLEID)){
				hpayorder.setServicerId(loginUser.getServicerId());
			}
			
			Integer o_id = RequestHandler.getInteger(request, "o_id");
			hpayorder.setO_id(o_id);
			
			String o_no = RequestHandler.getString(request, "o_no");
			hpayorder.setO_no(o_no);
			
			Integer fr = RequestHandler.getInteger(request, "fr");
			hpayorder.setFr(fr);
			
			String query_id = RequestHandler.getString(request, "query_id");
			hpayorder.setQuery_id(query_id);
			
			String pay_ip = RequestHandler.getString(request, "pay_ip");
			hpayorder.setPay_ip(pay_ip);
			
			Integer c_id = RequestHandler.getInteger(request, "c_id");
			hpayorder.setC_id(c_id);
			
			Integer yw_id = RequestHandler.getInteger(request, "yw_id");
			hpayorder.setYw_id(yw_id);
			
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
			
			String c_name = RequestHandler.getString(request, "c_name");
			hpayorder.setC_name(c_name);
			
			String pay_account = RequestHandler.getString(request, "pay_account");
			hpayorder.setPay_account(pay_account);
			
			Integer operator_id = RequestHandler.getInteger(request, "operator_id");
			hpayorder.setOperator_id(operator_id);
			
			String payee = RequestHandler.getString(request, "payee");
			hpayorder.setPayee(payee);
			
			Date create_time = RequestHandler.getDate(request, "create_time");
			hpayorder.setCreate_time(create_time);
			
			Date pay_time = RequestHandler.getDate(request, "pay_time");
			hpayorder.setPay_time(pay_time);
			
			String pay_status = RequestHandler.getString(request, "pay_status");
			hpayorder.setPay_status(pay_status);
			
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
			
			String create_start_time_str = RequestHandler.getString(request, "create_start_time");
			if(StringUtils.isNotBlank(create_start_time_str)){
				create_start_time_str = create_start_time_str + " 00:00:00";
				hpayorder.setCreate_start_time(sf.parse(create_start_time_str));
			}
			String create_end_time_str = RequestHandler.getString(request, "create_end_time");
			if(StringUtils.isNotBlank(create_end_time_str)){
				create_end_time_str = create_end_time_str + " 23:59:59";
				hpayorder.setCreate_end_time(sf.parse(create_end_time_str));
			}
			
			Double startTotalFeeD = RequestHandler.getDouble(request, "startTotalFee");
			Double endTotalFeeD = RequestHandler.getDouble(request, "endTotalFee");
			if(startTotalFeeD!=null){
				BigDecimal sFee = new BigDecimal(startTotalFeeD*100);
		        double f11 = sFee.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		        BigDecimal bg111 = new BigDecimal(f11);
		        int f1 = bg111.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
		        hpayorder.setStartTotalFee(f1);
			}
			if(endTotalFeeD!=null){
				BigDecimal sFee = new BigDecimal(endTotalFeeD*100);
				double f11 = sFee.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				BigDecimal bg111 = new BigDecimal(f11);
				int f1 = bg111.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
				hpayorder.setEndTotalFee(f1);
			}

			ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			if(adminUser.getRoleId().equals(Integer.valueOf(FileUploadConstants.YWY_ROLE_ID))){
				hpayorder.setYw_id(adminUser.getAdminId());
			}
			
			String agentOneOpenId = RequestHandler.getString(request, "agentOneOpenId");
			String agentTwoOpenId = RequestHandler.getString(request, "agentTwoOpenId");
			Integer servicerId = RequestHandler.getInteger(request, "servicerId");
			hpayorder.setOneAgentOpenId(agentOneOpenId);
			hpayorder.setTwoAgentOpenID(agentTwoOpenId);
			hpayorder.setServicerId(servicerId);
			
			Integer is_zz = RequestHandler.getInteger(request, "is_zz");
			hpayorder.setIs_zz(is_zz);
			
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hpayorder.setSortColumn(sortColumn);
			
			int hpayorderListCount = hpayorderService.getHPayOrderListCount(hpayorder);
			List<HPayOrder> hpayorderList = null;
			if ( hpayorderListCount > 0 )
			{
				hpayorderList = hpayorderService.getHPayOrderBaseList(hpayorder);
				Iterator<HPayOrder> it = hpayorderList.iterator();
				while(it.hasNext()){
					HPayOrder order = (HPayOrder)it.next();
					String totalFeeStr1 = null;
					if(order.getAmount()>0){
						if(String.valueOf(order.getAmount()).length()>2){
							int fen = order.getAmount()%100;
							if(fen>0&&fen<10){
								totalFeeStr1 = order.getAmount()/100 + ".0" + fen;
							}else if(fen>=10){
								totalFeeStr1 = order.getAmount()/100 + "." + fen;
							}else{
								totalFeeStr1 = order.getAmount()/100 + ".00";
							}
							order.setAmountStr(totalFeeStr1);
						}else if(String.valueOf(order.getAmount()).length()==1){
							order.setAmountStr("0.0"+order.getAmount());
						}else{
							order.setAmountStr("0."+order.getAmount());
						}
					}else{
						order.setAmountStr("0.00");
					}
				}
				LinkedList list = new LinkedList();
				list.addAll(hpayorderList);
				LinkedList fields = new LinkedList();
				fields.add("o_no");
				fields.add("query_id");
				fields.add("c_id");
				fields.add("c_name");
				fields.add("amountStr");
				fields.add("pay_type");
				fields.add("pay_time");
				fields.add("pay_status");
				fields.add("create_time");
				fields.add("tick_off_status");
				fields.add("tick_off_time");
				fields.add("back_fee_status");
				fields.add("back_time");
				fields.add("yw_name");
				fields.add("electric_number");
				fields.add("contact_name");
				fields.add("contact_phone");
				fields.add("oneAgentName");
				fields.add("twoAgentName");
				fields.add("servicerName");
				LinkedList titles = new LinkedList();
				titles.add("订单号");
				titles.add("交易流水号");
				titles.add("单位ID");
				titles.add("单位名称");
				titles.add("支付金额");
				titles.add("支付类型");
				titles.add("支付时间");
				titles.add("支付状态");
				titles.add("订单生成时间");
				titles.add("销账状态");
				titles.add("销账时间");
				titles.add("是否退费");
				titles.add("退费时间");
				titles.add("所属业务员");
				titles.add("相关电表号");
				titles.add("单位联系人姓名");
				titles.add("单位联系人电话");
				titles.add("客户经理");
				titles.add("代理");
				titles.add("服务人员");
				String path = hCommonService.excleExport(list, fields, titles, HPayOrder.class, "订单管理",request);
				writeSuccessMsg("成功", path, response);
			} else
			{
				writeErrorMsg("无可导出内容", null, response);
			}
		} catch (Exception e) {
			writeErrorMsg("系统错误", null, response);
		}
		return null;
	}
	@RequestMapping(value = "/exportUnpayExcel", method = RequestMethod.GET)
	public String exportUnpayExcel(HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			HPayOrder hpayorder = new HPayOrder();
			
			//角色条件处理
			ManageAdminUser loginUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			if(loginUser.getRoleId().toString().equals(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID)){
				hpayorder.setOneAgentOpenId(loginUser.getOneAgentOpenId());
			}else if(loginUser.getRoleId().toString().equals(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID)){
				hpayorder.setTwoAgentOpenID(loginUser.getTwoAgentOpenId());
			}else if(loginUser.getRoleId().toString().equals(FileUploadConstants.SERVICER_MANAGE_ROLEID)){
				hpayorder.setServicerId(loginUser.getServicerId());
			}
			
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
			
			Integer yw_id = RequestHandler.getInteger(request, "yw_id");
			hpayorder.setYw_id(yw_id);
			
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
			
			String c_name = RequestHandler.getString(request, "c_name");
			hpayorder.setC_name(c_name);
			
			String pay_account = RequestHandler.getString(request, "pay_account");
			hpayorder.setPay_account(pay_account);
			
			String contact_phone = RequestHandler.getString(request, "contact_phone");
			hpayorder.setContact_phone(contact_phone);
			
			Integer operator_id = RequestHandler.getInteger(request, "operator_id");
			hpayorder.setOperator_id(operator_id);
			
			String payee = RequestHandler.getString(request, "payee");
			hpayorder.setPayee(payee);
			
			Date create_time = RequestHandler.getDate(request, "create_time");
			hpayorder.setCreate_time(create_time);
			
			Date pay_time = RequestHandler.getDate(request, "pay_time");
			hpayorder.setPay_time(pay_time);
			
			String pay_status = RequestHandler.getString(request, "pay_status");
			hpayorder.setPay_status(pay_status);
			
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
				hpayorder.setTick_off_time(sf.parse(tick_start_time_str));
			}
			String tick_end_time_str = RequestHandler.getString(request, "tick_end_time");
			if(StringUtils.isNotBlank(tick_end_time_str)){
				tick_end_time_str = tick_end_time_str + " 23:59:59";
				hpayorder.setTick_end_time(sf.parse(tick_end_time_str));
			}
			
			String create_start_time_str = RequestHandler.getString(request, "create_start_time");
			if(StringUtils.isNotBlank(create_start_time_str)){
				create_start_time_str = create_start_time_str + " 00:00:00";
				hpayorder.setCreate_start_time(sf.parse(create_start_time_str));
			}
			String create_end_time_str = RequestHandler.getString(request, "create_end_time");
			if(StringUtils.isNotBlank(create_end_time_str)){
				create_end_time_str = create_end_time_str + " 23:59:59";
				hpayorder.setCreate_end_time(sf.parse(create_end_time_str));
			}
			
			ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			if(adminUser.getRoleId().equals(Integer.valueOf(FileUploadConstants.YWY_ROLE_ID))){
				hpayorder.setYw_id(adminUser.getAdminId());
			}
			
			Integer is_zz = RequestHandler.getInteger(request, "is_zz");
			hpayorder.setIs_zz(is_zz);
			
			Calendar cal = Calendar.getInstance();
		    int month = cal.get(Calendar.MONTH) + 1;
		    int year = cal.get(Calendar.YEAR);

			sf.format(new Date());
			String stime = year + "-" + month + "-1";
			
			hpayorder.setCreate_start_time(sf.parse(stime + " 00:00:00"));
			hpayorder.setCreate_end_time(new Date());
			
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hpayorder.setSortColumn(sortColumn);
			
			int hpayorderListCount = hpayorderService.getHPayOrderListCount(hpayorder);
			List<HPayOrder> hpayorderList = null;
			if ( hpayorderListCount > 0 )
			{
				hpayorderList = hpayorderService.getHPayOrderBaseList(hpayorder);
				Iterator<HPayOrder> it = hpayorderList.iterator();
				while(it.hasNext()){
					HPayOrder order = (HPayOrder)it.next();
					String totalFeeStr1 = null;
					if(order.getAmount()>0){
						if(String.valueOf(order.getAmount()).length()>2){
							int fen = order.getAmount()%100;
							if(fen>0&&fen<10){
								totalFeeStr1 = order.getAmount()/100 + ".0" + fen;
							}else if(fen>=10){
								totalFeeStr1 = order.getAmount()/100 + "." + fen;
							}else{
								totalFeeStr1 = order.getAmount()/100 + ".00";
							}
							order.setAmountStr(totalFeeStr1);
						}else if(String.valueOf(order.getAmount()).length()==1){
							order.setAmountStr("0.0"+order.getAmount());
						}else{
							order.setAmountStr("0."+order.getAmount());
						}
					}else{
						order.setAmountStr("0.00");
					}
				}
				LinkedList list = new LinkedList();
				list.addAll(hpayorderList);
				LinkedList fields = new LinkedList();
				fields.add("o_no");
				fields.add("query_id");
				fields.add("c_id");
				fields.add("c_name");
				fields.add("amountStr");
				fields.add("pay_type");
				fields.add("pay_time");
				fields.add("pay_status");
				fields.add("create_time");
				fields.add("tick_off_status");
				fields.add("tick_off_time");
				fields.add("back_fee_status");
				fields.add("back_time");
				fields.add("yw_name");
				fields.add("electric_number");
				LinkedList titles = new LinkedList();
				titles.add("订单号");
				titles.add("交易流水号");
				titles.add("单位ID");
				titles.add("单位名称");
				titles.add("支付金额");
				titles.add("支付类型");
				titles.add("支付时间");
				titles.add("支付状态");
				titles.add("订单生成时间");
				titles.add("销账状态");
				titles.add("销账时间");
				titles.add("是否退费");
				titles.add("退费时间");
				titles.add("所属业务员");
				titles.add("相关电表号");
				String path = hCommonService.excleExport(list, fields, titles, HPayOrder.class, "本月制单没有缴费的单位",request);
				writeSuccessMsg("成功", path, response);
			} else
			{
				writeErrorMsg("无可导出内容", null, response);
			}
		} catch (Exception e) {
			writeErrorMsg("系统错误", null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/dealFP", method = RequestMethod.GET)
	public String dealFP(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try{
			HPayOrder hpayorder = new HPayOrder();
			hpayorder.setPay_status("1");
			hpayorder.setTick_off_status("1");
			List<HPayOrder> list = hpayorderService.getHPayOrderBaseList(hpayorder);
			if(list!=null&&list.size()>0){
				for(HPayOrder order:list){
//					int countss = 0 ;
//					//查询子订单
//					HSubOrder hSubOrder = new HSubOrder();
//					hSubOrder.setO_id(order.getO_no());
//					List<HSubOrder> listSub = hsuborderService.getHSubOrderBaseList(hSubOrder);
//					if(listSub!=null&&listSub.size()>0){
//						for(HSubOrder sub:listSub){
//							HFpOrder hFpOrder = new HFpOrder();
//							hFpOrder.setOrderId(sub.getSub_id());
//							int count = hfporderService.getHFpOrderListCount(hFpOrder);
//							if(count>0){
//								countss = countss + 1;
//							}
//						}
//						if(countss!=0&&countss==listSub.size()){
//							order.setIs_fp(1);
//							hpayorderService.updateHPayOrder(order);
//						}
//					}
					HFp hFp = new HFp();
					hFp.setOrderNumber(order.getO_no());
					int count = hfpService.getHFpListCount(hFp);
					if(count>0){
						order.setIs_fp(1);
						hpayorderService.updateHPayOrder(order);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try{
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//查询所有有效的交易
			HPayOrder hPayOrder = new HPayOrder();
			hPayOrder.setPay_status("1");
			hPayOrder.setPay_start_time(sf.parse("2016-11-29 00:00:00"));
			hPayOrder.setTick_off_status("1");
			List<HPayOrder> list = hpayorderService.getHPayOrderBaseList(hPayOrder);
			if(list!=null&&list.size()>0){
				int i = 0 ;
				for(HPayOrder o:list){
					
					System.out.println("-----"+(++i)+"------>"+o.getO_no());
					//查询客户经理
					if(StringUtils.isNotBlank(o.getOneAgentOpenId())){
						ManageAdminUser adminUser = new ManageAdminUser();
						adminUser.setOneAgentOpenId(o.getOneAgentOpenId());
						adminUser.setRoleId(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
						adminUser = manageadminuserService.getManageAdminUser(adminUser);
						
						
						HUserAccount agentAccount = new HUserAccount();
						agentAccount.setOneAgentOpenId(o.getOneAgentOpenId());
						agentAccount.setRole_id(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
						agentAccount = huseraccountService.getHUserAccount(agentAccount);
						if(agentAccount==null){
							//创建账户
							agentAccount = new HUserAccount();
							agentAccount.setOpenId(adminUser.getOpenId());
							agentAccount.setTotalFee(new BigDecimal("0.00"));
							agentAccount.setCreateTime(new Date());
							agentAccount.setStatus(1);
							agentAccount.setOneAgentOpenId(o.getOneAgentOpenId());
							agentAccount.setOneAgentName(o.getOneAgentName());
							agentAccount.setNickName(adminUser.getNickName());
							agentAccount.setPhone(adminUser.getMobile());
							agentAccount.setMobile(adminUser.getMobile());
							agentAccount.setRole_id(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
							huseraccountService.insertHUserAccount(agentAccount);
						}
						hpayorderService.udpateAccount(agentAccount, o, 0.001D);
					}
					
					if(StringUtils.isNotBlank(o.getTwoAgentOpenID())){
						ManageAdminUser adminUser = new ManageAdminUser();
						adminUser.setTwoAgentOpenId(o.getTwoAgentOpenID());
						adminUser.setRoleId(Integer.valueOf(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID));
						adminUser = manageadminuserService.getManageAdminUser(adminUser);
						
						HUserAccount agentAccount = new HUserAccount();
						agentAccount.setTwoAgentOpenId(o.getTwoAgentOpenID());
						agentAccount.setRole_id(Integer.valueOf(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID));
						agentAccount = huseraccountService.getHUserAccount(agentAccount);
						if(agentAccount==null){
							//创建账户
							agentAccount = new HUserAccount();
							agentAccount.setOpenId(adminUser.getOpenId());
							agentAccount.setTotalFee(new BigDecimal("0.00"));
							agentAccount.setCreateTime(new Date());
							agentAccount.setStatus(1);
							agentAccount.setOneAgentOpenId(o.getOneAgentOpenId());
							agentAccount.setOneAgentName(o.getOneAgentName());
							agentAccount.setTwoAgentOpenId(o.getTwoAgentOpenID());
							agentAccount.setTwoAgentName(o.getTwoAgentName());
							agentAccount.setNickName(adminUser.getNickName());
							agentAccount.setPhone(adminUser.getMobile());
							agentAccount.setMobile(adminUser.getMobile());
							agentAccount.setRole_id(Integer.valueOf(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID));
							huseraccountService.insertHUserAccount(agentAccount);
						}
						hpayorderService.udpateAccount(agentAccount, o, 0.001D);
					}
					
					if(o.getServicerId()!=null){
						ManageAdminUser adminUser = new ManageAdminUser();
						adminUser.setAdminId(o.getServicerId());
						adminUser = manageadminuserService.getManageAdminUser(adminUser);
						
						HUserAccount agentAccount = new HUserAccount();
						agentAccount.setServicerId(o.getServicerId());
						agentAccount.setRole_id(Integer.valueOf(FileUploadConstants.SERVICER_MANAGE_ROLEID));
						agentAccount = huseraccountService.getHUserAccount(agentAccount);
						if(agentAccount==null){
							//创建账户
							agentAccount = new HUserAccount();
							agentAccount.setServicerId(o.getServicerId());
							agentAccount.setOpenId(adminUser.getOpenId());
							agentAccount.setTotalFee(new BigDecimal("0.00"));
							agentAccount.setCreateTime(new Date());
							agentAccount.setStatus(1);
							agentAccount.setServicerName(adminUser.getRealName());
							agentAccount.setNickName(adminUser.getNickName());
							agentAccount.setPhone(adminUser.getPhone());
							agentAccount.setMobile(adminUser.getMobile());
							agentAccount.setRole_id(Integer.valueOf(FileUploadConstants.SERVICER_MANAGE_ROLEID));
							huseraccountService.insertHUserAccount(agentAccount);
						}
						
						hpayorderService.udpateAccount(agentAccount, o, 0.0002D);
					}
				}
			}
			System.out.println("------size------->"+list.size());
		}catch(Exception e){
			e.printStackTrace();
		}
		writeSuccessMsg("ok", null, response);
		return null;
	}
	
	/**  
     * 计算两个日期之间相差的天数  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     * @throws ParseException  
     */    
    public static int daysBetween(Date smdate,Date bdate) throws Exception    
    {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));           
    }    
    
    public static void main(String args[]) throws Exception{
    	String o_id = "PO1707171532265168";
    	SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMddHHmmss");
    	System.out.println(daysBetween(sf1.parse("20"+o_id.substring(2, 14)), new Date()));
    }
}
