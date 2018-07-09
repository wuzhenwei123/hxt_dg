package com.hxt.wap.controller;

import com.alibaba.fastjson.JSONObject;
import com.base.controller.BaseController;
import com.base.utils.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hxt.hAmmeterInfo.model.HAmmeterInfo;
import com.hxt.hAmmeterInfo.service.HAmmeterInfoService;
import com.hxt.hArea.model.HArea;
import com.hxt.hArea.service.HAreaService;
import com.hxt.hCommon.service.HCommonService;
import com.hxt.hCompany.model.HCompany;
import com.hxt.hCompany.service.HCompanyService;
import com.hxt.hProfitRatio.model.HProfitRatio;
import com.hxt.hProfitRatio.service.HProfitRatioService;
import com.hxt.hSubCompany.model.HSubCompany;
import com.hxt.hSubCompany.service.HSubCompanyService;
import com.hxt.hSubOrder.service.HSubOrderService;
import com.hxt.hUserAccount.service.HUserAccountService;
import com.sys.manageAdminUser.model.ManageAdminUser;
import com.sys.manageAdminUser.service.ManageAdminUserService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;

/**
 * @author	coolme
 * @time	2015年11月18日 01:15:06
 */
@Controller
@RequestMapping("/ammetermanager")
public class AmmeterManagerController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HSubOrderController.class); //Logger
	
	@Autowired
	private HSubCompanyService hSubCompanyService;//电表发票
	@Autowired
	private HAmmeterInfoService hAmmeterInfoService;//电表
	@Autowired
	private HSubOrderService hsuborderService;
	@Autowired
	private HCommonService hCommonService;
	@Autowired
	private HUserAccountService huseraccountService = null;
	
	@Autowired
	private HProfitRatioService hprofitratioService = null;
	@Autowired
	private HAreaService hareaService = null;
	@Autowired
	private ManageAdminUserService manageadminuserService = null;// 用户
	@Autowired
	private HCompanyService hcompanyService = null;

	public static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();    // gson解析对象。


	/**
	 * 获取发票信息
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getHSubCompanyBySId", method = RequestMethod.GET)
	public String getHSubCompanyBySId(HttpServletRequest request, HttpServletResponse response, Model model)
	{

		Map<String, Object> resultMap = new HashMap<String, Object>();//返回数据
		List resultList = new ArrayList();
		Integer sId = RequestHandler.getInteger(request, "sId");
		HSubCompany hSubCompany = new HSubCompany();
		hSubCompany.setS_id(sId);
		List<HSubCompany> hSubCompanyList = new ArrayList<HSubCompany>();
		try {
			hSubCompanyList = hSubCompanyService.getHSubCompanyBaseList(hSubCompany);
			resultMap.put("status", "success");
			resultMap.put("msg", "获取发票信息成功!");
			resultMap.put("data", gson.toJson(hSubCompanyList.get(0)));
		}catch (Exception e){
			resultMap.put("status", "fail");
			resultMap.put("msg", "获取发票信息失败!");
		}
		ResponseUtil.responseText(response, gson.toJson(resultMap));
		return null;
	}

	/**
	 * 获取发票电表信息
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getInvoceAmmeterInfoByCId", method = RequestMethod.GET)
	public String getInvoceAmmeterInfoByCId(HttpServletRequest request, HttpServletResponse response, Model model)
	{

		Map<String, Object> resultMap = new HashMap<String, Object>();//返回数据
		List<Map> resultList = new ArrayList();
		Integer cId = RequestHandler.getInteger(request, "cId");
		Integer proxyType = RequestHandler.getInteger(request, "proxy");
		HSubCompany hSubCompanyQry = new HSubCompany();
		hSubCompanyQry.setC_id(cId);
		List<HSubCompany> hSubCompanyList = new ArrayList<HSubCompany>();
		String IP = this.getIpAddr(request);
		int feeTotal = 0;
		try {
			// 排序
			hSubCompanyQry.setSortColumn("create_time desc");
			hSubCompanyList = hSubCompanyService.getHSubCompanyBaseList(hSubCompanyQry);
			for (HSubCompany invoce : hSubCompanyList){
				int subOwedSum = 0;//子单位缴费金额
				Map<String,Object> dataMap = new HashMap<String, Object>();//数据
				List<HAmmeterInfo> hAmmeterInfos = new ArrayList<HAmmeterInfo>();//电表信息
//				dataMap = gson.fromJson(gson.toJson(invoce),Map.class);
//				dataMap.put("invoce",invoce);
				Integer sId = invoce.getS_id();
				if(sId!=null){
					HAmmeterInfo hAmmeterInfoQry = new HAmmeterInfo();
					hAmmeterInfoQry.setS_id(sId);
					hAmmeterInfoQry.setC_id(cId);
					hAmmeterInfoQry.setDelete_state(1);
					if(proxyType!=null&&proxyType==1){
						hAmmeterInfoQry.setProxy_flag(1);	
					}
					hAmmeterInfos = hAmmeterInfoService.getHAmmeterInfoBaseList(hAmmeterInfoQry);
					for (HAmmeterInfo hAmmeterInfo:hAmmeterInfos){
						if("1".equals(hAmmeterInfo.getPay_status())){
							String ammeterNo = hAmmeterInfo.getAmmeter_no();//电表号
							JSONObject result = hCommonService.hXTServiceQuery(ammeterNo, IP);
							if("00".equals(result.get("resultCode"))){//获取电表信息成功
								Integer fee = Integer.valueOf(result.get("totalFeeStr") == null ? "0" : result.get("totalFeeStr").toString());
								System.out.println("-------fee-----------"+fee);
								feeTotal = feeTotal + fee;
								if(fee>=0){
									hAmmeterInfo.setTotalFee(fee);
									if(fee>0){
										subOwedSum=subOwedSum+fee;//子单位欠费
									}
								}
								if(StringUtils.isNotBlank(result.getJSONObject("resultInfo").getString("accountName"))){
									hAmmeterInfo.setAmmeter_name(result.getJSONObject("resultInfo").getString("accountName"));
									hAmmeterInfoService.updateHAmmeterInfo(hAmmeterInfo);
								}
							}else{//获取电表信息失败
								
							}
						}
					}
					invoce.setTotalFee(subOwedSum);
					dataMap = MapUtil.convertBean(invoce);
					dataMap.put("ammeters", hAmmeterInfos);
				}
				resultList.add(dataMap);
			}
			resultMap.put("all_total_fee", feeTotal);
			resultMap.put("status", "success");
			resultMap.put("msg", "获取发票电表信息成功!");
			resultMap.put("data", gson.toJson(resultList));
		}catch (Exception e){
			resultMap.put("status", "fail");
			resultMap.put("msg", "获取发票电表信息失败!");
			e.printStackTrace();
		}
		ResponseUtil.responseText(response, gson.toJson(resultMap));
		return null;
	}
	/**
	 * 获取发票电表缴费信息
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getInvoceAmmeterPayInfoByCId", method = RequestMethod.GET)
	public String getInvoceAmmeterPayInfoByCId(HttpServletRequest request, HttpServletResponse response, Model model)
	{

		Map<String, Object> resultMap = new HashMap<String, Object>();//返回数据
		List<Map> resultList = new ArrayList();
		Integer cId = RequestHandler.getInteger(request, "cId");
		HSubCompany hSubCompanyQry = new HSubCompany();
		hSubCompanyQry.setC_id(cId);
		List<HSubCompany> hSubCompanyList = new ArrayList<HSubCompany>();
		String IP = this.getIpAddr(request);
		int ammeterSum = 0;//总电表数
		int owedAmmeterSum = 0;//欠费电表数
		int rowsTotal=0;
		int feeTotal = 0;
		try {
			// 排序
			hSubCompanyQry.setSortColumn("create_time desc");
			hSubCompanyList = hSubCompanyService.getHSubCompanyBaseList(hSubCompanyQry);
			for (HSubCompany invoce : hSubCompanyList){
				int subOwedSum = 0;//子单位缴费金额
				Map<String,Object> dataMap = new HashMap<String, Object>();//数据
				List<HAmmeterInfo> hAmmeterInfos = new ArrayList<HAmmeterInfo>();//电表信息
				Integer sId = invoce.getS_id();
				if(sId!=null){
					HAmmeterInfo hAmmeterInfoQry = new HAmmeterInfo();
					hAmmeterInfoQry.setC_id(cId);
					hAmmeterInfoQry.setS_id(sId);
					hAmmeterInfoQry.setPay_status("1");
					hAmmeterInfos = hAmmeterInfoService.getHAmmeterInfoBaseList(hAmmeterInfoQry);
					for (HAmmeterInfo hAmmeterInfo:hAmmeterInfos){
						ammeterSum++;
						String ammeterNo = hAmmeterInfo.getAmmeter_no();//电表号

						JSONObject result = hCommonService.hXTServiceQuery(ammeterNo, IP);
						if("00".equals(result.get("resultCode"))){//获取电表信息成功
							Integer fee = Integer.valueOf(result.get("totalFeeStr") == null ? "0" : result.get("totalFeeStr").toString());
							System.out.println("-------fee-----------"+fee);
							feeTotal = feeTotal + fee;
							if(fee>=0){
								hAmmeterInfo.setTotalFee(fee);
								if(fee>0){
									subOwedSum=subOwedSum+fee;//子单位欠费
									owedAmmeterSum++;//欠费表数
								}
							}
							if(StringUtils.isNotBlank(result.getJSONObject("resultInfo").getString("accountName"))){
								hAmmeterInfo.setAmmeter_name(result.getJSONObject("resultInfo").getString("accountName"));
								hAmmeterInfoService.updateHAmmeterInfo(hAmmeterInfo);
							}
						}else{//获取电表信息失败
							
						}
					}
					invoce.setTotalFee(subOwedSum);
					System.out.println("--------subOwedSum----------"+subOwedSum);
					dataMap = MapUtil.convertBean(invoce);
					dataMap.put("ammeters", hAmmeterInfos);
					if(hAmmeterInfos.size()>0){
						rowsTotal++;
						resultList.add(dataMap);
					}
				}
			}
			if(resultList!=null&&resultList.size()>0){
				
			}
			ComparatorAmmeter comparator=new ComparatorAmmeter();
			Collections.sort(resultList, comparator);
			resultMap.put("status", "success");
			resultMap.put("msg", "获取发票电表信息成功!");
			System.out.println("--------resultMap----------"+gson.toJson(resultMap));
			resultMap.put("data", gson.toJson(resultList));
			resultMap.put("ammeterSum", ammeterSum);
			resultMap.put("owedAmmeterSum", owedAmmeterSum);
//			resultMap.put("paysum", String.format("%.2f", Double.parseDouble(paysum+"")));
			resultMap.put("paysum",feeTotal);
			resultMap.put("rowsTotal", rowsTotal);
		}catch (Exception e){
			resultMap.put("status", "fail");
			resultMap.put("msg", "获取发票电表信息失败!");
			e.printStackTrace();
		}
		ResponseUtil.responseText(response, gson.toJson(resultMap));
		return null;
	}
	/**
	 * 保存子单位信息
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/saveHSubCompany", method = RequestMethod.POST)
	public String saveHSubCompany(HttpServletRequest request, HttpServletResponse response, Model model) {
		Map dataMap = request.getParameterMap();
		HSubCompany hSubCompany = new HSubCompany();
		ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
		MapUtil.transMap2Bean2(dataMap,hSubCompany);
		Map<String, Object> resultMap = new HashMap<String, Object>();//返回数据
		try {
			int id = 0;
			if(hSubCompany.getS_id()!=null && hSubCompany.getS_id()>0){//存在更新
				hSubCompany.setUpdate_time(new Date());
				hSubCompanyService.updateHSubCompany(hSubCompany);
			}else{
				hSubCompany.setC_id(adminUser.getCompanyId());
				id= hSubCompanyService.insertHSubCompany(hSubCompany);
			}
			if(id>0){
				hSubCompany.setS_id(id);
			}
			resultMap.put("status", "success");
			resultMap.put("msg", "保存子单位信息成功!");
			resultMap.put("data", gson.toJson(hSubCompany));
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", "fail");
			resultMap.put("msg", "保存子单位信息失败!");
		}
		ResponseUtil.responseText(response, gson.toJson(resultMap));
		return null;
	}
	
	/**
	 * 保存子单位信息
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/updateHSubCompany", method = RequestMethod.POST)
	public String updateHSubCompany(HttpServletRequest request, HttpServletResponse response, Model model) {
		Map dataMap = request.getParameterMap();
		HSubCompany hSubCompany = new HSubCompany();
//		ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
		MapUtil.transMap2Bean2(dataMap,hSubCompany);
//		hSubCompany.setC_id(adminUser.getCompanyId());
		Map<String, Object> resultMap = new HashMap<String, Object>();//返回数据
		try {
//			int id = 0;
//			if(hSubCompany.getS_id()!=null && hSubCompany.getS_id()>0){//存在更新
				hSubCompany.setUpdate_time(new Date());
				hSubCompanyService.updateHSubCompany(hSubCompany);
//			}else{
//				id= hSubCompanyService.insertHSubCompany(hSubCompany);
//			}
//			if(id>0){
//				hSubCompany.setS_id(id);
//			}
			resultMap.put("status", "success");
			resultMap.put("msg", "更新子单位信息成功!");
			resultMap.put("data", gson.toJson(hSubCompany));
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", "fail");
			resultMap.put("msg", "更新子单位信息失败!");
		}
		ResponseUtil.responseText(response, gson.toJson(resultMap));
		return null;
	}

	/**
	 * 保存电表信息
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/saveHAmmeterInfo", method = RequestMethod.POST)
	public String saveHAmmeterInfo(HttpServletRequest request, HttpServletResponse response, Model model) {
		ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
		Integer proxy = RequestHandler.getInteger(request, "proxy");
		Map dataMap = request.getParameterMap();
		HAmmeterInfo hAmmeterInfo = new HAmmeterInfo();
		MapUtil.transMap2Bean2(dataMap,hAmmeterInfo);
//		HAmmeterInfo hAmmeterInfo = gson.fromJson(gson.toJson(dataMap), HAmmeterInfo.class);
		Map<String, Object> resultMap = new HashMap<String, Object>();//返回数据
		try {
			//查询电表信息是否已经存在
			HAmmeterInfo hAmmeterInfo1 = new HAmmeterInfo();
			hAmmeterInfo1.setAmmeter_no(hAmmeterInfo.getAmmeter_no());
			hAmmeterInfo1.setDelete_state(1);
			int count1 = hAmmeterInfoService.getHAmmeterInfoListCount(hAmmeterInfo1);
			hAmmeterInfo1.setC_id(hAmmeterInfo.getC_id());
			int count = hAmmeterInfoService.getHAmmeterInfoListCount(hAmmeterInfo1);//不能存在未删除相同电表号
			if(count>0){
				resultMap.put("status", "fail");
				resultMap.put("msg", "该缴费号您已绑定，无法再次绑定。");
			}else if(count1>0){
				resultMap.put("status", "fail");
				resultMap.put("msg", "该缴费号已被其他账号绑定，无法再次绑定。");
			}else{
				//获取默认的分润比例
				HProfitRatio hProfitRatio = new HProfitRatio();
				hProfitRatio.setIs_default(1);
				hProfitRatio = hprofitratioService.getHProfitRatio(hProfitRatio);
				if(hProfitRatio!=null){
					hAmmeterInfo.setOperator_id(adminUser.getAdminId());
					hAmmeterInfo.setProfit_id(hProfitRatio.getId());
					hAmmeterInfo = hareaService.setAreaCodeToAmmeterInfo(hAmmeterInfo);
					if(proxy!=null){
						hAmmeterInfo.setProxy_flag(proxy);
					}
					int id= hAmmeterInfoService.insertHAmmeterInfo(hAmmeterInfo);
					if(id>0){
						//获取对应的openId
						HCompany hCompany = new HCompany();
						hCompany.setId(hAmmeterInfo.getC_id());
						hCompany = hcompanyService.getHCompany(hCompany);
						if(hCompany!=null){
							ManageAdminUser manageAdminUser = new ManageAdminUser();
							manageAdminUser.setOneAgentOpenId(hCompany.getOneAgentOpenId());
							manageAdminUser.setRoleId(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
							ManageAdminUser user = manageadminuserService.getManageAdminUser(manageAdminUser);
							String toOPENID = null;
							if(user!=null){
								toOPENID = user.getOpenId();
							}
							huseraccountService.sendAOTempltMsg(toOPENID, "bind", hCompany.getContact_name(), hCompany.getContact_phone(), hAmmeterInfo.getAmmeter_no(), hAmmeterInfo.getC_id(), hCompany.getName());
						}
					}
					hAmmeterInfo.setA_id(id);
					resultMap.put("status", "success");
					resultMap.put("msg", "保存电表信息成功!");
					resultMap.put("data", gson.toJson(hAmmeterInfo));
				}else{
					resultMap.put("status", "fail");
					resultMap.put("msg", "请联系系统管理员，设置默认的分润比例。");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", "fail");
			resultMap.put("msg", "保存电表信息失败!");
		}
		ResponseUtil.responseText(response, gson.toJson(resultMap));
		return null;
	}

	/**
	 * 更新电表信息
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/updateHAmmeterInfoStatus", method = RequestMethod.POST)
	public String updateHAmmeterInfoStatus(HttpServletRequest request, HttpServletResponse response, Model model) {
		Map<String, Object> resultMap = new HashMap<String, Object>();//返回数据
		Map dataMap = request.getParameterMap();
		HAmmeterInfo hAmmeterInfo = new HAmmeterInfo();
		MapUtil.transMap2Bean2(dataMap,hAmmeterInfo);
		String status = hAmmeterInfo.getPay_status();
		try {
			hAmmeterInfo.setPay_status(null);
			hAmmeterInfo = hAmmeterInfoService.getHAmmeterInfo(hAmmeterInfo);
			hAmmeterInfo.setPay_status(status);
			hAmmeterInfo.setUpdate_time(new Date());
			int id = hAmmeterInfoService.updateHAmmeterInfo(hAmmeterInfo);
			if(id>0&&"0".equals(status)){
				//获取对应的openId
				HCompany hCompany = new HCompany();
				hCompany.setId(hAmmeterInfo.getC_id());
				hCompany = hcompanyService.getHCompany(hCompany);
				if(hCompany!=null){
					ManageAdminUser manageAdminUser = new ManageAdminUser();
					manageAdminUser.setOneAgentOpenId(hCompany.getOneAgentOpenId());
					manageAdminUser.setRoleId(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
					ManageAdminUser user = manageadminuserService.getManageAdminUser(manageAdminUser);
					String toOPENID = null;
					if(user!=null){
						toOPENID = user.getOpenId();
					}
					huseraccountService.sendAOTempltMsg(toOPENID, "unbind", hCompany.getContact_name(), hCompany.getContact_phone(), hAmmeterInfo.getAmmeter_no(), hAmmeterInfo.getC_id(), hCompany.getName());
				}
			}
			resultMap.put("status", "success");
			resultMap.put("msg", "电表信息更新成功!");
			resultMap.put("data", gson.toJson(hAmmeterInfo));
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", "fail");
			resultMap.put("msg", "电表信息更新失败!");
		}
		ResponseUtil.responseText(response, gson.toJson(resultMap));
		return null;
	}
	/**
	 * 更新电表信息for代扣
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/updateProxy", method = RequestMethod.POST)
	public String updateProxy(HttpServletRequest request, HttpServletResponse response, Model model) {
		try{
			ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
			if(adminUser!=null){
				Integer id = RequestHandler.getInteger(request, "id");
				Integer state = RequestHandler.getInteger(request, "proxy_flag");
				if(id!=null&&state!=null){
					HAmmeterInfo ammeter = new HAmmeterInfo();
					ammeter.setA_id(id);
					ammeter.setC_id(adminUser.getCompanyId());
					ammeter = hAmmeterInfoService.getHAmmeterInfo(ammeter);
					if(ammeter!=null){
						if("1".equals(ammeter.getPay_status())){
							ammeter.setProxy_flag(state);
							hAmmeterInfoService.updateHAmmeterInfo(ammeter);
							writeSuccessMsg("设置成功！", null, response);
						}else{
							writeErrorMsg("电表为暂停缴费状态，不可设置代扣！", null, response);
						}
					}else{
						writeErrorMsg("电表信息错误！", null, response);
					}
				}else{
					writeErrorMsg("参数错误！", null, response);
				}
			}else{
				writeErrorMsg("请先登录！", null, response);
			}
		}catch(Exception e){
			e.printStackTrace();
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

	@RequestMapping(value = "/toZhifu", method = RequestMethod.GET)
	public String toZhifu(HttpServletRequest request, HttpServletResponse response, Model model){
		Integer cId = RequestHandler.getInteger(request, "cId");
		request.setAttribute("cId",cId+"");
//		return "/front/zhiFu";
		return "/public/electricPay";
	}

	/**
	 * 逻辑删除电表号
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/delAmmeter", method = RequestMethod.POST)
	public String delAmmeter(HttpServletRequest request, HttpServletResponse response, Model model) {
		Map<String, Object> resultMap = new HashMap<String, Object>();//返回数据
		ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
		String ammNo = RequestHandler.getString(request, "ammeter_no");
		Integer s_id = RequestHandler.getInteger(request, "s_id");
		try {
			if(StringUtils.isNotBlank(ammNo)&&adminUser.getCompanyId()!=null){
				HAmmeterInfo hAmmeterInfo = new HAmmeterInfo();
				hAmmeterInfo.setAmmeter_no(ammNo);
				hAmmeterInfo.setS_id(s_id);
				hAmmeterInfo.setDelete_state(1);
				hAmmeterInfo = hAmmeterInfoService.getHAmmeterInfo(hAmmeterInfo);
				if(hAmmeterInfo!=null){
					if(adminUser.getCompanyId()!=null&&(adminUser.getCompanyId().equals(hAmmeterInfo.getC_id())||hAmmeterInfo.getC_id()==adminUser.getCompanyId())){
						hAmmeterInfo.setDelete_state(0);
						int id = hAmmeterInfoService.updateHAmmeterInfo(hAmmeterInfo);
						if(id>0){
							//获取对应的openId
							HCompany hCompany = new HCompany();
							hCompany.setId(hAmmeterInfo.getC_id());
							hCompany = hcompanyService.getHCompany(hCompany);
							if(hCompany!=null){
								ManageAdminUser manageAdminUser = new ManageAdminUser();
								manageAdminUser.setOneAgentOpenId(hCompany.getOneAgentOpenId());
								manageAdminUser.setRoleId(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
								ManageAdminUser user = manageadminuserService.getManageAdminUser(manageAdminUser);
								String toOPENID = null;
								if(user!=null){
									toOPENID = user.getOpenId();
								}
								huseraccountService.sendAOTempltMsg(toOPENID, "delete", hCompany.getContact_name(), hCompany.getContact_phone(), hAmmeterInfo.getAmmeter_no(), hAmmeterInfo.getC_id(), hCompany.getName());
							}
						}
						resultMap.put("status", "success");
						resultMap.put("msg", "删除成功!");
						resultMap.put("data", gson.toJson(hAmmeterInfo));
					}else{
						resultMap.put("status", "error");
						resultMap.put("msg", "该电表号不属于你的单位，无法删除!");
					}
				}else{
					resultMap.put("status", "error");
					resultMap.put("msg", "电表号不正确!");
				}
			}else{
				resultMap.put("status", "error");
				resultMap.put("msg", "参数错误!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", "fail");
			resultMap.put("msg", "删除失败!");
		}
		ResponseUtil.responseText(response, gson.toJson(resultMap));
		return null;
	}
	
	/**
	 * 删除分组
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/delSubcompany", method = RequestMethod.POST)
	public String delSubcompany(HttpServletRequest request, HttpServletResponse response, Model model) {
		Map<String, Object> resultMap = new HashMap<String, Object>();//返回数据
		ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
		String sid = RequestHandler.getString(request, "sid");
		try {
			if(StringUtils.isNotBlank(sid)){
				HSubCompany sub = new HSubCompany();
				sub.setS_id(Integer.valueOf(sid));
				sub = hSubCompanyService.getHSubCompany(sub);
				if(sub!=null&&sub.getC_id()!=null&&sub.getC_id().equals(adminUser.getCompanyId())){
					HAmmeterInfo ame = new HAmmeterInfo();
					ame.setS_id(Integer.valueOf(sid));
					List<HAmmeterInfo> list = hAmmeterInfoService.getHAmmeterInfoBaseList(ame);
					if(list!=null&&list.size()>0){
						for(int i =0;i<list.size();i++){
							HAmmeterInfo tmp = list.get(i);
							tmp.setDelete_state(0);
							hAmmeterInfoService.updateHAmmeterInfo(tmp);
						}
					}
					hSubCompanyService.removeHSubCompany(sub);
					resultMap.put("status", "success");
					resultMap.put("msg", "删除成功!");
				}else{
					resultMap.put("status", "error");
					resultMap.put("msg", "该分组无法删除!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", "fail");
			resultMap.put("msg", "删除失败!");
		}
		ResponseUtil.responseText(response, gson.toJson(resultMap));
		return null;
	}
}
