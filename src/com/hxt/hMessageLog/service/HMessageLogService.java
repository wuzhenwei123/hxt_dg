package com.hxt.hMessageLog.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxt.hMessageLog.dao.HMessageLogDAO;
import com.hxt.hMessageLog.model.HMessageLog;
import com.base.utils.ResponseList;

/**
 * @author	zhangyiyang
 * @time	2016年08月23日 22:01:10
 */
 @Service("hMessageLogService")
public class HMessageLogService {

	@Resource(name = "hMessageLogDao")
    private HMessageLogDAO hMessageLogDAO;
    
    public ResponseList<HMessageLog> getHMessageLogList(HMessageLog hMessageLog) {
        return hMessageLogDAO.getHMessageLogList(hMessageLog);
    }
    
    public List<HMessageLog> getHMessageLogBaseList(HMessageLog hMessageLog) {
        return hMessageLogDAO.getHMessageLogBaseList(hMessageLog);
    }
    
    public int getHMessageLogListCount(HMessageLog hMessageLog) {
        return hMessageLogDAO.getHMessageLogListCount(hMessageLog);
    }
    
    public int getMessageLogCount(HMessageLog hMessageLog) {
    	return hMessageLogDAO.getMessageLogCount(hMessageLog);
    }

    public HMessageLog getHMessageLog(HMessageLog hMessageLog) { 
        return hMessageLogDAO.getHMessageLog(hMessageLog);
    }

    public int insertHMessageLog(HMessageLog hMessageLog) throws Exception {
        return hMessageLogDAO.insertHMessageLog(hMessageLog);
    }

    public int updateHMessageLog(HMessageLog hMessageLog) throws Exception {
        return hMessageLogDAO.updateHMessageLog(hMessageLog);
    }
    
    public int removeHMessageLog(HMessageLog hMessageLog) throws Exception {
        return hMessageLogDAO.removeHMessageLog(hMessageLog);
    }
    
}
