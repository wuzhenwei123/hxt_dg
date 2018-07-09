package com.hxt.hCompany.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.base.utils.FileUploadConstants;
import com.base.utils.MD5;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.utils.SendMsgUtil;
import com.hxt.hAgent.dao.HAgentDAO;
import com.hxt.hAgent.model.HAgent;
import com.hxt.hAgent.service.HAgentService;
import com.hxt.hAgentTwo.dao.HAgentTwoDAO;
import com.hxt.hAgentTwo.model.HAgentTwo;
import com.hxt.hAmmeterInfo.model.HAmmeterInfo;
import com.hxt.hAmmeterInfo.service.HAmmeterInfoService;
import com.hxt.hArea.service.HAreaService;
import com.hxt.hCommon.service.HCommonService;
import com.hxt.hCompany.dao.HCompanyDAO;
import com.hxt.hCompany.model.HCompany;
import com.hxt.hMessageLog.model.HMessageLog;
import com.hxt.hMessageLog.service.HMessageLogService;
import com.hxt.hProfitRatio.model.HProfitRatio;
import com.hxt.hProfitRatio.service.HProfitRatioService;
import com.hxt.hRecRecord.model.HRecRecord;
import com.hxt.hRecRecord.service.HRecRecordService;
import com.hxt.hRegGuli.dao.HRegGuliDAO;
import com.hxt.hRegGuli.model.HRegGuli;
import com.hxt.hRegGuliSend.dao.HRegGuliSendDAO;
import com.hxt.hRegGuliSend.model.HRegGuliSend;
import com.hxt.hSubCompany.model.HSubCompany;
import com.hxt.hSubCompany.service.HSubCompanyService;
import com.hxt.hUserAccount.model.HUserAccount;
import com.hxt.hUserAccount.service.HUserAccountService;
import com.hxt.hUserAccountDetail.model.HUserAccountDetail;
import com.hxt.hUserAccountDetail.service.HUserAccountDetailService;
import com.sys.adminUserRole.dao.AdminUserRoleDAO;
import com.sys.adminUserRole.model.AdminUserRole;
import com.sys.manageAdminUser.dao.ManageAdminUserDAO;
import com.sys.manageAdminUser.model.ManageAdminUser;

/**
 * @author	yy
 * @time	2015年11月17日 23:59:31
 */
 @Service("hCompanyService")
public class HCompanyService {
	 
	Logger log = Logger.getLogger(HCompanyService.class);
	
	public static int INDEX_SERVER = 0;

	@Resource(name = "hCompanyDao")
    private HCompanyDAO hCompanyDAO;
	@Resource(name = "hAgentDao")
	private HAgentDAO hagentdao;
	@Resource(name = "manageAdminUserDao")
	private ManageAdminUserDAO manageAdminUserDao;
	@Resource(name = "adminUserRoleDao")
	private AdminUserRoleDAO adminUserRoleDao;
	@Resource(name = "hAgentTwoDao")
	private HAgentTwoDAO hAgentTwoDao;
	@Autowired
	private HProfitRatioService hprofitratioService = null;
	@Autowired
	private HRecRecordService hrecrecordService = null;
	@Autowired
	private HAreaService hareaService = null;
	@Autowired
	private HUserAccountService huseraccountService = null;
	@Autowired
	private HMessageLogService hmessagelogService = null;
	@Resource(name = "hRegGuliDao")
    private HRegGuliDAO hRegGuliDAO;
	@Resource(name = "hRegGuliSendDao")
    private HRegGuliSendDAO hRegGuliSendDAO;
	@Autowired
	private HUserAccountDetailService huseraccountdetailService = null;
    
    public ResponseList<HCompany> getHCompanyList(HCompany hCompany) {
        return hCompanyDAO.getHCompanyList(hCompany);
    }
    public ResponseList<HCompany> getHCompanyList2(HCompany hCompany) {
    	return hCompanyDAO.getHCompanyList2(hCompany);
    }
    
    public List<HCompany> getHCompanyBaseList(HCompany hCompany) {
        return hCompanyDAO.getHCompanyBaseList(hCompany);
    }
    /**
     * 缴费单位导出
     * @param hCompany
     * @return
     */
    public List<HCompany> getUnPayHCompany(HCompany hCompany) {
    	return hCompanyDAO.getUnPayHCompany(hCompany);
    }
    
