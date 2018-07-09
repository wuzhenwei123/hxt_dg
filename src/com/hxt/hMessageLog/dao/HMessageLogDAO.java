package com.hxt.hMessageLog.dao;

import java.util.List;

import com.hxt.hMessageLog.model.HMessageLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.utils.ResponseList;
/**
 * @author	zhangyiyang
 * @time	2016年08月23日 22:01:10
 */
 @Repository("hMessageLogDao")
public class HMessageLogDAO extends BaseDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClient;

	@SuppressWarnings("unchecked")
	public ResponseList<HMessageLog> getHMessageLogList(HMessageLog hMessageLog) {
		List<HMessageLog> list = sqlMapClient.queryForList("HMessageLog.getHMessageLogList", hMessageLog);
		return buildResponseList(list);
	}

	@SuppressWarnings("unchecked")
	public List<HMessageLog> getHMessageLogBaseList(HMessageLog hMessageLog) {
		return sqlMapClient.queryForList("HMessageLog.getHMessageLog", hMessageLog);
	}

	public int getHMessageLogListCount(HMessageLog hMessageLog) {
		return (Integer)sqlMapClient.queryForObject("HMessageLog.getHMessageLogListCount", hMessageLog);
	}
	
	public int getMessageLogCount(HMessageLog hMessageLog) {
		return (Integer)sqlMapClient.queryForObject("HMessageLog.getMessageLogCount", hMessageLog);
	}
	
	public HMessageLog getHMessageLog(HMessageLog hMessageLog) {
		return (HMessageLog)sqlMapClient.queryForObject("HMessageLog.getHMessageLog", hMessageLog);
	}

    public int insertHMessageLog(HMessageLog hMessageLog) throws Exception {
        return (Integer)sqlMapClient.insert("HMessageLog.insertHMessageLog", hMessageLog);
    }

    public int updateHMessageLog(HMessageLog hMessageLog) throws Exception {
        return sqlMapClient.update("HMessageLog.updateHMessageLog", hMessageLog);
    }
    
    public int removeHMessageLog(HMessageLog hMessageLog) throws Exception {
        return sqlMapClient.delete("HMessageLog.removeHMessageLog", hMessageLog);
    }

}
