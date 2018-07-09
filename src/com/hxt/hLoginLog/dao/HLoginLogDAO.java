package com.hxt.hLoginLog.dao;

import java.util.List;

import com.hxt.hLoginLog.model.HLoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	wuzhenwei
 * @time	2016年09月19日 11:08:42
 */
 @Repository("hLoginLogDao")
public class HLoginLogDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HLoginLog> getHLoginLogList(HLoginLog hLoginLog) {
		List<HLoginLog> list = sqlMapClient.queryForList("HLoginLog.getHLoginLogList", hLoginLog);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HLoginLog> getHLoginLogBaseList(HLoginLog hLoginLog) {
		return sqlMapClient.queryForList("HLoginLog.getHLoginLog", hLoginLog);
	}

	public int getHLoginLogListCount(HLoginLog hLoginLog) {
		return (Integer)sqlMapClient.queryForObject("HLoginLog.getHLoginLogListCount", hLoginLog);
	}
	
	public HLoginLog getHLoginLog(HLoginLog hLoginLog) {
		return (HLoginLog)sqlMapClient.queryForObject("HLoginLog.getHLoginLog", hLoginLog);
	}

    public int insertHLoginLog(HLoginLog hLoginLog) throws Exception {
        return (Integer)sqlMapClient.insert("HLoginLog.insertHLoginLog", hLoginLog);
    }

    public int updateHLoginLog(HLoginLog hLoginLog) throws Exception {
        return sqlMapClient.update("HLoginLog.updateHLoginLog", hLoginLog);
    }
    
    public int removeHLoginLog(HLoginLog hLoginLog) throws Exception {
        return sqlMapClient.delete("HLoginLog.removeHLoginLog", hLoginLog);
    }

}
