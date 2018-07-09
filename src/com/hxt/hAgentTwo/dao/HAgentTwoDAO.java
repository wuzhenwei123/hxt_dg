package com.hxt.hAgentTwo.dao;

import java.util.List;

import com.hxt.hAgentTwo.model.HAgentTwo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	zlb
 * @time	2016年08月08日 21:39:33
 */
 @Repository("hAgentTwoDao")
public class HAgentTwoDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HAgentTwo> getHAgentTwoList(HAgentTwo hAgentTwo) {
		List<HAgentTwo> list = sqlMapClient.queryForList("HAgentTwo.getHAgentTwoList", hAgentTwo);
		return buildResponseList(list);
	}
	
	@SuppressWarnings("unchecked")
	public ResponseList<HAgentTwo> getHAgentTwoPP(HAgentTwo hAgentTwo) {
		List<HAgentTwo> list = sqlMapClient.queryForList("HAgentTwo.getHAgentTwoPP", hAgentTwo);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HAgentTwo> getHAgentTwoBaseList(HAgentTwo hAgentTwo) {
		return sqlMapClient.queryForList("HAgentTwo.getHAgentTwo", hAgentTwo);
	}

	public int getHAgentTwoListCount(HAgentTwo hAgentTwo) {
		return (Integer)sqlMapClient.queryForObject("HAgentTwo.getHAgentTwoListCount", hAgentTwo);
	}
	
	public int getHAgentTwoPPCount(HAgentTwo hAgentTwo) {
		return (Integer)sqlMapClient.queryForObject("HAgentTwo.getHAgentTwoPPCount", hAgentTwo);
	}
	
	public int checkCard(HAgentTwo hAgentTwo) {
		return (Integer)sqlMapClient.queryForObject("HAgentTwo.checkCard", hAgentTwo);
	}
	
	public int checkMobile(HAgentTwo hAgentTwo) {
		return (Integer)sqlMapClient.queryForObject("HAgentTwo.checkMobile", hAgentTwo);
	}
	
	public HAgentTwo getHAgentTwo(HAgentTwo hAgentTwo) {
		return (HAgentTwo)sqlMapClient.queryForObject("HAgentTwo.getHAgentTwo", hAgentTwo);
	}

    public int insertHAgentTwo(HAgentTwo hAgentTwo) throws Exception {
        return (Integer)sqlMapClient.insert("HAgentTwo.insertHAgentTwo", hAgentTwo);
    }

    public int updateHAgentTwo(HAgentTwo hAgentTwo) throws Exception {
        return sqlMapClient.update("HAgentTwo.updateHAgentTwo", hAgentTwo);
    }
    public int updateDefaultRegGl(HAgentTwo hAgentTwo) throws Exception {
    	return sqlMapClient.update("HAgentTwo.updateDefaultRegGl", hAgentTwo);
    }
    public int updateDefaultYLGl(HAgentTwo hAgentTwo) throws Exception {
    	return sqlMapClient.update("HAgentTwo.updateDefaultYLGl", hAgentTwo);
    }
    public int updateDefaultSJGl(HAgentTwo hAgentTwo) throws Exception {
    	return sqlMapClient.update("HAgentTwo.updateDefaultSJGl", hAgentTwo);
    }
	public int checkMobile1(HAgentTwo hAgentTwo){
		return (Integer)sqlMapClient.queryForObject("HAgentTwo.checkMobile1", hAgentTwo);
	}
    public int removeHAgentTwo(HAgentTwo hAgentTwo) throws Exception {
        return sqlMapClient.delete("HAgentTwo.removeHAgentTwo", hAgentTwo);
    }

}
