package com.hxt.hRegGuli.dao;

import java.util.List;

import com.hxt.hRegGuli.model.HRegGuli;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	wuzhenwei
 * @time	2017年04月06日 17:50:28
 */
 @Repository("hRegGuliDao")
public class HRegGuliDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HRegGuli> getHRegGuliList(HRegGuli hRegGuli) {
		List<HRegGuli> list = sqlMapClient.queryForList("HRegGuli.getHRegGuliList", hRegGuli);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HRegGuli> getHRegGuliBaseList(HRegGuli hRegGuli) {
		return sqlMapClient.queryForList("HRegGuli.getHRegGuli", hRegGuli);
	}

	public int getHRegGuliListCount(HRegGuli hRegGuli) {
		return (Integer)sqlMapClient.queryForObject("HRegGuli.getHRegGuliListCount", hRegGuli);
	}
	
	public int checkDefault(HRegGuli hRegGuli) {
		return (Integer)sqlMapClient.queryForObject("HRegGuli.checkDefault", hRegGuli);
	}
	
	public HRegGuli getHRegGuli(HRegGuli hRegGuli) {
		return (HRegGuli)sqlMapClient.queryForObject("HRegGuli.getHRegGuli", hRegGuli);
	}

    public int insertHRegGuli(HRegGuli hRegGuli) throws Exception {
        return (Integer)sqlMapClient.insert("HRegGuli.insertHRegGuli", hRegGuli);
    }

    public int updateHRegGuli(HRegGuli hRegGuli) throws Exception {
        return sqlMapClient.update("HRegGuli.updateHRegGuli", hRegGuli);
    }
    
    public int removeHRegGuli(HRegGuli hRegGuli) throws Exception {
        return sqlMapClient.delete("HRegGuli.removeHRegGuli", hRegGuli);
    }

}
