package com.hxt.hPublic.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.base.controller.BaseController;
import com.base.utils.FileUploadConstants;
import com.hxt.hAgent.model.HAgent;
import com.hxt.hAgent.service.HAgentService;
import com.hxt.hAmmeterInfo.model.HAmmeterInfo;
import com.hxt.hAmmeterInfo.service.HAmmeterInfoService;
import com.hxt.hCompany.model.HCompany;
import com.hxt.hCompany.service.HCompanyService;
import com.hxt.hPayOrder.model.HPayOrder;
import com.hxt.hPayOrder.service.HPayOrderService;
import com.hxt.hProfitRatio.model.HProfitRatio;
import com.hxt.hProfitRatio.service.HProfitRatioService;
import com.hxt.hSubOrder.model.HSubOrder;
import com.hxt.hSubOrder.service.HSubOrderService;
import com.hxt.hUserAccount.model.HUserAccount;
import com.hxt.hUserAccount.service.HUserAccountService;
import com.hxt.hUserAccountDetail.model.HUserAccountDetail;
import com.hxt.hUserAccountDetail.service.HUserAccountDetailService;
import com.sys.adminUserRole.model.AdminUserRole;
import com.sys.adminUserRole.service.AdminUserRoleService;
import com.sys.manageAdminUser.model.ManageAdminUser;
import com.sys.manageAdminUser.service.ManageAdminUserService;
/**
 * @author	zhangyiyang
 * @time	2016年08月02日 22:11:34
 */
@Controller
@RequestMapping("/old")
public class ProcessDataController extends BaseController
{
	private static Logger logger = Logger.getLogger(ProcessDataController.class);  
	@Autowired
	private ManageAdminUserService manageadminuserService = null;
	@Autowired
	private HAgentService hagentService = null;
	@Autowired
	private HUserAccountService huseraccountService = null;
	@Autowired
	private HUserAccountDetailService huseraccountdetailService = null;
	@Autowired
	private HAmmeterInfoService hAmmeterInfoService = null;
	@Autowired
	private HCompanyService hcompanyService = null;
	@Autowired
	private HSubOrderService hsuborderService = null;
	@Autowired
	private HPayOrderService hpayorderService = null;
	@Autowired
	private AdminUserRoleService adminuserroleService = null;
	@Autowired
	private HProfitRatioService hprofitratioService = null;
	
