package com.hxt.hUserAccountDetail.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxt.hAgentTwo.model.HAgentTwo;
import com.hxt.hAmmeterInfo.model.HAmmeterInfo;
import com.hxt.hCompany.model.HCompany;
import com.hxt.hPayOrder.model.HPayOrder;
import com.hxt.hProfitRatio.model.HProfitRatio;
import com.hxt.hSubOrder.model.HSubOrder;
import com.hxt.hUserAccount.dao.HUserAccountDAO;
import com.hxt.hUserAccount.model.HUserAccount;
import com.hxt.hUserAccountDetail.dao.HUserAccountDetailDAO;
import com.hxt.hUserAccountDetail.model.HUserAccountDetail;
import com.sys.manageAdminUser.dao.ManageAdminUserDAO;
import com.sys.manageAdminUser.model.ManageAdminUser;
import com.base.utils.FileUploadConstants;
import com.base.utils.ResponseList;

/**
 * @author	zlb
 * @time	2016年08月16日 10:58:43
 */
 @Service("hUserAccountDetailService")
public class HUserAccountDetailService {

	@Resource(name = "hUserAccountDetailDao")
    private HUserAccountDetailDAO hUserAccountDetailDAO;
	@Resource(name = "hUserAccountDao")
    private HUserAccountDAO hUserAccountDAO;
	@Resource(name = "manageAdminUserDao")
    private ManageAdminUserDAO manageAdminUserDAO;
    
    public ResponseList<HUserAccountDetail> getHUserAccountDetailList(HUserAccountDetail hUserAccountDetail) {
        return hUserAccountDetailDAO.getHUserAccountDetailList(hUserAccountDetail);
    }
    public List<HUserAccountDetail> getHUserAccountDetailListWx(HUserAccountDetail hUserAccountDetail) {
    	return hUserAccountDetailDAO.getHUserAccountDetailListWx(hUserAccountDetail);
    }
    
    public List<HUserAccountDetail> getHUserAccountDetailBaseList(HUserAccountDetail hUserAccountDetail) {
        return hUserAccountDetailDAO.getHUserAccountDetailBaseList(hUserAccountDetail);
    }
    
    public int getHUserAccountDetailListCount(HUserAccountDetail hUserAccountDetail) {
        return hUserAccountDetailDAO.getHUserAccountDetailListCount(hUserAccountDetail);
    }

    public HUserAccountDetail getHUserAccountDetail(HUserAccountDetail hUserAccountDetail) { 
        return hUserAccountDetailDAO.getHUserAccountDetail(hUserAccountDetail);
    }

    public int insertHUserAccountDetail(HUserAccountDetail hUserAccountDetail) throws Exception {
        return hUserAccountDetailDAO.insertHUserAccountDetail(hUserAccountDetail);
    }

    public int updateHUserAccountDetail(HUserAccountDetail hUserAccountDetail) throws Exception {
        return hUserAccountDetailDAO.updateHUserAccountDetail(hUserAccountDetail);
    }
    
    public int removeHUserAccountDetail(HUserAccountDetail hUserAccountDetail) throws Exception {
        return hUserAccountDetailDAO.removeHUserAccountDetail(hUserAccountDetail);
    }

	public HUserAccountDetail getAllDetailFee(HUserAccountDetail param) {
		return hUserAccountDetailDAO.getAllDetailFee(param);
	}
	public HUserAccountDetail getAllDetailFee1(HUserAccountDetail param) {
		return hUserAccountDetailDAO.getAllDetailFee1(param);
	}
    
	public HUserAccountDetail getAllRateMoney(HUserAccountDetail param) {
		return hUserAccountDetailDAO.getAllRateMoney(param);
	}
	
	public int udpateAccount(HAgentTwo agentTwo,Double fee,String electric,Integer sendGlId) throws Exception{
		
		try{
			//获取对应的openId
			ManageAdminUser manageAdminUser = new ManageAdminUser();
			manageAdminUser.setTwoAgentOpenId(agentTwo.getOpenId());
			manageAdminUser.setRoleId(Integer.valueOf(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID));
			ManageAdminUser user = manageAdminUserDAO.getManageAdminUser(manageAdminUser);
			
			HUserAccount agentAccount = new HUserAccount();
			agentAccount.setTwoAgentOpenId(agentTwo.getOpenId());
			agentAccount.setRole_id(Integer.valueOf(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID));
			agentAccount = hUserAccountDAO.getHUserAccount(agentAccount);
			if(agentAccount==null){
				//创建账户
				agentAccount = new HUserAccount();
				if(user!=null){
					agentAccount.setOpenId(user.getOpenId());
				}
				agentAccount.setOneAgentOpenId(agentTwo.getCreate_openId());
				agentAccount.setOneAgentName(user.getOneAgentName());
				agentAccount.setTotalFee(new BigDecimal("0.00"));
				agentAccount.setCreateTime(new Date());
				agentAccount.setStatus(1);
				agentAccount.setRemark1("2");
				agentAccount.setTwoAgentOpenId(agentTwo.getOpenId());
				agentAccount.setTwoAgentName(agentTwo.getName());
				agentAccount.setNickName(agentTwo.getName());
				agentAccount.setPhone(agentTwo.getMobile1());
				agentAccount.setMobile(agentTwo.getMobile2());
				agentAccount.setRole_id(Integer.valueOf(FileUploadConstants.TWO_AGENT_MANAGE_ROLEID));
				hUserAccountDAO.insertHUserAccount(agentAccount);
			}
			
			//金额
			BigDecimal totalFee = new BigDecimal(fee);
			//账户明细
			HUserAccountDetail detail = new HUserAccountDetail();
			detail.setUserAccountId(agentAccount.getId());
			detail.setType(3);
			detail.setTotalFee(totalFee);
			detail.setCreateTime(new Date());
			detail.setRate(null);
			detail.setOrderId(null);
			detail.setAmmeterNum(electric);
//			detail.setOrderId(sendGlId);
			hUserAccountDetailDAO.insertHUserAccountDetail(detail);
			//更新账户
			agentAccount.setTotalFee(agentAccount.getTotalFee().add(totalFee));
		    
			hUserAccountDAO.updateHUserAccount(agentAccount);
			return 1;
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
}
