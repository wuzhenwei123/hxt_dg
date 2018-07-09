package com.hxt.wap.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.management.IntrospectionException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.base.controller.BaseController;
import com.base.utils.Base64;
import com.base.utils.Common;
import com.base.utils.DateFormatToString;
import com.base.utils.DemoBase;
import com.base.utils.FileUploadConstants;
import com.base.utils.MapUtil;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseUtil;
import com.base.utils.SendMsgUtil;
import com.base.utils.SessionName;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hxt.hAgent.model.HAgent;
import com.hxt.hAgent.service.HAgentService;
import com.hxt.hAgentTwo.model.HAgentTwo;
import com.hxt.hAgentTwo.service.HAgentTwoService;
import com.hxt.hAmmeterInfo.model.HAmmeterInfo;
import com.hxt.hAmmeterInfo.service.HAmmeterInfoService;
import com.hxt.hCommon.service.HCommonService;
import com.hxt.hCompany.model.HCompany;
import com.hxt.hCompany.service.HCompanyService;
import com.hxt.hLbImg.service.HLbImgService;
import com.hxt.hMessageLog.model.HMessageLog;
import com.hxt.hMessageLog.service.HMessageLogService;
import com.hxt.hPayOrder.controller.HPayOrderController;
import com.hxt.hPayOrder.model.HPayOrder;
import com.hxt.hPayOrder.service.HPayOrderService;
import com.hxt.hPayurlCheck.model.HPayurlCheck;
import com.hxt.hPayurlCheck.service.HPayurlCheckService;
import com.hxt.hReviewUser.model.HReviewUser;
import com.hxt.hReviewUser.service.HReviewUserService;
import com.hxt.hSubCompany.model.HSubCompany;
import com.hxt.hSubCompany.service.HSubCompanyService;
import com.hxt.hSubOrder.model.HSubOrder;
import com.hxt.hSubOrder.service.HSubOrderService;
import com.hxt.hUserAccount.service.HUserAccountService;
import com.hxt.wap.service.CallProcedureService;
import com.sys.manageAdminUser.model.ManageAdminUser;
import com.sys.manageAdminUser.service.ManageAdminUserService;

@Controller
@RequestMapping("/pay")
public class PayController extends BaseController{
	
	Logger log = Logger.getLogger(HPayOrderController.class);
	
	private static Map<String,Integer> mapMsg = new HashMap<String,Integer>();
	public static Map<String,Date> templeteMap = new HashMap<String,Date>();
	

	@Autowired
	private HLbImgService hlbimgService = null;
	@Autowired
	private HSubCompanyService hSubCompanyService;
	@Autowired
	private HAmmeterInfoService hAmmeterInfoService;
	@Autowired
	private HCommonService hCommonService;
	@Autowired
	private HPayOrderService hPayOrderService;
	@Autowired
	private HSubOrderService hSubOrderService;
	@Autowired
	private CallProcedureService callProcedureService;
	@Autowired
	private HPayurlCheckService hpayurlcheckService;
	@Autowired
	private HCompanyService hcompanyService = null;
	@Autowired
	private HReviewUserService hreviewuserService = null;
	@Autowired
	private HMessageLogService hmessagelogService = null;
	@Autowired
	private HUserAccountService huseraccountService = null;
	@Autowired
	private ManageAdminUserService manageadminuserService = null;// 用户
	@Autowired
	private HAgentTwoService hagenttwoService = null;
	@Autowired
	private HAgentService hagentService = null;

	public static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();    // gson解析对象。
	
	

	/**
	 * 进入缴费页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toElectricPay", method = RequestMethod.GET)
	public String toElectricPay(HttpServletRequest request, HttpServletResponse response, Model model){
		model.addAttribute("nav", "dianfei");
		return "/public/payUrlCheck";
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

	/**
	 * 生成支付订单号
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/generateONo", method = RequestMethod.GET)
	public String generateONo(HttpServletRequest request, HttpServletResponse response, Model model){

		Map<String, Object> resultMap = new HashMap<String, Object>();//返回数据
		Map map = new HashMap();
		map.put("snNamePre", "P");//编号前缀
		map.put("snName", "PaySN");//编号名称，记录用
		map.put("num", "10");//编号长度
		map.put("sn", "@sn");//编号

		try {
			String oNo = callProcedureService.callGenerateSnNo(map);//订单编号
			resultMap.put("status", "success");
			resultMap.put("msg", "获取订单号成功!");
			resultMap.put("oNo",oNo);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", "fail");
			resultMap.put("msg", "获取订单号失败!");
		}
		ResponseUtil.responseText(response, gson.toJson(resultMap));
		return null;

	}

	/**
	 * 生成缴费链接
	 * @return url
	 */
	@RequestMapping(value = "/getPayurl", method = RequestMethod.POST)
	public String getPayurl(HttpServletRequest request, HttpServletResponse response, Model model){
		Map<String, Object> resultMap = new HashMap<String, Object>();//返回数据
        StringBuffer path = request.getRequestURL();
        String urlPath = FileUploadConstants.URL_PATH;
//        urlPath = path.toString().replace(urlPath,"");
		try{
            SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
//            Date now=new Date();
            HPayurlCheck hpayurlcheckQry = new HPayurlCheck();
			Integer c_id = RequestHandler.getInteger(request, "companyId");
			String companyName = RequestHandler.getString(request, "companyName");
            hpayurlcheckQry.setC_id(c_id);
//            hpayurlcheckQry.setCreate_date(new Date());
            HPayurlCheck hpayurlcheck = hpayurlcheckService.findNowHPayurlCheck(hpayurlcheckQry);
			if(hpayurlcheck!= null && hpayurlcheck.getId()!=null && !"".equals(hpayurlcheck.getId()+"")){//一天只能生成一个支付url
                resultMap.put("status", "success");
                resultMap.put("msg", "获取缴费链接成功!");
                resultMap.put("data",gson.toJson(hpayurlcheck));
			}else{
                String checkNo = Common.getRandomString(6);//随机生成
                hpayurlcheckQry.setCheck_no(checkNo);
                Map<String,String> params = new HashMap<String,String>();
                String status = RequestHandler.getString(request, "status");
                hpayurlcheckQry.setStatus("1");//有效
                hpayurlcheckQry.setPay_url("");
                hpayurlcheckQry.setCheck_url("");
                int result = hpayurlcheckService.insertHPayurlCheck(hpayurlcheckQry);
                params.put("c_id", c_id + "");
//			params.put("check_no", checkNo);
                params.put("id", result + "");
                params.put("status", "1");
                String param = Base64.encodeBytes(gson.toJson(params));
                param = URLEncoder.encode(param);
                params.remove("id");
                params.remove("status");
                params.put("companyName",companyName);
                String paramc = Base64.encodeBytes(gson.toJson(params));
                paramc = URLEncoder.encode(paramc);
                String check_url = urlPath+"/pay/toElectricPay?param="+param;
                String pay_url = urlPath+"/ammetermanager/toZhifu?param="+paramc;
                hpayurlcheckQry.setPay_url(pay_url);
                hpayurlcheckQry.setCheck_url(check_url);
                hpayurlcheckQry.setId(result);
                hpayurlcheckService.updateHPayurlCheck(hpayurlcheckQry);
                resultMap.put("status", "success");
                resultMap.put("msg", "生成缴费链接成功!");
                resultMap.put("data",gson.toJson(hpayurlcheckQry));
            }


		}catch(Exception e){
			e.printStackTrace();
			resultMap.put("status", "fail");
			resultMap.put("msg", "生成缴费链接失败!");
		}
        ResponseUtil.responseText(response, gson.toJson(resultMap));

        return null;
	}

