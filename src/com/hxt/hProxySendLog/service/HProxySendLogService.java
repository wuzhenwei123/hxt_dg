package com.hxt.hProxySendLog.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxt.hProxySendLog.dao.HProxySendLogDAO;
import com.hxt.hProxySendLog.model.HProxySendLog;
import com.base.utils.ResponseList;

/**
 * @author	wuzhenwei
 * @time	2016年11月17日 07:55:47
 */
 @Service("hProxySendLogService")
public class HProxySendLogService {

	@Resource(name = "hProxySendLogDao")
    private HProxySendLogDAO hProxySendLogDAO;
    
    public ResponseList<HProxySendLog> getHProxySendLogList(HProxySendLog hProxySendLog) {
        return hProxySendLogDAO.getHProxySendLogList(hProxySendLog);
    }
    
    public List<HProxySendLog> getHProxySendLogBaseList(HProxySendLog hProxySendLog) {
        return hProxySendLogDAO.getHProxySendLogBaseList(hProxySendLog);
    }
    
    public int getHProxySendLogListCount(HProxySendLog hProxySendLog) {
        return hProxySendLogDAO.getHProxySendLogListCount(hProxySendLog);
    }

    public HProxySendLog getHProxySendLog(HProxySendLog hProxySendLog) { 
        return hProxySendLogDAO.getHProxySendLog(hProxySendLog);
    }

    public int insertHProxySendLog(HProxySendLog hProxySendLog) throws Exception {
        return hProxySendLogDAO.insertHProxySendLog(hProxySendLog);
    }

    public int updateHProxySendLog(HProxySendLog hProxySendLog) throws Exception {
        return hProxySendLogDAO.updateHProxySendLog(hProxySendLog);
    }
    
    public int removeHProxySendLog(HProxySendLog hProxySendLog) throws Exception {
        return hProxySendLogDAO.removeHProxySendLog(hProxySendLog);
    }
    
}
