package com.hxt.hVerificate.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxt.hAgent.dao.HAgentDAO;
import com.hxt.hAgent.model.HAgent;
import com.hxt.hAgentTwo.dao.HAgentTwoDAO;
import com.hxt.hAgentTwo.model.HAgentTwo;
import com.hxt.hVerificate.dao.HVerificateDAO;
import com.hxt.hVerificate.model.HVerificate;
import com.sys.manageAdminUser.dao.ManageAdminUserDAO;
import com.sys.manageAdminUser.model.ManageAdminUser;
import com.base.utils.FileUploadConstants;
import com.base.utils.ResponseList;

/**
 * @author	wuzhenwei
 * @time	2015年09月30日 14:11:22
 */
 @Service("hVerificateService")
public class HVerificateService {

	@Resource(name = "hVerificateDao")
    private HVerificateDAO hVerificateDAO;
	@Resource(name = "hAgentDao")
    private HAgentDAO hAgentDAO;
	@Resource(name = "hAgentTwoDao")
    private HAgentTwoDAO hAgentTwoDAO;
	@Resource(name = "manageAdminUserDao")
    private ManageAdminUserDAO manageAdminUserDAO;
    
    public ResponseList<HVerificate> getHVerificateList(HVerificate hVerificate) {
        return hVerificateDAO.getHVerificateList(hVerificate);
    }
    
    public List<HVerificate> getHVerificateBaseList(HVerificate hVerificate) {
        return hVerificateDAO.getHVerificateBaseList(hVerificate);
    }
    
    public int getHVerificateListCount(HVerificate hVerificate) {
        return hVerificateDAO.getHVerificateListCount(hVerificate);
    }

    public HVerificate getHVerificate(HVerificate hVerificate) { 
        return hVerificateDAO.getHVerificate(hVerificate);
    }

    public int insertHVerificate(HVerificate hVerificate) throws Exception {
        return hVerificateDAO.insertHVerificate(hVerificate);
    }

    public int updateHVerificate(HVerificate hVerificate) throws Exception {
        return hVerificateDAO.updateHVerificate(hVerificate);
    }
    
    public int removeHVerificate(HVerificate hVerificate) throws Exception {
        return hVerificateDAO.removeHVerificate(hVerificate);
    }
    
    /**
     * 微信号是否验证
     * @param openId
     * @return -1 未验证/0所在机构被禁用/1正常/2所在机构被暂停
     */
    public int isVerficate(String openId){
    	int b = -1;
    	try{
    		HVerificate hVerificate = new HVerificate();
			hVerificate.setOpenId(openId);
			hVerificate.setState(1);
			HVerificate ver  = hVerificateDAO.getHVerificate(hVerificate);
			if(ver!=null&&ver.getId()>0){
				//判断机构状态
				if(ver.getLevel()==1){
					HAgent hAgent = new HAgent();
					hAgent.setOpenId(ver.getAgentOpenId());
					HAgent agent = hAgentDAO.getHAgent(hAgent);
					if(agent!=null){
						b = agent.getStatus();
					}
				}else if(ver.getLevel()==2){
					HAgentTwo hAgentTwo = new HAgentTwo();
					hAgentTwo.setOpenId(ver.getAgentOpenId());
					HAgentTwo agentTwo = hAgentTwoDAO.getHAgentTwo(hAgentTwo);
					if(agentTwo!=null){
						b = agentTwo.getStatus();
					}
				}else if(ver.getLevel()==3){
					ManageAdminUser manageAdminUser = new ManageAdminUser();
					manageAdminUser.setOpenId(openId);
					manageAdminUser.setRoleId(Integer.valueOf(FileUploadConstants.SERVICER_MANAGE_ROLEID));
					manageAdminUser = manageAdminUserDAO.getAdminUserByLogin(manageAdminUser);
					if(manageAdminUser!=null){
						b = manageAdminUser.getState();
					}
				}
			}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return b;
    }
    
}
