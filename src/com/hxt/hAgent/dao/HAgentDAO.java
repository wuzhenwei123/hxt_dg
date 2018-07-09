package com.hxt.hAgent.dao;

import java.util.List;

import com.hxt.hAgent.model.HAgent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	zlb
 * @time	2016年08月07日 21:47:53
 */
 @Repository("hAgentDao")
public class HAgentDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HAgent> getHAgentList(HAgent hAgent) {
		List<HAgent> list = sqlMapClient.queryForList("HAgent.getHAgentList", hAgent);
		return buildResponseList(list);
	}
	@SuppressWarnings("unchecked")
	public ResponseList<HAgent> getAgentPP(HAgent hAgent) {
		List<HAgent> list = sqlMapClient.queryForList("HAgent.getAgentPP", hAgent);
		return buildResponseList(list);
	}
	public int checkMobile1(HAgent hAgent){
		return (Integer)sqlMapClient.queryForObject("HAgent.checkMobile1", hAgent);
	}
	@SuppressWarnings("unchecked")
	public List<HAgent> getHAgentBaseList(HAgent hAgent) {
		return sqlMapClient.queryForList("HAgent.getHAgent", hAgent);
	}

	public int getHAgentListCount(HAgent hAgent) {
		return (Integer)sqlMapClient.queryForObject("HAgent.getHAgentListCount", hAgent);
	}
	
	public int getAgentPPCount(HAgent hAgent) {
		return (Integer)sqlMapClient.queryForObject("HAgent.getAgentPPCount", hAgent);
	}
	
	public int checkUseAgent(HAgent hAgent) {
		return (Integer)sqlMapClient.queryForObject("HAgent.checkUseAgent", hAgent);
	}
	
	public HAgent getHAgent(HAgent hAgent) {
		return (HAgent)sqlMapClient.queryForObject("HAgent.getHAgent", hAgent);
	}

    public int insertHAgent(HAgent hAgent) throws Exception {
        return (Integer)sqlMapClient.insert("HAgent.insertHAgent", hAgent);
    }

    public int updateHAgent(HAgent hAgent) throws Exception {
        return sqlMapClient.update("HAgent.updateHAgent", hAgent);
    }
    public int updateDefaultYLGl(HAgent hAgent) throws Exception {
    	return sqlMapClient.update("HAgent.updateDefaultYLGl", hAgent);
    }
    public int updateDefaultSJGl(HAgent hAgent) throws Exception {
    	return sqlMapClient.update("HAgent.updateDefaultSJGl", hAgent);
    }
    
    public int removeHAgent(HAgent hAgent) throws Exception {
        return sqlMapClient.delete("HAgent.removeHAgent", hAgent);
    }
	public int isDefault1() {
		 return sqlMapClient.update("HAgent.isDefault1", new HAgent());
	}
	public int isDefault2(HAgent hAgent) {
		 return sqlMapClient.update("HAgent.isDefault2", hAgent);
	}

}
