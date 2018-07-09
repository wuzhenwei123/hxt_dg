package com.hxt.hAgentTwo.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxt.hAgent.model.HAgent;
import com.hxt.hAgentTwo.dao.HAgentTwoDAO;
import com.hxt.hAgentTwo.model.HAgentTwo;
import com.base.utils.ResponseList;

/**
 * @author	zlb
 * @time	2016年08月08日 21:39:35
 */
 @Service("hAgentTwoService")
public class HAgentTwoService {

	@Resource(name = "hAgentTwoDao")
    private HAgentTwoDAO hAgentTwoDAO;
	/**
	 * 校验账号
	 * @param id
	 * @param user_name
	 * @return
	 */
	public boolean checkUserName(Integer id,String user_name){
		HAgentTwo h = new HAgentTwo();
		h.setUser_name(user_name);
		List<HAgentTwo> agentList = hAgentTwoDAO.getHAgentTwoBaseList(h);
		
		if(agentList.size() > 0 ){
			HAgentTwo agent = agentList.get(0);
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
    public ResponseList<HAgentTwo> getHAgentTwoList(HAgentTwo hAgentTwo) {
        return hAgentTwoDAO.getHAgentTwoList(hAgentTwo);
    }
    public ResponseList<HAgentTwo> getHAgentTwoPP(HAgentTwo hAgentTwo) {
    	return hAgentTwoDAO.getHAgentTwoPP(hAgentTwo);
    }
    
    public List<HAgentTwo> getHAgentTwoBaseList(HAgentTwo hAgentTwo) {
        return hAgentTwoDAO.getHAgentTwoBaseList(hAgentTwo);
    }
    
    public int getHAgentTwoListCount(HAgentTwo hAgentTwo) {
        return hAgentTwoDAO.getHAgentTwoListCount(hAgentTwo);
    }
    
    public int getHAgentTwoPPCount(HAgentTwo hAgentTwo) {
    	return hAgentTwoDAO.getHAgentTwoPPCount(hAgentTwo);
    }

    public HAgentTwo getHAgentTwo(HAgentTwo hAgentTwo) { 
        return hAgentTwoDAO.getHAgentTwo(hAgentTwo);
    }

    public int insertHAgentTwo(HAgentTwo hAgentTwo) throws Exception {
        return hAgentTwoDAO.insertHAgentTwo(hAgentTwo);
    }

    public int updateHAgentTwo(HAgentTwo hAgentTwo) throws Exception {
        return hAgentTwoDAO.updateHAgentTwo(hAgentTwo);
    }
    
    public int updateDefaultRegGl(HAgentTwo hAgentTwo) throws Exception {
    	return hAgentTwoDAO.updateDefaultRegGl(hAgentTwo);
    }
    public int updateDefaultYLGl(HAgentTwo hAgentTwo) throws Exception {
    	return hAgentTwoDAO.updateDefaultYLGl(hAgentTwo);
    }
    public int updateDefaultSJGl(HAgentTwo hAgentTwo) throws Exception {
    	return hAgentTwoDAO.updateDefaultSJGl(hAgentTwo);
    }
    
    public int checkMobile1(HAgentTwo hAgentTwo){
    	return hAgentTwoDAO.checkMobile1(hAgentTwo);
    }
    public int removeHAgentTwo(HAgentTwo hAgentTwo) throws Exception {
        return hAgentTwoDAO.removeHAgentTwo(hAgentTwo);
    }
    
    public boolean checkCard(HAgentTwo hAgentTwo){
    	int count  = hAgentTwoDAO.checkCard(hAgentTwo);
    	if(count>0){
    		return false;
    	}else{
    		return true;
    	}
    }
    
}
