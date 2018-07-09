package com.hxt.hPayurlCheck.dao;

import java.util.List;

import com.hxt.hPayurlCheck.model.HPayurlCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	wuzhenwei
 * @time	2015年11月28日 22:43:25
 */
 @Repository("hPayurlCheckDao")
public class HPayurlCheckDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HPayurlCheck> getHPayurlCheckList(HPayurlCheck hPayurlCheck) {
		List<HPayurlCheck> list = sqlMapClient.queryForList("HPayurlCheck.getHPayurlCheckList", hPayurlCheck);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HPayurlCheck> getHPayurlCheckBaseList(HPayurlCheck hPayurlCheck) {
		return sqlMapClient.queryForList("HPayurlCheck.getHPayurlCheck", hPayurlCheck);
	}

	public int getHPayurlCheckListCount(HPayurlCheck hPayurlCheck) {
		return (Integer)sqlMapClient.queryForObject("HPayurlCheck.getHPayurlCheckListCount", hPayurlCheck);
	}
	
	public HPayurlCheck getHPayurlCheck(HPayurlCheck hPayurlCheck) {
		return (HPayurlCheck)sqlMapClient.queryForObject("HPayurlCheck.getHPayurlCheck", hPayurlCheck);
	}

    public int insertHPayurlCheck(HPayurlCheck hPayurlCheck) throws Exception {
        return (Integer)sqlMapClient.insert("HPayurlCheck.insertHPayurlCheck", hPayurlCheck);
    }

    public int updateHPayurlCheck(HPayurlCheck hPayurlCheck) throws Exception {
        return sqlMapClient.update("HPayurlCheck.updateHPayurlCheck", hPayurlCheck);
    }
    
    public int removeHPayurlCheck(HPayurlCheck hPayurlCheck) throws Exception {
        return sqlMapClient.delete("HPayurlCheck.removeHPayurlCheck", hPayurlCheck);
    }
	public HPayurlCheck findNowHPayurlCheck(HPayurlCheck hPayurlCheck) {
		return (HPayurlCheck)sqlMapClient.queryForObject("HPayurlCheck.findNowHPayurlCheck", hPayurlCheck);
	}

}
