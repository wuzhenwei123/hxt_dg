package com.hxt.hAmmeterQueryLog.dao;

import java.util.List;

import com.hxt.hAmmeterQueryLog.model.HAmmeterQueryLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	wuzhenwei
 * @time	2016年09月20日 12:15:05
 */
 @Repository("hAmmeterQueryLogDao")
public class HAmmeterQueryLogDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HAmmeterQueryLog> getHAmmeterQueryLogList(HAmmeterQueryLog hAmmeterQueryLog) {
		List<HAmmeterQueryLog> list = sqlMapClient.queryForList("HAmmeterQueryLog.getHAmmeterQueryLogList", hAmmeterQueryLog);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HAmmeterQueryLog> getHAmmeterQueryLogBaseList(HAmmeterQueryLog hAmmeterQueryLog) {
		return sqlMapClient.queryForList("HAmmeterQueryLog.getHAmmeterQueryLog", hAmmeterQueryLog);
	}

	public int getHAmmeterQueryLogListCount(HAmmeterQueryLog hAmmeterQueryLog) {
		return (Integer)sqlMapClient.queryForObject("HAmmeterQueryLog.getHAmmeterQueryLogListCount", hAmmeterQueryLog);
	}
	
	public HAmmeterQueryLog getHAmmeterQueryLog(HAmmeterQueryLog hAmmeterQueryLog) {
		return (HAmmeterQueryLog)sqlMapClient.queryForObject("HAmmeterQueryLog.getHAmmeterQueryLog", hAmmeterQueryLog);
	}

    public int insertHAmmeterQueryLog(HAmmeterQueryLog hAmmeterQueryLog) throws Exception {
        return (Integer)sqlMapClient.insert("HAmmeterQueryLog.insertHAmmeterQueryLog", hAmmeterQueryLog);
    }

    public int updateHAmmeterQueryLog(HAmmeterQueryLog hAmmeterQueryLog) throws Exception {
        return sqlMapClient.update("HAmmeterQueryLog.updateHAmmeterQueryLog", hAmmeterQueryLog);
    }
    
    public int removeHAmmeterQueryLog(HAmmeterQueryLog hAmmeterQueryLog) throws Exception {
        return sqlMapClient.delete("HAmmeterQueryLog.removeHAmmeterQueryLog", hAmmeterQueryLog);
    }

}