    public List<HCompany> getHCompanyList1(HCompany hCompany) {
    	return hCompanyDAO.getHCompanyList1(hCompany);
    }
    
	public List<HCompany> getCompanyListByOneAgent(HCompany hCompany) {
    	return hCompanyDAO.getCompanyListByOneAgent(hCompany);
	}
	
	public List<HCompany> getCompanyListByOther(HCompany hCompany) {
		return hCompanyDAO.getCompanyListByOther(hCompany);
	}
	public List<HCompany> getUnPayHCompany1(HCompany hCompany) {
		return hCompanyDAO.getUnPayHCompany1(hCompany);
	}
    
    public int getHCompanyListCount(HCompany hCompany) {
        return hCompanyDAO.getHCompanyListCount(hCompany);
    }
    public int getHCompanyListCount2(HCompany hCompany) {
    	return hCompanyDAO.getHCompanyListCount2(hCompany);
    }
    
    public int getCompanyListByOneAgentCount(HCompany hCompany) {
		return hCompanyDAO.getCompanyListByOneAgentCount(hCompany);
	}
	
	public int getCompanyListByOtherCount(HCompany hCompany) {
		return hCompanyDAO.getCompanyListByOtherCount(hCompany);
	}

    public HCompany getHCompany(HCompany hCompany) { 
        return hCompanyDAO.getHCompany(hCompany);
    }

    public int insertHCompany(HCompany hCompany) throws Exception {
        return hCompanyDAO.insertHCompany(hCompany);
    }

    public int updateHCompany(HCompany hCompany) throws Exception {
        return hCompanyDAO.updateHCompany(hCompany);
    }
    
    public int updateServicerName(HCompany hCompany) throws Exception {
    	return hCompanyDAO.updateServicerName(hCompany);
    }
    
    public int updateOneAgentName(HCompany hCompany) throws Exception {
    	return hCompanyDAO.updateOneAgentName(hCompany);
    }
    
    public int updateTwoAgentName(HCompany hCompany) throws Exception {
    	return hCompanyDAO.updateTwoAgentName(hCompany);
    }
    
    public int updateHCompany1(HCompany hCompany) throws Exception {
    	return hCompanyDAO.updateHCompany1(hCompany);
    }
    
    public int updateHCompany2(HCompany hCompany) throws Exception {
    	return hCompanyDAO.updateHCompany2(hCompany);
    }
    
    public int removeHCompany(HCompany hCompany) throws Exception {
        return hCompanyDAO.removeHCompany(hCompany);
    }
    
    public List<HCompany> getHCompanyListByTask(HCompany hCompany) {
        return hCompanyDAO.getHCompanyListByTask(hCompany);
    }
    public boolean checkPhone(String phone) {
    	HCompany hCompany = new HCompany();
    	hCompany.setContact_phone(phone);
        List list = hCompanyDAO.getHCompanyBaseList(hCompany);
        if(list!=null&&list.size()>0){
        	return false;
        }else{
        	return true;
        }
    }