	/**
	 * 校验缴费链接
	 * @return url
	 */
	@RequestMapping(value = "/checkPayurl", method = RequestMethod.GET)
	public String checkPayurl(HttpServletRequest request, HttpServletResponse response, Model model){
		Map<String, Object> resultMap = new HashMap<String, Object>();//返回数据
        String result = "验证码校验失败！";
        String status = "fail";
        try{
            String param = RequestHandler.getString(request, "param");
            String checkNo = RequestHandler.getString(request, "checkNo");
            param = Base64.getFromBase64(param);
            HPayurlCheck hpayurlcheckQry = gson.fromJson(param,HPayurlCheck.class);
            hpayurlcheckQry.setCheck_no(checkNo);
            HPayurlCheck hpayurlcheck = hpayurlcheckService.getHPayurlCheck(hpayurlcheckQry);
            Date urlDate = hpayurlcheck.getCreate_time();//支付连接创建时间
            Date nowDate = new Date();//现在时间
            int days = DateFormatToString.daysBetween(nowDate,urlDate);
            if(days==0 && nowDate.getHours()<21){//有效期内 当天时间21点前
                if (hpayurlcheck != null){
                    result = "验证码校验成功！";
                    status = "success";
                    resultMap.put("data", hpayurlcheck.getPay_url()+"&cId="+hpayurlcheck.getC_id());
                }else{
                    result = "验证码错误，请重新输入！";
                }
            }else {
                result = "验证码有效期已过，请重新生成支付连接！";
            }
            resultMap.put("status", status);
            resultMap.put("msg", result);
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put("status", status);
			resultMap.put("msg", result);
		}
        ResponseUtil.responseText(response, gson.toJson(resultMap));
        return null;
	}