	private boolean processSwitch = true;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/process", method = RequestMethod.GET)
	public String process(HttpServletRequest request, HttpServletResponse response, Model model,Integer p)
	{
		processSwitch = false;
		List errList = new ArrayList();
		try{
			//获取默认分润比例
			HProfitRatio rate = new HProfitRatio();
			rate.setIs_default(1);
			rate = hprofitratioService.getHProfitRatio(rate);
			//税率
			BigDecimal taxRate = new BigDecimal(FileUploadConstants.TAX_RATE);
			//获取所有业务员
			ManageAdminUser param = new ManageAdminUser();
			param.setRoleId(Integer.valueOf(FileUploadConstants.YWY_ROLE_ID));
			List<ManageAdminUser> ywyList = manageadminuserService.getManageAdminUserBaseList(param);
			for(ManageAdminUser ywy :ywyList){
				try{
					//创建一级代理
					HAgent agent = new HAgent();
					String uuid = UUID.randomUUID().toString();
					uuid = uuid.replaceAll("-","");
					agent.setOpenId(uuid);
					agent.setName(ywy.getRealName());
					agent.setMobile1(ywy.getMobile());
					agent.setCreate_time(new Date());
					hagentService.insertHAgent(agent);
					//更新ywy的oneagentopenid
					ywy.setOneAgentOpenId(uuid);
					manageadminuserService.updateManageAdminUser(ywy);
					//修改roleId
					AdminUserRole role = new AdminUserRole();
					role.setAdminId(ywy.getAdminId());
					role = adminuserroleService.getAdminUserRole(role);
					role.setRoleId(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
					adminuserroleService.updateAdminUserRole(role);
					//创建账户
			/*		HUserAccount account = new HUserAccount();
					account = new HUserAccount();
					account.setOneAgentOpenId(uuid);
					account.setOpenId(ywy.getOpenId());
					account.setTotalFee(new BigDecimal("0.00"));
					account.setCreateTime(new Date());
					account.setStatus(1);
					account.setOneAgentName(ywy.getRealName());
					account.setNickName(ywy.getNickName());
					account.setPhone(ywy.getPhone());
					account.setMobile(ywy.getMobile());
					account.setRole_id(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
					int accId = huseraccountService.insertHUserAccount(account);
					account.setId(accId);*/
					//获取下属公司
					String contextPath = request.getSession().getServletContext().getRealPath("/");
					HCompany com = new HCompany();
					com.setYwyId(ywy.getAdminId());
					com.setVerify_status(1);
					List<HCompany> comList = hcompanyService.getHCompanyBaseList(com);
					if(comList!=null&&comList.size()>0){
						for(HCompany company : comList){
							//获取业务员
							ManageAdminUser tmpServicer = hcompanyService.getDefaultServicer(contextPath);
							Integer servicerId = null;
							String servicerName = null;
							if(tmpServicer!=null){
								servicerId = tmpServicer.getAdminId();
								servicerName = tmpServicer.getRealName();
							}
							//更新company一级代理
							company.setOneAgentOpenId(uuid);
							company.setOneAgentName(ywy.getRealName());
							company.setServicerId(servicerId);
							company.setServicerName(servicerName);
							hcompanyService.updateHCompany(company);
							hcompanyService.updateDefaultService(servicerId);
							//更新company对应用户agentId
							ManageAdminUser comUser = new ManageAdminUser();
							comUser.setAdminId(company.getUser_id());
							comUser = manageadminuserService.getManageAdminUser(comUser);
							comUser.setOneAgentOpenId(uuid);
							comUser.setOneAgentName(ywy.getRealName());
							comUser.setServicerId(servicerId);
							comUser.setServicerName(servicerName);
							manageadminuserService.updateManageAdminUser(comUser);
							//订单处理
							
							//获取订单
							HPayOrder orderParam = new HPayOrder();
							orderParam.setC_id(company.getId());
//							orderParam.setPay_status("1");
							List<HPayOrder> orderList = hpayorderService.getHPayOrderBaseList(orderParam);
							if(orderList!=null&&orderList.size()>0){
								for(HPayOrder order : orderList){
									BigDecimal onerate = new BigDecimal("0");
									BigDecimal tworate = new BigDecimal("0");
									//更新订单代理信息
									order.setOneAgentName(ywy.getRealName());
									order.setOneAgentOpenId(uuid);
									order.setC_name(company.getName());
									order.setPay_type("1");
									order.setServicerId(servicerId);
									order.setServicerName(servicerName);
									hpayorderService.updateHPayOrder(order);
									//订单明细
//									HSubOrder subParam = new HSubOrder();
//									subParam.setO_id(order.getO_no());
//									subParam.setC_id(company.getId());
//									List<HSubOrder> subList = hsuborderService.getHSubOrderBaseList(subParam);
//									if(subList!=null&&subList.size()>0){
//										for(HSubOrder subOrder : subList){
//											//付款金额
//											Integer subFee = subOrder.getAmount();//分
//											//电表号
//											String eleNum = subOrder.getElectric();
//											//获取分润比例
//											//获取电表信息
//											HAmmeterInfo ammeter = new HAmmeterInfo();
//											ammeter.setAmmeter_no(eleNum);
//											ammeter.setDelete_state(1);
//											ammeter.setC_id(subOrder.getC_id());
//											ammeter.setS_id(subOrder.getSub_id());
//											ammeter = hAmmeterInfoService.getHAmmeterInfo(ammeter);
//											if(ammeter!=null&&ammeter.getProfit_id()!=null){
//												HProfitRatio ratio = new HProfitRatio();
//												ratio.setId(ammeter.getProfit_id());
//												rate = hprofitratioService.getHProfitRatio(ratio);
//											}
//											//订单明细分润
//											BigDecimal subFeeDec = new BigDecimal(subFee);//分
//											BigDecimal oneRate = new BigDecimal(rate.getOnt_agent_ratio());
//											Long allRateFee = subFeeDec.multiply(oneRate).longValue();//分
//											subOrder.setOneFee(allRateFee);
//											subOrder.setOneRate(oneRate);
//											onerate.add(subFeeDec.multiply(oneRate));
//											hsuborderService.updateHSubOrder(subOrder);
//											//分润金额
////											BigDecimal taxFee = taxRate.multiply(subFeeDec.multiply(oneRate)).divide(new BigDecimal("100"));//税费 元
////											BigDecimal afterTaxFee = (subFeeDec.multiply(oneRate).divide(new BigDecimal("100")).subtract(taxFee));//税后分润   元
//											BigDecimal afterTaxFee = (subFeeDec.multiply(oneRate).divide(new BigDecimal("100")));//税前分润   元
//											//账户明细分润
//											HUserAccountDetail accDetail = new HUserAccountDetail();
//											accDetail.setUserAccountId(accId);
//											accDetail.setType(1);
//											accDetail.setTotalFee(afterTaxFee);
//											accDetail.setCreateTime(new Date());
//											accDetail.setOperaterId(-5);
//											accDetail.setRate(oneRate.floatValue());
//											accDetail.setOrderId(order.getO_id());
//											accDetail.setAmmeterNum(eleNum);
//											accDetail.setOrderDetailId(subOrder.getO_sub_id());
//											accDetail.setOrderDetailMoney(subFeeDec);
//											accDetail.setTaxRate(taxRate);
////											accDetail.setTaxMoney(taxFee);
//											huseraccountdetailService.insertHUserAccountDetail(accDetail);
//											//账户分润
//											HUserAccount acTmp = new HUserAccount();
//											acTmp.setId(accId);
//											account = huseraccountService.getHUserAccount(acTmp);
//											if(account.getTotalFee()!=null){
//												account.setTotalFee(account.getTotalFee().add(afterTaxFee));
//												huseraccountService.updateHUserAccount(account);
//											}
//										}
//									}
									order.setOneRate(onerate);
								}
							}
						}
					}
				}catch(Exception e){
					logger.error("ywy:"+ywy.getAdminName()+",详细异常："+e.getMessage(),e);
					errList.add("ywy:"+ywy.getAdminName()+",详细异常："+getExceptionAllinformation(e));
					e.printStackTrace();
					continue;
				}
			}
			writeSuccessMsg("完成", errList, response);
		}catch(Exception e){
			writeSuccessMsg("error", errList, response);
			e.printStackTrace();
		}
		return null;
	}
	public static String getExceptionAllinformation(Exception ex){
        String sOut = "";
        StackTraceElement[] trace = ex.getStackTrace();
        for (StackTraceElement s : trace) {
            sOut += "\tat " + s + "\r\n";
        }
        return sOut;
 }
	/*****************页面中转*********************/
	@RequestMapping(value = "/reset", method = RequestMethod.GET)
	public String reset(HttpServletRequest request, HttpServletResponse response, Model model,Integer p)
	{
		processSwitch = true;
		writeSuccessMsg("完成", null, response);
		return null;
	}
}
