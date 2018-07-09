package com.hxt.hProfitRatio.dao;

import java.util.List;

import com.hxt.hProfitRatio.model.HProfitRatio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	zlb
 * @time	2016年08月04日 21:43:45
 */
 @Repository("hProfitRatioDao")
public class HProfitRatioDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HProfitRatio> getHProfitRatioList(HProfitRatio hProfitRatio) {
		List<HProfitRatio> list = sqlMapClient.queryForList("HProfitRatio.getHProfitRatioList", hProfitRatio);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HProfitRatio> getHProfitRatioBaseList(HProfitRatio hProfitRatio) {
		return sqlMapClient.queryForList("HProfitRatio.getHProfitRatio", hProfitRatio);
	}

	public int getHProfitRatioListCount(HProfitRatio hProfitRatio) {
		return (Integer)sqlMapClient.queryForObject("HProfitRatio.getHProfitRatioListCount", hProfitRatio);
	}
	
	public HProfitRatio getHProfitRatio(HProfitRatio hProfitRatio) {
		return (HProfitRatio)sqlMapClient.queryForObject("HProfitRatio.getHProfitRatio", hProfitRatio);
	}

    public int insertHProfitRatio(HProfitRatio hProfitRatio) throws Exception {
        return (Integer)sqlMapClient.insert("HProfitRatio.insertHProfitRatio", hProfitRatio);
    }

    public int updateHProfitRatio(HProfitRatio hProfitRatio) throws Exception {
        return sqlMapClient.update("HProfitRatio.updateHProfitRatio", hProfitRatio);
    }
    public int updateAllDefaultRatio(HProfitRatio hProfitRatio) throws Exception {
    	return sqlMapClient.update("HProfitRatio.updateAllDefaultRatio", hProfitRatio);
    }
    
    public int removeHProfitRatio(HProfitRatio hProfitRatio) throws Exception {
        return sqlMapClient.delete("HProfitRatio.removeHProfitRatio", hProfitRatio);
    }

}
