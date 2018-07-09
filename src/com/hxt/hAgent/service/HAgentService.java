package com.hxt.hAgent.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxt.hAgent.dao.HAgentDAO;
import com.hxt.hAgent.model.HAgent;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;

/**
 * @author	zlb
 * @time	2016年08月07日 21:47:54
 */
 @Service("hAgentService")
public class HAgentService {

	@Resource(name = "hAgentDao")
    private HAgentDAO hAgentDAO;
	public int checkMobile1(HAgent hAgent){
		return hAgentDAO.checkMobile1(hAgent);
	}
	/**
	 * 校验账号
	 * @param id
	 * @param user_name
	 * @return
	 */
	public boolean checkUserName(Integer id,String user_name){
		HAgent h = new HAgent();
		h.setUser_name(user_name);
		List<HAgent> agentList = hAgentDAO.getHAgentBaseList(h);
		
		if(agentList.size() > 0 ){
			HAgent agent = agentList.get(0);
			if(id == null ){//添加
				return false;
			}else{//更新
				if(agent.getId().equals(id)){//和自己用户相同没问题
					return true;
				}else{
					return false;
				}
			}
		}else{
			return true;
		}
	}
    public ResponseList<HAgent> getHAgentList(HAgent hAgent) {
        return hAgentDAO.getHAgentList(hAgent);
    }
    public ResponseList<HAgent> getAgentPP(HAgent hAgent) {
    	return hAgentDAO.getAgentPP(hAgent);
    }
    
    public List<HAgent> getHAgentBaseList(HAgent hAgent) {
        return hAgentDAO.getHAgentBaseList(hAgent);
    }
    
    public int getHAgentListCount(HAgent hAgent) {
        return hAgentDAO.getHAgentListCount(hAgent);
    }
    public int getAgentPPCount(HAgent hAgent) {
    	return hAgentDAO.getAgentPPCount(hAgent);
    }
    
    public int checkUseAgent(HAgent hAgent) {
    	return hAgentDAO.checkUseAgent(hAgent);
    }

    public HAgent getHAgent(HAgent hAgent) { 
        return hAgentDAO.getHAgent(hAgent);
    }

    public int insertHAgent(HAgent hAgent) throws Exception {
        return hAgentDAO.insertHAgent(hAgent);
    }

    public int updateHAgent(HAgent hAgent) throws Exception {
        return hAgentDAO.updateHAgent(hAgent);
    }
    public int updateDefaultYLGl(HAgent hAgent) throws Exception {
    	return hAgentDAO.updateDefaultYLGl(hAgent);
    }
    public int updateDefaultSJGl(HAgent hAgent) throws Exception {
    	return hAgentDAO.updateDefaultSJGl(hAgent);
    }
    
    public int removeHAgent(HAgent hAgent) throws Exception {
        return hAgentDAO.removeHAgent(hAgent);
    }
	public int isDefault1() {
		return hAgentDAO.isDefault1();
	}
	public int isDefault2(HAgent hAgent) {
		return hAgentDAO.isDefault2(hAgent);
	}
}