    /**
     * 生成支付订单，跳转支付页面
     * @param request
     * @param response
     * @param model
     * @return
     */
    @SuppressWarnings({ "unused", "unchecked" })
	@RequestMapping(value = "/generatePayOrder", method = RequestMethod.GET)
    public String generatePayOrder(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception
    {

//    	ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
        Map<String, Object> resultMap = new HashMap<String, Object>();//返回数据
        List<Map> resultList = new ArrayList();
//        Integer cId = adminUser.getCompanyId();
        Integer cId =  RequestHandler.getInteger(request, "cId");
        String isDirect = RequestHandler.getString(request, "isDirect");
//        ManageAdminUser adminUser = (ManageAdminUser)request.getSession().getAttribute(SessionName.ADMIN_USER);
        String payType = RequestHandler.getString(request, "payType");
        Integer subId = RequestHandler.getInteger(request, "subId");
        Integer payType1 = RequestHandler.getInteger(request, "payType1");
        Integer payType2 = RequestHandler.getInteger(request, "payType2");
        Integer reviewRadio = RequestHandler.getInteger(request, "reviewRadio");
        HReviewUser rev = new HReviewUser();
		rev.setCompanyId(cId);
		rev.setState(1);
		rev.setIsDefault(1);
		rev = hreviewuserService.getHReviewUser(rev);
		String phone = "";
		if(rev!=null){
			phone = rev.getMobil();
		}
        if(cId==null){
            resultMap.put("status", "fail");
            resultMap.put("msg", "获取公司信息失败!");
        }else {
        	//公司缴费
        	if(StringUtils.isNotBlank(payType)&&payType.equals("1")){
    		  HPayOrder hPayOrderQur = new HPayOrder();
              hPayOrderQur.setC_id(cId);
              hPayOrderQur.setPay_status("0");//未支付
              hPayOrderQur.setSub_id(-1);
              HPayOrder findHPayOrder = hPayOrderService.findTodayHPayOrder(hPayOrderQur);

              String IP = this.getIpAddr(request);

              HSubCompany hSubCompanyQry = new HSubCompany();
              hSubCompanyQry.setC_id(cId);
              List<HSubCompany> hSubCompanyList = new ArrayList<HSubCompany>();//字典表
              hSubCompanyQry.setSortColumn("create_time desc");
              hSubCompanyList = hSubCompanyService.getHSubCompanyBaseList(hSubCompanyQry);
              //校验订单支付金额是否与欠费金额相等
              boolean flag = true;
              if(findHPayOrder!=null){
              	Integer amount = findHPayOrder.getAmount();//订单金额
              	log.info("--------验证订单amount----------->"+amount);
              	flag = checkPayOrderSum(hSubCompanyList,IP,amount);
              }
              if(findHPayOrder!=null && flag){//当天存在支付订单，不重复下单 直接跳转支付页面
            	//发送末班消息
            	  HCompany hCompany1 = new HCompany();
                  hCompany1.setId(cId);
                  HCompany hCompany = hcompanyService.getHCompany(hCompany1);
				  ManageAdminUser manageAdminUser = new ManageAdminUser();
				  manageAdminUser.setOneAgentOpenId(hCompany.getOneAgentOpenId());
				  manageAdminUser.setRoleId(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
				  ManageAdminUser user = manageadminuserService.getManageAdminUser(manageAdminUser);
				  HAgent agent = new HAgent();
				  agent.setStatus(1);
				  agent.setOpenId(hCompany.getOneAgentOpenId());
				  agent = hagentService.getHAgent(agent);
				  //获取对应的openId
				  String toOPENID = null;
				  if(user!=null&&user.getState()==1&&agent!=null&&agent.getStatus()==1&&agent.getCheck_status()==1){
				     toOPENID = user.getOpenId();
				     Date d = templeteMap.get(findHPayOrder.getO_no()+"_1");
				     if(d==null){
				    	 templeteMap.put(findHPayOrder.getO_no()+"_1", new Date());
				    	 huseraccountService.sendJFTempltMsg(toOPENID, super.getMoney(findHPayOrder.getAmount()), findHPayOrder.getO_no(), hCompany.getContact_name(), hCompany.getContact_phone(), hCompany.getId(), hCompany.getName());
				     }
				  }
//            	  ManageAdminUser manageAdminUser2 = new ManageAdminUser();
//            	  manageAdminUser2.setTwoAgentOpenId(hCompany.getTwoAgentOpenID());
//            	  manageAdminUser2.setRoleId(Integer.valueOf(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID));
//				  ManageAdminUser user2 = manageadminuserService.getManageAdminUser(manageAdminUser2);
//				  HAgentTwo hAgentTwo = new HAgentTwo();
//              	  hAgentTwo.setOpenId(hCompany.getTwoAgentOpenID());
//              	  hAgentTwo = hagenttwoService.getHAgentTwo(hAgentTwo);
//				  //获取对应的openId
//				  String toOPENID2 = null;
//				  if(user2!=null&&user2.getState()==1&&hAgentTwo!=null&&hAgentTwo.getStatus()==1&&hAgentTwo.getCheck_status()==1){
//					  toOPENID2 = user2.getOpenId();
//					  huseraccountService.sendJFTempltMsg(toOPENID2, super.getMoney(findHPayOrder.getAmount()), findHPayOrder.getO_no(), hCompany.getContact_name(), hCompany.getContact_phone(), hCompany.getId(), hCompany.getName());
//				  }
				  //获取服务人员
                  if(hCompany!=null&&hCompany.getServicerId()!=null){
                	  ManageAdminUser manageAdminUser3 = new ManageAdminUser();
    				  manageAdminUser3.setAdminId(hCompany.getServicerId());
    				  ManageAdminUser user3 = manageadminuserService.getManageAdminUser(manageAdminUser3);
    				  if(user3!=null&&user3.getState()==1){
    					  Date d = templeteMap.get(findHPayOrder.getO_no()+"_3");
					     if(d==null){
					    	 templeteMap.put(findHPayOrder.getO_no()+"_3", new Date());
					    	 huseraccountService.sendJFTempltMsg(user3.getOpenId(), super.getMoney(findHPayOrder.getAmount()), findHPayOrder.getO_no(), hCompany.getContact_name(), hCompany.getContact_phone(), hCompany.getId(), hCompany.getName());
					     }
    				  }
                  }
            	  
                  //跳转支付页面
                  Map<String,String> payMap = new HashMap<String,String>();
                  payMap.put("version", "1.0.0");
                  payMap.put("encoding", "UTF-8");
                  payMap.put("signMethod", "01");
                  payMap.put("bizType", "000202");
                  payMap.put("frontUrl", FileUploadConstants.FRONT_URL);
                  payMap.put("backUrl", FileUploadConstants.BACK_URL);
                  payMap.put("merId", "000110060120006");
                  payMap.put("merOrderId", findHPayOrder.getO_no());
                  payMap.put("merTxnTime",getNowTime());//获取时间
  				log.info("-----------money1-------------"+findHPayOrder.getAmount());

                  payMap.put("merTxnAmt", findHPayOrder.getAmount()+"");
//                          payMap.put("merTxnAmt", "1");
                  payMap.put("currencyCode", "156");
                  if(payType2!=null&&payType2==2){
                	  payMap.put("payBankCode", "BJRCB");//民生E支付
                  }
                  Map<String, String> hiddens = DemoBase.signData(payMap);
                  String html = DemoBase.createHtml(FileUploadConstants.GATE_WAY, hiddens);
                  response.setContentType("text/html;charset=UTF-8");
                  PrintWriter pw = null;
                  try {
                      pw = response.getWriter();
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
                  pw.print(html);
                  pw.flush();
                  pw.close();
                  if(reviewRadio!=null&&reviewRadio == 1&&StringUtils.isNotBlank(phone)){
                	  //根据订单号查询次数
                	  Integer sendCount = mapMsg.get(findHPayOrder.getO_no());
                	  if(sendCount==null||sendCount==0){
                		  mapMsg.put(findHPayOrder.getO_no(), 1);
                		  sendReviewMsg(phone,findHPayOrder.getAmount(),cId,IP);
                	  }
                  }
              }else{//重新生成支付订单
                  HCompany hCompany1 = new HCompany();
                  hCompany1.setId(cId);
                  HCompany hCompany = hcompanyService.getHCompany(hCompany1);


                  
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
                              hAmmeterInfoQry.setDelete_state(1);
                              hAmmeterInfos = hAmmeterInfoService.getHAmmeterInfoBaseList(hAmmeterInfoQry);
                              for (HAmmeterInfo hAmmeterInfo : hAmmeterInfos) {
                                  ammeterSum++;
                                  String ammeterNo = hAmmeterInfo.getAmmeter_no();//电表号
                                  if(hAmmeterInfo.getFp_style()==null){
                                  	is_zz = true;
                                  }
                                  JSONObject result = hCommonService.hXTServiceQuery(ammeterNo, IP);
                                  if ("00".equals(result.get("resultCode"))) {//获取电表信息成功
                                  	Integer fee = Integer.valueOf(result.get("totalFeeStr") == null ? "0" : result.get("totalFeeStr").toString());
                                  	feeTotal = feeTotal + fee;
//                                          paysum = paysum + owed;//欠费总额累加
                                      if (fee >= 0) {
                                          hAmmeterInfo.setTotalFee(fee);
                                          if (fee > 0) {
                                              owedAmmeterSum++;//欠费表数
                                              sb.append(ammeterNo + ",");
                                              //插入子订单信息
                                              HSubOrder hSubOrder = new HSubOrder();
                                              hSubOrder.setC_id(cId);
                                              hSubOrder.setSub_id(sId);
//                                                  hSubOrder.setO_id(oNo);
                                              hSubOrder.setElectric(ammeterNo);//电表编号
                                              hSubOrder.setAmount(fee);//欠费金额
                                              //获取分组信息
                                              HSubCompany subcom = new HSubCompany();
                                              subcom.setS_id(sId);
                                              subcom = hSubCompanyService.getHSubCompany(subcom);
                                              if(subcom!=null){
                                            	  hSubOrder.setSub_name(subcom.getSub_name());
                                            	  hSubOrder.setConsignee(subcom.getConsignee());
                                            	  String adress = "";
                                            	  if(StringUtils.isNotBlank(subcom.getProvince_name())){
                                            		  adress = adress + subcom.getProvince_name();
                                            	  }
                                            	  
                                            	  if(StringUtils.isNotBlank(subcom.getCity_name())){
                                            		  adress = adress + subcom.getCity_name();
                                            	  }
                                            	  
                                            	  if(StringUtils.isNotBlank(subcom.getArea_name())){
                                            		  adress = adress + subcom.getArea_name();
                                            	  }
                                            	  
                                            	  if(StringUtils.isNotBlank(subcom.getConsignee_address())){
                                            		  adress = adress + subcom.getConsignee_address();
                                            	  }
                                            	  hSubOrder.setConsignee_address(adress);
                                            	  hSubOrder.setConsignee_phone(subcom.getConsignee_phone());
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
                          //启动多线程查单
//                        		ThreadUseExtends thread1=new ThreadUseExtends(IP,is_zz,feeTotal,cId,hCompany.getYwyId(),subOrderList,sb);
//                        		thread1.start();
                    	  String oNo = createOrder(IP,is_zz,feeTotal,cId,hCompany.getYwyId(),subOrderList,sb,null);//解决订单重复问题
                    	  //发送末班消息
	                      if(hCompany!=null&&hCompany.getOneAgentOpenId()!=null){
	                    	  ManageAdminUser manageAdminUser = new ManageAdminUser();
		  					  manageAdminUser.setOneAgentOpenId(hCompany.getOneAgentOpenId());
		  					  manageAdminUser.setRoleId(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
		  					  ManageAdminUser user = manageadminuserService.getManageAdminUser(manageAdminUser);
		  					  //获取一级机构
		  					  HAgent hAgent = new HAgent();
		  					  hAgent.setOpenId(hCompany.getOneAgentOpenId());
		  					  hAgent = hagentService.getHAgent(hAgent);
		  					  //获取对应的openId
		  					  String toOPENID = null;
		  					  if(user!=null&&user.getState()==1&&hAgent!=null&&hAgent.getStatus()==1&&hAgent.getCheck_status()==1){
		  					     toOPENID = user.getOpenId();
		  					     Date d = templeteMap.get(oNo+"_1");
							     if(d==null){
							    	 templeteMap.put(oNo+"_1", new Date());
							    	 huseraccountService.sendJFTempltMsg(toOPENID, super.getMoney(feeTotal), oNo, hCompany.getContact_name(), hCompany.getContact_phone(), hCompany.getId(), hCompany.getName());
							     }
		  					  }
	                      }
	  					  
//	                      if(hCompany!=null&&hCompany.getTwoAgentOpenID()!=null){
//	                    	  ManageAdminUser manageAdminUser2 = new ManageAdminUser();
//	                    	  manageAdminUser2.setTwoAgentOpenId(hCompany.getTwoAgentOpenID());
//	                    	  manageAdminUser2.setRoleId(Integer.valueOf(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID));
//	        				  ManageAdminUser user2 = manageadminuserService.getManageAdminUser(manageAdminUser2);
//	        				  //获取二级机构
//	        				  HAgentTwo hAgentTwo = new HAgentTwo();
//	        				  hAgentTwo.setOpenId(hCompany.getTwoAgentOpenID());
//	        				  hAgentTwo = hagenttwoService.getHAgentTwo(hAgentTwo);
//	        				  //获取对应的openId
//	        				  String toOPENID2 = null;
//	        				  if(user2!=null&&user2.getState()==1&&hAgentTwo!=null&&hAgentTwo.getStatus()==1&&hAgentTwo.getCheck_status()==1){
//	        					  toOPENID2 = user2.getOpenId();
//	        					  huseraccountService.sendJFTempltMsg(toOPENID2, super.getMoney(feeTotal), oNo, hCompany.getContact_name(), hCompany.getContact_phone(), hCompany.getId(), hCompany.getName());
//	        				  }
//	                      }
                    	  
	                      //获取服务人员
	                      if(hCompany!=null&&hCompany.getServicerId()!=null){
	                    	  ManageAdminUser manageAdminUser3 = new ManageAdminUser();
	        				  manageAdminUser3.setAdminId(hCompany.getServicerId());
	        				  ManageAdminUser user3 = manageadminuserService.getManageAdminUser(manageAdminUser3);
	        				  if(user3!=null&&user3.getState()==1){
	        					  Date d = templeteMap.get(oNo+"_3");
	        					  if(d==null){
							    	 templeteMap.put(oNo+"_3", new Date());
							    	 huseraccountService.sendJFTempltMsg(user3.getOpenId(), super.getMoney(feeTotal), oNo, hCompany.getContact_name(), hCompany.getContact_phone(), hCompany.getId(), hCompany.getName());
	        					  }
	        				  }
	                      }
                          //跳转支付页面
                          Map<String,String> payMap = new HashMap<String,String>();
                          payMap.put("version", "1.0.0");
                          payMap.put("encoding", "UTF-8");
                          payMap.put("signMethod", "01");
                          payMap.put("bizType", "000202");
                          payMap.put("frontUrl", FileUploadConstants.FRONT_URL);
                          payMap.put("backUrl", FileUploadConstants.BACK_URL);
                          payMap.put("merId", "000110060120006");
                          payMap.put("merOrderId", oNo);
                          payMap.put("merTxnTime",getNowTime());//获取时间
                          payMap.put("merTxnAmt", feeTotal+"");
//                          payMap.put("merTxnAmt", "1");
                          payMap.put("currencyCode", "156");
                          if(payType2!=null&&payType2==2){
                        	  payMap.put("payBankCode", "BJRCB");//民生E支付
                          }
                          Map<String, String> hiddens = DemoBase.signData(payMap);
                          String html = DemoBase.createHtml(FileUploadConstants.GATE_WAY, hiddens);
                          response.setContentType("text/html;charset=UTF-8");
                          PrintWriter pw = null;
                          try {
                              pw = response.getWriter();
                          } catch (IOException e) {
                              e.printStackTrace();
                          }
                          pw.print(html);
                          pw.flush();
                          pw.close();
                          if(reviewRadio!=null&&reviewRadio == 1&&StringUtils.isNotBlank(phone)){
                        	  //根据订单号查询次数
                        	  Integer sendCount = mapMsg.get(oNo);
                        	  if(sendCount==null||sendCount==0){
                        		  mapMsg.put(oNo, 1);
                        		  sendReviewMsg(phone,feeTotal,cId,IP);
                        	  }
                          }
                      }else{
                          resultMap.put("status", "fail");
                          resultMap.put("msg", "您单位登记的所有电表缴费及时，没有欠费，感谢使用。");
                      }
                  } catch (Exception e) {
                      e.printStackTrace();
                      resultMap.put("status", "fail");
                      resultMap.put("msg", "支付订单生成失败!");
                  }
              }
        	}else if(StringUtils.isNotBlank(payType)&&payType.equals("2")&&subId!=null){//子单位缴费
        		HPayOrder hPayOrderQur = new HPayOrder();
                hPayOrderQur.setSub_id(subId);
                hPayOrderQur.setC_id(cId);
                hPayOrderQur.setPay_status("0");//未支付
                HPayOrder findHPayOrder = hPayOrderService.findTodayHPayOrder(hPayOrderQur);
                String IP = this.getIpAddr(request);
                HAmmeterInfo ammeterParam = new HAmmeterInfo();
                ammeterParam.setS_id(subId);
                ammeterParam.setDelete_state(1);
                ammeterParam.setAmmeterinfo_type(1);
                ammeterParam.setPay_status("1");
                List<HAmmeterInfo> ammeterList = hAmmeterInfoService.getHAmmeterInfoBaseList(ammeterParam);
                //校验订单支付金额是否与欠费金额相等
                boolean flag = true;
                if(findHPayOrder!=null){
                	Integer amount = findHPayOrder.getAmount();//订单金额
                	log.info("--------验证订单amount----------->"+amount);
                	flag = checkPayOrderSumSub(ammeterList,IP,amount);
                }
                if(findHPayOrder!=null && flag){//当天存在支付订单，不重复下单 直接跳转支付页面
                	//发送末班消息
              	  HCompany hCompany1 = new HCompany();
                    hCompany1.setId(cId);
                    HCompany hCompany = hcompanyService.getHCompany(hCompany1);
                    
                  if(hCompany!=null&&hCompany.getOneAgentOpenId()!=null){
                	  ManageAdminUser manageAdminUser = new ManageAdminUser();
      				  manageAdminUser.setOneAgentOpenId(hCompany.getOneAgentOpenId());
      				  manageAdminUser.setRoleId(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
      				  ManageAdminUser user = manageadminuserService.getManageAdminUser(manageAdminUser);
      				  //获取一级机构
    				  HAgent hAgent = new HAgent();
    				  hAgent.setOpenId(hCompany.getOneAgentOpenId());
    				  hAgent = hagentService.getHAgent(hAgent);
      				  //获取对应的openId
      				  String toOPENID = null;
      				  if(user!=null&&user.getState()==1&&hAgent!=null&&hAgent.getCheck_status()==1&&hAgent.getStatus()==1){
      				     toOPENID = user.getOpenId();
      				     Date d = templeteMap.get(findHPayOrder.getO_no()+"_1");
					     if(d==null){
					    	 templeteMap.put(findHPayOrder.getO_no()+"_1", new Date());
					    	 huseraccountService.sendJFTempltMsg(toOPENID, super.getMoney(findHPayOrder.getAmount()), findHPayOrder.getO_no(), hCompany.getContact_name(), hCompany.getContact_phone(), hCompany.getId(), hCompany.getName());
					     }
      				  }
                  }
//                  if(hCompany!=null&&hCompany.getTwoAgentOpenID()!=null){
//                	  ManageAdminUser manageAdminUser2 = new ManageAdminUser();
//                  	  manageAdminUser2.setTwoAgentOpenId(hCompany.getTwoAgentOpenID());
//                  	  manageAdminUser2.setRoleId(Integer.valueOf(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID));
//      				  ManageAdminUser user2 = manageadminuserService.getManageAdminUser(manageAdminUser2);
//      				  //获取二级机构
//    				  HAgentTwo hAgentTwo = new HAgentTwo();
//    				  hAgentTwo.setOpenId(hCompany.getTwoAgentOpenID());
//    				  hAgentTwo = hagenttwoService.getHAgentTwo(hAgentTwo);
//      				  //获取对应的openId
//      				  String toOPENID2 = null;
//      				  if(user2!=null&&user2.getState()==1&&hAgentTwo!=null&&hAgentTwo.getStatus()==1&&hAgentTwo.getCheck_status()==1){
//      					  toOPENID2 = user2.getOpenId();
//      					  huseraccountService.sendJFTempltMsg(toOPENID2, super.getMoney(findHPayOrder.getAmount()), findHPayOrder.getO_no(), hCompany.getContact_name(), hCompany.getContact_phone(), hCompany.getId(), hCompany.getName());
//      				  }
//                  }
                  //获取服务人员
                  if(hCompany!=null&&hCompany.getServicerId()!=null){
                	  ManageAdminUser manageAdminUser3 = new ManageAdminUser();
    				  manageAdminUser3.setAdminId(hCompany.getServicerId());
    				  ManageAdminUser user3 = manageadminuserService.getManageAdminUser(manageAdminUser3);
    				  if(user3!=null&&user3.getState()==1){
    					 Date d = templeteMap.get(findHPayOrder.getO_no()+"_3");
 					     if(d==null){
 					    	 templeteMap.put(findHPayOrder.getO_no()+"_3", new Date());
 					    	 huseraccountService.sendJFTempltMsg(user3.getOpenId(), super.getMoney(findHPayOrder.getAmount()), findHPayOrder.getO_no(), hCompany.getContact_name(), hCompany.getContact_phone(), hCompany.getId(), hCompany.getName());
 					     }
    				  }
                  }
              	  
                    //跳转支付页面
                    Map<String,String> payMap = new HashMap<String,String>();
                    payMap.put("version", "1.0.0");
                    payMap.put("encoding", "UTF-8");
                    payMap.put("signMethod", "01");
                    payMap.put("bizType", "000202");
                    payMap.put("frontUrl", FileUploadConstants.FRONT_URL);
                    payMap.put("backUrl", FileUploadConstants.BACK_URL);
                    payMap.put("merId", "000110060120006");
                    payMap.put("merOrderId", findHPayOrder.getO_no());
                    payMap.put("merTxnTime",getNowTime());//获取时间
    				log.info("-----------money1-------------"+findHPayOrder.getAmount());

                    payMap.put("merTxnAmt", findHPayOrder.getAmount()+"");
                    payMap.put("currencyCode", "156");
                    if(payType2!=null&&payType2==2){
                    	payMap.put("payBankCode", "BJRCB");//民生E支付
                    }
                    Map<String, String> hiddens = DemoBase.signData(payMap);
                    String html = DemoBase.createHtml(FileUploadConstants.GATE_WAY, hiddens);
                    response.setContentType("text/html;charset=UTF-8");
                    PrintWriter pw = null;
                    try {
                        pw = response.getWriter();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    pw.print(html);
                    pw.flush();
                    pw.close();
                    if(reviewRadio!=null&&reviewRadio == 1&&StringUtils.isNotBlank(phone)){
                    	//根据订单号查询次数
                  	  	Integer sendCount = mapMsg.get(findHPayOrder.getO_no());
                  	  	if(sendCount==null||sendCount==0){
                  	  		mapMsg.put(findHPayOrder.getO_no(), 1);
                  	  		sendReviewMsg(phone,findHPayOrder.getAmount(),cId,IP);
                  	  	}
                    }
                }else{//重新生成支付订单
                    HCompany hCompany1 = new HCompany();
                    hCompany1.setId(cId);
                    HCompany hCompany = hcompanyService.getHCompany(hCompany1);
                    boolean is_zz = false;//是否包含增值税发票
                    int ammeterSum = 0;//总电表数
                    int owedAmmeterSum = 0;//欠费电表数
                    StringBuffer sb = new StringBuffer();
                    int rowsTotal = 0;
                    int feeTotal = 0;
                    List<HSubOrder> subOrderList = new ArrayList<HSubOrder>();
                    try {
                        for (HAmmeterInfo hAmmeterInfo : ammeterList) {
                            ammeterSum++;
                            String ammeterNo = hAmmeterInfo.getAmmeter_no();//电表号
                            if(hAmmeterInfo.getFp_style()==null){
                            	is_zz = true;
                            }
                            JSONObject result = hCommonService.hXTServiceQuery(ammeterNo, IP);
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
                                        hSubOrder.setSub_id(subId);
                                        hSubOrder.setElectric(ammeterNo);//电表编号
                                        hSubOrder.setAmount(fee);//欠费金额
                                        //获取分组信息
                                        HSubCompany subcom = new HSubCompany();
                                        subcom.setS_id(subId);
                                        subcom = hSubCompanyService.getHSubCompany(subcom);
                                        if(subcom!=null){
                                      	  hSubOrder.setSub_name(subcom.getSub_name());
                                      	  hSubOrder.setConsignee(subcom.getConsignee());
                                      	  String adress = "";
	                                  	  if(StringUtils.isNotBlank(subcom.getProvince_name())){
	                                  		  adress = adress + subcom.getProvince_name();
	                                  	  }
	                                  	  
	                                  	  if(StringUtils.isNotBlank(subcom.getCity_name())){
	                                  		  adress = adress + subcom.getCity_name();
	                                  	  }
	                                  	  
	                                  	  if(StringUtils.isNotBlank(subcom.getArea_name())){
	                                  		  adress = adress + subcom.getArea_name();
	                                  	  }
	                                  	  
	                                  	  if(StringUtils.isNotBlank(subcom.getConsignee_address())){
	                                  		  adress = adress + subcom.getConsignee_address();
	                                  	  }
	                                  	  hSubOrder.setConsignee_address(adress);
//	                                  	  hSubOrder.setConsignee_address(subcom.getProvince_name()+subcom.getCity_name()+subcom.getArea_name()+subcom.getConsignee_address());
                                      	  hSubOrder.setConsignee_phone(subcom.getConsignee_phone());
                                        }
                                        subOrderList.add(hSubOrder);
                                    }
                                }
                            } else {//获取电表信息失败

                            }
                        }
                        if (sb.length() > 0) {//存在欠费电表
                        	System.out.println("-----------money2-------------"+feeTotal);
                            //启动多线程查单
//                          		ThreadUseExtends thread1=new ThreadUseExtends(IP,is_zz,feeTotal,cId,hCompany.getYwyId(),subOrderList,sb);
//                          		thread1.start();
                      		String oNo = createOrder(IP,is_zz,feeTotal,cId,hCompany.getYwyId(),subOrderList,sb,subId);//解决订单重复问题
                      		 //发送末班消息
                      		if(hCompany!=null&&hCompany.getOneAgentOpenId()!=null){
  	                    	  ManageAdminUser manageAdminUser = new ManageAdminUser();
	  	  					  manageAdminUser.setOneAgentOpenId(hCompany.getOneAgentOpenId());
	  	  					  manageAdminUser.setRoleId(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
	  	  					  ManageAdminUser user = manageadminuserService.getManageAdminUser(manageAdminUser);
	  	  					 //获取一级机构
	  	    				  HAgent hAgent = new HAgent();
	  	    				  hAgent.setOpenId(hCompany.getOneAgentOpenId());
	  	    				  hAgent = hagentService.getHAgent(hAgent);
	  	  					  //获取对应的openId
	  	  					  String toOPENID = null;
	  	  					  if(user!=null&&user.getState()==1&&hAgent!=null&&hAgent.getStatus()==1&&hAgent.getCheck_status()==1){
	  	  					     toOPENID = user.getOpenId();
		  	  					 Date d = templeteMap.get(oNo+"_1");
		 					     if(d==null){
		 					    	 templeteMap.put(oNo+"_1", new Date());
		 					    	 huseraccountService.sendJFTempltMsg(toOPENID, super.getMoney(feeTotal), oNo, hCompany.getContact_name(), hCompany.getContact_phone(), hCompany.getId(), hCompany.getName());
		 					     }
	  	  					  }
                      		}
//                      		if(hCompany!=null&&hCompany.getTwoAgentOpenID()!=null){
//                      			ManageAdminUser manageAdminUser2 = new ManageAdminUser();
//  	                      	  	manageAdminUser2.setTwoAgentOpenId(hCompany.getTwoAgentOpenID());
//  	                      	  	manageAdminUser2.setRoleId(Integer.valueOf(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID));
//  	                      	  	ManageAdminUser user2 = manageadminuserService.getManageAdminUser(manageAdminUser2);
//  	                      	  	//获取二级机构
//  	                      	  	HAgentTwo hAgentTwo = new HAgentTwo();
//  	                      	  	hAgentTwo.setOpenId(hCompany.getTwoAgentOpenID());
//  	                      	  	hAgentTwo = hagenttwoService.getHAgentTwo(hAgentTwo);
//  	                      	  	//获取对应的openId
//  	                      	  	String toOPENID2 = null;
//  	                      	  	if(user2!=null&&user2.getState()==1&&hAgentTwo!=null&&hAgentTwo.getCheck_status()==1&&hAgentTwo.getStatus()==1){
//  	                      	  		toOPENID2 = user2.getOpenId();
//  	                      	  		huseraccountService.sendJFTempltMsg(toOPENID2, super.getMoney(feeTotal), oNo, hCompany.getContact_name(), hCompany.getContact_phone(), hCompany.getId(), hCompany.getName());
//  	                      	  	}
//	  	  					}
                      		//获取服务人员
                            if(hCompany!=null&&hCompany.getServicerId()!=null){
                          	  ManageAdminUser manageAdminUser3 = new ManageAdminUser();
              				  manageAdminUser3.setAdminId(hCompany.getServicerId());
              				  ManageAdminUser user3 = manageadminuserService.getManageAdminUser(manageAdminUser3);
              				  if(user3!=null&&user3.getState()==1){
              					 Date d = templeteMap.get(oNo+"_3");
		 					     if(d==null){
		 					    	 templeteMap.put(oNo+"_3", new Date());
		 					    	 huseraccountService.sendJFTempltMsg(user3.getOpenId(), super.getMoney(feeTotal), oNo, hCompany.getContact_name(), hCompany.getContact_phone(), hCompany.getId(), hCompany.getName());
		 					     }
              				  }
                            }
	                      	  
                            //跳转支付页面
                            Map<String,String> payMap = new HashMap<String,String>();
                            payMap.put("version", "1.0.0");
                            payMap.put("encoding", "UTF-8");
                            payMap.put("signMethod", "01");
                            payMap.put("bizType", "000202");
                            payMap.put("frontUrl", FileUploadConstants.FRONT_URL);
                            payMap.put("backUrl", FileUploadConstants.BACK_URL);
                            payMap.put("merId", "000110060120006");
                            payMap.put("merOrderId", oNo);
                            payMap.put("merTxnTime",getNowTime());//获取时间
                            payMap.put("merTxnAmt", feeTotal+"");
//                            payMap.put("merTxnAmt", "1");
                            payMap.put("currencyCode", "156");
                            if(payType2!=null&&payType2==2){
                            	payMap.put("payBankCode", "BJRCB");//民生E支付
                            }
                            Map<String, String> hiddens = DemoBase.signData(payMap);
                            String html = DemoBase.createHtml(FileUploadConstants.GATE_WAY, hiddens);
                            response.setContentType("text/html;charset=UTF-8");
                            PrintWriter pw = null;
                            try {
                                pw = response.getWriter();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            pw.print(html);
                            pw.flush();
                            pw.close();
                            if(reviewRadio!=null&&reviewRadio == 1&&StringUtils.isNotBlank(phone)){
                            	//根据订单号查询次数
                          	  Integer sendCount = mapMsg.get(oNo);
                          	  if(sendCount==null||sendCount==0){
                          		  mapMsg.put(oNo, 1);
                          		  sendReviewMsg(phone,feeTotal,cId,IP);
                          	  }
                            }
                        }else{
                            resultMap.put("status", "fail");
                            resultMap.put("msg", "您单位登记的所有电表缴费及时，没有欠费，感谢使用。");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        resultMap.put("status", "fail");
                        resultMap.put("msg", "支付订单生成失败!");
                    }
                }
        	}else{
        		resultMap.put("status", "fail");
                resultMap.put("msg", "参数错误!");
        	}
        }
        ResponseUtil.responseText(response, gson.toJson(resultMap));
        return null;
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
                hAmmeterInfoQry.setDelete_state(1);
                hAmmeterInfos = hAmmeterInfoService.getHAmmeterInfoBaseList(hAmmeterInfoQry);
                for (HAmmeterInfo hAmmeterInfo : hAmmeterInfos) {
                    String ammeterNo = hAmmeterInfo.getAmmeter_no();//电表号

                    JSONObject result = hCommonService.hXTServiceQuery(ammeterNo, IP);
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
    /**
     * 判断欠费金额
     * @param hSubCompanyList
     * @param IP
     * @param amount
     * @return
     */
    public boolean checkPayOrderSumSub(List<HAmmeterInfo> ammeterList,String IP,Integer amount){
    	
    	boolean flag = false;
    	int totalFee = 0;
		for (HAmmeterInfo hAmmeterInfo : ammeterList) {
			String ammeterNo = hAmmeterInfo.getAmmeter_no();//电表号
			JSONObject result = hCommonService.hXTServiceQuery(ammeterNo, IP);
			if ("00".equals(result.get("resultCode"))) {//获取电表信息成功
				Integer fee = Integer.valueOf(result.get("totalFeeStr") == null ? "0" : result.get("totalFeeStr").toString());//欠费金额
				totalFee = totalFee + fee;//欠费总额累加
			} else {//获取电表信息失败
				
			}
		}
    	log.info("--------子单位验证订单amount----------->"+amount);
    	if(amount.equals(totalFee)){//欠费金额相等
    		flag = true;
    		log.info("==================>子单位订单欠费金额相等 amount="+amount+"   totalFee="+totalFee);
    	}else{
    		log.info("==================>子单位订单欠费金额不相等 amount="+amount+"   totalFee="+totalFee);
    	}
    	return flag;
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
     * 校验验证码
     * @param request
     * @param response
     * @param model
     */
    @RequestMapping(value = "/checkVerifyImg", method = RequestMethod.GET)
    public String checkVerifyImg(HttpServletRequest request, HttpServletResponse response, Model model){
        Map<String, Object> resultMap = new HashMap<String, Object>();//返回数据

        String verify = RequestHandler.getString(request, "verify");

        Object token = request.getSession().getAttribute(SessionName.TOKEN);// 验证码
        if (token == null || !verify.equalsIgnoreCase(token.toString())) {
            resultMap.put("status", "fail");
            resultMap.put("msg", "验证码校验失败!");
        }else{
            resultMap.put("status", "success");
            resultMap.put("msg", "验证码校验成功!");

        }
        ResponseUtil.responseText(response, gson.toJson(resultMap));
        return null;
    }
    /**
     * 获取时间
     * @return
     */
    public synchronized String getNowTime(){
       return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }

    /**
     * 获取系统时间
     * @return
     */
    @RequestMapping(value = "/checkPayTime", method = RequestMethod.GET)
    public String checkPayTime(HttpServletRequest request, HttpServletResponse response, Model model){
        Map<String, Object> resultMap = new HashMap<String, Object>();//返回数据
        Date nowTime = new Date();//当前时间
        int hours = nowTime.getHours();
        if (hours >= 23 || hours <= 4) {//晚上23点至凌晨4点禁止缴费
            resultMap.put("status", "fail");
            resultMap.put("msg", "尊敬的客户，缴费时间为4：00-23:00，请您等候");
        }else {
            resultMap.put("status", "success");
            resultMap.put("msg", "校验成功！");

        }
        ResponseUtil.responseText(response, gson.toJson(resultMap));
        return null;
    }
    
    /**
     * 获取系统时间
     * @return
     */
	@RequestMapping(value = "/toMiddle", method = RequestMethod.GET)
	public String toMiddle(HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
//			ManageAdminUser adminUser = (ManageAdminUser) request.getSession().getAttribute(SessionName.ADMIN_USER);
			Integer cId = RequestHandler.getInteger(request, "cId");
			request.getSession().setAttribute("cId", cId);
			String reviewRadio = RequestHandler.getString(request, "reviewRadio");
			request.getSession().setAttribute("reviewRadio",reviewRadio);
			String reviewerName = RequestHandler.getString(request, "reviewerName");
			Integer reviewerSex = RequestHandler.getInteger(request, "reviewerSex");
			String reviewerPhone = RequestHandler.getString(request, "reviewerPhone");
			Integer payType1 = RequestHandler.getInteger(request, "payType1");
			Integer payType2 = RequestHandler.getInteger(request, "payType2");
			// 处理复核人员信息
			HReviewUser rev = new HReviewUser();
			rev.setCompanyId(cId);
			rev.setState(1);
			rev.setIsDefault(1);
			rev = hreviewuserService.getHReviewUser(rev);
			if("1".equals(reviewRadio)){
				if (rev == null) {
					//校验手机号、参数
					if(StringUtils.isNotBlank(reviewRadio)&&StringUtils.isNotBlank(reviewerName)&&reviewerSex!=null&&StringUtils.isNotBlank(reviewerPhone)){
						Pattern p = Pattern.compile("^1\\d{10}$");
						Matcher m = p.matcher(reviewerPhone);
						if(!m.matches()){
							response.setContentType("text/html;charset=UTF-8");
							PrintWriter pw = null;
							try {
								pw = response.getWriter();
							} catch (IOException e) {
								e.printStackTrace();
							}
							pw.print("<script>"
									+ "alert('复核人员手机号不正确！请返回重试!');window.close();"
									+ "</script>");
							pw.flush();
							pw.close();
							return null;
						}
					}else{
						response.setContentType("text/html;charset=UTF-8");
						PrintWriter pw = null;
						try {
							pw = response.getWriter();
						} catch (IOException e) {
							e.printStackTrace();
						}
						pw.print("<script>"
								+ "alert('参数错误!');window.close();"
								+ "</script>");
						pw.flush();
						pw.close();
						return null;
					}
					// 生成默认复核人员
					rev = new HReviewUser();
					rev.setUserName(reviewerName);
					rev.setState(1);
					rev.setCreateTime(new Date());
					rev.setCreateId(cId);
					rev.setSex(reviewerSex);
					rev.setMobil(reviewerPhone);
					rev.setIsDefault(1);
					rev.setCompanyId(cId);
					rev.setMsgSwitch(1);
					int id = hreviewuserService.insertHReviewUser(rev);
					if(id>0){
						model.addAttribute("payType1", payType1);
						model.addAttribute("payType2", payType2);
					}else{
						response.setContentType("text/html;charset=UTF-8");
						PrintWriter pw = null;
						try {
							pw = response.getWriter();
						} catch (IOException e) {
							e.printStackTrace();
						}
						pw.print("<script>"
								+ "alert('复核人员信息错误！请返回重试');window.close();"
								+ "</script>");
						pw.flush();
						pw.close();
						return null;
					}
				}
				request.getSession().setAttribute("reviewRadio", 1);
			}else{
				request.getSession().setAttribute("reviewRadio", 0);
			}
			request.getSession().setAttribute("payType", RequestHandler.getString(request, "payType"));
			request.getSession().setAttribute("subId", RequestHandler.getString(request, "subId"));
			request.getSession().setAttribute("payType1", payType1);
			request.getSession().setAttribute("payType2", payType2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/front/middle";
	}
    
    
    /**
	 * 调用线程处理缴费
	 * @author ll
     * @throws Exception 
	 *
	 */
	/*class ThreadUseExtends extends Thread {
		private String oNo;
        private String IP;
        private Integer paysum;
        private Integer cId;
        private Integer ywyId;
        private StringBuffer sb;
        private List<HSubOrder> subOrderList;
        private boolean is_zz;
        public ThreadUseExtends(String IP, boolean is_zz, Integer paysum,Integer cId,Integer ywyId,List<HSubOrder> subOrderList,StringBuffer sb){ 
//        	this.oNo = oNo; 
        	this.IP = IP;
        	this.cId = cId;
        	this.paysum = paysum;
        	this.ywyId = ywyId;
        	this.sb = sb;
        	this.subOrderList = subOrderList;
        	this.is_zz = is_zz;
        }
        
        public void run(){
        	synchronized (this) {
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
                    hPayOrder.setElectric_number(ammeterNums);
                    hPayOrder.setYw_id(ywyId==null?null:ywyId);
                    hPayOrder.setCreate_time(new Date());
                    
                    HPayOrder hPayOrderQur = new HPayOrder();
                    hPayOrderQur.setC_id(cId);
                    hPayOrderQur.setPay_status("0");//未支付
                    HPayOrder findHPayOrder = hPayOrderService.findTodayHPayOrder(hPayOrderQur);
                	if(findHPayOrder==null){//不存在订单
                		hPayOrderService.insertHPayOrder(hPayOrder);//插入订单数据
                        for (HSubOrder hSubOrder:subOrderList){
                        	hSubOrder.setO_id(oNo);
                            hSubOrderService.insertHSubOrder(hSubOrder);//插入子订单数据
                        }
                	}else{
                		log.info(oNo+"-----------findHPayOrder.getAmount()---------------->"+findHPayOrder.getAmount());
                		log.info(oNo+"--------paysum-------------->"+paysum);
                		log.info(oNo+"--------paysum111-------------->"+findHPayOrder.getAmount().equals(paysum));
                		if(!findHPayOrder.getAmount().equals(paysum)){//金额不相等
                			hPayOrderService.insertHPayOrder(hPayOrder);//插入订单数据
                            for (HSubOrder hSubOrder:subOrderList){
                                hSubOrderService.insertHSubOrder(hSubOrder);//插入子订单数据
                            }
                		}
                	}
                }catch (Exception e){
                        e.printStackTrace();
                }
			}
        }
	}*/

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
	public synchronized String createOrder(String IP, boolean is_zz, Integer paysum,Integer cId,Integer ywyId,List<HSubOrder> subOrderList,StringBuffer sb, Integer subId) {
		String oNo = "";
		try{
			// 排序
			
			HPayOrder hPayOrder = new HPayOrder();//电表支付订单
			String ammeterNums = sb.toString().substring(0, sb.length() - 1);
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
			comp = hcompanyService.getHCompany(comp);
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
				hPayOrderQur.setSub_id(-1);
			}else{
				hPayOrderQur.setC_id(cId);
				hPayOrderQur.setPay_status("0");//未支付
				hPayOrderQur.setSub_id(subId);
			}
			//插入代理数据
			HCompany company = new HCompany();
			company.setId(cId);
			company = hcompanyService.getHCompany(company);
			if(company!=null){
				if(company.getOneAgentOpenId()!=null){
					  ManageAdminUser manageAdminUser = new ManageAdminUser();
  					  manageAdminUser.setOneAgentOpenId(company.getOneAgentOpenId());
  					  manageAdminUser.setRoleId(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
  					  ManageAdminUser user = manageadminuserService.getManageAdminUser(manageAdminUser);
						//获取一级机构
					  HAgent hAgent = new HAgent();
					  hAgent.setOpenId(company.getOneAgentOpenId());
					  hAgent = hagentService.getHAgent(hAgent);
					  if(hAgent!=null&&hAgent.getStatus()==1&&hAgent.getCheck_status()==1&&user!=null&&user.getState()==1){
						  hPayOrder.setOneAgentName(company.getOneAgentName());
						  hPayOrder.setOneAgentOpenId(company.getOneAgentOpenId());
					  }
				}
				if(company.getTwoAgentOpenID()!=null){
				  ManageAdminUser manageAdminUser2 = new ManageAdminUser();
               	  manageAdminUser2.setTwoAgentOpenId(company.getTwoAgentOpenID());
               	  manageAdminUser2.setRoleId(Integer.valueOf(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID));
   				  ManageAdminUser user2 = manageadminuserService.getManageAdminUser(manageAdminUser2);
					//获取二级机构
  				  HAgentTwo hAgentTwo = new HAgentTwo();
  				  hAgentTwo.setOpenId(company.getTwoAgentOpenID());
  				  hAgentTwo = hagenttwoService.getHAgentTwo(hAgentTwo);
  				  if(hAgentTwo!=null&&hAgentTwo.getStatus()==1&&hAgentTwo.getCheck_status()==1&&user2!=null&&user2.getState()==1){
  					hPayOrder.setTwoAgentName(company.getTwoAgentName());
  					hPayOrder.setTwoAgentOpenID(company.getTwoAgentOpenID());
  				  }
				}
				if(company!=null&&company.getServicerId()!=null){
              	  ManageAdminUser manageAdminUser3 = new ManageAdminUser();
  				  manageAdminUser3.setAdminId(company.getServicerId());
  				  ManageAdminUser user3 = manageadminuserService.getManageAdminUser(manageAdminUser3);
  				  if(user3!=null&&user3.getState()==1){
  					hPayOrder.setServicerId(company.getServicerId());
  					hPayOrder.setServicerName(company.getServicerName());
  				  }
                }
			}
			HPayOrder findHPayOrder = hPayOrderService.findTodayHPayOrder(hPayOrderQur);
			if(findHPayOrder==null){//不存在订单
				Map map = new HashMap();
				map.put("snNamePre", "PO");//编号前缀
				map.put("snName", "PaySN");//编号名称，记录用
				map.put("num", "4");//编号长度
				map.put("sn", "@sn");//编号
				oNo = callProcedureService.callGenerateSnNo(map);//订单编号
				hPayOrder.setO_no(oNo);
				hPayOrderService.insertHPayOrder(hPayOrder);//插入订单数据
				for (HSubOrder hSubOrder:subOrderList){
					hSubOrder.setO_id(oNo);
					hSubOrderService.insertHSubOrder(hSubOrder);//插入子订单数据
				}
			}else{
				oNo = findHPayOrder.getO_no();
				log.info(oNo+"-----------findHPayOrder.getAmount()---------------->"+findHPayOrder.getAmount());
				log.info(oNo+"--------paysum-------------->"+paysum);
				log.info(oNo+"--------paysum111-------------->"+findHPayOrder.getAmount().equals(paysum));
				if(!findHPayOrder.getAmount().equals(paysum)){//金额不相等
					Map map = new HashMap();
					map.put("snNamePre", "PO");//编号前缀
					map.put("snName", "PaySN");//编号名称，记录用
					map.put("num", "4");//编号长度
					map.put("sn", "@sn");//编号
					oNo = callProcedureService.callGenerateSnNo(map);//订单编号
					hPayOrder.setO_no(oNo);
					hPayOrderService.insertHPayOrder(hPayOrder);//插入订单数据
					for (HSubOrder hSubOrder:subOrderList){
						hSubOrder.setO_id(hPayOrder.getO_no());
						hSubOrderService.insertHSubOrder(hSubOrder);//插入子订单数据
					}
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return oNo;
	}
	//发送复核短信
	private void sendReviewMsg(String phone,Integer fee,Integer cId,String ip) throws Exception{
		//获取复核人
		HReviewUser rev = new HReviewUser();
		rev.setCompanyId(cId);
		rev.setState(1);
		rev.setIsDefault(1);
		rev = hreviewuserService.getHReviewUser(rev);
		if(rev==null){
			rev = new HReviewUser();
		}
		String name = "";
		if(!StringUtils.isNotBlank(rev.getUserLabel())){
			name = rev.getUserName();
			if(rev.getSex()==1){
				name = name + "先生";
			}else{
				name = name + "女士";
			}
		}else{
			name = rev.getUserLabel();
//			if(rev.getSex()==1){
//				name = name + "先生";
//			}else{
//				name = name + "女士";
//			}
		}
		String content = name + "，贵单位正在企业缴费平台制单，金额共计"+super.getMoney(fee)+"元 ，请留意您的企业网银账户并复核订单，超过今天复核可能会因为超时而导致缴费失败，如您今天无法复核，请重新制单后再复核，客服电话：010-96199。";
		SendMsgUtil.sendMsg(phone,content);
//		HMessageLog hMessageLog = new HMessageLog();
//		hMessageLog.setContent(content);
//		hMessageLog.setCreateTime(new Date());
//		hMessageLog.setPhone(phone);
//		hMessageLog.setIp(ip);
//		try {
//			hmessagelogService.insertHMessageLog(hMessageLog);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	   /**
     * 保存复核人员
     * @return
     */
	@RequestMapping(value = "/saveReviewer", method = RequestMethod.POST)
	public String saveReviewer(HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			ManageAdminUser adminUser = (ManageAdminUser) request.getSession().getAttribute(SessionName.ADMIN_USER);
			request.getSession().setAttribute("cId", adminUser.getCompanyId());
			String reviewerName = RequestHandler.getString(request, "reviewerName");
			Integer reviewerSex = RequestHandler.getInteger(request, "reviewerSex");
			String reviewerPhone = RequestHandler.getString(request, "reviewerPhone");
			// 处理复核人员信息
			HReviewUser rev = new HReviewUser();
			rev.setCompanyId(adminUser.getCompanyId());
			rev.setState(1);
			rev.setIsDefault(1);
			rev = hreviewuserService.getHReviewUser(rev);
			if (rev == null) {
				//校验手机号、参数
				if(StringUtils.isNotBlank(reviewerName)&&reviewerSex!=null&&StringUtils.isNotBlank(reviewerPhone)){
					Pattern p = Pattern.compile("^1\\d{10}$");
					Matcher m = p.matcher(reviewerPhone);
					if(!m.matches()){
						writeErrorMsg("复核人员手机号不正确！请返回重试", null, response);
		                return null;
					}
				}else{
					writeErrorMsg("参数错误！", null, response);
	                return null;
				}
				// 生成默认复核人员
				rev = new HReviewUser();
				rev.setUserName(reviewerName);
				rev.setState(1);
				rev.setCreateTime(new Date());
				rev.setCreateId(adminUser.getAdminId());
				rev.setSex(reviewerSex);
				rev.setMobil(reviewerPhone);
				rev.setIsDefault(1);
				rev.setCompanyId(adminUser.getCompanyId());
				rev.setMsgSwitch(1);
				int id = hreviewuserService.insertHReviewUser(rev);
				writeSuccessMsg("保存成功", id, response);
			}else{
				writeSuccessMsg("已存在复核人员，请刷新页面重新尝试！", null, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
