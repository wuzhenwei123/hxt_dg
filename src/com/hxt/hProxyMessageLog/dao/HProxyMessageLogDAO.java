package com.hxt.hProxyMessageLog.dao;

import java.util.List;

import com.hxt.hProxyMessageLog.model.HProxyMessageLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	wuzhenwei
 * @time	2016年11月03日 09:29:08
 */
 @Repository("hProxyMessageLogDao")
public class HProxyMessageLogDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HProxyMessageLog> getHProxyMessageLogList(HProxyMessageLog hProxyMessageLog) {
		List<HProxyMessageLog> list = sqlMapClient.queryForList("HProxyMessageLog.getHProxyMessageLogList", hProxyMessageLog);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HProxyMessageLog> getHProxyMessageLogBaseList(HProxyMessageLog hProxyMessageLog) {
		return sqlMapClient.queryForList("HProxyMessageLog.getHProxyMessageLog", hProxyMessageLog);
	}

	public int getHProxyMessageLogListCount(HProxyMessageLog hProxyMessageLog) {
		return (Integer)sqlMapClient.queryForObject("HProxyMessageLog.getHProxyMessageLogListCount", hProxyMessageLog);
	}
	
	public HProxyMessageLog getHProxyMessageLog(HProxyMessageLog hProxyMessageLog) {
		return (HProxyMessageLog)sqlMapClient.queryForObject("HProxyMessageLog.getHProxyMessageLog", hProxyMessageLog);
	}

    public int insertHProxyMessageLog(HProxyMessageLog hProxyMessageLog) throws Exception {
        return (Integer)sqlMapClient.insert("HProxyMessageLog.insertHProxyMessageLog", hProxyMessageLog);
    }

    public int updateHProxyMessageLog(HProxyMessageLog hProxyMessageLog) throws Exception {
        return sqlMapClient.update("HProxyMessageLog.updateHProxyMessageLog", hProxyMessageLog);
    }
    
    public int removeHProxyMessageLog(HProxyMessageLog hProxyMessageLog) throws Exception {
        return sqlMapClient.delete("HProxyMessageLog.removeHProxyMessageLog", hProxyMessageLog);
    }

}
