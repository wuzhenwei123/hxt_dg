package com.hxt.hProxySendLog.dao;

import java.util.List;

import com.hxt.hProxySendLog.model.HProxySendLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	wuzhenwei
 * @time	2016年11月17日 07:55:46
 */
 @Repository("hProxySendLogDao")
public class HProxySendLogDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HProxySendLog> getHProxySendLogList(HProxySendLog hProxySendLog) {
		List<HProxySendLog> list = sqlMapClient.queryForList("HProxySendLog.getHProxySendLogList", hProxySendLog);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HProxySendLog> getHProxySendLogBaseList(HProxySendLog hProxySendLog) {
		return sqlMapClient.queryForList("HProxySendLog.getHProxySendLog", hProxySendLog);
	}

	public int getHProxySendLogListCount(HProxySendLog hProxySendLog) {
		return (Integer)sqlMapClient.queryForObject("HProxySendLog.getHProxySendLogListCount", hProxySendLog);
	}
	
	public HProxySendLog getHProxySendLog(HProxySendLog hProxySendLog) {
		return (HProxySendLog)sqlMapClient.queryForObject("HProxySendLog.getHProxySendLog", hProxySendLog);
	}

    public int insertHProxySendLog(HProxySendLog hProxySendLog) throws Exception {
        return (Integer)sqlMapClient.insert("HProxySendLog.insertHProxySendLog", hProxySendLog);
    }

    public int updateHProxySendLog(HProxySendLog hProxySendLog) throws Exception {
        return sqlMapClient.update("HProxySendLog.updateHProxySendLog", hProxySendLog);
    }
    
    public int removeHProxySendLog(HProxySendLog hProxySendLog) throws Exception {
        return sqlMapClient.delete("HProxySendLog.removeHProxySendLog", hProxySendLog);
    }

}
