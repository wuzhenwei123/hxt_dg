package com.hxt.hPayGuli.dao;

import java.util.List;

import com.hxt.hPayGuli.model.HPayGuli;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	wuzhenwei
 * @time	2017年04月08日 17:00:49
 */
 @Repository("hPayGuliDao")
public class HPayGuliDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HPayGuli> getHPayGuliList(HPayGuli hPayGuli) {
		List<HPayGuli> list = sqlMapClient.queryForList("HPayGuli.getHPayGuliList", hPayGuli);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HPayGuli> getHPayGuliBaseList(HPayGuli hPayGuli) {
		return sqlMapClient.queryForList("HPayGuli.getHPayGuli", hPayGuli);
	}

	public int getHPayGuliListCount(HPayGuli hPayGuli) {
		return (Integer)sqlMapClient.queryForObject("HPayGuli.getHPayGuliListCount", hPayGuli);
	}
	
	public int checkDefault(HPayGuli hPayGuli) {
		return (Integer)sqlMapClient.queryForObject("HPayGuli.checkDefault", hPayGuli);
	}
	
	public HPayGuli getHPayGuli(HPayGuli hPayGuli) {
		return (HPayGuli)sqlMapClient.queryForObject("HPayGuli.getHPayGuli", hPayGuli);
	}

    public int insertHPayGuli(HPayGuli hPayGuli) throws Exception {
        return (Integer)sqlMapClient.insert("HPayGuli.insertHPayGuli", hPayGuli);
    }

    public int updateHPayGuli(HPayGuli hPayGuli) throws Exception {
        return sqlMapClient.update("HPayGuli.updateHPayGuli", hPayGuli);
    }
    
    public int updateHPayGuli1(HPayGuli hPayGuli) throws Exception {
    	return sqlMapClient.update("HPayGuli.updateHPayGuli1", hPayGuli);
    }
    
    public int removeHPayGuli(HPayGuli hPayGuli) throws Exception {
        return sqlMapClient.delete("HPayGuli.removeHPayGuli", hPayGuli);
    }

}