	public int saveCompanyForReg(HttpServletRequest request,HCommonService hCommonService,HCompanyService hcompanyService,HAmmeterInfoService hAmmeterInfoService,HSubCompanyService hsubcompanyservice,HAgentService hagentservice,HCompany hcompany) throws Exception {
		//注册校验电表号
		String agentOpenId = null;
		Integer agentId = null;
		Integer agentTwoId = null;
		String agentTwoOpenId = null;
		Integer servicerId = null;
		String agentName = null;
		String agentTwoName = null;
		String servicerName = null;
		Integer reg_gl_id = null;
		Integer sendGLId = null;
		
		HAgentTwo agentTwo = null;
		String ammeterNo = RequestHandler.getString(request, "ammeterNo");
		if(StringUtils.isNotBlank(ammeterNo)){
			String IP = this.getIpAddr(request);
			JSONObject result = hCommonService.hXTServiceQuery(ammeterNo, IP);
			if("00".equals(result.get("resultCode"))){
				String ammeterAddress = RequestHandler.getString(request, "ammeterAddress");
				HAmmeterInfo hAmmeterInfo1 = new HAmmeterInfo();
				hAmmeterInfo1.setAmmeter_no(ammeterNo);
				hAmmeterInfo1.setDelete_state(1);
				int count = hAmmeterInfoService.getHAmmeterInfoListCount(hAmmeterInfo1);//不能存在未删除相同电表号
				
				//校验缴费号是否存在
				HAmmeterInfo hAmmeterInfo = new HAmmeterInfo();
				hAmmeterInfo.setAmmeter_no(ammeterNo);
				int countss = hAmmeterInfoService.getHAmmeterInfoListCount(hAmmeterInfo);
				
				hAmmeterInfo1 = hareaService.setAreaCodeToAmmeterInfo(hAmmeterInfo1);
				if(count>0){
					return -1;
				}else{
					hAmmeterInfo1.setAmmeter_address(ammeterAddress);
					//推荐人
					String recommend = RequestHandler.getString(request, "recPhone");
					String refereeOpenId = RequestHandler.getString(request, "refereeOpenId");//微信扫描二维码推荐人优先级高于recommend
					String toOPENID = null;
					String toOPENID2 = null;
					String toOPENID3 = null;
					if(!StringUtils.isNotBlank(refereeOpenId)&&StringUtils.isNotBlank(recommend)){
						Pattern p = Pattern.compile("^1\\d{10}$");
						Matcher m = p.matcher(recommend);
						if(m.matches()){
							hcompany.setRec_phone(recommend);
							HAgent ag = new HAgent();
							ag.setMobile1(recommend);
							ag.setStatus(1);
							ag.setCheck_status(1);
							List<HAgent> tmplist = hagentservice.getHAgentBaseList(ag);
							if(tmplist!=null&&tmplist.size()>0){
								agentOpenId = tmplist.get(0).getOpenId();
								agentName = tmplist.get(0).getName();
								agentId = tmplist.get(0).getId();
								//获取对应的openId
								ManageAdminUser manageAdminUser = new ManageAdminUser();
								manageAdminUser.setOneAgentOpenId(agentOpenId);
								manageAdminUser.setRoleId(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
								ManageAdminUser user = manageAdminUserDao.getManageAdminUser(manageAdminUser);
								if(user!=null){
									toOPENID = user.getOpenId();
								}
							}else{
								//一级不存在 取二级
								HAgentTwo ag2 = new HAgentTwo();
								ag2.setMobile1(recommend);
								ag2.setStatus(1);
								ag2.setCheck_status(1);
								List<HAgentTwo> tmplist2 = hAgentTwoDao.getHAgentTwoBaseList(ag2);
								if(tmplist2!=null&&tmplist2.size()>0){
									agentTwo = tmplist2.get(0);
									agentTwoOpenId = tmplist2.get(0).getOpenId();
									agentTwoName = tmplist2.get(0).getName();
									reg_gl_id = tmplist2.get(0).getReg_gl_id();
									agentTwoId = tmplist2.get(0).getId();
									//取对应一级代理
//									HAgent tmp = new HAgent();
//									tmp.setId(tmplist2.get(0).getAgent_id());
//									tmp = hagentservice.getHAgent(tmp);
//									if(tmp!=null){
										agentOpenId = tmplist2.get(0).getCreate_openId();
										agentName = tmplist2.get(0).getAgent_name();
										//获取对应的openId
										ManageAdminUser manageAdminUser = new ManageAdminUser();
										manageAdminUser.setOneAgentOpenId(agentOpenId);
										manageAdminUser.setRoleId(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
										ManageAdminUser user = manageAdminUserDao.getManageAdminUser(manageAdminUser);
										if(user!=null){
											toOPENID = user.getOpenId();
										}
										//获取对应的openId
										ManageAdminUser manageAdminUser2 = new ManageAdminUser();
										manageAdminUser2.setTwoAgentOpenId(agentTwoOpenId);
										manageAdminUser2.setRoleId(Integer.valueOf(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID));
										ManageAdminUser user2 = manageAdminUserDao.getManageAdminUser(manageAdminUser2);
										if(user2!=null){
											toOPENID2 = user2.getOpenId();
											log.info("----------toOPENID2333333333------------------>"+toOPENID2);
										}
//									}
								}else{
									Map<String,String> defaultRecommend = getDefaultRecommend();
									agentOpenId = defaultRecommend.get("id");
									agentName = defaultRecommend.get("name");
									//记录数据库
									HRecRecord hRecRecord = new HRecRecord();
									hRecRecord.setRec_phone(recommend);
									hRecRecord.setAmmeter_no(ammeterNo);
									hRecRecord.setAmmeter_address(result.getJSONObject("resultInfo").getString("address"));
									hRecRecord.setAmmeter_name(result.getJSONObject("resultInfo").getString("accountName"));
									hRecRecord.setAmmeterinfo_type(1);
									hRecRecord.setContact_phone(hcompany.getContact_phone());
									hRecRecord.setCreate_time(new Date());
									hrecrecordService.insertHRecRecord(hRecRecord);
									//获取对应的openId
									ManageAdminUser manageAdminUser = new ManageAdminUser();
									manageAdminUser.setOneAgentOpenId(agentOpenId);
									manageAdminUser.setRoleId(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
									ManageAdminUser user = manageAdminUserDao.getManageAdminUser(manageAdminUser);
									if(user!=null){
										toOPENID = user.getOpenId();
									}
								}
							}
						}else{
							//推荐人手机号码格式错误
							Map<String,String> defaultRecommend = getDefaultRecommend();
							agentOpenId = defaultRecommend.get("id");
							agentName = defaultRecommend.get("name");	
							//记录数据库
							HRecRecord hRecRecord = new HRecRecord();
							hRecRecord.setRec_phone(recommend);
							hRecRecord.setAmmeter_no(ammeterNo);
							hRecRecord.setAmmeter_address(result.getJSONObject("resultInfo").getString("address"));
							hRecRecord.setAmmeter_name(result.getJSONObject("resultInfo").getString("accountName"));
							hRecRecord.setAmmeterinfo_type(1);
							hRecRecord.setContact_phone(hcompany.getContact_phone());
							hRecRecord.setCreate_time(new Date());
							hrecrecordService.insertHRecRecord(hRecRecord);
						}
					}else if(!StringUtils.isNotBlank(refereeOpenId)&&!StringUtils.isNotBlank(recommend)){
						//未填写推荐人  取默认推荐人
						Map<String,String> defaultRecommend = getDefaultRecommend();
						agentOpenId = defaultRecommend.get("id");
						agentName = defaultRecommend.get("name");
						//获取对应的openId
						ManageAdminUser manageAdminUser = new ManageAdminUser();
						manageAdminUser.setOneAgentOpenId(agentOpenId);
						manageAdminUser.setRoleId(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
						ManageAdminUser user = manageAdminUserDao.getManageAdminUser(manageAdminUser);
						if(user!=null){
							toOPENID = user.getOpenId();
						}
					}else if(StringUtils.isNotBlank(refereeOpenId)){//存在微信推荐人openid，说明是扫码
						HAgent ag = new HAgent();
						ag.setOpenId(refereeOpenId);
						ag.setStatus(1);
						ag.setCheck_status(1);
						List<HAgent> tmplist = hagentservice.getHAgentBaseList(ag);
						if(tmplist!=null&&tmplist.size()>0){
							agentOpenId = tmplist.get(0).getOpenId();
							agentName = tmplist.get(0).getName();
							agentId = tmplist.get(0).getId();
							//获取对应的openId
							ManageAdminUser manageAdminUser = new ManageAdminUser();
							manageAdminUser.setOneAgentOpenId(agentOpenId);
							manageAdminUser.setRoleId(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
							ManageAdminUser user = manageAdminUserDao.getManageAdminUser(manageAdminUser);
							if(user!=null){
								toOPENID = user.getOpenId();
							}
						}else{
							//一级不存在 取二级
							HAgentTwo ag2 = new HAgentTwo();
							ag2.setOpenId(refereeOpenId);
							ag2.setStatus(1);
							ag2.setCheck_status(1);
							List<HAgentTwo> tmplist2 = hAgentTwoDao.getHAgentTwoBaseList(ag2);
							if(tmplist2!=null&&tmplist2.size()>0){
								agentTwo = tmplist2.get(0);
								agentTwoOpenId = tmplist2.get(0).getOpenId();
								agentTwoName = tmplist2.get(0).getName();
								reg_gl_id = tmplist2.get(0).getReg_gl_id();
								agentTwoId = tmplist2.get(0).getId();
								//取对应一级代理
//								HAgent tmp = new HAgent();
//								tmp.setId(tmplist2.get(0).getAgent_id());
//								tmp = hagentservice.getHAgent(tmp);
//								if(tmp!=null){
									agentOpenId = tmplist2.get(0).getCreate_openId();
									agentName = tmplist2.get(0).getAgent_name();
									//获取对应的openId
									ManageAdminUser manageAdminUser = new ManageAdminUser();
									manageAdminUser.setOneAgentOpenId(agentOpenId);
									manageAdminUser.setRoleId(Integer.valueOf(FileUploadConstants.ONE_AGENT_MANAGE_ROLEID));
									ManageAdminUser user = manageAdminUserDao.getManageAdminUser(manageAdminUser);
									if(user!=null){
										toOPENID = user.getOpenId();
									}
									
									//获取对应的openId
									ManageAdminUser manageAdminUser2 = new ManageAdminUser();
									manageAdminUser2.setTwoAgentOpenId(agentTwoOpenId);
									manageAdminUser2.setRoleId(Integer.valueOf(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID));
									ManageAdminUser user2 = manageAdminUserDao.getManageAdminUser(manageAdminUser2);
									if(user2!=null){
										toOPENID2 = user2.getOpenId();
										log.info("----------toOPENID2333333333------------------>"+toOPENID2);
									}
									
//								}
							}
						}
					}
					//分配服务人员
					String contextPath = request.getSession().getServletContext().getRealPath("/");
					ManageAdminUser tmpServicer = getDefaultServicer(contextPath);
					if(tmpServicer!=null){
						servicerId = tmpServicer.getAdminId();
						servicerName = tmpServicer.getRealName();
						toOPENID3 = tmpServicer.getOpenId();
					}
					hcompany.setOneAgentOpenId(agentOpenId);
					hcompany.setTwoAgentOpenID(agentTwoOpenId);
					hcompany.setServicerId(servicerId);
					hcompany.setOneAgentName(agentName);
					hcompany.setTwoAgentName(agentTwoName);
					hcompany.setServicerName(servicerName);
					//生成单位
					int companyId = hcompanyService.insertHCompany(hcompany);
					if(companyId>0){
						if(countss==0&&StringUtils.isNotBlank(agentTwoOpenId)&&reg_gl_id!=null){//发放鼓励金
							//查询代理是否绑定鼓励金
							HRegGuli hRegGuli = new HRegGuli();
							hRegGuli.setId(reg_gl_id);
							hRegGuli.setState(1);
							hRegGuli = hRegGuliDAO.getHRegGuli(hRegGuli);
							if(hRegGuli!=null&&hRegGuli.getId()>0){
								if(hRegGuli.getStartTime().before(new Date())&&hRegGuli.getEndTime().after(new Date())){
									HRegGuliSend hRegGuliSend = new HRegGuliSend();
									hRegGuliSend.setSendTime(new Date());
									hRegGuliSend.setAgentId(agentId);
									hRegGuliSend.setAgentName(agentName);
									hRegGuliSend.setAgentTwoId(agentTwoId);
									hRegGuliSend.setAgentTwoName(agentTwoName);
									hRegGuliSend.setAmmeter(ammeterNo);
									hRegGuliSend.setCompanyId(companyId);
									hRegGuliSend.setCompanyName(hcompany.getName());
									hRegGuliSend.setCreateTime(new Date());
									hRegGuliSend.setState(1);
									hRegGuliSend.setGuliId(hRegGuli.getId());
									hRegGuliSend.setFee(hRegGuli.getFee());
									sendGLId = hRegGuliSendDAO.insertHRegGuliSend(hRegGuliSend);
									huseraccountdetailService.udpateAccount(agentTwo, hRegGuli.getFee(), ammeterNo,sendGLId);
									//发送模板
									huseraccountService.sendRegGLTempltMsg(toOPENID2, ammeterNo, hRegGuli.getFee());
								}
							}
						}
						if(countss>0){
							huseraccountService.sendRegGLTempltMsg1(toOPENID2, ammeterNo, null);
							HRegGuliSend hRegGuliSend = new HRegGuliSend();
							hRegGuliSend.setSendTime(new Date());
							hRegGuliSend.setAgentId(agentId);
							hRegGuliSend.setAgentName(agentName);
							hRegGuliSend.setAgentTwoId(agentTwoId);
							hRegGuliSend.setAgentTwoName(agentTwoName);
							hRegGuliSend.setAmmeter(ammeterNo);
							hRegGuliSend.setCompanyId(companyId);
							hRegGuliSend.setCompanyName(hcompany.getName());
							hRegGuliSend.setCreateTime(new Date());
							hRegGuliSend.setState(1);
							hRegGuliSend.setFee(0.0);
							hRegGuliSend.setInfo("缴费号已经被注册过");
							sendGLId = hRegGuliSendDAO.insertHRegGuliSend(hRegGuliSend);
						}
							
						//发送信息
						huseraccountService.sendZCTempltMsg(toOPENID, hcompany.getContact_name(), hcompany.getContact_phone(), ammeterNo, companyId);
						//发送信息
						huseraccountService.sendZCTempltMsg(toOPENID3, hcompany.getContact_name(), hcompany.getContact_phone(), ammeterNo, companyId);
						//生成分组(子单位)
						HSubCompany sub = new HSubCompany();
						sub.setC_id(companyId);
						sub.setCreate_time(new Date());
//						sub.setSub_name(result.getJSONObject("resultInfo").getString("accountName"));
						int subId = hsubcompanyservice.insertHSubCompany(sub);
						if(subId>0){
							hAmmeterInfo1.setC_id(companyId);
							hAmmeterInfo1.setAmmeter_type("A");
							hAmmeterInfo1.setLast_pay_day(7);
							hAmmeterInfo1.setPay_status("1");
							hAmmeterInfo1.setS_id(subId);
							hAmmeterInfo1.setOperator_id(companyId);
							//获取默认的分润比例
							HProfitRatio hProfitRatio = new HProfitRatio();
							hProfitRatio.setIs_default(1);
							hProfitRatio.setState(1);
							hProfitRatio = hprofitratioService.getHProfitRatio(hProfitRatio);
							if(hProfitRatio!=null&&hProfitRatio.getId()>0){
								hAmmeterInfo1.setProfit_id(hProfitRatio.getId());
								hAmmeterInfo1.setProfit_name(hProfitRatio.getName());
								hAmmeterInfo1.setProfit_one(hProfitRatio.getOnt_agent_ratio());
								hAmmeterInfo1.setProfit_two(hProfitRatio.getTwo_agent_ratio());
								hAmmeterInfo1.setProfit_servicer(hProfitRatio.getPersonal_ratio());
							}
							int ammNo= hAmmeterInfoService.insertHAmmeterInfo(hAmmeterInfo1);
							//模拟审核 生成用户
							hcompany.setVerify_status(1);
							hcompanyService.updateHCompany(hcompany);
							boolean b = true;
							//判断是否已经生成了账号和密码
							String username = hcompany.getContact_phone();
							ManageAdminUser user1 = new ManageAdminUser();
							user1.setAdminName(username);
							int count1 = manageAdminUserDao.getManageAdminUserListCount(user1);
							if(count1>0){
								b = false;
							}
							
							//生成账号密码
							String password = "";
							if(StringUtils.isNotBlank(hcompany.getContact_phone())){
								String tmpStr = hcompany.getContact_phone();
								password = tmpStr.substring(tmpStr.length()-6,tmpStr.length());
							}else{
								StringBuilder str=new StringBuilder();//定义变长字符串
								Random random=new Random();
								for(int i=0;i<8;i++){
									str.append(random.nextInt(10));
								}
								password = str.toString();
							}
							
							ManageAdminUser user = new ManageAdminUser();
							user.setAdminName(username);
							user.setPasswd(new MD5().getMD5ofStr(password));
//							user.setNickName(hcompany.getName());
//							user.setRealName(hcompany.getName());
							user.setMobile(hcompany.getContact_phone());
							user.setCreateTime(new Date());
							user.setCreaterId(-2);//注册
							user.setState(1);
							user.setCompanyId(companyId);
							user.setOneAgentOpenId(agentOpenId);
							user.setTwoAgentOpenId(agentTwoOpenId);
							user.setServicerId(servicerId);
							Integer userId = manageAdminUserDao.insertManageAdminUser(user);
							//更新服务人员绑定人数
							if(servicerId!=null){
								updateDefaultService(servicerId);
							}
							AdminUserRole adminUserRole = new AdminUserRole();
							adminUserRole.setAdminId(userId);
							adminUserRole.setRoleId(Integer.valueOf(FileUploadConstants.COMPANY_ROLE_ID));
							adminUserRoleDao.insertAdminUserRole(adminUserRole);
							hcompany.setUser_id(userId);
							hcompanyService.updateHCompany(hcompany);
//							String phone =  hcompany.getContact_phone();
//							String content = "贵单位的“电费短信通知服务”已开通。登录http://qiye.chinaepay.com既可以查询电费，也可以交纳电费，并享有送票上门服务。登录账号："+username+"密码："+password+"详情可致电96199咨询";
//							SendMsgUtil.sendMsg(phone,content);
							return 1;
						}else{
							return -4;
						}
					}else{
						return -2;
					}
				}
			}else{
				return -3;
			}
		}else{
			return 0;
		}
	}
	/**
	 * 后台添加
	 * @throws Exception 
	 */
	public int saveCompanyForManage(HttpServletRequest request,HCompanyService hcompanyService,HCompany hcompany,HAmmeterInfoService hAmmeterInfoService) throws Exception{
		//处理代理绑定
		String agentOneOpenId = RequestHandler.getString(request, "agentOneOpenId");
		String agentOneName = RequestHandler.getString(request, "agentOneName");
		String agentTwoOpenId = RequestHandler.getString(request, "agentTwoOpenId");
		String agentTwoName = RequestHandler.getString(request, "agentTwoName");
		Integer servicerId = RequestHandler.getInteger(request, "servicerId");
		String servicerName = RequestHandler.getString(request, "servicerName");
		//取默认代理、服务人员
		if(StringUtils.isBlank(agentOneOpenId)){
			Map<String,String> tmpmap = hcompanyService.getDefaultRecommend();
			agentOneOpenId = tmpmap.get("id");
			agentOneName = tmpmap.get("name");
		}
		String contextPath = request.getSession().getServletContext().getRealPath("/");
		if(servicerId==null){
			ManageAdminUser tmpServicer = hcompanyService.getDefaultServicer(contextPath);
			if(tmpServicer!=null){
				servicerId = tmpServicer.getAdminId();
				servicerName = tmpServicer.getRealName();
			}
		}
		hcompany.setOneAgentOpenId(agentOneOpenId);
		hcompany.setTwoAgentOpenID(agentTwoOpenId);
		hcompany.setServicerId(servicerId);
		hcompany.setOneAgentName(agentOneName);
		hcompany.setTwoAgentName(agentTwoName);
		hcompany.setServicerName(servicerName);
		//生成单位
		int companyId = hcompanyService.insertHCompany(hcompany);
		if(companyId>0){
			//模拟审核 生成用户
			hcompany.setVerify_status(1);
			hcompanyService.updateHCompany(hcompany);
			boolean b = true;
			//判断是否已经生成了账号和密码
			String username = hcompany.getContact_phone();
			ManageAdminUser user1 = new ManageAdminUser();
			user1.setAdminName(username);
			int count1 = manageAdminUserDao.getManageAdminUserListCount(user1);
			if(count1>0){
				b = false;
			}
			
			//生成账号密码
			String password = "";
			if(StringUtils.isNotBlank(hcompany.getContact_phone())){
				String tmpStr = hcompany.getContact_phone();
				password = tmpStr.substring(tmpStr.length()-6,tmpStr.length());
			}else{
				StringBuilder str=new StringBuilder();//定义变长字符串
				Random random=new Random();
				for(int i=0;i<8;i++){
					str.append(random.nextInt(10));
				}
				password = str.toString();
			}
			
			ManageAdminUser user = new ManageAdminUser();
			user.setAdminName(username);
			user.setPasswd(new MD5().getMD5ofStr(password));
			user.setNickName(hcompany.getName());
			user.setRealName(hcompany.getName());
			user.setMobile(hcompany.getContact_phone());
			user.setCreateTime(new Date());
			user.setCreaterId(-2);//注册
			user.setState(1);
			user.setCompanyId(companyId);
			user.setOneAgentOpenId(agentOneOpenId);
			user.setTwoAgentOpenId(agentTwoOpenId);
			user.setServicerId(servicerId);
			Integer userId = manageAdminUserDao.insertManageAdminUser(user);
			//更新服务人员绑定人数
			if(servicerId!=null){
				updateDefaultService(servicerId);
			}
			AdminUserRole adminUserRole = new AdminUserRole();
			adminUserRole.setAdminId(userId);
			adminUserRole.setRoleId(Integer.valueOf(FileUploadConstants.COMPANY_ROLE_ID));
			adminUserRoleDao.insertAdminUserRole(adminUserRole);
			hcompany.setUser_id(userId);
			hcompanyService.updateHCompany(hcompany);
			String phone =  hcompany.getContact_phone();
			String content = "贵单位的“电费短信通知服务”已开通。登录http://qiye.chinaepay.com既可以查询电费，也可以交纳电费，并享有送票上门服务。登录账号："+username+"密码："+password+"详情可致电96199咨询";
			int s = SendMsgUtil.sendMsg(phone,content);
//			if(s>0){
//				HMessageLog hMessageLog = new HMessageLog();
//				hMessageLog.setContent(content);
//				hMessageLog.setCreateTime(new Date());
//				hMessageLog.setPhone(phone);
//				hMessageLog.setIp(this.getIpAddr(request));
//				hmessagelogService.insertHMessageLog(hMessageLog);
//			}
			return 1;
		}else{
			return -2;
		}
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
	public Map<String,String> getDefaultRecommend(){
		Map<String,String> map = new HashMap<String,String>();
		HAgent ag = new HAgent();
		ag.setRemark3("1");
		ag.setStatus(1);
		ag.setCheck_status(1);
		List<HAgent> tmplist = hagentdao.getHAgentBaseList(ag);
		if(tmplist!=null&&tmplist.size()>0){
			map.put("id", tmplist.get(0).getOpenId());
			map.put("name", tmplist.get(0).getName());
		}
		return map;
	}
//	public ManageAdminUser getDefaultServicer(){
//		ManageAdminUser user = new ManageAdminUser();
//		user.setRoleId(Integer.valueOf(FileUploadConstants.SERVICER_MANAGE_ROLEID));
//		user = manageAdminUserDao.getDefaultServicer(user);
//		if(user!=null){
//			return user;
//		}else{
//			return null;
//		}
//	}
	public ManageAdminUser getDefaultServicer(String url){
		try {
			//读取文件
			List<String> listServiceId = this.readServiceFile(url);
			Integer serviceId = null;
			if(listServiceId!=null&&listServiceId.size()>0){
				serviceId = Integer.valueOf(listServiceId.get(0));
			}
			ManageAdminUser user = new ManageAdminUser();
			user.setRoleId(Integer.valueOf(FileUploadConstants.SERVICER_MANAGE_ROLEID));
			user.setState(1);
			if(serviceId!=null){
				user.setServicerId(serviceId);
				List<ManageAdminUser> list = manageAdminUserDao.getServiceUser(user);
				if(list!=null&&list.size()>0&&list.get(0).getAdminId()!=null){
					Integer adminId = list.get(0).getAdminId();
					this.writeServiceFile(adminId+"", url);
					return manageAdminUserDao.getAdminUserByID(adminId);
				}else{
					user.setServicerId(null);
					List<ManageAdminUser> list1 = manageAdminUserDao.getServiceUser(user);
					if(list1!=null&&list1.size()>0&&list1.get(0).getAdminId()!=null){
						Integer adminId = list1.get(0).getAdminId();
						this.writeServiceFile(adminId+"", url);
						return manageAdminUserDao.getAdminUserByID(adminId);
					}else{
						return null;
					}
				}
			}else{
				List<ManageAdminUser> list = manageAdminUserDao.getServiceUser(user);
				if(list!=null&&list.size()>0&&list.get(0).getAdminId()!=null){
					Integer adminId = list.get(0).getAdminId();
					this.writeServiceFile(adminId+"", url);
					return manageAdminUserDao.getAdminUserByID(adminId);
				}else{
					return null;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void updateDefaultService(Integer adminId){
		ManageAdminUser manageAdminUser = new ManageAdminUser();
		manageAdminUser.setAdminId(adminId);
		manageAdminUserDao.updateDefaultService(manageAdminUser);
	}
	
	public List<String> readServiceFile(String url) throws Exception {
		List<String> list = new ArrayList<String>();
		try {
			String encoding = "utf-8"; // 字符编码(可解决中文乱码问题 )
			File file = new File(url+"/upload/service.txt");
			if (file.isFile() && file.exists()) {
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTXT = null;
				while ((lineTXT = bufferedReader.readLine()) != null) {
					list.add(lineTXT.toString().trim());
				}
				bufferedReader.close();
				read.close();
			} else {
				System.out.println("找不到指定的文件！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	

	public void writeServiceFile(String str,String url) {
		System.out.println("--------开始--------");
		File file = new File(url+"/upload/service.txt");
		try {
			BufferedWriter output = new BufferedWriter(new FileWriter(file));
			output.write(str);
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("--------结束 --------");
	}
}
